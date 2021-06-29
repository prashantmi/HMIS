package registration.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import registration.controller.fb.HandoverToDeadBodyFB;
import registration.controller.util.HandoverToDeadBodyUTIL;

public class HandoverToDeadBodyACTION extends DispatchAction{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		HandoverToDeadBodyFB fb=(HandoverToDeadBodyFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		ControllerUTIL.setSysdate(request);
		HandoverToDeadBodyUTIL.getDeadPatientList(fb, request);
		//fb.setIsBodyHandoverToMortuary(Config.BODY_HANDOVER_MORTUARY);
		//fb.setNormalBodyHandover(Config.NORMAL_BODY_HANDOVER);
		//fb.setMlcBodyHandover(Config.MLC_BODY_HANDOVER);
		//fb.setIsClient(Config.CLIENT);
		//fb.setIsDelivery(RegistrationConfig.IS_DELIVERY_NO);
		//fb.setIsPregnant(RegistrationConfig.IS_PREGNATNT_NO);
		//PatientDeathDetailUTIL.getPatientdeathDetail(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		HandoverToDeadBodyFB fb=(HandoverToDeadBodyFB)form;
		HandoverToDeadBodyUTIL.getEssentialDetail(fb,request); 
		//fb.setHmode("GETPATDTL");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		HandoverToDeadBodyFB fb=(HandoverToDeadBodyFB)form;
		HandoverToDeadBodyUTIL.saveHandoverToDetail(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward PRINTCERT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		HandoverToDeadBodyFB fb=(HandoverToDeadBodyFB)form;
		HandoverToDeadBodyUTIL.getDeathDetailByCrNo(fb,request);
		return mapping.findForward("PRINT");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		HandoverToDeadBodyFB fb=(HandoverToDeadBodyFB)form;
		Status objStatus = new Status();
		objStatus.add(Status.LIST);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}	
	
	
}
