package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdNextVisitAppointmentFB;
import opd.transaction.controller.util.OpdNextVisitAppointmentUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdNextVisitAppointmentACTION extends CSRFGardTokenAction {
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		generateToken(request);
		return showslotdtlPage(mapping,form,request,response); 	
	}
	public ActionForward showslotdtlPage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		System.out.println("inside Apt_AllSlotDtlACTION ShowMainPage.............");		
		OpdNextVisitAppointmentFB fb = (OpdNextVisitAppointmentFB)form;				
		WebUTIL.refreshTransState(request);
		OpdNextVisitAppointmentUTIL.setSysdate(request);		
		fb.reset(mapping,request);		
		OpdNextVisitAppointmentUTIL.getEssentials(request,fb);
		OpdNextVisitAppointmentUTIL.getNextSlotDate(request,fb);
		OpdNextVisitAppointmentUTIL.getSlotDtl(request, fb, 2) ;
		return mapping.findForward("showslotdtlPage");
	}
	public ActionForward GETSLOTDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
				
		OpdNextVisitAppointmentFB fb = (OpdNextVisitAppointmentFB)form;
		OpdNextVisitAppointmentUTIL.getSlotDtl(request, fb, 2);
		OpdNextVisitAppointmentUTIL.showSlotRows(fb,request,response);
		
		return null;
	}
	public ActionForward GIVENAPPOINTMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		System.out.println("inside Apt_AllSlotDtlACTION GIVENAPPOINTMENT ............... ");		
		OpdNextVisitAppointmentFB fb = (OpdNextVisitAppointmentFB)form;
		OpdNextVisitAppointmentUTIL.SaveData(request,fb);
		return mapping.findForward("showslotdtlPage");
	}


}
