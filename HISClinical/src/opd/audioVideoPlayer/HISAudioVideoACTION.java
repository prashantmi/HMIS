package opd.audioVideoPlayer;


import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class HISAudioVideoACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HisAudioVideoFB fb=(HisAudioVideoFB)form;
		fb.reset(mapping,request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		HisAudioVideoUTIL.getAudioVideoEssentials(fb,request);	
		return mapping.findForward("NEW");
	}
	
	public ActionForward PLAY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HisAudioVideoFB fb=(HisAudioVideoFB)form;
		HisAudioVideoUTIL.getAudioVideoFile(fb,request,response);
		return mapping.findForward("PLAY");
	}
	
	public ActionForward UPLOAD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HisAudioVideoFB fb=(HisAudioVideoFB)form;
		new HisAudioVideoUTIL().setAudioVideoUpload(fb,request);
		return this.NEW(mapping, form, request, response);
	}

}
