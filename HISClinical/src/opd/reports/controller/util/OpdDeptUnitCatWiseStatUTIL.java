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

import opd.reports.controller.data.OpdDeptUnitCatWiseStatDATA;
import opd.reports.controller.fb.OpdDeptUnitCatWiseStatFB;

import registration.RegistrationConfig;

public class OpdDeptUnitCatWiseStatUTIL extends ReportUTIL
{
	public static void getEssenttial(OpdDeptUnitCatWiseStatFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		setSysdate(_request);
	 try{	
	    	Date dt=OpdDeptUnitCatWiseStatDATA.getSysDateAsDate(); 
		    Map mp=	OpdDeptUnitCatWiseStatDATA.getDeptAndCategory(ControllerUTIL.getUserVO(_request));
		    WebUTIL.setMapInSession(mp,_request);
		    WebUTIL.getSession(_request).setAttribute(RegistrationConfig.SYSADATEOBJECT,dt);
		    _fb.setChoice(Config.CHOICE_TODAY);	
	     	_fb.setGraphOrText(Config.TEXT);	
		    _fb.setReportType(Config.RTF);
	   }
		catch(HisRecordNotFoundException e)
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
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
	}
	
	

	public static void setUnitBasedOnDepartment(OpdDeptUnitCatWiseStatFB _fb,HttpServletRequest _request)
	{
		 Status objStatus= new Status();
      try {		  
		
    	 String deptCode=_fb.getDepartmentCode();
		 List unitList=OpdDeptUnitCatWiseStatDATA.getUnitBasedOnDepartment(deptCode,getUserVO(_request));
		 WebUTIL.getSession(_request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
		 objStatus.add(Status.TRANSINPROCESS);
		 WebUTIL.setStatus(_request,objStatus);
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
		WebUTIL.setStatus(_request,objStatus);
		System.out.println("   -----> objStatus in finally  : "+objStatus);
		System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
	}	
  }
}






