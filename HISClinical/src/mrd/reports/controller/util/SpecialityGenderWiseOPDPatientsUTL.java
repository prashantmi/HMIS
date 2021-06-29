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

import mrd.reports.controller.data.SpecialityGenderWiseOPDPatientsDATA;
import mrd.reports.controller.fb.SpecialityGenderWiseOPDPatientsFB;


public class SpecialityGenderWiseOPDPatientsUTL extends ReportUTIL
{
	public static void getEssential(SpecialityGenderWiseOPDPatientsFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		//HttpSession session = _request.getSession();
		
		try
		{
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
			_fb.setChoice(Config.CHOICE_DATE_WISE);
			
			Map mp=SpecialityGenderWiseOPDPatientsDATA.getSpecialityGenderWiseOPDPatientReportEssentials(ControllerUTIL.getUserVO(_request));  
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
	/*
	public static void setUnitBasedOnSpeciality(SpecialityGenderWiseOPDPatientsFB _fb,HttpServletRequest _request)
	{
		
		String speciality=_fb.getSpeciality();
		Status objStatus= new Status();
		List unitList=new ArrayList();
		try
		{	
			_fb.setOption(MrdConfig.UNIT_WISE);
			unitList=SpecialityGenderWiseOPDPatientsDATA.getUnitBasedOnSpeciality(speciality,getUserVO(_request));
			WebUTIL.getSession(_request).setAttribute(MrdConfig.MRD_DEPT_UNIT_LIST,unitList);
			//objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.getSession(_request).setAttribute(MrdConfig.MRD_DEPT_UNIT_LIST,unitList);
			objStatus.add(Status.UNSUCESSFULL,"","");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			WebUTIL.getSession(_request).setAttribute(MrdConfig.MRD_DEPT_UNIT_LIST,unitList);
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
	}*/
}


