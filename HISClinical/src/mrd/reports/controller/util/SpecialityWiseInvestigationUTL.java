package mrd.reports.controller.util;

import java.util.List;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;

import mrd.reports.controller.data.SpecialityWiseInvestigationDATA;
import mrd.reports.controller.fb.SpecialityWiseInvestigationFB;

public class SpecialityWiseInvestigationUTL extends ReportUTIL
{
	public static void getEssential(SpecialityWiseInvestigationFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		HttpSession session = _request.getSession();
		StringBuffer sb=new StringBuffer();
		
		try
		{
			setSysdate(_request);
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
			_fb.setOption(MrdConfig.DEPT_WISE);
			_fb.setLabTestOption(MrdConfig.LAB_WISE);
			Map mp=SpecialityWiseInvestigationDATA.getSpecialityWiseInvestigationReportEssentials(ControllerUTIL.getUserVO(_request));
			WebUTIL.setMapInSession(mp,_request);
			

			List unitLst=(List)session.getAttribute(MrdConfig.UNITS_BASED_ON_SPECIALITY);
			for(int i=0;i<unitLst.size();i++)
			{
				if(i<unitLst.size()-1)
				{
				Entry entry=(Entry)unitLst.get(i); 
				sb.append(entry.getValue()+"@");
				sb.append(entry.getLabel()+"#");
				}
				else
				{
					Entry entry=(Entry)unitLst.get(i); 
					sb.append(entry.getValue()+"@");
					sb.append(entry.getLabel());
				}
				
			}
			_fb.setCombo(sb.toString());

		
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


