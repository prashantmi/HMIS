package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.master.controller.fb.AudioVideoMasterFB;
import opd.master.controller.util.AudioVideoMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AudioVideoMasterACTION extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		AudioVideoMasterFB fb=(AudioVideoMasterFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		fb.setTempMode(fb.getHmode());
		fb.setIsDefault(OpdConfig.NO);
		
		return mapping.findForward("NEW");
		
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AudioVideoMasterFB fb=(AudioVideoMasterFB)form;
		WebUTIL.refreshTransState(request);
		AudioVideoMasterUTIL.getAudioVideoForModify(fb,request);
		fb.setTempMode(fb.getHmode());

		return mapping.findForward("NEW");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AudioVideoMasterFB fb=(AudioVideoMasterFB)form;
		WebUTIL.refreshTransState(request);
		AudioVideoMasterUTIL.getAudioVideoForModify(fb,request);
		//AudioVideoMasterUTIL.playAudioVideoFile(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward FILE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AudioVideoMasterFB fb=(AudioVideoMasterFB)form;
		AudioVideoMasterUTIL.addFile(fb,request);
		fb.setHmode(fb.getTempMode());
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		AudioVideoMasterFB fb=(AudioVideoMasterFB)form;
		AudioVideoMasterUTIL.saveAudioVideoFile(fb,request);
		fb.setHmode(fb.getTempMode());
		
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		AudioVideoMasterFB fb=(AudioVideoMasterFB)form;
		boolean flag=AudioVideoMasterUTIL.saveModifyAudioVideo(fb,request);
		fb.setHmode(fb.getTempMode());
		 if(flag)
		 {
			 return mapping.findForward("LIST");
		 }
		 else
		 {
			 return this.MODIFY(mapping, form, request, response);
		 }
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		 	
		  return mapping.findForward("LIST");		 	   	
	}
}
