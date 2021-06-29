package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.OfflineMrdRecordReqDtlFB;
import mrd.transaction.controller.fb.IcdIndexingFB;
import mrd.transaction.controller.utl.IcdIndexingDtlUTL;
import mrd.transaction.controller.utl.OfflineMrdRecordReqDtlUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IcdIndexingACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		IcdIndexingFB fb=(IcdIndexingFB)form;
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward GOGETDATA(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		IcdIndexingFB fb=(IcdIndexingFB)form;
		if(fb.getAddmissionNo()!=null)
		{
		IcdIndexingDtlUTL.getEssentialForIcdIndexing(fb, request);
		OfflineMrdRecordReqDtlUTL.getIcdDtls(fb, request);
		}
		else{
			
			fb=(IcdIndexingFB)request.getSession().getAttribute(MrdConfig.LIST_MRD_FB);
			IcdIndexingDtlUTL.getEssentialForIcdIndexing(fb, request);
			OfflineMrdRecordReqDtlUTL.getIcdDtls(fb, request);
			fb.setDiagnosisName("");
		}
		if(!fb.getStatusMessage().equals("")){
			return mapping.findForward("NEW");
		}else{
		return mapping.findForward("GOGETDATA");
		}
	}
	
	
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		IcdIndexingFB fb=(IcdIndexingFB)form;
		IcdIndexingDtlUTL.saveIcdDtl(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		IcdIndexingFB fb=(IcdIndexingFB)form;
	//	OfflineMrdRecordReqDtlUTL.getEssentialForOfflineReq(fb, request); //Added by Vasu on 11.10.2018
		//request.getSession().removeAttribute(MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO);
		//fb.setIsRecordRequested(MrdConfig.NO);
		return mapping.findForward("GOGETDATA");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	
	//Added by Vasu on 7.March.2019
	public ActionForward DELETERECORD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//validateToken(request,response);
		IcdIndexingFB fb=(IcdIndexingFB)form;
		IcdIndexingDtlUTL.deleteIcdRecord(fb, request);
		IcdIndexingDtlUTL.getEssentialForIcdIndexing(fb, request);
		return mapping.findForward("GOGETDATA");
	}
}
