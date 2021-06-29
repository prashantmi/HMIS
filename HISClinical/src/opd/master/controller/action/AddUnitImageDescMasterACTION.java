package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.AddUnitImageDescMasterFB;
import opd.master.controller.util.AddUnitImageDescMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AddUnitImageDescMasterACTION extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		AddUnitImageDescMasterFB fb=(AddUnitImageDescMasterFB)form;
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		AddUnitImageDescMasterUTIL.getUnitImageDescEssential(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		AddUnitImageDescMasterFB fb=(AddUnitImageDescMasterFB)form;
		AddUnitImageDescMasterUTIL.saveUnitImageDesc(fb,request);
		fb.setHmode("ADD");
		return this.ADD(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{ 
		AddUnitImageDescMasterFB fb=(AddUnitImageDescMasterFB)form;
		AddUnitImageDescMasterUTIL.getUnitImageDescForModify(fb, request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		AddUnitImageDescMasterFB fb=(AddUnitImageDescMasterFB)form;
		AddUnitImageDescMasterUTIL.saveModUnitImageDesc(fb,request);
		return mapping.findForward("LIST");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		AddUnitImageDescMasterFB fb=(AddUnitImageDescMasterFB)form;
		AddUnitImageDescMasterUTIL.getUnitImageDescForModify(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
}
