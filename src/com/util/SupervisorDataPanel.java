/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.data.DataSave;
import com.data.LeaveInfo;
import com.view.MTable;

public class SupervisorDataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable table;
	URL resource;
	ImageIcon icon;
	
	public SupervisorDataPanel(String supName){
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
		
		final JLabel leftLabel = new JLabel();
		leftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		leftLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		resource = SupervisorDataPanel.this.getClass().getResource("/imgs/leftpanel2.JPG");
		icon = new ImageIcon(resource);
		leftLabel.setIcon(icon);
		splitPane.setLeftComponent(leftLabel);

		//get data
		String excelFilePath = "Leave.xls";
		LeaveInfo reader = new LeaveInfo();
		List<Object> listBooks = null;
		
		try {
			listBooks = reader.readBooksFromExcelFile(excelFilePath, supName);
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
		
		//Vector<Object> can contain different type of data(String,double,Boolean.etc)
		Vector<Vector> vecLeave = new Vector<Vector>();
		for (Object info : listBooks) {
			Vector<Object> vecStr = new Vector<Object>();  
			vecStr.add(((LeaveInfo) info).getName());vecStr.add(((LeaveInfo) info).getStartDate());
			vecStr.add(((LeaveInfo) info).getEndDate());vecStr.add(((LeaveInfo) info).getSupervisor());
			vecStr.add(new Boolean (false));
			vecLeave.add(vecStr);
			
//			System.out.println("vecLeave = "+vecLeave);
			tableModel = new DefaultTableModel(vecLeave, columnNames);
//			new DataSave().createXLS(tableModel, "Process.xls");
			
			table = new MTable(tableModel);
			table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table); 
		}
	}
}
