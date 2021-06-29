/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package emr.dataentry.spp.presentation.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import mrd.MrdConfig;
import mrd.transaction.controller.data.MRDDocumentUploadDATA;
import mrd.transaction.controller.data.PatImageUploadDATA;
import mrd.transaction.controller.fb.MRDDocumentUploadFB;
import mrd.vo.MRDDocumentUploadVO;
import opd.OpdConfig;
import registration.RegistrationConfig;
import registration.controller.fb.CRNoFB;
import ehr.EHRConfig;
import ehr.allergies.presentation.EHRSection_AllergiesDATA;
import ehr.chronicdisease.presentation.EHRSection_ChronicDiseaseDATA;
import ehr.examination.presentation.EHRSection_ExaminationDATA;
import ehr.history.presentation.EHRSection_HistoryDATA;
import ehr.investigation.presentation.EHRSection_InvestigationAdviceDATA;
import ehr.ot.presentation.EHRSection_OTDetailsData;
import ehr.patientreferral.presentation.EHRSection_PatientReferralDATA;
import ehr.serviceprocedure.presentation.EHRSection_ServiceProcedureDATA;
import ehr.vo.EHRVOUtility;
import emr.dataentry.spp.business.bo.UniPagePrescriptionBO;
import emr.dataentry.spp.presentation.data.UniPagePrescriptionDATA;
import emr.dataentry.spp.presentation.fb.UniPagePrescriptionFB;
import emr.datafetch.patientClinicalDocuments.presentation.data.PatientClinicalDocumentsDATA;
import emr.vo.PatientClinicalDocDetailVO;
import emr.vo.EHR_PatientProfileDtlVO;
import opd.bo.OpdPatientBO;

public class UniPagePrescriptionUTL extends ControllerUTIL {

	public static void getPatDetailOpp(HttpServletRequest request, HttpServletResponse response, UniPagePrescriptionFB fb) {
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);
		String flag1="1";
		PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		String crno = ptaientDetailVO.getPatCrNo();
		try{
		UniPagePrescriptionDATA.getPatDetailOpp(ptaientDetailVO,userVO);
		//HelperMethods.populate(ptaientDetailVO, fb);
		/*String json ="";
		json += ptaientDetailVO.getDepartmentUnitCode();
		json += ",";
		json+= ptaientDetailVO.getDepartmentUnitName();
		json+=",";
		json+=ptaientDetailVO.getVisitDate();
		json+=",";
		json+=ptaientDetailVO.getVisitReason();*/
		fb.setVisitReason(ptaientDetailVO.getVisitReason());
		fb.setVisitDate(ptaientDetailVO.getVisitDate());
		fb.setDeptUnit(ptaientDetailVO.getDepartmentUnitName());
		System.out.println(crno);
		UniPagePrescriptionUTL.getAdvisedByList(request,response);
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	public static void writeResponse(HttpServletResponse resp, String output)
			throws IOException {
		// //LOGGER_INV.log(Level.INFO," NewTemplateCreatorForTestGroupUTIL::writeResponse");

		try {
			resp.reset();
			resp.flushBuffer();
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "no-cache");
			resp.getWriter().write(output);
		} catch (Exception e) {
			// //LOGGER_INV.log(Level.INFO,e.getMessage());}
			e.printStackTrace();

		}
	}
	public static void getAdvisedByList(HttpServletRequest _request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		UserVO userVO = getUserVO(_request);
		setSysdate(_request);
		List advisedByList = new ArrayList();
		advisedByList=UniPagePrescriptionDATA.getAdvisedByList("123", userVO);
		WebUTIL.setAttributeInSession(_request, OpdConfig.ADVISED_BY_LIST,advisedByList);
		
	}
	
