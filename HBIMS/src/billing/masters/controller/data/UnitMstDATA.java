
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
import billing.masters.bo.UnitMstBO;
import billing.masters.controller.fb.UnitMstFB;
import billing.masters.vo.UnitMstVO;

public class UnitMstDATA {
	
	
	/**
	 * checks the duplicacy of records while insertion
	 * 
	 * @param fb
	 * @return boolean 
	 * 'true' if data is Non-Atomic otherwise 'false'
	 */
	public void  initialAdd(UnitMstFB fb)
	{
		UnitMstVO vo=null;
		UnitMstBO bo=null;
		try
		{
			vo=new UnitMstVO();
			bo=new UnitMstBO();
			vo = (UnitMstVO) TransferObjectFactory.populateData("billing.masters.vo.UnitMstVO", fb);
			bo.initialAdd(vo);
			TransferObjectFactory.populateData(fb,vo);
		} catch (Exception e) {

			new HisException("Advance Master", "UnitMstDATA.initialAdd",
					e.getMessage());
		}
		
		
		
	}
	
	/**
	 * Initializes the BO and VO and transfers control to BO for insertion
	 * 
	 * @param fb
	 * @return boolean 
	 * 'true' if data is inserted otherwise 'false'
	 */
	public void insert(UnitMstFB fb) {
		 
		// String target;
		boolean rval=false;
		UnitMstVO vo=null;
		UnitMstBO bo=null;

		try {

			
			vo=new UnitMstVO();
			bo=new UnitMstBO();
			vo = (UnitMstVO) TransferObjectFactory.populateData("billing.masters.vo.UnitMstVO", fb);

			rval=bo.insert(vo);
			TransferObjectFactory.populateData(fb, vo);
			if(rval)
				fb.setStrMsg(vo.getStrMsg());
			else
				fb.setStrWarning(vo.getStrWarning());
			
			
		} catch (Exception e) {

			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","UnitMstDATA->insert()", msgStr);
			fb.setStrErr(eObj.getMessage());
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
	 * @param fb
	 */
	
	public void modify(String chk,
			UnitMstFB fb) {
		
		UnitMstVO voObj=null;
		UnitMstBO bo=null;


		try {

			
			bo = new UnitMstBO();
			voObj = new UnitMstVO();
			voObj = (UnitMstVO) TransferObjectFactory.populateData("billing.masters.vo.UnitMstVO", fb);

			bo.modify(chk,voObj);
			
			TransferObjectFactory.populateData(fb,voObj);
			
					
		} catch (Exception e) {

			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","UnitMstDATA->modify()", msgStr);
			fb.setStrErr(eObj.getMessage());
			eObj=null;
		} finally {

			bo = null;
			voObj = null;

		}
		
	}
	
	/**
	 * Function to Modify the Unit Name
	 * 
	 * @param fb
	 * 'true' if data is Non-Atomic otherwise 'false'
	 */
	public void modName(UnitMstFB fb) {
		
		UnitMstVO voObj = null;
		UnitMstBO bo=null;
		try {
			   voObj = new UnitMstVO();
			   bo = new UnitMstBO();
			   voObj = (UnitMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.UnitMstVO", fb);
			   bo.modName(voObj);
			  TransferObjectFactory.populateData(fb,voObj);
			} catch (Exception e) {
				e.printStackTrace();
				HisException eObj = new HisException("Billing",
						"Unit Master",
						"UnitMstDATA.modName -->"
								+ e.getMessage());
				fb.setStrErr("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");

				eObj = null;
			}
	}
	
	/**
	 * Initializes the BO and VO and transfers control to BO for Updation
	 * 
	 * @param fb
	 * @param strChk
	 * @return boolean 
	 * 'true' if data is updated otherwise 'false'
	 */
	
	public boolean update(String strChk,UnitMstFB fb) {
		
		UnitMstVO voObj = null;
		UnitMstBO bo=null;
		boolean rval=false;
		try {
			   voObj = new UnitMstVO();
			   bo = new UnitMstBO();
			   voObj = (UnitMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.UnitMstVO", fb);
			   rval= bo.update(strChk,voObj);
			  TransferObjectFactory.populateData(fb,voObj);
			  if(rval)
				  fb.setStrMsg("Data Upadated Succesfully");
	
			  
			} catch (Exception e) {
				e.printStackTrace();
				HisException eObj = new HisException("Billing",
						"Unit Master",
						"UnitMstDATA.update-->"
								+ e.getMessage());
				fb.setStrErr("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");

				eObj = null;
			}
			return rval;
	}

	
	
	

}
