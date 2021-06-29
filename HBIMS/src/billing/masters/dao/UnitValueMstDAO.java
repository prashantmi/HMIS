package billing.masters.dao;
/* Unit Value Master DAO
 * author: Debashis Sardar
 * Created on : 16-Sep-2011
 */

import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;
import billing.masters.vo.UnitValueMstVO;
import hisglobal.utility.HisUtil;

public class UnitValueMstDAO {
	
	
	/**
	 * retrieves insert Query and executes it.
	 * @param vo
	 * @return true - if Record Inserted Successfully. <br>
	 *         false - if Record Not Inserted Successfully.
	 */
	public static boolean insertQuery(UnitValueMstVO vo) throws Exception {

		HisDAO dao1 = null;

		boolean retvalue = true;
		String fromUnitVal = "";

		dao1 = new HisDAO("Billing", "DAOUnitValueMst");
		String query = billing.qryHandler_billing.getQuery(1,
				"insert.unitvalue.0");
		String query2 = billing.qryHandler_billing.getQuery(1,
				"insert.unitvalue.0");

		int qryIndex = dao1.setQuery(query);
		int qryIndex2 = dao1.setQuery(query2);
		String temp[] = null;
		fromUnitVal = vo.getStrfromUnit();
		temp = fromUnitVal.split("#");
		vo.setStrfromUnit(temp[0]);
		String strConValue = vo.getStrconvertedValue();
		double conVal = Double.parseDouble(strConValue);
		double revVal = 1.0d / conVal;

		String strRevVal = HisUtil.getAmountWithDecimal(revVal, 3);

		try {

			dao1.setQryValue(qryIndex, 1, vo.getStrHospitalCode());
			dao1.setQryValue(qryIndex, 2, vo.getStrmoduleName());
			dao1.setQryValue(qryIndex, 3, vo.getStrfromUnit());
			dao1.setQryValue(qryIndex, 4, vo.getStrtoUnit());
			dao1.setQryValue(qryIndex, 5, vo.getStrconvertedValue());
			dao1.setQryValue(qryIndex, 6, vo.getStreffectiveFrom());
			dao1.setQryValue(qryIndex, 7, vo.getStrSeatId());
			dao1.setQryValue(qryIndex, 8, vo.getStrremark());
			dao1.setQryValue(qryIndex, 9, "1");
			dao1.setQryValue(qryIndex, 10, vo.getStrmoduleName());
			dao1.setQryValue(qryIndex, 11, vo.getStrfromUnit());
			dao1.setQryValue(qryIndex, 12, vo.getStrtoUnit());
			dao1.setQryValue(qryIndex, 13, vo.getStrHospitalCode());

			dao1.setQryValue(qryIndex2, 1, vo.getStrHospitalCode());
			dao1.setQryValue(qryIndex2, 2, vo.getStrmoduleName());
			dao1.setQryValue(qryIndex2, 3, vo.getStrtoUnit());
			dao1.setQryValue(qryIndex2, 4, vo.getStrfromUnit());
			dao1.setQryValue(qryIndex2, 5, strRevVal);
			dao1.setQryValue(qryIndex2, 6, vo.getStreffectiveFrom());
			dao1.setQryValue(qryIndex2, 7, vo.getStrSeatId());
			dao1.setQryValue(qryIndex2, 8, vo.getStrremark());
			dao1.setQryValue(qryIndex2, 9, "1");
			dao1.setQryValue(qryIndex2, 10, vo.getStrmoduleName());
			dao1.setQryValue(qryIndex2, 11, vo.getStrfromUnit());
			dao1.setQryValue(qryIndex2, 12, vo.getStrtoUnit());
			dao1.setQryValue(qryIndex2, 13, vo.getStrHospitalCode());

			dao1.execute(qryIndex, 0);
			if (!(vo.getStrfromUnit().equals(vo.getStrtoUnit()))) {
				dao1.execute(qryIndex2, 0);
			}

			synchronized (dao1) {
				dao1.fire();
			}

			retvalue = true;

		} catch (Exception e) {
	 
			retvalue = false;
			e.printStackTrace();
			vo.setStrMsgString("billing.UnitValueMstDAO.insertQuery"
					+ e.getMessage());
			vo.setStrMsgType("1");
			
		} finally {
			dao1.free();
			dao1 = null;
		}

		return retvalue;
	}

	
	/**
	 * retrieves and executes Modify Query
	 * 
	 * @param vo
	 * @param Chk1
	 */
	public static void modifyQuery(String chk1, UnitValueMstVO vo)
			throws Exception {

		
		HisDAO dao = null;
		String temp[] = null;
		int qryIndex;
		String query = new String();
		WebRowSet web = null;

		temp = chk1.replace('$', '#').split("#"); 
		try {
			dao = new HisDAO("Billing", "DAOUnitValueMst");
			query = billing.qryHandler_billing
					.getQuery(1, "select.unitvalue.9");

			qryIndex = dao.setQuery(query);
			String[] temp1 = temp[0].replaceAll("@", "#").split("#");

			for (int i = 1; i <= temp1.length; i++) {
				
				dao.setQryValue(qryIndex, i, temp1[i - 1]);
			}

			web = dao.executeQry(qryIndex);

			while (web.next()) {

				
				vo.setStrmoduleName(web.getString(1));
				vo.setStrfromUnit(web.getString(2));
				vo.setStrtoUnit(web.getString(3));
				vo.setStrconvertedValue(web.getString(4));
				vo.setStreffectiveFrom(web.getString(5));
				vo.setStrSeatId(web.getString(6));
				vo.setStrremark(web.getString(7));
				vo.setStrisValid(web.getString(8));

			}

		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("billing.UnitValueMstDAO.modifyQuery"
					+ e.getMessage());
			vo.setStrMsgType("1");
			
		} finally {
			dao.free();
			dao = null;
		}

	}

