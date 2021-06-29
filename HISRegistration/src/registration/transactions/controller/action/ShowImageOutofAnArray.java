package registration.transactions.controller.action;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowImageOutofAnArray extends HttpServlet{	

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
			byte[] imageArray =(byte[])request.getSession().getAttribute("Upload_File_WithServlet");
			if(imageArray!=null){
			response.flushBuffer();
			response.setContentType("image/jpg");
			OutputStream os = response.getOutputStream();
			os.flush();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.flush();
			bos.write(imageArray, 0, imageArray.length);
			bos.close();
			}
		/*	byte[] bytes=HelperMethods.getByteArrayOfImage("C:"+"\\"+"cdac1.jpg");
			response.flushBuffer();
			response.setContentType("image/jpg");
			OutputStream os = response.getOutputStream();
			os.flush();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.flush();
			bos.write(bytes, 0, bytes.length);
			bos.close();
		*/}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
	 	
		byte[] imageArray =(byte[])request.getSession().getAttribute("Upload_File_WithServlet");
		response.setContentType("image/jpg");
		OutputStream os = response.getOutputStream();
		os.flush();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		bos.write(imageArray, 0, imageArray.length);
		bos.close();
	}
}
