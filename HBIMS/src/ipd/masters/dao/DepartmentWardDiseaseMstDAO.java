package ipd.masters.dao;

import ipd.masters.vo.DepartmentWardDiseaseMstVO;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;


public class DepartmentWardDiseaseMstDAO {

	/**
	 * retrieves and executes insert Query
	 * @param formBean - Form Object of the Current Master
	 * @return true - If Record Inserted Successfully. <br> false - If Record Not Inserted Successfully 
	 * @throws Exception
	 */
	public static boolean insertQuery(DepartmentWardDiseaseMstVO formBean)throws Exception {
	
		boolean fReturnValue = true;
		int nQueryIndex = 0;
		HisDAO dao = null;
		String strQuery = null;
		dao = new HisDAO("Department Ward Disease Master","DAODepartmentWardDiseaseMst.insertQuery()");
		strQuery = ipd.qryHandler_ipd.getQuery(1, "insert.departmentWardDiseaseMst.0");
		nQueryIndex = dao.setQuery(strQuery);

		try {

			if(formBean.getStrDisease().length != 0){
				for (int i = 0; i < formBean.getStrDisease().length; i++) {

					dao.setQryValue(nQueryIndex, 1, formBean.getStrDepartment().replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 2, formBean.getStrUnit().replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 3, formBean.getStrDeptWardValue().replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 4, formBean.getStrDepartment().replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 5, formBean.getStrUnit().replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 6, formBean.getStrDeptWardValue().replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 7, formBean.getStrDisease()[i].replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 8, formBean.getStrDisease()[i].replace('^', '#').split("#")[0]);
					dao.setQryValue(nQueryIndex, 9, formBean.getStrEffectiveFromDate()[i]);
					dao.setQryValue(nQueryIndex, 10, formBean.getStrRemarks());
					dao.setQryValue(nQueryIndex, 11, formBean.getStrSeatId());
					dao.setQryValue(nQueryIndex, 12, "1");

					dao.execute(nQueryIndex, 0);

				}
				
				synchronized (dao) {
					dao.fire();
				}
			}
		} catch (Exception e) {
			fReturnValue = false;
			throw new Exception("DAODepartmentWardDiseaseMst.insertQuery() -->"+ e.getMessage());
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
	public static void modifyQuery(String strChk, DepartmentWardDiseaseMstVO formBean) throws Exception{

		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex;

		try {
			String strTemp1[] = null;
			dao = new HisDAO("Department Ward Disease Master", "DAODepartmentWardDiseaseMst.modifyQuery()");
			String strTemp[] = strChk.replace('@', '#').split("#");
			strQuery = ipd.qryHandler_ipd.getQuery(1, "select.departmentWardDiseaseMst.2");
			nQryIndex = dao.setQuery(strQuery);

			for (int i = 0; i < strTemp.length; i++) {

				strTemp1 = strTemp[i].replace('$', '#').split("#");
				dao.setQryValue(nQryIndex, (i + 1), strTemp1[0]);
			}
			WebRowSet web = dao.executeQry(nQryIndex);

			while (web.next()) {

				formBean.setStrDeptWardName(web.getString(1));
				formBean.setStrDepartment(web.getString(2));
				formBean.setStrUnit(web.getString(3));
				formBean.setStrDiseaseTempName(web.getString(4));
				formBean.setStrEffTempDate(web.getString(5));
				formBean.setStrRemarks(web.getString(6));
				formBean.setStrActive(web.getString(7));
				
			}

		} catch (Exception e) {
			throw new Exception("DAODepartmentWardDiseaseMst.modifyQuery()-->"+e.getMessage());
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
	public static boolean updateQuery(String chk, DepartmentWardDiseaseMstVO bean)throws Exception{

		boolean fReturnValue = false;
		HisDAO dao = null;
		String strQuery = null;
		int nQryIndex = 0;

		try {
			String strTemp1[] = null;
			dao = new HisDAO("Department Ward Disease Master ", "DAODepartmentWardDiseaseMst.updateQuery()");
			String strTemp[] = chk.replace('@', '#').split("#");
			strQuery = ipd.qryHandler_ipd.getQuery(1,"update.departmentWardDiseaseMst.0");
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
			throw new Exception("DAODepartmentWardDiseaseMst.updateQuery()-->"+e.getMessage());
		} finally {
			dao.free();
			dao = null;
		}
		return fReturnValue;

	}
	
	
}
