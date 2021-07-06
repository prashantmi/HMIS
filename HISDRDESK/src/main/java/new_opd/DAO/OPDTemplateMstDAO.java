package new_opd.DAO;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.WebRowSet;

import new_opd.vo.OPDTemplateMstVO;;

public class OPDTemplateMstDAO {
	
	
	public static void getDeptDtl(OPDTemplateMstVO vo) {
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
			dao.setProcInValue(nProcIndex, "modeval", "7",1);
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
			_Err.printStackTrace();
			vo
					.setStrMsgString("OPDTemplateMstVO.getDeptDtl() --> "
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
	
	public static void getTemlateListingData(OPDTemplateMstVO vo) {
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
			dao.setProcInValue(nProcIndex, "modeval", "4",1);
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
				vo.setStrTemalteListingData(wsResult);
			
			}
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			_Err.printStackTrace();
			vo
					.setStrMsgString("OPDTemplateMstVO.getDeptDtl() --> "
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

	public static void gettmpateCatCMB(OPDTemplateMstVO vo) {
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
			dao.setProcInValue(nProcIndex, "modeval", "3",1);
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
				vo.setStrTemplateCatWb(wsResult);
			
			}
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			_Err.printStackTrace();
			vo
					.setStrMsgString("OPDTemplateMstVO.getDeptDtl() --> "
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

	public static void SaveData(OPDTemplateMstVO vo) {
		
		
		int funcIndex;
		HisDAO dao = null;
		String TemplateNo;
		int procIndex3 = 0, procIndex4 = 0;
		String proc_name1 ,proc_name2,strErr;
		try 
		{
			
			dao = new HisDAO("MMS",
					"transactions.OPDTEMPLATEDAO..SaveData()");
			
			proc_name1 = "{call pkg_opddesk_template_dml.sync_pat_vitals_from_oplite(?,?,?,?,? ,?)}";

			procIndex3 = dao.setProcedure(proc_name1);
			
			dao.setProcInValue(procIndex3, "modval", "1", 1);
			dao.setProcInValue(procIndex3, "templateid", vo.getStrTemplateNo(), 2);
			dao.setProcInValue(procIndex3, "seatId", vo.getStrSeatId(), 3);
			dao.setProcInValue(procIndex3, "hosp_code",vo.getStrHospitalCode(), 4);
			dao.setProcInValue(procIndex3, "paraId", vo.getStrJsonParaMetereIdString(), 5);
			dao.setProcOutValue(procIndex3, "errmsg", 1, 6);
		
			//dao.executeProcedureByPosition(procIndex3);
			dao.execute(procIndex3, 1);

			//strErr = dao.getString(procIndex3, "errmsg");
			
			
for (int i = 0; i < vo.getStrDeptCode().length; i++) {
				
				proc_name2 = "{call pkg_opddesk_template_dml.dml_hopl_template_mst(?,?,?,?,? ,?,?,?,? ,?, ?)}";
				procIndex4 = dao.setProcedure(proc_name2);
				
				dao.setProcInValue(procIndex4, "modval", "1", 1);
				dao.setProcInValue(procIndex4, "templateid", vo.getStrTemplateNo(), 2);
				dao.setProcInValue(procIndex4, "seatId", vo.getStrSeatId(), 3);
				dao.setProcInValue(procIndex4, "hosp_code",vo.getStrHospitalCode(), 4);
				dao.setProcInValue(procIndex4, "templateName", vo.getStrTemplateName(), 5);
				dao.setProcInValue(procIndex4, "deptcode", vo.getStrDeptCode()[i].split("#")[0], 6);
				
				dao.setProcInValue(procIndex4, "templateCatCode", vo.getStrTemplateCatCode(), 7);
				dao.setProcInValue(procIndex4, "IsActive", vo.getStrIsActive(), 8);
				dao.setProcInValue(procIndex4, "templateType", vo.getStrTemplateType(), 9);
				dao.setProcInValue(procIndex4, "p_count", String.valueOf(i), 10);
				
				dao.setProcOutValue(procIndex4, "errmsg", 1, 11);
			
				//dao.executeProcedureByPosition(procIndex4);
				dao.execute(procIndex4, 1);
				//strErr = dao.getString(procIndex4, "errmsg");
				
				
			}			
			synchronized (dao) {
				dao.fire();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dao.free();
		}
		
		
		
	}

	public static void getTemplateNo(OPDTemplateMstVO vo) {
	
		int funcIndex;
		HisDAO dao = null;
		String TemplateNo;
		try 
		{
			
			dao = new HisDAO("mms","OPDTemplateMstDAO.getTemplateNo(vo)");
			funcIndex = dao.setFunction("{? = call pkg_opddrdesk_view.generate_templateNo(?)}");

			dao.setFuncInValue(funcIndex, 2,vo.getStrTemplateCatCode());
			dao.setFuncOutValue(funcIndex, 1);
			dao.executeFunction(funcIndex);
			TemplateNo = dao.getFuncString(funcIndex); 
			
			
			System.out.println("Template Number Generation ::::::" + TemplateNo);
			vo.setStrTemplateNo(TemplateNo);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dao=null;
		}
		
		
	}
	public static void GETPARAMETER(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		
		String strproc_name = "{call pkg_opdDrDesk_view.proc_parameter_dtl(?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk","DoctorDeskDAO()");
			dao = new HisDAO("MMS","transactions.DoctorDeskDAO.GETPARAMETER");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "term",vo.getStrReplaceTerm(),3);	
			
			dao.setProcOutValue(nProcIndex, "err", 1,4);
			dao.setProcOutValue(nProcIndex, "resultset", 2,5);
			dao.executeProcedureByPosition(nProcIndex);
			
			
			strErr = dao.getString(nProcIndex, "err");



			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			System.out.println("GETPARAMETER wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
			{
				vo.setStrHiddenParameter(wsResult);;	
			}
			else
				throw new Exception(strErr);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("OPDTemplateMstVO.GETPARAMETER()-->"+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			
		}

	}
	
	public static void GETDRUG(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		
		String strproc_name = "{call pkg_opdDrDesk_view.proc_druglisttemplate_dtl(?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk","DoctorDeskDAO()");
			dao = new HisDAO("MMS","transactions.DoctorDeskDAO.GETPARAMETER");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "term",vo.getStrReplaceTerm(),3);	
			
			dao.setProcOutValue(nProcIndex, "err", 1,4);
			dao.setProcOutValue(nProcIndex, "resultset", 2,5);
			dao.executeProcedureByPosition(nProcIndex);
			
			
			strErr = dao.getString(nProcIndex, "err");



			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			System.out.println("GETPARAMETER wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
			{
				vo.setStrHiddenParameter(wsResult);;	
			}
			else
				throw new Exception(strErr);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("OPDTemplateMstVO.GETPARAMETER()-->"+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			
		}

	}
	
	public static void GETTEST(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		
		String strproc_name = "{call pkg_opdDrDesk_view.proc_testlisttemplate_dtl(?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk","DoctorDeskDAO()");
			dao = new HisDAO("MMS","transactions.DoctorDeskDAO.GETPARAMETER");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "term",vo.getStrReplaceTerm(),3);	
			
			dao.setProcOutValue(nProcIndex, "err", 1,4);
			dao.setProcOutValue(nProcIndex, "resultset", 2,5);
			dao.executeProcedureByPosition(nProcIndex);
			
			
			strErr = dao.getString(nProcIndex, "err");



			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			System.out.println("GETPARAMETER wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
			{
				vo.setStrHiddenParameter(wsResult);;	
			}
			else
				throw new Exception(strErr);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("OPDTemplateMstVO.GETPARAMETER()-->"+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			
		}

	}
	
	
	public static void GETICD(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		
		String strproc_name = "{call pkg_opdDrDesk_view.proc_icd_diseaselist(?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk","DoctorDeskDAO()");
			dao = new HisDAO("MMS","transactions.DoctorDeskDAO.GETPARAMETER");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "term",vo.getStrReplaceTerm(),3);	
			
			dao.setProcOutValue(nProcIndex, "err", 1,4);
			dao.setProcOutValue(nProcIndex, "resultset", 2,5);
			dao.executeProcedureByPosition(nProcIndex);
			
			
			strErr = dao.getString(nProcIndex, "err");



			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			System.out.println("GETPARAMETER wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
			{
				vo.setStrHiddenParameter(wsResult);;	
			}
			else
				throw new Exception(strErr);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("OPDTemplateMstVO.GETPARAMETER()-->"+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			
		}

	}
	
	
	
	public static void GETSITE(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		
		String strproc_name = "{call pkg_opdDrDesk_view.proc_sitemsttemplate_dtl(?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk","DoctorDeskDAO()");
			dao = new HisDAO("MMS","transactions.DoctorDeskDAO.GETPARAMETER");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "term",vo.getStrReplaceTerm(),3);	
			
