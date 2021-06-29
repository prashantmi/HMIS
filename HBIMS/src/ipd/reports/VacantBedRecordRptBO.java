package ipd.reports;

public class VacantBedRecordRptBO {

	public void getDepartmentDetails(VacantBedRecordRptVO voObj){
		
		VacantBedRecordRptDAO.getDepartmentList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("VacantBedRecordRptBO.getDepartmentDetails()-->"+strErr);
		}
				
			}
	public void getWardCombo(VacantBedRecordRptVO voObj){
		
		
		VacantBedRecordRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("VacantBedRecordRptBO.getWardCombo()-->"+strErr);
		}
			
		}		
		public void getUnitWardDetails(VacantBedRecordRptVO voObj){
				
			VacantBedRecordRptDAO.getUnitList(voObj);
			VacantBedRecordRptDAO.getWardCombo(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("VacantBedRecordRptBO.getUnitDetails()-->"+strErr);
			}
				
			}

		public void getWardDetails(VacantBedRecordRptVO voObj){
			
			VacantBedRecordRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("VacantBedRecordRptBO.getWardDetails()-->"+strErr);
			}
			
		}
		public void getHospitalName(VacantBedRecordRptVO voObj){
			
			VacantBedRecordRptDAO.getHospitalName(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("VacantBedRecordRptBO.getHospitalName()-->"+strErr);
		}
		
	}
		public void getdeptComboDetails(VacantBedRecordRptVO voObj){
			
			
			VacantBedRecordRptDAO.getdeptComboDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("VacantBedRecordRptBO.getdeptComboDetails()-->"+strErr);
			}
				
			}

}
