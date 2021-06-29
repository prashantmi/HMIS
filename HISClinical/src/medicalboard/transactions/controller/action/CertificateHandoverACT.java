package medicalboard.transactions.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.servlets.ServletsUtilityConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.transactions.controller.fb.CertificateHandoverFB;
import medicalboard.transactions.controller.utl.CertificateHandoverUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CertificateHandoverACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}

	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		CertificateHandoverFB _fb = (CertificateHandoverFB) form;
		_fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
		CertificateHandoverUTL.setCertificateHandoverEssentials(_fb, request);
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//CertificateHandoverFB fb=(CertificateHandoverFB)form;
		return mapping.findForward("CANCEL");
	}
	
	
public ActionForward REQDATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{	
		CertificateHandoverFB _fb = (CertificateHandoverFB) form;
		_fb.setTemplateId("");
		if(_fb.getCertificateTypeID().equals("-1"))
		{
			return this.NEW(mapping, form, request, response);
		}
		else
		{
			CertificateHandoverUTL.getReqDateByCertificateTypeId(_fb, request);
		 	return mapping.findForward("NEW");
		}
		
}
public ActionForward GETCANDIDATELIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	CertificateHandoverFB _fb = (CertificateHandoverFB) form;
	CertificateHandoverUTL.getCandidateListByReqDate(_fb, request);
 	return mapping.findForward("NEW");
}

public ActionForward HANDOVERTO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	CertificateHandoverFB _fb = (CertificateHandoverFB) form;
	CertificateHandoverUTL.getHandOverToDetail(_fb, request);
 	return mapping.findForward("NEW");
}

public ActionForward SELECTHANDOVERTO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	CertificateHandoverFB _fb = (CertificateHandoverFB) form;
	CertificateHandoverUTL.getSelectHandOverTo(_fb, request);
 	return mapping.findForward("NEW");
}

public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
{
	validateToken(request,response);
	CertificateHandoverFB _fb = (CertificateHandoverFB) form;
	CertificateHandoverUTL.saveHandOverDetail(_fb, request);
	
	String [] CertificateArr= null;
	
	if(_fb.getCertificateTypeID()!=null&& !_fb.getCertificateTypeID().equals(""))
	{
	 CertificateArr = _fb.getCertificateTypeID().split("#");
	}
	_fb.setCertificateTypeID("-1");

	
	if(CertificateArr!=null)
	{
		if(CertificateArr.length==4)
		{
			return mapping.findForward("NEW");
		}
		
		else return this.NEW(mapping,form,request,response);	
		
	}
	else if(_fb.getTemplateId()!=null&& !_fb.getTemplateId().equals(""))
		return mapping.findForward("NEW");
	
	else
	{
		return this.NEW(mapping,form,request,response);
	}
	
}

public ActionForward GETREQLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	CertificateHandoverFB _fb = (CertificateHandoverFB) form;
	CertificateHandoverUTL.getReqListByCrNo(_fb, request);
 	return mapping.findForward("NEW");
}

public ActionForward SEARCHTYPE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	CertificateHandoverFB _fb = (CertificateHandoverFB) form;
	_fb.reset(mapping,request);	
	WebUTIL.refreshTransState(request);
	CertificateHandoverUTL.getSearchType(_fb, request);	
 	return mapping.findForward("NEW");
	//return this.NEW(mapping, form, request, response);
}

public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	CertificateHandoverFB fb = (CertificateHandoverFB) form;
	CertificateHandoverUTL.getReqListByCrNo(fb,request);
	return mapping.findForward("NEW");
}

public ActionForward HANDOVERTOBYPATWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	CertificateHandoverFB _fb = (CertificateHandoverFB) form;
	CertificateHandoverUTL.getHandOverToDetailPatWise(_fb, request);
 	return mapping.findForward("NEW");
}
public ActionForward POPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
{
	CertificateHandoverFB fb = (CertificateHandoverFB) form;
	CertificateHandoverUTL.setTemplateData( fb, request);
	return mapping.findForward("POPUP");
	
}
public ActionForward PRINT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
{
	CertificateHandoverFB fb = (CertificateHandoverFB) form;
	WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getTemplateHtmlCode());
	return mapping.findForward("POPUP");
}


}
