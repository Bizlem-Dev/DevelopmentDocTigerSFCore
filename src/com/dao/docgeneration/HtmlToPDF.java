package com.dao.docgeneration;

import java.io.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;



public class HtmlToPDF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		    String k = "<html><body> This is my Project </body></html>";
		    OutputStream file = new FileOutputStream(new File("D:\\Test.pdf"));
		    Document document = new Document();
		    PdfWriter.getInstance(document, file);
		    document.open();
		    HTMLWorker htmlWorker = new HTMLWorker(document);
		    htmlWorker.parse(new StringReader(k));
		    document.close();
		    file.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		          

	}

}
