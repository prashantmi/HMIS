package billing.masters.bo;

import billing.masters.controller.fb.VOHospServiceGroupMst;
import billing.masters.dao.DAOHospServiceGroupMst;
import hisglobal.exceptions.HisException;

public class BOHospServiceGroupMst {

	public String InsertRecord(VOHospServiceGroupMst vo) {
		String strmsgText = null;
		String target = "";
		boolean retvalue;
		String errmsg = "";
		String msg = "";

		try {
			retvalue = DAOHospServiceGroupMst.insertQuery(vo);
			// System.out.println("retvalue"+retvalue);
			if (!retvalue) {
				// System.out.println("INSIDE FALSE CONDITION");
				errmsg = "Record not saved!";
				vo.setStrErrorMsg(errmsg);
				target = "add";
			} else {

				msg = "Record saved successfully!";
				vo.setMsg(msg);
				target = "add";
			}

		} catch (Exception e) {
			strmsgText = "billing.masters.BOHospServiceGroupMst.InsertRecord(chk1,vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"HospServiceGroupMaster->InsertRecord()", strmsgText);
			vo.setStrErrorMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}
		// System.out.println("target"+target);
		return target;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String updateRecord(String chk1, VOHospServiceGroupMst vo) {
		String strmsgText = null;
		String target = "";
		boolean retvalue;
		String errmsg = "";
		try {
			retvalue = DAOHospServiceGroupMst.updateQuery(chk1, vo);
			if (!retvalue) {
				errmsg = "Record not modified successfully!";
				vo.setStrErrorMsg(errmsg);
				target = "modify";
			} else {
				target = "list";

			}
		} catch (Exception e) {

			strmsgText = "billing.masters.BOHospServiceGroupMst.updateRecord(chk1,vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"HospServiceGroupMaster->updateRecord()", strmsgText);
			vo.setStrErrorMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}
		return target;//
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void modifyRecord(String chk1, VOHospServiceGroupMst vo) {
		String strmsgText = null;
		try {
			DAOHospServiceGroupMst.modifyQuery(chk1, vo);
		} catch (Exception e) {
			strmsgText = "billing.masters.BOHospServiceGroupMst.modifyRecord(chk1,vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"HospServiceGroupMaster->modifyRecord()", strmsgText);
			vo.setStrErrorMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;

		}
	}
}