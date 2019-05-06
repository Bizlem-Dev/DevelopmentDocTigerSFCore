package com.doc.convertors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.doc.util.Utility;
import com.dto.CurrentNewOwner;
import com.dto.CustomerMortgageVO;
import com.dto.DocGenDTO;

public class AddTableDataInTemplate {
	final static Logger logger = Logger.getLogger(AddTableDataInTemplate.class);

	public enum FontType {
        ARABIC,
        ENGLISH
     }
	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	public static byte[] addRowsInTableUsingJsonDataInAssignmentNOC(DocGenDTO objDocGenDTO, String temaplatePath) throws FileNotFoundException, IOException {


		XWPFDocument doc = new XWPFDocument(new FileInputStream(temaplatePath));
		XWPFTable unitinformationTable = doc.getTableArray(1);
		XWPFTable currentOwnerTable = doc.getTableArray(2);
		XWPFTable newOwnerTable = doc.getTableArray(3);
		
//		XWPFTable unitinformationTable = doc.getTableArray(1);
//		XWPFTable currentOwnerTable = doc.getTableArray(3);
//		XWPFTable newOwnerTable = doc.getTableArray(4);

		try {
			List<CurrentNewOwner> currentOwnerList = Utility.getCurrentNewOwnerList(objDocGenDTO.getJsonString1());
			List<CurrentNewOwner> newOwnerList = Utility.getCurrentNewOwnerList(objDocGenDTO.getJsonString2());
			List<CurrentNewOwner> unitinformationList = Utility.getCurrentNewOwnerList(objDocGenDTO.getJsonString3());

			for(CurrentNewOwner row : currentOwnerList){
				XWPFTableRow newRow = currentOwnerTable.createRow();
		
				int twipsPerInch =  1440;
			    newRow.setHeight((int)(twipsPerInch*18/100)); //set height 1/10 inch.
			    newRow.getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT); //set w:hRule="exact"
			    
				createParagraphRun(newRow.getCell(0), "Arial", 9, ParagraphAlignment.LEFT, row.getElement1().trim(), FontType.ENGLISH);
				createParagraphRun(newRow.getCell(1), "Arial", 9, ParagraphAlignment.LEFT, row.getElement2().trim(), FontType.ENGLISH);
				createParagraphRun(newRow.getCell(3), "Arial", 8, ParagraphAlignment.RIGHT, row.getElement3().trim(), FontType.ARABIC);
				createParagraphRun(newRow.getCell(4), "Arial", 8, ParagraphAlignment.RIGHT, row.getElement4().trim(), FontType.ARABIC);
				
			}

			for(CurrentNewOwner row : newOwnerList){
				XWPFTableRow newRow = newOwnerTable.createRow();

				int twipsPerInch =  1440;
			    newRow.setHeight((int)(twipsPerInch*18/100)); //set height 1/10 inch.
			    newRow.getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT); //set w:hRule="exact"
			    
	            
				createParagraphRun(newRow.getCell(0), "Arial", 9, ParagraphAlignment.LEFT, row.getElement1().trim(), FontType.ENGLISH);
				createParagraphRun(newRow.getCell(1), "Arial", 9, ParagraphAlignment.LEFT, row.getElement2().trim(), FontType.ENGLISH);
				createParagraphRun(newRow.getCell(3), "Arial", 8, ParagraphAlignment.RIGHT, row.getElement3().trim(), FontType.ARABIC);
				createParagraphRun(newRow.getCell(4), "Arial", 8, ParagraphAlignment.RIGHT, row.getElement4().trim(), FontType.ARABIC);
			}
			
