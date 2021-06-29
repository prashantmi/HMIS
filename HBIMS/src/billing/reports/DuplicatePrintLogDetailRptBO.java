/**
 * 
 */
package billing.reports;

/**
 * @author Administrator
 *
 */
public class DuplicatePrintLogDetailRptBO {
	
	public void getHospitalName(DuplicatePrintLogDetailRptVO voObj){
		
		DuplicatePrintLogDetailRptDAO.getHospitalName(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BillCancellationLogDetailRptBO.getHospitalName()-->"+strErr);
		}
		
	}

}
