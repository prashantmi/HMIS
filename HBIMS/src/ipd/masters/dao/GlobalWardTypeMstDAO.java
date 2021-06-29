package ipd.masters.dao;

import ipd.masters.vo.GlobalWardTypeMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class GlobalWardTypeMstDAO {

	/**
	 * 
	 * @param globalWardTypeMstVO_p
	 * @return
	 * @throws Exception
	 * All the data are insert and saved into data base after inserting
	 */
	public static boolean insertQuery(GlobalWardTypeMstVO globalWardTypeMstVO_p) throws Exception {
		HisDAO dao = null;
		int qryIndex;
		boolean retvalue = true;
		String query = new String();
		dao = new HisDAO("ipd", "GlobalWardTypeMst");
		query = ipd.qryHandler_ipd.getQuery(1, "insert.globalWardtype.0");
		qryIndex = dao.setQuery(query);
		try {
			dao.setQryValue(qryIndex, 1, globalWardTypeMstVO_p.getStrhospitalCode());
			dao.setQryValue(qryIndex, 2, globalWardTypeMstVO_p.getStrWardType());
			dao.setQryValue(qryIndex, 3, globalWardTypeMstVO_p.getStrEffectiveDate());
			dao.setQryValue(qryIndex, 4, globalWardTypeMstVO_p.getStrSeatId());
			dao.setQryValue(qryIndex, 5, globalWardTypeMstVO_p.getStrhospitalCode());
			dao.execute(qryIndex, 0);
			synchronized (dao) {
				dao.fire();
				retvalue = true;

			}

		} catch (Exception e) {
			retvalue = false;
			throw new Exception("ipd.GlobalWardTypeMst.insertQuery"+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return retvalue;

	}

	/**
	 * 
	 * @param globalWardTypeMstVO_p
	 * @return
	 * @throws Exception
	 * Check whether Duplicacy is there or not
	 */
	public static boolean initialAddQuery(GlobalWardTypeMstVO globalWardTypeMstVO_p) throws Exception {
		HisDAO dao = new HisDAO("ipd", "GlobalWardTypeMst");
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		String query = ipd.qryHandler_ipd.getQuery(1, "select.globalWardtype.2");

		try {
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, globalWardTypeMstVO_p.getStrWardType());
			dao.setQryValue(qryIndex, 2, globalWardTypeMstVO_p.getStrhospitalCode());

			wb = dao.executeQry(qryIndex);
			while (wb.next()) {
				count = Integer.parseInt(wb.getString(1));
			}
			if (count == 0) {
				returnValue = true;
			} else {
				returnValue = false;
			}

		} catch (Exception e) {
			returnValue = false;
			throw new Exception("ipd.GlobalWardTypeMst.initialAddQuery"
					+ e.getMessage());

		}

		finally {

			dao.free();
			dao = null;
		}
		return returnValue;
	}

	/**
	 * 
	 * @param strChk1_p
	 * @param globalWardTypeMstVO_p
	 * @throws Exception
	 * Select one data and forward to modify page
	 */
	public static void modifyQuery(String strChk1_p, GlobalWardTypeMstVO globalWardTypeMstVO_p)
			throws Exception {

		HisDAO dao = null;
		String temp[] = null;
		int qryIndex;
		String query = new String();

		strChk1_p=strChk1_p.replace("@", "$");
		temp = strChk1_p.replace('$', '#').split("#");
		globalWardTypeMstVO_p.setStrhospitalCode(temp[0]);
		globalWardTypeMstVO_p.setWardTypeCode(temp[1]);
		try {
			dao = new HisDAO("ipd", "GlobalWardTypeMst");
			query = ipd.qryHandler_ipd.getQuery(1, "select.globalWardtype.3");

			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, temp[1]); //global ward type code
			dao.setQryValue(qryIndex, 2, temp[0]); //hospital Code
			dao.setQryValue(qryIndex, 3, temp[2].replace("|", "@").split("@")[0]); //Serial No
			WebRowSet web = dao.executeQry(qryIndex);
			while (web.next()) {

				globalWardTypeMstVO_p.setStrWardType(web.getString(1));
				globalWardTypeMstVO_p.setStrIsValid(web.getString(2));

			}
		} catch (Exception e) {

			throw new Exception("ipd.GlobalWardTypeMst.modifyQuery"
					+ e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}

	}

	/**
	 * 
	 * @param StrChk_p
	 * @param globalWardTypeMstVO_p
	 * @return
	 * @throws Exception
	 * After modification, All the data update
	 */
	public static boolean updateQuery(String StrChk_p, GlobalWardTypeMstVO globalWardTypeMstVO_p)
			throws Exception {
		boolean retvalue = true;
		HisDAO dao = null;
		String temp[] = null;
		int qryIndex;
		String query = new String();
		try {

			dao = new HisDAO("ipd", "GlobalWardTypeMstDAO");
			query = ipd.qryHandler_ipd.getQuery(1, "update.globalWardtype.1");
			
			qryIndex = dao.setQuery(query);
			
			StrChk_p=StrChk_p.replace("@", "$");
			temp = StrChk_p.replace('$', '#').split("#");
			
			dao.setQryValue(qryIndex, 1, globalWardTypeMstVO_p.getStrWardType());
			dao.setQryValue(qryIndex, 2, globalWardTypeMstVO_p.getStrEffectiveDate());
			dao.setQryValue(qryIndex, 3, globalWardTypeMstVO_p.getStrLastModifiedSeatId());
			dao.setQryValue(qryIndex, 4, globalWardTypeMstVO_p.getStrIsValid());
			dao.setQryValue(qryIndex, 5, temp[1]); //global ward type code
			dao.setQryValue(qryIndex, 6, temp[0]); //hospital Code
			dao.setQryValue(qryIndex, 7, temp[2].replace("|", "@").split("@")[0]); //Serial No
			dao.execute(0, qryIndex);

			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {

			retvalue = false;
			throw new Exception("ipd.GlobalWardTypeMst.updateQuery"
					+ e.getMessage());
		} finally {

			dao.free();
			dao = null;
		}

		return retvalue;

	}

	/**
	 * 
	 * @param StrChk_p
	 * @param globalWardTypeMstVO_p
	 * @return
	 * @throws Exception
	 * Check about initial update
	 */
	public static boolean initialUpdateQuery(String StrChk_p, GlobalWardTypeMstVO globalWardTypeMstVO_p)
			throws Exception {
		HisDAO dao = new HisDAO("ipd", "GlobalWardTypeMst");
		String temp[] = null;
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		String query = ipd.qryHandler_ipd.getQuery(1, "select.globalWardtype.4");
		try {
			StrChk_p = StrChk_p.replace('@', '$');
			temp=StrChk_p.replace("$", "#").split("#");
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, temp[1]);
			dao.setQryValue(qryIndex, 2,globalWardTypeMstVO_p.getStrWardType());
			dao.setQryValue(qryIndex, 3,temp[0]);
			dao.setQryValue(qryIndex, 4,temp[2].replace("|", "@").split("@")[0]);
			wb = dao.executeQry(qryIndex);
			while (wb.next()) {
					count = Integer.parseInt(wb.getString(1));
			}
			if (count == 0) {
				returnValue = true;
			} else {
				returnValue = false;
			}
		} catch (Exception e) {
			returnValue = false;
			throw new Exception("ipd.GlobalWardTypeMst.initialAddQuery"
					+ e.getMessage());
		}
		finally {
			dao.free();
			dao = null;
		}
		return returnValue;
	}

	/**
	 * 
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getWardTypedtl(boolean orderBy) throws Exception {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String tempStr = "";
		int qryIndex = 0;

		String errMsg = "";
		String qryStr = "";
		qryStr = ipd.qryHandler_ipd.getQuery(1, "gbl.globalWardtype.0");
		tempStr = ipd.qryHandler_ipd.getQuery(1, "gbl.globalWardtype.cond.0");
		qryStr += " WHERE " + tempStr;
		// CHECK ORDER BY
		if (orderBy) {
			tempStr = ipd.qryHandler_ipd.getQuery(1, "gbl.globalWardtype.cond.1");
			qryStr += " ORDER BY " + tempStr;
		}

		try {
			if (!qryStr.equals("")) {
				daoObj = new HisDAO("ipd", "ipd_qry_mst.properties."
						+ "GlobalWardTypeMst");
				qryIndex = daoObj.setQuery(qryStr);

				ws = daoObj.executeQry(qryIndex);
			} else {
				errMsg = "DAOIpd.getWardType -->Query is blank";
				throw new Exception(errMsg);
			}
		} catch (Exception e) {
			throw new Exception("ipd.GlobalWardTypeMst.getWardTypedtl"
					+ e.getMessage());
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

		return ws;
	}

}