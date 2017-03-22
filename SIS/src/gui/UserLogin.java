package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class UserLogin implements Printable, ActionListener {

	private static JFrame frame;
	JMenuBar menuBar;
	JMenu menu, submenu;
	private JMenu mnMenu;
	JMenuItem menuItem;
	private JMenuItem mnýtmExit;
	private JMenuItem mnýtmHelp;

	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {

		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		/* Now print the window and its visible contents */
		frame.printAll(g);

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					UIManager.put("swing.boldMetal", Boolean.FALSE);
					UserLogin window = new UserLogin();
					window.frame.setVisible(true);
					window.frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
					window.frame.setResizable(false);
					frame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							System.exit(0);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserLogin() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();

		// When you click x button, program is closed completely.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Title top of the screen
		frame.setTitle("User Login screen");
		// Visibility
		frame.setVisible(true);

		// size of the screen
		frame.setSize(500, 180);

		// Center of the screen
		frame.setLocationRelativeTo(null);

		final JPanel p1 = new JPanel();
		p1.setBackground(SystemColor.inactiveCaption);
		frame.getContentPane().add(p1, BorderLayout.CENTER);
		p1.setLayout(new GridLayout(4, 2));

		JPanel p2 = new JPanel();
		p2.setBackground(SystemColor.inactiveCaption);
		p2.setLayout(new FlowLayout());

		p1.add(p2);

		JLabel username = new JLabel("Username: ");
		p2.add(username);

		final JTextField textusername = new JTextField(10);
		textusername.requestFocus();
		p2.add(textusername);

		JLabel textpassword = new JLabel("  Password: ");
		p2.add(textpassword);

		final JPasswordField userpassword = new JPasswordField(10);
		p2.add(userpassword);

		JPanel p3 = new JPanel();
		p3.setBackground(SystemColor.inactiveCaption);
		p1.add(p3);

		JButton button = new JButton("Admin Login");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String admin_uname = textusername.getText().trim();
				String admin_pass = new String(userpassword.getPassword());

				String correct_adminUname = "admin";
				String correct_adminPass = "123";

				if (admin_uname.equals(correct_adminUname)
						&& admin_pass.equals(correct_adminPass)) {

					// it closes the login window after login.
					frame.dispose();

					new OpenScreen(1);
				} else {
					JOptionPane.showMessageDialog(null,
							"Wrong username or password", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		p3.add(button);

		JPanel p4 = new JPanel();
		p4.setBackground(SystemColor.inactiveCaption);
		p1.add(p4);

		JButton btnNormalUser = new JButton("Normal User");
		btnNormalUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				new OpenScreen(0);
			}
		});
		p4.add(btnNormalUser);

		// active the enter key
		frame.getRootPane().setDefaultButton(button);

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}

		}

	}
}
