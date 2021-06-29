package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.SparePartStatusMstVO;

public class SparePartStatusMstDAO {
	public static void save(SparePartStatusMstVO sparePartStatusMstVO_p,
			HisDAO hisDAO_p) throws Exception {

		/* Total Parameter 21. 1 out, 20 in parameter */

	//	final String strproc_name = "{CALL  PKG_BMED_DML.PROC_SPARE_PART_STATUS_MST(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?)}";
	//	final int nProcedureIndex;

		try {

			// nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
			// HisUtil.replaceNullValueWithEmptyString(sparePartStatusMstVO_p);
			// //HisUtil.printStringFieldsOfVO(sparePartStatusMstVO_p);
			// /* Setting and Registering In and Out Parameters */
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
			// sparePartStatusMstVO_p.getStrMode());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_req_id",
			// sparePartStatusMstVO_p.getStrReqId());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_schedule_id",
			// sparePartStatusMstVO_p.getStrScheduleId());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_req_type",
			// sparePartStatusMstVO_p.getStrReqType());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",
			// sparePartStatusMstVO_p.getStrHospitalCode());
			// hisDAO_p.setProcInValue(nProcedureIndex,
			// "p_hemdt_comp_intemation",
			// sparePartStatusMstVO_p.getStrCompIntemation());
			// hisDAO_p.setProcInValue(nProcedureIndex,
			// "p_hemstr_vendor_contact_person",
			// sparePartStatusMstVO_p.getStrVendorContactPerson());
			// hisDAO_p.setProcInValue(nProcedureIndex,
			// "p_hemnum_vendor_comm_id",
			// sparePartStatusMstVO_p.getStrVendorCommId());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_hemstr_remarks",
			// sparePartStatusMstVO_p.getStrRemarks());
			// hisDAO_p.setProcInValue(nProcedureIndex,
			// "p_hemstr_vendor_contact_no",
			// sparePartStatusMstVO_p.getStrVendorContactNo());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_hemstr_prob_descrip",
			// sparePartStatusMstVO_p.getStrProbDescrip());
			// hisDAO_p.setProcInValue(nProcedureIndex,
			// "p_hemnum_service_engg_id",
			// sparePartStatusMstVO_p.getStrServiceEnggId());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_vendor_id",
			// sparePartStatusMstVO_p.getStrVendorId());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_expect_visit",
			// sparePartStatusMstVO_p.getStrExpectVisit());
			// hisDAO_p.setProcInValue(nProcedureIndex,
			// "p_hemdt_expected_visit",
			// sparePartStatusMstVO_p.getStrExpectedVisitDate());
			// hisDAO_p.setProcInValue(nProcedureIndex,
			// "p_hemnum_expect_visit_unit",
			// sparePartStatusMstVO_p.getStrExpectVisitUnitId());
			// hisDAO_p.setProcInValue(nProcedureIndex,
			// "p_hemstr_solution_provided",
			// sparePartStatusMstVO_p.getStrSolutionProvided());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_status",
			// sparePartStatusMstVO_p.getStrStatus());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",
			// sparePartStatusMstVO_p.getStrSeatId());
			// hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",
			// sparePartStatusMstVO_p.getStrIsValid());

		//	hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1);

			/* Executing Procedure */
		//	hisDAO_p.execute(nProcedureIndex, 1);

		} catch (Exception exception) {
			throw new Exception(
					"SparePartStatusMstDAO.save(HemtItemMcDtlVO)-->"
							+ exception.getMessage());
		}
	}

	public static void getSparePartStatusCombo(SparePartStatusMstVO sparePartStatusMstVO_p,
			HisDAO hisDAO_p) throws Exception {

		final String strProcName = "{CALL PKG_BMED_VIEW.PROC_SPARE_PART_STATUS_MST_CMB(?,?,?,?,?)}";

		final int nProcedureIndex;
		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(sparePartStatusMstVO_p);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					sparePartStatusMstVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_status_id",
					sparePartStatusMstVO_p.getStrStatusId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					"101",3);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,4);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,5);

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
			sparePartStatusMstVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"SparePartStatusMstDAO.getData(SparePartStatusMstVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}

}
