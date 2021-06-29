package mortuary.masters.controller.hlp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ExternalLabTestMasterVO;
import hisglobal.vo.UserVO;
import javax.servlet.http.HttpServletRequest;

import mortuary.MortuaryConfig;


public class ExternalLabTestMasterUTL extends ControllerUTIL
{
	
	

	
	
	
	// On Add Page saving Values into Database
	public static boolean saveExternalLabTestDetails(ExternalLabTestMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			ExternalLabTestMasterVO _externalLabTestMstVO=new ExternalLabTestMasterVO();
			
			
			HelperMethods.populate(_externalLabTestMstVO, _fb); 

			
				
				
			_fb.setHmode("ADD");
			
			hasFlag=ExternalLabTestMasterDATA.saveExternalLabTestDetails(_externalLabTestMstVO, userVO);
			
			
			if(hasFlag)
				objStatus.add(Status.INPROCESS, "", "Record Added Successfully");
				
			           	
    	}
		catch (HisDuplicateRecordException e)
		{
			//System.out.println("Inside HisDuplicateRecordException");
			objStatus.add(Status.INPROCESS, "", e.getMessage());
			hasFlag = false;
			e.printStackTrace();
		}
		catch (HisRecordNotFoundException e)
		{
			//System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			//System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			hasFlag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			//System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		catch (HisException e)
		{
			//System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			hasFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			//System.out.println("   -----> objStatus in finally  : " + objStatus);
		//	System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}

	// on modify Page for Showing Data of Selected Record
	public static void getExternalLabTestDetails(ExternalLabTestMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		
		//passed as a parmeter
		ExternalLabTestMasterVO _externalLabTestMstVO=new ExternalLabTestMasterVO();

		//received from a function
		ExternalLabTestMasterVO chamberMstVO=new ExternalLabTestMasterVO();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String externalLabTestId = concatid[0];
			String Slno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			_externalLabTestMstVO.setExternalLabTestId(externalLabTestId);
			_externalLabTestMstVO.setSerialNo(Slno);
			
			chamberMstVO=ExternalLabTestMasterDATA.getExternalLabTestDetails(_externalLabTestMstVO, userVO);
			
			HelperMethods.populate(_fb, chamberMstVO);
			_fb.setChk(chk);

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
		
	}

	// for Updating The old Record
	public static boolean updateExternalLabTestDetails(ExternalLabTestMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			//passed as a parmeter
			ExternalLabTestMasterVO _externalLabTestMstVO=new ExternalLabTestMasterVO();

				
				
					
			HelperMethods.populate(_externalLabTestMstVO, _fb);
			
			flag=ExternalLabTestMasterDATA.updateExternalLabTestDetails(_externalLabTestMstVO, userVO);
			if(flag)
			{
				_fb.setHmode("LIST");
				objStatus.add(Status.DONE,"","Record Modified Successfully");
			}
			
			
		}
		catch (HisDuplicateRecordException e)
		{
			_fb.setHmode("MODIFY");
			//System.out.println("Inside HisDuplicateRecordException");
			objStatus.add(Status.DONE, "", e.getMessage());
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
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
		return flag;
	}

	
}
