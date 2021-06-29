package new_investigation.reports.controller.utl;

import hisglobal.ReportUtil;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.InvestigationConfig;
import new_investigation.reports.controller.data.InvestigationListingReportDATA;
import new_investigation.masters.controller.fb.MandatoryComboMstFB;
import new_investigation.reports.controller.fb.InvestigationListingReportFB;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.InvestigationListingReportVO;

public class InvestigationListingReportUTIL {
	

	public static boolean fetchDept(InvestigationListingReportFB reqListReport_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvestigationListingReportVO reqList_VO = new InvestigationListingReportVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			HisUtil util = null;
			
			 
			Date dt= (Date)_request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(_request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
					 
				 
			Map mp=new HashMap(); 
			//Map mpp=new HashMap(); 

			mp=InvestigationListingReportDATA.fetchDept(reqList_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			/*mpp=InvestigationListingReportDATA.fetchLab(reqList_VO, userVO);
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
	

	
	
	
	
	
	
	public static boolean fetchLab(InvestigationListingReportFB reqListReport_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvestigationListingReportVO reqList_VO = new InvestigationListingReportVO();
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
			mp=InvestigationListingReportDATA.fetchLab(reqList_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			/*mpp=InvestigationListingReportDATA.fetchTest(reqList_VO, userVO);
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

	
	

	public static boolean fetchTest(InvestigationListingReportFB reqListReport_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		InvestigationListingReportVO reqList_VO = new InvestigationListingReportVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			
			reqList_VO.setDeptCode(reqListReport_fb.getDeptCode());
			reqList_VO.setLabCode(reqListReport_fb.getLabCode());
			mp=InvestigationListingReportDATA.fetchTest(reqList_VO, userVO);
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
	
	
public static void setInitDtl(InvestigationListingReportFB formBean,HttpServletRequest request) {
		
		HisUtil util = null;
		
		try {
					
				util = new HisUtil("Patient Reference Report","InvestigationnListingReportDATA");
				
				formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
				
			} 
		 catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException("Transport", "InvestigationListingReportData->setInitDtl()", 
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], " +
					"Contact System Administrator! ");
			eObj = null;
		} finally {
			util = null;
		}

	}
	
	
public static boolean fetchSample(InvestigationListingReportFB reqListReport_fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	InvestigationListingReportVO reqList_VO = new InvestigationListingReportVO();
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
		mp=InvestigationListingReportDATA.fetchSample(reqList_VO, userVO);
		WebUTIL.setMapInSession(mp, _request);
		/*mpp=InvestigationListingReportDATA.fetchLab(reqList_VO, userVO);
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

/*Added By Prashant */
public static boolean fetchRdLab(InvestigationListingReportFB reqListReport_fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	InvestigationListingReportVO reqList_VO = new InvestigationListingReportVO();
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
		mp=InvestigationListingReportDATA.fetchRdLab(reqList_VO, userVO);
		WebUTIL.setMapInSession(mp, _request);
		/*mpp=InvestigationListingReportDATA.fetchTest(reqList_VO, userVO);
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


public static boolean fetchRdTest(InvestigationListingReportFB reqListReport_fb, HttpServletRequest _request)
{
	Status objStatus = new Status();
	InvestigationListingReportVO reqList_VO = new InvestigationListingReportVO();
	try
	{
		UserVO userVO = ControllerUTIL.getUserVO(_request);
		ControllerUTIL.setSysdate(_request);

		Map mp=new HashMap(); 
		
		reqList_VO.setDeptCode(reqListReport_fb.getDeptCode());
		reqList_VO.setLabCode(reqListReport_fb.getLabCode());
		mp=InvestigationListingReportDATA.fetchRdTest(reqList_VO, userVO);
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


}
