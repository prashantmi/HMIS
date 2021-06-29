package mms.transactions.bo;

import mms.transactions.dao.IndentTransADDDAO;
import mms.transactions.dao.IndentTransADDDAO;
import mms.transactions.vo.IndentTransADDVO;
import mms.transactions.vo.IndentTransADDVO;

/**
 * @author Amit Kumar
 * Date of Creation : 31/3/2009
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : Store
 */
public class IndentTransADDBO 
{
	/**
	 * GetData Method is Used to Populate the Data  for
	 * @param vo
	 */
	public void GetData(IndentTransADDVO vo)
	{
		IndentTransADDDAO.GetData(vo);
		IndentTransADDDAO.getAvalBudgetDetails(vo);
		IndentTransADDDAO.ToStoreCombo(vo);
		IndentTransADDDAO.IndentPeriodCombo(vo);
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("IndentTransADDBO.GetData() --> "+ vo.getStrMsgString());
		  }
		  
	}
	
	/**
	 * Calling Function Method is Used to Populate the Data  for add page
	 * here we get Store Name & Indent Type & Item Category
	 * @param vo
	 */
	public void CallFunction(IndentTransADDVO vo)
	{
 
		  IndentTransADDDAO.callingFunctionStoreName(vo);
		  IndentTransADDDAO.callingFunctionIndentType(vo);
		  IndentTransADDDAO.callingItemCategory(vo);
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("IndentTransADDBO.CallFunction() --> "+ vo.getStrMsgString());
		  }
		  
	}
	
	/**
	 * INSERT  Method is Used to transfer Value Object to Data Access Object 
	 * @param vo
	 */
	public void INSERT(IndentTransADDVO vo)
	{
		IndentTransADDDAO.INSERT(vo);
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("IndentTransADDBO.INSERT() --> "+ vo.getStrMsgString());
		  }
		  
	}
	
	
	public void PLACEREGULARINDENT(IndentTransADDVO vo)
	{
		IndentTransADDDAO.PLACEREGULARINDENT(vo);
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("IndentTransADDBO.PLACEREGULARINDENT() --> "+ vo.getStrMsgString());
		  }
		  
	}
	
	

}
