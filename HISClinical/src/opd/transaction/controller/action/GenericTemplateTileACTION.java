package opd.transaction.controller.action;

/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.bo.GenericTemplateEssentialBO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.GenericTemplateTileFB;
import opd.transaction.controller.util.GenericTemplateTileUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GenericTemplateTileACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	// Setting Essentials
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		
		String isCallFromSinglePage = request.getParameter("isCallFromSinglePage");
		
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		GenericTemplateTileUTIL.setTemplateTileEssentials(fb, request);
		
		if(isCallFromSinglePage!=null && !isCallFromSinglePage.equals(""))
		{
		return mapping.findForward("GENERICTEMPLATEFORSPD");
		}
		return mapping.findForward("NEW");
	}

	// Saving Clinical Data
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		GenericTemplateTileUTIL.savePatientClinicalData(fb, request);
		return mapping.findForward("NEW");
	}

	// Pop Uu for Adding More Templates
	public ActionForward MORETEMPLATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("MORETEMPLATE");
	}

	// Adding More Undefault Templates
	public ActionForward ADDUNDEFAULT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		GenericTemplateTileUTIL.addUndefaultTemplates(fb, request);
		return mapping.findForward("NEW");
	}

	//** Report 
	public ActionForward TEMPREPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		fb.setReportMode(GenericTemplateConfig.TEMPLATE_VIEW_REPORT_TYPE_MODE_TEMPLATE_WISE);
		GenericTemplateTileUTIL.setTempReportEssentials(fb, request);
		return mapping.findForward("TEMPREPORT");
	}
	//** Report Mode Change
	public ActionForward CHANGEREPORTMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		GenericTemplateTileUTIL.setTempReportEssentials(fb, request);
		return mapping.findForward("TEMPREPORT");
	}
	// ** Getting Previous Visit Data
	public ActionForward GOGETREPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
		GenericTemplateTileUTIL.setViewReportEssentials(fb, request);
		return mapping.findForward("VIEWREPORT");
	}
	//** Chart View Mode Change
	public ActionForward CHANGECHARTVIEWMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("VIEWREPORT");
	}

	/*// * Getting Report Template-Wise
	public ActionForward TEMPWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		GenericTemplateTileUTIL.setTempReportEssentials(fb, request);
		return mapping.findForward("PREVVISIT");
	}

	// * Getting Report Parameter-Wise
	public ActionForward PARAWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		GenericTemplateTileUTIL.setParaReportEssentials(fb, request);
		return mapping.findForward("PREVVISIT");
	}

	// Get Previous Visits Data
	public ActionForward LASTVISIT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		GenericTemplateTileUTIL.setLastVisitReportEssentials(fb, request);
		return mapping.findForward("PREVVISIT");
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
		OpdTemplateTabFB fb = (OpdTemplateTabFB) form;
		OpdTemplateTabUTIL.setTempReportEssentials(fb, request);
		return mapping.findForward("PREVVISIT");
	}

	// * Getting Report Parameter-Wise
	public ActionForward PARAWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
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
	}*/
	
	public ActionForward AJX_G_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
		throws HisException, Exception, SQLException
	{
		StringBuffer strBuff= new StringBuffer();
		strBuff.append("[{summary:\'Patient Template Detail\'}]");
		System.out.println("GenericTemplateTileACTION.AJX_G_SUMMARY()   " +strBuff.toString() );
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
	
	//Added by Vasu on 13.Aug.2019
	public ActionForward SAVETEMPLATEDATASPD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//validateToken(request,response);
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		GenericTemplateTileUTIL.savePatientClinicalData(fb, request);
		return mapping.findForward("GENERICTEMPLATEFORSPD");
	}
	
	public ActionForward CLEARTEMPLATESPD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		GenericTemplateTileUTIL.setTemplateTileEssentials(fb, request);
		return mapping.findForward("GENERICTEMPLATEFORSPD");	
	}
	
	public ActionForward GrowthChart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		//GenericTemplateEssentialBO.GrowthChart(fb.getPatCrNo(), request,response);
		return mapping.findForward("GROWTHCHART");	
	}
	
	public ActionForward GrowthChartEssential(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericTemplateTileFB fb = (GenericTemplateTileFB) form;
		GenericTemplateEssentialBO.GrowthChart(fb.getPatCrNo(), request,response);
		return null;	
	}
}