	public static void getClinicalSectionCompositions(HttpServletRequest request, HttpServletResponse response, UniPagePrescriptionFB fb) {
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);
		//PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		String crno = fb.getPatCrNo();
		PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();
		clinicalDocVO.setEpisodeCode(fb.getEpisodeCode());
		clinicalDocVO.setDeptUnitcode(fb.getDepartmentUnitCode());
		clinicalDocVO.setWardCode(fb.getWardCode());
		try
		{
	    HelperMethods.populate(clinicalDocVO,fb);
		if(request.getAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_KEY)!=null)
		{
			clinicalDocVO.setDocumentType((String)request.getAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_CODE));
		}			
		else if(clinicalDocVO.getDocumentType()== null || clinicalDocVO.getDocumentType().trim().equals(""))
	    {
	    	clinicalDocVO.setDocumentType("51");//"17");
	    	clinicalDocVO.setDocumentTitle("SINGLE PAGE PRESCRIPTION");//"Discharge Summary");
	    }
	    
	    /**Added by Vasu to get Clinical Sections Template Entry*/
	    List<PatientClinicalDocDetailVO> lstClinicalDocDetailVO= new ArrayList<PatientClinicalDocDetailVO>();
	    lstClinicalDocDetailVO=PatientClinicalDocumentsDATA.getClinicalSectionCompostionsList(clinicalDocVO,userVO);
	    
	    /**Added by Vasu on 07.Nov.2018 to get Clinical Sections Template Print*/
	    List<PatientClinicalDocDetailVO> lstClinicalDocTemplatePrintVO= new ArrayList<PatientClinicalDocDetailVO>();
	    lstClinicalDocTemplatePrintVO=PatientClinicalDocumentsDATA.getClinicalSectionTemplatePrintList(clinicalDocVO,userVO);
	   /* Gson gson = new Gson();
	    String json=gson.toJson(lstClinicalDocDetailVO);
	    fb.setClinicalSectionCompJSON(json);
		*/
	    fb.setDocumentType(clinicalDocVO.getDocumentType());
		WebUTIL.setAttributeInSession(request, EHRConfig.CLINICAL_SECTION_COMP_LIST, lstClinicalDocDetailVO);
		
		WebUTIL.setAttributeInSession(request, "compositionList", lstClinicalDocDetailVO);
		
		
		WebUTIL.setAttributeInSession(request, EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS, clinicalDocVO);
		
		EHRVOUtility.setClinicalDocDetailVO(request, fb.getPatCrNo(), lstClinicalDocTemplatePrintVO);
		
		/**Added by Vasu on 27.Nov.2018*/
		List<EpisodeVO> lstEpisodeVO= new ArrayList<EpisodeVO>();
		lstEpisodeVO=PatientClinicalDocumentsDATA.getEpisodeVisitDetails(clinicalDocVO,userVO);
		EHRVOUtility.setEpisodeDetailVO(request, fb.getPatCrNo(), lstEpisodeVO);
		
		
		//createJSONObject
		//String jsonSelect =  {"patCrNo":"","patDocId":"","patEncounterDtl":{},"patCompositionSelectData":{"ADVICEONDISCHARGE": {"list_selected":[], "html":{}} ,"TREATMENT":{"list_selected":[],"html":{}},"ENCDIAGNOSIS":{"list_selected":[],"html":{}},
	                          //"ENCINVESTIGATION":{"list_selected":[]}}};
		
		/**Added by Vasu on 03.May.2019 to get Clinical section data for SPP Print**/
		/*PatientDetailVO selectedPatientVO =null;
		selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		for(PatientClinicalDocDetailVO lstClinicalSectionsToPrintVO: lstClinicalDocTemplatePrintVO)
		{
			if(lstClinicalSectionsToPrintVO.getClinicalSecCompKey().equals("ENC_EXAM"))
			{
				EHRSection_ExaminationDATA.getExaminationData(userVO,selectedPatientVO);			
			}
			else if(lstClinicalSectionsToPrintVO.getClinicalSecCompKey().equals("ENC_OT"))
			{
				EHRSection_OTDetailsData.getOperationDetailsData(userVO,selectedPatientVO);
			}
			else if(lstClinicalSectionsToPrintVO.getClinicalSecCompKey().equals("ENC_INV"))//PROFILE_HEADER_SPP
			{
				EHRSection_InvestigationAdviceDATA.getInvestigationData(request,userVO,selectedPatientVO);
			}
			else if(lstClinicalSectionsToPrintVO.getClinicalSecCompKey().equals("PAT_ALLERGY"))//PAT_ALLERGIES
			{
				EHRSection_AllergiesDATA.getAllergiesData(request,userVO,selectedPatientVO);
			}
			else if(lstClinicalSectionsToPrintVO.getClinicalSecCompKey().equals("PAT_CHRONIC_DISEASE"))//PROFILE_HEADER_SPP
			{
				//EHRSection_ChronicDiseaseDATA.getChronicDiseaseData(request,userVO,selectedPatientVO);
			}
			else if(lstClinicalSectionsToPrintVO.getClinicalSecCompKey().equals("PAT_HISTORY"))//PAT_HISTORY
			{
				EHRSection_HistoryDATA.getTemplateDataForHistory(request,userVO,selectedPatientVO,clinicalDocVO);
			}
			else if(lstClinicalSectionsToPrintVO.getClinicalSecCompKey().equals("PROFILE_HEADER_SPP"))//PAT_REFERRAL
			{
				EHRSection_PatientReferralDATA.getPatientReferralData(request,userVO,selectedPatientVO,clinicalDocVO);
			}
			else if(lstClinicalSectionsToPrintVO.getClinicalSecCompKey().equals("SERVICE_PROCEDURE"))
			{
				EHRSection_ServiceProcedureDATA.getPatientServiceProcedureData(request,userVO,selectedPatientVO,clinicalDocVO);
			}
		}*/
		
	    }
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void setPatientDtlByCrno(HttpServletRequest request, HttpServletResponse response, UniPagePrescriptionFB _fb) {
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);
		
		System.out.println("mak::::8");
		Status status = new Status();
		EpisodeVO[] arrOpenEpisodeVO;
		EpisodeVO admittedPatientVO;
		try {
		//PatientVO objPatientVO=new PatientVO();
			PatientDetailVO  objPatientVO=new PatientDetailVO();
		objPatientVO.setPatCrNo(_fb.getPatCrNo());
		
		CRNoFB crnoFB = new CRNoFB();
		crnoFB.setPatCrNo(_fb.getPatCrNo());
		
		//objPatientVO=ChangeTreatmentCategoryDATA.getPatientDtl(objPatientVO,userVO);
		//PatDetailUTIL.getPatientDtlByCrno(crnoFB, request);
		//objPatientVO=(PatientVO)request.getSession().getAttribute(OpdConfig.PATIENT_VO);
		
		
		InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, request);
		
		PatientDetailVO voDP = null;
		 objPatientVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

		 
		String crNo=objPatientVO.getPatCrNo();
		//if(_fb.getHmode().equals("GETPATDTL"))
		{
			//if(objPatientVO.getPatStatusCode().equals(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED) || objPatientVO.getPatStatusCode().equals(InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE))
			if (objPatientVO!=null)
			_fb.setIsIpdFlag(OpdConfig.YES);
			else
				_fb.setIsIpdFlag(OpdConfig.NO);
		}
		
		if(_fb.getIsIpdFlag().equals(OpdConfig.YES))
			{
			admittedPatientVO=UniPagePrescriptionDATA.getPatientAdmittedEpisodes(crNo,userVO);
			//admittedPatientVO.setPatPrimaryCatCode(objPatientVO.getPatCatCode());
			WebUTIL.setAttributeInSession(request,RegistrationConfig.ADMITTED_PATIENT_VO,admittedPatientVO);
			}
		//else
		///{
			arrOpenEpisodeVO=UniPagePrescriptionDATA.getAllOpenEpisodesVisitedToday(crNo, userVO);
			for(int i=0;i<arrOpenEpisodeVO.length;i++){
			if(arrOpenEpisodeVO[i].getPatSecondaryCatCode()==null)
				arrOpenEpisodeVO[i].setPatSecondaryCatCode("-1");
			}										
			WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_OPEN_EPISODE_VO,arrOpenEpisodeVO);
		//}
		String ipdFlag =_fb.getIsIpdFlag();
		WebUTIL.setAttributeInSession(request,OpdConfig.IPD_FLAG,ipdFlag);
		
		_fb.setGoFlag("1");
		_fb.setAfterGo("1");
	
		_fb.setGoFlag("1");
		_fb.setAfterGo("1");
		if(_fb.getGoFlag().equals("1"))
		{
		//populateformvalues();
	
		}
	} catch (HisRecordNotFoundException e) {
		_fb.setGoFlag("0");
		_fb.setErrorMessage(e.getMessage());
		status.add(Status.NEW, e.getMessage(), "");
	} catch (HisDataAccessException e) {
		_fb.setGoFlag("0");
		status.add(Status.ERROR_DA, e.getMessage(), "");
	} catch (HisApplicationExecutionException e) {
		_fb.setGoFlag("0");
		status.add(Status.ERROR_AE, e.getMessage(), "");
		throw new HisApplicationExecutionException();
	} catch (HisException e) {
		_fb.setGoFlag("0");
		status.add(Status.ERROR, e.getMessage(), "");
		throw new HisException();
	} finally {
		WebUTIL.setStatus(request, status);
	}
	}	
		
		public static void getMRDPatientDtlByCrNo(HttpServletRequest request, HttpServletResponse response, UniPagePrescriptionFB _fb) {
			Status objStatus = new Status();
			HttpSession session = request.getSession();
			UserVO userVO = getUserVO(request);
			
			System.out.println("mak::::8");
			Status status = new Status();
			EpisodeVO[] arrOpenEpisodeVO;
			EpisodeVO admittedPatientVO;
			try {
			//PatientVO objPatientVO=new PatientVO();
				PatientDetailVO  objPatientVO=new PatientDetailVO();
			objPatientVO.setPatCrNo(_fb.getPatCrNo());
			
			CRNoFB crnoFB = new CRNoFB();
			crnoFB.setPatCrNo(_fb.getPatCrNo());
			
			//objPatientVO=ChangeTreatmentCategoryDATA.getPatientDtl(objPatientVO,userVO);
			//PatDetailUTIL.getPatientDtlByCrno(crnoFB, request);
			//objPatientVO=(PatientVO)request.getSession().getAttribute(OpdConfig.PATIENT_VO);
			
			
			//InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, request);
			InpatientDetailUTL.getMRDPatientDtlByCrNo(crnoFB, request);
			String crNo=objPatientVO.getPatCrNo();
			PatientDetailVO voDP = null;
			 objPatientVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

			 
			
			//if(_fb.getHmode().equals("GETPATDTL"))
			{
				//if(objPatientVO.getPatStatusCode().equals(InpatientConfig.PATIENT_ADMISSION_STATUS_ADMITTED) || objPatientVO.getPatStatusCode().equals(InpatientConfig.PATIENT_ADMISSION_STATUS_DISCHARGE))
				if (objPatientVO!=null)
				_fb.setIsIpdFlag(OpdConfig.YES);
				else
					_fb.setIsIpdFlag(OpdConfig.NO);
			}
			
			if(_fb.getIsIpdFlag().equals(OpdConfig.YES))
				{
			//	admittedPatientVO=UniPagePrescriptionDATA.getPatientAdmittedEpisodes(crNo,userVO);
				admittedPatientVO=UniPagePrescriptionDATA.getPatientAdmittedEpisodesMRD(crNo,userVO);
				//admittedPatientVO.setPatPrimaryCatCode(objPatientVO.getPatCatCode());
				WebUTIL.setAttributeInSession(request,RegistrationConfig.ADMITTED_PATIENT_VO,admittedPatientVO);
				}
			//else
			///{
				arrOpenEpisodeVO=UniPagePrescriptionDATA.getAllOpenEpisodesVisitedToday(crNo, userVO);
				for(int i=0;i<arrOpenEpisodeVO.length;i++){
				if(arrOpenEpisodeVO[i].getPatSecondaryCatCode()==null)
					arrOpenEpisodeVO[i].setPatSecondaryCatCode("-1");
				}										
				WebUTIL.setAttributeInSession(request,RegistrationConfig.ARR_OPEN_EPISODE_VO,arrOpenEpisodeVO);
			//}
			String ipdFlag =_fb.getIsIpdFlag();
			WebUTIL.setAttributeInSession(request,OpdConfig.IPD_FLAG,ipdFlag);
			
			_fb.setGoFlag("1");
			_fb.setAfterGo("1");
		
			_fb.setGoFlag("1");
			_fb.setAfterGo("1");
			if(_fb.getGoFlag().equals("1"))
			{
			//populateformvalues();
		
			}
		} catch (HisRecordNotFoundException e) {
			_fb.setGoFlag("0");
			_fb.setErrorMessage(e.getMessage());
			status.add(Status.NEW, e.getMessage(), "");
		} catch (HisDataAccessException e) {
			_fb.setGoFlag("0");
			status.add(Status.ERROR_DA, e.getMessage(), "");
		} catch (HisApplicationExecutionException e) {
			_fb.setGoFlag("0");
			status.add(Status.ERROR_AE, e.getMessage(), "");
			throw new HisApplicationExecutionException();
		} catch (HisException e) {
			_fb.setGoFlag("0");
			status.add(Status.ERROR, e.getMessage(), "");
			throw new HisException();
		} finally {
			WebUTIL.setStatus(request, status);
		}
	
	}
	
		//Added by Dheeraj on 15-Oct-2018 to save Digitally Signed Prescription
		
		public static boolean saveDocument(UniPagePrescriptionFB fb, HttpServletRequest request)
		{
			boolean flgSave = true;
			Status objStatus = new Status();
			PatientClinicalDocDetailVO patientPrescriptionUploadVOs = null;
			HttpSession session = WebUTIL.getSession(request);
			
			try
			{
				
				PatientDetailVO selectedPatientVO = null;
				selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
					

				String sysdate = (String) session.getAttribute(Config.SYSDATE);
				
				byte[] fileArray = Base64.decodeBase64(fb.getPdfFileOut());
					String strUploadDocName = fb.getPatCrNo()+"_"+fb.getEpisodeCode()+"_"+fb.getEpisodeVisitNo();

					
					patientPrescriptionUploadVOs = new PatientClinicalDocDetailVO();
					patientPrescriptionUploadVOs.setPatCrNo(fb.getPatCrNo());
					patientPrescriptionUploadVOs.setSeatId(selectedPatientVO.getSeatId());
					patientPrescriptionUploadVOs.setEpisodeCode(fb.getEpisodeCode());
					patientPrescriptionUploadVOs.setVisitNo(fb.getEpisodeVisitNo());
					patientPrescriptionUploadVOs.setDocumentTitle(selectedPatientVO.getPatDocType());
					patientPrescriptionUploadVOs.setFileName(strUploadDocName);//fileName);
					patientPrescriptionUploadVOs.setDocData(fileArray);
					patientPrescriptionUploadVOs.setEntryDate(sysdate);
					patientPrescriptionUploadVOs.setHospCode(fb.getHospitalCode());  
					patientPrescriptionUploadVOs.setFileType(fb.getFileType());
					
					patientPrescriptionUploadVOs.setIsValid(Config.IS_VALID_ACTIVE);
					
				UniPagePrescriptionBO.savePatientPrescriptiondetails(patientPrescriptionUploadVOs, getUserVO(request));  
				objStatus.add(Status.TRANSINPROCESS, "Document Successfully Uploaded", "");
			}
			catch (HisRecordNotFoundException e)
			{
				flgSave = false;
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			}
			catch (HisDataAccessException e)
			{
				flgSave = false;
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (HisApplicationExecutionException e)
			{
				flgSave = false;
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (HisException e)
			{
				flgSave = false;
				objStatus.add(Status.ERROR, e.getMessage(), "");
				e.printStackTrace();
			}
			catch (Exception e)
			{
				flgSave = false;
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
				e.printStackTrace();
			}
			finally
			{
				WebUTIL.setStatus(request, objStatus);
			}
			return flgSave;
		}
		
		
		
		public static void getPrescriptionDtl(HttpServletRequest request, HttpServletResponse response, UniPagePrescriptionFB fb) {
			Status objStatus = new Status();
			HttpSession session = request.getSession();
			UserVO userVO = getUserVO(request);
			//PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			String crno = fb.getPatCrNo();
			PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();
		
			try
			{
		    HelperMethods.populate(clinicalDocVO,fb);
			
		    	clinicalDocVO.setFileType("21");//"17");
		    
		    List<PatientClinicalDocDetailVO> previousPrescriptions= new ArrayList<PatientClinicalDocDetailVO>();
		    previousPrescriptions=UniPagePrescriptionBO.getPrecsriptionDtl(clinicalDocVO,userVO);

		    WebUTIL.setAttributeInSession(request, EHRConfig.PREVIOUS_PRESCRIPTIONS, previousPrescriptions);
			
			
			
			
		    }
			catch (HisDataAccessException e)
			{
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			}
			catch (HisApplicationExecutionException e)
			{
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			catch (HisException e)
			{
				objStatus.add(Status.ERROR, e.getMessage(), "");
			}
			catch (Exception e)
			{
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			finally
			{
				WebUTIL.setStatus(request, objStatus);
			}
		}
		


		//Added by Dheeraj on 17-Oct-2018 to get List of Prescription PDFs from Postgres
		
		public static void getViewDoc(UniPagePrescriptionFB fb,	HttpServletRequest request, HttpServletResponse response_p) 
		{
			Status objStatus = new Status();
			InputStream inputStream = null;
			BufferedOutputStream bos = null;
			PatientClinicalDocDetailVO patientPrescriptionUploadVO = null;
			List patientDocDetailVO=null;
			List byteArray=new ArrayList();
			
			try
			{
			
				patientDocDetailVO=UniPagePrescriptionBO.patientDocDetailVO(fb.getPatCrNo(),getUserVO(request));
				
				
					byte[] getDoc = UniPagePrescriptionBO.fetchImageFromPostgres(patientPrescriptionUploadVO); 
					
					
					OutputStream os = response_p.getOutputStream();
					bos = new BufferedOutputStream(os);
					inputStream = new ByteArrayInputStream(getDoc);
					int c;
					while ((c = inputStream.read()) != -1)
					{
						os.write(c);
					}    
					
					
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				objStatus.add(Status.LIST, e.getMessage(), "");
			}
			catch (HisDataAccessException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			}
			catch (HisApplicationExecutionException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			catch (HisException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR, e.getMessage(), "");
			}
			/*catch (Exception e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}*/
			catch (Exception e)
			{
				e.printStackTrace();
				String msg="<B>This file does not exist:: Please Contact Administrator</B>";
				byte[] bytes=msg.getBytes();
				response_p.setHeader("Pragma", "no-cache");
				try
				{
					bos.write(bytes, 0, bytes.length);
				}catch(Exception ee)
				{
				}
				
			}
			finally
			{
				/*WebUTIL.setStatus(request, objStatus);*/
				try
				{
					if(inputStream!=null) inputStream.close();
					response_p.getOutputStream().flush();
					if(bos!=null)	bos.close();
				}
				catch(Exception e)
				{
				}
			}
			
		}

		//Added by Dheeraj on 22-Oct-2018 to get Prescription PDF from Postgres
		
		public static void viewPrescription(UniPagePrescriptionFB fb,	HttpServletRequest request, HttpServletResponse response_p) 
		{
			Status objStatus = new Status();
			InputStream inputStream = null;
			BufferedOutputStream bos = null;
			
			try
			{
			
					byte[] getDoc = UniPagePrescriptionBO.fetchPrescriptionFromPostgres(fb); 
					
					response_p.setContentType("application/pdf");
			        
					OutputStream os = response_p.getOutputStream();
					bos = new BufferedOutputStream(os);
					inputStream = new ByteArrayInputStream(getDoc);
					int c;
					while ((c = inputStream.read()) != -1)
					{
						os.write(c);
					}    
					
			}
			catch (HisRecordNotFoundException e)
			{
				e.printStackTrace();
				objStatus.add(Status.LIST, e.getMessage(), "");
			}
			catch (HisDataAccessException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			}
			catch (HisApplicationExecutionException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			catch (HisException e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR, e.getMessage(), "");
			}
			/*catch (Exception e)
			{
				e.printStackTrace();
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}*/
			catch (Exception e)
			{
				e.printStackTrace();
				String msg="<B>This file does not exist:: Please Contact Administrator</B>";
				byte[] bytes=msg.getBytes();
				response_p.setHeader("Pragma", "no-cache");
				try
				{
					bos.write(bytes, 0, bytes.length);
				}catch(Exception ee)
				{
				}
				
			}
			finally
			{
				/*WebUTIL.setStatus(request, objStatus);*/
				try
				{
					if(inputStream!=null) inputStream.close();
					response_p.getOutputStream().flush();
					if(bos!=null)	bos.close();
				}
				catch(Exception e)
				{
				}
			}
		}
	 
		//Added by Vasu on 05.Nov.2018
		public static boolean savePatientProfileDtl(HttpServletRequest request, HttpServletResponse response, UniPagePrescriptionFB fb) {
			boolean isSave = true;
			
			Status objStatus = new Status();
			HttpSession session = request.getSession();
			UserVO userVO = getUserVO(request);
			//PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			String crno = fb.getPatCrNo();
			EHR_PatientProfileDtlVO profileDtlVO = new EHR_PatientProfileDtlVO();
			String profileId = "";
			try
			{
				
				PatientDetailVO selectedPatientVO =null;
				profileDtlVO = (EHR_PatientProfileDtlVO) session.getAttribute(EHRConfig.EHR_SINGLE_PAGE_DISCHARGE_PAT_PROFILE_DTL_VO);
				//profileDtlVO.setHtmlPreview(fb.getHtmlPreview());
				selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				profileDtlVO.setAdmissionNo(selectedPatientVO.getPatAdmNo());
				//profileDtlVO.setProfileHeader(profileHeader);
				profileDtlVO.setIsSinglePageFlag("1");
				if(fb.getDocumentType().equals("51"))
				{
					profileDtlVO.setProfileHeader("OPD Prescription");
					profileDtlVO.setProfileType("21");
				}
				else
				{
					profileDtlVO.setProfileHeader("Discharge Summary");
					profileDtlVO.setProfileType("16");
				}
					
				profileId = OpdPatientBO.savePatientProfileDtl(profileDtlVO,userVO);
				fb.setProfileId(profileId);
					
		    }
			catch (HisDataAccessException e)
			{
				isSave = false;
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			}
			catch (HisApplicationExecutionException e)
			{
				isSave = false;
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			catch (HisException e)
			{
				isSave = false;
				objStatus.add(Status.ERROR, e.getMessage(), "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
				isSave = false;
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			finally
			{
				WebUTIL.setStatus(request, objStatus);
			}
			return isSave;
		}
		
		//Added by Vasu on 13.Nov.2018
		public static void getViewDischargeDoc(HttpServletRequest request, HttpServletResponse response, UniPagePrescriptionFB fb) {
			Status objStatus = new Status();
			InputStream inputStream = null;
			BufferedOutputStream bos = null;
			HttpSession session = request.getSession();
			UserVO userVO = getUserVO(request);
			String crno = fb.getPatCrNo();
			EHR_PatientProfileDtlVO profileDtlVO = new EHR_PatientProfileDtlVO();
		    
			try
			{
		    //HelperMethods.populate(profileDtlVO,fb);
			
			profileDtlVO = (EHR_PatientProfileDtlVO) session.getAttribute(EHRConfig.EHR_SINGLE_PAGE_DISCHARGE_PAT_PROFILE_DTL_VO);
			profileDtlVO.setProfileId(fb.getProfileId());
			
			
			byte[] getDoc = UniPagePrescriptionBO.fetchPDFFromPostgres(profileDtlVO); 
			
			//byte[] getDoc =  profileDtlVO.getProfileDataPdf();
			response.setContentType("application/pdf");
			OutputStream os = response.getOutputStream();
			bos = new BufferedOutputStream(os);
			inputStream = new ByteArrayInputStream(getDoc);
			int c;
			while ((c = inputStream.read()) != -1)
			{
				os.write(c);
			}    
			
			
	}
	catch (HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.LIST, e.getMessage(), "");
	}
	catch (HisDataAccessException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR_DA, e.getMessage(), "");
	}
	catch (HisApplicationExecutionException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR_AE, e.getMessage(), "");
	}
	catch (HisException e)
	{
		e.printStackTrace();
		objStatus.add(Status.ERROR, e.getMessage(), "");
	}
	
	catch (Exception e)
	{
		e.printStackTrace();
		String msg="<B>This file does not exist:: Please Contact Administrator</B>";
		byte[] bytes=msg.getBytes();
		response.setHeader("Pragma", "no-cache");
		try
		{
			bos.write(bytes, 0, bytes.length);
		}catch(Exception ee)
		{
		}
		
	}
	finally
	{
		try
		{
			if(inputStream!=null) inputStream.close();
			response.getOutputStream().flush();
			if(bos!=null)	bos.close();
		}
		catch(Exception e)
		{
		}
	}
	
}

		//Added by Vasu on 11.March.2019
		public static void getPreviousDischargeProfiles(HttpServletRequest request, HttpServletResponse response, UniPagePrescriptionFB fb) {
			Status objStatus = new Status();
			HttpSession session = request.getSession();
			UserVO userVO = getUserVO(request);
			//PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			String crno = fb.getPatCrNo();
			PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();
			EHR_PatientProfileDtlVO profileDtlVO = new EHR_PatientProfileDtlVO();
			try
			{
				
			PatientDetailVO selectedPatientVO =null;
			profileDtlVO = (EHR_PatientProfileDtlVO) session.getAttribute(EHRConfig.EHR_SINGLE_PAGE_DISCHARGE_PAT_PROFILE_DTL_VO);
			
			
			
			
		    List<PatientClinicalDocDetailVO> previousPrescriptions= new ArrayList<PatientClinicalDocDetailVO>();
		    previousPrescriptions=UniPagePrescriptionBO.getPrecsriptionDtl(clinicalDocVO,userVO);

		    WebUTIL.setAttributeInSession(request, EHRConfig.PREVIOUS_PRESCRIPTIONS, previousPrescriptions);
			
			
			
			
		    }
			catch (HisDataAccessException e)
			{
				objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			}
			catch (HisApplicationExecutionException e)
			{
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			catch (HisException e)
			{
				objStatus.add(Status.ERROR, e.getMessage(), "");
			}
			catch (Exception e)
			{
				objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			}
			finally
			{
				WebUTIL.setStatus(request, objStatus);
			}
		}
	
}
