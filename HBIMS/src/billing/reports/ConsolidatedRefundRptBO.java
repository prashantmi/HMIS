package billing.reports;

public class ConsolidatedRefundRptBO {
	
public void getHospSerDetails(ConsolidatedRefundRptVO voObj){
		
	ConsolidatedRefundRptDAO.getHospSerList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedFeeCollRptBO.getHospSerDetails()-->"+strErr);
		}
   }
public void getHospitalName(ConsolidatedRefundRptVO voObj){
	
	ConsolidatedRefundRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ConsolidatedRefundRptBO.getHospitalName()-->"+strErr);
	}
	
}
public void getCounterDetails(ConsolidatedRefundRptVO voObj){
		
	ConsolidatedRefundRptDAO.getCounterList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedRefundRptBO.getCounterDetails()-->"+strErr);
		}
			
		}
	public void getClerkDetails(ConsolidatedRefundRptVO voObj){
		
		ConsolidatedRefundRptDAO.getClerkList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedRefundRptBO.getClerkDetails()-->"+strErr);
		}
			
		}
	
	public void getTreatCatDetails(ConsolidatedRefundRptVO voObj){
		
		ConsolidatedRefundRptDAO.getTreatCatList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedRefundRptBO.getTreatCatDetails()-->"+strErr);
		}
			
		}
}
