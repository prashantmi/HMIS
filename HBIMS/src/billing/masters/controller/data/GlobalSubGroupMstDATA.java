package billing.masters.controller.data;
/* Global Sub Group Master DATA
 * author: Debashis Sardar
 * Created on : 26-Aug-2011
 */
import javax.sql.rowset.WebRowSet;

import billing.masters.bo.GlobalSubGroupMstBO;
import billing.masters.bo.GlobalTariffMstBO;
import billing.masters.controller.fb.GlobalSubGroupMstFB;
import billing.masters.controller.fb.GlobalTariffMstFB;
import billing.masters.vo.GlobalSubGroupMstVO;
import billing.masters.vo.GlobalTariffMstVO;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HelperMethods;

public class GlobalSubGroupMstDATA{
		
	
	/**
	 * Initializes the BO and VO and transfers control to BO for insertion
	 * 
	 * @param fb
	 * @return int count
	 */
    public void initAdd(GlobalSubGroupMstFB bean) throws Exception
	
	{
		WebRowSet wb = null;
		String groupname = "";
		
		GlobalSubGroupMstVO voObj = null;

	
		try {
			
			voObj = new GlobalSubGroupMstVO();
			voObj = (GlobalSubGroupMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.GlobalSubGroupMstVO", bean);
			wb = GlobalSubGroupMstBO.initAddQuery(voObj);
			while (wb.next()) {
				groupname = wb.getString(1);
			}
			
			bean.setStrgroupName(groupname);
			

		} catch (Exception e) {
		
			throw new Exception("GlobalSubGroupMstDATA.initAdd() -->" + e.getMessage());
		}

	}
	
	public int InsertRecord(GlobalSubGroupMstFB fb) {
		int count=-1;
		
		GlobalSubGroupMstBO bo = null;
		GlobalSubGroupMstVO voObj = null;

		try {

			
			bo = new GlobalSubGroupMstBO();
			voObj = new GlobalSubGroupMstVO();
			
			voObj = (GlobalSubGroupMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.GlobalSubGroupMstVO", fb);
			
			 HelperMethods.populate(fb,voObj);
			

			 count=bo.InsertRecord(voObj);
			 

			
			
		} catch (Exception e) {

			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","GlobalSubGroupMstDATA->InsertRecord()", msgStr);
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
	
	public void modifyRecord(String chk,GlobalSubGroupMstFB fb) {
		
		GlobalSubGroupMstBO bo = null;
		GlobalSubGroupMstVO voObj = null;

		try {

			
			bo = new GlobalSubGroupMstBO();
			voObj = new GlobalSubGroupMstVO();
			voObj = (GlobalSubGroupMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.GlobalSubGroupMstVO", fb);
		
			

			bo.modifyRecord(chk,voObj);
			
			fb.setStrsubgroupName(voObj.getStrsubgroupName());
			fb.setStrisPackage(voObj.getStrisPackage());
			fb.setStrEffectiveFrom(voObj.getStrEffectiveFrom());
			fb.setStrisValid(voObj.getStrisValid());
			fb.setStrremark(voObj.getStrremark());
			fb.setStrisVisible(voObj.getStrisVisible());
			fb.setStrseatId(voObj.getStrseatId());
			fb.setStrsubgroupCode(voObj.getStrsubgroupCode());
			fb.setStrgrpId(voObj.getStrgrpId());
			fb.setStrGroupId(voObj.getStrGroupId());
			fb.setStrgroupName(voObj.getStrgroupName());
			
			
		} catch (Exception e) {

			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","GlobalSubGroupMstDATA->modifyRecord()", msgStr);
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
	public int updateRecord(String chk1,GlobalSubGroupMstFB fb) 
	{		
		int count=-1;
		GlobalSubGroupMstBO bo = null;
		GlobalSubGroupMstVO voObj = null;

		try 
		{
			bo = new GlobalSubGroupMstBO();
			voObj = new GlobalSubGroupMstVO();
			voObj = (GlobalSubGroupMstVO) TransferObjectFactory.populateData("billing.masters.vo.GlobalSubGroupMstVO", fb);
			count=bo.updateRecord(chk1,voObj);			
		}		
		catch (Exception e) 
		{
			String msgStr = e.getMessage();
			HisException eObj = new HisException("Billing","GlobalSubGroupMstDATA->updateRecord()", msgStr);
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