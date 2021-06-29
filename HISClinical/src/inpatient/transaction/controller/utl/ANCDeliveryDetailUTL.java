package inpatient.transaction.controller.utl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.ANCDeliveryDetailDATA;
import inpatient.transaction.controller.fb.ANCDeliveryDetailFB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;

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
import hisglobal.vo.ANCDeliveryDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCHistoryDeliveryDetailVO;
import hisglobal.vo.ANCHistoryDetailVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class ANCDeliveryDetailUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void setEssentials(ANCDeliveryDetailFB _fb, HttpServletRequest _request)
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
			_fb.setEntryTime(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "HH:mm"));

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
			_fb.setPatCrNo(voDP.getPatCrNo());
			_fb.setEpisodeCode(voDP.getEpisodeCode());
			_fb.setEpisodeVisitNo(voDP.getEpisodeVisitNo());
			_fb.setDepartmentUnitCode(voDP.getDepartmentUnitCode());
			if (voDP.getPatAdmNo() != null) _fb.setAdmissionNo(voDP.getPatAdmNo());
			else _fb.setAdmissionNo("");

						
			// Check ANC Eligiblility Gender	
			//changed by anshul  previously was getPatGenderType on 12 dec 2012 for postgres
			
			if(patDtlVO.getPatGenderCode() != null)
			{	
				if(!patDtlVO.getPatGenderCode().equals(Config.GENDER_TYPE_CODE_FEMALE))
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
			
			// Getting ANC Detail Essentials
			essentialMap = ANCDeliveryDetailDATA.getEssentials(userVO);

			ANCDetailVO ancDetailVO = new ANCDetailVO();
			HelperMethods.populate(ancDetailVO, _fb);
			Map mp  = ANCDeliveryDetailDATA.getANCDetailsForDelivery(ancDetailVO, userVO);

			String ancDetailFlag = (String) mp.get(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL_FLAG);			
			if(ancDetailFlag.equals(OpdConfig.NO))
			{
				throw new HisRecordNotFoundException("No ANC Detail Found.. Please Enter an ANC Detail First");
			}
			else
			{
				// Getting ANC Detail
				ancDetailVO = (ANCDetailVO) mp.get(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL);
				
				if(ancDetailVO.getGestationStartDate()==null || ancDetailVO.getGestationStartDate().equals(""))
					throw new HisRecordNotFoundException("No Gestation Start Date Found in ANC Detail.. Please Update Gestation Start Date in ANC Detail First");
				
				if(ancDetailVO.getGestationStartDate()!=null && !ancDetailVO.getGestationStartDate().equals("") 
						&& Integer.parseInt(ancDetailVO.getGestationPeriod())<=Integer.parseInt(Config.ANC_DETAIL_MANIMUM_GESTATION_WEEK_FOR_DELIVERY))
					throw new HisRecordNotFoundException("Gestation Period ("+ancDetailVO.getGestationPeriod()+" Weeks) is low for Delivery");
				essentialMap.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL, ancDetailVO);				
				populateFBFromAncDetailVO(_fb, ancDetailVO);
			}
				
			/*
			_fb.setAncDetailGetFlag(ancDetailFlag);
			
			ancDetailVO = (ANCDetailVO) mp.get(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL);
			populateFBFromAncDetailVO(_fb, ancDetailVO);
			
			patDtlVO = (PatientDetailVO) mp.get(InpatientConfig.ANCDETAIL_PATIENT_DETAIL_FROM_HRGT);
			populateFBFromVO(_fb, patDtlVO);

			List<ANCHistoryDetailVO> lstANCHist = (List<ANCHistoryDetailVO>) mp.get(InpatientConfig.ANCDETAIL_PATIENT_ANC_HISTORY_DETAIL_LIST);
			List<ANCHistoryDeliveryDetailVO> lstANCHistDelivery = (List<ANCHistoryDeliveryDetailVO>) mp.get(InpatientConfig.ANCDETAIL_PATIENT_ANC_HISTORY_DELIVERY_DETAIL_LIST);
			setANCHistory(_fb, lstANCHist, lstANCHistDelivery);
			essentialMap.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_HISTORY_DETAIL_LIST, lstANCHist);

			if(_fb.getPatDateOfMarriage()==null)	_fb.setPatDateOfMarriage("");
			if(_fb.getPatDateOfMenarche()==null)	_fb.setPatDateOfMenarche("");
			if(_fb.getLmpDate()==null)				_fb.setLmpDate("");
			if(_fb.getGestationStartDate()==null)	_fb.setGestationStartDate("");
			if(_fb.getUltraSoundEDD()==null)		_fb.setUltraSoundEDD("");
			if(_fb.getExpectedDeliveryDate()==null)	_fb.setExpectedDeliveryDate("");
			if(_fb.getDetectionDate()==null)		_fb.setDetectionDate("");*/
			
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

