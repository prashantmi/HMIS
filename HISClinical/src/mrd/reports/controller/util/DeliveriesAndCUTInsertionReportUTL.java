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

import mrd.reports.controller.data.DeliveriesAndCUTInsertionReportDATA;
import mrd.reports.controller.fb.DeliveriesAndCUTInsertionReportFB;
import mrd.vo.MrdReportDataVO;
import mrd.vo.MrdReportVO;


public class DeliveriesAndCUTInsertionReportUTL extends ReportUTIL
{
	public static void getEssential(DeliveriesAndCUTInsertionReportFB _fb,HttpServletRequest _request)
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

	public static void printReport(DeliveriesAndCUTInsertionReportFB objFB_p, HttpServletRequest objRequest_p)
	{
		Status objStatus = new Status();
		try
		{
			ControllerUTIL.setSysdate(objRequest_p);
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);

			MrdReportVO voReport_p = new MrdReportVO();
			HelperMethods.populate(voReport_p, objFB_p);
			String strServiceType=objFB_p.getStrService();
			Map<String,List<MrdReportDataVO>> mapReport = DeliveriesAndCUTInsertionReportDATA.getReportData(voReport_p, voUser,strServiceType);
			
			// Handling Exceptional Conditions
			if (mapReport == null || mapReport.keySet().size() == 0)
			{
				objStatus.add(Status.NEW, "SORRY, THERE IS NO DATA AVAILABLE!", "");
			}
			else
			{
				List<MrdReportDataVO> reportDataDel = mapReport.get("D");
				List<MrdReportDataVO> reportDataCuT = mapReport.get("C");
				if(reportDataDel!=null) objFB_p.setStrDeliveriesCount(reportDataDel.get(0).getStrCount());
				else	objFB_p.setStrDeliveriesCount("0");
				if(reportDataCuT!=null) objFB_p.setStrCuTInsertCount(reportDataCuT.get(0).getStrCount());
				else	objFB_p.setStrCuTInsertCount("0");
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


