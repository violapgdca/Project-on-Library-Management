package omepage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.x.protobuf.MysqlxCrud.UpdateOperation.UpdateType;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.Book;

public class Addbook extends JFrame {

	private JPanel contentPane;
	private JTextField txtAuthor;
	private JTextField txtBookName;
	private JTextField txtCategory;
	private JTextField txtRefID;
	private JTextField txtEdition;
	private JTable Booktable;
	private JTextField txtBookId;

	/**
	 * Launch the application.
	 */

	private void clearFields() {
		txtBookId.setText(null);
		txtAuthor.setText(null);
		txtBookName.setText(null);
		txtCategory.setText(null);
		txtRefID.setText(null);
		txtEdition.setText(null);
		txtEdition.setText(null);

	}

	private void fillTable() {

		System.out.println("Fill Table");
		int b;
		try {
			Statement stmt = DBhandler.getConnection().createStatement();
			String query = "SELECT * from book_details";
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

			stmt.close();
			DBhandler.getConnection().commit();
		} catch (SQLException error) {
			System.out.println("An Error occured while adding book.");
			error.printStackTrace();
		}

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Addbook frame = new Addbook();
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
	public Addbook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 89, 218, 187);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setBounds(10, 11, 53, 14);
		panel.add(lblBookName);

		txtCategory = new JTextField();
		txtCategory.setBounds(71, 154, 137, 20);
		panel.add(txtCategory);
		txtCategory.setColumns(10);

		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setBounds(10, 121, 63, 14);
		panel.add(lblEdition);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(10, 157, 46, 14);
		panel.add(lblCategory);

		JLabel lblRefNo = new JLabel("Book_Id");
		lblRefNo.setBounds(10, 83, 63, 14);
		panel.add(lblRefNo);

		txtRefID = new JTextField();
		txtRefID.setBounds(71, 80, 137, 20);
		panel.add(txtRefID);
		txtRefID.setColumns(10);

		txtEdition = new JTextField();
		txtEdition.setColumns(10);
		txtEdition.setBounds(71, 118, 137, 20);
		panel.add(txtEdition);

		txtAuthor = new JTextField();
		txtAuthor.setBounds(71, 41, 137, 20);
		panel.add(txtAuthor);
		txtAuthor.setColumns(10);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(10, 44, 63, 14);
		panel.add(lblAuthor);

		txtBookName = new JTextField();
		txtBookName.setBounds(73, 8, 135, 20);
		panel.add(txtBookName);
		txtBookName.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 590, 33);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("MANAGE BOOKS");
		lblNewLabel_4.setBounds(198, 7, 142, 17);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_4);

		JButton btnback = new JButton("BACK");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Mbooks frame = new Mbooks();
				//frame.setVisible(true);
			}
		});
		btnback.setBounds(392, 6, 89, 23);
		panel_1.add(btnback);

		JButton btnlogout = new JButton("LOGOUT");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage n = new homepage();
				n.setVisible(true);
				dispose();
			}
		});
		
		
		    





	
