package ipd.transactions;

import ipd.IpdConfigUtil;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.*;
import hisglobal.utility.GetNetworkAddress;

import javax.sql.rowset.WebRowSet;

public class AdmissionAdviceTransDAO {
	public static void checkForCrNo(AdmissionAdviceTransVO vo) throws Exception {
		String strProcName = "{call pkg_ipd_view.proc_pat_status(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try {
			daoObj = new HisDAO("Admission Advice Transaction",
					"AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "puk", vo.getPatCrNo(), 2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),
					3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 5);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {
				if (ws.size() == 0) {
					vo.setStrMsgString("Invalid CR No.");
					vo.setStrMsgType("2");
				} else {
					ws.next();
					if (ws.getString(3).equals("11")) {
						vo.setStrMsgString("Patient is Admitted.");
						vo.setStrMsgType("2");
					} else if (ws.getString(6).equals("1")) {
						vo.setStrMsgString("Patient is Dead.");
						vo.setStrMsgType("2");
					}
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("AdmissionAdviceTransDAO.checkForCrNo() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
			throw e;
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to set all details of particular patient regarding
	 * to episode details on the main page in their corresponding hidden fields
	 * 
	 * @param vo
	 */
	public static void setEpisodeDtl(AdmissionAdviceTransVO vo)
			throws Exception {
		String strProcName = "{call pkg_ipd_view.proc_episode_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getPatCrNo();
		try {

			daoObj = new HisDAO("Admission Advice Transaction",
					"AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			if (vo.getStrUnitValue() == null
					|| vo.getStrUnitValue().equals("0"))
				daoObj.setProcInValue(nProcIndex, "modval", "1", 1);
			else
				daoObj.setProcInValue(nProcIndex, "modval", "2", 1);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum, 2);
			daoObj.setProcInValue(nProcIndex, "dept_code", vo
					.getStrDepartmentValue().equals("") ? "0" : vo
					.getStrDepartmentValue(), 3);
			daoObj.setProcInValue(nProcIndex, "unit_code",
					vo.getStrUnitValue(), 4);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),
					5);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 7);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				if (ws.next()) {
					vo.setStrEpisodeNumber(ws.getString(1));
					vo.setStrVisitValue(ws.getString(2));
					vo.setStrMlcNo(ws.getString(3));
					// System.out.println(""+ws.getString(4));
					vo.setStrDepartmentValue(ws.getString(4));
					vo.setStrUnitValue(ws.getString(5));
					vo.setStrPrimaryCategory(ws.getString(6));
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {

			 e.printStackTrace();
			vo.setStrMsgString("AdmissionAdviceTransDAO.setEpisodeDtl() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
			throw e;
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	/**
	 * This function is used to set all details of particular patient regarding
	 * to episode details on the main page in their corresponding hidden fields
	 * 
	 * @param vo
	 */
	public static void setAdvanceAmountDTL(AdmissionAdviceTransVO vo)
			throws Exception {
		String strProcName = "{call pkg_ipd_view.proc_episode_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		
		try {

			daoObj = new HisDAO("Admission Advice Transaction",
					"AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "4", 1);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum, 2);
			daoObj.setProcInValue(nProcIndex, "dept_code", vo
					.getStrDepartmentValue().equals("") ? "0" : vo
					.getStrDepartmentValue(), 3);
			daoObj.setProcInValue(nProcIndex, "unit_code",
					vo.getStrUnitValue(), 4);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),
					5);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 7);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				if (ws.next()) {
					vo.setStrAdvanceAmount(ws.getString(1));
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {

			 e.printStackTrace();
			vo.setStrMsgString("AdmissionAdviceTransDAO.setAdvnaceAmountValues() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
			throw e;
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	
	/*public static void getAdvanceAmountDTL(AdmissionAdviceTransVO vo)
	throws Exception {
String strProcName = "{call pkg_ipd_view.proc_Advanceamount_dtl(?,?,?,?,?,?,?,?)}";
int nProcIndex = 0;
String strErr = "";
HisDAO daoObj = null;
WebRowSet ws = null;
String strCrNum = vo.getStrCrNo();
try {

	daoObj = new HisDAO("Admission Advice Transaction",
			"AdmissionAdviceTransDAO");
	nProcIndex = daoObj.setProcedure(strProcName);
	daoObj.setProcInValue(nProcIndex, "modval", "1", 1);
	daoObj.setProcInValue(nProcIndex, "pukno", strCrNum, 2);
	daoObj.setProcInValue(nProcIndex, "dept_code",vo.getStrTreatmentCategoryCode(), 3);
	daoObj.setProcInValue(nProcIndex, "unit_code",
			vo.getStrUnitValue(), 4);
	daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),
			5);
    daoObj.setProcInValue(nProcIndex, "ward_type", "",
			6);
	daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
	daoObj.setProcOutValue(nProcIndex, "resultset", 2, 8);
	daoObj.executeProcedureByPosition(nProcIndex);
	strErr = daoObj.getString(nProcIndex, "err");
	if (strErr == null)
		strErr = "";
	ws = daoObj.getWebRowSet(nProcIndex, "resultset");

	if (strErr.equals("")) {

		if (ws.next()) {
			vo.setStrAdvanceAmount(ws.getString(1));
		}

	} else {
		throw new Exception(strErr);
	}
} catch (Exception e) {

	// e.printStackTrace();
	vo.setStrMsgString("AdmissionAdviceTransDAO.setAdvnaceAmountValues() --> "
			+ e.getMessage());
	vo.setStrMsgType("1");
	throw e;
} finally {
	if (daoObj != null) {
		daoObj.free();
		daoObj = null;
	}
}
}*/

	/**
	 * This function is used bring data in ward combo
	 * 
	 * @param voObj
	 */
	public static void setWardDtl(AdmissionAdviceTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String strDepartmentCode = voObj.getStrDepartment();
		String strDeptUnitCode = voObj.getStrUnitValue();
		String strUnitCode = voObj.getStrUnitCode();
		String strAge = voObj.getStrAge();
		String strSex = voObj.getStrSex();
		String strCategory = voObj.getStrTreatmentCategoryCode();
		String strDiseaseType = voObj.getStrDiseaseTypeCode();
		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");
			if (strDepartmentCode != null && strDeptUnitCode != null && strAge != null && strSex != null && strCategory != null) 
			{
				nProcIndex = daoObj.setProcedure(strProcName);	
				
				System.out.println("strDepartmentCode"+strDepartmentCode);
				
				System.out.println("strDeptUnitCode"+strDeptUnitCode);
				
				System.out.println("voObj.getStrHospCode()"+voObj.getStrHospCode());
				
				daoObj.setProcInValue(nProcIndex, "modeVal", "4", 1);
				daoObj.setProcInValue(nProcIndex, "wardtypcode", "", 2);
				daoObj.setProcInValue(nProcIndex, "deptcode",strDepartmentCode, 3);
				daoObj.setProcInValue(nProcIndex, "deptunitcode",strDeptUnitCode, 4);
				daoObj.setProcInValue(nProcIndex, "unitcode", strUnitCode, 5);
				daoObj.setProcInValue(nProcIndex, "age", strAge, 6);
				daoObj.setProcInValue(nProcIndex, "gender", strSex, 7);
				daoObj.setProcInValue(nProcIndex, "treatment_cat", strCategory,8);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(), 9);
				daoObj.setProcInValue(nProcIndex, "effect_from", "", 10);
				daoObj.setProcInValue(nProcIndex, "effect_to", "", 11);
				daoObj.setProcInValue(nProcIndex, "diseasetypcode",strDiseaseType, 12);
				daoObj.setProcInValue(nProcIndex, "wardcode", "", 13);
				daoObj.setProcInValue(nProcIndex, "puk_no", "", 14);
				daoObj.setProcInValue(nProcIndex, "charge_type_id", "", 15);
				daoObj.setProcOutValue(nProcIndex, "err", 1, 16);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2, 17);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {

					voObj.setWardWS(ws);

				} else {
					throw new Exception(strErr);
				}

			}
		} catch (Exception e) {

			e.printStackTrace();
			voObj.setStrMsgString("AdmissionAdviceTransDAO.setWardDtl() --> "
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
	 * This function is used to set initial details for ward type combo that
	 * would be displayed on ward type combo.
	 * 
	 * @param voObj
	 */
	public static void setWardDtlTypes(AdmissionAdviceTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.PROC_HIPT_WARDTYPE_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		// WebRowSet web=null;

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(), 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				voObj.setWardTYPES(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj.setStrMsgString("AdmissionAdviceTransDAO.setWardDtl() --> "
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
	 * This combo is used to set intial details for provison diagnosis detail
	 * combo
	 * 
	 * @param voObj
	 */

	public static void getProvisionDiagnosisDtl(AdmissionAdviceTransVO voObj) {

		String strProcName = "{call pkg_simple_view.proc_diagnosis_list(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNo = voObj.getPatCrNo();
		String strEpisodeNo = voObj.getStrEpisodeNumber().equals("") ? "0"
				: voObj.getStrEpisodeNumber();
		String strVisitNo = voObj.getStrVisitValue().equals("") ? "0" : voObj
				.getStrVisitValue();

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strCrNo != null && strEpisodeNo != null && strVisitNo != null) {

				daoObj.setProcInValue(nProcIndex, "puk", strCrNo, 1);
				daoObj.setProcInValue(nProcIndex, "episodecode", strEpisodeNo,
						2);
				daoObj.setProcInValue(nProcIndex, "visitno", strVisitNo, 3);

				daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2, 5);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {

					voObj.setProvisionDiagnosisWs(ws);

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.getProvisionDiagnosisDtl() --> "
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
	 * This procedure is used to set intial information for treatment category
	 * combo
	 * 
	 * @param voObj
	 */
	public static void setTreatmentCatDtl(AdmissionAdviceTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call PKG_SIMPLE_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeVal", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode(), 2);
			daoObj.setProcInValue(nProcIndex, "puk_no", "", 3);
			daoObj.setProcInValue(nProcIndex, "charge_type_id", "0", 4);
			daoObj.setProcInValue(nProcIndex, "effect_from", "", 5);
			daoObj.setProcInValue(nProcIndex, "effect_TO", "", 6);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 8);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				voObj.setTreatmentCategoryWs(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.setTreatmentCatDtl() --> "
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
	 * This procedure used to set initial information to bring details in
	 * consultant combo on basis of deptunit code
	 * 
	 * @param voObj
	 */
	public static void setConsultantNameDtl(AdmissionAdviceTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String deptUnitCode="";
		if(voObj.getStrMode().equals("1"))
		{
		 deptUnitCode = voObj.getStrUnitValue().equals("") ? "0" : voObj
				.getStrUnitValue();
		}
		else
		{
		 deptUnitCode=voObj.getStrDeptUnitCode();
		}

		String strErr = "";
		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (deptUnitCode != null) {

				daoObj.setProcInValue(nProcIndex, "modval", "6", 1);
				daoObj.setProcInValue(nProcIndex, "deptunitcode", deptUnitCode,
						2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
						.getStrHospCode(), 3);
				daoObj.setProcInValue(nProcIndex, "seatid", "", 4);
				daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2, 6);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				/*
				 * while(ws.next()) { System.out.println("Consultant
				 * Name"+ws.getString(1)); System.out.println("Consultant
				 * Name"+ws.getString(2)); }
				 */

				if (strErr.equals("")) {

					voObj.setConsultantNameWs(ws);
				} else {
					throw new Exception(strErr);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.setConsultantNameDtl() --> "
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
	 * This procedure is used to set mlc number on the basis of puk number.
	 * 
	 * @param voObj
	 */
	public static void setMlcNumber(AdmissionAdviceTransVO voObj) {

		String strProcName = "{call pkg_ipd_view.proc_pat_mlc(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = voObj.getPatCrNo();
		String strEpiNum = voObj.getStrEpisodeNumber().equals("") ? "0" : voObj
				.getStrEpisodeNumber();
		String strMlcNum = "";
		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strCrNum != null && strEpiNum != null) {

				daoObj.setProcInValue(nProcIndex, "puk", strCrNum, 1);
				daoObj.setProcInValue(nProcIndex, "episode", strEpiNum, 2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
						.getStrHospCode(), 3);
				daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2, 5);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {

					if (ws.next()) {
						strMlcNum = ws.getString(1);

						voObj.setStrMlcNo(strMlcNum);
					}

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {
              e.printStackTrace();
			voObj.setStrMsgString("AdmissionAdviceTransDAO.setMlcNumber() --> "
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
	 * This procedure is used to set department details
	 * 
	 * @param voObj
	 */
	public static void setDepartmentName(AdmissionAdviceTransVO voObj) {

		String strProcName = "{call pkg_simple_view.proc_GBLT_DEPARTMENT_MST(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String modVal = "3";
		String strDeptCode = voObj.getStrDepartmentValue().equals("") ? "0"
				: voObj.getStrDepartmentValue();
		String strDeptName = "";
		String hosp_code = voObj.getStrHospCode();

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);
			if (strDeptCode != null) {

				daoObj.setProcInValue(nProcIndex, "modeVal", modVal, 1);
				daoObj.setProcInValue(nProcIndex, "hosp_code", hosp_code, 2);
				daoObj.setProcInValue(nProcIndex, "deptcode", strDeptCode, 3);

				daoObj.setProcInValue(nProcIndex, "puk_no", "", 4);
				daoObj.setProcInValue(nProcIndex, "charge_type_id", "", 5);
				daoObj.setProcInValue(nProcIndex, "effect_from", "", 6);
				daoObj.setProcInValue(nProcIndex, "effect_to", "", 7);
				daoObj.setProcInValue(nProcIndex, "userId", "", 8);

				daoObj.setProcOutValue(nProcIndex, "err", 1, 9);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2, 10);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {

					if (ws.next()) {

						strDeptName = ws.getString(2);

						voObj.setStrDepartment(strDeptName);
						//System.out.println("hasahsa========="+strDeptName);

					}
				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.setDepartmentName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	// This method generate department value list
	public static void setDepartmentNameType(AdmissionAdviceTransVO voObj) {

		String strProcName = "{call pkg_simple_view.proc_dept_mst_view(?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strDeptCode = voObj.getStrDepartmentValue();
		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strDeptCode != null) {

				daoObj.setProcInValue(nProcIndex, "deptcode", strDeptCode, 1);

				daoObj.setProcOutValue(nProcIndex, "err", 1, 2);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2, 3);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					voObj.setDepartTypeWS(ws);

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.setDepartmentName() --> "
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
	 * This procedure is used to set unit name
	 * 
	 * @param voObj
	 */
	public static void setUnitName(AdmissionAdviceTransVO voObj) {

		String strProcName = "{call pkg_simple_view.proc_unit_mst_view(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;
		
		String strDeptUnitCode="";

		//1 In Case Of OFFline Advice Mode  0 in Case Of Online Advice(OPD Desk)
		if(voObj.getStrMode().equals("1"))
		{
		strDeptUnitCode = voObj.getStrUnitValue().equals("") ? "0" : voObj.getStrUnitValue();
		}
		else
		{
		strDeptUnitCode = voObj.getStrDeptUnitCode().equals("") ? "0"	: voObj.getStrDeptUnitCode();
		}
			
		String strDeptUnitName = "";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strDeptUnitCode != null) {

				daoObj.setProcInValue(nProcIndex, "dptunitcode",
						strDeptUnitCode, 1);
				daoObj.setProcInValue(nProcIndex, "deptcode", strDeptUnitCode,
						2);
				daoObj.setProcInValue(nProcIndex, "slno", strDeptUnitCode, 3);
				daoObj.setProcInValue(nProcIndex, "unitname", strDeptUnitCode,
						4);
				daoObj.setProcInValue(nProcIndex, "empno", strDeptUnitCode, 5);
				daoObj.setProcInValue(nProcIndex, "effctdate", strDeptUnitCode,
						6);
				daoObj.setProcInValue(nProcIndex, "seatid", strDeptUnitCode, 7);
				daoObj.setProcInValue(nProcIndex, "entry_dt", strDeptUnitCode,
						8);
				daoObj.setProcInValue(nProcIndex, "isgeneral", strDeptUnitCode,
						9);
				daoObj.setProcInValue(nProcIndex, "unitcode", strDeptUnitCode,
						10);
				daoObj.setProcInValue(nProcIndex, "isvalid", strDeptUnitCode,
						11);
				daoObj.setProcInValue(nProcIndex, "effectvfrm",
						strDeptUnitCode, 12);
				daoObj.setProcInValue(nProcIndex, "effectvto", strDeptUnitCode,
						13);
				daoObj.setProcInValue(nProcIndex, "lstmoddate",
						strDeptUnitCode, 14);
				daoObj.setProcInValue(nProcIndex, "lstmodseatid",
						strDeptUnitCode, 15);
				daoObj.setProcInValue(nProcIndex, "remarks", strDeptUnitCode,
						16);
				daoObj.setProcInValue(nProcIndex, "isexpiry", strDeptUnitCode,
						17);
				daoObj.setProcInValue(nProcIndex, "expiryday", strDeptUnitCode,
						18);
				daoObj.setProcInValue(nProcIndex, "expirymonth",
						strDeptUnitCode, 19);
				daoObj.setProcInValue(nProcIndex, "diagcodetyp",
						strDeptUnitCode, 20);
				daoObj.setProcInValue(nProcIndex, "defaultclose_day",
						strDeptUnitCode, 21);

				daoObj.setProcOutValue(nProcIndex, "err", 1, 22);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2, 23);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {

					if (ws.next()) {

						strDeptUnitName = ws.getString(2);
						voObj.setStrUnit(strDeptUnitName);
					}

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj.setStrMsgString("AdmissionAdviceTransDAO.setUnitName() --> "
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
	 * This function is used to set intial details for room type combo that
	 * would be displayed on bed status pop up window
	 * 
	 * @param voObj
	 */
	public static void setRoomTypeDtl(AdmissionAdviceTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomtype_a(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(), 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				voObj.setRoomTypeWs(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.setRoomTypeDtl() --> "
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
	 * This combo is used to set initial details for bed type combo
	 * 
	 * @param voObj
	 */
	public static void setBedTypeDtl(AdmissionAdviceTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_bed_type_mst(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", "100", 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				voObj.setBedTypeWs(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.setBedTypeDtl() --> "
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
	public static void setBedStatusDtl(AdmissionAdviceTransVO voObj) {

		String strProcName = "{call pkg_ipd_view.proc_bed_status(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strWardCode = voObj.getStrWard();
		String strRoomType = voObj.getStrRoomType();
		String strBedType = voObj.getStrBedType();
		String strBDate = voObj.getStrPropAdmissionDate();

		if (!strBDate.equals(""))
			strProcName = "{call pkg_ipd_view.proc_bed_status(?,?,?,?,?,?)}";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strWardCode != null && strRoomType != null
					&& strBedType != null) {

				if (!strBDate.equals(""))
					daoObj.setProcInValue(nProcIndex, "statusdt", strBDate, 1);
				else {
					HisUtil util = new HisUtil("ipd", "setBedStatusDtl");
					daoObj.setProcInValue(nProcIndex, "statusdt", util
							.getASDate("dd/mmm/yyyy"), 1);
				}

				daoObj.setProcInValue(nProcIndex, "wardcode", strWardCode, 2);
				daoObj.setProcInValue(nProcIndex, "bedtype", strBedType, 3);
				daoObj.setProcInValue(nProcIndex, "roomtype", strRoomType, 4);

				daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2, 6);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {

					voObj.setBedStatusWs(ws);

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.setBedStatusDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void getBedStatusPatientDtls(AdmissionAdviceTransVO voObj) {

		String strProcName = "{call pkg_ipd_view.proc_pat_list(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strBedStatusMode = voObj.getStrBedStatusMode();
		String strWardCode = voObj.getStrWard();
		String strRoomType = voObj.getStrRoomType();
		String strBedType = voObj.getStrBedType();
		String strBDate = voObj.getStrPropAdmissionDate();

		if (!strBDate.equals(""))
			strProcName = "{call pkg_ipd_view.proc_pat_list(?,?,?,?,?,?,?)}";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strWardCode != null && strRoomType != null
					&& strBedType != null) {

				daoObj.setProcInValue(nProcIndex, "list_type",
						strBedStatusMode, 1);
				if (!strBDate.equals(""))
					daoObj.setProcInValue(nProcIndex, "statusdt", strBDate, 2);
				else {
					HisUtil util = new HisUtil("ipd", "setBedStatusDtl");
					daoObj.setProcInValue(nProcIndex, "statusdt", util
							.getASDate("dd/mmm/yyyy"), 2);
				}
				daoObj.setProcInValue(nProcIndex, "wardcode", strWardCode, 3);
				daoObj.setProcInValue(nProcIndex, "bedtype", strBedType, 4);
				daoObj.setProcInValue(nProcIndex, "roomtype", strRoomType, 5);
				daoObj.setProcOutValue(nProcIndex, "err", 1, 6);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2, 7);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {

					voObj.setBedStatusPatientWs(ws);

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.setBedStatusDtl() --> "
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
	 * This combo is used to set initial details for Hospital diagnosis detail
	 * combo
	 * 
	 * @param voObj
	 */
	public static void getHospitalDiagnosisDtl(AdmissionAdviceTransVO voObj) {

		String strProcName = "{call pkg_simple_view.proc_diagnosis_hosiptal_list(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "searchmode", voObj
					.getStrSearchMode(), 1);
			daoObj.setProcInValue(nProcIndex, "searchstr", voObj
					.getStrSearchString(), 2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(), 3);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 5);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				voObj.setHospitalDiagnosisWs(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.getHospitalDiagnosisDtl() --> "
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
	public static void getIcd10DiagnosisDtl(AdmissionAdviceTransVO voObj) {

		String strProcName = "{call pkg_simple_view.Proc_Diagnosis_Icd10_List(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "searchmode", voObj
					.getStrSearchMode(), 1);
			daoObj.setProcInValue(nProcIndex, "searchstr", voObj
					.getStrSearchString(), 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				voObj.setIcd10DiagnosisWs(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.getIcd10DiagnosisDtl() --> "
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
	 * This procedure is used to insert record into data base
	 * 
	 * @param voObj
	 */
	public static void insert(AdmissionAdviceTransVO voObj) 
	{
		String strProcName1 = "{call pkg_ipd_dml.proc_pat_advice_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		String strProcName2 = "{call pkg_ipd_dml.proc_pat_diag_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		int nProcIndex1 = 0;
		int nProcIndex2 = 0;

		String strErr = "";

		HisDAO daoObj = null;
		
		if(voObj.getStrEpisodeNumber().equals("NA"))
			voObj.setStrEpisodeNumber("0");	

		try 
		{
			daoObj = new HisDAO("ADT", "DAOAdmissionAdviceTrans");

			nProcIndex1 = daoObj.setProcedure(strProcName1);
			
			daoObj.setProcInValue(nProcIndex1, "query_code", "I_1", 1);
			daoObj.setProcOutValue(nProcIndex1, "err", 1, 2);
			daoObj.setProcInValue(nProcIndex1, "puk", voObj.getPatCrNo(), 3);
			daoObj.setProcInValue(nProcIndex1, "adm_adviceno", "", 4);
			daoObj.setProcInValue(nProcIndex1, "adm_advicedt", "", 5);
			daoObj.setProcInValue(nProcIndex1, "epicode", voObj.getStrEpisodeNumber(), 6);
			daoObj.setProcInValue(nProcIndex1, "preference_admdt", voObj.getStrPropAdmissionDate(), 7);
			daoObj.setProcInValue(nProcIndex1, "pref_wardcode", voObj.getStrWard(), 8);
			daoObj.setProcInValue(nProcIndex1, "pref_bedtype", voObj.getStrBedType(), 9);
			daoObj.setProcInValue(nProcIndex1, "adm_priority", voObj.getStrAdmissionTypeValues(), 10);
			daoObj.setProcInValue(nProcIndex1, "deptunit_code", voObj.getStrUnitValue(), 11);
			daoObj.setProcInValue(nProcIndex1, "emp_no", voObj.getStrConsultantName(), 12);
			daoObj.setProcInValue(nProcIndex1, "pat_staylength", voObj.getStrMaxLenStay(), 13);
			if (voObj.getStrTreatment().equals("0")) 
			{
				daoObj.setProcInValue(nProcIndex1, "tratment_cat", voObj.getStrPrimaryCategory(), 14);
			} 
			else 
			{
				daoObj.setProcInValue(nProcIndex1, "tratment_cat", voObj.getStrTreatment(), 14);
			}
			daoObj.setProcInValue(nProcIndex1, "advice_status", voObj.getStrApprovalStatus(), 15);
			daoObj.setProcInValue(nProcIndex1, "booking_dt", "", 16);
			daoObj.setProcInValue(nProcIndex1, "booking_qno", "", 17);
			daoObj.setProcInValue(nProcIndex1, "entry_dt", "", 18);
			daoObj.setProcInValue(nProcIndex1, "seatid", voObj.getStrSeatId(),19);
			daoObj.setProcInValue(nProcIndex1, "isvalid", "1", 20);
			daoObj.setProcInValue(nProcIndex1, "visitno", voObj.getStrVisitValue(), 21);
			daoObj.setProcInValue(nProcIndex1, "roomtype", voObj.getStrRoomType(), 22);
			daoObj.setProcInValue(nProcIndex1, "diseasetypcode", voObj.getStrDiseaseType(), 23);
			daoObj.setProcInValue(nProcIndex1, "booking_status", "", 24);
			daoObj.setProcInValue(nProcIndex1, "hosp_code", voObj.getStrHospCode(), 25);
			daoObj.setProcInValue(nProcIndex1, "remarks",voObj.getStrRemarks(), 26);
			daoObj.setProcInValue(nProcIndex1, "billFlag", voObj.getStrBillFlag(), 27);
			daoObj.setProcInValue(nProcIndex1, "advanceFlag", voObj.getStrAdvanceFlag(), 28);
			daoObj.setProcInValue(nProcIndex1, "advance_amt", voObj.getStrAdvanceAmount(), 29);
			daoObj.setProcInValue(nProcIndex1, "planned_surgery_date", voObj.getStrPlannedSurgeryDate(), 30);
			daoObj.setProcInValue(nProcIndex1, "ip_address", voObj.getStrIpAddress(), 31);
			daoObj.setProcOutValue(nProcIndex1, "adv_no	", 1, 32);		
			daoObj.setProcInValue(nProcIndex1, "issue_estimation_certificate","0", 33);
			daoObj.setProcInValue(nProcIndex1, "tariff_id",voObj.getStrPackageApply().replace("^", "#").split("#")[0], 34);	
			daoObj.setProcInValue(nProcIndex1, "mac", GetNetworkAddress.GetAddress("mac"), 35);		

			daoObj.execute(nProcIndex1, 1);

			if (voObj.getStrProvisionDiagnosis() != null) 
			{
				for (int i = 0; i < voObj.getStrProvisionDiagnosis().length; i++) 
				{
					nProcIndex2 = daoObj.setProcedure(strProcName2);
					daoObj.setProcInValue(nProcIndex2, "puk", voObj.getPatCrNo(), 1);
					daoObj.setProcInValue(nProcIndex2, "episode_code", voObj.getStrEpisodeNumber().equals("") ? "0" : voObj.getStrEpisodeNumber(), 2);
					daoObj.setProcInValue(nProcIndex2, "visit_no", voObj.getStrVisitValue().equals("") ? "0" : voObj.getStrVisitValue(), 3);
					daoObj.setProcInValue(nProcIndex2, "diag_type_code", voObj.getStrDiagTypeCode()[i], 4);
					daoObj.setProcInValue(nProcIndex2, "diag_code", voObj.getStrProvisionDiagnosis()[i], 5);
					daoObj.setProcInValue(nProcIndex2, "seat_id", voObj.getStrSeatId(), 6);
					daoObj.setProcInValue(nProcIndex2, "entry_date", "", 7);
					daoObj.setProcInValue(nProcIndex2, "isvalid", "1", 8);
					daoObj.setProcInValue(nProcIndex2, "episode_dt", "", 9);
					daoObj.setProcInValue(nProcIndex2, "remarks", voObj.getStrDiagRemarks()[i], 10);
					daoObj.setProcInValue(nProcIndex2, "diag_code_type", voObj.getStrDiagCodeType()[i], 11);
					daoObj.setProcInValue(nProcIndex2, "hosp_code", voObj.getStrHospCode(), 12);
					daoObj.setProcInValue(nProcIndex2, "isRepeat", voObj.getStrisRepaeat()[i], 13);
					daoObj.setProcInValue(nProcIndex2, "siteCode", voObj.getStrDiseaseSite()[i], 14);
					daoObj.execute(nProcIndex2, 1);
				}
			}

			synchronized (daoObj) 
			{
				daoObj.fire();
				voObj.setStrMsg("Record is successfully saved");
			}
			strErr = daoObj.getString(nProcIndex1, "err");

			if (strErr == null)
				strErr = "";

			if (!strErr.equals("")) 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("AdmissionAdviceTransDAO.insert() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
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
	 * This procedure is used to set age and sex of the patient on the basis of
	 * c.r number.
	 * 
	 * @param voObj
	 */
	public static void setAgeandSex(AdmissionAdviceTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_pat_demo_address_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		String strCrNum = voObj.getPatCrNo();
		String strUnitCode = "";
		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (strCrNum != null && !strCrNum.equals("")) {
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum, 1);
				daoObj.setProcInValue(nProcIndex, "addresstype", "1", 2);
				daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2, 4);
				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {

					if (ws.next()) {

						String strAgeandSex = ws.getString(2);
						voObj.setStrPatName(ws.getString(3));
						
						voObj.setStrTreatmentCategoryCode(ws.getString(7));

						String[] temp = strAgeandSex.replace("/", "#").split(
								"#");
						// This body setting age code
						for (int i = 0; i < temp[0].length(); i++) {
							if (temp[0].charAt(i) == 'Y') {
								strUnitCode = "3";
								break;
							}
							if (temp[0].charAt(i) == 'M') {
								strUnitCode = "2";
								break;
							}
							if (temp[0].charAt(i) == 'D') {
								strUnitCode = "1";
								break;
							}
							if (temp[0].charAt(i) == 'W') {
								strUnitCode = "4";
								break;
							}
							if (temp[0].charAt(i) == 'H') {

								strUnitCode = "1";
								break;
							}
						}

						voObj.setStrUnitCode(strUnitCode);
						String temp1 = "0";
						if (temp[1].length() == 4) {
							temp1 = "1";
						} else {
							temp1 = "2";
						}

						voObj.setStrAge(strAgeandSex.substring(0, temp[0]
								.length() - 2));
						// voObj.setStrAge("24");
						voObj.setStrSex(temp1);

					}

				} else {
					throw new Exception(strErr);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj.setStrMsgString("DAOIpd.setPatientDtl() --> "
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
	 * This function is used to set initial details for ward combo
	 * 
	 * @param vo
	 */
	public static void getWardValues(AdmissionAdviceTransVO vo) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		daoObj = new HisDAO("Patient Admission Transaction",
				"PatientAdmissionTransDAO.getWardValues()");
		try {

			daoObj = new HisDAO("Admission Advice Transaction",
					"PatientAdmissionTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeVal", "11", 1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode", vo
					.getStrWardTypeCode(), 2);
			daoObj.setProcInValue(nProcIndex, "deptcode", vo
					.getStrDepartmentValue(), 3);
			daoObj.setProcInValue(nProcIndex, "deptunitcode", vo
					.getStrUnitValue(), 4);
			daoObj.setProcInValue(nProcIndex, "unitcode", vo.getStrUnitCode(),
					5);
			daoObj.setProcInValue(nProcIndex, "age", vo.getStrAge(), 6);
			daoObj.setProcInValue(nProcIndex, "gender", vo.getStrSex(), 7);
			daoObj.setProcInValue(nProcIndex, "treatment_cat", vo
					.getStrTreatmentCategoryCode(), 8);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),
					9);
			daoObj.setProcInValue(nProcIndex, "effect_from", "", 10);
			daoObj.setProcInValue(nProcIndex, "effect_to", "", 11);
			daoObj.setProcInValue(nProcIndex, "diseasetypcode", "0", 12);
			daoObj.setProcInValue(nProcIndex, "wardcode", "0", 13);
			daoObj.setProcInValue(nProcIndex, "puk_no", vo.getPatCrNo(), 14);
			daoObj.setProcInValue(nProcIndex, "charge_type_id", "0", 15);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 16);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 17);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setWardWS(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("AdmissionAdviceTransDAO.getWardValues() --> "
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
	 * This function is used to set initial details for room combo on the basis
	 * of ward code and room type code
	 * 
	 * @param vo
	 */
	public static void getRoomValues(AdmissionAdviceTransVO vo) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modval", "3", 1);
			daoObj.setProcInValue(nProcIndex, "roomtypcode", vo
					.getStrRoomType(), 2);
			daoObj.setProcInValue(nProcIndex, "wardcode", vo.getStrWard(), 3);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),
					4);
			daoObj.setProcInValue(nProcIndex, "unitcode", vo.getStrUnitCode(),
					5);
			daoObj.setProcInValue(nProcIndex, "age", vo.getStrAge(), 6);
			daoObj.setProcInValue(nProcIndex, "deptcode", "", 7);
			daoObj.setProcInValue(nProcIndex, "deptunitcode", vo
					.getStrUnitValue(), 8);
			daoObj.setProcInValue(nProcIndex, "gender", vo.getStrSex(), 9);
			daoObj.setProcInValue(nProcIndex, "treatment_cat", vo
					.getStrTreatmentCategoryCode(), 10);
			daoObj.setProcInValue(nProcIndex, "puk_no", vo.getPatCrNo(), 11);
			daoObj.setProcInValue(nProcIndex, "diseasetypcode", "0", 12);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 13);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 14);
			daoObj.executeProcedureByPosition(nProcIndex);
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
			vo.setStrMsgString("AdmissionAdviceTransDAO.getRoomValues() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	public static void getBedValues(AdmissionAdviceTransVO vo) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("Admission Advice  Transaction",
					"NewBornBabyTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "wardcode", vo.getStrWard(), 2);
			daoObj.setProcInValue(nProcIndex, "roomno", vo.getStrRoomCode(), 3);
			daoObj.setProcInValue(nProcIndex, "bedtypcode", vo.getStrBedType(),
					4);
			daoObj.setProcInValue(nProcIndex, "bedstatcode", "", 5);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),
					6);
			daoObj.setProcInValue(nProcIndex, "deptunit", vo.getStrUnitValue(),
					7);
			daoObj.setProcInValue(nProcIndex, "bedcode", "", 8);
			daoObj.setProcInValue(nProcIndex, "shr_chk", "",9);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 10);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 11);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				vo.setBedDetailWs(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			vo.setStrMsgString("AdmissionAdviceTransDAO.getBedValues() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	/**
	 * This procedure is used to set initial information for disease type combo
	 * 
	 * @param voObj
	 */
	public static void setDiseaseType(AdmissionAdviceTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_hipt_diseasetype_mst(?,?,?)}";
		String strHosCode = voObj.getStrHospCode();
		int nProcIndex = 0;

		String strErr = "";
		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hospital_code", strHosCode, 1);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 3);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				voObj.setDiseaseTypeWs(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj.setStrMsgString("DAOIpd.setDiseaseType() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void setListDtl(AdmissionAdviceTransVO voObj) {

		String strProcName = "{call pkg_simple_view.proc_advice_dtl(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strAdvDate = voObj.getStrAdviceDate();
		String strDepartmentCode = voObj.getStrDepartment();
		String strUnitCode = voObj.getStrUnit();

		System.out.println(strAdvDate + " " + voObj.getStrUnitValue() + " "
				+ voObj.getStrHospCode() + " " + voObj.getStrCrNo());
		if (!strAdvDate.equals(""))
			strProcName = "{call pkg_simple_view.proc_advice_dtl(?,?,?,?,?,?)}";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"DAOAdmissionAdviceTrans");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "advdate", strAdvDate, 1);
			daoObj.setProcInValue(nProcIndex, "unitcode", voObj
					.getStrUnitValue(), 2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(), 3);
			daoObj.setProcInValue(nProcIndex, "puk", voObj.getStrCrNo(), 4);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 6);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			// System.out.println("strErr"+strErr+":: "+strAdvDate);
			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			/*
			 * if(ws.next()==false) { voObj.setStrWarning("Admission advice has
			 * not been yet generated for this department by today"); }
			 */
			if (strErr.equals("")) {

				voObj.setListWs(ws);

			} else {
				throw new Exception(strErr);
			}

			// }

		} catch (Exception e) {
			// System.out.println("error");
			voObj.setStrMsgString("AdmissionAdviceTransDAO.setListDtl() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void checkDuplicate(AdmissionAdviceTransVO vo) {
		String strProcName = "{call pkg_ipd_view.proc_duplicacy_dtl(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
			daoObj = new HisDAO("ADT", "AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "puk", vo.getPatCrNo(), 1);
			daoObj.setProcInValue(nProcIndex, "wardcode", vo.getStrWard(), 2);
			daoObj.setProcInValue(nProcIndex, "deptunitcode", vo
					.getStrUnitValue(), 3);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),
					4);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 6);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {
				if (ws.next()) {
					vo.setStrFlag(ws.getString(1));
					System.out.println("ws.getString(1) -----------------> "+ws.getString(1));
				}
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			vo.setStrMsgString("AdmissionAdviceTransDAO.checkDuplicate() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}

	/**
	 * This is used to get previous diagnosis details on the basis of his visit
	 * no and episode code
	 * 
	 * @param voObj
	 */
	public static void getPrevoiusDiagnosisDtl(AdmissionAdviceTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_simple_view.Proc_Hrgt_Episode_Diag_Dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		
		System.out.println("visit value"+voObj.getStrVisitValue());

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			/**
			 * 
			 */

			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj
					.getStrHospCode(), 2);
			daoObj.setProcInValue(nProcIndex, "puk", voObj.getPatCrNo(), 3);
			daoObj.setProcInValue(nProcIndex, "episode_code", voObj
					.getStrEpisodeNumber().equals("") ? "0" : voObj
					.getStrEpisodeNumber(), 4);
			daoObj.setProcInValue(nProcIndex, "visitno", voObj
					.getStrVisitValue().equals("") ? "0" : voObj
					.getStrVisitValue(), 5);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 7);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				voObj.setPreviousDiagnosisWs(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			voObj
					.setStrMsgString("AdmissionAdviceTransDAO.getPrevoiusDiagnosisDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void setDeptComboValues(AdmissionAdviceTransVO _AdmissionAdviceTransVO) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.Proc_Department(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("ADT","AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "3", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",_AdmissionAdviceTransVO.getStrHospCode(), 2);
			daoObj.setProcInValue(nProcIndex, "puk", _AdmissionAdviceTransVO.getStrCrNo(), 3);
			daoObj.setProcInValue(nProcIndex, "seatid", "", 4);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 6);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals(""))
				_AdmissionAdviceTransVO.setStrDeptComboValuesWs(ws);
			else
				throw new Exception(strErr);
		} 
		catch (Exception e) 
		{
			_AdmissionAdviceTransVO.setStrMsgString("AdmissionAdviceTransDAO.setDeptComboValues() --> "+ e.getMessage());
			_AdmissionAdviceTransVO.setStrMsgType("1");
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

	public static void setUnitComboValues(
			AdmissionAdviceTransVO _AdmissionAdviceTransVO) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.Proc_Unit(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "2", 1);
			daoObj.setProcInValue(nProcIndex, "dept_code",
					_AdmissionAdviceTransVO.getStrDepartmentCode(), 2);
			daoObj.setProcInValue(nProcIndex, "hosp_code",
					_AdmissionAdviceTransVO.getStrHospCode(), 3);
			daoObj.setProcInValue(nProcIndex, "puk", _AdmissionAdviceTransVO
					.getStrCrNo(), 4);
			daoObj.setProcInValue(nProcIndex, "seatid", "", 5);
			daoObj.setProcInValue(nProcIndex, "wardcode", "", 6);
			daoObj.setProcInValue(nProcIndex, "unitcode", "", 7);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 9);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals(""))
				_AdmissionAdviceTransVO.setStrUnitComboValuesWs(ws);
			else
				throw new Exception(strErr);
		} catch (Exception e) {
			_AdmissionAdviceTransVO
					.setStrMsgString("AdmissionAdviceTransDAO.setUnitComboValues() --> "
							+ e.getMessage());
			_AdmissionAdviceTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void setAdmissionTypeValue(
			AdmissionAdviceTransVO _AdmissionAdviceTransVO) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_simple_view.Proc_Hrgt_Episode_Diag_Dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			/**
			 * 
			 */

			daoObj.setProcInValue(nProcIndex, "modeval", "2", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",
					_AdmissionAdviceTransVO.getStrHospCode(), 2);
			daoObj.setProcInValue(nProcIndex, "puk", _AdmissionAdviceTransVO
					.getPatCrNo(), 3);
			daoObj
					.setProcInValue(nProcIndex, "episode_code",
							_AdmissionAdviceTransVO.getStrEpisodeNumber()
									.equals("") ? "0" : _AdmissionAdviceTransVO
									.getStrEpisodeNumber(), 4);
			daoObj.setProcInValue(nProcIndex, "visitno",
					_AdmissionAdviceTransVO.getStrVisitValue().equals("") ? "0"
							: _AdmissionAdviceTransVO.getStrVisitValue(), 5);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 7);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				_AdmissionAdviceTransVO.setStrAdmissionTypeWS(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			_AdmissionAdviceTransVO
					.setStrMsgString("AdmissionAdviceTransDAO.setAdmissionTypeValue() --> "
							+ e.getMessage());
			_AdmissionAdviceTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	
	public static void setTariffNameValues(
			AdmissionAdviceTransVO _AdmissionAdviceTransVO) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_simple_view.Proc_Hrgt_Episode_Diag_Dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "3", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",
					_AdmissionAdviceTransVO.getStrHospCode(), 2);
			daoObj.setProcInValue(nProcIndex, "puk", _AdmissionAdviceTransVO
					.getPatCrNo(), 3);
			daoObj
					.setProcInValue(nProcIndex, "episode_code",
							_AdmissionAdviceTransVO.getStrEpisodeNumber()
									.equals("") ? "0" : _AdmissionAdviceTransVO
									.getStrEpisodeNumber(), 4);
			daoObj.setProcInValue(nProcIndex, "visitno",
					_AdmissionAdviceTransVO.getStrVisitValue().equals("") ? "0"
							: _AdmissionAdviceTransVO.getStrVisitValue(), 5);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 7);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {

				_AdmissionAdviceTransVO.setStrTariffNameWS(ws);

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();
			_AdmissionAdviceTransVO
					.setStrMsgString("AdmissionAdviceTransDAO.setTariffNameValues() --> "+e.getMessage());
			_AdmissionAdviceTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void setAccDetails(
			AdmissionAdviceTransVO _AdmissionAdviceTransVO) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_acc_details(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "crno", _AdmissionAdviceTransVO
					.getPatCrNo(), 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", _AdmissionAdviceTransVO.getStrHospCode(),
					2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				if (ws.next()) {
					_AdmissionAdviceTransVO.setStrAccountNo(ws.getString(1));
					_AdmissionAdviceTransVO.setStrAdvanceAmtpaid(ws.getString(2));
					_AdmissionAdviceTransVO.setStrAdvDepDate(ws.getString(3));
					_AdmissionAdviceTransVO.setStrAccType(ws.getString(4));
				}

			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {

			 e.printStackTrace();
			 _AdmissionAdviceTransVO.setStrMsgString("AdmissionAdviceTransDAO.setAccDetails() --> "
					+ e.getMessage());
			 _AdmissionAdviceTransVO.setStrMsgType("1");
		
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

}
	public static void getAdvanceAmountDTL(AdmissionAdviceTransVO vo) throws Exception 
	{		
		int funcIndex = 0;
		String retVal = null;
		HisDAO daoObj = null;
		
	
		try 
		{
			daoObj = new HisDAO("ADT","AdmissionAdviceTransDAO");
			funcIndex = daoObj.setFunction("{? = call BILL_MST.get_advance_part_amt(?,?,?,?,?,?)}");
			daoObj.setFuncInValue(funcIndex, 2, "1");
			daoObj.setFuncInValue(funcIndex, 3, vo.getStrHospCode());
			daoObj.setFuncInValue(funcIndex, 4, vo.getStrWardCode());
			daoObj.setFuncInValue(funcIndex, 5, "2");//CHARGE TYPE-IPD
			daoObj.setFuncInValue(funcIndex, 6, vo.getStrTreatmentCategoryCode());
		    daoObj.setFuncInValue(funcIndex, 7, "0");//IPD CHARGE TYPE-.THIS VALUE NOT USEFUL /LOGIC DEFINED IN FUCTION
			daoObj.setFuncOutValue(funcIndex, 1);
			daoObj.executeFunction(funcIndex);
			retVal = daoObj.getFuncString(funcIndex);
			
			vo.setStrAdvanceAmount(retVal);
				
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			vo.setStrMsgString("AdmissionAdviceTransDAO.setAdvnaceAmountValues() --> "+ e.getMessage());
			vo.setStrMsgType("1");
			throw e;
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
	
	public static void getBillingPackageNames(AdmissionAdviceTransVO _AdmissionAdviceTransVO) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_bill_view.proc_hblt_package_mst(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("ADT","AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code",_AdmissionAdviceTransVO.getStrHospCode(), 1);
			daoObj.setProcInValue(nProcIndex, "ward_id", _AdmissionAdviceTransVO.getStrWardCode(), 2);//here ward is passed to get package cost according to wards
			daoObj.setProcInValue(nProcIndex, "modeval", "1", 3);
			daoObj.setProcInValue(nProcIndex, "catcode", _AdmissionAdviceTransVO.getStrTreatmentCategoryCode(), 4);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2, 6);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				_AdmissionAdviceTransVO.setPackageComboValuesWs(ws);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			_AdmissionAdviceTransVO.setStrMsgString("AdmissionAdviceTransDAO.getBillingPackageNames() --> "+ e.getMessage());
			_AdmissionAdviceTransVO.setStrMsgType("1");
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