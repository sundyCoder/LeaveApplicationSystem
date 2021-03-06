/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DataSave {
	public DataSave() {
	}
	public void createXLS(DefaultTableModel dtm,String fileName) {
		try {			
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFCreationHelper createhelper = wb.getCreationHelper();
			HSSFSheet sheet = wb.createSheet("Staff");
			Row row = null;
			Cell cell = null;
			for (int i = 0; i < dtm.getRowCount(); i++) {
				row = sheet.createRow(i);
				for (int j = 0; j < dtm.getColumnCount(); j++) {
					cell = row.createCell(j);
					cell.setCellValue(dtm.getValueAt(i, j).toString());
				}
			}

			FileOutputStream out = new FileOutputStream(fileName);
			wb.write(out);
			out.close();
			wb.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(DataSave.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(DataSave.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
