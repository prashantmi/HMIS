package ipd.transactions;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;
import javax.sql.rowset.WebRowSet;

public class PatientLeaveRequestTransDAO
{

	/**
    This function is used to set comsultant code and consultant name on the behalf of puk no
    **/
  public static void setConsultantName(PatientLeaveRequestTransVO voObj)
  {
	String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
    int nProcIndex = 0;
    String strErr = "";
    HisDAO daoObj = null;
    WebRowSet ws = null;
    String temp="";
	HisUtil  util=null;
	util = new HisUtil("ipd","PatientLeaveRequestTransDAO");
    String strDeptUnitCode=voObj.getStrDeptUnitCode();
    try {
      daoObj = new HisDAO("Patient Leave","PatientLeaveRequestTransDAO");
      nProcIndex = daoObj.setProcedure(strProcName);
      daoObj.setProcInValue(nProcIndex, "modval", "1",1);
      daoObj.setProcInValue(nProcIndex, "deptunitcode", strDeptUnitCode,2);
      daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode(),3);
      daoObj.setProcInValue(nProcIndex, "seatid", "",4);
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
         voObj.setStrErrMsgString("PatientLeaveRequestTransDAO.setConsultantName() --> "+ e.getMessage());
          voObj.setStrMsgType("1");
  } finally {
   if(daoObj != null) {
      daoObj.free();
      daoObj = null;
   }
  }
 }

 
   public WebRowSet getAdvisedBy(PatientLeaveRequestTransVO voObj)
   {
	    WebRowSet ws = null;
	    String strProcName = "{call pkg_ipd_view.proc_unit_consulatant_view(?,?,?,?,?,?)}";
		HisDAO daoObj = null;
		int nProcIndex = 0;
		String strErr = "";
		try {
		  daoObj = new HisDAO("ipd","transactions.PatientLeaveRequestTransDAO .getAdvisedBy()");
		  nProcIndex = daoObj.setProcedure(strProcName);
		  daoObj.setProcInValue(nProcIndex, "modVal", "2");
		  daoObj.setProcInValue(nProcIndex, "deptunitcode", "");
	      daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospCode());
	      daoObj.setProcInValue(nProcIndex, "seatid", "");
	      daoObj.setProcOutValue(nProcIndex, "err", 1);
	      daoObj.setProcOutValue(nProcIndex, "resultset", 2);
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
			voObj.setStrErrMsgString("PatientLeaveRequestTransDAO.getAdvisedBy() --> "
					+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			daoObj.free();
			daoObj = null;
		}
       return ws;
   }

   public void getRsnRmk(PatientLeaveRequestTransVO voObj)
   {
	   HisUtil hisutil = new HisUtil("transaction", "PatientLeaveRequestTransDAO");
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
		 voObj.setStrErrMsgString("PatientLeaveRequestTransDAO.getRsnRmk() -->"+ e.getMessage());
		 voObj.setStrMsgType("1");
	  }
	  w1=null;
   }
  
   /*********PROC for getting Adm Status Code**********/

   public  WebRowSet admStatus(PatientLeaveRequestTransVO voObj)
   {
	WebRowSet ws = null;
	String proc_name1 = "";
    proc_name1 = "{call pkg_ipd_view.PROC_PAT_ADMSTATUS_CODE_DTL(?,?,?,?,?,?,?)}";
    HisDAO dao = null;
    int procIndex1 = 0;
    String strerr = "";
    try {
     dao = new HisDAO("ipd","transactions.PatientLeaveRequestTransDAO.admStatus()");
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
    	 voObj.setStrErrMsgString("PatientLeaveRequestTransDAO.admStatus() -->"+ e.getMessage());
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
   
   /********validate Leave Request***********/
   public static boolean getLeaveRequestValidate(PatientLeaveRequestTransVO voObj)
   {
	    HisDAO dao = null;
	    int funcIndex = 0;
	    int i=0;
	    boolean retVal = false;
	    String ret="false";
        try
        {  
          dao = new HisDAO("Patient Leave Request Trans","PatientLeaveRequestTransDAO");
          funcIndex = dao.setFunction("{? = call IPD_MST.getLeaveStatus(?::numeric,?::numeric,?::numeric)}");
          dao.setFuncInValue(funcIndex,2,"1");
          dao.setFuncInValue(funcIndex,3,voObj.getStrCrNo());
          dao.setFuncInValue(funcIndex,4,voObj.getStrHospCode());
          dao.setFuncOutValue(funcIndex,3);
          // Execute Function
          dao.executeFuncForNumeric(funcIndex);
          // returns
          ret = dao.getFuncNumeric(funcIndex);
          i=Integer.parseInt(ret);
          if(i==0)
        	retVal=true;
          else
        	retVal=false;  
        }
        catch(Exception e)
        {
        	voObj.setStrErrMsgString("PatientLeaveRequestTransDAO.getLeaveRequestValidate() --> "+ e.getMessage());
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

   /*****Insert Starts******/
   public  static void patMov_LJ(PatientLeaveRequestTransVO voObj)
   {
    String proc_name1 = "";
    proc_name1 = "{call pkg_ipd_dml.PROC_PAT_LEAVE_JOIN_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    HisDAO dao = null;
    int procIndex1 = 0;
    String strerr = "";
    try {
     dao = new HisDAO("ipd","transactions.PatientLeaveRequestTransDAO.patMov_LJ()");
     procIndex1 = dao.setProcedure(proc_name1);
     dao.setProcInValue(procIndex1, "modval","1",1);
     dao.setProcInValue(procIndex1, "hipnum_admno",voObj.getStrAdmNo(),2);
     dao.setProcInValue(procIndex1, "hrgnum_puk",voObj.getStrCrNo(),3);
     dao.setProcInValue(procIndex1, "hipnum_leave_slno","",4);
     dao.setProcInValue(procIndex1, "GNUM_HOSPITAL_CODE",voObj.getStrHospCode(),5);
     dao.setProcInValue(procIndex1, "GNUM_LEAVE_FROM_DEPT_CODE",voObj.getStrDeptCode(),6);  
     dao.setProcInValue(procIndex1, "GNUM_LEAVE_FROM_DEPTUNIT_CODE",voObj.getStrDeptUnitCode(),7);
     dao.setProcInValue(procIndex1, "HIPNUM_LEAVE_FROM_WARD_CODE",voObj.getStrWardCode(),8);
     dao.setProcInValue(procIndex1, "hipdt_leave_sanction_dt","",9);
     dao.setProcInValue(procIndex1, "HIPDT_LEAVE_FROM_DT",voObj.getStrValidFrom(),10);
     dao.setProcInValue(procIndex1, "HIPDT_LEAVE_TO_DT",voObj.getStrValidTo(),11);
     dao.setProcInValue(procIndex1, "hipstr_leave_sanction_by","",12);
     dao.setProcInValue(procIndex1, "HIPSTR_LEAVE_REMARKS",voObj.getStrRsn(),13);
     dao.setProcInValue(procIndex1, "hipdt_leave_join_dt","",14);
     dao.setProcInValue(procIndex1, "gnum_join_dept_code","",15);
     dao.setProcInValue(procIndex1, "gnum_join_deptunit_code","",16);
     dao.setProcInValue(procIndex1, "hipnum_join_ward_code","",17);
     dao.setProcInValue(procIndex1, "GDT_ENTRY_DATE",voObj.getStrEntryDate(),18);
     dao.setProcInValue(procIndex1, "GNUM_ISVALID","1",19);
     dao.setProcInValue(procIndex1, "GNUM_SEAT_ID",voObj.getStrSeatId(),20 );
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
     dao.setProcInValue(procIndex1, "hipdt_leave_availed_from_dt","",31);
     dao.setProcInValue(procIndex1, "hipdt_leave_availed_to_dt","",32);
     dao.setProcInValue(procIndex1, "hipnum_advice_given","",33);
     dao.setProcInValue(procIndex1, "hipnum_is_bed_vacant", voObj.getStrIsBedVacant() == null
					|| voObj.getStrIsBedVacant().trim().equals("") ? "0" : "1",34);
     dao.setProcInValue(procIndex1, "hipnum_join_remarks","",35);
     dao.setProcInValue(procIndex1, "hipnum_join_by","",36);
     dao.setProcInValue(procIndex1, "hipnum_leave_status",voObj.getStrLeaveStatusCode(),37);
     dao.setProcInValue(procIndex1, "hipnum_approval_reject_remark","",38);
     dao.setProcInValue(procIndex1, "hipdt_admstatus_code","12",39);
     dao.setProcInValue(procIndex1, "strLeaveType",voObj.getStrLeaveType(),40);
     dao.setProcOutValue(procIndex1, "err",1,41); // 1 for string return
     
     // execute procedure
      dao.executeProcedureByPosition(procIndex1);
     // get value
     strerr = dao.getString(procIndex1, "err");
     if (strerr == null)
      strerr = "";
     if (strerr.equals(""))
     {
      voObj.setStrMsgType("0");
      voObj.setStrNormalMsgString("Record Successfully Inserted and Updated");
     }
     else
     {
      throw new Exception(strerr);
     }
    }
    catch (Exception e)
    {
    	voObj.setStrErrMsgString("PatientLeaveRequestTransDAO.patMov_LJ() --> "+ e.getMessage());
    	voObj.setStrMsgType("1");	
    }
    finally {
     if (dao != null) {
      dao.free();
      dao = null;
     }
    }
  }
   /*********insert complete**********/
}
