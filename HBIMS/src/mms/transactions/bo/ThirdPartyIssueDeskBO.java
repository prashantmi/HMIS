package mms.transactions.bo;

import mms.transactions.dao.ThirdPartyIssueDeskDAO;
import mms.transactions.vo.ThirdPartyIssueDeskVO;

public class ThirdPartyIssueDeskBO {
	
	
public void getItemDetails(ThirdPartyIssueDeskVO voObj){
		
	//System.out.println("InBO");
		ThirdPartyIssueDeskDAO.getItemDetails(voObj);
		//System.out.println("OutBO");
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ThirdPartyIssueDeskBO.getItemDetails()-->"+strErr);
		}
		
	}
	
}
