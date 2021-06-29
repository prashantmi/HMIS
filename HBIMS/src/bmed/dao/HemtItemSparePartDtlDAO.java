package bmed.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import bmed.vo.HemtItemSparePartDtlVO;

/**
 * @author Vivek Aggarwal Creation Date:- 12-May-2011 Modifying Date:-
 *         12-May-2011 Used For:- Team Lead By:- Module:- BMED(HIS Project)
 * 
 */
public class HemtItemSparePartDtlDAO {
	/*
	 * To get data
	 * 
	 * @param hemtItemSparePartDtlVO_p the HsttStoreCategoryMstVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void getData(HemtItemSparePartDtlVO hemtItemSparePartDtlVO_p,
			HisDAO hisDAO_p) throws Exception {
		final String strProcName = "{call pkg_bmed_view.proc_hemt_item_sapre_part_dtl(?,?,?,?,?, ?,?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strProcName);

			HisUtil.replaceNullValueWithEmptyString(hemtItemSparePartDtlVO_p);

			/* Setting and Registering In and Out Parameters Total 10 */

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					hemtItemSparePartDtlVO_p.getStrMode(),1);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_store_id",
					hemtItemSparePartDtlVO_p.getStrStoreId(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_item_id",
					hemtItemSparePartDtlVO_p.getStrItemId(),3);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemstr_item_sl_no",
					hemtItemSparePartDtlVO_p.getStrItemSlNo(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_spare_id",
					hemtItemSparePartDtlVO_p.getStrSpareId(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemstr_spare_sl_no",
					hemtItemSparePartDtlVO_p.getStrSpareSlNo(),6);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_sl_no",
					hemtItemSparePartDtlVO_p.getStrSlNo(),7);
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",
					hemtItemSparePartDtlVO_p.getStrHospitalCode(),8);

			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,9); // 1 for
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,10); // 2 for
																		// Cursor
			/* Executing Procedure */
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);
			// hisDAO_p.execute(nProcedureIndex, 1);

			/* Getting out parameters */
			strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
			webRowSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			/* If Database Error Occurs, No further processing is required. */
			if (strDbErr != null && !strDbErr.equals("")) {
				throw new Exception("Data Base Error:" + strDbErr);
			}

			/* Sets The WebRowSet in HemtItemSparePartDtlVO */
			hemtItemSparePartDtlVO_p.setWrsData(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"HemtItemSparePartDtlDAO.getData(hemtItemSparePartDtlVO_p,hisDAO_p)-->"
							+ exception.getMessage());
		}

	}

	/*
	 * To insert data
	 * 
	 * @param hemtItemSparePartDtlVO_p the HemtItemSparePartDtlVO
	 * 
	 * @param hisDAO_p the HisDAO
	 */
	public static void save(HemtItemSparePartDtlVO hemtItemSparePartDtlVO_p,
			HisDAO hisDAO_p) throws Exception {
		/* Total Variable 22 */
		final String strproc_name = "{CALL  pkg_bmed_dml.proc_hemt_item_spare_part_dtl(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?)}";
		final int nProcedureIndex;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			HisUtil.replaceNullValueWithEmptyString(hemtItemSparePartDtlVO_p);

			//HisUtil.printStringFieldsOfVO(hemtItemSparePartDtlVO_p);

			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode",
					hemtItemSparePartDtlVO_p.getStrMode(),1); // 1

			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_item_id",
					hemtItemSparePartDtlVO_p.getStrItemId(),2); // 2
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_store_id",
					hemtItemSparePartDtlVO_p.getStrStoreId(),3); // 3
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_hospital_code",
					hemtItemSparePartDtlVO_p.getStrHospitalCode(),4); // 4
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemstr_item_sl_no",
					hemtItemSparePartDtlVO_p.getStrItemSlNo(),5); // 5
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_spare_id",
					hemtItemSparePartDtlVO_p.getStrSpareId(),6); // 6
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemstr_spare_sl_no",
					hemtItemSparePartDtlVO_p.getStrSpareSlNo(),7); // 7
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_sl_no",
					hemtItemSparePartDtlVO_p.getStrSlNo(),8); // 8
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_hemstr_item_manuf_slno",
					hemtItemSparePartDtlVO_p.getStrItemManufSlno(),9); // 9
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_hemstr_spare_manuf_slno",
					hemtItemSparePartDtlVO_p.getStrSpareManufSlno(),10); // 10
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_vendor_id",
					hemtItemSparePartDtlVO_p.getStrVendorId(),11); // 11
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemdt_warranty_date",
					hemtItemSparePartDtlVO_p.getStrWarrantyDate(),12);// 12
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hstnum_warranty_upto",
					hemtItemSparePartDtlVO_p.getStrWarrantyUpto(),13); // 13
			hisDAO_p.setProcInValue(nProcedureIndex,
					"p_hstnum_warranty_upto_unit",
					hemtItemSparePartDtlVO_p.getStrWarrantyUptoUnit(),14); // 14
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemstr_specification",
					hemtItemSparePartDtlVO_p.getStrSpecification(),15); // 15
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_is_added",
					hemtItemSparePartDtlVO_p.getStrIsAdded(),16); // 16
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemnum_status",
					hemtItemSparePartDtlVO_p.getStrStatus(),17); // 17
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hemdt_action_date",
					hemtItemSparePartDtlVO_p.getStrActionDate(),18); // 18
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gdt_entry_date",
					hemtItemSparePartDtlVO_p.getStrEntryDate(),19); // 19
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_isvalid",
					hemtItemSparePartDtlVO_p.getStrIsvalid(),20); // 20
			hisDAO_p.setProcInValue(nProcedureIndex, "p_gnum_seatid",
					hemtItemSparePartDtlVO_p.getStrSeatid(),21); // 21

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,22); // varchar //22

			/* Executing Procedure */
			hisDAO_p.execute(nProcedureIndex, 1);
		} catch (Exception exception) {
			throw new Exception(
					"HemtItemSparePartDtlDAO.save(HemtItemSparePartDtlVO hemtItemSparePartDtlVO_p, HisDAO hisDAO_p)-->"
							+ exception.getMessage());
		}

	}

}