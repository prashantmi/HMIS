package mrd.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import mrd.transaction.controller.fb.OPDFileTrackingFB;
import mrd.transaction.controller.utl.OPDFileTrackingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OPDFileTrackingACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OPDFileTrackingFB fb=(OPDFileTrackingFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		boolean flag = OPDFileTrackingUTIL.getMrdList(fb,request);
		if(flag)
			return this.DESK(mapping, form, request, response);
		else
			return mapping.findForward("NEW");
	}
	
	
	
	public ActionForward DESK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OPDFileTrackingFB fb=(OPDFileTrackingFB)form;
		OPDFileTrackingUTIL.setDetails(fb,request);
		return mapping.findForward("DESK");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}

}
