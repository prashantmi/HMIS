
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
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest; 

import dutyroster.DutyRosterConfig; 
import dutyroster.transaction.controller.data.EmpRosterGenerationDATA;
import dutyroster.transaction.controller.fb.EmpRosterGenerationFB;


public class EmpRosterGenerationUTL extends ControllerUTIL
{

	


	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean getRosterCategoryList(HttpServletRequest _request,EmpRosterGenerationFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterCategoryList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
			
			
			
			EmpRosterGenerationUTLFunctions.getYearList(_request,_fb);
			EmpRosterGenerationUTLFunctions.getMonthsList(_request,_fb);
			
			
			//currentYear=WebUTIL.getSession(_request).getAttribute(arg0)
			
			dutyRosterCategoryList=EmpRosterGenerationDATA.getRosterCategoryList(_userVO);			
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
	
	


	
	/**
	 * Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and
	 * Employee details from PIST_EMP_PERSONNEL_DTL and  gblt_designation_mst
	 * 
	 * @param _request
	 * @param _fb
	 * @return
	 */
	
	public static boolean getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(HttpServletRequest _request,EmpRosterGenerationFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyRosterList=new ArrayList();
		String currentYear="";
		
		try
		{
			UserVO _userVO = getUserVO(_request);
		
			dutyRosterList=EmpRosterGenerationDATA.getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(_fb.getRosterCategory().split("@")[0],_userVO);			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY, dutyRosterList);
			
			
			if(dutyRosterList.size()==1)
			{
				Entry obj=(Entry)dutyRosterList.get(0);
				String rostId=obj.getValue();
				_fb.setRosterId(rostId);
				EmpRosterGenerationUTL.getDutyAreaWithCapacityAndDesignationBasedOnRosterType(_request, _fb);
			}
			
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.LIST_OF_ROSTERS_ON_THE_BASIS_OF_ROSTER_CATEGORY, dutyRosterList);
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
		 * Getting the Duty Area on the Basis of Duty Area Types 
		 * from the Package PKG_DUTY_ROSTER using the Procedure Proc_get_duty_area
		 * 	 
		 * @param _request
		 * @param _fb
		 * @return
		 */
	
	
		public static boolean getDutyAreaWithCapacityAndDesignationBasedOnRosterType(HttpServletRequest _request,EmpRosterGenerationFB _fb)
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
			
				
				EssentialMap=EmpRosterGenerationDATA.getDutyAreaWithCapacityAndDesignationBasedOnRosterType( _rosterId,_userVO);
				
			
				WebUTIL.setMapInSession(EssentialMap,_request);
				
				dutyAreaList=(ArrayList)EssentialMap.get(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA);
				desigList=(ArrayList)EssentialMap.get(DutyRosterConfig.LIST_OF_DESIGNATION_ON_THE_BASIS_OF_ROSTER_TYPE);
				
				if(dutyAreaList.size()==1)
				{
					Entry obj=(Entry)dutyAreaList.get(0);
						String areaId=obj.getValue();
					_fb.setAreaCode(areaId);
				
				}
				/*if(desigList.size()==1)
				{
					Entry obj=(Entry)desigList.get(0);
					String desigId=obj.getValue();
					_fb.setDesignationId(desigId);
				}*/
				
				_fb.setDesignationId("ALL");
				
				if(dutyAreaList.size()==1 )//&& desigList.size()==1)
					{
					EmpRosterGenerationUTL.getEmployeesCalendarBasedOnDutyAreaAndDesignation(_request, _fb);
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
		



	


			
			/**Getting the Employees Based on Duty  Area type ,Area and Designation
			 * 
			 * @param _request
			 * @param _fb
			 * @return
			 */
			public static boolean getEmployeesCalendarBasedOnDutyAreaAndDesignation(HttpServletRequest _request,EmpRosterGenerationFB _fb)
			{
				Status objStatus = new Status();
				
				
				Map EssentialMap=new HashMap();
				Map empDutyAreaMap=new HashMap();
			//	List empRosterList=new ArrayList();
				RosterDtlVO[] _rosterDtlVO=null;
				
				try
				{
					
					//setting the by default designation to be ALL
					_fb.setDesignationId("ALL");
					
					UserVO _userVO = getUserVO(_request);
					
		 
					String concateValues=_fb.getRosterId();
					String splitValues[]=concateValues.split("@");
					
					String _rosterId=splitValues[0];
					String _desigId=_fb.getDesignationId();
					String _areaCode=_fb.getAreaCode().split("@")[0];
					String _areaTypeCode=_fb.getAreaCode().split("@")[5];//splitValues[1];
					String _month=_fb.getMonth();
					String _year=_fb.getYear();
					String _areaId=_fb.getAreaCode().split("@")[5];
					
					System.out.println("manisha_areaTypeCode"+_fb.getAreaCode().split("@")[1]);
					
					//setting the duty Type of the roster [24x7,Offical,Holiday]
					_fb.setDutyType(splitValues[2]);
					
					
//Getting the employees whose Roster has been prepared on the basis of  year,
//month,rosterId,areaTypeCode and areaCode		
					
					
					EssentialMap=EmpRosterGenerationDATA.getEmployeesWithRoster(_desigId,_year, _month,_areaTypeCode, _areaCode, _rosterId, _areaId,  _userVO);
					
					
					//fetching the roster dtl vo's of old
					_rosterDtlVO=(RosterDtlVO[])EssentialMap.get(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);	
						
					//in case of modification setting the generated id
					if(_rosterDtlVO!=null)
						_fb.setGeneratedRosterId(_rosterDtlVO[0].getGeneratedRosterId());
					else//in case of NEW entry
						_fb.setGeneratedRosterId("");
					
					
					
		//setting all the essential things present in map to session
					WebUTIL.setMapInSession(EssentialMap, _request);
					
	
				EmpRosterGenerationUTLFunctions.getEmpDutyRosterCalendar(_request,_fb);
						
					
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
public static boolean saveEmpDutyRoster(EmpRosterGenerationFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		RosterWiseReliversDtlVO[] _reliverRosterVO=null;
		try
		{
			UserVO userVO = getUserVO(_request);
			String[] arrayOfEmployees=null;
			
if(_fb.getConcatedValueToBeSaved().contains("#"))			
			arrayOfEmployees=_fb.getConcatedValueToBeSaved().split("#");
			RosterDtlVO[] _oldRosterDtlVO=null;
			
			RosterDtlVO[] _rosterDtlVO=null;
			
if(arrayOfEmployees!=null && arrayOfEmployees.length!=0)
			_rosterDtlVO=EmpRosterGenerationUTLFunctions.getRosterDtlVOsToBeSaved(arrayOfEmployees,_request,_fb);		
			
			_oldRosterDtlVO=(RosterDtlVO[])WebUTIL.getSession(_request).getAttribute(DutyRosterConfig.VO_OF_EMP_WISE_ROSTER);
			
			
			
			
		String _year=_fb.getYear();
		String _month=_fb.getMonth();
			
		
			
		
		String[] arrayOfRosterDetails=_fb.getRosterId().replace("^", "@").split("@");     
		
		String _rosterId=arrayOfRosterDetails[0];
		String _areaTypeCode=arrayOfRosterDetails[1];
		String _areaCode=_fb.getAreaCode().split("@")[0];
		
		if(!_fb.getReliverEmpList().equals(""))
			_reliverRosterVO=EmpRosterGenerationUTLFunctions.getRosterDtlVOsToBeRelived(_request, _fb,_rosterId);

		////Now setting the duty type=1
if(_rosterDtlVO!=null){
		for(int i=0; i < _rosterDtlVO.length;i++)	
			_rosterDtlVO[i].setDutyType(DutyRosterConfig.DUTY_TYPE_NORMAL);
			}
		
			
//if we are not getting any earlier saved VO then we will only save record
//1st time insert ,only save will be performed			
			
if(_oldRosterDtlVO==null && _rosterDtlVO!=null)			
			EmpRosterGenerationDATA.saveEmpDutyRoster(_rosterDtlVO, userVO);
			else//else we will save and modify the old  records
				{
				//	String empList=EmpRosterGenerationUTLFunctions.getEmpListToBeUpdated(_request, _fb);
					
					String empListToBeUpdated=_fb.getEmpListToBeUpdated();
					String daysListToBeUpdated=EmpRosterGenerationUTLFunctions.getDaysListToBeUpdated(_request, _fb);

if(_rosterDtlVO!=null){					
					for(int i=0; i < _rosterDtlVO.length;i++)	
						_rosterDtlVO[i].setGeneratedRosterId(_fb.getGeneratedRosterId());
					  }

					
				EmpRosterGenerationDATA.saveAndModifyEmpDutyRoster(_rosterDtlVO, userVO,empListToBeUpdated,daysListToBeUpdated,_year,_month,_rosterId,_areaTypeCode,_areaCode);
				}
				EmpRosterGenerationDATA.saveEmpReliverDtl(userVO, _reliverRosterVO);
				
			objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
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




public static void setTotalEmpRoster(EmpRosterGenerationFB _fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	boolean hasFlag = true;

	try
	{
		UserVO userVO = getUserVO(_request);
		String[] arrayOfEmployees=null;
		
		Map empCatgAreaMap=(Map)_request.getSession().getAttribute(DutyRosterConfig.TOTAL_MAP_OF_EMPWISE_MONTHLY_ROSTERS);
			
			Map catgAreaMap=(Map)empCatgAreaMap.get(_request.getParameter("empId"));

			_request.setAttribute(DutyRosterConfig.MAP_OF_EMPWISE_MONTHLY_ROSTERS, catgAreaMap);
			
			
Map empAreaMap=(Map)_request.getSession().getAttribute(DutyRosterConfig.TOTAL_MAP_OF_EMP_AREA_MAPPING);
			
			String areasMapped="";
			
				areasMapped+=(String)empAreaMap.get(_request.getParameter("empId"));

			_request.setAttribute(DutyRosterConfig.EMP_MAPPED_AREA, areasMapped);
			
			

////////////////////////Setting the Emp Reliver List/////////////////////			

String reliverRange="";		
			
Map reliverEmpMap=EmpRosterGenerationUTLFunctions.getMapOfEmpToBeRelived(_request, _fb);
List empReliverList=new ArrayList();

if(reliverEmpMap.containsKey(_request.getParameter("empId")));
		empReliverList=(ArrayList)reliverEmpMap.get(_request.getParameter("empId"));
	
		
		
if(empReliverList!=null && empReliverList.size() >0)
	{
for(int i=0; i< empReliverList.size();i++)

	if(i < empReliverList.size()-1)
		reliverRange+=empReliverList.get(i)+",";
	else
		reliverRange+=empReliverList.get(i);
	
	}
	
_request.setAttribute(DutyRosterConfig.EMP_RELIVER_LIST, reliverRange);




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

}


/**For Generating the Emp Roster
 * @author ankur
 *
 */


public static boolean generateEmpDutyRoster(EmpRosterGenerationFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
	
		try
		{
			UserVO userVO = getUserVO(_request);
					
		RosterGenerationDtlVO _rosterGenerationDtlVO=new RosterGenerationDtlVO();
			
		RosterWiseReliversDtlVO[] _reliverRosterVO=null;
				
		
		String[] arrayOfRosterDetails=_fb.getRosterId().replace("^", "@").split("@");     
		
		String _rosterId=arrayOfRosterDetails[0];
		String _areaTypeCode=arrayOfRosterDetails[1];
		String _areaCode=_fb.getAreaCode().split("@")[0];
		
		
		_rosterGenerationDtlVO.setRosterId(_rosterId);
		_rosterGenerationDtlVO.setAreaCode(_areaCode);
		_rosterGenerationDtlVO.setAreaTypeCode(_areaTypeCode);
		_rosterGenerationDtlVO.setStartDate(_fb.getStartDate());
		_rosterGenerationDtlVO.setEndDate(_fb.getEndDate());
		_rosterGenerationDtlVO.setRosterStatus(DutyRosterConfig.ROSTER_STATUS_IS_GENERATED);
		_rosterGenerationDtlVO.setGeneratedRosterId(_fb.getGeneratedRosterId());
		
		
	if(!_fb.getReliverEmpList().equals(""))
		_reliverRosterVO=EmpRosterGenerationUTLFunctions.getRosterDtlVOsToBeRelived(_request, _fb,_rosterId);
		
		EmpRosterGenerationDATA.generateEmpDutyRoster(_rosterGenerationDtlVO, userVO,_reliverRosterVO);
			
				
				
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



/**For dropping the whole Emp Roster
 * @author ankur
 *
 */


public static boolean dropEmpDutyRoster(EmpRosterGenerationFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
	
		try
		{
			UserVO userVO = getUserVO(_request);
			String[] arrayOfEmployees=null;
			
			
		RosterGenerationDtlVO _rosterGenerationDtlVO=new RosterGenerationDtlVO();
			

				
		
		String[] arrayOfRosterDetails=_fb.getRosterId().replace("^", "@").split("@");     
		
		String _rosterId=arrayOfRosterDetails[0];
		String _areaTypeCode=arrayOfRosterDetails[1];
		String _areaCode=_fb.getAreaCode().split("@")[0];
		String _year=_fb.getYear();
		String _month=_fb.getMonth();
		
		
		
	
		String empListToBeUpdated=_fb.getEmpListToBeUpdated();
		String daysListToBeUpdated=EmpRosterGenerationUTLFunctions.getDaysListToBeUpdated(_request, _fb);
		
		String generatedRosterId=_fb.getGeneratedRosterId();
		
		EmpRosterGenerationDATA.dropEmpDutyRoster(_year,_month,_rosterId,_areaTypeCode,_areaCode,userVO,empListToBeUpdated,daysListToBeUpdated,generatedRosterId);
				
				
			objStatus.add(Status.INPROCESS, "Roster Dropped Successfully", "");
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
