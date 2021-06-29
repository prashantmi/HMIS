
/* Counter Master DAO
 * Created By: Pawan Kumar B N
 * Created On: 01-Sep-2011
 */package billing.masters.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.masters.vo.CounterMstVO;

/**
 * The Class DAOcounterMst.
 */
public class CounterMstDAO {


	/**
	 * Initial add query.
	 * 
	 * @param vo the vo
	 * 
	 *  
	 * @throws Exception the exception
	 *//*
	public static String initialAddQuery(VOcounterMst vo) throws Exception {

		HisDAO dao = new HisDAO("billing", "DAOcounterMst");

		int nqryIndex;
		String modName = "";

		WebRowSet wb = null;
		String strquery = billing.qryHandler_billing.getQuery(1,
				"select.moduleName.0");// .replace("?",
										// vo.getStrHospitalCode());

		try {
			nqryIndex = dao.setQuery(strquery);

			dao.setQryValue(nqryIndex, 1, vo.getModuleId());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());

			wb = dao.executeQry(nqryIndex);

			while (wb.next()) {
				modName = wb.getString(1);
			}

		} catch (Exception e) {

			throw new Exception("DAOcounterMst.initialAddQuery() --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return modName;
	}*/

	/**
	 * Adds the data.
	 * 
	 * @param bean the bean
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public static boolean addData(CounterMstVO vo) throws Exception {
		
		HisDAO hisdao = new HisDAO("Billing", "CounterMstDAO");
		boolean returnValue = false;
		int index;
		String Query = billing.qryHandler_billing.getQuery(1,
				"insert.counterMst.0");

		try {
			index = hisdao.setQuery(Query);

			hisdao.setQryValue(index, 1, vo.getModuleId());
			hisdao.setQryValue(index, 2, vo.getIpAddress());
			hisdao.setQryValue(index, 3, vo.getCounterName());
			hisdao.setQryValue(index, 4, vo.getEffectiveFrm());
			System.out.println("vo.getEffectiveFrm()"+vo.getEffectiveFrm());
			hisdao.setQryValue(index, 5, vo.getSeatId());
			hisdao.setQryValue(index, 6, vo.getRemarks());
			hisdao.setQryValue(index, 7, "1");
			hisdao.setQryValue(index, 8, vo.getStrBuildingId());
			hisdao.setQryValue(index, 9, vo.getStrBlockId());
			hisdao.setQryValue(index, 10, vo.getStrFloorId());
			hisdao.setQryValue(index, 11, vo.getModuleId());
			hisdao.setQryValue(index, 12, vo.getStrHospitalCode());
			hisdao.setQryValue(index, 13, vo.getStrHospitalCode());
			hisdao.setQryValue(index, 14, vo.getStrLocation());

			hisdao.execute(index, 0);
			synchronized (hisdao) {
				hisdao.fire();
			}
			returnValue = true;
		} catch (Exception e) {
			returnValue = false;
			throw new Exception("billing.DAOcounterMst.addData() --> "
					+ e.getMessage());
		} finally {

			hisdao.free();
			hisdao = null;
		}
		return returnValue;
	}

	
	/**
	 * Gets the data.
	 * 
	 * @param vo the vo
	 * @param chk the chk
	 * 
	 * @return the data
	 * 
	 * @throws Exception the exception
	 */
	public static boolean getData(CounterMstVO vo, String chk)
			throws Exception {
		
		HisDAO hisdao = null;
		boolean returnValue = true;
		WebRowSet wb;
		int index;
		String strtemp[] = null;
		String strtemp2[] = null;
		strtemp = chk.replace('@', '#').split("#");
		strtemp2 = strtemp[1].replace('$', '#').split("#");
		strtemp[1] = strtemp2[0];

		String Query = billing.qryHandler_billing.getQuery(1,
				"select.counterMst.3");
		try {
			hisdao = new HisDAO("Billing", "CounterMstDAO");
			index = hisdao.setQuery(Query);

			hisdao.setQryValue(index, 1, strtemp[0]);
			hisdao.setQryValue(index, 2, strtemp[1]);
			wb = hisdao.executeQry(index);

			while (wb.next()) {
				vo.setIpAddress(wb.getString(1));
				vo.setCounterName(wb.getString(2));
				vo.setStrLocation(wb.getString(3));
				vo.setEffectiveFrm(wb.getString(4));
				vo.setRemarks(wb.getString(5));
				vo.setIsValid(wb.getString(6));
			}
		} catch (Exception e) {
			returnValue = false;
		
			throw new Exception("billing.CounterMstDAO.getData() --> "
					+ e.getMessage());
		} finally {

			hisdao.free();
			hisdao = null;
		}
		return returnValue;
	}

