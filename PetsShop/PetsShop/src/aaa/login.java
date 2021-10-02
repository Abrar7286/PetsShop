package aaa;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Name =request.getParameter("name");
		String Username =request.getParameter("username");
		String  Address =request.getParameter("address");
		String Zip =request.getParameter("zip");
		String country =request.getParameter("country");
		String gender=request.getParameter("gender");
		String  phone =request.getParameter("phone");
		String  Email =request.getParameter("email");		
		String Password =request.getParameter("passid");
		
		
		DbConnection mydb = new DbConnection();
		mydb.myData(Name,Address,Zip,country,gender,phone,Email,Username,Password);
		response.sendRedirect("html/login.jsp");

		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("5555555555555555555555555555555555");// TODO Auto-generated method stub
	}

}
