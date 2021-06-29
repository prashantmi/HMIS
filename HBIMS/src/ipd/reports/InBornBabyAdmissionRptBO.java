package ipd.reports;

public class InBornBabyAdmissionRptBO {


	/*
	 * Function to get the Hospital Combo List
	 * @param: ListAdmittedPatientRptVO voObj
	 */
	public void getHospitalName(InBornBabyAdmissionRptVO voObj){
		
		InBornBabyAdmissionRptDAO.getHospitalName(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("AdmittedPatientRptBO.getHospitalName()-->"+strErr);
		}
		
	}
		
}
