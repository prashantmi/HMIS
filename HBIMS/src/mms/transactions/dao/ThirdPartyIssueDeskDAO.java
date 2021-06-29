package mms.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;
import javax.sql.rowset.WebRowSet;
import mms.transactions.vo.ThirdPartyIssueDeskVO;

public class ThirdPartyIssueDeskDAO {
	public static String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
	public static String DATE_FORMAT_NOW = "dd-MMM-yyyy";

	/**
	 * This method is used to call the procedure and set the values
	 * corresponding to it for populating the ITEM CATEGORY LIST
	 */

	public static void getItemDetails(ThirdPartyIssueDeskVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Mms_View.Proc_Thirdpartyissue_Item_Dtl(?,?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("MMS Transactions", "ThirdPartyIssueDeskDAO");

			nProcIndex = daoObj.setProcedure(strProcName);
			// System.out.println("hosp_code->"+voObj.getStrHospitalCode());
			// System.out.println("req_no->"+voObj.getStrReqNo());
			// System.out.println("store_Id->"+voObj.getStrStoreId());
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "hosp_code",
					voObj.getStrHospitalCode(),4);
			daoObj.setProcInValue(nProcIndex, "req_no", voObj.getStrReqNo(),2);
			daoObj.setProcInValue(nProcIndex, "store_Id", voObj.getStrStoreId(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);
			// strErr = daoObj.getString(nProcIndex, "err");

			/*
			 * if (strErr == null) strErr = "";
			 */

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				// System.out.println("ws Size->"+ws.size());
				voObj.setStrItemDetailsWS(ws);
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			voObj.setStrMsgString("ThirdPartyIssueDeskDAO.getItemDetails() --> "
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
