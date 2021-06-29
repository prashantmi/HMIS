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
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;  
import dutyroster.reports.controller.data.EmpDailyWorkReportDATA;
import dutyroster.reports.controller.fb.EmpDailyWorkReportFB;


public class EmpDailyWorkReportUTL extends ControllerUTIL
{

	//For getting the roster main category
	
	public static boolean getListOfRoleBasedRosterMainCategory(HttpServletRequest _request,EmpDailyWorkReportFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterMainCategoryList=new ArrayList();
		
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
			
			
			String systemDate = hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date) _request.getSession().getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
			
			_fb.setWorkingDate(systemDate);
		
					
			dutyRosterMainCategoryList=EmpDailyWorkReportDATA.getListOfRoleBasedRosterMainCategory(_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY, dutyRosterMainCategoryList);
			
						
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
	
	
// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_PERSONNEL_DTL and  GBLT_DESIGNATION_MST

	public static boolean getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(HttpServletRequest _request,EmpDailyWorkReportFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterCategoryList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
			
				
			/********Initally setting all the values *************/
			
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTER_CATEGORY, new ArrayList());
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY, new ArrayList());
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, new ArrayList());
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.EMP_WORK_REPORT_MAP, new LinkedHashMap());
			
			_fb.setRosterCategory("-1");
			_fb.setRosterId("-1");
			_fb.setAreaCode("-1");
			_fb.setEmpId("-1");
			
			
			/********Initally setting all the values *************/
		
					
			dutyRosterCategoryList=EmpDailyWorkReportDATA.getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(_fb.getRosterMainCategory(),_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTER_CATEGORY, dutyRosterCategoryList);
			
						
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
	
	
	// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_PERSONNEL_DTL and  GBLT_DESIGNATION_MST

	public static boolean getListOfRoleBasedRostersOnTheBasisOfRosterCategory(HttpServletRequest _request,EmpDailyWorkReportFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterList=new ArrayList();
		String currentYear="";
		
		try
		{
			
			/********Initally setting all the values *************/
			
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY, new ArrayList());
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, new ArrayList());
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.EMP_WORK_REPORT_MAP, new LinkedHashMap());
			
		
			_fb.setRosterId("-1");
			_fb.setAreaCode("-1");
			_fb.setEmpId("-1");
			
			
			/********Initally setting all the values *************/
			
			UserVO _userVO = getUserVO(_request);
		
			dutyRosterList=EmpDailyWorkReportDATA.getListOfRoleBasedRostersOnTheBasisOfRosterCategory(_fb.getRosterCategory(),_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY, dutyRosterList);
			
			
			if(dutyRosterList.size()==1)
			{
				Entry obj=(Entry)dutyRosterList.get(0);
				String rostId=obj.getValue();
				_fb.setRosterId(rostId);
				EmpDailyWorkReportUTL.getDutyAreasBasedOnRosterType(_request, _fb);
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
		
		public static boolean getDutyAreasBasedOnRosterType(HttpServletRequest _request,EmpDailyWorkReportFB _fb)
		{
			Status objStatus = new Status();
			
			Map	EssentialMap =new HashMap();
			List dutyAreaList=new ArrayList();
			List desigList=new ArrayList();
			
			
			try
			{
			
				/********Initally setting all the values *************/
				
				
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.EMP_WORK_REPORT_MAP, new LinkedHashMap());
				
			
				
				_fb.setAreaCode("-1");
				_fb.setEmpId("-1");
				
				
				/********Initally setting all the values *************/
				
				
			
				UserVO _userVO = getUserVO(_request);
			
	 
				//fetching the Roster ID Information from the Concatinated Values 
				String concateValues=_fb.getRosterId().replace("^","@");
				String splitValues[]=null;
					
				if(!_fb.getRosterId().equals("-1"))
					splitValues=concateValues.split("@");
				
				String _rosterId="-1";
				String areaTypeCode="-1";
				
				if(splitValues!=null)
						{	
						_rosterId=splitValues[0];
				 		areaTypeCode=splitValues[1];
						}		
				
				dutyAreaList=EmpDailyWorkReportDATA.getDutyAreasBasedOnRosterType( _rosterId,_userVO);
				
			
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, dutyAreaList);
				
				dutyAreaList=(ArrayList)EssentialMap.get(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA);

				
							
				objStatus.add(Status.NEW);
		}
			catch (HisRecordNotFoundException e)
			{
				
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
							
				
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
								
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
//		 from the Package PKG_DUTY_ROSTER using the Procedure Proc_get_duty_area
		
		public static boolean getListOfAllMappedEmployeesHavingUserId(HttpServletRequest _request,EmpDailyWorkReportFB _fb)
		{
			Status objStatus = new Status();
			
			Map	EssentialMap =new HashMap();
			List empList=new ArrayList();
			
			
			
			try
			{
			
				
				/********Initally setting all the values *************/
				
				
				
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.EMP_WORK_REPORT_MAP, new LinkedHashMap());
				
									
				_fb.setEmpId("-1");
			
				
				/********Initally setting all the values *************/
			
				UserVO _userVO = getUserVO(_request);
			
	 
				//fetching the Roster ID Information from the Concatinated Values 
				String concateValues=_fb.getRosterId().replace("^","@");
				String splitValues[]=concateValues.split("@");
				
				String _rosterId=splitValues[0];
				String areaTypeCode=splitValues[1];
			

				
				
			
				
				empList=EmpDailyWorkReportDATA.getListOfAllMappedEmployeesHavingUserId(_rosterId,_fb.getAreaCode(),areaTypeCode,_userVO);
				
			
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, empList);
				
				
			Iterator itr=empList.iterator();
					while(itr.hasNext())
					{
						Entry obj=(Entry)itr.next();
							
							if(obj.getValue().equals(_fb.getEmpId()))
								_fb.setEmpName(obj.getLabel());
						
					}
			
				
							
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
				
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE, new ArrayList());
							
				
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
								
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

			
			public static boolean getEmpDailyWorkReport(HttpServletRequest _request,EmpDailyWorkReportFB _fb)
			{
				Status objStatus = new Status();
				
				Map EssentialMap=new LinkedHashMap();
				
				
				
				
				
				
				
				try
				{
					
					

					/********Initally setting all the values *************/
					
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.EMP_WORK_REPORT_MAP, new LinkedHashMap());
					
					/********Initally setting all the values *************/
					
					
					
					
					UserVO _userVO = getUserVO(_request);
				
					
					
					String[] empArray=null;
				
					if(!_fb.getEmpId().equals("-1"))	
							empArray=_fb.getEmpId().split("@");
					
					String empId="-1";
					
					if(empArray!=null)
							{
							empId=empArray[0];
							_fb.setDesignationName(empArray[2]);
							}
					
					
					String concateValues=_fb.getRosterId().replace("^","@");
					String splitValues[]=concateValues.split("@");
					
					String _rosterId=splitValues[0];
				
					String _areaTypeCode=splitValues[1];
					String _areaCode=_fb.getAreaCode();
					
//Getting the employee work report  whose Roster has been prepared on the basis of  
//rosterId,areaTypeCode and areaCode and emp 		
					
					
					EssentialMap=EmpDailyWorkReportDATA.getEmpDailyWorkReport(_rosterId,_areaTypeCode, _areaCode,empId,_fb.getWorkingDate(), _userVO);
				
				WebUTIL.setMapInSession(EssentialMap, _request);

				
				
				//fUNCTION USED FOR CALCULATING THE WORK REPORT AND SETTING IT TO DISPLAY IT
				
				EmpDailyWorkReportUTL.setEmpWorkReportMap(EssentialMap,_request, _fb);
				
				
				//Getting the emp List and Iterating through it 
				//and setting the emp name
				
				List empList=new ArrayList();
					empList=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.LIST_OF_EMP_BASED_ON_AREA_TYPE_AND_AREA_CODE);
				
					
				
				Iterator itr=empList.iterator();
						while(itr.hasNext())
						{
							Entry obj=(Entry)itr.next();
								
								if(obj.getValue().equals(_fb.getEmpId()))
									_fb.setEmpName(obj.getLabel());
							
						}
				
				
					
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

			
	
	







