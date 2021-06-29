package ipd.reports;

public class ListMLCDeathRptBO {

public void getDepartmentDetails(ListMLCDeathRptVO voObj){
		
	ListMLCDeathRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ListMLCDeathRptBO.getDepartmentDetails()-->"+strErr);
	}
				
			}
			
		public void getUnitWardDetails(ListMLCDeathRptVO voObj){
				
			ListMLCDeathRptDAO.getUnitList(voObj);
			ListMLCDeathRptDAO.getWardCombo(voObj);
			
			if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListMLCDeathRptBO.getUnitDetails()-->"+strErr);
			}
				
			}
		public void getWardCombo(ListMLCDeathRptVO voObj){
			
			
			ListMLCDeathRptDAO.getWardCombo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListMLCDeathRptBO.getWardCombo()-->"+strErr);
			}
				
			}
		public void getWardDetails(ListMLCDeathRptVO voObj){
			
			ListMLCDeathRptDAO.getWardList(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListMLCDeathRptBO.getWardDetails()-->"+strErr);
			}
			
		}
		
		public void getHospitalName(ListMLCDeathRptVO voObj){
			
			ListMLCDeathRptDAO.getHospitalName(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListMLCDeathRptBO.getHospitalName()-->"+strErr);
			}
			
		}
		public void getdeptComboDetails(ListMLCDeathRptVO voObj){
			
			
			ListMLCDeathRptDAO.getdeptComboDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListMLCDeathRptBO.getdeptComboDetails()-->"+strErr);
			}
				
			}


}
