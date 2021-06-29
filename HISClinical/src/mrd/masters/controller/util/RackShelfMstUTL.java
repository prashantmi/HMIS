package mrd.masters.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.RackShelfMstVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.masters.controller.data.RackShelfMstDATA;
import mrd.masters.controller.fb.RackShelfMstFB;

public class RackShelfMstUTL extends ControllerUTIL
{
	
	public static void getEssentials(RackShelfMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			_fb.setRackId(_fb.getControls()[0]);
			String rackName=RackShelfMstDATA.getRackNameByRackId(_fb.getRackId(),userVO);
			_fb.setRackName(rackName);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
			WebUTIL.setMapInSession(essentialMap, _request);
						
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

	public static boolean saveRackShelf(RackShelfMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RackShelfMstVO rackShelfVO=new RackShelfMstVO();
		boolean flag=true;
		
		try
		{
			UserVO userVO = getUserVO(_request);
			HelperMethods.populate(rackShelfVO, _fb);
			_fb.setRackId(rackShelfVO.getRackId());
			flag=RackShelfMstDATA.saveRackShelf(rackShelfVO, userVO);
					
			if(flag)
				objStatus.add(Status.DONE,"","Record Saved Successfully");
			else{
				objStatus.add(Status.DONE,"","Shelf Name Already Exists");
			}
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
			//_fb.setRackId(rackShelfVO.getRackId());
			//System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
		return flag;
	}
	
		
	public static void getRackShelfDetail(RackShelfMstFB _fb,HttpServletRequest _request) {
		Status objStatus = new Status();
		String primaryKey=_fb.getChk()[0];
		primaryKey=primaryKey.replace("^", "@");
		//String rackId=primaryKey.split("@")[0];
		//Map essentialMap=new HashMap();
		RackShelfMstVO rackShelfVO=new RackShelfMstVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			
			rackShelfVO.setRackShelfId(primaryKey.split("@")[0]);
			rackShelfVO.setSerialNo(primaryKey.split("@")[2]);
			
			rackShelfVO=RackShelfMstDATA.getRackShelfDetail(rackShelfVO,userVO);
			HelperMethods.populate(_fb, rackShelfVO);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			//WebUTIL.setMapInSession(essentialMap, _request);
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());
							
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
	
	
	public static boolean modifyRackShelf(RackShelfMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RackShelfMstVO rackShelfVO=new RackShelfMstVO();
		boolean flag=false;
		try
		{
			UserVO userVO = getUserVO(_request);
			HelperMethods.populate(rackShelfVO, _fb);
			
			flag=RackShelfMstDATA.modifyRackShelf(rackShelfVO, userVO);
			if(!flag)
				objStatus.add(Status.DONE,"","Shelf Name Already Exists");
			else{
				objStatus.add(Status.DONE,"","Record Updated Successfully");
			}
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
			///System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return flag;
	}

}
		

