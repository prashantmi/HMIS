package ipd.transactions;
/*
 * Patient Attendance DAO
 * 
 * author: Debashis Sardar
 * 
 * dated: 16/02/2012 
 */
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import javax.sql.rowset.WebRowSet;

public class PatientAttendanceDAO {
	
	/**
	 * to get Service type combo
	 * @param PatientAttendanceVO - vo
	 * 
	 */
	public static void getServiceType(PatientAttendanceVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_get_service_type(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
				daoObj = new HisDAO("Patient Attendance","PatientAttendanceDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval","1",1);
				daoObj.setProcInValue(nProcIndex, "serviceTypeId","0",2); 
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
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
			
			vo.setStrMsgString("PatientAttendanceDAO.getServiceType() --> "	+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	/**
	 * to get department combo
	 * @param PatientAttendanceVO - voObj
	 * 
	 */
	public static void department(PatientAttendanceVO voObj) {
		String strproc_name = "{call pkg_ipd_view.Proc_Department(?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("ipd",
					"transactions.PatientAttendanceDAO.department()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospCode(),2);
			dao.setProcInValue(nprocIndex, "seatId", voObj.getStrSeatId(),4);
			dao.setProcOutValue(nprocIndex, "err", 1,5); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,6); // 2 for object
			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcInValue(nprocIndex, "puk", "",3); 
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				voObj.setStrDepartmentWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			voObj
					.setStrErrMsgString("PatientAttendanceDAO.department() --> "
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
	 * to get unit combo
	 * @param PatientAttendanceVO - voObj
	 * 
	 */
	public static void unit(PatientAttendanceVO voObj) {
		String strproc_name = "{call pkg_ipd_view.Proc_Unit(?,?,?,?::character varying,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("ipd","transactions.PatientAttendanceDAO.unit()");
			nprocIndex = dao.setProcedure(strproc_name);
			
			dao.setProcInValue(nprocIndex, "modeval", "1",1);
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
			voObj.setStrErrMsgString("PatientAttendanceDAO.unit() ->"
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
	 * to get ward combo
	 * @param PatientAttendanceVO - voObj
	 * 
	 */
	public static void ward(PatientAttendanceVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp = "";
		HisUtil util = null;
		util = new HisUtil("ipd", "PatientAttendanceDAO");
		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		daoObj = new HisDAO("Patient Attendance Transaction","PatientAttendanceDAO.ward()");
		try {

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "16",1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode", "0",2);
			daoObj.setProcInValue(nProcIndex, "deptcode", voObj.getStrDeptCode(),3);
			daoObj.setProcInValue(nProcIndex, "deptunitcode", voObj.getStrDeptUnitCode(),4);
			daoObj.setProcInValue(nProcIndex, "unitcode", voObj.getStrAgeCode(),5);
			daoObj.setProcInValue(nProcIndex, "age", voObj.getStrAge(),6);
			daoObj.setProcInValue(nProcIndex, "gender", voObj.getStrGenderCode(),7);
			daoObj.setProcInValue(nProcIndex, "treatment_cat", voObj.getStrTreatmentCat(),8);
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
			
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			temp = util.getOptionValue(ws, "0", "0^Select Value", false);
			voObj.setStrWard(temp);
			if (strErr.equals("")) {
				
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrErrMsgString("PatientAttendanceDAO.ward() --> "
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
	 * to get room combo
	 * @param PatientAttendanceVO - voObj
	 * 
	 */
	public static void room(PatientAttendanceVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp = "";
		HisUtil util = null;
		util = new HisUtil("ipd", "PatientAttendanceDAO");
		String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		daoObj = new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO.getRoomValues()");
		
		try {
			daoObj = new HisDAO("Patient Attendance Transaction","PatientAttendanceDAO.room()");

			util = new HisUtil("ipd", "PatientAttendanceDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modval", "3",1);
			daoObj.setProcInValue(nProcIndex, "roomtypcode","",2);
			daoObj.setProcInValue(nProcIndex, "wardcode", voObj.getStrWard(),3);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),4);
			daoObj.setProcInValue(nProcIndex, "unitcode", voObj.getStrAgeCode(),5);
			daoObj.setProcInValue(nProcIndex, "age", voObj.getStrAge(),6);
			daoObj.setProcInValue(nProcIndex, "deptcode", voObj.getStrDeptCode(),7);
			daoObj.setProcInValue(nProcIndex, "deptunitcode", voObj.getStrDeptUnitCode(),8);
			daoObj.setProcInValue(nProcIndex, "gender", voObj.getStrGenderCode(),9);
			daoObj.setProcInValue(nProcIndex, "treatment_cat", voObj.getStrTreatmentCat(),10);
			daoObj.setProcInValue(nProcIndex, "puk_no", voObj.getStrCrNo(),11);
			daoObj.setProcInValue(nProcIndex, "diseasetypcode", "0",12);
			daoObj.setProcOutValue(nProcIndex, "err", 1,13);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,14);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			temp = util.getOptionValue(ws, "0", "0^Select Value", false);
			voObj.setStrRoom(temp);
			if (strErr.equals("")) {
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			voObj.setStrErrMsgString("PatientAttendanceDAO.room() --> "	+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	/**
	 * to get service name combo
	 * @param PatientAttendanceVO - voObj
	 * 
	 */
	public static void getServiceName(PatientAttendanceVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_get_service_type(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		int qryIndex;
		HisDAO daoObj = null;
		WebRowSet ws = null;
		WebRowSet wb = null;
		String query="";
		String strStatus="";
		try {
				daoObj = new HisDAO("Patient Attendance","PatientAttendanceDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval","2",1);
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
					 strStatus=ws.getString(2);
				}
				
				vo.setStrStatus(strStatus);
				qryIndex = daoObj.setQuery(query);
				daoObj.setQryValue(qryIndex, 1, vo.getStrHospCode());
				
				wb = daoObj.executeQry(qryIndex);
				
				vo.setStrServiceNameWS(wb);
				 
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientAttendanceDAO.getServiceName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	/**
	 * to accept pending patient
	 * @param PatientAttendanceVO - vo
	 * 
	 */
	public static void accept(PatientAttendanceVO vo)
	{
		String strProcName = "{call pkg_ipd_dml.Proc_Pat_Attendance_Acceptance(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		HisDAO daoObj = null;
		try {
				daoObj = new HisDAO("Patient Attendance","PatientAttendanceDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","1",1);
				daoObj.setProcInValue(nProcIndex, "crNo",vo.getStrCrNo(),2); 
				daoObj.setProcInValue(nProcIndex, "admNo", vo.getStrAdmNo(),3);
				daoObj.setProcInValue(nProcIndex, "movNo", vo.getStrMovNo(),4);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),5);
				daoObj.setProcInValue(nProcIndex, "orgDeptCode", "",6);
				daoObj.setProcInValue(nProcIndex, "orgUnitCode", "",7);
				daoObj.setProcInValue(nProcIndex, "orgWardCode", "",8);
				daoObj.setProcInValue(nProcIndex, "orgRoomCode", "",9);
				daoObj.setProcInValue(nProcIndex, "orgBedCode", "",10);
				daoObj.setProcInValue(nProcIndex, "isBedVacant", "",11);
				daoObj.setProcInValue(nProcIndex, "serviceTypeId", "",12);
				daoObj.setProcInValue(nProcIndex, "serviceName", "",13);
				daoObj.setProcInValue(nProcIndex, "serviceDeptCode", "",14);
				daoObj.setProcInValue(nProcIndex, "serviceUnitCode", "",15);
				daoObj.setProcInValue(nProcIndex, "seatId", vo.getStrSeatId(),16);
				daoObj.setProcInValue(nProcIndex, "status", vo.getStrStatusCombo(),17);
				daoObj.setProcInValue(nProcIndex, "remarks", "",18);
				daoObj.setProcOutValue(nProcIndex, "err", 1,19);
				daoObj.setProcOutValue(nProcIndex, "dml_count", 1,20);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				if (strErr.equals("")) {
					vo.setStrMsgType("0");
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("PatientAttendanceDAO.accept() --> "	+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	/**
	 * to reject pending patient
	 * @param PatientAttendanceVO - vo
	 * 
	 */
	public static void reject(PatientAttendanceVO vo)
	{
		/*String strProcName = "{call pkg_ipd_dml.Proc_Patient_Cancel(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		

		String strErr = "";
		HisDAO daoObj = null;
		try {
				daoObj = new HisDAO("Patient Attendance","PatientAttendanceDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "hipnum_admno", vo.getStrAdmNo(),1);
				daoObj.setProcInValue(nProcIndex, "mov_no", vo.getStrMovNo(),20);
				daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospCode(),2);
				daoObj.setProcInValue(nProcIndex, "hrgnum_puk", vo.getStrCrNo(),3);
				daoObj.setProcInValue(nProcIndex, "gnum_seatid", vo.getStrSeatId(),4);
				daoObj.setProcInValue(nProcIndex, "gnum_dept_code", vo.getStrDepartment(),16);
				daoObj.setProcInValue(nProcIndex, "code_remarks", "",5);
				daoObj.setProcInValue(nProcIndex, "belonging_remarks", "",13);
				daoObj.setProcInValue(nProcIndex, "hipnum_isaccepted", "1",7);
				daoObj.setProcInValue(nProcIndex, "gnum_nurse_seatid", vo.getStrSeatId(),8);
				daoObj.setProcInValue(nProcIndex, "hgnum_bed_status_code","12",9);
				daoObj.setProcInValue(nProcIndex, "hipnum_ward_code", vo.getStrWard(),10);
				daoObj.setProcInValue(nProcIndex, "hipnum_room_code", vo.getStrRoom(),11);
				daoObj.setProcInValue(nProcIndex, "hipnum_bed_code", vo.getStrBed(),12);
				daoObj.setProcInValue(nProcIndex, "hipdt_admstatus_code", "12",14);
				daoObj.setProcInValue(nProcIndex, "hgnum_deptunitcode",  vo.getStrUnit(),15);
				daoObj.setProcInValue(nProcIndex, "billFlag",  "",21);
				daoObj.setProcInValue(nProcIndex, "bedChargeFlag",  "",22);// --0 MEANS MANUALLY
				daoObj.setProcOutValue(nProcIndex, "err", 1,23);
				daoObj.setProcOutValue(nProcIndex, "dml_count", 1,24);
				
				daoObj.setProcInValue(nProcIndex, "hipdt_bedalloc_datetime","",6);
				daoObj.setProcInValue(nProcIndex, "gnum_owndept_code","",17);
				daoObj.setProcInValue(nProcIndex, "gnum_owndeptunit_code","",18);
				daoObj.setProcInValue(nProcIndex, "hipnum_ownward_code","",19);
				//daoObj.setProcInValue(nProcIndex, "transferToServiceAreaFlag","1");
				
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				if (strErr.equals("")) {
					vo.setStrMsgType("0");
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientAttendanceDAO.reject() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}*/
		String strProcName = "{call pkg_ipd_dml.Proc_Pat_Attendance_Acceptance(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		HisDAO daoObj = null;
		try {
				daoObj = new HisDAO("Patient Attendance","PatientAttendanceDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","3",1);
				daoObj.setProcInValue(nProcIndex, "crNo",vo.getStrCrNo(),2); 
				daoObj.setProcInValue(nProcIndex, "admNo", vo.getStrAdmNo(),3);
				daoObj.setProcInValue(nProcIndex, "movNo", vo.getStrMovNo(),4);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),5);
				daoObj.setProcInValue(nProcIndex, "orgDeptCode", vo.getStrDepartment(),6);
				daoObj.setProcInValue(nProcIndex, "orgUnitCode", vo.getStrUnit(),7);
				daoObj.setProcInValue(nProcIndex, "orgWardCode", vo.getStrWard(),8);
				daoObj.setProcInValue(nProcIndex, "orgRoomCode", vo.getStrRoom(),9);
				daoObj.setProcInValue(nProcIndex, "orgBedCode",vo.getStrBed(),10);
				daoObj.setProcInValue(nProcIndex, "isBedVacant", "",11);
				daoObj.setProcInValue(nProcIndex, "serviceTypeId", "",12);
				daoObj.setProcInValue(nProcIndex, "serviceName", "",13);
				daoObj.setProcInValue(nProcIndex, "serviceDeptCode", "",14);
				daoObj.setProcInValue(nProcIndex, "serviceUnitCode", "",15);
				daoObj.setProcInValue(nProcIndex, "seatId", vo.getStrSeatId(),16);
				daoObj.setProcInValue(nProcIndex, "status", vo.getStrStatusCombo(),17);
				daoObj.setProcInValue(nProcIndex, "remarks", vo.getStrRemarks(),18);
				daoObj.setProcOutValue(nProcIndex, "err", 1,19);
				daoObj.setProcOutValue(nProcIndex, "dml_count", 1,20);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				
				if (strErr == null)
					strErr = "";
				if (strErr.equals("")) {
					vo.setStrMsgType("0");
				} else {
					throw new Exception(strErr);
				}
				
		} catch (Exception e) {
			
			e.printStackTrace();
			vo.setStrMsgString("PatientAttendanceDAO.reject() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	/**
	 * to transfer patient back to ADT
	 * @param PatientAttendanceVO - vo
	 * 
	 */
	public static void transfer(PatientAttendanceVO vo)
	{
		String strProcName = "{call pkg_ipd_dml.Proc_Pat_Attendance_Acceptance(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		HisDAO daoObj = null;
		try {
				daoObj = new HisDAO("Patient Attendance","PatientAttendanceDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","4",1);
				daoObj.setProcInValue(nProcIndex, "crNo",vo.getStrCrNo(),2); 
				daoObj.setProcInValue(nProcIndex, "admNo", vo.getStrAdmNo(),3);
				daoObj.setProcInValue(nProcIndex, "movNo", vo.getStrMovNo(),4);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),5);
				daoObj.setProcInValue(nProcIndex, "orgDeptCode", vo.getStrDepartment(),6);
				daoObj.setProcInValue(nProcIndex, "orgUnitCode", vo.getStrUnit(),7);
				daoObj.setProcInValue(nProcIndex, "orgWardCode", vo.getStrWard(),8);
				daoObj.setProcInValue(nProcIndex, "orgRoomCode", vo.getStrRoom(),9);
				daoObj.setProcInValue(nProcIndex, "orgBedCode",vo.getStrBed(),10);
				daoObj.setProcInValue(nProcIndex, "isBedVacant", "",11);
				daoObj.setProcInValue(nProcIndex, "serviceTypeId", "",12);
				daoObj.setProcInValue(nProcIndex, "serviceName", "",13);
				daoObj.setProcInValue(nProcIndex, "serviceDeptCode", "",14);
				daoObj.setProcInValue(nProcIndex, "serviceUnitCode", "",15);
				daoObj.setProcInValue(nProcIndex, "seatId", vo.getStrSeatId(),16);
				daoObj.setProcInValue(nProcIndex, "status", vo.getStrStatusCombo(),17);
				daoObj.setProcInValue(nProcIndex, "remarks", vo.getStrRemarks(),18);
				daoObj.setProcOutValue(nProcIndex, "err", 1,19);
				daoObj.setProcOutValue(nProcIndex, "dml_count", 1,20);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				
				if (strErr == null)
					strErr = "";
				if (strErr.equals("")) {
					vo.setStrMsgType("0");
				} else {
					throw new Exception(strErr);
				}
				
		} catch (Exception e) {
			
			e.printStackTrace();
			vo.setStrMsgString("PatientAttendanceDAO.transfer() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	/**
	 * to get patient list
	 * @param PatientAttendanceVO - voObj
	 * 
	 */
	public static void getPatientList(PatientAttendanceVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_get_pending_patList(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;
		
		System.out.println(vo.getStrServiceTypeId()+" "+vo.getStrServiceName()+" "+vo.getStrServiceDeptCode()+" "+vo.getStrServiceUnitCode()+" "+vo.getStrStatus());

		try {
				daoObj = new HisDAO("Patient Attendance","PatientAttendanceDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval","1",1);
				daoObj.setProcInValue(nProcIndex, "serviceTypeId",vo.getStrServiceTypeId(),2); 
				daoObj.setProcInValue(nProcIndex, "serviceNameVal",vo.getStrServiceName(),3);
				daoObj.setProcInValue(nProcIndex, "serviceDept",vo.getStrServiceDeptCode(),4); 
				daoObj.setProcInValue(nProcIndex, "serviceUnit",vo.getStrServiceUnitCode(),5); 
				daoObj.setProcInValue(nProcIndex, "status",vo.getStrStatus(),6); 
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),7);
				daoObj.setProcOutValue(nProcIndex, "err", 1,8);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				if (strErr.equals("")) {
					vo.setStrPatDetailWs(ws);
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientAttendanceDAO.getPatientList() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	/**
	 * to get patient status list for View Process
	 * @param PatientAttendanceVO - vo
	 * 
	 */
	public static void getPatientStatusView(PatientAttendanceVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_get_pat_status_view(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
			
				daoObj = new HisDAO("Patient Attendance","PatientAttendanceDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval","1",1);
				daoObj.setProcInValue(nProcIndex, "serviceTypeId",vo.getStrServiceTypeId(),2); 
				daoObj.setProcInValue(nProcIndex, "serviceNameVal",vo.getStrServiceName(),3);
				daoObj.setProcInValue(nProcIndex, "serviceDept",vo.getStrServiceDeptCode(),4); 
				daoObj.setProcInValue(nProcIndex, "serviceUnit",vo.getStrServiceUnitCode(),5); 
				daoObj.setProcInValue(nProcIndex, "fromDate",vo.getStrEffectiveFrom(),6); 
				daoObj.setProcInValue(nProcIndex, "toDate",vo.getStrEffectiveTo(),7); 
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),8);
				daoObj.setProcOutValue(nProcIndex, "err", 1,9);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				if (strErr.equals("")) {
					vo.setStrPatStatusViewDtlWs(ws);
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientAttendanceDAO.getPatientStatusView() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static String getBedBlkHrs(String obj) {
		// TODO Auto-generated method stub
		
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.get_bed_blk_hrs(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String temp="";
		String strErr = "";
		PatientAttendanceVO vo=null;

		try {
			daoObj = new HisDAO("Patient Belonging","PatBelongingTransDAO");
			
			vo=new PatientAttendanceVO();

			nProcIndex = daoObj.setProcedure(strProcName);
			obj=obj.replace("^","#");
			
			daoObj.setProcInValue(nProcIndex, "modeval","1");
			daoObj.setProcInValue(nProcIndex, "hosp_code",obj.split("#")[2]);
			daoObj.setProcInValue(nProcIndex, "puk",obj.split("#")[0]);
			daoObj.setProcInValue(nProcIndex, "admno",obj.split("#")[1]);
			daoObj.setProcInValue(nProcIndex, "movno",obj.split("#")[3]);
			daoObj.setProcInValue(nProcIndex, "wardno",obj.split("#")[4]);
			daoObj.setProcOutValue(nProcIndex, "err",1);
			daoObj.setProcOutValue(nProcIndex, "resultset",2);

			daoObj.executeProcedure(nProcIndex);

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");			
			strErr = daoObj.getString(nProcIndex, "err");			
			if (strErr == null)
				strErr = "";

	
			if (strErr.equals("")) {
				while(ws.next()){temp =ws.getString(1);temp+="#"+ws.getString(2);}
			} else 				
				throw new Exception(strErr);			
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("PatientAttendanceDAO.getBedBlkHrs() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
		return temp;	
		
	}
}
