package com.view;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.data.DataSave;

public class TableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean DEBUG = true;
	private Object[][] data;
	private DefaultTableModel tableModel;
	
	private String[] columnNames = {"Name",
			"Superviosr",
	        "Endorse?"};
	
	public  TableModel(Object[][] data){
//		for (int i = 0; i < data.length; i++) {
//			System.out.print("    row " + i + ":");
//			for (int j1 = 0; j1 < 2; j1++) {
//				System.out.print("  " + data[i][j1]);
//			}
//			System.out.println();
//		}
//		System.out.println("--------------------------");
		this.data = data;
		tableModel = new DefaultTableModel(data, columnNames);
		new DataSave().createXLS(tableModel, "Progress.xls");
	}
	
//    private Object[][] data = {
////    		{"sundy", "2016-4-1","2016-5-1", "James", new Boolean(false)},
////    {"John", "Doe",  "Rowing", new Integer(3), new Boolean(true)},
////    {"Sue", "Black", "Knitting", new Integer(2), new Boolean(false)},
////    {"Jane", "White","Speed reading", new Integer(20), new Boolean(false)},
//    		{"sundy", "2016-4-1","2016-5-1", "James", new Boolean(false)}
//    };
    
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

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

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                               + " to " + value
                               + " (an instance of "
                               + value.getClass() + ")");
        }

        data[row][col] = value;
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
        
        tableModel = new DefaultTableModel(data, columnNames);
		new DataSave().createXLS(tableModel, "Progress.xls"); 
    }   
}
