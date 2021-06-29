package ipd.masters.dao;

import hisglobal.transactionmgnt.HisDAO;
import ipd.qryHandler_ipd;
import ipd.masters.vo.DUWRBedMstVO;

import javax.sql.rowset.WebRowSet;

public class DUWRBedMstDAO {

	/**
	 * retrieves and executes insert Query
	 * 
	 * @param formBean -
	 *            Form Object of the Current Master
	 * @return true - If Record Inserted Successfully. <br>
	 *         false - If Record Not Inserted Successfully
	 * @throws Exception
	 */
	public static WebRowSet getDeptUnitDtl(boolean strOrderBy_p, String strHospitalCode_p)
			throws Exception {

		String strErrMsg = "";
		String strQry = "";
		String strModuleName = "";
		String strFileName = "";
		int nQryIndex = 0;
		HisDAO daoObj = null;
		WebRowSet ws = null;
		strQry = ipd.qryHandler_ipd.getQuery(1, "select.deptunit.0");

		try {
			if (!strQry.equals("")) {
				daoObj = new HisDAO(strModuleName, "ipd_qry_mst.properties."
						+ strFileName);
				nQryIndex = daoObj.setQuery(strQry);
				daoObj.setQryValue(nQryIndex, 1, strHospitalCode_p);
				ws = daoObj.executeQry(nQryIndex);
			} else {
				strErrMsg = "DAOIpd.getDeptUnitDtl() -->Query is blank";
				throw new Exception(strErrMsg);
			}
		} catch (Exception e) {
			strErrMsg = "DAOIpd.getDeptUnitDtl() -->" + e.getMessage();
			throw new Exception(strErrMsg);
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

		return ws;
	}

	/**
	 * Though this method user get detail of ward name.
	 * 
	 * @param strOrderBy_p- getback boolean value 
	 * @param strHospitalCode_p - String Variable
	 * @return - webrowset
	 * @throws Exception
	 */
	public static WebRowSet getWardNameDtl(boolean strOrderBy_p, String strHospitalCode_p)
			throws Exception {

		String strErrMsg = "";
		String strQry = "";
		String strModuleName = "";
		String strFileName = "";
		int nQryIndex = 0;
		HisDAO daoObj = null;
		WebRowSet ws = null;
		
		strQry = ipd.qryHandler_ipd.getQuery(1, "select.wardname.0");

		try {
			if (!strQry.equals("")) {
				daoObj = new HisDAO(strModuleName, "ipd_qry_mst.properties."
						+ strFileName);
				nQryIndex = daoObj.setQuery(strQry);
				daoObj.setQryValue(nQryIndex, 1, strHospitalCode_p);
				ws = daoObj.executeQry(nQryIndex);
			} else {
				strErrMsg = "DAOIpd.getWardNameDtl() -->Query is blank";
				throw new Exception(strErrMsg);
			}
		} catch (Exception e) {
			strErrMsg = "DAOIpd.getWardNameDtl() -->" + e.getMessage();
			throw new Exception(strErrMsg);
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

		return ws;
	}

	/**
	 * By this method user get room detail using ward name.
	 * 
	 * @param strOrderBy_p
	 * @param strHospitalCode_p
	 * @param wardname
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getRoomNameDtl(boolean strOrderBy_p, String strHospitalCode_p,
			String wardname) throws Exception {

		String strErrMsg = "";
		String strQry = "";
		String strModuleName = "";
		String strFileName = "";
		int nQryIndex = 0;
		HisDAO daoObj = null;
		WebRowSet ws = null;
		
		strQry = ipd.qryHandler_ipd.getQuery(1, "select.roomname.0");

		try {
			if (!strQry.equals("")) {
				daoObj = new HisDAO(strModuleName, "ipd_qry_mst.properties."
						+ strFileName);
				nQryIndex = daoObj.setQuery(strQry);

				daoObj.setQryValue(nQryIndex, 1, strHospitalCode_p);
				daoObj.setQryValue(nQryIndex, 2, wardname);
				ws = daoObj.executeQry(nQryIndex);
			} else {
				strErrMsg = "DAOIpd.getRoomNameDtl() -->Query is blank";
				throw new Exception(strErrMsg);
			}
		} catch (Exception e) {
			strErrMsg = "DAOIpd.getRoomNameDtl() -->" + e.getMessage();
			throw new Exception(strErrMsg);
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

		return ws;
	}

	/**
	 *  By this method user get left bed details using ward and room name
	 * @param strOrderBy_p
	 * @param deptunitname
	 * @param strHospitalCode_p
	 * @param wardname
	 * @param roomname
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getLBedDtl(boolean strOrderBy_p, String deptunitname,
			String strHospitalCode_p, String wardname, String roomname)
			throws Exception {
		String strErrMsg = "";
		String strQry = "";
		String strModuleName = "";
		String strFileName = "";
		int nQryIndex = 0;
		HisDAO daoObj = null;
		WebRowSet ws = null;

		strErrMsg = "";

		strQry = ipd.qryHandler_ipd.getQuery(1, "select.leftbed.0");
		try {
			if (!strQry.equals("")) {
				daoObj = new HisDAO(strModuleName, "ipd_qry_mst.properties."
						+ strFileName);
				nQryIndex = daoObj.setQuery(strQry);
			    
			    daoObj.setQryValue(nQryIndex, 1, deptunitname);
				daoObj.setQryValue(nQryIndex, 2, wardname);
				daoObj.setQryValue(nQryIndex, 3, deptunitname);
				daoObj.setQryValue(nQryIndex, 4, wardname);
				daoObj.setQryValue(nQryIndex, 5, strHospitalCode_p);
				//daoObj.setQryValue(nQryIndex, 3, wardname);
				daoObj.setQryValue(nQryIndex, 6, "999");
			    daoObj.setQryValue(nQryIndex, 7, roomname);
				ws = daoObj.executeQry(nQryIndex);
			} else {
				strErrMsg = "DAOIpd.getRoomDtl() -->Query is blank";
				throw new Exception(strErrMsg);
			}
		} catch (Exception e) {
			strErrMsg = "DAOIpd.getRoomDtl() -->" + e.getMessage();
			throw new Exception(strErrMsg);
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

		return ws;
	}

	/**
	 *  By this method user get selected right bed details using department,ward and room name
	 * @param strOrderBy_p
	 * @param deptunitname
	 * @param strHospitalCode_p
	 * @param wardname
	 * @param roomname
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getRBedDtl(boolean strOrderBy_p, String strHospitalCode_p,
			String deptunitname, String wardname, String roomname)
			throws Exception {
		String strErrMsg = "";
		String strQry = "";
		String strModuleName = "";
		String strFileName = "";
		int nQryIndex = 0;
		HisDAO daoObj = null;
		WebRowSet ws = null;
		/**
		 *  By this method user get left bed details using ward and room name
		 * @param strOrderBy_p
		 * @param deptunitname
		 * @param strHospitalCode_p
		 * @param wardname
		 * @param roomname
		 * @return
		 * @throws Exception
		 */

		strErrMsg = "";

		strQry = ipd.qryHandler_ipd.getQuery(1, "select.rghtbed.0");
		try {
			if (!strQry.equals("")) {
				daoObj = new HisDAO(strModuleName, "ipd_qry_mst.properties."
						+ strFileName);
				nQryIndex = daoObj.setQuery(strQry);

				daoObj.setQryValue(nQryIndex, 1, strHospitalCode_p);
				daoObj.setQryValue(nQryIndex, 2, deptunitname);
				daoObj.setQryValue(nQryIndex, 3, wardname);
	            daoObj.setQryValue(nQryIndex, 4, roomname);

				ws = daoObj.executeQry(nQryIndex);
			} else {
				strErrMsg = "DAOIpd.getRoomDtl() -->Query is blank";
				throw new Exception(strErrMsg);
			}
		} catch (Exception e) {
			strErrMsg = "DAOIpd.getRoomDtl() -->" + e.getMessage();
			throw new Exception(strErrMsg);
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

		return ws;
	}

	/**
	 * retrieves and executes insert Query
	 * 
	 * @param formBean -
	 *            Form Object of the Current Master
	 * @return true - If Record Inserted Successfully. <br>
	 *         false - If Record Not Inserted Successfully
	 * @throws Exception
	 */
	public static boolean insertQuery(DUWRBedMstVO formBean) throws Exception {

		boolean fReturnValue = false;
		int nQueryIndex = 0;
		HisDAO dao = null;
		try {
			String strTemp1[] = null;
			String strTemp4[] = null;
			String strQuery = null;
			dao = new HisDAO("DUWR Bed Master", "DUWRBedMstDAO.insertQuery()");
			strQuery = ipd.qryHandler_ipd.getQuery(1, "insert.duwrbed.0");

			String deptcode = formBean.getStrDeptUnitName();
			String temp = deptcode.substring(0, 3);
			String var = formBean.getStrWardName();
			strTemp1 = var.replace('^', '#').split("#");
			String val = formBean.getStrRoomName();
			strTemp4 = val.replace('^', '#').split("#");

			String temp2[] = formBean.getStrRBed();
			nQueryIndex = dao.setQuery(strQuery);
		//	String s[][] = new String[temp2.length][temp2.length];
		
			String temp3[] = null;
			
			int count = 0; 

			for (int i = 0; i < temp2.length; i++) {
				int l = temp2[i].replace('^', '#').split("#").length;

				temp3 = temp2[i].replace('^', '#').split("#");

			/*	if (l > 1) {
					
				} else { */

					dao.setQryValue(nQueryIndex, 1, formBean.getStrHospitalCode());
					// dao.setQryValue(nQueryIndex, 1, "108");
					dao.setQryValue(nQueryIndex, 2, formBean
							.getStrDeptUnitName());// deptunit code
					dao.setQryValue(nQueryIndex, 3, strTemp1[0]);// ward code
					dao.setQryValue(nQueryIndex, 4, strTemp4[0]);// room no
					dao.setQryValue(nQueryIndex, 5, temp3[0]);// bed code
					dao.setQryValue(nQueryIndex, 6, formBean
							.getStrHospitalCode());
					dao.setQryValue(nQueryIndex, 7, temp);
					dao.setQryValue(nQueryIndex, 8, formBean
							.getStrDeptUnitName());
					dao.setQryValue(nQueryIndex, 9, strTemp1[0]);
					dao.setQryValue(nQueryIndex, 10, formBean
							.getStrEffectiveFrom());
					dao.setQryValue(nQueryIndex, 11, formBean.getStrRemarks());
					dao.setQryValue(nQueryIndex, 12, formBean.getStrSeatId());
					// dao.setQryValue(nQueryIndex, 13, "1");
					dao.setQryValue(nQueryIndex, 13, strTemp4[0]);
					dao.setQryValue(nQueryIndex, 14, temp3[0]);
					// dao.setQryValue(nQueryIndex, 15, "102100202");
					count++;
					
					dao.execute(nQueryIndex, 0);
				/*} */
			}

			if(count != 0){
			synchronized (dao) {
				dao.fire();
				fReturnValue = true;
			}
			}
		} catch (Exception e) {
			fReturnValue = false;
			//e.printStackTrace();
			throw new Exception("DUWRBedMstDAO.insertQuery() -->"
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fReturnValue;
	}

	/**
	 * View the Record First to Last or Previous Record.
	 * 
	 * @param chk1 -Primary Keys Concatenated with '@'.
	 * @param vo -Form Object of the Current Master.
	 * @return true - if Record View Successfully. <br>
	 * 		   false - if Record Not View Successfully.
	 * @throws Exception
	 */
	public static boolean viewQuery(String chk1, DUWRBedMstVO vo)
			throws Exception {

		boolean retValue = false;
		HisDAO hisdao = new HisDAO("ipd", "DUWRBedMstDAO");
		int nIndex1;
		int nIndex2;
		WebRowSet wb = null;
		WebRowSet wb2 = null;

		String temp[] = chk1.replaceAll("@", "#").split("#");

		try {
			String strQuery1 = qryHandler_ipd.getQuery(1, "select.duwrbed.1");// VIEW
																				// QUERY
			String strQuery2 = qryHandler_ipd.getQuery(1, "select.rghtbed.1");// BED
																				// QUERY

			nIndex1 = hisdao.setQuery(strQuery1);

			hisdao.setQryValue(nIndex1, 1, temp[0]);
			hisdao.setQryValue(nIndex1, 2, temp[1]);
			hisdao.setQryValue(nIndex1, 3, temp[2]);
			hisdao.setQryValue(nIndex1, 4, temp[3]);
			hisdao.setQryValue(nIndex1, 5, temp[4]);
			hisdao.setQryValue(nIndex1, 6, "1");

			wb = hisdao.executeQry(nIndex1);

			if (wb.next()) {

				vo.setStrDeptUnitName(wb.getString(1) + " / " + wb.getString(2));
				vo.setStrWardName(wb.getString(3));
				vo.setStrRoomName(wb.getString(4));
				vo.setStrBedName(wb.getString(5));
				vo.setStrEffectiveFrom(wb.getString(6));
				vo.setStrIsValid(wb.getString(7));

			}

			nIndex2 = hisdao.setQuery(strQuery2);
			hisdao.setQryValue(nIndex2, 1, temp[0]);
			hisdao.setQryValue(nIndex2, 2, temp[1]);
			hisdao.setQryValue(nIndex2, 3, temp[2]);
			hisdao.setQryValue(nIndex2, 4, temp[3]);
			wb2 = hisdao.executeQry(nIndex2);

			vo.setWstemp(wb2);
			// String[] bed = DUWRBedMstDAO.getMultiRowContent(wb,1);
			// wb.beforeFirst();
			// vo.setStrLRBed(bed);

		} catch (Exception e) {
			 e.printStackTrace();
			retValue = false;
			throw new Exception("ipd.DUWRBedMstDAO.viewQuery()-->"
					+ e.getMessage());

		} finally {
			hisdao.free();
			hisdao = null;
		}
		return retValue;
	}

	
	/**
	 * retrieves and executes modify Query
	 * @param strChk - Primary Keys Concatenated with '@'.
	 * @param formBean - Form Object of the Current Master 
	 * @throws Exception
	 */
	public static void modifyQuery(String strChk, DUWRBedMstVO formBean)
			throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex;

		try {
			String strTemp1[] = null;
			String strTemp2[] = null;
			dao = new HisDAO("DUWR Bed Master", "DUWRBedMstDAO.modifyQuery()");
			
			strTemp1 = strChk.replace('@', '#').split("#");
			
			strTemp2 = strTemp1[5].replace('$', '#').split("#");
			strQuery = ipd.qryHandler_ipd.getQuery(1, "select.duwrbed.3");
			
			nQryIndex = dao.setQuery(strQuery);
			
			dao.setQryValue(nQryIndex, 1, strTemp1[0]);
			
			dao.setQryValue(nQryIndex, 2, strTemp2[0]);
			dao.setQryValue(nQryIndex, 3, strTemp1[1]);
			dao.setQryValue(nQryIndex, 4, strTemp1[2]);
			dao.setQryValue(nQryIndex, 5, strTemp1[3]);
			dao.setQryValue(nQryIndex, 6, strTemp1[4]);

			WebRowSet web = dao.executeQry(nQryIndex);

			if (web.next()) {

				formBean.setStrDeptUnitName(web.getString(1));
				formBean.setStrWardName(web.getString(2));
				formBean.setStrRoomName(web.getString(3));
				formBean.setStrBedName(web.getString(4));
				formBean.setStrEffectiveFrom(web.getString(5));
				formBean.setStrRemarks(web.getString(6));
				formBean.setStrIsValid(web.getString(7));

			} else {

				// System.out.println("web is false ");
			}

		} catch (Exception e) {
			//e.printStackTrace();
			throw new Exception("DUWRBedMstDAO.modifyQuery()-->"
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}

	/**
	 * Logically Deletes Current Record and Inserts New Record with the Modified
	 * Data.
	 * 
	 * @param chk -
	 *            Primary Keys Concatenated with '@'.
	 * @param formBean -
	 *            Form Object of the Current Master.
	 * @return true - if Record Updated Successfully. <br>
	 *         false - if Record Not Updated Successfully.
	 */
	public static boolean updateQuery(String chk, DUWRBedMstVO formBean)
			throws Exception {

		boolean fReturnValue = false;
		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex = 0;
		
		try {
			String strTemp1[] = null;
			dao = new HisDAO("DUWR Bed Master ", "DUWRBedMstDAO.updateQuery()");
			chk = chk.replace('@', '$');
			strQuery = ipd.qryHandler_ipd.getQuery(1, "update.duwrbed.1");
			nQryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nQryIndex, 1, formBean.getStrEffectiveFrom());
			dao.setQryValue(nQryIndex, 2, formBean.getStrLastModSeatId());
			// dao.setQryValue(nQryIndex, 2, formBean.getStrLastModSeatId());
			dao.setQryValue(nQryIndex, 3, formBean.getStrRemarks());
		
			dao.setQryValue(nQryIndex, 4, formBean.getStrIsValid());
			strTemp1 = chk.replace('$', '#').split("#");
			
			dao.setQryValue(nQryIndex, 5, strTemp1[0]);
			dao.setQryValue(nQryIndex, 6, strTemp1[1]);
			dao.setQryValue(nQryIndex, 7, strTemp1[2]);
			dao.setQryValue(nQryIndex, 8, strTemp1[3]);
			dao.setQryValue(nQryIndex, 9, strTemp1[4]);
			dao.setQryValue(nQryIndex, 10, strTemp1[5]);
			dao.execute(nQryIndex, 0);
			
			synchronized (dao) {
				dao.fire();
			}
			fReturnValue = true;
		} catch (Exception e) {

			fReturnValue = false;
			throw new Exception("DUWRBedMstDAO.updateQuery()-->"
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		
		return fReturnValue;

	}
	public static boolean updateQuerybeforeinsert ( DUWRBedMstVO formBean)
			throws Exception {

		boolean fReturnValue = false;
		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex = 0;

		try {
			String strTemp1[] = null;
			String strTemp4[] = null;
			dao = new HisDAO("DUWR Bed Master ", "DUWRBedMstDAO.updateQuerybeforeinsert()");
		   
			strQuery = ipd.qryHandler_ipd.getQuery(1, "update.duwrbedmst.0");
			String temp2[] = formBean.getStrRBed();
			nQryIndex = dao.setQuery(strQuery);
			
			String var = formBean.getStrWardName();
			strTemp1 = var.replace('^', '#').split("#");
			
			String val = formBean.getStrRoomName();
			strTemp4 = val.replace('^', '#').split("#");
			
			String temp3[] = null;
			
			int count = 0; 

			for (int i = 0; i < temp2.length; i++) {
				int l = temp2[i].replace('^', '#').split("#").length;

				temp3 = temp2[i].replace('^', '#').split("#");
			
			dao.setQryValue(nQryIndex, 1, formBean.getStrHospitalCode());
			// dao.setQryValue(nQueryIndex, 1, "108");
			dao.setQryValue(nQryIndex, 2, formBean
					.getStrDeptUnitName());// deptunit code
			dao.setQryValue(nQryIndex, 3, strTemp1[0]);// ward code
			dao.setQryValue(nQryIndex, 4, strTemp4[0]);// room no
			dao.setQryValue(nQryIndex, 5, temp3[0]);// bed code
			dao.setQryValue(nQryIndex, 6, formBean.getStrSeatId());
			dao.execute(nQryIndex, 0);
			}	
			synchronized (dao) {
				dao.fire();
			}
			fReturnValue = true;
		} catch (Exception e) {
		    e.printStackTrace();
			fReturnValue = false;
			throw new Exception("DUWRBedMstDAO.updateQuerybeforeinsert()-->"
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return fReturnValue;

		}
}
