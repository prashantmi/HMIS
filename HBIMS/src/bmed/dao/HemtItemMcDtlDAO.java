package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HemtItemMcDtlVO;

public class HemtItemMcDtlDAO {
	/**
	 * 
	 * @param hemtItemMcDtlVO_p
	 *            the HemtItemMcDtlVO
	 * @param hisDAO_p
	 *            the HisDAO
	 * 
	 * @throws Exception
	 */
	public static void getPrevMantDtl(HemtItemMcDtlVO hemtItemMcDtlVO_p,
			HisDAO hisDAO_p) throws Exception {
		final String strproc_name = "{CALL PKG_BMED_VIEW.proc_hemt_item_mc_dtl(?,?,?,?,?, ?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(hemtItemMcDtlVO_p);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					hemtItemMcDtlVO_p.getStrMode(), 1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_id",
					hemtItemMcDtlVO_p.getStrItemId().equals("") ? "0"
							: hemtItemMcDtlVO_p.getStrItemId(), 2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_batch_no",
					hemtItemMcDtlVO_p.getStrBatchNo().equals("") ? "0"
							: hemtItemMcDtlVO_p.getStrBatchNo(), 3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_item_SlNo",
					hemtItemMcDtlVO_p.getStrItemSlNo().equals("") ? "0"
							: hemtItemMcDtlVO_p.getStrItemSlNo(), 4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_sl_No",
					hemtItemMcDtlVO_p.getStrSlNo().equals("") ? "0"
							: hemtItemMcDtlVO_p.getStrSlNo(), 5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code",
					hemtItemMcDtlVO_p.getStrHospCode(), 6);
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
			hemtItemMcDtlVO_p.setWrsMCDetails(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"HemtItemMcDtlDAO.getItemBrandCombo(HemtItemMcDtlVO)-->"
							+ exception.getMessage());
		}

	}

	/**
	 * 
	 * @param hemtItemMcDtlVO_p
	 *            the HemtItemMcDtlVO
	 * @param hisDAO_p
	 *            the HisDAO
	 * 
	 * @throws Exception
	 */
	public static void insert(HemtItemMcDtlVO hemtItemMcDtlVO_p, HisDAO hisDAO_p)
			throws Exception {
		/* Total Variable 42 */
		final String strproc_name = "{CALL  pkg_bmed_dml.proc_hemt_item_mc_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)}";
		final int nProcedureIndex;
		try {
			// System.out.println("p_HEMNUM_ITEM_ID::"+hemtItemMcDtlVO_p.getStrItemId());
			// System.out.println("p_HEMSTR_BATCH_NO::"+hemtItemMcDtlVO_p.getStrBatchNo());
			// System.out.println("p_HEMNUM_ITEM_SL_NO::"+hemtItemMcDtlVO_p.getStrItemSlNo());
			// System.out.println("p_HEMNUM_SL_NO::"+hemtItemMcDtlVO_p.getStrSlNo());
			// System.out.println("p_GNUM_HOSPITAL_CODE:::"+hemtItemMcDtlVO_p.getStrHospCode());

			// Here We Call Utility method to fill Null value with Empty String
			HisUtil.replaceNullValueWithEmptyString(hemtItemMcDtlVO_p);

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					hemtItemMcDtlVO_p.getStrMode(),1); // 1
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_MC_TYPE",
					hemtItemMcDtlVO_p.getStrMcType(),2); // 2
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ITEM_SL_NO",
					hemtItemMcDtlVO_p.getStrItemSlNo(),3); // 3
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ITEM_ID",
					hemtItemMcDtlVO_p.getStrItemId(),4); // 4
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_BATCH_NO",
					hemtItemMcDtlVO_p.getStrBatchNo(),5); // 5
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_MANUF_SL_NO",
					hemtItemMcDtlVO_p.getStrManufctSlNo(),6); // 6
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_SL_NO",
					hemtItemMcDtlVO_p.getStrSlNo(),7); // Enter By Function //7
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_TERM_N_CON",
					hemtItemMcDtlVO_p.getStrTermsCond(),8); // 8
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_IS_ITEM",
					hemtItemMcDtlVO_p.getStrIsItem(),9); // 9
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ROUTINE_VISIT",
					hemtItemMcDtlVO_p.getStrRoutineFreq(),10); // 10
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_MC_NAME",
					hemtItemMcDtlVO_p.getStrMcName(),11); // 11
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_BREAK_VISIT",
					"",12);// Dummy //12
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_MANUF_ID",
					hemtItemMcDtlVO_p.getStrManufactId(),13); // 13
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_RESPONSE_TIME",
					hemtItemMcDtlVO_p.getStrResponseTime(),14); // Hard Coded
																	// Value
																	// //14
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_COST",
					hemtItemMcDtlVO_p.getStrCost(),15); // 15
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_PENALTY_CON",
					hemtItemMcDtlVO_p.getStrPenaltyCond(),16); // 16
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GDT_ENTRY_DATE", "",17); // Hard
																					// Coded
																					// in
																					// Proc
																					// //17
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_ISVALID",
					hemtItemMcDtlVO_p.getStrIsValid(),18); // Hard Coded in
															// Proc //18
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_SEAT_ID",
					hemtItemMcDtlVO_p.getStrSeatId(),19); // 19
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GNUM_HOSPITAL_CODE",
					hemtItemMcDtlVO_p.getStrHospCode(),20); // 20
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_ROUTINE_FREQ",
					hemtItemMcDtlVO_p.getStrRoutineFreq(),21); // 21
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_FREQ_UNIT",
					hemtItemMcDtlVO_p.getStrFrqUnit(),22); // 22
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_RES_TIME_UNIT",
					hemtItemMcDtlVO_p.getStrResponseTimeUnit(),23); // 23
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMDT_START_DATE",
					hemtItemMcDtlVO_p.getStrStarDate(),24); // 24
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMDT_END_DATE",
					hemtItemMcDtlVO_p.getStrEndDate(),25); // 25
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_TENDER_NO",
					hemtItemMcDtlVO_p.getStrTenderNo(),26); // 26
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HPURNUM_UPLOAD_NO",
					hemtItemMcDtlVO_p.getStrUploadNo().equals("") || hemtItemMcDtlVO_p.getStrUploadNo()==null?"0":hemtItemMcDtlVO_p.getStrUploadNo(),27); // Enter By
																// Function //27
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HPURSTR_DOC_REF_NO",
					hemtItemMcDtlVO_p.getStrDocRefNo()==null || hemtItemMcDtlVO_p.getStrDocRefNo().equals("")?"0":hemtItemMcDtlVO_p.getStrDocRefNo(),28); // 28
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMDT_TENDER_DATE",
					hemtItemMcDtlVO_p.getStrTenderDate(),29); // 29
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HPURDT_DOC_REF_DATE",
					hemtItemMcDtlVO_p.getStrDocrefDate(),30); // Hard Coded in
																// Proc //30
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_ORDER_NO",
					hemtItemMcDtlVO_p.getStrOrederNo(),31); // 31
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_CANCEL_SEATID",
					hemtItemMcDtlVO_p.getStrSeatId(),32); // 32
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMDT_ORDER_DATE",
					hemtItemMcDtlVO_p.getStrOrderDate(),33); // 33
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_HEMDT_FINANCIAL_START_YEAR",
					hemtItemMcDtlVO_p.getStrFinancialStartYear(),34); // 34
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GDT_EFFECTIVE_FROM",
					hemtItemMcDtlVO_p.getStrEffFrom(),35); // 35
			hisDAO_p.setProcInValue(nProcedureIndex, "p_GSTR_REMARKS",
					hemtItemMcDtlVO_p.getStrRemarks(),36); // 36
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_HEMDT_FINANCIAL_END_YEAR",
					hemtItemMcDtlVO_p.getStrFinancialEndYear(),37); // 37
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_IS_RENEWED",
					hemtItemMcDtlVO_p.getStrIsReNew(),38); // 38
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMNUM_CANCEL_ID",
					hemtItemMcDtlVO_p.getStrCancelId(),39);// 39
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMDT_CANCEL_DATE",
					hemtItemMcDtlVO_p.getStrCancelDate(),40);// 40
			hisDAO_p.setProcInValue(nProcedureIndex, "p_HEMSTR_CANCEL_REMARKS",
					hemtItemMcDtlVO_p.getStrCancelRemarks(),41);// 41

			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_p_warehouseid",
					hemtItemMcDtlVO_p.getStrDeptId(),42);// 41
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_c_warehouseid",
					hemtItemMcDtlVO_p.getStrStoreId(),43);// 41
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_enggitemtypeid",
					hemtItemMcDtlVO_p.getStrEnggItemTypeId(),44);// 41
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_hemnum_enggitemsubtypeid",
					hemtItemMcDtlVO_p.getStrEnggItemSubTypeId(),45);// 41
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_hemnum_equipmentitemid", "0",46);// 41
			hisDAO_p.setProcInValue(nProcedureIndex, "p_companycontactno",
					//hemtItemMcDtlVO_p.getStrManufacturerName()==null || hemtItemMcDtlVO_p.getStrManufacturerName().equals("")?"0":hemtItemMcDtlVO_p.getStrManufacturerName(),47); // 41
					"0",47); // 41
			hisDAO_p.setProcInValue(nProcedureIndex, "p_companymailid", "NA",48);// 41
			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,49); // varchar
																		// //42

			/* Executing Procedure */
			hisDAO_p.execute(nProcedureIndex, 1);

		} catch (Exception exception) {

			throw new Exception(
					"HemtItemMcDtlDAO.getItemBrandCombo(HemtItemMcDtlVO)-->"
							+ exception.getMessage());
		}

	}

}
