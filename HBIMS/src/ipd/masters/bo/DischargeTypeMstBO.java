package ipd.masters.bo;

import ipd.masters.dao.DischargeTypeMstDAO;
import ipd.masters.vo.DischargeTypeMstVO;
import hisglobal.exceptions.HisException;

public class DischargeTypeMstBO {
	/**
	 * Invoke DAODischargeTypeMst's insertQuery()
	 * @param vo
	 * @return
	 */
	// ///////////////////////////TEST//////////////////////////////////////////
	public String InsertRecord(DischargeTypeMstVO vo) {
		String strTarget = "add";
		boolean fretValue = true;
		String strErrmsg = "";
		String strmsgText = "";
		String strMsg = ""; 
		String strWarning = "";

		try {
			fretValue = this.initialAdd(vo); // Check for Record Duplicacy
			if (fretValue) {
				fretValue = DischargeTypeMstDAO.insertQuery(vo);
				
				if (fretValue) {
					
					strMsg = "Record saved successfully!";
					vo.setStrMsg(strMsg);
				}
			}else
			{
				strWarning = "Record Not Saved!Data Already Exist!!";
				   vo.setStrWarning(strWarning); 
			}
		} catch (Exception e) {
			fretValue = false;
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Discharge Type Master", "BODischargeTypeMst-->InsertRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			strErrmsg = "Record Not Saved!!!";
			vo.setStrErrorMsg(strErrmsg); 
			strmsgText = "ipd.masters.BODischargeTypeMst.InsertRecord(vo) --> "
					+ e.getMessage();
			new HisException("IPD", "Discharge Type Master.Add",strmsgText);
		}

		

		return strTarget;
	}
/**
 * Invoke DAODischargeTypeMst's initialAddQuery()
 * @param vo
 * @return
 * @throws Exception
 */
	public boolean initialAdd(DischargeTypeMstVO vo) throws Exception// Check
																		// for
																		// Existing
																		// Duplicate
																		// Record
	{
		boolean fretvalue = true;
		// System.out.println("In initialAdd(BO) (SAVE-II)");
		try {
			fretvalue = DischargeTypeMstDAO.initialAddQuery(vo);
			// System.out.println("retvalue in bo =>"+fretvalue);
		} catch (Exception e) {
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Discharge Type Master", "BODischargeTypeMst-->initialAdd()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fretvalue;

	}
/**
 * invoke DAODischargeTypeMst's modifyQuery()
 * @param chk1
 * @param vo
 * @throws Exception
 */
	public void modifyRecord(String chk1, DischargeTypeMstVO vo)
			throws Exception {
		// System.out.println("Inside modifyRecord()=====>");
		try {
			DischargeTypeMstDAO.modifyQuery(chk1, vo);
		} catch (Exception e) {
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Discharge Type Master", "BODischargeTypeMst-->modifyRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}
/**
 * invoke initialUpdate of this class 
 * @param chk1
 * @param vo
 * @return
 */
	public String updateRecord(String chk1, DischargeTypeMstVO vo) {
		String strtarget = "";
		boolean fretvalue;
		boolean fretvalue1;
		//String strmsgText = "";
	//	BODischargeTypeMst bo = new BODischargeTypeMst();
		try {
			fretvalue1 = this.initialUpdate(chk1, vo);// Check For Record
														// Modification

			if (!fretvalue1) {
				String strWarning = "Record can not be modified!";
				vo.setStrWarning(strWarning);
				strtarget = "modify";
			} else {
				fretvalue = DischargeTypeMstDAO.updateQuery(chk1, vo);
				if (!fretvalue) {

					String strWarning = "Record not modified successfully!";
					vo.setStrWarning(strWarning);
					strtarget = "modify";
				} else {

					strtarget = "list";
				}
			}
		} catch (Exception e) {
			fretvalue = false;
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Discharge Type Master", "BODischargeTypeMst-->updateRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			
		}
		return strtarget;
	}
/**
 * invoke DAODischargeTypeMst's initialUpdateQuery()
 * @param chk
 * @param vo
 * @return
 * @throws Exception
 */
	public boolean initialUpdate(String chk, DischargeTypeMstVO vo)
			throws Exception// Check Data in Database for Modification
	{
		boolean fretvalue = true;
		try {
			fretvalue = DischargeTypeMstDAO.initialUpdateQuery(chk, vo);
		} catch (Exception e) {
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Discharge Type Master", "BODischargeTypeMst-->initialUpdate()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fretvalue;

	}

}