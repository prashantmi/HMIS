package ipd.reports;

public class PatDischargeDtlRptBO {

public void getDepartmentDetails(PatDischargeDtlRptVO voObj){
		
	PatDischargeDtlRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmittedPatientRptBO.getDepartmentDetails()-->"+strErr);
		}
		
	}
	
public void getUnitWardDetails(PatDischargeDtlRptVO voObj){
		
	PatDischargeDtlRptDAO.getUnitList(voObj);
	PatDischargeDtlRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmittedPatientRptBO.getUnitDetails()-->"+strErr);
		}
		
	}
public void getWardCombo(PatDischargeDtlRptVO voObj){
	
	
	PatDischargeDtlRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatDischargeDtlRptBO.getWardCombo()-->"+strErr);
	}
		
	}
public void getWardDetails(PatDischargeDtlRptVO voObj){
	
	PatDischargeDtlRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmittedPatientRptBO.getWardDetails()-->"+strErr);
	}
	
}

public void getRoomDetails(PatDischargeDtlRptVO voObj){
	
	PatDischargeDtlRptDAO.getRoomList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmittedPatientRptBO.getRoomDetails()-->"+strErr);
	}
}
	public void getBedDetails(PatDischargeDtlRptVO voObj){
		
		PatDischargeDtlRptDAO.getBedList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmittedPatientRptBO.getBedDetails()-->"+strErr);
		}
		
	}
	public void getHospitalName(PatDischargeDtlRptVO voObj){
		
		PatDischargeDtlRptDAO.getHospitalName(voObj);
		if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatDischargeDtlRptBO.getHospitalName()-->"+strErr);
	}
	}
	public void getdeptComboDetails(PatDischargeDtlRptVO voObj){
		
		
		PatDischargeDtlRptDAO.getdeptComboDetails(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("PatDischargeDtlRptBO.getdeptComboDetails()-->"+strErr);
	}
	}

}
