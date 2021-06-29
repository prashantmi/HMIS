package opd.transaction.controller.action;

import hisglobal.utility.HelperMethods;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import opd.OpdConfig;


 public class ShowFileFromDir extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ShowFileFromDir() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dirPath=request.getParameter(OpdConfig.DOCUMENT_STORAGE_PATH);
		//String dirPath="c:/PatientImages";
		String fileName=request.getParameter(OpdConfig.FILE_NAME);
		String isExtension=request.getParameter(OpdConfig.IS_EXTENSION);
		String contentTypeStr="";
		String fileExt="";
		String extension=request.getParameter(OpdConfig.EXTENSION_NAME);
		//String fileNameWithPath=dirPath+"\\"+fileName;
		if(isExtension.equals(OpdConfig.IS_EXTENSION_FALSE)){
			fileExt=fileName.substring(fileName.indexOf(".")+1);
			fileExt=fileExt.trim();
		}
		if(isExtension.equals(OpdConfig.IS_EXTENSION_TRUE)){
			fileExt=extension;
			fileExt=fileExt.trim();
		}
		
		
		if(fileExt.equalsIgnoreCase("jpg") || fileExt.equalsIgnoreCase("jpeg") || fileExt.equalsIgnoreCase("gif"))
		{
			contentTypeStr="image/";
			
		}
		
		if(fileExt.equalsIgnoreCase("pdf"))
		{
			contentTypeStr="application/";
		}
		
		if(fileExt.equalsIgnoreCase("htm") || fileExt.equalsIgnoreCase("html"))
		{
			contentTypeStr="text/";
		}
		contentTypeStr=contentTypeStr+fileExt;
		OutputStream os =response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		
		try {			
			byte[] bytes=HelperMethods.getByteArrayOfImage(dirPath+"\\"+fileName);
			response.setContentType(contentTypeStr);
		//	response.setContentType("text/html");
				if(bytes!=null)
				{
						response.setHeader("Pragma","no-cache");		 		
						bos.write(bytes, 0, bytes.length);
						
				}
				else{
					response.setContentType("text/html");
					//PrintWriter out=response.getWriter();
					//out.println("<b>File Not Found</b>");
					String msg="<b>File Not Found</b>";
					bos.write(msg.getBytes());
				}
		
			}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally{
			response.getOutputStream().flush();
			bos.close();			
		}
		
		System.out.println("");
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}   	  	    
}