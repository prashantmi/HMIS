package billing.reports;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

public class PaymentDtlRptDATA {

	public static void initReportPage(PaymentDtlRptFB formBean,HttpServletRequest request) {

		PaymentDtlRptBO bo = null;

		PaymentDtlRptVO voObj = null;

		HisUtil util = null;
		String strHospSerVal = "";
		String strmsgText = null;
		String strHospNameValues="";
		String temp = "";
		String strFeeClerkValues="";


		try {
			
			bo = new PaymentDtlRptBO();
			voObj = new PaymentDtlRptVO();
			
			//voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			 voObj.setStrHospitalCode((formBean.getStrHospitalCode()==null || formBean.getStrHospitalCode()=="" )? request.getSession().getAttribute("HOSPITAL_CODE").toString():formBean.getStrHospitalCode());
			 voObj.setStrSeatId(request.getSession().getAttribute("SEATID").toString());	
				
			// bo.getHospitalName(voObj);
		//	bo.getHospSerDetails(voObj);
          bo.getHospitalName(voObj);
          bo.getFeeClerkList(voObj);
          
			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("Billing Reports","PaymentDtlRptDATA");
			
			
			
			
			
			
			  strHospNameValues = util.getOptionValue(voObj.getStrHospitalWs(), request.getSession().getAttribute("HOSPITAL_CODE").toString(),"0^All", false);	
			  
			  strFeeClerkValues=util.getOptionValue(voObj.getStrClerkWs(),formBean.getStrFeeClerk(),"0^All", false );

				
				strHospSerVal = util.getOptionValue(voObj.getStrHospSerWs(), "0","0^All", false);
				
				/*added for: 'payment mode' combo,  by: manisha gangwar dt: 23.8.18*/
				bo.getPaymentModeList(voObj); 
				if (voObj.getStrPaymentModeList() != null && voObj.getStrPaymentModeList().size() != 0)
				{
					temp = util.getOptionValue(voObj.getStrPaymentModeList(), "0","0^All", false);
					formBean.setStrPaymentModeContents(temp);
				}
				
				/*end */ 
				
				formBean.setStrHospNameValues(strHospNameValues);
				formBean.setStrHospSerValues(strHospSerVal);
				formBean.setStrFeeClerkValues(strFeeClerkValues);
			
			
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));

		} catch (Exception e) {
			strmsgText = "billing.reports.PaymentDtlRptDATA.initReportPage --> "
					+ e.getMessage();
			HisException eObj = new HisException("billing",
					"PaymentDtlRpt->initReportPage()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			voObj = null;
			util = null;
		}

	}
	
	
	public static void showReport(PaymentDtlRptFB formBean,HttpServletRequest request, HttpServletResponse response) 
	{
		ReportUtil ts = new ReportUtil();
		String reportFormat = "html";
		String strmsgText = "";
		
		Map<String, Object> params = new HashMap<String, Object>();
		try 
		{					
			String strReportName = "Complete Payment Detail Report";								
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
			String strUserRemarks = formBean.getStrUserRemarks();
			String strHospSerName = formBean.getStrHospSerName();
			String strFromDate = formBean.getStrEffectiveFrom();
			String strToDate = formBean.getStrEffectiveTo();
			String strCrNo = formBean.getStrCrNo();
			String strPatName = formBean.getStrPatName();
			String strBillNo = formBean.getStrBillNo();
			String strReceiptNo = formBean.getStrReceiptNo();
			String strHeaderHospCode="100";
			String strPaymentMode="";
			String seat_id=formBean.getStrFeeClerk();
			
			//System.out.println("formBean.getStrPaymentMode().length"+formBean.getStrPaymentMode().length);
			if (formBean.getStrPaymentMode().length > 0) 
			{
	            /*StringBuilder sb = new StringBuilder();

	            for (String s : formBean.getStrPaymentMode()) 
	            {
	            	
	                sb.append(s).append(",");
	            }
	            strPaymentMode = sb.deleteCharAt(sb.length() - 1).toString();*/
	            
				for (String s : formBean.getStrPaymentMode()) 
	            {
					strPaymentMode=strPaymentMode+s.split("#")[0]+",";	
	            }
				strPaymentMode = strPaymentMode.substring(0,(strPaymentMode.length()-1));
	            
	        }
			
			//System.out.println("strPaymentMode"+strPaymentMode);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			reportFormat = formBean.getStrReportFormat();
			params.put("report_formattype", reportFormat);
			boolean footerVisible = true;
			
			/*strPaymentMode=strPaymentMode.split(",")[0];
			
			strPaymentMode=strPaymentMode.split("#")[0];	*/		
			
			if(formBean.getStrAllHospCode().equalsIgnoreCase("testCode"))//multi hosp or single selected (ALL not selected)
			{
				strHospitalCode= formBean.getStrHospitalCode();
			
				if(formBean.getStrHospitalCode().contains(","))//multi hosp sel
				{
					//since multiple hospitals are selected
					strHeaderHospCode="100";
				}
				else
				{
					strHeaderHospCode=formBean.getStrHospitalCode();
				}
			}
			else //all selected
			{
				strHospitalCode=formBean.getStrAllHospCode();
				strHeaderHospCode="100";
			}
			if (formBean.getStrIsFooter().equals("1")) 
			{
				footerVisible = false;
			}			
			if(formBean.getStrCase().equals("1"))
			{
				
				String reportPath = "/billing/reports/paymentDtl1New_billrpt.rptdesign";

				if(strHospSerName.equals("0"))
				{
					params.put("chargeType_Name", "All");
				}
				else if(strHospSerName.equals("1"))
				{
					params.put("chargeType_Name", "Opd Morning");
				}
				else if(strHospSerName.equals("2"))
				{
					params.put("chargeType_Name", "Ipd");
				}
				else if(strHospSerName.equals("3"))
				{
					params.put("chargeType_Name", "Emergency");
				}
				else if(strHospSerName.equals("4"))
				{
					params.put("chargeType_Name", "Opd Special");
				}
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("report_Footer_Visible", footerVisible);
				params.put("report_user_Remarks", strUserRemarks);
				params.put("hosp_Code",strHospitalCode);
				params.put("from_Date", strFromDate);
				params.put("to_Date", strToDate);
				params.put("cr_No", strCrNo);
				params.put("pat_Name", "A");
				if(strHospSerName.equals("0"))
				{
					params.put("charge_Type_Id", "0");
				}
				else
				{
					params.put("charge_Type_Id", strHospSerName);
				}
				
				/*added for: 'payment mode' combo,  by: manisha gangwar dt: 23.8.18*/
				
				if(strPaymentMode.equals("0"))
				{
					params.put("payment_mode_Id", "0");
				}
				else
				{
					params.put("payment_mode_Id", strPaymentMode);
				}
				/*end*/
					ts.displayReport(request, response, reportPath, reportFormat,params,strHeaderHospCode);				
			}
			else if(formBean.getStrCase().equals("2"))
			{
			
					String reportPath = "/billing/reports/paymentDtl2New_billrpt.rptdesign";
					if(strHospSerName.equals("0"))
					{
						params.put("chargeType_Name", "All");
					}
					else if(strHospSerName.equals("1"))
					{
						params.put("chargeType_Name", "Opd Morning");
					}
					else if(strHospSerName.equals("2"))
					{
						params.put("chargeType_Name", "Ipd");
					}
					else if(strHospSerName.equals("3"))
					{
						params.put("chargeType_Name", "Emergency");
					}
					else if(strHospSerName.equals("4"))
					{
						params.put("chargeType_Name", "Opd Special");
					}
					params.put("hosp_Code", strHospitalCode);
					params.put("report_id", strReportId);
					params.put("report_Name", strReportName);
					params.put("report_Footer_Visible", footerVisible);
					params.put("report_user_Remarks", strUserRemarks);
					params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
					params.put("to_Date", sdf.format(sdf.parse(strToDate)));
					params.put("pat_Name", strPatName);
					params.put("cr_No", "0");
					if(strHospSerName.equals("0"))
					{
						params.put("charge_Type_Id", "0");
					}
					else
					{
						params.put("charge_Type_Id", strHospSerName);
					}
					
					if(strPaymentMode.equals("0"))
					{
						params.put("payment_mode_Id", "0");
					}
					else
					{
						params.put("payment_mode_Id", strPaymentMode);
					}
					
					ts.displayReport(request, response, reportPath, reportFormat,params,strHeaderHospCode);
			
			}
			else if(formBean.getStrCase().equals("3"))//Date Wise
			{						
				String reportPath = "/billing/reports/paymentDtl3_billrpt.rptdesign";
				if(strHospSerName.equals("0"))
				{
					params.put("chargeType_Name", "All");
				}
				else if(strHospSerName.equals("1"))
				{
					params.put("chargeType_Name", "Opd Morning");
				}
				else if(strHospSerName.equals("2"))
				{
					params.put("chargeType_Name", "IPD");
				}
				else if(strHospSerName.equals("3"))
				{
					params.put("chargeType_Name", "Emergency");
				}
				else if(strHospSerName.equals("4"))
				{
					params.put("chargeType_Name", "Opd Special");
				}
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("report_Footer_Visible", footerVisible);
				params.put("report_user_Remarks", strUserRemarks);
				params.put("hosp_Code", strHospitalCode);
				params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
				params.put("to_Date", sdf.format(sdf.parse(strToDate)));
				params.put("cr_No", "null");
				params.put("pat_Name", "null");
				if(strHospSerName.equals("0"))
				{
					params.put("charge_Type_Id", "0");
				}
				else
				{
					params.put("charge_Type_Id", strHospSerName);
				}
				
				if(strPaymentMode.equals("0"))
				{
					params.put("payment_mode_Id", "0");
				}
				else
				{
					params.put("payment_mode_Id", strPaymentMode);
				}
				
				ts.displayReport(request, response, reportPath, reportFormat,params,strHeaderHospCode);						
			}					
			else if(formBean.getStrCase().equals("4"))//Bill no Wise
			{
				String reportPath = "/billing/reports/paymentDtlBillWise_billrpt.rptdesign";
				if(strHospSerName.equals("0"))
				{
					params.put("chargeType_Name", "All");
				}
				else if(strHospSerName.equals("1"))
				{
					params.put("chargeType_Name", "Opd Morning");
				}
				else if(strHospSerName.equals("2"))
				{
					params.put("chargeType_Name", "IPD");
				}
				else if(strHospSerName.equals("3"))
				{
					params.put("chargeType_Name", "Emergency");
				}
				else if(strHospSerName.equals("4"))
				{
					params.put("chargeType_Name", "Opd Special");
				}
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("report_Footer_Visible", footerVisible);
				params.put("report_user_Remarks", strUserRemarks);
				params.put("hosp_Code", strHospitalCode);
				params.put("cr_No", "null");
				params.put("pat_Name", "null");
				if(strHospSerName.equals("0"))
				{
					params.put("charge_Type_Id", "0");
				}
				else
				{
					params.put("charge_Type_Id", strHospSerName);
				}
				
				if(strPaymentMode.equals("0")){
					params.put("payment_mode_Id", "0");
				}else{
					params.put("payment_mode_Id", strPaymentMode);
				}
				
				params.put("billNo", strBillNo);
				params.put("receiptNo", strReceiptNo);
				
				ts.displayReport(request, response, reportPath, reportFormat,params,strHeaderHospCode);						
			}
			else if(formBean.getStrCase().equals("5"))//Cashier Wise
			{						
				String reportPath = "/billing/reports/paymentDtl5_billrpt.rptdesign";
				if(strHospSerName.equals("0"))
				{
					params.put("chargeType_Name", "All");
				}
				else if(strHospSerName.equals("1"))
				{
					params.put("chargeType_Name", "Opd Morning");
				}
				else if(strHospSerName.equals("2"))
				{
					params.put("chargeType_Name", "IPD");
				}
				else if(strHospSerName.equals("3"))
				{
					params.put("chargeType_Name", "Emergency");
				}
				else if(strHospSerName.equals("4"))
				{
					params.put("chargeType_Name", "Opd Special");
				}
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("report_Footer_Visible", footerVisible);
				params.put("report_user_Remarks", strUserRemarks);
				params.put("hosp_Code", strHospitalCode);
				params.put("from_Date", sdf.format(sdf.parse(strFromDate)));
				params.put("to_Date", sdf.format(sdf.parse(strToDate)));
				params.put("cr_No", "null");
				params.put("pat_Name", "null");
				params.put("seat_id", seat_id);
				if(strHospSerName.equals("0"))
				{
					params.put("charge_Type_Id", "0");
				}
				else
				{
					params.put("charge_Type_Id", strHospSerName);
				}				
				if(strPaymentMode.equals("0"))
				{
					params.put("payment_mode_Id", "0");
				}
				else
				{
					params.put("payment_mode_Id", strPaymentMode);
				}
				
				ts.displayReport(request, response, reportPath, reportFormat,params,strHeaderHospCode);						
			}
			
		} 
				catch (Exception e) 
				{
					e.printStackTrace();
					strmsgText = "billing.reports.PaymentDtlRptDATA.showReport --> "+ e.getMessage();
					HisException eObj = new HisException("billing","PaymentDtlRpt->showReport()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");	
					eObj = null;
				}
			}
		}
