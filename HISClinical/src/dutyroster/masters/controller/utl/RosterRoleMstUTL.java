package dutyroster.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.RosterRoleMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.controller.data.RosterRoleMstDATA;
import dutyroster.masters.controller.fb.RosterRoleMstFB;

public class RosterRoleMstUTL extends ControllerUTIL
{
	
	public static void getDutyRoleNotIn(RosterRoleMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List dutyRoleNotInlist=new ArrayList();
		List dutyRoleAssigned=new ArrayList();
		String rosterTypeId=_fb.getRosterTypeId();
		String [] array;
		String fetchedList="";
		try
		{
			UserVO userVO = getUserVO(_request);
			dutyRoleAssigned=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.DUTY_ROLE_IN_ROSTER_ROLE);
			int len=0;
			for(int i=0;i<dutyRoleAssigned.size();i++){
				len++;
			}
			array=new String[len];
			for(int i=0;i<dutyRoleAssigned.size();i++){
				Entry entry=new Entry();
				entry=(Entry)dutyRoleAssigned.get(i);
				array[i]=entry.getValue();
			}
			
			for(int i=0;i<array.length;i++){
				fetchedList=fetchedList+array[i]+ "%";
			}
			_fb.setFetchedList(fetchedList);
			
			dutyRoleNotInlist=RosterRoleMstDATA.getDutyRoleNotIn(rosterTypeId, userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROLE_NOT_IN_ROSTER_ROLE, dutyRoleNotInlist);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Either No Duty Role Record Exist or All are Mapped");
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROLE_NOT_IN_ROSTER_ROLE, dutyRoleNotInlist);
						
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
		
	}
	
	public static void getDutyRole(RosterRoleMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List dutyRolelist=new ArrayList();
		String rosterTypeId=_fb.getRosterTypeId();
		try
		{
			UserVO userVO = getUserVO(_request);
			dutyRolelist=RosterRoleMstDATA.getDutyRole(rosterTypeId, userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROLE_IN_ROSTER_ROLE, dutyRolelist);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROLE_IN_ROSTER_ROLE, dutyRolelist);
			
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
		
	}
	
	public static void getRosterType(RosterRoleMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		String rosterTypeId=_fb.getRosterTypeId();
		try
		{
			UserVO userVO = getUserVO(_request);
			List rosterTypelist=(ArrayList)RosterRoleMstDATA.getRosterType(userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_LIST, rosterTypelist);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_LIST, new ArrayList());
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
			
		}
		
	}
	
	
	public static void saveRosterShift(RosterRoleMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RosterRoleMstVO insertRosterRoleVO[]=null;
		RosterRoleMstVO updateRosterRoleVO[]=null;
		int len=0;
		String fetchedList[]=_fb.getFetchedList().split("%");
		String selectedList[]=_fb.getSelectedDutyRole();
		boolean flag=true;
		if(fetchedList.length==0){
			for(int i=0;i<selectedList.length;i++){
				len++;
			}
			insertRosterRoleVO=new RosterRoleMstVO[len];
			for(int i=0;i<selectedList.length;i++){
				insertRosterRoleVO[len]=new RosterRoleMstVO();
				insertRosterRoleVO[len].setRosterTypeId(_fb.getRosterTypeId());
				insertRosterRoleVO[len].setDutyRole(selectedList[i]);
				len++;
			}	
			len=0;
			for(int i=0;i<fetchedList.length;i++){
				len++;
			}
			updateRosterRoleVO=new RosterRoleMstVO[len];
			for(int i=0;i<fetchedList.length;i++){
				updateRosterRoleVO[len]=new RosterRoleMstVO();
				updateRosterRoleVO[len].setRosterTypeId(_fb.getRosterTypeId());
				updateRosterRoleVO[len].setDutyRole(fetchedList[i]);
				len++;
			}	
		}
		else{
			len=0;
		for(int i=0;i<selectedList.length;i++){
			for(int j=0;j<fetchedList.length;j++){
				if(selectedList[i].equals(fetchedList[j])){
					flag=true;
					break;
				}
				else{
					flag=false;
				}
			}if(!flag){
				len++;
			}
		}	
		insertRosterRoleVO=new RosterRoleMstVO[len];
		len=0;
		for(int i=0;i<selectedList.length;i++){
			for(int j=0;j<fetchedList.length;j++){
				if(selectedList[i].equals(fetchedList[j])){
					flag=true;
					break;
				}
				else{
					flag=false;
				}
			}if(!flag){
				insertRosterRoleVO[len]=new RosterRoleMstVO();
				insertRosterRoleVO[len].setRosterTypeId(_fb.getRosterTypeId());
				insertRosterRoleVO[len].setDutyRole(selectedList[i]);
				len++;
			}
		}	
		len=0;
		for(int i=0;i<fetchedList.length;i++){
			for(int j=0;j<selectedList.length;j++){
				if(fetchedList[i].equals(selectedList[j])){
					flag=true;
					break;
				}
				else{
					flag=false;
				}
			}if(!flag){
				len++;
			}
		}	
		updateRosterRoleVO=new RosterRoleMstVO[len];
		len=0;
		for(int i=0;i<fetchedList.length;i++){
			for(int j=0;j<selectedList.length;j++){
				if(fetchedList[i].equals(selectedList[j])){
					flag=true;
					break;
				}
				else{
					flag=false;
				}
			}if(!flag){
				updateRosterRoleVO[len]=new RosterRoleMstVO();
				updateRosterRoleVO[len].setRosterTypeId(_fb.getRosterTypeId());
				updateRosterRoleVO[len].setDutyRole(fetchedList[i]);
				len++;
			}
		}
		}
		try
		{
			UserVO userVO = getUserVO(_request);
			String rosterType=_fb.getRosterTypeId();
			RosterRoleMstDATA.saveRosterRole(insertRosterRoleVO,updateRosterRoleVO,userVO);
			objStatus.add(Status.DONE,"","Record Saved Successfully");
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
		
	}
	
			
}
		

