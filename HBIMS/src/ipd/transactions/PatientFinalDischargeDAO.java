package ipd.transactions;

import ipd.IpdConfigUtil;

import java.util.ResourceBundle;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.GetNetworkAddress;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class PatientFinalDischargeDAO {
	public static WebRowSet getTransferRsn() {
		WebRowSet ws = null;
		HisDAO dao = null;
		int nQryIndex;
		try {
			dao = new HisDAO("ipd",
					"transactions.PatientFinalDischargeDAO.getTransferRsn()");
			nQryIndex = dao
					.setQuery("select HBLSTR_REMARKS from HBLT_REMARKS_MST where GNUM_ISVALID=" + 1);
			ws = dao.executeQry(nQryIndex);
		} catch (Exception e) {
		} finally {
			dao.free();
			dao = null;
		}
		return ws;
	}

	public static void getOPDVisitRoom(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.Proc_opd_room_dtl(?,?,?,?,?,?)}";
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("ipd","transactions.PatientFinalDischargeDAO .getOPDVisitRoom()");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval","1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",_patientFinalDischargeVO.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptunit",_patientFinalDischargeVO.getStrDeptUnitCode(),3);
			daoObj.setProcInValue(nProcIndex, "seatId","",4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			// System.out.println("wsSize->"+ws.size());
			if (strErr == "")
				_patientFinalDischargeVO.setStrRoomOPDWS(ws);

		} catch (Exception e) {
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getOPDVisitRoom() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		} finally {
			daoObj.free();
			daoObj = null;
		}
	}

	public static WebRowSet getAdvisedBy(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		WebRowSet ws = null;
		String strProcName = "";
		String deptUnitCode="0";
		if(_patientFinalDischargeVO.getStrCurrentDeptUnitRoom().replace("^","#").split("#").length>1)
			deptUnitCode=_patientFinalDischargeVO.getStrCurrentDeptUnitRoom().replace("^","#").split("#")[1];
		// _patientFinalDischargeVO.setStrApprovalComboMode("2");//delete
		if (_patientFinalDischargeVO.getStrApprovalComboMode().equals("3"))
			strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		else
			strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
		
		// System.out.println("mode->"+
		// _patientFinalDischargeVO.getStrApprovalComboMode());
		// System.out.println("deptunit->"+_patientFinalDischargeVO.getStrDeptUnitCode());
		try {
			daoObj = new HisDAO("ipd","transactions.PatientFinalDischargeDAO .getAdvisedBy()");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modVal",	_patientFinalDischargeVO.getStrApprovalComboMode(),1);
			if (_patientFinalDischargeVO.getStrApprovalComboMode().equals("3"))
				daoObj.setProcInValue(nProcIndex, "deptunitcode",deptUnitCode,2);
			else
				daoObj.setProcInValue(nProcIndex, "deptunitcode","",2);
			daoObj.setProcInValue(nProcIndex, "hosp_code",_patientFinalDischargeVO.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "seatId",	"",4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

		} catch (Exception e) {
			e.printStackTrace();
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getAdvisedBy() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		} finally {
			daoObj.free();
			daoObj = null;
		}
		return ws;
	}

	public static void getRsnRmk(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		HisUtil hisutil = null;
		WebRowSet w1 = null;
		try {
			hisutil = new HisUtil("transaction", "PatientFinalDischargeDAO");
			if(_patientFinalDischargeVO.getStrDischrg_Mode().equals("1"))//online
			_patientFinalDischargeVO.setStrApprovalComboMode("2");
			else
				_patientFinalDischargeVO.setStrApprovalComboMode("3");
			
			w1 = getAdvisedBy(_patientFinalDischargeVO);
			_patientFinalDischargeVO.setStrDisBy(w1);
			String str1 = hisutil.getOptionValue(_patientFinalDischargeVO.getStrDisBy(), "0", "0^Select value", true);
			_patientFinalDischargeVO.setStrRmk(str1);

		} catch (Exception e) {
			e.printStackTrace();
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getRsnRmk() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		} finally {
			w1 = null;
			hisutil = null;
		}
	}

	public static void getPatientDischargeParameter(PatientFinalDischargeVO _patientFinalDischargeVO) 
	{
		String strProcName = "{call pkg_ipd_view.PROC_HEPRT_DEPT_DOCCOMP_MST(?::numeric,?::numeric,?::numeric,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		
		try 
		{
			String strDocCode = "";
			ResourceBundle mstRes = ResourceBundle.getBundle("ipd.hisIpdProperties");
			strDocCode = mstRes.getString("Discharge_Document_ID");
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code",_patientFinalDischargeVO.getStrHospitalCode(),1);
			daoObj.setProcInValue(nProcIndex, "dept_code",_patientFinalDischargeVO.getStrDeptCode(),2);
			daoObj.setProcInValue(nProcIndex, "document_code", strDocCode,3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			String[] strComponentName = new String[ws.size()];
			String[] strComponentID = new String[ws.size()];
			if (strErr.equals("")) 
			{
				for (int tmpI = 0; ws.next(); tmpI++) 
				{
					strComponentID[tmpI] = ws.getString(1);
					strComponentName[tmpI] = ws.getString(2);
				}
			} 
			else 
			{
				throw new Exception(strErr);
			}
			_patientFinalDischargeVO.setStrComponentName(strComponentName);
			_patientFinalDischargeVO.setStrComponentID(strComponentID);
		} 
		catch (Exception e) 
		{
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeDAO.getPatientDischargeParameter() --> "+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
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

	public static void getHospitalList(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		String strProcName = "{call pkg_ipd_view.PROC_HOSPITAL_MST(?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try {
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcOutValue(nProcIndex, "err", 1,1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,2);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				_patientFinalDischargeVO.setHospitalListWs(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getHospitalList() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void initFinalDiagnosis(
			PatientFinalDischargeVO _patientFinalDischargeVO) {

		String strProcName = "{call pkg_simple_view.proc_diagnosis_hosiptal_list(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
			daoObj = new HisDAO("Patient Final Discharge",
					"PatientFinalDischargeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "searchmode", _patientFinalDischargeVO.getStrSearchMode(),1);
			daoObj.setProcInValue(nProcIndex, "searchstr", _patientFinalDischargeVO.getStrSearchString(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code",_patientFinalDischargeVO.getStrHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				_patientFinalDischargeVO.setWsFinalDiagnosis(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.initFinalDiagnosis() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void getIcd10DiagnosisDtl(
			PatientFinalDischargeVO _patientFinalDischargeVO) {

		String strProcName = "{call pkg_simple_view.proc_diagnosis_icd10_list(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
			daoObj = new HisDAO("Patient Final Discharge",
					"PatientFinalDischargeDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "searchmode", _patientFinalDischargeVO.getStrSearchMode(),1);
			daoObj.setProcInValue(nProcIndex, "searchstr", _patientFinalDischargeVO.getStrSearchString(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				_patientFinalDischargeVO.setWsIcd10Diagnosis(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getIcd10DiagnosisDtl() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void getPatientDischargeTypeCombo(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		String strProcName = "{call pkg_ipd_view.PROC_HIPT_DISCHARGE_TYPE_MST(?::numeric,?::numeric,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		HisUtil util = null;
		try {
			util = new HisUtil("Patient Discharge", "PatientFinalDischargeDAO");
			String strDeathCode = "";
			ResourceBundle mstRes = ResourceBundle
					.getBundle("ipd.hisIpdProperties");
			strDeathCode = mstRes.getString("Death_Code");
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code",
					IpdConfigUtil.SUPER_HOSPITAL_CODE,1);
			if (_patientFinalDischargeVO.getStrIsDead().equals("true"))//For Online IPD
				daoObj.setProcInValue(nProcIndex, "dis_type_code",strDeathCode,2);
			else
				//daoObj.setProcInValue(nProcIndex, "dis_type_code", "0"); //Offline,commented to show death discharge
				daoObj.setProcInValue(nProcIndex, "dis_type_code", strDeathCode,2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				IpdConfigUtil ipdC = new IpdConfigUtil(_patientFinalDischargeVO.getStrHospitalCode());				
				
				if (_patientFinalDischargeVO.getStrIsDead().equals("true"))
					_patientFinalDischargeVO.setStrPatientDischargeTypeComboValues(util.getOptionValue(ws, ipdC.getStrDischargeTypeDeath(), "0^Select Value", false));
				else
				_patientFinalDischargeVO.setStrPatientDischargeTypeComboValues(util.getOptionValue(ws, "", "0^Select Value", false));
			} 
			else 
			{
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getPatientDischargeTypeCombo() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
			ws = null;
			util = null;
		}
	}
	

	public static void getPatientDeathDetails(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		String strProcName = "{call pkg_ipd_view.PROC_IS_DEATH_FROM_CRNO(?::character varying,?::numeric,?::numeric,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try {
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval","2",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",
					_patientFinalDischargeVO.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "cr_no", _patientFinalDischargeVO
					.getStrCrNo(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					_patientFinalDischargeVO.setStrIsDead("true");
					_patientFinalDischargeVO.setStrDeathDateAndTime(ws
							.getString(1));
					_patientFinalDischargeVO
							.setStrDeathCauseIM(ws.getString(2));
					_patientFinalDischargeVO
							.setStrDeathCauseAN(ws.getString(3));
					_patientFinalDischargeVO.setStrDeathMannerID(ws
							.getString(4));
					_patientFinalDischargeVO.setStrDeathManner(ws.getString(5));
				}
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getPatientDeathDetails() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void getStrDeathMannerComboValues(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		String strProcName = "{call pkg_ipd_view.PROC_HIPT_DEATH_MANNER_MST(?::numeric,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		HisUtil util = null;
		try {
			util = new HisUtil("Patient Discharge", "PatientFinalDischargeDAO");
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code",
					_patientFinalDischargeVO.getStrHospitalCode(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				_patientFinalDischargeVO.setStrDeathMannerComboValues(util
						.getOptionValue(ws, "", "0^Select Value", false));
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getStrDeathMannerComboValues() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
			ws = null;
			util = null;
		}
	}

	public static void getStrDeathCauseComboValues(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		String strProcName = "{call pkg_ipd_view.proc_hospital_disease_mst(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		HisUtil util = null;
		try {
			util = new HisUtil("Patient Discharge", "PatientFinalDischargeDAO");
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code",_patientFinalDischargeVO.getStrHospitalCode(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				_patientFinalDischargeVO.setStrDeathCauseComboValues(util
						.getOptionValue(ws, "", "0^Select Value", false));
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getStrDeathCauseComboValues() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
			ws = null;
			util = null;
		}
	}

	public static void getGender(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		String strProcName = "{call pkg_ipd_view.PROC_GENDER_CODE(?::numeric,?::numeric,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try {
			String strGenderCode = "";
			ResourceBundle mstRes = ResourceBundle
					.getBundle("ipd.hisIpdProperties");
			strGenderCode = mstRes.getString("Gender_Code_Female");
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code",
					_patientFinalDischargeVO.getStrHospitalCode(),1);
			daoObj.setProcInValue(nProcIndex, "cr_no", _patientFinalDischargeVO
					.getStrCrNo(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {
				if (ws.next()) {
					_patientFinalDischargeVO.setStrIsFemale(ws.getString(1)
							.equals(strGenderCode) ? "true" : "false");
				}
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getGender() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
			ws = null;
		}
	}

	public static void insert(PatientFinalDischargeVO _patientFinalDischargeVO) {
		String strproc_name = "{call PKG_IPD_DML.Proc_discharge_parameter_dtl(?,?,?,?,?,?,?,?,?)}";
		String strProcName2 ="{call pkg_ipd_dml.proc_ip_mac(?,?,?,?,?,?,?,?)}";
		int nprocIndex = 0;
		int nProcIndex2 = 0;
		StringBuffer strproc_name1 = new StringBuffer();
		boolean fTmpFlag = true;
		HisDAO dao = null;
		// String errStr="";
		String strErr1 = "";
		String strErr2 = "";
		String strErr3 = "";
		String strErr4 = "";
		
		
		
		try {
			dao = new HisDAO("ipd",
					"transactions.PatientFinalDischargeDAO.insert()");
			if (_patientFinalDischargeVO.getStrComponentID() != null
					&& _patientFinalDischargeVO.getStrDischrg_Param_Req().equals("1")) 
			{
				for (int nTmpI = 0; nTmpI < _patientFinalDischargeVO.getStrComponentID().length; nTmpI++) 
				{
					if (_patientFinalDischargeVO.getStrPatientDisParamDetails()[nTmpI] != null
							&& !(_patientFinalDischargeVO.getStrPatientDisParamDetails()[nTmpI].equals(""))) 
					{
						if (fTmpFlag)
							nprocIndex = dao.setProcedure(strproc_name);
						dao.setProcInValue(nprocIndex, "modval", "1",1);
						dao.setProcInValue(nprocIndex, "gnum_hospital_code",_patientFinalDischargeVO.getStrHospitalCode(),2);
						dao.setProcInValue(nprocIndex, "hipnum_admno",_patientFinalDischargeVO.getCurAdmNo(),3);
						dao.setProcInValue(nprocIndex, "hrgnum_puk",_patientFinalDischargeVO.getStrCrNo(),4);
						dao.setProcInValue(nprocIndex, "hipnum_slno","",5);
						dao.setProcInValue(nprocIndex, "hepnum_component_code",_patientFinalDischargeVO.getStrComponentID()[nTmpI],6);
						dao.setProcInValue(nprocIndex, "hepstr_component_value",_patientFinalDischargeVO.getStrPatientDisParamDetails()[nTmpI],7);
						dao.setProcInValue(nprocIndex, "gnum_seatid",_patientFinalDischargeVO.getStrSeatID(),8);
						dao.setProcOutValue(nprocIndex, "err", 1,9);
						dao.execute(nprocIndex, 1);
						fTmpFlag = false;
					}
				}
			}
			strproc_name = "{call PKG_IPD_DML.Proc_discharge_diagnosis_dtl(?,?,?,?,?,?,?,?,?)}";
			for (int nTmpI = 0; _patientFinalDischargeVO.getStrProvisionDiagnosis() != null
					&& nTmpI < _patientFinalDischargeVO.getStrProvisionDiagnosis().length; nTmpI++) 
			{
				nprocIndex = dao.setProcedure(strproc_name);
				dao.setProcInValue(nprocIndex, "modval", "1",1);
				dao.setProcInValue(nprocIndex, "gnum_hospital_code",_patientFinalDischargeVO.getStrHospitalCode(),2);
				dao.setProcInValue(nprocIndex, "hipnum_admno",_patientFinalDischargeVO.getCurAdmNo(),3);
				dao.setProcInValue(nprocIndex, "hrgnum_puk",_patientFinalDischargeVO.getStrCrNo(),4);
				dao.setProcInValue(nprocIndex, "hipnum_slno", "",5);
				dao.setProcInValue(nprocIndex, "hipstr_diagnosis_code",_patientFinalDischargeVO.getStrProvisionDiagnosis()[nTmpI],6);
				dao.setProcInValue(nprocIndex, "hipnum_diagnosis_type",_patientFinalDischargeVO.getStrDiagnosisType(),7);
				dao.setProcInValue(nprocIndex, "gnum_seatid",_patientFinalDischargeVO.getStrSeatID(),8);
				dao.setProcOutValue(nprocIndex, "err", 1,9);
				dao.execute(nprocIndex, 1);
			}

			strproc_name1.append("{call PKG_IPD_DML.Proc_Final_Discharge(?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying,?::character varying)}");
			/*if (_patientFinalDischargeVO.getStrIsMLC().trim().equals("1")
					&& _patientFinalDischargeVO.getStrDisplay_MLC_Dtls().trim().equals("1")
					&& _patientFinalDischargeVO.getStrDischrg_Mode().trim().equals("2")) 
			{
				strproc_name1.append(",?,?,?");
			}
			else
			{
				strproc_name1.append(",?,?,?");
			}
			if (_patientFinalDischargeVO.getStrDisplay_OPD_Visit_Dtls().trim().equals("1")
					&& _patientFinalDischargeVO.getStrDischrg_Mode().trim().equals("2")) {
				strproc_name1.append(",?,?");
			}
			else
			{
				strproc_name1.append(",?,?");
			}
			strproc_name1.append(")}");*/
			
			strproc_name = strproc_name1.toString();

			nprocIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nprocIndex, "admNo", _patientFinalDischargeVO.getCurAdmNo(),1);
			dao.setProcInValue(nprocIndex, "hosp_code",_patientFinalDischargeVO.getStrHospitalCode(),2);
			dao.setProcInValue(nprocIndex, "puk", _patientFinalDischargeVO.getStrCrNo(),3);
			dao.setProcInValue(nprocIndex, "seatId", _patientFinalDischargeVO.getStrSeatID(),4);
			dao.setProcInValue(nprocIndex, "disType", _patientFinalDischargeVO.getStrTransferUnit(),5);
			dao.setProcInValue(nprocIndex, "disBy", (_patientFinalDischargeVO.getStrDischrg_Mode().trim().equals("2")?_patientFinalDischargeVO.getStrRmk():_patientFinalDischargeVO.getStrAdvisedBy()),6);
			dao.setProcInValue(nprocIndex, "disAdvice",	(_patientFinalDischargeVO.getStrDischrg_Mode().trim().equals("2")?_patientFinalDischargeVO.getStrRsn():_patientFinalDischargeVO.getStrRemarksOnline()),7);
			if (_patientFinalDischargeVO.getStrIsMLC().trim().equals("1")
					&& _patientFinalDischargeVO.getStrDisplay_MLC_Dtls().trim().equals("1")
					&& _patientFinalDischargeVO.getStrDischrg_Mode().trim().equals("2")) {
				dao.setProcInValue(nprocIndex, "police_informed_date",_patientFinalDischargeVO.getStrPoliceInfrmDtMLC(),8);
				dao.setProcInValue(nprocIndex, "mlc_approved_date",	_patientFinalDischargeVO.getStrApprovDtMLC(),9);
				dao.setProcInValue(nprocIndex, "mlc_approved_by",_patientFinalDischargeVO.getStrApprovByMLC(),10);
			}
			else
			{
				dao.setProcInValue(nprocIndex, "police_informed_date","",8);
				dao.setProcInValue(nprocIndex, "mlc_approved_date","",9);
				dao.setProcInValue(nprocIndex, "mlc_approved_by","",10);
			}
			if (_patientFinalDischargeVO.getStrDisplay_OPD_Visit_Dtls().trim().equals("1")
					&& _patientFinalDischargeVO.getStrDischrg_Mode().trim().equals("2")) {
				
				dao.setProcInValue(nprocIndex, "next_visitdate",_patientFinalDischargeVO.getStrNxtVisitOPD(),11);
				dao.setProcInValue(nprocIndex, "room_no",_patientFinalDischargeVO.getStrRoomOPD(),12);
			}
			else
			{
				dao.setProcInValue(nprocIndex, "next_visitdate","",11);
				dao.setProcInValue(nprocIndex, "room_no","",12);
			}
			
			dao.setProcInValue(nprocIndex, "billFlag", _patientFinalDischargeVO.getStrBillIntegrated(),13);
			dao.setProcInValue(nprocIndex, "onlineofflineflag",_patientFinalDischargeVO.getStrDischrg_Mode(),14);
			dao.setProcInValue(nprocIndex, "treatmentResult",_patientFinalDischargeVO.getStrTreatmentResult(),15);
			dao.setProcInValue(nprocIndex, "consentRequiredFlag",_patientFinalDischargeVO.getStrConsentRequired(),16);
			dao.setProcInValue(nprocIndex, "consentSignedBy",_patientFinalDischargeVO.getStrConsentSignedByRelativeName(),17);
			dao.setProcInValue(nprocIndex, "abscondedDateTime",_patientFinalDischargeVO.getStrAbscondedDate(),18);
			dao.setProcInValue(nprocIndex, "patHeight",_patientFinalDischargeVO.getStrPatHeight(),19);
			dao.setProcInValue(nprocIndex, "patColor",_patientFinalDischargeVO.getStrPatColor(),20);
			dao.setProcInValue(nprocIndex, "identificationMark",_patientFinalDischargeVO.getStrPatIdentifyMark(),21);
			dao.setProcInValue(nprocIndex, "lastSeenDate",_patientFinalDischargeVO.getStrLastSeenDate(),22);
			dao.setProcInValue(nprocIndex, "isWearingHospClothes",_patientFinalDischargeVO.getStrIsPatWearHospClothes(),23);
			dao.setProcInValue(nprocIndex, "hospClothesDetails",_patientFinalDischargeVO.getStrClothesDetails(),24);
			dao.setProcOutValue(nprocIndex, "err", 1,25);
			dao.setProcInValue(nprocIndex, "disdate",_patientFinalDischargeVO.getStrDisDate()+" "+_patientFinalDischargeVO.getStrAbsHour()+":"+_patientFinalDischargeVO.getStrAbsMin()+":"+_patientFinalDischargeVO.getStrAbsSec()+" "+_patientFinalDischargeVO.getStrAbsAmPm(),26);
			//System.out.println("_patientFinalDischargeVO.getStrDisDate"+_patientFinalDischargeVO.getStrDisDate()+" "+_patientFinalDischargeVO.getStrAbsHour()+":"+_patientFinalDischargeVO.getStrAbsMin()+":"+_patientFinalDischargeVO.getStrAbsSec()+" "+_patientFinalDischargeVO.getStrAbsAmPm());
			dao.execute(nprocIndex, 1);
			
			//Death Patient Offline
			if(_patientFinalDischargeVO.getStrDeathFlag().equals("1"))
			{
				String strProcName = "{call pkg_ipd_dml.proc_pat_deathnotif_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				//int nProcIndex = 0;
				//String strErr = "";
				String strDeptCode="0";
				String strUnitCode="0";
				String strRoomCode="0";
				if( _patientFinalDischargeVO.getCurDept_Unt_RomCode().replace("^","#").split("#").length>0)
				{
					strDeptCode=_patientFinalDischargeVO.getCurDept_Unt_RomCode().replace("^","#").split("#")[0];
					strUnitCode=_patientFinalDischargeVO.getCurDept_Unt_RomCode().replace("^","#").split("#")[1];
					strRoomCode=_patientFinalDischargeVO.getCurDept_Unt_RomCode().replace("^","#").split("#")[2];
				}
				nprocIndex = dao.setProcedure(strProcName);
				
				dao.setProcInValue(nprocIndex, "modval","1",1);
				dao.setProcInValue(nprocIndex, "hrgnum_puk", _patientFinalDischargeVO.getStrCrNo(),2);
				dao.setProcInValue(nprocIndex, "hipnum_admno", _patientFinalDischargeVO.getCurAdmNo(),3);
				dao.setProcInValue(nprocIndex, "hrgnum_episode_code", _patientFinalDischargeVO.getStrEpisodeCode(),4);
				dao.setProcInValue(nprocIndex, "hrgnum_visit_no", _patientFinalDischargeVO.getStrVisitNo(),5);
				dao.setProcInValue(nprocIndex, "hrgdt_visit_date","",6);
				dao.setProcInValue(nprocIndex, "hipdt_admdatetime", _patientFinalDischargeVO.getStrAdmDate(),7);
				dao.setProcInValue(nprocIndex, "gnum_dept_code", strDeptCode,8);
				dao.setProcInValue(nprocIndex, "gnum_deptunit_code",  strUnitCode,9);
				dao.setProcInValue(nprocIndex, "hipnum_ward_code", _patientFinalDischargeVO.getStrWardCode(),10);
				dao.setProcInValue(nprocIndex, "hipnum_room_code",  strRoomCode,11);
				dao.setProcInValue(nprocIndex, "hipnum_bed_code", _patientFinalDischargeVO.getCurWrdBedCode().replace("^","#").split("#")[1],12);
				dao.setProcInValue(nprocIndex, "hipnum_isnewborn", _patientFinalDischargeVO.getStrIsNewBorn(),13);
				dao.setProcInValue(nprocIndex, "hrgnum_mlc_no", _patientFinalDischargeVO.getStrMlcNo(),14);
				dao.setProcInValue(nprocIndex, "hepdt_death_datetime", _patientFinalDischargeVO.getStrDeathDateAndTime(),15);
				dao.setProcInValue(nprocIndex, "hepdt_death_onset_datetime", _patientFinalDischargeVO.getStrOnsetDeathDateAndTime(),16);
				dao.setProcInValue(nprocIndex, "hipnum_death_cause_code_im", _patientFinalDischargeVO.getStrDeathCauseID(),17);
				dao.setProcInValue(nprocIndex, "hipnum_death_manner_code", _patientFinalDischargeVO.getStrDeathMannerID(),18);
				dao.setProcInValue(nprocIndex, "hepstr_death_cause_im  ", _patientFinalDischargeVO.getStrDescpCauseDeath(),19);
				dao.setProcInValue(nprocIndex, "hepstr_death_cause_an ", _patientFinalDischargeVO.getStrAntecedentCauseDeath(),20);
				dao.setProcInValue(nprocIndex, "hepstr_injury_dtl", _patientFinalDischargeVO.getStrInjuryDetail(),21);
				dao.setProcInValue(nprocIndex, "hepnum_ispregnent", _patientFinalDischargeVO.getStrIsPregnant(),22);
				dao.setProcInValue(nprocIndex, "hepnum_isdelivery", "0",23);
				dao.setProcInValue(nprocIndex, "hepnum_consultant_id", _patientFinalDischargeVO.getStrRmk(),24);
				dao.setProcInValue(nprocIndex, "hepdt_verify_datetime	", _patientFinalDischargeVO.getStrVerifyDateAndTime(),25);
				dao.setProcInValue(nprocIndex, "gdt_entry_date","",26);
				dao.setProcInValue(nprocIndex, "gnum_seatid	", _patientFinalDischargeVO.getStrSeatID(),27);
				dao.setProcInValue(nprocIndex, "gnum_isvalid","",28);
				dao.setProcInValue(nprocIndex, "gnum_hospital_code", _patientFinalDischargeVO.getStrHospitalCode(),29);
				dao.setProcInValue(nprocIndex, "gnum_nurse_seatid","",30);
				dao.setProcInValue(nprocIndex, "hepdt_bodyshift_datetime	", _patientFinalDischargeVO.getStrShiftDateAndTime(),31);
				dao.setProcInValue(nprocIndex, "hgnum_bed_status_code", _patientFinalDischargeVO.getStrBedStatusVacantCode(),32);
				//dao.setProcInValue(nprocIndex, "hgnum_pat_status_code", _patientFinalDischargeVO.getStrDeathCode());
				dao.setProcInValue(nprocIndex, "hgnum_pat_status_code", "13",33);
				dao.setProcInValue(nprocIndex, "hepnum_is_shift_mortury",_patientFinalDischargeVO.getStrIsSiftToMortuary(),34);
				dao.setProcInValue(nprocIndex, "hepstr_body_handover_to", _patientFinalDischargeVO.getStrHandoverTo(),35);
				dao.setProcInValue(nprocIndex, "hepdt_body_handover_datetime",_patientFinalDischargeVO.getStrHandoverDateAndTime(),36);
				dao.setProcOutValue(nprocIndex, "err", 1,37);
				dao.setProcOutValue(nprocIndex, " dml_count", 1,38);
				dao.setProcInValue(nprocIndex, "death_details_required",_patientFinalDischargeVO.getStrDeathDetailsRequired(),39);
				
				//dao.executeProcedure(nprocIndex);
				dao.execute(nprocIndex,1);
				//strErr = dao.getString(nprocIndex, "err");
				
			}
				
			nProcIndex2 = dao.setProcedure(strProcName2);
			dao.setProcInValue(nProcIndex2, "modeval", "1",1);									
			dao.setProcInValue(nProcIndex2, "adm_no", _patientFinalDischargeVO.getCurAdmNo(),2);	//							
			dao.setProcInValue(nProcIndex2, "hospital_code",  _patientFinalDischargeVO.getStrHospitalCode(),3);					
			dao.setProcInValue(nProcIndex2, "puk", _patientFinalDischargeVO.getStrCrNo(),4);		
			dao.setProcInValue(nProcIndex2, "mov_no", "",5);	
			dao.setProcInValue(nProcIndex2, "ip", GetNetworkAddress.GetAddress("ip"),6);			
			dao.setProcInValue(nProcIndex2, "mac", GetNetworkAddress.GetAddress("mac"),7);		//				
			dao.setProcOutValue(nProcIndex2, "err", 1,8);		//			
			dao.executeProcedureByPosition(nProcIndex2);
			
			
			strErr4 = dao.getString(nProcIndex2, "err");
			if (strErr4 == null)
				strErr4 = "";
			if (strErr4.equals("")) {
			} else {
				throw new Exception(strErr4);
			}
			
			synchronized (dao) 
			{
				dao.fire();
			}
			
			if (strErr1 == null && strErr2 == null && strErr3 == null) 
			{
				strErr1 = "";
				strErr2 = "";
				strErr3 = "";
			}
			/*
			 * if (strErr1.equals("") && strErr2.equals("") &&
			 * strErr3.equals("")) { System.out.println("err blank");
			 * _patientFinalDischarge_patientFinalDischargeVO.setStrMsgType("0"); } else {
			 * errStr=strErr1+"###"+strErr2+"###"+strErr3; throw new
			 * Exception(errStr); }
			 */
		} catch (Exception e) {
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.insert() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}
	
	
	

	public static void checkAdmin(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		String strProcName = "{call PKG_IPD_VIEW.Proc_Pat_Admstatus_Code_Dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		IpdConfigUtil ipdC=new IpdConfigUtil(_patientFinalDischargeVO.getStrHospitalCode());
		try {
			// ResourceBundle mstRes =
			// ResourceBundle.getBundle("ipd.hisIpdProperties");
			// String strAdmittedCode = mstRes.getString("Admitted");
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval","1",1);
			daoObj.setProcInValue(nProcIndex, "hipnum_admno","",2);
			daoObj.setProcInValue(nProcIndex, "hrgnum_puk",_patientFinalDischargeVO.getStrCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "gnum_hospital_code",_patientFinalDischargeVO.getStrHospitalCode(),4);
			//ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
			//daoObj.setProcInValue(nProcIndex, "issuereq", resourceBundle.getString("BELONGING_ISSUE_ITEM_REQUIRED"));
			if(ipdC.getStrBelongingRequired().equals("1") ||  ipdC.getStrIssueItemRequired().equals("1"))
			daoObj.setProcInValue(nProcIndex, "issuereq", "1",5);
			else
				daoObj.setProcInValue(nProcIndex, "issuereq", "0",5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (ws.size() == 0) {//not admitted
				_patientFinalDischargeVO.setStrMsgType("4");
			} else {
				if (strErr.equals("")) {
					_patientFinalDischargeVO.setStrCheckAdminWS(ws);

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.checkAdmin() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
			ws = null;
		}
	}

	public static void checkForBillClearance(PatientFinalDischargeVO vo) 
	{
		String strProcName = "{? = call Bill_Interface.FUNC_ONLINE_IPDBILL_CHECK(?,?,?)}";
		int nProcIndex = 0;
		String val = "";

		HisDAO daoObj = null;
		
		try 
		{
			daoObj = new HisDAO("ADT","PatientFinalDischargeDAO");
			nProcIndex = daoObj.setFunction(strProcName);
			daoObj.setFuncInValue(nProcIndex, 2, "1");
			daoObj.setFuncInValue(nProcIndex, 3, vo.getStrHospitalCode());
			daoObj.setFuncInValue(nProcIndex, 4, vo.getStrCrNo());
			daoObj.setFuncOutValue(nProcIndex, 1);
			daoObj.executeFunction(nProcIndex);
			val = daoObj.getFuncString(nProcIndex);
			if (Integer.parseInt(val) <= 0)
				vo.setStrMsgType("5");
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("PatientFinalDischargeDAO.checkForBillClearance() --> "+ e.getMessage());
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

	public static void checkTransitTimeClearance(PatientFinalDischargeVO vo) {
		String strProcName = "{? = call ipd_mst.check_Discharge_Time(?,?,?)}";
		int nProcIndex = 0;
		String strResult = "";
		HisDAO daoObj = null;
		try {
			daoObj = new HisDAO("Patient Discharge Transaction",
					"PatientFinalDischargeDAO.checkTransitTimeClearance");
			nProcIndex = daoObj.setFunction(strProcName);
			daoObj.setFuncInValue(nProcIndex, 2, vo.getStrAdmNo());
			daoObj.setFuncInValue(nProcIndex, 3, vo.getStrHospitalCode());
			daoObj.setFuncInValue(nProcIndex, 4, vo.getStrAdmStatCode());
			daoObj.setFuncOutValue(nProcIndex, 1);
			daoObj.executeFunction(nProcIndex);
			strResult = daoObj.getFuncString(nProcIndex);
			if (strResult.equals("1"))
				vo.setStrClearForDischarge("true");
			else
				vo.setStrClearForDischarge("false");
		} catch (Exception e) {
			vo
					.setStrMsgString("PatientFinalDischargeDAO.checkTransitTimeClearance() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	// To get Treatment Result Combo
	// added by Debashis on 19th Nov 2012 
	public static void getStrTreatmentResultCombo(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		String strProcName = "{call pkg_ipd_view.proc_treatment_result_mst(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		HisUtil util = null;
		try {
			util = new HisUtil("Patient Discharge", "PatientFinalDischargeDAO");
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code",IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				_patientFinalDischargeVO.setStrTreatmentResultComboValues(util
						.getOptionValue(ws, "", "0^Select Value", false));
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getStrTreatmentResultCombo() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
			ws = null;
			util = null;
		}
	}
	//to get profile id for printing discharge summary
	public static void getStrProfileId(
			PatientFinalDischargeVO _patientFinalDischargeVO) {
		String strProcName = "{call pkg_ipd_view.proc_profile_id(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		HisUtil util = null;
		try {
			util = new HisUtil("Patient Discharge", "PatientFinalDischargeDAO");
			daoObj = new HisDAO("Patient Discharge", "PatientFinalDischargeDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "crno",_patientFinalDischargeVO.getStrCrNo(),1);
			daoObj.setProcInValue(nProcIndex, "admno", _patientFinalDischargeVO.getStrAdmNo(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code",_patientFinalDischargeVO.getStrHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if (ws.next()) {
					_patientFinalDischargeVO.setStrProfileId(ws.getString(1));
				}
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			_patientFinalDischargeVO
					.setStrMsgString("PatientFinalDischargeDAO.getStrProfileId() --> "
							+ e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
			ws = null;
			util = null;
		}
	}
	public static void getIcuPvtBillStatus(PatientFinalDischargeVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_geticupvtbillstatus(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			
				daoObj = new HisDAO("Patient Discharge",
					"PatientFinalDischargeDAO");
				
				nProcIndex = daoObj.setProcedure(strProcName);
				
				daoObj.setProcInValue(nProcIndex, "modeval","2",1);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "crno",vo.getStrCrNo(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					if(ws.size()==0)
					{
						vo.setBillcount("0");
					}
					else if(ws.next())
					{
						vo.setBillcount(ws.getString(1));
					}
				
				} else {
							throw new Exception(strErr);
						}
			
		} catch (Exception e) {
				vo.setStrMsgString("PatientFinalDischargeDAO.getIcuPvtBillStatus() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
		} 
		finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
}