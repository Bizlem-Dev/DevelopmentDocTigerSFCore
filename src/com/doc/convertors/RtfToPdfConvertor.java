package com.doc.convertors;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.document.DefaultDocumentFormatRegistry;
import org.artofsolving.jodconverter.document.DocumentFormat;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

public class RtfToPdfConvertor {
	final static Logger log = Logger.getLogger(RtfToPdfConvertor.class);

	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	private static String TEMP_FILENAME_1 ="TEMP1.doc";
	private static String TEMP_FILENAME_2 ="TEMP2.doc";
	public static void convertRtfFileToPDF(String rtfPath,String pdfPath, Map<String, Object> paramsMap, String outputDocsPath){

		OfficeManager officeManager = null;
		try {
			officeManager = new DefaultOfficeManagerConfiguration().setOfficeHome(new File(bundle.getString("openoffice_path"))).buildOfficeManager();
			officeManager.start();

			OfficeDocumentConverter converter = readRtfFile( officeManager);
			createDocument(converter,rtfPath,outputDocsPath+TEMP_FILENAME_1);
			textReplacementUsingPOI(outputDocsPath+TEMP_FILENAME_1,outputDocsPath+TEMP_FILENAME_2, paramsMap);
			createDocument(converter,outputDocsPath+TEMP_FILENAME_2,pdfPath);
		} catch (Exception e) {
			log.info("error in convertRtfFileToPDF in RtfToPdfConvertor "+ e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 4) Stop LibreOffice in headless mode.
			if (officeManager != null) {
				officeManager.stop();
			}
		}
	}
	public static void convertDocxFileToPDF(String docxPath,String pdfPath, Map<String, Object> paramsMap, String outputDocsPath){

		OfficeManager officeManager = null;
		try {
			officeManager = new DefaultOfficeManagerConfiguration().setOfficeHome(new File(bundle.getString("openoffice_path"))).buildOfficeManager();
			officeManager.start();

			OfficeDocumentConverter converter = readRtfFile( officeManager);
			//createDocument(converter,rtfPath,outputDocsPath+TEMP_FILENAME_1);
			//textReplacementUsingPOI(outputDocsPath+TEMP_FILENAME_1,outputDocsPath+TEMP_FILENAME_2, paramsMap);
			createDocument(converter,docxPath,pdfPath);
		} catch (Exception e) {
			log.info("error in convertDocxFileToPDF in RtfToPdfConvertor"+ e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 4) Stop LibreOffice in headless mode.
			if (officeManager != null) {
				officeManager.stop();
			}
		}
	}

	public static void convertByteArrayFileToPDF(byte[] arr,String pdfPath, Map<String, Object> paramsMap, String outputDocsPath){

		OfficeManager officeManager = null;
		try {
			officeManager = new DefaultOfficeManagerConfiguration().setOfficeHome(new File(bundle.getString("openoffice_path"))).buildOfficeManager();
			officeManager.start();
			OfficeDocumentConverter converter = readRtfFile( officeManager);
			InputStream is = new ByteArrayInputStream(arr);
			HWPFDocument document = new HWPFDocument(new POIFSFileSystem(is));
			OutputStream out = new FileOutputStream(outputDocsPath);
			document.write(out);
			
			createDocument(converter,outputDocsPath+TEMP_FILENAME_2,pdfPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 4) Stop LibreOffice in headless mode.
			if (officeManager != null) {
				officeManager.stop();
			}
		}
	}
	public static void convertRtfFileToPDF(byte[] modifiedTable, String pdfPath, Map<String, Object> paramsMap, String outputDocsPath){

		OfficeManager officeManager = null;
		try {
			officeManager = new DefaultOfficeManagerConfiguration().setOfficeHome(new File(bundle.getString("openoffice_path"))).buildOfficeManager();
			officeManager.start();

			OfficeDocumentConverter converter = readRtfFile( officeManager);
			//createDocument(converter,rtfPath,outputDocsPath+TEMP_FILENAME_1);
			textReplacementUsingPOI(modifiedTable,outputDocsPath+TEMP_FILENAME_2, paramsMap);
			createDocument(converter,outputDocsPath+TEMP_FILENAME_2,pdfPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 4) Stop LibreOffice in headless mode.
			if (officeManager != null) {
				officeManager.stop();
			}
		}
	}

	private static OfficeDocumentConverter readRtfFile( OfficeManager officeManager) {
		OfficeDocumentConverter converter = null;
		try {
			// 2) Create JODConverter converter
			converter = new OfficeDocumentConverter(officeManager);

		}catch (Exception e) {
			e.printStackTrace();
		}

		return converter;
	}

	private static void createDocument(OfficeDocumentConverter converter,String inputFile, String outputFile) {
		try {
			long start = System.currentTimeMillis();
			converter.convert(new File(inputFile), new File(outputFile));
			System.err.println("Generate pdf/pdf with "
					+ (System.currentTimeMillis() - start) + "ms");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void textReplacementUsingPOI(String srcFile, String destFile,Map<String, Object> paramsMap) throws IOException {

		InputStream is = new FileInputStream(new File(srcFile));
		HWPFDocument document = new HWPFDocument(new POIFSFileSystem(is));

		Range range = document.getRange();

		for (Map.Entry<String, Object> entry : paramsMap.entrySet()){
			range.replaceText(entry.getKey(),  entry.getValue().toString());
		}
		savePOIDocument(document,destFile);
	}
	private static void textReplacementUsingPOI(byte[] byteArr, String destFile,Map<String, Object> paramsMap) throws IOException {

		InputStream is = new ByteArrayInputStream(byteArr);
		HWPFDocument document = new HWPFDocument(new POIFSFileSystem(is));
		Range range = document.getRange();
		for (Map.Entry<String, Object> entry : paramsMap.entrySet()){
			range.replaceText(entry.getKey(),  entry.getValue().toString());
		}
		savePOIDocument(document,destFile);
	}

	private static void savePOIDocument(HWPFDocument doc, String file) {
		try  {
			OutputStream out = new FileOutputStream(file);
			doc.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
