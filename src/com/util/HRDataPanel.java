package com.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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
	
	public HRDataPanel(){
		super();
		setLayout(new BorderLayout());
		
		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);
		
		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(130);
		add(splitPane, BorderLayout.CENTER);

		final JPanel treePanel = new JPanel();
		treePanel.setBackground(Color.WHITE);
		splitPane.setLeftComponent(treePanel);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(30);
		treePanel.setLayout(flowLayout);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		root.add(new DefaultMutableTreeNode(" Staff  Info "));
		root.add(new DefaultMutableTreeNode(" Leave Info"));

		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
//				tableValueV.removeAllElements();
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				String name = (String) treeNode.getUserObject();
				name = name.trim().replace(" ", "");
				tableModel.setDataVector(tableValueV, tableColumnV);
				if (table.getRowCount() > 0)
					table.setRowSelectionInterval(0, 0);
			}
		});
		
		DefaultTreeCellRenderer treeCellRenderer = new DefaultTreeCellRenderer();// 设置结点的图标、字体和背景色
		treeCellRenderer.setLeafIcon(new ImageIcon());// 设置叶子结点的图标
		treeCellRenderer.setClosedIcon(new ImageIcon());// 设置结点折叠时的图标
		treeCellRenderer.setOpenIcon(new ImageIcon());// 设置结点展开时的图标
		tree.setCellRenderer(treeCellRenderer);
		tree.setRowHeight(30);
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		treePanel.add(tree);

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
				// TODO Auto-generated method stub
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (treeNode == null) {
					JOptionPane.showMessageDialog(null, "Please Chose one Item!", "Attention",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					String item = (String) treeNode.getUserObject();
					item = item.trim().replace(" ", "");
					int row = table.getRowCount();
					if (table.getColumnCount() == 5) {
						AddAccountItemDialog aaid;
//						Vector vector;
						if (item.equals("Staff Info")) {
							aaid = new AddAccountItemDialog(true, item);
						} else {// Other items
							aaid = new AddAccountItemDialog(false, item);
						}
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
			}
		});
		addButton.setText("Add Staff");
		panel.add(addButton);
		
		final JButton delButton = new JButton();
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (treeNode == null) {
					JOptionPane.showMessageDialog(null, "Please choose the info！", "Attention",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					String item = (String) treeNode.getUserObject();
					item = item.trim().replace(" ", "");
					int row = table.getSelectedRow();
					String name = table.getValueAt(row, 1).toString();
					int i = JOptionPane.showConfirmDialog(null, "Are you sure to delete“"+ name + "”？", "Attention",
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
			}
		});
		delButton.setText("Delte Staff");
		panel.add(delButton);
	}
	
	public String getparms(String item) {
		String input = "";
		while (input != null && input.length() == 0) {
			input = JOptionPane.showInputDialog(null, "Please input" + item + "：",
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
									+ "already exist！", "Attention",
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
						JOptionPane.showMessageDialog(null, "The" + item + "already exist！", "Attention",
								JOptionPane.INFORMATION_MESSAGE);
						item = "";
					}
				}
			}
		}
		return item;
	}
}
