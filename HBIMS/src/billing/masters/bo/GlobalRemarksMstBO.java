/* 
 * author: Pawan Kumar B N
 * Global Remarks Master BO
 * Created on : 26-Aug-2011
 */
package billing.masters.bo;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import billing.masters.dao.GlobalRemarksMstDAO;
import billing.masters.vo.GlobalRemarksMstVO;

public class GlobalRemarksMstBO {

	public GlobalRemarksMstBO() {

	}

	/**
	 * inserts new record by checking the existence of Record with same content.
	 * 
	 * @param vo
	 * @return true - if New Record Inserted Successfully. <br>
	 *         false - if New Record Not Inserted Successfully.
	 */
	public boolean insert(GlobalRemarksMstVO vo) {

		String qry = billing.qryHandler_billing.getQuery(1,
				"select.remarksMst.4");
		HisDAO hisDao = new HisDAO("Global Remarks Master", "GlobalRemarksMstBO.insert()");
		int qryIndex = hisDao.setQuery(qry);
		boolean rval = true;
		boolean returnValue = false;
		boolean returnValue2 = false;

		try {
			hisDao.setQryValue(qryIndex, 1, vo.getStrRemarksDesc());
			hisDao.setQryValue(qryIndex, 2, vo.getStrHospitalCode());
			hisDao.setQryValue(qryIndex, 3, vo.getStrRemarksType());
			WebRowSet wb = hisDao.executeQry(qryIndex);
			while (wb.next()) {
				if (wb.getInt(1) > 0) {

					//vo.setStrWarning("Record '"+
					//vo.getStrRemarksDesc()+ "' already Exists for selected Remarks Type");

					rval = false;
				}
			}
		} catch (Exception e) {

			String strmsgText = "billing.masters.GlobalRemarksMstBO.insert(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"GlobalRemarksMstBO->insertData()", strmsgText);

			vo.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

			return false;

		} finally {
			hisDao.free();
			hisDao = null;
		}

		if (rval) {
			returnValue = GlobalRemarksMstDAO.insertQuery(vo);
			//vo.setStrMsg("data inserted successfully:BO");
			if (returnValue) {
				
				vo.setStrMsg("Record Saved Successfully!");
				returnValue2 = true;
			}
			
		} else {
			vo.setStrWarning("Record '" + vo.getStrRemarksDesc()
					+ "' already Exists for selected Remarks Type");
		}

		return returnValue2;
	}

	/**
	 * invokes modifyQuery Method to retrieve Data's from Database
	 * 
	 * @param strChk
	 * @param vo
	 */
	public void modify(String strChk, GlobalRemarksMstVO vo) throws Exception {

		try {
			GlobalRemarksMstDAO.modifyQuery(strChk, vo);
		} catch (Exception e) {
			String strmsgText = "billing.masters.BOpackservMst.modify(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"GlobalRemarksMstBO->modify()", strmsgText);

			vo.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		}

	}

	/**
	 * invokes UpdateQuery method based on existence of Record and returns
	 * Update Status
	 * 
	 * @param request
	 * @param strChk
	 * @param vo
	 * @return true - if record Updated Successfully. <br>
	 *         false - if record not updated Successfully.
	 */
	public boolean update(String strChk,GlobalRemarksMstVO vo) {

		String strtemp[] = null;
		String strtemp2[] = null;
		String qry = billing.qryHandler_billing.getQuery(1,
				"select.remarksMst.5");
		HisDAO hisDao = new HisDAO("Remark Master", "GlobalRemarksMstBO.insert()");
		boolean rval = true;
		boolean returnValue = false;

		try {
			
			strtemp = strChk.replace('@', '#').split("#");
			strtemp2 = strtemp[1].replace("$", "#").split("#");
			strtemp[1] = strtemp2[0];
			int qryIndex = hisDao.setQuery(qry);

			hisDao.setQryValue(qryIndex, 1, vo.getStrRemarksDesc());
			hisDao.setQryValue(qryIndex, 2, strtemp[0]);
			hisDao.setQryValue(qryIndex, 3, strtemp[1]);
			hisDao.setQryValue(qryIndex, 4, vo.getStrRemarksType());

			WebRowSet wb = hisDao.executeQry(qryIndex);

			while (wb.next()) {

				if (wb.getInt(1) > 0) {

					vo
							.setStrWarning("Record '"
									+ vo.getStrRemarksDesc()
									+ "' already Exists");

					rval = false;
				}
			}
		} catch (Exception e) {

			HisException eObj = new HisException("Billing",
					"GlobalRemarksMstBO->update()", e.getMessage());

			vo.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

			return false;

		} finally {
			hisDao.free();
			hisDao = null;
		}
		if (rval) {
			returnValue = GlobalRemarksMstDAO.updateQuery(strChk, vo);
		
		}

		return returnValue;
	}

}
