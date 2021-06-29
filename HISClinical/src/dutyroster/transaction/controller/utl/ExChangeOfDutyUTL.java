package dutyroster.transaction.controller.utl;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.GregorianCalendar;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisEpisodeOpenInEmergencyException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.DutyRosterShiftMasterVO;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterExChangeDetailVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.transaction.controller.data.ExChangeOfDutyDATA;
import dutyroster.transaction.controller.fb.EmployeeDutyRosterFB;
import dutyroster.transaction.controller.fb.ExChangeOfDutyFB; 

public class ExChangeOfDutyUTL extends ControllerUTIL
{

	


	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean getRosterMainCategory(HttpServletRequest _request,ExChangeOfDutyFB _fb)
	{
		Status objStatus = new Status();
		
		List rosterMainCatgList=new ArrayList();
		
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
				
			 
			 
			ExChangeOfDutyUTL.getYearList(_request,_fb);
			ExChangeOfDutyUTL.getMonthsList(_request,_fb);
				
				
			 
			rosterMainCatgList=ExChangeOfDutyDATA.getRosterMainCategory(_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY, rosterMainCatgList);
			
						
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY, new ArrayList());
			System.out.println("Inside HisRecordNotFoundException");
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
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}	
	
	


	
		


	 
	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean getRosterCategoryBasedOnRosterMainCategory(HttpServletRequest _request,ExChangeOfDutyFB _fb)
	{
		Status objStatus = new Status();
		
		List areaList=new ArrayList();
		
		
		try
		{
			UserVO _userVO = getUserVO(_request);
		
						
			areaList=ExChangeOfDutyDATA.getRosterCategoryBasedOnRosterMainCategory(_fb.getRosterMainCatg(),_userVO);			
		
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTER_CATEGORY, areaList);				
		
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(), "");
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
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}	
	
	
	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean getEmpList(HttpServletRequest _request,ExChangeOfDutyFB _fb)
	{
		Status objStatus = new Status();
		
		List empList=new ArrayList();
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			
					
			
		
			empList=ExChangeOfDutyDATA.getEmpList(_fb.getRosterCatg(),_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUEST_BY_EMP_TO_BE_EXCHANGED, empList);
			
					
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUEST_BY_EMP_TO_BE_EXCHANGED, empList);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(), "");
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
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}	
	
	
	
	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean getRequestByEmpDutyListForExchange(HttpServletRequest _request,ExChangeOfDutyFB _fb)
	{
		Status objStatus = new Status();
		
		RosterDtlVO[] _rosterDtlVO=null;
		RosterDtlVO[] _rosterDtlVOExchangeWith=null;
		
		List empList=new ArrayList();
		List empListNew=new ArrayList();
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			
					
			
		/////////1st list of Duties of 1st Employee/////////////// 
			_rosterDtlVO=ExChangeOfDutyDATA.getEmpDutyListForExchange(_fb.getYear(),_fb.getMonth(),_fb.getRosterCatg(),_fb.getRequestedEmp(),_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUESTED_BY_EMP_DUTY_LIST_TO_BE_EXCHANGED, _rosterDtlVO);
			
			
			////////////1st we get the requested emp List/////////
			empList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_REQUEST_BY_EMP_TO_BE_EXCHANGED);
			
			
			////////////2nd we get the Exchanged emp List,from the request emp List/////////			
			empListNew=(ArrayList)WebUTIL.removeEntriesfromOptions(empList, _fb.getRequestedEmp());
			
			
			//////Now setting the new list//////////////
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_REQUEST_WITH_EMP_TO_BE_EXCHANGED, empListNew);
			
			
			//For setting the new list of Exchange with employee null
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_EXCHANGED, _rosterDtlVOExchangeWith);
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_CHANGED, _rosterDtlVOExchangeWith);
			
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUESTED_BY_EMP_DUTY_LIST_TO_BE_EXCHANGED, _rosterDtlVO);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(), "");
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
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}	
	

	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean getRequestWithEmpDutyListForExchange(HttpServletRequest _request,ExChangeOfDutyFB _fb)
	{
		Status objStatus = new Status();
		
		RosterDtlVO[] _rosterDtlVOForExchange=null;
		RosterDtlVO[] _rosterDtlVOForChange=null;
		
		List empList=new ArrayList();
		List empListNew=new ArrayList();
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			
			String year=_fb.getYear();
			String month=_fb.getMonth();
			
			
			
			if(_fb.getExchangeWithMonth().equals("N"))
				{
					month=Integer.toString(Integer.parseInt(month)+1);
					
					if(Integer.parseInt(month) >12 )
						{	
						month="1";
						year=Integer.toString(Integer.parseInt(year)+1);
						}
				}
			
		/////////2nd list of Duties of 2nd Employee/////////////// 
			
		
	if(_fb.getMode().equals("1")) 	//FOR EXCHANGE
		{
		_rosterDtlVOForExchange=ExChangeOfDutyDATA.getEmpDutyListForExchange(year,month,_fb.getRosterCatg(),_fb.getExchangedEmp(),_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_EXCHANGED, _rosterDtlVOForExchange);
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_CHANGED, _rosterDtlVOForChange);
		
		}
	else
