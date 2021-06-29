package ipd.transactions;


import java.util.ResourceBundle;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class  SlipReprintTransDAO {
	
	private static ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
	public static String admitted = hisProp.getString("Admitted");
	
	/**
	 * This function set ward type values in ward type combo on bed details pop up window
	 * @param vo
	 */
	public static void setWardTypeDtl(SlipReprintTransVO vo) {
		
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_ipd_view.PROC_HIPT_WARDTYPE_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		//WebRowSet web=null; 
		
		try {
				daoObj = new HisDAO("Patient Admission Modification Transaction",
					"PatientAdmissionModificationTransDAO");
                nProcIndex = daoObj.setProcedure(strProcName);
			    daoObj.setProcInValue(nProcIndex, "modeVal","1");
			    daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode());
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);
				daoObj.executeProcedure(nProcIndex);
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
			//e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setWardTypeDtl() --> "
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
	public static void setRoomTypeDtl(SlipReprintTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomtype_a(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		try {
				daoObj = new HisDAO("Patient Admission Modification Transaction",
					"PatientAdmissionModificationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","1");
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode());
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);
				daoObj.executeProcedure(nProcIndex);
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
			//e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setRoomTypeDtl() --> "
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
	public static void getBedStatusDtl(SlipReprintTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_ipd_bedstatus_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";
		try {
				daoObj = new HisDAO("Patient Admission Modification Transaction",
					"PatientAdmissionModificationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "pukno",vo.getStrCrNo());
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode());
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);
				daoObj.executeProcedure(nProcIndex);
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
			
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.getBedStatusDtl() --> "
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
	public static void getWardValues(SlipReprintTransVO vo)
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
			
			
			daoObj = new HisDAO("Admission Modification","PatientAdmissionModificationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal","5");
			daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode());
			daoObj.setProcInValue(nProcIndex, "wardtypcode",vo.getStrWardTypeCode());
			daoObj.setProcInValue(nProcIndex, "unitcode",vo.getStrAgeUnit());
			daoObj.setProcInValue(nProcIndex, "age",vo.getStrAge());
			daoObj.setProcInValue(nProcIndex, "gender",vo.getStrSexCode());
			daoObj.setProcInValue(nProcIndex, "treatment_cat",vo.getStrTreatmentCategoryCode());
			daoObj.setProcInValue(nProcIndex, "deptunitcode",vo.getStrDeptUnitCode());
			daoObj.setProcInValue(nProcIndex, "puk_no",vo.getStrCrNo());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "deptcode","0");			
			daoObj.setProcInValue(nProcIndex, "effect_from","");
			daoObj.setProcInValue(nProcIndex, "effect_to","");
			daoObj.setProcInValue(nProcIndex, "diseasetypcode","0");
			daoObj.setProcInValue(nProcIndex, "wardcode","0");
			daoObj.setProcInValue(nProcIndex, "charge_type_id","0");
			
			daoObj.executeProcedure(nProcIndex);
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
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.getWardValues() --> "
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
	public static void getRoomValues(SlipReprintTransVO vo)
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
				daoObj = new HisDAO("Patient Admission Transaction",
				"PatientAdmissionTransDAO");
				
			
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","3");
				daoObj.setProcInValue(nProcIndex, "roomtypcode",vo.getStrRoomTypeCode());
				daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode());
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode());				
				daoObj.setProcInValue(nProcIndex, "unitcode",vo.getStrAgeUnit());
				daoObj.setProcInValue(nProcIndex, "deptcode",vo.getStrDeptUnitCode().substring(0,3));
				daoObj.setProcInValue(nProcIndex, "deptunitcode",vo.getStrDeptUnitCode());
				daoObj.setProcInValue(nProcIndex, "age",vo.getStrAge());
				daoObj.setProcInValue(nProcIndex, "gender",vo.getStrSexCode());
				daoObj.setProcInValue(nProcIndex, "treatment_cat",vo.getStrTreatmentCategoryCode());
				daoObj.setProcInValue(nProcIndex, "puk_no",vo.getStrCrNo());
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);
				daoObj.setProcInValue(nProcIndex, "diseasetypcode","0");
				daoObj.executeProcedure(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				/*daoObj = new HisDAO("Patient Admission Modification Transaction",
				"PatientAdmissionModificationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval","1");
				daoObj.setProcInValue(nProcIndex, "roomtypcode",vo.getStrRoomTypeCode());
				daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode());
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode());
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.getRoomValues() --> "
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
	public static void setBedTypeDtl(SlipReprintTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_bed_type_mst(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Admission Modification Transaction",
					"PatientAdmissionModificationTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex,"modval","1");
			daoObj.setProcInValue(nProcIndex,"hosp_code",voObj.getStrHospCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);
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
			//e.printStackTrace();
			voObj.setStrMsgString("PatientAdmissionModificationTransDAO.setBedTypeDtl() --> "
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
	public static void getBedValues(SlipReprintTransVO vo)
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
			
			daoObj = new HisDAO("New Born Baby Admission  Transaction",
			"NewBornBabyTransDAO");
        nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "modval","1");
	    daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode());
	    daoObj.setProcInValue(nProcIndex, "roomno",vo.getStrRoom());
	    daoObj.setProcInValue(nProcIndex, "bedtypcode",vo.getStrBedTypeCode());
	    daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode());
	    daoObj.setProcInValue(nProcIndex, "deptunit",vo.getStrDeptUnitCode());
	    daoObj.setProcOutValue(nProcIndex, "err", 1);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2);
		
		daoObj.setProcInValue(nProcIndex, "bedstatcode","");
		daoObj.setProcInValue(nProcIndex, "bedCode","");
		daoObj.setProcInValue(nProcIndex, "shr_chk","");
		
		daoObj.executeProcedure(nProcIndex);
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
			
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.getBedValues() --> "
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
	public static void countBed_in_ward(SlipReprintTransVO vo)
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
        	daoObj.setProcInValue(nProcIndex, "modval","2");
        	daoObj.setProcInValue(nProcIndex, "wardcode",vo.getStrWardCode());
        	daoObj.setProcInValue(nProcIndex, "roomno",vo.getStrRoom());
        	daoObj.setProcInValue(nProcIndex, "bedtypcode",vo.getStrBedTypeCode());
        	daoObj.setProcInValue(nProcIndex, "bedstatcode","10");
        	daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode());
        	daoObj.setProcInValue(nProcIndex, "deptunit",vo.getStrDeptUnitCode());
        	daoObj.setProcOutValue(nProcIndex, "err", 1);
        	daoObj.setProcOutValue(nProcIndex, "resultset", 2);
        	
        	daoObj.setProcInValue(nProcIndex, "bedCode","");
        	daoObj.setProcInValue(nProcIndex, "shr_chk","");
        	
			daoObj.executeProcedure(nProcIndex);
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
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.countBed_in_ward() --> "
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
	public static void setTreat_CatName(SlipReprintTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_ipd_treatment_cat_name(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Patient Admission Modification Transaction",
					"PatientAdmissionModificationTransDAO");
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
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setTreat_CatName() --> "
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
	public static void setPatientStatus(SlipReprintTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_pat_status(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Patient Admission Modification Transaction",
					"PatientAdmissionModificationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval", "1");
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum);
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
							vo.setStrPatCatCode(ws.getString(1));
						vo.setStrIsUrban(ws.getString(2));
						vo.setStrPatStatusCode(ws.getString(3));
					}
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setPatientStatus() --> "
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
	public static void setConsultantName(SlipReprintTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_consultant(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		String strDeptUnitCode=vo.getStrDeptUnitCode();
		//System.out.println("strCrNum"+strCrNum);
	//	System.out.println("DEPTUNIT"+strDeptUnitCode);
		try {
				daoObj = new HisDAO("Patient Admission Modification Transaction",
					"PatientAdmissionModificationTransDAO");
				
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "PUKNO", strCrNum);
				daoObj.setProcInValue(nProcIndex, "DEPTUNIT", strDeptUnitCode);
				daoObj.setProcInValue(nProcIndex, "HOSP_CODE", vo.getStrHospCode());
				daoObj.setProcOutValue(nProcIndex, "ERR", 1);
				daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2);
				daoObj.executeProcedure(nProcIndex);
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
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setConsultantName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	// Getting Unit Consultants List (added on 31-Mar-2011 By Pragya)
	public static void setConsultantNameDtl(SlipReprintTransVO voObj) {

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
				daoObj.setProcInValue(nProcIndex, "modVal", "2",1);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
				
				//daoObj.setProcInValue(nProcIndex, "deptunitcode", "");
				daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrDeptUnitCode(),2);
				daoObj.setProcInValue(nProcIndex, "seatId", "",4);
			     
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

	public static boolean insert(SlipReprintTransVO vo)
	{
		
		boolean retVal2=false;
		HisDAO daoObj = null;
		try
		{
			daoObj = new HisDAO("Patient Admission Modification Transaction",
			"PatientAdmissionModificationTransDAO");
			retVal2=SlipReprintTransDAO.insertPatAdmission(daoObj, vo);
			
		}
		catch(Exception e)
		{
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.insert() --> "
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
	public static boolean insertPatAdmission(HisDAO daoObj,SlipReprintTransVO vo)
	{
		String strProcName1 ="{call pkg_ipd_dml.Proc_Patient_Adm_modify(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?  ,?,?,?,?)}"; // 4 parameter are added by Adil Wasi
		
		String err=new String();
		String admno="";
		admno=vo.getStrAdmNo();
		int nProcIndex1 = 0;
	
		String strAddress=vo.getStrHouseNo()+","+vo.getStrStreet()+","+vo.getStrCity();
		vo.setStrAddress(strAddress);
		String strHouseNo=vo.getStrHouseNo();
		String strStreet=vo.getStrStreet();
		boolean chkFlag=false;
		String deptCode="";
		 String deptUnitCode="";
		try
		{
				if(vo.getStrNewBorn().equals("1"))
				{
					deptCode=vo.getStrDeptNameNewBorn();
					deptUnitCode=vo.getStrUnitNewBorn();
				}
				else
				{
					deptCode=vo.getStrDeptCode();
					deptUnitCode=vo.getStrDeptUnitCode();
				}
			
				nProcIndex1 = daoObj.setProcedure(strProcName1);
				daoObj.setProcInValue(nProcIndex1, "hipnum_admno", vo.getStrAdmNo());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_puk", vo.getStrCrNo());
				//daoObj.setProcInValue(nProcIndex1, "hipnum_adm_advno", vo.getStrAdviceAdmNo());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_episode_code", vo.getStrEpisodeCode());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_visit_no", vo.getStrVisitNo());
				daoObj.setProcInValue(nProcIndex1, "gnum_dept_code", deptCode);
				daoObj.setProcInValue(nProcIndex1, "gnum_deptunit_code",deptUnitCode);
				daoObj.setProcInValue(nProcIndex1, "hipnum_ward_code", vo.getStrWardCode());
				daoObj.setProcInValue(nProcIndex1, "hipnum_room_code", vo.getStrRoomCode());
				daoObj.setProcInValue(nProcIndex1, "gnum_owndept_code", deptCode);
				daoObj.setProcInValue(nProcIndex1, "gnum_owndeptunit_code", deptUnitCode);
				daoObj.setProcInValue(nProcIndex1, "hipnum_ownward_code", vo.getStrWardCode());
				daoObj.setProcInValue(nProcIndex1, "hipnum_treat_categ_code", vo.getStrMotherTreatmentCateg());
				daoObj.setProcInValue(nProcIndex1, "hipnum_bed_code", vo.getStrBedCode());
				daoObj.setProcInValue(nProcIndex1, "hipnum_isrural", vo.getStrIsUrban());
				//daoObj.setProcInValue(nProcIndex1, "hipnum_isnewborn", vo.getStrNewBorn());
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
				//daoObj.setProcInValue(nProcIndex1, "hipdt_booking_dt", vo.getStrBookingDate());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_house_no", strHouseNo);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_street_no", strStreet);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_district", vo.getStrDistrict());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_phone_no", vo.getStrPhoneNo());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_mobile_no", vo.getStrMobileNo());
				daoObj.setProcInValue(nProcIndex1, "hrgnum_pincode", vo.getStrPinCode());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_ip_add", vo.getStrIpAddress());
				daoObj.setProcInValue(nProcIndex1, "hrgstr_state_name", vo.getStrState());
				//daoObj.setProcInValue(nProcIndex1, "hipnum_isaccepted", "0");
				daoObj.setProcInValue(nProcIndex1, "hipdt_admstatus_code", admitted);
				daoObj.setProcInValue(nProcIndex1, "hrgstr_spousename", vo.getStrSpouseName());
				daoObj.setProcOutValue(nProcIndex1, "err",1);
				
				daoObj.setProcInValue(nProcIndex1, "modval", "1");
				daoObj.setProcInValue(nProcIndex1, "hipnum_mothadmno", "");
				
				// Adding Consultant Code for modification (updated at 31-Mar-2011 By Pragya)
				//daoObj.setProcInValue(nProcIndex1, "hipnum_consultant_id", "");
				daoObj.setProcInValue(nProcIndex1, "hipnum_consultant_id", vo.getStrConsultantCode());
				daoObj.setProcInValue(nProcIndex1, "gnum_nurse_seatid", "");
				daoObj.setProcInValue(nProcIndex1, "hipnum_pat_from", "");
				daoObj.setProcInValue(nProcIndex1, "hipnum_ref_adm_no", "");
				daoObj.setProcInValue(nProcIndex1, "gnum_desig_code", "");
				daoObj.setProcInValue(nProcIndex1, "hrgstr_momname", "");
				daoObj.setProcInValue(nProcIndex1, "hrgnum_mother_puk", "");
				daoObj.setProcInValue(nProcIndex1, "hrgstr_moth_nationality", "");
				daoObj.setProcInValue(nProcIndex1, "hrgnum_moth_relgn_code", "");
				
				
				//below 4 lines are added by Adil Wasi
				daoObj.setProcInValue(nProcIndex1, "gnum_caste_code","");
				daoObj.setProcInValue(nProcIndex1, "hrgstr_tehsil","");
				daoObj.setProcInValue(nProcIndex1, "gnum_marital_status_code","");
				daoObj.setProcInValue(nProcIndex1, "hgnum_relief_fund_code","");
				
				daoObj.executeProcedure(nProcIndex1);
				err=daoObj.getString(nProcIndex1, "err");
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
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.insertPatAdmission() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
		
		return chkFlag;
	}
	/**
	 * This function is used to set all details of particular patient regarding to episode details on the main page in their corresponding hidden fields
	 * @param vo
	 */
	public static void setEpisodeDtl(SlipReprintTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_episode_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = vo.getStrCrNo();
		try
		{
			daoObj = new HisDAO("Admission Modification","PatientAdmissionModificationTransDAO");
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
				if(ws.size()==0)
				{
					vo.setStrValidCrNo("1");
				}
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
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setEpisodeDtl() --> "
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
	public static void setAdviceAdmNo(SlipReprintTransVO vo)
	{
		
		//System.out.println("welcome here advice stl");
		String strProcName = "{call pkg_ipd_view.proc_admission_advice_dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
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
			daoObj.setProcInValue(nProcIndex, "pukno", strCrNum);
			daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode());
			daoObj.setProcInValue(nProcIndex, "adv_frm_validity", vo.getStrAdmissionAdviceValidityFrom());
			daoObj.setProcInValue(nProcIndex, "adv_to_validity", vo.getStrAdmissionAdviceValidityTo());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			
			daoObj.setProcInValue(nProcIndex, "modval","1");
			daoObj.setProcInValue(nProcIndex, "patListType","0"); //--ONLINE ADVICE PATIENT LISTING
			daoObj.setProcInValue(nProcIndex, "searchStr","");
			daoObj.setProcInValue(nProcIndex, "searchType","1");
			daoObj.setProcInValue(nProcIndex, "toRows","0");
			daoObj.setProcInValue(nProcIndex, "frmRows","0");
	        
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			
			if (strErr.equals("")) {
					if (ws.next())
					{
						vo.setStrAdviceAdmNo(ws.getString(1));
						vo.setStrBookingDate(ws.getString(2));
						vo.setStrNewBorn(ws.getString(3));
					}
			} else {
					vo.setStrAdviceStatus("0");	
					throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setAdviceAdmNo() --> "
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
	public static void getPatientAdd(SlipReprintTransVO vo)
	{
		//String strProcName = "{call pkg_ipd_view.proc_pat_demographicdtl(?,?,?,?)}";
		String strProcName = "{call pkg_simple_view.proc_pat_demographicdtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum;
		strCrNum = vo.getStrCrNo();
		try
		{
			daoObj = new HisDAO("Patient Admission Modification",
			"PatientAdmissionModificationTransDAO");
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
				}
			}
			else
			{
				throw new Exception(strErr);
			}

		}
		catch(Exception e)
		{
			//e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.insertOccupationDtl() --> "
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
	public static void setReligion(SlipReprintTransVO vo)
	{

		String strProcName = "{call pkg_ipd_view.Proc_religion_dtl(?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Patient Admission Modification Transaction",
			"PatientAdmissionModificationTransDAO");
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
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setReligion() --> "
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
	public static void setState(SlipReprintTransVO vo)
	{

		String strProcName = "{call pkg_ipd_view.Proc_state_dtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Patient Admission Modification Transaction",
			"PatientAdmissionModificationTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval","1",1);
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
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setState() --> "
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
	public static void getPatDtl_Msapproval(SlipReprintTransVO vo) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_pat_adm_msapproval(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
				daoObj = new HisDAO("Patient Admission Modification Transaction",
					"PatientAdmissionModificationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeVal","1");
				daoObj.setProcInValue(nProcIndex, "pukno",vo.getStrCrNo());
				daoObj.setProcInValue(nProcIndex, "advno",vo.getStrAdviceAdmNo());
				daoObj.setProcInValue(nProcIndex, "bookingdate",vo.getStrBookingDate());
				daoObj.setProcInValue(nProcIndex, "hosp_code",vo.getStrHospCode());
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);
				daoObj.executeProcedure(nProcIndex);
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
				vo.setStrMsgString("PatientAdmissionModificationTransDAO.getPatDtl_Msapproval() --> "
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
	public static void setCountry(SlipReprintTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.Proc_state_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;
		try
		{
			daoObj = new HisDAO("Patient Admission Modification Transaction",
			"PatientAdmissionModificationTransDAO");
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
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setCountry() --> "
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
	public static void getMsApprovalStatus(SlipReprintTransVO vo)
	{
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
					//	System.out.println("Ms approval"+ws.getString(6));
						vo.setStrMsApprovalFlag(ws.getString(6));
						
					}
				
				} else {
							throw new Exception(strErr);
						}
			
		} catch (Exception e) {
				e.printStackTrace();
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
	 * This function is used to set inital parameters during new born baby case
	 * @param vo
	 */
	public static void setNewBornBabyDtl(SlipReprintTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_mother_dtl(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Patient Admission Modification Transaction",
					"PatientAdmissionModificationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum);
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
			
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setUnitName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static void department(SlipReprintTransVO vo) {

		String strproc_name = "{call pkg_ipd_view.Proc_Department(?,?,?,?,?,?)}";

		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try {

		dao = new HisDAO("ipd","transactions.PatientAdmissionModificationTransDAO");
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
			e.printStackTrace();
		vo.setStrMsgString("PatientAdmissionModificationTransDAO.department()--> "+ e.getMessage());

		vo.setStrMsgType("1");
		} finally {
		if (dao != null) {
		dao.free();
		dao = null;
		}
		}
		}
	// this function is used for unit combo on the basis of department

	public static void unit(SlipReprintTransVO vo) {

	String strproc_name = "{call pkg_ipd_view.Proc_Unit(?,?,?,?,?,?,?,?,?)}";

	HisDAO dao = null;
	int nprocIndex = 0;
	String strerr = "";
	WebRowSet ws = null;
	try {

	dao = new HisDAO("ipd","transactions.PatientAdmissionModificationTransDAO.unit()");

	nprocIndex = dao.setProcedure(strproc_name);
	// set value
	dao.setProcInValue(nprocIndex, "dept_code",vo.getStrDeptCode(),2);
	dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospCode(),3);
	dao.setProcOutValue(nprocIndex, "err", 1,8); // 1 for string return value
	dao.setProcOutValue(nprocIndex, "resultset", 2,9); // 2 for object
	
	dao.setProcInValue(nprocIndex, "modeval","1",1);
	dao.setProcInValue(nprocIndex, "puk","",4);
	dao.setProcInValue(nprocIndex, "seatId","",5);
	dao.setProcInValue(nprocIndex, "wardcode","",6);
	dao.setProcInValue(nprocIndex, "unitcode","",7);
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
		e.printStackTrace();
	vo.setStrMsgString("PatientAdmissionModificationTransDAO.unit() --> "+ e.getMessage());
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
	public static void setTreatmentCatDtl(SlipReprintTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call PKG_SIMPLE_VIEW.PROC_GBLT_PATIENT_CAT_MST(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("ipd","PatientAdmissionModificationTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1",1);
			daoObj.setProcInValue(nProcIndex, " hosp_code", voObj.getStrHospCode(),2);
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
			e.printStackTrace();
			voObj.setStrMsgString("PatientAdmissionModificationTransDAO.setTreatmentCatDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void setAdmDtl(SlipReprintTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_hipt_patadmission_dtl(?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Admission Modification","PatientAdmissionModificationTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeVal", "9",1);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospCode(),5);
				daoObj.setProcInValue(nProcIndex, "seatId","0",3);
				daoObj.setProcInValue(nProcIndex, "modifyTime","0",4);
				daoObj.setProcOutValue(nProcIndex, "err", 1,14);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,15);				
				
				daoObj.setProcInValue(nProcIndex, "patListType", "0",6);		// --ONLINE ADVICE PATIENT LISTING
				daoObj.setProcInValue(nProcIndex, "searchStr", "",7);
				daoObj.setProcInValue(nProcIndex, "searchType", "1",8);
				daoObj.setProcInValue(nProcIndex, "toRows", "0",9);
				daoObj.setProcInValue(nProcIndex, "frmRows", "0",10);
				daoObj.setProcInValue(nProcIndex, "onlinedis", "2",11);
				daoObj.setProcInValue(nProcIndex, "deptUnitCode", "",12);
				daoObj.setProcInValue(nProcIndex, "wardCode", "",13);

				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if(ws.size()==0)
				{
					vo.setStrModifyTimeStatus("1");
					
				}
				if (strErr.equals("")) {
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
						vo.setStrRoomTypeCode(ws.getString(17));
						vo.setStrAdviceAdmNo(ws.getString(18));
						vo.setStrBedTypeCode(ws.getString(19));
						vo.setStrTreatmentCategoryCode(ws.getString(20));
						vo.setStrTreatmentCategoryName(ws.getString(21));
						vo.setStrPatStatusCode(ws.getString(22));
						vo.setStrDeptUnitName(ws.getString(9)+"/"+ws.getString(10));
						
						// Fetching Consultant Code & Name (added at 31-Mar-2011 by Pragya)
						vo.setStrConsultantCode(ws.getString(23));
						vo.setStrConsultantName(ws.getString(24));
						vo.setStrAdmDateTime(ws.getString(25));
						
						}
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.setUnitName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void getStatusWhetherAdvanceAmountGiven(SlipReprintTransVO vo)
	{
		String strProcName = "{? = call BILL_INTERFACE.FUNC_ADVANCE_RECEIPT_DTL(?,?)}";
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
			
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransDAO.getStatusWhetherAdvanceAmountGiven() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
}