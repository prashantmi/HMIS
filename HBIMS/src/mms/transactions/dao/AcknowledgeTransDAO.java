package mms.transactions.dao;

import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import mms.dao.AcknowledgeDAO;
import mms.dao.AcknowledgeStockDAO;
import mms.transactions.vo.AcknowledgeTransVO;


public class AcknowledgeTransDAO {
	
	public static void getAcknowledgeVal(AcknowledgeTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("mms","AcknowledgeTransDAO");

			strProcName = "{call PKG_MMS_VIEW.Proc_Acknowledge_Details(?,?,?,?,?,?)}";
			nProcIndex = daoObj.setProcedure(strProcName);

			System.out.println("storeId"+ voObj.getStrStoreId());
			System.out.println("hosp_code"+ voObj.getStrHospitalCode());
			System.out.println("transNo"+ voObj.getStrTransNo());
			
			daoObj.setProcInValue(nProcIndex, "modval", "1",1);
			daoObj.setProcInValue(nProcIndex, "storeId", voObj.getStrStoreId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "transNo", voObj.getStrTransNo(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
 System.out.println("In getAcknowldegeVal ::"+ws.size());
			if (strErr.equals("")) {

				voObj.setStrAcknowledgeDtlWs(ws);
				

			} else {
				throw new Exception(strErr);
			}
			System.out.println("In the AcknowledgeTransDAO");
		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("AcknowledgeTransDAO.getAcknowledgeVal() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	public static void getAcknowledgeValVoucher(AcknowledgeTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("mms","AcknowledgeTransDAO");

			strProcName = "{call PKG_MMS_VIEW.Proc_Acknowledge_Details(?,?,?,?,?,?)}";
			nProcIndex = daoObj.setProcedure(strProcName);

			System.out.println("storeId"+ voObj.getStrStoreId());
			System.out.println("hosp_code"+ voObj.getStrHospitalCode());
			System.out.println("transNo"+ voObj.getStrTransNo());
			
			daoObj.setProcInValue(nProcIndex, "modval", "3",1);
			daoObj.setProcInValue(nProcIndex, "storeId", voObj.getStrStoreId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex, "transNo", voObj.getStrTransNo(),4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");
 System.out.println("In getAcknowldegeVal ::"+ws.size());
			if (strErr.equals("")) {

				voObj.setStrAcknowledgeDtlWs(ws);
				

			} else {
				throw new Exception(strErr);
			}
			System.out.println("In the AcknowledgeTransDAO");
		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("AcknowledgeTransDAO.getAcknowledgeVal() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
	
	public static void getAcknowledgeValView(AcknowledgeTransVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "";
		int nProcIndex = 0;

		String strErr = "";

		try {
			daoObj = new HisDAO("mms","AcknowledgeTransDAO");

			strProcName = "{call PKG_MMS_VIEW.Proc_Acknowledge_Details(?,?,?,?,?,?)}";
			nProcIndex = daoObj.setProcedure(strProcName);

			daoObj.setProcInValue(nProcIndex,"modval", "2",1);
			daoObj.setProcInValue(nProcIndex,"storeId", voObj.getStrStoreId(),2);
			daoObj.setProcInValue(nProcIndex,"hosp_code", voObj.getStrHospitalCode(),3);
			daoObj.setProcInValue(nProcIndex,"transNo", voObj.getStrTransNo(),4);
			daoObj.setProcOutValue(nProcIndex,"err", 1,5);
			daoObj.setProcOutValue(nProcIndex,"resultset", 2,6);

			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = daoObj.getWebRowSet(nProcIndex, "resultset");

			if (strErr.equals("")) {

				voObj.setStrAcknowledgeDtlWs(ws);
				

			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			voObj
					.setStrMsgString("AcknowledgeTransDAO.getAcknowledgeValView() --> "
							+ e.getMessage());
			voObj.setStrMsgType("1");

		} finally {
			if (daoObj != null) {
				daoObj.free();
				daoObj = null;
			}
		}
	}
		public static void getItemVal(AcknowledgeTransVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "";
			int nProcIndex = 0;

			String strErr = "";

			try {
				// value does not set
				daoObj = new HisDAO("mms","AcknowledgeTransDAO");

				strProcName = "{call PKG_MMS_VIEW.Proc_Ack_Item_Dtls(?,?,?,?,?,?,?)}";
				nProcIndex = daoObj.setProcedure(strProcName);

				daoObj.setProcInValue(nProcIndex, "modval", "1",1);
				daoObj.setProcInValue(nProcIndex, "storeId", voObj.getStrStoreId(),2);
				//System.out.println("Store Id-->>>"+voObj.getStrStoreId());
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
				daoObj.setProcInValue(nProcIndex, "transNo", voObj.getStrTransNo(),4);
				//System.out.println("Trans No-->>>"+voObj.getStrTransNo());
				daoObj.setProcInValue(nProcIndex, "reqTypeId", voObj.getStrReqTypeId(),5);
				//System.out.println("Req Type Id-->>"+voObj.getStrReqTypeId());
				daoObj.setProcOutValue(nProcIndex, "err", 1,6);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				System.out.println("in getItemVal size::"+ws.size());
				if (strErr.equals("")) {

					voObj.setStrItemDtlWs(ws);
					

				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {
				e.printStackTrace();
				voObj.setStrMsgString("AcknowledgeTransDAO.getItemVal() --> "+e.getMessage());
				voObj.setStrMsgType("1");

			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}

	}
		
		public static void getAckVal(AcknowledgeTransVO voObj) {

			HisDAO daoObj = null;
			WebRowSet ws = null;

			String strProcName = "";
			int nProcIndex = 0;

			String strErr = "";

			try {
				// value does not set
				daoObj = new HisDAO("mms","AcknowledgeTransDAO");

				strProcName = "{call  PKG_MMS_VIEW.Proc_Ack_Dtls(?,?,?,?,?,?)}";
				nProcIndex = daoObj.setProcedure(strProcName);

				daoObj.setProcInValue(nProcIndex, "modval", "1",1);
				daoObj.setProcInValue(nProcIndex, "storeId", voObj.getStrStoreId(),2);
				daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getStrHospitalCode(),3);
				daoObj.setProcInValue(nProcIndex, "transNo", voObj.getStrTransNo(),4);
				daoObj.setProcOutValue(nProcIndex, "err", 1,5);
				daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);

				daoObj.executeProcedureByPosition(nProcIndex);

				strErr = daoObj.getString(nProcIndex, "err");

				if (strErr == null)
					strErr = "";

				ws = daoObj.getWebRowSet(nProcIndex, "resultset");

				if (strErr.equals("")) {

					voObj.setStrAckDtlWs(ws);
					

				} else {
					throw new Exception(strErr);
				}

			} catch (Exception e) {
				e.printStackTrace();
				voObj
						.setStrMsgString("AcknowledgeTransDAO.getAckVal() --> "
								+ e.getMessage());
				voObj.setStrMsgType("1");

			} finally {
				if (daoObj != null) {
					daoObj.free();
					daoObj = null;
				}
			}

	}
		
		public synchronized static void insertRecord(AcknowledgeTransVO voObj) {

			HisDAO daoObj = null;
			
			AcknowledgeDAO ackDAO = null;
			AcknowledgeStockDAO ackStockDAO = null;

			try {
				// value does not set
				daoObj = new HisDAO("mms","AcknowledgeTransDAO");
				ackDAO = new AcknowledgeDAO();
				ackStockDAO = new AcknowledgeStockDAO();
				
				String strReqId = voObj.getStrReqTypeId();
				
				if(voObj.getStrHiddenValue() == null)
				{
					throw new Exception("999voObj.getStrHiddenValue is null !!");
				}
				
				for(int i = 0, stopI = voObj.getStrHiddenValue().length; i<stopI; i++)
				{
				
					String[] strTemp = voObj.getStrHiddenValue()[i].replace("^", "#").split("#");
					ackStockDAO.setStrToStoreId(voObj.getStrToStrId());//str id
					ackStockDAO.setStrStoreId(voObj.getStrStrId());   // Issuing Store
					ackStockDAO.setStrOldItemId(strTemp[3]);
					ackStockDAO.setStrOldItemBrandId(strTemp[4]);
					ackStockDAO.setStrOldBatchNo(strTemp[1]);
					ackStockDAO.setStrItemCatNo(voObj.getStrItemCatNo());					
					ackStockDAO.setStrInHandQty(voObj.getStrReceivedQty()[i]);				
					ackStockDAO.setStrSeatId(voObj.getStrSeatId());
					ackStockDAO.setStrHospitalCode(voObj.getStrHospitalCode());
					ackStockDAO.setStrAckNo(voObj.getStrTransNo());
					ackStockDAO.setStrOldStockStatusCode(strTemp[6]);
					ackStockDAO.setStrOldItemSerialNo(strTemp[5]);
					ackStockDAO.setStrReqTypeId(voObj.getStrReqTypeId());				
					ackStockDAO.update(daoObj);
				
				}
				ackDAO.setStrRemarks(voObj.getStrRemarks());
				ackDAO.setStrStoreId(voObj.getStrStoreId());//to store id
				ackDAO.setStrTransNo(voObj.getStrTransNo());
				ackDAO.setStrHospitalCode(voObj.getStrHospitalCode());
				ackDAO.setStrReqTypeId(strReqId);
				ackDAO.setStrAckBy(voObj.getStrSeatId());
				
				ackDAO.insert(daoObj);
				
				daoObj.fire();
				
			} catch (Exception e) {
				e.printStackTrace();
				voObj
						.setStrMsgString("AcknowledgeTransDAO.insertRecord() --> "
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
		 * This function is used to set details in view page(Brakage Item Dtl). 
		 * @param _BreakageItemDtlTransVO
		 */
		public static void getTransferDtl(AcknowledgeTransVO _AcknowledgeTransVO)
		{
			
			String strProcName = "{call pkg_mms_view.Proc_Transfer_Detail(?,?,?,?,?,?)}";  // Total 6 Variables
			int nProcIndex = 0;
			String strErr = "";
			
			WebRowSet ws = null;
			HisDAO daoObj=null;
			try
			{
				//System.out.println("Welcome to View Dtl");
				daoObj  = new HisDAO("MMSModule","AcknowledgeTransDAO");
				
				nProcIndex = daoObj.setProcedure(strProcName);
				daoObj.setProcInValue(nProcIndex, "modeval", "1");
				daoObj.setProcInValue(nProcIndex, "hosp_code",  _AcknowledgeTransVO.getStrHospitalCode());
				daoObj.setProcInValue(nProcIndex, "strId",      _AcknowledgeTransVO.getStrStoreId());
				//System.out.println("Transfer Dtl Store ID==>"+_AcknowledgeTransVO.getStrStoreId());
				daoObj.setProcInValue(nProcIndex, "transferNo", _AcknowledgeTransVO.getStrTransNo());
				daoObj.setProcOutValue(nProcIndex, "err",1); 
				daoObj.setProcOutValue(nProcIndex, "resultset",2);
				
				daoObj.executeProcedure(nProcIndex);
				
				strErr = daoObj.getString(nProcIndex, "err");
				if (strErr == null)
					strErr = "";
				ws = daoObj.getWebRowSet(nProcIndex, "resultset");
				if(strErr.equals(""))
				{
					
					_AcknowledgeTransVO.setTransferDtlWs(ws);
					
				}
			}
			catch(Exception _err)
			{
				_err.printStackTrace();
				_AcknowledgeTransVO.setStrMsgString("AcknowledgeTransDAO.getTransferDtl() --> "
						+ _err.getMessage());
				_AcknowledgeTransVO.setStrMsgType("1");
			}
		}	

}
