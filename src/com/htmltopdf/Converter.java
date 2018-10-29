package com.htmltopdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssUtils;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import org.w3c.tidy.Tidy;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {
    public static void main(String[] args) throws IOException, DocumentException {
    //	createPdfInput("Assignment_LOA_KSA_15-10-2017.doc", "Assignment_LOA_KSA_15-10-2017.pdf","Assignment_LOA_KSA_15-10-2017");
        //new Converter().convertHtmlToPdf("AssignmentNOC_Offplan_15-10-2017.doc", "AssignmentNOC_Offplan_15-10-2017.pdf");
    }

    public void convertHtmlToPdf(String htmlFile, String pdfFile) throws IOException, DocumentException {
        //remove meta tags from html and extract css
        byte[] encoded = Files.readAllBytes(Paths.get(htmlFile));
        String fileString = new String(encoded, "utf-8");
        System.out.println("File: "+fileString);
        fileString = fileString.replaceAll("<meta[^>]*>", "");
        fileString = fileString.replaceAll("<div align=\"center\">","<div style=\"text-align: center\">"); //for center alignment of div
     
        final String regex = "<style[^>]*>([^<]*)</style>";
        final Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileString);
        StringBuilder cssSb = new StringBuilder();
        while (matcher.find()) {

            cssSb.append(matcher.group(1));
        }
        String css = cssSb.toString();
        
        PrintWriter out = new PrintWriter("tmp.html","utf-8");
        
        out.print(fileString);
        out.close();
        
        //convert html to xhtml
        InputStream in = new FileInputStream("tmp.html");
        FileOutputStream fos = new FileOutputStream("tmp.xhtml");
        Tidy tidy = new Tidy();
        //tidy.setConfigurationFromProps(oProps);
        tidy.setShowWarnings(true);
        tidy.setSmartIndent(true);
        tidy.setTrimEmptyElements(true);
        tidy.setHideEndTags(true);
        tidy.setBreakBeforeBR(true);
        tidy.setDocType("strict");
        tidy.setPrintBodyOnly(true);
      
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);
        tidy.setForceOutput(true);
//        tidy.setMakeClean(true);
        tidy.parseDOM(in, fos);
        in.close();
        fos.close();

        //convert xhtml to pdf
        String htmlString = new String(Files.readAllBytes(Paths.get("tmp.xhtml")));
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

        document.open();
        	
        ElementList list = XMLWorkerHelper.parseToElementList(htmlString, css);
        for (Element element : list) {
            document.add(element);
        }
        document.close();
    }
    static ResourceBundle bundle = ResourceBundle.getBundle("config");
    public static void createPdfInput(String htmlFile, String pdfFile,String fileNameforHtmlXhtml)
            throws IOException, DocumentException {
    	 //remove meta tags from html and extract css
        byte[] encoded = Files.readAllBytes(Paths.get(htmlFile));
        String fileString = new String(encoded, "utf-8");
        System.out.println("File: "+fileString);
        fileString = fileString.replaceAll("<meta[^>]*>", "");
        fileString = fileString.replaceAll("<div align=\"center\">","<div style=\"text-align: center\">"); //for center alignment of div
     
        final String regex = "<style[^>]*>([^<]*)</style>";
        final Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileString);
        StringBuilder cssSb = new StringBuilder();
        while (matcher.find()) {

            cssSb.append(matcher.group(1));
        }
        String css = cssSb.toString();
        
        PrintWriter out = new PrintWriter(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".html","utf-8");
        
        out.print(fileString);
        out.close();
        
        //convert html to xhtml
        InputStream in = new FileInputStream(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".html");
        FileOutputStream fos = new FileOutputStream(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".xhtml");
        Tidy tidy = new Tidy();
        //tidy.setConfigurationFromProps(oProps);
        tidy.setShowWarnings(true);
        tidy.setSmartIndent(true);
        tidy.setTrimEmptyElements(true);
        tidy.setHideEndTags(true);
        tidy.setBreakBeforeBR(true);
        tidy.setDocType("strict");
        tidy.setPrintBodyOnly(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);
        tidy.setForceOutput(true);
//        tidy.setMakeClean(true);
        tidy.parseDOM(in, fos);
        in.close();
        fos.close();

        //convert xhtml to pdf
        String htmlString = new String(Files.readAllBytes(Paths.get(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".xhtml")));
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        //MyFooter event = new MyFooter();
        //writer.setPageEvent(event);
        document.open();
        
        //document.set	
        ElementList list = XMLWorkerHelper.parseToElementList(htmlString, css);
        //int iCount = 1;
        for (Element element : list) {
        	//System.out.println("iCOunt :: "+iCount);
            document.add(element);
            //iCount++;
        }
//        Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
//        PdfContentByte cb = writer.getDirectContent();
//        //   Phrase header = new Phrase("this is a header", ffont);
//           Phrase footer = new Phrase("this is a footer", ffont);
////           ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
////                   header,
////                   (document.right() - document.left()) / 2 + document.leftMargin(),
////                   document.top() + 10, 0);
//           ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
//                   footer,
//                   (document.right() - document.left()) / 2 + document.leftMargin(),
//                   document.bottom() - 10, 0);
//        
        
        document.close();
    }

    
    
