package com;
import java.sql.*;
public class Inquiry
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/power", "root", "kaumadi");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }


//view inquiry data section

public String readInquiry()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for reading.";
 }

                 // Prepare the html table to be displayed

         output = "<table border='1'><tr><th>Ref no</th><th>User name</th>" + "<th>Email</th>" + "<th>Phone no</th>" + "<th>Description</th>" +"<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from inquiry";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
 while (rs.next())
 {
         String id = Integer.toString(rs.getInt("id"));
		 String refno = rs.getString("refno");
		 String uname = rs.getString("uname");
		 String email = rs.getString("email");
		 String pnum = rs.getString("pnum");
		 String des = rs.getString("des");
 // Add into the html table


                output += "<tr><td><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='" + id+ "'>" + refno + "</td>";

                 //output += "<td>" + refno + "</td>";
		 output += "<td>" + uname + "</td>";
		 output += "<td>" + email + "</td>";
		 output += "<td>" + pnum + "</td>";
		 output += "<td>" + des + "</td>";

 // buttons
output += "<td><input name='btnUpdate'type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-id='"+ id + "'>" + "</td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the inquiry.";
 System.err.println(e.getMessage());
 }
 return output;
 }


//insert function

public String insertInquiry(String refno, String uname,String email, String pnum, String des)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for inserting.";
 }
 // create a prepared statement

         String query = " insert into inquiry(`id`,`refno`,`uname`,`email`,`pnum`,`des`)"+ " values (?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
		 
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, refno);
		 preparedStmt.setString(3, uname);
		 preparedStmt.setString(4,email);
		 preparedStmt.setString(5, pnum);
		 preparedStmt.setString(6, des);
		 
		 // execute the statement
		 
 preparedStmt.execute();
 con.close();


 String newInquiry = readInquiry();
 output = "{\"status\":\"success\", \"data\": \"" +newInquiry + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while inserting the inquiry.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }


//update

		public String updateInquiry(String id, String refno, String uname, String email, String pnum, String des)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for updating.";
 }
 // create a prepared statement

		String query = "UPDATE inquiry SET refno=?,uname=?,email=?,pnum=?,des=?WHERE id=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		
		// binding values
		
		preparedStmt.setString(1, refno);
		preparedStmt.setString(2, uname);
		preparedStmt.setString(3, email);
		preparedStmt.setString(4, pnum);
		preparedStmt.setString(5, des);
		preparedStmt.setInt(6, Integer.parseInt(id));
 
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newInquiry = readInquiry();
 output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while updating the inquiry.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }

//delete

public String deleteInquiry(String id)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for deleting.";
 }
 // create a prepared statement
		
		String query = "delete from inquiry where id=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(id));
 // execute the statement
 preparedStmt.execute();
 con.close();
  String newInquiry = readInquiry();
 output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while deleting the inquiry.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
}