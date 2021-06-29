
package dutyroster.reports.controller.utl;


import inpatient.InpatientConfig;
import inpatient.reports.controller.data.SpecialityWiseOperationDATA;
import inpatient.reports.controller.fb.SpecialityWiseOperationFB;

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
import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.DutyRosterShiftMasterVO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest; 

import dutyroster.DutyRosterConfig;  
import dutyroster.masters.controller.fb.RosterPrintMstFB;
import dutyroster.reports.controller.data.AreaWiseEmpRosterReportDATA;
import dutyroster.reports.controller.data.EmpWiseEmpRosterReportDATA; 
import dutyroster.reports.controller.fb.EmpWiseEmpRosterReportFB;
import dutyroster.transaction.controller.utl.EmployeeDutyRosterUTLFunctions;

public class EmpWiseEmpRosterReportUTL extends ControllerUTIL
{

	
// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_REGISTRATION_DTL and  GBLT_DESIGNATION_MST

	public static boolean getHospitalList(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb)
	{
		Status objStatus = new Status();
		
		List designationList=new ArrayList();
		
		String currentYear="";
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
			
			
			
			EmpWiseEmpRosterReportUTL.getYearList(_request,_fb);
			EmpWiseEmpRosterReportUTL.getMonthsList(_request,_fb);
			
			_request.getSession().removeAttribute(DutyRosterConfig. LIST_OF_ALL_DESIGNATIONS);
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_EMP_BASED_ON_DESIGNATION);
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_AREA_BASED_ON_EMPLOYEE);
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTERS_BASED_ON_AREA_EMPLOYEE);
			
			
			
			Map map1=(HashMap)AreaWiseEmpRosterReportDATA.getAllHospitalEssentials(getUserVO(_request));
			WebUTIL.setMapInSession(map1, _request);
			
			EmpWiseEmpRosterReportUTL.getDesignationList(_request,_fb);
								
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
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

	
	public static boolean getDesignationList(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb)
	{
		Status objStatus = new Status();
		
		List designationList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO uVO = getUserVO(_request);
			UserVO _userVO = new UserVO();
			HelperMethods.populate(_userVO, uVO);
			//_userVO.setHospitalCode(_fb.getHospitalCode());
		
			
			setSysdate(_request);
			
			
			
			EmpWiseEmpRosterReportUTL.getYearList(_request,_fb);
			EmpWiseEmpRosterReportUTL.getMonthsList(_request,_fb);
			
			
			
			
			designationList=EmpWiseEmpRosterReportDATA.getDesignationList(_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ALL_DESIGNATIONS, designationList);
			
						
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
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
	
	
	// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_REGISTRATION_DTL and  GBLT_DESIGNATION_MST

	public static boolean getEmpListBasedOnDesignation(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb)
	{
		Status objStatus = new Status();
		
		List empList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO uVO = getUserVO(_request);
			UserVO _userVO = new UserVO();
			HelperMethods.populate(_userVO, uVO);
			//_userVO.setHospitalCode(_fb.getHospitalCode());
		
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_EMP_BASED_ON_DESIGNATION);
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_AREA_BASED_ON_EMPLOYEE);
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTERS_BASED_ON_AREA_EMPLOYEE);
			
			_fb.setEmpId(null);
			_fb.setAreaCode(null);
			_fb.setRosterId(null);

			
			empList=EmpWiseEmpRosterReportDATA.getEmpListBasedOnDesignation(_fb.getDesignationId(),_userVO);			
			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_EMP_BASED_ON_DESIGNATION, empList);
			
			
			
			
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_EMP_BASED_ON_DESIGNATION, empList);
			
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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
		
		public static boolean getDutyAreaBasedOnEmployee(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb)
		{
			Status objStatus = new Status();
			
			
			List dutyAreaList=new ArrayList();
			
			
			
			try
			{
				
				
				UserVO uVO = getUserVO(_request);
				UserVO _userVO = new UserVO();
				HelperMethods.populate(_userVO, uVO);
				//_userVO.setHospitalCode(_fb.getHospitalCode());
			
				_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_AREA_BASED_ON_EMPLOYEE);
				_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTERS_BASED_ON_AREA_EMPLOYEE);
				
				_fb.setAreaCode(null);
				_fb.setRosterId(null);
			
	 
				

				
			
				
				dutyAreaList=EmpWiseEmpRosterReportDATA.getDutyAreaBasedOnEmployee( _fb.getEmpId(),_userVO);
				
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_AREA_BASED_ON_EMPLOYEE, dutyAreaList);
			
				
				/*if(dutyAreaList.size()==1)
				{
					Entry obj=(Entry)dutyAreaList.get(0);
						String areaId=obj.getValue();
					_fb.setAreaCode(areaId);
				
				}*/
				/*if(desigList.size()==1)
				{
					Entry obj=(Entry)desigList.get(0);
					String desigId=obj.getValue();
					_fb.setDesignationId(desigId);
				}
						
				if(dutyAreaList.size()==1 && desigList.size()==1)
					{
					EmpWiseEmpRosterReportUTL.getMonthWiseEmpRosterReportBasedOnDutyAreaAndDesignation(_request, _fb);
					}
				*/
				
				
				
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
				
				//WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
				//WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE, new ArrayList());
				
				
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				//WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
				//WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE, new ArrayList());
				
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
		


		// Getting the Duty Area on the Basis of Duty Area Types
