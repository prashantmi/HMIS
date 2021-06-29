package bmed.transactions.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import bmed.EMMSStaticConfigurator;
import bmed.dao.HemtItemMcDtlDAO;
import bmed.global.controller.util.BmedConfigUtil;
import bmed.transactions.bo.BmedTransBO;
import bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB;
import bmed.transactions.controller.fb.HemComplaintApprovalDeskFB;
import bmed.vo.ComplaintRequestDtlVO;
import bmed.vo.HemComplaintApprovalDeskVO;
import bmed.vo.HemtComplaintApprovalDtlVO;
import bmed.vo.HemtComplaintStatusDtlVO;
import bmed.vo.HemtItemMcDtlVO;
import bmed.vo.WarrantyDtlVO;

public class HemComplaintApprovalDeskDATA 
{
	
	 /**
     * 
     * @param hemComplaintApprovalDeskFB
     * @param request_p
     */
	public static void initializeItemComplaintApproval(HemComplaintApprovalDeskFB hemComplaintApprovalDeskFB_p,HttpServletRequest request_p) 
	{
	  
		String strErrMsg;
		BmedTransBO bmedTransBO=null;
		HemComplaintApprovalDeskVO  hemComplaintApprovalDeskVO_p=null;
		WarrantyDtlVO warrantyDtlVO = null;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		
		//String strDtl;
		String strHospitalCode_p,strSlNo="0",strItemId = null;
		String strItemBatchNo = null,strItemSerialNo = null;
		UserVO userVo = null;
		String strCombo[] = null;
		String strChk ;
		String strTmp[] = null;
		String strWarrantyDetailsTable;
		String strMaintenanceContractDetailsTable;
		String strUrl;
		try
		{
			                                
			                                  bmedTransBO = new BmedTransBO();
			                                  warrantyDtlVO = new WarrantyDtlVO();
			                      			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			                      			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			                 hemComplaintApprovalDeskVO_p = new HemComplaintApprovalDeskVO();
							            userVo = ControllerUTIL.getUserVO(request_p);
							 strHospitalCode_p = userVo.getHospitalCode();
							 warrantyDtlVO.setStrHospitalCode(strHospitalCode_p);
								hemtItemMcDtlVO.setStrHospCode(strHospitalCode_p);
								
								hemtItemMcDtlVO.setStrItemId(strItemId);
								//hemtItemMcDtlVO.setStrItemSlNo(strItemSerialNo);
								
								
								 strUrl = "/bmed"+request_p.getParameter("cnt_page")+".cnt";
									
									if(request_p.getParameter("cnt_page") == null)
									{
										
										strUrl = request_p.getParameter("strPath");
									}
									
									hemComplaintApprovalDeskFB_p.setStrPath(strUrl.trim());
								
							 strCombo = request_p.getParameterValues("combo");
							 
							 hemComplaintApprovalDeskFB_p.setStrComplaintStatus(strCombo[1]);
							 
							 
							 if (request_p.getParameter("chk") == null) 
							 {
									strChk = hemComplaintApprovalDeskFB_p.getStrChk();
							 } 
							 else 
							 {
									strChk = request_p.getParameter("chk");
							 }
							 hemComplaintApprovalDeskFB_p.setStrChk(request_p.getParameter("chk"));
							 strTmp = request_p.getParameter("chk").split("\\@");
							 //strTmp = strChk.split("\\@");
							 hemComplaintApprovalDeskFB_p.setStrDeptId(strCombo[0]);
							 hemComplaintApprovalDeskFB_p.setStrComplaintId(strTmp[0]);
							 hemComplaintApprovalDeskFB_p.setStrComplaintSlNo(strTmp[1]);						
							 hemComplaintApprovalDeskFB_p.setStrHospCode(strHospitalCode_p);
							 hemComplaintApprovalDeskFB_p.setStrItemBatchNo(strItemBatchNo);
			
							 hemComplaintApprovalDeskVO_p.setStrItemBatchNo(strItemBatchNo);
							 hemComplaintApprovalDeskVO_p.setStrDeptId(strCombo[0]);
							 hemComplaintApprovalDeskVO_p.setStrComplaintId(strTmp[0]);
							 hemComplaintApprovalDeskVO_p.setStrComplaintSlNo(strTmp[1]);
							 hemComplaintApprovalDeskVO_p.setStrHospCode(strHospitalCode_p);

							 
							 complaintRequestDtlVO.setStrBatchNo(hemComplaintApprovalDeskFB_p.getStrItemBatchNo());
							 complaintRequestDtlVO.setStrReqId(strTmp[0]);			
							 complaintRequestDtlVO.setStrHospitalCode(strHospitalCode_p);
							 strSlNo=strTmp[4].split("\\$")[1];
							 warrantyDtlVO.setStrSlNo(strSlNo);
							 hemtItemMcDtlVO.setStrSlNo(strSlNo);
					
			 
			    // Calling BO method here			    			    
			    bmedTransBO.initializeComplaintApprovalActions(complaintRequestDtlVO,warrantyDtlVO,hemtItemMcDtlVO);
			    bmedTransBO.getProcessSpecificDtl(hemComplaintApprovalDeskVO_p,"0");
			    
			    
			    hemComplaintApprovalDeskFB_p.setStrComplaintAppDtls(hemComplaintApprovalDeskVO_p.getStrComplaintAppDtls());
			    hemComplaintApprovalDeskFB_p.setStrComplaintId(complaintRequestDtlVO.getStrReqId());
			    hemComplaintApprovalDeskFB_p.setStrComplaintDate(complaintRequestDtlVO.getStrReqDate());
			    hemComplaintApprovalDeskFB_p.setStrItemName(complaintRequestDtlVO.getStrItemName());
			    hemComplaintApprovalDeskFB_p.setStrItemBatchNo(complaintRequestDtlVO.getStrBatchNo());
			   // hemComplaintApprovalDeskFB_p.setStr
			    hemComplaintApprovalDeskFB_p.setStrItemSerialNo(complaintRequestDtlVO.getStrSerialNo());
			    hemComplaintApprovalDeskFB_p.setStrManufacturerSerialNo(complaintRequestDtlVO.getStrManufSerialNo());
			    hemComplaintApprovalDeskFB_p.setStrComplaintDescription(complaintRequestDtlVO.getStrComplaintDes());
			    
			    hemComplaintApprovalDeskFB_p.setStrDeptName(complaintRequestDtlVO.getStrDeptName());
			    hemComplaintApprovalDeskFB_p.setStrStoreName(complaintRequestDtlVO.getStrStoreName());
			    
                // Calling Warranty & MC Details 
				           strWarrantyDetailsTable = getWarrantyDetailsTable(warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
				strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(complaintRequestDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON"); 
			
				hemComplaintApprovalDeskFB_p.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
				hemComplaintApprovalDeskFB_p.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
		}
		catch (Exception e) 
		{
	        e.printStackTrace();
			      strErrMsg = "HemComplaintApprovalDeskDATA.initializeMain() --> "+ e.getMessage();
		  HisException eObj = new HisException("BMED", "HemComplaintApprovalDeskDATA",strErrMsg);
		  hemComplaintApprovalDeskFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
		
	}
	
	
	/**
     * 
     * @param hemComplaintApprovalDeskFB
     * @param request_p
     */
	public static void initializeItemComplaintApprovalView(HemComplaintApprovalDeskFB hemComplaintApprovalDeskFB_p,HttpServletRequest request_p) 
	{
	  
		String strErrMsg;
		BmedTransBO bmedTransBO=null;
		HemComplaintApprovalDeskVO  hemComplaintApprovalDeskVO_p=null;
		WarrantyDtlVO warrantyDtlVO = null;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		
		//String strDtl;
		String strHospitalCode_p;
		UserVO userVo = null;
		String strCombo[] = null;
		String strChk ;
		String strTmp[] = null;
		String strWarrantyDetailsTable;
		String strMaintenanceContractDetailsTable;
		String strUrl;
		try
		{
			                                
			                                  bmedTransBO = new BmedTransBO();
			                                  warrantyDtlVO = new WarrantyDtlVO();
			                      			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			                      			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			                 hemComplaintApprovalDeskVO_p = new HemComplaintApprovalDeskVO();
							            userVo = ControllerUTIL.getUserVO(request_p);
							 strHospitalCode_p = userVo.getHospitalCode();
							 warrantyDtlVO.setStrHospitalCode(strHospitalCode_p);
								hemtItemMcDtlVO.setStrHospCode(strHospitalCode_p);
								
								 strUrl = "/bmed"+request_p.getParameter("cnt_page")+".cnt";
									
									if(request_p.getParameter("cnt_page") == null)
									{
										
										strUrl = request_p.getParameter("strPath");
									}
									
									hemComplaintApprovalDeskFB_p.setStrPath(strUrl.trim());
								
							 strCombo = request_p.getParameterValues("combo");
							 if (request_p.getParameter("chk") == null) 
							 {
									strChk = hemComplaintApprovalDeskFB_p.getStrChk();
							 } 
							 else 
							 {
									strChk = request_p.getParameter("chk");
							 }
							 strTmp = strChk.split("\\@");
							 hemComplaintApprovalDeskFB_p.setStrDeptId(strCombo[0]);
							 hemComplaintApprovalDeskFB_p.setStrComplaintId(strTmp[0]);
							 hemComplaintApprovalDeskFB_p.setStrComplaintSlNo(strTmp[1]);
							 hemComplaintApprovalDeskFB_p.setStrHospCode(strHospitalCode_p);
							 
							 hemComplaintApprovalDeskVO_p.setStrDeptId(strCombo[0]);
							 hemComplaintApprovalDeskVO_p.setStrComplaintId(strTmp[0]);
							 hemComplaintApprovalDeskVO_p.setStrComplaintSlNo(strTmp[1]);
							 hemComplaintApprovalDeskVO_p.setStrHospCode(strHospitalCode_p);
							 complaintRequestDtlVO.setStrReqId(strTmp[0]);			
							 complaintRequestDtlVO.setStrHospitalCode(strHospitalCode_p);
					
			 
			    // Calling BO method here			    			    
			    bmedTransBO.initializeComplaintApprovalActions(complaintRequestDtlVO,warrantyDtlVO,hemtItemMcDtlVO);
			    bmedTransBO.getProcessSpecificDtl(hemComplaintApprovalDeskVO_p,"1");
			    
			    
			    hemComplaintApprovalDeskFB_p.setStrComplaintAppDtls(hemComplaintApprovalDeskVO_p.getStrComplaintAppDtls());
			    hemComplaintApprovalDeskFB_p.setStrComplaintId(complaintRequestDtlVO.getStrReqId());
			    hemComplaintApprovalDeskFB_p.setStrComplaintDate(complaintRequestDtlVO.getStrReqDate());
			    hemComplaintApprovalDeskFB_p.setStrItemName(complaintRequestDtlVO.getStrItemName());
			    hemComplaintApprovalDeskFB_p.setStrItemBatchNo(complaintRequestDtlVO.getStrBatchNo());
			    hemComplaintApprovalDeskFB_p.setStrItemSerialNo(complaintRequestDtlVO.getStrSerialNo());
			    hemComplaintApprovalDeskFB_p.setStrManufacturerSerialNo(complaintRequestDtlVO.getStrManufSerialNo());
			    hemComplaintApprovalDeskFB_p.setStrComplaintDescription(complaintRequestDtlVO.getStrComplaintDes());
                // Calling Warranty & MC Details 
			    
				           strWarrantyDetailsTable = getWarrantyDetailsTable(warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
				strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON"); 
			
				hemComplaintApprovalDeskFB_p.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
				hemComplaintApprovalDeskFB_p.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
		}
		catch (Exception e) 
		{
//	        e.printStackTrace();
			      strErrMsg = "HemComplaintApprovalDeskDATA.initializeMain() --> "+ e.getMessage();
		  HisException eObj = new HisException("BMED", "HemComplaintApprovalDeskDATA",strErrMsg);
		  hemComplaintApprovalDeskFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
		
	}
	
	
	private static String getMaintenanceContractDetailsTable(
			WebRowSet wrsData_p, String strDisplayMode_p) throws SQLException {

		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);
		int nWidth;
		int index=0;
		int nColspan;
		if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
			nWidth = 19;
			nColspan = 6;
		} else {
			nWidth = 20;
			nColspan = 5;
		}
		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");
		if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
			sbHeader.append("<td width=\"5%\" class=\"LABEL_TD\" style=\"text-align: center;\"></td>");
		}
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Supplier Name</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Start Date</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">End Date</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Terms &amp; Condition</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">File</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			// HEMSTR_MC_TYPE: 1
			// HEMNUM_ITEM_SL_NO: 2
			// HEMNUM_ITEM_ID: 3
			// HEMSTR_BATCH_NO: 4
			// HEMNUM_MANUF_SL_NO: 5
			// HEMNUM_SL_NO: 6
			// HEMSTR_TERM_N_CON: 7
			// HEMNUM_IS_ITEM: 8
			// HEMNUM_ROUTINE_VISIT: 9
			// HEMSTR_MC_NAME: 10
			// HEMNUM_BREAK_VISIT: 11
			// HEMNUM_MANUF_ID: 12
			// HEMSTR_RESPONSE_TIME: 13
			// HEMNUM_COST: 14
			// HEMSTR_PENALTY_CON: 15
			// GDT_ENTRY_DATE: 16
			// GNUM_ISVALID: 17
			// GNUM_SEAT_ID: 18
			// GNUM_HOSPITAL_CODE: 19
			// HEMNUM_ROUTINE_FREQ: 20
			// HEMSTR_FREQ_UNIT: 21
			// HEMSTR_RES_TIME_UNIT: 22
			// HEMDT_START_DATE: 23
			// HEMDT_END_DATE: 24
			// HEMSTR_TENDER_NO: 25
			// HPURNUM_UPLOAD_NO: 26
			// HPURSTR_DOC_REF_NO: 27
			// HEMDT_TENDER_DATE: 28
			// HPURDT_DOC_REF_DATE: 29
			// HEMSTR_ORDER_NO: 30
			// HEMNUM_CANCEL_SEATID: 31
			// HEMDT_ORDER_DATE: 32
			// HEMDT_FINANCIAL_START_YEAR: 33
			// GDT_EFFECTIVE_FROM: 34
			// GSTR_REMARKS: 35
			// HEMDT_FINANCIAL_END_YEAR: 36
			// HEMNUM_IS_RENEWED: 37
			// HEMNUM_CANCEL_ID: 38
			// HEMDT_CANCEL_DATE: 39
			// HEMSTR_CANCEL_REMARKS: 40
			// MAINTE_TYPE_NAME: 41
			// ITEM_NAME: 42
			// FREQ_UNIT_NAME: 43
			// RES_TIME_UNIT_NAME: 44
			// VENDOR_NAME: 45
			String strExten="";
			while (wrsData_p.next()) {

				String strSupplierName = wrsData_p.getString("VENDOR_NAME");
				String strStartDate = wrsData_p.getString("HEMDT_START_DATE");
				String strEndDate = wrsData_p.getString("HEMDT_END_DATE");
				String strTermsAndCondition = wrsData_p
						.getString("HEMSTR_TERM_N_CON");
				String strFileName = wrsData_p.getString("HPURSTR_DOC_REF_NO");
				String strUploadNo = wrsData_p.getString("HPURNUM_UPLOAD_NO");
				String strMcSlNo = wrsData_p.getString("HEMNUM_SL_NO");
				if (strSupplierName == null) {
					strSupplierName = "---";
				}
				if (strStartDate == null) {
					strStartDate = "---";
				}
				if (strEndDate == null) {
					strEndDate = "---";
				}

				if (strTermsAndCondition == null) {
					strTermsAndCondition = "---";
				}
				if (strFileName == null) {
					strFileName = "";
				}
				if (strUploadNo == null) {
					strUploadNo = "";
				}		
				else
				{
					if(!strUploadNo.equals("0"))
					strExten=strUploadNo.split("\\.")[1];
				}
				if (strMcSlNo == null) {
					strMcSlNo = "0";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");
				if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
					sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><input type=\"radio\" name=\"strWarrantyOrMaintenanceSlNoAndType\" value=\""
							+ strMcSlNo + "^MAINTENANCE\"/></td>");
				}
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strSupplierName + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strStartDate + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strEndDate + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strTermsAndCondition + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><a style='cursor:pointer;color:blue;font-size:12px;' "
						+ "onClick=onUploadedFileName(this,"+index+",'"+strFileName+"."+strExten+"');>"
						+ strUploadNo + "</a></td>");
				sbBody.append("</tr>");
				index++;

			}

		} else {
			sbBody.append("<tr>");
			sbBody.append("<td colspan=\""
					+ nColspan
					+ "\" class=\"CONTROL_TD\" style=\"text-align: center; color: red;\">No Data Found!</td>");
			sbBody.append("</tr>");
		}

