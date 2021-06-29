package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ParaRangeMstFB;
import opd.master.controller.util.ParaRangeMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ParaRangeMstACTION extends CSRFGardTokenAction 
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		ParaRangeMstFB fb= (ParaRangeMstFB)form;
		WebUTIL.refreshTransState(request);
		//fb.reset(mapping,request);
		fb.setTempMode(fb.getHmode());
		ParaRangeMstUTL.getParaName(fb, request);
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}	
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		ParaRangeMstFB fb= (ParaRangeMstFB)form;
		fb.setTempMode(fb.getHmode());
		boolean flag=ParaRangeMstUTL.saveParaRangeInfo(fb, request);
		
		if(flag)
			fb.reset(mapping, request);
		
		return this.ADD(mapping, form, request, response);
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ParaRangeMstFB fb= (ParaRangeMstFB)form;
		ParaRangeMstUTL.fetchParaRangeInfo(fb, request);
		return mapping.findForward("NEW");
	}
		
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ParaRangeMstFB fb = (ParaRangeMstFB)form;
		boolean flag=ParaRangeMstUTL.saveModParaRangeInfo(fb,request);
		fb.setHmode("MODIFY");
		if(flag)
			return mapping.findForward("LIST");
		else
			return mapping.findForward("NEW");
	}
		
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ParaRangeMstFB fb= (ParaRangeMstFB)form;
		ParaRangeMstUTL.fetchParaRangeInfo(fb, request);
		return mapping.findForward("NEW");
	}
	
}
