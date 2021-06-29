package mrd.masters.controller.util;

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
import hisglobal.vo.RackMstVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.masters.controller.data.RackMstDATA;
import mrd.masters.controller.fb.RackMstFB;


public class RackMstUTL extends ControllerUTIL
{
	
	public static void getEssentialForRackMst(RackMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		UserVO userVO = getUserVO(_request);
		Map essentialMap=null;
		try
		{
			essentialMap=RackMstDATA.getEssentialForRackMst(userVO);
			List mrdList=(List)essentialMap.get(MrdConfig.ESSENTIAL_MRD_LIST_OPTION);
			if(mrdList!=null && mrdList.size()==1){
				_fb.setMrdSize("1");
				_fb.setMrdCode(((Entry)mrdList.get(0)).getValue());
			}
			
			WebUTIL.setMapInSession(essentialMap, _request);
			objStatus.add(Status.TRANSINPROCESS);
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
		
	}
	
	public static void getBlockList(RackMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List blockList=new ArrayList();
		UserVO userVO = getUserVO(_request);
		String buildingCode=fb.getBuildingCode();
		HttpSession session =WebUTIL.getSession(_request);
		try
		{
			blockList=RackMstDATA.getBlockList(buildingCode,userVO);
			WebUTIL.setAttributeInSession(_request, MrdConfig.BLOCK_LIST, blockList);
			session.removeAttribute(MrdConfig.FLOOR_LIST);
			session.removeAttribute(MrdConfig.ROOM_ID_LIST);
			objStatus.add(Status.TRANSINPROCESS);
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
			//System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}
	
	public static void getFloorList(RackMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List floorList=new ArrayList();
		UserVO userVO = getUserVO(_request);
		String blockId=fb.getBlockId();
		HttpSession session =WebUTIL.getSession(_request);
		try
		{
			floorList=RackMstDATA.getFloorList(blockId,userVO);
			WebUTIL.setAttributeInSession(_request,MrdConfig.FLOOR_LIST,floorList);
			session.removeAttribute(MrdConfig.ROOM_ID_LIST);
			objStatus.add(Status.TRANSINPROCESS);
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
			//System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}
	
	public static void getRoomList(RackMstFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List roomList=new ArrayList();
		UserVO userVO = getUserVO(_request);
		String floorId=fb.getFloorId();
		try
		{
			roomList=RackMstDATA.getRoomList(floorId,userVO);
			WebUTIL.setAttributeInSession(_request,MrdConfig.ROOM_ID_LIST,roomList);
			objStatus.add(Status.TRANSINPROCESS);
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
			//System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
	}

	
	public static boolean saveRackDetais(RackMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			RackMstVO _RackMstVO = new RackMstVO();
	
			HelperMethods.populate(_RackMstVO, _fb);
			hasFlag=RackMstDATA.saveRackDetails(_RackMstVO, userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.NEW, "Record Added Successfully", "");
			 }else
			 {		//System.out.println("in false");
				 objStatus.add(Status.NEW, "The Rack Name Already Exists", "");
			 }

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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			//System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static boolean getRackDetails(RackMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RackMstVO _RackMstVO = new RackMstVO();
		Map mp = new HashMap();

		try
		{
			UserVO userVO = getUserVO(_request);
			// Fetching Selected Record Primary Key
			String chk = _fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			_RackMstVO.setRackId(concatid[0]);
			_RackMstVO.setSerialNo(concatid[2]);
			_RackMstVO.setHospitalCode(concatid[1]);
			//_RackMstVO.setIsValid(_fb.getIsValid());
			

			mp = RackMstDATA.fetchRackDetails(_RackMstVO, userVO);
			RackMstVO vo=new RackMstVO();
			vo=(RackMstVO)mp.get(MrdConfig.RACK_MST_VO);
			HelperMethods.populate(_fb,vo);
			List mrdList=(List)mp.get(MrdConfig.ESSENTIAL_MRD_LIST_OPTION);
			if(mrdList!=null && mrdList.size()==1){
				_fb.setMrdSize("1");
				_fb.setMrdCode(((Entry)mrdList.get(0)).getValue());
			}
			
			WebUTIL.setMapInSession(mp, _request);
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
			//System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return true;
	}
	
	public static boolean modifyRackDetails(RackMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag=true;

		try
		{
			UserVO userVO = getUserVO(_request);
			RackMstVO _RackMstVO = new RackMstVO();
					
			HelperMethods.populate(_RackMstVO,_fb);
			hasFlag=RackMstDATA.modifyRackDetails(_RackMstVO,userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.NEW, "Record Modified Successfully", "");
			 }else
			 {		System.out.println("in false");
				 objStatus.add(Status.NEW, "The Rack Name Already Exists", "");
			 }			
			
			objStatus.add(Status.TRANSINPROCESS);
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
			//System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	
}
		

