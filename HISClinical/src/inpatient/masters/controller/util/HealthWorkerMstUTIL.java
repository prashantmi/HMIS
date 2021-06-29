package inpatient.masters.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import inpatient.InpatientConfig;
import inpatient.masters.controller.data.HealthWorkerMstDATA;
import inpatient.masters.controller.fb.HealthWorkerMasterFB;

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
import hisglobal.vo.HealthWorkerMasterVO;
import hisglobal.vo.UserVO;

public class HealthWorkerMstUTIL extends ControllerUTIL
{
	
	
	//Saving the Data
	public static boolean saveHealthWorkerMaster(HealthWorkerMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean exist=true;
		HealthWorkerMasterVO healthWorkerMasterVO = new HealthWorkerMasterVO();
		try
		{
			HelperMethods.populate(healthWorkerMasterVO, fb);
			fb.setHmode(fb.getTempMode());
			HealthWorkerMstDATA.saveHealthWorkerMaster(healthWorkerMasterVO,getUserVO(request));
			exist=false;
			
			objStatus.add(Status.DONE,"","Record Added Successfully");
			
		}
		catch (HisDuplicateRecordException e)
		{
			e.printStackTrace();
			fb.setHmode(fb.getTempMode());
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
	
	public static boolean getDataForModify(HealthWorkerMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		HealthWorkerMasterVO _HealthWorkerMasterVO = new HealthWorkerMasterVO();
		Map mp = new HashMap();
		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sHealthWorkerID = concatid[0];
			String shospitalCode = concatid[1];
			String sSlno = concatid[2];
			// putting the selected Record Primary Key into Vo

			fb.setHealthWorkerID(sHealthWorkerID);
			fb.setSlNo(sSlno);

			_HealthWorkerMasterVO.setSlNo(sSlno);
			_HealthWorkerMasterVO.setHealthWorkerID(sHealthWorkerID);
			
			_HealthWorkerMasterVO = HealthWorkerMstDATA.getDataForModify(_HealthWorkerMasterVO, userVO);
		    HelperMethods.populate(fb, _HealthWorkerMasterVO);
		    fb.setHealthWorkerID(sHealthWorkerID);
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
	
	public static boolean saveModHealthWorkerMaster(HealthWorkerMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			HealthWorkerMasterVO _HealthWorkerMasterVO = new HealthWorkerMasterVO();

			HelperMethods.populate(_HealthWorkerMasterVO , fb);
			fb.setHmode(fb.getTempMode());
			flag=HealthWorkerMstDATA.saveModHealthWorkerMaster(_HealthWorkerMasterVO, userVO);
		
			if(flag){
				objStatus.add(Status.DONE,"","Record modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Health Worker already exists");
				
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
			objStatus.add(Status.ERROR, "", "Health Worker already exists");
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
