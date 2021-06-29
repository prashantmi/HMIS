package mrd.reports.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.reports.controller.data.GenderWiseOutdoorPatientsDATA;
import mrd.reports.controller.fb.GenderWiseOutdoorPatientsFB;


public class GenderWiseOutdoorPatientsUTL extends ReportUTIL
{
	public static void getEssential(GenderWiseOutdoorPatientsFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		try
		{
				
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
			_fb.setChoice(Config.CHOICE_DATE_WISE);
			Map mp=GenderWiseOutdoorPatientsDATA.getGenderWiseOutdoorPatientReportEssentials(ControllerUTIL.getUserVO(_request));  
			WebUTIL.setMapInSession(mp,_request);
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


