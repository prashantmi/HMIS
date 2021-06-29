package inpatient.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.ANCDetailFB;
import inpatient.transaction.controller.utl.ANCDetailUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ANCDetailACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		ANCDetailFB fb = (ANCDetailFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		ANCDetailUTL.setEssentials(fb, request);
		if(fb.getAncDetailGetFlag().equals(OpdConfig.YES))
			return mapping.findForward("FOLLOW");
		else
			return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ANCDetailFB fb = (ANCDetailFB) form;
		if(ANCDetailUTL.saveANCDetail(fb, request))
		{
			fb.reset(mapping, request);
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			ANCDetailUTL.setEssentials(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.TRANSINPROCESS,"ANC Detail Saved Successfully","");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		if(fb.getAncDetailGetFlag().equals(OpdConfig.YES))
			return mapping.findForward("FOLLOW");
		else
			return mapping.findForward("NEW");
	}

	public ActionForward ADDMACRO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ANCDetailFB fb = (ANCDetailFB) form;
		ANCDetailUTL.getSetMacros(fb, request);		
		return mapping.findForward("MACROPOPUP");
	}

	public ActionForward SHOWMACRO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("MACROPOPUP");
	}
}
