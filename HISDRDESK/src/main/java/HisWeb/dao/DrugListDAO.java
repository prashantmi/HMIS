package HisWeb.dao;

import java.util.ArrayList;

import hisglobal.exceptions.HisException;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.sql.rowset.WebRowSet;

import HisWeb.vo.DrugListVO;

public class DrugListDAO  {

	public static void getInitilaData(DrugListVO vo) {
		
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
			System.out.println("llllll::::"+vo.getStrDeptCode()==null || vo.getStrDeptCode().equals("0") ? "0" :vo.getStrDeptCode());
			nProcIndex = dao.setProcedure(strproc_name);
			dao.setProcInValue(nProcIndex, "mod	eval", "1",1);
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
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DrugListDAO.getInitilaData()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DrugListVO.getInitilaData() --> "
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

	public static void getInvestogation(DrugListVO vo) {
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
			System.out.println("getInvestogation wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrTestWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DrugListDAO.getInvestogation()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DrugListVO.getInvestogation() --> "
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
	
	public static void getdrugdtl(DrugListVO vo) {
		String strproc_name = "{call pkg_mms_view.proc_drug_dtl(?,?,?,?,?,?,?)}";
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
			dao.setProcInValue(nProcIndex, "seat_id",  vo.getStrSeatId()==null ? "0" :vo.getStrSeatId(),3);
			dao.setProcInValue(nProcIndex, "deptCode", "",4);
			dao.setProcInValue(nProcIndex, "store_id", vo.getStrStoreId(),5);
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
			HisException eObj = new HisException("OPD Lite","DrugListDAO.getdrugdtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DrugListVO.getdrugdtl() --> "
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

	public static void getdrug_dosage_dtl(DrugListVO vo) {
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
			System.out.println("getdrug_dosage_dtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDosageWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DrugListDAO.getdrug_dosage_dtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DrugListVO.getdrug_dosage_dtl() --> "
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
	
	
	public static void getMacrosDtl(DrugListVO vo) {
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
			System.out.println("getMacrosDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrMacrosWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DrugListDAO.getMacrosDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DrugListVO.getMacrosDtl() --> "
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
	
	public static void getDeptDtl(DrugListVO vo) {
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
			System.out.println("getDeptDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDeptWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DrugListDAO.getDeptDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DrugListVO.getDeptDtl() --> "
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
	public static void getBookMarksTestDtl(DrugListVO vo) {
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
			System.out.println("getBookMarksTestDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrBookMarksTestWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DrugListDAO.getBookMarksTestDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DrugListVO.getBookMarksTestDtl() --> "
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

	public static String getPrevData(DrugListVO vo) {
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
        	HisException eObj = new HisException("OPD Lite","DrugListDAO.getPrevData()-->", e.getMessage() + "-->" + e);
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
	
	public static String getPrevDataInv(DrugListVO vo) {
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
        	HisException eObj = new HisException("OPD Lite","DrugListDAO.getPrevDataInv()-->", e.getMessage() + "-->" + e);
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
	
	public static String getPrevVisitReason(DrugListVO vo) {
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
            vo.setStrInvPrevVistReasonJSON(jsonolist);
            //System.out.println("jsonolist.toString()INV"+jsonolist.toString());
            return jsonolist.toString();
        }
        catch (Exception e) {
        	HisException eObj = new HisException("OPD Lite","DrugListDAO.getPrevVisitReason()-->", e.getMessage() + "-->" + e);
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
	
	
	public static String getPrevDiagnosisDtl(DrugListVO vo) {
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
                		js.put(key, value);
                		
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
        	HisException eObj = new HisException("OPD Lite","DrugListDAO.getPrevDiagnosisDtl()-->", e.getMessage() + "-->" + e);
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

	public static void getDIAGNOSISDtl(DrugListVO vo) {


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
			System.out.println("getDIAGNOSISDtl wsResult::::::::::::"+wsResult.size());
			if (strErr == null || strErr.equals(""))
				vo.setStrDiagnosisWb(wsResult);
			else
				throw new Exception(strErr);
		} catch (Exception _Err) {
			HisException eObj = new HisException("OPD Lite","DrugListDAO.getDIAGNOSISDtl()-->", _Err.getMessage() + "-->" + _Err);
			_Err.printStackTrace();
			vo
					.setStrMsgString("DrugListVO.getDIAGNOSISDtl() --> "
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
}
