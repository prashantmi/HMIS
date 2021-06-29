package billing.transactions;

public class BillRePrintTransBO {

	
	// from bala
	
	public void getBillListingDtl(BillRePrintTransVO voObj) {
		
		BillRePrintTransDAO.getBillListingDtl(voObj);
		
		if (voObj.getStrMsgType().equals("1")) {
		//	System.out.println("in bo vo msgtype=1");
			String err = "BillRePrintTransBO.initOffLineDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
			
		
	}
	
	
/*
  public boolean insertBillDtls(BillRePrintTransVO voObj)
  {
	  boolean fretValue = true;
		BillRePrintTransDAO.insertBillDtls(voObj);
		
		if (voObj.getStrMsgType().equals("1")) 
		{
		//	System.out.println("in bo vo msgtype=1");
			String err = "BillRePrintTransBO.initOffLineDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
			fretValue = false;
		}
			
		return fretValue; 
	}
  	*/
   public void getBillDetails(BillRePrintTransVO voObj)
   {
		
		BillRePrintTransDAO.getBillDtl(voObj);
		
		if (voObj.getStrMsgType().equals("1")) {
		//	System.out.println("in bo vo msgtype=1");
			String err = "BillRePrintTransBO.getBillDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
			
		
	}

}
