package ipd.masters.bo;

import ipd.masters.dao.WardDiseaseTypeMstDAO;
import ipd.masters.vo.WardDiseaseTypeMstVO;
import hisglobal.exceptions.HisException;

public class WardDiseaseTypeMstBO {
	
	/**
	 * First it will check duplicate record is there or not,<br>
	 * If duplicate record is there then it display a message "Record Not Saved!Data Already Exist!!"<br>
	 * If there is no duplicate record then it saved the record<br>
	 * And get back to the list page<br>
	 * But if some error is occurs then it will show the message "Record Not Saved!!!"<br>
	 * 
	 * @param vo - FormBean Object
	 * @return true- Record Saved Successfully!!
	 * false- Record Not Saved!!
	 */
	public String InsertRecord(WardDiseaseTypeMstVO vo) {
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
				fretValue = WardDiseaseTypeMstDAO.insertQuery(vo);
		
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
			   HisException eObj = new HisException("IPD-->Ward DiseaseType Master", "BOWardDiseaseTypeMst-->InsertRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			strmsgText = "ipd.masters.BOWardDiseaseTypeMst.InsertRecord(vo) --> "
					+ e.getMessage();
			new HisException("IPD", "Ward DiseaseType Master.Add", strmsgText);
			
			strErrmsg = "Record Not Saved!!!";
			vo.setStrErrorMsg(strErrmsg); 
		}
		return strTarget;
	}
	
	/**
	 * Check for Duplicate Record
	 * 
	 * @param vo - FormBean Object
	 * @return true- Record not saved,already exist!!
	 * false - record saved
	 * @throws Exception
	 */
	public boolean initialAdd(WardDiseaseTypeMstVO vo) 
	throws Exception							// Check for Existing Duplicate Record
	  {
		boolean retValue = false;
		try {
			retValue = WardDiseaseTypeMstDAO.initialAddQuery(vo);

		} catch (Exception e) {
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Ward DiseaseType Master", "BOWardDiseaseTypeMst-->initialAdd()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return retValue;
	  }
	
	/**
	 * Retrieve the content from the record to modify it.
	 * 
	 * @param chk1 - Primary Keys Concatenated with '@'.
	 * @param vo - FormBean Object
	 * @throws Exception
	 */	
	public void modifyRecord(String chk1, WardDiseaseTypeMstVO vo) throws Exception {

		try {

			WardDiseaseTypeMstDAO.modifyQuery(chk1, vo);
		} catch (Exception e) {
			vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Ward DiseaseType Master", "BOWardDiseaseTypeMst-->modifyRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}
	
	/**
	 * Update method returns true if Record Updated Successfully false if Record
	 * Not Updated Successfully
	 * 
	 * @param chk1 - Primary Keys Concatenated with '@'.
	 * @param vo - Form Object of the Current Master
	 * @return boolean Value
	 */
	public String updateRecord(String chk1, WardDiseaseTypeMstVO vo) {
		String strtarget = "";
		boolean fretValue;
		boolean fretValue1;
	//	String strmsgText = "";
		try {
		
			fretValue1 = this.initialUpdate(chk1, vo);// Check For Record
														// Modification

			if (!fretValue1) {
				String errmsg = "Record can not be modified due To Duplicacy !";
				vo.setStrErrorMsg(errmsg);
				strtarget = "modify";
			} else {
				fretValue = WardDiseaseTypeMstDAO.updateQuery(chk1, vo);
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
			   HisException eObj = new HisException("IPD-->Ward DiseaseType Master", "BOWardDiseaseTypeMst-->updateRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

		return strtarget;
	}
	
	/**
	 * To initial Update
	 * 
	 * @param chk - Primary Keys Concatenated with '@'.
	 * @param vo- FormBean Object
	 * @return - boolean value either it is true or false
	 * @throws Exception
	 */
	public boolean initialUpdate(String chk, WardDiseaseTypeMstVO vo)
	throws Exception             // Check Data in Database for Modification
	{
			boolean retvalue = true;
			try 
			{
				retvalue = WardDiseaseTypeMstDAO.initialUpdateQuery(chk, vo);
			} 
			catch (Exception e) {
				vo.setStrErrorMsg(e.getMessage());
				   HisException eObj = new HisException("IPD-->Ward DiseaseType Master", "BOWardDiseaseTypeMst-->initialUpdate()", vo.getStrErrorMsg());
				   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
				   eObj = null;
			}

			return retvalue;

	}




}
