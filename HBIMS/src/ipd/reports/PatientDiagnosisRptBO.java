package ipd.reports;

public class PatientDiagnosisRptBO {

public void getDepartmentDetails(PatientDiagnosisRptVO voObj){
		
	PatientDiagnosisRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatientDiagnosisRptBO.getDepartmentDetails()-->"+strErr);
	}
		
	}
	
public void getUnitDetails(PatientDiagnosisRptVO voObj){
		
	PatientDiagnosisRptDAO.getUnitList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatientDiagnosisRptBO.getUnitDetails()-->"+strErr);
	}
		
	}

public void getWardDetails(PatientDiagnosisRptVO voObj){
	
	PatientDiagnosisRptDAO.getWardList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatientDiagnosisRptBO.getWardDetails()-->"+strErr);
	}
	
}

public void getRoomDetails(PatientDiagnosisRptVO voObj){
	
	PatientDiagnosisRptDAO.getRoomList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatientDiagnosisRptBO.getRoomDetails()-->"+strErr);
	}
	
}
}
