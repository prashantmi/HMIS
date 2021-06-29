package dutyroster.reports.controller.utl;




import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.reports.controller.data.MonthWiseDutyRosterReportDATA;
import dutyroster.reports.controller.fb.MonthWiseDutyRosterReportFB;

public class MonthWiseDutyRosterReportUTL extends ControllerUTIL
{

	
// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_REGISRATION_DTL and  GBLT_DESIGNATION_MST

	public static boolean getDutyRosterCategory(HttpServletRequest _request,MonthWiseDutyRosterReportFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterCategoryList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
			
			
			
			MonthWiseDutyRosterReportUTL.getYearList(_request,_fb);
			MonthWiseDutyRosterReportUTL.getMonthsList(_request,_fb);
			
			
			//currentYear=WebUTIL.getSession(_request).getAttribute(arg0)
			
			dutyRosterCategoryList=MonthWiseDutyRosterReportDATA.getDutyRosterCategory(_userVO);			
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
	
	
	// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_REGISTRATION_DTL and  GBLT_DESIGNATION_MST

	public static boolean getDutyRostersOnTheBasisOfRosterCategory(HttpServletRequest _request,MonthWiseDutyRosterReportFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO _userVO = getUserVO(_request);
		
			dutyRosterList=MonthWiseDutyRosterReportDATA.getDutyRostersOnTheBasisOfRosterCategory(_fb.getRosterCategory(),_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY, dutyRosterList);
			
			
			if(dutyRosterList.size()==1)
			{
				Entry obj=(Entry)dutyRosterList.get(0);
				String rostId=obj.getValue();
				_fb.setRosterId(rostId);
				MonthWiseDutyRosterReportUTL.getDutyAreaAndDesignationBasedOnRosterType(_request, _fb);
			}
			
			
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
		
		public static boolean getDutyAreaAndDesignationBasedOnRosterType(HttpServletRequest _request,MonthWiseDutyRosterReportFB _fb)
		{
			Status objStatus = new Status();
			
			Map	EssentialMap =new HashMap();
			List dutyAreaList=new ArrayList();
			List desigList=new ArrayList();
			
			
			try
			{
				//setting the designation id
				_fb.setDesignationId("ALL");
				
				
				UserVO _userVO = getUserVO(_request);
			
	 
				

				//fetching the Roster ID Information from the Concatinated Values 
				String concateValues=_fb.getRosterId().replace("^","@");
				String splitValues[]=concateValues.split("@");
				
				String _rosterId=splitValues[0];
				String areaTypeCode=splitValues[1];
			
				
				EssentialMap=MonthWiseDutyRosterReportDATA.getDutyAreaAndDesignationBasedOnRosterType( _rosterId,_userVO);
				
			
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
						
				if(dutyAreaList.size()==1 )//&& desigList.size()==1)
					{
			//		MonthWiseDutyRosterReportUTL.getMonthWiseDutyRosterReportBasedOnDutyAreaAndDesignation(_request, _fb);
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

			
	public static boolean getMonthWiseDutyRosterReportBasedOnDutyAreaAndDesignation(HttpServletRequest _request,MonthWiseDutyRosterReportFB _fb)
	{
		Status objStatus = new Status();
		
		Map rosterPrintMap=new LinkedHashMap();
		
		Map _holidayMap=new LinkedHashMap();
		
		List empMappedList=new ArrayList();
		Map empRosterMap=new LinkedHashMap();
		Map empDutyAreaMap=new LinkedHashMap();
		RosterDtlVO[] _rosterDtlVO=null;
		DutyRosterPrintPropertiesVO[]  _rosterPrintVO=null;
		RosterGenerationDtlVO[] _rosterGenerationDtlVO=null;
		
		//Map of days the roster is generated
		Map rosterGenerationMap=new LinkedHashMap();
		
		//days list for which we want to print
		List rosterPrintDaysList=new ArrayList();
		
		try
		{
			
			//setting the designation id
			_fb.setDesignationId("ALL");
			
			UserVO _userVO = getUserVO(_request);
			
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_FOR_MONTHWISE_DUTY_ROSTER_REPORT_DETAILS, new HashMap());
			
			
			String concateValues=_fb.getRosterId().replace("^","@");
			String splitValues[]=concateValues.split("@");
			
			String _rosterId=splitValues[0];
			String _desigId=_fb.getDesignationId();
			String _areaTypeCode=splitValues[1];
			String _areaCode=_fb.getAreaCode();
			String _month=_fb.getMonth();
			String _year=_fb.getYear();
			
//Getting the employees whose Roster has been prepared on the basis of  year,
//month,rosterId,areaTypeCode and areaCode		
			
			
			empRosterMap=MonthWiseDutyRosterReportDATA.getMonthWiseDutyRosterReportBasedOnDutyAreaAndDesignation(_desigId,_year, _month,_areaTypeCode, _areaCode, _rosterId, _userVO,_fb.getPrintingFormat());

			//getting the map of gazetted holidays from the emproster map 
			_holidayMap=(Map)empRosterMap.get(DutyRosterConfig.MAP_OF_MONTHLY_GAZETTED_HOLIDAYS);
			
			empMappedList=(List)empRosterMap.get(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE);
			
			
			
			
			
			
			
//getting the total vo's for the roster generated days					
			_rosterGenerationDtlVO=(RosterGenerationDtlVO[])empRosterMap.get(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS);

//getting the list of roster generated days	in case of 				
			if(_rosterGenerationDtlVO!=null && _rosterGenerationDtlVO.length >0 )
				rosterGenerationMap=MonthWiseDutyRosterReportUTL.getMapofRosterGeneratedDays(_rosterGenerationDtlVO);

			
//Getting the list of days for which we want printing					
			rosterPrintDaysList=MonthWiseDutyRosterReportUTL.getListofRosterPrintingDays(_fb);
			
			
			_rosterDtlVO=(RosterDtlVO[])empRosterMap.get(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);
			_rosterPrintVO=(DutyRosterPrintPropertiesVO[])empRosterMap.get(DutyRosterConfig.VO_OF_ROSTER_PRINT_DETAILS);
			
	if(_rosterPrintVO!=null)		
			rosterPrintMap=MonthWiseDutyRosterReportUTL.getPrintingMapFromArrayOfVO(_rosterPrintVO);
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_OF_ROSTER_PRINT_DETAILS, rosterPrintMap);
			
//if we get employees whose roster has been prepared for the above specifiaction
//then only we generate a report
			
		if(_rosterDtlVO!=null )
		{
											

			WebUTIL.setMapInSession(empRosterMap, _request);
			_fb.setIncharge(_rosterDtlVO[_rosterDtlVO.length-1].getIncharge());
			//setting the Roster dtl map in session
			
	if(!_fb.getAreaCode().equals("ALL"))
	    	MonthWiseDutyRosterReportUTL.setMonthWiseDutyRosterReport(_request,_fb,rosterGenerationMap,rosterPrintDaysList,_holidayMap,empMappedList);
		else
	if(_fb.getAreaCode().equals("ALL"))	
			MonthWiseDutyRosterReportUTL.setMonthWiseDutyRosterReportForAllMappedAreas(_request, _fb, rosterGenerationMap, rosterPrintDaysList,_holidayMap,empMappedList);
			
			
		}	
		
			
		//setting the roster name area name and month name in the form bean
		MonthWiseDutyRosterReportUTL.setReportHeader(_request, _fb);
		
		
			
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
		}catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}	

			
	
	public static void  getYearList(HttpServletRequest _request,MonthWiseDutyRosterReportFB _fb)
	
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

	
public static void  getMonthsList(HttpServletRequest _request,MonthWiseDutyRosterReportFB _fb)
	
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




	public static String IntToLetter(int Int) {
	    if (Int<27){
	      return Character.toString((char)(Int+64));
	    } else {
	      if (Int%26==0) {
	        return IntToLetter((Int/26)-1)+IntToLetter((Int%26)+1);
	      } else {
	        return IntToLetter(Int/26)+IntToLetter(Int%26);
	      }
	    }
	  }
	public static void  setMonthWiseDutyRosterReport(HttpServletRequest _request,MonthWiseDutyRosterReportFB _fb,Map rosterGenerationMap,List rosterPrintDaysList,Map _holidayMap,List empMappedList)
	{
		Map dutyRosterMap=new LinkedHashMap();
		
		RosterDtlVO[] _rosterDtlVO=null;
		List shiftList=new ArrayList();
		List dayOffShiftList=new ArrayList();
		List rosterGenerationList=new ArrayList();
	
		//FOR GETTING THE HEADER LIST
		List headerList=new ArrayList();
		
		Map dutyRosterSortedMap=new LinkedHashMap();
	
		//getting the emmployees  and ShiftType List list  from the session
		
		dayOffShiftList	=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS);
		_rosterDtlVO	=(RosterDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);
		shiftList		=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
	
		 if(_rosterDtlVO!=null)
			 rosterGenerationList=(List)rosterGenerationMap.get(_rosterDtlVO[0].getAreaCode());
	 
		 String sunGzMapKey="SU/GZ@SU/GZ";
	 
		 for(int i=0; i < _rosterDtlVO.length ; i ++)
		 {
		
			 	String rosterDateMapKey=_rosterDtlVO[i].getStartDateTime(); //_rosterDtlVO[i].getRosterDate();
			 	String empShortNameList="--";
			 	
			 	Map shiftMap=new LinkedHashMap();
			 	
				Iterator itr=shiftList.iterator();
					
				while(itr.hasNext())
				{
					Entry obj=(Entry)itr.next();
					
					String[] concateArray=obj.getLabel().split("@");					
					String shiftMapKey=obj.getValue()+"@"+concateArray[3];
					
					shiftMap.put(shiftMapKey, empShortNameList);
					dutyRosterMap.put(rosterDateMapKey, shiftMap);
					
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
					
					shiftMap.put(shiftMapKey, empShortNameList);
					dutyRosterMap.put(rosterDateMapKey, shiftMap);
					
					String headerShift=concateArray[3];
					
					if(!headerList.contains(headerShift))
						headerList.add(headerShift);
				}
				
				//for sunday/Gazetetd holidays
					
				if(_fb.getPrintingFormat().equals("S"))
				{
					String headerShift="Su/Gz";
					
					if(!headerList.contains(headerShift))
						headerList.add(headerShift);
						
					shiftMap.put(sunGzMapKey, empShortNameList);
					dutyRosterMap.put(rosterDateMapKey, shiftMap);
				}
		 }	 
	
		 // Putting Blank in other DateMapKey
		 
		 if(_rosterDtlVO!=null){
			 if(rosterGenerationList!=null && rosterGenerationList.size()>0){
				 Iterator dateKeyIterator = rosterGenerationList.iterator();
				 int strDateKey;
				 
				 while(dateKeyIterator.hasNext()){
					 strDateKey=(Integer)dateKeyIterator.next();
					 Map tmpShiftMap=new LinkedHashMap();
					 tmpShiftMap=(Map)dutyRosterMap.get(strDateKey+"");
					 
					 if(tmpShiftMap==null){
						 
						 String empIdList="--";
						 Map shiftMap=new LinkedHashMap();
						
						 Iterator itr=shiftList.iterator();
							while(itr.hasNext())
							{
								Entry obj=(Entry)itr.next();
								
								String[] concateArray=obj.getLabel().split("@");					
								String shiftMapKey=obj.getValue()+"@"+concateArray[3];
								
								shiftMap.put(shiftMapKey, empIdList);
								dutyRosterMap.put(strDateKey, shiftMap);
								dutyRosterSortedMap.put(strDateKey, shiftMap);
								
							}
							
							Iterator itrDayOff=dayOffShiftList.iterator();
							
							while(itrDayOff.hasNext())
							{
								Entry obj=(Entry)itrDayOff.next();
								
								String[] concateArray=obj.getValue().replace("^", "@").split("@");					
								String shiftMapKey=concateArray[0]+"@"+concateArray[3];
								
								shiftMap.put(shiftMapKey, empIdList);
								dutyRosterMap.put(strDateKey, shiftMap);
								dutyRosterSortedMap.put(strDateKey, shiftMap);
								
							}
							
							//for sunday/Gazetetd holidays
							if(_fb.getPrintingFormat().equals("S"))
							{
								String headerShift="Su/Gz";
								
								shiftMap.put(sunGzMapKey, empIdList);
								dutyRosterMap.put(strDateKey, shiftMap);
								dutyRosterSortedMap.put(strDateKey, shiftMap);
							}
					 }else{
						 dutyRosterSortedMap.put(strDateKey, tmpShiftMap);
					 }
				 }
			 }
		 }
		 
		 
		//Now adding the days into the shift map
		int seq=0;
		Map<String, Entry> mapEmpSeq = new LinkedHashMap<String,Entry>();
		//List lstEmpExist = new ArrayList();
		for(int i=0; i < _rosterDtlVO.length ; i ++)
		{
			
			// Creating a  GregorianCalendar based on the Year and Month and Day Selected 
			Calendar dailyCalendar =new GregorianCalendar();	
			dailyCalendar.set(Integer.parseInt(_fb.getYear()),Integer.parseInt(_fb.getMonth())-1, Integer.parseInt(_rosterDtlVO[i].getStartDateTime()));
			
			int dayCode=dailyCalendar.get(Calendar.DAY_OF_WEEK);
			
			boolean holidayFlag=false;
			
			//Now checking wheteher it si sunday/gazetted 
			if(_fb.getPrintingFormat().equals("S")  && (dayCode==Calendar.SUNDAY || _holidayMap.containsKey(_rosterDtlVO[i].getStartDateTime()))) 
					holidayFlag=true;
			
			Map shiftMap=new LinkedHashMap();
		
			int rosterDateMapKey=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());		// _rosterDtlVO[i].getRosterDate();
			String shiftMapKey="";
			
				shiftMapKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName();
			
			String empIdList="";
			
			if(dutyRosterSortedMap.containsKey(rosterDateMapKey))	
			{
				shiftMap=(Map)dutyRosterSortedMap.get(rosterDateMapKey);
				
				//i.e. shiftmap contains shift key and it is not a holiday
				if(shiftMap.containsKey(shiftMapKey) && holidayFlag==false)
				{
					String oldEmpIdSeqList="";
					
					oldEmpIdSeqList=(String)shiftMap.get(shiftMapKey);
					//lstEmpId.add(oldEmpList);
					
					int day=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());
					
					//checking whether the day is contained in the 
					//rosterGenerationList	and  rosterPrintDaysList
							
					if(rosterGenerationList!=null && rosterPrintDaysList!=null && rosterGenerationList.contains(day) && rosterPrintDaysList.contains(day))
					{
						String strEmpSeq="";
						Entry empEntObj = new Entry();
						
						if(mapEmpSeq!=null && mapEmpSeq.size()>0 && mapEmpSeq.containsKey(_rosterDtlVO[i].getEmpId())){
							empEntObj=mapEmpSeq.get(_rosterDtlVO[i].getEmpId());
							strEmpSeq=empEntObj.getLabel();
						}else{
							seq++;
							String seqChar= IntToLetter(seq);
							System.out.println("Sequence :"+seqChar);
							strEmpSeq=seqChar;
							empEntObj.setLabel(seqChar);
							empEntObj.setValue(_rosterDtlVO[i].getEmpName());
							mapEmpSeq.put(_rosterDtlVO[i].getEmpId(), empEntObj);
							
						}
						if(oldEmpIdSeqList.equals("--"))
							empIdList=strEmpSeq;
						else
							empIdList=oldEmpIdSeqList+","+strEmpSeq;
						
						shiftMap.put(shiftMapKey, empIdList);
						dutyRosterSortedMap.put(rosterDateMapKey, shiftMap);
						
					}//inner if closed 
				}///if closed
				
				
				if(shiftMap.containsKey(sunGzMapKey) && holidayFlag==true)
				{
					String oldEmpIdSeqList="";
						
					oldEmpIdSeqList=(String)shiftMap.get(sunGzMapKey);
						
					int day=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());
							
					//checking whether the day is contained in the 
					//rosterGenerationList	and  rosterPrintDaysList
								
					if(rosterGenerationList!=null && rosterPrintDaysList!=null && rosterGenerationList.contains(day) && rosterPrintDaysList.contains(day))
					{
						String strEmpSeq="";
						Entry empEntObj = new Entry();
						
						if(mapEmpSeq!=null && mapEmpSeq.size()>0 && mapEmpSeq.containsKey(_rosterDtlVO[i].getEmpId())){
							empEntObj=mapEmpSeq.get(_rosterDtlVO[i].getEmpId());
							strEmpSeq=empEntObj.getLabel();
						}else{
							seq++;
							String seqChar= IntToLetter(seq);
							strEmpSeq=seqChar;
							empEntObj.setLabel(seqChar);
							empEntObj.setValue(_rosterDtlVO[i].getEmpName());
							mapEmpSeq.put(_rosterDtlVO[i].getEmpId(), empEntObj);
						}
						
						if(oldEmpIdSeqList.equals("--"))
							empIdList=strEmpSeq;
						else
							empIdList=oldEmpIdSeqList+","+strEmpSeq;
							
						shiftMap.put(sunGzMapKey, empIdList);
						dutyRosterSortedMap.put(rosterDateMapKey, shiftMap);
						
						/*if(!mapEmpSeq.keySet().contains(_rosterDtlVO[i].getEmpId()))
						{
							
						}*/
									
					}//inner if closed 
				}///if closed
				
				
			}
		}
		
