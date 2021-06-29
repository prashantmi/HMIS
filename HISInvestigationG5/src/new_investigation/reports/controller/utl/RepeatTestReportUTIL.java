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
import new_investigation.reports.controller.data.RepeatTestReportDATA;
import new_investigation.masters.controller.fb.MandatoryComboMstFB;
import new_investigation.reports.controller.fb.RepeatTestReportFB;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.RepeatTestReportVO;

public class RepeatTestReportUTIL {
	

	
	public static boolean fetchLab(RepeatTestReportFB reqListReport_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RepeatTestReportVO reqList_VO = new RepeatTestReportVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);
			HisUtil util = null;
			
			 
			Date dt= (Date)_request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(_request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);
					 
				 
			Map mp=new HashMap(); 
			Map mpp=new HashMap(); 

			mp=RepeatTestReportDATA.fetchLab(reqList_VO, userVO);
			WebUTIL.setMapInSession(mp, _request);
			/*mpp=SampleRejectionListingReportDATA.fetchTest(reqList_VO, userVO);
			WebUTIL.setMapInSession(mpp, _request);*/
			
			//HelperMethods.populate(reqListReport_fb, reqList_VO);

			 
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

	
	public static boolean fetchTest(RepeatTestReportFB reqListReport_fb, HttpServletRequest _request)
	{
		Status objStatus = new Status();
		RepeatTestReportVO reqList_VO = new RepeatTestReportVO();
		try
		{
			UserVO userVO = ControllerUTIL.getUserVO(_request);
			ControllerUTIL.setSysdate(_request);

			Map mp=new HashMap(); 
			reqList_VO.setLabCode(reqListReport_fb.getLabCode());
			mp=RepeatTestReportDATA.fetchTest(reqList_VO, userVO);
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

	
	
	
	
	public static void setInitDtl(RepeatTestReportFB formBean,HttpServletRequest request) {
		
		HisUtil util = null;
		
		try {
					
				util = new HisUtil("Patient Reference Report","SampleRejectionListingReportDATA");
				
				formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
				
			} 
		 catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException("Transport", "SampleRejectionListingReportData->setInitDtl()", 
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], " +
					"Contact System Administrator! ");
			eObj = null;
		} finally {
			util = null;
		}

	}
	
	

}
