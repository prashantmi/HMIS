package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ProfileTypeTabMappingFB;
import opd.master.controller.util.ProfileTypeTabMappingUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class ProfileTypeTabMappingACT extends CSRFGardTokenAction
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
		ProfileTypeTabMappingFB fb=(ProfileTypeTabMappingFB)form;
		fb.reset(mapping, _request);
		fb.setHmode("NEW");
		ProfileTypeTabMappingUTL.getProfileType(fb, _request);
		return mapping.findForward("NEW");
	}

	public ActionForward GETDESKMENU(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		ProfileTypeTabMappingFB _fb = (ProfileTypeTabMappingFB) form;
		//WebUTIL.refreshTransState(_request);
		ProfileTypeTabMappingUTL.getDeskMenu(_fb, _request);
		return mapping.findForward("NEW");
		
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		ProfileTypeTabMappingFB _fb = (ProfileTypeTabMappingFB) form;
		ProfileTypeTabMappingUTL.saveProfileTabMapping(_fb, _request);
		_fb.setFetchedList("");
		return this.NEW(mapping, form, _request, _response);
	}



	/*public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		ProfileTypeTabMappingFB fb = (ProfileTypeTabMappingFB) form;
		ProfileTypeTabMappingUTL.getProcessWiseDesigForView(fb, _request);
		return mapping.findForward("NEW");
	}
	*/
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("CANCEL");
	}

}
