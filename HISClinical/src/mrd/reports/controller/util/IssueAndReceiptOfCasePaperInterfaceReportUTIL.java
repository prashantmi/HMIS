package mrd.reports.controller.util;

/**
 * @author Adil Wasi
 * Creation Date 24-Jul-2012
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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.reports.controller.data.IssueAndReceiptOfCasePaperInterfaceReportDATA;
import mrd.reports.controller.fb.IssueAndReceiptOfCasePaperInterfaceReportFB;


public class IssueAndReceiptOfCasePaperInterfaceReportUTIL extends ReportUTIL
{
	public static void getEssential(IssueAndReceiptOfCasePaperInterfaceReportFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
//		UserVO userVO=new UserVO();
		try
		{
			setSysdate(_request);
			Map map1=(HashMap)IssueAndReceiptOfCasePaperInterfaceReportDATA.getAllHospitalEssentials(getUserVO(_request));
			
			
			WebUTIL.setMapInSession(map1, _request);
			
			String[] multipleHospitalArray = new String[1];
			multipleHospitalArray[0] = ControllerUTIL.getUserVO(_request).getHospitalCode();
			_fb.setMultipleHospitalCode(multipleHospitalArray);
			
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
	
	
	
}