		return sbHeader.toString() + sbBody.toString();
	}
	
	
	private static String getWarrantyDetailsTable(WebRowSet wrsData_p,
			String strDisplayMode_p) throws SQLException {

		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);

		int nWidth;
		int index=0;
		int nColspan;
		if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
			nWidth = 19;
			nColspan = 6;
		} else {
			nWidth = 20;
			nColspan = 5;
		}

		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");
		if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
			sbHeader.append("<td width=\"5%\" class=\"LABEL_TD\" style=\"text-align: center;\"></td>");
		}
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Supplier Name</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Warranty Date/Upto</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Extend Date</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Terms &amp; Condition</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">File</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			// HSTNUM_ITEM_ID:1
			// HSTNUM_ITEMBRAND_ID:2
			// HSTSTR_BATCH_SL_NO:3
			// GNUM_HOSPITAL_CODE:4
			// HSTNUM_ITEM_SL_NO:5
			// HSTNUM_MANUF_ID:6
			// HSTNUM_MANUF_SL_NO:7
			// HSTNUM_SL_NO:8
			// HSTDT_WARRENTY_DATE:9
			// HSTNUM_WARRENTY_UPTO:10
			// HSTNUM_WARRENTY_UPTO_UNIT:11
			// HSTNUM_IS_ITEM:12
			// HSTDT_FINANCIAL_START_YEAR:13
			// HSTDT_FINANCIAL_END_YEAR:14
			// HPURNUM_UPLOAD_NO:15
			// HPURSTR_DOC_REF_NO:16
			// HEMSTR_TERM_N_CON:17
			// HPURDT_DOC_REF_DATE:18
			// GSTR_REMARKS:19
			// GDT_ENTRY_DATE:20
			// GNUM_SEATID:21
			// GNUM_ISVALID:22
			// HEMSTR_TENDER_NO:23
			// GDT_LSTMOD_DATE:24
			// HEMDT_TENDER_DATE:25
			// GNUM_LSTMOD_SEATID:26
			// HEMSTR_ORDER_NO:27
			// HEMDT_ORDER_DATE:28
			// HEMNO_CANCEL_ID:29
			// HEMDT_CANCEL_DATE:30
			// HEMSTR_EXT_TERM_N_CON:31
			// HEMSTR_CANCEL_REMARKS:32
			// HEMNUM_IS_EXTENDED:33
			// HEMDT_EXTENDED_START_DATE:34
			// HEMNUM_EXTENDED_UPTO:35
			// HEMNUM_EXTENDED_UPTO_UNIT:36
			// HPURNUM_EXT_UPLOAD_NO:37
			// HPURNUM_EXT_DOC_REF_NO:38
			// HPURDT_EXT_DOC_REF_DATE:39
			// ITEM_NAME:40
			// VENDOR_NAME:41
			// WARRENTY_UPTO_UNIT_NAME:42
			// WARRANTY_EXTEND_DATE:43
			String strExten="";
			while (wrsData_p.next()) {

				String strSupplierName = wrsData_p.getString("VENDOR_NAME");
				String strWarrantyDate = wrsData_p
						.getString("HSTDT_WARRENTY_DATE");
				String strWarrantyUpto = wrsData_p
						.getString("HSTNUM_WARRENTY_UPTO");
				String strWarrantyUptoUnit = wrsData_p
						.getString("WARRENTY_UPTO_UNIT_NAME");
				String strExtendDate = wrsData_p
						.getString("WARRANTY_EXTEND_DATE");
				String strTermsAndCondition = wrsData_p
						.getString("HEMSTR_TERM_N_CON");
				String strFileName = wrsData_p.getString("HPURSTR_DOC_REF_NO");
				String strWarrantySlNo = wrsData_p.getString("HSTNUM_SL_NO"); 
				String strUploadNo = wrsData_p.getString("HPURNUM_UPLOAD_NO");

				if (strSupplierName == null) {
					strSupplierName = "---";
				}
				if (strWarrantyDate == null) {
					strWarrantyDate = "---";
				}
				if (strWarrantyUpto == null) {
					strWarrantyUpto = "---";
				}
				if (strWarrantyUptoUnit == null) {
					strWarrantyUptoUnit = "---";
				}
				if (strExtendDate == null) {
					strExtendDate = "---";
				}
				if (strTermsAndCondition == null) {
					strTermsAndCondition = "---";
				}
				if (strFileName == null) {
					strFileName = "";
				}
				if (strUploadNo == null) {
					strUploadNo = "";
				}		
				else
				{
					if(!strUploadNo.equals("0"))
					strExten=strUploadNo.split("\\.")[1];
				}
				if (strWarrantySlNo == null) {
					strWarrantySlNo = "";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");
				if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
					sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><input type=\"radio\" name=\"strWarrantyOrMaintenanceSlNoAndType\" value=\""
							+ strWarrantySlNo + "^WARRANTY\"/></td>");
				}
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strSupplierName + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strWarrantyDate
						+ " / "
						+ strWarrantyUpto
						+ " "
						+ strWarrantyUptoUnit + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strExtendDate + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strTermsAndCondition + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><a style='cursor:pointer;color:blue;font-size:12px;' "
						+ "onClick=onUploadedFileName(this,"+index+",'"+strFileName+"."+strExten+"');>"
						+ strUploadNo + "</a></td>");
				sbBody.append("</tr>");
				index++;

			}

		} else {
			sbBody.append("<tr>");
			sbBody.append("<td colspan=\""
					+ nColspan
					+ "\" class=\"CONTROL_TD\" style=\"text-align: center; color: red;\">No Data Found!</td>");
			sbBody.append("</tr>");
		}

		return sbHeader.toString() + sbBody.toString();
	}

	
	/**
	 * 
	 * @param viewValuesWS
	 * @return
	 * @throws Exception
	 */
	
	public static String getCoplaintSpecificDtl(WebRowSet viewValuesWS,String strIsShow)throws Exception
	{

		StringBuffer br = new StringBuffer("");
		WebRowSet wb = null;
		String strHiddComplaintAppDtls="";
		try 
		{
			wb = viewValuesWS;
			
            /*			
         
			      1.HEMNUM_REQ_ID 
				  2.GNUM_HOSPITAL_CODE  
				  3.HEMNUM_REQ_TYPE 
				  4.HEMNUM_IS_ATTACHED 
				  5.HEMNUM_IS_ITEM 
				  6.HEMNUM_ENGG_ITEM_TYPE_ID 
				  7.HEMNUM_IS_PREVENTIVE 
				  8.HEMNUM_IS_ONLINE
				  9.HEMNUM_ENGG_ITEM_SUB_TYPE_ID 
				  10.HEMNUM_ITEM_ID 
				  11.HEMNUM_HEM_FLAG 
				  12.HEMSTR_SERIAL_NO 
				  13.HEMNUM_REQ_DATE   
				  14.HEMSTR_MANUF_SERIAL_NO 
				  15.HEMNUM_STORE_ID 
				  16.HEMNUM_LANDMARK_DESC
				  17.HEMSTR_BATCH_NO  
				  18.HEMNUM_WARRANTY_SLNO
				  19.HEMNUM_MAIN_STATUS 
				  20.HEMNUM_MC_SLNO
				  21.HEMNUM_VENDOR_ID  
				  22.HEMNUM_MAINTE_ID  
				  23.HEMSTR_COMPLAINT_DES  
				  24.HEMSTR_EMP_ID  
				  25.HEMNUM_DEPT_ID
				  26.HEMSTR_PREFER_TIME_FR 
				  27.HEMSTR_PREFER_TIME_TO  
				  28.HEMSTR_DOWN_TIME_FR  
				  29.HEMSTR_CONTACT_PERSON  
				  30.HEMNUM_IS_WORKING 
				  31.HEMSTR_CONTACT_NO 
				  32.HEMDT_NOTWORKING_DATE   
				  33.GSTR_REMARKS  
				  34.GDT_ENTRY_DATE   
				  35.HEMDT_COMP_INTEMATION  
				  36.GNUM_ISVALID 
				  37.GNUM_SEATID 
				  38.HEMNUM_SUB_STATUS 
				  39.GSTR_STATUS_REMARKS 
				  40.HEMNUM_COST  
				  41.HEMSTR_VERIFIED_ID   
				  42.HEMSTR_VERIFIED_REMARKS 
				  43.HEMNUM_CANCEL_ID 
				  44.HEMDT_CANCEL_DATE
				  45.HEMSTR_CANCEL_REMARKS  
				  46.HEMNUM_CANCEL_SEATID 
				  47.HEMDT_CLOSED_DATE 
				  48.HEMDT_CLOSED_SEATID  
				  49.HEMSTR_CLOSE_REASON 
				  50.HEMSTR_VENDOR_INVOICE_NO 
				  51.Item Type Name
				  52.Item Sub Type Name
				  53.Item Name
				  54.Store Name
				  55.Status Name
				  56.Vendor Name
				  57.Maintenance Name
				  58.Service Engg Name
				  59.Dept Name
				  60.Verified By
            */
			
			
			if (wb != null) 
			{
				if(wb.size() != 0)
				{			
					while(wb.next())
					{
						strHiddComplaintAppDtls = wb.getString(1)+"^"+wb.getString(2)+"^"+wb.getString(3)+"^"+wb.getString(4)+"^"+wb.getString(5)+"^"+wb.getString(6)+"^"+wb.getString(7)+"^"+
						wb.getString(8)+"^"+wb.getString(9)+"^"+wb.getString(10)+"^"+wb.getString(11)+"^"+wb.getString(12)+"^"+wb.getString(13)+"^"+wb.getString(14)+"^"+wb.getString(15)+"^"+wb.getString(16)+"^"+wb.getString(17)+"^"+
						wb.getString(18)+"^"+wb.getString(19)+"^"+wb.getString(20)+"^"+wb.getString(21)+"^"+wb.getString(22)+"^"+wb.getString(23)+"^"+wb.getString(24)+"^"+wb.getString(25)+"^"+wb.getString(26)+"^"+wb.getString(27)+"^"+
						wb.getString(28)+"^"+wb.getString(29)+"^"+wb.getString(30)+"^"+wb.getString(31)+"^"+wb.getString(32)+"^"+wb.getString(33)+"^"+wb.getString(34)+"^"+wb.getString(35)+"^"+wb.getString(36)+"^"+wb.getString(37)+"^"+
						wb.getString(38)+"^"+wb.getString(39)+"^"+wb.getString(40)+"^"+wb.getString(41)+"^"+wb.getString(42)+"^"+wb.getString(43)+"^"+wb.getString(44)+"^"+wb.getString(45)+"^"+wb.getString(46)+"^"+wb.getString(47)+"^"+
						wb.getString(48)+"^"+wb.getString(49)+"^"+wb.getString(50)+"^"+wb.getString(51)+"^"+wb.getString(52)+"^"+wb.getString(53)+"^"+wb.getString(54)+"^"+wb.getString(55)+"^"+wb.getString(56)+"^"+wb.getString(57)+"^"+wb.getString(58)+"^"+
						wb.getString(59);
						
						
						
						br.append("<input type='hidden' name='strHiddComplaintAppDtls'   value='"+strHiddComplaintAppDtls+"'>");
						br.append("<table class='TABLE_STYLE'>");
						br.append("<tr>");
						br.append("<td width='25%' class='LABEL_TD'>Is Item in Working Condition</td>");
						if(wb.getString(30).equals("0"))
						{
						    br.append("<td width='25%' class='CONTROL_TD'>YES</td>");
						}
						else
						{
							br.append("<td width='25%' class='CONTROL_TD'>NO</td>");	
						}	
						if(wb.getString(30).equals("1"))
						{
							br.append("<td width='25%' class='LABEL_TD'>Date/Time</td>");
							br.append("<td width='25%' class='CONTROL_TD'>"+wb.getString(28)+"</td>");
						}
						else
						{
							br.append("<td width='25%' class='CONTROL_TD'></td>");
							br.append("<td width='25%' class='CONTROL_TD'></td>");
						}							
						br.append("</tr>");
						br.append("<tr>");
						br.append("<td width='25%' class='LABEL_TD'>");
						br.append("<font color='red'>*</font>Preferred From Time</td>");
							
						br.append("<td width='25%' class='CONTROL_TD'>"+wb.getString(26)+"</td>");
							
						br.append("<td width='25%' class='LABEL_TD'>");
						br.append("<font color='red'>*</font>Preferred To Time</td>");
							
						br.append("<td width='25%' class='CONTROL_TD'>"+wb.getString(27)+"</td>");
						br.append("</tr>");
						br.append("<tr>");
						br.append("<td class='LABEL_TD'>Contact Person Name</td>");
						br.append("<td class='CONTROL_TD'>"+wb.getString(29)+"</td>");
						br.append("<td width='25%' class='LABEL_TD'>Contact No.</td>");
						br.append("<td class='CONTROL_TD'>"+wb.getString(31)+"</td>");
						br.append("</tr>");
						br.append("<tr>");
						br.append("<td class='LABEL_TD'>Land Mark Description</td>");
						br.append("<td class='CONTROL_TD' colspan='3'>"+wb.getString(16)+"</td>");
						br.append("</tr>");
						if(strIsShow.equals("1"))
						{	
							br.append("<tr>");
							br.append("<td class='LABEL_TD'>Status</td>");
							br.append("<td class='CONTROL_TD'>"+wb.getString(55)+"</td>");
							br.append("<td width='25%' class='CONTROL_TD'></td>");
							br.append("<td class='CONTROL_TD'></td>");
							br.append("</tr>");
							br.append("<tr>");
							br.append("<td class='LABEL_TD'>Verified By</td>");
							br.append("<td class='CONTROL_TD'>"+wb.getString(60)+"</td>");
							br.append("<td width='25%' class='CONTROL_TD'>Verified Date</td>");
							br.append("<td class='CONTROL_TD'>"+wb.getString(34)+"</td>");
							br.append("</tr>");
							
							br.append("<tr>");
							br.append("<td class='LABEL_TD'>Remarks</td>");
							br.append("<td class='CONTROL_TD' colspan='3'>"+wb.getString(33)+"</td>");
							br.append("</tr>");
						}
						br.append("</table>");
					}
					
				}
			
		   }
					
			else 
			{
				br.append("<his:ContentTag>");
				br.append("<table class='TABLE_STYLE'>");
				
				br.append("<td colspan='5'  CLASS='CONTROL_TD' style='text-align:center' ><DIV class='errMsg' align='center'>No Previous Validated Details !!! </div></TD>");
	
				br.append("</table>");
				br.append("</his:ContentTag>");

			}
		}
		catch (Exception e) 
		{
			throw new Exception("DocumentComparativeStatementTransHLP.getQuotedSupplierDtls()->"+e.getMessage());
			
		}
		return br.toString();
	}
	
	
	
	
	/**
     * Method is Used to Save Data into table HEMT_COMPLAINT_STATUS_DTL
     * @param ItemWarrantyDtlsFB_p
     * @param request_p
     */		    
    public static boolean saveData(HemComplaintApprovalDeskFB hemComplaintApprovalDeskFB_p, HttpServletRequest request_p) 
    {
		String strErrMsg;
		String strSeatId_p;
		String strHospitalCode_p;
		UserVO userVo = null;
		boolean    retValue = true;
		HemtComplaintStatusDtlVO hemtComplaintStatusDtlsVO =null;
		ComplaintRequestDtlVO hemtComplaintRequestDtlsVO =null;
		HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO =null;
		
		HisUtil hisutil = null;
		String strChk;
		String strTmp[] = null;
		try
		{					     
						
			           userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode_p = userVo.getHospitalCode();
			      strSeatId_p = userVo.getSeatId();
			 hemtComplaintStatusDtlsVO =  new HemtComplaintStatusDtlVO();	
			hemtComplaintRequestDtlsVO =  new ComplaintRequestDtlVO();
			hemtComplaintApprovalDtlVO =  new HemtComplaintApprovalDtlVO();
			
			                   hisutil = new HisUtil("BMED Transactions", "");
			
				strChk = hemComplaintApprovalDeskFB_p.getStrChk();
			
			strTmp = strChk.split("\\@");
			
            //System.out.println("Req Dtl:::::>"+hemComplaintApprovalDeskFB_p.getStrHiddComplaintAppDtls());
			
			String        strReqType = hemComplaintApprovalDeskFB_p.getStrHiddComplaintAppDtls().split("\\^")[2];
			String    strComplStatus = hemComplaintApprovalDeskFB_p.getStrStatus();
			
			//System.out.println("Req Type 1--Internal::2--External::"+strReqType);
			//System.out.println("App or Rejected::::"+hemComplaintApprovalDeskFB_p.getStrStatus());
			
			
			hemComplaintApprovalDeskFB_p.setStrDeptId(hemComplaintApprovalDeskFB_p.getStrDeptId());
			hemComplaintApprovalDeskFB_p.setStrComplaintId(strTmp[0]);
			hemComplaintApprovalDeskFB_p.setStrComplaintSlNo(strTmp[1]);
			hemComplaintApprovalDeskFB_p.setStrHospCode(strHospitalCode_p);
			
			/* Three table is Used here  */
			/* This is for table HEMT_COMPLAINT_STATUS_DTL */
			if(strReqType.equals("1") && strComplStatus.equals("1"))
			{	
				hemtComplaintStatusDtlsVO.setStrMainStatus("2");
				hemtComplaintStatusDtlsVO.setStrSubStatus("20");
				hemtComplaintStatusDtlsVO.setStrRemarks("Approved: Approved Remarks");
			}
			if(strReqType.equals("2") && strComplStatus.equals("1"))
			{
				hemtComplaintStatusDtlsVO.setStrMainStatus("2");
				hemtComplaintStatusDtlsVO.setStrSubStatus("10");
				hemtComplaintStatusDtlsVO.setStrRemarks("Approved: Approved Remarks");
			}
			if((strReqType.equals("1")||strReqType.equals("2")) && strComplStatus.equals("2"))
			{
				hemtComplaintStatusDtlsVO.setStrMainStatus("3");
				hemtComplaintStatusDtlsVO.setStrSubStatus("20");
				hemtComplaintStatusDtlsVO.setStrRemarks("Rejected: Rejected Remarks");
			}
			
			if(strReqType.equals("1") && strComplStatus.equals("0"))
			{	
				hemtComplaintStatusDtlsVO.setStrMainStatus("2");
				hemtComplaintStatusDtlsVO.setStrSubStatus("20");
				hemtComplaintStatusDtlsVO.setStrRemarks("Pending: Pending Remarks");
			}
			
				hemtComplaintStatusDtlsVO.setStrReqId(strTmp[0]);
				hemtComplaintStatusDtlsVO.setStrMode("1");   // INSERT
				hemtComplaintStatusDtlsVO.setStrSlNo("0"); // Genrated by function
				hemtComplaintStatusDtlsVO.setStrTransId("0"); // Not Define in LLD
				hemtComplaintStatusDtlsVO.setStrActionId("0"); // According to LLD
				hemtComplaintStatusDtlsVO.setStrRemarks(hemComplaintApprovalDeskFB_p.getStrRemarks());
				if(strComplStatus.equals("1"))
				{	
				     hemtComplaintStatusDtlsVO.setStrIsValid("1");
				}
				else
				{
					 hemtComplaintStatusDtlsVO.setStrIsValid("0");
				}	
				hemtComplaintStatusDtlsVO.setStrSeatId(strSeatId_p);
				hemtComplaintStatusDtlsVO.setStrHospitalCode(strHospitalCode_p);
				/* End table HEMT_COMPLAINT_STATUS_DTL */								
				
				
				/* This is for table HEMT_COMPLAINT_REQUEST_DTL */
				hemtComplaintRequestDtlsVO.setStrMode("7");				 // UPDATE				
				if(strReqType.equals("2") && strComplStatus.equals("1"))
				{
					hemtComplaintRequestDtlsVO.setStrMainStatus("2");
					hemtComplaintRequestDtlsVO.setStrSubStatus("10");
					hemtComplaintRequestDtlsVO.setStrStatusRemarks("Approved: Approved Remarks");
				}
				if(strReqType.equals("1") && strComplStatus.equals("1"))
				{	
					hemtComplaintRequestDtlsVO.setStrMainStatus("2");
					hemtComplaintRequestDtlsVO.setStrSubStatus("20");
					hemtComplaintRequestDtlsVO.setStrStatusRemarks("Approved: Approved Remarks");
				}
				if((strReqType.equals("1")||strReqType.equals("2")) && strComplStatus.equals("2"))
				{
					hemtComplaintRequestDtlsVO.setStrMainStatus("3");
					hemtComplaintRequestDtlsVO.setStrSubStatus("20");
					hemtComplaintRequestDtlsVO.setStrStatusRemarks("Rejected: Rejected Remarks");
				}
							
				hemtComplaintRequestDtlsVO.setStrClosedSeatid(userVo.getUserSeatId());
				hemtComplaintRequestDtlsVO.setStrReqId(strTmp[0]);
				hemtComplaintRequestDtlsVO.setStrHospitalCode(strHospitalCode_p);
				hemtComplaintRequestDtlsVO.setStrClosedDate(hisutil.getASDate("dd-MMM-yyyy"));
				hemtComplaintRequestDtlsVO.setStrCancelSeatid(strSeatId_p);
				
				// Dummy Value
				hemtComplaintRequestDtlsVO.setStrReqDate(hisutil.getASDate("dd-MMM-yyyy"));
				hemtComplaintRequestDtlsVO.setStrNotWorkingDate(hisutil.getASDate("dd-MMM-yyyy"));
				hemtComplaintRequestDtlsVO.setStrEntryDate(hisutil.getASDate("dd-MMM-yyyy"));
				hemtComplaintRequestDtlsVO.setStrCancelDate(hisutil.getASDate("dd-MMM-yyyy"));
				hemtComplaintRequestDtlsVO.setStrClosedDate(hisutil.getASDate("dd-MMM-yyyy"));
				hemtComplaintRequestDtlsVO.setStrReqType("0");
				hemtComplaintRequestDtlsVO.setStrIsAttached("0");
				hemtComplaintRequestDtlsVO.setStrIsItem("0");
				hemtComplaintRequestDtlsVO.setStrEnggItemTypeId("0");
				hemtComplaintRequestDtlsVO.setStrIsPreventive("0");
				hemtComplaintRequestDtlsVO.setStrIsOnline("0");
				hemtComplaintRequestDtlsVO.setStrEnggItemSubTypeId("0");
				hemtComplaintRequestDtlsVO.setStrItemId("0");
				hemtComplaintRequestDtlsVO.setStrHemFlag("0");
				hemtComplaintRequestDtlsVO.setStrSerialNo("0");
				hemtComplaintRequestDtlsVO.setStrBatchNo("0");
				hemtComplaintRequestDtlsVO.setStrIsWorking("0");
				hemtComplaintRequestDtlsVO.setStrIsvalid("0");
				hemtComplaintRequestDtlsVO.setStrCost("0");
				
				/* End table HEMT_COMPLAINT_REQUEST_DTL */
				
				/* This is for table HEMT_COMPLAINT_APPROVAL_DTL */
				hemtComplaintApprovalDtlVO.setStrMode("3");       // UPDATE
				hemtComplaintApprovalDtlVO.setStrApprovedBy(userVo.getUserName());   
				hemtComplaintApprovalDtlVO.setStrApprovalStatus(hemComplaintApprovalDeskFB_p.getStrStatus());
				hemtComplaintApprovalDtlVO.setStrApprovedDate(hisutil.getASDate("dd-MMM-yyyy"));
				hemtComplaintApprovalDtlVO.setStrReqId(strTmp[0]);
				hemtComplaintApprovalDtlVO.setStrSlNo(hemComplaintApprovalDeskFB_p.getStrComplaintSlNo());
				hemtComplaintApprovalDtlVO.setStrHospitalCode(strHospitalCode_p);
							
				/* End table HEMT_COMPLAINT_APPROVAL_DTL */
				
				
		       /**Calling BO Method Here**/
			   BmedTransBO.saveComplaintApprovalData(hemtComplaintStatusDtlsVO,hemtComplaintRequestDtlsVO,hemtComplaintApprovalDtlVO);
			   if (hemtComplaintStatusDtlsVO.getStrMsgType().equals("0")) 
			   {
				   hemComplaintApprovalDeskFB_p.setStrNormalMsg("Data Extended Successfully");
			   }
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		 retValue = false;
		 strErrMsg = "HemComplaintApprovalDeskDATA.saveData() --> "	+ e.getMessage();
		 HisException eObj = new HisException("BMED", "HemComplaintApprovalDeskDATA",strErrMsg);
		 hemComplaintApprovalDeskFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");

		eObj = null;
		}
		return retValue;
	}
   
	public static void getUploadedFile(
			HemComplaintApprovalDeskFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p)

	{
		String strmsgText = null;
		String strFileName = "";
		File f = null;
		FileInputStream fis = null;
		byte[] fileContent = new byte[1024];
		BmedConfigUtil bmed =null;
		String strFtpFolderName ="";
		UserVO userVo = null;
		
		try 
		{
			   
			   bmed = new BmedConfigUtil();
			   userVo = ControllerUTIL.getUserVO(request_p);
			   strFtpFolderName = bmed.getStrFtpFileFolder("15", userVo.getHospitalCode());
		       
			  
			   
			   if(strFtpFolderName.equals("")||strFtpFolderName==null)
			   {
				    strFtpFolderName = "bmedDOCS";
			   } 
			
			
			strFileName = complaintMaintenanceStatusFB_p.getStrUploadFileId();
						
			//System.out.println("File Name::::::"+ItemWarrantyDtlsFB_p.getStrUploadFileId());
			String[] strTemp = strFileName.replace(".", "#").split("#");
			String strExt = strTemp[strTemp.length - 1];
			
			 String file=strTemp[0];
			 strFileName=file+"."+strExt;
			
			
			 if (strExt.equalsIgnoreCase("txt")
					|| strExt.equalsIgnoreCase("txt")) {
				
				response_p.setContentType("application/txt");
				response_p.setHeader("Content-disposition",
						" filename="+strFileName);
				
			}
			 else if (strExt.equalsIgnoreCase("pdf")) 
			{
                
				response_p.setContentType("application/pdf");
				response_p.setHeader("Content-disposition",	"attachment; filename="+strFileName);

			} else if (strExt.equalsIgnoreCase("html")
					|| strExt.equalsIgnoreCase("htm")) {
				
				response_p.setContentType("text/html;charset=utf-8");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			}else if (strExt.equalsIgnoreCase("xml")) {
				
				response_p.setContentType("application/xml");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			} else if (strExt.equalsIgnoreCase("doc") || strExt.equalsIgnoreCase("docx")) {
				
				response_p.setContentType("application/msword");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			} else if (strExt.equalsIgnoreCase("rdf")) {
				
				response_p.setContentType("application/msword");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			} else if (strExt.equalsIgnoreCase("rtf")) {
				
				response_p.setContentType("application/msword");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			} else if(strExt.equalsIgnoreCase("png")){

				response_p.setContentType("image/png");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			} else if(strExt.equalsIgnoreCase("gif")){

				response_p.setContentType("image/gif");
				response_p.setHeader("Content-disposition",
					"attachment; filename="+strFileName);
			
			} else if(strExt.equalsIgnoreCase("jpeg") || strExt.equalsIgnoreCase("jpg")){
	
				response_p.setContentType("image/jpg");
				response_p.setHeader("Content-disposition",
					"attachment; filename="+strFileName);
			
			} else {

				response_p.setContentType("text/html;charset=utf-8");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			}			/*******************************************************************/
			 
			 fis = new FileInputStream( new File(HisUtil.getParameterFromHisPathXML("TEMP_PATH")+"/"+strFileName));


			 /* String sessionFtpAddress=EMMSStaticConfigurator.bmedpath; //populate from session 10.0.5.152/ftpserver
			  //String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
			  String Fileurl= "ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
			 
			  System.out.println("test:::"+Fileurl);
			 
			  URL                  urlftp = new URL(Fileurl+"/"+strFileName);
			  URLConnection          urlc =	urlftp.openConnection();
			  InputStream              io = urlc.getInputStream();
			  		  
			 
			 */
			        FileOutputStream fos = new FileOutputStream(strFileName);
			        byte[] buf = new byte[4096];
			        int read = 0;
			        while ((read = fis.read(buf)) > 0) 
			        {
			            fos.write(buf, 0, read);
			        }	    				  				  	  
			  
			     f = new File(strFileName);

				if (!f.isFile()) 
				{

					throw new Exception("Invalid File Path");
				}

				fis = new FileInputStream(f);

				while (fis.read(fileContent) != -1) 
				{
	                
					response_p.getOutputStream().write(fileContent);
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "HemComplaintApprovalDeskDATA.getUploadedFile --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed","HemComplaintApprovalDeskDATA->getUploadedFile()", strmsgText);
			complaintMaintenanceStatusFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
			eObj = null;
		} finally {
	
			if (f != null)
				f = null;
			if (fis != null)
				fis = null;
		}
	}

}
