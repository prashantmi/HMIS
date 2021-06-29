package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.fb.ExternalDeceasedAcceptanceFB;
import mortuary.transaction.controller.utl.ExternalDeceasedAcceptanceUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ExternalDeceasedAcceptanceACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	{
		generateToken(request);
		ExternalDeceasedAcceptanceFB fb=(ExternalDeceasedAcceptanceFB)form;
		WebUTIL.refreshTransState(request);
		fb.setDeceasedFrom("");
		return mapping.findForward("NEW");
	}	
	
	public ActionForward DETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	{   
		generateToken(request);
		ExternalDeceasedAcceptanceFB fb=(ExternalDeceasedAcceptanceFB)form;
		ExternalDeceasedAcceptanceUTL.getEssentialForExternalBodyAcceptance(fb,request);
		fb.reset(mapping, request);
		fb.setIsActualDob("0");
		fb.setDutyOfficeFlag(MortuaryConfig.DUTY_OFFICER_IS_OTHER);
		fb.setStorageFlag(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER);
		fb.setIsAssociated(MortuaryConfig.YES);
		fb.setIsClaimed(MortuaryConfig.YES);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETRACK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ExternalDeceasedAcceptanceFB fb=(ExternalDeceasedAcceptanceFB)form;
		ExternalDeceasedAcceptanceUTL.getRackBasedOnChamber(fb,request);
		return mapping.findForward("NEW");
	}	
	
	public ActionForward SAVEOTHHOSP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		validateToken(request,response);
		ExternalDeceasedAcceptanceFB fb=(ExternalDeceasedAcceptanceFB)form;
		ExternalDeceasedAcceptanceUTL.saveExternalDeceasedAcceptance(fb,request);
		WebUTIL.refreshTransState(request);
		fb.setDeceasedFrom("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVEONSPOT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		validateToken(request,response);
		ExternalDeceasedAcceptanceFB fb=(ExternalDeceasedAcceptanceFB)form;
		ExternalDeceasedAcceptanceUTL.saveOnSpotDeceasedAcceptance(fb,request);
		WebUTIL.refreshTransState(request);
		fb.setDeceasedFrom("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVEOTHPLC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		validateToken(request,response);
		ExternalDeceasedAcceptanceFB fb=(ExternalDeceasedAcceptanceFB)form;
		ExternalDeceasedAcceptanceUTL.saveExternalDeceasedOtherPlaceAcceptance(fb,request);
		WebUTIL.refreshTransState(request);
		fb.setDeceasedFrom("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
