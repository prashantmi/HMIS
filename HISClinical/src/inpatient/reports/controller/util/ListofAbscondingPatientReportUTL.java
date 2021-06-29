package inpatient.reports.controller.util;

/**
 * @author Adil Wasi
 * Creation Date 03-Dec-2012
 */
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ReportUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import inpatient.reports.controller.data.ListofAbscondingPatientReportDATA;
import inpatient.reports.controller.fb.ListofAbscondingPatientReportFB;
import registration.RegistrationConfig;


public class ListofAbscondingPatientReportUTL extends ReportUTIL
{
	public static void getEssential(ListofAbscondingPatientReportFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		UserVO userVO=new UserVO();
		try
		{
			Map map1=(HashMap)ListofAbscondingPatientReportDATA.getAllHospitalEssentials(getUserVO(_request));
			WebUTIL.setMapInSession(map1, _request);
			
			
			userVO.setHospitalCode("100");
			Map mp=ListofAbscondingPatientReportDATA.getAllGlobalDepartment(userVO);
			WebUTIL.setMapInSession(mp, _request);
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
	
	
	
	/**
	 * AJAX Response : Get All Ward based on Hospital,Dept 
	 * @param fb
	 * @param request
	 * Added By Adil Wasi  on 03-Dec-2012
	 */
	public static StringBuffer getAllWard_AJAX(ListofAbscondingPatientReportFB fb,HttpServletRequest request)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus= new Status();
		try
		{
//			UserVO userVO = ControllerUTIL.getUserVO(request);
			
			
			List<Entry> wardBasedOnHospitalDeptUnitList=(List)ListofAbscondingPatientReportDATA.getWardBasedOnHospitalDepartmentUnit(fb.getHospitalCode(), fb.getDepartmentCode(), "%", getUserVO(request));
			
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


