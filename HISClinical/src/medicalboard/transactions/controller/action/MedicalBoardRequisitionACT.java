package medicalboard.transactions.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.transactions.controller.fb.MedicalBoardRequisitionFB;
import medicalboard.transactions.controller.utl.MedicalBoardRequisitionUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class MedicalBoardRequisitionACT extends CSRFGardTokenAction {

	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action  "NEW"
	 */
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}

	
	/**
	 * action mainly called at the initial loading of a page or when a form is reset
	 * refreshes Transaction State
	 * sets all  new registration essentials
	 * sets department options
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		generateToken(request);
		MedicalBoardRequisitionFB fb = (MedicalBoardRequisitionFB) form;
		fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
    	MedicalBoardRequisitionUTL.getPatientList(fb,request);
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalBoardRequisitionFB fb = (MedicalBoardRequisitionFB) form;
		MedicalBoardRequisitionUTL.getPatientForRequisitionByCrNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCERTIFICATETYPE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalBoardRequisitionFB fb = (MedicalBoardRequisitionFB) form;
		MedicalBoardRequisitionUTL.getCertificateTypeForRequisition(fb,request);
		return mapping.findForward("NEW");
	}
	
	
    public ActionForward GETCHECKLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalBoardRequisitionFB fb = (MedicalBoardRequisitionFB) form;
		MedicalBoardRequisitionUTL.getEssentialForRequisition(fb,request);
    	return mapping.findForward("NEW");	
	}
	
    public ActionForward GETSCHEDULELIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
    	
    	MedicalBoardRequisitionFB fb = (MedicalBoardRequisitionFB) form;
    	fb.setCurrentPage(1);
    	MedicalBoardRequisitionUTL.getScheduleList(fb,request);
    	return mapping.findForward("POPUP");	
    }
    
    public ActionForward GETCIDNOLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
    	
    	MedicalBoardRequisitionFB fb = (MedicalBoardRequisitionFB) form;
    	fb.setCurrentPage(1);
    	MedicalBoardRequisitionUTL.getCIDNoList(fb,request);
    	return mapping.findForward("CIDNO");	
    }

    public ActionForward GETORGDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalBoardRequisitionFB _fb = (MedicalBoardRequisitionFB) form;
    	MedicalBoardRequisitionUTL.getOrganizationDetail(_fb,request);
    	return mapping.findForward("NEW");	
	}
    
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		MedicalBoardRequisitionFB fb = (MedicalBoardRequisitionFB) form;
	    MedicalBoardRequisitionUTL.saveMedicalBoardRequisition(request, fb);		
		//fb.reset(mapping, request);
		return this.NEW(mapping,form,request,response);		
	}	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("CANCEL");		
	}	
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalBoardRequisitionFB fb = (MedicalBoardRequisitionFB) form;
		Status objStatus= new Status();
		
		//objStatus.add(Status.RECORDFOUND);
		objStatus.add(Status.LIST);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("CIDNO");
	}

 }
