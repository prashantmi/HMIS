package billing.reports;

import ipd.reports.DailyDischargeRptDAO;
import ipd.reports.DailyDischargeRptVO;

public class ConsolidatedDiscountDtlRptBO {
	
public void getHospSerDetails(ConsolidatedDiscountDtlRptVO voObj){
		
	ConsolidatedDiscountDtlRptDAO.getHospSerList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ConsolidatedDiscountDtlRptBO.getHospSerDetails()-->"+strErr);
			}
	   }
public void getHospitalName(ConsolidatedDiscountDtlRptVO voObj){
	
	ConsolidatedDiscountDtlRptDAO.getHospitalName(voObj);
if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("DailyDischargeRptDAO.getHospitalName()-->"+strErr);
	}
	
}
	public void getTreatCatDetails(ConsolidatedDiscountDtlRptVO voObj){
		
		ConsolidatedDiscountDtlRptDAO.getTreatCatList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("ConsolidatedDiscountDtlRptBO.getTreatCatDetails()-->"+strErr);
			}
		}
	

//Added By Tanvi
    public void getGroupName(ConsolidatedDiscountDtlRptVO voObj){
		
		ConsolidatedDiscountDtlRptDAO.getGrpNameValues(voObj);
	
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("ConsolidatedDiscountDtlRptBO.getGroupName()-->"+strErr);
		}
			
	}  

    public void getTariffName(ConsolidatedDiscountDtlRptVO voObj){
	
	ConsolidatedDiscountDtlRptDAO.getTariffNameValues(voObj);
    if (voObj.getStrMsgType().equals("1")) {
		
		String strErr = voObj.getStrMsgString();
			
		voObj.setStrMsgString("ConsolidatedDiscountDtlRptBO.getTariffName()-->"+strErr);
	}
		
}
}
