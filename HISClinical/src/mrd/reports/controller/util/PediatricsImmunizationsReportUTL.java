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
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.reports.controller.data.PediatricsImmunizationsReportDATA;
import mrd.reports.controller.fb.PediatricsImmunizationsReportFB;
import mrd.vo.MrdReportDataVO;
import mrd.vo.MrdReportVO;

public class PediatricsImmunizationsReportUTL extends ReportUTIL
{
	public static void getEssential(PediatricsImmunizationsReportFB objFB_p, HttpServletRequest objRequest_p)
	{
		Status objStatus = new Status();
		try
		{
			objFB_p.setGraphOrText(Config.TEXT);
			objFB_p.setReportType(Config.RTF);
			objFB_p.setChoice(Config.CHOICE_DATE_WISE);
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Error");
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
			objRequest_p.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}

	public static void printReport(PediatricsImmunizationsReportFB objFB_p, HttpServletRequest objRequest_p)
	{
		Status objStatus = new Status();
		try
		{
			ControllerUTIL.setSysdate(objRequest_p);
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);

			MrdReportVO voReport_p = new MrdReportVO();
			HelperMethods.populate(voReport_p, objFB_p);

			List<MrdReportDataVO> reportData = PediatricsImmunizationsReportDATA.getReportData(voReport_p, voUser);

			// Handling Exceptional Conditions
			if (reportData == null || reportData.size() == 0)
			{
				objStatus.add(Status.NEW, "SORRY, THERE IS NO DATA AVAILABLE!", "");
			}
			else
			{
				WebUTIL.setAttributeInSession(objRequest_p, MrdConfig.MRD_REPORT_DATA, reportData);
				objStatus.add(Status.TRANSINPROCESS);
			}
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, "", "Error");
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
			objRequest_p.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
	}
}
