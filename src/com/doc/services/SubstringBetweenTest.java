package com.doc.services;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.doc.convertors.DocxToPdfConvertor;
import com.doc.util.IConstants;
import com.doc.util.Utility;
import com.dto.TemplateFileVO;

public class SubstringBetweenTest {

	public static void main(String[] args) throws Exception {

		
		String a="[\"3 Majestine - TIGC Membership\",\"LTL Fixed Return annex-Finalized - 14 Aug 2018\",\"4% Promotion-V3-270618\",\"Artesia Hotel - 28 nights free stay promo-2018\"]";
		JSONArray promotionarr= new JSONArray(a);
		JSONArray urlarr = new JSONArray();
		for(int i=0;i<promotionarr.length(); i++) {
			String Temlatename=promotionarr.getString(i);
			String filename=Temlatename+".docx";
			System.out.println("Temlatename "+Temlatename+" filename "+filename);
//		String status=	Serverconnection.servconnect(Temlatename, filename);
//		if(status.equals("success")){							
//			TemplateFileVO templateFileVO1 = Utility.getDataByTemplateNameFromFile(Temlatename);
//		String outputFilename1 = getFilename(Temlatename, obj);
//			String outputPdfPath1 = bundle.getString("doc_loc")+outputFilename1+IConstants.PERIOD+IConstants.EXTENSION_PDF;
//			String outputDocxPath1 = bundle.getString("doc_loc")+outputFilename1+IConstants.PERIOD+IConstants.EXTENSION_DOCX;
//		//	url = bundle.getString("doc_loc_ip")+outputFilename+IConstants.PERIOD+IConstants.EXTENSION_PDF;
//	  String   url1 = bundle.getString("doc_loc_ip")+outputFilename1+IConstants.PERIOD+IConstants.EXTENSION_PDF;
//		DocxToPdfConvertor.replaceParamsInDocxFile(sfobj, templateFileVO1.getTemaplatePath(), outputDocxPath1, data);
		
//		RestTemplate restTemplate = new RestTemplate();
         
         
//         JSONObject jsonObj1 = new JSONObject();
//         jsonObj1.put("applicationId", applicationId);
//         jsonObj1.put("secretKey", secretKey);
//         jsonObj1.put("inputDocxFilePath", outputDocxPath);
//         jsonObj1.put("outputPdfFilePath", outputPdfPath);
//         
//         HttpHeaders headers1 = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);	
//         
//         HttpEntity<String> entity1 = new HttpEntity<String>(jsonObj.toString(),headers);
//         String answer1 = restTemplate.postForObject(bundle.getString("convertorServiceUrl"), entity, String.class);
//         System.out.println(answer);
//    
//         logger.info("3*  ");
//         System.out.println(answer);
//         logger.info("4*  ");
//     
//		url=url+url1;
//		
		}
		
	}
	
	public void aa() throws IOException {
	File file = new File("C:\\upoaded-templates\\test.html");
	String testHtml = FileUtils.readFileToString(file); // from commons io

	String title = StringUtils.substringBetween(testHtml, "<title>", "</title>");
	System.out.println("title:" + title); // good

	String[] tds = StringUtils.substringsBetween(testHtml, "{", "}");
	for (String td : tds) {
		System.out.println("{}" + td); // good
	}

	String moreStuff = StringUtils.substringBetween(testHtml, "head");
	System.out.println("\n'head' to 'head':" + moreStuff); // not so good
	}

}
