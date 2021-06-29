package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.master.controller.fb.MacroMstFB;
import opd.master.controller.util.MacroMstUTIL;

public class MacroMstACT extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		MacroMstFB fb = (MacroMstFB) form;
		WebUTIL.refreshTransState(request);
		//MacroMstUTIL.donationCompList(fb, request);
		MacroMstUTIL.getProcessName(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		validateToken(request,response);
		MacroMstFB fb = (MacroMstFB) form;
		//Status objStatus = new Status();
		boolean hasFlag = MacroMstUTIL.saveMacroInfo(fb, request);
		if (hasFlag)
		{
			fb.reset(mapping, request);
		}
			
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		MacroMstFB _fb = (MacroMstFB) form;
		WebUTIL.refreshTransState(_request);
		MacroMstUTIL.fetchMacroInfo(_fb, _request);
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{

		validateToken(_request,response);
		MacroMstFB _fb = (MacroMstFB) form;
	//	MacroMstUTIL.updateDonationCompInfo(_fb, _request);
		MacroMstUTIL.saveModMacroInfo(_fb, _request);
		return mapping.findForward("LIST");
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		MacroMstFB _fb = (MacroMstFB) form;
		WebUTIL.refreshTransState(_request);
		MacroMstUTIL.fetchMacroInfo(_fb, _request);
		return mapping.findForward("NEW");
	}
}
