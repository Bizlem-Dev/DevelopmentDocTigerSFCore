package com.doc.services;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Serverconnection {
	final static Logger logger = Logger.getLogger(Serverconnection.class); 
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public  static String servconnect(String Templatename, String filename) {
		try {
		//UploadTemplateServer ut=new UploadTemplateServer("35.201.178.201",22,"ubuntu","B!zL3M786");
			String SFservIP= bundle.getString("SFservIP");//35.221.183.246
			String SFservusername=bundle.getString("SFservusername");
				String 	SFservpass=bundle.getString("SFservpass");//B!zL3M786
			UploadTemplateServer ut=new UploadTemplateServer(SFservIP,22,SFservusername,SFservpass);			
		//	UploadTemplateServer ut=new UploadTemplateServer("35.221.183.246",22,"ubuntu","B!zL3M786");
                  //	FileUploadServer fus= new FileUploadServer("35.188.238.145",22,"ubuntu","$DocTiger@123$");
	      logger.info("a1 ");
   String serv201path= bundle.getString("Slingserverfilepath");
		  	 logger.info("serv201path "+serv201path);

  	String servsavepah=bundle.getString("uploaded_templates_path");
  	 logger.info("servsavepah "+servsavepah);

	String b=	ut.download(serv201path+""+filename, servsavepah);
  	 logger.info("b "+b);
		
		CreateJsonFile cj = new CreateJsonFile();
	      String fp = servsavepah+""+filename;

	//	String fp = "/home/vil/sling tomcat/apache-tomcat-6.0.35/webapps/ROOT/TemplateLibrary/Doc4.docx";
	   //     + filename;
	      String[] words;
	      String temp = Templatename;
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
			return "success";	

		}catch (Exception e) {
			e.getMessage();
			return "error";	

		}

		}

}
