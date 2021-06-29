package ipd.masters.dao;

import ipd.qryHandler_ipd;
import ipd.masters.vo.DeptDocumentMstVO;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;

import hisglobal.transactionmgnt.HisDAO;

public class DeptDocumentMstDAO {
	
	/**
	 * By this method user gets the combo value of department detail.
	 *  
	 * @param orderBy boolean value either true or false
	 * @param hsptlCode - String variable
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getDeptartmentDtl(boolean orderBy, String hsptlCode)
	throws Exception {

			String strErrMsg = "";
			String strQry = "";
			String strModuleName = "";
			String strFileName = "";
			int nQryIndex = 0;
			HisDAO daoObj = null;
			WebRowSet ws = null;
						
			strQry = ipd.qryHandler_ipd.getQuery(1, "combo.department.0");
			
			try {
				if (!strQry.equals("")) {
					daoObj = new HisDAO(strModuleName, "ipd_qry_mst.properties."
							+ strFileName);
					nQryIndex = daoObj.setQuery(strQry);
					daoObj.setQryValue(nQryIndex, 1, hsptlCode);
					ws = daoObj.executeQry(nQryIndex);
				} else {
					strErrMsg = "DAOIpd.getDeptartmentDtl() -->Query is blank";
					throw new Exception(strErrMsg);
				}
			} catch (Exception e) {
				strErrMsg = "DAOIpd.getDeptartmentDtl() -->" + e.getMessage();
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
	 * By this method user gets the combo value of document detail.
	 *  
	 * @param orderBy boolean value either true or false
	 * @param hsptlCode - String variable
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getDocumentDtl(boolean orderBy, String hsptlCode)
	throws Exception {

			String strErrMsg = "";
			String strQry = "";
			String strModuleName = "";
			String strFileName = "";
			int nQryIndex = 0;
			HisDAO daoObj = null;
			WebRowSet ws = null;
			
			strQry = ipd.qryHandler_ipd.getQuery(1, "combo.document.0");
			
			try {
				if (!strQry.equals("")) {
					daoObj = new HisDAO(strModuleName, "ipd_qry_mst.properties."
							+ strFileName);
					nQryIndex = daoObj.setQuery(strQry);
					daoObj.setQryValue(nQryIndex, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
					ws = daoObj.executeQry(nQryIndex);
				} else {
					strErrMsg = "DAOIpd.getDocumentDtl() -->Query is blank";
					throw new Exception(strErrMsg);
				}
			} catch (Exception e) {
				strErrMsg = "DAOIpd.getDocumentDtl() -->" + e.getMessage();
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
	 * By this method user gets the combo value of component detail.
	 *  
	 * @param orderBy boolean value either true or false
	 * @param hsptlCode - String variable
	 * @return
	 * @throws Exception
	 */
	public static WebRowSet getComponentDtl(boolean orderBy, String hsptlCode)
	throws Exception {

			String strErrMsg = "";
			String strQry = "";
			String strModuleName = "";
			String strFileName = "";
			int nQryIndex = 0;
			HisDAO daoObj = null;
			WebRowSet ws = null;
		
			strQry = ipd.qryHandler_ipd.getQuery(1, "combo.component.0");
			
			try {
				if (!strQry.equals("")) {
					daoObj = new HisDAO(strModuleName, "ipd_qry_mst.properties."
							+ strFileName);
					nQryIndex = daoObj.setQuery(strQry);
					daoObj.setQryValue(nQryIndex, 1, hsptlCode);
					ws = daoObj.executeQry(nQryIndex);
				} else {
					strErrMsg = "DAOIpd.getComponentDtl() -->Query is blank";
					throw new Exception(strErrMsg);
				}
			} catch (Exception e) {
				strErrMsg = "DAOIpd.getComponentDtl() -->" + e.getMessage();
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
	 * @param vo -
	 *            Form Object of the Current Master
	 * @return true - If Record Inserted Successfully. <br>
	 *         false - If Record Not Inserted Successfully
	 * @throws Exception
	 */
	
	public static boolean insertQuery(DeptDocumentMstVO vo) throws Exception {
		HisDAO dao = null;
		int nqryIndex;
		boolean fretValue = false;
		String strquery = new String();
		
		try {
			dao = new HisDAO("ipd", "DAODeptDocumentMst");
			String strtemp[]=vo.getStrComponentCode();
			String strtemp1[]=vo.getStrComponentFileName();
			String strtemp2[]=vo.getStrComponentRemark();
			strquery = ipd.qryHandler_ipd.getQuery(1, "insert.deptdocMst.0");
			nqryIndex = dao.setQuery(strquery);
			for(int i=0;i<strtemp.length;i++)
			{
				dao.setQryValue(nqryIndex, 1, vo.getStrDeptCode());
				dao.setQryValue(nqryIndex, 2, vo.getStrDocumentCode());
				dao.setQryValue(nqryIndex, 3, strtemp[i]);
				dao.setQryValue(nqryIndex, 4, vo.getStrHospitalCode());
				//dao.setQryValue(nqryIndex, 4, "108");
				
				
				dao.setQryValue(nqryIndex, 5, vo.getStrDeptCode());
				dao.setQryValue(nqryIndex, 6, vo.getStrDocumentCode());
				dao.setQryValue(nqryIndex, 7, strtemp[i]);
				dao.setQryValue(nqryIndex, 8, strtemp1[i]);
				dao.setQryValue(nqryIndex, 9, strtemp2[i]);
				dao.setQryValue(nqryIndex, 10, vo.getStrEffectiveFrom());
				dao.setQryValue(nqryIndex, 11, vo.getStrSeatId());
				//dao.setQryValue(nqryIndex, 11, "1001");
				dao.setQryValue(nqryIndex, 12, vo.getStrIsDefault());
				dao.setQryValue(nqryIndex, 13, vo.getStrHospitalCode());
				//dao.setQryValue(nqryIndex, 13, "108");
				
				dao.execute(nqryIndex, 0);
				
			    }	
				synchronized (dao) {
					dao.fire();
					fretValue = true;
				}
			
		} catch (Exception e) {
			
			fretValue = false;
			throw new Exception("DAODeptDocumentMst.insertQuery(vo) --> "
					+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fretValue;
	}
	
	/**
	 * retrieves and executes modify Query
	 * @param strChk - Primary Keys Concatenated with '@'.
	 * @param formBean - Form Object of the Current Master 
	 * @throws Exception
	 */
	public static void modifyQuery(String strChk, DeptDocumentMstVO formBean)
	throws Exception {

			HisDAO dao = null;
			String strQuery = null;
			int nQryIndex;
			
			try {
				String strTemp1[] = null;
				dao = new HisDAO("ADT", "DAODeptDocumentMst.modifyQuery()");
				strChk = strChk.replace('@', '$');
				strTemp1 = strChk.replace('$', '#').split("#");
				strQuery = ipd.qryHandler_ipd.getQuery(1, "select.deptdocMst.3");
				
				nQryIndex = dao.setQuery(strQuery);
				dao.setQryValue(nQryIndex, 1, strTemp1[0]);//Dept code
				dao.setQryValue(nQryIndex, 2, strTemp1[1]);//Doc code
				dao.setQryValue(nQryIndex, 3, strTemp1[2]);//Comp code
				dao.setQryValue(nQryIndex, 4, strTemp1[3]);//Sl no
				dao.setQryValue(nQryIndex, 5, strTemp1[4]);//hos code
				WebRowSet web = dao.executeQry(nQryIndex);
			
				if (web.next()) {
					formBean.setStrDeptName(web.getString(1));
					formBean.setStrDocumentName(web.getString(2));
					formBean.setStrComponentName(web.getString(3));
					formBean.setStrComponentFile(web.getString(4));
					formBean.setStrRemarks(web.getString(5));
					formBean.setStrEffectiveFrom(web.getString(6));
					formBean.setStrIsValid(web.getString(7));
					formBean.setStrIsDefault(web.getString(8));
				} else {
			
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("DAODeptDocumentMst.modifyQuery()-->"
						+ e.getMessage());
			} finally {
				dao.free();
				dao = null;
			}
		}
	
	/**
	 * updates the Current Record. 
	 * @param chk - Primary Keys Concatenated with '@'.
	 * @param formBean - Form Object of the Current Master.
	 * @return true - if Record Updated Successfully. <br>
	 * 		   false - if Record Not Updated Successfully.	
	 */
	public static boolean updateQuery(String chk, DeptDocumentMstVO formBean)
			throws Exception {
		
					boolean fReturnValue = true;
					HisDAO dao = null;
					String strQuery = null;
					int nQryIndex = 0;
					try {
						String strTemp1[] = null;
						dao = new HisDAO("Department Document Master",
								"DAODeptDocumentMst.updateQuery()");
						strQuery = ipd.qryHandler_ipd.getQuery(1, "update.deptdocMst.0");
						chk = chk.replace('@', '$');
						strTemp1 = chk.replace('$', '#').split("#");
						nQryIndex = dao.setQuery(strQuery);
						
						dao.setQryValue(nQryIndex, 1, formBean.getStrComponentFile());
						dao.setQryValue(nQryIndex, 2, formBean.getStrRemarks());
						dao.setQryValue(nQryIndex, 3, formBean.getStrEffectiveFrom());
						dao.setQryValue(nQryIndex, 4, formBean.getStrLastModifiedSeatId());
						dao.setQryValue(nQryIndex, 5, formBean.getStrIsValid());
						dao.setQryValue(nQryIndex, 6, formBean.getStrIsDefault());
						
						dao.setQryValue(nQryIndex, 7, strTemp1[0]);//dept code
						dao.setQryValue(nQryIndex, 8, strTemp1[1]);//document code
						dao.setQryValue(nQryIndex, 9, strTemp1[2]);//component code
						dao.setQryValue(nQryIndex, 10, strTemp1[3]);//sl no
						dao.setQryValue(nQryIndex, 11, strTemp1[4]);//hs code
						dao.execute(nQryIndex, 0);
						
						synchronized (dao) {
							dao.fire();
						}
						fReturnValue = true;
					} catch (Exception e) {
					
						fReturnValue = false;
						throw new Exception("DAODeptDocumentMst.updateQuery()-->"
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
	public static boolean viewQuery(String chk1, DeptDocumentMstVO vo)
	throws Exception {

				boolean retValue = false;
				HisDAO hisdao = null;
				int nIndex1;
				int nIndex2;
				WebRowSet wb = null;
				WebRowSet wb2 = null;
				try {
					String temp[]=null;
					hisdao = new HisDAO("ipd", "DAODeptDocumentMst");
					
					String strQuery1 = qryHandler_ipd.getQuery(1, "select.deptdocMst.1");// VIEW QUERY
				
					String strQuery2 = qryHandler_ipd.getQuery(1, "select.deptdocMst.9");// VALUES QUERY
					chk1=chk1.replace('@', '$');
					temp = chk1.replace('$', '#').split("#");
					nIndex1 = hisdao.setQuery(strQuery1);
					
					hisdao.setQryValue(nIndex1, 1, temp[0]);//dept code
					hisdao.setQryValue(nIndex1, 2, temp[1]);//document code
					hisdao.setQryValue(nIndex1, 3, temp[2]);//component code
					hisdao.setQryValue(nIndex1, 4, temp[3]);//sl no
					hisdao.setQryValue(nIndex1, 5, temp[4]);//hs code
					
					wb = hisdao.executeQry(nIndex1);
					if (wb.next()) 
					{
						vo.setStrDeptName(wb.getString(1));
						vo.setStrDocumentName(wb.getString(2));
						vo.setStrComponentName(wb.getString(3));
						vo.setStrComponentFile(wb.getString(4));
						vo.setStrIsValid(wb.getString(5));
						vo.setStrRemarks(wb.getString(6));
						vo.setStrEffectiveFrom(wb.getString(7));
						vo.setStrIsDefault(wb.getString(8));
					}
					nIndex2 = hisdao.setQuery(strQuery2);
					hisdao.setQryValue(nIndex2, 1, temp[0]);//dept code
					hisdao.setQryValue(nIndex2, 2, temp[1]);//document code
					hisdao.setQryValue(nIndex2, 3, temp[2]);//sl no
					hisdao.setQryValue(nIndex2, 4, temp[3]);//hs code
					
					wb2 = hisdao.executeQry(nIndex2);
					
					/*if(wb2.next())
					{
						vo.setStrComponentName(wb2.getString(1));
						vo.setStrComponentFile(wb2.getString(2));
						vo.setStrIsValid(wb2.getString(3));
						vo.setStrRemarks(wb2.getString(4));
					}*/
					vo.setWsComponent(wb2);
				
				} catch (Exception e) {
					retValue = false;
					throw new Exception("ipd.DAODeptDocumentMst.viewQuery()-->"
							+ e.getMessage());
				} finally {
					hisdao.free();
					hisdao = null;
				}
				return retValue;
			}
	
	/**
	 * This Method is for to get Component code from FormBean.
	 *  
	 * @param vo - Form Object of the Current Master
	 * @throws Exception
	 */
	public static void getComponentName(DeptDocumentMstVO vo) 
	throws Exception{
			HisDAO dao = null;
			int nqryIndex;
			String strQuery = new String();
			WebRowSet ws=null;
			
			
			dao = new HisDAO("ipd", "DAODeptDocumentMst");
		try {
			strQuery = ipd.qryHandler_ipd.getQuery(1, "select.deptdocMst.8");
			nqryIndex = dao.setQuery(strQuery);
			dao.setQryValue(nqryIndex, 1, vo.getStrDeptCode());
			dao.setQryValue(nqryIndex, 2, vo.getStrDocumentCode());
			dao.setQryValue(nqryIndex, 3, vo.getStrHospitalCode());
			ws=dao.executeQry(nqryIndex);
			
			vo.setComponent(ws);
			}
		catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
