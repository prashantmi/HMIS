package ipd.masters.dao;

import ipd.masters.vo.NurseCheckListMstVO;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class NurseCheckListMstDAO {

	/**
	 * retrieves and executes insert Query
	 * 
	 * @param formBean -
	 *            Form Object of the Current Master
	 * @return true - If Record Inserted Successfully. <br>
	 *         false - If Record Not Inserted Successfully
	 * @throws Exception
	 */
	public static boolean insertQuery(NurseCheckListMstVO formBean)
			throws Exception {

		boolean fReturnValue = true;
		int nQueryIndex = 0;
		HisDAO dao = null;
		String strQuery = null;
		dao = new HisDAO("Nurse Check List Master",
				"DAONurseCheckListMst.insertQuery()");
		strQuery = ipd.qryHandler_ipd.getQuery(1, "insert.nurseChkListMst.0");
		nQueryIndex = dao.setQuery(strQuery);

		try {
			//System.out.println("strEffectiveFromDate->"+formBean.getStrEffectiveFromDate());
			dao.setQryValue(nQueryIndex, 1, formBean.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 2, "1");
			dao.setQryValue(nQueryIndex, 3, formBean.getStrCheckListName());
			dao.setQryValue(nQueryIndex, 4, formBean.getStrCheckListType());
			dao.setQryValue(nQueryIndex, 5, formBean.getStrCheckListFor());
			dao.setQryValue(nQueryIndex, 6, formBean.getStrEffectiveFromDate());
			dao.setQryValue(nQueryIndex, 7, formBean.getStrRemarks());
			dao.setQryValue(nQueryIndex, 8, formBean.getStrSeatId());
			dao.setQryValue(nQueryIndex, 9, "1");
			dao.setQryValue(nQueryIndex, 10, formBean.getStrHospitalCode());
			dao.setQryValue(nQueryIndex, 11, formBean.getStrCheckListUnit());
			dao.setQryValue(nQueryIndex, 12, formBean.getStrCheckListDefaultVal());
			dao.setQryValue(nQueryIndex, 13, formBean.getStrCheckListDataType());
			
			dao.execute(nQueryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {
			fReturnValue = false;
			throw new Exception("DAONurseCheckListMst.insertQuery() -->"
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return fReturnValue;
	}

	/**
	 * returns whether the current Check List Name is Already Exists or Not.
	 * 
	 * @param formBean -
	 *            Form Object of the Current Master.
	 * @return true - If Check List Name Exists. <br>
	 *         false - If Check List Name does not Exists.
	 * @throws Exception
	 */
	public static boolean isInsertExists(NurseCheckListMstVO formBean)
			throws Exception {

		boolean fReturnValue = false;
		int nQueryIndex = 0;
		HisDAO dao = null;
		String strQuery = null;
		dao = new HisDAO("Nurse Check List Master",
				"DAONurseCheckListMst.isInsertExists()");
		strQuery = ipd.qryHandler_ipd.getQuery(1, "select.nurseChkListMst.2");
		nQueryIndex = dao.setQuery(strQuery);

		try {

			dao.setQryValue(nQueryIndex, 1, formBean.getStrCheckListName());
			WebRowSet web = dao.executeQry(nQueryIndex);

			while (web.next()) {
				if (Integer.parseInt(web.getString(1)) >= 1) {
					fReturnValue = true;
				} else {
					fReturnValue = false;
				}

			}

		} catch (Exception e) {
			throw new Exception("DAONurseCheckListMst.isInsertExists() -->"
					+ e.getMessage());
		}

		return fReturnValue;
	}

	/**
	 * returns whether the current Check List Name is Already Exists or Not.
	 * 
	 * @param formBean -
	 *            Form Object of the Current Master.
	 * @param strChk -
	 *            primary Keys concatenated with '@' symbol.
	 * @return true - If Check List Name Exists. <br>
	 *         false - If Check List Name does not Exists.
	 * @throws Exception
	 */
	public static boolean isUpdateExists(NurseCheckListMstVO formBean,
			String strChk) throws Exception {

		boolean fReturnValue = false;
		int nQryIndex = 0;
		HisDAO dao = null;
		String strQuery = null;
		dao = new HisDAO("Nurse Check List Master",
				"DAONurseCheckListMst.isUpdateExists()");
		strChk = strChk.replace('@', '$');
		String strTemp[] = strChk.replace('$', '#').split("#");
		strQuery = ipd.qryHandler_ipd.getQuery(1, "select.nurseChkListMst.4");
		nQryIndex = dao.setQuery(strQuery);
		// System.out.println("temp0---->"+strTemp[0]);
		// System.out.println("temp1---->"+strTemp[1]);
		try {

			dao.setQryValue(nQryIndex, 1, formBean.getStrCheckListName());
			dao.setQryValue(nQryIndex, 2, strTemp[0]);
			dao.setQryValue(nQryIndex, 3, strTemp[1]);

			WebRowSet web = dao.executeQry(nQryIndex);

			while (web.next()) {
				if (Integer.parseInt(web.getString(1)) >= 1) {
					fReturnValue = true;
				} else {
					fReturnValue = false;
				}

			}

		} catch (Exception e) {
			throw new Exception("DAONurseCheckListMst.isUpdateExists() -->"
					+ e.getMessage());
		}

		return fReturnValue;
	}

	/**
	 * retrieves and executes modify Query
	 * 
	 * @param strChk -
	 *            Primary Keys Concatenated with '@'.
	 * @param formBean -
	 *            Form Object of the Current Master
	 * @throws Exception
	 */
	public static void modifyQuery(String strChk, NurseCheckListMstVO formBean)
			throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		WebRowSet web=null;
		int nQryIndex;

		try {

			dao = new HisDAO("Nurse Check List Master",
					"DAONurseCheckListMst.modifyQuery()");

			strChk = strChk.replace('@', '$');
			String strTemp[] = strChk.replace('$', '#').split("#");
			strQuery = ipd.qryHandler_ipd.getQuery(1,
					"select.nurseChkListMst.3");
			nQryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQryIndex, 1, strTemp[0]);
			dao.setQryValue(nQryIndex, 2, strTemp[1]);
			dao.setQryValue(nQryIndex, 3, strTemp[2]);
			web= dao.executeQry(nQryIndex);
			if (web.next()) {
				formBean.setStrCheckListName(web.getString(1));
				formBean.setStrCheckListType(web.getString(2));
				formBean.setStrCheckListFor(web.getString(3));
				formBean.setStrEffectiveFromDate(web.getString(4));
				formBean.setStrRemarks(web.getString(5));
				formBean.setStrActive(web.getString(6));
				formBean.setStrCheckListUnit(web.getString(7));
				formBean.setStrCheckListDefaultVal(web.getString(8));
				formBean.setStrCheckListDataType(web.getString(9));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("DAONurseCheckListMst.modifyQuery()-->"
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

	}

	/**
	 * Logically Deletes Current Record and Inserts New Record with the Modified
	 * Data.
	 * 
	 * @param chk -
	 *            Primary Keys Concatenated with '@'.
	 * @param formBean -
	 *            Form Object of the Current Master.
	 * @return true - if Record Updated Successfully. <br>
	 *         false - if Record Not Updated Successfully.
	 */
	public static boolean updateQuery(String chk, NurseCheckListMstVO formBean)
			throws Exception {

		boolean fReturnValue = false;
		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex = 0;

		try {
			// String strTemp1[] = null;
			dao = new HisDAO("Nurse Check List Master ",
					"DAONurseCheckListMst.updateQuery()");
			chk = chk.replace('@', '$');
			String strTemp[] = chk.replace('$', '#').split("#");
			strQuery = ipd.qryHandler_ipd.getQuery(1,
					"delete.nurseChkListMst.1");
			nQryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQryIndex, 1, formBean.getStrLastModSeatId());
			// dao.setQryValue(nQryIndex, 2, "1");
			dao.setQryValue(nQryIndex, 2, strTemp[0]);
			dao.setQryValue(nQryIndex, 3, strTemp[1]);
			dao.setQryValue(nQryIndex, 4, strTemp[2]);
			dao.execute(nQryIndex, 0);

			strQuery = ipd.qryHandler_ipd.getQuery(1,
					"insert.nurseChkListMst.1");
			int nQueryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQueryIndex, 1, strTemp[0]);
			dao.setQryValue(nQueryIndex, 2, strTemp[0]);
			dao.setQryValue(nQueryIndex, 3, strTemp[2]);
			dao.setQryValue(nQueryIndex, 4, formBean.getStrCheckListName());
			dao.setQryValue(nQueryIndex, 5, formBean.getStrCheckListType());
			dao.setQryValue(nQueryIndex, 6, formBean.getStrCheckListFor());
			dao.setQryValue(nQueryIndex, 7, formBean.getStrEffectiveFromDate());
			dao.setQryValue(nQueryIndex, 8, formBean.getStrRemarks());
			dao.setQryValue(nQueryIndex, 9, formBean.getStrLastModSeatId());
			dao.setQryValue(nQueryIndex, 10, formBean.getStrActive());
			dao.setQryValue(nQueryIndex, 11, strTemp[2]);
			dao.setQryValue(nQueryIndex, 12, formBean.getStrCheckListUnit());
			dao.setQryValue(nQueryIndex, 13, formBean.getStrCheckListDataType());
			dao.setQryValue(nQueryIndex, 14, formBean.getStrCheckListDefaultVal());
			dao.execute(nQueryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}
			fReturnValue = true;
		} catch (Exception e) {

			fReturnValue = false;
			throw new Exception("DAONurseCheckListMst.updateQuery()-->"
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fReturnValue;

	}

	public static String getUnitValues(NurseCheckListMstVO vo) throws Exception {
		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex = 0;
		WebRowSet wb = null;
		String cmstr = "";
		try {
			dao = new HisDAO("Nurse Check List Master ",
					"DAONurseCheckListMst.updateQuery()");

			strQuery = ipd.qryHandler_ipd.getQuery(1,
					"select.nurseChkListMst.6");
			nQryIndex = dao.setQuery(strQuery);
			//System.out.println("getStrDeptTempName->"+vo.getStrDeptTempName());
			//System.out.println("getStrHospitalCode->"+vo.getStrHospitalCode());
			dao.setQryValue(nQryIndex, 1, vo.getStrDeptTempName());
			dao.setQryValue(nQryIndex, 2, vo.getStrHospitalCode());
			wb = dao.executeQry(nQryIndex);
			HisUtil his = new HisUtil("Nurse Check List Master",
					"VONurseCheckListMst");
			
			String tempStr = vo.getStrUnitTempName();
			//System.out.println("tempStrUnit->"+tempStr);
			if(tempStr == null) tempStr = "";
			
			cmstr = his.getOptionValue(wb, tempStr, "0^Select Value", false);

		} catch (Exception e) {

			throw new HisException("DAONurseCheckListMst.updateQuery()-->",
					"VONurseCheckListMst", e.getMessage());
		} finally {
			dao.free();
		}

		return cmstr;
	}

}
