package inpatient.transaction.controller.utl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.ANCTrimesterChecklistDetailDATA;
import inpatient.transaction.controller.fb.ANCTrimesterChecklistDetailFB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.controller.fb.CRNoFB;

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
import hisglobal.vo.ANCChecklistDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCTrimesterChecklistMasterVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class ANCTrimesterChecklistDetailUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	// Setting Esentials for ANC Child Handover Detail
	public static void setEssentials(ANCTrimesterChecklistDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			_fb.setEntryDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));

			// Getting Patient Detail
			CRNoFB crnoFB = new CRNoFB();
			crnoFB.setPatCrNo(_fb.getPatCrNo());
			InpatientDetailUTL.getInpatientDetailByCrNo(crnoFB, _request);
			PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
						
			// Setting Desk Essentials
			// User Seat, Unit, Ward
			_fb.setUserSeatId(userVO.getUserSeatId());
			//_fb.setDepartmentUnitCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE));
			_fb.setWardCode((String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE));
			// Episode, Visit, Admission No
			/*PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO voDP = null;
			for (PatientDetailVO vo : al)
				if (vo.getPatCrNo().equals(_fb.getPatCrNo()))
				{
					voDP = vo;
					break;
				}*/

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
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
			if (voDP.getPatAdmNo() != null) _fb.setAdmissionNo(voDP.getPatAdmNo());
			else _fb.setAdmissionNo("");

			// Check ANC Eligiblility Gender	
			if(patDtlVO.getPatGenderType() != null)
			{
				if(!patDtlVO.getPatGenderType().equals(Config.GENDER_TYPE_FEMALE))
					throw new HisRecordNotFoundException("Not Eligible for ANC (Unsuitable Gender)");
			}
			// Check ANC Eligiblility Age
			Date sysdate = (Date) session.getAttribute(Config.SYSDATEOBJECT);
			Date dob = WebUTIL.getDateFromString(patDtlVO.getPatDOB(), "dd-MMM-yyyy");
			long diff = sysdate.getTime() - dob.getTime();
			int age = (int)(diff / ((long)1000 * 60 * 60 * 24 * 365));
			if( age < Integer.parseInt(Config.ANC_DETAIL_MINIMUN_AGE_FOR_ANC_ELIGIBILITY) )
			{
				throw new HisRecordNotFoundException("Not Eligible for ANC (Unsuitable Age)");
			}

			// Getting Essentials
			ANCDetailVO ancDtlVO = new ANCDetailVO();
			HelperMethods.populate(ancDtlVO, _fb);
			essentialMap = ANCTrimesterChecklistDetailDATA.getEssentials(ancDtlVO, sysdate, userVO);
			
			/*ANCDetailVO ancDetailVO = new ANCDetailVO();
			HelperMethods.populate(ancDetailVO, _fb);
			Map mp  = ANCDeliveryDetailDATA.getANCDetailsForDelivery(ancDetailVO, userVO);*/

			ANCDetailVO ancDtlVOEss = (ANCDetailVO) essentialMap.get(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL);			
			if(ancDtlVOEss==null)
			{
				throw new HisRecordNotFoundException("No ANC Detail Found for this Unit ..");
			}
			
