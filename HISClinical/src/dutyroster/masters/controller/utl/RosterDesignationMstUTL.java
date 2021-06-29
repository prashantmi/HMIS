package dutyroster.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.RosterDesignationMstVO;
import hisglobal.vo.UserVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import dutyroster.DutyRosterConfig;
import dutyroster.masters.controller.data.RosterDesignationMstDATA;
import dutyroster.masters.controller.fb.RosterDesignationMstFB;

public class RosterDesignationMstUTL extends ControllerUTIL
{
	
	public static void getNotAssignedDesignation(RosterDesignationMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List designationNotAssigned=new ArrayList();
		List designationAssigned=new ArrayList();
		String rosterTypeId=_fb.getRosterTypeId();
		String [] array;
		String fetchedList="";
		try
		{
			UserVO userVO = getUserVO(_request);
			designationAssigned=(ArrayList)_request.getSession().getAttribute(DutyRosterConfig.ROSTER_DESIG_ASSIGNED_DESIG);
			int len=0;
			for(int i=0;i<designationAssigned.size();i++){
				len++;
			}
			array=new String[len];
			for(int i=0;i<designationAssigned.size();i++){
				Entry entry=new Entry();
				entry=(Entry)designationAssigned.get(i);
				array[i]=entry.getValue();
			}
			
			for(int i=0;i<array.length;i++){
				fetchedList+=array[i]+"%";
			}
			_fb.setFetchedList(fetchedList);
			designationNotAssigned=RosterDesignationMstDATA.getNotAssignedDesignation(rosterTypeId, userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.ROSTER_DESIG_NOT_ASSIGNED_DESIG, designationNotAssigned);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Either No Designation Record Exist or All are Mapped");
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.ROSTER_DESIG_NOT_ASSIGNED_DESIG, designationNotAssigned);
						
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
	
	public static void getAssignedDesignation(RosterDesignationMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List dutyRolelist=new ArrayList();
		String rosterTypeId=_fb.getRosterTypeId();
		try
		{
			UserVO userVO = getUserVO(_request);
			dutyRolelist=RosterDesignationMstDATA.getAssignedDesignation(rosterTypeId, userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.ROSTER_DESIG_ASSIGNED_DESIG, dutyRolelist);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.NEW);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.ROSTER_DESIG_ASSIGNED_DESIG, dutyRolelist);
			
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
	
	public static void getRosterType(RosterDesignationMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		String rosterTypeId=_fb.getRosterTypeId();
		try
		{
			UserVO userVO = getUserVO(_request);
			List rosterTypelist=(ArrayList)RosterDesignationMstDATA.getRosterType(userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_LIST, rosterTypelist);
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
			
		}
		
	}
	
	
	public static void saveRosterDesignation(RosterDesignationMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RosterDesignationMstVO insertRosterDesignationVO[]=null;
		RosterDesignationMstVO updateRosterDesignationVO[]=null;
		int len=0;
		String fetchedList[]=_fb.getFetchedList().split("%");
		String selectedList[]=_fb.getSelectedDesignationId();
		boolean flag=true;
		if(fetchedList.length==0){
			for(int i=0;i<selectedList.length;i++){
				len++;
			}
			insertRosterDesignationVO=new RosterDesignationMstVO[len];
			for(int i=0;i<selectedList.length;i++){
				insertRosterDesignationVO[len]=new RosterDesignationMstVO();
				insertRosterDesignationVO[len].setRosterTypeId(_fb.getRosterTypeId());
				insertRosterDesignationVO[len].setDesignationId(selectedList[i]);
				len++;
			}	
			len=0;
			for(int i=0;i<fetchedList.length;i++){
				len++;
			}
			updateRosterDesignationVO=new RosterDesignationMstVO[len];
			for(int i=0;i<fetchedList.length;i++){
				updateRosterDesignationVO[len]=new RosterDesignationMstVO();
				updateRosterDesignationVO[len].setRosterTypeId(_fb.getRosterTypeId());
				updateRosterDesignationVO[len].setDesignationId(fetchedList[i]);
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
		insertRosterDesignationVO=new RosterDesignationMstVO[len];
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
				insertRosterDesignationVO[len]=new RosterDesignationMstVO();
				insertRosterDesignationVO[len].setRosterTypeId(_fb.getRosterTypeId());
				insertRosterDesignationVO[len].setDesignationId(selectedList[i]);
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
		updateRosterDesignationVO=new RosterDesignationMstVO[len];
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
				updateRosterDesignationVO[len]=new RosterDesignationMstVO();
				updateRosterDesignationVO[len].setRosterTypeId(_fb.getRosterTypeId());
				updateRosterDesignationVO[len].setDesignationId(fetchedList[i]);
				len++;
			}
		}
		}
		try
		{
			UserVO userVO = getUserVO(_request);
			String rosterType=_fb.getRosterTypeId();
			RosterDesignationMstDATA.saveRosterDesignation(insertRosterDesignationVO,updateRosterDesignationVO,userVO);
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
		

