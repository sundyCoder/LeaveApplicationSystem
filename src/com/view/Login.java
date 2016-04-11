/*
 * Author: sundy
 * e-mail: sundycoder@gmail.com
 * Date:   March 27,2016
 */
package com.view;

import javax.swing.*;

import com.data.CacheFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	static Login loginPoint;
	String supName;
	
	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

    public Login() {
        super("Human Resource Management System");
        this.setSize(350, 300);
        this.setLocation(500, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Leave Application System"));
        aPanel.add(topPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridLayout(8, 3, 5, 10));
        for (int i = 0; i < 6; i++) {
            centerPanel.add(new JPanel());
        }
        centerPanel.add(new JLabel("Username", SwingConstants.RIGHT));
        final JTextField usernameTextField = new JTextField("", 20);
        centerPanel.add(usernameTextField);
        centerPanel.add(new JPanel());
        centerPanel.add(new JLabel("Password", SwingConstants.RIGHT));
        final JPasswordField passwordTextField = new JPasswordField("", 20);
        centerPanel.add(passwordTextField);
        centerPanel.add(new JPanel());
        centerPanel.add(new JPanel());
        final JButton button = new JButton("Login");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String userNameText = usernameTextField.getText();
            	 if (userNameText.equals("staff") && passwordTextField.getText().equals("123456")) {
            		 loginPoint.setVisible(false);
            		 clear();   //clear the cache data 
                     MainFrame frame = new MainFrame();
 					 frame.setVisible(true);
                 } else {
                     JOptionPane.showMessageDialog(Login.this, "Wrong username or password!");
                 }
            }

			private void clear() {
				// TODO Auto-generated method stub
				new CacheFile().clearCache();
			}
        });
        centerPanel.add(button);
        centerPanel.add(new JPanel());
        for (int i = 0; i < 9; i++) {
            centerPanel.add(new JPanel());
        }
        aPanel.add(centerPanel, BorderLayout.CENTER);

        this.add(aPanel);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	loginPoint = new Login();
	}
}
