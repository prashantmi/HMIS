
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
import javax.servlet.http.HttpSession;





import dutyroster.DutyRosterConfig;  
import dutyroster.reports.controller.data.AreaWiseEmpRosterReportDATA;
import dutyroster.reports.controller.fb.AreaWiseEmpRosterReportFB;
import dutyroster.transaction.controller.utl.EmpRosterGenerationUTLFunctions;
import dutyroster.transaction.controller.utl.EmployeeDutyRosterUTLFunctions;

public class AreaWiseEmpRosterReportUTL extends ControllerUTIL
{
	
		

// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_PERSONNEL_DTL and  GBLT_DESIGNATION_MST

	public static boolean getDutyRosterCategory(HttpServletRequest _request,AreaWiseEmpRosterReportFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterCategoryList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO uVO = getUserVO(_request);
			UserVO _userVO = new UserVO();
			HelperMethods.populate(_userVO, uVO);
			//_userVO.setHospitalCode(_fb.getHospitalCode());
			
			
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTER_CATEGORY);
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY);
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY_AND_AREA);
			
			
			_fb.setRosterCategory(null);
			_fb.setAreaCode(null);
			_fb.setRosterId(null);
			
			setSysdate(_request);
			
			
			
			AreaWiseEmpRosterReportUTL.getYearList(_request,_fb);
			AreaWiseEmpRosterReportUTL.getMonthsList(_request,_fb);
			
			
			//currentYear=WebUTIL.getSession(_request).getAttribute(arg0)
			
			//Map map1=(HashMap)AreaWiseEmpRosterReportDATA.getAllHospitalEssentials(getUserVO(_request));
			//WebUTIL.setMapInSession(map1, _request);

			dutyRosterCategoryList=AreaWiseEmpRosterReportDATA.getDutyRosterCategory(_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTER_CATEGORY, dutyRosterCategoryList);
			
						
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
	
	public static boolean getRosterCategoryBasedOnHospital(HttpServletRequest _request,AreaWiseEmpRosterReportFB _fb)
	{
		Status objStatus = new Status();
		
		List categoryList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO uVO = getUserVO(_request);
			UserVO _userVO = new UserVO();
			HelperMethods.populate(_userVO, uVO);
			//_userVO.setHospitalCode(_fb.getHospitalCode());
			
			
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTER_CATEGORY);
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY);
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY_AND_AREA);
			
			
			_fb.setRosterCategory(null);
			_fb.setAreaCode(null);
			_fb.setRosterId(null);
			
			
			categoryList=AreaWiseEmpRosterReportDATA.getRosterCategoryBasedOnHospital(_fb.getHospitalCode(),_userVO);
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTER_CATEGORY, categoryList);
			
			
			/*if(dutyRosterList.size()==1)
			{
				Entry obj=(Entry)dutyRosterList.get(0);
				String rostId=obj.getValue();
				_fb.setRosterId(rostId);
				AreaWiseEmpRosterReportUTL.getDutyAreaAndDesignationBasedOnRosterType(_request, _fb);
			}*/
			
			
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
//	Employee details from PIST_EMP_PERSONNEL_DTL and  GBLT_DESIGNATION_MST

	public static boolean getDutyAreasBasedOnRosterCategory(HttpServletRequest _request,AreaWiseEmpRosterReportFB _fb)
	{
		Status objStatus = new Status();
		
		List areaList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO uVO = getUserVO(_request);
			UserVO _userVO = new UserVO();
			HelperMethods.populate(_userVO, uVO);
			//_userVO.setHospitalCode(_fb.getHospitalCode());
			
			
			
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY);
			_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY_AND_AREA);
			
			
			
			_fb.setAreaCode(null);
			_fb.setRosterId(null);
			
		
			areaList=AreaWiseEmpRosterReportDATA.getDutyAreasBasedOnRosterCategory(_fb.getRosterCategory(),_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY, areaList);
			
			
			/*if(dutyRosterList.size()==1)
			{
				Entry obj=(Entry)dutyRosterList.get(0);
				String rostId=obj.getValue();
				_fb.setRosterId(rostId);
				AreaWiseEmpRosterReportUTL.getDutyAreaAndDesignationBasedOnRosterType(_request, _fb);
			}*/
			
			
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
	


	// Getting the Duty Area on the Basis of Duty Area Types
