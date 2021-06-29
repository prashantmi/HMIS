package billing.reports;

public class DayEndCashHandoverRptBO {
	
public void getHospSerDetails(DayEndCashHandoverRptVO voObj){
		
	DayEndCashHandoverRptDAO.getHospSerList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DayEndCashHandoverRptBO.getHospSerDetails()-->"+strErr);
		}
   }

public void getHospitalName(DayEndCashHandoverRptVO voObj){
	
	DayEndCashHandoverRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("HospitalStatisticsRptBO.getHospitalName()-->"+strErr);
	}
	
}

public void getCounterDetails(DayEndCashHandoverRptVO voObj){
	DayEndCashHandoverRptDAO.getCounterList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DayEndCashHandoverRptBO.getCounterDetails()-->"+strErr);
	}
		
	}
public void getClerkDetails(DayEndCashHandoverRptVO voObj){
	
	DayEndCashHandoverRptDAO.getClerkList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DayEndCashHandoverRptBO.getClerkDetails()-->"+strErr);
	}
		
	}
public void getGroupDetails(DayEndCashHandoverRptVO voObj){
	
	DayEndCashHandoverRptDAO.getGroupList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DayEndCashHandoverRptBO.getGroupDetails()-->"+strErr);
	}
		
	}
public void getTariffDetails(DayEndCashHandoverRptVO voObj){
	
	DayEndCashHandoverRptDAO.getTariffList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DayEndCashHandoverRptBO.getTariffDetails()-->"+strErr);
	}
		
	}
public void getTariffDetailsCombo(DayEndCashHandoverRptVO voObj){
	
	DayEndCashHandoverRptDAO.getTariffDetailsCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DayEndCashHandoverRptBO.getTariffDetails()-->"+strErr);
	}
		
	}
public void getDeptDetails(DayEndCashHandoverRptVO voObj){
	
	DayEndCashHandoverRptDAO.getDeptList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DayEndCashHandoverRptBO.getDeptDetails()-->"+strErr);
	}
		
	}

public void getCostTypeDetails(DayEndCashHandoverRptVO voObj) {
	// TODO Auto-generated method stub
	DayEndCashHandoverRptDAO.getCostTypeList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DayEndCashHandoverRptBO.getDeptDetails()-->"+strErr);
	}
}
}
