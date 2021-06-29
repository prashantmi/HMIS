package ipd.masters.dao;

import ipd.masters.vo.PropertyMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class PropertyMstDAO {
	/**
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 * All the data are saved into database after inserting
	 */
	public static boolean insertQuery(PropertyMstVO vo) throws Exception {
		HisDAO dao = null;
		int qryIndex;
		boolean retvalue = true;
		String query = new String();
		
		try {
			dao = new HisDAO("ipd", "DAOPropertyMst");
			query = ipd.qryHandler_ipd.getQuery(1, "insert.property.0");
			qryIndex = dao.setQuery(query);
			
			dao.setQryValue(qryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(qryIndex, 2, vo.getStrPropertyName());
			dao.setQryValue(qryIndex, 3, vo.getStrEffectiveFrom());
			dao.setQryValue(qryIndex, 4, vo.getStrRemark());
			dao.setQryValue(qryIndex, 5, vo.getStrHospitalCode());
			dao.setQryValue(qryIndex, 6, vo.getStrSeatId());
			
			dao.execute(qryIndex, 0);
			synchronized (dao) {
				dao.fire();
				retvalue = true;

			}

		} catch (Exception e) {
			retvalue = false;
			throw new Exception("ipd.DAOPropertyMst.insertQuery"+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return retvalue;

	}

	/**
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 * Check whether Duplicacy is there or not during insertion
	 */
	public static boolean initialAddQuery(PropertyMstVO vo) throws Exception {
		HisDAO dao = new HisDAO("ipd", "DAOPropertyMst");
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		

		try {
			String query = ipd.qryHandler_ipd.getQuery(1, "select.property.1");
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, vo.getStrPropertyName());
			dao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());

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
			throw new Exception("ipd.DAOPropertyMst.initialAddQuery"
					+ e.getMessage());

		}

		finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
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
	public static void modifyQuery(String chk1, PropertyMstVO vo)
			throws Exception {

		HisDAO dao = null;
		String temp[] = null;
		int qryIndex;
		String query = new String();

		chk1=chk1.replace("@", "$");
		temp = chk1.replace('$', '#').split("#");
		vo.setStrPropertyID(temp[0]);
		vo.setStrHospitalCode(temp[1]);
		try {
			dao = new HisDAO("ipd", "DAOPropertyMst");
			query = ipd.qryHandler_ipd.getQuery(1, "select.property.2");

			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, temp[0]);
			dao.setQryValue(qryIndex, 2, temp[1]);
			WebRowSet web = dao.executeQry(qryIndex);
			while (web.next()) {

				vo.setStrPropertyName(web.getString(1));
				vo.setStrEffectiveFrom(web.getString(2));
				vo.setStrRemark(web.getString(3));
				vo.setStrIsValid(web.getString(4));

			}
			
		} catch (Exception e) {

			throw new Exception("ipd.DAOPropertyMst.modifyQuery"
					+ e.getMessage());

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}
	/**
	 * 
	 * @param chk
	 * @param vo
	 * @return
	 * @throws Exception
	 * After modification, All the data are updated
	 */
	public static boolean updateQuery(String chk, PropertyMstVO vo)
			throws Exception {
		
		boolean retvalue = true;
		HisDAO dao = null;
		String temp[] = null;
		int qryIndex;
		String query = new String();
		try {

			dao = new HisDAO("ipd", "DAOPropertyMst");
			query = ipd.qryHandler_ipd.getQuery(1, "update.property.1");
			
			qryIndex = dao.setQuery(query);
			
			chk=chk.replace("@", "$");
			temp = chk.replace('$', '#').split("#");
			
			dao.setQryValue(qryIndex, 1, vo.getStrPropertyName());
			dao.setQryValue(qryIndex, 2, vo.getStrEffectiveFrom());
			dao.setQryValue(qryIndex, 3, vo.getStrRemark());
			dao.setQryValue(qryIndex, 4, vo.getStrLastModifiedSeatId());
			dao.setQryValue(qryIndex, 5, vo.getStrIsValid());
			dao.setQryValue(qryIndex, 6, temp[0]);
			dao.setQryValue(qryIndex, 7, temp[1]);
			dao.execute(0, qryIndex);

			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {

			retvalue = false;
			throw new Exception("ipd.DAOPropertyMst.updateQuery"
					+ e.getMessage());
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

		return retvalue;

	}

	/**
	 * 
	 * @param chk
	 * @param vo
	 * @return
	 * @throws Exception
	 * Check duplicate values during updation
	 */
	public static boolean initialUpdateQuery(String chk, PropertyMstVO vo)
			throws Exception {
		
		HisDAO dao = new HisDAO("ipd", "DAOPropertyMst");
		String temp[] = null;
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		String query = ipd.qryHandler_ipd.getQuery(1, "select.property.4");
		try {
			chk = chk.replace('@', '$');
			temp=chk.replace("$", "#").split("#");
			qryIndex = dao.setQuery(query);
			dao.setQryValue(qryIndex, 1, temp[0]);
			dao.setQryValue(qryIndex, 2,vo.getStrPropertyName());
			dao.setQryValue(qryIndex, 3,temp[1]);
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
			throw new Exception("ipd.DAOPropertyMst.initialAddQuery"
					+ e.getMessage());
		}
		finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return returnValue;
	}


}
