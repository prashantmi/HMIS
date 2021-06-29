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
import opd.master.controller.data.ModifyViewUserDeskMenuMasterDATA;
import opd.master.controller.fb.UserDeskMenuMasterFB;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.DeskMasterVO;
import hisglobal.vo.UserDeskMenuMasterVO;
import hisglobal.vo.UserVO;

public class ModifyViewUserDeskMenuMasterUTIL extends ControllerUTIL 
{
	//* Fetching User Desk Menu Record
	public static void fetchUserDeskRecordData(UserDeskMenuMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
		
			
			UserDeskMenuMasterVO voUsrDeskMst=new UserDeskMenuMasterVO();
			
			String chk=_fb.getChk()[0].replace("^","@");
			String[] concatid=chk.split("@");
			_fb.setHospitalCode(concatid[2]);
			_fb.setMappingSeqNo(concatid[1]);
			voUsrDeskMst.setDeskType(concatid[0]);
			voUsrDeskMst.setMappingSeqNo(concatid[1]);
			voUsrDeskMst.setHospitalCode(concatid[2]);
			
				
			voUsrDeskMst = ModifyViewUserDeskMenuMasterDATA.fetchUserDeskMenuVO(voUsrDeskMst,userVO);
			
			//String selUnit=voUsrDeskMst.getDeptUnitCode();
			
			_fb.setDeskType(voUsrDeskMst.getDeskType());
			_fb.setDeskTypeName(voUsrDeskMst.getDeskTypeName());
			_fb.setMappingType(voUsrDeskMst.getMappingType());
			_fb.setMappedTo(voUsrDeskMst.getMappedTo());
			//_fb.setDeskMenuId(voUsrDeskMst.getUserDeskMenuId());
			_fb.setIsValid(voUsrDeskMst.getIsValid());
			_fb.setDeskId(voUsrDeskMst.getDeskId());
			_fb.setDeskName(voUsrDeskMst.getDeskName());
			
		//	_fb.setUserSeatId(voUsrDeskMst.getUserSeatId());
			//_fb.setDeptUnitCode(voUsrDeskMst.getDeptUnitCode());
		//	
		
			
			
			
			/*//using method of Userdesk template master to get desk name
			DeskMasterVO voDesk=AddUserDeskMenuTemplateMasterDATA.fetchDeskVOByDeskId(voUsrDeskMst.getDeskId(),userVO);
			_fb.setDeskName(voDesk.getDeskName());			
			
			if(_fb.getUserSeatId()!=null )
			{
				UserDeskMenuMasterVO Seat=ModifyViewUserDeskMenuMasterDATA.getSeats(voUsrDeskMst,userVO);
				//_fb.setGotSeats("1");
				_fb.setSeatName(Seat.getSeatName());
				_fb.setUserSeatId(Seat.getUserSeatId());
			}
				_fb.setDeskTypeDesc(DynamicDeskConfig.DESK_TYPES[Integer.parseInt(_fb.getDeskType())]);		// Setting the Desk Type Description from DynamicDeskConfig
			
				
				//getting unit name
				UserDeskMenuMasterVO unitName=ModifyViewUserDeskMenuMasterDATA.getUnitName(selUnit,userVO);
				_fb.setUnitName(unitName.getUnitName());
				voUsrDeskMst.setUnitName(_fb.getUnitName());
				_fb.setDeptUnitCode(unitName.getDeptUnitCode());
				voUsrDeskMst.setDeptUnitCode(_fb.getDeptUnitCode());
		*/		
				
			/*else										//Checking for Unit Wise
			{
				Entry entobj=(Entry)al.get(0);
				_fb.setUnitName(entobj.getValue());
				entobj=(Entry)al.get(1);
				_fb.setDeskName(entobj.getValue());
				entobj=(Entry)al.get(2);
				/*_fb.setDeskTypeDesc(entobj.getValue());
				entobj=(Entry)al.get(3);*/
			/*	_fb.setDeskType(entobj.getValue());
				_fb.setDeskTypeDesc(DynamicDeskConfig.DESK_TYPES[Integer.parseInt(_fb.getDeskType())]);		// Setting the Desk Type Description from DynamicDeskConfig 
			}*/

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
	public static void getDeskListByType(UserDeskMenuMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List lstDesks=new ArrayList();
		Map mp= new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			String DeskType=_fb.getDeskType();
			lstDesks=ModifyViewUserDeskMenuMasterDATA.getDeskListByType(DeskType,userVO);
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
	public static boolean UpdateUserDesk(UserDeskMenuMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		try
		{
			UserVO userVO = getUserVO(_request);

			UserDeskMenuMasterVO voUserDeskMenu=new UserDeskMenuMasterVO();
			//voUserDeskMenu.setUserSeatId(_fb.getUserSeatId());
			//voUserDeskMenu.setDeptUnitCode(_fb.getDeptUnitCode());
			voUserDeskMenu.setDeskId(_fb.getDeskId());
			voUserDeskMenu.setDeskType(_fb.getDeskType());
			voUserDeskMenu.setIsValid(_fb.getIsValid());
			voUserDeskMenu.setMappingSeqNo(_fb.getMappingSeqNo());
			voUserDeskMenu.setMappingType(_fb.getMappingType());
			voUserDeskMenu.setHospitalCode(_fb.getHospitalCode());
			//voUserDeskMenu.setUserDeskMenuId(_fb.getDeskMenuId());
			
			ModifyViewUserDeskMenuMasterDATA.UpdateUserDeskMenuVO(voUserDeskMenu,userVO);
			
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
