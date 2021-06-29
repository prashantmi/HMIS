package ipd.reports;

public class ListDeathCaseRptBO {
	
public void getDepartmentDetails(ListDeathCaseRptVO voObj){
		
	ListDeathCaseRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ListDeathCaseRptBO.getDepartmentDetails()-->"+strErr);
	}
			
		}
		
	public void getUnitDetails(ListDeathCaseRptVO voObj){
			
		ListDeathCaseRptDAO.getUnitList(voObj);
		ListDeathCaseRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListDeathCaseRptBO.getUnitDetails()-->"+strErr);
		}
			
		}
	public void getWardCombo(ListDeathCaseRptVO voObj){
		
		
		ListDeathCaseRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListDeathCaseRptBO.getWardCombo()-->"+strErr);
		}
			
		}
	public void getWardDetails(ListDeathCaseRptVO voObj){
		
		ListDeathCaseRptDAO.getWardList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListDeathCaseRptBO.getWardDetails()-->"+strErr);
		}
		
	}
	
public void getHospitalName(ListDeathCaseRptVO voObj){
		
	ListDeathCaseRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListDeathCaseRptBO.getHospitalName()-->"+strErr);
		}
		
	}
public void getdeptComboDetails(ListDeathCaseRptVO voObj){
	
	
	ListDeathCaseRptDAO.getdeptComboDetails(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ListDeathCaseRptBO.getdeptComboDetails()-->"+strErr);
	}
		
	}

}
