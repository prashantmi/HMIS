package ipd.reports;

public class HospitalStatisticsRptBO {

	public void getDepartmentDetails(HospitalStatisticsRptVO voObj){
		
		HospitalStatisticsRptDAO.getDepartmentList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("HospitalStatisticsRptBO.getDepartmentDetails()-->"+strErr);
		}
				
			}
			
		public void getUnitWardDetails(HospitalStatisticsRptVO voObj){
				
			HospitalStatisticsRptDAO.getUnitList(voObj);
			HospitalStatisticsRptDAO.getWardCombo(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("HospitalStatisticsRptBO.getUnitDetails()-->"+strErr);
			}
				
			}
		public void getWardCombo(HospitalStatisticsRptVO voObj){
			
			
			HospitalStatisticsRptDAO.getWardCombo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("HospitalStatisticsRptBO.getWardCombo()-->"+strErr);
			}
				
			}
		public void getdeptComboDetails(HospitalStatisticsRptVO voObj){
			
			
			HospitalStatisticsRptDAO.getdeptComboDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("AdmittedPatientRptBO.getdeptComboDetails()-->"+strErr);
			}
				
			}
		
		public void getHospitalName(HospitalStatisticsRptVO voObj){
			
			HospitalStatisticsRptDAO.getHospitalName(voObj);
	if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("HospitalStatisticsRptBO.getHospitalName()-->"+strErr);
			}
			
		}
		public void getWardDetails(HospitalStatisticsRptVO voObj){
			
			HospitalStatisticsRptDAO.getWardList(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("HospitalStatisticsRptBO.getWardDetails()-->"+strErr);
			}
			
		}
}
