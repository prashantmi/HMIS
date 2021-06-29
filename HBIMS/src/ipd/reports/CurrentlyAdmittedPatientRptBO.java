package ipd.reports;

public class CurrentlyAdmittedPatientRptBO {

	public void getDepartmentDetails(CurrentlyAdmittedPatientRptVO voObj){
		
		CurrentlyAdmittedPatientRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("CurrentlyAdmittedPatientRptBO.getDepartmentDetails()-->"+strErr);
		}
		
	}
	
public void getUnitWardDetails(CurrentlyAdmittedPatientRptVO voObj){
		
		CurrentlyAdmittedPatientRptDAO.getUnitList(voObj);
		CurrentlyAdmittedPatientRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("CurrentlyAdmittedPatientRptBO.getUnitDetails()-->"+strErr);
		}
		
	}
public void getWardCombo(CurrentlyAdmittedPatientRptVO voObj){
	
	
	CurrentlyAdmittedPatientRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("CurrentlyAdmittedPatientRptBO.getWardCombo()-->"+strErr);
	}
		
	}
public void getWardDetails(CurrentlyAdmittedPatientRptVO voObj){
	
	CurrentlyAdmittedPatientRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("CurrentlyAdmittedPatientRptBO.getWardDetails()-->"+strErr);
	}
	
}
public void getHospitalName(CurrentlyAdmittedPatientRptVO voObj){
	
	CurrentlyAdmittedPatientRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmittedPatientRptBO.getHospitalName()-->"+strErr);
	}
	
}

public void getRoomDetails(CurrentlyAdmittedPatientRptVO voObj){
	
	CurrentlyAdmittedPatientRptDAO.getRoomList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("CurrentlyAdmittedPatientRptBO.getRoomDetails()-->"+strErr);
	}
	
}

}
