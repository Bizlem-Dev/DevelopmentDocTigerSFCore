package com.doc.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.doc.util.Utility;
import com.dto.TemplateFileVO;

import gvjava.org.json.JSONException;

public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(FileUploadServlet.class);
	ResourceBundle bundle = ResourceBundle.getBundle("config");
	private static final String FILE_UPLOAD_JSP_PATH = "/WEB-INF/views/FileUpload.jsp";
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		String pageName = Utility.isNull(request.getParameter("pageName")) ? "" : (String)request.getParameter("pageName");
		List<FileItem> multiparts = null;
		Map<String, String> paramsmap = null;
		if(pageName.equals("")){
			try {
				if(isMultipart){
					FileItemFactory factory = new DiskFileItemFactory();
					ServletFileUpload upload = new ServletFileUpload(factory);
					paramsmap = new HashMap<String, String>();
					multiparts = upload.parseRequest(request);

					for (FileItem item : multiparts) {
						if (item.isFormField()) {
							if(item.getFieldName().equals("pageName")){
								pageName = item.getString();
							}
							paramsmap.put(item.getFieldName(), item.getString());
						}
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		if(pageName.equals("TemplateUpload")){

			// process only if its multipart content
			if (isMultipart) {
				// Create a factory for disk-based file items
				//FileItemFactory factory = new DiskFileItemFactory();

				// Create a new file upload handler
				//ServletFileUpload upload = new ServletFileUpload(factory);
				try {
					// Parse the request
					//List<FileItem> multiparts = upload.parseRequest(request);

					String templatename = null;
					String filePath = null;
					String fileExtension = null;
					for (FileItem item : multiparts) {
						if (!item.isFormField()) {
							String filename = new File(item.getName()).getName();
							String[] fileParts = filename.split("\\.");
							templatename = fileParts[0];
							fileExtension = fileParts[1].toLowerCase();
							filePath = bundle.getString("uploaded_templates_path") + File.separator + filename;
							item.write(new File(filePath));
						}
					}

					String outputFormat = (String)paramsmap.get("outputFormats");
					String uploadDocumentLanguageType = (String)paramsmap.get("uploadDocumentLanguageType");

					TemplateFileVO templateVo = new TemplateFileVO();
					templateVo.setTemaplateName(templatename);
					templateVo.setInputFormat(fileExtension);
					templateVo.setOutputFormat(outputFormat);
					templateVo.setTemaplatePath(filePath);
					templateVo.setUploadDocumentLanguageType(uploadDocumentLanguageType);
					Utility.addTemplateToJson(templateVo);

					// File uploaded successfully
					request.setAttribute("message", "Your file has been uploaded!");
					//populate templates dropdown
					List<String> templates = Utility.getAllTemplatesName();

					request.setAttribute("templateList", templates);   
				} 
				catch (Exception e) 
				{
					request.setAttribute("message", "File Upload Failed due to :" + e);
					logger.error("File Upload Failed due to "+e);
				}
			} else 
			{
				request.setAttribute("message", "This function only handles file upload request");
			}
			request.getRequestDispatcher(FILE_UPLOAD_JSP_PATH).forward(request, response);
		}else if(pageName.equals("TemplateDownload")){
			String templateName = request.getParameter("templates");
			TemplateFileVO templateFileVO;
			try {
				templateFileVO = Utility.getDataByTemplateNameFromFile(templateName);

				//file export code
				File file = new File(templateFileVO.getTemaplatePath());
				if(!file.exists()){
					throw new ServletException("File doesn't exists on server.");
				}
				System.out.println("File location on server::"+file.getAbsolutePath());
				ServletContext ctx = getServletContext();
				InputStream fis = new FileInputStream(file);
				String mimeType = ctx.getMimeType(file.getAbsolutePath());
				response.setContentType(mimeType != null? mimeType:"application/octet-stream");
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "attachment; filename=\"" + templateFileVO.getTemaplateName()+"."+templateFileVO.getOutputFormat()+ "\"");

				ServletOutputStream os       = response.getOutputStream();
				byte[] bufferData = new byte[1024];
				int read=0;
				while((read = fis.read(bufferData))!= -1){
					os.write(bufferData, 0, read);
				}
				os.flush();
				os.close();
				fis.close();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("File downloaded at client successfully");
		}else if(pageName.equals("TemplateDelete")){
			String templateName = request.getParameter("templates");
			TemplateFileVO deleatedTemplateFileVO;
			try {
				deleatedTemplateFileVO = Utility.deleteJsonTemplateDataToFile(templateName);

				(new File(deleatedTemplateFileVO.getTemaplatePath())).delete();
				//request.setAttribute("message", "Your file has been deleated!");
				//request.getRequestDispatcher(FILE_UPLOAD_JSP_PATH).forward(request, response);
				response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
				response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
				response.getWriter().write("Your file has been deleated!");       // Write response body.
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			//populate templates dropdown
			List<String> templates;
			try {
				templates = Utility.getAllTemplatesName();
				request.setAttribute("templateList", templates);   
				request.setAttribute("message", "");
				request.getRequestDispatcher(FILE_UPLOAD_JSP_PATH).forward(request, response);
			} catch (JSONException e) {
				request.setAttribute("message", "File Upload Failed due to :" + e);
				logger.error("File Upload Failed due to "+e);
				e.printStackTrace();
			}
		}
	}

	public void doGet(HttpServletRequest request,HttpServletResponse response)  
			throws ServletException,IOException  
	{  

		String pageName = Utility.isNull(request.getParameter("pageName")) ? "" : (String)request.getParameter("pageName");
		if(pageName.equals("TemplateDownload")){
			String templateName = request.getParameter("templates");
			TemplateFileVO templateFileVO;
			try {
				templateFileVO = Utility.getDataByTemplateNameFromFile(templateName);

				//file export code
				File file = new File(templateFileVO.getTemaplatePath());
				if(!file.exists()){
					throw new ServletException("File doesn't exists on server.");
				}
				System.out.println("File location on server::"+file.getAbsolutePath());
				ServletContext ctx = getServletContext();
				InputStream fis = new FileInputStream(file);
				String mimeType = ctx.getMimeType(file.getAbsolutePath());
				response.setContentType(mimeType != null? mimeType:"application/octet-stream");
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "attachment; filename=\"" + templateFileVO.getTemaplateName()+"."+templateFileVO.getInputFormat()+ "\"");

				ServletOutputStream os       = response.getOutputStream();
				byte[] bufferData = new byte[3*1024];
				int read=0;
				while((read = fis.read(bufferData))!= -1){
					os.write(bufferData, 0, read);
				}
				os.flush();
				os.close();
				fis.close();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("File downloaded at client successfully");
		}else{
			//populate templates dropdown
			List<String> templates;
			try {
				templates = Utility.getAllTemplatesName();
				request.setAttribute("templateList", templates);   
				request.setAttribute("message", "");
				request.getRequestDispatcher(FILE_UPLOAD_JSP_PATH).forward(request, response);
			} catch (JSONException e) {
				request.setAttribute("message", "File Upload Failed due to :" + e);
				logger.error("File Upload Failed due to "+e);
				e.printStackTrace();
			}
		}
	}
}