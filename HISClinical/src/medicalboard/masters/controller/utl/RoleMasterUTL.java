package medicalboard.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.RoleMasterVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import medicalboard.masters.controller.data.RoleMasterDATA;
import medicalboard.masters.controller.fb.RoleMasterFB;


public class RoleMasterUTL extends ControllerUTIL{

	public static boolean saveRoleDtl(RoleMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		RoleMasterVO  roleMasterVO=new RoleMasterVO();
    	 boolean hasFlag=true;
		try
		{
			HelperMethods.populate(roleMasterVO, fb);
			
			RoleMasterDATA.saveRoleDtl(roleMasterVO,getUserVO(request));
			
			objStatus.add(Status.DONE,"","Record Added Successfully");
		}
		catch (HisDuplicateRecordException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisDataAccessException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{   hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		catch (Exception e)
		{  hasFlag=false;
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return hasFlag;
	}
	
	
	public static boolean getRoleDtl(RoleMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RoleMasterVO  roleMasterVO=new RoleMasterVO();
//		Map mp = new HashMap();
//		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sRoleId = concatid[0];
			String sSlno = concatid[1];
			// putting the selected Record Primary Key into Vo

			fb.setRoleID(sRoleId);
			fb.setSlNo(sSlno);

			roleMasterVO.setSlNo(sSlno);
			roleMasterVO.setRoleID(sRoleId);
			
			roleMasterVO = RoleMasterDATA.getRoleDtl(roleMasterVO, userVO);
		    
			HelperMethods.populate(fb,roleMasterVO);
			
			//objStatus.add(Status.NEW);
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
	
	public static boolean saveModRoleDtl(RoleMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=true;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			RoleMasterVO  roleMasterVO=new RoleMasterVO();
			HelperMethods.populate( roleMasterVO , fb);
		
			RoleMasterDATA.saveModRoleDtl(roleMasterVO,userVO);
			
			objStatus.add(Status.DONE,"","record modified successfully");
		}
		catch (HisDuplicateRecordException e)
		{  flag=false;
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch (HisRecordNotFoundException e)
		{  flag=false;
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{   flag=false;
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{   flag=false;
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{   flag=false;
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Abortion Method already exists");
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
