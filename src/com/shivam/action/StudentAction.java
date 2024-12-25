package com.shivam.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.shivam.beans.Student;

public class StudentAction {
	Connection con =null;
	Statement st =null;
	ResultSet rs = null;
	String status ="";
	Student std;
  public StudentAction() {
	  try {
		Class.forName("oracle.jdbc.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Shivam");
		st= con.createStatement();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
  }
  public String add(String sid,String sname,String saddr) {
	  
	  try {
		  Student std = Search(sid);
		  if (std==null){
			  int rowCount = st.executeUpdate("insert into student values ('"+sid+"','"+sname+"','"+saddr+"')");
			  if(rowCount==1) {
				  status ="success";
			  }else {
				  status="failure";
			  }
		  }else {
			  status="existed" ;
		  }
	} catch (Exception e) {
		// TODO: handle exception
		status ="failure";
		e.printStackTrace();
	}
	  return status;
  }
  public Student Search(String sid) {
	  try {
		rs=st.executeQuery("select * from student where SID = '"+sid+"'");
		boolean b =rs.next();
		if(b == true) {
			std = new Student();
			std.setSid(rs.getString("SID"));
			std.setSname(rs.getString("SNAME"));
			std.setSaddr(rs.getString("SADDR"));
		}else {
			std =null;
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	  return std;
  }
  public String update(String sid, String sname,String saddr) {
	  try {
		  int rowCount = st.executeUpdate("update student set SNAME ='"+sname+"',SADDR ='"+saddr+"' where sid='"+sid+"'");
		  if(rowCount == 1) {
			  status="success";
		  }else {
			  status ="failure";
		  }
		
	} catch (Exception e) {
		// TODO: handle exception
		status ="failure";
		e.printStackTrace();
	}
	  return status;
  }
  public String delete(String sid) {
	  try {
		  Student std = Search(sid);
		  if(std==null) {
			  status ="notExisted";
		  }else {
			  int rowCount = st.executeUpdate("delete student where SID='"+sid+"'");
			  if(rowCount==1) {
				  status="success";
			  }else {
				  status="failure";
			  }
		  }
		
	} catch (Exception e) {
		status ="failure";
	    e.printStackTrace();
	}
	  return status;
  }
  
  
}
