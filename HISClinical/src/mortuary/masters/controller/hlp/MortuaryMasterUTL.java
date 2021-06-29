 package mortuary.masters.controller.hlp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mortuary.MortuaryConfig;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.MortuaryMasterVO;
import hisglobal.vo.UserVO;

public class MortuaryMasterUTL extends ControllerUTIL
{
	//Getting Essential Data 
	public static void getEssentialForMortuaryMst(MortuaryMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		Map mp=new HashMap();
		try
		{
			setSysdate(request);
			mp=MortuaryMasterDATA.getEssentialForMortuaryMst(getUserVO(request));
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
	public static void getEmployeeListBasedOnDept(MortuaryMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List empList=new ArrayList();
		List newEmpList=new ArrayList();
		UserVO userVO = getUserVO(request);
		String deptCode=fb.getDepartmentCode();
		String hod="";
		
		try
		{
			empList=MortuaryMasterDATA.getEmployeeListBasedOnDept(deptCode,userVO);
	//		WebUTIL.setAttributeInSession(request, MortuaryConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT,empList);

			Entry ent=(Entry)empList.get(0);
			//hod=ent.getValue().split("@")[1];
			//fb.setHod(hod); problrm in modify/view in mortury incharge @ anant patel
			for(int i=0;i<empList.size();i++)
			{
				Entry entry=(Entry)empList.get(i);
				entry.setValue(entry.getValue().split("@")[0]);
				newEmpList.add(entry);
			}	
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT,newEmpList);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT,empList);
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
	
	//Getting the List of Block on the Basis of Building
	public static void getBlockList(MortuaryMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List blockList=new ArrayList();
		UserVO userVO = getUserVO(request);
		String buildingCode=fb.getBuildingCode();
		HttpSession session =WebUTIL.getSession(request);
		try
		{
			blockList=MortuaryMasterDATA.getBlockList(buildingCode,userVO);
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESSENTIAL_ALL_BLOCK_BASED_ON_BUILDING,blockList);
			session.removeAttribute(MortuaryConfig.ESSENTIAL_ALL_FLOOR_BASED_ON_BLOCK);
			session.removeAttribute(MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(request, MortuaryConfig.ESSENTIAL_ALL_BLOCK_BASED_ON_BUILDING,blockList);
			session.removeAttribute(MortuaryConfig.ESSENTIAL_ALL_FLOOR_BASED_ON_BLOCK);
			session.removeAttribute(MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR);
			objStatus.add(Status.UNSUCESSFULL, "", "Block Not Found");
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
	
	//Getting the List of Floor on the Basis of Block
	public static void getFloorList(MortuaryMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List floorList=new ArrayList();
		UserVO userVO = getUserVO(request);
		String blockId=fb.getBlockId();
		HttpSession session =WebUTIL.getSession(request);
		try
		{
			floorList=MortuaryMasterDATA.getFloorList(blockId,userVO);
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ESSENTIAL_ALL_FLOOR_BASED_ON_BLOCK,floorList);
			session.removeAttribute(MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ESSENTIAL_ALL_FLOOR_BASED_ON_BLOCK,floorList);
			session.removeAttribute(MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR);
			objStatus.add(Status.UNSUCESSFULL, "", "Floor Not Found");
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
	
	//Getting the List of Room on the Basis of Floor
	public static void getRoomList(MortuaryMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List roomList=new ArrayList();
		UserVO userVO = getUserVO(request);
		String floorId=fb.getFloorId();
		try
		{
			roomList=MortuaryMasterDATA.getRoomList(floorId,userVO);
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR,roomList);
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(request,MortuaryConfig.ESSENTIAL_ALL_ROOM_BASED_ON_FLOOR,roomList);
			objStatus.add(Status.UNSUCESSFULL, "", "Room Not Found ");
			
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
	
	//Saving the Data
	public static boolean saveMortuaryMaster(MortuaryMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean exist=true;
		MortuaryMasterVO mortuaryMstVO=new MortuaryMasterVO();
		try
		{
			HelperMethods.populate(mortuaryMstVO, fb);
			MortuaryMasterDATA.saveMortuaryMaster(mortuaryMstVO,getUserVO(request));
			exist=false;
			objStatus.add(Status.DONE,"","Record Added Successfully");
			
		}
		catch (HisDuplicateRecordException e)
		{
			e.printStackTrace();
			exist=true;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			exist=true;
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return exist;
	}
	
	public static boolean getDataForModify(MortuaryMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MortuaryMasterVO _MorturyMasterVO = new MortuaryMasterVO();
		Map mp = new HashMap();
		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sMorturyCode = concatid[0];
			String shospitalCode = concatid[1];
			String sMorturySlno = concatid[2];
			// putting the selected Record Primary Key into Vo

			fb.setMortuaryCode(sMorturyCode);
			fb.setSlNo(sMorturySlno);

			_MorturyMasterVO.setSlNo(sMorturySlno);
			_MorturyMasterVO.setMortuaryCode(sMorturyCode);
			
			_MorturyMasterVO = MortuaryMasterDATA.getDataForModify(_MorturyMasterVO, userVO);
		    HelperMethods.populate(fb, _MorturyMasterVO);
			fb.setSlNo(sMorturySlno);
			fb.setMortuaryCode(sMorturyCode);
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
	
	public static boolean saveModMortuaryMaster(MortuaryMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			MortuaryMasterVO _MortuaryMasterVO = new MortuaryMasterVO();

			HelperMethods.populate( _MortuaryMasterVO , fb);
			flag=MortuaryMasterDATA.saveModMortuaryMaster(_MortuaryMasterVO, userVO);
			if(flag){
				objStatus.add(Status.DONE,"","record modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Mortuary Name already exists");
				
			}
			
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
			objStatus.add(Status.ERROR, "", "Mortuary Name already exists");
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
