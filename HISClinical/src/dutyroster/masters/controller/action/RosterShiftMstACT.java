package dutyroster.masters.controller.action;

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

import dutyroster.masters.controller.fb.RosterShiftMstFB;
import dutyroster.masters.controller.utl.RosterShiftMstUTL;
import dutyroster.masters.controller.utl.RosterTypeMstUTL;

public class RosterShiftMstACT extends CSRFGardTokenAction
{
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		//Status status = new Status();
		generateToken(_request);
		Status obj= WebUTIL.getStatus(_request);
		obj.add(Status.NEW);
		
		WebUTIL.refreshTransState(_request); 
		
		RosterShiftMstFB fb=(RosterShiftMstFB)form;
		fb.reset(mapping, _request);
		ControllerUTIL.setSysdate(_request);
		RosterShiftMstUTL.getEssentials(fb, _request);
		WebUTIL.setStatus(_request, obj);
		return mapping.findForward("ADD");
	}

	public ActionForward GET_SHIFTS_BASED_ON_ROSTER(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		//Status status = new Status();
		//status.add(Status.NEW);
		RosterShiftMstFB fb=(RosterShiftMstFB)form;
		RosterShiftMstUTL.getShiftsBasedOnRoster(fb, _request);
	//	WebUTIL.setStatus(_request, status);
		return mapping.findForward("ADD");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RosterShiftMstFB _fb = (RosterShiftMstFB) form;
		RosterShiftMstUTL.saveRosterShift(_fb, _request);
		
		Status obj= WebUTIL.getStatus(_request);
		
		
		return this.ADD(mapping, form, _request, _response);
	//	return mapping.findForward(_fb.getHmode());
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		RosterShiftMstFB _fb = (RosterShiftMstFB) form;
		WebUTIL.refreshTransState(_request);
		//RosterShiftMstUTL.getEssentials(_fb, _request);
		RosterShiftMstUTL.getRosterShift(_fb, _request);
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RosterShiftMstFB _fb = (RosterShiftMstFB) form;
		//WebUTIL.refreshTransState(_request);
		RosterShiftMstUTL.modifyRosterShift(_fb, _request);
		return mapping.findForward(_fb.getHmode());
		
		//return this.ADD(mapping, form, _request, _response);
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		RosterShiftMstFB fb = (RosterShiftMstFB) form;
		WebUTIL.refreshTransState(_request);
		RosterShiftMstUTL.getRosterShift(fb, _request);
		return mapping.findForward("ADD");
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("LIST");
	}

}
