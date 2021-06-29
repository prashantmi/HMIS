package mms.reports.bo;

import mms.reports.dao.BreakageAndLostDrugDetailsRptDAO;
import mms.reports.vo.BreakageAndLostDrugDetailsRptVO;
import hisglobal.transactionmgnt.HisDAO;


public class BreakageAndLostDrugDetailsRptBO 
{

	
	/**
	 * To get Drug Warehouse Type Combo
	 * 
	 * @param voObj
	 */
	public void getInitializedValues(BreakageAndLostDrugDetailsRptVO voObj)
	{
	
	BreakageAndLostDrugDetailsRptDAO.getStoreList(voObj);
	BreakageAndLostDrugDetailsRptDAO.getDistrictStoreList(voObj);
	
		if (voObj.getStrMsgType().equals("1")) 
		{
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("BreakageAndLostDrugDetailsRptBO.getDwhTypeCombo()-->"+strErr);
		}
	}
	

	public void getItemCatList(BreakageAndLostDrugDetailsRptVO voObj){
		
		BreakageAndLostDrugDetailsRptDAO.getItemCatList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("BreakageAndLostDrugDetailsRptBO.getItemCatList()-->"+strErr);
				}
				
		}
	
	
	
	
	
}
