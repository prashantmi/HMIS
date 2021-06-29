package billing.transactions;

import javax.sql.rowset.WebRowSet;

public class DiscountRecommendationApprovalTransBO {

	public void setReqValues(DiscountRecommendationApprovalTransVO voObj) {
		
		DiscountRecommendationApprovalTransDAO cltDao = null;

		try 
		{
			cltDao = new DiscountRecommendationApprovalTransDAO();
			cltDao.getTariffDtl(voObj);
		}
		catch (Exception e) 
		{
		 // Set Error Message
			voObj.setStrMsgString("DiscountRecommendationApprovalTransBO.setReqValues()--> "
					+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		finally
		{	
			cltDao = null;
		}

	}

public void getDepartmentList(DiscountRecommendationApprovalTransVO voObj){
		
		DiscountRecommendationApprovalTransDAO.getDepartmentList(voObj);
		
		if(voObj.getStrMsgType().equals("1")){
			
			String strMsg = voObj.getStrMsgString();
			
			voObj.setStrMsgString("DiscountRecommendationApprovalTransBO.getDepartmentList()-->"+strMsg);
			
		}
		
		
	}


public void getUnitList(DiscountRecommendationApprovalTransVO voObj){
		
		DiscountRecommendationApprovalTransDAO.getUnitList(voObj);
		
		if(voObj.getStrMsgType().equals("1")){
			
			String strMsg = voObj.getStrMsgString();
			
			voObj.setStrMsgString("DiscountRecommendationApprovalTransBO.getUnitList()-->"+strMsg);
			
		}
		
		
	}
	
	public WebRowSet setPopUpVal(DiscountRecommendationApprovalTransVO voObj)
	{

		WebRowSet web = null;
		DiscountRecommendationApprovalTransDAO cltDao = null;

		try {

			cltDao = new DiscountRecommendationApprovalTransDAO();
			web = cltDao.getPopUpDtl(voObj);
			voObj.setStrPopUpResp(web);
			

		} catch (Exception e) {
			
			// Set Error Message
			voObj.setStrMsgString("DiscountRecommendationApprovalTransBO.setPopUpVal()--> "
					+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		return web;
	}

	public void getRsnRmk(DiscountRecommendationApprovalTransVO voObj)
	{
			
		DiscountRecommendationApprovalTransDAO cltDao = null;
		try 
		{
			
			cltDao = new DiscountRecommendationApprovalTransDAO();
			cltDao.getRsnRmk(voObj);
			
			
			
		} 
		catch (Exception e) 
		{
		
			// Set Error Message
			voObj.setStrMsgString("DiscountRecommendationApprovalTransBO.getRsnRmk()--> "
					+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
	}
	
	
	public boolean getInsertDataForFinalAdjustment(DiscountRecommendationApprovalTransVO voObj)
	{
		// Declare variables
		boolean retVal = false;

		try
		{
			// Call DAO Insert Update Procedure
			retVal = DiscountRecommendationApprovalTransDAO.getInsertDataForFinalAdjustment(voObj);
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		}
		catch (Exception e) 
		{
		   // Set Error Message
			voObj.setStrMsgString("DiscountRecommendationApprovalTransBO.getInsertDataForFinalAdjustment()--> "
							+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		return retVal;
	}
	
	public boolean getInsertDataForServices(DiscountRecommendationApprovalTransVO voObj)
	{
		// Declare variables
		boolean retVal = false;

		try
		{
				
		 // Call DAO Insert Update Procedure
		    retVal = DiscountRecommendationApprovalTransDAO.getInsertDataProcForServices(voObj);
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		}
		catch (Exception e) 
		{
		   // Set Error Message
			voObj.setStrMsgString("DiscountRecommendationApprovalTransBO.getInsertDataForServices()--> "
							+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		return retVal;
	}
}
