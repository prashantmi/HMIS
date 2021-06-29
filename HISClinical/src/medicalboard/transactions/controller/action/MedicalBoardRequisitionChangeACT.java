package medicalboard.transactions.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.fb.MedicalBoardRequisitionChangeFB;
import medicalboard.transactions.controller.utl.MedicalBoardRequisitionChangeUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MedicalBoardRequisitionChangeACT extends CSRFGardTokenAction {


	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}

	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		

		generateToken(request);
		MedicalBoardRequisitionChangeFB fb = (MedicalBoardRequisitionChangeFB) form;
		fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
		Status status=new Status();
		status.add(Status.NEW);
		WebUTIL.setStatus(request, status);
		ControllerUTIL.setSysdate(request);
    	return mapping.findForward("NEW");
	}
	
	public ActionForward SETREQUESTBYORG(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalBoardRequisitionChangeFB fb = (MedicalBoardRequisitionChangeFB) form;
		WebUTIL.refreshTransState(request);
		MedicalBoardRequisitionChangeUTL.getOranisationsList(fb,request);
		return mapping.findForward("NEW");
	}
	
	/**
	 * get All requisition which has not been yet performed
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward SETREQUESTBYHOSPITAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalBoardRequisitionChangeFB fb = (MedicalBoardRequisitionChangeFB) form;
		request.getSession().removeAttribute(MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST);
		//WebUTIL.refreshTransState(request);
		MedicalBoardRequisitionChangeUTL.getAllMbRequisitions(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalBoardRequisitionChangeFB fb = (MedicalBoardRequisitionChangeFB) form;
		MedicalBoardRequisitionChangeUTL.getPatientMbRequisitionsByCrNo(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETREQUISITIONDTLBYORG(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalBoardRequisitionChangeFB fb = (MedicalBoardRequisitionChangeFB) form;
		MedicalBoardRequisitionChangeUTL.getMbRequisitionsByOrg(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETREQUISITIONBYDATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalBoardRequisitionChangeFB fb = (MedicalBoardRequisitionChangeFB) form;
		MedicalBoardRequisitionChangeUTL.getMbRequisitionsByDate(fb,request);
		return mapping.findForward("NEW");
	}
	
	
    public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	validateToken(request,response);

    	MedicalBoardRequisitionChangeFB fb = (MedicalBoardRequisitionChangeFB) form;
	    MedicalBoardRequisitionChangeUTL.saveMedicalBoardRequisitionChange(request, fb);		
		//fb.reset(mapping, request);
		return this.NEW(mapping,form,request,response);		
	}	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("CANCEL");		
	}	
	

 }
