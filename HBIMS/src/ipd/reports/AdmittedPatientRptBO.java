package ipd.reports;

public class AdmittedPatientRptBO {

	public void getDepartmentDetails(AdmittedPatientRptVO voObj){
		
		AdmittedPatientRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmittedPatientRptBO.getDepartmentDetails()-->"+strErr);
		}
		
	}
public void getHospitalName(AdmittedPatientRptVO voObj){
		
		AdmittedPatientRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmittedPatientRptBO.getHospitalName()-->"+strErr);
		}
		
	}
	
public void getUnitWardDetails(AdmittedPatientRptVO voObj){
		
		AdmittedPatientRptDAO.getUnitList(voObj);
		AdmittedPatientRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmittedPatientRptBO.getUnitDetails()-->"+strErr);
		}
		
	}
public void getWardCombo(AdmittedPatientRptVO voObj){
	
	
	AdmittedPatientRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmittedPatientRptBO.getWardCombo()-->"+strErr);
	}
		
	}

public void getdeptComboDetails(AdmittedPatientRptVO voObj)
{
	AdmittedPatientRptDAO.getdeptComboDetails(voObj);
	if (voObj.getStrMsgType().equals("1")) 
	{
		String strErr = voObj.getStrMsgString();
		voObj.setStrMsgString("AdmittedPatientRptBO.getdeptComboDetails()-->"+strErr);
	}		
}
public void getWardDetails(AdmittedPatientRptVO voObj){
	
	AdmittedPatientRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmittedPatientRptBO.getWardDetails()-->"+strErr);
	}
	
}

public void getRoomDetails(AdmittedPatientRptVO voObj){
	
	AdmittedPatientRptDAO.getRoomList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmittedPatientRptBO.getRoomDetails()-->"+strErr);
	}
	
}

}
