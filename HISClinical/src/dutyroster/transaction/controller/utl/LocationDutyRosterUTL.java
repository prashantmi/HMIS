
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
import hisglobal.vo.BlockwiseRosterDtlVO;
import hisglobal.vo.DutyRosterAreaEmployeeVO;
import hisglobal.vo.DutyRosterShiftMasterVO;
import hisglobal.vo.RosterAreaCapacityMstVO;
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.dao.EssentialDAO;
import dutyroster.transaction.controller.data.LocationDutyRosterDATA; 
import dutyroster.transaction.controller.fb.EmployeeDutyRosterFB;
import dutyroster.transaction.controller.fb.LocationDutyRosterFB;

public class LocationDutyRosterUTL extends ControllerUTIL
{

	
// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst

	public static boolean getRosterAndAreaTypeListHavingRosterModeLocation(HttpServletRequest _request,LocationDutyRosterFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
			
					
			dutyRosterList=LocationDutyRosterDATA.getRosterAndAreaTypeListHavingRosterModeLocation(_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTERS_AND_AREA_TYPE_HAVING_ROSTER_MODE_LOCATION, dutyRosterList);
			
				
			if(dutyRosterList!=null && dutyRosterList.size()==1)
					{
					Entry obj=(Entry)dutyRosterList.get(0);
					String rosterId=obj.getValue();
					_fb.setRosterId(rosterId);
					
					LocationDutyRosterUTL.getDutyAreaWithCapacityAndDesignationBasedOnRosterType(_request, _fb);
					}
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
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
		
		public static boolean getDutyAreaWithCapacityAndDesignationBasedOnRosterType(HttpServletRequest _request,LocationDutyRosterFB _fb)
		{
			Status objStatus = new Status();
			
			List dutyAreaList=new ArrayList();
			Map EssentialMap=new HashMap(); 
			
			try
			{
				UserVO _userVO = getUserVO(_request);
				setSysdate(_request);
	 
				

				//fetching the Roster ID Information from the Concatinated Values 
				
				String splitValues[]=_fb.getRosterId().split("@");
				
			/*	String areaTypeCode= _request.getParameter("areaType");
				
				System.out.println("areaTypeCode----"+areaTypeCode);
				
			if(areaTypeCode==null)
					areaTypeCode="";*/
				
				EssentialMap=LocationDutyRosterDATA.getDutyAreaWithCapacityAndDesignationBasedOnRosterType(splitValues[0],_userVO);
				
				WebUTIL.setMapInSession(EssentialMap, _request);
				
				dutyAreaList=(ArrayList)EssentialMap.get(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA);
				
			if(dutyAreaList!=null && dutyAreaList.size()==1)
				{
				Entry obj=(Entry)dutyAreaList.get(0);
				String areaCode=obj.getValue();
				_fb.setAreaCode(areaCode);
				
				LocationDutyRosterUTL.getLocationWiseRosterEssentials(_request, _fb);
				
				}
				
			//	dutyAreaList=LocationDutyRosterDATA.getDutyAreaBasedOnDutyAreaType(areaTypeCode,_userVO);
				
			//	WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, dutyAreaList);
				
						
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
								
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA,  new ArrayList());
				
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA,  new ArrayList());
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
		



	
// Getting the Employees Based on Duty  Area type and Area  

			
			public static boolean getLocationWiseRosterEssentials(HttpServletRequest _request,LocationDutyRosterFB _fb)
			{
				Status objStatus = new Status();
				
				
				Map empRosterMap=new HashMap();
				Map empDutyAreaMap=new HashMap();
				List rosterList=new ArrayList();
				
				try
				{
					UserVO _userVO = getUserVO(_request);
					
		 
					String concateValues=_fb.getRosterId().replace("^","@");
					String splitValues[]=concateValues.split("@");
					
					String _rosterId=splitValues[0];
					String _areaTypeCode=splitValues[1];
					String _areaCode=_fb.getAreaCode().split("@")[0];
				

					empRosterMap=LocationDutyRosterDATA.getLocationWiseRosterEssentials(_areaTypeCode, _areaCode, _rosterId, _userVO);
					
					WebUTIL.setMapInSession(empRosterMap, _request);

// checking whether the area type is block or not and then 
// setting in session THE SIZE OF LIST OF LOCATIONWISE ROSTERS					

		if(_areaTypeCode.equals(DutyRosterConfig.DUTY_AREA_TYPE_CODE_BLOCK))
				{
					rosterList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_ROSTERS_LOCATION_WISE_FOR_BLOCK);
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.TOTAL_LOCATION_WISE_ROSTERS, rosterList.size());
				
				}
			else
			{
					rosterList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_ROSTERS_LOCATION_WISE_FOR_AREA);
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.TOTAL_LOCATION_WISE_ROSTERS, rosterList.size());
				
			}		

