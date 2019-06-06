package com.doc.services;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

public class SaveAttachmentFromURL {
	ResourceBundle bundle = ResourceBundle.getBundle("config");
	static ResourceBundle bundleststic = ResourceBundle.getBundle("config");
	public static void main(String args[]) throws IOException {
		SaveAttachmentFromURL sta=new SaveAttachmentFromURL();
		String fileurl="https://dev.bizlem.io:8082/scorpioexcel/TonnageData.xls";
		sta.SaveFile(fileurl) ;
		
	}
	/*get attachment url and save to root */
	public String SaveFile(String fileurl) throws IOException {
		String savepath=bundleststic.getString("doc_loc");
		String filename="";
		if (fileurl != null && fileurl != "") {
			int o = fileurl.lastIndexOf("/");
			 filename = fileurl.substring(o + 1, fileurl.length());
//		        out.println("comma separated ooooo: " +generatedfile);
		}
		
		String resp=null;

		try {
		URL url = new URL(fileurl);
		URLConnection conn = url.openConnection();

		String contentType = conn.getContentType();
		int contentLength = conn.getContentLength();
		if (contentType.startsWith("text/") || contentLength == -1) {
			// out.println("This is not a binary file.");
		}

		System.out.println("1");
		InputStream raw = conn.getInputStream();
		InputStream in = new BufferedInputStream(raw);
		byte[] data = new byte[contentLength];
		int bytesRead = 0;
		int offset = 0;
		while (offset < contentLength) {
			bytesRead = in.read(data, offset, data.length - offset);
			if (bytesRead == -1)
				break;
			offset += bytesRead;
		}
		in.close();

		if (offset != contentLength) {
			System.out.println(
					"Only read " + offset + " bytes; Expected " + contentLength + " bytes");
		}
	
		FileOutputStream streamout = new FileOutputStream(savepath+ filename);
		streamout.write(data);
		streamout.close();
		raw.close();
		in.close();
		System.out.println("done ");
		}catch (Exception e) {
			// TODO: handle exception
			resp=e.getMessage();
			System.out.println("exc- "+e.getMessage());
		}
		resp ="Save Successfully";
		return resp;

	}
}
