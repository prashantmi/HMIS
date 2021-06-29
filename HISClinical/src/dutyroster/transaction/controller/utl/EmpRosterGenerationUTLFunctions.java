
package dutyroster.transaction.controller.utl;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig; 
import dutyroster.transaction.controller.fb.EmpRosterGenerationFB;



public class EmpRosterGenerationUTLFunctions {

	
	
	
	/**For Making Fresh Roster Calendar/Fetched Roster Calendar
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
public static void  getEmpDutyRosterCalendar(HttpServletRequest _request,EmpRosterGenerationFB _fb)	
	{

		//Roster Generated Days
		List rosterGenerationList=new ArrayList();
		Map rosterGeneratedMap=new LinkedHashMap();
		String rosterGenList="";
	
	
		//emp mappped list
		List empMappedList=new ArrayList();
		
		///Emp whose rosters have been prepared
		List empRosterList=new ArrayList();
		
		//emp who are not mapped and there roster has also  been prepared
		List empMappedWithRosterList=new ArrayList();
		
		
		//emp who are not mapped currently (mapped earlier) but there roster has  been prepared
		//(earlier ,hence further there roster cannot be prepared for future dates)
		List empNotMappedWithRosterList=new ArrayList(); 
		
		
		
		 //emp who are mapped but there roster has not been prepared yet
		List empMappedWithNoRosterList=new ArrayList();
		
		Map legendShiftMap=new HashMap();
		
		List shiftList=new ArrayList();
		List dayOffShiftList=new ArrayList();
		Map _holidayMap=new HashMap();
		Map shiftTimingsMap=new HashMap();

		//vo of employees dtls
		RosterDtlVO[] _rosterDtlVO=null;
		
		
		//vo of all mapped emp leave
		RosterDtlVO[] _empMonthlyLeave=null;
		
		//map of all mapped emp leave
		Map _empLeaveMap=new LinkedHashMap();
		
		
		//Variable used for getting the complete monthly roster of all the mapped employees 
		//excluding this roster Type and area code
		
		RosterDtlVO[] _monthlyEmpRosterDtlVO=null;
		
		Map _totalMonthlyEmpRosterMap=new LinkedHashMap();
		
		//Map used for showing an employee 's roster 
		//categorywise,areawise,
		
		Map _empRosterCatgAreaMap=new LinkedHashMap();
		
		
		
		//total Area ,Emp mapping 
		
		DutyRosterAreaEmployeeVO[] _areaEmpMappingVO=null;
		
		
		Map _areaEmpMap=new LinkedHashMap();
		
		
		String shiftId="";
		String shiftName="";
		
		
		//Roster generation dtl vo's
		
		RosterGenerationDtlVO[] _rosterGenerationDtlVO=null;
		
		//getting the emmployees  and ShiftType List list  from the session

		//empRosterList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_EMPLOYEES_WITH_ROSTER);
		empMappedList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE);
		shiftList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
		dayOffShiftList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_DAY_OFF_SHIFTS);	
		_holidayMap=(Map)_request.getSession().getAttribute(DutyRosterConfig.MAP_OF_MONTHLY_GAZETTED_HOLIDAYS);
		shiftTimingsMap=(Map)_request.getSession().getAttribute(DutyRosterConfig.MAP_OF_DAY_WISE_SHIFT_TIMINGS);
		_rosterDtlVO=(RosterDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);
		_monthlyEmpRosterDtlVO=(RosterDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.TOTAL_VO_OF_EMP_WISE_MONTHLY_ROSTER);
		_areaEmpMappingVO=(DutyRosterAreaEmployeeVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.TOTAL_VO_OF_AREA_EMP_MAPPING);
		_empMonthlyLeave=(RosterDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.TOTAL_VO_OF_ALL_MAPPED_EMP_LEAVE);
		_rosterGenerationDtlVO=(RosterGenerationDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.TOTAL_VO_OF_ROSTER_GENERATED_DAYS);
		
		//getting the duty Type whether [ 24x7 ,Official , Holiday ]
		String dutyType=_fb.getDutyType();
		
		
		/////////////////For Creating Array of shifts/////////////
		
		String shiftIdArray="";
		String shiftNameArray="";
		String shiftStartTimeArray="";
		String shiftEndTimeArray="";
		String shiftFullNameArray="";
		String shiftTypeArray="";
		String shiftTimingsArray="";

		/////////////////For Creating Day off shifts/////////////
		
		String shiftIdDayOff="";
		String shiftNameDayOff="";
		String shiftStartTimeDayOff="";
		String shiftEndTimeDayOff="";
		String shiftFullNameDayOff="";
		String shiftTypeDayOff="";
		
		
	/////////////////For Creating Defult Shifts /////////////
		
		String shiftIdDefault="";
		String shiftNameDefault="";
		String shiftStartTimeDefault="";
		String shiftEndTimeDefault="";
		String shiftFullNameDefault="";
		String shiftTypeDefault="";
		
		
		//First Creating a Default  GregorianCalendar
		Calendar defaultCalendar =new GregorianCalendar();	
		
		//Getting the current Date
		Date trialTime=new Date();
		
		//Setting the current Date into the default calendar
		defaultCalendar.setTime(trialTime);
		
		
		//Getting the current date ,month and year
		int currentYear=defaultCalendar.get(Calendar.YEAR);
		int currentMonth=defaultCalendar.get(Calendar.MONTH);
		int currentDate=defaultCalendar.get(Calendar.DATE);
		
		
		
		//for setting by default start date and end date
		//of the month
		
		String[] arrayOfMonths={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		
		
		int monthId=Integer.parseInt(_fb.getMonth())-1;

		
		String monthName=arrayOfMonths[monthId];
		
		// Creating a  GregorianCalendar based on the Year and Month Selected
		Calendar calendarSelected =new GregorianCalendar();	
		calendarSelected.set(Integer.parseInt(_fb.getYear()),Integer.parseInt(_fb.getMonth())-1, 1);
		

		int maxDaysofMonth=calendarSelected.getActualMaximum(Calendar.DAY_OF_MONTH);

		
		
		//making the generation start date and end date
		
		String genStartDate="01-"+monthName+"-"+_fb.getYear();
		String genEndDate=Integer.toString(maxDaysofMonth)+"-"+monthName+"-"+_fb.getYear();
		
		_fb.setStartDate(genStartDate);
		_fb.setEndDate(genEndDate);
		
		


	//first making the emp roster list from the array of roster dtl vo's got
	//so that it can be compared with emp mapped list
if(_rosterDtlVO!=null)
{	
	for(int i=0; i < _rosterDtlVO.length ; i++)
	{
	Entry objEntry=new Entry();
	objEntry.setValue(_rosterDtlVO[i].getEmpId());
	objEntry.setLabel(_rosterDtlVO[i].getEmpName());
	
if(!empRosterList.contains(objEntry))
	empRosterList.add(objEntry);

	}	
		
}



	/////////Here We are getting those emp list who are mapped ,and  there Roster has also been prepared /////////

	empMappedWithRosterList=EmpRosterGenerationUTLFunctions.getEmpMappedWithRosterList(empMappedList, empRosterList);




	/////////Here We are getting those emp list who wre  mapped Earlier(But currently they are removed )
	//,and there Roster has been prepared Earlier ,But there future Roster Cannot be prepared/////////

	empNotMappedWithRosterList=EmpRosterGenerationUTLFunctions.getEmpNotMappedWithRosterList(empMappedList, empRosterList);

	



	/////////Here We are getting those emp list who are mapped ,but there Roster has not been prepared Yet/////////

	empMappedWithNoRosterList=EmpRosterGenerationUTLFunctions.getEmpMappedWithNoRosterList(empMappedList, empRosterList);



	//here we are making a map from the total vo's got 
	//for  the complete monthly roster of all the mapped employees 
	//excluding the selected  roster Type and area code

if(_monthlyEmpRosterDtlVO!=null && _monthlyEmpRosterDtlVO.length > 0)
	_totalMonthlyEmpRosterMap=EmpRosterGenerationUTLFunctions.getMapofTotalMonthlyRostersOfAllMappedEmp(_monthlyEmpRosterDtlVO);




//here we are making a map from the total vo's got 
//for  the complete monthly roster of all the mapped employees 
//excluding the selected  roster Type and area code

if(_monthlyEmpRosterDtlVO!=null && _monthlyEmpRosterDtlVO.length > 0)
	_empRosterCatgAreaMap=EmpRosterGenerationUTLFunctions.getEmpRosterCatgAreaMap(_monthlyEmpRosterDtlVO);






//here we are making a map from the total vo's got 
//of the mapping between area and employee


if(_areaEmpMappingVO!=null && _areaEmpMappingVO.length > 0)
_areaEmpMap=EmpRosterGenerationUTLFunctions.getMapofAreaEmpMapping(_areaEmpMappingVO);



//here we are making a map from the total vo's got 
//of the mapping between area and employee


if(_empMonthlyLeave!=null && _empMonthlyLeave.length > 0)
_empLeaveMap=EmpRosterGenerationUTLFunctions.getMapofEmpLeave(_empMonthlyLeave);


//getting the roster generation list & map
if(_rosterGenerationDtlVO!=null && _rosterGenerationDtlVO.length >0 ){
	rosterGenerationList=EmpRosterGenerationUTLFunctions.getListofRosterGeneratedDays(_rosterGenerationDtlVO);
	rosterGeneratedMap=EmpRosterGenerationUTLFunctions.getMapofRosterGeneratedDays(_rosterGenerationDtlVO);
}


if(rosterGenerationList!=null)
	{
	for(int k=0; k < rosterGenerationList.size() ; k++)
		{
	
	if(k< rosterGenerationList.size()-1)
		rosterGenList+=rosterGenerationList.get(k)+"@";
	else
		rosterGenList+=rosterGenerationList.get(k);
	
		}	
	
	}


//Setting the lsit of days for whcih the roster is generated 

WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTER_GENERATED_DAYS, rosterGenerationList);

	//////////////////////Now Creating the Default shift to be shown /////////////////////////////////////////////

	shiftIdDefault="-1";
	shiftNameDefault="N A";
	shiftStartTimeDefault="-1";
	shiftEndTimeDefault="-1";
	shiftFullNameDefault="Not Assigned";
	shiftTypeDefault="-1";


	String[] arrayOfRosterDetails=_fb.getRosterId().split("@");

	//Creating the Day Off  Shift Type to be Selected 
	//for each employee for each day

if(arrayOfRosterDetails[2].equals(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN))
{
	Entry dayOffEntryShift = new Entry();

	if(dayOffShiftList!=null && dayOffShiftList.size()!=0)
	{	
	dayOffEntryShift=(Entry)dayOffShiftList.get(0);

	String[] dayOffshiftConcate=dayOffEntryShift.getValue().replace("^","@").split("@");

	shiftIdDayOff=dayOffshiftConcate[0];
	shiftNameDayOff=dayOffEntryShift.getLabel();
	shiftStartTimeDayOff=dayOffshiftConcate[1];
	shiftEndTimeDayOff=dayOffshiftConcate[2];
	shiftFullNameDayOff=dayOffshiftConcate[3];
	shiftTypeDayOff=dayOffshiftConcate[0];
	
	legendShiftMap.put(shiftNameDayOff, shiftNameDayOff);
	
	}

}


	//////////////////////Now Creating the Array of  shift to be shown //////////////////////////////////////////


	///////using default shifts
	shiftIdArray=shiftIdDefault+"^";
	shiftNameArray=shiftNameDefault+"^";
	shiftStartTimeArray=shiftStartTimeDefault+"^";
	shiftEndTimeArray=shiftEndTimeDefault+"^";
	shiftFullNameArray=shiftFullNameDefault+"^";
	shiftTypeArray=shiftTypeDefault+"^";


	//using day off shifts
if(arrayOfRosterDetails[2].equals(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN))
{
		
	
	shiftIdArray+=shiftIdDayOff+"^";
	shiftNameArray+=shiftNameDayOff+"^";
	shiftStartTimeArray+=shiftStartTimeDayOff+"^";
	shiftEndTimeArray+=shiftEndTimeDayOff+"^";
	shiftFullNameArray+=shiftFullNameDayOff+"^";
	shiftTypeArray+=shiftTypeDayOff+"^";
}

	/////////using dynamic shifts coming from database

	//For Creating arrays for the Dynamic Shift Types,with there StartTime and EndTime
	
if(shiftList!=null && shiftList.size()!=0)
{		
	
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
	
	legendShiftMap.put(shiftNameConcate[0],shiftNameConcate[3]);
	
	}
}

	//making the shift timings map if the Day shift exists

	if(shiftTimingsMap!=null && shiftTimingsMap.size()!=0)
	{
		
	//getting the values from the map and concatinating them	
	for(int i=1; i <=7 ;i++ )
		shiftTimingsArray+=shiftTimingsMap.get(Integer.toString(i))+"^";


	}	
	
	
	
//////////////////////////////Creating the Days ////////////////////////////////////////////////////	
	
	
	//Creating a string Buffer for Creating a New Calendar on the basis of employees mapped
	///for the corresponding area type,areaCode and rosterType	
		StringBuilder strCal=new StringBuilder(); 
		
				
		strCal.append("<table width=\"100%\" border=\"1\" cellpadding=\"0\">");
		
		
				
		
		strCal.append("<tr>");
		strCal.append("<td width=\"1%\"  class=\"tdfonthead\" ><div align='center'>") ;
		strCal.append("<input type=\"checkbox\" id=\"selectAll\" tabindex=\"1\"  name=\"selectAll\"  onclick=\"selectAllEmployees(this)\" size=\"1\" onmouseover=\"selectAllEmpTitle(this)\" onmousemove=\"selectAllEmpTitle(this)\"></div></td>");
		
		strCal.append("<td width=\"3%\"  class=\"tdfonthead\" >") ;
		strCal.append("<div align='center'>REL</div></td>");
		//strCal.append("<div align='center'><input type=\"checkbox\" id=\"selectAllRelivers\" tabindex=\"1\"  name=\"selectAllRelivers\"  onclick=\"selectAllReliverEmp(this)\" size=\"1\" onmouseover=\"selectAllReliverEmpTitle(this)\" onmousemove=\"selectAllReliverEmpTitle(this)\">R</div></td>");
		

		strCal.append("<td  class=\"tdfonthead\" width=\"35%\" wrap=\"nowrap\" valign='center' ><div align='center' wrap=\"nowrap\"><font color='#FFEBD5'>.............</font>Employees(Designation)<font color='#FFEBD5'>..........</font></div>");
		strCal.append("<img class=\"button\" id=\"expandCollapseRosterId\" src=\"/HIS/hisglobal/images/avai/pl_small.gif\" tabindex=\"1\" style=\"cursor:pointer;\" 	onmouseover=\"showTitle(this)\" onmousemove=\"showTitle(this)\" onclick=\"expandCollapseRoster(this)\" >"); 
			
		strCal.append("</td>");

		
	//Creating the No. Of Days for the Calendar	
	for(int i=1 ; i <= maxDaysofMonth ;i++)
	{
		
		// Creating a  GregorianCalendar based on the Year and Month and Day Selected 
		Calendar dailyCalendar =new GregorianCalendar();	
		dailyCalendar.set(Integer.parseInt(_fb.getYear()),Integer.parseInt(_fb.getMonth())-1, i);
		
		int dayCode=dailyCalendar.get(Calendar.DAY_OF_WEEK);
		
		//System.out.println("day code--------"+dayCode);
		String day=EmpRosterGenerationUTLFunctions.getDayFromDayCode(dayCode);
			
		
		String holidayColor=DutyRosterConfig.HOLIDAY_COLOR;

		
		String tdId="dateTdId"+i;
		
		//Declaring the style of the display to be block by default to be shown
		String tdDisplay="";
		
		
		//if the days are generated 
		//then it's display will be hidden 
		if(rosterGenerationList.contains(i))
			tdDisplay="none;";
			
		
		if(rosterGeneratedMap.containsKey(i)){
		
			String expandTdId="expandTdId"+rosterGeneratedMap.get(i);
			String expandCollapseId="expandCollapseId_"+rosterGeneratedMap.get(i);
			String hiddenBoxId="hiddenBoxId_"+rosterGeneratedMap.get(i);
			
			strCal.append("<td width=\"2%\"  id=\""+expandTdId+"\" align=\"center\" class=\"tdfont\"  ><div align=\"center\">");
			strCal.append("<input type='hidden' id='"+hiddenBoxId+"' value='1' >");
			strCal.append("<img class=\"button\" id=\""+expandCollapseId+"\" src=\"/HIS/hisglobal/images/avai/pl_small.gif\" tabindex=\"1\" style=\"cursor:pointer;\" 	onmouseover=\"showTitleRange(this)\" onmousemove=\"showTitleRange(this)\" onclick=\"expandCollapseRosterRange(this)\" >");
			strCal.append("</div></td>");
			
		}
		
		
//if the day is sunday or it is a holiday ,hence it will be shown in dieerent color		
		if(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(Integer.toString(i)))
			strCal.append("<td width=\"2%\"  id=\""+tdId+"\" align=\"center\" class=\"tdfont\" style='display:"+tdDisplay+" background-color: "+holidayColor+";'    ><div align=\"center\">"+i+"("+day+")"+"</div></td>");
			else		
			strCal.append("<td width=\"2%\"  id=\""+tdId+"\" align=\"center\" class=\"tdfont\" style='display:"+tdDisplay+" ' ><div align=\"center\">"+i+"("+day+")"+"</div></td>");
	}	
	

	//now closing the row
	strCal.append("</tr>");
	
	
	
///////////////////////////////////Starting  of Making of Roster////////////////////////////////////////////

	//   Case1 :  Emp Roster Prepared     + Emp Mapping -------------------empMappedWithRosterList
	//   Case2 :  Emp Roster Prepared 	  + Emp Mapping Removed----------empNotMappedWithRosterList
	//   Case3 :  Emp Roster Not Prepared + Emp Mapping------------------empMappedWithNoRosterList 




	//Creating the Dynamic Calendar based on the the employees mapped 
	//for the corresponding area type,areaCode and rosterType
	//and the Current Year and Month Selected
	
	


	//iterating through the total No of Employees whose Roster Has been Prepared 
	//and they are also mapped currently from the list of empMappedWithRosterList

	//initally the rowId will start From 0
	int rowId=0;
	
if(empMappedWithRosterList!=null && empMappedWithRosterList.size()!=0)
	EmpRosterGenerationUTLFunctions.getPreparedRoster(_request, _fb, strCal, empMappedWithRosterList, maxDaysofMonth, _rosterDtlVO, dutyType, _holidayMap,DutyRosterConfig.MAPPED_EMP_WITH_ROSTER,rowId,_totalMonthlyEmpRosterMap,_areaEmpMap,_empLeaveMap,rosterGenerationList,rosterGeneratedMap);






//iterating through the total No of Employees whose Roster Has nOT been Prepared 
//and they are   mapped currently from the list of empMappedWithNoRosterList

	//new Row Id will be made by adding empMappedWithRosterList.size() to it 
if(empMappedWithRosterList!=null && empMappedWithRosterList.size()!=0)
		rowId+=empMappedWithRosterList.size();

if(empMappedWithNoRosterList!=null && empMappedWithNoRosterList.size()!=0)	
	EmpRosterGenerationUTLFunctions.getPreparedRoster(_request, _fb, strCal, empMappedWithNoRosterList, maxDaysofMonth, _rosterDtlVO, dutyType, _holidayMap,DutyRosterConfig.MAPPED_EMP_WITH_NO_ROSTER,rowId,_totalMonthlyEmpRosterMap,_areaEmpMap,_empLeaveMap,rosterGenerationList,rosterGeneratedMap);
		
	
	



	
	//new Row Id will be made by adding empNotMappedWithRosterList.size() to it 
if(empMappedWithNoRosterList!=null && empMappedWithNoRosterList.size()!=0)	
	rowId+=empMappedWithNoRosterList.size();


//iterating through the total No of Employees whose Roster Has been Prepared 
//and they are Not  mapped currently from the list of empNotMappedWithRosterList
	
if(empNotMappedWithRosterList!=null && empNotMappedWithRosterList.size()!=0)	
	EmpRosterGenerationUTLFunctions.getPreparedRoster(_request, _fb, strCal, empNotMappedWithRosterList, maxDaysofMonth, _rosterDtlVO, dutyType, _holidayMap,DutyRosterConfig.UN_MAPPED_EMP_WITH_ROSTER,rowId,_totalMonthlyEmpRosterMap,_areaEmpMap,_empLeaveMap,rosterGenerationList,rosterGeneratedMap);
	





	//Creating the Hidden Fields for the array of Shift Types,Start Time,End Time
	//for the corresponding Roster Types

	//getting the month into the format of (1-12) from (0-11)
	currentMonth=currentMonth+1;


	//shift details in hidden text boxes used for changing the shift type 
	
		strCal.append("<tr><td>");	
		strCal.append("<input type=\"hidden\" id=\"shiftIdArray\"  name=\"shiftIdArray\" value=\""+shiftIdArray+"\">");  
		strCal.append("<input type=\"hidden\" id=\"shiftNameArray\"  name=\"shiftNameArray\" value=\""+shiftNameArray+"\">"); 
		strCal.append("<input type=\"hidden\" id=\"shiftStartTimeArray\"  name=\"shiftStartTimeArray\" value=\""+shiftStartTimeArray+"\">"); 
		strCal.append("<input type=\"hidden\" id=\"shiftEndTimeArray\"  name=\"shiftEndTimeArray\" value=\""+shiftEndTimeArray+"\">");
		strCal.append("<input type=\"hidden\" id=\"shiftFullNameArray\"  name=\"shiftFullNameArray\" value=\""+shiftFullNameArray+"\">");	
		strCal.append("<input type=\"hidden\" id=\"shiftTypeArray\"  name=\"shiftTypeArray\" value=\""+shiftTypeArray+"\">");
		strCal.append("<input type=\"hidden\" id=\"shiftTimingsArray\"  name=\"shiftTimingsArray\" value=\""+shiftTimingsArray+"\">");  
		strCal.append("<input type=\"hidden\" id=\"rosterGenList\"  name=\"rosterGenList\" value=\""+rosterGenList+"\">");
		
	//max days in a month used for saving data 
		
		strCal.append("<input type=\"hidden\" id=\"maxDaysofMonth\" name=\"maxDaysofMonth\" value=\""+maxDaysofMonth+"\">"); 

		
	//calculating the total size	
	int sizeOfEmpMappedWithRosterList=0;
	int sizeOfEmpNotMappedWithRosterList=0;
	int sizeOfEmpMappedWithNoRosterList=0;
	int totalSizeOfEmpList=0;
	
	
	if(empMappedWithRosterList!=null)
		sizeOfEmpMappedWithRosterList=empMappedWithRosterList.size();
	
	if(empNotMappedWithRosterList!=null)
		sizeOfEmpNotMappedWithRosterList=empNotMappedWithRosterList.size();
	
	if(empMappedWithNoRosterList!=null)
		sizeOfEmpMappedWithNoRosterList=empMappedWithNoRosterList.size();
	
	
		totalSizeOfEmpList=sizeOfEmpMappedWithRosterList+sizeOfEmpNotMappedWithRosterList+sizeOfEmpMappedWithNoRosterList;

	// max no of employee used for saving data	
		strCal.append("<input type=\"hidden\" id=\"maxNoOfEmployees\"  name=\"maxNoOfEmployees\" value=\""+totalSizeOfEmpList+"\">"); 
		
		//setting the current year ,month, date for validation
		_fb.setCurrentYear(Integer.toString(currentYear));
		_fb.setCurrentMonth(Integer.toString(currentMonth));
		_fb.setCurrentDate(Integer.toString(currentDate));
		
		
			
		strCal.append("</td></tr>");
		strCal.append("</table>");
		
		
		
		
	 
		
		
		_fb.setCalendar(strCal.toString()); 
	

		WebUTIL.setAttributeInSession(_request, DutyRosterConfig.TOTAL_MAP_OF_EMP_AREA_MAPPING, _areaEmpMap);
		
		WebUTIL.setAttributeInSession(_request, DutyRosterConfig.TOTAL_MAP_OF_EMPWISE_MONTHLY_ROSTERS, _empRosterCatgAreaMap);

		WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAP_OF_LEGENDS_SHIFT, legendShiftMap);
		
	//	WebUTIL.setAttributeInSession(_request, DutyRosterConfig.MAX_DAYS_OF_A_MONTH_IN_EMPLOYEE_DUTY_ROSTER, maxDaysofMonth);


		

		
	}
	


/**For Making the array of Vo's to be saved
 *
 * 
 * 
 * @param arrayOfEmployees
 * @param _request
 * @param _fb
 * @return  RosterDtlVO[]
*/



public static RosterDtlVO[] getRosterDtlVOsToBeSaved(String[] arrayOfEmployees,HttpServletRequest _request,EmpRosterGenerationFB _fb)

{
	RosterDtlVO[] _rosterDtlVO=new RosterDtlVO[arrayOfEmployees.length];
		
	for(int i=0 ;i< arrayOfEmployees.length ;i++)
	{
		_rosterDtlVO[i]=new RosterDtlVO();
		             
		String empDetailsConcated=arrayOfEmployees[i].replace("^", "@");
		String rosterIdsConcated=_fb.getRosterId().replace("^", "@");
		
		
		String[] arrayOfRosterDetails=rosterIdsConcated.split("@");                
		
		String[] arrayOfVODetails=empDetailsConcated.split("@");
		String month=Integer.toString(Integer.parseInt(_fb.getMonth())+1);
		
		String startDateTime=arrayOfVODetails[1]+"/"+_fb.getMonth()+"/"+_fb.getYear()+" "+arrayOfVODetails[3]+":00";
		String endDateTime=arrayOfVODetails[1]+"/"+_fb.getMonth()+"/"+_fb.getYear()+" "+arrayOfVODetails[4]+":00";		
		
		
		_rosterDtlVO[i].setRosterId(arrayOfRosterDetails[0]);
		_rosterDtlVO[i].setAreaCode(_fb.getAreaCode().split("@")[0]);
		_rosterDtlVO[i].setAreaTypeCode(arrayOfRosterDetails[1]);             
		_rosterDtlVO[i].setEmpId(arrayOfVODetails[0]);
		_rosterDtlVO[i].setShiftId(arrayOfVODetails[2]);
		_rosterDtlVO[i].setStartDateTime(startDateTime);
		_rosterDtlVO[i].setEndDateTime(endDateTime);
		
		
		
	}
	
	WebUTIL.setAttributeInSession(_request, DutyRosterConfig.EMPLOYEE_WISE_VO_ROSTER_DETAILS, _rosterDtlVO);
	return _rosterDtlVO;

	
}


/**For getting the list of employees to be updated
 * 
 * @param _request
 * @param _fb
 * @return
 */


public static String getEmpListToBeUpdated(HttpServletRequest _request,EmpRosterGenerationFB _fb){

	String[] selectEmp=null;	
	selectEmp=_fb.getSelectEmp();
	StringBuilder empList=new StringBuilder();

	for(int i=0; i < selectEmp.length ; i++)
	{

	if(i!=selectEmp.length-1){
		empList.append("'");//empList+="'"+selectEmp[i]+"'"+",";
		empList.append(selectEmp[i]);
		empList.append("'");
		empList.append(",");
		}
		else{
			empList.append("'");//empList+="'"+selectEmp[i]+"'";	
			empList.append(selectEmp[i]);
			empList.append("'");
		}
	}
		
	return empList.toString();

}


/**For getting the Days List To be updated
 * 
 * @param _request
 * @param _fb
 * @return
 */


public static String getDaysListToBeUpdated(HttpServletRequest _request,EmpRosterGenerationFB _fb)

{
	
	//Geting the list of roster generated days 

	List generatedList=new ArrayList();
	
//in case we want to drop the roster 
//then we don't need to know about the generated roster
	
if(!_fb.getHmode().equals("DROP_ROSTER"))
	generatedList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_ROSTER_GENERATED_DAYS);
	
	
	
//getting the current years ,month and dates	
int currentYear=Integer.parseInt(_fb.getCurrentYear());
int currentMonth=Integer.parseInt(_fb.getCurrentMonth());
int currentDate=Integer.parseInt(_fb.getCurrentDate());


//getting the selected years ,month and dates
int selectedYear=Integer.parseInt(_fb.getYear());
int selectedMonth=Integer.parseInt(_fb.getMonth());
int maxDaysofMonth=Integer.parseInt(_fb.getMaxDaysofMonth());


StringBuilder daysList=new StringBuilder();

boolean check=false; 
int startingDate=0;

///now checking whether the selected  year/month is greater than the current year/month
//if greater then the list of days will be from the starting of the month 
if(selectedYear > currentYear || selectedMonth > currentMonth)
	{
	startingDate=1;
	}	
	else
		// else if the current year and month is selected than the list 
		 // will start from the current date to the end date of the month		
if(selectedYear==currentYear && selectedMonth==currentMonth)
	{
	startingDate=currentDate;	
	}



	

for(int i=startingDate; i <= maxDaysofMonth ; i++)
{

//if the day doesn't exsists in the generated list,then only it can be updated
	
if(!generatedList.contains(i) )
    {	
	if(i!=maxDaysofMonth)
		daysList.append(i+","); 	//daysList+=i+",";
		else
		daysList.append(i+"");     	//daysList+=i;		
     }
}



//if we get the last index element to be ","  then delete it 
if(daysList.lastIndexOf(",")==daysList.length()-1)
	daysList.deleteCharAt(daysList.length()-1);

//checking for whether there is no days list to be updated
//then we put -1 date to update
if(daysList.equals(""))
	daysList.append("-1");


return daysList.toString();

}


