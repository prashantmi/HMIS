package ipd.transactions;

import ipd.IpdConfigUtil;

//import java.util.ResourceBundle;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import javax.sql.rowset.WebRowSet;

public class PatientLeaveJoinRecordTransDAO
{
// this function is used for Department code and a name
/*	public static void department(PatientLeaveJoinRecordTransVO voObj) {
		String strproc_name = "{call Proc_Department(?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
		try {
			dao = new HisDAO("ipd","transactions.PatientLeaveJoinRecordTransDAO.department()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospCode());
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");
			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			System.out.println("afer calling ws");
			if (strerr.equals("")) {
				voObj.setStrMsgType("0");
				voObj.setStrDepartmenttWs(ws);
			} else {
				throw new Exception(strerr);
			}
	} catch (Exception e) {

		voObj.setStrErrMsgString("PatientLeaveJoinRecordTransTransDAO.department()--> "
				+ e.getMessage());
		voObj.setStrMsgType("1");
	} finally {
		if (dao != null) {
			dao.free();
			dao = null;
		}
	}
	}

	// this function is used for unit combo on the basis of department
	public static void unit(PatientLeaveJoinRecordTransVO voObj) {
		String strproc_name = "{call Proc_Unit(?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;
        System.out.println("DEPT CODE===="+voObj.getStrDepartment());
		try {
			dao = new HisDAO("ipd",
					"transactions.PatientLeaveJoinRecordTransDAO.unit()");
			nprocIndex = dao.setProcedure(strproc_name);
			// set value
			dao.setProcInValue(nprocIndex, "dept_code",voObj.getStrDepartment());
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHospCode());
			dao.setProcOutValue(nprocIndex, "err", 1); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "resultset", 2); // 2 for object
			// execute procedure
			dao.executeProcedure(nprocIndex);
			// get value
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
			System.out.println("afer calling wsSize->"+ws.size());
			if (strerr.equals("")) {
				voObj.setStrMsgType("0");
				voObj.setStrUnitWs(ws);
			} else {
				throw new Exception(strerr);
			}
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.unit() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
	}*/

