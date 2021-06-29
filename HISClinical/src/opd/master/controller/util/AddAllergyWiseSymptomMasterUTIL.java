package opd.master.controller.util;

/**
 * @author  CDAC
 */

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.master.controller.data.AddAlleryWiseSymptomMasterDATA;

import opd.master.controller.fb.AllergyWiseSymptomMasterFB;


import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.AllergyWiseSymptomMasterVO;

import hisglobal.vo.UserVO;

public class AddAllergyWiseSymptomMasterUTIL extends ControllerUTIL 
{
	//* Setting Essentials for Adding into User Desk Menu Master
	public static void getAllergyTypeNotInAllergyWiseSymptom(AllergyWiseSymptomMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();		
		Map mp=new HashMap();
		try
		{
			UserVO userVO = getUserVO(_request);
			mp=AddAlleryWiseSymptomMasterDATA.getAllergyTypeNotInAllergyWiseSymptom(userVO);
			WebUTIL.setMapInSession(mp,_request);
			if(_fb.getHmode().equals("SAVE"))
			{
				objStatus.add(Status.DONE,"Record Saved Successfully","");
			}
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
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			_request.setAttribute(Config.STATUS_OBJECT,objStatus);
		}	
	}
	
	
	
	public static boolean addAllergyTypeAgainstSymptom(AllergyWiseSymptomMasterFB _fb,HttpServletRequest _request)
	{
		Status  objStatus=new Status();
		boolean saveFlag=true;
		try
		{
			UserVO userVO = getUserVO(_request);
			AllergyWiseSymptomMasterVO[] voAllergyWiseSymptom;
			voAllergyWiseSymptom=new AllergyWiseSymptomMasterVO[_fb.getSelectedSymptoms().length];
			
			int countSymptoms=_fb.getSelectedSymptoms().length;
			for(int i=0;i<countSymptoms;i++)
			{
				voAllergyWiseSymptom[i] =new AllergyWiseSymptomMasterVO();
				voAllergyWiseSymptom[i].setSymptomCode(_fb.getSelectedSymptoms()[i]);
				voAllergyWiseSymptom[i].setAllergyTypeCode(_fb.getAllergyTypeId());
				voAllergyWiseSymptom[i].setIsValid(Config.IS_VALID_ACTIVE);
				
				
				
			}
			AddAlleryWiseSymptomMasterDATA.addAllergyTypeAgainstSymptom(voAllergyWiseSymptom,userVO);
			
			objStatus.add(Status.DONE,"Record Saved Successfully","");
		}	
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			saveFlag=false;
		}
		catch(HisApplicationExecutionException e)
		{		
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			saveFlag=false;
		}
		catch(HisException e)
		{
			objStatus.add(Status.ERROR,"","Error");
			saveFlag=false;
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}	
		return saveFlag;
	}
	
	
	
	
	
	}