		WebUTIL.setAttributeInSession(_request, DutyRosterConfig.HEADER_LIST_OF_SHIFTS_FOR_REPORT, headerList);
		WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_FOR_MONTHWISE_DUTY_ROSTER_REPORT_DETAILS, dutyRosterSortedMap);
		WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_EMP_SEQ_MAP, mapEmpSeq);
		
	}



public static void  setMonthWiseDutyRosterReportForAllMappedAreas(HttpServletRequest _request,MonthWiseDutyRosterReportFB _fb,Map rosterGenerationMap,List rosterPrintDaysList,Map _holidayMap,List empMappedList)

{
	Map areaRosterMap=new LinkedHashMap();
	
	
	

	RosterDtlVO[] _rosterDtlVO=null;
	List shiftList=new ArrayList();
	List dayOffShiftList=new ArrayList();
	
	List headerList=new ArrayList();
	
	List lstEmpId = new ArrayList();
	List<List> lstOfEmpList 	= new ArrayList<List>();
	Map<String,List> mapEmpList	= new HashMap<String,List>();


	//getting the emmployees  and ShiftType List list  from the session

	// empMappedList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE);
	dayOffShiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS);
	_rosterDtlVO=(RosterDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);
	shiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
	//  dayOffShiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS);	


	String sunGzMapKey="SU/GZ@SU/GZ";
 
 
	//First of all adding all types of Mapped Shifts with the Roster 
	//into the Map 

	for(int i=0; i < _rosterDtlVO.length ; i ++)
	{
		
	 		Map empRosterMap=new LinkedHashMap();
	 		Map dutyRosterMap=new LinkedHashMap();
		 
			String areaMapKey=_rosterDtlVO[i].getAreaCode()+"@"+_rosterDtlVO[i].getAreaName();
		 	//String empMapKey=_rosterDtlVO[i].getEmpId()+"@"+_rosterDtlVO[i].getEmpName();
		 	String rosterDateKey=_rosterDtlVO[i].getRosterDate();
		 	
		 	//in case the  areaRosterMap contains key areaMapKey
		 	//then we get the emp map
		 	
		 	if(areaRosterMap.containsKey(areaMapKey))
		 		dutyRosterMap=(Map)areaRosterMap.get(areaMapKey);
		 		//empRosterMap=(Map)areaRosterMap.get(areaMapKey);
		 		
		 	String empList="--";
		 	Map shiftMap=new LinkedHashMap();
		 	
			Iterator itr=shiftList.iterator();
				
			while(itr.hasNext())
			{
				Entry obj=(Entry)itr.next();
				
				String[] concateArray=obj.getLabel().split("@");					
				String shiftMapKey=obj.getValue()+"@"+concateArray[3];
				
				shiftMap.put(shiftMapKey, empList);
				
				//for making the header 
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
				
				shiftMap.put(shiftMapKey, empList);
				
				String headerShift=concateArray[3];
			
				if(!headerList.contains(headerShift))
					headerList.add(headerShift);
			}
				
			if(_fb.getPrintingFormat().equals("S"))
				shiftMap.put(sunGzMapKey, empList);	
						
						
			//for sunday/Gazetetd holidays
						
			String headerSuGzShift="Su/Gz";
			
			if(!headerList.contains(headerSuGzShift) && _fb.getPrintingFormat().equals("S") )
				headerList.add(headerSuGzShift);
	
			//empRosterMap.put(empMapKey, shiftMap);
			empRosterMap.put(rosterDateKey, shiftMap);
			areaRosterMap.put(areaMapKey,dutyRosterMap);
			//areaRosterMap.put(areaMapKey,empRosterMap);
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
		
		if(_fb.getPrintingFormat().equals("S")  &&(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(_rosterDtlVO[i].getStartDateTime()))) 
				holidayFlag=true;
		
		Map shiftMap=new LinkedHashMap();
		Map dateMap=new LinkedHashMap();
		
	
		String empMapKey=_rosterDtlVO[i].getEmpId()+"@"+_rosterDtlVO[i].getEmpName();
		String rosterDateMapKey=_rosterDtlVO[i].getStartDateTime(); //_rosterDtlVO[i].getRosterDate();
		String shiftMapKey="";
		
		shiftMapKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftName();
		
		String daysList="";
		String empShortNameList="";
		
		Map empRosterMap=new LinkedHashMap();
		Map dutyRosterMap=new LinkedHashMap();
		 
		String areaMapKey=_rosterDtlVO[i].getAreaCode()+"@"+_rosterDtlVO[i].getAreaName();
		
		if(areaRosterMap.containsKey(areaMapKey))
			dutyRosterMap=(Map)areaRosterMap.get(areaMapKey);
	 		//empRosterMap=(Map)areaRosterMap.get(areaMapKey);
		
		List rosterGenerationList=(List)rosterGenerationMap.get(_rosterDtlVO[i].getAreaCode());
		
		//if(empRosterMap.containsKey(empMapKey))
		if(dutyRosterMap.containsKey(rosterDateMapKey))
		{
			
			//shiftMap=(Map)empRosterMap.get(empMapKey);
			//shiftMap=(Map)empRosterMap.get(rosterDateMapKey);
			
			shiftMap=(Map)dutyRosterMap.get(rosterDateMapKey);
			
			if(shiftMap.containsKey(shiftMapKey) && holidayFlag==false)
			{
				String oldDaysList="";
				String oldEmpShortNameList="";
				
				//oldDaysList=(String)shiftMap.get(shiftMapKey);
				oldEmpShortNameList=(String)shiftMap.get(shiftMapKey);
				//lstEmpId.add(oldEmpList);
				
				int day=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());
				
				
				//getting the dynamic roster genration list depending upon the area code
							
				//checking whether the day is contained in the 
				//rosterGenerationList	and  rosterPrintDaysList
				
				if(rosterGenerationList!=null && rosterPrintDaysList!=null && rosterGenerationList.contains(day) && rosterPrintDaysList.contains(day))
				{
					if(oldEmpShortNameList.equals("--"))
						empShortNameList=_rosterDtlVO[i].getEmpShortName();
					else
						empShortNameList=oldEmpShortNameList+","+_rosterDtlVO[i].getEmpShortName();
					
					shiftMap.put(shiftMapKey, empShortNameList);
					empRosterMap.put(empMapKey, shiftMap);
								
				}//inner if closed 
					
			}//if closed for normal 
	
			//if for holiday and gazetted holidays
					
			if(shiftMap.containsKey(sunGzMapKey) && holidayFlag==true)
			{
				
				String oldEmpShortNameList="";
				
				oldEmpShortNameList=(String)shiftMap.get(sunGzMapKey);
				//lstEmpId.add(oldEmpList);
				
				int day=Integer.parseInt(_rosterDtlVO[i].getStartDateTime());
				
				
				//getting the dynamic roster genration list depending upon the area code
				
				if(rosterGenerationList!=null && rosterPrintDaysList!=null && rosterGenerationList.contains(day) && rosterPrintDaysList.contains(day))
				{
					if(oldEmpShortNameList.equals("--"))
						empShortNameList=_rosterDtlVO[i].getEmpShortName();
					else
						empShortNameList=oldEmpShortNameList+","+_rosterDtlVO[i].getEmpShortName();
					
					shiftMap.put(shiftMapKey, empShortNameList);
					empRosterMap.put(empMapKey, shiftMap);
								
				}//inner if closed 
								
					
				/*String oldDaysList="";
				
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
								
				}*/ 	//inner if closed 
				
				
			}///if closed	
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
public static void setReportHeader(HttpServletRequest _request,MonthWiseDutyRosterReportFB _fb)
{
	String[] arrayOfMonths={"January","February","March","April","May","June","July","August","September","October","November","December"};

	String rosterName="";
	String areaName="";
	String monthName="";
	
	//Setting the roster Name
	
	List dutyRosterList=new ArrayList();
	dutyRosterList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY);
	
	
	Iterator itr=dutyRosterList.iterator();
	
	while(itr.hasNext())
	{
		Entry obj=(Entry)itr.next();
		
		if(obj.getValue().equals(_fb.getRosterId()))
			rosterName=obj.getLabel();
		
	}
	
	
//Setting the Area Name
	
	List dutyAreaList=new ArrayList();
	dutyAreaList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA);
	
	
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
	headerMsg="Duty Roster of "+rosterName+" ,For All Mapped Duty Areas,  For the Month of "+monthName+", "+_fb.getYear();
else
	headerMsg="Duty Roster of "+rosterName+" ,Duty Area "+areaName+" For the Month of "+monthName+", "+_fb.getYear();
	
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


public static List getListofRosterPrintingDays(MonthWiseDutyRosterReportFB _fb)
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
