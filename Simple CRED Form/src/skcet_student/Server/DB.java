package skcet_student.Server;

/*
create table student
(
    rno varchar2(10) primary key,
	name varchar2(15),
	password varchar2(15),
	mobile varchar2(10),
	type varchar2(15),
	dept varchar2(5)
);
*/
import skcet_student.Data.Student;
import skcet_student.Client.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DB {
	Connection con;
	String query;
	PreparedStatement ps;
	ResultSet rs;
	public Student s;
	
	public DB(){
		try{
			s = new Student();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","skcet","java");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"  Exception   -->"+e);
		}
	}
	
	public Student dbToObj(String rno){
		query= "select * from student where rno = ?";
		
		try{
		ps = con.prepareStatement(query);
		ps.setString(1,rno);
		rs = ps.executeQuery();
		
		if(rs.next()){
			s.setRno(rs.getString(1));
			s.setName(rs.getString(2));
			s.setPassword(rs.getString(3));
			s.setMobile(rs.getString(4));
			s.setCity(rs.getString(5).equals("Hosteller"));
			s.setDept(rs.getString(6));
		}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"  Exception   -->"+e);
		}
		return s;
	}
	
	public void objToDb(Student s){
		try{
			ps = con.prepareStatement(query);
			ps.setString(1,s.getRno());
			ps.setString(2,s.getName());
			ps.setString(3,s.getPassword());
			ps.setString(4,s.getMobile());
			ps.setString(5,s.getCity()?"Hosteller":"Day Schollar");
			ps.setString(6,s.getDept());
			ps.executeUpdate();	
			con.commit();
			Login.viewScreen(s);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"  Exception   -->"+e);
		}
	}
	
	public void login(String rno, String pass){
		query= "select rno, password from student where rno = ?";
		try{
			ps = con.prepareStatement(query);
			ps.setString(1,rno);
			rs = ps.executeQuery();
			if(rs.next()){
				s = dbToObj(rno);
				Login.viewScreen(s);
			}else{
				JOptionPane.showMessageDialog(null,"Invalid Credentials!");
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"  Exception   -->"+e);
		}
	}

	public void register(Student s){
		query = "insert into student values(?,?,?,?,?,?)";
		objToDb(s);
	}
	
	public void update(Student s){
		query="update student set name=?,password=?,mobile=?,type=?,dept=? where rno=?";
		objToDb(s);
	}
	
	public void delete(Student s){
		query="delete from student where eid=?";
		try{
			ps = con.prepareStatement(query);
			ps.setString(1,s.getRno());
			ps.executeUpdate();	
			con.commit();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"  Exception   -->"+e);
		}
	}
}