
package dutyroster.transaction.controller.utl;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest; 

import dutyroster.DutyRosterConfig;
import dutyroster.transaction.controller.data.DateWiseEmployeeDutyRosterDATA;
import dutyroster.transaction.controller.data.EmpRosterGenerationDATA;
import dutyroster.transaction.controller.fb.DateWiseEmployeeDutyRosterFB;
import dutyroster.transaction.controller.fb.LocationDutyRosterFB;

public class DateWiseEmployeeDutyRosterUTL extends ControllerUTIL
{

	


	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean getDutyRosterCategory(HttpServletRequest _request,DateWiseEmployeeDutyRosterFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterCategoryList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
			
			
		
			dutyRosterCategoryList=DateWiseEmployeeDutyRosterDATA.getDutyRosterCategory(_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTER_CATEGORY, dutyRosterCategoryList);
			
						
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
	
	
	// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst

	public static boolean getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(HttpServletRequest _request,DateWiseEmployeeDutyRosterFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO _userVO = getUserVO(_request);
		
			dutyRosterList=DateWiseEmployeeDutyRosterDATA.getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(_fb.getRosterCategory(),_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY, dutyRosterList);
			
			
			if(dutyRosterList.size()==1)
			{
				Entry obj=(Entry)dutyRosterList.get(0);
				String rostId=obj.getValue();
				_fb.setRosterId(rostId);
				DateWiseEmployeeDutyRosterUTL.getDutyAreaWithCapacityAndDesignationBasedOnRosterType(_request, _fb);
				DateWiseEmployeeDutyRosterUTL.getDateWiseEmployeesRoster(_request, _fb);
			}
			
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{ 
			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY,new ArrayList());
			e.printStackTrace();
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
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
	


	// Getting the Duty Area on the Basis of Duty Area Types
//		 from the Package PKG_DUTY_ROSTER using the Procedure Proc_get_duty_area
		
		public static boolean getDutyAreaWithCapacityAndDesignationBasedOnRosterType(HttpServletRequest _request,DateWiseEmployeeDutyRosterFB _fb)
		{
			Status objStatus = new Status();
			
			Map	EssentialMap =new HashMap();
			List dutyAreaList=new ArrayList();
			List desigList=new ArrayList();
			
			
			try
			{
				UserVO _userVO = getUserVO(_request);
			
	 
				

				//fetching the Roster ID Information from the Concatinated Values 
				String concateValues=_fb.getRosterId().replace("^","@");
				String splitValues[]=concateValues.split("@");
				
				String _rosterId=splitValues[0];
				String areaTypeCode=splitValues[1];
			
				
				EssentialMap=DateWiseEmployeeDutyRosterDATA.getDutyAreaWithCapacityAndDesignationBasedOnRosterType( _rosterId,_userVO);
				
			
				WebUTIL.setMapInSession(EssentialMap,_request);
				
				dutyAreaList=(ArrayList)EssentialMap.get(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA);
				desigList=(ArrayList)EssentialMap.get(DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE);
				
				if(dutyAreaList.size()==1)
				{
					Entry obj=(Entry)dutyAreaList.get(0);
						String areaId=obj.getValue();
					_fb.setAreaCode(areaId);
				
				}
				if(desigList.size()==1)
				{
					Entry obj=(Entry)desigList.get(0);
					String desigId=obj.getValue();
					_fb.setDesignationId(desigId);
				}
						
				if(dutyAreaList.size()==1 && desigList.size()==1)
					{
					DateWiseEmployeeDutyRosterUTL.getDateWiseEmployeesRoster(_request, _fb);
					}
				
				
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
				
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE, new ArrayList());
				
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE, new ArrayList());
								
				System.out.println("Inside HisDataAccessException");
				e.printStackTrace();
				objStatus.add(Status.ERROR_DA, "", "");
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
		



	
// Getting the Employees Based on Duty  Area type ,Area and Designation  

			
			public static boolean getDateWiseEmployeesRoster(HttpServletRequest _request,DateWiseEmployeeDutyRosterFB _fb)
			{
				Status objStatus = new Status();
				
				
				Map empRosterMap=new HashMap();
				Map empDutyAreaMap=new HashMap();
				List empRosterList=new ArrayList();
				RosterDtlVO[] _rosterDtlVO=null;
				
				try
				{
					UserVO _userVO = getUserVO(_request);
					
		 
					String concateValues=_fb.getRosterId().replace("^","@");
					String splitValues[]=concateValues.split("@");
					
					String _rosterId=splitValues[0];
					String _desigId=_fb.getDesignationId();
					String _areaTypeCode=splitValues[1];
					String _areaCode=_fb.getAreaCode().split("@")[0];
					String _month=_fb.getMonth();
					String _year=_fb.getYear();
					
//Getting the employees whose Roster has been prepared on the basis of  year,
//month,rosterId,areaTypeCode and areaCode		
					
					
					empRosterMap=DateWiseEmployeeDutyRosterDATA.getDateWiseEmployeesRoster(_desigId,_areaTypeCode, _areaCode, _rosterId, _userVO);
					empRosterList=(ArrayList)empRosterMap.get(DutyRosterConfig.LIST_OF_EMPLOYEES_WITH_ROSTER);
					
					
//if we get no employee whose roster has been prepared for the above specifiaction
//then we get the fresh employees based for the current roisterId,areaType,areaCode					
if(empRosterList!=null && (empRosterList.size()==0 || _fb.getModeOfRoster().equals("NEW")))
				{
					empDutyAreaMap=DateWiseEmployeeDutyRosterDATA.getEmployeesBasedOnDutyAreaAndDesignation( _desigId,_areaTypeCode, _areaCode, _rosterId, _userVO);
					WebUTIL.setMapInSession(empDutyAreaMap, _request);
					DateWiseEmployeeDutyRosterUTL.setDateWiseEmpDutyRosterCalendar(_request, _fb,_rosterDtlVO);
					_fb.setModeOfRoster("NEW");
					
				}	
				else////for getting the list of old rosters
if(empRosterList.size() > 0)	
				{
					WebUTIL.setMapInSession(empRosterMap, _request);
					DateWiseEmployeeDutyRosterUTL.getListOfDateWiseWiseRoster(_request,_fb);
				}
				
					
						
					_fb.setHmode("GET_EMPLOYEES_CALENDAR");
					
					objStatus.add(Status.NEW);
				}
				catch (HisRecordNotFoundException e)
				{
				
					
					e.printStackTrace();
					
					objStatus.add(Status.NEW, "", e.getMessage());
				}
				catch (HisDataAccessException e)
				{
					
					System.out.println("Inside HisDataAccessException");
					e.printStackTrace();
					objStatus.add(Status.ERROR_DA, "", "Duty Area Not Found for the Corresponding Duty Area Type");
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

	

			
// Getting the Employees Based on Duty  Area type ,Area and Designation  

						
public static boolean fetchDateWiseEmpRoster(HttpServletRequest _request,DateWiseEmployeeDutyRosterFB _fb)
						{
							Status objStatus = new Status();
							
							
							Map empRosterMap=new HashMap();
							Map empDutyAreaMap=new HashMap();
							List empRosterList=new ArrayList();
							RosterDtlVO[] _rosterDtlVO;
							
							try
							{
								UserVO _userVO = getUserVO(_request);
								
					 
								String concateValues=_fb.getRosterId().replace("^","@");
								String splitValues[]=concateValues.split("@");
								
								String _rosterId=splitValues[0];
								String _desigId=_fb.getDesignationId();
								String _areaTypeCode=splitValues[1];
								String _areaCode=_fb.getAreaCode().split("@")[0];
								String _month=_fb.getMonth();
								String _year=_fb.getYear();
								
								
			//Getting the employees whose Roster has been prepared on the basis of  year,
			//month,rosterId,areaTypeCode and areaCode		
								
								String _generatedRosterId=_fb.getGeneratedRosterId();
								
								
								empRosterMap=DateWiseEmployeeDutyRosterDATA.getDateWiseEmployeesRosterModify(_generatedRosterId,_desigId,_areaTypeCode, _areaCode, _rosterId, _userVO);
								_rosterDtlVO=(RosterDtlVO[])empRosterMap.get(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);
							
								_fb.setStartDate(_rosterDtlVO[0].getFromDate());
								_fb.setEndDate(_rosterDtlVO[0].getToDate());
								
								DateWiseEmployeeDutyRosterUTL.setDateWiseEmpDutyRosterCalendar(_request, _fb,_rosterDtlVO);
								
								objStatus.add(Status.NEW);
							}
							catch (HisRecordNotFoundException e)
							{
							
								
								e.printStackTrace();
								
								objStatus.add(Status.NEW, "", e.getMessage());
							}
							catch (HisDataAccessException e)
							{
								
								System.out.println("Inside HisDataAccessException");
								e.printStackTrace();
								objStatus.add(Status.ERROR_DA, "", "Duty Area Not Found for the Corresponding Duty Area Type");
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
						

//Getting the Employees Based on Duty  Area type ,Area and Designation  


public static boolean showDateWiseEmpRosterReport(HttpServletRequest _request,DateWiseEmployeeDutyRosterFB _fb)
						{
							Status objStatus = new Status();
							
							
							Map empRosterMap=new HashMap();
							Map empDutyAreaMap=new HashMap();
							List empRosterList=new ArrayList();
							RosterDtlVO[] _rosterDtlVO;
							
							try
							{
								UserVO _userVO = getUserVO(_request);
								
					 
								String concateValues=_fb.getRosterId().replace("^","@");
								String splitValues[]=concateValues.split("@");
								
								String _rosterId=splitValues[0];
								String _desigId=_fb.getDesignationId();
								String _areaTypeCode=splitValues[1];
								String _areaCode=_fb.getAreaCode().split("@")[0];
								String _month=_fb.getMonth();
								String _year=_fb.getYear();
								
			//Getting the employees whose Roster has been prepared on the basis of  year,
			//month,rosterId,areaTypeCode and areaCode		
								
								String _generatedRosterId=_fb.getGeneratedRosterId();
								
								
								empRosterMap=DateWiseEmployeeDutyRosterDATA.getDateWiseEmployeesRosterModify(_generatedRosterId,_desigId,_areaTypeCode, _areaCode, _rosterId, _userVO);
								_rosterDtlVO=(RosterDtlVO[])empRosterMap.get(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);
							
								_fb.setStartDate(_rosterDtlVO[0].getFromDate());
								_fb.setEndDate(_rosterDtlVO[0].getToDate());
								
								DateWiseEmployeeDutyRosterUTL.setDateWiseEmpDutyRosterCalendar(_request, _fb,_rosterDtlVO);
								
								objStatus.add(Status.NEW);
							}
							catch (HisRecordNotFoundException e)
							{
							
								
								e.printStackTrace();
								
								objStatus.add(Status.NEW, "", e.getMessage());
							}
							catch (HisDataAccessException e)
							{
								
								System.out.println("Inside HisDataAccessException");
								e.printStackTrace();
								objStatus.add(Status.ERROR_DA, "", "Duty Area Not Found for the Corresponding Duty Area Type");
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

	// On Add Page saving Values into Database
	public static boolean saveEmpDutyRosterDateWise(DateWiseEmployeeDutyRosterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
	
		try
		{
			UserVO userVO = getUserVO(_request);
			String[] arrayOfEmployees=_fb.getConcatedValueToBeSaved().split("#");
			
			RosterDtlVO[] _rosterDtlVO=DateWiseEmployeeDutyRosterUTL.getRosterDtlVOsToBeSaved(arrayOfEmployees,_request,_fb);		
			
			
		////Now setting the duty type=1
			for(int i=0; i < _rosterDtlVO.length;i++)	
				_rosterDtlVO[i].setDutyType(DutyRosterConfig.DUTY_TYPE_NORMAL);
			
			
			DateWiseEmployeeDutyRosterDATA.saveEmpDutyRosterDateWise(_rosterDtlVO, userVO);
			
			objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
		}
		catch(HisDuplicateRecordException e){	   		   	
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			hasFlag = false;
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
			objStatus.add(Status.ERROR_DA, "","Data Access Error");
			hasFlag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
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


	public static void  getYearList(HttpServletRequest _request,DateWiseEmployeeDutyRosterFB _fb)
	
	{
		List yearList=new ArrayList();
		Calendar calendar1 =new GregorianCalendar();	
		Date trialTime=new Date();
		int arrayOfYears[]= new int [DutyRosterConfig.NO_OF_YEARS_TO_BE_SHOWN_IN_EMPLOYEE_DUTY_ROSTER];
		
		
		calendar1.setTime(trialTime);
		
		arrayOfYears[1]=calendar1.get(Calendar.YEAR);
		arrayOfYears[0]=arrayOfYears[1]-1;
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






public static void  setDateWiseEmpDutyRosterCalendar(HttpServletRequest _request,DateWiseEmployeeDutyRosterFB _fb,RosterDtlVO[] _rosterDtlVO)

{
	List empList=new ArrayList();
	List shiftList=new ArrayList();
	List dayOffShiftList=new ArrayList();
	
	Map buttonMap=new LinkedHashMap();
	
	
	String shiftId="";
	String shiftName="";
	
	
	String shiftIdArray="";
	String shiftNameArray="";
	String shiftStartTimeArray="";
	String shiftEndTimeArray="";
	String shiftFullNameArray="";
	String shiftTypeArray="";
	
	String shiftIdDefault="";
	String shiftNameDefault="";
	String shiftStartTimeDefault="";
	String shiftEndTimeDefault="";
	String shiftFullNameDefault="";
	String shiftTypeDefault="";
	
	
	String shiftIdOff="";
	String shiftNameOff="";
	String shiftStartTimeOff="";
	String shiftEndTimeOff="";
	String shiftFullNameOff="";
	String shiftTypeOff="";
	

	

	
		
//Creating a string Buffer for Creating a New Calendar on the basis of employees mapped
///for the corresponding area type,areaCode and rosterType	
	StringBuilder strCal=new StringBuilder(); 
	
	
//getting the emmployees  and ShiftType List list  from the session


empList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE);
shiftList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
dayOffShiftList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS);	




//Creating the default Shift Type to be Selected 
//for each employee for each day


shiftIdDefault="-1";
shiftNameDefault="N A";
shiftStartTimeDefault="-1";
shiftEndTimeDefault="-1";
shiftFullNameDefault="Not Assigned";
shiftTypeDefault="-1";


//adding it into the array
shiftIdArray=shiftIdDefault+"^";
shiftNameArray=shiftNameDefault+"^";
shiftStartTimeArray=shiftStartTimeDefault+"^";
shiftEndTimeArray=shiftEndTimeDefault+"^";
shiftFullNameArray=shiftFullNameDefault+"^";
shiftTypeArray=shiftTypeDefault+"^";






Entry defaultEntryShift = new Entry();
defaultEntryShift=(Entry)dayOffShiftList.get(0);

String[] defaultshiftConcate=defaultEntryShift.getValue().replace("^","@").split("@");

shiftIdOff=defaultshiftConcate[0];
shiftNameOff=defaultEntryShift.getLabel();
shiftStartTimeOff=defaultshiftConcate[1];
shiftEndTimeOff=defaultshiftConcate[2];
shiftFullNameOff=defaultshiftConcate[3];
shiftTypeOff=defaultshiftConcate[0];




shiftIdArray+=shiftIdOff+"^";
shiftNameArray+=shiftNameOff+"^";
shiftStartTimeArray+=shiftStartTimeOff+"^";
shiftEndTimeArray+=shiftEndTimeOff+"^";
shiftFullNameArray+=shiftFullNameOff+"^";
shiftTypeArray+=shiftTypeOff+"^";







//For Creating arrays for the Dynamic Shift Types,with there StartTime and EndTime
for(int i=0 ; i <shiftList.size();i++)
{
Entry objEntryShift = new Entry();
objEntryShift=(Entry)shiftList.get(i);
shiftId=objEntryShift.getValue();
String[] shiftNameConcate=objEntryShift.getLabel().split("@");

shiftIdArray+=shiftId+"^";
shiftNameArray+=shiftNameConcate[0]+"^";
shiftStartTimeArray+=shiftNameConcate[1]+"^";
shiftEndTimeArray+=shiftNameConcate[2]+"^";
shiftFullNameArray+=shiftNameConcate[3]+"^";
shiftTypeArray+=shiftNameConcate[4]+"^";
}


//Creating the Dynamic Calendar based on the the employees mapped 
//for the corresponding area type,areaCode and rosterType
//and the Current Year and Month Selected

//iterating through the total No of Employees mapped and getting the employee ID and employee name
for(int i=0 ; i <empList.size();i++)
{
	Entry objEntryEmp = new Entry();
	String empid="";
	String empName="";
	
	objEntryEmp=(Entry)empList.get(i);
	empid=objEntryEmp.getValue();
	empName	=objEntryEmp.getLabel();
	

	
	//button name==============empId+"@"+empName+"@"+shiftIdDefault+"@"+shiftStartTimeDefault+"@"+shiftEndTimeDefault+"@"+shiftFullNameDefault;			  		
	
	//setting the default values of shift button name and shift display name 
	
	String buttonName=empid+"@"+empName+"@"+shiftIdDefault+"@"+shiftStartTimeDefault+"@"+shiftEndTimeDefault+"@"+shiftFullNameDefault+"@"+shiftTypeDefault;
	shiftNameDefault="N A";

	
if(_rosterDtlVO!=null)
{	
	for(int x=0 ;x <_rosterDtlVO.length;x++)
		{
		String[] concatedShiftDetails=_rosterDtlVO[x].getShiftDesc().split("@");
		
		
		if(_rosterDtlVO[x].getEmpId().equals(empid))
			{
			buttonName=empid+"@"+empName+"@"+_rosterDtlVO[x].getShiftId()+"@"+concatedShiftDetails[1]+"@"+concatedShiftDetails[2]+"@"+concatedShiftDetails[3]+"@"+concatedShiftDetails[4];
			shiftNameDefault=concatedShiftDetails[0];
			break;
			}
		
		}
}	
	
System.out.println("name of buttton datewise emp roster --->"+buttonName);
		
		//String buttonId=Integer.toString(i+1);
		//System.out.println("buttonId--->"+buttonId); 
		

		
		
		buttonMap.put(buttonName, shiftNameDefault);
		
		
		
	
	
	
}	

//Creating the Hidden Fields for the array of Shift Types,Start Time,End Time
//for the corresponding Roster Types



	strCal.append("<input type=\"hidden\" id=\"shiftIdArray\"  value=\""+shiftIdArray+"\">");  
	strCal.append("<input type=\"hidden\" id=\"shiftNameArray\" value=\""+shiftNameArray+"\">"); 
	strCal.append("<input type=\"hidden\" id=\"shiftStartTimeArray\" value=\""+shiftStartTimeArray+"\">"); 
	strCal.append("<input type=\"hidden\" id=\"shiftEndTimeArray\" value=\""+shiftEndTimeArray+"\">");
	strCal.append("<input type=\"hidden\" id=\"shiftFullNameArray\" value=\""+shiftFullNameArray+"\">");
	strCal.append("<input type=\"hidden\" id=\"shiftTypeArray\" value=\""+shiftTypeArray+"\">");
	strCal.append("<input type=\"hidden\" id=\"maxNoOfEmployees\" value=\""+empList.size()+"\">"); 
	
	_fb.setCalendar(strCal.toString());
	
	
	

	WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_OF_EMPLOYEE_DETAILS_FOR_DATEWISE_DUTY_ROSTER, buttonMap);
	
	
}

public static RosterDtlVO[] getRosterDtlVOsToBeSaved(String[] arrayOfEmployees,HttpServletRequest _request,DateWiseEmployeeDutyRosterFB _fb)

{
	
	final long  MILLI_SEC_IN_EACH_DAY=1000*60*60*24;
	
	/*
	 * Earlier used code for saving data range wise
	 * when we used to enter each row for each day 
	 * but now we are entering start and end  date
	 * 
	 * 
	 * 
	 *
	 */
	
	int k=0	;
	String[] startDateArray=_fb.getStartDate().split("-");
	String[] endDateArray=_fb.getEndDate().split("-");
	
	
	
	
	//First Creating a startCalendar and endCalendar GregorianCalendar
	Calendar startCalendar =new GregorianCalendar();	
	Calendar endCalendar =new GregorianCalendar();
	Calendar[] tempCalendar=null;

	startCalendar.set(Integer.parseInt(startDateArray[2]),DateWiseEmployeeDutyRosterUTL.getMonthValue(startDateArray[1]) ,Integer.parseInt(startDateArray[0]));
	endCalendar.set(Integer.parseInt(endDateArray[2]),DateWiseEmployeeDutyRosterUTL.getMonthValue(endDateArray[1]) ,Integer.parseInt(endDateArray[0]));
	
	
	String fromDate=startDateArray[0]+"/"+Integer.toString(DateWiseEmployeeDutyRosterUTL.getMonthValue(startDateArray[1])+1)+"/"+startDateArray[2];
	String toDate=endDateArray[0]+"/"+Integer.toString(DateWiseEmployeeDutyRosterUTL.getMonthValue(endDateArray[1])+1)+"/"+endDateArray[2];
	
	
	long differenceOfDaysInMilliSec=(endCalendar.getTimeInMillis()-startCalendar.getTimeInMillis())/MILLI_SEC_IN_EACH_DAY;
	
	
	//First Computing the difference between the two  dates 
	
	int differenceOfDays=(int)differenceOfDaysInMilliSec;
	
	
	List listOfdays=new ArrayList();
	
	tempCalendar=new GregorianCalendar[differenceOfDays];
	
	listOfdays.add(startCalendar);
	
	for(int i=0; i < differenceOfDays;i++)
		{
		tempCalendar[i]=new GregorianCalendar();
		tempCalendar[i].set(Integer.parseInt(startDateArray[2]),DateWiseEmployeeDutyRosterUTL.getMonthValue(startDateArray[1]) ,Integer.parseInt(startDateArray[0])+i+1);
		 listOfdays.add(tempCalendar[i]);
		
		}
	
	
	
	RosterDtlVO[] _rosterDtlVO=new RosterDtlVO[arrayOfEmployees.length*listOfdays.size()];
	
	
//	RosterDtlVO[] _rosterDtlVO=new RosterDtlVO[arrayOfEmployees.length];
	
	for(int i=0 ;i< arrayOfEmployees.length ;i++)
	{
	
		for(int j=0; j < listOfdays.size() ; j++)
			
		{
			
	Calendar dateCalendar=new GregorianCalendar();
			 dateCalendar=(GregorianCalendar)listOfdays.get(j);
	
	if(k < arrayOfEmployees.length*listOfdays.size())	
		_rosterDtlVO[k]=new RosterDtlVO();
		       
	//	_rosterDtlVO[i]=new RosterDtlVO();
	
		String[] arrayOfVODetails=arrayOfEmployees[i].split("@");	
		String[] arrayOfRosterDetails=_fb.getRosterId().replace("^", "@").split("@");                
		
		
		int monthCode=dateCalendar.get(Calendar.MONTH)+1;
		
		String startDateTime=dateCalendar.get(Calendar.DAY_OF_MONTH)+"/"+monthCode+"/"+dateCalendar.get(Calendar.YEAR)+" "+arrayOfVODetails[3]+":00";
		String endDateTime=dateCalendar.get(Calendar.DAY_OF_MONTH)+"/"+monthCode+"/"+dateCalendar.get(Calendar.YEAR)+" "+arrayOfVODetails[4]+":00";		
	
	
		_rosterDtlVO[k].setGeneratedRosterId(_fb.getGeneratedRosterId());
		_rosterDtlVO[k].setRosterId(arrayOfRosterDetails[0]);
		_rosterDtlVO[k].setAreaCode(_fb.getAreaCode().split("@")[0]);
		_rosterDtlVO[k].setAreaTypeCode(arrayOfRosterDetails[1]);             
		_rosterDtlVO[k].setEmpId(arrayOfVODetails[0]);
		_rosterDtlVO[k].setShiftId(arrayOfVODetails[2]);
		_rosterDtlVO[k].setStartDateTime(startDateTime);
		_rosterDtlVO[k].setEndDateTime(endDateTime);
		_rosterDtlVO[k].setFromDate(fromDate);
		_rosterDtlVO[k].setToDate(toDate);
		
		k++;
		
		}
		
	}
	
	
	return _rosterDtlVO;

	
}




public static void  getListOfDateWiseWiseRoster(HttpServletRequest _request,DateWiseEmployeeDutyRosterFB _fb)

{
	
	List rosterList=new ArrayList();

	
			
		rosterList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_EMPLOYEES_WITH_ROSTER);


	
		
//Creating a string Buffer for Creating a New LocationWise Roster
///for the corresponding area type,areaCode and rosterType	
	StringBuilder strCal=new StringBuilder(); 
	
	strCal.append("<table width=\"100%\" border=\"1\" >");
	strCal.append("<tr>");
	strCal.append("<td  class=\"tdfonthead\" width=\"10%\"><div align=\"center\">Select</div></td>");
	strCal.append("<td  class=\"tdfonthead\" width=\"45%\"><div align=\"center\">From Date</div></td>");
	strCal.append("<td  class=\"tdfonthead\" width=\"45%\"><div align=\"center\">To Date</div></td>");
	strCal.append("</tr>");

//Creating the No. Of Shifts for the corresponding Roster Type
	if(rosterList.size()>=1)
	{
		Entry shiftEntry = new Entry();
		String startDate="";
		String endDate="";
		String concateDate="";
		String[] arrayOfDates=null;
			
		shiftEntry=(Entry)rosterList.get(0);
		
		concateDate=shiftEntry.getValue();
		arrayOfDates=concateDate.split("@");
		
		startDate=arrayOfDates[0];	
		endDate=arrayOfDates[1];
	   int isGenerated=Integer.parseInt(arrayOfDates[3]);
		
		String classType="";
		
	     if(isGenerated==1)
			classType="tdfont";
		
		strCal.append("<tr>");
		
		strCal.append("<td  width=\"10%\" class=\"tdfont\"><div align=\"center\">");
		
		strCal.append("<input type=\"radio\" name=\"distinctRoster\" value=\""+concateDate+"\" onClick='setGeneratedRosterId(this)'>");
		strCal.append("<input type=\"hidden\" name=\"startDateOld\"  value=\""+startDate+"\" >");
		strCal.append("<input type=\"hidden\" name=\"endDateOld\"  value=\""+endDate+"\" >");
		
		strCal.append("</div></td>");
		
		strCal.append("<td  width=\"45%\" class=\""+classType+"\"><div align=\"center\">"+startDate+"</div></td>");
		strCal.append("<td  width=\"45%\" class=\""+classType+"\"><div align=\"center\">"+endDate+"</div></td>");
		strCal.append("</tr>");
	}
/*for(int i=0 ; i < rosterList.size() ;i++)
{
	Entry shiftEntry = new Entry();
	String startDate="";
	String endDate="";
	String concateDate="";
	String[] arrayOfDates=null;
		
	shiftEntry=(Entry)rosterList.get(i);
	
	concateDate=shiftEntry.getValue();
	arrayOfDates=concateDate.split("@");
	
	startDate=arrayOfDates[0];	
	endDate=arrayOfDates[1];
   int isGenerated=Integer.parseInt(arrayOfDates[3]);
	
	String classType="";
	
     if(isGenerated==1)
		classType="tdfont";
	
	strCal.append("<tr>");
	
	strCal.append("<td  width=\"10%\" class=\"tdfont\"><div align=\"center\">");
	
	strCal.append("<input type=\"radio\" name=\"distinctRoster\" value=\""+concateDate+"\" onClick='setGeneratedRosterId(this)'>");
	strCal.append("<input type=\"hidden\" name=\"startDateOld\"  value=\""+startDate+"\" >");
	strCal.append("<input type=\"hidden\" name=\"endDateOld\"  value=\""+endDate+"\" >");
	
	strCal.append("</div></td>");
	
	strCal.append("<td  width=\"45%\" class=\""+classType+"\"><div align=\"center\">"+startDate+"</div></td>");
	strCal.append("<td  width=\"45%\" class=\""+classType+"\"><div align=\"center\">"+endDate+"</div></td>");
	strCal.append("</tr>");
}	*/


	





	
	
	
	strCal.append("</table>");
	
	
	strCal.append("<div align=\"center\" class=\"tdfont\">");
	
	strCal.append("<img class=\"button\" src='/../HIS/hisglobal/images/buttons/New.png' tabindex=\"1\" style=\"cursor: pointer\" onclick=\"getNewRoster()\" onkeypress=\"if(event.keyCode==13) getNewRoster()\">");
	strCal.append("<img class=\"button\" src='/../HIS/hisglobal/images/buttons/btn-mo.png' tabindex=\"1\" style=\"cursor: pointer\" onclick=\"modifyRoster()\" onkeypress=\"if(event.keyCode==13) modifyRoster()\">");
	strCal.append("<img class=\"button\" src='/../HIS/hisglobal/images/buttons/btn-generate.png' tabindex=\"1\" style=\"cursor: pointer\" onclick=\"generateRoster()\" onkeypress=\"if(event.keyCode==13) generateRoster()\">");
	strCal.append("<img class=\"button\" src='/../HIS/hisglobal/images/buttons/btn-view.png' tabindex=\"1\" style=\"cursor: pointer\" onclick=\"showReport()\" onkeypress=\"if(event.keyCode==13) showReport()\">");

	
	strCal.append("</div>");
	
	
	_fb.setDistinctRosterList(strCal.toString());
	
	
	
	}

// On Add Page saving Values into Database
public static boolean saveAndModifyEmpDutyRosterDateWise(DateWiseEmployeeDutyRosterFB _fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	boolean hasFlag = true;

	try
	{
		UserVO userVO = getUserVO(_request);
		String[] arrayOfEmployees=_fb.getConcatedValueToBeSaved().split("#");
		
		RosterDtlVO[] _rosterDtlVO=DateWiseEmployeeDutyRosterUTL.getRosterDtlVOsToBeSaved(arrayOfEmployees,_request,_fb);		
		
		
		
		DateWiseEmployeeDutyRosterDATA.saveAndModifyEmpDutyRosterDateWise(_rosterDtlVO, userVO);
		
		objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
	}
	catch(HisDuplicateRecordException e){	   		   	
		System.out.println("Inside HisRecordNotFoundException");
		objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		hasFlag = false;
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
		objStatus.add(Status.ERROR_DA, "","Data Access Error");
		hasFlag = false;
	}
	catch (HisApplicationExecutionException e)
	{
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		hasFlag = false;
	}
	catch (HisException e)
	{
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "", "Error");
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



		//For generating the datewise emp duty roster
public static boolean generateEmpDutyRosterDateWise(DateWiseEmployeeDutyRosterFB _fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	boolean hasFlag = true;

	try
	{
		UserVO userVO = getUserVO(_request);
		
		
		
	RosterGenerationDtlVO _rosterGenerationDtlVO=new RosterGenerationDtlVO();
		
	
			
	
	String[] arrayOfRosterDetails=_fb.getRosterId().replace("^", "@").split("@");     
	
	String _rosterId=arrayOfRosterDetails[0];
	String _areaTypeCode=arrayOfRosterDetails[1];
	String _areaCode=_fb.getAreaCode().split("@")[0];
	
	
	_rosterGenerationDtlVO.setRosterId(_rosterId);
	_rosterGenerationDtlVO.setAreaCode(_areaCode);
	_rosterGenerationDtlVO.setAreaTypeCode(_areaTypeCode);
	_rosterGenerationDtlVO.setStartDate(_fb.getStartDateOld());
	_rosterGenerationDtlVO.setEndDate(_fb.getEndDateOld());
	_rosterGenerationDtlVO.setRosterStatus(DutyRosterConfig.ROSTER_STATUS_IS_GENERATED);
	_rosterGenerationDtlVO.setGeneratedRosterId(_fb.getGeneratedRosterId());
	
	
	DateWiseEmployeeDutyRosterDATA.generateEmpDutyRosterDateWise(_rosterGenerationDtlVO, userVO);
		
			
			
		objStatus.add(Status.INPROCESS, "Roster Generated Successfully", "");
	}
	catch(HisDuplicateRecordException e){	   		   	
  		 e.printStackTrace(); 
  		objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		hasFlag = false;	  		
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
		objStatus.add(Status.ERROR_DA, "","Data Access Error");
		hasFlag = false;
	}
	catch (HisApplicationExecutionException e)
	{
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		hasFlag = false;
	}
	catch (HisException e)
	{
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "", "Error");
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

}
