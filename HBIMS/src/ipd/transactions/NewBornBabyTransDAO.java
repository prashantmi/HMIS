package ipd.transactions;

import java.util.ResourceBundle;

import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.GetNetworkAddress;

import javax.sql.rowset.WebRowSet;

public class NewBornBabyTransDAO {

	/**
	 * This function is used to set Department name on main page
	 * 
	 * @param vo
	 */
	public static void setDeptName(NewBornBabyTransVO vo) {
		String strProcName = "{call pkg_ipd_view.proc_dept_name(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws.size() == 0) {
				vo.setStrInvalidCrNo("1");
			} else {
				vo.setStrInvalidCrNo("0");
			}
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrDeptName(ws.getString(1));
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setDeptName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	// to match ward room criteria for new born baby while going to allot same bed as mother
	// added by Debashis as per CR 13 jan 2012
	public static void matchWardRoomCriteria(NewBornBabyTransVO vo) {
	
		String strIsWardRoomCriteriaMatch = "";
		String strFunc = "";
		strFunc = "{? = call PKG_SIMPLE_VIEW.Match_Ward_Room_Criteria(?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nfuncIndex = 0;

		try {
			dao = new HisDAO("New Born Baby Admission Transactio",
					"NewBornBabyTransDAO.matchWardRoomCriteria()");

			nfuncIndex = dao.setFunction(strFunc);

			dao.setFuncInValue(nfuncIndex, 2, "1");
			dao.setFuncInValue(nfuncIndex, 3, vo.getStrWardCode());
			dao.setFuncInValue(nfuncIndex, 4, "1"); // as new born baby
			dao.setFuncInValue(nfuncIndex, 5, vo.getStrRoomCode());
			dao.setFuncInValue(nfuncIndex, 6, "1"); // as new born baby
			dao.setFuncInValue(nfuncIndex, 7, vo.getStrSexCode());
			dao.setFuncInValue(nfuncIndex, 8, vo.getStrTreatmentCategoryCode().replace("^", "#").split("#")[0]);
			dao.setFuncInValue(nfuncIndex, 9, "0");
			dao.setFuncInValue(nfuncIndex, 10, vo.getStrHospCode());
			dao.setFuncInValue(nfuncIndex, 11, vo.getStrDobTime());
			dao.setFuncOutValue(nfuncIndex, 1);


			dao.executeFunction(nfuncIndex);

			strIsWardRoomCriteriaMatch = dao.getFuncString(nfuncIndex);
			vo.setStrIsWardRoomCriteriaMatch(strIsWardRoomCriteriaMatch);
			
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.matchWardRoomCriteria() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	/**
	 * This function is used to set unit name on main page
	 * 
	 * @param vo
	 */
	public static void setUnitName(NewBornBabyTransVO vo) {
		String strProcName = "{call pkg_ipd_view.proc_deptunit_name(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrUnitName(ws.getString(1));
					vo.setStrDeptUnitCode(ws.getString(2));
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setUnitName() --> "
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
	 * This function set ward type values in ward type combo on bed details pop
	 * up window
	 * 
	 * @param vo
	 */
	public static void setWardTypeDtl(NewBornBabyTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.PROC_HIPT_WARDTYPE_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		// WebRowSet web=null;

		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				vo.setWardTypeWS(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setWardTypeDtl() --> "
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
	 * This function is used to details in room type combo on bed status pop up
	 * window
	 * 
	 * @param vo
	 */
	public static void setRoomTypeDtl(NewBornBabyTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomtype_a(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval","1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setRoomTypeWS(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {

			vo.setStrMsgString("NewBornBabyTransDAO.setRoomTypeDtl() --> "
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
	 * 
	 * @param vo
	 */
	public static void getBedStatusDtl(PatientAdmissionTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_ipd_bedstatus_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "pukno", vo.getStrCrNo(),1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrWardTypeCode(ws.getString(1));
					vo.setStrWardCode(ws.getString(2));
					vo.setStrBedTypeCode(ws.getString(3));
					vo.setStrRoomTypeCode(ws.getString(4));
					vo.setStrWardName(ws.getString(5));
					vo.setStrDeptCode(ws.getString(6));
					vo.setStrMsApprovalFlag(ws.getString(7));
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {

			vo.setStrMsgString("NewBornBabyTransDAO.getBedStatusDtl() --> "
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
	 * This function is used to bring ward details in ward combo on bed details
	 * pop window
	 * 
	 * @param vo
	 */
	public static void getWardValues(NewBornBabyTransVO vo) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		
		
		
		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

	daoObj = new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO.getWardValues()");
		try
		{	
			
			daoObj = new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO");
					
			nProcIndex = daoObj.setProcedure(strProcName);	
						
			
			daoObj.setProcInValue(nProcIndex, "modeVal","11",1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode",vo.getStrWardTypeCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptcode",vo.getStrDeptCode(),3);
			daoObj.setProcInValue(nProcIndex, "deptunitcode",vo.getStrDeptUnitCode(),4);
			daoObj.setProcInValue(nProcIndex, "unitcode","1",5);
			daoObj.setProcInValue(nProcIndex, "age","1",6);
			daoObj.setProcInValue(nProcIndex, "gender",vo.getStrSexCode(),7);
			daoObj.setProcInValue(nProcIndex, "treatment_cat",vo.getStrTreatmentCategoryCode(),8);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),9);
			daoObj.setProcInValue(nProcIndex, "effect_from","",10);
			daoObj.setProcInValue(nProcIndex, "effect_to","",11);
			daoObj.setProcInValue(nProcIndex, "diseasetypcode","0",12);
			daoObj.setProcInValue(nProcIndex, "wardcode","0",13);
			daoObj.setProcInValue(nProcIndex, "puk_no",vo.getStrCrNo(),14);
			daoObj.setProcInValue(nProcIndex, "charge_type_id","0",15);
			daoObj.setProcOutValue(nProcIndex, "err", 1,16);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,17);
			
			
			
			        
	        
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			System.out.println("ws.size"+ws.size());
			if (strErr.equals("")) {
				vo.setWardWS(ws);
			} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.getWardValues() --> "
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
	 * This function is used to bring room details in room combo on the basis of
	 * room type code
	 * 
	 * @param vo
	 */
	public static void getRoomValues(NewBornBabyTransVO vo) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?::character varying,?)}";
		int nProcIndex = 0;

		String strErr = "";

		daoObj = new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO.getRoomValues()");
		try
		{
				daoObj = new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO");
						
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","3",1);
				daoObj.setProcInValue(nProcIndex, "roomtypcode",vo.getStrRoomTypeCode(),2);
				daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),3);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),4);
				daoObj.setProcInValue(nProcIndex, "unitcode","1",5);
				daoObj.setProcInValue(nProcIndex, "age","1",6);
				daoObj.setProcInValue(nProcIndex, "deptcode","",7);
				daoObj.setProcInValue(nProcIndex, "deptunitcode",vo.getStrDeptUnitCode(),8);
				daoObj.setProcInValue(nProcIndex, "gender",vo.getStrSexCode(),9);
				daoObj.setProcInValue(nProcIndex, "treatment_cat",vo.getStrTreatmentCategoryCode(),10);
				daoObj.setProcInValue(nProcIndex, "puk_no",vo.getStrCrNo(),11);
				daoObj.setProcInValue(nProcIndex, "diseasetypcode","0",12);
				daoObj.setProcOutValue(nProcIndex, "err", 1,13);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,14);
				
				
				
				    
				daoObj.executeProcedureByPosition(nProcIndex);
			
			
			/*daoObj = new HisDAO("New Born Baby Admission Transaction",
					"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1");
			daoObj.setProcInValue(nProcIndex, "roomtypcode", vo
					.getStrRoomTypeCode());
			daoObj.setProcInValue(nProcIndex, "wardcode", vo.getStrWardCode());
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);*/
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				vo.setRoomWs(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("NewBornBabyTransDAO.getRoomValues() --> "
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
	 * This function is used to bring details in bed type combo in bed details
	 * pop up window
	 * 
	 * @param voObj
	 */
	public static void setBedTypeDtl(NewBornBabyTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_bed_type_mst(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				voObj.setBedTypeWS(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {

			voObj.setStrMsgString("NewBornBabyTransDAO.setBedTypeDtl() --> "
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
	 * @param vo
	 */
	public static void getBedValues(NewBornBabyTransVO vo) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		daoObj = new HisDAO("New Born Baby Admission Transaction",
				"PatientAdmissionTransDAO");
		try {

			daoObj = new HisDAO("New Born Baby Admission  Transaction","NewBornBabyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "wardcode", vo.getStrWardCode(),2);
			daoObj.setProcInValue(nProcIndex, "roomno", vo.getStrRoom(),3);
			daoObj.setProcInValue(nProcIndex, "bedtypcode", vo.getStrBedTypeCode(),4);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),6);
			daoObj.setProcInValue(nProcIndex, "deptunit", vo.getStrDeptUnitCode(),7);
			daoObj.setProcInValue(nProcIndex, "shr_chk", "",9);
			daoObj.setProcOutValue(nProcIndex, "err", 1,10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
			
			daoObj.setProcInValue(nProcIndex, "bedstatcode", "",5);
			daoObj.setProcInValue(nProcIndex, "bedCode", "",8);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setBedDetailWS(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			vo.setStrMsgString("NewBornBabyTransDAO.getBedValues() --> "
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
	 * This function is used to count the number of vacant beds in a ward on the
	 * basis of ward code,room code and bed type code
	 * 
	 * @param vo
	 */
	public static void countBed_in_ward(NewBornBabyTransVO vo) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		daoObj = new HisDAO("New Born Baby Admission Transaction",
				"PatientAdmissionTransDAO");
		try {
			daoObj = new HisDAO("Patient Admission  Transaction",
					"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "2",1);
			daoObj.setProcInValue(nProcIndex, "wardcode", vo.getStrWardCode(),2);
			daoObj.setProcInValue(nProcIndex, "roomno", vo.getStrRoom(),3);
			daoObj.setProcInValue(nProcIndex, "bedtypcode", vo.getStrBedTypeCode(),4);
			daoObj.setProcInValue(nProcIndex, "bedstatcode", "10",5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),6);
			daoObj.setProcInValue(nProcIndex, "deptunit", vo.getStrDeptUnitCode(),7);
			daoObj.setProcInValue(nProcIndex, "bedcode", "",8);
			daoObj.setProcInValue(nProcIndex, "shr_chk", "",9);
			daoObj.setProcOutValue(nProcIndex, "err", 1,10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrVacantBed(ws.getString(1));
				}

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.countBed_in_ward() --> "
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
	 * This function is used to set treatment category name and treatment
	 * category on the main page
	 * 
	 * @param vo
	 */
	public static void setTreat_CatName(NewBornBabyTransVO vo) 
	{
		String strProcName = "{call pkg_ipd_view.proc_ipd_treatment_cat_name(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrTreatmentCategoryName(ws.getString(1));
					vo.setStrTreatmentCategoryCode(ws.getString(2));
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {

			vo.setStrMsgString("NewBornBabyTransDAO.setTreat_CatName() --> "+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to set patient details in hidden fields on the main
	 * page
	 * 
	 * @param vo
	 */
	public static void setPatientStatus(NewBornBabyTransVO vo) 
	{
		String strProcName = "{call pkg_ipd_view.proc_pat_status(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
			daoObj = new HisDAO("New Born Baby", "NewBornBabyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "2",1);
			daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrPatCatCode(ws.getString(1));
					vo.setStrIsUrban(ws.getString(2));
					vo.setStrPatStatusCode(ws.getString(3));
					vo.setStrPatientIsNotAccepted(ws.getString(5));
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("NewBornBabyTransDAO.setPatientStatus() --> "
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
	 * This function is used to display consultant name on main page who give
	 * advice to patient
	 * 
	 * @param vo
	 */
	public static void setConsultantName(NewBornBabyTransVO vo) {
		String strProcName = "{call pkg_ipd_view.proc_consultant(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		// String strDeptUnitCode = vo.getStrDeptUnitCode();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "PUKNO", strCrNum,1);
			daoObj.setProcInValue(nProcIndex, "DEPTUNIT", vo.getStrMotherUnitCode(),2);
			daoObj.setProcInValue(nProcIndex, "HOSP_CODE", vo.getStrHospCode(),3);
			daoObj.setProcOutValue(nProcIndex, "ERR", 1,4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrConsultantCode(ws.getString(1));
					vo.setStrConsultantName(ws.getString(2));
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setConsultantName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static boolean insert(NewBornBabyTransVO vo) {

		boolean retVal2 = false;
		HisDAO daoObj = null;
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			retVal2 = NewBornBabyTransDAO.insertPatAdmission(daoObj, vo);

		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.insert() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
		return retVal2;
	}

	/**
	 * This function is used to insert record into database
	 * 
	 * @param daoObj
	 * @param vo
	 * @return
	 */
	public static boolean insertPatAdmission(HisDAO daoObj,NewBornBabyTransVO vo) {

		String strProcName1 = "";
		String strProcName2 ="{call pkg_ipd_dml.proc_ip_mac(?,?,?,?,?,?,?,?)}";
		String err = new String();
		String admno = "";
		String cr_no = "";
		int nProcIndex1 = 0;
		int nProcIndex2 = 0;
		String strCrNoSize = "";
		IpdConfigUtil config=new IpdConfigUtil(vo.getStrHospCode());
		boolean chkFlag = false;
		try {
			StringBuffer buff = new StringBuffer();
			strCrNoSize = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties").getString("CR_FORMAT");
			String strAddress = vo.getStrHouseNo() + "," + vo.getStrStreet()+ "," + vo.getStrCity();
			vo.setStrAddress(strAddress);
			vo.setStrMotherCrNo(vo.getStrCrNo());
			buff.append("{call pkg_ipd_dml.Proc_Patient_newborn_Adm(");
			
			for (int i = 0; i <= 68; i++) {
				if (i == 68)
					buff.append("?");
				else
					buff.append("?,");
			}
			if(config.getStrNewBornBabyProcessType().equals("1"))
				buff.append(",?,?");
			else buff.append(",?,?");
			//buff.append(",?,?,?");
			buff.append(",?,?,?,?,?,?,?,?,?");
			buff.append(")}");
			strProcName1 = buff.toString();

			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			
			daoObj.setProcInValue(nProcIndex1, "gnum_hospital_code", vo.getStrHospCode(),1);
			daoObj.setProcInValue(nProcIndex1, "cr_size", strCrNoSize,2);
			daoObj.setProcInValue(nProcIndex1, "hrgdt_dob", vo.getStrDobTime(),3);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_fname", vo.getStrBabyName(),4);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_mname", "",5);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_lname", "",6);
			
			/*if(config.getStrNewBornBabyProcessType().equals("1"))
			{
				//online
				daoObj.setProcInValue(nProcIndex1, "gravidano", vo.getStrGravidaNo(),5);
				daoObj.setProcInValue(nProcIndex1, "slno", vo.getStrSlNo(),6);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "gravidano","",5);
				daoObj.setProcInValue(nProcIndex1, "slno","",6);
			}*/
			
			daoObj.setProcInValue(nProcIndex1, "gnum_gender_code", vo.getStrGender(),7);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_idmark1", vo.getStrIdMark1(),8);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_father_name", vo.getStrFatherName(),9);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_momname", vo.getStrMotherName(),10);
			if(vo.getStrMotherTreatmentCateg().replace("^", "#").split("#")[0].equals("0"))
			{
				daoObj.setProcInValue(nProcIndex1, "hgnum_patient_cat_code", vo.getStrPatCatCode(),11);			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "hgnum_patient_cat_code", vo.getStrMotherTreatmentCateg().replace("^", "#").split("#")[0],11);
			}
			
			
			daoObj.setProcInValue(nProcIndex1, "gnum_seat_id", vo.getStrSeatId(),12);
			daoObj.setProcInValue(nProcIndex1, "hrgnum_month_income", vo.getStrOccBasic(),13);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_ip_add", vo.getStrIpAddress(),14);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_contact_no", vo.getStrPhoneNo(),15);
			daoObj.setProcInValue(nProcIndex1, "gnum_religion_code", vo.getStrReligion(),16);
			daoObj.setProcInValue(nProcIndex1, "gnum_nationality_code", vo.getStrMotherNationalityCode(),17);
			daoObj.setProcInValue(nProcIndex1, "hrgnum_mother_puk", vo.getStrCrNo(),18);
			
			daoObj.setProcInValue(nProcIndex1, "hrgstr_idmark2", vo.getStrIdMark2(),19);
			daoObj.setProcInValue(nProcIndex1, "hipnum_isrural", vo.getStrIsUrban(),20);
			daoObj.setProcInValue(nProcIndex1, "gnum_relation_code", vo.getStrOccRelation(),21);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_city", vo.getStrCity(),22);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_district", vo.getStrDistrict(),23);
			daoObj.setProcInValue(nProcIndex1, "gnum_state_code", vo.getStrState(),24);
			daoObj.setProcInValue(nProcIndex1, "hrgnum_pincode", vo.getStrPinCode(),25);
			daoObj.setProcInValue(nProcIndex1, "gnum_country_code", vo.getStrCountry(),26);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_house_no", vo.getStrHouseNo(),27);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_street_no", vo.getStrStreet(),28);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_phone_no", vo.getStrPhoneNo(),29);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_mobile_no", vo.getStrMobileNo(),30);
			daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code", vo.getStrUnitNewBorn(),31);
			daoObj.setProcInValue(nProcIndex1, "gnum_dept_code", vo.getStrDeptNameNewBorn(),32);
			daoObj.setProcInValue(nProcIndex1, "hipnum_room_code", vo.getStrRoomCode(),33);
			//System.out.println("Ward"+vo.getStrWard()+"dsdfs"+vo.getStrWardCode()+"Room"+vo.getStrRoomCode());
			daoObj.setProcInValue(nProcIndex1, "hipnum_ward_code", vo.getStrWard(),34);
			daoObj.setProcInValue(nProcIndex1, "hipnum_bed_type", vo.getStrMotherBedTypeTypeCode(),35);
			daoObj.setProcInValue(nProcIndex1, "hipnum_room_type", vo.getStrMotherRoomTypeCode(),36);
			
			if(vo.getStrMotherTreatmentCateg().replace("^", "#").split("#")[0].equals("0"))
			{
				daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrTreatmentCategoryCode().replace("^", "#").split("#")[0],37);
			}
			else
			{
				daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrMotherTreatmentCateg().replace("^", "#").split("#")[0],37);
			}
			
			/*if(vo.getStrRegistrationChargeHidden().equals("0"))
			daoObj.setProcInValue(nProcIndex1, "hrgnum_reg_amt_collected", "0",38);
			else*/
			daoObj.setProcInValue(nProcIndex1, "hrgnum_reg_amt_collected",vo.getStrRegistrationChargeHidden(),38);	
			
			//daoObj.setProcInValue(nProcIndex1, "hrgnum_adm_amt_collected", vo.getStrAmountChargeFinal(),39);
			daoObj.setProcInValue(nProcIndex1, "hrgnum_adm_amt_collected", vo.getStrAdmissionChargeValue(),39);
			daoObj.setProcInValue(nProcIndex1, "hipnum_moth_room_code", vo.getStrRoomCode(),40);
			daoObj.setProcInValue(nProcIndex1, "hipnum_moth_ward_code", vo.getStrMotherWardCode(),41);
			daoObj.setProcInValue(nProcIndex1, "hipnum_moth_bed_code", vo.getStrBedCode(),42);
			daoObj.setProcInValue(nProcIndex1, "hipnum_mothadmno", vo.getStrMotherAdmissionNo(),43);
			daoObj.setProcInValue(nProcIndex1, "hipnum_consultant_id", vo.getStrConsNewBorn(),44);
			daoObj.setProcInValue(nProcIndex1, "hipstr_officetel", vo.getStrOccOffPhNo(),45);
			daoObj.setProcInValue(nProcIndex1, "hipnum_org_type", vo.getStrOccOrgType(),46);
			daoObj.setProcInValue(nProcIndex1, "hipstr_empno", vo.getStrOccEmpNo(),47);
			daoObj.setProcInValue(nProcIndex1, "hipstr_empname", vo.getStrOccEmpName(),48);
			daoObj.setProcInValue(nProcIndex1, "hipstr_desig", vo.getStrOccDesc(),49);
			daoObj.setProcInValue(nProcIndex1, "hipstr_officename", vo.getStrOccOffName(),50);
			daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd1", vo.getStrOccAdd1(),51);
			daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd2", vo.getStrOccAdd2(),52);
			daoObj.setProcInValue(nProcIndex1, "hipstr_officecity", vo.getStrOccCity(),53);
			daoObj.setProcInValue(nProcIndex1, "hipstr_officestate", vo.getStrOccState(),54);
			daoObj.setProcInValue(nProcIndex1, "hipnum_isgovtemp", vo.getStrOccIsGovtEmp(),55);
			daoObj.setProcInValue(nProcIndex1, "hipnum_basic", vo.getStrOccBasic(),56);
			daoObj.setProcInValue(nProcIndex1, "gnum_desig_code", "",57);
			daoObj.setProcInValue(nProcIndex1, "gnum_owndept_code",IpdTransConfig.getOwnDeptCode(),58);
			
			
			daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code",IpdTransConfig.getOwnUnitCode(),59);
			daoObj.setProcInValue(nProcIndex1, "hipnum_ownward_code",IpdTransConfig.getOwnWardCode(),60);
			daoObj.setProcInValue(nProcIndex1, "no_of_free_pass", vo.getStrNoOfFreePass(),61);
			daoObj.setProcInValue(nProcIndex1, "pass_valid_for", vo.getStrNoOfFreePassValidity(),62);
			daoObj.setProcInValue(nProcIndex1, "admissionchargeflag", vo.getStrAdmissionCharge(),63);
			daoObj.setProcInValue(nProcIndex1, "billflag", vo.getStrBillFlag(),64);
			if(config.getStrNewBornBabyProcessType().equals("1"))
			{
				daoObj.setProcInValue(nProcIndex1, "slno", vo.getStrSlNo(),65);
				daoObj.setProcInValue(nProcIndex1, "gravidano", vo.getStrGravidaNo(),66);
			}
			else
			{
				
				daoObj.setProcInValue(nProcIndex1, "slno","",65);
				daoObj.setProcInValue(nProcIndex1, "gravidano","",66);
			}
			
			daoObj.setProcInValue(nProcIndex1, "gnum_caste_code",vo.getStrPatientCaste(),67);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_tehsil",vo.getStrTehsil(),68);
			daoObj.setProcInValue(nProcIndex1, "hgnum_relief_fund_code",vo.getStrReliefFund(),69);
			daoObj.setProcInValue(nProcIndex1, "hgnum_admission_type_code", vo.getStrAdmissionType(),70);
			
			daoObj.setProcInValue(nProcIndex1, "samebedasmother",vo.getSameBedAsMother(),71);
			daoObj.setProcInValue(nProcIndex1, "bedcode",vo.getStrBedCode(),72);
			daoObj.setProcOutValue(nProcIndex1, "err", 1,73);// 1 for string return
			daoObj.setProcOutValue(nProcIndex1, "adm_no", 1,74);
			daoObj.setProcOutValue(nProcIndex1, "cr_no", 1,75);	
			daoObj.setProcInValue(nProcIndex1, "newBornDeleivered",vo.getStrNumberOfChildrenBorn(),76);
			daoObj.setProcInValue(nProcIndex1, "hrgnum_casesheetno", vo.getStrCaseSheetNo(),77);
			daoObj.setProcInValue(nProcIndex1, "hrgstr_state_name", vo.getStrStateName(),78);
			daoObj.setProcInValue(nProcIndex1, "district_code", vo.getStrDistrictCode(),79);
			daoObj.setProcInValue(nProcIndex1, "regChargeFlag", vo.getStrNewBornRegistrationCharge(),80);

			daoObj.executeProcedureByPosition(nProcIndex1);
			err = daoObj.getString(nProcIndex1, "err");
			admno = daoObj.getString(nProcIndex1, "adm_no");
			cr_no = daoObj.getString(nProcIndex1, "cr_no");
			
			nProcIndex2 = daoObj.setProcedure(strProcName2);
			daoObj.setProcInValue(nProcIndex2, "modeval", "1",1);									
			daoObj.setProcInValue(nProcIndex2, "adm_no", admno,2);	//							
			daoObj.setProcInValue(nProcIndex2, "hospital_code",  vo.getStrHospCode(),3);					
			daoObj.setProcInValue(nProcIndex2, "puk", cr_no,4);		
			daoObj.setProcInValue(nProcIndex2, "mov_no", "",5);	
			daoObj.setProcInValue(nProcIndex2, "ip", GetNetworkAddress.GetAddress("ip"),6);			
			daoObj.setProcInValue(nProcIndex2, "mac", GetNetworkAddress.GetAddress("mac"),7);		//				
			daoObj.setProcOutValue(nProcIndex2, "err", 1,8);		//			
			daoObj.executeProcedureByPosition(nProcIndex2);
			
			if (err.equals("")) 
			{
				chkFlag = true;
				vo.setStrAdmNo(admno);
				vo.setStrCrNo(cr_no);
				vo.setStrMsgType("0");
			} 
			else 
			{
				throw new Exception();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			chkFlag = false;
			vo.setStrMsgString("NewBornBabyTransDAO.insertPatAdmission() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}

		return chkFlag;
	}

	/**
	 * This function is used to set all details of particular patient regarding
	 * to episode details on the main page in their corresponding hidden fields
	 * 
	 * @param vo
	 */
	public static void setEpisodeDtl(PatientAdmissionTransVO vo) {
		String strProcName = "{call pkg_ipd_view.proc_episode_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "dept_code", "",3);
			daoObj.setProcInValue(nProcIndex, "unit_code", "",4);

			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrEpisodeCode(ws.getString(1));
					vo.setStrVisitNo(ws.getString(2));
					vo.setStrMlcNo(ws.getString(3));
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setEpisodeDtl() --> "
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
	 * This function set Admission advice number,booking date,NewBorn status on
	 * the main page
	 * 
	 * @param vo
	 */
	public static void setAdviceAdmNo(PatientAdmissionTransVO vo) {
		String strProcName = "{call pkg_ipd_view.proc_admission_advice_dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,11);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,12);
			
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "adv_frm_validity", "0",4);
			daoObj.setProcInValue(nProcIndex, "adv_to_validity", "0",5);
			daoObj.setProcInValue(nProcIndex, "patListType", "0",6);//--ONLINE ADVICE PATIENT LISTING
			daoObj.setProcInValue(nProcIndex, "searchStr", "",7);
			daoObj.setProcInValue(nProcIndex, "searchType", "1",8);
			daoObj.setProcInValue(nProcIndex, "toRows", "0",9);
			daoObj.setProcInValue(nProcIndex, "frmRows", "0",10);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws.size() == 0) {
				vo.setStrAdviceStatus("0");
			} else
				vo.setStrAdviceStatus("1");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrAdviceAdmNo(ws.getString(1));
					vo.setStrBookingDate(ws.getString(2));
					vo.setStrNewBorn(ws.getString(3));
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setAdviceAdmNo() --> "
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
	 * This function is used to bring address of a patient on the main page
	 * 
	 * @param vo
	 */
	public static void getPatientAdd(NewBornBabyTransVO vo) {
		// String strProcName = "{call
		// pkg_ipd_view.proc_pat_demographicdtl(?,?,?,?)}";
		String strProcName = "{call pkg_simple_view.proc_pat_demographicdtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum;
		if (vo.getStrNewBorn().equals("1")) {
			strCrNum = vo.getStrMotherCrNo();
		} else {
			strCrNum = vo.getStrCrNo();
		}
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "puk", strCrNum,1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (ws.next()) {

					vo.setStrReligionCode(ws.getString(5));
					vo.setStrHouseNo(ws.getString(6));
					vo.setStrStreet(ws.getString(7));
					vo.setStrPhoneNo(ws.getString(8));
					vo.setStrMobileNo(ws.getString(9));
					vo.setStrCity(ws.getString(10));
					//vo.setStrDistrict(ws.getString(11));
					vo.setStrStateCode(ws.getString(12));
					vo.setStrCountryCode(ws.getString(13));
					vo.setStrPinCode(ws.getString(14));
					vo.setStrFatherName(ws.getString(15));
					vo.setStrStateName(ws.getString(18));
					vo.setStrDistrictName(ws.getString(11));
					vo.setStrPatientCaste(ws.getString(19));
					vo.setStrDistrictCode(ws.getString(24));
				}
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.insertOccupationDtl() --> "
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
	 * This function is used to bring religion detail of a patient on main page
	 * 
	 * @param vo
	 */
	public static void setReligion(NewBornBabyTransVO vo) {

		String strProcName = "{call pkg_ipd_view.Proc_religion_dtl(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setReligionWs(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setReligion() --> "
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
	 * This function is used to set state on main page to which patient belong
	 * 
	 * @param vo
	 */
	public static void setState(NewBornBabyTransVO vo) {

		String strProcName = "{call pkg_ipd_view.Proc_state_dtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setStateWS(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setState() --> "
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
	 * This function is used to bring ward,room and bed details in case of Ms
	 * Approval
	 * 
	 * @param vo
	 */
	public static void getPatDtl_Msapproval(PatientAdmissionTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_pat_adm_msapproval(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1");
			daoObj.setProcInValue(nProcIndex, "pukno", vo.getStrCrNo());
			daoObj.setProcInValue(nProcIndex, "advno", vo.getStrAdviceAdmNo());
			daoObj.setProcInValue(nProcIndex, "bookingdate", vo.getStrBookingDate());
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrWardTypeCode(ws.getString(1));
					vo.setStrWardCode(ws.getString(2));
					vo.setStrDeptCode(ws.getString(3));
					vo.setStrRoomCode(ws.getString(4));
					vo.setStrBedCode(ws.getString(5));
					vo.setStrMsApprovalStatus(ws.getString(6));
					vo.setStrWardName(ws.getString(7));
					vo.setStrRoom(ws.getString(8));
				}

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			vo
					.setStrMsgString("NewBornBabyTransDAO.getPatDtl_Msapproval() --> "
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
	 * This function is used to bring details in country combo
	 * 
	 * @param vo
	 */
	public static void setCountry(NewBornBabyTransVO vo) {

		String strProcName = "{call pkg_ipd_view.Proc_state_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "2",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr.equals("")) {
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setCountryWS(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setCountry() --> "
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
	 * 
	 * @param vo
	 */
	public static void getMsApprovalStatus(NewBornBabyTransVO vo) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
		try {
			daoObj = new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "7");
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode());
			daoObj.setProcInValue(nProcIndex, "wardcode", vo.getStrWardCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "wardtypcode","0");
			daoObj.setProcInValue(nProcIndex, "unitcode","0");
			daoObj.setProcInValue(nProcIndex, "deptcode","0");
			daoObj.setProcInValue(nProcIndex, "deptunitcode","0");
			daoObj.setProcInValue(nProcIndex, "age","0");
			daoObj.setProcInValue(nProcIndex, "gender","0");
			daoObj.setProcInValue(nProcIndex, "treatment_cat","0");
			daoObj.setProcInValue(nProcIndex, "puk_no","0");			
			daoObj.setProcInValue(nProcIndex, "effect_from","");
			daoObj.setProcInValue(nProcIndex, "effect_to","");
			daoObj.setProcInValue(nProcIndex, "diseasetypcode","0");			
			daoObj.setProcInValue(nProcIndex, "charge_type_id","0");    
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrMsApprovalFlag(ws.getString(1));
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.getWardValues() --> "
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
	 * This function is used to set inital parameters during new born baby case
	 * 
	 * @param vo
	 */
	public static void setNewBornBabyDtl(NewBornBabyTransVO vo) 
	{
		String strProcName = "{call pkg_ipd_view.proc_mother_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;
		IpdConfigUtil icu = null;
		
			

		String strCrNum = vo.getStrCrNo();
		try 
		{
			daoObj = new HisDAO("ADT","NewBornBabyAdmissionTransDAO");
			icu   =  new IpdConfigUtil(vo.getStrHospCode());
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "puk", strCrNum,1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws.size() == 0) 
			{
				vo.setStrValidCrNo("1");
			}
			if (strErr.equals("")) 
			{
				if (ws.next()) 
				{
					vo.setStrMotherName(ws.getString(1));
					vo.setStrMotherAdmissionNo(ws.getString(3));
					vo.setStrMotherNationalityCode(ws.getString(4));
					vo.setStrMotherNationality(ws.getString(5));
					vo.setStrMotherReligionCode(ws.getString(6));
					vo.setStrMotherReligion(ws.getString(7));
					vo.setStrRoomCode(ws.getString(8));
					vo.setStrRoom(ws.getString(9));
					vo.setStrBedCode(ws.getString(11));
					vo.setStrMotherDeptCode(ws.getString(12));
					vo.setStrMotherUnitCode(ws.getString(13));
					vo.setStrMotherDeptName(ws.getString(14));
					vo.setStrMotherUnitName(ws.getString(15));
					vo.setStrMotherWardCode(ws.getString(16));
					vo.setStrMotherWardName(ws.getString(17));
					vo.setStrMotherWardTypeCode(ws.getString(18));
					vo.setStrMotherRoomTypeCode(ws.getString(19));
					vo.setStrMotherBedTypeTypeCode(ws.getString(20));
					vo.setStrTreatmentCategoryCode(ws.getString(21));
					vo.setStrTreatmentCategoryName(ws.getString(22));
					vo.setStrConsultantCode(ws.getString(23));
					vo.setStrConsultantName(ws.getString(24));
					vo.setStrMaxBabyAllowed(ws.getString(25));
					vo.setStrIsBedSharable(ws.getString(26));
					vo.setStrGenderCode(ws.getString(29));
					vo.setStrNumberOfChildrenBorn(ws.getString(30));
					vo.setStrAge(ws.getString(31));
					vo.setSetStrMotherCatgrp(ws.getString(32));
					//vo.setStrDeptNameNewBorn(vo.getStrMotherDeptCode());   //for Default Mother department first time
					vo.setStrDeptNameNewBorn(icu.getStrNewBornBabyDefaultDept());
					vo.setStrMotherRoomCode(ws.getString(8));
					vo.setStrMothAdmDate(ws.getString(33));
				}
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{

			vo.setStrMsgString("NewBornBabyTransDAO.setNewBornBabyDtl() --> "+ e.getMessage());
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

	/*
	 * This function is used to bring details in department combo.
	 */
	public static void department(NewBornBabyTransVO vo) {

		String strproc_name = "{call pkg_ipd_view.Proc_Department(?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {

			dao = new HisDAO("ipd","transactions.NewBornBabyTransDAO.department()");
			nprocIndex = dao.setProcedure(strproc_name);

			// set value
			dao.setProcInValue(nprocIndex, "modeval", "3",1);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospCode(),2);
			dao.setProcOutValue(nprocIndex, "err", 1,5); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,6); // 2 for object
			
			dao.setProcInValue(nprocIndex, "puk", "",3);
			dao.setProcInValue(nprocIndex, "seatId", "",4);
			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				vo.setDepartWS(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.department()--> "+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	// this function is used for unit combo on the basis of department

	public static void unit(NewBornBabyTransVO vo) {
		String strproc_name = "{call pkg_ipd_view.Proc_Unit(?,?,?,?::character varying,?::character varying,?,?,?,?)}";
	
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("ipd","transactions.PatientLeaveDAO.unit()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value

			dao.setProcInValue(nprocIndex, "dept_code", vo.getStrDeptNameNewBorn(),2);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospCode(),3);
			dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
			
			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcInValue(nprocIndex, "puk", "",4);
			dao.setProcInValue(nprocIndex, "seatId", "",5);
			dao.setProcInValue(nprocIndex, "wardcode", "",6);
			dao.setProcInValue(nprocIndex, "unitcode", "",7);
			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				vo.setUnitWS(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.unit() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}

	/**
	 * This procedure is used to set intial information for treatment category
	 * combo
	 * 
	 * @param voObj
	 */
	public static void setTreatmentCatDtl(NewBornBabyTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call PKG_SIMPLE_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "4",1);
			//daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
			
			daoObj.setProcInValue(nProcIndex, "puk_no", "",3);
			daoObj.setProcInValue(nProcIndex, "charge_type_id", "0",4);
			daoObj.setProcInValue(nProcIndex, "effect_from", "",5);
			daoObj.setProcInValue(nProcIndex, "effect_TO", "",6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				voObj.setTreatmentCategWS(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("NewBornBabyTransDAO.setTreatmentCatDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void setChargeVal(NewBornBabyTransVO vo) {
		String strProcName = "{? = call BILL_MST.getChargeDetails(?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::character varying)}";
		int nProcIndex = 0;
		String val = "0";

		HisDAO daoObj = null;
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");
			nProcIndex = daoObj.setFunction(strProcName);
			daoObj.setFuncInValue(nProcIndex, 2, "3");
			daoObj.setFuncInValue(nProcIndex, 3, vo.getStrHospCode());
			daoObj.setFuncInValue(nProcIndex, 4, vo.getStrMotherWardCode());
			daoObj.setFuncInValue(nProcIndex, 5, (!vo.getStrTreatmentCategoryCode().equals("") ? vo.getStrTreatmentCategoryCode().replace("^", "#").split("#")[0] : "0"));
			daoObj.setFuncInValue(nProcIndex, 6, "2");
			daoObj.setFuncInValue(nProcIndex, 7, "0");
			daoObj.setFuncInValue(nProcIndex, 8, "");
			daoObj.setFuncOutValue(nProcIndex, 1);
			daoObj.executeFunction(nProcIndex);
			val = daoObj.getFuncString(nProcIndex);
			vo.setStrAdmissionChargeValue(val);

		} catch (Exception e) {

			vo.setStrMsgString("NewBornBabyTransDAO.setChargeVal() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void setRegistrationVal(NewBornBabyTransVO vo) {
		String strProcName = "{? = call BILL_MST.getChargeDetails(?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric)}";
		int nProcIndex = 0;
		String val = "0";

		HisDAO daoObj = null;
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");
			nProcIndex = daoObj.setFunction(strProcName);
			daoObj.setFuncInValue(nProcIndex, 2, "11");
			daoObj.setFuncInValue(nProcIndex, 3, vo.getStrHospCode());
			daoObj.setFuncInValue(nProcIndex, 4, vo.getStrUnitNewBorn()); // here unit is passed in ward to calculate registration charges based on unit type
			daoObj.setFuncInValue(nProcIndex, 5, (!vo.getStrTreatmentCategoryCode().equals("") ?vo.getStrTreatmentCategoryCode().replace("^", "#").split("#")[0] : "0"));
			daoObj.setFuncInValue(nProcIndex, 6, "0");
			daoObj.setFuncInValue(nProcIndex, 7, "0");
			daoObj.setFuncOutValue(nProcIndex, 1);
			daoObj.executeFunction(nProcIndex);
			val = daoObj.getFuncString(nProcIndex);
			vo.setStrNewBornRegistrationChargeVal(val);

		} catch (Exception e) {

			vo.setStrMsgString("NewBornBabyTransDAO.setChargeVal() --> "+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	/**
	 * set Advance Amount on screen
	 * @param vo
	 */
	public static void setAdvanceAmountVal(NewBornBabyTransVO vo) {
		String strProcName = "{? = call BILL_MST.get_advance_part_amt(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String val = "0";

		HisDAO daoObj = null;
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");
			nProcIndex = daoObj.setFunction(strProcName);
			daoObj.setFuncInValue(nProcIndex, 2, "1");
			daoObj.setFuncInValue(nProcIndex, 3, vo.getStrHospCode());
			daoObj.setFuncInValue(nProcIndex, 4, vo.getStrMotherWardCode());
			daoObj.setFuncInValue(nProcIndex, 5,"2");
			daoObj.setFuncInValue(nProcIndex, 6,  (!vo.getStrTreatmentCategoryCode().equals("") ?vo.getStrTreatmentCategoryCode().replace("^", "#").split("#")[0] : "0"));
			daoObj.setFuncInValue(nProcIndex, 7,  "0");
			daoObj.setFuncOutValue(nProcIndex, 1);
			daoObj.executeFunction(nProcIndex);
			val = daoObj.getFuncString(nProcIndex);
			vo.setStrAdvanceAmount(val);

		} catch (Exception e) {

			e.printStackTrace();
			vo.setStrMsgString("NewBornBabyTransDAO.setChargeVal() --> "+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to bring detail in gender combo from gender master
	 * 
	 * @param vo
	 */
	public static void setGenderDtl(NewBornBabyTransVO vo) 
	{
		String strProcName = "{call pkg_ipd_view.proc_gender_dtl(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try 
		{
			daoObj = new HisDAO("ADT","NewBornBabyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr.equals("")) 
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setGenderWS(ws);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("NewBornBabyTransDAO.setGenderDtl() --> "+ e.getMessage());
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

	/**
	 * This function is used to set Gender Code on the basis of puk no.
	 * 
	 * @param vo
	 */
	public static void setGenderCode(NewBornBabyTransVO vo) {
		String strProcName = "{call pkg_ipd_view.PROC_GENDER_CODE(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "cr_no", strCrNum,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrGenderCode(ws.getString(1));
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setGenderCode() --> "+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	
	public static void onlineBabyBornEntery(NewBornBabyTransVO _NewBornBabyTransVO) {
		String strProcName = "{call pkg_ipd_view.Proc_Hipt_Patadmission_Dtl(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = _NewBornBabyTransVO.getStrCrNo();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "7");
			daoObj.setProcInValue(nProcIndex, "puk", strCrNum);
			daoObj.setProcInValue(nProcIndex, "hosp_code", _NewBornBabyTransVO.getStrHospCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.setProcInValue(nProcIndex, "seatId", "");
			daoObj.setProcInValue(nProcIndex, "modifyTime", "0");
			daoObj.setProcInValue(nProcIndex, "patListType", "0");//--ONLINE ADVICE PATIENT LISTING
			daoObj.setProcInValue(nProcIndex, "searchStr", "");
			daoObj.setProcInValue(nProcIndex, "searchType", "1");
			daoObj.setProcInValue(nProcIndex, "toRows", "0");
			daoObj.setProcInValue(nProcIndex, "frmRows", "0");
			daoObj.setProcInValue(nProcIndex, "onlinedis", "2");//--ONLINE DISCHARGE
			
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				_NewBornBabyTransVO.setOnlineBabyBornWS(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			_NewBornBabyTransVO.setStrMsgString("NewBornBabyTransDAO.onlineBabyBornEntery() --> "
					+ e.getMessage());
			_NewBornBabyTransVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void setPatientCaste(NewBornBabyTransVO _NewBornBabyTransVO) {
		String strProcName = "{call pkg_ipd_view.proc_patientCaste_dtl(?,?,?,?)}";	//2+2=4 variable
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Patient Admission",
			"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode","1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),2);
			daoObj.setProcOutValue(nProcIndex, "p_err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "p_resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr=daoObj.getString(nProcIndex, "p_err");
			if(strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "p_resultset");
				_NewBornBabyTransVO.setWrsPatientCaste(ws);
			}
			else
			{
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			_NewBornBabyTransVO.setStrMsgString("PatientAdmissionTransDAO.setPatientCaste() --> "
					+ e.getMessage());
			_NewBornBabyTransVO.setStrMsgType("1");
		}
		finally
		{
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
		
	}
	
	
	/*
	 * 
	 */
	public static void getAdmissionType(NewBornBabyTransVO _NewBornBabyTransVO) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_Admission_Type (?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {
			daoObj = new HisDAO("Patient Admission Transaction",
					"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode","1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),2);
			daoObj.setProcOutValue(nProcIndex, "p_err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "p_resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr=daoObj.getString(nProcIndex, "p_err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "p_resultset");

			if (strErr.equals("")) 
				_NewBornBabyTransVO.setWrsAdmissionTypeValues(ws);
			else 
				throw new Exception(strErr);
		} catch (Exception e) {
			e.printStackTrace();
			_NewBornBabyTransVO.setStrMsgString("AdmissionAdviceTransDAO.getAdmissionType() --> "
					+ e.getMessage());
			_NewBornBabyTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	/*
	 * 
	 */
	public static void getReliefFund(NewBornBabyTransVO _NewBornBabyTransVO) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.Proc_ReliefFund(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {
			daoObj = new HisDAO("Patient Admission Transaction",
					"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "p_mode","1",1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),2);
			daoObj.setProcOutValue(nProcIndex, "p_err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "p_resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr=daoObj.getString(nProcIndex, "p_err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "p_resultset");

			if (strErr.equals("")) 
				_NewBornBabyTransVO.setWrsReliefFundValues(ws);
			else 
				throw new Exception(strErr);
		} catch (Exception e) {
			e.printStackTrace();
			_NewBornBabyTransVO.setStrMsgString("AdmissionAdviceTransDAO.getReliefFund() --> "
					+ e.getMessage());
			_NewBornBabyTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
      public static void setDetailOfBabyBorn(NewBornBabyTransVO vo) {
		
		String strProcName = "{call pkg_ipd_view.Proc_Baby_Details(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),1);
			daoObj.setProcInValue(nProcIndex, "mother_adm_no", vo.getStrMotherAdmissionNo(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) 
				{
					
					vo.setAdmittedBabyDetails(ws);
					
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setDetailOfBabyBorn() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	// this method is used in 'Update Mother Details' for 'new born baby'..
	public static void setNumberOfBabiesWhoseDetailEntered(NewBornBabyTransVO vo) {
		
		String strProcName = "{call pkg_ipd_view.Proc_Baby_DetailsEntered_Count(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),1);
			daoObj.setProcInValue(nProcIndex, "mother_adm_no", vo.getStrMotherAdmissionNo(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) 
				{
					
					vo.setBabyCountWhoseDetailIsEntered(ws.getString(1));
					
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setNumberOfBabiesWhoseDetailEntered() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	
	public static void updatePatAdmission(NewBornBabyTransVO vo) {

		
		String strProcName = "{call pkg_ipd_dml.proc_update_babyNum(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "puk", strCrNum,3);
			daoObj.setProcInValue(nProcIndex, "hospital_code", vo.getStrHospCode(),2);
			daoObj.setProcInValue(nProcIndex, "adm_no", vo.getStrMotherAdmissionNo(),1);
			daoObj.setProcInValue(nProcIndex, "no_of_baby_delivered", vo.getStrNumberOfChildrenBorn(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
//			if (strErr == null)
//				strErr = "";
			if (strErr.equals("")) {
				vo.setStrMsgType("0");// successful...
				vo.setStrMsgString("Record successfully updated.");
			} else 
			{
				throw new Exception(strErr);
			}
					
			} catch (Exception e) {

			vo.setStrMsgString("NewBornBabyTransDAO.updatePatAdmission() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void unitNewBornDefault(NewBornBabyTransVO vo) {
		String strproc_name = "{call pkg_ipd_view.Proc_Unit(?,?,?,?::character varying,?::character varying,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		IpdConfigUtil icu = null;
		WebRowSet ws = null;
		try {
			icu   =  new IpdConfigUtil(vo.getStrHospCode());
			dao = new HisDAO("ipd","transactions.PatientLeaveDAO.unit()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
            String strDeptCode=icu.getStrNewBornBabyDefaultDept();
            if(strDeptCode==null)
            	strDeptCode="0";
            if(strDeptCode.equals(""))
            	strDeptCode="0";
			dao.setProcInValue(nprocIndex, "dept_code", strDeptCode,2);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospCode(),3);
			dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
			dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
			
			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcInValue(nprocIndex, "puk", "",4);
			dao.setProcInValue(nprocIndex, "seatId", "",5);
			dao.setProcInValue(nprocIndex, "wardcode", "",6);
			dao.setProcInValue(nprocIndex, "unitcode", "",7);
			// execute procedure

			dao.executeProcedureByPosition(nprocIndex);

			// get value

			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			if (strerr.equals("")) {
				vo.setUnitWS(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.unit() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	public static void setDistrict(NewBornBabyTransVO vo)
	{

		String strProcName = "{call pkg_ipd_view.Proc_district_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),2);
			daoObj.setProcInValue(nProcIndex, "state", vo.getStrStateCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr=daoObj.getString(nProcIndex, "err");
			if(strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setDistrictWS(ws);
			}
			else
			{
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("NewBornBabyTransDAO.setDistrict() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
		finally
		{
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
public static void setsharable(NewBornBabyTransVO vo) {
		
		String strProcName = "{call pkg_ipd_view.proc_getSharable(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		
		try {

			daoObj = new HisDAO("New Born Baby Admission Transaction","NewBornBabyTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),1);
			daoObj.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode(),2);
			daoObj.setProcInValue(nProcIndex, "unitcode", vo.getStrDeptUnitCode(),3);
			daoObj.setProcInValue(nProcIndex, "wardCode", vo.getStrWardCode(),4);
			daoObj.setProcInValue(nProcIndex, "roomCode", vo.getStrRoomCode(),5);
			daoObj.setProcInValue(nProcIndex, "bedCode", vo.getStrBedCode(),6);
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) 
				{
					
					vo.setSharableCnt(ws.getString(1));
					
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("NewBornBabyTransDAO.setDetailOfBabyBorn() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
public static void setConsultantNameDtl(NewBornBabyTransVO voObj) {

	HisDAO daoObj = null;
	WebRowSet ws = null;

	String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
	int nProcIndex = 0;

	String deptUnitCode = voObj.getStrUnitNewBorn();

	String strErr = "";
	//System.out.println("deptUnitCode"+voObj.getStrDeptUnitCode());
	//System.out.println(voObj.getStrHospCode());
	//System.out.println(voObj.getStrDeptUnitCode()==null?voObj.getStrDeptCode()+"%":voObj.getStrDeptUnitCode());;
	try 
	{
		daoObj = new HisDAO("Admission Advice Trans","NewBornBabyTransDAO");

		nProcIndex = daoObj.setProcedure(strProcName);

		if (deptUnitCode != null) 
		{
			//daoObj.setProcInValue(nProcIndex, "modVal", "2");
			daoObj.setProcInValue(nProcIndex, "modVal", "8",1);  //Modeval 1 changed to 7 for designation
			//daoObj.setProcInValue(nProcIndex, "modVal", "9",1);//changed from modVal 7 to 9 to get Unit also
			
			daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrUnitNewBorn(),2);
			
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),3);
			daoObj.setProcInValue(nProcIndex, "seatId", "0",4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			//daoObj.setProcInValue(nProcIndex, "deptunitcode", "");

			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				voObj.setConsWS(ws);
			} else {
				throw new Exception(strErr);
			}
		}
	} catch (Exception e) {

		voObj.setStrMsgString("NewBornBabyTransDAO.setConsultantNameDtl() --> "
						+ e.getMessage());
		voObj.setStrMsgType("1");
	} finally {
		if (daoObj != null) {
			daoObj.free();
			daoObj = null;
		}
	}

}
}