//			 from the Package PKG_DUTY_ROSTER using the Procedure Proc_get_duty_area
			
			public static boolean getRosterListBasedOnAreaEmployee(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb)
			{
				Status objStatus = new Status();
				
				
				List rosterList=new ArrayList();
				
				
				
				try
				{
					
					
					UserVO uVO = getUserVO(_request);
					UserVO _userVO = new UserVO();
					HelperMethods.populate(_userVO, uVO);
					//_userVO.setHospitalCode(_fb.getHospitalCode());
				
					_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTERS_BASED_ON_AREA_EMPLOYEE);
					
					_fb.setRosterId(null);
					
							
				
					
					rosterList=EmpWiseEmpRosterReportDATA.getRosterListBasedOnAreaEmployee( _fb.getEmpId(),_fb.getAreaCode(),_userVO);
					
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTERS_BASED_ON_AREA_EMPLOYEE, rosterList);
				
					
					/*if(dutyAreaList.size()==1)
					{
						Entry obj=(Entry)dutyAreaList.get(0);
							String areaId=obj.getValue();
						_fb.setAreaCode(areaId);
					
					}*/
					/*if(desigList.size()==1)
					{
						Entry obj=(Entry)desigList.get(0);
						String desigId=obj.getValue();
						_fb.setDesignationId(desigId);
					}
							
					if(dutyAreaList.size()==1 && desigList.size()==1)
						{
						EmpWiseEmpRosterReportUTL.getMonthWiseEmpRosterReportBasedOnDutyAreaAndDesignation(_request, _fb);
						}
					*/
					
					
					
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

			
			public static boolean getEmpWiseRosterReport(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb)
			{
				Status objStatus = new Status();
				
				Map rosterPrintMap=new LinkedHashMap();
				
				
				Map empRosterMap=new HashMap();
				Map empDutyAreaMap=new HashMap();
				RosterDtlVO[] _rosterDtlVO=null;
				DutyRosterPrintPropertiesVO[]  _rosterPrintVO=null;
				RosterGenerationDtlVO[] _rosterGenerationDtlVO=null;
				HospitalMstVO _hospitalVO = getHospitalVO(_request);
				//Map of days the roster is generated
				Map rosterGenerationMap=new LinkedHashMap();
				
				//days list for which we want to print
				List rosterPrintDaysList=new ArrayList();
				
				Map _holidayMap=new LinkedHashMap();
				
				try
				{
					
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS, new HashMap());
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS, rosterPrintMap);
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.HOSPITAL_MST_VO , _hospitalVO);
					//setting the designation id
					_fb.setDesignationId("ALL");
					
					UserVO uVO = getUserVO(_request);
					UserVO _userVO = new UserVO();
					HelperMethods.populate(_userVO, uVO);
					//_userVO.setHospitalCode(_fb.getHospitalCode());
				
					
					String splitValues[]=_fb.getRosterId().split("@");
					
					String _rosterId=splitValues[0];
					//String _desigId=_fb.getDesignationId();
					String _areaTypeCode=splitValues[1];
					String _areaCode=_fb.getAreaCode();
					String _month=_fb.getMonth();
					String _year=_fb.getYear();
					String _empId=_fb.getEmpId();
					
