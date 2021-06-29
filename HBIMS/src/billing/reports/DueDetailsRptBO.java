package billing.reports;



public class DueDetailsRptBO {
	
public void getDeptDetails(DueDetailsRptVO voObj){
		
		DueDetailsRptDAO.getDepartmentList(voObj);
		
	
	 if (voObj.getStrMsgType().equals("1"))
	 
	  {
		String strErr = voObj.getStrMsgString();
		voObj.setStrMsgString("DueDetailsRptBO.getDeptDetails()-->"+strErr);
	  }	
	
	
		
	
}
public void getHospitalName(DueDetailsRptVO voObj){
	
	DueDetailsRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("AdmittedPatientRptBO.getHospitalName()-->"+strErr);
	}
	
}

public void getUNITCMB(DueDetailsRptVO voObj)
{
	
	DueDetailsRptDAO.getUnitList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DueDetailsRptBO.getUNITCMB()-->"+strErr);
		 }	
	
	}


public void getWARDCMB(DueDetailsRptVO voObj)
{
	
	DueDetailsRptDAO.getWardList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DueDetailsRptBO.getWARDCMB()-->"+strErr);
		 }	
	
	}
}
