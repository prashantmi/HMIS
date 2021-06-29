package mms.reports.bo;

import mms.reports.dao.PendingIndentStatusRecordRptDAO;
import mms.reports.vo.PendingIndentStatusRecordRptVO;

public class PendingIndentStatusRecordRptBO {
	
public void getStoreList(PendingIndentStatusRecordRptVO voObj){
		
		PendingIndentStatusRecordRptDAO.getStoreList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("PendingIndentStatusRecordRptBO.getStoreList()-->"+strErr);
				}
				
		}

public void getItemCatList(PendingIndentStatusRecordRptVO voObj){
	
	PendingIndentStatusRecordRptDAO.getItemCatList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("PendingIndentStatusRecordRptBO.getItemCatList()-->"+strErr);
			}
			
	}
public void getReqTypeList(PendingIndentStatusRecordRptVO voObj){
	
	PendingIndentStatusRecordRptDAO.getIReqTypeList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("PendingIndentStatusRecordRptBO.getItemCatList()-->"+strErr);
			}
			
	}

}
