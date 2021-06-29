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
import hisglobal.vo.RosterReliverDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest; 

import dutyroster.DutyRosterConfig;
import dutyroster.transaction.controller.data.ExChangeOfDutyDATA;
import dutyroster.transaction.controller.data.ReliverRosterDATA;
import dutyroster.transaction.controller.fb.ReliverRosterFB; 

/**
 * @author ankur
 *
 */
public class ReliverRosterUTL extends ControllerUTIL
{
	

	


	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst

	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean getRosterMainCategory(HttpServletRequest _request,ReliverRosterFB _fb)
	{
		Status objStatus = new Status();
		
		List rosterMainCatgList=new ArrayList();
		
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
				
			 
			 
			ReliverRosterUTL.getYearList(_request,_fb);
			ReliverRosterUTL.getMonthsList(_request,_fb);
				
				
			 
			rosterMainCatgList=ReliverRosterDATA.getRosterMainCategory(_userVO);			
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
	
	public static boolean getRosterCategoryBasedOnRosterMainCategory(HttpServletRequest _request,ReliverRosterFB _fb)
	{
		Status objStatus = new Status();
		
		List rosterCatgList=new ArrayList();
		
		
		try
		{
			UserVO _userVO = getUserVO(_request);
		
			
			/********Initally setting all the values *************/
			
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTER_CATEGORY, new ArrayList());
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY, new ArrayList());
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.EMP_LIST_BASED_ON_ROSTER_CATEGORY, new ArrayList());
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.RELIVER_EMP_LIST_BASED_ON_ROSTER_CATEGORY, new ArrayList());
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.SHIFT_LIST_BASED_ON_ROSTER_CATEGORY, new ArrayList());
			
					
			/********Initally setting all the values *************/
			
			
			rosterCatgList=ReliverRosterDATA.getRosterCategoryBasedOnRosterMainCategory(_fb.getRosterMainCatg(),_userVO);			
		
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTER_CATEGORY, rosterCatgList);				
		
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
	
	public static boolean getAreaListBasedOnRosterCategory(HttpServletRequest _request,ReliverRosterFB _fb)
	{
		Status objStatus = new Status();
		
		List areaList=new ArrayList();
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			
			
			
			/********Initally setting all the values *************/
			
						
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY, new ArrayList());
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.EMP_LIST_BASED_ON_ROSTER_CATEGORY, new ArrayList());
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.RELIVER_EMP_LIST_BASED_ON_ROSTER_CATEGORY, new ArrayList());
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.SHIFT_LIST_BASED_ON_ROSTER_CATEGORY, new ArrayList());
			
					
			/********Initally setting all the values *************/		
			
		
			areaList=ReliverRosterDATA.getAreaListBasedOnRosterCategory(_fb.getRosterCatg(),_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY, areaList);
			
					
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY, areaList);
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
	
	public static boolean getEmpandShiftListBasedOnRosterCategory(HttpServletRequest _request,ReliverRosterFB _fb)
	{
		Status objStatus = new Status();
		
				
		try
		{
			UserVO _userVO = getUserVO(_request);
			
			
			
			/********Initally setting all the values *************/
										
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.EMP_LIST_BASED_ON_ROSTER_CATEGORY, new ArrayList());
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.RELIVER_EMP_LIST_BASED_ON_ROSTER_CATEGORY, new ArrayList());
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.SHIFT_LIST_BASED_ON_ROSTER_CATEGORY, new ArrayList());
			
					
			/********Initally setting all the values *************/	
			
			
			
			Map EssentialMap=new HashMap();
			
			String[] areaDetails=null;
					
			if(!_fb.getAreaCode().equals("-1"))
				areaDetails=_fb.getAreaCode().split("@");
			

			EssentialMap=ReliverRosterDATA.getEmpAndShiftListBasedOnRosterCategory(_fb.getYear(),_fb.getMonth(),_fb.getRosterCatg(),areaDetails[0],areaDetails[1],_fb.getReason(),_userVO);			
			WebUTIL.setMapInSession(EssentialMap,_request);
			
			
		
			
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
	
	public static boolean saveReliverOfDuty(HttpServletRequest _request,ReliverRosterFB _fb)
	{
		Status objStatus = new Status();
		
		boolean hasFlag = true;
		

		//For inserting reliver emp into the HDRT_ROSTER_DTL  Table
		
		RosterDtlVO[] _reliverEmpInsertNewVO=null;

		
		
		//For cancelling  the reliver employee old duty from   HDRT_ROSTER_DTL  Table
		
		RosterDtlVO[] _reliverEmpCancelOldVO=null;
		
		
		
		///For Updating the duty done=3 of the  Requested Employee of  HDRT_ROSTER_DTL Table
		
		RosterDtlVO[] _requestedEmpCancelOldVO=null;
		
		
		
		///For Modifying the day off duty for the next day 
		//of the Reliver  emp from  the HDRT_ROSTER_DTL  Table
		
		RosterDtlVO _reliverEmpModifyDayOffOldVO=null;
		
		
		//For inserting into the HDRT_ROSTER_RELIVER_DTL Table
		
		RosterReliverDtlVO[] _rosterReliverDtlVO=null;
		
		
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			
			
			
			//For inserting reliver emp into the HDRT_ROSTER_DTL  Table
			
			_reliverEmpInsertNewVO=ReliverRosterUTL.getReliverEmpInsertNewVO(_request, _fb);
			
			 
			//For cancelling  the reliver employee old duty from   HDRT_ROSTER_DTL  Table
			_reliverEmpCancelOldVO=ReliverRosterUTL.getReliverCancelOldVO(_request, _fb);
			 
			 
			//For cancelling  the reliver employee old duty from   HDRT_ROSTER_DTL  Table
if(_fb.getReason().equals(DutyRosterConfig.RELIVER_REASON_EMPLOYEE))			
			_requestedEmpCancelOldVO=ReliverRosterUTL.getRequestedEmpCancelOldVO(_request, _fb); 
			 
			 
			 ////For Modifying the  next day duty to  day off into the HDRT_ROSTER_DTL  Table///
			 
if(_fb.getIsDutyOff().equals("Y"))
				 _reliverEmpModifyDayOffOldVO=ReliverRosterUTL.getReliverEmpModifyOldDutyWithDayOffVO(_request, _fb);
			 
			 
			 /////////////////For inserting into the HDRT_ROSTER_RELIVER_DTL  Table/////////////
			 _rosterReliverDtlVO=ReliverRosterUTL.getRosterReliverEmpInsertNewVO(_request, _fb);
			 
			 
			ReliverRosterDATA.saveReliverOfDuty(_reliverEmpInsertNewVO,_reliverEmpCancelOldVO,_requestedEmpCancelOldVO,_reliverEmpModifyDayOffOldVO,_rosterReliverDtlVO,_userVO);			
			
			
					
			
			objStatus.add(Status.NEW,"","Duty is Assigned Succesfully");
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
	
	
	
	/** For getting the reliver emp  insert new VO with duty type=2
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */



	
	
	private static RosterDtlVO[] getReliverEmpInsertNewVO(HttpServletRequest _request, ReliverRosterFB _fb) {


		final long  MILLI_SEC_IN_EACH_DAY=1000*60*60*24;
	
		
		String[] startDateArray=_fb.getFromDate().split("-");
		String[] endDateArray=_fb.getToDate().split("-");
		
		
		
		
		//First Creating a startCalendar and endCalendar GregorianCalendar
		Calendar startCalendar =new GregorianCalendar();	
		Calendar endCalendar =new GregorianCalendar();
		Calendar[] tempCalendar=null;

		startCalendar.set(Integer.parseInt(startDateArray[2]),ReliverRosterUTL.getMonthValue(startDateArray[1]) ,Integer.parseInt(startDateArray[0]));
		endCalendar.set(Integer.parseInt(endDateArray[2]),ReliverRosterUTL.getMonthValue(endDateArray[1]) ,Integer.parseInt(endDateArray[0]));
		
		
		String fromDate=startDateArray[0]+"/"+Integer.toString(ReliverRosterUTL.getMonthValue(startDateArray[1])+1)+"/"+startDateArray[2];
		String toDate=endDateArray[0]+"/"+Integer.toString(ReliverRosterUTL.getMonthValue(endDateArray[1])+1)+"/"+endDateArray[2];
		
		
		long differenceOfDaysInMilliSec=(endCalendar.getTimeInMillis()-startCalendar.getTimeInMillis())/MILLI_SEC_IN_EACH_DAY;
		
		
		//First Computing the difference between the two  dates 
		
		int differenceOfDays=(int)differenceOfDaysInMilliSec;
		
		
		List listOfdays=new ArrayList();
		
		tempCalendar=new GregorianCalendar[differenceOfDays];
		
		listOfdays.add(startCalendar);
		
		for(int i=0; i < differenceOfDays;i++)
			{
			tempCalendar[i]=new GregorianCalendar();
			tempCalendar[i].set(Integer.parseInt(startDateArray[2]),ReliverRosterUTL.getMonthValue(startDateArray[1]) ,Integer.parseInt(startDateArray[0])+i+1);
			 listOfdays.add(tempCalendar[i]);
			
			}
		
		
		
		RosterDtlVO[] _reliverEmpInsertNewVO=new RosterDtlVO[listOfdays.size()];
		
		

		
		
		String[] arrayOfShiftDetails=_fb.getShiftId().split("@");
		
		
		String[] arrayOfAreaDetails=_fb.getAreaCode().split("@");
		
		
		String[] arrayOfReliverEmpDetails=_fb.getReliverEmpId().split("@"); 
		
		
		
				
			for(int j=0; j < listOfdays.size() ; j++)
				
			{
				
		Calendar dateCalendar=new GregorianCalendar();
				 dateCalendar=(GregorianCalendar)listOfdays.get(j);
		
		
				 _reliverEmpInsertNewVO[j]=new RosterDtlVO();
			       
			
			
			int monthCode=dateCalendar.get(Calendar.MONTH)+1;
			
			String startDateTime=dateCalendar.get(Calendar.DAY_OF_MONTH)+"/"+monthCode+"/"+dateCalendar.get(Calendar.YEAR)+" "+arrayOfShiftDetails[2]+":00";
			String endDateTime=dateCalendar.get(Calendar.DAY_OF_MONTH)+"/"+monthCode+"/"+dateCalendar.get(Calendar.YEAR)+" "+arrayOfShiftDetails[3]+":00";		
		
		
			
		
			_reliverEmpInsertNewVO[j].setGeneratedRosterId(arrayOfShiftDetails[4]);
			_reliverEmpInsertNewVO[j].setRosterId(arrayOfShiftDetails[0]);
			_reliverEmpInsertNewVO[j].setAreaCode(arrayOfAreaDetails[0]);
			_reliverEmpInsertNewVO[j].setAreaTypeCode(arrayOfAreaDetails[1]);
			_reliverEmpInsertNewVO[j].setEmpId(arrayOfReliverEmpDetails[0]);
			_reliverEmpInsertNewVO[j].setShiftId(arrayOfShiftDetails[1]);
			_reliverEmpInsertNewVO[j].setStartDateTime(startDateTime);
			_reliverEmpInsertNewVO[j].setEndDateTime(endDateTime);
			_reliverEmpInsertNewVO[j].setDutyType(DutyRosterConfig.DUTY_TYPE_AS_RELIVER);
			
			
			
			}
			
	
		
		
		return _reliverEmpInsertNewVO;

		

	}
	
	
	
	
	/**For getting Old Reliver Duty Done=5(For cancelling old duty)
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */



	
	
	
	private static RosterDtlVO[] getReliverCancelOldVO(HttpServletRequest _request, ReliverRosterFB _fb){


		final long  MILLI_SEC_IN_EACH_DAY=1000*60*60*24;
	
		
	
		String[] startDateArray=_fb.getFromDate().split("-");
		String[] endDateArray=_fb.getToDate().split("-");
		
		
		
		
		//First Creating a startCalendar and endCalendar GregorianCalendar
		Calendar startCalendar =new GregorianCalendar();	
		Calendar endCalendar =new GregorianCalendar();
		Calendar[] tempCalendar=null;

		startCalendar.set(Integer.parseInt(startDateArray[2]),ReliverRosterUTL.getMonthValue(startDateArray[1]) ,Integer.parseInt(startDateArray[0]));
		endCalendar.set(Integer.parseInt(endDateArray[2]),ReliverRosterUTL.getMonthValue(endDateArray[1]) ,Integer.parseInt(endDateArray[0]));
		
		
		String fromDate=startDateArray[0]+"/"+Integer.toString(ReliverRosterUTL.getMonthValue(startDateArray[1])+1)+"/"+startDateArray[2];
		String toDate=endDateArray[0]+"/"+Integer.toString(ReliverRosterUTL.getMonthValue(endDateArray[1])+1)+"/"+endDateArray[2];
		
		
		long differenceOfDaysInMilliSec=(endCalendar.getTimeInMillis()-startCalendar.getTimeInMillis())/MILLI_SEC_IN_EACH_DAY;
		
		
		//First Computing the difference between the two  dates 
		
		int differenceOfDays=(int)differenceOfDaysInMilliSec;
		
		
		List listOfdays=new ArrayList();
		
		tempCalendar=new GregorianCalendar[differenceOfDays];
		
		listOfdays.add(startCalendar);
		
		for(int i=0; i < differenceOfDays;i++)
			{
			tempCalendar[i]=new GregorianCalendar();
			tempCalendar[i].set(Integer.parseInt(startDateArray[2]),ReliverRosterUTL.getMonthValue(startDateArray[1]) ,Integer.parseInt(startDateArray[0])+i+1);
			 listOfdays.add(tempCalendar[i]);
			
			}
		
		
		
		RosterDtlVO[] _reliverEmpCancelOldVO=new RosterDtlVO[listOfdays.size()];
		
		
		String[] arrayOfReliverEmpDetails=_fb.getReliverEmpId().split("@"); 
		
		
		
				
			for(int j=0; j < listOfdays.size() ; j++)
				
			{
				
		Calendar dateCalendar=new GregorianCalendar();
				 dateCalendar=(GregorianCalendar)listOfdays.get(j);
		
		
				 _reliverEmpCancelOldVO[j]=new RosterDtlVO();
			       
			
			
			int monthCode=dateCalendar.get(Calendar.MONTH)+1;
			
			String startDateTime=dateCalendar.get(Calendar.DAY_OF_MONTH)+"/"+monthCode+"/"+dateCalendar.get(Calendar.YEAR);
			String endDateTime=dateCalendar.get(Calendar.DAY_OF_MONTH)+"/"+monthCode+"/"+dateCalendar.get(Calendar.YEAR);		
		
				
			_reliverEmpCancelOldVO[j].setGeneratedRosterId(arrayOfReliverEmpDetails[1]);
			_reliverEmpCancelOldVO[j].setEmpId(arrayOfReliverEmpDetails[0]);
			_reliverEmpCancelOldVO[j].setStartDateTime(startDateTime);
			_reliverEmpCancelOldVO[j].setEndDateTime(endDateTime);			
			_reliverEmpCancelOldVO[j].setDutyDone(DutyRosterConfig.DUTY_DONE_IS_CANCEL);
			
			
			
			}
			
	
		
		
		return _reliverEmpCancelOldVO;

		

	}





	/**For getting Old Requested Emp  Duty Done=5(For cancelling old duty)
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */



	
	
	
	private static RosterDtlVO[] getRequestedEmpCancelOldVO(HttpServletRequest _request, ReliverRosterFB _fb){


		final long  MILLI_SEC_IN_EACH_DAY=1000*60*60*24;
	
		
	
		String[] startDateArray=_fb.getFromDate().split("-");
		String[] endDateArray=_fb.getToDate().split("-");
		
		
		
		
		//First Creating a startCalendar and endCalendar GregorianCalendar
		Calendar startCalendar =new GregorianCalendar();	
		Calendar endCalendar =new GregorianCalendar();
		Calendar[] tempCalendar=null;

		startCalendar.set(Integer.parseInt(startDateArray[2]),ReliverRosterUTL.getMonthValue(startDateArray[1]) ,Integer.parseInt(startDateArray[0]));
		endCalendar.set(Integer.parseInt(endDateArray[2]),ReliverRosterUTL.getMonthValue(endDateArray[1]) ,Integer.parseInt(endDateArray[0]));
		
		
		String fromDate=startDateArray[0]+"/"+Integer.toString(ReliverRosterUTL.getMonthValue(startDateArray[1])+1)+"/"+startDateArray[2];
		String toDate=endDateArray[0]+"/"+Integer.toString(ReliverRosterUTL.getMonthValue(endDateArray[1])+1)+"/"+endDateArray[2];
		
		
		long differenceOfDaysInMilliSec=(endCalendar.getTimeInMillis()-startCalendar.getTimeInMillis())/MILLI_SEC_IN_EACH_DAY;
		
		
		//First Computing the difference between the two  dates 
		
		int differenceOfDays=(int)differenceOfDaysInMilliSec;
		
		
		List listOfdays=new ArrayList();
		
		tempCalendar=new GregorianCalendar[differenceOfDays];
		
		listOfdays.add(startCalendar);
		
		for(int i=0; i < differenceOfDays;i++)
			{
			tempCalendar[i]=new GregorianCalendar();
			tempCalendar[i].set(Integer.parseInt(startDateArray[2]),ReliverRosterUTL.getMonthValue(startDateArray[1]) ,Integer.parseInt(startDateArray[0])+i+1);
			 listOfdays.add(tempCalendar[i]);
			
			}
		
		
		
		RosterDtlVO[] _requestedEmpCancelOldVO=new RosterDtlVO[listOfdays.size()];
		
		
	
		
		String[] arrayOfRequestedEmpDetails=_fb.getRequestedEmpId().split("@"); 
		
		
		
				
			for(int j=0; j < listOfdays.size() ; j++)
				
			{
				
		Calendar dateCalendar=new GregorianCalendar();
				 dateCalendar=(GregorianCalendar)listOfdays.get(j);
		
		
				 _requestedEmpCancelOldVO[j]=new RosterDtlVO();
			       
			
			
			int monthCode=dateCalendar.get(Calendar.MONTH)+1;
			
			String startDateTime=dateCalendar.get(Calendar.DAY_OF_MONTH)+"/"+monthCode+"/"+dateCalendar.get(Calendar.YEAR);
			String endDateTime=dateCalendar.get(Calendar.DAY_OF_MONTH)+"/"+monthCode+"/"+dateCalendar.get(Calendar.YEAR);		
		
				
			_requestedEmpCancelOldVO[j].setGeneratedRosterId(arrayOfRequestedEmpDetails[1]);
			_requestedEmpCancelOldVO[j].setEmpId(arrayOfRequestedEmpDetails[0]);
			_requestedEmpCancelOldVO[j].setStartDateTime(startDateTime);
			_requestedEmpCancelOldVO[j].setEndDateTime(endDateTime);			
			_requestedEmpCancelOldVO[j].setDutyDone(DutyRosterConfig.DUTY_DONE_IS_FREE);
			
			
			
			}
			
	
		
		
		return _requestedEmpCancelOldVO;

		

	}

	
	
	
	

	
	
	/**For Modifying the next day duty of the reliver emp to day off
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */



	
	
	
	private static RosterDtlVO getReliverEmpModifyOldDutyWithDayOffVO(HttpServletRequest _request, ReliverRosterFB _fb){


		
		String[] arrayOfReliverEmpDetails=_fb.getReliverEmpId().split("@"); 
	
		
		
		
		RosterDtlVO _reliverEmpModifyOldDutyWithDayOffVO=new RosterDtlVO();
		
		
	
		
		//String[] arrayOfRequestedEmpDetails=_fb.getRequestedEmpId().split("@"); 
		
		
		_reliverEmpModifyOldDutyWithDayOffVO.setGeneratedRosterId(arrayOfReliverEmpDetails[1]);
		_reliverEmpModifyOldDutyWithDayOffVO.setEmpId(arrayOfReliverEmpDetails[0]);		
		_reliverEmpModifyOldDutyWithDayOffVO.setStartDateTime(_fb.getNextToReliverToDate());
		_reliverEmpModifyOldDutyWithDayOffVO.setEndDateTime(_fb.getNextToReliverToDate());
			
	
		
		
		return _reliverEmpModifyOldDutyWithDayOffVO;

		

	}
	
	


	

	/**For inserting into --> HDRT_ROSTER_RELIVER_DTL
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */



	
	
	
	private static RosterReliverDtlVO[] getRosterReliverEmpInsertNewVO(HttpServletRequest _request, ReliverRosterFB _fb){


		final long  MILLI_SEC_IN_EACH_DAY=1000*60*60*24;
	
		
	
		String[] startDateArray=_fb.getFromDate().split("-");
		String[] endDateArray=_fb.getToDate().split("-");
		
		
		
		
		//First Creating a startCalendar and endCalendar GregorianCalendar
		Calendar startCalendar =new GregorianCalendar();	
		Calendar endCalendar =new GregorianCalendar();
		Calendar[] tempCalendar=null;

		startCalendar.set(Integer.parseInt(startDateArray[2]),ReliverRosterUTL.getMonthValue(startDateArray[1]) ,Integer.parseInt(startDateArray[0]));
		endCalendar.set(Integer.parseInt(endDateArray[2]),ReliverRosterUTL.getMonthValue(endDateArray[1]) ,Integer.parseInt(endDateArray[0]));
		
		
		String fromDate=startDateArray[0]+"/"+Integer.toString(ReliverRosterUTL.getMonthValue(startDateArray[1])+1)+"/"+startDateArray[2];
		String toDate=endDateArray[0]+"/"+Integer.toString(ReliverRosterUTL.getMonthValue(endDateArray[1])+1)+"/"+endDateArray[2];
		
		
		long differenceOfDaysInMilliSec=(endCalendar.getTimeInMillis()-startCalendar.getTimeInMillis())/MILLI_SEC_IN_EACH_DAY;
		
		
		//First Computing the difference between the two  dates 
		
		int differenceOfDays=(int)differenceOfDaysInMilliSec;
		
		
		List listOfdays=new ArrayList();
		
		tempCalendar=new GregorianCalendar[differenceOfDays];
		
		listOfdays.add(startCalendar);
		
		for(int i=0; i < differenceOfDays;i++)
			{
			tempCalendar[i]=new GregorianCalendar();
			tempCalendar[i].set(Integer.parseInt(startDateArray[2]),ReliverRosterUTL.getMonthValue(startDateArray[1]) ,Integer.parseInt(startDateArray[0])+i+1);
			 listOfdays.add(tempCalendar[i]);
			
			}
		
		
		RosterReliverDtlVO[] _rosterReliverDtlVO =new RosterReliverDtlVO[listOfdays.size()];
		
		
		
		
	
		
		String[] arrayOfRequestedEmpDetails=_fb.getRequestedEmpId().split("@"); 
		
		
		String[] arrayOfShiftDetails=_fb.getShiftId().split("@");
		
		
		String[] arrayOfAreaDetails=_fb.getAreaCode().split("@");
		
		
		String[] arrayOfReliverEmpDetails=_fb.getReliverEmpId().split("@"); 
		
		
				
			for(int j=0; j < listOfdays.size() ; j++)
				
			{
				
		Calendar dateCalendar=new GregorianCalendar();
				 dateCalendar=(GregorianCalendar)listOfdays.get(j);
		
		
				 _rosterReliverDtlVO[j]=new RosterReliverDtlVO();

							
			int monthCode=dateCalendar.get(Calendar.MONTH)+1;
			
			String startDateTime=dateCalendar.get(Calendar.DAY_OF_MONTH)+"/"+monthCode+"/"+dateCalendar.get(Calendar.YEAR);
			String endDateTime=dateCalendar.get(Calendar.DAY_OF_MONTH)+"/"+monthCode+"/"+dateCalendar.get(Calendar.YEAR);		
		
			
			_rosterReliverDtlVO[j].setReliverEmp(arrayOfRequestedEmpDetails[0]);	//requested emp		
			_rosterReliverDtlVO[j].setRequestStatus(DutyRosterConfig.RELIVER_REQUEST_STATUS_APPROVED);
			_rosterReliverDtlVO[j].setShiftId(arrayOfShiftDetails[1]);
			_rosterReliverDtlVO[j].setReliverMode(_fb.getReason());
			_rosterReliverDtlVO[j].setStartDateTime(startDateTime);
			_rosterReliverDtlVO[j].setEndDateTime(endDateTime);			
			_rosterReliverDtlVO[j].setReliverId(arrayOfReliverEmpDetails[0]);	//reliver emp		
			_rosterReliverDtlVO[j].setGeneratedRosterId(arrayOfShiftDetails[4]); 
			_rosterReliverDtlVO[j].setReason(_fb.getReasonForReliver());
			
			
			}
			
	
		
		
		return _rosterReliverDtlVO;

		

	}



	/**For getting Months Value 
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */





	public static  int  getMonthValue(String monthName)
	{
			int monthValue=0; 
			
			
			String[] arrayOfMonths={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			
			
			
			
	for(int i=0;i< arrayOfMonths.length ; i++)
		{
			
		if(arrayOfMonths[i].equals(monthName))
				monthValue=i;
				
		}
			
					
			
	return monthValue;	
			
	}



	
	




/**For getting the Year List 
 * 
 * @param _request
 * @param _fb
 * @return
 */


public static void  getYearList(HttpServletRequest _request,ReliverRosterFB _fb)

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



public static void  getMonthsList(HttpServletRequest _request,ReliverRosterFB _fb)

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
