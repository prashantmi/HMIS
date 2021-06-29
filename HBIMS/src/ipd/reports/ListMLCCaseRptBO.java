package ipd.reports;

public class ListMLCCaseRptBO {

	public void getDepartmentDetails(ListMLCCaseRptVO voObj){
		
		ListMLCCaseRptDAO.getDepartmentList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListMLCCaseRptBO.getDepartmentDetails()-->"+strErr);
		}
				
			}
			
		public void getUnitWardDetails(ListMLCCaseRptVO voObj){
				
			ListMLCCaseRptDAO.getUnitList(voObj);
			ListMLCCaseRptDAO.getWardCombo(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListMLCCaseRptBO.getUnitDetails()-->"+strErr);
			}
				
			}
		public void getWardCombo(ListMLCCaseRptVO voObj){
			
			
			ListMLCCaseRptDAO.getWardCombo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListMLCCaseRptBO.getWardCombo()-->"+strErr);
			}
				
			}
		public void getWardDetails(ListMLCCaseRptVO voObj){
			
			ListMLCCaseRptDAO.getWardList(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListMLCCaseRptBO.getWardDetails()-->"+strErr);
			}
			
		}
}
