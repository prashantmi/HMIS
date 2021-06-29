

/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Client Master BO
 * 
 * Created on: 30-08-2011
 */
         
package billing.masters.bo;

import billing.masters.dao.ClientMstDAO;
import billing.masters.vo.ClientMstVO;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

public class ClientMstBO {

	/**
	 * function to insert data. This function retrieve result from DAO class and
	 * forwards to controller.
	 * 
	 * @param vo
	 * @return rval (boolean)
	 */
	public boolean insert(ClientMstVO vo) 
	{
		boolean fretValue = true;
		boolean rval=false;
		String strmsgText = "";
		String strMsg = "";
		String strWarning = "";

		try 
		{
			fretValue = this.initialAdd(vo); // Check for Record Duplicacy
			
			if (fretValue) 
			{
				fretValue = ClientMstDAO.insertQuery(vo);
				
				if (fretValue) 
				{
					strMsg = "Record saved successfully!";
					vo.setStrMsg(strMsg);
					rval=true;
				} 
				else 
				{
					strWarning = "Record not saved !";
					vo.setStrWarning(strWarning);
					rval=false;
				}
			} 
			else 
			{
				strWarning = "Record Not Saved!Data Already Exist!!";
				vo.setStrWarning(strWarning);
			}

		} catch (Exception e) 
		{
			e.printStackTrace();
			fretValue = false;
			strmsgText = "billing.masters.ClientMstBO.insert(vo) --> "+ e.getMessage();
			HisException eObj = new HisException("Billing",	"ClientMaster->Add()", strmsgText);
			vo.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		}

		return rval;
	}

	/**
	 * function to check the duplicacy of insertion data. 
	 * 
	 * @param vo
	 * @return boolean retValue
	 */
	public boolean initialAdd(ClientMstVO vo) throws Exception
	{
		boolean retValue = false;
		
		try 
		{
			retValue = ClientMstDAO.initialAddQuery(vo);

		} catch (Exception e) 
		{
			throw new Exception("ClientMstBO.initialAdd() -->" + e.getMessage());
		}
		return retValue;

	}
	/**
	 * function to view the modification data. 
	 * 
	 * @param chk1,vo
	 * @return boolean fval
	 */
	public void modify(String chk1, ClientMstVO vo) throws Exception 
	{
		String strmsgText = null;
		
		try 
		{
			ClientMstDAO.modifyQuery(chk1, vo);
			vo.setStrChk(chk1);
		} catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "billing.masters.bo.ClientMstBO.modifyRecord(chk1,vo) --> "+ e.getMessage();
			HisException eObj = new HisException("Billing",	"ClientMaster->modifyRecord()", strmsgText);
			vo.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		}
	}
	/**
	 * function to update the data. 
	 * 
	 * @param chk1,vo
	 * @return boolean fretvalue1,fretvalue
	 */
	public String update(String chk1, ClientMstVO vo) 
	{
		String strtarget = "";
		String strmsgText = "";
		String strMsg = "";
		boolean fretvalue;
		boolean fretvalue1;
		
		try 
		{
			// Check For Record Modification
			fretvalue1 = this.initialUpdate(chk1, vo);

			if (!fretvalue1) 
			{
				String errmsg = "Record cannot be modified! Data Already Exists";
				vo.setStrErr(errmsg);
				strtarget = "add";
			} 
			else 
			{
				fretvalue = ClientMstDAO.updateQuery(chk1, vo);
				
				if (!fretvalue) 
				{

					String errmsg = "Record not modified";
					vo.setStrErr(errmsg);
					strtarget = "add";
				} 
				else 
				{
					strMsg = "Record modified successfully!";
					vo.setStrMsg(strMsg);
					strtarget = "list";
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "billing.masters.bo.ClientMstBO.updateRecord(vo) --> "	+ e.getMessage();
			HisException eObj = new HisException("Billing","ClientMaster->Update()", strmsgText);
			vo.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		}
		
		return strtarget;
	}

	/**
	 * function to check the duplicacy of modification data. 
	 * 
	 * @param chk,vo
	 * @return boolean rval
	 */
	public boolean initialUpdate(String chk, ClientMstVO vo) throws Exception
	{
		String strmsgText = "";
		boolean fretvalue = false;
		
		try 
		{
			fretvalue = ClientMstDAO.initialUpdateQuery(chk, vo);
		} catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "billing.masters.bo.ClientMstBO.initialUpdate(chk,vo) --> "+ e.getMessage();
			HisException eObj = new HisException("Billing",	"ClientMaster->initialUpdate()", strmsgText);
			vo.setStrErr("Application Error [ERROR ID : " + eObj.getErrorID()+ "],Contact System Administrator! ");

			eObj = null;
		}
		return fretvalue;
	}
	
	public ClientMstVO setClientTypeId(ClientMstVO vo) 
	{
		ClientMstVO clientMstVo = null;
		
		try 
		{
			HisDAO hisDAO = new HisDAO("Billing", "billinBo");
			clientMstVo = ClientMstDAO.setClientTypeId(vo, hisDAO);

			if (clientMstVo.getStrMsgType() != null && clientMstVo.getStrMsgType().equals("1"))  
			{
				vo.setStrMsgString("clientMstBO.setClientTypeId(vo) --> "+ vo.getStrMsgString());
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgType("1");
		} finally {
		}
		return clientMstVo;
	}
	
}