if(_fb.getMode().equals("2"))	//FOR CHANGE
		{
	
	String[] arrayOfRequestedData=_fb.getSelectRequestedEmp().split("@");
	
	String[] arrayofDateDetails=arrayOfRequestedData[1].split("-");
	
	String day=Integer.toString(Integer.parseInt(arrayofDateDetails[0]));
	
		_rosterDtlVOForChange=ExChangeOfDutyDATA.getEmpDutyListForChange(year,month,day,_fb.getRosterCatg(),_fb.getExchangedEmp(),_userVO);			
		WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_CHANGED, _rosterDtlVOForChange);
		WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_EXCHANGED, _rosterDtlVOForExchange);
		}
	
	
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_EXCHANGED, _rosterDtlVOForExchange);
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_REQUESTED_WITH_EMP_DUTY_LIST_TO_BE_CHANGED, _rosterDtlVOForChange);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(), "");
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
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}	
	
	
	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean saveExchangeofDuty(HttpServletRequest _request,ExChangeOfDutyFB _fb)
	{
		Status objStatus = new Status();
		
		boolean hasFlag = true;
		
		RosterExChangeDetailVO _exchangeDtlVO=null;//For inserting into the HDRT_ROSTER_EXCHANGE_DTL Table
		
		RosterDtlVO _requestedEmpCancelOldVO=null;//For Updating the reliver employee of  HDRT_ROSTER_DTL  Table
		RosterDtlVO _exchangeEmpCancelOldVO=null;///For Updating the exchange employee of  HDRT_ROSTER_DTL Table
		RosterDtlVO _requestedEmpInsertNewVO=null;//For inserting reliver emp into the HDRT_ROSTER_DTL  Table
		RosterDtlVO _exchangeEmpInsertNewVO=null;///For inserting exchange emp into the HDRT_ROSTER_DTL  Table
		
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			
			
			
				/////////////////For inserting into the HDRT_ROSTER_EXCHANGE_DTL Table/////////////
			 _exchangeDtlVO=ExChangeOfDutyUTL.getExchangeRosterUpdate(_request, _fb);
			
			 
			 	/////////////////For Updating the reliver employee of  HDRT_ROSTER_DTL  Table/////////////
			 _requestedEmpCancelOldVO=ExChangeOfDutyUTL.getRequestedEmpCancelledForExchange(_request, _fb);
			 
			 
				/////////////////For Updating the exchange employee of  HDRT_ROSTER_DTL Table/////////////
			 _exchangeEmpCancelOldVO=ExChangeOfDutyUTL.getExchangedEmpCancelledForExchange(_request, _fb); 
			 
			 
			 /////////////////For inserting into the HDRT_ROSTER_DTL  Table/////////////
			 _requestedEmpInsertNewVO=ExChangeOfDutyUTL.getRequestedEmpInsertForExchange(_request, _fb);
			 
			 
			 /////////////////For inserting into the HDRT_ROSTER_DTL  Table/////////////
			 _exchangeEmpInsertNewVO=ExChangeOfDutyUTL.getExchangedEmpInsert(_request, _fb);
			 
			 
			ExChangeOfDutyDATA.saveExchangeofDuty(_exchangeDtlVO,_requestedEmpCancelOldVO,_exchangeEmpCancelOldVO,_requestedEmpInsertNewVO,_exchangeEmpInsertNewVO,_userVO);			
			
			
					
			
			objStatus.add(Status.NEW,"","Duty is Exchanged Succesfully");
		}
		catch (HisDuplicateRecordException e)
		{
			hasFlag=false;
			e.printStackTrace();
			System.out.println("Inside HisDuplicateRecordException");
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(), "");
		}
		catch (HisRecordNotFoundException e)
		{
		
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(), "");
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
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}	
	
	
	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean saveChangeofDuty(HttpServletRequest _request,ExChangeOfDutyFB _fb)
	{
		Status objStatus = new Status();
		
		
		
		RosterExChangeDetailVO _exchangeDtlVO=null;//For inserting into the HDRT_ROSTER_EXCHANGE_DTL Table
		
		RosterDtlVO _requestedEmpCancelOldVO=null;//For Updating the reliver employee of  HDRT_ROSTER_DTL  Table
		RosterDtlVO _changeEmpInsertNewVO=null;///For inserting exchange emp into the HDRT_ROSTER_DTL  Table
		
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			
			
			
				/////////////////For inserting into the HDRT_ROSTER_EXCHANGE_DTL Table/////////////
			 _exchangeDtlVO=ExChangeOfDutyUTL.getChangeRosterUpdate(_request, _fb);
			
			 
			 	/////////////////For Updating the reliver employee of  HDRT_ROSTER_DTL  Table/////////////
			 _requestedEmpCancelOldVO=ExChangeOfDutyUTL.getRequestedEmpCancelledForChange(_request, _fb);
			 
					 
			 /////////////////For inserting into the HDRT_ROSTER_DTL  Table/////////////
			 _changeEmpInsertNewVO=ExChangeOfDutyUTL.getChangedEmpInsert(_request, _fb);
			 
			 
			ExChangeOfDutyDATA.saveChangeofDuty(_exchangeDtlVO,_requestedEmpCancelOldVO,_changeEmpInsertNewVO,_userVO);			
			
			
					
			
			objStatus.add(Status.NEW,"","Duty is Changed Succesfully");
		}
		catch (HisRecordNotFoundException e)
		{
		
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,e.getMessage(), "");
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
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}	
	
	

