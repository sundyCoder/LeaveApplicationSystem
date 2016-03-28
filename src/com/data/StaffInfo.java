package com.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class StaffInfo {
	private String ID;
	private String name;
	private String age;
	private String title;
	private String supervisor;

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
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new HSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			StaffInfo aBook = new StaffInfo();
			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					aBook.setID((String) getCellValue(nextCell));
					break;
				case 1:
					aBook.setName((String) getCellValue(nextCell));
					break;
				case 2:
					aBook.setAge((String) getCellValue(nextCell));
					break;
				case 3:
					aBook.setTitle((String) getCellValue(nextCell));
					break;
				case 4:
					aBook.setSupervisor((String) getCellValue(nextCell));
					break;
				}
			}
			if (!supName.equals("Any")) {
				if (aBook.getSupervisor().equals(supName)) {
					staffVector.add(aBook);
				}
			} else {
				staffVector.add(aBook);
			}
		}
		workbook.close();
	    inputStream.close();
		return staffVector;
	}
}