/**For getting the Year List 
 * 
 * @param _request
 * @param _fb
 * @return
 */


public static void  getYearList(HttpServletRequest _request,EmpRosterGenerationFB _fb)

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
	//System.out.println("Entry: " + objEntry);
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



public static void  getMonthsList(HttpServletRequest _request,EmpRosterGenerationFB _fb)

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
	//System.out.println("Entry: " + objEntry);
	monthsList.add(objEntry);
	
		}
	_fb.setMonth(Integer.toString(currentMonth+1));
			
	WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_MONTHS_EMPLOYEE_DUTY_ROSTER_MASTER, monthsList);

	
}

public static StringBuilder getPreparedRoster(HttpServletRequest _request,EmpRosterGenerationFB _fb,StringBuilder strCal,List empList,int maxDaysofMonth,RosterDtlVO[] _rosterDtlVO,String dutyType,Map _holidayMap,int empCase,int rowId,Map _totalMonthlyEmpRosterMap,Map _areaEmpMap,Map _empLeaveMap,List rosterGenerationList,Map rosterGeneratedMap)
{
	
	Map empReliverMap=EmpRosterGenerationUTLFunctions.getMapOfEmpToBeRelived(_request, _fb);
	
	List empReliverList=new ArrayList();
	

	//For getting the list of holidays and Sundays
	String holidayList="";
	
	String shiftNameButtonColor=DutyRosterConfig.SHIFT_NAME_ON_BUTTON_COLOR;
	
	
/////////////////For Creating Default Shifts /////////////
	
	String shiftIdDefault="";
	String shiftNameDefault="";
	String shiftStartTimeDefault="";
	String shiftEndTimeDefault="";
	String shiftFullNameDefault="";
	String shiftTypeDefault="";
	
//////////////////////Now Creating the Default shift to be shown /////////////////////////////////////////////

	shiftIdDefault="-1";
	shiftNameDefault="N A";
	shiftStartTimeDefault="-1";
	shiftEndTimeDefault="-1";
	shiftFullNameDefault="Not Assigned";
	shiftTypeDefault="-1";
	
	
	
	
	
	
	for(int i=rowId ; i <rowId+empList.size();i++)
	{
		Entry objEntryEmp = new Entry();
		String empId="";
		String empName="";
		
		objEntryEmp=(Entry)empList.get(i-rowId);
		empId=objEntryEmp.getValue();
		empName	=objEntryEmp.getLabel();
		
		Map dayMap=new HashMap();
		
		
		if(_totalMonthlyEmpRosterMap.containsKey(empId))
			dayMap=(Map)_totalMonthlyEmpRosterMap.get(empId);

		
		
		
		
		
		///////check Box Name
		String checkBoxName="selectEmp";
		
		
		String reliverBoxName="reliverEmp";
		
		
		/////Id of check Box
		String  checkBoxId=checkBoxName+"_"+Integer.toString(i+1);
		
		String  reliverBoxId=reliverBoxName+"_"+Integer.toString(i+1);
		
		strCal.append("<tr>");
		
		
		String empAreaMapping="";
		String []arrayOfAreas=null;
		
		//getting the list of areas mapped
		//and arrayOfAreas
		
		if(_areaEmpMap.containsKey(empId))
			{
			empAreaMapping=(String)_areaEmpMap.get(empId);
			arrayOfAreas=empAreaMapping.split(",");
			}

		
		
		//declaring a hyperlink flag
		boolean hyperLinkFlag=false;

//if there exsists a daymap for the employee,
//i.e there exsists a duty for any other roster or it has been Assigned More than 1 Area		
if((dayMap!=null && dayMap.size() > 0 ) || (arrayOfAreas!=null && arrayOfAreas.length > 1))
			hyperLinkFlag=true;
		
		
		
		
		
		
		List _empLeaveList=new ArrayList();
		
		String _leaveListEmp="";
		
		if(_empLeaveMap.containsKey(empId))
			_empLeaveList=(List)_empLeaveMap.get(empId);
	
//concatinating the days of leave in a string 
//to set it into a hidden textbox ,so that it can be used in javascript
		
for(int k=0; k < _empLeaveList.size() ; k++)
{
if(k < _empLeaveList.size()-1)	
	_leaveListEmp+=_empLeaveList.get(k)+"@";
	else
	_leaveListEmp+=_empLeaveList.get(k);	
}
		
if(_leaveListEmp==null || _leaveListEmp.equals(""))
	_leaveListEmp="NA";
	





String checkedReliver="";


if(empReliverMap.containsKey(empId))
	checkedReliver=" checked='checked' ";




	/////creating the check box depending upon the emp case
		
		
		

//In MAPPED_EMP_WITH_ROSTER case all the employees will be selected by default		
		if(empCase==DutyRosterConfig.MAPPED_EMP_WITH_ROSTER )		
			{
			strCal.append("<td  class=\"tdfont\"><div align=\"center\"><input type=\"checkbox\" id=\""+checkBoxId+"\" tabindex=\"1\"  name=\""+checkBoxName+"\"  onclick=\"selectEmployee(this,"+"'"+_leaveListEmp+"'"+")\" size=\"1\"  value=\""+empId+"\" checked=\"checked\"></div></td>");
			strCal.append("<td  class=\"tdfont\"><div align=\"center\"><input type=\"checkbox\" id=\""+reliverBoxId+"\" tabindex=\"1\"  name=\""+reliverBoxName+"\"   size=\"1\"  value=\""+empId+"\" "+checkedReliver+" ></div></td>");
			}
			else
		
//In UN_MAPPED_EMP_WITH_ROSTER case also the employees will be selected by default		
			
if(empCase==DutyRosterConfig.UN_MAPPED_EMP_WITH_ROSTER)	
		{
		strCal.append("<td  class=\"tdfont\"><div align=\"center\"><input type=\"checkbox\" id=\""+checkBoxId+"\" tabindex=\"1\"  name=\""+checkBoxName+"\"  onclick=\"selectEmployee(this,"+"'"+_leaveListEmp+"'"+")\" size=\"1\"  value=\""+empId+"\" disabled=\"true\"></div></td>");
		strCal.append("<td  class=\"tdfont\"><div align=\"center\"><input type=\"checkbox\" id=\""+reliverBoxId+"\" tabindex=\"1\"  name=\""+reliverBoxName+"\"   size=\"1\"  value=\""+empId+"\" disabled=\"true\"></div></td>");
		}
		else		
		
//In MAPPED_EMP_WITH_NO_ROSTER case all the employees will not  be selected by default
			
if(empCase==DutyRosterConfig.MAPPED_EMP_WITH_NO_ROSTER)		
		{
		strCal.append("<td  class=\"tdfont\"><div align=\"center\"><input type=\"checkbox\" id=\""+checkBoxId+"\" tabindex=\"1\"  name=\""+checkBoxName+"\"  onclick=\"selectEmployee(this,"+"'"+_leaveListEmp+"'"+")\" size=\"1\"  value=\""+empId+"\" ></div></td>");
		strCal.append("<td  class=\"tdfont\"><div align=\"center\"><input type=\"checkbox\" id=\""+reliverBoxId+"\" tabindex=\"1\"  name=\""+reliverBoxName+"\"   size=\"1\"  value=\""+empId+"\" ></div></td>");
		}	
		
String empNameDivId="empNameDivId_"+(i+1);
		
//if we get the hyperlink flag to be true 
//then only there will be a hyperlink else not		
if(hyperLinkFlag==true)
		strCal.append("<td  class=\"tdfont\" wrap=\"nowrap\"><div align=\"center\" wrap=\"nowrap\" id=\""+empNameDivId+"\" title=\""+empName+"\" ><a onclick=\"openDependentPopup('EmpDutyRoster.cnt?hmode=GET_TOTAL_EMPLOYEE_ROSTER&&empId="+empId+" ',event,300,750);\"  style=\"cursor: pointer;\">"+empName+"</a></div></td>");
else
		strCal.append("<td  class=\"tdfont\"  wrap=\"nowrap\"><div align=\"center\" wrap=\"nowrap\" id=\""+empNameDivId+"\" title=\""+empName+"\" >"+empName+"</div></td>");

	//iterating through the total No of Days for the corresponding Year and month
	//and creating the individual Shift butons for each employee and each day	
		
		for(int j=1 ; j <= maxDaysofMonth ;j++)
			
		{
			
			
			// Creating a  GregorianCalendar based on the Year and Month and Day Selected 
			Calendar dailyCalendar =new GregorianCalendar();	
			dailyCalendar.set(Integer.parseInt(_fb.getYear()),Integer.parseInt(_fb.getMonth())-1, j);
			
			int dayCode=dailyCalendar.get(Calendar.DAY_OF_WEEK);
			
			//System.out.println("day code--------"+dayCode);
			
			
if(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(Integer.toString(j)))
		holidayList+=Integer.toString(j)+"@";
				
	String disableButton="";		



	//Declaring the style of the display to be block by default to be shown
	String tdDisplay="";
	
	
	//if the days are generated 
	//then it's display will be hidden 
	if(rosterGenerationList.contains(j))
		tdDisplay="none;";

	
			
			Map shiftMap=new HashMap();	
		String shiftList="";
			
			if(dayMap.containsKey(Integer.toString(j)))
				shiftMap=(Map)dayMap.get(Integer.toString(j));
			
			
			if(shiftMap!=null && shiftMap.size() > 0)
				shiftList=(String)shiftMap.get("shiftId");
			
			
			
				
			
			int temp=i+1;
			String buttonId=Integer.toString(temp)+"@"+Integer.toString(j);
			String hiddenId=Integer.toString(temp)+"@"+Integer.toString(j)+"@"+"1";
			String buttonTdId="buttonTdId"+Integer.toString(temp)+"@"+Integer.toString(j);
			String expandTdButtonId="expandTdButtonId"+Integer.toString(temp)+"@"+Integer.toString(j);
			
			//System.out.println("buttonId--->"+buttonId); 
			
			String backgroundColor="";
			String buttonColor="";
			String buttonName="";
			String buttonNameDefault="";
			String shiftButtonDisplay="";
			String monthDate="";
			
			
//Array of shift colors for different shift Types
			
//			0-->Day Off----------------------->window
//			1-->Morning----------------------->orange
//			2-->Evening----------------------->aqua
//			3-->Night------------------------->#A6A38C
//			4-->DayShift---------------------->yellow
//			5-->Early Morning Shift----------->#EAE9BB

			
			
			
			String[] arrayOfShiftColors={DutyRosterConfig.DAY_OFF_COLOR,DutyRosterConfig.MORNING_SHIFT_TYPE_COLOR,DutyRosterConfig.EVENING_SHIFT_TYPE_COLOR,DutyRosterConfig.NIGHT_SHIFT_TYPE_COLOR,DutyRosterConfig.DAY_SHIFT_TYPE_COLOR,DutyRosterConfig.EARLY_MORNING_SHIFT_TYPE_COLOR};
			
			
			
			
			
//if any shift is already assigned to this employee then 
//it's color will change to the shift color else 
//In case of more than 1 shift assigned,it's color will change to Red			
//hence it can see it from the hyperlink click			
			
if(shiftList!=null && !shiftList.equals(""))	
{		
			if(shiftList.contains("#"))				
				buttonColor="Red";
			else///else it will be assigned a color depending upon the shiftType 
				{
				String[] shiftArray=shiftList.split("@");
				buttonColor=arrayOfShiftColors[Integer.parseInt(shiftArray[0])];
				}
}			
	

//in case the employee is on leave 
//hence it's button will be disabled and background color will change to pink

if(_empLeaveList.contains(j))
	{
	disableButton="disabled='disabled'";
	buttonColor=DutyRosterConfig.EMP_LEAVE_COLOR;
	}


//changing the background color in case of sunday or gazetted holiday
if(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(Integer.toString(j)))
		backgroundColor=DutyRosterConfig.HOLIDAY_COLOR;				
			
			

//Changing the background color in case of Roster is Generated 
if(rosterGenerationList.contains(j))
		{
		backgroundColor=DutyRosterConfig.ROSTER_GENERATED_COLOR;				
		disableButton="disabled='disabled'";
		}	

			
			
			//MAKING THE DEFAULT SHIFT BUTTON NAME AND VALUE OF THE BUTTON	
			
					//			  1			 2			  3						4					 5					        	6				     7				   8		
	if(j < 10)		//			Empid   +   Date   +    shiftId     +		startTime        +     EndTime          +           ShifTFull Name   +    ShiftType    +    DayCode  		
				buttonNameDefault=empId+"^"+"0"+j+"^"+shiftIdDefault+"^"+shiftStartTimeDefault+"^"+shiftEndTimeDefault+"^"+shiftFullNameDefault+"^"+shiftTypeDefault+"^"+dayCode;
			else
				buttonNameDefault=empId+"^"+j+"^"+shiftIdDefault+"^"+shiftStartTimeDefault+"^"+shiftEndTimeDefault+"^"+shiftFullNameDefault+"^"+shiftTypeDefault+"^"+dayCode;
			
			//System.out.println("buttonNameDefault --->"+buttonNameDefault);
			
		
			
			
			if(j < 10)
				monthDate="0"+Integer.toString(j);
			else
				monthDate=Integer.toString(j);
		
		
		//iterating through the total length of roster list of vo's  got from the database
		//and fetching the shift id
if(_rosterDtlVO!=null)
{	
	for(int k=0;k < _rosterDtlVO.length ; k++)
	{


		


	//now we are matching the empid and day got from the month
	//to the emloyees mapped and the days of the month	

	if(_rosterDtlVO[k].getEmpId().equals(empId) &&_rosterDtlVO[k].getStartDateTime().equals(monthDate))
					{
							//			  1							   2			 						 3									   4									   5								6									    7					    8
							//			Empid   + 					  Date   +   						 shiftId     +							startTime        +    					 EndTime          +   			ShifTFull Name   +        				 ShiftType    +   		  DayCode	
				
					buttonName=_rosterDtlVO[k].getEmpId()+"^"+_rosterDtlVO[k].getStartDateTime()+"^"+_rosterDtlVO[k].getShiftId()+"^"+_rosterDtlVO[k].getShiftStartTime()+"^"+_rosterDtlVO[k].getShiftEndTime()+"^"+_rosterDtlVO[k].getShiftName()+"^"+_rosterDtlVO[k].getShiftType()+"^"+dayCode;	
					shiftButtonDisplay=_rosterDtlVO[k].getShiftShortName();
					}
					
					
	}

}
			
		
		
			
			
		//IF WE HAVE NOT GOT ANY buttonName ID AFTER ITERATING THROUGH THE TOTAL VO's OF EMP ROSTER
		///THEN WE ARE SETTING A DEFAULT SHIFT BUTTON NAME AND shiftButtonDisplay
		
		
		if(buttonName.equals("") || buttonName==null)
		{
			buttonName=buttonNameDefault;
			shiftButtonDisplay=shiftNameDefault;
			
			
		}
			
		
		if(rosterGeneratedMap.containsKey(j)){
		
			
			strCal.append("<td width=\"2%\"  id=\""+expandTdButtonId+"\" align=\"center\" class=\"tdfont\"  ><div align=\"center\">");
			strCal.append("</div></td>");
			
		}
		
			
//if the case is of  MAPPED_EMP_WITH_ROSTER	
//you can create your further rosters		
///////////////////////////////////////////////Case 1//////////////////////////////////////////////////////////			
if(empCase==DutyRosterConfig.MAPPED_EMP_WITH_ROSTER )			
{			
			
			
			
			
			
			//in case the roster is prepared for 24x7 duty type ,hence all the days button will be visible 
			
	if(dutyType.equals(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN))	
		{
			
			strCal.append("<td align=\"center\" style='display: "+tdDisplay+" background-color: "+backgroundColor+"'  id=\""+buttonTdId+"\"   ><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftType(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\"  style=\"color: "+shiftNameButtonColor+"; background-color: "+buttonColor+";\"  '"+disableButton+"' >");
			strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\"> </td>");
		}
			else//in case the roster is prepared for Offical days  ,hence  sunday / gazetted holidays  days button will be disabled rest will be enabled
if(dutyType.equals(DutyRosterConfig.DUTY_TYPE_OFFICIAL_DAYS))	
	{	
		
		//checking whether the current day is sunday ,then making it disabled
									//OR
		//checking whether the current day is gazetted ,then making it disabled
		

		
		if(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(Integer.toString(j)))
		{
		
			strCal.append("<td align=\"center\" class=\"tdfont\" style='display: "+tdDisplay+" background-color: "+backgroundColor+"' id=\""+buttonTdId+"\"><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftType(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\"  disabled=\"true\"  style=\" color: "+shiftNameButtonColor+"; background-color: "+buttonColor+";\">");
			strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\"> </td>");
		 
		}
		else //if it is neither sunday , nor gazetted then  it is an offical day hence enabled
  		{	  
	
	
				strCal.append("<td align=\"center\" style='display:"+tdDisplay+" background-color: "+backgroundColor+"' id=\""+buttonTdId+"\"><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftType(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\"  style=\" color: "+shiftNameButtonColor+"; background-color: "+buttonColor+";\" '"+disableButton+"'>");
				strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\"> </td>");
  		}
	}	
else//in case the roster is prepared for Holiday ,hence only sunday / gazetted holidays  days button will be enabled rest will be disabled
if(dutyType.equals(DutyRosterConfig.DUTY_TYPE_SUNDAY_HOLIDAY))	
	{	
			
			//checking whether the current day is sunday ,then making it enabled
									//OR
			//checking whether the current day is gazetted ,then making it enabled
			
			
			if(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(Integer.toString(j)))
				{
				strCal.append("<td align=\"center\" class=\"tdfont\" style='display:"+tdDisplay+" background-color: "+backgroundColor+"' id=\""+buttonTdId+"\"><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftType(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\"  style=\" color: "+shiftNameButtonColor+"; background-color: "+buttonColor+";\"  '"+disableButton+"'>");
				strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\">  </td>");
				
				}
				 else //if it is neither sunday , nor gazetted then  it is an offical day hence enabled
			  {	  

			   strCal.append("<td align=\"center\" style='display:"+tdDisplay+" background-color: "+backgroundColor+"'  id=\""+buttonTdId+"\"><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftType(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\" disabled=\"true\" style=\"color: "+shiftNameButtonColor+"; background-color: "+buttonColor+";\">");
			   strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\"></td>");
			  }
			
	}	


		
	
		
		}//if of MAPPED_EMP_WITH_ROSTER closed
