package inpatient.reports.controller.util;

import java.util.HashMap;
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

import inpatient.reports.controller.data.ListofAbscondingPatientReportDATA;
import inpatient.reports.controller.data.RegisteredMlcPatientsDATA;
import inpatient.reports.controller.fb.ListofAbscondingPatientReportFB;
import inpatient.reports.controller.fb.RegisteredMlcPatientsFB;


public class RegisteredMlcPatientsUTL extends ReportUTIL
{
	

	public static void getEssential(RegisteredMlcPatientsFB _fb, HttpServletRequest _request) {
		
		Status objStatus= new Status();
		try
		{
			setSysdate(_request);
			_fb.setGraphOrText(Config.TEXT);	
			_fb.setReportType(Config.RTF);
			
			Map map1=(HashMap)RegisteredMlcPatientsDATA.getAllHospitalEssentials(getUserVO(_request));
			WebUTIL.setMapInSession(map1, _request);
			
			String[] multipleHospitalArray = new String[1];
			multipleHospitalArray[0] = ControllerUTIL.getUserVO(_request).getHospitalCode();
			_fb.setMultipleHospitalCode(multipleHospitalArray);
		
		
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
	 * AJAX Response : Get All Ward based on Hospital,Dept 
	 * @param fb
	 * @param request
	 * Added By Adil Wasi  on 03-Dec-2012
	 */
	public static StringBuffer getAllWard_AJAX(RegisteredMlcPatientsFB fb,HttpServletRequest request)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus= new Status();
		try
		{
//			UserVO userVO = ControllerUTIL.getUserVO(request);
			
			
			List<Entry> wardBasedOnHospitalDeptUnitList=(List)RegisteredMlcPatientsDATA.getWardBasedOnHospitalDepartmentUnit(fb.getHospitalCode(), "%", "%", getUserVO(request));
			
			for(Entry e :wardBasedOnHospitalDeptUnitList){
				sbAjaxRes.append("<option value='"+e.getValue()+"'>"+e.getLabel()+"</option>");
			}
			
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