//    public static void createPdfInput(String htmlFile, String pdfFile,String fileNameforHtmlXhtml, String footer[])
//            throws IOException, DocumentException {
//    	 //remove meta tags from html and extract css
//        byte[] encoded = Files.readAllBytes(Paths.get(htmlFile));
//        String fileString = new String(encoded, "utf-8");
//        System.out.println("File: "+fileString);
//        fileString = fileString.replaceAll("<meta[^>]*>", "");
//        fileString = fileString.replaceAll("<div align=\"center\">","<div style=\"text-align: center\">"); //for center alignment of div
//     
//        final String regex = "<style[^>]*>([^<]*)</style>";
//        final Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(fileString);
//        StringBuilder cssSb = new StringBuilder();
//        while (matcher.find()) {
//
//            cssSb.append(matcher.group(1));
//        }
//        String css = cssSb.toString();
//        
//        PrintWriter out = new PrintWriter(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".html","utf-8");
//        
//        out.print(fileString);
//        out.close();
//        
//        //convert html to xhtml
//        InputStream in = new FileInputStream(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".html");
//        FileOutputStream fos = new FileOutputStream(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".xhtml");
//        Tidy tidy = new Tidy();
//        //tidy.setConfigurationFromProps(oProps);
//        tidy.setShowWarnings(true);
//        tidy.setSmartIndent(true);
//        tidy.setTrimEmptyElements(true);
//        tidy.setHideEndTags(true);
//        tidy.setBreakBeforeBR(true);
//        tidy.setDocType("strict");
//        tidy.setPrintBodyOnly(true);
//        tidy.setInputEncoding("UTF-8");
//        tidy.setOutputEncoding("UTF-8");
//        tidy.setXHTML(true);
//        tidy.setForceOutput(true);
////        tidy.setMakeClean(true);
//        tidy.parseDOM(in, fos);
//        in.close();
//        fos.close();
//
//        //convert xhtml to pdf
//        String htmlString = new String(Files.readAllBytes(Paths.get(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".xhtml")));
//        Document document = new Document(PageSize.A4);
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
//        MyFooter event = new MyFooter();
//        event.setFooterText(footer);
//        writer.setPageEvent(event);
//        document.open();
//        
//        //document.set	
//        ElementList list = XMLWorkerHelper.parseToElementList(htmlString, css);
//        //int iCount = 1;
//        for (Element element : list) {
//        	//System.out.println("iCOunt :: "+iCount);
//            document.add(element);
//            //iCount++;
//        }
////        Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
////        PdfContentByte cb = writer.getDirectContent();
////        //   Phrase header = new Phrase("this is a header", ffont);
////           Phrase footer = new Phrase("this is a footer", ffont);
//////           ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
//////                   header,
//////                   (document.right() - document.left()) / 2 + document.leftMargin(),
//////                   document.top() + 10, 0);
////           ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
////                   footer,
////                   (document.right() - document.left()) / 2 + document.leftMargin(),
////                   document.bottom() - 10, 0);
////        
//        
//        document.close();
//    }
  
    
    public static void createPdfInput(String htmlFile, String pdfFile,String fileNameforHtmlXhtml, String footer[])
            throws IOException, DocumentException {
    	 //remove meta tags from html and extract css
        byte[] encoded = Files.readAllBytes(Paths.get(htmlFile));
        String fileString = new String(encoded, "utf-8");
        System.out.println("File: "+fileString);
        fileString = fileString.replaceAll("<meta[^>]*>", "");
        fileString = fileString.replaceAll("<div align=\"center\">","<div style=\"text-align: center\">"); //for center alignment of div
     
        final String regex = "<style[^>]*>([^<]*)</style>";
        final Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileString);
        StringBuilder cssSb = new StringBuilder();
        while (matcher.find()) {

            cssSb.append(matcher.group(1));
        }
        String css = cssSb.toString();
        
        PrintWriter out = new PrintWriter(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".html","utf-8");
        
        out.print(fileString);
        out.close();
        
        //convert html to xhtml
        InputStream in = new FileInputStream(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".html");
        FileOutputStream fos = new FileOutputStream(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".xhtml");
        Tidy tidy = new Tidy();
        //tidy.setConfigurationFromProps(oProps);
        tidy.setShowWarnings(true);
        tidy.setSmartIndent(true);
        tidy.setTrimEmptyElements(true);
        tidy.setHideEndTags(true);
        tidy.setBreakBeforeBR(true);
        tidy.setDocType("strict");
        tidy.setPrintBodyOnly(true);
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);
        tidy.setForceOutput(true);
//        tidy.setMakeClean(true);
        tidy.parseDOM(in, fos);
        in.close();
        fos.close();

        //convert xhtml to pdf
        String htmlString = new String(Files.readAllBytes(Paths.get(bundle.getString("doc_loc")+"tmp_"+fileNameforHtmlXhtml+".xhtml")));
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
        MyFooter event = new MyFooter();
        event.setFooterText(footer);
        writer.setPageEvent(event);
        float margin = CssUtils.getInstance().parsePxInCmMmPcToPt("130px");
        document.setMargins(20, 20, 20, margin);
        document.open();
        
        //document.set	
        ElementList list = XMLWorkerHelper.parseToElementList(htmlString, css);
        //int iCount = 1;
        for (Element element : list) {
        	//System.out.println("iCOunt :: "+iCount);
            document.add(element);
            //iCount++;
        }
//        Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
//        PdfContentByte cb = writer.getDirectContent();
//        //   Phrase header = new Phrase("this is a header", ffont);
//           Phrase footer = new Phrase("this is a footer", ffont);
////           ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
////                   header,
////                   (document.right() - document.left()) / 2 + document.leftMargin(),
////                   document.top() + 10, 0);
//           ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
//                   footer,
//                   (document.right() - document.left()) / 2 + document.leftMargin(),
//                   document.bottom() - 10, 0);
//        
        
        document.close();
    }

    
    
}

