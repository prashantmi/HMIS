package billing.transactions;

import javax.sql.rowset.WebRowSet;

public class DiscountApprovalTransBO {

	public void setReqValues(DiscountApprovalTransVO voObj) {
		
		DiscountApprovalTransDAO cltDao = null;

		try 
		{
			cltDao = new DiscountApprovalTransDAO();
			cltDao.getTariffDtl(voObj);
		}
		catch (Exception e) 
		{
		 // Set Error Message
			voObj.setStrMsgString("DiscountApprovalTransBO.setReqValues()--> "
					+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		finally
		{	
			cltDao = null;
		}

	}

	public WebRowSet setPopUpVal(DiscountApprovalTransVO voObj)
	{

		WebRowSet web = null;
		DiscountApprovalTransDAO cltDao = null;

		try {

			cltDao = new DiscountApprovalTransDAO();
			web = cltDao.getPopUpDtl(voObj);
			voObj.setStrPopUpResp(web);
			

		} catch (Exception e) {
			
			// Set Error Message
			voObj.setStrMsgString("DiscountApprovalTransBO.setPopUpVal()--> "
					+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		return web;
	}

		
	public void getRsnRmk(DiscountApprovalTransVO voObj)
	{
		DiscountApprovalTransDAO cltDao = null;
		try 
		{
			cltDao = new DiscountApprovalTransDAO();
			cltDao.getRsnRmk(voObj);
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DiscountApprovalTransBO.getRsnRmk()--> "	+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
	}
	
	
	public boolean getInsertDataForFinalAdjustment(DiscountApprovalTransVO voObj)
	{
		// Declare variables
		boolean retVal = false;

		try
		{
			// Call DAO Insert Update Procedure
			retVal = DiscountApprovalTransDAO.getInsertDataForFinalAdjustment(voObj);
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		   // Set Error Message
			voObj.setStrMsgString("DiscountApprovalTransBO.getInsertDataForFinalAdjustment()--> "
							+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		return retVal;
	}
	
	public boolean getInsertDataForServices(DiscountApprovalTransVO voObj)
	{
		// Declare variables
		boolean retVal = false;

		try
		{
				
		 // Call DAO Insert Update Procedure
		    retVal = DiscountApprovalTransDAO.getInsertDataProcForServices(voObj);
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		}
		catch (Exception e) 
		{
		   // Set Error Message
			voObj.setStrMsgString("DiscountApprovalTransBO.getInsertDataForServices()--> "
							+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		return retVal;
	}
}
