package ehr.patientTile.dataentry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ehr.EHRConfig;
import ehr.vo.EHRVO;
import ehr.vo.EHRVOUtility;
import registration.RegistrationConfig;
import registration.controller.fb.CRNoFB;
import inpatient.InpatientConfig;
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


public class EHRSection_PatientTileUTL extends ControllerUTIL
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
			if(deskType==null || deskType.equals(""))
			{
				deskType = "1";
				
			}
			
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

				patDtlVO = EHRSection_PatientTileDATA.getInpatientDetailByCrNoNAdmNo(deskType, patDtlVO, userVO);

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
						mlcVO = EHRSection_PatientTileDATA.getMlcNo(patDtlVO, userVO);
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
						mlcVO = EHRSection_PatientTileDATA.getMlcNo(ptaientDetailVO, userVO);
						if (mlcVO != null) ptaientDetailVO.setMlcNo(mlcVO.getMlcNo());
					}
					catch (HisRecordNotFoundException e)
					{
						ptaientDetailVO.setMlcNo("yet to be alloted");
					}
				}
				//Added by Vasu on 8.Jan.2019 to set Patient Address
				StringBuilder sbAdd = new StringBuilder("");
				if (ptaientDetailVO != null)
				{
					if (ptaientDetailVO.getPatAddHNo() != null) sbAdd.append(ptaientDetailVO.getPatAddHNo());
					if (ptaientDetailVO.getPatAddStreet() != null)
					{
						sbAdd.append(", ");
						sbAdd.append(ptaientDetailVO.getPatAddStreet());
					}
					if (ptaientDetailVO.getPatAddCityLoc() != null)
					{
						sbAdd.append(", ");
						sbAdd.append(ptaientDetailVO.getPatAddCityLoc());
					}
					if (ptaientDetailVO.getPatAddCity() != null)
					{
						sbAdd.append(", ");
						sbAdd.append(ptaientDetailVO.getPatAddCity());
					}
					
					if(ptaientDetailVO.getPatAddDistrict()!=null) { sbAdd.append(", "); sbAdd.append(ptaientDetailVO.getPatAddDistrict()); }
					if (ptaientDetailVO.getPatAddState() != null)
					{
						sbAdd.append(", ");
						sbAdd.append(ptaientDetailVO.getPatAddState());
					}
					
					if(ptaientDetailVO.getPatAddCountry()!=null) 
					{
					    sbAdd.append(", "); 
					    sbAdd.append(ptaientDetailVO.getPatAddCountry()); 
					}
					if (ptaientDetailVO.getPatAddPIN() != null)
					{
						sbAdd.append(" - ");
						sbAdd.append(ptaientDetailVO.getPatAddPIN());
					}
					if (sbAdd.length()>0 && sbAdd.charAt(0) == ',') sbAdd.delete(0, 1);
					
					ptaientDetailVO.setPatCompleteAddressForDischargeSummary(sbAdd.toString());
				}
				
				//End Vasu
				
				HelperMethods.populate(fb, ptaientDetailVO);
				WebUTIL.setAttributeInSession(request,EHRConfig.PATIENT_DETAILS, ptaientDetailVO);
				
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

				patDtlVO = EHRSection_PatientTileDATA.getInpatientDetailByCrNoNAdmNo(deskType, patDtlVO, userVO);

				MlcVO mlcVO = new MlcVO();
				try
				{
					if (!(fb.getMlcNo() == null || fb.getMlcNo().equals("") || fb.getMlcNo().equals(" ")))
					{
						patDtlVO.setMlcNo(fb.getMlcNo());
					}
					else
					{
						mlcVO = EHRSection_PatientTileDATA.getMlcNo(patDtlVO, userVO);
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
							mlcVO = EHRSection_PatientTileDATA.getMlcNo(ptaientDetailVO, userVO);
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
		//PatientDetailVO patDtlVO1 = null;
		try
		{		
			patDtlVO = new PatientDetailVO();
			patDtlVO.setPatCrNo(_patCrNo);

			patDtlVO = EHRSection_PatientTileDATA.getInpatientDetailByCrNoNAdmNo(_deskType, patDtlVO, _userVO);
			MlcVO mlcVO = new MlcVO();
			try
			{					
				String fname = "(Unknown)" + patDtlVO.getPatFirstName();
				if (patDtlVO.getIsUnknown().equals(RegistrationConfig.PATIENT_ISUNKNOWN_TRUE)) patDtlVO.setPatFirstName(fname);

				mlcVO = EHRSection_PatientTileDATA.getMlcNo(patDtlVO, _userVO);
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
	
	
	public static void getInpatientDischargeDetailByCrNo(CRNoFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		//HttpSession session = request.getSession();
		UserVO userVO = getUserVO(request);
		PatientVO objPatientVo=new PatientVO();
		HttpSession session = WebUTIL.getSession(request);
		//UserVO userVO = getUserVO(_request);
		setSysdate(request);

		try
		{
			String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
				PatientDetailVO patDtlVO = new PatientDetailVO();
				PatientDetailVO patDtlVO1 = new PatientDetailVO();
				patDtlVO.setPatCrNo(fb.getPatCrNo());

				patDtlVO = EHRSection_PatientTileDATA.getInpatientDiscByCrNoNAdmNo(deskType, patDtlVO, userVO);

				
				WebUTIL.setAttributeInSession(request, InpatientConfig.INPATIENT_DISCHARGE_ADMISSION_VO, patDtlVO);
				HelperMethods.populate(fb, patDtlVO);
				
                
				//patDtlVO1 = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
				patDtlVO1 = (PatientDetailVO) session.getAttribute(EHRConfig.PATIENT_DETAILS);
				HelperMethods.populatetToNullOrEmpty(fb, patDtlVO1);
				
				patDtlVO.setPatCompleteAddressForDischargeSummary(patDtlVO1.getPatCompleteAddressForDischargeSummary());
			    /*EHRVO voEHR = new EHRVO();
		    
			    voEHR.getVoPatientDtl().setConsultantName(patDtlVO.getConsultantName());*/
				
				//Added by Vasu on 8.Jan.2019 to set Patient Address
				/*StringBuilder sbAdd = new StringBuilder("");
				if (patDtlVO != null)
				{
					if (patDtlVO.getPatAddHNo() != null) sbAdd.append(patDtlVO.getPatAddHNo());
					if (patDtlVO.getPatAddStreet() != null)
					{
						sbAdd.append(", ");
						sbAdd.append(patDtlVO.getPatAddStreet());
					}
					if (patDtlVO.getPatAddCityLoc() != null)
					{
						sbAdd.append(", ");
						sbAdd.append(patDtlVO.getPatAddCityLoc());
					}
					if (patDtlVO.getPatAddCity() != null)
					{
						sbAdd.append(", ");
						sbAdd.append(patDtlVO.getPatAddCity());
					}
					
					if(patDtlVO.getPatAddDistrict()!=null) { sbAdd.append(", "); sbAdd.append(patDtlVO.getPatAddDistrict()); }
					if (patDtlVO.getPatAddState() != null)
					{
						sbAdd.append(", ");
						sbAdd.append(patDtlVO.getPatAddState());
					}
					
					 if(patDtlVO.getPatAddCountry()!=null) { sbAdd.append(", "); sbAdd.append(patDtlVO.getPatAddCountry()); }
					if (patDtlVO.getPatAddPIN() != null)
					{
						sbAdd.append(" - ");
						sbAdd.append(patDtlVO.getPatAddPIN());
					}
					if (sbAdd.length()>0 && sbAdd.charAt(0) == ',') sbAdd.delete(0, 1);
					
					patDtlVO.setPatCompleteAddress(sbAdd.toString());
				}*/
				
				//End Vasu
				
				
				EHRVOUtility.populateEmptyPatientDetailVO(request, fb.getPatCrNo(), patDtlVO); //Added by Vasu on 11.Dec.2018
			
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
	
	
	
}
