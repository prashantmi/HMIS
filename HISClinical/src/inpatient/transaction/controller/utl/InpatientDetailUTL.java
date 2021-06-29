package inpatient.transaction.controller.utl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import registration.controller.fb.CRNoFB;

import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.InpatientDetailDATA;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import vo.registration.PatientVO;


public class InpatientDetailUTL extends ControllerUTIL
{
	/**
	## 		Modification Log		:	request.removeAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);				
	##		Modify Date				: 	24-02-2015
	##		Reason	(CR/PRS)		: 	Bec it was not able to refresh this DynamicDeskConfig key for some other modules like MRD..etc
	##		Modify By				: 	Akash Singh
	*/
	public static void getInpatientDetailByCrNo(CRNoFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);
		PatientVO objPatientVo=new PatientVO();

		try
		{
			String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(ptaientDetailVO == null || !ptaientDetailVO.getPatCrNo().equals(fb.getPatCrNo()))
				ptaientDetailVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			else
			{
				WebUTIL.setAttributeInSession(request, InpatientConfig.INPATIENT_ADMISSION_VO, ptaientDetailVO);
				
				//Added by Singaravelan on Date 24-03-2015 for Appointment Integration
				objPatientVo.setPatCrNo(ptaientDetailVO.getPatCrNo());
				objPatientVo.setPatFirstName(ptaientDetailVO.getPatFirstName());
				objPatientVo.setPatMiddleName(ptaientDetailVO.getPatMiddleName());
				objPatientVo.setPatLastName(ptaientDetailVO.getPatLastName());
				objPatientVo.setPatGuardianName(ptaientDetailVO.getPatFatherName());
				WebUTIL.setAttributeInSession(request, "patientVO", objPatientVo);
				
			}

			if (ptaientDetailVO == null || !ptaientDetailVO.getPatCrNo().equals(fb.getPatCrNo()))
			{		
				//Added by Akash Singh on Date 24-02-2015
				//ssWebUTIL.setAttributeInSession(request, InpatientConfig.INPATIENT_ADMISSION_VO, null);
				request.getSession().removeAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				PatientDetailVO patDtlVO = new PatientDetailVO();
				patDtlVO.setPatCrNo(fb.getPatCrNo());

				patDtlVO = InpatientDetailDATA.getInpatientDetailByCrNoNAdmNo(deskType, patDtlVO, userVO);

				MlcVO mlcVO = new MlcVO();
				try
				{					
					String fname = "(Unknown)" + patDtlVO.getPatFirstName();
					if (patDtlVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) patDtlVO.setPatFirstName(fname);

					if (!(fb.getMlcNo() == null || fb.getMlcNo().equals("") || fb.getMlcNo().equals(" ")))
					{
						patDtlVO.setMlcNo(fb.getMlcNo());
					}
					else
					{
						mlcVO = InpatientDetailDATA.getMlcNo(patDtlVO, userVO);
						if (mlcVO != null) patDtlVO.setMlcNo(mlcVO.getMlcNo());
					}
				}
				catch (HisRecordNotFoundException e)
				{
					patDtlVO.setMlcNo("yet to be alloted");
				}
				WebUTIL.setAttributeInSession(request, InpatientConfig.INPATIENT_ADMISSION_VO, patDtlVO);
				HelperMethods.populate(fb, patDtlVO);

			}
			else
			{
				if (ptaientDetailVO.getMlcNo() == null || ptaientDetailVO.getMlcNo().trim().equals(""))
				{
					MlcVO mlcVO = new MlcVO();
					try
					{
						mlcVO = InpatientDetailDATA.getMlcNo(ptaientDetailVO, userVO);
						if (mlcVO != null) ptaientDetailVO.setMlcNo(mlcVO.getMlcNo());
					}
					catch (HisRecordNotFoundException e)
					{
						ptaientDetailVO.setMlcNo("yet to be alloted");
					}
				}
				HelperMethods.populate(fb, ptaientDetailVO);
			}
			objStatus.add(Status.INPROCESS, "", "");
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}

	
	
	
	public static void getMRDPatientDtlByCrNo(CRNoFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);
		PatientVO objPatientVo=new PatientVO();

		try
		{
			String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			if(ptaientDetailVO == null || !ptaientDetailVO.getPatCrNo().equals(fb.getPatCrNo()))
				ptaientDetailVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);
			else
			{
				WebUTIL.setAttributeInSession(request, InpatientConfig.INPATIENT_ADMISSION_VO, ptaientDetailVO);
				
				//Added by Singaravelan on Date 24-03-2015 for Appointment Integration
				objPatientVo.setPatCrNo(ptaientDetailVO.getPatCrNo());
				objPatientVo.setPatFirstName(ptaientDetailVO.getPatFirstName());
				objPatientVo.setPatMiddleName(ptaientDetailVO.getPatMiddleName());
				objPatientVo.setPatLastName(ptaientDetailVO.getPatLastName());
				objPatientVo.setPatGuardianName(ptaientDetailVO.getPatFatherName());
				WebUTIL.setAttributeInSession(request, "patientVO", objPatientVo);
				
			}