//if the roster list is not available then the new location wise roster is build
//and if the mode of rosters is NEW ,then also a new roster is build		
		
		if(rosterList.size()==0 || _fb.getModeOfRoster().equals("NEW"))
					LocationDutyRosterUTL.getNewLocationWiseRoster(_request,_fb,_areaTypeCode);
				else//else we build OLD roster
					LocationDutyRosterUTL.getListOfLocationWiseRoster(_request,_fb,_areaTypeCode);
				
					
						
					
					objStatus.add(Status.NEW);
				}
				catch (HisRecordNotFoundException e)
				{
				
					
					e.printStackTrace();
					
					objStatus.add(Status.ERROR_DA, "", e.getMessage());
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

			
// Getting the Employees Based on Duty  Area type and Area  

			
			public static boolean fetchLocationWiseRosterDetails(HttpServletRequest _request,LocationDutyRosterFB _fb)
			{
				Status objStatus = new Status();
				
				
			
				Map EssentialAreaMap=new HashMap();
				BlockwiseRosterDtlVO[] _blockWiseRosterVO=null;
				
				
				try
				{
					UserVO _userVO = getUserVO(_request);
					
		 
					String concateValues=_fb.getRosterId().replace("^","@");
					String splitValues[]=concateValues.split("@");
					
					String _rosterId=splitValues[0];
					String _areaTypeCode=splitValues[1];
					String _areaCode=_fb.getAreaCode().split("@")[0];
				

					String arrayOfDates[]=_fb.getDistinctRoster().split("@");
					
					String _startDate=arrayOfDates[0];
					String _endDate=arrayOfDates[1];
					

			//in case the area type code is block then the data is fetched according to the block basis 
					

		if(_areaTypeCode.equals(DutyRosterConfig.DUTY_AREA_TYPE_CODE_BLOCK))
				{
			EssentialAreaMap=LocationDutyRosterDATA.fetchLocationRosterAreaWise(_startDate, _endDate, _areaTypeCode, _areaCode, _rosterId, _userVO);
			WebUTIL.setMapInSession(EssentialAreaMap, _request);
					
			_blockWiseRosterVO=(BlockwiseRosterDtlVO[])EssentialAreaMap.get(DutyRosterConfig.VO_OF_ROSTERS_LOCATION_WISE_FOR_BLOCK);
				}
			else//else it is fetched on the basis of area code
			{
				EssentialAreaMap=LocationDutyRosterDATA.fetchLocationRosterAreaWise(_startDate, _endDate, _areaTypeCode, _areaCode, _rosterId, _userVO);
				WebUTIL.setMapInSession(EssentialAreaMap, _request);
				
				_blockWiseRosterVO=(BlockwiseRosterDtlVO[])EssentialAreaMap.get(DutyRosterConfig.VO_OF_ROSTERS_LOCATION_WISE_FOR_AREA);
			}		
		
					
					
					_fb.setFromDate(_blockWiseRosterVO[0].getFromDate());
					_fb.setToDate(_blockWiseRosterVO[0].getToDate());
					
	
			if(_fb.getHmode().equals("REPORT"))
					{
					LocationDutyRosterUTL.setReportHeader(_request, _fb);
					LocationDutyRosterUTL.getLocationWiseRosterReport(_request, _fb, _blockWiseRosterVO);
					}
					else
					LocationDutyRosterUTL.getOldLocationWiseRoster(_request, _fb, _blockWiseRosterVO);
				
					
						
					
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
	public static boolean saveLocationDutyRoster(LocationDutyRosterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
	
		try
		{
			UserVO userVO = getUserVO(_request);
			String[] arrayOfValues=_fb.getConcatedValueToBeSaved().split("#");
			
			BlockwiseRosterDtlVO[] _blockWiseRosterVO=LocationDutyRosterUTL.getRosterDtlVOsToBeSaved(arrayOfValues,_request,_fb);		
			
			String modifyStatus=_fb.getModeOfRoster();
			
			String startDateTimeOld=_fb.getStartDateTimeOld();
			String endDateTimeOld=_fb.getEndDateTimeOld();
			String	fromDateCheck=_fb.getFromDateCheck();
			String toDateCheck=_fb.getToDateCheck();
			
			for(int i=0; i <_blockWiseRosterVO.length ;i++)
				_blockWiseRosterVO[i].setIsGenerated(DutyRosterConfig.LOCATION_WISE_ROSTER_IS_NOT_GENERATED);
			
			LocationDutyRosterDATA.saveLocationDutyRoster(_blockWiseRosterVO, userVO,modifyStatus,startDateTimeOld,endDateTimeOld,fromDateCheck,toDateCheck);
			
			
			
			objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
		}
		
		catch (HisRecordNotFoundException e)
		{
			hasFlag=false;
			System.out.println("Inside HisRecordNotFoundException");
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			
		}
		catch (HisDataAccessException e)
		{
			hasFlag=false;
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "","Data Access Error");
			
		}
		catch (HisApplicationExecutionException e)
		{
			hasFlag=false;
			System.out.println("Inside HisApplicationExecutionException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			
		}
		catch (HisException e)
		{
			hasFlag=false;
			System.out.println("Inside HisException");
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
			
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
	

	

	
public static void  getNewLocationWiseRoster(HttpServletRequest _request,LocationDutyRosterFB _fb,String _areaTypeCode)

{
	List areaList=new ArrayList();
	List shiftList=new ArrayList();
	List desigList=new ArrayList();
	String shiftId="";
	String shiftName="";
	
	Map _rosterShiftCapMap=new LinkedHashMap();
	

	_rosterShiftCapMap=LocationDutyRosterUTL.getRosterShiftCapacityMapFromVO(_request);
	
	areaList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_AREA_BASED_ON_BLOCK);
	shiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
	desigList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE);
		
//Creating a string Buffer for Creating a New LocationWise Roster
///for the corresponding area type,areaCode and rosterType	
	StringBuilder strCal=new StringBuilder(); 
	
	strCal.append("<table width=\"100%\" border=\"1\" cellspacing=\"1\" cellpadding=\"0\">");
	strCal.append("<tr>");
	strCal.append("<td  class=\"tdfonthead\" width=\"15%\"><div align=\"center\">Locations/Shifts</div></td>");
	strCal.append("<td  class=\"tdfonthead\" width=\"15%\"><div align=\"center\">Designation</div></td>");

//Creating the No. Of Shifts for the corresponding Roster Type
for(int i=0 ; i < shiftList.size() ;i++)
{
	Entry shiftEntry = new Entry();
	shiftEntry=(Entry)shiftList.get(i);
	shiftId=shiftEntry.getValue();
	shiftName=shiftEntry.getLabel();
	String shiftNameArray[]=shiftName.split("@");
	
	strCal.append("<td  class=\"tdfonthead\" width=\"20%\" ><div align=\"center\">"+shiftNameArray[3]+"</div></td>");
}	
	


	



//Creating the Dynamic Location Wise Roster  based on the the Area  mapped 
//for the corresponding area type,areaCode and rosterType and BlockId


//iterating through the total No of Employees mapped and getting the employee ID and employee name
if(_areaTypeCode.equals(DutyRosterConfig.DUTY_AREA_TYPE_CODE_BLOCK))
{
for(int i=0 ; i <areaList.size();i++)
{
	Entry objEntryarea = new Entry();
	String areaIdAndAreaType="";
	String areaName="";
	
	objEntryarea=(Entry)areaList.get(i);
	areaIdAndAreaType=objEntryarea.getValue();
	areaName=objEntryarea.getLabel();
	
	strCal.append("<tr>");
	
	
	strCal.append("<td align=\"center\" class=\"tdfonthead\">"+((areaName==null)?"":areaName)+"</td>");
	
	//iterating through the total No of Designations  for the corresponding Roster Type

	for(int j=0 ; j < desigList.size();j++)
		{
		Entry objEntrydesig = new Entry();
		String desigId="";
		String desigName="";
		
		objEntrydesig=(Entry)desigList.get(j);
		desigId=objEntrydesig.getValue();
		desigName=objEntrydesig.getLabel();
		
if(j!=0)
		strCal.append("<td align=\"center\" class=\"tdfonthead\"></td>");

		strCal.append("<td align=\"center\" class=\"tdfont\">"+desigName+"</td>");
	
		//iterating through the total No of Shifts for the corresponding Roster Type

	
	for(int k=0 ; k < shiftList.size() ;k++)
		
			{
		int rowId=i+1;
		int desgId=j+1;
		int colId=k+1;
		
		String textBoxId=Integer.toString(rowId)+"@"+Integer.toString(desgId)+"@"+Integer.toString(colId);
		System.out.println("textBoxId--->"+textBoxId);
		
		Entry shiftEntry = new Entry();
		shiftEntry=(Entry)shiftList.get(k);
		shiftId=shiftEntry.getValue();
		shiftName=shiftEntry.getLabel();
		
		String[] shiftArray=shiftName.split("@");
		
		String textBoxName="";		
		
			//     	[    areaIdAndAreaType	  ]									  [       						shiftName					  					  ]									
//textbox name=		areaCode   + @ +   areaType  +  @ + desig + @ + shiftId + @ + shiftshortname + @ + starttime + @ + endtime + @ + shiftfullname + @ + shifttype

		
		textBoxName=areaIdAndAreaType+"@"+desigId+"@"+shiftId+"@"+shiftName;
		
		String val=_request.getParameter(textBoxName);
		
		if(val==null)
			val=(String)_rosterShiftCapMap.get(Integer.parseInt(shiftArray[4]));

		if(val==null)
			val="";
		
		System.out.println("name of TextBox in case of BLOCK --->"+textBoxName+"---val---"+val);
		
		
		strCal.append("<td align=\"center\" class=\"\"><input type=\"text\" id=\""+textBoxId+"\"  name=\""+textBoxName+"\"  value='"+val+"' tabindex='1'  maxlength=\"2\"   size=\"3\" onkeypress=\"return validatePositiveIntegerOnly(this,event)\" ></td>");
			}
	strCal.append("</tr>");
	
		}
	}

	strCal.append("<input type=\"hidden\" id=\"maxNoOfAreaMapped\" value=\""+areaList.size()+"\">");
}
else
{
		
	String areaName="";
	String areaCode="";
	areaCode=_fb.getAreaCode();
	List areaListBasedOnareaType=new ArrayList();
	
	areaListBasedOnareaType=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA);
	
	Iterator itr=areaListBasedOnareaType.iterator();
	while(itr.hasNext())
	{
	Entry obj=(Entry)itr.next();
	
	if(obj.getValue().equals(areaCode))
		areaName=obj.getLabel();
		
		
	}
		
	
	
	
		strCal.append("<tr>");
		
		
		strCal.append("<td align=\"center\" class=\"tdfont\">"+((areaName==null)?"":areaName)+"</td>");
		
		//iterating through the total No of Designations  for the corresponding Roster Type

		for(int i=0 ; i < desigList.size();i++)
	{
			Entry objEntrydesig = new Entry();
			String desigId="";
			String desigName="";
			
			objEntrydesig=(Entry)desigList.get(i);
			desigId=objEntrydesig.getValue();
			desigName=objEntrydesig.getLabel();
			
	
		if(i!=0)
			strCal.append("<td align=\"center\" class=\"tdfonthead\"></td>");			
			
			strCal.append("<td align=\"center\" class=\"tdfont\">"+desigName+"</td>");		
		

	//iterating through the total No Shifts
	//and creating the individual Text Boxes for each employee and each day	
		
		for(int j=0 ; j < shiftList.size() ;j++)
			
		{
			int rowId=1;
			int desgId=i+1;
			int colId=j+1;
			String textBoxId=Integer.toString(rowId)+"@"+Integer.toString(desgId)+"@"+Integer.toString(colId);
			System.out.println("textBoxId--->"+textBoxId);
			
			Entry shiftEntry = new Entry();
			shiftEntry=(Entry)shiftList.get(j);
			shiftId=shiftEntry.getValue();
			shiftName=shiftEntry.getLabel();
			
			String[] shiftArray=shiftName.split("@");
			
			String textBoxName="";		
			
			
			//     							  [       						shiftName					  					  ]									
//textbox name=		 desig + @ + shiftId + @ + shiftshortname + @ + starttime + @ + endtime + @ + shiftfullname + @ + shifttype

			
			String val=_request.getParameter(textBoxName);
			if(val==null)
				val=(String)_rosterShiftCapMap.get(Integer.parseInt(shiftArray[4]));
			
			if(val==null)
				val="";
			
			textBoxName=desigId+"@"+shiftId+"@"+shiftName;
			System.out.println("name of TextBox in normal area type--->"+textBoxName+"--val---"+val);
			
			strCal.append("<td align=\"center\" class=\"\"><input type=\"text\" id=\""+textBoxId+"\"   value='"+val+"'  tabindex=\"1\" name=\""+textBoxName+"\"     maxlength=\"2\"   size=\"3\" onkeypress=\"return validatePositiveIntegerOnly(this,event)\" ></td>");
		}
		strCal.append("</tr>");
	}	
		strCal.append("<input type=\"hidden\" id=\"maxNoOfAreaMapped\" value=\""+1+"\">");
}	
	
	
//Creating the Hidden Fields for the array of Shift Types,Start Time,End Time
//for the corresponding Roster Types



	strCal.append("<input type=\"hidden\" id=\"maxNoOfShiftsMapped\" value=\""+shiftList.size()+"\">");
	strCal.append("<input type=\"hidden\" id=\"maxNoOfDesignation\" value=\""+desigList.size()+"\">");
	
	strCal.append("</table>");
	
	
	
	_fb.setLocationWiseRoster(strCal.toString());
	
	
	
	}

public static void  getOldLocationWiseRoster(HttpServletRequest _request,LocationDutyRosterFB _fb,BlockwiseRosterDtlVO[] _blockWiseRosterVO)

{
	List areaList=new ArrayList();
	List shiftList=new ArrayList();
	List desigList=new ArrayList();
	String shiftId="";
	String shiftName="";
	
	
if(_blockWiseRosterVO[0].getBlockId()==null || _blockWiseRosterVO[0].getBlockId().equals(""))
		{
	
		}
	else
	   {
		areaList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_AREA_BASED_ON_BLOCK);
	   } 
	shiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
	desigList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE);
		
//Creating a string Buffer for Creating a New LocationWise Roster
///for the corresponding area type,areaCode and rosterType	
	StringBuilder strCal=new StringBuilder(); 
	
	strCal.append("<table width=\"100%\" border=\"1\" cellspacing=\"1\" cellpadding=\"0\">");
	strCal.append("<tr>");
	strCal.append("<td  class=\"tdfonthead\" width=\"15%\"><div align=\"center\">Locations/Shifts</div></td>");
	strCal.append("<td  class=\"tdfonthead\" width=\"15%\"><div align=\"center\">Designation</div></td>");

//Creating the No. Of Shifts for the corresponding Roster Type
for(int i=0 ; i < shiftList.size() ;i++)
{
	Entry shiftEntry = new Entry();
	shiftEntry=(Entry)shiftList.get(i);
	shiftId=shiftEntry.getValue();
	shiftName=shiftEntry.getLabel();
	String shiftNameArray[]=shiftName.split("@");
	
	strCal.append("<td class=\"tdfonthead\" width=\"20%\" ><div align=\"center\">"+shiftNameArray[3]+"</div></td>");
}	
	

int v=0;
	



//Creating the Dynamic Location Wise Roster  based on the the Area  mapped 
//for the corresponding area type,areaCode and rosterType and BlockId


//iterating through the total No of VO's
//if area type is not block then make the roster accordingly 
if(_blockWiseRosterVO[0].getBlockId()==null || _blockWiseRosterVO[0].getBlockId().equals(""))
{
	
	String areaName="";
	
	areaName=_blockWiseRosterVO[0].getAreaname();
	
	
	
		strCal.append("<tr>");
		
		
		strCal.append("<td class=\"tdfont\"><div align=\"center\">"+((areaName==null)?"":areaName)+"</div></td>");
		
		//iterating through the total No of Designations  for the corresponding Roster Type

		for(int i=0 ; i < desigList.size();i++)
	{
			Entry objEntrydesig = new Entry();
			String desigId="";
			String desigName="";
			
			objEntrydesig=(Entry)desigList.get(i);
			desigId=objEntrydesig.getValue();
			desigName=objEntrydesig.getLabel();
			
	
		if(i!=0)
			strCal.append("<td align=\"center\" class=\"tdfonthead\"></td>");			
			
			strCal.append("<td  class=\"tdfont\"><div align=\"center\">"+desigName+"</div></td>");		
		

	//iterating through the total No Shifts
	//and creating the individual Text Boxes for each employee and each day	
		
		for(int j=0 ; j < shiftList.size() ;j++)
			
		{
			int rowId=1;
			int desgId=i+1;
			int colId=j+1;
			String textBoxId=Integer.toString(rowId)+"@"+Integer.toString(desgId)+"@"+Integer.toString(colId);
			System.out.println("textBoxId--->"+textBoxId);
			
			Entry shiftEntry = new Entry();
			shiftEntry=(Entry)shiftList.get(j);
			shiftId=shiftEntry.getValue();
			shiftName=shiftEntry.getLabel();
			BlockwiseRosterDtlVO _blockWiseVO=null;
			
			String capacity="";
			String textBoxName="";		
			
	if(v< _blockWiseRosterVO.length){
			 _blockWiseVO=_blockWiseRosterVO[v];
			
			textBoxName=_blockWiseVO.getDesignationId()+"@"+_blockWiseVO.getShiftId()+"@"+_blockWiseVO.getShiftdesc();
			capacity=_blockWiseVO.getCapacity();
			System.out.println("name of TextBox in modify for normal area --->"+textBoxName+"----capacity----"+capacity);
			
			strCal.append("<td align=\"center\" class=\"\"><input type=\"text\" id=\""+textBoxId+"\" tabindex=\"1\" name=\""+textBoxName+"\" value=\""+capacity+"\" maxlength=\"2\"   size=\"3\" onkeypress=\"return validatePositiveIntegerOnly(this,event)\" ></td>");			
			
			v++;
			
									}
		}
		strCal.append("</tr>");
	}	
		strCal.append("<input type=\"hidden\" id=\"maxNoOfAreaMapped\" value=\""+1+"\">");
}	
else
{
for(int i=0 ; i <areaList.size();i++)
{
	Entry objEntryarea = new Entry();
	String areaIdAndAreaType="";
	String areaName="";
	
	objEntryarea=(Entry)areaList.get(i);
	areaIdAndAreaType=objEntryarea.getValue();
	areaName=objEntryarea.getLabel();
	
	strCal.append("<tr>");
	
	
	strCal.append("<td  class=\"tdfonthead\"><div align=\"center\" >"+((areaName==null)?"":areaName)+"</div></td>");
	
	//iterating through the total No of Designations  for the corresponding Roster Type

	for(int j=0 ; j < desigList.size();j++)
		{
		Entry objEntrydesig = new Entry();
		String desigId="";
		String desigName="";
		
		objEntrydesig=(Entry)desigList.get(j);
		desigId=objEntrydesig.getValue();
		desigName=objEntrydesig.getLabel();
		
if(j!=0)
		strCal.append("<td align=\"center\" class=\"tdfonthead\"></td>");

		strCal.append("<td  class=\"tdfont\"><div align=\"center\" >"+desigName+"</div></td>");
	
		//iterating through the total No of Shifts for the corresponding Roster Type

	
	for(int k=0 ; k < shiftList.size() ;k++)
		
			{
		int rowId=i+1;
		int desgId=j+1;
		int colId=k+1;
		
		String textBoxId=Integer.toString(rowId)+"@"+Integer.toString(desgId)+"@"+Integer.toString(colId);
		System.out.println("textBoxId--->"+textBoxId);
		
		Entry shiftEntry = new Entry();
		shiftEntry=(Entry)shiftList.get(k);
		shiftId=shiftEntry.getValue();
		shiftName=shiftEntry.getLabel();
		
		BlockwiseRosterDtlVO _blockWiseVO=null;		
		String capacity="";		
		String textBoxName="";
		
		
		
		if(v< _blockWiseRosterVO.length){
			 _blockWiseVO=_blockWiseRosterVO[v];
			
			textBoxName=areaIdAndAreaType+"@"+_blockWiseVO.getDesignationId()+"@"+_blockWiseVO.getShiftId()+"@"+_blockWiseVO.getShiftdesc();
			capacity=_blockWiseVO.getCapacity();
			System.out.println("name of TextBox for modify in case of block --->"+textBoxName+"----capacity----"+capacity);
			
			strCal.append("<td align=\"center\" class=\"\"><input type=\"text\" id=\""+textBoxId+"\"  tabindex='1' name=\""+textBoxName+"\"  value=\""+capacity+"\"  maxlength=\"2\"   size=\"3\" onkeypress=\"return validatePositiveIntegerOnly(this,event)\" ></td>");
			
			v++;
	
										}
		}
	strCal.append("</tr>");
	
		}
	}

	strCal.append("<input type=\"hidden\" id=\"maxNoOfAreaMapped\" value=\""+areaList.size()+"\">");
}


	
//Creating the Hidden Fields for the array of Shift Types,Start Time,End Time
//for the corresponding Roster Types



	strCal.append("<input type=\"hidden\" id=\"maxNoOfShiftsMapped\" value=\""+shiftList.size()+"\">");
	strCal.append("<input type=\"hidden\" id=\"maxNoOfDesignation\" value=\""+desigList.size()+"\">");
	
	strCal.append("</table>");
	
	
	
	_fb.setLocationWiseRoster(strCal.toString());
	
	
	
	}


