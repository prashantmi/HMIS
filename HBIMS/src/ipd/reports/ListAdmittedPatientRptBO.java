package ipd.reports;

public class ListAdmittedPatientRptBO {

	public void getDepartmentDetails(ListAdmittedPatientRptVO voObj){
		
		ListAdmittedPatientRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListAdmittedPatientRptBO.getDepartmentDetails()-->"+strErr);
		}
			
		}
	public void getWardCombo(ListAdmittedPatientRptVO voObj){
		
		
		ListAdmittedPatientRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListAdmittedPatientRptBO.getWardCombo()-->"+strErr);
		}
			
		}	
	public void getUnitWardDetails(ListAdmittedPatientRptVO voObj){
		
		ListAdmittedPatientRptDAO.getUnitList(voObj);
		ListAdmittedPatientRptDAO.getWardCombo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListAbscondingCaseRptBO.getUnitDetails()-->"+strErr);
		}
			
		}

	public void getWardDetails(ListAdmittedPatientRptVO voObj){
		
		ListAdmittedPatientRptDAO.getWardList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListAdmittedPatientRptBO.getWardDetails()-->"+strErr);
		}
		
	}
	
	/*
	 * Function to get the Hospital Combo List
	 * @param: ListAdmittedPatientRptVO voObj
	 */
	public void getHospitalName(ListAdmittedPatientRptVO voObj){
		
		ListAdmittedPatientRptDAO.getHospitalName(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmittedPatientRptBO.getHospitalName()-->"+strErr);
		}
		
	}
	
	public void getdeptComboDetails(ListAdmittedPatientRptVO voObj){
		
		
		ListAdmittedPatientRptDAO.getdeptComboDetails(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListAdmittedPatientRptBO.getdeptComboDetails()-->"+strErr);
		}
			
		}
		
}
