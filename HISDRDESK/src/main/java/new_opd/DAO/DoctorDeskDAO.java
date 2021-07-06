package new_opd.DAO;

import java.util.ArrayList;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.sql.rowset.WebRowSet;

import new_opd.vo.DoctorDeskVO;

public class DoctorDeskDAO {

	public static void getInitilaData(DoctorDeskVO vo) {
		
		String strproc_name = "{call pkg_opdDrDesk_view.Proc_patient_dtl(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.setItemCatValues()");
			//System.out.println("llllll::::"+vo.getStrDeptCode()==null || vo.getStrDeptCode().equals("0") ? "0" :vo.getStrDeptCode());
			nProcIndex = dao.setProcedure(strproc_name);
			if(vo.getStrMode().equalsIgnoreCase("PRESCRIPTIONBOOKMARKING"))
			dao.setProcInValue(nProcIndex, "modeval", "4",1);
			else {
				dao.setProcInValue(nProcIndex, "modeval", "1",1);
			}
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("") ? "0" :vo.getStrDeptCode().split("#")[1] ,4);
			dao.setProcInValue(nProcIndex, "Room_No", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("")  ? "0" :vo.getStrDeptCode().split("#")[2],5);
			dao.setProcInValue(nProcIndex, "prev_date", vo.getStrPrevDate()==null ||vo.getStrPrevDate().equalsIgnoreCase("")  ? "0" :vo.getStrPrevDate(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getInitilaData wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrInitialWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getInitilaData()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getInitilaData() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	
public static void getConfigData(DoctorDeskVO vo) {
		
		String strproc_name = "{call pkg_opdDrDesk_view.Proc_patient_dtl(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.setItemCatValues()");
			//System.out.println("llllll::::"+vo.getStrDeptCode()==null || vo.getStrDeptCode().equals("0") ? "0" :vo.getStrDeptCode());
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "3",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("") ? "0" :vo.getStrDeptCode().split("#")[1] ,4);
			dao.setProcInValue(nProcIndex, "Room_No", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("")  ? "0" :vo.getStrDeptCode().split("#")[2],5);
			dao.setProcInValue(nProcIndex, "prev_date", vo.getStrPrevDate()==null ||vo.getStrPrevDate().equalsIgnoreCase("")  ? "0" :vo.getStrPrevDate(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getInitilaData wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrOPDConfigWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getInitilaData()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getInitilaData() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}

public static void getpatientTeleConsultancyData(DoctorDeskVO vo) {
		
		String strproc_name = "{call pkg_opdDrDesk_view.Proc_patient_dtl(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.setItemCatValues()");
			//System.out.println("llllll::::"+vo.getStrDeptCode()==null || vo.getStrDeptCode().equals("0") ? "0" :vo.getStrDeptCode());
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "2",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("") ? "0" :vo.getStrDeptCode().split("#")[1] ,4);
			dao.setProcInValue(nProcIndex, "Room_No", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("")  ? "0" :vo.getStrDeptCode().split("#")[2],5);
			dao.setProcInValue(nProcIndex, "prev_date", vo.getStrPrevDate()==null ||vo.getStrPrevDate().equalsIgnoreCase("")  ? "0" :vo.getStrPrevDate(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getpatientTeleConsultancyData wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrTeleConsultancyDataWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getInitilaData()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getInitilaData() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}


public static void getpatientRefferalConsultancyData(DoctorDeskVO vo) {
	
	String strproc_name = "{call pkg_opdDrDesk_view.Proc_patient_dtl(?,?,?,?,?,?,?,?)}";
	HisDAO dao = null;
	HisUtil util = null;

	int nProcIndex = 0;

	String strErr = "";
	WebRowSet wsResult = null;

	try {
		util = new HisUtil("OPDDRDRDESk",
				"DoctorDeskDAO()");
		dao = new HisDAO("MMS",
				"transactions.PODeskGenerateTransDAO.setItemCatValues()");
		//System.out.println("llllll::::"+vo.getStrDeptCode()==null || vo.getStrDeptCode().equals("0") ? "0" :vo.getStrDeptCode());
		nProcIndex = dao.setProcedure(strproc_name);
		dao.setProcInValue(nProcIndex, "modeval", "5",1);
		dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
		dao.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
		dao.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("") ? "0" :vo.getStrDeptCode().split("#")[1] ,4);
		dao.setProcInValue(nProcIndex, "Room_No", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("")  ? "0" :vo.getStrDeptCode().split("#")[2],5);
		dao.setProcInValue(nProcIndex, "prev_date", vo.getStrPrevDate()==null ||vo.getStrPrevDate().equalsIgnoreCase("")  ? "0" :vo.getStrPrevDate(),6);
		dao.setProcOutValue(nProcIndex, "err", 1,7);
		dao.setProcOutValue(nProcIndex, "resultset", 2,8);
		dao.executeProcedureByPosition(nProcIndex);

		strErr = dao.getString(nProcIndex, "err");

		wsResult = dao.getWebRowSet(nProcIndex, "resultset");
		//System.out.println("getpatientTeleConsultancyData wsResult::::::::::::"+wsResult.size());
		if (strErr == null || strErr.equals(""))
			vo.setStrRefeConsultancyDataWb(wsResult);
		else
			throw new Exception(strErr);
	} catch (Exception _Err) {
		HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getInitilaData()-->", _Err.getMessage() + "-->" + _Err);
		_Err.printStackTrace();
		vo
				.setStrMsgString("DoctorDeskVO.getInitilaData() --> "
						+ _Err.getMessage());
		vo.setStrMsgType("1");
	} finally {
		if (dao != null) {
			dao.free();
			dao = null;
		}
		util = null;
		wsResult = null;
	}
	
}

	public static void getInvestogation(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_invtest_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode()==null ? "0" :vo.getStrDeptCode() ,4);
			dao.setProcInValue(nProcIndex, "Room_No", vo.getStrRoomCode()==null ? "0" : vo.getStrRoomCode(),5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getInvestogation wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrTestWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getInvestogation()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getInvestogation() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	
	public static void getdrugdtl(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_drug_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getdrugdtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDrugWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getdrugdtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getdrugdtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}

	public static void getdrug_dosage_dtl(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_dosage_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getdrug_dosage_dtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDosageWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getdrug_dosage_dtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getdrug_dosage_dtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	
	
	public static void getMacrosDtl(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_macros_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.DoctorDeskDAO..setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getMacrosDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrMacrosWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getMacrosDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getMacrosDtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	
	public static void getClinicalProcedureDtls(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_macros_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.DoctorDeskDAO..setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "2",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getMacrosDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrCinicalProcudreWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getClinicalProcedureDtls()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getMacrosDtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	
	public static void getDeptDtl(DoctorDeskVO vo,HttpServletRequest request) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_dept_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.DoctorDeskDAO..setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getDeptDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
			{
				vo.setStrDeptWb(wsResult);
				if(vo.getStrDeptUnitCode()==null || vo.getStrDeptUnitCode().equals("")){
				if(wsResult.first()){
					vo.setStrHiddenDeptCode(wsResult.getString(1));
				}
				wsResult.beforeFirst();
				}
			}
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getClinicalProcedureDtls()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getDeptDtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	public static void getHospitalHeaderName(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_dept_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.DoctorDeskDAO..setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "8",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getDeptDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
			{
				vo.setStrHospitalHeaderWs(wsResult);
				
			}
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getClinicalProcedureDtls()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getDeptDtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}

	public static void getRefferalDeptDtl(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_dept_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.DoctorDeskDAO..setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "2",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getDeptDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrRefferalDeptWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getRefferalDeptDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getDeptDtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	public static void getBookMarksTestDtl(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_Bookmarks_test_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.DoctorDeskDAO..setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getBookMarksTestDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrBookMarksTestWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getBookMarksTestDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getBookMarksTestDtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}

	public static String getPrevData(DoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
        int nProcIndex = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
       
         
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
        
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
            dao = new HisDAO("WebServices", "HospotalMgmtDao.getHospitalMgmtData()");
            nProcIndex = dao.setProcedure(proc_name1);
            dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  "",3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "episodecode", vo.getStrEpisodeCode(),5);
			dao.setProcInValue(nProcIndex, "puk", vo.getStrPuk(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);
            err=dao.getString(nProcIndex, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		js.put(key, value);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              }
          if(ws != null){
        		ws.close();
        		ws = null;
        	}        
            vo.setStrDrugPrevCodeJSON(jsonolist);
            //System.out.println("jsonolist.toString()DRUG"+jsonolist.toString());
            return jsonolist.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getPrevData()-->", e.getMessage() + "-->" + e);
        	vo.setStrMsgType("1");
            e.printStackTrace();
            return jsonolist.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}
	public static String getPrevvisitresonData(DoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
        int nProcIndex = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
       
         
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
        
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
            dao = new HisDAO("WebServices", "HospotalMgmtDao.getHospitalMgmtData()");
            nProcIndex = dao.setProcedure(proc_name1);
            dao.setProcInValue(nProcIndex, "modeval", "7",1);
			dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  "",3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "episodecode", vo.getStrEpisodeCode(),5);
			dao.setProcInValue(nProcIndex, "puk", vo.getStrPuk(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);
            err=dao.getString(nProcIndex, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		js.put(key, value);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              }
          if(ws != null){
        		ws.close();
        		ws = null;
        	}        
            vo.setStrVisitReasonJSON(jsonolist);
            //System.out.println("jsonolist.toString()DRUG"+jsonolist.toString());
            return jsonolist.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getPrevData()-->", e.getMessage() + "-->" + e);
        	vo.setStrMsgType("1");
            e.printStackTrace();
            return jsonolist.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}
	
	public static String getPrevDataInv(DoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
        int nProcIndex = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
       
         
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
        
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
            dao = new HisDAO("WebServices", "HospotalMgmtDao.getHospitalMgmtData()");
            nProcIndex = dao.setProcedure(proc_name1);
            dao.setProcInValue(nProcIndex, "modeval", "2",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", "",3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "episodecode", vo.getStrEpisodeCode(),5);
			dao.setProcInValue(nProcIndex, "puk", vo.getStrPuk(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);
            err=dao.getString(nProcIndex, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		js.put(key, value);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              }
          if(ws != null){
        		ws.close();
        		ws = null;
        	}   
            vo.setStrInvPrevCodeJSON(jsonolist);
            //System.out.println("jsonolist.toString()INV"+jsonolist.toString());
            return jsonolist.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevData()-->", e.getMessage() + "-->" + e);
        	vo.setStrMsgType("1");
            e.printStackTrace();
            return jsonolist.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}
	
	public static String getPrevVisitReason(DoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
        int nProcIndex = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
       
         
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
        
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
            dao = new HisDAO("WebServices", "HospotalMgmtDao.getHospitalMgmtData()");
            nProcIndex = dao.setProcedure(proc_name1);
            dao.setProcInValue(nProcIndex, "modeval", "3",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrVistNo(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "episodecode", vo.getStrEpisodeCode(),5);
			dao.setProcInValue(nProcIndex, "puk", vo.getStrPuk(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);
            err=dao.getString(nProcIndex, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		if(key.equalsIgnoreCase("HRSTR_JSON_DATA")) //hrstr_json_data
                		{
                			
                			JSONParser parser = new JSONParser();
                    		org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(value);
                    		js.put(key, json);
                		}else {
                			js.put(key, value);
                		}
                	}
                	jsonolist.add(js) ;               	
                	
                }
              }
          if(ws != null){
        		ws.close();
        		ws = null;
        	}   
            vo.setStrInvPrevVistReasonJSON(jsonolist);
            //System.out.println("jsonolist.toString()INV"+jsonolist.toString());
            return jsonolist.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevDiagnosisDtl()-->", e.getMessage() + "-->" + e);
        	vo.setStrMsgType("1");
            e.printStackTrace();
            return jsonolist.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}
	
	
	public static String getPrevDiagnosisDtl(DoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
        int nProcIndex = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
       
         
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
        
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
            dao = new HisDAO("WebServices", "HospotalMgmtDao.getHospitalMgmtData()");
            nProcIndex = dao.setProcedure(proc_name1);
            dao.setProcInValue(nProcIndex, "modeval", "4",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", "",3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "episodecode", vo.getStrEpisodeCode(),5);
			dao.setProcInValue(nProcIndex, "puk", vo.getStrPuk(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);
            err=dao.getString(nProcIndex, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		if(key.equalsIgnoreCase("HRSTR_JSON_DATA")) //hrstr_json_data
                		{
                			
                			JSONParser parser = new JSONParser();
                    		org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(value);
                    		js.put(key, json);
                		}else {
                			js.put(key, value);
                		}
                		
                		
                		
                		
                		
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              }
          if(ws != null){
        		ws.close();
        		ws = null;
        	}   
            vo.setStrInvPrevDiagnosisJSON(jsonolist);
            //System.out.println("jsonolist.toString()INV"+jsonolist.toString());
            return jsonolist.toString();
        }
        catch (Exception e) {
        	
        	HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevVisitReason()-->", e.getMessage() + "-->" + e);
        	vo.setStrMsgType("1");
            e.printStackTrace();
            return jsonolist.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}

	
	
	public static void getDIAGNOSISDtl(DoctorDeskVO vo) {


		String strproc_name = "{call pkg_opdDrDesk_view.proc_invtest_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.setItemCatValues()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "2",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode()==null ? "0" :vo.getStrDeptCode() ,4);
			dao.setProcInValue(nProcIndex, "Room_No", vo.getStrRoomCode()==null ? "0" : vo.getStrRoomCode(),5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getDIAGNOSISDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDiagnosisWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevDiagnosisDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getDIAGNOSISDtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	
	public static String getPrevVitalforeTeleConsultancy(DoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
        int nProcIndex = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
       
         
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
        
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
            dao = new HisDAO("WebServices", "HospotalMgmtDao.getHospitalMgmtData()");
            nProcIndex = dao.setProcedure(proc_name1);
            dao.setProcInValue(nProcIndex, "modeval", "6",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrVistNo(),3); // Heare Seat Id Visit No
			dao.setProcInValue(nProcIndex, "deptCode", vo.getStreTeleconsultancyReqId(),4);  // here teleconsultancy use as request id
			dao.setProcInValue(nProcIndex, "episodecode", vo.getStrEpisodeCode(),5);
			dao.setProcInValue(nProcIndex, "puk", vo.getStrPuk(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);
            err=dao.getString(nProcIndex, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		js.put(key, value);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              }
          if(ws != null){
        		ws.close();
        		ws = null;
        	}   
            vo.setStrEtelePreVitalJSON(jsonolist);
            //System.out.println("jsonolist.toString()INV"+jsonolist.toString());
            return jsonolist.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevDiagnosisDtl()-->", e.getMessage() + "-->" + e);
        	vo.setStrMsgType("1");
            e.printStackTrace();
            return jsonolist.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}

	public static String getPrevVitalDtls(DoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_opddrdesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
        int nProcIndex = 0;
        HisDAO dao = null;
        WebRowSet ws = null;
       
         
         String status="0";
         JSONObject mainObj = new JSONObject();
         
         JSONArray ja = new JSONArray();
        
         ArrayList<String> columnlist = new ArrayList<String>();
         ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
         int length=0;
        try {
            dao = new HisDAO("WebServices", "HospotalMgmtDao.getHospitalMgmtData()");
            nProcIndex = dao.setProcedure(proc_name1);
            dao.setProcInValue(nProcIndex, "modeval", "5",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrVistNo(),3); // Heare Seat Id Visit No
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "episodecode", vo.getStrEpisodeCode(),5);
			dao.setProcInValue(nProcIndex, "puk", vo.getStrPuk(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);
            err=dao.getString(nProcIndex, "err");
			if(err.equals(""))
			{
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		js.put(key, value);
                		
                	}
                	jsonolist.add(js) ;               	
                	
                }
              }
          if(ws != null){
        		ws.close();
        		ws = null;
        	}   
            vo.setStrPreVitalJSON(jsonolist);
            //System.out.println("jsonolist.toString()INV"+jsonolist.toString());
            return jsonolist.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevDiagnosisDtl()-->", e.getMessage() + "-->" + e);
        	vo.setStrMsgType("1");
            e.printStackTrace();
            return jsonolist.toString();
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
	}

	public static void getDrugProfileDtl(DoctorDeskVO vo) {


		String strproc_name = "{call pkg_opdDrDesk_view.proc_invtest_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.getDrugProfileDtl()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "3",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("") ? "0" :vo.getStrDeptCode().split("#")[1] ,4);
			dao.setProcInValue(nProcIndex, "Room_No", vo.getStrRoomCode()==null ? "0" : vo.getStrRoomCode(),5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getDrugProfileDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDrugProfileWs(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevDiagnosisDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getDrugProfileDtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
		
	}
	
	public static void getTemplateName(DoctorDeskVO vo) {


		String strproc_name = "{call pkg_opdDrDesk_view.proc_template_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.getDrugProfileDtl()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("") ? "0" :vo.getStrDeptCode().split("#")[1] ,4);
			dao.setProcInValue(nProcIndex, "Room_No", vo.getStrRoomCode()==null ? "0" : vo.getStrRoomCode(),5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getDrugProfileDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDrugProfileWs(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","getPrevDataInv.getTemplateName()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getDrugProfileDtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
		
	}
	public static void getTemplateDtl(DoctorDeskVO vo) {
		
		String strproc_name = "{call pkg_opdDrDesk_view.proc_template_dtl(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.setItemCatValues()");
			//System.out.println("llllll::::"+vo.getStrDeptCode()==null || vo.getStrDeptCode().equals("0") ? "0" :vo.getStrDeptCode());
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("") ? "0" :vo.getStrDeptCode().split("#")[1] ,4);
			dao.setProcInValue(nProcIndex, "Room_No", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("")  ? "0" :vo.getStrDeptCode().split("#")[2],5);
			dao.setProcInValue(nProcIndex, "prev_date", vo.getStrPrevDate()==null ||vo.getStrPrevDate().equalsIgnoreCase("")  ? "0" :vo.getStrPrevDate(),6);
			dao.setProcOutValue(nProcIndex, "err", 1,7);
			dao.setProcOutValue(nProcIndex, "resultset", 2,8);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("Get Template Data wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrTemplateWebRowSet(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","getPrevDataInv.getTemplateDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo.setStrMsgString("getTemplateDtl.getTemplateDtl() --> "+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}

	public static void getPresriptionProfileDtl(DoctorDeskVO vo) {


		String strproc_name = "{call pkg_opdDrDesk_view.proc_invtest_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet ws = null;
		JSONObject mainObj = new JSONObject();
        
        JSONArray jsonArray = new JSONArray();
       
        ArrayList<String> columnlist = new ArrayList<String>();
        ArrayList<JSONObject> jsonolist = new ArrayList<JSONObject>();
        int length=0;
       
		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.PODeskGenerateTransDAO.getDrugProfileDtl()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "4",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id", vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("") ? "0" :vo.getStrDeptCode().split("#")[1] ,4);
			dao.setProcInValue(nProcIndex, "Room_No", vo.getStrRoomCode()==null ? "0" : vo.getStrRoomCode(),5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			ws = dao.getWebRowSet(nProcIndex, "resultset");
			System.out.println("getprecription profile wsResult::::::::::::"+ws.size());
			if(strErr.equals(""))
			{
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				 length=	ws.getMetaData().getColumnCount();
				for(int i=1;i<=length;i++)
				{
					columnlist.add(ws.getMetaData().getColumnName(i).toUpperCase());
				}
				
			}
          if (ws != null && ws.size() > 0) {
              while (ws.next()) {
            	  int j=0;
                	//status="1";
                	JSONObject js=new JSONObject();
                	for (int i=1;i<=length;i++)
                	{
                		String key=columnlist.get(i-1);
                		String value=ws.getString(i);
                		//if(value.equalsIgnoreCase("Active")){
                		if(key.equalsIgnoreCase("HJOSN_RX_COMPLETE")){
                		JSONParser parser = new JSONParser();
                		org.json.simple.JSONObject json = (org.json.simple.JSONObject) parser.parse(value);
                		js.put(key, json);
                		}else{
                			js.put(key, value);	
                		}
                		
                		
                	//}
                	}
                	jsonolist.add(js) ;  
                	jsonArray.put(js);
                	
                }
              }
			//System.out.println(jsonolist.toString());
			if (strErr == null || strErr.equals("")){
				vo.setStrPrescriptionProfileJSON(jsonolist);
	 JSONArray jsArray = new JSONArray();			
		for(int j=0 ;  j<jsonArray.length() ;j++){
			if(jsonArray.getJSONObject(j).getString("GNUM_ISVALID").equalsIgnoreCase("Active")){
				//jsonArray.remove(j);
				jsArray.put(jsonArray.get(j));
			}
		}
				vo.setStrPrescriptionProfileJSONArray(jsArray);
				//System.out.println("jsArray"+jsArray);
			//jsonArray.put(jsonolist);
			}
			else{
				throw new Exception(strErr);
			}
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevDiagnosisDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DoctorDeskVO.getDrugProfileDtl() --> "
							+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			ws = null;
		}
		
		
	}
	public static void getExternalHospital(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_hospital_dtl(?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("OPD",
					"DocDeskDao");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcOutValue(nProcIndex, "err", 1,3);
			dao.setProcOutValue(nProcIndex, "resultset", 2,4);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getExternalHospital wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrExternalHospitalweb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getRefferalDeptDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo.setStrMsgString("DoctorDeskVO.getExternalHospital() --> "+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	public static void getExternalInstitute(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_hospital_dtl(?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("OPD",
					"DocDeskDao");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "2",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcOutValue(nProcIndex, "err", 1,3);
			dao.setProcOutValue(nProcIndex, "resultset", 2,4);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getExternalHospital wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrExternalInstituteweb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getRefferalDeptDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo.setStrMsgString("DoctorDeskVO.getExternalHospital() --> "+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	public static void getExternalDepartmentList(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_hospital_dtl(?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("OPD",
					"DocDeskDao");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "3",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcOutValue(nProcIndex, "err", 1,3);
			dao.setProcOutValue(nProcIndex, "resultset", 2,4);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("getExternalHospital wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrExternalDepartmentListweb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getRefferalDeptDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo.setStrMsgString("DoctorDeskVO.getExternalHospital() --> "+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	
	public static void getServiceAreaList(DoctorDeskVO vo) {
		String strproc_name = "{call pkg_opdDrDesk_view.proc_macros_dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.DoctorDeskDAO.getServiceAreaList()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "3",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode",vo.getStrDeptCode()==null ||vo.getStrDeptCode().equalsIgnoreCase("") ? "0" :vo.getStrDeptCode().split("#")[0],4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			//System.out.println("proc_macros_dtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrServiceAreaWebRowSet(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getServiceAreaList()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo.setStrMsgString("DoctorDeskVO.getServiceAreaList() --> "+ _Err.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			util = null;
			wsResult = null;
		}
		
	}
	
//	public static void saveFileData(DoctorDeskVO vo) {}
	public static void saveFileData(DoctorDeskVO vo) {

		String err = "";
    	String proc_name1 = "{call pkg_OpdDesk_dml.hrgt_document_upload_dtl(?,?,?,?,?, ?,?,?,?,?)}";
    	
        int procIndex1 = 0;
        int procIndex2 = 0;
        HisDAO dao = null;
       
        
       
        
        try {
        	
   		
			//System.out.println("JsonData"+JsonData);
			//System.out.println("Diagnosis "+object.get("Diagnosis"));
			//System.out.println("CR_No"+object.get("CR_No"));
			//System.out.println("episodeCode"+object.get("episodeCode"));

   			StringBuffer sb=new StringBuffer();
   			
			dao = new HisDAO("OPD DR DESK DAO", "opdDrDeskDao.save()");
            procIndex1 = dao.setProcedure(proc_name1);
            dao.setProcInValue(procIndex1, "p_mode", "3",1);
            dao.setProcInValue(procIndex1, "p_puk", vo.getStrFileData().split(",")[1],2);
            dao.setProcInValue(procIndex1, "p_hospcode", vo.getStrHospitalCode(),3); 
            dao.setProcInValue(procIndex1, "p_seatId", vo.getStrSeatId(),4); 
            dao.setProcInValue(procIndex1, "p_episodecode",vo.getStrFileData().split(",")[2] ,5); 
            dao.setProcInValue(procIndex1, "p_visitno", "1",6);
            dao.setProcInValue(procIndex1, "p_json", vo.getStrLocation().getFileName().replaceAll(" ", "_"),7);
            dao.setProcInValue(procIndex1, "p_ftpPath", vo.getStrFtpPath(),8);
			dao.setProcInValue(procIndex1, "p_isvalid", vo.getStrDocumenttype(),9);
			
            dao.setProcOutValue(procIndex1, "err", 1,10);
            dao.executeProcedureByPosition(procIndex1);
		   		
		   		//	return "DATA INSERTED SUCCESSFULLY";
		   			vo.setStrMsgType("1");
        }
        catch (Exception e) {
        	vo.setStrMsgType("2");
        	HisException eObj = new HisException("OPD Lite","opdDrDeskDao.SaveEHRData()-->", e.getMessage() + "-->" + e);
            e.printStackTrace();
           // return "Something Went Wrong";
        }
        finally {
        	if (dao != null) {
                dao.free();
                dao = null;
            }
        }
		
		
	}

}
