package inpatient.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inpatient.masters.controller.fb.AnomalyTypeMasterFB;
import inpatient.masters.controller.fb.IntakeOutputParaMasterFB;
import inpatient.masters.controller.util.AnomalyTypeMstUTL;
import inpatient.masters.controller.util.IntakeOutputParaMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class intakeOutputParaMstACT extends CSRFGardTokenAction
{
	//Getting Essential Data For Add Page 
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		IntakeOutputParaMasterFB fb=(IntakeOutputParaMasterFB)form;
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
		IntakeOutputParaMasterFB fb=(IntakeOutputParaMasterFB)form;
		boolean exist=IntakeOutputParaMstUTL.saveIntakeOutputParaMaster(fb,request);
		if(exist)
		{
			return mapping.findForward("NEW");
		}
		else	
			return this.ADD(mapping, form, request, response);
	}	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		IntakeOutputParaMasterFB fb = (IntakeOutputParaMasterFB) form;
		WebUTIL.refreshTransState(request); 
		fb.setTempMode(fb.getHmode());
		IntakeOutputParaMstUTL.getDataForModify(fb,request);
		
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		IntakeOutputParaMasterFB _fb = (IntakeOutputParaMasterFB) form;
		flag=IntakeOutputParaMstUTL.saveModInOutParaMaster(_fb, _request);
		if(flag){
			return mapping.findForward("LIST");	
		}
		else{_fb.setHmode("MODIFY");
			return mapping.findForward("MODIFY");}
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IntakeOutputParaMasterFB fb = (IntakeOutputParaMasterFB) form;
		WebUTIL.refreshTransState(request);
		IntakeOutputParaMstUTL.getDataForModify(fb,request);
		return mapping.findForward("VIEW");
	}

}
