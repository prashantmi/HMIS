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
import hisglobal.vo.MortuaryRoleMasterVO;
import hisglobal.vo.UserVO;

public class RoleMasterUTL extends ControllerUTIL
{
	
	
	//Saving the Data
	public static boolean saveRoleMaster(RoleMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean exist=true;
		MortuaryRoleMasterVO MortuaryRoleMasterVO=new MortuaryRoleMasterVO();
		try
		{
			HelperMethods.populate(MortuaryRoleMasterVO, fb);
			RoleMasterDATA.saveRoleMaster(MortuaryRoleMasterVO,getUserVO(request));
			exist=false;
			fb.setHmode(fb.getTempMode());
			objStatus.add(Status.DONE,"","Record Added Successfully");
			
		}
		catch (HisDuplicateRecordException e)
		{
			e.printStackTrace();
			exist=true;
			fb.setHmode(fb.getTempMode());
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
	
	public static boolean getDataForModify(RoleMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		MortuaryRoleMasterVO _MortuaryRoleMasterVO = new MortuaryRoleMasterVO();
		Map mp = new HashMap();
		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sRoleID = concatid[0];
			String shospitalCode = concatid[1];
			String sSlno = concatid[2];
			// putting the selected Record Primary Key into Vo

			fb.setRoleID(sRoleID);
			fb.setSlNo(sSlno);

			_MortuaryRoleMasterVO.setSlNo(sSlno);
			_MortuaryRoleMasterVO.setRoleID(sRoleID);
			
			_MortuaryRoleMasterVO = RoleMasterDATA.getDataForModify(_MortuaryRoleMasterVO, userVO);
		    HelperMethods.populate(fb, _MortuaryRoleMasterVO);
		    fb.setRoleID(sRoleID);
			fb.setSlNo(sSlno);
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
	
	public static boolean saveModRoleMaster(RoleMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			MortuaryRoleMasterVO _MortuaryRoleMasterVO = new MortuaryRoleMasterVO();

			HelperMethods.populate( _MortuaryRoleMasterVO , fb);
			flag=RoleMasterDATA.saveModRoleMaster(_MortuaryRoleMasterVO, userVO);
			if(flag){
				objStatus.add(Status.DONE,"","record modified successfully");
			
			}
			else{
				objStatus.add(Status.DONE,"","Role Name already exists");
				
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
			objStatus.add(Status.ERROR, "", "Role Name already exists");
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
