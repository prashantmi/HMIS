package opd.reports.controller.util;

import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;
import opd.reports.controller.data.OpdDeptUnitDoctorWiseDATA;
import opd.reports.controller.data.OpdDeptUnitUserWiseStatDATA;
import opd.reports.controller.fb.OpdDeptUnitDoctorWiseFB;
import registration.RegistrationConfig;

public class OpdDeptUnitDoctorWiseUTIL extends ReportUTIL
{
	public static void getEssential(OpdDeptUnitDoctorWiseFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
	try{
		Map mp=OpdDeptUnitDoctorWiseDATA.getDepartment(ControllerUTIL.getUserVO(request));
		WebUTIL.setMapInSession(mp, request);
		List userList=OpdDeptUnitUserWiseStatDATA.getUser(getUserVO(request));
		WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIALBO_OPTION_USER,userList);
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
	
	
	public static void getUnitBasedOnDept(OpdDeptUnitDoctorWiseFB fb,HttpServletRequest request)
	{
		
		String deptCode=fb.getDepartmentCode();
		Status objStatus= new Status();
	 try{	
		List unitList=OpdDeptUnitDoctorWiseDATA.getUnitBasedOnDepartment(deptCode,getUserVO(request));
		WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);

	}catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.UNSUCESSFULL,"","Unit Not Found ");
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
	
	public static void getDoctorNameBasedOnUnit(OpdDeptUnitDoctorWiseFB fb,HttpServletRequest request)
	{
		String unitCode=fb.getUnit();
		Status objStatus=new Status();
	try{	
		List doctorName=OpdDeptUnitDoctorWiseDATA.getDoctorNameBasedOnUnit(unitCode,getUserVO(request));
		WebUTIL.getSession(request).setAttribute(OpdConfig.ESSENTIAL_BO_OPTION_DOCTOR_NAME_BASED_ON_UNIT,doctorName);
		objStatus.add(Status.TRANSINPROCESS);
		objStatus.add(Status.INPROCESS);
		WebUTIL.setStatus(request, objStatus);
		fb.setIsDoctorFound("Doctor found");
	}catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.TRANSINPROCESS);
		objStatus.add(Status.UNSUCESSFULL,"","No Doctor Found");
		fb.setIsDoctorFound("No doctor found");
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
