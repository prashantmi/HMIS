package mrd.transaction.controller.action;



import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.EstimateRequestFB;
import mrd.transaction.controller.utl.EstimateRequestUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class EstimateRequestACTION extends CSRFGardTokenAction {
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		EstimateRequestFB fb=(EstimateRequestFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);//----????????????
		EstimateRequestUTL.getEstimateRequestEssentials(fb,request);
		fb.setIsDirectCall("DESK");
		return mapping.findForward("NEW");
	}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		EstimateRequestFB fb=(EstimateRequestFB)form;
		if(EstimateRequestUTL.saveEstimateCertificateReqDtl(fb,request))
		{
			
				return this.NEW(mapping, form, request, response);
			
		}
		else
			return mapping.findForward("NEW");
	}
	}