//			// Finding Patient Trimester
//			Date gestStartDate = WebUTIL.getDateFromString(ancDtlVOEss.getGestationStartDate(), "dd-MMM-yyyy");
//			Calendar calGestStartDate = Calendar.getInstance();
//			calGestStartDate.setTime(gestStartDate);
//			Calendar calSysdate = Calendar.getInstance();
//			calSysdate.setTime(sysdate);
//			int trimester = 0;
//			while(calGestStartDate.before(calSysdate) && trimester!=3)
//			{
//				trimester++;
//				calGestStartDate.add(Calendar.MONTH, Integer.parseInt(InpatientConfig.TRIMESTER_DURATION_IN_MONTHS));
//			}
//			_fb.setTrimester(Integer.toString(trimester));
			
			_fb.setGravidaNo(ancDtlVOEss.getGravidaNo());
			_fb.setTrimester(ancDtlVOEss.getTrimester());
			_fb.setAncNo(ancDtlVOEss.getAncNo());
			_fb.setGestationPeriod(ancDtlVOEss.getGestationPeriod());
			_fb.setGestationStartDate(ancDtlVOEss.getGestationStartDate());
			
			List<ANCTrimesterChecklistMasterVO> lstAllTriChkList = (List<ANCTrimesterChecklistMasterVO>)essentialMap.get(InpatientConfig.ANCDETAIL_ESSENTIAL_CHECKLIST_ALL_TRIMESTER_LIST);
			List<ANCTrimesterChecklistMasterVO> lstTriChkList = new ArrayList<ANCTrimesterChecklistMasterVO>();
			List<ANCTrimesterChecklistMasterVO> lstTriChkDrugs = new ArrayList<ANCTrimesterChecklistMasterVO>();
			List<ANCTrimesterChecklistMasterVO> lstTriChkInvest = new ArrayList<ANCTrimesterChecklistMasterVO>();
			for(ANCTrimesterChecklistMasterVO vo : lstAllTriChkList)
			{
				if(Integer.parseInt(vo.getTrimester())==Integer.parseInt(_fb.getTrimester()))//<=Integer.parseInt(_fb.getTrimester()))
				{
					lstTriChkList.add(vo);
					if(vo.getChecklistType().equals(InpatientConfig.TRIMESTER_CHECKLIST_TYPE_INVESTIGATION))
						lstTriChkInvest.add(vo);
					else if(vo.getChecklistType().equals(InpatientConfig.TRIMESTER_CHECKLIST_TYPE_DRUG))
						lstTriChkDrugs.add(vo);
				}
			}
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST, lstTriChkList);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS, lstTriChkDrugs);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS, lstTriChkInvest);
			/*ANCNeonatalDetailVO voNeoNatDtl = (ANCNeonatalDetailVO)essentialMap.get(InpatientConfig.ANCDETAIL_ESSENTIAL_NEONAT_ADM_DETAIL);			
			if(voNeoNatDtl==null)
				throw new HisRecordNotFoundException("Not Eligible for Neonatal Handover Detail");
			else
			{
				ANCChildHandoverDetailVO voHandoverDtl = (ANCChildHandoverDetailVO)essentialMap.get(InpatientConfig.ANCDETAIL_ESSENTIAL_NEONAT_HANDOVER_DETAIL);
				if(voHandoverDtl!=null) HelperMethods.populate(_fb, voHandoverDtl);
			}*/				

			WebUTIL.setMapInSession(essentialMap, _request);			
			objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Getting All Trimester Checklist
	public static void getAllTrimesterChecklistPopup(ANCTrimesterChecklistDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			List<ANCTrimesterChecklistMasterVO> lstAllTriChkList = (List<ANCTrimesterChecklistMasterVO>) session.getAttribute(InpatientConfig.ANCDETAIL_ESSENTIAL_CHECKLIST_ALL_TRIMESTER_LIST);
			if(lstAllTriChkList==null || lstAllTriChkList.size()==0)
				objStatus.add(Status.UNSUCESSFULL,"No Checklist Found","");
			else
				objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Adding Drug CheckList Detail
	public static void addDrugRow(ANCTrimesterChecklistDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			List<ANCChecklistDetailVO> lstAddedDrugChkList = 
				(List<ANCChecklistDetailVO>)session.getAttribute(InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS_ADDED);
			if(lstAddedDrugChkList==null)
				lstAddedDrugChkList = new ArrayList<ANCChecklistDetailVO>();
			
			ANCChecklistDetailVO ancChklstDtlVO = new ANCChecklistDetailVO();
			HelperMethods.populate(ancChklstDtlVO, _fb);
			ancChklstDtlVO.setChecklistId(_fb.getDrugChecklistId());
			ancChklstDtlVO.setChecklistName(_fb.getDrugChecklistName());
			ancChklstDtlVO.setConductDate(_fb.getDrugConductDate());
			// Finding Patient Trimester
			Date gestStartDate = WebUTIL.getDateFromString(_fb.getGestationStartDate(), "dd-MMM-yyyy");
			Date conductDate = WebUTIL.getDateFromString(_fb.getDrugConductDate(), "dd-MMM-yyyy");
			Calendar calGestStartDate = Calendar.getInstance();
			calGestStartDate.setTime(gestStartDate);
			Calendar calConductDate = Calendar.getInstance();
			calConductDate.setTime(conductDate);
			int trimester = 0;
			while(calGestStartDate.before(calConductDate) && trimester!=3)
			{
				trimester++;
				calGestStartDate.add(Calendar.MONTH, Integer.parseInt(InpatientConfig.TRIMESTER_DURATION_IN_MONTHS));
			}
			ancChklstDtlVO.setTrimester(Integer.toString(trimester));
			
			lstAddedDrugChkList.add(ancChklstDtlVO);
			_fb.setDrugChecklistId(null);
			_fb.setDrugChecklistName(null);
			_fb.setDrugConductDate(null);
			_fb.setDelDrugCheckListIndex(null);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS_ADDED, lstAddedDrugChkList);
			
			objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Deleting Drug CheckList Detail
	public static void deleteDrugRow(ANCTrimesterChecklistDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			List<ANCChecklistDetailVO> lstAddedDrugChkList = 
				(List<ANCChecklistDetailVO>)session.getAttribute(InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS_ADDED);
			if(lstAddedDrugChkList!=null)
			{
				lstAddedDrugChkList.remove(Integer.parseInt(_fb.getDelDrugCheckListIndex()));
				WebUTIL.setAttributeInSession(_request, InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS_ADDED, lstAddedDrugChkList);
			}
			
			objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Adding Investigation CheckList Detail
	public static void addInvstRow(ANCTrimesterChecklistDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			List<ANCChecklistDetailVO> lstAddedInvstChkList = 
				(List<ANCChecklistDetailVO>)session.getAttribute(InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS_ADDED);
			if(lstAddedInvstChkList==null)
				lstAddedInvstChkList = new ArrayList<ANCChecklistDetailVO>();
			
			ANCChecklistDetailVO ancChklstDtlVO = new ANCChecklistDetailVO();
			HelperMethods.populate(ancChklstDtlVO, _fb);
			ancChklstDtlVO.setChecklistId(_fb.getInvstChecklistId());
			ancChklstDtlVO.setChecklistName(_fb.getInvstChecklistName());
			ancChklstDtlVO.setConductDate(_fb.getInvstConductDate());
			ancChklstDtlVO.setResult(_fb.getInvstResult());
			// Finding Patient Trimester
			Date gestStartDate = WebUTIL.getDateFromString(_fb.getGestationStartDate(), "dd-MMM-yyyy");
			Date conductDate = WebUTIL.getDateFromString(_fb.getInvstConductDate(), "dd-MMM-yyyy");
			Calendar calGestStartDate = Calendar.getInstance();
			calGestStartDate.setTime(gestStartDate);
			Calendar calConductDate = Calendar.getInstance();
			calConductDate.setTime(conductDate);
			int trimester = 0;
			while(calGestStartDate.before(calConductDate) && trimester!=3)
			{
				trimester++;
				calGestStartDate.add(Calendar.MONTH, Integer.parseInt(InpatientConfig.TRIMESTER_DURATION_IN_MONTHS));
			}
			ancChklstDtlVO.setTrimester(Integer.toString(trimester));
			
			lstAddedInvstChkList.add(ancChklstDtlVO);
			_fb.setInvstChecklistId(null);
			_fb.setInvstChecklistName(null);
			_fb.setInvstConductDate(null);
			_fb.setInvstResult(null);
			_fb.setDelInvstCheckListIndex(null);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS_ADDED, lstAddedInvstChkList);
			
			objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Deleting Investigation CheckList Detail
	public static void deleteInvstRow(ANCTrimesterChecklistDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			List<ANCChecklistDetailVO> lstAddedInvstChkList = 
				(List<ANCChecklistDetailVO>)session.getAttribute(InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS_ADDED);
			if(lstAddedInvstChkList!=null)
			{
				lstAddedInvstChkList.remove(Integer.parseInt(_fb.getDelInvstCheckListIndex()));
				WebUTIL.setAttributeInSession(_request, InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS_ADDED, lstAddedInvstChkList);
			}
			
			objStatus.add(Status.TRANSINPROCESS);
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
			WebUTIL.setStatus(_request, objStatus);
		}
	}

	// Saving ANC Trimester Checklist Detail
	public static boolean saveANCTrimesterChklstDetail(ANCTrimesterChecklistDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();		
		boolean flag = true;
		
		HttpSession session = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			session = _request.getSession();
			
			// Drug CheckList
			List<ANCChecklistDetailVO> lstAddedDrugChkList = 
				(List<ANCChecklistDetailVO>) session.getAttribute(InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_DRUGS_ADDED);
			if(lstAddedDrugChkList==null)
				lstAddedDrugChkList = new ArrayList<ANCChecklistDetailVO>();
			
			if(!_fb.getDrugChecklistId().equalsIgnoreCase("-1"))
			{
				ANCChecklistDetailVO ancChklstDtlVO = new ANCChecklistDetailVO();
				HelperMethods.populate(ancChklstDtlVO, _fb);
				ancChklstDtlVO.setChecklistId(_fb.getDrugChecklistId());
				ancChklstDtlVO.setChecklistName(_fb.getDrugChecklistName());
				ancChklstDtlVO.setConductDate(_fb.getDrugConductDate());
				// Finding Patient Trimester
				Date gestStartDate = WebUTIL.getDateFromString(_fb.getGestationStartDate(), "dd-MMM-yyyy");
				Date conductDate = WebUTIL.getDateFromString(_fb.getDrugConductDate(), "dd-MMM-yyyy");
				Calendar calGestStartDate = Calendar.getInstance();
				calGestStartDate.setTime(gestStartDate);
				Calendar calConductDate = Calendar.getInstance();
				calConductDate.setTime(conductDate);
				int trimester = 0;
				while(calGestStartDate.before(calConductDate) && trimester!=3)
				{
					trimester++;
					calGestStartDate.add(Calendar.MONTH, Integer.parseInt(InpatientConfig.TRIMESTER_DURATION_IN_MONTHS));
				}
				ancChklstDtlVO.setTrimester(Integer.toString(trimester));
				
				lstAddedDrugChkList.add(ancChklstDtlVO);
			}

		
			// Investigation Checklist
			List<ANCChecklistDetailVO> lstAddedInvstChkList = (List<ANCChecklistDetailVO>)session.getAttribute(InpatientConfig.ANCDETAIL_ESSENTIAL_TRIMESTER_WISE_CHECKLIST_INVESTIGATIONS_ADDED);
			if(lstAddedInvstChkList==null)
				lstAddedInvstChkList = new ArrayList<ANCChecklistDetailVO>();
			
			if(!_fb.getInvstChecklistId().equalsIgnoreCase("-1"))
			{
				ANCChecklistDetailVO ancChklstDtlVO = new ANCChecklistDetailVO();
				HelperMethods.populate(ancChklstDtlVO, _fb);
				ancChklstDtlVO.setChecklistId(_fb.getInvstChecklistId());
				ancChklstDtlVO.setChecklistName(_fb.getInvstChecklistName());
				ancChklstDtlVO.setConductDate(_fb.getInvstConductDate());
				ancChklstDtlVO.setResult(_fb.getInvstResult());
				// Finding Patient Trimester
				Date gestStartDate = WebUTIL.getDateFromString(_fb.getGestationStartDate(), "dd-MMM-yyyy");
				Date conductDate = WebUTIL.getDateFromString(_fb.getInvstConductDate(), "dd-MMM-yyyy");
				Calendar calGestStartDate = Calendar.getInstance();
				calGestStartDate.setTime(gestStartDate);
				Calendar calConductDate = Calendar.getInstance();
				calConductDate.setTime(conductDate);
				int trimester = 0;
				while(calGestStartDate.before(calConductDate) && trimester!=3)
				{
					trimester++;
					calGestStartDate.add(Calendar.MONTH, Integer.parseInt(InpatientConfig.TRIMESTER_DURATION_IN_MONTHS));
				}
				ancChklstDtlVO.setTrimester(Integer.toString(trimester));
				
				lstAddedInvstChkList.add(ancChklstDtlVO);
			}

			// Saving ANC Trimester Checklist Detail
			ANCTrimesterChecklistDetailDATA.saveANCTrimesterChecklistDetail(lstAddedDrugChkList, lstAddedInvstChkList, userVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{

			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return flag;
	}
}
