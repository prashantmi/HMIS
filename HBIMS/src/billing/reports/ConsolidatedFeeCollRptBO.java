package billing.reports;

public class ConsolidatedFeeCollRptBO {
public void getHospSerDetails(ConsolidatedFeeCollRptVO voObj){
		
		ConsolidatedFeeCollRptDAO.getHospSerList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedFeeCollRptBO.getHospSerDetails()-->"+strErr);
		}
   }
	public void getCounterDetails(ConsolidatedFeeCollRptVO voObj){
		
		ConsolidatedFeeCollRptDAO.getCounterList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedFeeCollRptBO.getCounterDetails()-->"+strErr);
		}
			
		}
	
public void getHospitalName(ConsolidatedFeeCollRptVO voObj){
		
	ConsolidatedFeeCollRptDAO.getHospitalName(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedFeeCollRptBO.getHospitalName()-->"+strErr);
		}
		
	}
	public void getClerkDetails(ConsolidatedFeeCollRptVO voObj){
		
		ConsolidatedFeeCollRptDAO.getClerkList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedFeeCollRptBO.getClerkDetails()-->"+strErr);
		}
			
		}
	//Added By Tanvi
   public void getGroupName(ConsolidatedFeeCollRptVO voObj){
		
		ConsolidatedFeeCollRptDAO.getGrpNameValues(voObj);
	
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedFeeCollRptBO.getGroupName()-->"+strErr);
		}
			
	}
   
   public void getTariffName(ConsolidatedFeeCollRptVO voObj){
		
		ConsolidatedFeeCollRptDAO.getTariffNameValues(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedFeeCollRptBO.getTariffName()-->"+strErr);
		}
			
	}
	
	public void getTreatCatDetails(ConsolidatedFeeCollRptVO voObj){
		
		ConsolidatedFeeCollRptDAO.getTreatCatList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedFeeCollRptBO.getTreatCatDetails()-->"+strErr);
		}
			
		}
	
public void getDeptName(ConsolidatedFeeCollRptVO voObj){
		
		ConsolidatedFeeCollRptDAO.getDeptList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedFeeCollRptBO.getDeptName()-->"+strErr);
		}
			
		}
}
