package skcet_student.Client;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import skcet_student.Data.Student;

public class viewUI {;
	private JLabel userLabel, headerLabel, rnoLabel, mobileLabel, deptLabel, hdLabel;
	private JLabel userInput, rnoInput, mobileInput, deptInput, hdInput;
	
	public viewUI(JPanel rightPanel, Student s){
		headerLabel = new JLabel("---Student Detail---");
		userLabel = new JLabel("Name :");
		rnoLabel = new JLabel("Register Number :");
		mobileLabel = new JLabel("Mobile Number :");
		deptLabel = new JLabel("Department :");
		hdLabel = new JLabel("Day Schollar/ Hosteller :");
		
		
		userInput = new JLabel(s.getName());
		rnoInput = new JLabel(s.getRno());
		mobileInput = new JLabel(s.getMobile());
		deptInput = new JLabel(s.getDept());
		hdInput = new JLabel(s.getCity()?"Hosteller":"Day Schollar");
		
		
		Font style = new Font("Calibri", Font.BOLD, 18);
		
		headerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		headerLabel.setForeground(new Color(102,102,102));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setFont(style);
		userInput.setFont(style);
		rnoLabel.setFont(style);
		rnoInput.setFont(style);
		mobileLabel.setFont(style);
		mobileInput.setFont(style);
		deptLabel.setFont(style);
		deptInput.setFont(style);
		hdLabel.setFont(style);
		hdInput.setFont(style);
	
		headerLabel.setBounds(0,50,500,50);
		userLabel.setBounds(50,210,200,25);
		userInput.setBounds(250,210,150,25);
		rnoLabel.setBounds(50,240,200,25);
		rnoInput.setBounds(250,240,150,25);
		mobileLabel.setBounds(50,270,200,25);
		mobileInput.setBounds(250,270,150,25);
		deptLabel.setBounds(50, 300, 200, 25);
		deptInput.setBounds(250, 300, 150, 25);
		hdLabel.setBounds(50, 330, 200, 25);
		hdInput.setBounds(250, 330, 150, 25);
				
		rightPanel.setBackground(Color.white);
		
		rightPanel.add(headerLabel);
		rightPanel.add(userLabel);
		rightPanel.add(userInput);
		rightPanel.add(rnoLabel);
		rightPanel.add(rnoInput);
		rightPanel.add(hdLabel);
		rightPanel.add(hdInput);
		rightPanel.add(deptInput);
		rightPanel.add(deptLabel);
		rightPanel.add(mobileInput);
		rightPanel.add(mobileLabel);
	}

}
