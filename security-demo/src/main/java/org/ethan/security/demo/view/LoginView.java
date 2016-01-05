package org.ethan.security.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.ethan.security.demo.remote.UserService;

public class LoginView extends JDialog {

	private static final long serialVersionUID = -4720720522882886845L;

	private final JLabel jlblUsername = new JLabel("Username");
	private final JLabel jlblPassword = new JLabel("Password");
	private final JButton jbtOk = new JButton("Login");
	private final JButton jbtCancel = new JButton("Cancel");
	private final JTextField jtfUsername = new JTextField(15);
	private final JPasswordField jpfPassword = new JPasswordField();

	private final JLabel jlblStatus = new JLabel(" ");

	public LoginView(final JFrame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		initListeners();
	}

	private void initComponents() {
		JPanel p3 = new JPanel(new GridLayout(2, 1));
		p3.add(jlblUsername);
		p3.add(jlblPassword);

		JPanel p4 = new JPanel(new GridLayout(2, 1));
		p4.add(jtfUsername);
		p4.add(jpfPassword);

		JPanel p1 = new JPanel();
		p1.add(p3);
		p1.add(p4);

		JPanel p2 = new JPanel();
		p2.add(jbtOk);
		p2.add(jbtCancel);

		JPanel p5 = new JPanel(new BorderLayout());
		p5.add(p2, BorderLayout.CENTER);
		p5.add(jlblStatus, BorderLayout.NORTH);
		jlblStatus.setForeground(Color.RED);
		jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);

		setLayout(new BorderLayout());
		add(p1, BorderLayout.CENTER);
		add(p5, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void initListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		jbtOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = jtfUsername.getText();
				char[] password = jpfPassword.getPassword();
				boolean isSucceed = UserService.getInstance().login(username, password);
				if (isSucceed) {

					SuccessView parent = (SuccessView) getParent();
					parent.setUserName(jtfUsername.getText());
					parent.setVisible(true);
					setVisible(false);
				} else {
					jlblStatus.setText("Invalid username or password");
				}

			}
		});

		jbtCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				((JFrame) getParent()).dispose();
				System.exit(0);
			}
		});
	}
}
