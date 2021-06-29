package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.IcdIndexMasterFB;
import opd.master.controller.util.IcdIndexMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IcdIndexMasterACTION extends CSRFGardTokenAction
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
		IcdIndexMasterFB fb = (IcdIndexMasterFB)form;

		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		IcdIndexMasterUTIL.getInitializeGroup(fb, request);
		
		return mapping.findForward("NEW");
	}

	
	/*
	 * Populating the Icd Sub Group Combo
	 */
	public ActionForward SETICDSUBGROUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexMasterFB fb = (IcdIndexMasterFB) form;
		IcdIndexMasterUTIL.getIcdSubGroup(fb, request);
		
		return mapping.findForward("NEW");
	}
	
	/*
	 * Populating the Dual Icd Sub Group Combo
	 */
	public ActionForward SETDUALICDSUBGROUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexMasterFB fb = (IcdIndexMasterFB) form;
		IcdIndexMasterUTIL.getDualIcdSubGroup(fb, request);
		
		return mapping.findForward("NEW");
		
	}
	
	/*
	 * Populating the Icd Disease Combo
	 */
	public ActionForward SETICDDISEASE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexMasterFB fb = (IcdIndexMasterFB) form;
		IcdIndexMasterUTIL.getIcdDisease(fb,request);
		return mapping.findForward("NEW");
	}
	
	/*
	 * Populating the Dual Icd Disease Combo
	 */
	public ActionForward SETDUALICDDISEASE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexMasterFB fb = (IcdIndexMasterFB) form;
		IcdIndexMasterUTIL.getDualIcdDisease(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		IcdIndexMasterFB fb = (IcdIndexMasterFB) form;
		boolean flag = IcdIndexMasterUTIL.saveRecord(fb, request);
		if (flag)
		{
			Status objStatus = new Status();
			fb.reset(mapping, request);
			//objStatus.add(Status.DONE, "Record added successfully", "");
			WebUTIL.setStatus(request, objStatus);
			return this.ADD(mapping, form, request, response);
		}
		//IcdIndexLevelMasterUTIL.getInitializeAdd(fb, request);
		return mapping.findForward("NEW");
	}	
	
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexMasterFB fb = (IcdIndexMasterFB) form;
		WebUTIL.refreshTransState(request);
	//	fb.reset(mapping, request);
		IcdIndexMasterUTIL.getModifyRecord(fb, request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		IcdIndexMasterFB fb = (IcdIndexMasterFB) form;
		boolean flag = IcdIndexMasterUTIL.modifySave(fb, request);
		if (flag)
		{
			return mapping.findForward("LIST");
		}
		else
		{
			//IcdIndexMasterUTIL.getInitializeAdd(fb, request);
			return mapping.findForward("MODIFY");
		}
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdIndexMasterFB fb = (IcdIndexMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		IcdIndexMasterUTIL.getModifyRecord(fb, request);
		return mapping.findForward("VIEW");
	}
	
	
}	

