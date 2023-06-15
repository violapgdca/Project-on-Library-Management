package omepage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;

public class ManageBooks extends JFrame {

	private JPanel contentPane;
	private JTextField txtRefID;
	private JTextField txtBookName;
	private JTextField txtAuthor;
	private JTextField txtEdition;
	private JTextField txtCategory;
	private JTable Booktable;

	private void clearFields() { // To clear text fields.
		txtAuthor.setText(null);
		txtBookName.setText(null);
		txtCategory.setText(null);
		txtRefID.setText(null);
		txtEdition.setText(null);
		txtCategory.setText(null);
	}

	private void fillTable() {
		int b;
		try {
			Statement stmt = DBhandler.getConnection().createStatement(); // Displaying the table from the database.
			String query = "SELECT * from book_details where Status='Available'";
			ResultSet rs = stmt.executeQuery(query);
			java.sql.ResultSetMetaData rsd = rs.getMetaData();
			b = rsd.getColumnCount();

			DefaultTableModel d = (DefaultTableModel) Booktable.getModel();
			d.setRowCount(0);

			while (rs.next()) {
				Vector<String> v2 = new Vector<String>();
				for (int i = 1; i <= b; i++) {
					v2.add(rs.getString("Book_Id"));
					v2.add(rs.getString("Book_Name"));
					v2.add(rs.getString("Author"));
					v2.add(rs.getString("Ref_Id"));
					v2.add(rs.getString("Edition"));
					v2.add(rs.getString("Category"));
					clearFields();
				}
				d.addRow(v2);

			}

			stmt.close(); // Closing the connection.
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
					ManageBooks frame = new ManageBooks();
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
	public ManageBooks() {
		//setAlwaysOnTop(true);
		setTitle("BOOK DETAILS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 413);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 0, 729, 41);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel BookDetails = new JLabel("BOOK DETAILS");
		BookDetails.setFont(new Font("Tahoma", Font.BOLD, 18));
		BookDetails.setBounds(259, 11, 160, 19);
		panel.add(BookDetails);

		JButton btnback = new JButton("BACK");
		btnback.setBackground(new Color(255, 255, 255));
		btnback.addActionListener(new ActionListener() { // Moving to Manage page.
			public void actionPerformed(ActionEvent e) {
				manage window = new manage();
				window.MANAGE.setVisible(true);
				dispose();
			}
		});
		btnback.setBounds(538, 12, 89, 23);
		panel.add(btnback);

		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBackground(new Color(255, 255, 255));
		btnLogout.addActionListener(new ActionListener() { // Moving to Login page.
			public void actionPerformed(ActionEvent e) {
				adminlogmin frame = new adminlogmin();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(637, 12, 82, 23);
		panel.add(btnLogout);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		panel_1.setBorder(new TitledBorder(null, "Ref_ID", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 52, 246, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txtRefID = new JTextField();
		txtRefID.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtRefID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) { // On press Enter the function runs.
					String a = txtRefID.getText();

					if (a.length() == 0) { // To check whether reference Id is empty.
						JOptionPane.showMessageDialog(null, "Enter Ref.Id");
					} else {
						int b;
						try {
							Statement stmt = DBhandler.getConnection().createStatement(); // Connecting to Database.
							//System.out.println(stmt);
							String query = "SELECT * from book_details where Ref_Id like '" + a + "%';"; // Displaying
																											// the data
																											// based on
																											// Ref.Id.
							ResultSet res = stmt.executeQuery(query);
							if (res.isBeforeFirst() == false) {
								JOptionPane.showMessageDialog(null, "No Record found");
							} else {
								java.sql.ResultSetMetaData rsd = res.getMetaData();
								b = rsd.getColumnCount();
								DefaultTableModel d = (DefaultTableModel) Booktable.getModel();
								d.setRowCount(0);
								while (res.next()) {
									Vector<String> v2 = new Vector<String>();
									for (int i = 1; i <= b; i++) {
										v2.add(res.getString("Book_Id"));
										v2.add(res.getString("Book_Name"));
										v2.add(res.getString("Author"));
										v2.add(res.getString("Ref_Id"));
										v2.add(res.getString("Edition"));
										v2.add(res.getString("Category"));

									}
									d.addRow(v2);
								}
							}
							stmt.close(); // Closing the database
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
		txtRefID.setBounds(38, 19, 170, 20);
		panel_1.add(txtRefID);
		txtRefID.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 128, 128));
		panel_2.setBounds(10, 104, 257, 214);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBookName.setBounds(10, 23, 73, 20);
		panel_2.add(lblBookName);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAuthor.setBounds(10, 66, 62, 20);
		panel_2.add(lblAuthor);

		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEdition.setBounds(10, 110, 62, 20);
		panel_2.add(lblEdition);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCategory.setBounds(10, 160, 62, 17);
		panel_2.add(lblCategory);

		txtBookName = new JTextField();
		txtBookName.setBounds(93, 23, 164, 20);
		panel_2.add(txtBookName);
		txtBookName.setColumns(10);

		txtAuthor = new JTextField();
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(93, 66, 164, 20);
		panel_2.add(txtAuthor);

		txtEdition = new JTextField();
		txtEdition.setColumns(10);
		txtEdition.setBounds(93, 110, 164, 20);
		panel_2.add(txtEdition);

		txtCategory = new JTextField();
		txtCategory.setColumns(10);
		txtCategory.setBounds(93, 158, 164, 20);
		panel_2.add(txtCategory);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(266, 52, 463, 266);
		contentPane.add(scrollPane);

		Booktable = new JTable();
		Booktable.setBackground(new Color(240, 255, 255));
		Booktable.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		Booktable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					Statement stmt = DBhandler.getConnection().createStatement();
					DefaultTableModel d = (DefaultTableModel) Booktable.getModel(); // Get the values from database

					String bookname = d.getValueAt(Booktable.getSelectedRow(), 1).toString();
					String author = d.getValueAt(Booktable.getSelectedRow(), 2).toString();
					String Refid = (d.getValueAt(Booktable.getSelectedRow(), 3).toString());
					String edition = (d.getValueAt(Booktable.getSelectedRow(), 4).toString());
					String Category = d.getValueAt(Booktable.getSelectedRow(), 5).toString();

					txtAuthor.setText(author); // Displaying the data in text field.
					txtBookName.setText(bookname);
					txtCategory.setText(Category);
					txtRefID.setText(Refid);
					txtEdition.setText(edition);

					stmt.close(); // Closing the database connection
					DBhandler.getConnection().commit();
				} catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not connected.
					error.printStackTrace();
				}
			}
		});
		Booktable.setModel(new DefaultTableModel( // Create a Jtable
				new Object[][] {}, new String[] { "Book_Id", "Book_Name", "Author", "Ref_Id", "Edition", "Category" }));
		scrollPane.setViewportView(Booktable);

		JButton btnAdd = new JButton("ADD");
		btnAdd.setBackground(new Color(248, 248, 255));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String a = txtBookName.getText(); // Get values from text field to check whether they are valid
														// details entered or invalid.
					String b = txtAuthor.getText();
					String c = txtRefID.getText();
					String d = txtEdition.getText();
					String f = txtCategory.getText();

					if (a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || f.isEmpty() ) {
						JOptionPane.showMessageDialog(contentPane, "Invalid Input");

					} else {

						Statement stmt = DBhandler.getConnection().createStatement();
						String sql = "INSERT INTO book_details (Book_Name,Author,Ref_Id,Edition,Category,Status) VALUES ('"
								+ txtBookName.getText() + "','" + txtAuthor.getText() + "','" + txtRefID.getText()
								+ "','" + txtEdition.getText() + "','" + txtCategory.getText() + "','" + "Available' )";

						// Insert the values to the database.

						int k = stmt.executeUpdate(sql); // Execute the query
						if (k == 1)

						{
							JOptionPane.showMessageDialog(contentPane, "Book added sucessfully");

							txtBookName.setText(""); // Clear the text fields
							txtAuthor.setText("");
							txtRefID.setText("");
							txtEdition.setText("");
							txtCategory.setText("");
							fillTable();
						} else {
							JOptionPane.showMessageDialog(contentPane, "Cannot add");
						}
						stmt.close(); // Closing the connection
						DBhandler.getConnection().commit();
					}
				} catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not connected.
					error.printStackTrace();
				}

			}
		});
		btnAdd.setBounds(31, 340, 89, 23);
		contentPane.add(btnAdd);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBackground(new Color(255, 250, 250));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = Booktable.getSelectedRow(); // Selected row is stored
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "No row selected."); // If row is not selected
						return;
					}

					DefaultTableModel d = (DefaultTableModel) Booktable.getModel();

					String bookid = (d.getValueAt(Booktable.getSelectedRow(), 0).toString());

					String query1 = "Update book_details set " + "Book_Name='" + txtBookName.getText() + "',"
							+ "Author='" + txtAuthor.getText() + "'," + "Ref_Id='" + txtRefID.getText() + "',"
							+ "Edition='" + txtEdition.getText() + "'," + "Category='" + txtCategory.getText()
							+ "' where Book_Id= " + bookid;
					// Query to Update the database.

					PreparedStatement pstmt = DBhandler.getConnection().prepareStatement(query1); // Connecting to the
																									// database.

					int result = pstmt.executeUpdate(); // Execute the query and store data in database.

					if (result > 0) {
						JOptionPane.showMessageDialog(null, "Record updated successfully.");
						fillTable();
					} else {
						JOptionPane.showMessageDialog(null, "Failed to update record.");
					}

					pstmt.close(); // Closing the connection
					DBhandler.getConnection().commit();
				} catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not connected.
					error.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(213, 340, 89, 23);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(new Color(255, 250, 250));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Select a row from Jtable
				int rs;
				Statement stmt;
				try {
					int row = Booktable.getSelectedRow();
					if (row == 1) {
						JOptionPane.showMessageDialog(null, "No row selected.");
						return;
					}
					DefaultTableModel d = (DefaultTableModel) Booktable.getModel();

					String bookid = (d.getValueAt(Booktable.getSelectedRow(), 0).toString());
					stmt = DBhandler.getConnection().createStatement();
					if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING", // Before deleting a book
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						String q = ("DELETE FROM book_details where Book_Id= " + bookid); // Query to delete
						rs = stmt.executeUpdate(q);
						fillTable(); // Result after deleting the book table is displayed.

						JOptionPane.showMessageDialog(null, "Delete Successfully");

						stmt.close(); // Connection is closed.
						DBhandler.getConnection().commit();

					} else {
						JOptionPane.showMessageDialog(null, "Delete Unsuccesfully!");
					}
				} catch (Exception er) {
					JOptionPane.showMessageDialog(null, "No row selected.");
				}

			}
		});
		btnDelete.setBounds(399, 340, 89, 23);
		contentPane.add(btnDelete);

		JButton btnClear = new JButton("CLEAR");
		btnClear.setBackground(new Color(255, 250, 250));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Clear the text fields.
				clearFields();
			}
		});
		btnClear.setBounds(595, 340, 89, 23);
		contentPane.add(btnClear);
		fillTable(); // Display the table in JTable.
	}
}
