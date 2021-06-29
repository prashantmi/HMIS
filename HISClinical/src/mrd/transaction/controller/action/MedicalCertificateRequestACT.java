
/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	MRD
## Process/Database Object Name	:	Medical Certificate Request
## Purpose						:	online request raise from OPD Doctor Desk or OPD Bay Desk or IPD Doctor Desk. Doctor provide request slip to patient with complete medical certificate information like rest dates, fitness dates etc.
## Date of Creation				: 	19-November-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 
*/
package mrd.transaction.controller.action;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.MedicalCertificateRequestFB;
import mrd.transaction.controller.utl.MedicalCertificateRequestUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MedicalCertificateRequestACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}	
	/** Action is called at the initial loading of a Page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		System.out.println("MedicalCertificateRequestACT.NEW()");
		MedicalCertificateRequestFB objFB = (MedicalCertificateRequestFB) form;	
		//WebUTIL.refreshTransState(request);	 // Commented By Pragya 2014.12.26 as required by New Desk	
			objFB.reset(mapping, request);
			System.out.println("episodeCode  :"+objFB.getEpisodeCode());
			System.out.println("episodeVisitNo  :"+objFB.getEpisodeVisitNo());
			System.out.println("getDepartmentUnitCode  :"+objFB.getDepartmentUnitCode());
			System.out.println("strCategoryCode  :"+objFB.getStrCategoryCode());
			System.out.println("patCrNo  :"+objFB.getPatCrNo());
		MedicalCertificateRequestUTL.setEssentials(objFB, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		System.out.println("MedicalCertificateRequestACT.SAVE()");
		MedicalCertificateRequestFB objFB = (MedicalCertificateRequestFB) form;
		Status status = new Status();
		status.add(Status.TRANSINPROCESS,"Medical Certificate Detail Saved Successfully","");
		request.setAttribute(Config.STATUS_OBJECT, status);
		
		if(MedicalCertificateRequestUTL.saveMedicalCertificateRequest(objFB,request))
		{
			
				return this.NEW(mapping, form, request, response);
			
		}
		else
			return mapping.findForward("NEW");
	}

	
	public ActionForward FITNESS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		System.out.println("MedicalCertificateRequestACT.FITNESS()");
		MedicalCertificateRequestFB objFB = (MedicalCertificateRequestFB) form;
		MedicalCertificateRequestUTL.setFitnessEssentials(objFB, request);
		return mapping.findForward("NEW");
	}
}
