package ipd.reports;

public class WardRoomBedRptBO {
	
public void getDepartmentDetails(WardRoomBedRptVO voObj){
		
		WardRoomBedRptDAO.getDepartmentList(voObj);
		WardRoomBedRptDAO.getWardTypeList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("WardRoomBedRptBO.getDepartmentDetails()-->"+strErr);
		}
				
			}
			
		public void getUnitDetails(WardRoomBedRptVO voObj){
				
			WardRoomBedRptDAO.getUnitList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("WardRoomBedRptBO.getUnitDetails()-->"+strErr);
			}
				
			}

		public void getWardDetails(WardRoomBedRptVO voObj){
			
			WardRoomBedRptDAO.getWardList(voObj);
	if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("WardRoomBedRptBO.getWardDetails()-->"+strErr);
			}
			
		}
		
		
}
