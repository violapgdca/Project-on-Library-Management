package omepage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class homepage extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookNameauthor;
	private JTable Display_table;

	/**
	 * Launch the application.
	 */
	
	
	private void fillTable() {
		int b;
		try {
			Statement stmt = DBhandler.getConnection().createStatement(); // Displaying the table from the database.
			String query = "SELECT * from book_details";
			ResultSet rs = stmt.executeQuery(query);
			java.sql.ResultSetMetaData rsd = rs.getMetaData();
			b = rsd.getColumnCount();

			DefaultTableModel d = (DefaultTableModel) Display_table.getModel();
			d.setRowCount(0);

			while (rs.next()) {
				Vector<String> v2 = new Vector<String>();
				for (int i = 1; i <= b; i++) {
					v2.add(rs.getString("Book_Id"));
					v2.add(rs.getString("Book_Name"));
					v2.add(rs.getString("Author"));
					v2.add(rs.getString("Category"));
					v2.add(rs.getString("Status"));
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homepage frame = new homepage();// Calling the constructor.
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					//frame.setSize(1650,1080);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	private void clearFields() {
		txtBookNameauthor.setText(null); // To clear the text fields.
	}

	public homepage() {
		setTitle("HOME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 358);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 729, 46);
		panel.setBackground(new Color(255, 222, 173));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbllibrary = new JLabel("THE LIBRARY MANGEMENT ");
		lbllibrary.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbllibrary.setBounds(0, 11, 491, 35);
		panel.add(lbllibrary);

		JButton btnAbminLogin = new JButton("");
		btnAbminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminlogmin nw = new adminlogmin(); // On click moving to Login page
				nw.setVisible(true);
				dispose();

			}

		});
		btnAbminLogin.setIcon(new ImageIcon(
				"C:\\Users\\Viola\\Desktop\\eclipse\\LibraryManagement\\src\\omepage\\Images\\lib21.PNG"));
		btnAbminLogin.setBounds(683, 11, 36, 25);
		panel.add(btnAbminLogin);
		btnAbminLogin.setBackground(UIManager.getColor("Button.disabledForeground"));

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homepage n = new homepage();
				n.setVisible(true); // Moving to Home page.
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(
				"C:\\Users\\Viola\\Desktop\\eclipse\\LibraryManagement\\src\\omepage\\Images\\Capture.PNG"));
		btnNewButton.setBounds(644, 11, 29, 25);
		panel.add(btnNewButton);
		btnNewButton.setBackground(UIManager.getColor("Button.disabledForeground"));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(248, 36, 234, -21);
		panel_1.setBackground(new Color(153, 102, 102));
		contentPane.add(panel_1);

		JPanel SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search By Book name/Author name", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		SearchPanel.setBounds(97, 57, 540, 46);
		contentPane.add(SearchPanel);
		SearchPanel.setLayout(null);

		txtBookNameauthor = new JTextField();
		txtBookNameauthor.setBounds(22, 15, 455, 20);
		SearchPanel.add(txtBookNameauthor);
		txtBookNameauthor.setColumns(10);
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(487, 12, 43, 23);
		SearchPanel.add(btnNewButton_1);
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Viola\\Desktop\\eclipse\\LibraryManagement\\src\\omepage\\Images\\search.PNG"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = txtBookNameauthor.getText();
				System.out.println(a);
				if (a.length() == 0) {
					JOptionPane.showMessageDialog(null, "Enter Book name/Author");
				} else {
					int b;
					try {
						Statement stmt = DBhandler.getConnection().createStatement();
						String query = "SELECT * from book_details where Book_Name like '" + a + "%' or Author like '"
								+ a + "%';"; // Query to search by Book name or Author.
						ResultSet rs = stmt.executeQuery(query);
						if (rs.isBeforeFirst() == false) {
							JOptionPane.showMessageDialog(null, "No Record found"); // If the Query is false.
						} else {
							java.sql.ResultSetMetaData rsd = rs.getMetaData();
							b = rsd.getColumnCount();
							DefaultTableModel d = (DefaultTableModel) Display_table.getModel();
							d.setRowCount(0);
							while (rs.next()) // Displaying the table.
							{
								Vector<String> v2 = new Vector<String>();
								for (int i = 1; i <= b; i++) {
									v2.add(rs.getString("Book_Id"));
									v2.add(rs.getString("Book_Name"));
									v2.add(rs.getString("Author"));
									v2.add(rs.getString("Category"));
									v2.add(rs.getString("Status"));
									clearFields();
								}
								d.addRow(v2);
							}
						}
						stmt.close(); // Closing the database connection.
						DBhandler.getConnection().commit();
					} catch (SQLException error) {
						JOptionPane.showMessageDialog(null, "Database is not connected"); // If there is no connection
						error.printStackTrace();																	// with the database.
					}
				}
			}
		});

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 0, 0));
		panel_3.setBounds(0, 46, 492, 1);
		contentPane.add(panel_3);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 131, 483, 177);
		contentPane.add(scrollPane);

		Display_table = new JTable(); // Displaying the table.
		Display_table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Book_Id", "Book_Name", "Author", "Category", "Status" }));
		scrollPane.setViewportView(Display_table);
		
		JLabel Image = new JLabel("New label");
		Image.setBounds(86, 115, 559, 204);
		Image.setIcon(new ImageIcon("C:\\Users\\Viola\\Downloads\\lib4.png"));
		contentPane.add(Image);
		fillTable();
	}
}
