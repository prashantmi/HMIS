package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.NursingDeskLoginFB;
import inpatient.transaction.controller.utl.NursingDeskLoginUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class NursingDeskLoginACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		generateToken(request);
		NursingDeskLoginFB fb=(NursingDeskLoginFB)form;
		WebUTIL.refreshTransState(request);
		DynamicDeskUTIL.refreshSessionState(request);
		fb.reset(mapping, request);
		
		if(InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
		{
			boolean flag = NursingDeskLoginUTL.getWardListBasedOnRole(fb,request);
			if(!flag)
				return this.GETROOM(mapping, form, request, response);
			else
				return mapping.findForward("WARDONLY");
		}
		else
		{
			boolean flag=NursingDeskLoginUTL.getDeptUnitList(fb,request);
			if(!flag)
				return this.GETWARD(mapping, form, request, response);
			else
				return mapping.findForward("NEW");
		}
	}
	
	public ActionForward GETWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		NursingDeskLoginFB fb=(NursingDeskLoginFB)form;
		boolean flag=NursingDeskLoginUTL.getWardOnBasisOfUnitCode(fb,request);
		
		if(!flag)
			return this.GETROOM(mapping, form, request, response);
		else
			return mapping.findForward("NEW");
	}
	
	public ActionForward GETROOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		NursingDeskLoginFB fb=(NursingDeskLoginFB)form;
		boolean flag=NursingDeskLoginUTL.getRoomOnBasisOfWardCode(fb,request);
		
		if(!flag)
		{
			NursingDeskLoginUTL.goToNursingDesk(fb,request); 
			return mapping.findForward("DYNAMICDESK");
		}
		else
		{
			if(InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
				return mapping.findForward("WARDONLY");
			else
				return mapping.findForward("NEW");
		}
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{

		validateToken(request,response);
		NursingDeskLoginFB fb=(NursingDeskLoginFB)form;
		NursingDeskLoginUTL.goToNursingDesk(fb,request); 
		return mapping.findForward("DYNAMICDESK");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
	    WebUTIL.refreshTransState(request);
	    DynamicDeskUTIL.refreshSessionState(request);	
		return mapping.findForward("CANCEL");
    }

	public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
	    WebUTIL.refreshTransState(request);
	    DynamicDeskUTIL.refreshSessionState(request);
		return mapping.findForward("ADD");
    }
}
