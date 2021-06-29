
package dutyroster.masters.controller.utl;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.controller.data.DutyAreaEmployeeMstDATA; 
import dutyroster.masters.controller.fb.DutyAreaEmployeeMstFB; 

public class DutyAreaEmployeeMstUTL extends ControllerUTIL
{

	
// Getting the Employee Area Essentials from the table HDRT_DUTY_ROLE_MST and 
//	Employee details from PIST_EMP_REGISTRATION_DTL and  GBLT_DESIGNATION_MST

	public static boolean getEmployeeAreaEssentials(HttpServletRequest _request,DutyAreaEmployeeMstFB _fb)
	{
		Status objStatus = new Status();
		
		List dutyAreaType=new ArrayList();
		
		try
		{
			UserVO _userVO = getUserVO(_request);
			setSysdate(_request);
			
			
			
		
			
			dutyAreaType=DutyAreaEmployeeMstDATA.getEmployeeAreaEssentials(_userVO);
			
			WebUTIL.setAttributeInSession(_request,DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA_TYPE, dutyAreaType);
			
						
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
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
		
		public static boolean getDutyAreaBasedOnDutyAreaType(HttpServletRequest _request,DutyAreaEmployeeMstFB _fb)
		{
			Status objStatus = new Status();
			
			List dutyAreaList=new ArrayList();
			
			try
			{
				UserVO _userVO = getUserVO(_request);
				setSysdate(_request);
	 
						
				dutyAreaList=DutyAreaEmployeeMstDATA.getDutyAreaBasedOnDutyAreaType(_fb.getAreaTypeCode(),_userVO);
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, dutyAreaList);
				
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_ROLE_TYPE,new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_DESIGNATION, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_UNSELECTED, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_SELECTED, new ArrayList());
				
				
				
				objStatus.add(Status.NEW);
			}
			catch (HisRecordNotFoundException e)
			{
				WebUTIL.getSession(_request).setAttribute(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_ROLE_TYPE,new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_DESIGNATION, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_UNSELECTED, new ArrayList());
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_SELECTED, new ArrayList());
				
				System.out.println("Inside HisRecordNotFoundException");
				objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
			}
			catch (HisDataAccessException e)
			{
				
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_AREA, new ArrayList());
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
		


// Getting the Roles and Designations from the Tables HDRT_DUTY_ROLE_MST and GBLT_designation_mst

			
			public static boolean getRoleAndDesignation(HttpServletRequest _request,DutyAreaEmployeeMstFB _fb)
			{
				Status objStatus = new Status();
				
				Map EssentialMap=new HashMap();
				
				try
				{
					UserVO _userVO = getUserVO(_request);
					setSysdate(_request);
		 
							
					EssentialMap=DutyAreaEmployeeMstDATA.getRoleAndDesignation(_userVO);
					
					WebUTIL.setMapInSession(EssentialMap, _request);
					
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_UNSELECTED, new ArrayList());
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_SELECTED, new ArrayList());
					_fb.setDutyRoleId("-1");
					_fb.setEmpDesg("-1");
					
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
			
	
// Getting the List of Left Employees Based on Designations 

			
			public static boolean getLeftEmployeesBasedOnDesignationAndArea(HttpServletRequest _request,DutyAreaEmployeeMstFB _fb)
			{
				Status objStatus = new Status();
				
				
				List empLeftList=new ArrayList();
				try
				{
					UserVO _userVO = getUserVO(_request);
					setSysdate(_request);
		 
					DutyRosterAreaEmployeeVO _dutyAreaEmpVO= new DutyRosterAreaEmployeeVO();
					
					_dutyAreaEmpVO.setAreaTypeCode(_fb.getAreaTypeCode());
					_dutyAreaEmpVO.setAreaCode(_fb.getAreaCode());
					
					
					
							
					empLeftList=DutyAreaEmployeeMstDATA.getLeftEmployeesBasedOnDesignationAndArea(_fb.getEmpDesg(),_dutyAreaEmpVO,_userVO);
					
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_UNSELECTED, empLeftList);
					
				
					
					objStatus.add(Status.NEW);
				}
				catch (HisRecordNotFoundException e)
				{
					if(empLeftList.size()==0)	
				WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_UNSELECTED, new ArrayList());
		System.out.println("Inside HisRecordNotFoundException");
					
