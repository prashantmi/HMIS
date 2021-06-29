package ipd.masters.dao;

import ipd.masters.vo.ComponentMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class ComponentMstDAO {
	
	/**
	 * retrieves and executes insert Query
	 * 
	 * @param vo -
	 *            Form Object of the Current Master
	 * @return true - If Record Inserted Successfully. <br>
	 *         false - If Record Not Inserted Successfully
	 * @throws Exception
	 */
	public static boolean insertQuery(ComponentMstVO vo) throws Exception {
		
		HisDAO dao = null;
		int nqryIndex;
		boolean fretvalue = false;
		String strquery = new String();
		try {
			dao = new HisDAO("ipd", "DAOComponentMst");
			strquery = ipd.qryHandler_ipd.getQuery(1, "insert.component.0");
			nqryIndex = dao.setQuery(strquery);
			
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			//dao.setQryValue(nqryIndex, 1, "108");
			dao.setQryValue(nqryIndex, 2, vo.getStrComponentName());
			dao.setQryValue(nqryIndex, 3, vo.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 4, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 5, vo.getStrSeatId());
			//dao.setQryValue(nqryIndex, 5, "10001");
			dao.setQryValue(nqryIndex, 6, "1");
			dao.setQryValue(nqryIndex, 7, vo.getStrHospitalCode());
			//dao.setQryValue(nqryIndex, 7, "108");
			dao.execute(nqryIndex, 0);

			synchronized (dao) {
				dao.fire();
				fretvalue = true;
			}
		} catch (Exception e) {
			fretvalue = false;
			throw new Exception("DAOComponentMst.insertQuery() --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fretvalue;
	}
	
	/**
	 * Check the Record weather data is Duplicate or not
	 * @param vo - FormBean Object
	 * @return - true -record cannot be saved ,already exist
	 * false - record will save
	 * @throws Exception
	 */
	public static boolean initialAddQuery(ComponentMstVO vo) throws Exception {
		HisDAO dao = new HisDAO("ipd", "DAOComponentMst");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.component.2");

		try {
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrComponentName());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			//dao.setQryValue(nqryIndex, 2, "108");
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
			throw new Exception("DAOComponentMst.initialAddQuery() --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return freturnValue;
	}
	
	/**
	 * retrieves and executes modify Query
	 * @param strChk - Primary Keys Concatenated with '@'.
	 * @param formBean - Form Object of the Current Master 
	 * @throws Exception
	 */
	public static void modifyQuery(String chk1, ComponentMstVO vo)
	throws Exception {

			HisDAO dao = null;
			String strtemp[] = null;
			int nqryIndex;
			String strquery = new String();
			try {
				dao = new HisDAO("ipd", "DAOComponentMst");
				strquery = ipd.qryHandler_ipd.getQuery(1, "select.component.3");
				nqryIndex = dao.setQuery(strquery);
				chk1=chk1.replace('@', '$');
				strtemp = chk1.replace('$', '#').split("#");
				
				dao.setQryValue(nqryIndex, 1, strtemp[0]);//component_code
				dao.setQryValue(nqryIndex, 2, strtemp[1]);//hospital_code
				//dao.setQryValue(nqryIndex, 2, "108");
				WebRowSet web = dao.executeQry(nqryIndex);
				while (web.next()) {
										
					vo.setStrComponentName(web.getString(1));
					vo.setStrEffectiveFrom(web.getString(2));
					
					vo.setStrRemarks(web.getString(3));
					
					vo.setStrIsValid(web.getString(4));
				}
			} catch (Exception e) {
				throw new Exception("DAOComponentMst.modifyQuery() --> "
						+ e.getMessage());
			} finally {
				dao.free();
				dao = null;
			}
		}
	
	/**
	 * To initial Update the record
	 * 
	 * @param chk -Primary Keys Concatenated with '@'.
	 * @param vo -Form Object of the Current Master.
	 * @return boolean value true - do not update the duplicate record
	 * false - cannot be modified!!
	 * @throws Exception
	 */
	public static boolean initialUpdateQuery(String chk, ComponentMstVO vo)
	throws Exception {
				HisDAO dao = null;
				String strtemp[] = null;
				boolean freturnValue = false;
				int nqryIndex;
				int ncount = 0;
				WebRowSet wb = null;
				String strquery = new String();
				try {
					dao = new HisDAO("ipd", "DAOComponentMst");
					strquery = ipd.qryHandler_ipd.getQuery(1, "select.component.4");
					nqryIndex = dao.setQuery(strquery);
					chk=chk.replace('@', '$');
					strtemp = chk.replace('$', '#').split("#");
					
					dao.setQryValue(nqryIndex, 1, strtemp[0]);
					dao.setQryValue(nqryIndex, 2, strtemp[1]);
					//dao.setQryValue(nqryIndex, 2, "108");
					dao.setQryValue(nqryIndex, 3, vo.getStrComponentName());
					wb = dao.executeQry(nqryIndex);
					while (wb.next()) {
						ncount = Integer.parseInt(wb.getString(1));
					}
					if (ncount < 1) {
						freturnValue = true;
					} else {
						freturnValue = false;
					}
				
				} catch (Exception e) {
					freturnValue = false;
					throw new Exception("DAOComponentMst.initialUpdateQuery() --> "
							+ e.getMessage());
				} finally {
					dao.free();
					dao = null;
				}
				return freturnValue;
		}
	
	/**
	 * updates the Current Record. 
	 * @param chk - Primary Keys Concatenated with '@'.
	 * @param formBean - Form Object of the Current Master.
	 * @return true - if Record Updated Successfully. <br>
	 * 		   false - if Record Not Updated Successfully.	
	 */
	public static boolean updateQuery(String chk, ComponentMstVO vo)
	throws Exception {
				boolean fretValue = true;
				HisDAO dao = null;
				String strtemp[] = null;
				int nqryIndex;
				String strquery = new String();
				try {
					dao = new HisDAO("IPD", "DAOComponentMst");
					strquery = ipd.qryHandler_ipd.getQuery(1, "update.component.1");
					nqryIndex = dao.setQuery(strquery);
					chk=chk.replace('@', '$');
					strtemp = chk.replace('$', '#').split("#");
					dao.setQryValue(nqryIndex, 1, vo.getStrComponentName());
					dao.setQryValue(nqryIndex, 2, vo.getStrEffectiveFrom());
					dao.setQryValue(nqryIndex, 3, vo.getStrLastModifiedSeatId()); 
					dao.setQryValue(nqryIndex, 4, vo.getStrRemarks());
					dao.setQryValue(nqryIndex, 5, vo.getStrIsValid());
					dao.setQryValue(nqryIndex, 6, strtemp[0]);//doc_code
					dao.setQryValue(nqryIndex, 7, strtemp[1]);//hospital_code
					//dao.setQryValue(nqryIndex, 7, "108");//hospital_code
					dao.execute(nqryIndex, 0);
				
					synchronized (dao) {
						dao.fire();
					}
				} catch (Exception e) {
					fretValue = false;
					throw new Exception("DAOComponentMst.updateQuery() --> "
							+ e.getMessage());
				} finally {
					dao.free();
					dao = null;
				}
				return fretValue;
			}

	

}
