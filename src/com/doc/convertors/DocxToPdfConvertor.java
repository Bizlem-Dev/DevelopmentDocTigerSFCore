package com.doc.convertors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.Docx4jProperties;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.SpreadsheetMLPackage;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorkbookPart;
import org.docx4j.openpackaging.parts.SpreadsheetML.WorksheetPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.utils.Log4jConfigurator;
import org.docx4j.utils.SingleTraversalUtilVisitorCallback;
import org.docx4j.utils.TraversalUtilVisitor;
import org.docx4j.wml.Body;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.xlsx4j.org.apache.poi.ss.usermodel.DataFormatter;
import org.xlsx4j.sml.Cell;
import org.xlsx4j.sml.Row;
import org.xlsx4j.sml.Sheet;
import org.xlsx4j.sml.SheetData;
import org.xlsx4j.sml.Sheets;
import org.xlsx4j.sml.Workbook;
import org.xlsx4j.sml.Worksheet;

import com.doc.services.ApiCall;
import com.doc.util.Utility;

public class DocxToPdfConvertor {
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	final static Logger log = Logger.getLogger(DocxToPdfConvertor.class);

	public static void convertDocxFileToPDF(byte[] addedTable, String pdfPath, Map<String, Object> paramsMap) {
		try { 
			WordprocessingMLPackage wordMLPackage = readDocxFile(addedTable);
			prepare(wordMLPackage);
			System.out.println(XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(), true, true));
			replacePlaceholder(null, wordMLPackage, paramsMap);
			replacePlaceholderInHeader( wordMLPackage, paramsMap);
			createPDF(wordMLPackage, pdfPath);
		} catch (FileNotFoundException e) {
			log.info("error in convertDocxFileToPDF DocxToPdfConvertor1  "+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
			log.info("error in convertDocxFileToPDF DocxToPdfConvertor2  "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			log.info("error in convertDocxFileToPDF DocxToPdfConvertor3  "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void convertDocxFileToPDF(String docxPath,String pdfPath, Map<String, Object> paramsMap){
		try {
			WordprocessingMLPackage wordMLPackage = readDocxFile(docxPath);
			prepare(wordMLPackage);
			XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(), true, true);
			replacePlaceholder(null, wordMLPackage, paramsMap);
			replacePlaceholderInHeader( wordMLPackage, paramsMap);
			createPDF(wordMLPackage, pdfPath);
			
		} catch (FileNotFoundException e) {
			log.info("error in convertDocxFileToPDF2 DocxToPdfConvertor1  "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
			log.info("error in convertDocxFileToPDF2 DocxToPdfConvertor2  "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			log.info("error in convertDocxFileToPDF2 DocxToPdfConvertor3  "+e.getMessage() + " ::  " +e.getLocalizedMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
//seen
	public static void replaceParamsInDocxFile(JSONObject sfobj,String docxPath,String outputDocxPath, Map<String, Object> paramsMap){

		try {
			//XWPFDocument  doc = new XWPFDocument(new FileInputStream(docxPath));
			//ByteArrayOutputStream baos = null;
			//baos = new ByteArrayOutputStream();
			//doc.write(baos);
			//doc.close();
			// baos.toByteArray();
			//WordprocessingMLPackage wordMLPackage = readDocxFile( baos.toByteArray());
			log.info(" DocxToPdfConvertorstart  readDocxFile docxPath= "+docxPath);
			WordprocessingMLPackage wordMLPackage = readDocxFile(docxPath);
			log.info(" DocxToPdfConvertor1  docxPath= "+docxPath);
			prepare(wordMLPackage);
			log.info(" DocxToPdfConvertor2 sfobj= "+sfobj);
			if(paramsMap.containsKey("<<tablearray>>")) {
				log.info(" DocxToPdfConvertor2 paramsMap.get(<<tablearray>>)= " + paramsMap.get("<<tablearray>>"));
				JSONObject tblArr=new JSONObject(paramsMap.get("<<tablearray>>").toString());
				log.info(" DocxToPdfConvertor2 parseTableArray= " + tblArr);
				
				parseTableArray(wordMLPackage, tblArr);
			}
			
			//System.out.println(XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(), true, true));
			//replaceParagraph(paramsMap, wordMLPackage, wordMLPackage.getMainDocumentPart());
			replacePlaceholder(sfobj, wordMLPackage, paramsMap);
			log.info(" DocxToPdfConvertor3 paramsMap "+paramsMap);
			replacePlaceholderInHeader( wordMLPackage, paramsMap);
			log.info(" DocxToPdfConvertor4 outputDocxPath= "+outputDocxPath);
			writeDocxToStream( wordMLPackage, outputDocxPath);
			log.info(" DocxToPdfConvertor5  ");
		} catch (FileNotFoundException e) {
			log.info("error in DocxToPDF Convertor replaceParamsInDocxFile1 DocxToPdfConvertor1  "+e);

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
//			log.info("error in replaceParamsInDocxFile1 DocxToPdfConvertor2  "+e.getMessage());
			log.info("error in replaceParamsInDocxFile1 DocxToPdfConvertor2  "+e);
		// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			log.info("error in replaceParamsInDocxFile1 DocxToPdfConvertor3  "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void replaceParamsInDocxFile(JSONObject sfobj, byte[] modifiedFileArr, String outputDocxPath, Map<String, Object> paramsMap){

		try {
			WordprocessingMLPackage wordMLPackage = readDocxFile(modifiedFileArr);
			prepare(wordMLPackage);
			//System.out.println(XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(), true, true));
			if(paramsMap.containsKey("<<tablearray>>")) {
				JSONObject tblArr=new JSONObject(paramsMap.get("<<tablearray>>").toString());
				parseTableArray(wordMLPackage, tblArr);
			}
			
			replaceParagraph(paramsMap, wordMLPackage, wordMLPackage.getMainDocumentPart());
			replacePlaceholder(sfobj, wordMLPackage, paramsMap);
			replacePlaceholderInHeader( wordMLPackage, paramsMap);
			writeDocxToStream( wordMLPackage, outputDocxPath);
		} catch (FileNotFoundException e) {
			log.info("error in replaceParamsInDocxFile2 DocxToPdfConvertor1  "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
			log.info("error in replaceParamsInDocxFile2 DocxToPdfConvertor2  "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			log.info("error in replaceParamsInDocxFile2 DocxToPdfConvertor3  "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void convertByteArrayToDocx(byte[] modifiedFileArr, String outputDocxPath) {
		try {
			WordprocessingMLPackage wordMLPackage = readDocxFile(modifiedFileArr);
			prepare(wordMLPackage);
			System.out.println(XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(), true, true));
			writeDocxToStream( wordMLPackage, outputDocxPath);
		} catch (FileNotFoundException e) {
			log.info("error in convertByteArrayToDocx DocxToPdfConvertor1 "+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
			log.info("error in convertByteArrayToDocx DocxToPdfConvertor2 "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			log.info("error in convertByteArrayToDocx DocxToPdfConvertor3"+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void writeDocxToStream(WordprocessingMLPackage template, String target)  {
		try{
		File f = new File(target);
		template.save(f);
		}catch(Exception e) {
			log.error(e.getMessage());
		}
	}

	private static void createPDF(WordprocessingMLPackage wordMLPackage, String pdfPath) {
		try {
			long start = System.currentTimeMillis();


			// 1) Prepare Pdf settings
			PdfSettings pdfSettings = new PdfSettings();

			// 2) Convert WordprocessingMLPackage to Pdf
			OutputStream out = new FileOutputStream(new File(pdfPath));
			PdfConversion converter = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(
					wordMLPackage);
			converter.output(out, pdfSettings);

			System.out.println("Generate pdf/HelloWorld.pdf with "
					+ (System.currentTimeMillis() - start) + "ms");
			log.info("Generate pdf/HelloWorld.pdf with "
					+ (System.currentTimeMillis() - start) + "ms");

		} catch (Throwable e) {
			e.printStackTrace();
			log.info("error in DocxToPdfConvertor createPDF  "+e.getMessage());
		}
	}

	private static void replacePlaceholderInHeader(WordprocessingMLPackage wordMLPackage, Map<String, Object> paramsMap) {
	//	String filePath = bundle.getString("uploaded_templates_path")+"Damac.png";

	//	modifiedFileArr =AddTableDataInTemplate.addLogoInTablearabic(modifiedFileArr, filePath,  Integer.parseInt(objDocGenDTO.getLogoTableNo()));

		
		for (Entry<PartName, Part> entry : wordMLPackage.getParts().getParts().entrySet()) {

			Part p = entry.getValue(); 

			if (p instanceof HeaderPart) {
				//List<Object> texts = ((HeaderPart) p).getContent();
				List<Object> texts = getAllElementFromObject(p, Text.class);

				for (Object text : texts) {
					Text textElement = (Text) text;

					if(paramsMap.containsKey(textElement.getValue().trim())){
						textElement.setValue(paramsMap.get(textElement.getValue().trim()).toString());
					}else if(textElement.getValue().trim().contains("Attribute")){
						String[] attrParts = textElement.getValue().trim().split(" ");
						StringBuffer finalString = new StringBuffer("");
						for(String part : attrParts){
							
							if(!Utility.isNull(paramsMap.get(part.trim()))){
								finalString.append(paramsMap.get(part.trim()).toString()).append(" ");
							}else{
								finalString.append(part).append(" ");
							}
						}
						textElement.setValue(finalString.toString());
					}


					/*if (textElement.getValue().trim().equals(placeholder)) {
						textElement.setValue(replacement);
					}*/
				}
			}

			/*if (p instanceof FooterPart) {
				List<Object> texts = ((HeaderPart) p).getContent();
			}*/

		}
	}
	
	
	private static void replaceParagraph(Map<String, Object> paramsMap, WordprocessingMLPackage template, ContentAccessor addTo) {
		try {
			log.info("in replaceParagraph method");
		String textToAdd = ""; 
		// 1. get the paragraph
		 List<Object> paragraphs = getAllElementFromObject(template.getMainDocumentPart(), P.class);
		 log.info("paragraphs :: "+paragraphs);
		 P toReplace = null;
		 for (Object p : paragraphs) {
		  List<Object> texts = getAllElementFromObject(p, Text.class);
		  for (Object t : texts) {
		   Text content = (Text) t;
		   log.info("contennt :: "+content.getValue());
		   if (content.getValue().equals("payment_plan__c_en")) {
		    toReplace = (P) p;
		    if(paramsMap.containsKey("<<payment_plan__c_en>>")) {
		    textToAdd = (String) paramsMap.get("<<payment_plan__c_en>>");
		    //break;
			 System.out.println("Englisg "+textToAdd);
			 log.info("payment_plan__c_en "+textToAdd);

			// System.out.println(toReplace);
			 // we now have the paragraph that contains our placeholder: toReplace
			 // 2. split into seperate lines
			// String as[] = StringUtils.splitPreserveAllTokens(textToAdd, '\n');
			 String as[] = textToAdd.split("\n");
			 for (int i = 0; i < as.length; i++) {
			  String ptext = as[i];
				 log.info("ptext i="+i+" "+ptext);

			  // 3. copy the found paragraph to keep styling correct
			  P copy = (P) XmlUtils.deepCopy(toReplace);

			  // replace the text elements from the copy
			  List texts1 = getAllElementFromObject(copy, Text.class);
			  if (texts1.size() > 0) {
				  log.info("texts1.size() "+texts1.size());
			   Text textToReplace = (Text) texts1.get(0);
			   textToReplace.setValue(ptext);
			   log.info("1 en ");
			  }

			  // add the paragraph to the document
			  ((ContentAccessor)toReplace.getParent()).getContent().add(copy);
			 }
		    }
			 // 4. remove the original one
			 ((ContentAccessor)toReplace.getParent()).getContent().remove(toReplace);
			 log.info("2 en");
		   }else if (content.getValue().equals("payment_plan__c_ar")) {
			   if(paramsMap.containsKey("<<payment_plan__c_ar>>")) {
			    toReplace = (P) p;
			    textToAdd = (String) paramsMap.get("<<payment_plan__c_ar>>");
			    //break;
			    
				 System.out.println("arabic "+textToAdd);
				 // we now have the paragraph that contains our placeholder: toReplace
				 // 2. split into seperate lines
				// String as[] = StringUtils.splitPreserveAllTokens(textToAdd, '\n');
				 String as[] = textToAdd.split("\n");
				 for (int i = 0; i < as.length; i++) {
				  String ptext = as[i];
					 log.info("ptext i="+i+" "+ptext);

				  // 3. copy the found paragraph to keep styling correct
				  P copy = (P) XmlUtils.deepCopy(toReplace);

				  // replace the text elements from the copy
				  List texts1 = getAllElementFromObject(copy, Text.class);
				  if (texts1.size() > 0) {
					  log.info("texts1.size() "+texts1.size());

				   Text textToReplace = (Text) texts1.get(0);
				   textToReplace.setValue(ptext);
				   log.info("1 ar ");

				  }
				  
				  // add the paragraph to the document
				  ((ContentAccessor)toReplace.getParent()).getContent().add(copy);
				 }
			   }
				 // 4. remove the original one
				 ((ContentAccessor)toReplace.getParent()).getContent().remove(toReplace);
				 log.info("2 ar");

			   }else if (content.getValue().equals("j_purchaser_name")) {
				    toReplace = (P) p;
				    if(paramsMap.containsKey("<<j_purchaser_name>>")) {
				    textToAdd = (String) paramsMap.get("<<j_purchaser_name>>");
					 System.out.println("arabic "+textToAdd);
					 String as[] = textToAdd.split("\n");
					 for (int i = 0; i < as.length; i++) {
					  String ptext = as[i];
						 log.info("ptext i="+i+" "+ptext);
					  P copy = (P) XmlUtils.deepCopy(toReplace);
					  List texts1 = getAllElementFromObject(copy, Text.class);
					  if (texts1.size() > 0) {
						  log.info("texts1.size() "+texts1.size());
					   Text textToReplace = (Text) texts1.get(0);
					   textToReplace.setValue(ptext);
					   log.info("1 ar ");
					  }
					  ((ContentAccessor)toReplace.getParent()).getContent().add(copy);
					 }
				    }
					 ((ContentAccessor)toReplace.getParent()).getContent().remove(toReplace);
					 log.info("2 ar");

				   }else if (content.getValue().equals("j_purchaser_name_arabic")) {
					    toReplace = (P) p;
					    if(paramsMap.containsKey("<<j_purchaser_name_arabic>>")) {
					    textToAdd = (String) paramsMap.get("<<j_purchaser_name_arabic>>");
						 System.out.println("arabic "+textToAdd);
						 String as[] = textToAdd.split("\n");
						 for (int i = 0; i < as.length; i++) {
						  String ptext = as[i];
							 log.info("ptext i="+i+" "+ptext);
						  P copy = (P) XmlUtils.deepCopy(toReplace);
						  List texts1 = getAllElementFromObject(copy, Text.class);
						  if (texts1.size() > 0) {
							  log.info("texts1.size() "+texts1.size());
						   Text textToReplace = (Text) texts1.get(0);
						   textToReplace.setValue(ptext);
						   log.info("1 ar ");
						  }
						  ((ContentAccessor)toReplace.getParent()).getContent().add(copy);
						 }
					    }
						 ((ContentAccessor)toReplace.getParent()).getContent().remove(toReplace);
						 log.info("2 ar");

					   }else if (content.getValue().equals("j_purchaser")) {
						    toReplace = (P) p;
						    if(paramsMap.containsKey("<<j_purchaser>>")) {
						    textToAdd = (String) paramsMap.get("<<j_purchaser>>");
							 System.out.println("arabic "+textToAdd);
							 String as[] = textToAdd.split("\n");
							 for (int i = 0; i < as.length; i++) {
							  String ptext = as[i];
								 log.info("ptext i="+i+" "+ptext);
							  P copy = (P) XmlUtils.deepCopy(toReplace);
							  List texts1 = getAllElementFromObject(copy, Text.class);
							  if (texts1.size() > 0) {
								  log.info("texts1.size() "+texts1.size());
							   Text textToReplace = (Text) texts1.get(0);
							   textToReplace.setValue(ptext);
							   log.info("1 ar ");
							  }
							  ((ContentAccessor)toReplace.getParent()).getContent().add(copy);
							 }
						    }
							 ((ContentAccessor)toReplace.getParent()).getContent().remove(toReplace);
							 log.info("2 ar");

						   }else if (content.getValue().equals("j_purchaser_arabic")) {
							    toReplace = (P) p;
							    if(paramsMap.containsKey("<<j_purchaser_arabic>>")) {
							    textToAdd = (String) paramsMap.get("<<j_purchaser_arabic>>");
								 System.out.println("arabic "+textToAdd);
								 String as[] = textToAdd.split("\n");
								 for (int i = 0; i < as.length; i++) {
								  String ptext = as[i];
									 log.info("ptext i="+i+" "+ptext);
								  P copy = (P) XmlUtils.deepCopy(toReplace);
								  List texts1 = getAllElementFromObject(copy, Text.class);
								  if (texts1.size() > 0) {
									  log.info("texts1.size() "+texts1.size());
								   Text textToReplace = (Text) texts1.get(0);
								   textToReplace.setValue(ptext);
								   log.info("1 ar ");
								  }
								  ((ContentAccessor)toReplace.getParent()).getContent().add(copy);
								 }
							    }
								 ((ContentAccessor)toReplace.getParent()).getContent().remove(toReplace);
								 log.info("2 ar");

							   } else if (content.getValue().equals("j_purchaser_co")) {
							    toReplace = (P) p;
							    if(paramsMap.containsKey("<<j_purchaser_co>>")) {
							    textToAdd = (String) paramsMap.get("<<j_purchaser_co>>");
								 System.out.println("arabic "+textToAdd);
								 String as[] = textToAdd.split("\n");
								 for (int i = 0; i < as.length; i++) {
								  String ptext = as[i];
									 log.info("ptext i="+i+" "+ptext);
								  P copy = (P) XmlUtils.deepCopy(toReplace);
								  List texts1 = getAllElementFromObject(copy, Text.class);
								  if (texts1.size() > 0) {
									  log.info("texts1.size() "+texts1.size());
								   Text textToReplace = (Text) texts1.get(0);
								   textToReplace.setValue(ptext);
								   log.info("1 ar ");
								  }
								  ((ContentAccessor)toReplace.getParent()).getContent().add(copy);
								 }
							    }
								 ((ContentAccessor)toReplace.getParent()).getContent().remove(toReplace);
								 log.info("2 ar");

							   }
							   else if (content.getValue().equals("j_purchaser_arabic_co")) {
								    toReplace = (P) p;
								    if(paramsMap.containsKey("<<j_purchaser_arabic_co>>")) {
								    textToAdd = (String) paramsMap.get("<<j_purchaser_arabic_co>>");
									 System.out.println("arabic "+textToAdd);
									 String as[] = textToAdd.split("\n");
									 for (int i = 0; i < as.length; i++) {
									  String ptext = as[i];
										 log.info("ptext i="+i+" "+ptext);
									  P copy = (P) XmlUtils.deepCopy(toReplace);
									  List texts1 = getAllElementFromObject(copy, Text.class);
									  if (texts1.size() > 0) {
										  log.info("texts1.size() "+texts1.size());
									   Text textToReplace = (Text) texts1.get(0);
									   textToReplace.setValue(ptext);
									   log.info("1 ar ");
									  }
									  ((ContentAccessor)toReplace.getParent()).getContent().add(copy);
									 }
								    }
									 ((ContentAccessor)toReplace.getParent()).getContent().remove(toReplace);
									 log.info("2 ar");

								   }
		  }
		 }
		}catch(Exception e) {
			log.info("error in replaceParagraph method"+ e.getMessage());

		}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	private static void replacePlaceholder(JSONObject sfobj, WordprocessingMLPackage wordMLPackage, Map<String, Object> paramsMap) {
		ResourceBundle bundle = ResourceBundle.getBundle("config");
////////jhgvyufyfg
		List<Object> texts = getAllElementFromObject(wordMLPackage.getMainDocumentPart(), Text.class);
//		for (Entry<String, Object> pair : paramsMap.entrySet()) {
//			System.out.println("key "+pair.getKey());
//			System.out.println("value "+pair.getValue());
//			 }
		try {
		    for (Object text : texts) {
			Text textElement = (Text) text;

			
			
			//for (Map.Entry<String, Object> entry : paramsMap.entrySet()){
			System.out.println("textElement1 ::  "+textElement.getValue().trim());
			log.info("textElement1 ::  "+textElement.getValue().trim());
			
			
			try {
				 if(textElement.getValue().trim().contains("[[") && textElement.getValue().trim().contains("]]")){
					 log.info("1 ");
				// by pallavi
					 log.info("in [[ ]]");
				//System.out.println("in 2 if");
				String[] tds = StringUtils.substringsBetween(textElement.getValue().trim(), "[[", "]]");
				//System.out.println(tds.length);
				log.info("tds.length ::  "+tds.length);
				StringBuffer finalString = new StringBuffer(textElement.getValue().trim());
	         log.info(finalString.indexOf("[["));
	        log.info(finalString.indexOf("]]"));


				for (String td : tds) {
					//System.out.println("td: "+td);
					log.info("td ::  "+td);

					log.info("[["+td+"]]");
					//System.out.println("paramsMap: "+paramsMap.get("<<Account Number>>").toString());
					if(paramsMap.get("[["+td+"]]")!=null && paramsMap.get("[["+td+"]]")!="") {

			//		System.out.println("paramsMap: "+paramsMap.get("<<"+td+">>").toString());
				log.info("paramsMap: "+paramsMap.get("[["+td+"]]").toString());
				JSONArray arr = new JSONArray(paramsMap.get("[["+td+"]]").toString());			
			log.info("arr"+arr );
			String data="";
				for(int i=0; i<arr.length(); i++) {
					data=data+"\r\n"+ arr.getString(i);
					log.info("data*** "+ 	data);
 
				}
				
				////for  clause name
				String name="";
				String clsname=td.substring(0, td.lastIndexOf("_"));
				log.info("clsname "+clsname);
				log.info("td.contains(\"English\") "+td.contains("English"));
				log.info("td.contains(\"Arabic\") "+td.contains("Arabic"));

			if(td.contains("English")){
				log.info("td_clausename_English ****" +clsname+"_clausename_English");
				log.info("paramsMap.containsKey(clsname+\"_clausename_English\")"+  paramsMap.containsKey(clsname+"_clausename_English"));
				if(paramsMap.containsKey(clsname+"_clausename_English")) {
				 name=paramsMap.get(clsname+"_clausename_English").toString();
				 log.info("English name "+name);
				}
				///------------
	            //if(finalString.contains("[["+td+"]]")){
					//finalString.replace(finalString.indexOf("[["), finalString.indexOf("]]")+2, td+"\r\n "+paramsMap.get("[["+td+"]]").toString());
					finalString.replace(finalString.indexOf("[["), finalString.indexOf("]]")+2, name+"\r\n "+data);

					//	System.out.println("textElement1 ::  "+textElement.getValue().trim());
	           //	 }
					
					
					}else if(td.contains("Arabic")) {
						log.info("paramsMap.containsKey(clsname+\"_clausename_English\")"+  paramsMap.containsKey(clsname+"_clausename_Arabic"));

						if(paramsMap.containsKey(clsname+"_clausename_Arabic")) {
							 name=paramsMap.get(clsname+"_clausename_Arabic").toString();
							 log.info("Arabic name "+name);

							}
						finalString.replace(finalString.indexOf("[["), finalString.indexOf("]]")+2, name+"\r\n "+data);
						
					} else {
						finalString.replace(finalString.indexOf("[["), finalString.indexOf("]]")+2, td+"\r\n "+data);

					}
					
					
					}
				}
				textElement.setValue(finalString.toString());

				}	
				}catch(Exception e) {
					//e.getMessage();
					}
			
			
			
			
			
			
			log.info("paramsMap.containsKey(textElement.getValue().trim()) ::  "+paramsMap.containsKey(textElement.getValue().trim()));

			if(paramsMap.containsKey(textElement.getValue().trim())){
				 log.info("2 ");

				textElement.setValue(paramsMap.get(textElement.getValue().trim()).toString());
				log.info("textElement after replace ::  "+	textElement.getValue().trim());

			}
							
			for (Entry<String, Object> pair : paramsMap.entrySet()) {
				if(textElement.getValue().trim().contains(pair.getKey())){
					 log.info("3 ");

					String[] attrParts = textElement.getValue().trim().split(" ");
					StringBuffer finalString = new StringBuffer("");
					for(String part : attrParts){
						log.info("part ::  "+part);
						if(!Utility.isNull(paramsMap.get(part.trim().replace(".", "")))){
							finalString.append(paramsMap.get(part.trim().replace(".", "")).toString()).append(" ");
						}else{
							finalString.append(part).append(" ");
						}
					}
					textElement.setValue(finalString.toString());
					log.info("textElement after replace ::  "+	textElement.getValue().trim());

				}
			}
			
			
			
			
			
			
//			else if(textElement.getValue().trim().contains("Attribute")){
//				String[] attrParts = textElement.getValue().trim().split(" ");
//				StringBuffer finalString = new StringBuffer("");
//				for(String part : attrParts){
//					if(!Utility.isNull(paramsMap.get(part.trim().replace(".", "")))){
//						finalString.append(paramsMap.get(part.trim().replace(".", "")).toString()).append(" ");
//					}else{
//						finalString.append(part).append(" ");
//					}
//				}
//				textElement.setValue(finalString.toString());
//			}

			
			 
				//log.info("before [{["); // good
				try{

			 if(textElement.getValue().trim().contains("[{[")){
					// by pallavi
				//	log.info("in [{["); // good
					String SFObject="";
					String Primery_key="";
					String Primery_key_value="";
					log.info("sfobj!=null" +sfobj!=null); // good

					if(sfobj!=null) {
						 SFObject=sfobj.getString("SFObject");
						 Primery_key=sfobj.getString("Primery_key");
						 Primery_key_value=sfobj.getString("Primery_key_value");
							log.info("SFObject "+SFObject +" Primery_key "+Primery_key +" Primery_key_value "+Primery_key_value); // good
 
					}

					String[] cla = StringUtils.substringsBetween(textElement.getValue().trim(), "[{[", "]}]");
				//	System.out.println(cla.length);
					StringBuffer finalString = new StringBuffer(textElement.getValue().trim());
					log.info("cla.length " + cla.length); // good

					for (String clause : cla) {
					log.info("[{[]}] " + clause+" *"); // good

					JSONObject param= new JSONObject();
					param.put("Email", "doctiger8@gmail.com");
					param.put("rulename", clause);
					param.put("SFObject", SFObject);
					param.put("Primery_key", Primery_key);
					param.put("Primery_key_value", Primery_key_value);

				
					log.info("param  "+param);
							String result =ApiCall.callPostJSon(bundle.getString("Doctigerruleclause"), param);
						String allclauses="";
						log.info("result ~~~  "+result);
						JSONObject resobj= new JSONObject(result);
								JSONArray ruleclause = resobj.getJSONArray("allclauses");
							for(int i=0; i<ruleclause.length(); i++) {
								JSONObject obj =ruleclause.getJSONObject(i);
								log.info("obj :: "+obj);
								String clausename=obj.getString("clausename");
								String Desc= obj.getString("Desc");
								allclauses=allclauses+"\r\n"+clausename+" \n "+Desc;
								
							}
							log.info("allclauses "+allclauses);
							log.info("finalString.indexOf(\"[{[\") "+finalString.indexOf("[{["));
							log.info("finalString.indexOf(\"]}]\")+1 "+finalString.indexOf("]}]")+1);

						finalString.replace(finalString.indexOf("[{["), finalString.indexOf("]}]")+3, allclauses);
					
					}
					textElement.setValue(finalString.toString());
		
					} 
				}catch(Exception e) {
					log.info("exception [{["+e.getMessage());
				}
			 
			
			
			
//			 if(textElement.getValue().trim().contains("<<")){
//					// by pallavi
//					//System.out.println("in 2 if");
//					String[] tds = StringUtils.substringsBetween(textElement.getValue().trim(), "<<", ">>");
//					//System.out.println(tds.length);
//					StringBuffer finalString = new StringBuffer(textElement.getValue().trim());
//                 //System.out.println(finalString.indexOf("<<"));
//                 //System.out.println(finalString.indexOf(">>"));
//
//
//					for (String td : tds) {
//						//System.out.println("td: "+td);
//						//System.out.println("<<"+td+">>");
//						//System.out.println("paramsMap: "+paramsMap.get("<<Account Number>>").toString());
//						if(paramsMap.get("<<"+td+">>")!=null && paramsMap.get("<<"+td+">>")!="") {
//
//				//		System.out.println("paramsMap: "+paramsMap.get("<<"+td+">>").toString());
//						finalString.replace(finalString.indexOf("<<"), finalString.indexOf(">>")+2, paramsMap.get("<<"+td+">>").toString());
//					//	System.out.println("textElement1 ::  "+textElement.getValue().trim());
//						}
//					}
//					textElement.setValue(finalString.toString());
//		
//					}
			 
//			 
//			 if(textElement.getValue().trim().contains("[")){
//					// by pallavi
//					
//					String[] cla = StringUtils.substringsBetween(textElement.getValue().trim(), "[", "]");
//				//	System.out.println(cla.length);
//					StringBuffer finalString = new StringBuffer(textElement.getValue().trim());
//
//					for (String clause : cla) {
//						System.out.println("[]" + clause+"*"); // good
//						 String PARAM=bundle.getString("clauseparam")+"="+clause;
//							String result =ApiCall.callPosturl(bundle.getString("Doctigerclause"), PARAM);
//						finalString.replace(finalString.indexOf("["), finalString.indexOf("]")+1, result);
//					
//					}
//					textElement.setValue(finalString.toString());
//		
//					}
			 
			 
			/*if (textElement.getValue().trim().equals(placeholder)) {
				textElement.setValue(replacement);
			}*/
		}
			 
			 
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
		List<Object> result = new ArrayList<Object>();
		if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();

		if (obj.getClass().equals(toSearch))
			result.add(obj);
		else if (obj instanceof ContentAccessor) {
			List<?> children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(getAllElementFromObject(child, toSearch));
			}
		}
		return result;
	}


	private static WordprocessingMLPackage readDocxFile(String docxPath) throws FileNotFoundException, Docx4JException, Exception  
	{
//		try {
		Docx4jProperties.getProperties().setProperty("docx4j.Log4j.Configurator.disabled", "true");
		log.info("1 in readDocxFile");
		Log4jConfigurator.configure();            
		log.info("2");
		org.docx4j.convert.out.pdf.viaXSLFO.Conversion.log.setLevel(Level.OFF);
		log.info("3");

		InputStream is = new FileInputStream(new File(docxPath));
		log.info("4docxPath"+docxPath);
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(is);
		log.info("5");
		return wordMLPackage;
		
	}

	private static WordprocessingMLPackage readDocxFile(byte[] byteArr) throws FileNotFoundException, Docx4JException, Exception  
	{
		Docx4jProperties.getProperties().setProperty("docx4j.Log4j.Configurator.disabled", "true");
		Log4jConfigurator.configure();            
		org.docx4j.convert.out.pdf.viaXSLFO.Conversion.log.setLevel(Level.OFF);

		InputStream is = new ByteArrayInputStream(byteArr);

		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(is);

		return wordMLPackage;
	}

	public static void prepare(WordprocessingMLPackage wmlPackage) throws Exception {

		// Apply the filter
		WordprocessingMLPackage.FilterSettings filterSettings = new WordprocessingMLPackage.FilterSettings();
		filterSettings.setRemoveProofErrors(true);
		filterSettings.setRemoveContentControls(true);
		filterSettings.setRemoveRsids(true);
		wmlPackage.filter(filterSettings);
		// Note the filter is deprecated, since its questionable whether this
		// is important enough to live in WordprocessingMLPackage,
		// and in any case probably should be replaced with a TraversalUtil
		// approach (which wouldn't involve marshal/unmarshall, and 
		// so should be more efficient).

		//        if(log.isInfoEnabled()) {
		//            log.info(XmlUtils.marshaltoString(wmlPackage.getMainDocumentPart().getJaxbElement(), true, true));
		//        }

		// Now clean up some more
		org.docx4j.wml.Document wmlDocumentEl = wmlPackage.getMainDocumentPart().getJaxbElement();
		Body body =  wmlDocumentEl.getBody();

		SingleTraversalUtilVisitorCallback paragraphVisitor 
		= new SingleTraversalUtilVisitorCallback(
				new TraversalUtilParagraphVisitor());
		paragraphVisitor.walkJAXBElements(body);

//		if(log.isInfoEnabled()) {
//			log.info(XmlUtils.marshaltoString(wmlPackage.getMainDocumentPart().getJaxbElement(), true, true));
//		}
	}

	private final static QName _RT_QNAME = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "t");


	public static void joinupRuns(P p) {

		List<Object> existingContents = p.getContent();
		List<Object> newContents = new ArrayList<Object>();

		R currentR = null;
		String currentRPrString = null;

		// First join up runs with same run properties
		for (Object o : existingContents) {

			if (o instanceof R) {

				if (currentR==null) { // first object, or after something not a run
					currentR=(R)o;
					if (currentR.getRPr()!=null) {
						currentRPrString = XmlUtils.marshaltoString(currentR.getRPr(), true);
					}
					newContents.add(currentR);
				} else {
					RPr other = ((R)o).getRPr();

					boolean makeNewRun = true; // unless proven otherwise

					if (currentRPrString==null && other==null) makeNewRun=false;
					if (currentRPrString!=null && other!=null) {
						// Simple minded notion of equality
						if ( XmlUtils.marshaltoString(other, true).equals(currentRPrString) )  makeNewRun=false; 
					}

					if (makeNewRun) {
						currentR=(R)o;
						if (currentR.getRPr()==null) {
							currentRPrString = null;
						} else {
							currentRPrString = XmlUtils.marshaltoString(currentR.getRPr(), true);
						}
						newContents.add(currentR);
					} else {
						currentR.getContent().addAll( ((R)o).getContent() );
					}
				}

			} else {
				// not a run (eg w:ins) .. just add it and move on
				newContents.add(o);
				currentR = null;
				currentRPrString = null;
			}

		}

		// Now, in each run, join up adjacent text nodes
		for (Object o : newContents) {

			if (o instanceof R) {

				List<Object> newRunContents = new ArrayList<Object>();	
				JAXBElement currentT = null;
				for ( Object rc : ((R)o).getContent() ) {

					if (rc instanceof JAXBElement
							&& ((JAXBElement)rc).getName().equals(_RT_QNAME)) {

						if (currentT==null) { // first object, or after something not a w:t
							currentT=(JAXBElement)rc;
							newRunContents.add(currentT);
						} else {
							Text currentText = (Text)XmlUtils.unwrap(currentT);
							String val = currentText.getValue();

							currentText.setValue(val + ((Text)XmlUtils.unwrap(rc)).getValue() );								
						}

						// <w:t xml:space="preserve">
						if (((Text)XmlUtils.unwrap(rc)).getSpace()!=null
								&& ((Text)XmlUtils.unwrap(rc)).getSpace().equals("preserve")) { // any of them
							((Text)XmlUtils.unwrap(currentT)).setSpace("preserve");
						}

					} else {
						log.debug(rc.getClass().getName());
						// not text .. just add it and move on
						newRunContents.add(rc);
						currentT = null;
					}

				}

				((R)o).getContent().clear();
				((R)o).getContent().addAll(newRunContents);

			}

		}

		// Now replace w:p contents
		p.getContent().clear();
		p.getContent().addAll(newContents);

	}

	public static class TraversalUtilParagraphVisitor extends TraversalUtilVisitor<P> {

		@Override
		public void apply(P p, Object parent, List<Object> siblings) {
			joinupRuns(p);
		}

	}
	
	public static void convertxlsxFileToPDF(String docxPath,String pdfPath, Map<String, Object> paramsMap){

		try {
			
			SpreadsheetMLPackage xlsxMLPackage = readxlsxFile(docxPath);
			
	//		preparexlsx(xlsxMLPackage);
		//	replacePlaceholder(wordMLPackage, paramsMap);
		//	replacePlaceholderInHeader( wordMLPackage, paramsMap);
		//	createPDF(wordMLPackage, pdfPath);
		} catch (FileNotFoundException e) {
			log.info("error in convertDocxFileToPDF2 DocxToPdfConvertor1  "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Docx4JException e) {
			log.info("error in convertDocxFileToPDF2 DocxToPdfConvertor2  "+e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			log.info("error in convertDocxFileToPDF2 DocxToPdfConvertor3  "+e.getMessage() + " ::  " +e.getLocalizedMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static SpreadsheetMLPackage readxlsxFile(String docxPath) throws FileNotFoundException, Docx4JException  
	{
		Docx4jProperties.getProperties().setProperty("docx4j.Log4j.Configurator.disabled", "true");
		Log4jConfigurator.configure();            
		org.docx4j.convert.out.pdf.viaXSLFO.Conversion.log.setLevel(Level.OFF);

		InputStream is = new FileInputStream(new File(docxPath));
	//	SpreadsheetMLPackage xlsxMLPackage =  (SpreadsheetMLPackage) SpreadsheetMLPackage.load(is);
		SpreadsheetMLPackage xlsxMLPackage = SpreadsheetMLPackage.load(new java.io.File(docxPath));		

		return xlsxMLPackage;
	}

//	public static void preparexlsx(SpreadsheetMLPackage xlsxMLPackage) throws Exception {
//
//		WorkbookPart workbookPart = xlsxMLPackage.getWorkbookPart();
//		WorksheetPart sheet = workbookPart.getWorksheet(0);		
//		DataFormatter formatter = new DataFormatter();
//
//		// Now lets print the cell content
//		displayContent(sheet, formatter);
//
//	}
//	
//	
//	private static void displayContent(WorksheetPart sheet, DataFormatter formatter) throws Docx4JException {
//
//		Worksheet ws = sheet.getContents();
//		SheetData data = ws.getSheetData();
//		
//		for (Row r : data.getRow() ) {
//			System.out.println("row " + r.getR() );			
//			
//			for (Cell c : r.getC() ) {
//
//
//	            // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
//	            String text = formatter.formatCellValue(c);
//	            System.out.println(c.getR() + " contains " + text);
//
//	            }
//		}
//		
//	}
//	
	
	/* Array Implementation for multiple tables in doc and replace the values */
	private static void replaceTable(String[] placeholders, List<Map<String, String>> textToAdd,
			WordprocessingMLPackage template) throws Docx4JException, JAXBException {
		List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);

		// 1. find the table
		Tbl tempTable = getTemplateTable(tables, placeholders[0]);
		List<Object> rows = getAllElementFromObject(tempTable, Tr.class);
		log.info("replaceTable 1");
		// first row is header, second row is content
		if (rows.size() == 2) {
			// this is our template row
			Tr templateRow = (Tr) rows.get(1);

			for (Map<String, String> replacements : textToAdd) {
				// 2 and 3 are done in this method
				addRowToTable(tempTable, templateRow, replacements);
			}

			// 4. remove the template row
			tempTable.getContent().remove(templateRow);
		}

	}

	private static Tbl getTemplateTable(List<Object> tables, String templateKey) throws Docx4JException, JAXBException {
		for (Iterator<Object> iterator = tables.iterator(); iterator.hasNext();) {
			Object tbl = iterator.next();
			List<?> textElements = getAllElementFromObject(tbl, Text.class);
			for (Object text : textElements) {
				Text textElement = (Text) text;
				if (textElement.getValue() != null && textElement.getValue().equals(templateKey))
					return (Tbl) tbl;
			}
		}
		return null;
	}

	private static void addRowToTable(Tbl reviewtable, Tr templateRow, Map<String, String> replacements) {
		Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
		log.info("addRowToTable 1");
		List textElements = getAllElementFromObject(workingRow, Text.class);
		for (Object object : textElements) {
			Text text = (Text) object;
			String replacementValue = (String) replacements.get(text.getValue());
			if (replacementValue != null)
				text.setValue(replacementValue);
		}

		reviewtable.getContent().add(workingRow);
	}

	public static void parseTableArray(WordprocessingMLPackage template, JSONObject tblArr) {
		try {
			log.info(" DocxToPdfConvertor2 parseTableArray 1" );
//		Docx4jSampleForReplaceTable objDocx4jSampleForReplaceTable = new Docx4jSampleForReplaceTable();
		Iterator keys = tblArr.keys();
		
		while (keys.hasNext()) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		Map<String, String> repl1 = null;
		// loop to get the dynamic key
		String currentDynamicKey = (String) keys.next();
		log.info(" DocxToPdfConvertor2 currentDynamicKey "+currentDynamicKey );
		// get the value of the dynamic key
		JSONArray currentDynamicValue = tblArr.getJSONArray(currentDynamicKey);
		log.info(" DocxToPdfConvertor2 currentDynamicValue "+currentDynamicValue );
		String placeholder[] = null;
		int arrCount = 0;
		for (int i = 0; i < currentDynamicValue.length(); i++) {
		repl1 = new HashMap<String, String>();
		JSONObject subJson = currentDynamicValue.getJSONObject(i);
		if (i == 0) {
		placeholder = new String[subJson.length()];
		}
		log.info(" DocxToPdfConvertor2 subJson 1 : "+subJson );
		System.out.println("subJson :: " + subJson);
		Iterator keysJson = subJson.keys();

		while (keysJson.hasNext()) {
		String subKey = (String) keysJson.next();
		String subValue = subJson.getString(subKey);
		repl1.put(subKey, subValue);
		if (i == 0) {
		placeholder[arrCount] = subKey;
		arrCount++;
		}
		}
		mapList.add(repl1);
		}
		log.info(" DocxToPdfConvertor2 mapList "+mapList );
		replaceTable(placeholder, mapList, template);

		}
		} catch (Exception e) {
		System.out.println("error :: "+e.getMessage());
		}

		}
	
}
