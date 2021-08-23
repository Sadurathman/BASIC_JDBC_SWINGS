package skcet_student.Client;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import skcet_student.Data.Student;

public class Client {
	private Socket socket;
	private ObjectInputStream response;
	private ObjectOutputStream request;
	private DataOutputStream type;
	private boolean isConnected = false;
	private Student s;
	
	public Client(){
		if(!isConnected){
			try{
				socket = new Socket("localHost", 3000);
				isConnected = true;
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Server Not Found -->"+e);
			}
		}
	}
	
	public Student login(String rno, String pass){
		try{
			type = new DataOutputStream(socket.getOutputStream());
			type.writeUTF("LOGIN");
			type.writeUTF(rno+" "+pass);
			response = new ObjectInputStream(socket.getInputStream());
			s = (Student)response.readObject();
			if(s!=null){
				Login.reset();
				Login.viewScreen(s);
				return s;
			}else{
				JOptionPane.showMessageDialog(null,"Invalid Credentials");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Login Request Failed   -->"+e);
		}
		return null;
	}
	
	public void register(Student s){
		try{
			type = new DataOutputStream(socket.getOutputStream());
			type.writeUTF("REGISTER");
			request = new ObjectOutputStream(socket.getOutputStream());	
			request.writeObject(s);
			response = new ObjectInputStream(socket.getInputStream());
			s = (Student)response.readObject();
			if(s!=null){
				Register.reset();
				JOptionPane.showMessageDialog(null,"Registration Successful");
				Login.viewScreen(s);
			}else{
				JOptionPane.showMessageDialog(null,"Registration Failed");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Register request Failed   -->"+e);
		}
	}
	
	public void update(Student s){
		try{
			type = new DataOutputStream(socket.getOutputStream());
			type.writeUTF("UPDATE");
			request = new ObjectOutputStream(socket.getOutputStream());
			request.writeObject(s);
			response = new ObjectInputStream(socket.getInputStream());
			s = (Student)response.readObject();
			if(s!=null){
				JOptionPane.showMessageDialog(null,"Update Successful");
				Login.viewScreen(s);
			}else{
				JOptionPane.showMessageDialog(null,"Update Failed ");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Update request Failed   -->"+e);
		}
	}
	
	public void delete(Student s){
		try{
			type = new DataOutputStream(socket.getOutputStream());
			type.writeUTF("DELETE");
			request = new ObjectOutputStream(socket.getOutputStream());
			request.writeObject(s);
			response = new ObjectInputStream(socket.getInputStream());
			s = (Student)response.readObject();
			if(s!=null){
				JOptionPane.showMessageDialog(null,"User: "+s.getRno()+" Deleted SuccessFully");
			}else{
				JOptionPane.showMessageDialog(null,"Delete operation Failed");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Delete request Failed   -->"+e);
		}
	}
	
	
	public static void main(String args[]){
		new Login();
	}
}