///////Fn Used for getting the HDRT_ROSTER_EXCHANGE_DTL VO to be inserted/////////////	
	
	
public static RosterExChangeDetailVO getExchangeRosterUpdate(HttpServletRequest _request,ExChangeOfDutyFB _fb)
	{
	
	
	
	
	String[] arrayOfExchangeForData=_fb.getSelectRequestedEmp().split("@");
	String[] arrayOfExchangeByData=_fb.getSelectExchangedEmp().split("@");
	String startDateTime="";
	String	endDateTime="";
	String startDateTimeExBy="";
	String endDateTimeExBy="";
	String shifId=""; String generatedShift=""; String generatedBy=""; String shiftExBy="";
	
	RosterExChangeDetailVO _rosterForExchange=new RosterExChangeDetailVO();
	
	if(arrayOfExchangeForData.length > 0)
	{
	 startDateTime=arrayOfExchangeForData[1] +" "+arrayOfExchangeForData[3]+":00";
	 endDateTime=arrayOfExchangeForData[1] +" "+arrayOfExchangeForData[4]+":00";

	 if(arrayOfExchangeByData.length > 0)
		{
	 startDateTimeExBy=arrayOfExchangeByData[1] +" "+arrayOfExchangeByData[3]+":00";
	 endDateTimeExBy=arrayOfExchangeByData[1] +" "+arrayOfExchangeByData[4]+":00";
		}
	
	//in case the shift is day off ,then no need to enter start and end time 
	if(arrayOfExchangeForData[2].equals("0"))
	{
		 startDateTime=arrayOfExchangeForData[1];
		 endDateTime=arrayOfExchangeForData[1];
	}
	
	
	//in case the shift is day off ,then no need to enter start and end time 
	if(arrayOfExchangeByData[2].equals("0"))
	{
		startDateTimeExBy=arrayOfExchangeByData[1];
		endDateTimeExBy=arrayOfExchangeByData[1];
	}
  
	generatedShift=arrayOfExchangeForData[0];
	shifId=arrayOfExchangeForData[2];
	generatedBy=arrayOfExchangeByData[0];
	shiftExBy=arrayOfExchangeByData[2];
	}
	
	_rosterForExchange.setExchangeForEmp(_fb.getRequestedEmp());
	_rosterForExchange.setRequestStatus(DutyRosterConfig.EXCHANGE_REQUEST_STATUS_APPROVED);
	_rosterForExchange.setShiftId(shifId);
	_rosterForExchange.setExchangeType(_fb.getMode());
	_rosterForExchange.setStartDateTime(startDateTime);
	_rosterForExchange.setEndDateTime(endDateTime);
	_rosterForExchange.setExchangeByEmp(_fb.getExchangedEmp());
	_rosterForExchange.setGeneratedRosterId(generatedShift);
	_rosterForExchange.setGeneratedRosterIdExBy(generatedBy);
	_rosterForExchange.setReason(_fb.getReason());
	_rosterForExchange.setShiftIdExBy(shiftExBy);
	_rosterForExchange.setStartDateTimeExBy(startDateTimeExBy);
	_rosterForExchange.setEndDateTimeExBy(endDateTimeExBy);
	
	
	
	return _rosterForExchange;	
	}

