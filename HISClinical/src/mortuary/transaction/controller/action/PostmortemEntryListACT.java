package mortuary.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.PostmortemEntryListFB;
import mortuary.transaction.controller.utl.PostmortemEntryListUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PostmortemEntryListACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PostmortemEntryListFB fb=(PostmortemEntryListFB)form;
		WebUTIL.refreshTransState(request);
		PostmortemEntryListUTL.getDeceasedListForPostmortemEntry(fb,request);
		return mapping.findForward("NEW");
	}
	
}
