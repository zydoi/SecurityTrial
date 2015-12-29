package org.ethan.security.demo.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SuccessView extends JFrame {
	private static final long serialVersionUID = -4720720522882886845L;

    private JPanel contentPanel;
	
	private JLabel lblMessage;
	
	public SuccessView() {
		super();
		setSize(300, 200);
		
		contentPanel = new JPanel();
		
		lblMessage = new JLabel();
		lblMessage.setVerticalAlignment(SwingConstants.TOP);
		lblMessage.setHorizontalAlignment(SwingConstants.LEFT);
		lblMessage.setBounds(15, 20, 280, 60);
		contentPanel.add(lblMessage, BorderLayout.CENTER);
		getContentPane().add(contentPanel);
		
		LoginView loginView = new LoginView(this, true);
		loginView.setVisible(true);
	}
	
	public void setUserName(String name) {
		lblMessage.setText(name + " logged in successfully.");
	}
}
