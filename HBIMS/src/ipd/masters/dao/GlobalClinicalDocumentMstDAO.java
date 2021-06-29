package ipd.masters.dao;

import ipd.masters.vo.GlobalClinicalDocumentMstVO;

import javax.sql.rowset.WebRowSet;

import hisglobal.transactionmgnt.HisDAO;

public class GlobalClinicalDocumentMstDAO {
	
	/**
	 * retrieves and executes insert Query
	 * 
	 * @param globalClinicalDocumentMstVO_p -Form Object of the Current Master
	 * @return - If Record Inserted Successfully. <br> false - If Record Not Inserted Successfully
	 * @throws Exception
	 */
	public static boolean insertQuery(GlobalClinicalDocumentMstVO globalClinicalDocumentMstVO_p) throws Exception {
		HisDAO dao = null;
		int nqryIndex;
		boolean fretvalue = false;
		String strquery = new String();
		try {
			dao = new HisDAO("ipd", "GlobalClinicalDocumentMstDAO");
			strquery = ipd.qryHandler_ipd.getQuery(1, "insert.globalClinicalDoc.0");
			nqryIndex = dao.setQuery(strquery);
			dao.setQryValue(nqryIndex, 1, globalClinicalDocumentMstVO_p.getStrHospitalCode());
			//dao.setQryValue(nqryIndex, 1, "108");
			dao.setQryValue(nqryIndex, 2, globalClinicalDocumentMstVO_p.getStrDocumentName());
			dao.setQryValue(nqryIndex, 3, globalClinicalDocumentMstVO_p.getStrEffectiveFrom());
			dao.setQryValue(nqryIndex, 4, globalClinicalDocumentMstVO_p.getStrRemarks());
			dao.setQryValue(nqryIndex, 5, globalClinicalDocumentMstVO_p.getStrSeatId());
			//dao.setQryValue(nqryIndex, 5, "10001");
			dao.setQryValue(nqryIndex, 6, "1");
			dao.setQryValue(nqryIndex, 7, globalClinicalDocumentMstVO_p.getStrHospitalCode());
			//dao.setQryValue(nqryIndex, 7, "108");
			dao.execute(nqryIndex, 0);

			synchronized (dao) {
				dao.fire();
				fretvalue = true;
			}
		} catch (Exception e) {
			fretvalue = false;
			throw new Exception("GlobalClinicalDocumentMstDAO.insertQuery() --> "
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
	 * @param globalClinicalDocumentMstVO_p - Form Object of the Current Master
	 * @return - true then record already exist else false then record saved
	 * @throws Exception
	 */
	public static boolean initialAddQuery(GlobalClinicalDocumentMstVO globalClinicalDocumentMstVO_p) throws Exception {
		HisDAO dao = new HisDAO("ipd", "GlobalClinicalDocumentMstDAO");
		boolean freturnValue = false;
		int nqryIndex;
		int ncount = 0;
		WebRowSet wb = null;
		String strquery = ipd.qryHandler_ipd.getQuery(1, "select.globalClinicalDoc.2");

		try {
			nqryIndex = dao.setQuery(strquery);
			
			//System.out.println(globalClinicalDocumentMstVO_p.getStrHospitalCode());
			//System.out.println(globalClinicalDocumentMstVO_p.getStrDocumentName());
			
			dao.setQryValue(nqryIndex, 1, globalClinicalDocumentMstVO_p.getStrDocumentName());
			dao.setQryValue(nqryIndex, 2, globalClinicalDocumentMstVO_p.getStrHospitalCode());
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
			throw new Exception("GlobalClinicalDocumentMstDAO.initialAddQuery() --> "
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
	 * @param strChk1_p - Primary Keys Concatenated with '@'.
	 * @param globalClinicalDocumentMstVO_p - Form Object of the Current Master
	 * @throws Exception
	 */
	public static void modifyQuery(String strChk1_p, GlobalClinicalDocumentMstVO globalClinicalDocumentMstVO_p)
	throws Exception {

			HisDAO dao = null;
			String strtemp[] = null;
			int nqryIndex;
			String strquery = new String();
			try {
				
				strChk1_p=strChk1_p.replace('@', '$');
				strtemp = strChk1_p.replace('$', '#').split("#");
				
				dao = new HisDAO("ipd", "GlobalClinicalDocumentMstDAO");
				strquery = ipd.qryHandler_ipd.getQuery(1, "select.globalClinicalDoc.3");
				
				nqryIndex = dao.setQuery(strquery);
				dao.setQryValue(nqryIndex, 1, strtemp[0]);//document_code
				dao.setQryValue(nqryIndex, 2, strtemp[1]);//hospital_code
				dao.setQryValue(nqryIndex, 3, strtemp[2]);//Serial No
				//dao.setQryValue(nqryIndex, 2, "108");
				WebRowSet web = dao.executeQry(nqryIndex);
				while (web.next()) {
					
					globalClinicalDocumentMstVO_p.setStrDocumentName(web.getString(1));
					globalClinicalDocumentMstVO_p.setStrEffectiveFrom(web.getString(2));
					globalClinicalDocumentMstVO_p.setStrRemarks(web.getString(3));
					globalClinicalDocumentMstVO_p.setStrIsValid(web.getString(4));
				}
			} catch (Exception e) {
				throw new Exception("GlobalClinicalDocumentMstDAO.modifyQuery() --> "
						+ e.getMessage());
			} finally {
				dao.free();
				dao = null;
			}
		}
	
	/**
	 * For initial update
	 * 
	 * @param strChk_p - Primary Keys Concatenated with '@'.
	 * @param globalClinicalDocumentMstVO_p - FormBean Object
	 * @return boolean Value true or false
	 */
	public static boolean initialUpdateQuery(String strChk_p, GlobalClinicalDocumentMstVO globalClinicalDocumentMstVO_p)
	throws Exception {
				HisDAO dao = null;
				String strtemp[] = null;
				boolean freturnValue = false;
				int nqryIndex;
				int ncount = 0;
				WebRowSet wb = null;
				String strquery = new String();
				try {
					dao = new HisDAO("ipd", "GlobalClinicalDocumentMstDAO");
					strquery = ipd.qryHandler_ipd.getQuery(1, "select.globalClinicalDoc.4");
					strChk_p=strChk_p.replace('@', '$');
					strtemp = strChk_p.replace('$', '#').split("#");
					
					nqryIndex = dao.setQuery(strquery);
					
					dao.setQryValue(nqryIndex, 1, strtemp[0]);
					dao.setQryValue(nqryIndex, 2, strtemp[1]);//hospital_code
					//dao.setQryValue(nqryIndex, 2, "108");
					dao.setQryValue(nqryIndex, 3, globalClinicalDocumentMstVO_p.getStrDocumentName());
					dao.setQryValue(nqryIndex, 4, strtemp[1]);//Serial No.
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
					throw new Exception("GlobalClinicalDocumentMstDAO.initialUpdateQuery() --> "
							+ e.getMessage());
				} finally {
					dao.free();
					dao = null;
				}
				return freturnValue;
		}
	
	/**
	 * updates the Current Record.
	 * 
	 * @param strChk_p - Primary Keys Concatenated with '@'.
	 * @param globalClinicalDocumentMstVO_p -  Form Object of the Current Master.
	 * @return -true - if Record Updated Successfully. <br>
	 * 		   false - if Record Not Updated Successfully.
	 * @throws Exception
	 * 
	 */
	public static boolean updateQuery(String strChk_p, GlobalClinicalDocumentMstVO globalClinicalDocumentMstVO_p)
	throws Exception {
				boolean fretValue = true;
				HisDAO dao = null;
				String strtemp[] = null;
				int nqryIndex;
				String strquery = new String();
				try {
					dao = new HisDAO("IPD", "GlobalClinicalDocumentMstDAO");
					strquery = ipd.qryHandler_ipd.getQuery(1, "update.globalClinicalDoc.1");
					nqryIndex = dao.setQuery(strquery);
					strChk_p=strChk_p.replace('@', '$');
					strtemp = strChk_p.replace('$', '#').split("#");
					dao.setQryValue(nqryIndex, 1, globalClinicalDocumentMstVO_p.getStrDocumentName());
					dao.setQryValue(nqryIndex, 2, globalClinicalDocumentMstVO_p.getStrEffectiveFrom());
					dao.setQryValue(nqryIndex, 3, globalClinicalDocumentMstVO_p.getStrLastModifiedSeatId()); 
					dao.setQryValue(nqryIndex, 4, globalClinicalDocumentMstVO_p.getStrRemarks());
					dao.setQryValue(nqryIndex, 5, globalClinicalDocumentMstVO_p.getStrIsValid());
					dao.setQryValue(nqryIndex, 6, strtemp[0]);//doc_code
					dao.setQryValue(nqryIndex, 7, strtemp[1]);//hospital_code
					dao.setQryValue(nqryIndex, 8, strtemp[2]);//Serial No.
					dao.execute(nqryIndex, 0);
				
					synchronized (dao) {
						dao.fire();
					}
				} catch (Exception e) {
					fretValue = false;
					throw new Exception("GlobalClinicalDocumentMstDAO.updateQuery() --> "
							+ e.getMessage());
				} finally {
					dao.free();
					dao = null;
				}
				return fretValue;
			}
}
