package bmed.transactions.controller.util;

import java.io.IOException;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bmed.transactions.controller.data.ComplaintLogOfflineDATA;
import bmed.transactions.controller.data.ComplaintMaintenanceStatusDATA;
import bmed.transactions.controller.data.EquipmentInspectionTestDtlsDATA;
import bmed.transactions.controller.fb.ComplaintLogOfflineFB;
import bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB;
import bmed.transactions.controller.fb.EquipmentInspectionTestDtlsFB;

import bmed.vo.ComplaintAttendDtlVO;
import bmed.vo.ComplaintRequestDtlVO;
import bmed.vo.HemtComplaintStatusDtlVO;
import bmed.vo.HemtItemSparePartDtlVO;
import bmed.vo.ServiceEnggMstVO;

public class EquipmentInspectionTestDtlsUTIL {

	public static void initializeMain(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		ComplaintMaintenanceStatusDATA.initializeMain(
				complaintMaintenanceStatusFB_p, request_p);

	}

	public static void getAjaxComplaintRequestData(
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ComplaintMaintenanceStatusDATA.getAjaxComplaintRequestData(request_p,
				response_p);

	}

	public static void initializeItemComplaintRegister(
			EquipmentInspectionTestDtlsFB complaintLogOfflineFB_p,
			HttpServletRequest request_p) {
		EquipmentInspectionTestDtlsDATA.initializeItemComplaintRegister(
				complaintLogOfflineFB_p, request_p);

	}

	public static void getStoreName(
			EquipmentInspectionTestDtlsFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		EquipmentInspectionTestDtlsDATA.getStoreName(
				complaintMaintenanceStatusFB_p, request_p, response_p);

	}
	
	public static void getVendorDetails(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ComplaintLogOfflineDATA.getVendorDetials(
				complaintMaintenanceStatusFB_p, request_p, response_p);

	}
	
	
	public static void getVendorCombo(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ComplaintLogOfflineDATA.getVendorCombo(
				complaintMaintenanceStatusFB_p, request_p, response_p);

	}
	
	public static void getEmpCombo(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ComplaintLogOfflineDATA.getEmpName(
				complaintMaintenanceStatusFB_p, request_p, response_p);

	}
	
	public static void getEmpDesignationDetails(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		ComplaintLogOfflineDATA.getEmpDesignationDetails(
				complaintMaintenanceStatusFB_p, request_p, response_p);

	}
	
	
	public static void getServiceEngName(
			EquipmentInspectionTestDtlsFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		EquipmentInspectionTestDtlsDATA.getServiceEngName(
				complaintMaintenanceStatusFB_p, request_p, response_p);
	}

	
	

	public static void getEnggItemSubTypeCmb(
			EquipmentInspectionTestDtlsFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		EquipmentInspectionTestDtlsDATA.getEnggItemSubTypeCmb(
				complaintMaintenanceStatusFB_p, request_p, response_p);

	}

	public static void getItemName(EquipmentInspectionTestDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		EquipmentInspectionTestDtlsDATA.getItemName(formBean_p, request_p,
				response_p);

	}

	public static void getNonItemName(EquipmentInspectionTestDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		EquipmentInspectionTestDtlsDATA.getNonItemName(formBean_p, request_p,
				response_p);

	}

	public static void getStockDetails(
			EquipmentInspectionTestDtlsFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		EquipmentInspectionTestDtlsDATA.getStockDetails(
				complaintMaintenanceStatusFB_p, request_p, response_p);

	}

	public static void getPreviousComplaintDetails(
			EquipmentInspectionTestDtlsFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		EquipmentInspectionTestDtlsDATA.getPreviousComplaintDetails(
				complaintMaintenanceStatusFB_p, request_p, response_p);

	}

	public static void getWarrantyDetails(
			EquipmentInspectionTestDtlsFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		EquipmentInspectionTestDtlsDATA.getWarrantyDetails(
				complaintMaintenanceStatusFB_p, request_p, response_p);

	}

	public static void getMaintenanceContractDetails(
			EquipmentInspectionTestDtlsFB formBean_p,
			HttpServletRequest request_p, HttpServletResponse response_p) {
		EquipmentInspectionTestDtlsDATA.getMaintenanceContractDetails(
				formBean_p, request_p, response_p);

	}

	public static void saveData(EquipmentInspectionTestDtlsFB formBean_p,
			HttpServletRequest request_p) {
		EquipmentInspectionTestDtlsDATA.saveData(formBean_p, request_p);

	}

