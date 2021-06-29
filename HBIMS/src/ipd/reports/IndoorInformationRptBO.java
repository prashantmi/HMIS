package ipd.reports;

public class IndoorInformationRptBO {

	
	public void getDepartmentDetails(IndoorInformationRptVO voObj){
		
		IndoorInformationRptDAO.getDepartmentList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("IndoorInformationRptBO.getDepartmentDetails()-->"+strErr);
		}
			
		}
	public void getdeptComboDetails(IndoorInformationRptVO voObj){
		
		
		IndoorInformationRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmittedPatientRptBO.getdeptComboDetails()-->"+strErr);
		}
			
		}
	public void getUnitWardDetails(IndoorInformationRptVO voObj){
			
		IndoorInformationRptDAO.getUnitList(voObj);
		IndoorInformationRptDAO.getWardCombo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("IndoorInformationRptBO.getUnitDetails()-->"+strErr);
		}
			
		}

	public void getWardDetails(IndoorInformationRptVO voObj){
		
		IndoorInformationRptDAO.getWardList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("IndoorInformationRptBO.getWardDetails()-->"+strErr);
		}
		
	}
}
