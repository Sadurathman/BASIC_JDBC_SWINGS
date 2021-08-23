package skcet_student.Server;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import skcet_student.Data.Student;

public class Server {
	private ServerSocket serverSocket;
	private Socket client;
	private static DB db;
	private static int PORT = 3000;
	
	public Server(){
		try{
			db = new DB();
			serverSocket = new ServerSocket(PORT);
			serverSocket.setReuseAddress(true);
			System.out.println("Server Started on PORT : "+PORT);
			while(true){
				client = serverSocket.accept();
				ClientHandler clientSocket = new ClientHandler(client);
				new Thread(clientSocket).start();
			}
		}catch(Exception e){
			System.out.println("Server Start Error ---> "+e);
		}finally{
			if(serverSocket != null){
				try{
					serverSocket.close();
				}catch(Exception e){
					System.out.println("Server Finally Error ---> "+e);					
				}
			}
		}
	}
	
	public static void main(String[] args){
		new Server();
	}
	
	private static class ClientHandler implements Runnable{
		private final Socket clientSocket;
		
		public ClientHandler(Socket socket){
			this.clientSocket = socket;
		}
		
		public void run(){
			DataInputStream request = null;
			ObjectOutputStream response = null;
			Student s = null;
			try{
				request = new DataInputStream(clientSocket.getInputStream());
				String req = request.readUTF();
				System.out.println(req+" request from Client "+clientSocket.getInetAddress().getHostAddress());
				switch(req){
					case "LOGIN": DataInputStream credentials = new DataInputStream(clientSocket.getInputStream());
								  String[] user_pass = credentials.readUTF().split(" ");
								  System.out.println("	Username: "+user_pass[0]);
								  s = db.login(user_pass[0], user_pass[1]);
								  response = new ObjectOutputStream(clientSocket.getOutputStream());
								  response.writeObject(s);
								  break;
								  
					case "REGISTER": ObjectInputStream regStu = new ObjectInputStream(clientSocket.getInputStream());
									 s = (Student)regStu.readObject();
									 System.out.println("	Username: "+s.getRno());
									 s = db.register(s);
									 response = new ObjectOutputStream(clientSocket.getOutputStream());
									 response.writeObject(s);
									 break;
									 
					case "UPDATE": ObjectInputStream updStu = new ObjectInputStream(clientSocket.getInputStream());
					 			    s = (Student)updStu.readObject();
					 			    System.out.println("	Username: "+s.getRno());
					 			    s = db.update(s);
					 				response = new ObjectOutputStream(clientSocket.getOutputStream());
					 			    response.writeObject(s);
					 			    break;
					 			    
					case "DELETE": ObjectInputStream delStu = new ObjectInputStream(clientSocket.getInputStream());
					 			   s = (Student)delStu.readObject();
					 			   System.out.println("	Username: "+s.getRno());
					 			   db.delete(s);
					 			   response = new ObjectOutputStream(clientSocket.getOutputStream());
					 			   response.writeObject(s);
					 			   break;
				}
			}catch(Exception e){
				System.out.println("Error In Thread ---> "+e);
			}finally{
				try{
					if(response != null) response.close();
					if(request != null) {
						request.close();
						clientSocket.close();
					}
				}catch(Exception e){
					System.out.println("Finally Run Error ---> "+e);
				}
			}
		}
	}
}
