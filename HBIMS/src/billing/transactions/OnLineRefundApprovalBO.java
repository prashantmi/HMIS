package billing.transactions;

public class OnLineRefundApprovalBO 
{
	public void getOnLineRefundApprovalDtl(OnLineRefundApprovalVO voObj) 
	{
			
			OnLineRefundApprovalDAO.getOnLineRefundApprovalDtlOne(voObj);
			OnLineRefundApprovalDAO.getTodayApprovalDtl(voObj);
			OnLineRefundApprovalDAO.getRefundBy(voObj);
			
			if (voObj.getStrMsgType().equals("1")) 
			{
			
				String err = "OnLineRefundApprovalBO.getOnLineRefundApprovalDtl()-->"
						+ voObj.getStrMsgString();
	
				voObj.setStrMsgString(err);
			}
				
			
		}
	
	public void getRefundwithCR(OnLineRefundApprovalVO voObj) 
	{
			
			OnLineRefundApprovalDAO.getOnLineRefundApprovalDtlTwo(voObj);
			
			if (voObj.getStrMsgType().equals("1")) 
			{
			
				String err = "OnLineRefundApprovalBO.getTariffDetails()-->"
						+ voObj.getStrMsgString();
	
				voObj.setStrMsgString(err);
			}
				
			
		}
	
	public void getTariffDetails(OnLineRefundApprovalVO voObj) 
	{
			
			OnLineRefundApprovalDAO.getTariffDetails(voObj);
			
			if (voObj.getStrMsgType().equals("1")) 
			{
			
				String err = "OnLineRefundApprovalBO.getTariffDetails()-->"
						+ voObj.getStrMsgString();
	
				voObj.setStrMsgString(err);
			}
				
			
 	}
	/**
	 * This is used to bring prevoius doc detail
	 * @param vo
	 */
	public boolean save(OnLineRefundApprovalVO vo) 
	{
		boolean retVal = false;

		try 
        {
		  retVal = OnLineRefundApprovalDAO.save(vo);
		  if (vo.getStrMsgType().equals("1")) 
		  {
				vo.setStrMsgString("OnLineRefundApprovalBO.save() --> "
						+ vo.getStrMsgString());
		  }
		        	 
	    } 
	    catch (Exception e) 
	    {
		// Set Error Msg
		vo.setStrMsgString("OnLineRefundApprovalBO.save()--> "+vo.getStrMsgString());
		vo.setStrMsgType("1");
	    }
     return retVal;
	}

}
