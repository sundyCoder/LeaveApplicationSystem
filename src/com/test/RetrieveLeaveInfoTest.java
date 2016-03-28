package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.data.LeaveInfo;

public class RetrieveLeaveInfoTest {

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
	
	public Vector<Object> readBooksFromExcelFile(String excelFilePath,String supName) throws IOException {
    Vector<Object> listLeave = new Vector<Object>();
    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
 
    Workbook workbook = new HSSFWorkbook(inputStream);
    Sheet firstSheet = workbook.getSheetAt(0);
    Iterator<Row> iterator = firstSheet.iterator();
    LeaveInfo aBook = new LeaveInfo();
    while (iterator.hasNext()) {
        Row nextRow = iterator.next();
        Iterator<Cell> cellIterator = nextRow.cellIterator();
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
        if(!supName.equals("Any")){
        	if(aBook.getSupervisor().equals(supName)){
        		listLeave.add(aBook);
        	}
        }else{
        	listLeave.add(aBook);
        }
    }
 
    workbook.close();
    inputStream.close();
 
    return listLeave;
}

	private LeaveInfo LeaveInfo() {
		// TODO Auto-generated method stub
		return null;
	}
    
//	//Testing demo
//	public static void main(String[] args) throws IOException {
//	    String excelFilePath = "./doc/Leave.xls";
//	    RetrieveLeaveInfo reader = new RetrieveLeaveInfo();
//	    List<Object> listBooks = reader.readBooksFromExcelFile(excelFilePath,"Eric");
//	    System.out.println(listBooks);
//	}
}