public static void  getLocationWiseRosterReport(HttpServletRequest _request,LocationDutyRosterFB _fb,BlockwiseRosterDtlVO[] _blockWiseRosterVO)
{

	List areaList=new ArrayList();
	List shiftList=new ArrayList();
	List desigList=new ArrayList();
	String shiftId="";
	String shiftName="";
	
	
if(_blockWiseRosterVO[0].getBlockId()==null || _blockWiseRosterVO[0].getBlockId().equals(""))
		{
	
		}
	else
	   {
		areaList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_AREA_BASED_ON_BLOCK);
	   } 
	shiftList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_SHIFTS_ON_BASIS_OF_ROSTER_TYPE);
	desigList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE);
		
//Creating a string Buffer for Creating a New LocationWise Roster
///for the corresponding area type,areaCode and rosterType	
	StringBuilder strCal=new StringBuilder(); 
	
	strCal.append("<table width=\"100%\" border=\"1\" cellspacing=\"1\" cellpadding=\"0\">");
	strCal.append("<tr>");
	strCal.append("<td   width=\"15%\"><div align=\"center\">Locations/Shifts</div></td>");
	strCal.append("<td   width=\"15%\"><div align=\"center\">Designation</div></td>");

//Creating the No. Of Shifts for the corresponding Roster Type
for(int i=0 ; i < shiftList.size() ;i++)
{
	Entry shiftEntry = new Entry();
	shiftEntry=(Entry)shiftList.get(i);
	shiftId=shiftEntry.getValue();
	shiftName=shiftEntry.getLabel();
	String shiftNameArray[]=shiftName.split("@");
	
	strCal.append("<td  width=\"20%\" ><div align=\"center\">"+shiftNameArray[3]+"</div></td>");
}	
	