/*	private static void populatePatAddInfoFromFB(ANCDeliveryDetailFB _fb, PatientDetailVO _vo)
	{
		_vo.setPatCrNo(_fb.getPatCrNo());
		
		
		if(_fb.getPatBloodGroup().equals("-1")) _vo.setPatBloodGroup("");		
		else _vo.setPatBloodGroup(_fb.getPatBloodGroup());
		
		if(_fb.getPatHusbandBloodGroup().equals("-1")) _vo.setPatHusbandBloodGroup("");		
		else _vo.setPatHusbandBloodGroup(_fb.getPatHusbandBloodGroup());

		if(_fb.getPatCaste().equals("-1")) _vo.setPatCaste("");		
		else _vo.setPatCaste(_fb.getPatCaste());
		
		if(_fb.getPatFamilyType().equals("-1")) _vo.setPatFamilyType("");		
		else _vo.setPatFamilyType(_fb.getPatFamilyType());
		
		_vo.setPatGuardianName(_fb.getPatGuardianName());
		_vo.setPatMotherName(_fb.getPatMotherName());
		_vo.setPatHusbandName(_fb.getPatHusbandName());

		if(_fb.getPatOccupation().equals("-1")) _vo.setPatOccupation("");		
		else _vo.setPatOccupation(_fb.getPatOccupation());

		if(_fb.getPatHusbandOccupation().equals("-1")) _vo.setPatHusbandOccupation("");		
		else _vo.setPatHusbandOccupation(_fb.getPatHusbandOccupation());

		if(_fb.getPatFatherOccupation().equals("-1")) _vo.setPatFatherOccupation("");		
		else _vo.setPatFatherOccupation(_fb.getPatFatherOccupation());

		if(_fb.getPatMotherOccupation().equals("-1")) _vo.setPatMotherOccupation("");		
		else _vo.setPatMotherOccupation(_fb.getPatMotherOccupation());

		if(_fb.getPatEducationStatus().equals("-1")) _vo.setPatEducationStatus("");		
		else _vo.setPatEducationStatus(_fb.getPatEducationStatus());

		if(_fb.getPatSpouseEducationStatus().equals("-1")) _vo.setPatSpouseEducationStatus("");		
		else _vo.setPatSpouseEducationStatus(_fb.getPatSpouseEducationStatus());

		if(_fb.getPatFatherEducationStatus().equals("-1")) _vo.setPatFatherEducationStatus("");		
		else _vo.setPatFatherEducationStatus(_fb.getPatFatherEducationStatus());

		if(_fb.getPatMotherEducationStatus().equals("-1")) _vo.setPatMotherEducationStatus("");		
		else _vo.setPatMotherEducationStatus(_fb.getPatMotherEducationStatus());

		_vo.setIsActualDateOfMarriage(_fb.getIsActualAgeOfMenarche());
		_vo.setPatDateOfMarriage(_fb.getPatDateOfMarriage());
		_vo.setPatAgeOfMarriage(_fb.getPatAgeOfMarriage());
		_vo.setPatAgeOfMenarche(_fb.getPatAgeOfMenarche());
	}*/

