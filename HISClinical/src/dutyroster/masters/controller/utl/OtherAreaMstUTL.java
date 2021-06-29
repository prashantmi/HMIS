package dutyroster.masters.controller.utl;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisEpisodeOpenInEmergencyException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.DutyRosterOtherAreaMstVO; 
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest; 

import dutyroster.masters.controller.data.OtherAreaMstDATA;
import dutyroster.masters.controller.fb.OtherAreaMstFB;

public class OtherAreaMstUTL extends ControllerUTIL
{
	// On Add Page saving Values into Database
	public static boolean saveOtherAreaInfo(OtherAreaMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			DutyRosterOtherAreaMstVO _otherAreaMstVO = new DutyRosterOtherAreaMstVO();

			HelperMethods.populate(_otherAreaMstVO, _fb); // for droping all values of formbean into vo//

			OtherAreaMstDATA.saveOtherAreaInfo(_otherAreaMstVO, userVO);

			objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
		}
		catch(HisDuplicateRecordException e){	   		   	
			objStatus.add(Status.NEW,e.getMessage(),"");	
	  		 e.printStackTrace(); 
	  		
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
	public static boolean fetchRosterCategoryInfo(OtherAreaMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		DutyRosterOtherAreaMstVO _otherAreaMstVO = new DutyRosterOtherAreaMstVO();
		DutyRosterOtherAreaMstVO rostCatMstVO = new DutyRosterOtherAreaMstVO();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String otherAreaCode = concatid[0];
			String otherAreaSlno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			_otherAreaMstVO.setOtherAreaCode(otherAreaCode);
			_otherAreaMstVO.setSerialNo(otherAreaSlno);
			_otherAreaMstVO.setHospitalCode(sHtcode);
			

			rostCatMstVO=OtherAreaMstDATA.fetchRosterCategoryInfo(_otherAreaMstVO, userVO);
			
			
             
			
			HelperMethods.populate(_fb, rostCatMstVO);
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
		return true;
	}

	// for Updating The old Record
	public static boolean updateRosterCategoryInfo(OtherAreaMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			DutyRosterOtherAreaMstVO _otherAreaMstVO = new DutyRosterOtherAreaMstVO();
			
			// Fetching Selected Record Primary Key
			String[] chk = _fb.getChk().split("@");
			String sRosterId = chk[0];
			String sRosterSlno = chk[1];
			String sHtcode = chk[2];
			
			
			
			

			HelperMethods.populate(_otherAreaMstVO, _fb);
			OtherAreaMstDATA.updateRosterCategoryInfo(sRosterId,sRosterSlno,_otherAreaMstVO, userVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDuplicateRecordException e){	   		   	
			objStatus.add(Status.TRANSINPROCESS,e.getMessage(),"");	
	  		 e.printStackTrace(); 
	  		 flag=false;
	  		 _fb.setHmode("MODIFY");
	  		
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
