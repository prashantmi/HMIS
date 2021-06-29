package ipd.reports;

/**
 * 
 * @author: Vivek Aggarwal
 * @Date: 12-July-2011
 * @Module:	ADT
 */
public class CompleteWardCensusDetailRptBO {

	/**
	 * To get Department Details
	 * 
	 * @param voObj	the CompleteWardCensusDetailRptVO
	 */
	public void getDepartmentDetails(CompleteWardCensusDetailRptVO voObj)
	{
		//CompleteWardCensusDetailRptDAO.getDepartmentList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("CompleteWardCensusDetailRptBO.getDepartmentDetails()-->"+strErr);
		}
	}
	
	public void getWardAll(CompleteWardCensusDetailRptVO voObj){
		
		
		  CompleteWardCensusDetailRptDAO.getWardAll(voObj);
		 if (voObj.getStrMsgType().equals("1")) {
				
				String strErr = voObj.getStrMsgString();
					
				voObj.setStrMsgString("CompleteWardCensusDetailRptBO.getWardAll()-->"+strErr);
			}
				
			}

	/**
	 * To get Unit & Ward Details
	 * 
	 * @param voObj	the CompleteWardCensusDetailRptVO
	 */
	public void getUnitWardDetails(CompleteWardCensusDetailRptVO voObj){
			
		CompleteWardCensusDetailRptDAO.getUnitList(voObj);//Get All Units for a particular dept
		CompleteWardCensusDetailRptDAO.getWardCombo(voObj);//Get All Wards for All Units of a particular dept
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("CompleteWardCensusDetailRptBO.getUnitWardDetails()-->"+strErr);
		}
			
	}

	
	/**
	 * To get All wards for a particular dept and unit 
	 * 
	 * @param voObj	the CompleteWardCensusDetailRptVO
	 */
	public void getWardDetails(CompleteWardCensusDetailRptVO voObj){
		
		CompleteWardCensusDetailRptDAO.getWardList(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("CompleteWardCensusDetailRptBO.getWardDetails()-->"+strErr);
		}
		
	}
	
	/**
	 * To get All wards for a particular dept 
	 * 
	 * @param voObj	the CompleteWardCensusDetailRptVO
	 */
	public void getWardCombo(CompleteWardCensusDetailRptVO voObj){
		
		CompleteWardCensusDetailRptDAO.getWardCombo(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("CompleteWardCensusDetailRptBO.getWardCombo()-->"+strErr);
		}
		
	}
		
}
