package ipd.transactions;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import javax.sql.rowset.WebRowSet;

public class  PatientLeaveApprovalTransDAO
{

	/**
    This function is used to set comsultant code and consultant name on the behalf of puk no
    **/
  public static void setConsultantName( PatientLeaveApprovalTransVO voObj)
  {
	String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
    int nProcIndex = 0;
    String strErr = "";
    HisDAO daoObj = null;
    WebRowSet ws = null;
    String temp="";
	HisUtil  util=null;
	util = new HisUtil("ipd"," PatientLeaveApprovalTransDAO");
    String strDeptUnitCode=voObj.getStrDeptUnitCode();
    try {
      daoObj = new HisDAO("Patient Leave"," PatientLeaveApprovalTransDAO");
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
         voObj.setStrErrMsgString(" PatientLeaveApprovalTransDAO.setConsultantName() --> "+ e.getMessage());
          voObj.setStrMsgType("1");
  } finally {
   if(daoObj != null) {
      daoObj.free();
      daoObj = null;
   }
  }
 }

 
   public WebRowSet getAdvisedBy( PatientLeaveApprovalTransVO voObj)
   {
	    WebRowSet ws = null;
	    String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
		try {
		  daoObj = new HisDAO("ipd","transactions. PatientLeaveApprovalTransDAO .getAdvisedBy()");
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
			voObj.setStrErrMsgString(" PatientLeaveApprovalTransDAO.getAdvisedBy() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			daoObj.free();
			daoObj = null;
		}
       return ws;
   }

   public void getRsnRmk( PatientLeaveApprovalTransVO voObj)
   {
	   HisUtil hisutil = new HisUtil("transaction", " PatientLeaveApprovalTransDAO");
	   WebRowSet w1;
	   try
	  {
	     w1=getAdvisedBy(voObj);
		 voObj.setStrDisBy(w1);
		 String str1 = hisutil.getOptionValue(voObj.getStrDisBy(), "0", "0^Select value", true);
		 voObj.setStrRmk(str1);
	  }
	  catch(Exception e)
	  {
		 voObj.setStrErrMsgString(" PatientLeaveApprovalTransDAO.getRsnRmk() -->"+ e.getMessage());
		 voObj.setStrMsgType("1");
	  }
	  w1=null;
   }
 
   /*********PROC for getting Adm Status Code**********/

   public  WebRowSet admStatus( PatientLeaveApprovalTransVO voObj)
   {
	   WebRowSet ws = null;
		String proc_name1 = "";
	    proc_name1 = "{call pkg_ipd_view.PROC_PAT_ADMSTATUS_CODE_DTL(?,?,?,?,?,?,?)}";
	    HisDAO dao = null;
	    int procIndex1 = 0;
	    String strerr = "";
	  //  System.out.println("strCrno in DAOadmStatus->"+voObj.getStrCrNo());
	    try {
	     dao = new HisDAO("ipd","transactions.PatientLeaveApprovalTransDAO.admStatus()");
	     procIndex1 = dao.setProcedure(proc_name1);
	     dao.setProcInValue(procIndex1, "modval","1",1);
	     dao.setProcInValue(procIndex1, "hipnum_admno","",2);
	     dao.setProcInValue(procIndex1, "hrgnum_puk",voObj.getStrCrNo(),3);
	     dao.setProcInValue(procIndex1, "gnum_hospital_code",voObj.getStrHospCode(),4);
	     dao.setProcInValue(procIndex1, "issuereq","2",5);
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
    	voObj.setStrErrMsgString(" PatientLeaveApprovalTransDAO.admStatus() -->"+ e.getMessage());
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

  
   
   /*********select Leave Request Details********/
   
   public static void setLeaveDtl(PatientLeaveApprovalTransVO voObj) {
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strProcName = "{call pkg_ipd_view.proc_hipt_pat_leave_dtl(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Leave Approval Trans","PatientLeaveApprovalTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			   daoObj.setProcInValue(nProcIndex, "modeVal","1",1);
				daoObj.setProcInValue(nProcIndex, "puk",voObj.getStrCrNo(),2);
				daoObj.setProcInValue(nProcIndex, "patListType","0",3);
				daoObj.setProcInValue(nProcIndex, "searchStr","",4);
				daoObj.setProcInValue(nProcIndex, "searchType","1",5);
				daoObj.setProcInValue(nProcIndex, "frmRows","0",6);
				daoObj.setProcInValue(nProcIndex, "toRows","0",7);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),8);
				daoObj.setProcOutValue(nProcIndex, "err", 1,9);
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
			voObj.setStrErrMsgString("PatientLeaveApprovalTransDAO.setLeaveDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
   
   /*********update Leave Approval Details**********/
   
   public static void upadateLeaveApprovalDtl(PatientLeaveApprovalTransVO voObj) {

		HisDAO daoObj = null;
		String strProcName = "{call pkg_ipd_dml.PROC_PAT_LEAVE_JOIN_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try {
			daoObj = new HisDAO("Patient Leave Approval Trans","PatientLeaveApprovalTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			     daoObj.setProcInValue(nProcIndex, "modval","3",1);
			     daoObj.setProcInValue(nProcIndex, "hipnum_admno",voObj.getCurAdmNo(),2);
				  daoObj.setProcInValue(nProcIndex, "hrgnum_puk",voObj.getStrCrNo(),3);
				  daoObj.setProcInValue(nProcIndex, "hipnum_leave_slno", voObj.getStrLeaveSlNo(),4);
				  daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", voObj.getStrHospCode(),5);
				  daoObj.setProcInValue(nProcIndex, "GNUM_LEAVE_FROM_DEPT_CODE","",6);  
			     daoObj.setProcInValue(nProcIndex, "GNUM_LEAVE_FROM_DEPTUNIT_CODE","",7);
			     daoObj.setProcInValue(nProcIndex, "HIPNUM_LEAVE_FROM_WARD_CODE","",8);
			     daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_dt", voObj.getStrCtDate(),9);
			     daoObj.setProcInValue(nProcIndex, "HIPDT_LEAVE_FROM_DT","",10);
			     daoObj.setProcInValue(nProcIndex, "HIPDT_LEAVE_TO_DT","",11);
			     daoObj.setProcInValue(nProcIndex, "hipstr_leave_sanction_by","",12);
			     daoObj.setProcInValue(nProcIndex, "HIPSTR_LEAVE_REMARKS","",13);
			     daoObj.setProcInValue(nProcIndex, "hipdt_leave_join_dt","",14);
			     daoObj.setProcInValue(nProcIndex, "gnum_join_dept_code","",15);
			     daoObj.setProcInValue(nProcIndex, "gnum_join_deptunit_code","",16);
			     daoObj.setProcInValue(nProcIndex, "hipnum_join_ward_code","",17);
			     daoObj.setProcInValue(nProcIndex, "GDT_ENTRY_DATE","",18);
			     daoObj.setProcInValue(nProcIndex, "GNUM_ISVALID","1",19);
			     daoObj.setProcInValue(nProcIndex, "GNUM_SEAT_ID","",20);
			     daoObj.setProcInValue(nProcIndex, "HIPSTR_PAT_LEAVE_COND","",21);
			     daoObj.setProcInValue(nProcIndex, "hipstr_pat_join_cond","",22);
			     daoObj.setProcInValue(nProcIndex, "hipnum_phone_no","",23);                    
			     daoObj.setProcInValue(nProcIndex, "hipnum_address","",24);
			     daoObj.setProcInValue(nProcIndex, "hipnum_leave_from_room_code","",25);
			     daoObj.setProcInValue(nProcIndex, "hipnum_leave_from_bed_code","",26);
			     daoObj.setProcInValue(nProcIndex, "gnum_join_room_code","",27);
			     daoObj.setProcInValue(nProcIndex, "gnum_join_bed_code","",28);
			     
				if(voObj.getStrLeaveStatusCode().equals("2"))
				{
				  daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_from_dt", voObj.getStrValidFrom(),29);
				  daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_to_dt", voObj.getStrValidTo(),30);
				}
				else
				{
				  daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_from_dt", "",29);
				  daoObj.setProcInValue(nProcIndex, "hipdt_leave_sanction_to_dt", "",30);
				}
				daoObj.setProcInValue(nProcIndex, "hipdt_leave_availed_from_dt","",31);
				daoObj.setProcInValue(nProcIndex, "hipdt_leave_availed_to_dt","",32);	
				daoObj.setProcInValue(nProcIndex, "hipnum_advice_given","",33);
				daoObj.setProcInValue(nProcIndex, "hipnum_is_bed_vacant", "",34);
				daoObj.setProcInValue(nProcIndex, "hipnum_join_remarks","",35);
			   daoObj.setProcInValue(nProcIndex, "hipnum_join_by","",36); 
				daoObj.setProcInValue(nProcIndex, "hipnum_leave_status", voObj.getStrLeaveStatusCode(),37);
				daoObj.setProcInValue(nProcIndex, "hipnum_approval_reject_remark ", voObj.getStrApprRejectRmk(),38);
				daoObj.setProcInValue(nProcIndex, "hipdt_admstatus_code","12",39);
				daoObj.setProcInValue(nProcIndex, "strLeaveType",voObj.getStrLeaveType(),40);
				daoObj.setProcOutValue(nProcIndex, "err", 1,41);
				
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				     if (strErr.equals(""))
				     {
				    	 voObj.setStrMsgType("0");
				    	 voObj.setStrNormalMsgString("Record Successfully Updated");
				     }
				     else
				     {
				      throw new Exception(strErr);
				     }
		} catch (Exception e) {

			voObj.setStrErrMsgString("PatientLeaveApprovalTransDAO.upadateLeaveApprovalDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
   /**************Leave Request Approval ENDS***************/
}