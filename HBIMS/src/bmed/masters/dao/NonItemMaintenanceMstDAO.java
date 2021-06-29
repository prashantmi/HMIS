package bmed.masters.dao;

import hisglobal.hisconfig.Config;

import java.util.ArrayList;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.NonItemMaintenanceMstVO;
import hisglobal.transactionmgnt.HisDAO;

public class NonItemMaintenanceMstDAO {
	public static void getEngineeringItemTypeWRS() {

	}

	public static void initializeAdd(NonItemMaintenanceMstVO vo) {

		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.initializeAdd.0");

			
			nQueryIndex = dao.setQuery(strQuery);
			
			System.out.println("1=="+vo.getStrHospitalCode());
			System.out.println("2=="+vo
					.getStrEngineeringItemSubTypeId());

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao
					.setQryValue(nQueryIndex, 2, vo
							.getStrEngineeringItemSubTypeId());

			web = dao.executeQry(nQueryIndex);

			if (web.next()) {
				vo.setStrEngineeringItemTypeName(web.getString(1));
				vo.setStrEngineeringItemSubTypeName(web.getString(2));
			}
			web.close();

		} catch (Exception e) {

			vo
					.setStrMsgString("NonItemMaintenanceMstDAO.initializeAdd(vo) --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
			web = null;
		}

	}

	// public static void setWrsBuildingCodeOptions(NonItemMaintenanceMstVO vo)
	// {
	// HisDAO dao = null;
	// String strQuery = null;
	// int nQueryIndex = 0;
	// WebRowSet web = null;
	// try {
	// dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
	// strQuery = bmed.qryHandler_bmed.getQuery(1,
	// "nonItemMaintenanceMst.building.combo.0");
	// nQueryIndex = dao.setQuery(strQuery);
	//
	// dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
	//
	// web = dao.executeQry(nQueryIndex);
	//
	// vo.setWrsBuildingCodeOptions(web);
	//
	// } catch (Exception e) {
	//
	// vo.setStrMsgString("NonItemMaintenanceMstDAO.setWrsBuildingCodeOptions(vo)
	// --> "
	// + e.getMessage());
	// vo.setStrMsgType("1");
	// } finally {
	// if (dao != null) {
	// dao.free();
	// }
	// dao = null;
	// }
	//
	// }

