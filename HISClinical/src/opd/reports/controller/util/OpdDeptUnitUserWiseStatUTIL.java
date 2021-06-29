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

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.reports.controller.data.OpdDeptUnitUserWiseStatDATA;
import opd.reports.controller.fb.OpdDeptUnitUserWiseStatFB;

import registration.RegistrationConfig;

public class OpdDeptUnitUserWiseStatUTIL extends ReportUTIL
{
	public static void getEssential(OpdDeptUnitUserWiseStatFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
	try{	
		setSysdate(request);
		Date dt=OpdDeptUnitUserWiseStatDATA.getSysDateAsDate(); 
		Map mp=	OpdDeptUnitUserWiseStatDATA.getDepartment(ControllerUTIL.getUserVO(request));
		WebUTIL.setMapInSession(mp,request);
		List userList=OpdDeptUnitUserWiseStatDATA.getUser(getUserVO(request));
		WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIALBO_OPTION_USER,userList);
		/*List groupList=OpdDeptUnitUserWiseStatDATA.getAllGroupList(getUserVO(request));
		WebUTIL.getSession(request).setAttribute(OpdConfig.ESSENTIALBO_ALL_GROUP_LIST,groupList);*/
		WebUTIL.getSession(request).setAttribute(RegistrationConfig.SYSADATEOBJECT,dt);
		fb.setChoice(Config.CHOICE_TODAY);	
		fb.setGraphOrText(Config.TEXT);	
		fb.setReportType(Config.RTF);
	}catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
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
	
	
	public static void setUnitBasedOnDepartment(OpdDeptUnitUserWiseStatFB fb,HttpServletRequest request)
	{
		String deptCode=fb.getDepartmentCode();
		Status objStatus= new Status();
	try{	
		List unitList=OpdDeptUnitUserWiseStatDATA.getUnitBasedOnDepartment(deptCode,getUserVO(request));
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
	
	/*public static void getUserBasedOnGroup(OpdDeptUnitUserWiseStatFB fb,HttpServletRequest request)
	{
		String groupCode=fb.getGroupCode();
		Status objStatus= new Status();
	try{	
		List userList=OpdDeptUnitUserWiseStatDATA.getUserBasedOnGroup(groupCode,getUserVO(request));
		WebUTIL.getSession(request).setAttribute(OpdConfig.ESSENTIALBO_ALL_USER_BASED_ON_GROUP,userList);
		objStatus.add(Status.LIST);
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
  }*/
}
