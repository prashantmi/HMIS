package dutyroster.masters.controller.utl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import hisglobal.vo.RosterCategoryMstVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.controller.data.RosterCategoryMstDATA;
import dutyroster.masters.controller.fb.RosterCategoryMstFB;

public class RosterCategoryMstUTL extends ControllerUTIL
{
	
	
	// On getting into the add page ,getting the list of roster category
	public static boolean getListOfRosterMainCategory(RosterCategoryMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			List _primaryCatgList=new ArrayList();
			
			UserVO userVO = getUserVO(_request);
			_primaryCatgList=RosterCategoryMstDATA.getListOfRosterMainCategory(userVO);
			WebUTIL.setAttributeInSession(_request, DutyRosterConfig.LIST_OF_ROSTER_MAIN_CATEGORY, _primaryCatgList);
			
			
			objStatus.add(Status.INPROCESS, "", "");
		}
		catch(HisDuplicateRecordException e){	   		   	
			objStatus.add(Status.NEW,"Roster Category Already Exists","");	
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
	
	
	// On Add Page saving Values into Database
	public static boolean saveRosterCategoryInfo(RosterCategoryMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = true;
		try
		{
			UserVO userVO = getUserVO(_request);
			RosterCategoryMstVO _rosterCatMstVO = new RosterCategoryMstVO();
			
if(!_fb.getDutyOffFlag().equals("1"))
			_fb.setDutyOffAccount("");
	
			HelperMethods.populate(_rosterCatMstVO, _fb); // for droping all values of formbean into vo//

			RosterCategoryMstDATA.saveRosterCategoryInfo(_rosterCatMstVO, userVO);

			objStatus.add(Status.INPROCESS, "Record Added Successfully", "");
		}
		catch(HisDuplicateRecordException e){	   		   	
			objStatus.add(Status.NEW,"Roster Category Already Exists","");	
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
	public static boolean fetchRosterCategoryInfo(RosterCategoryMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RosterCategoryMstVO _rosterCatMstVO = new RosterCategoryMstVO();
		RosterCategoryMstVO rostCatMstVO = new RosterCategoryMstVO();
		Map EssentialMap=new HashMap(); 
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String sRosterId = concatid[0];
			String sRosterSlno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			_rosterCatMstVO.setRosterCategoryCode(sRosterId);
			_rosterCatMstVO.setSerialNo(sRosterSlno);
			_rosterCatMstVO.setHospitalCode(sHtcode);
			

			EssentialMap=RosterCategoryMstDATA.fetchRosterCategoryInfo(_rosterCatMstVO, userVO);
			WebUTIL.setMapInSession(EssentialMap,_request);
			
			rostCatMstVO=(RosterCategoryMstVO)EssentialMap.get(DutyRosterConfig.VO_OF_ROSTER_CATEGORY);
			             
			
			HelperMethods.populate(_fb, rostCatMstVO);
			_fb.setChk(chk);
		
			
			if(!_fb.getDutyOffFlag().equals("1"))
				_fb.setDutyOffAccount("");	

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
	public static boolean updateRosterCategoryInfo(RosterCategoryMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean flag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			RosterCategoryMstVO _rosterCatMstVO = new RosterCategoryMstVO();
			
			// Fetching Selected Record Primary Key
			String[] chk = _fb.getChk().split("@");
			String sRosterId = chk[0];
			String sRosterSlno = chk[1];
			String sHtcode = chk[2];
			
			
			if(!_fb.getDutyOffFlag().equals("1"))
				_fb.setDutyOffAccount("");	
			

			HelperMethods.populate(_rosterCatMstVO, _fb);
			RosterCategoryMstDATA.updateRosterCategoryInfo(sRosterId,sRosterSlno,_rosterCatMstVO, userVO);
			
			objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisDuplicateRecordException e){	   		   	
			objStatus.add(Status.TRANSINPROCESS,"Roster Category Already Exists","");	
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
