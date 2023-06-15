package omepage;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class DefaultersList extends JFrame {

	private JPanel contentPane;
	private JTable Defaultertable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefaultersList frame = new DefaultersList();
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
	 * @throws ParseException 
	 */
	
	
	private void fillTable() {
		int b;
		try {
			Statement stmt = DBhandler.getConnection().createStatement(); // Displaying the table from the database.
			 
			String query="SELECT DATEDIFF(CURDATE(), Due_date) as days,Book_id,Book_Name,Id,Name,Issue_Date,Due_date FROM issue_books WHERE DATEDIFF(CURDATE(), Due_date) > 0 LIMIT 0, 1000";
			
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			java.sql.ResultSetMetaData rsd = rs.getMetaData();
			b = rsd.getColumnCount();

			DefaultTableModel d = (DefaultTableModel) Defaultertable.getModel();
			d.setRowCount(0);

			while (rs.next()) {
				Vector<String> v2 = new Vector<String>();
				for (int i = 1; i <= b; i++) {
					
					v2.add(rs.getString("Book_Id"));
					v2.add(rs.getString("Book_Name"));
					v2.add(rs.getString("Id"));
					v2.add(rs.getString("Name"));
					v2.add(rs.getString("Issue_Date"));
					v2.add(rs.getString("Due_date"));
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
	public DefaultersList() {
		setTitle("DEFAULTER LIST");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 215, 0));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(0, 0, 729, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbldefaulter = new JLabel("DEFAULTERS  LIST");
		lbldefaulter.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldefaulter.setBounds(251, 11, 181, 18);
		panel.add(lbldefaulter);
		
		JButton btnback = new JButton("BACK");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manage window = new manage();
				window.MANAGE.setVisible(true);
				dispose();
			}
		});
		btnback.setBackground(SystemColor.window);
		btnback.setBounds(531, 11, 89, 23);
		panel.add(btnback);
		
		JButton btnlogout = new JButton("LOGOUT");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminlogmin frame = new adminlogmin();
				frame.setVisible(true);
				dispose();
			}
		});
		btnlogout.setBackground(SystemColor.window);
		btnlogout.setBounds(630, 11, 89, 23);
		panel.add(btnlogout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 44, 729, 330);
		contentPane.add(scrollPane);
		
		Defaultertable = new JTable();
		Defaultertable.setForeground(new Color(255, 0, 0));
		Defaultertable.setBackground(new Color(240, 255, 255));
		Defaultertable.setFont(new Font("Tahoma", Font.BOLD, 11));
		Defaultertable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book_Id", "Book_Name", "Student_Id", "Student_Name", "Issue_Date", "Due_Date"
			}
		));
		scrollPane.setViewportView(Defaultertable);
		fillTable(); // Display the table in JTable.
	}
}
