package inpatient.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.servlets.ServletsUtilityConfig;
import inpatient.transaction.controller.fb.ANCNeonatalDetailFB;
import inpatient.transaction.controller.utl.ANCNeonatalDetailUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ANCNeonatalDetailACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		ANCNeonatalDetailFB fb = (ANCNeonatalDetailFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		ANCNeonatalDetailUTL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GENERATEBIRTHCERT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ANCNeonatalDetailFB fb=(ANCNeonatalDetailFB)form;
		ANCNeonatalDetailUTL.getBirthDetailOfNeoNatal(fb,request);
		return mapping.findForward("BIRTHCERT");
	}

	public ActionForward PRINTBIRTHCERTIFICATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		ANCNeonatalDetailFB fb= (ANCNeonatalDetailFB)form;
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getHtmlCertificateData());
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());
		return mapping.findForward("BIRTHCERT");
	}	

	public ActionForward GETNEONATALDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ANCNeonatalDetailFB fb = (ANCNeonatalDetailFB) form;
		ANCNeonatalDetailUTL.getNewNatalDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDAPGAR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ANCNeonatalDetailFB fb = (ANCNeonatalDetailFB) form;
		ANCNeonatalDetailUTL.addMoreApgarDetail(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ANCNeonatalDetailFB fb = (ANCNeonatalDetailFB) form;
		if(ANCNeonatalDetailUTL.saveANCNewnatalDetail(fb, request))
		{
			fb.reset(mapping, request);
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			ANCNeonatalDetailUTL.setEssentials(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.LIST,"Neonatal Detail Saved Successfully","");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}
	
	public ActionForward APGARDTLPOPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ANCNeonatalDetailFB fb = (ANCNeonatalDetailFB) form;
		ANCNeonatalDetailUTL.getSetApgarDtlForPopup(fb, request);
		return mapping.findForward("APGARDTL");
	}	
}
