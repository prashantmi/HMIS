package ipd.masters.dao;

import ipd.masters.vo.DischargeTypeMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class DischargeTypeMstDAO {
	/**
	 * Insert data
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean insertQuery(DischargeTypeMstVO vo) throws Exception {
		// System.out.println("Inside Insert query");
		HisDAO dao = null;
		int nqryIndex;
		
		boolean fretValue1 = false;

		String query = new String();
		dao = new HisDAO("ipd", "DAODischargeTypeMst");

		query = ipd.qryHandler_ipd.getQuery(1, "insert.disChargeType.0");
		//System.out.println("Query in Insert Query :=>" + query);
		nqryIndex = dao.setQuery(query);

		try {
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrDischargeTypeName());
			dao.setQryValue(nqryIndex, 3, vo.getStrEffectiveDate());
			dao.setQryValue(nqryIndex, 4, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 5, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 6, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 7, "1");
			dao.setQryValue(nqryIndex, 8, vo.getStrHospitalCode());
			dao.execute(nqryIndex, 0);
			//System.out.println("insert query");
			synchronized (dao) {
				dao.fire();
				fretValue1 = true;
			}
		} catch (Exception e) {
			fretValue1 = false;
			throw new Exception("DAODischargeTypeMst.insertQuery(vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		
		return fretValue1;

	}
/**
 * Check for duplicate data before insertion
 * @param vo
 * @return
 * @throws Exception
 */
	public static boolean initialAddQuery(DischargeTypeMstVO vo)
			throws Exception {
		HisDAO dao = new HisDAO("ipd", "DAODischargeTypeMst");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;

		String strquery = ipd.qryHandler_ipd.getQuery(1,
				"select.disChargeType.2");

		try {
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrDischargeTypeName());
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));

			}
			if (ncount == 0) {
				freturnValue = true;
			} else {
				freturnValue = false;
			}
		} catch (Exception e) {
			freturnValue = false;
			throw new Exception("DAODischargeTypeMst.initialAddQuery(vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return freturnValue;
	}
/**
 * This function is used to bring data on modify page
 * @param chk1
 * @param vo
 * @throws Exception
 */
	public static void modifyQuery(String chk1, DischargeTypeMstVO vo)
			throws Exception {

		HisDAO dao = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		try{
		chk1=chk1.replace('@', '$');
		//System.out.println(""+chk1);
		strtemp = chk1.replace('$', '#').split("#");
		dao = new HisDAO("ipd", "DAODischargeTypeMst");
			strquery = ipd.qryHandler_ipd.getQuery(1, "select.disChargeType.3");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, strtemp[0]);//hospital_code
			dao.setQryValue(nqryIndex, 2, strtemp[1]);//discharge_type_code
			
			WebRowSet web = dao.executeQry(nqryIndex);
			while (web.next()) {
				vo.setStrDischargeTypeName(web.getString(1));
				vo.setStrEffectiveDate(web.getString(2));
				vo.setStrRemarks(web.getString(3));
				vo.setStrIsValid(web.getString(4));
			}
		} catch (Exception e) {
			throw new Exception("DAODischargeTypeMst.modifyQuery(chk,vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

	}
/**
 * is used for Updating record
 * @param chk1
 * @param vo
 * @return
 * @throws Exception
 */
	public static boolean updateQuery(String chk1, DischargeTypeMstVO vo)
			throws Exception {
		boolean fretValue = true;
		HisDAO dao = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		try {
			dao = new HisDAO("IPD", "DAODischargeTypeMst");
			strquery = ipd.qryHandler_ipd.getQuery(1, "update.disChargeType.1");
			nqryIndex = dao.setQuery(strquery);
			chk1=chk1.replace('@', '$');
			strtemp = chk1.replace('$', '#').split("#");
			
			dao.setQryValue(nqryIndex, 1, vo.getStrDischargeTypeName());
			dao.setQryValue(nqryIndex, 2, vo.getStrEffectiveDate());
			dao.setQryValue(nqryIndex, 3, vo.getStrLastModifiedSeatId()); 
			dao.setQryValue(nqryIndex, 4, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 5, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 6, vo.getStrIsValid());
			dao.setQryValue(nqryIndex, 7, strtemp[0]);//hospital_code
			dao.setQryValue(nqryIndex, 8, strtemp[1]);//discharge_code
			dao.execute(nqryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {
			fretValue = false;
			throw new Exception("DAODischargeTypeMst.updateQuery(chk1,vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fretValue;
	}
/**
 * Check for duplicate data before updating any record
 * @param chk
 * @param vo
 * @return
 * @throws Exception
 */
	public static boolean initialUpdateQuery(String chk, DischargeTypeMstVO vo)
			throws Exception {
		HisDAO dao = null;
		String strtemp[] = null;
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String query = new String();

		try {
			dao = new HisDAO("ipd", "DAODischargeTypeMst");
			query = ipd.qryHandler_ipd.getQuery(1, "select.disChargeType.4");
			chk=chk.replace('@', '$');
			strtemp = chk.replace('$', '#').split("#");
			nqryIndex = dao.setQuery(query);
			/*System.out.println(""+strtemp[0]);
			System.out.println(""+strtemp[1]);
			System.out.println("---------->"+vo.getStrDischargeTypeName());*/
		//hs code
			dao.setQryValue(nqryIndex, 1, strtemp[1]);//discharge code
			dao.setQryValue(nqryIndex, 2, vo.getStrDischargeTypeName());
			dao.setQryValue(nqryIndex, 3, strtemp[0]);
			wb = dao.executeQry(nqryIndex);
			while (wb.next()) {
				ncount = Integer.parseInt(wb.getString(1));
			}
			//System.out.println("-------->"+ncount);
			if (ncount == 0) {
				freturnValue = true;
			} else {
				freturnValue = false;
			}

		} catch (Exception e) {
			freturnValue = false;
			throw new Exception(
					"DAODischargeTypeMst.initialUpdateQuery(chk,vo) --> "
							+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		//System.out.println("returnValue in add==>" + freturnValue);
		return freturnValue;
	}
}