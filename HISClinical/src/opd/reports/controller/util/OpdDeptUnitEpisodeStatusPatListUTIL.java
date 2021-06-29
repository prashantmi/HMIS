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

import opd.reports.controller.data.OpdDeptUnitEpisodeStatusPatListDATA;
import opd.reports.controller.fb.OpdDeptUnitEpisodeStatusPatListFB;

import registration.RegistrationConfig;

public class OpdDeptUnitEpisodeStatusPatListUTIL extends ReportUTIL
{
	public static void getEssential(OpdDeptUnitEpisodeStatusPatListFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
	try{	
		Map mp=OpdDeptUnitEpisodeStatusPatListDATA.getDepartment(ControllerUTIL.getUserVO(request));
		WebUTIL.setMapInSession(mp, request);
		fb.setChoice(Config.CHOICE_TODAY);	
		fb.setGraphOrText(Config.TEXT);	
		fb.setReportType(Config.RTF);
	}catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.UNSUCESSFULL,"","Data Not Found Error");
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
	
	
	public static void getUnitBasedOnDept(OpdDeptUnitEpisodeStatusPatListFB fb,HttpServletRequest request)
	{
		String deptCode=fb.getDepartmentCode();
		Status objStatus= new Status();
	try{	
		List unitList=OpdDeptUnitEpisodeStatusPatListDATA.getUnitBasedOnDepartment(deptCode,getUserVO(request));
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
