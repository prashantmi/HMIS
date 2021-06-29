package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.UnitDrugMasterFB;
import opd.master.controller.util.UnitDrugMasterUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class UnitDrugMasterACTION extends CSRFGardTokenAction{
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		UnitDrugMasterFB fb=(UnitDrugMasterFB)form;
		UnitDrugMasterUtil.getUnitDrugEssential(fb, request);
		fb.reset(mapping, request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitDrugMasterFB fb=(UnitDrugMasterFB)form;
		UnitDrugMasterUtil.saveUnitDrugList(fb, request);
		UnitDrugMasterUtil.getUnitDrugEssential(fb, request);
		fb.reset(mapping, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitDrugMasterFB fb=(UnitDrugMasterFB)form;
		UnitDrugMasterUtil.getUnitDrugLisyForModify(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitDrugMasterFB fb=(UnitDrugMasterFB)form;
		UnitDrugMasterUtil.modifySaveUnitDrugDetail(fb,request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("LIST"); 
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitDrugMasterFB fb=(UnitDrugMasterFB)form;
		UnitDrugMasterUtil.getUnitDrugLisyForModify(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
}
