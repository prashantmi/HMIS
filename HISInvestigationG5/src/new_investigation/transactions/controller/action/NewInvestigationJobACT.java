package new_investigation.transactions.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.transactions.controller.fb.NewInvestigationFB;
import new_investigation.transactions.controller.utl.InvValueAuditUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class NewInvestigationJobACT extends CSRFGardTokenAction {

	
	
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
		NewInvestigationFB objFB = (NewInvestigationFB)objForm_p;
		HttpSession session=WebUTIL.getSession(objRequest_p);
		WebUTIL.refreshTransState(objRequest_p);
		//DynamicDeskUTIL.refreshSessionState(objRequest_p);
		//InvValueAuditUTIL.getEssential(objFB, objRequest_p);
		return objMapping_p.findForward("NEW");
	}
	
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		NewInvestigationFB fb = (NewInvestigationFB) form;
		//fb.setShowStatus("0");
          
		ControllerUTIL.setSysdate(request);
	      	 
		//InvValueAuditUTIL.getTestName(fb,request);
		 
		return mapping.findForward("NEW");
	}
	

}
