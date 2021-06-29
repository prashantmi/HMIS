package mrd.reports.controller.util;

/**
 * @author Adil Wasi
 * Creation Date 11-Jul-2012
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

import mrd.reports.controller.data.WardCensusReportDATA;
import mrd.reports.controller.fb.WardCensusReportFB;
import registration.RegistrationConfig;


public class WardCensusReportUTIL extends ReportUTIL
{
	public static void getEssential(WardCensusReportFB _fb,HttpServletRequest _request)
	{
		Status objStatus= new Status();
		UserVO userVO=new UserVO();
		try
		{
			Map map1=(HashMap)WardCensusReportDATA.getAllHospitalEssentials(getUserVO(_request));
			WebUTIL.setMapInSession(map1, _request);
			
			
			userVO.setHospitalCode("100");
			Map mp=WardCensusReportDATA.getDepartment(userVO);
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
	
	public static void getUnitBasedOnDept(WardCensusReportFB fb,HttpServletRequest request)
	{
		String deptCode=fb.getDepartmentCode();
		Status objStatus= new Status();
		
		try{	
			List unitList=WardCensusReportDATA.getUnitBasedOnDepartment(deptCode,getUserVO(request));
			WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,unitList);
			//objStatus.add(Status.TRANSINPROCESS);
			//WebUTIL.setStatus(request,objStatus);
		}catch(HisRecordNotFoundException e)
		{
			e.printStackTrace();
			//objStatus.add(Status.UNSUCESSFULL,"","Unit Not Found ");
			WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,null);
		}
		catch(HisDataAccessException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
			WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,null);
		}
		catch(HisApplicationExecutionException e)
		{		
			e.printStackTrace();
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
			WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,null);
		}
		catch(HisException e)
		{
			e.printStackTrace();
			objStatus.add(Status.ERROR,"","Error");
			WebUTIL.getSession(request).setAttribute(RegistrationConfig.ESSENTIAL_BO_OPTION_UNIT_BASED_ON_DEPT,null);
		}		
		finally
		{
			WebUTIL.setStatus(request,objStatus);
			System.out.println("   -----> objStatus in finally  : "+objStatus);
			System.out.println("   -----> objStatus list  : "+objStatus.getStatusList());
		}
	}
	
	/**
	 * AJAX Response : Get All Unit based on Dept
	 * @param fb
	 * @param request
	 * Added By Adil Wasi  on 24-Jul-2012
	 */
	public static StringBuffer getAllUnit_AJAX(WardCensusReportFB fb,HttpServletRequest request)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus= new Status();
		try
		{
//			UserVO userVO = ControllerUTIL.getUserVO(request);
			
			
			List<Entry> unitBasedOnDeptList=(List)WardCensusReportDATA.getUnitBasedOnDepartment(fb.getDepartmentCode(),getUserVO(request));
			
			for(Entry e :unitBasedOnDeptList){
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
	
	/**
	 * AJAX Response : Get All Ward based on Hospital Dept Unit
	 * @param fb
	 * @param request
	 * Added By Adil Wasi  on 24-Jul-2012
	 */
	public static StringBuffer getAllWard_AJAX(WardCensusReportFB fb,HttpServletRequest request)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus= new Status();
		try
		{
//			UserVO userVO = ControllerUTIL.getUserVO(request);
			
			
			List<Entry> wardBasedOnHospitalDeptUnitList=(List)WardCensusReportDATA.getWardBasedOnHospitalDepartmentUnit(fb.getHospitalCode(), fb.getDepartmentCode(), fb.getUnit(), getUserVO(request));
			
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
	
	/**
	 * AJAX Response : Get All Room based on Hospital,Unit and ward
	 * @param fb
	 * @param request
	 * Added By Adil Wasi  on 24-Jul-2012
	 */
	public static StringBuffer getAllRoom_AJAX(WardCensusReportFB fb,HttpServletRequest request)
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		Status objStatus= new Status();
		try
		{
//			UserVO userVO = ControllerUTIL.getUserVO(request);
			
			
			List<Entry> roomBasedOnHospitalDeptUnitWardList=(List)WardCensusReportDATA.getRoomBasedOnHospitalDeptUnitWard(fb.getHospitalCode(), fb.getUnit(), fb.getWardCode());
			
			for(Entry e :roomBasedOnHospitalDeptUnitWardList){
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


