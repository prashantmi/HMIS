package billing.masters.dao;

/* Group Master DAO
 * author: Debashis Sardar
 * Created on : 09-Sep-2011
 */

	

	import hisglobal.transactionmgnt.HisDAO;
	import billing.masters.vo.GroupMstVO;
	import javax.sql.rowset.WebRowSet;

	public class GroupMstDAO 
	{
		/**
		 * retrieves insert Query and executes it.
		 * @param vo
		 * @return true - if Record Inserted Successfully. <br>
		 *         false - if Record Not Inserted Successfully.
		 */
		public static boolean insertQuery(GroupMstVO vo) throws Exception 
		{
			
			HisDAO dao = null;
			int qryIndex;
			boolean retvalue = true;
			String query = new String();
			String query1=new String();
			try
			{
				dao = new HisDAO("Billing", "GroupMstDAO");
				query = billing.qryHandler_billing.getQuery(1, "insert.grp.0");
				qryIndex = dao.setQuery(query);
			    dao.setQryValue(qryIndex, 1, vo.getStrhospitalcode());
				dao.setQryValue(qryIndex, 2, vo.getStrgroupId());
				dao.setQryValue(qryIndex, 3, vo.getStrhospitalcode());
				dao.setQryValue(qryIndex, 4, vo.getStrgroupId());
				dao.setQryValue(qryIndex, 5, vo.getStrgroupName());
				dao.setQryValue(qryIndex, 6, vo.getStrisPackage());
				dao.setQryValue(qryIndex, 7, vo.getStrEffectiveFrom());
				dao.setQryValue(qryIndex, 8, vo.getStrseatId());
				dao.setQryValue(qryIndex, 9, vo.getStrremark());
				dao.setQryValue(qryIndex, 10,vo.getStrisVisible());
				dao.setQryValue(qryIndex,11,vo.getStrconsumeableCharge());
				dao.execute(qryIndex, 0);
				
				query1 = billing.qryHandler_billing.getQuery(1, "insert.grp.1");
				int temp[]=vo.gethospitalServiceValues();
				qryIndex = dao.setQuery(query1);
				for (int i = 0; i < temp.length; i++)
				{
					dao.setQryValue(qryIndex, 1,""+temp[i]);
					dao.setQryValue(qryIndex, 2, vo.getStrgroupId());
					dao.setQryValue(qryIndex, 3, vo.getStrhospitalcode());
					dao.setQryValue(qryIndex, 4, vo.getStrhospitalcode());
					dao.setQryValue(qryIndex, 5, vo.getStrgroupId());
					dao.setQryValue(qryIndex, 6, vo.getStrEffectiveFrom());
					dao.setQryValue(qryIndex, 7, vo.getStrseatId());
					dao.setQryValue(qryIndex, 8, vo.getStrseatId());
					dao.setQryValue(qryIndex, 9, vo.getStrEffectiveFrom());
					dao.setQryValue(qryIndex, 10, vo.getStrremark());
					dao.execute(qryIndex, 0);
				}
				synchronized (dao) 
				{
					dao.fire();
					retvalue = true;

				}

			} catch (Exception e) 
			{
				retvalue = false;
				e.printStackTrace();
				vo.setStrMsgString("billing.GroupMstDAO.insertQuery"+ e.getMessage());
				vo.setStrMsgType("1");
			}
			finally 
			{
				dao.free();
				dao = null;
			}
			return retvalue;

		}
		/**
		 * retrieves and executes Modify Query
		 * 
		 * @param vo
		 * @param Chk1
		 */
		public static void modifyQuery(String chk1, GroupMstVO vo) throws Exception
		{		
			HisDAO dao = null;
			String strtemp[] = null;
			String strtemp2[] = null;
			
			int qryIndex;
			String query = new String();

			strtemp = chk1.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace("$", "#").split("#");
			strtemp[1] = strtemp2[0];

			
			try 
			{
				dao = new HisDAO("Billing", "GroupMstDAO");
				query = billing.qryHandler_billing.getQuery(1, "select.grp.1");		
				qryIndex = dao.setQuery(query);			
				dao.setQryValue(qryIndex, 1, strtemp[0]);
				dao.setQryValue(qryIndex, 2, strtemp[1]);
				WebRowSet web = dao.executeQry(qryIndex);
				while (web.next()) 
				{
					vo.setStrgroupName(web.getString(1));
					vo.setStrisPackage(web.getString(2));
					vo.setStrEffectiveFrom(web.getString(3));
					vo.setStrremark(web.getString(4));
					vo.setStrisVisible(web.getString(5));
					vo.setStrseatId(web.getString(6));
					vo.setStrisValid(web.getString(7));
					vo.setStrconsumeableCharge(web.getString(8));
					//vo.setStrhospitalcode(web.getString(9));// Add value of One more web
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				vo.setStrMsgString("billing.GroupMstDAO.modifyQuery"+ e.getMessage());
				vo.setStrMsgType("1");

			} 
			finally 
			{
				dao.free();
				dao = null;
			}

		}
		/**
		 * retrieves and executes update Query
		 * @param Chk
		 * @param vo
		 * @return true - if Record Updated Successfully. <br>
		 *         false - if Record Not Updated Successfully.
		 */
		public static boolean updateQuery(String chk, GroupMstVO vo) throws Exception 
		{
			boolean retvalue = false;
			HisDAO dao = null;
			String strtemp[] = null;
			String strtemp2[] = null;
			int qryIndex;
			String query = new String();
			try 
			{
				dao = new HisDAO("Billing", "GroupMstDAO");
				query = billing.qryHandler_billing.getQuery(1, "update.grp.1");
				qryIndex = dao.setQuery(query);
				strtemp = chk.replace('@', '#').split("#");
				strtemp2 = strtemp[1].replace("$", "#").split("#");
				strtemp[1] = strtemp2[0];
				dao.setQryValue(qryIndex, 1, vo.getStrgroupName());
				dao.setQryValue(qryIndex, 2, vo.getStrisPackage());
				dao.setQryValue(qryIndex, 3, vo.getStrseatId());
				dao.setQryValue(qryIndex, 4, vo.getStrseatId());
				dao.setQryValue(qryIndex, 5, vo.getStrremark());
				dao.setQryValue(qryIndex, 6, vo.getStrisValid());
				dao.setQryValue(qryIndex, 7, vo.getStrisVisible());
				dao.setQryValue(qryIndex, 8, vo.getStrconsumeableCharge());
				dao.setQryValue(qryIndex, 9, strtemp[0]);
				dao.setQryValue(qryIndex, 10, strtemp[1]);
				dao.execute(qryIndex,0);
				if(vo.gethospitalServiceValues()!=null)
				{
				    int temp[]=vo.gethospitalServiceValues();
				    if(temp.length!=0)
					query = billing.qryHandler_billing.getQuery(1, "insert.grp.1");
					qryIndex = dao.setQuery(query);
					for (int i = 0; i < temp.length; i++)
					{
							dao.setQryValue(qryIndex, 1,""+temp[i]);
							dao.setQryValue(qryIndex, 2, strtemp[1]);
							dao.setQryValue(qryIndex, 3, strtemp[0]);
							dao.setQryValue(qryIndex, 4, strtemp[0]);
							dao.setQryValue(qryIndex, 5, strtemp[1]);
							dao.setQryValue(qryIndex, 6, vo.getStrEffectiveFrom());
							dao.setQryValue(qryIndex, 7, vo.getStrseatId());
							dao.setQryValue(qryIndex, 8, vo.getStrseatId());
							dao.setQryValue(qryIndex, 9, vo.getStrEffectiveFrom());
							dao.setQryValue(qryIndex, 10, vo.getStrremark());
							dao.execute(qryIndex, 0);
					}
				}
				synchronized (dao) {
					dao.fire();
					retvalue = true;
				}
			} 
			catch (Exception e) 
			{
				retvalue = false;
				e.printStackTrace();
				vo.setStrMsgString("billing.GroupMstDAO.updateQuery"+ e.getMessage());
				vo.setStrMsgType("1");

			}
			finally 
			{
				dao.free();
				dao = null;
			}
			return retvalue;

		}
		/**
		 * initial add query to check duplicate group name
		 * @param vo
		 * @return boolean
		 */
		public static boolean initialAddQuery(GroupMstVO vo) throws Exception 
		{
			HisDAO dao = new HisDAO("Billing", "GroupMstDAO");
			boolean returnValue = false;
			int qryIndex;
			int count = 0;
			WebRowSet wb = null;
			String query = billing.qryHandler_billing.getQuery(1, "select.grp.3");
			try
			{
				qryIndex = dao.setQuery(query);
				dao.setQryValue(qryIndex, 1, vo.getStrgroupName());
				dao.setQryValue(qryIndex, 2, vo.getStrhospitalcode());
				wb = dao.executeQry(qryIndex);
				while (wb.next()) 
				{
					count = Integer.parseInt(wb.getString(1));
				}
				if (count == 0) 
				{
					returnValue = true;
				} 
				else
				{
					returnValue = false;
				}
			} 
			catch (Exception e) 
			{
				returnValue = false;
				e.printStackTrace();
				vo.setStrMsgString("billing.GroupMstDAO.initialAddQuery"+ e.getMessage());
				vo.setStrMsgType("1");
			}
			finally 
			{
				dao.free();
				dao = null;
			}
			return returnValue;
		}
		/**
		 * initial add query to check duplicate global group Id
		 * @param vo
		 * @return boolean
		 */
		public static boolean initialAddQuery2(GroupMstVO vo) throws Exception 
		{
			HisDAO dao = new HisDAO("Billing", "GroupMstDAO");
			boolean returnValue = false;
			int qryIndex;
			int count = 0;
			WebRowSet wb = null;
			String query = billing.qryHandler_billing.getQuery(1, "select.grp.3.0");
			try 
			{
				qryIndex = dao.setQuery(query);
				dao.setQryValue(qryIndex, 1, vo.getStrGlobalGroupName());
				dao.setQryValue(qryIndex, 2, vo.getStrhospitalcode());
				wb = dao.executeQry(qryIndex);
				while (wb.next()) 
				{
					count = Integer.parseInt(wb.getString(1));	
				}
				if (count == 0) 
				{
					returnValue = true;
				}
				else
				{
					returnValue = false;
				}

			} 
			catch (Exception e)
			{
				returnValue = false;
				e.printStackTrace();
				vo.setStrMsgString("billing.GroupMstDAO.initialAddQuery2"+ e.getMessage());
				vo.setStrMsgType("1");
			}

			finally 
			{
				dao.free();
				dao = null;
			}
			return returnValue;
		}
		/**
		 * initial update query
		 * @param vo
		 * @param chk
		 * @return boolean
		 */
		public static boolean initialUpdateQuery(String chk, GroupMstVO vo)throws Exception 
		{
			HisDAO dao = new HisDAO("Billing", "GroupMstDAO");
			String strtemp[] = null;
			String strtemp2[] = null;
			boolean returnValue = false;
			int qryIndex;
			int count = 0;
			WebRowSet wb = null;
			String query = billing.qryHandler_billing.getQuery(1, "select.grp.4");
			try
			{
				strtemp = chk.replace('@', '#').split("#");
				strtemp2 = strtemp[1].replace("$", "#").split("#");
				strtemp[1] = strtemp2[0];
				qryIndex = dao.setQuery(query);
				dao.setQryValue(qryIndex, 1, vo.getStrgroupName());
				dao.setQryValue(qryIndex, 2, strtemp[0]);
				dao.setQryValue(qryIndex, 3, strtemp[1]);
				wb = dao.executeQry(qryIndex);
				while (wb.next()) 
				{
					count = Integer.parseInt(wb.getString(1));
				}
				if (count == 0) 
				{
					returnValue = true;
				}
				else
				{
					returnValue = false;
				}
				
			}
			catch (Exception e) 
			{
				returnValue = false;
				e.printStackTrace();
				vo.setStrMsgString("billing.GroupMstDAO.initialUpdateQuery"+ e.getMessage());
				vo.setStrMsgType("1");
			}
			finally 
			{
				dao.free();
				dao = null;
			}
			return returnValue;
		}
		public static WebRowSet hospitalServiceAdd(String chk1, GroupMstVO vo) throws Exception
		{
			HisDAO dao = new HisDAO("Billing", "GroupMstDAO");
			int qryIndex;
			WebRowSet wb = null;
			String query = billing.qryHandler_billing.getQuery(1,"gbl.chargetype.0");
			try 
			{
			       qryIndex = dao.setQuery(query);
		           dao.setQryValue(qryIndex, 1, chk1);
		           wb= dao.executeQry(qryIndex);
		           if(wb!=null)
		           {
		        	   vo.setHospitalServiceCheckboxwb(wb);
		           }
			}
			catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("billing.GroupMstDAO.hospitalServiceQuery"+ e.getMessage());
			vo.setStrMsgType("1");
		}
		finally 
		{
			dao.free();
			dao = null;
		}
			return wb;
		}
		
		public static WebRowSet hospitalServiceCheckbox(String chk1, GroupMstVO vo,String[] temp) throws Exception
		{
			HisDAO dao = new HisDAO("Billing", "GroupMstDAO");
			int qryIndex;
			WebRowSet wb = null;
			String query = billing.qryHandler_billing.getQuery(1,"select.grouptype.0");
			try {
			       qryIndex = dao.setQuery(query);
			       dao.setQryValue(qryIndex, 1, temp[0]);
			       dao.setQryValue(qryIndex, 2, temp[1]);
		           dao.setQryValue(qryIndex, 3, chk1);
		           wb= dao.executeQry(qryIndex);
		           if(wb!=null)
		           {
		        	   vo.setHospitalServiceCheckboxwb(wb);
		           }
			}
			catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("billing.GroupMstDAO.hospitalServiceQuery"+ e.getMessage());
			vo.setStrMsgType("1");
		}

		finally
		{
			dao.free();
			dao = null;
		}
			return wb;
		}
		
		
	}
	

