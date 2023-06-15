package omepage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class manage {

	JFrame MANAGE;
	private JTextField txtTbooks;
	private JTextField txtTsudents;
	private JTextField txtTfaculty;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manage window = new manage();
					window.MANAGE.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public manage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MANAGE = new JFrame();
		MANAGE.setTitle("MANAGE");
		MANAGE.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		MANAGE.setBounds(100, 100, 745, 413);
		MANAGE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MANAGE.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 221, 374);
		panel.setBackground(new Color(204, 102, 102));
		MANAGE.getContentPane().add(panel);

		JButton btnManageBooks = new JButton(" Manage Books");
		btnManageBooks.setBounds(22, 51, 170, 30);
		btnManageBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Moving to manage books page.
				ManageBooks frame = new ManageBooks();
				frame.setVisible(true);
				MANAGE.dispose();
			}
		});
		btnManageBooks.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnManageBooks.setBackground(new Color(255, 250, 250));

		JButton btnManageFaculty = new JButton("Manage Faculty");
		btnManageFaculty.setBounds(22, 92, 170, 30);
		btnManageFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Moving to Manage faculty page.
				AddFaculty frame = new AddFaculty();
				frame.setVisible(true);
				MANAGE.dispose();
			}
		});
		btnManageFaculty.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnManageFaculty.setBackground(new Color(255, 250, 250));

		JButton btnManageStudents = new JButton("Manage Students");
		btnManageStudents.setBounds(22, 133, 170, 30);
		btnManageStudents.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnManageStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Moving to Manage students page.
				AddStudents frame = new AddStudents();
				frame.setVisible(true);
				MANAGE.dispose();
			}
		});
		btnManageStudents.setBackground(new Color(255, 250, 250));

		JButton btnIssueBooks = new JButton("Issue Books");
		btnIssueBooks.setBounds(22, 174, 170, 23);
		btnIssueBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Moving to Issue book page
				IssueBook frame = new IssueBook();
				frame.setVisible(true);
				MANAGE.dispose();
			}
		});
		btnIssueBooks.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIssueBooks.setBackground(new Color(255, 250, 250));

		JButton btnReturnBooks = new JButton("Return Books");
		btnReturnBooks.setBounds(22, 208, 170, 30);
		btnReturnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Moving to Return page.
				ReturnBooks frame = new ReturnBooks();
				frame.setVisible(true);
				MANAGE.dispose();
			}
		});
		btnReturnBooks.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReturnBooks.setBackground(new Color(255, 250, 250));

		JButton btnViewIssuedBooks = new JButton("View Issued Books");
		btnViewIssuedBooks.setBounds(22, 249, 170, 30);
		btnViewIssuedBooks.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnViewIssuedBooks.setBackground(new Color(255, 250, 250));
		btnViewIssuedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Moving to View issue page
				IssuedbooksView window = new IssuedbooksView();
				window.frmIssuedBooks.setVisible(true);
				MANAGE.dispose();
			}
		});

		JButton btnDefaulterList = new JButton("Defaulters List");
		btnDefaulterList.setBounds(22, 290, 170, 30);
		btnDefaulterList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultersList frame = new DefaultersList();
				frame.setVisible(true);
				MANAGE.dispose();
				
			}
		});
		btnDefaulterList.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDefaulterList.setForeground(new Color(0, 0, 0));
		btnDefaulterList.setBackground(new Color(255, 250, 250));

		JButton btnHome = new JButton("Home");
		btnHome.setBounds(22, 11, 170, 30);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Moving to Home page.
				homepage frame = new homepage();
				frame.setVisible(true);
				MANAGE.dispose();

			}
		});
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHome.setBackground(new Color(248, 248, 255));

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(22, 331, 170, 32);
		btnLogout.addActionListener(new ActionListener() { //Moving to Login page.
			public void actionPerformed(ActionEvent e) {
				adminlogmin frame = new adminlogmin();
				frame.setVisible(true);
				MANAGE.dispose();
			}
		});
		btnLogout.setBackground(new Color(255, 250, 250));
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.setLayout(null);
		panel.add(btnHome);
		panel.add(btnManageBooks);
		panel.add(btnManageFaculty);
		panel.add(btnManageStudents);
		panel.add(btnIssueBooks);
		panel.add(btnReturnBooks);
		panel.add(btnViewIssuedBooks);
		panel.add(btnDefaulterList);
		panel.add(btnLogout);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(220, 36, 509, 338);
		panel_1.setBackground(new Color(255, 255, 255));
		MANAGE.getContentPane().add(panel_1);

		JLabel Image = new JLabel("New label");
		Image.setBounds(101, 96, 83, 59);
		Image.setIcon(new ImageIcon("C:\\Users\\Viola\\Documents\\lib8.PNG"));

		JLabel image = new JLabel("New label");
		image.setBounds(293, 89, 70, 63);
		image.setIcon(new ImageIcon("C:\\Users\\Viola\\Documents\\lib9.PNG"));

		JLabel lblimage = new JLabel("New label");
		lblimage.setBounds(224, 244, 70, 52);
		lblimage.setIcon(new ImageIcon("C:\\Users\\Viola\\Documents\\lib10.PNG"));

		JLabel lblTB = new JLabel("Total Books");
		lblTB.setBounds(95, 58, 111, 29);
		lblTB.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblTS = new JLabel("Total Students");
		lblTS.setBounds(261, 61, 120, 22);
		lblTS.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblNewLabel_3_2 = new JLabel("Total faculty");
		lblNewLabel_3_2.setBounds(198, 211, 111, 22);
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 15));

		txtTbooks = new JTextField();
		txtTbooks.setBounds(126, 159, 46, 20);
		txtTbooks.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtTbooks.setEditable(false);
		txtTbooks.setColumns(10);

		txtTsudents = new JTextField();
		txtTsudents.setBounds(303, 163, 44, 20);
		txtTsudents.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTsudents.setEditable(false);
		txtTsudents.setColumns(10);

		txtTfaculty = new JTextField();
		txtTfaculty.setBounds(246, 307, 46, 20);
		txtTfaculty.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTfaculty.setEditable(false);
		txtTfaculty.setColumns(10);

		JLabel LblBackground = new JLabel("New label");
		LblBackground.setBounds(35, 11, 468, 327);
		LblBackground.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				try {
					Statement stmt = DBhandler.getConnection().createStatement(); // Database connection

					String sql = "SELECT count(Book_ID) as count FROM book_details"; // Query to count books
					ResultSet resultSet = stmt.executeQuery(sql);

					while (resultSet.next()) {
						txtTbooks.setText(resultSet.getString("count")); // Storing the book count from the database
					}

					String sql1 = "SELECT count(Faculty_Id) as count FROM faculty_details"; // Query to count faculty
					ResultSet resultSet1 = stmt.executeQuery(sql1);

					while (resultSet1.next()) {
						txtTfaculty.setText(resultSet1.getString("count")); // Storing the faculty count from the
																			// database
					}

					String sql3 = "SELECT count(Student_Id) as count FROM student_details"; // Query to count students
					ResultSet resultSet3 = stmt.executeQuery(sql3);
					while (resultSet3.next()) {
						txtTsudents.setText(resultSet3.getString("count")); // Storing the student count from the
																			// database
					}

					stmt.close(); // Closing the connection
				} catch (SQLException error) {
					System.out.println("Database is not connected"); // If database is not connected.
					error.printStackTrace();
				}
			}
		});
		LblBackground.setIcon(new ImageIcon("C:\\Users\\Viola\\Downloads\\lib3.jpg"));
		panel_1.setLayout(null);
		panel_1.add(image);
		panel_1.add(lblNewLabel_3_2);
		panel_1.add(txtTfaculty);
		panel_1.add(lblimage);
		panel_1.add(txtTsudents);
		panel_1.add(txtTbooks);
		panel_1.add(lblTB);
		panel_1.add(lblTS);
		panel_1.add(Image);
		panel_1.add(LblBackground);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(220, 0, 509, 35);
		panel_2.setBackground(new Color(102, 51, 51));
		MANAGE.getContentPane().add(panel_2);
	}
}