int v=0;
	



//Creating the Dynamic Location Wise Roster  based on the the Area  mapped 
//for the corresponding area type,areaCode and rosterType and BlockId


//iterating through the total No of VO's
//if area type is not block then make the roster accordingly 
if(_blockWiseRosterVO[0].getBlockId()==null || _blockWiseRosterVO[0].getBlockId().equals(""))
{
	
	String areaName="";
	
	areaName=_blockWiseRosterVO[0].getAreaname();
	
	
	
		strCal.append("<tr>");
		
		
		strCal.append("<td ><div align=\"center\">"+((areaName==null)?"":areaName)+"</div></td>");
		
		//iterating through the total No of Designations  for the corresponding Roster Type

		for(int i=0 ; i < desigList.size();i++)
	{
			Entry objEntrydesig = new Entry();
			String desigId="";
			String desigName="";
			
			objEntrydesig=(Entry)desigList.get(i);
			desigId=objEntrydesig.getValue();
			desigName=objEntrydesig.getLabel();
			
	
		if(i!=0)
			strCal.append("<td align=\"center\" ></td>");			
			
			strCal.append("<td  ><div align=\"center\">"+desigName+"</div></td>");		
		

	//iterating through the total No Shifts
	//and creating the individual Text Boxes for each employee and each day	
		
		for(int j=0 ; j < shiftList.size() ;j++)
			
		{
			int rowId=1;
			int desgId=i+1;
			int colId=j+1;
			String textBoxId=Integer.toString(rowId)+"@"+Integer.toString(desgId)+"@"+Integer.toString(colId);
			//System.out.println("textBoxId--->"+textBoxId);
			
			Entry shiftEntry = new Entry();
			shiftEntry=(Entry)shiftList.get(j);
			shiftId=shiftEntry.getValue();
			shiftName=shiftEntry.getLabel();
			BlockwiseRosterDtlVO _blockWiseVO=null;
			
			String capacity="";
			String textBoxName="";		
			
	if(v< _blockWiseRosterVO.length){
			 _blockWiseVO=_blockWiseRosterVO[v];
			
			textBoxName=_blockWiseVO.getDesignationId()+"@"+_blockWiseVO.getShiftId()+"@"+_blockWiseVO.getShiftdesc();
			capacity=_blockWiseVO.getCapacity();
			//System.out.println("name of TextBox in modify for normal area --->"+textBoxName+"----capacity----"+capacity);
			
			strCal.append("<td align=\"center\" class=\"\">"+capacity+"</td>");			
			
			v++;
			
									}
		}
		strCal.append("</tr>");
	}	
		strCal.append("<input type=\"hidden\" id=\"maxNoOfAreaMapped\" value=\""+1+"\">");
}	
else
{
for(int i=0 ; i <areaList.size();i++)
{
	Entry objEntryarea = new Entry();
	String areaIdAndAreaType="";
	String areaName="";
	
	objEntryarea=(Entry)areaList.get(i);
	areaIdAndAreaType=objEntryarea.getValue();
	areaName=objEntryarea.getLabel();
	
	strCal.append("<tr>");
	
	
	strCal.append("<td ><div align=\"center\" >"+((areaName==null)?"":areaName)+"</div></td>");
	
	//iterating through the total No of Designations  for the corresponding Roster Type

	for(int j=0 ; j < desigList.size();j++)
		{
		Entry objEntrydesig = new Entry();
		String desigId="";
		String desigName="";
		
		objEntrydesig=(Entry)desigList.get(j);
		desigId=objEntrydesig.getValue();
		desigName=objEntrydesig.getLabel();
		
if(j!=0)
		strCal.append("<td align=\"center\" class=\"\"></td>");

		strCal.append("<td ><div align=\"center\" >"+desigName+"</div></td>");
	
		//iterating through the total No of Shifts for the corresponding Roster Type

	
	for(int k=0 ; k < shiftList.size() ;k++)
		
			{
		int rowId=i+1;
		int desgId=j+1;
		int colId=k+1;
		
		String textBoxId=Integer.toString(rowId)+"@"+Integer.toString(desgId)+"@"+Integer.toString(colId);
		System.out.println("textBoxId--->"+textBoxId);
		
		Entry shiftEntry = new Entry();
		shiftEntry=(Entry)shiftList.get(k);
		shiftId=shiftEntry.getValue();
		shiftName=shiftEntry.getLabel();
		
		BlockwiseRosterDtlVO _blockWiseVO=null;		
		String capacity="";		
		String textBoxName="";
		
		
		
		if(v< _blockWiseRosterVO.length){
			 _blockWiseVO=_blockWiseRosterVO[v];
			
			textBoxName=areaIdAndAreaType+"@"+_blockWiseVO.getDesignationId()+"@"+_blockWiseVO.getShiftId()+"@"+_blockWiseVO.getShiftdesc();
			capacity=_blockWiseVO.getCapacity();
			System.out.println("name of TextBox for modify in case of block --->"+textBoxName+"----capacity----"+capacity);
			
			strCal.append("<td align=\"center\" class=\"\">"+capacity+"</td>");
			
			v++;
	
										}
		}
	strCal.append("</tr>");
	
		}
	}

	strCal.append("<input type=\"hidden\" id=\"maxNoOfAreaMapped\" value=\""+areaList.size()+"\">");
}


	
//Creating the Hidden Fields for the array of Shift Types,Start Time,End Time
//for the corresponding Roster Types



	strCal.append("<input type=\"hidden\" id=\"maxNoOfShiftsMapped\" value=\""+shiftList.size()+"\">");
	strCal.append("<input type=\"hidden\" id=\"maxNoOfDesignation\" value=\""+desigList.size()+"\">");
	
	strCal.append("</table>");
	
	
	
	_fb.setLocationWiseRoster(strCal.toString());
	
	
	
		

}

