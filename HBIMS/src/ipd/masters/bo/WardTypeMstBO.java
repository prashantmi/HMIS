package ipd.masters.bo;

import ipd.masters.dao.WardTypeMstDAO;
import ipd.masters.vo.WardTypeMstVO;
import hisglobal.exceptions.HisException;

public class WardTypeMstBO {
	/**
	 * 
	 * @param vo
	 * @return
	 * First it will check duplicate record is there or not,
	 * If duplicate record is there then it display a msg "Record Not Saved!Data Already Exist!!"
	 * If there is no duplicate record then it saved the record
	 * And get back to the list page
	 * But if some error is occurs then it will show the msg "Record Not Saved!!!"
	 */
	public String InsertRecord(WardTypeMstVO vo)  {
		String target = "";
		boolean retvalue;
		boolean retvalue1;
		String errmsg = "";
		String msg ="";
		try{
		retvalue1 = this.initialAdd(vo);// check for duplicate record
		if (!retvalue1) {
			errmsg = "Record already exist!";
			vo.setStrerrorMsg(errmsg);
			target = "add";
		} else{
			retvalue = WardTypeMstDAO.insertQuery(vo);
			if (!retvalue) {
				errmsg = "Record not saved successfully!";
				vo.setStrerrorMsg(errmsg);
				target = "add";
			} else {
				msg ="Record Saved Successfully!";
				vo.setMsg(msg);
				target = "add";
			}
		}
		}catch(Exception e){
			vo.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Ward Type", "WardTypeMstBO-->InsertRecord()", vo.getStrerrorMsg());
			   vo.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return target;
	}

	/**
	 * 
	 * @param vo
	 * @return
	 * Check for Duplicacy Record
	 */
	public boolean initialAdd(WardTypeMstVO vo)throws Exception// check for dupliacte record
	{
		boolean retvalue =false;
		try{
			retvalue = WardTypeMstDAO.initialAddQuery(vo);
		}catch(Exception e){
			vo.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Ward Type", "WardTypeMstBO-->initialAdd()", vo.getStrerrorMsg());
			   vo.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
	  }
		return retvalue;
	}

	/**
	 * 
	 * @param chk1
	 * @param vo
	 * To modify the record 
	 */
	public void modifyRecord(String chk1, WardTypeMstVO vo)throws Exception  {
		try{
		WardTypeMstDAO.modifyQuery(chk1, vo);
		}catch(Exception e){
			vo.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Ward Type", "WardTypeMstBO-->modifyRecord()", vo.getStrerrorMsg());
			   vo.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
	}

	/**
	 * 
	 * @param chk1
	 * @param vo
	 * @return
	 * After modification, updates all the data where the changes has been done
	 * And saved the data in data base.
	 */
	public String updateRecord(String chk1, WardTypeMstVO vo)throws Exception
			 {
		String target = "";
		boolean retvalue;
		boolean retvalue1;
		
		try{
		retvalue1 = this.initialUpdate(chk1, vo);// chk for modii

		if (!retvalue1) {
			String errmsg = "Record already Exist!";
			vo.setStrerrorMsg(errmsg);
			target = "modify";
		} else {
			retvalue = WardTypeMstDAO.updateQuery(chk1, vo);
			if (!retvalue) {

				String errmsg = "Record not modified successfully!";
				vo.setStrerrorMsg(errmsg);
				target = "modify";
			} else {

				target = "list";
			}
		}
		}catch(Exception e){
			vo.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Ward Type", "WardTypeMstBO-->updateRecord()", vo.getStrerrorMsg());
			   vo.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return target;
	}

	/**
	 * 
	 * @param chk
	 * @param vo
	 * @return
	 * For initial update
	 */
	public boolean initialUpdate(String chk, WardTypeMstVO vo)throws Exception// check for
																// modification
	{
		boolean retvalue=false;
		try{
		retvalue = WardTypeMstDAO.initialUpdateQuery(chk, vo);
		}catch(Exception e){
			vo.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Ward Type", "WardTypeMstBO-->initialUpdate()", vo.getStrerrorMsg());
			   vo.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return retvalue;

	}

	/**
	 * 
	 * @param vo
	 * @return
	 * to get global ward type name
	 */
	public boolean getGlobalWardType(WardTypeMstVO vo)throws Exception
	{
		boolean retvalue =false;
		try{
			retvalue = WardTypeMstDAO.getGlobalWardType(vo);
		}catch(Exception e){
			vo.setStrerrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Ward Type", "WardTypeMstBO-->getGlobalWardType()", vo.getStrerrorMsg());
			   vo.setStrerrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
	  }
		return retvalue;
	}
}