package com.shivam.jdbc.crud;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.shivam.action.StudentAction;
import com.shivam.beans.Student;

public class Test {

	public static void main(String[] args)throws Exception {
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 StudentAction studentaction= new StudentAction();
	 
	 while(true) {
		 System.out.println("1. Add Student");
		 System.out.println("2. Search Student");
		 System.out.println("3. Update Student");
		 System.out.println("4. Delete Student");
		 System.out.println("5. EXIT");
		 System.out.println("Enter Your Option : ");
		 int option = Integer.parseInt(br.readLine());
		 String sid ="",sname="",saddr="";
		 switch(option) {
		 case 1:
			 System.out.println("Enter Student Id :");
			 sid = br.readLine();
			 System.out.println("Enter Student Name :");
			 sname = br.readLine();
			 System.out.println("Enter Student Address :");
			 saddr=br.readLine();
			 String status = studentaction.add(sid,sname,saddr);
			 if(status.equals("success")) {
				 System.out.println("Student successfully Added");
			 } if(status.equals("existed")) {
				 System.out.println("Student already existed");
			 } if(status.equals("failure")){
				 System.out.println("Some error occure");
			 }
			 System.out.println();
			 break;
		 case 2:
			 System.out.println("Student id :");
			 sid = br.readLine();
			 Student std = studentaction.Search(sid);
			 if(std == null) {
				 System.out.println("User is not existed");
			 }else {
				 System.out.println("**********Student Details**********");
				 System.out.println("Student id      "+std.getSid());
				 System.out.println("Student Name    "+std.getSname());
				 System.out.println("Student Address "+std.getSaddr());
				 System.out.println("***********************************");
			 }
			 break;
		 case 3:
			 System.out.println("Enter Student id : ");
			 sid = br.readLine();
			 std = studentaction.Search(sid);
			 if(sid==null) {
				 System.out.println("No student existed with this id "+sid);
			 }else {
				 System.out.println("********Student old Details**********");
				 System.out.println("Student id      : "+std.getSid());
				 System.out.println("Student name    : "+std.getSname());
				 System.out.println("Student Address : "+std.getSaddr());
				 System.out.println("*************************************");
				 System.out.println("Enter new Name    :");
				 String New_sname = br.readLine();
				 System.out.println("Enter New Address :");
				 String New_saddr= br.readLine();
				 if(New_sname== null ||New_sname.equals("")) {
					 New_sname=std.getSname();
				 }
				 if(New_saddr==null || New_saddr.equals("")) {
					 New_saddr=std.getSaddr();
				 }
				 status = studentaction.update(sid, New_sname, New_saddr);
				 if(status.equals("success")) {
					 System.out.println("Data Updated successfully");
				 }else {
					 System.out.println("Something error");
				 }
			 }
			 break;
		 case 4:
			 System.out.println("Enter the student id :");
			 sid =br.readLine();
			 status = studentaction.delete(sid);
			 if(status.equals("success")) {
				 System.out.println("Student deleted successfully");
			 }if(status.equals("notExisted")) {
				 System.out.println("Student are not Existed");
			 }if(status.equals("failure")) {
				 System.out.println("Failure in delete operation");
			 }
			 break;
		 case 5:
			 System.out.println("Thank you for using std app");
			System.exit(0);
			 break;
	     default:
	    	 break;
	    	 
		 }
	 }
	}
}
