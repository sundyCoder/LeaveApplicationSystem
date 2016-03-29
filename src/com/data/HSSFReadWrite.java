package com.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class HSSFReadWrite {
	public static HSSFWorkbook readFile(String filename) throws IOException {
	    FileInputStream fis = new FileInputStream(filename);
	    try {
	        return new HSSFWorkbook(fis);
	    } finally {
	        fis.close();
	    }
	}
	
//	public static void main(String[] args) throws IOException {
//		HSSFWorkbook wb = HSSFReadWrite.readFile("doc/Staff.xls");
//		FileOutputStream stream = new FileOutputStream("doc/Staff.xls");
//		HSSFSheet sheet = wb.getSheetAt(0);
//		for (int k = 0; k < 2; k++) {
//			HSSFRow row = sheet.getRow(k);
//
//			sheet.removeRow(row);
//		}
////		for (int k = 74; k < 100; k++) {
////			HSSFRow row = sheet.getRow(k);
////
////			sheet.removeRow(row);
////		}
////		HSSFRow row = sheet.getRow(39);
////		HSSFCell cell = row.getCell(3);
////		cell.setCellValue("MODIFIED CELL!!!!!");
//
//		wb.write(stream);
//		stream.close();
//		wb.close();
//	}
}
