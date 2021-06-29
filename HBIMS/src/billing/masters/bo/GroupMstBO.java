package billing.masters.bo;
import billing.masters.dao.GroupMstDAO;
import billing.masters.vo.GroupMstVO;
    

	public class GroupMstBO 
	{
		/**
		 * transfers control to DAO for insertion
		 * 
		 * @param vo
		 * @return int
		 */
		public int InsertRecord(GroupMstVO vo)throws Exception
		{
			boolean retvalue;
			boolean retvalue1;
			boolean retvalue2;
			int count=-1;
			retvalue1 = this.initialAdd(vo);// check for duplicate record
			retvalue2 = this.initialAdd2(vo);
	
			if (retvalue2) 
			{		
				if (retvalue1)
				{
						retvalue = GroupMstDAO.insertQuery(vo);
						if (!retvalue) 
						{
							count=1;
						} 
						else 
						{							
							count=2;
						}
					
				}
				else 
				{
						count=3; 
				}
			}
			else
			{
				if (retvalue1) 
				{
						count=4; 
				}
				else
				{
						count=5; 	
				}
			}
				
			if (vo.getStrMsgType().equals("1"))
			{
					vo.setStrMsgString("GroupMstBO.InsertRecord(vo) -->" + vo.getStrMsgString());
			}
			
			return count;
		}
		/**
		 * transfer control to DAO for initial add query to check duplicate group name
		 * @param vo
		 * @return boolean
		 */
		public boolean initialAdd(GroupMstVO vo)throws Exception// check for dupliacte local group name
		{
			boolean retvalue = false;
			retvalue = GroupMstDAO.initialAddQuery(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
					vo.setStrMsgString("GroupMstBO.initialAdd(vo)) -->" + vo.getStrMsgString());
			}
			return retvalue;

		}
		/**
		 * transfer control to DAO for initial add query to check duplicate global group Id
		 * @param vo
		 * @return boolean
		 */
		public boolean initialAdd2(GroupMstVO vo)throws Exception// check for dupliacte Global Group record
		{
			boolean retvalue = false;
			retvalue = GroupMstDAO.initialAddQuery2(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
					vo.setStrMsgString("GroupMstBO.initialAdd2(vo)) -->" + vo.getStrMsgString());
					
			}
			return retvalue;
		}

		/**
		 * transfers control to DAO for Modification
		 * 
		 * @param vo
		 * @param chk1
		 */

		public void modifyRecord(String chk1, GroupMstVO vo)throws Exception
		{
				GroupMstDAO.modifyQuery(chk1, vo);
				if (vo.getStrMsgType().equals("1")) 
				{
					vo.setStrMsgString("GroupMstBO.modifyRecord(chk1,vo)) -->" + vo.getStrMsgString());
					
				}
		}
		public void hospitalServiceAdd(String superHospCode,GroupMstVO vo )throws Exception
		{
			GroupMstDAO.hospitalServiceAdd(superHospCode,vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("GroupMstBO.add/modifyRecord(chk1,vo)) -->" + vo.getStrMsgString());
				
			}
		}
		
		public void hospitalServiceCheckbox(String superHospCode,GroupMstVO vo,String[] strtemp2 )throws Exception
		{
			GroupMstDAO.hospitalServiceCheckbox(superHospCode,vo,strtemp2);
			if (vo.getStrMsgType().equals("1")) 
			{
				vo.setStrMsgString("GroupMstBO.add/modifyRecord(chk1,vo)) -->" + vo.getStrMsgString());
				
			}
		}
		/**
		 *  transfers control to DAO for update
		 * @param vo
		 * @param chk
		 * @return int
		 */
		public int updateRecord(String chk1, GroupMstVO vo)throws Exception {
			int count=-1;
			boolean retvalue;
			boolean retvalue1;
			retvalue1 = this.initialUpdate(chk1, vo);// chk for modify
			if (!retvalue1) 
			{
				count=0;
			}
			else 
			{
				retvalue = GroupMstDAO.updateQuery(chk1, vo);
				if (!retvalue) 
				{
						count=1;
				} 
				else 
				{
						
						count=2;
				}
			}
			if (vo.getStrMsgType().equals("1"))
			{
					vo.setStrMsgString("GroupMstBO.updateRecord(chk1,vo)) -->" + vo.getStrMsgString());	
			}
			return count;
		}
		/**
		 * transfers control to DAO for initial update query
		 * @param vo
		 * @param chk
		 * @return boolean
		 */
		public boolean initialUpdate(String chk, GroupMstVO vo)throws Exception// check for modification
		{
			boolean retvalue = false;		
			retvalue = GroupMstDAO.initialUpdateQuery(chk, vo);
			if (vo.getStrMsgType().equals("1")) 
			{
					vo.setStrMsgString("GroupMstBO.initialUpdate(chk,vo)) -->" + vo.getStrMsgString());	
			}
			return retvalue;
		}

	}

	

