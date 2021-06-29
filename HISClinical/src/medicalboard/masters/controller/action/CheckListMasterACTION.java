package medicalboard.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.masters.controller.fb.ChecklistMasterFB;
import medicalboard.masters.controller.utl.ChecklistMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CheckListMasterACTION extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		ChecklistMasterFB fb = (ChecklistMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		ChecklistMasterUTL.getIsCompulsoryOptions(fb, request);
		return mapping.findForward("NEW");
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ChecklistMasterFB fb = (ChecklistMasterFB) form;
//		Status objStatus = new Status();
		boolean flag = ChecklistMasterUTL.saveChecklistMst(fb, request);
		if (flag)
		{
			fb.reset(mapping, request);
		}
		fb.setHmode("ADD");	
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		ChecklistMasterFB _fb = (ChecklistMasterFB) form;
		ChecklistMasterUTL.getChecklistById(_fb, _request);
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		ChecklistMasterFB _fb = (ChecklistMasterFB) form;
		boolean flag=ChecklistMasterUTL.modifySaveChecklist(_fb, _request);
		if(flag){
			return mapping.findForward("LIST");
		}
		else{
			_fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		ChecklistMasterFB _fb = (ChecklistMasterFB) form;
		ChecklistMasterUTL.getChecklistById(_fb, _request);
		return mapping.findForward("NEW");
	}
}
