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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class DB {
	private Connection con;
	private String query;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public DB(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","skcet","java");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null," Database Connection Error   -->"+e);
		}
	}
	
	public Student dbToObj(String rno){
		query= "select * from student where rno = ?";
		
		try{
			ps = con.prepareStatement(query);
			ps.setString(1,rno);
			rs = ps.executeQuery();
			
			if(rs.next()){
				Student s = new Student();
				s.setRno(rs.getString(1));
				s.setName(rs.getString(2));
				s.setPassword(rs.getString(3));
				s.setMobile(rs.getString(4));
				s.setCity(rs.getString(5).equals("Hosteller"));
				s.setDept(rs.getString(6));
				return s;
			}
		}
		catch(Exception e){
			System.out.println("dbToObject ---> "+e);
		}
		return null;
	}
	
	public Student login(String rno, String pass){
		query= "select rno from student where rno = ? and password=?";
		try{
			ps = con.prepareStatement(query);
			ps.setString(1,rno);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs.next()){
				return dbToObj(rno);
			}
		}
		catch(Exception e){
			System.out.println("Login Error ---> "+e);
		}
		return null;
	}

	public Student register(Student s){
		query = "insert into student values(?,?,?,?,?,?)";
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
			return login(s.getRno(), s.getPassword());
		}
		catch(Exception e){
			System.out.println("Registration Error ---> "+e);
		}
		return null;
	}
	
	public Student update(Student s){
		query="update student set name=?,password=?,mobile=?,type=?,dept=? where rno=?";
		try{
			ps = con.prepareStatement(query);
			ps.setString(1,s.getName());
			ps.setString(2,s.getPassword());
			ps.setString(3,s.getMobile());
			ps.setString(4,s.getCity()?"Hosteller":"Day Schollar");
			ps.setString(5,s.getDept());
			ps.setString(6,s.getRno());
			ps.executeUpdate();	
			con.commit();
		}
		catch(Exception e){
			System.out.println("Updation Error ---> "+e);
		}
		return s;
	}
	
	public void delete(Student s){
		query="delete from student where rno=?";
		try{
			ps = con.prepareStatement(query);
			ps.setString(1,s.getRno());
			ps.executeUpdate();	
			con.commit();
		}
		catch(Exception e){
			System.out.println("Deletion Error ---> "+e);
		}
	}
}