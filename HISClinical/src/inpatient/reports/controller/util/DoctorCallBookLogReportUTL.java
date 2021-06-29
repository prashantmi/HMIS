package inpatient.reports.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.reports.controller.data.DoctorCallBookLogReportDATA;
import inpatient.reports.controller.fb.DoctorCallBookLogReportFB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DoctorCallBookLogReportUTL extends ReportUTIL
{

	public static void getEssential(DoctorCallBookLogReportFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		// HttpSession session=request.getSession();
		// List listWard = new ArrayList();
		try
		{
			setSysdate(request);
			Date dt = DoctorCallBookLogReportDATA.getSysDateAsDate();

			WebUTIL.getSession(request).setAttribute(InpatientConfig.SYSADATEOBJECT, dt);
			fb.setGraphOrText(Config.TEXT);
			fb.setReportType(Config.RTF);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Data Not Found");
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

	public static boolean getWardOnBasisOfUnitCode(DoctorCallBookLogReportFB fb, HttpServletRequest request)
	{
		Status objStatus = new Status();
		boolean flag = true;
		HttpSession session = request.getSession();
		List listWard = new ArrayList();
		
		try
		{
			UserVO voUser = getUserVO(request);
			if(InpatientConfig.IPD_NURSING_DESK_UNIT_SELECTION.equals(InpatientConfig.UNIT_SELECTION_YES))
			{
				String unit = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_UNIT_CODE);
				fb.setDeptUnitCode(unit);
				listWard = DoctorCallBookLogReportDATA.getWardOnBasisOfUnitCode(fb.getDeptUnitCode(), voUser);
			}
			else
				listWard = DoctorCallBookLogReportDATA.getWardListBasedOnRole(voUser);
			WebUTIL.setAttributeInSession(request, InpatientConfig.WARD_LIST, listWard);

			objStatus.add(Status.INPROCESS);
		}
		catch (HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
			objStatus.add(Status.INPROCESS);
			WebUTIL.setAttributeInSession(request, InpatientConfig.WARD_LIST, listWard);
			e.printStackTrace();
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, e.getMessage(), "");
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		catch (HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR, e.getMessage(), "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE, e.getMessage(), "");
		}
		finally
		{
			WebUTIL.setStatus(request, objStatus);
		}
		return flag;
	}

	public static void getConsultantDetails(DoctorCallBookLogReportFB _fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		List consultantDetails = new ArrayList();
		try
		{
			UserVO userVO = getUserVO(_request);
			setSysdate(_request);

			consultantDetails = DoctorCallBookLogReportDATA.getAllConsultantDetails(_fb.getDeptUnitCode(), userVO);

			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_LIST, consultantDetails);
			// objStatus.add(Status.NEW);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			WebUTIL.setAttributeInSession(_request, InpatientConfig.CONSULTANT_LIST, consultantDetails);
			objStatus.add(Status.UNSUCESSFULL, "", "No Consultant Found");
		}
		catch (HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
	}
}
