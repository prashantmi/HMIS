package mms.reports.controller.data;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.reports.bo.PendingIndentStatusRecordRptBO;
import mms.reports.controller.fb.PendingIndentStatusRecordRptFB;
import mms.reports.vo.PendingIndentStatusRecordRptVO;

public class PendingIndentStatusRecordRptDATA {
	
	public static void getStoreList(PendingIndentStatusRecordRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PendingIndentStatusRecordRptBO bo = null;
		PendingIndentStatusRecordRptVO voObj = null;
		String strmsgText = null;
		String strStoreVal = "";
		HisUtil util = null;
		try {

			bo = new PendingIndentStatusRecordRptBO();
			voObj = new PendingIndentStatusRecordRptVO();
		//	String strUserLevel = request.getSession().getAttribute("USER_LEVEL").toString();
			
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatId());
			voObj.setStrItemCatId("10");

			/*if(strUserLevel.equals("6")){
				voObj.setStrMode("6");
			}
			else*/
				voObj.setStrMode("8"); // role based stores
			bo.getStoreList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Transactions", "PendingIndentStatusRecordRptDATA");
			
			strStoreVal = util.getOptionValue(voObj.getStrStoreWs(), "-1", "-1^SelectValue", false);
						
			formBean.setStrStoreValues(strStoreVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.transactions.PendingIndentStatusRecordRptDATA.getStoreList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"PendingIndentStatusRecordRptDATA->getStoreList()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
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
	
	public static void getItemCatList(PendingIndentStatusRecordRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PendingIndentStatusRecordRptBO bo = null;
		PendingIndentStatusRecordRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {

			bo = new PendingIndentStatusRecordRptBO();
			voObj = new PendingIndentStatusRecordRptVO();
			
			String strStoreId = request.getParameter("storeId");
			
			if (strStoreId == null)
				strStoreId = "0";
			
			voObj.setStrStoreId(strStoreId);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrMode("2");
			bo.getItemCatList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Transactions", "PendingIndentStatusRecordRptDATA");
			String temp = "<option value='0'>SelectValue</option>";

			if (voObj.getStrItemCatWs().size() != 0) {
				
				temp = util.getOptionValue(voObj.getStrItemCatWs(), "0", "0^SelectValue",
						true);

			}else{
				
				temp = "<option value='0'>SelectValue</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.transactions.PendingIndentStatusRecordRptDATA.getItemCatList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"PendingIndentStatusRecordRptDATA->getItemCatList()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
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
	
	public static void getReqTypeList(PendingIndentStatusRecordRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		PendingIndentStatusRecordRptBO bo = null;
		PendingIndentStatusRecordRptVO voObj = null;
		String strmsgText = null;
		HisUtil util = null;
		try {

			bo = new PendingIndentStatusRecordRptBO();
			voObj = new PendingIndentStatusRecordRptVO();
			
			String strStoreId = request.getParameter("storeId");
			
			if (strStoreId == null)
				strStoreId = "0";
			
			//added by vipul on 10.05.2021
			if (strStoreId.equals("0")){
				voObj.setStrMode("3");
				}
			else {
				voObj.setStrMode("2");
				}
			// ended by vipul on 10.05.2021
			voObj.setStrStoreId(request.getParameter("storeId"));
			voObj.setStrItemCatId(request.getParameter("catId"));
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			
			bo.getReqTypeList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Transactions", "PendingIndentStatusRecordRptDATA");
			String temp = "<option value='0'>SelectValue</option>";

			if (voObj.getStrReqTypeWs().size() != 0) {
				
				temp = util.getOptionValue(voObj.getStrReqTypeWs(), "0", "0^SelectValue",
						true);

			}else{
				
				temp = "<option value='0'>SelectValue</option>";
			}

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.transactions.PendingIndentStatusRecordRptDATA.getItemCatList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"PendingIndentStatusRecordRptDATA->getItemCatList()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
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
	
	public static void showReport(PendingIndentStatusRecordRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "html";
		String strReportName;
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strStoreId = formBean.getStrStoreId();
			String strCatCode = formBean.getStrItemCatNo();
			String strFromDate = formBean.getStrFromDate();
			String strToDate = formBean.getStrToDate();
			String strUserRemarks = formBean.getStrUserRemarks();
			String strReqTypeId = formBean.getStrReqTypeId();
			String strRptType = formBean.getStrRptType();
			
			String strStoreName= formBean.getFilter();
			String strIsConsolidated=formBean.getStrIsConsolidated();
			
			reportFormat = formBean.getStrReportFormat();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			boolean footerVisible = true;
			
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
				
			}
			
			//added strStoreName on 10.05.2021 by vipul
		if(strIsConsolidated.equals("3"))
		{
			if(strStoreId==null || strStoreId.equals(""))
			{
				strStoreId="0";
			}
			if(strRptType.equals("1"))
			{
			
					if(strReqTypeId.equals("17")){
						
						 strReportName = "Not Issued Drugs/Items ( Consolidated / Indent For Issue(Dept))";
						String reportPath = "/mms/reports/pendingndentstatusConsolidated2_mmsrpt.rptdesign";
						
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("hospCode", strHospitalCode);
							params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
							params.put("toDate", sdf.format(sdf.parse(strToDate)));
							params.put("storeId", strStoreId);
							params.put("catCode", strCatCode);
							params.put("reqTypeId", strReqTypeId);
						     params.put("report_Fix_Header","Header");
						ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
						
					}
					else 
						if(strReqTypeId.equals("11")||strReqTypeId.equals("86"))
					{
							if(strReqTypeId.equals("11"))
							{	
						         strReportName = "Not Issued Drugs/Items ( Consolidated / Indent For Annual Purchase)";
							}
							else
							{
								 strReportName = "Not Issued Drugs/Items Consolidated";	
							}
							String reportPath = "/mms/reports/pendingndentstatusConsolidated2_mmsrpt.rptdesign";
							
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
								
								System.out.println("report_id"+ strReportId);
								System.out.println("report_Name"+ strReportName);
								System.out.println("report_Footer_Visible"+ footerVisible);
								System.out.println("report_user_Remarks"+ strUserRemarks);
								System.out.println("hospCode"+ strHospitalCode);
								System.out.println("fromDate"+ sdf.format(sdf.parse(strFromDate)));
								System.out.println("toDate"+ sdf.format(sdf.parse(strToDate)));
								System.out.println("storeId"+ strStoreId);
								System.out.println("catCode"+ strCatCode);
								System.out.println("reqTypeId"+ strReqTypeId);
								
							    params.put("report_Fix_Header","Header");
							ts.displayReport(request, response, reportPath, reportFormat,
									params,strHospitalCode);
							
					}else if(strReqTypeId.equals("12")||strReqTypeId.equals("13")||strReqTypeId.equals("14")||strReqTypeId.equals("10")){
						  
						if(strReqTypeId.equals("12")||strReqTypeId.equals("13")||strReqTypeId.equals("14")){
							 strReportName = "Not Issued Drugs/Items ( Consolidated / Indent For Patient IP)";
							params.put("report_Name", strReportName);
						}else{
							 strReportName = "Not Issued Drugs/Items ( Consolidated / Indent For Contigency)";
							params.put("report_Name", strReportName);
						}
						
						String reportPath = "/mms/reports/pendingndentstatusConsolidated2_mmsrpt.rptdesign";
						
								params.put("report_id", strReportId);
								
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
								params.put("report_Fix_Header","Header");
					
					ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
					
				    }else if(strReqTypeId.equals("15")){
						  
				    	
				    	 strReportName = "Not Issued Drugs/Items ( Consolidated / Indent For Purchase-Imported)";
				    	String reportPath = "/mms/reports/pendingndentstatusConsolidated2_mmsrpt.rptdesign";
							
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("hospCode", strHospitalCode);
							params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
							params.put("toDate", sdf.format(sdf.parse(strToDate)));
							params.put("storeId", strStoreId);
							params.put("catCode", strCatCode);
							params.put("reqTypeId", strReqTypeId);
				
					ts.displayReport(request, response, reportPath, reportFormat,
							params,strStoreId);
					
				    }else if(strReqTypeId.equals("16")||strReqTypeId.equals("19")){
						  
				    	
				    	 strReportName = "Not Issued Drugs/Items ( Consolidated / Condemnation Request)";
				    	String reportPath = "/mms/reports/pendingndentstatusConsolidated2_mmsrpt.rptdesign";
							
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("hospCode", strHospitalCode);
							params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
							params.put("toDate", sdf.format(sdf.parse(strToDate)));
							params.put("storeId", strStoreId);
							params.put("catCode", strCatCode);
							params.put("reqTypeId", strReqTypeId);
							params.put("report_Fix_Header","Header");
				
					ts.displayReport(request, response, reportPath, reportFormat,
							params,strStoreId);
					
				   } 
			}		
				    else if(strRptType.equals("2"))
				    {

						if(strReqTypeId.equals("17")){
							
							 strReportName = "Issued Drugs/Items ( Consolidated / Indent For Issue(Dept))";
							String reportPath = "/mms/reports/pendingndentstatusConsolidated_mmsrpt.rptdesign";
							
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
							     params.put("report_Fix_Header","Header");
							ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
							
						}
						else 
							if(strReqTypeId.equals("11")||strReqTypeId.equals("86"))
						{
								if(strReqTypeId.equals("11"))
								{	
							         strReportName = "Issued Drugs/Items ( Consolidated / Indent For Annual Purchase)";
								}
								else
								{
									 strReportName = "Issued Drugs/Items Consolidated";	
								}
								String reportPath = "/mms/reports/pendingndentstatusConsolidated_mmsrpt.rptdesign";
								
									params.put("report_id", strReportId);
									params.put("report_Name", strReportName);
									params.put("report_Footer_Visible", footerVisible);
									params.put("report_user_Remarks", strUserRemarks);
									params.put("hospCode", strHospitalCode);
									params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
									params.put("toDate", sdf.format(sdf.parse(strToDate)));
									params.put("storeId", strStoreId);
									params.put("catCode", strCatCode);
									params.put("reqTypeId", strReqTypeId);
									
									System.out.println("report_id"+ strReportId);
									System.out.println("report_Name"+ strReportName);
									System.out.println("report_Footer_Visible"+ footerVisible);
									System.out.println("report_user_Remarks"+ strUserRemarks);
									System.out.println("hospCode"+ strHospitalCode);
									System.out.println("fromDate"+ sdf.format(sdf.parse(strFromDate)));
									System.out.println("toDate"+ sdf.format(sdf.parse(strToDate)));
									System.out.println("storeId"+ strStoreId);
									System.out.println("catCode"+ strCatCode);
									System.out.println("reqTypeId"+ strReqTypeId);
									
								    params.put("report_Fix_Header","Header");
								ts.displayReport(request, response, reportPath, reportFormat,
										params,strHospitalCode);
								
						}else if(strReqTypeId.equals("12")||strReqTypeId.equals("13")||strReqTypeId.equals("14")||strReqTypeId.equals("10")){
							  
							if(strReqTypeId.equals("12")||strReqTypeId.equals("13")||strReqTypeId.equals("14")){
								 strReportName = "Issued Drugs/Items ( Consolidated / Indent For Patient IP)";
								params.put("report_Name", strReportName);
							}else{
								 strReportName = "Issued Drugs/Items ( Consolidated / Indent For Contigency)";
								params.put("report_Name", strReportName);
							}
							
							String reportPath = "/mms/reports/pendingndentstatusConsolidated_mmsrpt.rptdesign";
							
									params.put("report_id", strReportId);
									
									params.put("report_Footer_Visible", footerVisible);
									params.put("report_user_Remarks", strUserRemarks);
									params.put("hospCode", strHospitalCode);
									params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
									params.put("toDate", sdf.format(sdf.parse(strToDate)));
									params.put("storeId", strStoreId);
									params.put("catCode", strCatCode);
									params.put("reqTypeId", strReqTypeId);
									params.put("report_Fix_Header","Header");
						
						ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
						
					    }else if(strReqTypeId.equals("15")){
							  
					    	
					    	 strReportName = "Issued Drugs/Items ( Consolidated / Indent For Purchase-Imported)";
					    	String reportPath = "/mms/reports/pendingndentstatusConsolidated_mmsrpt.rptdesign";
								
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
								params.put("report_Fix_Header","Header");
					
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strStoreId);
						
					    }else if(strReqTypeId.equals("16")||strReqTypeId.equals("19")){
							  
					    	
					    	 strReportName = "Issued Drugs/Items ( Consolidated / Condemnation Request)";
					    	String reportPath = "/mms/reports/pendingndentstatusConsolidated_mmsrpt.rptdesign";
								
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
								params.put("report_Fix_Header","Header");
					
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strStoreId);
						
					   } 
				    }
			
		}	
		
		else{
			
	
			if(strRptType.equals("1"))
			{
			
					if(strReqTypeId.equals("17")){
						
						 strReportName = "Not Issued Drugs/Items ( "+strStoreName+" / Indent For Issue(Dept))";
						String reportPath = "/mms/reports/pendingndentstatus_mmsrpt.rptdesign";
						
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("hospCode", strHospitalCode);
							params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
							params.put("toDate", sdf.format(sdf.parse(strToDate)));
							params.put("storeId", strStoreId);
							params.put("catCode", strCatCode);
							params.put("reqTypeId", strReqTypeId);
						     params.put("report_Fix_Header","Header");
						ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
						
					}
					else 
						if(strReqTypeId.equals("11")||strReqTypeId.equals("86"))
					{
							if(strReqTypeId.equals("11"))
							{	
						         strReportName = "Not Issued Drugs/Items ( "+strStoreName+" / Indent For Annual Purchase)";
							}
							else
							{
								 strReportName = "Not Issued Drugs/Items  "+strStoreName+" ";	
							}
							String reportPath = "/mms/reports/pendingndentstatus1_mmsrpt.rptdesign";
							
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
								
								System.out.println("report_id"+ strReportId);
								System.out.println("report_Name"+ strReportName);
								System.out.println("report_Footer_Visible"+ footerVisible);
								System.out.println("report_user_Remarks"+ strUserRemarks);
								System.out.println("hospCode"+ strHospitalCode);
								System.out.println("fromDate"+ sdf.format(sdf.parse(strFromDate)));
								System.out.println("toDate"+ sdf.format(sdf.parse(strToDate)));
								System.out.println("storeId"+ strStoreId);
								System.out.println("catCode"+ strCatCode);
								System.out.println("reqTypeId"+ strReqTypeId);
								
							    params.put("report_Fix_Header","Header");
							ts.displayReport(request, response, reportPath, reportFormat,
									params,strHospitalCode);
							
					}else if(strReqTypeId.equals("12")||strReqTypeId.equals("13")||strReqTypeId.equals("14")||strReqTypeId.equals("10")){
						  
						if(strReqTypeId.equals("12")||strReqTypeId.equals("13")||strReqTypeId.equals("14")){
							 strReportName = "Not Issued Drugs/Items ( "+strStoreName+" / Indent For Patient IP)";
							params.put("report_Name", strReportName);
						}else{
							 strReportName = "Not Issued Drugs/Items ( "+strStoreName+" / Indent For Contigency)";
							params.put("report_Name", strReportName);
						}
						
						String reportPath = "/mms/reports/pendingndentstatus2_mmsrpt.rptdesign";
						
								params.put("report_id", strReportId);
								
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
								params.put("report_Fix_Header","Header");
					
					ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
					
				    }else if(strReqTypeId.equals("15")){
						  
				    	
				    	 strReportName = "Not Issued Drugs/Items ( "+strStoreName+" / Indent For Purchase-Imported)";
				    	String reportPath = "/mms/reports/pendingndentstatus3_mmsrpt.rptdesign";
							
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("hospCode", strHospitalCode);
							params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
							params.put("toDate", sdf.format(sdf.parse(strToDate)));
							params.put("storeId", strStoreId);
							params.put("catCode", strCatCode);
							params.put("reqTypeId", strReqTypeId);
				
					ts.displayReport(request, response, reportPath, reportFormat,
							params,strStoreId);
					
				    }else if(strReqTypeId.equals("16")||strReqTypeId.equals("19")){
						  
				    	
				    	 strReportName = "Not Issued Drugs/Items ( "+strStoreName+" / Condemnation Request)";
				    	String reportPath = "/mms/reports/pendingndentstatus4_mmsrpt.rptdesign";
							
							params.put("report_id", strReportId);
							params.put("report_Name", strReportName);
							params.put("report_Footer_Visible", footerVisible);
							params.put("report_user_Remarks", strUserRemarks);
							params.put("hospCode", strHospitalCode);
							params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
							params.put("toDate", sdf.format(sdf.parse(strToDate)));
							params.put("storeId", strStoreId);
							params.put("catCode", strCatCode);
							params.put("reqTypeId", strReqTypeId);
							params.put("report_Fix_Header","Header");
				
					ts.displayReport(request, response, reportPath, reportFormat,
							params,strStoreId);
					
				   } 
			}		
				    else if(strRptType.equals("2"))
				    {

						if(strReqTypeId.equals("17")){
							
							 strReportName = "Issued Drugs/Items ( "+strStoreName+" / Indent For Issue(Dept))";
							String reportPath = "/mms/reports/pendingndentstatus5_mmsrpt.rptdesign";
							
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
							     params.put("report_Fix_Header","Header");
							ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
							
						}
						else 
							if(strReqTypeId.equals("11")||strReqTypeId.equals("86"))
						{
								if(strReqTypeId.equals("11"))
								{	
							         strReportName = "Issued Drugs/Items ( "+strStoreName+" / Indent For Annual Purchase)";
								}
								else
								{
									 strReportName = "Issued Drugs/Items  "+strStoreName+"";	
								}
								String reportPath = "/mms/reports/pendingndentstatus6_mmsrpt.rptdesign";
								
									params.put("report_id", strReportId);
									params.put("report_Name", strReportName);
									params.put("report_Footer_Visible", footerVisible);
									params.put("report_user_Remarks", strUserRemarks);
									params.put("hospCode", strHospitalCode);
									params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
									params.put("toDate", sdf.format(sdf.parse(strToDate)));
									params.put("storeId", strStoreId);
									params.put("catCode", strCatCode);
									params.put("reqTypeId", strReqTypeId);
									
									System.out.println("report_id"+ strReportId);
									System.out.println("report_Name"+ strReportName);
									System.out.println("report_Footer_Visible"+ footerVisible);
									System.out.println("report_user_Remarks"+ strUserRemarks);
									System.out.println("hospCode"+ strHospitalCode);
									System.out.println("fromDate"+ sdf.format(sdf.parse(strFromDate)));
									System.out.println("toDate"+ sdf.format(sdf.parse(strToDate)));
									System.out.println("storeId"+ strStoreId);
									System.out.println("catCode"+ strCatCode);
									System.out.println("reqTypeId"+ strReqTypeId);
									
								    params.put("report_Fix_Header","Header");
								ts.displayReport(request, response, reportPath, reportFormat,
										params,strHospitalCode);
								
						}else if(strReqTypeId.equals("12")||strReqTypeId.equals("13")||strReqTypeId.equals("14")||strReqTypeId.equals("10")){
							  
							if(strReqTypeId.equals("12")||strReqTypeId.equals("13")||strReqTypeId.equals("14")){
								 strReportName = "Issued Drugs/Items ( "+strStoreName+" / Indent For Patient IP)";
								params.put("report_Name", strReportName);
							}else{
								 strReportName = "Issued Drugs/Items ( "+strStoreName+" / Indent For Contigency)";
								params.put("report_Name", strReportName);
							}
							
							String reportPath = "/mms/reports/pendingndentstatus7_mmsrpt.rptdesign";
							
									params.put("report_id", strReportId);
									
									params.put("report_Footer_Visible", footerVisible);
									params.put("report_user_Remarks", strUserRemarks);
									params.put("hospCode", strHospitalCode);
									params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
									params.put("toDate", sdf.format(sdf.parse(strToDate)));
									params.put("storeId", strStoreId);
									params.put("catCode", strCatCode);
									params.put("reqTypeId", strReqTypeId);
									params.put("report_Fix_Header","Header");
						
						ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
						
					    }else if(strReqTypeId.equals("15")){
							  
					    	
					    	 strReportName = "Issued Drugs/Items ( "+strStoreName+" / Indent For Purchase-Imported)";
					    	String reportPath = "/mms/reports/pendingndentstatus8_mmsrpt.rptdesign";
								
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
								params.put("report_Fix_Header","Header");
					
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strStoreId);
						
					    }else if(strReqTypeId.equals("16")||strReqTypeId.equals("19")){
							  
					    	
					    	 strReportName = "Issued Drugs/Items ( "+strStoreName+" / Condemnation Request)";
					    	String reportPath = "/mms/reports/pendingndentstatus9_mmsrpt.rptdesign";
								
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
								params.put("report_Fix_Header","Header");
					
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strStoreId);
						
					   } 
				    }
				    else
				    {

						if(strReqTypeId.equals("17")){
							
							 strReportName = "Issued as well as Not Issued Drugs/Items ( "+strStoreName+" / Indent For Issue(Dept))";
							String reportPath = "/mms/reports/pendingndentstatus_mmsrpt10.rptdesign";
							
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
							     params.put("report_Fix_Header","Header");
							ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
							
						}
						else 
							if(strReqTypeId.equals("11")||strReqTypeId.equals("86"))
						{
								if(strReqTypeId.equals("11"))
								{	
							         strReportName = "Issued as well as Not Issued Drugs/Items ( "+strStoreName+" / Indent For Annual Purchase)";
								}
								else
								{
									 strReportName = "Issued as well as Not Issued Drugs/Items  "+strStoreName+"";	
								}
								String reportPath = "/mms/reports/pendingndentstatus11_mmsrpt.rptdesign";
								
									params.put("report_id", strReportId);
									params.put("report_Name", strReportName);
									params.put("report_Footer_Visible", footerVisible);
									params.put("report_user_Remarks", strUserRemarks);
									params.put("hospCode", strHospitalCode);
									params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
									params.put("toDate", sdf.format(sdf.parse(strToDate)));
									params.put("storeId", strStoreId);
									params.put("catCode", strCatCode);
									params.put("reqTypeId", strReqTypeId);
									
									System.out.println("report_id"+ strReportId);
									System.out.println("report_Name"+ strReportName);
									System.out.println("report_Footer_Visible"+ footerVisible);
									System.out.println("report_user_Remarks"+ strUserRemarks);
									System.out.println("hospCode"+ strHospitalCode);
									System.out.println("fromDate"+ sdf.format(sdf.parse(strFromDate)));
									System.out.println("toDate"+ sdf.format(sdf.parse(strToDate)));
									System.out.println("storeId"+ strStoreId);
									System.out.println("catCode"+ strCatCode);
									System.out.println("reqTypeId"+ strReqTypeId);
									
								    params.put("report_Fix_Header","Header");
								ts.displayReport(request, response, reportPath, reportFormat,
										params,strHospitalCode);
								
						}else if(strReqTypeId.equals("12")||strReqTypeId.equals("13")||strReqTypeId.equals("14")||strReqTypeId.equals("10")){
							  
							if(strReqTypeId.equals("12")||strReqTypeId.equals("13")||strReqTypeId.equals("14")){
								 strReportName = "Issued as well as Not Issued Drugs/Items ( "+strStoreName+" / Indent For Patient IP)";
								params.put("report_Name", strReportName);
							}else{
								 strReportName = "Issued as well as Not Issued Drugs/Items ( "+strStoreName+" / Indent For Contigency)";
								params.put("report_Name", strReportName);
							}
							
							String reportPath = "/mms/reports/pendingndentstatus12_mmsrpt.rptdesign";
							
									params.put("report_id", strReportId);
									
									params.put("report_Footer_Visible", footerVisible);
									params.put("report_user_Remarks", strUserRemarks);
									params.put("hospCode", strHospitalCode);
									params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
									params.put("toDate", sdf.format(sdf.parse(strToDate)));
									params.put("storeId", strStoreId);
									params.put("catCode", strCatCode);
									params.put("reqTypeId", strReqTypeId);
									params.put("report_Fix_Header","Header");
						
						ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
						
					    }else if(strReqTypeId.equals("15")){
							  
					    	
					    	 strReportName = "Issued as well as Not Issued Drugs/Items ( "+strStoreName+" / Indent For Purchase-Imported)";
					    	String reportPath = "/mms/reports/pendingndentstatus13_mmsrpt.rptdesign";
								
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
								params.put("report_Fix_Header","Header");
					
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strStoreId);
						
					    }else if(strReqTypeId.equals("16")||strReqTypeId.equals("19")){
							  
					    	
					    	 strReportName = "Issued as well as Not Issued Drugs/Items ( "+strStoreName+" / Condemnation Request)";
					    	String reportPath = "/mms/reports/pendingndentstatus14_mmsrpt.rptdesign";
								
								params.put("report_id", strReportId);
								params.put("report_Name", strReportName);
								params.put("report_Footer_Visible", footerVisible);
								params.put("report_user_Remarks", strUserRemarks);
								params.put("hospCode", strHospitalCode);
								params.put("fromDate", sdf.format(sdf.parse(strFromDate)));
								params.put("toDate", sdf.format(sdf.parse(strToDate)));
								params.put("storeId", strStoreId);
								params.put("catCode", strCatCode);
								params.put("reqTypeId", strReqTypeId);
								params.put("report_Fix_Header","Header");
					
						ts.displayReport(request, response, reportPath, reportFormat,
								params,strStoreId);
						
					   } 
				    }
		}
		} catch (Exception e) {

			e.printStackTrace();

		}
	}


}
