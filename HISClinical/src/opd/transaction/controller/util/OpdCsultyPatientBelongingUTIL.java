package opd.transaction.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientBelongingVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.data.OpdCsultyPatientBelongingDATA;
import opd.transaction.controller.fb.OpdCsultyPatientBelongingFB;
import registration.RegistrationConfig;
import registration.controller.util.PatDetailUTIL;

public class OpdCsultyPatientBelongingUTIL extends ControllerUTIL
{
	// Direct Call
	//* Setting Patient Belonging Essentials
	public static void getPatientBelongingDirectEssentials(OpdCsultyPatientBelongingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();

		Map essentialMap;
		try
		{
			HttpSession session = WebUTIL.getSession(_rq);
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			
			// Patient Information
			PatDetailUTIL.getPatientDtlByCrno(_fb, _rq);
			PatientVO patVO =(PatientVO) session.getAttribute(RegistrationConfig.PATIENT_VO);

			// Episode Detail
			EpisodeVO episodeVO = OpdCsultyPatientBelongingDATA.getLastEpisodeInEmergency(patVO, userVO);
			WebUTIL.setAttributeInSession(_rq,RegistrationConfig.REGISTRATIONDESK_EPISODEVO_ARR,episodeVO);
			_fb.setEpisodeCode(episodeVO.getEpisodeCode());

			// Episode Visit No
			episodeVO.setPatCrNo(_fb.getPatCrNo());
			episodeVO = OpdCsultyPatientBelongingDATA.retrieveByEpisodeNo(episodeVO, userVO);			
			_fb.setEpisodeVisitNo(episodeVO.getEpisodeVisitNo());

			// Setting Essentials
			essentialMap = OpdCsultyPatientBelongingDATA.getPatientBelongingEssentials(_fb.getPatCrNo(), userVO);
			WebUTIL.setMapInSession(essentialMap, _rq);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
			essentialMap = e.getEssentialMap();
			WebUTIL.setMapInSession(essentialMap, _rq);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	//* Setting Patient Belonging Essentials
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void getPatientBelongingEssentials(OpdCsultyPatientBelongingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();

		Map essentialMap;
		try
		{
			HttpSession session = WebUTIL.getSession(_rq);
			UserVO userVO = getUserVO(_rq);
			setSysdate(_rq);
			PatientDetailVO[] dailyPatientVOs = null;			

			// Setting Essentials from Desk
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(voDP == null || !voDP.getPatCrNo().equals(_fb.getPatCrNo()))
			{
				dailyPatientVOs = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
				for (int i = 0; i < dailyPatientVOs.length; i++)
				{
					if (_fb.getPatCrNo().equals(dailyPatientVOs[i].getPatCrNo()))
					{
						voDP = dailyPatientVOs[i];
						break;
					}
				}
			}
			
			/*DailyPatientVO[] al = (DailyPatientVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			DailyPatientVO voDP = new DailyPatientVO();
			for (int i = 0; i < al.length; i++)
			{
				voDP = (DailyPatientVO) al[i];
				if (voDP.getPatCrNo().equals(_fb.getPatCrNo())) break;
			}*/
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			
			// Patient Information
			PatDetailUTIL.getPatientDtlByCrno(_fb, _rq);

			// Setting Essentials
			essentialMap = OpdCsultyPatientBelongingDATA.getPatientBelongingEssentials(_fb.getPatCrNo(), userVO);
			WebUTIL.setMapInSession(essentialMap, _rq);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
			essentialMap = e.getEssentialMap();
			WebUTIL.setMapInSession(essentialMap, _rq);
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	//* Adding Belonging Detail Row
	public static void addDetailRow(OpdCsultyPatientBelongingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		
		Map selectedItemMap = null;
		List selectedItemList = new ArrayList();
		List belongingList = null;
		String belongingItemName = "";
		
		try
		{
			HttpSession session = WebUTIL.getSession(_rq);
			
			String belongingItemCode = _fb.getBelongingItemCode();
			
			selectedItemMap = (Map) session.getAttribute(OpdConfig.PATIENT_BELONGING_MAP);
			belongingList = (List) session.getAttribute(OpdConfig.ESSENTIALBO_BELONGING_LIST);
			
			if (selectedItemMap == null)
			{
				selectedItemMap = new LinkedHashMap();
			}

			ListIterator listIterator = belongingList.listIterator();
			while (listIterator.hasNext())
			{
				Entry entry = (Entry) listIterator.next();
				if (entry.getValue().equals(belongingItemCode))
				{
					belongingItemName = entry.getLabel();
					break;
				}
			}

			belongingList = (List) WebUTIL.removeEntriesfromOptions(belongingList, belongingItemCode);

			selectedItemList.add(0, belongingItemName);
			selectedItemList.add(1, belongingItemCode);
			selectedItemList.add(2, _fb.getQuantity());
			selectedItemList.add(3, _fb.getRemarks());

			selectedItemMap.put(belongingItemCode, selectedItemList);

			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_BELONGING_MAP, selectedItemMap);
			WebUTIL.setAttributeInSession(_rq, OpdConfig.ESSENTIALBO_BELONGING_LIST, belongingList);

			_fb.setQuantity("");
			_fb.setRemarks("");

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	//* Removing Patient Belonging Row
	public static void removeDetailRow(OpdCsultyPatientBelongingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();		
		try
		{
			HttpSession session = WebUTIL.getSession(_rq);
			
			Map selectedBelongingMap = (Map) session.getAttribute(OpdConfig.PATIENT_BELONGING_MAP);
			List belongingList = (List) session.getAttribute(OpdConfig.ESSENTIALBO_BELONGING_LIST);
			
			String belongingItemCode = _fb.getRemoveBelongingCode();
	
			List selectedItemList = (List) selectedBelongingMap.get(belongingItemCode);
			String belongingItemName = (String) selectedItemList.get(0);
	
			belongingList = (List) WebUTIL.addEntryToOptions(belongingList, belongingItemCode, belongingItemName);
	
			selectedBelongingMap.remove(belongingItemCode);
	
			session.setAttribute(OpdConfig.PATIENT_BELONGING_MAP, selectedBelongingMap);
			session.setAttribute(OpdConfig.ESSENTIALBO_BELONGING_LIST, belongingList);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	//* Saving Belongings Detail
	public static void saveBelongingDetails(OpdCsultyPatientBelongingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();

		Map selectedBelongingMap;
		List SelectedBelongingList = new ArrayList();
		PatientBelongingVO[] patBelongingVO = null;

		int lengthvo = 0;
		int loopCounter = 0;

		try
		{
			HttpSession session = WebUTIL.getSession(_rq);

			// Adding Form Properties to Map
			selectedBelongingMap = (Map) session.getAttribute(OpdConfig.PATIENT_BELONGING_MAP);
			if (selectedBelongingMap == null)
			{
				selectedBelongingMap = new LinkedHashMap();
			}
			SelectedBelongingList.add(0, "");
			SelectedBelongingList.add(1, _fb.getBelongingItemCode());
			SelectedBelongingList.add(2, _fb.getQuantity());
			SelectedBelongingList.add(3, _fb.getRemarks());

			selectedBelongingMap.put(_fb.getBelongingItemCode(), SelectedBelongingList);

			lengthvo = selectedBelongingMap.size();

			patBelongingVO = new PatientBelongingVO[lengthvo];

			Iterator innerMapItr = ((Collection) selectedBelongingMap.values()).iterator();
			while (innerMapItr.hasNext())
			{
				SelectedBelongingList = (List) innerMapItr.next();

				patBelongingVO[loopCounter] = new PatientBelongingVO();
				patBelongingVO[loopCounter].setBelongingItemCode((String) SelectedBelongingList.get(1));
				patBelongingVO[loopCounter].setQuantity((String) SelectedBelongingList.get(2));
				patBelongingVO[loopCounter].setRemarks((String) SelectedBelongingList.get(3));
				patBelongingVO[loopCounter].setPatCrNo(_fb.getPatCrNo());

				loopCounter++;
			}

			OpdCsultyPatientBelongingDATA.saveBelongingDetails(patBelongingVO, getUserVO(_rq));
			
			objStatus.add(Status.DONE, "Belonging Details saved Successfully", "");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	//* Modifying Belonging
	public static void modifyBelonging(OpdCsultyPatientBelongingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		try
		{
			HttpSession session = _rq.getSession();
	
			PatientBelongingVO[] arrayPatBelongingVOs = (PatientBelongingVO[]) session.getAttribute(OpdConfig.PATIENT_BELONGING_DETAILS_VOS);
	
			String selectedCheckBox = _fb.getChk()[0];
			String selectedItmCode = selectedCheckBox.substring(0, selectedCheckBox.indexOf("^"));
			String selectedDate = selectedCheckBox.substring(selectedCheckBox.indexOf("^") + 1);
			for (int j = 0; j < arrayPatBelongingVOs.length; j++)
			{
				if (arrayPatBelongingVOs[j].getBelongingItemCode().equals(selectedItmCode)
						&& arrayPatBelongingVOs[j].getCollectionDate().equals(selectedDate))
				{
					_fb.setBelongingItemCode(arrayPatBelongingVOs[j].getBelongingItemCode());
					_fb.setQuantity(arrayPatBelongingVOs[j].getQuantity());
					_fb.setRemarks(arrayPatBelongingVOs[j].getRemarks());
					WebUTIL.setAttributeInSession(_rq, OpdConfig.SELECTED_PATIENT_BELONGING_VO, arrayPatBelongingVOs[j]);
				}
			}

			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	//* Modifying Belonging Detail
	public static void saveModifyDetails(OpdCsultyPatientBelongingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();

		PatientBelongingVO patBelongingVOs = new PatientBelongingVO();
		PatientBelongingVO savePatBelongingVO = new PatientBelongingVO();

		PatientBelongingVO[] arrayPatBeloningVOs = null;

		try
		{
			UserVO userVO = getUserVO(_rq);

			patBelongingVOs = (PatientBelongingVO) session.getAttribute(OpdConfig.SELECTED_PATIENT_BELONGING_VO);
			String oldItemCode = patBelongingVOs.getBelongingItemCode();
			String CollectionDate = patBelongingVOs.getCollectionDate();

			savePatBelongingVO.setBelongingItemCode(_fb.getBelongingItemCode());
			savePatBelongingVO.setQuantity(_fb.getQuantity());
			savePatBelongingVO.setRemarks(_fb.getRemarks());
			savePatBelongingVO.setPatCrNo(_fb.getPatCrNo());
			savePatBelongingVO.setSeatID(userVO.getSeatId());

			arrayPatBeloningVOs = OpdCsultyPatientBelongingDATA.modifyBelongingDetails(savePatBelongingVO, oldItemCode, CollectionDate, getUserVO(_rq));

			WebUTIL.setAttributeInSession(_rq, OpdConfig.PATIENT_BELONGING_DETAILS_VOS, arrayPatBeloningVOs);

			objStatus.add(Status.NEW, "Record Modified Successfully", "");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}

	//* Saving Hand Over Deatils
	public static void saveHandOverDetails(OpdCsultyPatientBelongingFB _fb, HttpServletRequest _rq)
	{
		Status objStatus = new Status();
		HttpSession session = _rq.getSession();
		PatientBelongingVO[] savePatBelongingVO = new PatientBelongingVO[_fb.getChk().length];
		PatientBelongingVO[] arrayPatBelongingVOs = null;
		int loopCounter = 0;

		try
		{
			UserVO userVO = getUserVO(_rq);

			arrayPatBelongingVOs = (PatientBelongingVO[]) session.getAttribute(OpdConfig.PATIENT_BELONGING_DETAILS_VOS);
			for (int i = 0; i < _fb.getChk().length; i++)
			{
				String selectedCheckBox = _fb.getChk()[i];
				String selectedItmCode = selectedCheckBox.substring(0, selectedCheckBox.indexOf("^"));
				String selectedDate = selectedCheckBox.substring(selectedCheckBox.indexOf("^") + 1);
				for (int j = 0; j < arrayPatBelongingVOs.length; j++)
				{
					if (arrayPatBelongingVOs[j].getBelongingItemCode().equals(selectedItmCode) && arrayPatBelongingVOs[j].getCollectionDate().equals(selectedDate))
					{
						savePatBelongingVO[loopCounter] = new PatientBelongingVO();
						HelperMethods.populate(savePatBelongingVO[loopCounter], arrayPatBelongingVOs[j]);
						savePatBelongingVO[loopCounter].setLastModifiedSeatID(userVO.getSeatId());
						savePatBelongingVO[loopCounter].setHandOverTo(_fb.getHandOverTo() + "^" + _fb.getRelation());
						savePatBelongingVO[loopCounter].setHandOverBy(userVO.getSeatId());
						loopCounter++;
					}
				}
			}

			OpdCsultyPatientBelongingDATA.saveBelongingHandoverDetails(savePatBelongingVO, getUserVO(_rq));
			
			objStatus.add(Status.DONE, "Belonging Hand Over saved Successfully", "");
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.TRANSINPROCESS, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "Data Access Error", "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		catch (HisException e)
		{
			System.out.println("Inside Exception");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "Application Execution Error", "");
		}
		finally
		{
			WebUTIL.setStatus(_rq, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
}