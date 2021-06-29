package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.UnitMacroMasterFB;
import opd.master.controller.util.UnitMacroMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class UnitMacroMasterACTION extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		UnitMacroMasterFB fb=(UnitMacroMasterFB)form;
		UnitMacroMasterUTIL.getUnitMacroEssential(fb, request);
		fb.reset(mapping, request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitMacroMasterFB fb=(UnitMacroMasterFB)form;
		UnitMacroMasterUTIL.saveUnitExtTreatment(fb, request);
		UnitMacroMasterUTIL.getUnitMacroEssential(fb, request);
		fb.reset(mapping, request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitMacroMasterFB fb=(UnitMacroMasterFB)form;
		UnitMacroMasterUTIL.getUnitMacroForModify(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitMacroMasterFB fb=(UnitMacroMasterFB)form;
		UnitMacroMasterUTIL.modifySaveUnitMacro(fb,request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("LIST"); 
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitMacroMasterFB fb=(UnitMacroMasterFB)form;
		UnitMacroMasterUTIL.getUnitMacroForModify(fb,request);
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
}
