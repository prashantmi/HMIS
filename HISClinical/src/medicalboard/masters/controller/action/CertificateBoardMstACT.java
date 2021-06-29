package medicalboard.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.masters.controller.fb.CertificateBoardMstFB;
import medicalboard.masters.controller.utl.CertificateBoardMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class CertificateBoardMstACT extends CSRFGardTokenAction{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.NEW(mapping, form, _request, _response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		WebUTIL.refreshTransState(_request); 
		CertificateBoardMstFB fb=(CertificateBoardMstFB)form;
		fb.reset(mapping, _request);
		CertificateBoardMstUTL.getEssential(_request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETBOARD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		CertificateBoardMstFB fb=(CertificateBoardMstFB)form;
		CertificateBoardMstUTL.getBoard(fb,_request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		CertificateBoardMstFB _fb = (CertificateBoardMstFB) form;
		CertificateBoardMstUTL.saveCertificateBoardInfo(_fb, _request);
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
		return mapping.findForward("CANCEL");
	}

	
	
	
}
