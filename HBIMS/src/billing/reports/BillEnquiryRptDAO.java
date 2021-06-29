package billing.reports;
/*
 * Bill Enquiry Report DAO
 * 
 * author: Debashis Sardar
 * 
 * dated: 05/03/2012
 */
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.BillingVO;

public class BillEnquiryRptDAO {
	public static void getBillTypeDtls(BillEnquiryRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.RPTB_GET_BILLTYPE_DTLS(?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","BillEnquiryRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
			daoObj.setProcOutValue(nProcIndex, "err", 1,2);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,3);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setStrBillTypeWs(ws);
								
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
	 e.printStackTrace();
			voObj
					.setStrMsgString("BillEnquiryRptDAO.getBillTypeDtls() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getClientName(BillEnquiryRptVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_hblt_client_mst(?,?,?,?,?)}";
		int nProcIndex = 0;

		//String strHospitalCode = pageContext.getSession().getAttribute("HOSPITAL_CODE").toString();

		String strErr = "";

		try {

			daoObj = new HisDAO("Billing","OnlineClientDetails");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "client_no", "",3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2,5);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
				voObj.setStrOrgCodeValuesWs(ws);

			} else {
				throw new Exception(strErr);
			}
	}
		
		catch (Exception e) {

			voObj.setStrMsgString("BillEnquiryRptDAO.getClientName() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}

	public static void checkFinalBill(BillEnquiryRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.checkFinalBill(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","BillEnquiryRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),1);
			daoObj.setProcInValue(nProcIndex, "billNo", voObj.getStrTransNo(),2);
			daoObj.setProcInValue(nProcIndex, "rcptNo", voObj.getStrRcptNo(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (ws != null) {

					if (ws.next()) {

						voObj.setStrHospSerName(ws.getString(1));
						voObj.setStrServiceId(ws.getString(2));
						if(!ws.getString(3).equals("-1"))
							voObj.setStrHospSerName("3");

					}
				}
				
								
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
		 
			e.printStackTrace();
			voObj
					.setStrMsgString("BillEnquiryRptDAO.checkFinalBill() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getBillDetails(BillEnquiryRptVO voObj) 
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.rptb_payment_dtl(?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;	
		String strErr = "";

		try 
		{
			daoObj = new HisDAO("Billing","BillEnquiryRptDAO");
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "4",1);
			daoObj.setProcInValue(nProcIndex, "chargeType_id", voObj.getStrBillType(),2);
			daoObj.setProcInValue(nProcIndex, "crNo", voObj.getStrCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "patName", "",4);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),5);
			daoObj.setProcInValue(nProcIndex, "fromDate", voObj.getStrEffectiveFrom(),6);
			daoObj.setProcInValue(nProcIndex, "toDate", voObj.getStrEffectiveTo(),7);
			daoObj.setProcOutValue(nProcIndex, "err", 1,8);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,9);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) 
			{
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");		
				voObj.setStrBillDetailsWs(ws);								
			} 
			else 
			{
				throw new Exception(strErr);
			}
		} 
		catch (Exception e) 
		{
			voObj.setStrMsgString("BillEnquiryRptDAO.getBillDetails() --> "+ e.getMessage());
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
	
	public static void getBillContent(BillEnquiryRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.billWisePaymentDetails(?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing","BillEnquiryRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval", "3",1);
			daoObj.setProcInValue(nProcIndex, "billNo", voObj.getStrTransNo(),3);
			daoObj.setProcInValue(nProcIndex, "rcptNo", voObj.getStrRcptNo(),4);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		
				voObj.setBillContent(ws);
								
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
	 
			voObj.setStrMsgString("BillEnquiryRptDAO.getBillContent() --> "+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	public static void getBillReportDetails(BillEnquiryRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.billWisePaymentDetails(?,?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","BillEnquiryRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", voObj.getStrHospSerName(),1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "billNo",voObj.getStrTransNo() ,3);
			daoObj.setProcInValue(nProcIndex, "rcptNo",voObj.getStrRcptNo(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if (ws != null) 
				{

					voObj.setBillDetailsHtmlWb(ws);
				}
				
								
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
		 
			e.printStackTrace();
			voObj
					.setStrMsgString("BillEnquiryRptDAO.checkFinalBill() --> "
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
