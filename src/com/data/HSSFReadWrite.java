/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.data;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class HSSFReadWrite {
	public static HSSFWorkbook readFile(String filename) throws IOException {
	    FileInputStream fis = new FileInputStream(filename);
	    try {
	        return new HSSFWorkbook(fis);
	    } finally {
	        fis.close();
	    }
	}
}
