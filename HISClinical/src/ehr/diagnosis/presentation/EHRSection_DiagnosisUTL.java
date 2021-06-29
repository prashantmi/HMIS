/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.diagnosis.presentation;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import registration.RegistrationConfig;
import ehr.EHRConfig;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.vo.EHRVOUtility;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import emr.vo.EHR_PatEncounterDetailsVO;
import hisglobal.vo.PatientDetailVO;
import ehr.vo.EHRVO;

public class EHRSection_DiagnosisUTL extends ControllerUTIL
{
	
	public static void getEssentials(EHRSection_DiagnosisFB _fb, HttpServletRequest _rq,HttpServletResponse _rs)
	{
		Status objStatus = new Status();
		Map essentialMap = new HashMap();
		HttpSession session = WebUTIL.getSession(_rq);
		EHRVO ehrVO = new EHRVO(); 
		try
		{
		
			String[] strArray = new String[1];
			strArray[0] = "";
		    _fb.setSnomedCTDiagnosisCode(strArray);
			_fb.setSnomedCTDiagnosisName(strArray); 
			_fb.setRemarks(strArray);
			_fb.setDiagonisticTypeCode(strArray);
			_fb.setDiagnosisSite(strArray);
			_fb.setDiagnosisSiteLabel(strArray);
			_fb.setSnomedCTDiagnosisSiteName(strArray); 
			_fb.setSnomedCTDiagnosisSiteCode(strArray); 
			_fb.setSnomedPTRemarks(strArray); 
			_fb.setSnomedCIdRemarks(strArray); 
			_fb.setUnitDiagnosisCodeType(OpdConfig.CHOICE_SNOMEDCT_CODE); 
			_fb.setDiagnosisRecordStatus(strArray); 
			_fb.setSnomedCTICDCode(strArray); 
			// Fetching Patient Detail from Desk
			PatientDetailVO selectedPatientVO = new PatientDetailVO();
			HelperMethods.populate(selectedPatientVO, _fb);
		
			if(_fb.getEpisodeCode()==null)
			{
			selectedPatientVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			_fb.setEpisodeCode(selectedPatientVO.getEpisodeCode());
			_fb.setAdmissionNo(selectedPatientVO.getPatAdmNo());
			_fb.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
			_fb.setDepartmentUnitCode(selectedPatientVO.getDepartmentUnitCode());
			
			}
		
			if(selectedPatientVO!=null)
			{
				essentialMap = EHRSection_DiagnosisLNK.getSnomdDiagnosisEssential(selectedPatientVO, getUserVO(_rq));	
			}
			WebUTIL.setMapInSession(essentialMap, _rq);
			
			EHRSection_DiagnosisVO[] arrayPreviousDiagVO = (EHRSection_DiagnosisVO[]) essentialMap.get(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO);
			WebUTIL.getSession(_rq).setAttribute(OpdConfig.Latest_DIAGNOSIS_VO, arrayPreviousDiagVO);
			
			
			
			if(arrayPreviousDiagVO.length>0)
			{
				_fb.setIsSetDIGANOSIS("1");
				//Map Map1 = new HashMap();
				//Map map1=(HashMap) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
				//map1.put(EHRConfig.EHR_DIAGNOSIS_ESSENTIAL_SAVE, arrayPreviousDiagVO);
				//session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, map1);
				
				
				EHRSection_DiagnosisVO[] values = arrayPreviousDiagVO; //converting DIagnosisVOarray to list<DiagnosisVO>
				List<EHRSection_DiagnosisVO> allSaveEssentiallist = new ArrayList<EHRSection_DiagnosisVO>(Arrays.asList(values));
				
				EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
				patencountervo.setListSaveAllDiagnosis(allSaveEssentiallist);
				WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
			
				EHRVOUtility.setDiagnosisDetailVO(_rq, _fb.getPatCrNo(),allSaveEssentiallist ); 
				objStatus.add(Status.TRANSINPROCESS);
			}//else
				//objStatus.add(Status.TRANSINPROCESS,"","No Previous Diagnosis Found");
		//	generateComboOption(_fb,_rq);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			essentialMap = e.getEssentialMap();
			WebUTIL.setMapInSession(essentialMap, _rq);
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
			WebUTIL.setStatus(_rq, objStatus);
		}
	}
	
	public static void generateComboOption(EHRSection_DiagnosisFB _fb, HttpServletRequest _rq)
	{
		HttpSession session = _rq.getSession();
		List diagnosisTypeList = (List) session.getAttribute(RegistrationConfig.DIAGNOSIS_LIST);
		String resp = "";
		if(diagnosisTypeList!=null)
		{
			Iterator itr = diagnosisTypeList.iterator();
	
			while (itr.hasNext())
			{
				Entry objEntry = (Entry) itr.next();
				resp = resp + "<option value='" + objEntry.getValue() + "' "+ ((objEntry.getValue().equals(OpdConfig.DIAGNOSIS_TYPE_PROVISIONAL))?"selected='selected'":"") +">" + objEntry.getLabel() + "</option>";
								
			}
			_fb.setComboOptionString(resp);
		}
		
		
	}
	
