
/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Consumable Charge Discount Rule Master DATA
 * 
 * Created on: 08-09-2011
 */
package billing.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import billing.BillConfigUtil;
import billing.masters.bo.ConsumableChargeDiscountRuleMstBO;
import billing.masters.controller.fb.ConsumableChargeDiscountRuleMstFB;
import billing.masters.vo.ConsumableChargeDiscountRuleMstVO;


/*
 * Class : ConsumableChargeDiscountRuleMasterDATA
 */


public class ConsumableChargeDiscountRuleMstDATA {
	
	/**
	 * Populates the Patient Category values
	 * 
	 * @param strCategory
	 * @return String strCategoryValues
	 */
	
	public String getStrCategoryValues(String strCategory_p) {

		String hosCode=BillConfigUtil.SUPER_HOSPITAL_CODE;
		String strCategoryValues = "";
		HisUtil hisUtil = new HisUtil("ConsumableCharge Discount Rule Master",
				"ConsumableChargeDiscountRuleMstDATA.getStrCategoryValues");
		try {

			String strQry = billing.qryHandler_billing.getQuery(1,
					"select.ccRuleMst.2").replace("?", hosCode);

			if (strCategory_p == null)
				strCategory_p = "0";

			strCategoryValues = hisUtil.getOptionValue(strQry, strCategory_p,"0^Select Value");
			hisUtil = null;

		} catch (Exception e) {

			new HisException("ConsumableCharge Discount Rule Master",
					"ConsumableCharge Discount RuleMstDATA.getStrCategoryValues", e.getMessage());
		}
		return strCategoryValues;
	}
	
	/**
	 * Populates the Hospital Service values
	 * 
	 * @param strChargeTypeId
	 * @param hoscode
	 * @return String strHospServiceValues
	 */
	public String getHospServiceValues(String strChargeTypeId_p,String hoscode_p) {

		String strHospServiceValues = "";
		HisUtil hisUtil = new HisUtil("ConsumableCharge Discount Rule Master",
				"ConsumableChargeDiscountRuleMstDATA.getHospServiceValues");
		try {

			String strQry = billing.qryHandler_billing.getQuery(1,
					"select.ccRuleMst.3").replace("?", BillConfigUtil.SUPER_HOSPITAL_CODE);

			if (strChargeTypeId_p == null)
				strChargeTypeId_p = "0";

			strHospServiceValues = hisUtil.getOptionValue(strQry, strChargeTypeId_p,
					"0^Select Value");
			hisUtil = null;

		} catch (Exception e) {

			new HisException("ConsumableCharge Discount Rule Master",
					"ConsumableCharge Discount RuleMstDATA.getHospServiceValues", e.getMessage());
		}
		return strHospServiceValues;
	}
	
	/**
	 * Populates the IPD Ward Type Values
	 * 
	 * @param strWardType_p
	 * @param hoscode_p
	 * @return String strWardValues
	 */
	public String getStrWardValues(String strWardType_p,String hoscode_p) {

  	    String strWardValues = "";

	HisUtil hisUtil = new HisUtil("Consumable Charge Master",
			"ConsumableChargeDiscountRuleMstDATA.getStrWardValues");

	try {

		String strQry = billing.qryHandler_billing.getQuery(1,
				"select.ccRuleMst.4").replace("?", hoscode_p);

		if (strWardType_p == null)
			strWardType_p = "0";

		strWardValues = hisUtil.getOptionValue(strQry, strWardType_p,
				"0^Select Value");
		hisUtil = null;

	} catch (Exception e) {

		new HisException("Advance Master", "ConsumableChargeDiscountRuleMstDATA.getStrWardValues",
				e.getMessage());
	}
	
	return strWardValues;
}
/***********************************************************************************************************************/	
	/**
	 * checks the duplicacy of records while insertion
	 * 
	 * @param fb_p
	 * @return boolean 
	 * 'true' if data is Non-Atomic otherwise 'false'
	 */
/*	public boolean chkAddCCRule(ConsumableChargeDiscountRuleMstFB fb_p)
	{
		boolean rval=false;
		ConsumableChargeDiscountRuleMstVO vo=null;
		ConsumableChargeDiscountRuleMstBO bo=null;
		try
		{
			vo=new ConsumableChargeDiscountRuleMstVO();
			bo=new ConsumableChargeDiscountRuleMstBO();
			vo = (ConsumableChargeDiscountRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ConsumableChargeDiscountRuleMstVO", fb_p);
			rval=bo.chkAddCCRule(vo);
		} catch (Exception e) {

			new HisException("ConsumableChargeDiscountRule Master", "ConsumableChargeDiscountRuleMstDATA.chkAddCCRule",
					e.getMessage());
		}
		
		return rval;
		
	}
*/	
	
/***************************************************************************************************************************/
	
