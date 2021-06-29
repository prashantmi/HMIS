package bmed.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import bmed.transactions.vo.ItemMaintContractDtlsTransVO;
import bmed.vo.ItemMaintContractDtlsVO;

public class ItemMaintContractDtlsTransDAO {

	public static void getBmedHospitalsCombo(ItemMaintContractDtlsTransVO vo,
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
			exception.printStackTrace();
			throw new Exception(
					"GbltDepartmentMstDAO.getDepartmentCombo(ItemWarrantyDtlsTransVO)-->"
							+ exception.getMessage());
		}

	}

	public static void getItemBrandName(ItemMaintContractDtlsVO vo) {

		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {

			dao = new HisDAO("bmed", "ItemMaintContractDtlsDAO");
			strproc_name = "{call PKG_BMED_VIEW.proc_storeitem_list(?,?,?,?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", "1");
			dao.setProcInValue(nprocIndex, "store_id", vo.getStrStoreId());
			dao.setProcInValue(nprocIndex, "item_id", vo.getStrItemNameId());
			dao.setProcInValue(nprocIndex, "cat_no", "0");
			dao.setProcInValue(nprocIndex, "group_id", "0");
			dao.setProcInValue(nprocIndex, "sub_group_id", "0");
			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode());

			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr != null && !strerr.equals("")) {
				throw new Exception("Data Base Error:" + strerr);
			}
			vo.setStrItemBrandComboWS(wb);
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

	public static void getEquipmentNameCombo(ItemMaintContractDtlsTransVO vo) {
		String strproc_name = "";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet wb = null;
		try {
			dao = new HisDAO("bmed", "ItemMaintContractDtlsDAO");
			strproc_name = "{call PKG_BMED_VIEW.proc_storeitem_list(?,?,?,?,?,?,?,?,?)}";
			nprocIndex = dao.setProcedure(strproc_name);

			dao.setProcInValue(nprocIndex, "modeval", vo.getStrMode());
			dao.setProcInValue(nprocIndex, "store_id", vo.getStrDeptCode());
			dao.setProcInValue(nprocIndex, "item_id", "0");
			dao.setProcInValue(nprocIndex, "cat_no", "0");

			dao.setProcInValue(nprocIndex, "group_id", vo.getStrGroupId());
			dao.setProcInValue(nprocIndex, "sub_group_id", "0");

			dao
					.setProcInValue(nprocIndex, "hosp_code", vo
							.getStrHospitalCode());
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			wb = dao.getWebRowSet(nprocIndex, "resultset");

			if (strerr.equals("")) {
				vo.setWrsItemCombo(wb);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo
					.setStrMsgString("ItemWarrantyDtlsTransDAO.getEquipmentNameCombo() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}

	/**
	 * retrieves and executes view
	 */

	public static void viewRecord(ItemMaintContractDtlsVO vo) {

		String strErr = "";
		String strProcName = "";
		int nProcIndex = 0;

		HisDAO daoObj = null;
		WebRowSet web = null;

		try {
			daoObj = new HisDAO("MMS", "ItemMaintContractDtlsTransDAO");
			strProcName = "{call  pkg_bmed_view.proc_hemt_item_mc_dtl(?,?,?,?,?,?,?,?)}";

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode", "10");

			daoObj.setProcInValue(nProcIndex, "p_item_id", vo
					.getStrItemBrandId());
			daoObj.setProcInValue(nProcIndex, "p_batch_no", vo.getStrBatchNo());
			daoObj.setProcInValue(nProcIndex, "p_item_slno", vo
					.getStrItemSerialNo());
			daoObj.setProcInValue(nProcIndex, "p_sl_no", vo.getStrSerialNo());
			daoObj.setProcInValue(nProcIndex, "p_hospital_code", "101");

			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);

			daoObj.executeProcedure(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (!strErr.equals(""))
				throw new Exception(strErr);

			web = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (web != null) {

				while (web.next()) {

					vo.setStrStoreId(web.getString(1));
					vo.setStrItemNameId(web.getString(2));
					vo.setStrItemBrandId(web.getString(3));
					vo.setStrMantContractSuppId(web.getString(4));
					vo.setStrMantContractTypeId(web.getString(5));
					vo.setStrMaintenanceContractName(web.getString(6));
					vo.setStrTenderNo(web.getString(7));
					vo.setStrTenderDate(web.getString(8));
					vo.setStrOrderNo(web.getString(9));
					vo.setStrOrderDate(web.getString(10));
					vo.setStrFromDate(web.getString(11));
					vo.setStrToDate(web.getString(12));
					vo.setStrRoutineFrequency(web.getString(13));
					vo.setStrRoutineUnitId(web.getString(14));
					vo.setStrResponseTime(web.getString(15));
					vo.setStrResponseTimeUnitId(web.getString(16));
					vo.setStrMaintenanceCost(web.getString(17));
					vo.setStrRemarks(web.getString(18));
					vo.setStrTermsAndCond(web.getString(19));
					vo.setStrPeneltyCond(web.getString(20));
					vo.setStrUploadFileId(web.getString(21));
					vo.setStrDocRefDate(web.getString(22));
					vo.setStrHospitalCode(web.getString(23));
					vo.setStrDeptId(web.getString(24));
					vo.setStrEnggItemTypeId(web.getString(25));
					vo.setStrEnggItemSubTypeId(web.getString(26));
					vo.setStrCompanyContactNo(web.getString(27));
					vo.setStrCompanyMailId(web.getString(28));
					vo.setStrBatchNo(web.getString(29));
					vo.setStrDocRefNo(web.getString(30));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			vo
					.setStrMsgString("ItemMaintContractDtlsTransDAO.viewRecord() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

}
