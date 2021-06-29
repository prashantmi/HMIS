package ipd.reports;

public class NonAcceptedPatientListingRptBO {

	public void getDepartmentDetails(NonAcceptedPatientListingRptVO voObj){
		
		NonAcceptedPatientListingRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("NonAcceptedPatientListingRptBO.getDepartmentDetails()-->"+strErr);
		}
		
	}
	
public void getUnitWardDetails(NonAcceptedPatientListingRptVO voObj){
		
	NonAcceptedPatientListingRptDAO.getUnitList(voObj);
	NonAcceptedPatientListingRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("NonAcceptedPatientListingRptBO.getUnitDetails()-->"+strErr);
	}
		
	}

public void getWardDetails(NonAcceptedPatientListingRptVO voObj){
	
	NonAcceptedPatientListingRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("NonAcceptedPatientListingRptBO.getWardDetails()-->"+strErr);
	}
	
}
public void getHospitalName(NonAcceptedPatientListingRptVO voObj){
	
	NonAcceptedPatientListingRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("NonAcceptedPatientListingRptBO.getHospitalName()-->"+strErr);
	}
	
}

public void getdeptComboDetails(NonAcceptedPatientListingRptVO voObj){
	
	
	NonAcceptedPatientListingRptDAO.getdeptComboDetails(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("NonAcceptedPatientListingRptBO.getdeptComboDetails()-->"+strErr);
	}
		
	}

}
