package mortuary.transaction.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PostmortemDeskACT extends DispatchAction 
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("NEW");
	}
	
	public ActionForward DEFAULT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DEFAULT");
	}
	
	public ActionForward GENERALAPPEARANCE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("GENERALAPPEARANCE");
	}
	
	public ActionForward DECEASEDITEM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DECEASEDITEM");
	}
	
	public ActionForward BODYIDENTIFICATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("BODYIDENTIFICATION");
	}
	
	public ActionForward PHOTOUPLOAD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PHOTOUPLOAD");
	}
	
	public ActionForward INJURIES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("INJURIES");
	}
	
	public ActionForward TEAMDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("TEAMDETAIL");
	}
	
	public ActionForward DECEASEDSAMPLESEND(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DECEASEDSAMPLESEND");
	}
	
}
