package inpatient.transaction.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.SMSSender.SMSHttpPostClient;
import hisglobal.utility.SMSSender.SMSHttpsNICPostClient;
import hisglobal.utility.SMSSender.config.SMSConfig;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.data.DoctorCallBookDATA;
import inpatient.transaction.controller.fb.DoctorCallBookFB;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class DoctorCallBookUTL extends ControllerUTIL

{
	/**
	## 		Modification Log		: DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO						
	##		Modify Date				: 10-02-2015	
	##		Reason	(CR/PRS)		: To get data from DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO
	##		Modify By				: Akash Singh
	*/
	public static void getCallBookDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		DoctorCallBookVO[] doctorCallBookDtlVOArray = null;
		DoctorCallBookVO doctorCallBookVO = new DoctorCallBookVO();
		DoctorCallBookVO[] newDoctorCallBookVOArray =null ;
		PatientDetailVO[] dailyPatientVOs = null;
		try
		{
			setSysdate(_request);
			UserVO userVO = getUserVO(_request);
			/*PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO=null;
			for(int i=0;i<arrayDailyPatVO.length;i++)
			{
				if(_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO=arrayDailyPatVO[i];
				}
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
			doctorCallBookVO.setPatCrNo(voDP.getPatCrNo());
			doctorCallBookVO.setPatAdmnNo(voDP.getPatAdmNo());
			
			String status="";
			doctorCallBookDtlVOArray = DoctorCallBookDATA.getCallBookDetails(doctorCallBookVO, userVO);
			for(int i=0;i<doctorCallBookDtlVOArray.length;i++)
			{
				if(doctorCallBookDtlVOArray[i].getStatus()==null && doctorCallBookDtlVOArray[i].getCallType().equals(InpatientConfig.CALL_MANUAL))
				{
					//doctorCallBookDtlVOArray[i].setStatus(InpatientConfig.CALL_PENDING);
					status=InpatientConfig.CALL_PENDING;
				}
				else
				{
					//doctorCallBookDtlVOArray[i].setStatus(InpatientConfig.CALL_ACKNOLWEDGED);
					status=InpatientConfig.CALL_ACKNOLWEDGED;
				}
				if(doctorCallBookDtlVOArray[i].getStatus()==null && doctorCallBookDtlVOArray[i].getCallType().equals(InpatientConfig.CALL_AUTOMATIC))
				{
					doctorCallBookDtlVOArray[i].setCallPriority(InpatientConfig.CALL_PRIORITY_NAME_LOW);
					status=InpatientConfig.CALL_AUTOMATIC;
				}
				doctorCallBookDtlVOArray[i].setStatus(status);
				
			}
			int len=doctorCallBookDtlVOArray.length;
			newDoctorCallBookVOArray=new DoctorCallBookVO[doctorCallBookDtlVOArray.length];
			for(int i=0,j=len-1,k=0;i<doctorCallBookDtlVOArray.length;i++)
			{
				if(doctorCallBookDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_PENDING))
				{
					 
					newDoctorCallBookVOArray[k]=doctorCallBookDtlVOArray[i];
					k=k+1;
					
				}
				
				if(doctorCallBookDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_AUTOMATIC) || doctorCallBookDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_ACKNOLWEDGED))
				{
					newDoctorCallBookVOArray[j]=doctorCallBookDtlVOArray[i];
					j=j-1;
					
				}
			}
			
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CALL_BOOK_VO_ARRAY, newDoctorCallBookVOArray);
			objStatus.add(Status.LIST);
			objStatus.add(Status.NEW);
		
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.LIST);
			objStatus.add(Status.NEW);
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	public static void getAllCalls(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session = _request.getSession();
		DoctorCallBookVO[] doctorCallBookDtlVOArray = null;
		DoctorCallBookVO doctorCallBookVO = new DoctorCallBookVO();
		//DoctorCallBookVO[] newDoctorCallBookVOArray =null ;
		try
		{
			setSysdate(_request);
			UserVO userVO = getUserVO(_request);
			PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			PatientDetailVO selectedPatientVO=null;
			for(int i=0;i<arrayDailyPatVO.length;i++)
			{
				if(_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
				{
					selectedPatientVO=arrayDailyPatVO[i];
				}
			}
			
			doctorCallBookVO.setPatCrNo(selectedPatientVO.getPatCrNo());
			doctorCallBookVO.setPatAdmnNo(selectedPatientVO.getPatAdmNo());
			
			String status="";
			doctorCallBookDtlVOArray = DoctorCallBookDATA.getAllCalls(doctorCallBookVO, userVO);
			for(int i=0;i<doctorCallBookDtlVOArray.length;i++)
			{
				if(doctorCallBookDtlVOArray[i].getStatus()==null && doctorCallBookDtlVOArray[i].getCallType().equals(InpatientConfig.CALL_MANUAL))
				{
					//doctorCallBookDtlVOArray[i].setStatus(InpatientConfig.CALL_PENDING);
					status=InpatientConfig.CALL_PENDING;
				}
				else
				{
					//doctorCallBookDtlVOArray[i].setStatus(InpatientConfig.CALL_ACKNOLWEDGED);
					status=InpatientConfig.CALL_ACKNOLWEDGED;
				}
				if(doctorCallBookDtlVOArray[i].getStatus()==null && doctorCallBookDtlVOArray[i].getCallType().equals(InpatientConfig.CALL_AUTOMATIC))
				{
					doctorCallBookDtlVOArray[i].setCallPriority((InpatientConfig.CALL_PRIORITY_NAME_LOW));
					status=InpatientConfig.CALL_AUTOMATIC;
				}
				doctorCallBookDtlVOArray[i].setStatus(status);
				
			}
			//int len=doctorCallBookDtlVOArray.length;
			/*newDoctorCallBookVOArray=new DoctorCallBookVO[doctorCallBookDtlVOArray.length];
			for(int i=0,j=len-1,k=0;i<doctorCallBookDtlVOArray.length;i++)
			{
				if(doctorCallBookDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_PENDING))
				{
					 
					newDoctorCallBookVOArray[k]=doctorCallBookDtlVOArray[i];
					k=k+1;
					
				}
				
				if(doctorCallBookDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_AUTOMATIC) || doctorCallBookDtlVOArray[i].getStatus().equals(InpatientConfig.CALL_ACKNOLWEDGED))
				{
					newDoctorCallBookVOArray[j]=doctorCallBookDtlVOArray[i];
					j=j-1;
					
				}
			}
				*/
			
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CALL_BOOK_VO_ARRAY, doctorCallBookDtlVOArray);
			objStatus.add(Status.LIST);
			objStatus.add(Status.NEW);
		
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.LIST);
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
	
	public static boolean getDeptUnitList(DoctorCallBookFB fb,HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		HttpSession session=request.getSession();
		
		try
		{
			//String unitCode=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE);
			//fb.setUnitCode(unitCode);
			
			// Unit
		//	PatientDetailVO[] al = (PatientDetailVO[]) session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
		//	PatientDetailVO voDP = null;
		//	for (PatientDetailVO vo : al)
			//	if (vo.getPatCrNo().equals(fb.getPatCrNo()))
			//	{
			//		voDP = vo;
			//		break;					
			//	}
			//fb.setUnitCode(voDP.getDepartmentUnitCode());
			
			List lstUnit=DoctorCallBookDATA.getDeptUnitList(getUserVO(request));
			WebUTIL.setAttributeInSession(request,InpatientConfig.DEPT_UNIT_LIST ,lstUnit);
			
			objStatus.add(Status.INPROCESS);
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
		
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
			
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}
	
	public static void getConsultantDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//HttpSession session=_request.getSession();
		List consultantDetails = new ArrayList();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			String parts[]=_fb.getUnitCode().split("#");
			
			if(_fb.getUnitCode().equals(InpatientConfig.ALL_DEPARTMENTS))
				consultantDetails=DoctorCallBookDATA.getAllConsultantDetails(parts[0], userVO);
			else
				consultantDetails=DoctorCallBookDATA.getConsultantDetails(parts[0], userVO);
			_fb.setEmpNo("-1");

			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_LIST,consultantDetails);
		   			
			//objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			_fb.setEmpNo(null);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_LIST,consultantDetails);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_PHONE_LIST,new ArrayList());
			objStatus.add(Status.UNSUCESSFULL, "", "No Consultant Found");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	
	
		}
	
	public static void getPhoneDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//HttpSession session=_request.getSession();
		
		List phoneLst=null;
		//List consList = (ArrayList) session.getAttribute(InpatientConfig.CONSULTANT_LIST);
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			String parts[]=_fb.getUnitCode().split("#");
			if(((_fb.getEmpNo().equals("0")||(_fb.getEmpNo()==null)))||(_fb.getEmpNo().equals("")))
			{	
				phoneLst=DoctorCallBookDATA.getUnitPhone(parts[0], userVO);
	
			}
			else
			{
				String part[]=_fb.getEmpNo().split("#");
			phoneLst=DoctorCallBookDATA.getConsultantPhone(part[0], userVO);
			}
			
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_PHONE_LIST,phoneLst);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_PHONE_LIST,phoneLst);
			objStatus.add(Status.UNSUCESSFULL, "", "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	
	
		}
	public static void getMODPhoneDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//HttpSession session=_request.getSession();
		
		List phoneLst=null;
		//List consList = (ArrayList) session.getAttribute(InpatientConfig.CONSULTANT_LIST);
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			int i=_fb.getEmpNo().length();
			if(i==5)
			{
				phoneLst=DoctorCallBookDATA.getUnitPhone(_fb.getEmpNo(), userVO);

			}
			else
			{
			phoneLst=DoctorCallBookDATA.getConsultantPhone(_fb.getEmpNo(), userVO);
			}
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_PHONE_LIST,phoneLst);
			
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_PHONE_LIST,phoneLst);
			objStatus.add(Status.UNSUCESSFULL, "", "");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	
	
		}
	
	
	
	
	public static void getPeonDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		//HttpSession session=_request.getSession();
		List peonDetails = new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			peonDetails=DoctorCallBookDATA.getPeonDetails(_fb.getUnitCode(), userVO);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.PEON_LIST,peonDetails);
		   			
			//objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(_request, InpatientConfig.PEON_LIST,peonDetails);
			objStatus.add(Status.UNSUCESSFULL, "", "No Peon Found");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	
	
		}
	
	
	public static void getCallDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		DoctorCallBookVO doctorCallBookVO=new DoctorCallBookVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			int index=Integer.parseInt(_fb.getSelectedPatCrNo());
			
			setSysdate(_request);
			DoctorCallBookVO[] doctorCallBookVOArray = (DoctorCallBookVO[]) session.getAttribute(InpatientConfig.CALL_BOOK_VO_ARRAY);
			if(doctorCallBookVOArray!=null)
			{
				
				doctorCallBookVO.setCallNo(doctorCallBookVOArray[index].getCallNo());
				doctorCallBookVO.setCallDate(doctorCallBookVOArray[index].getCallDate());
				doctorCallBookVO.setEmpNo(doctorCallBookVOArray[index].getEmpNo());
				doctorCallBookVO.setCallDateFormat(doctorCallBookVOArray[index].getCallDateFormat());
				doctorCallBookVO.setStatus(doctorCallBookVOArray[index].getStatus());
				if(doctorCallBookVO.getStatus()!=null) _fb.setStatus(doctorCallBookVO.getStatus());
				
			}
			
			doctorCallBookVO=DoctorCallBookDATA.getCallDetails(doctorCallBookVO, userVO);
			_fb.setConsultantName(doctorCallBookVO.getConsultantName());
			_fb.setEmpNo(doctorCallBookVO.getEmpNo());
			_fb.setCallRemarks(doctorCallBookVO.getCallRemarks());
			_fb.setCallPriority(doctorCallBookVO.getCallPriority());
			_fb.setDocCallByPeonStatus(doctorCallBookVO.getDocCallByPeonStatus());
			_fb.setDocCallByPeonRemarks(doctorCallBookVO.getDocCallByPeonRemarks());
			_fb.setDocCallByPhoneStatus(doctorCallBookVO.getDocCallByPhoneStatus());
			_fb.setDocCallByPhoneRemarks(doctorCallBookVO.getDocCallByPhoneRemarks());
			
			_fb.setCallNo(doctorCallBookVO.getCallNo());
			
			if(doctorCallBookVO.getDocCallByPeonStatus()==null)
				_fb.setDocCallByPeonStatus("0");
			if(doctorCallBookVO.getDocCallByPhoneStatus()==null)
				_fb.setDocCallByPhoneStatus("0");
			
			if(doctorCallBookVO.getIsDocCallByPeon().equals(InpatientConfig.DOC_CALL_BY_PEON_YES))
			{
				_fb.setIsDocCallByPeon("on");
				_fb.setPeonEmpNo(doctorCallBookVO.getPeonEmpNo());
			}
				
			else
			{
				_fb.setIsDocCallByPeon("off");
				_fb.setPeonEmpNo("-1");
			}
			if(doctorCallBookVO.getIsDocCallbyPhone().equals(InpatientConfig.DOC_CALL_BY_PHONE_YES))
				_fb.setIsDocCallbyPhone("on");
			else
				_fb.setIsDocCallbyPhone("off");
			if(doctorCallBookVO.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_LOW))
				_fb.setCallPriorityName(InpatientConfig.CALL_PRIORITY_NAME_LOW);
			if(doctorCallBookVO.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_MEDIUM))
				_fb.setCallPriorityName(InpatientConfig.CALL_PRIORITY_NAME_MEDIUM);
			if(doctorCallBookVO.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_HIGH))
				_fb.setCallPriorityName(InpatientConfig.CALL_PRIORITY_NAME_HIGH);
			//objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CALL_BOOK_VO, doctorCallBookVO);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	
	
		}
	
	//modifiedby:NehaRajgariya Date:5Sept2016
	public static boolean saveDoctorCallDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean hasFlag = true;
		String smsFlag = "";
		
		try
		{
			UserVO userVO = getUserVO(_request);
			String wardCode=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE);
			List docPhoneList=(List)session.getAttribute(InpatientConfig.CONSULTANT_PHONE_LIST);
			PatientDetailVO selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			
			String docPhoneNo=((Entry)docPhoneList.get(0)).getValue();
		
			DoctorCallBookVO _callBookVO = new DoctorCallBookVO();
			
			//PatientDetailVO[] arrayDailyPatVO=(PatientDetailVO[])session.getAttribute(DynamicDeskConfig.DESK_PATIENT_LIST);
			//PatientDetailVO selectedPatientVO=null;
			//for(int i=0;i<arrayDailyPatVO.length;i++)
			//{
			//	if(_fb.getPatCrNo().equals(arrayDailyPatVO[i].getPatCrNo()))
		//		{
			//		selectedPatientVO=arrayDailyPatVO[i];
			//	}
			//}
			if(_fb.getIsDocCallByPeon().equals("on"))
				_fb.setIsDocCallByPeon(InpatientConfig.DOC_CALL_BY_PEON_YES);
			if(_fb.getIsDocCallByPeon().equals("off"))
			{
				_fb.setIsDocCallByPeon(InpatientConfig.DOC_CALL_BY_PEON_NO);
				_fb.setDocCallByPeonRemarks(null);
				_fb.setDocCallByPeonStatus(null);
			}
			if(_fb.getIsDocCallbyPhone().equals("on"))
				_fb.setIsDocCallbyPhone(InpatientConfig.DOC_CALL_BY_PHONE_YES);
			if(_fb.getIsDocCallbyPhone().equals("off"))
			{
				_fb.setIsDocCallbyPhone(InpatientConfig.DOC_CALL_BY_PHONE_NO);
				_fb.setDocCallByPhoneRemarks(null);
				_fb.setDocCallByPhoneStatus(null);
			}
			_callBookVO.setPeonEmpNo(_fb.getPeonEmpNo());
			_callBookVO.setCallPriority(_fb.getCallPriority());
			_callBookVO.setIsDocCallByPeon(_fb.getIsDocCallByPeon());
			_callBookVO.setDocCallByPeonStatus(_fb.getDocCallByPeonStatus());
			_callBookVO.setDocCallByPhoneStatus(_fb.getDocCallByPhoneStatus());
			_callBookVO.setIsDocCallbyPhone(_fb.getIsDocCallbyPhone());
			_callBookVO.setPatCrNo(selectedPatientVO.getPatCrNo());
			_callBookVO.setPatAdmnNo(selectedPatientVO.getPatAdmNo());
			if((_fb.getEmpNo()!=null)&&(!(_fb.getEmpNo().equals("0"))))
			{	
				String parts[]=_fb.getEmpNo().split("#");
				_callBookVO.setEmpNo(parts[0]);
				_callBookVO.setToConsultantName(parts[1]);
				_callBookVO.setUserType("1");
			}
			else
			{
				String part[]=_fb.getUnitCode().split("#");
				_callBookVO.setEmpNo(part[0]);
				_callBookVO.setUserType("2");
				_callBookVO.setToConsultantName(part[1]);
				
			}
			//_callBookVO.setEmpNo(_fb.getEmpNo());
			_callBookVO.setCallRemarks(_fb.getCallRemarks());
			_callBookVO.setWardCode(wardCode);
			_callBookVO.setDocCallByPeonRemarks(_fb.getDocCallByPeonRemarks());
			_callBookVO.setDocCallByPhoneRemarks(_fb.getDocCallByPhoneRemarks());
			if(_fb.getPeonEmpNo().equals("-1"))
				_callBookVO.setPeonEmpNo(null);
			else 
				_callBookVO.setPeonEmpNo(_fb.getPeonEmpNo());
		
			
			hasFlag=DoctorCallBookDATA.saveDoctorCallDetails(_callBookVO, userVO);			
		
			//addedBy:NehaRajgariya Date:1sept2016
			smsFlag = DoctorCallBookDATA.getSmsFlagDetails(_callBookVO, userVO);
			
			if(hasFlag == true && smsFlag.equals(InpatientConfig.YES))
			{
				SMSConfig objSMSConfig=new SMSConfig();
				String   message  = "CALL FOR DOCTOR \n" ;
				
				if(_callBookVO.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_LOW))
				{
					message += "Priority: " + "Low"+"\n";
				}
				if(_callBookVO.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_MEDIUM))
				{
					message += "Priority: " + "Medium"+"\n";
				}
				if(_callBookVO.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_HIGH))
				{
					message += "Priority: " + "High"+"\n";
				}
				
				message += "CR No: " + _callBookVO.getPatCrNo()+"\n";
				message += "Patient:  " + selectedPatientVO.getPatName()+"\n";
				message += "Reason: " + _callBookVO.getCallRemarks()+"\n";
				message += "DUWRB: " + selectedPatientVO.getDepartmentUnitName()+" "+selectedPatientVO.getIpdRoomName()+" "+selectedPatientVO.getBedName()+"\n";
				//+voDP.getWardName()+"/"
				message += "Addmission No: " + _callBookVO.getPatAdmnNo()+"\n";
				message += "Admission Date Time: "+selectedPatientVO.getAdmDateTime();
				
				//code from sending message through CDAC MUmbai SMS Gateway
				//SMSHttpPostClient.sendSingleSMSThroughSMSGateway(objSMSConfig.sms_username, objSMSConfig.sms_password,objSMSConfig.sms_senderId,objSMSConfig.sms_url,docPhoneNo,message);//docPhoneNo
				
				//code from sending message through NIC SMS Gateway
				SMSHttpsNICPostClient.sendTextSMSThroughNICSMSGateway(objSMSConfig.nic_sms_username, objSMSConfig.nic_sms_password,objSMSConfig.nic_sms_senderId,objSMSConfig.nic_sms_url, docPhoneNo,message);
			}
			//end:NehaRajgariya
			
			// Nursing Desk Data Set
			PatNursingDeskDataFlagsMenuWiseUTL.setMenuDetailFlag(_request, _fb.getDeskMenuId());
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_PHONE_LIST,new ArrayList());
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
			 }else
			 {		System.out.println("in false");
				 objStatus.add(Status.NEW, "", "");
			 }

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			hasFlag = false;

		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static boolean ModifyDoctorCallDetails(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		boolean hasFlag = true;
		String smsFlag = null;
		
		try
		{
			UserVO userVO = getUserVO(_request);
			List docPhoneList=(List)session.getAttribute(InpatientConfig.CONSULTANT_PHONE_LIST);
			List peonDetialsList=(List)session.getAttribute(InpatientConfig.PEON_LIST);
			PatientDetailVO voDP = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			String docPhoneNo=((Entry)docPhoneList.get(0)).getValue();
			//String wardCode=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_WARD_CODE);
			DoctorCallBookVO _callBookVO = new DoctorCallBookVO();
			
		
			DoctorCallBookVO callVO=(DoctorCallBookVO)session.getAttribute(InpatientConfig.CALL_BOOK_VO);

			_callBookVO.setPatCrNo(callVO.getPatCrNo());
			_callBookVO.setPatAdmnNo(callVO.getPatAdmnNo());
			if(callVO.getIsDocCallByPeon().equals(InpatientConfig.DOC_CALL_BY_PEON_YES))
			{
				_callBookVO.setIsDocCallByPeon(callVO.getIsDocCallByPeon());
				_callBookVO.setPeonEmpNo(callVO.getPeonEmpNo());
			}
			else
			{
				_callBookVO.setIsDocCallByPeon(_fb.getIsDocCallByPeon());
				_callBookVO.setPeonEmpNo(_fb.getPeonEmpNo());
			}
			if(callVO.getDocCallByPeonRemarks()!=null)
				_callBookVO.setDocCallByPeonRemarks(callVO.getDocCallByPeonRemarks());
			else 
				_callBookVO.setDocCallByPeonRemarks(_fb.getDocCallByPeonRemarks());
			if(callVO.getDocCallByPeonStatus()!=null)
				_callBookVO.setDocCallByPeonStatus(callVO.getDocCallByPeonStatus());
			else 
				_callBookVO.setDocCallByPeonStatus(_fb.getDocCallByPeonStatus());
			if(callVO.getDocCallByPhoneStatus()!=null)
				_callBookVO.setDocCallByPhoneStatus(callVO.getDocCallByPhoneStatus());
			else 
				_callBookVO.setDocCallByPhoneStatus(_fb.getDocCallByPhoneStatus());
			if(callVO.getIsDocCallbyPhone().equals(InpatientConfig.DOC_CALL_BY_PHONE_YES))
				_callBookVO.setIsDocCallbyPhone(callVO.getIsDocCallbyPhone());
			else 
				_callBookVO.setIsDocCallbyPhone(_fb.getIsDocCallbyPhone());
			if(callVO.getDocCallByPhoneRemarks()!=null)
				_callBookVO.setDocCallByPhoneRemarks(callVO.getDocCallByPhoneRemarks());
			else 
				_callBookVO.setDocCallByPhoneRemarks(_fb.getDocCallByPhoneRemarks());
			
			if(_fb.getIsDocCallByPeon().equals("on"))
				_callBookVO.setIsDocCallByPeon(InpatientConfig.DOC_CALL_BY_PEON_YES);
			if(_fb.getIsDocCallByPeon().equals("off"))
			{
				_callBookVO.setIsDocCallByPeon(InpatientConfig.DOC_CALL_BY_PEON_NO);
				_callBookVO.setDocCallByPeonRemarks(null);
				_callBookVO.setDocCallByPeonStatus(null);
			}
			if(_fb.getIsDocCallbyPhone().equals("on"))
				_callBookVO.setIsDocCallbyPhone(InpatientConfig.DOC_CALL_BY_PHONE_YES);
			if(_fb.getIsDocCallbyPhone().equals("off"))
			{
				_callBookVO.setIsDocCallbyPhone(InpatientConfig.DOC_CALL_BY_PHONE_NO);
				_callBookVO.setDocCallByPhoneRemarks(null);
				_callBookVO.setDocCallByPhoneStatus(null);
			}
			_callBookVO.setCallNo(_fb.getCallNo());
			_callBookVO.setEmpNo(_fb.getEmpNo());
			_callBookVO.setCallRemarks(_fb.getCallRemarks());
				
			hasFlag=DoctorCallBookDATA.ModifyDoctorCallDetails(_callBookVO, userVO);
			
			//addedBy:NehaRajgariya Date:1sept2016
			smsFlag = DoctorCallBookDATA.getSmsFlagDetails(_callBookVO, userVO);
			
			if(hasFlag == true && smsFlag.equals("1"))
			{
				SMSConfig objSMSConfig=new SMSConfig();
				String   message  = "CALL FOR DOCTOR [M] \n" ;
				
				if(callVO.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_LOW))
				{
					message += "Priority: " + "Low"+"\n";
				}
				if(callVO.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_MEDIUM))
				{
					message += "Priority: " + "Medium"+"\n";
				}
				if(callVO.getCallPriority().equals(InpatientConfig.CALL_PRIORITY_HIGH))
				{
					message += "Priority: " + "High"+"\n";
				}
				
				message += "CR No: " + _callBookVO.getPatCrNo()+"\n";
				message += "Patient:  " + voDP.getPatName()+"\n";
				message += "Reason[M]: " + _callBookVO.getCallRemarks()+"\n";
				message += "DUWRB: " + voDP.getDepartmentUnitName()+" "+voDP.getIpdRoomName()+" "+voDP.getBedName()+"\n";
				//+voDP.getWardName()+"/"
				message += "Addmission No: " + _callBookVO.getPatAdmnNo()+"\n";
				message += "Admission Date Time: "+voDP.getAdmDateTime()+"\n";
				if(callVO.getIsDocCallByPeon().equals(InpatientConfig.DOC_CALL_BY_PEON_YES))
				{
					message += "Call By Peon \n";
				}
					if(callVO.getDocCallByPeonRemarks()!=null)
					{
						message += "Peon Remarks: " + _callBookVO.getDocCallByPeonRemarks();
					}
				//code from sending message through CDAC MUmbai SMS Gateway
				//SMSHttpPostClient.sendSingleSMSThroughSMSGateway(objSMSConfig.sms_username, objSMSConfig.sms_password,objSMSConfig.sms_senderId,objSMSConfig.sms_url,docPhoneNo,message);//docPhoneNo
				
				//code from sending message through NIC SMS Gateway
				SMSHttpsNICPostClient.sendTextSMSThroughNICSMSGateway(objSMSConfig.nic_sms_username, objSMSConfig.nic_sms_password,objSMSConfig.nic_sms_senderId,objSMSConfig.nic_sms_url, docPhoneNo,message);
			}
			//end:NehaRajgariya
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Updated Successfully", "");
			 }else
			 {	
				 objStatus.add(Status.NEW, "", "");
			 }

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			hasFlag = false;

		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static void getCallRemarks(DoctorCallBookFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HttpSession session=_request.getSession();
		DoctorCallBookVO doctorCallBookVO=new DoctorCallBookVO();
		try
		{
			//UserVO userVO = getUserVO(_request);
			int index=Integer.parseInt(_fb.getIndex());
			
			setSysdate(_request);
			DoctorCallBookVO[] doctorCallBookVOArray = (DoctorCallBookVO[]) session.getAttribute(InpatientConfig.CALL_BOOK_VO_ARRAY);
		
			doctorCallBookVO.setDoctorRemarks(doctorCallBookVOArray[index].getDoctorRemarks());
			doctorCallBookVO.setCallRemarks(doctorCallBookVOArray[index].getCallRemarks());
			_fb.setDoctorRemarks(doctorCallBookVO.getDoctorRemarks());
			_fb.setCallRemarks(doctorCallBookVO.getCallRemarks());
				objStatus.add(Status.LIST);
				objStatus.add(Status.NEW);
				objStatus.add(Status.RECORDFOUND);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	
	
		}
	public static void getCallRemarksNNotes(DoctorCallBookFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		
		try
		{
			List lst=DoctorCallBookDATA.getCallRemarksNNotes(fb.getProcessId(),getUserVO(request));
			WebUTIL.setAttributeInSession(request, InpatientConfig.PROGRESS_NOTES_LIST , lst);
			if(lst.size()>0)
				objStatus.add(Status.NEW, "", "");
			else
				objStatus.add(Status.NEW, "", "No Call Remarks Found");
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch(HisDataAccessException e){
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		
		catch(Exception e)
		{
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}	
	}	

	
}
