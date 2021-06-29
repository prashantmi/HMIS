package dutyroster.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.RosterShiftMstVO;
import hisglobal.vo.RosterTypeMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.controller.data.RosterShiftMstDATA;
import dutyroster.masters.controller.fb.RosterShiftMstFB;

public class RosterShiftMstUTL extends ControllerUTIL
{
	
	public static void getEssentials(RosterShiftMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List rosterlist=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			rosterlist=RosterShiftMstDATA.getRosterShiftEssentials(userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.ESSENTIAL_ROSTER_TYPE_LIST, rosterlist);
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}
	
	public static void getShiftsBasedOnRoster(RosterShiftMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		
		List shiftList=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			String[] arrayOfRosterDtl=_fb.getRosterTypeId().split("@");
			
			String shiftType="";
			
			//in case the duty type is 24x7,then day shift is not availabe to it 
			
			if(arrayOfRosterDtl[1].equals(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN))
				shiftType="4";
			else
				shiftType="-1";
				
				
			shiftList=RosterShiftMstDATA.getShiftsBasedOnRoster(arrayOfRosterDtl[0],shiftType,userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.ESSENTIAL_SHIFT_LIST, shiftList);
			objStatus.add(Status.NEW);
			_fb.setHmode("ADD");
		}
		catch (HisRecordNotFoundException e)
		{
			_fb.setHmode("ADD");
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.ESSENTIAL_SHIFT_LIST, new ArrayList());
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}
	
	public static void saveRosterShift(RosterShiftMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		String shiftIds[]=_fb.getSelectedShiftId();
		String shiftId[]=new String[shiftIds.length];
		for(int i=0;i<shiftIds.length;i++)
			shiftId[i]=shiftIds[i].split("@")[0];
		
		RosterShiftMstVO rosterShiftVO[]=new RosterShiftMstVO[shiftIds.length] ;
		
		for(int i=0;i<shiftIds.length;i++){
			rosterShiftVO[i]=new RosterShiftMstVO();
		}	
		try
		{
			UserVO userVO = getUserVO(_request);
			
			String[] rosterTypeArray=_fb.getRosterTypeId().split("@");
			
			for(int i=0;i<shiftId.length;i++){
				rosterShiftVO[i].setShiftId(shiftId[i]);
				rosterShiftVO[i].setRosterTypeId(rosterTypeArray[0]);
			}
			_fb.setHmode("ADD");
			RosterShiftMstDATA.saveRosterShift(rosterShiftVO,userVO);
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}
	
	public static void getRosterShift(RosterShiftMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		
		String primaryKey=_fb.getChk();
		
		primaryKey=primaryKey.replace("^","@");
		
		String rosterTypeId=primaryKey.split("@")[0];
		String slNo=primaryKey.split("@")[1];
		String shiftId=primaryKey.split("@")[2];
		
						
		/*String shiftType="";
		
		if(primaryKey.split("@")[4].equals(DutyRosterConfig.DUTY_TYPE_TWENTY_FOR_SEVEN))
			shiftType="4";
		else
			shiftType="-1";*/
		
		
		
		RosterShiftMstVO rosterShiftVO=new RosterShiftMstVO();
		Map essentialMap=new HashMap();
		List  rosterShift=new ArrayList();
		String fetchedList="";
		try
		{
			UserVO userVO = getUserVO(_request);
			rosterShiftVO.setRosterTypeId(rosterTypeId);
			rosterShiftVO.setShiftId(shiftId); 
			rosterShiftVO.setSerialNo(slNo);
			rosterShiftVO.setIsActive(_fb.getIsActive());
		//	rosterShiftVO.setShiftType(shiftType);
			
			essentialMap=RosterShiftMstDATA.getRosterShift(rosterShiftVO,userVO);
			rosterShift=(List)essentialMap.get(DutyRosterConfig.ROSTER_SHIFT_DETAIL);
			for(int i=0;i<rosterShift.size();i++){
				Entry entry=(Entry)rosterShift.get(i);
				fetchedList+=entry.getValue()+"%";
			}
			
			_fb.setRosterTypeName((String)essentialMap.get(DutyRosterConfig.ROSTER_TYPE_NAME));
			_fb.setRosterType(rosterTypeId);
			_fb.setFetchedList(fetchedList);
			WebUTIL.setMapInSession(essentialMap, _request);
				
			objStatus.add(Status.NEW);
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}

	public static void modifyRosterShift(RosterShiftMstFB _fb,HttpServletRequest _request) 
	{
			Status objStatus = new Status();
			String primaryKey=_fb.getChk();
			primaryKey=primaryKey.replace("^","@");
			String rosterTypeId=primaryKey.split("@")[0];
			String slNo=primaryKey.split("@")[1];
			
			String shiftIds[]=_fb.getSelectedShiftId();
			String shiftId[]=new String[shiftIds.length];
			for(int i=0;i<shiftIds.length;i++)
				shiftId[i]=shiftIds[i].split("@")[0];
			RosterShiftMstVO insertRosterShiftVO[]=null;
			RosterShiftMstVO updateRosterShiftVO[]=null;
			int len=0;
			String fetchedList[]=_fb.getFetchedList().split("%");
			String selectedList[]=_fb.getSelectedShiftId();
			boolean flag=true;
			
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
			insertRosterShiftVO=new RosterShiftMstVO[len];
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
					insertRosterShiftVO[len]=new RosterShiftMstVO();
					insertRosterShiftVO[len].setRosterTypeId(_fb.getRosterType());
					insertRosterShiftVO[len].setSelectedShiftId(selectedList[i].split("@")[0]);
					insertRosterShiftVO[len].setIsActive(_fb.getIsActive());
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
			updateRosterShiftVO=new RosterShiftMstVO[len];
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
					updateRosterShiftVO[len]=new RosterShiftMstVO();
					updateRosterShiftVO[len].setRosterTypeId(_fb.getRosterType());
					updateRosterShiftVO[len].setSelectedShiftId(fetchedList[i].split("@")[0]);
					len++;
				}
			}
			len=0;
			boolean hasFlag=true;
			for(int i=0;i<fetchedList.length;i++){
				for(int j=0;j<selectedList.length;j++){
					if(!fetchedList[i].equals(selectedList[j])){
						hasFlag=false;
						break;
					}
					break;
				}
			}
			if(hasFlag){
				if(!_fb.getIsActive().equals(fetchedList[0].split("@")[4])){
					for(int i=0;i<fetchedList.length;i++){
						len++;
					}
					updateRosterShiftVO=new RosterShiftMstVO[len];
					insertRosterShiftVO=new RosterShiftMstVO[len];
					
					for(int i=0;i<fetchedList.length;i++){
						updateRosterShiftVO[i]=new RosterShiftMstVO();
						updateRosterShiftVO[i].setRosterTypeId(_fb.getRosterType());
						updateRosterShiftVO[i].setSelectedShiftId(fetchedList[i].split("@")[0]);
					}
					for(int i=0;i<fetchedList.length;i++){
						insertRosterShiftVO[i]=new RosterShiftMstVO();
						insertRosterShiftVO[i].setRosterTypeId(_fb.getRosterType());
						insertRosterShiftVO[i].setSelectedShiftId(selectedList[i].split("@")[0]);
						insertRosterShiftVO[i].setIsActive(_fb.getIsActive());
					}
				}
			}
			try
			{
				UserVO userVO = getUserVO(_request);
				_fb.setHmode("LIST");
				RosterShiftMstDATA.modifyRosterShift(insertRosterShiftVO,updateRosterShiftVO,userVO);
				objStatus.add(Status.DONE);
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
				_request.setAttribute(Config.STATUS_OBJECT, objStatus);
				System.out.println("   -----> objStatus in finally  : " + objStatus);
				System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
			}
			
		}
		
}
		

