package billing.reports;

public class DiscountDetailsRptBO {

public void getHospSerDetails(DiscountDetailsRptVO voObj){
		
		DiscountDetailsRptDAO.getHospSerList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DiscountDetailsRptBO.getHospSerDetails()-->"+strErr);
		}
			
		}
public void getHospitalName(DiscountDetailsRptVO voObj){
	
	DiscountDetailsRptDAO.getHospitalName(voObj);
	//DiscountDetailsRptDAO.getHospSerList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ChargeDetailRptBO.getHospitalName()-->"+strErr);
	}
	
}
	public void getGroupDetails(DiscountDetailsRptVO voObj){
			
		DiscountDetailsRptDAO.getGroupList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DiscountDetailsRptBO.getGroupDetails()-->"+strErr);
		}	
		}
	public void getCategoryDetails(DiscountDetailsRptVO voObj){
		
		DiscountDetailsRptDAO.getCategoryList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DiscountDetailsRptBO.getCategoryDetails()-->"+strErr);
		}	
		}
		
	public void getConsultDetails(DiscountDetailsRptVO voObj){
			
		DiscountDetailsRptDAO.getConsultList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DiscountDetailsRptBO.getConsultDetails()-->"+strErr);
		}	
		}
	public void getReasonDetails(DiscountDetailsRptVO voObj){
		
		DiscountDetailsRptDAO.getReasonList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DiscountDetailsRptBO.getReasonDetails()-->"+strErr);
		}	
		
		}
	
public void getTariffDetails(DiscountDetailsRptVO voObj){
		
		DiscountDetailsRptDAO.getTariffList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DiscountDetailsRptBO.getTariffDetails()-->"+strErr);
		}	
}
}
