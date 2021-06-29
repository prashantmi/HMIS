package billing.transactions;

import hisglobal.hisconfig.Config;
import hisglobal.transactionmgnt.HisDAO;

import javax.sql.rowset.WebRowSet;

import billing.dao.ApprovalDAO;
import billing.dao.BillRequisitionDAO;
import billing.dao.InBoundDAO;
import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;

public class RefundApprovalTransDAO
{

	public static void getBillDetails(RefundApprovalTransVO vo)
	{
		String proc_name = "";
		proc_name = "{call pkg_bill_view.proc_SBLT_OUTBOUND_DTL(?,?,?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		try
		{
			dao = new HisDAO("billing", "transactions.RefundApprovalTransDAO .getBillDetails()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "crNo", vo.getStrCrNo(), 1);
			dao.setProcInValue(nprocIndex, "modeval", "1", 9);

			dao.setProcInValue(nprocIndex, "chargetypeid", "4", 3);
			dao.setProcInValue(nprocIndex, "patlisttype", "0", 4);
			dao.setProcInValue(nprocIndex, "searchstr", "", 5);
			dao.setProcInValue(nprocIndex, "searchtype", "1", 6);
			dao.setProcInValue(nprocIndex, "frmrows", "", 7);
			dao.setProcInValue(nprocIndex, "torows", "", 8);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1", 2);

			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(), 10); 
			dao.setProcOutValue(nprocIndex, "err", 1, 11); 
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 12); 
			dao.executeProcedureByPosition(nprocIndex);
			strErr = dao.getString(nprocIndex, "err");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			vo.setStrBillDtls(ws);
		} catch (Exception e)
		{
			vo.setStrMsgString("RefundApprovalTransDAO.getBillDetails() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}

	}

	public static void getTariffDetails_NoAcc(RefundApprovalTransVO vo) throws Exception
	{
		WebRowSet ws = null;
		String strTemp[] = vo.getStrValmode().replace('^', '#').split("#");
		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_BILL_SERVICE_DTL(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try
		{
			dao = new HisDAO("billing", "transactions.RefundApprovalTransDAO .getTariffDetails_NoAcc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", strTemp[0], 1);
			dao.setProcInValue(nprocIndex, "modeval", "1", 3);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "1", 2);
			dao.setProcInValue(nprocIndex, "recNo", "", 5);
			dao.setProcInValue(nprocIndex, "recType", "", 6);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(), 4); 
			dao.setProcOutValue(nprocIndex, "ERR", 1, 7);
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 8);
			dao.executeProcedureByPosition(nprocIndex);
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			vo.setStrTrfDtls(ws);
			if (ws != null)
			{
			}
		} 
		catch (Exception e)
		{
			vo.setStrMsgString("RefundApprovalTransDAO.getTariffDetails_NoAcc() --> " + e.getMessage());
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

	public static void getUnitCmb(RefundApprovalTransVO vo)
	{
		WebRowSet ws = null;
		String strUnitId = vo.getStrUnit();

		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.proc_GBLT_UNIT_MST(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try
		{
			dao = new HisDAO("billing", "transactions.RefundApprovalTransDAO .getTariffDetails_NoAcc()");
			nprocIndex = dao.setProcedure(proc_name);
			// System.out.println("Hospital Code in:
			// getUNITCOMBO():"+vo.getStrHospitalCode());
			dao.setProcInValue(nprocIndex, "hosp_code ", vo.getStrHospitalCode(), 1);
			dao.setProcInValue(nprocIndex, "unit_id ", strUnitId, 2);
			dao.setProcInValue(nprocIndex, "modeval", "1", 3);

			dao.setProcOutValue(nprocIndex, "ERR", 1, 4); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 5); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			vo.setStrUnitCmb(ws);
			if (ws != null)
			{
			}
		} catch (Exception e)
		{
			// e.printStackTrace();
			// throw new
			// Exception("RefundApprovalTransDAO.getUnitCmb()->"+e.getMessage());
			vo.setStrMsgString("RefundApprovalTransDAO.getUnitCmb() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}
		// System.out.println("in dao unitc mb ws sixe--"+ws.size());

	}

	public static void getTariffDetails_Acc(RefundApprovalTransVO vo)
	{
		WebRowSet ws = null;
		String valMode = vo.getStrValmode();

		String strTemp[] = valMode.replace('^', '#').split("#");

		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_PATACCOUNT_SERVICE(?,?,?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try
		{

			dao = new HisDAO("billing", "transactions.RefundApprovalTransDAO .getTariffDetails_Acc()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo ", strTemp[0], 1);
			dao.setProcInValue(nprocIndex, "accNo ", strTemp[4], 2);
			dao.setProcInValue(nprocIndex, "modeval", "1", 5);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "", 3);
			dao.setProcInValue(nprocIndex, "recNo", "", 7);
			dao.setProcInValue(nprocIndex, "recType", "", 8);
			dao.setProcInValue(nprocIndex, "groupId", "", 4);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(), 6); // New Value
			dao.setProcOutValue(nprocIndex, "ERR", 1, 9); // 1 for string return
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 10); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

			if (ws != null)
			{
				vo.setStrTrfDtls(ws);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("RefundApprovalTransDAO.getTariffDetails_Acc() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}

	}

	public static void getRefundRsn(RefundApprovalTransVO vo)
	{
		WebRowSet wb = null;

		String PROC_NAME = "";
		PROC_NAME = "{call pkg_simple_view.proc_hblt_remarks_mst(?,?,?,?,?)}";

		HisDAO dao = null;

		int procIndex = 0;

		String strErr = "";

		try
		{
			dao = new HisDAO("billing", "RefundApprovalTransDAO.getRefundRsn");
			procIndex = dao.setProcedure(PROC_NAME);

			dao.setProcInValue(procIndex, "modeVal", "1", 2);
			dao.setProcInValue(procIndex, "rmkstype", "3", 1);
			//dao.setProcInValue(procIndex, "hosp_code", vo.getStrHospitalCode()); // New
			// Value
			dao.setProcInValue(procIndex, "hosp_code", Config.SUPER_USER_HOSPITAL_CODE, 3); // Added By Pawan Kumar B N on 05-03-2012
			dao.setProcOutValue(procIndex, "err", 1, 4);
			dao.setProcOutValue(procIndex, "resultset", 2, 5);

			dao.executeProcedureByPosition(procIndex);

			strErr = dao.getString(procIndex, "err");

			if (strErr == null)
				strErr = "";
			wb = dao.getWebRowSet(procIndex, "resultset");
			vo.setStrCancelReasonWs(wb);

			if (strErr.equals(""))
			{
				vo.setStrCancelReasonWs(wb);

			} else
			{
				throw new Exception(strErr);
			}

		} catch (Exception e)
		{

			vo.setStrMsgString("RefundApprovalTransDAO.getRefundRsn() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally
		{

			if (dao != null)
			{
				dao.free();
				dao = null;
			}
		}

	}

	public static void getRefundBy(RefundApprovalTransVO vo)
	{
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_SIMPLE_VIEW.PROC_CONSULTANT_NAME(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try
		{
			dao = new HisDAO("billing", "transactions.RefundApprovalTransDAO .getRefundBy()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(), 2); // New Value
			dao.setProcInValue(nprocIndex, "deptunitCode", "", 1);
			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(), 3);

			dao.setProcOutValue(nprocIndex, "ERR", 1, 4); // 1 for string return
			// value
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 5); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");
			vo.setStrCancelledBy(ws);
		} catch (Exception e)
		{
			vo.setStrMsgString("RefundApprovalTransDAO.getRefundRsn() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally
		{
			dao.free();
			dao = null;
		}

	}

	/**
	 * This method calls on SAVE button to cancel selected tariffs and insert
	 * the data into database.
	 * 
	 * To generate a Approval Id, We call some methods of PrimaryKeyDAO class
	 * and to rollback generated Approval Id (in case of any failure) we call
	 * some method of PrimaryKeyLogDAO class.This will write the error in log
	 * file.
	 * 
	 * @param vo
	 */
	public static void insertDataProc(RefundApprovalTransVO vo)
	{

		HisDAO dao = null;
		// BillConfigUtil config=null;

		PrimaryKeyDAO keyDAO = null;
		PrimaryKeyLogDAO keyLogDAO = null;

		ApprovalDAO appDAO = null;
		InBoundDAO inboundDAO = null;
		BillRequisitionDAO reqDAO = null;

		int i = 0;
		int chkCounter = 0;

		/* variables for bill details */
		int reqFlag = 0;
		int appFlag = 0;
		// String strBillChk = "";
		// String temp[]= null;
		String strApprovalId = "0";
		String strStatus = "0";
		String strReqNo = "";
		String strBillNo = "";
		String strBServiceId = "";
		String strChargeTypeId = "";
		String strWardCode = "";
		String strReceiptNo = "";
		String strPatAccNo = "";
		String strDeptCode = "";
		String strCatCode = "";
		String strAdmNo = "";
		String strEpisodeCode = "";
		String strIpdChargeTypeId = "";

		/* variables for tariff details */
		String strTrfChkAll = "";
		String tempBill[] = null;
		// String strScreenPenalty="";
		//String tempNoTtf[] = null;

		int procIndex = 0;

		try
		{
			dao = new HisDAO("billing", "RefundApprovalTransDAO.insertDataProc()->");

			appDAO = new ApprovalDAO();
			inboundDAO = new InBoundDAO();
			reqDAO = new BillRequisitionDAO();
			// config = new BillConfigUtil();
			keyDAO = new PrimaryKeyDAO();
			keyLogDAO = new PrimaryKeyLogDAO();

			// fetch data
			// strBillChk = vo.getStrBillNo();
			strTrfChkAll = vo.getStrChkValues(); // selected bill no

			// System.out.println("vo.getStrCrNo()--"+vo.getStrCrNo());
			// System.out.println("in dao
			// vo.getStrChkValues()-->"+vo.getStrChkValues());
			// System.out.println("in dao strBillChk-->"+strBillChk);
			// System.out.println("in dao strTrfChkAll-->"+strTrfChkAll);
			// System.out.println("in dao userlevel-->"+vo.getStrUserLevel());
			// System.out.println("in dao hospital
			// code-->"+vo.getStrHospitalCode());
			// System.out.println("in dao seat id-->"+vo.getStrSeatId());
			// System.out.println("in dao refund by-->"+vo.getStrRefBy());
			// System.out.println("in dao refund
			// reason-->"+vo.getStrOtherReason());

			// tempBill = strBillChk.replace('^', '#').split("#");

			tempBill = strTrfChkAll.replace('^', '#').split("#");

			strBServiceId = tempBill[3];
			strBillNo = tempBill[0];
			strChargeTypeId = tempBill[1];
			strWardCode = tempBill[7];
			strReceiptNo = tempBill[8];
			strPatAccNo = tempBill[4];
			// System.out.println("Pateint Acct No::::::"+strPatAccNo);
			strAdmNo = tempBill[11];
			strEpisodeCode = tempBill[12];
			strCatCode = tempBill[13];
			strDeptCode = tempBill[14];
			strIpdChargeTypeId = tempBill[15];

			vo.setStrBillNo(strBillNo);
			vo.setStrPatAccNo(strPatAccNo);
			vo.setStrBServiceId(strBServiceId);

			// System.out.println("strBServiceId--"+strBServiceId);
			// System.out.println("strChargeTypeId--"+strChargeTypeId);
			// System.out.println("strBillNo--"+strBillNo);
			// System.out.println("strWardCode--"+strWardCode);
			// System.out.println("strReceiptNo--"+strReceiptNo);
			// System.out.println("strPatAccNo--"+strPatAccNo);
			// System.out.println("strAdmNo--"+strAdmNo);
			// System.out.println("strEpisodeCode--"+strEpisodeCode);
			// System.out.println("strDeptCode--"+strDeptCode);
			// System.out.println("strIpdChargeTypeId--"+strIpdChargeTypeId);
			// System.out.println("strCatCode--"+strCatCode);

			/*
			 * To get approval id by executing the following procedure
			 * pkg_bill_view. proc_sblt_primarykey_dtl
			 * (“APPROVAL”,1,:resultset,:err); set appFlag = 1;
			 */

			if (vo.getStrRefundMode().equals("0"))
			{

				strStatus = "1";

				try
				{

					keyDAO.setStrKeyname("APPROVAL");
					keyDAO.setStrBlockkey("1");
					keyDAO.setStrHospCode(vo.getStrHospitalCode());
					keyDAO.select(dao);
					strApprovalId = keyDAO.getStrPrimrayKeyValue();
					// System.out.println("Genrated App Id-->"+strApprovalId);
					vo.setStrApprovalId(strApprovalId);

				} catch (Exception e)
				{
					appFlag = 1;
					throw new Exception("keyDAO.setStrKeyname(APPROVAL)" + e.getMessage());

				}

				/*
				 * If above procedure does not return error (means approval id is
				 * not null) then Put approval details by calling the following
				 * procedure pkg_bill_dml.dml_hblt_approval_dtl (?,?,…,:err);
				 */

				appDAO.setStrApprovalId(strApprovalId);
				appDAO.setStrApprovalSlNo("1");
				appDAO.setStrReceiptType("2");
				appDAO.setStrBServiceId(strBServiceId);
				appDAO.setStrChargeTypeId(strChargeTypeId);
				appDAO.setStrDiscountType("0");
				appDAO.setStrDiscountUnit("0");
				appDAO.setStrEmpNo(vo.getStrRefBy());
				appDAO.setStrUserLevel(vo.getStrUserLevel());
				appDAO.setStrRemarks(vo.getStrOtherReason());
				appDAO.setStrApprovalType("3");
				appDAO.setStrStatus("1");
				appDAO.setStrSeatId(vo.getStrSeatId());
				appDAO.setStrIsValid("1");
				appDAO.setStrHospitalCode(vo.getStrHospitalCode());

				appDAO.insert(dao);//no change in procedure for credit billing..

			}
			/*
			 * To get Requisition No executing the following procedure
			 * pkg_bill_view. proc_sblt_primarykey_dtl
			 * (“REQNO”,1,:resultset,:err); reqFlag = 1
			 */
			try
			{
				keyDAO.setStrKeyname("REQNO");
				keyDAO.setStrBlockkey("1");
				keyDAO.setStrHospCode(vo.getStrHospitalCode());
				keyDAO.select(dao);
				strReqNo = keyDAO.getStrPrimrayKeyValue();
				vo.setStrReqNo(strReqNo);

				// System.out.println("REQ NO Genrated-->>>"+strReqNo);

			} catch (Exception e)
			{
				reqFlag = 1;
				throw new Exception("keyDAO.setStrKeyname(REQNO)" + e.getMessage());
			}

			/*
			 * If above procedure does not return error (means requisition no.
			 * is not null) then to insert the details into inbound details Call
			 * Pkg_bill_dml.dml_sblt_inbound_dtl(?,?,..,:err)
			 */

			inboundDAO.setStrReqNo(strReqNo);
			inboundDAO.setStrDeptCode(strDeptCode);
			inboundDAO.setStrChargeTypeId(strChargeTypeId);
			inboundDAO.setStrBServiceId(strBServiceId);
			inboundDAO.setStrBillNo(strBillNo);
			inboundDAO.setStrCatCode(strCatCode);
			inboundDAO.setStrEpisodeCode(strEpisodeCode);
			inboundDAO.setStrPuk(vo.getStrCrNo());
			inboundDAO.setStrAdmNo(strAdmNo);
			inboundDAO.setStrPatAccNo(strPatAccNo);
			inboundDAO.setStrAppId(strApprovalId);
			inboundDAO.setStrStatus(strStatus);
			inboundDAO.setStrSeatId(vo.getStrSeatId());
			inboundDAO.setStrIsValid("1");
			inboundDAO.setStrAppBy(vo.getStrRefBy());
			inboundDAO.setStrReqType("2");
			inboundDAO.setStrIpdChargeTypeId(strIpdChargeTypeId);
			inboundDAO.setStrWardCode(strWardCode);
			inboundDAO.setStrReceiptNo(strReceiptNo);
			inboundDAO.setStrHospitalCode(vo.getStrHospitalCode());
			inboundDAO.setStrRemarks(vo.getStrOtherReason());
			inboundDAO.insert(dao);//no change in procedure for credit billing..
			// System.out.println("INBOUND DAO:::::2");
			/*
			 * If above (means requisition no. is not null) then to insert the
			 * requisition details into bill requisition detail table Call
			 * pkg_bill_dml. dml_hblt_billreq_dtl(?,?,..,:err)
			 */

			reqDAO.setStrReqNo(strReqNo);
			reqDAO.setStrDeptCode(strDeptCode);
			reqDAO.setStrChargeTypeId(strChargeTypeId);
			reqDAO.setStrBServiceId(strBServiceId);
			reqDAO.setStrCatCode(strCatCode);
			reqDAO.setStrEpisodeCode(strEpisodeCode);
			reqDAO.setStrPuk(vo.getStrCrNo());
			reqDAO.setStrAdmNo(strAdmNo);
			reqDAO.setStrPatAccNo(strPatAccNo);
			reqDAO.setStrAppId(strApprovalId);
			reqDAO.setStrBillNo(strBillNo);
			reqDAO.setStrRemarks(vo.getStrOtherReason());
			reqDAO.setStrStatus(strStatus);
			reqDAO.setStrSeatId(vo.getStrSeatId());
			reqDAO.setStrIsValid("1");
			reqDAO.setStrAppBy(vo.getStrRefBy());
			reqDAO.setStrReqType("2");
			reqDAO.setStrIpdChargeTypeId(strIpdChargeTypeId);
			reqDAO.setStrWardCode(strWardCode);
			reqDAO.setStrReceiptNo(strReceiptNo);
			reqDAO.setStrHospitalCode(vo.getStrHospitalCode());
			reqDAO.setStrProcessFeePenalty(vo.getStrProcessFeePenalty());
			reqDAO.insert(dao);//no change in procedure for credit billing..
			// System.out.println("REQ DAO INSERT:::::3");

			/* To insert Tariff Details */

			//	tempNoTtf = strTrfChkAll.split(",");
			// int length = tempNoTtf.length;
			int length = vo.getChkHidd().length;
			//System.out.println("total length = " + length);

			/* 7.1 LOOP UNTIL ALL CHECKED TARIFFS ARE HANDLED */

			for (i = 0; i < length; i++)
			{
				/* to find penalty for user'd choice */
				// System.out.println("Refund
				// Type:::::"+vo.getRefundType()[i-1]);
				// System.out.println("Unit ID::::::::"+vo.getStrUnitId()[i-1]);
				/*
				 * if(vo.getRefundType()[i-1].equals("2")) { if
				 * (vo.getStrHospServ().equals("1")) { strScreenPenalty =
				 * config.getOpdRefundPenalty();
				 *  } else if (vo.getStrHospServ().equals("2")) {
				 * strScreenPenalty = config.getIpdRefundPenalty();
				 *  } else { strScreenPenalty =
				 * config.getEmergencyRefundPenalty(); } } else {
				 * strScreenPenalty="0"; }
				 */

				if (Integer.parseInt(vo.getChkHidd()[i]) == 1)
				{

					//if (chkCounter == 0)
					//{

						String proc_name = "{call pkg_bill_dml.dml_refund_approval_service(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
						procIndex = dao.setProcedure(proc_name);
					//}
					RefundApprovalServiceProcedure(vo, vo.getChk()[chkCounter++], dao, i, procIndex);
				}
				// RefundApprovalServiceProcedure(vo,tempNoTtf[i],dao,i);
			}

			synchronized (dao)
			{
				dao.fire();

			}
		} catch (Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("RefundApprovalTransDAO.insertDataProc()-->" + e.getMessage());
			vo.setStrMsgType("1");
			try
			{
				if (appFlag == 1)
				{
					keyLogDAO.setStrBlockkey("1");
					// keyLogDAO.setStrError(vo.getStrPrimaryKeyMsg());
					keyLogDAO.setStrKeyname("APPROVAL");
					keyLogDAO.setStrStartkey(strApprovalId);
					keyLogDAO.setStrHospCode(vo.getStrHospitalCode());
					keyLogDAO.setStrSeatid(vo.getStrSeatId());
					keyLogDAO.insert(dao);
				}
				if (reqFlag == 1)
				{
					keyLogDAO.setStrBlockkey("1");
					// keyLogDAO.setStrError(vo.getStrPrimaryKeyMsg());
					keyLogDAO.setStrKeyname("REQNO");
					keyLogDAO.setStrHospCode(vo.getStrHospitalCode());
					keyLogDAO.setStrStartkey(strApprovalId);
					keyLogDAO.setStrSeatid(vo.getStrSeatId());
					keyLogDAO.insert(dao);
				}

			} catch (Exception e1)
			{
				vo.setStrMsgType("1");
				vo.setStrMsgString("RefundApprovalTransDAO.PK Log(Approval) --> " + e1.getMessage());
			}

			vo.setStrMsgString("RefundApprovalTransDAO.insertDataProc()-->" + e.getMessage());
			vo.setStrMsgType("1");
		}

		finally
		{
			if (dao != null)
			{
				dao.free();
				dao = null;
			}
			appDAO = null;
			inboundDAO = null;
			reqDAO = null;
			// config = null;
			keyDAO = null;
			keyLogDAO = null;

		}

	}

	public static void RefundApprovalServiceProcedure(RefundApprovalTransVO vo, String tempNoTtf, HisDAO dao, int i,
			int nprocIndex)
	{
		// String proc_name = "";
		// proc_name = "{call
		// pkg_bill_dml.dml_refund_approval_service(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

		// int nprocIndex = 0;

		try
		{

			/* HIDDEN VALUE
			 * 
			 * HBLNUM_TARIFF_ID||''^''||(NVL(HBLNUM_REMAINED_QTY,0)*Bill_Mst.getUnitBaseValue(GNUM_QTY_UNIT_ID,A.GNUM_HOSPITAL_CODE))||''^''||GNUM_QTY_UNIT_ID||''^''||
			 * NVL(HBLNUM_TARIFF_RATE,0)||''^''||GNUM_RATE_UNIT_CODE||''^''||HBLNUM_GROUP_ID||''^''||
			 * NVL(HBLNUM_TARIFF_ACTUAL_RATE,0)||''^''||NVL(HBLNUM_SERVICE_TAX,0)||''^''||NVL(HBLNUM_DISCOUNT_UNIT,0)||''^''||
			 * NVL(HBLNUM_DISCOUNT_TYPE ,
			 * 0)||''^''||GSTR_TARIFF_ID||''^''||HBLNUM_APPROVAL_ID||''^''||
			 * Bill_Mst.getUnitName(A.GNUM_HOSPITAL_CODE,GNUM_RATE_UNIT_CODE)||''^''||
			 * Bill_Mst.getUnitBaseValue(GNUM_RATE_UNIT_CODE,A.GNUM_HOSPITAL_CODE)||''^''||
			 * HBLNUM_IS_PACKAGE||''^''||HBLNUM_NET_AMOUNT||''^''||HBLNUM_PENELTY||''^''||
			 * HBLNUM_PENELTY_AMT||
			 * ''^''||HBLNUM_RECIEPT_NO||''^''||NVL(trim(SBLNUM_SERVICE_ID),''
			 * '')
			 */

			String[] temp = tempNoTtf.replace('^', '#').split("#");

			String strTariffId = temp[0];
			String strTariffRate = temp[3];

			String strRateUnitId = temp[4];
			if (strRateUnitId == null || strRateUnitId.equals(""))
				strRateUnitId = "0";

			String strGroupId = temp[5];
			String strServiceTax = temp[7];
			String strDiscountPerUnit = temp[8];
			String strDiscountType = temp[9];
			String strGblTariffId = temp[10];
			String strIsPackage = temp[14];
			String strTrfReceiptNo = temp[18];
			String strServiceId = temp[19];

			if (strDiscountType == null || strDiscountType.trim().length() == 0)
			{

				strDiscountType = "0";

			}

			String strDisAppId = temp[11]; // discount approval id
			if (strDisAppId == null || strDisAppId.equals(""))
				strDisAppId = "0";

			// String strPenalty = temp[16];
			String strPenalty = vo.getTrf_penalty()[i];

			String[] temp1 = vo.getStrUnitId()[i].replace('^', '#').split("#"); // unitid
			// ^
			// base
			// value

			// nprocIndex = dao.setProcedure(proc_name);

			dao.setProcInValue(nprocIndex, "modval", "1", 1);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(), 2); // 1 // New Value
			dao.setProcInValue(nprocIndex, "reqNo", vo.getStrReqNo(), 3); // 2
			dao.setProcInValue(nprocIndex, "billNo", vo.getStrBillNo(), 4); // 3
			dao.setProcInValue(nprocIndex, "trfRecNo", strTrfReceiptNo, 5); // 4
			dao.setProcInValue(nprocIndex, "patAccNo", vo.getStrPatAccNo(), 6); // 5
			dao.setProcInValue(nprocIndex, "puk", vo.getStrCrNo(), 7); // 6
			dao.setProcInValue(nprocIndex, "grpId", strGroupId, 8); // 7
			dao.setProcInValue(nprocIndex, "trfId", strTariffId, 9); // 8
			dao.setProcInValue(nprocIndex, "refQty", vo.getRefundQty()[i], 10); // 9
			dao.setProcInValue(nprocIndex, "refQtyUnitId", temp1[0], 11); // 10
			dao.setProcInValue(nprocIndex, "rate", strTariffRate, 12); // 11
			dao.setProcInValue(nprocIndex, "rateUnitId", strRateUnitId, 13); // 12
			dao.setProcInValue(nprocIndex, "serTax", strServiceTax, 14); // 13
			dao.setProcInValue(nprocIndex, "penelty", (strPenalty==null || strPenalty.equalsIgnoreCase("null")|| strPenalty.equalsIgnoreCase(""))?"0":strPenalty, 15); // 14
			dao.setProcInValue(nprocIndex, "appId", strDisAppId, 16); // 15
			dao.setProcInValue(nprocIndex, "disUnit", strDiscountPerUnit, 17); // 16
			dao.setProcInValue(nprocIndex, "disType", strDiscountType, 18); // 17
			dao.setProcInValue(nprocIndex, "bServiceId", vo.getStrBServiceId(), 19); // 18
			dao.setProcInValue(nprocIndex, "serviceId", strServiceId, 20); // 19
			// Check
			// It
			dao.setProcInValue(nprocIndex, "gblTrfId", strGblTariffId, 21); // 20
			dao.setProcInValue(nprocIndex, "isPackage", strIsPackage, 22); // 21
			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(), 23); // 22
			dao.setProcInValue(nprocIndex, "refundMode", vo.getStrRefundMode(), 24); // 22
			dao.setProcOutValue(nprocIndex, "err", 1, 25); // 1 for return string // //23
			//new credit values
			dao.setProcInValue(nprocIndex, "clNo", vo.getClNo()[i], 26); // 22
			dao.setProcInValue(nprocIndex, "clDate", vo.getClDate()[i].equals("-")?"":vo.getClDate()[i], 27); // 22
			dao.setProcInValue(nprocIndex, "clPath", vo.getClPath()[i], 28); // 22
			dao.setProcInValue(nprocIndex, "clientNo", vo.getClientNo()[i], 29); // 22
			dao.setProcInValue(nprocIndex, "amtPaidByPat", vo.getAmtByPat()[i], 30); // 22
			dao.setProcInValue(nprocIndex, "amtPaidByClient", vo.getAmtByClient()[i], 31); // 22
			

			// execute procedure
			dao.execute(nprocIndex, 1);

		} catch (Exception e)
		{
			 e.printStackTrace();
			vo.setStrMsgString("RefundApprovalTransDAO.RefundApprovalServiceProcedure() --> " + e.getMessage());
			vo.setStrMsgType("1");
		}

	}

	/*
	 * method used in insert method to find package details if patient account
	 * exist
	 */

	public static WebRowSet getPatAccPkgDtl(RefundApprovalTransVO vo, String strBillNo, String strTariffId,
			String strPatAccNo)
	{
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call pkg_bill_view.Proc_Hblt_Pataccount_Package(?,?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try
		{
			dao = new HisDAO("billing", "transactions.RefundApprovalTransDAO .getPatAccPkgDtl()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo", strBillNo, 1);
			dao.setProcInValue(nprocIndex, "accNo", strPatAccNo, 2);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "", 3);
			dao.setProcInValue(nprocIndex, "pkgId", strTariffId, 4);
			dao.setProcInValue(nprocIndex, "modeval", "1", 5);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(), 6); // New Value
			dao.setProcOutValue(nprocIndex, "ERR", 1, 7); // 1 for return string
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 8); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

		} catch (Exception e)
		{
			vo.setStrMsgString("RefundApprovalTransDAO.getPatAccPkgDtl() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally
		{
			dao.free();
			dao = null;
		}
		return ws;
	}

	/* method used in insert method to find package details */

	public static WebRowSet getBillPkgDtl(RefundApprovalTransVO vo, String strBillNo, String strTariffId)
	{
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call pkg_bill_view. Proc_Hblt_Bill_Package_Dtl(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		try
		{
			dao = new HisDAO("billing", "transactions.RefundApprovalTransDAO .getBillPkgDtl()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "billNo", strBillNo, 1);
			dao.setProcInValue(nprocIndex, "to_be_refund_pkg", "", 2);
			dao.setProcInValue(nprocIndex, "pkgId", strTariffId, 3);

			dao.setProcInValue(nprocIndex, "modeval", "1", 4);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(), 5); // New Value
			dao.setProcOutValue(nprocIndex, "ERR", 1, 6); // 1 for return string
			dao.setProcOutValue(nprocIndex, "RESULTSET", 2, 7); // 2 for object
			// execute procedure
			dao.executeProcedureByPosition(nprocIndex);
			// get value
			strErr = dao.getString(nprocIndex, "ERR");
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "RESULTSET");

		} catch (Exception e)
		{
			vo.setStrMsgString("RefundApprovalTransDAO.getBillPkgDtl() --> " + e.getMessage());
			vo.setStrMsgType("1");
		} finally
		{
			dao.free();
			dao = null;
		}
		return ws;
	}

}