	/**
	 * This function is used to set Department name on main page
	 * @param voObj
	 */
/*	public static void setDeptName(PatientLeaveJoinRecordTransVO voObj)
	{
		String strProcName = "{call pkg_ipd_view.proc_dept_name(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = voObj.getStrCrNo();
		try {
				daoObj = new HisDAO("Patient Admission",
					"PatientLeaveJoinRecordTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "pukno", strCrNum);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode());
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);
				daoObj.executeProcedure(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					voObj.setStrMsgType("0");
					if (ws.next()) {
						voObj.setStrDeptName(ws.getString(1));
					}
				} else {
					throw new Exception(strErr);
				}

		} catch (Exception e) {
			
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.setDeptName() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}*/
	/**
	 * This function is used to set unit name on main page
	 * @param voObj
	 */
/*	public static void setUnitName(PatientLeaveJoinRecordTransVO voObj)
	{
		String strProcName = "{call pkg_ipd_view.proc_deptunit_name(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strCrNum = voObj.getStrCrNo();
		try {
			daoObj = new HisDAO("Patient Admission",
					"PatientLeaveJoinRecordTransDAO");
			    nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "pukno", strCrNum);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode());
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);
				daoObj.executeProcedure(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					voObj.setStrMsgType("0");
					if (ws.next()) {
						voObj.setStrUnitName(ws.getString(1));
						voObj.setStrDeptUnitCode(ws.getString(2));
					}
				} else {
					throw new Exception(strErr);
				}
	} catch (Exception e) {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.setUnitName() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}*/
	/**
	 * This function set ward type values in ward type combo on bed details pop up window
	 * @param voObj
	 */
	/*public static void setWardTypeDtl(PatientLeaveJoinRecordTransVO voObj) {
		HisDAO daoObj = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveJoinRecordTransDAO");
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.PROC_HIPT_WARDTYPE_MST(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
				daoObj = new HisDAO("Patient Leaveaction","PatientLeaveJoinRecordTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
			    daoObj.setProcInValue(nProcIndex, "modeVal","1");
			    daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode());
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);
				daoObj.executeProcedure(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
                ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					voObj.setWardTypeWS(ws);
					voObj.setStrMsgType("0");
					// System.out.println("wardType ws->"+ws.size());
					temp=util.getOptionValue(ws,"0","0^Select Value", false);
					voObj.setStrwardType(temp);
					//System.out.println("wardTypeStr->"+temp);
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.setWardTypeDtl() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}*/
	/**
	 * This function is used to details in room type combo on bed status pop up window
	 * @param voObj
	 */
	public static void setRoomTypeDtl(PatientLeaveJoinRecordTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomtype_a(?,?,?,?)}";
		int nProcIndex = 0;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveJoinRecordTransDAO");
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Admission Transaction",
					"PatientLeaveJoinRecordTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modval","1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				voObj.setRoomTypeWS(ws);
				temp=util.getOptionValue(ws,"0","0^Select Value", false);
				voObj.setStrRoomType(temp);
				voObj.setStrMsgType("0");
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.setRoomTypeDtl() --> "
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
	public static void getBedStatusDtl(PatientLeaveJoinRecordTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_ipd_bedstatus_dtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Admission Transaction",
					"PatientLeaveJoinRecordTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "pukno",voObj.getStrCrNo(),1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			if (strErr.equals("")) {
				voObj.setStrMsgType("0");
					if(ws.next())
					{
						voObj.setStrWardTypeCode(ws.getString(1));
						voObj.setStrWardCode(ws.getString(2));
						voObj.setStrBedTypeCode(ws.getString(3));
						voObj.setStrRoomTypeCode(ws.getString(4));
						voObj.setStrWardName(ws.getString(5));
						voObj.setStrDeptCode(ws.getString(6));
						voObj.setStrMsApprovalFlag(ws.getString(7));
					}

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.getBedStatusDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
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
	 * @param voObj
	 */
/*	public static void getWardValues(PatientLeaveJoinRecordTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveJoinRecordTransDAO");
		String strProcName = "{call pkg_ipd_view.proc_HIPT_WARD_MST(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
    	daoObj = new HisDAO("Patient Leaveaction",
				"PatientLeaveJoinRecordTransDAO.getWardValues()");
		try
		{
			daoObj = new HisDAO("Patient Leave",
			"PatientLeaveJoinRecordTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal","8");
			daoObj.setProcInValue(nProcIndex, "wardtypcode",voObj.getStrWardTypeCode());
			daoObj.setProcInValue(nProcIndex, "deptcode",voObj.getStrDeptCode());
			daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrDeptUnitCode());
			daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode());
			daoObj.setProcOutValue(nProcIndex, "err", 1);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			daoObj.executeProcedure(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
			temp=util.getOptionValue(ws,"0","0^Select Value", false);
			voObj.setStrWard(temp);
			if (strErr.equals("")) {
				voObj.setStrMsgType("0");
				voObj.setWardWS(ws);
			} else {
					throw new Exception(strErr);
				}
		}
		catch(Exception e)
		{
			
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.getWardValues() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}*/
	/**
	 * This function is used to bring room details in room combo on the basis of room type code
	 * @param voObj
	 */
	public static void getRoomValues(PatientLeaveJoinRecordTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveJoinRecordTransDAO");
		String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
	    daoObj = new HisDAO("Patient Admission Transaction",
				"PatientLeaveJoinRecordTransDAO.getRoomValues()");
		try
		{
			daoObj = new HisDAO("Patient Leave",
			"PatientLeaveJoinRecordTransDAO");
        nProcIndex = daoObj.setProcedure(strProcName);
		daoObj.setProcInValue(nProcIndex, "modval","3",1);
	    daoObj.setProcInValue(nProcIndex, "roomtypcode",voObj.getStrRoomTypeCode(),2);
	    daoObj.setProcInValue(nProcIndex, "wardcode",voObj.getStrWardCode(),3);
	    daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode(),4);
		daoObj.setProcInValue(nProcIndex, "unitcode",voObj.getStrUnitCode(),5);
		daoObj.setProcInValue(nProcIndex, "age",voObj.getStrAge(),6);
		daoObj.setProcInValue(nProcIndex, "deptcode","0",7);
		daoObj.setProcInValue(nProcIndex, "deptunitcode",voObj.getStrUnitValue(),8);
		daoObj.setProcInValue(nProcIndex, "gender",voObj.getStrSex(),9);
		daoObj.setProcInValue(nProcIndex, "treatment_cat",voObj.getStrTreatmentCategoryCode(),10);
		daoObj.setProcInValue(nProcIndex, "puk_no",voObj.getStrCrNo(),11);
		daoObj.setProcInValue(nProcIndex, "diseasetypcode","0",12);
	    daoObj.setProcOutValue(nProcIndex, "err", 1,13);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,14);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		if (strErr == null)
			strErr = "";
        ws = daoObj.getWebRowSet(nProcIndex, "resultset");
    	temp=util.getOptionValue(ws,"0","0^Select Value", false);
		voObj.setStrRoom(temp);
        if (strErr.equals("")) {
        	voObj.setStrMsgType("0");
        	voObj.setRoomWs(ws);
		} else {
			throw new Exception(strErr);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.getRoomValues() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
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
	public static void setBedTypeDtl(PatientLeaveJoinRecordTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveJoinRecordTransDAO");
		String strProcName = "{call pkg_ipd_view.proc_bed_type_mst(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Leave","PatientLeaveJoinRecordTransDAO");
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
			temp=util.getOptionValue(ws,"0","0^Select Value", false);
			voObj.setStrBedType(temp);
			if (strErr.equals("")) {
				voObj.setStrMsgType("0");
				voObj.setBedTypeWS(ws);
			} else {
				throw new Exception(strErr);
			}
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.setBedTypeDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	/**
	 * This function is used to bring the bed Values in bed combo
	 * @param voObj
	 */
	public static void getBed(PatientLeaveJoinRecordTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveJoinRecordTransDAO");
		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
	    daoObj = new HisDAO("Patient Leave Transaction","PatientLeaveJoinRecordTransDAO");
		try
		{
		  daoObj = new HisDAO("Patient Leave  Transaction","PatientLeaveJoinRecordTransDAO");
          nProcIndex = daoObj.setProcedure(strProcName);
		  daoObj.setProcInValue(nProcIndex, "modval","5",1);
	      daoObj.setProcInValue(nProcIndex, "wardcode",voObj.getStrWardCode(),2);
	      daoObj.setProcInValue(nProcIndex, "roomno",voObj.getStrRoomCode(),3);
	      daoObj.setProcInValue(nProcIndex, "bedtypcode",voObj.getStrBedTypeCode(),4);
	      daoObj.setProcInValue(nProcIndex, "bedstatcode","10",5);
	      daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode(),6);
	      daoObj.setProcInValue(nProcIndex, "deptunit",voObj.getStrDeptUnitCode(),7);
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
        	  voObj.setStrMsgType("0");
			voObj.setBedDetailWS(ws);
		  } else {
			 throw new Exception(strErr);
		  }
          if(voObj.getStrPopUp().equals("0"))
          {
            temp=util.getOptionValue(ws,"0","0^Select Value", false);
            voObj.setStrBed(temp);
          }
		}
		catch(Exception e)
		{
		  voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.getBedValues() --> "+ e.getMessage());
		  voObj.setStrMsgType("1");
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	/**
	 * This function is used to bring the bed details on bed details pop up window on the basis of ward code,room number,bed type code
	 * @param voObj
	 */
	public static void getBedValues(PatientLeaveJoinRecordTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveJoinRecordTransDAO");
		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
	    daoObj = new HisDAO("Patient Leave Transaction","PatientLeaveJoinRecordTransDAO");
		try
		{
		  daoObj = new HisDAO("Patient Leave  Transaction","PatientLeaveJoinRecordTransDAO");
          nProcIndex = daoObj.setProcedure(strProcName);
		  daoObj.setProcInValue(nProcIndex, "modval","4",1);
	      daoObj.setProcInValue(nProcIndex, "wardcode",voObj.getStrWardCode(),2);
	      daoObj.setProcInValue(nProcIndex, "roomno",voObj.getStrRoomCode(),3);
	      daoObj.setProcInValue(nProcIndex, "bedtypcode",voObj.getStrBedTypeCode(),4);
	      daoObj.setProcInValue(nProcIndex, "bedstatcode","",5);
	      daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode(),6);
	      daoObj.setProcInValue(nProcIndex, "deptunit",voObj.getStrDeptUnitCode(),7);
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
        	  voObj.setStrMsgType("0");
			voObj.setBedDetailWS(ws);
		  } else {
			 throw new Exception(strErr);
		  }
          if(voObj.getStrPopUp().equals("0"))
          {
            temp=util.getOptionValue(ws,"0","0^Select Value", false);
            voObj.setStrBed(temp);
          }
		}
		catch(Exception e)
		{
		  voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.getBedValues() --> "+ e.getMessage());
		  voObj.setStrMsgType("1");
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

/*	public static void getServRoomValues(PatientLeaveJoinRecordTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveJoinRecordTransDAO");
		String strProcName = "{call pkg_ipd_view.Proc_Hipt_Service_Dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
	    daoObj = new HisDAO("Patient Leave Transaction","PatientLeaveJoinRecordTransDAO");
	    System.out.println("servAreaCode->"+voObj.getStrServAreaCode());
		try
		{
			  daoObj = new HisDAO("Patient Leave  Transaction","PatientLeaveJoinRecordTransDAO");
	          nProcIndex = daoObj.setProcedure(strProcName);
			  daoObj.setProcInValue(nProcIndex, "modeVal","2");
		      daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode());
		      daoObj.setProcInValue(nProcIndex, "service_code",voObj.getStrServAreaCode());
		      daoObj.setProcOutValue(nProcIndex, "err", 1);
			  daoObj.setProcOutValue(nProcIndex, "resultset", 2);
			  daoObj.executeProcedure(nProcIndex);
			  strErr = daoObj.getString(nProcIndex, "err");
		if (strErr == null)
			strErr = "";
        ws = daoObj.getWebRowSet(nProcIndex, "resultset");
        if (strErr.equals("")) {
        	voObj.setStrMsgType("0");
			voObj.setStrRoomWs(ws);
		} else {
			throw new Exception(strErr);
		}
        temp=util.getOptionValue(ws,"0","0^Select Value", false);
        voObj.setStrRoom(temp);
		}
		catch(Exception e)
		{
		voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.getServRoomValues() -->"
					+ e.getMessage());
			voObj.setStrMsgType("1");
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

	public static void setServAreaName(PatientLeaveJoinRecordTransVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveJoinRecordTransDAO");
		String strProcName = "{call pkg_ipd_view.Proc_Hipt_Service_Dtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
    	daoObj = new HisDAO("Patient Leave Transaction","PatientLeaveJoinRecordTransDAO");
		try
		{
		  daoObj = new HisDAO("Patient Leave  Transaction","PatientLeaveJoinRecordTransDAO");
          nProcIndex = daoObj.setProcedure(strProcName);
		  daoObj.setProcInValue(nProcIndex, "modeVal","1");
	      daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode());
	      daoObj.setProcOutValue(nProcIndex, "err", 1);
		  daoObj.setProcOutValue(nProcIndex, "resultset", 2);
		  daoObj.executeProcedure(nProcIndex);
		  strErr = daoObj.getString(nProcIndex, "err");
		  if (strErr == null)
			strErr = "";
          ws = daoObj.getWebRowSet(nProcIndex, "resultset");
          if (strErr.equals("")) {
        	  voObj.setStrMsgType("0");
			voObj.setStrServAreaWS(ws);
		   } else {
			 throw new Exception(strErr);
		   }
           temp=util.getOptionValue(ws,"0","0^Select Value", false);
           voObj.setStrServArea(temp);
		}
		catch(Exception e)
		{
		   voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.setServAreaName() --> "+ e.getMessage());
		   voObj.setStrMsgType("1");
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}*/

	/**
    This function is used to set comsultant code and consultant name on the behalf of puk no
    **/
  public static void setConsultantName(PatientLeaveJoinRecordTransVO voObj)
  {
	String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
    int nProcIndex = 0;
    String strErr = "";
    HisDAO daoObj = null;
    WebRowSet ws = null;
    String temp="";
	HisUtil  util=null;
	util = new HisUtil("ipd","PatientLeaveJoinRecordTransDAO");
    String strDeptUnitCode=voObj.getStrDeptUnitCode();
    try {
      daoObj = new HisDAO("Patient Leave","PatientLeaveJoinRecordTransDAO");
      nProcIndex = daoObj.setProcedure(strProcName);
      daoObj.setProcInValue(nProcIndex, "modVal", "1",1);
      daoObj.setProcInValue(nProcIndex, "deptunitcode", strDeptUnitCode,2);
      daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),3);
      daoObj.setProcInValue(nProcIndex, "seatId", "",4);
      daoObj.setProcOutValue(nProcIndex, "err", 1,5);
      daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
      daoObj.executeProcedureByPosition(nProcIndex);
      strErr = daoObj.getString(nProcIndex, "err");
      if (strErr == null)
        strErr = "";
      ws = daoObj.getWebRowSet(nProcIndex, "resultset");
      temp=util.getOptionValue(ws,"0","0^Select Value", false);
      voObj.setStrConsultantCode(temp);
      if (strErr.equals("")) {
    	  voObj.setStrMsgType("0");
      } else {
      throw new Exception(strErr);
       }
      } catch (Exception e) {
         voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.setConsultantName() --> "+ e.getMessage());
          voObj.setStrMsgType("1");
  } finally {
   if(daoObj != null) {
      daoObj.free();
      daoObj = null;
   }
  }
 }

