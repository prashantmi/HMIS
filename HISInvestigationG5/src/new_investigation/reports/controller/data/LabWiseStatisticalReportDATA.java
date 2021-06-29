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
import new_investigation.reports.controller.fb.LabWiseStatisticalReportFB;
import new_investigation.reports.delegate.LabWiseStatisticalReportDelegate;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.LabWiseStatisticalReportVO;
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



public class LabWiseStatisticalReportDATA {
	

	

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
	public static void showReport(LabWiseStatisticalReportFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
	
		ReportUtil ts = null;
		LabWiseStatisticalReportVO reqList_VO = new LabWiseStatisticalReportVO();
		String reportFormat = "html";
	
		Map<String, Object> params = null;
		try {
			
			ts = new ReportUtil();
			
			params = new HashMap<String, Object>();
			Date Date=(Date)request.getSession().getAttribute("sysDate");
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
			String fmonth =formBean.getFromMonth();
			String fyear =formBean.getFromYear();
			String tmonth =formBean.getToMonth();
			String tyear =formBean.getToYear();
			String fromMonthYear = fmonth+"-"+fyear;
			formBean.setFromMonthYear(fromMonthYear);
			String toMonthYear = tmonth+"-"+tyear;
			formBean.setToMonthYear(toMonthYear);
			String strFromMonthYear=formBean.getFromMonthYear();
			String strToMonthYear=formBean.getToMonthYear();
			String strFromYear=formBean.getFromYr();
			String strToYear=formBean.getToYr();
			String dateTimeType=formBean.getDatetype();
			String strFromHour=formBean.getFromHrTime();
			String strFromMin=formBean.getFromMinTime();
			String strToHour=formBean.getToHrTime();
			String strToMin=formBean.getToMinTime();
			String strMode=formBean.getReportMode();
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
				
			}
			
			String strReportName = "LabWise Statistical Report";
	
				//String reportPath = "/new_investigation/reports/fuelDetail_transportrpt.rptdesign";
			String reportPath;
		
		  if(formBean.getDatetype().equals("datewise"))
		{
			params.put("mode", "1");
			System.out.println("I m datewise");
		}
		  else if(formBean.getDatetype().equals("today"))
			{
				params.put("mode", "2");
				/*String sysDate=WebUTIL.getCustomisedSysDate(Date, "dd-MMM-yyyy");
				strFromDate=sysDate+" "+strFromHour+":"+strFromMin;
				strToDate=sysDate+" "+strToHour+":"+strToMin;*/

				System.out.println("I m today");
			}
		  else 	if(formBean.getDatetype().equals("monthwise"))
			{
				params.put("mode", "3");
				strFromDate=strFromMonthYear;
				strToDate=strToMonthYear;

				System.out.println("I m monthwise");
			}
		  else 	if(formBean.getDatetype().equals("yearwise"))
			{
				params.put("mode", "4");
				strFromDate=""+strFromYear;
				strToDate=""+strToYear;

				System.out.println("I m yearwise");
			}
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("report_Footer_Visible", footerVisible);
				params.put("report_user_Remarks", strUserRemarks);
				params.put("hospCode", strHospitalCode);
				//params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
				//params.put("toDate", sdf.format(sdf.parse(strToDate)));
				params.put("fromDate", strFromDate);
				params.put("toDate", strToDate);
				//params.put("sample", sample1);
				params.put("labCode", strlabCode);
				params.put("testcode", strtestcode);
				params.put("reportMode", strMode);
				params.put("rpt_format", reportFormat);
				params.put("fromYr",strFromYear);
				params.put("toYr",strToYear);
				params.put("fromMonthYear",strFromMonthYear);
				params.put("toMonthYear",strToMonthYear);
				System.out.println("labcode--------------------------------------------------------");
				System.out.println(strlabCode);
				System.out.println("testCode--------------------------------------------------------");
				System.out.println("datetype============================================================");
				System.out.println(dateTimeType);
				System.out.println(strtestcode);
				if(strMode.equalsIgnoreCase("1"))
					reportPath = "/new_investigation/reports/labwisestatisticalrpt.rptdesign";
				else
					reportPath = "/new_investigation/reports/labwisestatisticalDetailedrpt.rptdesign";
				//System.out.println(sdf.format(sdf.parse(strFromDate)));
				//System.out.println(sdf.format(sdf.parse(strToDate)));
				ts.displayReport(request, response, reportPath, reportFormat,
						params, strHospitalCode);
		      	
		} catch (Exception e) {
			e.printStackTrace();
			
			String strmsgText = e.getMessage();
			HisException eObj = new HisException("Transport", "LabWiseStatisticalRptData->showReport()", 
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], " +
					"Contact System Administrator! ");
			eObj = null;
		} finally {
			ts = null;
			params = null;
		}
	}

	
	public static Map fetchLab(LabWiseStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		LabWiseStatisticalReportDelegate reportDelegate = new LabWiseStatisticalReportDelegate();
		return reportDelegate.fetchLab(reqList_VO, _UserVO);
	}
	

	public static Map fetchTest(LabWiseStatisticalReportVO reqList_VO, UserVO _UserVO)
	{
		LabWiseStatisticalReportDelegate reportDelegate = new LabWiseStatisticalReportDelegate();
		return reportDelegate.fetchTest(reqList_VO, _UserVO);
	}



}
