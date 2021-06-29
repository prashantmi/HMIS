package billing.transactions;

import javax.sql.rowset.WebRowSet;

public class CreditBillApprovalTransBO {

	public void getWaivedBy(CreditBillApprovalTransVO voObj)
	{
		CreditBillApprovalTransDAO cltDao = null;
		try 
		{
			cltDao = new CreditBillApprovalTransDAO();
			cltDao.getWaivedBy(voObj);
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("CreditBillApprovalTransBO.getRsnRmk()--> "	+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
	}
	
public void setReqValues(CreditBillApprovalTransVO voObj) 
{
		
		CreditBillApprovalTransDAO cltDao = null;

		try 
		{
			cltDao = new CreditBillApprovalTransDAO();
			cltDao.getTariffDtl(voObj);
		}
		catch (Exception e) 
		{
		 // Set Error Message
			voObj.setStrMsgString("CreditBillApprovalTransBO.setReqValues()--> "
					+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		finally
		{	
			cltDao = null;
		}

	}

public boolean getInsertDataForServices(CreditBillApprovalTransVO voObj)
{
	boolean retVal = false;

	try
	{		
	    retVal = CreditBillApprovalTransDAO.getInsertDataProcForServices(voObj);
		if (voObj.getStrMsgType().equals("1")) 
		{
			throw new Exception(voObj.getStrMsgString());
		}
	}
	catch (Exception e) 
	{
	   voObj.setStrMsgString("CreditBillApprovalTransBO.getInsertDataForServices()--> "+ voObj.getStrMsgString());
	   voObj.setStrMsgType("1");
	}
	return retVal;
}

public CreditBillApprovalTransVO getClientNo(CreditBillApprovalTransVO voObj)
{
	CreditBillApprovalTransDAO cltDao = null;
	CreditBillApprovalTransVO tempVo=new CreditBillApprovalTransVO();
	
	try 
	{
		cltDao = new CreditBillApprovalTransDAO();
		tempVo=cltDao.getClientNo(voObj);		
	} 
	catch (Exception e) 
	{
		voObj.setStrMsgString("CreditBillApprovalTransBO.getClientNo()--> "	+ voObj.getStrMsgString());
		voObj.setStrMsgType("1");
	}
	
	return tempVo;
}

public void getRelationWS(CreditBillApprovalTransVO voObj)
{
	
	CreditBillApprovalTransDAO cltDao = null;
	try 
	{
		cltDao = new CreditBillApprovalTransDAO();
		cltDao.getRelationWS(voObj);
	} 
	catch (Exception e) 
	{
		voObj.setStrMsgString("CreditBillApprovalTransBO.getRelationWS()--> "	+ voObj.getStrMsgString());
		voObj.setStrMsgType("1");
	}
}
	/////////////////////////////////////old data///////////////////////////////
	

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
		   // Set Error Message
			voObj.setStrMsgString("DiscountApprovalTransBO.getInsertDataForFinalAdjustment()--> "
							+ voObj.getStrMsgString());
			voObj.setStrMsgType("1");
		}
		return retVal;
	}
	
	
}
