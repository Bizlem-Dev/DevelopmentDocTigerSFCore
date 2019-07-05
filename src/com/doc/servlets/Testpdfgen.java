package com.doc.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * Servlet implementation class Testpdfgen
 */
public class Testpdfgen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testpdfgen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		try{
			out.print("in Get");
			//String inputFile="D:\\doctiger\\WelcomeLetter_25-Jun-2019_17-31-24-612.docx";
			// String outputFile="D:\\doctiger\\WelcomeLetter_25-Jun-2019_17-31-24-612.pdf";
			String inputFile="/home/ubuntu/generationTomcat/apache-tomcat-8.5.41/webapps/ROOT/Attachment/Temp1_04-Jul-2019_18-55-19-754.docx";
			String outputFile="/home/ubuntu/generationTomcat/apache-tomcat-8.5.41/webapps/ROOT/Attachment/Temp1_04-Jul-2019_18-55-19-754.pdf";
			out.println("inputFile:" + inputFile + ",outputFile:"+ outputFile);
			FileInputStream in=new FileInputStream(inputFile);
			out.println("1 :: inputFile"+inputFile);
			XWPFDocument document=new XWPFDocument(in);
			
			File outFile=new File(outputFile);
			out.println("2:: "+outputFile);
			OutputStream out1=new FileOutputStream(outFile);
			PdfOptions options=null;
			out.println("3:: ");
			PdfConverter.getInstance().convert(document,out1,options);
			out.println("3:: DONE.........");

			}catch(Exception e){
//			e.printStackTrace();
				out.println("exception in createpdfpio :: "+e);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
