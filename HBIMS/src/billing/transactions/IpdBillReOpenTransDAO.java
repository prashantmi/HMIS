package billing.transactions;
/*
 * Ipd Bill Re-Open Transaction DAO
 * 
 * author : Debashis Sardar
 * 
 * date: 10-Dec-2011
 * 
 */
import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;
import billing.BillConfigUtil;

public class IpdBillReOpenTransDAO {

	public static void getCrNo(IpdBillReOpenTransVO vo) {
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.proc_get_pat_crno(?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try {

			dao = new HisDAO("billing",
					"transactions.IpdBillReOpenTransDAO .getCrNo()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "modeval", "1",1);
			dao.setProcInValue(nprocIndex, "billNo", vo.getStrRcptNo(),2 );
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),3);
			dao.setProcInValue(nprocIndex, "reopenvalidity", BillConfigUtil.IPD_BILL_REOPEN_VALIDITY,4);
			dao.setProcOutValue(nprocIndex, "ERR", 1,5); // 1 for string return
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2,6); // 2 for object
			
			dao.executeProcedureByPosition(nprocIndex);
			
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
            
			if (ws != null) {
				while(ws.next())
				{
				vo.setStrCrNo(ws.getString(1));
				vo.setStrPatAccNo(ws.getString(2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("IpdBillReOpenTransDAO.getCrNo() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}
	
	public static void updateForPoorFreePatCategory(IpdBillReOpenTransVO vo) {
		
		String proc_name = "";
		proc_name = "{call PKG_BILL_DML.proc_billReopen_poorFreeCat(?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		BillConfigUtil bcu = null;
		bcu = new BillConfigUtil(vo.getStrHospitalCode());
        String strFreeCat=bcu.getIpdFreeCategory();
		try {

			dao = new HisDAO("billing",
					"transactions.IpdBillReOpenTransDAO .updateForPoorFreePatCategory()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "modeval", "1");
			dao.setProcInValue(nprocIndex, "billNo", vo.getStrRcptNo() );
			dao.setProcInValue(nprocIndex, "patCatCode", strFreeCat );
			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAccNo() );
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode()); // New Value
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			
			dao.executeProcedure(nprocIndex);
		
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			
		}
		catch (Exception e) {
			vo
					.setStrMsgString("IpdBillReOpenTransDAO.updateForPoorFreePatCategory() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		}
		
	
		finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}
	public static void updateForCreditCategory(IpdBillReOpenTransVO vo) 
	{		
		String proc_name = "";
		proc_name = "{call PKG_BILL_DML.proc_billreopen_creditcat(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		BillConfigUtil bcu = null;
		bcu = new BillConfigUtil(vo.getStrHospitalCode());
        String strFreeCat=bcu.getIpdFreeCategory();
		try 
		{
			dao = new HisDAO("billing","transactions.IpdBillReOpenTransDAO .updateForCreditCategory()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "modeval", "1");
			dao.setProcInValue(nprocIndex, "puk", vo.getStrCrNo());
			dao.setProcInValue(nprocIndex, "billNo", vo.getStrRcptNo() );			
			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAccNo() );
			dao.setProcInValue(nprocIndex, "patCatCode", vo.getStrBillCatCode() );
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode()); // New Value
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			
			dao.executeProcedure(nprocIndex);
		
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			
		}
		catch (Exception e) 
		{
			vo.setStrMsgString("IpdBillReOpenTransDAO.updateForPoorFreePatCategory() --> "+ e.getMessage());
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
	public static void saveData(IpdBillReOpenTransVO vo) 
	{		
		String proc_name = "";
		proc_name = "{call PKG_BILL_DML.proc_save_bill_reopen(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try 
		{
			dao = new HisDAO("billing","transactions.IpdBillReOpenTransDAO .saveData()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "modeval", "1");
			dao.setProcInValue(nprocIndex, "billNo", vo.getStrRcptNo() );
			dao.setProcInValue(nprocIndex, "crno", vo.getStrCrNo() );
			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId() );
			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAccNo() );
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode()); // New Value
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			
			dao.executeProcedure(nprocIndex);
			
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			
			if(vo.getStrBillCatGrp().equals("3") || vo.getStrBillCatGrp().equals("4"))
				updateForCreditCategory(vo) ;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			vo.setStrMsgString("IpdBillReOpenTransDAO.saveData() --> "+ e.getMessage());
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
	public static void getBillDetails(IpdBillReOpenTransVO vo) {
		WebRowSet ws = null;
		
		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.proc_get_bill_details(?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try {

			dao = new HisDAO("billing",
					"transactions.IpdBillReOpenTransDAO .getBillDetails()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "modeval", "1");
			dao.setProcInValue(nprocIndex, "billNo", vo.getStrRcptNo() );
			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAccNo() );
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode()); // New Value
			dao.setProcOutValue(nprocIndex, "ERR", 1); // 1 for string return
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2); // 2 for object
			
			dao.executeProcedure(nprocIndex);
			
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
            
			if (ws != null) {
				while(ws.next())
				{
				vo.setStrBillDate(ws.getString(1));
				vo.setStrApprovedBy(ws.getString(2));
				vo.setStrExpenseAmt(ws.getString(3));
				vo.setStrReceiveAmt(ws.getString(4));
				vo.setStrBillCatCode(ws.getString(5));
				vo.setStrBillCatGrp(ws.getString(6));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setStrMsgString("IpdBillReOpenTransDAO.getBillDetails() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");
		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}

	}	
}
