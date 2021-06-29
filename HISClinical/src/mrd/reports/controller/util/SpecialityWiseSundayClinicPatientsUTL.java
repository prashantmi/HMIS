package mrd.reports.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.reports.controller.data.SpecialityWiseSundayClinicPatientsDATA;
import mrd.reports.controller.fb.SpecialityWiseSundayClinicPatientsFB;


public class SpecialityWiseSundayClinicPatientsUTL extends ReportUTIL
{
	public static void getEssential(SpecialityWiseSundayClinicPatientsFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		try
		{
			setSysdate(_request);
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
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
	
	public static void setUnitBasedOnWeekDay(SpecialityWiseSundayClinicPatientsFB _fb,HttpServletRequest _request)
	{
		
		String  day= _fb.getWeekDay();
		Status objStatus= new Status();
		
		try
		{	
			
			Map mp=SpecialityWiseSundayClinicPatientsDATA.getUnitBasedOnWeekDay(day,getUserVO(_request));
			WebUTIL.setMapInSession(mp,_request);
			//objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
		//	WebUTIL.getSession(_request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
			objStatus.add(Status.UNSUCESSFULL,"","No Units Found");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
		//	WebUTIL.getSession(_request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
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