			for(CurrentNewOwner row : unitinformationList){
				XWPFTableRow newRow = unitinformationTable.createRow();
				

				int twipsPerInch =  1440;
			    newRow.setHeight((int)(twipsPerInch*18/100)); //set height 1/10 inch.
			    newRow.getCtRow().getTrPr().getTrHeightArray(0).setHRule(STHeightRule.EXACT); //set w:hRule="exact"
			     
				createParagraphRun(newRow.getCell(0), "Arial", 9, ParagraphAlignment.LEFT, row.getElement1().trim(), FontType.ENGLISH);
				createParagraphRun(newRow.getCell(1), "Arial", 9, ParagraphAlignment.LEFT, row.getElement2().trim(), FontType.ENGLISH);
				createParagraphRun(newRow.getCell(3), "Arial", 8, ParagraphAlignment.RIGHT, row.getElement3().trim(), FontType.ARABIC);
				createParagraphRun(newRow.getCell(4), "Arial", 8, ParagraphAlignment.RIGHT, row.getElement4().trim(), FontType.ARABIC);
			}
		}catch(Exception e) {
			System.err.println("Not able to create table in docx");
			//not able to create table in docx
		}
		currentOwnerTable.removeRow(1);
		newOwnerTable.removeRow(1);
		unitinformationTable.removeRow(1);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		doc.write(baos);
		doc.close();
		return baos.toByteArray();
	}
	
	public static byte[] addRowsInTableUsingJsonDataInMortgageRequest(DocGenDTO objDocGenDTO, String temaplatePath) throws FileNotFoundException, IOException, InvalidFormatException {
		
		XWPFDocument doc = new XWPFDocument(new FileInputStream(temaplatePath));
		
		CustomerMortgageVO customerMortgageVO = Utility.getCustomerMortgageList(objDocGenDTO.getJsonString1());
		
		//unit status
		XWPFTable unitStatusTable = doc.getTableArray(1);
		XWPFTableRow row = unitStatusTable.getRow(0);
		List<XWPFTableCell> cell = row.getTableCells();
		List<XWPFParagraph> paragraph1 = cell.get(0).getParagraphs();
		List<XWPFRun> run1 = paragraph1.get(0).getRuns();
		//Image
		InputStream imgReady =null;
		InputStream imgoffPlan = null;
		InputStream imgDifc = null;
		InputStream imgAssignment = null;
		InputStream imgFreshMortgage = null;
		InputStream imgRefinance = null;
		//unit status
		if(customerMortgageVO.getReady().equals("Y")) {
			imgReady = new FileInputStream(bundle.getString("uploaded_templates_path")+"check.png");
		}else {
			imgReady = new FileInputStream(bundle.getString("uploaded_templates_path")+"uncheck.png");
		}
		run1.get(0).addPicture(imgReady, XWPFDocument.PICTURE_TYPE_PNG, "", Units.toEMU(10), Units.toEMU(10));
		
		List<XWPFParagraph> paragraph2 = cell.get(1).getParagraphs();
		List<XWPFRun> run2 = paragraph2.get(0).getRuns();
		
		if(customerMortgageVO.getOffPlan().equals("Y")) {
			imgoffPlan = new FileInputStream(bundle.getString("uploaded_templates_path")+"check.png");
		}else {
			imgoffPlan = new FileInputStream(bundle.getString("uploaded_templates_path")+"uncheck.png");
		}
		run2.get(0).addPicture(imgoffPlan, XWPFDocument.PICTURE_TYPE_PNG, "", Units.toEMU(10), Units.toEMU(10));
		
		List<XWPFParagraph> paragraph3 = cell.get(2).getParagraphs();
		List<XWPFRun> run3 = paragraph3.get(0).getRuns();
		
		if(customerMortgageVO.getDifc().equals("Y")) {
			imgDifc = new FileInputStream(bundle.getString("uploaded_templates_path")+"check.png");
		}else {
			imgDifc = new FileInputStream(bundle.getString("uploaded_templates_path")+"uncheck.png");
		}
		run3.get(0).addTab();
		run3.get(0).addPicture(imgDifc, XWPFDocument.PICTURE_TYPE_PNG, "", Units.toEMU(10), Units.toEMU(10));

		// Transaction Type:
		XWPFTable transactionType = doc.getTableArray(2);
		XWPFTableRow row2 = transactionType.getRow(0);
		List<XWPFTableCell> cell2 = row2.getTableCells();
		List<XWPFParagraph> paragraph4 = cell2.get(0).getParagraphs();
		List<XWPFRun> run4 = paragraph4.get(0).getRuns();
		
		if(customerMortgageVO.getAssignment().equals("Y")) {
			imgAssignment = new FileInputStream(bundle.getString("uploaded_templates_path")+"check.png");
		}else {
			imgAssignment = new FileInputStream(bundle.getString("uploaded_templates_path")+"uncheck.png");
		}
		run4.get(0).addTab();
		run4.get(0).addPicture(imgAssignment, XWPFDocument.PICTURE_TYPE_PNG, "", Units.toEMU(10), Units.toEMU(10));
		
		
		List<XWPFParagraph> paragraph5 = cell2.get(1).getParagraphs();
		List<XWPFRun> run5 = paragraph5.get(0).getRuns();
		
		if(customerMortgageVO.getFreshMortgage().equals("Y")) {
			imgFreshMortgage = new FileInputStream(bundle.getString("uploaded_templates_path")+"check.png");
		}else {
			imgFreshMortgage = new FileInputStream(bundle.getString("uploaded_templates_path")+"uncheck.png");
		}
		run5.get(0).addTab();
		run5.get(0).addPicture(imgFreshMortgage, XWPFDocument.PICTURE_TYPE_PNG, "", Units.toEMU(10), Units.toEMU(10));
		
		
		List<XWPFParagraph> paragraph6 = cell2.get(2).getParagraphs();
		List<XWPFRun> run6 = paragraph6.get(0).getRuns();
		
		if(customerMortgageVO.getRefiance().equals("Y")) {
			imgRefinance = new FileInputStream(bundle.getString("uploaded_templates_path")+"check.png");
		}else {
			imgRefinance = new FileInputStream(bundle.getString("uploaded_templates_path")+"uncheck.png");
		}
		run6.get(0).addTab();
		run6.get(0).addPicture(imgRefinance, XWPFDocument.PICTURE_TYPE_PNG, "", Units.toEMU(10), Units.toEMU(10));

		
		unitStatusTable.addRow(row, 0);
		unitStatusTable.removeRow(1);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		doc.write(baos);
		doc.close();
		//imgStream.close();
		return baos.toByteArray();
	}
    
	public static XWPFParagraph createParagraphRun(XWPFTableCell xwpfTableCell, String fontFamily, int fontSize, ParagraphAlignment align/*, TextOrientation orientation*/, String text, FontType fontType){
	

		XWPFParagraph paragraph = xwpfTableCell.addParagraph();
		xwpfTableCell.removeParagraph(0);
		XWPFRun run = paragraph.createRun();
		run.setFontSize(fontSize);
	
		if(fontType == FontType.ARABIC) {
			
			run.setFontFamily(fontFamily, XWPFRun.FontCharRange.ascii);
			run.setFontFamily(fontFamily, XWPFRun.FontCharRange.hAnsi);
			run.setFontFamily(null, XWPFRun.FontCharRange.cs);
			run.setFontFamily(null, XWPFRun.FontCharRange.eastAsia);
			CTP ctp = paragraph.getCTP();
			CTPPr ctppr = ctp.getPPr();
			if (ctppr == null) ctppr = ctp.addNewPPr();
			ctppr.addNewBidi().setVal(STOnOff.ON);
		}else {
			run.setFontFamily(fontFamily);
		}
		run.setText(text);
		paragraph.setAlignment(align);
		return paragraph;
	}

	public static byte[] addRowsInTable(String path,  List<List<String>> rowList, int tableNumber, int colsCount) throws FileNotFoundException, IOException, XmlException{

		XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
		XWPFTable table = doc.getTableArray(tableNumber-1);
		//get row 2 
		for(List<String> row : rowList){
			XWPFTableRow newRow = table.createRow();
			for(int j=0; j<colsCount; j++){
				newRow.getCell(j).setText(row.get(j));
			}
		}
		table.removeRow(1);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		doc.write(baos);
		doc.close();
		return baos.toByteArray();
	}

	public static byte[] addRowsInTableWithFixedRows(String path,  List<List<String>> rowList, int tableNumber, int colsCount) throws FileNotFoundException, IOException, XmlException{

		XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
		XWPFTable table = doc.getTableArray(tableNumber-1);
		//get row 2 
		XWPFTableRow oldRow = table.getRow(1);
		CTRow ctrow = CTRow.Factory.parse(oldRow.getCtRow().newInputStream());

		int i = 1;
		for(List<String> row : rowList){
			XWPFTableRow tableRow = table.getRow(i);
			int j=0;
			for (XWPFTableCell cell : tableRow.getTableCells()) {
				if(j>=colsCount){
					break;
				}
				for (XWPFParagraph paragraph : cell.getParagraphs()) {
					for (XWPFRun run : paragraph.getRuns()) {
						System.out.println(run.getText(0));
						if(run.getText(0)!=null && run.getText(0).contains("Attribute")){
							String text = run.getText(0).replace("Attribute", row.get(j++));
							run.setText( text, 0);
							break;
						}
					}
					break;
				}
			}

			//table.addRow(tableRow, i);
			i++;
		}
		//table.removeRow(1);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		doc.write(baos);
		doc.close();
		return baos.toByteArray();
	}

	public static byte[] addQRCodeInTable(String path, String imagePath, int tableNumber) throws FileNotFoundException, IOException, XmlException, InvalidFormatException{
		ByteArrayOutputStream baos = null;

		XWPFDocument doc=null;
		try{
		 doc = new XWPFDocument(new FileInputStream(path));
	
		XWPFTable table = doc.getTableArray(tableNumber-1);
		//get row 2 
		XWPFTableRow row = table.getRow(0);
		logger.info("**1");

		List<XWPFTableCell> cell = row.getTableCells();
		logger.info("**2");

		List<XWPFParagraph> paragraph = cell.get(0).getParagraphs();
		logger.info("**3");

		List<XWPFRun> run = paragraph.get(0).getRuns();
		logger.info("**4");


		//Image
		InputStream imgStream = new FileInputStream(imagePath);
		logger.info("**5");


		/*BufferedImage img = ImageIO.read(imgStream);
		double w = img.getWidth();
		double h = img.getHeight();

		double scaling = 1.0;
		   if (w > 25*1) scaling = (25*1)/w; //scale width not to be greater than 6 inches
		 */
logger.info("**"+run.get(0).getText(0));
		run.get(0).addPicture(imgStream, XWPFDocument.PICTURE_TYPE_PNG, " ", Units.toEMU(50), Units.toEMU(50));
		table.addRow(row, 0);
		table.removeRow(1);
		 baos = new ByteArrayOutputStream();
		doc.write(baos);
		doc.close();
		//imgStream.close();
		}catch(Exception e ) {
		logger.info("addTabledata  "  +e.getMessage());
		
		baos = new ByteArrayOutputStream();
		doc.write(baos);
		doc.close();
		
		}
		return baos.toByteArray();

	}
	
	
	
	public static byte[] addcoverimg(byte[] byteArr, String imagePath, int tableNumber, int width, int height) throws FileNotFoundException, IOException, XmlException, InvalidFormatException{
		ByteArrayOutputStream baos = null;
logger.info("tableNumber "+tableNumber);
		InputStream is = new ByteArrayInputStream(byteArr);

		try{
		XWPFDocument doc = new XWPFDocument(is);
	
		XWPFTable table = doc.getTableArray(tableNumber-1);
		//get row 2 
		XWPFTableRow row = table.getRow(0);
		logger.info("**1");

		List<XWPFTableCell> cell = row.getTableCells();
		logger.info("**2");

		List<XWPFParagraph> paragraph = cell.get(0).getParagraphs();
		logger.info("**3");

		List<XWPFRun> run = paragraph.get(0).getRuns();
		logger.info("**4");


		//Image
		InputStream imgStream = new FileInputStream(imagePath);
		logger.info("**5");


		/*BufferedImage img = ImageIO.read(imgStream);
		double w = img.getWidth();
		double h = img.getHeight();

		double scaling = 1.0;
		   if (w > 25*1) scaling = (25*1)/w; //scale width not to be greater than 6 inches
		 */
//logger.info("**"+run.get(0).getText(0));
		run.get(0).addPicture(imgStream, XWPFDocument.PICTURE_TYPE_PNG, " ", Units.toEMU(width), Units.toEMU(height));
		logger.info("**6");
		table.addRow(row, 0);
		logger.info("**7");
		table.removeRow(1);
		logger.info("**8");
		 baos = new ByteArrayOutputStream();
			logger.info("**9");
		doc.write(baos);
		doc.close();
		logger.info("**10");
		//imgStream.close();
		}catch(Exception e ) {
		logger.info("addTabledata  "  +e.getMessage());
		}
		return baos.toByteArray();
	}
	
	
	
	
	
	
	
	
	
	

	public static byte[] addQRCodeInTable(byte[] byteArr, String imagePath, int tableNumber) throws FileNotFoundException, IOException, XmlException, InvalidFormatException{

		InputStream is = new ByteArrayInputStream(byteArr);

		XWPFDocument doc = new XWPFDocument(is);
		XWPFTable table = doc.getTableArray(tableNumber-1);
		//get row 2 
		XWPFTableRow row = table.getRow(0);
		List<XWPFTableCell> cell = row.getTableCells();
		List<XWPFParagraph> paragraph = cell.get(0).getParagraphs();
		List<XWPFRun> run = paragraph.get(0).getRuns();
		run.get(0).addPicture(new FileInputStream(imagePath), XWPFDocument.PICTURE_TYPE_PNG, "", 1000, 1000);
		table.addRow(row, 0);
		table.removeRow(1);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		doc.write(baos);
		doc.close();
		return baos.toByteArray();
	}
	//addQRCodeInTable(modifiedFileArr, filePath, qrCodeTableNumber)	
	public static byte[] addLogoInTablearabic(byte[] byteArr, String logopath, int tableNumber, int width, int height) throws FileNotFoundException, IOException, XmlException, InvalidFormatException{
System.out.println(logopath);
		InputStream is = new ByteArrayInputStream(byteArr);
		String a ="text to be added";
		InputStream imgStream = new FileInputStream(logopath);

		XWPFDocument doc = new XWPFDocument(is);
	//	doc.getHeaderArray(0);
		XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(doc);
		XWPFHeader header = policy.getDefaultHeader();

		
		if (header != null) {
			System.out.println("in header");
			XWPFTable table = header.getTableArray(0);
			System.out.println("in header");
			XWPFTableRow row = table.getRow(0);
			System.out.println("in header");
			List<XWPFTableCell> cell = row.getTableCells();
			System.out.println("in header");
			List<XWPFParagraph> paragraph = cell.get(0).getParagraphs();
			System.out.println("in header");
			List<XWPFRun> run = paragraph.get(0).getRuns();
		//	run.get(0).setText(a);
			run.get(0).addPicture(imgStream, XWPFDocument.PICTURE_TYPE_PNG, "", Units.toEMU(width), Units.toEMU(height));

			//run.get(0).addPicture(new FileInputStream(logopath), XWPFDocument.PICTURE_TYPE_PNG, "", 100, 100);
			table.addRow(row, 0);
			table.removeRow(1);

		//header.addPictureData(imgStream, XWPFDocument.PICTURE_TYPE_PNG );
			System.out.println(header.getText());
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		doc.write(baos);
		doc.close();
		return baos.toByteArray();
	}
	
	
	

}
