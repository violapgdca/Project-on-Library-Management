package omepage;

import java.awt.EventQueue;
//import org.mindrot.jbcrypt.BCrypt;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class adminlogmin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */

	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminlogmin frame = new adminlogmin();
					frame.setLocationRelativeTo(null);
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
	public adminlogmin() {
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel Panlogin = new JPanel();
		Panlogin.setBackground(new Color(255, 255, 204));
		Panlogin.setBounds(244, 0, 247, 319);
		contentPane.add(Panlogin);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(118, 5, 1, 1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setBounds(92, 11, 55, 22);
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 18));

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(20, 59, 65, 33);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));

		txtUsername = new JTextField();
		txtUsername.setBounds(31, 103, 180, 29);
		txtUsername.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(31, 194, 180, 28);
		passwordField.setToolTipText("Password");

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(20, 150, 99, 33);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JButton btnlogin = new JButton("LOGIN");
		btnlogin.setBounds(23, 268, 89, 23);
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String un = txtUsername.getText(); // Storing the value in variable.
				String pas = passwordField.getText();
				try {
					String encrypt = null;
					MessageDigest m = MessageDigest.getInstance("MD5"); // MessageDigest instance for MD5.
					m.update(pas.getBytes()); // Adding password bytes to digest using MD5 update() method.
					byte[] bytes = m.digest(); // Converting the hash value into bytes.

					StringBuilder s = new StringBuilder(); // Byte array has bytes in decimal form. Converting it into
															// hexadecimal format.
					for (int i = 0; i < bytes.length; i++) {
						s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
					}

				encrypt = s.toString(); // Hashed password in hexadecimal format.

					try {
						Statement stmt = DBhandler.getConnection().createStatement(); // Database connection
						String sql = "SELECT * FROM user";		//Calling the data from user table.
						
						stmt.executeQuery(sql);

						ResultSet resultSet = stmt.executeQuery(sql);
						
						while (resultSet.next()) {

							String username = resultSet.getString("Username");		//Storing the value from the database.
							String password = resultSet.getString("Password");

							if (un.equals(username) && encrypt.equals(password)) {		//Matching the values
								JOptionPane.showMessageDialog(null, "Login Successful!");
								manage window = new manage();
								window.MANAGE.setVisible(true); // Moving to Next page.
								dispose();
							} 
							else {
								JOptionPane.showMessageDialog(null, "Invalid username or password");
							}

						}
						stmt.close();			//Closing the connection
					} catch (SQLException error) {
						JOptionPane.showMessageDialog(null, "Database is not connected");		//If database is not connected.
						error.printStackTrace();
					}
				} catch (NoSuchAlgorithmException er) {
					 JOptionPane.showMessageDialog(null, "Invalid username or password");
					 er.printStackTrace();
				}
			}
		});
		btnlogin.setBackground(new Color(255, 0, 0));

		JButton btnCancle = new JButton("CANCEL");
		btnCancle.setBounds(122, 268, 89, 23);
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage n = new homepage();
				n.setVisible(true);
				dispose();
			}

		});
		btnCancle.setBackground(new Color(255, 0, 0));
		Panlogin.setLayout(null);
		Panlogin.add(panel_1);
		Panlogin.add(lblNewLabel_1);
		Panlogin.add(lblUsername);
		Panlogin.add(lblPassword);
		Panlogin.add(btnlogin);
		Panlogin.add(btnCancle);
		Panlogin.add(txtUsername);
		Panlogin.add(passwordField);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Viola\\Downloads\\Lib1.jpg"));
		lblNewLabel_2.setBounds(0, 0, 247, 319);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Viola\\Downloads\\Lib1.jpg"));
		lblNewLabel.setBounds(489, 0, 240, 319);
		contentPane.add(lblNewLabel);
	}
}
