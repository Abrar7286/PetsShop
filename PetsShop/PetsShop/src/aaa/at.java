package aaa;

import java.io.*;
import java.util.*;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

/**
 * Servlet implementation class UploadServlet
 */
public class at extends HttpServlet {
	
	private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 4092 * 4092;
	   private int maxMemSize = 600 * 1024;
	   private File file ;
	   
	   public void init( ){
		      // Get the file location where it would be stored.
		      filePath = "D:\\Notepad++\\webApp\\finalProj\\WebContent\\images"; 
		   }
	   
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public at() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

    	throw new ServletException("GET method used with " + getClass( ).getName( )+": POST method required.");
    	

}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	String name,qua,dis,price;
	public void doPost(HttpServletRequest request, 
            HttpServletResponse response)
           throws ServletException, java.io.IOException {
		name = request.getParameter("name");

    	price = request.getParameter("price");
    	qua = request.getParameter("quantity");
    	dis = request.getParameter("description");    	
    	DbConnection con = new DbConnection();
   // Check that we have a file upload request
   isMultipart = ServletFileUpload.isMultipartContent(request);
   //response.setContentType("text/html");
   java.io.PrintWriter out = response.getWriter( );
   if( isMultipart ){
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet upload</title>");  
      out.println("</head>");
      out.println("<body>");
      out.println("<p>No file uploaded</p>"); 
      out.println("</body>");
      out.println("</html>");
      return;
   }
   DiskFileItemFactory factory = new DiskFileItemFactory();
   // maximum size that will be stored in memory
   factory.setSizeThreshold(maxMemSize);
   // Location to save data that is larger than maxMemSize.
   factory.setRepository(new File("\\temp"));

   // Create a new file upload handler
   ServletFileUpload upload = new ServletFileUpload(factory);
   // maximum file size to be uploaded.
   //upload.setSizeMax( maxFileSize );

   try{ 
   // Parse the request to get file items.
   List fileItems = upload.parseRequest(request);
	
   // Process the uploaded file items
   Iterator i = fileItems.iterator();

   out.println("<html>");
   out.println("<head>");
   out.println("<title>Servlet upload</title>");  
   out.println("</head>");
   out.println("<body>");
   while ( i.hasNext () ) 
   {
      FileItem fi = (FileItem)i.next();
      if(fi.isFormField()){
    	  //input text/radio/checkbox field
    	  String fieldName = fi.getFieldName();
          String fieldValue = fi.getString();
      	System.out.println(fieldName);
    	System.out.println(fieldValue);
          if(fieldName.equalsIgnoreCase("name"))
          {
        	  name = fieldValue;
          }
          else if (fieldName.equalsIgnoreCase("price"))
          {
        	 price = fieldValue;
          }
          else if (fieldName.equalsIgnoreCase("description"))
          {
        	 dis = fieldValue;
          }
          else if (fieldName.equalsIgnoreCase("quantity"))
          {
        	 qua = fieldValue;
          }
      }
      if ( !fi.isFormField () )	
      {
         // Get the uploaded file parameters
         String fieldName = fi.getFieldName();
         String fileName = fi.getName();
         String contentType = fi.getContentType();
         boolean isInMemory = fi.isInMemory();
         long sizeInBytes = fi.getSize();
         // Write the file
         if( fileName.lastIndexOf("\\") >= 0 ){
            file = new File( filePath + 
            fileName.substring( fileName.lastIndexOf("\\"))) ;
         }else{
            file = new File( filePath + 
            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
         }
         fi.write( file ) ;
         out.println("Uploaded Filename: " + fileName + "<br>");
      }
   }
   out.println("</body>");
   out.println("</html>");
   // String path = "D:\\Notepad++\\webApp\\finalProj\\WebContent\\images\\"+file.getName();
   //String path = "D:\\\\Notepad++\\\\webApp\\\\finalProj\\\\WebContent\\\\images\\\\"+file.getName();
   String path = "D:\\\\\\Notepad++\\\\\\webApp\\\\\\finalProj\\\\\\WebContent\\\\\\images\\\\\\"+file.getName();
 
   
   con.AddProduct(name, price, dis, qua,path);

   }
   catch(Exception ex) {
    System.out.println(ex);
}
}
}