//Getting the employees whose Roster has been prepared on the basis of  year,_rosterId
//month,rosterId,areaTypeCode and areaCode		
					
					
					empRosterMap=EmpWiseEmpRosterReportDATA.getEmpWiseRosterReport(_year, _month,_rosterId, _areaTypeCode, _areaCode,_empId, _userVO);
					
					
					
					//getting the map of gazetted holidays from the emproster map 
					_holidayMap=(Map)empRosterMap.get(DutyRosterConfig.MAP_OF_MONTHLY_GAZETTED_HOLIDAYS);
				
					
					

//getting the total vo's for the roster generated days					
					_rosterGenerationDtlVO=(RosterGenerationDtlVO[])empRosterMap.get(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS);

//getting the list of roster generated days	in case of 				
					if(_rosterGenerationDtlVO!=null && _rosterGenerationDtlVO.length >0 )
						rosterGenerationMap=EmpWiseEmpRosterReportUTL.getMapofRosterGeneratedDays(_rosterGenerationDtlVO);

					
//Getting the list of days for which we want printing					
					rosterPrintDaysList=EmpWiseEmpRosterReportUTL.getListofRosterPrintingDays(_fb);
					
					
					_rosterDtlVO=(RosterDtlVO[])empRosterMap.get(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);
					_rosterPrintVO=(DutyRosterPrintPropertiesVO[])empRosterMap.get(DutyRosterConfig.VO_OF_ROSTER_PRINT_DETAILS);
					
			if(_rosterPrintVO!=null)		
					rosterPrintMap=EmpWiseEmpRosterReportUTL.getPrintingMapFromArrayOfVO(_rosterPrintVO);
					
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS, rosterPrintMap);
					
