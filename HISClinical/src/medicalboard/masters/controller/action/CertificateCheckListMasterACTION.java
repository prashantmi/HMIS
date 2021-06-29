package medicalboard.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.masters.controller.fb.CertificateChecklistMasterFB;
import medicalboard.masters.controller.utl.CertificateChecklistMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CertificateCheckListMasterACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		CertificateChecklistMasterFB fb = (CertificateChecklistMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setMode("ADD");
		CertificateChecklistMasterUTL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDCHECKLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CertificateChecklistMasterFB fb = (CertificateChecklistMasterFB) form;
		CertificateChecklistMasterUTL.addChecklist(fb, request);
		String forward="";
		if(fb.getMode().equals("ADD")){
			forward="NEW";
		}
		else{
			forward="MODIFY";
		}
		return mapping.findForward(forward);
	}
	
	public ActionForward REMOVECHECKLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		CertificateChecklistMasterFB fb = (CertificateChecklistMasterFB) form;
		CertificateChecklistMasterUTL.removeChecklist(fb, request);
		String forward="";
		if(fb.getMode().equals("ADD")){
			forward="NEW";
		}
		else{
			forward="MODIFY";
		}
		return mapping.findForward(forward);
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CertificateChecklistMasterFB fb = (CertificateChecklistMasterFB) form;
//		Status objStatus = new Status();
		CertificateChecklistMasterUTL.saveCerificateChecklistMst(fb, request);
		return this.ADD(mapping, form, request, response);
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		CertificateChecklistMasterFB _fb = (CertificateChecklistMasterFB) form;
		WebUTIL.refreshTransState(_request);
		_fb.setMode("MODIFY");
		CertificateChecklistMasterUTL.setEssentials(_fb, _request);
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		CertificateChecklistMasterFB _fb = (CertificateChecklistMasterFB) form;
		CertificateChecklistMasterUTL.modifySaveCertificateChecklist(_fb, _request);
		return mapping.findForward("LIST");
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		CertificateChecklistMasterFB _fb = (CertificateChecklistMasterFB) form;
		CertificateChecklistMasterUTL.setEssentials(_fb, _request);
		return mapping.findForward("NEW");
	}
}