else
	//if the case is of  MAPPED_EMP_WITH_NO_ROSTER
	//you can create your further rosters		
///////////////////////////////////////////////Case 2//////////////////////////////////////////////////////////	
if( empCase==DutyRosterConfig.MAPPED_EMP_WITH_NO_ROSTER)			
	{			
				
	//all buttons will be disabled by default
	
			
	    strCal.append("<td align=\"center\" style='display:"+tdDisplay+" background-color: "+backgroundColor+"' id=\""+buttonTdId+"\" ><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftType(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\"  style=\"color: "+shiftNameButtonColor+"; background-color: "+buttonColor+";\" disabled=\"true\">");
		strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\"> </td>");

						
	}	
	
	//if of MAPPED_EMP_WITH_ROSTER closed 
else
	//if the case is of  APPED_EMP_WITH_ROSTER	&& MAPPED_EMP_WITH_NO_ROSTER
	//you cannot  create your further rosters		
///////////////////////////////////////////////Case 3//////////////////////////////////////////////////////////			
if(empCase==DutyRosterConfig.UN_MAPPED_EMP_WITH_ROSTER)			
	{			
				
				
				
				
				
				//in case the roster is prepared for 24x7 duty type ,hence all the days button will be visible 
				
		if(dutyType.equals(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN))	
				{
				strCal.append("<td align=\"center\" style='display:"+tdDisplay+" background-color: "+backgroundColor+"' id=\""+buttonTdId+"\"><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftTypeOfUnMappedEmp(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\" style=\"color: "+shiftNameButtonColor+"; background-color: "+buttonColor+";\">");
				strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\"> </td>");		
				}
				else//in case the roster is prepared for Offical days  ,hence  sunday / gazetted holidays  days button will be disabled rest will be enabled
		if(dutyType.equals(DutyRosterConfig.DUTY_TYPE_OFFICIAL_DAYS))	
		{	
			
			//checking whether the current day is sunday ,then making it disabled
										//OR
			//checking whether the current day is gazetted ,then making it disabled
			

			
			if(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(Integer.toString(j)))
				{
				strCal.append("<td align=\"center\" class=\"\" style='display="+tdDisplay+"; background-color: "+backgroundColor+"' id=\""+buttonTdId+"\"><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftTypeOfUnMappedEmp(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\"  disabled=\"true\" style=\"color: "+shiftNameButtonColor+"; background-color: "+buttonColor+";\">");
				strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\" > </td>");
				}
				else //if it is neither sunday , nor gazetted then  it is an offical day hence enabled
			  {	  
					strCal.append("<td align=\"center\" class=\"\" id=\""+buttonTdId+"\" style='display="+tdDisplay+"; ' ><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftTypeOfUnMappedEmp(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\" style=\"color: "+shiftNameButtonColor+"; background-color: "+buttonColor+";\">");
					strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\" > </td>");
			  }
			
		}	
		else//in case the roster is prepared for Holiday ,hence only sunday / gazetted holidays  days button will be enabled rest will be disabled
			if(dutyType.equals(DutyRosterConfig.DUTY_TYPE_SUNDAY_HOLIDAY))	
		{	
				
				//checking whether the current day is sunday ,then making it enabled
										//OR
				//checking whether the current day is gazetted ,then making it enabled
				
				
				if(dayCode==Calendar.SUNDAY || _holidayMap.containsKey(Integer.toString(j)))
					{

					strCal.append("<td align=\"center\" style='display:"+tdDisplay+" background-color: "+backgroundColor+"' id=\""+buttonTdId+"\" ><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftTypeOfUnMappedEmp(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\"  style=\"color: "+shiftNameButtonColor+";background-color: "+buttonColor+";\">");
					strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\" > </td>");
					}
					 else //if it is neither sunday , nor gazetted then  it is an offical day hence enabled
				  {	  
						//if any shift is already assigned to this employee then it's colr will change to red
				    strCal.append("<td align=\"center\" class=\"\"  id=\""+buttonTdId+"\" style='display:"+tdDisplay+" ' ><input type=\"button\" id=\""+buttonId+"\" tabindex=\"1\" onclick=\"changeShiftTypeOfUnMappedEmp(this)\"  onmouseover=\"showShiftName(this)\"  onmousemove=\"showShiftName(this)\"  name=\""+buttonName+"\"  size=\"1\"  value=\""+shiftButtonDisplay+"\" disabled=\"true\" style=\"color: "+shiftNameButtonColor+"; background-color: "+buttonColor+";\">");
				    strCal.append("<input type=\"hidden\" id=\""+hiddenId+"\"  value=\""+shiftList+"\" > </td>");
				  }
				
		}	


			
		
			
			}//if of UN_MAPPED_EMP_WITH_ROSTER closed  

		
}//for of days list closed

		strCal.append("<input type=\"hidden\" id=\"holidayList\"  name=\"holidayList\"  value=\""+holidayList+"\" > </td>");
		 
		strCal.append("</tr>");
		
		
}	//for of emp list closed
	

	
	return strCal;

	

} 

