package com.doc.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConvertDocxToPDF {

	public static String getWordToPdfData(String inputFile, String outputFile){

		StringBuffer response= new StringBuffer();
		try {

		String url = "http://prod.bizlem.io:8085/ExcelCheckApi/WordToPdfServlet?inputFile="+inputFile+"&outputFile="+outputFile;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
		}
		in.close();

		//print result
			System.out.println(response.toString());



		} catch (Exception e) {
			System.out.println("exc : "+e);
			return e.getMessage();
		}
		return response.toString();

		}	
	
	public static void main(String args[]) {
		//Temp1_04-Jul-2019_17-24-47-804.docx
		// /home/ubuntu/generationTomcat/apache-tomcat-8.5.41/webapps/ROOT/Attachment/Temp1_04-Jul-2019_17-24-47-804.docx
		String data=getWordToPdfData("/home/ubuntu/generationTomcat/apache-tomcat-8.5.41/webapps/ROOT/Attachment/Temp1_04-Jul-2019_17-24-47-804.docx", "/home/ubuntu/generationTomcat/apache-tomcat-8.5.41/webapps/ROOT/Attachment/Temp1_04-Jul-2019_17new.pdf");
		System.out.println("data == "+data);
	}
	
	
}
