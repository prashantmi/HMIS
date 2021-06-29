package ipd.masters.dao;

import ipd.masters.vo.GlobalDischargeTypeMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class GlobalDischargeTypeMstDAO {
	/**
	 * Insert data
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public static boolean insertQuery(GlobalDischargeTypeMstVO globalDischargeTypeMstVO_p) throws Exception {
		// System.out.println("Inside Insert query");
		HisDAO dao = null;
		int nqryIndex;
		
		boolean fretValue1 = false;

		String query = new String();
		dao = new HisDAO("ipd", "GlobalDischargeTypeMstDAO");

		query = ipd.qryHandler_ipd.getQuery(1, "insert.globalDisChargeType.0");
		//System.out.println("Query in Insert Query :=>" + query);
		nqryIndex = dao.setQuery(query);

		try {
			dao.setQryValue(nqryIndex, 1, globalDischargeTypeMstVO_p.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, globalDischargeTypeMstVO_p.getStrDischargeTypeName());
			dao.setQryValue(nqryIndex, 3, globalDischargeTypeMstVO_p.getStrEffectiveDate());
			dao.setQryValue(nqryIndex, 4, globalDischargeTypeMstVO_p.getStrSeatId());
			dao.setQryValue(nqryIndex, 5, globalDischargeTypeMstVO_p.getStrRemarks());
			dao.setQryValue(nqryIndex, 6, globalDischargeTypeMstVO_p.getStrSeatId());
			dao.setQryValue(nqryIndex, 7, "1");
			dao.setQryValue(nqryIndex, 8, globalDischargeTypeMstVO_p.getStrHospitalCode());
			dao.execute(nqryIndex, 0);
			//System.out.println("insert query");
			synchronized (dao) {
				dao.fire();
				fretValue1 = true;
			}
		} catch (Exception e) {
			fretValue1 = false;
			throw new Exception("GlobalDischargeTypeMstDAO.insertQuery(globalDischargeTypeMstVO_p) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		
		return fretValue1;

	}
/**
 * Check for duplicate data before insertion
 * @param globalDischargeTypeMstVO_p
 * @return
 * @throws Exception
 */
	public static boolean initialAddQuery(GlobalDischargeTypeMstVO globalDischargeTypeMstVO_p)
			throws Exception {
		HisDAO dao = new HisDAO("ipd", "GlobalDischargeTypeMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;

		String strquery = ipd.qryHandler_ipd.getQuery(1,
				"select.globalDisChargeType.2");

		try {
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, globalDischargeTypeMstVO_p.getStrDischargeTypeName());
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
			throw new Exception("GlobalDischargeTypeMstDAO.initialAddQuery(globalDischargeTypeMstVO_p) --> "
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
 * @param globalDischargeTypeMstVO_p
 * @throws Exception
 */
	public static void modifyQuery(String chk1, GlobalDischargeTypeMstVO globalDischargeTypeMstVO_p)
			throws Exception {

		HisDAO dao = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		try{
		chk1=chk1.replace('@', '$');
		//System.out.println(""+chk1);
		strtemp = chk1.replace('$', '#').split("#");
		dao = new HisDAO("ipd", "GlobalDischargeTypeMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "select.globalDisChargeType.3");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, strtemp[0]);//hospital_code
			dao.setQryValue(nqryIndex, 2, strtemp[1]);//discharge_type_code
			dao.setQryValue(nqryIndex, 3, strtemp[2]);//serial no
			
			WebRowSet web = dao.executeQry(nqryIndex);
			while (web.next()) {
				globalDischargeTypeMstVO_p.setStrDischargeTypeName(web.getString(1));
				globalDischargeTypeMstVO_p.setStrEffectiveDate(web.getString(2));
				globalDischargeTypeMstVO_p.setStrRemarks(web.getString(3));
				globalDischargeTypeMstVO_p.setStrIsValid(web.getString(4));
			}
		} catch (Exception e) {
			throw new Exception("GlobalDischargeTypeMstDAO.modifyQuery(chk,globalDischargeTypeMstVO_p) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

	}
/**
 * is used for Updating record
 * @param chk1
 * @param globalDischargeTypeMstVO_p
 * @return
 * @throws Exception
 */
	public static boolean updateQuery(String chk1, GlobalDischargeTypeMstVO globalDischargeTypeMstVO_p)
			throws Exception {
		boolean fretValue = true;
		HisDAO dao = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		try {
			dao = new HisDAO("IPD", "GlobalDischargeTypeMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "update.globalDisChargeType.1");
			nqryIndex = dao.setQuery(strquery);
			chk1=chk1.replace('@', '$');
			strtemp = chk1.replace('$', '#').split("#");
			
			dao.setQryValue(nqryIndex, 1, globalDischargeTypeMstVO_p.getStrDischargeTypeName());
			dao.setQryValue(nqryIndex, 2, globalDischargeTypeMstVO_p.getStrEffectiveDate());
			dao.setQryValue(nqryIndex, 3, globalDischargeTypeMstVO_p.getStrLastModifiedSeatId()); 
			dao.setQryValue(nqryIndex, 4, globalDischargeTypeMstVO_p.getStrRemarks());
			dao.setQryValue(nqryIndex, 5, globalDischargeTypeMstVO_p.getStrIsValid());
			dao.setQryValue(nqryIndex, 6, strtemp[0]);//hospital_code
			dao.setQryValue(nqryIndex, 7, strtemp[1]);//discharge_code
			dao.setQryValue(nqryIndex, 8, strtemp[2]);//Serial No
			dao.execute(nqryIndex, 0);

			synchronized (dao) {
				dao.fire();
			}
		} catch (Exception e) {
			fretValue = false;
			throw new Exception("GlobalDischargeTypeMstDAO.updateQuery(chk1,globalDischargeTypeMstVO_p) --> "
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
 * @param globalDischargeTypeMstVO_p
 * @return
 * @throws Exception
 */
	public static boolean initialUpdateQuery(String chk, GlobalDischargeTypeMstVO globalDischargeTypeMstVO_p)
			throws Exception {
		HisDAO dao = null;
		String strtemp[] = null;
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String query = new String();

		try {
			dao = new HisDAO("ipd", "GlobalDischargeTypeMstDAO");
			query = ipd.qryHandler_ipd.getQuery(1, "select.globalDisChargeType.4");
			chk=chk.replace('@', '$');
			strtemp = chk.replace('$', '#').split("#");
			nqryIndex = dao.setQuery(query);
			/*System.out.println(""+strtemp[0]);
			System.out.println(""+strtemp[1]);
			System.out.println("---------->"+globalDischargeTypeMstVO_p.getStrDischargeTypeName());*/
		//hs code
			dao.setQryValue(nqryIndex, 1, strtemp[1]);//discharge code
			dao.setQryValue(nqryIndex, 2, globalDischargeTypeMstVO_p.getStrDischargeTypeName());
			dao.setQryValue(nqryIndex, 3, strtemp[0]);
			dao.setQryValue(nqryIndex, 4, strtemp[2]);
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
					"DischargeTypeMstDAO.initialUpdateQuery(chk,globalDischargeTypeMstVO_p) --> "
							+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		//System.out.println("returnValue in add==>" + freturnValue);
		return freturnValue;
	}
}