//Making the Map from the array of Vos 
public static void setEmpWorkReportMap(Map EssentialMap,HttpServletRequest _request,EmpDailyWorkReportFB _fb)
{
	
	
	
	
	Map empWorkReportMap=new LinkedHashMap();

	RosterDtlVO[] _rosterDtlVO=null;
	List roleList=new ArrayList();
	
	try
	{
	
		_rosterDtlVO=(RosterDtlVO[])EssentialMap.get(DutyRosterConfig.EMP_WORK_REPORT_LIST);	
	
		roleList=(ArrayList)EssentialMap.get(DutyRosterConfig.ESSENTIAL_ALL_DUTY_ROLE_LIST);	
	
if(_rosterDtlVO!=null)
{		
		for(int i=0; i <_rosterDtlVO.length; i++)
		{
			String areaKey=_rosterDtlVO[i].getAreaName();
			String shiftKey=_rosterDtlVO[i].getShiftId()+"@"+_rosterDtlVO[i].getShiftDesc();
			
			Map shiftMap=new LinkedHashMap();
			List roleNamesList=new ArrayList();
			
			
			if(empWorkReportMap.containsKey(areaKey))
				shiftMap=(LinkedHashMap)empWorkReportMap.get(areaKey);
			
			if(shiftMap.containsKey(shiftKey))
				roleNamesList=(List)shiftMap.get(shiftKey);
			
				
				
				
				
			String[] dutyRoleIdArray=null;
			
					
			if(_rosterDtlVO[i].getRoleId()!=null)
				dutyRoleIdArray=_rosterDtlVO[i].getRoleId().split("#");
			
			
			
if(dutyRoleIdArray!=null)
{	
			for(int j=0; j < dutyRoleIdArray.length;j++)
			{

				
if(roleList!=null)
	{	
				
			for(int k=0; k < roleList.size();k++)
				{
				Entry obj=(Entry)roleList.get(k);
				
				
				
				if(dutyRoleIdArray[j].equals(obj.getValue()))
						{
						roleNamesList.add(obj.getLabel());
						break;
						}
				
				}//for of role list closed
	}	
				
				
			}//for of roles arrray closed
}			
			shiftMap.put(shiftKey, roleNamesList);
			empWorkReportMap.put(areaKey, shiftMap);
			
		}//for of roster dtl vo closed 
		
		
		
		
		
	}
}	
	catch (HisException e)
	{
		e.printStackTrace();
	}
	finally
	{
		WebUTIL.setAttributeInSession(_request, DutyRosterConfig.EMP_WORK_REPORT_MAP, empWorkReportMap);
	}
	
	
}





}
