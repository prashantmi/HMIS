package billing.reports;

public class UserFeeCollectionRptBO {
	
	
	public void getHospSerDetails(UserFeeCollectionRptVO voObj){
		
		UserFeeCollectionRptDAO.getHospSerList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("UserFeeCollectionRptBO.getHospSerDetails()-->"+strErr);
			}
	   }
	public void getHospitalName(UserFeeCollectionRptVO voObj){
		
		UserFeeCollectionRptDAO.getHospitalName(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("UserFeeCollectionRptBO.getHospitalName()-->"+strErr);
		}
		
	}

	public void getCounterDetails(UserFeeCollectionRptVO voObj){
		
		UserFeeCollectionRptDAO.getCounterList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("UserFeeCollectionRptBO.getCounterDetails()-->"+strErr);
		}
			
		}
	public void getClerkDetails(UserFeeCollectionRptVO voObj){
		
		UserFeeCollectionRptDAO.getClerkList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("UserFeeCollectionRptBO.getClerkDetails()-->"+strErr);
		}
			
		}
	public void getGroupDetails(UserFeeCollectionRptVO voObj){
		
		UserFeeCollectionRptDAO.getGroupList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("UserFeeCollectionRptBO.getGroupDetails()-->"+strErr);
		}
			
		}
	public void getTariffDetails(UserFeeCollectionRptVO voObj){
		
		UserFeeCollectionRptDAO.getTariffList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("UserFeeCollectionRptBO.getTariffDetails()-->"+strErr);
		}
			
		}
	public void getDeptDetails(UserFeeCollectionRptVO voObj){
		
		UserFeeCollectionRptDAO.getDeptList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("UserFeeCollectionRptBO.getDeptDetails()-->"+strErr);
		}
			
		} 
	}
