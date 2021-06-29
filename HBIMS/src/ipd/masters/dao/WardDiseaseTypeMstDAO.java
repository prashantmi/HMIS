package ipd.masters.dao;

import ipd.masters.vo.WardDiseaseTypeMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class WardDiseaseTypeMstDAO {
	
	/**
	 * retrieves and executes insert Query
	 * 
	 * @param vo -Form Object of the Current Master
	 * @return - If Record Inserted Successfully. <br> false - If Record Not Inserted Successfully
	 * @throws Exception
	 */
	public static boolean insertQuery(WardDiseaseTypeMstVO vo) throws Exception {
		
		HisDAO dao = null;
		int nqryIndex;
		boolean fretvalue = true;
		String strquery = new String();
		try {
			dao = new HisDAO("ipd", "DAOWardDiseaseTypeMst");
			strquery = ipd.qryHandler_ipd.getQuery(1, "insert.diseasetype.0");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 3, vo.getStrDiseaseTypeName());
			dao.setQryValue(nqryIndex, 4, vo.getStrRemarks());
			dao.setQryValue(nqryIndex, 5, vo.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 6, vo.getStrSeatId());
			dao.setQryValue(nqryIndex, 7, "1");
			
			dao.execute(nqryIndex, 0);

			synchronized (dao) {
				dao.fire();
				fretvalue = true;
			}
		} catch (Exception e) {
			fretvalue = false;
			throw new Exception("DAOWardDiseaseTypeMst.insertQuery() --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fretvalue;

	}
	
	/**
	 * To check for Duplicate Records
	 * 
	 * @param vo - Form Object of the Current Master
	 * @return - true then record already exist else false then record saved
	 * @throws Exception
	 */
	public static boolean initialAddQuery(WardDiseaseTypeMstVO vo) throws Exception {
		HisDAO dao = new HisDAO("ipd", "DAOWardDiseaseTypeMst");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.diseasetype.2");

		try {
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, vo.getStrHospitalCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrDiseaseTypeName());
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
			throw new Exception("DAOWardDiseaseTypeMst.initialAddQuery() --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	
		return freturnValue;
	}
	
	/**
	 * retrieves and executes modify Query.
	 * 
	 * @param chk1 - Primary Keys Concatenated with '@'.
	 * @param vo - Form Object of the Current Master
	 * @throws Exception
	 */
	public static void modifyQuery(String chk1, WardDiseaseTypeMstVO vo)
	throws Exception {

		HisDAO dao = null;
		String strtemp[] = null;
		int nqryIndex;
		String strquery = new String();
		try {
				chk1=chk1.replace('@', '$');
				strtemp = chk1.replace('$', '#').split("#");
				 
				dao = new HisDAO("ipd", "DAOWardDiseaseTypeMst");
				strquery = ipd.qryHandler_ipd.getQuery(1, "select.diseasetype.3");
				nqryIndex = dao.setQuery(strquery);
				dao.setQryValue(nqryIndex, 1, strtemp[0]);//hospital_code
				dao.setQryValue(nqryIndex, 2, strtemp[1]);//diseasetype_code
				WebRowSet web = dao.executeQry(nqryIndex);
				while (web.next()) {
					vo.setStrDiseaseTypeName(web.getString(1));
					vo.setStrEffectiveFrom(web.getString(2));
					vo.setStrRemarks(web.getString(3));
					vo.setStrIsValid(web.getString(4));
			}
		} catch (Exception e) {
					throw new Exception("DAOWardDiseaseTypeMst.modifyQuery() --> "+ e.getMessage());
			}
		finally 
		{
			dao.free();
			dao = null;
		}
					
	}
	
	/**
	 * updates the Current Record.
	 * 
	 * @param chk - Primary Keys Concatenated with '@'.
	 * @param vo -  Form Object of the Current Master.
	 * @return -true - if Record Updated Successfully. <br>
	 * 		   false - if Record Not Updated Successfully.
	 * @throws Exception
	 * 
	 */
	public static boolean updateQuery(String chk, WardDiseaseTypeMstVO vo)
	throws Exception {
			boolean fretValue = true;
			HisDAO dao = null;
			String strtemp[] = null;
			int nqryIndex;
			String strquery = new String();
			try {
				dao = new HisDAO("IPD", "DAOWardDiseaseType");
				strquery = ipd.qryHandler_ipd.getQuery(1, "update.diseasetype.1");
				nqryIndex = dao.setQuery(strquery);
				chk=chk.replace('@', '$');
				strtemp = chk.replace('$', '#').split("#");
				dao.setQryValue(nqryIndex, 1, vo.getStrDiseaseTypeName());
				dao.setQryValue(nqryIndex, 2, vo.getStrEffectiveFrom());
				dao.setQryValue(nqryIndex, 3, vo.getStrLastModifiedSeatId()); 
				dao.setQryValue(nqryIndex, 4, vo.getStrRemarks());
				dao.setQryValue(nqryIndex, 5, vo.getStrIsValid());
				dao.setQryValue(nqryIndex, 6, strtemp[0]);//hospital_code
				dao.setQryValue(nqryIndex, 7, strtemp[1]);//disease_code
				dao.execute(nqryIndex, 0);
			
				synchronized (dao) {
					dao.fire();
				}
			} catch (Exception e) {
				e.printStackTrace();
				fretValue = false;
				throw new Exception("DAOWardDiseaseTypeMst.updateQuery() --> "
						+ e.getMessage());
			} finally {
				dao.free();
				dao = null;
			}
			return fretValue;
	}
	
	/**
	 * For initial update
	 * 
	 * @param chk - Primary Keys Concatenated with '@'.
	 * @param vo - FormBean Object
	 * @return boolean Value true or false
	 */
	public static boolean initialUpdateQuery(String chk, WardDiseaseTypeMstVO vo)
	       throws Exception {
					HisDAO dao = new HisDAO("ipd", "DAOWardDiseaseTypeMst");
					String strtemp[] = null;
					boolean freturnValue = false;
					int nqryIndex;
					int ncount = 0;
					WebRowSet wb = null;
					String strquery = ipd.qryHandler_ipd.getQuery(1, "select.diseasetype.4");
					try {
						chk=chk.replace('@', '$');
						strtemp = chk.replace('$', '#').split("#");
						nqryIndex = dao.setQuery(strquery);
						dao.setQryValue(nqryIndex, 1, strtemp[0]);//hospital_code
						dao.setQryValue(nqryIndex, 2, strtemp[1]);//disease_code
						dao.setQryValue(nqryIndex, 3, vo.getStrDiseaseTypeName());
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
						throw new Exception("DAOWardDiseaseTypeMst.initialUpdateQuery() --> "
								+ e.getMessage());
					} finally {
						dao.free();
						dao = null;
					}
					return freturnValue;
			}


}
