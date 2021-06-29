package bmed.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.transactions.vo.ItemWarrantyDtlsTransVO;

public class ItemWarrantyDtlsTransDAO {

	public static void getBmedHospitalsCombo(ItemWarrantyDtlsTransVO vo,
			HisDAO hisDAO_p) throws Exception {

		final String strproc_name = "{CALL PKG_BMED_VIEW.proc_hstt_store_cmb(?,?,?,?,?,?,?,?)}";

		final int nProcedureIndex;

		final String strDbErr;
		final WebRowSet webRowSet;
		try {

			nProcedureIndex = hisDAO_p.setProcedure(strproc_name);

			/* Setting and Registering In and Out Parameters */
			hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", vo.getStrMode());
			hisDAO_p.setProcInValue(nProcedureIndex, "p_seat_id", vo
					.getStrSeatId());
			hisDAO_p.setProcInValue(nProcedureIndex, "p_hospital_code", vo
					.getStrHospitalCode());
			hisDAO_p.setProcInValue(nProcedureIndex, "p_store_id", "0");
			hisDAO_p.setProcInValue(nProcedureIndex, "p_store_type_id", "0");
			hisDAO_p.setProcInValue(nProcedureIndex, "p_depart_id", "0");

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

			/* Sets The WebRowSet in ItemWarrantyDtlsTransVO */
			vo.setWrsDepartmentOptions(webRowSet);

		} catch (Exception exception) {
			throw new Exception(
					"GbltDepartmentMstDAO.getDepartmentCombo(ItemWarrantyDtlsTransVO)-->"
							+ exception.getMessage());
		}

	}

	/**
	 * retrieves and executes view
	 */

	public static void viewRecord(ItemWarrantyDtlsTransVO vo) {

		String strErr = "";
		String strProcName = "";
		int nProcIndex = 0;

		String strProcName2 = "";
		int nProcIndex2 = 0;

		String strProcName3 = "";
		int nProcIndex3 = 0;

		String strProcName4 = "";
		int nProcIndex4 = 0;

		HisDAO daoObj = null;
		WebRowSet web = null;
		WebRowSet warrantyWs = null;
		WebRowSet installWs = null;

		try {
			daoObj = new HisDAO("BMED", "ItemWarrantyDtlsTransDAO");
			strProcName = "{call  pkg_bmed_view.proc_hstt_warranty_dtl(?,?,?,?,?,?,?,?,?)}"; // 9
																								// parameters

			nProcIndex = daoObj.setProcedure(strProcName);
			//System.out.println("Hos Code::" + vo.getStrHospitalCode());
			//System.out.println("getStrItemNameId::" + vo.getStrItemNameId());
			//System.out.println("getStrBatchNo::" + vo.getStrBatchNo());
			//System.out.println("getStrItemSerialNo:" + vo.getStrItemSerialNo());
			//System.out.println("getStrSerialNo::" + vo.getStrSerialNo());
			//System.out.println("getStrItemBrandId::" + vo.getStrItemBrandId());

			daoObj.setProcInValue(nProcIndex, "p_mode", "8");
			daoObj.setProcInValue(nProcIndex, "p_item_id", vo
					.getStrItemNameId());
			daoObj.setProcInValue(nProcIndex, "p_hospital_code", vo
					.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "p_batch_no", vo.getStrBatchNo());
			daoObj.setProcInValue(nProcIndex, "p_item_sl_no", vo
					.getStrItemSerialNo());
			daoObj.setProcInValue(nProcIndex, "p_sl_no", vo.getStrSerialNo());
			daoObj.setProcInValue(nProcIndex, "p_itembrand_id", vo
					.getStrItemBrandId());
			// daoObj.setProcInValue(nProcIndex,
			// "p_hospital_code",vo.getStrHospitalCode());

			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			// daoObj.execute(nProcIndex, 1);
			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (!strErr.equals(""))
				throw new Exception(strErr);

			web = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (web != null) {

				while (web.next()) {
					vo.setStrDeptId(web.getString(1));
					vo.setStrStoreId(web.getString(2));
					vo.setStrEnggItemTypeId(web.getString(3));
					vo.setStrEnggItemSubTypeId(web.getString(4));
					vo.setStrItemNameId(web.getString(5));
					vo.setStrItemBrandId(web.getString(6));
					vo.setStrWarrantySuppId(web.getString(7));
					vo.setStrWarrantyStartDate(web.getString(8));
					vo.setStrWarrantyUpTo(web.getString(9));
					vo.setStrWarrantyId(web.getString(10));
					vo.setStrTenderNo(web.getString(11));
					vo.setStrTenderDate(web.getString(12));
					vo.setStrOrderNo(web.getString(13));
					vo.setStrOrderDate(web.getString(14));
					vo.setStrTermsAndCond(web.getString(15));
					vo.setStrRemarks(web.getString(16));
					vo.setStrUploadFileId(web.getString(17));
					vo.setstrDocRefDate(web.getString(18));
					vo.setStrHospitalCode(web.getString(19));
					vo.setStrBatchNo(web.getString(20));
					vo.setStrDocRefNo(web.getString(21));
				//	vo.setStrProgramName(web.getString(22));

				}// while loop ends here
			}// if condition closes here

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ItemWarrantyDtlsTransDAO.viewRecord() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void getItemBrandName(ItemWarrantyDtlsTransVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {
			dao = new HisDAO("bmed", "ItemWarrantyDtlsTransDAO");
			strproc_name = "{call PKG_BMED_VIEW.proc_storeitem_list(?,?,?,?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);
			System.out.println(vo.getStrStoreId());
			System.out.println(vo.getStrItemNameId());
			dao.setProcInValue(nprocIndex, "modeval", "1");
			dao.setProcInValue(nprocIndex, "store_id", vo.getStrStoreId());
			dao.setProcInValue(nprocIndex, "item_id", vo.getStrItemNameId());
			dao.setProcInValue(nprocIndex, "cat_no", "0");
			dao.setProcInValue(nprocIndex, "group_id", "0");
			dao.setProcInValue(nprocIndex, "sub_group_id", "0");
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode());
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				vo.setStrItemBrandComboWS(wb);

			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("ItemWarrantyDtlsTransDAO.getItemBrandName() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

}
