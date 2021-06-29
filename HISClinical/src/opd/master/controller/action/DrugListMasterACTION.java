package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.DrugListMasterFB;
import opd.master.controller.util.DrugListMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DrugListMasterACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.ADD(mapping, form, _request, _response);
	}
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		DrugListMasterFB fb=(DrugListMasterFB)form;
		fb.reset(mapping, _request);
		WebUTIL.refreshTransState(_request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DrugListMasterFB fb=(DrugListMasterFB)form;
		
		boolean exist=DrugListMasterUTIL.saveKeywordMstDetail(fb, request);
		if(exist)
		{
			fb.setHmode("ADD");
			return this.ADD(mapping, form, request, response);
		}
		else
		{
			generateToken(request);
			fb.setHmode("ADD");
			return mapping.findForward("NEW");
		}
			
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DrugListMasterFB fb = (DrugListMasterFB) form;
		fb.setHmode("MODIFY");
		DrugListMasterUTIL.getDataForModify(fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		DrugListMasterFB _fb = (DrugListMasterFB) form;
		flag=DrugListMasterUTIL.saveModDrugListMaster(_fb, _request);
		if(flag){
			return mapping.findForward("LIST");	
		}
		else{_fb.setHmode("MODIFY");
			return mapping.findForward("MODIFY");}
		
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DrugListMasterFB _fb = (DrugListMasterFB) form;
		WebUTIL.refreshTransState(_request); 
		DrugListMasterUTIL.getDataForModify(_fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
}
