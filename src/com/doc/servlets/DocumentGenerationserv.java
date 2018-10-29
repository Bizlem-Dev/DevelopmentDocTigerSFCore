package com.doc.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.docgeneration.DocGenDAO;
import com.doc.generation.SFDCDocumentGeneration;
import com.doc.services.CreateJsonFile;
import com.doc.services.DocGenService;
import com.doc.services.UploadTemplateServer;
import com.doc.util.IConstants;
import com.doc.util.Utility;
import com.dto.TemplateFileVO;

import org.json.JSONObject;

public class DocumentGenerationserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SFDCDocumentGeneration.class); 
	DocGenDAO objDocGenDAO = new DocGenDAO();
	ResourceBundle bundle = ResourceBundle.getBundle("config");
	DocGenService objDocGenService = new DocGenService();
    
    public DocumentGenerationserv() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		String result = null;
		String TemplateName = "";
		String filename = "";
		String jsonstring = "";
		JSONObject obj;
		try {
			out=resp.getWriter();
  //			req.setCharacterEncoding("UTF-8");
  //			//req.getH
  //			BufferedInputStream bis = new BufferedInputStream(req.getInputStream());
  //			//new Buff
  //			ByteArrayOutputStream buf = new ByteArrayOutputStream();
  //			int result1 = bis.read();
  //			while (result1 != -1) {
  //				buf.write((byte) result1);
  //				result1 = bis.read();
  //			}
			//String res = buf.toString("UTF-8"); 
			//req.getReader();
			//String item = request.getParameter("param"); 

			 StringBuilder buffer = new StringBuilder();
		        BufferedReader reader = req.getReader();

		        String line;
		        while((line = reader.readLine()) != null){
		            buffer.append(line);
		        }
		        // reqBytes = buffer.toString().getBytes();

		       String res = buffer.toString();
      //		       out.println("json response from sling befre  123 :: "+res);
      //			byte[] bytes = input.toString().getBytes(StandardCharsets.ISO_8859_1);
     //			String res = new String(bytes, StandardCharsets.UTF_8);
	//		out.println("json response from sling befre  :: "+res);
			
			JSONObject resobj = new JSONObject(res);
			
			
		//	out.println("json response from sling  :: "+resobj);
			
			 TemplateName = resobj.getString("TemplateName");

			filename=resobj.getString("filename");
			
		      logger.info("TemplateName::  "+resobj.getString("TemplateName"));
		      logger.info("filename :: "+resobj.getString("filename"));
		      logger.info("jsonstring ::  "+resobj);

			
			
			DocGenService objDocGenService = new DocGenService();

			
			//UploadTemplateServer ut=new UploadTemplateServer("35.201.178.201",22,"ubuntu","B!zL3M786");
			UploadTemplateServer ut=new UploadTemplateServer("35.221.183.246",22,"ubuntu","B!zL3M786");
                      //	FileUploadServer fus= new FileUploadServer("35.188.238.145",22,"ubuntu","$DocTiger@123$");
		      logger.info("a1 ");
	   //   String a=ut.connect();
	   //   logger.info("a "+a);
	   //   System.out.print("connect :: "+a);
	     // String serv201path= "/home/vil/sling\\ tomcat/apache-tomcat-6.0.35/webapps/ROOT/TemplateLibraryAdvance/";
		      String serv201path= bundle.getString("Slingserverfilepath");
			  	 logger.info("serv201path "+serv201path);

		             //	String servsavepah="/home/ubuntu/uploaded-templates";
	  	String servsavepah=bundle.getString("uploaded_templates_path");
	  	 logger.info("servsavepah "+servsavepah);
	  			     //"C:\\upoaded-templates\\";

		String b=	ut.download(serv201path+""+filename, servsavepah);
	  	 logger.info("b "+b);

			
			CreateJsonFile cj = new CreateJsonFile();
		      String fp = servsavepah+""+filename;

		//	String fp = "/home/vil/sling tomcat/apache-tomcat-6.0.35/webapps/ROOT/TemplateLibrary/Doc4.docx";
		   //     + filename;
		      String[] words;
		      String temp = TemplateName;
		      String ext = "";
		      String g = filename;
		      words = g.split("\\.");
		      for (int j = 0; j < words.length; j++) {
		       temp = words[words.length - 2];
		       ext = words[words.length - 1];
		      }		      
		    	  String jsonfile_pah= bundle.getString("uploaded_templates_json");
		    	//  "C:\\upoaded-templates\\uploadedTemplates.json"
				String res1 = cj.addTemplateToJson(jsonfile_pah ,temp, ext, "pdf", fp, " docLanguage");
			
			  	 logger.info("res1 "+res1);
						
				TemplateFileVO templateFileVO = Utility.getDataByTemplateNameFromFile(TemplateName);
				 if(templateFileVO.getInputFormat().equals(IConstants.EXTENSION_DOCX)){
						System.out.println("in docx");
					//	result = objDocGenService.converEnglishDocxFileToPdf(resobj, templateFileVO);
						
						logger.info("converArabicDocxFileToPdf");

						result = objDocGenService.converArabicDocxFileToPdf(resobj, templateFileVO);
					
					}else if(templateFileVO.getInputFormat().equals(IConstants.EXTENSION_XLSX)) {
					System.out.println("in xlsx");					
				//	result = objDocGenService.converEnglishxlsFileToPdf(objDocGenDTO, templateFileVO);

					}else{
						result = "Under Construction";
					}
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = e.getMessage();
			}
				
			
		out.println(result);
		
		
		
		
		
		
		
		
		
	}

}
