package billing.reports;

public class PaymentDtlRptBO {

public void getHospSerDetails(PaymentDtlRptVO voObj){
		
	PaymentDtlRptDAO.getHospSerList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("PaymentDtlRptBO.getHospSerDetails()-->"+strErr);
		}
			
		}
public void getHospitalName(PaymentDtlRptVO voObj){
	
	PaymentDtlRptDAO.getHospitalName(voObj);
	PaymentDtlRptDAO.getHospSerList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ChargeDetailRptBO.getHospitalName()-->"+strErr);
	}
	
}

/*added for: 'payment mode' combo,  by: manisha gangwar dt: 23.8.18*/

public void getPaymentModeList(PaymentDtlRptVO voObj){
	
	PaymentDtlRptDAO.getPaymentModeList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PaymentDtlRptBO.getPaymentModeList()-->"+strErr);
	}
	
}
public void getFeeClerkList(PaymentDtlRptVO voObj)
{
	PaymentDtlRptDAO.getFeeClerkList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PaymentDtlRptBO.getFeeClerkList()-->"+strErr);
	}
	
}

}
