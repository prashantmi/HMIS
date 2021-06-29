package dutyroster.masters.controller.utl;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DutyRoleMstVO;
import hisglobal.vo.UserVO;
import javax.servlet.http.HttpServletRequest;
import dutyroster.masters.controller.data.DutyRoleMstDATA;
import dutyroster.masters.controller.fb.DutyRoleMstFB;

public class DutyRoleMstUTL extends ControllerUTIL
{
	// On Add Page saving Values into Database
	public static boolean saveDutyRole(DutyRoleMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			DutyRoleMstVO dutyRoleMstVO = new DutyRoleMstVO();
			HelperMethods.populate(dutyRoleMstVO, _fb); 

			_fb.setHmode("ADD");
			
			hasFlag=DutyRoleMstDATA.saveDutyRole(dutyRoleMstVO, userVO);
			if(hasFlag){
				objStatus.add(Status.INPROCESS, "", "Record Added Successfully");
				
			}	
			/*else{
				objStatus.add(Status.INPROCESS, "", "Role Name Already Exists");
				_fb.setHmode("ADD");
			}	*/
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
	public static void getDutyRole(DutyRoleMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		DutyRoleMstVO dutyRoleMstVO = new DutyRoleMstVO();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String dutyRoleId = concatid[0];
			String dutyRoleSlno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			dutyRoleMstVO.setDutyRoleId(dutyRoleId);
			dutyRoleMstVO.setSerialNo(dutyRoleSlno);
			
			dutyRoleMstVO=DutyRoleMstDATA.getDutyRole(dutyRoleMstVO, userVO);
			
			HelperMethods.populate(_fb, dutyRoleMstVO);
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
	public static void updateDutyRole(DutyRoleMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			DutyRoleMstVO dutyRoleMstVO = new DutyRoleMstVO();
			
			HelperMethods.populate(dutyRoleMstVO, _fb);
			
			flag=DutyRoleMstDATA.updateDutyRole(dutyRoleMstVO, userVO);
			if(flag)
			{
				_fb.setHmode("LIST");
				objStatus.add(Status.DONE,"","Record Modified Successfully");
			}
			/*else{
				_fb.setHmode("MODIFY");
				objStatus.add(Status.DONE,"","Role Name Already Exists");
			}*/
			
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
		
	}

	
}