///////Fn Used for getting the HDRT_ROSTER_EXCHANGE_DTL VO to be inserted/////////////	


public static RosterExChangeDetailVO getChangeRosterUpdate(HttpServletRequest _request,ExChangeOfDutyFB _fb)
	{
	
	
	
	
	String[] arrayOfExchangeForData=_fb.getSelectRequestedEmp().split("@");
	//String[] arrayOfExchangeByData=_fb.getSelectExchangedEmp().split("@");
	
	RosterExChangeDetailVO _rosterForExchange=new RosterExChangeDetailVO();
	
	String startDateTime=arrayOfExchangeForData[1] +" "+arrayOfExchangeForData[3]+":00";
	String endDateTime=arrayOfExchangeForData[1] +" "+arrayOfExchangeForData[4]+":00";

	
//	String startDateTimeExBy=arrayOfExchangeByData[1] +" "+arrayOfExchangeByData[3]+":00";
//	String endDateTimeExBy=arrayOfExchangeByData[1] +" "+arrayOfExchangeByData[4]+":00";

	
	//in case the shift is day off ,then no need to enter start and end time 
	if(arrayOfExchangeForData[2].equals("0"))
	{
		 startDateTime=arrayOfExchangeForData[1];
		 endDateTime=arrayOfExchangeForData[1];
	}
	
	
	//in case the shift is day off ,then no need to enter start and end time 
	/*if(arrayOfExchangeByData[2].equals("0"))
	{
		startDateTimeExBy=arrayOfExchangeByData[1];
		endDateTimeExBy=arrayOfExchangeByData[1];
	}*/

		
	
	_rosterForExchange.setExchangeForEmp(_fb.getRequestedEmp());
	_rosterForExchange.setRequestStatus(DutyRosterConfig.EXCHANGE_REQUEST_STATUS_APPROVED);
	_rosterForExchange.setShiftId(arrayOfExchangeForData[2]);
	_rosterForExchange.setExchangeType(_fb.getMode());
	_rosterForExchange.setStartDateTime(startDateTime);
	_rosterForExchange.setEndDateTime(endDateTime);
	_rosterForExchange.setExchangeByEmp(_fb.getExchangedEmp());
	_rosterForExchange.setGeneratedRosterId(arrayOfExchangeForData[0]);
//	_rosterForExchange.setGeneratedRosterIdExBy(arrayOfExchangeByData[0]);
	_rosterForExchange.setReason(_fb.getReason());
//	_rosterForExchange.setShiftIdExBy(arrayOfExchangeByData[2]);
//	_rosterForExchange.setStartDateTimeExBy(startDateTimeExBy);
//	_rosterForExchange.setEndDateTimeExBy(endDateTimeExBy);//no need to enter the commented  fields,
														  //				in case of change
	
	
	
	return _rosterForExchange;	
	}

