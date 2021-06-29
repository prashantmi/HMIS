package ipd.reports;

public class FileDepositRecordRptBO {

	
public void getDepartmentDetails(FileDepositRecordRptVO voObj){
		
	FileDepositRecordRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("FileDepositRecordRptBO.getDepartmentDetails()-->"+strErr);
	}
		
	}
	
public void getUnitDetails(FileDepositRecordRptVO voObj){
		
	FileDepositRecordRptDAO.getUnitList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("FileDepositRecordRptBO.getUnitDetails()-->"+strErr);
	}
		
	}

public void getWardDetails(FileDepositRecordRptVO voObj){
	
	FileDepositRecordRptDAO.getWardList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("FileDepositRecordRptBO.getWardDetails()-->"+strErr);
	}
	
}
}