	public static void setWrsMaintenanceIdOptions(NonItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.maintenance.combo.0");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrDeptId());
			dao.setQryValue(nQueryIndex, 3, vo.getStrNonItemId());
			dao.setQryValue(nQueryIndex, 4, vo.getStrEngineeringItemTypeId());
			dao
					.setQryValue(nQueryIndex, 5, vo
							.getStrEngineeringItemSubTypeId());

			web = dao.executeQry(nQueryIndex);

			vo.setWrsMaintenanceIdOptions(web);

		} catch (Exception e) {

			vo
					.setStrMsgString("NonItemMaintenanceMstDAO.setWrsMaintenanceIdOptions(vo) --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	public static void setWrsMaintenancePeriodUnitIdOptions(
			NonItemMaintenanceMstVO vo) {

		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.maintenancePeriodUnit.combo.0");

			nQueryIndex = dao.setQuery(strQuery);

			//dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 1, Config.SUPER_USER_HOSPITAL_CODE);

			web = dao.executeQry(nQueryIndex);

			vo.setWrsMaintenancePeriodUnitIdOptions(web);

		} catch (Exception e) {

			vo
					.setStrMsgString("NonItemMaintenanceMstDAO.setWrsMaintenancePeriodUnitIdOptions(vo) --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	public static void setWrsDeptIdOptionsOptions(NonItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.department.combo.0");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());

			web = dao.executeQry(nQueryIndex);

			vo.setWrsDeptIdOptions(web);

		} catch (Exception e) {

			vo
					.setStrMsgString("NonItemMaintenanceMstDAO.setWrsStoreIdOptionsOptions(vo) --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	// public static void setWrsBlockCodeOptions(NonItemMaintenanceMstVO vo)
	// throws Exception {
	//
	// HisDAO dao = null;
	// String strQuery = null;
	// int nQueryIndex = 0;
	// WebRowSet web = null;
	// try {
	// dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
	// strQuery = bmed.qryHandler_bmed.getQuery(1,
	// "nonItemMaintenanceMst.block.combo.0");
	//
	// nQueryIndex = dao.setQuery(strQuery);
	//
	// dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
	// dao.setQryValue(nQueryIndex, 2, vo.getStrBuildingCode());
	//
	// web = dao.executeQry(nQueryIndex);
	//
	// vo.setWrsBlockCodeOptions(web);
	//
	// } catch (Exception e) {
	//
	// throw new Exception(
	// "NonItemMaintenanceMstDAO.setWrsBlockCodeOptions(vo) --> "
	// + e.getMessage());
	// } finally {
	// if (dao != null) {
	// dao.free();
	// }
	// dao = null;
	// }
	//
	// }

	// public static void setWrsFloorIdOptions(NonItemMaintenanceMstVO vo)
	// throws Exception {
	// HisDAO dao = null;
	// String strQuery = null;
	// int nQueryIndex = 0;
	// WebRowSet web = null;
	// try {
	// dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
	// strQuery = bmed.qryHandler_bmed.getQuery(1,
	// "nonItemMaintenanceMst.floor.combo.0");
	//
	// nQueryIndex = dao.setQuery(strQuery);
	//
	// dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
	// dao.setQryValue(nQueryIndex, 2, vo.getStrBuildingCode());
	// dao.setQryValue(nQueryIndex, 3, vo.getStrBlockCode());
	//
	// web = dao.executeQry(nQueryIndex);
	//
	// vo.setWrsFloorIdOptions(web);
	//
	// } catch (Exception e) {
	//
	// throw new Exception(
	// "NonItemMaintenanceMstDAO.setWrsFloorIdOptions(vo) --> "
	// + e.getMessage());
	// } finally {
	// if (dao != null) {
	// dao.free();
	// }
	// dao = null;
	// }
	//
	// }

	// public static void setWrsRoomIdOptions(NonItemMaintenanceMstVO vo)
	// throws Exception {
	// HisDAO dao = null;
	// String strQuery = null;
	// int nQueryIndex = 0;
	// WebRowSet web = null;
	// try {
	// dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
	// strQuery = bmed.qryHandler_bmed.getQuery(1,
	// "nonItemMaintenanceMst.room.combo.0");
	//
	// nQueryIndex = dao.setQuery(strQuery);
	//
	// dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
	// dao.setQryValue(nQueryIndex, 2, vo.getStrBuildingCode());
	// dao.setQryValue(nQueryIndex, 3, vo.getStrBlockCode());
	// dao.setQryValue(nQueryIndex, 4, vo.getStrFloorId());
	//
	// web = dao.executeQry(nQueryIndex);
	//
	// vo.setWrsRoomIdOptions(web);
	//
	// } catch (Exception e) {
	//
	// throw new Exception(
	// "NonItemMaintenanceMstDAO.setWrsRoomIdOptions(vo) --> "
	// + e.getMessage());
	// } finally {
	// if (dao != null) {
	// dao.free();
	// }
	// dao = null;
	// }
	//
	// }

	public static void setWrsLeftTaskIdOptions(NonItemMaintenanceMstVO vo)
			throws Exception {
		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.avalableTask.combo.0");

			nQueryIndex = dao.setQuery(strQuery);

		
			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrEngineeringItemTypeId());
			dao
					.setQryValue(nQueryIndex, 3, vo
							.getStrEngineeringItemSubTypeId());
			dao.setQryValue(nQueryIndex, 4, vo.getStrDeptId());
			dao.setQryValue(nQueryIndex, 5, vo.getStrNonItemId());
			dao.setQryValue(nQueryIndex, 6, vo.getStrMaintenanceId());

			web = dao.executeQry(nQueryIndex);

			vo.setWrsLeftTaskIdOptions(web);

		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception(
					"NonItemMaintenanceMstDAO.setWrsLeftTaskIdOptions --> "
							+ e.getMessage());
		} finally {

			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	public static void setWrsRightTaskIdOptions(NonItemMaintenanceMstVO vo)
			throws Exception {
		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.scheduledTask.combo.0");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrEngineeringItemTypeId());
			dao
					.setQryValue(nQueryIndex, 3, vo
							.getStrEngineeringItemSubTypeId());
			dao.setQryValue(nQueryIndex, 4, vo.getStrDeptId());
			dao.setQryValue(nQueryIndex, 5, vo.getStrNonItemId());
			dao.setQryValue(nQueryIndex, 6, vo.getStrMaintenanceId());

			web = dao.executeQry(nQueryIndex);

			vo.setWrsRightTaskIdOptions(web);

		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception(
					"NonItemMaintenanceMstDAO.setWrsRightTaskIdOptions --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	public static void insert(NonItemMaintenanceMstVO vo) throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;

		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");

			/*
			 * Insert in HEMT_NONITEM_MAINTE_MST
			 */
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.insert.nonItemMaintenanceMst");

			nQueryIndex = dao.setQuery(strQuery);
			
			
			dao.setQryValue(nQueryIndex, 1, (vo.getStrDeptId() == null
					|| vo.getStrDeptId().equals("") ? "NA" : vo.getStrDeptId()));// HEMNUM_DEPT_ID
			dao.setQryValue(nQueryIndex, 2, (vo.getStrNonItemId() == null
					|| vo.getStrNonItemId().equals("") ? "NA" : vo.getStrNonItemId()));// HEMNUM_NONITEM_ID
			dao.setQryValue(nQueryIndex, 3, (vo.getStrMaintenanceId() == null
					|| vo.getStrMaintenanceId().equals("") ? "NA" : vo.getStrMaintenanceId()));// HEMNUM_MAINTE_ID
			dao.setQryValue(nQueryIndex, 4, (vo.getStrHospitalCode() == null
					|| vo.getStrHospitalCode().equals("") ? "NA" : vo.getStrHospitalCode()));// GNUM_HOSPITAL_CODE
			dao.setQryValue(nQueryIndex, 5, (vo.getStrEngineeringItemTypeId() == null
					|| vo.getStrEngineeringItemTypeId().equals("") ? "NA" : vo.getStrEngineeringItemTypeId()));// HEMNUM_ENGG_ITEM_TYPE_ID
			dao
				.setQryValue(nQueryIndex, 6, (vo
						.getStrEngineeringItemSubTypeId() == null
									|| vo
									.getStrEngineeringItemSubTypeId().equals("") ? "0" : vo
											.getStrEngineeringItemSubTypeId()));// HEMNUM_ENGG_ITEM_SUB_TYPE_ID
			dao.setQryValue(nQueryIndex, 7, (vo.getStrLandmarkDesc() == null
					|| vo.getStrLandmarkDesc().equals("") ? "NA" : vo.getStrLandmarkDesc()));// HEMNUM_LANDMARK_DESC
			dao.setQryValue(nQueryIndex, 8, (vo.getStrMaintenancePeriod() == null
					|| vo.getStrMaintenancePeriod().equals("") ? "0" : vo.getStrMaintenancePeriod()));// HEMNUM_MAINTE_PRD
			dao.setQryValue(nQueryIndex, 9, (vo.getStrMaintenancePeriodUnitId() == null
					|| vo.getStrMaintenancePeriodUnitId().equals("") ? "0" : vo.getStrMaintenancePeriodUnitId()));// HEMNUM_MAINTE_PRD_UNIT
			dao.setQryValue(nQueryIndex, 10, (vo.getStrAlertPeriod() == null
					|| vo.getStrAlertPeriod().equals("") ? "0" : vo.getStrAlertPeriod()));// HEMNUM_ALERT_PERIOD
			dao.setQryValue(nQueryIndex, 11, (vo.getStrEffectiveFrom() == null
					|| vo.getStrEffectiveFrom().equals("") ? "0" : vo.getStrEffectiveFrom()));// GDT_EFFECTIVE_FROM
			dao.setQryValue(nQueryIndex, 12, (vo.getStrRemarks() == null
					|| vo.getStrRemarks().equals("") ? "NA" : vo.getStrRemarks()));// GSTR_REMARKS
			dao.setQryValue(nQueryIndex, 13, (vo.getStrPreferTimeFrom() == null
					|| vo.getStrPreferTimeFrom().equals("") ? "0" : vo.getStrPreferTimeFrom()));// HEMSTR_PREFER_TIME_FR
			dao.setQryValue(nQueryIndex, 14, (vo.getStrSeatId() == null
					|| vo.getStrSeatId().equals("") ? "0" : vo.getStrSeatId()));// GNUM_SEATID
			dao.setQryValue(nQueryIndex, 15, (vo.getStrPreferTimeTo() == null
					|| vo.getStrPreferTimeTo().equals("") ? "0" : vo.getStrPreferTimeTo()));// HEMSTR_PREFER_TIME_TO
			dao.setQryValue(nQueryIndex, 16, (vo.getStrVendorId() == null
					|| vo.getStrVendorId().equals("") ? "0" : vo.getStrVendorId()));// HEMNUM_VENDOR_ID
			dao.setQryValue(nQueryIndex, 17, (vo.getStrMcSlNo() == null
					|| vo.getStrMcSlNo().equals("") ? "0" : vo.getStrMcSlNo()));// HEMNUM_MC_SLNO
			dao.setQryValue(nQueryIndex, 18, (vo.getStrWarrantySlNo() == null
					|| vo.getStrWarrantySlNo().equals("") ? "0" : vo.getStrWarrantySlNo()));// HEMNUM_WARRANTY_SLNO

			dao.execute(nQueryIndex, 0); // mode - 0 if query are added into
			// batch and 1 if procedures are
			// added into batch

			/*
			 * Insert in HEMT_NONITEM_MAINTE_MST
			 */

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.insert.nonItemMaintenanceTaskMst");

			for (String strTaskId : vo.getStrRightTaskId()) {

				nQueryIndex = dao.setQuery(strQuery);

				dao.setQryValue(nQueryIndex, 1, (vo.getStrDeptId() == null
						|| vo.getStrDeptId().equals("") ? "0" : vo.getStrDeptId()));// HEMNUM_DEPT_ID
				dao.setQryValue(nQueryIndex, 2, (vo.getStrNonItemId() == null
						|| vo.getStrNonItemId().equals("") ? "0" : vo.getStrNonItemId()));// HEMNUM_NONITEM_ID
				dao.setQryValue(nQueryIndex, 3, (vo.getStrMaintenanceId() == null
						|| vo.getStrMaintenanceId().equals("") ? "0" : vo.getStrMaintenanceId()));// HEMNUM_MAINTE_ID
				dao.setQryValue(nQueryIndex, 4, strTaskId);// HEMNUM_TASK_ID
				dao.setQryValue(nQueryIndex, 5, (vo.getStrHospitalCode() == null
						|| vo.getStrHospitalCode().equals("") ? "0" : vo.getStrHospitalCode()));// GNUM_HOSPITAL_CODE
				dao.setQryValue(nQueryIndex, 6, (vo.getStrSeatId() == null
						|| vo.getStrSeatId().equals("") ? "0" : vo.getStrSeatId()));// GNUM_SEATID

				dao.execute(nQueryIndex, 0); // mode - 0 if query are added
												// into
				// batch and 1 if procedures are
				// added into batch

			}

			dao.fire();

		} catch (Exception e) {
			
			//e.printStackTrace();
			throw new Exception("NonItemMaintenanceMstDAO.insert(vo) --> "
					+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	public static void checkDuplicate(NonItemMaintenanceMstVO vo)
			throws Exception {
		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;

		int nNoOfMatchedRows = 0;
		try {
			vo.setfExistStatus(false);

			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");

			/* Checking Duplicate entry in HEMT_NONITEM_MAINTE_MST */
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.insert.chkduplicate.0");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrDeptId()); // HEMNUM_DEPT_ID
			dao.setQryValue(nQueryIndex, 2, vo.getStrNonItemId()); // HEMNUM_NONITEM_ID
			dao.setQryValue(nQueryIndex, 3, vo.getStrMaintenanceId()); // HEMNUM_MAINTE_ID
			dao.setQryValue(nQueryIndex, 4, vo.getStrHospitalCode()); // GNUM_HOSPITAL_CODE

			web = dao.executeQry(nQueryIndex);

			if (web.next()) {
				nNoOfMatchedRows = web.getInt(1);
			}
			web.close();
			if (nNoOfMatchedRows > 0) {
				vo.setfExistStatus(true);

			}

			/*
			 * If duplicate data exists in HEMT_NONITEM_MAINTE_MST, duplicate
			 * value check is not necessary for HEMT_NONITEM_MAINTE_TASK_MST
			 */
			if (vo.isfExistStatus()) {
				return;
			}

			/* Checking Duplicate entry in HEMT_NONITEM_MAINTE_TASK_MST */
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.insert.chkduplicate.1");

			nQueryIndex = dao.setQuery(strQuery);

			for (String strTaskId : vo.getStrRightTaskId()) {

				dao.setQryValue(nQueryIndex, 1, vo.getStrDeptId()); // HEMNUM_DEPT_ID
				dao.setQryValue(nQueryIndex, 2, vo.getStrNonItemId()); // HEMNUM_NONITEM_ID
				dao.setQryValue(nQueryIndex, 3, vo.getStrMaintenanceId()); // HEMNUM_MAINTE_ID
				dao.setQryValue(nQueryIndex, 4, strTaskId); // HEMNUM_TASK_ID
				dao.setQryValue(nQueryIndex, 5, vo.getStrHospitalCode()); // GNUM_HOSPITAL_CODE

				web = dao.executeQry(nQueryIndex);

				if (web.next()) {
					nNoOfMatchedRows = web.getInt(1);
				}
				web.close();
				if (nNoOfMatchedRows > 0) {
					vo.setfExistStatus(true);
					break;
				}

			}

		} catch (Exception e) {

			throw new Exception(
					"NonItemMaintenanceMstDAO.checkDuplicate(vo) --> "
							+ e.getMessage());

		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
			web = null;
		}

	}

	public static void initializeModify(NonItemMaintenanceMstVO vo)
			throws Exception {
		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.initializeModify.0");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrDeptId()); // HEMNUM_DEPT_ID
			dao.setQryValue(nQueryIndex, 2, vo.getStrNonItemId()); // HEMNUM_NONITEM_ID
			dao.setQryValue(nQueryIndex, 3, vo.getStrMaintenanceId()); // HEMNUM_MAINTE_ID
			dao.setQryValue(nQueryIndex, 4, vo.getStrHospitalCode()); // GNUM_HOSPITAL_CODE

			web = dao.executeQry(nQueryIndex);

			if (web.next()) {
				vo.setStrDeptId(web.getString(1)); // DEPT_ID
				vo.setStrNonItemId(web.getString(2)); // NONITEM_ID
				vo.setStrReqType(web.getString(3)); // REQ_TYPE
				vo.setStrMaintenanceId(web.getString(4)); // MAINTE_ID
				vo.setStrEngineeringItemTypeId(web.getString(5)); // ITEM_TYPE_ID
				vo.setStrEngineeringItemSubTypeId(web.getString(6)); // ITEM_SUB_TYPE_ID
				vo.setStrVendorId(web.getString(7)); // VENDOR_ID
				vo.setStrLandmarkDesc(web.getString(8)); // LANDMARK_DESC
				vo.setStrMaintenancePeriod(web.getString(9)); // MAINTE_PRD
				vo.setStrMcSlNo(web.getString(10)); // MC_SLNO
				vo.setStrMaintenancePeriodUnitId(web.getString(11)); // MAINTE_PRD_UNIT
				vo.setStrWarrantySlNo(web.getString(12)); // WARRANTY_SLNO
				vo.setStrAlertPeriod(web.getString(13)); // ALERT_PERIOD
				vo.setStrEffectiveFrom(web.getString(14)); // EFFECTIVE_FROM
				vo.setStrRemarks(web.getString(15)); // REMARKS
				vo.setStrPreferTimeFrom(web.getString(16)); // PREFER_TIME_FR
				vo.setStrPreferTimeTo(web.getString(17)); // PREFER_TIME_TO
				vo.setStrIsValid(web.getString(18)); // ISVALID
				vo.setStrDeptName(web.getString(19)); // DEPT_NAME
				vo.setStrNonItemName(web.getString(20)); // NONITEM_NAME
				vo.setStrEngineeringItemTypeName(web.getString(21)); // ITEM_TYPE_NAME
				vo.setStrEngineeringItemSubTypeName(web.getString(22)); // ITEM_SUB_TYPE_NAME
				vo.setStrMaintenanceName(web.getString(23)); // MAINTE_NAME
			}

			web.close();

		} catch (Exception e) {

			throw new Exception(
					"NonItemMaintenanceMstDAO.initializeModify(vo) --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
			web = null;
		}

	}

	public static void update(NonItemMaintenanceMstVO vo) throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;

		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");

			/*
			 * Update in HEMT_NONITEM_MAINTE_MST
			 */
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.update.nonItemMaintenanceMst");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, (vo.getStrLandmarkDesc() == null
					|| vo.getStrLandmarkDesc().equals("") ? "0" : vo.getStrLandmarkDesc()));// HEMNUM_LANDMARK_DESC
			dao.setQryValue(nQueryIndex, 2, (vo.getStrMaintenancePeriod() == null
					|| vo.getStrMaintenancePeriod().equals("") ? "0" : vo.getStrMaintenancePeriod()));// HEMNUM_MAINTE_PRD
			dao.setQryValue(nQueryIndex, 3, (vo.getStrMaintenancePeriodUnitId() == null
					|| vo.getStrMaintenancePeriodUnitId().equals("") ? "0" : vo.getStrMaintenancePeriodUnitId()));// HEMNUM_MAINTE_PRD_UNIT
			dao.setQryValue(nQueryIndex, 4, (vo.getStrAlertPeriod() == null
					|| vo.getStrAlertPeriod().equals("") ? "0" : vo.getStrAlertPeriod()));// HEMNUM_ALERT_PERIOD
			dao.setQryValue(nQueryIndex, 5, (vo.getStrEffectiveFrom() == null
					|| vo.getStrEffectiveFrom().equals("") ? "0" : vo.getStrEffectiveFrom()));// GDT_EFFECTIVE_FROM
			dao.setQryValue(nQueryIndex, 6, (vo.getStrRemarks() == null
					|| vo.getStrDeptId().equals("") ? "NA" : vo.getStrDeptId()));// GSTR_REMARKS
			dao.setQryValue(nQueryIndex, 7, (vo.getStrPreferTimeFrom() == null
					|| vo.getStrPreferTimeFrom().equals("") ? "0" : vo.getStrPreferTimeFrom()));// HEMSTR_PREFER_TIME_FR
			dao.setQryValue(nQueryIndex, 8, (vo.getStrPreferTimeTo() == null
					|| vo.getStrPreferTimeTo().equals("") ? "0" : vo.getStrPreferTimeTo()));// HEMSTR_PREFER_TIME_TO
			dao.setQryValue(nQueryIndex, 9, (vo.getStrIsValid() == null
					|| vo.getStrIsValid().equals("") ? "0" : vo.getStrIsValid()));// GNUM_ISVALID
			dao.setQryValue(nQueryIndex, 10, (vo.getStrSeatId() == null
					|| vo.getStrSeatId().equals("") ? "0" : vo.getStrSeatId()));// GNUM_LSTMOD_SEATID

			/* =============== Condition ============================ */
			dao.setQryValue(nQueryIndex, 11, (vo.getStrDeptId() == null
					|| vo.getStrDeptId().equals("") ? "0" : vo.getStrDeptId()));// HEMNUM_DEPT_ID
			dao.setQryValue(nQueryIndex, 12, (vo.getStrNonItemId() == null
					|| vo.getStrNonItemId().equals("") ? "0" : vo.getStrNonItemId()));// HEMNUM_NONITEM_ID
			dao.setQryValue(nQueryIndex, 13, (vo.getStrMaintenanceId() == null
					|| vo.getStrMaintenanceId().equals("") ? "0" : vo.getStrMaintenanceId()));// HEMNUM_MAINTE_ID
			dao.setQryValue(nQueryIndex, 14, (vo.getStrHospitalCode() == null
					|| vo.getStrHospitalCode().equals("") ? "0" : vo.getStrHospitalCode()));// GNUM_HOSPITAL_CODE

			dao.execute(nQueryIndex, 0); // mode - 0 if query are added into
			// batch and 1 if procedures are
			// added into batch

			/*
			 * Delete from HEMT_NONITEM_MAINTE_TASK_MST
			 */

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.update.nonItemMaintenanceTaskMst");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, (vo.getStrDeptId() == null
					|| vo.getStrDeptId().equals("") ? "0" : vo.getStrDeptId()));// HEMNUM_DEPT_ID
			dao.setQryValue(nQueryIndex, 2, (vo.getStrNonItemId() == null
					|| vo.getStrNonItemId().equals("") ? "0" : vo.getStrNonItemId()));// HEMNUM_NONITEM_ID
			dao.setQryValue(nQueryIndex, 3, (vo.getStrMaintenanceId() == null
					|| vo.getStrMaintenanceId().equals("") ? "0" : vo.getStrMaintenanceId()));// HEMNUM_MAINTE_ID
			dao.setQryValue(nQueryIndex, 4, (vo.getStrHospitalCode() == null
					|| vo.getStrHospitalCode().equals("") ? "0" : vo.getStrHospitalCode()));// GNUM_HOSPITAL_CODE

			dao.execute(nQueryIndex, 0); // mode - 0 if query are added into
			// batch and 1 if procedures are
			// added into batch

			/*
			 * Insert into HEMT_NONITEM_MAINTE_TASK_MST The Same Query is used
			 * in insert operation.
			 */

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.insert.nonItemMaintenanceTaskMst");

			for (String strTaskId : vo.getStrRightTaskId()) {

				nQueryIndex = dao.setQuery(strQuery);

				dao.setQryValue(nQueryIndex, 1, (vo.getStrDeptId() == null
						|| vo.getStrDeptId().equals("") ? "0" : vo.getStrDeptId())); // HEMNUM_DEPT_ID
				dao.setQryValue(nQueryIndex, 2, (vo.getStrNonItemId() == null
						|| vo.getStrNonItemId().equals("") ? "0" : vo.getStrNonItemId())); // HEMNUM_NONITEM_ID
				dao.setQryValue(nQueryIndex, 3, (vo.getStrMaintenanceId() == null
						|| vo.getStrMaintenanceId().equals("") ? "0" : vo.getStrMaintenanceId())); // HEMNUM_MAINTE_ID
				dao.setQryValue(nQueryIndex, 4, strTaskId); // HEMNUM_TASK_ID
				dao.setQryValue(nQueryIndex, 5, (vo.getStrHospitalCode() == null
						|| vo.getStrHospitalCode().equals("") ? "0" : vo.getStrHospitalCode())); // GNUM_HOSPITAL_CODE
				dao.setQryValue(nQueryIndex, 6, vo.getStrSeatId()); // GNUM_SEATID

				dao.execute(nQueryIndex, 0); // mode - 0 if query are added
												// into
				// batch and 1 if procedures are
				// added into batch

			}

			dao.fire();

		} catch (Exception e) {

			throw new Exception(
					"NonItemMaintenanceMstDAO.initializeAdd(vo) --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	public static void initializeView(NonItemMaintenanceMstVO vo)
			throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		ArrayList<String> listStrTaskName = null;
		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.initializeView.0");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrDeptId());// HEMNUM_DEPT_ID
			dao.setQryValue(nQueryIndex, 2, vo.getStrNonItemId());// HEMNUM_NONITEM_ID
			dao.setQryValue(nQueryIndex, 3, vo.getStrMaintenanceId());// HEMNUM_MAINTE_ID
			dao.setQryValue(nQueryIndex, 4, vo.getStrHospitalCode());// GNUM_HOSPITAL_CODE

			web = dao.executeQry(nQueryIndex);

			if (web.next()) {
				vo.setStrDeptName(web.getString(1)); // DEPT_NAME
				vo.setStrNonItemName(web.getString(2)); // NONITEM_NAME
				vo.setStrMaintenanceName(web.getString(3)); // MAINTE_NAME
				vo.setStrEngineeringItemTypeName(web.getString(4)); // ITEM_TYPE_NAME
				vo.setStrEngineeringItemSubTypeName(web.getString(5)); // ITEM_SUB_TYPE_NAME
				vo.setStrLandmarkDesc(web.getString(6)); // LANDMARK_DESC
				vo.setStrMaintenancePeriod(web.getString(7)); // MAINTE_PRD
				vo.setStrMaintenancePeriodUnitName(web.getString(8)); // MAINTE_PRD_UNIT
				vo.setStrAlertPeriod(web.getString(9)); // ALERT_PERIOD
				vo.setStrEffectiveFrom(web.getString(10)); // EFFECTIVE_FROM
				vo.setStrRemarks(web.getString(11)); // REMARKS
				vo.setStrPreferTimeFrom(web.getString(12)); // PREFER_TIME_FR
				vo.setStrPreferTimeTo(web.getString(13)); // PREFER_TIME_TO
				vo.setStrRecordStatusName(web.getString(14)); // ISVALID
			}

			web.close();

			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.initializeView.1");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());// GNUM_HOSPITAL_CODE
			dao.setQryValue(nQueryIndex, 2, vo.getStrDeptId());// HEMNUM_DEPT_ID
			dao.setQryValue(nQueryIndex, 3, vo.getStrNonItemId());// HEMNUM_NONITEM_ID
			dao.setQryValue(nQueryIndex, 4, vo.getStrMaintenanceId());// HEMNUM_MAINTE_ID

			web = dao.executeQry(nQueryIndex);
			listStrTaskName = new ArrayList<String>();

			while (web.next()) {
				String strTaskName = web.getString(1);
				listStrTaskName.add(strTaskName);
			}
			vo.setListStrTaskName(listStrTaskName);

			web.close();

		} catch (Exception e) {

			throw new Exception(
					"NonItemMaintenanceMstDAO.initializeView(vo) --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
			web = null;
		}

	}

	public static void setWrsNonItemOptions(NonItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.nonItem.combo.0");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrEngineeringItemTypeId());
			dao
					.setQryValue(nQueryIndex, 3, vo
							.getStrEngineeringItemSubTypeId());

			web = dao.executeQry(nQueryIndex);

			vo.setWrsNonItemOptions(web);

		} catch (Exception e) {

			vo
					.setStrMsgString("NonItemMaintenanceMstDAO.setWrsNonItemOptions(vo) --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	public static void setWrsMaintenanceNameOptions(NonItemMaintenanceMstVO vo) {
		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "NonItemMaintenanceMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"nonItemMaintenanceMst.maintenanceName.combo.0");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, vo.getStrEngineeringItemTypeId());
			dao
					.setQryValue(nQueryIndex, 3, vo
							.getStrEngineeringItemSubTypeId());

			web = dao.executeQry(nQueryIndex);

			vo.setWrsMaintenanceIdOptions(web);

		} catch (Exception e) {

			e.printStackTrace();
			vo
					.setStrMsgString("NonItemMaintenanceMstDAO.setWrsNonItemOptions(vo) --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}
}