///////Fn Used for getting the Requested Emp duty to be cancelled   HDRT_ROSTER_DTL/////////////


public static RosterDtlVO getRequestedEmpCancelledForExchange(HttpServletRequest _request,ExChangeOfDutyFB _fb)
{




String[] arrayOfExchangeForData=_fb.getSelectRequestedEmp().split("@");

RosterDtlVO _rosterDtlVO=new RosterDtlVO();

_rosterDtlVO.setGeneratedRosterId(arrayOfExchangeForData[0]);
_rosterDtlVO.setEmpId(_fb.getRequestedEmp());
_rosterDtlVO.setStartDateTime(arrayOfExchangeForData[1]);
_rosterDtlVO.setEndDateTime(arrayOfExchangeForData[1]);
_rosterDtlVO.setDutyDone(DutyRosterConfig.DUTY_DONE_IS_EXCHANGED);/////Duty Done is Exhanged

return _rosterDtlVO;	
}


///////Fn Used for getting the Requested Emp duty to be cancelled   HDRT_ROSTER_DTL/////////////


public static RosterDtlVO getRequestedEmpCancelledForChange(HttpServletRequest _request,ExChangeOfDutyFB _fb)
{




String[] arrayOfExchangeForData=_fb.getSelectRequestedEmp().split("@");

RosterDtlVO _rosterDtlVO=new RosterDtlVO();

_rosterDtlVO.setGeneratedRosterId(arrayOfExchangeForData[0]);
_rosterDtlVO.setEmpId(_fb.getRequestedEmp());
_rosterDtlVO.setStartDateTime(arrayOfExchangeForData[1]);
_rosterDtlVO.setEndDateTime(arrayOfExchangeForData[1]);
_rosterDtlVO.setDutyDone(DutyRosterConfig.DUTY_DONE_IS_CHANGED);/////Duty Done is Changed

return _rosterDtlVO;	
}


///////Fn Used for getting the exchanged emp duty to be cancelled   HDRT_ROSTER_DTL/////////////

public static RosterDtlVO getExchangedEmpCancelledForExchange(HttpServletRequest _request,ExChangeOfDutyFB _fb)
{

String[] arrayOfExchangeByData=_fb.getSelectExchangedEmp().split("@");

RosterDtlVO _rosterDtlVO=new RosterDtlVO();

_rosterDtlVO.setGeneratedRosterId(arrayOfExchangeByData[0]);
_rosterDtlVO.setEmpId(_fb.getExchangedEmp());
_rosterDtlVO.setStartDateTime(arrayOfExchangeByData[1]);
_rosterDtlVO.setEndDateTime(arrayOfExchangeByData[1]);
_rosterDtlVO.setDutyDone(DutyRosterConfig.DUTY_DONE_IS_EXCHANGED);/////Duty Done is Exhanged

return _rosterDtlVO;	
}


///////Fn Used for getting the Requested Emp New Duty to be inserted HDRT_ROSTER_DTL/////////////

