package com.doc.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class ApiCall {
	final static Logger logger = Logger.getLogger(ApiCall.class);

	public static void main(String[] args) {
//		ResourceBundle bundle = ResourceBundle.getBundle("config");
//		 String PARAM =bundle.getString("ruleparam")+"=hjdsygy";
//		String result =ApiCall.callPosturl(bundle.getString("DocTigerrule"), PARAM);
		String result =ApiCall.callGetApi("http://35.188.238.145:8080/DocTigerSF/pdffromurl?pdfurl=https://sf.deeprootsurface.com/plans/OS22.pdf&destinationFile=/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/images/qrcode/OS22.pdf");

//System.out.println(result);
		
	}
	
	public static String callGetApi(String GET_URL) {
		String result="";
		URL obj=null;
		HttpURLConnection con;

		StringBuffer response=null;
		try {
			obj = new URL(GET_URL);
		
			con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			 response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request not worked");
		}

	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{

	}
		return response.toString();
	}
	
	

    public static String callPosturl(String POST_URL,String POST_PARAMS ){
        StringBuilder result=null;
   // String POST_PARAMS = "userName=jay";

		 try {
            URL url = new URL(POST_URL);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
       //     http.setRequestProperty("Content-Type", "application/json");
            http.setDoInput(true);
            http.setDoOutput(true);
            
            
//            JSONObject obj = new JSONObject();
//            obj.put("query", "create project in jira");
//            obj.put("lang", "en");
//            obj.put("sessionId", "sdvsbvs");
//            System.out.println(obj.toString());
//            //Send request
            DataOutputStream wr = new DataOutputStream(
                    http.getOutputStream());
           // wr.writeBytes(obj.toString());
            wr.writeBytes(POST_PARAMS);

            wr.flush();
            wr.close();
            int statusCode = http.getResponseCode();
            System.out.println("code=======" + statusCode);
            String newLine = System.getProperty("line.separator");
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            result = new StringBuilder();
            String line;
            boolean flag = false;
            while ((line = reader.readLine()) != null) {
                result.append(flag ? newLine : "").append(line);
                flag = true;
            }
          //  System.out.println(result);
        } catch (Exception e) {
            System.out.println("error  " + e.getMessage());
        }
                 return result.toString();
	}
    
		
		
    public static String callPostJSon(String urlstr, JSONObject Obj) {
    	logger.info("urlstr  ::  "+urlstr);
    	logger.info("Obj  ::  "+Obj);

    	StringBuffer response =null;
    		  int responseCode = 0;
    		  String urlParameters = "";
    		  try {

    		   URL url = new URL(urlstr);
    		   HttpURLConnection con = (HttpURLConnection) url.openConnection();
    		   con.setRequestMethod("POST");

    		   con.setRequestProperty("Content-Type", "application/json");

    		  
    		   con.setDoOutput(true);
    		   DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    		   wr.writeBytes(Obj.toString());
    		   wr.flush();
    		   wr.close();

    		   responseCode = con.getResponseCode();

    		   BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    		   String inputLine;
    		    response = new StringBuffer();

    		   while ((inputLine = in.readLine()) != null) {
    		    response.append(inputLine);
    		   }
    		   in.close();

    		   System.out.println(response.toString());
    		  }
    		  catch (Exception e) {
    		return   e.getMessage();
    		  }
    		  return response.toString();

    		 }


	

}
