package new_investigation.reports.controller.utl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

import new_investigation.InvestigationConfig;
import new_investigation.reports.controller.data.InvestigationStatisticalReportDATA;
import new_investigation.reports.controller.fb.InvestigationStatisticalReportFB;
import new_investigation.vo.InvestigationStatisticalReportVO;

public class InvestigationStatisticalReportUTIL {
	
	public static boolean fetchDept(InvestigationStatisticalReportFB reqListReport_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvestigationStatisticalReportVO reqList_VO = new InvestigationStatisticalReportVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			HisUtil util = null;
			
			 
			Date dt= (Date)_request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(_request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
					 
				 
			Map mp=new HashMap(); 
			//Map mpp=new HashMap(); 

			mp=InvestigationStatisticalReportDATA.fetchDept(reqList_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			/*mpp=InvestigationStatisticalReportDATA.fetchLab(reqList_VO, userVO);
			WebUTIL.setMapInSession(mpp, _request);*/
			
		//	HelperMethods.populate(reqListReport_fb, reqList_VO);

			 
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	

	
	
	
	
	
	
	public static boolean fetchLab(InvestigationStatisticalReportFB reqListReport_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvestigationStatisticalReportVO reqList_VO = new InvestigationStatisticalReportVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			HisUtil util = null;
			
			 
			Date dt= (Date)_request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(_request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
					 
				 
			Map mp=new HashMap(); 
			//Map mpp=new HashMap(); 
			reqList_VO.setDeptCode(reqListReport_fb.getDeptCode());
			mp=InvestigationStatisticalReportDATA.fetchLab(reqList_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			/*mpp=InvestigationStatisticalReportDATA.fetchTest(reqList_VO, userVO);
			WebUTIL.setMapInSession(mpp, _request);*/
			
			HelperMethods.populate(reqListReport_fb, reqList_VO);
			objStatus.add(Status.NEW);
			 
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}

	
	

	public static boolean fetchTest(InvestigationStatisticalReportFB reqListReport_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvestigationStatisticalReportVO reqList_VO = new InvestigationStatisticalReportVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			
			reqList_VO.setDeptCode(reqListReport_fb.getDeptCode());
			reqList_VO.setLabCode(reqListReport_fb.getLabCode());
			mp=InvestigationStatisticalReportDATA.fetchTest(reqList_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
		
			HelperMethods.populate(reqListReport_fb, reqList_VO);

			objStatus.add(Status.NEW);
			
		}
		catch (HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
		}
		catch (HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA, "", "Data Access Error");
		}
		catch (HisApplicationExecutionException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
		}
		catch (HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR, "", "Error");
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
		}
		return true;
	}
	
	
public static void setInitDtl(InvestigationStatisticalReportFB formBean,HttpServletRequest request) {
		
		HisUtil util = null;
		
		try {
					
				util = new HisUtil("Patient Reference Report","InvestigationnListingReportDATA");
				
				formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
				
			} 
		 catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException("Transport", "InvestigationStatisticalReportDATA->setInitDtl()", 
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], " +
					"Contact System Administrator! ");
			eObj = null;
		} finally {
			util = null;
		}

	}
	
	
public static boolean fetchSample(InvestigationStatisticalReportFB reqListReport_fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	InvestigationStatisticalReportVO reqList_VO = new InvestigationStatisticalReportVO();
	try
	{
		UserVO userVO = ControllerUTIL.getUserVO(_request);
		ControllerUTIL.setSysdate(_request);
		HisUtil util = null;
		
		 
		Date dt= (Date)_request.getSession().getAttribute(Config.SYSDATEOBJECT); 
		WebUTIL.getSession(_request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
				 
			 
		Map mp=new HashMap(); 
		//Map mpp=new HashMap(); 
		reqList_VO.setDeptCode(reqListReport_fb.getDeptCode());
		reqList_VO.setLabCode(reqListReport_fb.getLabCode());
		reqList_VO.setTestCode(reqListReport_fb.getTestCode());
		mp=InvestigationStatisticalReportDATA.fetchSample(reqList_VO, userVO);
		WebUTIL.setMapInSession(mp, _request);
		/*mpp=InvestigationStatisticalReportDATA.fetchLab(reqList_VO, userVO);
		WebUTIL.setMapInSession(mpp, _request);*/
		
		HelperMethods.populate(reqListReport_fb, reqList_VO);

		 
	}
	catch (HisRecordNotFoundException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.UNSUCESSFULL, "", "Record Not Found Error");
	}
	catch (HisDataAccessException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_DA, "", "Data Access Error");
	}
	catch (HisApplicationExecutionException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR_AE, "", "Application Execution Error");
	}
	catch (HisException e)
	{
		System.out.println(e.getMessage());
		objStatus.add(Status.ERROR, "", "Error");
	}
	finally
	{
		WebUTIL.setStatus(_request, objStatus);
	}
	return true;
}

}