public static void  getListOfLocationWiseRoster(HttpServletRequest _request,LocationDutyRosterFB _fb,String _areaTypeCode)

{
	
	
	List rosterList=new ArrayList();

	
	if(_areaTypeCode.equals(DutyRosterConfig.DUTY_AREA_TYPE_CODE_BLOCK))
		rosterList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_ROSTERS_LOCATION_WISE_FOR_BLOCK);
	else			
		rosterList=(ArrayList)WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.LIST_OF_ROSTERS_LOCATION_WISE_FOR_AREA);

String select="Select";
	
		
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
if(rosterList!=null && rosterList.size() >0 )
{
	
for(int i=0 ; i < rosterList.size() ;i++)
	{
	String bgColor=""; 
	
	
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
	
	
	if(arrayOfDates[2].equals("1"))
		bgColor=DutyRosterConfig.LOCATION_WISE_GENERATED_ROSTER_COLOR;
	
	strCal.append("<tr>");
	strCal.append("<td  width=\"10%\" class=\"tdfont\"><div align=\"center\"><input type=\"radio\" name=\"distinctRoster\" value=\""+concateDate+"\" ></div></td>");
	strCal.append("<td align=\"center\" width=\"45%\" class=\"\" bgcolor='"+bgColor+"'>"+startDate+"</td>");
	strCal.append("<td align=\"center\" width=\"45%\" class=\"\" bgcolor='"+bgColor+"'>"+endDate+"</td>");
	strCal.append("</tr>");
	}	
	
}




	
	
	
	strCal.append("</table>");
	
	
	strCal.append("<div align=\"center\" class=\"tdfont\">");
	
	//strCal.append("<input type=\"button\" name=\"\" onclick=\"getNewRoster()\" value=\"  NEW  \" />"); 
	//strCal.append("<input type=\"button\" name=\"\" onclick=\"modifyRoster()\" value=\"MODIFY\" />");
	//strCal.append("<input type=\"button\" name=\"\" onclick=\"generateRoster()\" value=\"GENERATE\" />");
	//strCal.append("<input type=\"button\" name=\"\" onclick=\"generateReport()\" value=\"REPORT\" />");
	
	strCal.append("<img class=\"button\" src='/../HIS/hisglobal/images/buttons/btn-new.png' tabindex=\"1\" style=\"cursor: pointer\" onclick=\"getNewRoster()\"  onkeypress=\"if(event.keyCode==13) getNewRoster()\">");
	strCal.append("<img class=\"button\" src='/../HIS/hisglobal/images/buttons/btn-mo.png' tabindex=\"1\" style=\"cursor: pointer\" onclick=\"modifyRoster()\" onkeypress=\"if(event.keyCode==13) modifyRoster()\">");
	strCal.append("<img class=\"button\" src='/../HIS/hisglobal/images/buttons/btn-generate.png' tabindex=\"1\" style=\"cursor: pointer\" onclick=\"generateRoster()\" onkeypress=\"if(event.keyCode==13) generateRoster()\">");
	strCal.append("<img class=\"button\" src='/../HIS/hisglobal/images/buttons/btn-rpt.png' tabindex=\"1\" style=\"cursor: pointer\" onclick=\"generateReport()\" onkeypress=\"if(event.keyCode==13) generateReport()\">");
	

	
	strCal.append("</div>");
	
	
	_fb.setDistinctRosterList(strCal.toString());
	
	
	
	}