//if we get employees whose roster has been prepared for the above specifiaction
//then only we generate a report
					
				if(_rosterDtlVO!=null )
				{
													

					WebUTIL.setMapInSession(empRosterMap, _request);

					//setting the Roster dtl map in session
					
			if(!_fb.getAreaCode().equals("ALL"))
			    	EmpWiseEmpRosterReportUTL.setMonthWiseEmpRosterReport(_request,_fb,rosterGenerationMap,rosterPrintDaysList,_holidayMap);
				else
			if(_fb.getAreaCode().equals("ALL"))	
					EmpWiseEmpRosterReportUTL.setMonthWiseEmpRosterReportForAllMappedAreas(_request, _fb, rosterGenerationMap, rosterPrintDaysList);
					
					
				}	
				
					
				//setting the roster name area name and month name in the form bean
				EmpWiseEmpRosterReportUTL.setRosterNameAreaNameAndMonth(_request, _fb);
				
				
					
					objStatus.add(Status.NEW);
				}
				catch (HisRecordNotFoundException e)
				{
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS, rosterPrintMap);
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS, new HashMap());
					e.printStackTrace();
					
					objStatus.add(Status.UNSUCESSFULL, "", "");
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

			
	
	public static void  getYearList(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb)
	
	{


		List yearList=new ArrayList();
		Calendar calendar1 =new GregorianCalendar();	
		Date trialTime=new Date();
		
		calendar1.setTime(trialTime);
		
		System.out.println("monthAnkur------"+calendar1.get(Calendar.MONTH));
		
		int arrayOfYears[]=null;
		                 
		if(calendar1.get(Calendar.MONTH)==11)
				 arrayOfYears= new int [DutyRosterConfig.NO_OF_YEARS_TO_BE_SHOWN_IN_EMPLOYEE_DUTY_ROSTER];
		else
			 	arrayOfYears= new int [2];
		
		
		
		
		
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

	
public static void  getMonthsList(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb)
	
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





public static void  setMonthWiseEmpRosterReport(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb,Map rosterGenerationMap,List rosterPrintDaysList,Map _holidayMap)
{

	Map empRosterMap=new LinkedHashMap();
	

	RosterDtlVO[] _rosterDtlVO=null;
	List shiftList=new ArrayList();
	List dayOffShiftList=new ArrayList();
	List rosterGenerationList=new ArrayList();

	//FOR GETTING THE HEADER LIST
	List headerList=new ArrayList();
	

//getting the emmployees  and ShiftType List list  from the session

//empMappedList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE);
dayOffShiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS);
	_rosterDtlVO=(RosterDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);
 shiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
//  dayOffShiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS);	


 if(_rosterDtlVO!=null)
	 rosterGenerationList=(List)rosterGenerationMap.get(_rosterDtlVO[0].getAreaCode());
 
 String sunGzMapKey="SU/GZ@SU/GZ";
 
 
//First of all adding all types of Mapped Shifts with the Roster 
//into the Map 
 
 
/*Code used in case we  want to show all the emp mapped
 * 
 * 
 for(int i=0; i < empMappedList.size() ; i ++)
 {
	 
	 Entry objEmp=(Entry)empMappedList.get(i);
		
		String empName=objEmp.getLabel();					
		String empId=objEmp.getValue();
	 
	 
	 
	 

	 	String empMapKey=empId+"@"+empName;
	 	
	 	
	 	String daysList="--";
	 	 Map shiftMap=new LinkedHashMap();
	 	
		 
	
		 Iterator itr=shiftList.iterator();
			
			while(itr.hasNext())
			{
				Entry obj=(Entry)itr.next();
				
				String[] concateArray=obj.getLabel().split("@");					
				String shiftMapKey=obj.getValue()+"@"+concateArray[3];
				
				//System.out.println("shiftMapKey---------"+shiftMapKey);
				
				shiftMap.put(shiftMapKey, daysList);
				empRosterMap.put(empMapKey, shiftMap);						
				
			}
		 
			
Iterator itrDayOff=dayOffShiftList.iterator();
			
			while(itrDayOff.hasNext())
			{
				Entry obj=(Entry)itrDayOff.next();
				
				String[] concateArray=obj.getValue().replace("^", "@").split("@");					
				String shiftMapKey=concateArray[0]+"@"+concateArray[3];
				
				//System.out.println("shiftMapKey---------"+shiftMapKey);
				
				shiftMap.put(shiftMapKey, daysList);
				empRosterMap.put(empMapKey, shiftMap);						
				
			}
			
			//for sunday/Gazetetd holidays
			
			
			
			//System.out.println("shiftMapKey---------"+shiftMapKey);
			headerList.add(sunGzMapKey);
			
			shiftMap.put(sunGzMapKey, daysList);
			empRosterMap.put(empMapKey, shiftMap);	
 }	 */
 
	
	 
 for(int i=0; i < _rosterDtlVO.length ; i ++)
 {

	 	String empMapKey=_rosterDtlVO[i].getEmpId()+"@"+_rosterDtlVO[i].getEmpName();
	 	
	 	
	 	String daysList="--";
	 	 Map shiftMap=new LinkedHashMap();
	 	
		 
	
		 Iterator itr=shiftList.iterator();
			
			while(itr.hasNext())
			{
				Entry obj=(Entry)itr.next();
				
				String[] concateArray=obj.getLabel().split("@");					
				String shiftMapKey=obj.getValue()+"@"+concateArray[3];
				
				//System.out.println("shiftMapKey---------"+shiftMapKey);
				
				shiftMap.put(shiftMapKey, daysList);
			//	empRosterMap.put(empMapKey, shiftMap);						
				
				String shiftDisplay=concateArray[3]+"("+concateArray[1]+" To "+concateArray[2]+")";
				
				if(!headerList.contains(shiftDisplay))
					headerList.add(shiftDisplay);
				
			}
		 
			
Iterator itrDayOff=dayOffShiftList.iterator();
			
			while(itrDayOff.hasNext())
			{
				Entry obj=(Entry)itrDayOff.next();
				
				String[] concateArray=obj.getValue().replace("^", "@").split("@");					
				String shiftMapKey=concateArray[0]+"@"+concateArray[3];
				
				//System.out.println("shiftMapKey---------"+shiftMapKey);
				
				shiftMap.put(shiftMapKey, daysList);
			//	empRosterMap.put(empMapKey, shiftMap);						
				
				
				if(i==0)
					headerList.add(concateArray[3]);
			}
			
			//for sunday/Gazetetd holidays
			
	if(_fb.getPrintingFormat().equals("S"))
			{
			
			//System.out.println("shiftMapKey---------"+shiftMapKey);
			headerList.add(sunGzMapKey);
			
			shiftMap.put(sunGzMapKey, daysList);
			
			}
	empRosterMap.put(empMapKey, shiftMap);
	
 }	 

 
 
 
 
//Now adding the days into the shift map

for(int i=0; i < _rosterDtlVO.length ; i ++)
{

	
	// Creating a  GregorianCalendar based on the Year and Month and Day Selected 
	Calendar dailyCalendar =new GregorianCalendar();	
	dailyCalendar.set(Integer.parseInt(_fb.getYear()),Integer.parseInt(_fb.getMonth())-1, Integer.parseInt(_rosterDtlVO[i].getStartDateTime()));
	
	int dayCode=dailyCalendar.get(Calendar.DAY_OF_WEEK);
	
	System.out.println("day code--------"+dayCode);
	
	boolean holidayFlag=false;
	
	//Now checking wheteher it si sunday/gazetted 
	
	if(_fb.getPrintingFormat().equals("S")  && (dayCode==Calendar.SUNDAY || _holidayMap.containsKey(_rosterDtlVO[i].getStartDateTime()))) 
			holidayFlag=true;
	
	
	
Map shiftMap=new LinkedHashMap();
Map dateMap=new LinkedHashMap();

	
	String empMapKey=_rosterDtlVO[i].getEmpId()+"@"+_rosterDtlVO[i].getEmpName();
	String shiftMapKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName();
	
	String daysList="";
	
	if(empRosterMap.containsKey(empMapKey))
	{
		
		shiftMap=(Map)empRosterMap.get(empMapKey);
		
		//i.e. shiftmap contains shift key and it is not a holiday
		
		if(shiftMap.containsKey(shiftMapKey) && holidayFlag==false)
	{
			String oldDaysList="";
			
			oldDaysList=(String)shiftMap.get(shiftMapKey);
			
			int day=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());
			
			
//checking whether the day is contained in the 
//rosterGenerationList	and  rosterPrintDaysList
			
if(rosterGenerationList!=null && rosterPrintDaysList!=null && rosterGenerationList.contains(day) && rosterPrintDaysList.contains(day))
		   {
			if(oldDaysList.equals("--"))
					daysList=Integer.toString(day);
					else
					daysList=oldDaysList+", "+Integer.toString(day);	
			
			
			shiftMap.put(shiftMapKey, daysList);
			empRosterMap.put(empMapKey, shiftMap);
						
			}//inner if closed 
	}///if closed
		
		
		/*	if(shiftMap.containsKey(sunGzMapKey) && holidayFlag==true)
			{
					String oldDaysList="";
					
					oldDaysList=(String)shiftMap.get(sunGzMapKey);
					
					int day=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());
					
					
		//checking whether the day is contained in the 
		//rosterGenerationList	and  rosterPrintDaysList
					
		if(rosterGenerationList!=null && rosterPrintDaysList!=null && rosterGenerationList.contains(day) && rosterPrintDaysList.contains(day))
				   {
					if(oldDaysList.equals("--"))
							daysList=Integer.toString(day);
							else
							daysList=oldDaysList+", "+Integer.toString(day);	
					
					
					shiftMap.put(sunGzMapKey, daysList);
					empRosterMap.put(empMapKey, shiftMap);
								
					}//inner if closed 
			}///if closed
*/		
		
	
		
		
		
	}
	




}


