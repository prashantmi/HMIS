package billing.reports;

public class ProvisionalFinalBillRptBO {
	
public void getDeptDetails(ProvisionalFinalBillRptVO voObj){
		
	ProvisionalFinalBillRptDAO.getDepartmentList(voObj);
		
	
	 if (voObj.getStrMsgType().equals("1"))
	 
	  {
		String strErr = voObj.getStrMsgString();
		voObj.setStrMsgString("DueDetailsRptBO.getDeptDetails()-->"+strErr);
	  }	

}



public void getWARDCMB(ProvisionalFinalBillRptVO voObj)
{
	
	ProvisionalFinalBillRptDAO.getWardList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DueDetailsRptBO.getWARDCMB()-->"+strErr);
		 }	
	
	}

}
