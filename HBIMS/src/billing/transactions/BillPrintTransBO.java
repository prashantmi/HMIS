package billing.transactions;


public class BillPrintTransBO {
	
	public void getOnlineRequestDetails(BillPrintTransVO vo){
		
		BillPrintTransDAO.getOnLineReqDiscount(vo);
		
		if(vo.getStrMsgType().equals("1")){
			
			vo.setStrMsgString("BillPrintTransBO.getOnlineRequestDetails() -> "+vo.getStrMsgString());
			
		}
		
	}
	
}
