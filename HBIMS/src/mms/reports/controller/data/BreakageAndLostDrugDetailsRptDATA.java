package mms.reports.controller.data;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.reports.bo.BreakageAndLostDrugDetailsRptBO;
import mms.reports.controller.fb.BreakageAndLostDrugDetailsRptFB;
import mms.reports.vo.BreakageAndLostDrugDetailsRptVO;

public class BreakageAndLostDrugDetailsRptDATA {
	
	public static void getInitializedValues(BreakageAndLostDrugDetailsRptFB breakageAndLostDrugDetailsRptFB_p,	HttpServletRequest request, HttpServletResponse response) {

		BreakageAndLostDrugDetailsRptBO bo = null;
		BreakageAndLostDrugDetailsRptVO voObj = null;
		String strMsgText = null;
		String strCurrentDate;
		HisUtil hisutil = null;
		
		int strCurrentMonth;
		int CURRENT_YEAR;
		
		String strCurrentFinancialYear;
		String strNextFinancialYear;
		String strHospitalCode,strStoreVal,strDwhTypeVal,strDistrictStoreVal;
		
		try {

			bo = new BreakageAndLostDrugDetailsRptBO();
			voObj = new BreakageAndLostDrugDetailsRptVO();
			String strUserLevel = request.getSession().getAttribute("USER_LEVEL").toString();
			
			strHospitalCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			breakageAndLostDrugDetailsRptFB_p.setStrHospitalCode(strHospitalCode);
			breakageAndLostDrugDetailsRptFB_p.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
			hisutil = new HisUtil("DWH","BreakageAndLostDrugDetailsRptDATA");
			
					
			strCurrentDate=hisutil.getASDate("dd-MM-yyyy");
			
			
			strCurrentMonth = Integer.parseInt( strCurrentDate.split("\\-")[1] );
			
			
			if(strCurrentMonth>=4 ){
				CURRENT_YEAR = Integer.parseInt( strCurrentDate.split("\\-")[2] );
			}
			else
			{
				CURRENT_YEAR = Integer.parseInt( strCurrentDate.split("\\-")[2] ) - 1 ;	
			}
				
			
			strCurrentFinancialYear = CURRENT_YEAR + " - " + (CURRENT_YEAR + 1); 
			
			voObj.setStrSeatId(breakageAndLostDrugDetailsRptFB_p.getStrSeatId());
			voObj.setStrHospitalCode(strHospitalCode);
			
			if(strUserLevel.equals("6")){
				voObj.setStrMode("4");
			}
			else
				voObj.setStrMode("1");
			
			bo.getInitializedValues(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			
			if(strUserLevel.equals("6"))
			{
			strStoreVal = hisutil.getOptionValue(voObj.getStrStoreWs(), "0", "0^All", false);
			strDistrictStoreVal = hisutil.getOptionValue(voObj.getStrDistrictStoreWs(), "0", "0^All", false);
			}
			else
			{
		    strStoreVal = hisutil.getOptionValue(voObj.getStrStoreWs(), "0", "-1^Select Value", false);	
		    strDistrictStoreVal = hisutil.getOptionValue(voObj.getStrDistrictStoreWs(), "0", "-1^Select Value", false);
			}
			
			//strStoreVal = hisutil.getOptionValue(voObj.getStrStoreWs(), "0", "0^All", false);
			
//			strDistrictStoreVal = hisutil.getOptionValue(voObj.getStrDistrictStoreWs(), "0", "0^All", false);
			
			breakageAndLostDrugDetailsRptFB_p.setStrDistrictStoreValues(strDistrictStoreVal);
			
			breakageAndLostDrugDetailsRptFB_p.setStrStoreValues(strStoreVal);
			
			
			
			
						
			hisutil = new HisUtil("MMS Transactions", "BreakageAndLostDrugDetailsRptDATA");
			// For setting the financial year 
			breakageAndLostDrugDetailsRptFB_p.setStrStartFinancialYear(""+CURRENT_YEAR);
			breakageAndLostDrugDetailsRptFB_p.setStrEndFinancialYear(""+(CURRENT_YEAR+1));
			strCurrentDate=hisutil.getASDate("dd-MMM-yyyy");
			breakageAndLostDrugDetailsRptFB_p.setStrCurrentDate(strCurrentDate);			
		

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strMsgText = "mms.transactions.BreakageAndLostDrugDetailsRptDATA.getInitializedValues --> "+ e.getMessage();
			HisException eObj = new HisException("dwh",	"BreakageAndLostDrugDetailsRptDATA->getInitializedValues()", strMsgText);
			breakageAndLostDrugDetailsRptFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} 
		finally 
		{
			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (hisutil != null)
				hisutil = null;
		}
	}
	
	
	public static void getItemCatList(BreakageAndLostDrugDetailsRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{

		BreakageAndLostDrugDetailsRptBO bo = null;
		BreakageAndLostDrugDetailsRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {

			bo = new BreakageAndLostDrugDetailsRptBO();
			voObj = new BreakageAndLostDrugDetailsRptVO();
			
			String strStoreId = request.getParameter("storeId");
			
			if (strStoreId == null)
				strStoreId = "0";
			
			voObj.setStrStoreId(strStoreId);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
				
			bo.getItemCatList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Transactions", "BreakageAndLostDrugDetailsRptDATA");
			String temp = "<option value='0'>SelectValue</option>";

			if (voObj.getStrItemCatWs().size() != 0) {
				
				temp = util.getOptionValue(voObj.getStrItemCatWs(), "0", "0^SelectValue",true);

			}else{
				
				temp = "<option value='0'>SelectValue</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			//e.printStackTrace();
			strmsgText = "mms.transactions.BreakageAndLostDrugDetailsRptDATA.getItemCatList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",	"BreakageAndLostDrugDetailsRptDATA->getItemCatList()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}
	}

/*	
	public static void getStoreList(BreakageAndLostDrugDetailsRptFB breakageAndLostDrugDetailsRptFB_p,HttpServletRequest request, HttpServletResponse response) 
	{

		BreakageAndLostDrugDetailsRptBO bo = null;
		BreakageAndLostDrugDetailsRptVO voObj = null;
		String strmsgText = null;
		String strHospitalCode, strStoreVal = "";
		String strDwhTypeVal;

		HisUtil util = null;
		try {

			bo = new BreakageAndLostDrugDetailsRptBO();
			voObj = new BreakageAndLostDrugDetailsRptVO();
			
			strHospitalCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			breakageAndLostDrugDetailsRptFB_p.setStrHospitalCode(strHospitalCode);
			breakageAndLostDrugDetailsRptFB_p.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			voObj.setStrHospitalCode(strHospitalCode);
			voObj.setStrSeatId(breakageAndLostDrugDetailsRptFB_p.getStrSeatId());
			breakageAndLostDrugDetailsRptFB_p.setStrDrugWarehouseTypeId(request.getParameter("dwhTypeId"));
			
			
			voObj.setStrDrugWarehouseTypeId(breakageAndLostDrugDetailsRptFB_p.getStrDrugWarehouseTypeId());
			
				
			
			bo.getStoreList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("DWH", "BreakageAndLostDrugDetailsRptDATA");
			
			strStoreVal = util.getOptionValue(voObj.getStrStoreWs(), "0", "0^Select Value", false);
						
		
			 response.setHeader("Cache-Control", "no-cache");
			 response.getWriter().print(strStoreVal);

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.transactions.BreakageAndLostDrugDetailsRptDATA.getStoreList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"BreakageAndLostDrugDetailsRptDATA->getStoreList()", strmsgText);
			breakageAndLostDrugDetailsRptFB_p.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			if (bo != null)
				bo = null;
			if (voObj != null)
				voObj = null;
			if (util != null)
				util = null;
		}
	}
	*/
	
	public static void showReport(BreakageAndLostDrugDetailsRptFB breakageAndLostDrugDetailsRptFB_p,HttpServletRequest request, HttpServletResponse response) {
	
		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strReportName ="";
		
		String strStoreId = "";
		
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			breakageAndLostDrugDetailsRptFB_p.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			breakageAndLostDrugDetailsRptFB_p.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			String strHospitalCode = breakageAndLostDrugDetailsRptFB_p.getStrHospitalCode();
			String strReportId = breakageAndLostDrugDetailsRptFB_p.getStrReportId();
	
			
			
			String strFromDate = breakageAndLostDrugDetailsRptFB_p.getStrFromDate();
			String strToDate = breakageAndLostDrugDetailsRptFB_p.getStrToDate();
			
			String strUserRemarks = breakageAndLostDrugDetailsRptFB_p.getStrUserRemarks();
			
			reportFormat = breakageAndLostDrugDetailsRptFB_p.getStrReportFormat();
			
			
			
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if(breakageAndLostDrugDetailsRptFB_p.getStrIsFooter()==null)
				breakageAndLostDrugDetailsRptFB_p.setStrIsFooter("0");
			
			if (breakageAndLostDrugDetailsRptFB_p.getStrIsFooter().equals("1"))
			{
				footerVisible = false;
				
			}
			
			if(breakageAndLostDrugDetailsRptFB_p.getStrWhetherConsolidatedDataForAllInstitutesOfDistrictDrugWarehouseCheckBox()==null)
			{
				breakageAndLostDrugDetailsRptFB_p.setStrWhetherConsolidatedDataForAllInstitutesOfDistrictDrugWarehouseCheckBox("0");
			}
			
			
			
			if(breakageAndLostDrugDetailsRptFB_p.getStrBreakageOrLost()!=null && breakageAndLostDrugDetailsRptFB_p.getStrBreakageOrLost().equals("1"))
			{
				if(breakageAndLostDrugDetailsRptFB_p.getStrWhetherConsolidatedDataForAllInstitutesOfDistrictDrugWarehouseCheckBox().equals("0"))
				{
					strReportName = "BREAKAGE DETAILS";
				}
				else
				{
					strReportName = "CONSOLIDATED BREAKAGE DETAILS"; 
				}
			}
			else
			{
				strReportName = "Lost Details";
				
				if(breakageAndLostDrugDetailsRptFB_p.getStrWhetherConsolidatedDataForAllInstitutesOfDistrictDrugWarehouseCheckBox().equals("1"))
				{
					strReportName = "LOST DETAILS"; 
				}
				else
				{
					strReportName = "CONSOLIDATED LOST DETAILS"; 
				}
			}
			

							
				String reportPath = "/mms/reports/dwh_breakageAndLostDrugDetails_rpt.rptdesign";
				
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("report_Footer_Visible", footerVisible);
				params.put("report_user_Remarks", strUserRemarks);
				
				params.put("hospCode", strHospitalCode);
				params.put("mode", "1");
				
				if(breakageAndLostDrugDetailsRptFB_p.getStrStoreId()!=null && !breakageAndLostDrugDetailsRptFB_p.getStrStoreId().equals("0"))
				{
					params.put("storeId", breakageAndLostDrugDetailsRptFB_p.getStrStoreId());
					  strStoreId=breakageAndLostDrugDetailsRptFB_p.getStrStoreId();
				}
				else
				{
					params.put("storeId", breakageAndLostDrugDetailsRptFB_p.getStrDistrictStoreId());
					
					strStoreId = breakageAndLostDrugDetailsRptFB_p.getStrDistrictStoreId();
				}
				
								
				params.put("breakageOrLostFlag", breakageAndLostDrugDetailsRptFB_p.getStrBreakageOrLost());
								
				
				params.put("catCode", breakageAndLostDrugDetailsRptFB_p.getStrItemCatNo());
				params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
				params.put("toDate", sdf.format(sdf.parse(strToDate)));
				
				params.put("storeName", breakageAndLostDrugDetailsRptFB_p.getStrStoreName());
								
				ts.displayReport(request, response, reportPath, reportFormat,params, strStoreId);
				
			
		      	
		} catch (Exception e) {
	
			e.printStackTrace();
	
		}
	}

}
