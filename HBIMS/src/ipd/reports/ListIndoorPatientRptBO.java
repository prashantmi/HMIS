package ipd.reports;

public class ListIndoorPatientRptBO {

public void getDepartmentDetails(ListIndoorPatientRptVO voObj){
		
	ListIndoorPatientRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ListIndoorPatientRptBO.getDepartmentDetails()-->"+strErr);
	}
		
	}
	
public void getUnitWardDetails(ListIndoorPatientRptVO voObj){
		
	ListIndoorPatientRptDAO.getUnitList(voObj);
	ListIndoorPatientRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ListIndoorPatientRptBO.getUnitDetails()-->"+strErr);
	}
		
	}
public void getWardCombo(ListIndoorPatientRptVO voObj){
	
	
	ListIndoorPatientRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ListIndoorPatientRptBO.getWardCombo()-->"+strErr);
	}
		
	}
public void getWardDetails(ListIndoorPatientRptVO voObj){
	
	ListIndoorPatientRptDAO.getWardList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ListIndoorPatientRptBO.getWardDetails()-->"+strErr);
	}
	
}

public void getHospitalName(ListIndoorPatientRptVO voObj){
	
	ListIndoorPatientRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ListIndoorPatientRptBO.getHospitalName()-->"+strErr);
	}
	
}
public void getdeptComboDetails(ListIndoorPatientRptVO voObj){
	
	
	ListIndoorPatientRptDAO.getdeptComboDetails(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ListIndoorPatientRptBO.getdeptComboDetails()-->"+strErr);
	}
		
	}

	
}
