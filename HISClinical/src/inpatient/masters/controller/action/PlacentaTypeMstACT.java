package inpatient.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inpatient.masters.controller.fb.PlacentaTypeMasterFB;
import inpatient.masters.controller.util.PlacentaTypeMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PlacentaTypeMstACT extends CSRFGardTokenAction
{
	//Getting Essential Data For Add Page 
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		PlacentaTypeMasterFB fb=(PlacentaTypeMasterFB)form;
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
		PlacentaTypeMasterFB fb=(PlacentaTypeMasterFB)form;
		boolean exist=PlacentaTypeMstUTL.savePlacentaTypeMaster(fb,request);
		if(exist)
		{
			return mapping.findForward("NEW");
		}
		else	
			return this.ADD(mapping, form, request, response);
	}	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		PlacentaTypeMasterFB fb = (PlacentaTypeMasterFB) form;
		WebUTIL.refreshTransState(request); 
		fb.setTempMode(fb.getHmode());
		PlacentaTypeMstUTL.getDataForModify(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		PlacentaTypeMasterFB _fb = (PlacentaTypeMasterFB) form;
		flag=PlacentaTypeMstUTL.saveModDeliveryPlaceMaster(_fb, _request);
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
		PlacentaTypeMasterFB fb = (PlacentaTypeMasterFB) form;
		WebUTIL.refreshTransState(request);
		PlacentaTypeMstUTL.getDataForModify(fb,request);
		return mapping.findForward("NEW");
	}

}
