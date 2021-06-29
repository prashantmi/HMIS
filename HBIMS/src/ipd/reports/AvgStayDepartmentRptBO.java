package ipd.reports;

public class AvgStayDepartmentRptBO {

public void getDepartmentDetails(AvgStayDepartmentRptVO voObj){
		
	AvgStayDepartmentRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AvgStayDepartmentRptBO.getDepartmentDetails()-->"+strErr);
	}
		
	}

public void getWardDetails(AvgStayDepartmentRptVO voObj){
	
	AvgStayDepartmentRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AvgStayDepartmentRptBO.getWardDetails()-->"+strErr);
	}
	
}

public void getUnitWardDetails(AvgStayDepartmentRptVO voObj){
	
	AvgStayDepartmentRptDAO.getUnitList(voObj);
	AvgStayDepartmentRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AvgStayDepartmentRptBO.getUnitDetails()-->"+strErr);
	}
	
}
	
}
