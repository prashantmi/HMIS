package bmed.transactions.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import bmed.EMMSStaticConfigurator;
import bmed.global.controller.util.BmedConfigUtil;
import bmed.transactions.bo.BmedGlobalBO;
import bmed.transactions.bo.BmedTransBO;
import bmed.transactions.controller.fb.ComplaintLogOfflineFB;
import bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB;
import bmed.vo.ComplaintAttendDtlVO;
import bmed.vo.ComplaintMaintenanceStatusVO;
import bmed.vo.ComplaintRequestDtlVO;
import bmed.vo.ComplaintScheduleDtlVO;
import bmed.vo.HemDeskVO;
import bmed.vo.HemtComplaintApprovalDtlVO;
import bmed.vo.HemtComplaintEscalationDtlVO;
import bmed.vo.HemtComplaintStatusDtlVO;
import bmed.vo.HemtItemMcDtlVO;
import bmed.vo.HemtItemSparePartDtlVO;
import bmed.vo.HemtReminderDtlVO;
import bmed.vo.ItemBrandMstVO;
import bmed.vo.SemtConfigPropertyMstVO;
import bmed.vo.ServiceEnggMstVO;
import bmed.vo.TaskMstVO;
import bmed.vo.WarrantyDtlVO;

public class ComplaintLogOfflineDATA {

