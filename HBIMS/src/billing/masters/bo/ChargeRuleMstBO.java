/**
 * 
 */
package billing.masters.bo;

import hisglobal.exceptions.HisException;
import billing.masters.dao.*;
import billing.masters.vo.*;
import billing.masters.controller.fb.*;
/**
 * Developer : DEEPIKA GABA
 * Process Name : Charge Rule Master
 * Date : 2 SEP 2011
 * Version : 1.0
 * Modify Date : FB FROM VO
 *
 */
public class ChargeRuleMstBO {
	
	/**
	  * 
	  * @param vo - FormBean Object
	  */
	public void getRuleDetails(ChargeRuleMstVO vo)  {
		try{
		   ChargeRuleMstDAO.getRuleDetails(vo);
		   
		   ChargeRuleMstDAO.getNewChargeType(vo);
		   
		   ChargeRuleMstDAO.getOldChargeType(vo);
		   
		}catch(Exception e){
			   vo.setStrErrMsg( "BILLING-->" +
				   		"Charge Rule Master"+ "ChargeRuleMstBO-->getRuleDetails()"+ e.getMessage());
			 
	    }		
	}

	/**
	  * Get Ward Type Combo Values
	  * 
	  * @param vo - FormBean Object
	  */
	public void getNewWardType(ChargeRuleMstVO vo)  {
		try{
		   ChargeRuleMstDAO.getNewWardTypeValues(vo);
		   
		}catch(Exception e){
			   vo.setStrErrMsg("BILLING-->" +
				   		"Charge Rule Master"+ "ChargeRuleMstBO-->getNewWardType()"+ e.getMessage());
			 
	    }		
	}
	
	/**
	  * Get Ward Type Combo Values
	  * 
	  * @param vo - FormBean Object
	  */
	public void getOldWardType(ChargeRuleMstVO vo)  {
		try{
		   ChargeRuleMstDAO.getOldWardTypeValues(vo);
		   
		}catch(Exception e){
			  vo.setStrErrMsg("BILLING-->" +
				   		"Charge Rule Master"+ "ChargeRuleMstBO-->getOldWardType()"+ e.getMessage());
			   
	    }		
	}
	
	/**
	  * Get New Patient Category Combo Values
	  * 
	  * @param vo - FormBean Object
	  */
	public void getNewPatCat(ChargeRuleMstVO vo)  {
		try{
		   ChargeRuleMstDAO.getNewPatCatValues(vo);
		   
		}catch(Exception e){
			
			  vo.setStrErrMsg("BILLING-->" +
				   		"Charge Rule Master"+ "ChargeRuleMstBO-->getNewPatCat()"+ e.getMessage());
			   
			 
	    }		
	}
	
	/**
	  * Get New Patient Category Combo Values
	  * 
	  * @param vo - FormBean Object
	  */
	public void getOldPatCat(ChargeRuleMstVO vo)  {
		try{
		   ChargeRuleMstDAO.getOldPatCatValues(vo);
		   
		}catch(Exception e){
			
			  vo.setStrErrMsg("BILLING-->" +
				   		"Charge Rule Master"+ "ChargeRuleMstBO-->getOldPatCat()"+ e.getMessage());
			 
			 
	    }		
	}
	
	/**
	 * function to insert data. This function retrieve result from DAO class and
	 * forwards to controller.
	 * 
	 * @param vo
	 * @return
	 */
	public boolean insertData(ChargeRuleMstVO vo) {
		boolean fval = false;
		try {
			
			fval = ChargeRuleMstDAO.insertRecord(vo);
			
			if (fval) {
				vo.setStrNormalMsg("Record saved successfully");
				
				vo.setStrNewChargeTypeId("0");
				
			} else {
				if(vo.getStrMsgType().equals("1"))
				{
					vo.setStrWarningMsg("Record already Exist"); 
				}
				else{
				vo.setStrErrMsg("Record not saved");
				}
			}
		} catch (Exception e) {
			
			if(vo.getStrMsgType().equals("1"))
			{
				vo.setStrWarningMsg("Record already Exist"); 
			}else{
			HisException eObj = new HisException("Billing",
					"ChargeRuleMstBO.insertData -->", e.getMessage());

			vo.setStrErrMsg("Error [ERROR ID : " + eObj.getErrorID()
					+ "], Contact System Administrator !");
			
			if (eObj != null)
				eObj = null;
			}
		}
		return fval;
	}

	
	/**
	 * function to correct data. 
	 * 
	 * @param vo
	 * @return
	 */
	public boolean correctData(ChargeRuleMstVO vo) {
		boolean fval = false;
		try {
			
			fval = ChargeRuleMstDAO.CorrectRecord(vo);
			
			if(fval==true)
				 vo.setStrNormalMsg("Data Updated");
				 else
					 vo.setStrErrMsg("Data is not saved");
		}
		catch (Exception e) {
			vo.setStrErrMsg("Record Not Updated");
			vo.setStrErrMsg(e.getMessage());
			   HisException eObj = new HisException("Billing","ChargeRuleMstBO.correctData -->", vo.getStrErrMsg());
			   vo.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fval;
	}
	
	/**
	 * function to correct data. 
	 * 
	 * @param vo
	 * @return
	 */
	public boolean modifyData(ChargeRuleMstVO vo) {
		boolean fval = false;
		try {
			
			fval = ChargeRuleMstDAO.ModifyRecord(vo);
			
			if(fval==true)
				 vo.setStrNormalMsg("Data Updated");
				 else
					 vo.setStrErrMsg("Data is not saved");
		}
		catch (Exception e) {
			vo.setStrErrMsg("Record Not Updated");
			vo.setStrErrMsg(e.getMessage());
			   HisException eObj = new HisException("Billing","ChargeRuleMstBO.modifyData -->", vo.getStrErrMsg());
			   vo.setStrErrMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fval;
	}
	
	/**
	 * function to correct data. 
	 * 
	 * @param vo
	 * @return
	 */
	public boolean deleteData(ChargeRuleMstVO vo) {
		boolean fval = false;
		try {
			
			fval = ChargeRuleMstDAO.DeleteRecord(vo);
			
		}
		catch (Exception e) {
			vo.setStrErrMsg("Record Not Updated");
			vo.setStrErrMsg(e.getMessage());
			   HisException eObj = new HisException("Billing","ChargeRuleMstBO.deleteData -->", vo.getStrErrMsg());
			   vo.setStrErrMsg("Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fval;
	}
}
