package registration.reports.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.classic.Session;

import registration.RegistrationConfig;


 public class GenerateGraphACTION extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   
	public GenerateGraphACTION() {
		super();
	}   	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		byte[] imageArray =(byte[])WebUTIL.getSession(request).getAttribute(Config.UPLOAD_CHART_IMAGE);
		//byte[] imageArrayrequest.getAttribute(RegistrationConfig.UPLOAD_CHART_IMAGE);
		response.setContentType("image/png");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Cache-Control","must-revalidate");
		response.setIntHeader("Refresh", 5);
		OutputStream os = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		if(imageArray!=null)
			bos.write(imageArray, 0, imageArray.length);
		bos.close();
		
		
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}   	  	    
}