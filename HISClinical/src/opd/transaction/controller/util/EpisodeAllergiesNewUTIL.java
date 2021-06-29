package opd.transaction.controller.util;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.EpisodeAllergiesNewDATA;
import opd.transaction.controller.data.EpisodeAllergyDATA;
import opd.transaction.controller.data.GenericPatientAlertDATA;
import opd.transaction.controller.fb.EpisodeAllergyNewFB;
import opd.transaction.controller.fb.GenericPatientAlertFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class EpisodeAllergiesNewUTIL extends ControllerUTIL
{

	/**
	 * Getting Essential data for patient Allergy
	 * 
	 * @param _fb
	 * @param _rq
	 */
	
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void getEssentials(EpisodeAllergyNewFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		Map essentialMap = new HashMap();
		WebUTIL.getSession(_rq).removeAttribute(OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES);
		WebUTIL.getSession(_rq).removeAttribute(OpdConfig.MAP_SELECTED_ALLERGIES);
		WebUTIL.getSession(_rq).removeAttribute(OpdConfig.OUTER_ALLERGY_MAP);
		WebUTIL.getSession(_rq).removeAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT);
		try
		{
			setSysdate(_rq);
			UserVO userVO = getUserVO(_rq);
			
			InpatientDetailUTL.getInpatientDetailByCrNo(_fb, _rq);
			PatientDetailVO patientVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			String patDeathStatus = (String) patientVO.getPatStatusCode();
			String patDOB = (String) patientVO.getPatDOB();
			String patAge = (String) patientVO.getPatAge();
			_fb.setHiddenPatDeadStatus(patDeathStatus);
			_fb.setPatDOB(patDOB);
			_fb.setPatAge(patAge);
			 
			_rq.getSession().getAttribute(Config.SYSDATE);
			// WebUTIL.getSession(_rq).getAttribute(Config.SYSDATE);
		    
			 String sysDate=_rq.getSession().getAttribute(Config.SYSDATE).toString();
			 
			 _fb.setSysDate(sysDate);
			
			 PatientDetailVO patientDetailVO = null;
			// //////Retrieving the patient vo of the selected patient////////
				patientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				if(patientDetailVO == null || !patientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
					
					for (int i = 0; i < arrayDailyPatVO.length; i++)
					{
						if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
						{
							patientDetailVO = arrayDailyPatVO[i];
							break;
						}
					}
				}
				_fb.setPatCrNo(patientDetailVO.getPatCrNo());
				_fb.setHiddenPatDeadStatus(patientDetailVO.getIsDead());
			
			WebUTIL.getSession(_rq).setAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO, patientDetailVO);
			essentialMap = EpisodeAllergyDATA.getAllergyType(userVO);
			WebUTIL.setMapInSession(essentialMap, _rq);
			
			PatAllergyDtlVO[] arrPatAllergyDtlVO = EpisodeAllergiesNewDATA.getEpisodeAllergiesByPatient(patientDetailVO, userVO);
			// Getting all the previous allergies of the patient
			WebUTIL.getSession(_rq).setAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO, arrPatAllergyDtlVO);

			/*
			 * if(lst.size()==0) objStatus.add(Status.TRANSINPROCESS,"","No Advice Header Found"); else
			 */
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();

			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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

	
	/**
	 * Adding allergy name allergy type wise for multirow
	 * 
	 * @param _fb
	 * @param _rq
	 */
	
	public static void addDetailRow(EpisodeAllergyNewFB fb, HttpServletRequest request)
	{
		Status  objStatus=new Status();
		HttpSession session=request.getSession();
		List<EpisodeAllergiesVO> lstAddedPatAllergies = null;
		
		List<Entry> lstAlert = null;
		String alertName = "";
		
		try
		{
			///////////setCheckedRevoke(fb,request);

			/*lstAlert=(List<Entry>)session.getAttribute(OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST);
			for(Entry ent : lstAlert)
			    if( ent.getValue().equalsIgnoreCase(fb.getPatAlertId()))
			    {
			    	alertName=ent.getLabel();
			    	break;
			    }
			
			List newList = new ArrayList(lstAlert);
			newList=(List) WebUTIL.removeEntriesfromOptions(newList,fb.getPatAlertId()); 
			WebUTIL.setAttributeInSession(request,OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST,newList);
			
			*/
			lstAddedPatAllergies=(List<EpisodeAllergiesVO>)session.getAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT);
			
			if(lstAddedPatAllergies==null)
				lstAddedPatAllergies = new ArrayList<EpisodeAllergiesVO>();

			
			
			EpisodeAllergiesVO voPatAllergies = new EpisodeAllergiesVO();
			voPatAllergies.setAllergyName(fb.getSnomedCTAllergyName());
			voPatAllergies.setAllergiesCode(fb.getSnomedCTAllergyCode());
			voPatAllergies.setSensitivityCode(fb.getSensitivityCode());
			voPatAllergies.setSensitivityName(fb.getSensitivity());
			voPatAllergies.setDuration(fb.getDuration());
			//voPatAllergies.setRemarks(fb.getRemarks());
			
			voPatAllergies.setAllergySite(fb.getSnomedCTAllergySiteName());
			voPatAllergies.setSnomedCTAllergySiteCode(fb.getSnomedCTAllergySiteCode());
			voPatAllergies.setSnomedCTAllergySiteName(fb.getSnomedCTAllergySiteName());
			
			voPatAllergies.setAllergySymtoms(fb.getSnomedCTAllergySymptomsName());
			voPatAllergies.setSnomedCTAllergySymptomsCode(fb.getSnomedCTAllergySymptomsCode());
			voPatAllergies.setSnomedCTAllergySymptomsName(fb.getSnomedCTAllergySymptomsName());
			
			voPatAllergies.setSnomdCIdAllergyAdvice(fb.getSnomdPTAllergyAdvice());
			voPatAllergies.setSnomdPTAllergyAdvice(fb.getSnomdCIdAllergyAdvice());
			voPatAllergies.setAdvice(fb.getAdviceText());
	
			
			
			lstAddedPatAllergies.add(voPatAllergies);
			WebUTIL.setAttributeInSession(request, OpdConfig.ARR_ADDED_PATIENT_ALERT, lstAddedPatAllergies);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
		
	}	
	
	
	/**
	 * Deleting Allergy Type From multirowDeleting Allergy Name Allergy Type Wise From multirow
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void removeAllergyDetail(EpisodeAllergyNewFB _fb, HttpServletRequest _rq)
	{

		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		Map outerAllergy = (Map) session.getAttribute(OpdConfig.OUTER_ALLERGY_MAP);
		Map selectedAllergy = (Map) session.getAttribute(OpdConfig.MAP_SELECTED_ALLERGIES);
		List allergyList = (List) session.getAttribute(OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES);

		String allergyCode = _fb.getAllergyTableId().substring(0, _fb.getAllergyTableId().indexOf("^"));
		outerAllergy.remove(allergyCode);
		List selectedAllergyList = (List) selectedAllergy.get(allergyCode);
		String allergyName = (String) selectedAllergyList.get(0);
		selectedAllergy.remove(allergyCode);

		/*
		 * Entry obj = new Entry(); obj.setLabel(allergyName); obj.setValue(allergyCode);
		 * 
		 * allergyList.add(obj);
		 */

		allergyList = (List) WebUTIL.addEntryToOptions(allergyList, allergyCode, allergyName);

		session.setAttribute(OpdConfig.OUTER_ALLERGY_MAP, outerAllergy);
		session.setAttribute(OpdConfig.MAP_SELECTED_ALLERGIES, selectedAllergy);
		session.setAttribute(OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES, allergyList);
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(_rq, objStatus);
	}
	
	
	public static void removeAllergyRow(EpisodeAllergyNewFB fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		List<EpisodeAllergiesVO> lstAddedPatAlerts = null;
		List lstAlert=null;
		
		try
		{
		/////////	setCheckedRevoke(fb,_request);

			//lstAlert=(List)session.getAttribute(OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST);
			
			//List newList = new ArrayList(lstAlert);
			//newList=(List) WebUTIL.addEntryToOptions(newList,fb.getHiddenAllergyName(),fb.getHiddenAllergyCode()); 
			//WebUTIL.setAttributeInSession(_request,OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST,newList);
			
			lstAddedPatAlerts = (List<EpisodeAllergiesVO>)session.getAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT);			
			String hiddencode=fb.getHiddenAllergyCode();
			for(EpisodeAllergiesVO vo : lstAddedPatAlerts)
			{
				if(hiddencode.trim().equalsIgnoreCase(vo.getAllergiesCode().trim()))
				{
					lstAddedPatAlerts.remove(vo);
					break;
				}
			}
			WebUTIL.setAttributeInSession(_request,OpdConfig.ARR_ADDED_PATIENT_ALERT , lstAddedPatAlerts);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}	
	

	
	
	public static void saveAllergyDetails(EpisodeAllergyNewFB fb, HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		HttpSession session=_request.getSession();
		List<EpisodeAllergiesVO> lstAddedPatAlerts = null;
		DailyPatientVO dailyPatVO = null;
		EpisodeAllergiesVO vo= new EpisodeAllergiesVO();
		List<Entry> lstAlert=null;
		String alertName="";
		PatientDetailVO[] dailyPatientVOs = null;
		
		try
		{		
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			
			fb.setPatCrNo(voDP.getPatCrNo());
			if(voDP.getPatAdmNo() != null)
			{
				fb.setAdmissionNo(voDP.getPatAdmNo());
			}
			else
			{
				fb.setAdmissionNo("");
			}
			HelperMethods.populate(vo, voDP);
			vo.setAdmissionNo(fb.getAdmissionNo());
			
			// New Added Alerts(Chronic Diseases) 
			lstAddedPatAlerts = (List<EpisodeAllergiesVO>)session.getAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT);
			if(lstAddedPatAlerts==null)
				lstAddedPatAlerts = new ArrayList<EpisodeAllergiesVO>();
			/*else
			{
				for(EpisodeAllergiesVO vo : lstAddedPatAlerts)
				{
					HelperMethods.populate(vo, voDP);
					vo.setAdmissionNo(voDP.getPatAdmNo());
				}
			}*/
			
			//if(!fb.getPatAlertId().trim().equalsIgnoreCase("-1"))
			if(!fb.getSnomedCTAllergyCode().equalsIgnoreCase(""))
			{
			
				/*lstAlert=(List<Entry>)session.getAttribute(OpdConfig.ESSENTIAL_ALL_PAT_ALERT_LIST);
				for(Entry ent : lstAlert)
				    if( ent.getValue().equalsIgnoreCase(fb.getPatAlertId()))
				    {
				    	alertName=ent.getLabel();
				    	break;
				    }
*/
				
				EpisodeAllergiesVO voPatAllergies = new EpisodeAllergiesVO();
				
				voPatAllergies.setAllergyName(fb.getSnomedCTAllergyName());
				voPatAllergies.setAllergiesCode(fb.getSnomedCTAllergyCode());
				voPatAllergies.setSensitivityCode(fb.getSensitivityCode());
				voPatAllergies.setSensitivityName(fb.getSensitivity());
				voPatAllergies.setDuration(fb.getDuration());
				//voPatAllergies.setRemarks(fb.getRemarks());
				
				voPatAllergies.setAllergySite(fb.getSnomedCTAllergySiteName());
				voPatAllergies.setSnomedCTAllergySiteCode(fb.getSnomedCTAllergySiteCode());
				voPatAllergies.setSnomedCTAllergySiteName(fb.getSnomedCTAllergySiteName());
				
				voPatAllergies.setAllergySymtoms(fb.getSnomedCTAllergySymptomsName());
				voPatAllergies.setSnomedCTAllergySymptomsCode(fb.getSnomedCTAllergySymptomsCode());
				voPatAllergies.setSnomedCTAllergySymptomsName(fb.getSnomedCTAllergySymptomsName());
				
				voPatAllergies.setSnomdCIdAllergyAdvice(fb.getSnomdPTAllergyAdvice());
				voPatAllergies.setSnomdPTAllergyAdvice(fb.getSnomdCIdAllergyAdvice());
				voPatAllergies.setAdvice(fb.getAdviceText());
				
				lstAddedPatAlerts.add(voPatAllergies);
			}

			//GenericPatientAlertDATA.revokeAlerts(patientAlertDtlVO,getUserVO(request));
			//objStatus.add(Status.DONE,"","Patient Chronic Disease is  Revoked");
			
			EpisodeAllergiesNewDATA.saveAllergyDetailsNew(lstAddedPatAlerts, vo, getUserVO(_request));
			
			objStatus.add(Status.DONE,"","Patient Allergies Updated Successfully");
			session.removeAttribute(OpdConfig.ARR_ADDED_PATIENT_ALERT);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}

	
	/**
	 * Viewing the allergies detail of the given allergy
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void getAllergyDtlEpisodeWise(EpisodeAllergyNewFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		DailyPatientVO selectedPatientVO = null;
		UserVO userVO = getUserVO(_rq);
		//String allergyTypeCode = _fb.getSelectedAllergyCode();
		String allergyID = _fb.getSelectedAllergyID();
		try
		{
			selectedPatientVO = (DailyPatientVO) session.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);

			EpisodeAllergiesVO[] arrEpisodeAllergyDtlVO = EpisodeAllergiesNewDATA.getAllergyDtlEpisodeWise(allergyID, selectedPatientVO, userVO);
			WebUTIL.getSession(_rq).setAttribute(OpdConfig.ARRAY_ALL_ALLERGY_DETAIL_VO, arrEpisodeAllergyDtlVO);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(_rq, objStatus);

		}
	}

	
	
	public static void addAdvice(EpisodeAllergyNewFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		// HttpSession session=_rq.getSession();
		// List lstAdvice=null;

		try
		{
			// lstAdvice=(List)session.getAttribute(OpdConfig.ESSENTIALBO_OPTION_ALLERGY_ADVICE);

			List lstAdvice = EpisodeAllergiesNewDATA.getAdvice(getUserVO(_rq));
			WebUTIL.setAttributeInSession(_rq, OpdConfig.ESSENTIALBO_OPTION_ALLERGY_ADVICE, lstAdvice);
			if (lstAdvice.size() > 0) objStatus.add(Status.NEW, "", "");
			else objStatus.add(Status.NEW, "", "No Advice Found");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
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
			_rq.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}

	
	/**
	 * Setting the status of the popup
	 * 
	 * @param fb
	 * @param request
	 */
	public static void calculateDay(EpisodeAllergyNewFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();

		try
		{
			objStatus.add(Status.NEW, "", "");
		}
		catch (Exception e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}

	/**
	 * Revoking the allergy
	 * 
	 * @param fb
	 * @param request
	 */
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 24-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void revokeAllergy(EpisodeAllergyNewFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		EpisodeAllergiesVO epiAllergyVO = new EpisodeAllergiesVO();
		PatientDetailVO[] dailyPatientVOs = null;

		try
		{
			String revokeAllergyId = fb.getRevokeAllergyId().trim();
		/*	PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
				if (fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo())) selectedPatientVO = arrayDailyPatVO[i];
		 */
			PatientDetailVO selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(selectedPatientVO == null || !selectedPatientVO.getPatCrNo().equals(fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						selectedPatientVO = dailyPatientVOs[i];
						break;
					}
				}
			}
			
			HelperMethods.populate(epiAllergyVO, selectedPatientVO);
			PatAllergyDtlVO[] arrPatAllergyDtlVO = (PatAllergyDtlVO[]) session.getAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO);
			for (int i = 0; i < arrPatAllergyDtlVO.length; i++)
			{
				if (revokeAllergyId.equalsIgnoreCase(arrPatAllergyDtlVO[i].getAllergyID()))
				{
					//epiAllergyVO.setAllergiesCode(arrPatAllergyDtlVO[i].getAllergyID());
					//epiAllergyVO.setAllergyTypeCode(arrPatAllergyDtlVO[i].getAllergyTypeCode());
					//epiAllergyVO.setAllergyTypeName(arrPatAllergyDtlVO[i].getAllergyTypeName());
					epiAllergyVO.setReasonCode(arrPatAllergyDtlVO[i].getAllergyID());
					//epiAllergyVO.setReasonName(arrPatAllergyDtlVO[i].getAllergyName());
					//epiAllergyVO.setSensitivityCode(arrPatAllergyDtlVO[i].getsAllergyName());
					//sensivity--add
				}
			}
			int i = Integer.parseInt(fb.getIndex());
			epiAllergyVO.setRemarks(fb.getRevokeRemarks()[i]);

			EpisodeAllergiesNewDATA.revokeAllergy(epiAllergyVO, getUserVO(request));
			objStatus.add(Status.DONE, "", "Patient Allergy is  Revoked");

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
			WebUTIL.setStatus(request, objStatus);
		}
	}

}
