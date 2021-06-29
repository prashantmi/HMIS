package billing.reports;

public class FeeCollectionDetailsRptBO {
	
public void getHospSerDetails(FeeCollectionDetailsRptVO voObj){
		
	FeeCollectionDetailsRptDAO.getHospSerList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("FeeCollectionDetailsRptBO.getHospSerDetails()-->"+strErr);
		}
   }

public void getHospitalName(FeeCollectionDetailsRptVO voObj){
	
	FeeCollectionDetailsRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("HospitalStatisticsRptBO.getHospitalName()-->"+strErr);
	}
	
}

public void getCounterDetails(FeeCollectionDetailsRptVO voObj){
	FeeCollectionDetailsRptDAO.getCounterList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("FeeCollectionDetailsRptBO.getCounterDetails()-->"+strErr);
	}
		
	}
public void getClerkDetails(FeeCollectionDetailsRptVO voObj){
	
	FeeCollectionDetailsRptDAO.getClerkList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("FeeCollectionDetailsRptBO.getClerkDetails()-->"+strErr);
	}
		
	}
public void getGroupDetails(FeeCollectionDetailsRptVO voObj){
	
	FeeCollectionDetailsRptDAO.getGroupList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("FeeCollectionDetailsRptBO.getGroupDetails()-->"+strErr);
	}
		
	}
public void getTariffDetails(FeeCollectionDetailsRptVO voObj){
	
	FeeCollectionDetailsRptDAO.getTariffList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("FeeCollectionDetailsRptBO.getTariffDetails()-->"+strErr);
	}
		
	}
public void getTariffDetailsCombo(FeeCollectionDetailsRptVO voObj){
	
	FeeCollectionDetailsRptDAO.getTariffDetailsCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("FeeCollectionDetailsRptBO.getTariffDetails()-->"+strErr);
	}
		
	}
public void getDeptDetails(FeeCollectionDetailsRptVO voObj){
	
	FeeCollectionDetailsRptDAO.getDeptList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("FeeCollectionDetailsRptBO.getDeptDetails()-->"+strErr);
	}
		
	}
}
