package ipd.transactions;

import ipd.IpdConfigUtil;

//import java.util.ResourceBundle;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import javax.sql.rowset.WebRowSet;

public class PatientLeaveDAO
{

	/**
	 * This function is used to details in room type combo on bed status pop up window
	 * @param voObj
	 */
	public static void setRoomTypeDtl(PatientLeaveVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_roomtype_a(?,?,?,?)}";
		int nProcIndex = 0;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveDAO");
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Admission Transaction",
					"PatientLeaveDAO");
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
			voObj.setStrErrMsgString("PatientLeaveDAO.setRoomTypeDtl() --> "
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
	public static void getBedStatusDtl(PatientLeaveVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_ipd_bedstatus_dtl(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Admission Transaction",
					"PatientLeaveDAO");
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
			voObj.setStrErrMsgString("PatientLeaveDAO.getBedStatusDtl() --> "
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
	 * This function is used to bring room details in room combo on the basis of room type code
	 * @param voObj
	 */
	public static void getRoomValues(PatientLeaveVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveDAO");
		//String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?)}";
		String strProcName = "{call pkg_ipd_view.proc_roomconfig(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
	    daoObj = new HisDAO("Patient Admission Transaction",
				"PatientLeaveDAO.getRoomValues()");
		try
		{
			daoObj = new HisDAO("Patient Leave",
			"PatientLeaveDAO");
        nProcIndex = daoObj.setProcedure(strProcName);
        
        
		
		daoObj.setProcInValue(nProcIndex, "modval","1",1);
	    daoObj.setProcInValue(nProcIndex, "roomtypcode",voObj.getStrRoomTypeCode(),2);
	    daoObj.setProcInValue(nProcIndex, "wardcode",voObj.getStrWardCode(),3);
	    daoObj.setProcInValue(nProcIndex, "hosp_code",voObj.getStrHospCode(),4);
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
    	temp=util.getOptionValue(ws,"0","0^Select Value", false);
	//	System.out.println("roomValues->"+temp);
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
			voObj.setStrErrMsgString("PatientLeaveDAO.getRoomValues() --> "
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
	public static void setBedTypeDtl(PatientLeaveVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveDAO");
		String strProcName = "{call pkg_ipd_view.proc_bed_type_mst(?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Leave","PatientLeaveDAO");
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
			voObj.setStrErrMsgString("PatientLeaveDAO.setBedTypeDtl() --> "+ e.getMessage());
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
	public static void getBed(PatientLeaveVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveDAO");
		//String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?)}";
		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try
		{
		  daoObj = new HisDAO("Patient Leave  Transaction","PatientLeaveDAO");
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
            temp=util.getOptionValue(ws,"0","0^Select Value", false);
            voObj.setStrBed(temp);
		}
		catch(Exception e)
		{
		  voObj.setStrErrMsgString("PatientLeaveDAO.getBedValues() --> "+ e.getMessage());
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
	public static void getBedValues(PatientLeaveVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String temp="";
		HisUtil  util=null;
		util = new HisUtil("ipd","PatientLeaveDAO");
		//String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?))}";
		String strProcName = "{call pkg_ipd_view.proc_bed_dtl(?,?,?,?,?,?,?,?,?,?,?)}";
		
		int nProcIndex = 0;
		String strErr = "";
		try
		{
		  daoObj = new HisDAO("Patient Leave  Transaction","PatientLeaveDAO");
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
		  voObj.setStrErrMsgString("PatientLeaveDAO.getBedValues() --> "+ e.getMessage());
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
    This function is used to set comsultant code and consultant name on the behalf of puk no
    **/
  public static void setConsultantName(PatientLeaveVO voObj)
  {
	//String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?)}";
	  String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
    int nProcIndex = 0;
    String strErr = "";
    HisDAO daoObj = null;
    WebRowSet ws = null;
    String temp="";
	HisUtil  util=null;
	util = new HisUtil("ipd","PatientLeaveDAO");
    String strDeptUnitCode=voObj.getStrDeptUnitCode();
    try {
      daoObj = new HisDAO("Patient Leave","PatientLeaveDAO");
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
         voObj.setStrErrMsgString("PatientLeaveDAO.setConsultantName() --> "+ e.getMessage());
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
	public static void getPatDtl_Msapproval(PatientLeaveVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;
		//String strProcName = "{call pkg_ipd_view.proc_pat_adm_msapproval(?,?,?,?,?,?)}";
		String strProcName = "{call pkg_ipd_view.proc_pat_adm_msapproval(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
				daoObj = new HisDAO("Patient Leave Transaction",
					"PatientLeaveDAO");
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
				voObj.setStrErrMsgString("PatientLeaveDAO.getPatDtl_Msapproval() --> "
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


   public WebRowSet getAdvisedBy(PatientLeaveVO voObj)
   {
	    WebRowSet ws = null;
	    //String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?)}";
	    String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
		try {
		  daoObj = new HisDAO("ipd","transactions.PatientLeaveDAO .getAdvisedBy()");
		  nProcIndex = daoObj.setProcedure(strProcName);
		  daoObj.setProcInValue(nProcIndex, "modVal", "2",1);
		  daoObj.setProcInValue(nProcIndex, "deptunitcode", "",2);
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
			voObj.setStrErrMsgString("PatientLeaveDAO.getAdvisedBy() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			daoObj.free();
			daoObj = null;
		}
       return ws;
   }

   public void getRsnRmk(PatientLeaveVO voObj)
   {
	   HisUtil hisutil = new HisUtil("transaction", "PatientLeaveDAO");
	   WebRowSet w1=null;
	   try
	  {
	     w1=getAdvisedBy(voObj);
		 voObj.setStrDisBy(w1);
		 String str1 = hisutil.getOptionValue(voObj.getStrDisBy(), "0", "0^Select value", true);
		 voObj.setStrRmk(str1);
	  }
	  catch(Exception e)
	  {
		 voObj.setStrErrMsgString("PatientLeaveDAO.getRsnRmk() -->"+ e.getMessage());
		 voObj.setStrMsgType("1");
	  }
	  w1=null;
   }

   /**************Leave & Joining***************/
   
   /*********PROC for getting Adm Status Code**********/

   public  WebRowSet admStatus(PatientLeaveVO voObj)
   {
	WebRowSet ws = null;
	String proc_name1 = "";
	IpdConfigUtil ipdC=new IpdConfigUtil(voObj.getStrHospCode());
    //proc_name1 = "{call pkg_ipd_view.PROC_PAT_ADMSTATUS_CODE_DTL(?,?,?,?,?)}";
	proc_name1 = "{call pkg_ipd_view.PROC_PAT_ADMSTATUS_CODE_DTL(?,?,?,?,?,?,?)}";
    HisDAO dao = null;
    int procIndex1 = 0;
    String strerr = "";
    try {
     dao = new HisDAO("ipd","transactions.PatientLeaveDAO.admStatus()");
     procIndex1 = dao.setProcedure(proc_name1);
     
     dao.setProcInValue(procIndex1, "modval","1",1);
     dao.setProcInValue(procIndex1, "hipnum_admno","",2);
     dao.setProcInValue(procIndex1, "hrgnum_puk",voObj.getStrCrNo(),3);
     dao.setProcInValue(procIndex1, "gnum_hospital_code",voObj.getStrHospCode(),4);
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
    	e.printStackTrace();
    	voObj.setStrErrMsgString("PatientLeaveDAO.getAdvisedBy() --> "+ e.getMessage());
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
  

   /*********select********/
   public static void setLeaveDtl(PatientLeaveVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String tmpLeavorJoin="";
		//String strProcName = "{call pkg_ipd_view.proc_hipt_pat_leave_dtl(?,?,?,?,?)}";
		String strProcName = "{call pkg_ipd_view.proc_hipt_pat_leave_dtl(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Leave Join  Trans","PatientLeaveDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
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
			voObj.setStrErrMsgString("PatientLeaveDAO.setLeaveDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}

   public  boolean patMov_LJ(PatientLeaveVO voObj)
   {
	    String proc_name1 = "";
	    String proc_name2 = "";
	    //proc_name1 = "{call pkg_ipd_dml.PROC_PAT_LEAVE_JOIN_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	    proc_name1 = "{call pkg_ipd_dml.PROC_PAT_LEAVE_JOIN_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	    //proc_name2 ="{call pkg_ipd_dml.proc_patient_movement(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	    proc_name2 ="{call pkg_ipd_dml.proc_patient_movement(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	    HisDAO dao = null;
	    int procIndex1 = 0;
	    int nProcIndex2 = 0;
	    String strErr1 = "";
	    String strErr2 = "";
	  
	    boolean retVal = false;
	    try {
	    	
	    	
	     dao = new HisDAO("ipd","transactions.patMov_LJ");
	     procIndex1 = dao.setProcedure(proc_name1);
	     dao.setProcInValue(procIndex1, "modval","2",1);
	     dao.setProcInValue(procIndex1, "hipnum_admno",voObj.getStrAdmNo(),2);
	     dao.setProcInValue(procIndex1, "hrgnum_puk",voObj.getStrCrNo(),3);
	     dao.setProcInValue(procIndex1, "hipnum_leave_slno", "",4);
	     dao.setProcInValue(procIndex1, "GNUM_HOSPITAL_CODE",voObj.getStrHospCode(),5);
	     dao.setProcInValue(procIndex1, "GNUM_LEAVE_FROM_DEPT_CODE",voObj.getStrDeptCode(),6);  
	     dao.setProcInValue(procIndex1, "GNUM_LEAVE_FROM_DEPTUNIT_CODE",voObj.getStrDeptUnitCode(),7);
	     dao.setProcInValue(procIndex1, "HIPNUM_LEAVE_FROM_WARD_CODE",voObj.getStrWardCode(),8);
	     dao.setProcInValue(procIndex1, "HIPDT_LEAVE_SANCTION_DT",voObj.getStrSactionDate(),9);
	     dao.setProcInValue(procIndex1, "HIPDT_LEAVE_FROM_DT",voObj.getStrCtDate(),10);
	     dao.setProcInValue(procIndex1, "HIPDT_LEAVE_TO_DT",voObj.getStrValidTo(),11);
	     dao.setProcInValue(procIndex1, "HIPSTR_LEAVE_SANCTION_BY",voObj.getStrRmkL(),12);
	     dao.setProcInValue(procIndex1, "HIPSTR_LEAVE_REMARKS",voObj.getStrRsnL(),13);
	     dao.setProcInValue(procIndex1, "hipdt_leave_join_dt","",14);
		  dao.setProcInValue(procIndex1, "gnum_join_dept_code","",15);
		  dao.setProcInValue(procIndex1, "gnum_join_deptunit_code","",16);
		  dao.setProcInValue(procIndex1, "hipnum_join_ward_code","",17);
		  dao.setProcInValue(procIndex1, "GDT_ENTRY_DATE",voObj.getStrEntryDate(),18);
	     dao.setProcInValue(procIndex1, "GNUM_ISVALID","1",19);
	     dao.setProcInValue(procIndex1, "GNUM_SEAT_ID",voObj.getStrSeatId(),20);
	     dao.setProcInValue(procIndex1, "HIPSTR_PAT_LEAVE_COND",voObj.getStrPatCondL(),21);
	     dao.setProcInValue(procIndex1, "hipstr_pat_join_cond","",22);
	     dao.setProcInValue(procIndex1, "hipnum_phone_no",voObj.getStrPhoneNo(),23);
	     dao.setProcInValue(procIndex1, "hipnum_address",voObj.getStrAddrLeave(),24);
	     dao.setProcInValue(procIndex1, "hipnum_leave_from_room_code",voObj.getStrRoomCode(),25);
	     dao.setProcInValue(procIndex1, "hipnum_leave_from_bed_code",voObj.getStrBedCode(),26);
	     dao.setProcInValue(procIndex1, "gnum_join_room_code","",27);
		  dao.setProcInValue(procIndex1, "gnum_join_bed_code","",28);
		  dao.setProcInValue(procIndex1, "hipdt_leave_sanction_from_dt","",29);
		  dao.setProcInValue(procIndex1, "hipdt_leave_sanction_to_dt","",30);
		  dao.setProcInValue(procIndex1, "hipdt_leave_availed_from_dt", "",31);
		  dao.setProcInValue(procIndex1, "hipdt_leave_availed_to_dt","",32);		
	     dao.setProcInValue(procIndex1, "hipnum_advice_given", voObj.getStrAdviceL(),33);
	     dao.setProcInValue(procIndex1, "hipnum_is_bed_vacant",voObj.getStrIsBedVacant()==null||voObj.getStrIsBedVacant().trim().equals("")?"0":"1",34);
	     dao.setProcInValue(procIndex1, "hipnum_join_remarks","",35);
		  dao.setProcInValue(procIndex1, "hipnum_join_by","",36);
	     dao.setProcInValue(procIndex1, "hipnum_leave_status","5",37);
	     dao.setProcInValue(procIndex1, "hipnum_approval_reject_remark","",38);		
	     dao.setProcInValue(procIndex1, "hipdt_admstatus_code", "",39);	
	     dao.setProcInValue(procIndex1, "strLeaveType",voObj.getStrLeaveType(),40);
	     dao.setProcOutValue(procIndex1, "err",1,41); // 1 for string return
	      
         dao.execute(procIndex1,1);
         
         /* calling of call proc_patient_movement(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)*/
			
		 nProcIndex2 = dao.setProcedure(proc_name2);
		  dao.setProcInValue(nProcIndex2, "hipnum_admno",voObj.getCurAdmNo(),1);
		  dao.setProcInValue(nProcIndex2, "hipnum_mov_no","",2);
	     dao.setProcInValue(nProcIndex2, "gnum_hospital_code",voObj.getStrHospCode(),3);
	     dao.setProcInValue(nProcIndex2, "hrgnum_puk",voObj.getStrCrNo(),4);
	     dao.setProcInValue(nProcIndex2, "gnum_dept_code",voObj.getStrDeptCode(),5); 
	     dao.setProcInValue(nProcIndex2, "gnum_deptunit_code",voObj.getStrDeptUnitCode(),6);
	     dao.setProcInValue(nProcIndex2, "hipnum_trans_serarea_code","",7);
	     dao.setProcInValue(nProcIndex2, "hipnum_wardcode",voObj.getStrWardCode(),8);
	     dao.setProcInValue(nProcIndex2, "hipnum_roomno",voObj.getStrRoomCode(),9);
	     dao.setProcInValue(nProcIndex2, "hipnum_bed_code",voObj.getStrBedCode(),10);
	     dao.setProcInValue(nProcIndex2, "hipdt_trans_datetime","",11);
	     dao.setProcInValue(nProcIndex2, "hipdt_transin_datetime","",12);
	     dao.setProcInValue(nProcIndex2, "hipdt_transout_datetime","",13);
	     dao.setProcInValue(nProcIndex2, "hipstr_trans_reason",voObj.getStrRsnL(),14);
	     dao.setProcInValue(nProcIndex2, "hipnum_transin_flg","4",15);
	     dao.setProcInValue(nProcIndex2, "hipnum_transout_flg","",16);
	     dao.setProcInValue(nProcIndex2, "gdt_entry_date","",17);
	     dao.setProcInValue(nProcIndex2, "gnum_seatid",voObj.getStrSeatId(),18);
	     dao.setProcInValue(nProcIndex2, "gnum_isvalid","1",19);
	     dao.setProcInValue(nProcIndex2, "hipnum_bedvacat_flg",voObj.getStrIsBedVacant(),20);
	     dao.setProcInValue(nProcIndex2, "gnum_nurse_seatid","",21);
	     dao.setProcInValue(nProcIndex2, "old_hgnum_ward_code",voObj.getStrWardCode(),22);
	     dao.setProcInValue(nProcIndex2, "old_hipnum_room_no ",voObj.getStrRoomCode(),23);
	     dao.setProcInValue(nProcIndex2, "old_hgnum_bed_code",voObj.getStrBedCode(),24);
	     dao.setProcInValue(nProcIndex2, "old_gnum_hospital_code",voObj.getStrHospCode(),25);
	     dao.setProcInValue(nProcIndex2, "outside_location","",26);
	     dao.setProcInValue(nProcIndex2, "prev_double_occupancy","",27);
	     dao.setProcInValue(nProcIndex2, "hold_room","",28);
	     dao.setProcInValue(nProcIndex2, "str_advice_by","",29);
	     dao.setProcOutValue(nProcIndex2, "err",1,30); // 1 for string return
	     dao.setProcOutValue(nProcIndex2, "dml_count",1,31);  // value
	     
		 dao.execute(nProcIndex2, 1);
     // get value
		 synchronized (dao) {
			 dao.fire();
			} 
     strErr1 = dao.getString(procIndex1, "err");	
	 strErr2 = dao.getString(nProcIndex2, "err");
	 if (strErr1 == null && strErr2==null)
		{
			strErr1 = "";
			strErr2 = "";
		}
		     if (strErr1.equals("") && strErr2.equals("") )
		     {
		    	 retVal=true;
		    	 voObj.setStrMsgType("0");
		    	 voObj.setStrNormalMsgString("Record is successfully Inserted and Updated");
		     }
		     else
		     {
		    	 throw new Exception(strErr1+"#"+strErr2);
		     }
    }
    catch (Exception e)
    {
    	voObj.setStrErrMsgString("PatientLeaveDAO.patMov_LJ() -->"+ e.getMessage());
    	voObj.setStrMsgType("1");
    }
    finally {
     if (dao != null) {
      dao.free();
      dao = null;
     }
    }
    return retVal;
  }
   /*********insert complete**********/
   
   /******UPDATE*****/
   
   /*********update**********/
   public static void upadateLeaveApprovalDtl(PatientLeaveVO voObj) {

		HisDAO daoObj = null;
		String strProcName = "";
		String strProcName2 = "";
		//strProcName = "{call pkg_ipd_dml.PROC_PAT_LEAVE_JOIN_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		strProcName = "{call pkg_ipd_dml.PROC_PAT_LEAVE_JOIN_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		strProcName2 ="{call pkg_ipd_dml.Proc_movement_Acceptance(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		int nProcIndex = 0;
		int nProcIndex2 = 0;
		String strErr1 = "";  
		String strErr2 = "";  
		try {
						
				daoObj = new HisDAO("Patient Leave Join ","PatientLeaveDAO");
				nProcIndex  = daoObj.setProcedure(strProcName);
				nProcIndex2 = daoObj.setProcedure(strProcName2);
			
				voObj.setStrLeaveStatusCode("6");
				voObj.setStrAdmStatCode("12");
				daoObj.setProcInValue(nProcIndex, "modval","5");
			    daoObj.setProcInValue(nProcIndex, "hipnum_admno",voObj.getCurAdmNo());
				daoObj.setProcInValue(nProcIndex, "hrgnum_puk",voObj.getStrCrNo());
				daoObj.setProcInValue(nProcIndex, "hipnum_leave_slno", voObj.getStrLeaveSlNo());
				daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", voObj.getStrHospCode());
				daoObj.setProcInValue(nProcIndex, "gnum_join_dept_code", voObj.getStrDeptCode());
				daoObj.setProcInValue(nProcIndex, "gnum_join_deptunit_code ", voObj.getStrDeptUnitCode());
				daoObj.setProcInValue(nProcIndex, "hipnum_join_ward_code", voObj.getStrWardCode());
				daoObj.setProcInValue(nProcIndex, "hipstr_pat_join_cond", voObj.getStrPatCondL());
				if(voObj.getStrIsBedVacant().trim().equals("1"))
				{	
				  daoObj.setProcInValue(nProcIndex, "gnum_join_room_code", voObj.getStrRoom());
				  daoObj.setProcInValue(nProcIndex, "gnum_join_bed_code", voObj.getStrBed());
				}
				else 
				{
				  daoObj.setProcInValue(nProcIndex, "gnum_join_room_code", voObj.getStrRoomCode());
				  daoObj.setProcInValue(nProcIndex, "gnum_join_bed_code", voObj.getStrBedCode());
				}
				daoObj.setProcInValue(nProcIndex, "hipdt_leave_availed_to_dt", voObj.getStrCtDate());
				daoObj.setProcInValue(nProcIndex, "hipnum_join_remarks", voObj.getStrRsnJ());
				daoObj.setProcInValue(nProcIndex, "hipnum_join_by", voObj.getStrRmkJ());
				daoObj.setProcInValue(nProcIndex, "hipnum_leave_status", voObj.getStrLeaveStatusCode());
				daoObj.setProcInValue(nProcIndex, "hipdt_admstatus_code", voObj.getStrAdmStatCode());
				daoObj.setProcOutValue(nProcIndex, "err", 1);
				
				//
				daoObj.setProcInValue(nProcIndex, "hipdt_leave_availed_from_dt", "");				
				daoObj.setProcInValue(nProcIndex, "hipnum_advice_given","");								
			     daoObj.setProcInValue(nProcIndex, "GNUM_LEAVE_FROM_DEPT_CODE","");  
			     daoObj.setProcInValue(nProcIndex, "GNUM_LEAVE_FROM_DEPTUNIT_CODE","");
			     daoObj.setProcInValue(nProcIndex, "HIPNUM_LEAVE_FROM_WARD_CODE","");
			     daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_dt","");
			     daoObj.setProcInValue(nProcIndex, "HIPDT_LEAVE_FROM_DT","");
			     daoObj.setProcInValue(nProcIndex, "HIPDT_LEAVE_TO_DT","");
			     daoObj.setProcInValue(nProcIndex, "hipstr_leave_sanction_by","");
			     daoObj.setProcInValue(nProcIndex, "HIPSTR_LEAVE_REMARKS","");
			     daoObj.setProcInValue(nProcIndex, "hipdt_leave_join_dt","");
			    
			     daoObj.setProcInValue(nProcIndex, "GDT_ENTRY_DATE","");
			     daoObj.setProcInValue(nProcIndex, "GNUM_ISVALID","1");
			     daoObj.setProcInValue(nProcIndex, "GNUM_SEAT_ID","");
			     daoObj.setProcInValue(nProcIndex, "HIPSTR_PAT_LEAVE_COND","");			     
			     daoObj.setProcInValue(nProcIndex, "hipnum_phone_no","");                    
			     daoObj.setProcInValue(nProcIndex, "hipnum_address","");
			     daoObj.setProcInValue(nProcIndex, "hipnum_leave_from_room_code","");
			     daoObj.setProcInValue(nProcIndex, "hipnum_leave_from_bed_code","");			    
			     daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_from_dt","");
			     daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_to_dt","");			     
			     daoObj.setProcInValue(nProcIndex, "hipnum_approval_reject_remark","");
			     daoObj.setProcInValue(nProcIndex, "hipnum_is_bed_vacant", "");
								
				
				daoObj.execute(nProcIndex,1);
				
				/* calling of Proc_movement_Acceptance(?,?,?,?,?,?,?,?,?,?,?,?)*/
				
				//nProcIndex2 = daoObj.setProcedure(strProcName2);
				daoObj.setProcInValue(nProcIndex2, "hipnum_admno",voObj.getCurAdmNo());
				daoObj.setProcInValue(nProcIndex2, "gnum_hospital_code", voObj.getStrHospCode());
				daoObj.setProcInValue(nProcIndex2, "hrgnum_puk",voObj.getStrCrNo());
				daoObj.setProcInValue(nProcIndex2, "gnum_seatid",voObj.getStrSeatId());
				daoObj.setProcInValue(nProcIndex2, "gnum_dept_code", voObj.getStrDeptCode());
				daoObj.setProcInValue(nProcIndex2, "gnum_deptunit_code ", voObj.getStrDeptUnitCode());
				daoObj.setProcInValue(nProcIndex2, "hipnum_ward_code", voObj.getStrWardCode());
				if(voObj.getStrIsBedVacant().trim().equals("1"))
				{	
				  daoObj.setProcInValue(nProcIndex2, "hipnum_room_code", voObj.getStrRoom());
				  daoObj.setProcInValue(nProcIndex2, "hipnum_bed_code", voObj.getStrBed());
				}
				else 
				{
				  daoObj.setProcInValue(nProcIndex2, "hipnum_room_code", voObj.getStrRoomCode());
				  daoObj.setProcInValue(nProcIndex2, "hipnum_bed_code", voObj.getStrBedCode());
				}
				daoObj.setProcInValue(nProcIndex2, "hipnum_transin_flg", "9");
				daoObj.setProcOutValue(nProcIndex2, "err", 1);
				daoObj.setProcOutValue(nProcIndex2, "dml_count", 1);
				
				daoObj.setProcInValue(nProcIndex2, "code_remarks", "");
				daoObj.setProcInValue(nProcIndex2, "belonging_remarks", "");
				
//
				
				
				daoObj.execute(nProcIndex2, 1);
			synchronized (daoObj) {
				daoObj.fire();
			} 
			strErr1 = daoObj.getString(nProcIndex, "err");	
			strErr2 = daoObj.getString(nProcIndex2, "err");
			//System.out.println("strErr1->"+strErr1);	
		//	System.out.println("strErr2->"+strErr2);	
				if (strErr1 == null && strErr2==null)
				{
					strErr1 = "";
					strErr2 = "";
				}
				     if (strErr1.equals("") && strErr2.equals("") )
				     {
				 //   	 System.out.println("err blank");
				    	 voObj.setStrMsgType("0");
				    	 voObj.setStrNormalMsgString("Record is successfully Updated");
				     }
				     else
				     {
				   // 	 System.out.println("inside err");
				    	 throw new Exception(strErr1+"#"+strErr2);
				     }
		} catch (Exception e) {
			voObj.setStrErrMsgString("PatientLeaveDAO.upadateLeaveApprovalDtl() -->"+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
   
   /**************Leave & Joining ENDS***************/
}