  /**
	 * This function is used to bring ward,room and bed details in case of Ms Approval
	 * @param voObj
	 */
	public static void getPatDtl_Msapproval(PatientLeaveJoinRecordTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_pat_adm_msapproval(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
				daoObj = new HisDAO("Patient Leave Transaction",
					"PatientLeaveJoinRecordTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeVal","1",1);
				daoObj.setProcInValue(nProcIndex, "pukno",voObj.getStrCrNo(),2);
				daoObj.setProcInValue(nProcIndex, "advno",voObj.getStrAdviceAdmNo(),3);
				daoObj.setProcInValue(nProcIndex, "bookingdate",voObj.getStrCtDate(),4);
				daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode(),5);
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
						voObj.setStrWardTypeCode(ws.getString(1));
						voObj.setStrWardCode(ws.getString(2));
						voObj.setStrDeptCode(ws.getString(3));
						voObj.setStrRoomCode(ws.getString(4));
						voObj.setStrBedCode(ws.getString(5));
						voObj.setStrMsApprovalStatus(ws.getString(6));
						voObj.setStrWardName(ws.getString(7));
						voObj.setStrRoom(ws.getString(8));
					}
					voObj.setStrMsgType("0");
				} else {
							throw new Exception(strErr);
						}

		} catch (Exception e) {
				voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.getPatDtl_Msapproval() --> "
						+ e.getMessage());
				voObj.setStrMsgType("1");
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
  
   public WebRowSet getAdvisedBy(PatientLeaveJoinRecordTransVO voObj)
   {
	    WebRowSet ws = null;
	    String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
		try {
		  daoObj = new HisDAO("ipd","transactions.PatientLeaveJoinRecordTransDAO .getAdvisedBy()");
		  nProcIndex = daoObj.setProcedure(strProcName);
		   daoObj.setProcInValue(nProcIndex, "modVal", "2",1);
		   daoObj.setProcInValue(nProcIndex, "deptunitcode","",2);
	      daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),3);
	      daoObj.setProcInValue(nProcIndex, "seatId", "",4);
	      daoObj.setProcOutValue(nProcIndex, "err", 1,5);
	      daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
	      daoObj.executeProcedureByPosition(nProcIndex);
	      strErr = daoObj.getString(nProcIndex, "err");
	      ws = daoObj.getWebRowSet(nProcIndex, "resultset"); 
	      if (strErr == null)
	      {	  
	        strErr = "";
	      }
	      if(strErr=="")
	      {
	    	  voObj.setStrMsgType("0");
	      }
	      else
	    	  throw new Exception(strErr);
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.getAdvisedBy() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			daoObj.free();
			daoObj = null;
		}
       return ws;
   }

   public void getRsnRmk(PatientLeaveJoinRecordTransVO voObj)
   {
	   HisUtil hisutil = new HisUtil("transaction", "PatientLeaveJoinRecordTransDAO");
	   WebRowSet w1;
	   try
	  {
		     w1=getAdvisedBy(voObj);
			 voObj.setStrDisBy(w1);
			 String str1 = hisutil.getOptionValue(voObj.getStrDisBy(), "0", "0^Select value", true);
			 voObj.setStrRmk(str1);
		//	 System.out.println("Leave By Combo->"+voObj.getStrRmk());
	  }
	  catch(Exception e)
	  {
		 voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.getRsnRmk() -->"+ e.getMessage());
		 voObj.setStrMsgType("1");
	  }
	  w1=null;
   }
 
   /*********PROC for getting Adm Status Code**********/

   public  WebRowSet admStatus(PatientLeaveJoinRecordTransVO voObj)
   {
	WebRowSet ws = null;
	String proc_name1 = "";
    proc_name1 = "{call pkg_ipd_view.PROC_PAT_ADMSTATUS_CODE_DTL(?,?,?,?,?,?,?)}";
    HisDAO dao = null;
    int procIndex1 = 0;
    String strerr = "";
    IpdConfigUtil ipdC=new IpdConfigUtil(voObj.getStrHospCode());
    
    try {
     dao = new HisDAO("ipd","transactions.PatientLeaveJoinRecordTransDAO.admStatus()");
     procIndex1 = dao.setProcedure(proc_name1);
     dao.setProcInValue(procIndex1, "modval","1",1);
     dao.setProcInValue(procIndex1, "hipnum_admno","",2);
     dao.setProcInValue(procIndex1, "hrgnum_puk",voObj.getStrCrNo(),3);
     dao.setProcInValue(procIndex1, "gnum_hospital_code",voObj.getStrHospCode(),4);
     //ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
     //dao.setProcInValue(procIndex1, "issuereq", resourceBundle.getString("BELONGING_ISSUE_ITEM_REQUIRED"));
     if(ipdC.getStrBelongingRequired().equals("1") ||  ipdC.getStrIssueItemRequired().equals("1"))
    	 dao.setProcInValue(procIndex1, "issuereq", "1",5);
     else
    	 dao.setProcInValue(procIndex1, "issuereq", "0",5);
     dao.setProcOutValue(procIndex1, "err",1,6); // 1 for string return
     dao.setProcOutValue(procIndex1, "resultset",2,7);
     // execute procedure
      dao.executeProcedureByPosition(procIndex1);
     // get value
     strerr = dao.getString(procIndex1, "err");
     ws=dao.getWebRowSet(procIndex1,"resultset");
     if (strerr == null)
      strerr = "";
     if (strerr.equals(""))
     {
      voObj.setStrMsgType("0");
     }
     else
     {
      throw new Exception(strerr);
     }
    }
    catch (Exception e)
    {
    	 voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.admStatus() -->"+ e.getMessage());
		 voObj.setStrMsgType("1");
    }
    finally {
     if (dao != null) {
      dao.free();
      dao = null;
     }
    }
    return ws;
   }

   /*************END**************/
   
 
   
  /***validate request*****/
   
   public static boolean getLeaveStatus(PatientLeaveJoinRecordTransVO voObj)
   {
	    HisDAO dao = null;
	    int funcIndex = 0;
	    int i=0;
	    boolean retVal = false;
	    String ret="false";
        try
        {  
          dao = new HisDAO("Patient Leave Join Record Trans","PatientLeaveJoinRecordTransDAO");
          funcIndex = dao.setFunction("{? = call IPD_MST.getLeaveStatus(?::numeric,?::numeric,?::numeric)}");
          if(voObj.getStrTempLeaveJoin().equals("leave"))
             dao.setFuncInValue(funcIndex,2,"2");
          else
        	 dao.setFuncInValue(funcIndex,2,"3"); 
          dao.setFuncInValue(funcIndex,3,voObj.getStrCrNo());
          dao.setFuncInValue(funcIndex,4,voObj.getStrHospCode());
          dao.setFuncOutValue(funcIndex,3);
          // Execute Function
          dao.executeFuncForNumeric(funcIndex);
          // returns
          ret = dao.getFuncNumeric(funcIndex);
          i=Integer.parseInt(ret);
          if(i>0)
        	retVal=true;
          else
        	retVal=false;  
        }
        catch(Exception e)
        {
        	voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.getLeaveStatus() --> "+ e.getMessage());
			voObj.setStrMsgType("1");	
			retVal=false;
        }
        finally
        {
          if (dao != null) 
          {
        	dao.free();
        	dao = null;
          } 
        }
        return retVal;
	  
   }
   /*********select********/
   public static void setLeaveDtl(PatientLeaveJoinRecordTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String tmpLeavorJoin="";
		String strProcName = "{call pkg_ipd_view.proc_hipt_pat_leave_dtl(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Leave Join Record Trans","PatientLeaveJoinRecordTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			if(voObj.getStrTempLeaveJoin().equals("leave"))
				tmpLeavorJoin="3";
			else	
				tmpLeavorJoin="4";
			    daoObj.setProcInValue(nProcIndex, "modeVal",tmpLeavorJoin,1);
				daoObj.setProcInValue(nProcIndex, "puk",voObj.getStrCrNo(),2);
				daoObj.setProcInValue(nProcIndex, "patListType","0",3);
				daoObj.setProcInValue(nProcIndex, "searchStr","",4);
				daoObj.setProcInValue(nProcIndex, "searchType","1",5);
				daoObj.setProcInValue(nProcIndex, "frmRows","0",6);
				daoObj.setProcInValue(nProcIndex, "toRows","0",7);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),8);
				daoObj.setProcOutValue(nProcIndex,"err", 1,9);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					voObj.setStrLeaveApprovalWS(ws);
				} else {
					throw new Exception(strErr);
				}
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.setLeaveDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
   /*********update**********/
   public static void upadateLeaveApprovalDtl(PatientLeaveJoinRecordTransVO voObj) {

		HisDAO daoObj = null;
		String strProcName = "";
		String strProcName2 = "";
		String tempLeaveOrJoin=voObj.getStrTempLeaveJoin();
		if(tempLeaveOrJoin.equals("leave"))
		{
		   strProcName = "{call pkg_ipd_dml.PROC_PAT_LEAVE_JOIN_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		   strProcName2 ="{call pkg_ipd_dml.proc_patient_movement(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		}   
		else
		{	
		   //strProcName = "{call pkg_ipd_dml.PROC_PAT_LEAVE_JOIN_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			strProcName = "{call pkg_ipd_dml.PROC_PAT_LEAVE_JOIN_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		   strProcName2 ="{call pkg_ipd_dml.Proc_movement_Acceptance(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		}
		int nProcIndex = 0;
		int nProcIndex2 = 0;
		String strErr1 = "";
		String strErr2 = "";
		try {
			daoObj = new HisDAO("Patient Leave Join Record Trans","PatientLeaveJoinRecordTransDAO");
			nProcIndex  = daoObj.setProcedure(strProcName);
			nProcIndex2 = daoObj.setProcedure(strProcName2);
			if(tempLeaveOrJoin.equals("leave"))
			{	
				  voObj.setStrLeaveStatusCode("5");
				  voObj.setStrAdmStatCode("15");
				  
				  daoObj.setProcInValue(nProcIndex, "modval","4",1);
			     daoObj.setProcInValue(nProcIndex, "hipnum_admno",voObj.getCurAdmNo(),2);
				  daoObj.setProcInValue(nProcIndex, "hrgnum_puk",voObj.getStrCrNo(),3);
				  daoObj.setProcInValue(nProcIndex, "hipnum_leave_slno", voObj.getStrLeaveSlNo(),4);
				  daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", voObj.getStrHospCode(),5);
				  daoObj.setProcInValue(nProcIndex, "GNUM_LEAVE_FROM_DEPT_CODE",voObj.getStrDeptCode(),6);  
			     daoObj.setProcInValue(nProcIndex, "GNUM_LEAVE_FROM_DEPTUNIT_CODE",voObj.getStrDeptUnitCode(),7);
			     daoObj.setProcInValue(nProcIndex, "HIPNUM_LEAVE_FROM_WARD_CODE",voObj.getStrWardCode(),8);
			     daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_dt","",9);
			     daoObj.setProcInValue(nProcIndex, "HIPDT_LEAVE_FROM_DT",voObj.getStrValidFrom(),10);
			     daoObj.setProcInValue(nProcIndex, "HIPDT_LEAVE_TO_DT",voObj.getStrValidTo(),11);
			     daoObj.setProcInValue(nProcIndex, "hipstr_leave_sanction_by","",12);
			     daoObj.setProcInValue(nProcIndex, "HIPSTR_LEAVE_REMARKS",voObj.getStrRsn(),13);
			     daoObj.setProcInValue(nProcIndex, "hipdt_leave_join_dt","",14);
			     daoObj.setProcInValue(nProcIndex, "gnum_join_dept_code","",15);
			     daoObj.setProcInValue(nProcIndex, "gnum_join_deptunit_code","",16);
			     daoObj.setProcInValue(nProcIndex, "hipnum_join_ward_code","",17);
			     daoObj.setProcInValue(nProcIndex, "GDT_ENTRY_DATE",voObj.getStrEntryDate(),18);
			     daoObj.setProcInValue(nProcIndex, "GNUM_ISVALID","1",19);
			     daoObj.setProcInValue(nProcIndex, "GNUM_SEAT_ID",voObj.getStrSeatId(),20 );
			     daoObj.setProcInValue(nProcIndex, "HIPSTR_PAT_LEAVE_COND",voObj.getStrPatCondL(),21);
			     daoObj.setProcInValue(nProcIndex, "hipstr_pat_join_cond","",22);
			     daoObj.setProcInValue(nProcIndex, "hipnum_phone_no",voObj.getStrPhoneNo(),23);                    
			     daoObj.setProcInValue(nProcIndex, "hipnum_address",voObj.getStrAddrLeave(),24);
			     daoObj.setProcInValue(nProcIndex, "hipnum_leave_from_room_code",voObj.getStrRoomCode(),25);
			     daoObj.setProcInValue(nProcIndex, "hipnum_leave_from_bed_code",voObj.getStrBedCode(),26);
			     daoObj.setProcInValue(nProcIndex, "gnum_join_room_code","",27);
			     daoObj.setProcInValue(nProcIndex, "gnum_join_bed_code","",28);
			     daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_from_dt","",29);
			     daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_to_dt","",30);
				  daoObj.setProcInValue(nProcIndex, "hipdt_leave_availed_from_dt", voObj.getStrLeavingDt(),31);
				  daoObj.setProcInValue(nProcIndex, "hipdt_leave_availed_to_dt","",32);	
				  daoObj.setProcInValue(nProcIndex, "hipnum_advice_given", voObj.getStrAdviceL(),33);
				  daoObj.setProcInValue(nProcIndex, "hipnum_is_bed_vacant", "",34);
				  daoObj.setProcInValue(nProcIndex, "hipnum_join_remarks","",35);
			     daoObj.setProcInValue(nProcIndex, "hipnum_join_by","",36);
			     daoObj.setProcInValue(nProcIndex, "hipnum_leave_status", voObj.getStrLeaveStatusCode(),37);
			     daoObj.setProcInValue(nProcIndex, "hipnum_approval_reject_remark","",38);
				  daoObj.setProcInValue(nProcIndex, "hipdt_admstatus_code", voObj.getStrAdmStatCode(),39);	
				  daoObj.setProcInValue(nProcIndex, "strLeaveType",voObj.getStrLeaveType(),40);
				  daoObj.setProcOutValue(nProcIndex, "err", 1,41);
				  daoObj.execute(nProcIndex,1);
				
                /* calling of call proc_patient_movement(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)*/
							
				 nProcIndex2 = daoObj.setProcedure(strProcName2);
				 daoObj.setProcInValue(nProcIndex2, "hipnum_admno",voObj.getCurAdmNo(),1);
				 daoObj.setProcInValue(nProcIndex2, "hipnum_mov_no","",2);
			     daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code",voObj.getStrHospCode(),3);
			     daoObj.setProcInValue(nProcIndex2, "hrgnum_puk",voObj.getStrCrNo(),4);
			     daoObj.setProcInValue(nProcIndex2, "gnum_dept_code",voObj.getStrDeptCode(),5); 
			     daoObj.setProcInValue(nProcIndex2, "gnum_deptunit_code",voObj.getStrDeptUnitCode(),6);
			     daoObj.setProcInValue(nProcIndex2, "hipnum_trans_serarea_code","",7);
			     daoObj.setProcInValue(nProcIndex2, "hipnum_wardcode",voObj.getStrWardCode(),8);
			     daoObj.setProcInValue(nProcIndex2, "hipnum_roomno",voObj.getStrRoomCode(),9);
			     daoObj.setProcInValue(nProcIndex2, "hipnum_bed_code",voObj.getStrBedCode(),10);
			     daoObj.setProcInValue(nProcIndex2, "hipdt_trans_datetime","",11);
			     daoObj.setProcInValue(nProcIndex2, "hipdt_transin_datetime","",12);
			     daoObj.setProcInValue(nProcIndex2, "hipdt_transout_datetime","",13);
			     daoObj.setProcInValue(nProcIndex2, "hipstr_trans_reason",voObj.getStrAdviceL(),14);
			     daoObj.setProcInValue(nProcIndex2, "hipnum_transin_flg","4",15);
			     daoObj.setProcInValue(nProcIndex2, "hipnum_transout_flg","",16);
			     daoObj.setProcInValue(nProcIndex2, "gdt_entry_date","",17);
			     daoObj.setProcInValue(nProcIndex2, "gnum_seatid",voObj.getStrSeatId(),18);
			     daoObj.setProcInValue(nProcIndex2, "gnum_isvalid","1",19);
			     daoObj.setProcInValue(nProcIndex2, "hipnum_bedvacat_flg",voObj.getStrIsBedVacant()==null||voObj.getStrIsBedVacant().trim().equals("")?"0":"1",20);
			     daoObj.setProcInValue(nProcIndex2, "gnum_nurse_seatid","",21);
			     daoObj.setProcInValue(nProcIndex2, "old_hgnum_ward_code",voObj.getStrWardCode(),22);
			     daoObj.setProcInValue(nProcIndex2, "old_hipnum_room_no",voObj.getStrRoomCode(),23);
			     daoObj.setProcInValue(nProcIndex2, "old_hgnum_bed_code",voObj.getStrBedCode(),24);
			     daoObj.setProcInValue(nProcIndex2, "old_gnum_hospital_code",voObj.getStrHospCode(),25);
			     daoObj.setProcInValue(nProcIndex2, "outside_location","",26);
			     daoObj.setProcInValue(nProcIndex2, "prev_double_occupancy","0",27);
			     daoObj.setProcInValue(nProcIndex2, "hold_room","",28);
			     daoObj.setProcInValue(nProcIndex2, "str_advice_by","",29);
			     daoObj.setProcOutValue(nProcIndex2, "err",1,30); // 1 for string return
			     daoObj.setProcOutValue(nProcIndex2, "dml_count",1,31);  // value
				 daoObj.execute(nProcIndex2, 1);
			}
			else
			{
				voObj.setStrLeaveStatusCode("6");
				voObj.setStrAdmStatCode("12");
				
					  daoObj.setProcInValue(nProcIndex, "modval","5",1);
				     daoObj.setProcInValue(nProcIndex, "hipnum_admno",voObj.getCurAdmNo(),2);
					  daoObj.setProcInValue(nProcIndex, "hrgnum_puk",voObj.getStrCrNo(),3);
					  daoObj.setProcInValue(nProcIndex, "hipnum_leave_slno", voObj.getStrLeaveSlNo(),4);
					  daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", voObj.getStrHospCode(),5);
					  daoObj.setProcInValue(nProcIndex, "GNUM_LEAVE_FROM_DEPT_CODE","",6);  
				     daoObj.setProcInValue(nProcIndex, "GNUM_LEAVE_FROM_DEPTUNIT_CODE","",7);
				     daoObj.setProcInValue(nProcIndex, "HIPNUM_LEAVE_FROM_WARD_CODE","",8);
				     daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_dt","",9);
				     daoObj.setProcInValue(nProcIndex, "HIPDT_LEAVE_FROM_DT","",10);
				     daoObj.setProcInValue(nProcIndex, "HIPDT_LEAVE_TO_DT","",11);
				     daoObj.setProcInValue(nProcIndex, "hipstr_leave_sanction_by","",12);
				     daoObj.setProcInValue(nProcIndex, "HIPSTR_LEAVE_REMARKS","",13);
			        daoObj.setProcInValue(nProcIndex, "hipdt_leave_join_dt","",14);
			        daoObj.setProcInValue(nProcIndex, "gnum_join_dept_code", voObj.getStrDeptCode(),15);
					  daoObj.setProcInValue(nProcIndex, "gnum_join_deptunit_code ", voObj.getStrDeptUnitCode(),16);
					  daoObj.setProcInValue(nProcIndex, "hipnum_join_ward_code", voObj.getStrWardCode(),17);
					  daoObj.setProcInValue(nProcIndex, "GDT_ENTRY_DATE","",18);
				     daoObj.setProcInValue(nProcIndex, "GNUM_ISVALID","1",19);
				     daoObj.setProcInValue(nProcIndex, "GNUM_SEAT_ID","",20);
				     daoObj.setProcInValue(nProcIndex, "HIPSTR_PAT_LEAVE_COND","",21);	
				     daoObj.setProcInValue(nProcIndex, "hipstr_pat_join_cond", voObj.getStrPatCondL(),22);
				     daoObj.setProcInValue(nProcIndex, "hipnum_phone_no","",23);                    
				     daoObj.setProcInValue(nProcIndex, "hipnum_address","",24);
				     daoObj.setProcInValue(nProcIndex, "hipnum_leave_from_room_code","",25);
				     daoObj.setProcInValue(nProcIndex, "hipnum_leave_from_bed_code","",26);	
					  	if(voObj.getStrIsBedVacant().equals("1"))
						{	
						  daoObj.setProcInValue(nProcIndex, "gnum_join_room_code", voObj.getStrRoom(),27);
						  daoObj.setProcInValue(nProcIndex, "gnum_join_bed_code", voObj.getStrBed(),28);
						}
						else
						{
						  daoObj.setProcInValue(nProcIndex, "gnum_join_room_code", voObj.getStrRoomCode(),27);
						  daoObj.setProcInValue(nProcIndex, "gnum_join_bed_code", voObj.getStrBedCode(),28);
						}
				     daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_from_dt","",29);
				     daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_to_dt","",30);	
				     daoObj.setProcInValue(nProcIndex, "hipdt_leave_availed_from_dt", "",31);	
				     daoObj.setProcInValue(nProcIndex, "hipdt_leave_availed_to_dt", voObj.getStrCtDate(),32);
				     daoObj.setProcInValue(nProcIndex, "hipnum_advice_given","",33);		
				     daoObj.setProcInValue(nProcIndex, "hipnum_is_bed_vacant", "",34);
				     daoObj.setProcInValue(nProcIndex, "hipnum_join_remarks", voObj.getStrRsn_Entry(),35);
					  daoObj.setProcInValue(nProcIndex, "hipnum_join_by", voObj.getStrRmkJ(),36);
					  daoObj.setProcInValue(nProcIndex, "hipnum_leave_status", voObj.getStrLeaveStatusCode(),37);
					  daoObj.setProcInValue(nProcIndex, "hipnum_approval_reject_remark","",38);
					  daoObj.setProcInValue(nProcIndex, "hipdt_admstatus_code", voObj.getStrAdmStatCode(),39);
					  daoObj.setProcInValue(nProcIndex, "strLeaveType",voObj.getStrLeaveType(),40);
				     daoObj.setProcOutValue(nProcIndex, "err", 1,41);
				     daoObj.execute(nProcIndex,1);
				
				/* call pkg_ipd_dml.Proc_movement_Acceptance(?,?,?,?,?,?,?,?,?,?,?,?)}*/
				
				nProcIndex2 = daoObj.setProcedure(strProcName2);
	
				daoObj.setProcInValue(nProcIndex2, "hipnum_admno",voObj.getCurAdmNo(),1);
				daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code", voObj.getStrHospCode(),2);
				daoObj.setProcInValue(nProcIndex2, "hrgnum_puk",voObj.getStrCrNo(),3);
				daoObj.setProcInValue(nProcIndex2, "gnum_seatid",voObj.getStrSeatId(),4);
				daoObj.setProcInValue(nProcIndex2, "gnum_dept_code", voObj.getStrDeptCode(),5);
				daoObj.setProcInValue(nProcIndex2, "gnum_deptunit_code ", voObj.getStrDeptUnitCode(),6);
				daoObj.setProcInValue(nProcIndex2, "hipnum_ward_code", voObj.getStrWardCode(),7);
				if(voObj.getStrIsBedVacant().equals("1"))
				{	
				  daoObj.setProcInValue(nProcIndex2, "hipnum_room_code", voObj.getStrRoom(),8);
				  daoObj.setProcInValue(nProcIndex2, "hipnum_bed_code", voObj.getStrBed(),9);
				}
				else
				{
				  daoObj.setProcInValue(nProcIndex2, "hipnum_room_code", voObj.getStrRoomCode(),8);
				  daoObj.setProcInValue(nProcIndex2, "hipnum_bed_code", voObj.getStrBedCode(),9);
				}
				daoObj.setProcInValue(nProcIndex2, "hipnum_transin_flg", "9",10);
				
				daoObj.setProcInValue(nProcIndex2, "code_remarks", "",11);
				daoObj.setProcInValue(nProcIndex2, "belonging_remarks", "",12);
				daoObj.setProcOutValue(nProcIndex2, "err", 1,13);
				daoObj.setProcOutValue(nProcIndex2, "dml_count", 1,14);
				daoObj.execute(nProcIndex2, 1);
		  }
			synchronized (daoObj) {
				daoObj.fire();
			} 
				
				strErr1 = daoObj.getString(nProcIndex, "err");
				strErr2 = daoObj.getString(nProcIndex2, "err");
				if (strErr1 == null && strErr2==null)
				{
					strErr1 = "";
					strErr2 = "";
				}	
				     if (strErr1.equals("") && strErr2.equals(""))
				     {
				  //  	 System.out.println("err blank");
				    	 voObj.setStrMsgType("0");
				    	 voObj.setStrNormalMsgString("Record Updated Successfully");
				     }
				     else
				     {
				 //   	 System.out.println("inside err");
				    	 throw new Exception(strErr1+"###"+strErr2);
				     }
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientLeaveJoinRecordTransDAO.upadateLeaveApprovalDtl() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
   /**************Leave & Joining Entry ENDS***************/
}
