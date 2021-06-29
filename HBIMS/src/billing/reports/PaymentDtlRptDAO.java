package billing.reports;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

public class PaymentDtlRptDAO {
	
	public static void getHospSerList(PaymentDtlRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.RPTB_SBLT_CHARGETYPE_MST(?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","PaymentDtlRptDAO");

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
				voObj.setStrHospSerWs(ws);
				
								
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
		 

			voObj
					.setStrMsgString("PaymentDtlRptDAO.getHospSerList() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getHospitalName(PaymentDtlRptVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Bill_Rpt.RPT_GET_HOSPITAL_LIST(?,?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","PaymentDtlRptDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue( nProcIndex, "modeval","1",1);
			daoObj.setProcInValue( nProcIndex, "hosp_code",voObj.getStrHospitalCode(),2);
			daoObj.setProcInValue( nProcIndex, "seatId",voObj.getStrSeatId(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,5);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		

				voObj.setStrHospitalWs(ws);
				
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("PaymentDtlRptDAO.getHospitalName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}

	}
	
	/*added for: 'payment mode' combo,  by: manisha gangwar dt: 23.8.18*/
	public static void getPaymentModeList(PaymentDtlRptVO voObj)   
	{

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_view.proc_sblt_paymentmode_mst(?,?,?,?)}";
		int nProcIndex = 0;

		String strHospitalCode = voObj.getStrHospitalCode();

		String strErr = "";

		try
		{

			daoObj = new HisDAO("Billing Reports", "PaymentDtlRptDAO.getPaymentModeList");

			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex, "modeval", "1", 1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", strHospitalCode, 2);
			daoObj.setProcOutValue(nProcIndex, "err", 1, 3);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 4);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals(""))
			{

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");

				voObj.setStrPaymentModeList(ws);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			voObj.setStrMsgString("CashCollectionTransDAO.getPaymentModeList() --> " + e.getMessage());
			voObj.setStrMsgType("1");

		} finally
		{
			if (daoObj != null)
			{
				daoObj.free();
				daoObj = null;
			}
		}

	}
	public static void getFeeClerkList(PaymentDtlRptVO voObj)
	{
		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call pkg_bill_rpt.RPTB_user_list(?,?,?,?)}";
		int nProcIndex = 0;
	
		String strErr = "";

		try {

			daoObj = new HisDAO("Billing Reports","BillCancellationLogDetailDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeval","1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),2);
			daoObj.setProcOutValue(nProcIndex, "err", 1,3);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,4);

			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
		

				voObj.setStrClerkWs(ws);
				
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			voObj
					.setStrMsgString("BillCancellationLogDetailRptDAO.getFeeClerkName() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
		
	}
	/*end*/

}
