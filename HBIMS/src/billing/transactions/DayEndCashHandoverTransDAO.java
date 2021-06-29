package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;

public class DayEndCashHandoverTransDAO {

	public static void callingDayEndProcess(DayEndCashHandoverTransVO vo) 
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
			vo.setStrMsgString("DayEndCashHandoverTransDAO.callingDayEndProcess() --> "+ e.getMessage());
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

	

	public synchronized static boolean insertAddDataProc(DayEndCashHandoverTransVO vo) 
	{
		boolean retVal = false;
		String proc_name="",proc_name2 = "";
		HisDAO dao = null;
		int procIndex=0,procIndex2 = 0;
		String strErr = "";
		String strSummNo = "";
		PrimaryKeyDAO keyDAO = null;
		PrimaryKeyLogDAO keyLogDAO = null;

		try 
		{
			dao = new HisDAO("billing", "DayEndCashHandoverTransDAO.insertAddDataProc");
			keyDAO = new PrimaryKeyDAO();
			keyLogDAO = new PrimaryKeyLogDAO();

			proc_name = "{call Pkg_Bill_Dml.DML_DAYEND_HANDOVER_DTL(?,?,?,?,?,?,?,?,?,?,?)}";
			proc_name2 = "{call Pkg_Bill_Dml.DML_DAYEND_HANDOVER_DTL(?,?,?,?,?,?,?,?,?,?,?)}";
			procIndex = dao.setProcedure(proc_name);
			

			keyDAO.setStrKeyname("SUMM_HANDOVER_NO");
			//keyDAO.setStrKeyname("SUMMNO");
			keyDAO.setStrBlockkey("1");
			keyDAO.setStrHospCode(vo.getStrHospitalCode());
			keyDAO.select(dao);
			
			
			

/*CREATE TABLE hblt_dayend_handover_dtl
(
  hblnum_summ_handover_no numeric(15,0) NOT NULL,
  sblnum_handover_type_id numeric(2,0) NOT NULL,
  hblnum_payment_amount numeric(9,2) NOT NULL DEFAULT 0,
  gnum_seatid numeric(5,0),
  gdt_entry_date timestamp without time zone DEFAULT sysdate,
  gnum_isvalid numeric(1,0) DEFAULT 1,
  gnum_hospital_code numeric(6,0) NOT NULL DEFAULT 0 , 
  CONSTRAINT pk_dayend_handover_dtl PRIMARY KEY (hblnum_summ_handover_no, sblnum_handover_type_id, gnum_hospital_code)
);
*/

			strSummNo = keyDAO.getStrPrimrayKeyValue();
			//vo.setStrSummNo(strSummNo);
			
 			for(int i=0;i<vo.getStrCashHandoverTo().length-1;i++)//TO ENter Data in Handover Table
			{
				dao.setProcInValue(procIndex, "modeval", "1",1);
				dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode(),2);
				dao.setProcInValue(procIndex, "summ_no", vo.getStrChk()[i].replace("^","#").split("#")[0],3);
				dao.setProcInValue(procIndex, "summ_handover_no", strSummNo,4);
				dao.setProcInValue(procIndex, "sblnum_handover_type_id",vo.getStrCashHandoverTo()[i] ,5);
				dao.setProcInValue(procIndex, "hblnum_payment_amount", vo.getStrCashHandOverAmount()[i],6);
				dao.setProcInValue(procIndex, "hblnum_reference_no", vo.getStrRefNo()[i],7);
				dao.setProcInValue(procIndex, "hblnum_handover_name", vo.getStrHandOverToName()[i],8);
				dao.setProcInValue(procIndex, "seatId", vo.getStrSeatId(),9);
				dao.setProcInValue(procIndex, "costtype","0",10);
				dao.setProcOutValue(procIndex, "err", 1,11);
				dao.execute(procIndex,1);
			}
			procIndex2 = dao.setProcedure(proc_name2);
			for(int i=0;i<=vo.getStrChk().length-1;i++)//To Update Staus in Day End Table
			{
				dao.setProcInValue(procIndex2, "modeval", "2",1);
				dao.setProcInValue(procIndex2, "hosp_code", vo.getStrHospitalCode(),2);
				dao.setProcInValue(procIndex2, "summ_no", vo.getStrChk()[i].replace("^","#").split("#")[0],3);
				dao.setProcInValue(procIndex2, "summ_handover_no", strSummNo,4);
				dao.setProcInValue(procIndex2, "sblnum_handover_type_id","0" ,5);
				dao.setProcInValue(procIndex2, "hblnum_payment_amount", "0",6);
				int j=Integer.parseInt(vo.getStrChk()[i].replace("^","#").split("#")[1]);
				dao.setProcInValue(procIndex2, "hblnum_reference_no", vo.getStrRefNo()[j-1],7);
			    dao.setProcInValue(procIndex2, "hblnum_handover_name",vo.getStrHandOverToName()[j-1],8);
				dao.setProcInValue(procIndex2, "seatId", vo.getStrSeatId(),9);
				dao.setProcInValue(procIndex2, "costtype",vo.getStrChk()[i].replace("^","#").split("#")[1],10);
				dao.setProcOutValue(procIndex2, "err", 1,11);	
				dao.execute(procIndex2,1);
			}
            
			synchronized (dao)
			{
				dao.fire();
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
			e.printStackTrace();
			vo.setStrMsgString("DayEndCashHandoverTransDAO.insertAddDataProc() --> "+ e.getMessage());
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
				e.printStackTrace();
				vo.setStrMsgString("DayEndCashHandoverTransDAO.insertAddDataProc() --> "+ e1.getMessage());
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
			keyDAO = null;
			keyLogDAO = null;
			
		} return retVal;
	}
	
	
	public static void getCounterAndUserName(DayEndCashHandoverTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.PROC_GET_COUNTER_USER(?,?,?,?,?,?)}";
		try 
		{
			dao = new HisDAO("billing","billing.DayEndCashHandoverTransDAO.getCounterAndUserName(PrintVO voHdrObj)");

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
			voObj.setStrMsgString("DayEndCashHandoverTransDAO.getCounterAndUserName() --> "+ e.getMessage());
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
	 * @param voObj	the DayEndCashHandoverTransVO
	 */
	public static void PendingDayEndProcess(DayEndCashHandoverTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.proc_hblt_payment_detail(?,?,?,?,?,?,?)}";
		try {

			dao = new HisDAO("billing",	"billing.DayEndCashHandoverTransDAO.getCounterAndUserName()");

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
			voObj.setStrMsgString("DayEndCashHandoverTransDAO.getCounterAndUserName() --> "+ e.getMessage());
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
	public static void getDayEndAmt(DayEndCashHandoverTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.PROC_DAYENED_CASH_AMOUNT_DTL(?,?,?,?,?,?,?,?,?)}";
		
		try 
		{
			dao = new HisDAO("billing","billing.DayEndCashHandoverTransDAO.getDayEndAmt(PrintVO voHdrObj)");

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
			voObj.setStrMsgString("DayEndCashHandoverTransDAO.getDayEndAmt() --> "	+ e.getMessage());
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
	public static void getDayEndCreditAmtCollection(DayEndCashHandoverTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.PROC_DAYENED_CASH_AMOUNT_DTL(?,?,?,?,?,?,?,?,?)}";
		
		try 
		{
			dao = new HisDAO("billing","billing.DayEndCashHandoverTransDAO.getDayEndCreditAmtCollection(DayEndCashHandoverTransVO voHdrObj)");

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
			voObj.setStrMsgString("DayEndCashHandoverTransDAO.getDayEndCreditAmtCollection() --> "	+ e.getMessage());
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
		public static void getDayEndInstAmt(DayEndCashHandoverTransVO voObj) 
		{
			HisDAO dao = null;
			int procIndex1 = 0;
			String err = "";
			WebRowSet ws = null;
			String proc_name1 = "";
			proc_name1 = "{call PKG_BILL_VIEW.PROC_DAYENED_CASH_AMOUNT_DTL(?,?,?,?,?,?,?,?,?)}";
			
			try 
			{
				dao = new HisDAO("billing","billing.DayEndCashHandoverTransDAO.getDayEndInstAmt(PrintVO voHdrObj)");

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
				voObj.setStrMsgString("DayEndCashHandoverTransDAO.getDayEndInstAmt() --> "	+ e.getMessage());
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
	public static void checkDayEndAllowed(DayEndCashHandoverTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.PROC_CHECK_DAYEND_ALLOWED(?,?,?,?,?,?,?,?,?)}";
		
		try 
		{
			dao = new HisDAO("Billing","billing.DayEndCashHandoverTransDAO.checkDayEndAllowed(DayEndCashHandoverTransVO voObj)");

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
			voObj.setStrMsgString("DayEndCashHandoverTransDAO.checkDayEndAllowed() --> "	+ e.getMessage());
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
	
	public static void ScrollDetails(DayEndCashHandoverTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.proc_scroll_detail(?,?,?,?,?,?,?)}";
		try {

			dao = new HisDAO("billing",	"billing.DayEndCashHandoverTransDAO.ScrollDetails()");

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
			voObj.setStrMsgString("DayEndCashHandoverTransDAO.ScrollDetails() --> "+ e.getMessage());
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
	public static void ScrollDetails_bill(DayEndCashHandoverTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.proc_scroll_detail(?,?,?,?,?,?,?)}";
		try {

			dao = new HisDAO("billing",	"billing.DayEndCashHandoverTransDAO.ScrollDetails()");

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
			voObj.setStrMsgString("DayEndCashHandoverTransDAO.ScrollDetails_bill() --> "+ e.getMessage());
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
	public static void ScrollDetails_credit(DayEndCashHandoverTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.proc_scroll_detail(?,?,?,?,?,?,?)}";
		try {

			dao = new HisDAO("billing",	"billing.DayEndCashHandoverTransDAO.ScrollDetails()");

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
			voObj.setStrMsgString("DayEndCashHandoverTransDAO.ScrollDetails_credit() --> "+ e.getMessage());
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
	
	public static void getHandoverTo(DayEndCashHandoverTransVO voObj) 
	{
		HisDAO dao = null;
		int procIndex1 = 0;
		String err = "";
		WebRowSet ws = null;
		String proc_name1 = "";
		proc_name1 = "{call PKG_BILL_VIEW.proc_sblt_costtype_mst(?,?,?,?)}";
		try 
		{
			dao = new HisDAO("billing","billing.DayEndCashHandoverTransDAO.getHandoverTo");

			procIndex1 = dao.setProcedure(proc_name1);
 
			dao.setProcInValue(procIndex1, "modval", "1",1);
			dao.setProcInValue(procIndex1, "hosp_code", "100",2);
			dao.setProcOutValue(procIndex1, "err", 1,3);
			dao.setProcOutValue(procIndex1, "resultset", 2,4);
			dao.executeProcedureByPosition(procIndex1);

			err = dao.getString(procIndex1, "err");
			if (err == null)
				err = "";

			if (err.equals("")) 
			{
				ws = dao.getWebRowSet(procIndex1, "resultset");
				
			
					voObj.setStrCashHandoverToWS(ws);

			} 
			else 
			{
				throw new Exception(err);
			}

		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("DayEndCashHandoverTransDAO.getHandoverTo() --> "+ e.getMessage());
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

