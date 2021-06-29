package opd.icdsearchengine.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.icdsearchengine.fb.ICDSearchEngineFB;
import opd.icdsearchengine.util.ICDSearchEngineUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ICDEngineResultVol3ACT extends DispatchAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDSearchEngineFB fb = (ICDSearchEngineFB) form;
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}

	public ActionForward INITSEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDSearchEngineFB fb = (ICDSearchEngineFB) form;
		ICDSearchEngineUTL.getICDSearchingResulforVol3(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
