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

import mrd.reports.controller.data.SpecialityUnitWiseOPDPatientsDATA;
import mrd.reports.controller.fb.SpecialityUnitWiseOpdPatientsFB;


public class SpecialityUnitWiseOPDPatientsUTL extends ReportUTIL
{
	public static void getEssential(SpecialityUnitWiseOpdPatientsFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		try
		{
			setSysdate(_request);
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
		//	_fb.setChoice(Config.CHOICE_DATE_WISE);
			Map mp=SpecialityUnitWiseOPDPatientsDATA.getSpecialityUnitWiseOPDPatientReportEssentials(ControllerUTIL.getUserVO(_request));  
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
	
	/*public static void setUnitBasedOnDept(SpecialityUnitWiseOpdPatientsFB _fb,HttpServletRequest _request)
	{
		
		String  deptCode= _fb.getDepartmentCode();
		Status objStatus= new Status();
		List unitList=new ArrayList();
		try
		{	
			
			unitList=SpecialityUnitWiseOPDPatientsDATA.getUnitBasedOnDept(deptCode,getUserVO(_request));
			WebUTIL.getSession(_request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
			//objStatus.add(Status.TRANSINPROCESS);
		}
		catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.getSession(_request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
			objStatus.add(Status.UNSUCESSFULL,"","This Department Has No Unit");
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			WebUTIL.getSession(_request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
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


