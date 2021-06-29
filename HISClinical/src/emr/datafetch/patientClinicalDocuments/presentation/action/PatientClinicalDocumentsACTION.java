/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : OPD IPD
 ## Process/Database Object Name	    : PATIENT CLINICAL DOCUMENTS
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2017 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/


package emr.datafetch.patientClinicalDocuments.presentation.action;

import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.servlets.ServletsUtilityConfig;
import hisglobal.vo.PatientDetailVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ehr.EHRConfig;
import emr.datafetch.patientClinicalDocuments.presentation.fb.PatientClinicalDocumentsFB;
import emr.datafetch.patientClinicalDocuments.presentation.util.PatientClinicalDocumentsUTL;
import emr.vo.EHR_PatEncounterDetailsVO;
import opd.OpdConfig;




import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import DataProcessing.SavePDF;
public class PatientClinicalDocumentsACTION extends DispatchAction
{
	/*public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("NEW");
		
	}*/
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
		generateToken(request);
		if(request.getAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_KEY)!=null)
		{
			fb.setDocumentType((String)request.getAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_CODE));
			fb.setHmode("CLINICALDOCUMENTS"); 
			return this.CLINICALDOCUMENTS(mapping, form, request, response);
		}
		else
		{
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			request.getSession().removeAttribute(OpdConfig.DRUG_SHEDULE_MAP);
			//fb.reset();
			PatientClinicalDocumentsUTL.setEssentials(fb, request);
			
			return mapping.findForward("NEW");
		}
	}


	
	public ActionForward CLINICALDOCUMENTS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			PatientClinicalDocumentsFB _fb = (PatientClinicalDocumentsFB) form;
			Status objStatus = new Status();
			HttpSession session = request.getSession();
			String flag="true";
			try
			{
				
				
				// Fetching Patient Detail from Desk
				PatientDetailVO selectedPatientVO = null;
				
				 if (session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO)!=null)
				{
					
					selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
					
					_fb.setEpisodeCode(selectedPatientVO.getEpisodeCode());
					_fb.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
					_fb.setDepartmentUnitCode(selectedPatientVO.getDepartmentUnitCode());
					_fb.setPatCrNo(selectedPatientVO.getPatCrNo());
					
				}
				
				// Map map= new HashMap();
					//map.put("dd",selectedPatientVO);	
				EHR_PatEncounterDetailsVO patencountervo = new 	EHR_PatEncounterDetailsVO();
				HelperMethods.populatetToNullOrEmpty(patencountervo, _fb);
				session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
				
				PatientClinicalDocumentsUTL.getClinicalSectionCompositions(request,response,_fb);
				 
				
			}
			catch (HisRecordNotFoundException e)
			{
				objStatus.add(Status.FAILURE, "", "");
			}		
			_fb.setClinicalDocumentMode("CLINICALDOCUMENTS");		
			return mapping.findForward("CLINICALDOCUMENTS");
	}
	
	
	
	public ActionForward VISITREASON(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
		PatientClinicalDocumentsUTL.getPatDetailOpp(request,response,fb);
		return mapping.findForward("VISITREASON");
	}
	
	public ActionForward DESKDIAGNOSIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("DESKDIAGNOSIS");
	}
	
		
	public ActionForward DESKTREATMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("DESKTREATMENT");
	}
	
	
	public ActionForward FOLLOWUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("FOLLOWUP");
	}
	

	public ActionForward REQUISITIONRAISING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("REQUISITIONRAISING");
	}
	
	
	public ActionForward OPDNEXTVISITDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("OPDNEXTVISITDETAIL");
	}
	
	
	public ActionForward DISCHARGESTATUS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("DISCHARGESTATUS");
	}
	public ActionForward COMPLAINTS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("COMPLAINTS");
	}
	public ActionForward HISTORY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("HISTORY");
	}
	public ActionForward EXAMINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("EXAMINATION");
	}
	public ActionForward ADVICEONDISCHARGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("ADVICEONDISCHARGE");
	}
	public ActionForward VIEWINVESTIGATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("VIEWINVESTIGATION");
	}
	public ActionForward TREATMENTGIVEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("TREATMENTGIVEN");
	}
	/*public ActionForward HOSPITALCOURSE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("HOSPITALCOURSE");
	}*/
		
	public ActionForward DOCUMENTHEADER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("DOCUMENTHEADER");
	}	
	
	public ActionForward PATDETAILS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		//PatientClinicalDocumentsUTL.getPatDetailOpp(request,response);
		return null;
	}
		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
		PatientClinicalDocumentsUTL.saveDetails(request,response,fb);
		return null;
		
	}
		
		public ActionForward PREVIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			//PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
			//WebUTIL.setAttributeInSession(request, EHRConfig.CLINICAL_SECTION_COMP_SELECT_JSON, fb.getClinicalDocCompSelectJSON());
			//PatientClinicalDocumentsUTL.getPatDetailOpp(request,response);
			//SavePDF.saveFileToLocation(headerString, footerString, htmlString, pdfFileName, footerHeight, headerHeight, headerwidth, footerWidth, pageHeight, pageWidth, oldpdf)
			 //SavePDF.saveFileToLocation(fb.getClinicalDocCompSelectJSON(), fb.getClinicalDocCompSelectJSON(), fb.getClinicalDocCompSelectJSON(), "abc.pdf", 20.0f, 20.0f, 80.0f, 80.0f, "100", "100");
			return mapping.findForward("PREVIEW");
		}
		
		public ActionForward PREVIEWPDF(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
			WebUTIL.setAttributeInSession(request, EHRConfig.CLINICAL_SECTION_COMP_SELECT_JSON, fb.getClinicalDocCompSelectJSON());
			//PatientClinicalDocumentsUTL.getPatDetailOpp(request,response);
			//SavePDF.saveFileToLocation(headerString, footerString, htmlString, pdfFileName, footerHeight, headerHeight, headerwidth, footerWidth, pageHeight, pageWidth, oldpdf)
			 //SavePDF.saveFileToLocation(fb.getClinicalDocCompSelectJSON(), fb.getClinicalDocCompSelectJSON(), fb.getClinicalDocCompSelectJSON(), "abc.pdf", 20.0f, 20.0f, 80.0f, 80.0f, "100", "100");
			
			//response write PDF
			return null;
		}

		
		// get the detail of the profile to generating
		public ActionForward VIEWGENRATEDDOCUMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception
		{
			PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
			System.out.println("In PatientClinicalDocumentsACTION In VIEWGENRATEDDOCUMENT()");
			PatientClinicalDocumentsUTL.fetchDetailsForGenerate(fb, request);
			return mapping.findForward("GENERATE");
		}
		
		
		//Generate the Document
		public ActionForward GENRATEDOCUMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception
		{
			PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
			if (PatientClinicalDocumentsUTL.documentGeneration(fb, request))
			{
				//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
				fb.reset();
				PatientClinicalDocumentsUTL.setEssentials(fb, request);
				Status objStatus = new Status();
				objStatus.add(Status.NEW, "", "Document Genrated Successfully");
				WebUTIL.setStatus(request, objStatus);
				return mapping.findForward("NEW");
			}
			else
			{
				return mapping.findForward("OPTIONS");
			}
		}
		
		
		public ActionForward VIEWPRINTDOCUMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception
		{
			PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;

			
			String getcontent=PatientClinicalDocumentsUTL.getViewDoc(fb,request,response);
			
			fb.setDocumentHTML(getcontent);
			fb.setPatCrNo(fb.getPatCrNo());
			
			WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getDocumentHTML());
			// Water Marking
			WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());
		
			return mapping.findForward("VIEWPRINT");
		}
		
		// View or print the profile
		public ActionForward PRINTDOCUMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception
		{
			PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
			WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getDocumentHTML());
			// Water Marking
			WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());
			return mapping.findForward("AUTOMATICGENERATE");
		}
		
		
		//Modify
		
		// get the detail of the profile for modification
		public ActionForward FETCHDOCUMENTDETAILS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception
		{
			PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
			System.out.println("In PatientClinicalDocumentsACTION In VIEWGENRATEDDOCUMENT()");
			PatientClinicalDocumentsUTL.getClinicalSectionCompositions(request,response,fb);
			PatientClinicalDocumentsUTL.fetchDetailsForModify(fb, request);
			fb.setClinicalDocumentMode("MODIFY");
			return mapping.findForward("MODIFY");
		}

		
		
		public ActionForward MODIFYCLINICALDOCUMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception
		{
			
			PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
			PatientClinicalDocumentsUTL.modifyClinicalDocument(fb, request);
			PatientClinicalDocumentsUTL.setEssentials(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW, "", "Clinical Document Modified Successfully");
			WebUTIL.setStatus(request, objStatus);
			return mapping.findForward("NEW");
		}

		public ActionForward REMOVECLINICALDOCUMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception
		{
			PatientClinicalDocumentsFB fb = (PatientClinicalDocumentsFB) form;
			PatientClinicalDocumentsUTL.removeClinicalDocument(fb, request);
			PatientClinicalDocumentsUTL.setEssentials(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW, "", "Clinical Document Removed Successfully");
			WebUTIL.setStatus(request, objStatus);
			return mapping.findForward("NEW");
		}

	
	
}