package opd.transaction.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.GenericMacroPopupFB;
import opd.transaction.controller.util.GenericMacroPopupUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class GenericMacroPopupACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.ADDMACRO(mapping, form, request, response);
	}

	public ActionForward ADDMACRO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericMacroPopupFB fb = (GenericMacroPopupFB) form;
		GenericMacroPopupUTIL.getSetMacros(fb, request);		
		return mapping.findForward("MACROPOPUP");
	}

	public ActionForward SHOWMACRO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("MACROPOPUP");
	}

	public ActionForward ADDUNITMACRO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericMacroPopupFB fb = (GenericMacroPopupFB) form;
		GenericMacroPopupUTIL.getSetUnitMacros(fb, request);		
		return mapping.findForward("MACROPOPUP");
	}
}
