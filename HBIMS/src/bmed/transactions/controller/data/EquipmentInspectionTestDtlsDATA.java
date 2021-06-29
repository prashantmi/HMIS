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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import bmed.EMMSStaticConfigurator;
import bmed.global.controller.util.BmedConfigUtil;
import bmed.transactions.bo.BmedGlobalBO;
import bmed.transactions.bo.BmedTransBO;
import bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB;
import bmed.transactions.controller.fb.EquipmentInspectionTestDtlsFB;
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
import bmed.vo.TestDtlVO;
import bmed.vo.TestParameterDtlVO;
import bmed.vo.WarrantyDtlVO;

public class EquipmentInspectionTestDtlsDATA {

	public static void initializeMain(
			ComplaintMaintenanceStatusFB fb,
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
			fb.setStrDeptOptions(strDepartmentComboOptions);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeMain --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	
	/**
	 * To Get Service  Eng Name
	 * 
	 * @param fb
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 */
	
	public static void getServiceEngName(
			EquipmentInspectionTestDtlsFB fb,
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
			
			
			strServiceEngComboOptions  = strServiceEngComboOptions + "<option title='Select Value' value='-1' >Other</option>";
			fb
					.setStrServiceEngNameCombo(strServiceEngComboOptions);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strServiceEngComboOptions);

		} catch (Exception e) {
			strMsgText = "ComplaintLogOfflineDATA.getServiceEngName(ComplaintMaintenanceStatusFB fb,HttpServletRequest request_p, HttpServletResponse response_p) --> "
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

			/*strComplaintRequestNonPreventive = getComplaintRequestData(
					complaintRequestDtlNonPreventiveVO.getWrsData(),
					"activeComplaintsTable", nNoOfRowsPerPage,
					hemtReminderDtlVO);
			strComplaintRequestPreventive = getComplaintRequestData(
					complaintRequestDtlPreventiveVO.getWrsData(),
					"activePreventiveMaintenanceTable", nNoOfRowsPerPage,
					hemtReminderDtlVO);*/
			strMsgStatus = "SUCCESS";
			/*strResponse = strMsgStatus + "####"
					+ strComplaintRequestNonPreventive + "^^^^"
					+ strComplaintRequestPreventive;*/
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

	/*private static String getComplaintRequestData(WebRowSet webRowSet_p,
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
		
	}*/

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
			ComplaintMaintenanceStatusFB fb,
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
			strReqId = fb.getStrHiddenComplaintId();

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

			fb.setStrComplaintId(strComplaintId);
			fb.setStrComplaintDate(strComplaintDate);
			fb.setStrItemName(strItemName);
			fb.setStrItemBatchNo(strItemBatchNo);
			fb.setStrItemSerialNo(strItemSerialNo);
			fb.setStrManufacturerSerialNo(strManufacturerSerialNo);
			fb.setStrComplaintDescription(strComplaintDescription);

			strWarrantyDetailsTable = getWarrantyDetailsTable(warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");
			strSchedulesDetailsTable = getSchedulesDetailsTable(complaintScheduleDtlVO.getWrsData());
			strAttenderDetailsTable = getAttenderDetailsTable(complaintAttendDtlVO.getWrsData());

			strCancelTypeComboOptions = bmedGlobalBO.getCancelTypeComboOptions(strHospitalCode);

			fb.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			fb.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			fb.setStrSchedulesDetailsTable(strSchedulesDetailsTable);
			fb.setStrAttenderDetailsTable(strAttenderDetailsTable);

			fb.setStrCancelTypeOptions(strCancelTypeComboOptions);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeCancel --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	/**
	 * Forward Control to Item Complaint Register
	 * 
	 * @param fb
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 * 
	 */
	public static void initializeItemComplaintRegister(
			EquipmentInspectionTestDtlsFB fb,
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
		String strTestNameComboOptions;
		final String strManufactureNameOptions;
	
		String strDesignation;
		String strHospitalCode;
		String strSeatId;
		String strDeptCode;
		UserVO userVO = null;
		BmedTransBO bmedTransBO = null;
		String strTestParaComboOptions;
		HisUtil hisutil =null;
		HemDeskVO hemVo = null;
		try {

			bmedGlobalBO = new BmedGlobalBO();
			bmedTransBO = new BmedTransBO();
			hemVo = new HemDeskVO();
			semtConfigPropertyMstVO = new SemtConfigPropertyMstVO();
			hisutil = new HisUtil("bmed", "ComplaintMaintenanceStatusDATA");
			String strCtDate = hisutil.getASDate("dd-MMM-yyyy");
			/*
			 * User Value
			 */
			userVO = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVO.getHospitalCode();
			strSeatId = userVO.getSeatId();

			strDeptCode = fb.getStrDeptCode();

			strDesignation = bmedTransBO.getDesignation(userVO.getUserEmpID(),
					strHospitalCode);

			fb.setStrEmpName(userVO.getUserName());
			fb.setStrEmpId(userVO.getUserEmpID());

			strDepartmentComboOptions = bmedGlobalBO.getDepartmentComboOptions(strHospitalCode, strDeptCode,strSeatId,2);
			fb
					.setStrDeptCombo(strDepartmentComboOptions);

			if (strDeptCode == null)
				strDeptCode = "0";

			strIsAttached = fb.getStrIsAttached();

			if (strIsAttached == null)
				strIsAttached = "0";

			if (strIsAttached.equals("1")) {
				strItemComboOptions = bmedTransBO
						.getItemBrandComboOptionsOnDepartment(strHospitalCode,
								strDeptCode);

				fb
						.setStrItemNameCombo(strItemComboOptions);

			} else {
				strStoreComboOptions = bmedGlobalBO.getStoreComboOptions(
						strHospitalCode, strSeatId, strDeptCode);

				fb
						.setStrStoreNameCombo(strStoreComboOptions);

				strStoreId = fb.getStrStoreId() == null ? "0"
						: fb.getStrStoreId();
				ItemBrandMstVO vo = new ItemBrandMstVO();
				vo.setStrMode("13");
				strItemComboOptions = bmedGlobalBO
						.getItemBrandComboOptionsOnStore(strHospitalCode,
								strStoreId,vo);

				fb
						.setStrItemNameCombo(strItemComboOptions);
			}

			strEnggItemTypeComboOptions = bmedGlobalBO
					.getItemTypeComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			fb
					.setStrEngineeringItemTypeCmb(strEnggItemTypeComboOptions);

			semtConfigPropertyMstVO.setStrHospitalCode(Config.SUPER_USER_HOSPITAL_CODE);

			strTestNameComboOptions = bmedGlobalBO.getTestComboOptions(strHospitalCode);
			fb.setStrTestNameCombo(strTestNameComboOptions);
			strManufactureNameOptions = bmedGlobalBO
			.getManufacturerNameComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			fb.setStrManufactureNameOptions(strManufactureNameOptions);
			
			strTestParaComboOptions = bmedGlobalBO.getTestParaMeterComboOptions(strHospitalCode);
			
			
			fb.setStrTestParaNameCombo(strTestParaComboOptions);
			
			// Getting the Property value from semt_config_property_mst where
			// property Id=14

			semtConfigPropertyMstVO.setStrMode("2");
			semtConfigPropertyMstVO.setStrConfigPropertyId("14");
			bmedTransBO.getPropertyValue(semtConfigPropertyMstVO);
			fb
					.setStrConfigPropertyValue(semtConfigPropertyMstVO
							.getStrPropertyValue());

			fb.setStrIsItemInWorkingCondition("0");

			if (fb.getStrComplaintType() == null) {
				fb
						.setStrComplaintType(fb
								.getStrPageFlag().equals("ITEM") ? "1" : "2");
			}

			fb.setStrDesignation(strDesignation);
			
			fb.setStrTestDate(strCtDate);
			
			// Confirmed By Combo
			hemVo.setStrHospCode(userVO.getHospitalCode());
			hemVo.setStrEnggItemSubTypeId("0");
			hemVo.setStrEnggItemTypeId("0");
			hemVo.setStrMode("6");
			String strServiceEngComboOptions = bmedGlobalBO.getServiceEnggNameCombo(hemVo);
			strServiceEngComboOptions  = strServiceEngComboOptions + "<option title='Select Value' value='-1' >Other</option>";
			
			fb.setStrServiceEngNameCombo(strServiceEngComboOptions);

		} catch (Exception e) {
			e.printStackTrace();
			strMsgText = "ComplaintMaintenanceStatusDATA.initializeItemComplaintRegister --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"bmed",
					"ComplaintMaintenanceStatusDATA.initializeItemComplaintRegister()",
					strMsgText);
			fb
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
	 * @param fb
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 */
	public static void getStoreName(
			EquipmentInspectionTestDtlsFB fb,
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
			fb
					.setStrStoreNameCombo(strStoreComboOptions);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strStoreComboOptions);

		} catch (Exception e) {
			strMsgText = "ComplaintMaintenanceStatusDATA.getStoreName(ComplaintMaintenanceStatusFB fb,HttpServletRequest request_p, HttpServletResponse response_p) --> "
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
	 * Get Engg Item Sub Type Combo using Ajax.
	 * 
	 * @param fb
	 *            the form
	 * @param request_p
	 *            the request
	 * @param response_p
	 *            the response
	 * 
	 * @return the null
	 */
	public static void getEnggItemSubTypeCmb(
			EquipmentInspectionTestDtlsFB fb,
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
	 * @param fb the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getItemName(EquipmentInspectionTestDtlsFB formBean,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		String strMsgText;

		BmedGlobalBO bmedGlobalBO = null;
		BmedTransBO bmedTransBO = null;
		ItemBrandMstVO vo =null;
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
	 * @param fb the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getNonItemName(EquipmentInspectionTestDtlsFB formBean,
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
	 * @param fb the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getStockDetails(
			EquipmentInspectionTestDtlsFB fb,
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
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To Save Data
	 * 
	 * @param fb the EquipmentInspectionTestDtlsFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void saveData(
			EquipmentInspectionTestDtlsFB fb,
			HttpServletRequest request_p) {
		String strErrMsg;

		String strSeatId_p;
		String strHospitalCode_p;
		UserVO userVo = null;
		HisUtil hisutil = null;
		String strPageFlag;

		List<TestParameterDtlVO> listTestParameterDtlVO;
		
		TestDtlVO testDtlsVO;
		TestParameterDtlVO testParameterVO;
		WarrantyDtlVO warrantyDtlVO;
		String strStockInf="";
		try {
			hisutil = new HisUtil("bmed", "ComplaintMaintenanceStatusDATA");

			String strCtDate = hisutil.getASDate("dd-MMM-yyyy");
			strPageFlag = fb.getStrPageFlag();
				
			
						
			strStockInf = (String) request_p.getParameter("strStockInf");

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode_p = userVo.getHospitalCode();
			strSeatId_p = userVo.getSeatId();

			fb.setStrSeatId(strSeatId_p);
			fb
					.setStrHospitalCode(strHospitalCode_p);
			fb
					.setStrIsValid(Config.IS_VALID_ACTIVE);
			fb.setStrCtDate(strCtDate);

			if (fb.getStrIsAttached() == null) {
				fb.setStrIsAttached("0");
			}

			if (fb.getStrComplaintType() == null) {
				fb
						.setStrComplaintType(fb
								.getStrPageFlag().equals("ITEM") ? "1" : "2");
			}

			/*
			 * private String strStockInf;
			 * //HSTNUM_STORE_ID||'^'||HSTNUM_ITEM_ID||'^'||HSTNUM_ITEMBRAND_ID
			 * //||'^'||HSTSTR_BATCH_NO||'^'||HSTSTR_ITEM_SL_NO||'^'||
			 * //GNUM_HOSPITAL_CODE||'^'||HSTNUM_STOCK_STATUS_CODE
			 */

			// HEMT_COMPLAINT_REQUEST_DTL
			
			
			/* arun data */
			
			testDtlsVO=new TestDtlVO();
			
			testDtlsVO.setStrEquTestId("0");			// dummy
			testDtlsVO.setStrTestId(fb.getStrTestId());
			testDtlsVO.setStrDeptCodeNew(fb.getStrDeptCodeNew());
			testDtlsVO.setStrStoreId(fb.getStrStoreId());
			testDtlsVO.setStrEnggItemTypeId(fb.getStrEngineeringItemTypeId());
			testDtlsVO.setStrEnggItemSubTypeId(fb.getStrEngineeringItemSubTypeId());
			testDtlsVO.setStrItemId(fb.getStrItemId());
			testDtlsVO.setStrItemSerialNo(strStockInf.split("\\^")[4]);
			testDtlsVO.setStrItemBatchNo(strStockInf.split("\\^")[3]);
			testDtlsVO.setStrWarrantySlNo("0"); // dummy
			testDtlsVO.setStrManufacturerSerialNo(fb.getStrManufacturerSerialNo());
			testDtlsVO.setStrMaintenanceContractSlNo(fb.getStrManufacturerSerialNo());
			testDtlsVO.setStrIsInternal(fb.getStrItemOrNonItemMode());
			testDtlsVO.setStrContactPersonName(fb.getStrOtherName());
			testDtlsVO.setStrTestDate(fb.getStrTestDate());
			testDtlsVO.setStrResult(fb.getStrResult());
			testDtlsVO.setStrRemarks(fb.getStrRemarks());
			testDtlsVO.setStrHospitalCode(strHospitalCode_p);
			testDtlsVO.setStrIsValid("1");
			testDtlsVO.setStrEntryDate(strCtDate);
			testDtlsVO.setStrSeatId(strSeatId_p);
			testDtlsVO.setStrTestDate(fb.getStrTestDate());
			testDtlsVO.setStrTestTime(request_p.getParameter("strAttendTime"));
			if(fb.getStrItemOrNonItemMode().equals("1")){
				testDtlsVO.setStrConfirmedBy(fb.getStrVendorServiceEngName());
				if(fb.getStrVendorServiceEngName().equals("-1"))
					testDtlsVO.setStrConfirmedBy(fb.getStrOtherName());
			}
			else
				testDtlsVO.setStrConfirmedBy(fb.getStrSpareManufacturerId());
			
		    listTestParameterDtlVO = new ArrayList<TestParameterDtlVO>();
			for(int i=0;i<fb.getStrTestParaId().length;i++){
				
				testParameterVO=new TestParameterDtlVO(); 
				testParameterVO.setStrTestParaEquId("0"); // dummy
				testParameterVO.setStrTestId(fb.getStrTestId());
				testParameterVO.setStrTestParaId(fb.getStrTestParaId()[i]);
				testParameterVO.setStrOutPut(fb.getStroutPut()[i]);
				testParameterVO.setStrHospitalCode(strHospitalCode_p);
				testParameterVO.setStrIsValid("1");
				testParameterVO.setStrSeatId(strSeatId_p);
				testParameterVO.setStrEntryDate(strCtDate);
				testParameterVO.setStrMode("1");
				listTestParameterDtlVO.add(testParameterVO);
				
			}
			
			
			/*arun data end */
			
					
			testDtlsVO.setStrMode("1");

						
			testDtlsVO.setTestParameterDtlVO(listTestParameterDtlVO);
			
			String[] strTemp;
			
			if(fb.getStrWarrantyOrMaintenanceSlNoAndType()!=null)
			{
				strTemp = fb.getStrWarrantyOrMaintenanceSlNoAndType().split("\\^");
				
				if (strTemp[1].equals("WARRANTY")) {
					
					testDtlsVO.setStrWarrantySlNo(strTemp[0]);
					
					testDtlsVO.setStrMaintenanceContractSlNo("");	
				}

				else if (strTemp[1].equals("MAINTENANCE")) 
				{
					testDtlsVO.setStrMaintenanceContractSlNo(strTemp[0]);
					testDtlsVO.setStrWarrantySlNo("");
				}
			}			
			else
			{
				testDtlsVO.setStrMaintenanceContractSlNo("");
				testDtlsVO.setStrWarrantySlNo("");
			}

			
			/* hstnum_item_id = p_hstnum_item_id
            AND hstnum_itembrand_id = p_hstnum_itembrand_id
            AND hststr_batch_sl_no LIKE p_hststr_batch_sl_no
            AND hstnum_item_sl_no LIKE p_hstnum_item_sl_no
            AND hstnum_sl_no = p_hstnum_sl_no
            AND gnum_hospital_code = p_gnum_hospital_code;
			 * 
			 * 
			 * 
			 * */
			
			warrantyDtlVO =new WarrantyDtlVO();
			warrantyDtlVO.setStrItemId(strStockInf.split("\\^")[1]);
			warrantyDtlVO.setStrItemBrandId(strStockInf.split("\\^")[2]);
			warrantyDtlVO.setStrHospitalCode(strStockInf.split("\\^")[5]);
			warrantyDtlVO.setStrBatchSlNo(strStockInf.split("\\^")[3]);// Dummy
																		// NA
			warrantyDtlVO.setStrItemSlNo(strStockInf.split("\\^")[4]);
			warrantyDtlVO.setStrIsExtended("0");
			warrantyDtlVO.setStrMode("4");  // this is just of updating hstt_warranty_dtl.hemnum_is_extended =0;
					
			

			BmedTransBO.saveDataForEquipmentInspectionTestDetails(testDtlsVO,warrantyDtlVO);
			fb.setStrNormalMsg("Data Saved Successfully ! ");

		} catch (Exception e) 
		{
			e.printStackTrace();
			strErrMsg = "ComplaintMaintenanceStatusDATA.saveData() --> "+ e.getMessage();
			HisException eObj = new HisException("BMED","ComplaintMaintenanceStatusDATA", strErrMsg);
			fb.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID()	+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To getPreviousComplaintDetails Data
	 * 
	 * @param fb the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getPreviousComplaintDetails(
			EquipmentInspectionTestDtlsFB fb,
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

			e.printStackTrace();
			strErrMsg = "ItemMaintContractDtlsTransDATA.getPreviousComplaintDetails() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ItemMaintContractDtlsDATA", strErrMsg);
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To get Warranty Details Data
	 * 
	 * @param fb the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getWarrantyDetails(
			EquipmentInspectionTestDtlsFB fb,
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
				fb
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
				fb
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
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	/*
	 * To get Maintenance Contract Details Data
	 * 
	 * @param fb the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 */
	public static void getMaintenanceContractDetails(
			EquipmentInspectionTestDtlsFB fb,
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
				hemtItemMcDtlVO.setStrItemId(strStockInf.split("\\^")[1]);
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
				 * fb
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
			fb
					.setStrWarrantyDetailsTable(strMaintenanceContractDetailsTable);

			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strMaintenanceContractDetailsTable);

		} catch (Exception e) {

			strErrMsg = "ItemMaintContractDtlsTransDATA.getPreviousComplaintDetails() --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ItemMaintContractDtlsDATA", strErrMsg);
			fb
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
					if(!strUploadNo.equals("0"))
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
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><a style='cursor:pointer;color:blue;font-size:12px;' "
						+ "onClick=onUploadedFileName(this,"+index+",'"+strDocRefNo+"');>"
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
				String ExpiryDateflg = wrsData_p.getString("ExpiryDateflg");


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
					if(!strUploadNo.equals("0"))
						strExten=strDocRefNo.split("\\.")[1];
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
				if(ExpiryDateflg.equalsIgnoreCase("0"))
				{
					if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
						sbBody
								.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><input type=\"radio\" name=\"strWarrantyOrMaintenanceSlNoAndType\" id=\"radWaranty\"  value=\""
										+ strWarrantySlNo + "^WARRANTY\" disabled/></td>");
					}	
				}else
				{
					if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
						sbBody
								.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><input type=\"radio\" name=\"strWarrantyOrMaintenanceSlNoAndType\" id=\"radWaranty\"  value=\""
										+ strWarrantySlNo + "^WARRANTY\"/></td>");
					}
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
						+ "onClick=onUploadedFileName(this,"+index+",'"+strDocRefNo+"');>"
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
			ComplaintMaintenanceStatusFB fb,
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
			fb.setStrPath(strPath);

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
			strReqId = fb.getStrHiddenComplaintId();
			strIsHemDesk = fb.getStrIsHemDesk();
			if (strReqId == null) {
				strReqId = request_p.getParameter("strComplaintId");
				strIsHemDesk = request_p.getParameter("strIsHemDesk");
				fb.setStrIsHemDesk(strIsHemDesk);

			}

			if (strIsHemDesk == null) {
				strIsHemDesk = "0";
				fb.setStrIsHemDesk(strIsHemDesk);
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

			fb.setStrIsHemDesk(strIsHemDesk);

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

			fb.setStrComplaintId(strComplaintId);
			fb
					.setStrComplaintDate(strComplaintDate);
			fb.setStrItemName(strItemName);
			fb.setStrItemBatchNo(strItemBatchNo);
			fb.setStrItemSerialNo(strItemSerialNo);
			fb
					.setStrManufacturerSerialNo(strManufacturerSerialNo);
			fb
					.setStrComplaintDescription(strComplaintDescription);
			fb
					.setStrComplaintType(strComplaintType);
			fb.setStrVendorId(strVendorId);
			fb
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

			fb
					.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			fb
					.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			fb
					.setStrSchedulesDetailsTable(strSchedulesDetailsTable);
			fb
					.setStrAttenderDetailsTable(strAttenderDetailsTable);
			fb
					.setStrServiceEngineerDetailsTable(strServiceEngineerDetailsTable);

			fb
					.setStrExpectedVisitUnitOptions(strExpectedVisitUnitComboOptions);

			fb
					.setStrWarrantyAndMaintenanceContractDivDisplay(strWarrantyAndMaintenanceContractDivDisplay);
			fb
					.setStrExternalServiceProvidercheckBoxDisplay(strExternalServiceProvidercheckBoxDisplay);
			fb
					.setStrExternalServiceProvidercheckBoxChecked(strExternalServiceProvidercheckBoxChecked);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeSchedule --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			fb
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
			ComplaintMaintenanceStatusFB fb,
			HttpServletRequest request_p) {
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

			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			complaintScheduleDtlVO.setStrHospitalCode(strHospitalCode);
			complaintAttendDtlVO.setStrHospitalCode(strHospitalCode);
			itemSparePartDtlVO.setStrHospitalCode(strHospitalCode);
			taskMstVO.setStrHospitalCode(strHospitalCode);

			/*
			 * Getting Request Data:
			 */
			strReqId = fb.getStrHiddenComplaintId();

			complaintRequestDtlVO.setStrReqId(strReqId);
			complaintScheduleDtlVO.setStrReqId(strReqId);
			complaintAttendDtlVO.setStrReqId(strReqId);

			bmedTransBO.initializeComplaintActions(complaintRequestDtlVO,
					warrantyDtlVO, hemtItemMcDtlVO, complaintScheduleDtlVO,
					complaintAttendDtlVO, null, false, itemSparePartDtlVO,
					true, taskMstVO, true);

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

			fb.setStrComplaintId(strComplaintId);
			fb
					.setStrComplaintDate(strComplaintDate);
			fb.setStrItemName(strItemName);
			fb.setStrItemBatchNo(strItemBatchNo);
			fb.setStrItemSerialNo(strItemSerialNo);
			fb
					.setStrManufacturerSerialNo(strManufacturerSerialNo);
			fb
					.setStrComplaintDescription(strComplaintDescription);
			fb.setStrMainteId(strMainteId);
			fb.setStrVendorId(strVendorId);
			fb.setStrStoreId(strStoreId);
			fb.setStrItemId(strItemId);
			fb.setStrSerialNo(strSerialNo);

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
			strTaskTable = getTaskTableRow(taskMstVO.getWrsData());

			strSparePartStatusOptions = bmedGlobalBO.getSparePartStatusOptions(
					strHospitalCode, "0");
			strWarrantyUptoUnitOptions = BmedGlobalBO
					.getUnitComboOptions(strHospitalCode);
			strSparePartNameOptions = bmedGlobalBO.getAllSparePartOptions(
					strHospitalCode, "0");
			strManufactureNameOptions = bmedGlobalBO
					.getManufacturerNameComboOptions(strHospitalCode);

			fb
					.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			fb
					.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			fb
					.setStrSchedulesDetailsTable(strSchedulesDetailsTable);
			fb
					.setStrAttenderDetailsTable(strAttenderDetailsTable);
			fb
					.setStrSparePartMaintenaceStatusTableRow(strSparePartMaintenaceStatusTableRow);

			fb
					.setStrSparePartStatusOptions(strSparePartStatusOptions);
			fb
					.setStrWarrantyUptoUnitOptions(strWarrantyUptoUnitOptions);
			fb
					.setStrSparePartNameOptions(strSparePartNameOptions);
			fb
					.setStrManufactureNameOptions(strManufactureNameOptions);
			fb
					.setStrSparePartDetailsTable(strSparePartDetailsTable);
			fb.setStrTaskTable(strTaskTable);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeCancel --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			fb
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
	 * @param fb
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 * 
	 */
	public static void initializeComplaintDetailsView(
			ComplaintMaintenanceStatusFB fb,
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
			strReqId = fb.getStrComplaintId();

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

			fb
					.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			fb
					.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			fb
					.setStrApprovalDetailsTable(strApprovalDetailsTable);
			fb
					.setStrSchedulesDetailsTable(strSchedulesDetailsTable);
			fb
					.setStrAttenderDetailsTable(strAttenderDetailsTable);
			fb
					.setStrRemindersDetailsTable(strRemindersDetailsTable);
			fb
					.setStrGrievancesDetailsTable(strGrievancesDetailsTable);
			fb
					.setStrSparePartDetailsTable(strSparePartDetailsTable);
			fb
					.setStrCloseDetailsTable(strCloseDetailsTable);

		} catch (Exception e) {
			strErrMsg = "ComplaintMaintenanceStatusDATA.initializeComplaintDetailsView(fb,request_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			fb
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
	 * @param fb
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 * 
	 */
	/*public static void initializeReminder(
			ComplaintMaintenanceStatusFB fb,
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
			
			 * User Value
			 
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			hemtReminderDtlVO.setStrHospitalCode(strHospitalCode);

			strCtDate = hisutil.getDSDate("DD-MON-YYYY HH24:MI");

			
			 * Getting Request Data:
			 
			strReqId = fb.getStrHiddenComplaintId();

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

			fb.setStrComplaintId(strComplaintId);
			fb
					.setStrComplaintDate(strComplaintDate);
			fb.setStrItemName(strItemName);
			fb.setStrItemBatchNo(strItemBatchNo);
			fb.setStrItemSerialNo(strItemSerialNo);
			fb
					.setStrManufacturerSerialNo(strManufacturerSerialNo);
			fb
					.setStrComplaintDescription(strComplaintDescription);
			fb.setStrContactNo(strContactNo);
			fb.setStrComplaintType(strReqType);
			fb.setStrDeptName(strDeptName);
			fb.setStrEmpId(strEmpId);
			fb.setStrCtDate(strCtDate);
			fb
					.setStrNoOfReminders(strNoOfReminders);
			fb
					.setStrContactPerson(strReminderByName);
			fb.setStrEmailId(strEmailId);

			strWarrantyDetailsTable = getWarrantyDetailsTable(
					warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(
					hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");

			fb
					.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			fb
					.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);

		} catch (Exception e) {
			strErrMsg = "ComplaintMaintenanceStatusDATA.initializeReminder(fb,request_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	
	 * To Save Reminder Data
	 * 
	 * @param fb the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 
	public static void saveReminder(
			ComplaintMaintenanceStatusFB fb,
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

			
			 * User Value
			 
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			strSeatId = userVo.getUserSeatId();

			strReqId = fb.getStrComplaintId();

			strReqType = fb.getStrComplaintType();
			strReminderById = fb.getStrEmpId();
			strReminderRemarks = fb
					.getStrReminderDetails();

			 Setting value for Reminder VO Start 

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
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	*//**
	 * To initialize Reminder Reply Page
	 * 
	 * @param fb
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the HttpServletRequest
	 * 
	 *//*
	public static void initializeReminderReply(
			ComplaintMaintenanceStatusFB fb,
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
			
			 * User Value
			 
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			hemtReminderDtlVO.setStrHospitalCode(strHospitalCode);

			strCtDate = hisutil.getDSDate("DD-MON-YYYY HH24:MI");

			
			 * Getting Request Data:
			 
			strReqId = fb.getStrHiddenComplaintId();
			strIsHemDesk = fb.getStrIsHemDesk();

			 This code block will be in action when called from HemDesk. 
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

			fb.setStrComplaintId(strComplaintId);
			fb
					.setStrComplaintDate(strComplaintDate);
			fb.setStrItemName(strItemName);
			fb.setStrItemBatchNo(strItemBatchNo);
			fb.setStrItemSerialNo(strItemSerialNo);
			fb
					.setStrManufacturerSerialNo(strManufacturerSerialNo);
			fb
					.setStrComplaintDescription(strComplaintDescription);
			fb.setStrContactNo(strContactNo);
			fb.setStrComplaintType(strReqType);
			fb.setStrDeptName(strDeptName);
			fb.setStrEmpId(strEmpId);
			fb.setStrCtDate(strCtDate);
			fb
					.setStrNoOfReminders(strNoOfReminders);
			fb
					.setStrContactPerson(strReminderByName);
			fb
					.setStrReminderDetails(strReminderDetails);
			fb.setStrEmailId(strEmailId);

			strWarrantyDetailsTable = getWarrantyDetailsTable(
					warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(
					hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");

			fb
					.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			fb
					.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			fb.setStrIsHemDesk(strIsHemDesk);

		} catch (Exception e) {
			strErrMsg = "ComplaintMaintenanceStatusDATA.initializeReminderReply(fb,request_p) --> "
					+ e.getMessage();
			HisException eObj = new HisException("BMED",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}
	}

	
	 * To Save Reminder Reply Data
	 * 
	 * @param fb the ComplaintMaintenanceStatusFB
	 * 
	 * @param request_p the HttpServletRequest
	 
	public static void saveReminderReply(
			ComplaintMaintenanceStatusFB fb,
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

			
			 * User Value
			 
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			strReqId = fb.getStrComplaintId();

			strReminderReplyId = fb.getStrEmpId();
			strReplyRemarks = fb
					.getStrReplyRemarks();

			 Setting value for Reminder VO Start 

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
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	
	 * This method is used to initialize the complaint close process.
	 
	public static void initializeClose(
			ComplaintMaintenanceStatusFB fb,
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

			
			 * User Value
			 
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();

			warrantyDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemMcDtlVO.setStrHospCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			complaintScheduleDtlVO.setStrHospitalCode(strHospitalCode);
			complaintAttendDtlVO.setStrHospitalCode(strHospitalCode);

			
			 * Getting Request Data:
			 
			strReqId = fb.getStrHiddenComplaintId();

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

			fb.setStrComplaintId(strComplaintId);
			fb.setStrComplaintDate(strComplaintDate);
			fb.setStrItemName(strItemName);
			fb.setStrItemBatchNo(strItemBatchNo);
			fb.setStrItemSerialNo(strItemSerialNo);
			fb.setStrManufacturerSerialNo(strManufacturerSerialNo);
			fb.setStrComplaintDescription(strComplaintDescription);

			strWarrantyDetailsTable = getWarrantyDetailsTable(warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
			strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON");
			strSchedulesDetailsTable = getSchedulesDetailsTable(complaintScheduleDtlVO.getWrsData());
			strAttenderDetailsTable = getAttenderDetailsTable(complaintAttendDtlVO.getWrsData());

			strVerifiedByOptions = bmedGlobalBO.getEmployeeNameComboOptions(strHospitalCode);
			strManufactureNameOptions = bmedGlobalBO.getManufacturerNameComboOptions(strHospitalCode);

			fb.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
			fb.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
			fb.setStrSchedulesDetailsTable(strSchedulesDetailsTable);
			fb.setStrAttenderDetailsTable(strAttenderDetailsTable);

			fb.setStrVerifiedByOptions(strVerifiedByOptions);
			fb.setStrManufactureNameOptions(strManufactureNameOptions);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusTransDATA.initializeCancel --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusDATA", strErrMsg);
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}*/

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
			ComplaintMaintenanceStatusFB fb,
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
			strReqId = fb.getStrHiddenComplaintId();

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
			fb
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

		return strChk;

	}
	
	
	public static void getUploadedFile(
			EquipmentInspectionTestDtlsFB fb,
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
			  // strFtpFolderName = bmed.getStrFtpFileFolder("15", userVo.getHospitalCode());
		       
			  
			   
			   if(strFtpFolderName.equals("")||strFtpFolderName==null)
			   {
				    strFtpFolderName = "bmedDOCS";
			   } 
			
			
			strFileName = fb.getStrUploadFileId();
						
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
				
			}			/*******************************************************************/

			   fis = new FileInputStream( new File(HisUtil.getParameterFromHisPathXML("TEMP_PATH")+"/"+strFileName));   
			//  String sessionFtpAddress=EMMSStaticConfigurator.bmedpath; //populate from session 10.0.5.152/ftpserver
			  //String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
			/*  String Fileurl= "ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
			 
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
			strmsgText = "mms.master.PreTechApprovalTransDATA.getUploadedFile --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms","PreTechApprovalTransDATA->getUploadedFile()", strmsgText);
			fb.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
			eObj = null;
		} finally {
	
			if (f != null)
				f = null;
			if (fis != null)
				fis = null;
		}
	}

	
			}
		    
	
