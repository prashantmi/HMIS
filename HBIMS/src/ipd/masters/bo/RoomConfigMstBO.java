package ipd.masters.bo;

import ipd.masters.controller.hlp.RoomConfigMstHLP;
import ipd.masters.dao.RoomConfigMstDAO;
import ipd.masters.vo.RoomConfigMstVO;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

public class RoomConfigMstBO {
	
	/**
	 * 
	 */
	public void getPropertyComboValues(RoomConfigMstVO vo) {
		String strErrmsg = "";
		
		String msgText = "";
		
		HisUtil util = null;
		String cmb =  "";
	try {
		
		util = new HisUtil("IPD","BORoomConfigMst");
		RoomConfigMstDAO.getPropertyComboValues(vo);
		if (vo.getPropertyComboWs() != null
				|| vo.getPropertyComboWs().size() > 0) {
			cmb = util.getOptionValue(vo.getPropertyComboWs(),"0", "0^Select Value", false);
		} else {
			cmb = "<option value='0'>Select Value</option>";
		}
		vo.setStrPropertyComboValues(cmb);
		
		
		} catch (Exception e) {
			
			vo.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Config Master", "BORoomConfigMst-->InsertRecord()", vo.getStrErr());
			   vo.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			strErrmsg = "Record Not Saved!!!";
			vo.setStrerrorMsg(strErrmsg); 
			msgText = "ipd.masters.BORoomConfigMst.InsertRecord(vo) --> "
					+ e.getMessage();
			new HisException("IPD", "Room Config Master.Add", msgText);
		}

		
	}
    