//		 from the Package PKG_DUTY_ROSTER using the Procedure Proc_get_duty_area
		
		public static boolean getRostersBasedOnDutyAreaAndRosterCatg(HttpServletRequest _request,AreaWiseEmpRosterReportFB _fb)
		{
			Status objStatus = new Status();
			
			Map	EssentialMap =new HashMap();
			List rosterList=new ArrayList();
			
			
			
			try
			{
								
				
				//UserVO _userVO = getUserVO(_request);
				UserVO uVO = getUserVO(_request);
				UserVO _userVO = new UserVO();
				HelperMethods.populate(_userVO, uVO);
				//_userVO.setHospitalCode(_fb.getHospitalCode());
				
				
				_request.getSession().removeAttribute(DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY_AND_AREA);
			
				_fb.setRosterId(null);
				
				

				//fetching the Roster ID Information from the Concatinated Values 
			
				String splitValues[]=_fb.getAreaCode().split("@");
				
				String _areaTypeCode=splitValues[0];
				String _areaCode=splitValues[1];
				
			
				
				rosterList=AreaWiseEmpRosterReportDATA.getRostersBasedOnDutyAreaAndRosterCatg( _fb.getRosterCategory(),_areaTypeCode,_areaCode,_userVO);
				
			
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY_AND_AREA, rosterList);
				
				/*dutyAreaList=(ArrayList)EssentialMap.get(DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY_AND_AREA);
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
					AreaWiseEmpRosterReportUTL.getMonthWiseEmpRosterReportBasedOnDutyAreaAndDesignation(_request, _fb);
					}
				*/
				
				
				
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
				
				
				
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

			
			public static boolean getAreaWiseReport(HttpServletRequest _request,AreaWiseEmpRosterReportFB _fb)
			{
				Status objStatus = new Status();
				
				Map rosterPrintMap=new LinkedHashMap();
				
				
				Map _holidayMap=new LinkedHashMap();
				
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
				
				try
				{
					
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS, new HashMap());
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.HOSPITAL_MST_VO , _hospitalVO);
					
					UserVO uVO = getUserVO(_request);
					UserVO _userVO = new UserVO();
					HelperMethods.populate(_userVO, uVO);
					//_userVO.setHospitalCode(_fb.getHospitalCode());
					
		 
					
					String splitValues[]=_fb.getAreaCode().split("@");
					
					String _rosterId=_fb.getRosterId();
				
					String _areaTypeCode=splitValues[0];
					String _areaCode=splitValues[1];
					
					String _month=_fb.getMonth();
					String _year=_fb.getYear();
					
//Getting the employees whose Roster has been prepared on the basis of  year,
//month,rosterId,areaTypeCode and areaCode		
					
					
				empRosterMap=AreaWiseEmpRosterReportDATA.getAreaWiseReport(_year, _month,_areaTypeCode, _areaCode, _rosterId, _userVO,_fb.getRosterCategory());
					

					//getting the map of gazetted holidays from the emproster map 
					_holidayMap=(Map)empRosterMap.get(DutyRosterConfig.MAP_OF_MONTHLY_GAZETTED_HOLIDAYS);
					
					
					
					
//getting the total vo's for the roster generated days					
					_rosterGenerationDtlVO=(RosterGenerationDtlVO[])empRosterMap.get(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS);

//getting the list of roster generated days	in case of 				
					if(_rosterGenerationDtlVO!=null && _rosterGenerationDtlVO.length >0 )
						rosterGenerationMap=AreaWiseEmpRosterReportUTL.getMapofRosterGeneratedDays(_rosterGenerationDtlVO);

					
//Getting the list of days for which we want printing					
					rosterPrintDaysList=AreaWiseEmpRosterReportUTL.getListofRosterPrintingDays(_fb);
					
					
					_rosterDtlVO=(RosterDtlVO[])empRosterMap.get(DutyRosterConfig.TOTAL_VO_OF_EMPWISE_ROSTER_DTL);
					
					
					_rosterPrintVO=(DutyRosterPrintPropertiesVO[])empRosterMap.get(DutyRosterConfig.VO_OF_ROSTER_PRINT_DETAILS);
					
			if(_rosterPrintVO!=null)		
					rosterPrintMap=AreaWiseEmpRosterReportUTL.getPrintingMapFromArrayOfVO(_rosterPrintVO);
					
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS, rosterPrintMap);
					
