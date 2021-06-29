package bmed.dao;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HemtComplaintApprovalDtlVO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

public class HemtComplaintApprovalDtlDAO {
	/*
	 * To get data
	 * 
	 * @param hemtComplaintApprovalDtlVO_p the HemtComplaintApprovalDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void getData(HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO_p,HisDAO hisDAO_p)throws Exception {

		final String strproc_name = "{CALL PKG_BMED_VIEW.proc_complaint_approval_dtl(?,?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(hemtComplaintApprovalDtlVO_p);

			/* Setting and Registering In and Out Parameters */

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",	hemtComplaintApprovalDtlVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_req_id",hemtComplaintApprovalDtlVO_p.getStrReqId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",	hemtComplaintApprovalDtlVO_p.getStrHospitalCode(),3);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4); // 1 
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5); // 2

			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No farther processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			hemtComplaintApprovalDtlVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"ComplaintRequestDtlDAO.getData(ComplaintRequestDtlVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}
	/*
	 * To insert or update data
	 * 
	 * @param hemtComplaintApprovalDtlVO_p the HemtComplaintApprovalDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void save(
			HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO_p,
			HisDAO hisDAO_p) throws Exception {
		/* Total Variable 20 */// Insertion into the table
								// HEMT_COMPLAINT_APPROVAL_DTL
		final String strproc_name = "{CALL  pkg_bmed_dml.proc_hemt_complaint_app_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)}";
		final int nProcedureIndex;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
			HisUtil.replaceNullValueWithEmptyString(hemtComplaintApprovalDtlVO_p);
		
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",	hemtComplaintApprovalDtlVO_p.getStrMode(),1); // 1

			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REQ_ID",	hemtComplaintApprovalDtlVO_p.getStrReqId(),2); // 2
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_HOSPITAL_CODE",hemtComplaintApprovalDtlVO_p.getStrHospitalCode(),3); // 3
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_SLNO",	hemtComplaintApprovalDtlVO_p.getStrSlNo(),4); // 4
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_REQ_TYPE",	hemtComplaintApprovalDtlVO_p.getStrReqType(),5); // 5
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_IS_PREVENTIVE",	hemtComplaintApprovalDtlVO_p.getStrIsPreventive(),6); // 6
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_IS_WORKING",	hemtComplaintApprovalDtlVO_p.getStrIsWorking(),7); // 7
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ENGG_ITEM_TYPE_ID",hemtComplaintApprovalDtlVO_p.getStrEnggItemTypeId(),8); // 8
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ENGG_ITEM_SUB_TYPE_ID",hemtComplaintApprovalDtlVO_p.getStrEnggItemSubTypeId(),9); // 9
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_EMP_NAME",hemtComplaintApprovalDtlVO_p.getStrEmpName(),10); // 10
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_APPROVED_BY",hemtComplaintApprovalDtlVO_p.getStrApprovedBy(),11); // 11
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_APPROVED_DATE",hemtComplaintApprovalDtlVO_p.getStrApprovedDate(),12); // 12
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_APPROVAL_STATUS",	hemtComplaintApprovalDtlVO_p.getStrApprovalStatus(),13); // 13
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_EMP_ID",	hemtComplaintApprovalDtlVO_p.getStrEmpId(),14); // 14
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_DEPT_ID",hemtComplaintApprovalDtlVO_p.getStrDeptId(),15); // 15
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GSTR_REMARKS",hemtComplaintApprovalDtlVO_p.getStrRemarks(),16); // 16
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GDT_ENTRY_DATE", "",17); // Hard
																				// Coded
																				// Value
																				// //17
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_ISVALID",	hemtComplaintApprovalDtlVO_p.getStrIsValid(),18); // 18
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_SEATID",	hemtComplaintApprovalDtlVO_p.getStrSeatId(),19); // 19

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,20); // 20

			/* Executing Procedure */
			hisDAO_p.execute(nProcedureIndex, 1);
		} catch (Exception exception) {
			throw new Exception("HemtItemMcDtlDAO.getItemBrandCombo(HemtItemMcDtlVO)-->"+ exception.getMessage());
		}
	}

	/*
	 * To modify data
	 * 
	 * @param hemtComplaintApprovalDtlVO_p the HemtComplaintApprovalDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void modify(
			HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO_p,
			HisDAO hisDAO_p) {

	}

	/*
	 * To delete data
	 * 
	 * @param hemtComplaintApprovalDtlVO_p the HemtComplaintApprovalDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void delete(
			HemtComplaintApprovalDtlVO hemtComplaintApprovalDtlVO_p,
			HisDAO hisDAO_p) {

	}

}
