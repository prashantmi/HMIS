package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;

public class DayEndTransDAO {

	public static void callingDayEndProcess(DayEndTransVO vo) 
	{
		HisDAO dao = null;
		String retVal = null;
		int funcIndex = 0;
		String temp[] = new String[2];
		String moduleId = "";

		try 
		{
				moduleId = BillConfigUtil.BILL_MODULE_ID;
				dao = new HisDAO("BillingModule", "DayEndTrans");
			    funcIndex = dao.setFunction("{? = call BILL_MST.getCounterDtl(?,?,?,?,?)}");
				dao.setFuncInValue(funcIndex, 2, moduleId);
				dao.setFuncInValue(funcIndex, 3, vo.getStrIpAddress());
				dao.setFuncInValue(funcIndex, 4, vo.getStrHospitalCode());
				dao.setFuncInValue(funcIndex, 5, "1");
				dao.setFuncInValue(funcIndex, 6, "0");
				
				dao.setFuncOutValue(funcIndex, 1);

				dao.executeFunction(funcIndex);
				retVal = dao.getFuncString(funcIndex);
				vo.setStrRetValue(retVal);

				temp = retVal.replace('^', '#').split("#");
				vo.setStrCounterName(temp[1]);
				vo.setStrCounterId(temp[0]);
		}
		catch (Exception e) 
		{
			vo.setStrMsgString("DayEndTransDAO.callingDayEndProcess() --> "+ e.getMessage());
			vo.setStrMsgType("1");
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

	

	public synchronized static boolean insertAddDataProc(DayEndTransVO vo) 
	{
		boolean retVal = false;
		String proc_name = "";
		HisDAO dao = null;
		int procIndex = 0;
		String strErr = "";
		String strSummNo = "";
		PrimaryKeyDAO keyDAO = null;
		PrimaryKeyLogDAO keyLogDAO = null;

		try 
		{
			dao = new HisDAO("billing", "DayEndTransDAO.insertAddDataProc");
			keyDAO = new PrimaryKeyDAO();
			keyLogDAO = new PrimaryKeyLogDAO();

			proc_name = "{call Pkg_Bill_Dml.DML_DAYEND_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			procIndex = dao.setProcedure(proc_name);

			keyDAO.setStrKeyname("SUMMNO");
			keyDAO.setStrBlockkey("1");
			keyDAO.setStrHospCode(vo.getStrHospitalCode());
			keyDAO.select(dao);
			System.out.println("vo.getStrBillModuleId()"+vo.getStrBillModuleId());
			strSummNo = keyDAO.getStrPrimrayKeyValue();
			vo.setStrSummNo(strSummNo);
            if(vo.getStrBillModuleId().equals("3"))//Module ID -3 Means Combined Day End of Billing and Registration Module
            	dao.setProcInValue(procIndex, "modeval", "3",1);
            else
            	dao.setProcInValue(procIndex, "modeval", "1",1);
			dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(procIndex, "summNo", strSummNo,3);
			dao.setProcInValue(procIndex, "sessValue", vo.getStrSessionId().replace("^", "#").split("#")[0],5);
			dao.setProcInValue(procIndex, "sessFrm", vo.getStrSessionFromTime(),6);
			dao.setProcInValue(procIndex, "sessTo", vo.getStrSessionToTime(),7);
			dao.setProcInValue(procIndex, "remarks", vo.getStrRemarks(),8);
			dao.setProcInValue(procIndex, "seatId", vo.getStrSeatId(),9);
			dao.setProcInValue(procIndex, "moduleId", vo.getStrBillModuleId(),10);
			dao.setProcInValue(procIndex, "userMode", vo.getStrUserMode(),11);
			dao.setProcInValue(procIndex, "dayEndDate", vo.getStrDayEndDate(),13);
			//user mode = 1 : Clerk and 2 : Supervisor
			if(!vo.getStrUserMode().equals("1"))
			{
				dao.setProcInValue(procIndex, "dayEndSeatId", vo.getStrUsrId(),12);
				dao.setProcInValue(procIndex, "counterId", vo.getStrCountId(),4);
			}
			else
			{
				dao.setProcInValue(procIndex, "dayEndSeatId", vo.getStrSeatId(),12);
				dao.setProcInValue(procIndex, "counterId", vo.getStrCounterId(),4);
			}
			
			dao.setProcOutValue(procIndex, "err", 1,14); 
			dao.setProcInValue(procIndex, "dayEndTotalCash", vo.getStrDayEndAmount(),15);
			dao.setProcInValue(procIndex, "dayEndMode", vo.getStrDayEndMode(),16);
			dao.setProcInValue(procIndex, "dayEndCounterName", vo.getStrDayEndCounterName(),17);
			dao.setProcInValue(procIndex, "dayEndUserName", vo.getStrDayEndUserName(),18);
			dao.setProcInValue(procIndex, "dayEndIpAdd", vo.getStrIpAddress(),19);	
			dao.setProcInValue(procIndex, "twothousandNote", vo.getStrTwoThousandNotes(),20);
			dao.setProcInValue(procIndex, "thousandNote", vo.getStrThousandNotes(),21);
			dao.setProcInValue(procIndex, "FiveHundNote", vo.getStrFiveHundNotes(),22);
			dao.setProcInValue(procIndex, "TwoHundNote", vo.getStrTwoHundNotes(),23);
			dao.setProcInValue(procIndex, "hundNote", vo.getStrHundNotes(),24);
			dao.setProcInValue(procIndex, "fiftyNote", vo.getStrFiftyNotes(),25);
			dao.setProcInValue(procIndex, "twentyNote", vo.getStrTwentyNotes(),26);
			dao.setProcInValue(procIndex, "tenNote", vo.getStrTenNotes(),27);
			dao.setProcInValue(procIndex, "fiveNote", vo.getStrFiveNotes(),28);
			dao.setProcInValue(procIndex, "twoNote", vo.getStrTwoNotes(),29);
			dao.setProcInValue(procIndex, "oneNote", vo.getStrOneNotes(),30);
			dao.setProcInValue(procIndex, "tenCoin", vo.getStrtenCoins(),31);
			dao.setProcInValue(procIndex, "fiveCoin", vo.getStrFiveCoins(),32);
			dao.setProcInValue(procIndex, "twoCoin", vo.getStrTwoCoins(),33);
			dao.setProcInValue(procIndex, "oneCoin", vo.getStrOneCoins(),34);
			dao.setProcInValue(procIndex, "creditAmt", vo.getStrDayEndCreditCol(),35);

					
			dao.executeProcedureByPosition(procIndex);
			strErr = dao.getString(procIndex, "err");
			if (strErr == null) 
			{
				strErr = "";
			}
			if (strErr.equals("")) 
			{
				retVal = true;
				vo.setStrMsgString("Data Inserted Successfully");
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("DayEndTransDAO.insertAddDataProc() --> "+ e.getMessage());
			vo.setStrMsgType("1");
			
			vo.setStrPrimaryKeyMsg(e.getMessage());
			try 
			{
				keyLogDAO.setStrKeyname("SUMMNO");
				keyLogDAO.setStrStartkey(strSummNo);
				keyLogDAO.setStrBlockkey("1");
				keyLogDAO.setStrHospCode(vo.getStrHospitalCode());
				keyLogDAO.setStrError(vo.getStrPrimaryKeyMsg());
				keyLogDAO.setStrSeatid(vo.getStrSeatId());
				keyLogDAO.insert(dao);
			} 
			catch (Exception e1) 
			{
				vo.setStrMsgString("DayEndTransDAO.insertAddDataProc() --> "+ e1.getMessage());
				vo.setStrMsgType("1");
			}
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		} return retVal;
	}
	
	
	public static void getCounterAndUserName(DayEndTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.PROC_GET_COUNTER_USER(?,?,?,?,?,?)}";
		try 
		{
			dao = new HisDAO("billing","billing.DayEndTransDAO.getCounterAndUserName(PrintVO voHdrObj)");

			procIndex1 = dao.setProcedure(proc_name1);
 
			dao.setProcInValue(procIndex1, "modVal", "2",1);
			dao.setProcInValue(procIndex1, "hosp_code", voObj.getStrHospitalCode(),2);
			dao.setProcInValue(procIndex1, "seatid", voObj.getStrSeatId(),4);
			dao.setProcInValue(procIndex1, "ipAddress", "",3);
			dao.setProcOutValue(procIndex1, "err", 1,5);
			dao.setProcOutValue(procIndex1, "resultset", 2,6);
			dao.executeProcedureByPosition(procIndex1);

			err = dao.getString(procIndex1, "err");
			if (err == null)
				err = "";

			if (err.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				
				if(ws.next())
				{
					voObj.setStrUserName(ws.getString(1));
				}				

			} 
			else 
			{
				throw new Exception(err);
			}

		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DayEndTransDAO.getCounterAndUserName() --> "+ e.getMessage());
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


	/**
	 * 
	 * @param voObj	the DayEndTransVO
	 */
	public static void PendingDayEndProcess(DayEndTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.proc_hblt_payment_detail(?,?,?,?,?,?,?)}";
		try {

			dao = new HisDAO("billing",	"billing.DayEndTransDAO.getCounterAndUserName()");

			procIndex1 = dao.setProcedure(proc_name1);
		       
			dao.setProcInValue(procIndex1, "modeval", voObj.getStrMode(),1);
			dao.setProcInValue(procIndex1, "hosp_code", voObj.getStrHospitalCode(),2);						
			dao.setProcInValue(procIndex1, "p_dayEndSeatId", voObj.getStrSeatId(),3);			
			dao.setProcInValue(procIndex1, "p_counterId", voObj.getStrCounterId(),4);			
			dao.setProcInValue(procIndex1, "p_dayEndProcess", voObj.getStrDayEndProcess(),5);			
			dao.setProcOutValue(procIndex1, "err", 1,6);
			dao.setProcOutValue(procIndex1, "resultset", 2,7);			
			dao.executeProcedureByPosition(procIndex1);
						err = dao.getString(procIndex1, "err");
			if (err == null)
				err = "";

			if (err.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");					
				voObj.setWrsData(ws);
			} 
			else 
			{
				throw new Exception(err);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DayEndTransDAO.getCounterAndUserName() --> "+ e.getMessage());
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
	
	
	//get day end cash amount (excludes credit collection)
	public static void getDayEndAmt(DayEndTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.PROC_DAYENED_CASH_AMOUNT_DTL(?,?,?,?,?,?,?,?,?)}";
		
		try 
		{
			dao = new HisDAO("billing","billing.DayEndTransDAO.getDayEndAmt(PrintVO voHdrObj)");

			procIndex1 = dao.setProcedure(proc_name1);
			
			dao.setProcInValue(procIndex1, "modeval", "1",1);
			dao.setProcInValue(procIndex1, "seatid", voObj.getStrSeatId(),2);
			dao.setProcInValue(procIndex1, "counterId", voObj.getStrCounterId(),3);
			dao.setProcInValue(procIndex1, "hospitalcode",voObj.getStrHospitalCode(),4);
			dao.setProcInValue(procIndex1, "dayEndProcessType",voObj.getStrDayEndMode(),5);
			
			if(voObj.getStrDayEndDate().equals(""))
			{
				voObj.setStrDayEndDate("0");
			}
			dao.setProcInValue(procIndex1, "dayEndDate",voObj.getStrDayEndDate(),6);
			dao.setProcInValue(procIndex1, "billModuleId",voObj.getStrBillModuleId(),7);
			dao.setProcOutValue(procIndex1, "err", 1,8);
			dao.setProcOutValue(procIndex1, "resultset", 2,9);
			dao.executeProcedureByPosition(procIndex1);

			err = dao.getString(procIndex1, "err");
			if (err == null)
				err = "";

			if (err.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");				
				if(ws.next())
				{					
					voObj.setStrDayEndAmount(ws.getString(1));					
				}
			} 
			else 
			{
				throw new Exception(err);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DayEndTransDAO.getDayEndAmt() --> "	+ e.getMessage());
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
	
	//get day end credit collection
	public static void getDayEndCreditAmtCollection(DayEndTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.PROC_DAYENED_CASH_AMOUNT_DTL(?,?,?,?,?,?,?,?,?)}";
		
		try 
		{
			dao = new HisDAO("billing","billing.DayEndTransDAO.getDayEndCreditAmtCollection(DayEndTransVO voHdrObj)");

			procIndex1 = dao.setProcedure(proc_name1);
			
			dao.setProcInValue(procIndex1, "modeval", "2",1);
			dao.setProcInValue(procIndex1, "seatid", voObj.getStrSeatId(),2);
			dao.setProcInValue(procIndex1, "counterId", voObj.getStrCounterId(),3);
			dao.setProcInValue(procIndex1, "hospitalcode",voObj.getStrHospitalCode(),4);
			dao.setProcInValue(procIndex1, "dayEndProcessType",voObj.getStrDayEndMode(),5);
			
			if(voObj.getStrDayEndDate().equals(""))
			{
				voObj.setStrDayEndDate("0");
			}
			dao.setProcInValue(procIndex1, "dayEndDate",voObj.getStrDayEndDate(),6);
			dao.setProcInValue(procIndex1, "billModuleId",voObj.getStrBillModuleId(),7);
			dao.setProcOutValue(procIndex1, "err", 1,8);
			dao.setProcOutValue(procIndex1, "resultset", 2,9);
			dao.executeProcedureByPosition(procIndex1);

			err = dao.getString(procIndex1, "err");
			if (err == null)
				err = "";

			if (err.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");				
				if(ws.next())
				{
					voObj.setStrDayEndCreditCol(ws.getString(1));
				}
			} 
			else 
			{
				throw new Exception(err);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			voObj.setStrMsgString("DayEndTransDAO.getDayEndCreditAmtCollection() --> "	+ e.getMessage());
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
	//get day end instrument amount (excludes credit and cash collection)
		public static void getDayEndInstAmt(DayEndTransVO voObj) 
		{
			HisDAO dao = null;
			int procIndex1 = 0;
			String err = "";
			WebRowSet ws = null;
			String proc_name1 = "";
			proc_name1 = "{call PKG_BILL_VIEW.PROC_DAYENED_CASH_AMOUNT_DTL(?,?,?,?,?,?,?,?,?)}";
			
			try 
			{
				dao = new HisDAO("billing","billing.DayEndTransDAO.getDayEndInstAmt(PrintVO voHdrObj)");

				procIndex1 = dao.setProcedure(proc_name1);
				
				dao.setProcInValue(procIndex1, "modeval", "3",1);
				dao.setProcInValue(procIndex1, "seatid", voObj.getStrSeatId(),2);
				dao.setProcInValue(procIndex1, "counterId", voObj.getStrCounterId(),3);
				dao.setProcInValue(procIndex1, "hospitalcode",voObj.getStrHospitalCode(),4);
				dao.setProcInValue(procIndex1, "dayEndProcessType",voObj.getStrDayEndMode(),5);
				
				if(voObj.getStrDayEndDate().equals(""))
				{
					voObj.setStrDayEndDate("0");
				}
				dao.setProcInValue(procIndex1, "dayEndDate",voObj.getStrDayEndDate(),6);
				dao.setProcInValue(procIndex1, "billModuleId",voObj.getStrBillModuleId(),7);
				dao.setProcOutValue(procIndex1, "err", 1,8);
				dao.setProcOutValue(procIndex1, "resultset", 2,9);
				dao.executeProcedureByPosition(procIndex1);

				err = dao.getString(procIndex1, "err");
				if (err == null)
					err = "";

				if (err.equals("")) 
				{
					ws = dao.getWebRowSet(procIndex1, "resultset");				
					if(ws.next())
					{					
						voObj.setStrDayEndInstAmt(ws.getString(1));					
					}
				} 
				else 
				{
					throw new Exception(err);
				}
			} 
			catch (Exception e) 
			{
				voObj.setStrMsgString("DayEndTransDAO.getDayEndInstAmt() --> "	+ e.getMessage());
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
	public static void checkDayEndAllowed(DayEndTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.PROC_CHECK_DAYEND_ALLOWED(?,?,?,?,?,?,?,?,?)}";
		
		try 
		{
			dao = new HisDAO("Billing","billing.DayEndTransDAO.checkDayEndAllowed(DayEndTransVO voObj)");

			procIndex1 = dao.setProcedure(proc_name1);
			
			dao.setProcInValue(procIndex1, "modeval", "1",1);
			dao.setProcInValue(procIndex1, "seatid", voObj.getStrSeatId(),2);
			dao.setProcInValue(procIndex1, "counterId", voObj.getStrCounterId(),3);
			dao.setProcInValue(procIndex1, "hospitalcode",voObj.getStrHospitalCode(),4);
			dao.setProcInValue(procIndex1, "dayEndProcessType",voObj.getStrDayEndMode(),5);//User Wise Or Supervisor Wise
			
			if(voObj.getStrDayEndDate().equals(""))
			{
				voObj.setStrDayEndDate("0");
			}
			dao.setProcInValue(procIndex1, "dayEndDate",voObj.getStrDayEndDate(),6);
			dao.setProcInValue(procIndex1, "billModuleId",voObj.getStrBillModuleId(),7);
			dao.setProcOutValue(procIndex1, "err", 1,8);
			dao.setProcOutValue(procIndex1, "resultset", 2,9);
			dao.executeProcedureByPosition(procIndex1);

			err = dao.getString(procIndex1, "err");
			if (err == null)
				err = "";

			if (err.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");				
				if(ws.next())
				{
					voObj.setStrDayEndTimeBoundFlag(ws.getString(1));
					voObj.setStrDayEndAllowedFlag(ws.getString(2));
					voObj.setStrDayEndAllowedTime(ws.getString(3));
					voObj.setStrCurrentTime(ws.getString(4));
				}
			} 
			else 
			{
				throw new Exception(err);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DayEndTransDAO.checkDayEndAllowed() --> "	+ e.getMessage());
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
	
	public static void ScrollDetails(DayEndTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.proc_scroll_detail(?,?,?,?,?,?,?)}";
		try {

			dao = new HisDAO("billing",	"billing.DayEndTransDAO.ScrollDetails()");

			procIndex1 = dao.setProcedure(proc_name1);
		       
			dao.setProcInValue(procIndex1, "modeval", "1",1);
			dao.setProcInValue(procIndex1, "hosp_code", voObj.getStrHospitalCode(),2);						
			dao.setProcInValue(procIndex1, "summNo", voObj.getStrSummNo(),3);	
			dao.setProcInValue(procIndex1, "dayEndDate",voObj.getStrDayEndDate(),4);
			dao.setProcInValue(procIndex1, "seatId", voObj.getStrSeatId(),5);
			dao.setProcOutValue(procIndex1, "err", 1,6);
			dao.setProcOutValue(procIndex1, "resultset", 2,7);			
			dao.executeProcedureByPosition(procIndex1);
			err = dao.getString(procIndex1, "err");
			if (err == null)
				err = "";

			if (err.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");					
				voObj.setWrsData1(ws);
			} 
			else 
			{
				throw new Exception(err);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DayEndTransDAO.ScrollDetails() --> "+ e.getMessage());
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
	public static void ScrollDetails_bill(DayEndTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.proc_scroll_detail(?,?,?,?,?,?,?)}";
		try {

			dao = new HisDAO("billing",	"billing.DayEndTransDAO.ScrollDetails()");

			procIndex1 = dao.setProcedure(proc_name1);
		       
			dao.setProcInValue(procIndex1, "modeval", "2",1);
			dao.setProcInValue(procIndex1, "hosp_code", voObj.getStrHospitalCode(),2);						
			dao.setProcInValue(procIndex1, "summNo", voObj.getStrSummNo(),3);	
			dao.setProcInValue(procIndex1, "dayEndDate",voObj.getStrDayEndDate(),4);
			dao.setProcInValue(procIndex1, "seatId", voObj.getStrSeatId(),5);
			dao.setProcOutValue(procIndex1, "err", 1,6);
			dao.setProcOutValue(procIndex1, "resultset", 2,7);			
			dao.executeProcedureByPosition(procIndex1);
			err = dao.getString(procIndex1, "err");
			if (err == null)
				err = "";

			if (err.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");					
				voObj.setWrsData2(ws);
			} 
			else 
			{
				throw new Exception(err);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DayEndTransDAO.ScrollDetails_bill() --> "+ e.getMessage());
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
	public static void ScrollDetails_credit(DayEndTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.proc_scroll_detail(?,?,?,?,?,?,?)}";
		try {

			dao = new HisDAO("billing",	"billing.DayEndTransDAO.ScrollDetails()");

			procIndex1 = dao.setProcedure(proc_name1);
		       
			dao.setProcInValue(procIndex1, "modeval", "3",1);
			dao.setProcInValue(procIndex1, "hosp_code", voObj.getStrHospitalCode(),2);						
			dao.setProcInValue(procIndex1, "summNo", voObj.getStrSummNo(),3);	
			dao.setProcInValue(procIndex1, "dayEndDate",voObj.getStrDayEndDate(),4);
			dao.setProcInValue(procIndex1, "seatId", voObj.getStrSeatId(),5);
			dao.setProcOutValue(procIndex1, "err", 1,6);
			dao.setProcOutValue(procIndex1, "resultset", 2,7);			
			dao.executeProcedureByPosition(procIndex1);
			err = dao.getString(procIndex1, "err");
			if (err == null)
				err = "";

			if (err.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");					
				voObj.setWrsData3(ws);
			} 
			else 
			{
				throw new Exception(err);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DayEndTransDAO.ScrollDetails_credit() --> "+ e.getMessage());
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

