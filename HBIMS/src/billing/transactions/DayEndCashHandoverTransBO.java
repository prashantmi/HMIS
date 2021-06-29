package billing.transactions;

public class DayEndCashHandoverTransBO {

	public void getDayEndProcess(DayEndCashHandoverTransVO voObj) 
	{
		try 
		{
			DayEndCashHandoverTransDAO.checkDayEndAllowed(voObj);
			DayEndCashHandoverTransDAO.callingDayEndProcess(voObj);
			DayEndCashHandoverTransDAO.getCounterAndUserName(voObj);
			DayEndCashHandoverTransDAO.getDayEndAmt(voObj);
			DayEndCashHandoverTransDAO.getDayEndCreditAmtCollection(voObj);
			DayEndCashHandoverTransDAO.getDayEndInstAmt(voObj);
			DayEndCashHandoverTransDAO.getHandoverTo(voObj);
			
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DayEndCashHandoverTransBO.getDayEndProcess()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}
 
	 
	public void insertAddData(DayEndCashHandoverTransVO voObj) {

		// Create DAO object
		try {

			DayEndCashHandoverTransDAO.insertAddDataProc(voObj);
			
			// Check Error
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}
		} catch (Exception e) {
			// Set Error Message
			voObj.setStrMsgString("DayEndCashHandoverTransBO.insertAddData()--> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}

	/**
	 * 
	 * @param voObj the DayEndCashHandoverTransVO
	 */
	public void getPendingDayEndProcessBilling(DayEndCashHandoverTransVO voObj) {
		// Create DAO object
		try 
		{
			DayEndCashHandoverTransDAO.PendingDayEndProcess(voObj);
			
			// Check Error
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			// Set Error Message
			voObj.setStrMsgString("DayEndCashHandoverTransBO.getDayEndProcess()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
		
	}
	
	public void getDayEndAmountAjax(DayEndCashHandoverTransVO voObj) 
	{
		// Create DAO object
		try 
		{
			DayEndCashHandoverTransDAO.getDayEndAmt(voObj);
		
			// Check Error
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			// Set Error Message
			voObj.setStrMsgString("DayEndCashHandoverTransBO.getDayEndAmountAjax()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}
	public void getDayEndCreditColAjax(DayEndCashHandoverTransVO voObj) 
	{
		// Create DAO object
		try 
		{
			DayEndCashHandoverTransDAO.getDayEndCreditAmtCollection(voObj);
		
			// Check Error
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			// Set Error Message
			voObj.setStrMsgString("DayEndCashHandoverTransBO.getDayEndCreditColAjax()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}
	public void getDayEndInstAmtAjax(DayEndCashHandoverTransVO voObj) 
	{
		// Create DAO object
		try 
		{
			DayEndCashHandoverTransDAO.getDayEndInstAmt(voObj);
		
			// Check Error
			if (voObj.getStrMsgType().equals("1")) 
			{
				throw new Exception(voObj.getStrMsgString());
			}
		} 
		catch (Exception e) 
		{
			// Set Error Message
			voObj.setStrMsgString("DayEndCashHandoverTransBO.getDayEndInstAmtAjax()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		}
	}
}