	public static void initializeCancel(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		ComplaintMaintenanceStatusDATA.initializeCancel(
				complaintMaintenanceStatusFB_p, request_p);

	}

	public static void saveCancel(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		ComplaintMaintenanceStatusDATA.saveCancel(
				complaintMaintenanceStatusFB_p, request_p);

	}

	public static void initializeSchedule(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		ComplaintMaintenanceStatusDATA.initializeSchedule(
				complaintMaintenanceStatusFB_p, request_p);

	}

	public static void saveSchedule(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		ComplaintMaintenanceStatusDATA.saveSchedule(
				complaintMaintenanceStatusFB_p, request_p);

	}

	public static void getFilterValue(HttpServletRequest request_p,
			HttpServletResponse response) {

		/*
		 * variable declaration
		 */
		final UserVO userVo;
		final ServiceEnggMstVO serviceEnggMstVO;

		final String strHospitalCode;
		final String strSeatId;
		final String strEngineeringItemTypeId;
		final String strFilterType;
		final String filterValueOptions;

		final StringBuffer sbResponseStringBuffer = new StringBuffer(1000);
		;
		final String strResponseString;

		try {

			/* Getting Data From Request */
			strEngineeringItemTypeId = request_p
					.getParameter("strEngineeringItemTypeId");
			strFilterType = request_p.getParameter("strFilterType");

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			strSeatId = userVo.getUserSeatId();

			serviceEnggMstVO = new ServiceEnggMstVO();
			serviceEnggMstVO.setStrHospitalCode(strHospitalCode);
			serviceEnggMstVO.setStrSeatid(strSeatId);
			serviceEnggMstVO.setStrEnggItemTypeId(strEngineeringItemTypeId);

			filterValueOptions = ComplaintMaintenanceStatusDATA.getFilterValue(
					serviceEnggMstVO, strFilterType);

			sbResponseStringBuffer.append("SUCCESS");
			sbResponseStringBuffer.append("####");
			sbResponseStringBuffer.append(filterValueOptions);

		} catch (Exception _e) {

			HisException eObj = new HisException(
					"BMED",
					"ComplaintMaintenanceStatusUTIL->getFilterValue(HttpServletRequest,HttpServletResponse) -->",
					_e.getMessage());

			sbResponseStringBuffer.append("ERROR");
			sbResponseStringBuffer.append("####");
			sbResponseStringBuffer.append("Application Error [ERROR ID : ");
			sbResponseStringBuffer.append(eObj.getErrorID());
			sbResponseStringBuffer.append("], Contact System Administrator! ");

			eObj = null;
		} finally {

			strResponseString = sbResponseStringBuffer.toString();
			try {
				response.getWriter().write(strResponseString);
			} catch (IOException e) {

				new HisException(
						"BMED",
						"ComplaintMaintenanceStatusUTIL->getFilterValue(HttpServletRequest,HttpServletResponse)[finally block]",
						e.getMessage());
			}
		}

	}

