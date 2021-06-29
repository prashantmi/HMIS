package hisglobal.tools.imageupload;

import hisglobal.tools.imageupload.*;
import hisglobal.tools.imageUtility.action.ImageServletACTION;
import hisglobal.tools.imageUtility.util.CommonServletUTIL;
import hisglobal.tools.imageUtility.util.ImageServletUTIL;
import hisglobal.tools.imageUtility.util.VideoServletUTIL;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.List;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

public class File_Upload_Action extends DispatchAction{

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("INSIDE UNSPECIFIED");
		return this.UPLOAD(mapping,form,request,response);		
	}	
    public ActionForward UPLOAD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
	    System.out.println("inside new of upload File_Upload_Action");
//	    Status objStatus=new Status();
	//    objStatus.add(Status.NEW);
		//WebUTIL.setStatus(request,objStatus);
	    FileUploadFB fb = (FileUploadFB) form;
	    System.out.println("ScreenType-------->"+request.getParameter("ScreenType"));
	    System.out.println("primaryKey-------->"+request.getParameter("primaryKey"));
	    
	  /*  fb.setScreenType(request.getParameter("ScreenType"));
	    fb.setPrimaryKey(request.getParameter("primaryKey"));*/
	    
	    request.setAttribute("Status","new");
	 	return mapping.findForward("UPLOAD");
	}
    public ActionForward UPLOADVIDEO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
	    System.out.println("inside new of upload File_Upload_Action");
//	    Status objStatus=new Status();
	//    objStatus.add(Status.NEW);
		//WebUTIL.setStatus(request,objStatus);
	    request.setAttribute("Status","new");
	 	return mapping.findForward("UPLOADVIDEO");
	}
    
    
    
    /**
     * This method eaxtract byte array out of uploaded file and keeps
     * that bytearray in session
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	/*public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		FileUploadFB fb = (FileUploadFB) form;		
		FormFile myfile=fb.getUploadedFileName();
		String fileName=myfile.getFileName();
		
		String ftpfileLoc="Image";
		System.out.println("file name SAVE"+fileName);
		
		byte[] fileDataAsByte=null;
		try {
			 fileDataAsByte =myfile.getFileData();
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
		
		
		ImageServletUTIL imgutil=new ImageServletUTIL();
		try
		{
			imgutil.SaveImage(request,fileName,"IMAGE",fileDataAsByte);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		fb.setHmode("CLOSE");
		
		
		
		System.out.println("file name SAVE"+fileName);
		
		byte[] fileDataAsByte=null;
		try {
			 fileDataAsByte =myfile.getFileData();
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
		HttpSession session=request.getSession();
		
		List<byte[]> imageList=(List<byte[]>)request.getSession().getAttribute("IMAGELIST");
		imageList.add(fileDataAsByte);
		System.out.println("list size ="+imageList.size());
		request.getSession().setAttribute("IMAGELIST",imageList);
		request.setAttribute("Status","close");	
		fb.setHmode("CLOSE");
		return mapping.findForward("UPLOAD");
	}
	
	*/
    public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		FileUploadFB fb = (FileUploadFB) form;		
		FormFile myfile=fb.getUploadedFileName();
		String fileName=myfile.getFileName();
		
		String ftpfileLoc="Image";
		System.out.println("file name SAVE"+fileName);
		
		byte[] fileDataAsByte=null;
		byte[] bytesOut=null;
		try {
			 fileDataAsByte =myfile.getFileData();
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
		
		 BufferedImage bufferedImage = null;
			
			if(fileDataAsByte!=null){
			//System.out.println("base" + base+"media File: "+mediaFile);
			InputStream in = new ByteArrayInputStream(fileDataAsByte);
			try {
				bufferedImage = javax.imageio.ImageIO.read(in);
				in.close();				
				
				// Get a pixel
//				With this method, we can create a 24x24 avatar by calling:
					BufferedImage resized = resize(bufferedImage, 400, 400);
					
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(resized, "jpg", baos);
					bytesOut = baos.toByteArray();
					
					
					//The last step is to save the image so that we can see the results. The ImageIO class can do this job:
					//ImageIO.write(resized, "png", new File("c:/picture1.png"));

			    
				
			/*	AffineTransform tx = new AffineTransform();
			    tx.scale(1, 2);

			    AffineTransformOp op = new AffineTransformOp(tx,
			        AffineTransformOp.TYPE_BILINEAR);
			    bufferedImage = op.filter(bufferedImage, null);
*/				
				//ImageIO.write(resized, "jpg", new File("c:/temp.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			System.out.println("Byte Array to image Convert");
			}
		
		
		try
		{
			
			if(fb.getScreenType().equals("IMAGE")){
				ImageServletUTIL imgutil=new ImageServletUTIL();	
			imgutil.SaveImage(request,fileName,"IMAGE",bytesOut,fb.getScreenType(),fb.getPrimaryKey());}
			else{
				CommonServletUTIL commutil=new CommonServletUTIL();
				commutil.SaveCommonImage(request,fileName,"IMAGE",bytesOut,fb.getScreenType(),fb.getPrimaryKey());}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		fb.setHmode("CLOSE");
	
		return mapping.findForward("UPLOAD");
	}
	
	
	
	public ActionForward SAVEVIDEO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException{
		FileUploadFB fb = (FileUploadFB) form;		
		FormFile myfile=fb.getUploadedFileName();
		String fileName=myfile.getFileName();
		String ftpUrl="ftp://10.0.5.152/ftpserver";
		String ftpfileLoc="Video";
		System.out.println("file name SAVE"+fileName);
		
		byte[] fileDataAsByte=null;
		try {
			 fileDataAsByte =myfile.getFileData();
		     InputStream in = new ByteArrayInputStream(fileDataAsByte);
			 StringBuffer sb = new StringBuffer( ftpUrl );
			  	 
		    	 sb.append( "/"+ftpfileLoc+"/" );
		         sb.append( fileName );
		         /*
					 * type ==> a=ASCII mode, i=image (binary) mode, d= file
					 * directory listing
					 */
		         sb.append( ";type=i" );

		         BufferedInputStream bis = null;
		         
		         URL urlftp1 = new URL( sb.toString() );
	             URLConnection urlc1 = urlftp1.openConnection();
	             BufferedOutputStream buos = new BufferedOutputStream(urlc1.getOutputStream() );
								
				int c;
				while ((c = in.read()) != -1) {
					buos.write((char) c);
				}
				buos.close();
				in.close();
				VideoServletUTIL.SaveVideo(fileName,ftpUrl, ftpfileLoc, "VIDEO");
			 
			 
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}		
		
		
		
		fb.setHmode("CLOSE");
		return mapping.findForward("UPLOADVIDEO");
	}
	
	
	
	public ActionForward UPLOADNEWIMAGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		FileUploadFB fb = (FileUploadFB) form;
		System.out.println("uploading action for============================images");
		FileUploadUTIL.renderImages(request,fb);
		return mapping.findForward("UPLOADNEWIMAGE");
	}
	public ActionForward renderImage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
			
		return mapping.findForward("UPLOADNEWIMAGE");
	}
	private static BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height,
		BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
		}
}
