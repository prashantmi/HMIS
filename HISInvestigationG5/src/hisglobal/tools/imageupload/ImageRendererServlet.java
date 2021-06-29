package hisglobal.tools.imageupload;

import hisglobal.vo.ImageUtilityVO;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class for Servlet: ImageRendererServlet
 *
 */
 public class ImageRendererServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ImageRendererServlet() {
		super();
	}   	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
	 	System.out.println("inside ShowImageOutofAnArray:GET");						
		
		java.util.List<ImageUtilityVO> imageArrayList = (java.util.List<ImageUtilityVO>)request.getSession().getAttribute((String)request.getParameter("sessionListName")); 

		System.out.println("File Name+++++++++++"+imageArrayList.size());
		
		String position = (String)request.getParameter("value");
			if(imageArrayList !=null)
			{
					if(imageArrayList.get(Integer.parseInt(position)).getImageByteArray()!=null)
					{
					byte[] imageArray=(byte[])imageArrayList.get(Integer.parseInt(position)).getImageByteArray();
					if(imageArray!=null){
					response.flushBuffer();
					//System.out.println("Buffer size"+response.getBufferSize());
					response.setContentType("image/jpg");
					OutputStream os = response.getOutputStream();
					os.flush();
					BufferedOutputStream bos = new BufferedOutputStream(os);
					bos.flush();
					bos.write(imageArray, 0, imageArray.length);
					bos.close();
					}
			}
		}
	
	}
public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
 	System.out.println("inside ShowImageOutofAnArray:POST");						
 	
 	java.util.List<ImageUtilityVO> imageArrayList = (java.util.List<ImageUtilityVO>)request.getSession().getAttribute((String)request.getParameter("sessionListName")); 

	System.out.println("File Name+++++++++++"+imageArrayList.size());
	String position = (String)request.getParameter("value");
	if(imageArrayList !=null)
	{
		byte[] imageArray=(byte[])imageArrayList.get(Integer.parseInt(position)).getImageByteArray();
	if(imageArray!=null){
	response.flushBuffer();
	//System.out.println("Buffer size"+response.getBufferSize());
	response.setContentType("image/jpg");
	OutputStream os = response.getOutputStream();
	os.flush();
	BufferedOutputStream bos = new BufferedOutputStream(os);
	bos.flush();
	bos.write(imageArray, 0, imageArray.length);
	bos.close();
	}
	}
}	  	    
}
 /*public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
	 	System.out.println("inside ShowImageOutofAnArray:GET");						
		List<byte[]> imageArrayList =(List<byte[]>)request.getSession().getAttribute("IMAGELIST");
		System.out.println("File Name+++++++++++"+imageArrayList.size());
		int position = Integer.parseInt((String)request.getParameter("value"));
		if(imageArrayList !=null)
		{
		byte[] imageArray=imageArrayList.get(position-1);
		if(imageArray!=null){
		response.flushBuffer();
		//System.out.println("Buffer size"+response.getBufferSize());
		response.setContentType("image/jpg");
		OutputStream os = response.getOutputStream();
		os.flush();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		bos.flush();
		bos.write(imageArray, 0, imageArray.length);
		bos.close();
		}
		}
	
	}
public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
 	System.out.println("inside ShowImageOutofAnArray:POST");						
 	
 	List<byte[]> imageArrayList =(List<byte[]>)request.getSession().getAttribute("IMAGELIST");
	System.out.println("File Name+++++++++++"+imageArrayList.size());
	int position = Integer.parseInt((String)request.getParameter("value"));
	if(imageArrayList !=null)
	{
	byte[] imageArray=imageArrayList.get(position);
	if(imageArray!=null){
	response.flushBuffer();
	//System.out.println("Buffer size"+response.getBufferSize());
	response.setContentType("image/jpg");
	OutputStream os = response.getOutputStream();
	os.flush();
	BufferedOutputStream bos = new BufferedOutputStream(os);
	bos.flush();
	bos.write(imageArray, 0, imageArray.length);
	bos.close();
	}
	}
}*/