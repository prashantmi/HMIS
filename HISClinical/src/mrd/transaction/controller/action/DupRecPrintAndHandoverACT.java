/* 
## Copyright Information		: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Anant Patel 
 ## Module Name					: MRD
 ## Process/Database Object Name: Duplicate Record Printing And Handover process
 ## Purpose						: Duplicate Record Printing And Handover process
 ## Date of Creation			: 19 Jan 2015
 ## Modification Log			:				
 ##		Modify Date				:  
 ##		Reason	(CR/PRS)		:  
 ##		Modify By				: 

*/

package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.DuplicateRecordPrintReqFB;
import mrd.transaction.controller.utl.DupRecPrintAndHandoverUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DupRecPrintAndHandoverACT extends CSRFGardTokenAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DuplicateRecordPrintReqFB fb=(DuplicateRecordPrintReqFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		DupRecPrintAndHandoverUTL.getDupRecPrintAndHandoverList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DuplicateRecordPrintReqFB fb=(DuplicateRecordPrintReqFB)form;
		Status objStatus= new Status();		
		objStatus.add(Status.RECORDFOUND);
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		DuplicateRecordPrintReqFB fb=(DuplicateRecordPrintReqFB)form;
		//WebUTIL.refreshTransState(request);	
		//fb.reset(mapping, request);
		DupRecPrintAndHandoverUTL.getDupRecPrintAndHandoverDtl(fb,request);
		return mapping.findForward("GETDTL");
	}
	
	public  ActionForward FINALCANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("FINALCANCEL");
	}
	
	public  ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DuplicateRecordPrintReqFB fb=(DuplicateRecordPrintReqFB)form;
		if(DupRecPrintAndHandoverUTL.saveDuplicateCertificateIssueDtl(fb,request))
		{
			
				//return this.GETDTL(mapping, form, request, response);
				return this.NEW(mapping, form, request, response);
			
		}
		else
			//return mapping.findForward("GETDTL");
		    return mapping.findForward("NEW");
	}
	
	
	
	
	
}