	/**
	 * Initializes the BO and VO and transfers control to BO for insertion
	 * 
	 * @param fb_p
	 * @return boolean 
	 * 'true' if data is inserted otherwise 'false'
	 */
	public void insert(ConsumableChargeDiscountRuleMstFB fb_p) {
		 
		// String target;
		boolean rval=false;
		ConsumableChargeDiscountRuleMstVO vo=null;
		ConsumableChargeDiscountRuleMstBO bo=null;

		try {

			
			vo=new ConsumableChargeDiscountRuleMstVO();
			bo=new ConsumableChargeDiscountRuleMstBO();
			vo = (ConsumableChargeDiscountRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ConsumableChargeDiscountRuleMstVO", fb_p);

			rval=bo.insertData(vo);
			TransferObjectFactory.populateData(fb_p, vo);
			if(rval)
				fb_p.setStrMsg(vo.getStrMsg());
			else
				fb_p.setStrWarning(vo.getStrWarning());
			
			
		} catch (Exception e) {

			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","ConsumableChargeDiscountRuleMstDATA->insert()", msgStr);
			fb_p.setStrErrorMsg(eObj.getMessage());
			eObj=null;
		} finally {

			bo = null;
			vo = null;
			
		}
		
	}
	
	/**
	 * Initializes the BO and VO and transfers control to BO for Modification
	 * 
	 * @param  chk
	 * @param fb_p
	 */
	
	public void modifyRecord(String chk,ConsumableChargeDiscountRuleMstFB fb_p) {
		// TODO Auto-generated method stub
		
		ConsumableChargeDiscountRuleMstVO voObj=null;
		ConsumableChargeDiscountRuleMstBO bo=null;


		try {

			
			bo = new ConsumableChargeDiscountRuleMstBO();
			voObj = new ConsumableChargeDiscountRuleMstVO();
			voObj = (ConsumableChargeDiscountRuleMstVO) TransferObjectFactory.populateData("billing.masters.vo.ConsumableChargeDiscountRuleMstVO", fb_p);

			bo.modifyData(voObj,chk);
			
			TransferObjectFactory.populateData(fb_p,voObj);
			
					
		} catch (Exception e) {

			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","ConsumableChargeDiscountRuleMstDATA->modifyRecord()", msgStr);
			fb_p.setStrErrorMsg(eObj.getMessage());
			eObj=null;
		} finally {

			bo = null;
			voObj = null;

		}
		
	}
	
	
	/**
	 * Initializes the BO and VO and transfers control to BO for Updation
	 * 
	 * @param fb_p
	 * @param strChk
	 * @return boolean 
	 * 'true' if data is updated otherwise 'false'
	 */
	
	public boolean Update(ConsumableChargeDiscountRuleMstFB fb_p, String strChk_p) {
		
		ConsumableChargeDiscountRuleMstVO voObj = null;
		ConsumableChargeDiscountRuleMstBO bo=null;
		boolean rval=false;
		try {
			   voObj = new ConsumableChargeDiscountRuleMstVO();
			   bo = new ConsumableChargeDiscountRuleMstBO();
			   voObj = (ConsumableChargeDiscountRuleMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.ConsumableChargeDiscountRuleMstVO", fb_p);
			  rval= bo.Update(voObj,strChk_p);
			  TransferObjectFactory.populateData(fb_p,voObj);
			  if(rval)
				  fb_p.setStrMsg("Data Upadated Succesfully");
	
			  
			} catch (Exception e) {
				e.printStackTrace();
				HisException eObj = new HisException("Billing",
						"ConsumableChargeDiscountRule Master",
						"ConsumableChargeDiscountRuleMstDATA.Update-->"
								+ e.getMessage());
				fb_p.setStrErrorMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");

				eObj = null;
			}
			return rval;
	}

	
	
	

}
