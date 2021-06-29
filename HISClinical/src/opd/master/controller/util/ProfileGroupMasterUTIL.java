package opd.master.controller.util;

import java.util.HashMap;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInsertNotAllowedException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ProfileGroupMasterVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import opd.master.controller.data.ProfileGroupMasterDATA;
import opd.master.controller.fb.ProfileGroupMasterFB;

public class ProfileGroupMasterUTIL extends ControllerUTIL{

	
	public static void fetchProfileGroupEssentials(ProfileGroupMasterFB _fb,HttpServletRequest _rq)
	{
		Status objStatus=new Status();
		Map essentialMap=new HashMap();
		//List<Entry> listProfileType= new ArrayList<Entry>();
		try{
			UserVO userVO= getUserVO( _rq);
			essentialMap=ProfileGroupMasterDATA.fetchProfileGroupEssentials(userVO);
					
			WebUTIL.setMapInSession(essentialMap, _rq);
				objStatus.add(Status.NEW,"","");
		}
		catch(HisRecordNotFoundException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisInsertNotAllowedException e){	
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"",e.getMessage());		
			
		}
		catch(HisDataAccessException e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");			
		}
		catch(HisApplicationExecutionException e){
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"Exception","");
			}		
		catch(Exception e){	
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in DisasterAreaMstUTIL","");
		}
		finally{
			WebUTIL.setStatus(_rq,objStatus);
		}
	}
	
	public static boolean saveProfileGroupDetail(ProfileGroupMasterFB _fb, HttpServletRequest _request)
	{
		boolean flag=false;
		Status objStatus = new Status();
		ProfileGroupMasterVO profileGroupMasterVO=new ProfileGroupMasterVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			HelperMethods.populate(profileGroupMasterVO, _fb);
			ProfileGroupMasterDATA.saveProfileGroupDetail(profileGroupMasterVO,userVO);
			flag=true;
			objStatus.add(Status.DONE,"","Record Saved Successfully");
		}
		catch(HisDuplicateRecordException e){	   		   	
			 System.out.println("Inside HisDuplicateRecordException");
	  		 e.printStackTrace(); 
	  		 flag=false;
	  		 objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
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
		return flag;
	}

	
	public static void fetchProfileGroupDetailModify(ProfileGroupMasterFB _fb,HttpServletRequest _request) {

		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		Map essentialMap=new HashMap();
		//List<Entry> listProfileType= new ArrayList<Entry>();
		ProfileGroupMasterVO profileGroupMasterVO=new ProfileGroupMasterVO();
		
		try
		{
			
			essentialMap=ProfileGroupMasterDATA.fetchProfileGroupEssentials(userVO);
			WebUTIL.setMapInSession(essentialMap, _request);
			
			String chk=_fb.getChk().replace("^", "#");
			String primaryKey[]=chk.split("#");
			String profileGroupId=primaryKey[0];
			
			profileGroupMasterVO.setProfileGroupId(profileGroupId);
			
			profileGroupMasterVO=ProfileGroupMasterDATA.fetchProfileGroupDetailModify(profileGroupMasterVO,userVO);
			HelperMethods.populate(_fb, profileGroupMasterVO);
			objStatus.add(Status.DONE);
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println("Inside HisRecordNotFoundException");
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "",e.getMessage());
			
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
	
	
	/**
	 * modify the old record and insert new record in the database
	 * @param _fb
	 * @param _request
	 */
	
	public static boolean modifySave(ProfileGroupMasterFB _fb,HttpServletRequest _request) {
		
		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		boolean flag=false;
		ProfileGroupMasterVO profileGroupMasterVO=new ProfileGroupMasterVO();
		
		try
		{
			//String disasterTypeCode=_fb.getDisasterTypeCode();
			HelperMethods.populate(profileGroupMasterVO, _fb);
			ProfileGroupMasterDATA.modifySave(profileGroupMasterVO,userVO);
			//HelperMethods.populate(_fb, diasasterVO);
			flag=true;
			objStatus.add(Status.DONE);
		}
		catch(HisDuplicateRecordException e){	   		   	
			 System.out.println("Inside HisDuplicateRecordException");
	  		 e.printStackTrace(); 
	  		 flag=false;
	  		 objStatus.add(Status.UNSUCESSFULL, "", e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			flag=false;
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
			
		}
		catch (HisApplicationExecutionException e)
		{
			flag=false;
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
			
		}
		catch (HisException e)
		{
			flag=false;
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR, "", "Error");
			
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