			dao.setProcOutValue(nProcIndex, "err", 1,4);
			dao.setProcOutValue(nProcIndex, "resultset", 2,5);
			dao.executeProcedureByPosition(nProcIndex);
			
			
			strErr = dao.getString(nProcIndex, "err");



			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			System.out.println("GETPARAMETER wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
			{
				vo.setStrHiddenParameter(wsResult);;	
			}
			else
				throw new Exception(strErr);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("OPDTemplateMstVO.GETPARAMETER()-->"+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
			
		}

	}
	
	
	public static void getAutoCompleteData(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		String strproc_name = "{call pkg_opdDrDesk_view.proc_autoc_dta(?,?,?,?,?)}";
		HisDAO dao = null;
		HisUtil util = null;

		int nProcIndex = 0;

		String strErr = "";
		WebRowSet wsResult = null;

		try {
			util = new HisUtil("OPDDRDRDESk",
					"DoctorDeskDAO()");
			dao = new HisDAO("MMS",
					"transactions.DoctorDeskDAO.getAutoCompleteData()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "1",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcOutValue(nProcIndex, "err", 1,4);
			dao.setProcOutValue(nProcIndex, "resultset", 2,5);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			System.out.println("getDeptDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
			{
				vo.setStrAutoCompleteWs(wsResult);
				
				
			}
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			_Err.printStackTrace();
			vo
					.setStrMsgString("OPDTemplateMstVO.getDeptDtl() --> "
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
	public static void getTempCatDtl(OPDTemplateMstVO vo) {
		// TODO Auto-generated method stub
		//
		
		
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
					"transactions.DoctorDeskDAO.getTempCatDtl()");
			
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "modeval", "3",1);
			dao.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "Room_No", "",5);
			dao.setProcOutValue(nProcIndex, "err", 1,6);
			dao.setProcOutValue(nProcIndex, "resultset", 2,7);
			dao.executeProcedureByPosition(nProcIndex);

			strErr = dao.getString(nProcIndex, "err");

			wsResult = dao.getWebRowSet(nProcIndex, "resultset");
			if (strErr == null || strErr.equals(""))
			{
				vo.setStrTempCatWs(wsResult);
			
			}
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			_Err.printStackTrace();
			vo
					.setStrMsgString("OPDTemplateMstVO.getTempCatDtl() --> "
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
	
public static void getDeleteRecord(OPDTemplateMstVO vo) {
		
		
		int funcIndex;
		HisDAO dao = null;
		String TemplateNo;
		int procIndex3 = 0, procIndex4 = 0;
		String proc_name1 ,proc_name2,strErr;
		try 
		{
			
			dao = new HisDAO("MMS",
					"transactions.OPDTEMPLATEDAO..SaveData()");
			
			proc_name1 = "{call pkg_opddesk_template_dml.sync_pat_vitals_from_oplite(?,?,?,?,? ,?)}";

			procIndex3 = dao.setProcedure(proc_name1);
			
			dao.setProcInValue(procIndex3, "modval", "2", 1);
			dao.setProcInValue(procIndex3, "templateid", vo.getStrTemplateNo(), 2);
			dao.setProcInValue(procIndex3, "seatId", vo.getStrDeptUnitCode(), 3);
			dao.setProcInValue(procIndex3, "hosp_code",vo.getStrHospitalCode(), 4);
			dao.setProcInValue(procIndex3, "paraId", "", 5);
			dao.setProcOutValue(procIndex3, "errmsg", 1, 6);
			dao.execute(procIndex3, 1);

			
			synchronized (dao) {
				dao.fire();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dao.free();
		}
		
		
		
	}


public static void ModifySave(OPDTemplateMstVO vo) {
	
	
	int funcIndex;
	HisDAO dao = null;
	String TemplateNo;
	int procIndex3 = 0, procIndex4 = 0;
	String proc_name1 ,proc_name2,strErr;
	try 
	{
		
		dao = new HisDAO("MMS",
				"transactions.OPDTEMPLATEDAO..SaveData()");
		
		proc_name1 = "{call pkg_opddesk_template_dml.sync_pat_vitals_from_oplite(?,?,?,?,? ,?)}";

		procIndex3 = dao.setProcedure(proc_name1);
		
		dao.setProcInValue(procIndex3, "modval", "3", 1);
		dao.setProcInValue(procIndex3, "templateid", vo.getStrTemplateNo(), 2);
		dao.setProcInValue(procIndex3, "seatId", vo.getStrSeatId(), 3);
		dao.setProcInValue(procIndex3, "hosp_code",vo.getStrHospitalCode(), 4);
		dao.setProcInValue(procIndex3, "paraId", vo.getStrJsonParaMetereIdString(), 5);
		dao.setProcOutValue(procIndex3, "errmsg", 1, 6);
	
		//dao.executeProcedureByPosition(procIndex3);
		dao.execute(procIndex3, 1);

		//strErr = dao.getString(procIndex3, "errmsg");
		
		
for (int i = 0; i < vo.getStrDeptCode().length; i++) {
			
			proc_name2 = "{call pkg_opddesk_template_dml.dml_hopl_template_mst(?,?,?,?,? ,?,?,?,? ,?,?)}";
			procIndex4 = dao.setProcedure(proc_name2);
			
			dao.setProcInValue(procIndex4, "modval", "2", 1);
			dao.setProcInValue(procIndex4, "templateid", vo.getStrTemplateNo(), 2);
			dao.setProcInValue(procIndex4, "seatId", vo.getStrSeatId(), 3);
			dao.setProcInValue(procIndex4, "hosp_code",vo.getStrHospitalCode(), 4);
			dao.setProcInValue(procIndex4, "templateName", vo.getStrTemplateName(), 5);
			dao.setProcInValue(procIndex4, "deptcode", vo.getStrDeptCode()[i].split("#")[0], 6);
			
			dao.setProcInValue(procIndex4, "templateCatCode", vo.getStrTemplateCatCode(), 7);
			dao.setProcInValue(procIndex4, "IsActive", vo.getStrIsActive(), 8);
			dao.setProcInValue(procIndex4, "templateType", vo.getStrTemplateType(), 9);
			dao.setProcInValue(procIndex4, "p_count", String.valueOf(i), 10);
			
			dao.setProcOutValue(procIndex4, "errmsg", 1, 11);
		
			//dao.executeProcedureByPosition(procIndex4);
			dao.execute(procIndex4, 1);
			//strErr = dao.getString(procIndex4, "errmsg");
			
			
		}			
		synchronized (dao) {
			dao.fire();
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		dao.free();
	}
	
	
	
}
}