public static BlockwiseRosterDtlVO[] getRosterDtlVOsToBeSaved(String[] arrayOfValues,HttpServletRequest _request,LocationDutyRosterFB _fb)

{
	
	Map  monthMap=LocationDutyRosterUTL.getMonthMap();
	
	String rosterIdsConcated=_fb.getRosterId().replace("^", "@");
	String[] arrayOfRosterIdDetails=rosterIdsConcated.split("@");
	
	final long  MILLI_SEC_IN_EACH_DAY=1000*60*60*24;
		
	
	
	
	int k=0	;
	String[] startDateArray=_fb.getFromDate().split("-");
	String[] endDateArray=_fb.getToDate().split("-");
	
	
	
	
	//First Creating a startCalendar and endCalendar GregorianCalendar
	Calendar startCalendar =new GregorianCalendar();	
	Calendar endCalendar =new GregorianCalendar();
	Calendar[] tempCalendar=null;

	startCalendar.set(Integer.parseInt(startDateArray[2]),(Integer)monthMap.get(startDateArray[1]) ,Integer.parseInt(startDateArray[0]));
	endCalendar.set(Integer.parseInt(endDateArray[2]),(Integer)monthMap.get(endDateArray[1]) ,Integer.parseInt(endDateArray[0]));
	
	
	
	long differenceOfDaysInMilliSec=(endCalendar.getTimeInMillis()-startCalendar.getTimeInMillis())/MILLI_SEC_IN_EACH_DAY;
	
	
	//First Computing the difference between the two  dates 
	
	int differenceOfDays=(int)differenceOfDaysInMilliSec;
	
	
	
	
	List listOfdays=new ArrayList();
	
	tempCalendar=new GregorianCalendar[differenceOfDays];
	
	listOfdays.add(startCalendar);
	
	for(int i=0; i < differenceOfDays;i++)
		{
		tempCalendar[i]=new GregorianCalendar();

		tempCalendar[i].set(Integer.parseInt(startDateArray[2]),(Integer)monthMap.get(startDateArray[1]) ,Integer.parseInt(startDateArray[0]));
		
		tempCalendar[i].add(Calendar.DATE, i+1);
		             
	
		 listOfdays.add(tempCalendar[i]);
		
		}
	
	
	
//	RosterDtlVO[] _rosterDtlVO=new RosterDtlVO[arrayOfEmployees.length*listOfdays.size()];
	
	BlockwiseRosterDtlVO[] _blockWiseRosterVO=new BlockwiseRosterDtlVO[arrayOfValues.length*listOfdays.size()];
	
//if the area type code is block	
if(arrayOfRosterIdDetails[1].equals(DutyRosterConfig.DUTY_AREA_TYPE_CODE_BLOCK))
{	

	
	
	


	
	for(int i=0 ;i< arrayOfValues.length ;i++)
	    {
		
		String[] arrayOfVODetails=arrayOfValues[i].split("@");     
		
		for(int j=0; j< listOfdays.size(); j++)
		{
		
			_blockWiseRosterVO[k]=new BlockwiseRosterDtlVO();

		
			
		Calendar calendarFromList =new GregorianCalendar();	
		
		calendarFromList=(GregorianCalendar)listOfdays.get(j);
			
		String startDateTime=calendarFromList.get(Calendar.DATE)+"/"+Integer.toString(calendarFromList.get(Calendar.MONTH)+1)+"/"+calendarFromList.get(Calendar.YEAR)+" "+arrayOfVODetails[5]+":00";
		String endDateTime=calendarFromList.get(Calendar.DATE)+"/"+Integer.toString(calendarFromList.get(Calendar.MONTH)+1)+"/"+calendarFromList.get(Calendar.YEAR)+" "+arrayOfVODetails[6]+":00";		
		

	
		_blockWiseRosterVO[k].setRosterId(arrayOfRosterIdDetails[0]);
		_blockWiseRosterVO[k].setAreaCode(arrayOfVODetails[0]);
		_blockWiseRosterVO[k].setAreaTypeCode(arrayOfVODetails[1]);
		_blockWiseRosterVO[k].setDesignationId(arrayOfVODetails[2]);
		_blockWiseRosterVO[k].setShiftId(arrayOfVODetails[3]);
		_blockWiseRosterVO[k].setCapacity(arrayOfVODetails[9]);		
		_blockWiseRosterVO[k].setStartDateTime(startDateTime);
		_blockWiseRosterVO[k].setEndDateTime(endDateTime);				
		_blockWiseRosterVO[k].setFromDate(_fb.getFromDate());
		_blockWiseRosterVO[k].setToDate(_fb.getToDate());		
		_blockWiseRosterVO[k].setBlockId(_fb.getAreaCode().split("@")[0]);
		
			k++;
		
		}
		
	      
		
		
	  
  }	
}else//else if the area type code is not block
{
	

	for(int i=0 ;i< arrayOfValues.length ;i++)
		{
	
	
		
			
		String[] arrayOfVODetails=arrayOfValues[i].split("@");           
		
		for(int j=0; j< listOfdays.size(); j++)
		{
		
			_blockWiseRosterVO[k]=new BlockwiseRosterDtlVO();
			
			Calendar calendarFromList =new GregorianCalendar();	
			
			calendarFromList=(GregorianCalendar)listOfdays.get(j);
				
			String startDateTime=calendarFromList.get(Calendar.DATE)+"/"+Integer.toString(calendarFromList.get(Calendar.MONTH)+1)+"/"+calendarFromList.get(Calendar.YEAR)+" "+arrayOfVODetails[3]+":00";
			String endDateTime=calendarFromList.get(Calendar.DATE)+"/"+Integer.toString(calendarFromList.get(Calendar.MONTH)+1)+"/"+calendarFromList.get(Calendar.YEAR)+" "+arrayOfVODetails[4]+":00";		
			

			

	
		_blockWiseRosterVO[k].setRosterId(arrayOfRosterIdDetails[0]);
		_blockWiseRosterVO[k].setAreaCode(_fb.getAreaCode().split("@")[0]);
		_blockWiseRosterVO[k].setAreaTypeCode(arrayOfRosterIdDetails[1]);
		_blockWiseRosterVO[k].setDesignationId(arrayOfVODetails[0]);
		_blockWiseRosterVO[k].setShiftId(arrayOfVODetails[1]);
		_blockWiseRosterVO[k].setCapacity(arrayOfVODetails[7]);
		_blockWiseRosterVO[k].setStartDateTime(startDateTime);
		_blockWiseRosterVO[k].setEndDateTime(endDateTime);	
		_blockWiseRosterVO[k].setFromDate(_fb.getFromDate());
		_blockWiseRosterVO[k].setToDate(_fb.getToDate());		
	
		
		k++;
		
		}	
		
	  
   }

}
	WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LOCATION_WISE_VO_ROSTER_DETAILS, _blockWiseRosterVO);
	return _blockWiseRosterVO;

	
}
 

