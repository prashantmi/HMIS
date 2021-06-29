package billing.reports;

public class ConsolidatedExpDtlRptBO {
	
	public void getHospSerDetails(ConsolidatedExpDtlRptVO voObj){
		
		ConsolidatedExpDtlRptDAO.getHospSerList(voObj);
			if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("ConsolidatedExpDtlRptBO.getHospSerDetails()-->"+strErr);
				}
		   }
	public void getHospitalName(ConsolidatedExpDtlRptVO voObj){
		
		ConsolidatedExpDtlRptDAO.getHospitalName(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DailyDischargeRptDAO.getHospitalName()-->"+strErr);
		}
		
	}
	public void getTreatCatDetails(ConsolidatedExpDtlRptVO voObj){
			
		ConsolidatedExpDtlRptDAO.getTreatCatList(voObj);
			if (voObj.getStrMsgType().equals("1")) {
					
					String strErr = voObj.getStrMsgString();
						
					voObj.setStrMsgString("ConsolidatedExpDtlRptBO.getTreatCatDetails()-->"+strErr);
				}
			}
	
	//Added By Tanvi
    public void getGroupName(ConsolidatedExpDtlRptVO voObj){
		
		ConsolidatedExpDtlRptDAO.getGrpNameValues(voObj);
	
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedExpDtlRptBO.getGroupName()-->"+strErr);
		}
			
	}  

    public void getTariffName(ConsolidatedExpDtlRptVO voObj){
	
	ConsolidatedExpDtlRptDAO.getTariffNameValues(voObj);
    if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ConsolidatedExpDtlRptBO.getTariffName()-->"+strErr);
	}
		
   }
}
