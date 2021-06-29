package dutyroster.transaction.controller.utl;

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
import javax.servlet.http.HttpSession;

import dutyroster.DutyRosterConfig;
import dutyroster.reports.controller.fb.AreaWiseEmpRosterReportFB;
import dutyroster.transaction.controller.data.DutyRoleAssignmentDATA;
import dutyroster.transaction.controller.fb.DutyRoleAssignmentFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.DutyRoleDetailVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;

public class DutyRoleAssignmentUTL extends ControllerUTIL {

	/**
	 * @param _request
	 * @param _fb
	 */
	public static void getRosterForRoleAssignment(HttpServletRequest _request,DutyRoleAssignmentFB _fb) {
		Status objStatus=new Status();
		List RosterTypeList=new ArrayList();
		HttpSession session=WebUTIL.getSession(_request);
		try{
			setSysdate(_request);
			UserVO userVO=getUserVO(_request);
			RosterTypeList=DutyRoleAssignmentDATA.getRosterForRoleAssignment(userVO);
			getYearList(_request,_fb);
			getMonthsList(_request, _fb);
			String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			_fb.setFromDate(sysDate);
			_fb.setToDate(sysDate);
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_FOR_ROLE_ASSIGNMENT, RosterTypeList);
			objStatus.add(Status.NEW);
		}catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Exception");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			
		}
		
	}
	
	public static void  getYearList(HttpServletRequest _request,DutyRoleAssignmentFB _fb)
	
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

	
	public static void  getMonthsList(HttpServletRequest _request,DutyRoleAssignmentFB _fb)
	
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



	public static void getShiftAndAreaForRoster(HttpServletRequest _request,DutyRoleAssignmentFB _fb) {
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		try{
			UserVO userVO=getUserVO(_request);
			essentialMap=DutyRoleAssignmentDATA.getShiftAndAreaForRoster(_fb.getRosterTypeID(),userVO);
			WebUTIL.setMapInSession(essentialMap,_request);
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROLE_ASSIGNED_DATE_RANGE_WISE, new ArrayList()); 
		}catch (HisRecordNotFoundException e)
		{
			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.ESSENTIAL_SHIFT_TYPE_BASEDON_ROSTER, new ArrayList());
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
			
					
			e.printStackTrace();
			objStatus.add(Status.NEW, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Exception");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			
		}
		
	}

	/**
	 * @param _request
	 * @param _fb
	 */
	public static void getEmployeesForRosterShift(HttpServletRequest _request,DutyRoleAssignmentFB _fb) {
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		List employeeShiftRoleList=new ArrayList();
		RosterDtlVO[] arrayRosterDtlVO=null;
		List employeeList;
		Map shiftMap=new LinkedHashMap();
		
		String[] arrayOfDateRangeData=null;
		
		try{
			UserVO userVO=getUserVO(_request);
			RosterDtlVO rosterDtlVO=new RosterDtlVO();
			rosterDtlVO.setRosterId(_fb.getRosterTypeID());
			rosterDtlVO.setShiftId(_fb.getShiftID());
			rosterDtlVO.setAreaCode(_fb.getDutyAreaId());
			rosterDtlVO.setStartDateTime(_fb.getFromDate());
			rosterDtlVO.setEndDateTime(_fb.getToDate());
			
			
			if(_fb.getRoleDateRangeId()!=null)
				arrayOfDateRangeData=_fb.getRoleDateRangeId().split("@");

			if(_fb.getMode().equals("MODIFY") && arrayOfDateRangeData!=null)
				{
				rosterDtlVO.setStartDateTime(arrayOfDateRangeData[0]);
				rosterDtlVO.setEndDateTime(arrayOfDateRangeData[1]);
				rosterDtlVO.setShiftId(arrayOfDateRangeData[3]);
				_fb.setShiftID(arrayOfDateRangeData[3]);
				_fb.setFromDate(arrayOfDateRangeData[0]);
				_fb.setToDate(arrayOfDateRangeData[1]);
				}
				
				
			
			essentialMap=DutyRoleAssignmentDATA.getEmployeeMapAndRole(rosterDtlVO,userVO);
			
			arrayRosterDtlVO=(RosterDtlVO[])essentialMap.get(DutyRosterConfig.EMPLOYEE_LIST_ROSTER_DTL_VO_ARRAY);
			//List roleList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.ESSENTIAL_DUTY_ROLE_FOR_ROSTER);
			//String[] newRoleForm=new String[arrayRosterDtlVO.length*roleList.size()];
			
			List shiftList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.ESSENTIAL_SHIFT_TYPE_BASEDON_ROSTER);
			
			///for lop for generating shift map for display
			for(int i=0;i<arrayRosterDtlVO.length;i++)
			{
				String epmId=arrayRosterDtlVO[i].getEmpId();
				String roleId=arrayRosterDtlVO[i].getRoleId();
				String shiftId=arrayRosterDtlVO[i].getShiftId();
				String empName=arrayRosterDtlVO[i].getEmpName();
				String shiftName="";
				String shiftMapKey="";
				Iterator shiftListItr=shiftList.iterator();
				while(shiftListItr.hasNext())
				{
					Entry shiftEntry=(Entry)shiftListItr.next();
					if(shiftEntry.getValue().equals(shiftId))
					{
						shiftName=shiftEntry.getLabel();
						shiftMapKey=shiftName+"#"+shiftId;
					}
				}
				if(shiftMap.containsKey(shiftMapKey))
				{
					employeeList=(ArrayList)shiftMap.get(shiftMapKey);
					
				}
				else
				{
					employeeList=new ArrayList();
					shiftMap.put(shiftMapKey, employeeList);
				}
				Entry newEntry=new Entry();
				newEntry.setValue(epmId+"@"+roleId);
				newEntry.setLabel(empName);
				employeeList.add(newEntry);
				
				
				if(roleId!=null && !roleId.equals(""))
				{
					String[] arrayEmpRoleID=roleId.split("#");
					
					
					for(int j=0;j<arrayEmpRoleID.length;j++)
					{
						String employeeListObject=epmId+"#"+shiftId+"#"+arrayEmpRoleID[j];
						employeeShiftRoleList.add(employeeListObject);
					}
					
					
				}
	
				System.out.println("shift name1-----"+shiftName);
				if(_fb.getMode().equals("MODIFY"))
					_fb.setShiftName(shiftName);
					
				
			}
			
			System.out.println("shift name2-----"+_fb.getShiftName());
			
			_fb.setGeneratedRosterId(arrayRosterDtlVO[0].getGeneratedRosterId());
			
			essentialMap.put(DutyRosterConfig.EMPLOYEE_LIST_ROSTER_AREA_AND_SHIFT_WISE, shiftMap);
			WebUTIL.setMapInSession(essentialMap,_request);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROLE_MAPPED_WITH_EMPLOYEE, employeeShiftRoleList);
		
			
			List rosterList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_FOR_ROLE_ASSIGNMENT);
			List areaList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA);
			
			Iterator rosterListItr=rosterList.iterator();
			while(rosterListItr.hasNext())
			{
				Entry rosterEntry=(Entry)rosterListItr.next();
				if(rosterEntry.getValue().equals(_fb.getRosterTypeID()))
					_fb.setRosterName(rosterEntry.getLabel());
					
			}
			
			
			Iterator areaListItr=areaList.iterator();
			while(areaListItr.hasNext())
			{
			Entry areaEntry=(Entry)areaListItr.next();
				if(areaEntry.getValue().equals(_fb.getDutyAreaId()))
					_fb.setAreaName(areaEntry.getLabel());
				
			}
			
			
			
			
			objStatus.add(Status.TRANSINPROCESS);
		}catch (HisRecordNotFoundException e)
		{
			//WebUTIL.setMapInSession(e.getEssentialMap(),_request);	
			
				
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.ESSENTIAL_DUTY_ROLE_FOR_ROSTER, new ArrayList());
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROLE_ASSIGNED_DATE_RANGE_WISE, new ArrayList());
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.EMPLOYEE_LIST_ROSTER_DTL_VO_ARRAY, arrayRosterDtlVO);
			
		//	WebUTIL.setMapInSession(e.getEssentialMap(), _request);
			
			e.printStackTrace();
			objStatus.add(Status.NEW, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Exception");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			
		}
		
	}
	
	public static Map createMapForVO(List listForMap)
	{
		Map employeeMap=new HashMap();
		Map shiftMap=new HashMap();
		try{
			
			
			String roleId="";
			for(int m=0;m<listForMap.size();m++)
			{
				String[] employeeRoleShiftArray=((String)listForMap.get(m)).split("#");
				String empId=employeeRoleShiftArray[0];
				String shiftId=employeeRoleShiftArray[1];
				String empRoleId=employeeRoleShiftArray[2];
				
				if(shiftMap.containsKey(shiftId)){
					employeeMap=(HashMap)shiftMap.get(shiftId);
					if(employeeMap.containsKey(empId)){
						roleId=(String)employeeMap.get(empId);
						roleId=roleId+"#"+empRoleId;
						employeeMap.put(empId,roleId);
					}else{
						employeeMap.put(empId,new String(empRoleId) );
					}
				}
				else{
					employeeMap=new HashMap();
					employeeMap.put(empId,new String(empRoleId) );
					shiftMap.put(shiftId, employeeMap);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return shiftMap;
	}
	
	public static RosterDtlVO[] createVOFromMap(Map shiftMap,DutyRoleAssignmentFB _fb)
	{
		RosterDtlVO[] rosterDtlVO=null;
		Map employeeMap=new HashMap();
		try{
		int insertVOSize=0;
		
		Set shiftKeySet=shiftMap.keySet();
		Iterator itr=shiftKeySet.iterator();
		while(itr.hasNext())
		{
			employeeMap=(Map)shiftMap.get(itr.next());
			insertVOSize+=employeeMap.size();
			
		}
		
		int counter=0;
	rosterDtlVO=new RosterDtlVO[insertVOSize];
	shiftKeySet=shiftMap.keySet();
	itr=shiftKeySet.iterator();
	while(itr.hasNext())
	{
		String shiftId=(String)itr.next();
		employeeMap=(Map)shiftMap.get(shiftId);
		Set empMapSet=employeeMap.keySet();
		Iterator empMapsetItr=empMapSet.iterator();
		while(empMapsetItr.hasNext())
		{
			String empId=(String)empMapsetItr.next();
			String roleId=(String) employeeMap.get(empId);
			rosterDtlVO[counter]=new RosterDtlVO();
			rosterDtlVO[counter].setAreaCode(_fb.getDutyAreaId());
			rosterDtlVO[counter].setGeneratedRosterId(_fb.getGeneratedRosterId());
			rosterDtlVO[counter].setFromDate(_fb.getFromDate());
			rosterDtlVO[counter].setToDate(_fb.getToDate());
			rosterDtlVO[counter].setEmpId(empId);
			rosterDtlVO[counter].setRoleId(roleId);
			rosterDtlVO[counter].setShiftId(shiftId);
			counter++;
		}
	}
		}catch(Exception e){
			e.printStackTrace();
		}
		return rosterDtlVO;
	}
	
	

	public static void saveEmployeeRoleDetail(HttpServletRequest _request,DutyRoleAssignmentFB _fb) {
		Status objStatus=new Status();
		
		RosterDtlVO[] insertRosterDtlVO=null;
		RosterDtlVO[] updateRosterDtlVO=null;
		
		////List of already mapped employee role
		List mappedRoleEmployeeList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_ROLE_MAPPED_WITH_EMPLOYEE);
		
		//new list on employee role from form bean
		List newEmployeeRoleList=new ArrayList();
		
		for(int i=0;i<_fb.getRoleID().length;i++)
		{
			newEmployeeRoleList.add(_fb.getRoleID()[i]);
		}
		try{
			
			List insertList=new ArrayList();
			List updateList=new ArrayList();
			
			//creating new object of insert and update list
			Iterator newEmployeeRoleListIterator=newEmployeeRoleList.iterator();
			while(newEmployeeRoleListIterator.hasNext())
			{
				insertList.add(newEmployeeRoleListIterator.next());
			}
			Iterator mappedRoleEmployeeListIterator=mappedRoleEmployeeList.iterator();
			while(mappedRoleEmployeeListIterator.hasNext())
			{
				updateList.add(mappedRoleEmployeeListIterator.next());
			}
			////////////////////////////////////////
			//////temp list same as insert
			List temp=new ArrayList();
			newEmployeeRoleListIterator=newEmployeeRoleList.iterator();
			while(newEmployeeRoleListIterator.hasNext())
			{
				temp.add(newEmployeeRoleListIterator.next());
			}
			
			/////removing already inserted object
			insertList.removeAll(mappedRoleEmployeeList);
			///add coomon elements
			temp.retainAll(mappedRoleEmployeeList);
			
			/////////insertlist
			insertList.addAll(temp);
			//////creating update list
			updateList.removeAll(newEmployeeRoleList);
			
			///creating insert rostervo
			Map employeeMap=new HashMap();
			Map shiftMap=null;
			shiftMap=createMapForVO(insertList);
			insertRosterDtlVO=createVOFromMap(shiftMap,_fb);
			
			
			///creating update roster vo
			shiftMap=createMapForVO(updateList);
			updateRosterDtlVO=createVOFromMap(shiftMap,_fb);
			
			
			UserVO userVO=getUserVO(_request);
			
			DutyRoleDetailVO[] dutyRoleDetailVO=new DutyRoleDetailVO[insertRosterDtlVO.length];
			
					
			for(int i=0;i<insertRosterDtlVO.length;i++)
			{
				dutyRoleDetailVO[i]=new DutyRoleDetailVO();
				dutyRoleDetailVO[i].setDutyAreaId(insertRosterDtlVO[i].getAreaCode());
				dutyRoleDetailVO[i].setEmpID(insertRosterDtlVO[i].getEmpId());
				dutyRoleDetailVO[i].setFromDate(insertRosterDtlVO[i].getFromDate());
				dutyRoleDetailVO[i].setToDate(insertRosterDtlVO[i].getToDate());
				dutyRoleDetailVO[i].setRoleID(insertRosterDtlVO[i].getRoleId());
				dutyRoleDetailVO[i].setGeneratedRosterId(insertRosterDtlVO[i].getGeneratedRosterId());
				dutyRoleDetailVO[i].setShiftID(insertRosterDtlVO[i].getShiftId());
				
			}
			
			
			DutyRoleAssignmentDATA.saveEmployeeRoleDetail(dutyRoleDetailVO,insertRosterDtlVO,updateRosterDtlVO,userVO,_fb.getMode());
				
			
			
			objStatus.add(Status.NEW,"","Duty Role Assigned Successfully");
		}catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.NEW, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Exception");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			
		}
		
		
	}

}
