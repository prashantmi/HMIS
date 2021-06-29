package medicalboard.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.masters.controller.fb.ReferMappingMstFB;
import medicalboard.masters.controller.utl.ReferMappingMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class ReferMappingMstACT extends CSRFGardTokenAction{

	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		ReferMappingMstFB fb = (ReferMappingMstFB) form;
		WebUTIL.refreshTransState(request);
		ReferMappingMstUTL.getEssential(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ReferMappingMstFB fb = (ReferMappingMstFB) form;
		ReferMappingMstUTL.addDepartmentUnitList(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVELIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ReferMappingMstFB fb = (ReferMappingMstFB) form;
		ReferMappingMstUTL.removeDepartmentUnitList(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ReferMappingMstUTL.saveReferMappingData(request);
		ReferMappingMstFB fb = (ReferMappingMstFB) form;
		fb.setMode("SAVE");
		return this.ADD(mapping, form, request, response) ;
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		ReferMappingMstFB _fb = (ReferMappingMstFB) form;
		WebUTIL.refreshTransState(_request);
		_fb.setMode("MODIFY");
		ReferMappingMstUTL.getReferMappingDetail(_fb, _request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
//		ReferMappingMstFB fb = (ReferMappingMstFB) form0
		validateToken(_request,response);;
		boolean hasFlag=ReferMappingMstUTL.saveModReferMappingData(_request);
		if(hasFlag){
			return mapping.findForward("LIST");
		}else{
		return mapping.findForward("NEW");}
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		ReferMappingMstFB _fb = (ReferMappingMstFB) form;
		WebUTIL.refreshTransState(_request);
		_fb.setMode("VIEW");
		ReferMappingMstUTL.getReferMappingDetail(_fb, _request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
	
	
}
