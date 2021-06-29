package ipd.reports;

import billing.reports.ConsolidatedFeeCollRptDAO;
import billing.reports.ConsolidatedFeeCollRptVO;

public class DailyWardCensusSumRptBO {

	public void getDepartmentDetails(DailyWardCensusSumRptVO voObj){
		
		DailyWardCensusSumRptDAO.getDepartmentList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DailyWardCensusSumRptBO.getDepartmentDetails()-->"+strErr);
		}
			
		}
	public void getWardAll(DailyWardCensusSumRptVO voObj){
		
		
		DailyWardCensusSumRptDAO.getWardAll(voObj);
	     if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DailyWardCensusSumRptBO.getWardAll()-->"+strErr);
		}
			
		}	
	
	
public void getWardType(DailyWardCensusSumRptVO voObj){
		
		
		DailyWardCensusSumRptDAO.getWardType(voObj);
	     if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DailyWardCensusSumRptBO.getWardAll()-->"+strErr);
		}
			
		}	

	public void getUnitWardDetails(DailyWardCensusSumRptVO voObj){
			
		DailyWardCensusSumRptDAO.getUnitList(voObj);
		DailyWardCensusSumRptDAO.getWardCombo(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DailyWardCensusSumRptBO.getUnitDetails()-->"+strErr);
		}
		
			
		}
	public void getWardCombo(DailyWardCensusSumRptVO voObj){
		
		
		DailyWardCensusSumRptDAO.getWardCombo(voObj);
	if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DailyWardCensusSumRptBO.getWardCombo()-->"+strErr);
		}
			
		}
	public void getWardDetails(DailyWardCensusSumRptVO voObj){
		
		DailyWardCensusSumRptDAO.getWardList(voObj);
if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("DailyWardCensusSumRptBO.getWardDetails()-->"+strErr);
		}
		
	}
	public void getYearName(DailyWardCensusSumRptVO voObj)
	{		
		DailyWardCensusSumRptDAO.getYearName(voObj);
		if (voObj.getStrMsgType().equals("1")) 
		{
			String strErr = voObj.getStrMsgString();				
			voObj.setStrMsgString("ConsolidatedFeeCollRptBO.getYearName()-->"+strErr);
		}			
	}
	
}
