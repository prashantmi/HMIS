package ipd.masters.bo;

import ipd.masters.dao.BedTypeMstDAO;
import ipd.masters.vo.BedTypeMstVO;
import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class BedTypeMstBO {

	/*
	 * private String qryStr = ""; private String moduleName = ""; private
	 * String fileName = ""; private int qryIndex = 0;
	 */

	private String strQry = "";
	private String strModuleName = "";
	private String strFileName = "";
	// private int nQryIndex = 0;

	// error message
	private String strErrMsg = "";

	// error message
	// private String errMsg = "";
	/**
	 * Check for Record Duplicacy
	 */

	// ///////////////////////////TEST//////////////////////////////////////////
	/**
	 * Invoked BedTypeMstDAO's function->getGlobalBedType
	 * 
	 * @param vo
	 * @throws Exception
	 */

	public void getGlobalBedType(BedTypeMstVO vo) throws Exception {
		try {
			BedTypeMstDAO.getGlobalBedType(vo);
		} catch (Exception e) {
			 vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Bed Type Master", "BedTypeMstBO-->getGlobalBedType()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}
	
	
	public String InsertRecord(BedTypeMstVO vo) {
		String strTarget = "add";
		boolean fretValue = true;
		String strErrmsg = "";
		// String strmsgText = "";
		String strMsg = "";
		// String strErrmsg = "Record saved successfully!";
		String msgText = "";
		String strWarning = "";

		try {
			fretValue = this.initialAdd(vo); // 
			// System.out.println("Inside fretValue=>"+fretValue);
			if (fretValue) {
				fretValue = BedTypeMstDAO.insertQuery(vo);
				if (fretValue) {
					strMsg = "Record saved successfully!";
					vo.setStrMsg(strMsg);
				}
			} else {
				strWarning = "Record Not Saved!Data Already Exist!!";
				vo.setStrWarning(strWarning);
			}
		} catch (Exception e) {
			   vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Bed Type Master", "BedTypeMstBO-->InsertRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			fretValue = false;
			msgText = "ipd.masters.BedTypeMstBO.InsertRecord(vo) --> "
					+ e.getMessage();
			new HisException("IPD", "BedTypeMaster.Add", msgText);
			strErrmsg = "Record Not Saved!!!";
			vo.setStrErrorMsg(strErrmsg);
		}

		return strTarget;
	}

	/**
	 * InvokedDAOBedTypeMst's function->initialAddQuery
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean initialAdd(BedTypeMstVO vo) throws Exception// Check for
	// Existing
	// Duplicate
	// Record
	{
		boolean fretValue = false;

		try {
			fretValue = BedTypeMstDAO.initialAddQuery(vo);
		} catch (Exception e) {
			 vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Bed Type Master", "BedTypeMstBO-->initialAdd()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fretValue;

	}

	/**
	 * InvokedDAOBedTypeMst's function->modifyQuery
	 * 
	 * @param chk1
	 * @param vo
	 * @throws Exception
	 */

	public void modifyRecord(String chk1, BedTypeMstVO vo) throws Exception {
		try {
			BedTypeMstDAO.modifyQuery(chk1, vo);
		} catch (Exception e) {
			 vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Bed Type Master", "BedTypeMstBO-->modifyRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}

	}

	/**
	 * This function invoke the initialUpdate of this class
	 * 
	 * @param chk1
	 * @param vo
	 * @return
	 */
	public String updateRecord(String chk1, BedTypeMstVO vo) {

		String strtarget = "";
		boolean fretValue;
		boolean fretValue1;
		String strmsgText = "";
		// BedTypeMstBO bo = new BedTypeMstBO();
		try {
			fretValue1 = this.initialUpdate(chk1, vo);// Check For Record
			// Modification

			if (!fretValue1) {
				String errmsg = "Record can not be modified!!Data Already Exist!!";
				vo.setStrErrorMsg(errmsg);
				strtarget = "modify";
			} else {
				fretValue = BedTypeMstDAO.updateQuery(chk1, vo);
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
			   HisException eObj = new HisException("IPD-->Bed Type Master", "BedTypeMstBO-->updateRecord()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
			strmsgText = "ipd.masters.BedTypeMstBO.UpdateRecord(chk,vo) --> "
					+ e.getMessage();
			new HisException("IPD", "Bed Type Master.Update", strmsgText);
		}
		return strtarget;
	}

	/**
	 * InvokedDAOBedTypeMst's function->initialUpdateQuery
	 * 
	 * @param chk
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean initialUpdate(String chk, BedTypeMstVO vo) throws Exception// Check
	// Data
	// in
	// Database
	// for
	// Modification
	{

		boolean fretvalue = true;
		try {
			fretvalue = BedTypeMstDAO.initialUpdateQuery(chk, vo);
		} catch (Exception e) {
			 vo.setStrErrorMsg(e.getMessage());
			   HisException eObj = new HisException("IPD-->Bed Type Master", "BedTypeMstBO-->initialUpdate()", vo.getStrErrorMsg());
			   vo.setStrErrorMsg("ERROR####Application Error [ERROR ID : " + eObj.getErrorID() + "], Contact System Administrator! ");
			   eObj = null;
		}
		return fretvalue;

	}

	// ///////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 */
	public WebRowSet getBedDtl1(boolean orderBy) throws Exception {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String tempStr = "";

		this.strErrMsg = "";

		this.strQry = ipd.qryHandler_ipd.getQuery(1, "gbl.bedDtl1.1");

		// CHECK ORDER BY
		if (orderBy) {
			tempStr = ipd.qryHandler_ipd.getQuery(1, "gbl.bedDtl.0");
			this.strQry += " ORDER BY " + tempStr;
		}

		try {
			if (!this.strQry.equals("")) {
				daoObj = new HisDAO(this.strModuleName, "DAOIpd."
						+ this.strFileName);
				// fire the query
				ws = daoObj.getQryResult(this.strQry);
			} else {
				this.strErrMsg = "DAOIpd.getWardDtl() -->Query is blank";
				throw new Exception(this.strErrMsg);
			}
		} catch (Exception e) {
			this.strErrMsg = "DAOIpd.getWardDtl() -->" + e.getMessage();
			throw new Exception(this.strErrMsg);
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
		return ws;
	}

	// ///////////////////////////////////////////////////////////////////////////
}