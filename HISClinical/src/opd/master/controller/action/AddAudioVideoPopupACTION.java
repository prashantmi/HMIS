package opd.master.controller.action;

import java.io.FileNotFoundException;
import java.io.IOException;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.master.controller.fb.AddAudioVideoPopupFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

public class AddAudioVideoPopupACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}	
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		generateToken(request);
	    Status objStatus=new Status();
	    objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);	    
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		AddAudioVideoPopupFB fb=(AddAudioVideoPopupFB)form;
		Status objStatus=new Status();
		FormFile myFile=fb.getFileName();
		byte[] fileDataAsByte=null;
		
		try
		{
			fileDataAsByte=myFile.getFileData();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		WebUTIL.setAttributeInSession(request,OpdConfig.UPLOADED_AUDIO_VIDEO_FILE_AS_ARRAY,fileDataAsByte);
		WebUTIL.setAttributeInSession(request,OpdConfig.UPLOADED_AUDIO_VIDEO_FILE_NAME,myFile.getFileName());
		objStatus.add(Status.DONE);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("CLOSE");
	}
	
}
