package ipd.masters.bo;

import ipd.masters.dao.PropertyMstDAO;
import ipd.masters.vo.PropertyMstVO;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;

public class PropertyMstBO {
	/**
	 * To ge the current date
	 * @param vo
	 */
	public void getCurrentdate(PropertyMstVO vo){
		HisUtil hisutil = null;
		String ctDate="";
		try{
			hisutil = new HisUtil("ipd", "CNTPropertyMst");
			ctDate = hisutil.getASDate("dd-MMM-yyyy");
			vo.setStrCtDate(ctDate);
		}catch(Exception e){
			 HisException eObj = new HisException("IPD-->Property Master", "BOPropertyMst-->getCurrentdate()", e.getMessage());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
	}
	/**
	 * 
	 * @param vo
	 * @return
	 * First it will check duplicate record is there or not,
	 * If duplicate record is there then it display a msg "Record Not Saved!Data Already Exist!!"
	 * If there is no duplicate record then it saved the record
	 * And get back to the add page 
	 * But if some error is occurs then it will show the msg "Record Not Saved successfully!!!"
	 */
	public String InsertRecord(PropertyMstVO vo,HttpServletRequest request)  {
		String target = "";
		boolean retvalue;
		boolean retvalue1;
		String errmsg = "";
		String msg ="";
		try{
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		retvalue1 = this.initialAdd(vo);// check for duplicate record
		if (!retvalue1) {
			errmsg = "Record Not Saved!Data Already Exist!!";
			vo.setStrWarnings(errmsg);
			target = "add";
		} else{
			retvalue = PropertyMstDAO.insertQuery(vo);
			if (!retvalue) {
				errmsg = "Record not saved successfully!";
				vo.setStrErrorMsg(errmsg);
				target = "add";
			} else {
				msg ="Record Saved Successfully!";
				vo.setStrMsg(msg);
				target = "add";
			}
		}
		}catch(Exception e){
			
			   HisException eObj = new HisException("IPD-->Property Master", "BOPropertyMst-->InsertRecord()", e.getMessage());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return target;
	}
	/**
	 * 
	 * @param vo
	 * @return
	 * Check for Duplicacy Record during insertion
	 */
	public boolean initialAdd(PropertyMstVO vo)throws Exception// check for dupliacte record
	{
		boolean retvalue =false;
		try{
			retvalue = PropertyMstDAO.initialAddQuery(vo);
		}catch(Exception e){
			 HisException eObj = new HisException("IPD-->Property Master", "BOPropertyMst-->initialAdd()", e.getMessage());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
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
	public void modifyRecord(String chk1, PropertyMstVO vo,HttpServletRequest request)throws Exception  {
		try{
			vo.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrChk(request.getParameter("chk"));
			PropertyMstDAO.modifyQuery(chk1, vo);
		}catch(Exception e){
			HisException eObj = new HisException("IPD-->Property Master", "BOPropertyMst-->modifyRecord()", e.getMessage());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
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
	public String updateRecord(String chk1, PropertyMstVO vo,HttpServletRequest request)throws Exception
			 {
		
		String target = "";
		boolean retvalue;
		boolean retvalue1;
		
		try{
			vo.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			vo.setStrLastModifiedSeatId(request.getSession().getAttribute("SEATID").toString());
			retvalue1 = this.initialUpdate(chk1, vo);// chk for modii

		if (!retvalue1) {
			String errmsg = "Record Not Saved!Data Already Exist!!";
			vo.setStrWarnings(errmsg);
			target = "modify";
		} else {
			retvalue = PropertyMstDAO.updateQuery(chk1, vo);
			if (!retvalue) {

				String errmsg = "Record not modified successfully!";
				vo.setStrErrorMsg(errmsg);
				target = "modify";
			} else {

				target = "list";
			}
		}
		}catch(Exception e){
			HisException eObj = new HisException("IPD-->Property Master", "BOPropertyMst-->updateRecord()", e.getMessage());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		
		return target;
	}

	/**
	 * 
	 * @param chk
	 * @param vo
	 * @return
	 * Check duplicate value during Updation
	 * */
	public boolean initialUpdate(String chk, PropertyMstVO vo)throws Exception // check for
																               // modification
	{
		
		boolean retvalue=false;
		try{
		retvalue = PropertyMstDAO.initialUpdateQuery(chk, vo);
		}catch(Exception e){
			HisException eObj = new HisException("IPD-->Property Master", "BOPropertyMst-->initialUpdate()", e.getMessage());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return retvalue;

	}
}
