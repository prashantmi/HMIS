package billing.transactions;
/*
 * Ipd Bill Re-Open Transaction BO
 * 
 * author : Debashis Sardar
 * 
 * date: 10-Dec-2011
 * 
 */
public class IpdBillReOpenTransBO {

	public void getCrNo(IpdBillReOpenTransVO voObj) {

		try {
				IpdBillReOpenTransDAO.getCrNo(voObj);
		
			if (voObj.getStrMsgType().equals("1"))
			{
				voObj.setStrMsgString("IpdBillReOpenTransBO."
						+ "getCrNo() --> " + voObj.getStrMsgString());
			}

		} catch (Exception e) 
		{
			voObj.setStrMsgString("IpdBillReOpenTransBO.getCrNo() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		}

	}
	
public void getBillDetails(IpdBillReOpenTransVO voObj) {

		try {
			
				IpdBillReOpenTransDAO.getBillDetails(voObj);
		
			if (voObj.getStrMsgType().equals("1"))
			{
				voObj.setStrMsgString("IpdBillReOpenTransBO."
						+ "getBillDetails() --> " + voObj.getStrMsgString());
			}

		} catch (Exception e) 
		{
			voObj.setStrMsgString("IpdBillReOpenTransBO.getBillDetails() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		}

	}
public void saveData(IpdBillReOpenTransVO voObj) {


	try {
		
		IpdBillReOpenTransDAO.saveData(voObj);
		//IpdBillReOpenTransDAO.updateForPoorFreePatCategory(voObj);
	
		if (voObj.getStrMsgType().equals("1"))
		{
			voObj.setStrMsgString("IpdBillReOpenTransBO."
					+ "saveData() --> " + voObj.getStrMsgString());
		}

	} catch (Exception e) 
	{
		voObj.setStrMsgString("IpdBillReOpenTransBO.saveData() --> "
						+ e.getMessage());
		voObj.setStrMsgType("1");
	}

}
}
