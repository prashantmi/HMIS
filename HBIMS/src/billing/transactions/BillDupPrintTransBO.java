package billing.transactions;

public class BillDupPrintTransBO {

	
	
	public void getBillListingDtl(BillDupPrintTransVO voObj) {
		
		BillDupPrintTransDAO.getBillListingDtl(voObj);
		
		if (voObj.getStrMsgType().equals("1")) {
		//	System.out.println("in bo vo msgtype=1");
			String err = "BillDupPrintTransBO.initOffLineDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
			
		
	}
	
	

  public boolean insertBillDtls(BillDupPrintTransVO voObj)
  {
	  boolean fretValue = true;
		BillDupPrintTransDAO.insertBillDtls(voObj);
		
		if (voObj.getStrMsgType().equals("1")) 
		{
		//	System.out.println("in bo vo msgtype=1");
			String err = "BillDupPrintTransBO.initOffLineDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
			fretValue = false;
		}
			
		return fretValue; 
	}
  

  
  	
   public void getBillDetails(BillDupPrintTransVO voObj)
   {
		
		BillDupPrintTransDAO.getBillDtl(voObj);
		BillDupPrintTransDAO.getTariffDetails(voObj);
		BillDupPrintTransDAO.getReceiptType(voObj);
		
		if (voObj.getStrMsgType().equals("1")) {
		//	System.out.println("in bo vo msgtype=1");
			String err = "BillDupPrintTransBO.getBillDetails()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
			
		
	}
   
   public void getReceiptType(BillDupPrintTransVO voObj)
   {
		try
		{
			BillDupPrintTransDAO.getReceiptType(voObj);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			if (voObj.getStrMsgType().equals("1")) 
			{
				String err = "BillDupPrintTransBO.getBillDetails()-->"
							  + voObj.getStrMsgString();

				voObj.setStrMsgString(err);
			}
		}
		
		
		
		
			
		
	}
   
   public void getBillDetailsReg(BillDupPrintTransVO voObj)
   {
		
		BillDupPrintTransDAO.getBillDtlReg(voObj);
		BillDupPrintTransDAO.getTariffDetailsReg(voObj);
		BillDupPrintTransDAO.getReceiptType(voObj);
		
		if (voObj.getStrMsgType().equals("1")) {
		//	System.out.println("in bo vo msgtype=1");
			String err = "BillDupPrintTransBO.getBillDetailsReg()-->"
					+ voObj.getStrMsgString();

			voObj.setStrMsgString(err);
		}
			
		
	}

}
