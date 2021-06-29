package mrd.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.RackShelfMstFB;
import mrd.masters.controller.util.RackShelfMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class RackShelfMstACT extends CSRFGardTokenAction
{
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		WebUTIL.refreshTransState(_request); 
		RackShelfMstFB fb=(RackShelfMstFB)form;
		fb.reset(mapping, _request);
		RackShelfMstUTL.getEssentials(fb, _request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RackShelfMstFB _fb = (RackShelfMstFB) form;
		boolean flag=RackShelfMstUTL.saveRackShelf(_fb, _request);
		_fb.setHmode("ADD");
		if(flag){
			_fb.reset(mapping, _request);
			return mapping.findForward("ADD");
		}else
			return mapping.findForward("ADD");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RackShelfMstFB _fb = (RackShelfMstFB) form;
		WebUTIL.refreshTransState(_request); 
		_fb.setMode("MODIFY");
		RackShelfMstUTL.getRackShelfDetail(_fb, _request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RackShelfMstFB _fb = (RackShelfMstFB) form;
		WebUTIL.refreshTransState(_request); 
		_fb.setMode("MODIFY");
		RackShelfMstUTL.getRackShelfDetail(_fb, _request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		//validateToken(_request,_response);
		RackShelfMstFB _fb = (RackShelfMstFB) form;
		boolean flag=RackShelfMstUTL.modifyRackShelf(_fb, _request);
		_fb.setHmode("MODIFY");
		if(flag){
			return mapping.findForward("LIST");
		}else
			return mapping.findForward("ADD");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("LIST");
	}

}
