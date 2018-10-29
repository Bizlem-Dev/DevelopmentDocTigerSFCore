package com.doc.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class pdffromurl
 */
public class pdffromurl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pdffromurl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = null;
		
		    //String pdfurl= "https://sf.deeprootsurface.com/plans/OS222208A.pdf";
			//String destinationFile = "/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/images/qrcode/OS222208A.pdf";

			
			String pdfurl= request.getParameter("pdfurl");
			String destinationFile= request.getParameter("destinationFile");

		
			
			//out.println("pdfurl  "+pdfurl);
			//out.println("destinationFile  "+destinationFile);
			InputStream is = null;
			OutputStream os = null;
			URL url = null;
			HttpURLConnection conn  = null;
			try {
				out = response.getWriter();
			 url = new URL(pdfurl);
			  conn  = (HttpURLConnection) url.openConnection();
			//out.println("1");
			 conn.connect();
			 if(conn.getResponseCode() == 200) {
			is = conn.getInputStream();
			//out.println("2");
			//out.println("3");
			os = new FileOutputStream(destinationFile);
			//out.println("4");

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			//out.println();


			
			out.print("success");
			}else {
				out.print("false");	
			}
			}catch(Exception e) {
				e.printStackTrace();
				out.println("error "+e.getMessage());
				 
			}finally {
				os.close();
				is.close();
			
			}
			
		




		
		
		
		
	
	}

	

}
