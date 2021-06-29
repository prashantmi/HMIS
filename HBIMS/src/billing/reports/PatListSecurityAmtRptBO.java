package billing.reports;


public class PatListSecurityAmtRptBO {

public void getDepartmentDetails(PatListSecurityAmtRptVO voObj){
		
	PatListSecurityAmtRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatListSecurityAmtRptBO.getDepartmentDetails()-->"+strErr);
	}		
	}
	
public void getUnitDetails(PatListSecurityAmtRptVO voObj){
		
	PatListSecurityAmtRptDAO.getUnitList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatListSecurityAmtRptBO.getUnitDetails()-->"+strErr);
	}		
	}

public void getWardDetails(PatListSecurityAmtRptVO voObj){
	
	PatListSecurityAmtRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatListSecurityAmtRptBO.getWardDetails()-->"+strErr);
	}	
}
}
