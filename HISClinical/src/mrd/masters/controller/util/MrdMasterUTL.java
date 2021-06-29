package mrd.masters.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.masters.controller.data.MrdMasterDATA;
import mrd.masters.controller.fb.MrdMasterFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MrdMasterVO;
import hisglobal.vo.UserVO;

public class MrdMasterUTL extends ControllerUTIL
{
	public static void getEssentialForMrdMst(MrdMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			//setSysdate(request);
			mp=MrdMasterDATA.getEssentialForMrdMst(getUserVO(request));
			String mainMrdFlag=(String)mp.get(MrdConfig.MAIN_MRD_FLAG_EXISTENCE_CHECK);
			fb.setMainMrdFlag(mainMrdFlag);
			
			if(!mainMrdFlag.equals("0"))
			{
				fb.setMrdType(MrdConfig.MRD_TYPE_SUB_MRD);
			}
			
			WebUTIL.setMapInSession(mp, request);
		//	System.out.println("sxsxcsd");
		}
		catch(HisRecordNotFoundException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR," ","");			
		}
		catch(HisDataAccessException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
		}		
		catch(Exception e)
		{			
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in UTIL","");
		}		
		finally
		{
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	//Getting the List of Employee on the Basis of Department
	public static void getEmployeeListBasedOnDept(MrdMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		HttpSession session=WebUTIL.getSession(request);
		List empList=new ArrayList();
		List newEmpList=new ArrayList();
		UserVO userVO = getUserVO(request);
		String deptCode=fb.getDepartmentCode();
		String hod="";		
		try
		{
			session.removeAttribute(MrdConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT);
			
			empList=MrdMasterDATA.getEmployeeListBasedOnDept(deptCode,userVO);
			//WebUTIL.setAttributeInSession(request, MortuaryConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT,empList);

			
			Entry ent=(Entry)empList.get(0);
			if((ent.getValue().split("@").length)>1){
				hod=ent.getValue().split("@")[1];				
			}
			else{
				hod="";
			}
				
			
			fb.setHod(hod);
		
			for(int i=0;i<empList.size();i++)
			{
				Entry entry=(Entry)empList.get(i);
				entry.setValue(entry.getValue().split("@")[0]);
				newEmpList.add(entry);
			}	
			
			WebUTIL.setAttributeInSession(request, MrdConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT,newEmpList);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(request, MrdConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT,empList);
			objStatus.add(Status.UNSUCESSFULL, "", "Employee Not Found");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
	
	public static void getBlockList(MrdMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List blockList=new ArrayList();
		UserVO userVO = getUserVO(_request);
		String buildingCode=fb.getBuildingCode();
		HttpSession session =WebUTIL.getSession(_request);
		try
		{
			blockList=MrdMasterDATA.getBlockList(buildingCode,userVO);
			WebUTIL.setAttributeInSession(_request, MrdConfig.BLOCK_LIST, blockList);
			session.removeAttribute(MrdConfig.FLOOR_LIST);
			session.removeAttribute(MrdConfig.ROOM_ID_LIST);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "No Block Found");
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
	
	public static void getFloorList(MrdMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List floorList=new ArrayList();
		UserVO userVO = getUserVO(_request);
		String blockId=fb.getBlockId();
		HttpSession session =WebUTIL.getSession(_request);
		try
		{
			floorList=MrdMasterDATA.getFloorList(blockId,userVO);
			WebUTIL.setAttributeInSession(_request,MrdConfig.FLOOR_LIST,floorList);
			session.removeAttribute(MrdConfig.ROOM_ID_LIST);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "No Floor Found");
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
	
	public static void getRoomList(MrdMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List roomList=new ArrayList();
		UserVO userVO = getUserVO(_request);
		String floorId=fb.getFloorId();
		try
		{
			roomList=MrdMasterDATA.getRoomList(floorId,userVO);
			WebUTIL.setAttributeInSession(_request,MrdConfig.ROOM_ID_LIST,roomList);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "No Room Found");
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
	
	public static boolean saveMrdDetais(MrdMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			MrdMasterVO mrdMstVO = new MrdMasterVO();
			
			mrdMstVO.setMrdName(_fb.getMrdName());
			mrdMstVO.setShortName(_fb.getMrdShortName());
			mrdMstVO.setMrdType(_fb.getMrdType());
			mrdMstVO.setDeptCode(_fb.getDepartmentCode());
			mrdMstVO.setEmpNo(_fb.getEmpNo());
			mrdMstVO.setLocationDesc(_fb.getLocationDesc());
			mrdMstVO.setBuildingCode(_fb.getBuildingCode());
			mrdMstVO.setNumBlockId(_fb.getBlockId());
			mrdMstVO.setNumFloorId(_fb.getFloorId());
			mrdMstVO.setNumRoomId(_fb.getRoomId());
			mrdMstVO.setSlNo("1");
			
			hasFlag=MrdMasterDATA.saveMrdDetails(mrdMstVO, userVO);
			
			if(hasFlag)
			 {
				 objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
			 }else
			 {		System.out.println("in false");
				 objStatus.add(Status.NEW, "The Rack Name Already Exists", "");
			 }

		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "No Record Found");
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
			objStatus.add(Status.ERROR, "", "Mrd Name already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}
	
	public static boolean getDataForModify(MrdMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MrdMasterVO mrdMasterVO = new MrdMasterVO();
		Map modifyMap = new HashMap();
		List empList=new ArrayList();
		String hod="";
		List newEmpList=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sMrdCode = concatid[0];
			String sMrdSlno = concatid[1];
			String shospitalCode = concatid[2];
			// putting the selected Record Primary Key into Vo

			

			mrdMasterVO.setSlNo(sMrdSlno);
			mrdMasterVO.setMrdCode(sMrdCode);
			mrdMasterVO.setHospitalCode(shospitalCode);
			
			modifyMap = MrdMasterDATA.getDataForModifyMrdMst(mrdMasterVO, userVO);
		    
			mrdMasterVO=(MrdMasterVO)modifyMap.get(MrdConfig.MRD_MST_VO);
			
			fb.setMrdName(mrdMasterVO.getMrdName());
			fb.setMrdShortName(mrdMasterVO.getShortName());
			
			fb.setMrdType(mrdMasterVO.getMrdType());
			fb.setDepartmentCode(mrdMasterVO.getDeptCode());
			fb.setEmpNo(mrdMasterVO.getEmpNo());
			fb.setLocationDesc(mrdMasterVO.getLocationDesc());
			fb.setBuildingCode(mrdMasterVO.getBuildingCode());
			fb.setBlockId(mrdMasterVO.getNumBlockId());
			fb.setFloorId(mrdMasterVO.getNumFloorId());
			fb.setRoomId(mrdMasterVO.getNumRoomId());
			fb.setIsActive(mrdMasterVO.getIsActive());
			
			fb.setSereialNo(sMrdSlno);
			fb.setMrdCode(sMrdCode);
			
			WebUTIL.setMapInSession(modifyMap, _request);
			
			String mainMrdFlag=(String)modifyMap.get(MrdConfig.MAIN_MRD_FLAG_EXISTENCE_CHECK);
			fb.setMainMrdFlag(mainMrdFlag);
			
			/*
			if(!mainMrdFlag.equals("0"))
			{
				fb.setMrdType(MrdConfig.MRD_TYPE_SUB_MRD);
			}
			*/
			empList=(List)modifyMap.get(MrdConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT);
			

			if( empList!=null && empList.size()>0)
			{
			Entry ent=(Entry)empList.get(0);
			if((ent.getValue().split("@").length)>1){
				hod=ent.getValue().split("@")[1];				
			}
			else{
				hod="";
			}
			
			//hod=ent.getValue().split("@")[1];
			fb.setHod(hod);
		
			for(int i=0;i<empList.size();i++)
			{
				Entry entry=(Entry)empList.get(i);
				entry.setValue(entry.getValue().split("@")[0]);
				newEmpList.add(entry);
			}
			}
			
			WebUTIL.setAttributeInSession(_request, MrdConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT,newEmpList);
			
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "No Record Found");
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
	
	public static boolean saveModMrdMaster(MrdMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			MrdMasterVO mrdMstVO = new MrdMasterVO();

			HelperMethods.populate( mrdMstVO , _fb);
			mrdMstVO.setMrdName(_fb.getMrdName());
			mrdMstVO.setShortName(_fb.getMrdShortName());
			mrdMstVO.setMrdType(_fb.getMrdType());
			mrdMstVO.setDeptCode(_fb.getDepartmentCode());
			mrdMstVO.setEmpNo(_fb.getEmpNo());
			mrdMstVO.setLocationDesc(_fb.getLocationDesc());
			mrdMstVO.setBuildingCode(_fb.getBuildingCode());
			mrdMstVO.setNumBlockId(_fb.getBlockId());
			mrdMstVO.setNumFloorId(_fb.getFloorId());
			mrdMstVO.setNumRoomId(_fb.getRoomId());
			mrdMstVO.setSlNo(_fb.getSereialNo());
			mrdMstVO.setMrdCode(_fb.getMrdCode());
			mrdMstVO.setIsActive(_fb.getIsActive());
			
			flag=MrdMasterDATA.saveModMrdMaster(mrdMstVO, userVO);
			if(flag){
				objStatus.add(Status.DONE,"","Record Modified Successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Mrd Name already exists");
				
			}
			
		}
		
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "No Record Found");
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
			objStatus.add(Status.ERROR, "", "Mrd Name already exists");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}
}