public static RosterDtlVO getRequestedEmpInsertForExchange(HttpServletRequest _request,ExChangeOfDutyFB _fb)
{
		String[] arrayOfExchangeForData=_fb.getSelectRequestedEmp().split("@");
		
		String[] arrayOfExchangeByData=_fb.getSelectExchangedEmp().split("@");
	 
		///shift timings of exchange emp is added
		
		String startDateTime=arrayOfExchangeByData[1] +" "+arrayOfExchangeByData[3]+":00";
		String endDateTime=arrayOfExchangeByData[1] +" "+arrayOfExchangeByData[4]+":00";
	 
	 

		if(arrayOfExchangeByData[2].equals("0"))
			{
			 startDateTime=arrayOfExchangeByData[1];
			 endDateTime=arrayOfExchangeByData[1] ;
		   }
		

RosterDtlVO _rosterDtlVO=new RosterDtlVO();


_rosterDtlVO.setGeneratedRosterId(arrayOfExchangeByData[0]);
_rosterDtlVO.setRosterId(arrayOfExchangeByData[5]);
_rosterDtlVO.setAreaCode(arrayOfExchangeByData[6]);
_rosterDtlVO.setAreaTypeCode(arrayOfExchangeByData[7]);
_rosterDtlVO.setEmpId(_fb.getRequestedEmp());////only emp id of the requested emp ,rest all details are of exchanged emp		
_rosterDtlVO.setShiftId(arrayOfExchangeByData[2]);///shift of exchange emp is added 
_rosterDtlVO.setStartDateTime(startDateTime);
_rosterDtlVO.setEndDateTime(endDateTime);
//_rosterDtlVO.setDutyDone(dutyDone)
_rosterDtlVO.setDutyType(DutyRosterConfig.DUTY_TYPE_EXCHANGE);




///shift timings of exchange emp is added




return _rosterDtlVO;	
}



///////Fn Used for getting the exchanged emp new duty to be inserted  HDRT_ROSTER_DTL/////////////


public static RosterDtlVO getExchangedEmpInsert(HttpServletRequest _request,ExChangeOfDutyFB _fb)
{
	String[] arrayOfExchangeForData=_fb.getSelectRequestedEmp().split("@");
	
	String[] arrayOfExchangeByData=_fb.getSelectExchangedEmp().split("@");
 
	///shift timings of exchange emp is added
	
	String startDateTime=arrayOfExchangeForData[1] +" "+arrayOfExchangeForData[3]+":00";
	String endDateTime=arrayOfExchangeForData[1] +" "+arrayOfExchangeForData[4]+":00";
 
 

	if(arrayOfExchangeForData[2].equals("0"))
		{
		 startDateTime=arrayOfExchangeForData[1];
		 endDateTime=arrayOfExchangeForData[1] ;
	   }
	

RosterDtlVO _rosterDtlVO=new RosterDtlVO();


_rosterDtlVO.setGeneratedRosterId(arrayOfExchangeForData[0]);
_rosterDtlVO.setRosterId(arrayOfExchangeForData[5]);
_rosterDtlVO.setAreaCode(arrayOfExchangeForData[6]);
_rosterDtlVO.setAreaTypeCode(arrayOfExchangeForData[7]);
_rosterDtlVO.setEmpId(_fb.getExchangedEmp());////only emp id of the Exchanged emp ,rest all details are of Requested emp		
_rosterDtlVO.setShiftId(arrayOfExchangeForData[2]);///shift of Requested emp is added 
_rosterDtlVO.setStartDateTime(startDateTime);
_rosterDtlVO.setEndDateTime(endDateTime);
//_rosterDtlVO.setDutyDone(dutyDone)
_rosterDtlVO.setDutyType(DutyRosterConfig.DUTY_TYPE_EXCHANGE);







return _rosterDtlVO;	
}


///////Fn Used for getting the exchanged emp new duty to be inserted  HDRT_ROSTER_DTL/////////////


