package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.DoctorWardRoundDetailFB;
import inpatient.transaction.controller.utl.DoctorWardRoundDetailUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DoctorWardRoundDetailACT extends CSRFGardTokenAction

{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		DoctorWardRoundDetailFB fb = (DoctorWardRoundDetailFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		DoctorWardRoundDetailUTL.getDeptUnitList(fb, request);
		DoctorWardRoundDetailUTL.getRoundByEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward FETCHROUNDBY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DoctorWardRoundDetailFB fb = (DoctorWardRoundDetailFB) form;
		//fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		DoctorWardRoundDetailUTL.getRoundByEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DoctorWardRoundDetailFB fb=(DoctorWardRoundDetailFB)form;
		DoctorWardRoundDetailUTL.saveDoctorWardRoundDtl(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward ROUNDTYPE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DoctorWardRoundDetailFB fb=(DoctorWardRoundDetailFB)form;
		DoctorWardRoundDetailUTL.getOnCallDetails(fb,request);
		return mapping.findForward("ROUNDTYPE");
	}
	
	public ActionForward SAVECALLDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DoctorWardRoundDetailFB fb=(DoctorWardRoundDetailFB)form;
		DoctorWardRoundDetailUTL.saveCallDetails(fb, request);
		return null;
	}

}
