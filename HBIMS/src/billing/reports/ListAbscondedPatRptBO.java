package billing.reports;

public class ListAbscondedPatRptBO {

	public void getDepartmentDetails(ListAbscondedPatRptVO voObj){
		
		ListAbscondedPatRptDAO.getDepartmentList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListAbscondedPatRptBO.getDepartmentDetails()-->"+strErr);
		}		
	}
		
	public void getUnitDetails(ListAbscondedPatRptVO voObj){
			
		ListAbscondedPatRptDAO.getUnitList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListAbscondedPatRptBO.getUnitDetails()-->"+strErr);
		}		
	}

	public void getWardDetails(ListAbscondedPatRptVO voObj){
		
		ListAbscondedPatRptDAO.getWardList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListAbscondedPatRptBO.getWardDetails()-->"+strErr);
		}	
	}
}
