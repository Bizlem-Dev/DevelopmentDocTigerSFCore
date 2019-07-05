package com.doc.services;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.xmlbeans.XmlException;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.utils.Log4jConfigurator;

import com.doc.convertors.AddTableDataInTemplate;
import com.doc.convertors.DocxToPdfConvertor;
import com.doc.convertors.QRCode;
import com.doc.convertors.RtfToPdfConvertor;
import com.doc.util.AttributeArrForAll;
import com.doc.util.IConstants;
import com.doc.util.Utility;
import com.dto.DocGenDTO;
import com.dto.TemplateFileVO;
import com.scorpion.request.ConvertRequestProperty;
import com.scorpion.service.ConvertServiceImpl;

import reactor.util.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DocGenService {
	final static Logger logger = Logger.getLogger(DocGenService.class);
	ResourceBundle bundle = ResourceBundle.getBundle("config");
	static ResourceBundle bundleststic = ResourceBundle.getBundle("config");

	QRCode objQRCode = new QRCode();
	 ConvertServiceImpl convertService;

	
	public String converEnglishDocxFileToPdf(JSONObject obj, TemplateFileVO templateFileVO) {
logger.info("converEnglishDocxFileToPdf");
		// Build the data-model
System.out.println("in converEnglishDocxFileToPdf");
JSONObject sfobj = new JSONObject();
JSONObject QRobj = new JSONObject();


		Map<String, Object> data = new HashMap<String, Object>();
		String url = null;
		try{
			if (obj.has("QRobj")) { 
				QRobj=obj.getJSONObject("QRobj");
				logger.info(" QRobj :"+sfobj.toString());
				obj.remove("QRobj");
	    	}
			if (obj.has("sfobj")) { 
	    		sfobj=obj.getJSONObject("sfobj");
				logger.info(" sfobj :"+sfobj.toString());
				obj.remove("sfobj");
	    	}
			
			System.out.println("in converEnglishDocxFileToPdf");
			 data = new HashMap<String, Object>();
			
				Iterator keys = obj.keys();
			    while(keys.hasNext()) {
			    	String key = (String)keys.next();
			    	if (key.equals("sfobj")) { 
			    	}if (key.equals("QRobj")) { 
			    	}else {

			       String value = (String)obj.get(key);
			logger.info("key : "+key+ " value :"+value);
			        data.put(key, value);
			    	}
			    }
				
		
			String outputFilename = getFilename(templateFileVO.getTemaplateName(), obj);
			System.out.println("outputFilename "+outputFilename);
		//	String outputPdfPath = bundle.getString("doc_loc")+getFilename(templateFileVO.getTemaplateName(), obj)+IConstants.PERIOD+IConstants.EXTENSION_PDF;
			String outputPdfPath = bundle.getString("doc_loc")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_PDF;

			url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_PDF;

			System.out.println(templateFileVO.getTemaplatePath());
					DocxToPdfConvertor.convertDocxFileToPDF(templateFileVO.getTemaplatePath(), outputPdfPath, data);
				
				//url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_DOCX;
				logger.info("url"+url);
			return url;
		}catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}finally {
			
		}
		
	}

	
	
	
	
	
	public String converArabicDocxFileToPdf(JSONObject obj , TemplateFileVO templateFileVO) {
		System.out.println("converArabicDocxFileToPdf");
				// Build the data-model
		JSONObject sfobj = new JSONObject();
		JSONObject QRobj = null;

				Map<String, Object> data = new HashMap<String, Object>();
				String url = null;
				String docxurl = null;
				try{

					logger.info("obj "+obj);
					if (obj.has("QRobj")) { 
						QRobj = new JSONObject();
						QRobj=obj.getJSONObject("QRobj");
						logger.info(" QRobj :"+QRobj.toString());
						obj.remove("QRobj");

			    	}
					if (obj.has("sfobj")) { 
			    		sfobj=obj.getJSONObject("sfobj");
						logger.info(" sfobj :"+sfobj.toString());
						obj.remove("sfobj");

			    	}
					
					String logourl= "";
					if (obj.has("logo")) { 
						logourl=obj.getString("logo");
						logger.info(" logourl :"+logourl);
						obj.remove("logo");

			    	}
					
					String Coverimageurl= "";
					String Coverimage_tableNo= "";
					if (obj.has("Coverimageurl") &&  obj.has("Coverimage_tableNo") ){ 
						Coverimageurl=obj.getString("Coverimageurl");
						Coverimage_tableNo=obj.getString("Coverimage_tableNo");
						logger.info(" Coverimageurl :"+Coverimageurl);
						logger.info(" Coverimage_tableNo :"+Coverimage_tableNo);
						obj.remove("Coverimageurl");
						obj.remove("Coverimage_tableNo");


			    	}
					String floorplanstring= "";
					if (obj.has("floorplanarr")) { 
						floorplanstring=obj.getString("floorplanarr");
						logger.info(" floorplanarr :"+floorplanstring);
						obj.remove("floorplanarr");

			    	} 
					
					String promotion="";
					if (obj.has("<<promotion>>")) { 
						promotion=obj.getString("<<promotion>>");
						logger.info(" promotion :"+promotion);
						obj.remove("<<promotion>>");

			    	}
					
					logger.info(" obj *  :"+sfobj.toString());

					
					System.out.println("in converArabicDocxFileToPdf");
					 data = new HashMap<String, Object>();
					
						Iterator keys = obj.keys();
					    while(keys.hasNext()) {
					    	String key = (String)keys.next();
					    	if (key.equals("sfobj")) { 
					    	}if (key.equals("QRobj")) { 
					    	}else {

					       String value = (String)obj.get(key);
					logger.info("key : "+key+ " value :"+value);
					        data.put(key, value);
					    	}
					    }
						//int coverimagetableno=1;
						int logoimagetableno=1;
						//int qrCodeTableNumber =2;
//						try{
//							qrCodeTableNumber = Integer.parseInt(obj.getString("qrCodeTableNumber"));
//						}catch(Exception e){
//							qrCodeTableNumber = 0;
//						}
						   
						String outputFilename = getFilename(templateFileVO.getTemaplateName(), obj);
						String outputPdfPath = bundle.getString("doc_loc")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_PDF;
						String outputDocxPath = bundle.getString("doc_loc")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_DOCX;

					//	url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_PDF;
				url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_PDF;
						logger.info("outputFilename "+outputFilename);
						logger.info("outputPdfPath "+outputPdfPath);
						logger.info("outputDocxPath "+outputDocxPath);

						
						byte[] modifiedFileArr = null;
						//is qr code present
						//if(qrCodeTableNumber>1 && QRobj!=null && (!Coverimageurl.equals(""))){
							if( QRobj!=null ){

							logger.info("qrCodeTableNumber  in if "+QRobj.getString("TableNo"));
							logger.info("templateFileVO.getTemaplatePath() "+templateFileVO.getTemaplatePath());
							logger.info("Coverimageurl in if "+Coverimageurl);
							modifiedFileArr =addQRCode (templateFileVO.getTemaplatePath(),  Integer.parseInt(QRobj.getString("TableNo")),  QRobj);
                       if(!Coverimageurl.equals("")) {
							modifiedFileArr=addCoverImage(modifiedFileArr, Coverimageurl, Integer.parseInt(Coverimage_tableNo), 420, 460);
                            }
						//	modifiedFileArr=addLogo(modifiedFileArr, logourl, logoimagetableno, 80, 50);
						//	modifiedFileArr=addfloorplan(modifiedFileArr, floorplanstring,  300, 350);

							
							DocxToPdfConvertor.replaceParamsInDocxFile(sfobj, modifiedFileArr, outputDocxPath, data);
							//modifiedFileArr =addQRCode (outputDocxPath,  qrCodeTableNumber,  objDocGenDTO);
							//DocxToPdfConvertor.convertByteArrayToDocx(modifiedFileArr, outputDocxPath);
							//RtfToPdfConvertor.convertDocxFileToPDF(outputDocxPath, outputPdfPath, data, bundle.getString("doc_loc"));

						}else{
								logger.info("in else QRCode ");
								logger.info("templateFileVO.getTemaplatePath()"+ templateFileVO.getTemaplatePath());
								DocxToPdfConvertor.replaceParamsInDocxFile(sfobj, templateFileVO.getTemaplatePath(), outputDocxPath, data);
//								//RtfToPdfConvertor.convertDocxFileToPDF(outputDocxPath, outputPdfPath, data, bundle.getString("doc_loc"));
//								String applicationId=bundle.getString("applicationId");
//		                           logger.info("applicationId*  "+applicationId);
//	                            String secretKey=bundle.getString("secretKey");
//	                            logger.info("secretKey*  "+secretKey);
//								
//								convertService = new ConvertServiceImpl(applicationId, secretKey);
//						         File file = new File(outputDocxPath);
//						         logger.info("file*  "+file);
//						         ConvertRequestProperty convertRequestProperty = ConvertRequestProperty.builder()
//						                 .inputFile(file).build();
//						         logger.info("1*  ");
//						         byte[] bytes = convertService.convert(convertRequestProperty);
//						         logger.info("2*  ");
//						         FileUtils.writeByteArrayToFile(new File(outputPdfPath), bytes);
//						         logger.info("3*  ");
//						         Assert.notNull(bytes);
//						         logger.info("4*  ");
//		
							}
						
		//				wait(5000);
						
//						String applicationId=bundle.getString("applicationId");
//                           logger.info("applicationId*  "+applicationId);
//                        String secretKey=bundle.getString("secretKey");
//                        logger.info("secretKey*  "+secretKey);
//                        
//                        RestTemplate restTemplate = new RestTemplate();
//				         
//				         
//				         JSONObject jsonObj = new JSONObject();
//				         jsonObj.put("applicationId", applicationId);
//				         jsonObj.put("secretKey", secretKey);
//				         jsonObj.put("inputDocxFilePath", outputDocxPath);
//				         jsonObj.put("outputPdfFilePath", outputPdfPath);
//				         logger.info("jsonObj= "+jsonObj.toString());
//				         HttpHeaders headers = new HttpHeaders();
//				         headers.setContentType(MediaType.APPLICATION_JSON);	
//				         logger.info("headers= "+headers);
//				         
//				         HttpEntity<String> entity = new HttpEntity<String>(jsonObj.toString(),headers);
//				         logger.info("22*  ");
//				         String answer = restTemplate.postForObject(bundle.getString("convertorServiceUrl"), entity, String.class);
//				         System.out.println(answer);
//				    
				         logger.info("3*  ");
				       
				         logger.info("4*  ");
                      
				         
						
						logger.info(" outputFilename docx " +bundle.getString("doc_loc")+outputFilename+".docx");
						logger.info(" outputFilename pdf " +bundle.getString("doc_loc")+outputFilename+".pdf");
//						logger.info("bundle.getString(\"doc_loc_ip\")+\"Attachment/\"+outputFilename+\".pdf\" = "+bundle.getString("doc_loc_ip")+"Attachment/"+outputFilename+".pdf");
						//doc_loc
						try {
						String pdfres= ConvertDocxToPDF.getWordToPdfData(bundle.getString("doc_loc")+outputFilename+".docx", bundle.getString("doc_loc")+outputFilename+".pdf");
						logger.info("ConvertDocxToPDF.getWordToPdfData pdfres = "+pdfres);
						}catch (Exception e) {
							logger.info("exc in getWordToPdfData = "+e);
							// TODO: handle exception
						}
						docxurl= bundle.getString("doc_loc_ip")+"Attachment/"+outputFilename+".pdf";	
						logger.info("docxurl = "+docxurl);
	                   //	DocxToPdfConvertor.replaceParamsInDocxFile( sfobj, templateFileVO.getTemaplatePath(), bundle.getString("doc_loc")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_DOCX, data);
	                   //	RtfToPdfConvertor.convertDocxFileToPDF(bundle.getString("doc_loc")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_DOCX, outputPdfPath, data, bundle.getString("doc_loc"));
	
	
						 /* Docx to PDF using doc4j
//				         Docx4jSampleForReplaceTable objDocx4jSampleForReplaceTable = new Docx4jSampleForReplaceTable();
//				 		WordprocessingMLPackage template = objDocx4jSampleForReplaceTable.getTemplate(filename);
				         WordprocessingMLPackage wordMLPackage =readDocxFile(outputDocxPath);
				         logger.info(" DocxToPdfConvertor1  pdfcovert 345= " + outputDocxPath);
				         PdfSettings pdfSettings = new PdfSettings();
				         org.docx4j.convert.out.pdf.viaXSLFO.Conversion.log.setLevel(Level.OFF);
				         ///home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/
				         int o= outputDocxPath.lastIndexOf("/");
					       String generatedfile = outputDocxPath.substring(o+1,outputDocxPath.length());
					       logger.info(" DocxToPdfConvertor1 generatedfile= " + generatedfile);
				         OutputStream out = new FileOutputStream(new File(bundle.getString("doc_loc")+outputFilename+".pdf"));
				     
				         PdfConversion converter = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(
				        		 wordMLPackage);
				         logger.info(" DocxToPdfConvertor1 outputFilename= " +outputFilename);
				         converter.output(out, pdfSettings);
				         logger.info(" DocxToPdfConvertor1 generatedfile2= " );
				  String   	url1 = bundle.getString("doc_loc_ip")+outputFilename+".pdf";
				         logger.info(" DocxToPdfConvertor1 genurl= " + url);
				         logger.info(" bundle.getString(\"doc_loc\")+outputFilename+IConstants.EXTENSION_PDF= " + bundle.getString("doc_loc")+outputFilename+".pdf");
				       
				         */
				         //url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_PDF;
//				         end
				         
//				         PdfSettings pdfSettings = new PdfSettings();
//				         org.docx4j.convert.out.pdf.viaXSLFO.Conversion.log.setLevel(Level.OFF);
//				         OutputStream out = new FileOutputStream(new File(
//				         "E:\\HelloWorld.pdf"));
//				         PdfConversion converter = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(
//				         template);
//				         converter.output(out, pdfSettings);
				         logger.info("3*  ");
//				         logger.info(answer);
				         logger.info("4*  ");
                     
                       
						logger.info("url "+url);
						
						
			/*			JSONArray promotionarr= new JSONArray(promotion);
						JSONArray urlarr = new JSONArray();
						for(int i=0;i<promotionarr.length(); i++) {
							try {
							String Temlatename=promotionarr.getString(i);
							String filename=Temlatename+".docx";
							logger.info("Temlatename "+Temlatename);
						String status=	Serverconnection.servconnect(Temlatename, filename);
						logger.info("status "+status);
						if(status.equals("success")){							
							TemplateFileVO templateFileVO1 = Utility.getDataByTemplateNameFromFile(Temlatename);
						String outputFilename1 = getFilename(Temlatename, obj);
							String outputPdfPath1 = bundle.getString("doc_loc")+outputFilename1+IConstants.PERIOD+IConstants.EXTENSION_PDF;
							String outputDocxPath1 = bundle.getString("doc_loc")+outputFilename1+IConstants.PERIOD+IConstants.EXTENSION_DOCX;
						//	url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_PDF;
					  String   url1 = bundle.getString("doc_loc_ip")+outputFilename1+IConstants.PERIOD+IConstants.EXTENSION_PDF;
						logger.info("templateFileVO1.getTemaplatePath() "+templateFileVO1.getTemaplatePath());
					  DocxToPdfConvertor.replaceParamsInDocxFile(sfobj, templateFileVO1.getTemaplatePath(), outputDocxPath1, data);
						
				   //		RestTemplate restTemplate = new RestTemplate();
				         logger.info("outputPdfPath1 "+outputPdfPath1);
				         logger.info("outputDocxPath1 "+outputDocxPath1);
				         logger.info("url"+url);
				         
				         JSONObject jsonObj1 = new JSONObject();
				         jsonObj1.put("applicationId", applicationId);
				         jsonObj1.put("secretKey", secretKey);
				         jsonObj1.put("inputDocxFilePath", outputDocxPath1);
				         jsonObj1.put("outputPdfFilePath", outputPdfPath1);
				         
				         HttpHeaders headers1 = new HttpHeaders();
				         headers1.setContentType(MediaType.APPLICATION_JSON);	
				         
				         HttpEntity<String> entity1 = new HttpEntity<String>(jsonObj1.toString(),headers1);
				         String answer1 = restTemplate.postForObject(bundle.getString("convertorServiceUrl"), entity1, String.class);
				         System.out.println(answer1);
				        logger.info(answer1);

				         logger.info("3*  ");
				         System.out.println(answer1);
				         logger.info("4*  ");
                     
						url=url+","+url1;
						
						}
						
						}catch(Exception e) {
							
						}
							}
						*/
						
						
						
						
						
					}catch (Exception e) {
						e.printStackTrace();
					logger.info("exc in docgenservice = "+e);
				//	return "error "+e.getMessage();
				}
				return docxurl;
			}
	
	
	
	
	
	private byte[] addLogo(byte[] modifiedFileArr,  String logourl, int logotableno, int width, int height) {
		
		//byte[] modifiedFileArr=null;
		 try {
			 
			 String logoimagename = "logoimage_"+logotableno+".png";
				String logoimagenamePath = bundle.getString("qr_loc")+logoimagename;
				logger.info(" logoimagename 1  "+logoimagename+"  logoimagenamePath  "+logoimagenamePath);				
			String status =	saveImage( logourl,  logoimagenamePath) ;
          if(status.equalsIgnoreCase("success")) {
			modifiedFileArr = AddTableDataInTemplate.addLogoInTablearabic(modifiedFileArr, logoimagenamePath, logotableno,  width,  height);
             }
 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			return modifiedFileArr;

	}
	
	
	
	
	private byte[] addfloorplan(byte[] modifiedFileArr,  String floorplanarray,  int width, int height) {
		
		//byte[] modifiedFileArr=null;
		 try {
			 
			 InputStream is = new ByteArrayInputStream(modifiedFileArr);
				
				XWPFDocument doc = new XWPFDocument(is);
				XWPFTable table ;
				int nooftable= doc.getTables().size();
				System.out.println("nooftable  "+nooftable);	 			 
			  JSONArray arr = new JSONArray(floorplanarray);
			 
			int  tableno =nooftable-arr.length();
			tableno=tableno+1;
			
			// for(int i=0;i<arr.length(); i++) {
				// String floorimageurl=arr.getString(i);
				 
				 
			 //  hardcode table no --------   String html = arr.getString(i);
				 String html = arr.getString(0);
				  Pattern p = Pattern.compile("href=\"(.*?)\"");
				  Matcher m = p.matcher(html);
				  String url = null;
				  if (m.find()) {
				      url = m.group(1); // this variable should contain the link URL
				  }
				  logger.info("imagepdf url -- "+url);
				  System.out.println("imagepdf url -- "+url);
					String pfdPath = bundle.getString("qr_loc")+url.substring(url.lastIndexOf("/")+1);
				 System.out.println("pfdPath  "+pfdPath);
				 logger.info("pfdPath  "+pfdPath);
				String status1 = "";

				 
				//	String urltopdfderv= "http://35.188.238.145:8080/DocTigerSF/pdffromurl?pdfurl="+url+"&destinationFile="+pfdPath	;
			//		logger.info("pdffromurl urll --"+urltopdfderv );
			//		System.out.println("pdffromurl urll --"+urltopdfderv);
   //					 String status1= ApiCall.callGetApi(urltopdfderv);
   //					 logger.info("status1 "+status1);
//					InputStream isNew = null;
//					OutputStream osNew = null;
//					URL urlNew = null;
//					HttpURLConnection conn  = null;
//					try {
//						urlNew = new URL(url);
//					  conn  = (HttpURLConnection) urlNew.openConnection();
//					//out.println("1");
//					 conn.connect();
//					 if(conn.getResponseCode() == 200) {
//					is = conn.getInputStream();
//					//out.println("2");
//					//out.println("3");
//					osNew = new FileOutputStream(pfdPath);
//					//out.println("4");
//
//					byte[] b = new byte[2048];
//					int length;
//
//					while ((length = isNew.read(b)) != -1) {
//						osNew.write(b, 0, length);
//					}
//					//out.println();
				 
				 
 
				 

//					status1 = "success";
//					
//					}else {
//						status1 = "false";	
//					}
//					}catch(Exception e) {
//						e.printStackTrace();
//						System.out.println("error "+e.getMessage());
//						 
//					}finally {
//						osNew.close();
//						isNew.close();
//					
//					}
				
				 RestTemplate restTemplate = new RestTemplate();
		         
		         
		         JSONObject jsonObj = new JSONObject();
		         jsonObj.put("inputDocxFilePath", url);
		         jsonObj.put("outputPdfFilePath", pfdPath);
		         
		         HttpHeaders headers = new HttpHeaders();
		         headers.setContentType(MediaType.APPLICATION_JSON);	
		         
		         HttpEntity<String> entity = new HttpEntity<String>(jsonObj.toString(),headers);
		         String answer = restTemplate.postForObject(bundle.getString("saveurlfrompdf"), entity, String.class);
		         System.out.println(answer);
		    
		         logger.info("3*  ");
		         System.out.println(answer);
		         logger.info("4*  ");
                 				
				
				
				// status1=	 saveFileFromUrlWithJavaIO( pfdPath,  url);
               // System.out.print("status1 "+status1);
					 
					// if (status1.equalsIgnoreCase("success")) {
					 //  String floorimagename = "floorpmage_"+i+"_"+tableno+".png";
						String floorimagenamedir = bundle.getString("qr_loc");
						logger.info("  floorimagenamedir  "+floorimagenamedir);
						System.out.println("floorimagenamedir "+floorimagenamedir);
			String 	floorimagenamePath=  pdftoimageconverter( pfdPath,  floorimagenamedir);
				 System.out.println("floorimagenamePath "+floorimagenamePath);
					//String status =	saveImage( floorimageurl,  floorimagenamePath) ;
				 int[] tno= {7,9,14};
				 for(int j=0; j<tno.length; j++) {
					 if(!floorimagenamePath.equalsIgnoreCase("")) {
							modifiedFileArr = AddTableDataInTemplate.addcoverimg(modifiedFileArr, floorimagenamePath, tno[j],  width,  height);

							//hardcodetableno------	 modifiedFileArr = AddTableDataInTemplate.addcoverimg(modifiedFileArr, floorimagenamePath, tableno,  width,  height);
				             }
				 }
					 //}
				//hardcodetableno------	 tableno++;
			//hardcodetableno  ------  }
         
 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
			return modifiedFileArr;
	}
	
	
	
	
	
	
	
	
	
	
	
	private byte[] addCoverImage(byte[] modifiedFileArr,  String coverimageurl, int coverimagetableno, int width, int height) {
		
		//byte[] modifiedFileArr=null;
		 try {
			 
			 String coverimagename = "coverpmage_"+coverimagetableno+".png";
				String coverimagenamePath = bundle.getString("qr_loc")+coverimagename;
				logger.info(" coverimagename 1  "+coverimagename+"  coverimagenamePath  "+coverimagenamePath);				
			String status =	saveImage( coverimageurl,  coverimagenamePath) ;
          if(status.equalsIgnoreCase("success")) {
			modifiedFileArr = AddTableDataInTemplate.addcoverimg(modifiedFileArr, coverimagenamePath, coverimagetableno,  width,  height);
             }
 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
			return modifiedFileArr;

	}
	
	public static String saveImage(String imageUrl, String destinationFile) throws IOException {
		try {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
		return "success";
		}catch(Exception e) {
			return "error";
		}
		
	}

	public static String savepdffromurl(String pdfurl, String destinationFile) {
		
			
		try {
			logger.info("pdfurl "+pdfurl);
			logger.info("destinationFile  "+destinationFile);

//		URL url = new URL(pdfurl);
//		InputStream is = url.openStream();
//		OutputStream os = new FileOutputStream(destinationFile);
//
//		byte[] b = new byte[2048];
//		int length;
//
//		while ((length = is.read(b)) != -1) {
//			os.write(b, 0, length);
//		}
//
//		is.close();
//		os.close();
			String currentIP= bundleststic.getString("currentIp");
		String urltopdfderv= "http://"+currentIP+":8080/DocTigerSFCore/pdffromurl?pdfurl="+pdfurl+"&destinationFile="+destinationFile	;
	//	String urltopdfderv= "http://35.188.238.145:8080/DocTigerSF/pdffromurl?pdfurl="+pdfurl+"&destinationFile="+destinationFile	;

		logger.info("pdffromurl urll --"+urltopdfderv );
		System.out.println("pdffromurl urll --"+urltopdfderv);
	String status=	ApiCall.callGetApi(urltopdfderv);
			
//			URL url = new URL(pdfurl);
//			  File download = new File(destinationFile);
//			  FileUtils.copyURLToFile(url, download);		
//			
			  return status;
	//return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error" + e.getMessage();
		}
		
	}






	private String getFilename(String templateName, JSONObject obj){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss-SSS");
		Date date = new Date(); 
		String strDate= formatter.format(date);
		String RegId = "RegIdNotPassed";
		
		return templateName.replace(" ", "")+"_"+strDate;
	}

	private byte[] addQRCode(byte[] modifiedFileArr, int qrCodeTableNumber, DocGenDTO objDocGenDTO) throws FileNotFoundException, InvalidFormatException, IOException, XmlException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss-SSS");
		Date date = new Date(); 
		String strDate= formatter.format(date);
		String RegId = "RegIdNotPassed";
		if(objDocGenDTO.getRegId() != null) {
			RegId = objDocGenDTO.getRegId();
		}
		String NameOfDoc = "Assignment NOC";
		String myCodeText = "Seller Name : "+objDocGenDTO.getATTRIBUTE1()+"\nSR Type : "+objDocGenDTO.getATTRIBUTE111()+"\nReg ID : "+objDocGenDTO.getRegId()+"\nUnit Name : "+objDocGenDTO.getATTRIBUTE112()+"\nProject Name : "+objDocGenDTO.getATTRIBUTE113()+"\nName of Document : "+NameOfDoc+"\nDate and Time of Generation : "+strDate+"\nCustomer Name : "+objDocGenDTO.getATTRIBUTE114()+"\nBuyer Name : "+objDocGenDTO.getATTRIBUTE115()+"\nSource : "+objDocGenDTO.getATTRIBUTE116()+"\nUser ID : "+objDocGenDTO.getATTRIBUTE117();
		String filenameQR = RegId+"_"+objDocGenDTO.getQrCodeTableNumber()+"_"+strDate+".png";
		String filePath = bundle.getString("qr_loc")+filenameQR;
		objQRCode.generateQRCode(myCodeText, filePath);
		modifiedFileArr = AddTableDataInTemplate.addQRCodeInTable(modifiedFileArr, filePath, qrCodeTableNumber);
		return modifiedFileArr;
	}

	private byte[] addQRCode(String docxFilePath, int qrCodeTableNumber, JSONObject QRobj) throws FileNotFoundException, InvalidFormatException, IOException, XmlException{
		byte[] modifiedFileArr =null;
		String myCodeText="";
		 try {
				logger.info(" addQRCode 1");
				logger.info("docxFilePath"+ docxFilePath);
				logger.info("QRobj "+QRobj);

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss-SSS");
		Date date = new Date(); 
		String strDate= formatter.format(date);
		String RegId = "RegIdNotPassed";
		
		String NameOfDoc = "Assignment NOC";
		JSONArray param= QRobj.getJSONArray("Param");
		for(int i=0; i<param.length(); i++) {
			JSONObject oneparam= param.getJSONObject(i);
			String field=oneparam.getString("Field");
			String Field_value=oneparam.getString("Field_value");
				 myCodeText =myCodeText+" \n "+field+" : "+ Field_value;
		}
		
		logger.info(" myCodeText 1"+myCodeText);

		//String myCodeText = "Seller Name : "+objDocGenDTO.getATTRIBUTE1()+"\nSR Type : "+objDocGenDTO.getATTRIBUTE111()+"\nReg ID : "+objDocGenDTO.getRegId()+"\nUnit Name : "+objDocGenDTO.getATTRIBUTE112()+"\nProject Name : "+objDocGenDTO.getATTRIBUTE113()+"\nName of Document : "+NameOfDoc+"\nDate and Time of Generation : "+strDate+"\nCustomer Name : "+objDocGenDTO.getATTRIBUTE114()+"\nBuyer Name : "+objDocGenDTO.getATTRIBUTE115()+"\nSource : "+objDocGenDTO.getATTRIBUTE116()+"\nUser ID : "+objDocGenDTO.getATTRIBUTE117();
		String filenameQR = RegId+"_"+qrCodeTableNumber+"_"+strDate+".png";
		String filePath = bundle.getString("qr_loc")+filenameQR;
		logger.info(" filenameQR 1  "+filenameQR+"  filePath  "+filePath);

		objQRCode.generateQRCode(myCodeText, filePath);
		 modifiedFileArr = AddTableDataInTemplate.addQRCodeInTable(docxFilePath, filePath, qrCodeTableNumber);
		} catch (JSONException e) {
			logger.info("error addQRCode "+e.getMessage());
			e.printStackTrace();
		}
		return modifiedFileArr;
	}

	
	public String converEnglishxlsFileToPdf(JSONObject obj, TemplateFileVO templateFileVO) {
		logger.info("converEnglishDocxFileToPdf");
				// Build the data-model
				Map<String, Object> data = new HashMap<String, Object>();
				String url = null;
				try{

					
					String outputFilename = getFilename(templateFileVO.getTemaplateName(), obj);
					String outputPdfPath = bundle.getString("doc_loc")+getFilename(templateFileVO.getTemaplateName(), obj)+IConstants.PERIOD+IConstants.EXTENSION_PDF;


					DocxToPdfConvertor.convertxlsxFileToPDF(templateFileVO.getTemaplatePath(), outputPdfPath, data);

						//DocxToPdfConvertor.convertDocxFileToPDF(modifiedFileArr, outputPdfPath, data);
						url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_PDF;
						//url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_DOCX;
					
						url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_PDF;
						//url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_DOCX;
					
					return url;
				}catch (Exception e) {
					logger.info("error in converEnglishDocxFileToPdf"+ e.getMessage());
					e.printStackTrace();
					return "error "+e.getMessage();
				}finally {
					try {
						FileUtils.cleanDirectory(new File(bundle.getString("qr_loc")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
					} 
				}
				
			}

	public String pdftoimageconverter(String sourceDir, String destinationDir) {
		 String imgpath="";
		 try {
			 logger.info("in pdftoimageconverter");
		       // String sourceDir = "C:/Documents/04-Request-Headers.pdf"; // Pdf files are read from this folder
		        //String destinationDir = "C:/Documents/Converted_PdfFiles_to_Image/"; // converted images from pdf document are saved here

		        File sourceFile = new File(sourceDir);
		        File destinationFile = new File(destinationDir);
		        if (!destinationFile.exists()) {
		            destinationFile.mkdir();
		            logger.info("Folder Created -> "+ destinationFile.getAbsolutePath());
		        }
		        if (sourceFile.exists()) {
		            System.out.println("Images copied to Folder: "+ destinationFile.getName());             
		            PDDocument document = PDDocument.load(sourceDir);
		            List<PDPage> list =  document.getDocumentCatalog().getAllPages();
		            System.out.println("Total files to be converted -> "+ list.size());

		            String fileName = sourceFile.getName().replace(".pdf", "");             
		            int pageNumber = 1;
		            for (PDPage page : list) {
		                BufferedImage image = page.convertToImage();
		                 imgpath=destinationDir + fileName +"_"+ pageNumber +".png";
		                File outputfile = new File(destinationDir + fileName +"_"+ pageNumber +".png");
		                System.out.println("Image Created -> "+ outputfile.getName());
		                ImageIO.write(image, "png", outputfile);
		                pageNumber++;
		            }
		            document.close();
		            logger.info("Converted Images are saved at -> "+ destinationFile.getAbsolutePath());
		        } else {
		        	logger.error(sourceFile.getName() +" File not exists");
		        }

		    } catch (Exception e) {
	        	logger.info(e.getMessage());

		        e.printStackTrace();
		    }
	return imgpath;
	} 

	
	public static String saveFileFromUrlWithJavaIO(String fileName, String fileUrl)
			 throws MalformedURLException, IOException {
			 BufferedInputStream in = null;
			 FileOutputStream fout = null;
			 try {
			 in = new BufferedInputStream(new URL(fileUrl).openStream());
			 fout = new FileOutputStream(fileName);
			 
			byte data[] = new byte[1024];
			 int count;
			 while ((count = in.read(data, 0, 1024)) != -1) {
			 fout.write(data, 0, count);
			 }return "success";
			 
			 
			 } finally {
			 if (in != null)
			 in.close();
			 if (fout != null)
			 fout.close();
			 }
			 }
	
	private static WordprocessingMLPackage readDocxFile(String docxPath)
			throws FileNotFoundException, Docx4JException, Exception {
		Docx4jProperties.getProperties().setProperty("docx4j.Log4j.Configurator.disabled", "true");
		Log4jConfigurator.configure();
		org.docx4j.convert.out.pdf.viaXSLFO.Conversion.log.setLevel(Level.OFF);

		InputStream is = new FileInputStream(new File(docxPath));

		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(is);

		return wordMLPackage;
	}
	
	
	// convert to pdf new
	
	public void createpdfpio(String inputFile ,String outputFile) {
		try{
		//String inputFile="D:\\doctiger\\WelcomeLetter_25-Jun-2019_17-31-24-612.docx";
		// String outputFile="D:\\doctiger\\WelcomeLetter_25-Jun-2019_17-31-24-612.pdf";
		System.out.println("inputFile:" + inputFile + ",outputFile:"+ outputFile);
		FileInputStream in=new FileInputStream(inputFile);
		logger.info("1 :: inputFile"+inputFile);
		XWPFDocument document=new XWPFDocument(in);
		
		File outFile=new File(outputFile);
		logger.info("2:: "+outputFile);
		OutputStream out=new FileOutputStream(outFile);
		PdfOptions options=null;
		logger.info("3:: ");
		PdfConverter.getInstance().convert(document,out,options);
		logger.info("4 ");

		}catch(Exception e){
//		e.printStackTrace();
		   logger.info("exception in createpdfpio :: "+e);
		}

		}
		
}
