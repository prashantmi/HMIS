package inpatient.transaction.controller.utl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.ANCNeonatalDetailDATA;
import inpatient.transaction.controller.fb.ANCNeonatalDetailFB;

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
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.ANCDeliveryDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCNeonatalApgarVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class ANCNeonatalDetailUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	// Setting Esentials for ANC New Natal Detail
	public static void setEssentials(ANCNeonatalDetailFB _fb, HttpServletRequest _request)
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

			// Getting ANC Detail Essentials
			ANCNeonatalDetailVO ancNeoNatVO = new ANCNeonatalDetailVO();
			HelperMethods.populate(ancNeoNatVO, _fb);
			essentialMap = ANCNeonatalDetailDATA.getEssentials(ancNeoNatVO ,userVO);
			
			List<ANCNeonatalDetailVO> lstDeliveryOutcome = (ArrayList<ANCNeonatalDetailVO>)essentialMap.get(InpatientConfig.ANCDETAIL_DELIVERY_OUTCOME_LIST);			
			if(lstDeliveryOutcome.size()==0)
				throw new HisRecordNotFoundException("No New Natal Detail Found");

			WebUTIL.setMapInSession(essentialMap, _request);			
			objStatus.add(Status.LIST);
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

	// Getting Birth Detail Of Neo Natal for Birth Certificate
	public static void getBirthDetailOfNeoNatal(ANCNeonatalDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			setSysdate(_request);
			_fb.setEntryDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
			_fb.setEntryTime(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "HH:mm"));

			List<ANCNeonatalDetailVO> lstNeoNatDtl = (ArrayList<ANCNeonatalDetailVO>)session.getAttribute(InpatientConfig.ANCDETAIL_DELIVERY_OUTCOME_LIST);			
			ANCNeonatalDetailVO ancNeoNatVO = lstNeoNatDtl.get(Integer.parseInt(_fb.getSelBabySerialCert()));
			HelperMethods.populatetToNullOrEmpty(_fb, ancNeoNatVO);

			List<ANCNeonatalApgarVO> lstApgars = (List<ANCNeonatalApgarVO>) session.getAttribute(InpatientConfig.ANCDETAIL_DELIVERY_OUTCOME_APGAR_DETAIL_LIST);
			_fb.setSelectedNeoNatSlNo(ancNeoNatVO.getSlNo());
			
			List<ANCNeonatalApgarVO> lstNeoNatApgars = new ArrayList<ANCNeonatalApgarVO>();
			for(ANCNeonatalApgarVO vo : lstApgars)
				if(vo.getSlNo().equals(_fb.getSelectedNeoNatSlNo()))
					lstNeoNatApgars.add(vo);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST, lstNeoNatApgars);
			
			// Patient Detail
			PatientDetailVO patDtlVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			populateFBFromVO(_fb,patDtlVO);
			
			// ANC DEtail
			ANCDetailVO ancDetailVO = (ANCDetailVO)session.getAttribute(InpatientConfig.ANCDETAIL_PATINET_ANC_DETAIL_VO);
			populateFBFromAncDetailVO(_fb,ancDetailVO);
			
			// ANC Delivery Detail
			ANCDeliveryDetailVO ancDelDtlVO = (ANCDeliveryDetailVO)session.getAttribute(InpatientConfig.ANCDETAIL_PATINET_ANC_DELIVERY_DETAIL);
			populateFBFromAncDeliveryDetailVO(_fb, ancDelDtlVO);
						
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

	// Getting Esentials for ANC New Natal Detail
	public static void getNewNatalDetail(ANCNeonatalDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			setSysdate(_request);
			_fb.setEntryDate(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy"));
			_fb.setEntryTime(WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "HH:mm"));

			List<ANCNeonatalDetailVO> lstNeoNatDtl = (ArrayList<ANCNeonatalDetailVO>)session.getAttribute(InpatientConfig.ANCDETAIL_DELIVERY_OUTCOME_LIST);
			List<ANCNeonatalApgarVO> lstApgars = (List<ANCNeonatalApgarVO>) session.getAttribute(InpatientConfig.ANCDETAIL_DELIVERY_OUTCOME_APGAR_DETAIL_LIST);

			ANCNeonatalDetailVO ancNeoNatVO = lstNeoNatDtl.get(Integer.parseInt(_fb.getSelectedNeoNat()));
			_fb.setUmbilicalArteries(null);
			HelperMethods.populatetToNullOrEmpty(_fb, ancNeoNatVO);			
			_fb.setSelectedNeoNatSlNo(ancNeoNatVO.getSlNo());
			
			List<ANCNeonatalApgarVO> lstNeoNatApgars = new ArrayList<ANCNeonatalApgarVO>();
			for(ANCNeonatalApgarVO vo : lstApgars)
				if(vo.getSlNo().equals(_fb.getSelectedNeoNatSlNo()))
					lstNeoNatApgars.add(vo);
			
			
			
			List<Entry> lstApgarTime = (List<Entry>) session.getAttribute(InpatientConfig.ANCDETAIL_ESSENTIAL_APGAR_TIME_LIST);
			List<Entry> lstApgarTimeRest = new ArrayList<Entry>();
			for(Entry entObj : lstApgarTime)
			{
				ANCNeonatalApgarVO apgarVO = null;
				for(ANCNeonatalApgarVO vo : lstNeoNatApgars)
					if(vo.getApgarTime().equals(entObj.getValue()))
					{
						apgarVO = vo;
						break;
					}
				if(apgarVO==null)	lstApgarTimeRest.add(entObj);
			}
			WebUTIL.setAttributeInSession(_request, InpatientConfig.ANCDETAIL_ESSENTIAL_SELECTED_NEO_NAT_APGAR_TIME_LIST, lstApgarTimeRest);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST, lstNeoNatApgars);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.ANCDETAIL_SELECTED_NEONAT_ADDED_APGAR_DETAIL_LIST, new ArrayList<ANCNeonatalApgarVO>());
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
	
	// Getting ANC Apgar for Popup
	public static void getSetApgarDtlForPopup(ANCNeonatalDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			List<ANCNeonatalApgarVO> lstApgars = (List<ANCNeonatalApgarVO>) session.getAttribute(InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST);
			if(lstApgars==null || lstApgars.size()==0)
				objStatus.add(Status.UNSUCESSFULL,"No Previous Apgar Detail Found","");
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

	// Getting ANC Apgar for Popup
	public static void addMoreApgarDetail(ANCNeonatalDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			List<ANCNeonatalApgarVO> lstApgars = (List<ANCNeonatalApgarVO>) session.getAttribute(InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST);
			List<ANCNeonatalApgarVO> lstAddedApgars = (List<ANCNeonatalApgarVO>) session.getAttribute(InpatientConfig.ANCDETAIL_SELECTED_NEONAT_ADDED_APGAR_DETAIL_LIST);
			if(lstAddedApgars==null)	lstAddedApgars=new ArrayList<ANCNeonatalApgarVO>();
			
			
			if(_fb.getApgarTime()!=null && !_fb.getApgarTime().trim().equals("-1"))
			{
				ANCNeonatalApgarVO apgarVO = new ANCNeonatalApgarVO();
				HelperMethods.populate(apgarVO, _fb);
				apgarVO.setSlNo(_fb.getSelectedNeoNatSlNo());			
				lstAddedApgars.add(apgarVO);
				lstApgars.add(apgarVO);
			}

			List<Entry> lstApgarTime = (List<Entry>) session.getAttribute(InpatientConfig.ANCDETAIL_ESSENTIAL_APGAR_TIME_LIST);
			List<Entry> lstApgarTimeRest = new ArrayList<Entry>();
			for(Entry entObj : lstApgarTime)
			{
				ANCNeonatalApgarVO apgarVO = null;
				for(ANCNeonatalApgarVO vo : lstApgars)
					if(vo.getApgarTime().equals(entObj.getValue()))
					{
						apgarVO = vo;
						apgarVO.setApgarTimeDesc(entObj.getLabel());
						break;
					}
				if(apgarVO==null)	lstApgarTimeRest.add(entObj);
			}

			clearFBAfterApgarAdd(_fb);

			WebUTIL.setAttributeInSession(_request, InpatientConfig.ANCDETAIL_ESSENTIAL_SELECTED_NEO_NAT_APGAR_TIME_LIST, lstApgarTimeRest);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.ANCDETAIL_SELECTED_NEONAT_ADDED_APGAR_DETAIL_LIST, lstAddedApgars);
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

	private static void clearFBAfterApgarAdd(ANCNeonatalDetailFB _fb)
	{
		if(_fb.getBabyBloodGroupCode()!=null && _fb.getBabyBloodGroupCode().trim().equals("-1")) _fb.setBabyBloodGroupCode(null);
		if(_fb.getGenderCode()!=null && _fb.getGenderCode().trim().equals("-1")) _fb.setGenderCode(null);
		if(_fb.getWeight()!=null && _fb.getWeight().trim().equals("")) _fb.setWeight(null);
		if(_fb.getDeathAge()!=null && _fb.getDeathAge().trim().equals("")) _fb.setDeathAge(null);
		if(_fb.getBabylength()!=null && _fb.getBabylength().trim().equals("")) _fb.setBabylength(null);
		if(_fb.getIsAnomolyPresent()!=null && _fb.getIsAnomolyPresent().trim().equals("")) _fb.setIsAnomolyPresent(null);
		if(_fb.getUmbilicalArteries()!=null && _fb.getUmbilicalArteries().trim().equals("-1")) _fb.setUmbilicalArteries(null);
		if(_fb.getTransferToFlag()!=null && _fb.getTransferToFlag().trim().equals("")) _fb.setTransferToFlag(null);
		if(_fb.getIsFootPrintTaken()!=null && _fb.getIsFootPrintTaken().trim().equals("")) _fb.setIsFootPrintTaken(null);
		if(_fb.getLiquorAppearance()!=null && _fb.getLiquorAppearance().trim().equals("")) _fb.setLiquorAppearance(null);
		if(_fb.getIsCordBloodSampleTaken()!=null && _fb.getIsCordBloodSampleTaken().trim().equals("")) _fb.setIsCordBloodSampleTaken(null);
		if(_fb.getHeadCircumferences()!=null && _fb.getHeadCircumferences().trim().equals("")) _fb.setHeadCircumferences(null);
		if(_fb.getCryDateTime()!=null && _fb.getCryDateTime().trim().equals("")) _fb.setCryDateTime(null);
		if(_fb.getFeedDateTime()!=null && _fb.getFeedDateTime().trim().equals("")) _fb.setFeedDateTime(null);
		if(_fb.getUrineDateTime()!=null && _fb.getUrineDateTime().trim().equals("")) _fb.setUrineDateTime(null);
		if(_fb.getMotherFeedDateTime()!=null && _fb.getMotherFeedDateTime().trim().equals("")) _fb.setMotherFeedDateTime(null);
		if(_fb.getIsBirthTrauma()!=null && _fb.getIsBirthTrauma().trim().equals("")) _fb.setIsBirthTrauma(null);
		if(_fb.getTraumaCauseId()!=null && _fb.getTraumaCauseId().trim().equals("-1")) _fb.setTraumaCauseId(null);
		if(_fb.getWhenStillBirthDetection()!=null && _fb.getWhenStillBirthDetection().trim().equals("")) _fb.setWhenStillBirthDetection(null);

		_fb.setApgarTime(null);
		_fb.setHeartRate(null);
		_fb.setHeartRateApgar(null);
		_fb.setRespiration(null);
		_fb.setRespirationApgar(null);
		_fb.setActivity(null);
		_fb.setActivityApgar(null);
		_fb.setColor(null);
		_fb.setColorApgar(null);
		_fb.setGrimace(null);
		_fb.setGrimaceApgar(null);
		_fb.setApgarScore(null);
	}

	private static void populateFBFromVO(ANCNeonatalDetailFB _fb, PatientDetailVO _vo)
	{
		_vo.setPatientName();
		_fb.setPatName(_vo.getPatName());
		_fb.setPatHusbandName(_vo.getPatHusbandName());
		_fb.setPatAge(_vo.getPatAge());
		_fb.setAdmDateTime(_vo.getAdmDateTime());
		_fb.setPatSpouceName(_vo.getPatSpouceName());
		_fb.setAdmissionNo(_vo.getPatAdmNo());
	}

	private static void populateFBFromAncDetailVO(ANCNeonatalDetailFB _fb, ANCDetailVO _vo)
	{
		_fb.setParityNo(_vo.getParityNo());
		_fb.setAbortusNo(_vo.getAbortusNo());
		_fb.setLmpDate(_vo.getLmpDate());
		_fb.setExpectedDeliveryDate(_vo.getExpectedDeliveryDate());
		_fb.setPregnancyDuration(_vo.getGestationPeriod());
	}

	private static void populateFBFromAncDeliveryDetailVO(ANCNeonatalDetailFB _fb, ANCDeliveryDetailVO _vo)
	{
		_fb.setDeliveryCount(_vo.getDeliveryCount());
		_fb.setIsInduced(_vo.getIsInduced());
		_fb.setIndicationOfInduction(_vo.getIndicationOfInduction());
		_fb.setInductionMethodId(_vo.getInductionMethodId());
		_fb.setInductionMethod(_vo.getInductionMethod());
	}

	public static boolean saveANCNewnatalDetail(ANCNeonatalDetailFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();		
		boolean flag = true;
		
		HttpSession session = null;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			session = _request.getSession();

			// ANC New Natal Detail
			ANCNeonatalDetailVO ancNeoNatalVO = new ANCNeonatalDetailVO();
			// Setting Times  
				//Cry
			if(_fb.getCryDate()!=null && !_fb.getCryDate().equals("") && !_fb.getCryDate().equals("dd-mm-yyyy"))
				_fb.setCryDateTime(_fb.getCryDate() +" "+ _fb.getCryTime());
			if(_fb.getUrineDate()!=null && !_fb.getUrineDate().equals("") && !_fb.getUrineDate().equals("dd-mm-yyyy"))
				_fb.setUrineDateTime(_fb.getUrineDate() +" "+ _fb.getUrineTime());
			if(_fb.getFeedDate()!=null && !_fb.getFeedDate().equals("") && !_fb.getFeedDate().equals("dd-mm-yyyy"))
				_fb.setFeedDateTime(_fb.getFeedDate() +" "+ _fb.getFeedTime());
			if(_fb.getMotherFeedDate()!=null && !_fb.getMotherFeedDate().equals("") && !_fb.getMotherFeedDate().equals("dd-mm-yyyy"))
				_fb.setMotherFeedDateTime(_fb.getMotherFeedDate() +" "+ _fb.getMotherFeedTime());
			
			HelperMethods.populate(ancNeoNatalVO, _fb);
			ancNeoNatalVO.setSlNo(_fb.getSelectedNeoNatSlNo());
			
			// ANC Apgar Detail
			List<ANCNeonatalApgarVO> lstAddedApgarDtl = (List<ANCNeonatalApgarVO>) session.getAttribute(InpatientConfig.ANCDETAIL_SELECTED_NEONAT_ADDED_APGAR_DETAIL_LIST);
			if(_fb.getApgarTime()!=null && !_fb.getApgarTime().trim().equals("-1"))
			{
				ANCNeonatalApgarVO apgarVO = new ANCNeonatalApgarVO();
				HelperMethods.populate(apgarVO, _fb);
				apgarVO.setSlNo(_fb.getSelectedNeoNatSlNo());			
				lstAddedApgarDtl.add(apgarVO);
			}			
			ANCNeonatalDetailDATA.saveANCNewNatalDetail(ancNeoNatalVO, lstAddedApgarDtl, userVO);
			
			objStatus.add(Status.LIST);
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
