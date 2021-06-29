package inpatient.reports.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.InpatientConfig;
import inpatient.reports.controller.data.JSYRegistrationReportDATA;
import inpatient.reports.controller.fb.JSYRegistrationReportFB;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class JSYRegistrationReportUTL extends ReportUTIL
{

	public static void getEssential(JSYRegistrationReportFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		//HttpSession session=request.getSession();
		//List listWard = new ArrayList();
	try{	
		setSysdate(request);
		Date dt=JSYRegistrationReportDATA.getSysDateAsDate();
		
		WebUTIL.getSession(request).setAttribute(InpatientConfig.SYSADATEOBJECT,dt);
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
	
}
