package com.doc.services;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
//import java.net.URLConnection;
import java.util.ResourceBundle;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

//import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;



public class SaveTemplateFromURL {
	ResourceBundle bundle = ResourceBundle.getBundle("config");
	static ResourceBundle bundleststic = ResourceBundle.getBundle("config");

	public static void main(String args[]) throws IOException, JSONException {
		SaveTemplateFromURL sta = new SaveTemplateFromURL();
		String nn="https://development.bizlem.io:8083/portal/content/services/freetrial/users/viki_gmail.com/DocTigerAdvanced/TemplateLibrary/scorpio_report_summary/TemplateFile/File/Status Report for new -bracketTempNe.docx";
		String savepath = "D:\\DoctigerSAlesforce\\";
		String op="http://34.74.13.213:8082/portal/bin/cpm/nodes/property.bin/content/user/bizlemcclink_isvedition.org.bizpartial/DocTigerAdvanced/TemplateLibrary/test/TemplateFile/File/welcome letter sample.docx/_jcr_content?name=jcr%3Adata";
		String hh="http://35.200.169.114:8082/portal/content/services/freetrial/users/viki_gmail.com/DocTigerAdvanced/TemplateLibrary/ttss/TemplateFile/File/DocumentScorpio.docx";
		String fr = "http://34.74.13.213:8082/portal/bin/cpm/nodes/property.bin/content/user/doctiger_xyz.com/DocTigerAdvanced/TemplateLibrary/newtem6june/TemplateFile/File/welcomtempyy.docx/_jcr_content?name=jcr%3Adata";
//		String fileurl,String savepath ,String filename
		sta.saveTemplate(nn, savepath, "dct21.docx");
//		sta.getTemp(op, savepath, "newdoc1.docx");
//		 URL url =new URL(nn);
//		InputStream is = url.openStream();
//		System.out.println("done");
//		OutputStream os = new FileOutputStream("D:\\DOCTIGER114IPProject\\testing docx\\file.docx");
//
//		byte[] b = new byte[2048];
//		int length;
//
//		while ((length = is.read(b)) != -1) {
//			os.write(b, 0, length);
//		}
//
//		is.close();
//		os.close();
	}

	/* get template url and save to server */

	public String saveTemplate(String fileurl, String savepath, String filename) {
		String resp = "";

		try {
			fileurl = fileurl.replace(" ", "%20");
			SaveTemplateFromURL.trustAllCertificate();
			BufferedInputStream in = new BufferedInputStream(new URL(fileurl).openStream());
			FileOutputStream fileOutputStream = new FileOutputStream(savepath + filename);
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
			resp = "success";
			System.out.println("files saved");
		} catch (Exception e) {
			// TODO: handle exception
			resp = e.getMessage();
			System.out.println("e :: " + e);
		} finally {

		}
		return resp;
	}
	public void getTemp(String fileurl,String savepath ,String filename) {
		String resp="";
		try {
			fileurl = fileurl.replace(" ", "%20");
//			SaveTemplateFromURL.trustAllCertificate();
			URL url;
			InputStream ins = null;
			
//			String encodedURL="https://development.bizlem.io:8083"+java.net.URLEncoder.encode(fileurl,"UTF-8");
//			System.out.println(encodedURL);
			StringBuilder requestString = new StringBuilder(fileurl);
			url = new URL(requestString.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			System.out.println("done1 ");
//			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			System.out.println("done2 ");
//			URL urlconnection = new URL(fileurl);
//			URLConnection conn = urlconnection.openConnection();

			String contentType = conn.getContentType();
			System.out.println("done3 ");
			int contentLength = conn.getContentLength();
			System.out.println("done3 ");
//			if (contentType.startsWith("text/") || contentLength == -1) {
//				System.out.println("This is not a binary file.");
//			}
			System.out.println("done3 5");
			InputStream raw = conn.getInputStream();
			System.out.println("4");
			InputStream in = new BufferedInputStream(raw);
			byte[] databyte = new byte[contentLength];
			int bytesRead = 0;
			int offset = 0;
			while (offset < contentLength) {
				bytesRead = in.read(databyte, offset, databyte.length - offset);
				if (bytesRead == -1)
					break;
				offset += bytesRead;
			}
			in.close();

			if (offset != contentLength) {
			//	System.out.println("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
			}
			FileOutputStream streamout = new FileOutputStream(savepath+filename);
			streamout.write(databyte);
			streamout.close();
			raw.close();
			in.close();
			System.out.println("done ");
		} catch (Exception e) {
			// TODO: handle exception
			resp = resp + "3" + e;
//			resp = e.getMessage();
			System.out.println("exc- " + e);
		}
	
		
	}
	public static void trustAllCertificate() {
		try {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		return null;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) {
		}
		} };

		// Install the all-trusting trust manager
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
		public boolean verify(String hostname, SSLSession session) {
		return true;
		}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		} catch (Exception e) {
		// TODO: handle exception
		}
		}
	
}