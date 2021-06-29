package ipd.reports;

public class DailyWardSummaryRptBO {

public void getDepartmentDetails(DailyWardSummaryRptVO voObj){
		
	DailyWardSummaryRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyWardSummaryRptBO.getDepartmentDetails()-->"+strErr);
	}
		
	}
	
public void getUnitWardDetails(DailyWardSummaryRptVO voObj){
		
	DailyWardSummaryRptDAO.getUnitList(voObj);
	DailyWardSummaryRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyWardSummaryRptBO.getUnitDetails()-->"+strErr);
	}
		
	}
public void getWardCombo(DailyWardSummaryRptVO voObj){
	
	
	DailyWardSummaryRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyWardSummaryRptBO.getWardCombo()-->"+strErr);
	}
		
	}
public void getWardDetails(DailyWardSummaryRptVO voObj){
	
	DailyWardSummaryRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyWardSummaryRptBO.getWardDetails()-->"+strErr);
	}
	
}
}
