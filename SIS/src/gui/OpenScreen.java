package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class OpenScreen extends DefaultTableCellRenderer {

	private JFrame frame;
	private JTable table;
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenu mnMenu;
	JMenuItem menuItem;
	JMenuItem mnýtmExit;
	JMenuItem mnýtmHelp;

	static int admin;
	static int n_user;
	private JButton btnLogout;

	/**
	 * Create the application.
	 */

	public void close1() {
		WindowEvent winClosingEvent = new WindowEvent(frame,
				WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue()
				.postEvent(winClosingEvent);
	}

	public OpenScreen(int admin) {
		this.admin = admin;
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new OpenScreen(admin);
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 470, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Open screen");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// frame.dispose();

		// General Panel
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());

		// Middle panel
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(1, 2));

		// Bottom panel
		JPanel altbottom = new JPanel();
		altbottom.setLayout(new FlowLayout());

		try {
			final Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gui", "root", "");
			Statement st = con.createStatement();
			String s = "select * from student";
			ResultSet rs = st.executeQuery(s);
			ResultSetMetaData rsmt = rs.getMetaData();
			int c = rsmt.getColumnCount();
			Vector column = new Vector<String>(c);
			for (int i = 1; i <= c; i++) {
				column.add(rsmt.getColumnName(i));
			}
			Vector data = new Vector();
			Vector row = new Vector();
			while (rs.next()) {
				row = new Vector(c);
				for (int i = 1; i <= c; i++) {
					row.add(rs.getString(i));
				}
				data.add(row);
			}
			DefaultTableModel model = new DefaultTableModel(data, column);

			table = new JTable(model);
			
			table.addMouseListener(new MouseAdapter() {
				
					
				
					
					
					
					
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					
					/*		int arr[][]=new int[table.getRowCount()][table.getColumnCount()];
					for (int i = 0; i < table.getRowCount(); i++) {
						int memo= arr[i][0];
						StudentView a=new StudentView(admin, memo);
					}*/
						
				
					int selectedRowIndex = table.getSelectedRow();
					//int selectedColumnIndex = table.getSelectedColumn();
					String selectedObject = (String) table.getModel().getValueAt(selectedRowIndex, 0);
						int b=Integer.parseInt(selectedObject);
					StudentView a=new StudentView(admin, b);
					
					
				/*
						int row=table.getSelectedRow();
						String table_click=(table.getModel().getValueAt(row, 0).toString());
						String sql = "select * from student where ID ="+ table_click+";";
						Statement st = con.prepareStatement(sql);
						ResultSet  rs = st.executeQuery(sql);
						if (rs.next()) {
						
							String add1= rs.getString("ID");
					*/
					
					
					
					
					
				}
			});
table.setCellSelectionEnabled(false);
			table.setColumnSelectionAllowed(false);
			table.setRowSelectionAllowed(false);
		
			p2.add(table);

			JScrollPane jsp = new JScrollPane(table);
			p2.add(jsp);

			final JTextField inf_text = new JTextField(10);
			altbottom.add(inf_text);

			final TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(
					table.getModel());
			table.setRowSorter(rowSorter);

			inf_text.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void changedUpdate(DocumentEvent arg0) {
					throw new UnsupportedOperationException(
							"Not supported yet.");
					// To change body of generated methods, choose Tools |
					// Templates.
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					String text = inf_text.getText();

					if (text.trim().length() == 0) {
						rowSorter.setRowFilter(null);
					} else {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"
								+ text));
					}
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					String text = inf_text.getText();

					if (text.trim().length() == 0) {
						rowSorter.setRowFilter(null);
					} else {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"
								+ text));
					}
				}

			});

			if (admin == 1) {
				final JButton register = new JButton("Add new student");
				register.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						frame.dispose();
						new AddUser(admin);

					}
				});
				altbottom.add(register);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e);
		}

		p1.add(altbottom, BorderLayout.SOUTH);

		// -----------------------------------------------------------------------------

		JButton edit = new JButton("View Students");

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new StudentView(admin, 112200032);
			}
		});

		altbottom.add(edit);

		JButton btnNewButton = new JButton();
		
		btnNewButton.setPreferredSize(new Dimension(20,20));
		btnNewButton.setBackground(new Color(255, 0, 51));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\tibo1_000\\Desktop\\log.png"));

		btnNewButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserLogin login=new UserLogin();
				
				
			}
		});
		altbottom.add(btnNewButton);
		p1.add(p2);
		frame.getContentPane().add(p1);

		// -------------------------------------------------------------------------------------------------------------------------------------------

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

				try {
					Boolean complete2 = table.print();

					if (complete2) {
						JOptionPane.showMessageDialog(null, "Done printing..");
					} else {
						JOptionPane.showMessageDialog(null,
								"Printing operation cannot be done !");
					}
				} catch (PrinterException ex) {

					Logger.getLogger(UserLogin.class.getName()).log(
							Level.SEVERE, null, ex);
				}
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

	private void close() {
		// TODO Auto-generated method stub

	}

}
