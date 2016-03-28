package com.util;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddAccountItemDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JComboBox typeComboBox;
	private JComboBox bossComboBox;
	private JTextField nameTextField;
	private JTextField ageTextField;
	private Vector vector;
	private ArrayList<String>  strArray;
	private Iterator<String> iter;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			AddAccountItemDialog dialog = new AddAccountItemDialog(true, "");
			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog
	 */
	public AddAccountItemDialog(final boolean isTimecard, final String item) {
		super();
		getContentPane().setLayout(new GridBagLayout());
		setTitle("Add " + item);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 300, 230);

		final JLabel nameLabel = new JLabel();
		nameLabel.setText("Name£º");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		getContentPane().add(nameLabel, gridBagConstraints);

		nameTextField = new JTextField();
		nameTextField.setColumns(11);
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.gridy = 0;
		gridBagConstraints_4.gridx = 1;
		getContentPane().add(nameTextField, gridBagConstraints_4);
		
		final JLabel typeLabel = new JLabel();
		typeLabel.setText("Age£º");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 0;
		getContentPane().add(typeLabel, gridBagConstraints_1);

		ageTextField = new JTextField();
		ageTextField.setColumns(11);
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_5.gridy = 1;
		gridBagConstraints_5.gridx = 1;
		getContentPane().add(ageTextField, gridBagConstraints_5);

		final JLabel typeLabel1 = new JLabel();
		typeLabel1.setText("Title£º");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_2.gridy = 2;
		gridBagConstraints_2.gridx = 0;
		getContentPane().add(typeLabel1, gridBagConstraints_2);

		typeComboBox = new JComboBox();
		typeComboBox.addItem("Please Choose");
		typeComboBox.addItem("Staff");
		typeComboBox.addItem("Supervisor");
		typeComboBox.addItem("Director");
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_6.gridy = 2;
		gridBagConstraints_6.gridx = 1;
		getContentPane().add(typeComboBox, gridBagConstraints_6);
		
		
		
		final JLabel unitLabel = new JLabel();
		unitLabel.setText("Superior£º");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_2.gridy = 3;
		gridBagConstraints_2.gridx = 0;
		getContentPane().add(unitLabel, gridBagConstraints_2);

		bossComboBox = new JComboBox();
		bossComboBox.addItem("Please Choose");
		bossComboBox.addItem("James");
		bossComboBox.addItem("Eric");
		bossComboBox.addItem("Petter");
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_7.gridy = 3;
		gridBagConstraints_7.gridx = 1;
		getContentPane().add(bossComboBox, gridBagConstraints_7);
		
		
		final JPanel panel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setLayout(flowLayout);
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.ipadx = 25;
		gridBagConstraints_8.gridwidth = 2;
		gridBagConstraints_8.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_8.gridy = 4;
		gridBagConstraints_8.gridx = 0;
		getContentPane().add(panel, gridBagConstraints_8);

		final JButton submitButton = new JButton();
		strArray = new ArrayList<String>();
		HRDataPanel dPanel = new HRDataPanel();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Staff Name
				String name = nameTextField.getText();
				if (name.length() == 0 || name.length() > 20) {
					JOptionPane.showMessageDialog(null,
							"Name cannot be null£¡", "Attention",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				dPanel.checkInput(name);				
				
				//Staff Age
				String age = ageTextField.getText();                
				if (age.length() == 0 || age.length() > 20) {
					JOptionPane.showMessageDialog(null,
							"Age cannot be null£¡", "Attention",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				//Staff Title
				String type = typeComboBox.getSelectedItem().toString();
//				type = type.trim().replace(" ", "");
				if (type.equals("Please Choose")) {
					JOptionPane.showMessageDialog(null, "Please choose a right title£¡", "Attention",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				System.out.println("Test Director:"+type);
				if (type.equals("Director")) {
//					if(strArray.contains(type)){
//						JOptionPane.showMessageDialog(null, "Director already exist!", "Attention",
//								JOptionPane.INFORMATION_MESSAGE);
//					}
					iter = strArray.iterator();
					while (iter.hasNext()) {
						System.out.println("coming into this judgement!!!");
						String str = (String) iter.next();
						if (type.equals(str)) {
							JOptionPane.showMessageDialog(null, "Director already exist", "Attention",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}

				//Staff Title
				String superior = bossComboBox.getSelectedItem().toString();
//				type = type.trim().replace(" ", "");
				if (type.equals("Please Choose")) {
					JOptionPane.showMessageDialog(null, "Please allocate a Supevisor£¡", "Attention",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				vector = new Vector();
				vector.add(name);
				vector.add(age);
				vector.add(type);
				vector.add(superior);
				
			}
		});
		submitButton.setText("Finish");
		panel.add(submitButton);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		exitButton.setText("Exit");
		panel.add(exitButton);
		//
	}

	public Vector getVector() {
		return vector;
	}
}
