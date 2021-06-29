package billing.masters.controller.data;
/* Global Tariff Master DATA
 * author: Debashis Sardar
 * Created on : 14-Sep-2011
 */
import javax.sql.rowset.WebRowSet;
import hisglobal.TransferObjectFactory;
import hisglobal.exceptions.HisException;
import billing.masters.bo.GlobalTariffMstBO;
import billing.masters.controller.fb.GlobalTariffMstFB;
import billing.masters.vo.GlobalTariffMstVO;

public class GlobalTariffMstDATA {

	
	/**
	 * Initializes the BO and VO and transfers control to BO for insertion
	 * 
	 * @param fb
	 * @return boolean
	 */
	public boolean addData(GlobalTariffMstFB bean) {
		boolean retValue = false;
		
		GlobalTariffMstVO voObj = null;
		try {
			
			voObj = new GlobalTariffMstVO();
			
			voObj = (GlobalTariffMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.GlobalTariffMstVO", bean);
			retValue = GlobalTariffMstBO.insertData(voObj);
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"GlobalTariffMstDATA->addData()", e.getMessage());
			
			
			bean.setErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}

		return retValue;
	}
	/**
	 * for getting option value of combo on add page
	 * @param fb
	 * @return 
	 * throws Exception
	 */
	
	public void initAdd(GlobalTariffMstFB bean) throws Exception
	
	{
		WebRowSet wb = null;
		String groupname = "";
		
		GlobalTariffMstVO voObj = null;

	
		try {
			
			voObj = new GlobalTariffMstVO();
			voObj = (GlobalTariffMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.GlobalTariffMstVO", bean);
			wb = GlobalTariffMstBO.initAddQuery(voObj);
			while (wb.next()) {
				groupname = wb.getString(1);
			}
			
			bean.setStrgroupName(groupname);
			

		} catch (Exception e) {
		
			throw new Exception("GlobalTariffMstDATA.initAdd() -->" + e.getMessage());
		}

	}
	
	/**
	 * to check uniqueness of data while adding.
	 * @param fb
	 * @return boolean
	 */
	public boolean chkAddTariffName(GlobalTariffMstFB bean) {
		boolean retValue = false;
		GlobalTariffMstVO voObj = null;
		try {
            voObj = new GlobalTariffMstVO();
			
			voObj = (GlobalTariffMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.GlobalTariffMstVO", bean);
			retValue = GlobalTariffMstBO.chkAddUniqueData(voObj);
			if (retValue) 
			{
				retValue = addData(bean);
				if (retValue == false)
				{
					
					bean.setErrMsg("Record Not Saved!");
				}
				else
				{
					
					bean.setNormalMsg("Record Saved Successfully!");
				}
			} 
			else
			{
				
				bean.setErrMsg("Tariff Name already exists.");
			}	
		} catch (Exception e) {
			
			HisException eObj = new HisException("Billing",
					"GlobalTariffMstDATA->chkAddTariffName()", e.getMessage());
			
			
			bean.setErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		return retValue;
	}

	/**
	 * Initializes the BO and VO and transfers control to BO for Modification
	 * 
	 * @param fb
	 *  @param String
	 * @return boolean
	 */
	public boolean toModifyData(GlobalTariffMstFB bean, String val) {
		boolean retVal = false;
		GlobalTariffMstVO voObj = null;
		String chk = val;
		try {
            voObj = new GlobalTariffMstVO();
			
			voObj = (GlobalTariffMstVO) TransferObjectFactory.populateData(
					"billing.masters.vo.GlobalTariffMstVO", bean);
			retVal = GlobalTariffMstBO.getData(voObj, chk);
			
			bean.setStrgrpId(voObj.getStrgrpId());
			bean.setStrGroupId(voObj.getStrGroupId()); 
			bean.setStrtrfPkgName(voObj.getStrtrfPkgName());
			bean.setStrtariffId(voObj.getStrtariffId());
			bean.setStrseatId(voObj.getStrseatId());
			bean.setStrisValid(voObj.getStrisValid());
			bean.setStrgroupName(voObj.getStrgroupName());
			bean.setStrsubgroupName(voObj.getStrsubgroupName());
			
			
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"GlobalTariffMstDATA->getData()", e.getMessage());
			
			
			bean.setErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		return retVal;
	}

	/**
	 * Initializes the BO and VO and transfers control to BO for Updation
	 * 
	 * @param fb
	 * @return boolean
	 */
	public boolean modifyData(GlobalTariffMstFB bean) {
		boolean retValue = false;
		GlobalTariffMstVO voObj = null;
		try {
	           voObj = new GlobalTariffMstVO();
				
			voObj = (GlobalTariffMstVO) TransferObjectFactory.populateData(
						"billing.masters.vo.GlobalTariffMstVO", bean);
			retValue = GlobalTariffMstBO.updateData(voObj);
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"GlobalTariffMstDATA->updateData()", e.getMessage());
			
			
			bean.setErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		
		return retValue;
	}

	
	/**
	 * to check uniqueness of data while modifying.
	 * 
	 * @param fb
	 * @return boolean
	 */
	public boolean chkModifyTariffName(GlobalTariffMstFB bean)
	{
		boolean retValue = false;
		GlobalTariffMstVO voObj = null;
		try 
		{
	         voObj = new GlobalTariffMstVO();
				
			voObj = (GlobalTariffMstVO) TransferObjectFactory.populateData(
							"billing.masters.vo.GlobalTariffMstVO", bean);
			retValue = GlobalTariffMstBO.chkModifyUniqueData(voObj);
			if (retValue) 
			{
				retValue = modifyData(bean);
				if (retValue == false)
					bean.setErrMsg("Record not Updated!");
			} else
				bean.setErrMsg("Tariff Name already exists.");
		} catch (Exception e) {
			HisException eObj = new HisException("Billing",
					"GlobalTariffMstDATA->chkModifyTariffName()", e.getMessage());
			
			
			bean.setErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}
		
		return retValue;
	}
	

	
}
