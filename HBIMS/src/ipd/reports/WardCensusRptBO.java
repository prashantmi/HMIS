package ipd.reports;

public class WardCensusRptBO {

public void getDepartmentDetails(WardCensusRptVO voObj){
		
	WardCensusRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("WardCensusRptBO.getDepartmentDetails()-->"+strErr);
	}
		
	}
	
public void getUnitWardDetails(WardCensusRptVO voObj){
		
	WardCensusRptDAO.getUnitList(voObj);
	WardCensusRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("WardCensusRptBO.getUnitDetails()-->"+strErr);
	}
		
	}
public void getWardCombo(WardCensusRptVO voObj){
	
	
	WardCensusRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("WardCensusRptBO.getWardCombo()-->"+strErr);
	}
		
	}
public void getWardDetails(WardCensusRptVO voObj){
	
	WardCensusRptDAO.getWardList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("WardCensusRptBO.getWardDetails()-->"+strErr);
	}
	
}

public void getRoomDetails(WardCensusRptVO voObj){
	
	WardCensusRptDAO.getRoomList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("WardCensusRptBO.getRoomDetails()-->"+strErr);
	}
	
}
public void getHospitalName(WardCensusRptVO voObj){
	
	WardCensusRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
	
	String strErr = voObj.getStrMsgString();
		
	voObj.setStrMsgString("WardCensusRptBO.getHospitalName()-->"+strErr);
}

}
public void getdeptComboDetails(WardCensusRptVO voObj){
	
	
	WardCensusRptDAO.getdeptComboDetails(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("WardCensusRptBO.getdeptComboDetails()-->"+strErr);
	}
		
}
	

}
