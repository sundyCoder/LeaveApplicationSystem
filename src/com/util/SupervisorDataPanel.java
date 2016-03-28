package com.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.data.LeaveInfo;
import com.data.RetrieveLeaveInfo;
import com.test.SupervisorTable;
import com.view.MTable;
import com.view.SupervisorLogin;

public class SupervisorDataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;
	
	public SupervisorDataPanel(/*String supName*/){
		super();
		setLayout(new BorderLayout());
		
		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		
		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(130);
		add(splitPane, BorderLayout.CENTER);
		

		final JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		//get data
		String excelFilePath = "Leave.xls";
		RetrieveLeaveInfo reader = new RetrieveLeaveInfo();
		List<Object> listBooks = null;
		try {
			listBooks = reader.readBooksFromExcelFile(excelFilePath, "Eric");
			System.out.println("listBooks = " + listBooks);
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
		//Vector<Object> can contain different type of data(String,double,Boolean.etc)
		Vector<Object> vecStr = new Vector<Object>();  
		for (Object info : listBooks) {
			vecStr.add(((LeaveInfo) info).getName());vecStr.add(((LeaveInfo) info).getStartDate());
			vecStr.add(((LeaveInfo) info).getEndDate());vecStr.add(((LeaveInfo) info).getSupervisor());
			vecStr.add(new Boolean(false));
		}
		vecLeave.add(vecStr);
		System.out.println("vecLeave = "+vecLeave);
		tableModel = new DefaultTableModel(vecLeave, columnNames);

		table = new MTable(tableModel);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table); 
	}
}
