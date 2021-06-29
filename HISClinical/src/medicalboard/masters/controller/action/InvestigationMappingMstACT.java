package medicalboard.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import medicalboard.MedicalBoardConfig;
import medicalboard.masters.controller.fb.InvestigationMappingMstFB;
import medicalboard.masters.controller.utl.InvestigationMappingMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InvestigationMappingMstACT extends CSRFGardTokenAction{

	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		InvestigationMappingMstFB fb = (InvestigationMappingMstFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST);
		InvestigationMappingMstUTL.getEssential(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvestigationMappingMstFB fb = (InvestigationMappingMstFB) form;
		InvestigationMappingMstUTL.addList(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEDATA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvestigationMappingMstFB fb = (InvestigationMappingMstFB) form;
		InvestigationMappingMstUTL.removeListData(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		validateToken(request,response);
		InvestigationMappingMstFB fb = (InvestigationMappingMstFB) form;
		InvestigationMappingMstUTL.saveInvestigationMappintDtl(fb,request);
		return this.ADD(mapping, form, request, response) ;
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		InvestigationMappingMstFB _fb = (InvestigationMappingMstFB) form;
		InvestigationMappingMstUTL.getModifyDetail(_fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYADDLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		InvestigationMappingMstFB fb = (InvestigationMappingMstFB) form;
		InvestigationMappingMstUTL.addList(fb,request);
		fb.setHmode("MODIFY");
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYREMOVEDATA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		InvestigationMappingMstFB fb = (InvestigationMappingMstFB) form;
		InvestigationMappingMstUTL.removeListData(fb,request);
		fb.setHmode("MODIFY");
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		InvestigationMappingMstFB fb = (InvestigationMappingMstFB) form;
		InvestigationMappingMstUTL.updateInvestigationMappintDtl(fb,_request);
		fb.setHmode("MODIFY");
		//return mapping.findForward("MODIFY");
		return mapping.findForward("LIST");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		InvestigationMappingMstFB fb= (InvestigationMappingMstFB)form;
		InvestigationMappingMstUTL.getModifyDetail(fb, request);
		return mapping.findForward("MODIFY");
	}
		
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}


	
}
