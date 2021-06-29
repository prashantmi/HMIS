package ipd.masters.bo;

import ipd.masters.dao.DepartmentWardDiseaseMstDAO;
import ipd.masters.vo.DepartmentWardDiseaseMstVO;
import hisglobal.exceptions.HisException;

public class DepartmentWardDiseaseMstBO {
	/**
	 * invokes DAODepartmentWardDiseaseMst's insertQuery Method.
	 * 
	 * @param formBean -
	 *            Form Object of the Current Master
	 */
	public void insert(DepartmentWardDiseaseMstVO formBean) {

		try {

			boolean fReturnValue = DepartmentWardDiseaseMstDAO
					.insertQuery(formBean);

			if (fReturnValue) {
				formBean.setStrMsg("Data Inserted Successfully");
			}

		} catch (Exception e) {
			formBean.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Deparment ward Disease type", "BODepartmentWardDiseaseMst-->insert()", formBean.getStrErr());
			   formBean.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			formBean.setStrErr("Row Not Inserted");
			new HisException("Department Ward Master",
					"BODepartmentWardDiseaseMst.ADD",
					"BODepartmentWardDiseaseMst.insert() -->" + e.getMessage());
		}

	}

	/**
	 * invokes DAODepartmentWardDiseaseMst's modifyQuery Method.
	 * 
	 * @param strChk -
	 *            Primary Keys Concatenated with '@'.
	 * @param formBean -
	 *            Form Object of the Current Master
	 */
	public void modify(String strChk, DepartmentWardDiseaseMstVO formBean) {

		try {
			DepartmentWardDiseaseMstDAO.modifyQuery(strChk, formBean);
		} catch (Exception e) {

			formBean.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Deparment ward Disease type", "BODepartmentWardDiseaseMst-->modify()", formBean.getStrErr());
			   formBean.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}

	/**
	 *
	 * Update method 
	 *
	 * @param strChk
	 * @param formBean
	 * @return true - if Record Updated Successfully<br>
	 * false - if Record Not Updated Successfully
	 */
	public boolean update(String strChk, DepartmentWardDiseaseMstVO formBean) {

		boolean fReturnValue = true;

		try {
			fReturnValue = DepartmentWardDiseaseMstDAO.updateQuery(strChk,
					formBean);
		} catch (Exception e) {
			formBean.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Deparment ward Disease type", "BODepartmentWardDiseaseMst-->update()", formBean.getStrErr());
			   formBean.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			formBean.setStrErr("Row Not Updated");
			new HisException("Department Ward Disease Master",
					"BODepartmentWardDiseaseMst.UPDATE",
					"BODepartmentWardDiseaseMst.update()-->" + e.getMessage());
		}

		if (!fReturnValue) {
			formBean.setStrErr("Record Not Updated");
		}

		return fReturnValue;
	}

}
