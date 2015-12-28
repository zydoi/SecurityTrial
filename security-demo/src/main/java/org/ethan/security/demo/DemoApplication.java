package org.ethan.security.demo;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.ethan.security.demo.view.SuccessView;


public class DemoApplication {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
                JFrame frame = new SuccessView();
                frame.setTitle("Logged In");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
			}
		});

	}
}
