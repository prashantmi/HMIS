package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class BillRePrintTransDAO {
	
	
	// from bala
	
	
	
	/**
	 *         Insert Account Dtl Method    INSERT DATA INTO
	 *         HBLT_PATACCOUNT_DTL            
	 *         */
	/*public static boolean insertBillDtls(BillRePrintTransVO vo) 
	{
	    String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_DML.dml_hblt_bill_reprint_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int procIndex1 = 0;
		String strerr = "";
		//WebRowSet ws = null;
		boolean retVal = false;
		try {

			dao = new HisDAO("billing",
					"transactions.BillRePrint.insertBillDtls()");

			procIndex1 = dao.setProcedure(proc_name1);

			
if(vo.getStrPukNo().equals("-")|| vo.getStrPukNo().length() == 1){
				
				vo.setStrPukNo("0");
				
			}
			
			// set value
			
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHosCode());

			dao.setProcInValue(procIndex1, "billNo",vo.getStrBillNo());
			
			dao.setProcInValue(procIndex1, "puk",vo.getStrPukNo());
			
			
			dao.setProcInValue(procIndex1, "patName",vo.getStrPatientName());
			
			
			dao.setProcInValue(procIndex1, "bServiceId",vo.getStrBServiceId());
						
            dao.setProcInValue(procIndex1, "chargeTypeId",vo.getStrChargeTypeID());
			
			dao.setProcInValue(procIndex1, "printCharge",vo.getStrRePrintChg());
			
			dao.setProcInValue(procIndex1, "catCode",vo.getStrPatientCatCode());
			

			dao.setProcInValue(procIndex1, "moduleId",vo.getStrBillService());
			
            dao.setProcInValue(procIndex1, "ipAddress",vo.getStrIpAddr());
            
            dao.setProcInValue(procIndex1, "accNo",vo.getStrPatActNo());
			
			dao.setProcInValue(procIndex1, "admNo",vo.getStrAdmnNo());
				
			dao.setProcInValue(procIndex1, "deptCode",vo.getStrDeptCode());
					
			dao.setProcInValue(procIndex1, "wardId",vo.getStrWardCode());
					            
			dao.setProcInValue(procIndex1, "seatId",vo.getStrSeatId());
            
			dao.setProcInValue(procIndex1, "prepuk",vo.getStrPreviousPukNo());
			
			dao.setProcOutValue(procIndex1, "recNo",1);
			
			dao.setProcOutValue(procIndex1, "ERR",1); // 1 for string return
														// value
			// execute procedure

		 	 dao.executeProcedure(procIndex1);

			// get value

			strerr = dao.getString(procIndex1, "ERR");
      			
			if (strerr == null)
				strerr = "";

			if (strerr.equals(""))
			{
				retVal = true;
				vo.setStrMsgType("0");
			}
			else
			{
				throw new Exception(strerr);
			}

		} 
		catch (Exception e) 
		  {
			e.printStackTrace();
    		vo.setStrMsgString("IpdBillManagementTransDAO.InsertAccountDtl() --> "+ e.getMessage());
			vo.setStrMsgType("1");
          }

		finally {

			if (dao != null) {

				dao.free();

				dao = null;

			}

		}
		 return retVal;

	}
	*/
	
	public static void getBillListingDtl(BillRePrintTransVO voObj) {
	
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strErr = "";
		
		String strCase = voObj.getStrCase();
		String strSearchString = voObj.getStrSearchString();
		String strHosCode = voObj.getStrHosCode();
		String strFromRows = voObj.getStrBillFromRow();
		String strToRows = voObj.getStrBillToRow();
		String strFromDate = voObj.getStrFromDate();
		String strToDate = voObj.getStrToDate();
		String strModeType = "";
		
		if(strCase.equals("1"))
		{
			 strModeType = "4";
		}
		else
		{
			 strModeType = "5";
		}
		
		if(strSearchString == null || strSearchString.equals("")) strSearchString = "";
		
		String strProcName = "{call pkg_bill_view.Proc_Hblt_Bill_Reciept_Dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		
		try {
			daoObj = new HisDAO("Bill RePrint Trans ","BillRePrintTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", strModeType,8);
			daoObj.setProcInValue(nProcIndex, "hosp_code",strHosCode,1);
				if(voObj.getStrSearchType().equals("1"))
				{
					daoObj.setProcInValue(nProcIndex, "pukNo", strSearchString,2);
					daoObj.setProcInValue(nProcIndex, "patName", "",3);
				}
				else
				{
					daoObj.setProcInValue(nProcIndex, "pukNo", "",2);
					daoObj.setProcInValue(nProcIndex, "patName", strSearchString,3);
				}
				daoObj.setProcInValue(nProcIndex, "frmDate", strFromDate,4);
				daoObj.setProcInValue(nProcIndex, "toDate", strToDate,5);
				daoObj.setProcInValue(nProcIndex, "frmRows", strFromRows,6);
				daoObj.setProcInValue(nProcIndex, "toRows", strToRows,7);
				daoObj.setProcInValue(nProcIndex, "billNo", "",9);
				daoObj.setProcInValue(nProcIndex, "recNo", "",10);
				daoObj.setProcOutValue(nProcIndex, "err", 1,11);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,12);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {
					
					voObj.setBillSearchList(ws);

				} else {
					throw new Exception(strErr);
				}
			

		} catch (Exception e) {
		e.printStackTrace();
			//System.out.println("dao exception");
			voObj.setStrMsgString("BillRePrintTransDAO.getBillListingDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	
	public static void getBillDtl(BillRePrintTransVO voObj) 
	{
	    HisDAO daoObj = null;
		WebRowSet ws = null;

		String strErr = "";
		String strHosCode = voObj.getStrHosCode();
		String strModeType = "3";
		
				
		String strProcName = "{call pkg_bill_view.Proc_Hblt_Bill_Reciept_Dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		
		try {
			daoObj = new HisDAO("Bill RePrint Trans ","BillRePrintTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval", strModeType,8);
				daoObj.setProcInValue(nProcIndex, "billNo", voObj.getStrBillNo(),9);
				daoObj.setProcInValue(nProcIndex, "recNo", voObj.getStrRcptNo(),10);
				daoObj.setProcInValue(nProcIndex, "hosp_code",strHosCode,1);
				daoObj.setProcInValue(nProcIndex, "pukNo", "",2);
				daoObj.setProcInValue(nProcIndex, "patName", "",3);
				daoObj.setProcInValue(nProcIndex, "frmDate", "",4);
				daoObj.setProcInValue(nProcIndex, "toDate", "",5);
				daoObj.setProcInValue(nProcIndex, "frmRows", "",6);
				daoObj.setProcInValue(nProcIndex, "toRows", "",7);
				daoObj.setProcOutValue(nProcIndex, "err", 1,11);
				daoObj.setProcOutValue(nProcIndex, "resultset",2, 12);
				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (strErr.equals("")) {
					voObj.setBillDetails(ws);
				} else {
					throw new Exception(strErr);
				}

		}
		catch (Exception e)
		{
		   voObj.setStrMsgString("BillRePrintTransDAO.getBillDtl() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	

}
