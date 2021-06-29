package billing.reports;

public class ChargeDetailRptBO {

public void getHospSerDetails(ChargeDetailRptVO voObj){
		
		ChargeDetailRptDAO.getHospSerList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ChargeDetailRptBO.getHospSerDetails()-->"+strErr);
		}
	}

public void getHospitalName(ChargeDetailRptVO voObj){
	
	ChargeDetailRptDAO.getHospitalName(voObj);
	ChargeDetailRptDAO.getHospSerList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ChargeDetailRptBO.getHospitalName()-->"+strErr);
	}
	
}
	public void getCategoryDetails(ChargeDetailRptVO voObj){
		
		ChargeDetailRptDAO.getCategoryList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ChargeDetailRptBO.getCategoryDetails()-->"+strErr);
		}	
	}
	
	public void getGroupDetails(ChargeDetailRptVO voObj){
		
		ChargeDetailRptDAO.getGroupList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ChargeDetailRptBO.getGroupDetails()-->"+strErr);
		}	
	}
	
	
public void getReportDetails(ChargeDetailRptVO voObj){
		
		ChargeDetailRptDAO.getReportDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ChargeDetailRptBO.getGroupDetails()-->"+strErr);
		}	
	}
}
