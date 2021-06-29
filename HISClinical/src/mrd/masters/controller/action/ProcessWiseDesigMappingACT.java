package mrd.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.ProcessWiseDesigMappingFB;
import mrd.masters.controller.util.ProcessWiseDesigMappingUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class ProcessWiseDesigMappingACT extends CSRFGardTokenAction
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
		ProcessWiseDesigMappingFB fb=(ProcessWiseDesigMappingFB)form;
		fb.reset(mapping, _request);
		fb.setHmode("NEW");
		ProcessWiseDesigMappingUTL.getProcessType(fb, _request);
		return mapping.findForward("NEW");
	}

	public ActionForward GETDESIG(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		ProcessWiseDesigMappingFB _fb = (ProcessWiseDesigMappingFB) form;
		//WebUTIL.refreshTransState(_request);
		ProcessWiseDesigMappingUTL.getAssignedDesignationList(_fb, _request);
		ProcessWiseDesigMappingUTL.getNotAssignedDesignationList(_fb, _request);
		return mapping.findForward("NEW");
		
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		ProcessWiseDesigMappingFB _fb = (ProcessWiseDesigMappingFB) form;
		ProcessWiseDesigMappingUTL.saveProcessWiseDesig(_fb, _request);
		_fb.setFetchedList("");
		return this.NEW(mapping, form, _request, _response);
	}



	/*public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		ProcessWiseDesigMappingFB fb = (ProcessWiseDesigMappingFB) form;
		ProcessWiseDesigMappingUTL.getProcessWiseDesigForView(fb, _request);
		return mapping.findForward("NEW");
	}
	*/
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("CANCEL");
	}

}
