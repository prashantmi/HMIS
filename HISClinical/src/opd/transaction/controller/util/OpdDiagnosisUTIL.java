package opd.transaction.controller.util;

/**
 * @copyright CDAC
 * @developer Hruday Meher
 * @lastModified Pargya Sharma  08-Jul-2010
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisNotCompatibleTypeExcetion;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.IcdDiseaseMasterVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdDiagnosisDATA;
import opd.transaction.controller.fb.OpdDiagnosisFB;
import opd.transaction.controller.fb.OpdDiagnosisFB;
import registration.RegistrationConfig;

public class OpdDiagnosisUTIL extends ControllerUTIL
{
	/** Setting Diagnosis Code Type
	 * @param _fb
	 * @param _rq
	 */
	public static void getEssentials(OpdDiagnosisFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		try
		{
			setSysdate(_rq);
			
			// Fetching Patient Detail from Desk
			PatientDetailVO selectedPatientVO = null;
			if (session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO)!=null)
			{
				
				selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				
				_fb.setEpisodeCode(selectedPatientVO.getEpisodeCode());
				_fb.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
				_fb.setDepartmentUnitCode(selectedPatientVO.getDepartmentUnitCode());
			}
			else if(session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST)!=null)
			{
				PatientDetailVO[] arrayDailyPatVO = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < arrayDailyPatVO.length; i++)
					if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
						selectedPatientVO = arrayDailyPatVO[i];
	
				_fb.setEpisodeCode(selectedPatientVO.getEpisodeCode());
				_fb.setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());
				_fb.setDepartmentUnitCode(selectedPatientVO.getDepartmentUnitCode());
				WebUTIL.setAttributeInSession(_rq, DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO, selectedPatientVO);
			}
			
			// Fetch Department Unit from Patient List VO
			/*if(session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE).equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK))
				_fb.setUnitDiagnosisCodeType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_DIAGNOSIS_TYPE_CODE));
			else
				_fb.setUnitDiagnosisCodeType((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_DIAGNOSIS_TYPE_CODE));  //_fb.setUnitDiagnosisCodeType(OpdConfig.CHOICE_ICD_CODE);  //Commented By Adil Wasi*/
			
			//------- 
			//AddedBy: NehaRajgariya Date:28July2016
			String DeptUnitCode = _fb.getDepartmentUnitCode();
			String icdHospCode = OpdDiagnosisDATA.getIcdHospCode(DeptUnitCode,getUserVO(_rq));
			if(icdHospCode != null)
			{
				_fb.setUnitDiagnosisCodeType(icdHospCode);
			}
			else
			{
				_fb.setUnitDiagnosisCodeType(OpdConfig.CHOICE_ALL_ICD_HOSPITAL_SNOMED);
			}
			
			//_fb.setUnitDiagnosisCodeType(OpdConfig.CHOICE_ALL_ICD_HOSPITAL_SNOMED); //  temporary added for snomd  
			_fb.setUnitDiagnosisCodeType(OpdConfig.CHOICE_ALL_AND_SNOMED_DEFAULT ); //Temperory Added for Snomed and ICD-O By Vasu on 15.Oct.2018
			_fb.setRepeatedDiagnosisLength("0");
			
			if (_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_CODE)) 
			{	
				_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_ICD_CODE);
			}
			else if(_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_HOSPITAL_CODE))
			{
				_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_HOSPITAL_CODE);
			}
			else if(_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_SNOMEDCT_CODE))
			{
				_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_SNOMEDCT_CODE);
			}
			else if(_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE)||_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_DEFAULT_ICDO_CODE)) //Added by Vasu on 05.Oct.2018 for ICD-0 Integration
			{
				_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_ICD_CODE);
			}
			else if(_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_AND_HOSPITAL_DEFAULT_CODE))
			{
				_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_HOSPITAL_CODE);
			}
			else if(_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ALL_ICD_HOSPITAL_SNOMED))
			{
				_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_ICD_CODE);
			}
			else if(_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ALL_AND_SNOMED_DEFAULT))
			{
				_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_SNOMEDCT_CODE);
			}
			else if(_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_HOSPITAL_AND_SNOMED_DEFAULT))
			{
				_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_SNOMEDCT_CODE);
			}
			
			/**Added by Vasu on 05.Oct.2018 for ICD-0 Integration*/
			else if(_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICDO_DEFAULT_ICD_CODE))
			{	
				_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_ICDO_CODE);
			}	
			getEssentialsByChoice(_fb, _rq);
			generateComboOption(_fb, _rq);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.FAILURE, "No Unit Code or Diagnosis Type Code Found: Session might have expired", "");
		}		
		finally
		{
			// objStatus.add(Status.TRANSINPROCESS);
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	/**Getting Essential data for diagnosis
	 * @param _fb
	 * @param _rq
	 */
	public static void getEssentialsByChoice(OpdDiagnosisFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		Map essentialMap = new HashMap();
		HttpSession session = _rq.getSession();
		try
		{
			// ////setting the fields in form bean to avoid null references///////////////
			String[] strArray = new String[1];
			strArray[0] = "";
			_fb.setIcdCode(strArray);
			_fb.setDignosisName(strArray);
			_fb.setHospitalDiagnosisCode(strArray);
			_fb.setHospitalDiagnosisName(strArray);
			_fb.setSnomedCTDiagnosisCode(strArray); //snomd integration
			_fb.setSnomedCTDiagnosisName(strArray); //snomd integration
			_fb.setRemarks(strArray);
			_fb.setDiagonisticTypeCode(strArray);
			_fb.setDiagnosisSite(strArray);
			_fb.setDiagnosisSiteLabel(strArray);
			_fb.setSnomedCTDiagnosisSiteName(strArray); 
			_fb.setSnomedCTDiagnosisSiteCode(strArray); 
			_fb.setSnomedPTRemarks(strArray); 
			_fb.setSnomedCIdRemarks(strArray); 
			
			/**Added by Vasu on 18.Oct.2018 for ICD-O Integration*/
			_fb.setDiseaseCode(strArray);
			_fb.setDiseaseSite(strArray);
			_fb.setDiseaseSiteId(strArray);
			_fb.setMorphCode(strArray);
			_fb.setMorphTitle(strArray);
			/**End Vasu*/
			// Fetching Patient Detail from Desk
			PatientDetailVO selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			
			// Getting Diagnosis Essentials
			if(selectedPatientVO!=null)
			{
				//if (_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_CODE)) 
				if (_fb.getIcdNHospitalFlag().equals(OpdConfig.CHOICE_ICD_CODE)) 
				{	
					//_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_ICD_CODE);
					essentialMap = OpdDiagnosisDATA.getIcdDiagnosisEssential(selectedPatientVO, getUserVO(_rq)); //Getting data for ICD diagnosis
				}	
				else if (_fb.getIcdNHospitalFlag().equals(OpdConfig.CHOICE_HOSPITAL_CODE)) 
				{
					essentialMap = OpdDiagnosisDATA.getHospitalDiagnosisEssential(selectedPatientVO, getUserVO(_rq));	//Getting data for Hospital diagnosis
					/*if(_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_HOSPITAL_CODE))
					{
						_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_HOSPITAL_CODE);
						essentialMap = OpdDiagnosisDATA.getHospitalDiagnosisEssential(getUserVO(_rq), _fb.getPatCrNo(), _fb.getEpisodeCode());
					}	
					else
					{
						if(_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE))
							_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_ICD_CODE);
						else
							_fb.setIcdNHospitalFlag(OpdConfig.CHOICE_HOSPITAL_CODE);
						essentialMap = OpdDiagnosisDATA.getIcdNHospitalDiagnosisEssential(getUserVO(_rq), _fb.getPatCrNo(), _fb.getEpisodeCode());
					}	*/
				}
				
				else if (_fb.getIcdNHospitalFlag().equals(OpdConfig.CHOICE_SNOMEDCT_CODE)) 
				{
					essentialMap = OpdDiagnosisDATA.getSnomdDiagnosisEssential(selectedPatientVO, getUserVO(_rq));	//Getting data for Hospital diagnosis
					
				}
				
				/**Added by Vasu on 17.Oct for ICD-O Integration*/
				else
				{
					essentialMap = OpdDiagnosisDATA.getHospitalDiagnosisEssential(selectedPatientVO, getUserVO(_rq));
				}
				
				WebUTIL.setMapInSession(essentialMap, _rq);
				// //setting VO in fb///
				EpisodeDiagnosisVO[] arrayPreviousDiagVO = (EpisodeDiagnosisVO[]) essentialMap.get(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO);
	
				// ///Fetching the latest diagnosis from array of VO////
				// EpisodeDiagnosisVO prevDiagVO=arrayPreviousDiagVO[(arrayPreviousDiagVO.length)-1];
				EpisodeDiagnosisVO[] prevDiagVO = null;
				EpisodeDiagnosisVO[] stopDiagVO = null;
				if(arrayPreviousDiagVO.length>0)
				{
					String maxVisitNO = arrayPreviousDiagVO[(arrayPreviousDiagVO.length) - 1].getEpisodeVisitNo();
					
		
					int numberOfLastVisitDiagnosis = 0;
					int numberOfStopDiagnosis = 0;
					// /finding the size of latest daignosis vo////
					for (int i = 0; i < arrayPreviousDiagVO.length; i++)
					{
						if (arrayPreviousDiagVO[i].getEpisodeVisitNo().equals(maxVisitNO) && !arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_STOP) && !arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_CANCELLED)) numberOfLastVisitDiagnosis++;
						if (arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_STOP) || arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_CANCELLED)) numberOfStopDiagnosis++;
					}
					prevDiagVO = new EpisodeDiagnosisVO[numberOfLastVisitDiagnosis];
					stopDiagVO = new EpisodeDiagnosisVO[numberOfStopDiagnosis];
					// ////preparing the vo
					int counter = 0;
					int countStop = 0;
					for (int j = 0; j < arrayPreviousDiagVO.length; j++)
					{
						if (arrayPreviousDiagVO[j].getEpisodeVisitNo().equals(maxVisitNO) && !arrayPreviousDiagVO[j].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_STOP) && !arrayPreviousDiagVO[j].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_CANCELLED))
						{
							prevDiagVO[counter] = new EpisodeDiagnosisVO();
							prevDiagVO[counter] = arrayPreviousDiagVO[j];
							counter++;
						}
						if (arrayPreviousDiagVO[j].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_STOP) || arrayPreviousDiagVO[j].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_CANCELLED))
						{
							stopDiagVO[countStop] = new EpisodeDiagnosisVO();
							stopDiagVO[countStop] = arrayPreviousDiagVO[j];
							countStop++;
						}
					}
				}	
					WebUTIL.getSession(_rq).setAttribute(OpdConfig.Latest_DIAGNOSIS_VO, prevDiagVO);
					WebUTIL.getSession(_rq).setAttribute(OpdConfig.STOP_DIAGNOSIS_VO, stopDiagVO);
				//	_fb.setDiagnosisCodeType(prevDiagVO[0].getDiagnosisCodeType());
					_fb.setPreviousDiagnosisVO(prevDiagVO);
					_fb.setArrayPreviousDiagnosisVO(arrayPreviousDiagVO);
				
				if(arrayPreviousDiagVO.length>0)
					objStatus.add(Status.TRANSINPROCESS);
				else
					objStatus.add(Status.TRANSINPROCESS,"","No Previous Diagnosis Found");
			}
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

	/** Generating Combo for Diagnosis List, Diagnosis Site for multirow
	 * @param _fb
	 * @param _rq
	 */
	public static void generateComboOption(OpdDiagnosisFB _fb, HttpServletRequest _rq)
	{
		HttpSession session = _rq.getSession();
		List diagnosisTypeList = (List) session.getAttribute(RegistrationConfig.DIAGNOSIS_LIST);
		List diagnosisSiteList = (List) session.getAttribute(OpdConfig.DIAGNOSIS_SITE_LIST);
		String resp = "";
		String respDiagnosisSite="";
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
		
		if(diagnosisSiteList!=null)
		{
			Iterator itr = diagnosisSiteList.iterator();
	
			while (itr.hasNext())
			{
				Entry objEntry = (Entry) itr.next();
				respDiagnosisSite = respDiagnosisSite + "<option value='" + objEntry.getValue() + "'>" + objEntry.getLabel() + "</option>";
			}
			_fb.setComboDiagnosisSite(respDiagnosisSite);
		}
	}

	/** Saving patient diagnosis
	 * @param _fb
	 * @param _rq
	 * @return
	 */
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO				
	##		Modify Date				: 16-01-2015	
	##		Reason	(CR/PRS)		: To Run Desk according to New VO
	##		Modify By				: Akash Singh
	*/
	public static boolean save(OpdDiagnosisFB _fb, HttpServletRequest _rq)
	{
		boolean isSave = true;
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		EpisodeDiagnosisVO[] episodeDiaVO = null;
		DailyPatientVO selectedPatientVO = null;
		EpisodeDiagnosisVO[] allDiagnosisVO = (EpisodeDiagnosisVO[]) WebUTIL.getSession(_rq).getAttribute(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO);
		EpisodeDiagnosisVO[] latestDiagnosisVO = (EpisodeDiagnosisVO[]) WebUTIL.getSession(_rq).getAttribute(OpdConfig.Latest_DIAGNOSIS_VO);
		
		try
		{

			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			selectedPatientVO = ptaientDetailVO;
			if(ptaientDetailVO == null || !ptaientDetailVO.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				DailyPatientVO[] arrayDailyPatVO = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);				
				for (int i = 0; i < arrayDailyPatVO.length; i++)
					if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
						selectedPatientVO = arrayDailyPatVO[i];
			}
			int voLength = 0;
			int currentDiagLength = 0;
			int repeatDiagLength = 0;
			int stopDiagLength = 0;

			if (_fb.getAddDiagnosisCode() != null && (latestDiagnosisVO!=null && latestDiagnosisVO.length>0 && !latestDiagnosisVO[0].getEpisodeVisitNo().equalsIgnoreCase(_fb.getEpisodeVisitNo())))
				repeatDiagLength = _fb.getAddDiagnosisCode().length;
			if (_fb.getAddDiagnosis() != null && (latestDiagnosisVO!=null && latestDiagnosisVO.length>0 && !latestDiagnosisVO[0].getEpisodeVisitNo().equalsIgnoreCase(_fb.getEpisodeVisitNo())))
				stopDiagLength = _fb.getAddDiagnosis().length;

			// //////length of the vo///////
			if (_fb.getDiagonisticTypeCode().length > 0 && !_fb.getDiagonisticTypeCode()[0].equals("-1")) currentDiagLength = _fb
					.getDiagonisticTypeCode().length;
			if (allDiagnosisVO == null)
				voLength = currentDiagLength;
			else
				voLength = currentDiagLength + repeatDiagLength + stopDiagLength;
			    episodeDiaVO = new EpisodeDiagnosisVO[voLength];

			// //populating vo with fb
			// Add New One
			for (int j = 0; j < currentDiagLength; j++)
			{
				episodeDiaVO[j] = new EpisodeDiagnosisVO();
				if (_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_ICD_CODE)) episodeDiaVO[j].setDiagnosticCode(_fb.getIcdCode()[j]);
				if (_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_HOSPITAL_CODE))  episodeDiaVO[j].setDiagnosticCode(_fb.getHospitalDiagnosisCode()[j]);
				if (_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_SNOMEDCT_CODE))  episodeDiaVO[j].setDiagnosticCode(_fb.getSnomedCTDiagnosisCode()[j]); //snomed integration
				if (_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE))  episodeDiaVO[j].setDiagnosticCode(_fb.getMorphCode()[j]); //ICD-O Integration Added by Vasu on 23.Oct.2018
				episodeDiaVO[j].setDiagnosticTypeCode(_fb.getDiagonisticTypeCode()[j]);
				episodeDiaVO[j].setRemarks(_fb.getRemarks()[j]);
				
				if (_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_SNOMEDCT_CODE)) 
					episodeDiaVO[j].setDiagnosisSite(_fb.getSnomedCTDiagnosisSiteCode()[j]);
				/**Added by Vasu on 23.Oct.2018 for ICD-O Integration*/
				else if (_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_ICD_DEFAULT_AND_HOSPITAL_CODE))
				{	
				episodeDiaVO[j].setDiagnosisSite(_fb.getDiseaseSiteId()[j]);
				episodeDiaVO[j].setDignosisName(_fb.getDiseaseSite()[j]);
				episodeDiaVO[j].setDiagnosisCodeType(_fb.getDiagnosisCodeType());
				}
				
				else{episodeDiaVO[j].setDiagnosisSite(_fb.getDiagnosisSite()[j]);  }
				
				
				if (_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_SNOMEDCT_CODE)) 
					episodeDiaVO[j].setDiagnosisSiteLabel(_fb.getSnomedCTDiagnosisSiteName()[j]);
				else{episodeDiaVO[j].setDiagnosisSiteLabel(""); 
				
				}
				
				if(_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_ICD_CODE))
						{
				episodeDiaVO[j].setDiagnosisCodeType(OpdConfig.DIAGNOSIS_CODE_TYPE_ICD);
						}
				if(_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_HOSPITAL_CODE))
				{
	        	episodeDiaVO[j].setDiagnosisCodeType(OpdConfig.DIAGNOSIS_CODE_TYPE_HOSPITAL);
				}
				if(_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_SNOMEDCT_CODE))
				{
	        	episodeDiaVO[j].setDiagnosisCodeType(OpdConfig.DIAGNOSIS_CODE_TYPE_SNOMED);
				}
				
				//added as per data model changes
				if (_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_ICD_CODE)) episodeDiaVO[j].setDignosisName(_fb.getDignosisName()[j]);
				if (_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_HOSPITAL_CODE))  episodeDiaVO[j].setDignosisName(_fb.getHospitalDiagnosisName()[j]);
				if (_fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_SNOMEDCT_CODE))  episodeDiaVO[j].setDignosisName(_fb.getSnomedCTDiagnosisName()[j]); //snomed integration
				//if(_fb.getDiagnosticTypeName()[j]!=null) episodeDiaVO[j].setDiagnosticTypeName(_fb.getDiagnosticTypeName()[j]);
				//if(_fb.getDiagnosisSiteLabel()[j]!=null)episodeDiaVO[j].setDiagnosisSiteLabel(_fb.getDiagnosisSiteLabel()[j]);
			
				
				HelperMethods.populatetToNullOrEmpty(episodeDiaVO[j], selectedPatientVO);
				episodeDiaVO[j].setIsRepeat(OpdConfig.DIAGNOSIS_IS_REPEAT_NEW);
			}
			// /////populating vo with checkbox selection and repeatVO/////

			if (allDiagnosisVO != null)
			{
				// Repeat Old
				if (repeatDiagLength > 0)
				{
					for (int x = 0; x < repeatDiagLength; x++)
					{
						int j = currentDiagLength + x;
						episodeDiaVO[j] = new EpisodeDiagnosisVO();
						episodeDiaVO[j].setDiagnosticCode(_fb.getAddDiagnosisCode()[x]);
						episodeDiaVO[j].setDiagnosticTypeCode(_fb.getAddDiagnosticTypeCode()[x]);
						episodeDiaVO[j].setRemarks(_fb.getAddDiagnosisRemarks()[x]);
						episodeDiaVO[j].setDiagnosisCodeType(_fb.getAddDiagnosisCodeType()[x]);
						episodeDiaVO[j].setDiagnosisSite(_fb.getAddDiagnosisSite()[x]);
						HelperMethods.populatetToNullOrEmpty(episodeDiaVO[j], selectedPatientVO);
						episodeDiaVO[j].setIsRepeat(OpdConfig.DIAGNOSIS_IS_REPEAT_REPEAT);
					}
				}
				if (stopDiagLength > 0)
				{
					for (int i = 0; i < stopDiagLength; i++)
					{
						EpisodeDiagnosisVO stopVO = null;
						for (EpisodeDiagnosisVO vo : latestDiagnosisVO)
						{
							if (_fb.getAddDiagnosis()[i].equals(vo.getDiagnosticCode()))
							{
								stopVO = vo;
								break;
							}
						}
						int j = currentDiagLength + repeatDiagLength + i;
						episodeDiaVO[j] = new EpisodeDiagnosisVO();
						episodeDiaVO[j].setDiagnosticCode(stopVO.getDiagnosticCode());
						episodeDiaVO[j].setDiagnosticTypeCode(stopVO.getDiagnosticTypeCode());
						episodeDiaVO[j].setRemarks(_fb.getAddDiagnosisCancelRemarks()[i]);
						episodeDiaVO[j].setDiagnosisCodeType(stopVO.getDiagnosisCodeType());
						HelperMethods.populatetToNullOrEmpty(episodeDiaVO[j], selectedPatientVO);
						episodeDiaVO[j].setIsRepeat(OpdConfig.DIAGNOSIS_IS_REPEAT_STOP);
					}
				}
				/*
				 * if(repeatDiagLength>0) { if(repeatDiagLength>0) { for(int x=0;x<repeatDiagLength;x++) { } for(int x=0;x<repeatDiagLength;x++) {
				 * episodeDiaVO[currentDiagLength + previousDiagLength + x]= repeatDiagnosisVO[x];
				 * HelperMethods.populatetToNullOrEmpty(episodeDiaVO[currentDiagLength + previousDiagLength +
				 * x],selectedPatientVO); } } if(previousDiagLength==0) { for(int x=0;x<repeatDiagLength;x++) {
				 * episodeDiaVO[currentDiagLength + x]= repeatDiagnosisVO[x];
				 * HelperMethods.populatetToNullOrEmpty(episodeDiaVO[currentDiagLength + x],selectedPatientVO); } } }
				 */
			}

			for (int i = 0; i < episodeDiaVO.length; i++)
				episodeDiaVO[i].setEpisodeVisitNo(selectedPatientVO.getEpisodeVisitNo());

			OpdDiagnosisDATA.saveOpdDiagnosisDetails(episodeDiaVO, getUserVO(_rq));

			session.removeAttribute(OpdConfig.REPEAT_DIAGNOSIS_VO);
			objStatus.add(Status.DONE, "Record Saved", "");
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
			System.out.print(e.getMessage());
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return isSave;
	}
	
	
	

	/** 
	 * Searching diagnosis based on diagnosis code & Name
	 * 
	 * @param fb
	 * @param _rq
	 */
	public static void searchCode(OpdDiagnosisFB fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List icdCodes = new ArrayList();
		try
		{
			String searchCode = fb.getSearchCode();
			String searchDisease = fb.getSearchDisease();
			fb.setClickedICDCode("");
			//if (fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_CODE))
			if (fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_ICD_CODE))	//Searching ICD code
			{
				if (searchCode == null || searchCode.trim().equals(""))
					if (searchDisease == null || searchDisease.trim().equals("")) System.out.println("gfhfh");
					else icdCodes = OpdDiagnosisDATA.getICDCodesSearchDetail(searchDisease,"2", getUserVO(_rq));	//Getting list on the basis of ICD Disease name
				else icdCodes = OpdDiagnosisDATA.getICDCodesSearchDetail(searchCode,"1", getUserVO(_rq));		//Getting list on the basis of ICD Code

				if (icdCodes == null) throw new HisRecordNotFoundException("No Code Exists");
				WebUTIL.getSession(_rq).setAttribute(OpdConfig.EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE, icdCodes);
			}
			//if (fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_HOSPITAL_CODE))
			else if (fb.getIcdNHospitalFlagValue().equals(OpdConfig.CHOICE_HOSPITAL_CODE))	//Searching Hospital diagnosis code
			{
				if (searchCode == null || searchCode.trim().equals("")) if (searchDisease == null || searchDisease.trim().equals("")) System.out
						.println("gfhfh");
				else icdCodes = OpdDiagnosisDATA.getHospitalDiagnosisName(searchDisease, getUserVO(_rq));	//Getting list on the basis of Hospital Disease name
				else icdCodes = OpdDiagnosisDATA.getHospitalDiagnosisCodes(searchCode, getUserVO(_rq));		//Getting list on the basis of Hospital Code

				if (icdCodes == null) throw new HisRecordNotFoundException("No Code Exists");
				WebUTIL.getSession(_rq).setAttribute(OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST, icdCodes);
			}
			
			//Added by Vasu on 08.March.2019 for ICD Index Search in MRD 
			else
			{
				if (searchCode == null || searchCode.trim().equals(""))
					if (searchDisease == null || searchDisease.trim().equals("")) System.out.println("gfhfh");
					else icdCodes = OpdDiagnosisDATA.getICDCodesSearchDetail(searchDisease,"2", getUserVO(_rq));	//Getting list on the basis of ICD Disease name
				else icdCodes = OpdDiagnosisDATA.getICDCodesSearchDetail(searchCode,"1", getUserVO(_rq));		//Getting list on the basis of ICD Code

				if (icdCodes == null) throw new HisRecordNotFoundException("No Code Exists");
				WebUTIL.getSession(_rq).setAttribute(OpdConfig.EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE, icdCodes);
				fb.setIcdNHospitalFlag(OpdConfig.CHOICE_ICD_CODE);
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, "No Disease Found", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	/** 
	 * Searching Sub diagnosis based on diagnosis code Clicked
	 * 
	 * @param fb
	 * @param _rq
	 */
	public static void searchSubDiseaseByCode(OpdDiagnosisFB fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		List<IcdDiseaseMasterVO> lstSubDisease = new ArrayList();
		try
		{
			String clickedCode = fb.getClickedICDCode();
			if (clickedCode != null && !clickedCode.trim().equals(""))
				lstSubDisease = OpdDiagnosisDATA.getICDSubDiseases(clickedCode,getUserVO(_rq));

			WebUTIL.getSession(_rq).setAttribute(OpdConfig.OPD_ESSENTIAL_DIAGNOSIS_SUB_DISEASE_LIST_BY_PARENT_CODE, lstSubDisease);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, "No Disease Found", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	/** Populating the selected diagnosis
	 * @param _fb
	 * @param _rq
	 * @param _resp
	 */
	public static void populate(OpdDiagnosisFB _fb, HttpServletRequest _rq, HttpServletResponse _resp)
	{
		List listDiagnosis = null;
		if (_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_CODE)) listDiagnosis = (List) WebUTIL.getSession(_rq).getAttribute(
				OpdConfig.EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE);
		if (_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_HOSPITAL_CODE)) listDiagnosis = (List) WebUTIL.getSession(_rq).getAttribute(
				OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST);

		Entry ent = (Entry) listDiagnosis.get(Integer.parseInt(_fb.getSelectedCode()));

		String label = ent.getLabel();
		String value = (ent.getValue()).trim();
		try
		{
			PrintWriter writer = _resp.getWriter();
			writer.write(label + '^' + value);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/** Not In Use
	 * @param fb
	 * @param _rq
	 * @param _resp
	 */
	public static void getListForTextArea(OpdDiagnosisFB fb, HttpServletRequest _rq, HttpServletResponse _resp)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		List mainList = new ArrayList();
		List newCodeValueList = new ArrayList();
		List newCodeLableList = new ArrayList();
		List tempList = new ArrayList();
		String result = "";
		String searchText = _rq.getParameter("WORD");
		try
		{
			if (fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_CODE)) mainList = (List) session
					.getAttribute(OpdConfig.EssentialBO_DIAGNOSIS_CODE_LIST_UNIT_WISE);
			else mainList = (List) session.getAttribute(OpdConfig.EssentialBO_HOSPITAL_DIAGNOSIS_CODE_LIST);
			// String searchText=fb.getIcdCode();
			ListIterator iterator = mainList.listIterator();
			String searchType = _rq.getParameter("SEARCHTYPE");
			// if(searchType.equalsIgnoreCase("CODE") && !WebUTIL.objectIsAvailableInSession(_rq,"ICDCODEVALUELST")){
			if (searchType.equalsIgnoreCase("CODE"))
			{
				while (iterator.hasNext())
				{
					Entry entry = (Entry) iterator.next();
					String value = entry.getValue();
					String lable = entry.getLabel();
					Entry newEntry = new Entry();
					newEntry.setLabel(value);
					newEntry.setValue(lable);
					newCodeValueList.add(newEntry);
				}
			}
			else
			{
				if (searchType.equalsIgnoreCase("LABLE"))
					newCodeLableList = mainList;
			}
			if (searchType.equalsIgnoreCase("CODE"))
				tempList = newCodeValueList;
			else
				tempList = newCodeLableList;
			
			ListIterator tempIterator = tempList.listIterator();
			searchText = searchText.trim();
			if (!searchText.equals(""))
				while (tempIterator.hasNext())
				{
					Entry entry = (Entry) tempIterator.next();
					String searchValue = entry.getLabel();
					if ((searchValue != null) && (!searchValue.equals("")) && (searchValue.toUpperCase().startsWith(searchText.toUpperCase())))
						result = result + entry.getValue() + "$" + searchValue + "$";
				}
			
			try
			{
				PrintWriter writer = _resp.getWriter();
				if (!searchText.equals(""))
					writer.write(result);
				else writer.write("");
			}
			catch (IOException e)
			{
			}
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	/** Not In Use
	 * @param _fb
	 * @param _rq
	 */
	public static void repeatDiagnosis(OpdDiagnosisFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		String[] icdCode ={ "" };
		String[] disease ={ "" };
		String[] diagnosticCode ={ "" };
		String[] remark ={ "" };
		EpisodeDiagnosisVO[] arrayEpisodeDiagnosisVO = (EpisodeDiagnosisVO[]) WebUTIL.getSession(_rq).getAttribute(
				OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO);
		EpisodeDiagnosisVO previousDiagVO = new EpisodeDiagnosisVO();
		previousDiagVO = arrayEpisodeDiagnosisVO[(arrayEpisodeDiagnosisVO.length) - 1];

		try
		{
			// ////Populating the form bean in case of repeat///////
			if (_fb.getRepeat().equals(OpdConfig.CHOICE_YES))
			{
				// ////////////////Handling the null values
				if (previousDiagVO.getDiagnosticCode() == null)
					previousDiagVO.setDiagnosticCode("");
				if (previousDiagVO.getDignosisName() == null)
					previousDiagVO.setDignosisName("");
				if (previousDiagVO.getDiagnosticTypeName() == null)
					previousDiagVO.setDiagnosticTypeName("");
				if (previousDiagVO.getRemarks() == null)
					previousDiagVO.setRemarks("");
				_fb.setIcdCode(new String[]{ "" });
				_fb.setDignosisName(new String[]{ "" });
				_fb.setHospitalDiagnosisCode(new String[]{ "" });
				_fb.setHospitalDiagnosisName(new String[]{ "" });
				_fb.setRemarks(new String[]{ "" });
				_fb.setDiagonisticTypeCode(new String[]{ "" });

				// //////////////////////////////////////////////////////
				System.out.println("unit=" + _fb.getUnitDiagnosisCodeType());
				System.out.println("diagnosis" + _fb.getDiagnosisCodeType());
				if (_fb.getUnitDiagnosisCodeType().equals(_fb.getDiagnosisCodeType()))
				{
					if (_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_ICD_CODE))
					{
						icdCode[0] = previousDiagVO.getDiagnosticCode();
						_fb.setIcdCode(icdCode);
						disease[0] = previousDiagVO.getDignosisName();
						_fb.setDignosisName(disease);
					}
					if (_fb.getUnitDiagnosisCodeType().equals(OpdConfig.CHOICE_HOSPITAL_CODE))
					{
						icdCode[0] = previousDiagVO.getDiagnosticCode();
						_fb.setHospitalDiagnosisCode(icdCode);
						disease[0] = previousDiagVO.getDignosisName();
						_fb.setHospitalDiagnosisName(disease);
					}
					diagnosticCode[0] = previousDiagVO.getDiagnosticTypeCode();
					_fb.setDiagonisticTypeCode(diagnosticCode);
					remark[0] = previousDiagVO.getRemarks();
					_fb.setRemarks(remark);
				}
				else
					throw new HisNotCompatibleTypeExcetion("Hospital Code and Icd Code cannot map to eah other");
			}
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisNotCompatibleTypeExcetion e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
		}
		finally
		{
			_fb.setArrayPreviousDiagnosisVO(arrayEpisodeDiagnosisVO);
			WebUTIL.setStatus(_rq, objStatus);
		}
	}

	/** Not In Use
	 * @param _fb
	 * @param _rq
	 */
	public static void repeatSelectedDiagnosis(OpdDiagnosisFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		EpisodeDiagnosisVO[] arrayPreviousDiagVO = (EpisodeDiagnosisVO[]) WebUTIL.getSession(_rq)
				.getAttribute(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO);
		System.out.println(_fb.getSelectedDiagnosis().length);
		EpisodeDiagnosisVO[] arrayRepeatDiagnosisVO = new EpisodeDiagnosisVO[_fb.getSelectedDiagnosis().length];
		for (int i = 0; i < _fb.getSelectedDiagnosis().length; i++)
			arrayRepeatDiagnosisVO[i] = arrayPreviousDiagVO[Integer.parseInt(_fb.getSelectedDiagnosis()[i])];
		WebUTIL.setAttributeInSession(_rq, OpdConfig.REPEAT_DIAGNOSIS_VO, arrayRepeatDiagnosisVO);

		_fb.setRepeatedDiagnosisLength(String.valueOf(_fb.getSelectedDiagnosis().length));
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(_rq, objStatus);
	}

	/** Getting previous diagnosis given to the patient diagnosis wise
	 * @param _fb
	 * @param _request
	 */
	public static void getAllPreviousDiagDtl(OpdDiagnosisFB _fb, HttpServletRequest _request)
	{
		EpisodeDiagnosisVO[] arrayPreviousDiagVO = (EpisodeDiagnosisVO[]) WebUTIL.getSession(_request).getAttribute(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO);
		
		int noOfPrevDiag = 0;
		for (int i = 0; i < arrayPreviousDiagVO.length; i++)
		{
			if (_fb.getSelectedDiagCode().trim().equals(arrayPreviousDiagVO[i].getDiagnosticCode().trim()) && !arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_REPEAT))
				noOfPrevDiag++;
		}
		
		int counter = 0;
		EpisodeDiagnosisVO[] AllPrevDiagVO = new EpisodeDiagnosisVO[noOfPrevDiag];
		for (int i = 0; i < arrayPreviousDiagVO.length; i++)
			if (_fb.getSelectedDiagCode().trim().equals(arrayPreviousDiagVO[i].getDiagnosticCode().trim()) && !arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_REPEAT))
				AllPrevDiagVO[counter++] = arrayPreviousDiagVO[i];
		
		WebUTIL.getSession(_request).setAttribute(OpdConfig.PREVIOUS_ALL_DIAGNOSIS_VO, AllPrevDiagVO);
	}
	
	
	
		 
	
	
	/** Revoking the diagnosis
	 * @param _fb
	 * @param _rq
	 */
	public static boolean revokeDiagnosis(OpdDiagnosisFB _fb, HttpServletRequest _rq)
	{
		boolean flag = true;
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		EpisodeDiagnosisVO episodeDiaVO=new EpisodeDiagnosisVO();
		
		try
		{	// commented by manisha gangwar date :18/2/2016 bug fixing (old code not in use)		
			/*DailyPatientVO[] arrayDailyPatVO = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO selectedPatientVO = null;
			for (int i = 0; i < arrayDailyPatVO.length; i++)
				if (_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
					selectedPatientVO = arrayDailyPatVO[i];*/

			PatientDetailVO selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			
			String revokeCode=_fb.getRevokeDiagCode();
			HelperMethods.populate(episodeDiaVO, selectedPatientVO);
			//episodeDiaVO.setDiagnosisCodeType(_fb.getUnitDiagnosisCodeType());
			episodeDiaVO.setDiagnosisCodeType(_fb.getRevokeDiagicdNHospitalFlag());
			OpdDiagnosisDATA.revokeDiagnosis(revokeCode,episodeDiaVO,getUserVO(_rq));
			
		}	
		catch (HisDataAccessException e)
		{
			flag = false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			flag = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			flag = false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			flag = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
		return flag;
	}

	
	/** Getting all revoked and canceled diagnosis
	 * @param _fb
	 * @param _request
	 */
	public static void getAllRevokedDiagnosisDtl(OpdDiagnosisFB _fb, HttpServletRequest _request)
	{
		EpisodeDiagnosisVO[] arrayPreviousDiagVO = (EpisodeDiagnosisVO[]) WebUTIL.getSession(_request).getAttribute(OpdConfig.PREVIOUS_DIAGNOSIS_DETAIL_VO);
		
		/*int noOfRevokedDiag = 0;
		for (int i = 0; i < arrayPreviousDiagVO.length; i++)
			//if (_fb.getSelectedDiagCode().trim().equals(arrayPreviousDiagVO[i].getDiagnosticCode().trim()))
			if (arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_STOP) || arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_CANCELLED))
				noOfRevokedDiag++;
		
		int counter = 0;
		EpisodeDiagnosisVO[] AllRevokedDiagnosisVO = new EpisodeDiagnosisVO[noOfRevokedDiag];
		for (int i = 0; i < arrayPreviousDiagVO.length; i++)
			//if (_fb.getSelectedDiagCode().trim().equals(arrayPreviousDiagVO[i].getDiagnosticCode().trim()))
			if (arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_STOP) || arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_CANCELLED))
				AllRevokedDiagnosisVO[counter++] = arrayPreviousDiagVO[i];*/
		
		
		List<EpisodeDiagnosisVO> lstRevokedDiag=new ArrayList<EpisodeDiagnosisVO>();
		for (int i = 0; i < arrayPreviousDiagVO.length; i++)
		{
			if (arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_STOP) || arrayPreviousDiagVO[i].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_CANCELLED))
				lstRevokedDiag.add(arrayPreviousDiagVO[i]);
		}
		
		Set s=new HashSet();
		for(int i=0;i<lstRevokedDiag.size();i++)
		{
			s.add(lstRevokedDiag.get(i).getDiagnosticCode().trim());
		}
		
		Map rvkDiag=new HashMap();
		Iterator itr=s.iterator();
		while(itr.hasNext())
		{
			String str=(String)itr.next(); 
			List lstRvk=new ArrayList();
			if(rvkDiag.containsKey(str))
				lstRvk=(List)rvkDiag.get(str);
			else
			{
				rvkDiag.put(str,lstRvk);
			}
			
			for(int j=0;j<arrayPreviousDiagVO.length;j++)
			{
				if (str.trim().equals(arrayPreviousDiagVO[j].getDiagnosticCode().trim()) && !arrayPreviousDiagVO[j].getIsRepeat().equals(OpdConfig.DIAGNOSIS_IS_REPEAT_REPEAT))
					lstRvk.add(arrayPreviousDiagVO[j]) ;
			}
			
		}
		WebUTIL.getSession(_request).setAttribute(OpdConfig.ALL_REVOKED_DIAGNOSIS_VO, rvkDiag);
	}
	
	/*public static void getEssentialDiagnosisDetail(OpdDiagnosisFB fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		try
		{
		UserVO userVo=ControllerUTIL.getUserVO(_rq)	;
			EpisodeDiagnosisVO previousDiagVO = new EpisodeDiagnosisVO();	
			previousDiagVO.setPatCrNo(fb.getPatCrNo());
			previousDiagVO.setEpisodeCode(fb.getEpisodeCode());
			previousDiagVO.setEpisodeVisitNo(fb.getEpisodeVisitNo());
		    OpdDiagnosisDATA.getEssentialDiagnosisDetail(previousDiagVO,userVo);
		    fb.setDiagnosticCode(previousDiagVO.getDiagnosticCode());
		    fb.setIcdName(previousDiagVO.getDignosisName());
		    if(!previousDiagVO.equals("null"))
		    {
		    	
		    }
		    

			
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW, "No Disease Found", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
		}
	}*/
	
	
	public static StringBuffer getEssentialDiagnosisDetail(OpdDiagnosisFB fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		//UserVO userVO=ControllerUTIL.getUserVO(_rq);
		//HttpSession session = WebUTIL.getSession(_rq);
		//List treatmentListDetailList=null;
		StringBuffer strBuff= new StringBuffer();
		try
		{	
			
			UserVO userVo=ControllerUTIL.getUserVO(_rq)	;
			EpisodeDiagnosisVO previousDiagVO = new EpisodeDiagnosisVO();	
			previousDiagVO.setPatCrNo(fb.getPatCrNo());
			previousDiagVO.setEpisodeCode(fb.getEpisodeCode());
			previousDiagVO.setEpisodeVisitNo(fb.getEpisodeVisitNo());
			strBuff.append("{header:'Patient Diagnosis Detail',data:[");
			List diagnosisListDetailList=OpdDiagnosisDATA.getEssentialDiagnosisDetail(previousDiagVO,userVo);

;
			for(int i=0;i<diagnosisListDetailList.size();i++)
			{
				EpisodeDiagnosisVO profileVO=(EpisodeDiagnosisVO)diagnosisListDetailList.get(i);
				strBuff.append("{header:'Icd Code',value:'" +profileVO.getDiagnosticCode()  + "'},{header:'Diagnosis Name',value:'" +profileVO.getDignosisName() + "'}");
			
			}
			strBuff.append("]}");
		}		
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
		
		return strBuff;
	}

	
	
	
	
	
}
