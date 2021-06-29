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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.MrdConfig;
import mrd.reports.controller.data.HospitalPerformanceReportDATA;
import mrd.reports.controller.fb.HospitalPerformanceReportFB;
import mrd.vo.MrdReportDataVO;
import mrd.vo.MrdReportVO;


public class HospitalPerformanceReportUTL extends ReportUTIL
{
	public static void getEssential(HospitalPerformanceReportFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		
		try
		{
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
			_fb.setChoice(Config.CHOICE_DATE_WISE);
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

	public static void printReport(HospitalPerformanceReportFB objFB_p, HttpServletRequest objRequest_p)
	{
		Status objStatus = new Status();
		try
		{
			ControllerUTIL.setSysdate(objRequest_p);
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);

			MrdReportVO voReport_p = new MrdReportVO();
			HelperMethods.populate(voReport_p, objFB_p);

			Map<String,List<MrdReportDataVO>> mapReportData = HospitalPerformanceReportDATA.getReportData(voReport_p, voUser);
			
			// Handling Exceptional Conditions
			if (mapReportData == null || mapReportData.keySet().size() == 0)
			{
				objStatus.add(Status.NEW, "SORRY, THERE IS NO DATA AVAILABLE!", "");
			}
			else
			{
				WebUTIL.setAttributeInSession(objRequest_p, MrdConfig.MRD_REPORT_DATA, mapReportData);
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


