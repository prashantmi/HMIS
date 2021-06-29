package billing.transactions;

public class DayEndTransBO {

	public void getDayEndProcess(DayEndTransVO voObj) 
	{
		try 
		{
			DayEndTransDAO.checkDayEndAllowed(voObj);
			DayEndTransDAO.callingDayEndProcess(voObj);
			DayEndTransDAO.getCounterAndUserName(voObj);
			DayEndTransDAO.getDayEndAmt(voObj);
			DayEndTransDAO.getDayEndCreditAmtCollection(voObj);
			DayEndTransDAO.getDayEndInstAmt(voObj);
			
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DayEndTransBO.getDayEndProcess()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}
 
	 
	public void insertAddData(DayEndTransVO voObj) {

		// Create DAO object
		try {

			DayEndTransDAO.insertAddDataProc(voObj);
			
			// Check Error
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
		} catch (Exception e) {
			// Set Error Message
			voObj.setStrMsgString("DayEndTransBO.insertAddData()--> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	/**
	 * 
	 * @param voObj the DayEndTransVO
	 */
	public void getPendingDayEndProcessBilling(DayEndTransVO voObj) {
		// Create DAO object
		try 
		{
			DayEndTransDAO.PendingDayEndProcess(voObj);
			
			// Check Error
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			// Set Error Message
			voObj.setStrMsgString("DayEndTransBO.getDayEndProcess()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
		
	}
	
	public void getDayEndAmountAjax(DayEndTransVO voObj) 
	{
		// Create DAO object
		try 
		{
			DayEndTransDAO.getDayEndAmt(voObj);
		
			// Check Error
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			// Set Error Message
			voObj.setStrMsgString("DayEndTransBO.getDayEndAmountAjax()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}
	public void getDayEndCreditColAjax(DayEndTransVO voObj) 
	{
		// Create DAO object
		try 
		{
			DayEndTransDAO.getDayEndCreditAmtCollection(voObj);
		
			// Check Error
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			// Set Error Message
			voObj.setStrMsgString("DayEndTransBO.getDayEndCreditColAjax()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}
	public void getDayEndInstAmtAjax(DayEndTransVO voObj) 
	{
		// Create DAO object
		try 
		{
			DayEndTransDAO.getDayEndInstAmt(voObj);
		
			// Check Error
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			// Set Error Message
			voObj.setStrMsgString("DayEndTransBO.getDayEndInstAmtAjax()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}
}
