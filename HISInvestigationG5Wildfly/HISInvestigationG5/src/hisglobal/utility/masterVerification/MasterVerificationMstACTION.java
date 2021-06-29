package hisglobal.utility.masterVerification;

import java.io.IOException;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HTMLToPDFUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MasterVerificationMstACTION extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}

	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
		MasterVerificationMstUTL.getModuleList(fb,request);
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
		MasterVerificationMstUTL.getModuleList(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETMASTERLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		MasterVerificationMstUTL.getMasterList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCOLUMN(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		fb.setCriteriaLabel("");
		MasterVerificationMstUTL.getMasterColumnList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDCOLUMN(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		MasterVerificationMstUTL.addColumn(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDWHERECONDITION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		MasterVerificationMstUTL.addWhereCondition(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		MasterVerificationMstUTL.save(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		WebUTIL.refreshTransState(request);
		MasterVerificationMstUTL.getModuleList(fb, request);
		MasterVerificationMstUTL.getMasterVerficationData(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		
		if(MasterVerificationMstUTL.modify(fb, request))
			return mapping.findForward("MODIFY");
		else
			return mapping.findForward("NEW");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		WebUTIL.refreshTransState(request);
		MasterVerificationMstUTL.getModuleList(fb, request);
		MasterVerificationMstUTL.getMasterVerficationData(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward TESTQUERY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationMstFB fb = (MasterVerificationMstFB) form;
		MasterVerificationMstUTL.testQuery(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("CANCEL");
	}

}
