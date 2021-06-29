package inpatient.transaction.controller.action;

import hisglobal.Status;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.BloodTransfusionFB;
import inpatient.transaction.controller.utl.BloodTransfusionDtlUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BloodTransfusionACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		BloodTransfusionFB fb = (BloodTransfusionFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		BloodTransfusionDtlUTL.setEssentials(fb, request);
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		BloodTransfusionFB fb = (BloodTransfusionFB) form;
		Status status= new Status();
		
		if(BloodTransfusionDtlUTL.saveBloodTransfusionDtl(fb,request))
		{
			fb.reset(mapping, request);
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			BloodTransfusionDtlUTL.setEssentials(fb, request);
			
			status.add(Status.NEW,"Blood Transfused Successfully","");
			request.setAttribute(Config.STATUS_OBJECT, status);
		}
		
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward BAGDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//BloodTransfusionFB fb = (BloodTransfusionFB) form;
		//BloodTransfusionDtlUTL.saveBloodTransfusionDtl(fb,request);
		return mapping.findForward("BAGDETAIL");
	}
	
	public ActionForward GETTRANSFUSIONDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		BloodTransfusionFB fb= (BloodTransfusionFB) form;
		BloodTransfusionDtlUTL.getExtPatTransReactionEssential(fb,request);
		return mapping.findForward("GETTRANSFUSIONDTL");
	}
	
	public ActionForward SAVETRANSFUSIONDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		BloodTransfusionFB fb = (BloodTransfusionFB) form;
		BloodTransfusionDtlUTL.savePatTransReactionDtl(fb,request);
		return mapping.findForward("SAVETRANSFUSIONDTL");
	}
	
}
