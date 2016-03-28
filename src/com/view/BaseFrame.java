/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.util.HRDataPanel;
import com.util.StaffDataPanel;
import com.util.SupervisorDataPanel;

public class BaseFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTree tree;
	final JPanel rightPanel = new JPanel();
	final JLabel backgroundLabel = new JLabel();
	URL resource;
	ImageIcon icon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaseFrame frame = new BaseFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BaseFrame() {
		setResizable(false);
		setExtendedState(BaseFrame.MAXIMIZED_BOTH);// 设置窗口以最大化打开
		setTitle("Polyu");
		setBounds(0, 0, 950, 700);
		setBounds(0, 0, 1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(0, 100));
		getContentPane().add(topPanel, BorderLayout.NORTH);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		resource = BaseFrame.this.getClass().getResource("/img/logo.JPG");
		icon = new ImageIcon(resource);
		label.setIcon(icon);
		label.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		label.setPreferredSize(new Dimension(160, 0));
		topPanel.add(label, BorderLayout.WEST);
		
		final JPanel buttonPanel = new JPanel();// 创建工具栏面板
		final GridLayout gridLayout = new GridLayout(1, 0);// 创建一个水平箱式布局管理器对象
		gridLayout.setVgap(6);// 箱的垂直间隔为6像素
		gridLayout.setHgap(6);// 箱的水平间隔为6像素
		buttonPanel.setLayout(gridLayout);// 设置工具栏面板采用的布局管理器为箱式布局
		buttonPanel.setBackground(new Color(90, 130, 189));// 设置工具栏面板的背景色
		buttonPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));// 设置工具栏面板采用的边框样式		
		topPanel.add(buttonPanel, BorderLayout.CENTER);// 将工具栏面板添加到上级面板中
		
		final JButton recordShortcutKeyButton = new JButton();// 创建进入“Staff Info”的快捷按钮
		resource = this.getClass().getResource("/img/front.JPG");
		icon = new ImageIcon(resource);
		recordShortcutKeyButton.setIcon(icon);
		buttonPanel.add(recordShortcutKeyButton);
		
		final JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(90, 130, 189));
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		leftPanel.setPreferredSize(new Dimension(160, 0));
		getContentPane().add(leftPanel, BorderLayout.WEST);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");// 创建树的根结点
		DefaultMutableTreeNode personnelNode = new DefaultMutableTreeNode("Human Resource");// 创建树的一级子结点
		personnelNode.add(new DefaultMutableTreeNode("HRStaff"));// 创建树的叶子结点并添加到一级子结点
		personnelNode.add(new DefaultMutableTreeNode("Staff"));
		personnelNode.add(new DefaultMutableTreeNode("Supervisor"));
		personnelNode.add(new DefaultMutableTreeNode("Director"));
		root.add(personnelNode);// 向根结点添加一级子结点
		
		DefaultMutableTreeNode helpNode = new DefaultMutableTreeNode("Help?");// 创建树的一级子结点
		helpNode.add(new DefaultMutableTreeNode("About"));
		helpNode.add(new DefaultMutableTreeNode("Contact"));
		helpNode.add(new DefaultMutableTreeNode("Polyu"));
		root.add(helpNode);
		
		DefaultTreeModel treeModel = new DefaultTreeModel(root);// 通过树结点对象创建树模型对象

		tree = new JTree(treeModel);// 通过树模型对象创建树对象
		tree.setBackground(Color.WHITE);// 设置树的背景色
		tree.setRootVisible(false);// 设置不显示树的根结点，默认为显示，即true
		tree.setRowHeight(24);// 设置各结点的高度为27像素
		tree.setFont(new Font("宋体", Font.BOLD, 14));// 设置结点的字体样式

		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();// 创建一个树的绘制对象
		// renderer.setLeafIcon(null);// 设置叶子结点不采用图标
		renderer.setClosedIcon(null);// 设置结点折叠时不采用图标
		renderer.setOpenIcon(null);// 设置结点展开时不采用图标
		tree.setCellRenderer(renderer);// 将树的绘制对象设置到树中
		int count = root.getChildCount();// 获得一级结点的数量
		for (int i = 0; i < count; i++) {// 遍历树的一级结点
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) root
					.getChildAt(i);// 获得指定索引位置的一级结点对象
			TreePath path = new TreePath(node.getPath());// 获得结点对象的路径
			tree.expandPath(path);// 展开该结点
		}
		tree.addTreeSelectionListener(new TreeSelectionListener() {// 捕获树的选取事件
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				rightPanel.removeAll();
				TreePath treePath = e.getPath();
				int pathCount = treePath.getPathCount() + 1;
				if (pathCount == 2) {
					rightPanel.add(backgroundLabel, BorderLayout.CENTER);
				}else{
					String selectedNode = treePath.getLastPathComponent().toString();
					String parentNode = treePath.getParentPath().getLastPathComponent().toString();
					if (parentNode.equals("Human Resource")) {
						if (selectedNode.equals("HRStaff")) {
							System.out.println("Human Resource->HRStaff");
							rightPanel.setVisible(true);
							rightPanel.add(new HRDataPanel(),BorderLayout.CENTER);
							
						} else if (selectedNode.equals("Staff")) {
							System.out.println("Human Resource->Staff");
							rightPanel.setVisible(true);
							rightPanel.add(new StaffDataPanel(),BorderLayout.CENTER);
							
						} else if (selectedNode.equals("Supervisor")) {
							System.out.println("Human Resource->Supervisor");
							rightPanel.setVisible(false);
							//Login in to address leave application information
//							SupervisorLogin supervisor = new SupervisorLogin();
//							String supName = supervisor.getSupName();
							rightPanel.add(new SupervisorDataPanel(/*supName*/),BorderLayout.CENTER);
							rightPanel.setVisible(true);
							
						} else if (selectedNode.equals("Director")) {
							System.out.println("Human Resource->Staff");
							rightPanel.setVisible(false);
						}
					}else if(parentNode.equals("Help?")){
						if (selectedNode.equals("About")) {
							try {
								java.awt.Desktop.getDesktop().browse(new java.net.URI("https://github.com/sundyCoder/LeaveApplicationSystem"));
							} catch (IOException | URISyntaxException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else if(selectedNode.equals("Contact")){
							
						}else if(selectedNode.equals("Polyu")){
							try {
								java.awt.Desktop.getDesktop().browse(new java.net.URI("http://www.polyu.edu.hk/~hro/"));
							} catch (IOException | URISyntaxException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}
		});
		leftPanel.add(tree);
		
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(0, 0));
		rightPanel.setBackground(new Color(255, 255, 247));
		rightPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		getContentPane().add(rightPanel, BorderLayout.CENTER);
		setBackground();
	}
	
	public void setBackground(){
		resource = this.getClass().getResource("/img/back.JPG");
		icon = new ImageIcon(resource);
		backgroundLabel.setIcon(icon);
		backgroundLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(backgroundLabel, BorderLayout.CENTER);
	}
}
