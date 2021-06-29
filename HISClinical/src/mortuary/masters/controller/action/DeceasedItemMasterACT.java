package mortuary.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.masters.controller.hlp.DeceasedItemMasterFB;
import mortuary.masters.controller.hlp.DeceasedItemMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeceasedItemMasterACT extends CSRFGardTokenAction
{
	//Getting Essential Data For Add Page 
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DeceasedItemMasterFB fb=(DeceasedItemMasterFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		fb.setTempMode(fb.getHmode());
		DeceasedItemMasterUTL.getItemTypeList(fb, request);
		fb.setHmode("ADD");
		
		
		return mapping.findForward("NEW");
	}
	
	//Saving the Data
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeceasedItemMasterFB fb=(DeceasedItemMasterFB)form;
		
		boolean exist=DeceasedItemMasterUTL.saveDeceasedItemMaster(fb,request);
		if(exist)
		{
			return mapping.findForward("NEW");
		}
		else	
			return this.ADD(mapping, form, request, response);
	}	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		generateToken(request);
		DeceasedItemMasterFB fb = (DeceasedItemMasterFB) form;
		WebUTIL.refreshTransState(request); 
		fb.setTempMode(fb.getHmode());
		DeceasedItemMasterUTL.getDataForModify(fb,request);
		DeceasedItemMasterUTL.getItemTypeList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		DeceasedItemMasterFB _fb = (DeceasedItemMasterFB) form;
		flag=DeceasedItemMasterUTL.saveModDeceasedItemMaster(_fb, _request);
		DeceasedItemMasterUTL.getItemTypeList(_fb, _request);
		if(flag){
			return mapping.findForward("LIST");	
		}
		else{_fb.setHmode("MODIFY");
			return mapping.findForward("NEW");}
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeceasedItemMasterFB fb = (DeceasedItemMasterFB) form;
		WebUTIL.refreshTransState(request);
		DeceasedItemMasterUTL.getDataForModify(fb,request);
		DeceasedItemMasterUTL.getItemTypeList(fb, request);
		return mapping.findForward("NEW");
	}

}
