package billing.masters.bo;
/* Global Group Master BO
 * author: Debashis Sardar
 * Created on : 26-Aug-2011
 */
import billing.masters.dao.GlobalGroupMstDAO;
import billing.masters.vo.GlobalGroupMstVO;
import hisglobal.exceptions.HisException;

public class GlobalGroupMstBO {

	public int InsertRecord(GlobalGroupMstVO vo) throws Exception{
		
		boolean retvalue;
		boolean retvalue1;
		int count=-1;
		
		
		String strmsgText = null;
		
			retvalue1 = this.initialAdd(vo);// check for duplicate record
			if (!retvalue1) {
				
				
				count=0;
			} else

			{
				retvalue = GlobalGroupMstDAO.insertQuery(vo);
				 
				if (!retvalue) {
					
					
					count=1;
				} else {
					
					
					count=2;
				}
			}
		
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("GlobalGroupMstBO.InsertRecord(vo) -->" + vo.getStrMsgString());
				
			}
			

		
		return count;
	}

	public boolean initialAdd(GlobalGroupMstVO vo)throws Exception// check for duplicate record
	{
		boolean retvalue = false;
		
		
			retvalue = GlobalGroupMstDAO.initialAddQuery(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("GlobalGroupMstBO.initialAdd(vo) -->" + vo.getStrMsgString());
				
			}

			

		
		return retvalue;

	}



	public void modifyRecord(String chk1, GlobalGroupMstVO vo)throws Exception {
		
	
		
			GlobalGroupMstDAO.modifyQuery(chk1, vo);
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("GlobalGroupMstBO.modifyRecord(chk1,vo) -->" + vo.getStrMsgString());
				
			}

			

		}
	

	public int updateRecord(String chk1, GlobalGroupMstVO vo)throws Exception 
	{
		int count=-1;
		boolean retvalue;
		boolean retvalue1;
		retvalue1 = this.initialUpdate(chk1, vo);// chk for duplicate

		if (!retvalue1) 
		{			
			count=0;
		} 
		else 
		{
			retvalue = GlobalGroupMstDAO.updateQuery(chk1, vo);
			if (!retvalue) 
				count=1;
			else 
				count=2;
		}
		if (vo.getStrMsgType().equals("1")) 
		{
			vo.setStrMsgString("GlobalGroupMstBO.updateRecord(chk1,vo) -->" + vo.getStrMsgString());
		}
		return count;
	}

	public boolean initialUpdate(String chk, GlobalGroupMstVO vo)throws Exception// check for duplicate
	{
		boolean retvalue = false;
		String strmsgText = null;
		
		retvalue = GlobalGroupMstDAO.initialUpdateQuery(chk, vo);
	
		if (vo.getStrMsgType().equals("1")) 
		{
			vo.setStrMsgString("GlobalGroupMstBO.initialUpdate(chk1,vo) -->" + vo.getStrMsgString());
		}

		return retvalue;
	}
}