	/**
	 * Modify data.
	 * 
	 * @param vo the vo
	 * @param chk the chk
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public static boolean modifyData(CounterMstVO vo, String chk)
			throws Exception {

		HisDAO hisdao = null;
		boolean returnValue = false;
		int index;
		String strtemp[] = null;
		String strtemp2[] = null;

		strtemp = chk.replace('@', '#').split("#");
		strtemp2 = strtemp[1].replace('$', '#').split("#");
		strtemp[1] = strtemp2[0];
		String query = billing.qryHandler_billing.getQuery(1,
				"update.counterMst.0");
		try {
			hisdao = new HisDAO("Billing", "CounterMstDAO");
			index = hisdao.setQuery(query);

			hisdao.setQryValue(index, 1, vo.getIpAddress());
			hisdao.setQryValue(index, 2, vo.getCounterName());
			//hisdao.setQryValue(index, 3, vo.getEffectiveFrm());
			hisdao.setQryValue(index, 3, vo.getLstSeatId());
			hisdao.setQryValue(index, 4, vo.getRemarks());
			hisdao.setQryValue(index, 5, vo.getIsValid());
			hisdao.setQryValue(index, 6, vo.getStrLocation());
			hisdao.setQryValue(index, 7, strtemp[0]);
			hisdao.setQryValue(index, 8, strtemp[1]);

			hisdao.execute(index, 0);
			hisdao.fire();

			returnValue = true;
		} catch (Exception e) {
			returnValue = false;
		
			throw new Exception("billing.CounterMstDAO.modifyData() --> "
					+ e.getMessage());
		} finally {

			hisdao.free();
			hisdao = null;
		}
		return returnValue;
	}

	
	/**
	 * Chk add counter.
	 * 
	 * @param vo the vo
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public static boolean chkAddCounter(CounterMstVO vo) throws Exception {
		
		HisDAO hisdao = new HisDAO("Billing", "CounterMstDAO");
		boolean returnValue = false;
		int index;
		int count = 0;
		WebRowSet wb = null;
		String query = billing.qryHandler_billing.getQuery(1,
				"select.counterMst.5");
		try {
			index = hisdao.setQuery(query);
			hisdao.setQryValue(index, 1, vo.getModuleId());
			hisdao.setQryValue(index, 2, vo.getStrHospitalCode());
			hisdao.setQryValue(index, 3, vo.getIpAddress());
			hisdao.setQryValue(index, 4, vo.getCounterName());

			wb = hisdao.executeQry(index);
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
		
			throw new Exception("billing.CounterMstDAO.chkAddCounter() --> "
					+ e.getMessage());
		}

		finally {

			hisdao.free();
			hisdao = null;
		}
		return returnValue;
	}

	
	/**
	 * Chk modify counter.
	 * 
	 * @param vo the vo
	 * @param chk the chk
	 * 
	 * @return true, if successful
	 * 
	 * @throws Exception the exception
	 */
	public static boolean chkModifyCounter(CounterMstVO vo, String chk)
			throws Exception {

		HisDAO hisdao = new HisDAO("Billing", "CounterMstDAO");
		boolean returnValue = false;
		int index;
		int count = 0;
		WebRowSet wb;
		String strtemp[] = null;
		String strtemp2[] = null;
		strtemp = chk.replace('@', '#').split("#");
		strtemp2 = strtemp[1].replace('$', '#').split("#");
		strtemp[1] = strtemp2[0];

		try {
	
			String query = billing.qryHandler_billing.getQuery(1,
					"select.counterMst.4");
			index = hisdao.setQuery(query);
			hisdao.setQryValue(index, 1, vo.getCounterName());
			hisdao.setQryValue(index, 2, strtemp[0]);
			hisdao.setQryValue(index, 3, strtemp[1]);
			hisdao.setQryValue(index, 4, vo.getModuleId());
			
			wb = hisdao.executeQry(index);
			while (wb.next()) {
				count = Integer.parseInt(wb.getString(1));
			}
			if (count == 0) {
				returnValue = true;
			} else {
				vo.setWarningMsg("Counter Name already exists.");
				returnValue = false;
			}

	
			if (returnValue) {
				String query2 = billing.qryHandler_billing.getQuery(1,
						"select.counterMst.4.0");
				index = hisdao.setQuery(query2);
				hisdao.setQryValue(index, 1, vo.getIpAddress());
				hisdao.setQryValue(index, 2, strtemp[0]);
				hisdao.setQryValue(index, 3, strtemp[1]);
				hisdao.setQryValue(index, 4, vo.getModuleId());

				wb = hisdao.executeQry(index);
				while (wb.next()) {
					count = Integer.parseInt(wb.getString(1));
				}
				if (count == 0) {
					returnValue = true;
				} else {
					vo.setWarningMsg(" IP Address already exists.");
					returnValue = false;
				}
			}

		} catch (Exception e) {
			returnValue = false;
	
			throw new Exception("billing.CounterMstDAO.chkModifyCounter() --> "
					+ e.getMessage());
		}
	
		return returnValue;
	}


}
