package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.ComplaintRequestDtlVO;
import bmed.vo.HemtItemMcDtlVO;

public class ComplaintRequestDtlDAO {

	/**
	 * To Get Data
	 * 
	 * @param complaintRequestDtlVO_p	the ComplaintRequestDtlVO
	 * @param hisDAO_p	the	HisDAO
	 * @throws Exception
	 */
	public static void getData(ComplaintRequestDtlVO complaintRequestDtlVO_p,
			HisDAO hisDAO_p) throws Exception {

		final String strproc_name = "{CALL PKG_BMED_VIEW.PROC_COMPLAINT_REQUEST_DTL(?,?,?,?,?, ?,?,?,?,?, ?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(complaintRequestDtlVO_p);

			/* Setting and Registering In and Out Parameters */

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",complaintRequestDtlVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",complaintRequestDtlVO_p.getStrHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_dept_code",complaintRequestDtlVO_p.getStrDeptId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_attached",complaintRequestDtlVO_p.getStrIsAttached(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_emp_id",complaintRequestDtlVO_p.getStrEmpId(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_preventive",complaintRequestDtlVO_p.getStrIsPreventive(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_req_id",complaintRequestDtlVO_p.getStrReqId(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_id",complaintRequestDtlVO_p.getStrItemId(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_batch_no",complaintRequestDtlVO_p.getStrBatchNo(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_serial_no",complaintRequestDtlVO_p.getStrSerialNo(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id",complaintRequestDtlVO_p.getStrSeatid(),11);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err",1,12); // 1 for varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset",2,13); // 2 for Cursor

			/* Executing Procedure */
			//hisDAO_p.execute(nProcedureIndex, 1);
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			complaintRequestDtlVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			System.out.println("exception----->"+exception);
			exception.printStackTrace();
			throw new Exception(
					"ComplaintRequestDtlDAO.getData(ComplaintRequestDtlVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}

	/*
	 * To insert or update data
	 * 
	 * @param complaintRequestDtlVO_p the ComplaintRequestDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void save(ComplaintRequestDtlVO complaintRequestDtlVO_p,
			HisDAO hisDAO_p) throws Exception {
		/* Total Variable 52 */
		final String strproc_name = "{CALL  pkg_bmed_dml.proc_hemt_complaint_req_dtl(?,?,?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? ,?,?,?,?,?, ?,?,?,?,?, ?,?)}";
		final int nProcedureIndex;
		try {
			
			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(complaintRequestDtlVO_p);
			
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", complaintRequestDtlVO_p.getStrMode(),1); // 1

			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REQ_ID",	complaintRequestDtlVO_p.getStrReqId(),2); // 2
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_HOSPITAL_CODE", complaintRequestDtlVO_p.getStrHospitalCode(),3); // 3
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REQ_TYPE", complaintRequestDtlVO_p.getStrReqType(),4); // 4
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_IS_ATTACHED", complaintRequestDtlVO_p.getStrIsAttached(),5); // 5
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_IS_ITEM", complaintRequestDtlVO_p.getStrIsItem(),6); // 6
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ENGG_ITEM_TYPE_ID", complaintRequestDtlVO_p.getStrEnggItemTypeId(),7); // 7
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_IS_PREVENTIVE", complaintRequestDtlVO_p.getStrIsPreventive(),8); // 8
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_IS_ONLINE", complaintRequestDtlVO_p.getStrIsOnline(),9); // 9
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ENGG_ITEM_SUB_TYPE_ID",	complaintRequestDtlVO_p.getStrEnggItemSubTypeId(),10); // 10
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ITEM_ID", complaintRequestDtlVO_p.getStrItemId(),11); // 11
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_HEM_FLAG", complaintRequestDtlVO_p.getStrHemFlag(),12);// Dummy //12
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_SERIAL_NO",	complaintRequestDtlVO_p.getStrSerialNo(),13); // 13
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REQ_DATE", complaintRequestDtlVO_p.getStrReqDate()==null||
					complaintRequestDtlVO_p.getStrReqDate().equals("") ? "0" : complaintRequestDtlVO_p.getStrReqDate(),14); // Hard Coded Value //14
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_MANUF_SERIAL_NO",	complaintRequestDtlVO_p.getStrManufSerialNo(),15); // 15
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_STORE_ID", complaintRequestDtlVO_p.getStrStoreId(),16); // 16 //Dummy
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_LANDMARK_DESC", complaintRequestDtlVO_p.getStrLandmarkDesc(),17); // Hard Coded in Proc //17
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_BATCH_NO", complaintRequestDtlVO_p.getStrBatchNo(),18); // Hard Coded in Proc //18
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_WARRANTY_SLNO",	complaintRequestDtlVO_p.getStrWarrantySlNo()==null||
					complaintRequestDtlVO_p.getStrWarrantySlNo().equals("") ? "0" : complaintRequestDtlVO_p.getStrWarrantySlNo(),19); // 19
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_MAIN_STATUS", complaintRequestDtlVO_p.getStrMainStatus(),20); // 20
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_MC_SLNO", complaintRequestDtlVO_p.getStrMcSlNo()==null || 
					complaintRequestDtlVO_p.getStrMcSlNo().equals("") ? "0" : complaintRequestDtlVO_p.getStrMcSlNo(),21); // 21
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_VENDOR_ID",	complaintRequestDtlVO_p.getStrVendorId()==null ||
					complaintRequestDtlVO_p.getStrVendorId().equals("") ? "0" : complaintRequestDtlVO_p.getStrVendorId(),22); // 22
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_MAINTE_ID",	complaintRequestDtlVO_p.getStrMainteId()==null ||
					complaintRequestDtlVO_p.getStrMainteId().equals("") ? "0" : complaintRequestDtlVO_p.getStrMainteId(),23); // 23
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_COMPLAINT_DES",	complaintRequestDtlVO_p.getStrComplaintDes(),24); // 24
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_EMP_ID", complaintRequestDtlVO_p.getStrEmpId(),25); // 25
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_DEPT_ID", complaintRequestDtlVO_p.getStrDeptId(),26); // 26
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_PREFER_TIME_FR",	complaintRequestDtlVO_p.getStrPreferTimeFr(),27); // Enter By Function //27
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_PREFER_TIME_TO",	complaintRequestDtlVO_p.getStrPreferTimeTo(),28); // 28
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_DOWN_TIME_FR",	complaintRequestDtlVO_p.getStrDownTimeFr()==null ||
					complaintRequestDtlVO_p.getStrDownTimeFr().equals("") ? "" : complaintRequestDtlVO_p.getStrDownTimeFr(),29); // 29
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_CONTACT_PERSON",	complaintRequestDtlVO_p.getStrContactPerson(),30); // Hard Coded in Proc //30
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_IS_WORKING",	complaintRequestDtlVO_p.getStrIsWorking(),31); // 31
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_CONTACT_NO",	complaintRequestDtlVO_p.getStrContactNo(),32); // 32
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMDT_NOTWORKING_DATE",	complaintRequestDtlVO_p.getStrNotWorkingDate()==null||
					complaintRequestDtlVO_p.getStrNotWorkingDate().equals("") ? "NA" : complaintRequestDtlVO_p.getStrNotWorkingDate(),33); // 33
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GSTR_REMARKS",	complaintRequestDtlVO_p.getStrRemarks()==null||
					complaintRequestDtlVO_p.getStrRemarks().equals("") ? "NA" : complaintRequestDtlVO_p.getStrRemarks(),34); // 34
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GDT_ENTRY_DATE", complaintRequestDtlVO_p.getStrEntryDate()==null||
					complaintRequestDtlVO_p.getStrEntryDate().equals("0") ? "0" : complaintRequestDtlVO_p.getStrEntryDate(),35); // 35
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMDT_COMP_INTEMATION",	complaintRequestDtlVO_p.getStrCompIntemation()==null||
					complaintRequestDtlVO_p.getStrCompIntemation().equals("") ? "0" : complaintRequestDtlVO_p.getStrCompIntemation(),36); // 36
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_ISVALID", complaintRequestDtlVO_p.getStrIsvalid(),37); // 37
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_SEATID",  complaintRequestDtlVO_p.getStrSeatid(),38); // 38
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_SUB_STATUS",	complaintRequestDtlVO_p.getStrSubStatus(),39);// 39
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GSTR_STATUS_REMARKS", complaintRequestDtlVO_p.getStrStatusRemarks(),40);// 40
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_COST", complaintRequestDtlVO_p.getStrCost(),41);// 41
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_VERIFIED_ID", complaintRequestDtlVO_p.getStrVerifiedId()==null || 
					complaintRequestDtlVO_p.getStrVerifiedId().equals("") ? "0" : complaintRequestDtlVO_p.getStrVerifiedId(),42);// 42
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_VERIFIED_REMARKS", complaintRequestDtlVO_p.getStrVerifiedRemarks()==null ||
					complaintRequestDtlVO_p.getStrVerifiedRemarks().equals("") ? "0" : complaintRequestDtlVO_p.getStrVerifiedRemarks(),43);// 43
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_CANCEL_ID",	complaintRequestDtlVO_p.getStrCancelId()==null || 
					complaintRequestDtlVO_p.getStrCancelId().equals("") ? "0" : complaintRequestDtlVO_p.getStrCancelId(),44);// 44
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMDT_CANCEL_DATE",	complaintRequestDtlVO_p.getStrCancelDate()==null||
					complaintRequestDtlVO_p.getStrCancelDate().equals("") ? "0" : complaintRequestDtlVO_p.getStrCancelDate(),45);// 45
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_CANCEL_REMARKS",	complaintRequestDtlVO_p.getStrCancelRemarks()==null||
					complaintRequestDtlVO_p.getStrCancelRemarks().equals("") ? "0" : complaintRequestDtlVO_p.getStrCancelRemarks(),46);// 46
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_CANCEL_SEATID",	complaintRequestDtlVO_p.getStrCancelSeatid()==null ||
					complaintRequestDtlVO_p.getStrCancelSeatid().equals("") ? "0" : complaintRequestDtlVO_p.getStrCancelSeatid(),47);// 47
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMDT_CLOSED_DATE",	complaintRequestDtlVO_p.getStrClosedDate()==null||
					complaintRequestDtlVO_p.getStrClosedDate().equals("") ? "0" : complaintRequestDtlVO_p.getStrClosedDate(),48);// 48
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMDT_CLOSED_SEATID", complaintRequestDtlVO_p.getStrClosedSeatid()==null ||
					complaintRequestDtlVO_p.getStrClosedSeatid().equals("") ? "0" : complaintRequestDtlVO_p.getStrClosedSeatid(),49);// 49
			
