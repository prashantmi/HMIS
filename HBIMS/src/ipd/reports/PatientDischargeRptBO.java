package ipd.reports;

public class PatientDischargeRptBO {
	
public void getDepartmentDetails(PatientDischargeRptVO voObj){
		
	PatientDischargeRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatientDischargeRptBO.getDepartmentDetails()-->"+strErr);
	}
		
	}
public void getUnitDetails(PatientDischargeRptVO voObj){
		
	PatientDischargeRptDAO.getUnitList(voObj);
	
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatientDischargeRptBO.getUnitDetails()-->"+strErr);
	}
		
	}
public void getWardDetails(PatientDischargeRptVO voObj){
	
	PatientDischargeRptDAO.getWardList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatientDischargeRptBO.getWardDetails()-->"+strErr);
	}
	
		}
public void getConsultDetails(PatientDischargeRptVO voObj){
	
	PatientDischargeRptDAO.getConsultList(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatientDischargeRptBO.getConsultDetails()-->"+strErr);
	}
	
	}
public void getHospitalName(PatientDischargeRptVO voObj){
	
	PatientDischargeRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("PatientDischargeRptBO.getHospitalName()-->"+strErr);
	}
	
}

}
