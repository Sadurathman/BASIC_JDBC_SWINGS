package skcet_student.Client;

import skcet_student.Data.Student;
import skcet_student.Server.DB;

import javax.swing.*;
import javax.swing.border.MatteBorder;



import java.awt.*;
import java.awt.event.*;


public class Login implements ActionListener{
	
	private static JFrame frame;
	private static JPanel leftPanel, rightPanel, registerPanel, viewPanel, updatePanel;
	private JLabel userIcon, userLabel, passLabel, headerLabel, welcomeLabel;
	private static JLabel viewName;
	private JTextField userInput;
	private JPasswordField passInput;
	private JButton cancelButton, loginButton, logoutButton, registerButton, registerSwapButton, loginSwapButton, updateButton, deleteButton, editButton;
	private DB db;
	private static Student s;
	private Register register;
	
	public Login(){
		//Initialization
		db = new DB();
		frame = new JFrame("SKCET Student Portal");
		frame.setSize(1000,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFont(new Font("Verdana", Font.BOLD, 12));
		
		userIcon = new JLabel(new ImageIcon(new ImageIcon("Images/User.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));

		userLabel = new JLabel("Username");
		passLabel = new JLabel("Password");
		headerLabel = new JLabel("Login");
		welcomeLabel = new JLabel("SKCET OFFICIAL");
		viewName =  new JLabel("");
		
		userInput = new JTextField();
		passInput = new JPasswordField();
		
		loginButton = new JButton("Login");
		cancelButton = new JButton("Cancel");
		logoutButton = new JButton("Logout");
		editButton = new JButton("Edit");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		loginSwapButton = new JButton("Login");
		registerButton = new JButton("Register");
		registerSwapButton = new JButton("Register");
		
		leftPanel = new JPanel();
		viewPanel = new JPanel();
		rightPanel = new JPanel();
		updatePanel = new JPanel();
		registerPanel = new JPanel();
		Font style = new Font("Calibri", Font.BOLD, 20);
		
		//Styling
		userInput.setBorder(new MatteBorder(0, 0, 2, 0, Color.GRAY));
		passInput.setBorder(new MatteBorder(0, 0, 2, 0, Color.GRAY));
		
		leftPanel.setLayout(null);
		viewPanel.setLayout(null);
		updatePanel.setLayout(null);
		rightPanel.setLayout(null);
		registerPanel.setLayout(null);
		
		leftPanel.setBounds(0,0,500,720);
		viewPanel.setBounds(500,0,1000,720);
		updatePanel.setBounds(500,0,1000,720);
		rightPanel.setBounds(500,0,1000,720);
		registerPanel.setBounds(500,0,1000,720);
		
		welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 36));
		viewName.setFont(new Font("Calibri", Font.BOLD, 36));
		
		headerLabel.setFont(new Font("Calibri", Font.BOLD, 36));
		headerLabel.setForeground(new Color(102,102,102));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		userLabel.setFont(style);
		passLabel.setFont(style);
		userInput.setFont(style);
		loginButton.setFont(style);
		cancelButton.setFont(style);
		loginSwapButton.setFont(style);
		registerButton.setFont(style);
		registerSwapButton.setFont(style);
		updateButton.setFont(style);
		deleteButton.setFont(style);
		editButton.setFont(style);
		logoutButton.setFont(style);
		
		welcomeLabel.setForeground(Color.white);
		viewName.setForeground(Color.white);
		
		//setBounds
		userIcon.setBounds(100, 100, 300, 350);
		viewName.setBounds(0,500,500,100);
		viewName.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(0,370,500,50);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		headerLabel.setBounds(0,50,500,50);
		userLabel.setBounds(100,150,200,25);
		userInput.setBounds(100,180,200,25);
		passLabel.setBounds(100,220,200,25);
		passInput.setBounds(100,250,200,25);
		
		registerSwapButton.setBounds(100,300,105,30);
		loginButton.setBounds(230,300,105,30);

		registerButton.setBounds(100,400,105,30);
		loginSwapButton.setBounds(230,400,105,30);
		
		cancelButton.setBounds(230,400,105,30);
		updateButton.setBounds(100,400,105,30);
		
		deleteButton.setBounds(50,620,100,30);
		editButton.setBounds(200,620,100,30);
		logoutButton.setBounds(350,620,100,30);

		//ActionListeners
		loginButton.addActionListener(this);
		loginSwapButton.addActionListener(this);
		registerSwapButton.addActionListener(this);
		registerButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		editButton.addActionListener(this);
		cancelButton.addActionListener(this);
		logoutButton.addActionListener(this);

		leftPanel.setBackground(new Color(0, 102, 204));
		rightPanel.setBackground(Color.white);
		updatePanel.setBackground(Color.white);
		viewPanel.setBackground(Color.white);
	
		//Adding to Panel
		leftPanel.add(viewName);
		leftPanel.add(welcomeLabel);
		leftPanel.add(userIcon);
		
		rightPanel.add(headerLabel);
		rightPanel.add(userLabel);
		rightPanel.add(passLabel);
		rightPanel.add(userInput);
		rightPanel.add(passInput);
		rightPanel.add(loginButton);
		rightPanel.add(registerSwapButton);
		
		viewPanel.add(deleteButton);
		viewPanel.add(editButton);
		viewPanel.add(logoutButton);
		
		register = new Register(registerPanel);
		registerPanel.add(loginSwapButton);
		registerPanel.add(registerButton);
		
		updatePanel.add(updateButton);
		updatePanel.add(cancelButton);
		
		viewPanel.setVisible(false);
		updatePanel.setVisible(false);
		
		
		//Adding to Frame
		frame.add(leftPanel);		
		frame.add(rightPanel);
		frame.add(registerPanel);
		frame.add(updatePanel);
		frame.add(viewPanel);
		
		//Customizing Frame
 		frame.setLayout(null);
		frame.setVisible(true);
		
		centreWindow(frame);
	}
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public void actionPerformed(ActionEvent e){
		Object button = e.getSource();
		
		if(button == loginButton){
			db.login(userInput.getText(), new String(passInput.getPassword()));
		}	
		if(button == registerButton && register.Validate()){
			s = register.UiToObj();
			db.register(s);
		}
		if(button == updateButton && register.Validate()){
			s = register.UiToObj();
			db.update(s);
		}		
		if(button == deleteButton){
			db.delete(s);
			viewPanel.setVisible(false);
			rightPanel.setVisible(true);
		}
		if(button == registerSwapButton){
			rightPanel.setVisible(false);
			registerPanel.setVisible(true);
		}
		if(button == loginSwapButton){
			registerPanel.setVisible(false);
			rightPanel.setVisible(true);
		}
		if(button == editButton){
			viewPanel.setVisible(false);
			new Register(updatePanel).update(s);
			updatePanel.setVisible(true);
		}
		if(button == logoutButton){
			frame.dispose();
			new Login();
		}
		if(button == cancelButton){
			updatePanel.setVisible(false);
			viewPanel.setVisible(true);
		}
	}
	
	public static void viewScreen(Student s){
		Login.s = s;
		rightPanel.setVisible(false);
		registerPanel.setVisible(false);
		updatePanel.setVisible(false);		
		
		viewName.setText("Welcome "+s.getName());
		new viewUI(viewPanel,s);
		
		viewPanel.setVisible(true);
		leftPanel.setVisible(true);
	}
	
	public static void main(String arg[]){
		new Login();
	}
}