package com.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.data.DataSave;
import com.view.MTable;

public class StaffDataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private MTable table;
	private Vector<String> tableColumnV;
	private Vector<Vector> tableValueV;
	private Vector vector;
	private JTree tree;
	private Container treePanel;
	
	public StaffDataPanel(){
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

		tableColumnV = new Vector<String>();
		tableColumnV.add("Name");
		tableColumnV.add("Start Date");
		tableColumnV.add("End  Date");
		tableColumnV.add("Superior");

		tableValueV = new Vector<Vector>();
		tableModel = new DefaultTableModel(tableValueV, tableColumnV);

		table = new MTable(tableModel);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table); 
		
		final JButton LeaveButton = new JButton();
		LeaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					int row = table.getRowCount();
					System.out.println("row = "+row);
					if (table.getColumnCount() == 4) {
						LeaveApplicationForm aaid;
   					    aaid = new LeaveApplicationForm(true, "Leave");
						Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
						aaid.setBounds((size.width - 680) / 2,(size.height - 620) / 2, 480, 420);
						aaid.setVisible(true);
						vector = aaid.getVector();
						
						if (vector != null) {
							Vector tableRowV = new Vector(vector);
							System.out.println("vector:"+tableRowV);
//							tableRowV.insertElementAt(row + 1, 0);
							tableModel.addRow(tableRowV);
							table.setRowSelectionInterval(row, row);
							
							//save the staff information to Satff.xls
						    new DataSave().createXLS(tableModel, "Leave.xls");
						}
						aaid.dispose();
					}
			}
		});
		LeaveButton.setText("      Leave Application   ");
		panel.add(LeaveButton);
		
		final JButton delButton = new JButton();
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					int row = table.getSelectedRow();
					String name = table.getValueAt(row, 1).toString();
					int i = JOptionPane.showConfirmDialog(null, "Are you sure to delete¡°"+ name + "¡±£¿", "Attention",
							JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						System.out.println("Ready to delete!");
						tableModel.removeRow(row);
						int rowCount = table.getRowCount();
						if (row < rowCount) {
							for (int j = row; j < rowCount; j++) {
								table.setValueAt(j + 1, j, 0);
							}
							table.setRowSelectionInterval(row, row);
						} 
						else if(rowCount == 0){
							System.out.println("row = "+row + "rowcount = "+rowCount);
						}
						else{
							table.setRowSelectionInterval(0, rowCount - 1);
						}
					}
				}
		});
		delButton.setText("Modify Info");
		panel.add(delButton);
	}
}
