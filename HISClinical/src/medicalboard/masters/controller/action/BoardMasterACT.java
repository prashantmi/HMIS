package medicalboard.masters.controller.action;


import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import medicalboard.masters.controller.fb.BoardMasterFB;
import medicalboard.masters.controller.utl.BoardMasterUTL;


public class BoardMasterACT extends CSRFGardTokenAction
{
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);

		BoardMasterFB fb = (BoardMasterFB) form;
		WebUTIL.refreshTransState(request);
		BoardMasterUTL.getEssential(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		BoardMasterFB fb = (BoardMasterFB) form;
		 BoardMasterUTL.saveBoardInfo(fb,request);
		 fb.reset(mapping, request);
		 return this.ADD(mapping, form, request, response);
	}
	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		BoardMasterFB _fb = (BoardMasterFB) form;
		WebUTIL.refreshTransState(_request);
		BoardMasterUTL.getBoardDetail(_fb, _request);
		return mapping.findForward("NEW");
	}
	
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		BoardMasterFB _fb = (BoardMasterFB) form;
		boolean hasFlage;
		hasFlage=BoardMasterUTL.saveModBoardInfo(_fb, _request);
		if(hasFlage){
		  return mapping.findForward("LIST");}
		else{
		  return mapping.findForward("NEW");
		}
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		BoardMasterFB _fb = (BoardMasterFB) form;
		WebUTIL.refreshTransState(_request);
		_fb.setBoardTypeID(_fb.getControls()[0]);
		BoardMasterUTL.getBoardDetail(_fb, _request);
		return mapping.findForward("NEW");
	}
}
