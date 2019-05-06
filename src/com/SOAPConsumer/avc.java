package com.SOAPConsumer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class avc {
	public static void main(String [] args) {
		//String a="TemplateName=CRF11&filename=CRF11.docx&jsonstring={\"sdvsv\":\"vsdsv\"}";
		//new avc().callPostService("http://35.188.238.145:8080/DocTiger/services/SFDCDocumentGeneration/DocGeneration", a);

		
		String a="TemplateName=DocTesting&filename=DocTesting.docx&jsonstring={\"<<Id>>\":\"12344\",\"<<namefirst>>\":\"aaaaa - aaaaaaaaaaa\", \"<<type>>\":\"false\", \"<<Isdeleted>>\":\"false\",\"<<website>>\":\"www.abc.com\", \"<<master>>\":\"78444444444\" ,\"<<accountnumber>>\":\"5454657\"}";
		new avc().callPostService("http://35.188.238.145:8080/DocTigerSF/services/SFDCDocumentGeneration/DocGeneration", a);
	//	new avc().callPostService("http://localhost:8085/DocTigerSF/services/SFDCDocumentGeneration/DocGeneration", a);

	}
	
	public  String callPostService(String urlStr, String  param) {
String userresult="";
		try {
			  // TODO Auto-generated method stub
		//	  String json = "{\"Fetcher_Id\":\"vivek123@bizlem.com\",\"Template_Name\":\"templatetest1\",\"Subject\":[\"sdsvd\"],\"Body\":[],\"Attachment\":[],\"Attachment_Name\":[],\"match\":\"1\",\"Alert\":\"\"}";
			  String urlParameters =param;
			  urlParameters = urlParameters.replace(" ", "%20");
			//  URL url = new URL("http://104.196.62.35:8082/portal/servlet/service/MapMailSolrServ.tem1");
			  URL url = new URL(urlStr);

			  URLConnection conn = url.openConnection();

			  conn.setDoOutput(true);

			  OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

			  writer.write(urlParameters);
			  writer.flush();
InputStream inputXml= conn.getInputStream();

DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
Document doc = dBuilder.parse(inputXml);
doc.getDocumentElement().normalize();
NodeList nList1 = doc.getElementsByTagName("ns:DocGenerationResponse");
org.w3c.dom.Node nNode = nList1.item(0);
org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
 userresult = eElement.getElementsByTagName("ns:return").item(0).getTextContent();

System.out.println("userresult  "+userresult);

			//  String line;
			//  BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

//			  while ((line = reader.readLine()) != null) {
//			   //   System.out.println(line);
//				  res=  res+line;
//			  }
//			  reader.close();         
			  
writer.close();
	  	  }catch (Exception e) {
			   // TODO: handle exception
			   System.out.println("error :: "+e.getMessage());
			  }
		return userresult;
    }
}
