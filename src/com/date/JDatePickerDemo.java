package com.date;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * This program demonstrates how to use JDatePicker to display a calendar 
 * component in a Swing program.
 * @author www.codejava.net
 *
 */
public class JDatePickerDemo extends JFrame implements ActionListener {
	
	private JDatePickerImpl datePicker;
	
	public JDatePickerDemo() {
		UtilDateModel model = new UtilDateModel();
		model.setDate(1990, 8, 24);
		model.setSelected(true);
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		add(datePicker);
		
		JButton buttonOK = new JButton("OK");
		buttonOK.addActionListener(this);
		
		add(buttonOK);
		
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		Date selectedDate = (Date) datePicker.getModel().getValue();
		JOptionPane.showMessageDialog(this, "The selected date is " + selectedDate);
	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				new JDatePickerDemo().setVisible(true);
//			}
//		});
//	}
}