package omepage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

import com.mysql.cj.protocol.Resultset;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ReturnBooks extends JFrame {

	private JPanel contentPane;
	private JTextField txtbookid;
	private JTextField txtfine;
	private JTextField txtduedate;
	private JTextField txtbookname;
	private JTextField txtname;
	private JTextField txtid;
	private JTextField txtissuedate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBooks frame = new ReturnBooks();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void clearFields() {

		txtbookid.setText(null);
		txtbookname.setText(null);
		txtname.setText(null);
		txtissuedate.setText(null);
		txtid.setText(null);
		txtfine.setText(null);
		txtduedate.setText(null);
		txtfine.setText(null);

	}

	/**
	 * Create the frame.
	 */
	public ReturnBooks() {
		setTitle("RETURN BOOKS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(186, 85, 211));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(0, 0, 729, 57);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("RETURN BOOK");
		lblNewLabel_1.setBackground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(286, 9, 157, 29);
		panel.add(lblNewLabel_1);

		JButton btnback = new JButton("BACK");
		btnback.setBackground(new Color(255, 250, 250));
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manage window = new manage();
				window.MANAGE.setVisible(true);
				dispose();
			}
		});
		btnback.setBounds(531, 11, 89, 28);
		panel.add(btnback);

		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setBackground(new Color(255, 250, 250));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage n = new homepage();
				n.setVisible(true);
				dispose();
			}
		});
		btnLogout.setBounds(630, 11, 89, 28);
		panel.add(btnLogout);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(221, 160, 221));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(0, 56, 370, 318);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblbookid = new JLabel("Book ID :");
		lblbookid.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblbookid.setBounds(10, 48, 84, 14);
		panel_1.add(lblbookid);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(221, 160, 221));
		panel_2.setBounds(368, 56, 361, 318);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblduedate = new JLabel("Due Date");
		lblduedate.setBounds(10, 56, 69, 14);
		panel_2.add(lblduedate);
		lblduedate.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblfine = new JLabel("Fine");
		lblfine.setBounds(10, 185, 69, 14);
		panel_2.add(lblfine);
		lblfine.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtbookid = new JTextField();
		txtbookid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					try {
						int Book_Id = Integer.parseInt(txtbookid.getText());
						try {
							Statement stmt = DBhandler.getConnection().createStatement();

							String sql2 = "SELECT * FROM book_details WHERE `Status` ='Not Available' and Book_Id = "
									+ Book_Id;

							ResultSet rs2 = stmt.executeQuery(sql2);
							if (!rs2.next()) {
								JOptionPane.showMessageDialog(contentPane, "Book is not issued yet ");
							} else {

								try {
									// Statement stmt1 = DBhandler.getConnection().createStatement();
									String sql = "SELECT * FROM issue_books WHERE Book_Id = " + Book_Id;
									txtfine.setVisible(true);
									txtduedate.setVisible(true);
									lblduedate.setVisible(true);
									lblfine.setVisible(true);
									// txtfine.setVisible(false);
									System.out.println(sql);
									ResultSet rs = stmt.executeQuery(sql);
									if (rs.next()) {
										txtbookname.setText(rs.getString("Book_Name"));
										// txtrefid.setText(rs.getString("Ref_Id"));
										txtname.setText(rs.getString("Name"));
										txtid.setText(rs.getString("Id"));
										txtissuedate.setText(rs.getString("Issue_Date"));
										txtduedate.setText(rs.getString("Due_Date"));
										// DueDate.setToolTipText(rs.getString("Due_Date"));
									} else {
										// JOptionPane.showMessageDialog(contentPane, "Invalid Book_ID");
										// txtfine.setVisible(true);
										txtfine.setVisible(false);
										txtduedate.setVisible(false);
										lblduedate.setVisible(false);
										lblfine.setVisible(false);
									}

									String s = "SELECT * FROM fac_issued_books WHERE Book_Id = " + Book_Id;
									System.out.println(s);
									ResultSet r = stmt.executeQuery(s);
									System.out.println(r);

									if (r.next()) {

										txtbookname.setText(r.getString("Book_Name"));
										// txtrefid.setText(r.getString("Ref_Id"));
										txtname.setText(r.getString("Name"));
										txtid.setText(r.getString("Id"));
										txtissuedate.setText(r.getString("Issue_Date"));

									} else {
										// JOptionPane.showMessageDialog(contentPane, "Invalid Book_ID 2");
									}

									rs2.close();
									stmt.close();
								} catch (SQLException err) {
									JOptionPane.showMessageDialog(contentPane, "Invalid Book_ID ");
									err.printStackTrace();
								}

							}

						} catch (SQLException err) {
							JOptionPane.showMessageDialog(contentPane, "Enter Valid Book_ID");

						}

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(contentPane, "Enter Valid Book_ID");

					}

				}

			}
		});

		txtbookid.setBounds(117, 47, 171, 31);
		panel_1.add(txtbookid);
		txtbookid.setColumns(10);

		JLabel lblbookname = new JLabel("Book Name :");
		lblbookname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblbookname.setBounds(10, 99, 84, 22);
		panel_1.add(lblbookname);

		txtbookname = new JTextField();
		txtbookname.setEditable(false);
		txtbookname.setBounds(117, 100, 171, 31);
		panel_1.add(txtbookname);
		txtbookname.setColumns(10);

		JLabel lblissuedate = new JLabel("Issue Date :");
		lblissuedate.setBounds(10, 254, 84, 22);
		panel_1.add(lblissuedate);
		lblissuedate.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtname = new JTextField();
		txtname.setEditable(false);
		txtname.setColumns(10);
		txtname.setBounds(117, 203, 171, 31);
		panel_1.add(txtname);

		JLabel lblid = new JLabel("ID :");
		lblid.setBounds(10, 161, 46, 14);
		panel_1.add(lblid);
		lblid.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setColumns(10);
		txtid.setBounds(117, 148, 171, 31);
		panel_1.add(txtid);

		JLabel lblname = new JLabel(" Name :");
		lblname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblname.setBounds(10, 202, 84, 22);
		panel_1.add(lblname);

		txtissuedate = new JTextField();
		txtissuedate.setEditable(false);
		txtissuedate.setColumns(10);
		txtissuedate.setBounds(117, 256, 171, 31);
		panel_1.add(txtissuedate);

		txtduedate = new JTextField();
		txtduedate.setEditable(false);
		txtduedate.setColumns(10);
		txtduedate.setBounds(131, 54, 164, 29);
		panel_2.add(txtduedate);

		txtfine = new JTextField();
		txtfine.setEditable(false);
		txtfine.setBounds(131, 183, 164, 29);
		panel_2.add(txtfine);
		txtfine.setColumns(10);

		JLabel lblreturndate = new JLabel("Return Date");
		lblreturndate.setBounds(10, 131, 96, 14);
		panel_2.add(lblreturndate);
		lblreturndate.setFont(new Font("Tahoma", Font.BOLD, 12));

		JDateChooser date_return = new JDateChooser();
		date_return.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					int bookId = Integer.parseInt(txtbookid.getText());
					java.util.Date utilReturnDate = date_return.getDate();
					// java.util.Date utildueDate = DueDate.getDate();
					if (utilReturnDate == null) {
						JOptionPane.showMessageDialog(contentPane, "Please select a return date");
						return;
					}

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String f = txtduedate.getText();

					java.util.Date utilReturnDat = sdf.parse(txtduedate.getText());
					String s = sdf.format(utilReturnDat);

					System.out.println(s);

					String desiredFormat = "yyyy-MM-dd";
					SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date date = inputFormat.parse(f);

					SimpleDateFormat outputFormat = new SimpleDateFormat(desiredFormat);
					String formattedDate = outputFormat.format(date);
					System.out.println(formattedDate);

					LocalDateTime from = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
					LocalDateTime to = LocalDateTime.ofInstant(utilReturnDate.toInstant(), ZoneId.systemDefault());
					Duration d = Duration.between(from, to);

					System.out.println(d.toDays());
					if (d.toDays() <= 0) {

						txtfine.setText("0.0");

					} else {
						txtfine.setText(String.valueOf(d.toDays() * 0.5f));
					}

				} catch (Exception err) {
					// JOptionPane.showMessageDialog(contentPane, "return date");
				}
			}
		});
		date_return.setBounds(131, 125, 164, 29);
		panel_2.add(date_return);

		JButton btnreturn = new JButton("RETURN");
		btnreturn.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnreturn.setBackground(new Color(255, 250, 250));
		btnreturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int bookId = Integer.parseInt(txtbookid.getText());
					try {
					java.util.Date utilReturnDate = date_return.getDate();

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String s = sdf.format(utilReturnDate);

					System.out.println(s);

					try {

						String q1 = "UPDATE book_details SET `Status` ='Available' WHERE Book_Id =" + bookId;
						PreparedStatement pstmt = DBhandler.getConnection().prepareStatement(q1);
						int r =pstmt.executeUpdate(q1);
						if(true) {
						System.out.println(q1);
						String q2 = "DELETE FROM issue_books where Book_Id= " + bookId;		
						PreparedStatement pstmt1= DBhandler.getConnection().prepareStatement(q2);
						pstmt1.executeUpdate(q2);
						 pstmt1.close();
						System.out.println(" row(s) updated.");
						

						System.out.println(q1);
						}
						
						if(true){
						
						String q3 = "DELETE FROM fac_issued_books where Book_Id= " + bookId;		
						PreparedStatement pstmt2= DBhandler.getConnection().prepareStatement(q3);
						pstmt2.executeUpdate(q3);
						 pstmt2.close();
						
						System.out.println(" row(s) updated.");
						}
						

						//int result = pstmt.executeUpdate(q1);
						//System.out.println("res" + result);
						JOptionPane.showMessageDialog(contentPane, "Book Returned");
						pstmt.close();
						DBhandler.getConnection().commit();
					} catch (SQLException err) {
						JOptionPane.showMessageDialog(contentPane, "Record not added");

					}}catch (Exception er) {
						JOptionPane.showMessageDialog(null,"Please select an Return date" );
					}
					

				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(contentPane, "Enter Details");
				}
			}

		});
		btnreturn.setBounds(28, 255, 101, 29);
		panel_2.add(btnreturn);

		JButton btnclear = new JButton("CLEAR");
		btnclear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnclear.setBackground(new Color(255, 250, 250));
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnclear.setBounds(187, 255, 96, 29);
		panel_2.add(btnclear);

	}
}
