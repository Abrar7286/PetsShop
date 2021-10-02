package aaa;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public LogginServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("UN");
		String password= request.getParameter("PW");
		System.out.println("in login function");

		PrintWriter pw = response.getWriter();
		DbConnection mydb = new DbConnection();
		
		if (mydb.verify(userName, password))
			{
			
			
			System.out.println("login successfully");
				
			    HttpSession session = request.getSession(true);	    
		          session.setAttribute("username",userName); 
		          
		          
				Cookie abc = new Cookie("uname", userName);
				response.addCookie(abc);
				

				String userType = mydb.getUserType(userName);
				
					System.out.println(" Type " + userType);
					
					if( userType.equals("admin") ){
						
						System.out.println(userType);

						response.sendRedirect("html/manage_product.jsp");

						
					}else {
						response.sendRedirect("html/Home.jsp");

						
					}
					
			
					
				}
		else 
		{
			response.sendRedirect("html/login.jsp");

			System.out.println("login unsuccessfully");
		}
		
	
}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
