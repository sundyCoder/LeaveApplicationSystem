/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.test;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.data.LeaveInfo;
import com.view.MTable;

public class SupervisorTableTest extends JPanel{
	private static final long serialVersionUID = 1L;
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private DefaultTableModel tableModel;
	private MTable table;
	public SupervisorTableTest(Vector<String> columnNames,Vector<Vector> data) {
		super(new GridLayout(1, 0));
		tableModel = new DefaultTableModel(data,columnNames);
		table = new MTable(tableModel);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportView(table); 
		add(scrollPane);
	}

	private static void createAndShowGUI(Vector<String> columnNames,Vector<Vector> vecLeave) {
		// Create and set up the window.
		JFrame frame = new JFrame("TableDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		SupervisorTableTest newContentPane = new SupervisorTableTest(columnNames,vecLeave);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void createTable(){
		String excelFilePath = "Leave.xls";
		RetrieveLeaveInfoTest reader = new RetrieveLeaveInfoTest();
		List<Object> listBooks = null;
		try {
			listBooks = reader.readBooksFromExcelFile(excelFilePath, "Eric");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Name");
		columnNames.add("Start Date");
		columnNames.add("End Date");
		columnNames.add("Supervisor");
		columnNames.add("Endorse?");
		
		Vector<Vector> vecLeave = new Vector<Vector>();
		Vector<Object> vecStr = new Vector<Object>();
		for (Object info : listBooks) {
			vecStr.add(((LeaveInfo) info).getName());
			vecStr.add(((LeaveInfo) info).getStartDate());
			vecStr.add(((LeaveInfo) info).getEndDate());
			vecStr.add(((LeaveInfo) info).getSupervisor());
			vecStr.add(new Boolean(false));
		}
		vecLeave.add(vecStr);
		System.out.println("vecLeave = "+vecLeave);
		createAndShowGUI(columnNames,vecLeave);
	}

//	public static void main(String[] args) {
//		// Schedule a job for the event-dispatching thread:
//		// creating and showing this application's GUI.
//		javax.swing.SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				createTable();
//			}
//		});
//	}
}
