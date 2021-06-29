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

import dutyroster.masters.controller.fb.RosterDesignationMstFB;
import dutyroster.masters.controller.utl.RosterDesignationMstUTL;

public class RosterDesignationMstACT extends CSRFGardTokenAction
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.NEW(mapping, form, _request, _response);
	}
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		WebUTIL.refreshTransState(_request); 
		RosterDesignationMstFB fb=(RosterDesignationMstFB)form;
		fb.reset(mapping, _request);
		ControllerUTIL.setSysdate(_request);
		RosterDesignationMstUTL.getRosterType(fb, _request);
		Status st=(Status)_request.getAttribute(Config.STATUS_OBJECT);
		//WebUTIL.setStatus(_request, status);
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETDESIGNATION(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RosterDesignationMstFB fb=(RosterDesignationMstFB)form;
		ControllerUTIL.setSysdate(_request);
		RosterDesignationMstUTL.getAssignedDesignation(fb, _request);
		RosterDesignationMstUTL.getNotAssignedDesignation(fb, _request);
		return mapping.findForward("ADD");
	}
	

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RosterDesignationMstFB _fb = (RosterDesignationMstFB) form;
		RosterDesignationMstUTL.saveRosterDesignation(_fb, _request);
		return this.NEW(mapping, form, _request, _response);
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("LIST");
	}

}
