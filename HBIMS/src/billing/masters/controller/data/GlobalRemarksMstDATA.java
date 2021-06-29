/* Global Remarks Master DATA
 * author: Pawan Kumar B N
 * Created on : 26-Aug-2011
 */
package billing.masters.controller.data;

import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;

import billing.masters.bo.GlobalRemarksMstBO;
import billing.masters.controller.fb.GlobalRemarksMstFB;
import billing.masters.vo.GlobalRemarksMstVO;

public class GlobalRemarksMstDATA{
		
	@SuppressWarnings("finally")
	/**
	 * Initializes the BO and VO and transfers control to BO for insertion
	 * 
	 * @param fb
	 * @param request
	 * @return boolean 
	 * 'true' if data is inserted otherwise 'false'
	 */
	
	public boolean insertRecord(GlobalRemarksMstFB fb) {
		 boolean retval,rval=false;
		GlobalRemarksMstBO bo = null;
		GlobalRemarksMstVO voObj = null;

		try 
		{
			bo = new GlobalRemarksMstBO();
			voObj = new GlobalRemarksMstVO();
			voObj = (GlobalRemarksMstVO) TransferObjectFactory.populateData("billing.masters.vo.GlobalRemarksMstVO", fb);

			retval=bo.insert(voObj);
			TransferObjectFactory.populateData(fb,voObj);
			if(retval)
				rval=true;			
		} 
		catch (Exception e) 
		{
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","CashCollectionTransDATA->checkCounterStatus()", msgStr);
			fb.setStrErr(eObj.getMessage());
			eObj=null;
		} 
		finally 
		{
			bo = null;
			voObj = null;			
		}
		return rval;
	}
	
	/**
	 * Initializes the BO and VO and transfers control to BO for Modification
	 * 
	 * @param request
	 * @param chk
	 * @param fb
	 */
	
	public void modifyRecord(String chk,GlobalRemarksMstFB fb) {
		
		GlobalRemarksMstBO bo = null;
		GlobalRemarksMstVO voObj = null;

		try 
		{
			bo = new GlobalRemarksMstBO();
			voObj = new GlobalRemarksMstVO();
			voObj = (GlobalRemarksMstVO) TransferObjectFactory.populateData("billing.masters.vo.GlobalRemarksMstVO", fb);

			bo.modify(chk,voObj);
			
			fb.setStrRemarksDesc(voObj.getStrRemarksDesc());
			fb.setStrEffectiveFrom(voObj.getStrEffectiveFrom());
			fb.setStrRemarksType(voObj.getStrRemarksType());
			fb.setStrRemarksFor(voObj.getStrRemarksFor());
			fb.setStrSeatId(voObj.getStrSeatId());
			fb.setStrValid(voObj.getStrValid());
		} 
		catch (Exception e) 
		{
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","CashCollectionTransDATA->checkCounterStatus()", msgStr);
			fb.setStrErr(eObj.getMessage());
			eObj=null;
		} 
		finally 
		{
			bo = null;
			voObj = null;
		}
	}

	/**
	 * Initializes the BO and VO and transfers control to BO for updation
	 * 
	 * @param request
	 * @param fb
	 * @return boolean 
	 * 'true' if data is updated otherwise 'false'
	 */
public boolean updateRecord(String strChk,GlobalRemarksMstFB fb) {
		
		GlobalRemarksMstBO bo = null;
		GlobalRemarksMstVO voObj = null;
		boolean returnvalue=false;
		try 
		{
			bo = new GlobalRemarksMstBO();
			voObj = new GlobalRemarksMstVO();
			voObj = (GlobalRemarksMstVO) TransferObjectFactory.populateData("billing.masters.vo.GlobalRemarksMstVO", fb);
			
			returnvalue=bo.update(strChk,voObj);
			TransferObjectFactory.populateData(fb,voObj);			
		} 
		catch (Exception e) 
		{
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","CashCollectionTransDATA->checkCounterStatus()", msgStr);
			fb.setStrErr(eObj.getMessage());
			eObj=null;
		} 
		finally 
		{
			bo = null;
			voObj = null;
		}
		return returnvalue;
	}

	
}
