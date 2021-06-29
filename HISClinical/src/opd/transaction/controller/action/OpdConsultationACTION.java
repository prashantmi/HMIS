package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.transaction.controller.fb.OpdConsultationFB;
import opd.transaction.controller.util.OpdConsultationUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdConsultationACTION extends CSRFGardTokenAction {
	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action  "NEW"
	 */
		public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			return this.NEW(mapping, form, request, response);
		}
		
		/**
		 * action mainly called at the initial loading of a page or when a form is reset
		 * refreshes Transaction State
		 * sets all  OpdPatient essentials
		 * sets PatientList 
		 * @param mapping -object of ActionMapping
		 * @param form - object of  ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse 
		 * @return action forwards to the output view called "NEW"
		 */
		public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			generateToken(request);
			OpdConsultationFB opdConsultationFB=(OpdConsultationFB)form;
			opdConsultationFB.reset();
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			OpdConsultationUTIL.getOpdConsultationEssentials(opdConsultationFB,request);
			return mapping.findForward("NEW");
		}
		
		public ActionForward SEND(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			validateToken(request,response);
			OpdConsultationFB opdConsultationFB=(OpdConsultationFB)form;
			OpdConsultationUTIL.sendOpdConsultationData(opdConsultationFB,request);
			return this.NEW(mapping, form, request, response);
		}
		
		public ActionForward GETPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			//OpdConsultationFB opdConsultationFB=(OpdConsultationFB)form;
			Status objStatus=new Status();
			List patProfileDtlVOList=(List)request.getSession().getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);
			if(patProfileDtlVOList==null)
				patProfileDtlVOList=new ArrayList();
			if(patProfileDtlVOList.size()==0){
				objStatus.add(Status.DONE,"","No Profile Found");
			}
			WebUTIL.setStatus(request, objStatus);
			return mapping.findForward("SHOWPROFILE");
		}
		
		public ActionForward ATTACHPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			OpdConsultationFB opdConsultationFB=(OpdConsultationFB)form;
			OpdConsultationUTIL.attachProfile(opdConsultationFB,request);
			return this.NEW(mapping, form, request, response);
		}
}
