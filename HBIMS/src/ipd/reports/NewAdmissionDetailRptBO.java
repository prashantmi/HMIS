
	package ipd.reports;
	/*
	 * author : Debashis sardar
	 * 
	 * New Admission Detail User Wise BO
	 * 
	 * date: 30/12/2011
	 */
	public class NewAdmissionDetailRptBO {


	public void getClerkDetails(NewAdmissionDetailRptVO voObj){
		
		NewAdmissionDetailRptDAO.getClerkDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("NewAdmissionDetailRptBO.getClerkDetails()-->"+strErr);
		}
		
	}

	}


