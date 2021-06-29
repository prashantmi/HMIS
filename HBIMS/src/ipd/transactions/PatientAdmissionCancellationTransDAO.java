package ipd.transactions;


import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class  PatientAdmissionCancellationTransDAO {
	
	/**
	 * This function is used to set Department name on main page
	 * @param vo
	 */
	public static void setDeptName(PatientAdmissionCancellationTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_dept_name(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
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
							vo.setStrDeptName(ws.getString(1));
					}

				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setDeptName() --> "
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
	 * This function is used to set unit name on main page
	 * @param vo
	 */
	public static void setUnitName(PatientAdmissionCancellationTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_deptunit_name(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Patient Admission Cancellation",
					"PatientAdmissionCancellationTransDAO");
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
			
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setUnitName() --> "
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
	 * This function set ward type values in ward type combo on bed details pop up window
	 * @param vo
	 */
	public static void setWardTypeDtl(PatientAdmissionCancellationTransVO vo) {
		
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.PROC_HIPT_WARDTYPE_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		
		try {
				daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
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
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setWardTypeDtl() --> "
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
	public static void setRoomTypeDtl(PatientAdmissionCancellationTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomtype_a(?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		try {
				daoObj = new HisDAO("Patient Admission Cancellation",
					"PatientAdmissionCancellationTransDAO");
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

			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setRoomTypeDtl() --> "
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
	public static void getBedStatusDtl(PatientAdmissionCancellationTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_ipd_bedstatus_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		try {
				daoObj = new HisDAO("Patient Admission Cancellation",
					"PatientAdmissionCancellationTransDAO");
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
			
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.getBedStatusDtl() --> "
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
	public static void getWardValues(PatientAdmissionCancellationTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
	    daoObj = new HisDAO("Patient Admission Transaction",
				"PatientAdmissionCancellationTransDAO.getWardValues()");
		try
		{
			
			daoObj = new HisDAO("Patient Admission Cancellation","PatientAdmissionCancellationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal","5",1);
			daoObj.setProcInValue(nProcIndex, "wardtypcode",vo.getStrWardTypeCode(),2);
			daoObj.setProcInValue(nProcIndex, "deptcode",vo.getStrDeptCode(),3);
			daoObj.setProcInValue(nProcIndex, "deptunitcode","0",4);
			daoObj.setProcInValue(nProcIndex, "unitcode","0",5);
			daoObj.setProcInValue(nProcIndex, "age","0",6);
			daoObj.setProcInValue(nProcIndex, "gender","0",7);
			daoObj.setProcInValue(nProcIndex, "treatment_cat","0",8);
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),9);
			daoObj.setProcInValue(nProcIndex, "effect_from","",10);
			daoObj.setProcInValue(nProcIndex, "effect_to","",11);
			daoObj.setProcInValue(nProcIndex, "diseasetypcode","0",12);
			daoObj.setProcInValue(nProcIndex, "wardcode","0",13);
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
					vo.setWardWS(ws);
			} else {
					throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.getWardValues() --> "
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
	public static void getRoomValues(PatientAdmissionCancellationTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

	daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
		try
		{
				daoObj = new HisDAO("Patient Admission Cancellation",
				"PatientAdmissionCancellationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","1",1);
				daoObj.setProcInValue(nProcIndex, "roomtypcode",vo.getStrRoomTypeCode(),2);
				daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),3);
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),4);
				daoObj.setProcInValue(nProcIndex, "unitcode","",5);
				daoObj.setProcInValue(nProcIndex, "age","",6);
				daoObj.setProcInValue(nProcIndex, "deptcode","",7);
				daoObj.setProcInValue(nProcIndex, "deptunitcode","",8);
				daoObj.setProcInValue(nProcIndex, "gender","",9);
				daoObj.setProcInValue(nProcIndex, "treatment_cat","",10);
				daoObj.setProcInValue(nProcIndex, "puk_no","",11);
				daoObj.setProcInValue(nProcIndex, "diseasetypcode","0",12);
				daoObj.setProcOutValue(nProcIndex, "err", 1,13);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,14);
				          
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
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.getRoomValues() --> "
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
	public static void setBedTypeDtl(PatientAdmissionCancellationTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_bed_type_mst(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");

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

			voObj.setStrMsgString("PatientAdmissionCancellationTransDAO.setBedTypeDtl() --> "
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
	public static void getBedValues(PatientAdmissionCancellationTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");		
		try
		{		

        nProcIndex = daoObj.setProcedure(strProcName);
		 daoObj.setProcInValue(nProcIndex, "modval","1",1);
	    daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),2);
	    daoObj.setProcInValue(nProcIndex, "roomno",vo.getStrRoom(),3);
	    daoObj.setProcInValue(nProcIndex, "bedtypcode",vo.getStrBedTypeCode(),4);
	    daoObj.setProcInValue(nProcIndex, "bedstatcode","",5);
	    daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),6);
	    daoObj.setProcInValue(nProcIndex, "deptunit","",7);
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
			
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.getBedValues() --> "
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
	public static void countBed_in_ward(PatientAdmissionCancellationTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
		try
		{
			nProcIndex = daoObj.setProcedure(strProcName);
        	daoObj.setProcInValue(nProcIndex, "modval","2",1);
        	daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode(),2);
        	daoObj.setProcInValue(nProcIndex, "roomno",vo.getStrRoom(),3);
        	daoObj.setProcInValue(nProcIndex, "bedtypcode",vo.getStrBedTypeCode(),4);
        	daoObj.setProcInValue(nProcIndex, "bedstatcode","10",5);
        	daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode(),6);
        	daoObj.setProcInValue(nProcIndex, "deptunit","",7);
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
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.countBed_in_ward() --> "
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
	public static void setTreat_CatName(PatientAdmissionCancellationTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_ipd_treatment_cat_name(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Admission Cancellation",
					"PatientAdmissionCancellationTransDAO");
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
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setTreat_CatName() --> "
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
	public static void setPatientStatus(PatientAdmissionCancellationTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_pat_status(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval","1",1);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if(ws.size()==0)
				{
					vo.setStrValidCrNo("1");
				}
				if (strErr.equals("")) {
				if (ws.next()) {
						vo.setStrPatCatCode(ws.getString(1));
						vo.setStrIsUrban(ws.getString(2));
						vo.setStrPatStatusCode(ws.getString(3));
						vo.setStrCheckForServiceAndAdvance(ws.getString(7));
						System.out.println("Advance and service "+vo.getStrCheckForServiceAndAdvance());
					}
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setPatientStatus() --> "
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
	 * This function is used to display consultant name on main page who give advice to patient
	 * @param vo
	 */
	public static void setConsultantName(PatientAdmissionCancellationTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_consultant(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		String strDeptUnitCode=vo.getStrDeptUnitCode();
		try {
				daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
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
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setConsultantName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static boolean insert(PatientAdmissionCancellationTransVO vo)
	{
		
		boolean retVal2=false;
		HisDAO daoObj = null;
		try
		{
			daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
			retVal2=PatientAdmissionCancellationTransDAO.insertPatAdmission(daoObj, vo);
			
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.insert() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
		return retVal2;
	}
	/**
	 * This function is used to insert record into database
	 * @param daoObj
	 * @param vo
	 * @return
	 */
	public static boolean insertPatAdmission(HisDAO daoObj,PatientAdmissionCancellationTransVO vo)
	{
		String strProcName1 ="{call pkg_ipd_dml.Proc_admission_cancellation(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex1=0;
		String err="";
		boolean chkFlag=false;
		try
		{
				nProcIndex1 = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex1, "modval","1",1);
				daoObj.setProcInValue(nProcIndex1, "admNo", vo.getStrAdmNo(),2);
				daoObj.setProcInValue(nProcIndex1, "hosp_code ", vo.getStrHospCode(),3);
				daoObj.setProcInValue(nProcIndex1, "puk", vo.getStrCrNo(),4);
				daoObj.setProcInValue(nProcIndex1, "seatId", vo.getStrSeatId(),5);
				daoObj.setProcInValue(nProcIndex1, "cancelBy", vo.getStrConsultantName(),6);
				daoObj.setProcInValue(nProcIndex1, "cancelReason", vo.getStrRemarks(),7);
				daoObj.setProcInValue(nProcIndex1, "billFlag", vo.getStrBillFlag(),8);
				daoObj.setProcInValue(nProcIndex1, "advanceRefund", vo.getStrAdvanceRefund(),9);
				daoObj.setProcInValue(nProcIndex1, "paymode", "1",10);
				daoObj.setProcInValue(nProcIndex1, "paydetail", "",11);
				daoObj.setProcInValue(nProcIndex1, "status", vo.getStatus(),12);
				daoObj.setProcOutValue(nProcIndex1, "err", 1,13);
				daoObj.executeProcedureByPosition(nProcIndex1);
				err=daoObj.getString(nProcIndex1, "err");
						
				if(err.equals(""))
				{
				
				}
				else
				{
					throw new Exception();
				}
		}
		catch(Exception e)
		{
			
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.insertPatAdmission() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
		
		return chkFlag;
	}
	/**
	 * This function is used to set all details of particular patient regarding to episode details on the main page in their corresponding hidden fields
	 * @param vo
	 */
	public static void setEpisodeDtl(PatientAdmissionCancellationTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_episode_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try
		{
			daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
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
			if (strErr.equals("")) {
					if (ws.next())
					{
						vo.setStrEpisodeCode(ws.getString(1));
						vo.setStrVisitNo(ws.getString(2));
						vo.setStrMlcNo(ws.getString(3));
					}

			} else {
				throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setEpisodeDtl() --> "
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
	public static void setAdviceAdmNo(PatientAdmissionCancellationTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_admission_advice_dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try
		{
			daoObj = new HisDAO("Admission Cancellation","PatientAdmissionModificationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum,2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
			daoObj.setProcInValue(nProcIndex, "adv_frm_validity", "0",4);
			daoObj.setProcInValue(nProcIndex, "adv_to_validity", "0",5);
			daoObj.setProcInValue(nProcIndex, "patListType", "0",6);//--ONLINE ADVICE PATIENT LISTING
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
			if(ws.size()==0)
			{
				vo.setStrAdviceStatus("0");
			}
			else
				vo.setStrAdviceStatus("1");
			if (strErr.equals("")) {
					if (ws.next())
					{
						vo.setStrAdviceAdmNo(ws.getString(1));
						vo.setStrBookingDate(ws.getString(2));
						vo.setStrNewBorn(ws.getString(3));
					}
			} else {
					throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setAdviceAdmNo() --> "
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
	 * This function is used to bring address of a patient on the main page
	 * @param vo
	 */
	public static void getPatientAdd(PatientAdmissionCancellationTransVO vo)
	{
		//String strProcName = "{call pkg_ipd_view.(?,?,?,?)}";
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
			daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
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
					vo.setStrPatientName(ws.getString(3));
					vo.setStrFatherName(ws.getString(4));
					vo.setStrReligionCode(ws.getString(5));
					vo.setStrHouseNo(ws.getString(6));
					vo.setStrStreet(ws.getString(7));
					vo.setStrPhoneNo(ws.getString(8));
					vo.setStrMobileNo(ws.getString(9));
					vo.setStrCity(ws.getString(10));
					vo.setStrDistrict(ws.getString(11));
					vo.setStrStateCode(ws.getString(12));
					vo.setStrCountryCode(ws.getString(13));
					vo.setStrPinCode(ws.getString(14));
					vo.setStrSpouseName(ws.getString(15));
					vo.setStrReligoinName(ws.getString(16));
					vo.setStrCountryName(ws.getString(17));
					vo.setStrStateName(ws.getString(18));
				}
			}
			else
			{
				throw new Exception(strErr);
			}

		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.insertOccupationDtl() --> "
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
	public static void setReligion(PatientAdmissionCancellationTransVO vo)
	{

		String strProcName = "{call pkg_ipd_view.Proc_religion_dtl(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),1);
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
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setReligion() --> "
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
	public static void setState(PatientAdmissionCancellationTransVO vo)
	{

		String strProcName = "{call pkg_ipd_view.Proc_state_dtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),2);
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
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setState() --> "
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
	public static void getPatDtl_Msapproval(PatientAdmissionCancellationTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_pat_adm_msapproval(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
				daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeVal","1",1);
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
					if(ws.next())
					{
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
				vo.setStrMsgString("PatientAdmissionCancellationTransDAO.getPatDtl_Msapproval() --> "
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
	public static void setCountry(PatientAdmissionCancellationTransVO vo)
	{

		String strProcName = "{call pkg_ipd_view.Proc_state_dtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval", "2",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),2);
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
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setCountry() --> "
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
	public static void getMsApprovalStatus(PatientAdmissionCancellationTransVO vo)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		daoObj = new HisDAO("Admission Cancellation","PatientAdmissionModificationTransDAO");
		try
		{
			daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
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
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.getWardValues() --> "
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
	public static void setNewBornBabyDtl(PatientAdmissionCancellationTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_mother_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Admission Cancellation",
					"PatientAdmissionCancellationTransDAO");
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
						vo.setStrTreatmentCategoryCode(ws.getString(10));
						
					}
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setUnitName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static void department(PatientAdmissionCancellationTransVO vo) {

		String strproc_name = "{call pkg_ipd_view.Proc_Department(?,?,?,?,?,?)}";

		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {

		dao = new HisDAO("ipd","transactions.PatientAdmissionCancellationTransDAO");

		nprocIndex = dao.setProcedure(strproc_name);
		// set value
		dao.setProcInValue(nprocIndex, "modeval","1",1);
		dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospCode(),2);
		dao.setProcInValue(nprocIndex, "puk","",3);
		dao.setProcInValue(nprocIndex, "seatId","",4);
		dao.setProcOutValue(nprocIndex, "err", 1,5); // 1 for string return value
		dao.setProcOutValue(nprocIndex, "resultset", 2,6); // 2 for object

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
		vo.setStrMsgString("PatientAdmissionCancellationTransDAO.department()--> "+ e.getMessage());
		vo.setStrMsgType("1");
		} finally {
		if (dao != null) {
		dao.free();
		dao = null;
		}
		}
		}
	// this function is used for unit combo on the basis of department

	public static void unit(PatientAdmissionCancellationTransVO vo) {

	String strproc_name = "{call pkg_ipd_view.Proc_Unit(?,?,?,?,?,?,?)}";

	HisDAO dao = null;
	int nprocIndex = 0;
	String strerr = "";
	WebRowSet ws = null;
	try {

	dao = new HisDAO("ipd","transactions.PatientAdmissionCancellationTransDAO.unit()");

	nprocIndex = dao.setProcedure(strproc_name);
	// set value
	dao.setProcInValue(nprocIndex, "modeval","1",1);
	dao.setProcInValue(nprocIndex, "dept_code",vo.getStrMotherDeptCode(),2);
	dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospCode(),3);
	dao.setProcInValue(nprocIndex, "puk", "",4);
	dao.setProcInValue(nprocIndex, "seatId","",5);
	dao.setProcInValue(nprocIndex, "wardcode", "",6);
	dao.setProcInValue(nprocIndex, "unitcode","",7);
	dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
	dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object

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
	vo.setStrMsgString("PatientAdmissionCancellationTransDAO.unit() --> "+ e.getMessage());
	vo.setStrMsgType("1");
	} finally {
	if (dao != null) {
	dao.free();
	dao = null;
	}
	}
	}
	/**
	 * This procedure is used to set intial information for treatment category combo
	 * @param voObj 
	 */
	public static void setTreatmentCatDtl(PatientAdmissionCancellationTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call PKG_SIMPLE_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("ipd","PatientAdmissionCancellationTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1");
			daoObj.setProcInValue(nProcIndex, " hosp_code", voObj.getStrHospCode());
			daoObj.setProcInValue(nProcIndex, "puk_no", "");
			daoObj.setProcInValue(nProcIndex, "charge_type_id", "0");
         daoObj.setProcInValue(nProcIndex, "effect_from", "");
			daoObj.setProcInValue(nProcIndex, "effect_TO", "");
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
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
			
			voObj.setStrMsgString("PatientAdmissionCancellationTransDAO.setTreatmentCatDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	/**
	 * This function is used to bring admission details on the main screen
	 * @param vo
	 */
	public static void setAdmDtl(PatientAdmissionCancellationTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_hipt_patadmission_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try 
		{
				daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval", "3",1);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
				daoObj.setProcInValue(nProcIndex, "seatId","",3);
				daoObj.setProcInValue(nProcIndex, "modifyTime","0",4);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),5);
				daoObj.setProcInValue(nProcIndex, "patListType", "0",6); // --ONLINE ADVICE PATIENT LISTING
				daoObj.setProcInValue(nProcIndex, "searchStr", "",7);
				daoObj.setProcInValue(nProcIndex, "searchType", "1",8);
				daoObj.setProcInValue(nProcIndex, "toRows", "0",9);
				daoObj.setProcInValue(nProcIndex, "frmRows", "0",10);
				daoObj.setProcInValue(nProcIndex, "onlinedis", "2",11);//online discharge
				daoObj.setProcInValue(nProcIndex, "deptUnitCode", "0",12);
				daoObj.setProcInValue(nProcIndex, "wardCode", "0",13);
				daoObj.setProcOutValue(nProcIndex, "err", 1,14);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,15);
				
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if(ws.size()==0)
				{
					vo.setStrIsNotAdmitted("1");
				}
				if (strErr.equals("")) 
				{
					if(ws.next())
					{
						vo.setStrDeptCode(ws.getString(2));
						vo.setStrDeptUnitCode(ws.getString(3));
						vo.setStrWardCode(ws.getString(4));
						vo.setStrTreatmentCategoryCode(ws.getString(5));
						vo.setStrNewBorn(ws.getString(6));
						vo.setStrDeptName(ws.getString(9));
						vo.setStrUnitName(ws.getString(10));
						vo.setStrWardName(ws.getString(11));
						vo.setStrWardTypeCode(ws.getString(13));
						vo.setStrAdmNo(ws.getString(14));
						vo.setStrRoomCode(ws.getString(15));
						vo.setStrRoom(ws.getString(16));
						vo.setStrIsAccepted(ws.getString(17));
						vo.setStrTreatmentCategoryCode(ws.getString(18));
						vo.setStrTreatmentCategoryName(ws.getString(19));
						vo.setStrAdmissionCharge(ws.getString(20));
						vo.setStrAdmissionChargeValue(ws.getString(21));						
					}
				} 
				else 
				{
					throw new Exception(strErr);
				}
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setUnitName() --> "+ e.getMessage());
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
	public static void setConsultantNameDtl(PatientAdmissionCancellationTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String deptUnitCode = voObj.getStrDeptUnitCode();

		String strErr = "";
		try {
			daoObj = new HisDAO("Admission Cancellation","PatientAdmissionCancellationTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			
			
			
			

			if (deptUnitCode != null) 
			{
				
				if (deptUnitCode != null && !deptUnitCode.equals("0") || deptUnitCode.equals("")) 
				{
					daoObj.setProcInValue(nProcIndex, "modVal", "1",1);
					daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrDeptUnitCode(),2);
				}
				else
				{
					daoObj.setProcInValue(nProcIndex, "modVal", "5",1);
					daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrDeptCode(),2);
				}
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),3);
				daoObj.setProcInValue(nProcIndex, "seatId", "",4);
				daoObj.setProcOutValue(nProcIndex, "err", 1,5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
				
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

			voObj.setStrMsgString("PatientAdmissionCancellationTransDAO.setConsultantNameDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	public static void setAdvanceDtl(PatientAdmissionCancellationTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_advance_details(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try 
		{
				daoObj = new HisDAO("ADT","PatientAdmissionCancellationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
				daoObj.setProcInValue(nProcIndex, "crno", strCrNum,2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),3);
				daoObj.setProcInValue(nProcIndex, "admno", vo.getStrAdmNo(),4);
				daoObj.setProcOutValue(nProcIndex, "err", 1,5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
				
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if(ws.size()==0)
				{
					vo.setStrAdvanceDepositStatus("0");
				}
				if (strErr.equals("")) 
				{
					if(ws.next())
					{
						vo.setStrAdvanceDepositStatus("1");
						vo.setStrAdvanceDetails(ws.getString(3));
					}
				} 
				else 
				{
					throw new Exception(strErr);
				}
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("PatientAdmissionCancellationTransDAO.setAdvanceDtl() --> "+ e.getMessage());
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