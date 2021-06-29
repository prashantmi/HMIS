package inpatient.transaction.controller.action;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.IpdDoctorDeskLoginFB;
import inpatient.transaction.controller.utl.IpdDoctorDeskLoginUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IpdDoctorDeskLoginACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{		
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		IpdDoctorDeskLoginFB fb=(IpdDoctorDeskLoginFB)form;
		WebUTIL.refreshTransState(request);
		DynamicDeskUTIL.refreshSessionState(request);
		fb.reset(mapping, request);
		
		if(InpatientConfig.IPD_DOCTOR_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
		{
			boolean flag = IpdDoctorDeskLoginUTL.getWardListBasedOnRole(fb,request);
			if(!flag)
				return this.GETROOM(mapping, form, request, response);
			else
				return mapping.findForward("WARDONLY");
		}
		else
		{
			boolean flag=IpdDoctorDeskLoginUTL.getDeptUnitList(fb,request);
			if(!flag)
				return this.GETWARD(mapping, form, request, response);
			else
				return mapping.findForward("NEW");
		}
	}
	
	public ActionForward GETWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		IpdDoctorDeskLoginFB fb=(IpdDoctorDeskLoginFB)form;
		boolean flag=IpdDoctorDeskLoginUTL.getWardOnBasisOfUnitCode(fb,request);
		
		if(!flag)
			return this.GETROOM(mapping, form, request, response);
		else
			return mapping.findForward("NEW");
	}
	
	public ActionForward GETROOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		IpdDoctorDeskLoginFB fb=(IpdDoctorDeskLoginFB)form;
		boolean flag=IpdDoctorDeskLoginUTL.getRoomOnBasisOfWardCode(fb,request);
		
		if(!flag)
		{
			IpdDoctorDeskLoginUTL.goToIpdDoctorDesk(fb,request); 
			return mapping.findForward("DYNAMICDESK");
		}
		else
		{
			if(InpatientConfig.IPD_DOCTOR_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_NO))
				return mapping.findForward("WARDONLY");
			else
				return mapping.findForward("NEW");
		}
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		IpdDoctorDeskLoginFB fb=(IpdDoctorDeskLoginFB)form;
		IpdDoctorDeskLoginUTL.goToIpdDoctorDesk(fb,request); 
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
