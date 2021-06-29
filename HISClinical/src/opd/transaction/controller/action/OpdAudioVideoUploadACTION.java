package opd.transaction.controller.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdAudioVideoUploadFB;
import opd.transaction.controller.util.OpdAudioVideoUploadUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdAudioVideoUploadACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		OpdAudioVideoUploadFB fb=(OpdAudioVideoUploadFB)form;
		fb.reset(mapping,request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		OpdAudioVideoUploadUTIL.getAudioVideoEssentials(fb,request);	
		return mapping.findForward("NEW");
	}
	
	public ActionForward PLAY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		OpdAudioVideoUploadFB fb=(OpdAudioVideoUploadFB)form;
		OpdAudioVideoUploadUTIL.getAudioVideoFile(fb,request,response);
		return mapping.findForward("PLAY");
	}
	
	public ActionForward UPLOAD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		OpdAudioVideoUploadFB fb=(OpdAudioVideoUploadFB)form;
		new OpdAudioVideoUploadUTIL().setAudioVideoUpload(fb,request);
		return this.NEW(mapping, form, request, response);
	}

}
