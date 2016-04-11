/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.data;

import java.io.File;
import java.util.ArrayList;

public class CacheFile {
	ArrayList<String> list = new ArrayList<String>();

	public CacheFile(){
		initData();
	}
	
	private void initData() {
		// TODO Auto-generated method stub
		list.add("Leave.xls");
		list.add("Staff.xls");
	}
	
	public void clearCache(){
		for (int i = 0; i < list.size(); i++) {
			String listStr = list.get(i);
			File cacheFile = new File(listStr);
			cacheFile.deleteOnExit();
		}
	}
}