public static Map  getRosterShiftCapacityMapFromVO(HttpServletRequest _request)
{
RosterAreaCapacityMstVO _rosterAreaCapacity=new RosterAreaCapacityMstVO();
Map _shiftCapacityMap=new LinkedHashMap();

_rosterAreaCapacity=(RosterAreaCapacityMstVO)_request.getSession().getAttribute(DutyRosterConfig.VO_OF_ROSTER_SHIFT_CAPACITY);


_shiftCapacityMap.put(DutyRosterConfig.MORNING_SHIFT_TYPE_ID, _rosterAreaCapacity.getMorningCapacity());
_shiftCapacityMap.put(DutyRosterConfig.EVENING_SHIFT_TYPE_ID, _rosterAreaCapacity.getEveningCapacity());
_shiftCapacityMap.put(DutyRosterConfig.NIGHT_SHIFT_TYPE_ID, _rosterAreaCapacity.getNightCapacity());
_shiftCapacityMap.put(DutyRosterConfig.DAY_SHIFT_TYPE_ID, _rosterAreaCapacity.getDayCapacity());
_shiftCapacityMap.put(DutyRosterConfig.EARLY_MORNING_SHIFT_TYPE_ID, _rosterAreaCapacity.getEarlyMorningCapacity());


return _shiftCapacityMap;
}




