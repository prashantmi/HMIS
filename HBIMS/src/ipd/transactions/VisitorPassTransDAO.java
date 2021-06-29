package ipd.transactions;

import ipd.IpdConfigUtil;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class VisitorPassTransDAO {
	
	/**
	 * This function is used to set Department name on main page
	 * @param vo
	 */
	public static void setvisitPatientName(VisitorPassTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_visitadmission_advice_dtl(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
				daoObj = new HisDAO("Visitor Pass",
					"VisitorPassTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval", "1",1);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum,2);
				daoObj.setProcInValue(nProcIndex, "admNo", "",3);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),4);
				daoObj.setProcOutValue(nProcIndex, "err", 1,5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					
					if (ws.next()) {
						
						vo.setStrAdmnNo(ws.getString(1));
						vo.setStrAdmnDate(ws.getString(2));
						vo.setStrRoomBed(ws.getString(6));
						vo.setStrWard(ws.getString(7));
					}

				} else {
					throw new Exception(strErr);
				}
		
		} catch (Exception e) {
			vo.setStrMsgString("VisitorPassTransDAO.setvisitPatientName() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void insertvisitorpassdtl(VisitorPassTransVO vo)
	{
		String strProcName1 = "{call PKG_IPD_DML.proc_hipt_visitor_pass_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?::numeric,?::integer,?)}";
		String strProcName2= "{call PKG_IPD_DML.proc_hipt_visitor_pass_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?::numeric,?::integer,?)}";
		String err="";
				
		String [] hpasstype=vo.getStrhPassType();
		String [] issueDate=vo.getStrIssueRenewDt();
		String [] hvalidfrom = vo.getStrhValidFrom();
		String [] strPassno=vo.getStrPass_No();
		String [] strChk=vo.getStrchkvisit();
		String [] paidAmmount=vo.getStrPaidAmount();
		String [] validUptoh=vo.getStrValidUptoR();
		String selectedChk[]=null;
		IpdConfigUtil ipdconfig=null;
		int nProcIndex = 0;
		HisDAO daoObj=null;
		int iLength=0;

		try
		{
			ipdconfig=new IpdConfigUtil(vo.getStrHospitalCode());
			daoObj=new HisDAO("Visitor Pass","VisitorPassTransDAO");
			if(vo.getStrNewRenew().equals("1"))
			{
				for(int i=0;i<paidAmmount.length-1;i++)
				{
					paidAmmount[i]=paidAmmount[i+1];
				}
				
				for(int i=0;i<strChk.length;i++)
				{
				
					int k=Integer.parseInt(strChk[i]);
					nProcIndex = daoObj.setProcedure(strProcName1);
					daoObj.setProcInValue(nProcIndex, "modval", "1",1);
					daoObj.setProcInValue(nProcIndex, "hipdt_issue_date", issueDate[k-1],2);
					daoObj.setProcInValue(nProcIndex, "hipdt_valid_from", hvalidfrom[k-1],3);
					daoObj.setProcInValue(nProcIndex, "hipdt_valid_to", validUptoh[k-1],4);
					daoObj.setProcInValue(nProcIndex, "hipnum_pass_type", hpasstype[k-1],5);
					daoObj.setProcInValue(nProcIndex, "hipnum_renew_flg", vo.getStrNewRenew(),6);
					daoObj.setProcInValue(nProcIndex, "gdt_entry_date", "",7);
					daoObj.setProcInValue(nProcIndex, "gnum_seatid", vo.getStrSeatId(),8);
					daoObj.setProcInValue(nProcIndex, "gnum_isvalid", "1",9);
					daoObj.setProcInValue(nProcIndex, "hipnum_paid_amount",paidAmmount[k-1],10 );
					daoObj.setProcInValue(nProcIndex, "hipnum_admno", vo.getStrAdmnNo(),11);
					daoObj.setProcInValue(nProcIndex, "pass_no", "1",12);
					daoObj.setProcInValue(nProcIndex, "hipnum_pass_slno", "",13);
					daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospitalCode(),14);
					daoObj.setProcInValue(nProcIndex, "hrgnum_puk", vo.getStrCrNo(),15);
					daoObj.setProcOutValue(nProcIndex, "err",1,16); 
					daoObj.setProcOutValue(nProcIndex, "dml_count",1,17);
					daoObj.setProcInValue(nProcIndex, "hipnum_pass_no", strPassno[k-1],18);
					daoObj.setProcInValue(nProcIndex, "pass_valid_for", "",19);      
					daoObj.execute(nProcIndex, 1);
				}
							
			}else{
					String temp1[]=vo.getStrhPassType();
					String temp2[]=vo.getStrhValidFrom();
					String temp3[]=vo.getStrValidUptoR();
					String temp4[]=vo.getStrPaidAmount();
					selectedChk=vo.getStrSelectedChkNo().split(",");
					iLength=selectedChk.length;
					for(int j =0;j<iLength;j++)
					{
						if(!selectedChk[j].trim().equals("")){ 
						int no=Integer.parseInt(selectedChk[j]);
							 nProcIndex = daoObj.setProcedure(strProcName2);
							 daoObj.setProcInValue(nProcIndex, "modval", "1",1);
							 daoObj.setProcInValue(nProcIndex, "hipdt_issue_date","",2);
							 daoObj.setProcInValue(nProcIndex, "hipdt_valid_from", temp2[no],3);
							 daoObj.setProcInValue(nProcIndex, "hipdt_valid_to", temp3[no],4);
							 daoObj.setProcInValue(nProcIndex, "hipnum_pass_type", temp1[no],5);
							 daoObj.setProcInValue(nProcIndex, "hipnum_renew_flg", vo.getStrNewRenew(),6);
							 daoObj.setProcInValue(nProcIndex, "gdt_entry_date", "",7);
							 daoObj.setProcInValue(nProcIndex, "gnum_seatid", vo.getStrSeatId(),8);
							 daoObj.setProcInValue(nProcIndex, "gnum_isvalid", "1",9);
							 daoObj.setProcInValue(nProcIndex, "hipnum_paid_amount",temp4[no],10);
						    daoObj.setProcInValue(nProcIndex, "hipnum_admno", vo.getStrAdmnNo(),11);
						    daoObj.setProcInValue(nProcIndex, "hipnum_pass_no","",12);
							 daoObj.setProcInValue(nProcIndex, "hipnum_pass_slno", "",13);
							 daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospitalCode(),14);
							 daoObj.setProcInValue(nProcIndex, "hrgnum_puk", vo.getStrCrNo(),15);
							 daoObj.setProcOutValue(nProcIndex, "err",1,16); 
							 daoObj.setProcOutValue(nProcIndex, "dml_count",1,17);
							 daoObj.setProcInValue(nProcIndex, "pass_no", "1",18);
							 daoObj.setProcInValue(nProcIndex, "pass_valid_for", "",19);
							 daoObj.execute(nProcIndex, 1);
						}
				}
					
					
					
					if(ipdconfig.getStrAttendentPass().equals("1") && !ipdconfig.getStrAttendentPassGenerateAtAdmissionTime().equals("1"))
					{
						if(vo.getStrAttendentPassCheckBox().equals("1"))
						{
							nProcIndex = daoObj.setProcedure(strProcName2);
							daoObj.setProcInValue(nProcIndex, "modval", "1",1);
							 daoObj.setProcInValue(nProcIndex, "hipdt_issue_date","",2);
							 daoObj.setProcInValue(nProcIndex, "hipdt_valid_from", vo.getStrAttendentPassValidFrom(),3);
							 daoObj.setProcInValue(nProcIndex, "hipdt_valid_to", vo.getStrAttendentPassValidUpto(),4);
							 daoObj.setProcInValue(nProcIndex, "hipnum_pass_type", vo.getStrAttendentPassType(),5);
							 daoObj.setProcInValue(nProcIndex, "hipnum_renew_flg", "0",6);
							 daoObj.setProcInValue(nProcIndex, "gdt_entry_date", "",7);
							 daoObj.setProcInValue(nProcIndex, "gnum_seatid", vo.getStrSeatId(),8);
							 daoObj.setProcInValue(nProcIndex, "gnum_isvalid", "1",9);
							 daoObj.setProcInValue(nProcIndex, "hipnum_paid_amount",vo.getStrAttendentPassPaidAmount(),10);
						    daoObj.setProcInValue(nProcIndex, "hipnum_admno", vo.getStrAdmnNo(),11);
						    daoObj.setProcInValue(nProcIndex, "hipnum_pass_no","",12);
							 daoObj.setProcInValue(nProcIndex, "hipnum_pass_slno", "",13);
							 daoObj.setProcInValue(nProcIndex, "gnum_hospital_code", vo.getStrHospitalCode(),14);
							 daoObj.setProcInValue(nProcIndex, "hrgnum_puk", vo.getStrCrNo(),15);
							 daoObj.setProcOutValue(nProcIndex, "err",1,16); 
							 daoObj.setProcOutValue(nProcIndex, "dml_count",1,17);
							 daoObj.setProcInValue(nProcIndex, "pass_no", "1",18);
							 daoObj.setProcInValue(nProcIndex, "pass_valid_for", "",19);
							
							 daoObj.execute(nProcIndex, 1);
						}
					}
					 
			}
			synchronized (daoObj) {
				daoObj.fire();
			}
			
			if(err.equals(""))
			{
				vo.setStrMsgType("0");
			}
			else
			{
				throw new Exception(err);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("VisitorPassTransDAO.insertvisitorpassdtl() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
	
	public static void setvisitorPassdtl(VisitorPassTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_visitor_passprev_dtl(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
        
		HisDAO daoObj = null;
		WebRowSet ws = null;
		String strAdmnNum = vo.getStrAdmnNo();
		
		try {
				daoObj = new HisDAO("Visitor Pass","VisitorPassTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval", "1",1);
				daoObj.setProcInValue(nProcIndex, "admno", strAdmnNum,2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					vo.setPassDetailWS(ws);
				} else {
					throw new Exception(strErr);
				}
			} catch (Exception e) {
			vo.setStrMsgString("VisitorPassTransDAO.setvisitorPassdtl() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void setvisitorcount(VisitorPassTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.Proc_Visitor_Pass_Count(?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
        HisDAO daoObj = null;
		WebRowSet ws = null;
		
		String strAdmnNo = vo.getStrAdmnNo();
				
		try {
				daoObj = new HisDAO("Visitor Pass","VisitorPassTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				
				daoObj.setProcInValue(nProcIndex, "admNo", strAdmnNo,1);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),2);
				daoObj.setProcInValue(nProcIndex, "modeVal", "2",3);
				daoObj.setProcOutValue(nProcIndex, "err", 1,4);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					if(ws.next()){
						vo.setStrcountFree(ws.getString(1));
						vo.setStrcountPass(ws.getString(2));
						vo.setStrAttendancePassCount(ws.getString(3));
											
					}
				} else {
					throw new Exception(strErr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			vo.setStrMsgString("VisitorPassTransDAO.setvisitorcount() --> "
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
	/*public static void setPatientStatus(VisitorPassTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_pat_status(?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
			daoObj = new HisDAO("Visitor Pass",
			           "VisitorPassTransDAO");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "puk", strCrNum);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode());
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
			
			vo.setStrMsgString("VisitorPassTransDAO.setPatientStatus() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	*/
	/**
	 * This function is used to set patient details in hidden fields on the main page
	 * @param vo
	 */
	public static void setpatStatusCode(VisitorPassTransVO vo)
	{
		String strProcName = "{call pkg_ipd_view.proc_pat_admstatus_code_dtl(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strCrNum = vo.getStrCrNo();
		try {
			daoObj = new HisDAO("ipd", "transactions.VisitorPassTransDAO.setpatStatusCode()");
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modval", "1",1);
				daoObj.setProcInValue(nProcIndex, "hipnum_admno","",2);
				daoObj.setProcInValue(nProcIndex, "hrgnum_puk",strCrNum,3);
				daoObj.setProcInValue(nProcIndex, "gnum_hospital_code",vo.getStrHospitalCode(),4);
				daoObj.setProcInValue(nProcIndex, "issuereq","",5);
				daoObj.setProcOutValue(nProcIndex, "err", 1,6);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
				
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");				
				if (strErr.equals("")) 
				{
				  while(ws.next())
				  {
						vo.setStrAdmnStatusCode(ws.getString(1));
						vo.setStrPatDeadCode(ws.getString(2));}
				}
				 else {
					throw new Exception(strErr);
				}
			
		} catch (Exception e) {
			//
			vo.setStrMsgString("VisitorPassTransDAO.setpatStatusCode() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getVisitorPassDtl(VisitorPassTransVO vo)
	{
		
		int nProcIndex = 0;
		
		String strPassType[] = null;
		String strPassNo[] = null;
		String strIssueDate[] = null;
		String strValidFrom[] = null;
		String strValidTo[] = null;
		String strPassAmount[] = null;
		String strCrNo[] = null;
		String strAdmNo[] = null;
		String strPatName[] = null;
		String strMomName[] = null;
		String strMomCrNo[] = null;
		String strDeptName[] = null;
		String strWardName[] = null;
		String strRoomName[] = null;
		String strUnitName[] = null;
		String strAdmDtTime[] = null;
		String strIsNewBorn[] = null;
		String strIsMLC[] = null;
		String strFSName[] = null;
		
		int i = 0;
		
		String strErr = "";

		HisDAO daoObj = null;
		WebRowSet ws = null;

		try {
			String strProcName = "{call pkg_ipd_view.Proc_VisitorPass_List(?,?,?,?,?)}";
			daoObj = new HisDAO("ipd", "transactions.VisitorPassTransDAO.getVisitorPassDtl()");
			
				nProcIndex = daoObj.setProcedure(strProcName);
				
				daoObj.setProcInValue(nProcIndex, "HOSP_CODE",vo.getStrHospitalCode(),1);
				daoObj.setProcInValue(nProcIndex, "CRNO",vo.getStrCrNo(),2);
				daoObj.setProcInValue(nProcIndex, "admNo",vo.getStrAdmnNo(),3);
				daoObj.setProcOutValue(nProcIndex, "ERR", 1,4);
				daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "ERR");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				
				if (strErr.equals("")) 
				{
					strAdmNo = new String[ws.size()];
					strPassNo = new String[ws.size()];
					strCrNo = new String[ws.size()];
					strIssueDate = new String[ws.size()];
					strValidFrom = new String[ws.size()];
					strValidTo = new String[ws.size()];
					strPassType = new String[ws.size()];
					strPassAmount = new String[ws.size()];
					strPatName = new String[ws.size()];
					strMomName = new String[ws.size()];
					strMomCrNo = new String[ws.size()];
					strAdmDtTime = new String[ws.size()];
					strDeptName = new String[ws.size()];
					strWardName = new String[ws.size()];
					strUnitName = new String[ws.size()];
					strRoomName = new String[ws.size()];
					strIsNewBorn = new String[ws.size()];
					strIsMLC = new String[ws.size()];
					strFSName = new String[ws.size()];
				  while(ws.next())
				  {
					  
					  strAdmNo[i]= ws.getString(1);
					  strPassNo[i]= ws.getString(2);
					  strCrNo[i]= ws.getString(3);
					  strIssueDate[i]= ws.getString(4);
					  strValidFrom[i]= ws.getString(5);
					  strValidTo[i]= ws.getString(6);
					  strPassType[i]= ws.getString(7);
					  strPassAmount[i]= ws.getString(8);
					  strPatName[i]= ws.getString(9);
					  strMomName[i]= ws.getString(10);
					  strMomCrNo[i]= ws.getString(11);
					  strAdmDtTime[i]= ws.getString(12);
					  strDeptName[i]= ws.getString(13);
					  strUnitName[i]= ws.getString(14);
					  strWardName[i]= ws.getString(15);
					  strRoomName[i]= ws.getString(17);
					  strIsNewBorn[i] = ws.getString(18);
					  strIsMLC[i] = ws.getString(19);
					  strFSName[i] = ws.getString(20);
					  i++;
				  }
				  vo.setStrAdmissionNo(strAdmNo);
				  vo.setStrPassNo(strPassNo);
				  vo.setStrPuk(strCrNo);
				  vo.setStrIssueDate(strIssueDate);
				  vo.setStrValidFrom(strValidFrom);
				  vo.setStrValidTo(strValidTo);
				  vo.setStrPassType(strPassType);
				  vo.setStrPassAmount(strPassAmount);
				  vo.setStrPatName(strPatName);
				  vo.setStrMomName(strMomName);
				  vo.setStrMomCrNo(strMomCrNo);
				  vo.setStrAdmDtTime(strAdmDtTime);
				  vo.setStrDeptName(strDeptName);
				  vo.setStrWardName(strWardName);
				  vo.setStrRoomName(strRoomName);
				  vo.setStrUnitName(strUnitName);
				  vo.setStrIsNewBorn(strIsNewBorn);
				  vo.setStrIsMLC(strIsMLC);
				  vo.setStrFSName(strFSName);
				  
				} else {
					throw new Exception(strErr);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("VisitorPassTransDAO.getVisitorPassDtl() --> "
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