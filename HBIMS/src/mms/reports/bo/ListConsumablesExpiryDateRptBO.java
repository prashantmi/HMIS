package mms.reports.bo;

import mms.reports.dao.ListConsumablesExpiryDateRptDAO;
import mms.reports.vo.ListConsumablesExpiryDateRptVO;

	public class ListConsumablesExpiryDateRptBO {
	
	public void getStoreList(ListConsumablesExpiryDateRptVO voObj,String strUserLevel){
		
		if(strUserLevel.equals("6")){
			voObj.setStrMode("6");
		}
		else
			voObj.setStrMode("1");
		ListConsumablesExpiryDateRptDAO.getStoreList(voObj);
		
		if(strUserLevel.equals("6")){
			voObj.setStrMode("4");
		}
		else
			voObj.setStrMode("5");
		
	//	ListConsumablesExpiryDateRptDAO.getDistrictStoreList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("ListConsumablesExpiryDateRptBO.getStoreList()-->"+strErr);
				}
				
		}

	public void getItemCatList(ListConsumablesExpiryDateRptVO voObj){
	
	ListConsumablesExpiryDateRptDAO.getItemCatList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListConsumablesExpiryDateRptBO.getItemCatList()-->"+strErr);
			}
			
	}


}
