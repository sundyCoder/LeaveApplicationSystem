package com.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SupervisorLogin extends JFrame {
	String supName;
	
	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

    public SupervisorLogin() {
        super("Human Resource Management System");
        this.setSize(350, 300);
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel aPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Supervisor Login"));
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
        JButton button = new JButton("Login");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String userNameText = usernameTextField.getText();
            	setSupName(userNameText);  
                if (userNameText.length() >= 3 && passwordTextField.getText().equals("123456")) {
                	int i = JOptionPane.showConfirmDialog(null, userNameText+",you have some information to deal with!", 
                			"Attention",JOptionPane.OK_CANCEL_OPTION);
                	if(i == 0){
                		System.out.println(userNameText);
                		/*If click ok, then the staff leave list will show on the table*/
                		Window window = SwingUtilities.windowForComponent(button);
    					window.setVisible(false);  /*close the login dialog*/
                	}
                    
                } else {
                    JOptionPane.showMessageDialog(SupervisorLogin.this, "Wrong username or password!");
                }
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
}
