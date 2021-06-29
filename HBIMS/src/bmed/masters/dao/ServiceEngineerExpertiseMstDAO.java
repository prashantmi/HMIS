package bmed.masters.dao;

import javax.sql.rowset.WebRowSet;

import bmed.masters.vo.ServiceEngineerExpertiseMstVO;
import hisglobal.transactionmgnt.HisDAO;

public class ServiceEngineerExpertiseMstDAO {

	public static void checkDuplicateInsert(ServiceEngineerExpertiseMstVO vo)
			throws Exception {
		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;

		int nNoOfMatchedRows = 0;
		try {
			dao = new HisDAO("BMED", "ServiceEngineerExpertiseMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.insert.chkduplicate");

			nQueryIndex = dao.setQuery(strQuery);

			vo.setfExistStatus(false);

			for (String strSkillId : vo.getArrStrSelectedExpertiseId()) {

				dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
				dao.setQryValue(nQueryIndex, 2, strSkillId);
				dao.setQryValue(nQueryIndex, 3,
						vo.getStrServiceEngineerEmployeeId());

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
					"ServiceEngineerExpertiseMstDAO.checkDuplicate(vo) --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
			web = null;
		}

	}

	public static void checkDuplicateModify(ServiceEngineerExpertiseMstVO vo) {
		/* No Need to check if data exists or not. */
		vo.setfExistStatus(false);

	}

	public static void initializeAdd(ServiceEngineerExpertiseMstVO vo)
			throws Exception {

		/* Nothing To initialize. */

	}

	public static void initializeModify(ServiceEngineerExpertiseMstVO vo)
			throws Exception {
		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "ServiceEngineerExpertiseMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.initializeModify.0");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2,
					vo.getStrServiceEngineerEmployeeId());
			dao.setQryValue(nQueryIndex, 3, vo.getStrSelectedExpertiseId());// For
																			// modify
																			// there
																			// will
																			// be
																			// only
																			// one
																			// Skil
																			// id.

			web = dao.executeQry(nQueryIndex);

			if (web.next()) {

				vo.setStrEffectiveFrom(web.getString(1)); // GDT_EFFECTIVE_FROM
				vo.setStrIsValid(web.getString(2)); // GNUM_ISVALID
				vo.setStrExpertiseName(web.getString(3)); // SKILL_NAME
				vo.setStrServiceEngineerName(web.getString(4)); // EMP_NAME
				vo.setStrRemarks("There is no remarks field in table.");
			}

			web.close();

		} catch (Exception e) {

			throw new Exception(
					"ServiceEngineerExpertiseMstDAO.initializeModify(vo) --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
			web = null;
		}

	}

	public static void insert(ServiceEngineerExpertiseMstVO vo)
			throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;

		try {
			dao = new HisDAO("BMED", "ServiceEngineerExpertiseMstDAO");

			/*
			 * Insert in HEMT_NONITEM_MAINTE_MST
			 */
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.insert");

			nQueryIndex = dao.setQuery(strQuery);

			for (String strSkillId : vo.getArrStrSelectedExpertiseId()) {

				dao.setQryValue(nQueryIndex, 1,
						vo.getStrServiceEngineerEmployeeId());// HEMSTR_EMP_ID
				dao.setQryValue(nQueryIndex, 2, strSkillId);// HEMNUM_SKILL_ID
				dao.setQryValue(nQueryIndex, 3, vo.getStrHospitalCode());// GNUM_HOSPITAL_CODE
				dao.setQryValue(nQueryIndex, 4, vo.getStrEffectiveFrom());// GDT_EFFECTIVE_FROM
				dao.setQryValue(nQueryIndex, 5, vo.getStrSeatId());// GNUM_SEATID

				dao.execute(nQueryIndex, 0); // mode - 0 if query are added into
												// batch and 1 if procedures are
												// added into batch

			}

			dao.fire();
			vo.setStrNormalMsg("Data has been successfully saved.");

		} catch (Exception e) {
			
			//e.printStackTrace();
			throw new Exception(
					"ServiceEngineerExpertiseMstDAO.initializeModify(vo) --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	public static void update(ServiceEngineerExpertiseMstVO vo)
			throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;

		try {
			dao = new HisDAO("BMED", "ServiceEngineerExpertiseMstDAO");

			/*
			 * Update in HEMT_NONITEM_MAINTE_MST
			 */
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.update");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrIsValid());// SET
																// GNUM_ISVALID
			dao.setQryValue(nQueryIndex, 2, vo.getStrSeatId());// GNUM_LSTMOD_SEATID
			dao.setQryValue(nQueryIndex, 3, vo.getStrHospitalCode());// GNUM_HOSPITAL_CODE
			dao.setQryValue(nQueryIndex, 4,
					vo.getStrServiceEngineerEmployeeId());// HEMSTR_EMP_ID

			/* For modify there will be only one Skil id. */
			dao.setQryValue(nQueryIndex, 5, vo.getStrSelectedExpertiseId());// HEMNUM_SKILL_ID

			dao.execute(nQueryIndex, 0); // mode - 0 if query are added into
											// batch and 1 if procedures are
											// added into batch

			dao.fire();

		} catch (Exception e) {

			throw new Exception(
					"ServiceEngineerExpertiseMstDAO.update(vo) --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}

	public static void setWrsAvailableExpertiseOptions(
			ServiceEngineerExpertiseMstVO vo) throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQueryIndex = 0;
		WebRowSet web = null;
		try {
			dao = new HisDAO("BMED", "ServiceEngineerExpertiseMstDAO");
			strQuery = bmed.qryHandler_bmed.getQuery(1,
					"serviceEngineerExpertiseMst.availableExpertise.combo");

			nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2,
					vo.getStrServiceEngineerEmployeeId());

			web = dao.executeQry(nQueryIndex);
			vo.setWrsAvailableExpertiseOptions(web);

		} catch (Exception e) {

			throw new Exception(
					"ServiceEngineerExpertiseMstDAO.initializeModify(vo) --> "
							+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
			}
			dao = null;
		}

	}
}
