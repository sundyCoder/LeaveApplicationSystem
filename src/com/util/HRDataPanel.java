/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.data.DataSave;
import com.data.HSSFReadWrite;
import com.data.StaffInfo;
import com.view.MTable;

public class HRDataPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private MTable table;
	private Vector<String> tableColumnV;
	private Vector<Vector> tableValueV;
	private Vector vector;
	private URL resource;
	private ImageIcon icon;
	
	public HRDataPanel() {
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
		resource = HRDataPanel.this.getClass().getResource("/imgs/leftpanel0.JPG");
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
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		ArrayList<String> stList = new ArrayList<String>();

		final JButton showAll = new JButton();
		showAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String excelFilePath = "Staff.xls";
				StaffInfo reader = new StaffInfo();
				List<Object> listBooks = null;
				try {
					listBooks = reader.readBooksFromExcelFile(excelFilePath, "Any");
					System.out.println("listBooks = " + listBooks);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Vector<String> columnNames = new Vector<String>();
				columnNames.add("ID");
				columnNames.add("Name");
				columnNames.add("Age");
				columnNames.add("Title");
				columnNames.add("Supervisor");

				// Vector<Object> can contain different type of
				// data(String,double,Boolean.etc)
				Vector<Vector> vecLeave = new Vector<Vector>();
				for (Object info : listBooks) {
					Vector<Object> vecStr = new Vector<Object>();
					vecStr.add(((StaffInfo) info).getID());
					vecStr.add(((StaffInfo) info).getName());
					vecStr.add(((StaffInfo) info).getAge());
					vecStr.add(((StaffInfo) info).getTitle());
					vecStr.add(((StaffInfo) info).getSupervisor());
					vecLeave.add(vecStr);
					// System.out.println("vecLeave = "+vecLeave);
					tableModel = new DefaultTableModel(vecLeave, columnNames);

					table = new MTable(tableModel);
					table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(table);
				}
			}
		});
		showAll.setText("Show All");
		panel.add(showAll);

		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getRowCount();

				if (table.getColumnCount() == 5) {
					AddAccountItemDialog aaid;
					aaid = new AddAccountItemDialog();
					Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
					aaid.setBounds((size.width - 280) / 2, (size.height - 220) / 2, 280, 220);
					aaid.setVisible(true);
					vector = aaid.getVector();
					if (vector != null) {
						Vector tableRowV = new Vector(vector);
						// System.out.println("vector = "+tableRowV);
						String input = (String) vector.elementAt(0);   //name cannot be repeated
						String ckStr = checkInput(input);
						
						input = (String) vector.elementAt(2);  //check if there are more than one director
						ckStr = checkInput(input);
						
						if (ckStr != null) {
							if (!ckStr.equals("exist")) {
								tableRowV.insertElementAt(row + 1, 0);
								tableModel.addRow(tableRowV);
								table.setRowSelectionInterval(row, row);

								// save the staff information to Satff.xls
								new DataSave().createXLS(tableModel, "Staff.xls");
							}
						}
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
				int row = table.getSelectedRow();
				System.out.println("row = " + row);
				if (row != -1) {
					String name = table.getValueAt(row, 1).toString();
					int i = JOptionPane.showConfirmDialog(null, "Are you sure to delete¡°" + name + "¡±£¿", "Attention",
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
							//delete the item from the xls
						} else if (rowCount == 0) {
							// System.out.println("row = "+row + "rowcount =
							// "+rowCount);
							// table.setRowSelectionInterval(0,0);
							//delete the item from the xls
						} else {
							
							table.setRowSelectionInterval(0, rowCount - 1);
							//delete the item from the xls
						}
					}
					//delete the item from XLS
					HSSFWorkbook wb;
					try {
						wb = HSSFReadWrite.readFile("Staff.xls");
						FileOutputStream stream = new FileOutputStream("Staff.xls");
						HSSFSheet sheet = wb.getSheetAt(0);
						HSSFRow rowXLS = sheet.getRow(row);

						sheet.removeRow(rowXLS);
						wb.write(stream);
						stream.close();
						wb.close();
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else { // row == 0
					JOptionPane.showMessageDialog(null, "No Staff infomations!");
				}
			}
			// }
		});
		delButton.setText("Delte Staff");
		panel.add(delButton);

	}

	public String checkInput(String item) {
		System.out.println(" Before Judge Item:"+item);
		if (item == null) {
			System.out.println("null,Checking the input!!!");
			if (item.length() == 0) {
				JOptionPane.showMessageDialog(null, "Please input" + item + "Information cannot be null", "Attention",
						JOptionPane.INFORMATION_MESSAGE);
			}
			return null;
		} else {
			System.out.println("Checking the input!!!");
			for (int i = 0; i < table.getRowCount(); i++) {
				Object valueAt = table.getValueAt(i, 1);
				Object bossAt = table.getValueAt(i, 3);
				
				if (item.equals(valueAt.toString()) ) { //bossAt.toString().equals("Director")
					table.setRowSelectionInterval(i, i);
					JOptionPane.showMessageDialog(null, "The " + "["+item + "]"+ "already exist£¡", "Attention",
							JOptionPane.INFORMATION_MESSAGE);
					return "exist";
				}
				System.out.println(" Before Judge Item2:"+item);
				if (item.equals("Director")) {
					System.out.println("xxxx="+bossAt);
					if (item.equals(bossAt.toString())) { // bossAt.toString().equals("Director")
						table.setRowSelectionInterval(i, i);
						JOptionPane.showMessageDialog(null, "The " + "[" + item + "]" + "already exist£¡", "Attention",
								JOptionPane.INFORMATION_MESSAGE);
						return "exist";
					}
				}
			}
		}
		return "good";
	}
}
