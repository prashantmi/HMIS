package inpatient.reports.controller.util;

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
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import inpatient.InpatientConfig;
import inpatient.reports.controller.data.SpecialityWiseOperationDATA;
import inpatient.reports.controller.fb.SpecialityWiseOperationFB;

public class SpecialityWiseOperationUTL extends ReportUTIL
{
	public static void getEssential(SpecialityWiseOperationFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		HttpSession session = _request.getSession();
		StringBuffer sb=new StringBuffer();
		
		try
		
		{
			setSysdate(_request);
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
			_fb.setOption(InpatientConfig.DEPT_WISE);	
			
			Map map1=(HashMap)SpecialityWiseOperationDATA.getAllHospitalEssentials(getUserVO(_request));
			WebUTIL.setMapInSession(map1, _request);
			ControllerUTIL.getUserVO(_request).getHospitalCode();
			
			Map mp=SpecialityWiseOperationDATA.getSpecialityWiseOperationReportEssentials(ControllerUTIL.getUserVO(_request));
			WebUTIL.setMapInSession(mp,_request);
			
			List unitLst=(List)session.getAttribute(InpatientConfig.UNITS_BASED_ON_SPECIALITY);
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
	
	/**
	 * AJAX Response : Get All Unit based on Hospital
	 * @param fb
	 * @param request
	 * Added By Adil Wasi  on 25-Nov-2012
	 */
	public static StringBuffer getAllUnit_AJAX(SpecialityWiseOperationFB fb,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		StringBuffer sb=new StringBuffer();
		Status objStatus= new Status();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(request);
			userVO.setHospitalCode(fb.getHospitalCode());
			
			List<Entry> unitBasedOnHospList=(List)SpecialityWiseOperationDATA.getSpecialityWiseOperationUnit(userVO);
			
			
			for(Entry e :unitBasedOnHospList){
				sbAjaxRes.append("<option value='"+e.getValue()+"'>"+e.getLabel()+"</option>");
			}
			
			List unitLst=(List)session.getAttribute(InpatientConfig.UNITS_BASED_ON_SPECIALITY);
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
			sbAjaxRes.append("^"+sb.toString());
			
		}catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			//objStatus.add(Status.UNSUCESSFULL,"","Data Not Found Error");
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
		catch(Exception e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
		}
		finally
		{
			WebUTIL.setStatus(request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return sbAjaxRes;
	}
	
}


