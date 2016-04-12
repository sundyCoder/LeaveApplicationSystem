/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.view;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MTable extends JTable {
	private static final long serialVersionUID = 1L;

	public MTable(DefaultTableModel tableModel) {
		super(tableModel);
	}

	@Override
	// 使表格4列之前的不可编辑
	public boolean isCellEditable(int row, int column) {
		  if (column < 4) {
	            return false;
	        } else {
	            return true;
	        }
	}
	
	 public Class getColumnClass(int c) {
         return getValueAt(0, c).getClass();
     }
	 
	 

	@Override
	// 使表格列不允许重新排列
	public JTableHeader getTableHeader() {
		JTableHeader tableHeader = super.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		return tableHeader;
	}
}
