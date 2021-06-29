package new_investigation.transactions.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.invReportHistoryFB;
import new_investigation.transactions.controller.fb.viewExternalInvFB;

import new_investigation.transactions.controller.utl.InvValueAuditUTIL;
import new_investigation.transactions.controller.utl.invReportHistoryUTL;
import new_investigation.transactions.controller.utl.viewExternalInvUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class invReportHistoryACT extends CSRFGardTokenAction {

	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
	return this.NEW(mapping,form,request,response);
	}

	
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception
	{
		generateToken(objRequest_p);
		invReportHistoryFB objFB = (invReportHistoryFB)objForm_p;
		HttpSession session=WebUTIL.getSession(objRequest_p);
		WebUTIL.refreshTransState(objRequest_p);
		ControllerUTIL.setSysdate(objRequest_p);
		objFB.setShowStatus("0");
		return objMapping_p.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		invReportHistoryFB fb=(invReportHistoryFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}

	
	public ActionForward SHOWREPORTDATA(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		invReportHistoryFB fb = (invReportHistoryFB) form;
		
		fb.setShowStatus("1");
          
		ControllerUTIL.setSysdate(request);
		
		invReportHistoryUTL.getEssential(fb, request);
		 
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SHOWREPORTDATAA(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		invReportHistoryFB fb = (invReportHistoryFB) form;
		
		fb.setShowStatus("1");
          
		ControllerUTIL.setSysdate(request);
		
		invReportHistoryUTL.getEssentiall(fb, request);
		 
		return mapping.findForward("NEW");
	}
	
	public ActionForward SHOWREPORTDATAALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		invReportHistoryFB fb = (invReportHistoryFB) form;
		
		fb.setShowStatus("2");
          
		ControllerUTIL.setSysdate(request);
		
		invReportHistoryUTL.getalldata(fb, request);
		 
		return mapping.findForward("NEW");
	}
	
	public ActionForward SHOWREPORTDATAALLINACTIVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		invReportHistoryFB fb = (invReportHistoryFB) form;
		
		fb.setShowStatus("3");
          
		ControllerUTIL.setSysdate(request);
		
		invReportHistoryUTL.getalldataInactive(fb, request,"0");
		 
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward RESUMESERVICE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		invReportHistoryFB fb = (invReportHistoryFB) form;
		
		//fb.setShowStatus("3");
          
		ControllerUTIL.setSysdate(request);
		
		invReportHistoryUTL.getalldataInactive(fb, request,"1");
		 
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SETDATAREFRANGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		invReportHistoryFB fb = (invReportHistoryFB) form;
		
		//fb.setShowStatus("3");
          
		ControllerUTIL.setSysdate(request);
		
		invReportHistoryUTL.getalldataInactive(fb, request,"2");
		 
		return mapping.findForward("NEW");
	}
	
	/*
	public ActionForward FORCEGENERATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		invReportHistoryFB fb = (invReportHistoryFB) form;
		
		//fb.setShowStatus("3");
          
		ControllerUTIL.setSysdate(request);
		
        boolean jobProcessContinue = true;

		PGTemplateProcessing pgtp = new PGTemplateProcessing();
		 while (jobProcessContinue) {
			 
			 try
			 {
			 jobProcessContinue=	pgtp.processingData();		 
			 }
			 catch(Exception e)
			 {
				e.printStackTrace();
			 }
			 
			 }
	
		
        boolean jobProcessContinuepdf = true;

		PGPDFProcessing ppp = new PGPDFProcessing();
		while (jobProcessContinuepdf) {
			
			try
			{
			jobProcessContinuepdf=ppp.processingData();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			}
		//invReportHistoryUTL.getalldataInactive(fb, request,"2");
		 
		return mapping.findForward("NEW");
	}*/
	
	
}
