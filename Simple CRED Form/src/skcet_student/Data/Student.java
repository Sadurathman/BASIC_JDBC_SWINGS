package skcet_student.Data;

import java.io.Serializable;


public class Student implements Serializable {
	
	private static final long serialVersionUID = 22L;
	
	private String rno;
	private String name;
	private String password;
	private String mobile;
	private boolean hosteller;
	private String dept;
	
	public String getRno(){
		return rno;
	}
	
	public void setRno(String rno){
		this.rno = rno;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean getCity() {
        return hosteller;
    }

    public void setCity(boolean hosteller) {
        this.hosteller = hosteller;
    }
    
    public String getDept(){
    	return dept;
    }
    
    public void setDept(String dept){
    	this.dept = dept;
    }
    
    public String getPassword(){
    	return password;
    }
    
    public void setPassword(String password){
    	this.password = password;
    }
    
    @Override
    public String toString(){
    	return (name+" from "+dept+" department.");
    }
}