//	 public int getRefId() {
//	        return getRefId();
//	    }
//	
//		private ArrayList<Book> bookList;
//		private String[] columnNames = {"Ref_Id", "Book_Name", "Author", "Edition", "Category"};
//
//		
//		
//
//		public Book getBookByReferenceId(int refId) {
//		    for (Book book : bookList) {
//		    	int txtRefId = Integer.parseInt(txtRefID.getText());
//		    	if (getRefId() == txtRefId) {
//		            return book;
//		        }
//		    }
//		    return null;
//		}
//
//		public ArrayList<Book> getBooksByName(String name) {
//		    ArrayList<Book> matchingBooks = new ArrayList<>();
//		    for (Book book : bookList) {
//		        if (txtBookName.getName().equals(name)) {
//		            matchingBooks.add(book);
//		        }
//		    }
//		    return matchingBooks;
//		    {
//		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				try {
					Statement stmt = DBhandler.getConnection().createStatement();
					String sql = "INSERT INTO book_details (Book_Name,Author,Ref_Id,Edition,Category) VALUES ('"
							+ txtBookName.getText() + "','" + txtAuthor.getText() + "','" + txtRefID.getText() + "','"
							+ txtEdition.getText() + "','" + txtCategory.getText() + "')";

					System.out.println(sql);
					int k = stmt.executeUpdate(sql);

					if (k == 1)

					{
						JOptionPane.showMessageDialog(contentPane, "Book added sucessfully");

						txtBookName.setText("");
						txtAuthor.setText("");
						txtRefID.setText("");
						txtEdition.setText("");
						txtCategory.setText("");
						fillTable();
					} else {
						JOptionPane.showMessageDialog(contentPane, "Error");

					}
					stmt.close();
					DBhandler.getConnection().commit();
				} catch (SQLException error) {
					JOptionPane.showMessageDialog(contentPane,"Invalid Details");
					error.printStackTrace();
				}
				            }
				        
			
		});

		btnAdd.setBounds(10, 287, 89, 23);
		contentPane.add(btnAdd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(228, 45, 362, 232);
		contentPane.add(scrollPane);

		Booktable = new JTable();

		Booktable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					Statement stmt = DBhandler.getConnection().createStatement();
					DefaultTableModel d = (DefaultTableModel) Booktable.getModel();

					String bookid = (d.getValueAt(Booktable.getSelectedRow(), 0).toString());
					String bookname = d.getValueAt(Booktable.getSelectedRow(), 1).toString();
					String author = d.getValueAt(Booktable.getSelectedRow(), 2).toString();
					String Refid = (d.getValueAt(Booktable.getSelectedRow(), 3).toString());
					String edition = (d.getValueAt(Booktable.getSelectedRow(), 4).toString());
					String Category = d.getValueAt(Booktable.getSelectedRow(), 5).toString();
					System.out.println(bookid);

					txtBookId.setText(bookid);
					txtAuthor.setText(author);
					txtBookName.setText(bookname);
					txtCategory.setText(Category);
					txtRefID.setText(Refid);
					txtEdition.setText(edition);

					stmt.close();
					DBhandler.getConnection().commit();
				} catch (SQLException error) {
					JOptionPane.showMessageDialog(null, "No Record ");
					System.out.println(error.getMessage());

				}

			}
		});
		Booktable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Book_Id", "Book_Name", "Author", "Ref_Id", "Edition", "Category" }

		));

		scrollPane.setViewportView(Booktable);
		Booktable.setBorder(new LineBorder(new Color(0, 0, 0), 2));

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBounds(467, 287, 89, 23);
		contentPane.add(btnCancel);

		JPanel Search = new JPanel();
		Search.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ref_Id",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Search.setBounds(0, 44, 218, 48);
		contentPane.add(Search);
		Search.setLayout(null);

		txtBookId = new JTextField();
		txtBookId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String a = txtBookId.getText();
		
					if (a.length() == 0) {
						JOptionPane.showMessageDialog(null, "No Record found");

					} else {
						int b;
						try {
							Statement stmt = DBhandler.getConnection().createStatement();
							System.out.println(stmt);
							String query = "SELECT * from book_details where Ref_Id like '" + a + "%';";
													
							System.out.println(query);
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
							stmt.close();
							DBhandler.getConnection().commit();
						} catch (SQLException error) {
							JOptionPane.showMessageDialog(null, "No Record ");

							System.out.println(error.getMessage());

						}

					}
				}
			}
		});
		txtBookId.setBounds(42, 17, 143, 20);
		Search.add(txtBookId);
		txtBookId.setColumns(10);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
			            int row = Booktable.getSelectedRow();
			            if (row == -1) {
			                JOptionPane.showMessageDialog(null, "No row selected.");
			                return;
			            }
			            Statement stmt = DBhandler.getConnection().createStatement();
						String query1 = "Update book_details set " + "Book_Name='" 
					+ txtBookName.getText() + "'," + "Author='" + txtAuthor.getText() + "'," +
								 "Ref_Id='" + txtRefID.getText() + "'," + "Edition='" + txtEdition.getText() +
								  "'," + "Category='" + txtCategory.getText() + "' where Book_Id= " + txtBookId.getText();			           
						PreparedStatement pstmt = DBhandler.getConnection().prepareStatement(query1);
			            
			            System.out.println(query1);

			            
			            int result = pstmt.executeUpdate();
			            System.out.println("res" + result);
			            
			            if (result > 0) {
			                JOptionPane.showMessageDialog(null, "Record updated successfully.");
			                fillTable();
			            } else {
			                JOptionPane.showMessageDialog(null, "Failed to update record.");
			            }

			            pstmt.close();
			            DBhandler.getConnection().commit();
			        } catch (SQLException error) {
			            JOptionPane.showMessageDialog(null, "Error: " + error.getMessage());
			        }
				
				}
			
		});
		btnUpdate.setBounds(161, 287, 89, 23);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = txtBookId.getText();
				int rs;
				Statement stmt;
				try {
					int row = Booktable.getSelectedRow();
					stmt = DBhandler.getConnection().createStatement();
					if (row == -1) {
		                JOptionPane.showMessageDialog(null, "No row selected.");
		                return;
		            }
					
					if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						String q = ("DELETE FROM book_details where Book_Id= " + a);					
						rs = stmt.executeUpdate(q);
						fillTable();
						System.out.println("res" + rs);
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
		btnDelete.setBounds(344, 287, 89, 23);
		contentPane.add(btnDelete);
		fillTable();
		    }
}