	public static void saveDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_DiagnosisFB _fb) 
	{
		//String flag=EHRSection_DiagnosisUTL.saveOPPDiagnosis(_rq,response,_fb);
		
		try
		{
			String flag=EHRSection_DiagnosisUTL.saveOPPDiagnosis(_rq,response,_fb);
			//writeResponse(response, flag);
		}
		catch (Exception e) {
				e.printStackTrace();
			
		}
		
		
		
	}
	
	public static String saveOPPDiagnosis(HttpServletRequest _rq,HttpServletResponse response, EHRSection_DiagnosisFB _fb) 
	{
		
		String isSave = "true";
		Status objStatus = new Status();
		DailyPatientVO selectedPatientVO = new DailyPatientVO();
		List<EHRSection_DiagnosisVO> addEpisodeDiag= new ArrayList<EHRSection_DiagnosisVO>();
		List<EHRSection_DiagnosisVO> revokedEpisodeDiag= new ArrayList<EHRSection_DiagnosisVO>();
		List<EHRSection_DiagnosisVO> allSaveListEpisodeDiag= new ArrayList<EHRSection_DiagnosisVO>();
		int currentDiagnosisLength = 0;
		
		try
		{

			HelperMethods.populatetToNullOrEmpty(selectedPatientVO, _fb);
			
			currentDiagnosisLength=_fb.getDiagnosisRecordStatus().length;
			
			for (int k = 0; k < currentDiagnosisLength; k++)
			{
				if(!_fb.getSnomedCTDiagnosisCode()[k].isEmpty())
				{
					
				
				if(_fb.getDiagnosisRecordStatus()[k].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_NOT_SAVED))
				{
					
					    EHRSection_DiagnosisVO addepisodeDiaVO = new EHRSection_DiagnosisVO();
						addepisodeDiaVO.setDiagnosticCode(_fb.getSnomedCTDiagnosisCode()[k]); 
						addepisodeDiaVO.setDiagnosticTypeCode(_fb.getDiagonisticTypeCode()[k]);
						addepisodeDiaVO.setDiagnosticTypeName(_fb.getDiagnosticTypeName()[k]);
						addepisodeDiaVO.setRemarks(_fb.getRemarks()[k]);
						//addepisodeDiaVO.setDiagnosisSite(_fb.getSnomedCTDiagnosisSiteCode()[k]);
						//addepisodeDiaVO.setDiagnosisSiteLabel(_fb.getSnomedCTDiagnosisSiteName()[k]);
						addepisodeDiaVO.setDiagnosisSite("");
						addepisodeDiaVO.setDiagnosisSiteLabel("");
						
						if(!_fb.getSnomedCTDiagnosisCode()[k].equalsIgnoreCase(_fb.getSnomedCTDiagnosisName()[k]))
							addepisodeDiaVO.setDiagnosisCodeType(OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED);
						
						else
							addepisodeDiaVO.setDiagnosisCodeType(OpdConfig.DIAGNOSIS_CODE_TYPE_OTHER);
						
						
						addepisodeDiaVO.setDignosisName(_fb.getSnomedCTDiagnosisName()[k]); 
						HelperMethods.populatetToNullOrEmpty(addepisodeDiaVO, selectedPatientVO);
						addepisodeDiaVO.setIsRepeat(OpdConfig.DIAGNOSIS_IS_REPEAT_NEW);
						addepisodeDiaVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
						addepisodeDiaVO.setSnomedCTICDCode(_fb.getSnomedCTICDCode()[k]);
						
						addEpisodeDiag.add(addepisodeDiaVO);
						
				}
				
				if(_fb.getDiagnosisRecordStatus()[k].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_TOREVOKE))
				{	
						EHRSection_DiagnosisVO episodeRevokeDiaVO=new EHRSection_DiagnosisVO();
						HelperMethods.populatetToNullOrEmpty(episodeRevokeDiaVO, selectedPatientVO);
						episodeRevokeDiaVO.setDiagnosticCode(_fb.getSnomedCTDiagnosisCode()[k]);
						
						if(!_fb.getSnomedCTDiagnosisCode()[k].equalsIgnoreCase(_fb.getSnomedCTDiagnosisName()[k]))
							episodeRevokeDiaVO.setDiagnosisCodeType(OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED);
						else
							episodeRevokeDiaVO.setDiagnosisCodeType(OpdConfig.DIAGNOSIS_CODE_TYPE_OTHER);
						
						revokedEpisodeDiag.add(episodeRevokeDiaVO);
						
						EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
						patencountervo.setListSaveAllDiagnosis(allSaveListEpisodeDiag);
						WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
				}
				
				if(_fb.getDiagnosisRecordStatus()[k].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_SAVED))
				{
				
						EHRSection_DiagnosisVO allSaveepisodeDiaVO = new EHRSection_DiagnosisVO();
						allSaveepisodeDiaVO.setDiagnosticCode(_fb.getSnomedCTDiagnosisCode()[k]); 
						allSaveepisodeDiaVO.setDiagnosticTypeCode(_fb.getDiagonisticTypeCode()[k]);
						allSaveepisodeDiaVO.setDiagnosticTypeName(_fb.getDiagnosticTypeName()[k]);
						allSaveepisodeDiaVO.setRemarks(_fb.getRemarks()[k]);
						//allSaveepisodeDiaVO.setDiagnosisSite(_fb.getSnomedCTDiagnosisSiteCode()[k]);
						//allSaveepisodeDiaVO.setDiagnosisSiteLabel(_fb.getSnomedCTDiagnosisSiteName()[k]);
						
						allSaveepisodeDiaVO.setDiagnosisSite("");
						allSaveepisodeDiaVO.setDiagnosisSiteLabel("");
						
						allSaveepisodeDiaVO.setDignosisName(_fb.getSnomedCTDiagnosisName()[k]); 
						allSaveepisodeDiaVO.setSnomedCTICDCode(_fb.getSnomedCTICDCode()[k]);
						allSaveepisodeDiaVO.setSnomedCTICDCode("");
						
						allSaveListEpisodeDiag.add(allSaveepisodeDiaVO);
						
				}
			}
			}
				
			EHRSection_DiagnosisLNK.saveOpdDiagnosisDetails(addEpisodeDiag,revokedEpisodeDiag, getUserVO(_rq));
			allSaveListEpisodeDiag.addAll(addEpisodeDiag); 
			
			//Map essentialMap=(HashMap) session.getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			//essentialMap.put(EHRConfig.EHR_DIAGNOSIS_SAVE, allSaveListEpisodeDiag);
			//session.setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, essentialMap);
			
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			patencountervo.setListSaveAllDiagnosis(allSaveListEpisodeDiag);
			WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO, patencountervo);
		
			
     			
			objStatus.add(Status.DONE, "Record Saved", "");
		

		}
		catch (HisDataAccessException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR, e.getMessage(), "");
			System.out.print(e.getMessage());
		}
		catch (Exception e)
		{
			isSave = "false";
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return isSave;
		
	}
	
	public static void writeResponse(HttpServletResponse resp, String output)
			throws IOException {
		
		try {
			resp.reset();
			resp.flushBuffer();
			resp.setContentType("application/json");
			resp.setHeader("Cache-Control", "no-cache");
			resp.getWriter().write(output);
		} catch (Exception e) {
						e.printStackTrace();

		}
	}
	
	//  Added by VASU on 25-Sept-2017 for Getting Patient Diagnosis Details
	public static boolean getPatientEncDiagnosisDetail(EHRSection_DiagnosisFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			EHR_PatientEncounterVO _voEHREncounter = new EHR_PatientEncounterVO();
			EHR_AccessPermissionVO _voEHRAccess = new EHR_AccessPermissionVO();
			EHRSection_DiagnosisVO _voEHRDiagnosis = new EHRSection_DiagnosisVO();
			HelperMethods.populate(_voEHREncounter,_fb);
			HelperMethods.populate(_voEHRAccess,_fb);
			//HelperMethods.populate(_voEHRDiagnosis,_fb);
			HelperMethods.populate(_voEHREncounter,userVO);
			
			_voEHRDiagnosis.setEpisodeCode(_fb.getEpisodeCode());
			_voEHRDiagnosis.setPatCrNo(_fb.getPatCrNo());
			
			List<EHRSection_DiagnosisVO> lstPatEncDiagnosis= new ArrayList<EHRSection_DiagnosisVO>();
			
			lstPatEncDiagnosis = EHRSection_DiagnosisLNK.getPatientEncDiagnosisDetail( _voEHRAccess,  _voEHREncounter,  _voEHRDiagnosis);
			
			WebUTIL.setAttributeInSession(_rq, EHRConfig.EHR_COMPOSITION_SESSION_KEY_LIST_PATIENT_ENCOUNTER_DIAGNOSIS, lstPatEncDiagnosis);
			EHRVOUtility.setDiagnosisDetailVO(_rq, _fb.getPatCrNo(),lstPatEncDiagnosis ); //Added by Vasu on 2.Nov.2018
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			flag = false;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			flag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
			flag = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			flag = false;
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

}


