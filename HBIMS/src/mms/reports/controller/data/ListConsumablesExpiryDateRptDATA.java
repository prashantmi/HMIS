package mms.reports.controller.data;

import hisglobal.ReportUtil;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.MmsConfigUtil;
import mms.reports.bo.ListConsumablesExpiryDateRptBO;
import mms.reports.controller.fb.ListConsumablesExpiryDateRptFB;
import mms.reports.vo.ListConsumablesExpiryDateRptVO;

public class ListConsumablesExpiryDateRptDATA {
	
	public static void getStoreList(ListConsumablesExpiryDateRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ListConsumablesExpiryDateRptBO bo = null;
		ListConsumablesExpiryDateRptVO voObj = null;
		String strmsgText = null;
		String strStoreVal = "",strDistrictStoreVal="";
		HisUtil util = null;
		try {

			bo = new ListConsumablesExpiryDateRptBO();
			voObj = new ListConsumablesExpiryDateRptVO();
			String strUserLevel = "1";//request.getSession().getAttribute("USER_LEVEL").toString();
			
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
			voObj.setStrSeatId(formBean.getStrSeatId());

			
			
			bo.getStoreList(voObj,strUserLevel);

			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Transactions", "ListConsumablesExpiryDateRptDDATA");
			
			if(strUserLevel.equals("6"))
			{
				strStoreVal = util.getOptionValue(voObj.getStrStoreWs(), "0", "0^All", false);
				//strDistrictStoreVal = util.getOptionValue(voObj.getStrDistrictStoreWs(), "0", "0^All", false);
			}
			else
			{
				strStoreVal = util.getOptionValue(voObj.getStrStoreWs(), "0", "0^All", false);
			//	strDistrictStoreVal = util.getOptionValue(voObj.getStrDistrictStoreWs(), "0", "-1^Select Value", false);
			}
				
			
			           		
			
			
			//formBean.setStrDistrictStoreValues(strDistrictStoreVal);						
			formBean.setStrStoreValues(strStoreVal);
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrUserLevel(strUserLevel);
			
			bo.getItemCatList(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Transactions", "ListConsumablesExpiryDateRptDDATA");
			String temp = "";

		//	if(strUserLevel.equals("6"))
		//	{
			//	temp = "<option value='0'>Select Value</option><option value='10'>Drug</option>";
			temp = util.getOptionValue(voObj.getStrItemCatWs(), "0", "0^Select Value",true);
//			//}
//			else
//			{
//				if (voObj.getStrItemCatWs().size() != 0) {
//					
//					temp = util.getOptionValue(voObj.getStrItemCatWs(), "0", "0^Select Value",true);
//
//				}else{
//					
//					temp = "<option value='0'>Select Value</option>";
//				}
//			}
			formBean.setStrItemCatValues(temp);
			

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.transactions.ListConsumablesExpiryDateRptDDATA.getStoreList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ListConsumablesExpiryDateRptDDATA->getStoreList()", strmsgText);
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
	
	public static void getItemCatList(ListConsumablesExpiryDateRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ListConsumablesExpiryDateRptBO bo = null;
		ListConsumablesExpiryDateRptVO voObj = null;
		String strmsgText = null;
		String strUserLevel = "1";//request.getSession().getAttribute("USER_LEVEL").toString();
		HisUtil util = null;
		try {

			bo = new ListConsumablesExpiryDateRptBO();
			voObj = new ListConsumablesExpiryDateRptVO();
			
			String strStoreId = request.getParameter("storeId");
			
			if (strStoreId == null)
				strStoreId = "0";
			
			voObj.setStrStoreId(strStoreId);
			voObj.setStrHospitalCode(formBean.getStrHospitalCode());
				
			bo.getItemCatList(voObj);

			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());

			}
			util = new HisUtil("MMS Transactions", "ListConsumablesExpiryDateRptDDATA");
		//	String temp = "<option value='0'>SelectValue</option>";
			String temp ="";
//			if(strUserLevel.equals("6"))
//			{
			//	temp = "<option value='0'>Select Value</option><option value='10'>Drug</option>";
//			}
//			else
//			{
		//	if (voObj.getStrItemCatWs().size() != 0) {
					
					temp = util.getOptionValue(voObj.getStrItemCatWs(), "0", "0^Select Value",true);

	//			}else{
					
		//			temp = "<option value='0'>Select Value</option><option value='10'>Drug</option>";
		//	}
//			}
			
			

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(temp);

		} catch (Exception e) {
			e.printStackTrace();
			strmsgText = "mms.transactions.ListConsumablesExpiryDateRptDDATA.getItemCatList --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"ListConsumablesExpiryDateRptDDATA->getItemCatList()", strmsgText);
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
	
	public static void showReport(ListConsumablesExpiryDateRptFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		MmsConfigUtil mcu = null;
		String reportFormat = "html";
		HisUtil util = null;
		String strDueDate  ="";
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			mcu = new MmsConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			util = new HisUtil("MMS Transactions", "ListConsumablesExpiryDateRptDDATA");
			String strExpiryAlertDays = mcu.getStrExpAlertDays();
			String strNearExpiryDate="";
			String strNearExpiryDays="";
			String strStoreId ="";
			String strReportName ="";
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId     = formBean.getStrReportId();
			String strCatCode      = formBean.getStrItemCatNo();
			
			//added by vipul on 25-05-2021 as item exipry details need to show
			String itemName="";
			if (strCatCode.equals("10"))
			{
				itemName="Drugs";
			}	
			else {itemName="Items";}
			
			formBean.setStrCurrentDate(util.getASDate("dd-MMM-yyyy"));
			formBean.setStrFrmExpiryDays( (formBean.getStrFrmExpiryDays()!=null && !formBean.getStrFrmExpiryDays().equals("")) ?formBean.getStrFrmExpiryDays():"0");

			
	formBean.setStrFrmExpiryDays((formBean.getStrFrmExpiryDays()!=null?formBean.getStrFrmExpiryDays():"0"));
			
			if(!formBean.getStrExpNonExpiryFlag().equals("10"))//   Expired
		    {       
		         if(formBean.getStrDistrictFlag().equals("2"))
				 {	
					     strStoreId    = formBean.getStrStoreId();
					     strReportName = "Near Expiry Details";
				 }
				 else
				 {
						 strStoreId    = formBean.getStrDistrictStoreId();	
						 strReportName = "Consolidated List Of Expired Drugs";
				 }
		         params.put("expOrNearExpFlag","0");
		         
		    }
			else//  Near Expiry
			{
				if(formBean.getStrDistrictFlag().equals("2"))
				 {	
					 strStoreId    = formBean.getStrStoreId();
					     if(formBean.getStrDateDaysFlag().equals("22")) // Due Date
				         {
				           strNearExpiryDate = formBean.getStrDueDate();
				           strReportName = "List Of "+itemName+" Expiring Within "+formBean.getStrDueDate()+"";
				           params.put("dueFlag","0");
				         }
				         else		// Due Days
				         {        
				          
				            strNearExpiryDays = formBean.getStrFrmExpiryDays();
				            strReportName = "List Of "+itemName+" Expiring Within "+formBean.getStrFrmExpiryDays()+" Day(s)";
				            params.put("dueFlag","1");
				         }  
					     
				 }
				 else
				 {
					 strStoreId    = formBean.getStrDistrictStoreId();	
					 if(formBean.getStrDateDaysFlag().equals("22"))
			         {
			           strNearExpiryDate = formBean.getStrDueDate();
			           strReportName = "List Of Expired "+itemName+" Till Date "+formBean.getStrDueDate()+" ";
			           params.put("dueFlag","0");
			         }
			         else
			         {        
			          
			            strNearExpiryDays = formBean.getStrFrmExpiryDays();
			            strReportName = "List Of Expired "+itemName+" With  "+formBean.getStrFrmExpiryDays()+" Day(s) ";
			            params.put("dueFlag","1");
			         }  
				 }
				  params.put("expOrNearExpFlag","1");
				
			}	
			Calendar ca2      = Calendar.getInstance();
			String   year1    = formBean.getStrDueDate().split("\\-")[2];
            String   day1     = formBean.getStrDueDate().split("\\-")[0];
            String   month1   = formBean.getStrDueDate().split("\\-")[1];
            
            
            int month=0;
            
            if(month1.equals("Jan"))
         	{
         		month=1;
         	}  
         	else if(month1.equals("Feb"))
         	{
         		month=2;
         	}  
         	 else if(month1.equals("Mar"))
         	{
         		month=3;
         	}  
         	else if(month1.equals("Apr"))
         	{
         		month=4;
         	} 
         	else if(month1.equals("May"))
         	{
         		month=5;
         	}  
         	else if(month1.equals("Jun"))
         	{
         		month=6;
         	}        
         	else if(month1.equals("Jul"))
         	{
         		month=7;
         	}  
         	else if(month1.equals("Aug"))
         	{
         		month=8;
         	}  
         	else if(month1.equals("Sep"))
         	{
         		month=9;
         	}  
         	else if(month1.equals("Oct"))
         	{
            	month=10;
         	} 
         	else if(month1.equals("Nov"))
         	{
         		month=11;
         	} 
         	else if(month1.equals("Dec"))
         	{
         		month=12;
         	}     
         	
         	
            ca2.set(Integer.parseInt(year1), month-1, Integer.parseInt(day1));  // From Date

SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			
//System.out.println("From Date::Add hrs::"+sdf.format(ca2.getTime()));            
			
			if(formBean.getStrExpNonExpiryFlag().equals("20")) // Near Expiry
			{
				if(formBean.getStrExpiryDays()!=null  && formBean.getStrExpiryDays().equals("11"))
				{
					ca2.add(Calendar.HOUR, (Integer.parseInt(formBean.getStrFrmExpiryDays())*24));
			           
					//System.out.println("formBean.getStrFrmExpiryDays()"+formBean.getStrFrmExpiryDays());	
				}	
			}
			
			
                        
            
			String strUserRemarks  = formBean.getStrUserRemarks();
			
			if(formBean.getStrExpNonExpiryFlag().equals("10"))//   Expired
			{
				strDueDate      = formBean.getStrCurrentDate();
				strExpiryAlertDays = "0";
			}
			else
			{
				strDueDate      = formBean.getStrDueDate();
			}
			
			
			reportFormat = formBean.getStrReportFormat();
			
//			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
//			
//			System.out.println("From Date::Add hrs::"+sdf.format(ca2.getTime()));
			
			
			boolean footerVisible = true;
			
			if (formBean.getStrIsFooter().equals("1")) {
				footerVisible = false;
			}
			
			
			/*System.out.println("strExpiryAlertDays"+strExpiryAlertDays);
			System.out.println("dueDate"+sdf.format(ca2.getTime()));
			
			System.out.println("strDueDate"+strDueDate);*/
			
			String reportPath = "/mms/reports/listconsexpirydate_mms_rpt.rptdesign";
				
				params.put("report_id", strReportId);
				params.put("report_Name", strReportName);
				params.put("report_Footer_Visible", footerVisible);
				params.put("report_user_Remarks", strUserRemarks);
				params.put("hospCode", strHospitalCode);
				params.put("catCode", strCatCode);
				params.put("storeName", formBean.getStrStoreName());
				
				if(formBean.getStrExpNonExpiryFlag().equals("10"))//   Expired
				{
					params.put("storeId", strStoreId);//+"^"+"0");
					params.put("dueDate", sdf.format(sdf.parse(strDueDate))); // Expiry Date
				}
				else
				{
					params.put("storeId", strStoreId);//+"^"+"1");
					
					if(formBean.getStrExpiryDays()!=null  && formBean.getStrExpiryDays().equals("11"))
					{
						params.put("dueFlag","1");

						params.put("dueValue", formBean.getStrFrmExpiryDays());// sdf.format(ca2.getTime())); // Expiry Days
						
					}
					else	
						{
						params.put("dueFlag","0");
						params.put("dueValue", formBean.getStrDueDate());//)sdf.format(sdf.parse(strDueDate))); // Expiry Date	
						}
				}
					
				
				
				
				params.put("report_Fix_Header","Header");
			
				System.out.println("strStoreId"+strStoreId);
				System.out.println("hospCode"+ strHospitalCode);
				System.out.println("catCode"+ strCatCode);
				System.out.println("storeName"+ formBean.getStrStoreName());
				
				
				System.out.println("reportPath>>>>>"+reportPath);
				ts.displayReport(request, response, reportPath, reportFormat,params,strHospitalCode);
			
		      	
		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
