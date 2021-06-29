package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.ComplaintAttendDtlVO;

public class ComplaintAttendDtlDAO {
	public static void save(ComplaintAttendDtlVO complaintAttendDtlVO_p,
			HisDAO hisDAO_p) throws Exception {

		/* Total Parameter 31. 1 out, 20 in parameter */

		final String strproc_name = "{CALL  PKG_BMED_DML.PROC_HEMT_COMPLAINT_ATTEND_DTL(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?)}";
		final int nProcedureIndex;

		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
			HisUtil.replaceNullValueWithEmptyString(complaintAttendDtlVO_p);
			//HisUtil.printStringFieldsOfVO(complaintAttendDtlVO_p);
			
			
			
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					complaintAttendDtlVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_req_id", complaintAttendDtlVO_p.getStrReqId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_attend_id", complaintAttendDtlVO_p.getStrAttendId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", complaintAttendDtlVO_p.getStrHospitalCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mainte_id", complaintAttendDtlVO_p.getStrMainteId(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_vendor_comp_id", complaintAttendDtlVO_p.getStrVendorCompId(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_attended_contact_person", complaintAttendDtlVO_p.getStrAttendedContactPerson(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_attended_contact_no", complaintAttendDtlVO_p.getStrAttendedContactNo(),8);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_attended_remarks", complaintAttendDtlVO_p.getStrAttendedRemarks(),9);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_prob_descrip", complaintAttendDtlVO_p.getStrProbDescrip(),10);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_service_engg_id", complaintAttendDtlVO_p.getStrServiceEnggId(),11);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_completed_date", complaintAttendDtlVO_p.getStrCompletedDate(),12);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_vendor_id", complaintAttendDtlVO_p.getStrVendorId(),13);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_attend_date", complaintAttendDtlVO_p.getStrAttendDate(),14);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_spareparts", complaintAttendDtlVO_p.getStrIsSpareParts(),15);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_cost", complaintAttendDtlVO_p.getStrIsCost(),16);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_cost", complaintAttendDtlVO_p.getStrCost(),17);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_billNo", complaintAttendDtlVO_p.getStrBillNo(),18);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_billDate", complaintAttendDtlVO_p.getStrBillDate(),19);			
			hisDAO_p.setProcInValue(nProcedureIndex, "p_fromDate", complaintAttendDtlVO_p.getStrFromDate(),20);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_toDate", complaintAttendDtlVO_p.getStrToDate(),21);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_working", complaintAttendDtlVO_p.getStrIsWorking(),22);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_solution_provided", complaintAttendDtlVO_p.getStrSolutionProvided(),23);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_workstatus_date", complaintAttendDtlVO_p.getStrWorkStatusDate(),24);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_spare_req", complaintAttendDtlVO_p.getStrIsSpareReq(),25);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gatepass_no", complaintAttendDtlVO_p.getStrGatepassNo(),26);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_status", complaintAttendDtlVO_p.getStrStatus(),27);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_is_solved", complaintAttendDtlVO_p.getStrIsSolved(),28);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seatid", complaintAttendDtlVO_p.getStrSeatid(),29);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_isvalid", complaintAttendDtlVO_p.getStrIsValid(),30);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,31);

			/* Executing Procedure */
			hisDAO_p.execute(nProcedureIndex, 1);

		} catch (Exception exception) {
			throw new Exception(
					"ComplaintAttendDtlDAO.save(ComplaintAttendDtlVO)-->"
							+ exception.getMessage());
		}
	}

	public static void getData(ComplaintAttendDtlVO complaintAttendDtlVO_p,
			HisDAO hisDAO_p) throws Exception {

		final String strProcName = "{call pkg_bmed_view.proc_hemt_complaint_attend_dtl(?,?,?,?,?, ?)}";
		
		final int nProcedureIndex;
		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(complaintAttendDtlVO_p);


			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", complaintAttendDtlVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_req_id", complaintAttendDtlVO_p.getStrReqId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_attend_id", complaintAttendDtlVO_p.getStrAttendId().equals("") ? "0" : complaintAttendDtlVO_p.getStrAttendId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", complaintAttendDtlVO_p.getStrHospitalCode(),4);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5); // 1 for
																	// varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6); // 2 for
																		// Cursor

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			/* Sets The WebRowSet in ItemTypeMstVO */
			complaintAttendDtlVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"ComplaintAttendDtlDAO.getData(ComplaintAttendDtlVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}
	
	
	public static void setNewAttendId(ComplaintAttendDtlVO complaintAttendDtlVO_p,
			HisDAO hisDAO_p) throws Exception {
		
		final String strNewAttendId;

		final String strFuncName = "{?= call BMED_FUNCTION.GEN_ATTENDED_ID(?,?,?)}";

		final int nFuncIndex;
		try {

			nFuncIndex = hisDAO_p.setFunction(strFuncName);

			HisUtil.replaceNullValueWithEmptyString(complaintAttendDtlVO_p);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setFuncInValue(nFuncIndex, 2,complaintAttendDtlVO_p.getStrMode()); 
			hisDAO_p.setFuncInValue(nFuncIndex, 3,complaintAttendDtlVO_p.getStrHospitalCode()); 
			hisDAO_p.setFuncInValue(nFuncIndex, 4,complaintAttendDtlVO_p.getStrReqId()); 

			hisDAO_p.setFuncOutValue(nFuncIndex, 3);

			/* Executing Procedure */
			hisDAO_p.executeFuncForNumeric(nFuncIndex);
			

			/* Getting function output */
			strNewAttendId = hisDAO_p.getFuncNumeric(nFuncIndex);

			
			/* Sets The WebRowSet in ItemTypeMstVO */
			complaintAttendDtlVO_p.setStrAttendId(strNewAttendId);

		} catch (Exception exception) {
			throw new Exception(
					"ComplaintAttendDtlDAO.setNewAttendId(ComplaintAttendDtlVO,HisDAO)-->"
							+ exception.getMessage());
		}
		

	}
	
}
