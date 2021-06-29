package ipd.masters.bo;

import ipd.masters.dao.BedStatusMstDAO;
import ipd.masters.vo.BedStatusMstVO;
import hisglobal.exceptions.HisException;

public class BedStatusMstBO {
	
	/**
	 * invoke BedStatusMstDAO's getGlobalBedStatus()
	 * @param chk1
	 * @param vo
	 * @throws Exception
	 */
		public void getGlobalBedStatus(BedStatusMstVO vo) throws Exception {

			try {

				BedStatusMstDAO.getGlobalBedStatus(vo);
			} catch (Exception e) {
				 vo.setStrErrorMsg(e.getMessage());
				   HisException eObj = new HisException("IPD-->Bed Status Master ", "BedStatusMstBO-->modifyRecord()", vo.getStrErrorMsg());
				   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				   eObj = null;
			}

		}
	
	
	/**
	 * Invoke BedStatusMstDAO' s insetrQuery()
	 * @param vo
	 * @return
	 */
		public String InsertRecord(BedStatusMstVO vo) 
		{
			String strTarget = "add";
			boolean fretValue = true;
			String strErrmsg = "";
			String strmsgText = "";
			String strMsg = ""; 
			String strWarning = "";
	
			try 
			{
				 //fretValue = this.initialAdd(vo); // Check for Record Duplicacy				 
				 if (fretValue) 
				 {
					fretValue = BedStatusMstDAO.insertQuery(vo);
					if (fretValue) 
					{
						strMsg = "Record Saved Successfully!";
						vo.setStrMsg(strMsg);
					}
				 }
				 else
				 {
					   strWarning = "Record Not Saved!Data Already Exist!!";
					   vo.setStrWarning(strWarning); 
				 }			   
			} 
			catch (Exception e) 
			{
				vo.setStrErrorMsg(e.getMessage());
				HisException eObj = new HisException("ADT-->Bed Status Master ", "BedStatusMstBO-->InsertRecord()", vo.getStrErrorMsg());
				vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				eObj = null;
				fretValue = false;
				strmsgText = "ipd.masters.BedStatusMstBO.InsertRecord(vo) --> "+ e.getMessage();
				new HisException("IPD", "Bed Status Master.Add", strmsgText);
				
				strErrmsg = "Record Not Saved!!!";
				vo.setStrErrorMsg(strErrmsg); 
			}			
			return strTarget;
	}
/**
 * Invoke BedStatusMstDAO's initialAddQuery()
 * @param vo
 * @return
 * @throws Exception
 */
	public boolean initialAdd(BedStatusMstVO vo) throws Exception// Check for  Existing Duplicate Record
	{
		boolean retValue = false;
		try 
		{
			retValue = BedStatusMstDAO.initialAddQuery(vo);
		} 
		catch (Exception e) 
		{
			   vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("ADT-->Bed Status Master ", "BedStatusMstBO-->initialAdd()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return retValue;

	}
/**
 * invoke BedStatusMstDAO's modifyQuery()
 * @param chk1
 * @param vo
 * @throws Exception
 */
	public void modifyRecord(String chk1, BedStatusMstVO vo) throws Exception {

		try {

			BedStatusMstDAO.modifyQuery(chk1, vo);
		} catch (Exception e) {
			 vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Bed Status Master ", "BedStatusMstBO-->modifyRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}
	/**
	 * Invoke BedStatusMstDAO's updateQuery()
	 * @param chk1
	 * @param vo
	 * @return
	 */

	public String updateRecord(String chk1, BedStatusMstVO vo) 
	{
		String strtarget = "";
		boolean fretValue;
		boolean fretValue1;
		String strmsgText = "";
		try {
			fretValue1 = this.initialUpdate(chk1, vo);// Check For Record Modification

			if (!fretValue1) 
			{
				String errmsg = "Record Not Saved!Data Already Exists!";
				vo.setStrErrorMsg(errmsg);
				strtarget = "modify";
			} 
			else 
			{
				fretValue = BedStatusMstDAO.updateQuery(chk1, vo);
				if (!fretValue) 
				{
					String errmsg = "Record Not Modified!";
					vo.setStrErrorMsg(errmsg);
					strtarget = "modify";
				} 
				else 
				{
					strtarget = "list";
				}
			}
		} 
		catch (Exception e) 
		{
			fretValue = false;
			strmsgText = "ipd.masters.BedStatusMstBO.InsertRecord(vo) --> "+ e.getMessage();
			vo.setStrErrorMsg(e.getMessage());
			HisException eObj = new HisException("ADT-->Bed Status Master ", "BedStatusMstBO-->updateRecord()", vo.getStrErrorMsg());
			vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			eObj = null;
			new HisException("IPD", "Bed Status Master.Update", strmsgText);
		}

		return strtarget;
	}
/**
 * invoke BedStatusMstDAO's initialUpdateQuery()
 * @param chk
 * @param vo
 * @return
 * @throws Exception
 */
	public boolean initialUpdate(String chk, BedStatusMstVO vo)
			throws Exception// Check Data in Database for Modification
	{
		boolean retvalue = true;
		try {
			retvalue = BedStatusMstDAO.initialUpdateQuery(chk, vo);
		} catch (Exception e) {
			 vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("ADT-->Bed Status Master ", "BedStatusMstBO-->initialUpdate()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

		return retvalue;

	}

}