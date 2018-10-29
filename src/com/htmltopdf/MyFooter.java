package com.htmltopdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

class MyFooter extends PdfPageEventHelper {
	Font ffont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
	String footerText[];
	Font ffontSmall = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);

	public void onEndPage(PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		//  Phrase header = new Phrase("this is a header", ffont);
		//String s =  "<p>Request-NOC-Td 271111 <text style='margin: 0 15px'></text>Assignor ${Attribute54} .............. <text style='margin: 0 15px'></text>Assignee ${Attribute55} ..............</p><br/><p><text style='margin: 0 109px'></text>Joint Assignor ${Attribute56} _____________ <text style='margin: 0 15px'></text>Joint Assignee ${Attribute57} _____________</p>";
		try {
			Phrase footer = new Phrase();
			footer.setFont(ffont);

			PdfPTable table = new PdfPTable(2);

			table.setWidths(new int[]{35,35});
			table.getDefaultCell().setFixedHeight(30);
			table.setTotalWidth(700);
			PdfPCell cell1 = new PdfPCell(new Paragraph(footerText[0],ffont)); 
			cell1.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell1);
			PdfPCell cell2 = new PdfPCell(new Paragraph(footerText[1],ffont));
			cell2.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell2);
			
			//blank row
			PdfPCell blankCell = new PdfPCell(new Paragraph(" ",ffontSmall));
			blankCell.setBorder(Rectangle.NO_BORDER);
			table.addCell(blankCell);
			table.addCell(blankCell);
			
			PdfPCell cell3 = new PdfPCell(new Paragraph(footerText[2],ffont));
			cell3.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell3);
			PdfPCell cell4 = new PdfPCell(new Paragraph(footerText[3],ffont));
			cell4.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell4);
			
			
			if(footerText[4]!=null){
				PdfPCell cell5 = new PdfPCell(new Paragraph(footerText[4],ffontSmall));
				cell5.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell5);
			}else{
				table.addCell(blankCell);
			}

			table.addCell(blankCell);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			
			table.writeSelectedRows(0, -1, 63, table.getTotalHeight()+40, writer.getDirectContent());
			footer.add(table);
			//Request-NOC-Td 271111 Assignor ${Attribute54} _____________				Assignee ${Attribute55} _____________ Joint Assignor ${Attribute56} _____________				Joint Assignee ${Attribute57} _____________
			/*
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT,
					footer,
					(document.right() - document.left()) / 10 + document.leftMargin(),
					document.bottom() - 10, document.bottom()-5);*/
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String[] getFooterText() {
		return footerText;
	}


	public void setFooterText(String[] footerText) {
		this.footerText = footerText;
	}
}
