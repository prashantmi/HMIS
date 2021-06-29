package bmed.masters.dao;

import hisglobal.hisconfig.Config;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.ItemMaintenanceMstVO;
import hisglobal.transactionmgnt.HisDAO;

public class ItemMaintenanceMstDAO {
	/**
	 * Gets Department store.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getDeptStoreName(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.storeName.1");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 2, vo.getStrStoreId());
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());

			web = dao.executeQry(nqryIndex);
			while (web.next()) {
				vo.setStrStoreName(web.getString(1));
			}

		} catch (Exception e) {

			vo.setStrMsgString("ItemMaintenanceMstDAO.getDeptStoreName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getEnggItemTypeCmb(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.enggItemType.combo.2");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 2, Config.SUPER_USER_HOSPITAL_CODE);

			web = dao.executeQry(nqryIndex);

			vo.setWsEnggItemType(web);

		} catch (Exception e) {
			e.printStackTrace();

			vo.setStrMsgString("ItemMaintenanceMstDAO.getDeptStoreName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getMaintenanceNameCmb(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.maintenanceName.combo.4");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrStoreId());
			/*dao.setQryValue(nqryIndex, 3, vo.getStrItemBrandId());
			dao.setQryValue(nqryIndex, 4, vo.getStrBatchNo());
			dao.setQryValue(nqryIndex, 5, vo.getStrSerialNo());*/
			
			web = dao.executeQry(nqryIndex);

