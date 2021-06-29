package billing.masters.bo;
/* Global Sub Group Master BO
 * author: Debashis Sardar
 * Created on : 26-Aug-2011
 */
import javax.sql.rowset.WebRowSet;

import billing.masters.dao.GlobalSubGroupMstDAO;
import billing.masters.dao.GlobalTariffMstDAO;
import billing.masters.vo.GlobalSubGroupMstVO;
import billing.masters.vo.GlobalTariffMstVO;
import hisglobal.exceptions.HisException;

public class GlobalSubGroupMstBO {

	public int InsertRecord(GlobalSubGroupMstVO vo) throws Exception{
		
		boolean retvalue;
		boolean retvalue1;
		int count=-1;
		
		
		String strmsgText = null;
		
			retvalue1 = this.initialAdd(vo);// check for duplicate record
			if (!retvalue1) {
				
				
				count=0;
			} else

			{
				retvalue = GlobalSubGroupMstDAO.insertQuery(vo);
				 
				if (!retvalue) {
					
					
					count=1;
				} else {
					
					
					count=2;
				}
			}
		
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("GlobalSubGroupMstBO.InsertRecord(vo) -->" + vo.getStrMsgString());
				
			}
			

		
		return count;
	}

	public boolean initialAdd(GlobalSubGroupMstVO vo)throws Exception// check for duplicate record
	{
		boolean retvalue = false;
		
		
			retvalue = GlobalSubGroupMstDAO.initialAddQuery(vo);

			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("GlobalSubGroupMstBO.initialAdd(vo) -->" + vo.getStrMsgString());
				
			}

			

		
		return retvalue;

	}

       public static WebRowSet initAddQuery(GlobalSubGroupMstVO vo) throws Exception {

		
		
		WebRowSet wb = null;
		
		wb=GlobalSubGroupMstDAO.initAddQuery(vo);
		
		if (vo.getStrMsgType().equals("1")) {
			vo.setStrMsgString("GlobalSubGroupMstBO.initAddQuery() --> " + vo.getStrMsgString());
			
		}

		
		return wb;
	}

	public void modifyRecord(String chk1, GlobalSubGroupMstVO vo)throws Exception {
		
	
		
			GlobalSubGroupMstDAO.modifyQuery(chk1, vo);
			if (vo.getStrMsgType().equals("1")) {
				vo.setStrMsgString("GlobalSubGroupMstBO.modifyRecord(chk1,vo) -->" + vo.getStrMsgString());
				
			}

			

		}
	

	public int updateRecord(String chk1, GlobalSubGroupMstVO vo)throws Exception 
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
			retvalue = GlobalSubGroupMstDAO.updateQuery(chk1, vo);
			if (!retvalue) 
				count=1;
			else 
				count=2;
		}
		if (vo.getStrMsgType().equals("1")) 
		{
			vo.setStrMsgString("GlobalSubGroupMstBO.updateRecord(chk1,vo) -->" + vo.getStrMsgString());
		}
		return count;
	}

	public boolean initialUpdate(String chk, GlobalSubGroupMstVO vo)throws Exception// check for duplicate
	{
		boolean retvalue = false;
		String strmsgText = null;
		
		retvalue = GlobalSubGroupMstDAO.initialUpdateQuery(chk, vo);
	
		if (vo.getStrMsgType().equals("1")) 
		{
			vo.setStrMsgString("GlobalSubGroupMstBO.initialUpdate(chk1,vo) -->" + vo.getStrMsgString());
		}

		return retvalue;
	}
}