/*	private static void populateFBFromVO(ANCDeliveryDetailFB _fb, PatientDetailVO _vo)
	{
		_fb.setPatBloodGroup(_vo.getPatBloodGroup());
		_fb.setPatHusbandBloodGroup(_vo.getPatHusbandBloodGroup());
		_fb.setPatCaste(_vo.getPatCaste());
		_fb.setPatFamilyType(_vo.getPatFamilyType());
		_fb.setPatGuardianName(_vo.getPatGuardianName());
		_fb.setPatMotherName(_vo.getPatMotherName());
		_fb.setPatHusbandName(_vo.getPatHusbandName());
		_fb.setPatOccupation(_vo.getPatOccupation());
		_fb.setPatHusbandOccupation(_vo.getPatHusbandOccupation());
		_fb.setPatFatherOccupation(_vo.getPatFatherOccupation());
		_fb.setPatMotherOccupation(_vo.getPatMotherOccupation());
		_fb.setPatEducationStatus(_vo.getPatEducationStatus());
		_fb.setPatSpouseEducationStatus(_vo.getPatSpouseEducationStatus());
		_fb.setPatFatherEducationStatus(_vo.getPatFatherEducationStatus());
		_fb.setPatMotherEducationStatus(_vo.getPatMotherEducationStatus());
		_fb.setPatDateOfMarriage(_vo.getPatDateOfMarriage());
		_fb.setPatAgeOfMarriage(_vo.getPatAgeOfMarriage());		
		_fb.setPatAgeOfMenarche(_vo.getPatAgeOfMenarche());
		_fb.setPatDateOfMenarche(_vo.getPatDateOfMenarche());
	}*/

	private static void populateFBFromAncDetailVO(ANCDeliveryDetailFB _fb, ANCDetailVO _vo)
	{
		_fb.setGravidaNo(_vo.getGravidaNo());

		_fb.setParityNo(_vo.getParityNo());
		_fb.setAbortusNo(_vo.getAbortusNo());
		_fb.setLmpDate(_vo.getLmpDate());
		_fb.setExpectedDeliveryDate(_vo.getExpectedDeliveryDate());
		_fb.setUltraSoundEDD(_vo.getUltraSoundEDD());
		_fb.setGestationStartDate(_vo.getGestationStartDate());
		_fb.setGestationPeriod(_vo.getGestationPeriod());
		_fb.setPregnancyDuration(_vo.getGestationPeriod());
		_fb.setIsHighriskPregnancy(_vo.getIsHighRiskPregnancy());
		_fb.setComplications(_vo.getComplications());
		_fb.setQueckeningWeek(_vo.getQuickeningWeek());
		_fb.setQueckeningRemarks(_vo.getQuickeningRemarks());
		_fb.setIsContraceptiveUsed(_vo.getIsContraceptiveUsed());
		_fb.setComplicationRemarks(_vo.getContraceptiveRemarks());
		_fb.setDetectionMethod(_vo.getDetectionMethod());
		_fb.setDetectionDate(_vo.getDetectionDate());
		_fb.setIsAntiDGiven(_vo.getIsAntiDGiven());

		_fb.setAncEpisodeCode(_vo.getEpisodeCode());
		_fb.setAncEpisodeUnit(_vo.getEpisodeUnit());
		_fb.setAncEpisodeUnitCode(_vo.getEpisodeUnitCode());
	}

	public static boolean saveANCDeliveryDetail(ANCDeliveryDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();		
		boolean flag = true;
		
		HttpSession session = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			session = _request.getSession();

			// Patient ANC Delivery Detail			
			ANCDeliveryDetailVO ancDeliveryDetailVO = new ANCDeliveryDetailVO();
			HelperMethods.populate(ancDeliveryDetailVO, _fb);
		
			// ANC Detail
			ANCDetailVO ancDetailVO = (ANCDetailVO)session.getAttribute(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL);
			ancDetailVO.setDeliveryStatus(_fb.getDeliveryStatus());
			ancDetailVO.setActualDeliveryDate(_fb.getDeliveryDate());
			
			// ANC Detail Outcome Detail
			List<ANCNeonatalDetailVO> lstDelOutcomeDetail = new ArrayList<ANCNeonatalDetailVO>();			

			// ANC History Detail
			ANCHistoryDetailVO ancHistVO = new ANCHistoryDetailVO();
			List<ANCHistoryDeliveryDetailVO> lstANCHistChildren = new ArrayList<ANCHistoryDeliveryDetailVO>();
			getANCHistoryNOutcomeDetail(_fb, _request, ancDetailVO, ancHistVO, lstDelOutcomeDetail, lstANCHistChildren);						

			ANCDeliveryDetailDATA.saveANCDeliveryDetail(ancDeliveryDetailVO, ancDetailVO, ancHistVO, lstDelOutcomeDetail, lstANCHistChildren, userVO);
									
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
	
	public static void getANCHistoryNOutcomeDetail(ANCDeliveryDetailFB _fb, HttpServletRequest _request, ANCDetailVO _ancDetailVO, ANCHistoryDetailVO _ANCHistVO, List<ANCNeonatalDetailVO> _lstANCChildren, List<ANCHistoryDeliveryDetailVO> _lstANCHistChildren)
	{
		try
		{
			int parity = Integer.parseInt(_ancDetailVO.getParityNo()); 
			int abortus = Integer.parseInt(_ancDetailVO.getAbortusNo()); 
				
			_ANCHistVO.setPatCrNo(_fb.getPatCrNo());
			_ANCHistVO.setEpisodeCode(_fb.getEpisodeCode());
			_ANCHistVO.setEpisodeVisitNo(_fb.getEpisodeVisitNo());
			_ANCHistVO.setAdmissionNo(_fb.getAdmissionNo());
			_ANCHistVO.setEntryMode(InpatientConfig.ANC_HISTORY_ENTRY_ONLINE);
			_ANCHistVO.setGravidaNo(_fb.getGravidaNo());
			_ANCHistVO.setDeliveryDate(WebUTIL.getCustomisedSysDate(WebUTIL.getDateFromString(_fb.getDeliveryDate(), "dd-MMM-yyyy"),"dd-MM-yyyy"));
			_ANCHistVO.setDeliveryStatus(_fb.getDeliveryStatus());
			_ANCHistVO.setPregnancyDuration(_fb.getPregnancyDuration());
			_ANCHistVO.setPregnancyRemarks(_fb.getPregnancyRemarks());
			if(_ANCHistVO.getDeliveryStatus().equals(InpatientConfig.DELIVERY_STATUS_DELIVERY))
			{
				int births = Integer.parseInt(_fb.getDeliveryCount());
				parity += births;
				_ANCHistVO.setDeliveryPlaceId(_fb.getDeliveryPlaceId());
				_ANCHistVO.setLabourDuration(_fb.getLabourDuration());
				_ANCHistVO.setDeliveryTypeId(_fb.getDeliveryTypeId());
				//_ANCHistVO.setIsAntiDGiven(_fb.getIsHistIsAntiDGiven()[i]);
				_ANCHistVO.setLabourRemarks(_fb.getLabourRemarks());
						
				for(int j=0;j<births;j++)
				{
					ANCHistoryDeliveryDetailVO ancHistoryDeliveryDetailVO = new ANCHistoryDeliveryDetailVO();
					HelperMethods.populate(ancHistoryDeliveryDetailVO, _ANCHistVO);
					
					ancHistoryDeliveryDetailVO.setBirthNatureId(_fb.getBirthNatureId()[j+1]);
					ancHistoryDeliveryDetailVO.setGenderCode(_fb.getBirthGenderCode()[j+1]);
					ancHistoryDeliveryDetailVO.setWeight(_fb.getBirthWeight()[j+1]);
					//ancHistoryDeliveryDetailVO.setBabyBloodGroupCode(_fb.getbiHistBabyBloodGroupCode()[l]);
					//ancHistoryDeliveryDetailVO.setPresentHealth(_fb.getBirthPresentHealth()[j+1]);
					//ancHistoryDeliveryDetailVO.setDeathAge(_fb.getBirthDeathAge()[j+1]);
					//ancHistoryDeliveryDetailVO.setDeathCause(_fb.getBirthDeathCause()[j+1]);
					_lstANCHistChildren.add(ancHistoryDeliveryDetailVO);
					
					ANCNeonatalDetailVO ancNeonatDetailVO = new ANCNeonatalDetailVO();
					HelperMethods.populate(ancNeonatDetailVO, ancHistoryDeliveryDetailVO);
					
					ancNeonatDetailVO.setDeathStillBirthCause(ancHistoryDeliveryDetailVO.getDeathCause());
					ancNeonatDetailVO.setBirthDateTime(_fb.getBirthDateTime()[j+1]);
					_lstANCChildren.add(ancNeonatDetailVO);
				}
			}
			else
			{
				abortus++;
			}
			_ANCHistVO.setParityNo(Integer.toString(parity));
			_ANCHistVO.setAbortusNo(Integer.toString(abortus));
			_ancDetailVO.setParityNo(Integer.toString(parity));
			_ancDetailVO.setAbortusNo(Integer.toString(abortus));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	// Setting ANC History
	/*private static void setANCHistory(ANCDeliveryDetailFB _fb, List<ANCHistoryDetailVO> _lstANCHistory, List<ANCHistoryDeliveryDetailVO> _lstANCHistoryDelivery) throws Exception
	{
		try
		{
			StringBuilder sb = new StringBuilder("");
			_fb.setInitalGravida(Integer.toString(_lstANCHistory.size()+1));
			if(_lstANCHistory.size()>0)
			{
				Map<String, List<ANCHistoryDeliveryDetailVO>> mapChildren = new HashMap<String, List<ANCHistoryDeliveryDetailVO>>();
				for(ANCHistoryDeliveryDetailVO vo: _lstANCHistoryDelivery)
				{
					String gravidaNo = vo.getGravidaNo();
					if(!mapChildren.containsKey(gravidaNo))
					{
						List<ANCHistoryDeliveryDetailVO> lstChildren = new ArrayList<ANCHistoryDeliveryDetailVO>();
						for(ANCHistoryDeliveryDetailVO delVO: _lstANCHistoryDelivery)
						{
							if(delVO.getGravidaNo().equals(gravidaNo))
								lstChildren.add(delVO);
						}
						mapChildren.put(gravidaNo, lstChildren);
					}
				}
							
				for(ANCHistoryDetailVO vo:_lstANCHistory)
				{
					if(!sb.toString().equals("")) sb.append(ROW_SEP);
					sb.append(vo.getGravidaNo());
					sb.append(ROW_DATA_SEP);
					sb.append(vo.getDeliveryStatus());
					sb.append(ROW_DATA_SEP);
					sb.append((vo.getDeliveryDate()==null)?"":vo.getDeliveryDate());
					sb.append(ROW_DATA_SEP);
					sb.append((vo.getPregnancyDuration()==null)?"":vo.getPregnancyDuration());
					sb.append(ROW_DATA_SEP);
					sb.append((vo.getPregnancyRemarks()==null)?"":vo.getPregnancyRemarks());
					sb.append(ROW_DATA_SEP);
					if(vo.getDeliveryStatus().equals(InpatientConfig.DELIVERY_STATUS_DELIVERY))
					{
						sb.append((vo.getDeliveryPlaceId()==null)?"":vo.getDeliveryPlaceId());
						sb.append(ROW_DATA_SEP);
						sb.append((vo.getLabourDuration()==null)?"":vo.getLabourDuration());
						sb.append(ROW_DATA_SEP);
						sb.append((vo.getDeliveryTypeId()==null)?"":vo.getDeliveryTypeId());
						sb.append(ROW_DATA_SEP);
						sb.append((vo.getIsAntiDGiven()==null)?"":vo.getIsAntiDGiven());
						sb.append(ROW_DATA_SEP);
						sb.append((vo.getLabourRemarks()==null)?"":vo.getLabourRemarks());
						
						List<ANCHistoryDeliveryDetailVO> lstChildren = mapChildren.get(vo.getGravidaNo());

						sb.append(ROW_DATA_SEP);
						sb.append(Integer.toString(lstChildren.size()));
						
						for(ANCHistoryDeliveryDetailVO delVO: lstChildren)
						{
							sb.append(ROW_CHILD_DATA_SEP);
							sb.append(delVO.getBirthNatureId());
							sb.append(ROW_DATA_SEP);
							sb.append((delVO.getGenderCode()==null)?"":delVO.getGenderCode());
							sb.append(ROW_DATA_SEP);
							sb.append((delVO.getWeight()==null)?"":delVO.getWeight());
							sb.append(ROW_DATA_SEP);
							sb.append((delVO.getBabyBloodGroupCode()==null)?"":delVO.getBabyBloodGroupCode());
							sb.append(ROW_DATA_SEP);
							sb.append((delVO.getPresentHealth()==null)?"":delVO.getPresentHealth());
							sb.append(ROW_DATA_SEP);
							sb.append((delVO.getDeathAge()==null)?"":delVO.getDeathAge());
							sb.append(ROW_DATA_SEP);
							sb.append((delVO.getDeathCause()==null)?"":delVO.getDeathCause());
							sb.append(ROW_DATA_SEP);
						}
					}
				}
			}
			_fb.setHistPregData(sb.toString());
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}*/
	
	/*private static ANCVisitDetailVO getANCVisitDetail(ANCDeliveryDetailFB _fb, HttpServletRequest _request) throws Exception
	{
		ANCVisitDetailVO currentVisitDtl = new ANCVisitDetailVO();
		try
		{
			HttpSession session = _request.getSession();
			ANCDetailVO initAncDtl = (ANCDetailVO)session.getAttribute(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL);
			
			HelperMethods.populate(currentVisitDtl, _fb);
			
			if(currentVisitDtl.getComplications().equals(initAncDtl.getComplications()))
				currentVisitDtl.setComplications(null);
			if(currentVisitDtl.getIsHighRiskPregnancy().equals(initAncDtl.getIsHighRiskPregnancy()))
				currentVisitDtl.setIsHighRiskPregnancy(null);
			if(currentVisitDtl.getQuickeningRemarks().equals(initAncDtl.getQuickeningRemarks()))
				currentVisitDtl.setQuickeningRemarks(null);
			if(currentVisitDtl.getQuickeningWeek().equals(initAncDtl.getQuickeningWeek()))
				currentVisitDtl.setQuickeningWeek(null);
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return currentVisitDtl;
	}*/
	/*static String ROW_SEP = "!!##!!";
	static String ROW_DATA_SEP = "!#!";
	static String ROW_CHILD_DATA_SEP = "!#@#!";*/

}
