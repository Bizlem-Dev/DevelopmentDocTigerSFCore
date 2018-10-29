package com.doc.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.doc.generation.SFDCDocumentGeneration;
import com.scorpion.request.ConvertRequestProperty;
import com.scorpion.service.ConvertServiceImpl;

import reactor.util.Assert;


public class pdfTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(pdfTest.class); 

	 ConvertServiceImpl convertService;


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter out =	response.getWriter();
	
	
	String outputDocxPath  ="/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/Template_09_08-Sep-2018_13-00-11-369.docx";
	String outputPdfPath ="/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/Template_09_08-Sep-2018_13-00-11-369.pdf";
	
	String applicationId="4f909e42-64ec-479e-9cd5-8c07f71b45fd";
    logger.info("applicationId*  "+applicationId);
 String secretKey="0515f105-ea44-48b8-a32b-7416b14f0d8f" ;
 logger.info("secretKey*  "+secretKey);
	
	convertService = new ConvertServiceImpl(applicationId, secretKey);
     File file = new File(outputDocxPath);
     logger.info("file*  "+file);
     ConvertRequestProperty convertRequestProperty = ConvertRequestProperty.builder()
             .inputFile(file).build();
     logger.info("1*  ");
     byte[] bytes = convertService.convert(convertRequestProperty);
     logger.info("2*  ");
     FileUtils.writeByteArrayToFile(new File(outputPdfPath), bytes);
     logger.info("3*  ");
     Assert.notNull(bytes);
     logger.info("4*  ");
		
		
		
		
	}

}
