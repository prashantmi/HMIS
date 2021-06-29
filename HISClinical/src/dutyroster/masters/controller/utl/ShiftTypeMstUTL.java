package dutyroster.masters.controller.utl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import hisglobal.vo.DutyRosterShiftMasterVO;
import hisglobal.vo.DutyRosterShiftTimingsMasterVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.controller.data.ShiftTypeMstDATA; 
import dutyroster.masters.controller.fb.ShiftTypeMstFB; 

public class ShiftTypeMstUTL extends ControllerUTIL
{
	
	// Getting the Shift Types from the System Generated Table HDRT_SHIFT_TYPE_MST
	
	public static boolean getShiftTypes(ShiftTypeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List shiftType=new ArrayList();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
 
					
			shiftType=ShiftTypeMstDATA.getShiftTypes(userVO);
			
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_SHIFT_TYPE, shiftType);
			
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

	
	// On Add Page saving Values into Database
	public static boolean saveShiftTypeInfo(ShiftTypeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			DutyRosterShiftMasterVO _shiftMstVO=new DutyRosterShiftMasterVO();
			DutyRosterShiftTimingsMasterVO[] _shiftTimingsMstVO=new DutyRosterShiftTimingsMasterVO[7];

			HelperMethods.populate(_shiftMstVO, _fb); // for dropping all values of formBean into vo//

			
			
			//fetching the shift ID Information from the Concatinated Values & Time Values also
			String concateValues=_fb.getShiftTypeCode().replace("^","@");
			String splitValues[]=concateValues.split("@");
			_shiftMstVO.setShiftTypeCode(splitValues[0]);
			
			
			//String[] shiftTypeArray=_fb.getShiftTypeCode().replace("^", "@").split("@");
			
			String[] shiftTypeArray=_fb.getShiftTypeCode().split("//^");
			
//In case the days are different 
//then timings wil be different			
if(_fb.getIsDayWiseShift().equals("1"))
{
	String [] startTimeHr=_fb.getStartTimeHrUser();
	String [] startTimeMin=_fb.getStartTimeMinUser();
	String [] endTimeHr=_fb.getEndTimeHrUser();
	String [] endTimeMin=_fb.getEndTimeMinUser();
	
	
	//setting the default shift type timings 
	//in DutyRosterShiftMasterVO
	
	_shiftMstVO.setShiftStartTime(shiftTypeArray[1]);
	_shiftMstVO.setShiftEndTime(shiftTypeArray[2]);
	
	
	
	//iterating through the DutyRosterShiftTimingsMasterVO vo's and setting the timings
	
	for(int i=0; i < _shiftTimingsMstVO.length ; i++)
	 {
		
		String weekDayCode=Integer.toString(i+1);
	
	_shiftTimingsMstVO[i]=new DutyRosterShiftTimingsMasterVO();
	_shiftTimingsMstVO[i].setWeekDayCode(weekDayCode);
	_shiftTimingsMstVO[i].setShiftStartTime(startTimeHr[i+1]+":"+startTimeMin[i+1]);
	_shiftTimingsMstVO[i].setShiftEndTime(endTimeHr[i+1]+":"+endTimeMin[i+1]);
	
	
	 }
	
	
}
else//if we don't  want to make daywise ,then for each shift we enter 7 rows for each day
{
			
			String [] startTimeHr=_fb.getStartTimeHrUser();
			String [] startTimeMin=_fb.getStartTimeMinUser();
			String [] endTimeHr=_fb.getEndTimeHrUser();
			String [] endTimeMin=_fb.getEndTimeMinUser();
			
			_shiftMstVO.setShiftStartTime(startTimeHr[0]+":"+startTimeMin[0]);
			_shiftMstVO.setShiftEndTime(endTimeHr[0]+":"+endTimeMin[0]);
			
//iterating through the DutyRosterShiftTimingsMasterVO vo's and setting the timings
			
			for(int i=0; i < _shiftTimingsMstVO.length ; i++)
			 {
				
				String weekDayCode=Integer.toString(i+1);
			
			_shiftTimingsMstVO[i]=new DutyRosterShiftTimingsMasterVO();
			_shiftTimingsMstVO[i].setWeekDayCode(weekDayCode);
			_shiftTimingsMstVO[i].setShiftStartTime(startTimeHr[0]+":"+startTimeMin[0]);
			_shiftTimingsMstVO[i].setShiftEndTime(endTimeHr[0]+":"+endTimeMin[0]);
			
			
			 }
			
			
}
			
			ShiftTypeMstDATA.saveShiftTypeInfo(_shiftMstVO, userVO,_shiftTimingsMstVO);
			
			objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
		}
		catch(HisDuplicateRecordException e){	   		   	
			objStatus.add(Status.NEW,"",e.getMessage());	
	  		 e.printStackTrace(); 
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
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
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
			//_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}

	// on modify Page for Showing Data of Selected Record
	public static boolean fetchShiftTypeInfo(ShiftTypeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		DutyRosterShiftMasterVO _shiftMstVO=new DutyRosterShiftMasterVO();
		DutyRosterShiftMasterVO  shiftMasterVO=new DutyRosterShiftMasterVO();
		String shiftTypeCode="";
		String _hCode="";
		String _sTypeCode="";
		Map essentialMap=new HashMap(); 		
		DutyRosterShiftTimingsMasterVO[]  _shiftTimeVO=null;
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String shiftCode = concatid[0];
			String shiftSlno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			_shiftMstVO.setShiftCode(shiftCode);
			_shiftMstVO.setSerialNo(shiftSlno);
			_shiftMstVO.setHospitalCode(sHtcode);
			_shiftMstVO.setIsValid(_fb.getIsValid());

			essentialMap=ShiftTypeMstDATA.fetchShiftTypeInfo(_shiftMstVO, userVO);
			WebUTIL.setMapInSession(essentialMap,_request);
			
			shiftMasterVO=(DutyRosterShiftMasterVO)essentialMap.get(DutyRosterConfig.DUTY_ROSTER_MASTER_VO_SHIFT_MST);
			
			_shiftTimeVO=(DutyRosterShiftTimingsMasterVO[])essentialMap.get(DutyRosterConfig.VO_OF_SHIFT_TIMINGS);
			
			
			_hCode=userVO.getHospitalCode();
			_sTypeCode=shiftMasterVO.getShiftTypeCode();
			
			String shiftTypeName="";
			
			
			
			/*if(_fb.getHmode().equals("VIEW"))
			{
				ArrayList shiftTypeList=(ArrayList)essentialMap.get(DutyRosterConfig.DUTY_ROSTER_MASTER_LIST_OF_SHIFT_TYPE);
				Iterator itr=shiftTypeList.iterator();
				
				while(itr.hasNext())
				{
					Entry entryObj=(Entry)itr.next();
					if(entryObj.getValue().equals(shiftMasterVO.getShiftTypeCode()))
					{
						shiftTypeName=entryObj.getLabel();
					}
				}
			}*/
				
			
			String[] startTime=null;
			String[] endTime=null;
			

			
			
			String concateValues=shiftMasterVO.getShiftTypeCode().replace("^", "@");
			String[] arrayValues=concateValues.split("@");
			
			
			if(_fb.getHmode().equals("MODIFY"))
			{
			_fb.setStartTimeTable(arrayValues[1]);
			_fb.setEndTimeTable(arrayValues[2]);
			}
			
			
			HelperMethods.populate(_fb, shiftMasterVO);
			
			//_fb.setShiftTypeName(shiftTypeName);
			
			//here we are retaining the values of checkboxin chk 
			_fb.setChk(chk);

			//here we are retaining the values of shiftType in  a hidden textbox of shiftType name
			//so that it can be used in case of modification
			_fb.setShiftTypeName(_fb.getShiftTypeCode());
			
//in case the daywise shift is 0 ,then we can fetch the data from the table shift master			
			
if(shiftMasterVO.getIsDayWiseShift().equals("0"))	
			{
				startTime=shiftMasterVO.getShiftStartTime().split(":");
				endTime=shiftMasterVO.getShiftEndTime().split(":");
			
				String[] startTimeHrUser={startTime[0],startTime[0],startTime[0],startTime[0],startTime[0],startTime[0],startTime[0]};
				String[] startTimeMinUser={startTime[1],startTime[1],startTime[1],startTime[1],startTime[1],startTime[1],startTime[1]};
				String[] endTimeHrUser={endTime[0],endTime[0],endTime[0],endTime[0],endTime[0],endTime[0],endTime[0]};
				String[] endTimeMinUser={endTime[1],endTime[1],endTime[1],endTime[1],endTime[1],endTime[1],endTime[1]};
				
			_fb.setStartTimeHrUser(startTimeHrUser);
			_fb.setStartTimeMinUser(startTimeMinUser);
			_fb.setEndTimeHrUser(endTimeHrUser);
			_fb.setEndTimeMinUser(endTimeMinUser); 
			
			}
else//else we fetch the data from the shiftTimings Master
if(shiftMasterVO.getIsDayWiseShift().equals("1"))	
{
ArrayList startHr=new ArrayList();
ArrayList startMin=new ArrayList();
ArrayList endHr=new ArrayList();
ArrayList endMin=new ArrayList();

//ITEARATING THROUGH ALL THE TIMINGS VO 
//AND PUTTING IT INTO AN ARRAYLIST
for(int i=0 ;i < _shiftTimeVO.length ; i++)
	{
	
		startTime=_shiftTimeVO[i].getShiftStartTime().split(":");
		endTime=_shiftTimeVO[i].getShiftEndTime().split(":");
		
		startHr.add(startTime[0]);
		startMin.add(startTime[1]);
		endHr.add(endTime[0]);
		endMin.add(endTime[1]);
		
		
	}

//NOW MAKING ARRAY FROM ARRAYLIST

		String[] startTimeHrUser=(java.lang.String[])startHr.toArray(new String[startHr.size()]);
		String[] startTimeMinUser=(String[])startMin.toArray(new String[startHr.size()]);
		String[] endTimeHrUser=(String[])endHr.toArray(new String[startHr.size()]);
		String[] endTimeMinUser=(String[])endMin.toArray(new String[startHr.size()]);
		
		
//FINALLLY PUTTING THAT ARRAY INTO THE FROM BEAN		
	_fb.setStartTimeHrUser(startTimeHrUser);
	_fb.setStartTimeMinUser(startTimeMinUser);
	_fb.setEndTimeHrUser(endTimeHrUser);
	_fb.setEndTimeMinUser(endTimeMinUser); 
	
	}
			
			
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

	// for Updating The old Record
	public static boolean updateShiftTypeInfo(ShiftTypeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			DutyRosterShiftMasterVO _shiftMstVO=new DutyRosterShiftMasterVO();
			DutyRosterShiftTimingsMasterVO[] _shiftTimingsMstVO=new DutyRosterShiftTimingsMasterVO[7];
			
			
			// Fetching Selected Record Primary Key
			String[] chk = _fb.getChk().split("@");
			
			String shiftCode = chk[0];
			String serialNo = chk[1];
			String sHtcode = chk[2];
			
		
			HelperMethods.populate(_shiftMstVO, _fb);
			_shiftMstVO.setShiftCode(shiftCode);
			
		
			
			
			// fetching the shift ID Information (Concatinated Values & Time Values ) 
			// from the hidden text box of shiftTypeName  
			
			String concateValues=_fb.getShiftTypeName().replace("^","@");
			String splitValues[]=concateValues.split("@");
			_shiftMstVO.setShiftTypeCode(splitValues[0]);
			//_shiftMstVO.setShiftStartTime(_fb.getStartTimeHrUser()+":"+_fb.getStartTimeMinUser());
		//	_shiftMstVO.setShiftEndTime(_fb.getEndTimeHrUser()+":"+_fb.getEndTimeMinUser());
			
			
			//In case the days are different 
			//then timings wil be different			
			if(_fb.getIsDayWiseShift().equals("1"))
			{
				String [] startTimeHr=_fb.getStartTimeHrUser();
				String [] startTimeMin=_fb.getStartTimeMinUser();
				String [] endTimeHr=_fb.getEndTimeHrUser();
				String [] endTimeMin=_fb.getEndTimeMinUser();
				
				//iterating through the DutyRosterShiftTimingsMasterVO vo's and setting the timings
				
				for(int i=0; i < _shiftTimingsMstVO.length ; i++)
				 {
					
					String weekDayCode=Integer.toString(i+1);
				
				_shiftTimingsMstVO[i]=new DutyRosterShiftTimingsMasterVO();
				_shiftTimingsMstVO[i].setWeekDayCode(weekDayCode);
				_shiftTimingsMstVO[i].setShiftStartTime(startTimeHr[i+1]+":"+startTimeMin[i+1]);
				_shiftTimingsMstVO[i].setShiftEndTime(endTimeHr[i+1]+":"+endTimeMin[i+1]);
				_shiftTimingsMstVO[i].setShiftCode(shiftCode);
				
				 }
				
				
			}
			else//if we don't  want to make daywise ,then the timings will be same for each 7 rows of the  days
			{
						
						String [] startTimeHr=_fb.getStartTimeHrUser();
						String [] startTimeMin=_fb.getStartTimeMinUser();
						String [] endTimeHr=_fb.getEndTimeHrUser();
						String [] endTimeMin=_fb.getEndTimeMinUser();
						
						
			//in case the timings are same for all the days then we enter into the shift master also			
						_shiftMstVO.setShiftStartTime(startTimeHr[0]+":"+startTimeMin[0]);
						_shiftMstVO.setShiftEndTime(endTimeHr[0]+":"+endTimeMin[0]);
						
			//iterating through the DutyRosterShiftTimingsMasterVO vo's and setting the timings
						
						for(int i=0; i < _shiftTimingsMstVO.length ; i++)
						 {
							
							String weekDayCode=Integer.toString(i+1);
						
						_shiftTimingsMstVO[i]=new DutyRosterShiftTimingsMasterVO();
						_shiftTimingsMstVO[i].setWeekDayCode(weekDayCode);
						_shiftTimingsMstVO[i].setShiftStartTime(startTimeHr[0]+":"+startTimeMin[0]);
						_shiftTimingsMstVO[i].setShiftEndTime(endTimeHr[0]+":"+endTimeMin[0]);
						_shiftTimingsMstVO[i].setShiftCode(shiftCode);
						
						 }
						
						
			}
			
			ShiftTypeMstDATA.updateShiftTypeInfo(shiftCode,serialNo,_shiftMstVO, userVO,_shiftTimingsMstVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDuplicateRecordException e){	   		   	
			objStatus.add(Status.TRANSINPROCESS,"",e.getMessage());	
	  		 e.printStackTrace(); 
	  		 flag=false;
	  		 _fb.setHmode("MODIFY");
	  		
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}

	
}
