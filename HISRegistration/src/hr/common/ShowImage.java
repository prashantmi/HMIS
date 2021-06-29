/* Module Name: Global
*  Name of Process: Show Image 
*  Name of Developer: Sh. Ashwini Mishra
*  Name of TL: Sh. Ashwini Mishra 
*  Date of Creation: 07-09-2014
*/

package hr.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
 
public class ShowImage extends ActionSupport {
	
	
		public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
			 	System.out.println("inside ShowImageOutofAnArray");						
			try
			{
			 	String imageName=request.getParameter("IMAGE_NAME");
			 	FileInputStream fis=new FileInputStream(imageName);
			 	//System.out.println("imageName .....ashwini    "+imageName);
			 	byte[] bytes=new byte[fis.available()];
			 	fis.read(bytes);
			 	response.setContentType("image/jpg");
				OutputStream os = response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(os);
				bos.write(bytes);
				bos.flush();
				bos.close();
			}
			catch(FileNotFoundException e)
			{
				System.out.println("FileNotFoundException  " + e);
			}
			catch(IOException e)
			{
				System.out.println("IOException  " + e);
			}		
			catch(Exception e)
			{
				System.out.println("Exception  " + e);
			}
			
			}
		
	}
