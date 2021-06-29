package billing.reports;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

public class ChargeDetailRptDATA {
	
	public static void initReportPage(ChargeDetailRptFB formBean,HttpServletRequest request) {

		ChargeDetailRptBO bo = null;

		ChargeDetailRptVO voObj = null;

		HisUtil util = new HisUtil("Billing Reports", "ChargeDetailRptDATA");
		String strHospSerVal = "";
		 String strHospNameValues="";
		String strmsgText = null;
		try {
			bo = new ChargeDetailRptBO();
			voObj = new ChargeDetailRptVO();
			 
			//vo.setStrHospitalCode(formBean.getStrHospitalCode());
			//to get the hospital name from form Bean added by Anshul.
           voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )? request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
           voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			//to get the list of Hospitals.Added by Anshul.
			bo.getHospitalName(voObj);
			//bo.getHospSerDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			//to store the Name of the hospitals in a string variable.Added by Anshul.
             strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), "0","0^Select Value", false);	

			
			strHospSerVal = util.getOptionValue(voObj.getStrHospServiceWs(), "0","0^Select Value", false);
			
			//to set the Hospital Names in the combo Box.Added by Anshul.
			formBean.setStrHospNameValues(strHospNameValues);
			//strHospSerVal = util.getOptionValue(voObj.getStrHospServiceWs(),"1", "", false);
			

			formBean.setStrHospSerValues(strHospSerVal);
			formBean.setStrCurrentDate(util.getASDate("MMM yyyy"));
		} catch (Exception e) {
			strmsgText = "billing.reports.ChargeDetailRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ChargeDetailRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

		
				bo = null;
				voObj = null;
				util = null;
		}
	}
	public static void getHospitalServiceDetails(ChargeDetailRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {
		ChargeDetailRptBO bo = null;

		ChargeDetailRptVO voObj = null;

		HisUtil util = null;
		String strmsgText = null;
		try {

			bo = new ChargeDetailRptBO();
			voObj = new ChargeDetailRptVO();
			
			voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

			//String strhospCode=request.getParameter("hospCode").toString();

			
		//	if (strhospCode == null)
				//strhospCode = "0";
		
		//	voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());

		
			
			
			
			bo.getHospSerDetails(voObj);
			
			util = new HisUtil("Billing Reports", "ChargeDetailRptDATA");
			
			String temp1;
			
			if (voObj.getStrHospSerWs().size() != 0) {
				temp1 = util.getOptionValue(voObj.getStrHospSerWs(), "0", "0^Select value",true);
			}
			else {
				temp1 ="<option value='0'>Select value</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp1);
		}
		
		catch (Exception e) 
		{
			strmsgText = "ipd.reports.ChargeDetailRptDATA.getHospitalServiceDetails --> "+ e.getMessage();
			HisException eObj = new HisException("ipd","ChargeDetailRpt->getHospitalServiceDetails()", 

strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 

	}
	
	
	
	
	public static void getCategoryDetails(ChargeDetailRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		ChargeDetailRptBO bo = null;
		ChargeDetailRptVO vo = null;
		
		String strmsgText = null;
		HisUtil util = null;
		String strCategoryVal = "";
		
		try {
		
			bo = new ChargeDetailRptBO();
			vo = new ChargeDetailRptVO();
			
			//vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )?request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			bo.getCategoryDetails(vo);
			
			if (vo.getStrMsgType().equals("1")) {
			
				throw new Exception(vo.getStrMsgString());
			
			}
			util = new HisUtil("Billing Reports", "ChargeDetailRptDATA");
			
			strCategoryVal = util.getOptionValue(vo.getStrCategoryWs(),
					"1", "All", false);
			
			formBean.setStrCategoryValues(strCategoryVal);
			
		} catch (Exception e) {
			strmsgText = "billing.reports.ChargeDetailRptDATA.getCategoryDetails --> "
						+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"ChargeDetailRptDATA->getCategoryDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");
			
			eObj = null;
		} finally {
		
			bo = null;
			vo = null;
			util = null;
			}
		}
	
	public static void getGroupDetails(ChargeDetailRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		ChargeDetailRptBO bo = null;
		ChargeDetailRptVO vo = null;

		HisUtil util = null;
		String strmsgText = null;
		
		try {
			
			bo = new ChargeDetailRptBO();
			vo = new ChargeDetailRptVO();
			
			String strChargeTypeId = request.getParameter("chargeTypeId");
			if(strChargeTypeId == null)
				strChargeTypeId = "0";

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrChargeTypeId(strChargeTypeId);
			bo.getGroupDetails(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());

			}

			util = new HisUtil("Billing Reports", "Charge Detail Report");
			String temp = "<option value='0'>Select Value</option>";

			if (vo.getStrGroupDetailWs().size() != 0) {

				temp = util.getOptionValue(vo.getStrGroupDetailWs(), "0", "0^All",
						true);

			}else {
				temp = "<option value='0'>All</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);
			
			
		} catch (Exception e) {
			strmsgText = "billing.reports.ChargeDetailRptDATA.getGroupDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("ipd",
					"ChargeDetailRptDATA->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {

			bo = null;
			vo = null;
			util = null;
		}
	}
	
	
	public static void showReport(ChargeDetailRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
				//	String strReportName = "";	
					
					String strChargeTypeId = formBean.getStrChargeTypeId();
					String strGroupId = formBean.getStrGroupNameId();
					String strPatientCatCode = formBean.getStrCategory();
					String strMonthVal = formBean.getStrMonthVal();
					String strYear = formBean.getStrYear();
					
					String strMonYear = strMonthVal+" "+strYear;
					
					String strHospitalCode = formBean.getStrHospitalCode();
					String strCategoryName = formBean.getStrCatCode();
					String strCurrentDate = formBean.getStrCurrentDate();
										
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
					reportFormat = formBean.getStrReportFormat();
					
				//	SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
					
					boolean footerVisible = true;
					boolean IsIpd = true;
					boolean IsOpdEmer = true;
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
					}


					String reportPath = "/billing/reports/chargeDetail_billrpt.rptdesign";
					
					if(strChargeTypeId.equals("2")){
						
						IsIpd = false;
						
						if(strGroupId.equals("0")){
							params.put("group_Id", "null");
						}else{
							params.put("group_Id", strGroupId);
						}
						
							params.put("chargeType_Name", "Ipd");
						
						params.put("category_Name", strCategoryName);

						params.put("cat_Code", strPatientCatCode);
						params.put("chargetype_Id", strChargeTypeId);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Mon", "0");
						params.put("from_Year", "0");
						params.put("IsIpd", IsIpd);
						
						if(formBean.getStrRateVal().equals("0")){
							params.put("from_Date", strMonYear);
						}else if(formBean.getStrRateVal().equals("1")){
							params.put("from_Date", strCurrentDate);
						}
						
						params.put("report_id", strReportId);
					//	params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						
					}else if(strChargeTypeId.equals("1") || strChargeTypeId.equals("3")){
						
						IsOpdEmer = false;
					
						if(strGroupId.equals("0")){
							params.put("group_Id", "0");
						}else{
							params.put("group_Id", strGroupId);
						}
						if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						params.put("category_Name", strCategoryName);
						
						params.put("cat_Code", strPatientCatCode);
						params.put("hosp_Code", strHospitalCode);
						params.put("chargetype_Id", strChargeTypeId);
						params.put("from_Mon", "0");
						params.put("from_Year", "0");
						params.put("IsOpdEmer", IsOpdEmer);
						
						if(formBean.getStrRateVal().equals("0")){
							params.put("from_Date", strMonYear);
						}else if(formBean.getStrRateVal().equals("1")){
							params.put("from_Date", strCurrentDate);
						}
												
						params.put("report_id", strReportId);
					//	params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						
						
					}
					
					
					
					
					
					
else if(strChargeTypeId.equals("0"))
{
						
						IsOpdEmer = false;
					
						if(strGroupId.equals("0")){
							params.put("group_Id", "null");
						}else{
							params.put("group_Id", strGroupId);
						}
						
							params.put("chargeType_Name", "All Hospital Services");
						
						params.put("category_Name", strCategoryName);
						
						params.put("cat_Code", strPatientCatCode);
						params.put("hosp_Code", strHospitalCode);
						params.put("chargetype_Id", strChargeTypeId);
						params.put("from_Mon", "0");
						params.put("from_Year", "0");
						params.put("IsOpdEmer", IsOpdEmer);
						
						if(formBean.getStrRateVal().equals("0")){
							params.put("from_Date", strMonYear);
						}else if(formBean.getStrRateVal().equals("1")){
							params.put("from_Date", strCurrentDate);
						}
												
						params.put("report_id", strReportId);
					//	params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						
						
					}
					
					
					
					
					
					ts.displayReport(request, response, reportPath, reportFormat,
							params,strHospitalCode);

				} catch (Exception e) {
				
					strmsgText = "billing.reports.ChargeDetailRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"ChargeDetailRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;		
					}
			}
	
	
	
	

	public static void showReportTest(ChargeDetailRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String strmsgText = "";
				ChargeDetailRptBO bo = null;
				ChargeDetailRptVO vo = null;
				
				Map<String, Object> params = new HashMap<String, Object>();
			try {
					
				//	String strReportName = "";	
				bo = new ChargeDetailRptBO();
				vo = new ChargeDetailRptVO();
				
					String strChargeTypeId = formBean.getStrChargeTypeId();
					String strGroupId = formBean.getStrGroupNameId();
					String strPatientCatCode = formBean.getStrCategory();
					String strMonthVal = formBean.getStrMonthVal();
					String strYear = formBean.getStrYear();
					
					String strMonYear = strMonthVal+" "+strYear;
					
					String strHospitalCode = formBean.getStrHospitalCode();
					String strCategoryName = formBean.getStrCatCode();
					String strCurrentDate = formBean.getStrCurrentDate();
										
					String strReportId = formBean.getStrReportId();
					String strUserRemarks = formBean.getStrUserRemarks();
					
					
					
					vo.setStrHospitalCode(formBean.getStrHospitalCode());
					
					if(strChargeTypeId == null)
						strChargeTypeId = "0";
					vo.setStrChargeTypeId(strChargeTypeId);
					
					if(strPatientCatCode == null)
						strPatientCatCode = "0";
					vo.setStrCategory(strPatientCatCode);
					
					if(strGroupId == null)
						strGroupId = "0";
					vo.setStrGroupNameId(strGroupId);
					
					if(strMonthVal == null)
						strMonthVal = "0";
					vo.setStrMonth(strMonthVal);
					
					if(strYear == null)
						strYear = "0";
					vo.setStrYear(strYear);
					
					
					
				
					bo.getReportDetails(vo);

					if (vo.getStrMsgType().equals("1")) {
						throw new Exception(vo.getStrMsgString());

					}
					ResultSet hrs=null;
					
					hrs= (ResultSet)vo.getStrReportWs();
					
					HashMap paramsReport = new HashMap();
					paramsReport.put("paramsReport", hrs);
					
					
					reportFormat = formBean.getStrReportFormat();
					
				//	SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
					
					boolean footerVisible = true;
					boolean IsIpd = true;
					boolean IsOpdEmer = true;
	
					if (formBean.getStrIsFooter().equals("1")) {
						footerVisible = false;
					}


					String reportPath = "/billing/reports/chargeDetailNew_billrpt.rptdesign";
					
					/*if(strChargeTypeId.equals("2")){
						
						IsIpd = false;
						
						if(strGroupId.equals("0")){
							params.put("group_Id", "null");
						}else{
							params.put("group_Id", strGroupId);
						}
						
							params.put("chargeType_Name", "Ipd");
						
						params.put("category_Name", strCategoryName);

						params.put("cat_Code", strPatientCatCode);
						params.put("chargetype_Id", strChargeTypeId);
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Mon", "0");
						params.put("from_Year", "0");
						params.put("IsIpd", IsIpd);
						
						if(formBean.getStrRateVal().equals("0")){
							params.put("from_Date", strMonYear);
						}else if(formBean.getStrRateVal().equals("1")){
							params.put("from_Date", strCurrentDate);
						}
						
						params.put("report_id", strReportId);
					//	params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
					
					}else if(strChargeTypeId.equals("1") || strChargeTypeId.equals("3")){
						
						IsOpdEmer = false;
					
						if(strGroupId.equals("0")){
							params.put("group_Id", "0");
						}else{
							params.put("group_Id", strGroupId);
						}
						if(strChargeTypeId.equals("1")){
							params.put("chargeType_Name", "Opd");
						}else if(strChargeTypeId.equals("3")){
							params.put("chargeType_Name", "Emergency");
						}
						params.put("category_Name", strCategoryName);
						
						params.put("cat_Code", strPatientCatCode);
						params.put("hosp_Code", strHospitalCode);
						params.put("chargetype_Id", strChargeTypeId);
						params.put("from_Mon", "0");
						params.put("from_Year", "0");
						params.put("IsOpdEmer", IsOpdEmer);
						
						if(formBean.getStrRateVal().equals("0")){
							params.put("from_Date", strMonYear);
						}else if(formBean.getStrRateVal().equals("1")){
							params.put("from_Date", strCurrentDate);
						}
												
						params.put("report_id", strReportId);
					//	params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						
						
					}
					
					
					
					
					
					
else if(strChargeTypeId.equals("0"))
{
						
						IsOpdEmer = false;
					
						if(strGroupId.equals("0")){
							params.put("group_Id", "null");
						}else{
							params.put("group_Id", strGroupId);
						}
						
							params.put("chargeType_Name", "All Hospital Services");
						
						params.put("category_Name", strCategoryName);
						
						params.put("cat_Code", strPatientCatCode);
						params.put("hosp_Code", strHospitalCode);
						params.put("chargetype_Id", strChargeTypeId);
						params.put("from_Mon", "0");
						params.put("from_Year", "0");
						params.put("IsOpdEmer", IsOpdEmer);
						
						if(formBean.getStrRateVal().equals("0")){
							params.put("from_Date", strMonYear);
						}else if(formBean.getStrRateVal().equals("1")){
							params.put("from_Date", strCurrentDate);
						}
												
						params.put("report_id", strReportId);
					//	params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", strUserRemarks);
						
						
					}
					
					
					
					*/	
					
					ts.displayReportWithResultset(request, response, reportPath, reportFormat,
							paramsReport,strHospitalCode);

				} catch (Exception e) {
				
					strmsgText = "billing.reports.ChargeDetailRptDATA.showReport --> "
						+ e.getMessage();
					HisException eObj = new HisException("billing",
							"ChargeDetailRptDATA->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;		
					}
			}
	}
	

