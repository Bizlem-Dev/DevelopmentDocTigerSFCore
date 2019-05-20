package com.doc.convertors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.docx4j.convert.out.html.AbstractHtmlExporter;
import org.docx4j.convert.out.html.AbstractHtmlExporter.HtmlSettings;
import org.docx4j.convert.out.html.HtmlExporterNG2;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.jsoup.Jsoup;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

public class DocToPDF {

	
	public static void main(String args[]) throws IOException {
		   String st="D:\\DOCTIGER114IPProject\\testing docx\\abhishek\\";
		   String textOnly="<table  class=\"TableGrid TableNormal \" id=\"docx4j_tbl_0\" style=\"table-layout: fixed;border-collapse:>";
		   String b="'<table border= \"1\"";
		   System.out.println("b= "+b);
		   
		String tr=   textOnly.replace("<table", b);
//		   textOnly.replace("table", b);
		   System.out.println("textOnly new== "+tr);
//		Document pdfDoc = new Document(PageSize.A4);
//		PdfWriter.getInstance(pdfDoc, new FileOutputStream("src/output/txt.pdf"))
//		  .setPdfVersion(PdfWriter.PDF_VERSION_1_7);
//		pdfDoc.open();
		
//		PdfSettings pdfSettings = new PdfSettings();
//		org.docx4j.convert.out.pdf.viaXSLFO.Conversion.log.setLevel(Level.OFF);
//		OutputStream out = new FileOutputStream(new File(
//		"E:\\HelloWorld.pdf"));
//		PdfConversion converter = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(
//		template);
//		converter.output(out, pdfSettings);
		//https://angelozerr.wordpress.com/2012/12/06/how-to-convert-docxodt-to-pdfhtml-with-java/
		   try {
	            long start = System.currentTimeMillis();
	 
	            // 1) Load DOCX into WordprocessingMLPackage
	            InputStream is = new FileInputStream(new File(
	                    "D:\\DOCTIGER114IPProject\\testing docx\\abhishek\\Document.docx"));
	            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
	                    .load(is);
	            System.out.println("1");
	            // 2) Prepare Pdf settings
	            PdfSettings pdfSettings = new PdfSettings();
	            System.out.println("1");
	            // 3) Convert WordprocessingMLPackage to Pdf
	            OutputStream out = new FileOutputStream(new File(
	                    "D:\\DOCTIGER114IPProject\\testing docx\\abhishek\\Scorpi45.pdf"));
	            PdfConversion converter = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(
	                    wordMLPackage);
	            converter.output(out, pdfSettings);
	            System.out.println("done");
	            System.err.println("Generate pdf/HelloWorld.pdf with "
	                    + (System.currentTimeMillis() - start) + "ms");
	 
	        } catch (Throwable e) {
	            e.printStackTrace();
	        }
	
	
	
//	   public static void main(String[] args) throws IOException {
		
		   try {
	            long start = System.currentTimeMillis();
	 
//	             1) Load DOCX into WordprocessingMLPackage
	            InputStream is = new FileInputStream(new File(
	                    st+"TemplateTest.docx"));
	            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
	                    .load(is);
	            System.out.println("1");
	            // 2) Prepare HTML settings
	            HtmlSettings htmlSettings = new HtmlSettings();
	 
	            // 3) Convert WordprocessingMLPackage to HTML
	            OutputStream out = new FileOutputStream(new File(
	                    st+"scrorhtml.html"));
	            AbstractHtmlExporter exporter = new HtmlExporterNG2();
	            StreamResult result = new StreamResult(out);
	            exporter.html(wordMLPackage, result, htmlSettings);
	            System.out.println("done2");
	            System.err.println("Generate html/HelloWorld.html with "
	                    + (System.currentTimeMillis() - start) + "ms");
	 
	        } catch (Throwable e) {
	            e.printStackTrace();
	        }
		   
	
	
				  /* 
				   try {
			    OutputStream file = new FileOutputStream(new File(st+"Test2.pdf"));
			    Document document = new Document();
			    PdfWriter.getInstance(document, file);
			    document.open();
			    HTMLWorker htmlWorker = new HTMLWorker(document);
			    htmlWorker.parse(new StringReader(k));
			    document.close();
			    file.close();
			    System.out.println("done");
			} catch (Exception e) {
			    e.printStackTrace();
			}
		   */
		   
		   FileReader reader = new FileReader
				      (st+"scrorhtml.html");
		String ht=   extractText(reader);
		System.out.println("ht ="+ht);
		System.out.println();
				 try {

				OutputStream file = new FileOutputStream(new File(st+"TestingScor.pdf"));
				Document document = new Document();
				PdfWriter.getInstance(document, file);
				document.open();
				HTMLWorker htmlWorker = new HTMLWorker(document);
				htmlWorker.parse(new StringReader(ht));
				document.close();
				file.close();

				} catch (Exception e) {
				e.printStackTrace();
				}

				System.out.println("finished converting");
//				

	    
	   
	}
	   public static  String extractText(Reader reader) throws IOException {
		   StringBuilder sb = new StringBuilder();
		   BufferedReader br = new BufferedReader(reader);
		   String line;
		   while ( (line=br.readLine()) != null) {
		     sb.append(line);
		   }
		   String textOnly = sb.toString();
		   System.out.println("textOnly old== "+textOnly);
		   String b="<table border= \"1\"";
		   System.out.println("b= "+b);
		String tr=   textOnly.replace("<table", b);
		   System.out.println("textOnly new== "+tr);
		   return tr;
		   }
	}

	

