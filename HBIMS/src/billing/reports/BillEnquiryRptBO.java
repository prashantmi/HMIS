package billing.reports;
/*
 * Bill Enquiry Report BO
 * 
 * author: Debashis Sardar
 * 
 * dated: 05/03/2012
 */
public class BillEnquiryRptBO {
	public void getBillTypeDtls(BillEnquiryRptVO voObj){
		
		BillEnquiryRptDAO.getBillTypeDtls(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("BillEnquiryRptBO.getBillTypeDtls()-->"+strErr);
			}
	   }
public void getClientName(BillEnquiryRptVO voObj){
		
		BillEnquiryRptDAO.getClientName(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("BillEnquiryRptBO.getBillTypeDtls()-->"+strErr);
			}
	   }
	
public void checkFinalBill(BillEnquiryRptVO voObj){
		
	BillEnquiryRptDAO.checkFinalBill(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("BillEnquiryRptBO.checkFinalBill()-->"+strErr);
			}
				
}
	
public void getBillDetails(BillEnquiryRptVO voObj){
		
		BillEnquiryRptDAO.getBillDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("BillEnquiryRptBO.getBillDetails()-->"+strErr);
			}
	   }


public void getBillContent(BillEnquiryRptVO voObj){
		
		BillEnquiryRptDAO.getBillContent(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("BillEnquiryRptBO.getBillDetails()-->"+strErr);
			}
	   }

//to make html from the requested billing data n write it via ajax
public void getBillData(BillEnquiryRptVO voObj){
		
		BillEnquiryRptDAO.getBillReportDetails(voObj);
		
		if (voObj.getStrMsgType().equals("1")) 
		{
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("BillEnquiryRptBO.getBillData()-->"+strErr);
			}
	   }



}