WebUTIL.setAttributeInSession(_request, DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT, headerList);

WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS, empRosterMap);

	 
	
	
}



public static void  setMonthWiseEmpRosterReportForAllMappedAreas(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb,Map rosterGenerationMap,List rosterPrintDaysList)

{
	Map areaRosterMap=new HashMap();
	
	
	

	RosterDtlVO[] _rosterDtlVO=null;
	List shiftList=new ArrayList();
	List dayOffShiftList=new ArrayList();
	
	List headerList=new ArrayList();


//getting the emmployees  and ShiftType List list  from the session

//empMappedList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE);
dayOffShiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS);
	_rosterDtlVO=(RosterDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);
 shiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
//  dayOffShiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS);	


 
 
 
//First of all adding all types of Mapped Shifts with the Roster 
//into the Map 

 for(int i=0; i < _rosterDtlVO.length ; i ++)
 {

	 Map empRosterMap=new HashMap();
	 
		String areaMapKey=_rosterDtlVO[i].getAreaCode()+"@"+_rosterDtlVO[i].getAreaName();
		
	 	String empMapKey=_rosterDtlVO[i].getEmpId()+"@"+_rosterDtlVO[i].getEmpName();
	 	
	 	//in case the  areaRosterMap contains key areaMapKey
	 	//then we get the emp map
	 	
	 	if(areaRosterMap.containsKey(areaMapKey))
	 		empRosterMap=(Map)areaRosterMap.get(areaMapKey);
	 		
	 	
	 	String daysList="--";
	 	 Map shiftMap=new HashMap();
	 	
		 
	
		 Iterator itr=shiftList.iterator();
			
			while(itr.hasNext())
			{
				Entry obj=(Entry)itr.next();
				
				String[] concateArray=obj.getLabel().split("@");					
				String shiftMapKey=obj.getValue()+"@"+concateArray[3];
				
				
				//System.out.println("shiftMapKey---------"+shiftMapKey);
				
				shiftMap.put(shiftMapKey, daysList);
				empRosterMap.put(empMapKey, shiftMap);						
				areaRosterMap.put(areaMapKey,empRosterMap);
				
				
				//for making the header 
			if(i==0)
				headerList.add(concateArray[3]);
			}
		 
			
Iterator itrDayOff=dayOffShiftList.iterator();
			
			while(itrDayOff.hasNext())
			{
				Entry obj=(Entry)itrDayOff.next();
				
				String[] concateArray=obj.getValue().replace("^", "@").split("@");					
				String shiftMapKey=concateArray[0]+"@"+concateArray[3];
				
				//System.out.println("shiftMapKey---------"+shiftMapKey);
				
				shiftMap.put(shiftMapKey, daysList);
				empRosterMap.put(empMapKey, shiftMap);						
				areaRosterMap.put(areaMapKey,empRosterMap);
				
				//for making the header 
			if(i==0)	
				headerList.add(concateArray[3]);
			}
			
 }	 
	
	 

 
 
 
 
