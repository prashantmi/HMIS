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
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mrd.reports.controller.data.LabCensusReportDATA;
import mrd.reports.controller.fb.LabCensusReportFB;


public class LabCensusReportUTIL extends ReportUTIL
{
	public static void getEssential(LabCensusReportFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		UserVO userVO=new UserVO();
		try
		{
			Map map1=(HashMap)LabCensusReportDATA.getAllHospitalEssentials(getUserVO(_request));
			WebUTIL.setMapInSession(map1, _request);
			
			userVO.setHospitalCode("100");
			Map mp=LabCensusReportDATA.getDepartment(userVO);
			WebUTIL.setMapInSession(mp, _request);
			
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
	
	public static StringBuffer getLaboratoryandTimeMap(HttpServletRequest _request, LabCensusReportFB _fb) 
	{
		Status objStatus= new Status();
		 
		StringBuffer sbAjaxRes = new StringBuffer();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			userVO.setHospitalCode(_fb.getHospitalCode());
			
			List<Entry> roomBasedOnHospitalDeptUnitWardList=(List)LabCensusReportDATA.getLaboratoryandTimeMap(userVO,_fb.getDepartmentCode());
			
			for(Entry e :roomBasedOnHospitalDeptUnitWardList){
				sbAjaxRes.append("<option value='"+e.getValue()+"'>"+e.getLabel()+"</option>");
			}
		}catch(HisRecordNotFoundException e)
		{
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found Error");
		}
		catch(HisDataAccessException e)
		{
			System.out.println("Inside HisDataAccessException");
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println("Inside HisApplicationExecutionException");
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println("Inside HisException");
			objStatus.add(Status.ERROR,"","Error");
		}		
		finally
		{
			WebUTIL.setStatus(_request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}	
		return sbAjaxRes;
	}
	
	
	
}


