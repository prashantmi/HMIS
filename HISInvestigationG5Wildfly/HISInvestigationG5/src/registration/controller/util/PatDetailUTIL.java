package registration.controller.util;

/**
 * @author s.singaravelan
 * Creation Date:- 04-Feb-2014
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.vo.MlcVO;
import hisglobal.vo.UserVO;
import registration.RegistrationConfig;
import registration.controller.actionsupport.CRNoSUP;
import registration.controller.actionsupport.PatientSearchSUP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.registration.PatientIdVO;
import vo.registration.PatientVO;

public class PatDetailUTIL extends ControllerUTIL {


	/**
	 * retrieves the patient details retrieves the patientVo from session for cr no if patientvo is not found in session or
	 * if the requested details against cr no. doesn't match with that in session. retrieves MLC no. refer method
	 * getMlcNo()PatientBO for explanation
	 * 
	 * @param _fb CRNoFB form bean
	 * @param _request HttpServletRequest
	 */

	public static void getPatientDtlByCrno(CRNoSUP _CRNoSUP, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			String constraint = _request.getParameter("eligible");
			UserVO userVO = getUserVO(_request);
			HttpSession ses = WebUTIL.getSession(_request);
			System.out.println("---------------"+ses.getAttribute(RegistrationConfig.PATIENT_VO)+"--------------");
			PatientVO patVO = (PatientVO) ses.getAttribute(RegistrationConfig.PATIENT_VO);
			System.out.println("---------------"+patVO+"--------------");

			if (patVO != null)
			{
				if (patVO.getPatPrimaryCatCode() == null && patVO.getPatPrimaryCat() == null)
				{
					throw new HisRecordNotFoundException("Patient Record Not Found");
				}
			}
			if (patVO == null || !patVO.getPatCrNo().equalsIgnoreCase(_CRNoSUP.getPatCrNo()))
			{
				PatientVO patientVO = new PatientVO();
				patientVO.setPatCrNo(_CRNoSUP.getPatCrNo());
				patientVO.setOtherHospitalFlag(_CRNoSUP.getOtherHospitalFlag());

				patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				if (constraint != null && !constraint.equalsIgnoreCase(""))
				{
					//PatientDetailDATA.getPatientDtl(patientVO, userVO, constraint);
				}
				else
				{

					PatientDetailDATA.getPatientDtl(patientVO, userVO);

					//******** check if needed
					//WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_NAME, patientVO.getImageFileName());
					///WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, patientVO.getImageFile());
				
					
					MlcVO mlcVO = new MlcVO();
					patientVO.setPatCrNo(_CRNoSUP.getPatCrNo());
					try
					{
						if (!(_CRNoSUP.getMlcNo() == null || _CRNoSUP.getMlcNo().equals("") || _CRNoSUP.getMlcNo().equals(" ") || _CRNoSUP.getMlcNo().indexOf("(") == -1))
						{
							patientVO.setMlcNo(_CRNoSUP.getMlcNo());
						}
						else if (session.getAttribute(RegistrationConfig.MLC_NO) != null)
						{
							String mlcNo = (String) session.getAttribute(RegistrationConfig.MLC_NO);
							patientVO.setMlcNo(mlcNo);
							session.removeAttribute(RegistrationConfig.MLC_NO);
						}
						else
						{
							//////////////////////////////////////////MLc not yet started//////////////////////////////////
							//mlcVO = PatientDetailDATA.getMlcNo(patientVO, userVO);
							if (mlcVO != null) patientVO.setMlcNo(mlcVO.getMlcNo());
						}
						
						
						// added by manisha gangwar date: 02.04.2018
						WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_NAME, patientVO.getImageFileName());
						WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, patientVO.getImageFile());
						
						WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_NAME, patientVO.getVerificationDocumentId());
						WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT_DOC,patientVO.getVerificationDocumentFile());
					
						 
						
					}
					catch (HisRecordNotFoundException e)
					{
						patientVO.setMlcNo("yet to be alloted");
					}

				}
				//if (_CRNoSUP instanceof PatientDetailFB) 
				HelperMethods.populate(_CRNoSUP, patientVO);
				WebUTIL.setAttributeInSession(_request, RegistrationConfig.PATIENT_VO, patientVO);
			}
			else
			{
				if (patVO.getMlcNo() == null || patVO.getMlcNo().equalsIgnoreCase(""))
				{
					MlcVO mlcVO = new MlcVO();
					try
					{
						if (!(_CRNoSUP.getMlcNo() == null || _CRNoSUP.getMlcNo().equals("") || _CRNoSUP.getMlcNo().equals(" ") || _CRNoSUP.getMlcNo().indexOf("(") == -1))
						{
							patVO.setMlcNo(_CRNoSUP.getMlcNo());
						}
						else if (session.getAttribute(RegistrationConfig.MLC_NO) != null)
						{
							String mlcNo = (String) session.getAttribute(RegistrationConfig.MLC_NO);
							patVO.setMlcNo(mlcNo);
							session.removeAttribute(RegistrationConfig.MLC_NO);
						}
						else
						{
							/////////////////till the time mlc is not encountered///////////////////////////////////////
							//	mlcVO = PatientDetailDATA.getMlcNo(patVO, userVO);
							if (mlcVO != null) patVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						// patVO.setMlcNo("yet to be alloted");
						System.out.println("inside record not found of mlc");
					}
					
				}
				//to decrypt the aadhaar number
				if(patVO.getPatNationalId()!=null && !patVO.getPatNationalId().equals(""))
				{
					if(patVO.getPatNationalId().length()>12)
					{
						String tempAadhaar = NewRegistrationDATA.getAadhaarDecrypted(patVO.getPatNationalId(), patVO.getPatCrNo());
						patVO.setPatNationalId(tempAadhaar.split("#@#")[0]);
					}
				}
				HelperMethods.populate(_CRNoSUP, patVO);
				WebUTIL.setAttributeInSession(_request, RegistrationConfig.PATIENT_VO, patVO);
			//	if (_CRNoSUP instanceof PatientDetailFB) HelperMethods.populate(((PatientDetailFB) _fb), patVO);
				
			}
			objStatus.add(Status.INPROCESS, "", "");
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");

		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}



	/**
	 * retrieves the patient details retrieves the patientVo from session for cr no if patientvo is not found in session or
	 * if the requested details against cr no. doesn't match with that in session. retrieves MLC no. refer method
	 * getMlcNo()PatientBO for explanation
	 * 
	 * @param _fb CRNoFB form bean
	 * @param _request HttpServletRequest
	 */

	public static void getPatientDtlByCrnoDailyPatient(CRNoSUP _CRNoSUP, HttpServletRequest _request, String strVisitType)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			UserVO userVO = getUserVO(_request);
			HttpSession ses = WebUTIL.getSession(_request);
			//PatientVO patVO = (PatientVO) ses.getAttribute(RegistrationConfig.PATIENT_VO);
			PatientVO patVO=null;
			if (patVO != null)
			{
				if (patVO.getPatPrimaryCatCode() == null && patVO.getPatPrimaryCat() == null)
				{
					throw new HisRecordNotFoundException("Patient Record Not Found");
				}
			}
			if (patVO == null || !patVO.getPatCrNo().equalsIgnoreCase(_CRNoSUP.getPatCrNo()))
			{
				PatientVO patientVO = new PatientVO();
				patientVO.setPatCrNo(_CRNoSUP.getPatCrNo());
				patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				{

					patientVO=PatientDetailDATA.getPatientDtlDaily(patientVO, userVO,strVisitType);

					byte[] imgBytes= patientVO.getImageFile();
					//InputStream fis=null;
					//fis = new ByteArrayInputStream(imgBytes);  
										
					WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_NAME, patientVO.getImageFileName());
					WebUTIL.setAttributeInSession(_request,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, imgBytes);
					
					
					
					MlcVO mlcVO = new MlcVO();
					patientVO.setPatCrNo(_CRNoSUP.getPatCrNo());
					try
					{
						if (!(_CRNoSUP.getMlcNo() == null || _CRNoSUP.getMlcNo().equals("") || _CRNoSUP.getMlcNo().equals(" ") || _CRNoSUP.getMlcNo().indexOf("(") == -1))
						{
							patientVO.setMlcNo(_CRNoSUP.getMlcNo());
						}
						else if (session.getAttribute(RegistrationConfig.MLC_NO) != null)
						{
							String mlcNo = (String) session.getAttribute(RegistrationConfig.MLC_NO);
							patientVO.setMlcNo(mlcNo);
							session.removeAttribute(RegistrationConfig.MLC_NO);
						}
						else
						{
							if (mlcVO != null) patientVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						patientVO.setMlcNo("yet to be alloted");
					}

				}
				//if (_CRNoSUP instanceof PatientDetailFB) 
				HelperMethods.populate(_CRNoSUP, patientVO);
				WebUTIL.setAttributeInSession(_request, RegistrationConfig.PATIENT_VO, patientVO);
			}
			else
			{
				if (patVO.getMlcNo() == null || patVO.getMlcNo().equalsIgnoreCase(""))
				{
					MlcVO mlcVO = new MlcVO();
					try
					{
						if (!(_CRNoSUP.getMlcNo() == null || _CRNoSUP.getMlcNo().equals("") || _CRNoSUP.getMlcNo().equals(" ") || _CRNoSUP.getMlcNo().indexOf("(") == -1))
						{
							patVO.setMlcNo(_CRNoSUP.getMlcNo());
						}
						else if (session.getAttribute(RegistrationConfig.MLC_NO) != null)
						{
							String mlcNo = (String) session.getAttribute(RegistrationConfig.MLC_NO);
							patVO.setMlcNo(mlcNo);
							session.removeAttribute(RegistrationConfig.MLC_NO);
						}
						else
						{
							/////////////////till the time mlc is not encountered///////////////////////////////////////
							//	mlcVO = PatientDetailDATA.getMlcNo(patVO, userVO);
							if (mlcVO != null) patVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						// patVO.setMlcNo("yet to be alloted");
						System.out.println("inside record not found of mlc");
					}
				}
				HelperMethods.populate(_CRNoSUP, patVO);
				//	if (_CRNoSUP instanceof PatientDetailFB) HelperMethods.populate(((PatientDetailFB) _fb), patVO);

			}
			objStatus.add(Status.INPROCESS, "", "");
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");

		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
	
	
	/**
	 * retrieves the patient details retrieves the patientVo from session for cr no if patientvo is not found in session or
	 * if the requested details against cr no. doesn't match with that in session. retrieves MLC no. refer method
	 * getMlcNo()PatientBO for explanation
	 * 
	 * @param _fb CRNoFB form bean
	 * @param _request HttpServletRequest
	 */

	public static void searchPatientDetailForSearcTile(PatientSearchSUP _CRNoSUP, HttpServletRequest _request,HttpServletResponse objResponse)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		List<String> lstPatientJsonObjString = new ArrayList<String>();
		StringBuilder strResponse = new StringBuilder();
		try
		{
			
			UserVO userVO = getUserVO(_request);
			PatientVO patientVO = new PatientVO();
			PatientIdVO patientIdVO=new PatientIdVO();
							
			if(_CRNoSUP.getUniqueIdType().equals(RegistrationConfig.SEARCH_BY_CRNO))
			{
	               System.out.println("Inside UTIL"+_CRNoSUP.getUniqueIdType());
				    patientVO.setPatCrNo(_CRNoSUP.getPatIdNo());
				    if(_CRNoSUP.getHospitalCode().equalsIgnoreCase("000"))
				    	patientVO.setIsGlobal("1");
				    else
				    	patientVO.setIsGlobal("0");
				    patientVO.setPatRefGnctdHospitalCode(_CRNoSUP.getHospitalCode());
				    patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				    lstPatientJsonObjString=PatientDetailDATA.searchPatientDetailByCRNo(patientVO, userVO);								
			}
			if(_CRNoSUP.getUniqueIdType().equals(RegistrationConfig.SEARCH_BY_AADHAR))
			{
	               System.out.println("Inside UTIL"+_CRNoSUP.getUniqueIdType());
	               patientIdVO.setPatIdNo(_CRNoSUP.getPatIdNo());
	               if(_CRNoSUP.getHospitalCode().equalsIgnoreCase("000"))
				    	patientIdVO.setIsGlobal("1");
				    else
				    	patientIdVO.setIsGlobal("0");
	               patientIdVO.setHospitalCode(_CRNoSUP.getHospitalCode());
				    patientIdVO.setPatDocType(RegistrationConfig.DOCTYPE_CODE_AADHAR);
				   lstPatientJsonObjString=PatientDetailDATA.searchPatientDetailByUniqueId(patientIdVO, userVO);								
			}
			if(_CRNoSUP.getUniqueIdType().equals(RegistrationConfig.SEARCH_BY_ALTERNATEID))
			{
	               System.out.println("Inside UTIL"+_CRNoSUP.getUniqueIdType());
	               patientIdVO.setPatIdNo(_CRNoSUP.getPatIdNo());
	               patientIdVO.setPatDocType(_CRNoSUP.getDocType());
	               if(_CRNoSUP.getHospitalCode().equalsIgnoreCase("000"))
	            	   patientIdVO.setIsGlobal("1");
				    else
				    	patientIdVO.setIsGlobal("0");
	               patientIdVO.setHospitalCode(_CRNoSUP.getHospitalCode());
				   lstPatientJsonObjString=PatientDetailDATA.searchPatientDetailByUniqueId(patientIdVO, userVO);								
			}
			if(_CRNoSUP.getUniqueIdType().equals(RegistrationConfig.SEARCH_BY_EMPLOYEEID))
			{
	               System.out.println("Inside UTIL"+_CRNoSUP.getUniqueIdType());
	               patientIdVO.setPatIdNo(_CRNoSUP.getPatIdNo());
	               patientIdVO.setPatDocType(RegistrationConfig.DOCTYPE_CODE_EMPLOYEE);
	               patientIdVO.setHospitalCode(_CRNoSUP.getHospitalCode());
	               if(_CRNoSUP.getHospitalCode().equalsIgnoreCase("000"))
	            	   patientIdVO.setIsGlobal("1");
				    else
				    	patientIdVO.setIsGlobal("0");
				   lstPatientJsonObjString=PatientDetailDATA.searchPatientDetailByUniqueId(patientIdVO, userVO);								
			}
			if(_CRNoSUP.getUniqueIdType().equals(RegistrationConfig.SEARCH_BY_MOBILENO))
			{
	               System.out.println("Inside UTIL"+_CRNoSUP.getUniqueIdType());
				    patientVO.setPatAddMobileNo(_CRNoSUP.getPatIdNo());
				    if(_CRNoSUP.getHospitalCode().equalsIgnoreCase("000"))
				    	patientVO.setIsGlobal("1");
				    else
				    	patientVO.setIsGlobal("0");
				    patientVO.setPatRefGnctdHospitalCode(_CRNoSUP.getHospitalCode());
				    patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				    lstPatientJsonObjString=PatientDetailDATA.searchPatientDetailByMobileNo(patientVO, userVO);								
			}
			if(_CRNoSUP.getUniqueIdType().equals(RegistrationConfig.SEARCH_BY_EMAILID))
			{
	               System.out.println("Inside UTIL"+_CRNoSUP.getUniqueIdType());
				    patientVO.setPatAddEmailId(_CRNoSUP.getPatIdNo());
				    if(_CRNoSUP.getHospitalCode().equalsIgnoreCase("000"))
				    	patientVO.setIsGlobal("1");
				    else
				    	patientVO.setIsGlobal("0");
				    patientVO.setPatRefGnctdHospitalCode(_CRNoSUP.getHospitalCode());
				    patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				    lstPatientJsonObjString=PatientDetailDATA.searchPatientDetailByEmail(patientVO, userVO);								
			}
			strResponse.append("[");
			if(lstPatientJsonObjString!=null && lstPatientJsonObjString.size()>0){
				for(int i=0; i<lstPatientJsonObjString.size(); i++){
					if(i==0)
						strResponse.append(lstPatientJsonObjString.get(i));
					else
						strResponse.append(","+lstPatientJsonObjString.get(i));
				}
			}
			strResponse.append("]");
					
		objStatus.add(Status.INPROCESS, "", "");
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");

		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			//String strResponse1="[{\"patCrNo\":\"1011400000137\", \"patdob\":\"28-Jan-2014\", \"patfirstname\":\"Anish\",\"patmiddlename\":\"\",\"patlastname\":\"\",\"isActualDob\":\"1\", \"patAgeWithUnit\":\"5 Wk\",\"patGenderCode\":\"F\", \"patgender\":\"Female\", \"patGuardianName\":\"Mr Father\", \"patMotherName\":\"\",\"pathusbandname\":\"\",\"patAadharNo\":\"\" , \"patAddPhoneNo\":\"\" , \"patAddMobileNo\":\"\",\"patAddHNo\":\"\" , \"patAddStreet\":\"\" , \"patAddLandMarks\":\"\" , \"patadddistrict\":\"Mumbai, \" , \"pataddstate\":\"Maharashtra, \" , \"pataddcountry\":\"India\"}]";
			
			//String strResponse1="[{\"patCrNo\":\"1011400000137\", \"patdob\":\"28-Jan-2014\", \"patfirstname\":\"Anish\",\"patmiddlename\":\"Hello\",\"patlastname\":\"Test\",\"isActualDob\":\"1\",\"patGenderCode\":\"F\", \"patgender\":\"Female\"}]";
			//writeResponse(objResponse, strResponse1);
			writeResponse(objResponse, strResponse.toString());
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
	
	/**
	 * retrieves the patient details retrieves the patientVo from session for cr no if patientvo is not found in session or
	 * if the requested details against cr no. doesn't match with that in session. retrieves MLC no. refer method
	 * getMlcNo()PatientBO for explanation
	 * 
	 * @param _fb CRNoFB form bean
	 * @param _request HttpServletRequest
	 */

	public static void searchPatientDetailByDemographicDetailsForSearcTile(PatientSearchSUP _CRNoSUP, HttpServletRequest _request,HttpServletResponse objResponse)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		List<String> lstPatientJsonObjString = new ArrayList<String>();
		StringBuilder strResponse = new StringBuilder();
		try
		{
			
			UserVO userVO = getUserVO(_request);
			PatientVO patientVO = new PatientVO();	
	        System.out.println("Inside UTIL"+_CRNoSUP.getUniqueIdType());
	    /*  ## 		Modification Log							
			##		Modify Date				:18thDec'14 
			##		Reason	(CR/PRS)		:Add From Date and To Date logic in patient search
			##		Modify By				:Sheeldarshi */
	        if(!_CRNoSUP.getIsUnknown().equals("1"))
	        {
	        patientVO.setPatFirstName(_CRNoSUP.getPatFirstName());
	        patientVO.setPatMiddleName(_CRNoSUP.getPatMiddleName());
	        patientVO.setPatLastName(_CRNoSUP.getPatLastName());
	        patientVO.setPatGenderCode(_CRNoSUP.getPatGender());
	        patientVO.setPatGuardianName(_CRNoSUP.getPatGuardianName());
	        patientVO.setPatHusbandName(_CRNoSUP.getPatHusbandName());
	        patientVO.setPatMotherName(_CRNoSUP.getPatMotherName());

	        patientVO.setPatAddHNo(_CRNoSUP.getPatAddHNo());	        
	        patientVO.setPatAddStreet(_CRNoSUP.getPatAddStreet());
	        patientVO.setPatAddCityLoc(_CRNoSUP.getPatAddCityLoc());
	        patientVO.setPatAddCity(_CRNoSUP.getPatAddCity());
	        patientVO.setPatAddPIN(_CRNoSUP.getPatAddPIN());
	        patientVO.setPatAge(_CRNoSUP.getPatAge());
	        patientVO.setPatAddCountryCode(_CRNoSUP.getPatAddCountryCode());
			patientVO.setPatAddStateCode(_CRNoSUP.getPatAddStateCode());
			patientVO.setPatAddStateName(_CRNoSUP.getPatAddStateName());
			patientVO.setPatAddDistrict(_CRNoSUP.getPatAddDistrict());
			patientVO.setPatAddDistrictCode(_CRNoSUP.getPatAddDistrictCode());
	        }
	        patientVO.setIsUnknown(_CRNoSUP.getIsUnknown());
			patientVO.setFromDate(_CRNoSUP.getFromDate());
			patientVO.setToDate(_CRNoSUP.getToDate());
			//End:Sheeldarshi
			
	        //patientVO.setPatRefGnctdHospitalCode(_CRNoSUP.getHospitalCode());
		    //patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
		    lstPatientJsonObjString=PatientDetailDATA.searchPatientDetailByDemographicDetails(patientVO, userVO);								
			
			
			strResponse.append("[");
			if(lstPatientJsonObjString!=null && lstPatientJsonObjString.size()>0){
				for(int i=0; i<lstPatientJsonObjString.size(); i++){
					if(i==0)
						strResponse.append(lstPatientJsonObjString.get(i));
					else
						strResponse.append(","+lstPatientJsonObjString.get(i));
				}
			}
			strResponse.append("]");
					
		objStatus.add(Status.INPROCESS, "", "");
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");

		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			//String strResponse1="[{\"patCrNo\":\"1011400000137\", \"patdob\":\"28-Jan-2014\", \"patfirstname\":\"Anish\",\"patmiddlename\":\"\",\"patlastname\":\"\",\"isActualDob\":\"1\", \"patAgeWithUnit\":\"5 Wk\",\"patGenderCode\":\"F\", \"patgender\":\"Female\", \"patGuardianName\":\"Mr Father\", \"patMotherName\":\"\",\"pathusbandname\":\"\",\"patAadharNo\":\"\" , \"patAddPhoneNo\":\"\" , \"patAddMobileNo\":\"\",\"patAddHNo\":\"\" , \"patAddStreet\":\"\" , \"patAddLandMarks\":\"\" , \"patadddistrict\":\"Mumbai, \" , \"pataddstate\":\"Maharashtra, \" , \"pataddcountry\":\"India\"}]";
			
			//String strResponse1="[{\"patCrNo\":\"1011400000137\", \"patdob\":\"28-Jan-2014\", \"patfirstname\":\"Anish\",\"patmiddlename\":\"Hello\",\"patlastname\":\"Test\",\"isActualDob\":\"1\",\"patGenderCode\":\"F\", \"patgender\":\"Female\"}]";
			//writeResponse(objResponse, strResponse1);
			writeResponse(objResponse, strResponse.toString());
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
	
	public static void writeResponse(HttpServletResponse resp, String output){
		try{
			resp.reset();
			resp.setContentType("text/xml");
			resp.setHeader("Cache-Control", "no-cache");
			System.out.println(output);
		resp.getWriter().write(output);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void getPatientDtlByCrno(CRNoFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			String constraint = _request.getParameter("eligible");
			UserVO userVO = getUserVO(_request);
			HttpSession ses = WebUTIL.getSession(_request);
			System.out.println("---------------"+ses.getAttribute(RegistrationConfig.PATIENT_VO)+"--------------");
			PatientVO patVO = (PatientVO) ses.getAttribute(RegistrationConfig.PATIENT_VO);
			System.out.println("---------------"+patVO+"--------------");

			if (patVO != null)
			{
				if (patVO.getPatPrimaryCatCode() == null && patVO.getPatPrimaryCat() == null)
				{
					throw new HisRecordNotFoundException("Patient Record Not Found");
				}
			}
			if (patVO == null || !patVO.getPatCrNo().equalsIgnoreCase(_fb.getPatCrNo()))
			{
				PatientVO patientVO = new PatientVO();
				patientVO.setPatCrNo(_fb.getPatCrNo());
				patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				if (constraint != null && !constraint.equalsIgnoreCase(""))
				{
					//PatientDetailDATA.getPatientDtl(patientVO, userVO, constraint);
					PatientDetailDATA.getPatientDtl(patientVO, userVO);
				}
				else
				{

					PatientDetailDATA.getPatientDtl(patientVO, userVO);

					MlcVO mlcVO = new MlcVO();
					patientVO.setPatCrNo(_fb.getPatCrNo());
					try
					{
						if (!(_fb.getMlcNo() == null || _fb.getMlcNo().equals("") || _fb.getMlcNo().equals(" ") || _fb.getMlcNo().indexOf("(") == -1))
						{
							patientVO.setMlcNo(_fb.getMlcNo());
						}
						else if (session.getAttribute(RegistrationConfig.MLC_NO) != null)
						{
							String mlcNo = (String) session.getAttribute(RegistrationConfig.MLC_NO);
							patientVO.setMlcNo(mlcNo);
							session.removeAttribute(RegistrationConfig.MLC_NO);
						}
						else
						{

							//mlcVO = PatientDetailDATA.getMlcNo(patientVO, userVO); MLC No yet started
							if (mlcVO != null) patientVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						patientVO.setMlcNo("yet to be alloted");
					}

				}
				if (_fb instanceof PatientDetailFB) HelperMethods.populate(((PatientDetailFB) _fb), patientVO);
				// System.out.println("patVO.patprimarycat code.......... "+((PatientDetailFB)_fb).getPatPrimaryCatCode());
				// System.out.println("_fb.getPatFirstName(); in util "+((PatientDetailFB)_fb).getPatFirstName());
				/*
				 * if(patientVO.getRegRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE)) throw new
				 * HisRenewalRequiredException();
				 */
				WebUTIL.setAttributeInSession(_request, RegistrationConfig.PATIENT_VO, patientVO);
			}
			else
			{
				if (patVO.getMlcNo() == null || patVO.getMlcNo().equalsIgnoreCase(""))
				{
					MlcVO mlcVO = new MlcVO();
					try
					{
						if (!(_fb.getMlcNo() == null || _fb.getMlcNo().equals("") || _fb.getMlcNo().equals(" ") || _fb.getMlcNo().indexOf("(") == -1))
						{
							patVO.setMlcNo(_fb.getMlcNo());
						}
						else if (session.getAttribute(RegistrationConfig.MLC_NO) != null)
						{
							String mlcNo = (String) session.getAttribute(RegistrationConfig.MLC_NO);
							patVO.setMlcNo(mlcNo);
							session.removeAttribute(RegistrationConfig.MLC_NO);
						}
						else
						{

							//mlcVO = PatientDetailDATA.getMlcNo(patVO, userVO); MLC No yet started
							if (mlcVO != null) patVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						// patVO.setMlcNo("yet to be alloted");
						System.out.println("inside record not found of mlc");
					}
				}
				if (_fb instanceof PatientDetailFB) HelperMethods.populate(((PatientDetailFB) _fb), patVO);
				/*
				 * if(patVO.getRegRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE)) throw new
				 * HisRenewalRequiredException();
				 */

			}

			// System.out.println("patVO.getPatFirstName(); in util "+patVO.getPatFirstName());
			objStatus.add(Status.INPROCESS, "", "");
		}
		/*
		 * catch(HisRenewalRequiredException e){ System.out.println("Inside HisRenewalRequiredException");
		 * objStatus.add(Status.ERROR_AE,"","Renewal of Registration Required"); e.printStackTrace(); }
		 */
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("Inside HisRecordNotFoundException");
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			//e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
			e.printStackTrace();
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");

		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("objStatus in finally" + objStatus);
			System.out.println("objStatus list" + objStatus.getStatusList());
		}
	}
}
