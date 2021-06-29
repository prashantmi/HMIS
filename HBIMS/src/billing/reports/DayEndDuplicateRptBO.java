package billing.reports;

import billing.reports.DayEndDuplicateRptDAO;

public class DayEndDuplicateRptBO {
	
	public void getSummaryDetails(DayEndDuplicateRptVO voObj) {

		try {
			
			DayEndDuplicateRptDAO.getSummaryDetails(voObj);

			if (voObj.getStrMsgType().equals("1")) {

				throw new Exception(voObj.getStrMsgString());

			}

		} catch (Exception e) {
			voObj.setStrMsgString("DayEndDuplicateRptBO.getSummaryDetails() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		}
		// return web;
	}
public void getHospitalName(DayEndDuplicateRptVO voObj){
		
	DayEndDuplicateRptDAO.getHospitalName(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DayEndDuplicateRptBO.getHospitalName()-->"+strErr);
		}
		
	}

}
