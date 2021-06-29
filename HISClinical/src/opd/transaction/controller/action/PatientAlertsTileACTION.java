package opd.transaction.controller.action;

/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.GenericTemplateTileFB;
import opd.transaction.controller.util.GenericTemplateTileUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PatientAlertsTileACTION extends CSRFGardTokenAction
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
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		GenericTemplateTileUTIL.setTemplateTileEssentials(fb, request);
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
}
