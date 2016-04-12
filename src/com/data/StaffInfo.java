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

import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StaffInfo {
	private String ID;
	private String name;
	private String age;
	private String title;
	private String supervisor;
	Workbook workbook;

	public StaffInfo() {
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String iD) {
		ID = iD;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String string) {
		this.age = string;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		Vector<Object> vectLeave = new Vector<Object>();
		vectLeave.add(ID);
		vectLeave.add(name);
		vectLeave.add(age);
		vectLeave.add(title);
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
		Vector<Object> staffVector = new Vector<Object>();

		File file=new File(excelFilePath);
		if(!file.exists()){
			HSSFWorkbook wb = new HSSFWorkbook();
			wb.createSheet("Staff");
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
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			StaffInfo stInfo = new StaffInfo();
			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					stInfo.setID((String) getCellValue(nextCell));
					break;
				case 1:
					stInfo.setName((String) getCellValue(nextCell));
					break;
				case 2:
					stInfo.setAge((String) getCellValue(nextCell));
					break;
				case 3:
					stInfo.setTitle((String) getCellValue(nextCell));
					break;
				case 4:
					stInfo.setSupervisor((String) getCellValue(nextCell));
					break;
				}
			}
			if (!supName.equals("Any")) {
				if (stInfo.getSupervisor().equals(supName)) {
					staffVector.add(stInfo);
				}
			} else {
				staffVector.add(stInfo);
			}
		}
		workbook.close();
	    inputStream.close();
		return staffVector;
	}
}
