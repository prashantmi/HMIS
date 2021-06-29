

package dutyroster.reports.controller.utl;




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
import dutyroster.reports.controller.data.EmpWiseSupervisiorEmpRosterReportDATA; 
import dutyroster.reports.controller.fb.EmpWiseSupervisiorEmpRosterReportFB;


public class EmpWiseSupervisiorEmpRosterReportUTL extends ControllerUTIL
{

	public static boolean getEmpListofSupervisior(HttpServletRequest _request,EmpWiseSupervisiorEmpRosterReportFB _fb)
	{
		Status objStatus = new Status();
		
		Map rosterPrintMap=new LinkedHashMap();
		
		
		//days list for which we want to print
		List empList=new ArrayList();
		
		
		
		try
		{
			
			//setting the designation id
			_fb.setDesignationId("ALL");
			
			UserVO _userVO = ControllerUTIL.getUserVO(_request);
			
 
			EmpWiseSupervisiorEmpRosterReportUTL.setYearList(_request, _fb);
			EmpWiseSupervisiorEmpRosterReportUTL.setMonthsList(_request, _fb);


			
			
//Getting the employees whose Roster has been prepared on the basis of  year,_rosterId
//month,rosterId,areaTypeCode and areaCode		
			
			
			empList=EmpWiseSupervisiorEmpRosterReportDATA.getEmpListofSupervisior(_userVO);
			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_SUPERVISIOR_EMPLOYEES, empList);
			
			
			
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
		
			
			e.printStackTrace();
			
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
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

			
			public static boolean getEmpWiseRosterReport(HttpServletRequest _request,EmpWiseSupervisiorEmpRosterReportFB _fb)
			{
				Status objStatus = new Status();
				
				Map rosterPrintMap=new LinkedHashMap();
				HospitalMstVO _hospitalVO = getHospitalVO(_request);
				
				Map EssentialMap=new HashMap();
				Map empDutyAreaMap=new HashMap();
				
				
				//Map of days the roster is generated
				Map areaGenarationMap=new LinkedHashMap();
				
				//days list for which we want to print
				List rosterPrintDaysList=new ArrayList();
				
				
				
				try
				{
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER,new HashMap());
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.HOSPITAL_MST_VO , _hospitalVO);
					
					//setting the designation id
					_fb.setDesignationId("ALL");
					
					UserVO _userVO = ControllerUTIL.getUserVO(_request);
					
		 
					
					
					
//Getting the employees whose Roster has been prepared on the basis of  year,_rosterId
//month,rosterId,areaTypeCode and areaCode		
					
					
					EssentialMap=EmpWiseSupervisiorEmpRosterReportDATA.getEmpWiseRosterReport(_fb.getYear(),_fb.getMonth(),_fb.getEmpId(),_userVO);
					
					WebUTIL.setMapInSession(EssentialMap, _request);
					
					
					//getting the roster generation nmap
									
					areaGenarationMap=EmpWiseSupervisiorEmpRosterReportUTL.getMapofRosterGeneratedDays(_request);
				
				
					rosterPrintDaysList=EmpWiseSupervisiorEmpRosterReportUTL.getListofRosterPrintingDays(_fb);
					
					
				
					
		if(_fb.getPrintingFormat().equals("A"))
			EmpWiseSupervisiorEmpRosterReportUTL.setAreaWiseEmpSelfRosterReport(_request, _fb, areaGenarationMap, rosterPrintDaysList);
				else
		if(_fb.getPrintingFormat().equals("D"))
			EmpWiseSupervisiorEmpRosterReportUTL.setDateWiseEmpSelfRosterReport(_request, _fb, areaGenarationMap, rosterPrintDaysList);
							
								
				
		//setting the report heasder 
		EmpWiseSupervisiorEmpRosterReportUTL.setReportHeader(_request, _fb);

		
					
					objStatus.add(Status.NEW);
				}
				catch (HisRecordNotFoundException e)
				{
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER,new HashMap());
					
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

			
	

/**
 * 	getting the complete monthly roster of all the mapped employees 
 * 	excluding this roster Type and area code
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static Map getMapofRosterGeneratedDays(HttpServletRequest _request)

{
	RosterGenerationDtlVO[] _rosterGenerationDtlVO=null;
	
	_rosterGenerationDtlVO=(RosterGenerationDtlVO[])_request.getSession().getAttribute(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS);
	
	
	Map areaGenarationMap=new LinkedHashMap();

if(_rosterGenerationDtlVO!=null)
{	
	

		
	for(int i=0; i < _rosterGenerationDtlVO.length ; i++ )
	{
		Map rosterMap=new LinkedHashMap();
		
		
		
		
		
		List rosterGenList=new ArrayList();
		
		String areaKey=_rosterGenerationDtlVO[i].getAreaTypeCode()+"@"+ _rosterGenerationDtlVO[i].getAreaCode();
		
		String rosterKey=_rosterGenerationDtlVO[i].getRosterId();
		

		
		//in case it contains the list old Map will be fetched 				
		if(areaGenarationMap.containsKey(areaKey))		
			{
			rosterMap=(LinkedHashMap)areaGenarationMap.get(areaKey);
			
			//if contains a key then dates are added to the old list 
			if(rosterMap.containsKey(rosterKey))	
				{
				rosterGenList=(List)rosterMap.get(rosterKey);
				
				}
				
			
				
			}	
	
	
		
		//in case the 	rosterGenerationMap does not contains the ara key ,hence fresh list will be added 
		
		
		int startDate=Integer.parseInt(_rosterGenerationDtlVO[i].getStartDate());
		int endDate=Integer.parseInt(_rosterGenerationDtlVO[i].getEndDate());
				
			for(int j=startDate; j <= endDate ; j++)
				rosterGenList.add(j);
			
				rosterMap.put(rosterKey, rosterGenList);
				areaGenarationMap.put(areaKey, rosterMap);
				
				
			
	}

}	
	
	return areaGenarationMap;
}



/**
 * 	getting the complete monthly roster of all the mapped employees 
 * 	excluding this roster Type and area code
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static List getListofRosterPrintingDays(EmpWiseSupervisiorEmpRosterReportFB _fb)
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

public static void  setAreaWiseEmpSelfRosterReport(HttpServletRequest _request,EmpWiseSupervisiorEmpRosterReportFB _fb,Map areaGenarationMap,List rosterPrintDaysList)
{



	RosterDtlVO[] _rosterDtlVO=null;
	
	_rosterDtlVO=(RosterDtlVO[])_request.getSession().getAttribute(DutyRosterConfig.TOTAL_VO_OF_EMPWISE_ROSTER_DTL);
		
	//List headerList=new ArrayList(); 

	Map areaMap=new LinkedHashMap();


	if(_rosterDtlVO!=null)
{
		for(int i=0 ;i < _rosterDtlVO.length; i++)
	{

			String areaKey=_rosterDtlVO[i].getAreaTypeCode()+"@"+_rosterDtlVO[i].getAreaCode()+"@"+_rosterDtlVO[i].getAreaName();
			String rosterKey=_rosterDtlVO[i].getRosterId()+"@"+_rosterDtlVO[i].getRosterName();
			String shiftKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName()+"@"+_rosterDtlVO[i].getShiftShortName();
			String newDays=_rosterDtlVO[i].getStartDateTime();
			String oldDays="NA";	
			
			
			Map rosterMap=new LinkedHashMap();
			Map shiftMap=new LinkedHashMap();
			
			
			if(areaMap.containsKey(areaKey))
				{
				rosterMap=(LinkedHashMap)areaMap.get(areaKey);
				
				if(rosterMap.containsKey(rosterKey))
						{
					shiftMap=(LinkedHashMap)rosterMap.get(rosterKey);
					
					    if(shiftMap.containsKey(shiftKey))
					    		oldDays=(String)shiftMap.get(shiftKey);
					
						}
					
				}//inner if closed 
				
			if(oldDays.equals("NA"))
				shiftMap.put(shiftKey, newDays);
				else
			if(!oldDays.equals("NA"))
				{
				oldDays=oldDays+" ,"+newDays;
				shiftMap.put(shiftKey, oldDays);
				}	
			rosterMap.put(rosterKey, shiftMap);
			areaMap.put(areaKey, rosterMap);
			
			
			
			
			
	}//for cloesd
		
		
		
}//outer if closed


	
		WebUTIL.setAttributeInSession(_request, DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER,areaMap);

	

}



public static void  setDateWiseEmpSelfRosterReport(HttpServletRequest _request,EmpWiseSupervisiorEmpRosterReportFB _fb,Map areaGenarationMap,List rosterPrintDaysList)
{

	RosterDtlVO[] _rosterDtlVO=null;
	
	_rosterDtlVO=(RosterDtlVO[])_request.getSession().getAttribute(DutyRosterConfig.TOTAL_VO_OF_EMPWISE_ROSTER_DTL);
		

	Map dateMap=new LinkedHashMap();


	if(_rosterDtlVO!=null)
	 {
		for(int i=0 ;i < _rosterDtlVO.length; i++)
			{
			String dateKey=_rosterDtlVO[i].getStartDateTime();
			String areaKey=_rosterDtlVO[i].getAreaTypeCode()+"@"+_rosterDtlVO[i].getAreaCode()+"@"+_rosterDtlVO[i].getAreaName();
			String rosterKey=_rosterDtlVO[i].getRosterId()+"@"+_rosterDtlVO[i].getRosterName();
			String shiftName="";
			
			Map areaMap=new LinkedHashMap();
			Map rosterMap=new LinkedHashMap();
			
			String oldShift="NA";
			
			
			
			
			Map rosterGenarationMap=new LinkedHashMap();
			List daysGeneratedList=new ArrayList();
			
			String areaGenKey=_rosterDtlVO[i].getAreaTypeCode()+"@"+_rosterDtlVO[i].getAreaCode();
			String rosterGenKey=_rosterDtlVO[i].getRosterId();
			
			rosterGenarationMap=(LinkedHashMap)areaGenarationMap.get(areaGenKey);
			
			//getting the daysgenerated list 
			daysGeneratedList=(List)rosterGenarationMap.get(rosterGenKey);
			
			int date=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());
			
			boolean containsFlag=false;
			
			//now checking whether the current date exsists or not 
			//in rosterPrintDaysList and daysGeneratedList
			if(rosterPrintDaysList!=null && daysGeneratedList!=null && rosterPrintDaysList.contains(date) && daysGeneratedList.contains(date))
				containsFlag=true;
			
			
			
			
			
			
		if(dateMap.containsKey(dateKey))
				{
			
			areaMap=(LinkedHashMap)dateMap.get(dateKey);
			
					if(areaMap.containsKey(areaKey))
						{
						rosterMap=(LinkedHashMap)areaMap.get(areaKey);
						
						if(rosterMap.containsKey(rosterKey))
								{
							oldShift=(String)rosterMap.get(rosterKey);
							
							    }
						
						}
			
			
				}
			
			
		
if(containsFlag==true)
			 {	
		
		if(oldShift!=null && !oldShift.equals("NA") )
			shiftName=oldShift+", "+_rosterDtlVO[i].getShiftName();
		else
			shiftName=_rosterDtlVO[i].getShiftName();
		
		
		
			
			rosterMap.put(rosterKey, shiftName);
			areaMap.put(areaKey, rosterMap);
			dateMap.put(dateKey, areaMap);
				}
			
			
			}
		
		
		
	 }


		WebUTIL.setAttributeInSession(_request, DutyRosterConfig.TOTAL_MAP_OF_EMP_ROSTER,dateMap);

}

//Making the Map from the array of Vos 
public static void setReportHeader(HttpServletRequest _request,EmpWiseSupervisiorEmpRosterReportFB _fb)
{
	
	String[] arrayOfMonths={"January","February","March","April","May","June","July","August","September","October","November","December"};
	
	String headerMsg="";
	
	List empList=new ArrayList();
	empList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_SUPERVISIOR_EMPLOYEES);
	
Iterator itr=empList.iterator();

String empName="";

while(itr.hasNext())
{
Entry obj=new Entry();
obj=(Entry)itr.next();

if(obj.getValue().equals(_fb.getEmpId()))
	empName=obj.getLabel();

}
	
	String period="";
	String monthName="";
	
int monthId=Integer.parseInt(_fb.getMonth())-1;

	
	monthName=arrayOfMonths[monthId];
	
	
	
if(!_fb.getStartDate().equals("") && !_fb.getEndDate().equals(""))
	period=", (for the period "+_fb.getStartDate()+" To "+_fb.getEndDate()+")";
else
	period=", (for the Month of "+monthName+")";
	
	


if(_fb.getPrintingFormat().equals("A"))
	headerMsg="AreaWise Report of "+empName+period;
else
if(_fb.getPrintingFormat().equals("D"))	
	headerMsg="DateWise Report of "+empName+period;
	
	

	_fb.setHeaderMsg(headerMsg);
	
}



public static void  setYearList(HttpServletRequest _request,EmpWiseSupervisiorEmpRosterReportFB _fb)

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


public static void  setMonthsList(HttpServletRequest _request,EmpWiseSupervisiorEmpRosterReportFB _fb)

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