			vo.setWsMaintenanceCmb(web);

		} catch (Exception e) {

			vo.setStrMsgString("ItemMaintenanceMstDAO.getMaintenanceNameCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getUnitCmb(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.unitName.combo.5");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, "1");
			dao.setQryValue(nqryIndex, 2, Config.SUPER_USER_HOSPITAL_CODE);

			web = dao.executeQry(nqryIndex);

			vo.setWsUnitName(web);

		} catch (Exception e) {

			vo.setStrMsgString("ItemMaintenanceMstDAO.getMaintenanceNameCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getItemCatgCmb(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.itemCatg.combo.1");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrStoreId());
			dao.setQryValue(nqryIndex, 3, Config.SUPER_USER_HOSPITAL_CODE);

			web = dao.executeQry(nqryIndex);
			vo.setWsItemCatgName(web);

		} catch (Exception e) {

			e.printStackTrace();

			vo.setStrMsgString("ItemMaintenanceMstDAO.getItemCatgCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getItemNameCmb(ItemMaintenanceMstVO vo) {

		System.out.println("getItemNameCmb");
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");

			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.itemName.combo.60");
			nqryIndex = dao.setQuery(strquery);

			System.out.println("strquery::::" + strquery);
			System.out.println(vo.getStrStoreId());
			System.out.println(vo.getStrHospitalCode());
			System.out.println(vo.getStrItemCatgId());
			
			/*dao.setQryValue(nqryIndex, 1, vo.getStrStoreId());*/ // Its actually a  'Commented on 29-Oct-2015'
																// dept Code
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());

			dao.setQryValue(nqryIndex, 2, vo.getStrItemCatgId());

			System.out.println("nqryIndex::::" + nqryIndex);

			web = dao.executeQry(nqryIndex);
			vo.setWsItemName(web);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ItemMaintenanceMstDAO.getItemNameCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getStockDtl(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.stock.dtl.30");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrItemid());
			dao.setQryValue(nqryIndex, 3, vo.getStrItemCatgId());
			//dao.setQryValue(nqryIndex, 4, vo.getStrStoreId());
			web = dao.executeQry(nqryIndex);
			vo.setWsBatchName(web); // This Variable used to save Stock Details

		} catch (Exception e) {

			vo.setStrMsgString("ItemMaintenanceMstDAO.getBatchNoCmb() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getItemSlNoCmbBasisOfBatch(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");

			if (!vo.getStrItemCatgId().equals("10")) {
				strquery = bmed.qryHandler_bmed.getQuery(1,
						"itemMaintenanceMst.serialNo.combo.100");
			} else {
				strquery = bmed.qryHandler_bmed.getQuery(1,
						"itemMaintenanceMst.serialNo.combo.101");
			}

			nqryIndex = dao.setQuery(strquery);

			// dao.setQryValue(nqryIndex, 1, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrBatchNo());
			web = dao.executeQry(nqryIndex);
			vo.setWsItemSlNo(web);

		} catch (Exception e) {

			vo.setStrMsgString("ItemMaintenanceMstDAO.getItemSlNoCmbBasisOfBatch() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getItemSlNoCmbBasisOfItem(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");

			if (!vo.getStrItemCatgId().equals("10")) {
				strquery = bmed.qryHandler_bmed.getQuery(1,
						"itemMaintenanceMst.serialNo.combo.90");
			} else {
				strquery = bmed.qryHandler_bmed.getQuery(1,
						"itemMaintenanceMst.serialNo.combo.91");
			}

			nqryIndex = dao.setQuery(strquery);

			// dao.setQryValue(nqryIndex, 1, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrItemid());
			web = dao.executeQry(nqryIndex);
			vo.setWsItemSlNo(web);

		} catch (Exception e) {

			vo.setStrMsgString("ItemMaintenanceMstDAO.getItemSlNoCmbBasisOfItem() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getEnggItemSubTypeOnBasisOfEnggItemType(
			ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.enggItemSubType.combo.7");
			nqryIndex = dao.setQuery(strquery);

			// dao.setQryValue(nqryIndex, 1, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrEnggItemTypeId());
			web = dao.executeQry(nqryIndex);
			vo.setWsEnggItemSubType(web);

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ItemMaintenanceMstDAO.getEnggItemSubTypeOnBasisOfEnggItemType() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getLeftListBoxValue(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.availableTask.listbox.1");
			nqryIndex = dao.setQuery(strquery);

			// System.out.println("Hosp Code::::"+vo.getStrHospitalCode());
			// System.out.println("Main Id::::"+vo.getStrMaintenanceId());
			// System.out.println("Item Brand Id::::"+vo.getStrItemBrandId());
			// System.out.println("Batch No::::"+vo.getStrBatchNo());
			// System.out.println("Serial No::::"+vo.getStrSerialNo());
			// System.out.println("DeptId::::"+vo.getStrDeptID());

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrMaintenanceId());
			dao.setQryValue(nqryIndex, 3, vo.getStrItemBrandId());
			dao.setQryValue(nqryIndex, 4, vo.getStrBatchNo());
			dao.setQryValue(nqryIndex, 5, vo.getStrSerialNo());
			dao.setQryValue(nqryIndex, 6, vo.getStrDeptID());

			web = dao.executeQry(nqryIndex);
			vo.setWsLeftListBox(web);

		} catch (Exception e) {

			vo.setStrMsgString("ItemMaintenanceMstDAO.getLeftListBoxValue() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * Gets the component name.
	 * 
	 * @param vo
	 *            the vo
	 * 
	 * @return the component name
	 */
	public static void getRightListBoxValue(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex = 0;
		WebRowSet web = null;
		String strquery;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.selectedTask.listbox.2");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrMaintenanceId());
			dao.setQryValue(nqryIndex, 3, vo.getStrItemBrandId());
			dao.setQryValue(nqryIndex, 4, vo.getStrBatchNo());
			dao.setQryValue(nqryIndex, 5, vo.getStrSerialNo());
			dao.setQryValue(nqryIndex, 6, vo.getStrDeptID());

			web = dao.executeQry(nqryIndex);
			vo.setWsRightListBox(web);

		} catch (Exception e) {

			vo.setStrMsgString("ItemMaintenanceMstDAO.getRightListBoxValue() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;

		}
	}

	/**
	 * to insert the data.
	 * 
	 * @param vo
	 *            the vo
	 */

	public static void insertQuery(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nProcIndex1;
		int nProcIndex_U;
		String strProcName;
		String strProcName_U;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_ITEM_MAINTE_MST(?,?,?,?, ?,?,?,? ,?,?,?,? ,?,?,?,? ,?,?,?,? ,?,?,?,? )}"; // Total
																																// 23
																																// Values
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "1",1);
			dao.setProcInValue(nProcIndex_U, "strdeptid", vo.getStrStoreId(),2); // Variable
																				// Used
																				// as
																				// Dept
																				// Code
			dao.setProcInValue(nProcIndex_U, "strMaintenanceId",
					vo.getStrMaintenanceId(),3);
			dao.setProcInValue(nProcIndex_U, "strItemCatgId",
					vo.getStrItemCatgId(),4);
			dao.setProcInValue(nProcIndex_U, "strEnggItemTypeId",
					vo.getStrEnggItemTypeId(),5);
			dao.setProcInValue(nProcIndex_U, "strEnggItemSubTypeId",
					vo.getStrEnggItemSubTypeId(),6);
			dao.setProcInValue(nProcIndex_U, "strItemId", vo.getStrItemid(),7);
			dao.setProcInValue(nProcIndex_U, "strBatchNo", vo.getStrBatchNo(),8);
			dao.setProcInValue(nProcIndex_U, "strSerialNo", vo.getStrSerialNo(),9);
			dao.setProcInValue(nProcIndex_U, "strMaintenanceDesc",
					vo.getStrMaintenanceDesc(),10);
			dao.setProcInValue(nProcIndex_U, "strPreferTimeFrom",
					vo.getStrFromHour() + ":" + vo.getStrFromMin(),11);
			dao.setProcInValue(nProcIndex_U, "strPreferTimeTo",
					vo.getStrToHour() + ":" + vo.getStrToMin(),12);
			dao.setProcInValue(nProcIndex_U, "strMaintenancePeriod",
					vo.getStrMaintenancePeriod(),13);
			dao.setProcInValue(nProcIndex_U, "strUnitId", vo.getStrUnitId(),14);
			dao.setProcInValue(nProcIndex_U, "strMaintenancePeriodInDays",
					vo.getStrMaintenancePeriodInDays(),15);
			dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",
					vo.getStrEffectiveFrom(),16);
			dao.setProcInValue(nProcIndex_U, "strRemarks", vo.getStrRemarks(),17);
			dao.setProcInValue(nProcIndex_U, "strHospitalCode",
					vo.getStrHospitalCode(),18);
			dao.setProcInValue(nProcIndex_U, "strSeatId", vo.getStrSeatId(),19);
			dao.setProcInValue(nProcIndex_U, "strIsValid", "1",20);
			dao.setProcInValue(nProcIndex_U, "strReqType",
					vo.getStrComplaintFlg(),21);
			dao.setProcInValue(nProcIndex_U, "strWarrantySlNo", "0",22);
			dao.setProcInValue(nProcIndex_U, "strMcSlNo", "0",23);
			dao.setProcOutValue(nProcIndex_U, "err", 1,24);
			dao.execute(nProcIndex_U, 1);
			
			strProcName = "{call PKG_BMED_DML.PROC_HEMT_ITEM_MAINTE_TASK_MST(?,?,?,?,?,?,?,?,?,?,?,?)}";

			for (int i = 0, stopI = vo.getStrRightItemIds().length; i < stopI; i++) {

				// System.out.println("vo.getStrRightItemIds()[i]-"+vo.getStrRightItemIds()[i]);
				if (!vo.getStrRightItemIds()[i].equals("0")) {
					nProcIndex1 = dao.setProcedure(strProcName);

					dao.setProcInValue(nProcIndex1, "p_mode", "1",1);
					dao.setProcInValue(nProcIndex1, "strItemId",
							vo.getStrItemid(),2);
					dao.setProcInValue(nProcIndex1, "strdeptid",
							vo.getStrStoreId(),3); // Variable Used as Dept Code
					dao.setProcInValue(nProcIndex1, "strBatchNo",
							vo.getStrBatchNo(),4);
					dao.setProcInValue(nProcIndex1, "strSerialNo",
							vo.getStrSerialNo(),5);
					dao.setProcInValue(nProcIndex1, "strMaintenanceId",
							vo.getStrMaintenanceId(),6);
					dao.setProcInValue(nProcIndex1, "strTaskId",
							vo.getStrRightItemIds()[i],7);
					dao.setProcInValue(nProcIndex1, "strHospitalCode",
							vo.getStrHospitalCode(),8);
					dao.setProcInValue(nProcIndex1, "strEffectiveFrom",
							vo.getStrEffectiveFrom(),9);
					dao.setProcInValue(nProcIndex1, "strSeatId",
							vo.getStrSeatId(),10);
					dao.setProcInValue(nProcIndex1, "strIsValid", "1",11);
					dao.setProcOutValue(nProcIndex1, "err", 1,12);
					dao.execute(nProcIndex1, 1);
					//dao.executeProcedureByPosition(nProcIndex1);
					// System.out.println("strItemCatgId:::"+vo.getStrItemCatgId());
					// System.out.println("strEnggItemTypeId::::"+vo.getStrEnggItemTypeId());
					// System.out.println("strEnggItemSubTypeId::::"+vo.getStrEnggItemSubTypeId());
					// System.out.println("strItemId::::"+vo.getStrItemid());
					// System.out.println("strBatchNo:::"+vo.getStrBatchNo());
					// System.out.println("strSerialNo::::"+vo.getStrSerialNo());
					// System.out.println("strMaintenanceDesc::::"+vo.getStrMaintenanceDesc());
					// System.out.println("strFromHour::::"+vo.getStrFromHour());
					// System.out.println("To Hour::::"+vo.getStrToHour()+":"+vo.getStrToMin());
					// System.out.println("From Time::::"+vo.getStrFromHour()+":"+vo.getStrFromMin());
					// System.out.println("strMaintenancePeriod:::"+vo.getStrMaintenancePeriod());
					// System.out.println("strUnitId::::"+vo.getStrUnitId());
					// System.out.println("strMaintenancePeriodInDays::::"+vo.getStrMaintenancePeriodInDays());
					// System.out.println("strEffectiveFrom::::"+vo.getStrEffectiveFrom());
					// System.out.println("strRemarks::::"+vo.getStrRemarks());

				}
			}

			synchronized (dao) {
				dao.fire();

			}
			vo.setStrMsgType("0");

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("-->ItemMaintenanceMstDAO.insert()-->"
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
	 * Chk duplicate.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void chkDuplicate(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = new String();

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.duplicate.1");
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getStrStoreId()); // Use as
																// Department ID
			dao.setQryValue(nqryIndex, 2, vo.getStrItemid());
			dao.setQryValue(nqryIndex, 3, vo.getStrBatchNo());
			dao.setQryValue(nqryIndex, 4, vo.getStrSerialNo());
			dao.setQryValue(nqryIndex, 5, vo.getStrMaintenanceId());
			dao.setQryValue(nqryIndex, 6, vo.getStrHospitalCode());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			if (ncount == 0) {

				vo.setBExistStatus(true);
			} else {
				vo.setBExistStatus(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ItemMaintenanceMstDAO.chkDuplicate() --> "
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
	 * view.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void view(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nqryIndex1 = 0;
		WebRowSet wb2 = null;
		String strquery1;

		try {
			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strquery1 = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.view.2");
			nqryIndex1 = dao.setQuery(strquery1);

			// System.out.println("Hosp Code::::"+vo.getStrHospitalCode());
			// System.out.println("Store ::::"+vo.getStrStoreId());
			// System.out.println("strItemId::::"+vo.getStrItemid());
			// System.out.println("strBatchNo:::"+vo.getStrBatchNo());
			// System.out.println("strSerialNo::::"+vo.getStrSerialNo());
			// System.out.println("Maintinance Id::::"+vo.getStrMaintenanceId());

			dao.setQryValue(nqryIndex1, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex1, 2, vo.getStrMaintenanceId());
			dao.setQryValue(nqryIndex1, 3, vo.getStrStoreId());
			dao.setQryValue(nqryIndex1, 4, vo.getStrItemid());
			dao.setQryValue(nqryIndex1, 5, vo.getStrSerialNo());
			dao.setQryValue(nqryIndex1, 6, vo.getStrBatchNo());

			wb2 = dao.executeQry(nqryIndex1);
			while (wb2.next()) {

				vo.setStrMaintenancePeriod(wb2.getString(1));
				vo.setStrMaintenancePeriodInDays(wb2.getString(2));
				vo.setStrPreferTimeFrom(wb2.getString(3));
				vo.setStrPreferTimeTo(wb2.getString(4));
				vo.setStrMaintenanceDesc(wb2.getString(5));
				vo.setStrEffectiveFrom(wb2.getString(6));
				vo.setStrRemarks(wb2.getString(7));
				vo.setStrIsValid(wb2.getString(8));

			}

			vo.setStrMsgType("0");
		} catch (Exception e) {

			vo.setStrMsgString("ItemMaintenanceMstDAO.chkDuplicate() --> "
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
	 * Modify.
	 * 
	 * @param vo
	 *            the vo
	 */
	public static void modify(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;

		int nQueryIndex = 0;
		int nQueryIndex1 = 0;
		int nQueryIndex2 = 0;
		int nQueryIndex4 = 0;

		WebRowSet web = null;
		WebRowSet web1 = null;
		WebRowSet web2 = null;
		WebRowSet web4 = null;

		String strTmp[] = null;
		String tmp[] = null;
		String strQuery, strQuery1, strQuery2, strQuery4;
		try {

			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.getMaintenanceName.1");
			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrMaintenanceId());
			web = dao.executeQry(nQueryIndex);

			if (web.next()) {
				vo.setStrMaintenanceCmb(web.getString(1));
			}

			strQuery1 = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.getItemName.2");
			nQueryIndex1 = dao.setQuery(strQuery1);

			dao.setQryValue(nQueryIndex1, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex1, 2, vo.getStrItemid());
			web1 = dao.executeQry(nQueryIndex1);

			if (web1.next()) {
				vo.setStrItemNameCmb(web1.getString(1));
			}

			strQuery2 = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.details.0");
			nQueryIndex2 = dao.setQuery(strQuery2);

			dao.setQryValue(nQueryIndex2, 1, vo.getStrStoreId());

			dao.setQryValue(nQueryIndex2, 2, vo.getStrItemid());
			dao.setQryValue(nQueryIndex2, 3, vo.getStrBatchNo());
			dao.setQryValue(nQueryIndex2, 4, vo.getStrSerialNo());
			dao.setQryValue(nQueryIndex2, 5, vo.getStrMaintenanceId());
			dao.setQryValue(nQueryIndex2, 6, vo.getStrHospitalCode());
			web2 = dao.executeQry(nQueryIndex2);

			if (web2.next()) {

				vo.setStrDtl(web2.getString(1));
			}

			strTmp = vo.getStrDtl().split("\\@");
			vo.setStrEnggItemTypeId(strTmp[0]);
			vo.setStrEnggItemSubTypeId(strTmp[2]);
			vo.setStrEnggItemSubTypeCmb(strTmp[3]);
			vo.setStrEnggItemTypeCmb(strTmp[1]);
			vo.setStrEffectiveFrom(strTmp[4]);
			vo.setStrIsValid(strTmp[5]);
			vo.setStrRemarks(strTmp[6]);

			tmp = strTmp[7].split("\\:");
			vo.setStrFromHour(tmp[0]);
			vo.setStrFromMin(tmp[1]);
			vo.setStrToHour(strTmp[8].split("\\:")[0]);
			vo.setStrToMin(strTmp[8].split("\\:")[1]);
			vo.setStrMaintenanceDesc(strTmp[9]);
			vo.setStrMaintenancePeriod(strTmp[10]);
			vo.setStrMaintenancePeriodUnit(strTmp[11]);
			vo.setStrAlertDuration(strTmp[12]);

			strQuery4 = bmed.qryHandler_bmed.getQuery(1,
					"itemMaintenanceMst.getDeptName.6");
			nQueryIndex4 = dao.setQuery(strQuery4);
			
			dao.setQryValue(nQueryIndex4, 1, vo.getStrStoreId());
			dao.setQryValue(nQueryIndex4, 2, vo.getStrHospitalCode());
			

			web4 = dao.executeQry(nQueryIndex4);

			if (web4.next()) {
				vo.setStrStoreName(web4.getString(1));

			}

		} catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("ItemMaintenanceMstDAO.modify() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			dao = null;
			web = null;
		}
	}

	/**
	 * This function is used to update record during click of modify.
	 * 
	 * @param vo
	 *            the vo
	 */

	public static void updateRecord(ItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		int nProcIndex1, nProcIndex2, nProcIndex_U;
		String strProcName, strProcName1, strProcName_U;
		try {

			dao = new HisDAO("bmed", "ItemMaintenanceMstDAO");
			strProcName_U = "{call PKG_BMED_DML.PROC_HEMT_ITEM_MAINTE_MST(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)}"; // Total
																															// 23
			nProcIndex_U = dao.setProcedure(strProcName_U);

			dao.setProcInValue(nProcIndex_U, "p_mode", "2",1);
			dao.setProcInValue(nProcIndex_U, "strdeptid", vo.getStrStoreId(),2);
			dao.setProcInValue(nProcIndex_U, "strMaintenanceId",vo.getStrMaintenanceId(),3);
			dao.setProcInValue(nProcIndex_U, "strItemCatgId", "0",4);
			dao.setProcInValue(nProcIndex_U, "strEnggItemTypeId", "0",5);

			dao.setProcInValue(nProcIndex_U, "strEnggItemSubTypeId", "0",6);
			dao.setProcInValue(nProcIndex_U, "strItemId", vo.getStrItemid(),7);
			dao.setProcInValue(nProcIndex_U, "strBatchNo", vo.getStrBatchNo(),8);
			dao.setProcInValue(nProcIndex_U, "strSerialNo", vo.getStrSerialNo(),9);
			dao.setProcInValue(nProcIndex_U, "strMaintenanceDesc",vo.getStrMaintenanceDesc(),10);
			
			dao.setProcInValue(nProcIndex_U, "strPreferTimeFrom",vo.getStrFromHour() + ":" + vo.getStrFromMin(),11);
			dao.setProcInValue(nProcIndex_U, "strPreferTimeTo",vo.getStrToHour() + ":" + vo.getStrToMin(),12);
			dao.setProcInValue(nProcIndex_U, "strMaintenancePeriod",vo.getStrMaintenancePeriod(),13);
			dao.setProcInValue(nProcIndex_U, "strUnitId", vo.getStrUnitId(),14);
			dao.setProcInValue(nProcIndex_U, "strMaintenancePeriodInDays",vo.getStrMaintenancePeriodInDays(),15);

			dao.setProcInValue(nProcIndex_U, "strEffectiveFrom",vo.getStrEffectiveFrom(),16);
			dao.setProcInValue(nProcIndex_U, "strRemarks", vo.getStrRemarks(),17);
			dao.setProcInValue(nProcIndex_U, "strHospitalCode",vo.getStrHospitalCode(),18);
			dao.setProcInValue(nProcIndex_U, "strSeatId", vo.getStrSeatId(),19);
			dao.setProcInValue(nProcIndex_U, "strIsValid", vo.getStrIsValid(),20);

			dao.setProcInValue(nProcIndex_U, "strreqtype","0",21);
			dao.setProcInValue(nProcIndex_U, "strWarrantySlNo", "0",22);
			dao.setProcInValue(nProcIndex_U, "strMcSlNo", "0",23);

			dao.setProcOutValue(nProcIndex_U, "err", 1,24);
			dao.execute(nProcIndex_U, 1);

			strProcName = "{call PKG_BMED_DML.PROC_HEMT_ITEM_MAINTE_TASK_MST(?,?,?,?,?, ?,?,?,?,?, ?,?)}";
			nProcIndex1 = dao.setProcedure(strProcName);

			dao.setProcInValue(nProcIndex1, "p_mode", "2",1);
			dao.setProcInValue(nProcIndex1, "strItemId", vo.getStrItemid(),2);
			dao.setProcInValue(nProcIndex1, "strdeptid", vo.getStrStoreId(),3);
			dao.setProcInValue(nProcIndex1, "strBatchNo", vo.getStrBatchNo(),4);
			dao.setProcInValue(nProcIndex1, "strSerialNo", vo.getStrSerialNo(),5);
			
			dao.setProcInValue(nProcIndex1, "strMaintenanceId",vo.getStrMaintenanceId(),6);
			dao.setProcInValue(nProcIndex1, "strTaskId", "0",7);
			dao.setProcInValue(nProcIndex1, "strHospitalCode",vo.getStrHospitalCode(),8);
			dao.setProcInValue(nProcIndex1, "strEffectiveFrom", "0",9);
			dao.setProcInValue(nProcIndex1, "strSeatId", "0",10);

			dao.setProcInValue(nProcIndex1, "strIsValid", "0",11);
			dao.setProcOutValue(nProcIndex1, "err", 1,12);
			
			dao.execute(nProcIndex1, 1);

			strProcName1 = "{call PKG_BMED_DML.PROC_HEMT_ITEM_MAINTE_TASK_MST(?,?,?,?,?, ?,?,?,?,?, ?,?)}";

			for (int i = 0, stopI = vo.getStrRightItemIds().length; i < stopI; i++) {

				// System.out.println("[In Case of Up date ]vo.getStrRightItemIds()[i]---->>>"+vo.getStrRightItemIds()[i]);
				if (!vo.getStrRightItemIds()[i].equals("0")) {
					nProcIndex2 = dao.setProcedure(strProcName1);

					dao.setProcInValue(nProcIndex2, "p_mode", "3",1);
					dao.setProcInValue(nProcIndex2, "strItemId",vo.getStrItemid(),2);
					dao.setProcInValue(nProcIndex2, "strdeptid",vo.getStrStoreId(),3);
					dao.setProcInValue(nProcIndex2, "strBatchNo",vo.getStrBatchNo(),4);
					dao.setProcInValue(nProcIndex2, "strSerialNo",vo.getStrSerialNo(),5);
					
					dao.setProcInValue(nProcIndex2, "strMaintenanceId",vo.getStrMaintenanceId(),6);
					dao.setProcInValue(nProcIndex2, "strTaskId",vo.getStrRightItemIds()[i],7);
					dao.setProcInValue(nProcIndex2, "strHospitalCode",vo.getStrHospitalCode(),8);
					dao.setProcInValue(nProcIndex2, "strEffectiveFrom",vo.getStrEffectiveFrom(),9);
					dao.setProcInValue(nProcIndex2, "strSeatId",vo.getStrSeatId(),10);
					
					dao.setProcInValue(nProcIndex2, "strIsValid", "1",11);
					dao.setProcOutValue(nProcIndex2, "err", 1,12);
					dao.execute(nProcIndex2, 1);
				}
			}

			synchronized (dao) {
				dao.fire();

			}
			vo.setStrMsgType("0");

		} catch (Exception e) {
			 e.printStackTrace();
			vo.setStrMsgString("-->ItemMaintenanceMstDAO.insert()-->"
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
	 * 
	 * @param vo
	 */
	public static void getWarrantyData(ItemMaintenanceMstVO vo) {

		String strProcName = "{call pkg_bmed_view.proc_hstt_warranty_dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		WebRowSet ws = null;
		HisDAO daoObj = null;
		try {

			daoObj = new HisDAO("bmed", "ItemMaintenanceMstDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "p_mode", "7", 1);
			daoObj.setProcInValue(nProcIndex, "p_item_id", vo.getStrItemid(), 2);
			daoObj.setProcInValue(nProcIndex, "p_hospital_code",
					vo.getStrHospitalCode(), 3);
			daoObj.setProcInValue(nProcIndex, "p_batch_no", vo.getStrBatchNo(),
					4);
			daoObj.setProcInValue(nProcIndex, "p_item_sl_no",
					vo.getStrSerialNo(), 5);
			daoObj.setProcInValue(nProcIndex, "p_sl_no", "0", 6);
			daoObj.setProcInValue(nProcIndex, "p_itembrand_id",
					vo.getStrItemBrandId(), 7);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 9);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null) {
				strErr = "";
			}
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {
				vo.setWsWarrantyDtl(ws);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("ItemMaintenanceMstDAO.getItemSlNoCmbBasisOfBatch() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	/**
	 * 
	 * @param vo
	 */
	public static void getContractData(ItemMaintenanceMstVO vo) {

		String strProcName = "{call pkg_bmed_view.proc_hemt_item_mc_dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		HisDAO daoObj = null;
		try {

			daoObj = new HisDAO("bmed", "ItemMaintenanceMstDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "p_mode", "9", 1);
			daoObj.setProcInValue(nProcIndex, "p_item_id",
					vo.getStrItemBrandId(), 2);
			daoObj.setProcInValue(nProcIndex, "p_batch_no", vo.getStrBatchNo(),
					3);
			daoObj.setProcInValue(nProcIndex, "p_item_slno",
					vo.getStrSerialNo(), 4);
			daoObj.setProcInValue(nProcIndex, "p_sl_no", "0", 5);
			daoObj.setProcInValue(nProcIndex, "p_hospital_code",
					vo.getStrHospitalCode(), 6);

			daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 8);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null) {
				strErr = "";
			}
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {
				vo.setWsMaintenanceDtl(ws);
			}
		} catch (Exception e) {
			vo.setStrMsgString("ItemMaintenanceMstDAO.getItemSlNoCmbBasisOfBatch() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}

	}

}
