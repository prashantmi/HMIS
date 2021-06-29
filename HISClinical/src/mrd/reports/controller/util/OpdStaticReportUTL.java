package mrd.reports.controller.util;

/**
 * @author Adil Wasi
 * Creation Date 27-Jul-2012
 */
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.reports.controller.data.OpdStaticReportDATA;
import mrd.reports.controller.fb.OpdStaticReportFB;
import opd.OpdConfig;
import registration.RegistrationConfig;


public class OpdStaticReportUTL extends ReportUTIL
{
	public static void getEssential(OpdStaticReportFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		UserVO userVO=new UserVO();
	 try{	
		
		 Map map1=(HashMap)OpdStaticReportDATA.getAllHospitalEssentials(getUserVO(request));
			WebUTIL.setMapInSession(map1, request);
			
		Map map=OpdStaticReportDATA.getRegAndPatCategory(ControllerUTIL.getUserVO(request));
		WebUTIL.setMapInSession(map, request);
		userVO.setHospitalCode("100");
		Map mp=OpdStaticReportDATA.getDepartment(userVO);
		WebUTIL.setMapInSession(mp, request);
		
		fb.setChoice(Config.CHOICE_TODAY);	
		fb.setGraphOrText(Config.TEXT);	
		fb.setReportType(Config.RTF);
		fb.setView(OpdConfig.VIEW_REGISTRATION_CATEGORY);
		
		
				
	}catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		objStatus.add(Status.UNSUCESSFULL,"","Data Not Found ");
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
	
	
	public static void getUnitBasedOnDept(OpdStaticReportFB fb,HttpServletRequest request)
	{
		String deptCode=fb.getDepartmentCode();
		Status objStatus= new Status();
	try{	
		List unitList=OpdStaticReportDATA.getUnitBasedOnDepartment(deptCode,getUserVO(request));
		WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
		//objStatus.add(Status.TRANSINPROCESS);
		//WebUTIL.setStatus(request,objStatus);
	}catch(HisRecordNotFoundException e)
	{
		e.printStackTrace();
		//objStatus.add(Status.UNSUCESSFULL,"","Unit Not Found ");
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


