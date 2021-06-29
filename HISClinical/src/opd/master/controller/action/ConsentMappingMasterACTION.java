package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ConsentMappingMasterFB;
import opd.master.controller.util.ConsentMappingMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ConsentMappingMasterACTION extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{

		generateToken(request);
		ConsentMappingMasterFB fb= (ConsentMappingMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);
		ControllerUTIL.setSysdate(request);
		ConsentMappingMasterUTIL.getEssentails(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ConsentMappingMasterFB fb= (ConsentMappingMasterFB)form;
		ControllerUTIL.setSysdate(request);
		ConsentMappingMasterUTIL.getModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}	
	public ActionForward GETDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		ConsentMappingMasterFB fb= (ConsentMappingMasterFB)form;
		ConsentMappingMasterUTIL.getDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		validateToken(request,response);
		ConsentMappingMasterFB fb= (ConsentMappingMasterFB)form;
		ConsentMappingMasterUTIL.saveDetail(fb, request);
		fb.reset(mapping,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.LIST(mapping, form, request, response);
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ConsentMappingMasterFB fb = (ConsentMappingMasterFB)form;
		ConsentMappingMasterUTIL.saveModifyDetail(fb,request);
		return mapping.findForward("LIST");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ConsentMappingMasterFB fb= (ConsentMappingMasterFB)form;
		ConsentMappingMasterUTIL.getModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}

}
