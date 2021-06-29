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
import hisglobal.vo.RosterTypeMstVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.controller.data.RosterTypeMstDATA;
import dutyroster.masters.controller.fb.RosterTypeMstFB;

public class RosterTypeMstUTL extends ControllerUTIL
{
	
	public static void getEssentials(RosterTypeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			RosterTypeMstVO rosterTypeVO = new RosterTypeMstVO();
			HelperMethods.populate(rosterTypeVO, _fb); 

			essentialMap=RosterTypeMstDATA.getRosterTypeEssentials(userVO);
			_fb.setRosterGenerationMethod(DutyRosterConfig.ROSTER_GENERATION_METHOD_MONTHWISE);
			WebUTIL.setMapInSession(essentialMap, _request);
			objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			WebUTIL.setMapInSession(essentialMap, _request);
			System.out.println("Inside HisRecordNotFoundException");
			objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
						
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
	
	
	
	// On Add Page saving Values into Database
	public static boolean  saveRosterType(RosterTypeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = false;
		try
		{
			UserVO userVO = getUserVO(_request);
			RosterTypeMstVO rosterTypeVO = new RosterTypeMstVO();
			HelperMethods.populate(rosterTypeVO, _fb); 

			RosterTypeMstDATA.saveRosterType(rosterTypeVO, userVO);
			
			
			/*if(hasFlag){
				objStatus.add(Status.INPROCESS, "", "Record Added Successfully");
				_fb.setHmode("ADD");
			}	
			else{
				objStatus.add(Status.INPROCESS, "", "Roster Name Already Exists");
				_fb.setHmode("ADD");
			}	*/
			
			_fb.setHmode("ADD");
			hasFlag=true;
			objStatus.add(Status.INPROCESS, "", "Record Added Successfully");
		}
		catch(HisDuplicateRecordException e){	   
			_fb.setHmode("ADD");
				e.printStackTrace(); 
				objStatus.add(Status.INPROCESS,e.getMessage(), "");
				
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
		
		return hasFlag;
	}

	// on modify Page for Showing Data of Selected Record
	
	public static void getRosterType(RosterTypeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RosterTypeMstVO rosterTypeVO = new RosterTypeMstVO();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			// Fetching Selected Record Primary Key
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");

			String rosterTypeId = concatid[0];
			String rosterTypeSlno = concatid[1];
			String sHtcode = concatid[2];
			// putting the selected Record Primary Key into Vo
			
			rosterTypeVO.setRosterTypeId(rosterTypeId);
			rosterTypeVO.setSerialNo(rosterTypeSlno);
			
			rosterTypeVO=RosterTypeMstDATA.getRosterType(rosterTypeVO, userVO);
			
			HelperMethods.populate(_fb, rosterTypeVO);
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
	public static void modifyRosterType(RosterTypeMstFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		boolean hasFlag = false;
		try
		{
			UserVO userVO = getUserVO(_request);
			RosterTypeMstVO rosterTypeVO = new RosterTypeMstVO();
			HelperMethods.populate(rosterTypeVO, _fb); 

			RosterTypeMstDATA.modifyRosterType(rosterTypeVO, userVO);
			
			/*if(hasFlag){
				objStatus.add(Status.INPROCESS, "", "record modified Successfully");
				_fb.setHmode("LIST");
			}	
			else{
				objStatus.add(Status.INPROCESS, "", "Roster Name Already Exists");
				_fb.setHmode("MODIFY");
			}	*/
			
			_fb.setHmode("LIST");
			objStatus.add(Status.INPROCESS, "", "Record Modified Successfully");
		}
		catch(HisDuplicateRecordException e){	
			_fb.setHmode("MODIFY");
			e.printStackTrace(); 
			objStatus.add(Status.INPROCESS,e.getMessage(), "");
			
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
