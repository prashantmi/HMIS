package dutyroster.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DutyBlockMstVO;
import hisglobal.vo.UserVO;
import javax.servlet.http.HttpServletRequest;
import dutyroster.masters.controller.data.DutyBlockMstDATA;
import dutyroster.masters.controller.fb.DutyBlockMstFB;

public class DutyBlockMstUTL extends ControllerUTIL
{
	// On Add Page saving Values into Database
	public static boolean saveDutyBlock(DutyBlockMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			DutyBlockMstVO dutyBlockMstVO = new DutyBlockMstVO();
			HelperMethods.populate(dutyBlockMstVO, _fb); 

			hasFlag=DutyBlockMstDATA.saveDutyBlock(dutyBlockMstVO, userVO);
			if(hasFlag){
				objStatus.add(Status.INPROCESS, "", "Record Added Successfully");
				_fb.setHmode("ADD");
			}	
			else{
				objStatus.add(Status.INPROCESS, "", "Block Name Already Exists");
				_fb.setHmode("ADD");
			}	
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
			hasFlag = false;
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			hasFlag = false;
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			hasFlag = false;
		}
		catch (HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			hasFlag = false;
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		return hasFlag;
	}

	// on modify Page for Showing Data of Selected Record
	public static void getDutyBlock(DutyBlockMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		DutyBlockMstVO dutyBlockMstVO = new DutyBlockMstVO();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String dutyBlockId = concatid[0];
			String dutyBlockSlno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			dutyBlockMstVO.setDutyBlockId(dutyBlockId);
			dutyBlockMstVO.setSerialNo(dutyBlockSlno);
			
			dutyBlockMstVO=DutyBlockMstDATA.getDutyBlock(dutyBlockMstVO, userVO);
			
			HelperMethods.populate(_fb, dutyBlockMstVO);
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
	public static void updateDutyBlock(DutyBlockMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			DutyBlockMstVO dutyBlockMstVO = new DutyBlockMstVO();
			
			HelperMethods.populate(dutyBlockMstVO, _fb);
			
			flag=DutyBlockMstDATA.updateDutyBlock(dutyBlockMstVO, userVO);
			if(flag)
			{
				_fb.setHmode("LIST");
				objStatus.add(Status.DONE,"","Record modified successfully");
			}
			else{
				_fb.setHmode("MODIFY");
				objStatus.add(Status.DONE,"","Block Name Already Exists");
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
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			_request.setAttribute(Config.STATUS_OBJECT, objStatus);
			System.out.println("   -----> objStatus in finally  : " + objStatus);
			System.out.println("   -----> objStatus list  : " + objStatus.getStatusList());
		}
		
	}

	
}
