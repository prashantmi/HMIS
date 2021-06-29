package new_investigation.reports.controller.data;
/**
 *this new patient listing data file for multihospitals
 * 
 */

//import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.reports.controller.fb.RequistionlistingReportFB;
import new_investigation.reports.controller.fb.SampleRejectionListingReportFB;
import new_investigation.reports.delegate.SampleRejectionListingReportDelegate;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.SampleRejectionListingReportVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.ReportUtil;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;





import java.text.SimpleDateFormat;



public class SampleRejectionListingReportDATA {
	

	

/*public class RequistionListingReportDATA {
	
	public static void setInitDtl(FuelDetailRptFB formBean,HttpServletRequest request) {
		
		HisUtil util = null;
		
		try {
					
				util = new HisUtil("Vehicle Reference Report","FuelDetailRptDATA");
				
				formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
				
			} 
		 catch (Exception e) {

			String strmsgText = e.getMessage();
			HisException eObj = new HisException("Transport", "FuelDetailRptData->setInitDtl()", 
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], " +
					"Contact System Administrator! ");
			eObj = null;
		} finally {
			util = null;
		}

	}
	*/
	public static void showReport(SampleRejectionListingReportFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
	
		ReportUtil ts = null;
		
		String reportFormat = "html";
	
		Map<String, Object> params = null;
		try {
			
			ts = new ReportUtil();
			
			params = new HashMap<String, Object>();
			
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strFromDate = formBean.getFromDate();
			strFromDate+=" "+formBean.getFromHrTime()+":"+formBean.getFromMinTime();

			String strToDate = formBean.getToDate();
			strToDate+=" "+formBean.getToHrTime()+":"+formBean.getToMinTime();
System.out.println(strFromDate+"  "+strToDate);
			//String strReportType=formBean.getStrReportType();
			String strUserRemarks = formBean.getStrUserRemarks();
			reportFormat = formBean.getStrReportFormat();
			//String sample1= formBean.getStrSample();
			//String strReportMode=formBean.getReportMode();
			String strlabCode=formBean.getLabCode();
			String strtestcode=formBean.getTestCode();	
			/*if(strReportType.equalsIgnoreCase("1"))
				reportFormat = "pdf";
			else if(strReportType.equalsIgnoreCase("3"))
				reportFormat = "xls";*/
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
				
			}
			
			String strReportName = "Sample Rejection Listing Report";
	
				//String reportPath = "/new_investigation/reports/fuelDetail_transportrpt.rptdesign";
			String reportPath = "/new_investigation/reports/samplerejectionlistingrpt.rptdesign";
			if(formBean.getDatetype().equals("today"))
			{
				params.put("mode", "2");
			}
		 else if(formBean.getDatetype().equals("datewise"))
		{
			params.put("mode", "1");
		}
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("report_Footer_Visible", footerVisible);
				params.put("report_user_Remarks", strUserRemarks);
				params.put("hospCode", strHospitalCode);
				
				
				if(formBean.getDatetype().equals("today"))
				{
					params.put("fromDate", strFromDate);
					params.put("toDate", strToDate);
				}
			 else if(formBean.getDatetype().equals("datewise"))
			{
				 params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
					params.put("toDate", sdf.format(sdf.parse(strToDate)));
			}
				
				
				
				
				
				//params.put("sample", sample1);
				params.put("labCode", strlabCode);
				params.put("testcode", strtestcode);
				params.put("report_mode", "1");
				params.put("rpt_format", reportFormat);
				System.out.println("labcode--------------------------------------------------------");
				System.out.println(strlabCode);
				System.out.println("testCode--------------------------------------------------------");
				System.out.println(strtestcode);
				System.out.println(sdf.format(sdf.parse(strFromDate)));
				System.out.println(sdf.format(sdf.parse(strToDate)));
				ts.displayReport(request, response, reportPath, reportFormat,params, strHospitalCode);
		      	
		} catch (Exception e) {
			e.printStackTrace();
			
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("Transport", "samplerejectionlistingRptData->showReport()",strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], " + "Contact System Administrator! ");

			eObj = null;
		} finally {
			ts = null;
			params = null;
		}
	}
	
	

	
	public static Map fetchLab(SampleRejectionListingReportVO reqList_VO, UserVO _UserVO)
	{
		SampleRejectionListingReportDelegate reportDelegate = new SampleRejectionListingReportDelegate();
		return reportDelegate.fetchLab(reqList_VO, _UserVO);
	}
	

	public static Map fetchTest(SampleRejectionListingReportVO reqList_VO, UserVO _UserVO)
	{
		SampleRejectionListingReportDelegate reportDelegate = new SampleRejectionListingReportDelegate();
		return reportDelegate.fetchTest(reqList_VO, _UserVO);
	}



}
