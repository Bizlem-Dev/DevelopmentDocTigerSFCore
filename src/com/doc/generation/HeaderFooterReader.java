package com.doc.generation;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;

public class HeaderFooterReader {

	public static void main(String[] args) {
		
		try {
			FileInputStream fis = new FileInputStream("D:\\docgenlocal\\CreateWordHeaderFooterTable.docx");
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(xdoc);
			InputStream imgStream = new FileInputStream("C:\\upoaded-templates\\Damac.png");

			XWPFHeader header = policy.getDefaultHeader();
			if (header != null) {
				
				header.addPictureData(imgStream, XWPFDocument.PICTURE_TYPE_PNG );
				System.out.println(header.getText());
			}

			XWPFFooter footer = policy.getDefaultFooter();
			if (footer != null) {
				System.out.println(footer.getText());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}