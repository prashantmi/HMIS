package mms.transactions.controller.data;

import hisglobal.ReportUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.transactions.controller.fb.PODeskPrintTransFB;

public class PODeskPrintTransDATA {
	
	private static final String pathFileName = "hisglobal.hisconfig.hisReport";
	private static String storagePath = "";
	public static void showReport(PODeskPrintTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		ReportUtil ts = new ReportUtil();
		
		String reportFormat = "pdf";

		Map<String, Object> params = new HashMap<String, Object>();
		try {
			
			String[] Temp = request.getParameterValues("combo");
			String str = request.getParameter("comboValue");
			String strHospitalCode = formBean.getStrHospitalCode();
			String strReportId = formBean.getStrReportId();
								
			String strChk = request.getParameter("chk");
			String[] strtemp = strChk.replace("@", "#").split("#");
			
			String strStoreId = strtemp[0];
			String strPONo = strtemp[1];
			String strPOTypeId = strtemp[3];
			String strItemCatId = strtemp[4];
			
//		System.out.println("chk value------->"+strChk);
//		System.out.println("strPOTypeId--------->"+strPOTypeId);
			
//			
			params.put("report_Header1", getStoragePath("PO_REPORT_HEADER1"));
			
			
			params.put("po_report_Address", getStoragePath("PO_REPORT_ADDRESS"));
			params.put("po_report_Contact", getStoragePath("PO_REPORT_CONTACT"));
			
			params.put("po_report_sub_Header", getStoragePath("PO_REPORT_SUB_HEADER"));
					
				
			
			// in case of local (with grant type)	
			if(strPOTypeId.equals("25") || strPOTypeId.equals("26") ||
					strPOTypeId.equals("27")){
				
				String reportPath = "/mms/reports/po_localcont_mmsrpt1.rptdesign";
				String strReportName = "Purchase Order(Local & Contigency With Grant)";
				
				params.put("report_Name", strReportName);
				params.put("report_id", strReportId);
				params.put("hospCode", strHospitalCode);
				params.put("storeId", strStoreId);
				params.put("poNo", strPONo);
				params.put("poTypeId", strPOTypeId);
				params.put("itemCategory", strItemCatId);	
				params.put("po_Header", getStoragePath("PO_HEADER"));
				
				ts.displayReport(request, response, reportPath, reportFormat,
						params,strHospitalCode);
				
			// in case of local	
			}else if(strPOTypeId.equals("21") ){
				
				if(Temp[3].equals("1"))
				{
				
					//String reportPath = "/mms/reports/po_localpur_mmsrpt.rptdesign";
					String reportPath = "/mms/reports/po_bulkpurchase_mmsrpt.rptdesign";
					String strReportName = "Draft Purchase Order";
					
					params.put("report_Name", strReportName);
					params.put("report_id", strReportId);
					params.put("hospCode", strHospitalCode);
					params.put("storeId", strStoreId);
					params.put("poNo", strPONo);
					params.put("poTypeId", strPOTypeId);
					params.put("itemCategory", strItemCatId);
					params.put("po_Header", getStoragePath("PO_HEADER"));
					params.put("stampPaperAmt", "0");
					params.put("contractValue", "0");
					params.put("storeName", str);
					
					ts.displayReport(request, response, reportPath, "html",params,strHospitalCode);
				}
				else
				{ 
					//String reportPath = "/mms/reports/po_localpur_mmsrpt.rptdesign";
					String reportPath = "/mms/reports/po_bulkpurchase_mmsrpt_new1.rptdesign";
					String strReportName = "Purchase Order";
					
					params.put("report_Name", strReportName);
					params.put("report_id", strReportId);
					params.put("hospCode", strHospitalCode);
					params.put("storeId", strStoreId);
					params.put("poNo", strPONo);
					params.put("poTypeId", strPOTypeId);
					params.put("itemCategory", strItemCatId);
					params.put("po_Header", getStoragePath("PO_HEADER"));
					params.put("stampPaperAmt", "0");
					params.put("contractValue", "0");
					params.put("storeName", str);
					
					ts.displayReport(request, response, reportPath, "html",params,strHospitalCode);//(request, response, reportPath, reportFormat,params,strHospitalCode,strPONo);
				}
				
			// in case of annual purchase
			}else if(strPOTypeId.equals("22") ){
				
			//	String reportPath = "/mms/reports/po_annualpurchase_mmsrpt_new.rptdesign";
				String reportPath = "/mms/reports/po_bulkpurchase_mmsrpt_new2.rptdesign";
				String strReportName = "Local PO";
				
				params.put("report_Name", strReportName);
				params.put("report_id", strReportId);
				params.put("hospCode", strHospitalCode);
				params.put("storeId", strStoreId);
				params.put("poNo", strPONo);
				params.put("poTypeId", strPOTypeId);
				params.put("itemCategory", strItemCatId);
				params.put("po_Header", getStoragePath("PO_HEADER1"));
				params.put("stampPaperAmt", "0");
				params.put("contractValue", "0");
				params.put("storeName", str);
				/*ts.displayReportWithName(request, response, reportPath, reportFormat,
						params,strHospitalCode,strPONo);*/
				ts.displayReport(request, response, reportPath, "html",params,strHospitalCode);//(request, response, reportPath, reportFormat,params,strHospitalCode,strPONo);
			
			}else if(strPOTypeId.equals("24")){
			//System.out.println("callinfg this");
				String reportPath = "/mms/reports/po_imported_mmsrpt.rptdesign";
				String strReportName = "Purchase Order(Annual Purchase)";
				
				params.put("report_Name", strReportName);
				params.put("report_id", strReportId);
				params.put("hospCode", strHospitalCode);
				params.put("storeId", strStoreId);
				params.put("poNo", strPONo);
				params.put("poTypeId", strPOTypeId);
				params.put("itemCategory", strItemCatId);
				params.put("po_Header", getStoragePath("PO_HEADER1"));
				
				ts.displayReport(request, response, reportPath, reportFormat,
						params,strHospitalCode);
			
			}else if(strPOTypeId.equals("23")){
			//System.out.println("callinfg this");
				String reportPath = "/mms/reports/po_contigency_mmsrpt.rptdesign";
				String strReportName = "Purchase Order(Annual Purchase)";
				
				params.put("report_Name", strReportName);
				params.put("report_id", strReportId);
				params.put("hospCode", strHospitalCode);
				params.put("storeId", strStoreId);
				params.put("poNo", strPONo);
				params.put("poTypeId", strPOTypeId);
				params.put("itemCategory", strItemCatId);
				params.put("po_Header", getStoragePath("PO_HEADER1"));
				
				ts.displayReport(request, response, reportPath, reportFormat,
						params,strHospitalCode);
			
			}
			else if(strPOTypeId.equals("87")){
				
				String reportPath = "/mms/reports/po_bspurchase_mmsrpt_new.rptdesign";
				String strReportName = "LIST OF MEDICINES APPROVED & TO BE PURCHASED";
				
				params.put("report_Name", strReportName);
				params.put("report_id", strReportId);
				params.put("hospCode", strHospitalCode);
				params.put("storeId", strStoreId);
				params.put("poNo", strPONo);
				params.put("poTypeId", strPOTypeId);
				params.put("itemCategory", strItemCatId);
				params.put("po_Header", getStoragePath("PO_HEADER1"));
				params.put("stampPaperAmt", "0");
				params.put("contractValue", "0");
				params.put("storeName", str);
				ts.displayReportWithName(request, response, reportPath, reportFormat,
						params,strHospitalCode,strPONo);
			
			}
						
		      	
		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	
	public static String getStoragePath(String key) {

		ResourceBundle rsBundle = ResourceBundle.getBundle(pathFileName);
		storagePath = rsBundle.getString(key);
		return storagePath;
	}
}
