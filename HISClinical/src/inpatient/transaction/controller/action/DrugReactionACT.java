package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.DrugReactionFB;
import inpatient.transaction.controller.utl.DrugReactionUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DrugReactionACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		DrugReactionFB fb = (DrugReactionFB) form;
		fb.reset(mapping, request);
		fb.setSelectedDrug("");
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.setViewTreatAdminChoice(InpatientConfig.DATEWISE_TREAT_INFO);
		DrugReactionUTL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DRUGGIVEN(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugReactionFB fb=(DrugReactionFB)form;
		DrugReactionUTL.getDrugInfo(fb,request);
		fb.reset(mapping, request);
		fb.setSelectedDrug("");
		return mapping.findForward("NEW");	
	}
	
	public ActionForward GETREACTIONDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugReactionFB fb=(DrugReactionFB)form;
		DrugReactionUTL.getSelectedDrugInfo(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");	
	}
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DrugReactionFB fb=(DrugReactionFB)form;
		DrugReactionUTL.saveDrugReactionDtl(fb, request);
		fb.reset(mapping, request);
		fb.setSelectedDrug("");
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward SELECTEDDRUGDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//DrugReactionFB fb=(DrugReactionFB)form;
		return mapping.findForward("SELECTEDDRUGDETAIL");	
	}
	
	public ActionForward PREVREACTIONDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DrugReactionFB fb=(DrugReactionFB)form;
		DrugReactionUTL.getDrugReactionDetail(fb, request);
		return mapping.findForward("PREVREACTIONDETAIL");	
	}
	
	
	
}
