package mortuary.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.MortuaryConfig;
import mortuary.masters.controller.hlp.IncisionTypeMasterFB;
import mortuary.masters.controller.hlp.IncisionTypeMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IncisionTypeMasterACT extends CSRFGardTokenAction
{
	//Getting Essential Data For Add Page 
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		IncisionTypeMasterFB fb=(IncisionTypeMasterFB)form;
		fb.reset(mapping, request);
		fb.setHmode("ADD");
		fb.setTempMode(fb.getHmode());
		WebUTIL.refreshTransState(request);
		return mapping.findForward("NEW");
	}
	
	//Saving the Data
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		IncisionTypeMasterFB fb=(IncisionTypeMasterFB)form;
		boolean exist=IncisionTypeMasterUTL.saveIncisionTypeMaster(fb,request);
		if(exist)
		{
			fb.setHmode(fb.getTempMode());
			return mapping.findForward("NEW");
		}
		else	
			return this.ADD(mapping, form, request, response);
	}	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		generateToken(request);
		IncisionTypeMasterFB fb = (IncisionTypeMasterFB) form;
		WebUTIL.refreshTransState(request); 
		IncisionTypeMasterUTL.getDataForModify(fb,request);
		fb.setHmode("MODIFY");
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		IncisionTypeMasterFB _fb = (IncisionTypeMasterFB) form;
		flag=IncisionTypeMasterUTL.saveModIncisionTypeMaster(_fb, _request);
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
		IncisionTypeMasterFB fb = (IncisionTypeMasterFB) form;
		WebUTIL.refreshTransState(request);
		IncisionTypeMasterUTL.getDataForModify(fb,request);
		fb.setHmode("VIEW");
		return mapping.findForward("NEW");
	}

}
