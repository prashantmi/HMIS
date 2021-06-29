package mrd.reports.controller.util;

/**
 * @author Pawan Kumar B N
 * Creation Date 09-Jul-2012
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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.reports.controller.data.RegistrationPatientListingReportDATA;
import mrd.reports.controller.fb.RegistrationPatientListingReportFB;


public class RegistrationPatientListingReportUTL extends ReportUTIL
{
	public static void getEssential(RegistrationPatientListingReportFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		try
		{
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
			_fb.setChoice(Config.CHOICE_DATE_WISE);
			
			Map mp=RegistrationPatientListingReportDATA.getRegistrationPatientListingReportEssentials(ControllerUTIL.getUserVO(_request));
			_fb.setHospitalCode(ControllerUTIL.getUserVO(_request).getHospitalCode());
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


