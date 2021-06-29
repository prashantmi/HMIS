package ipd.masters.dao;

import ipd.masters.vo.WardTypeMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;


public class WardTypeMstDAO {

	
		 /* To Get Global Ward Type for Main Page.
		 * 
		 * @param vo the vo
		 */
		public static boolean getGlobalWardType(WardTypeMstVO vo) 
		throws Exception {

			HisDAO dao = null;
			int nqryIndex;
			String strquery = "";
			WebRowSet wb = null;
			boolean retvalue = true;

			try {
				dao = new HisDAO("ipd", "WardTypeMstDAO");

				strquery = ipd.qryHandler_ipd.getQuery(1,"select.wardtype.globalWardType.1");
				nqryIndex = dao.setQuery(strquery);
				
				dao.setQryValue(nqryIndex, 1, vo.getStrhospitalCode());
				
				wb = dao.executeQry(nqryIndex);
				if(wb !=null && wb.size()>0){
					vo.setWrsGlobalWardType(wb);
				}
					
				
			} catch (Exception e) {
				retvalue = false;
				throw new Exception("ipd.WardTypeMstDAO.getGlobalWardType --> "
						+ e.getMessage());
			} finally {
				dao.free();
				dao = null;
			}
			return retvalue;
		}	
	
	/**
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 * All the data are insert and saved into data base after inserting
	 */
	public static boolean insertQuery(WardTypeMstVO vo) throws Exception {
		HisDAO dao = null;
		int qryIndex;
		boolean retvalue = true;
		String query = new String();
		dao = new HisDAO("ipd", "DAOWardTypeMst");
		query = ipd.qryHandler_ipd.getQuery(1, "insert.wardtype.0");
		qryIndex = dao.setQuery(query);
		try {
			dao.setQryValue(qryIndex, 1, vo.getStrGlobalWardType());
			dao.setQryValue(qryIndex, 2, vo.getStrWardType());
			dao.setQryValue(qryIndex, 3, vo.getStrEffectiveDate());
			dao.setQryValue(qryIndex, 4, vo.getStrSeatId());
			dao.setQryValue(qryIndex, 5, vo.getStrhospitalCode());
			dao.setQryValue(qryIndex, 6, vo.getStrhospitalCode());
			dao.setQryValue(qryIndex, 7, vo.getBlockhrs());
			dao.execute(qryIndex, 0);
			synchronized (dao) {
				dao.fire();
				retvalue = true;

			}

		} catch (Exception e) {
			e.printStackTrace();
			retvalue = false;
			throw new Exception("ipd.DAOWardTypeMst.insertQuery"+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return retvalue;

	}

	/**
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 * Check whether Duplicacy is there or not
	 */
	public static boolean initialAddQuery(WardTypeMstVO vo) throws Exception {
		HisDAO dao = new HisDAO("ipd", "DAOWardTypeMst");
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		String query = ipd.qryHandler_ipd.getQuery(1, "select.wardtype.2");

		try {
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, vo.getStrWardType());
			dao.setQryValue(qryIndex, 2, vo.getStrhospitalCode());

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
			throw new Exception("ipd.DAOWardTypeMst.initialAddQuery"
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
	 * @param chk1
	 * @param vo
	 * @throws Exception
	 * Select one data and forward to modify page
	 */
	public static void modifyQuery(String chk1, WardTypeMstVO vo)
			throws Exception {

		HisDAO dao = null;
		String temp[] = null;
		int qryIndex;
		String query = new String();

		chk1=chk1.replace("@", "$");
		temp = chk1.replace('$', '#').split("#");
		vo.setStrhospitalCode(temp[0]);
		vo.setWardTypeCode(temp[1]);
		try {
			dao = new HisDAO("ipd", "DAOWardTypeMst");
			query = ipd.qryHandler_ipd.getQuery(1, "select.wardtype.3");

			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, temp[1]);
			dao.setQryValue(qryIndex, 2, temp[0]);
			dao.setQryValue(qryIndex, 3, temp[2]);
			WebRowSet web = dao.executeQry(qryIndex);
			while (web.next()) {

				vo.setStrWardType(web.getString(1));
				vo.setStrEffectiveDate(web.getString(2));
				vo.setStrIsValid(web.getString(3));
				vo.setBlockhrs(web.getString(4));

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ipd.WardTypeMstDAO.modifyQuery"
					+ e.getMessage());

		} finally {
			dao.free();
			dao = null;
		}

	}

	/**
	 * 
	 * @param chk
	 * @param vo
	 * @return
	 * @throws Exception
	 * After modification, All the data update
	 */
	public static boolean updateQuery(String chk, WardTypeMstVO vo)
			throws Exception {
		boolean retvalue = true;
		HisDAO dao = null;
		String temp[] = null;
		int qryIndex;
		String query = new String();
		try {

			dao = new HisDAO("ipd", "WardTypeDAO");
			query = ipd.qryHandler_ipd.getQuery(1, "update.wardtype.1");
			
			qryIndex = dao.setQuery(query);
			
			chk=chk.replace("@", "$");
			temp = chk.replace('$', '#').split("#");
			
			dao.setQryValue(qryIndex, 1, vo.getStrWardType());
			dao.setQryValue(qryIndex, 2, vo.getStrEffectiveDate());
			dao.setQryValue(qryIndex, 3, vo.getStrLastModifiedSeatId());
			dao.setQryValue(qryIndex, 4, vo.getStrIsValid());
			dao.setQryValue(qryIndex, 5, vo.getBlockhrs());
			dao.setQryValue(qryIndex, 6, temp[1]);
			dao.setQryValue(qryIndex,7, temp[0]);
			dao.setQryValue(qryIndex, 8, temp[2]);
			
			dao.execute(0, qryIndex);

			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {
			e.printStackTrace();
			retvalue = false;
			throw new Exception("ipd.DAOWardTypeMst.updateQuery"
					+ e.getMessage());
		} finally {

			dao.free();
			dao = null;
		}

		return retvalue;

	}

	/**
	 * 
	 * @param chk
	 * @param vo
	 * @return
	 * @throws Exception
	 * Check about initial update
	 */
	public static boolean initialUpdateQuery(String chk, WardTypeMstVO vo)
			throws Exception {
		HisDAO dao = new HisDAO("ipd", "DAOWardTypeMst");
		String temp[] = null;
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		String query = ipd.qryHandler_ipd.getQuery(1, "select.wardtype.4");
		try {
			chk = chk.replace('@', '$');
			temp=chk.replace("$", "#").split("#");
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, temp[1]);
			dao.setQryValue(qryIndex, 2,vo.getStrWardType());
			dao.setQryValue(qryIndex, 3,temp[0]);
			dao.setQryValue(qryIndex, 4,temp[2]);
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
			throw new Exception("ipd.DAOWardTypeMst.initialAddQuery"
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
		qryStr = ipd.qryHandler_ipd.getQuery(1, "gbl.wardtype.0");
		tempStr = ipd.qryHandler_ipd.getQuery(1, "gbl.wardtype.cond.0");
		qryStr += " WHERE " + tempStr;
		// CHECK ORDER BY
		if (orderBy) {
			tempStr = ipd.qryHandler_ipd.getQuery(1, "gbl.wardtype.cond.1");
			qryStr += " ORDER BY " + tempStr;
		}

		try {
			if (!qryStr.equals("")) {
				daoObj = new HisDAO("ipd", "ipd_qry_mst.properties."
						+ "DAOWardTypeMst");
				qryIndex = daoObj.setQuery(qryStr);

				ws = daoObj.executeQry(qryIndex);
			} else {
				errMsg = "DAOIpd.getWardType -->Query is blank";
				throw new Exception(errMsg);
			}
		} catch (Exception e) {
			throw new Exception("ipd.DAOWardTypeMst.getWardTypedtl"
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