/**
 * Here We are getting those emp list who are mapped ,and  there Roster has also been prepared
 * 
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static List getEmpMappedWithRosterList(List empMappedList,List empRosterList)

{
	
	List empMappedWithRosterList=new ArrayList();
	
	for(int i=0 ; i <empMappedList.size();i++)
	{
		Entry objEntryEmp = new Entry();
		String empId="";
		String empName="";
		
		objEntryEmp=(Entry)empMappedList.get(i);
		empId=objEntryEmp.getValue();
		empName	=objEntryEmp.getLabel();
		
		boolean check=false;
		

		for(int j=0 ; j <empRosterList.size();j++)
		{
			Entry objEntryEmp2 = new Entry();
			String empId2="";
			String empName2="";
			
			objEntryEmp2=(Entry)empRosterList.get(j);
			empId2=objEntryEmp2.getValue();
			empName2=objEntryEmp2.getLabel();
			
			
			if(empId.equals(empId2))//if start
				{
				check=true;
				break;
				}//if closed
			
			
		}//inner for closed
		
		//i.e. If emp is in mapped list and also  in roster list
		//i.e. it's roster has been prepared  
		//hence it is added in empMappedWithRosterList
		if(check==true)
			empMappedWithRosterList.add(objEntryEmp);
			
		
	}	//outer for closed

	
	
	return empMappedWithRosterList;
}



/**
 * Here We are getting those emp list who wre  mapped Earlier(But currently they are removed ),
 * and there Roster has been prepared Earlier ,But there future Roster Cannot be prepared
 * 
 * @param  empMappedList
 * @param  empRosterList
 * @return empNotMappedWithRosterList
 */


