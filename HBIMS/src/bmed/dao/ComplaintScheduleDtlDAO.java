package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.ComplaintScheduleDtlVO;

public class ComplaintScheduleDtlDAO {
	public static void save(ComplaintScheduleDtlVO complaintScheduleDtlVO_p,
			HisDAO hisDAO_p) throws Exception {

		/* Total Parameter 21. 1 out, 20 in parameter */

		final String strproc_name = "{CALL  PKG_BMED_DML.PROC_COMPLAINT_SCHEDULE_DTL(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?)}";
		final int nProcedureIndex;

		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
			HisUtil.replaceNullValueWithEmptyString(complaintScheduleDtlVO_p);
			//HisUtil.printStringFieldsOfVO(complaintScheduleDtlVO_p);
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					complaintScheduleDtlVO_p.getStrMode(),1); //1
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_req_id",
					complaintScheduleDtlVO_p.getStrReqId(),2);//2
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_schedule_id",
					complaintScheduleDtlVO_p.getStrScheduleId(),3);//3
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_req_type",
					complaintScheduleDtlVO_p.getStrReqType(),4);//4
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",
					complaintScheduleDtlVO_p.getStrHospitalCode(),5);//5
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemdt_comp_intemation",
					complaintScheduleDtlVO_p.getStrCompIntemation(),6);//6
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_hemstr_vendor_contact_person",
					complaintScheduleDtlVO_p.getStrVendorContactPerson(),7);//7
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_vendor_comm_id",
					complaintScheduleDtlVO_p.getStrVendorCommId(),8);//8
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemstr_remarks",
					complaintScheduleDtlVO_p.getStrRemarks(),9);//9
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_hemstr_vendor_contact_no",
					complaintScheduleDtlVO_p.getStrVendorContactNo(),10);//10
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemstr_prob_descrip",
					complaintScheduleDtlVO_p.getStrProbDescrip(),11);//11
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_hemnum_service_engg_id",
					complaintScheduleDtlVO_p.getStrServiceEnggId(),12);//12
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_vendor_id",
					complaintScheduleDtlVO_p.getStrVendorId(),13);//13
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_expect_visit",
					complaintScheduleDtlVO_p.getStrExpectVisit(),14);//14
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemdt_expected_visit",
					complaintScheduleDtlVO_p.getStrExpectedVisitDate(),15);//15
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_hemnum_expect_visit_unit",
					complaintScheduleDtlVO_p.getStrExpectVisitUnitId(),16);//16
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_hemstr_solution_provided",
					complaintScheduleDtlVO_p.getStrSolutionProvided(),17);//17
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_status",
					complaintScheduleDtlVO_p.getStrStatus(),18);//18
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",
					complaintScheduleDtlVO_p.getStrSeatId(),19);//19
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",
					complaintScheduleDtlVO_p.getStrIsValid(),20);//20

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,21); //21

			/* Executing Procedure */
			hisDAO_p.execute(nProcedureIndex, 1);
			

		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(
					"ComplaintScheduleDtlDAO.save(HemtItemMcDtlVO)-->"
							+ exception.getMessage());
		}
	}

	public static void getData(ComplaintScheduleDtlVO complaintScheduleDtlVO_p,
			HisDAO hisDAO_p) throws Exception {

		final String strProcName = "{call pkg_bmed_view.proc_complaint_schedule_dtl(?,?,?,?,?, ?)}";

		final int nProcedureIndex;
		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strProcName);
			
			HisUtil.replaceNullValueWithEmptyString(complaintScheduleDtlVO_p);
			
			
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					complaintScheduleDtlVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_req_id",
					complaintScheduleDtlVO_p.getStrReqId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_schedule_id",
					complaintScheduleDtlVO_p.getStrScheduleId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					complaintScheduleDtlVO_p.getStrHospitalCode(),4);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,5);
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,6);

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
			complaintScheduleDtlVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"ComplaintScheduleDtlDAO.getData(ComplaintScheduleDtlVO,HisDAO)-->"
							+ exception.getMessage());
		}

	}
	
	public static void setNewScheduleId(ComplaintScheduleDtlVO complaintScheduleDtlVO_p,
			HisDAO hisDAO_p) throws Exception {
		
		final String strNewScheduleId;

		final String strFuncName = "{?= call BMED_FUNCTION.GEN_SCHEDULE_ID(?,?,?)}";

		final int nFuncIndex;
		try {

			nFuncIndex = hisDAO_p.setFunction(strFuncName);

			HisUtil.replaceNullValueWithEmptyString(complaintScheduleDtlVO_p);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setFuncInValue(nFuncIndex, 2,complaintScheduleDtlVO_p.getStrMode()); 
			hisDAO_p.setFuncInValue(nFuncIndex, 3,complaintScheduleDtlVO_p.getStrHospitalCode()); 
			hisDAO_p.setFuncInValue(nFuncIndex, 4,complaintScheduleDtlVO_p.getStrReqId()); 

			hisDAO_p.setFuncOutValue(nFuncIndex, 3);

			/* Executing Procedure */
			hisDAO_p.executeFuncForNumeric(nFuncIndex);
			

			/* Getting function output */
			strNewScheduleId = hisDAO_p.getFuncNumeric(nFuncIndex);

			
			/* Sets The WebRowSet in ItemTypeMstVO */
			complaintScheduleDtlVO_p.setStrScheduleId(strNewScheduleId);

		} catch (Exception exception) {
			throw new Exception(
					"ComplaintScheduleDtlDAO.setNewScheduleId(complaintScheduleDtlVO_p)-->"
							+ exception.getMessage());
		}
		

	}
	
}
