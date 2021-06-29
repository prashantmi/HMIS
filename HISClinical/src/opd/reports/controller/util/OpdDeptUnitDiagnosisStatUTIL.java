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

import opd.OpdConfig;
import opd.reports.controller.data.OpdDeptUnitDiagnosisStatDATA;
import opd.reports.controller.fb.OpdDeptUnitDiagnosisStatFB;

import registration.RegistrationConfig;

public class OpdDeptUnitDiagnosisStatUTIL extends ReportUTIL
{
	public static void getEssential(OpdDeptUnitDiagnosisStatFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
	 try{	
		Map mp=OpdDeptUnitDiagnosisStatDATA.getDepartment(ControllerUTIL.getUserVO(request));
		WebUTIL.setMapInSession(mp, request);
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
	
	
	public static void getUnitAndIcdCode(OpdDeptUnitDiagnosisStatFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		List unitList=null;
		List icdCodeList=null;
	 try{
	    
		String deptCode=fb.getDepartmentCode();
		unitList=OpdDeptUnitDiagnosisStatDATA.getUnitBasedOnDepartment(deptCode,getUserVO(request));
		WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
		icdCodeList=OpdDeptUnitDiagnosisStatDATA.getIcdCode(deptCode,getUserVO(request));
		WebUTIL.getSession(request).setAttribute(OpdConfig.ESSENTIAL_BO_OPTION_ICD_CODE_BASED_ON_DEPT,icdCodeList);
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
	}catch(HisRecordNotFoundException e)
	{
		if(unitList==null)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"","Unit Not Found ");
		}
		else
		{
			if(icdCodeList==null)
			{
				e.printStackTrace();
				objStatus.add(Status.UNSUCESSFULL,"","ICD Code Not Found ");
			}
		}
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
