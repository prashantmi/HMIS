package ipd.masters.dao;

import ipd.masters.vo.DepartmentWardMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class DepartmentWardMstDAO {

	/**
	 * retrieves and executes insert Query
	 * @param formBean - Form Object of the Current Master
	 * @return true - If Record Inserted Successfully. <br> false - If Record Not Inserted Successfully 
	 * @throws Exception
	 */
	public static boolean insertQuery(DepartmentWardMstVO formBean)throws Exception {

		boolean fReturnValue = true;
		int nQueryIndex = 0;
		HisDAO dao = null;
		String strQuery = null;
		dao = new HisDAO("Department Ward Master","DAODepartmentWardMst.insertQuery()");
		strQuery = ipd.qryHandler_ipd.getQuery(1, "insert.departmentWardMst.0");
		nQueryIndex = dao.setQuery(strQuery);

		try {

			if(formBean.getStrDepartment().length != 0){
				for (int i = 0; i < formBean.getStrDepartment().length; i++) {

					dao.setQryValue(nQueryIndex, 1, formBean.getStrDepartment()[i].replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 2, formBean.getStrUnit()[i].replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 3, formBean.getStrDeptWardValue().replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 4, formBean.getStrDepartment()[i].replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 5, formBean.getStrUnit()[i].replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 6, formBean.getStrDeptWardValue().replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 7, formBean.getStrEffectiveFromDate()[i]);
					dao.setQryValue(nQueryIndex, 8, formBean.getStrRemarks());
					dao.setQryValue(nQueryIndex, 9, formBean.getStrSeatId());
					dao.setQryValue(nQueryIndex, 10, "1");

					dao.execute(nQueryIndex, 0);

				}
				
				synchronized (dao) {
					dao.fire();
				}
			}
		} catch (Exception e) {
			fReturnValue = false;
			throw new Exception("DAODepartmentWardMst.insertQuery() -->"+ e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}

		return fReturnValue;
	}

	/**
	 * retrieves and executes modify Query
	 * @param strChk - Primary Keys Concatenated with '@'.
	 * @param formBean - Form Object of the Current Master 
	 * @throws Exception
	 */
	public static void modifyQuery(String strChk, DepartmentWardMstVO formBean)throws Exception {

		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex;

		try {
			String strTemp1[] = null;
			dao = new HisDAO("Department Ward Master", "DAODepartmentWardMst.modifyQuery()");
			String strTemp[] = strChk.replace('@', '#').split("#");
			strQuery = ipd.qryHandler_ipd.getQuery(1, "select.departmentWardMst.2");
			nQryIndex = dao.setQuery(strQuery);

			for (int i = 0; i < strTemp.length; i++) {

				strTemp1 = strTemp[i].replace('$', '#').split("#");
				dao.setQryValue(nQryIndex, (i + 1), strTemp1[0]);
			}
			WebRowSet web = dao.executeQry(nQryIndex);

			while (web.next()) {

				formBean.setStrDeptWardName(web.getString(1));
				formBean.setStrDeptTempName(web.getString(2));
				formBean.setStrUnitTempName(web.getString(3));
				formBean.setStrEffTempDate(web.getString(4));
				formBean.setStrRemarks(web.getString(5));
				formBean.setStrActive(web.getString(6));
				
			}

		} catch (Exception e) {
			//e.printStackTrace();
			throw new Exception("DAODepartmentWardMst.modifyQuery()-->"+
					e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
	}
	
	
	/**
	 * updates the Current Record. 
	 * @param chk - Primary Keys Concatenated with '@'.
	 * @param bean - Form Object of the Current Master.
	 * @return true - if Record Updated Successfully. <br>
	 * 		   false - if Record Not Updated Successfully.	
	 */
	public static boolean updateQuery(String chk, DepartmentWardMstVO bean) throws Exception{

		boolean fReturnValue = false;
		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex = 0;

		try {
			String strTemp1[] = null;
			dao = new HisDAO("Department Ward Master ", "DAODepartmentWardMst.updateQuery()");
			String strTemp[] = chk.replace('@', '#').split("#");
			
			strQuery = ipd.qryHandler_ipd.getQuery(1,
					"update.departmentWardMst.0");
			nQryIndex = dao.setQuery(strQuery);

			dao.setQryValue(nQryIndex, 1, bean.getStrEffTempDate());
			dao.setQryValue(nQryIndex, 2, bean.getStrRemarks());
			dao.setQryValue(nQryIndex, 3, bean.getStrLastModSeatId());
			dao.setQryValue(nQryIndex, 4, bean.getStrActive());
		
			for (int i = 0; i < strTemp.length; i++) {
				strTemp1= strTemp[i].replace('$', '#').split("#");
				dao.setQryValue(nQryIndex, (i + 5), strTemp1[0]);
			}

			dao.execute(0, nQryIndex);

			synchronized (dao) {
				dao.fire();
			}
			fReturnValue = true;
		} catch (Exception e) {
			
			fReturnValue = false;
			throw new Exception("DAODepartmentWardMst.updateQuery()-->"+ e
					.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fReturnValue;

	}
	
}
