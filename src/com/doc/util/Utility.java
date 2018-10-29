package com.doc.util;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.dto.CurrentNewOwner;
import com.dto.CustomerMortgageVO;
import com.dto.TemplateFileVO;

import gvjava.org.json.JSONArray;
import gvjava.org.json.JSONException;
import gvjava.org.json.JSONObject;

public class Utility {

	//private static String UPLOAD_TEMPLATE_FILENAME = "/WEB-INF/conf/uploadedTemplates.json";
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	public static JSONObject addTemplateToJson(TemplateFileVO templateVo) throws JSONException{

		JSONObject rootObject = readTemplateFile(bundle.getString("uploaded_templates_json"));
		
		JSONObject templateobj = new JSONObject();
		templateobj.put("temaplateName", templateVo.getTemaplateName());
		templateobj.put("inputFormat", templateVo.getInputFormat());
		templateobj.put("outputFormat", templateVo.getOutputFormat());
		templateobj.put("templatePath", templateVo.getTemaplatePath());
		templateobj.put("docLanguage", templateVo.getUploadDocumentLanguageType());
		
		rootObject.put(templateVo.getTemaplateName(), templateobj);
		
		saveJsonTemplateDataToFile(rootObject);
		
		return rootObject;
	}

	public synchronized static void saveJsonTemplateDataToFile(JSONObject tempaltesJson){
		try {
			FileWriter file = new FileWriter(bundle.getString("uploaded_templates_json"));
			file.write(tempaltesJson.toString(1));
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject readTemplateFile(String filePath){
		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
			if(content.trim().equals("")){
				content = "{}";
			}
			JSONObject rootObject = new JSONObject(content);
			return rootObject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized static TemplateFileVO deleteJsonTemplateDataToFile(String templateName) throws IOException, JSONException{
		JSONObject rootObject = readTemplateFile(bundle.getString("uploaded_templates_json"));
		TemplateFileVO templateVo =getDataByTemplateNameFromFile( templateName);
		rootObject.remove(templateName);

		saveJsonTemplateDataToFile(rootObject);
		return templateVo;
	}

	public static TemplateFileVO getDataByTemplateNameFromFile(String templateName) throws JSONException{
		JSONObject rootObject = readTemplateFile(bundle.getString("uploaded_templates_json"));
		JSONObject templateObj = (JSONObject)rootObject.get(templateName);
System.out.println(templateObj);
		//setting templatevo
		TemplateFileVO templateVo = new TemplateFileVO();
		templateVo.setTemaplateName(templateObj.getString("temaplateName").toString());
		templateVo.setInputFormat(templateObj.getString("inputFormat").toString());
		templateVo.setOutputFormat(templateObj.getString("outputFormat").toString());
		templateVo.setTemaplatePath(templateObj.getString("templatePath").toString());
		templateVo.setUploadDocumentLanguageType(templateObj.getString("docLanguage").toString());

		return templateVo;

	}

	public static List<String> getAllTemplatesName() throws JSONException{
		List<String> templatesName =  new ArrayList<String>();
		JSONObject rootObject = readTemplateFile(bundle.getString("uploaded_templates_json"));
		Iterator<String> keysIte = rootObject.keys();
		templatesName = new ArrayList<String>();
		while(keysIte.hasNext()){
			templatesName.add(keysIte.next());
		}
		return templatesName;

	}



	public static boolean isNull(Object input){
		if(input==null)
			return true;
		else
			return false;
	}

	public static List<CurrentNewOwner> getCurrentNewOwnerList(String currentNewOwner){

		List<CurrentNewOwner> currentOwnerList = new ArrayList<CurrentNewOwner>();
		try {
			JSONObject rootObject = new JSONObject(currentNewOwner);
			JSONArray englishArray = (JSONArray)rootObject.get("english");
			JSONArray arabicArray = (JSONArray)rootObject.get("arabic");
			 
			for(int i=0; i<englishArray.length(); i++) {
				CurrentNewOwner currentNewOwnerObj = new CurrentNewOwner();
				JSONObject englishElement = (JSONObject) englishArray.get(i);
				JSONObject arabicElement = (JSONObject) arabicArray.get(i);

				currentNewOwnerObj.setElement1(englishElement.get("label")!=null ? englishElement.get("label").toString(): "");
				currentNewOwnerObj.setElement2(englishElement.get("value")!=null ? englishElement.get("value").toString(): "");
				currentNewOwnerObj.setElement3(arabicElement.get("label")!=null ? arabicElement.get("label").toString(): "");
				currentNewOwnerObj.setElement4(arabicElement.get("value")!=null ? arabicElement.get("value").toString(): "");

				currentOwnerList.add(currentNewOwnerObj);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return currentOwnerList;
	}
	
	public static CustomerMortgageVO getCustomerMortgageList(String customerMortgage){

		CustomerMortgageVO customerMortgageObj = new CustomerMortgageVO();
		try {
			JSONObject rootObject = new JSONObject(customerMortgage);
			JSONObject unitStatusObj = (JSONObject)rootObject.get("UnitStatus");
			JSONObject transactionTypeObj = (JSONObject)rootObject.get("TransactionType");

			customerMortgageObj.setReady(unitStatusObj.get("Ready")!=null ? unitStatusObj.get("Ready").toString(): "N");
			customerMortgageObj.setOffPlan(unitStatusObj.get("Off Plan")!=null ? unitStatusObj.get("Off Plan").toString(): "N");
			customerMortgageObj.setDifc(unitStatusObj.get("DIFC")!=null ? unitStatusObj.get("DIFC").toString(): "N");

			customerMortgageObj.setAssignment(transactionTypeObj.get("Assignment")!=null ? transactionTypeObj.get("Assignment").toString(): "N");
			customerMortgageObj.setFreshMortgage(transactionTypeObj.get("Fresh Mortgage")!=null ? transactionTypeObj.get("Fresh Mortgage").toString(): "N");
			customerMortgageObj.setRefiance(transactionTypeObj.get("Refinance")!=null ? transactionTypeObj.get("Refinance").toString(): "N");

		}catch(Exception e) {
			e.printStackTrace();
		}

		return customerMortgageObj;
	}

	public static List<String> getNewOwnerList(String newOwner) {

		JSONObject rootObject = readTemplateFile(bundle.getString("uploaded_templates_json"));
		
		return null;
	}

}
