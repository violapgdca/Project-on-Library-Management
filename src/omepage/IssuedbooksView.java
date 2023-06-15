package omepage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.Color;

public class IssuedbooksView {

	JFrame frmIssuedBooks;
	private JTable StudentBooktable;
	private JTable Facultubooktable;
	private JTable Facultytable;
	private JTable Studenttable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssuedbooksView window = new IssuedbooksView();
					//window.setLocationRelativeTo(null);
					window.frmIssuedBooks.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IssuedbooksView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIssuedBooks = new JFrame();
		frmIssuedBooks.getContentPane().setBackground(new Color(0, 0, 128));
		frmIssuedBooks.setTitle("ISSUED BOOKS");
		frmIssuedBooks.setBounds(100, 100, 745, 413);
		frmIssuedBooks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIssuedBooks.getContentPane().setLayout(null);
		
		JLabel lblissuedbooks = new JLabel("ISSUED BOOKS");
		lblissuedbooks.setForeground(new Color(245, 255, 250));
		lblissuedbooks.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblissuedbooks.setBounds(230, 17, 128, 16);
		frmIssuedBooks.getContentPane().add(lblissuedbooks);
		
		JButton btnback = new JButton("BACK");
		btnback.setBackground(new Color(255, 250, 240));
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manage window = new manage();
				window.MANAGE.setVisible(true);
				frmIssuedBooks.dispose();
			}
		});
		btnback.setBounds(487, 13, 98, 29);
		frmIssuedBooks.getContentPane().add(btnback);
		
		JButton btnlogout = new JButton("LOGOUT");
		btnlogout.setBackground(new Color(255, 250, 240));
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminlogmin frame = new adminlogmin();
				frame.setVisible(true);
				frmIssuedBooks.dispose();
			}
		});
		btnlogout.setBounds(595, 13, 103, 29);
		frmIssuedBooks.getContentPane().add(btnlogout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(23, 50, 675, 298);
		frmIssuedBooks.getContentPane().add(tabbedPane);
		
		Panel Faculty = new Panel();
		Faculty.setFont(new Font("Dialog", Font.BOLD, 10));
		tabbedPane.addTab("Faculty", null, Faculty, null);
		Faculty.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 668, 284);
		Faculty.add(scrollPane);
		
		Facultytable = new JTable();
		Facultytable.setFont(new Font("Tahoma", Font.BOLD, 13));
		Facultytable.setBackground(new Color(240, 255, 255));
		Facultytable.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				
				int b;
				try {
					Statement stmt = DBhandler.getConnection().createStatement();
					String query="SELECT * from fac_issued_books";
					ResultSet rs= stmt.executeQuery(query);
			        java.sql.ResultSetMetaData rsd = rs.getMetaData();
					 b=rsd.getColumnCount();
					
					 DefaultTableModel d=(DefaultTableModel)Facultytable.getModel();
					 d.setRowCount(0);
					 while (rs.next())
					 {
						 Vector<String> v2 = new Vector<String> ();
						 for(int i=1; i<=b; i++)
						 {
							 v2.add(rs.getString("Issue_Id"));
							 v2.add(rs.getString("Book_Id"));
							 v2.add(rs.getString("Book_Name"));
							 v2.add(rs.getString("Id"));
					         v2.add(rs.getString("Name"));
							 v2.add(rs.getString("Issue_Date"));
							 
							 
						 }
						 d.addRow(v2);
					 }
						 stmt.close();
							DBhandler.getConnection().commit();
						} catch (SQLException error) {
						System.out.println("404 Error");
						error.printStackTrace();
					
						
						
					}
				
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		Facultytable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"F_Issue_Id", "Book_Id", "Book_Name", "Faculty_Id", "Faculty_Name", "Issue_Date"
			}
			
		));
		Facultytable.getColumnModel().getColumn(0).setPreferredWidth(82);
		Facultytable.getColumnModel().getColumn(1).setPreferredWidth(82);
		Facultytable.getColumnModel().getColumn(2).setPreferredWidth(78);
	    Facultytable.getColumnModel().getColumn(3).setPreferredWidth(81);
		Facultytable.getColumnModel().getColumn(4).setPreferredWidth(87);
		scrollPane.setViewportView(Facultytable);

			
		
		
		Panel Student = new Panel();
		tabbedPane.addTab("Student", null, Student, null);
		Student.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 613, 270);
		Student.add(scrollPane_1);
		
		Studenttable = new JTable();
		Studenttable.setFont(new Font("Tahoma", Font.BOLD, 13));
		Studenttable.setBackground(new Color(255, 248, 220));
		Studenttable.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				int b;
				try {
					Statement stmt = DBhandler.getConnection().createStatement();
					String query="SELECT * from issue_books";
					ResultSet rs= stmt.executeQuery(query);
			        java.sql.ResultSetMetaData rsd = rs.getMetaData();
					 b=rsd.getColumnCount();
					
					 DefaultTableModel d=(DefaultTableModel)Studenttable.getModel();
					 d.setRowCount(0);
					 while (rs.next())
					 {
						 Vector<String> v2 = new Vector<String> ();
						 for(int i=1; i<=b; i++)
						 {
							 v2.add(rs.getString("Issue_Id"));
							 v2.add(rs.getString("Book_Id"));
							 v2.add(rs.getString("Book_Name"));
							 v2.add(rs.getString("Id"));
					         v2.add(rs.getString("Name"));
							 v2.add(rs.getString("Issue_Date"));
							 v2.add(rs.getString("Due_date"));
							 
							 
						 }
						 d.addRow(v2);
					 }
						 stmt.close();
							DBhandler.getConnection().commit();
						} catch (SQLException error) {
						System.out.println("404 Error");
						error.printStackTrace();
					
						
						
					}
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		Studenttable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Issue_Id", "Book_Id", "Book_Name", "Student_Id", "Student_Name", "Issue_Date", "Due_date"
			}
		));
		scrollPane_1.setViewportView(Studenttable);
		
	}
}
