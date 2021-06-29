package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.UnitAudioVideoMasterFB;
import opd.master.controller.util.UnitAudioVideoMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class UnitAudioVideoMasterACTION extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		generateToken(request);
		UnitAudioVideoMasterFB fb=(UnitAudioVideoMasterFB)form;
	//	WebUTIL.refreshTransState(request);
		//fb.reset(mapping, request);
		//UnitAudioVideoMasterUTIL.setUnitName(fb,request);
		UnitAudioVideoMasterUTIL.getUnitAudioVideoEssential(fb,request);
		
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitAudioVideoMasterFB fb=(UnitAudioVideoMasterFB)form;
		UnitAudioVideoMasterUTIL.saveUnitAudioVideo(fb,request);
		UnitAudioVideoMasterUTIL.getUnitAudioVideoEssential(fb,request);
		fb.reset(mapping, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitAudioVideoMasterFB fb=(UnitAudioVideoMasterFB)form;
		UnitAudioVideoMasterUTIL.setUnitName(fb,request);
		UnitAudioVideoMasterUTIL.getUnitAudioVideoForModify(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitAudioVideoMasterFB fb=(UnitAudioVideoMasterFB)form;
		UnitAudioVideoMasterUTIL.modifySaveUnitAudioVideo(fb,request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("LIST"); 
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitAudioVideoMasterFB fb=(UnitAudioVideoMasterFB)form;
		UnitAudioVideoMasterUTIL.setUnitName(fb,request);
		UnitAudioVideoMasterUTIL.getUnitAudioVideoForModify(fb,request);
		return mapping.findForward("NEW");
	}
}