public static List getEmpNotMappedWithRosterList(List empMappedList,List empRosterList)

{
	
	List empNotMappedWithRosterList=new ArrayList();
	
	for(int i=0 ; i <empRosterList.size();i++)
	{
	Entry objEntryEmp = new Entry();
	String empId="";
	String empName="";

	objEntryEmp=(Entry)empRosterList.get(i);
	empId=objEntryEmp.getValue();
	empName	=objEntryEmp.getLabel();

	boolean check=false;


	for(int j=0 ; j <empMappedList.size();j++)
	{
	Entry objEntryEmp2 = new Entry();
	String empId2="";
	String empName2="";

	objEntryEmp2=(Entry)empMappedList.get(j);
	empId2=objEntryEmp2.getValue();
	empName2=objEntryEmp2.getLabel();


	if(empId.equals(empId2))//if start
		{
		check=true;
		break;
		}//if closed


	}//inner for closed

	//i.e. If emp is in Roster list but does not in Emp Mapped list
	//i.e. it's roster has  been prepared earlier ,but it's future Roster cannot be prepared 
	//hence it is added in empNotMappedWithRosterList
	if(check==false)
		empNotMappedWithRosterList.add(objEntryEmp);


	}	//outer for closed

	
	
	return empNotMappedWithRosterList;
}





