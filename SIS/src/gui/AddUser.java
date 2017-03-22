package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JMenuBar;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class AddUser {

	int admin;

	private JFrame frame;
	JMenuBar menuBar;
	JMenu menu, submenu;
	private JMenu mnMenu;
	JMenuItem menuItem;
	private JMenuItem mnýtmExit;
	private JMenuItem mnýtmHelp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// AddUser window = new AddUser(1);
					// window.frame.setVisible(true);
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
	public AddUser(int admin) {
		this.admin = admin;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		if (admin == 1) {
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
		frame.setSize(450, 300);
		frame.setTitle("Add student");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(6, 2));
		// -----------------------------------------------------------
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		JLabel l_name = new JLabel("Name:");
		p2.add(l_name);
		
				final JTextField name = new JTextField(10);
				p2.add(name);
		
		
		
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

		// submenu-help event
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
				frame.getContentPane().add(txt_pane, BorderLayout.CENTER);
				txt_pane.setBackground(SystemColor.control);
				// txt_pane.setFont(new Font("Microsoft YaHei UI", Font.PLAIN,
				// 11));
				txt_pane.setFont(new Font("Nirmala UI Semilight", Font.PLAIN,
						12));
				txt_pane.setText("Hello, Thanks to this program, we create a system which include the student informations (SIS). "
						+ "Helps us to take some informations about student."
						+ " If you are admin you can add, delete and edit student and their informations. Unless you are admin, "
						+ "you can only see students profile. Thanks..");
				frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
				frame.setResizable(false);

				txt_pane.setToolTipText("");

			}
		});
		mnýtmHelp.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 10));
		submenu.add(mnýtmHelp);

		// creating a exit title and assign to exit the system
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
	
		// -----------------------------------------------------------
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());

		JLabel l_sname = new JLabel("Surname:");
		p3.add(l_sname);

		final JTextField surname = new JTextField(10);
		p3.add(surname);
		// -----------------------------------------------------------
		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout());

		JLabel l_dept = new JLabel("Department:");
		p4.add(l_dept);

		String[] depts = { "Select", "Justice", "Mechanical Engineering",
				"Computer Engineering", "Law", "Public Relations",
				"Electrical Engineering", "Music", "Finance", "Sociology",
				"Fashion Design", "Computer Science", "Politics Science" };

		final JComboBox department = new JComboBox(depts);
		p4.add(department);

		// -----------------------------------------------------------
		JPanel p5 = new JPanel();
		p5.setLayout(new FlowLayout());

		JLabel gender = new JLabel("Gender:");
		p5.add(gender);

		ButtonGroup bg = new ButtonGroup();
		final JRadioButton m = new JRadioButton("male");
		final JRadioButton f = new JRadioButton("female");

		bg.add(m);
		bg.add(f);

		p5.add(m);
		p5.add(f);

		// -----------------------------------------------------------
		JPanel p6 = new JPanel();
		JLabel l_photo = new JLabel("Photo:");
		p6.add(l_photo);

		final JLabel photo_directory = new JLabel();
		photo_directory.setText("-");

		JButton b_photo = new JButton("Browse");
		b_photo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser();
				c.setCurrentDirectory(new File(System.getProperty("user.home")));
				int retval = c.showOpenDialog(frame);
				if (retval == JFileChooser.APPROVE_OPTION) {
					File f = c.getSelectedFile();
					photo_directory.setText(f.getAbsolutePath());
				}

			}
		});

		p6.add(b_photo);
		p6.add(photo_directory);

		// -----------------------------------------------------------
		JPanel p7 = new JPanel();
		p7.setLayout(new FlowLayout());

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (name.getText().trim().equals("")
						|| surname.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(name,
							"Please enter name and surname");
				} else if (department.getSelectedItem().equals("Select")) {
					JOptionPane.showMessageDialog(name,
							"Please select department");
				} else if (m.isSelected() == false && f.isSelected() == false) {
					JOptionPane
							.showMessageDialog(name, "Please 	select gender");
				} else if (photo_directory.getText().equals("-")) {
					JOptionPane.showMessageDialog(name,
							"Please select an image");
				} else {
					String s_name = name.getText().trim();
					String s_surname = surname.getText().trim();
					String s_dept = "" + department.getSelectedItem();
					String s_gender;
					if (m.isSelected()) {
						s_gender = "male";
					} else {
						s_gender = "female";
					}
					String s_photo = photo_directory.getText();

					try {
						Connection con = makeConnection();
						PreparedStatement ps = (PreparedStatement) con
								.prepareStatement("INSERT INTO student VALUES(null, '"
										+ s_name
										+ "', '"
										+ s_surname
										+ "', '"
										+ s_dept
										+ "', '"
										+ s_gender
										+ "', '"
										+ s_photo + "')");

						ps.execute();

						JOptionPane
								.showMessageDialog(name,
										"Student add operation is successfully completed. ");
						frame.dispose();

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(name, "Error " + e2);
					}

				}
			}
		});
		p7.add(save);

		p1.add(p2);
		
				
		p1.add(p3);
		p1.add(p4);
		p1.add(p5);
		p1.add(p6);
		p1.add(p7);
		
		JButton btnNewButton = new JButton();
		p7.add(btnNewButton);
		btnNewButton.setPreferredSize(new Dimension(20,20));
		btnNewButton.setBackground(new Color(255, 0, 51));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\tibo1_000\\Desktop\\log.png"));

		btnNewButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserLogin login=new UserLogin();
				
				
			}
		});
		
		frame.getContentPane().add(p1);
	}

	protected static Connection makeConnection() {
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
}
