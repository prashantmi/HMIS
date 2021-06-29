package inpatient.masters.controller.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import inpatient.InpatientConfig;
import inpatient.masters.controller.data.PlacentaTypeMstDATA;
import inpatient.masters.controller.fb.PlacentaTypeMasterFB;

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
import hisglobal.vo.PlacentaTypeMasterVO;
import hisglobal.vo.UserVO;

public class PlacentaTypeMstUTL extends ControllerUTIL
{
	
	
	//Saving the Data
	public static boolean savePlacentaTypeMaster(PlacentaTypeMasterFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean exist=true;
		PlacentaTypeMasterVO placentaTypeMasterVO=new PlacentaTypeMasterVO();
		try
		{
			HelperMethods.populate(placentaTypeMasterVO, fb);
			fb.setHmode(fb.getTempMode());
			PlacentaTypeMstDATA.savePlacentaTypeMaster(placentaTypeMasterVO,getUserVO(request));
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
	
	public static boolean getDataForModify(PlacentaTypeMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		PlacentaTypeMasterVO placentaTypeMasterVO = new PlacentaTypeMasterVO();
		Map mp = new HashMap();
		String str = new String();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = fb.getChk()[0].replace("^", "@");
			String[] concatid = chk.split("@");

			String sPlacentaID = concatid[0];
			String shospitalCode = concatid[1];
			String sSlno = concatid[2];
			// putting the selected Record Primary Key into Vo

			fb.setPlacentaTypeId(sPlacentaID);
			fb.setSlNo(sSlno);

			placentaTypeMasterVO.setSlNo(sSlno);
			placentaTypeMasterVO.setPlacentaTypeId(sPlacentaID);
			
			placentaTypeMasterVO = PlacentaTypeMstDATA.getDataForModify(placentaTypeMasterVO, userVO);
		    HelperMethods.populate(fb, placentaTypeMasterVO);
		    fb.setPlacentaTypeId(sPlacentaID);
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
	
	public static boolean saveModDeliveryPlaceMaster(PlacentaTypeMasterFB fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			
			UserVO userVO = getUserVO(_request);
			PlacentaTypeMasterVO placentaTypeMasterVO = new PlacentaTypeMasterVO();

			HelperMethods.populate( placentaTypeMasterVO , fb);
			fb.setHmode(fb.getTempMode());
			flag=PlacentaTypeMstDATA.saveModPlacentaTypeMaster(placentaTypeMasterVO, userVO);
		
			if(flag){
				objStatus.add(Status.DONE,"","Record modified successfully");
				
			}
			else{
				objStatus.add(Status.DONE,"","Placenta Type already exists");
				
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
			objStatus.add(Status.ERROR, "", "Placenta Type already exists");
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