/**
 * Here We are getting those emp list who are mapped ,but there Roster has not been prepared Yet
 * 
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithNoRosterList
 */


public static List getEmpMappedWithNoRosterList(List empMappedList,List empRosterList)

{
	
	List empMappedWithNoRosterList=new ArrayList();
	
	for(int i=0 ; i <empMappedList.size();i++)
	{
	Entry objEntryEmp = new Entry();
	String empId="";
	String empName="";

	objEntryEmp=(Entry)empMappedList.get(i);
	empId=objEntryEmp.getValue();
	empName	=objEntryEmp.getLabel();

	boolean check=false;


	for(int j=0 ; j <empRosterList.size();j++)
	{
	Entry objEntryEmp2 = new Entry();
	String empId2="";
	String empName2="";

	objEntryEmp2=(Entry)empRosterList.get(j);
	empId2=objEntryEmp2.getValue();
	empName2=objEntryEmp2.getLabel();


	if(empId.equals(empId2))//if start
		{
		check=true;
		break;
		}//if closed


	}//inner for closed

	//i.e. If emp is in mapped list but does not in roster list
	//i.e. it's roster has not been prepared yet 
	//hence it is added in empMappedWithNoRosterList
	if(check==false)
	empMappedWithNoRosterList.add(objEntryEmp);


	}	//outer for closed


	
	
	return empMappedWithNoRosterList;
}


