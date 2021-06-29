package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.UnitDrugListMasterFB;
import opd.master.controller.util.UnitDrugListMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class UnitDrugListMasterACTION extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		UnitDrugListMasterFB fb=(UnitDrugListMasterFB)form;
		UnitDrugListMasterUTIL.getUnitDrugListEssential(fb, request);
		fb.reset(mapping, request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitDrugListMasterFB fb=(UnitDrugListMasterFB)form;
		UnitDrugListMasterUTIL.saveUnitDrugList(fb, request);
		UnitDrugListMasterUTIL.getUnitDrugListEssential(fb, request);
		fb.reset(mapping, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitDrugListMasterFB fb=(UnitDrugListMasterFB)form;
		UnitDrugListMasterUTIL.getUnitDrugListForModify(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitDrugListMasterFB fb=(UnitDrugListMasterFB)form;
		UnitDrugListMasterUTIL.modifySaveDrugList(fb,request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("LIST"); 
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitDrugListMasterFB fb=(UnitDrugListMasterFB)form;
		UnitDrugListMasterUTIL.getUnitDrugListForModify(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
}
