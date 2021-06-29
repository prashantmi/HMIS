package inpatient.reports.controller.util;

import java.util.HashMap;
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

import inpatient.reports.controller.data.DeathDetailsReportDATA;
import inpatient.reports.controller.fb.DeathDetailsReportFB;


public class DeathDetailsReportUTL extends ReportUTIL
{
	public static void getEssential(DeathDetailsReportFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		try
		{
			setSysdate(_request);
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
			
			Map map1=(HashMap)DeathDetailsReportDATA.getAllHospitalEssentials(getUserVO(_request));
			WebUTIL.setMapInSession(map1, _request);
			
			String[] multipleHospitalArray = new String[1];
			multipleHospitalArray[0] = ControllerUTIL.getUserVO(_request).getHospitalCode();
			_fb.setMultipleHospitalCode(multipleHospitalArray);
		
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL,"","Error");
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
		}	
	}
}


