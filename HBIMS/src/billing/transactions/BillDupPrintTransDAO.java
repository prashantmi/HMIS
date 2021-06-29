package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class BillDupPrintTransDAO {
	
	
	// from bala
	
	
	
	/**
	 *         Insert Account Dtl Method    INSERT DATA INTO
	 *         HBLT_PATACCOUNT_DTL            
	 *         */
	public static boolean insertBillDtls(BillDupPrintTransVO vo) 
	{
	    String proc_name1 = "";

		proc_name1 = "{call PKG_BILL_DML.dml_hblt_bill_reprint_dtl(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int procIndex1 = 0;
		String strerr = "";
		String strRecNo = "";
		//WebRowSet ws = null;
		boolean retVal = false;
		try {

			dao = new HisDAO("billing",
					"transactions.BillRePrint.insertBillDtls()");

			procIndex1 = dao.setProcedure(proc_name1);

			
			if(vo.getStrPukNo().length() < 5){
				
				vo.setStrPukNo("0");
				
			}
			
			// set value
			
			dao.setProcInValue(procIndex1, "hosp_code", vo.getStrHosCode(),1);

			dao.setProcInValue(procIndex1, "billNo",vo.getStrBillNo(),2);
			
			dao.setProcInValue(procIndex1, "puk",vo.getStrPukNo(),3);
			
			
			dao.setProcInValue(procIndex1, "patName",vo.getStrPatientName(),5);
			
			
			dao.setProcInValue(procIndex1, "bServiceId",vo.getStrBServiceId(),6);
						
            dao.setProcInValue(procIndex1, "chargeTypeId",vo.getStrChargeTypeID(),7);
			
			dao.setProcInValue(procIndex1, "printCharge",vo.getStrRePrintChg(),8);
			
			dao.setProcInValue(procIndex1, "catCode",vo.getStrPatientCatCode(),9);
			

			dao.setProcInValue(procIndex1, "moduleId",vo.getStrBillService(),14);
			
            dao.setProcInValue(procIndex1, "ipAddress",vo.getStrIpAddr(),15);
            
            dao.setProcInValue(procIndex1, "accNo",vo.getStrPatActNo(),10);
			
			dao.setProcInValue(procIndex1, "admNo",vo.getStrAdmnNo(),11);
				
			dao.setProcInValue(procIndex1, "deptCode",vo.getStrDeptCode(),12);
					
			dao.setProcInValue(procIndex1, "wardId",vo.getStrWardCode(),13);
					            
			dao.setProcInValue(procIndex1, "seatId",vo.getStrSeatId(),16);
			
			dao.setProcInValue(procIndex1, "prepuk",vo.getStrPreviousPukNo(),4);
			
			 		
			dao.setProcOutValue(procIndex1, "ERR",1,18); // 1 for string return
														// value
			dao.setProcOutValue(procIndex1, "recNo",1,17);
			// execute procedure

		 	 dao.executeProcedureByPosition(procIndex1);

			// get value

			strerr = dao.getString(procIndex1, "ERR");
			
      			
			if (strerr == null)
				strerr = "";

			if (strerr.equals(""))
			{
				retVal = true;
				vo.setStrMsgType("0");
				strRecNo = dao.getString(procIndex1, "recNo");
				
				
				vo.setStrRcptNo(strRecNo);
			}
			else
			{
				throw new Exception(strerr);
			}

		} 
		catch (Exception e) 
		  {
			e.printStackTrace();
    		vo.setStrMsgString("BillDupPrintTransDAO.insertBillDtls() --> "+ e.getMessage());
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
	
	
	
	public static void getBillListingDtl(BillDupPrintTransVO voObj) {
	
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
		
		// With CR. No. 
		if(strCase.equals("1"))
		{			
			if(voObj.getStrDuplicateMode().trim().equals("1"))//Consolidated
			{				
				 strModeType = "1";				
			}
			else//Replica of Original
			{ 
				 strModeType = "6";
			}			
			
		}
		else// Without CR No.				
		{
			if(voObj.getStrDuplicateMode().trim().equals("1"))//Consolidated
			{				
				 strModeType = "2";
			}
			else//Replica of Original
			{				
				 strModeType = "7";
			}
		}
		
		if(strSearchString == null || strSearchString.equals("")) strSearchString = "";
		
		String strProcName = "{call pkg_bill_view.Proc_Hblt_Bill_Reciept_Dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		
		try 
		{
			daoObj = new HisDAO("Billing","BillDupPrintTransDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
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
			daoObj.setProcInValue(nProcIndex, "modeval", strModeType,8);				
			daoObj.setProcInValue(nProcIndex, "billNo", "",9);
			daoObj.setProcInValue(nProcIndex, "recNo", "",10);
			daoObj.setProcOutValue(nProcIndex, "err", 1,11);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,12);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) 
			{					
				voObj.setBillSearchList(ws);
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("BillDupPrintTransDAO.setPatientListingDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
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

	
	public static void getBillDtl(BillDupPrintTransVO voObj) 
	{
	    HisDAO daoObj = null;
		WebRowSet ws = null;
		String strErr = "";
		String strHosCode = voObj.getStrHosCode();
		String strModeType = "1";
		 String strProcName;
		int nProcIndex = 0;
		daoObj = new HisDAO("Billing","BillDupPrintTransDAO");
		System.out.println("voObj.getStrDuplicateMode()"+voObj.getStrDuplicateMode());
		try 
		{
			if(voObj.getStrDuplicateMode().equals("2"))
			{
				strProcName = "{call pkg_bill_view.Proc_Hblt_Bill_Reciept_Dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";			
				strModeType = "8";
				
				nProcIndex = daoObj.setProcedure(strProcName);
				try 
				{
					daoObj.setProcInValue(nProcIndex, "hosp_code",strHosCode,1);
					daoObj.setProcInValue(nProcIndex, "pukNo", "",2);
					daoObj.setProcInValue(nProcIndex, "patName", "",3);
					daoObj.setProcInValue(nProcIndex, "frmDate", "",4);
					daoObj.setProcInValue(nProcIndex, "toDate", "",5);
					daoObj.setProcInValue(nProcIndex, "frmRows", "",6);
					daoObj.setProcInValue(nProcIndex, "toRows", "",7);		
					daoObj.setProcInValue(nProcIndex, "modeval", strModeType,8);
					daoObj.setProcInValue(nProcIndex, "billNo", voObj.getStrBillNo(),9);
					daoObj.setProcInValue(nProcIndex, "recNo", voObj.getStrRcptNo(),10);
					daoObj.setProcOutValue(nProcIndex, "err", 1,11);
					daoObj.setProcOutValue(nProcIndex, "resultset", 2,12);
					daoObj.executeProcedureByPosition(nProcIndex);
					
					strErr = daoObj.getString(nProcIndex, "err");
					if (strErr == null)
						strErr = "";
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
					if (strErr.equals("")) 
					{						
						voObj.setBillDetails(ws);
					} 
					else 
					{
						try 
						{
							throw new Exception(strErr);
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}				
			}
			else
			{
				strProcName = "{call pkg_bill_view.Proc_Hblt_Bill_Detail(?,?,?,?,?)}";
				nProcIndex = daoObj.setProcedure(strProcName);
	
				try 
				{
					daoObj.setProcInValue(nProcIndex, "modeval", strModeType,1);
					daoObj.setProcInValue(nProcIndex, "billNo", voObj.getStrBillNo(),2);
					daoObj.setProcInValue(nProcIndex, "hosp_code",strHosCode,3);				
					daoObj.setProcOutValue(nProcIndex, "err", 1,4);
					daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);
					daoObj.executeProcedureByPosition(nProcIndex);
					strErr = daoObj.getString(nProcIndex, "err");
					if (strErr == null)
						strErr = "";
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
	
					if (strErr.equals("")) 
					{					
						voObj.setBillDetails(ws);
					} 
					else 
					{
						throw new Exception(strErr);
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}			
			}
		}
		catch (Exception e)
		{
		   voObj.setStrMsgString("BillDupPrintTransDAO.getBillDtl() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
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
	 * retrieves Tariff Details based on Bill Number.
	 * 
	 * @param voObj -
	 *            Value Object.
	 */
	public static void getTariffDetails(BillDupPrintTransVO voObj) 
	{
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_BILL_SERVICE_DTL(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		
		try 
		{
			dao = new HisDAO("billing","transactions.CashCollectionTransDAO.getTariffDetails_NoAcc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", voObj.getStrBillNo(),1);
		 	dao.setProcInValue(nprocIndex, "modeval", "5",3);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHosCode(),4);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1",2);
			dao.setProcInValue(nprocIndex, "recNo", voObj.getStrRcptNo(),5);
			dao.setProcInValue(nprocIndex, "recType", "1",6);
			dao.setProcOutValue(nprocIndex, "ERR", 1,7); // 1 for string return
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,8); // 2 for object
		 	dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
				voObj.setTariffDetails(ws);
			} 
			else 
			{
				voObj.setTariffDetails(null);
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("BillDupPrintTransDAO.getTariffDetails() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
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
	
	public static void getReceiptType(BillDupPrintTransVO voObj) {
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_GET_RECEIPT_TYPE(?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try {
			dao = new HisDAO("billing",
					"transactions.CashCollectionTransDAO.getReceiptType()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "modeval","1",1);
		 	dao.setProcInValue(nprocIndex, "isValid", "1",2);
			dao.setProcOutValue(nprocIndex, "err", 1,3); // 1 for string return
			dao.setProcOutValue(nprocIndex, "resultset", 2,4); // 2 for object
		 	
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{

				ws = dao.getWebRowSet(nprocIndex, "resultset");

				voObj.setReceiptTypeWb(ws);
				
			} 
			else 
			{
				voObj.setReceiptTypeWb(null);
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("BillDupPrintTransDAO.getReceiptType() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}
	
	public static void getBillDtlReg(BillDupPrintTransVO voObj) 
	{
	    HisDAO daoObj = null;
		WebRowSet ws = null;
		String strErr = "";
		String strHosCode = voObj.getStrHosCode();
		 String strProcName;
		int nProcIndex = 0;
		daoObj = new HisDAO("Billing","BillDupPrintTransDAO");
		System.out.println("voObj.getStrDuplicateMode()"+voObj.getStrDuplicateMode());
		try 
		{
			if(voObj.getStrDuplicateMode().equals("2"))
			{
				strProcName = "{call pkg_bill_view.Proc_Hblt_Bill_Reciept_Dtl(?,?,?,?,?,?,?,?,?,?,?,?)}";			
				
				
				nProcIndex = daoObj.setProcedure(strProcName);
				try 
				{
					daoObj.setProcInValue(nProcIndex, "hosp_code",strHosCode,1);
					daoObj.setProcInValue(nProcIndex, "pukNo", "",2);
					daoObj.setProcInValue(nProcIndex, "patName", "",3);
					daoObj.setProcInValue(nProcIndex, "frmDate", "",4);
					daoObj.setProcInValue(nProcIndex, "toDate", "",5);
					daoObj.setProcInValue(nProcIndex, "frmRows", "",6);
					daoObj.setProcInValue(nProcIndex, "toRows", "",7);		
					daoObj.setProcInValue(nProcIndex, "modeval", "9",8);
					daoObj.setProcInValue(nProcIndex, "billNo", voObj.getStrBillNo(),9);
					daoObj.setProcInValue(nProcIndex, "recNo", voObj.getStrRcptNo(),10);
					daoObj.setProcOutValue(nProcIndex, "err", 1,11);
					daoObj.setProcOutValue(nProcIndex, "resultset", 2,12);
					daoObj.executeProcedureByPosition(nProcIndex);
					
					strErr = daoObj.getString(nProcIndex, "err");
					if (strErr == null)
						strErr = "";
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
					if (strErr.equals("")) 
					{						
						voObj.setBillDetails(ws);
					} 
					else 
					{
						try 
						{
							throw new Exception(strErr);
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}				
			}
			
		}
		catch (Exception e)
		{
		   voObj.setStrMsgString("BillDupPrintTransDAO.getBillDtlReg() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
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
	
	public static void getTariffDetailsReg(BillDupPrintTransVO voObj) 
	{
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_BILL_SERVICE_DTL(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		
		try 
		{
			dao = new HisDAO("billing","transactions.CashCollectionTransDAO.getTariffDetails_NoAcc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", voObj.getStrBillNo(),1);
		 	dao.setProcInValue(nprocIndex, "modeval", "9",3);
			dao.setProcInValue(nprocIndex, "hosp_code", voObj.getStrHosCode(),4);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1",2);
			dao.setProcInValue(nprocIndex, "recNo", voObj.getStrRcptNo(),5);
			dao.setProcInValue(nprocIndex, "recType", "1",6);
			dao.setProcOutValue(nprocIndex, "ERR", 1,7); // 1 for string return
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,8); // 2 for object
		 	dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
				voObj.setTariffDetails(ws);
			} 
			else 
			{
				voObj.setTariffDetails(null);
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("BillDupPrintTransDAO.getTariffDetailsReg() --> "+ e.getMessage());
			voObj.setStrMsgType("1");
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

}
