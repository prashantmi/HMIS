package ipd.masters.bo;

import ipd.masters.dao.ComponentMstDAO;
import ipd.masters.vo.ComponentMstVO;
import hisglobal.exceptions.HisException;

public class ComponentMstBO {
	/**
	 * First it will check duplicate record is there or not,<br>
	 * If duplicate record is there then it display a message "Record Not Saved!Data Already Exist!!"<br>
	 * If there is no duplicate record then it saved the record<br>
	 * And get back to the list page,<br>
	 * But if some error is occurs then it will show the message "Record Not Saved!!!"<br>
	 * 
	 * @param vo - FormBean Object
	 * @return - String
	 */
	public String InsertRecord(ComponentMstVO vo) {
		String strTarget = "add";
		boolean fretValue = true;
		String strErrmsg = "";
		String strmsgText = "";
		String strMsg = ""; 
		String strWarning = "";

		try {
			 fretValue = this.initialAdd(vo); // Check for Record Duplicacy
			 if (fretValue) 
			   {
				fretValue = ComponentMstDAO.insertQuery(vo);
				if (fretValue) {
					strMsg = "Record saved successfully!";
					vo.setStrMsg(strMsg);
				}
			}
		   else
		   {
			   strWarning = "Record Not Saved!Data Already Exist!!";
			   vo.setStrWarning(strWarning); 
		   }
		   
		} catch (Exception e) {
			fretValue = false;
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Component Master", "BOComponentMst-->InsertRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			strmsgText = "ipd.masters.BOComponentMst.InsertRecord(vo) --> "
					+ e.getMessage();
			new HisException("IPD", "Component Master.Add", strmsgText);
			
			strErrmsg = "Record Not Saved!!!";
			vo.setStrErrorMsg(strErrmsg); 
		}
		return strTarget;
	}
	
	/**
	 * To check weather duplicate value is exist or not
	 * @param vo - FormBean Object
	 * @return Boolean value true - Record Not Saved!Data Already Exist!!
	 * false - Record saved successfully!
	 * @throws Exception
	 */
	public boolean initialAdd(ComponentMstVO vo) throws Exception// Check for Existing Duplicate Record
		{
				boolean retValue = false;
				try {
					retValue = ComponentMstDAO.initialAddQuery(vo);
				
				} catch (Exception e) {
					vo.setStrErrorMsg(e.getMessage());
					   HisException eObj = new HisException("IPD-->Component Master", "BOComponentMst-->initialAdd()", vo.getStrErrorMsg());
					   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
					   eObj = null;
				}
				return retValue;
		}
	
	/**
	 * invokes modifyQuery Method.
	 * 
	 * @param strChk - Primary Keys Concatenated with '@'.
	 * @param formBean - FormBean Object
	 */
	public void modifyRecord(String chk1, ComponentMstVO vo) throws Exception {

		try {
			ComponentMstDAO.modifyQuery(chk1, vo);
		} catch (Exception e) {
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Component Master", "BOComponentMst-->modifyRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
	}

	 /**
	  * Update method returns true if Record Updated Successfully false if Record<br>
	  * Not Updated Successfully
	  * @param strChk - Primary Keys Concatenated with '@'.
	  * @param formBean -FormBean Object
	  * @return
	  */
	public String updateRecord(String chk1, ComponentMstVO vo) {
		String strtarget = "";
		boolean fretValue;
		boolean fretValue1;
		String strmsgText = "";
		try {
		
			fretValue1 = this.initialUpdate(chk1, vo);// Check For Record
														// Modification

			if (!fretValue1) {
				String errmsg = "Record can not be modified due To Duplicacy !";
				vo.setStrErrorMsg(errmsg);
				strtarget = "modify";
			} else {
				fretValue = ComponentMstDAO.updateQuery(chk1, vo);
				if (!fretValue) {

					String errmsg = "Record not modified successfully!";
					vo.setStrErrorMsg(errmsg);
					strtarget = "modify";
				} else {

					strtarget = "list";
				}
			}
		} catch (Exception e) {
			fretValue = false;
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Component Master", "BOComponentMst-->updateRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			strmsgText = "ipd.masters.BOComponentMst.InsertRecord(vo) --> "
					+ e.getMessage();
			new HisException("IPD", "Component Master.Update", strmsgText);
		}
		return strtarget;
	}

	/**
	 * To initial Update
	 * 
	 * @param chk - Primary Keys Concatenated with '@'.
	 * @param vo - FormBean Object
	 * @return true - Record not modified successfully!
	 * false - Record can not be modified due To Duplicate data !
	 * @throws Exception
	 */
	public boolean initialUpdate(String chk, ComponentMstVO vo)
			throws Exception// Check Data in Database for Modification
	{		
		boolean retvalue = true;
		try {			
			retvalue = ComponentMstDAO.initialUpdateQuery(chk, vo);
		} catch (Exception e) {
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Component Master", "BOComponentMst-->initialUpdate()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return retvalue;
	}

}
