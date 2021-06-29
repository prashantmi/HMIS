package inpatient.masters.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import inpatient.InpatientConfig;
import inpatient.masters.controller.data.AbortionMethodMstDATA;
import inpatient.masters.controller.fb.AbortionMethodMasterFB;

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
import hisglobal.vo.AbortionMethodMasterVO;
import hisglobal.vo.UserVO;

public class AbortionMethodMasterUTL extends ControllerUTIL
{
	//Getting Essential Data 
	public static void getAbortionTypeName(AbortionMethodMasterFB fb,HttpServletRequest request)
	{
		Status objStatus=new Status();
		Map mp=new HashMap();
		UserVO userVO = getUserVO(request);
		String typeName="";
	
		try
		{
			setSysdate(request);
				
			typeName=AbortionMethodMstDATA.getAbortionTypeName(fb.getAbortionTypeId(),userVO);
			fb.setAbortionTypeName(typeName);
		
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

	
	//Saving the Data
	public static boolean saveAbortionMethodMaster(AbortionMethodMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean exist=true;
		AbortionMethodMasterVO abortionMethodMasterVO=new AbortionMethodMasterVO();
		try
		{
			HelperMethods.populate(abortionMethodMasterVO, fb);
			fb.setHmode(fb.getTempMode());
			AbortionMethodMstDATA.saveAbortionMethodMaster(abortionMethodMasterVO,getUserVO(request));
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
	
	public static boolean getDataForModify(AbortionMethodMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		AbortionMethodMasterVO _AbortionMethodMasterVO = new AbortionMethodMasterVO();
		Map mp = new HashMap();
		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sMethodCode = concatid[0];
			String shospitalCode = concatid[1];
			String sSlno = concatid[2];
			// putting the selected Record Primary Key into Vo

			fb.setAbortionMethodId(sMethodCode);
			fb.setSlNo(sSlno);

			_AbortionMethodMasterVO.setSlNo(sSlno);
			_AbortionMethodMasterVO.setAbortionMethodId(sMethodCode);
			
			_AbortionMethodMasterVO = AbortionMethodMstDATA.getDataForModify(_AbortionMethodMasterVO, userVO);
		    HelperMethods.populate(fb, _AbortionMethodMasterVO);
			fb.setSlNo(sSlno);
			fb.setAbortionMethodId(sMethodCode);
			
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
	
	public static boolean saveModAbortionMethodMaster(AbortionMethodMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			AbortionMethodMasterVO abortionMethodMasterVO = new AbortionMethodMasterVO();

			HelperMethods.populate( abortionMethodMasterVO , fb);
			fb.setHmode(fb.getTempMode());
			flag=AbortionMethodMstDATA.saveModAbortionMethodMaster(abortionMethodMasterVO, userVO);
			if(flag){
				objStatus.add(Status.DONE,"","record modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Abortion method already exists");
				
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
