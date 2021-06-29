package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;

import billing.BillConfigUtil;
import billing.PrintHLP;

public class RefundApprovalTransBSDATA {

	public static void getBillDtls(RefundApprovalTransFB formBean,HttpServletRequest request) 
	{
		String strmsgText = "";
		String strHospitalCode = "";
		String billDetails = null;
		String strPatientDtls = "";
		RefundApprovalTransVO vo = new RefundApprovalTransVO();
		RefundApprovalTransBO bo = new RefundApprovalTransBO();
		
		try 
		{
			strHospitalCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			
			//formBean.setStrIpdRefundPenaltyAmt(billConfig.getIpdRefundPenalty());
			//formBean.setStrOpdRefundPenaltyAmt(billConfig.getOpdRefundPenalty());
			//formBean.setStrEmergencyRefundPenaltyAmt(billConfig.getEmergencyRefundPenalty());
			
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrHospitalCode(strHospitalCode);
			formBean.setPrintMode(BillConfigUtil.PRINT_MODE.toString());//Laser Printing or DMP Printing
			bo.getBillDtls(vo);

			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			}
			/*
			 * formBean.setStrDisRsn(vo.getStrDisRsn());
			 * formBean.setStrDisBy(vo.getStrDisBy());
			 */
			else 
			{
				strPatientDtls = hisglobal.tools.hlp.PatientDtlHLPNew.patientDtl(vo.getStrCrNo(),true);
				
				if (strPatientDtls.trim().equals("")) 
				{
					formBean.setStrErrMsg("Invalid CR No.");
					formBean.setStrMsgType("1");
				}
				else
				{
					// Patient Bill Details HLP
					billDetails = RefundApprovalTransHLPNew.getBillDetails(vo);
					/*formBean.setStrBillNo(vo.getStrBillNo());
					formBean.setStrBillDate(vo.getStrBillDate());*/
					if (vo.getStrMsgType().equals("1")) 
					{
						throw new Exception(vo.getStrMsgString());
					}
					else 
					{
						formBean.setStrBill(billDetails);
						formBean.setStrPatientDtls(strPatientDtls);
					}
				}
			}
		} catch (Exception e) {
			strmsgText = "billing.transactions.RefundApprovalTransBSDATA.getBillDtls(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"Billing",
					"RefundApprovalTransaction->RefundApprovalTransBSDATA->getBillDtls()",
					strmsgText);
			
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			
			eObj = null;
		} finally {
			
			//billConfig = null;
			vo = null;
			bo = null;
		}

	}

	public static void getRsnRmk(RefundApprovalTransFB formBean) {
		String strmsgText = "";
		RefundApprovalTransVO vo = null;
		RefundApprovalTransBO bo = null;
		try {

			vo = new RefundApprovalTransVO();
			bo = new RefundApprovalTransBO();
			
			vo.setStrRefundMode(formBean.getStrRefundMode());
			
			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			
			bo.getRsnRmk(vo);
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}
			/*
			 * formBean.setStrDisBy(vo.getStrDisBy());
			 * formBean.setStrDisRsn(vo.getStrDisRsn());
			 */// COMMENT BY ANSHUL
			else {
				formBean.setStrRmk(vo.getStrRmk());
				formBean.setStrRsn(vo.getStrRsn());
			}
		} catch (Exception e) {
			strmsgText = "billing.transactions.RefundApprovalTransBSDATA.getRsnRmk(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"Billing",
					"RefundApprovalTransaction->RefundApprovalTransBSDATA->getRsnRmk()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");

			eObj = null;
		} finally {
		
				vo = null;
		
				bo = null;
			/*
			if (formBean != null)
				formBean = null;
				*/
		}
	}
	
	/**
	 * get tariff details
	 * @param formBean
	 * @param request
	 * @return
	 */
	public static String TariffDtl(RefundApprovalTransFB formBean,HttpServletRequest request) 
	{
		String strmsgText = "";
		String tmpBsId[] = null;
		String tariffDetails = null;
		String strPenalty = "";
		RefundApprovalTransVO vo = null;
		RefundApprovalTransBO bo = null;
		BillConfigUtil config = null;

		try 
		{
			vo = new RefundApprovalTransVO();
			bo = new RefundApprovalTransBO();
			config = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE").toString();
			vo.setStrHospitalCode(hosCode);
			String strValmode = (String) request.getParameter("modName");
			System.out.println("strValmode------------>"+strValmode);
			vo.setStrValmode(strValmode);
			tmpBsId = strValmode.replace("^", "#").split("#");
			vo.setStrHospServ(tmpBsId[1]);

			if (vo.getStrHospServ().equals("1")) 
			{
				strPenalty = config.getOpdRefundPenalty();
				vo.setStrPenalty(strPenalty);
			} 
			else if (vo.getStrHospServ().equals("2")) 
			{
				strPenalty = config.getIpdRefundPenalty();
				vo.setStrPenalty(strPenalty);
			} 
			else 
			{
				strPenalty = config.getEmergencyRefundPenalty();
				vo.setStrPenalty(strPenalty);
			}
			bo.setTariffValues(vo);
			if (vo.getStrMsgType().equals("1")) 
			{
				throw new Exception(vo.getStrMsgString());
			} 
			else 
			{
				tariffDetails = RefundApprovalTransHLPNew.getTariffDetails(vo);
				// tariffDetails = null;
				if (vo.getStrMsgType().equals("1") || tariffDetails == null) 
				{
					throw new Exception(vo.getStrMsgString());
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = e.getMessage();
			HisException eObj = new HisException("Billing","RefundApprovalTransBSDATA->TariffDtl()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} 
		finally 
		{		
			vo = null;		
			bo = null;
		}
		if (strmsgText.equals("") || tariffDetails != null) 
		{
			return tariffDetails;
		} 
		else 
		{
			return formBean.getStrErrMsg();
		}
	}

	public static String popUpShw(HttpServletRequest request) {
		String res = "";
		String strmsgText = "";
		String strValmode = "";
		RefundApprovalTransVO vo = null;
		try {
			vo = new RefundApprovalTransVO();
			strValmode = (String) request.getParameter("modName");
			vo.setStrValmode(strValmode);
			res = RefundApprovalTransHLPNew.popUpShw(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString()); 
			}
		} catch (Exception e) {
			strmsgText = "billing.transactions.RefundApprovalTransBSDATA.popUpShw(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"Billing",
					"RefundApprovalTransaction->RefundApprovalTransBSDATA->popUpShw()",
					strmsgText);
			strmsgText = "ERROR###Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ";

			eObj = null;
		} finally {
			
			vo = null;
		}

		if (strmsgText.equals("")) {
			return res;
		} else {
			return strmsgText;
		}
	}

	public static boolean insertData(ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String strmsgText = null;
		boolean fretValue = true;
		String strHospitalCode = request.getSession().getAttribute(
				"HOSPITAL_CODE").toString();
		String strSeatId = request.getSession().getAttribute("SEATID")
				.toString();
		String strUserLevel = "1";
		

		if(request.getSession().getAttribute("USER_LEVEL") != null){
			
			strUserLevel = request.getSession().getAttribute("USER_LEVEL").toString();
			
		}
		
		// String strHospitalCode ="108";
		// String strSeatId = "6";

		String strChkValues = "";
		RefundApprovalTransBO bo = null;
		RefundApprovalTransVO vo = null;
		RefundApprovalTransFB formBean = null;

		try {
			bo = new RefundApprovalTransBO();
			vo = new RefundApprovalTransVO();
			formBean = (RefundApprovalTransFB) form;

			strChkValues = request.getParameter("chkValue"); //radio button value
			//System.out.println("strChkValues = " + strChkValues);
			
			
			vo.setStrRefundMode(formBean.getStrRefundMode());
			vo.setStrCrNo(formBean.getStrCrNo());
			vo.setStrBillNo(formBean.getStrBillNo());
			vo.setRefundType(formBean.getRefundType());
			vo.setTrf_penalty(formBean.getTrf_penalty());
			vo.setRefundQty(formBean.getRefundQty());

			vo.setStrUnitId(formBean.getStrUnitId());
			vo.setRefundAmt(formBean.getRefundAmt());

			vo.setStrChkValues(strChkValues);

			vo.setStrHospitalCode(strHospitalCode);
			vo.setStrSeatId(strSeatId);
			vo.setStrUserLevel(strUserLevel);

			vo.setStrRefBy(formBean.getStrRefBy());
			vo.setStrOtherReason(formBean.getStrOtherReason());
			vo.setStrRefundMode(formBean.getStrRefundMode());
			vo.setChk(formBean.getChk());	//tariff value
			vo.setChkHidd(formBean.getChkHidd());	//which checkbox is selected
			
			int length = vo.getChkHidd().length;
			vo.setClNo(formBean.getClNo());
			vo.setClDate(formBean.getClDate());
			vo.setClPath(formBean.getClPath());
			vo.setClientNo(formBean.getClientNo());
			vo.setAmtByClient(formBean.getAmtByClient());
			vo.setAmtByPat(formBean.getAmtByPat());
			vo.setStrProcessFeePenalty(formBean.getStrProcessFeePenalty());
			bo.insertData(vo);
			formBean.setStrReqNo(vo.getStrReqNo());

			if (vo.getStrMsgType().equals("1")) {
				fretValue = false;
				formBean.setStrCrNo("");
				formBean
						.setStrErrMsg("Data has not been inserted into database due to some ");
				throw new Exception(vo.getStrMsgString());
			} else {
				fretValue = true;
				//System.out.println("formBean.getTrf_grossRefund()"+formBean.getTrf_grossRefund());
				PrintHLP.OP_REF_REQ(formBean.getStrReqNo(), "10",formBean.getStrHospitalCode(),  request,formBean.getStrBillNo().replace("^","#").split("#")[0]+"/"+formBean.getStrBillNo().replace("^","#").split("#")[8],formBean.getStrBillDate(),formBean.getStrOtherReason(),formBean.getTrf_grossRefund());
	
				String fileName = HisUtil.getParameterFromHisPathXML("TEMP_PATH")+ "/"+ "Billing"+ request.getSession().getAttribute("SEATID").toString() + ".dat";
				formBean.setFilePath(fileName);
				request.setAttribute("filePath", fileName);
				formBean.setIsOpenPopUp("1");
				 
				if(vo.getStrRefundMode().equals("0")){
					formBean.setStrMsg("Refund has been Approved successfully");
				}else{
					formBean.setStrMsg("Refund has been Requested successfully");
				}
				formBean.setStrMsgType("0");
				formBean.setStrCrNo("");
			}

		} catch (Exception e) {
			fretValue = false;
			strmsgText = "billing.transactions.RefundApprovalTransBSDATA.insertData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("Billing",
					"RefundApprovalTransBSDATA->insertData()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "], Contact System Administrator! ");
			formBean.setStrMsgType("1");
			eObj = null;
		} finally {

			bo = null;
			vo = null;

		}
		return fretValue;
	}

}
