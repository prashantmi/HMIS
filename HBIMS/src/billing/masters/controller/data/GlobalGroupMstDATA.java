package billing.masters.controller.data;
/* Global Group Master DATA
 * author: Debashis Sardar
 * Created on : 26-Aug-2011
 */
import billing.masters.bo.GlobalGroupMstBO;
import billing.masters.controller.fb.GlobalGroupMstFB;
import billing.masters.vo.GlobalGroupMstVO;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HelperMethods;

public class GlobalGroupMstDATA{
		
	
	/**
	 * Initializes the BO and VO and transfers control to BO for insertion
	 * 
	 * @param fb
	 * @return int count
	 */
	public int InsertRecord(GlobalGroupMstFB fb) {
		int count=-1;
		
		GlobalGroupMstBO bo = null;
		GlobalGroupMstVO voObj = null;

		try {

			
			bo = new GlobalGroupMstBO();
			voObj = new GlobalGroupMstVO();
			
			voObj = (GlobalGroupMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.GlobalGroupMstVO", fb);
			
			 HelperMethods.populate(fb,voObj);
			

			 count=bo.InsertRecord(voObj);
			 

			
			
		} catch (Exception e) {

			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","GlobalGroupMstDATA->InsertRecord()", msgStr);
			fb.setStrErrorMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} finally {

			bo = null;
			voObj = null;

		}
		return count;
	}
	/**
	 * Initializes the BO and VO and transfers control to BO for Modification
	 * 
	 * @param fb
	 * @param chk
	 */
	
	public void modifyRecord(String chk,GlobalGroupMstFB fb) {
		
		GlobalGroupMstBO bo = null;
		GlobalGroupMstVO voObj = null;

		try {

			
			bo = new GlobalGroupMstBO();
			voObj = new GlobalGroupMstVO();
			voObj = (GlobalGroupMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.GlobalGroupMstVO", fb);
		
			

			bo.modifyRecord(chk,voObj);
			
			fb.setStrgroupName(voObj.getStrgroupName());
			fb.setStrisPackage(voObj.getStrisPackage());
			fb.setStrEffectiveFrom(voObj.getStrEffectiveFrom());
			fb.setStrisValid(voObj.getStrisValid());
			fb.setStrremark(voObj.getStrremark());
			fb.setStrisVisible(voObj.getStrisVisible());
			fb.setStrseatId(voObj.getStrseatId());
			fb.setStrgroupCode(voObj.getStrgroupCode());
			
			
		} catch (Exception e) {

			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","GlobalGroupMstDATA->modifyRecord()", msgStr);
			fb.setStrErrorMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} finally {

			bo = null;
			voObj = null;

		}
	}
	/**
	 * Initializes the BO and VO and transfers control to BO for updation
	 * 
	 * @param chk1
	 * @param fb
	 * @return int count
	 */
	public int updateRecord(String chk1,GlobalGroupMstFB fb) 
	{		
		int count=-1;
		GlobalGroupMstBO bo = null;
		GlobalGroupMstVO voObj = null;

		try 
		{
			bo = new GlobalGroupMstBO();
			voObj = new GlobalGroupMstVO();
			voObj = (GlobalGroupMstVO) TransferObjectFactory.populateData("billing.masters.vo.GlobalGroupMstVO", fb);
			count=bo.updateRecord(chk1,voObj);			
		}		
		catch (Exception e) 
		{
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","GlobalGroupMstDATA->updateRecord()", msgStr);
			fb.setStrErrorMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
			eObj = null;
		} 
		finally 
		{
			bo = null;
			voObj = null;
		}
		return count ;
	}	
}