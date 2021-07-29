package skcet_student.Client;

import skcet_student.Data.Student;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register implements FocusListener, ItemListener, ActionListener{
	private JPasswordField passInput, repassInput;
	private JLabel userLabel, passLabel,repassLabel, headerLabel, rnoLabel, mobileLabel, deptLabel, hdLabel;
	private JTextField userInput, rnoInput, mobileInput;
	private ButtonGroup bg;
	private JRadioButton rdbDs, rdbHs;
	private JComboBox<String> dept;
	
	Register(JPanel rightPanel){
		userLabel = new JLabel("Name :");
		passLabel = new JLabel("Password :");
		repassLabel = new JLabel("Re-Type Password :");
		headerLabel = new JLabel("Register");
		rnoLabel = new JLabel("Register Number :");
		mobileLabel = new JLabel("Mobile Number :");
		deptLabel = new JLabel("Department :");
		hdLabel = new JLabel("Day Schollar/ Hosteller :");
		
		
		userInput = new JTextField();
		passInput = new JPasswordField();
		repassInput = new JPasswordField();
		rnoInput = new JTextField();
		mobileInput = new JTextField();
		
		rdbDs = new JRadioButton("Day Schollar");
		rdbHs = new JRadioButton("Hosteller");
		
		bg = new ButtonGroup();
		bg.add(rdbDs);
		bg.add(rdbHs);
		
		dept = new JComboBox<String>();
		
		dept.addItem("select");
		dept.addItem("CSBS");
		dept.addItem("CSE");
		dept.addItem("IT");
		dept.addItem("ECE");
		dept.addItem("EEE");
		
		Font style = new Font("Calibri", Font.BOLD, 18);
		
		headerLabel.setFont(new Font("Calibri", Font.BOLD, 36));
		headerLabel.setForeground(new Color(102,102,102));
		userLabel.setFont(style);
		passLabel.setFont(style);
		userInput.setFont(style);
		passInput.setFont(style);
		repassLabel.setFont(style);
		repassInput.setFont(style);
		rnoLabel.setFont(style);
		rnoInput.setFont(style);
		mobileLabel.setFont(style);
		mobileInput.setFont(style);
		deptLabel.setFont(style);
		dept.setFont(style);
		hdLabel.setFont(style);
		rdbDs.setFont(style);
		rdbHs.setFont(style);

		userInput.addFocusListener(this);
		passInput.addFocusListener(this);
		repassInput.addFocusListener(this);
		rnoInput.addFocusListener(this);
		mobileInput.addFocusListener(this);
		rdbHs.addItemListener(this);
		rdbDs.addItemListener(this);
		dept.addActionListener(this);
		
		headerLabel.setBounds(0,50,500,50);
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		userLabel.setBounds(50,150,200,25);
		userInput.setBounds(250,150,150,25);
		passLabel.setBounds(50,180,200,25);
		passInput.setBounds(250,180,150,25);
		repassLabel.setBounds(50,210,200,25);
		repassInput.setBounds(250,210,150,25);
		rnoLabel.setBounds(50,240,200,25);
		rnoInput.setBounds(250,240,150,25);
		mobileLabel.setBounds(50,270,200,25);
		mobileInput.setBounds(250,270,150,25);
		deptLabel.setBounds(50, 300, 200, 25);
		dept.setBounds(250, 300, 150, 25);
		hdLabel.setBounds(50, 330, 200, 25);
		rdbDs.setBounds(250, 330, 100, 25);
		rdbHs.setBounds(350, 330, 100, 25);
				
		rightPanel.setBackground(Color.white);
		
		rightPanel.add(headerLabel);
		rightPanel.add(userLabel);
		rightPanel.add(userInput);
		rightPanel.add(passLabel);
		rightPanel.add(passInput);
		rightPanel.add(repassLabel);
		rightPanel.add(repassInput);
		rightPanel.add(rnoLabel);
		rightPanel.add(rnoInput);
		rightPanel.add(rdbDs);
		rightPanel.add(rdbHs);
		rightPanel.add(hdLabel);
		rightPanel.add(dept);
		rightPanel.add(deptLabel);
		rightPanel.add(mobileInput);
		rightPanel.add(mobileLabel);
		
		rightPanel.setVisible(false);
	}
	
	public void update(Student s){
		headerLabel.setText("Update");
		userInput.setText(s.getName());
		passLabel.setText("Old Password:");
		repassLabel.setText("new Password:");
		rnoInput.setText(s.getRno());
		rnoInput.setEditable(false);
		mobileInput.setText(s.getMobile());
		dept.setSelectedItem(s.getDept());
		
		boolean Hosteller = s.getCity();
		if(Hosteller) rdbHs.setSelected(true);
		else rdbDs.setSelected(true);
	}
	
	public Student UiToObj(){
		Student s = new Student();
		s.setRno(rnoInput.getText());
		s.setName(userInput.getText());
		s.setPassword(new String(passInput.getPassword()));
		s.setMobile(mobileInput.getText());
		if(rdbHs.isSelected())s.setCity(true);
		if(rdbDs.isSelected())s.setCity(false);
		s.setDept(dept.getSelectedItem().toString());
		return s;
	}
	
	public boolean Validate(){
		boolean valid = true;
		
		if(userInput.getText().length()==0){
			userInput.setForeground(Color.RED);
			userLabel.setForeground(Color.RED);
			valid = false;
		}
		if(new String(passInput.getPassword()).equals(new String(repassInput.getPassword()))){
			Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$");
			Matcher match = pattern.matcher(new String(passInput.getPassword()));
			if(!match.matches()){
				passInput.setForeground(Color.red);
				passLabel.setForeground(Color.red);
				repassLabel.setForeground(Color.red);
				repassInput.setForeground(Color.red);
				valid = false;
			}
		}else{
			passInput.setForeground(Color.red);
			passLabel.setForeground(Color.red);
			repassLabel.setForeground(Color.red);
			repassInput.setForeground(Color.red);
			valid = false;
		}
		
		Pattern pattern = Pattern.compile("[0-9]{2}EU[CIE][BCEST][0-9]{3}");
		Matcher match = pattern.matcher(rnoInput.getText());
		if(!match.matches()){
			rnoInput.setForeground(Color.red);			
			rnoLabel.setForeground(Color.red);			
			valid = false;
		}
		
		if(dept.getSelectedIndex()==0){
			dept.setForeground(Color.red);
			deptLabel.setForeground(Color.red);
			valid = false;
		}
		if(mobileInput.getText().length()!=10){
			mobileInput.setForeground(Color.red);
			mobileLabel.setForeground(Color.red);
			valid = false;
		}
		
		if(!rdbHs.isSelected()&&!rdbDs.isSelected()){
			hdLabel.setForeground(Color.red);
			valid = false;
		}
		return valid;
	}

	@Override
	public void focusGained(FocusEvent e) {
        Object obj = e.getComponent();
        e.getComponent().setForeground(Color.BLACK);
        if(obj==userInput) userLabel.setForeground(Color.BLACK); 
        if(obj==passInput) passLabel.setForeground(Color.BLACK);
        if(obj==repassInput) repassLabel.setForeground(Color.BLACK);
        if(obj==rnoInput) rnoLabel.setForeground(Color.BLACK);
        if(obj==mobileInput) mobileLabel.setForeground(Color.BLACK);
    }

	@Override
    public void focusLost(FocusEvent e) {
        e.getComponent().setForeground(Color.BLACK);
    }
	
	@Override
	public void itemStateChanged(ItemEvent ie){
		hdLabel.setForeground(Color.BLACK);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dept.setForeground(Color.BLACK);		
		deptLabel.setForeground(Color.BLACK);		
	}
}
