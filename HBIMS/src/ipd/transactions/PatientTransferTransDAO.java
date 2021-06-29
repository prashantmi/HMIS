package ipd.transactions;

import ipd.IpdConfigUtil;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.GetNetworkAddress;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class PatientTransferTransDAO {
	// this function is used for Department code and a name
	public static void department(PatientTransferTransVO voObj) {
		String strproc_name = "{call pkg_ipd_view.Proc_Department(?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("ipd",
					"transactions.PatientTransferTransDAO.department()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospCode(),2);
			dao.setProcInValue(nprocIndex, "puk", "",3); 
			dao.setProcInValue(nprocIndex, "seatId", voObj.getStrSeatId(),4);
			dao.setProcOutValue(nprocIndex, "err", 1,5); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,6); // 2 for object
			
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				voObj.setStrDepartmenttWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.department() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	// this function is used for unit combo on the basis of department
	public static void unit(PatientTransferTransVO voObj) {
		String strproc_name = "{call pkg_ipd_view.Proc_Unit(?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("ipd","transactions.PatientTransferTransDAO.unit()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcInValue(nprocIndex, "dept_code", voObj.getStrDepartment(),2);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospCode(),3);
			dao.setProcInValue(nprocIndex, "puk", "",4);
			dao.setProcInValue(nprocIndex, "seatId", voObj.getStrSeatId(),5);
			dao.setProcInValue(nprocIndex, "wardcode", "",6);
			dao.setProcInValue(nprocIndex, "unitcode", "",7);
			dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
			
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				voObj.setStrUnitWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientTransferTransDAO.unit() ->"
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	public static void unitChangeCombo(PatientTransferTransVO voObj) {
		String strproc_name = "{call pkg_ipd_view.Proc_Unit(?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("ipd","transactions.PatientTransferTransDAO.unitChangeCombo()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "modeval", "4",1);
			dao.setProcInValue(nprocIndex, "dept_code", voObj.getStrDepartment(),2);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospCode(),3);
			dao.setProcInValue(nprocIndex, "puk", "",4);
			dao.setProcInValue(nprocIndex, "seatId", voObj.getStrSeatId(),5);
			dao.setProcInValue(nprocIndex, "wardcode", voObj.getStrWard(),6);
			dao.setProcInValue(nprocIndex, "unitcode", voObj.getStrUnit(),7);
			dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
		
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				voObj.setStrUnitWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientTransferTransDAO.unitChangeCombo() ->"
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	public static void roomCombo(PatientTransferTransVO voObj) {
		String strproc_name = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		
		try {
			dao = new HisDAO("ipd","transactions.PatientTransferTransDAO.roomCombo()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "modval", "4",1);
			dao.setProcInValue(nprocIndex, "roomtypcode", "",2);
			dao.setProcInValue(nprocIndex, "wardcode", voObj.getStrWard(),3);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospCode(),4);
			dao.setProcInValue(nprocIndex, "unitcode", voObj.getStrAgeCode(),5);
			dao.setProcInValue(nprocIndex, "age", voObj.getStrAge(),6);
			dao.setProcInValue(nprocIndex, "deptcode", voObj.getStrDepartment(),7);
			dao.setProcInValue(nprocIndex, "deptunitcode", voObj.getStrUnit(),8);
			dao.setProcInValue(nprocIndex, "gender", voObj.getStrGenderCode(),9);
			dao.setProcInValue(nprocIndex, "treatment_cat", voObj.getStrTreatmentCategoryCode(),10);
			dao.setProcInValue(nprocIndex, "puk_no", "",11);
			dao.setProcInValue(nprocIndex, "diseasetypcode", "",12);
			dao.setProcOutValue(nprocIndex, "err", 1,13); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,14); // 2 for object
			
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				voObj.setStrRoomWs(ws);
			} else {
				throw new Exception(strerr);
			}
			
			
			
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientTransferTransDAO.unitChangeCombo() ->"
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	public static void setTransferTypeCombo(PatientTransferTransVO voObj) {

		String strproc_name = "{call pkg_ipd_view.Proc_transfered_type_mst(?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("ipd","transactions.PatientTransferTransDAO.setTransferTypeCombo()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospCode(),1);
			dao.setProcOutValue(nprocIndex, "err", 1,2); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2,3); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			// System.out.println("afer calling wsSize->"+ws.size());
			if (strerr.equals("")) {
				voObj.setTransferedTypeWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.setTransferTypeCombo() ->"
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	/**
	 * This function is used to set Department name on main page
	 * 
	 * @param voObj
	 */
	public static void setDeptName(PatientTransferTransVO voObj) {
		String strProcName = "{call pkg_ipd_view.proc_dept_name(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = voObj.getStrCrNo();
		try {
			daoObj = new HisDAO("Patient Admission", "PatientTransferTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				if (ws.next()) {

					voObj.setStrDeptName(ws.getString(1));
				}

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrErrMsgString("PatientTransferTransDAO.setDeptName() -->"
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to set unit name on main page
	 * 
	 * @param voObj
	 */
	public static void setUnitName(PatientTransferTransVO voObj) {
		String strProcName = "{call pkg_ipd_view.proc_deptunit_name(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = voObj.getStrCrNo();
		try {
			daoObj = new HisDAO("Patient Admission", "PatientTransferTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					voObj.setStrUnitName(ws.getString(1));
					voObj.setStrDeptUnitCode(ws.getString(2));
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.setUnitName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function set ward type values in ward type combo on bed details pop
	 * up window
	 * 
	 * @param voObj
	 */
	public static void setWardTypeDtl(PatientTransferTransVO voObj) {
		HisDAO daoObj = null;
		String temp = "";
		HisUtil util = null;
		util = new HisUtil("ipd", "PatientTransferTransDAO");
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.PROC_HIPT_WARDTYPE_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Transfer Transaction",
					"PatientTransferTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				voObj.setWardTypeWS(ws);
				// System.out.println("wardType ws->"+ws.size());
				temp = util.getOptionValue(ws, "0", "0^Select Value", false);
				voObj.setStrwardType(temp);
				// System.out.println("wardTypeStr->"+temp);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.setWardTypeDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to details in room type combo on bed status pop up
	 * window
	 * 
	 * @param voObj
	 */
	public static void setRoomTypeDtl(PatientTransferTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomtype_a(?,?,?,?)}";
		int nProcIndex = 0;
		String temp = "";
		HisUtil util = null;
		util = new HisUtil("ipd", "PatientTransferTransDAO");
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Admission Transaction","PatientTransferTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				voObj.setRoomTypeWS(ws);
				temp = util.getOptionValue(ws, "0", "0^Select Value", false);
				voObj.setStrRoomType(temp);

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.setRoomTypeDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * 
	 * @param voObj
	 */
	public static void getBedStatusDtl(PatientTransferTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_ipd_bedstatus_dtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Admission Transaction",
					"PatientTransferTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "pukno", voObj.getStrCrNo(),1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					voObj.setStrWardTypeCode(ws.getString(1));
					voObj.setStrWardCode(ws.getString(2));
					voObj.setStrBedTypeCode(ws.getString(3));
					voObj.setStrRoomTypeCode(ws.getString(4));
					voObj.setStrWardName(ws.getString(5));
					voObj.setStrDeptCode(ws.getString(6));
					voObj.setStrMsApprovalFlag(ws.getString(7));
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.getBedStatusDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	/**
	 * This function is used to bring ward details in ward combo on bed details
	 * pop window
	 * 
	 * @param voObj
	 */
	public static void getWardValues(PatientTransferTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp = "";
		HisUtil util = null;
		util = new HisUtil("ipd", "PatientTransferTransDAO");
		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		daoObj = new HisDAO("Patient Transfer Transaction","PatientTransferTransDAO.getWardValues()");
		try {
			daoObj = new HisDAO("Patient Admission Transaction",
					"PatientTransferTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "8",1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode", voObj.getStrWardTypeCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptcode", voObj.getStrDeptCode(),3);
			daoObj.setProcInValue(nProcIndex, "deptunitcode", voObj.getStrDeptUnitCode(),4);
			daoObj.setProcInValue(nProcIndex, "unitcode", voObj.getStrAgeCode(),5);
			daoObj.setProcInValue(nProcIndex, "age", voObj.getStrAge(),6);
			daoObj.setProcInValue(nProcIndex, "gender", voObj.getStrGenderCode(),7);
			daoObj.setProcInValue(nProcIndex, "treatment_cat", voObj.getStrTreatmentCategoryCode(),8);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),9);
			daoObj.setProcInValue(nProcIndex, "effect_from","",10);
			daoObj.setProcInValue(nProcIndex, "effect_to","",11);
			daoObj.setProcInValue(nProcIndex, "diseasetypcode","0",12);
			daoObj.setProcInValue(nProcIndex, "wardcode","0",13);
			daoObj.setProcInValue(nProcIndex, "puk_no", voObj.getStrCrNo(),14);
			daoObj.setProcInValue(nProcIndex, "charge_type_id","0",15);		  
			daoObj.setProcOutValue(nProcIndex, "err", 1,16);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,17);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			/*
			 * 
			 */

			/*
			 * nProcIndex = daoObj.setProcedure(strProcName);
			 * daoObj.setProcInValue(nProcIndex, "modeVal","8");
			 * daoObj.setProcInValue(nProcIndex,
			 * "wardtypcode",voObj.getStrWardTypeCode());
			 * daoObj.setProcInValue(nProcIndex,
			 * "deptcode",voObj.getStrDeptCode());
			 * daoObj.setProcInValue(nProcIndex,
			 * "deptunitcode",voObj.getStrDeptUnitCode());
			 * daoObj.setProcInValue(nProcIndex,
			 * "hosp_code",voObj.getStrHospCode());
			 * daoObj.setProcOutValue(nProcIndex, "err", 1);
			 * daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			 * daoObj.executeProcedure(nProcIndex); strErr =
			 * daoObj.getString(nProcIndex, "err");
			 */
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			temp = util.getOptionValue(ws, "0", "0^Select Value", false);
			voObj.setStrWard(temp);
			if (strErr.equals("")) {
				voObj.setWardWS(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.getWardValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to bring room details in room combo on the basis of
	 * room type code
	 * 
	 * @param voObj
	 */
	public static void getRoomValues(PatientTransferTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String temp = "";
		String strErr = "";
		HisUtil util = null;
		daoObj = new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO.getRoomValues()");
		try {
			daoObj = new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO");

			util = new HisUtil("Patient Transfer Trans",
					"PatientAdmissionModificationTransDATA");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modval", "3",1);
			daoObj.setProcInValue(nProcIndex, "roomtypcode", voObj.getStrRoomTypeCode(),2);
			daoObj.setProcInValue(nProcIndex, "wardcode", voObj.getStrWardCode(),3);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),4);
			daoObj.setProcInValue(nProcIndex, "unitcode", voObj.getStrAgeCode(),5);
			// daoObj.setProcInValue(nProcIndex,
			// "deptcode",vo.getStrDeptCode());
			daoObj.setProcInValue(nProcIndex, "age", voObj.getStrAge(),6);
			daoObj.setProcInValue(nProcIndex, "deptcode", (voObj.getStrDeptCode()==null)?"":voObj.getStrDeptCode(),7);
			daoObj.setProcInValue(nProcIndex, "deptunitcode", voObj.getStrDeptUnitCode(),8);
			daoObj.setProcInValue(nProcIndex, "gender", voObj.getStrGenderCode(),9);
			daoObj.setProcInValue(nProcIndex, "treatment_cat", voObj.getStrTreatmentCategoryCode(),10);
			daoObj.setProcInValue(nProcIndex, "puk_no", voObj.getStrCrNo(),11);
			daoObj.setProcInValue(nProcIndex, "diseasetypcode", "0",12);
			daoObj.setProcOutValue(nProcIndex, "err", 1,13);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,14);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			
			

			/*
			 * nProcIndex = daoObj.setProcedure(strProcName);
			 * 
			 * 
			 * 
			 * daoObj.setProcInValue(nProcIndex, "modval","1");
			 * daoObj.setProcInValue(nProcIndex,
			 * "roomtypcode",voObj.getStrRoomTypeCode());
			 * daoObj.setProcInValue(nProcIndex,
			 * "wardcode",voObj.getStrWardCode());
			 * daoObj.setProcInValue(nProcIndex,
			 * "hosp_code",voObj.getStrHospCode());
			 * daoObj.setProcOutValue(nProcIndex, "err", 1);
			 * daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			 * daoObj.executeProcedure(nProcIndex); strErr =
			 * daoObj.getString(nProcIndex, "err");
			 */
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			temp = util.getOptionValue(ws, "0", "0^Select Value", false);
			voObj.setStrRoom(temp);
			if (strErr.equals("")) {
				voObj.setRoomWs(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.getRoomValues() -->"
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to bring details in bed type combo in bed details
	 * pop up window
	 * 
	 * @param voObj
	 */
	public static void setBedTypeDtl(PatientTransferTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp = "";
		HisUtil util = null;
		util = new HisUtil("ipd", "PatientTransferTransDAO");
		String strProcName = "{call pkg_ipd_view.proc_bed_type_mst(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Transfer Transaction",
					"PatientTransferTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			temp = util.getOptionValue(ws, "0", "0^Select Value", false);
			voObj.setStrBedType(temp);
			if (strErr.equals("")) {

				voObj.setBedTypeWS(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrErrMsgString("PatientTransferTransDAO.setBedTypeDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to bring the bed Values in bed combo
	 * 
	 * @param voObj
	 */
	public static void getBed(PatientTransferTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp = "";
		HisUtil util = null;
		util = new HisUtil("ipd", "PatientTransferTransDAO");
		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			
			daoObj = new HisDAO("Patient Admission  Transaction","PatientTransferTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "8",1);
			daoObj.setProcInValue(nProcIndex, "wardcode", voObj.getStrWardCode(),2);
			daoObj.setProcInValue(nProcIndex, "roomno", voObj.getStrRoomCode(),3);
			daoObj.setProcInValue(nProcIndex, "bedtypcode", voObj.getStrBedTypeCode(),4);
			daoObj.setProcInValue(nProcIndex, "bedstatcode", "10",5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),6);
			daoObj.setProcInValue(nProcIndex, "deptunit", voObj.getStrDeptUnitCode(),7);
			daoObj.setProcInValue(nProcIndex, "bedCode","",8);
			daoObj.setProcInValue(nProcIndex, "shr_chk",(voObj.getSharableChk() != null) ? voObj.getSharableChk() : "0",9);
			System.out.println("shr_chk------------------------------------>"+voObj.getSharableChk());
			daoObj.setProcOutValue(nProcIndex, "err", 1,10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				voObj.setBedDetailWS(ws);
			} else {
				throw new Exception(strErr);
			}
			if (voObj.getStrPopUp().equals("0")) {
				temp = util.getOptionValue(ws, "0", "0^Select Value", false);
				voObj.setStrBed(temp);
			}
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.getBedValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to bring the bed details on bed details pop up
	 * window on the basis of ward code,room number,bed type code
	 * 
	 * @param voObj
	 * 
	 * public static void getBedValues(PatientTransferTransVO voObj) { HisDAO
	 * daoObj = null; WebRowSet ws = null; String temp=""; HisUtil util=null;
	 * util = new HisUtil("ipd","PatientTransferTransDAO"); String strProcName =
	 * "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?)}"; int nProcIndex = 0;
	 * String strErr = ""; System.out.println("Ward
	 * Code----->"+voObj.getStrWardCode()); System.out.println("Room
	 * Code----->"+voObj.getStrRoomCode()); System.out.println("Bed Type
	 * Code----->"+voObj.getStrBedTypeCode()); System.out.println("DeptUnit
	 * Code-->"+voObj.getStrDeptUnitCode()); try { daoObj = new HisDAO("Patient
	 * Admission Transaction","PatientTransferTransDAO"); nProcIndex =
	 * daoObj.setProcedure(strProcName); daoObj.setProcInValue(nProcIndex,
	 * "modval","4"); daoObj.setProcInValue(nProcIndex,
	 * "wardcode",voObj.getStrWardCode()); daoObj.setProcInValue(nProcIndex,
	 * "roomno",voObj.getStrRoomCode()); daoObj.setProcInValue(nProcIndex,
	 * "bedtypcode",voObj.getStrBedTypeCode());
	 * daoObj.setProcInValue(nProcIndex, "deptunit",voObj.getStrDeptUnitCode());
	 * daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode());
	 * daoObj.setProcOutValue(nProcIndex, "err", 1);
	 * daoObj.setProcOutValue(nProcIndex, "resultset", 2);
	 * daoObj.executeProcedure(nProcIndex); strErr =
	 * daoObj.getString(nProcIndex, "err"); if (strErr == null) strErr = ""; ws =
	 * daoObj.getWebRowSet(nProcIndex, "resultset"); if (strErr.equals("")) {
	 * voObj.setBedDetailWS(ws); } else { throw new Exception(strErr); }
	 * if(voObj.getStrPopUp().equals("0")) {
	 * temp=util.getOptionValue(ws,"0","0^Select Value", false);
	 * voObj.setStrBed(temp); } } catch(Exception e) {
	 * voObj.setStrErrMsgString("PatientTransferTransDAO.getBedValues() --> " +
	 * e.getMessage()); voObj.setStrMsgType("1"); } finally { if (daoObj !=
	 * null) { daoObj.free(); daoObj = null; } } }
	 */

	public static void getServRoomValues(PatientTransferTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp = "";
		HisUtil util = null;
		util = new HisUtil("ipd", "PatientTransferTransDAO");
		String strProcName = "{call pkg_ipd_view.Proc_Hipt_Service_Dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		daoObj = new HisDAO("Patient Admission Transaction",
				"PatientTransferTransDAO");
		// System.out.println("servAreaCode->"+voObj.getStrServAreaCode());
		try {
			daoObj = new HisDAO("Patient Transfer  Transaction",
					"PatientTransferTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "2",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(),2);
			daoObj.setProcInValue(nProcIndex, "service_code", voObj
					.getStrServAreaCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			// System.out.println("hi...123");
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			// System.out.println("hi...123 after ws");
			if (strErr.equals("")) {
				voObj.setStrRoomWs(ws);
			} else {
				throw new Exception(strErr);
			}
			temp = util.getOptionValue(ws, "0", "0^Select Value", false);
			// System.out.println("servRoom->"+temp);
			voObj.setStrRoom(temp);
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.getBedValues() -->"
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void setServAreaName(PatientTransferTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp = "";
		HisUtil util = null;
		util = new HisUtil("ipd", "PatientTransferTransDAO");
		String strProcName = "{call pkg_ipd_view.Proc_Hipt_Service_Dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		daoObj = new HisDAO("Patient Transfer Transaction",
				"PatientTransferTransDAO");
		try {
			daoObj = new HisDAO("Patient Transfer  Transaction",
					"PatientTransferTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),2);
			daoObj.setProcInValue(nProcIndex, "service_code", "",3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				voObj.setStrServAreaWS(ws);
			} else {
				throw new Exception(strErr);
			}
			temp = util.getOptionValue(ws, "0", "0^Select Value", false);
			voObj.setStrServArea(temp);
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientTransferTransDAO.getBedValues() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to set comsultant code and consultant name on the
	 * behalf of puk no
	 */
	public static void setConsultantName(PatientTransferTransVO voObj) {
		String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp = "";
		HisUtil util = null;
		util = new HisUtil("ipd", "PatientTransferTransDAO");
		String strDeptUnitCode = voObj.getStrDeptUnitCode();
		try {
			daoObj = new HisDAO("Patient Transfer", "PatientTransferTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modVal","1",1);
			daoObj.setProcInValue(nProcIndex, "deptunitcode", strDeptUnitCode,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),3);
			daoObj.setProcInValue(nProcIndex, "seatId","",4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			   
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			temp = util.getOptionValue(ws, "0", "0^Select Value", false);
			voObj.setStrConsultantCode(temp);
			if (strErr.equals("")) {
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientTransferTransdao.setConsultantName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to bring ward,room and bed details in case of Ms
	 * Approval
	 * 
	 * @param voObj
	 */
	public static void getPatDtl_Msapproval(PatientTransferTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_pat_adm_msapproval(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Transfer Transaction",
					"PatientTransferTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "pukno", voObj.getStrCrNo(),2);
			daoObj.setProcInValue(nProcIndex, "advno", voObj.getStrAdviceAdmNo(),3);
			daoObj.setProcInValue(nProcIndex, "bookingdate", voObj.getStrCtDate()/* "24-Jul-2008" */,4);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					voObj.setStrWardTypeCode(ws.getString(1));
					voObj.setStrWardCode(ws.getString(2));
					voObj.setStrDeptCode(ws.getString(3));
					voObj.setStrRoomCode(ws.getString(4));
					voObj.setStrBedCode(ws.getString(5));
					voObj.setStrMsApprovalStatus(ws.getString(6));
					voObj.setStrWardName(ws.getString(7));
					voObj.setStrRoom(ws.getString(8));
				}
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientAdmissionTransDAO.getPatDtl_Msapproval() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public WebRowSet getAdvisedBy(PatientTransferTransVO voObj) 
	{
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
		String deptUnitCode="0";
		if(voObj.getStrCurrentDeptUnitRoom().replace("^","#").split("#").length>1)
			deptUnitCode=voObj.getStrCurrentDeptUnitRoom().replace("^","#").split("#")[1];
		try 
		{
			daoObj = new HisDAO("ADT","transactions.PatientTransferTransdao.getAdvisedBy()");
			nProcIndex = daoObj.setProcedure(strProcName);
			//daoObj.setProcInValue(nProcIndex, "modVal", "2");
			daoObj.setProcInValue(nProcIndex, "modVal", "3",1);//To get dept unit wise consultant
			daoObj.setProcInValue(nProcIndex, "deptunitcode", deptUnitCode,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),3);
			daoObj.setProcInValue(nProcIndex, "seatId", "",4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);	
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		} 
		catch (Exception e) 
		{
			voObj.setStrErrMsgString("PatientTransferTransDAO.getAdvisedBy()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally 
		{
			daoObj.free();
			daoObj = null;
		}
		return ws;
	}

	public void getRsnRmk(PatientTransferTransVO voObj) 
	{
		HisUtil hisutil = new HisUtil("transaction", "PatientTransferTransDAO");
		WebRowSet w1;
		try 
		{
			w1 = getAdvisedBy(voObj);
			voObj.setStrDisBy(w1);
			String str1 = hisutil.getOptionValue(voObj.getStrDisBy(), voObj.getStrConsultant(),"0^Select value", true);
			voObj.setStrRmk(str1);
		} 
		catch (Exception e) 
		{
			voObj.setStrErrMsgString("PatientTransferTransDAO.getRsnRmk()--> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} 
		finally 
		{
			w1 = null;
		}

	}

	/** *******PROC for getting Adm Status Code********* */

	public WebRowSet admStatus(PatientTransferTransVO voObj) {
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call pkg_ipd_view.PROC_PAT_ADMSTATUS_CODE_DTL(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int procIndex1 = 0;
		String strerr = "";
		try {
			dao = new HisDAO("ipd", "transactions.PatientLeaveDAO.admStatus()");
			procIndex1 = dao.setProcedure(proc_name1);
			dao.setProcInValue(procIndex1, "modval","1",1);
			dao.setProcInValue(procIndex1, "hipnum_admno","",2);
			dao.setProcInValue(procIndex1, "hrgnum_puk", voObj.getStrCrNo(),3);
			dao.setProcInValue(procIndex1, "gnum_hospital_code", voObj.getStrHospCode(),4);
			dao.setProcInValue(procIndex1, "issuereq","2",5);
			dao.setProcOutValue(procIndex1, "err", 1,6); // 1 for string return
			dao.setProcOutValue(procIndex1, "resultset", 2,7);
			
			// execute procedure
			dao.executeProcedureByPosition(procIndex1);
			// get value
			strerr = dao.getString(procIndex1, "err");
			ws = dao.getWebRowSet(procIndex1, "resultset");
			if (strerr == null)
				strerr = "";
			if (strerr.equals("")) {
				voObj.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientTransferTransDAO.admStatus()--> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return ws;
	}

	/** ***********END************* */

	/** ******insert***** */

	public boolean patMov(PatientTransferTransVO voObj) {
		String strProcName = "";
		String strProcName2 ="{call pkg_ipd_dml.proc_ip_mac(?,?,?,?,?,?,?,?)}";
		String proc_name1 = "";
		int nProcIndex = 0;
		int nProcIndex2 = 0;
		String strServName="";
		String strServDept="";
		String strServUnit="";
		StringBuffer proc_name = new StringBuffer("");
		proc_name.append("{call PKG_IPD_DML.proc_patient_movement(?");
		int len = 35;

		for (int i = 1; i <= len; i++) {
			proc_name.append(",?");
		}
		proc_name.append(")}");
		proc_name1 = proc_name.toString();
		

		HisDAO dao = null;
		int procIndex1 = 0;
		String strerr = "";
		String strErr1 = "";
		boolean retVal = false;
		if(!voObj.getStrServiceName().equals("")&& voObj.getStrServiceName()!=null)
		{
			strServName=voObj.getStrServiceName().replace("^", "#").split("#")[0];
			strServDept=voObj.getStrServiceName().replace("^", "#").split("#")[1];
			strServUnit=voObj.getStrServiceName().replace("^", "#").split("#")[2];
		}
		try {
			dao = new HisDAO("ipd","transactions.PatientTransferTransDAO.patMov()");
			
			procIndex1 = dao.setProcedure(proc_name1);
			dao.setProcInValue(procIndex1, "hipnum_admno", voObj.getStrAdviceAdmNo(),1);
			dao.setProcInValue(procIndex1, "hipnum_mov_no", "",2);
			dao.setProcInValue(procIndex1, "gnum_hospital_code", voObj.getStrHospCode(),3);
			dao.setProcInValue(procIndex1, "hrgnum_puk", voObj.getStrCrNo(),4);
			dao.setProcInValue(procIndex1, "gnum_dept_code", voObj.getStrDeptCode(),5);
			dao.setProcInValue(procIndex1, "gnum_deptunit_code", voObj.getStrDeptUnitCode(),6);
			dao.setProcInValue(procIndex1, "hipnum_trans_serarea_code", voObj.getStrServAreaCode(),7);
			dao.setProcInValue(procIndex1, "hipnum_wardcode", voObj.getStrWardCode(),8);
			dao.setProcInValue(procIndex1, "hipnum_roomno", voObj.getStrRoomCode(),9);
			
			if (!voObj.getStrBedCode().equals("0"))
				dao.setProcInValue(procIndex1, "hipnum_bed_code",voObj.getStrBedCode().equals("undefined") ? "0" : voObj.getStrBedCode(),10);
			else
				dao.setProcInValue(procIndex1, "hipnum_bed_code", "0",10);
			dao.setProcInValue(procIndex1, "hipdt_trans_datetime", "",11);
			dao.setProcInValue(procIndex1, "hipdt_transin_datetime", "",12);
			dao.setProcInValue(procIndex1, "hipdt_transout_datetime", "",13);
			dao.setProcInValue(procIndex1, "hipstr_trans_reason", voObj.getStrRsn(),14);
			dao.setProcInValue(procIndex1, "hipnum_transin_flg", voObj.getStrTransFlg(),15);
			dao.setProcInValue(procIndex1, "hipnum_transout_flg", "",16);
			dao.setProcInValue(procIndex1, "gdt_entry_date", "",17);
			dao.setProcInValue(procIndex1, "gnum_seatid", voObj.getStrSeatId(),18);
			dao.setProcInValue(procIndex1, "gnum_isvalid", "1",19);
			dao.setProcInValue(procIndex1, "hipnum_bedvacat_flg", voObj.getStrIsBedVacant(),20);
			dao.setProcInValue(procIndex1, "gnum_nurse_seatid", "",21);
			dao.setProcInValue(procIndex1, "old_hgnum_ward_code", voObj.getStrOldWardCode(),22);
			dao.setProcInValue(procIndex1, "old_hipnum_room_no ", voObj.getStrOldRoomCode(),23);
			dao.setProcInValue(procIndex1, "old_hgnum_bed_code", voObj.getStrOldBedCode(),24);
			dao.setProcInValue(procIndex1, "old_gnum_hospital_code", voObj.getStrHospCode(),25);
			dao.setProcInValue(procIndex1, "outside_location", voObj.getStrLocation(),26);
			dao.setProcInValue(procIndex1, "prev_double_occupancy", voObj.getStrPrevDblOcc(),27);
			dao.setProcInValue(procIndex1, "hold_room", voObj.getStrHoldRoom(),28);
			dao.setProcInValue(procIndex1, "str_advice_by", voObj.getStrRmk(),29);
			dao.setProcOutValue(procIndex1, "err", 1,30); // 1 for string return
			dao.setProcOutValue(procIndex1, "dml_count", 1,31); // value
			dao.setProcInValue(procIndex1, "whetherVacantBed", voObj.getStrIsBedVacant(),32);
			dao.setProcInValue(procIndex1, "serviceTypeId", voObj.getStrServiceType(),33);
			dao.setProcInValue(procIndex1, "serviceName", strServName,34);
			dao.setProcInValue(procIndex1, "serviceDept", strServDept,35);
			dao.setProcInValue(procIndex1, "serviceUnit", strServUnit,36);

			// execute procedure
			//dao.execute(procIndex1,1);
			dao.executeProcedureByPosition(procIndex1);
			
	/*
			if (voObj.getStrTransferTypeBelonging().equals("1")) 
			{
				strProcName = "{call pkg_ipd_dml.Proc_Hipt_Pat_Belonging_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				nProcIndex = dao.setProcedure(strProcName);
				HisUtil util=new HisUtil("ADT","patMov");
				
				dao.setProcInValue(nProcIndex, "modval", "2",1);
				dao.setProcInValue(nProcIndex, "hipstr_belong_desc", "",2);
				dao.setProcInValue(nProcIndex, "hipstr_belong_qty", "",3);
				dao.setProcInValue(nProcIndex, "hipstr_benlog_remark", "",4);
				dao.setProcInValue(nProcIndex, "hgnum_deptunitcode", voObj.getStrOldDeptUnitCode(),5);
				dao.setProcInValue(nProcIndex, "hipnum_ward_code", voObj.getStrOldWardCode(),6);
				dao.setProcInValue(nProcIndex, "to_deptunitcode", "",7);
				dao.setProcInValue(nProcIndex, "to_ward_code", "",8);
				dao.setProcInValue(nProcIndex, "gdt_entry_date", util.getASDate("dd/MM/yyyy"),9);
				dao.setProcInValue(nProcIndex, "gnum_seatid","",10);
				dao.setProcInValue(nProcIndex, "gnum_isvalid","1",11);
				dao.setProcInValue(nProcIndex, "hipdt_return_datetime","",12);
				dao.setProcInValue(nProcIndex, "hipstr_return_to","",13);
				dao.setProcInValue(nProcIndex, "hipnum_admno", voObj.getStrAdmNo(),14);
				dao.setProcInValue(nProcIndex, "hipnum_belong_slno","",15);
				dao.setProcInValue(nProcIndex, "gnum_hospital_code", voObj.getStrHospCode(),16);
				dao.setProcInValue(nProcIndex, "hrgnum_puk","",17);
				dao.setProcInValue(nProcIndex, "return_rmks","",18);
				dao.setProcInValue(nProcIndex, "gnum_relation_code","0",19);
				dao.setProcInValue(nProcIndex, "item_type", "2",20);
				dao.setProcInValue(nProcIndex, "item_id","",21);
				dao.setProcInValue(nProcIndex, "status","",22);  
				dao.setProcOutValue(nProcIndex, "err", 1,23);
				dao.setProcOutValue(nProcIndex, "RESULT", 1,24);
				dao.setProcOutValue(nProcIndex, "dml_count", 1,25);
			      
				dao.execute(nProcIndex,1);
			}
			if (voObj.getStrTransferTypeIssue().equals("1")) 
			{
				strProcName = "{call pkg_ipd_dml.Proc_Hipt_Pat_Belonging_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				nProcIndex = dao.setProcedure(strProcName);
				HisUtil util=new HisUtil("ADT","patMov");
				
				dao.setProcInValue(nProcIndex, "modval", "2",1);
				dao.setProcInValue(nProcIndex, "hipstr_belong_desc", "",2);
				dao.setProcInValue(nProcIndex, "hipstr_belong_qty", "",3);
				dao.setProcInValue(nProcIndex, "hipstr_benlog_remark", "",4);
				dao.setProcInValue(nProcIndex, "hgnum_deptunitcode", voObj.getStrOldDeptUnitCode(),5);
				dao.setProcInValue(nProcIndex, "hipnum_ward_code", voObj.getStrOldWardCode(),6);
				dao.setProcInValue(nProcIndex, "to_deptunitcode", "",7);
				dao.setProcInValue(nProcIndex, "to_ward_code", "",8);
				dao.setProcInValue(nProcIndex, "gdt_entry_date", util.getASDate("dd/MM/yyyy"),9);
				dao.setProcInValue(nProcIndex, "gnum_seatid","",10);
				dao.setProcInValue(nProcIndex, "gnum_isvalid","1",11);
				dao.setProcInValue(nProcIndex, "hipdt_return_datetime","",12);
				dao.setProcInValue(nProcIndex, "hipstr_return_to","",13);
				dao.setProcInValue(nProcIndex, "hipnum_admno", voObj.getStrAdmNo(),14);
				dao.setProcInValue(nProcIndex, "hipnum_belong_slno","",15);
				dao.setProcInValue(nProcIndex, "gnum_hospital_code", voObj.getStrHospCode(),16);
				dao.setProcInValue(nProcIndex, "hrgnum_puk","",17);
				dao.setProcInValue(nProcIndex, "return_rmks","",18);
				dao.setProcInValue(nProcIndex, "gnum_relation_code","0",19);
				dao.setProcInValue(nProcIndex, "item_type", "1",20);	
				dao.setProcInValue(nProcIndex, "item_id","",21);
				dao.setProcInValue(nProcIndex, "status","",22);  
				dao.setProcOutValue(nProcIndex, "err", 1,23);
				dao.setProcOutValue(nProcIndex, "RESULT", 1,24);
				dao.setProcOutValue(nProcIndex, "dml_count", 1,25);

				dao.execute(nProcIndex,1);
				if (strerr == null)
					strerr = "";
				if (strerr.equals("")) 
				{
					retVal = true;
					voObj.setStrMsgType("0");
				} 
				else 
				{
					throw new Exception(strerr);
				}
			}
			synchronized(dao)
			{
				dao.fire();
			}*/
			strerr = dao.getString(procIndex1, "err");
			if (strerr == null)
				strerr = "";
			if (strerr.equals("")) {
				retVal = true;
				voObj.setStrMsgType("0");
			} else {
				throw new Exception(strerr);
			}
			
			nProcIndex2 = dao.setProcedure(strProcName2);
			dao.setProcInValue(nProcIndex2, "modeval", "1",1);									
			dao.setProcInValue(nProcIndex2, "adm_no", voObj.getStrAdviceAdmNo(),2);	//							
			dao.setProcInValue(nProcIndex2, "hospital_code",  voObj.getStrHospCode(),3);					
			dao.setProcInValue(nProcIndex2, "puk", voObj.getStrCrNo(),4);		
			dao.setProcInValue(nProcIndex2, "mov_no", "",5);	
			dao.setProcInValue(nProcIndex2, "ip", GetNetworkAddress.GetAddress("ip"),6);			
			dao.setProcInValue(nProcIndex2, "mac", GetNetworkAddress.GetAddress("mac"),7);		//				
			dao.setProcOutValue(nProcIndex2, "err", 1,8);		//			
			dao.executeProcedureByPosition(nProcIndex2);
			
			
			strErr1 = dao.getString(nProcIndex2, "err");
			if (strErr1 == null)
				strErr1 = "";
			if (strErr1.equals("")) {
			} else {
				throw new Exception(strErr1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			voObj.setStrErrMsgString("PatientTransferTransDAO.patMov()-->"
					+ e.getMessage());
			voObj.setStrMsgType("1");
			retVal = false;
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		return retVal;
	}
	
	
	/*For Service Type Combo In Patient Transfer Process*/
	public static void getServiceType(PatientTransferTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_get_service_type(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
				daoObj = new HisDAO("Patient Attendance","PatientTransferTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval","1",1);
				daoObj.setProcInValue(nProcIndex, "serviceTypeId","0",2); 
				daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE,3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				if (strErr.equals("")) {
					vo.setStrServiceTypeWS(ws);
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientTransferTransDAO.getServiceType() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	
	//Service Name Combo In Patient Transfer Process*/
	public static void getServiceName(PatientTransferTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_get_service_type(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		int qryIndex;
		HisDAO daoObj = null;
		WebRowSet ws = null;
		WebRowSet wb = null;
		String query="";
		String strBedVacantMode="";
		try {
				daoObj = new HisDAO("Patient Transfer Trans","PatientTransferTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval","2",1);
				daoObj.setProcInValue(nProcIndex, "serviceTypeId",vo.getStrServiceTypeId(),2); 
				daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE,3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				while (ws.next()) {
					 query=ws.getString(1);
					 //strStatus=ws.getString(2);
					 strBedVacantMode=ws.getString(3);
				}
				
				vo.setStrBedVacantMode(strBedVacantMode);
				qryIndex = daoObj.setQuery(query);
				daoObj.setQryValue(qryIndex, 1, vo.getStrHospCode());
				
				wb = daoObj.executeQry(qryIndex);
				
				vo.setStrServiceNameWS(wb);
				 
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientTransferTransDAO.getServiceName() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	
	
	public static void serviceValidation(PatientTransferTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_get_service_type(?,?,?,?,?)}";
		int nProcIndex = 0;
        int count=0;
		String strErr = "";
		int qryIndex;
		HisDAO daoObj = null;
		WebRowSet ws = null;
		WebRowSet wb = null;
		String query="";
		
		String serviceType=vo.getStrServiceTypeId();
		try {
				daoObj = new HisDAO("Patient Transfer Trans","PatientTransferTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval","3",1);
				daoObj.setProcInValue(nProcIndex, "serviceTypeId",vo.getStrServiceTypeId(),2); 
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				while (ws.next()) {
					 query=ws.getString(1);
				}
				
				
				
				if(query	!=	null	&&	!query.equals(""))
				{
					qryIndex = daoObj.setQuery(query);
					if(serviceType.equals("10")) // SERVICE AREA
					{
						daoObj.setQryValue(qryIndex, 1, vo.getStrCrNo());
						daoObj.setQryValue(qryIndex, 2, vo.getStrServiceName());// service area code
						daoObj.setQryValue(qryIndex, 3, vo.getStrHospCode());
					}
					else if(serviceType.equals("11")) // OT
					{
						daoObj.setQryValue(qryIndex, 1, vo.getStrServiceName());
						daoObj.setQryValue(qryIndex, 2, vo.getStrServiceDeptCode());
						daoObj.setQryValue(qryIndex, 3, vo.getStrHospCode());
						daoObj.setQryValue(qryIndex, 4, vo.getStrCrNo());					
					}
					else if(serviceType.equals("12")) // INVESTIGATION
					{
						daoObj.setQryValue(qryIndex, 1, vo.getStrServiceName());// Lab Code
						daoObj.setQryValue(qryIndex, 2, vo.getStrCrNo());					
					}
					wb = daoObj.executeQry(qryIndex);
					while (wb.next()) 
					{
						count++;
					}
					if(count==0)
						vo.setStrValidatedFlag("0");
					else
						vo.setStrValidatedFlag("1");
				}
				else
					vo.setStrValidatedFlag("1");				 
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			vo.setStrMsgString("PatientTransferTransDAO.serviceValidation() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} 
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
	}

}