public static RosterDtlVO getChangedEmpInsert(HttpServletRequest _request,ExChangeOfDutyFB _fb)
{
	String[] arrayOfExchangeForData=_fb.getSelectRequestedEmp().split("@");
	
	//String[] arrayOfExchangeByData=_fb.getSelectExchangedEmp().split("@");
 
	///shift timings of exchange emp is added
	
	String startDateTime=arrayOfExchangeForData[1] +" "+arrayOfExchangeForData[3]+":00";
	String endDateTime=arrayOfExchangeForData[1] +" "+arrayOfExchangeForData[4]+":00";
 
 

	if(arrayOfExchangeForData[2].equals("0"))
		{
		 startDateTime=arrayOfExchangeForData[1];
		 endDateTime=arrayOfExchangeForData[1] ;
	   }
	

RosterDtlVO _rosterDtlVO=new RosterDtlVO();


_rosterDtlVO.setGeneratedRosterId(arrayOfExchangeForData[0]);
_rosterDtlVO.setRosterId(arrayOfExchangeForData[5]);
_rosterDtlVO.setAreaCode(arrayOfExchangeForData[6]);
_rosterDtlVO.setAreaTypeCode(arrayOfExchangeForData[7]);
_rosterDtlVO.setEmpId(_fb.getExchangedEmp());////only emp id of the Exchanged emp ,rest all details are of Requested emp		
_rosterDtlVO.setShiftId(arrayOfExchangeForData[2]);///shift of Requested emp is added 
_rosterDtlVO.setStartDateTime(startDateTime);
_rosterDtlVO.setEndDateTime(endDateTime);
//_rosterDtlVO.setDutyDone(dutyDone)
_rosterDtlVO.setDutyType(DutyRosterConfig.DUTY_TYPE_CHANGE);







return _rosterDtlVO;	
}


/**For getting the Year List 
 * 
 * @param _request
 * @param _fb
 * @return
 */


public static void  getYearList(HttpServletRequest _request,ExChangeOfDutyFB _fb)

{
	List yearList=new ArrayList();
	Calendar calendar1 =new GregorianCalendar();	
	Date trialTime=new Date();
	
	calendar1.setTime(trialTime);
	
	//System.out.println("monthAnkur------"+calendar1.get(Calendar.MONTH));
	
	int arrayOfYears[]=null;
	                 
	if(calendar1.get(Calendar.MONTH)==11)
			 arrayOfYears= new int [DutyRosterConfig.NO_OF_YEARS_TO_BE_SHOWN_IN_EMPLOYEE_DUTY_ROSTER];
	else
		 	arrayOfYears= new int [DutyRosterConfig.NO_OF_YEARS_TO_BE_SHOWN_IN_EMPLOYEE_DUTY_ROSTER-1];
	
	
	
	
	
	arrayOfYears[1]=calendar1.get(Calendar.YEAR);
	arrayOfYears[0]=arrayOfYears[1]-1;

if(calendar1.get(Calendar.MONTH)==11)
	arrayOfYears[2]=arrayOfYears[1]+1;


	for(int i=0;i< arrayOfYears.length ; i++)
		{
	
	Entry objEntry = new Entry();
	
	objEntry.setLabel(Integer.toString(arrayOfYears[i]));
	objEntry.setValue(Integer.toString(arrayOfYears[i]));
	System.out.println("Entry: " + objEntry);
	yearList.add(objEntry);
	
		}
	_fb.setYear(Integer.toString(arrayOfYears[1]));
			
	WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_YEARS_EMPLOYEE_DUTY_ROSTER_MASTER, yearList);

	
}


/**For getting theMonths List
 * 
 * @param _request
 * @param _fb
 * @return
 */



public static void  getMonthsList(HttpServletRequest _request,ExChangeOfDutyFB _fb)

{
	List monthsList=new ArrayList();
	Calendar calendar1 =new GregorianCalendar();	
	Date trialTime=new Date();
	
	
	String[] arrayOfMonths={"JANUARY","FEBRUARY","MARCH","APRIL","MAY","JUNE","JULY","AUGUST","SEPTEMBER","OCTOBER","NOVEMBER","DECEMBER"};
	
	calendar1.setTime(trialTime);
	int currentMonth=calendar1.get(Calendar.MONTH);
	
	
	for(int i=0;i< arrayOfMonths.length ; i++)
		{
	
	Entry objEntry = new Entry();
	
	objEntry.setLabel(arrayOfMonths[i]);
	objEntry.setValue(Integer.toString(i+1)); 
	System.out.println("Entry: " + objEntry);
	monthsList.add(objEntry);
	
		}
	_fb.setMonth(Integer.toString(currentMonth+1));
			
	WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER, monthsList);

	
}
}
