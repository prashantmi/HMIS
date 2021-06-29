/*
 * Vasu
 * Date:- 01-08-2018
 * New Process Hospital Course
*/
package ehr.hospitalcourse.dataentry;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.hospitalcourse.dataentry.EHRSection_HospitalCourseFB;
import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeUTL;;

public class EHRSection_HospitalCourseACT extends CSRFGardTokenAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
			return mapping.findForward("NEW");
	}
	
	
	
	public ActionForward PATCLINICALDOC_ENC_COURSE_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		EHRSection_HospitalCourseFB fb = (EHRSection_HospitalCourseFB) form;
		//fb.reset(mapping, request);
		EHRSection_HospitalCourseUTL.getEssentials(fb, request,response);
		return mapping.findForward("HOSPITALCOURSE");
		
	}
	
		
	public ActionForward PATCLINICALDOC_ENC_COURSE_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_HospitalCourseFB fb = (EHRSection_HospitalCourseFB) form;
		EHRSection_HospitalCourseUTL.saveDetails( request, response, fb);
		EHRSection_HospitalCourseUTL.getEssentials(fb, request,response);
		return null;
		
	}
	
	
}
