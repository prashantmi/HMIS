package inpatient.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inpatient.masters.controller.fb.AbortionMethodMasterFB;
import inpatient.masters.controller.util.AbortionMethodMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AbortionMethodMstACT extends CSRFGardTokenAction
{
	//Getting Essential Data For Add Page 
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		AbortionMethodMasterFB fb=(AbortionMethodMasterFB)form;
		fb.reset(mapping, request);
		fb.setHmode("ADD");
		fb.setTempMode(fb.getHmode());
		if(fb.getAbortionTypeId()==null || fb.getAbortionTypeId().equals(""))
			fb.setAbortionTypeId(fb.getControls()[0]);
		AbortionMethodMasterUTL.getAbortionTypeName(fb, request);
		WebUTIL.refreshTransState(request);
		
		return mapping.findForward("NEW");
	}
	
	//Saving the Data
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		AbortionMethodMasterFB fb=(AbortionMethodMasterFB)form;
		boolean exist=AbortionMethodMasterUTL.saveAbortionMethodMaster(fb,request);
		if(exist)
		{
			return mapping.findForward("NEW");
		}
		else	
			return this.ADD(mapping, form, request, response);
	}	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		AbortionMethodMasterFB fb = (AbortionMethodMasterFB) form;
		WebUTIL.refreshTransState(request); 
		fb.setTempMode(fb.getHmode());
		AbortionMethodMasterUTL.getDataForModify(fb,request);
		AbortionMethodMasterUTL.getAbortionTypeName(fb, request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		AbortionMethodMasterFB _fb = (AbortionMethodMasterFB) form;
		flag=AbortionMethodMasterUTL.saveModAbortionMethodMaster(_fb, _request);
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
		AbortionMethodMasterFB fb = (AbortionMethodMasterFB) form;
		WebUTIL.refreshTransState(request);
		AbortionMethodMasterUTL.getDataForModify(fb,request);
		AbortionMethodMasterUTL.getAbortionTypeName(fb, request);
		return mapping.findForward("NEW");
	}

}
