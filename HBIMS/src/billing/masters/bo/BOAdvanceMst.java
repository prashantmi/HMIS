package billing.masters.bo;

import billing.masters.controller.fb.VOAdvanceMst;
import billing.masters.dao.DAOAdvanceMst;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericVO;

public class BOAdvanceMst {

	/**
	 * used to invoke insertQuery and returns whether Data Inserted.
	 * 
	 * @param formBean
	 * @return true - If Data Inserted Successfully.<br>
	 *         false - If Data Not Inserted Successfully.
	 */
	public boolean insert(VOAdvanceMst formBean) {

		boolean fReturnValue = false;

		try {

			fReturnValue = DAOAdvanceMst.checkForInsert(formBean);

			if (fReturnValue) {

				fReturnValue = DAOAdvanceMst.insertQuery(formBean);

				if (fReturnValue) {

					formBean.setStrMsg("Record Saved Successfully!");
				}

			} else {

				formBean.setStrWarning("Data Already Exists");
			}

		} catch (Exception e) {

			HisException eObj = new HisException("Billing",
					"AdvanceMaster->insert()", "BOAdvanceMst.insert() -->"
							+ e.getMessage());
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}

		formBean.setStrSpecialWardType("0");
		formBean.setStrWardType("0");
		formBean.setStrCategory("0");
		formBean.setStrEffectiveFromDate(formBean.getStrCtDate());
		formBean.setStrEffectiveToDate("");

		return fReturnValue;
	}

	/**
	 * invokes modifyQuery
	 * 
	 * @param chk
	 * @param formBean
	 */
	public void modify(String chk, VOAdvanceMst formBean) {

		try {
			DAOAdvanceMst.modifyQuery(chk, formBean);
		} catch (Exception e) {

			HisException eObj = new HisException("Billing",
					"Advance Master.Modify", "BOAdvanceMst.modify() -->"
							+ e.getMessage());
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}

	}

	public boolean update(String strChk, VOAdvanceMst formBean) {

		int nRes = 1;
		DAOAdvanceMst advDao = new DAOAdvanceMst();
		try {
			nRes = advDao.updateLogic(strChk, formBean);
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"Advance Master.Modify", "BOAdvanceMst.update() -->"
							+ e.getMessage());
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}

		if (nRes != 0) {
			formBean
					.setStrWarning("Effective To Date should be less than Effective From Date of Next Record");
			return false;
		}
		boolean fReturnValue = DAOAdvanceMst.updateQuery(strChk, formBean);

		return fReturnValue;
	}

	/**
	 * invokes various business Logic Methods and returns the insert Status
	 * 
	 * @param formBean
	 * @param strChk
	 * @return true - If Data Inserted Successfully.<br>
	 *         false - If Data Not Inserted Successfully.
	 */
	public boolean insertNew(VOAdvanceMst formBean, String strChk) {

		DAOAdvanceMst advDao = new DAOAdvanceMst();
		int nRes=1;
		try {
				nRes = advDao.effToDtNotNull(formBean, strChk);
			} catch (Exception e) {
				HisException eObj = new HisException("Billing",
						"Advance Master.Modify",
						"BOAdvanceMst.insertNew() @ effToDtNotNull -->"
								+ e.getMessage());
				formBean.setStrErr("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");

				eObj = null;
			}
			/*
			 * if (formBean.getStrEffectiveToDate() == null ||
			 * formBean.getStrEffectiveToDate().equals("")) { nRes =
			 * advDao.effToDtNull(formBean, strChk); } else { nRes =
			 * advDao.effToDtNotNull(formBean, strChk); }
			 */

		if (nRes != 0) {
			// formBean.setStrWarning("Row Not Updated, Select the Correct
			// Date");
			return false;
		}

		boolean fReturnValue = false;
		try {
			fReturnValue = DAOAdvanceMst.insertNewQuery(formBean, strChk);
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"Advance Master.Modify",
					"BOAdvanceMst.insertNew() @ insertNewQuery -->"
							+ e.getMessage());
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}

		
	 
		
		if (fReturnValue)
			advDao.updatePrevToDt(formBean, strChk);

		return fReturnValue;
	}

	/*public void deleteData(VOAdvanceMst formBean, String[] strChk) {
		DAOAdvanceMst.deleteData(formBean,strChk);
	}*/
	public void deleteData(String hosCode,GenericVO formBean, String strChk) {
		DAOAdvanceMst.deleteData(hosCode,formBean,strChk);
	}
	public void correctUpdateData(VOAdvanceMst formBean, String strChk) {
		DAOAdvanceMst.correctUpdateData(formBean,strChk);
	}

}
