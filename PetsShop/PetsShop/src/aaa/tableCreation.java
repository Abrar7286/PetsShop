package aaa;
	//STEP 1. Import required packages
	import java.sql.*;

	public class tableCreation {
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/mydbl";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "prepaid1414";
	   
	   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      //STEP 4: Execute a query
	      System.out.println("Creating table in given database...");
	      stmt = conn.createStatement();
	      
	    
	      String sql = "insert into form"+" ( Name,Address, ZipCode,Country,Gender,Phone,Email,Password ) " +
	      		"values"+"('', '', '','')";
	
	    
	      /* SELECT QUERY
	      System.out.println("Creating statement...");
	stmt = conn.createStatement();

	String sql = "SELECT id, first, last, age FROM Registration";
	ResultSet rs = stmt.executeQuery(sql);
	//STEP 5: Extract data from result set
	while(rs.next()){
	   //Retrieve by column name
	   int id  = rs.getInt("id");
	   int age = rs.getInt("age");
	   String first = rs.getString("first");
	   String last = rs.getString("last");

	   //Display values
	   System.out.print("ID: " + id);
	   System.out.print(", Age: " + age);
	   System.out.print(", First: " + first);
	   System.out.println(", Last: " + last);
	}
	rs.close();

	      String sql = "UPDATE Registration " +
	              "SET age = 30 WHERE id in ( 1) "+
	    		  "SET First= 'anila' WHERE First in 'ab'";


	 // Now you can extract all the records
	 // to see the updated records
	 sql = "SELECT id, first, last, age FROM Registration";
	 ResultSet rs = stmt.executeQuery(sql);

	 while(rs.next()){
	    //Retrieve by column name
	    int id  = rs.getInt("id");
	    int age = rs.getInt("age");
	    String first = rs.getString("first");
	    String last = rs.getString("last");

	    //Display values
	    System.out.print("ID: " + id);
	    System.out.print(", Age: " + age);
	    System.out.print(", First: " + first);
	    System.out.println(", Last: " + last);
	 }
	 rs.close(); */
	          stmt.executeUpdate(sql);
	      System.out.println("Created table in given database...");
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
	      }
	   }
	   System.out.println("have a nice day !");
	}
	}
	          
