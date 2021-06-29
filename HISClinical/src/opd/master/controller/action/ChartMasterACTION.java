package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ChartMasterFB;
import opd.master.controller.util.ChartMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ChartMasterACTION extends CSRFGardTokenAction  
{

  public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return this.ADD(mapping, form, request, response);
	}
  public ActionForward ADD(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	  generateToken(request);
	  ChartMasterFB _fb=(ChartMasterFB)form;
		WebUTIL.refreshTransState(request);
		_fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
  public ActionForward SAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	  validateToken(request,response);
	  ChartMasterFB fb=(ChartMasterFB)form;
		boolean flag=ChartMasterUTIL.saveChart(fb, request);
		if(flag)
			fb.reset(mapping, request);
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}
  
  public ActionForward MODIFY(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
	  generateToken(request);
	  ChartMasterFB _fb=(ChartMasterFB)form;
		WebUTIL.refreshTransState(request);
		_fb.reset(mapping,request);
		ChartMasterUTIL.getModifyDetail(_fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		ChartMasterFB fb=(ChartMasterFB)form;
		boolean flag=ChartMasterUTIL.modifySave(fb, request);
		if(!flag){
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}	
		else
			return mapping.findForward("LIST");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ChartMasterFB fb=(ChartMasterFB)form;
		ChartMasterUTIL.getModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}
  
  public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return mapping.findForward("LIST");
	}
  
  public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{	
	  return mapping.findForward("NEW"); 	   	
	}
}
