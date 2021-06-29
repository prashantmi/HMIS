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
import hisglobal.vo.MortuaryAreaMasterVO;
import hisglobal.vo.UserVO;

public class MortuaryAreaMasterUTL extends ControllerUTIL
{
	//Getting Essential Data 
	public static void getEssentialForMortuaryAreaMst(MortuaryAreaMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		Map mp=new HashMap();
		UserVO userVO = getUserVO(request);
		String mortuaryName="";
		List areaTypeLst=new ArrayList();
		try
		{
			setSysdate(request);
			mp=MortuaryAreaMasterDATA.getEssentialForMortuaryAreaMst(getUserVO(request));
			WebUTIL.setMapInSession(mp, request);
			for(int i=0;i<MortuaryConfig.MORTUARY_AREA_TYPE.length-1;i++)
			{
				Entry ent=new Entry();
				ent.setValue(String.valueOf(i+1));
				ent.setLabel(MortuaryConfig.MORTUARY_AREA_TYPE[i+1]);
				areaTypeLst.add(ent);
			}
		WebUTIL.setAttributeInSession(request, MortuaryConfig.MORTUARY_AREA_TYPE_LIST, areaTypeLst);	
			mortuaryName=MortuaryAreaMasterDATA.getMortuaryName(fb.getMortuaryCode(),userVO);
			fb.setMortuaryName(mortuaryName);
			
		
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
	
	
	//Getting the List of Block on the Basis of Building
	public static void getBlockList(MortuaryAreaMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List blockList=new ArrayList();
		UserVO userVO = getUserVO(request);
		String buildingCode=fb.getBuildingCode();
		HttpSession session =WebUTIL.getSession(request);
		try
		{
			blockList=MortuaryAreaMasterDATA.getBlockList(buildingCode,userVO);
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
	public static void getFloorList(MortuaryAreaMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List floorList=new ArrayList();
		UserVO userVO = getUserVO(request);
		String blockId=fb.getBlockId();
		HttpSession session =WebUTIL.getSession(request);
		try
		{
			floorList=MortuaryAreaMasterDATA.getFloorList(blockId,userVO);
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
	public static void getRoomList(MortuaryAreaMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		List roomList=new ArrayList();
		UserVO userVO = getUserVO(request);
		String floorId=fb.getFloorId();
		try
		{
			roomList=MortuaryAreaMasterDATA.getRoomList(floorId,userVO);
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
	public static boolean saveMortuaryAreaMaster(MortuaryAreaMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean exist=true;
		MortuaryAreaMasterVO mortuaryMstVO=new MortuaryAreaMasterVO();
		try
		{
			HelperMethods.populate(mortuaryMstVO, fb);
			MortuaryAreaMasterDATA.saveMortuaryAreaMaster(mortuaryMstVO,getUserVO(request));
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
	
	public static boolean getDataForModify(MortuaryAreaMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MortuaryAreaMasterVO _MorturyMasterVO = new MortuaryAreaMasterVO();
		Map mp = new HashMap();
		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sAreaCode = concatid[0];
			String shospitalCode = concatid[1];
			String sAreaSlno = concatid[2];
			// putting the selected Record Primary Key into Vo

			fb.setAreaCode(sAreaCode);
			fb.setSlNo(sAreaSlno);

			_MorturyMasterVO.setSlNo(sAreaSlno);
			_MorturyMasterVO.setAreaCode(sAreaCode);
			
			_MorturyMasterVO = MortuaryAreaMasterDATA.getDataForModify(_MorturyMasterVO, userVO);
		    HelperMethods.populate(fb, _MorturyMasterVO);
			fb.setSlNo(sAreaSlno);
			fb.setAreaCode(sAreaCode);
			fb.setHmode(fb.getTempMode());
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
	
	public static boolean saveModMortuaryAreaMaster(MortuaryAreaMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			MortuaryAreaMasterVO _MortuaryMasterVO = new MortuaryAreaMasterVO();

			HelperMethods.populate( _MortuaryMasterVO , fb);
			flag=MortuaryAreaMasterDATA.saveModMortuaryAreaMaster(_MortuaryMasterVO, userVO);
			if(flag){
				objStatus.add(Status.DONE,"","record modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Mortuary Area Name already exists");
				
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
