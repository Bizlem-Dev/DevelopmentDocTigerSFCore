package com.doc.services;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
//import java.net.URLConnection;
import java.util.ResourceBundle;


import org.json.JSONException;



public class SaveTemplateFromURL {
	ResourceBundle bundle = ResourceBundle.getBundle("config");
	static ResourceBundle bundleststic = ResourceBundle.getBundle("config");

	public static void main(String args[]) throws IOException, JSONException {
		SaveTemplateFromURL sta = new SaveTemplateFromURL();

		String savepath = "D:\\DoctigerSAlesforce\\";
		String fr = "http://34.74.13.213:8082/portal/bin/cpm/nodes/property.bin/content/user/doctiger_xyz.com/DocTigerAdvanced/TemplateLibrary/newtem6june/TemplateFile/File/welcomtempyy.docx/_jcr_content?name=jcr%3Adata";
		sta.saveTemplate(fr, savepath, "dct1.docx");

	}

	/* get template url and save to server */

	public String saveTemplate(String fileurl, String savepath, String filename) {
		String resp = "";

		try {

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
}
