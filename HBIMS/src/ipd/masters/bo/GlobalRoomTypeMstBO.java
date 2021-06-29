package ipd.masters.bo;

import ipd.masters.dao.GlobalRoomTypeMstDAO;
import ipd.masters.vo.GlobalRoomTypeMstVO;
import hisglobal.exceptions.HisException;

public class GlobalRoomTypeMstBO {
	
	/**
	 * Invoke GlobalRoomTypeMstDAO's insertQuery()
	 * @param globalRoomTypeMstVO_p
	 * @return
	 * @throws Exception
	 */
	
	public String InsertRecord(GlobalRoomTypeMstVO globalRoomTypeMstVO_p) throws Exception {
		String strTarget = "add";
		boolean fretValue = true;
		//String strErrmsg = "";
	//	String strmsgText = "";
		String strMsg = ""; 
		String strWarning = "";

		try {
			fretValue = this.initialAdd(globalRoomTypeMstVO_p); // Check for Record Duplicacy
			if (fretValue) {
				fretValue = GlobalRoomTypeMstDAO.insertQuery(globalRoomTypeMstVO_p);
	
				if (fretValue) {
						strMsg = "Record saved successfully!";
					globalRoomTypeMstVO_p.setStrMsg(strMsg);
				}
				else
					globalRoomTypeMstVO_p.setStrerrorMsg("Data is not saved successfully");
			}
			else
			{
				strWarning = "Record Not Saved!Data Already Exist!!";
				   globalRoomTypeMstVO_p.setStrWarning(strWarning); 
			}
		} catch (Exception e) {
			fretValue = false;
			globalRoomTypeMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Room Type Master", "GlobalRoomTypeMstBO-->InsertRecord()", globalRoomTypeMstVO_p.getStrErrorMsg());
			   globalRoomTypeMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
  	return strTarget;
	}
/**
 * Invoke GlobalRoomTypeMstDAO's initialAddQuery()
 * @param globalRoomTypeMstVO_p
 * @return
 */
	public boolean initialAdd(GlobalRoomTypeMstVO globalRoomTypeMstVO_p)// Check for Existing Duplicate
												// Record
	{
		boolean fretvalue = true;
	try{
		fretvalue = GlobalRoomTypeMstDAO.initialAddQuery(globalRoomTypeMstVO_p);
	}catch(Exception e){
		globalRoomTypeMstVO_p.setStrErrorMsg(e.getMessage());
		   HisException eObj = new HisException("IPD-->Global Room Type Master", "GlobalRoomTypeMstBO-->initialAdd()", globalRoomTypeMstVO_p.getStrErrorMsg());
		   globalRoomTypeMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		   eObj = null;
	}
		return fretvalue;

	}

	public void modifyRecord(String strChk1_p, GlobalRoomTypeMstVO globalRoomTypeMstVO_p) throws HisException {
		try{
		GlobalRoomTypeMstDAO.modifyQuery(strChk1_p, globalRoomTypeMstVO_p);
		}catch(Exception e){
			globalRoomTypeMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Room Type Master", "GlobalRoomTypeMstBO-->modifyRecord()", globalRoomTypeMstVO_p.getStrErrorMsg());
			   globalRoomTypeMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}
/**
 * This function invoke initialUpdate of this class
 * @param strChk1_p
 * @param globalRoomTypeMstVO_p
 * @return
 * @throws HisException
 */
	public String updateRecord(String strChk1_p, GlobalRoomTypeMstVO globalRoomTypeMstVO_p)
			throws HisException {
		String strtarget = "";
		boolean fretvalue;
		boolean fretvalue1;
		try{
		fretvalue1 = this.initialUpdate(strChk1_p, globalRoomTypeMstVO_p);// Check For Record
		
	
		// Modification

		if (!fretvalue1) {
			String errmsg = "Record can not be modified!Data Already Exist";
			globalRoomTypeMstVO_p.setStrerrorMsg(errmsg);
		
			
			strtarget = "modify";
		} else {
			fretvalue = GlobalRoomTypeMstDAO.updateQuery(strChk1_p, globalRoomTypeMstVO_p);
			if (!fretvalue) {

				String errmsg = "Record not modified successfully!";
				globalRoomTypeMstVO_p.setStrerrorMsg(errmsg);
				strtarget = "modify";
			} else {

				strtarget = "list";
			}
		}
		}catch(Exception e){
			globalRoomTypeMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Room Type Master", "GlobalRoomTypeMstBO-->updateRecord()", globalRoomTypeMstVO_p.getStrErrorMsg());
			   globalRoomTypeMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return strtarget;
	}
/**
 * Invoke GlobalRoomTypeMstDAO's initialUpdateQuery
 * @param strChk_p
 * @param globalRoomTypeMstVO_p
 * @return
 */
	public boolean initialUpdate(String strChk_p, GlobalRoomTypeMstVO globalRoomTypeMstVO_p)// Check Data in
																// Database for
																// Modification
	{
		boolean fretvalue = true;
		try{
		fretvalue = GlobalRoomTypeMstDAO.initialUpdateQuery(strChk_p, globalRoomTypeMstVO_p);
		}catch(Exception e){
			globalRoomTypeMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Room Type Master", "GlobalRoomTypeMstBO-->initialUpdate()", globalRoomTypeMstVO_p.getStrErrorMsg());
			   globalRoomTypeMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fretvalue;

	}

}