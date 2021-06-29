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

import mrd.reports.controller.data.DeptWiseMonthlyAbstractOfPatientDATA;
import mrd.reports.controller.fb.DeptWiseMonthlyAbstractOfPatientFB;


public class DeptWiseMonthlyAbstractOfPatientUTIL extends ReportUTIL
{
	public static void getEssential(DeptWiseMonthlyAbstractOfPatientFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
//		HttpSession session = _request.getSession();
		StringBuffer sb=new StringBuffer();
		//StringBuffer sbLabel=new StringBuffer();
		try
		{
			//_fb.setOption(MrdConfig.DEPT_WISE);	
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
		//	_fb.setChoice(Config.CHOICE_DATE_WISE);
			
			Map mp=DeptWiseMonthlyAbstractOfPatientDATA.getDeptWiseMonthlyAbstractOfPatientReportEssentials(ControllerUTIL.getUserVO(_request));  
			WebUTIL.setMapInSession(mp,_request);
			
		/*	List unitLst=(List)session.getAttribute(MrdConfig.UNITS_BASED_ON_SPECIALITY);
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
			*/
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
