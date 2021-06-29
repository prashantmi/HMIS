package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.NursingStationLoginFB;
import inpatient.transaction.controller.utl.NursingStationLoginUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class NursingStationLoginACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		generateToken(request);
		NursingStationLoginFB fb=(NursingStationLoginFB)form;
		WebUTIL.refreshTransState(request);
		DynamicDeskUTIL.refreshSessionState(request);		
		fb.reset(mapping, request);
		
		if(InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
		{
			boolean flag = NursingStationLoginUTL.getWardListBasedOnRole(fb,request);
			if(!flag)
				return this.GETROOM(mapping, form, request, response);
			else
				return mapping.findForward("WARDONLY");
		}
		else
		{
			boolean flag=NursingStationLoginUTL.getDeptUnitList(fb,request);
			if(!flag)
				return this.GETWARD(mapping, form, request, response);
			else			
				return mapping.findForward("NEW");
		}
	}
	
	public ActionForward GETWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		NursingStationLoginFB fb=(NursingStationLoginFB)form;
		boolean flag=NursingStationLoginUTL.getWardOnBasisOfUnitCode(fb,request);
		
		if(!flag)
			return this.GETROOM(mapping, form, request, response);
		else
			return mapping.findForward("NEW");
	}
	
	public ActionForward GETROOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		NursingStationLoginFB fb=(NursingStationLoginFB)form;
		boolean flag=NursingStationLoginUTL.getRoomOnBasisOfWardCode(fb,request);
		
		if(!flag)
		{
			NursingStationLoginUTL.goToNursingStation(fb,request); 
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
		NursingStationLoginFB fb=(NursingStationLoginFB)form;
		NursingStationLoginUTL.goToNursingStation(fb,request); 
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
