/* Counter Master DATA
 * Created By: Pawan Kumar B N
 * Created On: 01-Sep-2011
 */
package billing.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import billing.masters.bo.CounterMstBO;
import billing.masters.controller.fb.CounterMstFB;
import billing.masters.vo.CounterMstVO;

public class CounterMstDATA{
		
	@SuppressWarnings("finally")
	/**
	 * Insert data.
	 * 
	 * @param bean the bean
	 * 
	 * @return true, if successful
	 */
	
	public void insertData(CounterMstFB fb) 
	{
		boolean rval=false;
		CounterMstBO bo = null;
		CounterMstVO voObj = null;

		try 
		{
			bo = new CounterMstBO();
			voObj = new CounterMstVO();
			voObj = (CounterMstVO) TransferObjectFactory.populateData("billing.masters.vo.CounterMstVO", fb);

			rval=bo.insertData(voObj);
			if(rval)
				fb.setStrMsg(voObj.getStrMsg());
			else
				fb.setWarningMsg(voObj.getWarningMsg());
		} 
		catch (Exception e) 
		{
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","CounterMstDATA->checkCounterStatus()", msgStr);
			fb.setErrmsg(eObj.getMessage());
			eObj=null;
		} 
		finally 
		{

			bo = null;
			voObj = null;
			
		}
		
	}
	
	
	

	/**
	 * Edits the data.
	 * 
	 * @param bean the bean
	 * @param chk the chk
	 * 
	 * @return true, if successful
	 */
public boolean editData(String chk,CounterMstFB fb) {
	
		boolean rval=false;
		
		CounterMstBO bo = null;
		CounterMstVO voObj = null;
		try 
		{
			bo = new CounterMstBO();
			voObj = new CounterMstVO();
			voObj = (CounterMstVO) TransferObjectFactory.populateData("billing.masters.vo.CounterMstVO", fb);
			
			rval=bo.editData(voObj,chk);
			TransferObjectFactory.populateData(fb, voObj);
		} 
		catch (Exception e) 
		{

			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","CounterMstDATA->error in update", msgStr);
			fb.setErrmsg(eObj.getMessage());
			eObj=null;
		} 
		finally 
		{
			bo = null;
			voObj= null;
		}
		return rval;
	}

/**
 * Checks for the existence of the data and insert's the data if data not exists, otherwise display's 
 * warning 
 * 
 * @param bean the bean
 * 
 * @return true, if successful
 */
public boolean chkAddCntr(CounterMstFB fb) {
	boolean retValue = false;
	String strmsgText = null;
	CounterMstBO bo = null;
	CounterMstVO voObj = null;
	try {
		bo = new CounterMstBO();
		voObj = new CounterMstVO();
		voObj = (CounterMstVO) TransferObjectFactory.populateData("billing.masters.vo.CounterMstVO", fb);
		
		retValue = bo.chkAddCntr(voObj);
	
		TransferObjectFactory.populateData(fb, voObj);
	} catch (Exception e) {

		strmsgText = "billing.masters.CounterMstDATA.chkAddCntr() --> "
				+ e.getMessage();
		HisException eObj = new HisException("Billing",
				"CounterMstDATA-->chkAddCntr()", strmsgText);
		fb.setErrmsg("Application Error [ERROR ID : " + eObj.getErrorID()
				+ "],Contact System Administrator! ");

		eObj = null;

	}

	return retValue;
}

/**
 * Checks for the existence of the data and modifies the data if data not exists, otherwise display's 
 * warning 
 * 
 * @param bean the bean
 * @param chk the chk
 * 
 * @return true, if successful
 */

public boolean chkModifyCntr(CounterMstFB bean, String chk) {
	boolean retValue = false;
	String strmsgText = null;
	CounterMstBO bo = null;
	CounterMstVO voObj = null;
	try {
		bo = new CounterMstBO();
		voObj = new CounterMstVO();
		voObj = (CounterMstVO) TransferObjectFactory.populateData("billing.masters.vo.CounterMstVO", bean);
		retValue = bo.chkModifyCntr(voObj, chk);

		TransferObjectFactory.populateData(bean, voObj);
	} catch (Exception e) {

		strmsgText = "billing.masters.data.CounterMstDATA.chkModifyCntr() --> "
				+ e.getMessage();
		HisException eObj = new HisException("Billing",
				"CounterMstDATA-->chkModifyCntr()", strmsgText);
		bean.setErrmsg("Application Error [ERROR ID : " + eObj.getErrorID()
				+ "],Contact System Administrator! ");

		eObj = null;

	}

	return retValue;
}
/**
 * Gets the data to modify.
 * 
 * @param vo the vo
 * @param chk the chk
 * 
 * @return the data to modify
 */
public boolean getDataToModify(CounterMstFB fb, String chk) {
	boolean fVal = false;
	String strmsgText = null;
	CounterMstBO bo = null;
	CounterMstVO voObj = null;
	try {
		bo = new CounterMstBO();
		voObj = new CounterMstVO();
		voObj = (CounterMstVO) TransferObjectFactory.populateData("billing.masters.vo.CounterMstVO", fb);
		fVal = bo.getDataToModify(voObj, chk);
		TransferObjectFactory.populateData(fb, voObj);
	} catch (Exception e) {

		strmsgText = "billing.masters.data.CounterMstDATA.getDataToModify() --> "
				+ e.getMessage();
		HisException eObj = new HisException("Billing",
				"CounterMstDATA-->getDataToModify()", strmsgText);
		fb.setErrmsg("Application Error [ERROR ID : " + eObj.getErrorID()
				+ "],Contact System Administrator! ");

		eObj = null;

	}
	return fVal;
}


}
	

