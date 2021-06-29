/**
 * 
 */
package billing.reports;

/**
 * @author Administrator
 *
 */
public class BillCancellationLogDetailRptBO {
	
	public void getHospitalName(BillCancellationLogDetailRptVO voObj){
		
		BillCancellationLogDetailRptDAO.getHospitalName(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BillCancellationLogDetailRptBO.getHospitalName()-->"+strErr);
		}
		
	}
	
	
	
	public void getFeeClerkName(BillCancellationLogDetailRptVO voObj){
		BillCancellationLogDetailRptDAO.getFeeClerkName(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("BillCancellationLogDetailRptBO.getFeeClerkName()-->"+strErr);
			}
			
		
	}

}
