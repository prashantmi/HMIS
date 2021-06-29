package ipd.masters.bo;

import ipd.masters.dao.GlobalBedStatusMstDAO;
import ipd.masters.vo.GlobalBedStatusMstVO;
import hisglobal.exceptions.HisException;

public class GlobalBedStatusMstBO {
	/**
	 * Invoke GlobalBedStatusMstDAO' s insetrQuery()
	 * @param globalBedStatusMstVO_p
	 * @return
	 */
		public String InsertRecord(GlobalBedStatusMstVO globalBedStatusMstVO_p) {
		String strTarget = "add";
		boolean fretValue = true;
		String strErrmsg = "";
		String strmsgText = "";
		String strMsg = ""; 
		String strWarning = "";

		try 
		{
			 fretValue = this.initialAdd(globalBedStatusMstVO_p); // Check for Record Duplicacy
			 
		   if (fretValue) 
		   {
				fretValue = GlobalBedStatusMstDAO.insertQuery(globalBedStatusMstVO_p);
				if (fretValue) 
				{
					strMsg = "Record Saved successfully!";
					globalBedStatusMstVO_p.setStrMsg(strMsg);
				}
			}
		    else
		    {
			   strWarning = "Record Not Saved!Data Already Exist!!";
			   globalBedStatusMstVO_p.setStrWarning(strWarning); 
		    }		   
		} 
		catch (Exception e) 
		{
			globalBedStatusMstVO_p.setStrErrorMsg(e.getMessage());
			HisException eObj = new HisException("ADT-->Global Bed Status Master ", "GlobalBedStatusMstBO-->InsertRecord()", globalBedStatusMstVO_p.getStrErrorMsg());
			globalBedStatusMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
			fretValue = false;
			strmsgText = "ipd.masters.GlobalBedStatusMstBO.InsertRecord(globalBedStatusMstVO_p) --> "+ e.getMessage();
			new HisException("ADT", "Bed Status Master.Add", strmsgText);
			
			strErrmsg = "Record Not Saved!!!";
			globalBedStatusMstVO_p.setStrErrorMsg(strErrmsg);
		}		
		return strTarget;
	}
/**
 * Invoke GlobalBedStatusMstDAO's initialAddQuery()
 * @param globalBedStatusMstVO_p
 * @return
 * @throws Exception
 */
	public boolean initialAdd(GlobalBedStatusMstVO globalBedStatusMstVO_p) throws Exception// Check for Existing Duplicate Record
	{
		boolean retValue = false;
		try 
		{
			retValue = GlobalBedStatusMstDAO.initialAddQuery(globalBedStatusMstVO_p);
		} 
		catch (Exception e) 
		{
			globalBedStatusMstVO_p.setStrErrorMsg(e.getMessage());
			HisException eObj = new HisException("IPD-->Global Bed Status Master ", "GlobalBedStatusMstBO-->initialAdd()", globalBedStatusMstVO_p.getStrErrorMsg());
			globalBedStatusMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
		}
		return retValue;
	}
/**
 * invoke GlobalBedStatusMstDAO's modifyQuery()
 * @param chk1
 * @param globalBedStatusMstVO_p
 * @throws Exception
 */
	public void modifyRecord(String chk1, GlobalBedStatusMstVO globalBedStatusMstVO_p) throws Exception {

		try {

			GlobalBedStatusMstDAO.modifyQuery(chk1, globalBedStatusMstVO_p);
		} catch (Exception e) {
			 globalBedStatusMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Bed Status Master ", "GlobalBedStatusMstBO-->modifyRecord()", globalBedStatusMstVO_p.getStrErrorMsg());
			   globalBedStatusMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}
	/**
	 * Invoke GlobalBedStatusMstDAO's updateQuery()
	 * @param chk1
	 * @param globalBedStatusMstVO_p
	 * @return
	 */

	public String updateRecord(String chk1, GlobalBedStatusMstVO globalBedStatusMstVO_p) {
		String strtarget = "";
		boolean fretValue;
		boolean fretValue1;
		String strmsgText = "";
		try {
		//	GlobalBedStatusMstBO bo = new GlobalBedStatusMstBO();
			fretValue1 = this.initialUpdate(chk1, globalBedStatusMstVO_p);// Check For Record
														// Modification

			if (!fretValue1) {
				String errmsg = "Record can not be modified due To Duplicacy !";
				globalBedStatusMstVO_p.setStrErrorMsg(errmsg);
				strtarget = "modify";
			} else {
				fretValue = GlobalBedStatusMstDAO.updateQuery(chk1, globalBedStatusMstVO_p);
				if (!fretValue) {

					String errmsg = "Record not modified successfully!";
					globalBedStatusMstVO_p.setStrErrorMsg(errmsg);
					strtarget = "modify";
				} else {

					strtarget = "list";
				}
			}
		} catch (Exception e) {
			fretValue = false;
			strmsgText = "ipd.masters.GlobalBedStatusMstBO.InsertRecord(globalBedStatusMstVO_p) --> "
					+ e.getMessage();
			 globalBedStatusMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Bed Status Master ", "GlobalBedStatusMstBO-->updateRecord()", globalBedStatusMstVO_p.getStrErrorMsg());
			   globalBedStatusMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			new HisException("IPD", "Bed Status Master.Update", strmsgText);
		}

		return strtarget;
	}
/**
 * invoke GlobalBedStatusMstDAO's initialUpdateQuery()
 * @param chk
 * @param globalBedStatusMstVO_p
 * @return
 * @throws Exception
 */
	public boolean initialUpdate(String chk, GlobalBedStatusMstVO globalBedStatusMstVO_p)
			throws Exception// Check Data in Database for Modification
	{
		boolean retvalue = true;
		try {
			retvalue = GlobalBedStatusMstDAO.initialUpdateQuery(chk, globalBedStatusMstVO_p);
		} catch (Exception e) {
			 globalBedStatusMstVO_p.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Bed Status Master ", "GlobalBedStatusMstBO-->initialUpdate()", globalBedStatusMstVO_p.getStrErrorMsg());
			   globalBedStatusMstVO_p.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

		return retvalue;

	}

}