/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import com.data.DataSave;
import com.data.StaffRelation;
import com.view.MTable;
import com.view.TableModel;

public class LeaveProcessPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private MTable table;
	private Vector<String> tableColumnV;
	private Vector<Vector> tableValueV;
	private Vector vector;
	private JTree tree;
	private Container treePanel;
	URL resource;
	ImageIcon icon;
	
	public LeaveProcessPanel(){
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
		resource = LeaveProcessPanel.this.getClass().getResource("/imgs/leftpanel2.JPG");
		icon = new ImageIcon(resource);
		leftLabel.setIcon(icon);
		splitPane.setLeftComponent(leftLabel);
		
		
		final JButton dealButton = new JButton();
		dealButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				StaffRelation staff = new StaffRelation();
				try {
					staff.readRelFromStaffInfo();
					staff.readRelFromLeaveInfo();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Vector<String> leaveList = staff.getAllPeople();
//				System.out.println("leaveList="+leaveList);
				
				Vector<Vector> vvList = new Vector<Vector>();
				
				for (int i = 0; i < leaveList.size(); i++) {
					String name = leaveList.get(i);
					System.out.println("sundy:"+name);
					Vector<String> supList = staff.getSupList(name);
					System.out.println("supList="+supList);
					for (int j = 0; j < supList.size(); j++) {
						Vector<String> vStr = new Vector<String>();
						vStr.add(name);
						vStr.add(supList.get(j));
						vvList.add(vStr);
					}
				}
				
				Object[][] data = {null,null,null,null,null,null}; //³õÊ¼»¯‚€”µ
				int k = 0;
				for (int i = 0; i < vvList.size(); i++) {
					int j = 0;
					Vector ob = vvList.elementAt(i);
					String name =  (String) ob.elementAt(0);
					String sup = (String) ob.elementAt(1);
					Boolean bl = new Boolean(false);
					System.out.println("oo:"+i+" "+name+" "+sup+" "+bl);
					data[k++] = new Object[]{name,sup,bl};
				}
		       
				JTable table = new JTable(new TableModel(data));
				table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(table); 
			}
		});
		dealButton.setText("   Deal Leave   ");
		panel.add(dealButton);
	}
}
