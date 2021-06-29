package billing.transactions;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import billing.dao.ApprovalDAO;
import billing.dao.BillRequisitionTariffDAO;
import billing.dao.PrimaryKeyDAO;
import billing.dao.PrimaryKeyLogDAO;

public class DiscountApprovalTransDAO {

	public void getTariffDtl(DiscountApprovalTransVO vo) 
	{
		String strValmode = vo.getStrValmode();
		String strTemp[] = strValmode.replace('@', '#').split("#");

		String proc_name = "";
		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_BILLREQ_TARIFF_DTL(?,?,?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		WebRowSet ws = null;
		
		try 
		{
			dao = new HisDAO("billing","transactions.DiscountApprovalTransDAO .getTariffDtl()");
			nprocIndex = dao.setProcedure(proc_name);

			dao.setProcInValue(nprocIndex, "reqno ", strTemp[0],1);
			dao.setProcInValue(nprocIndex, "modeval", "1",2);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),3);
			dao.setProcInValue(nprocIndex, "chargeTypeId", "",4);
			dao.setProcInValue(nprocIndex, "deptCode", "0",5);			
			dao.setProcOutValue(nprocIndex, "err", 1,6); 
			dao.setProcOutValue(nprocIndex, "resultset", 2,7); 
			dao.executeProcedureByPosition(nprocIndex);
			
			strErr = dao.getString(nprocIndex, "err");

			if (strErr == null)
				strErr = "";

			ws = dao.getWebRowSet(nprocIndex, "resultset");
			vo.setStrOnLineTariffWs(ws);
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("DiscountApprovalTransDAO.getTariffDtl() --> "+ e.getMessage());
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

	public WebRowSet getPopUpDtl(DiscountApprovalTransVO vo) 
	{
		WebRowSet ws = null;
		String strValmode = vo.getStrValmode();
		vo.setStrPopUpId("1");
		String strTemp[] = strValmode.replace('^', '#').split("#");
		String proc_name = "";

		proc_name = "{call PKG_BILL_VIEW.PROC_HBLT_APPROVAL_DTL(?,?,?,?,?,?,?)}";

		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		
		try 
		{
			dao = new HisDAO("billing","transactions.DiscountApprovalTransDAO.getPopUpDtl()");
			nprocIndex = dao.setProcedure(proc_name);
			dao.setProcInValue(nprocIndex, "approvalID", strTemp[0]);
			dao.setProcInValue(nprocIndex, "approvalType", "2");
			dao.setProcInValue(nprocIndex, "record_status", "1");
			dao.setProcInValue(nprocIndex, "modeVal", "1");
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode());
			dao.setProcOutValue(nprocIndex, "err", 1);
			dao.setProcOutValue(nprocIndex, "resultset", 2);
			dao.executeProcedure(nprocIndex);
			strErr = dao.getString(nprocIndex, "err");

			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("DiscountApprovalTransDAO.getPopUpDtl() --> "+ e.getMessage());
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
		return ws;
	}

	public WebRowSet getDiscountRsn(DiscountApprovalTransVO VO) 
	{
		String proc_name = "";
		proc_name = "{call pkg_bill_view.PROC_HBLT_REMARKS_MST(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strerr = "";
		WebRowSet ws = null;

		try 
		{
			dao = new HisDAO("billing","transactions.DiscountApprovalTransDAO.getDiscountRsn()");
			nprocIndex = dao.setProcedure(proc_name);

			dao.setProcInValue(nprocIndex, "rmksType", "1",1);
			dao.setProcInValue(nprocIndex, "modeVal", "1",2);
			dao.setProcInValue(nprocIndex, "hosp_code", VO.getStrHospitalCode(),3);
			dao.setProcOutValue(nprocIndex, "err", 1,4); 
			dao.setProcOutValue(nprocIndex, "resultset", 2,5);
			
			dao.executeProcedureByPosition(nprocIndex);
			strerr = dao.getString(nprocIndex, "err");

			if (strerr == null)
				strerr = "";

			if (strerr.equals("")) 
			{
				ws = dao.getWebRowSet(nprocIndex, "resultset");
				VO.setStrMsgType("0");
			} 
			else 
			{
				throw new Exception(strerr);
			}
		}
		catch (Exception e) 
		{
			VO.setStrMsgString("transactions.DiscountApprovalTransDAO.getDiscountRsn()--> "+ e.getMessage());
			VO.setStrMsgType("1");
		}
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
		return ws;
	}

	public WebRowSet getDiscountBy(DiscountApprovalTransVO vo) 
	{
		WebRowSet ws = null;
		String proc_name = "";
		proc_name = "{call PKG_SIMPLE_VIEW.PROC_CONSULTANT_NAME(?,?,?,?,?)}";
		HisDAO dao = null;
		int nprocIndex = 0;
		String strErr = "";
		
		try 
		{
			dao = new HisDAO("billing","transactions.DiscountApprovalTransDAO .getDiscountBy()");
			nprocIndex = dao.setProcedure(proc_name);
			
			dao.setProcInValue(nprocIndex, "deptunitCode", "0",1);
			dao.setProcInValue(nprocIndex, "hosp_code", vo.getStrHospitalCode(),2);
			dao.setProcInValue(nprocIndex, "seatId", vo.getStrSeatId(),3);			
			dao.setProcOutValue(nprocIndex, "err", 1,4);
			dao.setProcOutValue(nprocIndex, "resultset", 2,5);
			
			dao.executeProcedureByPosition(nprocIndex);
			strErr = dao.getString(nprocIndex, "err");
			
			if (strErr == null)
				strErr = "";
			ws = dao.getWebRowSet(nprocIndex, "resultset");
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("DiscountApprovalTransDAO.getDiscountBy() --> "+ e.getMessage());
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
		return ws;
	}

	public void getRsnRmk(DiscountApprovalTransVO vo) 
	{
		HisUtil hisutil = null;
		WebRowSet w1;
		WebRowSet w2;
		String str2 = null;
		String str1 = null;
		
		try 
		{
			hisutil = new HisUtil("transaction", "RefundApprovalTransHLP");
			w1 = getDiscountRsn(vo);
			w2 = getDiscountBy(vo);
			if (w1 != null && w1.size() > 0) 
			{
				str2 = hisutil.getOptionValue(w1, "0", "0^Select Value", true);
			} 
			else 
			{
				str2 = "";
			}
			if (w2 != null && w2.size() > 0) 
			{
				if(w2.size()==1)//Only One Employee Found or Current Logged In Employee
					str1 = hisutil.getOptionValue(w2, "", "", true);
				else
					str1 = hisutil.getOptionValue(w2, "", "0^Select Value", true);
			} 
			else 
			{
				str1 = "";
			}
			vo.setStrRsn(str2);
			vo.setStrRmk(str1);
		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("DiscountApprovalTransDAO.getRsnRmk() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
		w1 = w2 = null;
	}

	/** ************************(Function Calling)******************************* */
	/* ---------------Calling of Function for View Charges--------------------- */
	public static void callingFunctionAppSlNo(DiscountApprovalTransVO VO,int i, HisDAO dao) 
	{
		String retVal = null;
		int funcIndex = 0;
	
		try 
		{
			funcIndex = dao.setFunction("{? = call BILL_MST.getApprovalSlNo(?,?)}");
			dao.setFuncInValue(funcIndex, 2, VO.getStrHospitalCode());
			dao.setFuncInValue(funcIndex, 3, VO.getApprovalId()[i]);
			dao.setFuncOutValue(funcIndex, 3);

			dao.executeFuncForNumeric(funcIndex);
			retVal = dao.getFuncNumeric(funcIndex);
			VO.setStrApprovalSlNo(retVal);

		} 
		catch (Exception e) 
		{
			VO.setStrMsgString("DiscountApprovalTransDAO.callingFunctionView() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		}
	}

	/* ---------------Calling of Function for View Charges--------------------- */
	public static void callingFunctionAppSlNo1(DiscountApprovalTransVO VO,HisDAO dao) 
	{
		String retVal = null;
		int funcIndex = 0;
	
		try 
		{
			funcIndex = dao.setFunction("{? = call BILL_MST.getApprovalSlNo(?,?)}");
			dao.setFuncInValue(funcIndex, 2, VO.getStrHospitalCode());
			dao.setFuncInValue(funcIndex, 3, VO.getApprovalId()[0]);
			dao.setFuncOutValue(funcIndex, 3);

			dao.executeFuncForNumeric(funcIndex);
			retVal = dao.getFuncNumeric(funcIndex);
			VO.setStrApprovalSlNo(retVal);
		} 
		catch (Exception e) 
		{
			VO.setStrMsgString("DiscountApprovalTransDAO.callingFunctionView() --> "+ e.getMessage());
			VO.setStrMsgType("1");
		}
	}

	/**
	 * ****************************Insert Logic For Final
	 * Adjustment********************************
	 */
	public static boolean getInsertDataForFinalAdjustment(
			DiscountApprovalTransVO vo) {
		// Decleration
		HisDAO dao = null;
		boolean retVal = false;
		String[] strHiddenVal = null;
		// int length = vo.getStrLength();
		int appSlNo = 0, appFlag = 0;
		double totDisUnit = 0.0;
		String appId = "";

		// String current1 = "";
		// String totaldisUnit1 = "";
		String strHbReqNo = null;
		String strSbChgTypId = null;
		String strSblBservId = null;
		String strHblReqType = null;
		// String strReqTypBServName = null;
		// Createing Object for Table Specific DAO
		PrimaryKeyDAO pKeyDao = new PrimaryKeyDAO();
		PrimaryKeyLogDAO pKeyLogDao = new PrimaryKeyLogDAO();
		ApprovalDAO appDao = new ApprovalDAO();
		BillRequisitionTariffDAO billreqtrfDao = new BillRequisitionTariffDAO();

		try {
			dao = new HisDAO("billing",
					"transactions.DAObilling.getInsertDataForFinalAdjustment()");

			strHiddenVal = vo.getStrHidValOnLineReqDis().split("\\^");

			strHbReqNo = strHiddenVal[0];
			strSbChgTypId = strHiddenVal[3];
			strSblBservId = strHiddenVal[4];
			strHblReqType = strHiddenVal[13];
			// strReqTypBServName = strHiddenVal[15];
			// System.out.println("*********************DAO************************");
			// System.out.println("Approval ID:::"+vo.getApproval_id());
			// System.out.println("strHblReqType:::"+strHblReqType);
			// System.out.println("strSbChgTypId:::"+strSbChgTypId);
			// System.out.println("vo.getStrIpdBillCurrDis():::"+vo.getStrIpdBillCurrDis());
			// System.out.println("Add Kerna Hai ya nahi:::"+
			// vo.getStrhiddTrfFADis());
			// System.out.println("FinalCalculatedSum:::"+vo.getSumFAdjustment());
			// System.out.println("CurrentDiscount Type(1 Fixed 2
			// %):::"+vo.getStrCurrDisTypeFA());
			// System.out.println("Prev Discount
			// Type:::"+vo.getStrPrevDisTypeFA());
			// System.out.println("Prev Dis Amt:::"+vo.getStrLstDisUnitFA());
			// System.out.println("Tariff Id:::"+vo.getStrTrfId());
			// System.out.println("Final Discount After
			// (Add/Sub):::"+vo.getStrIpdBillCurrDis());
			// System.out.println("**********************DAO***********************");

			if (vo.getApprovalId()[0].equals("0")
					|| vo.getApprovalId()[0].equals("null")) {

				System.out.println("entered = 1");
				// get approval id
				pKeyDao.setStrKeyname("APPROVAL");
				pKeyDao.setStrBlockkey("1");
				pKeyDao.setStrHospCode(vo.getStrHospitalCode());
				pKeyDao.select(dao);
				appSlNo = 1;

				if (pKeyDao.getStrErr() != null
						&& pKeyDao.getStrErr().trim().length() <= 0) {

					// System.out.println("entered = 2");
					// System.out.println("entered(dis) = " +
					// vo.getOpd_discount()[tempChkVal]);

					appId = pKeyDao.getStrPrimrayKeyValue();
					appDao.setStrApprovalId(appId);
					appDao.setStrApprovalSlNo(Integer.toString(appSlNo));
					appDao.setStrReceiptType(strHblReqType);
					appDao.setStrBServiceId(strSblBservId);
					appDao.setStrChargeTypeId(strSbChgTypId);
					appDao.setStrEmpNo(vo.getStrRmk());
					appDao.setStrUserLevel(vo.getStrUserLevel());
					// System.out.println("entered = 3");

					// add with prev discount is set to true
					if (vo.getStrhiddTrfFADis().equals("1")) {
						totDisUnit = Double.parseDouble(vo
								.getStrIpdBillCurrDis())
								+ Double.parseDouble(vo.getLstDisService()[0]);
						appDao.setStrDiscountUnit(String.valueOf(totDisUnit));
					} else {
						totDisUnit = Double.parseDouble(vo
								.getStrIpdBillCurrDis());
						appDao.setStrDiscountUnit(String.valueOf(totDisUnit));
					}

					// System.out.println("entered = 3.1");
					appDao.setStrRemarks(vo.getStrDrt());
					// System.out.println("entered = 3.2");
					appDao.setStrDiscountType(vo.getStrIpdBilldiscountType());
					// System.out.println("entered = 3.3");
					/*
					 * appDao .setStrPrevDiscountUnit(vo
					 * .getLstDisService()[i]);
					 */
					appDao.setStrApprovalType("2");
					appDao.setStrStatus("1");
					/*
					 * appDao.setStrPrevDiscountType(vo
					 * .getStrPrevDisType()[i]);
					 */
					appDao.setStrSeatId(vo.getStrSeatId());
					appDao.setStrEntryDate(vo.getStrCtDate());
					appDao.setStrIsValid("1");
					appDao.setStrHospitalCode(vo.getStrHospitalCode());
					// Execute in batch
					appDao.insert(dao);
					// System.out.println("entered = 4");
					/*-------------Update Start in else loop--------------*/
					billreqtrfDao.setStrTariffId("0");
					billreqtrfDao.setStrReqNo(strHbReqNo);
					billreqtrfDao.setStrAppId(appId);
					billreqtrfDao.setStrDiscountType(vo
							.getStrIpdBilldiscountType());
					billreqtrfDao
							.setStrDiscountUnit(String.valueOf(totDisUnit));
					billreqtrfDao.setStrHospitalCode(vo.getStrHospitalCode());
					billreqtrfDao.update(dao);
					// System.out.println("entered = 5");

				} else {
					appFlag = 1;
					throw new Exception(pKeyDao.getStrErr());
				}
			} else {
				// in case of re-approval
				try {
					callingFunctionAppSlNo1(vo, dao);
				} catch (Exception e) {
					if (vo.getStrMsgType().equals("1")) {
						throw new Exception(
								"Exception in Calling of function callingFunctionAppSlNo1(vo)"
										+ vo.getStrMsgString());
					}
				}

				appDao.setStrApprovalId(vo.getApprovalId()[0]);
				appDao.setStrApprovalSlNo(vo.getStrApprovalSlNo());
				appDao.setStrReceiptType(strHblReqType);
				appDao.setStrBServiceId(strSblBservId);
				appDao.setStrChargeTypeId(strSbChgTypId);
				appDao.setStrEmpNo(vo.getStrRmk());
				appDao.setStrUserLevel(vo.getStrUserLevel());

				// add with prev discount is set to true
				if (vo.getStrhiddTrfFADis().equals("1")) {
					totDisUnit = Double.parseDouble(vo.getStrIpdBillCurrDis())
							+ Double.parseDouble(vo.getLstDisService()[0]);
					appDao.setStrDiscountUnit(String.valueOf(totDisUnit));
				} else {
					totDisUnit = Double.parseDouble(vo.getStrIpdBillCurrDis());
					appDao.setStrDiscountUnit(String.valueOf(totDisUnit));
				}

				// appDao.setStrDiscountUnit(vo.getOpd_discount()[tempChkVal]);
				appDao.setStrRemarks(vo.getStrDrt());
				appDao.setStrDiscountType(vo.getStrIpdBilldiscountType());
				appDao.setStrPrevDiscountUnit(vo.getLstDisService()[0]);
				appDao.setStrApprovalType("2");
				appDao.setStrStatus("1");
				appDao.setStrPrevDiscountType(vo.getStrPrevDisType()[0]);
				appDao.setStrSeatId(vo.getStrSeatId());
				appDao.setStrEntryDate(vo.getStrCtDate());
				appDao.setStrIsValid("1");
				appDao.setStrHospitalCode(vo.getStrHospitalCode());
				// Execute in batch
				appDao.insert(dao);
				/*-------------Update Start in else loop--------------*/
				billreqtrfDao.setStrTariffId("0");
				billreqtrfDao.setStrReqNo(strHbReqNo);
				billreqtrfDao.setStrAppId(vo.getApprovalId()[0]);
				billreqtrfDao
						.setStrDiscountType(vo.getStrIpdBilldiscountType());
				billreqtrfDao.setStrDiscountUnit(String.valueOf(totDisUnit));
				billreqtrfDao.setStrHospitalCode(vo.getStrHospitalCode());
				billreqtrfDao.update(dao);
			}

			synchronized (dao) {
				dao.fire(); // Here we Execute in Batch
				retVal = true; // O.Value

			}

		} catch (Exception e) {
			retVal = false;
			// System.out.println("Error in FA--->>>" + e.getMessage());
			vo.setStrPrimaryKeyMsg(e.getMessage());
			vo.setStrMsgType("1");

			try {

				if (appFlag == 1) {

					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(appId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
					pKeyLogDao.setStrError(vo.getStrPrimaryKeyMsg());
					pKeyLogDao.setStrSeatid(vo.getStrSeatId());
					pKeyLogDao.insert(dao);
				}
			} catch (Exception e1) {
			}
			vo
					.setStrMsgString("DiscountApprovalTrans-->DiscountApprovalTransDAO.getInsertDataForFinalAdjustment() --> "
							+ e.getMessage());

			retVal = false;

		} finally {
			if (dao != null) {
				dao.free();
				dao = null;
			}
		}
		// System.out.println("O/P of DAO--->" + retVal);
		return retVal;

	}

	/**
	 * ****************************Insert Logic For
	 * Services(IPD/OPD)***********************************
	 */
	public static boolean getInsertDataProcForServices(DiscountApprovalTransVO vo) 
	{
		HisDAO dao = null;
		boolean retVal = false;
		String[] strHiddenVal = null;
		int appSlNo = 0, appFlag = 0;
		String appId = "";
		int tempChkVal = 0;
		double totDisUnit = 0.0;

		PrimaryKeyDAO pKeyDao = new PrimaryKeyDAO();
		PrimaryKeyLogDAO pKeyLogDao = new PrimaryKeyLogDAO();
		
		try 
		{
			dao = new HisDAO("billing","transactions.DAObilling.getInsertDataProcForServices()");
			strHiddenVal = vo.getStrHidValOnLineReqDis().split("\\^");
			String strHbReqNo = strHiddenVal[0];
			String strSbChgTypId = strHiddenVal[3];
			String strSblBservId = strHiddenVal[4];
			String strHblReqType = strHiddenVal[13];
			// String strReqTypBServName = strHiddenVal[15];

			// for (int i = 0; i < vo.getOpd_discount().length; i++)
			for (int i = 0; i < vo.getTrfDisApproval().length; i++) 
			{
				ApprovalDAO appDao = new ApprovalDAO();
				BillRequisitionTariffDAO billreqtrfDao = new BillRequisitionTariffDAO();
				tempChkVal = Integer.parseInt(vo.getTrfDisApproval()[i]); 
				// no earliaer discount given
				if (vo.getApprovalId()[tempChkVal].equals("0") || vo.getApprovalId()[i].equals("null")) 
				{
					pKeyDao.setStrKeyname("APPROVAL");
					pKeyDao.setStrBlockkey("1");
					pKeyDao.setStrHospCode(vo.getStrHospitalCode());
					pKeyDao.select(dao);
					appSlNo = 1;

					if (pKeyDao.getStrErr() != null && pKeyDao.getStrErr().trim().length() <= 0) 
					{
						// vo.getOpd_discount()[tempChkVal]);

						appId = pKeyDao.getStrPrimrayKeyValue();
						appDao.setStrApprovalId(appId);
						vo.setStrApproval_id(appId);
						appDao.setStrApprovalSlNo(Integer.toString(appSlNo));
						appDao.setStrReceiptType(strHblReqType);
						appDao.setStrBServiceId(strSblBservId);
						appDao.setStrChargeTypeId(strSbChgTypId);
						appDao.setStrEmpNo(vo.getStrRmk());
						appDao.setStrUserLevel(vo.getStrUserLevel());
						
						// add with prev discount is set to true
						if (vo.getStrhiddTrfAddDis()[tempChkVal].equals("1")) 
						{
							totDisUnit = Double.parseDouble(vo.getOpd_discount()[tempChkVal])+ Double.parseDouble(vo.getLstDisService()[tempChkVal]);
							appDao.setStrDiscountUnit(String.valueOf(totDisUnit));
						} 
						else 
						{
							totDisUnit = Double.parseDouble(vo.getOpd_discount()[tempChkVal]);
							appDao.setStrDiscountUnit(String.valueOf(totDisUnit));
						}

						appDao.setStrRemarks(vo.getStrDrt());
						appDao.setStrDiscountType(vo.getOpd_discountType()[i]);
						/*
						 * appDao .setStrPrevDiscountUnit(vo
						 * .getLstDisService()[i]);
						 */
						appDao.setStrApprovalType("2");
						appDao.setStrStatus("1");
						/*
						 * appDao.setStrPrevDiscountType(vo
						 * .getStrPrevDisType()[i]);
						 */
						appDao.setStrSeatId(vo.getStrSeatId());
						appDao.setStrEntryDate(vo.getStrCtDate());
						appDao.setStrIsValid("1");
						appDao.setStrHospitalCode(vo.getStrHospitalCode());
						// Execute in batch
						appDao.insert(dao);
						/*-------------Update Start in else loop--------------*/
						billreqtrfDao.setStrTariffId(vo.getStrTariffIdInsert()[tempChkVal]);
						billreqtrfDao.setStrReqNo(strHbReqNo);
						billreqtrfDao.setStrAppId(appId);
						billreqtrfDao.setStrDiscountType(vo.getOpd_discountType()[i]);
						billreqtrfDao.setStrDiscountUnit(String.valueOf(totDisUnit));
						billreqtrfDao.setStrHospitalCode(vo.getStrHospitalCode());
						billreqtrfDao.update(dao);
					} 
					else 
					{
						appFlag = 1;
						throw new Exception(pKeyDao.getStrErr());
					}
				} 
				else 
				{
					// in case of re-approval
					try 
					{
						callingFunctionAppSlNo(vo, tempChkVal, dao);
					} 
					catch (Exception e) 
					{
						if (vo.getStrMsgType().equals("1")) 
						{
							throw new Exception("Exception in Calling of function callingFunctionAppSlNo(vo)"+ vo.getStrMsgString());
						}
					}

					appDao.setStrApprovalId(vo.getApprovalId()[tempChkVal]);
					vo.setStrApproval_id(vo.getApprovalId()[tempChkVal]+""+vo.getStrApprovalSlNo());
					appDao.setStrApprovalSlNo(vo.getStrApprovalSlNo());
					appDao.setStrReceiptType(strHblReqType);
					appDao.setStrBServiceId(strSblBservId);
					appDao.setStrChargeTypeId(strSbChgTypId);
					appDao.setStrEmpNo(vo.getStrRmk());
					appDao.setStrUserLevel(vo.getStrUserLevel());

					if (vo.getStrhiddTrfAddDis()[tempChkVal].equals("1")) 
					{
						totDisUnit = Double.parseDouble(vo.getOpd_discount()[tempChkVal])+ Double.parseDouble(vo.getLstDisService()[tempChkVal]);
						appDao.setStrDiscountUnit(String.valueOf(totDisUnit));
					} 
					else 
					{
						totDisUnit = Double.parseDouble(vo.getOpd_discount()[tempChkVal]);
						appDao.setStrDiscountUnit(String.valueOf(totDisUnit));
					}
					// appDao.setStrDiscountUnit(vo.getOpd_discount()[tempChkVal]);
					appDao.setStrRemarks(vo.getStrDrt());
					appDao.setStrDiscountType(vo.getOpd_discountType()[i]);
					appDao.setStrPrevDiscountUnit(vo.getLstDisService()[tempChkVal]);
					appDao.setStrApprovalType("2");
					appDao.setStrStatus("1");
					appDao.setStrPrevDiscountType(vo.getStrPrevDisType()[tempChkVal]);
					appDao.setStrSeatId(vo.getStrSeatId());
					appDao.setStrEntryDate(vo.getStrCtDate());
					appDao.setStrIsValid("1");
					appDao.setStrHospitalCode(vo.getStrHospitalCode());

					appDao.insert(dao);
					/*-------------Update Start in else loop--------------*/
					billreqtrfDao.setStrTariffId(vo.getStrTariffIdInsert()[tempChkVal]);
					billreqtrfDao.setStrReqNo(strHbReqNo);
					billreqtrfDao.setStrAppId(vo.getApprovalId()[tempChkVal]);
					billreqtrfDao.setStrDiscountType(vo.getOpd_discountType()[i]);
					billreqtrfDao.setStrDiscountUnit(String.valueOf(totDisUnit));
					billreqtrfDao.setStrHospitalCode(vo.getStrHospitalCode());
					billreqtrfDao.update(dao);
				}
			}

			if (vo.getTrfDisApproval().length > 0) 
			{
				synchronized (dao) 
				{
					dao.fire();
					retVal = true;
				}
			} 
			else 
			{
				retVal = false;
			}
		} 
		catch (Exception e) 
		{
			retVal = false;
			vo.setStrMsgString("DiscountApprovalTransDAO.getInsertDataProc() --> "+ vo.getStrMsgString());
			vo.setStrMsgType("1");
			vo.setStrPrimaryKeyMsg(e.getMessage());
			try 
			{
				if (appFlag == 1) 
				{
					pKeyLogDao.setStrKeyname("APPROVAL");
					pKeyLogDao.setStrStartkey(appId);
					pKeyLogDao.setStrBlockkey("1");
					pKeyLogDao.setStrHospCode(vo.getStrHospitalCode());
					
					pKeyLogDao.setStrError(vo.getStrPrimaryKeyMsg());
					pKeyLogDao.setStrSeatid(vo.getStrSeatId());
					pKeyLogDao.insert(dao);
				}
			} 
			catch (Exception e1) 
			{
				vo.setStrMsgString("DiscountApprovalTransDAO.getInsertDataProc() --> "+ e1.getMessage());
				vo.setStrMsgType("1");
			}
			retVal = false;
		} 
		finally 
		{
			if (dao != null) 
			{
				dao.free();
				dao = null;
			}
		}
		return retVal;
	}	
}
