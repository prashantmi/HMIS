package opd.reports.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.reports.controller.data.OpdCountOldPatNextVisitDATA;
import opd.reports.controller.fb.OpdCountOldPatNextVisitFB;
import registration.RegistrationConfig;

public class OpdCountOldPatNextVisitUTIL extends ReportUTIL
{
	public static void getEssential(OpdCountOldPatNextVisitFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		try
		{
	//		setSysdate(request);
	//		Date dt=OpdCountOldPatNextVisitDATA.getSysDateAsDate(); 
			Map mp=	OpdCountOldPatNextVisitDATA.getDeptAndCategory(ControllerUTIL.getUserVO(request));
			WebUTIL.setMapInSession(mp,request);
	//		WebUTIL.getSession(request).setAttribute(RegistrationConfig.SYSADATEOBJECT,dt);
	//		fb.setChoice(RegistrationConfig.CHOICE_TODAY);	
			fb.setGraphOrText(Config.TEXT);	
			fb.setReportType(Config.RTF);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"","Record Not Found");
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
			WebUTIL.setStatus(request,objStatus);
		}
	}
	
	public static void setUnitBasedOnDepartment(OpdCountOldPatNextVisitFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
	
   try{
	    String deptCode=fb.getDepartmentCode();
		List unitList=OpdCountOldPatNextVisitDATA.getUnitBasedOnDepartment(deptCode,getUserVO(request));
		WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
	
   }catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.UNSUCESSFULL,"","Unit Not Found");
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
		WebUTIL.setStatus(request,objStatus);
		System.out.println("   -----> objStatus in finally  : "+objStatus);
		System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
	}	
  }
}
