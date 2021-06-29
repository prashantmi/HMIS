package medicalboard.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.masters.controller.fb.OrganizationMstFB;
import medicalboard.masters.controller.utl.OrganizationMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class OrganizationMstACT extends CSRFGardTokenAction{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.ADD(mapping, form, _request, _response);
	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_	request);
		WebUTIL.refreshTransState(_request); 
		OrganizationMstFB fb=(OrganizationMstFB)form;
		fb.reset(mapping, _request);
		OrganizationMstUTL.getEssential(_request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OrganizationMstFB fb=(OrganizationMstFB)form;
		boolean hasFlag= OrganizationMstUTL.saveOrganizationDtl(fb,request);
		if(hasFlag){
			return this.ADD(mapping, form, request, response);
		}else{
		return mapping.findForward("NEW");}
	}	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		OrganizationMstFB fb=(OrganizationMstFB)form;
		WebUTIL.refreshTransState(request); 
		OrganizationMstUTL.getEssential(request);
		OrganizationMstUTL.getOrganizationDtl(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		OrganizationMstFB fb=(OrganizationMstFB)form;
		flag=OrganizationMstUTL.saveModOrganizationDtl(fb, _request);
		if(flag){
			return mapping.findForward("LIST");	
		}
		else{fb.setHmode("MODIFY");
			return mapping.findForward("NEW");}
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OrganizationMstFB fb=(OrganizationMstFB)form;
		WebUTIL.refreshTransState(request); 
		OrganizationMstUTL.getEssential(request);
		OrganizationMstUTL.getOrganizationDtl(fb, request);
		return mapping.findForward("NEW");
	}
	
	
}
