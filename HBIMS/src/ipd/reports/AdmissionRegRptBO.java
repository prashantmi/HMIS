package ipd.reports;

public class AdmissionRegRptBO {

	public void getDepartmentDetails(AdmissionRegRptVO voObj){
		
		AdmissionRegRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmissionRegRptBO.getDepartmentDetails()-->"+strErr);
		}
		
	}
	
public void getUnitWardDetails(AdmissionRegRptVO voObj){
		
	AdmissionRegRptDAO.getUnitList(voObj);
	AdmissionRegRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmissionRegRptBO.getUnitDetails()-->"+strErr);
	}
		
	}
public void getHospitalName(AdmissionRegRptVO voObj){
	
	AdmissionRegRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmissionRegRptBO.getHospitalName()-->"+strErr);
	}
	
}
public void getWardDetails(AdmissionRegRptVO voObj){
	
	AdmissionRegRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmissionRegRptBO.getWardDetails()-->"+strErr);
	}
	
}

public void getCategoryDetails(AdmissionRegRptVO voObj){
	
	AdmissionRegRptDAO.getCategoryList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmissionRegRptBO.getCategoryList()-->"+strErr);
	}
	
}

}
