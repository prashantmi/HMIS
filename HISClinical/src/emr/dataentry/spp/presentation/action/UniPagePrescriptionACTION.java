/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package emr.dataentry.spp.presentation.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.utility.HTMLToPDFUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisFTLToHTML;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.transaction.controller.util.OpdDocumentArchivalUTIL;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ehr.EHRConfig;
import ehr.header.data.EHRSection_HEADER;
import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;
import emr.dataentry.spp.presentation.fb.UniPagePrescriptionFB;
import emr.dataentry.spp.presentation.util.UniPagePrescriptionUTL;
import emr.dataview.FTLHelloWorld;
import emr.vo.EHR_PatEncounterDetailsVO;
import freemarker.template.Configuration;
import emr.vo.EHR_PatientProfileDtlVO;



public class UniPagePrescriptionACTION extends DispatchAction
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
			UniPagePrescriptionFB _fb = (UniPagePrescriptionFB) form;
			_fb.setPreviousPrescription(null);
			Status objStatus = new Status();
			HttpSession session = request.getSession();
			String hmode="";
			String flag="true";
			
			UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
			try
			{
				//UniPagePrescriptionUTL.getViewDoc(_fb, request,response);
				
				// Fetching Patient Detail from Desk
				PatientDetailVO selectedPatientVO = new PatientDetailVO();
				
				 if (session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO)!=null)
				 {
					selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
						
				 }
				 else if(!_fb.getPatCrNo().equalsIgnoreCase(null))
				 {
					 
					 String crno = _fb.getPatCrNo();
					 String episodeCode =_fb.getEpisodeCode();
					 String visitCode = _fb.getEpisodeVisitNo();
					 String wardCode = _fb.getWardCode();
					 String deptUnitCode = _fb.getDepartmentUnitCode();
					 
					 selectedPatientVO.setPatCrNo(crno);
					 selectedPatientVO.setDepartmentUnitCode(deptUnitCode);
					 selectedPatientVO.setEpisodeVisitNo(visitCode);
					 selectedPatientVO.setEpisodeCode(episodeCode);
					 selectedPatientVO.setWardCode(wardCode);
					 
				 }
				 else 
				 { 
					 selectedPatientVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

					//hmode="GETPATDTL";
				 }
				 	_fb.setEpisodeCode(selectedPatientVO.getEpisodeCode());
					_fb.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
					_fb.setDepartmentUnitCode(selectedPatientVO.getDepartmentUnitCode());
					_fb.setPatCrNo(selectedPatientVO.getPatCrNo());
					_fb.setWardCode(selectedPatientVO.getWardCode());
				//	hmode="CLINICALDOCUMENTS";
					
				// Clear EHRVO and Initiate EHRVO
				EHRVOUtility.setEHRVO(request, _fb.getPatCrNo());
				// Set Hospital VO
				HospitalMstVO voHospital =  ControllerUTIL.getHospitalVO(request);
				EHRVOUtility.setHospitalVO(request, _fb.getPatCrNo(), voHospital);
				
				// Set PatientDetailVo to EHRVO
				EHRVOUtility.setPatientDetailVO(request, _fb.getPatCrNo(), selectedPatientVO);

				// Map map= new HashMap();
					//map.put("dd",selectedPatientVO);	
				EHR_PatEncounterDetailsVO patencountervo = new 	EHR_PatEncounterDetailsVO();
				HelperMethods.populatetToNullOrEmpty(patencountervo, _fb);
				session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
				 
				UniPagePrescriptionUTL.getClinicalSectionCompositions(request,response,_fb);
				
				//Added by Dheeraj 
				UniPagePrescriptionUTL.getPrescriptionDtl(request,response,_fb);
				
				/*if(_fb.getDocumentType().equals("17"))
				{
					UniPagePrescriptionUTL.getPreviousDischargeProfiles(request,response,_fb);
				}*/
			}
			catch (HisRecordNotFoundException e)
			{
				objStatus.add(Status.FAILURE, "", "");
			}		
		    
			if(_fb.getDocumentType().equals("17"))
			{
				return mapping.findForward("DISCHARGESUMMARY");
			}
			else
			{
		/*	return mapping.findForward("NEW");*/
				return mapping.findForward("DISCHARGESUMMARY");	
			}
			//_fb.setClinicalDocumentMode("CLINICALDOCUMENTS");		
			//return mapping.findForward("CLINICALDOCUMENTS");
	}
	
	
	
	public ActionForward VISITREASON(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		UniPagePrescriptionFB fb = (UniPagePrescriptionFB) form;
		UniPagePrescriptionUTL.getPatDetailOpp(request,response,fb);
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
	/* Added by Nilesh Gupta 17-oct-2017*/
	public ActionForward DISCHARGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("DISCHARGE");
	}
	public ActionForward REQUISITIONRAISING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("REQUISITIONRAISING");
	}
	
	
	public ActionForward OPDNEXTVISITDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("OPDNEXTVISITDETAIL");
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
	
	public ActionForward PATDETAILS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		//UniPagePrescriptionUTL.getPatDetailOpp(request,response);
		return null;
	}
	
	//Added by Vasu on 03.Dec.2018 for Patient demographic and Admission Details
	public ActionForward PATIENTDEMOGRAPHIC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("PATIENTDEMOGRAPHIC");
	}
	
	public ActionForward ADMISSIONDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("ADMISSIONDETAIL");
	}
	
	
		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		UniPagePrescriptionFB fb = (UniPagePrescriptionFB) form;
		//UniPagePrescriptionUTL.saveDetails(request,response,fb);
		//fb.reset(mapping, request);
		//fb.setNumberOfRow("1");
		//UniPagePrescriptionUTL.getEssentials(fb, request);
		//Status objStatus = new Status();
		//objStatus.add(Status.TRANSINPROCESS, "", "Record Saved Successfully");
		//request.setAttribute(Config.STATUS_OBJECT, objStatus);

		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		return null;
		//return mapping.findForward("NEW");
	}
		
		/*public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			UniPagePrescriptionFB _fb = (UniPagePrescriptionFB) form;
			UniPagePrescriptionUTL.setPatientDtlByCrno(request, response,_fb);
			return mapping.findForward("GETPATDTL");
		}
		
	public ActionForward GETUNIPAGEPRESCRIPTIONOPD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
			{
		System.out.println("mak::::111");
		return mapping.findForward("CLINICALDOCUMENTS");
	}
	*/
	
	

		public ActionForward CLINICALMRD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			UniPagePrescriptionFB _fb = (UniPagePrescriptionFB) form;
			HttpSession session = request.getSession();
			PatientDetailVO selectedPatientVO = null;
			String selPat= request.getParameter("selectedPatient");
			String arr[]= selPat.split("@");
			
			_fb.setPatCrNo(arr[0]);
			//UniPagePrescriptionUTL.setPatientDtlByCrno(request, response,_fb);
			UniPagePrescriptionUTL.getMRDPatientDtlByCrNo(request, response,_fb);
			selectedPatientVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			//hmode="GETPATDTL";
			 
			 	_fb.setEpisodeCode(selectedPatientVO.getEpisodeCode());
				_fb.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
				_fb.setDepartmentUnitCode(selectedPatientVO.getDepartmentUnitCode());
				_fb.setPatCrNo(selectedPatientVO.getPatCrNo());
			//	hmode="CLINICALDOCUMENTS";
			
			 
			// Map map= new HashMap();
			//map.put("dd",selectedPatientVO);	
			EHR_PatEncounterDetailsVO patencountervo = new 	EHR_PatEncounterDetailsVO();
			HelperMethods.populatetToNullOrEmpty(patencountervo, _fb);
			session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
			 
			UniPagePrescriptionUTL.getClinicalSectionCompositions(request,response,_fb);
			
				
			return mapping.findForward("CLINICALDOCUMENTS");
			//WebUTIL.refreshTransState(request);
			//	return mapping.findForward("NEW");
		}
			
		//Added by Vasu on 2.Nov.2018
		public ActionForward PREVIEWNGEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			HttpSession session = request.getSession();
			
			UniPagePrescriptionFB fb = (UniPagePrescriptionFB) form;
			//Configuration cfg =  HisFTLToHTML.prepareConfiguration(UniPagePrescriptionACTION.class,"/emr/dataview/spp/ftl");
			
			SimpleDateFormat _sdf1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
			SimpleDateFormat _sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat _sdf2 =new SimpleDateFormat("dd-MMM-yyyy HH:mm");
			String currDate =_sdf2.format(_sdf1.parse((String)request.getSession().getAttribute(Config.SYSDATE)));
			EHRVO voEHR  = EHRVOUtility.getEHRVO(request, fb.getPatCrNo());
			if(fb.getDocumentType().equals("17"))
			{	
			String deptName = voEHR.getVoPatientDtl().getDepartmentUnitName();
			String[] department = deptName.split("\\(");
			deptName = department[0];
			voEHR.setDepartment(deptName);
			}
			else
			{
				String deptName = voEHR.getVoPatientDtl().getDepartmentName();
				String[] department = deptName.split("\\(");
				deptName = department[0];
				voEHR.setDepartment(deptName);
			}
			
			voEHR.setCurrDateTime(currDate);
			String docHTML = "";
			// Generate Complete Doc HTML --------------
			//String docHTML = HisFTLToHTML.generateHTMLFromFTL(cfg,"spp_view_HDR_HOSPITAL_1.ftl","voEHR", voEHR);
			
			//Department Based Discharge Summary Added by Vasu on 29.Nov.18
			//if(voEHR.getVoPatientDtl().getDepartmentCode().equalsIgnoreCase("105")) //General Medicine
			//{
				//docHTML = FTLHelloWorld.getHtmlContentForGeneralMedicineDischargeSummary(request,voEHR);	
			//}
			//else if(voEHR.getVoPatientDtl().getDepartmentCode().equalsIgnoreCase("191"))//ENT //117-Orthopaedics
			//{
				//docHTML = FTLHelloWorld.getHtmlContentForOrthopaedicsDischargeSummary(request,voEHR);
			//}
			//else
			//{
			docHTML = FTLHelloWorld.getHtmlContentForDefaultDischargeSummary(request,voEHR); //Default
			//}
			/*ByteArrayOutputStream baosPDF = HTMLToPDFUTIL.convertHtmlToPDFDirect(request, docHTML);
			HisFileControlUtil hfcu = new HisFileControlUtil("abc.pdf", "F:\\", "/root/");
		    hfcu.setFileContent(baosPDF.toByteArray());
		    hfcu.saveFile();*/
		    
		    //PDF to byte array 
		     /*File file = new File("F:/abc.pdf");
		     //File file = new File("F:/sample_prescription.pdf");
	         FileInputStream fileInputStream;
	         byte[] data = null;
	         byte[] finalData = null;
	         ByteArrayOutputStream byteArrayOutputStream = null;
	        

	         try {
	            fileInputStream = new FileInputStream(file);
	            data = new byte[(int)file.length()];
	            finalData = new byte[(int)file.length()];
	            byteArrayOutputStream = new ByteArrayOutputStream();

	            fileInputStream.read(data);
	            byteArrayOutputStream.write(data);
	            finalData = byteArrayOutputStream.toByteArray();
	           
	            fileInputStream.close(); 

	        } catch (FileNotFoundException e) {
	            //LOGGER.info("File not found" + e);
	        } catch (IOException e) {
	            //LOGGER.info("IO exception" + e);
	        }*/
		    //End
		    
		   /* EHR_PatientProfileDtlVO profilevo = new EHR_PatientProfileDtlVO();
		    HelperMethods.populate(profilevo,voEHR.getVoPatientDtl());
		    profilevo.setProfileDataPdf(baosPDF.toByteArray());
		    profilevo.setHtmlPreview(docHTML);
		    //profilevo.setProfileDataPdf(finalData);
		    session.setAttribute(EHRConfig.EHR_SINGLE_PAGE_DISCHARGE_PAT_PROFILE_DTL_VO, profilevo);
		 
			fb.setHtmlPreview(docHTML);*/
			
			return mapping.findForward("PREVIEWNGEN");
		}
		
		//Added by Vasu on 03.Nov.2018
		public ActionForward SAVEDOCPDF(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			HttpSession session = request.getSession();
			UniPagePrescriptionFB fb = (UniPagePrescriptionFB) form;
			//Configuration cfg =  HisFTLToHTML.prepareConfiguration(UniPagePrescriptionACTION.class,"/emr/dataview/spp/ftl");
			String decoded = new String(Base64.decodeBase64(fb.getHtmlPreview().getBytes(StandardCharsets.UTF_8))); 
			String docHTML = decoded;//"";
			EHRVO voEHR  = EHRVOUtility.getEHRVO(request,fb.getPatCrNo());
			
			//docHTML = FTLHelloWorld.getHtmlContentForDischargeSummaryPrint(fb,request,voEHR); 
			
			ByteArrayOutputStream baosPDF = HTMLToPDFUTIL.convertHtmlToPDFDirect(request, docHTML);
			HisFileControlUtil hfcu = new HisFileControlUtil("abc.pdf", "F:\\", "/root/");
		    hfcu.setFileContent(baosPDF.toByteArray());
		    hfcu.saveFile();
		    
		    EHR_PatientProfileDtlVO profilevo = new EHR_PatientProfileDtlVO();
		    HelperMethods.populate(profilevo,voEHR.getVoPatientDtl());
		    profilevo.setProfileDataPdf(baosPDF.toByteArray());
		    profilevo.setHtmlPreview(docHTML);
		    //profilevo.setProfileDataPdf(finalData);
		    session.setAttribute(EHRConfig.EHR_SINGLE_PAGE_DISCHARGE_PAT_PROFILE_DTL_VO, profilevo);
		 
			fb.setHtmlPreview(docHTML);
			//UniPagePrescriptionUTL.savePatientProfileDtl(request,response,fb);
			
			
			if(UniPagePrescriptionUTL.savePatientProfileDtl(request,response,fb))
			{
				Status objStatus = new Status();
				UniPagePrescriptionUTL.getViewDischargeDoc(request,response,fb);
				objStatus.add(Status.TRANSINPROCESS, "Data Saved Successfully", "");
				request.setAttribute(Config.STATUS_OBJECT, objStatus);
			}
			
			// Generate Complete Doc HTML --------------
		/*	String docHTML = HisFTLToHTML.generateHTMLFromFTL(cfg,"spp_view_HDR_HOSPITAL_1.ftl","voEHR", voEHR);
			
			ByteArrayOutputStream baosPDF = HTMLToPDFUTIL.convertHtmlToPDFDirect(docHTML);
			HisFileControlUtil hfcu = new HisFileControlUtil("abc.pdf", "F:\\", "/root/");
		    hfcu.setFileContent(baosPDF.toByteArray());
		    hfcu.saveFile();

			fb.setHtmlPreview(docHTML);*/
				
			
			return mapping.findForward("NULL");
		}
		
		
		/*public ActionForward getViewDoc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			UniPagePrescriptionFB fb = (UniPagePrescriptionFB) form;
			//Configuration cfg =  HisFTLToHTML.prepareConfiguration(UniPagePrescriptionACTION.class,"/emr/dataview/spp/ftl");
			
			EHRVO voEHR  = EHRVOUtility.getEHRVO(request,fb.getPatCrNo());
			
			UniPagePrescriptionUTL.getViewDischargeDoc(request,response,fb);
		
			return mapping.findForward("NULL");
		}*/
		
		
		//Added by Vasu on 14.Aug.2018
		public ActionForward PREVIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			//String str = EHRSection_HEADER.getHeader(request);
			UniPagePrescriptionFB fb = (UniPagePrescriptionFB) form;
			/*Configuration cfg =  FTLHelloWorld.prepareConfiguration(UniPagePrescriptionACTION.class,"/emr/dataview/spp/ftl"); */
            
            EHRVO voEHR  = EHRVOUtility.getEHRVO(request,fb.getPatCrNo());
            
          
			ByteArrayOutputStream baosPDF = FTLHelloWorld.getByteArray(request,voEHR);
			
	            
	           String encodedString = new String(Base64.encodeBase64(baosPDF.toByteArray()));
			
			fb.setPdfFileIn(encodedString);
			System.out.println("PDF input stream : "+encodedString);
			
			return mapping.findForward("PREVIEW");
		}
		
		//Added by Dheeraj on 09-Oct-2018 to return PDF
		public ActionForward HTMLTOBASE64PDF(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			return mapping.findForward("PREVIEW");
		}
		
		public ActionForward HEADER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			//EHRSection_HEADERFB fb = (EHRSection_HEADERFB) form;
			
			UniPagePrescriptionFB fb = (UniPagePrescriptionFB) form;
			//String outPdf = fb.getPdfFileOut();
			fb.setFileType("21");
			
			try{
				UniPagePrescriptionUTL.saveDocument(fb, request);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return mapping.findForward("NEW");
			//return null;
		}
		
		
		public ActionForward VIEWPRESCRIPTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			HttpSession session = request.getSession();
			UniPagePrescriptionFB fb = (UniPagePrescriptionFB) form;
			PatientDetailVO selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			fb.setFileType("21");
			fb.setPatCrNo(selectedPatientVO.getPatCrNo());
			//fb.setVisitNo(selectedPatientVO.getSerialNo());
			try{
				UniPagePrescriptionUTL.viewPrescription(fb, request, response);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
			
		}
		
	/*Added by prachi on 14-may-2019 for chronic Disease Entry	*/
		
		
		public ActionForward CHRONICDISEASE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			
			return mapping.findForward("CHRONICDISEASE");
		}
		
		
	
		
/*Added by prachi on 15-may-2019 for allergy Entry	*/
		
		
		public ActionForward PATIENTALLERGY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			
			return mapping.findForward("PATIENTALLERGY");
		}
}	

	
