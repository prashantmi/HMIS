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

import dutyroster.masters.controller.fb.BlockAreaMstFB;
import dutyroster.masters.controller.utl.BlockAreaMstUTL;


public class BlockAreaMstACT extends CSRFGardTokenAction
{
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.NEW(mapping, form, _request, _response);
	}
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		Status status = new Status();
		status.add(Status.NEW);
		WebUTIL.refreshTransState(_request); 
		BlockAreaMstFB fb=(BlockAreaMstFB)form;
		fb.reset(mapping, _request);
		ControllerUTIL.setSysdate(_request);
		BlockAreaMstUTL.getEssentials(fb, _request);
		return mapping.findForward("ADD");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		BlockAreaMstFB _fb = (BlockAreaMstFB) form;
		BlockAreaMstUTL.saveBlockArea(_fb, _request);
		return this.NEW(mapping, form, _request, _response);
	}

	public ActionForward GETAREACODE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		BlockAreaMstFB _fb = (BlockAreaMstFB) form;
		//WebUTIL.refreshTransState(_request);
		BlockAreaMstUTL.getAssignedAreaCode(_fb, _request);
		BlockAreaMstUTL.getAreaCodeNotAssigned(_fb, _request);
		
		return mapping.findForward("ADD");
		
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		BlockAreaMstFB fb = (BlockAreaMstFB) form;
		WebUTIL.refreshTransState(_request);
		BlockAreaMstUTL.getBlockArea(fb, _request);
		return mapping.findForward("VIEW");
	}
	
	public ActionForward CHANGESEQUENCE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		BlockAreaMstFB fb = (BlockAreaMstFB) form;
		WebUTIL.refreshTransState(_request);
		BlockAreaMstUTL.getAreaCode(fb, _request);
		return mapping.findForward("CHANGESEQUENCE");
	}
	
	public ActionForward SAVECHANGESEQUENCE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		BlockAreaMstFB fb = (BlockAreaMstFB) form;
		WebUTIL.refreshTransState(_request);
		BlockAreaMstUTL.saveChangeWorkPreference(fb, _request);
		return this.NEW(mapping, form, _request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("INITIAL");
	}

}
