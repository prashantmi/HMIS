package mms.transactions.dao;
import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;
import mms.transactions.vo.BillApprovalTransVO; 

/**
 * 
 * @author dell
 *
 */
public class BillApprovalTransDAO {
	
	/**
	 * This method is used to call the procedure and set the values
	 * corresponding to it for populating the 
	 * PO Details
	 */
	public static void getPODetails(BillApprovalTransVO voObj) 
	{
			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "{call Pkg_Mms_View.Proc_Po_Detail_Info(?,?,?,?,?,?,?,?,?,?)}";
			int nProcIndex = 0;
		
			String strErr = "";

			try 
			{
				daoObj = new HisDAO("MMS","BillApprovalTransDAO");

				nProcIndex = daoObj.setProcedure(strProcName);

				daoObj.setProcInValue(nProcIndex, "modval", "5",1);
				daoObj.setProcInValue(nProcIndex, "item_category", "1",2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
				daoObj.setProcInValue(nProcIndex, "store_Id", voObj.getStrPOStoreId(),4);
				daoObj.setProcInValue(nProcIndex, "poNo", voObj.getStrPONo(),5);
				daoObj.setProcInValue(nProcIndex, "po_frmdate", "0",6);
				daoObj.setProcInValue(nProcIndex, "po_todate", "0",7);
				daoObj.setProcInValue(nProcIndex, "schedule_no", "0",8);
				daoObj.setProcOutValue(nProcIndex, "err", 1,9);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);

				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				if (strErr.equals("")) 
				{
					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					voObj.setStrPODetailsWs(ws);
				} 
				else 
				{
					throw new Exception(strErr);
				}
			} 
			catch (Exception e) 
			{
				voObj.setStrMsgString("BillApprovalTransDAO.getPODetails() -->"+ e.getMessage());
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
	 * This method is used to call the procedure and set the values
	 * corresponding to it for populating the 
	 * GROUP LIST
	 */
	public static void getPeneltyDtls(BillApprovalTransVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "{call Pkg_Mms_View.proc_penalty_dtl(?,?,?,?,?,?,?)}";
			int nProcIndex = 0;
		
			String strErr = "";

			try {
				daoObj = new HisDAO("MMS Transactions","BillApprovalTransDAO");

				nProcIndex = daoObj.setProcedure(strProcName);
				
				
				//System.out.println( "modval"+ "1");
				//System.out.println( "strScheduleNo"+ voObj.getStrScheduleNo());
				//System.out.println( "strPoNo"+ voObj.getStrPONo());
				//System.out.println( "strId"+ voObj.getStrPOStoreId());
				//System.out.println( "hosp_code"+ voObj.getStrHospitalCode());
				
				daoObj.setProcInValue(nProcIndex, "modval", "1",1);
				daoObj.setProcInValue(nProcIndex, "strScheduleNo", voObj.getStrScheduleNo(),2);
				daoObj.setProcInValue(nProcIndex, "strPoNo", voObj.getStrPONo(),3);
				daoObj.setProcInValue(nProcIndex, "strId", voObj.getStrPOStoreId(),4);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),5);
				daoObj.setProcOutValue(nProcIndex, "err", 1,6);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				if (strErr.equals("")) {

					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					//System.out.println("Size"+ws.size());
					voObj.setWsPeneltyDtl(ws);
				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {
				e.printStackTrace();
				voObj.setStrMsgString("BillApprovalTransDAO.getPeneltyDtls() -->"+ e.getMessage());
				voObj.setStrMsgType("1");
			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}
	public static void getWaiveOffApprovedBy(BillApprovalTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.Proc_Consultant_Name(?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {
			daoObj = new HisDAO("MMS Transactions","BillApprovalTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
           // System.out.println("hosp_code->"+voObj.getStrHospitalCode());
			daoObj.setProcInValue(nProcIndex, "modeval", "2",1);
			daoObj.setProcInValue(nProcIndex, "deptunitcode", "0",2);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "seatId", voObj.getStrSeatId(),4);
			
			
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				voObj.setStrWaiveOffApprovedByWS(ws);
				//System.out.println("ws size->"+ws.size());
				} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			voObj.setStrMsgString("BillApprovalTransDAO.getWaiveOffApprovedBy() -->"+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	/**
	 * This method is used to call the procedure and set the values
	 * corresponding to it for populating the 
	 * GROUP LIST
	 */
	/* public static void getPOScheduleInfoDetails(BillApprovalTransVO voObj) 
	{

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "{call Pkg_Mms_View.proc_Po_Schedule_Info_Dtl(?,?,?,?,?,?,?,?,?,?,?)}";  //Total Variables 11
			int nProcIndex = 0;
		
			String strErr = "";

			try {
				daoObj = new HisDAO("MMS Transactions","BillApprovalTransDAO");

				nProcIndex = daoObj.setProcedure(strProcName);
				

				daoObj.setProcInValue(nProcIndex, "modval", "1");   //1
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode()); //2
				daoObj.setProcInValue(nProcIndex, "store_Id", voObj.getStrPOStoreId());   //3
				//System.out.println("PO Stor ID==>"+voObj.getStrPOStoreId());
				daoObj.setProcInValue(nProcIndex, "poNo", voObj.getStrPONo());     //4
				daoObj.setProcInValue(nProcIndex, "po_frmdate", "0");   //5
				daoObj.setProcInValue(nProcIndex, "po_todate", "0");    //6
				
				
				daoObj.setProcInValue(nProcIndex, "item_category", "1"); //7
				daoObj.setProcInValue(nProcIndex, "schedule_no", "0");   //8
				daoObj.setProcInValue(nProcIndex, "delivery_location", voObj.getStrStoreId()); //9	
				//System.out.println("Delivery Location==>"+voObj.getStrStoreId());
			
				daoObj.setProcOutValue(nProcIndex, "err", 1);   //10
				daoObj.setProcOutValue(nProcIndex, "resultset", 2);//11

				daoObj.executeProcedure(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				if (strErr.equals("")) {

					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					voObj.setStrScheduleDtlsWS(ws);
				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {
				voObj
						.setStrMsgString("BillApprovalTransDAO.getPODetails() --> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}
	// Commented by Ajay Deshwal
	*/ 
	
	/**
	 * This method is used to call the procedure and set the values
	 * corresponding to it for populating the 
	 * GROUP LIST
	 */
	public static void getPOScheduleDetails(BillApprovalTransVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "{call Pkg_Mms_View.Proc_Po_Detail_Info(?,?,?,?,?,?,?,?,?,?)}";
			int nProcIndex = 0;
		
			String strErr = "";

			try {
				daoObj = new HisDAO("MMS Transactions","BillApprovalTransDAO");

				nProcIndex = daoObj.setProcedure(strProcName);

				daoObj.setProcInValue(nProcIndex, "modval", "6",1);
				daoObj.setProcInValue(nProcIndex, "item_category", "1",2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
				daoObj.setProcInValue(nProcIndex, "store_Id", voObj.getStrPOStoreId(),4);
				daoObj.setProcInValue(nProcIndex, "poNo", voObj.getStrPONo(),5);
				daoObj.setProcInValue(nProcIndex, "po_frmdate", "0",6);
				daoObj.setProcInValue(nProcIndex, "po_todate", "0",7);
				/* Start */
				
				
				daoObj.setProcInValue(nProcIndex, "schedule_no", "0",8); 
				
				/* End */
				daoObj.setProcOutValue(nProcIndex, "err", 1,9);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);

				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				if (strErr.equals("")) {

					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					voObj.setStrScheduleDtlsWS(ws);
				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {
				voObj
						.setStrMsgString("BillApprovalTransDAO.getPODetails() --> "
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
	 * This method is used to call the procedure and set the values
	 * corresponding to it for populating the 
	 * GROUP LIST
	 */
	public static void getPOScheduleItemDetails(BillApprovalTransVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "{call Pkg_Mms_View.Proc_Po_Detail_Info(?,?,?,?,?,?,?,?,?,?)}";
			int nProcIndex = 0;
		
			String strErr = "";

			try {
				daoObj = new HisDAO("MMS Transactions","BillApprovalTransDAO");

				nProcIndex = daoObj.setProcedure(strProcName);

				daoObj.setProcInValue(nProcIndex, "modval", "9",1); //changed from mode 7 to mode 9
				daoObj.setProcInValue(nProcIndex, "item_category", "1",2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
				daoObj.setProcInValue(nProcIndex, "store_Id", voObj.getStrPOStoreId(),4);
				daoObj.setProcInValue(nProcIndex, "poNo", voObj.getStrPONo(),5);
				daoObj.setProcInValue(nProcIndex, "po_frmdate", "0",6);
				daoObj.setProcInValue(nProcIndex, "po_todate", "0",7);
				daoObj.setProcInValue(nProcIndex, "schedule_no", voObj.getStrScheduleNo(),8);
				
				/* Start */
				
				/* End */
				
				daoObj.setProcOutValue(nProcIndex, "err", 1,9);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);

				daoObj.executeProcedureByPosition(nProcIndex);
				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				if (strErr.equals("")) {

					ws = daoObj.getWebRowSet(nProcIndex, "resultset");
					voObj.setStrScheduleItemDtlsWS(ws);
				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {
				voObj
						.setStrMsgString("BillApprovalTransDAO.getPODetails() --> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}
		}
	
	public static void getPODetailsSearchList(BillApprovalTransVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.Proc_Po_Detail_Info(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("MMS","BillApprovalTransDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modval", "4",1);
			daoObj.setProcInValue(nProcIndex, "item_category", "1",2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "store_Id", voObj.getStrStoreId(),4);
			daoObj.setProcInValue(nProcIndex, "poNo", "0",5);
			daoObj.setProcInValue(nProcIndex, "po_frmdate", "0",6);
			daoObj.setProcInValue(nProcIndex, "po_todate", "0",7);
			daoObj.setProcInValue(nProcIndex, "schedule_no", "0",8);
			daoObj.setProcOutValue(nProcIndex, "err", 1,9);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);
			daoObj.executeProcedureByPosition(nProcIndex);
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				voObj.setStrPOSearchDetailsWs(ws);
			}
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			voObj.setStrMsgString("BillApprovalTransDAO.getPODetails() --> "+ e.getMessage());
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
	
	public static void insert(BillApprovalTransVO vo)
	{
		String strProcName1 = "{call Pkg_Mms_Dml.DML_HSTT_INVOICE_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		String strProcName2 = "{call Pkg_Mms_Dml.DML_HSTT_INVOICE_SCHEDULE_DTL(?,?,?,?,?,?,?,?,?,?)}";
		String strProcName3 = "{call Pkg_Mms_Dml.DML_HSTT_INVOICE_DTL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		String err="";
        int funcIndex = 0;
		
		String strInvoiceNo = "";
		int nProcIndex = 0;
		HisDAO daoObj=null;
		String strFileName="";
		

		try
		{
			 daoObj=new HisDAO("Bill Approval","BillApprovalTransDAO");
			 funcIndex = daoObj.setFunction("{? = call MMS_MST.generate_invoice_no(?,?,?,?)}");
				// Set Value
			 daoObj.setFuncInValue(funcIndex,2,vo.getStrHospitalCode());
			 daoObj.setFuncInValue(funcIndex,3,vo.getStrStoreId());
			 daoObj.setFuncInValue(funcIndex,4,"70");//Bill Verifivation
			 daoObj.setFuncInValue(funcIndex,5,vo.getStrItemCategoryNoH());
			 daoObj.setFuncOutValue(funcIndex,3);
			 daoObj.executeFuncForNumeric(funcIndex);
			
			 strInvoiceNo = daoObj.getFuncNumeric(funcIndex);
					 
					 
					 
			
			 strFileName=vo.getStrStoreId()+"_"+strInvoiceNo+"_"+vo.getStrHospitalCode()/*+"_"+vo.getStrCurrentDateTime()*/+"."+vo.getStrFileExt();
			 vo.setStrFileName(strFileName);
			 //System.out.println("strFileName:::"+strFileName);
			 nProcIndex = daoObj.setProcedure(strProcName1);
			 daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
		     daoObj.setProcInValue(nProcIndex, "storeId", vo.getStrStoreId(),2);
			 daoObj.setProcInValue(nProcIndex, "po_no", vo.getStrPONo(),3);
			 daoObj.setProcInValue(nProcIndex, "invoice_no", strInvoiceNo,4);
			 daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),5);
			 daoObj.setProcInValue(nProcIndex, "invoice_typeId", vo.getStrBillType(),6);
			 daoObj.setProcInValue(nProcIndex, "invoice_date", "",7);
			 daoObj.setProcInValue(nProcIndex, "supplierId", vo.getStrSupplierId(),8);
			 daoObj.setProcInValue(nProcIndex, "po_storeId",vo.getStrPOStoreId(),9);
			 daoObj.setProcInValue(nProcIndex, "supp_invoice_no", vo.getStrBillNo(),10);
			 daoObj.setProcInValue(nProcIndex, "supp_invoice_date", vo.getStrBillDate(),11);
			 daoObj.setProcInValue(nProcIndex, "po_date", vo.getStrPODate(),12);
			 daoObj.setProcInValue(nProcIndex, "currencyId", vo.getStrCurrencyId(),13);
			 daoObj.setProcInValue(nProcIndex, "supp_invoice_amt", vo.getStrBillAmount(),14);
			 daoObj.setProcInValue(nProcIndex, "advance_amt",vo.getStrBalanceAdvance(),15);
			 daoObj.setProcInValue(nProcIndex, "adjusted_advance_amt", vo.getStrAdvanceAdjusted(),16);
			 daoObj.setProcInValue(nProcIndex, "penalty_amt",vo.getStrNetPenalty(),17);
			 daoObj.setProcInValue(nProcIndex, "penalty_waive_amt", vo.getStrWaiveOffAmt(),18);
			 if(vo.getStrWaiveOffAmt().trim().equals("0"))
			 {
			   daoObj.setProcInValue(nProcIndex, "waivepenelty_app_by","",19);
			   daoObj.setProcInValue(nProcIndex, "waivepenelty_app_date","",20); 
			 }
			 else
			 {
			   daoObj.setProcInValue(nProcIndex, "waivepenelty_app_by",vo.getStrWaiveOffApprovedBy(),19);
			   daoObj.setProcInValue(nProcIndex, "waivepenelty_app_date",vo.getStrWaiveOffApprovedDate(),20);
			 }  
			 daoObj.setProcInValue(nProcIndex, "financial_start_date", vo.getStrFinancialStartYear(),21);
			 daoObj.setProcInValue(nProcIndex, "financial_end_date", vo.getStrFinancialEndYear(),22);
			 daoObj.setProcInValue(nProcIndex, "remarks", vo.getStrRemarks(),23);
			 daoObj.setProcInValue(nProcIndex, "seatId", vo.getStrSeatId(),24);
			 daoObj.setProcInValue(nProcIndex, "item_cat_no",vo.getStrItemCategoryNoH(),25);
			 daoObj.setProcInValue(nProcIndex, "overall_tax",vo.getStrOverallPOTax(),26);
			 daoObj.setProcInValue(nProcIndex, "calculated_cost", vo.getStrNetItemCost(),27);
			 
			 
			 
			 daoObj.setProcOutValue(nProcIndex,"err",1,28); 
			 
			 daoObj.execute(nProcIndex,1);
				
			// System.out.println("scheduleNoLength->"+vo.getStrScheduleNoArrH().length);
			 for(int i=0 , stopI = vo.getStrScheduleNoArrH().length;i<stopI;i++)
			 { 
			
				//System.out.println("scheduleNo->"+vo.getStrScheduleNoArrH()[i]);
				//System.out.println("schedule_calculated_cost->"+vo.getStrScheduleCostArrH()[i]);
				nProcIndex = daoObj.setProcedure(strProcName2);
				daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			    daoObj.setProcInValue(nProcIndex, "storeId", vo.getStrStoreId(),2);
				daoObj.setProcInValue(nProcIndex, "po_no", vo.getStrPONo(),3);
				daoObj.setProcInValue(nProcIndex, "invoice_no", strInvoiceNo,4);
				daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),5);
				daoObj.setProcInValue(nProcIndex, "invoice_date", "",6);
				daoObj.setProcInValue(nProcIndex, "po_storeId",vo.getStrPOStoreId(),7);
				daoObj.setProcInValue(nProcIndex, "calculated_cost", vo.getStrScheduleCostArrH()[i],8);
				daoObj.setProcInValue(nProcIndex, "schedule_no", vo.getStrScheduleNoArrH()[i],9);
				
				
				
				
				daoObj.setProcOutValue(nProcIndex,"err",1,10); 
				
				daoObj.execute(nProcIndex,1);
			 }
			
			 nProcIndex = daoObj.setProcedure(strProcName3);
			 daoObj.setProcInValue(nProcIndex, "modeval", "2",1);
			 daoObj.setProcInValue(nProcIndex, "storeId","0",2);
			 daoObj.setProcInValue(nProcIndex, "po_no", vo.getStrPONo(),3);
			 daoObj.setProcInValue(nProcIndex, "invoice_no","0",4);
			 daoObj.setProcInValue(nProcIndex, "hosp_code", vo.getStrHospitalCode(),5);
			 daoObj.setProcInValue(nProcIndex, "invoice_typeId","0",6);
			 daoObj.setProcInValue(nProcIndex, "invoice_date","",7);
			 daoObj.setProcInValue(nProcIndex, "supplierId","0",8);
			 daoObj.setProcInValue(nProcIndex, "po_storeId",vo.getStrPOStoreId(),9);
			 
			 
			 /* Start */
			 
			
			
			 
			 
			 daoObj.setProcInValue(nProcIndex, "supp_invoice_no","0",10);
			 daoObj.setProcInValue(nProcIndex, "supp_invoice_date","",11);
			 daoObj.setProcInValue(nProcIndex, "po_date","",12);
			 daoObj.setProcInValue(nProcIndex, "currencyId","0",13);
			 daoObj.setProcInValue(nProcIndex, "supp_invoice_amt","0",14);
			 daoObj.setProcInValue(nProcIndex, "advance_amt","0",15);
			 daoObj.setProcInValue(nProcIndex, "adjusted_advance_amt","0",16);
			 daoObj.setProcInValue(nProcIndex, "penalty_amt","0",17);
			 daoObj.setProcInValue(nProcIndex, "penalty_waive_amt","0",18);
			 daoObj.setProcInValue(nProcIndex, "waivepenelty_app_by","",19);
			 daoObj.setProcInValue(nProcIndex, "waivepenelty_app_date","",20);
			 daoObj.setProcInValue(nProcIndex, "financial_start_date","",21);
			 daoObj.setProcInValue(nProcIndex, "financial_end_date","",22);
			 daoObj.setProcInValue(nProcIndex, "remarks","",23);
			 daoObj.setProcInValue(nProcIndex, "seatId","0",24);
			 daoObj.setProcInValue(nProcIndex, "item_cat_no","1",25);
			 daoObj.setProcInValue(nProcIndex, "overall_tax","0",26);
			 daoObj.setProcInValue(nProcIndex, "calculated_cost","0",27);
			 
			   
			   
			 /* End */
			 
			 daoObj.setProcOutValue(nProcIndex,"err",1,28);
			 
			 daoObj.execute(nProcIndex,1);
			 
			synchronized (daoObj) 
			{
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
			vo.setStrMsgString("BillApprovalTransDAO.insert() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
		}
	}
}
