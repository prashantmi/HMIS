package mrd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.transaction.controller.fb.EmrCommonDeskFB;
import mrd.transaction.controller.utl.EmrCommonDeskUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ehr.EHRConfig;

public class EmrCommonDeskACT extends DispatchAction {

	
	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action  "NEW"
	 */
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);
	}
	
	/**
	 * action mainly called at the initial loading of a page or when a form is reset
	 * refreshes Transaction State
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		//WebUTIL.refreshTransState(request);	 // Commented By Pragya 2014.12.26 as required by New Desk
		
		ControllerUTIL.setSysdate(request);
		HttpSession session = request.getSession();
		session.removeAttribute("crNo");
		//EmrCommonDeskUTL.getDepartmentUnitListForEMR(fb,request);
		//EmrCommonDeskUTL.configureDetail(fb,request);
		WebUTIL.setAttributeInSession(request, "callFrom", "DIRECT"); ////request.getSession().setAttribute("callFrom", "DIRECT");
		fb.setCallFrom("DIRECT");
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   if(!EmrCommonDeskUTL.getEssentials(fb, request))
		   {
				Status objStatus = new Status();
				objStatus.add(Status.TRANSINPROCESS, "", "No Episode Found for this CR No");
				request.setAttribute(Config.STATUS_OBJECT, objStatus);
		   }
		   else
		   {
		   WebUTIL.getSession(request).setAttribute("crNo",fb.getPatCrNo());
		   WebUTIL.getSession(request).setAttribute("departmentUnitCode",fb.getDepartmentUnitCode());
		   WebUTIL.getSession(request).setAttribute("chkTreatment",fb.getChkTreatment());
		   EmrCommonDeskUTL.getPatDetail(fb, request ,response);
		   return mapping.findForward("DESK");
		   }
		   return mapping.findForward("NEW");
		}
	
	public ActionForward PATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //EmrCommonDeskUTL.getPatDetail(fb, request ,response);
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   fb.setChkTreatment((String)WebUTIL.getSession(request).getAttribute("chkTreatment"));
		   return mapping.findForward("PATDTL");
		}
	
	public ActionForward PATDTLAJAX(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			try {
				
				String PatData= (String)request.getSession().getAttribute(EHRConfig.EHR_JSON_OBJECT_FIELD_NAME_PATIENT_DATA);
				
				System.out.println("PatData==\n"+PatData);
				
				if (PatData==null || PatData.trim().equals(""))
					PatData="-1";
			   response.getWriter().write(PatData);
				
			   response.setContentType("application/json"); 
			} 
			catch (HisException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		   return null;
		}

	public ActionForward GETNODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   EmrCommonDeskUTL.getNode(fb, request);
		   return mapping.findForward("NODE");
		}
	
	public ActionForward COMMON(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		  // EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   
		   return mapping.findForward("COMMON");
		}
	
	public ActionForward HIDETREE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
	   return mapping.findForward("HIDETREE");
	}

	public ActionForward HEADER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   //EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   
		   return mapping.findForward("HEADER");
		}
	
	public ActionForward PERSONALPROFILE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   fb.setSelectedTab("0");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPersonalProfileDetails(fb, request);
		   return mapping.findForward("PERSONALPROFILE");
		}
	
	public ActionForward TREATMENT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		 EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		  // fb.setSelectedTab("4");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPateintAllDrugAdivceDetails(fb,request);
		   return mapping.findForward("TREATMENT");
		}
	
	public ActionForward ALLERGIES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getAllergiesDetails(fb, request);
		  // fb.setSelectedTab("1");
		   return mapping.findForward("ALLERGIES");
		}
	
	public ActionForward INVESTIGATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		 EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientInvestigationDetails(fb, request);
		  // fb.setSelectedTab("5");
		   return mapping.findForward("INVESTIGATION");
		}
	
	public ActionForward INVESTIGATIONPOPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   return mapping.findForward("INVESTIGATIONPOPUP");
		}
	
	
	public ActionForward ALLIMAGES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   //EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   
		   return mapping.findForward("ALLIMAGES");
		}
	
	public ActionForward CHRONICDISEASE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("2");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientChronicDisease(fb,request);
		   return mapping.findForward("CHRONICDESASE");
		}
	public ActionForward DIAGNOSIS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("3");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getDiagnosisDetailsVisitWise(fb,request);
		   return mapping.findForward("DIAGNOSIS");
		}
	public ActionForward INTAKEOUTTAKE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("11");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientIntakeOutakeDetails(fb,request);
		   return mapping.findForward("INTAKEOUTTAKE");
		}
	public ActionForward PROGRESSNOTES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("12");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientProgressNotes(fb,request);
		   return mapping.findForward("PROGRESSNOTES");
		}
	public ActionForward PROGRESSNOTESPOPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   //EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   return mapping.findForward("PROGRESSNOTESPOPUP");
		}
	
	public ActionForward VITALS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("10");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientVitalDetails(fb,request);
		   return mapping.findForward("VITALS");
		}
	
	public ActionForward EXTINESTIGATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		  // fb.setSelectedTab("6");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientExternalInvestigationDetails(fb,request);
		   return mapping.findForward("EXTINESTIGATION");
		}
	
	public ActionForward COMPLAINTS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("7");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientComplaintsDetails(fb,request);
		   return mapping.findForward("COMPLAINTS");
		}
	public ActionForward HISTORY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("8");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientHistoryDetails(fb,request);
		   return mapping.findForward("HISTORY");
		}
	
	public ActionForward EXAMINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		  // fb.setSelectedTab("9");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientExaminationDetails(fb,request);
		   return mapping.findForward("EXAMINATION");
		}
	
	public ActionForward PROFILE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   
		   //fb.setSelectedTab("13");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientProfilesForAll(fb,request);
		  EmrCommonDeskUTL.getDocumentArchivalEssentials(fb,request);
		   return mapping.findForward("PROFILE");
		}
	
	public ActionForward IMAGEEXAMINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("14");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientDiagnosisImages(fb,request);
		   return mapping.findForward("IMAGEEXAMINATION");
		}
	
	public ActionForward OPERATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("15");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientOperationList(fb,request);
		   return mapping.findForward("OPERATION");
		}
	
	public ActionForward OPERATIONPOPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("15");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientOperationTemplateDetails(fb,request);
		   return mapping.findForward("OPERATIONPOPUP");
		}
	public ActionForward ANCDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("16");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getPatientAncDetails(fb,request);
		   return mapping.findForward("ANCDETAIL");
		}
		
	public ActionForward VISITSUMMARY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		EmrCommonDeskUTL.getPatientVisitSummary(fb,request);
		return mapping.findForward("VISITSUMMARY");
	}
	
	public ActionForward SORTONDEPTUNIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		EmrCommonDeskUTL.sortOnDepartmentUnit(fb,request);
		return mapping.findForward("VISITSUMMARY");
	}
	
	public ActionForward CRMERGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		EmrCommonDeskUTL.getPatientCRMerge(fb,request);
		return mapping.findForward("CRMERGE");
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward CALLFROMDESK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
	   fb.setCallFrom("DESK");
		WebUTIL.setAttributeInSession(request, "callFrom", "DESK"); //request.getSession().setAttribute("callFrom", "DESK");
		   EmrCommonDeskUTL.getPatDetail(fb, request ,response);

	   //WebUTIL.getSession(request).setAttribute("crNo",fb.getPatCrNo());
	   //WebUTIL.getSession(request).setAttribute("departmentUnitCode",fb.getDepartmentUnitCode());
	   return mapping.findForward("DESK");
	}
	
	public ActionForward CRMERGEEMR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		//WebUTIL.refreshTransState(request);  // Commented By Pragya 2014.12.26 as required by New Desk
		fb.setCallFrom("DIRECT");
		WebUTIL.setAttributeInSession(request, "callFrom", "DIRECT");	//request.getSession().setAttribute("callFrom", "DIRECT");
		WebUTIL.getSession(request).setAttribute("crNo",fb.getPatCrNo());
		WebUTIL.getSession(request).setAttribute("departmentUnitCode",fb.getDepartmentUnitCode());
		return mapping.findForward("DESK");
	}
	
	public ActionForward VISITREASON(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("3");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getVisitReasonsEMR(fb,request);
		   return mapping.findForward("VISITREASON");
		}
	
	public ActionForward SERVICEAREA(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("3");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getServiceProceduresEMR(fb,request);
		   return mapping.findForward("SERVICEAREA");
		}
	
	public ActionForward DOCUMENTARCHIVAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   //fb.setSelectedTab("3");
		   fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   EmrCommonDeskUTL.getDocUploadEMR(fb,request);
		   return mapping.findForward("DOCUMENTARCHIVAL");
		}
	
	public ActionForward GETEMRDATATHROUGHAJAX(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		   EmrCommonDeskFB fb=(EmrCommonDeskFB)form;
		   EmrCommonDeskUTL.getPatDetail(fb, request ,response);
		   //fb.setPatCrNo((String)WebUTIL.getSession(request).getAttribute("crNo"));
		   //fb.setChkTreatment((String)WebUTIL.getSession(request).getAttribute("chkTreatment"));
		   return null;
		}
	
}
