




/* Created By: Pawan Kumar B N
 * Counter Master BO
 * Created On: 01-Sep-2011
 */
package billing.masters.bo;

import hisglobal.exceptions.HisException;
import billing.masters.dao.CounterMstDAO;
import billing.masters.vo.CounterMstVO;

// TODO: Auto-generated Javadoc
/**
 * The Class CounterMstBO.
 */
public class CounterMstBO {

	
	/**
	 * Insert data.
	 * 
	 * @param vo 
	 * 
	 * @return true, if successful
	 */
	public boolean insertData(CounterMstVO vo) {
		boolean retValue = false;
		String strmsgText = null;
		try {
			retValue = CounterMstDAO.addData(vo);
		} catch (Exception e) {

			strmsgText = "billing.masters.CounterMstBO.insertData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"CounterMaster->insertData()", strmsgText);
			vo.setErrmsg("Application Error [ERROR ID : " + eObj.getErrorID()
					+ "],Contact System Administrator! ");

			eObj = null;

		}

		return retValue;
	}

	
	/**
	 * Edits the data.
	 * 
	 * @param vo 
	 * @param chk 
	 * 
	 * @return true, if successful
	 */
	public boolean editData(CounterMstVO vo, String chk) {
		boolean retValue = false;
		String strmsgText = null;
		try {
			retValue = CounterMstDAO.modifyData(vo, chk);
			
		} catch (Exception e) {

			strmsgText = "billing.masters.CounterMstBO.editData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"CounterMstBO-->editData()", strmsgText);
			vo.setErrmsg("Application Error [ERROR ID : " + eObj.getErrorID()
					+ "],Contact System Administrator! ");

			eObj = null;

		}

		return retValue;
	}


	/**
	 * Checks for the existence of the data and insert's the data if data not exists, otherwise display's 
	 * warning 
	 * 
	 * @param vo 
	 * 
	 * @return true, if successful
	 */
	public boolean chkAddCntr(CounterMstVO vo) {
		boolean retValue = false;
		String strmsgText = null;
		try {
			
			retValue = CounterMstDAO.chkAddCounter(vo);
		
			if (retValue) {
				retValue = insertData(vo);
		
				if (retValue)
					vo.setStrMsg("Data Added Successfully");
				else {
					vo.setErrmsg("Error in add page.");
				}
			} else
				vo.setWarningMsg("Counter Name or IP Address already exists.");
		} catch (Exception e) {

			strmsgText = "billing.masters.CounterMstBO.chkAddCntr() --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"CounterMstBO-->chkAddCntr()", strmsgText);
			vo.setErrmsg("Application Error [ERROR ID : " + eObj.getErrorID()
					+ "],Contact System Administrator! ");

			eObj = null;

		}

		return retValue;
	}

	
	/**
	 * Checks for the existence of the data and modifies the data if data not exists, otherwise display's 
	 * warning 
	 * 
	 * @param vo 
	 * @param chk 
	 * 
	 * @return true, if successful
	 */
	public boolean chkModifyCntr(CounterMstVO vo, String chk) {
		
		boolean retValue = false;
		String strmsgText = null;
		try {

			retValue = CounterMstDAO.chkModifyCounter(vo, chk);

			if (retValue) {
				retValue = editData(vo, chk);
				if (retValue == false)
					vo.setErrmsg("Error in modify page.");
			} else {
			}
		} catch (Exception e) {

			strmsgText = "billing.masters.CounterMstBO.chkModifyCntr() --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"CounterMstBO-->chkModifyCntr()", strmsgText);
			vo.setErrmsg("Application Error [ERROR ID : " + eObj.getErrorID()
					+ "],Contact System Administrator! ");

			eObj = null;

		}

		return retValue;
	}

	
	/**
	 * Gets the data to modify.
	 * 
	 * @param vo 
	 * @param chk 
	 * 
	 * @return the data to modify
	 */
	public boolean getDataToModify(CounterMstVO vo, String chk) {
		boolean fVal = false;
		String strmsgText = null;
		try {
			fVal = CounterMstDAO.getData(vo, chk);
		} catch (Exception e) {

			strmsgText = "billing.masters.CounterMstBO.getDataToModify() --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"CounterMstBO-->getDataToModify()", strmsgText);
			vo.setErrmsg("Application Error [ERROR ID : " + eObj.getErrorID()
					+ "],Contact System Administrator! ");

			eObj = null;

		}
		return fVal;
	}


	
	
}
