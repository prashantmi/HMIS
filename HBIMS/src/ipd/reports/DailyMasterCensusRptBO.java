package ipd.reports;

public class DailyMasterCensusRptBO {

public void getDepartmentDetails(DailyMasterCensusRptVO voObj){
		
	DailyMasterCensusRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyWardCensusSumRptBO.getDepartmentDetails()-->"+strErr);
	}
		
	}
public void getdeptComboDetails(DailyMasterCensusRptVO voObj){
	
	
	DailyMasterCensusRptDAO.getdeptComboDetails(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyMasterCensusRptBO.getdeptComboDetails()-->"+strErr);
	}
		
	}
public void getUnitWardDetails(DailyMasterCensusRptVO voObj){
		
	DailyMasterCensusRptDAO.getUnitList(voObj);
	DailyMasterCensusRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyWardCensusSumRptBO.getUnitDetails()-->"+strErr);
	}
		
	}


public void getHospitalName(DailyMasterCensusRptVO voObj){
	
	DailyMasterCensusRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyWardCensusSumRptBO.getHospitalName()-->"+strErr);
	}
	
}

public void getWardCombo(DailyMasterCensusRptVO voObj){
	
	
	DailyMasterCensusRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyMasterCensusRptBO.getWardCombo()-->"+strErr);
	}
		
	}
public void getWardDetails(DailyMasterCensusRptVO voObj){
	
	DailyMasterCensusRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyWardCensusSumRptBO.getWardDetails()-->"+strErr);
	}
	
}

	
}