	public static void initializeMain(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		String strErrMsg;

		BmedGlobalBO bmedGlobalBO = null;

		ComplaintMaintenanceStatusVO complaintMaintenanceStatusVO = null;

		String strDepartmentComboOptions = null;
		String strHospitalCode,strSeatId;

		UserVO userVo = null;

		try {

			bmedGlobalBO = new BmedGlobalBO();

			complaintMaintenanceStatusVO = new ComplaintMaintenanceStatusVO();

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			strSeatId		= userVo.getSeatId();
			complaintMaintenanceStatusVO.setStrHospitalCode(strHospitalCode);

			/* Combo Testing */
			/*
			 * strDepartmentComboOptions = bmedGlobalBO
			 * .getItemBrandComboOptionsOnItemCategory(strHospitalCode, "18");
			 * System.out.println(strDepartmentComboOptions);
			 * strDepartmentComboOptions = bmedGlobalBO
			 * .getItemBrandComboOptionsOnItemCategoryAndDepartment(
			 * strHospitalCode, "18", "132");
			 * System.out.println(strDepartmentComboOptions);
			 * strDepartmentComboOptions = bmedGlobalBO
			 * .getItemBrandComboOptionsOnItemCategoryAndStore( strHospitalCode,
			 * "18", "10102101"); System.out.println(strDepartmentComboOptions);
			 * strDepartmentComboOptions = bmedGlobalBO
			 * .getItemSubTypeComboOptions(strHospitalCode, "10");
			 * System.out.println(strDepartmentComboOptions);
			 * strDepartmentComboOptions = bmedGlobalBO
			 * .getItemTypeComboOptions(strHospitalCode);
			 * System.out.println(strDepartmentComboOptions);
			 * strDepartmentComboOptions = bmedGlobalBO.getStoreComboOptions(
			 * strHospitalCode, "10049");
			 * System.out.println(strDepartmentComboOptions);
			 * strDepartmentComboOptions = bmedGlobalBO.getStoreComboOptions(
			 * strHospitalCode, "10049", "132");
			 * System.out.println(strDepartmentComboOptions);
			 */
			/* End Combo Testing */

			strDepartmentComboOptions = bmedGlobalBO.getDepartmentComboOptions(strHospitalCode,strSeatId,2);
			complaintMaintenanceStatusFB_p.setStrDeptOptions(strDepartmentComboOptions);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeMain --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	public static void getAjaxComplaintRequestData(
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strErrMsg;
		String strHospitalCode;
		String strDeptCode;
		String strIsAttached;
		String strEmpId;
		String strMsgStatus;
		String strResponse = "";
		String strComplaintRequestNonPreventive;
		String strComplaintRequestPreventive;
		final int nNoOfRowsPerPage = 5;

		BmedTransBO bmedTransBO = null;

		ComplaintRequestDtlVO complaintRequestDtlNonPreventiveVO = null;
		ComplaintRequestDtlVO complaintRequestDtlPreventiveVO = null;
		HemtReminderDtlVO hemtReminderDtlVO = null;

		UserVO userVo = null;

		try {

			bmedTransBO = new BmedTransBO();

			complaintRequestDtlNonPreventiveVO = new ComplaintRequestDtlVO();
			complaintRequestDtlPreventiveVO = new ComplaintRequestDtlVO();
			hemtReminderDtlVO = new HemtReminderDtlVO();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			strEmpId = userVo.getUserEmpID();

			strDeptCode = request_p.getParameter("strDeptCode");
			strIsAttached = request_p.getParameter("strIsAttached");
			if (strIsAttached == null) {
				strIsAttached = "0";
			}

			/* Setting Non Preventive VO */
			complaintRequestDtlNonPreventiveVO
					.setStrHospitalCode(strHospitalCode);
			complaintRequestDtlNonPreventiveVO.setStrDeptId(strDeptCode);
			complaintRequestDtlNonPreventiveVO.setStrIsAttached(strIsAttached);
			complaintRequestDtlNonPreventiveVO.setStrEmpId(strEmpId);
			complaintRequestDtlNonPreventiveVO.setStrIsPreventive("0");

			/* Setting Preventive VO */
			complaintRequestDtlPreventiveVO.setStrHospitalCode(strHospitalCode);
			complaintRequestDtlPreventiveVO.setStrDeptId(strDeptCode);
			complaintRequestDtlPreventiveVO.setStrIsAttached(strIsAttached);
			complaintRequestDtlPreventiveVO.setStrEmpId(strEmpId);
			complaintRequestDtlPreventiveVO.setStrIsPreventive("1");

			/* BO operations */
			bmedTransBO
					.getComplaintRequestData(complaintRequestDtlNonPreventiveVO);
			bmedTransBO
					.getComplaintRequestData(complaintRequestDtlPreventiveVO);

			/*
			 * Setting Reminder Detail VO
			 */
			hemtReminderDtlVO.setStrHospitalCode(strHospitalCode);

			strComplaintRequestNonPreventive = getComplaintRequestData(
					complaintRequestDtlNonPreventiveVO.getWrsData(),
					"activeComplaintsTable", nNoOfRowsPerPage,
					hemtReminderDtlVO);
			strComplaintRequestPreventive = getComplaintRequestData(
					complaintRequestDtlPreventiveVO.getWrsData(),
					"activePreventiveMaintenanceTable", nNoOfRowsPerPage,
					hemtReminderDtlVO);
			strMsgStatus = "SUCCESS";
			strResponse = strMsgStatus + "####"
					+ strComplaintRequestNonPreventive + "^^^^"
					+ strComplaintRequestPreventive;
			// System.out.println(strComplaintRequestNonPreventive);

		} catch (Exception e) {

			strMsgStatus = "ERROR";

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.getAjaxComplaintRequestData --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);

			strResponse = "Application Error [ERROR ID : " + eObj.getErrorID()
					+ "],Contact System Administrator! ";

			strResponse = strMsgStatus + "####" + strResponse;

			eObj = null;
		} finally {
			try {
				response_p.getWriter().print(strResponse);
			} catch (IOException e) {
				new HisException("bmed", "ComplaintMaintenanceStatusDATA",
						"ComplaintMaintenanceStatusTransDATA.getAjaxComplaintRequestData -->"
								+ e.getMessage());
			}
		}

	}

	private static String getComplaintRequestData(WebRowSet webRowSet_p,
			String strTableId_p, int nNoOfRowsPerPage,
			HemtReminderDtlVO hemtReminderDtlVO_p) throws Exception {

		StringBuffer sbComplaintRequestData = new StringBuffer(500);

		// HEMNUM_REQ_ID: 1
		// HEMNUM_REQ_DATE: 2
		// HEMNUM_REQ_TYPE: 3
		// HEMNUM_ITEM_ID: 4
		// HEMSTR_SERIAL_NO: 5
		// HEMNUM_MAIN_STATUS: 6
		// HEMNUM_SUB_STATUS: 7
		// GSTR_REMARKS: 8
		// REQ_TYPE: 9
		// ITEM_NAME: 10
		// STATUS: 11
		// OPTIONS:12
		// webRowSet.getString(1);
		int nPageIndex = 0;
		int nNoOrRows = 0;
		int nTotalNoOfRows = 0;

		while (webRowSet_p.next()) {
			++nTotalNoOfRows;
			if (nNoOrRows % nNoOfRowsPerPage == 0) {
				nNoOrRows = 0;
				++nPageIndex;
			}
			++nNoOrRows;

			hemtReminderDtlVO_p.setStrReqId(webRowSet_p.getString(1));
			StringBuffer sbReminderURL=new StringBuffer(50);
			String strReminderPopup=getReminderPopupTable(strTableId_p + "_"
						+ nPageIndex + "_" + nNoOrRows
						+ "_REMINDER_DIV",
						webRowSet_p.getString(2),
						hemtReminderDtlVO_p,sbReminderURL) ;

			sbComplaintRequestData.append("<tr id=\"" + strTableId_p + "_"
					+ nPageIndex + "_" + nNoOrRows + "\">");
			sbComplaintRequestData
					.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><a style=\"color:blue;cursor: pointer\" onclick=\"showView('"
							+ webRowSet_p.getString(1)
							+ "')\">"
							+ webRowSet_p.getString(1)
							+ " / "
							+ webRowSet_p.getString(2) + "</a></td>");
			sbComplaintRequestData
					.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
							+ webRowSet_p.getString(9) + "</td>");
			sbComplaintRequestData
					.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
							+ webRowSet_p.getString(10)
							+ "("
							+ webRowSet_p.getString(5) + ")</td>");
			sbComplaintRequestData
					.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
							+ webRowSet_p.getString(11) + "</td>");
			sbComplaintRequestData
					.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><select name=\"strActionId\" id=\""
							+ strTableId_p
							+ "_"
							+ nPageIndex
							+ "_"
							+ nNoOrRows
							+ "_action\" class=\"COMBO_NORMAL\" tabindex=\"1\">"
							+ webRowSet_p.getString(12)
							+ "</select></td>");
			
			sbComplaintRequestData
			.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
					+strReminderPopup+sbReminderURL.toString()+ "</td>");
			sbComplaintRequestData
					.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><img class=\"button\" tabindex=\"1\" src='/AHIMS/hisglobal/images/GO.png' title=\"Go\" style=\"cursor: pointer\" onclick=\"goAction('"
							+ strTableId_p
							+ "_"
							+ nPageIndex
							+ "_"
							+ nNoOrRows
							+ "_action','"
							+ webRowSet_p.getString(1)
							+ "');\" onkeypress=\"if(event.keyCode==13) { goAction('"
							+ strTableId_p
							+ "_"
							+ nPageIndex
							+ "_"
							+ nNoOrRows
							+ "_action','"
							+ webRowSet_p.getString(1)
							+ "'); }\" /></td>");
			sbComplaintRequestData.append("</tr>");

		}

		
		if(sbComplaintRequestData.toString().trim().length() > 0){
		
			return sbComplaintRequestData.toString() + "^^"
			+ getPagination(strTableId_p, nNoOfRowsPerPage, nPageIndex);
		}else{
			
			return "";
		}
		
	}

	private static String getPagination(String strTableId_p,
			int nNumberOfRowsPerPage_p, int nNumberOfPages_p) {

		StringBuffer sbPagination = new StringBuffer(100);
		for (int i = 1; i <= nNumberOfPages_p; ++i) {
			sbPagination
					.append("<a id=\""
							+ strTableId_p
							+ "_"
							+ i
							+ "\" tabindex=\"1\" style=\"cursor: pointer;\" onclick=\"pagination('"
							+ strTableId_p + "','" + i + "', '"
							+ nNumberOfRowsPerPage_p + "','" + nNumberOfPages_p
							+ "')\">&nbsp;" + i + "&nbsp;</a>");
		}
		return sbPagination.toString();
	}

	public static void initializeCancel(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		String strErrMsg;
		BmedGlobalBO bmedGlobalBO = null;
		BmedTransBO bmedTransBO = null;
		String strCancelTypeComboOptions = null;
		String strHospitalCode = null;
		String strReqId = null;
		final String strWarrantyDetailsTable;
		final String strMaintenanceContractDetailsTable;
		final String strSchedulesDetailsTable;
		final String strAttenderDetailsTable;

		final String strComplaintId;
		final String strComplaintDate;
		final String strItemName;
		final String strItemBatchNo;
		final String strItemSerialNo;
		final String strManufacturerSerialNo;
		final String strComplaintDescription;

		UserVO userVo = null;
		WarrantyDtlVO warrantyDtlVO = null;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		ComplaintScheduleDtlVO complaintScheduleDtlVO = null;
		ComplaintAttendDtlVO complaintAttendDtlVO = null;

		try {

			bmedGlobalBO = new BmedGlobalBO();
			bmedTransBO = new BmedTransBO();
			warrantyDtlVO = new WarrantyDtlVO();
			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			complaintScheduleDtlVO = new ComplaintScheduleDtlVO();
			complaintAttendDtlVO = new ComplaintAttendDtlVO();

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			complaintScheduleDtlVO.setStrHospitalCode(strHospitalCode);
			complaintAttendDtlVO.setStrHospitalCode(strHospitalCode);

			/*
			 * Getting Request Data:
			 */
			strReqId = complaintMaintenanceStatusFB_p.getStrHiddenComplaintId();

			complaintRequestDtlVO.setStrReqId(strReqId);
			complaintScheduleDtlVO.setStrReqId(strReqId);
			complaintAttendDtlVO.setStrReqId(strReqId);

			bmedTransBO.initializeComplaintActions(complaintRequestDtlVO,warrantyDtlVO, hemtItemMcDtlVO,
							complaintScheduleDtlVO, complaintAttendDtlVO, null,
							false, null, false, null, false);

			strComplaintId = complaintRequestDtlVO.getStrReqId();
			strComplaintDate = complaintRequestDtlVO.getStrReqDate();
			strItemName = complaintRequestDtlVO.getStrItemName();
			strItemBatchNo = complaintRequestDtlVO.getStrBatchNo();
			strItemSerialNo = complaintRequestDtlVO.getStrSerialNo();
			strManufacturerSerialNo = complaintRequestDtlVO
					.getStrManufSerialNo();
			strComplaintDescription = complaintRequestDtlVO.getStrComplaintDes();

			complaintMaintenanceStatusFB_p.setStrComplaintId(strComplaintId);
			complaintMaintenanceStatusFB_p.setStrComplaintDate(strComplaintDate);
			complaintMaintenanceStatusFB_p.setStrItemName(strItemName);
			complaintMaintenanceStatusFB_p.setStrItemBatchNo(strItemBatchNo);
			complaintMaintenanceStatusFB_p.setStrItemSerialNo(strItemSerialNo);
			complaintMaintenanceStatusFB_p.setStrManufacturerSerialNo(strManufacturerSerialNo);
			complaintMaintenanceStatusFB_p.setStrComplaintDescription(strComplaintDescription);

			strWarrantyDetailsTable = getWarrantyDetailsTable(warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");
			strSchedulesDetailsTable = getSchedulesDetailsTable(complaintScheduleDtlVO.getWrsData());
			strAttenderDetailsTable = getAttenderDetailsTable(complaintAttendDtlVO.getWrsData());

			strCancelTypeComboOptions = bmedGlobalBO.getCancelTypeComboOptions(strHospitalCode);

			complaintMaintenanceStatusFB_p.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			complaintMaintenanceStatusFB_p.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			complaintMaintenanceStatusFB_p.setStrSchedulesDetailsTable(strSchedulesDetailsTable);
			complaintMaintenanceStatusFB_p.setStrAttenderDetailsTable(strAttenderDetailsTable);

			complaintMaintenanceStatusFB_p.setStrCancelTypeOptions(strCancelTypeComboOptions);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeCancel --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	/**
	 * Forward Control to Item Complaint Register
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 * 
	 */
	public static void initializeItemComplaintRegister(
			ComplaintLogOfflineFB complaintLogOfflineFB_p,
			HttpServletRequest request_p) {
		String strMsgText;

		String strIsAttached;
		String strStoreId;
		String strItemComboOptions = null;

		SemtConfigPropertyMstVO semtConfigPropertyMstVO = null;

		BmedGlobalBO bmedGlobalBO = null;
		String strDepartmentComboOptions;
		String strEnggItemTypeComboOptions;
		String strStoreComboOptions;
		String strDesignation;
		String strHospitalCode;
		String strSeatId;
		String strDeptCode;
		String strEmpComboOptions;
		String strVendorComboOptions = null;
		UserVO userVO = null;
		ItemBrandMstVO vo = null;
		BmedTransBO bmedTransBO = null;
		HisUtil hisutil =null;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			bmedTransBO = new BmedTransBO();
			vo = new ItemBrandMstVO();
			semtConfigPropertyMstVO = new SemtConfigPropertyMstVO();
			
			hisutil = new HisUtil("bmed", "ComplaintLogOfflineDATA");

			String strCtDate = hisutil.getASDate("dd-MMM-yyyy");

			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strSeatId = userVO.getSeatId();

			strDeptCode = complaintLogOfflineFB_p.getStrDeptCodeNew();

			strDesignation = bmedTransBO.getDesignation(userVO.getUserEmpID(),
					strHospitalCode);

			complaintLogOfflineFB_p.setStrEmpName(userVO.getUserName());
			complaintLogOfflineFB_p.setStrEmpId(userVO.getUserEmpID());

			strDepartmentComboOptions = bmedGlobalBO.getDepartmentComboOptions(strHospitalCode, strDeptCode,strSeatId,2);
			complaintLogOfflineFB_p
					.setStrDeptCombo(strDepartmentComboOptions);
			
			strVendorComboOptions=bmedGlobalBO.getManufacturerNameComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			complaintLogOfflineFB_p.setStrVenderCombo(strVendorComboOptions);
			
			strEmpComboOptions=bmedGlobalBO.getEmployeeNameDeptBasedComboOptions("101",strDeptCode);
			complaintLogOfflineFB_p.setStrEmpComboOptions(strEmpComboOptions);
			

			/*  resetting values */
			
			
			complaintLogOfflineFB_p.setStrProblemDescription("");
			complaintLogOfflineFB_p.setStrSolutionProvided("");
			complaintLogOfflineFB_p.setStrRemarks("");
			complaintLogOfflineFB_p.setStrRemarks("");
			complaintLogOfflineFB_p.setStrReasonForClosing("");
			
			complaintLogOfflineFB_p.setStrAttendDate(strCtDate);
			complaintLogOfflineFB_p.setStrClosingDate(strCtDate);
			
			complaintLogOfflineFB_p.setStrFromDate(strCtDate);
			
			complaintLogOfflineFB_p.setStrVerifyDate(strCtDate);
			
			
			
			
			if (strDeptCode == null)
				strDeptCode = "0";

			strIsAttached = complaintLogOfflineFB_p.getStrIsAttached();

			if (strIsAttached == null)
				strIsAttached = "0";

			if (strIsAttached.equals("1")) {
				strItemComboOptions = bmedTransBO
						.getItemBrandComboOptionsOnDepartment(strHospitalCode,
								strDeptCode);

				complaintLogOfflineFB_p
						.setStrItemNameCombo(strItemComboOptions);

			} else {
				strStoreComboOptions = bmedGlobalBO.getStoreComboOptions(
						strHospitalCode, strSeatId, strDeptCode);

				complaintLogOfflineFB_p
						.setStrStoreNameCombo(strStoreComboOptions);

				strStoreId = complaintLogOfflineFB_p.getStrStoreId() == null ? "0"
						: complaintLogOfflineFB_p.getStrStoreId();
				vo.setStrMode("13");
				strItemComboOptions = bmedGlobalBO
						.getItemBrandComboOptionsOnStore(strHospitalCode,
								strStoreId,vo);

				complaintLogOfflineFB_p
						.setStrItemNameCombo(strItemComboOptions);
			}

			strEnggItemTypeComboOptions = bmedGlobalBO
					.getItemTypeComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			complaintLogOfflineFB_p
					.setStrEngineeringItemTypeCmb(strEnggItemTypeComboOptions);

			semtConfigPropertyMstVO.setStrHospitalCode(strHospitalCode);

			// Getting the Property value from semt_config_property_mst where
			// property Id=14

			semtConfigPropertyMstVO.setStrMode("2");
			semtConfigPropertyMstVO.setStrConfigPropertyId("14");
			bmedTransBO.getPropertyValue(semtConfigPropertyMstVO);
			complaintLogOfflineFB_p
					.setStrConfigPropertyValue(semtConfigPropertyMstVO
							.getStrPropertyValue());

			complaintLogOfflineFB_p.setStrIsItemInWorkingCondition("0");

			if (complaintLogOfflineFB_p.getStrComplaintType() == null) {
				complaintLogOfflineFB_p
						.setStrComplaintType(complaintLogOfflineFB_p
								.getStrPageFlag().equals("ITEM") ? "1" : "2");
			}

			complaintLogOfflineFB_p.setStrDesignation(strDesignation);
			
			
			
		} catch (Exception e) {
			strMsgText = "ComplaintLogOfflineDATA.initializeItemComplaintRegister --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"bmed",
					"ComplaintLogOfflineDATA.initializeItemComplaintRegister()",
					strMsgText);
			complaintLogOfflineFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		} finally {
			strMsgText = null;
		}
	}

	/**
	 * To Get Store Names Data
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 */
	public static void getStoreName(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;
		BmedGlobalBO bmedGlobalBO = null;
		String strStoreComboOptions;
		String strHospitalCode = null;
		UserVO userVO = null;
		String strDeptId;
		String strSeatId;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strSeatId = userVO.getSeatId();

			strDeptId = request_p.getParameter("deptId");
			strStoreComboOptions = bmedGlobalBO.getStoreComboOptions(
					strHospitalCode, strSeatId, strDeptId);
			complaintMaintenanceStatusFB_p
					.setStrStoreNameCombo(strStoreComboOptions);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strStoreComboOptions);

		} catch (Exception e) {
			strMsgText = "ComplaintLogOfflineDATA.getStoreName(ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,HttpServletRequest request_p, HttpServletResponse response_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintLogOfflineDATA.getStoreName()", strMsgText);

			try {
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
			} catch (Exception e1) {
				new HisException("bmed",
						"ComplaintMaintenanceStatusDATA.getStoreName()",
						strMsgText);
			}

			eObj = null;
		} finally {
			bmedGlobalBO = null;
		}
	}
	
	/**
	 * To Get Service  Eng Name
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 */
	
	public static void getServiceEngName(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;
		BmedGlobalBO bmedGlobalBO = null;
		String strServiceEngComboOptions;
		String strHospitalCode = null;
		UserVO userVO = null;
		String strEngSubTypeId;
		String strEngTypeId;
		String strSeatId;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strSeatId = userVO.getSeatId();
			HemDeskVO vo=new HemDeskVO();
			strEngSubTypeId = request_p.getParameter("enggItemSubType");
			strEngTypeId = request_p.getParameter("enggItemType");
			
			vo.setStrHospCode(userVO.getHospitalCode());
			vo.setStrEnggItemSubTypeId(strEngSubTypeId);
			vo.setStrEnggItemTypeId(strEngTypeId);
			vo.setStrMode("6");
			strServiceEngComboOptions = bmedGlobalBO.getServiceEnggNameCombo(
					vo);
			complaintMaintenanceStatusFB_p
					.setStrServiceEngNameCombo(strServiceEngComboOptions);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strServiceEngComboOptions);

		} catch (Exception e) {
			strMsgText = "ComplaintLogOfflineDATA.getServiceEngName(ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,HttpServletRequest request_p, HttpServletResponse response_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA.getStoreName()", strMsgText);

			try {
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
			} catch (Exception e1) {
				new HisException("bmed",
						"ComplaintLogOfflineDATA.getStoreName()",
						strMsgText);
			}

			eObj = null;
		} finally {
			bmedGlobalBO = null;
		}
	}
	
	
	
	
	/**
	 * To Get Vendor Combo Data
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 */
	public static void getVendorCombo(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;
		BmedGlobalBO bmedGlobalBO = null;
		String strStoreComboOptions;
		String strHospitalCode = null;
		UserVO userVO = null;
		String strDeptId;
		String strSeatId;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strSeatId = userVO.getSeatId();

			strDeptId = request_p.getParameter("deptId");
			strStoreComboOptions = bmedGlobalBO.getManufacturerNameComboOptions(strHospitalCode);
					
			complaintMaintenanceStatusFB_p
					.setStrStoreNameCombo(strStoreComboOptions);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strStoreComboOptions);

		} catch (Exception e) {
			strMsgText = "ComplaintLogOfflineDATA.getVendorCombo(ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,HttpServletRequest request_p, HttpServletResponse response_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintLogOfflineDATA.getStoreName()", strMsgText);

			try {
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
			} catch (Exception e1) {
				new HisException("bmed",
						"ComplaintMaintenanceStatusDATA.getStoreName()",
						strMsgText);
			}

			eObj = null;
		} finally {
			bmedGlobalBO = null;
		}
	}
	
	
	public static void getEmpName(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;
		BmedGlobalBO bmedGlobalBO = null;
		String strEmpComboOptions;
		String strHospitalCode = null;
		UserVO userVO = null;
		String strDeptId;
		String strSeatId;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strSeatId = userVO.getSeatId();

			strDeptId = request_p.getParameter("deptId");
			strEmpComboOptions = bmedGlobalBO.getEmployeeNameDeptBasedComboOptions(
					strHospitalCode,  strDeptId);
			complaintMaintenanceStatusFB_p
					.setStrEmpComboOptions(strEmpComboOptions);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strEmpComboOptions);

		} catch (Exception e) {
			strMsgText = "ComplaintLogOfflineDATA.getStoreName(ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,HttpServletRequest request_p, HttpServletResponse response_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintLogOfflineDATA.getStoreName()", strMsgText);

			try {
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
			} catch (Exception e1) {
				new HisException("bmed",
						"ComplaintMaintenanceStatusDATA.getStoreName()",
						strMsgText);
			}

			eObj = null;
		} finally {
			bmedGlobalBO = null;
		}
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * To Get Store Names Data
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 */
	public static void getVendorDetials(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;
		BmedGlobalBO bmedGlobalBO = null;
		String strVenderdeatils;
		String strHospitalCode = null;
		UserVO userVO = null;
		String strVendorId;
		String strSeatId;
		
		BmedTransBO bo=null;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strSeatId = userVO.getSeatId();

			strVendorId = request_p.getParameter("vendorId");
			strVenderdeatils = bmedGlobalBO.getManufacturerDeatils(strHospitalCode,strVendorId);
			
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strVenderdeatils);

		} catch (Exception e) {
			strMsgText = "ComplaintMaintenanceStatusDATA.getStoreName(ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,HttpServletRequest request_p, HttpServletResponse response_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA.getStoreName()", strMsgText);

			try {
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
			} catch (Exception e1) {
				new HisException("bmed",
						"ComplaintMaintenanceStatusDATA.getStoreName()",
						strMsgText);
			}

			eObj = null;
		} finally {
			bmedGlobalBO = null;
		}
	}
	
	
	/**
	 * To Get Emp Designation Details 
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 */
	public static void getEmpDesignationDetails(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;
		String strDesignation;
		String strHospitalCode = null;
		UserVO userVO = null;
		String strEmpId;
		String strSeatId;
		
		BmedTransBO bo=null;
		try {

					/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strSeatId = userVO.getSeatId();
			 bo=new BmedTransBO();
			strEmpId = request_p.getParameter("empId");
			 strDesignation = bo.getDesignation(userVO.getUserEmpID(),
					strHospitalCode);
			
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strDesignation);
			//complaintMaintenanceStatusFB_p.setStrDesignation(strDesignation);

		} catch (Exception e) {
			e.printStackTrace();
			strMsgText = "ComplaintMaintenanceStatusDATA.getStoreName(ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,HttpServletRequest request_p, HttpServletResponse response_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA.getStoreName()", strMsgText);

			try {
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
			} catch (Exception e1) {
				
				e.printStackTrace();
				new HisException("bmed",
						"ComplaintMaintenanceStatusDATA.getStoreName()",
						strMsgText);
			}

			eObj = null;
		} finally {
			
		}
	}
	

	/**
	 * Get Engg Item Sub Type Combo using Ajax.
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the form
	 * @param request_p
	 *            the request
	 * @param response_p
	 *            the response
	 * 
	 * @return the null
	 */
	public static void getEnggItemSubTypeCmb(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;
		BmedGlobalBO bmedGlobalBO = null;
		String strEnggItemSubTypeComboOptions;
		String strHospitalCode = null;
		UserVO userVO = null;
		String strEnggItemTypeId;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strEnggItemTypeId = request_p.getParameter("enggItemType");
			strEnggItemSubTypeComboOptions = bmedGlobalBO
					.getItemSubTypeComboOptions(strHospitalCode,
							strEnggItemTypeId);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strEnggItemSubTypeComboOptions);

		} catch (Exception e) {
			strMsgText = "ComplaintMaintenanceStatusDATA.getEnggItemSubTypeCmb(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA.getEnggItemSubTypeCmb()",
					strMsgText);

			try {
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
			} catch (Exception e1) {
				new HisException(
						"bmed",
						"ComplaintMaintenanceStatusDATA.getEnggItemSubTypeCmb()",
						strMsgText);
			}

			eObj = null;
		} finally {
			bmedGlobalBO = null;
		}
	}

	/*
	 * To Get Item Details Data
	 * 
	 * @param complaintMaintenanceStatusFB_p the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getItemName(ComplaintLogOfflineFB formBean,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;

		BmedGlobalBO bmedGlobalBO = null;
		BmedTransBO bmedTransBO = null;
		ItemBrandMstVO vo = null;
		String strItemComboOptions = null;
		String strHospitalCode;
		String strDeptId;
		String strStoreId;
		UserVO userVO = null;
		String strFlag;
		try {
			bmedGlobalBO = new BmedGlobalBO();
			bmedTransBO = new BmedTransBO();
			vo = new ItemBrandMstVO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strFlag = (String) request_p.getParameter("flag");

			if (strFlag.equals("0")) {
				strStoreId = (String) request_p.getParameter("storeId");
				vo.setStrMode("13");
				strItemComboOptions = bmedGlobalBO
						.getItemBrandComboOptionsOnStore(strHospitalCode,
								strStoreId,vo);

			} else {
				strDeptId = (String) request_p.getParameter("deptId");
				strItemComboOptions = bmedTransBO
						.getItemBrandComboOptionsOnDepartment(strHospitalCode,
								strDeptId);
			}

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strItemComboOptions);
		} catch (Exception e) {
			strMsgText = "ComplaintMaintenanceStatusDATA.getItemName(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA.getItemName()", strMsgText);

			try {
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
			} catch (Exception e1) {
				new HisException("bmed",
						"ComplaintMaintenanceStatusDATA.getItemName()",
						strMsgText);
			}

			eObj = null;
		} finally {
			bmedGlobalBO = null;
		}
	}

	/*
	 * To Get Non-Item Details Data
	 * 
	 * @param complaintMaintenanceStatusFB_p the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getNonItemName(ComplaintLogOfflineFB formBean,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;

		BmedGlobalBO bmedGlobalBO = null;
		String strNonItemComboOptions = null;
		String strHospitalCode;
		// String strDeptId;
		// String strStoreId;
		String strEnggItemTypeId;
		String strEnggItemSubTypeId;
		UserVO userVO = null;
		try {
			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			// strFlag = (String) request_p.getParameter("enggItemSubTypeId");

			strEnggItemTypeId = request_p.getParameter("enggItemType");
			strEnggItemSubTypeId = request_p.getParameter("enggItemSubType");

			/*
			 * if (strFlag.equals("0")) { strStoreId = (String)
			 * request_p.getParameter("enggItemSubTypee");
			 */
			strNonItemComboOptions = bmedGlobalBO
					.getItemBrandComboOptionsOnBasisOfEngg(strHospitalCode,
							strEnggItemTypeId, strEnggItemSubTypeId);

			/*
			 * } else { strDeptId = (String) request_p.getParameter("deptId");
			 * strNonItemComboOptions = bmedGlobalBO
			 * .getItemBrandComboOptionsOnDepartment(strHospitalCode,
			 * strDeptId); }
			 */

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strNonItemComboOptions);
		} catch (Exception e) {
			strMsgText = "ComplaintMaintenanceStatusDATA.getItemName(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA.getItemName()", strMsgText);

			try {
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");
			} catch (Exception e1) {
				new HisException("bmed",
						"ComplaintMaintenanceStatusDATA.getItemName()",
						strMsgText);
			}

			eObj = null;
		} finally {
			bmedGlobalBO = null;
		}
	}

	/*
	 * To Get Stock Details Data
	 * 
	 * @param complaintMaintenanceStatusFB_p the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getStockDetails(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;
		BmedGlobalBO bmedGlobalBO = null;
		String strStockDtl = null;
		String strHospitalCode_p;
		String strItemBrandId_p;
		String strDeptId_p;
		UserVO userVo = null;
		try {
			bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode_p = userVo.getHospitalCode();
			strItemBrandId_p = (String) request_p.getParameter("itemNo");
			strDeptId_p = (String) request_p.getParameter("deptId");
			strStockDtl = bmedGlobalBO.getStockDetails(strHospitalCode_p,
					strItemBrandId_p, strDeptId_p);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strStockDtl);

		} catch (Exception e) {

			strMsgText = "ComplaintMaintenanceStatusTransDATA.getStockDetails() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ComplaintMaintenanceStatusDATA", strMsgText);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To Save Data
	 * 
	 * @param complaintMaintenanceStatusFB_p the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void saveData(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		String strErrMsg;

		String strSeatId_p;
		String strHospitalCode_p;
		UserVO userVo = null;
		HisUtil hisutil = null;
		String strPageFlag;

		ComplaintRequestDtlVO complaintRequestDtlVO;
		HemtComplaintStatusDtlVO hemtComplaintStatusDtlVO;
		HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO;
		ComplaintAttendDtlVO hemtComplaintAttendDtlVO;
		 HemtItemSparePartDtlVO hemtItemSparePartDtlVO = null;
		 HemtItemSparePartDtlVO oldHemtItemSparePartDtlVO = null;
		
		String strOldStoreId = null;
		String strOldItemId = null;
		String strOldSerialNo = null;
		String strOldSparePartId = null;
		String strOldSpareItemSerialNo = null;
		String strOldSpareItemTableSlNo = null;
	
		
		final String strStoreId;
		final String strItemId;
		final String strSerialNo;
		final String strSparePartId;
		final String strSpareItemSerialNo;
		final String strManufSerialNo;
		final String strSpareManufactureSerialNo;
		final String strSpareManufacturerId;
		final String strWarrantyFromDate;
		final String strWarrantyUpto;
		final String strWarrantyUptoUnitId;
		final String strSpecification;
		final String strSparePartStatusId;
		final String strPerformedDate;
		final String strSparePartStockDetailsRadio;
		final String[] arrStringPrimaryKey;
		String strStockInf="";
		String strNonItemId="";
		try {
			hisutil = new HisUtil("bmed", "ComplaintLogOfflineDATA");

			String strCtDate = hisutil.getASDate("dd-MMM-yyyy");
			strPageFlag = complaintMaintenanceStatusFB_p.getStrPageFlag();

			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			hemtComplaintStatusDtlVO = new HemtComplaintStatusDtlVO();
			hemtComplaintApprovalDtlVO = new HemtComplaintApprovalDtlVO();
			hemtComplaintAttendDtlVO = new ComplaintAttendDtlVO();
			
			
			
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode_p = userVo.getHospitalCode();
			strSeatId_p = userVo.getSeatId();

			complaintMaintenanceStatusFB_p.setStrSeatId(strSeatId_p);
			complaintMaintenanceStatusFB_p
					.setStrHospitalCode(strHospitalCode_p);
			complaintMaintenanceStatusFB_p
					.setStrIsValid(Config.IS_VALID_ACTIVE);
			complaintMaintenanceStatusFB_p.setStrCtDate(strCtDate);

			if (complaintMaintenanceStatusFB_p.getStrIsAttached() == null) {
				complaintMaintenanceStatusFB_p.setStrIsAttached("0");
			}

			if (complaintMaintenanceStatusFB_p.getStrComplaintType() == null) {
				complaintMaintenanceStatusFB_p
						.setStrComplaintType(complaintMaintenanceStatusFB_p
								.getStrPageFlag().equals("ITEM") ? "1" : "2");
			}

			/*
			 * private String strStockInf;
			 * //HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
			 * //||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
			 * //GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
			 */

			// HEMT_COMPLAINT_REQUEST_DTL
			complaintRequestDtlVO.setStrMode("1");

			complaintRequestDtlVO.setStrReqId("0"); // Dummy Get By Function
			complaintRequestDtlVO.setStrIsItem(complaintMaintenanceStatusFB_p
					.getStrItemOrNonItemMode());
			complaintRequestDtlVO.setStrReqType(complaintMaintenanceStatusFB_p
					.getStrComplaintType());
			complaintRequestDtlVO
					.setStrIsAttached("0"); // dummy passing value
			complaintRequestDtlVO.setStrReqDate(complaintMaintenanceStatusFB_p
					.getStrCtDate()); // Dummy SysDate
			complaintRequestDtlVO
					.setStrEnggItemTypeId(complaintMaintenanceStatusFB_p
							.getStrEngineeringItemTypeId());
			complaintRequestDtlVO
					.setStrEnggItemSubTypeId(complaintMaintenanceStatusFB_p
							.getStrEngineeringItemSubTypeId());
			if (complaintMaintenanceStatusFB_p.getStrDeptCode() != null) {
				complaintRequestDtlVO
						.setStrDeptId(complaintMaintenanceStatusFB_p
								.getStrDeptCode());
			}

			if (complaintMaintenanceStatusFB_p.getStrDeptCodeNew() != null) {
				complaintRequestDtlVO
						.setStrDeptId(complaintMaintenanceStatusFB_p
								.getStrDeptCodeNew());
			}

			complaintRequestDtlVO.setStrEmpId(complaintMaintenanceStatusFB_p
					.getStrEmpId());

			complaintRequestDtlVO
					.setStrItemId(strPageFlag.equals("ITEM") ? complaintMaintenanceStatusFB_p
							.getStrItemId() : complaintMaintenanceStatusFB_p
							.getStrNonItemId());

			complaintRequestDtlVO.setStrSerialNo("0");
			complaintRequestDtlVO.setStrBatchNo("0");
			complaintRequestDtlVO.setStrManufSerialNo("0");//
			
			
			if (request_p.getParameter("strPageFlag").equals("ITEM")) {
				strStockInf = (String) request_p.getParameter("strStockInf");

				/*
				 * 0 1 2
				 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
				 * 3 4 ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'|| 5 6
				 * 7
				 * GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item
				 * (1 : for Item 2:for Non-Item)
				 */

				
				complaintRequestDtlVO.setStrHospitalCode(strStockInf
						.split("\\^")[5]);
				complaintRequestDtlVO.setStrItemId(strStockInf.split("\\^")[2]);
				complaintRequestDtlVO
						.setStrBatchNo(strStockInf.split("\\^")[3]);// Dummy 0
				complaintRequestDtlVO
						.setStrSerialNo(strStockInf.split("\\^")[4]);// Dummy 0

				

			}

			if (request_p.getParameter("strPageFlag").equals("NON_ITEM")) {
				strNonItemId = complaintMaintenanceStatusFB_p.getStrNonItemId();

				
				complaintRequestDtlVO.setStrHospitalCode(strHospitalCode_p);
				complaintRequestDtlVO.setStrItemId(strNonItemId);
				complaintRequestDtlVO.setStrBatchNo("0");// Dummy 0
				complaintRequestDtlVO.setStrSerialNo("0");// Dummy 0

				
			}
			
			
		
			

			String[] strTemp;
			
			if(complaintMaintenanceStatusFB_p.getStrWarrantyOrMaintenanceSlNoAndType()!=null)
			{
				strTemp = complaintMaintenanceStatusFB_p.getStrWarrantyOrMaintenanceSlNoAndType().split("\\^");
				
				if (strTemp[1].equals("WARRANTY")) {

					complaintRequestDtlVO.setStrWarrantySlNo(strTemp[0]);
					complaintRequestDtlVO.setStrMcSlNo("");

				}

				else if (strTemp[1].equals("MAINTENANCE")) 
				{
					complaintRequestDtlVO.setStrMcSlNo(strTemp[0]);
					complaintRequestDtlVO.setStrWarrantySlNo("");
				}
			}			
			else
			{
				complaintRequestDtlVO.setStrMcSlNo("");
				complaintRequestDtlVO.setStrWarrantySlNo("");
			}

			complaintRequestDtlVO
					.setStrComplaintDes(complaintMaintenanceStatusFB_p
							.getStrComplaintDescription());
			complaintRequestDtlVO
					.setStrIsWorking(complaintMaintenanceStatusFB_p
							.getStrIsItemInWorkingCondition());
			if (complaintMaintenanceStatusFB_p.getStrIsItemInWorkingCondition()
					.equals("1")) {
				complaintRequestDtlVO
						.setStrNotWorkingDate(complaintMaintenanceStatusFB_p
								.getStrComplaintDate()
								+ " "
								+ complaintMaintenanceStatusFB_p
										.getStrComplaintTime() + ":00"); //
			} else {
				complaintRequestDtlVO.setStrNotWorkingDate("");
			}

			complaintRequestDtlVO
					.setStrPreferTimeFr(complaintMaintenanceStatusFB_p
							.getStrPreferredFromTime());
			complaintRequestDtlVO
					.setStrPreferTimeTo(complaintMaintenanceStatusFB_p
							.getStrPreferredToTime());
			complaintRequestDtlVO
					.setStrContactPerson(complaintMaintenanceStatusFB_p
							.getStrContactPersonName());
			complaintRequestDtlVO
					.setStrContactNo(complaintMaintenanceStatusFB_p
							.getStrContactNo());
			complaintRequestDtlVO
					.setStrLandmarkDesc(complaintMaintenanceStatusFB_p
							.getStrLandMarkDescription());
			complaintRequestDtlVO.setStrRemarks(complaintMaintenanceStatusFB_p
					.getStrRemarks());
			complaintRequestDtlVO.setStrMainStatus("1");
			complaintRequestDtlVO.setStrSubStatus("10");
			
			if(complaintMaintenanceStatusFB_p.getStrRemarks()==null)
				complaintMaintenanceStatusFB_p.setStrRemarks(" ");
			complaintRequestDtlVO
					.setStrStatusRemarks(complaintMaintenanceStatusFB_p.getStrRemarks());

			complaintRequestDtlVO
					.setStrStoreId(strPageFlag.equals("ITEM") ? complaintMaintenanceStatusFB_p
							.getStrStoreId() : "0");//

			complaintRequestDtlVO
					.setStrHospitalCode(complaintMaintenanceStatusFB_p
							.getStrHospitalCode());
			complaintRequestDtlVO.setStrIsvalid(complaintMaintenanceStatusFB_p
					.getStrIsValid());
			complaintRequestDtlVO.setStrSeatid(complaintMaintenanceStatusFB_p
					.getStrSeatId());

			// Dummy or default values
			complaintRequestDtlVO.setStrCompIntemation("");//
			complaintRequestDtlVO.setStrIsPreventive("0");// Dummy
			complaintRequestDtlVO.setStrIsOnline("0");// Dummy

			if (complaintMaintenanceStatusFB_p.getStrComplaintType()
					.equals("1")) {
				complaintRequestDtlVO.setStrHemFlag("0");
			} else if (complaintMaintenanceStatusFB_p.getStrComplaintType()
					.equals("2")) {
				complaintRequestDtlVO.setStrHemFlag("1");
			}

			complaintRequestDtlVO.setStrVendorId(complaintMaintenanceStatusFB_p.getStrVendorId());
			complaintRequestDtlVO.setStrMainteId("");// Dummy
			complaintRequestDtlVO.setStrDownTimeFr("");// Dummy
			complaintRequestDtlVO.setStrEntryDate("");// Dummy
			complaintRequestDtlVO.setStrCost("0.00");// Dummy
			complaintRequestDtlVO.setStrVerifiedId("");// Dummy
			complaintRequestDtlVO.setStrVerifiedRemarks("");// Dummy
			complaintRequestDtlVO.setStrCancelId("");// Dummy
			complaintRequestDtlVO.setStrCancelDate("");// Dummy
			complaintRequestDtlVO.setStrCancelRemarks("");// Dummy
			complaintRequestDtlVO.setStrCancelSeatid("");// Dummy
			complaintRequestDtlVO.setStrClosedDate(complaintMaintenanceStatusFB_p.getStrClosingDate());
			complaintRequestDtlVO.setStrClosedSeatid(userVo.getSeatId());// Dummy
			complaintRequestDtlVO.setStrCloseReason("");// Dummy
			if(complaintMaintenanceStatusFB_p.getStrInvoiceNo()!=null)
			complaintRequestDtlVO.setStrVendorInvoiceNo(complaintMaintenanceStatusFB_p.getStrInvoiceNo());
			
			

			// HEMT_COMPLAINT_STATUS_DTL
			hemtComplaintStatusDtlVO.setStrMode("1");

			hemtComplaintStatusDtlVO.setStrReqId("0"); // Dummy
			hemtComplaintStatusDtlVO.setStrSlNo("0"); // Dummy
			hemtComplaintStatusDtlVO.setStrMainStatus("1");
			hemtComplaintStatusDtlVO.setStrSubStatus("10");
			hemtComplaintStatusDtlVO.setStrTransId("0"); // Dummy
			hemtComplaintStatusDtlVO.setStrActionId("0"); // Dummy
			hemtComplaintStatusDtlVO
					.setStrRemarks("Department Head Approval Pending");
			hemtComplaintStatusDtlVO
					.setStrHospitalCode(complaintMaintenanceStatusFB_p
							.getStrHospitalCode());
			hemtComplaintStatusDtlVO
					.setStrIsValid(complaintMaintenanceStatusFB_p
							.getStrIsValid());
			// No entry date field
			hemtComplaintStatusDtlVO
					.setStrSeatId(complaintMaintenanceStatusFB_p.getStrSeatId());

			// HEMT_COMPLAINT_APPROVAL_DTL

			hemtComplaintApprovalDtlVO.setStrMode("1");

			hemtComplaintApprovalDtlVO.setStrReqId("0"); // Dummy
			hemtComplaintApprovalDtlVO.setStrSlNo("10");
			hemtComplaintApprovalDtlVO
					.setStrReqType(complaintMaintenanceStatusFB_p
							.getStrComplaintType());
			hemtComplaintApprovalDtlVO
					.setStrIsWorking(complaintMaintenanceStatusFB_p
							.getStrIsItemInWorkingCondition());
			hemtComplaintApprovalDtlVO
					.setStrEnggItemTypeId(complaintMaintenanceStatusFB_p
							.getStrEngineeringItemTypeId());
			hemtComplaintApprovalDtlVO
					.setStrEnggItemSubTypeId(complaintMaintenanceStatusFB_p
							.getStrEngineeringItemSubTypeId());
			hemtComplaintApprovalDtlVO
					.setStrDeptId(complaintMaintenanceStatusFB_p
							.getStrDeptCode());
			hemtComplaintApprovalDtlVO
					.setStrEmpId(complaintMaintenanceStatusFB_p.getStrEmpId());
			hemtComplaintApprovalDtlVO.setStrApprovalStatus("0");
			hemtComplaintApprovalDtlVO
					.setStrRemarks(complaintMaintenanceStatusFB_p
							.getStrRemarks());
			hemtComplaintApprovalDtlVO
					.setStrHospitalCode(complaintMaintenanceStatusFB_p
							.getStrHospitalCode());
			hemtComplaintApprovalDtlVO
					.setStrIsValid(complaintMaintenanceStatusFB_p
							.getStrIsValid());
			// No entry date field
			hemtComplaintApprovalDtlVO
					.setStrSeatId(complaintMaintenanceStatusFB_p.getStrSeatId());

			// Dummy of Default values
			hemtComplaintApprovalDtlVO.setStrIsPreventive("0");
			hemtComplaintApprovalDtlVO
					.setStrEmpName(complaintMaintenanceStatusFB_p
							.getStrEmpName());
			hemtComplaintApprovalDtlVO.setStrApprovedBy("");
			hemtComplaintApprovalDtlVO.setStrApprovedDate("");
			
			// HEMT_COMPLAINT_ATTEND_DTL
			
			hemtComplaintAttendDtlVO.setStrReqId("0") ;// dummy
			hemtComplaintAttendDtlVO.setStrAttendId("1"); // dummy
			hemtComplaintAttendDtlVO.setStrProbDescrip(complaintMaintenanceStatusFB_p.getStrProblemDescription());
			hemtComplaintAttendDtlVO.setStrVendorId(complaintMaintenanceStatusFB_p.getStrVendorId());
			hemtComplaintAttendDtlVO.setStrAttendedContactPerson(complaintMaintenanceStatusFB_p.getStrAttendedContactPerson());
			hemtComplaintAttendDtlVO.setStrAttendedContactNo(complaintMaintenanceStatusFB_p.getStrCommunicateIdContactId());
			hemtComplaintAttendDtlVO.setStrStatus("0");
			hemtComplaintAttendDtlVO.setStrHospitalCode(strHospitalCode_p);
			hemtComplaintAttendDtlVO.setStrAttendDate(complaintMaintenanceStatusFB_p.getStrAttendDate());
			hemtComplaintAttendDtlVO.setStrIsWorking(complaintMaintenanceStatusFB_p.getStrIsItemInWorkingCondition());
			hemtComplaintAttendDtlVO.setStrAttendDate(complaintMaintenanceStatusFB_p.getStrAttendDate());
			
			System.out.println("request_p.getParameter(strIsSparePartsMaintenanceInvolved)"+complaintMaintenanceStatusFB_p.getStrSparePartsFlag());
			hemtComplaintAttendDtlVO.setStrIsSpareReq(complaintMaintenanceStatusFB_p.getStrSparePartsFlag());
			hemtComplaintAttendDtlVO.setStrIsSolved("1");
			if(complaintMaintenanceStatusFB_p.getStrComplaintType().equals("1")){
			hemtComplaintAttendDtlVO.setStrServiceEnggId(complaintMaintenanceStatusFB_p.getStrVendorServiceEngName());
			}
			else{
				hemtComplaintAttendDtlVO.setStrVendorId(complaintMaintenanceStatusFB_p.getStrVendorServiceEngName());
			}
			
			hemtComplaintAttendDtlVO.setStrAttendDate(complaintMaintenanceStatusFB_p.getStrAttendDate()+" "+ complaintMaintenanceStatusFB_p.getStrAttendTime());
			hemtComplaintAttendDtlVO.setStrSeatid(userVo.getSeatId());
			hemtComplaintAttendDtlVO.setStrIsValid("1");
			
			if(complaintMaintenanceStatusFB_p.getStrIsSparePartsMaintenanceInvolved()!=null && complaintMaintenanceStatusFB_p.getStrIsSparePartsMaintenanceInvolved().equals("1")){
			
				hemtItemSparePartDtlVO = new HemtItemSparePartDtlVO();
				oldHemtItemSparePartDtlVO = new HemtItemSparePartDtlVO();
				
				
			hemtItemSparePartDtlVO.setStrHospitalCode(userVo.getHospitalCode());
			hemtItemSparePartDtlVO.setStrIsvalid("1");
			hemtComplaintAttendDtlVO.setStrStatus(complaintMaintenanceStatusFB_p.getStrSparePartStatusId());
			 strPerformedDate = complaintMaintenanceStatusFB_p.getStrPerformedDate();
			 strSparePartStockDetailsRadio = complaintMaintenanceStatusFB_p.getStrSparePartStockDetailsRadio();
			 strSparePartStatusId = complaintMaintenanceStatusFB_p.getStrSparePartStatusId();
			
			if ((!strSparePartStatusId.equals("1"))
					&& strSparePartStockDetailsRadio != null) {
				/*
				 * Primary Key in Spare Part Detail
				 * HEMNUM_STORE_ID||'^'||HEMNUM_ITEM_ID
				 * ||'^'||HEMSTR_ITEM_SL_NO||
				 * '^'||HEMNUM_SPARE_ID||'^'||HEMSTR_SPARE_SL_NO
				 * ||'^'||HEMNUM_SL_NO||'^'||GNUM_HOSPITAL_CODE
				 */
				arrStringPrimaryKey = strSparePartStockDetailsRadio
						.split("\\^");

				strOldStoreId = arrStringPrimaryKey[0];
				strOldItemId = arrStringPrimaryKey[1];
				strOldSerialNo = arrStringPrimaryKey[2];
				strOldSparePartId = arrStringPrimaryKey[3];
				strOldSpareItemSerialNo = arrStringPrimaryKey[4];
				strOldSpareItemTableSlNo = arrStringPrimaryKey[5];

			}
			
			/*
			 * Setting Spare Parts Details VO
			 */
			// HEMNUM_STORE_ID
			// HEMNUM_ITEM_ID
			// HEMSTR_ITEM_SL_NO
			// HEMNUM_SPARE_ID
			// HEMSTR_SPARE_SL_NO
			// HEMNUM_SL_NO
			// HEMSTR_ITEM_MANUF_SLNO
			// HEMSTR_SPARE_MANUF_SLNO
			// HEMNUM_VENDOR_ID
			// HEMDT_WARRANTY_DATE
			// HSTNUM_WARRANTY_UPTO
			// HSTNUM_WARRANTY_UPTO_UNIT
			// HEMSTR_SPECIFICATION
			// HEMNUM_IS_ADDED ifnew/replacedthen1else0
			// HEMNUM_STATUS
			// HEMDT_ACTION_DATE
			// GNUM_HOSPITAL_CODE session
			// GNUM_ISVALID 1
			// GNUM_ENTRY_DATE Sysdate
			// GNUM_SEAT_ID session
			
			
			strStoreId = complaintMaintenanceStatusFB_p.getStrStoreId();
			strItemId = complaintMaintenanceStatusFB_p.getStrItemId();
			strSerialNo = complaintMaintenanceStatusFB_p.getStrSerialNo();
			strSparePartId = complaintMaintenanceStatusFB_p.getStrSparePartId();
			strSpareItemSerialNo = complaintMaintenanceStatusFB_p.getStrSpareItemSerialNo();
			strManufSerialNo = complaintMaintenanceStatusFB_p.getStrManufSerialNo();
			strSpareManufactureSerialNo = complaintMaintenanceStatusFB_p.getStrSpareManufactureSerialNo();
			strSpareManufacturerId = complaintMaintenanceStatusFB_p.getStrSpareManufacturerId();
			strWarrantyFromDate = complaintMaintenanceStatusFB_p.getStrWarrantyFromDate();
			strWarrantyUpto = complaintMaintenanceStatusFB_p.getStrWarrantyUpto();
			strWarrantyUptoUnitId = complaintMaintenanceStatusFB_p.getStrWarrantyUptoUnitId();
			strSpecification = complaintMaintenanceStatusFB_p.getStrSpecification();
			
			
			hemtItemSparePartDtlVO.setStrStoreId(strStoreId);
			hemtItemSparePartDtlVO.setStrItemId(strItemId);
			hemtItemSparePartDtlVO.setStrItemSlNo(strSerialNo);
			hemtItemSparePartDtlVO.setStrSpareId(strSparePartId);
			hemtItemSparePartDtlVO.setStrSpareSlNo(strSpareItemSerialNo);
			hemtItemSparePartDtlVO.setStrSlNo("");
			hemtItemSparePartDtlVO.setStrItemManufSlno(strManufSerialNo);
			hemtItemSparePartDtlVO.setStrSpareManufSlno(strSpareManufactureSerialNo);
			hemtItemSparePartDtlVO.setStrVendorId(strSpareManufacturerId);
			hemtItemSparePartDtlVO.setStrWarrantyDate(strWarrantyFromDate);
			hemtItemSparePartDtlVO.setStrWarrantyUpto(strWarrantyUpto);
			hemtItemSparePartDtlVO.setStrWarrantyUptoUnit(strWarrantyUptoUnitId);
			hemtItemSparePartDtlVO.setStrSpecification(strSpecification);
			
			/* strSparePartStatusId=3 for repair */
			if (!strSparePartStatusId.equals("3")) {
				hemtItemSparePartDtlVO.setStrIsAdded("1");
			} else {
				hemtItemSparePartDtlVO.setStrIsAdded("0");
			}

			hemtItemSparePartDtlVO.setStrStatus(strSparePartStatusId);
			hemtItemSparePartDtlVO.setStrActionDate(strPerformedDate);
			hemtItemSparePartDtlVO.setStrIsvalid("1");
			if(complaintMaintenanceStatusFB_p.getStrVendorId()!=null)
			hemtItemSparePartDtlVO.setStrVendorId(complaintMaintenanceStatusFB_p.getStrVendorId());
			/*
			 * Setting Old Spare Part Detail VO
			 */

			oldHemtItemSparePartDtlVO.setStrStoreId(strOldStoreId);
			oldHemtItemSparePartDtlVO.setStrItemId(strOldItemId);
			oldHemtItemSparePartDtlVO.setStrItemSlNo(strOldSerialNo);
			oldHemtItemSparePartDtlVO.setStrSpareId(strOldSparePartId);
			oldHemtItemSparePartDtlVO.setStrSpareSlNo(strOldSpareItemSerialNo);
			oldHemtItemSparePartDtlVO.setStrSlNo(strOldSpareItemTableSlNo);
			oldHemtItemSparePartDtlVO.setStrStatus(strSparePartStatusId);
			
			}
			
			BmedTransBO.saveDataForComplaintLogOffline(complaintRequestDtlVO,
					hemtComplaintStatusDtlVO, hemtComplaintApprovalDtlVO,hemtComplaintAttendDtlVO,  hemtItemSparePartDtlVO ,oldHemtItemSparePartDtlVO);
			
			complaintMaintenanceStatusFB_p.setStrNormalMsg("Data Saved Successfully ! ");
		} catch (Exception e) 
		{
			e.printStackTrace();
			strErrMsg = "ComplaintMaintenanceStatusDATA.saveData() --> "+ e.getMessage();
			HisException eObj = new HisException("BMED","ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID()	+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To getPreviousComplaintDetails Data
	 * 
	 * @param complaintMaintenanceStatusFB_p the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getPreviousComplaintDetails(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strErrMsg;
		BmedTransBO bmedTransBO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		String strPrevCompDtl = null;
		String strNonItemId;
		String strHospitalCode;
		String strStockInf;
		UserVO userVo = null;
		try {
			bmedTransBO = new BmedTransBO();
			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			// bmedGlobalBO = new BmedGlobalBO();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			if (request_p.getParameter("flag").equals("ITEM")) {
				strStockInf = (String) request_p.getParameter("strStockInf");

				/*
				 * 0 1 2
				 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
				 * 3 4 ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'|| 5 6
				 * 7
				 * GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item
				 * (1 : for Item 2:for Non-Item)
				 */

				complaintRequestDtlVO.setStrMode("4");
				complaintRequestDtlVO.setStrHospitalCode(strStockInf
						.split("\\^")[5]);
				complaintRequestDtlVO.setStrItemId(strStockInf.split("\\^")[2]);
				complaintRequestDtlVO
						.setStrBatchNo(strStockInf.split("\\^")[3]);// Dummy 0
				complaintRequestDtlVO
						.setStrSerialNo(strStockInf.split("\\^")[4]);// Dummy 0

				strPrevCompDtl = bmedTransBO
						.getPrevCompDtlForItemComplaintRegister(
								complaintRequestDtlVO, strHospitalCode);

				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						strPrevCompDtl + "**" + strStockInf);

			}

			if (request_p.getParameter("flag").equals("NON_ITEM")) {
				strNonItemId = request_p.getParameter("nonItemId");

				complaintRequestDtlVO.setStrMode("4");
				complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
				complaintRequestDtlVO.setStrItemId(strNonItemId);
				complaintRequestDtlVO.setStrBatchNo("0");// Dummy 0
				complaintRequestDtlVO.setStrSerialNo("0");// Dummy 0

				strPrevCompDtl = bmedTransBO
						.getPrevCompDtlForItemComplaintRegister(
								complaintRequestDtlVO, strHospitalCode);

				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						strPrevCompDtl + "**" + strNonItemId);

			}

		} catch (Exception e) {

			strErrMsg = "ItemMaintContractDtlsTransDATA.getPreviousComplaintDetails() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ItemMaintContractDtlsDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To get Warranty Details Data
	 * 
	 * @param complaintMaintenanceStatusFB_p the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getWarrantyDetails(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strErrMsg;
		String strWarrantyDetailsTable;
		BmedTransBO bmedTransBO = null;
		String strHospitalCode;
		String strNonItemId;
		String strStockInf;
		WarrantyDtlVO warrantyDtlVO = null;
		// HemtItemMcDtlVO hemtItemMcDtlVO = null;

		UserVO userVo = null;
		try {
			bmedTransBO = new BmedTransBO();

			warrantyDtlVO = new WarrantyDtlVO();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			if (request_p.getParameter("flag").equals("ITEM")) {
				/*
				 * 0 1 2
				 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
				 * 3 4 ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'|| 5 6
				 * 7
				 * GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item
				 * (1 : for Item 2:for Non-Item)
				 */

				strStockInf = (String) request_p.getParameter("strStockInf");
				// System.out.println("Testing222::"+strStockInf);

				warrantyDtlVO.setStrMode("6");
				warrantyDtlVO.setStrItemId(strStockInf.split("\\^")[1]);
				warrantyDtlVO.setStrItemBrandId(strStockInf.split("\\^")[2]);
				warrantyDtlVO.setStrHospitalCode(strStockInf.split("\\^")[5]);
				warrantyDtlVO.setStrBatchSlNo(strStockInf.split("\\^")[3]);// Dummy
																			// NA
				warrantyDtlVO.setStrItemSlNo(strStockInf.split("\\^")[4]);

				bmedTransBO
						.getWarrantyDetailForItemComplaintRegister(warrantyDtlVO);

				strWarrantyDetailsTable = getWarrantyDetailsTable(
						warrantyDtlVO.getWrsResultData(), "WITH_RADIO_BUTTON");
				complaintMaintenanceStatusFB_p
						.setStrWarrantyDetailsTable(strWarrantyDetailsTable);

				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						strWarrantyDetailsTable + "**" + strStockInf);

			}

			if (request_p.getParameter("flag").equals("NON_ITEM")) {
				strNonItemId = request_p.getParameter("nonItemId");

				warrantyDtlVO.setStrMode("6");
				warrantyDtlVO.setStrItemId(strNonItemId);
				warrantyDtlVO.setStrItemBrandId("0");
				warrantyDtlVO.setStrHospitalCode(strHospitalCode);
				warrantyDtlVO.setStrBatchSlNo("0");// Dummy 0
				warrantyDtlVO.setStrItemSlNo("0");

				bmedTransBO
						.getWarrantyDetailForItemComplaintRegister(warrantyDtlVO);

				strWarrantyDetailsTable = getWarrantyDetailsTable(
						warrantyDtlVO.getWrsResultData(), "WITH_RADIO_BUTTON");
				complaintMaintenanceStatusFB_p
						.setStrWarrantyDetailsTable(strWarrantyDetailsTable);

				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(
						strWarrantyDetailsTable + "**" + strNonItemId);

			}
		} catch (Exception e) {
			e.printStackTrace();

			strErrMsg = "ItemMaintContractDtlsTransDATA.getPreviousComplaintDetails() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ItemMaintContractDtlsDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To get Maintenance Contract Details Data
	 * 
	 * @param complaintMaintenanceStatusFB_p the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getMaintenanceContractDetails(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strErrMsg;
		// BmedGlobalBO bmedGlobalBO = null;
		BmedTransBO bmedTransBO = null;
		String strMaintenanceContractDetailsTable;
		String strNonItemId;
		String strHospitalCode;
		String strStockInf;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;

		UserVO userVo = null;
		try {
			bmedTransBO = new BmedTransBO();

			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			if (request_p.getParameter("flag").equals("ITEM")) {
				/*
				 * 0 1 2
				 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
				 * 3 4 ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'|| 5 6
				 * 7
				 * GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item
				 * (1 : for Item 2:for Non-Item)
				 */

				strStockInf = (String) request_p.getParameter("strStockInf");
				// System.out.println("Testing333::" + strStockInf);

				hemtItemMcDtlVO.setStrMode("5");
				hemtItemMcDtlVO.setStrItemId(strStockInf.split("\\^")[2]);
				hemtItemMcDtlVO.setStrBatchNo(strStockInf.split("\\^")[3]);// Dummy
																			// 0
				hemtItemMcDtlVO.setStrHospCode(strStockInf.split("\\^")[5]);
				hemtItemMcDtlVO.setStrItemSlNo(strStockInf.split("\\^")[4]);// Dummy
																			// 0
				hemtItemMcDtlVO.setStrSlNo("0");// Dummy 0

				/*
				 * bmedTransBO.getMaintenanceContractDetails(hemtItemMcDtlVO);
				 * 
				 * strMaintenanceContractDetailsTable =
				 * getMaintenanceContractDetailsTable(
				 * hemtItemMcDtlVO.getWrsMCDetails(), "WITH_RADIO_BUTTON");
				 * complaintMaintenanceStatusFB_p
				 * .setStrWarrantyDetailsTable(strMaintenanceContractDetailsTable
				 * );
				 * 
				 * try { response_p.setHeader("Cache-Control", "no-cache");
				 * response_p
				 * .getWriter().print(strMaintenanceContractDetailsTable); }
				 * catch (Exception e) { e.printStackTrace(); }
				 */
			}

			if (request_p.getParameter("flag").equals("NON_ITEM")) {

				strNonItemId = request_p.getParameter("strNonItemId");

				hemtItemMcDtlVO.setStrMode("5");
				hemtItemMcDtlVO.setStrItemId(strNonItemId);
				hemtItemMcDtlVO.setStrBatchNo("0");// Dummy 0
				hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
				hemtItemMcDtlVO.setStrItemSlNo("0");// Dummy 0
				hemtItemMcDtlVO.setStrSlNo("0");// Dummy 0
			}
			bmedTransBO.getMaintenanceContractDetails(hemtItemMcDtlVO);

			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(
					hemtItemMcDtlVO.getWrsMCDetails(), "WITH_RADIO_BUTTON");
			complaintMaintenanceStatusFB_p
					.setStrWarrantyDetailsTable(strMaintenanceContractDetailsTable);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strMaintenanceContractDetailsTable);

		} catch (Exception e) {

			e.printStackTrace();
			strErrMsg = "ItemMaintContractDtlsTransDATA.getPreviousComplaintDetails() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ItemMaintContractDtlsDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	private static String getAttenderDetailsTable(WebRowSet wrsData_p)
			throws SQLException {

		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);

		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");
		sbHeader.append("<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Attended Date</td>");
		sbHeader.append("<td width=\"40%\" class=\"LABEL_TD\" style=\"text-align: center;\">Engineer Name</td>");
		sbHeader.append("<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Solution Provided</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			// HEMNUM_REQ_ID: 1
			// HEMNUM_ATTEND_ID: 2
			// GNUM_HOSPITAL_CODE: 3
			// HEMNUM_MAINTE_ID: 4
			// HEMSTR_VENDOR_COMP_ID: 5
			// HEMSTR_ATTENDED_CONTACT_PERSON: 6
			// HEMSTR_ATTENDED_CONTACT_NO: 7
			// HEMSTR_ATTENDED_REMARKS: 8
			// HEMSTR_PROB_DESCRIP: 9
			// HEMSTR_SERVICE_ENGG_ID: 10
			// HEMNUM_COMPLETED_DATE: 11
			// HEMNUM_VENDOR_ID: 12
			// HEMNUM_ATTEND_DATE: 13
			// HEMNUM_IS_SPAREPARTS: 14
			// HEMNUM_IS_COST: 15
			// HEMNUM_COST: 16
			// HEMNUM_IS_WORKING: 17
			// HEMSTR_SOLUTION_PROVIDED: 18
			// HEMDT_WORKSTATUS_DATE: 19
			// HEMNUM_IS_SPARE_REQ: 20
			// HEMSTR_GATEPASS_NO: 21
			// HEMNUM_STATUS: 22
			// HEMNUM_IS_SOLVED: 23
			// GNUM_SEATID: 24
			// GNUM_ISVALID: 25
			// MAINTENANCE_NAME: 26
			// SERVICE_ENGG_NAME: 27
			// VENDOR_NAME: 28
			// HEMNUM_COST: 29			
			// TOTAL_DOWN_TIME: 30

			while (wrsData_p.next()) {

				String strAttendedDate = wrsData_p.getString("HEMNUM_ATTEND_DATE");
				String strEngineerName = wrsData_p.getString("SERVICE_ENGG_NAME");
				String strSolutionProvided = wrsData_p.getString("HEMSTR_SOLUTION_PROVIDED");

				String strCost = wrsData_p.getString("HEMNUM_COST");				
				String strTotalDownTime = wrsData_p.getString("TOTAL_DOWN_TIME");
				
				if (strAttendedDate == null) {
					strAttendedDate = "---";
				}
				if (strEngineerName == null) {
					strEngineerName = "---";
				}
				if (strSolutionProvided == null) {
					strSolutionProvided = "---";
				}
				
				if (strCost == null) {
					strCost = "0";
				}
				if (strTotalDownTime == null) {
					strTotalDownTime = "";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");
				
				sbBody.append("<input type='hidden' name='strTempCost' value='"+strCost+"' />");
				sbBody.append("<input type='hidden' name='strTempTotalDownTime' value='"+strTotalDownTime+"' />");
				
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"	+ strAttendedDate + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"	+ strEngineerName + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"	+ strSolutionProvided + "</td>");
				sbBody.append("</tr>");

			}

		} else {
			sbBody.append("<tr>");
			sbBody.append("<td colspan=\"3\" class=\"CONTROL_TD\" style=\"text-align: center; color: red;\">No Data Found!</td></td>");
			sbBody.append("</tr>");
		}

		return sbHeader.toString() + sbBody.toString();
	}

	private static String getSchedulesDetailsTable(WebRowSet wrsData_p)
			throws SQLException {

		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);

		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");
		sbHeader.append("<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Intimation Date And Time (24Hr Format)</td>");
		sbHeader.append("<td width=\"40%\" class=\"LABEL_TD\" style=\"text-align: center;\">Contact Person</td>");
		sbHeader.append("<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Vendor Communication Id</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			// HEMSTR_VENDOR_CONTACT_PERSON: 1
			// HEMSTR_VENDOR_CONTACT_NO: 2
			// HEMSTR_SOLUTION_PROVIDED: 3
			// HEMSTR_REMARKS: 4
			// HEMSTR_PROB_DESCRIP: 5
			// HEMNUM_VENDOR_ID: 6
			// HEMNUM_VENDOR_COMM_ID: 7
			// HEMNUM_STATUS: 8
			// HEMNUM_SERVICE_ENGG_ID: 9
			// HEMNUM_SCHEDULE_ID: 10
			// HEMNUM_REQ_TYPE: 11
			// HEMNUM_REQ_ID: 12
			// HEMNUM_EXPECT_VISIT_UNIT: 13
			// HEMNUM_EXPECT_VISIT: 14
			// HEMDT_EXPECTED_VISIT: 15
			// HEMDT_COMP_INTEMATION: 16
			// GNUM_SEATID: 17
			// GNUM_ISVALID: 18
			// GNUM_HOSPITAL_CODE: 19
			// GDT_ENTRY_DATE: 20
			// VENDOR_NAME: 21
			// SERVICE_ENGG_NAME: 22
			// EXPECT_VISIT_UNIT_NAME: 23

			while (wrsData_p.next()) {

				String strIntimationDate = wrsData_p
						.getString("HEMDT_COMP_INTEMATION");
				String strContactPerson = wrsData_p
						.getString("HEMSTR_VENDOR_CONTACT_PERSON");
				String strVendorCommunicationId = wrsData_p
						.getString("HEMNUM_VENDOR_COMM_ID");

				if (strIntimationDate == null) {
					strIntimationDate = "---";
				}
				if (strContactPerson == null) {
					strContactPerson = "---";
				}
				if (strVendorCommunicationId == null) {
					strVendorCommunicationId = "---";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strIntimationDate + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strContactPerson + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strVendorCommunicationId + "</td>");
				sbBody.append("</tr>");

			}

		} else {
			sbBody.append("<tr>");
			sbBody.append("<td colspan=\"3\" class=\"CONTROL_TD\" style=\"text-align: center; color: red;\">No Data Found!</td></td>");
			sbBody.append("</tr>");
		}

		return sbHeader.toString() + sbBody.toString();
	}

	private static String getMaintenanceContractDetailsTable(
			WebRowSet wrsData_p, String strDisplayMode_p) throws SQLException {

		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);
		int nWidth;
		int nColspan;
		int index=0;
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
				String strUploadNo = wrsData_p.getString("HPURNUM_UPLOAD_NO"); 
				String strDocRefNo = wrsData_p.getString("HPURSTR_DOC_REF_NO");
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
				if (strUploadNo == null) {
					strUploadNo = "";
				}
				else
				{
					if (!strUploadNo.equals("0")) 
					strExten=strUploadNo.split("\\.")[1];
				}
				if (strDocRefNo == null) {
					strDocRefNo = "";
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
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><a style='cursor:pointer;color:blue;font-size:12px;'"
						+ " onClick=onUploadedFileName(this,"+index+",'"+strDocRefNo+"."+strExten+"');>"
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
		int nColspan;
		int index=0;
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
				String strUploadNo = wrsData_p.getString("HPURNUM_UPLOAD_NO"); 
				String strDocRefNo = wrsData_p.getString("HPURSTR_DOC_REF_NO");
				
				String strWarrantySlNo = wrsData_p.getString("HSTNUM_SL_NO");

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
				if (strUploadNo == null) {
					strUploadNo = "";
				}
				else
				{
					if (!strUploadNo.equals("0")) 
					strExten=strUploadNo.split("\\.")[1];
				}
				if (strDocRefNo == null) {
					strDocRefNo = "";
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
						+ "onClick=onUploadedFileName(this,"+index+",'"+strDocRefNo+"."+strExten+"');>"
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

	public static void saveCancel(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB,
			HttpServletRequest request) {
		String strErrMsg;

		BmedTransBO bmedTransBO = null;

		final String strHospitalCode;
		final String strCancelTypeId;
		;
		final String strComplaintId;
		final String strCancelRemarks;
		final String strSeatId;

		UserVO userVo = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		HemtComplaintApprovalDtlVO complaintApprovalDtlVO = null;
		HemtComplaintStatusDtlVO complaintStatusDtlVO = null;

		try {
			bmedTransBO = new BmedTransBO();
			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			complaintApprovalDtlVO = new HemtComplaintApprovalDtlVO();
			complaintStatusDtlVO = new HemtComplaintStatusDtlVO();

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request);
			strHospitalCode = userVo.getHospitalCode();
			strSeatId = userVo.getUserSeatId();

			strCancelTypeId = complaintMaintenanceStatusFB.getStrCancelTypeId();
			strComplaintId = complaintMaintenanceStatusFB.getStrComplaintId();
			strCancelRemarks = complaintMaintenanceStatusFB
					.getStrCancelRemarks();

			/* Setting value for Complaint Request VO Start */
			complaintRequestDtlVO.setStrMode("2");
			complaintRequestDtlVO.setStrCancelId(strCancelTypeId);
			complaintRequestDtlVO.setStrCancelRemarks(strCancelRemarks);
			complaintRequestDtlVO.setStrCancelSeatid(strSeatId);
			complaintRequestDtlVO.setStrStatusRemarks(strCancelRemarks);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			complaintRequestDtlVO.setStrReqId(strComplaintId);
			/* Setting value for Complaint Request VO End */

			/* Setting value for Complaint Approval VO Start */
			complaintApprovalDtlVO.setStrMode("2");
			complaintApprovalDtlVO.setStrReqId(strComplaintId);
			complaintApprovalDtlVO.setStrHospitalCode(strHospitalCode);
			/* Setting value for Complaint Approval VO End */

			/* Setting value for Complaint Approval VO Start */
			complaintStatusDtlVO.setStrMode("2");
			complaintStatusDtlVO.setStrReqId(strComplaintId);
			complaintStatusDtlVO.setStrHospitalCode(strHospitalCode);
			complaintStatusDtlVO.setStrRemarks(strCancelRemarks);
			/* Setting value for Complaint Approval VO End */

			bmedTransBO.saveComplaintCancelProcess(complaintRequestDtlVO,
					complaintApprovalDtlVO, complaintStatusDtlVO);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.saveCancel --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	/*
	 * This method is called Schedule Process (internal or external)
	 */
	public static void initializeSchedule(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		String strErrMsg;
		// BmedGlobalBO bmedGlobalBO = null;
		BmedTransBO bmedTransBO = null;
		String strExpectedVisitUnitComboOptions = null;
		String strHospitalCode = null;
		String strReqId = null;
		String strIsHemDesk = null;
		final String strWarrantyDetailsTable;
		final String strMaintenanceContractDetailsTable;
		final String strSchedulesDetailsTable;
		final String strAttenderDetailsTable;
		final String strServiceEngineerDetailsTable;

		final String strComplaintId;
		final String strComplaintDate;
		final String strItemName;
		final String strItemBatchNo;
		final String strItemSerialNo;
		final String strManufacturerSerialNo;
		final String strComplaintDescription;
		final String strComplaintType;
		final String strVendorId;
		final String strWarrantyAndMaintenanceContractDivDisplay;
		final String strExternalServiceProvidercheckBoxDisplay;
		final String strEngineeringItemTypeId;
		final String strExternalServiceProvidercheckBoxChecked;
		String strPath;
		UserVO userVo = null;
		WarrantyDtlVO warrantyDtlVO = null;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		ComplaintScheduleDtlVO complaintScheduleDtlVO = null;
		ComplaintAttendDtlVO complaintAttendDtlVO = null;
		ServiceEnggMstVO serviceEnggMstVO = null;

		final boolean serviceEngDetailsRequired;

		try {

			// bmedGlobalBO = new BmedGlobalBO();
			bmedTransBO = new BmedTransBO();
			warrantyDtlVO = new WarrantyDtlVO();
			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			complaintScheduleDtlVO = new ComplaintScheduleDtlVO();
			complaintAttendDtlVO = new ComplaintAttendDtlVO();
			serviceEnggMstVO = new ServiceEnggMstVO();
			strPath = "/bmed" + request_p.getParameter("cnt_page") + ".cnt";

			if (request_p.getParameter("cnt_page") == null) {

				strPath = request_p.getParameter("strPath");
			}
			if (strPath != null) {
				strPath = strPath.trim();
			}
			complaintMaintenanceStatusFB_p.setStrPath(strPath);

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			complaintScheduleDtlVO.setStrHospitalCode(strHospitalCode);
			complaintAttendDtlVO.setStrHospitalCode(strHospitalCode);
			serviceEnggMstVO.setStrHospitalCode(strHospitalCode);

			/*
			 * Getting Request Data:
			 */
			strReqId = complaintMaintenanceStatusFB_p.getStrHiddenComplaintId();
			strIsHemDesk = complaintMaintenanceStatusFB_p.getStrIsHemDesk();
			if (strReqId == null) {
				strReqId = request_p.getParameter("strComplaintId");
				strIsHemDesk = request_p.getParameter("strIsHemDesk");
				complaintMaintenanceStatusFB_p.setStrIsHemDesk(strIsHemDesk);

			}

			if (strIsHemDesk == null) {
				strIsHemDesk = "0";
				complaintMaintenanceStatusFB_p.setStrIsHemDesk(strIsHemDesk);
			}

			if (strIsHemDesk.equals("0")) {
				strWarrantyAndMaintenanceContractDivDisplay = "block";
				strExternalServiceProvidercheckBoxDisplay = "none";

				/* If not hem schedule then by default selected. */
				strExternalServiceProvidercheckBoxChecked = "checked";
			} else {
				strWarrantyAndMaintenanceContractDivDisplay = "none";
				strExternalServiceProvidercheckBoxDisplay = "block";

				/* If hem schedule then by default not selected. */
				strExternalServiceProvidercheckBoxChecked = "";
			}

			/*
			 * If the process is Hem Schedule, then service engineer detail is
			 * required.
			 */
			serviceEngDetailsRequired = (strIsHemDesk.equals("1")) ? true
					: false;

			complaintMaintenanceStatusFB_p.setStrIsHemDesk(strIsHemDesk);

			complaintRequestDtlVO.setStrReqId(strReqId);
			complaintScheduleDtlVO.setStrReqId(strReqId);
			complaintAttendDtlVO.setStrReqId(strReqId);

			bmedTransBO.initializeComplaintActions(complaintRequestDtlVO,
					warrantyDtlVO, hemtItemMcDtlVO, complaintScheduleDtlVO,
					complaintAttendDtlVO, serviceEnggMstVO,
					serviceEngDetailsRequired, null, false, null, false);

			strComplaintId = complaintRequestDtlVO.getStrReqId();
			strComplaintDate = complaintRequestDtlVO.getStrReqDate();
			strItemName = complaintRequestDtlVO.getStrItemName();
			strItemBatchNo = complaintRequestDtlVO.getStrBatchNo();
			strItemSerialNo = complaintRequestDtlVO.getStrSerialNo();
			strManufacturerSerialNo = complaintRequestDtlVO
					.getStrManufSerialNo();
			strComplaintDescription = complaintRequestDtlVO
					.getStrComplaintDes();
			strComplaintType = complaintRequestDtlVO.getStrReqType();
			strVendorId = complaintRequestDtlVO.getStrVendorId();
			strEngineeringItemTypeId = complaintRequestDtlVO
					.getStrEnggItemTypeId();

			complaintMaintenanceStatusFB_p.setStrComplaintId(strComplaintId);
			complaintMaintenanceStatusFB_p
					.setStrComplaintDate(strComplaintDate);
			complaintMaintenanceStatusFB_p.setStrItemName(strItemName);
			complaintMaintenanceStatusFB_p.setStrItemBatchNo(strItemBatchNo);
			complaintMaintenanceStatusFB_p.setStrItemSerialNo(strItemSerialNo);
			complaintMaintenanceStatusFB_p
					.setStrManufacturerSerialNo(strManufacturerSerialNo);
			complaintMaintenanceStatusFB_p
					.setStrComplaintDescription(strComplaintDescription);
			complaintMaintenanceStatusFB_p
					.setStrComplaintType(strComplaintType);
			complaintMaintenanceStatusFB_p.setStrVendorId(strVendorId);
			complaintMaintenanceStatusFB_p
					.setStrEngineeringItemTypeId(strEngineeringItemTypeId);

			strWarrantyDetailsTable = getWarrantyDetailsTable(
					warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(
					hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");
			strSchedulesDetailsTable = getSchedulesDetailsTable(complaintScheduleDtlVO
					.getWrsData());
			strAttenderDetailsTable = getAttenderDetailsTable(complaintAttendDtlVO
					.getWrsData());

			if (serviceEnggMstVO.getWrsData() != null) {

				strServiceEngineerDetailsTable = getServiceEngineerDetailsTable(
						serviceEnggMstVO.getWrsData(), "DEDICATED_SERVICE_ENGG");

			} else {
				strServiceEngineerDetailsTable = "No Data!";
			}

			strExpectedVisitUnitComboOptions = BmedGlobalBO
					.getUnitComboOptions(strHospitalCode);

			complaintMaintenanceStatusFB_p
					.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrSchedulesDetailsTable(strSchedulesDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrAttenderDetailsTable(strAttenderDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrServiceEngineerDetailsTable(strServiceEngineerDetailsTable);

			complaintMaintenanceStatusFB_p
					.setStrExpectedVisitUnitOptions(strExpectedVisitUnitComboOptions);

			complaintMaintenanceStatusFB_p
					.setStrWarrantyAndMaintenanceContractDivDisplay(strWarrantyAndMaintenanceContractDivDisplay);
			complaintMaintenanceStatusFB_p
					.setStrExternalServiceProvidercheckBoxDisplay(strExternalServiceProvidercheckBoxDisplay);
			complaintMaintenanceStatusFB_p
					.setStrExternalServiceProvidercheckBoxChecked(strExternalServiceProvidercheckBoxChecked);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeSchedule --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	public static void saveSchedule(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB,
			HttpServletRequest request) {
		String strErrMsg;

		BmedTransBO bmedTransBO = null;

		final String strHospitalCode;
		final String strIntimationDate;
		final String strIntimationTime;
		final String strContactPerson;
		final String strContactNo;
		final String strProblemDescription;
		final String strCommunicationId;
		final String strExpectedVisit;
		final String strExpectedVisitUnitId;
		final String strSolutionProvided;
		final String strSeatId;
		final String strComplaintId;
		final String strComplaintType;
		final String strVendorId;
		final String strServiceEngnieerRemarks;
		final String strServiceEnggId;
		final String strExpectedDateToAttend;
		final String strExpectedTimeToAttend;
		final String strServiceProviderRemarks;
		String strAllocateToExternalServiceProvider;

		UserVO userVo = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		ComplaintScheduleDtlVO complaintScheduleDtlVO = null;
		HemtComplaintStatusDtlVO complaintStatusDtlVO = null;

		try {
			bmedTransBO = new BmedTransBO();
			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			complaintScheduleDtlVO = new ComplaintScheduleDtlVO();
			complaintStatusDtlVO = new HemtComplaintStatusDtlVO();

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request);
			strHospitalCode = userVo.getHospitalCode();
			strSeatId = userVo.getUserSeatId();

			/* Getting Data from FormBean */
			strIntimationDate = complaintMaintenanceStatusFB
					.getStrIntimationDate();
			strIntimationTime = complaintMaintenanceStatusFB
					.getStrIntimationTime();
			strContactPerson = complaintMaintenanceStatusFB
					.getStrContactPerson();
			strContactNo = complaintMaintenanceStatusFB.getStrContactNo();
			strProblemDescription = complaintMaintenanceStatusFB
					.getStrProblemDescription();
			strCommunicationId = complaintMaintenanceStatusFB
					.getStrCommunicationId();
			strExpectedVisit = complaintMaintenanceStatusFB
					.getStrExpectedVisit();
			strExpectedVisitUnitId = complaintMaintenanceStatusFB
					.getStrExpectedVisitUnitId();
			strSolutionProvided = complaintMaintenanceStatusFB
					.getStrSolutionProvided();
			strComplaintId = complaintMaintenanceStatusFB.getStrComplaintId();
			strComplaintType = complaintMaintenanceStatusFB
					.getStrComplaintType();
			strVendorId = complaintMaintenanceStatusFB.getStrVendorId();
			strServiceEngnieerRemarks = complaintMaintenanceStatusFB
					.getStrServiceEngnieerRemarks();
			strAllocateToExternalServiceProvider = complaintMaintenanceStatusFB
					.getStrAllocateToExternalServiceProvider();

			strServiceEnggId = complaintMaintenanceStatusFB
					.getStrServiceEnggId();
			strExpectedDateToAttend = complaintMaintenanceStatusFB
					.getStrExpectedDateToAttend();
			strExpectedTimeToAttend = complaintMaintenanceStatusFB
					.getStrExpectedTimeToAttend();
			strServiceProviderRemarks = complaintMaintenanceStatusFB
					.getStrServiceProviderRemarks();

			if (strAllocateToExternalServiceProvider == null) {
				strAllocateToExternalServiceProvider = "0";
			}

			/* Setting value for Complaint Request VO Start */
			complaintRequestDtlVO.setStrHemFlag("1");
			if (strAllocateToExternalServiceProvider.equals("0")) {

				complaintRequestDtlVO
						.setStrStatusRemarks(strServiceEngnieerRemarks);

			} else if (strAllocateToExternalServiceProvider.equals("1")) {

				complaintRequestDtlVO
						.setStrStatusRemarks("External Scheduling");

			}
			complaintRequestDtlVO.setStrReqId(strComplaintId);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			/* Setting value for Complaint Request VO End */

			/* Setting value for Schedule Detail VO Start */
			complaintScheduleDtlVO.setStrReqId(strComplaintId);
			complaintScheduleDtlVO.setStrHospitalCode(strHospitalCode);
			complaintScheduleDtlVO.setStrReqType(strComplaintType);
			if (strAllocateToExternalServiceProvider.equals("0")) {
				/* Date format: '03-Sep-1984 16:15:34' 24hr time format. */
				complaintScheduleDtlVO.setStrCompIntemation("");// Sysdate
				complaintScheduleDtlVO.setStrProbDescrip("");
				complaintScheduleDtlVO.setStrVendorCommId("");
				complaintScheduleDtlVO.setStrVendorContactPerson("");
				complaintScheduleDtlVO.setStrVendorContactNo("");
				complaintScheduleDtlVO.setStrRemarks(strServiceEngnieerRemarks);
				complaintScheduleDtlVO.setStrServiceEnggId(strServiceEnggId);
				complaintScheduleDtlVO.setStrExpectVisit("");
				complaintScheduleDtlVO.setStrExpectVisitUnitId("");
				complaintScheduleDtlVO
						.setStrExpectedVisitDate(strExpectedDateToAttend + " "
								+ strExpectedTimeToAttend + ":00");
				complaintScheduleDtlVO.setStrSolutionProvided("");
				complaintScheduleDtlVO.setStrSeatId(strSeatId);
				complaintScheduleDtlVO.setStrVendorId(strVendorId);

			} else if (strAllocateToExternalServiceProvider.equals("1")) {
				/* Date format: '03-Sep-1984 16:15:34' 24hr time format. */
				complaintScheduleDtlVO.setStrCompIntemation(strIntimationDate
						+ " " + strIntimationTime + ":00");
				complaintScheduleDtlVO.setStrProbDescrip(strProblemDescription);
				complaintScheduleDtlVO.setStrVendorCommId(strCommunicationId);
				complaintScheduleDtlVO.setStrVendorContactPerson(strContactPerson);
				System.out.println("Contact Person::::"+strContactPerson);
				complaintScheduleDtlVO.setStrVendorContactNo(strContactNo);
				complaintScheduleDtlVO.setStrRemarks(strServiceProviderRemarks);
				complaintScheduleDtlVO.setStrServiceEnggId("");// In External
																// Case
				// No Service
				// Engineer
				complaintScheduleDtlVO.setStrExpectVisit(strExpectedVisit);
				complaintScheduleDtlVO
						.setStrExpectVisitUnitId(strExpectedVisitUnitId);
				complaintScheduleDtlVO
						.setStrSolutionProvided(strSolutionProvided);
				complaintScheduleDtlVO.setStrSeatId(strSeatId);
				complaintScheduleDtlVO.setStrVendorId(strVendorId);
			}

			/* Setting value for Schedule Detail VO End */

			/* Setting value for Complaint Status VO Start */
			complaintStatusDtlVO.setStrReqId(strComplaintId);
			complaintStatusDtlVO.setStrMainStatus("2");
			complaintStatusDtlVO.setStrSubStatus("20");
			complaintStatusDtlVO.setStrRemarks("Scheduling");
			complaintStatusDtlVO.setStrHospitalCode(strHospitalCode);
			complaintStatusDtlVO.setStrSeatId(strSeatId);

			/* Setting value for Complaint Approval VO End */

			bmedTransBO.saveComplaintScheduleProcess(complaintRequestDtlVO,	complaintScheduleDtlVO, complaintStatusDtlVO);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.saveCancel --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	private static String getServiceEngineerDetailsTable(WebRowSet wrsData_p,
			String strDivIdPattern) throws SQLException {
		StringBuffer sbServiceEngineerDetailsTable = new StringBuffer(1000);

		String strServiceEnggSkillData = null;
		String strServiceEnggJobData = null;
		String strServiceEnggName = null;
		String strServiceEnggId = null;

		/*
		 * Header Part
		 */
		sbServiceEngineerDetailsTable.append("<tr>");
		sbServiceEngineerDetailsTable
				.append("<td width=\"5%\" class=\"LABEL_TD\" style=\"text-align: center;\">&nbsp;</td>");
		sbServiceEngineerDetailsTable
				.append("<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Service Engineer Name</td>");
		sbServiceEngineerDetailsTable
				.append("<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Skills</td>");
		sbServiceEngineerDetailsTable
				.append("<td width=\"35%\" class=\"LABEL_TD\" style=\"text-align: center;\">Number of Jobs</td>");
		sbServiceEngineerDetailsTable.append("</tr>");

		/*
		 * Body Part
		 */
		if (wrsData_p.size() > 0) {
			int nRowCount = 0;
			while (wrsData_p.next()) {
				++nRowCount;

				/*
				 * Getting Data From WebRowSet
				 * 
				 * HEMSTR_EMP_ID: 1 HEMNUM_ENGG_ITEM_TYPE_ID: 2
				 * HEMNUM_ENGG_ITEM_SUB_TYPE_ID: 3 GNUM_HOSPITAL_CODE: 4
				 * GDT_EFFECTIVE_FROM: 5 GSTR_REMARKS: 6 GDT_ENTRY_DATE: 7
				 * GNUM_SEATID: 8 GNUM_ISVALID: 9 GDT_LSTMOD_DATE: 10
				 * GNUM_LSTMOD_SEATID: 11 SERVICE_ENGG_NAME: 12
				 * ENGG_ITEM_TYPE_NAME: 13 ENGG_ITEM_SUB_TYPE_NAME: 14
				 * SKILL_DESC: 15 JOB_DESC: 16
				 */
				strServiceEnggId = wrsData_p.getString("HEMSTR_EMP_ID");
				strServiceEnggName = wrsData_p.getString("SERVICE_ENGG_NAME");
				strServiceEnggSkillData = wrsData_p.getString("SKILL_DESC");
				strServiceEnggJobData = wrsData_p.getString("JOB_DESC");

				if (strServiceEnggId == null) {
					strServiceEnggId = "";
				}
				if (strServiceEnggName == null) {
					strServiceEnggName = "";
				}
				if (strServiceEnggSkillData == null) {
					strServiceEnggSkillData = "";
				}
				if (strServiceEnggJobData == null) {
					strServiceEnggJobData = "";
				}

				String strSkillDivId = strDivIdPattern + "_skill_" + nRowCount;
				String strJobDivId = strDivIdPattern + "_job_" + nRowCount;
				sbServiceEngineerDetailsTable.append("<tr>");
				sbServiceEngineerDetailsTable
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><input type=\"radio\" name=\"strServiceEnggId\" value=\""
								+ strServiceEnggId + "\"></td>");
				sbServiceEngineerDetailsTable
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strServiceEnggName + "</td>");
				sbServiceEngineerDetailsTable
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">");

				sbServiceEngineerDetailsTable.append(getPopupDivForSkill(
						strServiceEnggName, strServiceEnggSkillData,
						strSkillDivId));

				sbServiceEngineerDetailsTable
						.append("<a style=\"cursor: pointer; color: blue;\" onclick=\"display_popup_menu(this.parentNode,'"
								+ strSkillDivId + "','','');\">Skills...</a>");
				sbServiceEngineerDetailsTable.append("</td>");
				sbServiceEngineerDetailsTable
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">");
				StringBuffer sbNoOfJobs = new StringBuffer(2);
				sbServiceEngineerDetailsTable.append(getPopupDivForJob(
						strServiceEnggName, strServiceEnggJobData, strJobDivId,
						sbNoOfJobs));
				if (Integer.valueOf(sbNoOfJobs.toString()) > 0) {
					sbServiceEngineerDetailsTable
							.append("<a style=\"cursor: pointer; color: blue;\" onclick=\"display_popup_menu(this.parentNode,'"
									+ strJobDivId
									+ "','','');\">"
									+ sbNoOfJobs.toString() + "</a></td>");
				} else {
					sbServiceEngineerDetailsTable.append("No Job.</td>");
				}

				sbServiceEngineerDetailsTable.append("</tr>");

			}

		} else {

			/* No Data Found */

			sbServiceEngineerDetailsTable.append("<tr>");
			sbServiceEngineerDetailsTable
					.append("<td colspan=\"4\" class=\"CONTROL_TD\" style=\"text-align: center;color:red\">No Data Found!</td>");

		}

		return sbServiceEngineerDetailsTable.toString();
	}

	private static String getPopupDivForSkill(String strServiceEnggName_p,
			String strServiceEnggAndSkillData_p, String strDivId_p) {
		StringBuffer sbServiceEngineerDetailsTablePopupDivForSkill = new StringBuffer(
				1000);

		/* Initialize to Empty Array */
		String[] arrStrSkillName = {};

		if (strServiceEnggAndSkillData_p != null
				&& !strServiceEnggAndSkillData_p.equals("")) {
			arrStrSkillName = strServiceEnggAndSkillData_p.split("\\^");
		}

		sbServiceEngineerDetailsTablePopupDivForSkill
				.append("<div class=\"popup\" id=\"" + strDivId_p
						+ "\" style=\"display: none;\">");
		/*
		 * Header Part of Pop Up
		 */
		sbServiceEngineerDetailsTablePopupDivForSkill
				.append("<table class=\"TABLE_STYLE\" style=\"width: 400px;border-collapse: collapse;\">");
		sbServiceEngineerDetailsTablePopupDivForSkill
				.append("<tr class=\"FOOTER_TR\">");
		sbServiceEngineerDetailsTablePopupDivForSkill
				.append("<td style=\"text-align: left;\">Service Engineer Skills...</td>");
		sbServiceEngineerDetailsTablePopupDivForSkill
				.append("<td><img class=\"button\" tabindex=\"1\" src=\"/AHIMS/hisglobal/images/stop.png\" title=\"Close Popup\" style=\"cursor: pointer\" onclick=\"hide_popup_menu('"
						+ strDivId_p
						+ "');\" onkeypress=\"if(event.keyCode==13) hide_popup_menu('"
						+ strDivId_p + "');\" /></td>");
		sbServiceEngineerDetailsTablePopupDivForSkill.append("</tr>");
		sbServiceEngineerDetailsTablePopupDivForSkill.append("</table>");
		/*
		 * Service Engineer Details
		 */
		sbServiceEngineerDetailsTablePopupDivForSkill
				.append("<table class=\"TABLE_STYLE\" style=\"width: 400px;\">");
		sbServiceEngineerDetailsTablePopupDivForSkill.append("<tr>");
		sbServiceEngineerDetailsTablePopupDivForSkill
				.append("<td width=\"50%\" class=\"LABEL_TD\">Name:</td>");
		sbServiceEngineerDetailsTablePopupDivForSkill
				.append("<td width=\"50%\" class=\"CONTROL_TD\">"
						+ strServiceEnggName_p + "</td>");
		sbServiceEngineerDetailsTablePopupDivForSkill.append("</tr>");
		sbServiceEngineerDetailsTablePopupDivForSkill.append("</table>");
		sbServiceEngineerDetailsTablePopupDivForSkill
				.append("<table class=\"TABLE_STYLE\" style=\"width: 400px;\">");
		sbServiceEngineerDetailsTablePopupDivForSkill.append("<tr>");
		sbServiceEngineerDetailsTablePopupDivForSkill
				.append("<td class=\"LABEL_TD\" style=\"text-align: center; color: brown; background-color: #DEB887\" colspan=\"2\">Skills</td>");
		sbServiceEngineerDetailsTablePopupDivForSkill.append("</tr>");
		if (arrStrSkillName.length > 0) {
			/*
			 * If Service Engineer Skill Exists
			 */
			int nRowCounter = 0;
			for (String strSkillName : arrStrSkillName) {
				++nRowCounter;
				sbServiceEngineerDetailsTablePopupDivForSkill.append("<tr>");
				sbServiceEngineerDetailsTablePopupDivForSkill
						.append("<td width=\"10%\" class=\"LABEL_TD\">"
								+ nRowCounter + ".</td>");
				sbServiceEngineerDetailsTablePopupDivForSkill
						.append("<td width=\"90%\" class=\"CONTROL_TD\">"
								+ strSkillName + "</td>");
				sbServiceEngineerDetailsTablePopupDivForSkill.append("</tr>");
			}

		} else {

			/*
			 * If Service Engineer Skill Doesn't Exists
			 */

			sbServiceEngineerDetailsTablePopupDivForSkill.append("<tr>");
			sbServiceEngineerDetailsTablePopupDivForSkill
					.append("<td colspan=\"2\" class=\"CONTROL_TD\" style=\"text-align:center;color:red\">No Data Found!</td>");
			sbServiceEngineerDetailsTablePopupDivForSkill.append("</tr>");
		}
		sbServiceEngineerDetailsTablePopupDivForSkill.append("</table>");
		sbServiceEngineerDetailsTablePopupDivForSkill.append("</div>");

		return sbServiceEngineerDetailsTablePopupDivForSkill.toString();
	}

	private static String getPopupDivForJob(String strServiceEngineerName_p,
			String strServiceEnggJobData_p, String strDivId_p,
			StringBuffer sbNoOfJobs) {

		StringBuffer sbServiceEngineerDetailsTablePopupDivForJob = new StringBuffer(
				1000);
		String[] arrStrJobDesc = {};
		if (strServiceEnggJobData_p != null
				&& !strServiceEnggJobData_p.equals("")) {
			arrStrJobDesc = strServiceEnggJobData_p.split("\\^");
		}

		sbServiceEngineerDetailsTablePopupDivForJob.append("<div id=\""
				+ strDivId_p + "\" class=\"popup\" style=\"display: none;\">");

		/*
		 * Header Part
		 */
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<table class=\"TABLE_STYLE\" style=\"width: 400px; border-collapse: collapse;\">");
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<tr class=\"FOOTER_TR\">");
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<td style=\"text-align: left;\">Jobs...</td>");
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<td><img class=\"button\" tabindex=\"1\" src=\"/AHIMS/hisglobal/images/stop.png\" title=\"Close Popup\" style=\"cursor: pointer\" onclick=\"hide_popup_menu('"
						+ strDivId_p
						+ "');\" onkeypress=\"if(event.keyCode==13) hide_popup_menu('"
						+ strDivId_p + "');\" /></td>");
		sbServiceEngineerDetailsTablePopupDivForJob.append("</tr>");
		sbServiceEngineerDetailsTablePopupDivForJob.append("</table>");

		/*
		 * Service Engineer Data
		 */
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<table class=\"TABLE_STYLE\" style=\"width: 400px\">");
		sbServiceEngineerDetailsTablePopupDivForJob.append("<tr>");
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<td width=\"50%\" class=\"LABEL_TD\">Name:</td>");
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<td width=\"50%\" class=\"CONTROL_TD\">"
						+ strServiceEngineerName_p + "</td>");
		sbServiceEngineerDetailsTablePopupDivForJob.append("</tr>");
		sbServiceEngineerDetailsTablePopupDivForJob.append("</table>");
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<table class=\"TABLE_STYLE\" style=\"width: 400px; border-collapse: collapse;\">");
		sbServiceEngineerDetailsTablePopupDivForJob.append("<tr>");
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<td class=\"LABEL_TD\" style=\"text-align: center; color: brown; background-color: #DEB887\" colspan=\"2\">Jobs</td>");
		sbServiceEngineerDetailsTablePopupDivForJob.append("</tr>");
		sbServiceEngineerDetailsTablePopupDivForJob.append("</table>");
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<table class=\"TABLE_STYLE\" style=\"width: 400px;\">");
		sbServiceEngineerDetailsTablePopupDivForJob.append("<tr>");
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<td width=\"50%\" class=\"LABEL_TD\" style=\"text-align: center;\">Complaint Id</td>");
		sbServiceEngineerDetailsTablePopupDivForJob
				.append("<td width=\"50%\" class=\"LABEL_TD\" style=\"text-align: center;\">Expected Visit</td>");
		sbServiceEngineerDetailsTablePopupDivForJob.append("</tr>");

		sbNoOfJobs.append(arrStrJobDesc.length);
		if (arrStrJobDesc.length > 0) {
			/*
			 * If Job Exists.
			 */
			for (String strJobDesc : arrStrJobDesc) {
				String strComplaintId = strJobDesc.split("\\$")[0];
				String strExpectedVisit = strJobDesc.split("\\$")[1];
				sbServiceEngineerDetailsTablePopupDivForJob.append("<tr>");
				sbServiceEngineerDetailsTablePopupDivForJob
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strComplaintId + "</td>");
				sbServiceEngineerDetailsTablePopupDivForJob
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strExpectedVisit + "</td>");
				sbServiceEngineerDetailsTablePopupDivForJob.append("</tr>");

			}

		} else {

			/*
			 * If Job Doesn't Exists.
			 */

			sbServiceEngineerDetailsTablePopupDivForJob.append("<tr>");
			sbServiceEngineerDetailsTablePopupDivForJob
					.append("<td class=\"CONTROL_TD\" colspan=\"2\" style=\"text-align: center;color:red\">No Data Found!</td>");
			sbServiceEngineerDetailsTablePopupDivForJob.append("</tr>");
		}

		sbServiceEngineerDetailsTablePopupDivForJob.append("</table>");
		sbServiceEngineerDetailsTablePopupDivForJob.append("</div>");

		return sbServiceEngineerDetailsTablePopupDivForJob.toString();
	}

	public static String getFilterValue(ServiceEnggMstVO serviceEnggMstVO,
			String strFilterType) throws Exception {

		final String strFilterValueOptions;
		final BmedGlobalBO bmedGlobalBO;

		try {

			bmedGlobalBO = new BmedGlobalBO();

			if (strFilterType.equals("ENGG_ITEM_SUBTYPE")) {

				strFilterValueOptions = bmedGlobalBO
						.getItemSubTypeComboOptions(
								serviceEnggMstVO.getStrHospitalCode(),
								serviceEnggMstVO.getStrEnggItemTypeId());

			} else if (strFilterType.equals("SKILL")) {
				/*
				 * Option value 0 will be selected by default.
				 */
				strFilterValueOptions = bmedGlobalBO.getSkillComboOptions(
						serviceEnggMstVO.getStrHospitalCode(), "0");
			} else {
				throw new Exception("Filter Type: " + strFilterType
						+ "  is not supported.");
			}

		} catch (Exception e) {

			throw new Exception(
					"BmedGlobalBO.getFilterValue(ServiceEnggMstVO,String)-->"
							+ e.getMessage());
		}
		return strFilterValueOptions;
	}

	public static String getOtherServiceEngineerDetailsTable(
			ServiceEnggMstVO serviceEnggMstVO) throws Exception {

		String strOtherServiceEngineerDetailsTable = null;
		final BmedTransBO bmedTransBO;

		try {

			bmedTransBO = new BmedTransBO();

			bmedTransBO.getServiceEnggData(serviceEnggMstVO);

			strOtherServiceEngineerDetailsTable = getServiceEngineerDetailsTable(
					serviceEnggMstVO.getWrsData(), "OTHER_SERVICE_ENGG");

		} catch (Exception e) {

			throw new Exception(
					"BmedGlobalBO.getOtherServiceEngineerDetailsTable(ServiceEnggMstVO)-->"
							+ e.getMessage());
		}
		return strOtherServiceEngineerDetailsTable;
	}

	public static void initializeAttended(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p,HttpServletResponse response_p) {
		String strErrMsg;
		BmedGlobalBO bmedGlobalBO = null;
		BmedTransBO bmedTransBO = null;
		String strHospitalCode = null;
		String strReqId = null;
		final String strWarrantyDetailsTable;
		final String strMaintenanceContractDetailsTable;
		final String strSchedulesDetailsTable;
		final String strAttenderDetailsTable;
		final String strSparePartMaintenaceStatusTableRow;
		final String strSparePartStatusOptions;
		final String strSparePartDetailsTable;
		final String strTaskTable;

		
		String ajaxResponseString = "";
		
		final String strComplaintId;
		final String strComplaintDate;
		final String strItemName;
		final String strItemBatchNo;
		final String strItemSerialNo;
		final String strManufacturerSerialNo;
		final String strComplaintDescription;
		final String strWarrantyUptoUnitOptions;
		final String strSparePartNameOptions;
		final String strManufactureNameOptions;
		final String strMainteId;
		final String strVendorId;
		final String strStoreId;
		final String strItemId;
		final String strSerialNo;

		UserVO userVo = null;
		WarrantyDtlVO warrantyDtlVO = null;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		ComplaintScheduleDtlVO complaintScheduleDtlVO = null;
		ComplaintAttendDtlVO complaintAttendDtlVO = null;
		HemtItemSparePartDtlVO itemSparePartDtlVO = null;
		TaskMstVO taskMstVO = null;

		try {

			bmedGlobalBO = new BmedGlobalBO();
			bmedTransBO = new BmedTransBO();
			warrantyDtlVO = new WarrantyDtlVO();
			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			complaintScheduleDtlVO = new ComplaintScheduleDtlVO();
			complaintAttendDtlVO = new ComplaintAttendDtlVO();
			itemSparePartDtlVO = new HemtItemSparePartDtlVO();
			taskMstVO = new TaskMstVO();

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			if(complaintMaintenanceStatusFB_p.getStrStoreId()==null)
			
				complaintMaintenanceStatusFB_p.setStrStoreId(request_p.getParameter("storeId"));
			
			if(complaintMaintenanceStatusFB_p.getStrItemId()==null)
				complaintMaintenanceStatusFB_p.setStrItemId(request_p.getParameter("itemId"));
			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrStoreId(request_p.getParameter("storeId"));
			complaintRequestDtlVO.setStrItemId(complaintMaintenanceStatusFB_p.getStrItemId());
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			complaintRequestDtlVO.setStrEnggItemSubTypeId(request_p.getParameter("enggItemSubType"));
			complaintRequestDtlVO.setStrEnggItemTypeId(request_p.getParameter("enggItemType"));
			complaintScheduleDtlVO.setStrHospitalCode(strHospitalCode);
			complaintAttendDtlVO.setStrHospitalCode(strHospitalCode);
			
			String strStockInf="";
			String strNonItemId="";
			if (request_p.getParameter("strPageFlag").equals("ITEM")) {
				 strStockInf = (String) request_p.getParameter("strStockInf");

				/*
				 * 0 1 2
				 * HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
				 * 3 4 ||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'|| 5 6
				 * 7
				 * GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE||'^'||Is_Item
				 * (1 : for Item 2:for Non-Item)
				 */

				
				itemSparePartDtlVO.setStrHospitalCode(strStockInf
						.split("\\^")[5]);
				itemSparePartDtlVO.setStrItemId(strStockInf.split("\\^")[2]);
				itemSparePartDtlVO
						.setStrItemSlNo(strStockInf.split("\\^")[4]);// Dummy 0

				

			}

			if (request_p.getParameter("strPageFlag").equals("NON_ITEM")) {
				strNonItemId = request_p.getParameter("nonItemId");

				
				
				itemSparePartDtlVO.setStrItemId(strNonItemId);
				itemSparePartDtlVO.setStrItemSlNo("0");// Dummy 0

				
			}
			
			
			
			//itemSparePartDtlVO.setStrHospitalCode(strHospitalCode);
			itemSparePartDtlVO.setStrStoreId(request_p.getParameter("storeId"));
			//itemSparePartDtlVO.setStrItemId(complaintMaintenanceStatusFB_p.getStrItemId());
			itemSparePartDtlVO.setStrSpareId("0");
			itemSparePartDtlVO.setStrItemManufSlno("0");
			itemSparePartDtlVO.setStrItemSlNo("0");
			//itemSparePartDtlVO.setStrVendorId(strVendorId)
			taskMstVO.setStrHospitalCode(strHospitalCode);

				
			
			
			
			/*
			 * Getting Request Data:
			 */
			strReqId = complaintMaintenanceStatusFB_p.getStrHiddenComplaintId();

			complaintRequestDtlVO.setStrReqId(strReqId);
			complaintScheduleDtlVO.setStrReqId(strReqId);
			complaintAttendDtlVO.setStrReqId(strReqId);
			complaintRequestDtlVO.setStrSerialNo(itemSparePartDtlVO
						.getStrItemSlNo());
			bmedTransBO.sparePartsDetailsData(itemSparePartDtlVO);
			//bmedTransBO.tasklDetailsData(taskMstVO, complaintRequestDtlVO);
			//bmedTransBO.initializeComplaintActions(complaintRequestDtlVO,
					//warrantyDtlVO, hemtItemMcDtlVO, complaintScheduleDtlVO,
					//complaintAttendDtlVO, null, false, itemSparePartDtlVO,
					//true, taskMstVO, true);

			strComplaintId = complaintRequestDtlVO.getStrReqId();
			strComplaintDate = complaintRequestDtlVO.getStrReqDate();
			strItemName = complaintRequestDtlVO.getStrItemName();
			strItemBatchNo = complaintRequestDtlVO.getStrBatchNo();
			strItemSerialNo = complaintRequestDtlVO.getStrSerialNo();
			strManufacturerSerialNo = complaintRequestDtlVO
					.getStrManufSerialNo();
			strComplaintDescription = complaintRequestDtlVO
					.getStrComplaintDes();

			strMainteId = complaintRequestDtlVO.getStrMainteId();
			strVendorId = complaintRequestDtlVO.getStrVendorId();
			strStoreId = complaintRequestDtlVO.getStrStoreId();
			strItemId = complaintRequestDtlVO.getStrItemId();
			strSerialNo = complaintRequestDtlVO.getStrSerialNo();

			complaintMaintenanceStatusFB_p.setStrComplaintId(strComplaintId);
			complaintMaintenanceStatusFB_p
					.setStrComplaintDate(strComplaintDate);
			complaintMaintenanceStatusFB_p.setStrItemName(strItemName);
			complaintMaintenanceStatusFB_p.setStrItemBatchNo(strItemBatchNo);
			complaintMaintenanceStatusFB_p.setStrItemSerialNo(strItemSerialNo);
			complaintMaintenanceStatusFB_p
					.setStrManufacturerSerialNo(strManufacturerSerialNo);
			complaintMaintenanceStatusFB_p
					.setStrComplaintDescription(strComplaintDescription);
			complaintMaintenanceStatusFB_p.setStrMainteId(strMainteId);
			complaintMaintenanceStatusFB_p.setStrVendorId(strVendorId);
			complaintMaintenanceStatusFB_p.setStrStoreId(strStoreId);
			complaintMaintenanceStatusFB_p.setStrItemId(strItemId);
			complaintMaintenanceStatusFB_p.setStrSerialNo(strSerialNo);

			strWarrantyDetailsTable = getWarrantyDetailsTable(
					warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(
					hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");
			strSchedulesDetailsTable = getSchedulesDetailsTable(complaintScheduleDtlVO
					.getWrsData());
			strAttenderDetailsTable = getAttenderDetailsTable(complaintAttendDtlVO
					.getWrsData());

			strSparePartMaintenaceStatusTableRow = getSparePartMaintenaceStatusTableRow(itemSparePartDtlVO
					.getWrsData());
			strSparePartDetailsTable = getSparePartDetailsTableRow(itemSparePartDtlVO
					.getWrsData());
			//strTaskTable = getTaskTableRow(taskMstVO.getWrsData());

			strSparePartStatusOptions = bmedGlobalBO.getSparePartStatusOptions(
					strHospitalCode, "0");
		strWarrantyUptoUnitOptions = BmedGlobalBO
				.getUnitComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			strSparePartNameOptions = bmedGlobalBO.getAllSparePartOptions(
					Config.SUPER_USER_HOSPITAL_CODE, "0");
			strManufactureNameOptions = bmedGlobalBO
					.getManufacturerNameComboOptions(Config.SUPER_USER_HOSPITAL_CODE);

			complaintMaintenanceStatusFB_p
					.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrSchedulesDetailsTable(strSchedulesDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrAttenderDetailsTable(strAttenderDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrSparePartMaintenaceStatusTableRow(strSparePartMaintenaceStatusTableRow);

			complaintMaintenanceStatusFB_p
					.setStrSparePartStatusOptions(strSparePartStatusOptions);
			
			ajaxResponseString+=strSparePartStatusOptions+"#";
			complaintMaintenanceStatusFB_p
					.setStrWarrantyUptoUnitOptions(strWarrantyUptoUnitOptions);
			ajaxResponseString+=strWarrantyUptoUnitOptions+"#";
			complaintMaintenanceStatusFB_p
					.setStrSparePartNameOptions(strSparePartNameOptions);
			ajaxResponseString+=strSparePartNameOptions+"#";
			complaintMaintenanceStatusFB_p
					.setStrManufactureNameOptions(strManufactureNameOptions);
			ajaxResponseString+=strManufactureNameOptions+"#";
			ajaxResponseString+=strSparePartMaintenaceStatusTableRow;
			
			//System.out.println("****"+strSparePartDetailsTable);
			//System.out.println("****"+ajaxResponseString);
			//complaintMaintenanceStatusFB_p
					//.setStrSparePartDetailsTable(strSparePartDetailsTable);
			//complaintMaintenanceStatusFB_p.setStrTaskTable(strTaskTable);

			
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(ajaxResponseString);
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeCancel --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	/**
	 * This method is used in Attender Details Process.
	 * 
	 * @param wrsData
	 * @return
	 * @throws SQLException
	 */
	private static String getSparePartDetailsTableRow(WebRowSet webRowSet_p)
			throws SQLException {
		StringBuffer sbTableRow = new StringBuffer(1000);

		String strSparePartName;
		String strSparePartPrimaryKey;
		String strSparePartSerialNumber;
		String strSparePartBatchNumber;
		String strSparePartManufacturerNumber;

		webRowSet_p.beforeFirst();

		if (webRowSet_p.size() > 0) {

			while (webRowSet_p.next()) {

				// HEMNUM_ITEM_ID: 1
				// HEMNUM_STORE_ID: 2
				// GNUM_HOSPITAL_CODE: 3
				// HEMSTR_ITEM_SL_NO: 4
				// HEMNUM_SPARE_ID: 5
				// HEMSTR_SPARE_SL_NO: 6
				// HEMNUM_SL_NO: 7
				// HEMSTR_ITEM_MANUF_SLNO: 8
				// HEMSTR_SPARE_MANUF_SLNO: 9
				// HEMNUM_VENDOR_ID: 10
				// HEMDT_WARRANTY_DATE: 11
				// HSTNUM_WARRANTY_UPTO: 12
				// HSTNUM_WARRANTY_UPTO_UNIT: 13
				// HEMSTR_SPECIFICATION: 14
				// HEMNUM_IS_ADDED: 15
				// HEMNUM_STATUS: 16
				// HEMDT_ACTION_DATE: 17
				// GDT_ENTRY_DATE: 18
				// GNUM_ISVALID: 19
				// GNUM_SEATID: 20
				// ITEM_NAME: 21
				// STORE_NAME: 22
				// SPARE_PART_NAME: 23
				// VENDOR_NAME: 24
				// WARRANTY_UPTO_UNIT_NAME: 25
				// STATUS_NAME: 26
				// PRIMARY_KEY: 27
				strSparePartName = webRowSet_p.getString("SPARE_PART_NAME");
				strSparePartPrimaryKey = webRowSet_p.getString("PRIMARY_KEY");
				strSparePartSerialNumber = webRowSet_p
						.getString("HEMSTR_SPARE_SL_NO");

				// strSparePartBatchNumber=webRowSet_p.getString("ITEM_NAME");
				/* There is no corresponding column in Table */
				strSparePartBatchNumber = "Not Provided";

				strSparePartManufacturerNumber = webRowSet_p
						.getString("HEMSTR_SPARE_MANUF_SLNO");

				if (strSparePartName == null) {
					strSparePartName = "---";
				}

				if (strSparePartPrimaryKey == null) {
					strSparePartPrimaryKey = "---";
				}
				if (strSparePartSerialNumber == null) {
					strSparePartSerialNumber = "---";
				}

				/*
				 * This code will be activated when the design issue related to
				 * batch no is solved.
				 */
				/*
				 * if (strSparePartBatchNumber == null) {
				 * strSparePartBatchNumber = "---"; }
				 */
				if (strSparePartManufacturerNumber == null) {
					strSparePartManufacturerNumber = "---";
				}
				sbTableRow.append("<tr>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><input type=\"radio\" name=\"strSparePartStockDetailsRadio\" value=\""
								+ strSparePartPrimaryKey
								+ "\" onclick=\"showAddSparePartDetailsInReplaceMode('"
								+ strSparePartName + "')\"></td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strSparePartName + "</td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strSparePartSerialNumber + "</td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strSparePartBatchNumber + "</td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strSparePartManufacturerNumber + "</td>");
				sbTableRow.append("</tr>");

			}

		} else {

			sbTableRow.append("<tr>");
			sbTableRow
					.append("<td colspan=\"6\" class=\"CONTROL_TD\" style=\"text-align: center;color:red\">No Data Found!</td>");
			sbTableRow.append("</tr>");

		}

		return sbTableRow.toString();
	}

	private static String getSparePartMaintenaceStatusTableRow(
			WebRowSet webRowSet_p) throws SQLException {
		StringBuffer sbTableRow = new StringBuffer(1000);

		if (webRowSet_p.size() > 0) {

			String strSparePartName;
			String strManufacturerName;
			String strManufacturerId;
			String strItemSerialNo;
			String strStatusName;

			sbTableRow.append("<table class='TABLE_STYLE' >" );
			sbTableRow.append("<tr>");
			sbTableRow.append(" <td width='15%' class='LABEL_TD' style='text-align: center;'>Spare Part Name</td>");
			sbTableRow.append("<td width='15%' class='LABEL_TD' style='text-align: center;'>Manufacturer Name</td>");
			sbTableRow.append("<td width='20%' class='LABEL_TD' style='text-align: center;'>Manufacturer Id</td>");
			sbTableRow.append("<td width='20%' class='LABEL_TD' style='text-align: center;'>Item Serial No.</td>");
			sbTableRow.append("<td width='15%' class='LABEL_TD' style='text-align: center;'>Status</td>");
			sbTableRow.append("<td width='15%' class='LABEL_TD' style='text-align: center;'>&nbsp;</td>");
			sbTableRow.append("</tr>");
		
			
			while (webRowSet_p.next()) {

				// HEMNUM_ITEM_ID: 1
				// HEMNUM_STORE_ID: 2
				// GNUM_HOSPITAL_CODE: 3
				// HEMSTR_ITEM_SL_NO: 4
				// HEMNUM_SPARE_ID: 5
				// HEMSTR_SPARE_SL_NO: 6
				// HEMNUM_SL_NO: 7
				// HEMSTR_ITEM_MANUF_SLNO: 8
				// HEMSTR_SPARE_MANUF_SLNO: 9
				// HEMNUM_VENDOR_ID: 10
				// HEMDT_WARRANTY_DATE: 11
				// HSTNUM_WARRANTY_UPTO: 12
				// HSTNUM_WARRANTY_UPTO_UNIT: 13
				// HEMSTR_SPECIFICATION: 14
				// HEMNUM_IS_ADDED: 15
				// HEMNUM_STATUS: 16
				// HEMDT_ACTION_DATE: 17
				// GDT_ENTRY_DATE: 18
				// GNUM_ISVALID: 19
				// GNUM_SEATID: 20
				// ITEM_NAME: 21
				// STORE_NAME: 22
				// SPARE_PART_NAME: 23
				// VENDOR_NAME: 24
				// WARRANTY_UPTO_UNIT_NAME: 25
				// STATUS_NAME: 26
				strSparePartName = webRowSet_p.getString("SPARE_PART_NAME");
				strManufacturerName = webRowSet_p.getString("VENDOR_NAME");
				strManufacturerId = webRowSet_p
						.getString("HEMSTR_SPARE_MANUF_SLNO");
				strItemSerialNo = webRowSet_p.getString("HEMSTR_SPARE_SL_NO");
				strStatusName = webRowSet_p.getString("STATUS_NAME");

				if (strSparePartName == null) {
					strSparePartName = "---";
				}

				if (strManufacturerName == null) {
					strManufacturerName = "---";
				}
				if (strManufacturerId == null) {
					strManufacturerId = "---";
				}
				if (strItemSerialNo == null) {
					strItemSerialNo = "---";
				}
				if (strStatusName == null) {
					strStatusName = "---";
				}

				
				
				sbTableRow.append("<tr>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strSparePartName + "</td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strManufacturerName + "</td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strManufacturerId + "</td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strItemSerialNo + "</td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strStatusName + "</td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><a style=\"cursor: pointer; color: blue;\" onclick=\"alert('View...');\">View...</a></td>");
				sbTableRow.append("</tr>");

			}

		} else {

			sbTableRow.append("<tr>");
			sbTableRow
					.append("<td colspan=\"6\" class=\"CONTROL_TD\" style=\"text-align: center;color:red\">No Data Found!</td>");
			sbTableRow.append("</tr>");

		}
		sbTableRow.append("</table>" );
		return sbTableRow.toString();
	}

	private static String getTaskTableRow(WebRowSet webRowSet_p)
			throws SQLException {

		StringBuffer sbTableRow = new StringBuffer(1000);

		if (webRowSet_p.size() > 0) {

			int nCount = 0;
			String strTaskName;
			String strTaskId;

			while (webRowSet_p.next()) {

				// HEMNUM_ENGG_ITEM_TYPE_ID: 1
				// HEMNUM_TASK_ID: 2
				// GNUM_HOSPITAL_CODE: 3
				// HEMNUM_ENGG_ITEM_SUB_TYPE_ID: 4
				// HEMSTR_TASK_NAME: 5
				// GDT_EFFECTIVE_FROM: 6
				// GSTR_REMARKS: 7
				// GDT_ENTRY_DATE: 8
				// GNUM_SEATID: 9
				// GNUM_ISVALID: 10
				// GDT_LSTMOD_DATE: 11
				// GNUM_LSTMOD_SEATID: 12
				// ENGG_ITEM_TYPE_NAME: 13
				// ENGG_ITEM_SUB_TYPE_NAME: 14

				++nCount;
				strTaskName = webRowSet_p.getString("HEMSTR_TASK_NAME");
				strTaskId = webRowSet_p.getString("HEMNUM_TASK_ID");

				if (strTaskName == null) {
					strTaskName = "---";
				}

				if (strTaskId == null) {
					strTaskId = "---";
				}

				sbTableRow.append("<tr>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ nCount + ")</td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
								+ strTaskName + "</td>");
				sbTableRow
						.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><input type=\"checkbox\" name=\"arrStrTaskId\" value=\""
								+ strTaskId + "\"></td>");
				sbTableRow.append("</tr>");

			}

		} else {

			sbTableRow.append("<tr>");
			sbTableRow
					.append("<td colspan=\"3\" class=\"CONTROL_TD\" style=\"text-align: center;color:red\">No Data Found!</td>");
			sbTableRow.append("</tr>");

		}

		return sbTableRow.toString();

	}

	public static void saveAttended(
			ComplaintAttendDtlVO complaintAttendDtlVO_p,
			ComplaintRequestDtlVO complaintRequestDtlVO_p,
			HemtComplaintStatusDtlVO complaintStatusDtlVO_p,
			HemtItemSparePartDtlVO hemtItemSparePartDtlVO_p,
			HemtItemSparePartDtlVO oldHemtItemSparePartDtlVO_p)
			throws Exception {
		BmedTransBO bmedTransBO = null;
		try {
			bmedTransBO = new BmedTransBO();
			bmedTransBO.saveAttendDetailsProcess(complaintAttendDtlVO_p,
					complaintRequestDtlVO_p, complaintStatusDtlVO_p,
					hemtItemSparePartDtlVO_p, oldHemtItemSparePartDtlVO_p);

		} catch (Exception e) {

			throw new Exception(
					"ComplaintMaintenanceStatusTransDATA.saveAttended(ComplaintAttendDtlVO,ComplaintRequestDtlVO,HemtComplaintStatusDtlVO,HemtItemSparePartDtlVO,HemtItemSparePartDtlVO)-->"
							+ e.getMessage());
		}

	}

	/**
	 * To initialize Complaint Details View Page
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 * 
	 */
	public static void initializeComplaintDetailsView(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		String strErrMsg;
		String strReqId;
		BmedTransBO bmedTransBO = null;
		String strHospitalCode;

		String strWarrantyDetailsTable;
		String strMaintenanceContractDetailsTable;
		String strApprovalDetailsTable;
		String strSchedulesDetailsTable;
		String strAttenderDetailsTable;
		String strRemindersDetailsTable;
		String strGrievancesDetailsTable;
		String strSparePartDetailsTable;
		String strCloseDetailsTable;

		WarrantyDtlVO warrantyDtlVO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;
		HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO = null;
		ComplaintScheduleDtlVO complaintScheduleDtlVO = null;
		ComplaintAttendDtlVO complaintAttendDtlVO = null;
		HemtReminderDtlVO hemtReminderDtlVO = null;
		HemtComplaintEscalationDtlVO hemtComplaintEscalationDtlVO = null;

		HemtItemSparePartDtlVO hemtItemSparePartDtlVO = null;

		UserVO userVo = null;
		try {
			bmedTransBO = new BmedTransBO();

			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			warrantyDtlVO = new WarrantyDtlVO();
			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			hemtComplaintApprovalDtlVO = new HemtComplaintApprovalDtlVO();
			complaintScheduleDtlVO = new ComplaintScheduleDtlVO();
			complaintAttendDtlVO = new ComplaintAttendDtlVO();
			hemtReminderDtlVO = new HemtReminderDtlVO();
			hemtComplaintEscalationDtlVO = new HemtComplaintEscalationDtlVO();
			hemtItemSparePartDtlVO = new HemtItemSparePartDtlVO();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			hemtComplaintApprovalDtlVO.setStrHospitalCode(strHospitalCode);
			complaintScheduleDtlVO.setStrHospitalCode(strHospitalCode);
			complaintAttendDtlVO.setStrHospitalCode(strHospitalCode);
			hemtReminderDtlVO.setStrHospitalCode(strHospitalCode);
			hemtComplaintEscalationDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemSparePartDtlVO.setStrHospitalCode(strHospitalCode);

			/*
			 * Getting Request Data:
			 */
			strReqId = complaintMaintenanceStatusFB_p.getStrComplaintId();

			complaintRequestDtlVO.setStrReqId(strReqId);
			hemtComplaintApprovalDtlVO.setStrReqId(strReqId);
			complaintScheduleDtlVO.setStrReqId(strReqId);
			complaintAttendDtlVO.setStrReqId(strReqId);
			hemtReminderDtlVO.setStrReqId(strReqId);
			hemtComplaintEscalationDtlVO.setStrReqId(strReqId);

			// Calling BO
			bmedTransBO.initializeComplaintDetailsView(complaintRequestDtlVO,
					warrantyDtlVO, hemtItemMcDtlVO, hemtComplaintApprovalDtlVO,
					complaintScheduleDtlVO, complaintAttendDtlVO,
					hemtReminderDtlVO, hemtComplaintEscalationDtlVO,
					hemtItemSparePartDtlVO);

			strWarrantyDetailsTable = getWarrantyDetailsTable(
					warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(
					hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");

			strApprovalDetailsTable = getApprovalDetailsTable(hemtComplaintApprovalDtlVO
					.getWrsData());
			strSchedulesDetailsTable = getSchedulesDetailsTable(complaintScheduleDtlVO
					.getWrsData());
			strAttenderDetailsTable = getAttenderDetailsTable(complaintAttendDtlVO
					.getWrsData());
			strRemindersDetailsTable = getRemindersDetailsTable(hemtReminderDtlVO
					.getWrsData());
			strGrievancesDetailsTable = getGrievancesDetailsTable(hemtComplaintEscalationDtlVO
					.getWrsData());
			strSparePartDetailsTable = getSparePartDetailsTable(hemtItemSparePartDtlVO
					.getWrsData());
			strCloseDetailsTable = getCloseDetailsTable(complaintRequestDtlVO
					.getWrsData());

			complaintMaintenanceStatusFB_p
					.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrApprovalDetailsTable(strApprovalDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrSchedulesDetailsTable(strSchedulesDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrAttenderDetailsTable(strAttenderDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrRemindersDetailsTable(strRemindersDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrGrievancesDetailsTable(strGrievancesDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrSparePartDetailsTable(strSparePartDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrCloseDetailsTable(strCloseDetailsTable);

		} catch (Exception e) {
			strErrMsg = "ComplaintMaintenanceStatusDATA.initializeComplaintDetailsView(complaintMaintenanceStatusFB_p,request_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/**
	 * To get the Approval Details HLP
	 * 
	 * @param wrsData_p
	 *            the WebRowSet
	 */
	private static String getApprovalDetailsTable(WebRowSet wrsData_p)
			throws SQLException {

		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);

		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");
		sbHeader.append("<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Approved By</td>");
		sbHeader.append("<td width=\"40%\" class=\"LABEL_TD\" style=\"text-align: center;\">Approval Date/Time</td>");
		sbHeader.append("<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Status</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {

			/* Result Index */
			// HEMNUM_REQ_ID: 1
			// GNUM_HOSPITAL_CODE: 2
			// HEMNUM_SLNO: 3
			// HEMNUM_REQ_TYPE: 4
			// HEMNUM_IS_PREVENTIVE: 5
			// HEMNUM_IS_WORKING: 6
			// HEMNUM_ENGG_ITEM_TYPE_ID: 7
			// HEMNUM_ENGG_ITEM_SUB_TYPE_ID: 8
			// HEMSTR_EMP_NAME: 9
			// HEMSTR_APPROVED_BY: 10
			// HEMSTR_APPROVED_DATE: 11
			// HEMNUM_APPROVAL_STATUS: 12
			// HEMSTR_EMP_ID: 13
			// HEMNUM_DEPT_ID: 14
			// GSTR_REMARKS: 15
			// GDT_ENTRY_DATE: 16
			// GNUM_ISVALID: 17
			// GNUM_SEATID: 18

			while (wrsData_p.next()) {

				String strApprovedBy = wrsData_p
						.getString("HEMSTR_APPROVED_BY");
				String strApprovedDate = wrsData_p
						.getString("HEMSTR_APPROVED_DATE");
				String strApprovalStatus = wrsData_p
						.getString("HEMNUM_APPROVAL_STATUS");

				if (strApprovedBy == null) {
					strApprovedBy = "---";
				}
				if (strApprovedDate == null) {
					strApprovedDate = "---";
				}
				if (strApprovalStatus == null) {
					strApprovalStatus = "---";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strApprovedBy + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strApprovedDate + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strApprovalStatus + "</td>");
				sbBody.append("</tr>");

			}

		} else {
			sbBody.append("<tr>");
			sbBody.append("<td colspan=\"3\" class=\"CONTROL_TD\" style=\"text-align: center; color: red;\">No Data Found!</td></td>");
			sbBody.append("</tr>");
		}

		return sbHeader.toString() + sbBody.toString();
	}

	/**
	 * To get Reminders Details HLP
	 * 
	 * * @param wrsData_p the WebRowSet
	 */
	private static String getRemindersDetailsTable(WebRowSet wrsData_p)
			throws SQLException {

		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);

		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");
		sbHeader.append("<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Reminder By Name</td>");
		sbHeader.append("<td width=\"40%\" class=\"LABEL_TD\" style=\"text-align: center;\">No. of Reminders</td>");
		sbHeader.append("<td width=\"30%\" class=\"LABEL_TD\" style=\"text-align: center;\">Date/ Time</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			// HEMNUM_REQ_ID: 1
			// GNUM_HOSPITAL_CODE: 2
			// HEMNUM_REMINDER_ID: 3
			// HEMNUM_REQ_TYPE: 4
			// HEMSTR_REMINDER_BY_ID: 5
			// HEMSTR_REMINDER_REMARKS: 6
			// HEMSTR_REMINDER_SENT_DATE: 7
			// HEMSTR_REMINDER_REPLY_ID: 8
			// HEMSTR_REPLY_REMARKS: 9
			// HEMSTR_REMINDER_REPLY_DATE: 10
			// HEMNUM_REMINDER_STATUS: 11
			// GDT_ENTRY_DATE: 12
			// GNUM_ISVALID: 13
			// GNUM_SEATID: 14

			while (wrsData_p.next()) {

				String strReminderByName = wrsData_p
						.getString("REMINDER_BY_NAME");
				String strNoOfReminders = wrsData_p
						.getString("NO_OF_REMINDERS");
				String strDateAndTime = wrsData_p
						.getString("HEMSTR_REMINDER_SENT_DATE");

				if (strReminderByName == null) {
					strReminderByName = "---";
				}
				if (strNoOfReminders == null) {
					strNoOfReminders = "---";
				}
				if (strDateAndTime == null) {
					strDateAndTime = "---";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strReminderByName + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strNoOfReminders + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strDateAndTime + "</td>");
				sbBody.append("</tr>");

			}

		} else {
			sbBody.append("<tr>");
			sbBody.append("<td colspan=\"3\" class=\"CONTROL_TD\" style=\"text-align: center; color: red;\">No Data Found!</td></td>");
			sbBody.append("</tr>");
		}

		return sbHeader.toString() + sbBody.toString();
	}

	/**
	 * To get Grievances Details HLP
	 * 
	 * @param wrsData_p
	 *            the WebRowSet
	 */
	private static String getGrievancesDetailsTable(WebRowSet wrsData_p)
			throws SQLException {
		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);
		int nWidth;
		int nColspan;
		nWidth = 20;
		nColspan = 5;
		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");

		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Grievance Type</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Service Engineer/Vendor Name </td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Escalation Level </td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Date/Time</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Mode of Escalation </td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			// REQ_TYPE: 1
			// SERVICE_ENGINEER: 2
			// HEMNUM_ESCALATION_LEVEL: 3
			// ESCALATION_DATE: 4
			// ESCALATION_MODE: 5

			while (wrsData_p.next()) {

				String strGrievanceType = wrsData_p.getString("REQ_TYPE");
				String strServiceEngineer = wrsData_p
						.getString("SERVICE_ENGINEER");
				String strEscalationLevel = wrsData_p
						.getString("HEMNUM_ESCALATION_LEVEL");
				String strDateAndTime = wrsData_p.getString("ESCALATION_DATE");
				String strModeofEscalation = wrsData_p
						.getString("ESCALATION_MODE");

				if (strGrievanceType == null) {
					strGrievanceType = "---";
				}
				if (strServiceEngineer == null) {
					strServiceEngineer = "---";
				}
				if (strEscalationLevel == null) {
					strEscalationLevel = "---";
				}

				if (strDateAndTime == null) {
					strDateAndTime = "---";
				}
				if (strModeofEscalation == null) {
					strModeofEscalation = "";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");

				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strGrievanceType + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strServiceEngineer + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strEscalationLevel + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strDateAndTime + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strModeofEscalation + "</td>");
				sbBody.append("</tr>");

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
	 * To get Spare Part Details HLP
	 * 
	 * @param wrsData_p
	 *            the WebRowSet
	 */
	private static String getSparePartDetailsTable(WebRowSet wrsData_p)
			throws SQLException {
		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);
		int nWidth;
		int nColspan;
		nWidth = 25;
		nColspan = 4;
		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");

		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Spare Part Name </td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Spare Part Serial No.</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Manufacturer Name </td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Manufacturer No.</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			// SPARE_PART_NAME: 1
			// HEMSTR_SPARE_SL_NO: 2
			// MANUFACTURER_NAME: 3
			// HEMSTR_SPARE_MANUF_SLNO: 4

			while (wrsData_p.next()) {

				String strSparePartName = wrsData_p
						.getString("SPARE_PART_NAME");
				String SparePartSerialNo = wrsData_p
						.getString("HEMSTR_SPARE_SL_NO");
				String strManufacturerName = wrsData_p
						.getString("MANUFACTURER_NAME");
				String strManufacturerSlNo = wrsData_p
						.getString("HEMSTR_SPARE_MANUF_SLNO");

				if (strSparePartName == null) {
					strSparePartName = "---";
				}
				if (SparePartSerialNo == null) {
					SparePartSerialNo = "---";
				}
				if (strManufacturerName == null) {
					strManufacturerName = "---";
				}
				if (strManufacturerSlNo == null) {
					strManufacturerSlNo = "---";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");

				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strSparePartName + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ SparePartSerialNo + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strManufacturerName + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strManufacturerSlNo + "</td>");
				sbBody.append("</tr>");

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
	 * To get Close Details HLP
	 * 
	 * @param wrsData_p
	 *            the WebRowSet
	 */
	private static String getCloseDetailsTable(WebRowSet wrsData_p)
			throws SQLException {
		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);
		int nWidth;
		int nColspan;
		nWidth = 20;
		nColspan = 5;
		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");

		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Verified By</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Reason of Closing </td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Date/ Time </td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Total Cost </td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Total Down Time</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			// VERIFIED_BY_NAME: 1
			// HEMSTR_CLOSE_REASON: 2
			// HEMDT_CLOSED_DATE: 3
			// HEMNUM_COST: 4
			// HEMSTR_DOWN_TIME_FR: 5

			wrsData_p.beforeFirst();
			while (wrsData_p.next()) {

				String strVerifiedByName = wrsData_p
						.getString("VERIFIED_BY_NAME");
				String strCloseReason = wrsData_p
						.getString("HEMSTR_CLOSE_REASON");
				String strClosedDate = wrsData_p.getString("HEMDT_CLOSED_DATE");
				String strCost = wrsData_p.getString("HEMNUM_COST");
				String strDownTimeFr = wrsData_p
						.getString("HEMSTR_DOWN_TIME_FR");

				if (strVerifiedByName == null) {
					strVerifiedByName = "---";
				}
				if (strCloseReason == null) {
					strCloseReason = "---";
				}
				if (strClosedDate == null) {
					strClosedDate = "---";
				}

				if (strCost == null) {
					strCost = "---";
				}
				if (strDownTimeFr == null) {
					strDownTimeFr = "";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");

				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strVerifiedByName + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strCloseReason + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strClosedDate + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strCost + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strDownTimeFr + "</td>");
				sbBody.append("</tr>");

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
	 * To initialize Reminder Page
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 * 
	 */
	public static void initializeReminder(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		HisUtil hisutil = null;

		String strErrMsg;
		String strReqId;
		BmedTransBO bmedTransBO = null;
		String strHospitalCode;

		String strCtDate;
		String strComplaintId;
		String strComplaintDate;
		String strItemName;
		String strItemBatchNo;
		String strItemSerialNo;
		String strManufacturerSerialNo;
		String strComplaintDescription;
		String strContactNo = "---";
		String strEmailId = "---";
		String strReqType;
		String strDeptName;
		String strEmpId;
		String strNoOfReminders = "---";
		String strReminderByName = "---";

		String strWarrantyDetailsTable;
		String strMaintenanceContractDetailsTable;

		WarrantyDtlVO warrantyDtlVO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		HemtReminderDtlVO hemtReminderDtlVO = null;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;

		UserVO userVo = null;
		try {

			hisutil = new HisUtil("bmed", "ComplaintMaintenanceStatusDATA");

			bmedTransBO = new BmedTransBO();

			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			warrantyDtlVO = new WarrantyDtlVO();
			hemtReminderDtlVO = new HemtReminderDtlVO();
			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			hemtReminderDtlVO.setStrHospitalCode(strHospitalCode);

			strCtDate = hisutil.getDSDate("DD-MON-YYYY HH24:MI");

			/*
			 * Getting Request Data:
			 */
			strReqId = complaintMaintenanceStatusFB_p.getStrHiddenComplaintId();

			complaintRequestDtlVO.setStrReqId(strReqId);
			hemtReminderDtlVO.setStrReqId(strReqId);
			;

			// Calling BO
			bmedTransBO.initializeReminder(complaintRequestDtlVO,
					warrantyDtlVO, hemtItemMcDtlVO, hemtReminderDtlVO);

			strComplaintId = complaintRequestDtlVO.getStrReqId();
			strComplaintDate = complaintRequestDtlVO.getStrReqDate();
			strItemName = complaintRequestDtlVO.getStrItemName();
			strItemBatchNo = complaintRequestDtlVO.getStrBatchNo();
			strItemSerialNo = complaintRequestDtlVO.getStrSerialNo();
			strManufacturerSerialNo = complaintRequestDtlVO
					.getStrManufSerialNo();
			strComplaintDescription = complaintRequestDtlVO
					.getStrComplaintDes();
			strContactNo = complaintRequestDtlVO.getStrContactNo();
			strReqType = complaintRequestDtlVO.getStrReqType();
			strDeptName = complaintRequestDtlVO.getStrDeptName();
			strEmpId = complaintRequestDtlVO.getStrEmpId();

			if (hemtReminderDtlVO.getWrsData() != null
					&& hemtReminderDtlVO.getWrsData().size() > 0) {
				while (hemtReminderDtlVO.getWrsData().next()) {
					strNoOfReminders = hemtReminderDtlVO.getWrsData()
							.getString("NO_OF_REMINDERS");

					strReminderByName = hemtReminderDtlVO.getWrsData().getString("REMINDER_BY_NAME");

					strEmailId = hemtReminderDtlVO.getWrsData().getString("EMAIL_ID");

					strContactNo = hemtReminderDtlVO.getWrsData().getString("CONTACT_NO");
				}
			}

			complaintMaintenanceStatusFB_p.setStrComplaintId(strComplaintId);
			complaintMaintenanceStatusFB_p
					.setStrComplaintDate(strComplaintDate);
			complaintMaintenanceStatusFB_p.setStrItemName(strItemName);
			complaintMaintenanceStatusFB_p.setStrItemBatchNo(strItemBatchNo);
			complaintMaintenanceStatusFB_p.setStrItemSerialNo(strItemSerialNo);
			complaintMaintenanceStatusFB_p
					.setStrManufacturerSerialNo(strManufacturerSerialNo);
			complaintMaintenanceStatusFB_p
					.setStrComplaintDescription(strComplaintDescription);
			complaintMaintenanceStatusFB_p.setStrContactNo(strContactNo);
			complaintMaintenanceStatusFB_p.setStrComplaintType(strReqType);
			complaintMaintenanceStatusFB_p.setStrDeptName(strDeptName);
			complaintMaintenanceStatusFB_p.setStrEmpId(strEmpId);
			complaintMaintenanceStatusFB_p.setStrCtDate(strCtDate);
			complaintMaintenanceStatusFB_p
					.setStrNoOfReminders(strNoOfReminders);
			complaintMaintenanceStatusFB_p
					.setStrContactPerson(strReminderByName);
			complaintMaintenanceStatusFB_p.setStrEmailId(strEmailId);

			strWarrantyDetailsTable = getWarrantyDetailsTable(
					warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(
					hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");

			complaintMaintenanceStatusFB_p
					.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);

		} catch (Exception e) {
			strErrMsg = "ComplaintMaintenanceStatusDATA.initializeReminder(complaintMaintenanceStatusFB_p,request_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To Save Reminder Data
	 * 
	 * @param complaintMaintenanceStatusFB_p the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void saveReminder(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		String strErrMsg;

		BmedTransBO bmedTransBO = null;

		String strHospitalCode;
		String strSeatId;

		String strReqId;
		String strReqType;
		String strReminderById;
		String strReminderRemarks;

		UserVO userVo = null;
		HemtReminderDtlVO hemtReminderDtlVO = null;
		try {
			bmedTransBO = new BmedTransBO();
			hemtReminderDtlVO = new HemtReminderDtlVO();

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			strSeatId = userVo.getUserSeatId();

			strReqId = complaintMaintenanceStatusFB_p.getStrComplaintId();

			strReqType = complaintMaintenanceStatusFB_p.getStrComplaintType();
			strReminderById = complaintMaintenanceStatusFB_p.getStrEmpId();
			strReminderRemarks = complaintMaintenanceStatusFB_p
					.getStrReminderDetails();

			/* Setting value for Reminder VO Start */

			hemtReminderDtlVO.setStrMode("1");

			hemtReminderDtlVO.setStrReqId(strReqId);
			hemtReminderDtlVO.setStrHospitalCode(strHospitalCode);
			hemtReminderDtlVO.setStrReminderId("");
			hemtReminderDtlVO.setStrReqType(strReqType);
			hemtReminderDtlVO.setStrReminderById(strReminderById);
			hemtReminderDtlVO.setStrReminderRemarks(strReminderRemarks);
			hemtReminderDtlVO.setStrReminderSentDate("");// Sysdate
			hemtReminderDtlVO.setStrReminderReplyId("");// Default null
			hemtReminderDtlVO.setStrReplyRemarks("");// Default null
			hemtReminderDtlVO.setStrReminderReplyDate("");// Default null
			hemtReminderDtlVO.setStrReminderStatus("1");// 1 for Reminder sent
			hemtReminderDtlVO.setStrEntryDate("");// Sysdate
			hemtReminderDtlVO.setStrIsValid("1");
			hemtReminderDtlVO.setStrSeatId(strSeatId);

			// Calling BO
			bmedTransBO.saveReminder(hemtReminderDtlVO);

		} catch (Exception e)
		{
			e.printStackTrace();
			strErrMsg = "ComplaintMaintenanceStatusTransDATA.saveReminder --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	/**
	 * To initialize Reminder Reply Page
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 * 
	 */
	public static void initializeReminderReply(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		HisUtil hisutil = null;

		String strErrMsg;
		String strReqId;
		BmedTransBO bmedTransBO = null;
		String strHospitalCode;

		String strCtDate;
		String strComplaintId;
		String strComplaintDate;
		String strItemName;
		String strItemBatchNo;
		String strItemSerialNo;
		String strManufacturerSerialNo;
		String strComplaintDescription;
		String strContactNo = "---";
		String strReqType;
		String strDeptName;
		String strEmpId;
		String strEmailId = "---";
		String strNoOfReminders = "0";
		String strReminderByName = "---";
		String strReminderDetails = "---";
		String strIsHemDesk = null;

		String strWarrantyDetailsTable;
		String strMaintenanceContractDetailsTable;

		WarrantyDtlVO warrantyDtlVO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		HemtReminderDtlVO hemtReminderDtlVO = null;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;

		UserVO userVo = null;
		try {

			hisutil = new HisUtil("bmed", "ComplaintMaintenanceStatusDATA");

			bmedTransBO = new BmedTransBO();

			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			warrantyDtlVO = new WarrantyDtlVO();
			hemtReminderDtlVO = new HemtReminderDtlVO();
			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			hemtReminderDtlVO.setStrHospitalCode(strHospitalCode);

			strCtDate = hisutil.getDSDate("DD-MON-YYYY HH24:MI");

			/*
			 * Getting Request Data:
			 */
			strReqId = complaintMaintenanceStatusFB_p.getStrHiddenComplaintId();
			strIsHemDesk = complaintMaintenanceStatusFB_p.getStrIsHemDesk();

			/* This code block will be in action when called from HemDesk. */
			if (strReqId == null) {
				strReqId = request_p.getParameter("strHiddenComplaintId");
				strIsHemDesk = request_p.getParameter("strIsHemDesk");
			}

			complaintRequestDtlVO.setStrReqId(strReqId);
			hemtReminderDtlVO.setStrReqId(strReqId);

			// Calling BO
			bmedTransBO.initializeReminder(complaintRequestDtlVO,
					warrantyDtlVO, hemtItemMcDtlVO, hemtReminderDtlVO);

			strComplaintId = complaintRequestDtlVO.getStrReqId();
			strComplaintDate = complaintRequestDtlVO.getStrReqDate();
			strItemName = complaintRequestDtlVO.getStrItemName();
			strItemBatchNo = complaintRequestDtlVO.getStrBatchNo();
			strItemSerialNo = complaintRequestDtlVO.getStrSerialNo();
			strManufacturerSerialNo = complaintRequestDtlVO
					.getStrManufSerialNo();
			strComplaintDescription = complaintRequestDtlVO
					.getStrComplaintDes();
			strContactNo = complaintRequestDtlVO.getStrContactNo();
			strReqType = complaintRequestDtlVO.getStrReqType();
			strDeptName = complaintRequestDtlVO.getStrDeptName();
			strEmpId = complaintRequestDtlVO.getStrEmpId();

			if (hemtReminderDtlVO.getWrsData() != null
					&& hemtReminderDtlVO.getWrsData().size() > 0) {
				while (hemtReminderDtlVO.getWrsData().next()) {
					strNoOfReminders = hemtReminderDtlVO.getWrsData()
							.getString("NO_OF_REMINDERS");

					strReminderByName = hemtReminderDtlVO.getWrsData()
							.getString("REMINDER_BY_NAME");

					strReminderDetails = hemtReminderDtlVO.getWrsData()
							.getString("HEMSTR_REMINDER_REMARKS");

					strEmailId = hemtReminderDtlVO.getWrsData().getString(
							"EMAIL_ID");

					strContactNo = hemtReminderDtlVO.getWrsData().getString(
							"CONTACT_NO");

				}
			}

			complaintMaintenanceStatusFB_p.setStrComplaintId(strComplaintId);
			complaintMaintenanceStatusFB_p
					.setStrComplaintDate(strComplaintDate);
			complaintMaintenanceStatusFB_p.setStrItemName(strItemName);
			complaintMaintenanceStatusFB_p.setStrItemBatchNo(strItemBatchNo);
			complaintMaintenanceStatusFB_p.setStrItemSerialNo(strItemSerialNo);
			complaintMaintenanceStatusFB_p
					.setStrManufacturerSerialNo(strManufacturerSerialNo);
			complaintMaintenanceStatusFB_p
					.setStrComplaintDescription(strComplaintDescription);
			complaintMaintenanceStatusFB_p.setStrContactNo(strContactNo);
			complaintMaintenanceStatusFB_p.setStrComplaintType(strReqType);
			complaintMaintenanceStatusFB_p.setStrDeptName(strDeptName);
			complaintMaintenanceStatusFB_p.setStrEmpId(strEmpId);
			complaintMaintenanceStatusFB_p.setStrCtDate(strCtDate);
			complaintMaintenanceStatusFB_p
					.setStrNoOfReminders(strNoOfReminders);
			complaintMaintenanceStatusFB_p
					.setStrContactPerson(strReminderByName);
			complaintMaintenanceStatusFB_p
					.setStrReminderDetails(strReminderDetails);
			complaintMaintenanceStatusFB_p.setStrEmailId(strEmailId);

			strWarrantyDetailsTable = getWarrantyDetailsTable(
					warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(
					hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");

			complaintMaintenanceStatusFB_p
					.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			complaintMaintenanceStatusFB_p
					.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			complaintMaintenanceStatusFB_p.setStrIsHemDesk(strIsHemDesk);

		} catch (Exception e) {
			strErrMsg = "ComplaintMaintenanceStatusDATA.initializeReminderReply(complaintMaintenanceStatusFB_p,request_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To Save Reminder Reply Data
	 * 
	 * @param complaintMaintenanceStatusFB_p the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void saveReminderReply(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		String strErrMsg;

		BmedTransBO bmedTransBO = null;

		String strHospitalCode;

		String strReqId;
		String strReminderReplyId;
		String strReplyRemarks;

		UserVO userVo = null;
		HemtReminderDtlVO hemtReminderDtlVO = null;
		try {
			bmedTransBO = new BmedTransBO();
			hemtReminderDtlVO = new HemtReminderDtlVO();

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			strReqId = complaintMaintenanceStatusFB_p.getStrComplaintId();

			strReminderReplyId = complaintMaintenanceStatusFB_p.getStrEmpId();
			strReplyRemarks = complaintMaintenanceStatusFB_p
					.getStrReplyRemarks();

			/* Setting value for Reminder VO Start */

			hemtReminderDtlVO.setStrMode("2");

			hemtReminderDtlVO.setStrReqId(strReqId);
			hemtReminderDtlVO.setStrHospitalCode(strHospitalCode);
			hemtReminderDtlVO.setStrReminderReplyId(strReminderReplyId);// Default
																		// null
			hemtReminderDtlVO.setStrReplyRemarks(strReplyRemarks);// Default
																	// null
			hemtReminderDtlVO.setStrReminderReplyDate("");// Sysdate
			hemtReminderDtlVO.setStrReminderStatus("2");// 2 for Reminder reply

			// Calling BO
			bmedTransBO.saveReminder(hemtReminderDtlVO);

		} catch (Exception e) 
		{
			e.printStackTrace();
			strErrMsg = "ComplaintMaintenanceStatusTransDATA.saveReminderReply --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	/*
	 * This method is used to initialize the complaint close process.
	 */
	public static void initializeClose(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		String strErrMsg;
		BmedGlobalBO bmedGlobalBO = null;
		BmedTransBO bmedTransBO = null;
		String strVerifiedByOptions = null;
		String strHospitalCode = null;
		String strReqId = null;
		final String strWarrantyDetailsTable;
		final String strMaintenanceContractDetailsTable;
		final String strSchedulesDetailsTable;
		final String strAttenderDetailsTable;

		final String strComplaintId;
		final String strComplaintDate;
		final String strItemName;
		final String strItemBatchNo;
		final String strItemSerialNo;
		final String strManufacturerSerialNo;
		final String strComplaintDescription;
		final String strManufactureNameOptions;

		UserVO userVo = null;
		WarrantyDtlVO warrantyDtlVO = null;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
		ComplaintScheduleDtlVO complaintScheduleDtlVO = null;
		ComplaintAttendDtlVO complaintAttendDtlVO = null;

		try {

			bmedGlobalBO = new BmedGlobalBO();
			bmedTransBO = new BmedTransBO();
			warrantyDtlVO = new WarrantyDtlVO();
			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			complaintScheduleDtlVO = new ComplaintScheduleDtlVO();
			complaintAttendDtlVO = new ComplaintAttendDtlVO();

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			complaintScheduleDtlVO.setStrHospitalCode(strHospitalCode);
			complaintAttendDtlVO.setStrHospitalCode(strHospitalCode);

			/*
			 * Getting Request Data:
			 */
			strReqId = complaintMaintenanceStatusFB_p.getStrHiddenComplaintId();

			complaintRequestDtlVO.setStrReqId(strReqId);
			complaintScheduleDtlVO.setStrReqId(strReqId);
			complaintAttendDtlVO.setStrReqId(strReqId);

			bmedTransBO.initializeComplaintActions(complaintRequestDtlVO,
							warrantyDtlVO, hemtItemMcDtlVO,
							complaintScheduleDtlVO, complaintAttendDtlVO, null,
							false, null, false, null, false);

			strComplaintId = complaintRequestDtlVO.getStrReqId();
			strComplaintDate = complaintRequestDtlVO.getStrReqDate();
			strItemName = complaintRequestDtlVO.getStrItemName();
			strItemBatchNo = complaintRequestDtlVO.getStrBatchNo();
			strItemSerialNo = complaintRequestDtlVO.getStrSerialNo();
			strManufacturerSerialNo = complaintRequestDtlVO.getStrManufSerialNo();
			strComplaintDescription = complaintRequestDtlVO.getStrComplaintDes();

			complaintMaintenanceStatusFB_p.setStrComplaintId(strComplaintId);
			complaintMaintenanceStatusFB_p.setStrComplaintDate(strComplaintDate);
			complaintMaintenanceStatusFB_p.setStrItemName(strItemName);
			complaintMaintenanceStatusFB_p.setStrItemBatchNo(strItemBatchNo);
			complaintMaintenanceStatusFB_p.setStrItemSerialNo(strItemSerialNo);
			complaintMaintenanceStatusFB_p.setStrManufacturerSerialNo(strManufacturerSerialNo);
			complaintMaintenanceStatusFB_p.setStrComplaintDescription(strComplaintDescription);

			strWarrantyDetailsTable = getWarrantyDetailsTable(warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");
			strSchedulesDetailsTable = getSchedulesDetailsTable(complaintScheduleDtlVO.getWrsData());
			strAttenderDetailsTable = getAttenderDetailsTable(complaintAttendDtlVO.getWrsData());

			strVerifiedByOptions = bmedGlobalBO.getEmployeeNameComboOptions(strHospitalCode);
			strManufactureNameOptions = bmedGlobalBO.getManufacturerNameComboOptions(strHospitalCode);

			complaintMaintenanceStatusFB_p.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			complaintMaintenanceStatusFB_p.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			complaintMaintenanceStatusFB_p.setStrSchedulesDetailsTable(strSchedulesDetailsTable);
			complaintMaintenanceStatusFB_p.setStrAttenderDetailsTable(strAttenderDetailsTable);

			complaintMaintenanceStatusFB_p.setStrVerifiedByOptions(strVerifiedByOptions);
			complaintMaintenanceStatusFB_p.setStrManufactureNameOptions(strManufactureNameOptions);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeCancel --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	/*
	 * This method is used to save close process.
	 */
	public static void saveClose(

	ComplaintRequestDtlVO complaintRequestDtlVO_p) throws Exception {
		BmedTransBO bmedTransBO = null;
		try {
			bmedTransBO = new BmedTransBO();
			bmedTransBO.saveComplaintCloseProcess(complaintRequestDtlVO_p);

		} catch (Exception e) {

			throw new Exception(
					"ComplaintMaintenanceStatusTransDATA.saveClose(ComplaintRequestDtlVO)-->"
							+ e.getMessage());
		}

	}

	public static String initializeGrievances(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		String strErrMsg;

		BmedTransBO bmedTransBO = null;
		BmedConfigUtil bmedConfigUtil;

		String strHospitalCode = null;
		String strReqId = null;
		String strChk = "";

		String strAmcCmc = "";

		UserVO userVo = null;

		ComplaintRequestDtlVO complaintRequestDtlVO = null;

		try {

			bmedTransBO = new BmedTransBO();

			complaintRequestDtlVO = new ComplaintRequestDtlVO();

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			/*
			 * Config Data
			 */
			bmedConfigUtil = new BmedConfigUtil();
			strAmcCmc = bmedConfigUtil
					.getStrAmcComplaint("14", strHospitalCode);

			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);

			/*
			 * Getting Request Data:
			 */
			strReqId = complaintMaintenanceStatusFB_p.getStrHiddenComplaintId();

			complaintRequestDtlVO.setStrReqId(strReqId);

			bmedTransBO.getComplaintRequestDtl(complaintRequestDtlVO);

			strChk = complaintRequestDtlVO.getStrReqId() + "@"
					+ complaintRequestDtlVO.getStrHospitalCode() + "@"
					+ strAmcCmc + "@" + complaintRequestDtlVO.getStrReqType()
					+ "$0";

			// System.out.println("Complaint Maintenance Status Data->strChk::"+strChk);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeCancel --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

		return strChk;

	}

	private static String getReminderPopupTable(String strDivID_p,
			String strRequestDateAndTime_p, HemtReminderDtlVO reminderDtlVO_p,
			StringBuffer strReminderURL_p) throws Exception {
		final StringBuffer sbReminderPopupTable = new StringBuffer(1000);

		boolean fReminderReplyExist = false;
		final String strRequestId;

		final WebRowSet webRowSet;
		final BmedTransBO bmedTransBO;

		String strReminderFrom;
		String strReminderDateTime;
		String strReminderSenderContactNo;
		String strReminderSenderEmailId;
		String strReminderDetails;

		String strReplyFrom;
		String strReplyDate;
		String strReminderReply;
		String strReminderReplyId;

		int nReminderCount = 0;

		strRequestId = reminderDtlVO_p.getStrReqId();

		bmedTransBO = new BmedTransBO();
		bmedTransBO.getComplaintReminderDtl(reminderDtlVO_p);
		webRowSet = reminderDtlVO_p.getWrsData();

		sbReminderPopupTable
				.append("<div id=\""
						+ strDivID_p
						+ "\" class=\"popup\" style=\"display: none;\">"
						+ "<table style=\"border-collapse: collapse;width:650px\">"
						+ "<!-- Popup Header -->"
						+ "<tr class=\"FOOTER_TR\">"
						+ "<td colspan=\"3\" style=\"text-align: left;\">Reminder Details</td>"
						+ "<td colspan=\"1\" style=\"text-align: right;\"><img alt=\"Close\" src=\"/AHIMS/hisglobal/images/stop.png\" onclick=\"hide_popup_menu('"+strDivID_p+"');\"></td>"
						+ "</tr>");

		sbReminderPopupTable
				.append("<!-- Complaint Info -->"
						+ "<tr>"
						+ "<td width=\"25%\" class=\"LABEL_TD\">Request / Complaint ID</td>"
						+ "<td width=\"25%\" class=\"CONTROL_TD\">"
						+ strRequestId
						+ "</td>"
						+ "<td width=\"25%\" class=\"LABEL_TD\">Request Date</td>"
						+ "<td width=\"25%\" class=\"CONTROL_TD\">"
						+ strRequestDateAndTime_p + "</td>" + "</tr>");

		if (webRowSet.size() > 0) {

			while (webRowSet.next()) {

				++nReminderCount;

				strReminderFrom = webRowSet.getString("REMINDER_FROM");
				strReminderDateTime = webRowSet.getString("REMINDER_DATETIME");
				strReminderSenderContactNo = webRowSet
						.getString("REMINDER_CONTACT");
				strReminderSenderEmailId = webRowSet
						.getString("REMINDER_EMAIL");
				strReminderDetails = webRowSet.getString("REMINDER_DETAILS");

				strReplyFrom = webRowSet.getString("REPLY_FROM");
				strReplyDate = webRowSet.getString("REPLY_DATETIME");
				strReminderReply = webRowSet.getString("REMINDER_REPLY");
				strReminderReplyId = webRowSet.getString("REMINDER_REPLY_ID");

				if (strReminderReplyId != null
						&& !strReminderReplyId.equals("")) {
					fReminderReplyExist = true;
				} else {
					fReminderReplyExist = false;
				}

				sbReminderPopupTable
						.append("<!-- Reminder "
								+ nReminderCount
								+ "-->"
								+ "<tr class=\"FOOTER_TR\">"
								+ "<td colspan=\"4\" style=\"text-align: left;\">Reminder "
								+ nReminderCount + "</td>" + "</tr>");

				sbReminderPopupTable.append("<tr>"
						+ "<td class=\"LABEL_TD\">From</td>"
						+ "<td class=\"CONTROL_TD\">" + strReminderFrom
						+ "</td>" + "<td class=\"LABEL_TD\">Date/Time</td>"
						+ "<td class=\"CONTROL_TD\">" + strReminderDateTime
						+ "</td>" + "</tr>");

				sbReminderPopupTable.append("<tr>"
						+ "<td class=\"LABEL_TD\">Contact No.</td>"
						+ "<td class=\"CONTROL_TD\">"
						+ strReminderSenderContactNo + "</td>"
						+ "<td class=\"LABEL_TD\">Email Id</td>"
						+ "<td class=\"CONTROL_TD\">"
						+ strReminderSenderEmailId + "</td>" + "</tr>");

				sbReminderPopupTable.append("<tr>"
						+ "<td class=\"LABEL_TD\">Reminder Details</td>"
						+ "<td class=\"CONTROL_TD\">" + strReminderDetails
						+ "</td>" + "<td class=\"LABEL_TD\"></td>"
						+ "<td class=\"CONTROL_TD\"></td>" + "</tr>");

				if (fReminderReplyExist) {
					sbReminderPopupTable.append("<!-- 1st reply -->");
					sbReminderPopupTable
							.append("<tr class=\"FOOTER_TR\">"
									+ "<td colspan=\"4\" style=\"text-align: left;\">Reply Details</td>"
									+ "</tr>");
					sbReminderPopupTable.append("<tr>"
							+ "<td class=\"LABEL_TD\">From</td>"
							+ "<td class=\"CONTROL_TD\">" + strReplyFrom
							+ "</td>" + "<td class=\"LABEL_TD\">Date/Time</td>"
							+ "<td class=\"CONTROL_TD\">" + strReplyDate
							+ "</td>" + "</tr>");
					sbReminderPopupTable.append("<tr>"
							+ "<td class=\"LABEL_TD\">Reply</td>"
							+ "<td class=\"CONTROL_TD\">" + strReminderReply
							+ "</td>" + "<td class=\"LABEL_TD\"></td>"
							+ "<td class=\"CONTROL_TD\"></td>" + "</tr>");
				}
			}

		} else {

			sbReminderPopupTable
					.append("<tr>"
							+ "<td class=\"LABEL_TD\" colspan=\"4\" style=\"color:red;text-align:center\">No Data Found!</td>"
							+ "</tr>");

		}

		sbReminderPopupTable
				.append("<tr>"
						+ "<td colspan=\"4\" style=\"text-align: center;\"><img class=\"button\" tabindex=\"1\" src=\"../../hisglobal/images/close_tab.png\" title=\"Close\" style=\"cursor: pointer\" onclick=\"hide_popup_menu('"+strDivID_p+"');\" onkeypress=\"if(event.keyCode==13) { hide_popup_menu('"+strDivID_p+"'); }\" /></td>"
						+ "</tr>" + "</table>" + "</div>");

		if (nReminderCount == 0) {
			strReminderURL_p
					.append("<a style=\"color:blue;cursor: pointer\" onclick=\"display_popup_menu(this.parentNode.parentNode, '"+strDivID_p+"', '', '');\">sent(0)</a>");
		} else {
			if (fReminderReplyExist) {
				strReminderURL_p
						.append("<a style=\"color:green;cursor: pointer\" onclick=\"display_popup_menu(this.parentNode.parentNode, '"+strDivID_p+"', '', '');\">sent("
								+ nReminderCount + ")</a>");
			} else {
				strReminderURL_p
						.append("<a style=\"color:blue;cursor: pointer\" onclick=\"display_popup_menu(this.parentNode.parentNode, '"+strDivID_p+"', '', '');\">sent("
								+ nReminderCount + ")</a>");
			}

		}

		return sbReminderPopupTable.toString();

	}

	
	/**
	 * To get Uploaded File
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 * @param request_p
	 * @param response_p
	 */
	public static void getUploadedFile(ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,HttpServletRequest request_p, HttpServletResponse response_p) 

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
							
						}
					/*******************************************************************/

					  String sessionFtpAddress= EMMSStaticConfigurator.bmedpath;// "10.0.5.152/ftpserver"; //populate from session
					 // String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
					  String Fileurl= "ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
					 
					 
					  URL                  urlftp = new URL(Fileurl+"/"+strFileName);
					  URLConnection          urlc =	urlftp.openConnection();
					  InputStream              io = urlc.getInputStream();
					  		  
					 
					        FileOutputStream fos = new FileOutputStream(strFileName);
					        byte[] buf = new byte[4096];
					        int read = 0;
					        while ((read = io.read(buf)) > 0) 
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
					//e.printStackTrace();
					strmsgText = "ComplaintMaintenanceStatusDATA.getUploadedFile --> "+ e.getMessage();
					HisException eObj = new HisException("bmed","ComplaintMaintenanceStatusDATA->getUploadedFile()", strmsgText);
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