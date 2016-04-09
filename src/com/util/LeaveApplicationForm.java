/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.util;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.date.DateLabelFormatter;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


public class LeaveApplicationForm extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JComboBox bossComboBox;
	private JTextField nameTextField;
	private Vector vector;
	ArrayList<String>  strArray;
	Iterator<String> iter;
	private JDatePickerImpl startDatePicker;
	private JDatePickerImpl endDatePicker;
	private Date startDate;
	private Date endDate;
	
	/**
	 * Launch the application
	 * 
	 * @param args
	 */
//	public static void main(String args[]) {
//		try {
//			LeaveApplicationForm dialog = new LeaveApplicationForm(true, "");
//			dialog.addWindowListener(new WindowAdapter() {
//				public void windowClosing(WindowEvent e) {
//					System.exit(0);
//				}
//			});
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog
	 */
	public LeaveApplicationForm(final boolean isTimecard, final String item) {
		super();
		getContentPane().setLayout(new GridBagLayout());
		setTitle("Add " + item);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 300, 230);

		//Name
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
		
		/*Leave start date*/
		final JLabel leaveLabel = new JLabel();
		leaveLabel.setText("Start Date£º");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 0;
		getContentPane().add(leaveLabel, gridBagConstraints_1);

		final JPanel jsDate = new JPanel();
		jsDate.setLayout(new FlowLayout());
		UtilDateModel model = new UtilDateModel();
		model.setDate(2016, 1, 1);
		model.setSelected(true);
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		startDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		jsDate.add(startDatePicker);
		
		JButton startOK = new JButton("OK");
		startOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startDate = (Date) startDatePicker.getModel().getValue();
				JOptionPane.showMessageDialog(null, "The selected date is " + startDate);
			}
		});
		jsDate.add(startOK);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_5.gridy = 1;
		gridBagConstraints_5.gridx = 1;
		getContentPane().add(jsDate, gridBagConstraints_5);
		
		/*Leave start date*/
		final JLabel endLabel = new JLabel();
		endLabel.setText("End Date£º");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_2.gridy = 2;
		gridBagConstraints_2.gridx = 0;
		getContentPane().add(endLabel, gridBagConstraints_2);

		final JPanel jeDate = new JPanel();
		jeDate.setLayout(new FlowLayout());
		UtilDateModel model2 = new UtilDateModel();
		model2.setDate(2016, 1, 1);
		model2.setSelected(true);
		
		datePanel = new JDatePanelImpl(model2);
		endDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		jeDate.add(endDatePicker);
		
		JButton endOK = new JButton("OK");
		endOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				endDate = (Date) endDatePicker.getModel().getValue();
				JOptionPane.showMessageDialog(null, "The selected date is " + endDate);
			}
		});
		jeDate.add(endOK);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_6.gridy = 2;
		gridBagConstraints_6.gridx = 1;
		getContentPane().add(jeDate, gridBagConstraints_6);
		
		
		final JLabel unitLabel = new JLabel();
		unitLabel.setText("Superior£º");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_3.gridy = 3;
		gridBagConstraints_3.gridx = 0;
		getContentPane().add(unitLabel, gridBagConstraints_3);

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

				//Staff Title
				String superior = bossComboBox.getSelectedItem().toString();
				if (superior.equals("Please Choose")) {
					JOptionPane.showMessageDialog(null, "Please allocate a Supevisor£¡", "Attention",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				vector = new Vector();
				vector.add(name);
				vector.add(startDate);
				vector.add(endDate);
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