//if we get employees whose roster has been prepared for the above specifiaction
//then only we generate a report
					
				if(_rosterDtlVO!=null )
				{
													

					WebUTIL.setMapInSession(empRosterMap, _request);

					//setting the Roster dtl map in session
					
			if(!_fb.getRosterId().equals("ALL"))
			    	AreaWiseEmpRosterReportUTL.setMonthWiseEmpRosterReport(_request,_fb,rosterGenerationMap,rosterPrintDaysList,_holidayMap);
				else
			if(_fb.getRosterId().equals("ALL"))	
					AreaWiseEmpRosterReportUTL.setMonthWiseEmpRosterReportForAllRosters(_request, _fb, rosterGenerationMap, rosterPrintDaysList, _holidayMap);
			
					
				//	AreaWiseEmpRosterReportUTL.setMonthWiseEmpRosterReport(_request,_fb,rosterGenerationMap,rosterPrintDaysList,_holidayMap);
			
			
				}	
				
					
				//setting the roster name area name and month name in the form bean
				AreaWiseEmpRosterReportUTL.setRosterNameAreaNameAndMonth(_request, _fb);
				
				
					
					objStatus.add(Status.NEW);
				}
				catch (HisRecordNotFoundException e)
				{
				
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS, new HashMap());
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

			
	
	public static void  getYearList(HttpServletRequest _request,AreaWiseEmpRosterReportFB _fb)
	
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

	
public static void  getMonthsList(HttpServletRequest _request,AreaWiseEmpRosterReportFB _fb)
	
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





public static void  setMonthWiseEmpRosterReport(HttpServletRequest _request,AreaWiseEmpRosterReportFB _fb,Map rosterGenerationMap,List rosterPrintDaysList,Map _holidayMap)

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
	_rosterDtlVO=(RosterDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.TOTAL_VO_OF_EMPWISE_ROSTER_DTL);
 shiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
//  dayOffShiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS);	

 
 
 
 
Map rosMap=new LinkedHashMap();
 
 if(rosterGenerationMap.containsKey(_rosterDtlVO[0].getAreaCode()))
	 rosMap=(LinkedHashMap)rosterGenerationMap.get(_rosterDtlVO[0].getAreaCode());
 

 
 if(rosMap.containsKey(_rosterDtlVO[0].getRosterId()))
	 rosterGenerationList=(List)rosMap.get(_rosterDtlVO[0].getRosterId());

 
 
 
 
//First of all adding all types of Mapped Shifts with the Roster 
//into the Map 

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
				empRosterMap.put(empMapKey, shiftMap);			
				
				/*if(i==0)
					headerList.add(concateArray[3]);*/
				
				String headerShift=concateArray[3]+" ("+concateArray[1]+" To "+concateArray[2]+")";
				
				
				
				if(!headerList.contains(headerShift))
					headerList.add(headerShift);
				
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
				
				/*if(i==0)
					headerList.add(concateArray[3]);*/
				
				String headerShift=concateArray[3];
				
				if(!headerList.contains(headerShift))
					headerList.add(headerShift);
			}
			
			
			String sunGzMapKey="SU/GZ@SU/GZ";
			
			
			String headerShift="Su/Gz";
			
			if(!headerList.contains(headerShift) && _fb.getPrintingFormat().equals("S"))
				headerList.add(headerShift);
			
			//System.out.println("shiftMapKey---------"+shiftMapKey);
		//	headerList.add(shiftMapKey);
			
		//	shiftMap.put(shiftMapKey, daysList);
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
	
	/*if(_fb.getPrintingFormat().equals("S")  &&(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(_rosterDtlVO[i].getStartDateTime()))) 
			holidayFlag=true;*/
		

