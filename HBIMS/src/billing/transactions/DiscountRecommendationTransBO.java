package billing.transactions;

import javax.sql.rowset.WebRowSet;

public class DiscountRecommendationTransBO {

	public void setReqValues(DiscountRecommendationTransVO voObj) {
		
		DiscountRecommendationTransDAO cltDao = null;

		try 
		{
			cltDao = new DiscountRecommendationTransDAO();
			cltDao.getTariffDtl(voObj);
		}
		catch (Exception e) 
		{
		 // Set Error Message
			voObj.setStrMsgString("DiscountRecommendationTransBO.setReqValues()--> "
					+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		finally
		{	
			cltDao = null;
		}

	}

	public WebRowSet setPopUpVal(DiscountRecommendationTransVO voObj)
	{

		WebRowSet web = null;
		DiscountRecommendationTransDAO cltDao = null;

		try {

			cltDao = new DiscountRecommendationTransDAO();
			web = cltDao.getPopUpDtl(voObj);
			voObj.setStrPopUpResp(web);
			

		} catch (Exception e) {
			
			// Set Error Message
			voObj.setStrMsgString("DiscountRecommendationTransBO.setPopUpVal()--> "
					+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		return web;
	}

	public void getRsnRmk(DiscountRecommendationTransVO voObj)
	{
			
		DiscountRecommendationTransDAO cltDao = null;
		try 
		{
			
			cltDao = new DiscountRecommendationTransDAO();
			cltDao.getRsnRmk(voObj);
			
			
			
		} 
		catch (Exception e) 
		{
		
			// Set Error Message
			voObj.setStrMsgString("DiscountRecommendationTransBO.getRsnRmk()--> "
					+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
	}
	
	
	public boolean getInsertDataForFinalAdjustment(DiscountRecommendationTransVO voObj)
	{
		// Declare variables
		boolean retVal = false;

		try
		{
			// Call DAO Insert Update Procedure
			retVal = DiscountRecommendationTransDAO.getInsertDataForFinalAdjustment(voObj);
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		}
		catch (Exception e) 
		{
		   // Set Error Message
			voObj.setStrMsgString("DiscountRecommendationTransBO.getInsertDataForFinalAdjustment()--> "
							+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		return retVal;
	}
	
	public boolean getInsertDataForServices(DiscountRecommendationTransVO voObj)
	{
		// Declare variables
		boolean retVal = false;

		try
		{
				
		 // Call DAO Insert Update Procedure
		    retVal = DiscountRecommendationTransDAO.getInsertDataProcForServices(voObj);
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		}
		catch (Exception e) 
		{
		   // Set Error Message
			voObj.setStrMsgString("DiscountRecommendationTransBO.getInsertDataForServices()--> "
							+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		return retVal;
	}
}
