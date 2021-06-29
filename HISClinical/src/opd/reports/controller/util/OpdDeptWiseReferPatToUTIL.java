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

import javax.servlet.http.HttpServletRequest;

import opd.reports.controller.data.OpdDeptWiseReferPatToDATA;
import opd.reports.controller.fb.OpdDeptWiseReferPatToFB;
import registration.RegistrationConfig;

public class OpdDeptWiseReferPatToUTIL extends ReportUTIL
{
	public static void getEssenttial(OpdDeptWiseReferPatToFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
	try{	
		setSysdate(request);
		Date dt=OpdDeptWiseReferPatToDATA.getSysDateAsDate(); 
		List deptList=OpdDeptWiseReferPatToDATA.getAllDepartment(ControllerUTIL.getUserVO(request));
		WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT,deptList);
		WebUTIL.getSession(request).setAttribute(RegistrationConfig.SYSADATEOBJECT,dt);
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
	
}