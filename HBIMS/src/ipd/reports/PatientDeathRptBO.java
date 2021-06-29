package ipd.reports;

public class PatientDeathRptBO {
	
	public void getDepartmentDetails(PatientDeathRptVO voObj){
		
		PatientDeathRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("PatientDeathRptBO.getDepartmentDetails()-->"+strErr);
		}
			
		}
	public void getWardCombo(PatientDeathRptVO voObj){
		
		
		PatientDeathRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("PatientDeathRptBO.getWardCombo()-->"+strErr);
		}
			
		}
	public void getUnitWardDetails(PatientDeathRptVO voObj){
			
		PatientDeathRptDAO.getUnitList(voObj);
		PatientDeathRptDAO.getWardCombo(voObj);
		
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("PatientDeathRptBO.getUnitDetails()-->"+strErr);
		}
			
		}
	public void getWardDetails(PatientDeathRptVO voObj){
		
		PatientDeathRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("PatientDeathRptBO.getWardDetails()-->"+strErr);
		}
		
	}
	
	public void getHospitalName(PatientDeathRptVO voObj){
		
		PatientDeathRptDAO.getHospitalName(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("PatientDeathRptBO.getHospitalName()-->"+strErr);
		}
		
	}
	
	public void getdeptComboDetails(PatientDeathRptVO voObj){
		
		
		PatientDeathRptDAO.getdeptComboDetails(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("PatientDeathRptBO.getdeptComboDetails()-->"+strErr);
		}
			
		}

}
