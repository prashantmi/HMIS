package registration.controller.action;



import java.io.FileNotFoundException;
import java.io.IOException;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.tika.Tika;

import registration.RegistrationConfig;
import registration.controller.fb.FileUploadFB;

public class FileUploadACTION extends DispatchAction
{	
	/**
	 * the default action called 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("INDIDE UNSPECIFIED");
		return this.NEW(mapping,form,request,response);		
	}	
    public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
	    //System.out.println("inside new of upload");
	    Status objStatus=new Status();
	    objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);	    
	 	return mapping.findForward("NEW");
	}
    public ActionForward AUDIOVEDIO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
	   // System.out.println("inside new of upload");
	    Status objStatus=new Status();
	    objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);	    
	 	return mapping.findForward("AUDIOVEDIO");
	}
    
    /**
     * This method eaxtract byte array out of uploaded file and keeps
     * that bytearray in session
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		FileUploadFB fb = (FileUploadFB) form;
		Status objStatus=new Status();
		FormFile myfile=fb.getUploadedFileName();
		byte[] fileDataAsByte=null;
		long count=0;
		Tika tika=null;
		if(myfile!=null)
		{
			//System.out.println("myfile"+myfile.getFileName());
			if(myfile.getFileName().toUpperCase().contains(".JPG") || myfile.getFileName().toUpperCase().contains(".PDF") || myfile.getFileName().toUpperCase().contains(".JPEG") || myfile.getFileName().toUpperCase().contains(".GIF") || myfile.getFileName().toUpperCase().contains(".BMP"))
			{
				 if (myfile.getFileName().toUpperCase().contains(".JPEG")) 
				 {
					
						 tika = new Tika();		                 
		                 //detecting the file type using detect method
		                 String filetype = tika.detect(myfile.getInputStream());
		                 //System.out.println("Tika File Type-->>>"+filetype);
						 if(!filetype.equals("image/jpeg"))
						 {
							 	//System.out.println("myfile3"+myfile.getFileName());
								objStatus.add(Status.UNSUCESSFULL);
								WebUTIL.setStatus(request,objStatus);	    
							 	return mapping.findForward("NEW");
						 }
	
				}
				 
				 /// Added by Dheeraj on 27-Sept-2018 to add PDF files for MRDDocumentUpload
				 
				 if (myfile.getFileName().toUpperCase().contains(".PDF")) 
				 {
					
						 tika = new Tika();		                 
		                 //detecting the file type using detect method
		                 String filetype = tika.detect(myfile.getInputStream());
		                 //System.out.println("Tika File Type-->>>"+filetype);
						 if(!filetype.equals("application/pdf"))
						 {
							 	//System.out.println("myfile3"+myfile.getFileName());
								objStatus.add(Status.UNSUCESSFULL);
								WebUTIL.setStatus(request,objStatus);	    
							 	return mapping.findForward("NEW");
						 }
	
				}
				 
				 /////////////////////////////////////////////////////////////////////////////////
				if (myfile.getFileName().toUpperCase().contains(".JPG")) 
				{
					
						 tika = new Tika();		                 
		                 //detecting the file type using detect method
		                 String filetype = tika.detect(myfile.getInputStream());
		                 //System.out.println("Tika File Type-->>>"+filetype);
						 if(!filetype.equals("image/jpeg"))
						 {
							 	//System.out.println("myfile3"+myfile.getFileName());
								objStatus.add(Status.UNSUCESSFULL);
								WebUTIL.setStatus(request,objStatus);	    
							 	return mapping.findForward("NEW");
						 }
	
				}
				if (myfile.getFileName().toUpperCase().contains(".GIF")) 
				{
					
						 tika = new Tika();		                 
		                 //detecting the file type using detect method
		                 String filetype = tika.detect(myfile.getInputStream());
		                 //System.out.println("Tika File Type-->>>"+filetype);
						 if(!filetype.equals("image/gif"))
						 {
							 	//System.out.println("myfile3"+myfile.getFileName());
								objStatus.add(Status.UNSUCESSFULL);
								WebUTIL.setStatus(request,objStatus);	    
							 	return mapping.findForward("NEW");
						 }
	
				}
				if (myfile.getFileName().toUpperCase().contains(".BMP")) 
				{
					
						 tika = new Tika();		                 
		                 //detecting the file type using detect method
		                 String filetype = tika.detect(myfile.getInputStream());
		                 //System.out.println("Tika File Type-->>>"+filetype);
						 if(!filetype.equals("image/bmp"))
						 {
							 	//System.out.println("myfile3"+myfile.getFileName());
								objStatus.add(Status.UNSUCESSFULL);
								WebUTIL.setStatus(request,objStatus);	    
							 	return mapping.findForward("NEW");
						 }
	
				} 
				//System.out.println("myfile2"+myfile.getFileName());
				try 
				{
					
					 int fileSize=myfile.getFileSize();
					 if(fileSize>4194304){
						 throw new Exception("File Is Larger Than 4 MB");
					 }
					 fileDataAsByte =myfile.getFileData();
					
					/*for(int i=0;i<fileDataAsByte.length;i++){
						count=count+fileDataAsByte[i];
					}
					*/
					 	WebUTIL.setAttributeInSession(request,RegistrationConfig.UPLOADED_FILE_AS_ARRAY,fileDataAsByte);		
						WebUTIL.setAttributeInSession(request,RegistrationConfig.UPLOADED_FILE_NAME,myfile.getFileName());
						//System.out.println("Inside SAVE of FileUploadACTION");
						objStatus.add(Status.DONE);
						WebUTIL.setStatus(request,objStatus);	
					
				} catch (FileNotFoundException e) {
						e.printStackTrace();
				} catch (IOException e) {
						e.printStackTrace();
				}		
				catch (Exception e) {
					objStatus.add(Status.NEW);
					objStatus.add(Status.ERROR_DA);
					WebUTIL.setStatus(request,objStatus);	
					
					return mapping.findForward("CLOSE");
					
				}
			}
			else
			{
				//System.out.println("myfile3"+myfile.getFileName());
				objStatus.add(Status.UNSUCESSFULL);
				WebUTIL.setStatus(request,objStatus);	    
			 	return mapping.findForward("NEW");
			}
		}
		return mapping.findForward("CLOSE");
	}
}
