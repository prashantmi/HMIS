package ipd.reports;

public class AdmissionCashCollectionRptBO {
	
	public void getCategoryDetails(AdmissionCashCollectionRptVO voObj){
		
		AdmissionCashCollectionRptDAO.getCategoryList(voObj);
		
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmissionCashCollectionRptBO.getCategoryDetails()-->"+strErr);
		}
	}

}
