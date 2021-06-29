package ipd.reports;

public class StatisticalRptDischargePatRptBO {
	
public void getDepartmentDetails(StatisticalRptDischargePatRptVO voObj){
		
	StatisticalRptDischargePatRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("StatisticalRptDischargePatRptBO.getDepartmentDetails()-->"+strErr);
	}
		
	}
	
public void getUnitWardDetails(StatisticalRptDischargePatRptVO voObj){
		
	StatisticalRptDischargePatRptDAO.getUnitList(voObj);
	StatisticalRptDischargePatRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("StatisticalRptDischargePatRptBO.getUnitDetails()-->"+strErr);
	}
		
	}

public void getWardDetails(StatisticalRptDischargePatRptVO voObj){
	
	StatisticalRptDischargePatRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("StatisticalRptDischargePatRptBO.getWardDetails()-->"+strErr);
	}
	
}

public void getCategoryDetails(StatisticalRptDischargePatRptVO voObj){
	
	StatisticalRptDischargePatRptDAO.getCategoryList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("StatisticalRptDischargePatRptBO.getCategoryDetails()-->"+strErr);
	}
	
}

public void getHospitalName(StatisticalRptDischargePatRptVO voObj){
	
	StatisticalRptDischargePatRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
	
	String strErr = voObj.getStrMsgString();
		
	voObj.setStrMsgString("StatisticalRptDischargePatRptBO.getHospitalName()-->"+strErr);
}

}
public void getdeptComboDetails(StatisticalRptDischargePatRptVO voObj){
	
	
	StatisticalRptDischargePatRptDAO.getdeptComboDetails(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("StatisticalRptDischargePatRptBO.getdeptComboDetails()-->"+strErr);
	}
		
	}

}
