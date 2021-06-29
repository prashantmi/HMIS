package ipd.reports;

public class ListNonMLCDeathRptBO {

	public void getDepartmentDetails(ListNonMLCDeathRptVO voObj){
		
		ListNonMLCDeathRptDAO.getDepartmentList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ListNonMLCDeathRptBO.getDepartmentDetails()-->"+strErr);
		}
					
				}
				
			public void getUnitWardDetails(ListNonMLCDeathRptVO voObj){
					
				ListNonMLCDeathRptDAO.getUnitList(voObj);
				ListNonMLCDeathRptDAO.getWardCombo(voObj);
				if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("ListNonMLCDeathRptBO.getUnitDetails()-->"+strErr);
				}
					
				}
			public void getWardCombo(ListNonMLCDeathRptVO voObj){
				
				
				ListNonMLCDeathRptDAO.getWardCombo(voObj);
			if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("ListNonMLCDeathRptBO.getWardCombo()-->"+strErr);
				}
					
				}
			public void getWardDetails(ListNonMLCDeathRptVO voObj){
				
				ListNonMLCDeathRptDAO.getWardList(voObj);
				if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("ListNonMLCDeathRptBO.getWardDetails()-->"+strErr);
				}
				
			}
			public void getdeptComboDetails(ListNonMLCDeathRptVO voObj){
				
				
				ListNonMLCDeathRptDAO.getDepartmentList(voObj);
			if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("ListNonMLCDeathRptBO.getdeptComboDetails()-->"+strErr);
				}
					
				}
			public void getHospitalName(ListNonMLCDeathRptVO voObj){
				
				ListNonMLCDeathRptDAO.getHospitalName(voObj);
			if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("ListNonMLCDeathRptDAO.getHospitalName()-->"+strErr);
				}
				
			}
}


