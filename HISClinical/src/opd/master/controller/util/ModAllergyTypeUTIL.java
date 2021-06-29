package opd.master.controller.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.master.controller.data.AllergyTypeDATA;
import opd.master.controller.data.ModAllergyTypeDATA;
import opd.master.controller.fb.ModAllergyTypeFB;
import registration.RegistrationConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.AllergyTypeVO;
import hisglobal.vo.UserVO;

public class ModAllergyTypeUTIL extends ControllerUTIL
{
	public static void getAllergyType(ModAllergyTypeFB _fb,HttpServletRequest _request)
	{
		String allergyCode=_fb.getChk().substring(0,_fb.getChk().lastIndexOf("^"));
		//_fb.setAllergiesCode(_fb.getChk().substring(0,_fb.getChk().lastIndexOf("^")));
		Status objStatus=new Status();
		AllergyTypeVO allergyTypeVO=new AllergyTypeVO();
		
		try
		{
			allergyTypeVO=ModAllergyTypeDATA.getAllergyType(allergyCode,getUserVO(_request));
			HelperMethods.populate(_fb,allergyTypeVO);
			//String sensitivity= ModAllergyTypeDATA.getAllergySensitivity(_fb.getAllergySensitivity(),getUserVO(_request));
			//_fb.setAllergySensitivity(sensitivity);
			String mode="";
			if(!(_request.getParameter("hmode")==null))
			
				 mode=_request.getParameter("hmode");
			else
				mode=(String)(WebUTIL.getSession(_request).getAttribute("RegistrationConfig.VIEWORMODIFY"));
			
			if(mode.equalsIgnoreCase("view"))
			{
				WebUTIL.setAttributeInSession(_request,RegistrationConfig.VIEWORMODIFY,"view");			
			}
			else{
				WebUTIL.setAttributeInSession(_request,RegistrationConfig.VIEWORMODIFY,"modify");
			}
		}
		catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");
		}
		catch(HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE,"Exception","");
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
	
	public static void updateSave(ModAllergyTypeFB _fb, HttpServletRequest _request)
	{
		String allergyCode=_fb.getChk().substring(0,_fb.getChk().lastIndexOf("^"));
		Status objStatus=new Status();
		AllergyTypeVO _allergyTypeVO= new AllergyTypeVO();
		boolean flag=false;
		
		try
		{
			HelperMethods.populate(_allergyTypeVO,_fb);
			_allergyTypeVO.setAllergiesCode(allergyCode);
			flag=ModAllergyTypeDATA.updateAllergyType(_allergyTypeVO,getUserVO(_request));
			if(flag){
				objStatus.add(Status.DONE,"","Allergy Type Modified Successfully");
				_fb.setHmode("LIST");
			}
			else{
				objStatus.add(Status.DONE,"","Allergy Type Already Exists");
				_fb.setHmode("NEW");
			}
				
		}
		catch(HisUpdateUnsuccesfullException e)
		{
			objStatus.add(Status.ERROR_DA,e.getMessage(),"");
		}
		catch(HisDataAccessException e)
		{
			objStatus.add(Status.ERROR_DA,"Data Access Exception","");
		}
		catch(HisApplicationExecutionException e)
		{
			objStatus.add(Status.ERROR_AE,"Exception","");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"Exception in  Modify Allergy Type Master UTIL","");
		}
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
		}
		
	}
	  public static void getAllergySensistivity(ModAllergyTypeFB _fb,HttpServletRequest _request)
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
