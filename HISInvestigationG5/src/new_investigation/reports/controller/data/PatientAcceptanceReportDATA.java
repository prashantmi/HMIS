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

import new_investigation.reports.controller.fb.PatientAcceptanceReportFB;
import new_investigation.reports.delegate.PatientAcceptanceReportDelegate;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.PatientAcceptanceReportVO;
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



public class PatientAcceptanceReportDATA {
	

	

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
	public static void showReport(PatientAcceptanceReportFB formBean,
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
			String strToDate = formBean.getToDate();
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
			
			String strReportName = "Sample Collection Listing Report";
	
				//String reportPath = "/new_investigation/reports/fuelDetail_transportrpt.rptdesign";
			String reportPath = "/new_investigation/reports/patientacceptancerpt.rptdesign";
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
				params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
				params.put("toDate", sdf.format(sdf.parse(strToDate)));
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
				ts.displayReport(request, response, reportPath, reportFormat,
						params, strHospitalCode);
		      	
		} catch (Exception e) {
			e.printStackTrace();
			
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("Transport", "PatientAcceptanceRptData->showReport()", 
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], " +
					"Contact System Administrator! ");
			eObj = null;
		} finally {
			ts = null;
			params = null;
		}
	}

	
	public static Map fetchLab(PatientAcceptanceReportVO reqList_VO, UserVO _UserVO)
	{
		PatientAcceptanceReportDelegate reportDelegate = new PatientAcceptanceReportDelegate();
		return reportDelegate.fetchLab(reqList_VO, _UserVO);
	}
	

	public static Map fetchTest(PatientAcceptanceReportVO reqList_VO, UserVO _UserVO)
	{
		PatientAcceptanceReportDelegate reportDelegate = new PatientAcceptanceReportDelegate();
		return reportDelegate.fetchTest(reqList_VO, _UserVO);
	}



}
