package registration.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.RegistrationConfig;
import registration.controller.data.PatientDetailDATA;
import registration.controller.fb.CRNoFB;
import registration.controller.fb.PatientModificationFB;
import registration.controller.fb.SplPatientModificationFB;

public class PatDetailUTIL extends ControllerUTIL {
	
	
	/**
	 * retrieves the patient details retrieves the patientVo from session for cr no if patientvo is not found in session or
	 * if the requested details against cr no. doesn't match with that in session. retrieves MLC no. refer method
	 * getMlcNo()PatientBO for explanation
	 * 
	 * @param _fb CRNoFB form bean
	 * @param _request HttpServletRequest
	 */

	public static void getPatientDtlByCrno(CRNoFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			String constraint = _request.getParameter("eligible");
			UserVO userVO = getUserVO(_request);
			HttpSession ses = WebUTIL.getSession(_request);
			PatientVO patVO = null;
			System.out.println("---------------"+ses.getAttribute(RegistrationConfig.PATIENT_VO)+"--------------");
			//PatientVO patVO = (PatientVO) ses.getAttribute(RegistrationConfig.PATIENT_VO);
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

							mlcVO = PatientDetailDATA.getMlcNo(patientVO, userVO);
							if (mlcVO != null) patientVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						patientVO.setMlcNo("yet to be alloted");
					}

				}
				//if (_fb instanceof PatientDetailFB) HelperMethods.populate(((PatientDetailFB) _fb), patientVO);
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

							mlcVO = PatientDetailDATA.getMlcNo(patVO, userVO);
							if (mlcVO != null) patVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						// patVO.setMlcNo("yet to be alloted");
						System.out.println("inside record not found of mlc");
					}
				}
			//	if (_fb instanceof PatientDetailFB) HelperMethods.populate(((PatientDetailFB) _fb), patVO);
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
	
	public static void getPatientDtlByCrno(PatientModificationFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			String constraint = _request.getParameter("eligible");
			UserVO userVO = getUserVO(_request);
			PatientVO patVO=new PatientVO();
			HttpSession ses = WebUTIL.getSession(_request);
			System.out.println("---------------"+ses.getAttribute(RegistrationConfig.PATIENT_VO)+"--------------");
			if(ses.getAttribute(RegistrationConfig.PATIENT_VO)==null)
			{
				patVO = (PatientVO) ses.getAttribute(RegistrationConfig.PATIENT_VO);
			}
			else
			{
				System.out.println("---------------"+_fb.getCrNoToModify()+"--------------");
				System.out.println("---------------"+_fb.getPatPrimaryCatCode()+"--------------");
				if(_fb.getCrNoToModify()!=null)
					patVO.setPatCrNo(_fb.getCrNoToModify());
				else
					patVO.setPatCrNo(_fb.getPatCrNo());
				patVO.setPatPrimaryCatCode(_fb.getPatPrimaryCatCode());
				
				patVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				if (constraint != null && !constraint.equalsIgnoreCase(""))
				{
					PatientDetailDATA.getPatientDtl(patVO, userVO, constraint);
				}
				else
				{

					PatientDetailDATA.getPatientDtl(patVO, userVO);

					MlcVO mlcVO = new MlcVO();
					//patientVO.setPatCrNo(_fb.getPatCrNo());
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

							mlcVO = PatientDetailDATA.getMlcNo(patVO, userVO);
							if (mlcVO != null) patVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						patVO.setMlcNo("yet to be alloted");
					}

				}
					
				WebUTIL.setAttributeInSession(_request, RegistrationConfig.PATIENT_VO, patVO);
				
			}
			System.out.println("---------------"+patVO+"--------------");

			if (patVO != null)
			{
				if (patVO.getPatPrimaryCatCode() == null && patVO.getPatPrimaryCat() == null)
				{
					throw new HisRecordNotFoundException("Patient Record Not Found");
				}
			}
			//if (patVO == null || !patVO.getPatCrNo().equalsIgnoreCase(_fb.getPatCrNo()))
			if (patVO == null)
			{
				PatientVO patientVO = new PatientVO();
				patientVO.setPatCrNo(_fb.getPatCrNo());
				patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				if (constraint != null && !constraint.equalsIgnoreCase(""))
				{
					PatientDetailDATA.getPatientDtl(patientVO, userVO, constraint);
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

							mlcVO = PatientDetailDATA.getMlcNo(patientVO, userVO);
							if (mlcVO != null) patientVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						patientVO.setMlcNo("yet to be alloted");
					}

				}
				//if (_fb instanceof PatientDetailFB) HelperMethods.populate(((PatientDetailFB) _fb), patientVO);
				// System.out.println("patVO.patprimarycat code.......... "+((PatientDetailFB)_fb).getPatPrimaryCatCode());
				// System.out.println("_fb.getPatFirstName(); in util "+((PatientDetailFB)_fb).getPatFirstName());
				/*
				 * if(patientVO.getRegRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE)) throw new
				 * HisRenewalRequiredException();
				 */
				WebUTIL.setAttributeInSession(_request, RegistrationConfig.PATIENT_VO, patientVO);
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
	

	public static void getPatientDtlByCrno(SplPatientModificationFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		try
		{
			String constraint = _request.getParameter("eligible");
			UserVO userVO = getUserVO(_request);
			PatientVO patVO=new PatientVO();
			HttpSession ses = WebUTIL.getSession(_request);
			System.out.println("---------------"+ses.getAttribute(RegistrationConfig.PATIENT_VO)+"--------------");
			if(ses.getAttribute(RegistrationConfig.PATIENT_VO)==null)
			{
				patVO = (PatientVO) ses.getAttribute(RegistrationConfig.PATIENT_VO);
			}
			else
			{
				System.out.println("---------------"+_fb.getCrNoToModify()+"--------------");
				System.out.println("---------------"+_fb.getPatPrimaryCatCode()+"--------------");
				if(_fb.getCrNoToModify()!=null)
					patVO.setPatCrNo(_fb.getCrNoToModify());
				else
					patVO.setPatCrNo(_fb.getPatCrNo());
				patVO.setPatPrimaryCatCode(_fb.getPatPrimaryCatCode());
				
				patVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				if (constraint != null && !constraint.equalsIgnoreCase(""))
				{
					PatientDetailDATA.getPatientDtl(patVO, userVO, constraint);
				}
				else
				{

					PatientDetailDATA.getPatientDtl(patVO, userVO);

					MlcVO mlcVO = new MlcVO();
					//patientVO.setPatCrNo(_fb.getPatCrNo());
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

							mlcVO = PatientDetailDATA.getMlcNo(patVO, userVO);
							if (mlcVO != null) patVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						patVO.setMlcNo("yet to be alloted");
					}

				}
					
				WebUTIL.setAttributeInSession(_request, RegistrationConfig.PATIENT_VO, patVO);
				
			}
			System.out.println("---------------"+patVO+"--------------");

			if (patVO != null)
			{
				if (patVO.getPatPrimaryCatCode() == null && patVO.getPatPrimaryCat() == null)
				{
					throw new HisRecordNotFoundException("Patient Record Not Found");
				}
			}
			//if (patVO == null || !patVO.getPatCrNo().equalsIgnoreCase(_fb.getPatCrNo()))
			if (patVO == null)
			{
				PatientVO patientVO = new PatientVO();
				patientVO.setPatCrNo(_fb.getPatCrNo());
				patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
				if (constraint != null && !constraint.equalsIgnoreCase(""))
				{
					PatientDetailDATA.getPatientDtl(patientVO, userVO, constraint);
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

							mlcVO = PatientDetailDATA.getMlcNo(patientVO, userVO);
							if (mlcVO != null) patientVO.setMlcNo(mlcVO.getMlcNo());
						}
					}
					catch (HisRecordNotFoundException e)
					{
						patientVO.setMlcNo("yet to be alloted");
					}

				}
				//if (_fb instanceof PatientDetailFB) HelperMethods.populate(((PatientDetailFB) _fb), patientVO);
				// System.out.println("patVO.patprimarycat code.......... "+((PatientDetailFB)_fb).getPatPrimaryCatCode());
				// System.out.println("_fb.getPatFirstName(); in util "+((PatientDetailFB)_fb).getPatFirstName());
				/*
				 * if(patientVO.getRegRenewalRequired().equals(RegistrationConfig.RENEWAL_REQUIRED_TRUE)) throw new
				 * HisRenewalRequiredException();
				 */
				WebUTIL.setAttributeInSession(_request, RegistrationConfig.PATIENT_VO, patientVO);
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
	
	
	/**
	 * public static UserVO getUserVO(HttpServletRequest request){ //UserVO userVO = (UserVO)
	 * request.getSession().getAttribute(RegistrationConfig.USERVO); //<<hardCoded>>>>>> UserVO userVO = new UserVO();
	 * userVO.setSeatId("10001"); return userVO; }
	 */
	/**
	 * This method is simillar to the method getPatientDtlByCrno except the fact that
	 * it sets Status as record found rather than inprocesss. 
	 * @param _fb
	 * @param _request
	 */
	/*public static void getPatientDtl(CRNoFB _fb,HttpServletRequest _request){
		Status  objStatus=new Status();
		
		try{			
			  String constraint=_request.getParameter("eligible");
			  
			  System.out.println("inside getpatient detail by crno  util");
			  System.out.println("_fb.getPatCrNo"+_fb.getPatCrNo());			  
			  UserVO userVO =getUserVO(_request);
			  HttpSession ses=WebUTIL.getSession(_request);
			  PatientVO patVO=(PatientVO)ses.getAttribute(RegistrationConfig.PATIENT_VO);
			  patVO=null;
			  //System.out.println("patient vo cr no in session,,,,,,,,,"+patVO.getPatCrNo());
			 // System.out.println("patient vo name in session,,,,,,,,,"+patVO.getPatFirstName());
			  System.out.println("cr no to ret........"+_fb.getCrNoToRetrieve());
			  //added corres to get patient details by name 
			  if(_fb.getCrNoToRetrieve()!=null ){
				  System.out.println(" retreive crno has values................."+_fb.getCrNoToRetrieve());
				  _fb.setPatCrNo(_fb.getCrNoToRetrieve());
				  //patVO.setPatCrNo(_fb.getCrNoToRetrieve());
			  }
			  
			  
			  System.out.println("form bean pat cr no..cr no to retrieve......."+_fb.getPatCrNo());
			  ////////////////////////////////////
			  if(constraint!=null && !constraint.equalsIgnoreCase("")){
				  PatientDetailDATA.getPatientDtl(patVO,userVO,constraint);
			  }
			  if(patVO==null || !patVO.getPatCrNo().equalsIgnoreCase(_fb.getPatCrNo() ))
			    {
					  PatientVO patientVO=new PatientVO();
					  System.out.println("fb.getPatCrNo():::............"+_fb.getPatCrNo());
					  patientVO.setPatCrNo(_fb.getPatCrNo());
					  patientVO.setPatAddTypeCode(RegistrationConfig.PATIENT_ADD_TYPE_CURRENT);
					  if(constraint!=null && !constraint.equalsIgnoreCase("")){
						  PatientDetailDATA.getPatientDtl(patientVO,userVO,constraint);
					  }
					  else{
						  PatientDetailDATA.getPatientDtl(patientVO,userVO);
						  MlcVO mlcVO=new MlcVO();
						  mlcVO.setPatCrNo(patientVO.getPatCrNo());
						  try{
						  mlcVO=PatientDetailDATA.getMlcNo(mlcVO,userVO);
						  System.out.println("mlcVO.getMlcNo()::::::"+mlcVO.getMlcNo());
						  patientVO.setMlcNo(mlcVO.getMlcNo());						  						  
						  }
						  catch(HisRecordNotFoundException e){							  
						  }	  
						  
					  }				  			
					  HelperMethods.populate(_fb,patientVO);
					  WebUTIL.setAttributeInSession(_request,RegistrationConfig.PATIENT_VO,patientVO);					  
			   }
			  else{
				  if(patVO.getMlcNo()==null||patVO.getMlcNo().equalsIgnoreCase("")){
				  MlcVO mlcVO=new MlcVO();
				  try{
					   mlcVO=PatientDetailDATA.getMlcNo(mlcVO,userVO);				  
					   System.out.println("mlcVO.getMlcNo()::::::"+mlcVO.getMlcNo());
					   patVO.setMlcNo(mlcVO.getMlcNo());
					   
				  }
				  catch(HisRecordNotFoundException e){
					  
				  }
				  }
				  HelperMethods.populate(_fb,patVO);	
				  
			  }

			  objStatus.add(Status.RECORDFOUND,"","");

			  objStatus.add(Status.INPROCESS,"","");
			  

			}		
		catch(HisRecordNotFoundException e){
			System.out.println("Inside HisRecordNotFoundException");			
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found");
			e.printStackTrace();
		}		
		catch(HisDataAccessException e){
			System.out.println("Inside HisDataAccessException");
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
			e.printStackTrace();
		}
		catch(HisApplicationExecutionException e){		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
			
		}
		catch(HisException e){
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}		
		finally{
			
			WebUTIL.setStatus(_request,objStatus);
		    System.out.println("objStatus in finally"+objStatus);		 
		    System.out.println("objStatus list"+objStatus.getStatusList());
		}	
	}//end of method
*/	
}