//Now adding the days into the shift map

for(int i=0; i < _rosterDtlVO.length ; i ++)
{

Map shiftMap=new HashMap();
Map dateMap=new HashMap();

	
	String empMapKey=_rosterDtlVO[i].getEmpId()+"@"+_rosterDtlVO[i].getEmpName();
	String shiftMapKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName();
	
	String daysList="";
	
	
	 Map empRosterMap=new HashMap();
	 
		String areaMapKey=_rosterDtlVO[i].getAreaCode()+"@"+_rosterDtlVO[i].getAreaName();
	
		
	
	if(areaRosterMap.containsKey(areaMapKey))
 		empRosterMap=(Map)areaRosterMap.get(areaMapKey);
	
	
	List rosterGenerationList=(List)rosterGenerationMap.get(_rosterDtlVO[i].getAreaCode());
	
	
	
	
	if(empRosterMap.containsKey(empMapKey))
	{
		
		shiftMap=(Map)empRosterMap.get(empMapKey);
		
		if(shiftMap.containsKey(shiftMapKey))
	{
			String oldDaysList="";
			
			oldDaysList=(String)shiftMap.get(shiftMapKey);
			
			int day=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());
			
			
//getting the dynamic roster genration list depending upon the area code
			

			
//checking whether the day is contained in the 
//rosterGenerationList	and  rosterPrintDaysList
			
if(rosterGenerationList!=null && rosterPrintDaysList!=null && rosterGenerationList.contains(day) && rosterPrintDaysList.contains(day))
		   {
			if(oldDaysList.equals("--"))
					daysList=Integer.toString(day);
					else
					daysList=oldDaysList+", "+Integer.toString(day);	
			
			
			shiftMap.put(shiftMapKey, daysList);
			empRosterMap.put(empMapKey, shiftMap);
						
			}//inner if closed 
	}///if closed
	else
			{
			
		
			int day=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());


//checking whether the day is contained in the 
//rosterGenerationList	and  rosterPrintDaysList			

			if(rosterGenerationList!=null && rosterPrintDaysList!=null && rosterGenerationList.contains(day) && rosterPrintDaysList.contains(day))
				{
				daysList=Integer.toString(day);	
				shiftMap.put(shiftMapKey, daysList);
			    empRosterMap.put(empMapKey, shiftMap);
				}	
			
			}
		
		
		
	}
	




}