Map shiftMap=new HashMap();
Map dateMap=new HashMap();

	
	String empMapKey=_rosterDtlVO[i].getEmpId()+"@"+_rosterDtlVO[i].getEmpName();
	String shiftMapKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName();
	
	String daysList="";
	
	if(empRosterMap.containsKey(empMapKey))
	{
		
		shiftMap=(Map)empRosterMap.get(empMapKey);
		
		if(shiftMap.containsKey(shiftMapKey))
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

WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS, empRosterMap);

	 
	
}



public static void  setMonthWiseEmpRosterReportForAllRosters(HttpServletRequest _request,AreaWiseEmpRosterReportFB _fb,Map rosterGenerationMap,List rosterPrintDaysList,Map _holidayMap)

{
	Map empRosterMap=new LinkedHashMap();
	
	
	

	RosterDtlVO[] _rosterDtlVO=null;
	


//getting the emmployees  and ShiftType List list  from the session



	_rosterDtlVO=(RosterDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.TOTAL_VO_OF_EMPWISE_ROSTER_DTL);

	


 
 
 
//First of all adding all types of Mapped Shifts with the Roster 
//into the Map 

 
	 
	String offKey="offKey";
	String suGzKey="suGzKey";
	
	
	//for displaying the header
	Map shiftHeaderMapDisplay=new LinkedHashMap();

	 List shiftOffListDisplay=new ArrayList();
	 List shiftSuGzListDisplay=new ArrayList();

	
	 shiftHeaderMapDisplay.put(offKey, shiftOffListDisplay);
	 shiftHeaderMapDisplay.put(suGzKey, shiftSuGzListDisplay);
	 
	
	 
	
	Map shiftHeaderMap=new LinkedHashMap();

	 List shiftOffList=new ArrayList();
	 List shiftSuGzList=new ArrayList();

	 
	 shiftHeaderMap.put(offKey, shiftOffList);
	 shiftHeaderMap.put(suGzKey, shiftSuGzList);
	 
	 
	
	 
//first adding all the distinct shifts 	 
	 
 for(int i=0; i < _rosterDtlVO.length ; i ++)
 {
	 
	
	 
	// Creating a  GregorianCalendar based on the Year and Month and Day Selected 
		Calendar dailyCalendar =new GregorianCalendar();	
		dailyCalendar.set(Integer.parseInt(_fb.getYear()),Integer.parseInt(_fb.getMonth())-1, Integer.parseInt(_rosterDtlVO[i].getStartDateTime()));
		
		int dayCode=dailyCalendar.get(Calendar.DAY_OF_WEEK);
		
		System.out.println("day code--------"+dayCode);
		
		boolean holidayFlag=false;
		
		if(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(_rosterDtlVO[i].getStartDateTime()) ) 
				holidayFlag=true;
		
		
	
	if(holidayFlag==false)
		{
		String shiftKey="";
		String shiftKeyDisplay="";
		
	if(_rosterDtlVO[i].getShiftType().equals("0"))
		shiftKeyDisplay=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName()+"@"+"Offical";
	else	
		shiftKeyDisplay=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName()+"@"+"Offical("+_rosterDtlVO[i].getShiftStartTime()+" To "+_rosterDtlVO[i].getShiftEndTime()+")";
		
	
		shiftKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName()+"@"+"Offical";
	
		
		shiftOffList=(ArrayList)shiftHeaderMap.get(offKey);
		
		
		shiftOffListDisplay=(ArrayList)shiftHeaderMapDisplay.get(offKey);
		
		if(!shiftOffList.contains(shiftKey))
			shiftOffList.add(shiftKey);
		
		
		if(!shiftOffListDisplay.contains(shiftKeyDisplay))
			shiftOffListDisplay.add(shiftKeyDisplay);
		
		}	
		else
	if(holidayFlag==true)
		{
		String shiftKey="";
		String shiftKeyDisplay="";
		
		if(_rosterDtlVO[i].getShiftType().equals("0"))
			shiftKeyDisplay=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName()+"@"+"Su/Gz";
		else
			shiftKeyDisplay=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName()+"@"+"Su/Gz("+_rosterDtlVO[i].getShiftStartTime()+" To "+_rosterDtlVO[i].getShiftEndTime()+")";
		
		
		shiftKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName()+"@"+"Su/Gz";
		
		
		 shiftSuGzList=(ArrayList)shiftHeaderMap.get(suGzKey);
			
		 
		 shiftSuGzListDisplay=(ArrayList)shiftHeaderMapDisplay.get(suGzKey);
		 
		 
				if(!shiftSuGzList.contains(shiftKey))
					shiftSuGzList.add(shiftKey);
					
				if(!shiftSuGzListDisplay.contains(shiftKeyDisplay))
					shiftSuGzListDisplay.add(shiftKeyDisplay);
				
			}	
		
		
	 
 }	 
 shiftHeaderMap.put(offKey, shiftOffList);
 shiftHeaderMap.put(suGzKey, shiftSuGzList);
 
 
 //for displaying on the header
 
 shiftHeaderMapDisplay.put(offKey, shiftOffListDisplay);
 shiftHeaderMapDisplay.put(suGzKey, shiftSuGzListDisplay);
 
 
 
//now adding all shifts into each employee 
 
 for(int i=0; i < _rosterDtlVO.length ; i++)
 {
	 Map shifMap=new LinkedHashMap();
	 String days="--";
	 List shiftList=new ArrayList();
	 
		String empKey=_rosterDtlVO[i].getEmpId()+"@"+_rosterDtlVO[i].getEmpName();
		String shiftKey="";	
		
		if(!empRosterMap.containsKey(empKey))
			{
			
			shiftList=(ArrayList)shiftHeaderMap.get(offKey);
			
			
				for(int j=0; j < shiftList.size(); j++)
					{
					shiftKey=(String)shiftList.get(j);
					shifMap.put(shiftKey, days);
					}
					
			
				shiftList=(ArrayList)shiftHeaderMap.get(suGzKey);
				
				
				for(int j=0; j < shiftList.size(); j++)
					{
					shiftKey=(String)shiftList.get(j);
					shifMap.put(shiftKey, days);
					}
				
				empRosterMap.put(empKey, shifMap);
				
			}
			
	
		
	 
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
	
	if(_fb.getRosterId().equals("ALL")  &&(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(_rosterDtlVO[i].getStartDateTime()))) 
			holidayFlag=true;
	
	
	
	List rosterGenerationList=new ArrayList();
	
	Map rosMap=new LinkedHashMap();
	 
	 if(rosterGenerationMap.containsKey(_rosterDtlVO[i].getAreaCode()))
		 rosMap=(LinkedHashMap)rosterGenerationMap.get(_rosterDtlVO[i].getAreaCode());
	 

	 
	 if(rosMap.containsKey(_rosterDtlVO[i].getRosterId()))
		 rosterGenerationList=(List)rosMap.get(_rosterDtlVO[i].getRosterId());
	
	
	 int day=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());
	
	
	
	
	
	
	
	
	
	
	
	
	
	String empKey=_rosterDtlVO[i].getEmpId()+"@"+_rosterDtlVO[i].getEmpName();
	
	Map shiftMap=new LinkedHashMap();
	
	String oldDays="";
	String newDays="";
	
	if(empRosterMap.containsKey(empKey))
			shiftMap=(LinkedHashMap)empRosterMap.get(empKey);
	
	
	if(holidayFlag==false)
		{
		String shiftKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName()+"@"+"Offical";
		
		if(shiftMap.containsKey(shiftKey))
			oldDays=(String)shiftMap.get(shiftKey);
		
		
	if(rosterGenerationList!=null && rosterPrintDaysList!=null && rosterGenerationList.contains(day) && rosterPrintDaysList.contains(day))
			{
		if(oldDays!=null && oldDays.equals("--"))
			newDays=Integer.toString(day);
		else
			newDays=oldDays+", "+ Integer.toString(day);
		
			shiftMap.put(shiftKey, newDays);
			empRosterMap.put(empKey, shiftMap);
			}
		
		}
	else
		if(holidayFlag==true)
		{
		String shiftKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName()+"@"+"Su/Gz";
		
	if(shiftMap.containsKey(shiftKey))	
		oldDays=(String)shiftMap.get(shiftKey);
		
	
	if(rosterGenerationList!=null && rosterPrintDaysList!=null && rosterGenerationList.contains(day) && rosterPrintDaysList.contains(day))
			{
		if(oldDays!=null && oldDays.equals("--"))
			newDays=Integer.toString(day);
		else
			newDays=oldDays+", "+Integer.toString(day);
		
		
		shiftMap.put(shiftKey, newDays);
		empRosterMap.put(empKey, shiftMap);
			}
		
		}
	



}//for closed 