	/**
	 * First it will check duplicate record is there or not,<br>
	 * If duplicate record is there then it display a message "Record Not Saved!Data Already Exist!!"<br>
	 * If there is no duplicate record then it saved the record<br>
	 * And get back to the list page<br>
	 * But if some error is occurs then it will show the message "Record Not Saved!!!"<br>
	 * @param vo - FormBean Object
	 * @return true- Record Saved Successfully!!
	 * false- Record Not Saved!!
	 */
	public String InsertRecord(RoomConfigMstVO vo) {
	
		String strTarget = "add";
		boolean fretValue = true;
		String strErrmsg = "";
		String strMsg = ""; 
		String msgText = "";
		String strWarning = "";
	try {
		//System.out.println("vo.getStrPropertyId().length"+vo.getStrPropertyId().length);
		//System.out.println("vo.getStrPropertyId()[0]"+vo.getStrPropertyId()[0]);
			fretValue = this.initialAdd(vo); // Check for Record Duplicacy

			if (fretValue) {
				
				fretValue = RoomConfigMstDAO.insertQuery(vo);
				if (fretValue)
				{
					strMsg = "Record saved successfully!";
					vo.setStrMsg(strMsg);
				}
			}
			else
			{
				   //strWarning = "Record Not Inserted! Same Room Description  Exist For This Floor!!";
					strWarning = "Record Not Inserted! Same Room Description  Exist !!";
				   vo.setStrWarning(strWarning); 
			}
		} catch (Exception e) {
			fretValue = false;
			vo.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Config Master", "BORoomConfigMst-->InsertRecord()", vo.getStrErr());
			   vo.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			strErrmsg = "Record Not Saved!!!";
			vo.setStrerrorMsg(strErrmsg); 
			msgText = "ipd.masters.BORoomConfigMst.InsertRecord(vo) --> "
					+ e.getMessage();
			new HisException("IPD", "Room Config Master.Add", msgText);
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
	public boolean initialAdd(RoomConfigMstVO vo) throws Exception // Check for Existing Duplicate Record
	{
		boolean fretValue = true;

		try {
			fretValue = RoomConfigMstDAO.initialAddQuery(vo);
			
		} catch (Exception e) {
			vo.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Config Master", "BORoomConfigMst-->initialAdd()", vo.getStrErr());
			   vo.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fretValue;

	}

	/**
	 * Retrieve the content from the record to modify it.
	 * 
	 * @param strChk1 - Primary Keys Concatenated with '@'.
	 * @param vo - FormBean Object
	 * @throws Exception
	 */
	public void modifyRecord(String strChk1, RoomConfigMstVO vo)
			throws Exception {
		String strPersistProperty = "";
		try {
			RoomConfigMstDAO.modifyQuery(strChk1, vo);
			RoomConfigMstDAO.getPropertyComboValues(vo);
			strPersistProperty = RoomConfigMstHLP.getSelectedPropertyComboValues(vo);
			this.getPropertyComboValues(vo);
			vo.setStrPersistProperty(strPersistProperty);
			
		} catch (Exception e) {
			vo.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Config Master", "BORoomConfigMst-->modifyRecord()", vo.getStrErr());
			   vo.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}
	
	
	/**
	 * For Combo Query
	 * @param strVal - Variable of String using for combo query
	 * @param strVal1 - Second Variable of String using for combo query
	 * @param vo - FormBean Object
	 * @throws Exception
	 */
	public void qeryForCombo(String strVal, String strVal1, RoomConfigMstVO vo)
			throws Exception {
		try {
			RoomConfigMstDAO.comboQuery(strVal, strVal1, vo);
		} catch (Exception e) {
			vo.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Config Master", "BORoomConfigMst-->qeryForCombo()", vo.getStrErr());
			   vo.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
	}
   
	/**
	 * Update method returns true if Record Updated Successfully false if Record
	 * Not Updated Successfully
	 * 
	 * @param strChk1 - Primary Keys Concatenated with '@'.
	 * @param vo - Form Object of the Current Master
	 * @return boolean Value
	 */
	public String updateRecord(String strChk1, RoomConfigMstVO vo) {
		String strTarget = "";
		boolean fretValue, fretValue1;
	
		//String strerrorMsg = "";
		String strWarning = "";


		try {
		  
			fretValue1 = this.initialUpdate(strChk1, vo);
		  	if (!fretValue1) {
		  		System.out.println("vo.getStrMsg()"+vo.getStrMsg());
		  		if(vo.getStrMsg().equals("exception"))
		  		{
		  			System.out.println("vo.getStrErr()-"+vo.getStrErr());
		  			throw new Exception(vo.getStrErr());
		  		}
		  		else{
		  			 strWarning = "Record Not Updated! Same Room Description Exist !!";
					 vo.setStrWarning(strWarning); 
					 strTarget = "modify";
		  		}
		  		
			
			} else {
				fretValue = RoomConfigMstDAO.updateQuery(strChk1, vo);
				 
				if (!fretValue) {
   
					String errmsg = "Record not modified successfully!";
					 vo.setStrErr(errmsg);
					strTarget = "modify";
				} else {

					strTarget = "list";
				}
			}
		} catch (Exception e) {
			System.out.println("catch vo err-"+vo.getStrErr());
			fretValue = false;
			vo.setStrMsg("");
			vo.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Config Master", "BORoomConfigMst-->updateRecord()", vo.getStrErr());
			   vo.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			   strTarget = "modify";
			   
		}
		return strTarget;
	}
  
	/**
	 * To initial Update
	 * 
	 * @param strChk - Primary Keys Concatenated with '@'.
	 * @param vo- FormBean Object
	 * @return - boolean value either it is true or false
	 * @throws Exception
	 */
	public boolean initialUpdate(String strChk, RoomConfigMstVO vo)
			throws Exception // Check Data in Database for Modification
	{
		boolean fretValue = true;
		try {
			fretValue = RoomConfigMstDAO.initialUpdateQuery(strChk, vo); //Move to DAORoomConfig
		} catch (Exception e) {
			vo.setStrErr(e.getMessage());
			   HisException eObj = new HisException("IPD-->Room Config Master", "BORoomConfigMst-->initialUpdate()", vo.getStrErr());
			   vo.setStrErr("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fretValue;

	}
	
/*
	/////////////////////////////recordModification(chk,vo)/////////////////////////////
	public String recordModification(String strChk1, VORoomConfigMst vo) {
		String strTarget = "list";
		boolean fretValue = true;
		String strErrmsg = "";
		String strmsgText = "";
		String strMsg = ""; 
		String strWarning = "";

		try {
			 fretValue = this.initialModifcationUpdate(strChk1, vo); // Check for Record Duplicacy
		   if (fretValue) 
			   {
				fretValue = DAORoomConfigMst.updateModificationQuery(strChk1,vo);
				if (fretValue) {
					strMsg = "Record saved successfully!";
					vo.setStrMsg(strMsg);
				}
			}
		   else
		   {
			  strWarning = "Effective FROM Date (" + vo.getStrEffectiveTo()
				+ ") exists between the "
				+ "effective From Date and effective To date of "
				+ "existing records "+"Plz go back to List Page & choose Record Again";
						     
			   vo.setStrWarning(strWarning); 
               strTarget = "modify";
		   }
		   
		} catch (Exception e) {
			fretValue = false;
	        strTarget = "modify";
			strmsgText = "ipd.masters.BORoomConfigMst.InsertRecord(vo) --> "
					+ e.getMessage();
			new HisException("IPD", "RoomConfigMaster.Modify", strmsgText);
			
			strErrmsg = "Record Not Saved!!!";
			vo.setStrerrorMsg(strErrmsg); 
		}

		
		return strTarget;
	}
	
	
	
	///////////////////////////////////////////////////////////////////////
	public boolean initialModifcationUpdate(String strChk, VORoomConfigMst vo)
			throws Exception // Check Data in Database for Modification
	{
		boolean fretValue = true;
		try {
//			System.out.println("Inside initModificaton==>"+strChk);
			fretValue = DAORoomConfigMst.initialModifcationUpdateQuery(strChk,
					vo); // ==>Move to DAORoomConfig
		} catch (Exception e) {
			throw new Exception(
					"BORoomConfigMst.initialModificationUpdate() -->"
							+ e.getMessage());
		}
		return fretValue;

	}*/
	// ///////////////////////////////////////////////////////////////////

}