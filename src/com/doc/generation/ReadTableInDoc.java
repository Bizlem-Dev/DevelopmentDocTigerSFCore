package com.doc.generation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class ReadTableInDoc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String temaplatePath="D://docgenlocal//Braemar ACM Weekly Tanker Market Review 2 March 2018.docx";
		XWPFDocument doc;
		try {
			doc = new XWPFDocument(new FileInputStream(temaplatePath));
		doc.getTables();
	Iterator<XWPFTable> tablesarr=	doc.getTablesIterator();
	int count=0;
	int cellc=0;

	while(tablesarr.hasNext()) {
		XWPFTable table=tablesarr.next();
		List<XWPFTableRow> row= table.getRows();
		for(int i=0; i<row.size(); i++) {
			List<XWPFTableCell> cell=	row.get(i).getTableCells();
			for(int j=0; j<cell.size(); j++) {
				 cellc=0;
				System.out.print(cell.get(j).getParagraphs().get(0).getText());
				System.out.print(" ");
				//System.out.println(cell.get(j).getParagraphs().get(1).getText());

				//System.out.println(i+" cell count: "+j);
				cellc++;
			}
			System.out.println();
			//System.out.println(i+" cell count: "+cellc);
		}
		System.out.println("NEW TABLE "+count+"-------------------------------------------------");
		count++;
	}
		System.out.println(count);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