			if (ptaientDetailVO == null || !ptaientDetailVO.getPatCrNo().equals(fb.getPatCrNo()))
			{		
				//Added by Akash Singh on Date 24-02-2015
				//ssWebUTIL.setAttributeInSession(request, InpatientConfig.INPATIENT_ADMISSION_VO, null);
				request.getSession().removeAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				PatientDetailVO patDtlVO = new PatientDetailVO();
				patDtlVO.setPatCrNo(fb.getPatCrNo());

				//patDtlVO = InpatientDetailDATA.getInpatientDetailByCrNoNAdmNo(deskType, patDtlVO, userVO);
				patDtlVO = InpatientDetailDATA.getMRDPatientDtlByCrNo(deskType, patDtlVO, userVO);
				
				MlcVO mlcVO = new MlcVO();
				try
				{					
					String fname = "(Unknown)" + patDtlVO.getPatFirstName();
					if (patDtlVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) patDtlVO.setPatFirstName(fname);

					if (!(fb.getMlcNo() == null || fb.getMlcNo().equals("") || fb.getMlcNo().equals(" ")))
					{
						patDtlVO.setMlcNo(fb.getMlcNo());
					}
					else
					{
						mlcVO = InpatientDetailDATA.getMlcNo(patDtlVO, userVO);
						if (mlcVO != null) patDtlVO.setMlcNo(mlcVO.getMlcNo());
					}
				}
				catch (HisRecordNotFoundException e)
				{
					patDtlVO.setMlcNo("yet to be alloted");
				}
				WebUTIL.setAttributeInSession(request, InpatientConfig.INPATIENT_ADMISSION_VO, patDtlVO);
				HelperMethods.populate(fb, patDtlVO);

			}
			else
			{
				if (ptaientDetailVO.getMlcNo() == null || ptaientDetailVO.getMlcNo().trim().equals(""))
				{
					MlcVO mlcVO = new MlcVO();
					try
					{
						mlcVO = InpatientDetailDATA.getMlcNo(ptaientDetailVO, userVO);
						if (mlcVO != null) ptaientDetailVO.setMlcNo(mlcVO.getMlcNo());
					}
					catch (HisRecordNotFoundException e)
					{
						ptaientDetailVO.setMlcNo("yet to be alloted");
					}
				}
				HelperMethods.populate(fb, ptaientDetailVO);
			}
			objStatus.add(Status.INPROCESS, "", "");
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}

	
	
	public static void getInpatientDetailByCrNo(CRNoFB fb, String tileType, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);

		try
		{
			String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			PatientDetailVO ptaientDetailVO = (PatientDetailVO) session.getAttribute(InpatientConfig.INPATIENT_ADMISSION_VO);

			if (ptaientDetailVO == null || !ptaientDetailVO.getPatCrNo().equals(fb.getPatCrNo()))
			{
				PatientDetailVO patDtlVO = new PatientDetailVO();
				patDtlVO.setPatCrNo(fb.getPatCrNo());
				patDtlVO.setIsIpd(tileType);

				patDtlVO = InpatientDetailDATA.getInpatientDetailByCrNoNAdmNo(deskType, patDtlVO, userVO);

				MlcVO mlcVO = new MlcVO();
				try
				{
					if (!(fb.getMlcNo() == null || fb.getMlcNo().equals("") || fb.getMlcNo().equals(" ")))
					{
						patDtlVO.setMlcNo(fb.getMlcNo());
					}
					else
					{
						mlcVO = InpatientDetailDATA.getMlcNo(patDtlVO, userVO);
						if (mlcVO != null) patDtlVO.setMlcNo(mlcVO.getMlcNo());
					}
				}
				catch (HisRecordNotFoundException e)
				{
					patDtlVO.setMlcNo("yet to be alloted");
				}
				WebUTIL.setAttributeInSession(request, InpatientConfig.INPATIENT_ADMISSION_VO, patDtlVO);
				HelperMethods.populate(fb, patDtlVO);

			}
			else
			{
				if (ptaientDetailVO.getMlcNo() == null || ptaientDetailVO.getMlcNo().equals(""))
				{
					MlcVO mlcVO = new MlcVO();
					try
					{
						if (!(fb.getMlcNo() == null || fb.getMlcNo().equals("") || fb.getMlcNo().equals(" ")))
						{
							ptaientDetailVO.setMlcNo(fb.getMlcNo());
						}
						else
						{
							mlcVO = InpatientDetailDATA.getMlcNo(ptaientDetailVO, userVO);
							if (mlcVO != null) ptaientDetailVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						ptaientDetailVO.setMlcNo("yet to be alloted");
					}
				}
				HelperMethods.populate(fb, ptaientDetailVO);
			}
			objStatus.add(Status.INPROCESS, "", "");
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisException e)
		{
			objStatus.add(Status.ERROR, e.getMessage(), "");
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}

	public static PatientDetailVO getInpatientDetailByPatCrNo(String _patCrNo, String _deskType, UserVO _userVO)
	{
		PatientDetailVO patDtlVO = null;
		try
		{		
			patDtlVO = new PatientDetailVO();
			patDtlVO.setPatCrNo(_patCrNo);

			patDtlVO = InpatientDetailDATA.getInpatientDetailByCrNoNAdmNo(_deskType, patDtlVO, _userVO);
			MlcVO mlcVO = new MlcVO();
			try
			{					
				String fname = "(Unknown)" + patDtlVO.getPatFirstName();
				if (patDtlVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) patDtlVO.setPatFirstName(fname);

				mlcVO = InpatientDetailDATA.getMlcNo(patDtlVO, _userVO);
				if (mlcVO != null) patDtlVO.setMlcNo(mlcVO.getMlcNo());
			}
			catch (HisRecordNotFoundException e)
			{
				patDtlVO.setMlcNo("yet to be alloted");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			patDtlVO = null;
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			patDtlVO = null;
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			patDtlVO = null;
		}
		catch (HisException e)
		{
			e.printStackTrace();
			patDtlVO = null;
		}
		finally
		{
		}
		return patDtlVO;
	}
}
