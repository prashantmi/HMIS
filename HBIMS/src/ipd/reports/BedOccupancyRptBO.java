package ipd.reports;

public class BedOccupancyRptBO {

	public void getDepartmentDetails(BedOccupancyRptVO voObj){
		
		BedOccupancyRptDAO.getDepartmentList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BedOccupancyRptBO.getDepartmentDetails()-->"+strErr);
		}
			
		}

	public void getWardDetails(BedOccupancyRptVO voObj){
		
		BedOccupancyRptDAO.getWardList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BedOccupancyRptBO.getWardDetails()-->"+strErr);
		}
		
	}

	public void getUnitWardDetails(BedOccupancyRptVO voObj){
		
		BedOccupancyRptDAO.getUnitList(voObj);
		BedOccupancyRptDAO.getWardCombo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BedOccupancyRptBO.getUnitDetails()-->"+strErr);
		}
		
	}
		
	
}