/**
 * Here We are getting the Day By sending the day Code to the function
 * 
 * @param  dayCode
 * @return Day
 */


public static String  getDayFromDayCode(int dayCode)

{
	
	String[] daysArray={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	
	return daysArray[dayCode-1];
}



/**
 * 	getting the complete monthly roster of all the mapped employees 
 * 	excluding this roster Type and area code
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static Map getMapofTotalMonthlyRostersOfAllMappedEmp(RosterDtlVO[] _monthlyEmpRosterDtlVO)

{
	
	Map _totalMonthlyEmpRosterMap=new LinkedHashMap();
			
for(int i= 0 ; i < _monthlyEmpRosterDtlVO.length ; i++)
	{
	Map _dayMap=new LinkedHashMap();
	Map _shiftMap=new LinkedHashMap();
	
String shiftDetails="";
	
	//									  1									  2 											3											4													5			
	//									shiftName		+ @ +             shiftShortname  	+   			 @			ShiftType			+ 			 @		    RosterName			+				@   +			AreaName																			
//	shiftDetails=_monthlyEmpRosterDtlVO[i].getShiftName()+"@"+_monthlyEmpRosterDtlVO[i].getShiftShortName()+"@"+_monthlyEmpRosterDtlVO[i].getShiftType()+"@"+_monthlyEmpRosterDtlVO[i].getRosterName()+"@"+_monthlyEmpRosterDtlVO[i].getAreaName();	

shiftDetails=_monthlyEmpRosterDtlVO[i].getShiftType()+"@"+_monthlyEmpRosterDtlVO[i].getShiftName()+"@"+_monthlyEmpRosterDtlVO[i].getShiftShortName();
	
	
	//in case we don't get any data for a an employee 
if(!_totalMonthlyEmpRosterMap.containsKey(_monthlyEmpRosterDtlVO[i].getEmpId()))
	{
		
	_shiftMap.put("shiftId", shiftDetails);
	
	 _dayMap.put(_monthlyEmpRosterDtlVO[i].getStartDateTime(), _shiftMap);
	_totalMonthlyEmpRosterMap.put(_monthlyEmpRosterDtlVO[i].getEmpId(), _dayMap);
	
	
	}	
else//else if we get an employee entry
	{
	_dayMap=(Map)_totalMonthlyEmpRosterMap.get(_monthlyEmpRosterDtlVO[i].getEmpId());
	
	//in case the emp Map does not contains the day code
	if(!_dayMap.containsKey(_monthlyEmpRosterDtlVO[i].getStartDateTime()))
		{
		

		_shiftMap.put("shiftId", shiftDetails);
		// shiftDetails=_monthlyEmpRosterDtlVO[i].getShiftName();
		
		
		_dayMap.put(_monthlyEmpRosterDtlVO[i].getStartDateTime(), _shiftMap);
		_totalMonthlyEmpRosterMap.put(_monthlyEmpRosterDtlVO[i].getEmpId(), _dayMap);
		
		
		}
	else//else if we get a day code as a key 
		{
		//here we get the shift map
		_shiftMap=(Map)_dayMap.get(_monthlyEmpRosterDtlVO[i].getStartDateTime());

		String shiftDetailsTemp=(String)_shiftMap.get("shiftId")+"#"+shiftDetails;		
		
		_shiftMap.put("shiftId", shiftDetailsTemp);
		_dayMap.put(_monthlyEmpRosterDtlVO[i].getStartDateTime(), _shiftMap);
		_totalMonthlyEmpRosterMap.put(_monthlyEmpRosterDtlVO[i].getEmpId(), _dayMap);
				
		}//inner else closed 
		
		
		
		
		
	}//outer else closed
	




}//for closed
		
	

	
	
	return _totalMonthlyEmpRosterMap;
}


/**
 * 	getting the complete monthly roster of all the mapped employees 
 * 	excluding this roster Type and area code
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static Map getEmpRosterCatgAreaMap(RosterDtlVO[] _monthlyEmpRosterDtlVO)

{
	
	Map _empRosterCatgAreaMap=new LinkedHashMap();
	
			
for(int i= 0 ; i < _monthlyEmpRosterDtlVO.length ; i++)
{
	Map _rosterCatgMap=new LinkedHashMap();
	Map _areaMap=new LinkedHashMap();
	String day="";

	
	String _empKey=_monthlyEmpRosterDtlVO[i].getEmpId();
	
	String _catgKey=_monthlyEmpRosterDtlVO[i].getRosterCatgId()+"@"+_monthlyEmpRosterDtlVO[i].getRosterCatgName();
    
	String _areaKey=_monthlyEmpRosterDtlVO[i].getAreaCode()+"@"+_monthlyEmpRosterDtlVO[i].getRosterName()+"/ "+_monthlyEmpRosterDtlVO[i].getAreaName();
	
	day=_monthlyEmpRosterDtlVO[i].getStartDateTime()+"("+_monthlyEmpRosterDtlVO[i].getShiftShortName()+")";
	
	//if the _empRosterCatgAreaMap ,doesn't contain an employees entry 
	//hence it will be added first time
	if(!_empRosterCatgAreaMap.containsKey(_empKey))
	{
		
	
		
		
		_areaMap.put(_areaKey, day);
		_rosterCatgMap.put(_catgKey, _areaMap);
		_empRosterCatgAreaMap.put(_empKey, _rosterCatgMap);
		
		
	}//if closed
	else
	{
	_rosterCatgMap=(Map)_empRosterCatgAreaMap.get(_empKey);
	
	//if the emp map doesn't contains any catg key 
	
	if(!_rosterCatgMap.containsKey(_catgKey))
		{
		
		_areaMap.put(_areaKey, day);
		_rosterCatgMap.put(_catgKey, _areaMap);
		_empRosterCatgAreaMap.put(_empKey, _rosterCatgMap);
		
		
		}
		else//else if it contained a catgkey  
		{
			_areaMap=(Map)_rosterCatgMap.get(_catgKey);
			
			
			//checking for wheteher any area key is available or not 
			
			if(!_areaMap.containsKey(_areaKey))
				{
				_areaMap.put(_areaKey, day);
				_rosterCatgMap.put(_catgKey, _areaMap);
				_empRosterCatgAreaMap.put(_empKey, _rosterCatgMap);
				
				}
				else
				{
				String daysList=(String)_areaMap.get(_areaKey)+" ,"+day;
				
				_areaMap.put(_areaKey, daysList);
				_rosterCatgMap.put(_catgKey, _areaMap);
				_empRosterCatgAreaMap.put(_empKey, _rosterCatgMap);
					
					
				 }	
			
			
			
		}	
			
		
		
	}	//else closed 
	
	




}//for closed
		
	

	
	
	return _empRosterCatgAreaMap;
}


/**
 * 	getting the complete monthly roster of all the mapped employees 
 * 	excluding this roster Type and area code
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static Map getMapofAreaEmpMapping(DutyRosterAreaEmployeeVO[] _areaEmpMappingVO)

{
	
	Map _areaEmpMap=new LinkedHashMap();
			
for(int i= 0 ; i < _areaEmpMappingVO.length ; i++)
	{
	
	
String areaDetails="";
	
	areaDetails=_areaEmpMappingVO[i].getAreaCode();
	
	
	//in case we don't get any data for a an employee 
if(!_areaEmpMap.containsKey(_areaEmpMappingVO[i].getEmpId()))
	{
		
	
	_areaEmpMap.put(_areaEmpMappingVO[i].getEmpId(), areaDetails);
	
	
	}	
else//else if we get an employee entry
	{

	String areaDetailsTemp=_areaEmpMap.get(_areaEmpMappingVO[i].getEmpId())+","+areaDetails;
	
	_areaEmpMap.put(_areaEmpMappingVO[i].getEmpId(), areaDetailsTemp);
			
		
	}//outer else closed
	




}//for closed
		
	

	
	
	return _areaEmpMap;
}



/**
 * 	getting the complete monthly roster of all the mapped employees 
 * 	excluding this roster Type and area code
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static Map getMapofEmpLeave(RosterDtlVO[] _empMonthlyLeaveVO)

{
	
	Map _empLeaveMap=new LinkedHashMap();
			
	
	for(int i=0; i < _empMonthlyLeaveVO.length ; i++ )
	{
		List _empLeaveList=new ArrayList();
		int leaveStartDate=0;
		int leaveEndDate=0;
		
		
//if no record for employee exsists
if(!_empLeaveMap.containsKey(_empMonthlyLeaveVO[i].getEmpId()))
	{
	leaveStartDate=Integer.parseInt(_empMonthlyLeaveVO[i].getStartDateTime());
	leaveEndDate=Integer.parseInt(_empMonthlyLeaveVO[i].getEndDateTime());
	
	for(int j=leaveStartDate ; j <= leaveEndDate ;j++)
		_empLeaveList.add(j);
	
	_empLeaveMap.put(_empMonthlyLeaveVO[i].getEmpId(),_empLeaveList);
	
	}
else
//if  Record for employee exsists i.e. in case of extension 
	{
	_empLeaveList=(List)_empLeaveMap.get(_empMonthlyLeaveVO[i].getEmpId());
	
		leaveStartDate=Integer.parseInt(_empMonthlyLeaveVO[i].getStartDateTime());
		leaveEndDate=Integer.parseInt(_empMonthlyLeaveVO[i].getEndDateTime());
		
		for(int j=leaveStartDate ; j <= leaveEndDate ;j++)
			_empLeaveList.add(j);
		
		_empLeaveMap.put(_empMonthlyLeaveVO[i].getEmpId(),_empLeaveList);
		
		}
			
			
			
			
			
		
	}

	
	
	return _empLeaveMap;
}


/**
 * 	getting the getListofRosterGeneratedDays 
 * 	excluding this roster Type and area code
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static List getListofRosterGeneratedDays(RosterGenerationDtlVO[] _rosterGenerationDtlVO)

{
	
	List rosterGenerationList=new ArrayList();
			
	
	for(int i=0; i < _rosterGenerationDtlVO.length ; i++ )
	{
	
		int startDate=Integer.parseInt(_rosterGenerationDtlVO[i].getStartDate());
		int endDate=Integer.parseInt(_rosterGenerationDtlVO[i].getEndDate());
		
		
		for(int j=startDate; j <= endDate ; j++)
				rosterGenerationList.add(j);
			
	}

	
	
	return rosterGenerationList;
}


/**
 * 	getting the getMapofRosterGeneratedDays
 * 	excluding this roster Type and area code
 * @param  empMappedList
 * @param  empRosterList
 * @return empMappedWithRosterList
 */


public static Map getMapofRosterGeneratedDays(RosterGenerationDtlVO[] _rosterGenerationDtlVO)

{
	Map rosterGeneratedMap=new LinkedHashMap();
	
	try{
	
	List rosterGenList=new ArrayList();		
	int key=0;
	
	for(int i=0; i < _rosterGenerationDtlVO.length ; i++ )
	{
	
		int startDate=Integer.parseInt(_rosterGenerationDtlVO[i].getStartDate());
		int endDate=Integer.parseInt(_rosterGenerationDtlVO[i].getEndDate());
		
		
		
		//for the first time we are just adding the days to the list 
		// and then putting the list to the startdate as key in the map 
		if(i==0){			
			for(int k=startDate; k <= endDate;k++)
				rosterGenList.add(k);
			
			key=startDate;
			rosterGeneratedMap.put(key,rosterGenList);
			
			}
		else//if it is not the first time 
			{
			int startDateOld=Integer.parseInt(_rosterGenerationDtlVO[i-1].getStartDate());
			int endDateOld=Integer.parseInt(_rosterGenerationDtlVO[i-1].getEndDate());
			
				//if the range is continued then adding the range to the same list and then
				//putting it against the old key 	
				if(startDate==endDateOld+1){
					for(int k=startDate; k <= endDate;k++)
						rosterGenList.add(k);
					rosterGeneratedMap.put(key,rosterGenList);
				}
				else//if the range breaks then making a new list & new key 
				{
					//resetting the key & generation list 
					key=startDate;
					rosterGenList=new ArrayList();
					
					for(int k=startDate; k <= endDate;k++)
						rosterGenList.add(k);
					rosterGeneratedMap.put(key,rosterGenList);
										
				}	
			
			
			
			}//else closed
		
		//System.out.println("rosterGeneratedMap---"+rosterGeneratedMap);
	}//for loop ends 

	Set keySet=rosterGeneratedMap.keySet();
	
	//System.out.println("keySet.getclass---"+keySet.getClass());
	
	Iterator itrKey=keySet.iterator();
	
	while(itrKey.hasNext()){
		int mapKey=((Integer)itrKey.next()).intValue();
		List rosterList=(List)rosterGeneratedMap.get(mapKey);
		String generatedRange=(Integer)rosterList.get(0)+"@"+(Integer)rosterList.get(rosterList.size()-1);
		rosterGeneratedMap.put(mapKey, generatedRange);
		}
	
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	return rosterGeneratedMap;
}


public static RosterWiseReliversDtlVO[] getRosterDtlVOsToBeRelived(HttpServletRequest _request,EmpRosterGenerationFB _fb,String _rosterId)
{

	


	
	String[] reliverEmpListArray=_fb.getReliverEmpList().split("@");
	
	
	RosterWiseReliversDtlVO[] _reliverRosterVO=new RosterWiseReliversDtlVO[reliverEmpListArray.length];
		
	for(int i=0 ;i< reliverEmpListArray.length ;i++)
	{
		_reliverRosterVO[i]=new RosterWiseReliversDtlVO();
		             
		
			
		_reliverRosterVO[i].setRosterId(_rosterId);
		_reliverRosterVO[i].setGeneratedRosterId(_fb.getGeneratedRosterId());
		_reliverRosterVO[i].setEmpId(reliverEmpListArray[i]);
	//	_reliverRosterVO[i].setReliverType(DutyRosterConfig.RELIVER_TYPE_FROM_ROSTER);
		_reliverRosterVO[i].setEntryMode(DutyRosterConfig.RELIVER_TYPE_FROM_ROSTER);
		_reliverRosterVO[i].setFromDate(_fb.getStartDate());           
		_reliverRosterVO[i].setToDate(_fb.getEndDate());
		
		
		
		
	}
	
	
	return _reliverRosterVO;

	
}


public static Map getMapOfEmpToBeRelived(HttpServletRequest _request,EmpRosterGenerationFB _fb)
{

	


	Map reliverEmpMap=new LinkedHashMap();
	
	
	
	RosterWiseReliversDtlVO[] _reliverRosterVO=(RosterWiseReliversDtlVO[])_request.getSession().getAttribute(DutyRosterConfig.VO_OF_EMP_RELIVERS_ROSTER_WISE);
	
if(_reliverRosterVO!=null)
{	
	for(int i=0 ;i< _reliverRosterVO.length ;i++)
	{
		
		String dateRange=_reliverRosterVO[i].getFromDate()+"-"+_reliverRosterVO[i].getToDate();

		List empReliverList=new ArrayList();
	if(reliverEmpMap.containsKey(_reliverRosterVO[i].getEmpId()))
		      empReliverList=(ArrayList)reliverEmpMap.get(_reliverRosterVO[i].getEmpId());       
		
	
			empReliverList.add(dateRange);
			reliverEmpMap.put(_reliverRosterVO[i].getEmpId(), empReliverList);
	
	}
	
}	
	return reliverEmpMap;

	
}

}
