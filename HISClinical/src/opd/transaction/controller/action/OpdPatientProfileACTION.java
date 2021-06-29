package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdPatientProfileFB;
import opd.transaction.controller.util.OpdPatientProfileUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdPatientProfileACTION extends CSRFGardTokenAction
{
	/**
	 * the default action called when a page is loaded for the first time
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action "NEW"
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{

		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		OpdPatientProfileFB fb = (OpdPatientProfileFB) form;
		// fb.reset();
		fb.setSelectedEffectiveFrom(new String[]{ "" });
		fb.setSelectedEffectiveTo(new String[]{ "" });
		fb.setHtmlValue("");
		fb.setFromDate("");
		fb.setToDate("");
		fb.setRemarks(new String[]{ "" });
		OpdPatientProfileUTIL.getPreviousProfile(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward GETEPISODEORVISIT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OpdPatientProfileFB fb = (OpdPatientProfileFB) form;
		OpdPatientProfileUTIL.getPatientEpisodes(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward SHOWPROFILEMENU(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OpdPatientProfileFB fb = (OpdPatientProfileFB) form;
		OpdPatientProfileUTIL.showProfileMenu(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward SHOWSELECTEDPROFILEMENUDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OpdPatientProfileFB fb = (OpdPatientProfileFB) form;
		OpdPatientProfileUTIL.showProfileMenu(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward SHOWPROFILEDIAGNOSIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		boolean flag;
		OpdPatientProfileFB fb = (OpdPatientProfileFB) form;
		flag = OpdPatientProfileUTIL.getPatientDiagnosisDetail(fb, request);
		if (flag)
		{
			return mapping.findForward("SHOWPROFILEDIAGNOSIS");
		}
		else
		{
			return this.SHOWPROFILEMENU(mapping, form, request, response);
		}
	}

	public ActionForward GENRATEPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		validateToken(request,response);
		OpdPatientProfileFB fb = (OpdPatientProfileFB) form;
		OpdPatientProfileUTIL.genrateProfile(fb, request);
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward MODIFYPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		
		OpdPatientProfileFB fb = (OpdPatientProfileFB) form;
		OpdPatientProfileUTIL.modifyProfileEffectiveDate(fb, request);
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward SHOWPROFILEALLERGIES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		boolean flag;
		OpdPatientProfileFB fb = (OpdPatientProfileFB) form;
		flag = OpdPatientProfileUTIL.getPatientEpisodeAllergiesDetail(fb, request);
		if (flag)
		{
			return mapping.findForward("SHOWPROFILEALLERGIES");
		}
		else
		{
			return this.SHOWPROFILEMENU(mapping, form, request, response);
		}

	}

}
