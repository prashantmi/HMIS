package ipd.reports;

public class IndoorStatisticsRptBO {

public void getDepartmentDetails(IndoorStatisticsRptVO voObj){
		
	IndoorStatisticsRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("IndoorStatisticsRptBO.getDepartmentDetails()-->"+strErr);
	}
			
		}
		
	public void getUnitWardDetails(IndoorStatisticsRptVO voObj){
			
		IndoorStatisticsRptDAO.getUnitList(voObj);
		IndoorStatisticsRptDAO.getWardCombo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("IndoorStatisticsRptBO.getUnitDetails()-->"+strErr);
		}
			
		}
	
public void getHospitalName(IndoorStatisticsRptVO voObj){
		
	IndoorStatisticsRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("IndoorStatisticsRptBO.getHospitalName()-->"+strErr);
		}
		
	}
	
	public void getWardCombo(IndoorStatisticsRptVO voObj){
		
		
		IndoorStatisticsRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("IndoorStatisticsRptBO.getWardCombo()-->"+strErr);
		}
			
		}
	
	public void getdeptComboDetails(IndoorStatisticsRptVO voObj){
		
		
		IndoorStatisticsRptDAO.getDepartmentList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("IndoorStatisticsRptBO.getdeptComboDetails()-->"+strErr);
		}
			
		}
	public void getWardDetails(IndoorStatisticsRptVO voObj){
		
		IndoorStatisticsRptDAO.getWardList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("IndoorStatisticsRptBO.getWardDetails()-->"+strErr);
		}
		
	}
}
