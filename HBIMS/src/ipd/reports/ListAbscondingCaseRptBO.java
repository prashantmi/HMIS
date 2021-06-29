package ipd.reports;

public class ListAbscondingCaseRptBO {

	public void getDepartmentDetails(ListAbscondingCaseRptVO voObj){
		
		ListAbscondingCaseRptDAO.getDepartmentList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListAbscondingCaseRptBO.getDepartmentDetails()-->"+strErr);
		}
				
			}
public void getHospitalName(ListAbscondingCaseRptVO voObj){
		
	ListAbscondingCaseRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListAbscondingCaseRptBO.getHospitalName()-->"+strErr);
		}
		
	}

public void getdeptComboDetails(ListAbscondingCaseRptVO voObj){
	
	
	ListAbscondingCaseRptDAO.getDepartmentList(voObj);
	
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ListAbscondingCaseRptBO.getdeptComboDetails()-->"+strErr);
	}
		
	}

		public void getUnitWardDetails(ListAbscondingCaseRptVO voObj){
				
			ListAbscondingCaseRptDAO.getUnitList(voObj);
			ListAbscondingCaseRptDAO.getWardCombo(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListAbscondingCaseRptBO.getUnitDetails()-->"+strErr);
			}
				
			}

		public void getWardDetails(ListAbscondingCaseRptVO voObj){
			
			ListAbscondingCaseRptDAO.getWardList(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ListAbscondingCaseRptBO.getWardDetails()-->"+strErr);
			}
			
		}
}