	public static void getOtherServiceEngineerDetailsTable(
			HttpServletRequest request_p, HttpServletResponse response_p) {
		/*
		 * variable declaration
		 */
		final UserVO userVo;
		final ServiceEnggMstVO serviceEnggMstVO;

		final String strHospitalCode;
		final String strSeatId;
		final String strEngineeringItemTypeId;

		final String strFilterBy;
		final String strFilterValue;
		final String strOtherServiceEngineerDetailsTable;

		final StringBuffer sbResponseStringBuffer = new StringBuffer(1000);
		;
		final String strResponseString;

		try {

			/* Getting Data From Request */
			strEngineeringItemTypeId = request_p
					.getParameter("strEngineeringItemTypeId");

			strFilterBy = request_p.getParameter("strFilterBy");
			strFilterValue = request_p.getParameter("strFilterValue");

			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			strSeatId = userVo.getUserSeatId();

			serviceEnggMstVO = new ServiceEnggMstVO();
			serviceEnggMstVO.setStrHospitalCode(strHospitalCode);
			serviceEnggMstVO.setStrSeatid(strSeatId);
			serviceEnggMstVO.setStrEnggItemTypeId(strEngineeringItemTypeId);

			if (strFilterBy.equals("ENGG_ITEM_SUBTYPE")) {

				serviceEnggMstVO.setStrMode("3");
				serviceEnggMstVO.setStrEnggItemSubTypeId(strFilterValue);

			} else if (strFilterBy.equals("SKILL")) {
				serviceEnggMstVO.setStrMode("5");
				serviceEnggMstVO.setStrSkillId(strFilterValue);

			}

			strOtherServiceEngineerDetailsTable = ComplaintMaintenanceStatusDATA
					.getOtherServiceEngineerDetailsTable(serviceEnggMstVO);

			sbResponseStringBuffer.append("SUCCESS");
			sbResponseStringBuffer.append("####");
			sbResponseStringBuffer.append(strOtherServiceEngineerDetailsTable);

		} catch (Exception _e) {

			HisException eObj = new HisException(
					"BMED",
					"ComplaintMaintenanceStatusUTIL->getOtherServiceEngineerDetailsTable(HttpServletRequest,HttpServletResponse) -->",
					_e.getMessage());

			sbResponseStringBuffer.append("ERROR");
			sbResponseStringBuffer.append("####");
			sbResponseStringBuffer.append("Application Error [ERROR ID : ");
			sbResponseStringBuffer.append(eObj.getErrorID());
			sbResponseStringBuffer.append("], Contact System Administrator! ");

			eObj = null;
		} finally {

			strResponseString = sbResponseStringBuffer.toString();
			try {
				response_p.getWriter().write(strResponseString);
			} catch (IOException e) {

				new HisException(
						"BMED",
						"ComplaintMaintenanceStatusUTIL->getOtherServiceEngineerDetailsTable(HttpServletRequest,HttpServletResponse)[finally block]",
						e.getMessage());
			}
		}

	}

	public static void initializeAttended(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p,HttpServletResponse response_p) {
		ComplaintLogOfflineDATA.initializeAttended(
				complaintMaintenanceStatusFB_p, request_p,response_p);

	}

	/**
	 * Forwards the control to Complaint Details View Page
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the request
	 */
	public static void initializeComplaintDetailsView(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		ComplaintMaintenanceStatusDATA.initializeComplaintDetailsView(
				complaintMaintenanceStatusFB_p, request_p);
	}

	/**
	 * Forwards the control to initialize Reminder Page
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the request
	 */
	public static void initializeReminder(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		ComplaintMaintenanceStatusDATA.initializeReminder(
				complaintMaintenanceStatusFB_p, request_p);
	}

	/**
	 * To save data for Complaint Reminder Page
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the request
	 */
	public static void saveReminder(ComplaintMaintenanceStatusFB formBean_p,
			HttpServletRequest request_p) {
		ComplaintMaintenanceStatusDATA.saveReminder(formBean_p, request_p);
	}

	/**
	 * Forwards the control to initialize Reminder Reply Page
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the request
	 */
	public static void initializeReminderReply(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		 ComplaintMaintenanceStatusDATA.initializeReminderReply(complaintMaintenanceStatusFB_p,
		 request_p);
	}

	/**
	 * To save data for Complaint Reminder Reply Page
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 *            the ComplaintMaintenanceStatusFB
	 * @param request_p
	 *            the request
	 */
	public static void saveReminderReply(
			ComplaintMaintenanceStatusFB formBean_p,
			HttpServletRequest request_p) {
		 ComplaintMaintenanceStatusDATA.saveReminderReply(formBean_p,
		 request_p);
	}

