/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.test;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class TableModelTest extends AbstractTableModel {
	private boolean DEBUG = true;
	
	private String[] columnNames;
	private Vector<Vector> data;
	
	public TableModelTest(String[] columnNames,Vector<Vector> data){
		this.columnNames = columnNames;
		this.data = data;
	}

    public int getColumnCount() {
        return columnNames.length;
    }

//    public int getRowCount() {
//        return data.length;
//    }
//
//    public String getColumnName(int col) {
//        return columnNames[col];
//    }
//
//    public Object getValueAt(int row, int col) {
//        return data[row][col];
//    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
//    public void setValueAt(Object value, int row, int col) {
//        if (DEBUG) {
//            System.out.println("Setting value at " + row + "," + col
//                               + " to " + value
//                               + " (an instance of "
//                               + value.getClass() + ")");
//        }
//
//        data[row][col] = value;
//        fireTableCellUpdated(row, col);
//
//        if (DEBUG) {
//            System.out.println("New value of data:");
//            printDebugData();
//        }
//    }
//
//    private void printDebugData() {
//        int numRows = getRowCount();
//        int numCols = getColumnCount();
//
//        for (int i=0; i < numRows; i++) {
//            System.out.print("    row " + i + ":");
//            for (int j=0; j < numCols; j++) {
//                System.out.print("  " + data[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------");
//    }
}