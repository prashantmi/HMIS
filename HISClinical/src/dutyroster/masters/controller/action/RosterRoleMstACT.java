package dutyroster.masters.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import dutyroster.masters.controller.fb.RosterRoleMstFB;
import dutyroster.masters.controller.utl.RosterRoleMstUTL;

public class RosterRoleMstACT extends CSRFGardTokenAction
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.NEW(mapping, form, _request, _response);
	}
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		WebUTIL.refreshTransState(_request); 
		RosterRoleMstFB fb=(RosterRoleMstFB)form;
		fb.reset(mapping, _request);
		ControllerUTIL.setSysdate(_request);
		RosterRoleMstUTL.getRosterType(fb, _request);
		Status st=(Status)_request.getAttribute(Config.STATUS_OBJECT);
		//WebUTIL.setStatus(_request, status);
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETDUTYROLE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RosterRoleMstFB fb=(RosterRoleMstFB)form;
		ControllerUTIL.setSysdate(_request);
		RosterRoleMstUTL.getDutyRole(fb, _request);
		RosterRoleMstUTL.getDutyRoleNotIn(fb, _request);
		return mapping.findForward("ADD");
	}
	

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RosterRoleMstFB _fb = (RosterRoleMstFB) form;
		RosterRoleMstUTL.saveRosterShift(_fb, _request);
		return this.NEW(mapping, form, _request, _response);
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("LIST");
	}

}
