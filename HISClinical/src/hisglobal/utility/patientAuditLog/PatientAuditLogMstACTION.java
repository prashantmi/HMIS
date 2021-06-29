package hisglobal.utility.patientAuditLog;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientAuditLogMstACTION extends DispatchAction {

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}

	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientAuditLogMstFB fb = (PatientAuditLogMstFB) form;
		fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		request.getSession().removeAttribute(Config.PATIENT_AUDIT_LOG_MST_VO_LIST);
		//PatientAuditLogMstUTL.getPatientAuditLogEssentials(fb,request);
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientAuditLogMstFB fb = (PatientAuditLogMstFB) form;
		//PatientAuditLogMstUTL.getData(fb, request);
		PatientAuditLogMstUTL.getPatientAuditLogEssentials(fb, request);
		request.getSession().removeAttribute(Config.AUDIT_HEADER_LIST);
		request.getSession().removeAttribute(Config.PATIENT_AUDIT_LOG_OPTIONS);
		fb.setAuditHeaderSize("0");
		fb.setIsDateWise("0");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETAUDITHEADER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientAuditLogMstFB fb = (PatientAuditLogMstFB) form;
		PatientAuditLogMstUTL.getAuditLogHeader(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDAUDIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientAuditLogMstFB fb = (PatientAuditLogMstFB) form;
		PatientAuditLogMstUTL.addAuditLogHeader(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEAUDIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientAuditLogMstFB fb = (PatientAuditLogMstFB) form;
		PatientAuditLogMstUTL.removeAuditLogHeader(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETAUDITDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientAuditLogMstFB fb = (PatientAuditLogMstFB) form;
		PatientAuditLogMstUTL.getData(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientAuditLogMstFB fb = (PatientAuditLogMstFB) form;
		Status status=new Status();
		status.add(Status.LIST);
		WebUTIL.setStatus(request, status)	;
		//PatientAuditLogMstUTL.getMasterData(fb, request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward CONVERTTOPDF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PatientAuditLogMstFB fb = (PatientAuditLogMstFB) form;
		PatientAuditLogMstUTL.convertToPDF(fb, request,response);
		
		return null;
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("CANCEL");
	}

}
