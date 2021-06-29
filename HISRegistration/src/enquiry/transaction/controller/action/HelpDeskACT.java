package enquiry.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import enquiry.transaction.controller.fb.HelpDeskFB;

public class HelpDeskACT extends DispatchAction
{	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("WELCOME");
	}
	
	public ActionForward DEFAULT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("DEFAULT");
	}
	
	public ActionForward INPATIENTENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("INPATIENTENQUIRY");
	}
	
	public ActionForward PATIENTENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("PATIENTENQUIRY");
	}
	
	public ActionForward BULLETINBOARD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("BULLETINBOARD");
	}
	
	public ActionForward STAFFENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("STAFFENQUIRY");
	}
	
	public ActionForward DEPARTMENTENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("DEPARTMENTENQUIRY");
	}
	
	public ActionForward CONSULTANTENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CONSULTANTENQUIRY");
	}
	
	public ActionForward BLOODSTOCKENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("BLOODSTOCKENQUIRY");
	}
	
	public ActionForward OPDENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("OPDENQUIRY");
	}
	
	public ActionForward CHARGEENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CHARGEENQUIRY");
	}
	
	public ActionForward WARDENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("WARDENQUIRY");
	}
	
	public ActionForward LABENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LABENQUIRY");
	}
	
	public ActionForward HOSPITALENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("HOSPITALENQUIRY");
	}
	public ActionForward TELEPHONEENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("TELEPHONEENQUIRY");
	}
	
	public ActionForward HOLIDAY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("HOLIDAY");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	
}
