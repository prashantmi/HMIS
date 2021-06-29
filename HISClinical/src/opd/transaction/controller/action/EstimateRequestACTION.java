/*Copyright Information			: C-DAC, Noida  
	 ## Project Name				: NIMS
	 ## Name of Developer		 	: Amit Garg 	
	 ## Module Name					: MRD
	 ## Process/Database Object Name:Estimate Certificate  Request
	 ## Purpose						:Certificate Issue Request
	 ## Date of Creation			: 19-Nov-2014
	
 	 */

package opd.transaction.controller.action;



import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.EstimateRequestFB;
import opd.transaction.controller.util.EstimateRequestUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EstimateRequestACTION extends CSRFGardTokenAction {
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		EstimateRequestFB fb=(EstimateRequestFB)form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
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
	
	public ActionForward POPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		EstimateRequestFB fb=(EstimateRequestFB)form;
		EstimateRequestUTL.getTariffsList(fb,request);
		return mapping.findForward("POPUP");
	}
	
	
}
