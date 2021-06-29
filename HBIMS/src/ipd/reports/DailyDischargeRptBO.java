package ipd.reports;

public class DailyDischargeRptBO {

	public void getDepartmentDetails(DailyDischargeRptVO voObj){
		
		DailyDischargeRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DailyDischargeRptBO.getDepartmentDetails()-->"+strErr);
		}
		
	}
	
public void getUnitWardDetails(DailyDischargeRptVO voObj){
		
	DailyDischargeRptDAO.getUnitList(voObj);
	DailyDischargeRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyDischargeRptBO.getUnitDetails()-->"+strErr);
	}
		
	}
public void getHospitalName(DailyDischargeRptVO voObj){
	
	DailyDischargeRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyDischargeRptDAO.getHospitalName()-->"+strErr);
	}
	
}
public void getdeptComboDetails(DailyDischargeRptVO voObj){
	
	
	DailyDischargeRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyDischargeRptBO.getdeptComboDetails()-->"+strErr);
	}
		
	}
public void getWardDetails(DailyDischargeRptVO voObj){
	
	DailyDischargeRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyDischargeRptBO.getWardDetails()-->"+strErr);
	}
	
}

public void getDischargeTypeName(DailyDischargeRptVO voObj)
{          
	DailyDischargeRptDAO.getDischargeTypeName(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyDischargeRptBO.getDischargeTypeName()-->"+strErr);
	}
	 
}

}
