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
		setExtendedState(BaseFrame.MAXIMIZED_BOTH);// ���ô�������󻯴�
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
		
		final JPanel buttonPanel = new JPanel();// �������������
		final GridLayout gridLayout = new GridLayout(1, 0);// ����һ��ˮƽ��ʽ���ֹ���������
		gridLayout.setVgap(6);// ��Ĵ�ֱ���Ϊ6����
		gridLayout.setHgap(6);// ���ˮƽ���Ϊ6����
		buttonPanel.setLayout(gridLayout);// ���ù����������õĲ��ֹ�����Ϊ��ʽ����
		buttonPanel.setBackground(new Color(90, 130, 189));// ���ù��������ı���ɫ
		buttonPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));// ���ù����������õı߿���ʽ		
		topPanel.add(buttonPanel, BorderLayout.CENTER);// �������������ӵ��ϼ������
		
		final JButton recordShortcutKeyButton = new JButton();// �������롰Staff Info���Ŀ�ݰ�ť
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
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");// �������ĸ����
		DefaultMutableTreeNode personnelNode = new DefaultMutableTreeNode("Human Resource");// ��������һ���ӽ��
		personnelNode.add(new DefaultMutableTreeNode("HRStaff"));// ��������Ҷ�ӽ�㲢��ӵ�һ���ӽ��
		personnelNode.add(new DefaultMutableTreeNode("Staff"));
		personnelNode.add(new DefaultMutableTreeNode("Supervisor"));
		personnelNode.add(new DefaultMutableTreeNode("Director"));
		root.add(personnelNode);// ���������һ���ӽ��
		
		DefaultMutableTreeNode helpNode = new DefaultMutableTreeNode("Help?");// ��������һ���ӽ��
		helpNode.add(new DefaultMutableTreeNode("About"));
		helpNode.add(new DefaultMutableTreeNode("Contact"));
		helpNode.add(new DefaultMutableTreeNode("Polyu"));
		root.add(helpNode);
		
		DefaultTreeModel treeModel = new DefaultTreeModel(root);// ͨ���������󴴽���ģ�Ͷ���

		tree = new JTree(treeModel);// ͨ����ģ�Ͷ��󴴽�������
		tree.setBackground(Color.WHITE);// �������ı���ɫ
		tree.setRootVisible(false);// ���ò���ʾ���ĸ���㣬Ĭ��Ϊ��ʾ����true
		tree.setRowHeight(24);// ���ø����ĸ߶�Ϊ27����
		tree.setFont(new Font("����", Font.BOLD, 14));// ���ý���������ʽ

		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();// ����һ�����Ļ��ƶ���
		// renderer.setLeafIcon(null);// ����Ҷ�ӽ�㲻����ͼ��
		renderer.setClosedIcon(null);// ���ý���۵�ʱ������ͼ��
		renderer.setOpenIcon(null);// ���ý��չ��ʱ������ͼ��
		tree.setCellRenderer(renderer);// �����Ļ��ƶ������õ�����
		int count = root.getChildCount();// ���һ����������
		for (int i = 0; i < count; i++) {// ��������һ�����
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) root
					.getChildAt(i);// ���ָ������λ�õ�һ��������
			TreePath path = new TreePath(node.getPath());// ��ý������·��
			tree.expandPath(path);// չ���ý��
		}
		tree.addTreeSelectionListener(new TreeSelectionListener() {// ��������ѡȡ�¼�
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
