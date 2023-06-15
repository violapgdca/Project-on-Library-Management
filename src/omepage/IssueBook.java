package omepage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Scrollbar;
import java.util.Calendar;
import java.util.Vector;
import java.awt.Label;
import com.toedter.calendar.JCalendar;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Date;

public class IssueBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookId;
	private JTextField txtBookName;
	private JTextField txtAuthor;
	private JTextField txtRefId;
	private JTextField txtCategory;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtissue_date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void issuedate() {
		
		Date current=new Date();
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		String s = inputFormat.format(current);
		System.out.println(s);
		txtissue_date.setText(s);
	}
	
	private void clearFields() {

		txtBookId.setText(null);
		txtBookName.setText(null);
		txtAuthor.setText(null);
		txtRefId.setText(null);
		txtCategory.setText(null);
		txtID.setText(null);
		txtName.setText(null);
		txtissue_date.setText(null);

	}
	
	
	/**
	 * Create the frame.
	 */
	public IssueBook() {
		setTitle("ISSUE BOOKS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 413);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBackground(new Color(152, 251, 152));
		panel.setBounds(0, 49, 254, 325);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel BookName = new JLabel("Name :");
		BookName.setFont(new Font("Tahoma", Font.BOLD, 12));
		BookName.setBounds(10, 98, 66, 25);
		panel.add(BookName);

		JLabel Author = new JLabel("Author :");
		Author.setFont(new Font("Tahoma", Font.BOLD, 12));
		Author.setBounds(10, 145, 66, 30);
		panel.add(Author);

		JLabel Category = new JLabel("Category :");
		Category.setBackground(new Color(240, 240, 240));
		Category.setFont(new Font("Tahoma", Font.BOLD, 12));
		Category.setBounds(10, 259, 66, 25);
		panel.add(Category);

		JLabel lblBookId = new JLabel("Book ID");
		lblBookId.setBounds(10, 49, 66, 14);
		panel.add(lblBookId);
		lblBookId.setFont(new Font("Tahoma", Font.BOLD, 11));

		txtBookId = new JTextField();
		txtBookId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {		//Code to search the book using bookid and display the details.
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// Enter was pressed. Your code goes here.
					try {
						int Book_Id = Integer.parseInt(txtBookId.getText());
						
						try {
							Statement stmt = DBhandler.getConnection().createStatement();
							String sql = "select * from book_details where Book_Id="
									+ Book_Id + " and Status='Available'";
							ResultSet rs = stmt.executeQuery(sql);
							if (rs.next()) {
								txtBookName.setText(rs.getString("Book_Name"));
								txtAuthor.setText(rs.getString("Author"));
								txtRefId.setText(rs.getString("Ref_Id"));
								txtCategory.setText(rs.getString("Category"));

							} else {
								JOptionPane.showMessageDialog(contentPane, "Invalid Book_ID/Book is already issued");
							}
							
							 stmt.close();
					         DBhandler.getConnection().commit();

						} catch (SQLException error) {
							JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
							// connected.
							error.printStackTrace();
						}

					} catch (NumberFormatException err) {
						JOptionPane.showMessageDialog(contentPane, "Enter Book_ID");
					}

				}
			}
		});
		txtBookId.setBounds(86, 36, 161, 30);
		panel.add(txtBookId);
		txtBookId.setColumns(10);

		JLabel Ref_Id = new JLabel("Ref_Id :");
		Ref_Id.setFont(new Font("Tahoma", Font.BOLD, 11));
		Ref_Id.setBounds(10, 197, 63, 27);
		panel.add(Ref_Id);

		txtBookName = new JTextField();
		txtBookName.setEditable(false);
		txtBookName.setBounds(86, 91, 161, 30);
		panel.add(txtBookName);
		txtBookName.setColumns(10);

		txtAuthor = new JTextField();
		txtAuthor.setEditable(false);
		txtAuthor.setColumns(10);
		txtAuthor.setBounds(83, 145, 161, 30);
		panel.add(txtAuthor);

		txtRefId = new JTextField();
		txtRefId.setEditable(false);
		txtRefId.setColumns(10);
		txtRefId.setBounds(83, 197, 161, 30);
		panel.add(txtRefId);

		txtCategory = new JTextField();
		txtCategory.setEditable(false);
		txtCategory.setColumns(10);
		txtCategory.setBounds(83, 252, 161, 30);
		panel.add(txtCategory);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(60, 179, 113));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(0, 0, 729, 48);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		Label lblIssue = new Label("ISSUE BOOK");
		lblIssue.setBounds(245, 10, 129, 17);
		panel_2.add(lblIssue);
		lblIssue.setFont(new Font("Dialog", Font.BOLD, 17));

		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setBackground(new Color(255, 250, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manage window = new manage();
				window.MANAGE.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(524, 4, 89, 23);
		panel_2.add(btnNewButton);

		JButton btnBack = new JButton("LOGOUT");
		btnBack.setBackground(new Color(255, 250, 240));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminlogmin frame = new adminlogmin();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(630, 4, 89, 23);
		panel_2.add(btnBack);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(144, 238, 144));
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_3.setBounds(492, 44, 237, 330);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel txtIssueDate = new JLabel("Issue Date");
		txtIssueDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtIssueDate.setBounds(10, 98, 81, 20);
		panel_3.add(txtIssueDate);
		

		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDueDate.setBounds(10, 168, 69, 17);
		panel_3.add(lblDueDate);

		JDateChooser date_Due = new JDateChooser();
		date_Due.setBounds(89, 165, 130, 23);
		panel_3.add(date_Due);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(152, 251, 152));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(253, 44, 245, 330);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JComboBox FacStu = new JComboBox();
		FacStu.setFont(new Font("Tahoma", Font.BOLD, 13));
		FacStu.setBounds(20, 26, 187, 20);
		panel_1.add(FacStu);
		FacStu.setModel(new DefaultComboBoxModel(new String[] { "Faculty", "Student" }));

		JLabel Name = new JLabel("Name :");
		Name.setFont(new Font("Tahoma", Font.BOLD, 12));
		Name.setBounds(20, 160, 47, 33);
		panel_1.add(Name);
		
		txtissue_date = new JTextField();
		txtissue_date.setBounds(89, 99, 130, 20);
		panel_3.add(txtissue_date);
		txtissue_date.setColumns(10);
		

		JLabel ID = new JLabel("ID :");

		ID.setFont(new Font("Tahoma", Font.BOLD, 12));
		ID.setBounds(20, 85, 27, 26);
		panel_1.add(ID);

		txtID = new JTextField();
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { 		//Code to search faculty using facultyid and display the details

					String a = (String) FacStu.getSelectedItem();
					if (a.equals("Faculty")) {
						try {

							int Faculty_Id = Integer.parseInt(txtID.getText());
							lblDueDate.setVisible(false);
							date_Due.setVisible(false);
							try {
								Statement stmt = DBhandler.getConnection().createStatement();
								String sql = "select Faculty_Name from faculty_details where faculty_Id='" + Faculty_Id
										+ "'";
			
								ResultSet rs = stmt.executeQuery(sql);
								while (rs.next()) {

									txtName.setText(rs.getString("Faculty_Name"));

								}
								
								
								issuedate();
								
								 rs.close();
						            DBhandler.getConnection().commit();

							}catch (SQLException error) {
								JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
								// connected.
								error.printStackTrace();
							}

						} catch (NumberFormatException err) {
							JOptionPane.showMessageDialog(contentPane, "Enter Faculty Id");
						}
					}

					else {
						try {

							int Student_Id = Integer.parseInt(txtID.getText());
							lblDueDate.setVisible(true);
							date_Due.setVisible(true);
							try {
								Statement stmt = DBhandler.getConnection().createStatement();
								try {
									int s;
									String g= "Select count(Id) as Count from issue_books where Id=" + Student_Id;
								 
									ResultSet rs = stmt.executeQuery(g);
									while (rs.next()) {

									 s=Integer.parseInt(rs.getString("Count"));
									 
										if(s>1) {
											JOptionPane.showMessageDialog(contentPane, "Book Limit exceed");
										}

									}
			
					
										
									
								} catch (SQLException error) {
									JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
									// connected.
									error.printStackTrace();
								}

								String sql = "select Student_Name from student_details where Student_Id='" + Student_Id
										+ "'";
								
								ResultSet rs = stmt.executeQuery(sql);
								while (rs.next()) {

									txtName.setText(rs.getString("Student_Name"));

								}
								
								issuedate();
								 rs.close();
						            DBhandler.getConnection().commit();

								
								
							} catch (SQLException error) {
								JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
								// connected.
								error.printStackTrace();
							}

						 }catch (NumberFormatException err) {
							JOptionPane.showMessageDialog(contentPane, "Enter Student Id");
						
						 }
							}
						
				}
			}
		});

		// Updating Book details table

		JButton btnIssue = new JButton("ISSUE");
		btnIssue.setBackground(new Color(245, 255, 250));
		btnIssue.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int bookId = Integer.parseInt(txtBookId.getText());
					String bookName = txtBookName.getText();

					String a = (String) FacStu.getSelectedItem();
					if (a.equals("Student")) {

						try {

							int studentId = Integer.parseInt(txtID.getText());
							String studentName = txtName.getText();
							date_Due.isVisible();
							java.util.Date utilDueDate = date_Due.getDate();

							try

							{
								Statement stmt = DBhandler.getConnection().createStatement();

								String sql = "INSERT INTO issue_books (Book_Id, Book_Name, Id, Name, Issue_Date, Due_Date) VALUES ('"
										+ bookId + "','" + bookName + "','" + studentId + "','" + studentName + "','"
										+ txtissue_date.getText() + "','"
										+ new java.sql.Date(utilDueDate.getTime()) + "')";
								String q1 = "UPDATE book_details SET Status='Not Available' WHERE Book_Id="
										+ txtBookId.getText();
								PreparedStatement pstmt = DBhandler.getConnection().prepareStatement(q1);

								pstmt.executeUpdate();
								int k = stmt.executeUpdate(sql);

								if (k == 1) {

									JOptionPane.showMessageDialog(contentPane, "Record added successfully");

									txtBookId.setText("");
									txtBookName.setText("");
									txtAuthor.setText("");
									txtID.setText("");
									txtName.setText("");
									txtCategory.setText("");
									txtRefId.setText("");
									txtissue_date.setText(null);
									date_Due.setDate(null);
								} else {
									JOptionPane.showMessageDialog(contentPane, "Error");
								}
								DBhandler.getConnection().commit();
							} catch (SQLException error) {
								JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
								// connected.
								error.printStackTrace();
							}

						} catch (NumberFormatException err) {
							JOptionPane.showMessageDialog(contentPane, "Enter Student Id");
						}
					} else
					
					{
						try {
							int facultyId = Integer.parseInt(txtID.getText());
							String facultyName = txtName.getText();
							
							date_Due.setVisible(false);
							try {
								Statement stmt = DBhandler.getConnection().createStatement();

								String sql = "INSERT INTO fac_issued_books (Book_Id, Book_Name, Id, Name, Issue_Date) VALUES ('"
										+ bookId + "','" + bookName + "','" + facultyId + "','" + facultyName + "','"
										+ txtissue_date.getText() + "')";
								String q = "UPDATE book_details SET Status='Not Available' WHERE Book_Id="
										+ txtBookId.getText();
								PreparedStatement pstmt = DBhandler.getConnection().prepareStatement(q);

								pstmt.executeUpdate();

								int k = stmt.executeUpdate(sql);

								if (k == 1) {

									JOptionPane.showMessageDialog(contentPane, "Record added successfully");
									// Clear input fields
									txtBookId.setText("");
									txtBookName.setText("");
									txtAuthor.setText("");
									txtID.setText("");
									txtName.setText("");
									txtCategory.setText("");
									txtRefId.setText("");
									txtissue_date.setText(null);
								} else {
									JOptionPane.showMessageDialog(contentPane, "Error");
								}
								DBhandler.getConnection().commit();
							} catch (SQLException error) {
								JOptionPane.showMessageDialog(null, "Database is not connected"); // If database is not
								// connected.
								error.printStackTrace();
							}


						} catch (NumberFormatException err) {
							JOptionPane.showMessageDialog(contentPane, "Enter Student Id");
						}

					}
				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(contentPane, "Enter Details");
				}
			}

		});
		btnIssue.setBounds(10, 256, 89, 23);
		panel_3.add(btnIssue);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setBackground(new Color(255, 250, 240));
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClear.setBounds(130, 256, 89, 23);
		panel_3.add(btnClear);
		
		

		txtID.setColumns(10);
		txtID.setBounds(77, 85, 145, 26);
		panel_1.add(txtID);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setBounds(77, 160, 145, 27);
		panel_1.add(txtName);

		JLabel lblCourse = new JLabel("");
		lblCourse.setBounds(10, 190, 46, 14);
		panel_1.add(lblCourse);
	}
}
