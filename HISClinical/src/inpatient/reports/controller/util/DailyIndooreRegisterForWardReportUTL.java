package inpatient.reports.controller.util;

/**
 * @author Adil Wasi
 * Creation Date 04-Dec-2012
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

import inpatient.reports.controller.data.DailyIndooreRegisterForWardReportDATA;
import inpatient.reports.controller.fb.DailyIndooreRegisterForWardReportFB;


public class DailyIndooreRegisterForWardReportUTL extends ReportUTIL
{
	public static void getEssential(DailyIndooreRegisterForWardReportFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		UserVO userVO=new UserVO();
		try
		{
			userVO=getUserVO(_request);
			String strHospitalCode=userVO.getHospitalCode();
			Map map1=(HashMap)DailyIndooreRegisterForWardReportDATA.getAllHospitalEssentials(userVO);
			WebUTIL.setMapInSession(map1, _request);
			
			Map map3=DailyIndooreRegisterForWardReportDATA.getRegAndPatCategory(userVO);
			WebUTIL.setMapInSession(map3, _request);
			
			userVO.setHospitalCode("100");
			Map map2=DailyIndooreRegisterForWardReportDATA.getAllGlobalDepartment(userVO);
			WebUTIL.setMapInSession(map2, _request);
			
			
			
			Map map4=DailyIndooreRegisterForWardReportDATA.getDischargeStatus(userVO);
			WebUTIL.setMapInSession(map4, _request);
			
			_fb.setFromHour("00");
			_fb.setFromMin("00");
			_fb.setToHour("23");
			_fb.setToMin("59");
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
	public static StringBuffer getAllWard_AJAX(DailyIndooreRegisterForWardReportFB fb,HttpServletRequest request)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus= new Status();
		try
		{
//			UserVO userVO = ControllerUTIL.getUserVO(request);
			
			
			List<Entry> wardBasedOnHospitalDeptUnitList=(List)DailyIndooreRegisterForWardReportDATA.getWardBasedOnHospitalDepartmentUnit(fb.getHospitalCode(), fb.getDepartmentCode(), "%", getUserVO(request));
			
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


