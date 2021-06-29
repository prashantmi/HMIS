package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.master.controller.fb.DrugListItemMasterFB;
import opd.master.controller.util.DrugListItemMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DrugListItemMasterACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.ADD(mapping, form, _request, _response);
	}
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		DrugListItemMasterFB fb=(DrugListItemMasterFB)form;
		fb.reset(mapping, _request);
		WebUTIL.refreshTransState(_request);
		DrugListItemMstUTIL.setEssentials(fb, _request);
		return mapping.findForward("NEW");
	}
	public ActionForward GETDOSEDATA(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DrugListItemMasterFB fb=(DrugListItemMasterFB)form;
		DrugListItemMstUTIL.getDoseData(fb, _request);
		if(fb.getStatusFlag().equals(OpdConfig.STATUS_FLAG_FOR_MODIFY))
		{
			return mapping.findForward("MODIFY");
		}
		else
		{
			return mapping.findForward("NEW");
		}
		
	}
	
	public ActionForward ADDROW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DrugListItemMasterFB fb=(DrugListItemMasterFB)form;
		DrugListItemMstUTIL.addRow(fb, _request);
		if(fb.getStatusFlag().equals(OpdConfig.STATUS_FLAG_FOR_MODIFY))
		{
			return mapping.findForward("MODIFY");
		}
		else
		{
			return mapping.findForward("NEW");
		}
		
	}
	
	public ActionForward DELETEDRUGROW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DrugListItemMasterFB fb=(DrugListItemMasterFB)form;
		DrugListItemMstUTIL.deleteRow(fb, _request);
		if(fb.getStatusFlag().equals(OpdConfig.STATUS_FLAG_FOR_MODIFY))
		{
			return mapping.findForward("MODIFY");
		}
		else
		{
			return mapping.findForward("NEW");
		}
		
	}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{

		validateToken(_request,_response);
		DrugListItemMasterFB fb=(DrugListItemMasterFB)form;
		DrugListItemMstUTIL.save(fb, _request);
		return this.ADD(mapping, form, _request, _response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		DrugListItemMasterFB fb = (DrugListItemMasterFB) form;
		fb.setHmode("MODIFY");
		DrugListItemMstUTIL.getDataForModify(fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{

		validateToken(_request,_response);
		DrugListItemMasterFB fb = (DrugListItemMasterFB) form;
		fb.setHmode("MODIFY");
		DrugListItemMstUTIL.saveModify(fb, _request);
		return mapping.findForward("LIST");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DrugListItemMasterFB fb = (DrugListItemMasterFB) form;
		DrugListItemMstUTIL.getDataForModify(fb, _request);
		return mapping.findForward("MODIFY");
	}

	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
	
	
}
