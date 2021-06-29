package mrd.transaction.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.OPDFileTrackingDeskFB;
import mrd.transaction.controller.utl.OPDFileTrackingDeskUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OPDFileTrackingDeskACTION extends DispatchAction {

	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.MOVEMENT(mapping,form,request,response);
	}
	
	public ActionForward MOVEMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OPDFileTrackingDeskFB fb=(OPDFileTrackingDeskFB)form;
		Status objStatus =new Status();
		OPDFileTrackingDeskUTIL.setTabSequence(MrdConfig.OPD_FILE_MOVEMENT, request);
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);
		fb.setDeskmode("MOVEMENT");
		fb.setHmode("NEW");
		return mapping.findForward("MOVEMENT");
	}
	
	public ActionForward RETURN(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OPDFileTrackingDeskUTIL.setTabSequence(MrdConfig.OPD_FILE_RETURN, request);
		return mapping.findForward("RETURN");
	}
	
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
