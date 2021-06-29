package new_investigation.transactions.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.invAntibioticProcessFB;
import new_investigation.transactions.controller.fb.invRequisitionFormFB;
import new_investigation.transactions.controller.utl.invAntibioticProcessUTL;
import new_investigation.transactions.controller.utl.invRequisitionFormUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class invRequisitionFormACT extends CSRFGardTokenAction {


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
		//invAntibioticProcessFB objFB = (invAntibioticProcessFB)objForm_p;
		HttpSession session=WebUTIL.getSession(objRequest_p);
	//	WebUTIL.refreshTransState(objRequest_p);
		//DynamicDeskUTIL.refreshSessionState(objRequest_p);
		//invAntibioticProcessUTL.getEssential(objFB, objRequest_p);
		return objMapping_p.findForward("NEW");
	}
	
	
	public ActionForward EXISTINGREQUISITIONFORM(ActionMapping objMapping_p,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		invRequisitionFormFB  fb = (invRequisitionFormFB) form;
		invRequisitionFormUTIL util=new invRequisitionFormUTIL();
		util.showMandatory(request, fb);
		//return objMapping_p.findForward("GETREQUISITIONFORM");
		return objMapping_p.findForward("NEW");
	}
	
public ActionForward EXISTINGREQUISITIONFORMDATA(ActionMapping objMapping_p,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		invRequisitionFormFB  fb = (invRequisitionFormFB) form;
		invRequisitionFormUTIL util=new invRequisitionFormUTIL();
		util.showSampleCollection(request, fb);
		//return objMapping_p.findForward("GETREQUISITIONFORM");
		return objMapping_p.findForward("NEW");
	}


}
