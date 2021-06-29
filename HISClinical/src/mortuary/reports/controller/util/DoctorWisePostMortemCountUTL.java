package mortuary.reports.controller.util;

import java.util.List;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;

import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;

import mortuary.MortuaryConfig;
import mortuary.reports.controller.data.DoctorWisePostMortemDATA;
import mortuary.reports.controller.fb.DoctorWisePostMortemCountFB;


public class DoctorWisePostMortemCountUTL extends ReportUTIL
{
	public static void getEssential(DoctorWisePostMortemCountFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		try
		{
			setSysdate(_request);
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
		
			List essentialAllEmpBasedOnDept=DoctorWisePostMortemDATA.getAllDoctor(getUserVO(_request));
			WebUTIL.setAttributeInSession(_request,MortuaryConfig.ESSENTIAL_ALL_EMP_BASED_ON_DEPT, essentialAllEmpBasedOnDept);
			
		
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


