package ipd.masters.bo;

import ipd.masters.dao.RoomTypeMstDAO;
import ipd.masters.vo.RoomTypeMstVO;
import hisglobal.exceptions.HisException;

public class RoomTypeMstBO {
	
	
	
	/**
	 * This function invoke getGlobalRoomType of this class
	 * @param vo
	 * @return
	 * @throws HisException
	 */
	public void getGlobalRoomType(RoomTypeMstVO vo) throws HisException {
		try{
			RoomTypeMstDAO.getGlobalRoomType(vo);
		}catch(Exception e){
			//e.printStackTrace();
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Type Master", "getGlobalRoomType-->getGlobalRoomType()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}

	/**
	 * Invoke DAORoomTypeMst's insertQuery()
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	
	public String InsertRecord(RoomTypeMstVO vo) throws Exception {
		String strTarget = "add";
		boolean fretValue = true;
		//String strErrmsg = "";
	//	String strmsgText = "";
		String strMsg = ""; 
		String strWarning = "";

		try {
			fretValue = this.initialAdd(vo); // Check for Record Duplicacy
			if (fretValue) {
				fretValue = RoomTypeMstDAO.insertQuery(vo);
	
				if (fretValue) {
						strMsg = "Record saved successfully!";
					vo.setStrMsg(strMsg);
				}
				else
					vo.setStrerrorMsg("Data is not saved successfully");
			}
			else
			{
				strWarning = "Record Not Saved!Data Already Exist!!";
				   vo.setStrWarning(strWarning); 
			}
		} catch (Exception e) {
			fretValue = false;
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("ADT-->Room Type Master", "BORoomTypeMst-->InsertRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
  	return strTarget;
	}
/**
 * Invoke DAORoomTypeMst's initialAddQuery()
 * @param vo
 * @return
 */
	public boolean initialAdd(RoomTypeMstVO vo)// Check for Existing Duplicate
												// Record
	{
		boolean fretvalue = true;
	try{
		fretvalue = RoomTypeMstDAO.initialAddQuery(vo);
	}catch(Exception e){
		vo.setStrErrorMsg(e.getMessage());
		   HisException eObj = new HisException("IPD-->Room Type Master", "BORoomTypeMst-->initialAdd()", vo.getStrErrorMsg());
		   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
		   eObj = null;
	}
		return fretvalue;

	}

	public void modifyRecord(String chk1, RoomTypeMstVO vo) throws HisException {
		try{
		RoomTypeMstDAO.modifyQuery(chk1, vo);
		}catch(Exception e){
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Type Master", "BORoomTypeMst-->modifyRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}
/**
 * This function invoke initialUpdate of this class
 * @param chk1
 * @param vo
 * @return
 * @throws HisException
 */
	public String updateRecord(String chk1, RoomTypeMstVO vo)
			throws HisException {
		String strtarget = "";
		boolean fretvalue;
		boolean fretvalue1;
		try{
		fretvalue1 = this.initialUpdate(chk1, vo);// Check For Record
		
	
		// Modification

		if (!fretvalue1) {
			String errmsg = "Record can not be modified!Data Already Exist";
			vo.setStrerrorMsg(errmsg);
		
			
			strtarget = "modify";
		} else {
			fretvalue = RoomTypeMstDAO.updateQuery(chk1, vo);
			if (!fretvalue) {

				String errmsg = "Record not modified successfully!";
				vo.setStrerrorMsg(errmsg);
				strtarget = "modify";
			} else {

				strtarget = "list";
			}
		}
		}catch(Exception e){
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Type Master", "BORoomTypeMst-->updateRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return strtarget;
	}
/**
 * Invoke DAORoomTypeMst's initialUpdateQuery
 * @param chk
 * @param vo
 * @return
 */
	public boolean initialUpdate(String chk, RoomTypeMstVO vo)// Check Data in
																// Database for
																// Modification
	{
		boolean fretvalue = true;
		try{
		fretvalue = RoomTypeMstDAO.initialUpdateQuery(chk, vo);
		}catch(Exception e){
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Type Master", "BORoomTypeMst-->initialUpdate()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fretvalue;

	}

}