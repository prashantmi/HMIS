
package mms.transactions.bo;

import mms.transactions.dao.IndentTransDAO;
import mms.transactions.vo.IndentTransVO;

public class IndentTransBO 
{
	/**
	 * INSERT  Method is Used to transfer Value Object to Data Access Object 
	 * @param vo
	 */
	public void CANCEL(IndentTransVO vo)
	{
		IndentTransDAO.CANCEL(vo);
		  if (vo.getStrMsgType().equals("1")) 
		  {
			vo.setStrMsgString("IndentTransBO.CANCEL() --> "+ vo.getStrMsgString());
		  }
		  
	}

}
