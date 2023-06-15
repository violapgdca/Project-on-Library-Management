package omepage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class AddStudents extends JFrame {

	private JPanel contentPane;
	private JTextField txtCouse;
	private JTable Student_table;
	private JTextField txtStudent_Name;

	private void clearFields() {
		txtStudent_Name.setText(null);
		txtCouse.setText(null);

	}

	public void loadTable() { // Display the student table from database

		int b;
		try {
			Statement stmt = DBhandler.getConnection().createStatement();
			String query = "SELECT * from student_details";
			ResultSet rs = stmt.executeQuery(query);
			java.sql.ResultSetMetaData rsd = rs.getMetaData();
			b = rsd.getColumnCount();

			DefaultTableModel d = (DefaultTableModel) Student_table.getModel();
			d.setRowCount(0);

			while (rs.next()) {
				Vector<String> v2 = new Vector<String>();
				for (int i = 1; i <= b; i++) {
					v2.add(rs.getString("Student_ID"));
					v2.add(rs.getString("Student_Name"));
					v2.add(rs.getString("Course"));
				}
				d.addRow(v2);
			}

			stmt.close(); // Closing the database
			DBhandler.getConnection().commit();
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not connected.
			error.printStackTrace();
		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudents frame = new AddStudents();
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
	public AddStudents() {
		setTitle("STUDENT DETAILS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 413);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(216, 191, 216));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(0, 0, 729, 50);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("ADD STUDENT DETAILS");
		lblNewLabel_2.setBounds(187, 10, 167, 17);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel_2);

		JButton btnBack = new JButton("BACK");
		btnBack.setBackground(new Color(255, 250, 250));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() { // Moving to home page
			public void actionPerformed(ActionEvent e) {
				manage window = new manage();
				window.MANAGE.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(531, 9, 83, 23);
		panel.add(btnBack);

		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBackground(new Color(255, 250, 250));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Moving to Login page
				adminlogmin frame = new adminlogmin();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(624, 9, 83, 23);
		panel.add(btnLogout);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(216, 191, 216));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(0, 51, 252, 268);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblcourse = new JLabel("Course");
		lblcourse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblcourse.setForeground(new Color(0, 0, 0));
		lblcourse.setBounds(10, 159, 59, 14);
		panel_1.add(lblcourse);

		txtCouse = new JTextField();
		txtCouse.setBounds(79, 156, 163, 34);
		panel_1.add(txtCouse);
		txtCouse.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Student_Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 44, 232, 50);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		txtStudent_Name = new JTextField();
		txtStudent_Name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtStudent_Name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { // On press Enter the function runs.
					String a = txtStudent_Name.getText();

					if (a.length() == 0) { // If the text field is empty.
						JOptionPane.showMessageDialog(null, "No Record found");

					} else {
						int b;
						try {
							Statement stmt = DBhandler.getConnection().createStatement(); // Connect to database.

							String query = "SELECT * from student_details where Student_Name like '" + a + "%';";
							// Displaying the data based on student name.

							ResultSet res = stmt.executeQuery(query);

							if (res.isBeforeFirst() == false) {
								JOptionPane.showMessageDialog(null, "No Record found");

							} else {

								java.sql.ResultSetMetaData rsd = res.getMetaData();
								b = rsd.getColumnCount();

								DefaultTableModel d = (DefaultTableModel) Student_table.getModel();
								d.setRowCount(0);
								while (res.next()) {
									Vector<String> v2 = new Vector<String>();
									for (int i = 1; i <= b; i++) { // Display in the table.

										v2.add(res.getString("Student_ID"));
										v2.add(res.getString("Student_Name"));
										v2.add(res.getString("Course"));
										clearFields();

									}
									d.addRow(v2);
								}
							}
							stmt.close();
							DBhandler.getConnection().commit();
						} catch (SQLException error) {
							JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
							// connected.
							error.printStackTrace();
						}

					}
				}

			}
		});

		txtStudent_Name.setBounds(10, 19, 212, 20);
		panel_2.add(txtStudent_Name);
		txtStudent_Name.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(250, 51, 479, 268);
		contentPane.add(scrollPane);

		Student_table = new JTable();
		Student_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Statement stmt = DBhandler.getConnection().createStatement();
					DefaultTableModel d = (DefaultTableModel) Student_table.getModel();

					String studentname = d.getValueAt(Student_table.getSelectedRow(), 1).toString();
					String course = d.getValueAt(Student_table.getSelectedRow(), 2).toString();

					txtCouse.setText(course);
					txtStudent_Name.setText(studentname);

					stmt.close();
					DBhandler.getConnection().commit();
				}  catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
					// connected.
					error.printStackTrace();
				}

			}
		});
		Student_table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Student_ID", "Student_Name", "Course" }));
		Student_table.getColumnModel().getColumn(0).setPreferredWidth(101);
		Student_table.getColumnModel().getColumn(1).setPreferredWidth(124);
		Student_table.getColumnModel().getColumn(2).setPreferredWidth(98);
		scrollPane.setViewportView(Student_table);
		setVisible(true);

		JButton btnCancel = new JButton("CLEAR");
		btnCancel.setBackground(new Color(255, 250, 250));

		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setBounds(580, 329, 99, 34);
		contentPane.add(btnCancel);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBackground(new Color(255, 250, 250));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = Student_table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "No row selected.");
						return;
					}
					DefaultTableModel d = (DefaultTableModel) Student_table.getModel();

					String studentid = (d.getValueAt(Student_table.getSelectedRow(), 0).toString());

					String query1 = "Update student_details set " + "Student_Name='" + txtStudent_Name.getText() + "',"
							+ "Course='" + txtCouse.getText() + "' where Student_ID= " + studentid;
					PreparedStatement pstmt = DBhandler.getConnection().prepareStatement(query1);

					int result = pstmt.executeUpdate();
					
					if (result > 0) {
						JOptionPane.showMessageDialog(null, "Record updated successfully.");
						loadTable();
					} else {
						JOptionPane.showMessageDialog(null, "Failed to update record.");
					}

					pstmt.close();
					DBhandler.getConnection().commit();
				}  catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
					// connected.
					error.printStackTrace();
				}

			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setBounds(231, 329, 89, 34);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(new Color(255, 250, 250));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rs;
				Statement stmt;
				try {
					int row = Student_table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "No row selected.");
						return;
					}
					DefaultTableModel d = (DefaultTableModel) Student_table.getModel();

					String studentid = (d.getValueAt(Student_table.getSelectedRow(), 0).toString());
					stmt = DBhandler.getConnection().createStatement();

					if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						String q = ("DELETE FROM student_details where Student_ID= " + studentid);
						rs = stmt.executeUpdate(q);
						loadTable();
						
						stmt.close();
						DBhandler.getConnection().commit();

						JOptionPane.showMessageDialog(null, "Delete Successfully");
					} else {
						JOptionPane.showMessageDialog(null, "Delete Unsuccesfully!");
					}
				} catch (Exception er) {
					JOptionPane.showMessageDialog(null, er);
				}

			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(407, 329, 94, 34);
		contentPane.add(btnDelete);

		JButton btnAdd = new JButton("ADD");
		btnAdd.setBackground(new Color(255, 250, 250));
		btnAdd.setBounds(49, 329, 94, 34);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement stmt = DBhandler.getConnection().createStatement();
					String a = txtStudent_Name.getText();
					String b = txtCouse.getText();

					boolean n = true;
					n = a.matches("-?\\d+(\\.\\d+)?");

					boolean c = true;
					c = b.matches("-?\\d+(\\.\\d+)?");
					if (a.isEmpty() || b.isEmpty() || n || c) {
						JOptionPane.showMessageDialog(contentPane, "Invalid Input");

					} else {
						String sql = "INSERT INTO student_details (Student_Name,Course) VALUES ('"
								+ txtStudent_Name.getText() + "','" + txtCouse.getText() + "')";
						int k = stmt.executeUpdate(sql);

						if (k == 1)

						{
							JOptionPane.showMessageDialog(contentPane, "Student added sucessfully");

							txtStudent_Name.setText("");
							txtCouse.setText("");
							loadTable();
						} else {
							JOptionPane.showMessageDialog(contentPane, "Error");

						}
						stmt.close();
						DBhandler.getConnection().commit();
					}
				} catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "Invalid Details");
					error.printStackTrace();
				}

			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		loadTable();
	}
}
