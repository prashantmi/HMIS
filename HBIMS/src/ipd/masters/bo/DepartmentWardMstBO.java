package ipd.masters.bo;

import ipd.masters.dao.DepartmentWardMstDAO;
import ipd.masters.vo.DepartmentWardMstVO;
import hisglobal.exceptions.HisException;

public class DepartmentWardMstBO {

	/**
	 * invokes DAODepartmentWardMst insertQuery Method.
	 * @param formBean - Form Object of the Current Master
	 */
	public void insert(DepartmentWardMstVO formBean) {

		try {

			boolean fReturnValue = DepartmentWardMstDAO.insertQuery(formBean);

			if (fReturnValue) {
				formBean.setStrMsg("Data Inserted Successfully");
			}

		} catch (Exception e) {
			formBean.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Department Ward Master", "BODepartmentWardMst-->insert()", formBean.getStrErr());
			   formBean.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			formBean.setStrErr("Row Not Inserted");
			new HisException("Department Ward Master",
					"BODepartmentWardMst.ADD",
					"BODepartmentWardMst.insert() -->" + e.getMessage());
		}

	}

	/**
	 * invokes DAODepartmentWardMst modifyQuery Method.
	 * @param strChk - Primary Keys Concatenated with '@'. 
	 * @param formBean - Form Object of the Current Master
	 */
	public void modify(String strChk, DepartmentWardMstVO formBean) {

		try {
			DepartmentWardMstDAO.modifyQuery(strChk, formBean);
		} catch (Exception e) {
			formBean.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Department Ward Master", "BODepartmentWardMst-->modify()", formBean.getStrErr());
			   formBean.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}

	/**
	 * Update method returns true if Record Updated Successfully false if Record
	 * Not Updated Successfully
	 */
	public boolean update(String strChk, DepartmentWardMstVO formBean) {

		boolean fReturnValue = true;

		try {
			fReturnValue = DepartmentWardMstDAO.updateQuery(strChk, formBean);
		} catch (Exception e) {
			formBean.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Department Ward Master", "BODepartmentWardMst-->update()", formBean.getStrErr());
			   formBean.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			formBean.setStrErr("Row Not Updated");
			new HisException("Department Ward Master",
					"BODepartmentWardMst.UPDATE",
					"BODepartmentWardMst.update() -->" + e.getMessage());
		}

		if (!fReturnValue) {
			formBean.setStrErr("Record Not Updated");
		}

		return fReturnValue;
	}

	
	
}
