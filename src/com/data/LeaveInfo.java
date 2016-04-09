/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeaveInfo {
	private String name;
	private String startDate;
	private String endDate;
	private String supervisor;
	
	Workbook workbook;

	public LeaveInfo() {
	
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSupervisor() {
		return supervisor;
	}
	
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
	public String toString() {
		Vector<String> vectLeave = new Vector<String>();
		vectLeave.add(name);
		vectLeave.add(startDate);
		vectLeave.add(endDate);
		vectLeave.add(supervisor);
		return String.valueOf(vectLeave);
	}

	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}

		return null;
	}

	public Vector<Object> readBooksFromExcelFile(String excelFilePath, String supName) throws IOException {
		Vector<Object> listLeave = new Vector<Object>();
		File file=new File(excelFilePath);
		if(!file.exists()){
			HSSFWorkbook wb = new HSSFWorkbook();
			wb.createSheet("Leave");
			FileOutputStream out = new FileOutputStream(excelFilePath);
			wb.write(out);
			wb.write(out);
			out.close();
			wb.close();
		}
		
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook(inputStream);
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = new HSSFWorkbook(inputStream);
	    } else {
	        extracted();
	    }
		HSSFSheet firstSheet = (HSSFSheet) workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			LeaveInfo aBook = new LeaveInfo();
			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				switch (columnIndex) {
				case 0:
	                aBook.setName((String) getCellValue(nextCell));
	                break;
	            case 1:
	                aBook.setStartDate((String) getCellValue(nextCell));
	                break;
	            case 2:
	                aBook.setEndDate((String) getCellValue(nextCell));
	                break;
	            case 3:
	                aBook.setSupervisor((String) getCellValue(nextCell));
	                break;
				}
			}
				if (!supName.equals("Any")) {
					if (aBook.getSupervisor().equals(supName)) {
						listLeave.add(aBook);
					}
				} else {
					listLeave.add(aBook);
				}
			}
	    workbook.close();
	    inputStream.close();
		return listLeave;
	}

	private void extracted() {
		throw new IllegalArgumentException("The specified file is not Excel file");
	}
}