package ipd.masters.bo;

import ipd.masters.dao.GlobalWardTypeMstDAO;
import ipd.masters.vo.GlobalWardTypeMstVO;
import hisglobal.exceptions.HisException;

public class GlobalWardTypeMstBO {
	/**
	 * 
	 * @param globalWardTypeMstVO_p
	 * @return
	 * First it will check duplicate record is there or not,
	 * If duplicate record is there then it display a msg "Record Not Saved!Data Already Exist!!"
	 * If there is no duplicate record then it saved the record
	 * And get back to the list page
	 * But if some error is occurs then it will show the msg "Record Not Saved!!!"
	 */
	public String InsertRecord(GlobalWardTypeMstVO globalWardTypeMstVO_p)  {
		String target = "";
		boolean retvalue;
		boolean retvalue1;
		String errmsg = "";
		String msg ="";
		try{
		retvalue1 = this.initialAdd(globalWardTypeMstVO_p);// check for duplicate record
		if (!retvalue1) {
			errmsg = "Record already exist!";
			globalWardTypeMstVO_p.setStrerrorMsg(errmsg);
			target = "add";
		} else{
			retvalue = GlobalWardTypeMstDAO.insertQuery(globalWardTypeMstVO_p);
			if (!retvalue) {
				errmsg = "Record not saved successfully!";
				globalWardTypeMstVO_p.setStrerrorMsg(errmsg);
				target = "add";
			} else {
				msg ="Record Saved Successfully!";
				globalWardTypeMstVO_p.setMsg(msg);
				target = "add";
			}
		}
		}catch(Exception e){
			globalWardTypeMstVO_p.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Ward Type", "BOGlobalWardTypeMst-->InsertRecord()", globalWardTypeMstVO_p.getStrerrorMsg());
			   globalWardTypeMstVO_p.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return target;
	}

	/**
	 * 
	 * @param globalWardTypeMstVO_p
	 * @return
	 * Check for Duplicacy Record
	 */
	public boolean initialAdd(GlobalWardTypeMstVO globalWardTypeMstVO_p)throws Exception// check for dupliacte record
	{
		boolean retvalue =false;
		try{
			retvalue = GlobalWardTypeMstDAO.initialAddQuery(globalWardTypeMstVO_p);
		}catch(Exception e){
			globalWardTypeMstVO_p.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Ward Type", "GlobalWardTypeMstBO-->initialAdd()", globalWardTypeMstVO_p.getStrerrorMsg());
			   globalWardTypeMstVO_p.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
	  }
		return retvalue;
	}

	/**
	 * 
	 * @param chk1
	 * @param globalWardTypeMstVO_p
	 * To modify the record 
	 */
	public void modifyRecord(String chk1, GlobalWardTypeMstVO globalWardTypeMstVO_p)throws Exception  {
		try{
		GlobalWardTypeMstDAO.modifyQuery(chk1, globalWardTypeMstVO_p);
		}catch(Exception e){
			globalWardTypeMstVO_p.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Ward Type", "GlobalWardTypeMstBO-->modifyRecord()", globalWardTypeMstVO_p.getStrerrorMsg());
			   globalWardTypeMstVO_p.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
	}

	/**
	 * 
	 * @param strChk1_p
	 * @param globalWardTypeMstVO_p
	 * @return
	 * After modification, updates all the data where the changes has been done
	 * And saved the data in data base.
	 */
	public String updateRecord(String strChk1_p, GlobalWardTypeMstVO globalWardTypeMstVO_p)throws Exception
			 {
		String target = "";
		boolean retvalue;
		boolean retvalue1;
		
		try{
		retvalue1 = this.initialUpdate(strChk1_p, globalWardTypeMstVO_p);// strChk_p for modii

		if (!retvalue1) {
			String errmsg = "Record already Exist!";
			globalWardTypeMstVO_p.setStrerrorMsg(errmsg);
			target = "modify";
		} else {
			retvalue = GlobalWardTypeMstDAO.updateQuery(strChk1_p, globalWardTypeMstVO_p);
			if (!retvalue) {

				String errmsg = "Record not modified successfully!";
				globalWardTypeMstVO_p.setStrerrorMsg(errmsg);
				target = "modify";
			} else {

				target = "list";
			}
		}
		}catch(Exception e){
			globalWardTypeMstVO_p.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Ward Type", "GlobalWardTypeMstBO-->initialRecord()", globalWardTypeMstVO_p.getStrerrorMsg());
			   globalWardTypeMstVO_p.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return target;
	}

	/**
	 * 
	 * @param strChk_p
	 * @param globalWardTypeMstVO_p
	 * @return
	 * For initial update
	 */
	public boolean initialUpdate(String strChk_p, GlobalWardTypeMstVO globalWardTypeMstVO_p)throws Exception// check for
																// modification
	{
		boolean retvalue=false;
		try{
		retvalue = GlobalWardTypeMstDAO.initialUpdateQuery(strChk_p, globalWardTypeMstVO_p);
		}catch(Exception e){
			globalWardTypeMstVO_p.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Global Ward Type", "GlobalWardTypeMstBO-->initialUpdate()", globalWardTypeMstVO_p.getStrerrorMsg());
			   globalWardTypeMstVO_p.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return retvalue;

	}

}