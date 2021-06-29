

	package ipd.reports;
	/*
	 * author : Debashis sardar
	 * 
	 * New Admission Detail Ward Wise BO
	 * 
	 * date: 02/01/2012
	 */
	public class NewAdmissionDetailWardWiseRptBO {


	public void getWardDetails(NewAdmissionDetailWardWiseRptVO voObj){
		
		NewAdmissionDetailWardWiseRptDAO.getWardDetails(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("NewAdmissionDetailWardWiseRptBO.getClerkDetails()-->"+strErr);
		}
		
	}

	}




