package opd.transaction.controller.action;

/**
 * @author  CDAC
 */

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.transaction.controller.fb.OpdTemplateTabFB;
import opd.transaction.controller.util.OpdTemplateTabUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdTemplateTabACTION extends CSRFGardTokenAction
{
	// * Unspecified forwarded to NEW
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	// * Setting Essentials
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		OpdTemplateTabFB fb = (OpdTemplateTabFB) form;
		WebUTIL.refreshTransState(request);
		OpdTemplateTabUTIL.getSetDeskMenuTemplatesEssentials(fb, request);
		return mapping.findForward("NEW");
	}

	// * Saving Clinical Data
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdTemplateTabFB fb = (OpdTemplateTabFB) form;
		OpdTemplateTabUTIL.saveTemplateParameterValues(fb, request);
		return mapping.findForward("NEW");
	}

	// * Open Pop-up
	public ActionForward POPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("UNDEFAULT");
	}

	// * Adding Non-default Templates
	public ActionForward ADDUNDEFAULT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdTemplateTabFB fb = (OpdTemplateTabFB) form;
		OpdTemplateTabUTIL.addUndefaultTemplate(fb, request);
		return mapping.findForward("NEW");
	}

	// * Printing Consent
	public ActionForward PRINTCONSENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdTemplateTabFB fb = (OpdTemplateTabFB) form;
		OpdTemplateTabUTIL.printConsent(fb, request);
		return mapping.findForward("NEW");
	}

	// * Getting Previous Visit Data
	public ActionForward PREVVISIT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdTemplateTabFB fb = (OpdTemplateTabFB) form;
		OpdTemplateTabUTIL.setTempReportEssentials(fb, request);
		return mapping.findForward("PREVVISIT");
	}

	// * Getting Report Template-Wise
	public ActionForward TEMPWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//validateToken(request,response);
		OpdTemplateTabFB fb = (OpdTemplateTabFB) form;
		OpdTemplateTabUTIL.setTempReportEssentials(fb, request);
		return mapping.findForward("PREVVISIT");
	}

	// * Getting Report Parameter-Wise
	public ActionForward PARAWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//validateToken(request,response);
		OpdTemplateTabFB fb = (OpdTemplateTabFB) form;
		OpdTemplateTabUTIL.setParaReportEssentials(fb, request);
		return mapping.findForward("PREVVISIT");
	}

	//* Viewing Report
	public ActionForward VIEWREPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdTemplateTabFB fb = (OpdTemplateTabFB) form;
		if (fb.getChoice().equals(OpdConfig.CHOICE_PREVVISIT_TEMP_WISE))
		{
			OpdTemplateTabUTIL.getTempWiseReport(fb, request);
		}
		else if (fb.getChoice().equals(OpdConfig.CHOICE_PREVVISIT_PARA_WISE))
		{
			OpdTemplateTabUTIL.getParaWiseReport(fb, request);
		}
		return mapping.findForward("VIEWREPORT");
	}
	
	public ActionForward GrowthChart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//validateToken(request,response);
		//OpdTemplateTabFB fb = (OpdTemplateTabFB) form;
		//OpdTemplateTabUTIL.setParaReportEssentials(fb, request);
		return mapping.findForward("growthChart");
	}
}