public static boolean generateLocationWiseRoster(HttpServletRequest request,
		LocationDutyRosterFB _fb) {

	Status objStatus = new Status();
	
	boolean flag=true;

	
	
	
	try
	{
		UserVO _userVO = getUserVO(request);
		

		String concateValues=_fb.getRosterId().replace("^","@");
		String splitValues[]=concateValues.split("@");
		
		String _rosterId=splitValues[0];
		String _areaTypeCode=splitValues[1];
		String _areaCode=_fb.getAreaCode().split("@")[0];
	

		String arrayOfDates[]=_fb.getDistinctRoster().split("@");
		
		String _startDate=arrayOfDates[0];
		String _endDate=arrayOfDates[1];
		

//in case the area type code is block then the data is fetched according to the block basis 
		


LocationDutyRosterDATA.generateLocationWiseRoster(_startDate, _endDate, _areaTypeCode, _areaCode, _rosterId, _userVO);

		

	
	objStatus.add(Status.DONE, "Roster Generated Successfully", "");
	}
	catch (HisRecordNotFoundException e)
	{
		flag=false;
		
		e.printStackTrace();
		
		objStatus.add(Status.NEW, "", e.getMessage());
	}
	catch (HisDataAccessException e)
	{
		flag=false;	
		System.out.println("Inside HisDataAccessException");
		e.printStackTrace();
		objStatus.add(Status.ERROR_DA, "", "Duty Area Not Found for the Corresponding Duty Area Type");
	}
	catch (HisApplicationExecutionException e)
	{
		flag=false;
		System.out.println("Inside HisApplicationExecutionException");
		objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
	}
	catch (HisException e)
	{
		flag=false;
		System.out.println("Inside HisException");
		objStatus.add(Status.ERROR, "", "Error");
	}
	finally
	{
		flag=false;
		WebUTIL.setStatus(request, objStatus);
		System.out.println("   -----> objStatus in finally  : " + objStatus);
		System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
	}
	return flag;

	
}


public static Map  getMonthMap() { 

	Map monthMap=new HashMap();
	
	monthMap.put("Jan", 0);
	monthMap.put("Feb", 1);
	monthMap.put("Mar", 2);
	monthMap.put("Apr", 3);
	monthMap.put("May", 4);
	monthMap.put("Jun", 5);
	monthMap.put("Jul", 6);
	monthMap.put("Aug", 7);
	monthMap.put("Sep", 8);
	monthMap.put("Oct", 9);
	monthMap.put("Nov", 10);
	monthMap.put("Dec", 11);
	
	return monthMap;
	
}

public static void setReportHeader(HttpServletRequest _request,LocationDutyRosterFB _fb) { 

	List dutyRosterList=new ArrayList();
	List dutyAreaList=new ArrayList();
	
	dutyRosterList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_ROSTERS_AND_AREA_TYPE_HAVING_ROSTER_MODE_LOCATION);
	
	dutyAreaList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA);
	
	String rosterName="";
	String areaName="";

	Iterator itr1=dutyRosterList.iterator();
	Iterator itr2=dutyAreaList.iterator();
	
	
	while(itr1.hasNext())
	{
		Entry obj=(Entry)itr1.next();
		if(obj.getValue().equals(_fb.getRosterId()))
			rosterName=obj.getLabel();
		
	}
	
	
	while(itr2.hasNext())
	{
		Entry obj=(Entry)itr2.next();
		if(obj.getValue().equals(_fb.getAreaCode()))
			areaName=obj.getLabel();
		
	}
	
	
	String reportHeader="Location Wise Capacity Report of "+rosterName+"(Roster), "+"for "+areaName+"(Area)";
	_fb.setReportHeader(reportHeader);
	
}



}
