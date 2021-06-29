package opd.master.controller.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.IcdIndexLevelMasterFB;
import opd.master.controller.util.IcdIndexLevelMasterUTIL;

/**
 *  Developer : Vivek Aggarwal
 *  Created Date : 1-Feb-2011
 *  Process Name : Icd Index Level Master
 *  Last Modified Date : 28-Mar-2011  
 */
public class IcdIndexLevelMasterACTION extends CSRFGardTokenAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.ADD(mapping, form, request, response);
	}

	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		generateToken(request);
		IcdIndexLevelMasterFB fb = (IcdIndexLevelMasterFB)form;

		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		IcdIndexLevelMasterUTIL.getInitializeAdd(fb, request);
		
		return mapping.findForward("NEW");
	}

	
	public ActionForward SETPARENTMODIFIER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexLevelMasterFB fb = (IcdIndexLevelMasterFB) form;
		boolean flag =IcdIndexLevelMasterUTIL.getParentModifier(fb, request);
		
		if(fb.getPageFlag().equals("ADD_PAGE"))
			return mapping.findForward("NEW");
		else if(fb.getPageFlag().equals("MODIFY_PAGE"))
			return mapping.findForward("MODIFY");
		else if(fb.getPageFlag().equals("VIEW_PAGE"));
			return mapping.findForward("VIEW");
	}

	
	/*
	 * Populating the Icd Sub Group Combo
	 */
	public ActionForward SETICDSUBGROUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexLevelMasterFB fb = (IcdIndexLevelMasterFB) form;
		IcdIndexLevelMasterUTIL.getIcdSubGroup(fb, request);
		
		if(fb.getPageFlag().equals("ADD_PAGE"))
			return mapping.findForward("NEW");
		else if(fb.getPageFlag().equals("MODIFY_PAGE"))
			return mapping.findForward("MODIFY");
		else if(fb.getPageFlag().equals("VIEW_PAGE"));
			return mapping.findForward("VIEW");
	}
	
	/*
	 * Populating the Dual Icd Sub Group Combo
	 */
	public ActionForward SETDUALICDSUBGROUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexLevelMasterFB fb = (IcdIndexLevelMasterFB) form;
		IcdIndexLevelMasterUTIL.getDualIcdSubGroup(fb, request);
		
		if(fb.getPageFlag().equals("ADD_PAGE"))
			return mapping.findForward("NEW");
		else if(fb.getPageFlag().equals("MODIFY_PAGE"))
			return mapping.findForward("MODIFY");
		else if(fb.getPageFlag().equals("VIEW_PAGE"));
			return mapping.findForward("VIEW");
		
	}
	
	/*
	 * Populating the Icd Disease Combo
	 */
	public ActionForward SETICDDISEASE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexLevelMasterFB fb = (IcdIndexLevelMasterFB) form;
		IcdIndexLevelMasterUTIL.getIcdDisease(fb,request);

		if(fb.getPageFlag().equals("ADD_PAGE"))
			return mapping.findForward("NEW");
		else if(fb.getPageFlag().equals("MODIFY_PAGE"))
			return mapping.findForward("MODIFY");
		else if(fb.getPageFlag().equals("VIEW_PAGE"));
			return mapping.findForward("VIEW");
	}
	
	/*
	 * Populating the Dual Icd Disease Combo
	 */
	public ActionForward SETDUALICDDISEASE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexLevelMasterFB fb = (IcdIndexLevelMasterFB) form;
		IcdIndexLevelMasterUTIL.getDualIcdDisease(fb,request);

		if(fb.getPageFlag().equals("ADD_PAGE"))
			return mapping.findForward("NEW");
		else if(fb.getPageFlag().equals("MODIFY_PAGE"))
			return mapping.findForward("MODIFY");
		else if(fb.getPageFlag().equals("VIEW_PAGE"));
			return mapping.findForward("VIEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		IcdIndexLevelMasterFB fb = (IcdIndexLevelMasterFB) form;
		boolean flag = IcdIndexLevelMasterUTIL.saveRecord(fb, request);
		if (flag)
		{
			Status objStatus = new Status();
			fb.reset(mapping, request);
			WebUTIL.setStatus(request, objStatus);
			return this.ADD(mapping, form, request, response);
		}
		IcdIndexLevelMasterUTIL.getInitializeAdd(fb, request);
		return mapping.findForward("NEW");
	}	
	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexLevelMasterFB fb = (IcdIndexLevelMasterFB) form;
		WebUTIL.refreshTransState(request);
//		fb.reset(mapping, request);
		IcdIndexLevelMasterUTIL.getModifyRecord(fb, request);
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		IcdIndexLevelMasterFB fb = (IcdIndexLevelMasterFB) form;
		boolean flag = IcdIndexLevelMasterUTIL.modifySave(fb, request);
		if (flag)
		{
			return mapping.findForward("LIST");
		}
		else
		{
			IcdIndexLevelMasterUTIL.getInitializeAdd(fb, request);
			return mapping.findForward("MODIFY");
		}
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexLevelMasterFB fb = (IcdIndexLevelMasterFB) form;
		fb.reset(mapping, request);
		IcdIndexLevelMasterUTIL.getViewRecord(fb, request);
		return mapping.findForward("VIEW");
	}
}

