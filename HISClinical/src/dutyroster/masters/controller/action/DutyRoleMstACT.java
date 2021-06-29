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

import dutyroster.masters.controller.fb.DutyRoleMstFB;
import dutyroster.masters.controller.utl.DutyRoleMstUTL;

public class DutyRoleMstACT extends CSRFGardTokenAction
{
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{

		generateToken(_request);
        Status status = new Status();
		status.add(Status.NEW);
        WebUTIL.refreshTransState(_request); 
		((DutyRoleMstFB) form).reset(mapping, _request);
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		DutyRoleMstFB _fb = (DutyRoleMstFB) form;
		boolean hasFlag = DutyRoleMstUTL.saveDutyRole(_fb, _request);
		//WebUTIL.refreshTransState(_request);
		if (hasFlag)
		{
			_fb.reset(mapping, _request);
		}
		return mapping.findForward(_fb.getHmode());
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		DutyRoleMstFB _fb = (DutyRoleMstFB) form;
		//WebUTIL.refreshTransState(_request);
		DutyRoleMstUTL.getDutyRole(_fb, _request);
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		DutyRoleMstFB _fb = (DutyRoleMstFB) form;
		//WebUTIL.refreshTransState(_request);
		DutyRoleMstUTL.updateDutyRole(_fb, _request);
		return mapping.findForward(_fb.getHmode());
		
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		DutyRoleMstFB _fb = (DutyRoleMstFB) form;
		//WebUTIL.refreshTransState(_request);
		DutyRoleMstUTL.getDutyRole(_fb, _request);
		return mapping.findForward("ADD");
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("LIST");
	}

}
