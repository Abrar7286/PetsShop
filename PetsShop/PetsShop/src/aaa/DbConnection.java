package aaa;

import java.sql.*;
public class DbConnection 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://localhost:3306/mydbl";

	 //  Database credentials
	 static final String USER = "root";
	 static final String PASS = "prepaid1414";
	 Connection conn = null;
	 Statement stmt = null;
	 public DbConnection()
	{
		 try{
		    //STEP 2: Register JDBC driver
		    Class.forName("com.mysql.jdbc.Driver");

		    //STEP 3: Open a connection
		    System.out.println("Connecting to a selected database...");
		    conn = DriverManager.getConnection(DB_URL, USER, PASS);
		    System.out.println("Connected database successfully...");
		    }
		 catch(SQLException se){
			    //Handle errors for JDBC
			    se.printStackTrace();
			 }catch(Exception e){
			    //Handle errors for Class.forName
			    e.printStackTrace();
			 }
			 System.out.println("Goodbye!");
	}
	 
	 
	 
	 ///////////////////////// Login verify ///////////////////////////////
	 
	 
	public boolean verify(String Username, String PW ) 
	{ boolean status=false;  
	try
	{
		
	
		stmt = conn.createStatement();
	    String sql = "SELECT * FROM form where Username = '"+ Username +"' AND Password='"+PW +"' " ;
	    ResultSet rs = stmt.executeQuery(sql);
	    status=rs.next();  

	}
	catch(SQLException se){
	    //Handle errors for JDBC
	    se.printStackTrace();}
		    //finally block used to close resources
		return status;
	}
	
	
	 
		public String getUserType(String Username) 
		{   
		try
		{
			
		
			stmt = conn.createStatement();
		    String sql = "SELECT UserType FROM form where Username = '"+ Username +"'" ;
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    if(rs.next()){
		    	
		    	return rs.getString("UserType");
		    }
		    

		}
		catch(SQLException se){
		    //Handle errors for JDBC
		    se.printStackTrace();}
			    //finally block used to close resources
		
			return "";
		}
		
		
		
		
	///////////////////////////////////// function for add user data in db //////////////////////////////////////
	
	public void myData(String Name, String Address,String ZipCode , String Country,String Gender, String Phone,String Email,String Username,String Password )
	 {
	 try{
			  stmt = conn.createStatement();
			  String sql = "insert into form values ('" + Name + "','" +Address + "','" + ZipCode + "','" + Country + "','"
				       +Gender+ "','" +Phone+ "','" +Email+ "','" +Password+ "','" +Username+ "','member')";
  stmt.executeUpdate(sql);
			  System.out.println("Data is Inserted in db.....");
			 }catch(SQLException se){
			    //Handle errors for JDBC
			    se.printStackTrace();
			 }catch(Exception e){
			    //Handle errors for Class.forName
			    e.printStackTrace();
			 }finally{
			
			    try{
			       if(stmt!=null)
			          conn.close();
			    }catch(SQLException se){
			    }// do nothing
			    try{
			       if(conn!=null)
			          conn.close();
			    }catch(SQLException se){
			       se.printStackTrace();
			    }//end finally try
			 }//end try
			 System.out.println("Goodbye!");
	 }
	//function for saving product in db
	public void AddProduct( String Name,String Price,String Discription,String Quantity,String Image)
	{try{
		  stmt = conn.createStatement();
		  System.out.println("abc.......");
		  System.out.println(Name);
		  System.out.println(Price);
		  String sql = "insert into Products(Name, Price, Description, Quantity, Image) values ('" + Name + "','" + Price + "','" + Discription + "','" +Quantity + "','" +Image+ "')"; 
		  stmt.executeUpdate(sql);
		  System.out.println("product is saved in Database.....");
		 }catch(SQLException se){
		    //Handle errors for JDBC
		    se.printStackTrace();
		 }catch(Exception e){
		    //Handle errors for Class.forName
		    e.printStackTrace();
		 }finally{
		    //finally block used to close resources
		    try{
		       if(stmt!=null)
		          conn.close();
		    }catch(SQLException se){
		    }// do nothing
		    try{
		       if(conn!=null)
		          conn.close();
		    }catch(SQLException se){
		       se.printStackTrace();
		    }//end finally try
		 }//end try
		 System.out.println("Goodbye!");
	
	}
	public void close(){
		   try{
		       if(stmt!=null)
		          conn.close();
		    }catch(SQLException se){
		    }// do nothing
		   
	
		// TODO Auto-generated method stub
		
	}
	
}