WebUTIL.setAttributeInSession(_request, DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT, headerList);

WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS, areaRosterMap);

	 
	
}


//Making the Map from the array of Vos 
public static Map getPrintingMapFromArrayOfVO(DutyRosterPrintPropertiesVO[] _rosterPrintVO)
{
	Status objStatus = new Status();
	boolean hasFlag = true;
	Map rosterPrintMap=new LinkedHashMap();

	try
	{
			for(int i=0 ; i < _rosterPrintVO.length ; i++)
			{
				Map rosterOrderMap=new LinkedHashMap();
				
		String rosterPrintKey=_rosterPrintVO[i].getPropertyType()+"@"+_rosterPrintVO[i].getAlign();
						
				if(rosterPrintMap.containsKey(rosterPrintKey))
				{
					rosterOrderMap=(LinkedHashMap)rosterPrintMap.get(rosterPrintKey);
					rosterOrderMap.put(_rosterPrintVO[i].getDisplayOrder(), _rosterPrintVO[i].getDisplayValue());
					rosterPrintMap.put(rosterPrintKey, rosterOrderMap);
					
					
					
					
				}else
				{
										
						rosterOrderMap.put(_rosterPrintVO[i].getDisplayOrder(), _rosterPrintVO[i].getDisplayValue());
						rosterPrintMap.put(rosterPrintKey, rosterOrderMap);
						
											
				}	
				
				
				
				
				
				
			}
		
		
	}
	
	catch (HisException e)
	{
		e.printStackTrace();
	}
	finally
	{
		
	}
	
	return rosterPrintMap;
}



//Making the Map from the array of Vos 
public static void setRosterNameAreaNameAndMonth(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb)
{
	String[] arrayOfMonths={"January","February","March","April","May","June","July","August","September","October","November","December"};

	String rosterName="";
	String areaName="";
	String monthName="";
	
	//Setting the roster Name
	
	List dutyRosterList=new ArrayList();
	dutyRosterList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_ROSTERS_BASED_ON_AREA_EMPLOYEE);
	
	
	Iterator itr=dutyRosterList.iterator();
	
	while(itr.hasNext())
	{
		Entry obj=(Entry)itr.next();
		
		if(obj.getValue().equals(_fb.getRosterId()))
			rosterName=obj.getLabel();
		
	}
	
	
//Setting the Area Name
	
	List dutyAreaList=new ArrayList();
	dutyAreaList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_AREA_BASED_ON_EMPLOYEE);
	
	
	Iterator itr2=dutyAreaList.iterator();
	
	while(itr2.hasNext())
	{
		Entry obj2=(Entry)itr2.next();
		
		if(obj2.getValue().equals(_fb.getAreaCode()))
			areaName=obj2.getLabel();
		
	}
	
	
	
	
	//setting the month name
	
	int monthId=Integer.parseInt(_fb.getMonth())-1;

	
	monthName=arrayOfMonths[monthId];
	
	String headerMsg="";
	
//in case we have all the mapped areas then msg will be different 
	
if(_fb.getAreaCode().equals("ALL"))
	headerMsg="Duty Roster of "+rosterName+" ,For All Mapped Duty Areas,  (For the Month of "+monthName+", "+_fb.getYear()+")";
else
	headerMsg="Duty Roster of "+rosterName+" ,Duty Area "+areaName+" " +"(For the Month of "+monthName+", "+_fb.getYear()+")";
	
	

	_fb.setHeaderMsg(headerMsg);
	
}


