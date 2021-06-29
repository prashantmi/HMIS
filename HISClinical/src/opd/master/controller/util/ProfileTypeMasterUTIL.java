package opd.master.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.ProfileTypeMstVO;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.ProfileTypeMasterDATA;
import opd.master.controller.fb.ProfileTypeMasterFB;

public class ProfileTypeMasterUTIL extends ControllerUTIL{

	public static boolean saveProfileType(ProfileTypeMasterFB _fb, HttpServletRequest _request)
	{
		boolean flag=false;
		Status objStatus = new Status();
		ProfileTypeMstVO profileTypeMasterVO=new ProfileTypeMstVO();
		try
		{
			UserVO userVO = getUserVO(_request);
			HelperMethods.populate(profileTypeMasterVO, _fb);
			ProfileTypeMasterDATA.saveProfileType(profileTypeMasterVO,userVO);
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

public static void getModifyDetail(ProfileTypeMasterFB _fb,HttpServletRequest _request) {
		
		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		
		ProfileTypeMstVO profileTypeMstVO = new ProfileTypeMstVO();
		try
		{
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");
			String profileType = concatid[0];
			String serialNo = concatid[2];
			
			profileTypeMstVO.setProfileType(profileType);
			profileTypeMstVO.setSlNo(serialNo);
			_fb.setProfileType(profileType);
			_fb.setSlNo(serialNo);
			
			profileTypeMstVO = ProfileTypeMasterDATA.getModifyDetail(profileTypeMstVO,userVO);
			HelperMethods.populate(_fb, profileTypeMstVO);
			if(_fb.getHmode().equals("VIEW"))
			{
			if(_fb.getGenerationMode().equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED))
			{
				_fb.setGenerationMode(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED_LABEL);
			}
			else if(_fb.getGenerationMode().equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				_fb.setGenerationMode(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_LABEL);
			}
			else if(_fb.getGenerationMode().equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE))
			{
				_fb.setGenerationMode(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_LABEL);
			}
			if(_fb.getProfileUsablity().equals(OpdConfig.PROFILE_TYPE_USABLITY_OPD))
			{
				_fb.setProfileUsablity(OpdConfig.PROFILE_TYPE_USABLITY_OPD_LABEL);
			}
			else if(_fb.getProfileUsablity().equals(OpdConfig.PROFILE_TYPE_USABLITY_IPD))
			{
				_fb.setProfileUsablity(OpdConfig.PROFILE_TYPE_USABLITY_IPD_LABEL);
			}
			else if(_fb.getProfileUsablity().equals(OpdConfig.PROFILE_TYPE_USABLITY_OPD_AND_IPD))
			{
				_fb.setProfileUsablity(OpdConfig.PROFILE_TYPE_USABLITY_OPD_AND_IPD_LABEL);
			}
			}
			
			objStatus.add(Status.DONE);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
		
	}


	public static void fetchProfileType(ProfileTypeMasterFB _fb,HttpServletRequest _request) {

		Status objStatus = new Status();
		ProfileTypeMstVO profileTypeMstVO=new ProfileTypeMstVO();
		try
		{
			
			String chk=_fb.getChk().replace("^", "#");
			String primaryKey[]=chk.split("#");
			String profileType=primaryKey[0];
			//String profileType=primaryKey[1];
			
			profileTypeMstVO.setProfileType(profileType);
			//_fb.setPatientCategoryCode(patientCategoryCode);
			//_fb.setProfileType(profileType);
			
//			profileTypeMstVO=ProfileTypeMasterDATA.fetchPatientCatModify(profileTypeMstVO,userVO);
			HelperMethods.populate(_fb, profileTypeMstVO);
			
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
	
	public static boolean modifySave(ProfileTypeMasterFB _fb,HttpServletRequest _request) {
		
		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		boolean flag=false;
		ProfileTypeMstVO profileTypeMstVO=new ProfileTypeMstVO();
		try
		{
			//String disasterTypeCode=_fb.getDisasterTypeCode();
			HelperMethods.populate(profileTypeMstVO, _fb);
			String chk=_fb.getChk().replace("^", "#");
			String primaryKey[]=chk.split("#");
			String profileType=primaryKey[0];
			String slNo=primaryKey[2];
			//String profileTypeModify=primaryKey[1];
			
			profileTypeMstVO.setProfileType(profileType);
			profileTypeMstVO.setSlNo(slNo);
			
			//addedby:NehaRajgariya Date:7Sept2016
			String is_unique = ProfileTypeMasterUTIL.getisUniqueDetails(_fb, _request);
			if(is_unique.equals("1"))
			{
				flag=false;
				objStatus.add(Status.DONE,"","This Profile Type is System Data and cannot be modified");
			}
			else
			{
				ProfileTypeMasterDATA.modifySave(profileTypeMstVO,userVO);
				//HelperMethods.populate(_fb, diasasterVO);
				flag=true;
				objStatus.add(Status.DONE);
			}
			
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

	//addedby: NehaRajgariya Date:7Sept2016
	public static String getisUniqueDetails(ProfileTypeMasterFB _fb,HttpServletRequest _request) {
		
		UserVO userVO = getUserVO(_request);
		Status objStatus = new Status();
		String is_unique = null;
		ProfileTypeMstVO profileTypeMstVO = new ProfileTypeMstVO();
		try
		{
			String chk = _fb.getChk().replace("^", "@");
			String[] concatid = chk.split("@");
			String profileType = concatid[0];
			String serialNo = concatid[2];
			
			profileTypeMstVO.setProfileType(profileType);
			profileTypeMstVO.setSlNo(serialNo);
			_fb.setProfileType(profileType);
			_fb.setSlNo(serialNo);
			
			profileTypeMstVO = ProfileTypeMasterDATA.getModifyDetail(profileTypeMstVO,userVO);
			is_unique = profileTypeMstVO.getIsUnique();
			HelperMethods.populate(_fb, profileTypeMstVO);
		}
			
	
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");		
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,e.getMessage(),"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,e.getMessage(),"");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
		
		return is_unique;
	}
	
}
