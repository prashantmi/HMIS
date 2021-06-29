package billing.masters.bo;

import billing.masters.controller.fb.VOPatientCategoryAndPaymentModeMst;
import billing.masters.dao.DAOPatientCategoryAndPaymentModeMst;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericVO;

public class BOPatientCategoryAndPaymentModeMst {

	/**
	 * used to invoke insertQuery and returns whether Data Inserted.
	 * 
	 * @param formBean
	 * @return true - If Data Inserted Successfully.<br>
	 *         false - If Data Not Inserted Successfully.
	 */
	
	
	public boolean insert(VOPatientCategoryAndPaymentModeMst formBean) {

		boolean fReturnValue = false;

		try {

			fReturnValue =DAOPatientCategoryAndPaymentModeMst.checkForInsert(formBean);
			

			if (fReturnValue) {

				fReturnValue = DAOPatientCategoryAndPaymentModeMst.insertQuery(formBean);

				if (fReturnValue) {

					formBean.setStrMsg("Record Saved Successfully!");
				}

			} else {

				formBean.setStrWarning("Data Deleteion Exception");
			}

		} catch (Exception e) {

			HisException eObj = new HisException("Billing",
					"AdvanceMaster->insert()", "BOPatientCategoryAndPaymentModeMst.insert() -->"
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
	public void modify(String chk, VOPatientCategoryAndPaymentModeMst formBean) {

		try {
			DAOPatientCategoryAndPaymentModeMst.modifyQuery(chk, formBean);
		} catch (Exception e) {

			HisException eObj = new HisException("Billing",
					"Advance Master.Modify", "BOPatientCategoryAndPaymentModeMst.modify() -->"
							+ e.getMessage());
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}

	}

	public boolean update(String strChk, VOPatientCategoryAndPaymentModeMst formBean) {

		int nRes = 1;
		DAOPatientCategoryAndPaymentModeMst advDao = new DAOPatientCategoryAndPaymentModeMst();
		try {
			nRes = advDao.updateLogic(strChk, formBean);
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"Advance Master.Modify", "BOPatientCategoryAndPaymentModeMst.update() -->"
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
		boolean fReturnValue = DAOPatientCategoryAndPaymentModeMst.updateQuery(strChk, formBean);

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
	public boolean insertNew(VOPatientCategoryAndPaymentModeMst formBean, String strChk) {

		DAOPatientCategoryAndPaymentModeMst advDao = new DAOPatientCategoryAndPaymentModeMst();
		int nRes=1;
		try {
				nRes = advDao.effToDtNotNull(formBean, strChk);
			} catch (Exception e) {
				HisException eObj = new HisException("Billing",
						"Advance Master.Modify",
						"BOPatientCategoryAndPaymentModeMst.insertNew() @ effToDtNotNull -->"
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
			fReturnValue = DAOPatientCategoryAndPaymentModeMst.insertNewQuery(formBean, strChk);
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"Advance Master.Modify",
					"BOPatientCategoryAndPaymentModeMst.insertNew() @ insertNewQuery -->"
							+ e.getMessage());
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}

		
	 
		
		if (fReturnValue)
			advDao.updatePrevToDt(formBean, strChk);

		return fReturnValue;
	}

	/*public void deleteData(VOAdvanceBSMst formBean, String[] strChk) {
		DAOAdvanceBSMst.deleteData(formBean,strChk);
	}*/
	public void deleteData(String hosCode,GenericVO formBean, String strChk) {
		DAOPatientCategoryAndPaymentModeMst.deleteData(hosCode,formBean,strChk);
	}
	public void correctUpdateData(VOPatientCategoryAndPaymentModeMst formBean, String strChk) {
		DAOPatientCategoryAndPaymentModeMst.correctUpdateData(formBean,strChk);
	}
	
	public void getPaymentModeMapping(VOPatientCategoryAndPaymentModeMst vo) {

		DAOPatientCategoryAndPaymentModeMst advDao=null;
		try {
			 advDao = new DAOPatientCategoryAndPaymentModeMst();

			 advDao.getPaymentModeMapping(vo);
		} catch (Exception e) {
			e.printStackTrace();
			HisException eObj = new HisException("Billing",
					"Advance Master.Modify",
					"BOPatientCategoryAndPaymentModeMst.insertNew() @ insertNewQuery -->"
							+ e.getMessage());
			vo.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}

		
	 
	
	}


}
