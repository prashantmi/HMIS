package inpatient.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inpatient.masters.controller.fb.MethodMasterFB;
import inpatient.masters.controller.util.MethodMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MethodMstACT extends CSRFGardTokenAction
{
	//Getting Essential Data For Add Page 
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		MethodMasterFB fb=(MethodMasterFB)form;
		fb.reset(mapping, request);
		fb.setHmode("ADD");
		fb.setTempMode(fb.getHmode());
		fb.setMethodType(fb.getControls()[0]);
		MethodMasterUTL.getEssentialForMethodMst(fb,request);
		WebUTIL.refreshTransState(request);
		
		return mapping.findForward("NEW");
	}
	
	//Saving the Data
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MethodMasterFB fb=(MethodMasterFB)form;
		boolean exist=MethodMasterUTL.saveMethodMaster(fb,request);
		fb.setHmode(fb.getTempMode());
		if(exist)
		{
			return mapping.findForward("NEW");
		}
		else	
			return this.ADD(mapping, form, request, response);
	}	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		MethodMasterFB fb = (MethodMasterFB) form;
		WebUTIL.refreshTransState(request); 
		fb.setTempMode(fb.getHmode());
		MethodMasterUTL.getDataForModify(fb,request);
		MethodMasterUTL.getEssentialForMethodMst(fb,request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		MethodMasterFB _fb = (MethodMasterFB) form;
		flag=MethodMasterUTL.saveModMethodMaster(_fb, _request);
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
		MethodMasterFB fb = (MethodMasterFB) form;
		WebUTIL.refreshTransState(request);
		MethodMasterUTL.getDataForModify(fb,request);
		MethodMasterUTL.getEssentialForMethodMst(fb,request);
		
		return mapping.findForward("NEW");
	}

}
