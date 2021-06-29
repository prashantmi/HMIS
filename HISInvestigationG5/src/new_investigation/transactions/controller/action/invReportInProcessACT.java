package new_investigation.transactions.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.InvResultReportPrintingFB;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.controller.fb.invReportInProcessFB;
import new_investigation.transactions.controller.utl.InvResultReportPrintingUTIL;
import new_investigation.transactions.controller.utl.invReportInProcessUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class invReportInProcessACT extends CSRFGardTokenAction {

	
	
	
	
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception
	{
		generateToken(objRequest_p);
		return this.NEW(objMapping_p, objForm_p, objRequest_p, objResponse_p);
		
	}
	
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception
	{
		generateToken(objRequest_p);
		invReportInProcessFB objFB = (invReportInProcessFB)objForm_p;
		HttpSession session=WebUTIL.getSession(objRequest_p);
		WebUTIL.refreshTransState(objRequest_p);
		//DynamicDeskUTIL.refreshSessionState(objRequest_p);
		
		invReportInProcessUTL.getEssential( objRequest_p);
		
		return objMapping_p.findForward("NEW");
	}
	
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("INVREPORTINPROCESSACT: GETDETAILS  ");
        
		invReportInProcessFB fb=(invReportInProcessFB)form;
		fb.setShowStatus("0");
          
		ControllerUTIL.setSysdate(request);
	      	 
		invReportInProcessUTL.setResultReportPrintingEssentials(fb,request);
		 
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
		 invReportInProcessFB fb=(invReportInProcessFB)form;
		 
		 
		invReportInProcessUTL.saveInJobWorkOrder(fb, request);
		
		return mapping.findForward("NEW");
	 } 
	
	
	
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		invReportInProcessFB fb=(invReportInProcessFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	
public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
		    invReportInProcessFB fb=(invReportInProcessFB)form;
			Status  objStatus=new Status();
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(request,objStatus);
			return mapping.findForward("CANCEL");
		}		
	
	
	
	
	
	
	
	
}
