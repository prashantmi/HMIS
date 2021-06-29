package opd.reports.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import opd.reports.controller.data.ICDCodeReportDATA;
import opd.reports.controller.fb.ICDCodeReportFB;
import registration.RegistrationConfig;

public class ICDCodeReportUTL extends ReportUTIL
{
	public static void getEssential(ICDCodeReportFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		try
		{
			UserVO userVO = getUserVO(request);
			Map<String, Object> mpEssentials = ICDCodeReportDATA.getEssentials(userVO);
			fb.setGraphOrText(Config.TEXT);
			fb.setReportType(Config.RTF);
			WebUTIL.setMapInSession(mpEssentials, request);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Data Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}

	public static void setUnitBasedOnDepartment(ICDCodeReportFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		try
		{
			List unitList = ICDCodeReportDATA.getUnitBasedOnDepartment(fb.getDepartmentCode(), getUserVO(request));
			WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT, unitList);
			objStatus.add(Status.TRANSINPROCESS);
			WebUTIL.setStatus(request, objStatus);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Unit Not Found");
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
	}
}
