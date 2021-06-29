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

import dutyroster.masters.controller.fb.RosterTypeMstFB;
import dutyroster.masters.controller.utl.RosterTypeMstUTL;

public class RosterTypeMstACT extends CSRFGardTokenAction
{
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.refreshTransState(_request); 
		RosterTypeMstFB fb=(RosterTypeMstFB)form;
		fb.reset(mapping, _request);
		ControllerUTIL.setSysdate(_request);
		RosterTypeMstUTL.getEssentials(fb, _request);
		WebUTIL.setStatus(_request, status);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);

		RosterTypeMstFB _fb = (RosterTypeMstFB) form;
		boolean hasFlag = RosterTypeMstUTL.saveRosterType(_fb, _request);
		
		if (hasFlag)
		{
			_fb.reset(mapping, _request);
		}
		/*else
			WebUTIL.refreshTransState(_request);	*/
			
		 return mapping.findForward("ADD");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		RosterTypeMstFB _fb = (RosterTypeMstFB) form;
		WebUTIL.refreshTransState(_request);
		RosterTypeMstUTL.getEssentials(_fb, _request);
		RosterTypeMstUTL.getRosterType(_fb, _request);
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);

		RosterTypeMstFB _fb = (RosterTypeMstFB) form;
		//WebUTIL.refreshTransState(_request);
		RosterTypeMstUTL.modifyRosterType(_fb, _request);
		return mapping.findForward(_fb.getHmode());
		
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		RosterTypeMstFB fb = (RosterTypeMstFB) form;
		WebUTIL.refreshTransState(_request);
		RosterTypeMstUTL.getEssentials(fb, _request);
		RosterTypeMstUTL.getRosterType(fb, _request);
		return mapping.findForward("ADD");
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("LIST");
	}

}
