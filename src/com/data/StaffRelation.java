package com.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StaffRelation {
	private String strName;
	private String strSup;
	private static Vector<Object> relStaffVector;
	private static Vector<Object> relLeaveVector;
	
	private ArrayList<String> supList;
	private Vector subList;
	private StaffRelation allStaff;
	
	private Vector<String> relTable;
	
	Workbook workbook;
	
	public StaffRelation(){
		supList = new ArrayList<String>();
		subList = new Vector();
	}
	
	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getStrSup() {
		return strSup;
	}

	public void setStrSup(String strSup) {
		this.strSup = strSup;
	}

	public String toString() {
		Vector<Object> vecRel = new Vector<Object>();
		vecRel.add(strName);
		vecRel.add(strSup);
		return String.valueOf(vecRel);
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
	
	public Vector<Object> readRelFromStaffInfo() throws IOException {
		
		String excelFilePath = "Staff.xls";
		relStaffVector = new Vector<Object>();

		File file=new File(excelFilePath);
		if(!file.exists()){
			HSSFWorkbook wb = new HSSFWorkbook();
			wb.createSheet("Staff");
			FileOutputStream out = new FileOutputStream(excelFilePath);
			wb.write(out);
			wb.write(out);
			out.close();
			wb.close();
			System.out.println("readRel!");
			return null;
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
			StaffRelation stInfo = new StaffRelation();
			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					getCellValue(nextCell);
					break;
				case 1:
					stInfo.setStrName((String) getCellValue(nextCell));
					break;
				case 2:
					getCellValue(nextCell);
					break;
				case 3:
					getCellValue(nextCell);
					break;
				case 4:
					stInfo.setStrSup((String) getCellValue(nextCell));
					break;
				}
			}
			relStaffVector.add(stInfo);
		}
		workbook.close();
	    inputStream.close();
		return relStaffVector;
	}
	
	
   public Vector<Object> readRelFromLeaveInfo()throws IOException {
		String excelFilePath = "Leave.xls";
		relLeaveVector = new Vector<Object>();

		File file=new File(excelFilePath);
		if(!file.exists()){
			HSSFWorkbook wb = new HSSFWorkbook();
			wb.createSheet("Leave");
			FileOutputStream out = new FileOutputStream(excelFilePath);
			wb.write(out);
			wb.write(out);
			out.close();
			wb.close();
			System.out.println("readRel!");
			return null;
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
			StaffRelation stInfo = new StaffRelation();
			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					stInfo.setStrName((String) getCellValue(nextCell));
					break;
				case 1:
					getCellValue(nextCell);
					break;
				case 2:
					getCellValue(nextCell);
					break;
				case 3:
					stInfo.setStrSup((String) getCellValue(nextCell));
					break;
				}
			}
			relLeaveVector.add(stInfo);
		}
		workbook.close();
	    inputStream.close();
		return relLeaveVector;
	}   
   
   
   public boolean getLeaveProgress(String staffName) throws IOException{
			String excelFilePath = "Progress.xls";
			boolean progress = true;

			File file=new File(excelFilePath);
			if(!file.exists()){
				HSSFWorkbook wb = new HSSFWorkbook();
				wb.createSheet("Progress");
				FileOutputStream out = new FileOutputStream(excelFilePath);
				try {
					wb.write(out);
					wb.write(out);
					out.close();
					wb.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("readRel!");
				return false;
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
//				StaffRelation stInfo = new StaffRelation();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					
					String name = (String) getCellValue(nextCell);
					nextCell = cellIterator.next();
					String blTemp = (String) getCellValue(cellIterator.next());
					
					if(name.equals(staffName)){
						if(blTemp.equals("true")){
							progress = progress&&true;
						}else if(blTemp.equals("false")){
							progress = progress&&false;
						}
					}else{
						break;
					}
				}
			}
			workbook.close();
		    inputStream.close();
		    return progress;
   }
   
   //find all the leave people
   public Vector<String> getAllPeople() {
//		ArrayList<String> subList = new ArrayList<String>();
		for (Object obs : relLeaveVector) {
			StaffRelation  staff = (StaffRelation) obs;
		    subList.add(staff.strName); 
		}
		return subList;
  }   
   
	//请假的下属找所有他的直接和间接上司列表
	public String getStaffSupList(String subName) {
		StaffRelation staff = new StaffRelation();
		try {
			staff.readRelFromStaffInfo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Object obs : relStaffVector) {
			staff = (StaffRelation) obs;
//			System.out.println("staff.strName = "+staff.strName);
			if (staff.strName.equals(subName)) {
				return staff.strSup;
			}
		}
		return null;
   }
	
	public Vector<String> getSupList(String subName) {
		System.out.println("subName = "+subName);
		relTable = new Vector<String>();
		String sup = getStaffSupList(subName);
		while(true){
			System.out.println("check:"+sup);
			if(sup.equals("null")){
				return relTable;
			}else{
				relTable.add(sup);
				sup = getStaffSupList(sup);
			}
		}
   }
	
//	public static void main(String[] args) {
//		StaffRelation sp = new StaffRelation();		
//		System.out.println("result:"+sp.getSupList("sundy"));
//		System.out.println("result:"+sp.getSupList("James"));
//	}
}
