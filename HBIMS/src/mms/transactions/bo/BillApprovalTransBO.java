package mms.transactions.bo;

import mms.transactions.dao.BillApprovalTransDAO;
import mms.transactions.vo.BillApprovalTransVO;


/**
 * 
 * @author dell
 *
 */

public class BillApprovalTransBO {
	
	
	/**
	 * This method is used for GROUP LIST
	 * @param voObj
	 */
	public void getPODetails(BillApprovalTransVO voObj){

		BillApprovalTransDAO.getPODetails(voObj);
		
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BillApprovalTransBO.getPODetails()-->"+strErr);
		}
		
	}
	/**
	 * This method is used for GROUP LIST
	 * @param voObj
	 */
	public void getPeneltyDtl(BillApprovalTransVO voObj){

		BillApprovalTransDAO.getPeneltyDtls(voObj);
		
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BillApprovalTransBO.getPeneltyDtl()-->"+strErr);
		}
		
	}
	
	/**
	 * This method is used for GROUP LIST
	 * @param voObj
	 */
	public void getPOScheduleDetails(BillApprovalTransVO voObj){

		BillApprovalTransDAO.getPOScheduleDetails(voObj);  //Commented by Amit To Remove Other Delivery Location Schedule (Uncommented by Ajay Deshwal) 
	//	BillApprovalTransDAO.getPOScheduleInfoDetails(voObj);   Commented by Ajay Deshwal to show the details
		
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BillApprovalTransBO.getPODetails()-->"+strErr);
		}
		
	}
	/**
	 * This method is used for GROUP LIST
	 * @param voObj
	 */
	public void getPODetailsSearchList(BillApprovalTransVO voObj){

		BillApprovalTransDAO.getPODetailsSearchList(voObj);
		
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BillApprovalTransBO.getPODetails()-->"+strErr);
		}
		
	}

	/**
	 * This method is used for GROUP LIST
	 * @param voObj
	 */
	public void getScheduleItemDtls(BillApprovalTransVO voObj){

		BillApprovalTransDAO.getPOScheduleItemDetails(voObj);
		
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BillApprovalTransBO.getScheduleItemDetails()-->"+strErr);
		}
		
	}
	
	/**
	 * This method is used for insert the details.
	 * @param voObj
	 */
	public void insert(BillApprovalTransVO voObj){
	
		BillApprovalTransDAO.insert(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BillApprovalTransBO.insert()-->"+strErr);
		}
	}
	
	public void getWaiveOffApprovedBy(BillApprovalTransVO voObj){
		
		BillApprovalTransDAO.getWaiveOffApprovedBy(voObj);
		if (voObj.getStrMsgType().equals("1")) {
			
			String strErr = voObj.getStrMsgString();
				
			voObj.setStrMsgString("BillApprovalTransBO.getWaiveOffApprovedBy()-->"+strErr);
		}
	}
}
