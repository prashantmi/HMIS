package ipd.transactions;


import ipd.IpdConfigUtil;
import ipd.setup.IPDConfig;

import java.util.ResourceBundle;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.PrintBO;
import billing.PrintHLP;
import billing.PrintVO;
import billing.dao.PrimaryKeyDAO;
import billing.transactions.CashCollectionOfflineTransDAO;
import billing.transactions.CashCollectionOfflineTransVO;
import hisglobal.utility.GetNetworkAddress;


public class PatientAdmissionTransDAO {
	private static ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
	public static String admitted = hisProp.getString("Admitted");
	
	/**
	 * This function set ward type values in ward type combo on bed details pop up window
	 * @param vo
	 */
	public static void setWardTypeDtl(PatientAdmissionTransVO vo) {
		
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.PROC_HIPT_WARDTYPE_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		//WebRowSet web=null; 
		
		try {
				daoObj = new HisDAO("Admission","PatientAdmissionTransDAO");
                nProcIndex = daoObj.setProcedure(strProcName);
			    daoObj.setProcInValue(nProcIndex, "modeVal","1",1);
			    daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),2);
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
			vo.setStrMsgString("PatientAdmissionTransDAO.setWardTypeDtl() --> "
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
	 * This function is used to details in room type combo on bed status pop up window
	 * @param vo
	 */
	public static void setRoomTypeDtl(PatientAdmissionTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomtype_a(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		try {
				daoObj = new HisDAO("Patient Admission Transaction",
					"PatientAdmissionTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","1",1);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),2);
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

			vo.setStrMsgString("PatientAdmissionTransDAO.setRoomTypeDtl() --> "
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
				daoObj = new HisDAO("Patient Admission Transaction",
					"PatientAdmissionTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "pukno",vo.getStrCrNo(),1);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),2);
				daoObj.setProcOutValue(nProcIndex, "err", 1,3);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					if(ws.next())
					{
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
			
			vo.setStrMsgString("PatientAdmissionTransDAO.getBedStatusDtl() --> "
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
	/**
	 * This function is used to bring ward details in ward combo on bed details pop window
	 * @param vo
	 */
	public static void getWardValues(PatientAdmissionTransVO vo)
	{
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
			
			daoObj.setProcInValue(nProcIndex, "modeVal","14",1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode",vo.getStrWardTypeCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptcode",vo.getStrDeptCode(),3);
			daoObj.setProcInValue(nProcIndex, "deptunitcode",vo.getStrDeptUnitCode()==null?"0":vo.getStrDeptUnitCode(),4);
			daoObj.setProcInValue(nProcIndex, "unitcode",vo.getStrAgeUnit(),5);
			daoObj.setProcInValue(nProcIndex, "age",vo.getStrAge(),6);
			daoObj.setProcInValue(nProcIndex, "gender",vo.getStrSexCode(),7);
			daoObj.setProcInValue(nProcIndex, "treatment_cat",vo.getStrTreatmentCategoryCode(),8);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),9);
			daoObj.setProcInValue(nProcIndex, "effect_from","0",10);
			daoObj.setProcInValue(nProcIndex, "effect_to","0",11);
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
			if (strErr.equals("")) {
				vo.setWardWS(ws);
			} else {
					throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.getWardValues() --> "
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
	
	/**
	 * This function is used to bring ward details in ward combo on bed details pop window
	 * @param vo
	 */
	public static void getWardValuesBedStatus(PatientAdmissionTransVO vo)
	{
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
			
			daoObj.setProcInValue(nProcIndex, "modeVal","16",1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode",vo.getStrWardTypeCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptcode",vo.getStrDeptCode(),3);
			daoObj.setProcInValue(nProcIndex, "deptunitcode",vo.getStrDeptUnitCode()==null?"0":vo.getStrDeptUnitCode(),4);
			daoObj.setProcInValue(nProcIndex, "unitcode",vo.getStrAgeUnit(),5);
			daoObj.setProcInValue(nProcIndex, "age",vo.getStrAge(),6);
			daoObj.setProcInValue(nProcIndex, "gender",vo.getStrSexCode(),7);
			daoObj.setProcInValue(nProcIndex, "treatment_cat",vo.getStrTreatmentCategoryCode(),8);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),9);
			daoObj.setProcInValue(nProcIndex, "effect_from","0",10);
			daoObj.setProcInValue(nProcIndex, "effect_to","0",11);
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
			if (strErr.equals("")) {
				vo.setWardWS(ws);
			} else {
					throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.getWardValues() --> "
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
	
	//ward combo based on dept and unit
	public static void getWardName(PatientAdmissionTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		daoObj = new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO.getWardName()");
		try
		{
			
			daoObj = new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO");
					
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modeVal","14",1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode",vo.getStrWardTypeCode(),2);
			if(vo.getStrDeptCode().equals(""))
				daoObj.setProcInValue(nProcIndex, "deptcode","0",3);
			else
			    daoObj.setProcInValue(nProcIndex, "deptcode",vo.getStrDeptCode(),3);
			if(vo.getStrDeptUnitCode().equals(""))
				daoObj.setProcInValue(nProcIndex, "deptunitcode","0",4);
			else
			    daoObj.setProcInValue(nProcIndex, "deptunitcode",vo.getStrDeptUnitCode()==null?"0":vo.getStrDeptUnitCode(),4);
			daoObj.setProcInValue(nProcIndex, "unitcode",vo.getStrAgeUnit(),5);
			daoObj.setProcInValue(nProcIndex, "age",vo.getStrAge(),6);
			daoObj.setProcInValue(nProcIndex, "gender",vo.getStrSexCode(),7);
			if(vo.getStrTreatmentCategoryCode().equals(""))
				daoObj.setProcInValue(nProcIndex, "treatment_cat","0",8);
			else
			    daoObj.setProcInValue(nProcIndex, "treatment_cat",vo.getStrTreatmentCategoryCode(),8);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),9);
			daoObj.setProcInValue(nProcIndex, "effect_from","0",10);
			daoObj.setProcInValue(nProcIndex, "effect_to","0",11);
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
			if (strErr.equals("")) {
				vo.setWardWS(ws);
			} else {
					throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.getWardValues() --> "
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
	/**
	 * This function is used to bring room details in room combo on the basis of room type code
	 * @param vo
	 */
	public static void getRoomValues(PatientAdmissionTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

	daoObj = new HisDAO("Patient Admission Transaction",
				"PatientAdmissionTransDAO.getRoomValues()");
		try
		{
				/*System.out.println("vo.getStrRoomTypeCode()"+vo.getStrRoomTypeCode());
				System.out.println("vo.getStrWardCode()"+vo.getStrWardCode());*/
				
				daoObj = new HisDAO("Patient Admission Transaction",
				"PatientAdmissionTransDAO");
				
			
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","3",1);
				daoObj.setProcInValue(nProcIndex, "roomtypcode",vo.getStrRoomTypeCode(),2);
				daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),3);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),4);
				daoObj.setProcInValue(nProcIndex, "unitcode",vo.getStrAgeUnit(),5);
				daoObj.setProcInValue(nProcIndex, "age",vo.getStrAge(),6);
				daoObj.setProcInValue(nProcIndex, "deptcode",vo.getStrDeptCode(),7);
				daoObj.setProcInValue(nProcIndex, "deptunitcode",vo.getStrDeptUnitCode()==null?"0":vo.getStrDeptUnitCode(),8);
				daoObj.setProcInValue(nProcIndex, "gender",vo.getStrSexCode(),9);
				daoObj.setProcInValue(nProcIndex, "treatment_cat",vo.getStrTreatmentCategoryCode(),10);
				daoObj.setProcInValue(nProcIndex, "puk_no",vo.getStrCrNo(),11);
				daoObj.setProcInValue(nProcIndex, "diseasetypcode","0",12);
				daoObj.setProcOutValue(nProcIndex, "err", 1,13);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,14);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				
				
				/***
				 * 
				 */
				
				
				/*nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","1");
				daoObj.setProcInValue(nProcIndex, "roomtypcode",vo.getStrRoomTypeCode());
				daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode());
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode());
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);
				daoObj.executeProcedure(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");*/
				if (strErr == null)
					strErr = "";
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					
					
				if (strErr.equals("")) {
					//System.out.println("size"+ws.size());
					vo.setRoomWs(ws);
				} else {
					throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.getRoomValues() --> "
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
	/**
	 * This function is used to bring details in bed type combo in bed details pop up window
	 * @param voObj
	 */
	public static void setBedTypeDtl(PatientAdmissionTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_bed_type_mst(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Admission Transaction",
					"PatientAdmissionTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex,"modval","1",1);
			daoObj.setProcInValue(nProcIndex,"hosp_code",voObj.getStrHospCode(),2);
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

			voObj.setStrMsgString("PatientAdmissionTransDAO.setBedTypeDtl() --> "
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
	 * This function is used to bring the bed details on bed details pop up window on the basis of ward code,room number,bed type code
	 * @param vo
	 */
	public static void getBedValues(PatientAdmissionTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(vo.getStrHospCode());

		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";


		daoObj = new HisDAO("New Born Baby Admission Transaction",
		"PatientAdmissionTransDAO");
		try
		{
			
			daoObj = new HisDAO("New Born Baby Admission  Transaction","NewBornBabyTransDAO");
        nProcIndex = daoObj.setProcedure(strProcName);
        if(ipdConfigUtil.getStrUnitNameReq().equals("1"))
		daoObj.setProcInValue(nProcIndex, "modval","1",1);
        else
        	daoObj.setProcInValue(nProcIndex, "modval","9",1);
	    daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),2);
	    daoObj.setProcInValue(nProcIndex, "roomno",vo.getStrRoom(),3);
	    daoObj.setProcInValue(nProcIndex, "bedtypcode",vo.getStrBedTypeCode(),4);
	    daoObj.setProcInValue(nProcIndex, "bedstatcode","",5);
	    daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),6);
	    if(ipdConfigUtil.getStrUnitNameReq().equals("1"))
	    	daoObj.setProcInValue(nProcIndex, "deptunit",vo.getStrDeptUnitCode(),7);
	    else
	    	 daoObj.setProcInValue(nProcIndex, "deptunit",vo.getStrDeptCode(),7);
	    
	   
	    daoObj.setProcInValue(nProcIndex, "bedCode","",8);
	    daoObj.setProcInValue(nProcIndex, "shr_chk", "",9);
	    daoObj.setProcOutValue(nProcIndex, "err", 1,10);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
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
		}

		catch(Exception e)
		{
			
			vo.setStrMsgString("PatientAdmissionTransDAO.getBedValues() --> "
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
	/**
	 * This function is used to count the number of vacant beds in a ward on the basis of ward code,room code and bed type code 
	 * @param vo
	 */
	public static void countBed_in_ward(PatientAdmissionTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		daoObj = new HisDAO("New Born Baby Admission Transaction",
		"PatientAdmissionTransDAO");
		try
		{
			daoObj = new HisDAO("Patient Admission  Transaction",
			"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
        	daoObj.setProcInValue(nProcIndex, "modval","2",1);
        	daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),2);
        	daoObj.setProcInValue(nProcIndex, "roomno",vo.getStrRoom(),3);
        	daoObj.setProcInValue(nProcIndex, "bedtypcode",vo.getStrBedTypeCode(),4);
        	daoObj.setProcInValue(nProcIndex, "bedstatcode","10",5);
        	daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),6);
        	daoObj.setProcInValue(nProcIndex, "deptunit",vo.getStrDeptUnitCode(),7);
        	daoObj.setProcInValue(nProcIndex, "bedCode","",8);
        	daoObj.setProcInValue(nProcIndex, "shr_chk", "",9);
        	daoObj.setProcOutValue(nProcIndex, "err", 1,10);
        	daoObj.setProcOutValue(nProcIndex, "resultset", 2,11);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
					strErr = "";
		    ws = daoObj.getWebRowSet(nProcIndex, "resultset");
            if (strErr.equals("")) {
           	if(ws.next())
        	{
           		vo.setStrVacantBed(ws.getString(1));
        	}
        		
		} else {
			throw new Exception(strErr);
		}
	

		}
		catch(Exception e)
		{
			
			vo.setStrMsgString("PatientAdmissionTransDAO.countBed_in_ward() --> "
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
	/**
	 * This function is used to set treatment category name and treatment category on the main page 
	 * @param vo
	 */
	public static void setTreat_CatName(PatientAdmissionTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_ipd_treatment_cat_name(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Patient Admission",
					"PatientAdmissionTransDAO");
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
									vo.setStrTreatmentCategoryName(ws.getString(1));
									vo.setStrTreatmentCategoryCode(ws.getString(2));
									}
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientAdmissionTransDAO.setTreat_CatName() --> "
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
	 * This function is used to set patient details in hidden fields on the main page
	 * @param vo
	 */
	public static void setPatientStatus(PatientAdmissionTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_pat_status(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		
		try 
		{
				daoObj = new HisDAO("ADT","PatientAdmissionTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) 
				{
					if (ws.next()) 
					{						
						vo.setStrPatCatCode(ws.getString(1));
						vo.setStrIsUrban(ws.getString(2));
						vo.setStrPatStatusCode(ws.getString(3));
						vo.setStrPrimaryCategoryCode(ws.getString(4));
						vo.setStrIsDead(ws.getString(6));
						vo.setStrActiveWallet(ws.getString(8));
						vo.setStrDormantAcc(ws.getString(9));
					}
				} 
				else 
				{
					throw new Exception(strErr);
				}
		} 
		catch (Exception e) 
		{			
			vo.setStrMsgString("PatientAdmissionTransDAO.setPatientStatus() --> "+ e.getMessage());
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
	 * This function is used to display consultant name on main page who give advice to patient
	 * @param vo
	 */
	public static void setConsultantName(PatientAdmissionTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_consultant(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		String strDeptUnitCode=vo.getStrDeptUnitCode();
		try {
				daoObj = new HisDAO("Patient Admission",
					"PatientAdmissionTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "PUKNO", strCrNum,1);
				daoObj.setProcInValue(nProcIndex, "DEPTUNIT", strDeptUnitCode,2);
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
			vo.setStrMsgString("PatientAdmissionTransDAO.setConsultantName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static boolean insert(PatientAdmissionTransVO vo)
	{		
		boolean retVal2=false;
		HisDAO daoObj = null;
		IpdConfigUtil config=new IpdConfigUtil(vo.getStrHospCode());
		PrimaryKeyDAO pKey = new PrimaryKeyDAO();
	//	PrintBO boObj =null;
	//	PrintVO pVO =null;
		try
		{
			daoObj= new HisDAO("ADT","PatientAdmissionTransDAO");
		//	boObj = new PrintBO();
		////	pVO = new PrintVO();
			//synchronized (daoObj) 
			
			
			System.out.println("INSERT CALLED----------------------------------------------->");
			//{		
					if(vo.getStrIsSingleWindowAdmission().equals("1"))//Single Window Admission Is Batch Execution DAO of Admission And Billing
					{
						pKey.setStrBlockkey("1");
						pKey.setStrKeyname("ADM_NO");
						pKey.setStrHospCode(vo.getStrHospCode());
						pKey.select(daoObj);
						
						vo.setStrAdmNo(pKey.getStrPrimrayKeyValue());
						
						System.out.println("insertSingleWindowPatAdmission----------------------------------------------->");
						 
						retVal2=PatientAdmissionTransDAO.insertSingleWindowPatAdmission(daoObj, vo);
						
						if(config.getStrAdvanceDepsoitAtAdmissionCounter().equals("1"))
						{
							// billing - advance receipt 
						 	
							CashCollectionOfflineTransVO voObj = new CashCollectionOfflineTransVO();						
							
							voObj.setStrHospitalCode(vo.getStrHospCode());
							voObj.setStrSeatId(vo.getStrSeatId());
							
							voObj.setStrModuleId(BillConfigUtil.BILL_MODULE_ID);
							voObj.setStrGroupId(BillConfigUtil.GROUP_ID_DEPOSIT);
							voObj.setStrQtyUnitId(BillConfigUtil.DEFAULT_UNIT_ID);
							voObj.setStrOffLineAccountNo("0");
							voObj.setStrOffLineEpisode(vo.getStrEpisodeCode());
							voObj.setStrOffLineTreatmentCategory(vo.getStrTreatmentCategoryCode());
							voObj.setStrOffLineHospitalService("2"); // ipd
							voObj.setStrOffLineRaisingDepartment(vo.getStrDeptCode());
							voObj.setStrCrNo(vo.getStrCrNo());
							voObj.setStrOfflineTotalRecAmount(vo.getStrAdvanceAmountVal());
							voObj.setStrOffLineAdmissionNo(vo.getStrAdmNo());
							voObj.setStrOffLineBillingService("19");  // ipd advance 
							
							voObj.setStrOffLineSpecialWard(vo.getStrWardCode().replace("$", "#").split("#")[0]);
							voObj.setStrOffLineWard(vo.getStrWardTypeCode().replace("$", "#").split("#")[0]);
							
							voObj.setStrOffLineRequestType("1"); // receipt 
							
							voObj.setStrCreditBillApprovalDone("0");
							
							/*voObj.setStrOfflinePaymentMode(vo.getStrOfflinePaymentMode());
							voObj.setStrOfflinePaymentDtls(vo.getStrOfflinePaymentDtls());
							voObj.setStrOfflineAmount(vo.getStrOfflineAmount());*/
							
							String[] payMode={vo.getStrPaymentModeValue()};
							String[] payModeDtl={vo.getStrPayDetail()};
							
							//String[] payMode={"1"};
							//String[] payModeDtl={""};
							String[] advAmt={vo.getStrAdvanceAmountVal()};
							voObj.setStrOfflinePaymentMode(payMode);//Cash
							voObj.setStrOfflinePaymentDtls(payModeDtl);
							voObj.setStrOfflineAmount(advAmt);
							
							 		
							
							
							/*String billNoAndAccNo = CashCollectionOfflineTransDAO.insertOfflineAdvanceForAdmission(voObj, daoObj);
							
							CashCollectionOfflineTransVO billVO=new CashCollectionOfflineTransVO();*/
							
							
							System.out.println("insertOfflineAdvance----------------------------------------------->");
							CashCollectionOfflineTransDAO.insertOfflineAdvance(voObj,daoObj);
							
							vo.setStrBillNo(voObj.getStrBillNo());
						}
							System.out.println("insertIpdViaAdmission----------------------------------------------->");
							if((!vo.getStrBedCode().equals("")) && (!vo.getStrBedCode().equals(null)) && (!vo.getStrBedCode().equals("0")))				
								insertIpdViaAdmission(vo,daoObj,false);
							synchronized (daoObj)
							{
								daoObj.fire();
							}
						
						System.out.println("END----------------------------------------------->");
					}
					else
					{
						
						System.out.println("insertPatAdmission   ELSE----------------------------------------------->");
						retVal2=PatientAdmissionTransDAO.insertPatAdmission(daoObj, vo);
					}
			//}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.insert() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
		finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}

			if (pKey != null)
				pKey = null;			
		}
		return retVal2;
	}
	/**
	 * This function is used to insert record into database
	 * @param daoObj
	 * @param vo
	 * @return
	 */
	public static boolean insertPatAdmission(HisDAO daoObj,PatientAdmissionTransVO vo)
	{
		String strProcName1 ="{call pkg_ipd_dml.proc_patient_adm(?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?,?,?,?)}";	//100 variable
		String strProcName4 ="{call pkg_ipd_dml.proc_ip_mac(?,?,?,?,?,?,?,?)}";
		String err=new String();
		String admno="";
		int nProcIndex1 = 0;
		int nProcIndex2 = 0;
		String strAddress=vo.getStrHouseNo()+","+vo.getStrStreet()+","+vo.getStrCity();
		vo.setStrAddress(strAddress);
		String strHouseNo=vo.getStrHouseNo();
		String strStreet=vo.getStrStreet();
		IpdConfigUtil ipdConfig=null;
		boolean chkFlag=false;
		try
		{
			ipdConfig=new IpdConfigUtil(vo.getStrHospCode());			
			//System.out.println("max is "+ipdConfig.getStrMaxNoOfBabyMotherCanBorn());
			if(vo.getStrMsApprovalFlag().equals("6"))///To Bec checked
			{
				nProcIndex1 = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex1, "modval", "1",1);
				daoObj.setProcInValue(nProcIndex1, "hipnum_admno", "",2);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_puk", vo.getStrCrNo(),3);
				daoObj.setProcInValue(nProcIndex1, "hipnum_adm_advno", vo.getStrAdviceAdmNo(),4);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_episode_code", vo.getStrEpisodeCode(),5);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_visit_no", vo.getStrVisitNo(),6);
				daoObj.setProcInValue(nProcIndex1, "hipdt_admdatetime", "",7);
				daoObj.setProcInValue(nProcIndex1, "hipdt_bedalloc_datetime", "",8);
				daoObj.setProcInValue(nProcIndex1, "hipnum_lengthofstay", "",9);			
				daoObj.setProcInValue(nProcIndex1, "gnum_dept_code", vo.getStrDeptCode(),10);
				//daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code", vo.getStrDeptUnitCode(),11);
				if(vo.getStrIsAdmissionOnline().equals("1"))//Advice Found
					daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code",vo.getStrDeptUnitCode(),11);
				else
					daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code",vo.getStrConsultantCode().replace("$","#").split("#")[1],11);
				
				daoObj.setProcInValue(nProcIndex1, "hipnum_ward_code", vo.getStrWardCode().replace("$","#").split("#")[0],12);
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_code", vo.getStrRoomCode(),13);
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_code", vo.getStrBedCode(),14);
				daoObj.setProcInValue(nProcIndex1, "gnum_owndept_code", vo.getStrDeptCode(),15);
				//daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrDeptUnitCode(),16);
				if(vo.getStrIsAdmissionOnline().equals("1"))//Advice Found
					daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrDeptUnitCode(),16);
				else
					daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrConsultantCode().replace("$","#").split("#")[1],16);
				
				daoObj.setProcInValue(nProcIndex1, "hipnum_ownward_code",vo.getStrWardCode().replace("$","#").split("#")[0],17);				
				/*if(vo.getStrTreatmentCategoryCode().trim().equals("0"))
				{
					daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrPrimaryCategoryCode(),18);
				}
				else
				{
					daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrTreatmentCategoryCode(),18);
				}*/
				
		
				daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrTreatmentCategoryCode(),18);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isnewborn", vo.getStrNewBorn(),19);
				daoObj.setProcInValue(nProcIndex1, "hipnum_mothadmno","",20);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mlc_no", vo.getStrMlcNo(),21);
				daoObj.setProcInValue(nProcIndex1, "hipnum_consultant_id", vo.getStrConsultantCode().replace("$","#").split("#")[0],22);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isrural", vo.getStrIsUrban(),23);
				daoObj.setProcInValue(nProcIndex1, "hipstr_remarks", vo.getStrRemarks(),24);
				daoObj.setProcInValue(nProcIndex1, "hipnum_occup_id","",25);	
				daoObj.setProcInValue(nProcIndex1, "gnum_relation_code",vo.getStrOccRelation(),26 );
				daoObj.setProcInValue(nProcIndex1, "gdt_entry_date","",27);
				daoObj.setProcInValue(nProcIndex1, "gnum_seatid", vo.getStrSeatId(),28);
				daoObj.setProcInValue(nProcIndex1, "gnum_isvalid", "1",29);
				daoObj.setProcInValue(nProcIndex1, "gnum_hospital_code",vo.getStrHospCode(),30);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isaccepted", "0",31);
				daoObj.setProcInValue(nProcIndex1, "gnum_nurse_seatid","",32);
				daoObj.setProcInValue(nProcIndex1, "hipnum_pat_from","",33);
				daoObj.setProcInValue(nProcIndex1, "hipnum_ref_adm_no","",34);
				daoObj.setProcOutValue(nProcIndex1, "err",1,35);	
				daoObj.setProcOutValue(nProcIndex1, "adm_no",1,36);// 1 for string return
				daoObj.setProcInValue(nProcIndex1, "hrgstr_fathusbname", vo.getStrFatherName(),37);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_address",strAddress,38);				
				daoObj.setProcInValue(nProcIndex1, "hrgstr_city", vo.getStrCity(),39);
				daoObj.setProcInValue(nProcIndex1, "gnum_state_code", vo.getStrState(),40);
				daoObj.setProcInValue(nProcIndex1, "gnum_country_code", vo.getStrCountry(),41);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_relgn_code", vo.getStrReligion(),42);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officetel", vo.getStrOccOffPhNo(),43);
				daoObj.setProcInValue(nProcIndex1, "hipnum_org_type", vo.getStrOccOrgType(),44);
				daoObj.setProcInValue(nProcIndex1, "hipstr_empno", vo.getStrOccEmpNo(),45);					
				daoObj.setProcInValue(nProcIndex1, "hipstr_empname", vo.getStrOccEmpName(),46);
				daoObj.setProcInValue(nProcIndex1, "hipstr_desig", vo.getStrOccDesc(),47);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officename", vo.getStrOccOffName(),48);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd1", vo.getStrOccAdd1(),49);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd2", vo.getStrOccAdd2(),50);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officecity", vo.getStrOccCity(),51);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officestate", vo.getStrOccState(),52);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isgovtemp", vo.getStrOccIsGovtEmp(),53);
				daoObj.setProcInValue(nProcIndex1, "hipnum_basic", vo.getStrOccBasic(),54);			
				daoObj.setProcInValue(nProcIndex1, "gnum_desig_code","",55);
				daoObj.setProcInValue(nProcIndex1, "hipdt_booking_dt", vo.getStrBookingDate(),56);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_house_no", strHouseNo,57);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_street_no", strStreet,58);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_district", vo.getStrDistrict(),59);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_phone_no", vo.getStrPhoneNo(),60);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_mobile_no", vo.getStrMobileNo(),61);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_pincode", vo.getStrPinCode(),62);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_ip_add", vo.getStrIpAddress(),63);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_state_name", vo.getStrStateName(),64);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_momname","",65);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mother_puk","",66);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_moth_nationality","",67);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_moth_relgn_code","",68);
				daoObj.setProcInValue(nProcIndex1, "hipdt_admstatus_code", admitted,69);
				daoObj.setProcInValue(nProcIndex1, "hipnum_charge", (vo.getStrAdmissionChargeValue() == null ? "0" :vo.getStrAdmissionChargeValue()),70);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_spousename", vo.getStrSpouseName(),71);
				daoObj.setProcInValue(nProcIndex1, "pass_valid_for","",72);
				daoObj.setProcInValue(nProcIndex1, "no_of_free_pass", ipdConfig.getStrNoOfFreePassAdmisssionTime(),73);
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_type", vo.getStrBedTypeCode(),74);
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_type", vo.getStrRoomTypeCode(),75);
				daoObj.setProcInValue(nProcIndex1, "adviceFlag", vo.getStrIsAdmissionOnline(),76);
				daoObj.setProcInValue(nProcIndex1, "attendancePass", ipdConfig.getStrAttendentPassGenerateAtAdmissionTime(),77);				
				daoObj.setProcInValue(nProcIndex1, "maxBornallowed","",78);
				daoObj.setProcInValue(nProcIndex1, "billFlag", vo.getStrIsIntegratedWithBilling(),79);
				daoObj.setProcInValue(nProcIndex1, "admissionChargeFlag", vo.getStrAdmissionCharge(),80);
				daoObj.setProcInValue(nProcIndex1, "gnum_caste_code",vo.getStrPatientCaste(),81);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_tehsil",vo.getStrTehsil(),82);
				daoObj.setProcInValue(nProcIndex1, "gnum_marital_status_code",vo.getStrMaritalStatus(),83);
				daoObj.setProcInValue(nProcIndex1, "hgnum_relief_fund_code",vo.getStrReliefFund(),84);
				daoObj.setProcInValue(nProcIndex1, "hgnum_admission_type_code", vo.getStrAdmissionType(),85);	
				
				//System.out.println("Admission type value------->"+vo.getStrAdmissionType());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_idmark", vo.getStrIdMark(),86);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_location", vo.getStrCityLocation(),87);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_emgaddress1", vo.getStrEmgAddress1(),88);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_emgaddress2", vo.getStrEmgAddress2(),89);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonname", vo.getStrFirstPersonName(),90);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonrelation", vo.getStrFirstPersonRelationCode(),91);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonname", vo.getStrSecondPersonName(),92);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonrelation", vo.getStrSecondPersonRelationCode(),93);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonphone", vo.getStrFirstpersonphone(),94);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonphone", vo.getStrSecondpersonphone(),95);
				
				//checking if admission charge required
				if(!vo.getStrAdmissionCharge().equals("0"))
				{
					daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge_required", vo.getStrAdmissionCharge(),96);
					daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge", (vo.getStrAdmissionChargeValue() == null ? "0" :vo.getStrAdmissionChargeValue()),97);
				}
				else
				{
					daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge_required","",96);
					daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge","",97);
				}
				
				if(ipdConfig.getStrIntegrationBilling().equals("1"))
					daoObj.setProcInValue(nProcIndex1,"hrgstr_bill_flag", ipdConfig.getStrIntegrationBilling(),98);
				else
					daoObj.setProcInValue(nProcIndex1,"hrgstr_bill_flag","0",98);	
				daoObj.setProcInValue(nProcIndex1,"hrgnum_casesheetno", vo.getStrCaseSheetNo(),99);
				daoObj.setProcInValue(nProcIndex1, "district_code", vo.getStrDistrictCode(),100);
				
				
				daoObj.executeProcedureByPosition(nProcIndex1);
				err=daoObj.getString(nProcIndex1, "err");
				admno=daoObj.getString(nProcIndex1, "adm_no");
				
			}
		/*	else if(vo.getStrNewBorn().equals("1"))
			{
				nProcIndex1 = daoObj.setProcedure(strProcName3);
				//daoObj.setProcInValue(nProcIndex1, "hipnum_admno", vo.getStrAdmNo());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_puk", vo.getStrCrNo());
				daoObj.setProcInValue(nProcIndex1, "hipnum_adm_advno", vo.getStrAdviceAdmNo());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_episode_code", vo.getStrEpisodeCode());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_visit_no", vo.getStrVisitNo());
				daoObj.setProcInValue(nProcIndex1, "gnum_dept_code", vo.getStrDeptCode());
				daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code", vo.getStrDeptUnitCode());
				daoObj.setProcInValue(nProcIndex1, "hipnum_ward_code", vo.getStrWardCode());
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_code", vo.getStrRoomCode());
				daoObj.setProcInValue(nProcIndex1, "gnum_owndept_code", vo.getStrDeptCode());
				daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrDeptUnitCode());
				daoObj.setProcInValue(nProcIndex1, "hipnum_ownward_code", vo.getStrWardCode());
				daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrTreatmentCategoryCode());
				daoObj.setProcInValue(nProcIndex1, "hipnum_isrural", vo.getStrIsUrban());
				daoObj.setProcInValue(nProcIndex1, "hipnum_isnewborn", vo.getStrNewBorn());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mlc_no", vo.getStrMlcNo());
				daoObj.setProcInValue(nProcIndex1, "hipstr_remarks", vo.getStrRemarks());
				daoObj.setProcInValue(nProcIndex1, "gnum_relation_code",vo.getStrOccRelation() );
				daoObj.setProcInValue(nProcIndex1, "gnum_seatid", vo.getStrSeatId());
				daoObj.setProcInValue(nProcIndex1, "gnum_hospital_code",vo.getStrHospCode());
				daoObj.setProcInValue(nProcIndex1, "gnum_isvalid", "1");
				daoObj.setProcInValue(nProcIndex1, "hrgstr_fathusbname", vo.getStrFatherName());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_address",strAddress);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_city", vo.getStrCity());
				daoObj.setProcInValue(nProcIndex1, "gnum_state_code", vo.getStrState());
				daoObj.setProcInValue(nProcIndex1, "gnum_country_code", vo.getStrCountry());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_relgn_code", vo.getStrReligion());
				daoObj.setProcInValue(nProcIndex1, "hipstr_officetel", vo.getStrOccOffPhNo());
				daoObj.setProcInValue(nProcIndex1, "hipnum_org_type", vo.getStrOccOrgType());
				daoObj.setProcInValue(nProcIndex1, "hipstr_empno", vo.getStrOccEmpNo());
				daoObj.setProcInValue(nProcIndex1, "hipstr_empname", vo.getStrOccEmpName());
				daoObj.setProcInValue(nProcIndex1, "hipstr_desig", vo.getStrOccDesc());
				daoObj.setProcInValue(nProcIndex1, "hipstr_officename", vo.getStrOccOffName());
				daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd1", vo.getStrOccAdd1());
				daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd2", vo.getStrOccAdd2());
				daoObj.setProcInValue(nProcIndex1, "hipstr_officecity", vo.getStrOccCity());
				daoObj.setProcInValue(nProcIndex1, "hipstr_officestate", vo.getStrOccState());
				daoObj.setProcInValue(nProcIndex1, "hipnum_isgovtemp", vo.getStrOccIsGovtEmp());
				daoObj.setProcInValue(nProcIndex1, "hipnum_basic", vo.getStrOccBasic());
				daoObj.setProcInValue(nProcIndex1, "hipdt_booking_dt", vo.getStrBookingDate());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_house_no", strHouseNo);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_street_no", strStreet);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_district", vo.getStrDistrict());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_phone_no", vo.getStrPhoneNo());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_mobile_no", vo.getStrMobileNo());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_pincode",vo.getStrPinCode());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_ip_add", vo.getStrIpAddress());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_state_name", "");
				daoObj.setProcInValue(nProcIndex1, "hipnum_isaccepted", "0");
				daoObj.setProcInValue(nProcIndex1, "hipdt_admstatus_code", admitted);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mother_puk", vo.getStrMotherCrNo());
				daoObj.setProcInValue(nProcIndex1, "hipnum_mothadmno", vo.getStrMotherAdmissionNo());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_moth_nationality", vo.getStrMotherNationalityCode());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_moth_relgn_code", vo.getStrMotherReligionCode());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_momname", vo.getStrMotherName());
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_code", vo.getStrBedCode());
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_type", vo.getStrBedTypeCode());
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_type", vo.getStrRoomTypeCode());
				daoObj.setProcInValue(nProcIndex1, "adviceFlag", vo.getStrIsAdmissionOnline());
				daoObj.setProcInValue(nProcIndex1, "billFlag", vo.getStrIsIntegratedWithBilling());
				daoObj.setProcInValue(nProcIndex1, "admissionChargeFlag", vo.getStrAdmissionCharge());
				daoObj.setProcOutValue(nProcIndex1, "err",1); // 1 for string return
				daoObj.setProcOutValue(nProcIndex1, "adm_no",1);
				
				daoObj.setProcInValue(nProcIndex1, "modval", "1");
				daoObj.setProcInValue(nProcIndex1, "hipdt_admdatetime", "");
				daoObj.setProcInValue(nProcIndex1, "hipdt_bedalloc_datetime", "");
				daoObj.setProcInValue(nProcIndex1, "hipnum_lengthofstay", "");
				daoObj.setProcInValue(nProcIndex1, "hipnum_occup_id","");				
				daoObj.setProcInValue(nProcIndex1, "hipnum_charge", vo.getStrAdmissionChargeValue());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_spousename", vo.getStrSpouseName());
				daoObj.setProcInValue(nProcIndex1, "no_of_free_pass", ipdConfig.getStrNoOfFreePassAdmisssionTime());
				daoObj.setProcInValue(nProcIndex1, "attendancePass", ipdConfig.getStrAttendentPassGenerateAtAdmissionTime());				
				daoObj.setProcInValue(nProcIndex1, "hipnum_consultant_id", vo.getStrConsultantCode());
				daoObj.setProcInValue(nProcIndex1, "gdt_entry_date","");
				daoObj.setProcInValue(nProcIndex1, "gnum_nurse_seatid","");
				daoObj.setProcInValue(nProcIndex1, "hipnum_pat_from","");
				daoObj.setProcInValue(nProcIndex1, "hipnum_ref_adm_no","");
				daoObj.setProcInValue(nProcIndex1, "gnum_desig_code","");
				daoObj.setProcInValue(nProcIndex1, "pass_valid_for","");
				daoObj.setProcInValue(nProcIndex1, "maxBornallowed","");
				
				daoObj.setProcInValue(nProcIndex1, "gnum_caste_code",vo.getStrPatientCaste());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_tehsil",vo.getStrTehsil());
				daoObj.setProcInValue(nProcIndex1, "gnum_marital_status_code",vo.getStrMaritalStatus());
				daoObj.setProcInValue(nProcIndex1, "hgnum_relief_fund_code",vo.getStrReliefFund());
				daoObj.setProcInValue(nProcIndex1, "hgnum_admission_type_code", vo.getStrAdmissionType());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_idmark", vo.getStrIdMark());
				
				
				daoObj.executeProcedure(nProcIndex1);
				err=daoObj.getString(nProcIndex1, "err");
				admno=daoObj.getString(nProcIndex1, "adm_no");			
			}*/
			else
			{
				
				nProcIndex1 = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex1, "modval", "1",1);									
				daoObj.setProcInValue(nProcIndex1, "hipnum_admno", "",2);	//							
				daoObj.setProcInValue(nProcIndex1, "hrgnum_puk", vo.getStrCrNo(),3);					
				daoObj.setProcInValue(nProcIndex1, "hipnum_adm_advno", vo.getStrAdviceAdmNo(),4);		
				daoObj.setProcInValue(nProcIndex1, "hrgnum_episode_code", vo.getStrEpisodeCode(),5);	
				daoObj.setProcInValue(nProcIndex1, "hrgnum_visit_no", vo.getStrVisitNo(),6);			
				daoObj.setProcInValue(nProcIndex1, "hipdt_admdatetime", "",7);		//				
				daoObj.setProcInValue(nProcIndex1, "hipdt_bedalloc_datetime", "",8);		//			
				daoObj.setProcInValue(nProcIndex1, "hipnum_lengthofstay", "",9);			//			
				daoObj.setProcInValue(nProcIndex1, "gnum_dept_code", vo.getStrDeptCode(),10);			
				//daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code", vo.getStrDeptUnitCode(),11);
				if(vo.getStrIsAdmissionOnline().equals("1"))//Advice Found
					daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code",vo.getStrDeptUnitCode(),11);
				else
					daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code",vo.getStrConsultantCode().replace("$","#").split("#")[1],11);
				
				daoObj.setProcInValue(nProcIndex1, "hipnum_ward_code",vo.getStrWardCode().replace("$","#").split("#")[0],12);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_code", vo.getStrRoomCode(),13);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_code","",14);		//					
				daoObj.setProcInValue(nProcIndex1, "gnum_owndept_code", vo.getStrDeptCode(),15);		
				//daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrDeptUnitCode(),16);
				if(vo.getStrIsAdmissionOnline().equals("1"))//Advice Found
					daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrDeptUnitCode(),16);
				else
					daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrConsultantCode().replace("$","#").split("#")[1],16);
				
				
				daoObj.setProcInValue(nProcIndex1, "hipnum_ownward_code", vo.getStrWardCode().replace("$","#").split("#")[0],17);		
				/*if(vo.getStrTreatmentCategoryCode().trim().equals("0"))
				{
					daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrPrimaryCategoryCode(),18);
				}
				else
				{
					daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrTreatmentCategoryCode(),18);
				}*/
				
			
				daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrTreatmentCategoryCode(),18);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isnewborn", vo.getStrNewBorn(),19);			
				daoObj.setProcInValue(nProcIndex1, "hipnum_mothadmno", vo.getStrMotherAdmissionNo(),20);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mlc_no", vo.getStrMlcNo(),21);				
				daoObj.setProcInValue(nProcIndex1, "hipnum_consultant_id", vo.getStrConsultantCode().replace("$","#").split("#")[0],22);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isrural", vo.getStrIsUrban(),23);			
				daoObj.setProcInValue(nProcIndex1, "hipstr_remarks", vo.getStrRemarks(),24);			
				daoObj.setProcInValue(nProcIndex1, "hipnum_occup_id","",25);		//						
				daoObj.setProcInValue(nProcIndex1, "gnum_relation_code",vo.getStrOccRelation() ,26);
				daoObj.setProcInValue(nProcIndex1, "gdt_entry_date","",27);		//					
				daoObj.setProcInValue(nProcIndex1, "gnum_seatid", vo.getStrSeatId(),28);			
				daoObj.setProcInValue(nProcIndex1, "gnum_isvalid", "1",29);							
				daoObj.setProcInValue(nProcIndex1, "gnum_hospital_code",vo.getStrHospCode(),30);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_isaccepted", "0",31);						
				daoObj.setProcInValue(nProcIndex1, "gnum_nurse_seatid","",32);			//				
				daoObj.setProcInValue(nProcIndex1, "hipnum_pat_from","",33);				//			
				daoObj.setProcInValue(nProcIndex1, "hipnum_ref_adm_no","",34);						//	
				daoObj.setProcOutValue(nProcIndex1, "err",1,35); // 1 for string return				
				daoObj.setProcOutValue(nProcIndex1, "adm_no",1,36);								
				daoObj.setProcInValue(nProcIndex1, "hrgstr_fathusbname", vo.getStrFatherName(),37);	
				daoObj.setProcInValue(nProcIndex1, "hrgstr_address",strAddress,38);					
				daoObj.setProcInValue(nProcIndex1, "hrgstr_city", vo.getStrCity(),39);					
				daoObj.setProcInValue(nProcIndex1, "gnum_state_code", vo.getStrState(),40);			
				daoObj.setProcInValue(nProcIndex1, "gnum_country_code",vo.getStrCountry(),41);			
				daoObj.setProcInValue(nProcIndex1, "hrgnum_relgn_code", vo.getStrReligion(),42);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_officetel", vo.getStrOccOffPhNo(),43);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_org_type", vo.getStrOccOrgType(),44);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_empno", vo.getStrOccEmpNo(),45);			
				daoObj.setProcInValue(nProcIndex1, "hipstr_empname", vo.getStrOccEmpName(),46);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_desig", vo.getStrOccDesc(),47);				
				daoObj.setProcInValue(nProcIndex1, "hipstr_officename", vo.getStrOccOffName(),48);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd1", vo.getStrOccAdd1(),49);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd2", vo.getStrOccAdd2(),50);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_officecity", vo.getStrOccCity(),51);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_officestate", vo.getStrOccState(),52);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_isgovtemp", vo.getStrOccIsGovtEmp(),53);	
				daoObj.setProcInValue(nProcIndex1, "hipnum_basic", vo.getStrOccBasic(),54);			
				daoObj.setProcInValue(nProcIndex1, "gnum_desig_code","",55);				//			
				daoObj.setProcInValue(nProcIndex1, "hipdt_booking_dt", vo.getStrBookingDate(),56);		
				daoObj.setProcInValue(nProcIndex1, "hrgstr_house_no", strHouseNo,57);					
				daoObj.setProcInValue(nProcIndex1, "hrgstr_street_no", strStreet,58);					
				daoObj.setProcInValue(nProcIndex1, "hrgstr_district", vo.getStrDistrict(),59);			
				daoObj.setProcInValue(nProcIndex1, "hrgstr_phone_no", vo.getStrPhoneNo(),60);			
				daoObj.setProcInValue(nProcIndex1, "hrgstr_mobile_no", vo.getStrMobileNo(),61);		
				daoObj.setProcInValue(nProcIndex1, "hrgnum_pincode",vo.getStrPinCode(),62);			
				daoObj.setProcInValue(nProcIndex1, "hrgstr_ip_add", vo.getStrIpAddress(),63);			
				daoObj.setProcInValue(nProcIndex1, "hrgstr_state_name", vo.getStrStateName(),64);	//					
				daoObj.setProcInValue(nProcIndex1, "hrgstr_momname", vo.getStrMotherName(),65);		
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mother_puk", vo.getStrMotherCrNo(),66);		
				daoObj.setProcInValue(nProcIndex1, "hrgstr_moth_nationality", vo.getStrMotherNationalityCode(),67);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_moth_relgn_code", vo.getStrMotherReligionCode(),68);
				daoObj.setProcInValue(nProcIndex1, "hipdt_admstatus_code", admitted,69);				
				daoObj.setProcInValue(nProcIndex1, "hipnum_charge", vo.getStrAdmissionChargeValue(),70);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_spousename", vo.getStrSpouseName(),71);		
				daoObj.setProcInValue(nProcIndex1, "pass_valid_for", vo.getStrFreePassValid(),72);		
				daoObj.setProcInValue(nProcIndex1, "no_of_free_pass",ipdConfig.getStrNoOfFreePassAdmisssionTime(),73);
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_type", vo.getStrBedTypeCode(),74);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_type", vo.getStrRoomTypeCode(),75);
				daoObj.setProcInValue(nProcIndex1, "adviceFlag", vo.getStrIsAdmissionOnline(),76);		
				daoObj.setProcInValue(nProcIndex1, "attendancePass", ipdConfig.getStrAttendentPassGenerateAtAdmissionTime(),77);
				String tmpStrSplitAge=vo.getStrHiddenPatDtl().replace("^", "#").split("#")[3].replace("/", "#").split("#")[0];
				if(tmpStrSplitAge.contains("Yr"))
				{
					if(Integer.parseInt(tmpStrSplitAge.replace("Yr", "").trim())>=Integer.parseInt(ipdConfig.getStrMinAgeToBeMother()))
						daoObj.setProcInValue(nProcIndex1, "maxBornAllowed", ipdConfig.getStrMaxNoOfBabyMotherCanBorn(),78);
					else
						daoObj.setProcInValue(nProcIndex1, "maxBornAllowed", "",78);		//			
				}
				else
				{
					daoObj.setProcInValue(nProcIndex1, "maxBornAllowed", "",78);			//			
				}
				daoObj.setProcInValue(nProcIndex1, "billFlag", vo.getStrIsIntegratedWithBilling(),79);	
				daoObj.setProcInValue(nProcIndex1, "admissionChargeFlag",vo.getStrAdmissionCharge(),80);
				daoObj.setProcInValue(nProcIndex1, "gnum_caste_code",vo.getStrPatientCaste(),81);		
				daoObj.setProcInValue(nProcIndex1, "hrgstr_tehsil",vo.getStrTehsil(),82);				
				daoObj.setProcInValue(nProcIndex1, "gnum_marital_status_code",vo.getStrMaritalStatus(),83);
				daoObj.setProcInValue(nProcIndex1, "hgnum_relief_fund_code",vo.getStrReliefFund(),84);	
				daoObj.setProcInValue(nProcIndex1, "hgnum_admission_type_code", vo.getStrAdmissionType(),85);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_idmark", vo.getStrIdMark(),86);				
				daoObj.setProcInValue(nProcIndex1,"hrgstr_location", vo.getStrCityLocation(),87);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_emgaddress1", vo.getStrEmgAddress1(),88);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_emgaddress2", vo.getStrEmgAddress2(),89);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonname", vo.getStrFirstPersonName(),90);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonrelation", vo.getStrFirstPersonRelationCode(),91);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonname", vo.getStrSecondPersonName(),92);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonrelation", vo.getStrSecondPersonRelationCode(),93);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonphone", vo.getStrFirstpersonphone(),94);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonphone", vo.getStrSecondpersonphone(),95);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge_required", vo.getStrAdmissionCharge(),96);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge", (vo.getStrAdmissionChargeValue().equals("0") ? "" :vo.getStrAdmissionChargeValue()),97);
				if(ipdConfig.getStrIntegrationBilling().equals("1"))
				daoObj.setProcInValue(nProcIndex1,"hrgstr_bill_flag", ipdConfig.getStrIntegrationBilling(),98);
				else
				daoObj.setProcInValue(nProcIndex1,"hrgstr_bill_flag","0",98);
				daoObj.setProcInValue(nProcIndex1,"hrgnum_casesheetno", vo.getStrCaseSheetNo(),99);
				daoObj.setProcInValue(nProcIndex1, "district_code", vo.getStrDistrictCode(),100);
				
		
				daoObj.executeProcedureByPosition(nProcIndex1);
				err=daoObj.getString(nProcIndex1, "err");		
				admno=daoObj.getString(nProcIndex1, "adm_no");
			}
			
			nProcIndex2 = daoObj.setProcedure(strProcName4);
			daoObj.setProcInValue(nProcIndex2, "modeval", "1",1);									
			daoObj.setProcInValue(nProcIndex2, "adm_no", admno,2);							
			daoObj.setProcInValue(nProcIndex2, "hospital_code", vo.getStrHospCode(),3);					
			daoObj.setProcInValue(nProcIndex2, "puk", vo.getStrCrNo(),4);		
			daoObj.setProcInValue(nProcIndex2, "mov_no", "1",5);	
			daoObj.setProcInValue(nProcIndex2, "ip", GetNetworkAddress.GetAddress("ip"),6);			
			daoObj.setProcInValue(nProcIndex2, "mac", GetNetworkAddress.GetAddress("mac"),7);				
			daoObj.setProcOutValue(nProcIndex2, "err", 1,8);			
			daoObj.executeProcedureByPosition(nProcIndex2);
			err=daoObj.getString(nProcIndex2, "err");
			
			if(err.equals(""))
			{
				chkFlag=true;
				vo.setStrAdmNo(admno);
				vo.setStrMsgType("0");
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			chkFlag=false;
			vo.setStrMsgString("PatientAdmissionTransDAO.insertPatAdmission() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
		
		return chkFlag;
	}
	/**
	 * This function is used to set all details of particular patient regarding to episode details on the main page in their corresponding hidden fields
	 * @param vo
	 */
	public static void setEpisodeDtl(PatientAdmissionTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_episode_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		String ageSex = "";
		String ageUnit = "";
		String sex = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		
		try
		{
			daoObj = new HisDAO("ADT","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "3",1);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,2);
			daoObj.setProcInValue(nProcIndex, "dept_code", "",3);
			daoObj.setProcInValue(nProcIndex, "unit_code", "",4);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
			
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			if(ws.size()==0)
			{
				vo.setStrCrNoValid("1");
			}
			else
			{
				vo.setStrCrNoValid("0");
			}
			if (strErr.equals("")) 
			{					
					if (ws.next())
					{
						vo.setStrEpisodeCode(ws.getString(1));
						vo.setStrVisitNo(ws.getString(2));
						//vo.setStrMlcNo(ws.getString(3));
						vo.setStrDeptCode(ws.getString(3));
						vo.setStrDeptUnitCode(ws.getString(4));
						vo.setStrTreatmentCategoryCode(ws.getString(5));
						ageSex=ws.getString(6);
						
						ageUnit=ageSex.replace("/","#").split("#")[0].replace(" ","#").split("#")[1];
						sex=ageSex.replace("/","#").split("#")[1];
						vo.setStrAge(ageSex.replace("/","#").split("#")[0].replace(" ","#").split("#")[0]);
						
						if(ageUnit.toLowerCase().equals("yr") || ageUnit.toLowerCase().equals("y"))
							vo.setStrAgeUnit("3");
						else if(ageUnit.toLowerCase().equals("m") || ageUnit.toLowerCase().equals("mth"))
							vo.setStrAgeUnit("2");
						else if(ageUnit.toLowerCase().equals("w") || ageUnit.toLowerCase().equals("wk"))
							vo.setStrAgeUnit("4");
						else if(ageUnit.toLowerCase().equals("d"))
							vo.setStrAgeUnit("1");
						else if(ageUnit.toLowerCase().equals("hr"))
							vo.setStrAgeUnit("5");
						else 
							vo.setStrAgeUnit("3");
						
						if(sex.toLowerCase().equals("m") || sex.toLowerCase().equals("male"))
							vo.setStrSexCode("1");
						else if(sex.toLowerCase().equals("f") || sex.toLowerCase().equals("female"))
							vo.setStrSexCode("2");
						else
							vo.setStrSexCode("3");						
												
						vo.setStrPatIsUnknown(ws.getString(7));
						vo.setStrPatCatCode(ws.getString(8));
						vo.setStrIsUrban(ws.getString(9));
						vo.setStrPatStatusCode(ws.getString(10));
						vo.setStrPrimaryCategoryCode(ws.getString(11));
						vo.setStrPatCategoryName(ws.getString(12));
						vo.setStrHospDtl(ws.getString(13));
						
						
					}

			} else {
				throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransDAO.setEpisodeDtl() --> "
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
	/**
	 * This function set Admission advice number,booking date,NewBorn status on the main page 
	 * @param vo
	 */
	public static void setAdviceAdmNo(PatientAdmissionTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_admission_advice_dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try
		{
			
			daoObj = new HisDAO("ADT","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
			daoObj.setProcInValue(nProcIndex, "adv_frm_validity", vo.getStrAdmissionAdviceValidityTo(),4);
			daoObj.setProcInValue(nProcIndex, "adv_to_validity", vo.getStrAdmissionAdviceValidityFrom(),5);
			daoObj.setProcInValue(nProcIndex, "patListType", "0",6); // --ONLINE ADVICE PATIENT LISTING
			daoObj.setProcInValue(nProcIndex, "searchStr", "",7);
			daoObj.setProcInValue(nProcIndex, "searchType", "1",8);
			daoObj.setProcInValue(nProcIndex, "toRows", "0",9);
			daoObj.setProcInValue(nProcIndex, "frmRows", "0",10);
			daoObj.setProcOutValue(nProcIndex, "err", 1,11);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,12);
		
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			if (strErr.equals("")) 
			{
				if(ws.size()==0)//if(ws.size()==0 && vo.getStrIsAdmissionOnline().equals("1"))
				{
					vo.setStrAdviceStatus("0");
					vo.setStrIsAdmissionOnline("2");//If Admission Advice Found Then Set Admission As Online Otherwise Offline
				}
				else
				{
					vo.setStrAdviceStatus("1");
					vo.setStrIsAdmissionOnline("1");//If Admission Advice Found Then Set Admission As Online Otherwise Offline
				}
				if (ws.next())
				{
					vo.setStrAdviceAdmNo(ws.getString(1));
					vo.setStrBookingDate(ws.getString(2));
					vo.setStrNewBorn(ws.getString(3));
					vo.setStrDeptName(ws.getString(4));
					vo.setStrUnitName(ws.getString(5));
					vo.setStrDeptUnitName(ws.getString(5));
					vo.setStrDeptCode(ws.getString(6));
					vo.setStrDeptUnitCode(ws.getString(7));
					vo.setStrTreatmentCategoryName(ws.getString(8));
					vo.setStrTreatmentCategoryCode(ws.getString(9));
					vo.setStrWardCode(ws.getString(10));					
				}
			} 
			else 
			{
					vo.setStrAdviceStatus("0");	
					throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransDAO.setAdviceAdmNo() --> "+ e.getMessage());
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
	 * This function is used to bring address of a patient on the main page
	 * @param vo
	 */
	public static void getPatientAdd(PatientAdmissionTransVO vo)
	{
		//String strProcName = "{call pkg_ipd_view.proc_pat_demographicdtl(?,?,?,?)}";
		String strProcName = "{call pkg_simple_view.proc_pat_demographicdtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum;
		if(vo.getStrNewBorn().equals("1"))
		{
			strCrNum = vo.getStrMotherCrNo();
		}
		else
		{
			strCrNum = vo.getStrCrNo();
		}
		try
		{
			daoObj = new HisDAO("Patient Admission",
			"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "puk", strCrNum,1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr=daoObj.getString(nProcIndex, "err");
			if(strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				
				if(ws.next())
				{
					
					vo.setStrFatherName(ws.getString(4));
					vo.setStrReligionCode(ws.getString(5));
					vo.setStrHouseNo(ws.getString(6));
					vo.setStrStreet(ws.getString(7));
					vo.setStrPhoneNo(ws.getString(8));
					vo.setStrMobileNo(ws.getString(9));
					vo.setStrCity(ws.getString(10));
					vo.setStrStateName(ws.getString(18));
					vo.setStrDistrict(ws.getString(11));
					vo.setStrStateCode(ws.getString(12));
					vo.setStrCountryCode(ws.getString(13));
					vo.setStrPinCode(ws.getString(14));
					vo.setStrSpouseName(ws.getString(15));
					vo.setStrPatientCaste(ws.getString(19));
					vo.setStrMaritalStatus(ws.getString(20));
					vo.setStrTehsil(ws.getString(21));
					vo.setStrCityLocation(ws.getString(22));
					vo.setStrDistrictCode(ws.getString(24));
					
				}
			}
			else
			{
				throw new Exception(strErr);
			}

		}
		catch(Exception e)
		{
			
			vo.setStrMsgString("PatientAdmissionTransDAO.getPatientAdd() --> "
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
	/**
	 * This function is used to bring religion detail of a patient on main page
	 * @param vo
	 */
	public static void setReligion(PatientAdmissionTransVO vo)
	{

		String strProcName = "{call pkg_ipd_view.Proc_religion_dtl(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Patient Admission",
			"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr=daoObj.getString(nProcIndex, "err");
			if(strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setReligionWs(ws);
			}
			else
			{
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransDAO.setReligion() --> "
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
	/**
	 * This function is used to set state on main page to which patient belong
	 * @param vo
	 */
	public static void setState(PatientAdmissionTransVO vo)
	{

		String strProcName = "{call pkg_ipd_view.Proc_state_dtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Patient Admission",
			"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr=daoObj.getString(nProcIndex, "err");
			if(strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setStateWS(ws);
			}
			else
			{
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransDAO.setState() --> "
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
	/**
	 * This function is used to bring ward,room and bed details in case of Ms Approval 
	 * @param vo
	 */
	public static void getPatDtl_Msapproval(PatientAdmissionTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_pat_adm_msapproval(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			//	System.out.println("Hello Ms Approval");
				daoObj = new HisDAO("Patient Admission Transaction",
					"PatientAdmissionTransDAO");
				
				nProcIndex = daoObj.setProcedure(strProcName);
				
				daoObj.setProcInValue(nProcIndex, "modeVal","2",1);
				daoObj.setProcInValue(nProcIndex, "pukno",vo.getStrCrNo(),2);
				daoObj.setProcInValue(nProcIndex, "advno",vo.getStrAdviceAdmNo(),3);
				daoObj.setProcInValue(nProcIndex, "bookingdate",vo.getStrBookingDate(),4);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),5);
				daoObj.setProcOutValue(nProcIndex, "err", 1,6);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					if(ws.size()==0)
					{
						vo.setStrMsApprovalStatus("1");
					}
					else
					{
						vo.setStrMsApprovalStatus("0");
					}
					if(ws.next())
					{
						vo.setStrWardTypeCode(ws.getString(1));
						vo.setStrWardCode(ws.getString(2));
						vo.setStrDeptCode(ws.getString(3));
						vo.setStrRoomCode(ws.getString(4));
						vo.setStrBedCode(ws.getString(5));
						vo.setStrMsApprovalFlag(ws.getString(6));
						vo.setStrWardName(ws.getString(7));
						vo.setStrRoom(ws.getString(8));
						vo.setStrRoomTypeCode(ws.getString(9));
						vo.setStrBedTypeCode(ws.getString(10));
						vo.setStrDeptUnitCode(ws.getString(11));
					}
				
				} else {
							throw new Exception(strErr);
						}
			
		} catch (Exception e) {
				vo.setStrMsgString("PatientAdmissionTransDAO.getPatDtl_Msapproval() --> "
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
	public static void getPatDtl_Msapprovalstat(PatientAdmissionTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_pat_adm_msapproval(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			//	System.out.println("Hello Ms Approval");
				daoObj = new HisDAO("Patient Admission Transaction",
					"PatientAdmissionTransDAO");
				
				nProcIndex = daoObj.setProcedure(strProcName);
				
				daoObj.setProcInValue(nProcIndex, "modeVal","3",1);
				daoObj.setProcInValue(nProcIndex, "pukno",vo.getStrCrNo(),2);
				daoObj.setProcInValue(nProcIndex, "advno",vo.getStrAdviceAdmNo(),3);
				daoObj.setProcInValue(nProcIndex, "bookingdate",vo.getStrBookingDate(),4);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),5);
				daoObj.setProcOutValue(nProcIndex, "err", 1,6);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					if(ws.size()==0)
					{
						vo.setStrMsAppStatus("0");
					}
					else if(ws.next())
					{
						vo.setStrMsAppStatus(ws.getString(1));
						if(vo.getStrMsAppStatus().equals("2"))
						{
						vo.setStrWardCode(ws.getString(2));
						vo.setStrDeptCode(ws.getString(3));
						}
					}
				
				} else {
							throw new Exception(strErr);
						}
			
		} catch (Exception e) {
				vo.setStrMsgString("PatientAdmissionTransDAO.getPatDtl_Msapproval() --> "
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
	/**
	 * This function is used to bring details in  country combo
	 * @param vo
	 */
	public static void setCountry(PatientAdmissionTransVO vo)
	{

		String strProcName = "{call pkg_ipd_view.Proc_state_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Patient Admission",
			"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "2",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", IpdConfigUtil.SUPER_HOSPITAL_CODE.toString(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr=daoObj.getString(nProcIndex, "err");
			if(strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				vo.setCountryWS(ws);
			}
			else
			{
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransDAO.setCountry() --> "
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
	/**
	 * 
	 * @param vo
	 */
	public static void getMsApprovalStatus(PatientAdmissionTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		daoObj = new HisDAO("Patient Admission Transaction",
					"PatientAdmissionTransDAO.getWardValues()");
		try
		{
			daoObj = new HisDAO("Patient Admission Transaction",
			"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal","7",1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode","0",2);
			daoObj.setProcInValue(nProcIndex, "deptcode","0",3);
			daoObj.setProcInValue(nProcIndex, "deptunitcode","0",4);
            daoObj.setProcInValue(nProcIndex, "unitcode","0",5);
			daoObj.setProcInValue(nProcIndex, "age","0",6);
			daoObj.setProcInValue(nProcIndex, "gender","0",7);
			daoObj.setProcInValue(nProcIndex, "treatment_cat","0",8);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),9);
			daoObj.setProcInValue(nProcIndex, "effect_from","",10);
			daoObj.setProcInValue(nProcIndex, "effect_to","",11);
			daoObj.setProcInValue(nProcIndex, "diseasetypcode","0",12);
			daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),13);
            daoObj.setProcInValue(nProcIndex, "puk_no","0",14);			
			daoObj.setProcInValue(nProcIndex, "charge_type_id","0",15);
			daoObj.setProcOutValue(nProcIndex, "err", 1,16);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,17);
		
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				if(ws.next())
				{
					vo.setStrMsApprovalFlag(ws.getString(1));
				}
			} else {
					throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransDAO.getWardValues() --> "
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
	/**
	 * This function is used to set inital parameters during new born baby case
	 * @param vo
	 */
	public static void setNewBornBabyDtl(PatientAdmissionTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_newborn_baby_status(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
	//	System.out.println("inside new Born baby status");
		try {
				daoObj = new HisDAO("Patient Admission",
					"PatientAdmissionTransDAO");
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
				if (strErr.equals("")) {
					if (ws.next()) {
						vo.setStrMotherName(ws.getString(2));
						vo.setStrMotherCrNo(ws.getString(3));
						vo.setStrMotherAdmissionNo(ws.getString(4));
						vo.setStrMotherNationalityCode(ws.getString(5));
						vo.setStrMotherNationality(ws.getString(6));
						vo.setStrMotherReligionCode(ws.getString(7));
						vo.setStrMotherReligion(ws.getString(8));
						vo.setStrRoomCode(ws.getString(9));
						vo.setStrRoom(ws.getString(10));
						vo.setStrBedCode(ws.getString(11));
					}
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientAdmissionTransDAO.setNewBornBabyDtl() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static void setChargeVal(PatientAdmissionTransVO vo)
	{
		String strProcName = "{? = call BILL_MST.getChargeDetails(?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::character varying)}";
		
		int nProcIndex = 0;
		String val="0";
	
		HisDAO daoObj = null;
		try 
		{
				daoObj = new HisDAO("ADT","PatientAdmissionTransDAO");
				nProcIndex = daoObj.setFunction(strProcName);
				daoObj.setFuncInValue(nProcIndex, 2, "3");
				daoObj.setFuncInValue(nProcIndex, 3, vo.getStrHospCode());
				daoObj.setFuncInValue(nProcIndex, 4, vo.getStrWardCode().replace("$","#").split("#")[0]);  // commented since admission charges will be configured one-time and not on changing the ward
				//daoObj.setFuncInValue(nProcIndex, 4, "0");
				daoObj.setFuncInValue(nProcIndex, 5, vo.getStrTreatmentCategoryCode());
				daoObj.setFuncInValue(nProcIndex, 6, "2");
				daoObj.setFuncInValue(nProcIndex, 7, "0");
				daoObj.setFuncInValue(nProcIndex, 8, "0");
				daoObj.setFuncOutValue(nProcIndex, 1);
				daoObj.executeFunction(nProcIndex);
				val=daoObj.getFuncString(nProcIndex);
				vo.setStrAdmissionChargeValue(val);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.setChargeVal() --> "+ e.getMessage());
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
	public static void department(PatientAdmissionTransVO _PatientAdmissionTransVO) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.Proc_Department(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "5",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", _PatientAdmissionTransVO.getStrHospCode(),2);
			daoObj.setProcInValue(nProcIndex, "puk", _PatientAdmissionTransVO.getStrCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "seatId","",4);			
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) 
				_PatientAdmissionTransVO.setDepartWS(ws);
			else 
				throw new Exception(strErr);
		} catch (Exception e) {
			_PatientAdmissionTransVO.setStrMsgString("AdmissionAdviceTransDAO.setDeptComboValues() --> "
					+ e.getMessage());
			_PatientAdmissionTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	// this function is used for unit combo on the basis of department

	public static void unit(PatientAdmissionTransVO _PatientAdmissionTransVO) {
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.Proc_Unit(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";

		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"AdmissionAdviceTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "2",1);
			if(_PatientAdmissionTransVO.getStrDeptCode().equals(""))
				daoObj.setProcInValue(nProcIndex, "dept_code", "0",2);
			else
			    daoObj.setProcInValue(nProcIndex, "dept_code", _PatientAdmissionTransVO.getStrDeptCode(),2);
			
			daoObj.setProcInValue(nProcIndex, "hosp_code", _PatientAdmissionTransVO.getStrHospCode(),3);
			daoObj.setProcInValue(nProcIndex, "puk", _PatientAdmissionTransVO.getStrCrNo(),4);
			
			daoObj.setProcInValue(nProcIndex, "seatId", "0",5);
			daoObj.setProcInValue(nProcIndex, "wardcode", "0",6);
			daoObj.setProcInValue(nProcIndex, "unitcode", "0",7);
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) 
				_PatientAdmissionTransVO.setUnitWS(ws);
			else 
				throw new Exception(strErr);
		} catch (Exception e) {
			_PatientAdmissionTransVO.setStrMsgString("AdmissionAdviceTransDAO.setUnitComboValues() --> "
					+ e.getMessage());
			_PatientAdmissionTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	/**
	 * This function is used to set all details of particular patient regarding to episode details on the main page in their corresponding hidden fields
	 * @param vo
	 */
	public static void setTreatCat(PatientAdmissionTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_episode_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try
		{
			daoObj = new HisDAO("Patient Admission",
			"PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "2",1);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,2);
			daoObj.setProcInValue(nProcIndex, "dept_code", vo.getStrDeptCode(),3);
			daoObj.setProcInValue(nProcIndex, "unit_code", vo.getStrDeptUnitCode(),4);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),5);
			daoObj.setProcOutValue(nProcIndex, "err", 1,6);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
		
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if(ws.size()==0)
			{
				vo.setStrCrNoValid("1");
			}
			else
			{
				vo.setStrCrNoValid("0");
			}
			if (strErr.equals("")) {
					
					
					if (ws.next())
					{
						vo.setStrEpisodeCode(ws.getString(1));
						vo.setStrVisitNo(ws.getString(2));
						vo.setStrMlcNo(ws.getString(3));
						vo.setStrTreatmentCategoryCode(ws.getString(6));
					}

			} else {
				throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransDAO.setEpisodeDtl() --> "
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
	/**
	 * This procedure is used to set intial information for treatment category combo
	 * @param voObj
	 */
	public static void setTreatmentCatDtl(PatientAdmissionTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call PKG_SIMPLE_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("ipd",
					"PatientAdmissionTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, " hosp_code", voObj.getStrHospCode(),2);
			daoObj.setProcInValue(nProcIndex, "puk_no", "",3);
			daoObj.setProcInValue(nProcIndex, "charge_type_id", "0",4);
			daoObj.setProcInValue(nProcIndex, "effect_from", "",5);
			daoObj.setProcInValue(nProcIndex, "effect_TO", "",6);
			daoObj.setProcOutValue(nProcIndex, "err", 1,7);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
			
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
			e.printStackTrace();
			voObj.setStrMsgString("PatientAdmissionTransDAO.setTreatmentCatDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void setConsultantNameDtl(PatientAdmissionTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String deptUnitCode = voObj.getStrDeptUnitCode();

		String strErr = "";
    	//System.out.println("deptUnitCode"+voObj.getStrDeptUnitCode());
    	//System.out.println(voObj.getStrHospCode());
    	//System.out.println(voObj.getStrDeptUnitCode()==null?voObj.getStrDeptCode()+"%":voObj.getStrDeptUnitCode());;
		try 
		{
			daoObj = new HisDAO("Admission Advice Trans","PatientAdmissionTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (deptUnitCode != null) 
			{
				//daoObj.setProcInValue(nProcIndex, "modVal", "2");
				//daoObj.setProcInValue(nProcIndex, "modVal", "7",1);  //Modeval 1 changed to 7 for designation
				daoObj.setProcInValue(nProcIndex, "modVal", "9",1);//changed from modVal 7 to 9 to get Unit also
				if(voObj.getStrDeptUnitCode().equals(""))
					daoObj.setProcInValue(nProcIndex, "deptunitcode","0",2);
				else
				    daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrDeptUnitCode()==null?voObj.getStrDeptCode()+"%":voObj.getStrDeptUnitCode(),2);
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

					voObj.setConsultantWS(ws);
				} else {
					throw new Exception(strErr);
				}
			}
		} catch (Exception e) {

			voObj.setStrMsgString("PatientAdmissionTransDAO.setConsultantNameDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void checkStatusWhetherAdvanceAmountGiven(PatientAdmissionTransVO vo)
	{
		String strProcName = "{? = call BILL_INTERFACE.FUNC_ONLINE_ADVANCE_CHECK(?::character varying,?::character varying,?::character varying)}";
		int nProcIndex = 0;
		String val="0";
		
		HisDAO daoObj = null;
		try 
		{
			daoObj = new HisDAO("ADT","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setFunction(strProcName);
			daoObj.setFuncInValue(nProcIndex, 2, "1");
			daoObj.setFuncInValue(nProcIndex, 3, vo.getStrHospCode());
			daoObj.setFuncInValue(nProcIndex, 4, vo.getStrCrNo());
			daoObj.setFuncOutValue(nProcIndex, 1);
			daoObj.executeFunction(nProcIndex);
			val=daoObj.getFuncString(nProcIndex);
			vo.setStrIsAdvanceAmountAtAdmissionTaken(val);	
		} 
		catch (Exception e) 
		{			
			vo.setStrMsgString("PatientAdmissionTransDAO.checkStatusWhetherAdvanceAmountGiven() --> "+ e.getMessage());
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

	public static void getStatusWhetherAdvanceAmountGiven(PatientAdmissionTransVO vo)
	{
		String strProcName = "{? = call BILL_INTERFACE.FUNC_ADVANCE_RECEIPT_DTL(?::numeric,?::numeric)}";
		int nProcIndex = 0;
		String val="0";
		

		HisDAO daoObj = null;
		try {

				daoObj = new HisDAO("Patient Admission Transaction",
								"PatientAdmissionTransDAO");
				nProcIndex = daoObj.setFunction(strProcName);
				daoObj.setFuncInValue(nProcIndex, 2, vo.getStrHospCode());
				daoObj.setFuncInValue(nProcIndex, 3, vo.getStrCrNo());
				daoObj.setFuncOutValue(nProcIndex, 1);
				daoObj.executeFunction(nProcIndex);
				val=daoObj.getFuncString(nProcIndex);
				
				String[] adv = val.replace("^","#").split("#");
				
				vo.setStrAdvanceAmountReceiptNo(adv[0]);
				vo.setStrAdvanceAmountDate(adv[1]);
				vo.setStrAdvanceAmount(adv[2]);
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientAdmissionTransDAO.getStatusWhetherAdvanceAmountGiven() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static void setConsultantNameDept(PatientAdmissionTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String deptUnitCode = voObj.getStrDeptUnitCode();

		String strErr = "";
	//	System.out.println("deptUnitCode"+deptUnitCode);
		try {
			daoObj = new HisDAO("Admission Advice Trans",
					"PatientAdmissionTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			if (deptUnitCode != null) {
				//daoObj.setProcInValue(nProcIndex, "modVal", "2");
				//daoObj.setProcInValue(nProcIndex, "modVal", "7",1);//changed from modVal 5 to 7 to get Desig hierarchy
				daoObj.setProcInValue(nProcIndex, "modVal", "9",1);//changed from modVal 7 to 9 to get Unit also
				daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrDeptCode(),2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),3);
				daoObj.setProcInValue(nProcIndex, "seatId", "",4);
				daoObj.setProcOutValue(nProcIndex, "err", 1,5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
				//daoObj.setProcInValue(nProcIndex, "deptunitcode", "");
			     
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {

					voObj.setConsultantWS(ws);
				} else {
					throw new Exception(strErr);
				}
			}
		} catch (Exception e) {

			voObj.setStrMsgString("PatientAdmissionTransDAO.setConsultantNameDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void setPatientCaste(PatientAdmissionTransVO vo) {
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
				vo.setWrsPatientCaste(ws);
			}
			else
			{
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.setPatientCaste() --> "
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
	
	public static void setMaritalStatus(PatientAdmissionTransVO vo) {
		String strProcName = "{call pkg_ipd_view.proc_getMaritalStatus(?,?,?,?)}";	//2+2=4 variable
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
				vo.setWrsMaritalStatus(ws);
			}
			else
			{
				throw new Exception(strErr);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.setMaritalStatus() --> "
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
	
	/*
	 * 
	 */
	public static void getAdmissionType(PatientAdmissionTransVO _PatientAdmissionTransVO) {
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
				_PatientAdmissionTransVO.setWrsAdmissionTypeValues(ws);
			else 
				throw new Exception(strErr);
		} catch (Exception e) {
			e.printStackTrace();
			_PatientAdmissionTransVO.setStrMsgString("AdmissionAdviceTransDAO.getAdmissionType() --> "
					+ e.getMessage());
			_PatientAdmissionTransVO.setStrMsgType("1");
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
	public static void getReliefFund(PatientAdmissionTransVO _PatientAdmissionTransVO) {
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
				_PatientAdmissionTransVO.setWrsReliefFundValues(ws);
			else 
				throw new Exception(strErr);
		} catch (Exception e) {
			e.printStackTrace();
			_PatientAdmissionTransVO.setStrMsgString("AdmissionAdviceTransDAO.getReliefFund() --> "
					+ e.getMessage());
			_PatientAdmissionTransVO.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	
	public static  void setRelation(PatientAdmissionTransVO vo) 
	 {
	 	String strProcName = "{call pkg_gbl_view.proc_gblt_relation_mst(?,?,?)}";
	 	int nProcIndex = 0;
	 	String strErr = "";
	 	HisDAO dao = null;
	 	WebRowSet ws = null;
	 	try
	 	{
	 		dao = new HisDAO("ADT","PatientAdmissionDAO");
	 		
	 		nProcIndex = dao.setProcedure(strProcName);
	 		dao.setProcInValue(nProcIndex, "p_modeval", "1",1);
	 		dao.setProcOutValue(nProcIndex, "err", 1,2);
	 		dao.setProcOutValue(nProcIndex, "resultset", 2,3);
	 		
	 		dao.executeProcedureByPosition(nProcIndex);
	 		
	 		strErr = dao.getString(nProcIndex, "err");
	 		
	 		if (strErr == null)
	 			strErr = "";
	 		
	 				
	 		if(strErr.equals(""))
			{
				ws = dao.getWebRowSet(nProcIndex, "resultset");
				vo.setStrRelationWs(ws);
				vo.setStrMsgString("0");
			}
			else
			{
				throw new Exception(strErr);
			}
	 			
	 		
	 		 
	 	}catch(Exception e)
	 	{
	 		e.printStackTrace();
	 		vo.setStrMsgString("PatAdmissionTransDAO.setRelation() --> "+ e.getMessage());
	 		vo.setStrMsgType("1");
	 		//throw e;
	 	}
	 	finally
	 	{
	 		if (dao != null) 
	 		{
	 			dao.free();
	 			dao = null;
	 		}
	 	}
	   }
	
	
	public static void  getDataForBillingslip(PatientAdmissionTransVO vo)
	{	
		String strProcName = "{call pkg_ipd_view.proc_get_data_admission_slip(?,?,?,?,?)}";
	 	int nProcIndex = 0;
	 	String strErr = "";
	 	HisDAO dao = null;
	 	WebRowSet ws=null;
	 		 
	 	try
	 	{
	 		dao = new HisDAO("ADT","PatientAdmissionDAO");
	 		nProcIndex = dao.setProcedure(strProcName);
	 		dao.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),1);
	 		dao.setProcInValue(nProcIndex, "admno", vo.getStrAdmNo(),2);
	 		dao.setProcInValue(nProcIndex, "crno", vo.getStrCrNo(),3);
	 		dao.setProcOutValue(nProcIndex, "err", 1,4);
	 		dao.setProcOutValue(nProcIndex, "resultset", 2,5);
	 		
	 		dao.executeProcedureByPosition(nProcIndex);
	 		
	 		strErr = dao.getString(nProcIndex, "err");
	 		
	 		if (strErr == null)
	 			strErr = "";
	 		
	 				
	 		ws = dao.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) 
			{
				if (ws.next()) 
				{
                       vo.setStrAdmDateTime(ws.getString(1));
                       vo.setStrBillNo(ws.getString(2));
                       vo.setStrPatCategoryName(ws.getString(3));
                       vo.setStrCrNo(ws.getString(4));
                       vo.setStrDeptName(ws.getString(5));
                       vo.setStrPatName(ws.getString(6).replace("^", "#").split("#")[0]);
                       vo.setStrAge(ws.getString(6).replace("^", "#").split("#")[1]);
                       vo.setStrAdmissionChargeValue(ws.getString(7));
                       vo.setStrAdmissionCharge(ws.getString(8));
                 }
			} 
			else 
			{
				throw new Exception(strErr);
			}	 		 
	 	}
	 	catch(Exception e)
	 	{
	 		e.printStackTrace();
	 		vo.setStrMsgString("PatAdmissionTransDAO.getDataForBillingslip() --> "+ e.getMessage());
	 		vo.setStrMsgType("1");
	 		//throw e;
	 	}
	 	finally
	 	{
	 		if (dao != null) 
	 		{
	 			dao.free();
	 			dao = null;
	 		}
	 	}
	 	
	 
	}
	public static void setDistrict(PatientAdmissionTransVO vo)
	{

		String strProcName = "{call pkg_ipd_view.Proc_district_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Patient Admission",
			"PatientAdmissionTransDAO");
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
			vo.setStrMsgString("PatientAdmissionTransDAO.setState() --> "
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
	public static void getIcuPvtBillStatus(PatientAdmissionTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_geticupvtbillstatus(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			
				daoObj = new HisDAO("Patient Admission Transaction",
					"PatientAdmissionTransDAO");
				
				nProcIndex = daoObj.setProcedure(strProcName);
				
				daoObj.setProcInValue(nProcIndex, "modeval","1",1);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),2);
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
				vo.setStrMsgString("PatientAdmissionTransDAO.getIcuPvtBillStatus() --> "
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
	
	public static void setStateInput(PatientAdmissionTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_state_input_dtl(?,?,?,   ?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try{
			daoObj = new HisDAO("Patient Admission Transaction",
					"PatientAdmissionTransDAO");
				
				nProcIndex = daoObj.setProcedure(strProcName);
				
				daoObj.setProcInValue(nProcIndex, "modeval","1",1);
				daoObj.setProcInValue(nProcIndex, "crno",vo.getStrCrNo(),2);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),3);
				
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				
				if(strErr.equals(""))
				{
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					if(ws.size()==0)
					{
						vo.setStrStateName("---");
						vo.setStrDistrict("---");
					}
					else if(ws.next())
					{
						vo.setStrStateName(ws.getString(1));
						vo.setStrDistrict(ws.getString(2));
					}
					
				}
				else
				{
					throw new Exception(strErr);
				}
			
		}catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransDAO.setStateInput() --> "
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
	
	public static void getWardonUnit(PatientAdmissionTransVO vo)
	{
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
			
			daoObj.setProcInValue(nProcIndex, "modeVal","16",1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode",vo.getStrWardTypeCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptcode",vo.getStrDeptCode(),3);
			daoObj.setProcInValue(nProcIndex, "deptunitcode",vo.getStrDeptUnitCode()==null?"0":vo.getStrDeptUnitCode(),4);
			daoObj.setProcInValue(nProcIndex, "unitcode",vo.getStrAgeUnit(),5);
			daoObj.setProcInValue(nProcIndex, "age",vo.getStrAge(),6);
			daoObj.setProcInValue(nProcIndex, "gender",vo.getStrSexCode(),7);
			daoObj.setProcInValue(nProcIndex, "treatment_cat",vo.getStrTreatmentCategoryCode(),8);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),9);
			daoObj.setProcInValue(nProcIndex, "effect_from","0",10);
			daoObj.setProcInValue(nProcIndex, "effect_to","0",11);
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
			if (strErr.equals("")) {
				vo.setWardWS(ws);
			} else {
					throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.getWardonUnit() --> "
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
	
	/*public static void setStateInputForeign(PatientAdmissionTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_state_input_foreign_dtl(?,?,?,   ?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		
		try
		{
			
			daoObj =new HisDAO("Patient Admission Transaction","PatientAdmissionTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			
			daoObj.setProcInValue(nProcIndex, "modeval","1",1);
			daoObj.setProcInValue(nProcIndex, "crno",vo.getStrCrNo(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err",1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset",2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			
			if(strErr.equals(""))
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if(ws.size()==0)
				{
					vo.setStrStateName("---");
					vo.setStrDistrict("---");
				}
				else if(ws.next())
				{
					vo.setStrStateName(ws.getString(1));
					vo.setStrDistrict(ws.getString(2));
				}
				
			}
			else
			{
				throw new Exception(strErr);
			}
		
			
		}catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionTransDAO.setStateInputForeign()------>"+ e.getMessage());
			vo.setStrMsgType("1");
			
		}
		
	}*/
	
	public static void setAdvanceAmount(PatientAdmissionTransVO vo)
	{
		String strProcName = "{? = call BILL_MST.get_advance_part_amt(?::numeric,?::numeric,?::numeric,?::numeric,?::numeric,?::numeric)}";
		
		int nProcIndex = 0;
		String val="0";
	
		HisDAO daoObj = null;
		try 
		{
				daoObj = new HisDAO("ADT","PatientAdmissionTransDAO");
				nProcIndex = daoObj.setFunction(strProcName);
				daoObj.setFuncInValue(nProcIndex, 2, "1");
				daoObj.setFuncInValue(nProcIndex, 3, vo.getStrHospCode());
				daoObj.setFuncInValue(nProcIndex, 4, vo.getStrWardCode().replace("$","#").split("#")[0]);
				//daoObj.setFuncInValue(nProcIndex, 4, "0");
				daoObj.setFuncInValue(nProcIndex, 5, "2");
				daoObj.setFuncInValue(nProcIndex, 6, vo.getStrTreatmentCategoryCode());
				daoObj.setFuncInValue(nProcIndex, 7, "0");
				daoObj.setFuncOutValue(nProcIndex, 1);
				daoObj.executeFunction(nProcIndex);
				val=daoObj.getFuncString(nProcIndex);
				vo.setStrAdvanceAmountVal(val);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.setChargeVal() --> "+ e.getMessage());
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
	
	public static boolean insertSingleWindowPatAdmission(HisDAO daoObj,PatientAdmissionTransVO vo)
	{
		String strProcName1 ="{call pkg_ipd_dml.proc_patient_adm(?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,?,?,  ?,?,?,?,?,?,?,?,?,?,?,?,?)}";	//100 variable
		String err=new String();
		int nProcIndex1 = 0;
		String strAddress=vo.getStrHouseNo()+","+vo.getStrStreet()+","+vo.getStrCity();
		vo.setStrAddress(strAddress);
		String strHouseNo=vo.getStrHouseNo();
		String strStreet=vo.getStrStreet();
		IpdConfigUtil ipdConfig=null;
		boolean chkFlag=false;
		try
		{
			ipdConfig=new IpdConfigUtil(vo.getStrHospCode());			

			if(vo.getStrMsApprovalFlag().equals("6"))///To Bec checked
			{
				nProcIndex1 = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex1, "modval", "1",1);
				daoObj.setProcInValue(nProcIndex1, "hipnum_admno", vo.getStrAdmNo(),2);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_puk", vo.getStrCrNo(),3);
				daoObj.setProcInValue(nProcIndex1, "hipnum_adm_advno", vo.getStrAdviceAdmNo(),4);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_episode_code", vo.getStrEpisodeCode(),5);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_visit_no", vo.getStrVisitNo(),6);
				daoObj.setProcInValue(nProcIndex1, "hipdt_admdatetime", "",7);
				daoObj.setProcInValue(nProcIndex1, "hipdt_bedalloc_datetime", "",8);
				daoObj.setProcInValue(nProcIndex1, "hipnum_lengthofstay", "",9);			
				daoObj.setProcInValue(nProcIndex1, "gnum_dept_code", vo.getStrDeptCode(),10);
				//daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code", vo.getStrDeptUnitCode(),11);
				if(vo.getStrIsAdmissionOnline().equals("1"))//Advice Found
					daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code",vo.getStrDeptUnitCode(),11);
				else
					daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code",vo.getStrConsultantCode().replace("$","#").split("#")[1],11);
				
				daoObj.setProcInValue(nProcIndex1, "hipnum_ward_code", vo.getStrWardCode().replace("$","#").split("#")[0],12);
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_code", vo.getStrRoomCode(),13);
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_code", vo.getStrBedCode(),14);
				daoObj.setProcInValue(nProcIndex1, "gnum_owndept_code", vo.getStrDeptCode(),15);
				//daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrDeptUnitCode(),16);
				if(vo.getStrIsAdmissionOnline().equals("1"))//Advice Found
					daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrDeptUnitCode(),16);
				else
					daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrConsultantCode().replace("$","#").split("#")[1],16);
				daoObj.setProcInValue(nProcIndex1, "hipnum_ownward_code",vo.getStrWardCode().replace("$","#").split("#")[0],17);				
				/*if(vo.getStrTreatmentCategoryCode().trim().equals("0"))
				{
					daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrPrimaryCategoryCode(),18);
				}
				else
				{
					daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrTreatmentCategoryCode(),18);
				}*/
				
		
				daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrTreatmentCategoryCode(),18);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isnewborn", vo.getStrNewBorn(),19);
				daoObj.setProcInValue(nProcIndex1, "hipnum_mothadmno","",20);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mlc_no", vo.getStrMlcNo(),21);
				daoObj.setProcInValue(nProcIndex1, "hipnum_consultant_id", vo.getStrConsultantCode().replace("$","#").split("#")[0],22);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isrural", vo.getStrIsUrban(),23);
				daoObj.setProcInValue(nProcIndex1, "hipstr_remarks", vo.getStrRemarks(),24);
				daoObj.setProcInValue(nProcIndex1, "hipnum_occup_id","",25);	
				daoObj.setProcInValue(nProcIndex1, "gnum_relation_code",vo.getStrOccRelation(),26 );
				daoObj.setProcInValue(nProcIndex1, "gdt_entry_date","",27);
				daoObj.setProcInValue(nProcIndex1, "gnum_seatid", vo.getStrSeatId(),28);
				daoObj.setProcInValue(nProcIndex1, "gnum_isvalid", "1",29);
				daoObj.setProcInValue(nProcIndex1, "gnum_hospital_code",vo.getStrHospCode(),30);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isaccepted", "0",31);
				daoObj.setProcInValue(nProcIndex1, "gnum_nurse_seatid","",32);
				daoObj.setProcInValue(nProcIndex1, "hipnum_pat_from","",33);
				daoObj.setProcInValue(nProcIndex1, "hipnum_ref_adm_no","",34);
				daoObj.setProcOutValue(nProcIndex1, "err",1,35);	
				daoObj.setProcOutValue(nProcIndex1, "adm_no",1,36);// 1 for string return
				daoObj.setProcInValue(nProcIndex1, "hrgstr_fathusbname", vo.getStrFatherName(),37);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_address",strAddress,38);				
				daoObj.setProcInValue(nProcIndex1, "hrgstr_city", vo.getStrCity(),39);
				daoObj.setProcInValue(nProcIndex1, "gnum_state_code", vo.getStrState(),40);
				daoObj.setProcInValue(nProcIndex1, "gnum_country_code", vo.getStrCountry(),41);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_relgn_code", vo.getStrReligion(),42);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officetel", vo.getStrOccOffPhNo(),43);
				daoObj.setProcInValue(nProcIndex1, "hipnum_org_type", vo.getStrOccOrgType(),44);
				daoObj.setProcInValue(nProcIndex1, "hipstr_empno", vo.getStrOccEmpNo(),45);					
				daoObj.setProcInValue(nProcIndex1, "hipstr_empname", vo.getStrOccEmpName(),46);
				daoObj.setProcInValue(nProcIndex1, "hipstr_desig", vo.getStrOccDesc(),47);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officename", vo.getStrOccOffName(),48);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd1", vo.getStrOccAdd1(),49);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd2", vo.getStrOccAdd2(),50);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officecity", vo.getStrOccCity(),51);
				daoObj.setProcInValue(nProcIndex1, "hipstr_officestate", vo.getStrOccState(),52);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isgovtemp", vo.getStrOccIsGovtEmp(),53);
				daoObj.setProcInValue(nProcIndex1, "hipnum_basic", vo.getStrOccBasic(),54);			
				daoObj.setProcInValue(nProcIndex1, "gnum_desig_code","",55);
				daoObj.setProcInValue(nProcIndex1, "hipdt_booking_dt", vo.getStrBookingDate(),56);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_house_no", strHouseNo,57);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_street_no", strStreet,58);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_district", vo.getStrDistrict(),59);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_phone_no", vo.getStrPhoneNo(),60);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_mobile_no", vo.getStrMobileNo(),61);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_pincode", vo.getStrPinCode(),62);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_ip_add", vo.getStrIpAddress(),63);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_state_name", vo.getStrStateName(),64);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_momname","",65);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mother_puk","",66);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_moth_nationality","",67);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_moth_relgn_code","",68);
				daoObj.setProcInValue(nProcIndex1, "hipdt_admstatus_code", admitted,69);
				daoObj.setProcInValue(nProcIndex1, "hipnum_charge", (vo.getStrAdmissionChargeValue() == null ? "0" :vo.getStrAdmissionChargeValue()),70);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_spousename", vo.getStrSpouseName(),71);
				daoObj.setProcInValue(nProcIndex1, "pass_valid_for","",72);
				daoObj.setProcInValue(nProcIndex1, "no_of_free_pass", ipdConfig.getStrNoOfFreePassAdmisssionTime(),73);
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_type", vo.getStrBedTypeCode(),74);
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_type", vo.getStrRoomTypeCode(),75);
				daoObj.setProcInValue(nProcIndex1, "adviceFlag", vo.getStrIsAdmissionOnline(),76);
				daoObj.setProcInValue(nProcIndex1, "attendancePass", ipdConfig.getStrAttendentPassGenerateAtAdmissionTime(),77);				
				daoObj.setProcInValue(nProcIndex1, "maxBornallowed","",78);
				daoObj.setProcInValue(nProcIndex1, "billFlag", vo.getStrIsIntegratedWithBilling(),79);
				daoObj.setProcInValue(nProcIndex1, "admissionChargeFlag", vo.getStrAdmissionCharge(),80);
				daoObj.setProcInValue(nProcIndex1, "gnum_caste_code",vo.getStrPatientCaste(),81);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_tehsil",vo.getStrTehsil(),82);
				daoObj.setProcInValue(nProcIndex1, "gnum_marital_status_code",vo.getStrMaritalStatus(),83);
				daoObj.setProcInValue(nProcIndex1, "hgnum_relief_fund_code",vo.getStrReliefFund(),84);
				daoObj.setProcInValue(nProcIndex1, "hgnum_admission_type_code", vo.getStrAdmissionType(),85);	
				
				//System.out.println("Admission type value------->"+vo.getStrAdmissionType());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_idmark", vo.getStrIdMark(),86);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_location", vo.getStrCityLocation(),87);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_emgaddress1", vo.getStrEmgAddress1(),88);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_emgaddress2", vo.getStrEmgAddress2(),89);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonname", vo.getStrFirstPersonName(),90);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonrelation", vo.getStrFirstPersonRelationCode(),91);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonname", vo.getStrSecondPersonName(),92);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonrelation", vo.getStrSecondPersonRelationCode(),93);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonphone", vo.getStrFirstpersonphone(),94);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonphone", vo.getStrSecondpersonphone(),95);
				
				
				//checking if admission charge required
				if(!vo.getStrAdmissionCharge().equals("0"))
				{
					daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge_required", vo.getStrAdmissionCharge(),96);
					daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge", (vo.getStrAdmissionChargeValue() == null ? "0" :vo.getStrAdmissionChargeValue()),97);
				}
				else
				{
					daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge_required","",96);
					daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge","",97);
				}
				
				if(ipdConfig.getStrIntegrationBilling().equals("1"))
					daoObj.setProcInValue(nProcIndex1,"hrgstr_bill_flag", ipdConfig.getStrIntegrationBilling(),98);
				else
					daoObj.setProcInValue(nProcIndex1,"hrgstr_bill_flag","0",98);	
				daoObj.setProcInValue(nProcIndex1,"hrgnum_casesheetno", vo.getStrCaseSheetNo(),99);
				daoObj.setProcInValue(nProcIndex1, "district_code", vo.getStrDistrictCode(),100);
				daoObj.execute(nProcIndex1,1);				
			}		
			else
			{
				
				
			
				nProcIndex1 = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex1, "modval", "1",1);									
				daoObj.setProcInValue(nProcIndex1, "hipnum_admno", vo.getStrAdmNo(),2);	//							
				daoObj.setProcInValue(nProcIndex1, "hrgnum_puk", vo.getStrCrNo(),3);					
				daoObj.setProcInValue(nProcIndex1, "hipnum_adm_advno", vo.getStrAdviceAdmNo(),4);		
				daoObj.setProcInValue(nProcIndex1, "hrgnum_episode_code", vo.getStrEpisodeCode(),5);	
				daoObj.setProcInValue(nProcIndex1, "hrgnum_visit_no", vo.getStrVisitNo(),6);			
				daoObj.setProcInValue(nProcIndex1, "hipdt_admdatetime", "",7);		//				
				daoObj.setProcInValue(nProcIndex1, "hipdt_bedalloc_datetime", "",8);		//			
				daoObj.setProcInValue(nProcIndex1, "hipnum_lengthofstay", "",9);			//			
				daoObj.setProcInValue(nProcIndex1, "gnum_dept_code", vo.getStrDeptCode(),10);			
				//daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code", vo.getStrDeptUnitCode(),11);
				if(vo.getStrIsAdmissionOnline().equals("1"))//Advice Found
					daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code",vo.getStrDeptUnitCode(),11);
				else
					daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code",vo.getStrConsultantCode().replace("$","#").split("#")[1],11);
				daoObj.setProcInValue(nProcIndex1, "hipnum_ward_code",vo.getStrWardCode().replace("$","#").split("#")[0],12);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_code", vo.getStrRoomCode(),13);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_code","",14);		//					
				daoObj.setProcInValue(nProcIndex1, "gnum_owndept_code", vo.getStrDeptCode(),15);		
				//daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrDeptUnitCode(),16);
				if(vo.getStrIsAdmissionOnline().equals("1"))//Advice Found
					daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrDeptUnitCode(),16);
				else
					daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", vo.getStrConsultantCode().replace("$","#").split("#")[1],16);
				
				
				daoObj.setProcInValue(nProcIndex1, "hipnum_ownward_code", vo.getStrWardCode().replace("$","#").split("#")[0],17);		
				/*if(vo.getStrTreatmentCategoryCode().trim().equals("0"))
				{
					daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrPrimaryCategoryCode(),18);
				}
				else
				{
					daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrTreatmentCategoryCode(),18);
				}*/
				
			
				daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrTreatmentCategoryCode(),18);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isnewborn", vo.getStrNewBorn(),19);			
				daoObj.setProcInValue(nProcIndex1, "hipnum_mothadmno", vo.getStrMotherAdmissionNo(),20);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mlc_no", vo.getStrMlcNo(),21);				
				daoObj.setProcInValue(nProcIndex1, "hipnum_consultant_id", vo.getStrConsultantCode().replace("$","#").split("#")[0],22);
				daoObj.setProcInValue(nProcIndex1, "hipnum_isrural", vo.getStrIsUrban(),23);			
				daoObj.setProcInValue(nProcIndex1, "hipstr_remarks", vo.getStrRemarks(),24);			
				daoObj.setProcInValue(nProcIndex1, "hipnum_occup_id","",25);		//						
				daoObj.setProcInValue(nProcIndex1, "gnum_relation_code",vo.getStrOccRelation() ,26);
				daoObj.setProcInValue(nProcIndex1, "gdt_entry_date","",27);		//					
				daoObj.setProcInValue(nProcIndex1, "gnum_seatid", vo.getStrSeatId(),28);			
				daoObj.setProcInValue(nProcIndex1, "gnum_isvalid", "1",29);							
				daoObj.setProcInValue(nProcIndex1, "gnum_hospital_code",vo.getStrHospCode(),30);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_isaccepted", "0",31);						
				daoObj.setProcInValue(nProcIndex1, "gnum_nurse_seatid","",32);			//				
				daoObj.setProcInValue(nProcIndex1, "hipnum_pat_from","",33);				//			
				daoObj.setProcInValue(nProcIndex1, "hipnum_ref_adm_no","",34);						//	
				daoObj.setProcOutValue(nProcIndex1, "err",1,35); // 1 for string return				
				daoObj.setProcOutValue(nProcIndex1, "adm_no",1,36);								
				daoObj.setProcInValue(nProcIndex1, "hrgstr_fathusbname", vo.getStrFatherName(),37);	
				daoObj.setProcInValue(nProcIndex1, "hrgstr_address",strAddress,38);					
				daoObj.setProcInValue(nProcIndex1, "hrgstr_city", vo.getStrCity(),39);					
				daoObj.setProcInValue(nProcIndex1, "gnum_state_code", vo.getStrState(),40);			
				daoObj.setProcInValue(nProcIndex1, "gnum_country_code",vo.getStrCountry(),41);			
				daoObj.setProcInValue(nProcIndex1, "hrgnum_relgn_code", vo.getStrReligion(),42);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_officetel", vo.getStrOccOffPhNo(),43);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_org_type", vo.getStrOccOrgType(),44);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_empno", vo.getStrOccEmpNo(),45);			
				daoObj.setProcInValue(nProcIndex1, "hipstr_empname", vo.getStrOccEmpName(),46);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_desig", vo.getStrOccDesc(),47);				
				daoObj.setProcInValue(nProcIndex1, "hipstr_officename", vo.getStrOccOffName(),48);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd1", vo.getStrOccAdd1(),49);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_officeadd2", vo.getStrOccAdd2(),50);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_officecity", vo.getStrOccCity(),51);		
				daoObj.setProcInValue(nProcIndex1, "hipstr_officestate", vo.getStrOccState(),52);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_isgovtemp", vo.getStrOccIsGovtEmp(),53);	
				daoObj.setProcInValue(nProcIndex1, "hipnum_basic", vo.getStrOccBasic(),54);			
				daoObj.setProcInValue(nProcIndex1, "gnum_desig_code","",55);				//			
				daoObj.setProcInValue(nProcIndex1, "hipdt_booking_dt", vo.getStrBookingDate(),56);		
				daoObj.setProcInValue(nProcIndex1, "hrgstr_house_no", strHouseNo,57);					
				daoObj.setProcInValue(nProcIndex1, "hrgstr_street_no", strStreet,58);					
				daoObj.setProcInValue(nProcIndex1, "hrgstr_district", vo.getStrDistrict(),59);			
				daoObj.setProcInValue(nProcIndex1, "hrgstr_phone_no", vo.getStrPhoneNo(),60);			
				daoObj.setProcInValue(nProcIndex1, "hrgstr_mobile_no", vo.getStrMobileNo(),61);		
				daoObj.setProcInValue(nProcIndex1, "hrgnum_pincode",vo.getStrPinCode(),62);			
				daoObj.setProcInValue(nProcIndex1, "hrgstr_ip_add", vo.getStrIpAddress(),63);			
				daoObj.setProcInValue(nProcIndex1, "hrgstr_state_name", vo.getStrStateName(),64);	//					
				daoObj.setProcInValue(nProcIndex1, "hrgstr_momname", vo.getStrMotherName(),65);		
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mother_puk", vo.getStrMotherCrNo(),66);		
				daoObj.setProcInValue(nProcIndex1, "hrgstr_moth_nationality", vo.getStrMotherNationalityCode(),67);
				daoObj.setProcInValue(nProcIndex1, "hrgnum_moth_relgn_code", vo.getStrMotherReligionCode(),68);
				daoObj.setProcInValue(nProcIndex1, "hipdt_admstatus_code", admitted,69);
				
				String tmp=vo.getStrAdmissionChargeValue()+"^"+vo.getStrPaymentModeValue().split("#")[0]+"^"+vo.getStrPayDetail();
				//vo.setStrAdmissionChargeValue(vo.getStrAdmissionChargeValue()+"^"+tmp+"^"+vo.getStrPayDetail());    //appending payment mode & dtl for admission charge
				
				
				
				daoObj.setProcInValue(nProcIndex1, "hipnum_charge", tmp,70);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_spousename", vo.getStrSpouseName(),71);		
				daoObj.setProcInValue(nProcIndex1, "pass_valid_for", vo.getStrFreePassValid(),72);		
				daoObj.setProcInValue(nProcIndex1, "no_of_free_pass",ipdConfig.getStrNoOfFreePassAdmisssionTime(),73);
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_type", vo.getStrBedTypeCode(),74);		
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_type", vo.getStrRoomTypeCode(),75);
				daoObj.setProcInValue(nProcIndex1, "adviceFlag", vo.getStrIsAdmissionOnline(),76);		
				daoObj.setProcInValue(nProcIndex1, "attendancePass", ipdConfig.getStrAttendentPassGenerateAtAdmissionTime(),77);
				String tmpStrSplitAge=vo.getStrHiddenPatDtl().replace("^", "#").split("#")[3].replace("/", "#").split("#")[0];
				if(tmpStrSplitAge.contains("Yr"))
				{
					if(Integer.parseInt(tmpStrSplitAge.replace("Yr", "").trim())>=Integer.parseInt(ipdConfig.getStrMinAgeToBeMother()))
						daoObj.setProcInValue(nProcIndex1, "maxBornAllowed", ipdConfig.getStrMaxNoOfBabyMotherCanBorn(),78);
					else
						daoObj.setProcInValue(nProcIndex1, "maxBornAllowed", "",78);		//			
				}
				else
				{
					daoObj.setProcInValue(nProcIndex1, "maxBornAllowed", "",78);			//			
				}				
				daoObj.setProcInValue(nProcIndex1, "billFlag", vo.getStrIsIntegratedWithBilling(),79);	
				daoObj.setProcInValue(nProcIndex1, "admissionChargeFlag",vo.getStrAdmissionCharge(),80);
				daoObj.setProcInValue(nProcIndex1, "gnum_caste_code",vo.getStrPatientCaste(),81);		
				daoObj.setProcInValue(nProcIndex1, "hrgstr_tehsil",vo.getStrTehsil(),82);				
				daoObj.setProcInValue(nProcIndex1, "gnum_marital_status_code",vo.getStrMaritalStatus(),83);
				daoObj.setProcInValue(nProcIndex1, "hgnum_relief_fund_code",vo.getStrReliefFund(),84);	
				daoObj.setProcInValue(nProcIndex1, "hgnum_admission_type_code", vo.getStrAdmissionType(),85);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_idmark", vo.getStrIdMark(),86);				
				daoObj.setProcInValue(nProcIndex1,"hrgstr_location", vo.getStrCityLocation(),87);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_emgaddress1", vo.getStrEmgAddress1(),88);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_emgaddress2", vo.getStrEmgAddress2(),89);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonname", vo.getStrFirstPersonName(),90);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonrelation", vo.getStrFirstPersonRelationCode(),91);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonname", vo.getStrSecondPersonName(),92);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonrelation", vo.getStrSecondPersonRelationCode(),93);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_fpersonphone", vo.getStrFirstpersonphone(),94);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_spersonphone", vo.getStrSecondpersonphone(),95);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge_required", vo.getStrAdmissionCharge(),96);
				daoObj.setProcInValue(nProcIndex1,"hrgstr_admission_charge", (vo.getStrAdmissionChargeValue().equals("0") ? "" :vo.getStrAdmissionChargeValue()),97);
				if(ipdConfig.getStrIntegrationBilling().equals("1"))
				daoObj.setProcInValue(nProcIndex1,"hrgstr_bill_flag", ipdConfig.getStrIntegrationBilling(),98);
				else if(vo.getStrIsSingleWindowAdmission().equals("1"))  //In single window Advance  collection on same desk
					daoObj.setProcInValue(nProcIndex1,"hrgstr_bill_flag","1",98);
				else
					daoObj.setProcInValue(nProcIndex1,"hrgstr_bill_flag","0",98);
				daoObj.setProcInValue(nProcIndex1,"hrgnum_casesheetno", vo.getStrCaseSheetNo(),99);
				daoObj.setProcInValue(nProcIndex1, "district_code", vo.getStrDistrictCode(),100);

				//daoObj.executeProcedureByPosition(nProcIndex1);
				daoObj.execute(nProcIndex1,1);
				/*err=daoObj.getString(nProcIndex1, "err");		
				String admno=daoObj.getString(nProcIndex1, "adm_no");
				System.out.println("admno"+admno);*/
			}			
			if(err.equals(""))
			{
				chkFlag=true;
				vo.setStrMsgType("0");
			}
			else
			{
				throw new Exception();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			chkFlag=false;
			vo.setStrMsgString("PatientAdmissionTransDAO.insertSingleWindowPatAdmission() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
		
		return chkFlag;
	}
	public static void getPaymentMode(PatientAdmissionTransVO vo) {
		// TODO Auto-generated method stub
		
		
		String strProcName ="{call pkg_bill_view.sblt_payment_category_mapping_mst(?,?,?,?,?,?,?,?)}";	
		
		int nProcIndex = 0;
		String val="0";
		String strErr="";
		WebRowSet ws=null;
	
		HisDAO daoObj = null;
		try 
		{
				daoObj = new HisDAO("ADT","PatientAdmissionTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
			//	nProcIndex = daoObj.setFunction(strProcName);
				

				
				daoObj.setProcInValue(nProcIndex,"modeval","1" ,1);
				daoObj.setProcInValue(nProcIndex,"hosp_code", vo.getStrHospCode() ,2);
				daoObj.setProcInValue(nProcIndex,"req_type","1",3);
				daoObj.setProcInValue(nProcIndex,"pat_category", vo.getStrPatCatCode() ,4);
				daoObj.setProcInValue(nProcIndex,"pat_reciept_paymode","",5);

				daoObj.setProcInValue(nProcIndex,"charge_id", "" ,6);
				daoObj.setProcOutValue(nProcIndex, "err",1,7); // 1 for string return				
				daoObj.setProcOutValue(nProcIndex, "resultset",2,8);	
				
				daoObj.executeProcedureByPosition(nProcIndex);
				
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					vo.setStrPaymentMode(ws);
				}
				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransDAO.getPaymentMode() --> "+ e.getMessage());
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
	
	
	public static void insertIpdViaAdmission(PatientAdmissionTransVO voP,HisDAO daoObj,boolean daoFireStatus)
	{
		
		NursingDeskTransVO voN=new NursingDeskTransVO();
		
		voN.setHiddenadmno(voP.getStrAdmNo());
		voN.setStrHospitalCode(voP.getStrHospCode());
		voN.setHiddencrno(voP.getStrCrNo());
		voN.setHiddenchkremark("accepted via Single Window");
		voN.setStrSeatId(voP.getStrSeatId());
		voN.setStrWard(voP.getStrWardCode());
		voN.setHiddenbed(voP.getStrBedCode());
		voN.setStrUnit(voP.getStrConsultantCode().replace("$", "#").split("#")[1]);
		voN.setStrDepartment(voP.getStrDeptCode());
		voN.setStrhmoveno("1");
		voN.setStrBillFlag("1");;
		voN.setStrBedFlag("0");
		voN.setStrConsultantCode(voP.getStrConsultantCode().replace("$", "#").split("#")[0]);
		voN.setStrhtransinflag("0");
		voN.setStrIsCancelMode(null);
		
		NursingDeskTransDAO.save(voN);
	}
}
