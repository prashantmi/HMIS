package opd.master.controller.util;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.AllergyTypeDATA;
import opd.master.controller.fb.AllergyTypeFB;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisEpisodeOpenInEmergencyException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisRenewalRequiredException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.AllergyTypeVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AllergyTypeUTIL extends ControllerUTIL
{
	
	public static void getTheValues(AllergyTypeFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		Map mp = new HashMap();
		//List listColumn=new ArrayList();
		//List listPrimaryKeyColumn=new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			mp = AllergyTypeDATA.getTheValues(userVO);
			WebUTIL.setMapInSession(mp, _request);
			objStatus.add(Status.NEW);
		}
		catch (HisRenewalRequiredException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisEpisodeOpenInEmergencyException");
			objStatus.add(Status.ERROR_AE, "Renewal Required", "");
		}
		catch (HisEpisodeOpenInEmergencyException e)
		{
			e.printStackTrace();
			System.out.println("Inside HisEpisodeOpenInEmergencyException");
			objStatus.add(Status.ERROR, "Episode Opened in emergency", "");
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
	
	
	public static void getTableData(AllergyTypeFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		List listColumn=new ArrayList();
		
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);
			
			String TableId=_fb.getTableId();
			listColumn=AllergyTypeDATA.getTableData(userVO,TableId);
			if(listColumn!=null){
				WebUTIL.setAttributeInSession(_request, OpdConfig.ALLERGYTYPE_ALL_COLUMN_NAME, listColumn);
			}
			objStatus.add(Status.INPROCESS);
			
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
	}


  public static void saveAllergyType(AllergyTypeFB _fb,HttpServletRequest _request)
	{
		Status objStatus=new Status();
		boolean flag=false;
		try
		{   
			//UserVO userVO = getUserVO(_request);
			AllergyTypeVO allergyTypeVO;
			String sourceFlag=_fb.getSourceFlag();
			
			if(sourceFlag.equals(OpdConfig.ALLERY_TYPE_SOURCE_FLAG_STATIC))
			{
				allergyTypeVO =new AllergyTypeVO();
				allergyTypeVO.setAllergiesType(_fb.getAllergiesType());
				allergyTypeVO.setAllergiesDesc(_fb.getAllergiesDesc());
				allergyTypeVO.setAllergySensitivity(_fb.getAllergySensitivity());
				allergyTypeVO.setSourceFlag(_fb.getSourceFlag());
				allergyTypeVO.setIsValid(_fb.getIsValid());

			}
			else
			{
				allergyTypeVO =new AllergyTypeVO();
				allergyTypeVO.setAllergiesType(_fb.getAllergiesType());
				allergyTypeVO.setAllergiesDesc(_fb.getAllergiesDesc());
				allergyTypeVO.setAllergySensitivity(_fb.getAllergySensitivity());
				allergyTypeVO.setSourceFlag(_fb.getSourceFlag());
				allergyTypeVO.setIsValid(_fb.getIsValid());
				allergyTypeVO.setLongQuery(_fb.getLongQuery());
			}
			
			flag=AllergyTypeDATA.insertAllergyTypeDynamicMode(allergyTypeVO,getUserVO(_request));
			if(flag){
				objStatus.add(Status.DONE,"","Allergy Type Added Successfully");
				
			}
			else{
				objStatus.add(Status.UNSUCESSFULL,"","Allergy Type Already exists");
			}
			_fb.setHmode("NEW");
		}
		catch(HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");
		}
		catch(HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE,"Exception","");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in allergy Type Master UTIL","");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in allergy Type Master UTIL","");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
	}
  
  public static void getPrimaryKey(AllergyTypeFB _fb,HttpServletRequest _request)
  {
	  Status objStatus=new Status();
	  try
	  {   
		  String tableName=_fb.getTableId();
		  List primaryKey=AllergyTypeDATA.getPrimaryKey(tableName);
		  if(primaryKey!=null){
			  WebUTIL.setAttributeInSession(_request, OpdConfig.ALLERGYTYPE_PRIMARY_KEY_LIST, primaryKey);
		  }
		 objStatus.add(Status.INPROCESS);
		  
	  }
	  catch(HisDataAccessException e)
	  {
		  objStatus.add(Status.ERROR_DA,"Data Access Exception","");
	  }
	  catch(HisApplicationExecutionException e)
	  {
		  objStatus.add(Status.ERROR_AE,"Exception","");
	  }
	  catch(HisException e)
	  {
		  e.printStackTrace();
		  objStatus.add(Status.ERROR,"Exception in allergy Type Master UTIL","");
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
		  objStatus.add(Status.ERROR,"Exception in allergy Type Master UTIL","");
	  }
	  finally
	  {
		  WebUTIL.setStatus(_request,objStatus);
	  }
  }
  
  public static void getAllergySensistivity(AllergyTypeFB _fb,HttpServletRequest _request)
  {
	  Status objStatus=new Status();
	  UserVO userVO = getUserVO(_request);
	  try
	  {   
		  List allergySensitivity=AllergyTypeDATA.getAllergySensistivity(userVO);
		  if(allergySensitivity!=null){
			  WebUTIL.setAttributeInSession(_request, OpdConfig.ESSENTIALBO_OPTION_SENSITIVITY, allergySensitivity);
		  }
		 objStatus.add(Status.INPROCESS);
		  
	  }
	  catch(HisDataAccessException e)
	  {
		  objStatus.add(Status.ERROR_DA,"Data Access Exception","");
	  }
	  catch(HisApplicationExecutionException e)
	  {
		  objStatus.add(Status.ERROR_AE,"Exception","");
	  }
	  catch(HisException e)
	  {
		  e.printStackTrace();
		  objStatus.add(Status.ERROR,"Exception in allergy Type Master UTIL","");
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
		  objStatus.add(Status.ERROR,"Exception in allergy Type Master UTIL","");
	  }
	  finally
	  {
		  WebUTIL.setStatus(_request,objStatus);
	  }
  }
	
	
}

