/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.ot.presentation;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ehr.EHRConfig;
import ehr.ot.vo.EHRSection_OTDetailsVO;
import ehr.treatmentgiven.presentation.EHRSection_TreatmentGivenFB;
import ehr.treatmentgiven.presentation.EHRSection_TreatmentGivenLNK;
import ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO;
import ehr.vo.EHRVOUtility;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import emr.vo.EHR_PatEncounterDetailsVO;

public class EHRSection_OTDetailsUTL extends ControllerUTIL
{
	
	public static void saveDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_TreatmentGivenFB _fb) 
	{
		String flag=EHRSection_OTDetailsUTL.saveGivenTreatmentDetails(_rq,response,_fb);
		
		try
		{
			//writeResponse(response, flag);
		}
		catch (Exception e) {
				e.printStackTrace();
			
		}
		
		
		
	}
	
	public static String saveGivenTreatmentDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_TreatmentGivenFB _fb) 
	{
		
		String isSave = "true";
		Status objStatus = new Status();
		DailyPatientVO selectedPatientVO = new DailyPatientVO();
		
		List<EHRSection_TreatmentGivenVO> addTreatment= new ArrayList<EHRSection_TreatmentGivenVO>();
		List<EHRSection_TreatmentGivenVO> revokedTreatment= new ArrayList<EHRSection_TreatmentGivenVO>();
		List<EHRSection_TreatmentGivenVO> allSaveListTreatment= new ArrayList<EHRSection_TreatmentGivenVO>();
		int currentDiagLength = 0;
		
		try
		{

			HelperMethods.populatetToNullOrEmpty(selectedPatientVO, _fb);
			//currentDiagLength=_fb.getDiagonisticTypeCode().length;		
			//currentDiagLength =_fb.getSnomedCTTreatmentCode().length;
			currentDiagLength = _fb.getTreatmentGivenRecordStatus().length;
			for (int k = 0; k < currentDiagLength; k++)
			{
				//if(!_fb.getSnomedCTTreatmentCode()[k].isEmpty())
				//{
				
				if(_fb.getTreatmentGivenRecordStatus()[k].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_NOT_SAVED))
				{
					
					    EHRSection_TreatmentGivenVO addTreatmentVO = new EHRSection_TreatmentGivenVO();
			
					    addTreatmentVO.setTreatmentType(_fb.getTreatmentType()[k]);
					    addTreatmentVO.setSnomedCTTreatmentName(_fb.getSnomedCTTreatmentName()[k]);
					    addTreatmentVO.setSnomedCTTreatmentCode(_fb.getSnomedCTTreatmentCode()[k]);
					    addTreatmentVO.setTreatmentDate(_fb.getTreatmentDate()[k]);
					    addTreatmentVO.setSnomedPTRemarks(_fb.getSnomedPTRemarks()[k]);
					    addTreatmentVO.setSnomedCIdRemarks(_fb.getSnomedCIdRemarks()[k]);
					    addTreatmentVO.setRemarks(_fb.getRemarks()[k]);
						HelperMethods.populatetToNullOrEmpty(addTreatmentVO, selectedPatientVO);
						addTreatmentVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
                        addTreatmentVO.setAdmissionNo(_fb.getAdmissionNo());
					    addTreatment.add(addTreatmentVO);
				}
				
				if(_fb.getTreatmentGivenRecordStatus()[k].equalsIgnoreCase(EHRConfig.EHR_SECTION_RECORD_STATUS_TOREVOKE))
				{	
						EHRSection_TreatmentGivenVO episodeRevokeTreatmentVO=new EHRSection_TreatmentGivenVO();
						HelperMethods.populatetToNullOrEmpty(episodeRevokeTreatmentVO, selectedPatientVO);
						//episodeRevokeTreatmentVO.setSnomedCTTreatmentCode(_fb.getSnomedCTTreatmentCode()[k]);
						//episodeRevokeTreatmentVO.setDiagnosisCodeType(OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED);
						episodeRevokeTreatmentVO.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
						episodeRevokeTreatmentVO.setAdmissionNo(_fb.getAdmissionNo());
						revokedTreatment.add(episodeRevokeTreatmentVO);
						
						
					}
				
			}
				
			//EHRSection_DiagnosisLNK.saveOpdDiagnosisDetails(addEpisodeDiag,revokedEpisodeDiag, getUserVO(_rq));
			EHRSection_TreatmentGivenLNK.saveTreatmentGivenDetails(addTreatment,revokedTreatment,getUserVO(_rq));
			WebUTIL.getSession(_rq).setAttribute(EHRConfig.EHR_PAT_TREATMENT_GIVEN_DETAILS,addTreatment);
			
			EHR_PatEncounterDetailsVO patencountervo=(EHR_PatEncounterDetailsVO) WebUTIL.getSession(_rq).getAttribute(EHRConfig.EHR_PAT_ENCOUNTER_DETAILS_VO);
			//patencountervo.setListSaveAllDiagnosis(allSaveListEpisodeDiag);
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
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return isSave;
		
	}

	public static boolean getPatientEncTreatmentGivenDetail(EHRSection_TreatmentGivenFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		boolean flag = true;
		try
		{
			UserVO userVO = getUserVO(_rq);
			// setSysdate(_rq);

			EHR_PatientEncounterVO _voEHREncounter = new EHR_PatientEncounterVO();
			EHR_AccessPermissionVO _voEHRAccess = new EHR_AccessPermissionVO();
			//EHRSection_DiagnosisVO _voEHRDiagnosis = new EHRSection_DiagnosisVO();
			EHRSection_TreatmentGivenVO _voEHRTreatmentGIven = new EHRSection_TreatmentGivenVO();
			HelperMethods.populate(_voEHREncounter,_fb);
			HelperMethods.populate(_voEHRAccess,_fb);
			//HelperMethods.populate(_voEHRDiagnosis,_fb);
			HelperMethods.populate(_voEHREncounter,userVO);
			
			_voEHRTreatmentGIven.setEpisodeCode(_fb.getEpisodeCode());
			_voEHRTreatmentGIven.setPatCrNo(_fb.getPatCrNo());
			
			List<EHRSection_TreatmentGivenVO> lstPatEncTreatmentGiven= new ArrayList<EHRSection_TreatmentGivenVO>();
			
			lstPatEncTreatmentGiven = EHRSection_TreatmentGivenLNK.getPatientEncTreatmentGivenDetail( _voEHRAccess,  _voEHREncounter,  _voEHRTreatmentGIven);
			
			WebUTIL.setAttributeInSession(_rq, EHRConfig.EHR_PAT_TREATMENT_GIVEN_DETAILS, lstPatEncTreatmentGiven);
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
	
	public static void getOTEssentials(EHRSection_OTDetailsFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		Map essentialMap = new HashMap();
		try
		{
			UserVO userVO = getUserVO(_rq);
            
            PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			
			_fb.setAdmissionNo(ptaientDetailVO.getPatAdmNo());
			_fb.setEpisodeCode(ptaientDetailVO.getEpisodeCode());
			_fb.setEpisodeVisitNo(ptaientDetailVO.getEpisodeVisitNo());
			_fb.setPatCrNo(ptaientDetailVO.getPatCrNo());
			
			EHRSection_OTDetailsVO _voEHROTDetails = new EHRSection_OTDetailsVO();
			_voEHROTDetails.setAdmissionNo(ptaientDetailVO.getPatAdmNo());
			_voEHROTDetails.setEpisodeCode(ptaientDetailVO.getEpisodeCode());
			_voEHROTDetails.setEpisodeVisitNo(ptaientDetailVO.getEpisodeVisitNo());
			_voEHROTDetails.setPatCrNo(ptaientDetailVO.getPatCrNo());
			//HelperMethods.populatetToNullOrEmpty(_voEHROTDetails,_fb);
			
			essentialMap = EHRSection_OTDetailsData.getENCOTEssential(_voEHROTDetails, getUserVO(_rq));
			
			List OTList = (List)essentialMap.get(EHRConfig.OPERATIONS_LIST);
			WebUTIL.setAttributeInSession(_rq, EHRConfig.OPERATIONS_LIST, OTList);
			
			List surgeonList = (List)essentialMap.get(EHRConfig.SURGEON_LIST);
			WebUTIL.setAttributeInSession(_rq, EHRConfig.SURGEON_LIST, surgeonList);
			
			List<EHRSection_OTDetailsVO> prevOTDtlList = (List)essentialMap.get(EHRConfig.PREV_OT_DETAIL_LIST);
			WebUTIL.setAttributeInSession(_rq, EHRConfig.PREV_OT_DETAIL_LIST, prevOTDtlList);
			
			//Put OT List into EHRVO
			if(prevOTDtlList!=null)
			{
             EHRVOUtility.setOTDetailsVO(_rq, _fb.getPatCrNo(), prevOTDtlList);
			}
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
			
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
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		
	}
	
	public static boolean saveOperationDetails(HttpServletRequest _rq,HttpServletResponse response, EHRSection_OTDetailsFB _fb) 
	{
		
		boolean isSave = true;
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		EHRSection_OTDetailsVO EHROTDetailsVO =new EHRSection_OTDetailsVO();
		try
		{
          
            UserVO userVO = getUserVO(_rq);
            
            PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			
			_fb.setAdmissionNo(ptaientDetailVO.getPatAdmNo());
			_fb.setEpisodeCode(ptaientDetailVO.getEpisodeCode());
			_fb.setEpisodeVisitNo(ptaientDetailVO.getEpisodeVisitNo());
			_fb.setPatCrNo(ptaientDetailVO.getPatCrNo());
			
			
			
			/*String[] operationName =_fb.getSelOperationName();
			String[] operationCode = _fb.getSelOperationCode();
			String[] surgeonName = _fb.getSelSurgeonName();
			String[] surgeonCode = _fb.getSelSergeonCode();
			String[] findings = _fb.getSelOperativeFindings();*/
			String opDate = _fb.getOperationDate();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
			
			
			 Date date1=sdf.parse((String)opDate);
			 SimpleDateFormat sdf3 =new SimpleDateFormat("dd-MMM-yyyy");
			 String strDate = sdf3.format(date1); 
			 opDate = strDate;
			 //String[] dt = {strDate};
			   if(_fb.getSelOperationName()!=null && !_fb.getSelOperationName().trim().equals(""))
			   {
				EHROTDetailsVO.setSelOperationCode(_fb.getOperationCode());
				EHROTDetailsVO.setSelOperationName(_fb.getSelOperationName());
				EHROTDetailsVO.setSelOperationDate(opDate);
				EHROTDetailsVO.setSelOperativeFindings(_fb.getOperativeFindings());
				EHROTDetailsVO.setSelSergeonCode(_fb.getSurgeonCode());
				EHROTDetailsVO.setSelSurgeonName(_fb.getSelSurgeonName());
				EHROTDetailsVO.setAdmissionNo(_fb.getAdmissionNo());
				EHROTDetailsVO.setEpisodeCode(_fb.getEpisodeCode());
				EHROTDetailsVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
				EHROTDetailsVO.setPatCrNo(_fb.getPatCrNo());
				EHROTDetailsVO.setSelOperatonComplications(_fb.getOperationSummary());
				
				EHROTDetailsVO.setSelOperatonImplant(_fb.getOperationImplant());
				EHROTDetailsVO.setSelOperatonPreOp(_fb.getOperationPreOp());
				EHROTDetailsVO.setSelOperatonPostOp(_fb.getOperationPostOp());
			   }
				//EHROTDetailsVO.set(_fb.g);
			
			/*if(_fb.getSelOperationCode()!=null && _fb.getSelOperationCode().length!=0)
			{
				
				EHROTDetailsVO = new EHRSection_OTDetailsVO[_fb.getSelOperationCode().length];
				
				for(int i=0;i<EHROTDetailsVO.length-1;i++){
					EHROTDetailsVO[i]=new EHRSection_OTDetailsVO();
					
					EHROTDetailsVO[i].setSelOperationCode(operationCode[i]);
					EHROTDetailsVO[i].setSelOperationName(operationName[i]);
					EHROTDetailsVO[i].setSelOperationDate(opDate[i]);
					EHROTDetailsVO[i].setSelOperativeFindings(findings[i]);
					EHROTDetailsVO[i].setSelSergeonCode(surgeonCode[i]);
					EHROTDetailsVO[i].setSelSurgeonName(surgeonName[i]);
					EHROTDetailsVO[i].setAdmissionNo(_fb.getAdmissionNo());
					EHROTDetailsVO[i].setEpisodeCode(_fb.getEpisodeCode());
					EHROTDetailsVO[i].setEpisodeVisitNo(_fb.getEpisodeVisitNo());
					EHROTDetailsVO[i].setPatCrNo(_fb.getPatCrNo());
					
				}
			}*/
			EHRSection_OTDetailsData.saveOperationDetails(EHROTDetailsVO, getUserVO(_rq));

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
			System.out.print(e.getMessage());
		}
		catch (Exception e)
		{
			isSave = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return isSave;	
	}

	//Added by Vasu on 30.July.2019
	public static void deletePreviousOperations(EHRSection_OTDetailsFB fb,HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		EHRSection_OTDetailsVO EHROTDetailsVO =new EHRSection_OTDetailsVO();
		try
		{
			UserVO userVO = getUserVO(request);	
		    PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
		    
		    EHROTDetailsVO.setAdmissionNo(ptaientDetailVO.getAddmissionNo());
			EHROTDetailsVO.setEpisodeCode(ptaientDetailVO.getEpisodeCode());
			EHROTDetailsVO.setEpisodeVisitNo(ptaientDetailVO.getEpisodeVisitNo());
			EHROTDetailsVO.setPatCrNo(ptaientDetailVO.getPatCrNo());
			EHROTDetailsVO.setSlno(fb.getSlno());
			
			EHRSection_OTDetailsData.deletePreviousOperationDetails(EHROTDetailsVO, userVO);
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}




