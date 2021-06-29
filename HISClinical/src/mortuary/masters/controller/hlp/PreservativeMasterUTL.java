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
import hisglobal.vo.PreservativeMasterVO;
import hisglobal.vo.UserVO;
import javax.servlet.http.HttpServletRequest;

import mortuary.MortuaryConfig;


public class PreservativeMasterUTL extends ControllerUTIL
{
	
	

	
	
	
	// On Add Page saving Values into Database
	public static boolean savePreservativeDetails(PreservativeMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			PreservativeMasterVO _preservativeMasterVO=new PreservativeMasterVO();
			
			
			HelperMethods.populate(_preservativeMasterVO, _fb); 

			
				
				
			_fb.setHmode("ADD");
			
			hasFlag=PreservativeMasterDATA.savePreservativeDetails(_preservativeMasterVO, userVO);
			
			
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
	public static void getPreservativeDetails(PreservativeMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		
		//passed as a parmeter
		PreservativeMasterVO _preservativeMasterVO=new PreservativeMasterVO();

		//received from a function
		PreservativeMasterVO preservativeMasterVO=new PreservativeMasterVO();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String preservativeId = concatid[0];
			String Slno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			_preservativeMasterVO.setPreservativeId(preservativeId);
			_preservativeMasterVO.setSerialNo(Slno);
			
			preservativeMasterVO=PreservativeMasterDATA.getPreservativeDetails(_preservativeMasterVO, userVO);
			
			HelperMethods.populate(_fb, preservativeMasterVO);
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
	public static boolean updatePreservativeDetails(PreservativeMasterFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=false;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			//passed as a parmeter
			PreservativeMasterVO _preservativeMasterVO=new PreservativeMasterVO();

				
				
					
			HelperMethods.populate(_preservativeMasterVO, _fb);
			
			flag=PreservativeMasterDATA.updatePreservativeDetails(_preservativeMasterVO, userVO);
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
