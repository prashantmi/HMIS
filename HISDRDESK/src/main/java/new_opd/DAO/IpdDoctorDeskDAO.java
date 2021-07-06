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

import new_opd.vo.IpdDoctorDeskVO;

public class IpdDoctorDeskDAO {

	public static void getInitilaData(IpdDoctorDeskVO vo) {
		System.out.println("method called ipd getInitilaData----->");
		
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_patient_dtl(?,?,?,?,?,?,?,?)}";
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
			System.out.println("getInitilaData wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrInitialWb(wsResult);
			else
				throw new Exception(strErr);
			System.out.println("Webrowset---->"+wsResult);
		} catch (Exception _Err) {
			HisException eObj = new HisException("IPD Lite","DoctorDeskDAO.getInitilaData()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getInitilaData() --> "
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

public static void getpatientTeleConsultancyData(IpdDoctorDeskVO vo) {
		
		String strproc_name = "{call pkg_ipdDrDesk_view.Proc_patient_dtl(?,?,?,?,?,?,?,?)}";
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
			System.out.println("getpatientTeleConsultancyData wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrTeleConsultancyDataWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getInitilaData()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getInitilaData() --> "
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

	public static void getInvestogation(IpdDoctorDeskVO vo) {
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_invtest_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getInvestogation wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrTestWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getInvestogation()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getInvestogation() --> "
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
	
	public static void getdrugdtl(IpdDoctorDeskVO vo) {
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_drug_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getdrugdtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDrugWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getdrugdtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getdrugdtl() --> "
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

	public static void getdrug_dosage_dtl(IpdDoctorDeskVO vo) {
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_dosage_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getdrug_dosage_dtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDosageWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getdrug_dosage_dtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getdrug_dosage_dtl() --> "
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
	
	
	public static void getMacrosDtl(IpdDoctorDeskVO vo) {
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_macros_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getMacrosDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrMacrosWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getMacrosDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getMacrosDtl() --> "
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
	
	public static void getClinicalProcedureDtls(IpdDoctorDeskVO vo) {
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_macros_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getMacrosDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrCinicalProcudreWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getClinicalProcedureDtls()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getMacrosDtl() --> "
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
	
	public static void getDeptDtl(IpdDoctorDeskVO vo,HttpServletRequest request) {
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_dept_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getDeptDtl wsResult::::::::::::"+wsResult.size());
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
					.setStrMsgString("IpdDoctorDeskVO.getDeptDtl() --> "
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
	public static void getHospitalHeaderName(IpdDoctorDeskVO vo) {
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_dept_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getDeptDtl wsResult::::::::::::"+wsResult.size());
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
					.setStrMsgString("IpdDoctorDeskVO.getDeptDtl() --> "
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

	public static void getRefferalDeptDtl(IpdDoctorDeskVO vo) {
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_dept_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getDeptDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrRefferalDeptWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getRefferalDeptDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getDeptDtl() --> "
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
	public static void getBookMarksTestDtl(IpdDoctorDeskVO vo) {
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_Bookmarks_test_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getBookMarksTestDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrBookMarksTestWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DoctorDeskDAO.getBookMarksTestDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getBookMarksTestDtl() --> "
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

	public static String getPrevData(IpdDoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_ipdDrDesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
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
	public static String getPrevvisitresonData(IpdDoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_ipdDrDesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
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
	
	public static String getPrevDataInv(IpdDoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_ipdDrDesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
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
	
	public static String getPrevVisitReason(IpdDoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_ipdDrDesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
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
	
	
	public static String getPrevDiagnosisDtl(IpdDoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_ipdDrDesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
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

	
	
	public static void getDIAGNOSISDtl(IpdDoctorDeskVO vo) {


		String strproc_name = "{call pkg_ipdDrDesk_view.proc_invtest_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getDIAGNOSISDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDiagnosisWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevDiagnosisDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getDIAGNOSISDtl() --> "
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
	
	public static String getPrevVitalforeTeleConsultancy(IpdDoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_ipdDrDesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
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

	public static String getPrevVitalDtls(IpdDoctorDeskVO vo) {
		String err = "";
    	String proc_name1 = "{call pkg_ipdDrDesk_view.proc_prev_dtl(?,?,?,?, ?,?,?,?)}";
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

	public static void getDrugProfileDtl(IpdDoctorDeskVO vo) {


		String strproc_name = "{call pkg_ipdDrDesk_view.proc_invtest_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getDrugProfileDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDrugProfileWs(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevDiagnosisDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getDrugProfileDtl() --> "
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
	
	public static void getTemplateName(IpdDoctorDeskVO vo) {


		String strproc_name = "{call pkg_ipdDrDesk_view.proc_template_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println("getDrugProfileDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDrugProfileWs(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","getPrevDataInv.getTemplateName()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getDrugProfileDtl() --> "
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
	public static void getTemplateDtl(IpdDoctorDeskVO vo) {
		
		String strproc_name = "{call pkg_ipdDrDesk_view.proc_template_dtl(?,?,?,?,?,?,?,?)}";
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
			System.out.println("Get Template Data wsResult::::::::::::"+wsResult.size());
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

	public static void getPresriptionProfileDtl(IpdDoctorDeskVO vo) {


		String strproc_name = "{call pkg_ipdDrDesk_view.proc_invtest_dtl(?,?,?,?,?,?,?)}";
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
			System.out.println(jsonolist.toString());
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
				System.out.println("jsArray"+jsArray);
			//jsonArray.put(jsonolist);
			}
			else{
				throw new Exception(strErr);
			}
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","getPrevDataInv.getPrevDiagnosisDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("IpdDoctorDeskVO.getDrugProfileDtl() --> "
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

}
