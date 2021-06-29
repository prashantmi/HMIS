package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.DrugDoseMasterFB;
import opd.master.controller.util.DrugDoseMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DrugDoseMasterACTION extends CSRFGardTokenAction 
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DrugDoseMasterFB fb= (DrugDoseMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);
		DrugDoseMasterUTIL.getItemTypeName(fb, request);
		return mapping.findForward("NEW");
	}	
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		DrugDoseMasterFB fb= (DrugDoseMasterFB)form;
		if(DrugDoseMasterUTIL.saveDetail(fb, request))
		{
			fb.reset(mapping,request);
		}
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DrugDoseMasterFB fb= (DrugDoseMasterFB)form;
		WebUTIL.refreshTransState(request);
		//DrugDoseMasterUTIL.getEssentails(fb, request);
		DrugDoseMasterUTIL.getModifyDetail(fb, request);
		
		return mapping.findForward("MODIFY");
	}
		
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DrugDoseMasterFB fb = (DrugDoseMasterFB)form;
		if(DrugDoseMasterUTIL.saveModifyDetail(fb,request))
			return mapping.findForward("LIST");
		else
			return mapping.findForward("MODIFY");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DrugDoseMasterFB fb= (DrugDoseMasterFB)form;
		DrugDoseMasterUTIL.getModifyDetail(fb, request);
		return mapping.findForward("MODIFY");
	}
	
	
}
