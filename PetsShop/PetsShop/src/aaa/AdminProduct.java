package aaa;
import java.util.*;
import java.io.*;

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
 * Servlet implementation class AdminProduct
 */
public class AdminProduct extends HttpServlet {
	private boolean isMultipart;
	   private String filePath = System.getProperty("user.dir");
	   private int maxFileSize = 50 * 1024;
	   private int maxMemSize = 4 * 1024;
	   private File file ;

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String  ProductName =request.getParameter("name");
		String  ProductPrice =request.getParameter("price");
		String  ProductDiscrition =request.getParameter("description");
		String  ProductQuantity =request.getParameter("quantity");
		//String  ProductImage =request.getParameter("image");
		DbConnection mydb = new DbConnection();
		String  ProductImage ="a.jpg";
		
		
		
       System.out.println("Uploaded FilePath: " + filePath + "<br>");

        
		  // Check that we have a file upload request
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	    //  response.setContentType("text/html");
	      java.io.PrintWriter out = response.getWriter( );
	     
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File(this.filePath + "\\tmp\\" ));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );

	      try{ 
	      // Parse the request to get file items.
	      List fileItems = upload.parseRequest(request);
		
	      // Process the uploaded file items
	      Iterator i = fileItems.iterator();

	      while ( i.hasNext () ) 
	      {
	         FileItem fi = (FileItem)i.next();
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
	            
	            ProductImage=fileName;
	            System.out.println("Uploaded Filename: " + fileName + "<br>");
	         }else {
	        	 
		            System.out.println(" field : " + fi.getFieldName() + "<br>");

		            
	        	 if( fi.getFieldName() == "name" ){
	        		 
	        		 ProductName = fi.getFieldName();
	        		 
	        	 }else if( fi.getFieldName() == "price" ){
	        		 
	        		 ProductPrice = fi.getFieldName();

	        	 }else if( fi.getFieldName() == "quantity" ){
	        		 
	        		 ProductQuantity = fi.getFieldName();

	        	 }else if( fi.getFieldName() == "description" ){
	        		 
	        		 ProductDiscrition = fi.getFieldName();

	        		 
	        	 }
	        	 
	         }
	      }
	      
	  	System.out.println(ProductQuantity);
		
	  	
	  	
	   }catch(Exception ex) {
	       System.out.println(ex);
	   }
		mydb.AddProduct( ProductName,ProductPrice,ProductDiscrition,ProductQuantity, ProductImage);
	}

}
