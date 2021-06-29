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

public class MasterVerificationACT extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}

	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationFB fb = (MasterVerificationFB) form;
		fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
		MasterVerificationUTL.getModuleList(fb,request);
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward GETMASTERLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationFB fb = (MasterVerificationFB) form;
		MasterVerificationUTL.getMasterDetailList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SETOPTION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationFB fb = (MasterVerificationFB) form;
		fb.setCriteriaLabel("");
		MasterVerificationUTL.setOrderByOption(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDATA(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationFB fb = (MasterVerificationFB) form;
		MasterVerificationUTL.getMasterData(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationFB fb = (MasterVerificationFB) form;
		Status status=new Status();
		if(fb.getIsGrouped().equals("1"))
			status.add(Status.TRANSINPROCESS);
		else
			status.add(Status.LIST);
		WebUTIL.setStatus(request, status)	;
		//MasterVerificationUTL.getMasterData(fb, request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward GETCRITERIADATA(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationFB fb = (MasterVerificationFB) form;
		fb.setCriteriaLabel("");
		MasterVerificationUTL.setCriteriaOption(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CONVERTTOPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MasterVerificationFB fb = (MasterVerificationFB) form;
		MasterVerificationUTL.convertToPDF(fb, request,response);
		
		return null;
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("CANCEL");
	}

}