	/**
	 * retrieves and executes update Query
	 * @param Chk
	 * @param vo
	 * @return true - if Record Updated Successfully. 
	 *         false - if Record Not Updated Successfully.
	 */
	public static boolean updateQuery(String chk, UnitValueMstVO vo)
			throws Exception {
		boolean retvalue = true;
		HisDAO dao = null;
		String temp[] = null;
		String temp2[] = null;
		int qryIndex = 0;
		int qryIndex2 = 0;
		String query = new String();
		String query2 = new String();
		String strConValue = "";

		strConValue = vo.getStrconvertedValue();
		double conVal = Double.parseDouble(strConValue);
		double revVal = 1.0d / conVal;

		String strRevVal = HisUtil.getAmountWithDecimal(revVal, 3);
	

		try {

			dao = new HisDAO("Billing", "DAOUnitValueMst");
			query = billing.qryHandler_billing
					.getQuery(1, "update.unitvalue.0");
			query2 = billing.qryHandler_billing.getQuery(1,
					"update.unitvalue.1");

			temp = chk.replace('@', '#').split("#");
			temp2 = temp[4].replace("$", "#").split("#");
			
			qryIndex = dao.setQuery(query);
			qryIndex2 = dao.setQuery(query2);

			
			dao.setQryValue(qryIndex, 1, vo.getStrconvertedValue());
			dao.setQryValue(qryIndex, 2, vo.getStreffectiveFrom());
			dao.setQryValue(qryIndex, 3, vo.getStrSeatId());
			dao.setQryValue(qryIndex, 4, vo.getStrremark());
			dao.setQryValue(qryIndex, 5, vo.getStrSeatId());
			dao.setQryValue(qryIndex, 6, vo.getStrisValid());
			dao.setQryValue(qryIndex, 7, temp[0]);
			dao.setQryValue(qryIndex, 8, temp[1]);
			dao.setQryValue(qryIndex, 9, temp[2]);
			dao.setQryValue(qryIndex, 10, temp[3]);
			dao.setQryValue(qryIndex, 11, temp2[0]);

			
			dao.setQryValue(qryIndex2, 1, strRevVal);
			dao.setQryValue(qryIndex2, 2, vo.getStreffectiveFrom());
			dao.setQryValue(qryIndex2, 3, vo.getStrSeatId());
			dao.setQryValue(qryIndex2, 4, vo.getStrremark());
			dao.setQryValue(qryIndex2, 5, vo.getStrSeatId());
			dao.setQryValue(qryIndex2, 6, vo.getStrisValid());
			dao.setQryValue(qryIndex2, 7, temp[0]);
			dao.setQryValue(qryIndex2, 8, temp[1]);
			dao.setQryValue(qryIndex2, 9, temp[3]);
			dao.setQryValue(qryIndex2, 10, temp[2]);

			dao.execute(qryIndex, 0);
			if (!(vo.getStrfromUnit().equals(vo.getStrtoUnit()))) {
				dao.execute(qryIndex2, 0);
			}

			synchronized (dao) {
				dao.fire();
			}
			retvalue = true;
		}

		catch (Exception e) {
			vo.setStrErrorMsg("Record Not Updated");
			retvalue = false;
			e.printStackTrace();
			vo.setStrMsgString("billing.UnitValueMstDAO.updateQuery"
					+ e.getMessage());
			vo.setStrMsgType("1");
			
			

		} finally {
			dao.free();
			dao = null;
		}

		return retvalue;

	}

