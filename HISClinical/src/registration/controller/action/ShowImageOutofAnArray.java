package registration.controller.action;


import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import registration.RegistrationConfig;

public class ShowImageOutofAnArray extends HttpServlet{
	
	

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
			List imageByteArrayList=null;
			byte[] imageArray=null; 
			
			String index="";
			System.out.println("inside ShowImageOutofAnArray");				
			if(request.getParameter(Config.IMAGE_BYTE_ARRAY_KEY)==null){
				if( (WebUTIL.getSession(request).getAttribute(Config.IMAGE_BYTE_ARRAY)!=null) && (request.getParameter(Config.REQ_PARAMETER_IMAGE_INDEX)!=null))
				{
					index=request.getParameter(Config.REQ_PARAMETER_IMAGE_INDEX);
					imageByteArrayList=(List)WebUTIL.getSession(request).getAttribute(Config.IMAGE_BYTE_ARRAY);
					imageArray=(byte[])imageByteArrayList.get(Integer.parseInt(index));
				}
				else
				{
					imageArray=(byte[])WebUTIL.getSession(request).getAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY);
				}
			}
			else{
				if( request.getParameter(Config.REQ_PARAMETER_IMAGE_INDEX)!=null)
				{
					index=request.getParameter(Config.REQ_PARAMETER_IMAGE_INDEX);
					imageByteArrayList=(List)WebUTIL.getSession(request).getAttribute(request.getParameter(Config.IMAGE_BYTE_ARRAY_KEY));
					imageArray=(byte[])imageByteArrayList.get(Integer.parseInt(index));
				}
			}
			
			
			response.setContentType("image/jpg");
			OutputStream os = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			if(imageArray!=null)
				bos.write(imageArray, 0, imageArray.length);
			bos.close();
	
	}
	
}
