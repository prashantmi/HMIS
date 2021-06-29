package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ICDIncludeExcludeFB;
import opd.master.controller.util.ICDIncludeExcludeUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ICDIncludeExcludeACTION extends CSRFGardTokenAction
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
		ICDIncludeExcludeFB fb = (ICDIncludeExcludeFB) form;

		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		ICDIncludeExcludeUTIL.setEssential(fb, request);
		
		return mapping.findForward("NEW");
	}

	public ActionForward SETDISEASEDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDIncludeExcludeFB fb = (ICDIncludeExcludeFB) form;

		ICDIncludeExcludeUTIL.setDiseaseDetail(fb, request);
		
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ICDIncludeExcludeFB fb = (ICDIncludeExcludeFB) form;
		boolean flag = ICDIncludeExcludeUTIL.saveRecord(fb, request);
		if (flag)
		{
			Status objStatus = new Status();
			fb.reset(mapping, request);
			objStatus.add(Status.TRANSINPROCESS, "Record added successfully", "");
			WebUTIL.setStatus(request, objStatus);
		}
		return mapping.findForward("NEW");
	}	

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDIncludeExcludeFB fb = (ICDIncludeExcludeFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		ICDIncludeExcludeUTIL.getRecord(fb, request);
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ICDIncludeExcludeFB fb = (ICDIncludeExcludeFB) form;
		boolean flag = ICDIncludeExcludeUTIL.modifyRecord(fb, request);
		if (flag)
		{
			return mapping.findForward("LIST");
		}
		else
		{
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDIncludeExcludeFB fb = (ICDIncludeExcludeFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		ICDIncludeExcludeUTIL.getRecord(fb, request);
		return mapping.findForward("VIEW");
	}
}