/**
 * 	getting the complete monthly roster of all the mapped employees 
 * 	excluding this roster Type and area code
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static Map getMapofRosterGeneratedDays(RosterGenerationDtlVO[] _rosterGenerationDtlVO)

{
	
	Map rosterGenerationMap=new LinkedHashMap();
	
	for(int i=0; i < _rosterGenerationDtlVO.length ; i++ )
	{
		List rosterGenList=new ArrayList();
		
		String areaKey=_rosterGenerationDtlVO[i].getAreaCode();
		
		
	//in case the 	rosterGenerationMap does not contains the ara key ,hence fresh list will be added 
		
		if(!rosterGenerationMap.containsKey(areaKey))
			{
			
				
			}else
	//in case it contains the list old list will be fetched 			
		if(rosterGenerationMap.containsKey(areaKey))		
			{
			rosterGenList=(List)rosterGenerationMap.get(areaKey);
				
			}	
	
	
		int startDate=Integer.parseInt(_rosterGenerationDtlVO[i].getStartDate());
		int endDate=Integer.parseInt(_rosterGenerationDtlVO[i].getEndDate());
				
			for(int j=startDate; j <= endDate ; j++)
				rosterGenList.add(j);
				rosterGenerationMap.put(areaKey, rosterGenList);
		
			
	}

	
	
	return rosterGenerationMap;
}



/**
 * 	getting the complete monthly roster of all the mapped employees 
 * 	excluding this roster Type and area code
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static List getListofRosterPrintingDays(EmpWiseEmpRosterReportFB _fb)
	{
	
	List rosterPrintList=new ArrayList();
			
	
	if(!_fb.getStartDate().equals("") && !_fb.getEndDate().equals(""))
	{
	String[] startDateArray=_fb.getStartDate().split("-");
	String[] endDateArray=_fb.getEndDate().split("-");
	
		int startDate=Integer.parseInt(startDateArray[0]);
		int endDate=Integer.parseInt(endDateArray[0]);
		
		
		for(int j=startDate; j <= endDate ; j++)
			rosterPrintList.add(j);
	}
else
	{
	// Creating a  GregorianCalendar based on the Year and Month Selected
	Calendar calendarSelected =new GregorianCalendar();	
	calendarSelected.set(Integer.parseInt(_fb.getYear()),Integer.parseInt(_fb.getMonth())-1, 1);
	

	int maxDaysofMonth=calendarSelected.getActualMaximum(Calendar.DAY_OF_MONTH);
			
	for(int j=1; j <= maxDaysofMonth ; j++)
			rosterPrintList.add(j);
	
	}

		
	return rosterPrintList;
	}
public static boolean getDesignationBasedOnHospital(HttpServletRequest _request,EmpWiseEmpRosterReportFB _fb)
{
	Status objStatus = new Status();
	
	
	List dutyAreaList=new ArrayList();
	String currentYear="";
	
	
	try
	{
		
		
		UserVO uVO = getUserVO(_request);
		UserVO _userVO = new UserVO();
		HelperMethods.populate(_userVO, uVO);
		_userVO.setHospitalCode(_fb.getHospitalCode());
	
		_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ALL_DESIGNATIONS);
		_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_EMP_BASED_ON_DESIGNATION);
		_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_AREA_BASED_ON_EMPLOYEE);
		_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTERS_BASED_ON_AREA_EMPLOYEE);
		
		_fb.setDesignationId(null);
		_fb.setEmpId(null);
		_fb.setAreaCode(null);
		_fb.setRosterId(null);

		
		dutyAreaList=EmpWiseEmpRosterReportDATA.getDesignationList(_userVO);
		
		//dutyAreaList=EmpWiseEmpRosterReportDATA.getDutyAreaBasedOnEmployee( _fb.getEmpId(),_userVO);
		//EmpWiseEmpRosterReportUTL.getDesignationList(_request,_fb);
	WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ALL_DESIGNATIONS, dutyAreaList);
	
		
		/*if(dutyAreaList.size()==1)
		{
			Entry obj=(Entry)dutyAreaList.get(0);
				String areaId=obj.getValue();
			_fb.setAreaCode(areaId);
		
		}*/
		/*if(desigList.size()==1)
		{
			Entry obj=(Entry)desigList.get(0);
			String desigId=obj.getValue();
			_fb.setDesignationId(desigId);
		}
				
		if(dutyAreaList.size()==1 && desigList.size()==1)
			{
			EmpWiseEmpRosterReportUTL.getMonthWiseEmpRosterReportBasedOnDutyAreaAndDesignation(_request, _fb);
			}
		*/
		
		
		
		objStatus.add(Status.NEW);
	}
	catch (HisRecordNotFoundException e)
	{
		
		//WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
		//WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE, new ArrayList());
		
		
		System.out.println("Inside HisRecordNotFoundException");
		objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
	}
	catch (HisDataAccessException e)
	{
		//WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
		//WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE, new ArrayList());
		
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



}
