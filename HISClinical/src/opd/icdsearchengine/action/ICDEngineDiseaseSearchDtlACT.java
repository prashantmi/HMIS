package opd.icdsearchengine.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.icdsearchengine.fb.ICDEngineDiseaseDetailFB;
import opd.icdsearchengine.util.ICDSearchEngineUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ICDEngineDiseaseSearchDtlACT extends DispatchAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDEngineDiseaseDetailFB fb = (ICDEngineDiseaseDetailFB) form;
		ICDSearchEngineUTL.getICDDiseaseDetailNEW(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward SHOW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDEngineDiseaseDetailFB fb = (ICDEngineDiseaseDetailFB) form;
		ICDSearchEngineUTL.getICDDiseaseDetailSHOW(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward PREV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDEngineDiseaseDetailFB fb = (ICDEngineDiseaseDetailFB) form;
		ICDSearchEngineUTL.getICDDiseaseDetailPREV(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward NEXT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ICDEngineDiseaseDetailFB fb = (ICDEngineDiseaseDetailFB) form;
		ICDSearchEngineUTL.getICDDiseaseDetailNEXT(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