WebUTIL.setAttributeInSession(_request, DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT, shiftHeaderMapDisplay);

WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_FOR_MONTHWISE_EMP_ROSTER_REPORT_DETAILS, empRosterMap);

	 
	
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
public static void setRosterNameAreaNameAndMonth(HttpServletRequest _request,AreaWiseEmpRosterReportFB _fb)
{
	
	String[] arrayOfMonths={"January","February","March","April","May","June","July","August","September","October","November","December"};	
	
//setting the month name
	
	int monthId=Integer.parseInt(_fb.getMonth())-1;

	
	String monthName=arrayOfMonths[monthId];
	
	
List areaList=new ArrayList();
	areaList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_AREAS_ON_THE_BASIS_OF_ROSTER_CATEGORY);
	
Iterator itr=areaList.iterator();

String areaName="";

while(itr.hasNext())
{
Entry obj=new Entry();
obj=(Entry)itr.next();

if(obj.getValue().equals(_fb.getAreaCode()))
	areaName=obj.getLabel();

}
	
	
	String headerMsg="";
	
//in case we have all the mapped areas then msg will be different 
	
headerMsg="Report for Duty Area ,"+areaName+",for the month of "+monthName+","+_fb.getYear();	
	
	_fb.setMonthChar(monthName+"-"+_fb.getYear());
	_fb.setAreaName(areaName);
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
		Map rosterMap=new LinkedHashMap();
		
		List rosterGenList=new ArrayList();
		
		String areaKey=_rosterGenerationDtlVO[i].getAreaCode();
		
		
	
	//in case it contains the list old list will be fetched 			
		if(rosterGenerationMap.containsKey(areaKey))		
			{
			rosterMap=(LinkedHashMap)rosterGenerationMap.get(areaKey);
			
			if(rosterMap.containsKey(_rosterGenerationDtlVO[i].getRosterId()))
				rosterGenList=(List)rosterMap.get(_rosterGenerationDtlVO[i].getRosterId());
				
			}	
	
	
		int startDate=Integer.parseInt(_rosterGenerationDtlVO[i].getStartDate());
		int endDate=Integer.parseInt(_rosterGenerationDtlVO[i].getEndDate());
				
			for(int j=startDate; j <= endDate ; j++)
				rosterGenList.add(j);

				rosterMap.put(_rosterGenerationDtlVO[i].getRosterId(), rosterGenList);
				rosterGenerationMap.put(areaKey, rosterMap);
		
			
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


public static List getListofRosterPrintingDays(AreaWiseEmpRosterReportFB _fb)
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

}