	/**
	 * initial update query
	 * @param vo
	 * @param chk
	 * @return boolean
	 */

	public static boolean initialUpdateQuery(String chk, UnitValueMstVO vo)
			throws Exception {
		HisDAO dao = new HisDAO("Billing", "DAOUnitValueMst");
		String strtemp[] = null;
		String strtemp2[] = null;

		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		WebRowSet wb = null;
		String query = billing.qryHandler_billing.getQuery(1,
				"select.unitvalue.8");

		try {

			strtemp = chk.replace('@', '#').split("#");
			strtemp2 = strtemp[2].replace("$", "#").split("#");
			
			qryIndex = dao.setQuery(query);

			dao.setQryValue(qryIndex, 1, strtemp[0]);
			dao.setQryValue(qryIndex, 2, strtemp[1]);
			dao.setQryValue(qryIndex, 3, strtemp[2]);
			dao.setQryValue(qryIndex, 4, strtemp[3]);
			dao.setQryValue(qryIndex, 5, strtemp2[0]);
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

			e.printStackTrace();
			vo.setStrMsgString("billing.UnitValueMstDAO.initialUpdateQuery"
					+ e.getMessage());
			vo.setStrMsgType("1");
		}

		finally {

			dao.free();
			dao = null;
		}

		return returnValue;
	}
	/**
	 * initial add query
	 * @param vo
	 * @param chk
	 * @return boolean
	 */
	public static boolean initialAddQuery(UnitValueMstVO vo) throws Exception {
		HisDAO dao = new HisDAO("Billing", "DAOUnitValueMst");
		boolean returnValue = false;
		int qryIndex;
		int count = 0;
		String temp[] = new String[3];
		WebRowSet wb = null;
		String query = billing.qryHandler_billing.getQuery(1,
				"select.unitvalue.10");
		String val1 = vo.getStrmoduleName();
		String frmUnit = vo.getStrfromUnit();
		try {
			temp = frmUnit.split("#");
			qryIndex = dao.setQuery(query);
			
			dao.setQryValue(qryIndex, 1, val1);
			dao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			dao.setQryValue(qryIndex, 3, temp[0]);
			dao.setQryValue(qryIndex, 4, vo.getStrtoUnit());

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
			e.printStackTrace();
			vo.setStrMsgString("billing.UnitValueMstDAO.initialAddQuery"
					+ e.getMessage());
			vo.setStrMsgType("1");
			
		} finally {
			dao.free();
			dao = null;
		}

		return returnValue;
	}

}
