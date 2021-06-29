package mms.transactions.bo;

import mms.transactions.dao.ReturnRequestTransDAO;
import mms.transactions.vo.ReturnRequestTransVO;

public class ReturnRequestTransBO 
{
	/**
	 * GetData Method is Used to Populate the Data  for
	 * @param vo
	 */
	public void GetData(ReturnRequestTransVO vo)
	{
		
		ReturnRequestTransDAO.itemCategoryCombo(vo);
		ReturnRequestTransDAO.GetGroupNameCombo(vo);
		ReturnRequestTransDAO.callingFunctionStoreName(vo);
		ReturnRequestTransDAO.ToStoreCombo(vo);
		ReturnRequestTransDAO.GetGrantTypeCombo(vo);
		ReturnRequestTransDAO.GetRecommendByCombo(vo);
		ReturnRequestTransDAO.callingItemCategory(vo);
		
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("ReturnRequestTransBO.GetData() --> "+ vo.getStrMsgString());
		  }
		  
	}
	
	
	/**
	 * INSERT  Method is Used to transfer Value Object to Data Access Object 
	 * @param vo
	 */
	public void INSERT(ReturnRequestTransVO vo)
	{
		ReturnRequestTransDAO.INSERT(vo);
		  if (vo.getStrMsgType().equals("1")) 
		  {
			  vo.setStrMsgString("ReturnRequestTransBO.GetData() --> "+ vo.getStrMsgString());
		  }
		  
	}
	


}
