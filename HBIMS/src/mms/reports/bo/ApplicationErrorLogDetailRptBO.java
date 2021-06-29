package mms.reports.bo;

import mms.reports.dao.ApplicationErrorLogDetailRptDAO;
import mms.reports.vo.ApplicationErrorLogDetailRptVO;

public class ApplicationErrorLogDetailRptBO {
	
public void getStoreList(ApplicationErrorLogDetailRptVO voObj){
		
		ApplicationErrorLogDetailRptDAO.getStoreList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("ApplicationErrorLogDetailRptBO.getStoreList()-->"+strErr);
				}
				
		}

}
