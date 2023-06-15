package omepage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddFaculty extends JFrame {

	private JPanel contentPane;
	private JTextField txtdept;
	private JTable facultyTable;
	private JTextField txtFacultyName;

	private void clearFields() {
		txtFacultyName.setText(null);
		txtdept.setText(null);

	}

	public void loadTable() { // Display the table in Jtable.

		int b;
		try {
			Statement stmt = DBhandler.getConnection().createStatement();
			String query = "SELECT * from Faculty_details";
			ResultSet rs = stmt.executeQuery(query);
			java.sql.ResultSetMetaData rsd = rs.getMetaData();
			b = rsd.getColumnCount();

			DefaultTableModel d = (DefaultTableModel) facultyTable.getModel();
			d.setRowCount(0);

			while (rs.next()) {
				Vector<String> v2 = new Vector<String>();
				for (int i = 1; i <= b; i++) {
					v2.add(rs.getString("Faculty_Id"));
					v2.add(rs.getString("Faculty_Name"));
					v2.add(rs.getString("Department"));
				}
				d.addRow(v2);

			}

			stmt.close();
			DBhandler.getConnection().commit();
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
			// connected.
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
					AddFaculty frame = new AddFaculty();
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
	public AddFaculty() {
		setTitle("FACULTY DETAILS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 413);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(244, 164, 96));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(0, 0, 729, 49);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ADD FACULTY DETAILS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(209, 18, 178, 14);
		panel.add(lblNewLabel);

		JButton btnBack = new JButton("BACK");
		btnBack.setBackground(new Color(255, 250, 250));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Moving to manage page.
				manage window = new manage();
				window.MANAGE.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(538, 16, 73, 23);
		panel.add(btnBack);

		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBackground(new Color(255, 250, 250));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Moving to Login page
				adminlogmin frame = new adminlogmin();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(634, 16, 85, 23);
		panel.add(btnLogout);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(244, 164, 96));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(0, 48, 224, 281);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Department");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(0, 147, 72, 14);
		panel_1.add(lblNewLabel_1);

		txtdept = new JTextField();
		txtdept.setBounds(82, 144, 132, 27);
		panel_1.add(txtdept);
		txtdept.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(128, 0, 0));
		panel_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Faculty_Name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 21, 204, 57);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		txtFacultyName = new JTextField();
		txtFacultyName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFacultyName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) { // On press Enter the function runs.
					String a = txtFacultyName.getText();

					if (a.length() == 0) { // If the text field is empty.
						JOptionPane.showMessageDialog(null, "Enter Faculty Name");

					} else {
						int b;
						try {
							Statement stmt = DBhandler.getConnection().createStatement(); // Connect to database.

							String query = "SELECT * from faculty_details where Faculty_Name like '" + a + "%';";
							// Displaying the data based on Faculty name.

							ResultSet res = stmt.executeQuery(query);

							if (res.isBeforeFirst() == false) {
								JOptionPane.showMessageDialog(null, "Invalid Details");

							} else {

								java.sql.ResultSetMetaData rsd = res.getMetaData();
								b = rsd.getColumnCount();

								DefaultTableModel d = (DefaultTableModel) facultyTable.getModel(); // Display the
																									// records
								d.setRowCount(0);
								while (res.next()) {
									Vector<String> v2 = new Vector<String>();
									for (int i = 1; i <= b; i++) { // Display in the table.

										v2.add(res.getString("Faculty_ID"));
										v2.add(res.getString("Faculty_Name"));
										v2.add(res.getString("Department"));
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
		txtFacultyName.setBounds(10, 20, 173, 20);
		panel_2.add(txtFacultyName);
		txtFacultyName.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 48, 506, 281);
		contentPane.add(scrollPane);

		facultyTable = new JTable();
		facultyTable.addMouseListener(new MouseAdapter() { // Display the selected row from table
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Statement stmt = DBhandler.getConnection().createStatement();
					DefaultTableModel d = (DefaultTableModel) facultyTable.getModel();

					String facultyname = d.getValueAt(facultyTable.getSelectedRow(), 1).toString();
					String dept = d.getValueAt(facultyTable.getSelectedRow(), 2).toString();

					txtdept.setText(dept);
					txtFacultyName.setText(facultyname);

					stmt.close();
					DBhandler.getConnection().commit();
				} catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
					// connected.
					error.printStackTrace();
				}

			}
		});
		facultyTable.setModel(new DefaultTableModel( // Create a Jtable
				new Object[][] {}, new String[] { "Faculty_Id", "Faculty_Name", "Department" }));
		scrollPane.setViewportView(facultyTable);

		JButton btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdd.setBackground(new Color(255, 250, 250));
		btnAdd.setBounds(27, 340, 96, 34);
		contentPane.add(btnAdd);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClear.setBackground(new Color(255, 250, 250));
		btnClear.addActionListener(new ActionListener() { // Clear the text fields
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setBounds(594, 340, 96, 34);
		contentPane.add(btnClear);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setBackground(new Color(255, 250, 250));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // To update the records

				try {
					int row = facultyTable.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "No row selected.");
						return;
					}

					DefaultTableModel d = (DefaultTableModel) facultyTable.getModel();

					String facultyid = (d.getValueAt(facultyTable.getSelectedRow(), 0).toString());

					String query1 = "Update faculty_details set " + "Faculty_Name='" + txtFacultyName.getText() + "',"
							+ "Department='" + txtdept.getText() + "' where Faculty_Id= " + facultyid;
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
				} catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
					// connected.
					error.printStackTrace();
				}

			}
		});
		btnUpdate.setBounds(233, 340, 96, 34);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBackground(new Color(255, 250, 250));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int rs;
				Statement stmt;
				try {
					int row = facultyTable.getSelectedRow();

					if (row == -1) {
						JOptionPane.showMessageDialog(null, "No row selected.");
						return;
					}

					DefaultTableModel d = (DefaultTableModel) facultyTable.getModel();

					String facultyid = (d.getValueAt(facultyTable.getSelectedRow(), 0).toString());
					stmt = DBhandler.getConnection().createStatement();
					if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						String q = ("DELETE FROM faculty_details where Faculty_Id= " + facultyid);
						rs = stmt.executeUpdate(q);
						loadTable();

						stmt.close();
						DBhandler.getConnection().commit();

						JOptionPane.showMessageDialog(null, "Delete Successfully");
					} else {
						JOptionPane.showMessageDialog(null, "Delete Unsuccesfully!");
					}
				} catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
					// connected.
					error.printStackTrace();
				}

			}
		});
		btnDelete.setBounds(432, 340, 96, 34);
		contentPane.add(btnDelete);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Code to add a new faculty
				try {
					Statement stmt = DBhandler.getConnection().createStatement();
					String a = txtFacultyName.getText();
					String b = txtdept.getText();

					boolean n = true;
					n = a.matches("-?\\d+(\\.\\d+)?");

					boolean c = true;
					c = b.matches("-?\\d+(\\.\\d+)?");
					if (a.isEmpty() || b.isEmpty() || n || c) {
						JOptionPane.showMessageDialog(contentPane, "Invalid Input");

					} else {

						String sql = "INSERT INTO faculty_details (Faculty_Name,Department) VALUES ('"
								+ txtFacultyName.getText() + "','" + txtdept.getText() + "')";

						int k = stmt.executeUpdate(sql);

						if (k == 1)

						{
							JOptionPane.showMessageDialog(contentPane, "Faculty added sucessfully");

							txtFacultyName.setText("");
							txtdept.setText("");
							loadTable();
						} else {
							JOptionPane.showMessageDialog(contentPane, "Error");

						}
						stmt.close();
						DBhandler.getConnection().commit();
					}
				} catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
					// connected.
					error.printStackTrace();
				}

			}
		});
		loadTable();
	}
}
