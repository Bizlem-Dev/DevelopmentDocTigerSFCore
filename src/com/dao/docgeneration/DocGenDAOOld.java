package com.dao.docgeneration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.doc.generation.SFDCDocumentGeneration;
import com.dto.DocGenDTO;
import com.htmltopdf.Converter;
import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfWriter;
import com.qrcode.QRCode;

import freemarker.template.Configuration;
import freemarker.template.Template;
import util.HtmlToPdfConvertor;

public class DocGenDAOOld {
	final static Logger logger = Logger.getLogger(DocGenDAOOld.class);
    ResourceBundle bundle = ResourceBundle.getBundle("config");
    QRCode objQRCode = new QRCode();
	public String Assignment_LOA_Completed_Project(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("Assignment_LOA_Completed_Project.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			/*data.put("Attribute1", "30/09/2017");
			data.put("Attribute2", "Vivek");
			data.put("Attribute3", "U");
			data.put("Attribute4", "Kumar");
			data.put("Attribute5", "Indian");
			data.put("Attribute6", "P12345OOO");
			data.put("Attribute7", "P12345OOO");
			data.put("Attribute8", "P12345OOO");
			data.put("Attribute9", "P12345OOO");
			data.put("Attribute10", "P12345OOO");
			data.put("Attribute11", "P12345OOO");
			data.put("Attribute12", "P12345OOO");
			data.put("Attribute13", "P12345OOO");
			data.put("Attribute14", "P12345OOO");
			data.put("Attribute15", "P12345OOO");
			data.put("Attribute19", "P12345OOO");
			data.put("Attribute20", "P12345OOO");
			data.put("Attribute21", "P12345OOO");
			data.put("Attribute22", "P12345OOO");
			data.put("Attribute23", "P12345OOO");
			data.put("Attribute24", "P12345OOO");
			data.put("Attribute25", "P12345OOO");*/
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			data.put("Attribute39", objDocGenDTO.getATTRIBUTE39());
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			data.put("Attribute41", objDocGenDTO.getATTRIBUTE41());
			data.put("Attribute42", objDocGenDTO.getATTRIBUTE42());
			data.put("Attribute43", objDocGenDTO.getATTRIBUTE43());
			data.put("Attribute44", objDocGenDTO.getATTRIBUTE44());
			data.put("Attribute45", objDocGenDTO.getATTRIBUTE45());
			data.put("Attribute46", objDocGenDTO.getATTRIBUTE46());
			data.put("Attribute47", objDocGenDTO.getATTRIBUTE47());
			data.put("Attribute48", objDocGenDTO.getATTRIBUTE48());
			data.put("Attribute49", objDocGenDTO.getATTRIBUTE49());
			data.put("Attribute50", objDocGenDTO.getATTRIBUTE50());
			data.put("Attribute51", objDocGenDTO.getATTRIBUTE51());
			data.put("Attribute52", objDocGenDTO.getATTRIBUTE52());
			data.put("Attribute53", objDocGenDTO.getATTRIBUTE53());
			data.put("Attribute54", objDocGenDTO.getATTRIBUTE54());
			data.put("Attribute55", objDocGenDTO.getATTRIBUTE55());
			data.put("Attribute56", objDocGenDTO.getATTRIBUTE56());
			data.put("Attribute57", objDocGenDTO.getATTRIBUTE57());
			data.put("Attribute58", objDocGenDTO.getATTRIBUTE58());
			data.put("Attribute59", objDocGenDTO.getATTRIBUTE59());
			data.put("Attribute60", objDocGenDTO.getATTRIBUTE60());
			data.put("Attribute61", objDocGenDTO.getATTRIBUTE61());
			data.put("Attribute62", objDocGenDTO.getATTRIBUTE62());
			data.put("Attribute63", objDocGenDTO.getATTRIBUTE63());
			data.put("Attribute64", objDocGenDTO.getATTRIBUTE64());
			data.put("Attribute65", objDocGenDTO.getATTRIBUTE65());
			data.put("Attribute66", objDocGenDTO.getATTRIBUTE66());
			
			logger.info("SFDCDocumentGeneration :: DocGeneration :: Registration ID :: "+objDocGenDTO.getRegId()+" :: ATTRIBUTE1 :: "+objDocGenDTO.getATTRIBUTE1());
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_Assignment_LOA_Completed_Project_"+strDate+".doc";
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_Assignment_LOA_Completed_Project_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_Assignment_LOA_Completed_Project_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		/* 	StyleSheet css = new StyleSheet();
		css.loadTagStyle("body", "font-family", "Verdana, \"Bitstream Vera Sans\", Geneva, sans-serif;");
		css.loadTagStyle("pre", "font-family", "Verdana, \"Bitstream Vera Sans\", Geneva, sans-serif;");
	
		 OutputStream file = new FileOutputStream(new File(bundle.getString("doc_loc")+filename));
	    Document document = new Document();
	    PdfWriter.getInstance(document, file);
	    
	    document.open();
	    HTMLWorker htmlWorker = new HTMLWorker(document,null,css);
	    htmlWorker.parse(new StringReader(string));
	    document.close();
	    file.close();*/
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	public String AssignmentLOA_offplanunits(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AssignmentLOA_offplanunits.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			/*data.put("Attribute1", "30/09/2017");
			data.put("Attribute2", "Vivek");
			data.put("Attribute3", "U");
			data.put("Attribute4", "Kumar");
			data.put("Attribute5", "Indian");
			data.put("Attribute6", "P12345OOO");
			data.put("Attribute7", "P12345OOO");
			data.put("Attribute8", "P12345OOO");
			data.put("Attribute9", "P12345OOO");
			data.put("Attribute10", "P12345OOO");
			data.put("Attribute11", "P12345OOO");
			data.put("Attribute12", "P12345OOO");
			data.put("Attribute13", "P12345OOO");
			data.put("Attribute14", "P12345OOO");
			data.put("Attribute15", "P12345OOO");
			data.put("Attribute19", "P12345OOO");
			data.put("Attribute20", "P12345OOO");
			data.put("Attribute21", "P12345OOO");
			data.put("Attribute22", "P12345OOO");
			data.put("Attribute23", "P12345OOO");
			data.put("Attribute24", "P12345OOO");
			data.put("Attribute25", "P12345OOO");*/
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			data.put("Attribute39", objDocGenDTO.getATTRIBUTE39());
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			data.put("Attribute41", objDocGenDTO.getATTRIBUTE41());
			data.put("Attribute42", objDocGenDTO.getATTRIBUTE42());
			data.put("Attribute43", objDocGenDTO.getATTRIBUTE43());
			data.put("Attribute44", objDocGenDTO.getATTRIBUTE44());
			data.put("Attribute45", objDocGenDTO.getATTRIBUTE45());
			data.put("Attribute46", objDocGenDTO.getATTRIBUTE46());
			data.put("Attribute47", objDocGenDTO.getATTRIBUTE47());
			data.put("Attribute48", objDocGenDTO.getATTRIBUTE48());
			data.put("Attribute49", objDocGenDTO.getATTRIBUTE49());
			data.put("Attribute50", objDocGenDTO.getATTRIBUTE50());
			data.put("Attribute51", objDocGenDTO.getATTRIBUTE51());
			data.put("Attribute52", objDocGenDTO.getATTRIBUTE52());
			data.put("Attribute53", objDocGenDTO.getATTRIBUTE53());
			data.put("Attribute54", objDocGenDTO.getATTRIBUTE54());
			data.put("Attribute55", objDocGenDTO.getATTRIBUTE55());
			data.put("Attribute56", objDocGenDTO.getATTRIBUTE56());
			data.put("Attribute57", objDocGenDTO.getATTRIBUTE57());
			data.put("Attribute58", objDocGenDTO.getATTRIBUTE58());
			data.put("Attribute59", objDocGenDTO.getATTRIBUTE59());
			data.put("Attribute60", objDocGenDTO.getATTRIBUTE60());
			data.put("Attribute61", objDocGenDTO.getATTRIBUTE61());
			data.put("Attribute62", objDocGenDTO.getATTRIBUTE62());
			data.put("Attribute63", objDocGenDTO.getATTRIBUTE63());
			data.put("Attribute64", objDocGenDTO.getATTRIBUTE64());
			data.put("Attribute65", objDocGenDTO.getATTRIBUTE65());
			data.put("Attribute66", objDocGenDTO.getATTRIBUTE66());
			
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_AssignmentLOA_offplanunits_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_AssignmentLOA_offplanunits_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_AssignmentLOA_offplanunits_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
	/*		StyleSheet css = new StyleSheet();
			css.loadTagStyle("table", "margin", "0");
			css.loadTagStyle("table", "padding", "0");
			css.loadTagStyle("table", "border-collapse", "collapse");
			css.loadTagStyle("table", "width", "100%");
			
			css.loadTagStyle("table", "border", "1");
			css.loadTagStyle("th", "border", "1");
			css.loadTagStyle("td", "border", "1");
			css.loadStyle("tableOverride", "border", "0");
//			css.loadTagStyle("pre", "font-family", "Verdana, \"Bitstream Vera Sans\", Geneva, sans-serif;");
		
			 OutputStream file = new FileOutputStream(new File(bundle.getString("doc_loc")+filename));
		    Document document = new Document();
		    PdfWriter.getInstance(document, file);
		    
		    document.open();
		    HTMLWorker htmlWorker = new HTMLWorker(document);
		    htmlWorker.setStyleSheet(css);
		    htmlWorker.parse(new StringReader(string));
		   // htmlWorker.parseToList(new StringReader(string), css);
		    document.close();
		    file.close();
			*/
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}

	public String AssignmentLOA_PSDA_AKOYA(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AssignmentLOA_PSDA_AKOYA.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			data.put("Attribute39", objDocGenDTO.getATTRIBUTE39());
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			data.put("Attribute41", objDocGenDTO.getATTRIBUTE41());
			data.put("Attribute42", objDocGenDTO.getATTRIBUTE42());
			data.put("Attribute43", objDocGenDTO.getATTRIBUTE43());
			data.put("Attribute44", objDocGenDTO.getATTRIBUTE44());
			data.put("Attribute45", objDocGenDTO.getATTRIBUTE45());
			data.put("Attribute46", objDocGenDTO.getATTRIBUTE46());
			data.put("Attribute47", objDocGenDTO.getATTRIBUTE47());
			data.put("Attribute48", objDocGenDTO.getATTRIBUTE48());
			data.put("Attribute49", objDocGenDTO.getATTRIBUTE49());
			data.put("Attribute50", objDocGenDTO.getATTRIBUTE50());
			data.put("Attribute51", objDocGenDTO.getATTRIBUTE51());
			data.put("Attribute52", objDocGenDTO.getATTRIBUTE52());
			data.put("Attribute53", objDocGenDTO.getATTRIBUTE53());
			data.put("Attribute54", objDocGenDTO.getATTRIBUTE54());
			data.put("Attribute55", objDocGenDTO.getATTRIBUTE55());
			data.put("Attribute56", objDocGenDTO.getATTRIBUTE56());
			data.put("Attribute57", objDocGenDTO.getATTRIBUTE57());
			data.put("Attribute58", objDocGenDTO.getATTRIBUTE58());
			data.put("Attribute59", objDocGenDTO.getATTRIBUTE59());
			data.put("Attribute60", objDocGenDTO.getATTRIBUTE60());
			data.put("Attribute61", objDocGenDTO.getATTRIBUTE61());
			data.put("Attribute62", objDocGenDTO.getATTRIBUTE62());
			data.put("Attribute63", objDocGenDTO.getATTRIBUTE63());
			data.put("Attribute64", objDocGenDTO.getATTRIBUTE64());
			data.put("Attribute65", objDocGenDTO.getATTRIBUTE65());
			Date date1 = new Date();  
		    SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
		    String strDate1= formatter1.format(date1);
			data.put("Attribute66", strDate1);
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			
			String filename = RegId+"_AssignmentLOA_PSDA_AKOYA_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_AssignmentLOA_PSDA_AKOYA_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_AssignmentLOA_PSDA_AKOYA_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	
	public String AssignmentLOA_Completed_AbuDhabi(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AssignmentLOA_Completed_AbuDhabi.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			data.put("Attribute39", objDocGenDTO.getATTRIBUTE39());
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			data.put("Attribute41", objDocGenDTO.getATTRIBUTE41());
			data.put("Attribute42", objDocGenDTO.getATTRIBUTE42());
			data.put("Attribute43", objDocGenDTO.getATTRIBUTE43());
			data.put("Attribute44", objDocGenDTO.getATTRIBUTE44());
			data.put("Attribute45", objDocGenDTO.getATTRIBUTE45());
			data.put("Attribute46", objDocGenDTO.getATTRIBUTE46());
			data.put("Attribute47", objDocGenDTO.getATTRIBUTE47());
			data.put("Attribute48", objDocGenDTO.getATTRIBUTE48());
			data.put("Attribute49", objDocGenDTO.getATTRIBUTE49());
			data.put("Attribute50", objDocGenDTO.getATTRIBUTE50());
			data.put("Attribute51", objDocGenDTO.getATTRIBUTE51());
			data.put("Attribute52", objDocGenDTO.getATTRIBUTE52());
			data.put("Attribute53", objDocGenDTO.getATTRIBUTE53());
			data.put("Attribute54", objDocGenDTO.getATTRIBUTE54());
			data.put("Attribute55", objDocGenDTO.getATTRIBUTE55());
			data.put("Attribute56", objDocGenDTO.getATTRIBUTE56());
			data.put("Attribute57", objDocGenDTO.getATTRIBUTE57());
			data.put("Attribute58", objDocGenDTO.getATTRIBUTE58());
			data.put("Attribute59", objDocGenDTO.getATTRIBUTE59());
			data.put("Attribute60", objDocGenDTO.getATTRIBUTE60());
			data.put("Attribute61", objDocGenDTO.getATTRIBUTE61());
			data.put("Attribute62", objDocGenDTO.getATTRIBUTE62());
			data.put("Attribute63", objDocGenDTO.getATTRIBUTE63());
			data.put("Attribute64", objDocGenDTO.getATTRIBUTE64());
			data.put("Attribute65", objDocGenDTO.getATTRIBUTE65());
			
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_AssignmentLOA_Completed_AbuDhabi_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_AssignmentLOA_Completed_AbuDhabi_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_AssignmentLOA_Completed_AbuDhabi_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}

	public String AssignmentNOC_Completed(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AssignmentNOC_Completed.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			Date date1 = new Date();  
		    SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
		    String strDate1= formatter1.format(date1);
			data.put("date", strDate1);
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			data.put("Attribute39", objDocGenDTO.getATTRIBUTE39());
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			data.put("Attribute41", objDocGenDTO.getATTRIBUTE41());
			data.put("Attribute42", objDocGenDTO.getATTRIBUTE42());
			data.put("Attribute43", objDocGenDTO.getATTRIBUTE43());
			data.put("Attribute44", objDocGenDTO.getATTRIBUTE44());
			data.put("Attribute45", objDocGenDTO.getATTRIBUTE45());
			data.put("Attribute46", objDocGenDTO.getATTRIBUTE46());
			data.put("Attribute47", objDocGenDTO.getATTRIBUTE47());
			data.put("Attribute48", objDocGenDTO.getATTRIBUTE48());
			data.put("Attribute49", objDocGenDTO.getATTRIBUTE49());
			data.put("Attribute50", objDocGenDTO.getATTRIBUTE50());
			data.put("Attribute51", objDocGenDTO.getATTRIBUTE51());
			data.put("Attribute52", objDocGenDTO.getATTRIBUTE52());
			data.put("Attribute53", objDocGenDTO.getATTRIBUTE53());
			data.put("Attribute54", objDocGenDTO.getATTRIBUTE54());
			
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
		    //String NameOfDoc = RegId+"_AssignmentNOC_Completed_"+strDate;
		    String NameOfDoc = "Assignment NOC";
		    String myCodeText = "Seller Name : "+objDocGenDTO.getATTRIBUTE1()+"\nSR Type : "+objDocGenDTO.getATTRIBUTE111()+"\nReg ID : "+objDocGenDTO.getRegId()+"\nUnit Name : "+objDocGenDTO.getATTRIBUTE112()+"\nProject Name : "+objDocGenDTO.getATTRIBUTE113()+"\nName of Document : "+NameOfDoc+"\nDate and Time of Generation : "+strDate+"\nCustomer Name : "+objDocGenDTO.getATTRIBUTE114()+"\nBuyer Name : "+objDocGenDTO.getATTRIBUTE115()+"\nSource : "+objDocGenDTO.getATTRIBUTE116()+"\nUser ID : "+objDocGenDTO.getATTRIBUTE117();
		    String filenameQR = RegId+"_AssignmentNOC_Completed_"+strDate+".png";
		    String filePath = bundle.getString("qr_loc")+filenameQR;
		    objQRCode.generateQRCode(myCodeText, filePath);
			data.put("qrIp", bundle.getString("qr_loc_ip")+filenameQR);
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			
//			String filename = RegId+"_AssignmentNOC_Completed_"+strDate+".doc";
//			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
//			template.process(data, file);
//			file.flush();
//			file.close();
			String filenameP = RegId+"_AssignmentNOC_Completed_"+strDate+".pdf";
			String htmlContent= HtmlToPdfConvertor.processHtmlStringBeforePdfGeneration(string);
			HtmlToPdfConvertor.exportToPdfBox(htmlContent, bundle.getString("doc_loc")+filenameP);
			url = bundle.getString("doc_loc_ip")+filenameP;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	public String AssignmentLOA_Qatar(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AssignmentLOA_Qatar.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			data.put("Attribute39", objDocGenDTO.getATTRIBUTE39());
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			data.put("Attribute41", objDocGenDTO.getATTRIBUTE41());
			data.put("Attribute42", objDocGenDTO.getATTRIBUTE42());
			data.put("Attribute43", objDocGenDTO.getATTRIBUTE43());
			data.put("Attribute44", objDocGenDTO.getATTRIBUTE44());
			data.put("Attribute45", objDocGenDTO.getATTRIBUTE45());
			data.put("Attribute46", objDocGenDTO.getATTRIBUTE46());
			data.put("Attribute47", objDocGenDTO.getATTRIBUTE47());
			data.put("Attribute48", objDocGenDTO.getATTRIBUTE48());
			data.put("Attribute49", objDocGenDTO.getATTRIBUTE49());
			data.put("Attribute50", objDocGenDTO.getATTRIBUTE50());
			data.put("Attribute51", objDocGenDTO.getATTRIBUTE51());
			data.put("Attribute52", objDocGenDTO.getATTRIBUTE52());
			data.put("Attribute53", objDocGenDTO.getATTRIBUTE53());
			data.put("Attribute54", objDocGenDTO.getATTRIBUTE54());
			data.put("Attribute55", objDocGenDTO.getATTRIBUTE55());
			data.put("Attribute56", objDocGenDTO.getATTRIBUTE56());
			data.put("Attribute57", objDocGenDTO.getATTRIBUTE57());
			data.put("Attribute58", objDocGenDTO.getATTRIBUTE58());
			data.put("Attribute59", objDocGenDTO.getATTRIBUTE59());
			
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_AssignmentLOA_Qatar_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_AssignmentLOA_Qatar_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_AssignmentLOA_Qatar_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	public String AssignmentNOC_Offplan(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AssignmentNOC_Offplan.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			data.put("Attribute39", objDocGenDTO.getATTRIBUTE39());
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			data.put("Attribute41", objDocGenDTO.getATTRIBUTE41());
			data.put("Attribute42", objDocGenDTO.getATTRIBUTE42());
			data.put("Attribute43", objDocGenDTO.getATTRIBUTE43());
			data.put("Attribute44", objDocGenDTO.getATTRIBUTE44());
			data.put("Attribute45", objDocGenDTO.getATTRIBUTE45());
			data.put("Attribute46", objDocGenDTO.getATTRIBUTE46());
			data.put("Attribute47", objDocGenDTO.getATTRIBUTE47());
			data.put("Attribute48", objDocGenDTO.getATTRIBUTE48());
			data.put("Attribute49", objDocGenDTO.getATTRIBUTE49());
			data.put("Attribute50", objDocGenDTO.getATTRIBUTE50());
			data.put("Attribute51", objDocGenDTO.getATTRIBUTE51());
			data.put("Attribute52", objDocGenDTO.getATTRIBUTE52());
			data.put("Attribute53", objDocGenDTO.getATTRIBUTE53());
			data.put("Attribute54", objDocGenDTO.getATTRIBUTE54());
			data.put("Attribute55", objDocGenDTO.getATTRIBUTE55());
			data.put("Attribute56", objDocGenDTO.getATTRIBUTE56());
			data.put("Attribute57", objDocGenDTO.getATTRIBUTE57());
			data.put("Attribute58", objDocGenDTO.getATTRIBUTE58());
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			//String NameOfDoc = RegId+"_AssignmentNOC_Offplan_"+strDate;
			String NameOfDoc = "Assignment NOC";
			String myCodeText = "Seller Name : "+objDocGenDTO.getATTRIBUTE1()+"\nSR Type : "+objDocGenDTO.getATTRIBUTE111()+"\nReg ID : "+objDocGenDTO.getRegId()+"\nUnit Name : "+objDocGenDTO.getATTRIBUTE112()+"\nProject Name : "+objDocGenDTO.getATTRIBUTE113()+"\nName of Document : "+NameOfDoc+"\nDate and Time of Generation : "+strDate+"\nCustomer Name : "+objDocGenDTO.getATTRIBUTE114()+"\nBuyer Name : "+objDocGenDTO.getATTRIBUTE115()+"\nSource : "+objDocGenDTO.getATTRIBUTE116()+"\nUser ID : "+objDocGenDTO.getATTRIBUTE117();
		    String filenameQR = RegId+"_AssignmentNOC_Offplan_"+strDate+".png";
		    String filePath = bundle.getString("qr_loc")+filenameQR;
		    objQRCode.generateQRCode(myCodeText, filePath);
			data.put("qrIp", bundle.getString("qr_loc_ip")+filenameQR);
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			
		    
//			String filename = RegId+"_AssignmentNOC_Offplan_"+strDate+".doc";
//			url = bundle.getString("doc_loc_ip")+filename;
//			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
//			template.process(data, file);
//			file.flush();
//			file.close();
		    String filenameP = RegId+"_AssignmentNOC_Offplan_"+strDate+".pdf";
			String htmlContent= HtmlToPdfConvertor.processHtmlStringBeforePdfGeneration(string);
			HtmlToPdfConvertor.exportToPdfBox(htmlContent, bundle.getString("doc_loc")+filenameP);
			url = bundle.getString("doc_loc_ip")+filenameP;
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	public String AssignmentNOC_Organization(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AssignmentNOC_Organization.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			Date date1 = new Date();  
		    SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
		    String strDate1= formatter1.format(date1);
			data.put("date", strDate1);
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			data.put("Attribute39", objDocGenDTO.getATTRIBUTE39());
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			data.put("Attribute41", objDocGenDTO.getATTRIBUTE41());
			data.put("Attribute42", objDocGenDTO.getATTRIBUTE42());
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
		    //String NameOfDoc = RegId+"_AssignmentNOC_Organization_"+strDate;
		    String NameOfDoc = "Assignment NOC";
		    String myCodeText = "Seller Name : "+objDocGenDTO.getATTRIBUTE1()+"\nSR Type : "+objDocGenDTO.getATTRIBUTE111()+"\nReg ID : "+objDocGenDTO.getRegId()+"\nUnit Name : "+objDocGenDTO.getATTRIBUTE112()+"\nProject Name : "+objDocGenDTO.getATTRIBUTE113()+"\nName of Document : "+NameOfDoc+"\nDate and Time of Generation : "+strDate+"\nCustomer Name : "+objDocGenDTO.getATTRIBUTE114()+"\nBuyer Name : "+objDocGenDTO.getATTRIBUTE115()+"\nSource : "+objDocGenDTO.getATTRIBUTE116()+"\nUser ID : "+objDocGenDTO.getATTRIBUTE117();
		    String filenameQR = RegId+"_AssignmentNOC_Organization_"+strDate+".png";
		    String filePath = bundle.getString("qr_loc")+filenameQR;
		    objQRCode.generateQRCode(myCodeText, filePath);
			data.put("qrIp", bundle.getString("qr_loc_ip")+filenameQR);
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			
//			String filename = RegId+"_AssignmentNOC_Organization_"+strDate+".doc";
//			url = bundle.getString("doc_loc_ip")+filename;
//			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
//			template.process(data, file);
//			file.flush();
//			file.close();
		    
		    String filenameP = RegId+"_AssignmentNOC_Organization_"+strDate+".pdf";
		    String htmlContent= HtmlToPdfConvertor.processHtmlStringBeforePdfGeneration(string);
		    HtmlToPdfConvertor.exportToPdfBox(htmlContent, bundle.getString("doc_loc")+filenameP);
		    url = bundle.getString("doc_loc_ip")+filenameP;
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}

	public String CaseSummary(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("CaseSummary.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
						
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_CaseSummary_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_CaseSummary_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_CaseSummary_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	public String LienletterWith_Addendum(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("LienletterWith_Addendum.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_LienletterWith_Addendum_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_LienletterWith_Addendum_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_LienletterWith_Addendum_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}

	public String AOPT_Payment_Plan_Addendum(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AOPT_Payment_Plan_Addendum.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			
			String start = "";
			String end = "";
			String main = "";
			String commaBookingName = "";
			for(int i=0;i< objDocGenDTO.getATTRIBUTEARR1().length;i++) {
				if(i==0) {
					commaBookingName = objDocGenDTO.getATTRIBUTEARR1()[i].getAttribute1();
				}else {
					commaBookingName = commaBookingName + "," +objDocGenDTO.getATTRIBUTEARR1()[i].getAttribute1();
				}
				 start = "<h3><b><text style='margin-left: 41px;'></text>Unit Number&#58;</b><font style='font-weight: 100; font-size: large'>&nbsp;&nbsp;"+objDocGenDTO.getATTRIBUTEARR1()[i].getAttribute1()+"</font></h3><table border='1' style='text-align: center; font-weight: bold;width:100%'><tr style='background-color: #9ABD5A;'><td style='width: 80px;'>No.</td><td style='width: 150px;'>Installment</td><td style='width: 300px;'>Milestone Event</td><td style='width: 160px;'>Payment Date</td><td style='width: 160px;'>Percent Value</td></tr>";
				String tr = "";
				
				for(int j=0;j<objDocGenDTO.getATTRIBUTEARR1()[i].getAttribute2().length;j++) {
					int k = j+1;
					tr = tr + "<tr><td>"+k+"</td><td>"+objDocGenDTO.getATTRIBUTEARR1()[i].getAttribute2()[j].getAttribute1()+"</td><td>"+objDocGenDTO.getATTRIBUTEARR1()[i].getAttribute2()[j].getAttribute2()+"</td><td>"+objDocGenDTO.getATTRIBUTEARR1()[i].getAttribute2()[j].getAttribute3()+"</td><td>"+objDocGenDTO.getATTRIBUTEARR1()[i].getAttribute2()[j].getAttribute4()+"</td></tr>";	
					
				}
				 end = "</table>";
				 main = main + (start+tr+end);
			}
			
			data.put("AttributeArr9", main);
			data.put("AttributeArr8", commaBookingName);    
	                

			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString().trim();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_AOPT_Payment_Plan_Addendum_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_AOPT_Payment_Plan_Addendum_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_AOPT_Payment_Plan_Addendum_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}

	public String UndertakingChangeNameNationality(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("UndertakingChangeNameNationality.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			if(objDocGenDTO.getATTRIBUTE1().equals("")) {
			data.put("Attribute1", "____________________");
			}else {
				data.put("Attribute1", "["+objDocGenDTO.getATTRIBUTE1()+"]");
			}
			if(objDocGenDTO.getATTRIBUTE2().equals("")) {
			data.put("Attribute2", "____________________________");
			}else {
				data.put("Attribute2", "["+objDocGenDTO.getATTRIBUTE2()+"]");
			}
			if(objDocGenDTO.getATTRIBUTE3().equals("")) {
			data.put("Attribute3", "_______________");
			}else {
				data.put("Attribute3", "["+objDocGenDTO.getATTRIBUTE3()+"]");
			}
			if(objDocGenDTO.getATTRIBUTE4().equals("")) {
			data.put("Attribute4", "____________________");
			}else {
				data.put("Attribute4", "["+objDocGenDTO.getATTRIBUTE4()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE5().equals("")) {
			data.put("Attribute5", "_______________________________");
			}else {
				data.put("Attribute5", "["+objDocGenDTO.getATTRIBUTE5()+"]");
			}
			if(objDocGenDTO.getATTRIBUTE6().equals("")) {
			data.put("Attribute6", "____________________");
			}else {
				data.put("Attribute6", "["+objDocGenDTO.getATTRIBUTE6()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE7().equals("") & objDocGenDTO.getATTRIBUTE8().equals("")
					& objDocGenDTO.getATTRIBUTE8().equals("") & objDocGenDTO.getATTRIBUTE10().equals("")) {
			data.put("Attribute7", "___________________________________");
			data.put("Attribute8", "");
			data.put("Attribute9", "");
			data.put("Attribute10", "");
			}else {
				data.put("Attribute7", "["+objDocGenDTO.getATTRIBUTE7());
				data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
				data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
				data.put("Attribute10", objDocGenDTO.getATTRIBUTE10()+"]");
			}
			
			if(objDocGenDTO.getATTRIBUTE11().equals("")) {
			data.put("Attribute11", "_________________________");
			}else {
				data.put("Attribute11", "["+objDocGenDTO.getATTRIBUTE11()+"]");
			}
			if(objDocGenDTO.getATTRIBUTE12().equals("")) {
			data.put("Attribute12", "____________________");
			}else {
				data.put("Attribute12", "["+objDocGenDTO.getATTRIBUTE12()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE13().equals("")) {
			data.put("Attribute13", "____________________");
			}else {
				data.put("Attribute13", "["+objDocGenDTO.getATTRIBUTE13()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE14().equals("")) {
			data.put("Attribute14", "____________________");
			}else {
				data.put("Attribute14", "["+objDocGenDTO.getATTRIBUTE14()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE15().equals("")) {
			data.put("Attribute15", "____________________");
			}else {
				data.put("Attribute15", "["+objDocGenDTO.getATTRIBUTE15()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE16().equals("")) {
			data.put("Attribute16", "____________________________");
			}else {
				data.put("Attribute16", "["+objDocGenDTO.getATTRIBUTE16()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE17().equals("")) {
			data.put("Attribute17", "__________________________________");
			}else {
				data.put("Attribute17", "["+objDocGenDTO.getATTRIBUTE17()+"]");
			}
			if(objDocGenDTO.getATTRIBUTE18().equals("")) {
			data.put("Attribute18", "____________________");
			}else {
				data.put("Attribute18", "["+objDocGenDTO.getATTRIBUTE18()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE19().equals("")) {
			data.put("Attribute19", "____________________");
			}else {
				data.put("Attribute19", "["+objDocGenDTO.getATTRIBUTE19()+"]");
			}
			if(objDocGenDTO.getATTRIBUTE20().equals("")) {
			data.put("Attribute20", "____________________");
			}else {
				data.put("Attribute20", "["+objDocGenDTO.getATTRIBUTE20()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE21().equals("")) {
			data.put("Attribute21", "____________________");
			}else {
				data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());	
			}
			if(objDocGenDTO.getATTRIBUTE22().equals("")) {
			data.put("Attribute22", "____________________");
			}else {
				data.put("Attribute22", "["+objDocGenDTO.getATTRIBUTE22()+"]");
			}
			
			data.put("Attribute23", "["+objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25()+"]");
			data.put("Attribute26", "["+objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28()+"]");
			data.put("Attribute29", "["+objDocGenDTO.getATTRIBUTE29()+"]");
			data.put("Attribute30", "["+objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32()+"]");
			data.put("Attribute33", "["+objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35()+"]");
			if(objDocGenDTO.getATTRIBUTE36().equals("")) {
			data.put("Attribute36", "____________________");
			}else {
				data.put("Attribute36", "["+objDocGenDTO.getATTRIBUTE36()+"]");
			}
			if(objDocGenDTO.getATTRIBUTE37().equals("")) {
			data.put("Attribute37", "____________________");
			}else {
				data.put("Attribute37", "["+objDocGenDTO.getATTRIBUTE37()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE38().equals("")) {
			data.put("Attribute38", "____________________");
			}else {
				data.put("Attribute38", "["+objDocGenDTO.getATTRIBUTE38()+"]");	
			}
			if(objDocGenDTO.getATTRIBUTE40().equalsIgnoreCase("name and nationality")) {
			data.put("Attribute39", "Name ["+objDocGenDTO.getATTRIBUTE33()+" "+objDocGenDTO.getATTRIBUTE34()+" "+objDocGenDTO.getATTRIBUTE35()+"] and Nationality ["+objDocGenDTO.getATTRIBUTE39()+ "]");
			}else if(objDocGenDTO.getATTRIBUTE40().equalsIgnoreCase("name")) {
				data.put("Attribute39", "Name ["+objDocGenDTO.getATTRIBUTE33()+" "+objDocGenDTO.getATTRIBUTE34()+" "+objDocGenDTO.getATTRIBUTE35() + "]");
			}else if(objDocGenDTO.getATTRIBUTE40().equalsIgnoreCase("nationality")) {
				data.put("Attribute39", "Nationality ["+objDocGenDTO.getATTRIBUTE39() + "]");
			}
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
//			data.put("Attribute41", objDocGenDTO.getATTRIBUTE33());
//			data.put("Attribute42", objDocGenDTO.getATTRIBUTE34());
//			data.put("Attribute43", objDocGenDTO.getATTRIBUTE35());
			
			//${Attribute40} <input type="text" style="width:290px" value="${Attribute39}"
			
			logger.info("Attribute1 :: "+objDocGenDTO.getATTRIBUTE1());
			logger.info("Attribute2 :: "+objDocGenDTO.getATTRIBUTE2());
			logger.info("Attribute3 :: "+objDocGenDTO.getATTRIBUTE3());
			logger.info("Attribute4 :: "+objDocGenDTO.getATTRIBUTE4());
			logger.info("Attribute5 :: "+objDocGenDTO.getATTRIBUTE5());
			logger.info("Attribute6 :: "+objDocGenDTO.getATTRIBUTE6());
			logger.info("Attribute7 :: "+objDocGenDTO.getATTRIBUTE7());
			logger.info("Attribute8 :: "+objDocGenDTO.getATTRIBUTE8());
			logger.info("Attribute9 :: "+objDocGenDTO.getATTRIBUTE9());
			logger.info("Attribute10 :: "+objDocGenDTO.getATTRIBUTE10());
			logger.info("Attribute11 :: "+objDocGenDTO.getATTRIBUTE11());
			logger.info("Attribute12 :: "+objDocGenDTO.getATTRIBUTE12());
			logger.info("Attribute13 :: "+objDocGenDTO.getATTRIBUTE13());
			logger.info("Attribute14 :: "+ objDocGenDTO.getATTRIBUTE14());
			logger.info("Attribute15 :: "+ objDocGenDTO.getATTRIBUTE15());
			logger.info("Attribute16 :: " + objDocGenDTO.getATTRIBUTE16());
			logger.info("Attribute17 :: " + objDocGenDTO.getATTRIBUTE17());
			logger.info("Attribute18 :: "+objDocGenDTO.getATTRIBUTE18());
			logger.info("Attribute19 :: " + objDocGenDTO.getATTRIBUTE19());
			logger.info("Attribute20 ::" + objDocGenDTO.getATTRIBUTE20());
			logger.info("Attribute39 ::" + objDocGenDTO.getATTRIBUTE39());
			logger.info("Attribute40 ::" + objDocGenDTO.getATTRIBUTE40());
//			logger.info("Attribute21", objDocGenDTO.getATTRIBUTE21());
//			logger.info("Attribute22", objDocGenDTO.getATTRIBUTE22());
//			logger.info("Attribute23", objDocGenDTO.getATTRIBUTE23());
//			logger.info("Attribute24", objDocGenDTO.getATTRIBUTE24());
//			logger.info("Attribute25", objDocGenDTO.getATTRIBUTE25());
//			logger.info("Attribute26", objDocGenDTO.getATTRIBUTE26());
//			logger.info("Attribute27", objDocGenDTO.getATTRIBUTE27());
//			logger.info("Attribute28", objDocGenDTO.getATTRIBUTE28());
//			logger.info("Attribute29", objDocGenDTO.getATTRIBUTE29());
//			logger.info("Attribute30", objDocGenDTO.getATTRIBUTE30());
//			logger.info("Attribute31", objDocGenDTO.getATTRIBUTE31());
//			logger.info("Attribute32", objDocGenDTO.getATTRIBUTE32());
//			logger.info("Attribute33", objDocGenDTO.getATTRIBUTE33());
//			logger.info("Attribute34", objDocGenDTO.getATTRIBUTE34());
//			logger.info("Attribute35", objDocGenDTO.getATTRIBUTE35());
//			logger.info("Attribute36", objDocGenDTO.getATTRIBUTE36());
//			logger.info("Attribute37", objDocGenDTO.getATTRIBUTE37());
//			logger.info("Attribute38", objDocGenDTO.getATTRIBUTE38());
//			logger.info("Attribute39", objDocGenDTO.getATTRIBUTE39());
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			
			String filename = RegId+"_UndertakingChangeNameNationality_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_UndertakingChangeNameNationality_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_UndertakingChangeNameNationality_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	
	public String UndertakingChangeNameNationality1(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("UndertakingChangeNameNationality1.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			
			if(objDocGenDTO.getATTRIBUTE40().equalsIgnoreCase("name and nationality")) {
			data.put("Attribute39", "Name <input type='text' style='width:290px' value='"+objDocGenDTO.getATTRIBUTE33()+" "+objDocGenDTO.getATTRIBUTE34()+" "+objDocGenDTO.getATTRIBUTE35()+"'/> and Nationality <input type='text' value='"+objDocGenDTO.getATTRIBUTE39()+"'/>");
			}else if(objDocGenDTO.getATTRIBUTE40().equalsIgnoreCase("name")) {
				data.put("Attribute39", "Name <input type='text' style='width:290px' value='"+objDocGenDTO.getATTRIBUTE33()+" "+objDocGenDTO.getATTRIBUTE34()+" "+objDocGenDTO.getATTRIBUTE35()+"'/>");
			}else if(objDocGenDTO.getATTRIBUTE40().equalsIgnoreCase("nationality")) {
				data.put("Attribute39", "Nationality <input type='text' value='"+objDocGenDTO.getATTRIBUTE39()+"'/>");
			}
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			//${Attribute40} <input type="text" style="width:290px" value="${Attribute39}"
			logger.info("RegId :: "+objDocGenDTO.getRegId());
			logger.info("Attribute1 :: "+objDocGenDTO.getATTRIBUTE1());
			logger.info("Attribute2 :: "+objDocGenDTO.getATTRIBUTE2());
			logger.info("Attribute3 :: "+objDocGenDTO.getATTRIBUTE3());
			logger.info("Attribute4 :: "+objDocGenDTO.getATTRIBUTE4());
			logger.info("Attribute5 :: "+objDocGenDTO.getATTRIBUTE5());
			logger.info("Attribute6 :: "+objDocGenDTO.getATTRIBUTE6());
			logger.info("Attribute7 :: "+objDocGenDTO.getATTRIBUTE7());
			logger.info("Attribute8 :: "+objDocGenDTO.getATTRIBUTE8());
			logger.info("Attribute9 :: "+objDocGenDTO.getATTRIBUTE9());
			logger.info("Attribute10 :: "+objDocGenDTO.getATTRIBUTE10());
			logger.info("Attribute11 :: "+objDocGenDTO.getATTRIBUTE11());
			logger.info("Attribute12 :: "+objDocGenDTO.getATTRIBUTE12());
			logger.info("Attribute13 :: "+objDocGenDTO.getATTRIBUTE13());
			logger.info("Attribute14 :: "+ objDocGenDTO.getATTRIBUTE14());
			logger.info("Attribute15 :: "+ objDocGenDTO.getATTRIBUTE15());
			logger.info("Attribute16 :: " + objDocGenDTO.getATTRIBUTE16());
			logger.info("Attribute17 :: " + objDocGenDTO.getATTRIBUTE17());
			logger.info("Attribute18 :: "+objDocGenDTO.getATTRIBUTE18());
			logger.info("Attribute19 :: " + objDocGenDTO.getATTRIBUTE19());
			logger.info("Attribute20 ::" + objDocGenDTO.getATTRIBUTE20());
			logger.info("Attribute40 ::" + objDocGenDTO.getATTRIBUTE40());
//			logger.info("Attribute21", objDocGenDTO.getATTRIBUTE21());
//			logger.info("Attribute22", objDocGenDTO.getATTRIBUTE22());
//			logger.info("Attribute23", objDocGenDTO.getATTRIBUTE23());
//			logger.info("Attribute24", objDocGenDTO.getATTRIBUTE24());
//			logger.info("Attribute25", objDocGenDTO.getATTRIBUTE25());
//			logger.info("Attribute26", objDocGenDTO.getATTRIBUTE26());
//			logger.info("Attribute27", objDocGenDTO.getATTRIBUTE27());
//			logger.info("Attribute28", objDocGenDTO.getATTRIBUTE28());
//			logger.info("Attribute29", objDocGenDTO.getATTRIBUTE29());
//			logger.info("Attribute30", objDocGenDTO.getATTRIBUTE30());
//			logger.info("Attribute31", objDocGenDTO.getATTRIBUTE31());
//			logger.info("Attribute32", objDocGenDTO.getATTRIBUTE32());
//			logger.info("Attribute33", objDocGenDTO.getATTRIBUTE33());
//			logger.info("Attribute34", objDocGenDTO.getATTRIBUTE34());
//			logger.info("Attribute35", objDocGenDTO.getATTRIBUTE35());
//			logger.info("Attribute36", objDocGenDTO.getATTRIBUTE36());
//			logger.info("Attribute37", objDocGenDTO.getATTRIBUTE37());
//			logger.info("Attribute38", objDocGenDTO.getATTRIBUTE38());
//			logger.info("Attribute39", objDocGenDTO.getATTRIBUTE39());
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			
			String filename = RegId+"_UndertakingChangeNameNationality_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_UndertakingChangeNameNationality_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_UndertakingChangeNameNationality_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	

	public String AssignmentLOALebanon(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AssignmentLOALebanon.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			data.put("Attribute39", objDocGenDTO.getATTRIBUTE39());
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			data.put("Attribute41", objDocGenDTO.getATTRIBUTE41());
			data.put("Attribute42", objDocGenDTO.getATTRIBUTE42());
			data.put("Attribute43", objDocGenDTO.getATTRIBUTE43());
			data.put("Attribute44", objDocGenDTO.getATTRIBUTE44());
			data.put("Attribute45", objDocGenDTO.getATTRIBUTE45());
			data.put("Attribute46", objDocGenDTO.getATTRIBUTE46());
			data.put("Attribute47", objDocGenDTO.getATTRIBUTE47());
			data.put("Attribute48", objDocGenDTO.getATTRIBUTE48());
			data.put("Attribute49", objDocGenDTO.getATTRIBUTE49());
			data.put("Attribute50", objDocGenDTO.getATTRIBUTE50());
			data.put("Attribute51", objDocGenDTO.getATTRIBUTE51());
			data.put("Attribute52", objDocGenDTO.getATTRIBUTE52());
			data.put("Attribute53", objDocGenDTO.getATTRIBUTE53());
			data.put("Attribute54", objDocGenDTO.getATTRIBUTE54());
			data.put("Attribute55", objDocGenDTO.getATTRIBUTE55());
			data.put("Attribute56", objDocGenDTO.getATTRIBUTE56());
			data.put("Attribute57", objDocGenDTO.getATTRIBUTE57());
			data.put("Attribute58", objDocGenDTO.getATTRIBUTE58());
			data.put("Attribute59", objDocGenDTO.getATTRIBUTE59());
			data.put("Attribute60", objDocGenDTO.getATTRIBUTE60());
			data.put("Attribute61", objDocGenDTO.getATTRIBUTE61());
			data.put("Attribute62", objDocGenDTO.getATTRIBUTE62());
			data.put("Attribute63", objDocGenDTO.getATTRIBUTE63());
			data.put("Attribute64", objDocGenDTO.getATTRIBUTE64());
			data.put("Attribute65", objDocGenDTO.getATTRIBUTE65());
			data.put("Attribute66", objDocGenDTO.getATTRIBUTE66());
			data.put("Attribute67", objDocGenDTO.getATTRIBUTE67());
			data.put("Attribute68", objDocGenDTO.getATTRIBUTE68());
			data.put("Attribute69", objDocGenDTO.getATTRIBUTE69());
			data.put("Attribute70", objDocGenDTO.getATTRIBUTE70());
			data.put("Attribute71", objDocGenDTO.getATTRIBUTE71());
			data.put("Attribute72", objDocGenDTO.getATTRIBUTE72());
			data.put("Attribute73", objDocGenDTO.getATTRIBUTE73());
			data.put("Attribute74", objDocGenDTO.getATTRIBUTE74());
			data.put("Attribute75", objDocGenDTO.getATTRIBUTE75());
			data.put("Attribute76", objDocGenDTO.getATTRIBUTE76());
			data.put("Attribute77", objDocGenDTO.getATTRIBUTE77());
			data.put("Attribute78", objDocGenDTO.getATTRIBUTE78());
			data.put("Attribute79", objDocGenDTO.getATTRIBUTE79());
			data.put("Attribute80", objDocGenDTO.getATTRIBUTE80());
			data.put("Attribute81", objDocGenDTO.getATTRIBUTE81());
			data.put("Attribute82", objDocGenDTO.getATTRIBUTE82());
			data.put("Attribute83", objDocGenDTO.getATTRIBUTE83());
			data.put("Attribute84", objDocGenDTO.getATTRIBUTE84());
			data.put("Attribute85", objDocGenDTO.getATTRIBUTE85());
			data.put("Attribute86", objDocGenDTO.getATTRIBUTE86());
			data.put("Attribute87", objDocGenDTO.getATTRIBUTE87());
			data.put("Attribute88", objDocGenDTO.getATTRIBUTE88());
			data.put("Attribute89", objDocGenDTO.getATTRIBUTE89());
			data.put("Attribute90", objDocGenDTO.getATTRIBUTE90());
			data.put("Attribute91", objDocGenDTO.getATTRIBUTE91());
			data.put("Attribute92", objDocGenDTO.getATTRIBUTE92());
			data.put("Attribute93", objDocGenDTO.getATTRIBUTE93());
			data.put("Attribute94", objDocGenDTO.getATTRIBUTE94());
			data.put("Attribute95", objDocGenDTO.getATTRIBUTE95());
			data.put("Attribute96", objDocGenDTO.getATTRIBUTE96());
			data.put("Attribute97", objDocGenDTO.getATTRIBUTE97());
			data.put("Attribute98", objDocGenDTO.getATTRIBUTE98());
			data.put("Attribute99", objDocGenDTO.getATTRIBUTE99());
			data.put("Attribute100", objDocGenDTO.getATTRIBUTE100());
			data.put("Attribute101", objDocGenDTO.getATTRIBUTE101());
			data.put("Attribute102", objDocGenDTO.getATTRIBUTE102());
			data.put("Attribute103", objDocGenDTO.getATTRIBUTE103());
			data.put("Attribute104", objDocGenDTO.getATTRIBUTE104());
			data.put("Attribute105", objDocGenDTO.getATTRIBUTE105());
			data.put("Attribute106", objDocGenDTO.getATTRIBUTE106());
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			
//			String filename = RegId+"_AssignmentLOALebanon_"+strDate+".doc";
//			String filenameP = RegId+"_AssignmentLOALebanon_"+strDate+".pdf";
//			url = bundle.getString("doc_loc_ip")+filename;
//			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
//			template.process(data, file);
//			file.flush();
//			file.close();
		    String filenameP = RegId+"_AssignmentLOALebanon_"+strDate+".pdf";
		    String htmlContent= HtmlToPdfConvertor.processHtmlStringBeforePdfGeneration(string);
			HtmlToPdfConvertor.exportToPdfBox(htmlContent, bundle.getString("doc_loc")+filenameP);
			url = bundle.getString("doc_loc_ip")+filenameP;
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	public String Assignment_LOA_KSA(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("Assignment_LOA_KSA.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());
			data.put("Attribute21", objDocGenDTO.getATTRIBUTE21());
			data.put("Attribute22", objDocGenDTO.getATTRIBUTE22());
			data.put("Attribute23", objDocGenDTO.getATTRIBUTE23());
			data.put("Attribute24", objDocGenDTO.getATTRIBUTE24());
			data.put("Attribute25", objDocGenDTO.getATTRIBUTE25());
			data.put("Attribute26", objDocGenDTO.getATTRIBUTE26());
			data.put("Attribute27", objDocGenDTO.getATTRIBUTE27());
			data.put("Attribute28", objDocGenDTO.getATTRIBUTE28());
			data.put("Attribute29", objDocGenDTO.getATTRIBUTE29());
			data.put("Attribute30", objDocGenDTO.getATTRIBUTE30());
			data.put("Attribute31", objDocGenDTO.getATTRIBUTE31());
			data.put("Attribute32", objDocGenDTO.getATTRIBUTE32());
			data.put("Attribute33", objDocGenDTO.getATTRIBUTE33());
			data.put("Attribute34", objDocGenDTO.getATTRIBUTE34());
			data.put("Attribute35", objDocGenDTO.getATTRIBUTE35());
			data.put("Attribute36", objDocGenDTO.getATTRIBUTE36());
			data.put("Attribute37", objDocGenDTO.getATTRIBUTE37());
			data.put("Attribute38", objDocGenDTO.getATTRIBUTE38());
			data.put("Attribute39", objDocGenDTO.getATTRIBUTE39());
			data.put("Attribute40", objDocGenDTO.getATTRIBUTE40());
			data.put("Attribute41", objDocGenDTO.getATTRIBUTE41());
			data.put("Attribute42", objDocGenDTO.getATTRIBUTE42());
			data.put("Attribute43", objDocGenDTO.getATTRIBUTE43());
			data.put("Attribute44", objDocGenDTO.getATTRIBUTE44());
			data.put("Attribute45", objDocGenDTO.getATTRIBUTE45());
			data.put("Attribute46", objDocGenDTO.getATTRIBUTE46());
			data.put("Attribute47", objDocGenDTO.getATTRIBUTE47());
			data.put("Attribute48", objDocGenDTO.getATTRIBUTE48());
			data.put("Attribute49", objDocGenDTO.getATTRIBUTE49());
			data.put("Attribute50", objDocGenDTO.getATTRIBUTE50());
			data.put("Attribute51", objDocGenDTO.getATTRIBUTE51());
			data.put("Attribute52", objDocGenDTO.getATTRIBUTE52());
			data.put("Attribute53", objDocGenDTO.getATTRIBUTE53());
			data.put("Attribute54", objDocGenDTO.getATTRIBUTE54());
			data.put("Attribute55", objDocGenDTO.getATTRIBUTE55());
			data.put("Attribute56", objDocGenDTO.getATTRIBUTE56());
			data.put("Attribute57", objDocGenDTO.getATTRIBUTE57());
			data.put("Attribute58", objDocGenDTO.getATTRIBUTE58());
			data.put("Attribute59", objDocGenDTO.getATTRIBUTE59());
			data.put("Attribute60", objDocGenDTO.getATTRIBUTE60());
			data.put("Attribute61", objDocGenDTO.getATTRIBUTE61());
			data.put("Attribute62", objDocGenDTO.getATTRIBUTE62());
			data.put("Attribute63", objDocGenDTO.getATTRIBUTE63());
			data.put("Attribute64", objDocGenDTO.getATTRIBUTE64());
			data.put("Attribute65", objDocGenDTO.getATTRIBUTE65());
			data.put("Attribute66", objDocGenDTO.getATTRIBUTE66());
			data.put("Attribute67", objDocGenDTO.getATTRIBUTE67());
			data.put("Attribute68", objDocGenDTO.getATTRIBUTE68());
			data.put("Attribute69", objDocGenDTO.getATTRIBUTE69());
			data.put("Attribute70", objDocGenDTO.getATTRIBUTE70());
			data.put("Attribute71", objDocGenDTO.getATTRIBUTE71());
			data.put("Attribute72", objDocGenDTO.getATTRIBUTE72());
			data.put("Attribute73", objDocGenDTO.getATTRIBUTE73());
			data.put("Attribute74", objDocGenDTO.getATTRIBUTE74());
			data.put("Attribute75", objDocGenDTO.getATTRIBUTE75());
			data.put("Attribute76", objDocGenDTO.getATTRIBUTE76());
			data.put("Attribute77", objDocGenDTO.getATTRIBUTE77());
			data.put("Attribute78", objDocGenDTO.getATTRIBUTE78());
			data.put("Attribute79", objDocGenDTO.getATTRIBUTE79());
			data.put("Attribute80", objDocGenDTO.getATTRIBUTE80());
			data.put("Attribute81", objDocGenDTO.getATTRIBUTE81());
			data.put("Attribute82", objDocGenDTO.getATTRIBUTE82());
			data.put("Attribute83", objDocGenDTO.getATTRIBUTE83());
			data.put("Attribute84", objDocGenDTO.getATTRIBUTE84());
			data.put("Attribute85", objDocGenDTO.getATTRIBUTE85());
			data.put("Attribute86", objDocGenDTO.getATTRIBUTE86());
			data.put("Attribute87", objDocGenDTO.getATTRIBUTE87());
			data.put("Attribute88", objDocGenDTO.getATTRIBUTE88());
			data.put("Attribute89", objDocGenDTO.getATTRIBUTE89());
			data.put("Attribute90", objDocGenDTO.getATTRIBUTE90());
			data.put("Attribute91", objDocGenDTO.getATTRIBUTE91());
			data.put("Attribute92", objDocGenDTO.getATTRIBUTE92());
			data.put("Attribute93", objDocGenDTO.getATTRIBUTE93());
			data.put("Attribute94", objDocGenDTO.getATTRIBUTE94());
			data.put("Attribute95", objDocGenDTO.getATTRIBUTE95());
			data.put("Attribute96", objDocGenDTO.getATTRIBUTE96());
			data.put("Attribute97", objDocGenDTO.getATTRIBUTE97());
			data.put("Attribute98", objDocGenDTO.getATTRIBUTE98());
			data.put("Attribute99", objDocGenDTO.getATTRIBUTE99());
			data.put("Attribute100", objDocGenDTO.getATTRIBUTE100());
			data.put("Attribute101", objDocGenDTO.getATTRIBUTE101());
			data.put("Attribute102", objDocGenDTO.getATTRIBUTE102());
			data.put("Attribute103", objDocGenDTO.getATTRIBUTE103());
			data.put("Attribute104", objDocGenDTO.getATTRIBUTE104());
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			
//			String filename = RegId+"_Assignment_LOA_KSA_"+strDate+".doc";
//			url = bundle.getString("doc_loc_ip")+filename;
//			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
//			template.process(data, file);
//			file.flush();
//			file.close();
			String filenameP = RegId+"_Assignment_LOA_KSA_"+strDate+".pdf";
			String htmlContent= HtmlToPdfConvertor.processHtmlStringBeforePdfGeneration(string);
			 HtmlToPdfConvertor.exportToPdfBox(htmlContent, bundle.getString("doc_loc")+filenameP);
			 url = bundle.getString("doc_loc_ip")+filenameP;
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	
	public String HO_Notice_Jordan(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("HO_Notice_Jordan.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			
//			String filename = RegId+"_HO_Notice_Jordan_"+strDate+".doc";
//			url = bundle.getString("doc_loc_ip")+filename;
//			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
//			template.process(data, file);
//			file.flush();
//			file.close();
			String filenameP = RegId+"_HO_Notice_Jordan_"+strDate+".pdf";
			String htmlContent= HtmlToPdfConvertor.processHtmlStringBeforePdfGeneration(string);
			 HtmlToPdfConvertor.exportToPdfBox(htmlContent, bundle.getString("doc_loc")+filenameP);
			 url = bundle.getString("doc_loc_ip")+filenameP;
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	public String CRFEHO(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("CRFEHO.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			Date date1 = new Date();  
		    SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
		    String strDate1= formatter1.format(date1);
			data.put("Attribute1", strDate1);
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			String s = "";
			if(objDocGenDTO.getATTRIBUTE5().equalsIgnoreCase("true")) {
			 s = "<p align='left'> Other Properties with DAMAC &nbsp;[✔]Yes &nbsp;[]No <br/> <i>(If yes please mention)</i> : "+objDocGenDTO.getATTRIBUTE6()+"</p><br/>";
			}else {
				s = "<p align='left'> Other Properties with DAMAC &nbsp;[]Yes &nbsp;[✔]No <br/> <i>(If yes please mention)</i> : "+objDocGenDTO.getATTRIBUTE6()+"</p><br/>";	
			}
			data.put("Attribute6", s);
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_CRFEHO_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_CRFEHO_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_CRFEHO_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	public String HO_Pack_Jordan(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("HO_Pack_Jordan.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			/*data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			*/
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_HO_Pack_Jordan_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_HO_Pack_Jordan_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_HO_Pack_Jordan_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	public String PlotHandoverCustomerRequestForm(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("PlotHandoverCustomerRequestForm.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());

			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_PlotHandoverCustomerRequestForm_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_PlotHandoverCustomerRequestForm_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_PlotHandoverCustomerRequestForm_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	public String PlotHandoverNoObjectiCertificate_Exceptional_Cases(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("PlotHandoverNoObjectiCertificate_Exceptional_Cases.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			/*data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			*/
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_PlotHandoverNoObjectionCertificate_Exceptional_Cases_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_PlotHandoverNoObjectionCertificate_Exceptional_Cases_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_PlotHandoverNoObjectionCertificate_Exceptional_Cases_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}

	
	public String PlotHandoverUndertakingExceptionalCases(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("PlotHandoverUndertakingExceptionalCases.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			/*data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			*/
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_PlotHandoverUndertakingExceptionalCases_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_PlotHandoverUndertakingExceptionalCases_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_PlotHandoverUndertakingExceptionalCases_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	
	public String PlotHandoverUndertaking(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("PlotHandoverUndertaking.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			/*data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			*/
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_PlotHandoverUndertaking_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_PlotHandoverUndertaking_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_PlotHandoverUndertaking_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	
	public String PlotHandoverNoObjectionCertificate(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("PlotHandoverNoObjectionCertificate.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			/*data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			*/
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_PlotHandoverNoObjectionCertificate_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_PlotHandoverNoObjectionCertificate_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_PlotHandoverNoObjectionCertificate_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	
	public String PlotHandoverNOCPreliminaryconstruction(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("PlotHandoverNOCPreliminaryconstruction.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			/*data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			*/
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_PlotHandoverNOCPreliminaryconstruction_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_PlotHandoverNOCPreliminaryconstruction_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_PlotHandoverNOCPreliminaryconstruction_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	
	public String AcknowledgmentDischargeLetterDecreaseArea(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AcknowledgmentDischargeLetterDecreaseArea.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());

			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_AcknowledgmentDischargeLetterDecreaseArea_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_AcknowledgmentDischargeLetterDecreaseArea_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_AcknowledgmentDischargeLetterDecreaseArea_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	
	public String AcknowledgmentDischargeLetterIncreaseArea(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("AcknowledgmentDischargeLetterIncreaseArea.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			data.put("Attribute12", objDocGenDTO.getATTRIBUTE12());
			data.put("Attribute13", objDocGenDTO.getATTRIBUTE13());
			data.put("Attribute14", objDocGenDTO.getATTRIBUTE14());
			data.put("Attribute15", objDocGenDTO.getATTRIBUTE15());
			data.put("Attribute16", objDocGenDTO.getATTRIBUTE16());
			data.put("Attribute17", objDocGenDTO.getATTRIBUTE17());
			data.put("Attribute18", objDocGenDTO.getATTRIBUTE18());
			data.put("Attribute19", objDocGenDTO.getATTRIBUTE19());
			data.put("Attribute20", objDocGenDTO.getATTRIBUTE20());

			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			String filename = RegId+"_AcknowledgmentDischargeLetterIncreaseArea_"+strDate+".doc";
			//url = bundle.getString("doc_loc_ip")+filename;
			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
			template.process(data, file);
			file.flush();
			file.close();
			String filenamepdf = RegId+"_AcknowledgmentDischargeLetterIncreaseArea_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_AcknowledgmentDischargeLetterIncreaseArea_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}
//		    String filenameP = RegId+"_AcknowledgmentDischargeLetterIncreaseArea_"+strDate+".pdf";
//			String htmlContent= HtmlToPdfConvertor.processHtmlStringBeforePdfGeneration(string);
//			 HtmlToPdfConvertor.exportToPdfBox(htmlContent, bundle.getString("doc_loc")+filenameP);
//			 url = bundle.getString("doc_loc_ip")+filenameP;
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	
	
	public String NocForVisa(DocGenDTO objDocGenDTO) {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		System.out.println("2  "+System.getProperty("java.class.path"));
		String string = "";
		String url = null;
		try {
			//Load template from source folder
			cfg.setDirectoryForTemplateLoading(new File(bundle.getString("ftl_loc")));
			Template template = cfg.getTemplate("NocForVisa.ftl");
			
			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			Date date1 = new Date();  
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");  
		    String strDate1= formatter1.format(date1);
		    data.put("date", strDate1);
		    data.put("Attribute1", objDocGenDTO.getATTRIBUTE1());
			data.put("Attribute2", objDocGenDTO.getATTRIBUTE2());
			data.put("Attribute3", objDocGenDTO.getATTRIBUTE3());
			data.put("Attribute4", objDocGenDTO.getATTRIBUTE4());
			data.put("Attribute5", objDocGenDTO.getATTRIBUTE5());
			data.put("Attribute6", objDocGenDTO.getATTRIBUTE6());
			data.put("Attribute7", objDocGenDTO.getATTRIBUTE7());
			data.put("Attribute8", objDocGenDTO.getATTRIBUTE8());
			data.put("Attribute9", objDocGenDTO.getATTRIBUTE9());
			data.put("Attribute10", objDocGenDTO.getATTRIBUTE10());
			data.put("Attribute11", objDocGenDTO.getATTRIBUTE11());
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss-SSS");  
		    String strDate= formatter.format(date);
		    String RegId = "RegIdNotPassed";
		    if(objDocGenDTO.getRegId() != null) {
		    	RegId = objDocGenDTO.getRegId();
		    }
			//String NameOfDoc = RegId+"_NocForVisa_"+strDate;
			String NameOfDoc = "Noc For Visa";
		    String myCodeText = "SR Type : "+objDocGenDTO.getATTRIBUTE111()+"\nReg ID : "+objDocGenDTO.getRegId()+"\nUnit Name : "+objDocGenDTO.getATTRIBUTE112()+"\nProject Name : "+objDocGenDTO.getATTRIBUTE113()+"\nName of Document : "+NameOfDoc+"\nDate and Time of Generation : "+strDate+"\nCustomer Name : "+objDocGenDTO.getATTRIBUTE114()+"\nBuyer Name : "+objDocGenDTO.getATTRIBUTE115()+"\nSource : "+objDocGenDTO.getATTRIBUTE116()+"\nUser ID : "+objDocGenDTO.getATTRIBUTE117();
		    String filenameQR = RegId+"_NocForVisa_"+strDate+".png";
		    String filePath = bundle.getString("qr_loc")+filenameQR;
		    objQRCode.generateQRCode(myCodeText, filePath);
			data.put("qrIp", bundle.getString("qr_loc_ip")+filenameQR);
			
			StringWriter stringWriter = new StringWriter();
			template.process(data, stringWriter);

			// get the String from the StringWriter
			 string = stringWriter.toString();
			System.out.print(string);
			// File output
			//String filename = FTL_helloworld.doc
			
//			String filename = RegId+"_NocForVisa_"+strDate+".doc";
//			url = bundle.getString("doc_loc_ip")+filename;
//			Writer file = new FileWriter (new File(bundle.getString("doc_loc")+filename));
//			template.process(data, file);
//			file.flush();
//			file.close();
			String filenameP = RegId+"_NocForVisa_"+strDate+".pdf";
			String htmlContent= HtmlToPdfConvertor.processHtmlStringBeforePdfGeneration(string);
			 HtmlToPdfConvertor.exportToPdfBox(htmlContent, bundle.getString("doc_loc")+filenameP);
			 url = bundle.getString("doc_loc_ip")+filenameP;
			/*String filenamepdf = RegId+"_NocForVisa_"+strDate+".pdf";
			String fileNameforHtmlXhtml = RegId+"_NocForVisa_"+strDate;
			Converter.createPdfInput(bundle.getString("doc_loc")+filename, bundle.getString("doc_loc")+filenamepdf,fileNameforHtmlXhtml);
			url = bundle.getString("doc_loc_ip")+filenamepdf;
			File doc = new File(bundle.getString("doc_loc") + fileNameforHtmlXhtml+".doc");
			File html = new File(bundle.getString("doc_loc")+"tmp_" + fileNameforHtmlXhtml+".html");
			File xhtml = new File(bundle.getString("doc_loc")+"tmp_"+ fileNameforHtmlXhtml+".xhtml");
			if(doc.exists()) {
				doc.delete();
			}
			if(html.exists()) {
				html.delete();
			}
			if(xhtml.exists()) {
				xhtml.delete();
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			return "error "+e.getMessage();
		}

		return url;

	}
	

}
