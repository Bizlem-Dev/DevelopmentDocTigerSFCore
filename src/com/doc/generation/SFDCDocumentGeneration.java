package com.doc.generation;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.dao.docgeneration.DocGenDAO;
import com.doc.services.CreateJsonFile;
import com.doc.services.DocGenService;
import com.doc.services.UploadTemplateServer;
import com.doc.util.IConstants;
import com.doc.util.JsonGetterSetter;
import com.doc.util.Utility;
import com.dto.DocGenDTO;
import com.dto.TemplateFileVO;

import org.json.JSONException;
import org.json.JSONObject;

public class SFDCDocumentGeneration {
	final static Logger logger = Logger.getLogger(SFDCDocumentGeneration.class); 
	DocGenDAO objDocGenDAO = new DocGenDAO();
	ResourceBundle bundle = ResourceBundle.getBundle("config");
	DocGenService objDocGenService = new DocGenService();
	//public String DocGeneration(String TemplateName,String filename,String jsonstring) {
	public String DocGeneration(JsonGetterSetter objJsonGetterSetter) {
		String result = null;
		String TemplateName = objJsonGetterSetter.getTemplateName();
		String filename = objJsonGetterSetter.getFileName();
		String jsonstring = objJsonGetterSetter.getJsonString();
		JSONObject obj;
		try {
		      logger.info("TemplateName::  "+TemplateName);
		      logger.info("filename :: "+filename);
		      logger.info("jsonstring ::  "+jsonstring);

			
			
				obj = new JSONObject(jsonstring);			
			DocGenService objDocGenService = new DocGenService();

			
			UploadTemplateServer ut=new UploadTemplateServer("35.201.178.201",22,"root","B!zL3M786");
                      //	FileUploadServer fus= new FileUploadServer("35.188.238.145",22,"ubuntu","$DocTiger@123$");
		      logger.info("a1 ");

	   //   String a=ut.connect();
	   //   logger.info("a "+a);
	   //   System.out.print("connect :: "+a);
	      String serv222path= "/home/vil/sling\\ tomcat/apache-tomcat-6.0.35/webapps/ROOT/TemplateLibraryAdvance/";
		             //	String servsavepah="/home/ubuntu/uploaded-templates";
	  	String servsavepah=bundle.getString("uploaded_templates_path");
	  	 logger.info("servsavepah "+servsavepah);
	  			     //"C:\\upoaded-templates\\";

		String b=	ut.download(serv222path+""+filename, servsavepah);
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
						result = objDocGenService.converEnglishDocxFileToPdf(obj, templateFileVO);
					}else if(templateFileVO.getInputFormat().equals(IConstants.EXTENSION_XLSX)) {
					System.out.println("in xlsx");					
				//	result = objDocGenService.converEnglishxlsFileToPdf(objDocGenDTO, templateFileVO);

					}else if(templateFileVO.getInputFormat().equals(IConstants.EXTENSION_DOCX) && templateFileVO.getUploadDocumentLanguageType().equals(IConstants.ARABIC_LANGUAGE)){
						System.out.println("converArabicDocxFileToPdf");

						result = objDocGenService.converArabicDocxFileToPdf(obj, templateFileVO);
					}
					else{
						result = "Under Construction";
					}
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = e.getMessage();
			}
				
			
			return result;
		
		
		
		
	}

}