//			System.out.println("p_HEMDT_CLOSED_DATE::::"+complaintRequestDtlVO_p.getStrClosedDate());
//			System.out.println("p_HEMDT_CLOSED_SEATID::::"+complaintRequestDtlVO_p.getStrClosedSeatid());
//			System.out.println("p_HEMNUM_MAIN_STATUS:::"+complaintRequestDtlVO_p.getStrMainStatus());
//			System.out.println("p_HEMNUM_SUB_STATUS:::"+complaintRequestDtlVO_p.getStrSubStatus());
//			System.out.println("p_HEMNUM_REQ_ID:::"+complaintRequestDtlVO_p.getStrReqId()); // 2
//			System.out.println("p_GNUM_HOSPITAL_CODE:::"+complaintRequestDtlVO_p.getStrHospitalCode()); // 3
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_CLOSE_REASON", complaintRequestDtlVO_p.getStrCloseReason()==null||
					complaintRequestDtlVO_p.getStrCloseReason().equals("") ? "0" : complaintRequestDtlVO_p.getStrCloseReason(),50);// 50
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_VENDOR_INVOICE_NO", complaintRequestDtlVO_p.getStrVendorInvoiceNo()==null||
					complaintRequestDtlVO_p.getStrVendorInvoiceNo().equals("") ? "0" : complaintRequestDtlVO_p.getStrVendorInvoiceNo(),51);// 51
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_nature_of_service_id", complaintRequestDtlVO_p.getStrNatureOfServiceId(),52);// 52
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_nature_of_service", complaintRequestDtlVO_p.getStrNatureOfService(),53);// 53

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,54); // varchar //54

			/* Executing Procedure */
			hisDAO_p.execute(nProcedureIndex, 1);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(
					"HemtItemMcDtlDAO.getItemBrandCombo(HemtItemMcDtlVO)-->"
							+ exception.getMessage());
		}
	}



	/* This method builds single object from web row set */
	public static void getDataSingleRow(
			ComplaintRequestDtlVO complaintRequestDtlVO) throws Exception {
		String strReqId="";
		String strReqType="";
		String strIsAttached="";
		String strIsItem="";
		String strEnggItemTypeId="";
		String strIsPreventive="";
		String strIsOnline="";
		String strEnggItemSubTypeId="";
		String strItemId="";
		String strHemFlag="";
		String strSerialNo="";
		String strReqDate="";
		String strManufSerialNo="";
		String strStoreId="";
		String strLandmarkDesc="";
		String strBatchNo="";
		String strWarrantySlNo="";
		String strMainStatus="";
		String strMcSlNo="";
		String strVendorId="";
		String strMainteId="";
		String strComplaintDes="";
		String strEmpId="";
		String strDeptId="";
		String strPreferTimeFr="";
		String strPreferTimeTo="";
		String strDownTimeFr="";
		String strContactPerson="";
		String strIsWorking="";
		String strContactNo="";
		String strNotWorkingDate="";
		String strRemarks="";
		String strCompIntemation="";
		String strIsvalid="";
		String strSubStatus="";
		String strStatusRemarks="";
		String strCost="";
		String strVerifiedId="";
		String strVerifiedRemarks="";
		String strCancelId="";
		String strCancelDate="";
		String strCancelRemarks="";
		String strCancelSeatid="";
		String strClosedDate="";
		String strClosedSeatid="";
		String strCloseReason="";
		String strVendorInvoiceNo="";
		String strItemTypeName="";
		String strItemSubTypeName="";
		String strItemName="";
		String strStoreName="";
		String strStatusName="";
		String strVendorName="";
		String strMaintenanceName="";
		String strServiceEnggName="";
		String strDeptName="";
		WebRowSet webRowSet = complaintRequestDtlVO.getWrsData();
		try {

			if (webRowSet.next()) {

				// HEMNUM_REQ_ID: 1
				// GNUM_HOSPITAL_CODE: 2
				// HEMNUM_REQ_TYPE: 3
				// HEMNUM_IS_ATTACHED: 4
				// HEMNUM_IS_ITEM: 5
				// HEMNUM_ENGG_ITEM_TYPE_ID: 6
				// HEMNUM_IS_PREVENTIVE: 7
				// HEMNUM_IS_ONLINE: 8
				// HEMNUM_ENGG_ITEM_SUB_TYPE_ID: 9
				// HEMNUM_ITEM_ID: 10
				// HEMNUM_HEM_FLAG: 11
				// HEMSTR_SERIAL_NO: 12
				// HEMNUM_REQ_DATE: 13
				// HEMSTR_MANUF_SERIAL_NO: 14
				// HEMNUM_STORE_ID: 15
				// HEMNUM_LANDMARK_DESC: 16
				// HEMSTR_BATCH_NO: 17
				// HEMNUM_WARRANTY_SLNO: 18
				// HEMNUM_MAIN_STATUS: 19
				// HEMNUM_MC_SLNO: 20
				// HEMNUM_VENDOR_ID: 21
				// HEMNUM_MAINTE_ID: 22
				// HEMSTR_COMPLAINT_DES: 23
				// HEMSTR_EMP_ID: 24
				// HEMNUM_DEPT_ID: 25
				// HEMSTR_PREFER_TIME_FR: 26
				// HEMSTR_PREFER_TIME_TO: 27
				// HEMSTR_DOWN_TIME_FR: 28
				// HEMSTR_CONTACT_PERSON: 29
				// HEMNUM_IS_WORKING: 30
				// HEMSTR_CONTACT_NO: 31
				// HEMDT_NOTWORKING_DATE: 32
				// GSTR_REMARKS: 33
				// GDT_ENTRY_DATE: 34
				// HEMDT_COMP_INTEMATION: 35
				// GNUM_ISVALID: 36
				// GNUM_SEATID: 37
				// HEMNUM_SUB_STATUS: 38
				// GSTR_STATUS_REMARKS: 39
				// HEMNUM_COST: 40
				// HEMSTR_VERIFIED_ID: 41
				// HEMSTR_VERIFIED_REMARKS: 42
				// HEMNUM_CANCEL_ID: 43
				// HEMDT_CANCEL_DATE: 44
				// HEMSTR_CANCEL_REMARKS: 45
				// HEMNUM_CANCEL_SEATID: 46
				// HEMDT_CLOSED_DATE: 47
				// HEMDT_CLOSED_SEATID: 48
				// HEMSTR_CLOSE_REASON: 49
				// HEMSTR_VENDOR_INVOICE_NO: 50
				// ENGG_ITEM_TYPE_NAME: 51
				// ENGG_ITEM_SUB_TYPE_NAME: 52
				// ITEM_NAME: 53
				// STORE_NAME: 54
				// STATUS_NAME: 55
				// VENDOR_NAME: 56
				// MAINTENANCE_NAME: 57
				// SERVICE_ENGG_NAME: 58
				// DEPT_NAME: 59
				// Verified By Name

				strReqId = webRowSet.getString("HEMNUM_REQ_ID");
				strReqType = webRowSet.getString("HEMNUM_REQ_TYPE");
				strIsAttached = webRowSet.getString("HEMNUM_IS_ATTACHED");
				strIsItem = webRowSet.getString("HEMNUM_IS_ITEM");
				strEnggItemTypeId = webRowSet
						.getString("HEMNUM_ENGG_ITEM_TYPE_ID");
				strIsPreventive = webRowSet.getString("HEMNUM_IS_PREVENTIVE");
				strIsOnline = webRowSet.getString("HEMNUM_IS_ONLINE");
				strEnggItemSubTypeId = webRowSet
						.getString("HEMNUM_ENGG_ITEM_SUB_TYPE_ID");
				strItemId = webRowSet.getString("HEMNUM_ITEM_ID");
				strHemFlag = webRowSet.getString("HEMNUM_HEM_FLAG");
				strSerialNo = webRowSet.getString("HEMSTR_SERIAL_NO");
				strReqDate = webRowSet.getString("HEMNUM_REQ_DATE");
				strManufSerialNo = webRowSet
						.getString("HEMSTR_MANUF_SERIAL_NO");
				strStoreId = webRowSet.getString("HEMNUM_STORE_ID");
				strLandmarkDesc = webRowSet.getString("HEMNUM_LANDMARK_DESC");
				strBatchNo = webRowSet.getString("HEMSTR_BATCH_NO");
				strWarrantySlNo = webRowSet.getString("HEMNUM_WARRANTY_SLNO");
				strMainStatus = webRowSet.getString("HEMNUM_MAIN_STATUS");
				strMcSlNo = webRowSet.getString("HEMNUM_MC_SLNO");
				strVendorId = webRowSet.getString("HEMNUM_VENDOR_ID");
				strMainteId = webRowSet.getString("HEMNUM_MAINTE_ID");
				strComplaintDes = webRowSet.getString("HEMSTR_COMPLAINT_DES");
				strEmpId = webRowSet.getString("HEMSTR_EMP_ID");
				strDeptId = webRowSet.getString("HEMNUM_DEPT_ID");
				strPreferTimeFr = webRowSet.getString("HEMSTR_PREFER_TIME_FR");
				strPreferTimeTo = webRowSet.getString("HEMSTR_PREFER_TIME_TO");
				strDownTimeFr = webRowSet.getString("HEMSTR_DOWN_TIME_FR");
				strContactPerson = webRowSet.getString("HEMSTR_CONTACT_PERSON");
				strIsWorking = webRowSet.getString("HEMNUM_IS_WORKING");
				strContactNo = webRowSet.getString("HEMSTR_CONTACT_NO");
				strNotWorkingDate = webRowSet
						.getString("HEMDT_NOTWORKING_DATE");
				strRemarks = webRowSet.getString("GSTR_REMARKS");
				strCompIntemation = webRowSet
						.getString("HEMDT_COMP_INTEMATION");
				strIsvalid = webRowSet.getString("GNUM_ISVALID");
				strSubStatus = webRowSet.getString("HEMNUM_SUB_STATUS");
				strStatusRemarks = webRowSet.getString("GSTR_STATUS_REMARKS");
				strCost = webRowSet.getString("HEMNUM_COST");
				strVerifiedId = webRowSet.getString("HEMSTR_VERIFIED_ID");
				strVerifiedRemarks = webRowSet
						.getString("HEMSTR_VERIFIED_REMARKS");
				strCancelId = webRowSet.getString("HEMNUM_CANCEL_ID");
				strCancelDate = webRowSet.getString("HEMDT_CANCEL_DATE");
				strCancelRemarks = webRowSet.getString("HEMSTR_CANCEL_REMARKS");
				strCancelSeatid = webRowSet.getString("HEMNUM_CANCEL_SEATID");
				strClosedDate = webRowSet.getString("HEMDT_CLOSED_DATE");
				strClosedSeatid = webRowSet.getString("HEMDT_CLOSED_SEATID");
				strCloseReason = webRowSet.getString("HEMSTR_CLOSE_REASON");
				strVendorInvoiceNo = webRowSet
						.getString("HEMSTR_VENDOR_INVOICE_NO");
				strItemTypeName = webRowSet.getString("ENGG_ITEM_TYPE_NAME");
				strItemSubTypeName = webRowSet
						.getString("ENGG_ITEM_SUB_TYPE_NAME");
				strItemName = webRowSet.getString("ITEM_NAME");
				strStoreName = webRowSet.getString("STORE_NAME");
				strStatusName = webRowSet.getString("STATUS_NAME");
				strVendorName = webRowSet.getString("VENDOR_NAME");
				strMaintenanceName = webRowSet.getString("MAINTENANCE_NAME");
				strServiceEnggName = webRowSet.getString("SERVICE_ENGG_NAME");
				strDeptName = webRowSet.getString("DEPT_NAME");

			}

			/* Setting Data */
			complaintRequestDtlVO.setStrBatchNo(strBatchNo);
			complaintRequestDtlVO.setStrCancelDate(strCancelDate);
			complaintRequestDtlVO.setStrCancelId(strCancelId);
			complaintRequestDtlVO.setStrCancelRemarks(strCancelRemarks);
			complaintRequestDtlVO.setStrCancelSeatid(strCancelSeatid);
			complaintRequestDtlVO.setStrClosedDate(strClosedDate);
			complaintRequestDtlVO.setStrClosedSeatid(strClosedSeatid);
			complaintRequestDtlVO.setStrCloseReason(strCloseReason);
			complaintRequestDtlVO.setStrCompIntemation(strCompIntemation);
			complaintRequestDtlVO.setStrComplaintDes(strComplaintDes);
			complaintRequestDtlVO.setStrContactNo(strContactNo);
			complaintRequestDtlVO.setStrContactPerson(strContactPerson);
			complaintRequestDtlVO.setStrCost(strCost);
			complaintRequestDtlVO.setStrDeptId(strDeptId);
			complaintRequestDtlVO.setStrDeptName(strDeptName);
			complaintRequestDtlVO.setStrDownTimeFr(strDownTimeFr);
			complaintRequestDtlVO.setStrEmpId(strEmpId);
			complaintRequestDtlVO.setStrEnggItemSubTypeId(strEnggItemSubTypeId);
			complaintRequestDtlVO.setStrEnggItemTypeId(strEnggItemTypeId);
			complaintRequestDtlVO.setStrHemFlag(strHemFlag);
			complaintRequestDtlVO.setStrIsAttached(strIsAttached);
			complaintRequestDtlVO.setStrIsItem(strIsItem);
			complaintRequestDtlVO.setStrIsOnline(strIsOnline);
			complaintRequestDtlVO.setStrIsPreventive(strIsPreventive);
			complaintRequestDtlVO.setStrIsvalid(strIsvalid);
			complaintRequestDtlVO.setStrIsWorking(strIsWorking);
			complaintRequestDtlVO.setStrItemId(strItemId);
			complaintRequestDtlVO.setStrItemName(strItemName);
			complaintRequestDtlVO.setStrItemSubTypeName(strItemSubTypeName);
			complaintRequestDtlVO.setStrItemTypeName(strItemTypeName);
			complaintRequestDtlVO.setStrLandmarkDesc(strLandmarkDesc);
			complaintRequestDtlVO.setStrMainStatus(strMainStatus);
			complaintRequestDtlVO.setStrMainteId(strMainteId);
			complaintRequestDtlVO.setStrMaintenanceName(strMaintenanceName);
			complaintRequestDtlVO.setStrManufSerialNo(strManufSerialNo);
			complaintRequestDtlVO.setStrMcSlNo(strMcSlNo);
			complaintRequestDtlVO.setStrNotWorkingDate(strNotWorkingDate);
			complaintRequestDtlVO.setStrPreferTimeFr(strPreferTimeFr);
			complaintRequestDtlVO.setStrPreferTimeTo(strPreferTimeTo);
			complaintRequestDtlVO.setStrRemarks(strRemarks);
			complaintRequestDtlVO.setStrReqDate(strReqDate);
			complaintRequestDtlVO.setStrReqId(strReqId);
			complaintRequestDtlVO.setStrReqType(strReqType);
			complaintRequestDtlVO.setStrSerialNo(strSerialNo);
			complaintRequestDtlVO.setStrServiceEnggName(strServiceEnggName);
			complaintRequestDtlVO.setStrStatusName(strStatusName);
			complaintRequestDtlVO.setStrStatusRemarks(strStatusRemarks);
			complaintRequestDtlVO.setStrStoreId(strStoreId);
			complaintRequestDtlVO.setStrStoreName(strStoreName);
			complaintRequestDtlVO.setStrSubStatus(strSubStatus);
			complaintRequestDtlVO.setStrVendorId(strVendorId);
			complaintRequestDtlVO.setStrVendorInvoiceNo(strVendorInvoiceNo);
			complaintRequestDtlVO.setStrVendorName(strVendorName);
			complaintRequestDtlVO.setStrVerifiedId(strVerifiedId);
			complaintRequestDtlVO.setStrVerifiedRemarks(strVerifiedRemarks);
			complaintRequestDtlVO.setStrWarrantySlNo(strWarrantySlNo);

		} catch (Exception exception) {
			throw new Exception(
					"ComplaintRequestDtlDAO.getDataSingleRow(ComplaintRequestDtlVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}
	
	/**
	 * Process Specefic Method Used 
	 * @param complaintRequestDtlVO_p
	 * @param hisDAO_p
	 * @throws Exception
	 */
	public static void getProcessSpecificData(ComplaintRequestDtlVO complaintRequestDtlVO_p,
			HisDAO hisDAO_p) throws Exception {

		final String strproc_name = "{CALL PKG_BMED_VIEW.proc_complaint_approval_value(?,?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(complaintRequestDtlVO_p);

			/* Setting and Registering In and Out Parameters */

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					complaintRequestDtlVO_p.getStrMode());

			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_HOSPITAL_CODE",
					complaintRequestDtlVO_p.getStrHospitalCode());
			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REQ_ID",
					complaintRequestDtlVO_p.getStrReqId());
			

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1); // 1 for
																	// varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2); // 2 for
																		// Cursor

			/* Executing Procedure */
			hisDAO_p.executeProcedure(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			complaintRequestDtlVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"ComplaintRequestDtlDAO.getData(ComplaintRequestDtlVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}
	public static void getPrevMantDtl(ComplaintRequestDtlVO complaintRequestDtlVO_p,
			HisDAO hisDAO_p) throws Exception {
		final String strproc_name = "{CALL PKG_BMED_VIEW.proc_hemt_item_mc_dtl(?,?,?,?,?, ?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(complaintRequestDtlVO_p);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					complaintRequestDtlVO_p.getStrMode(), 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_id",
					complaintRequestDtlVO_p.getStrItemId().equals("") ? "0"
							: complaintRequestDtlVO_p.getStrItemId(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_batch_no",
					complaintRequestDtlVO_p.getStrBatchNo().equals("") ? "0"
							: complaintRequestDtlVO_p.getStrBatchNo(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_SlNo",
					complaintRequestDtlVO_p.getStrItemSlNo().equals("") ? "0"
							: complaintRequestDtlVO_p.getStrItemSlNo(), 4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_sl_No",
					complaintRequestDtlVO_p.getStrSerialNo().equals("") ? "0"
							: complaintRequestDtlVO_p.getStrSerialNo(), 5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					complaintRequestDtlVO_p.getStrHospitalCode(), 6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1, 7); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2, 8); // Cursor

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			/* Sets The WebRowSet in HemtItemMcDtlVO */
			complaintRequestDtlVO_p.setWrsMCDetails(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"HemtItemMcDtlDAO.getItemBrandCombo(HemtItemMcDtlVO)-->"
							+ exception.getMessage());
		}

	}


	

}
