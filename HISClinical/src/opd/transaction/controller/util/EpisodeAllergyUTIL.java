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
import opd.transaction.controller.data.EpisodeAllergyDATA;
import opd.transaction.controller.fb.EpisodeAllergyFB;
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
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class EpisodeAllergyUTIL extends ControllerUTIL
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
	public static void getEssentials(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		// List allergyType=null;
		Map essentialMap = new HashMap();
		//_rq.removeAttribute(OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES);
		//_rq.removeAttribute(OpdConfig.MAP_SELECTED_ALLERGIES);
		//_rq.removeAttribute(OpdConfig.OUTER_ALLERGY_MAP);
		//WebUTIL.setAttributeInSession(_rq, OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES, "");
		//WebUTIL.setAttributeInSession(_rq, OpdConfig.MAP_SELECTED_ALLERGIES, "");
		//WebUTIL.setAttributeInSession(_rq, OpdConfig.OUTER_ALLERGY_MAP, "");
		WebUTIL.getSession(_rq).removeAttribute(OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES);
		WebUTIL.getSession(_rq).removeAttribute(OpdConfig.MAP_SELECTED_ALLERGIES);
		WebUTIL.getSession(_rq).removeAttribute(OpdConfig.OUTER_ALLERGY_MAP);
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
			// List lst=(List)essentialMap.get(OpdConfig.ESSENTIALBO_OPTION_ALLERGY_ADVICE);

			/*
			 * EpisodeAllergiesVO[]
			 * arrayEpisodeAllergyVO=EpisodeAllergyDATA.getEpisodeAllergiesByPatient(selectedPatientVO,userVO);
			 * WebUTIL.getSession(_rq).setAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO, arrayEpisodeAllergyVO);
			 */
			PatAllergyDtlVO[] arrPatAllergyDtlVO = EpisodeAllergyDATA.getEpisodeAllergiesByPatient(patientDetailVO, userVO);
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
	 * Adding New Allergy Type For multirow and getting allergy name based on allergy type
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void addAllergyDetail(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{

		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		List allergyReason = null;
		// Map innerAllergyCodeKeyMap=null;
		Map outerAllergyCodeMap = null;
		Map innerAllergyMap = new LinkedHashMap();
		String allergyName = "";

		try
		{

			Map selectedAllergiesMap = (Map) WebUTIL.getSession(_rq).getAttribute(OpdConfig.MAP_SELECTED_ALLERGIES);
			outerAllergyCodeMap = (Map) session.getAttribute(OpdConfig.OUTER_ALLERGY_MAP);
			if (selectedAllergiesMap == null)
			{
				selectedAllergiesMap = new LinkedHashMap();
				// innerAllergyCodeKeyMap=new LinkedHashMap();
			}
			if (outerAllergyCodeMap == null)
			{
				outerAllergyCodeMap = new LinkedHashMap();
				// innerAllergyCodeKeyMap=new LinkedHashMap();
			}
			UserVO userVO = getUserVO(_rq);
			_fb.setAllergyCode("11"); // fixed for drug allergy only
			allergyReason = EpisodeAllergyDATA.getAllergiesEssential(_fb.getAllergyCode(), userVO);
			List nameWithReasonList = new ArrayList();
			List ListAllergyType = (List) _rq.getSession().getAttribute(OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES);
			List newList = new ArrayList(ListAllergyType);
			for (int i = 0; i < ListAllergyType.size(); i++)
			{
				Entry obj = (Entry) ((List) ListAllergyType).get(i);

				if (obj.getValue().equalsIgnoreCase(_fb.getAllergyCode()))
				{
					allergyName = obj.getLabel();
				}
			}

			newList = (List) WebUTIL.removeEntriesfromOptions(newList, _fb.getAllergyCode());
			nameWithReasonList.add(0, allergyName);
			nameWithReasonList.add(1, allergyReason);
			selectedAllergiesMap.put(_fb.getAllergyCode(), nameWithReasonList);
			outerAllergyCodeMap.put(_fb.getAllergyCode(), innerAllergyMap);
			// selectedAllergiesMap.put(_fb.getAllergyCode(), innerAllergyCodeKeyMap);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.ESSENTIALBO_LIST_ALL_ALLERGY_TYPES, newList);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.MAP_SELECTED_ALLERGIES, selectedAllergiesMap);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.OUTER_ALLERGY_MAP, outerAllergyCodeMap);

			String[] arrayRemarks = _fb.getRemarks();
			String[] arraySensitvity = _fb.getSensitivity();
			String[] arrayduration = _fb.getDuration();
			int lengthRemarks = 0;
			int lengthSensitivity = 0;
			int lengthDuration = 0;

			if (arrayRemarks != null)
			{
				lengthRemarks = arrayRemarks.length;
			}
			else
			{
				arrayRemarks = new String[0];
			}
			String[] newArrayRemarks = new String[lengthRemarks + 1];

			for (int i = 0; i < arrayRemarks.length; i++)
			{
				newArrayRemarks[i] = arrayRemarks[i];
			}
			int len = arrayRemarks.length;

			if (arraySensitvity != null)
			{
				lengthSensitivity = arraySensitvity.length;
			}
			else
			{
				arraySensitvity = new String[0];
			}
			String[] newArraySensitvity = new String[lengthSensitivity + 1];

			for (int i = 0; i < arraySensitvity.length; i++)
			{
				newArraySensitvity[i] = arraySensitvity[i];
			}
			
			if (arrayduration != null)
			{
				lengthDuration = arrayduration.length;
			}
			else
			{
				arrayduration = new String[0];
			}
			String[] newArrayDuration = new String[lengthDuration + 1];

			for (int i = 0; i < arrayduration.length; i++)
			{
				newArrayDuration[i] = arrayduration[i];
			}

			
			newArraySensitvity[len] = "";
			_fb.setSensitivity(newArraySensitvity);

			newArrayRemarks[len] = "";
			_fb.setRemarks(newArrayRemarks);
			
			newArrayDuration[len] = "";
			_fb.setDuration(newArrayDuration);

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, e.getMessage(), "");
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
	public static void addDetailRow(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{
		HttpSession session = _rq.getSession();
		Map selectedAllergiesMap = null;
		Map innerAllergyCodeMap = null;
		Map outerAllergyCodeMap = null;
		List innerReasonList = new ArrayList();
		String reasonName = "";
		List sensitivityList = null;
		String sensitivityName = "";
		Status objStatus = new Status();
		try
		{
			String allergyCode = _fb.getAllergyTableId().substring(0, _fb.getAllergyTableId().indexOf("^"));
			String tableNo = _fb.getAllergyTableId().substring(_fb.getAllergyTableId().indexOf("^") + 1);
			int seqNo = Integer.parseInt(tableNo);
			outerAllergyCodeMap = (Map) session.getAttribute(OpdConfig.OUTER_ALLERGY_MAP);
			if (outerAllergyCodeMap == null)
			{
				outerAllergyCodeMap = new LinkedHashMap();
				innerAllergyCodeMap = new LinkedHashMap();
			}
			if (outerAllergyCodeMap.get(allergyCode) == null)
			{
				innerAllergyCodeMap = new LinkedHashMap();
			}
			else
			{
				innerAllergyCodeMap = (LinkedHashMap) outerAllergyCodeMap.get(allergyCode);
			}

			selectedAllergiesMap = (Map) WebUTIL.getSession(_rq).getAttribute(OpdConfig.MAP_SELECTED_ALLERGIES);

			List nameWithReason = (List) selectedAllergiesMap.get(allergyCode);
			String name = nameWithReason.get(0).toString();
			List reaosn = (List) nameWithReason.get(1);
			ListIterator listIterator = reaosn.listIterator();
			while (listIterator.hasNext())
			{
				Entry entry = (Entry) listIterator.next();
				if (entry.getValue().equals(_fb.getReason()[seqNo]))
				{
					reasonName = entry.getLabel();
					break;
				}
			}

			sensitivityList = (List) session.getAttribute(OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY);
			ListIterator sensitivityListItr = sensitivityList.listIterator();
			while (sensitivityListItr.hasNext())
			{
				Entry entry = (Entry) sensitivityListItr.next();
				if (entry.getValue().equals(_fb.getSensitivity()[seqNo]))
				{
					sensitivityName = entry.getLabel();
					break;
				}
			}
			// Getting Allergy Site Description & Code
			List lst = null;
			Map mp = null;
			if (session.getAttribute(OpdConfig.ALLERGY_SITE_MAPPING_MAP) != null)
			{
				mp = (HashMap) ((HashMap) session.getAttribute(OpdConfig.ALLERGY_SITE_MAPPING_MAP)).get(allergyCode);
				if (mp != null) lst = (List) mp.get(tableNo);
			}
			String allergySite = getAllSiteDesc(lst);
			String allergySiteCode = getAllSiteCode(lst);

			// Getting Allergy Symptom Description & Code
			List lstSymp = null;
			Map mpSymp = null;
			if (session.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP) != null)
			{
				mpSymp = (HashMap) ((HashMap) session.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP)).get(allergyCode);
				if (mpSymp != null) lstSymp = (List) mpSymp.get(tableNo);
			}
			String allergySymptom = getAllSymptomDesc(lstSymp);
			String allergySymptomCode = getAllSymptomCode(lstSymp);

			reaosn = (List) WebUTIL.removeEntriesfromOptions(reaosn, _fb.getReason()[seqNo]);
			nameWithReason.add(1, reaosn);

			selectedAllergiesMap.put(allergyCode, nameWithReason);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.MAP_SELECTED_ALLERGIES, selectedAllergiesMap);

			innerReasonList.add(0, reasonName);
			innerReasonList.add(1, _fb.getRemarks()[seqNo]);
			innerReasonList.add(2, _fb.getSensitivity()[seqNo]);
			innerReasonList.add(3, _fb.getReason()[seqNo]);
			innerReasonList.add(4, sensitivityName);
			innerReasonList.add(5, name);
			innerReasonList.add(6, allergySite); // Adding Allergy Site Description
			innerReasonList.add(7, allergySymptom); // Adding Allergy Site Code
			innerReasonList.add(8, allergySiteCode); // Adding Allergy Symptom Description
			innerReasonList.add(9, allergySymptomCode); // Adding Allergy Symptom Code
			innerReasonList.add(10, _fb.getDuration()[seqNo]); // Adding The Duration
			innerAllergyCodeMap.put(_fb.getReason()[seqNo], innerReasonList);
			outerAllergyCodeMap.put(allergyCode, innerAllergyCodeMap);
			String[] sensitivity = _fb.getSensitivity();
			String[] remarks = _fb.getRemarks();
			String[] duration = _fb.getDuration();
			sensitivity[seqNo] = "-1";
			remarks[seqNo] = "";
			duration[seqNo] = "";

			if (session.getAttribute(OpdConfig.ALLERGY_SITE_MAPPING_MAP) != null) ((HashMap) session.getAttribute(OpdConfig.ALLERGY_SITE_MAPPING_MAP))
					.remove(allergyCode);

			if (session.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP) != null) ((HashMap) session
					.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP)).remove(allergyCode);

			WebUTIL.setAttributeInSession(_rq, OpdConfig.OUTER_ALLERGY_MAP, outerAllergyCodeMap);
			objStatus.add(Status.TRANSINPROCESS);
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
	 * Deleting Allergy Type From multirowDeleting Allergy Name Allergy Type Wise From multirow
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void removeAllergyDetail(EpisodeAllergyFB _fb, HttpServletRequest _rq)
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

	/**
	 * Deleting Allergy Name Allergy Type Wise From multirow
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void removeAllergyRow(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{

		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		Map outerAllergy = (Map) session.getAttribute(OpdConfig.OUTER_ALLERGY_MAP);
		Map selectedAllergy = (Map) session.getAttribute(OpdConfig.MAP_SELECTED_ALLERGIES);
		String allergyCode = _fb.getAllergyTableId().substring(0, _fb.getAllergyTableId().indexOf("^"));
		Map innerAllergy = (Map) outerAllergy.get(allergyCode);
		List reasonwithName = (List) selectedAllergy.get(allergyCode);
		List innerList = (List) innerAllergy.get(_fb.getReasonDetalRowId());
		String reasonName = (String) innerList.get(0);
		List reasonList = (List) reasonwithName.get(1);

		/*
		 * Entry obj=new Entry(); obj.setLabel(reasonName); obj.setValue(_fb.getReasonDetalRowId());
		 * 
		 * reasonList.add(obj);
		 */

		reasonList = (List) WebUTIL.addEntryToOptions(reasonList, _fb.getReasonDetalRowId(), reasonName);
		reasonwithName.add(1, reasonList);
		selectedAllergy.put(allergyCode, reasonwithName);

		innerAllergy.remove(_fb.getReasonDetalRowId());
		outerAllergy.put(allergyCode, innerAllergy);
		session.setAttribute(OpdConfig.OUTER_ALLERGY_MAP, outerAllergy);
		session.setAttribute(OpdConfig.MAP_SELECTED_ALLERGIES, selectedAllergy);
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(_rq, objStatus);

	}

	/**
	 * Saving Allergy of the patient
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static boolean saveAllergyDetails(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{
        boolean isSave = true;
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		Map selectedAllergiesMap = null;
		Map outerAllergy = null;
		Map innerAllergy = null;
		List innerList = null;
		EpisodeAllergiesVO[] episodeAllergiesVO = null;
		DailyPatientVO dailyPatVO = null;
		int lengthvo = 0;
		int loopCounter = 0;
		String reasonName = "";
		try
		{

			dailyPatVO = (DailyPatientVO) session.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
			outerAllergy = (Map) session.getAttribute(OpdConfig.OUTER_ALLERGY_MAP);

			selectedAllergiesMap = (Map) session.getAttribute(OpdConfig.MAP_SELECTED_ALLERGIES);

			if (outerAllergy != null)
			{
				for (int i = 0; i < _fb.getHiddenAllergyCode().length; i++)
				{
					String a = String.valueOf(i);
					List nameWithReason = (List) selectedAllergiesMap.get(_fb.getHiddenAllergyCode()[i]);
					String name = nameWithReason.get(0).toString();
					List reaosn = (List) nameWithReason.get(1);
					ListIterator listIterator = reaosn.listIterator();
					while (listIterator.hasNext())
					{
						Entry entry = (Entry) listIterator.next();
						if (entry.getValue().equals(_fb.getReason()[i]))
						{
							reasonName = entry.getLabel();
							break;
						}
					}
					// ////////////////
					// Getting Allergy Site Description & Code
					List lst = null;
					Map mp = null;
					if (session.getAttribute(OpdConfig.ALLERGY_SITE_MAPPING_MAP) != null)
					{
						mp = (HashMap) ((HashMap) session.getAttribute(OpdConfig.ALLERGY_SITE_MAPPING_MAP)).get(_fb.getHiddenAllergyCode()[i]);
						if (mp != null) lst = (List) mp.get(a);
					}
					String allergySite = getAllSiteDesc(lst);
					String allergySiteCode = getAllSiteCode(lst);

					// Getting Allergy Symptom Description & Code
					List lstSymp = null;
					Map mpSymp = null;
					if (session.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP) != null)
					{
						mpSymp = (HashMap) ((HashMap) session.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP)).get(_fb.getHiddenAllergyCode()[i]);
						if (mpSymp != null) lstSymp = (List) mpSymp.get(a);
					}
					String allergySymptom = getAllSymptomDesc(lstSymp);
					String allergySymptomCode = getAllSymptomCode(lstSymp);

					// /////////////////

					innerAllergy = (Map) outerAllergy.get(_fb.getHiddenAllergyCode()[i]);
					innerList = new ArrayList();
					innerList.add(0, reasonName);
					innerList.add(1, _fb.getRemarks()[i]);
					innerList.add(2, _fb.getSensitivity()[i]);
					innerList.add(3, _fb.getReason()[i]);
					innerList.add(4, name);
					innerList.add(5, name); // Setting Allergy Type
					innerList.add(6, allergySite); // Setting Allergy Site
					innerList.add(7, allergySymptom); // Setting Allergy Symptom
					innerList.add(8, allergySiteCode); // Setting Allergy Site Code
					innerList.add(9, allergySymptomCode); // Setting Allergy Symptom Code
					innerList.add(10, _fb.getDuration()[i]); // Setting Duration
					innerAllergy.put(_fb.getReason()[i], innerList);
					outerAllergy.put(_fb.getHiddenAllergyCode()[i], innerAllergy);
				}

			}
			Iterator itr = ((Collection) outerAllergy.values()).iterator();

			while (itr.hasNext())
			{

				lengthvo = lengthvo + ((Map) itr.next()).size();
			}

			episodeAllergiesVO = new EpisodeAllergiesVO[lengthvo];

			Set outerKeySet = (Set) outerAllergy.keySet();

			Iterator KetSetItr = outerKeySet.iterator();

			while (KetSetItr.hasNext())
			{
				String allergyCode = (String) KetSetItr.next();
				innerAllergy = (Map) outerAllergy.get(allergyCode);

				Iterator innerMapItr = ((Collection) innerAllergy.values()).iterator();
				while (innerMapItr.hasNext())
				{
					innerList = (List) innerMapItr.next();

					episodeAllergiesVO[loopCounter] = new EpisodeAllergiesVO();
					episodeAllergiesVO[loopCounter].setAllergiesCode(allergyCode);
					episodeAllergiesVO[loopCounter].setAllergyTypeCode(allergyCode);
					episodeAllergiesVO[loopCounter].setReasonCode((String) innerList.get(3));
					episodeAllergiesVO[loopCounter].setReasonName((String) innerList.get(0));
					episodeAllergiesVO[loopCounter].setRemarks((String) innerList.get(1));
					episodeAllergiesVO[loopCounter].setSensitivityCode((String) innerList.get(2));
					episodeAllergiesVO[loopCounter].setAllergyTypeName((String) innerList.get(5));
					episodeAllergiesVO[loopCounter].setAllergySite((String) innerList.get(6));
					episodeAllergiesVO[loopCounter].setAllergySymtoms((String) innerList.get(7));
					episodeAllergiesVO[loopCounter].setAllergySiteCode((String) innerList.get(8));
					episodeAllergiesVO[loopCounter].setAllergySymtomsCode((String) innerList.get(9));
					episodeAllergiesVO[loopCounter].setAdvice(_fb.getAdviceText());
					episodeAllergiesVO[loopCounter].setDuration((String) innerList.get(10));
					HelperMethods.populatetToNullOrEmpty(episodeAllergiesVO[loopCounter], dailyPatVO);
					loopCounter++;
				}
			}

			EpisodeAllergyDATA.saveAllergyDetails(episodeAllergiesVO, getUserVO(_rq));
			objStatus.add(Status.TRANSINPROCESS, "Allergies Details Saved Successfully", "");
			// objStatus.add(Status.DONE,"Allergies Details saved Successfully","");
		}
		catch (HisRecordNotFoundException e)
		{
			isSave = false;
			e.printStackTrace();
			objStatus.add(Status.NEW, e.getMessage(), "");
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
		}
		catch (Exception e)
		{
			isSave = false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{

			WebUTIL.setStatus(_rq, objStatus);

		}
		return isSave;
	}

	/**
	 * Viewing the allergies detail of the given allergy
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void getAllergyDtlEpisodeWise(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		DailyPatientVO selectedPatientVO = null;
		UserVO userVO = getUserVO(_rq);
		String allergyTypeCode = _fb.getSelectedAllergyCode();
		String allergyID = _fb.getSelectedAllergyID();
		try
		{
			selectedPatientVO = (DailyPatientVO) session.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);

			EpisodeAllergiesVO[] arrEpisodeAllergyDtlVO = EpisodeAllergyDATA.getAllergyDtlEpisodeWise(allergyID, allergyTypeCode, selectedPatientVO,
					userVO);
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

	/**
	 * Getting list of Allergy Site
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void getAllAllergySite(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		UserVO userVO = getUserVO(_rq);
		try
		{
			List lstAllergySite = EpisodeAllergyDATA.getAllAllergySite(userVO);

			String alleryType = _fb.getAllergyTableId().split("\\^")[0];
			String reasonSeq = _fb.getAllergyTableId().split("\\^")[1];
			List lst = null;
			Map mp = null;
			if (session.getAttribute(OpdConfig.ALLERGY_SITE_MAPPING_MAP) != null)
			{
				mp = (HashMap) ((HashMap) session.getAttribute(OpdConfig.ALLERGY_SITE_MAPPING_MAP)).get(alleryType);
				if (mp != null) lst = (List) mp.get(reasonSeq);
			}
			Map mapSeleceted = new HashMap<String, String>();
			if (lst != null) for (int i = 0; i < lst.size(); i++)
			{
				Entry e = (Entry) lst.get(i);
				mapSeleceted.put(e.getValue(), e.getValue());
			}
			_rq.setAttribute(OpdConfig.ALLERGY_SITE_MAPPING_LIST, mapSeleceted);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.ALL_ALLERGY_SITE_LIST, lstAllergySite);
			objStatus.add(Status.NEW, "", "");
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
			_rq.setAttribute(Config.STATUS_OBJECT, objStatus);

		}
	}

	/**
	 * Getting Allergy Symptom
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void getCommonNAllergyTypeSymptom(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		UserVO userVO = getUserVO(_rq);
		// String allergyTypeCode=_fb.getSelAllergyTypeForSymp();
		try
		{
			String alleryType = _fb.getAllergyTableId().split("\\^")[0];
			String reasonSeq = _fb.getAllergyTableId().split("\\^")[1];

			Map map = EpisodeAllergyDATA.getCommonNAllergyTypeSymptom(alleryType, userVO);

			List lst = null;
			Map mp = null;
			if (session.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP) != null)
			{
				mp = (HashMap) ((HashMap) session.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP)).get(alleryType);
				if (mp != null) lst = (List) mp.get(reasonSeq);
			}
			Map mapSeleceted = new HashMap<String, String>();
			if (lst != null) for (int i = 0; i < lst.size(); i++)
			{
				Entry e = (Entry) lst.get(i);
				mapSeleceted.put(e.getValue(), e.getValue());
			}
			_rq.setAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_LIST, mapSeleceted);
			WebUTIL.setMapInSession(map, _rq);

			objStatus.add(Status.NEW, "", "");

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
			_rq.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}

	/**
	 * Adding allergy site of a given allergy name
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void addAllergySite(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		// String allergySiteName="";

		try
		{
			Map<String, Map<String, List<Entry>>> mp;
			if (session.getAttribute(OpdConfig.ALLERGY_SITE_MAPPING_MAP) == null) mp = new HashMap<String, Map<String, List<Entry>>>();
			else mp = (HashMap<String, Map<String, List<Entry>>>) session.getAttribute(OpdConfig.ALLERGY_SITE_MAPPING_MAP);

			String alleryType = _fb.getAllergyTableId().split("\\^")[0];
			String reasonSeq = _fb.getAllergyTableId().split("\\^")[1];

			Map<String, List<Entry>> mapInner = mp.get(alleryType);
			if (mapInner == null)
			{
				mapInner = new HashMap<String, List<Entry>>();
				mp.put(alleryType, mapInner);
			}
			// List<Entry> lstSites = mapInner.get(reasonSeq);
			// if(lstSites==null)
			// {
			List<Entry> lstSites = new ArrayList<Entry>();
			mapInner.put(reasonSeq, lstSites);
			// }

			int len = _fb.getAllergySite().length;
			for (int i = 0; i < len; i++)
			{
				Entry ent = new Entry();
				ent.setValue(_fb.getAllergySite()[i].split("#")[0]);
				ent.setLabel(_fb.getAllergySite()[i].split("#")[1]);
				lstSites.add(ent);
				/*
				 * if(i==0) allergySiteName=_fb.getAllergySite()[i].split("#")[1]; else
				 * allergySiteName=allergySiteName+","+_fb.getAllergySite()[i].split("#")[1];
				 */
			}

			_rq.setAttribute(OpdConfig.ALLERGY_SITE_MAPPING_LIST, new HashMap());
			// WebUTIL.setAttributeInSession(_rq, OpdConfig.ALLERGY_SITE_DESCRIPTION , allergySiteName);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.ALLERGY_SITE_MAPPING_MAP, mp);

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
	 * Adding allergy Symptom of a given allergy name
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void addSymptom(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		// String allergySymptomName="";

		try
		{
			Map<String, Map<String, List<Entry>>> mp;
			if (session.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP) == null) mp = new HashMap<String, Map<String, List<Entry>>>();
			else mp = (HashMap<String, Map<String, List<Entry>>>) session.getAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP);

			String alleryType = _fb.getAllergyTableId().split("\\^")[0];
			String reasonSeq = _fb.getAllergyTableId().split("\\^")[1];

			Map<String, List<Entry>> mapInner = mp.get(alleryType);
			if (mapInner == null)
			{
				mapInner = new HashMap<String, List<Entry>>();
				mp.put(alleryType, mapInner);
			}
			// List<Entry> lstSites = mapInner.get(reasonSeq);
			// if(lstSites==null)
			// {
			List<Entry> lstSymptom = new ArrayList<Entry>();
			mapInner.put(reasonSeq, lstSymptom);
			// }

			int len = _fb.getAllergySymtoms().length;
			for (int i = 0; i < len; i++)
			{
				Entry ent = new Entry();
				ent.setValue(_fb.getAllergySymtoms()[i].split("#")[0]);
				ent.setLabel(_fb.getAllergySymtoms()[i].split("#")[1]);
				lstSymptom.add(ent);
				/*
				 * if(i==0) allergySiteName=_fb.getAllergySite()[i].split("#")[1]; else
				 * allergySiteName=allergySiteName+","+_fb.getAllergySite()[i].split("#")[1];
				 */
			}

			_rq.setAttribute(OpdConfig.ALLERGY_SYMPTOM_MAPPING_LIST, new HashMap());
			// WebUTIL.setAttributeInSession(_rq, OpdConfig.ALLERGY_SITE_DESCRIPTION , allergySiteName);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.ALLERGY_SYMPTOM_MAPPING_MAP, mp);
			/*
			 * int len=_fb.getAllergySymtoms().length; for(int i=0;i<len;i++) { if(i==0)
			 * allergySymptomName=_fb.getAllergySymtoms()[i].split("#")[1]; else
			 * allergySymptomName=allergySymptomName+","+_fb.getAllergySymtoms()[i].split("#")[1]; }
			 * WebUTIL.setAttributeInSession(_rq, OpdConfig.ALLERGY_SYMPTOMS_DESCRIPTION , allergySymptomName);
			 */

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
	 * Iterating the allergy site list and concating the allergy site name with comma to make a string
	 * 
	 * @param lst
	 * @return
	 */
	private static String getAllSiteDesc(List<Entry> lst)
	{
		String desc = "";
		if (lst == null || lst.size() <= 0) return desc;
		for (Entry e : lst)
			desc += e.getLabel() + ",";
		if (!desc.equals("")) desc = desc.substring(0, desc.length() - 1);
		return desc;
	}

	/**
	 * Iterating the allergy site list and concating the allergy site code with comma to make a string
	 * 
	 * @param lst
	 * @return
	 */
	private static String getAllSiteCode(List<Entry> lst)
	{
		String code = "";
		if (lst == null || lst.size() <= 0) return code;
		for (Entry e : lst)
			code += e.getValue() + "#";
		if (!code.equals("")) code = code.substring(0, code.length() - 1);
		return code;
	}

	/**
	 * Iterating the allergy symptom list and concating the allergy symptom name with comma to make a string
	 * 
	 * @param lst
	 * @return
	 */
	private static String getAllSymptomDesc(List<Entry> lst)
	{
		String desc = "";
		if (lst == null || lst.size() <= 0) return desc;
		for (Entry e : lst)
			desc += e.getLabel() + ",";
		if (!desc.equals("")) desc = desc.substring(0, desc.length() - 1);
		return desc;
	}

	/**
	 * Iterating the allergy symptom list and concating the allergy symptom code with comma to make a string
	 * 
	 * @param lst
	 * @return
	 */
	private static String getAllSymptomCode(List<Entry> lst)
	{
		String code = "";
		if (lst == null || lst.size() <= 0) return code;
		for (Entry e : lst)
			code += e.getValue() + "#";
		if (!code.equals("")) code = code.substring(0, code.length() - 1);
		return code;
	}

	/**
	 * Getting All Allergy Advice list
	 * 
	 * @param _fb
	 * @param _rq
	 */
	public static void addAdvice(EpisodeAllergyFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		// HttpSession session=_rq.getSession();
		// List lstAdvice=null;

		try
		{
			// lstAdvice=(List)session.getAttribute(OpdConfig.ESSENTIALBO_OPTION_ALLERGY_ADVICE);

			List lstAdvice = EpisodeAllergyDATA.getAdvice(getUserVO(_rq));
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
	public static void calculateDay(EpisodeAllergyFB fb, HttpServletRequest request)
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
	public static boolean revokeAllergy(EpisodeAllergyFB fb, HttpServletRequest request)
	{
		boolean isRevoked = true;
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
					epiAllergyVO.setAllergiesCode(arrPatAllergyDtlVO[i].getAllergyTypeCode());
					epiAllergyVO.setAllergyTypeCode(arrPatAllergyDtlVO[i].getAllergyTypeCode());
					epiAllergyVO.setAllergyTypeName(arrPatAllergyDtlVO[i].getAllergyTypeName());
					epiAllergyVO.setReasonCode(arrPatAllergyDtlVO[i].getAllergyID());
					epiAllergyVO.setReasonName(arrPatAllergyDtlVO[i].getAllergyName());
					//epiAllergyVO.setSensitivityCode(arrPatAllergyDtlVO[i].getsAllergyName());
					//sensivity--add
				}
			}
			int i = Integer.parseInt(fb.getIndex());
			epiAllergyVO.setRemarks(fb.getRevokeRemarks()[i]);

			EpisodeAllergyDATA.revokeAllergy(epiAllergyVO, getUserVO(request));
			objStatus.add(Status.DONE, "", "Patient Allergy is  Revoked");

		}
		catch (HisDataAccessException e)
		{
			isRevoked = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			isRevoked = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			isRevoked = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			isRevoked = false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return isRevoked;
	}

}
