package opd.master.controller.util;

/**
 * @author  CDAC
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.AddUserDeskMenuTemplateMasterDATA;
import opd.master.controller.data.AddUserDeskUnitWardMappingMasterDATA;
import opd.master.controller.data.ModifyViewUserDeskUnitWardMappingMasterDATA;
import opd.master.controller.fb.UserDeskUnitWardMappingMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;

public class ModifyViewUserDeskUnitWardMappingMasterUTIL extends ControllerUTIL 
{
	//* Fetching User Desk Unit Ward Mapping Master Record
	public static void fetchUserDeskRecordData(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			/*if(_fb.getControls()[0]!=null)
			{
				_fb.setDeskType(_fb.getControls()[0]);
			}*/
			
			UserDeskUnitWardMappingMasterVO voUsrDeskUnitWardMst=new UserDeskUnitWardMappingMasterVO();
			
			String chk=_fb.getChk()[0].replace("^","@");
			String[] concatid=chk.split("@");
			voUsrDeskUnitWardMst.setUserDeskMenuId(concatid[0]);
			voUsrDeskUnitWardMst.setHospitalCode(concatid[1]);
			voUsrDeskUnitWardMst.setIsValid(_fb.getIsActive());
					
			UserDeskUnitWardMappingMasterVO _voUsrDeskUnitWardMst = ModifyViewUserDeskUnitWardMappingMasterDATA.fetchUserDeskMenuVO(voUsrDeskUnitWardMst,userVO);
			
			_fb.setUserSeatId(_voUsrDeskUnitWardMst.getUserSeatId());
			_fb.setDeptUnitCode(_voUsrDeskUnitWardMst.getDeptUnitCode());
			_fb.setDeskId(_voUsrDeskUnitWardMst.getDeskId());
			_fb.setWardCode(_voUsrDeskUnitWardMst.getWardCode());
			_fb.setIsValid(_voUsrDeskUnitWardMst.getIsValid());
			_fb.setDeskMenuId(_voUsrDeskUnitWardMst.getUserDeskMenuId());
			_fb.setDeskType(_voUsrDeskUnitWardMst.getDeskType());
			
			String selUnit=_fb.getDeptUnitCode();
			
			UserDeskMenuTemplateMasterVO unitName=AddUserDeskMenuTemplateMasterDATA.getUnitName(selUnit,userVO);
			_fb.setUnitName(unitName.getUnitName());
			_voUsrDeskUnitWardMst.setUnitName(_fb.getUnitName());
			_fb.setDeptUnitCode(unitName.getDeptUnitCode());
			_voUsrDeskUnitWardMst.setDeptUnitCode(_fb.getDeptUnitCode());
			
			_fb.setDeskTypeDesc(DynamicDeskConfig.DESK_TYPES[Integer.parseInt(_fb.getDeskType())]);		// Setting the Desk Type Description from DynamicDeskConfig
						
			if((_fb.getUserSeatId()!=null && (_fb.getWardCode()!=null)))	//Checking for Unit Ward Seat Wise
			{
					
				UserDeskUnitWardMappingMasterVO ward = ModifyViewUserDeskUnitWardMappingMasterDATA.gettingWards(_voUsrDeskUnitWardMst,userVO);
				_fb.setWardCode(ward.getWardCode());
				_fb.setWardName(ward.getWardName());
				
				UserDeskUnitWardMappingMasterVO seat = ModifyViewUserDeskUnitWardMappingMasterDATA.gettingSeats(_voUsrDeskUnitWardMst,userVO);
				_fb.setUserSeatId(seat.getUserSeatId());
				_fb.setSeatName(seat.getSeatName());
			
			}
			
			if((_fb.getUserSeatId()==null && (_fb.getWardCode()!=null)))
			{
				UserDeskUnitWardMappingMasterVO ward = ModifyViewUserDeskUnitWardMappingMasterDATA.gettingWards(_voUsrDeskUnitWardMst,userVO);
				_fb.setWardCode(ward.getWardCode());
				_fb.setWardName(ward.getWardName());
			}
				
			DeskMasterVO voDesk=AddUserDeskMenuTemplateMasterDATA.fetchDeskVOByDeskId(_fb.getDeskId(),userVO);
			_fb.setDeskName(voDesk.getDeskName());
			
			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}	
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	//* Setting Source Seat List and Desk List By Type
	public static void getDeskListByType(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesks=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			String DeskType=_fb.getDeskType();
			lstDesks=ModifyViewUserDeskUnitWardMappingMasterDATA.getDeskListByType(DeskType,userVO);
			mp.put(OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE,lstDesks);
			WebUTIL.setMapInSession(mp,_request);

			objStatus.add(Status.INPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}

	//* Update Desk To Seats Unit-Wise in Desk Menu Master
	public static boolean UpdateUserDesk(UserDeskUnitWardMappingMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);

			UserDeskUnitWardMappingMasterVO voUserDeskMenu=new UserDeskUnitWardMappingMasterVO();
			voUserDeskMenu.setUserSeatId(_fb.getUserSeatId());
			voUserDeskMenu.setDeptUnitCode(_fb.getDeptUnitCode());
			voUserDeskMenu.setDeskId(_fb.getDeskId());
			voUserDeskMenu.setWardCode(_fb.getWardCode());
			voUserDeskMenu.setDeskType(_fb.getDeskType());
			voUserDeskMenu.setIsValid(_fb.getIsValid());
			voUserDeskMenu.setUserDeskMenuId(_fb.getDeskMenuId());
			
			ModifyViewUserDeskUnitWardMappingMasterDATA.UpdateUserDeskMenuVO(voUserDeskMenu,userVO);
			
			if(_fb.getUserSeatId()!=null && _fb.getWardCode()!=null)
					_fb.setAdditionMode(OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_SEAT_WISE);
			if(_fb.getUserSeatId()==null && _fb.getWardCode()!=null)
					_fb.setAdditionMode(OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_WISE);
			if(_fb.getUserSeatId()==null && _fb.getWardCode()==null)
				_fb.setAdditionMode(OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WISE);
			
			if(_fb.getAdditionMode()==OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WISE)
				AddUserDeskUnitWardMappingMasterDATA.AddDesktoUnitWise(voUserDeskMenu,userVO);
			if(_fb.getAdditionMode()==OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_WISE)
				AddUserDeskUnitWardMappingMasterDATA.AddDesktoUnitWardWise(voUserDeskMenu,userVO);
			if(_fb.getAdditionMode()==OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_SEAT_WISE)
				AddUserDeskUnitWardMappingMasterDATA.AddDesktoUnitWardWise(voUserDeskMenu,userVO);
			
			objStatus.add(Status.DONE);
		}
		catch(HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return true;
	}

}
