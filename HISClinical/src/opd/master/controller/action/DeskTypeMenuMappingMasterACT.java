package opd.master.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.DeskTypeMenuMappingMasterFB;
import opd.master.controller.util.DeskTypeMenuMappingMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeskTypeMenuMappingMasterACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DeskTypeMenuMappingMasterFB fb= (DeskTypeMenuMappingMasterFB)form;
		WebUTIL.refreshTransState(request);
		//fb.reset(mapping,request);
		DeskTypeMenuMappingMstUTIL.getEssentails(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeskTypeMenuMappingMasterFB fb=(DeskTypeMenuMappingMasterFB)form;
		if(DeskTypeMenuMappingMstUTIL.saveDetail(fb,request))
		{
			WebUTIL.refreshTransState(request);
			DeskTypeMenuMappingMstUTIL.getEssentails(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW,"Record Saved Successfully","");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
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
		DeskTypeMenuMappingMasterFB fb= (DeskTypeMenuMappingMasterFB)form;
		DeskTypeMenuMappingMstUTIL.getModifyDetail(fb, request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeskTypeMenuMappingMasterFB fb = (DeskTypeMenuMappingMasterFB)form;
		if(DeskTypeMenuMappingMstUTIL.saveModifyDetail(fb,request))
			return mapping.findForward("LIST");
		else
			return mapping.findForward("MODIFY");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeskTypeMenuMappingMasterFB fb= (DeskTypeMenuMappingMasterFB)form;
		DeskTypeMenuMappingMstUTIL.getModifyDetail(fb, request);
		return mapping.findForward("MODIFY");
	}
	
}
