/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Consumable Charge Discount Rule Master BO
 * 
 * Created on: 08-09-2011
 */
                                                                                                       

package billing.masters.bo;

import hisglobal.exceptions.HisException;
import billing.masters.dao.ConsumableChargeDiscountRuleMstDAO;
import billing.masters.vo.ConsumableChargeDiscountRuleMstVO;

/*
 * Class : ConsumableChargeDiscountRuleMasterBO
 */

public class ConsumableChargeDiscountRuleMstBO {
	
	
	
	/**
	 * function to insert data. This function retrieve result from DAO class and
	 * forwards to controller.
	 * 
	 * @param vo_p
	 * @return fval (boolean)
	 */
	public boolean insertData(ConsumableChargeDiscountRuleMstVO vo_p) {
		boolean fval = false;
		boolean bval=false;
		try {
			bval=chkAddCCRule(vo_p);
			if(bval){
				fval = ConsumableChargeDiscountRuleMstDAO.insertRecord(vo_p);
				if (fval) {
					vo_p.setStrMsg("Record saved successfully");
					
					} 
					
					else{
					vo_p.setStrErrorMsg("Record not saved");
					}
			}
			else
				vo_p.setStrWarning("Discount Value already exists !");
			
			
		} catch (Exception e) {
			
			
			HisException eObj = new HisException("Billing",
					"ConsumableChargeDiscountRuleMstBO.insertData -->", e.getMessage());

			vo_p.setStrErrorMsg("Error [ERROR ID : " + eObj.getErrorID()
					+ "], Contact System Administrator !");
			
			if (eObj != null)
				eObj = null;
		
		}
		return fval;
	}

	
	
	
	/**
	 * function to view the modification data. 
	 * 
	 * @param vo_p, chk_p
	 * @return boolean fval
	 */
	public boolean modifyData(ConsumableChargeDiscountRuleMstVO vo_p,String chk_p) {
		boolean fval = false;
		try {
			
			fval = ConsumableChargeDiscountRuleMstDAO.ModifyRecord(vo_p,chk_p);
			
			
		}
		catch (Exception e) {
			vo_p.setStrErrorMsg("Record Not Updated");
			vo_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("Billing","ConsumableChargeDiscountRuleMstBO.modifyData -->", vo_p.getStrErrorMsg());
			   vo_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fval;
	}
	
	/**
	 * function to check the duplicacy of insertion data. 
	 * 
	 * @param vo_p
	 * @return boolean rval
	 */

	public boolean chkAddCCRule(ConsumableChargeDiscountRuleMstVO vo_p) {
		// TODO Auto-generated method stub
		boolean bval=false;
		ConsumableChargeDiscountRuleMstDAO dao=null;
		try
		{
			dao=new ConsumableChargeDiscountRuleMstDAO();
			bval=dao.chkAddCCRule(vo_p);
		} catch (Exception e) {

			new HisException("ConsumableChargeDiscountRule Master", "ConsumableChargeDiscountRuleMstDATA.chkAddRule()",
					e.getMessage());
		}
		return bval;
	}
	

	
	/**
	 * function to update the data. 
	 * 
	 * @param voObj_p,srtChk_p
	 * @return boolean rval
	 */

	public boolean Update(ConsumableChargeDiscountRuleMstVO voObj_p, String strChk_p) {
		boolean rval=false;
		ConsumableChargeDiscountRuleMstDAO dao=null;
		try
		{
			dao=new ConsumableChargeDiscountRuleMstDAO();
			rval=dao.Update(voObj_p,strChk_p);
		} catch (Exception e) {

			new HisException("ConsumableChargeDiscountRule Master", "ConsumableChargeDiscountRuleMstDATA.Update()",
					e.getMessage());
		}
		return rval;
	}

}
