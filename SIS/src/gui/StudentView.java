package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class StudentView {

	private static JFrame frame;
	static JMenuBar menuBar;
	static JMenu submenu;
	private static JMenu mnMenu;
	static JMenuItem menuItem;
	private static JMenuItem mnýtmExit;
	private static JMenuItem mnýtmHelp;

	private int admin;;
	public int student_id;

	ArrayList<String> student_inf = new ArrayList<String>();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public StudentView(int admin, int student_id) {
		this.admin = admin;
		this.student_id = student_id;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(350, 550);
		frame.setLocationRelativeTo(null);
		if(admin==1){
			frame.setTitle("SIS (Admin login) Student ID: "+ student_id);
		}else{
		frame.setTitle("SIS (Normal User) Student ID: "+ student_id);
		}
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		// PANEL Main
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(9, 1));
	
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		// PANEL Bilgi University Information
		JPanel headerBilgi = new JPanel();
		headerBilgi.setLayout(new FlowLayout());
		JLabel bilgi = new JLabel("");
		bilgi.setIcon(new ImageIcon("C:\\Users\\tibo1_000\\Desktop\\bilgison.jpg"));
		headerBilgi.add(bilgi);

		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		// PANEL Image (Student)
		JPanel row1 = new JPanel();
		
		// buffering the image for resizing
		BufferedImage img = null;
	
		// reading the image
		try {
			img = ImageIO.read(new File(
					"C:/Users/tibo1_000/Desktop/Student_photos/" + student_id
							+ ".jpg"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"There was an error while loading the image",
					"Image Error", JOptionPane.INFORMATION_MESSAGE);
		}
			Image dimg = img.getScaledInstance(80, 180, Image.SCALE_SMOOTH);
			ImageIcon resized_st_image = new ImageIcon(dimg);
			JLabel st_image = new JLabel(resized_st_image);

			row1.setLayout(new BorderLayout(3, 1));
			row1.add(st_image);

		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		// PANEL Name (Student)
		JPanel row2 = new JPanel();
		row2.setLayout(new FlowLayout());

		// student name
		final JTextField txt_name = new JTextField(20);
		row2.add(txt_name);

		// student surname area
		JPanel row3 = new JPanel();
		row3.setLayout(new FlowLayout());

		// student surname
		final JTextField txt_surname = new JTextField(20);
		row3.add(txt_surname);
		
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		// PANEL ID (Student)
		JPanel row4 = new JPanel();
		row4.setLayout(new FlowLayout());

		// student id
		final JTextField txt_id = new JTextField(20);
		String s_id = "" + student_id;
		txt_id.setText(s_id);
		row4.add(txt_id);

		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------		
		// PANEL Department
		JPanel row5 = new JPanel();
		row5.setLayout(new FlowLayout());

		// student department
		final JTextField txt_department = new JTextField(20);
		row5.add(txt_department);

		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		// PANEL Edit Button Area
		JPanel row6 = new JPanel();
		row6.setLayout(new FlowLayout());

		// delete button
		final JButton btn_delete = new JButton("Delete");
		// save button
		final JButton btn_save = new JButton("Save");
		// change image button
		final JButton btn_changeImage = new JButton("Change Image");
		// edit button
		final JButton btn_edit = new JButton("Edit");
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_name.setEnabled(true);
				txt_surname.setEnabled(true);
				txt_id.setEnabled(false);
				txt_department.setEnabled(true);

				btn_delete.setVisible(true);
				btn_save.setVisible(true);
				btn_changeImage.setVisible(true);
				btn_edit.setVisible(false);
			}
		});
		row6.add(btn_edit);

		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		// PANEL Delete  Area
		JPanel row7 = new JPanel();
		row7.setLayout(new FlowLayout());

		if (admin == 1) {
			row7.add(btn_delete);
			row7.add(btn_save);
			row7.add(btn_changeImage);
		}

		if (admin == 1) {
			btn_edit.setVisible(true);
		} else {
			btn_edit.setVisible(false);
		}

		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_name.setEnabled(false);
				txt_surname.setEnabled(false);
				txt_id.setEnabled(false);
				txt_department.setEnabled(false);

				btn_delete.setVisible(false);
				btn_save.setVisible(false);
				btn_changeImage.setVisible(false);
				if (admin == 1) {
					btn_edit.setVisible(true);
				}
			}
		});

		txt_name.setEnabled(false);
		txt_surname.setEnabled(false);
		txt_id.setEnabled(false);
		txt_department.setEnabled(false);

		btn_delete.setVisible(false);
		btn_save.setVisible(false);
		btn_changeImage.setVisible(false);
	
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		// PANEL Photo Path Change Info Area
		JPanel row8 = new JPanel();
		row8.setLayout(new FlowLayout());

		// Loading data from database to application for default
		try {
			ArrayList<String> st_details = new ArrayList<String>();
			st_details = getStudentDetails(student_id);

			txt_name.setText(st_details.get(0));
			txt_surname.setText(st_details.get(1));
			txt_department.setText(st_details.get(2));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"There is an error occured while fetching the data!",
					"Error", JOptionPane.ERROR_MESSAGE);
		}

			btn_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = getConnection();
					PreparedStatement post = connection
							.prepareStatement("DELETE FROM student WHERE ID = " + student_id);
					post.executeUpdate();
					connection.close();
					post.close();

					JOptionPane.showMessageDialog(null, "Student sucessfully deleted", "Error", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error while deleting user" + e2, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

			btn_changeImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter( "JPG & PNG Images Only", "jpg", "png");
				chooser.setFileFilter(filter);
				// current directory is users home directory
				chooser.setCurrentDirectory(new File(System.getProperty("C:/User/Desktop")));
				int option = chooser.showOpenDialog(frame);
			
				if (option == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
				}
			}
		});
			
			btn_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txt_name.getText().trim();
				String surname = txt_surname.getText().trim();
				String department = txt_department.getText().trim();

				try {
					Connection connection = getConnection();
					PreparedStatement post = connection
							.prepareStatement("UPDATE student SET Name = '"
									+ name + "', Surname = '" + surname
									+ "', Department = '" + department
									+ "' WHERE ID = " + student_id);
					post.executeUpdate();
					connection.close();
					post.close();

					JOptionPane.showMessageDialog(null, "Student edited successfully!", "Successfull", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "Error while saving data. Error: " + e3, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		mainPanel.add(headerBilgi);
		mainPanel.add(row1);
		mainPanel.add(row2);
		mainPanel.add(row3);
		mainPanel.add(row4);
		mainPanel.add(row5);
		mainPanel.add(row6);
		
		
		JButton btn_logout = new JButton();
		row6.add(btn_logout);
		btn_logout.setPreferredSize(new Dimension(20,20));
		btn_logout.setIcon(new ImageIcon("C:\\Users\\tibo1_000\\Desktop\\log.png"));
		
				btn_logout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						UserLogin login=new UserLogin();
					}
				});
		mainPanel.add(row7);
		
				mainPanel.add(row8);
		
				
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		// CREATING MENU BAR
		// Create the menu bar.
		menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);

		// Build the first menu.
		mnMenu = new JMenu("File");
		mnMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 10));
		mnMenu.setMnemonic(KeyEvent.VK_A);
		mnMenu.getAccessibleContext().setAccessibleDescription(
				"The only menu in this program that has menu items");
		menuBar.add(mnMenu);

		// a group of JMenuItems
		menuItem = new JMenuItem("Print");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
		});

		menuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 10));

		submenu = new JMenu("Options");
		submenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 10));

		// HELP TITLE (SUB-MENU)
		mnýtmHelp = new JMenuItem("Help");
			mnýtmHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frame = new JFrame();
			
				// Title top of the screen
				frame.setTitle("Help");
				
				// Visibility
				frame.setVisible(true);
				
				// size of the screen
				frame.setSize(215, 300);
				
				// Center of the screen
				frame.setLocationRelativeTo(null);

				JPanel panel = new JPanel();
				panel.setBackground(Color.LIGHT_GRAY);
				panel.setLayout(new FlowLayout());
				frame.getContentPane().add(panel, BorderLayout.CENTER);

				JTextPane txt_pane = new JTextPane();
				txt_pane.setEditable(false);
				txt_pane.setBackground(SystemColor.control);
				txt_pane.setFont(new Font("Nirmala UI Semilight", Font.PLAIN,
						12));
				txt_pane.setText("Hello, Thanks to this program, we create a system which include the student informations (SIS). "
						+ "Helps us to take some informations about student."
						+ " If you are admin you can add, delete and edit student and their informations. Unless you are admin, "
						+ "you can only see students profile. Thanks..");
				frame.getContentPane().add(txt_pane, BorderLayout.CENTER);
				frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
				frame.setResizable(false);
			}
		});

		mnýtmHelp.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 10));
		submenu.add(mnýtmHelp);

		// EXIT TITLE (SUB-MENU)
		mnýtmExit = new JMenuItem("Exit");
		mnýtmExit.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 10));
		mnýtmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});

		submenu.add(mnýtmExit);
		mnMenu.add(submenu);

		// a submenu
		mnMenu.addSeparator();
		mnMenu.add(menuItem);

		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(mainPanel);
	}

	/**
	 * THIS METHOD PROVIDES TO CONNECT THE DATABASE
	 * 
	 * @return
	 * @throws Exception
	 */
	protected static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String databaseName = "gui";
			String url = "jdbc:mysql://localhost:3306/" + databaseName;
			String db_username = "root";
			String db_pass = "";

			Class.forName(driver);

			Connection connection = DriverManager.getConnection(url,
					db_username, db_pass);
			return connection;

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		return null;
	}

	/**
	 * THIS METHOD RETRIEVES THE STUDENTS DETAILS WITH GIVEN ID.
	 * 
	 * @return
	 * @throws Exception
	 */
	protected static ArrayList<String> getStudentDetails(int id)
			throws Exception {
		try {
			Connection connection = getConnection();
			PreparedStatement get = connection
					.prepareStatement("SELECT * FROM student WHERE ID = " + id);

			ResultSet result = get.executeQuery();

			ArrayList<String> studentDetails = new ArrayList<String>();

			String name = "";
			String surname = "";
			String department = "";

			if (result.next()) {
				name = result.getString("Name");
				surname = result.getString("Surname");
				department = result.getString("Department");

				studentDetails.add(name);
				studentDetails.add(surname);
				studentDetails.add(department);
			}
				connection.close();
				get.close();
				result.close();
				return studentDetails;
	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Oops there is an error while retrieving the student details. Error code:"
							+ e, "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;

	}

}
