package com.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.data.DataSave;
import com.view.MTable;

public class HRDataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private MTable table;
	private JTree tree;
	private Vector<String> tableColumnV;
	private Vector<Vector> tableValueV;
	Vector vector;
	URL resource;
	ImageIcon icon;
	
	
	public HRDataPanel(){
		super();
		setLayout(new BorderLayout());
		
		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		
		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(130);
		add(splitPane, BorderLayout.CENTER);
		
		final JLabel leftLabel = new JLabel();
		leftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		leftLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		resource = HRDataPanel.this.getClass().getResource("/img/leftpanel0.JPG");
		icon = new ImageIcon(resource);
		leftLabel.setIcon(icon);
		splitPane.setLeftComponent(leftLabel);
		setVisible(true);		

		final JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		tableColumnV = new Vector<String>();
		tableColumnV.add("ID");
		tableColumnV.add("Name");
		tableColumnV.add("Age");
		tableColumnV.add("Title");
		tableColumnV.add("Superior");

		tableValueV = new Vector<Vector>();
		tableModel = new DefaultTableModel(tableValueV, tableColumnV);

		table = new MTable(tableModel);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table); 
		
		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					int row = table.getRowCount();
					System.out.println("row = "+row);
					if (table.getColumnCount() == 5) {
						AddAccountItemDialog aaid;
						aaid = new AddAccountItemDialog(true, "");
						Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
						aaid.setBounds((size.width - 280) / 2,(size.height - 220) / 2, 280, 220);
						aaid.setVisible(true);
						vector = aaid.getVector();
						
						if (vector != null) {
							Vector tableRowV = new Vector(vector);
							System.out.println("vector:"+tableRowV);
							//tableRowV.remove(2);
							tableRowV.insertElementAt(row + 1, 0);
							tableModel.addRow(tableRowV);
							table.setRowSelectionInterval(row, row);
							
							//save the staff information to Satff.xls
						    new DataSave().createXLS(tableModel, "Staff.xls");
						}
						aaid.dispose();
					}
				}
		});
		addButton.setText("Add Staff");
		panel.add(addButton);
		
		final JButton delButton = new JButton();
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree
//						.getLastSelectedPathComponent();
//				if (treeNode == null) {
//					JOptionPane.showMessageDialog(null, "Please choose the info£¡", "Attention",
//							JOptionPane.INFORMATION_MESSAGE);
//					return;
//				} else {
//					String item = (String) treeNode.getUserObject();
//					item = item.trim().replace(" ", "");
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
//							System.out.println("row = "+row + "rowcount = "+rowCount);
//							table.setRowSelectionInterval(0,0);
						}
						else{
							table.setRowSelectionInterval(0, rowCount - 1);
						}
					}
				}
//			}
		});
		delButton.setText("Delte Staff");
		panel.add(delButton);
	}
	
	public String getparms(String item) {
		String input = "";
		while (input != null && input.length() == 0) {
			input = JOptionPane.showInputDialog(null, "Please input" + item + "£º",
					"add" + item, JOptionPane.INFORMATION_MESSAGE);
			if (input != null) {
				if (input.length() == 0) {
					JOptionPane.showMessageDialog(null, "Please input" + item
							+ "Information cannot be null", "Attention",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					for (int i = 0; i < table.getRowCount(); i++) {
						Object valueAt = table.getValueAt(i, 1);
						if (input.equals(valueAt.toString())) {
							table.setRowSelectionInterval(i, i);
							JOptionPane.showMessageDialog(null, "The" + item
									+ "already exist£¡", "Attention",
									JOptionPane.INFORMATION_MESSAGE);
							input = "";
						}
					}
				}
			}
		}
		return input;
	}
	
	public String checkInput(String item) {
		if (item == null) {
			System.out.println("null,Checking the input!!!");
			if (item.length() == 0) {
				JOptionPane.showMessageDialog(null, "Please input" + item + "Information cannot be null", "Attention",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				System.out.println("Checking the input!!!");
				for (int i = 0; i < table.getRowCount(); i++) {
					Object valueAt = table.getValueAt(i, 1);
					System.out.println("valueAt = "+valueAt);
					if (item.equals(valueAt.toString())) {
						table.setRowSelectionInterval(i, i);
						JOptionPane.showMessageDialog(null, "The" + item + "already exist£¡", "Attention",
								JOptionPane.INFORMATION_MESSAGE);
						item = "";
					}
				}
			}
		}
		return item;
	}
}