	public static void saveAttended(
			ComplaintLogOfflineFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		/*
		 * variable declaration
		 */
		final UserVO userVo;
		final ComplaintAttendDtlVO complaintAttendDtlVO;
		final ComplaintRequestDtlVO complaintRequestDtlVO;
		final HemtComplaintStatusDtlVO complaintStatusDtlVO;
		final HemtItemSparePartDtlVO hemtItemSparePartDtlVO;
		final HemtItemSparePartDtlVO oldHemtItemSparePartDtlVO;

		final String strHospitalCode;
		final String strSeatId;
		final String strErrMsg;

		final String strComplaintId;
		final String strMainteId;
		final String strProblemDescription;
		final String strVendorId;
		final String strServiceEngineerName;
		final String strContactNo;
		final String strRemarks;
		final String strAttendDate;
		final String strAttendTime;
		final String strSolutionProvided;
		final String strWorkingCondition;
		String strSparePartsRequired;
		final String strWorkStatus;
		final String strGatePassNo;
		
		String strIsCost,strCost,strBillNo,strBillDate;
		String strFromDate,strFromTime,strToDate,strToTime;

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
		// final String strSpareItemTableSlNo;

		String strOldStoreId = null;
		String strOldItemId = null;
		String strOldSerialNo = null;
		String strOldSparePartId = null;
		String strOldSpareItemSerialNo = null;
		String strOldSpareItemTableSlNo = null;

		try {

			/* Getting Data From Request */
			strComplaintId = complaintMaintenanceStatusFB_p.getStrComplaintId();
			strMainteId = complaintMaintenanceStatusFB_p.getStrMainteId();
			strProblemDescription = complaintMaintenanceStatusFB_p.getStrProblemDescription();
			strVendorId = complaintMaintenanceStatusFB_p.getStrVendorId();
			strServiceEngineerName = complaintMaintenanceStatusFB_p.getStrServiceEngineerName();
			strContactNo = complaintMaintenanceStatusFB_p.getStrContactNo();
			strRemarks = complaintMaintenanceStatusFB_p.getStrRemarks();
			strAttendDate = complaintMaintenanceStatusFB_p.getStrAttendDate();
			strAttendTime = complaintMaintenanceStatusFB_p.getStrAttendTime();
			strSolutionProvided = complaintMaintenanceStatusFB_p.getStrSolutionProvided();
			strWorkingCondition = complaintMaintenanceStatusFB_p.getStrWorkingCondition();
			strSparePartsRequired = complaintMaintenanceStatusFB_p.getStrSparePartsRequired();
			
			strIsCost		=	complaintMaintenanceStatusFB_p.getStrIsCostInvolved();
			strCost			=	complaintMaintenanceStatusFB_p.getStrCost();
			strBillNo		=	complaintMaintenanceStatusFB_p.getStrBillNo();
			strBillDate		=	complaintMaintenanceStatusFB_p.getStrBillDate();
			
			strFromDate		=	complaintMaintenanceStatusFB_p.getStrFromDate();
			strFromTime		=	complaintMaintenanceStatusFB_p.getStrFromTime();
			strToDate		=	complaintMaintenanceStatusFB_p.getStrToDate();
			strToTime		=	complaintMaintenanceStatusFB_p.getStrToTime();
			
			if (strSparePartsRequired == null) {
				strSparePartsRequired = "0";
			}
			strWorkStatus = complaintMaintenanceStatusFB_p.getStrWorkStatus();// Work Status has been changed to Visit Label
			strGatePassNo = complaintMaintenanceStatusFB_p.getStrGatePassNo();

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
			strSparePartStatusId = complaintMaintenanceStatusFB_p.getStrSparePartStatusId();
			strPerformedDate = complaintMaintenanceStatusFB_p.getStrPerformedDate();
			strSparePartStockDetailsRadio = complaintMaintenanceStatusFB_p.getStrSparePartStockDetailsRadio();

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
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			strSeatId = userVo.getUserSeatId();

			/*
			 * Instantiating VOs
			 */
			complaintAttendDtlVO = new ComplaintAttendDtlVO();
			complaintRequestDtlVO = new ComplaintRequestDtlVO();
			complaintStatusDtlVO = new HemtComplaintStatusDtlVO();
			hemtItemSparePartDtlVO = new HemtItemSparePartDtlVO();
			oldHemtItemSparePartDtlVO = new HemtItemSparePartDtlVO();

			/*
			 * Setting Hospital Code to VOs
			 */
			complaintAttendDtlVO.setStrHospitalCode(strHospitalCode);
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);
			complaintStatusDtlVO.setStrHospitalCode(strHospitalCode);
			hemtItemSparePartDtlVO.setStrHospitalCode(strHospitalCode);
			oldHemtItemSparePartDtlVO.setStrHospitalCode(strHospitalCode);

			/*
			 * Setting Seat Id to VOs
			 */
			complaintAttendDtlVO.setStrSeatid(strSeatId);
			complaintRequestDtlVO.setStrSeatid(strSeatId);
			complaintStatusDtlVO.setStrSeatId(strSeatId);
			hemtItemSparePartDtlVO.setStrSeatid(strSeatId);
			oldHemtItemSparePartDtlVO.setStrSeatid(strSeatId);

			/*
			 * Setting Attend Detail VO
			 */
			complaintAttendDtlVO.setStrReqId(strComplaintId);
			complaintAttendDtlVO.setStrMainteId(strMainteId);
			complaintAttendDtlVO.setStrProbDescrip(strProblemDescription);
			complaintAttendDtlVO.setStrVendorId(strVendorId);
			complaintAttendDtlVO.setStrAttendedContactPerson(strServiceEngineerName);
			complaintAttendDtlVO.setStrAttendedContactNo(strContactNo);
			complaintAttendDtlVO.setStrAttendedRemarks(strRemarks);
			complaintAttendDtlVO.setStrAttendDate(strAttendDate + " "+ strAttendTime);
			complaintAttendDtlVO.setStrSolutionProvided(strSolutionProvided);
			complaintAttendDtlVO.setStrIsWorking(strWorkingCondition);
			complaintAttendDtlVO.setStrIsSpareReq(strSparePartsRequired);
			complaintAttendDtlVO.setStrIsSolved(strWorkStatus);
			complaintAttendDtlVO.setStrGatepassNo(strGatePassNo);
			complaintAttendDtlVO.setStrStatus(strSparePartStatusId);
			
			complaintAttendDtlVO.setStrIsCost(strIsCost);
			complaintAttendDtlVO.setStrCost(strCost);
			complaintAttendDtlVO.setStrBillNo(strBillNo);
			complaintAttendDtlVO.setStrBillDate(strBillDate);
			
			complaintAttendDtlVO.setStrFromDate(strFromDate+" "+strFromTime);
			complaintAttendDtlVO.setStrToDate(strToDate+" "+strToTime);
			
			
			

			/*
			 * Setting Request Detail VO
			 */
			complaintRequestDtlVO.setStrReqId(strComplaintId);
			if (strWorkStatus.equals("1")) {
				complaintRequestDtlVO.setStrMainStatus("2");
				complaintRequestDtlVO.setStrSubStatus("50");
			} else if (strWorkStatus.equals("2")) {
				if (strSparePartsRequired.equals("1")) {
					complaintRequestDtlVO.setStrMainStatus("2");
					complaintRequestDtlVO.setStrSubStatus("40");
				} else if (strSparePartsRequired.equals("0")) {
					complaintRequestDtlVO.setStrMainStatus("2");
					complaintRequestDtlVO.setStrSubStatus("30");
				}
			}
			complaintRequestDtlVO.setStrRemarks(strRemarks);

			/*
			 * Setting Status Detail VO
			 */
			complaintStatusDtlVO.setStrReqId(strComplaintId);
			complaintStatusDtlVO.setStrActionId("3");
			if (strWorkStatus.equals("1")) {
				complaintStatusDtlVO.setStrMainStatus("2");
				complaintStatusDtlVO.setStrSubStatus("50");
			} else if (strWorkStatus.equals("2")) {
				if (strSparePartsRequired.equals("1")) {
					complaintStatusDtlVO.setStrMainStatus("2");
					complaintStatusDtlVO.setStrSubStatus("40");
				} else if (strSparePartsRequired.equals("0")) {
					complaintStatusDtlVO.setStrMainStatus("2");
					complaintStatusDtlVO.setStrSubStatus("30");
				}
			}
			complaintStatusDtlVO.setStrRemarks(strRemarks);

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

			/*
			 * Calling DATA
			 */
			ComplaintLogOfflineDATA.saveAttended(complaintAttendDtlVO,
					complaintRequestDtlVO, complaintStatusDtlVO,
					hemtItemSparePartDtlVO, oldHemtItemSparePartDtlVO);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusUTIL.saveAttended(ComplaintMaintenanceStatusFB,HttpServletRequest)--> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusUTIL", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	public static void initializeClose(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		ComplaintMaintenanceStatusDATA.initializeClose(
				complaintMaintenanceStatusFB_p, request_p);

	}

	public static void saveClose(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		/*
		 * variable declaration
		 */
		final UserVO userVo;
		final ComplaintRequestDtlVO complaintRequestDtlVO;

		final String strHospitalCode;
		final String strSeatId;
		final String strErrMsg;

		final String strComplaintId;

		final String strRepaiedByVendor;
		//String strVendorId = null;
		String strInvoiceNo = null;
		final String strClosingDate;
		final String strClosingTime;
		final String strTotalCostInvolved;
		final String strReasonForClosing;
		final String strWorkingCondition;
		String strTotalDownTime = null;
		final String strVerifiedBy;
		final String strRemarks;

		try {

			/* Getting Data From Request */
			strComplaintId = complaintMaintenanceStatusFB_p.getStrComplaintId();
			strRepaiedByVendor = complaintMaintenanceStatusFB_p
					.getStrRepaiedByVendor();
			if ("1".equals(strRepaiedByVendor)) {
				//strVendorId = complaintMaintenanceStatusFB_p.getStrVendorId();
				strInvoiceNo = complaintMaintenanceStatusFB_p.getStrInvoiceNo();
			}
			strClosingDate = complaintMaintenanceStatusFB_p.getStrClosingDate();
			strClosingTime = complaintMaintenanceStatusFB_p.getStrClosingTime();
			strTotalCostInvolved = complaintMaintenanceStatusFB_p
					.getStrTotalCostInvolved();
			strReasonForClosing = complaintMaintenanceStatusFB_p
					.getStrReasonForClosing();
			strWorkingCondition = complaintMaintenanceStatusFB_p
					.getStrWorkingCondition();
			if (strWorkingCondition.equals("0")) {
				strTotalDownTime = complaintMaintenanceStatusFB_p
						.getStrTotalDownTime();
			}
			strVerifiedBy = complaintMaintenanceStatusFB_p.getStrVerifiedBy();
			strRemarks = complaintMaintenanceStatusFB_p.getStrRemarks();
			/*
			 * User Value
			 */
			userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode = userVo.getHospitalCode();
			strSeatId = userVo.getUserSeatId();

			/*
			 * Instantiating VOs
			 */
			complaintRequestDtlVO = new ComplaintRequestDtlVO();

			/*
			 * Setting Hospital Code to VOs
			 */
			complaintRequestDtlVO.setStrHospitalCode(strHospitalCode);

			/*
			 * Setting Seat Id to VOs
			 */
			complaintRequestDtlVO.setStrSeatid(strSeatId);

			/*
			 * Setting Request Detail VO
			 */
			// HEMDT_CLOSED_DATE
			// HEMDT_CLOSED_SEATID
			// HEMSTR_CLOSE_REASON
			// HEMNUM_COST
			// HEMSTR_DOWN_TIME_FR
			// HEMSTR_VERIFIED_ID
			// HEMSTR_VERIFIED_REMARKS
			// HEMNUM_IS_WORKING
			// HEMNUM_STATUS_REMARKS
			// HEMSTR_VENDOR_INVOICE_NO

			complaintRequestDtlVO.setStrReqId(strComplaintId);

			complaintRequestDtlVO.setStrClosedDate(strClosingDate + " "	+ strClosingTime);
			complaintRequestDtlVO.setStrClosedSeatid(strSeatId);
			complaintRequestDtlVO.setStrCloseReason(strReasonForClosing);
			complaintRequestDtlVO.setStrCost(strTotalCostInvolved);
			complaintRequestDtlVO.setStrDownTimeFr(strTotalDownTime);
			complaintRequestDtlVO.setStrVerifiedId(strVerifiedBy);
			complaintRequestDtlVO.setStrVerifiedRemarks(strRemarks);
			complaintRequestDtlVO.setStrIsWorking(strWorkingCondition);
			complaintRequestDtlVO.setStrStatusRemarks(strRemarks);
			complaintRequestDtlVO.setStrVendorInvoiceNo(strInvoiceNo);

			/*
			 * Calling DATA
			 */
			ComplaintMaintenanceStatusDATA.saveClose(complaintRequestDtlVO);

		} catch (Exception e) {

			strErrMsg = "ComplaintMaintenanceStatusUTIL.saveClose(ComplaintMaintenanceStatusFB,HttpServletRequest)--> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed",
					"ComplaintMaintenanceStatusUTIL", strErrMsg);
			complaintMaintenanceStatusFB_p
					.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

			eObj = null;
		}

	}

	public static String initializeGrievances(
			ComplaintMaintenanceStatusFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p) {
		return ComplaintMaintenanceStatusDATA.initializeGrievances(complaintMaintenanceStatusFB_p, request_p);

	}

	/**
	 * To get Uploaded File
	 * 
	 * @param complaintMaintenanceStatusFB_p
	 * @param request_p
	 * @param response_p
	 */
	public static void getUploadedFile(	EquipmentInspectionTestDtlsFB fb,HttpServletRequest request_p, HttpServletResponse response_p) {
		EquipmentInspectionTestDtlsDATA.getUploadedFile(fb, request_p, response_p);
	}
	

}