					System.out.println("Inside HisRecordNotFoundException2");
					objStatus.add(Status.NEW, "", "No Employees Found ");
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

//Getting the List of Left Employees Based on Designations 			
	public static boolean getRightEmployeesBasedOnDesignationAndArea(HttpServletRequest _request,DutyAreaEmployeeMstFB _fb)
			{
				Status objStatus = new Status();
				
				List empRightList=new ArrayList();
				
				try
				{
					UserVO _userVO = getUserVO(_request);
					setSysdate(_request);
		 
					DutyRosterAreaEmployeeVO _dutyAreaEmpVO= new DutyRosterAreaEmployeeVO();
					
					_dutyAreaEmpVO.setAreaTypeCode(_fb.getAreaTypeCode());
					_dutyAreaEmpVO.setAreaCode(_fb.getAreaCode());
					
					
					
							
					empRightList=DutyAreaEmployeeMstDATA.getRightEmployeesBasedOnDesignationAndArea(_fb.getEmpDesg(),_dutyAreaEmpVO,_userVO);
					
					WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_SELECTED, empRightList);
					
				
					
					objStatus.add(Status.NEW);
				}
				catch (HisRecordNotFoundException e)
				{
		System.out.println("Inside HisRecordNotFoundException");
					
		if(empRightList.size()==0)	
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_EMP_DETAILS_SELECTED, new ArrayList());
					System.out.println("Inside HisRecordNotFoundException2");
					objStatus.add(Status.NEW, "", "No Employees Found ");
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
	public static boolean saveAndModifyDutyAreaEmpInfo(DutyAreaEmployeeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
	
		try
		{
			UserVO userVO = getUserVO(_request);
			
			
		
			StringTokenizer st1 = new StringTokenizer(_fb.getOldEmpSelectedLeft(),"^");
			StringTokenizer st2 = new StringTokenizer(_fb.getOldEmpSelectedRight(),"^");
			StringTokenizer st3 = new StringTokenizer(_fb.getNewEmpSelectedLeft(),"^");
			StringTokenizer st4 = new StringTokenizer(_fb.getNewEmpSelectedRight(),"^");

			System.out.println("st1---->"+_fb.getOldEmpSelectedLeft());
			System.out.println("st2---->"+ _fb.getOldEmpSelectedRight());
			System.out.println("st3---->"+ _fb.getNewEmpSelectedLeft());
			System.out.println("st4--->"+_fb.getNewEmpSelectedRight());

			List lstOne = new ArrayList();////contains old unselected Employees
			List lstTwo = new ArrayList();//contains old Selected Employees
			List lstThree = new ArrayList();////contains new unselected Employees
			List lstFour = new ArrayList();//contains new selected Employees

			
			while(st1.hasMoreElements())
			{
				lstOne.add(st1.nextToken().toString());		

			}


			while(st2.hasMoreElements())
			{
				lstTwo.add(st2.nextToken().toString());		

			}

			while(st3.hasMoreElements())
			{
				lstThree.add(st3.nextToken().toString());		

			}

			while(st4.hasMoreElements())
			{
				lstFour.add(st4.nextToken().toString());		

			}

			


			List recToInsert = new ArrayList();
			List recToDelete = new ArrayList();
			

		

			//FINDING  THE Employees TO BE DELETED
if(lstThree.size()!=0)
{	
			for(int i=0;i<lstThree.size();i++)
			{
				
				boolean found = false;
if(lstTwo.size()!=0)
		   {
				for(int t=0;t<lstTwo.size();t++)			
				{
					if(lstThree.get(i).toString().equals(lstTwo.get(t).toString()))
					{
						found = true;
						break;
					}
					
				}
			}
				
				if(found){
					recToDelete.add(lstThree.get(i));	
					System.out.println("value to delete---->"+ lstThree.get(i));         
						}	
		} 
}

if(lstOne.size()!=0)
{
    //FINDING THE Employees to be inserted 
			for(int i=0;i<lstOne.size();i++)
			{
				
				boolean found = false;
 if(lstFour.size()!=0)
	   {
				
				for(int t=0;t<lstFour.size();t++)			
				{
					if(lstOne.get(i).toString().equals(lstFour.get(t).toString()))
					{
						found = true;
						break;
					}
					
				}
	   }		
				if(found)
				 { 
				 recToInsert.add(lstOne.get(i));	
				 System.out.println("value to insert---->"+ lstOne.get(i));
				 }
			}
}			
		

			
			//Populating the array of VO's to be Inserted 
			DutyRosterAreaEmployeeVO[] _addDutyAreaEmpVO=new DutyRosterAreaEmployeeVO[recToInsert.size()];
			
			for( 	int i=0; i < recToInsert.size() ; i++)
			{	
				_addDutyAreaEmpVO[i]=new DutyRosterAreaEmployeeVO();
				_addDutyAreaEmpVO[i].setEmpId((String)recToInsert.get(i));
				_addDutyAreaEmpVO[i].setAreaTypeCode(_fb.getAreaTypeCode());
				_addDutyAreaEmpVO[i].setAreaCode(_fb.getAreaCode());
				_addDutyAreaEmpVO[i].setDutyRoleId(_fb.getDutyRoleId());			
			}
		

			//Populating the array of VO's to be Deleted 
			DutyRosterAreaEmployeeVO[] _deleteDutyAreaEmpVO=new DutyRosterAreaEmployeeVO[recToDelete.size()];
			
			for( 	int i=0; i < recToDelete.size() ; i++)
			{	
				_deleteDutyAreaEmpVO[i]=new DutyRosterAreaEmployeeVO();
				_deleteDutyAreaEmpVO[i].setEmpId((String)recToDelete.get(i));
				_deleteDutyAreaEmpVO[i].setAreaTypeCode(_fb.getAreaTypeCode());
				_deleteDutyAreaEmpVO[i].setAreaCode(_fb.getAreaCode());
				_deleteDutyAreaEmpVO[i].setDutyRoleId(_fb.getDutyRoleId());			
			}
			
			
			
			DutyAreaEmployeeMstDATA.saveAndModifyDutyAreaEmpInfo(_addDutyAreaEmpVO,_deleteDutyAreaEmpVO, userVO);
			
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

	
}
