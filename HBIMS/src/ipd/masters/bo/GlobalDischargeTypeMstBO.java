package ipd.masters.bo;

import ipd.masters.dao.GlobalDischargeTypeMstDAO;
import ipd.masters.vo.GlobalDischargeTypeMstVO;
import hisglobal.exceptions.HisException;

public class GlobalDischargeTypeMstBO {
	/**
	 * Invoke GlobalDischargeTypeMstDAO's insertQuery()
	 * @param vo
	 * @return
	 */
	// ///////////////////////////TEST//////////////////////////////////////////
	public String InsertRecord(GlobalDischargeTypeMstVO globalDischargeTypeMstVO_p) {
		String strTarget = "add";
		boolean fretValue = true;
		String strErrmsg = "";
		String strmsgText = "";
		String strMsg = ""; 
		String strWarning = "";

		try {
			fretValue = this.initialAdd(globalDischargeTypeMstVO_p); // Check for Record Duplicacy
			if (fretValue) {
				fretValue = GlobalDischargeTypeMstDAO.insertQuery(globalDischargeTypeMstVO_p);
				
				if (fretValue) {
					
					strMsg = "Record saved successfully!";
					globalDischargeTypeMstVO_p.setStrMsg(strMsg);
				}
			}else
			{
				strWarning = "Record Not Saved!Data Already Exist!!";
				   globalDischargeTypeMstVO_p.setStrWarning(strWarning); 
			}
		} catch (Exception e) {
			fretValue = false;
			globalDischargeTypeMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Discharge Type Master", "GlobalDischargeTypeMstBO-->InsertRecord()", globalDischargeTypeMstVO_p.getStrErrorMsg());
			   globalDischargeTypeMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			strErrmsg = "Record Not Saved!!!";
			globalDischargeTypeMstVO_p.setStrErrorMsg(strErrmsg); 
			strmsgText = "ipd.masters.GlobalDischargeTypeMstBO.InsertRecord(globalDischargeTypeMstVO_p) --> "
					+ e.getMessage();
			new HisException("IPD", "Discharge Type Master.Add",strmsgText);
		}

		

		return strTarget;
	}
/**
 * Invoke GlobalDischargeTypeMstDAO's initialAddQuery()
 * @param globalDischargeTypeMstVO_p
 * @return
 * @throws Exception
 */
	public boolean initialAdd(GlobalDischargeTypeMstVO globalDischargeTypeMstVO_p) throws Exception// Check
																		// for
																		// Existing
																		// Duplicate
																		// Record
	{
		boolean fretvalue = true;
		// System.out.println("In initialAdd(BO) (SAVE-II)");
		try {
			fretvalue = GlobalDischargeTypeMstDAO.initialAddQuery(globalDischargeTypeMstVO_p);
			// System.out.println("retvalue in bo =>"+fretvalue);
		} catch (Exception e) {
			globalDischargeTypeMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Discharge Type Master", "GlobalDischargeTypeMstBO-->initialAdd()", globalDischargeTypeMstVO_p.getStrErrorMsg());
			   globalDischargeTypeMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fretvalue;

	}
/**
 * invoke GlobalDischargeTypeMstDAO's modifyQuery()
 * @param chk1
 * @param globalDischargeTypeMstVO_p
 * @throws Exception
 */
	public void modifyRecord(String chk1, GlobalDischargeTypeMstVO globalDischargeTypeMstVO_p)
			throws Exception {
		// System.out.println("Inside modifyRecord()=====>");
		try {
			GlobalDischargeTypeMstDAO.modifyQuery(chk1, globalDischargeTypeMstVO_p);
		} catch (Exception e) {
			globalDischargeTypeMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Discharge Type Master", "GlobalDischargeTypeMstBO-->modifyRecord()", globalDischargeTypeMstVO_p.getStrErrorMsg());
			   globalDischargeTypeMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}
/**
 * invoke initialUpdate of this class 
 * @param chk1
 * @param globalDischargeTypeMstVO_p
 * @return
 */
	public String updateRecord(String chk1, GlobalDischargeTypeMstVO globalDischargeTypeMstVO_p) {
		String strtarget = "";
		boolean fretvalue;
		boolean fretvalue1;
		//String strmsgText = "";
	//	GlobalDischargeTypeMstBO bo = new GlobalDischargeTypeMstBO();
		try {
			fretvalue1 = this.initialUpdate(chk1, globalDischargeTypeMstVO_p);// Check For Record
														// Modification

			if (!fretvalue1) {
				String strWarning = "Record can not be modified!";
				globalDischargeTypeMstVO_p.setStrWarning(strWarning);
				strtarget = "modify";
			} else {
				fretvalue = GlobalDischargeTypeMstDAO.updateQuery(chk1, globalDischargeTypeMstVO_p);
				if (!fretvalue) {

					String strWarning = "Record not modified successfully!";
					globalDischargeTypeMstVO_p.setStrWarning(strWarning);
					strtarget = "modify";
				} else {

					strtarget = "list";
				}
			}
		} catch (Exception e) {
			fretvalue = false;
			globalDischargeTypeMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Discharge Type Master", "DischargeTypeMstBO-->updateRecord()", globalDischargeTypeMstVO_p.getStrErrorMsg());
			   globalDischargeTypeMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			
		}
		return strtarget;
	}
/**
 * invoke DischargeTypeMstDAO's initialUpdateQuery()
 * @param chk
 * @param globalDischargeTypeMstVO_p
 * @return
 * @throws Exception
 */
	public boolean initialUpdate(String chk, GlobalDischargeTypeMstVO globalDischargeTypeMstVO_p)
			throws Exception// Check Data in Database for Modification
	{
		boolean fretvalue = true;
		try {
			fretvalue = GlobalDischargeTypeMstDAO.initialUpdateQuery(chk, globalDischargeTypeMstVO_p);
		} catch (Exception e) {
			globalDischargeTypeMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Discharge Type Master", "GlobalDischargeTypeMstBO-->initialUpdate()", globalDischargeTypeMstVO_p.getStrErrorMsg());
			   globalDischargeTypeMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fretvalue;

	}

}