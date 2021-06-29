package billing.reports;
/*
 * Bill Enquiry Report DATA
 * 
 * author: Debashis Sardar
 * 
 * dated: 05/03/2012
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.PatientDtlHLP;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import billing.BillConfigUtil;

public class BillEnquiryRptDATA {
	public static void getBillTypeDtls(BillEnquiryRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		BillEnquiryRptBO bo = null;

		BillEnquiryRptVO voObj = null;

		HisUtil util = null;
		String strBillTypeVal = "";
		String strmsgText = null;
		String getClientName="";

		try {
			
			bo = new BillEnquiryRptBO();
			voObj = new BillEnquiryRptVO();
			
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			bo.getBillTypeDtls(voObj);
			bo.getClientName(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","BillEnquiryRptDATA");
			strBillTypeVal = util.getOptionValue(voObj.getStrBillTypeWs(), "0",
					"0^All", false);
			getClientName = util.getOptionValue(voObj.getStrOrgCodeValuesWs(), "0",
					"0^All", false);

			formBean.setStrBillTypeValues(strBillTypeVal);
			formBean.setStrOrgCodeValues(getClientName);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrReportType("1");
			

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "billing.reports.BillEnquiryRptDATA.getDeptDetails --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"BillEnquiryRptDATA->getDeptDetails()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void getBillDetails(BillEnquiryRptFB formBean,HttpServletRequest request,
			HttpServletResponse response) {

		BillEnquiryRptBO bo = null;

		BillEnquiryRptVO voObj = null;

		HisUtil util = null;
		String strBillVal = "";
		String strmsgText = null;
		String strBillDtls="";
        String strBillType=request.getParameter("strBillType");
        String strCrNo=request.getParameter("strCrNo");
        String strEffectiveFrom=request.getParameter("strEffectiveFrom");
        String strEffectiveTo=request.getParameter("strEffectiveTo");
		try {
			
			bo = new BillEnquiryRptBO();
			voObj = new BillEnquiryRptVO();
			
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrCrNo(strCrNo);
			voObj.setStrBillType(strBillType);
			voObj.setStrEffectiveFrom(strEffectiveFrom);
			voObj.setStrEffectiveTo(strEffectiveTo);
			bo.getBillDetails(voObj);
			String strPatView = PatientDtlHLP.compactPatientWithAdmissionDtl(strCrNo,
					formBean.getStrHospitalCode(), false);

			formBean.setStrPatientDetailsView(strPatView);
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
					
			strBillDtls = BillEnquiryRptHLP.getBillDtls(voObj);
				
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strBillDtls+"^"+strPatView);
			
			if (voObj.getStrMsgType().equals("1")) { 
				throw new Exception(formBean.getStrMsgString());
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			String msgStr = e.getMessage();

			if (msgStr.contains("CR.")) {
                String strErr="ERROR";
				formBean.setStrErrMsg("Invalid CR. No./Data Not Found");
				try {
					response.getWriter().print(strErr);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			} else {

				HisException eObj = new HisException("Billing",
						"BillEnquiryRptDATA->getBillDetails()", msgStr);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "], Contact System Administrator! ");

				formBean.setStrCrNo("");
				eObj = null;

			}
			
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	public static void showReport(BillEnquiryRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
			ReportUtil ts = new ReportUtil();
			String reportFormat = "html";
			
			String strmsgText = "";
			BillEnquiryRptBO bo = null;
			BillEnquiryRptVO voObj = null;
			Map<String, Object> params = new HashMap<String, Object>();
			try 
			{
				bo = new BillEnquiryRptBO();
				voObj = new BillEnquiryRptVO();
			
				String strReportName = "Bill Wise Payment Details Report";								
				String strHospitalCode = formBean.getStrHospitalCode();
				String strReportId = formBean.getStrReportId();

				if(formBean.getStrReportType().equals("1"))//html
				{
					if(formBean.getStrReportingFormat().equalsIgnoreCase("test"))
					{
						reportFormat = "html";
					}
					else
					{
						reportFormat = "pdf";
					}
					
				}
				else
					reportFormat = "pdf";
				
				boolean footerVisible = true;
										
				voObj.setStrHospitalCode(formBean.getStrHospitalCode());
				voObj.setStrTransNo(formBean.getStrTransNo());
				voObj.setStrRcptNo(formBean.getStrRcptNo());
				bo.checkFinalBill(voObj);
				String strHospSerName=voObj.getStrHospSerName();
				String strServiceId=voObj.getStrServiceId();
				String reportPath="";
				
				
				if(strHospSerName.equals("2")/*&& strServiceId.equals("21")*/ )//Final Bill
					reportPath = "/billing/reports/PaymentDtl_billWiseFinal_billRpt.rptdesign";
				else
					reportPath = "/billing/reports/PaymentDtl_billWise_billeRpt.rptdesign";
				
				String strRcptNo=formBean.getStrRcptNo();
	                
	            if(strRcptNo.equals(""))
	               strRcptNo="1";
	            if(strRcptNo==null)
	               strRcptNo="1";
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("report_Footer_Visible", footerVisible);
				params.put("report_user_Remarks", "");
				params.put("hosp_Code", strHospitalCode);
				params.put("bill_no", formBean.getStrTransNo());
				params.put("rcpt_no", strRcptNo);
				params.put("service_id",strServiceId);
												
				ts.displayReportBilling(request, response, reportPath, reportFormat,params,strHospitalCode);						
					
			} 
			catch (Exception e) 
			{
					strmsgText = "billing.reports.BillEnquiryRptDATA.showReport --> "+ e.getMessage();
					HisException eObj = new HisException("billing","BillEnquiryRpt->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");	
					eObj = null;
			}
	}
	public static void generateReport(BillEnquiryRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) 
	{

				ReportUtil ts = new ReportUtil();
				String reportFormat = "html";
				String reportContent="";
				Map<String, Object> params = new HashMap<String, Object>();
				BillEnquiryRptVO voObj = null;
				String strmsgText = null;
				
				
			try 
			{
				voObj = new BillEnquiryRptVO();
				voObj.setStrHospitalCode(formBean.getStrHospitalCode());
				voObj.setBillNo(formBean.getChk());
				voObj.setBillDate(formBean.getBillDate());
			//	voObj.setSerialNo(formBean.getSerialNo());
				
				reportContent= BillEnquiryRptHLP.getBillContent(voObj);
				formBean.setReportContent(reportContent);
				/*String temp_bill="";
				String[] billno=formBean.getChk();
				
				for(int i=0;i<billno.length;i++)
				{
					if(i==0)
						temp_bill=billno[i];
					else
						temp_bill=temp_bill+","+billno[i];								
				}
				
				
				String strReportName = "Bill Details Report";								
					String strHospitalCode = formBean.getStrHospitalCode();
					String strReportId = formBean.getStrReportId();
					String strFromDate = formBean.getStrEffectiveFrom();
					String strToDate = formBean.getStrEffectiveTo();
					String strCrNo = formBean.getStrCrNo();
					String strBillType = formBean.getStrBillType();
					String strBillTypeName = formBean.getStrBillTypeName();
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
					reportFormat = formBean.getStrReportFormat();
					boolean footerVisible = true;
					
					
						String reportPath="";
						
							reportPath = "/billing/reports/billDetails_billRpt.rptdesign";
						
					
						params.put("report_id", strReportId);
						params.put("report_Name", strReportName);
						params.put("report_Footer_Visible", footerVisible);
						params.put("report_user_Remarks", "");
						params.put("hosp_Code", strHospitalCode);
						params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
						params.put("to_Date", sdf.format(sdf.parse(strToDate)));
						params.put("cr_No", strCrNo);
						params.put("bill_type", strBillType);
						params.put("bill_type_name", strBillTypeName);
						
						params.put("bill_no", temp_bill);
						
						ts.displayReport(request, response, reportPath, reportFormat,
								params);
						*/
					
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
					strmsgText = "billing.reports.BillEnquiryRptDATA.generateReport --> "+ e.getMessage();
					HisException eObj = new HisException("billing","BillEnquiryRpt->generateReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;
			}
		}
	
	public static void returnHTML(BillEnquiryRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
				String strBillDtls="";				
				String strmsgText = "";
				BillEnquiryRptBO bo = null;
				BillEnquiryRptVO voObj = null;
				Map<String, Object> params = new HashMap<String, Object>();
				
				try 
				{
						bo = new BillEnquiryRptBO();
						voObj = new BillEnquiryRptVO();
						
						String strBillNo=request.getParameter("strBillNo").toString();
						String strReceiptNo=request.getParameter("strReceiptNo").toString();
						String strHospCode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
						
						voObj.setStrHospitalCode(strHospCode);
						voObj.setStrTransNo(strBillNo);
						voObj.setStrRcptNo(strReceiptNo);
						
						bo.checkFinalBill(voObj);
						
						String strHospSerName=voObj.getStrHospSerName();
						String strServiceId=voObj.getStrServiceId();
						String reportPath="";
						
						//strHospSerName=2 (ipd) els opd
						bo.getBillData(voObj);
						
						//move to hlp to make n return html from the bill data received..
						BillEnquiryRptHLP.getBillHTML(voObj);
						
						strBillDtls = BillEnquiryRptHLP.getBillHTML(voObj);
						
						response.setHeader("Cache-Control", "no-cache");
						response.getWriter().print(strBillDtls);
						
						
					
				} catch (Exception e) 
				{
					e.printStackTrace();
					strmsgText = "billing.reports.BillEnquiryRptDATA.returnHTML --> "+ e.getMessage();
					HisException eObj = new HisException("billing","BillEnquiryRpt->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
					eObj = null;
				}
			}
	
public static void showConsolidatedCreditReport(BillEnquiryRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
{

	ReportUtil ts = new ReportUtil();
	String reportFormat = "html";
	String strmsgText = "";
	
	Map<String, Object> params = new HashMap<String, Object>();
	BillConfigUtil billConfigUtil = null;
	try 
	{
		BillConfigUtil brt = new BillConfigUtil(formBean.getStrHospitalCode());
		String islogoreq=brt.getLogoReq();
		String strReportName = "OP CHARGES ACCOUNT FOR CREDIT";	
		String strReportId = formBean.getStrReportId();
		String strUserRemarks = formBean.getStrUserRemarks();
		String strFromDate = formBean.getStrEffectiveFrom();
		String strToDate = formBean.getStrEffectiveTo();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		reportFormat = formBean.getStrReportFormat();
		boolean footerVisible = true;
		
		if (formBean.getStrIsFooter().equals("1")) 
		{
			footerVisible = false;
		}
		
		if(formBean.getStrConsolidatedCreditReport().equals("1"))
		{					
			String reportPath = "/billing/reports/consolidatedCreditReport_billrpt.rptdesign";
			/*if(formBean.getStrOrgCode().equals("0"))
			{
				params.put("orgCode", "null");
			}
			else
			{*/
				//params.put("orgCode", formBean.getStrOrgCode());
			//}	
			if(formBean.getStrCrNo1().length()<15)
				formBean.setStrCrNo1("0");
			params.put("crno", formBean.getStrCrNo1());
			params.put("report_id", strReportId);
			params.put("report_Name", strReportName);
			params.put("report_Footer_Visible", footerVisible);
			params.put("report_user_Remarks", strUserRemarks);
			params.put("hosp_Code", formBean.getStrHospitalCode());
			params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
			params.put("to_Date", sdf.format(sdf.parse(strToDate)));
			params.put("seatID", "null");
			params.put("islogoreq", islogoreq);
			System.out.println("islogoreq"+islogoreq);
			
			ts.displayReport(request, response, reportPath, reportFormat,params,formBean.getStrHospitalCode());
	}
			
		
	} 
	catch (Exception e) 
	{
		strmsgText = "billing.reports.FeeCollectionDetailsRptDATA.showReport --> "+ e.getMessage();
		HisException eObj = new HisException("billing","FeeCollectionDetailsRptDATA->showReport()", strmsgText);
		formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
		eObj = null;			
	}
}

}
