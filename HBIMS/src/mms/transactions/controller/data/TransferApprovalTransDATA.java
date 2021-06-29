package mms.transactions.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.transactions.bo.TransferApprovalTransBO;
import mms.transactions.controller.fb.TransferApprovalTransFB;
import mms.transactions.controller.hlp.TransferApprovalTransHLP;
import mms.transactions.vo.TransferApprovalTransVO;

public class TransferApprovalTransDATA {

	public static void initOrderGenerate(TransferApprovalTransFB formBean) {

		TransferApprovalTransBO bo = null;
		TransferApprovalTransVO vo = null;

		HisUtil util = null;

		String strmsgText = "";

		try {
			bo = new TransferApprovalTransBO();
			vo = new TransferApprovalTransVO();
			util = new HisUtil("mms", "TransferApprovalTransDATA");

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());

			bo.initOrderGenerate(vo);

			if (vo.getWsDwhList() != null && vo.getWsDwhList().size() > 0) {

				formBean.setStrDwhNames(util.getOptionValue(vo.getWsDwhList(),
						"0", "0^All", false));

			} else {

				formBean.setStrDwhNames("<option value='0'>All</option> ");

			}

			formBean.setStrCtDate(util.getDSDate("dd-Mon-yyyy HH24:MI"));

			formBean.setStrDemandRequestDetails(TransferApprovalTransHLP
					.getDemandRequestDetails(vo.getWsDemandRequestDetails()));

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

		} catch (Exception e) {

			strmsgText = "TransferApprovalTransDATA.initOrderGenerate(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferApprovalTransDATA->initOrderGenerate()",
					strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			util = null;

		}

	}

	public static void getDemandRequestDetails(HttpServletRequest request,
			HttpServletResponse response) {

		String strmsgText = "";
		TransferApprovalTransBO bo = null;
		TransferApprovalTransVO vo = null;
		try {

			bo = new TransferApprovalTransBO();
			vo = new TransferApprovalTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();

			String strStoreId = (String) request.getParameter("strStoreId");

			vo.setStrStoreId(strStoreId);

			vo.setStrHospitalCode(strHospitalCode);

			bo.getDemandRequestDetails(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			String strDemandRequestDetails = TransferApprovalTransHLP
					.getDemandRequestDetails(vo.getWsDemandRequestDetails());

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strDemandRequestDetails);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "TransferApprovalTransDATA.getDemandRequestDetails(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferApprovalTransDATA->getDemandRequestDetails()",
					strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;

		}
	}

	public static void getTransferingDetails(HttpServletRequest request,
			HttpServletResponse response) {

		String strmsgText = "";
		TransferApprovalTransBO bo = null;
		TransferApprovalTransVO vo = null;
		try {

			bo = new TransferApprovalTransBO();
			vo = new TransferApprovalTransVO();

			String strHospitalCode = request.getSession()
					.getAttribute("HOSPITAL_CODE").toString();

			String strItemBrandId = (String) request
					.getParameter("strItemBrandId");

			vo.setStrItemBrandId(strItemBrandId);

			vo.setStrHospitalCode(strHospitalCode);

			bo.getTransferingDetails(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			String strTransferingDetails = TransferApprovalTransHLP
					.getTransferingDetails(vo.getWsTransferingDetails());

			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(strTransferingDetails);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {

			strmsgText = "TransferApprovalTransDATA.getTransferingDetails(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferApprovalTransDATA->getTransferingDetails()",
					strmsgText);
			try {

				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				e.printStackTrace();
			}

			eObj = null;
		} finally {
			vo = null;
			bo = null;

		}
	}

	public static void insertOrderGenerate(TransferApprovalTransFB formBean) {

		TransferApprovalTransBO bo = null;
		TransferApprovalTransVO vo = null;

		String strmsgText = "";

		try {
			bo = new TransferApprovalTransBO();
			vo = new TransferApprovalTransVO();

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());

			String[] strDemandRequestDetails = formBean.getStrDemandRequest()
					.replace("^", "#").split("#");
			vo.setStrStoreId(strDemandRequestDetails[0]);
			vo.setStrItemBrandId(strDemandRequestDetails[1]);
			vo.setStrRequestNo(strDemandRequestDetails[3]);
			vo.setStrRemarks(formBean.getStrRemarks());

			String[] strTransferStoreIds = null;
			String[] strTransferRequestNos = null;

			if (formBean.getStrTransferingDtls() != null
					&& formBean.getStrTransferingDtls().length > 0) {

				int len = formBean.getStrTransferingDtls().length;

				strTransferStoreIds = new String[len];
				strTransferRequestNos = new String[len];

				for (int i = 0; i < len; i++) {

					strTransferStoreIds[i] = formBean.getStrTransferingDtls()[i]
							.replace("^", "#").split("#")[0];
					strTransferRequestNos[i] = formBean.getStrTransferingDtls()[i]
							.replace("^", "#").split("#")[3];

				}

				vo.setStrTransferOrderQtys(formBean.getStrTransferOrderQty());
				vo.setStrTransferStoreIds(strTransferStoreIds);
				vo.setStrTransferRequestNos(strTransferRequestNos);

			}

			bo.insertOrderGenerate(vo);

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "TransferApprovalTransDATA.insertOrderGenerate(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferApprovalTransDATA->insertOrderGenerate()",
					strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	public static void initOrderModify(TransferApprovalTransFB formBean) {

		TransferApprovalTransBO bo = null;
		TransferApprovalTransVO vo = null;

		String strmsgText = "";

		try {
			bo = new TransferApprovalTransBO();
			vo = new TransferApprovalTransVO();

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrOrderNo(formBean.getStrOrderNo());

			bo.initOrderModify(vo);

			formBean.setStrOrderDate(vo.getStrOrderDate());
			formBean.setStrDemandingDDW(vo.getStrDemandingDDW());
			formBean.setStrRequestNo(vo.getStrRequestNo());
			formBean.setStrDemandNo(vo.getStrRequestNo());
			formBean.setStrTransRequestNo(vo.getStrTransRequestNo());
			formBean.setStrDrugName(vo.getStrDrugName());
			formBean.setStrOrderQty(vo.getStrOrderQty());
			formBean.setStrDemandDate(vo.getStrDemandDate());
			formBean.setStrTransStoreId(vo.getStrTransStoreId());
			formBean.setStrItemBrandId(vo.getStrItemBrandId());
			formBean.setStrDemandQty(vo.getStrDemandQty());
			formBean.setStrOrderedQty(vo.getStrOrderedQty());
			formBean.setStrBalanceQty(vo.getStrBalanceQty());
			formBean.setStrBalanceQtyBaseValue(vo.getStrBalanceQtyBaseValue());
			formBean.setStrStoreId(vo.getStrStoreId());

			formBean.setStrTransferingDetails(TransferApprovalTransHLP
					.getTransferingDetailsModify(vo.getWsTransferingDetails()));

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "TransferApprovalTransDATA.initOrderGenerate(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferApprovalTransDATA->initOrderGenerate()",
					strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}
	
	public static void insertOrderModify(TransferApprovalTransFB formBean) {

		TransferApprovalTransBO bo = null;
		TransferApprovalTransVO vo = null;

		String strmsgText = "";

		try {
			bo = new TransferApprovalTransBO();
			vo = new TransferApprovalTransVO();

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());

			vo.setStrOrderNo(formBean.getStrOrderNo());
			vo.setStrDemandNo(formBean.getStrDemandNo());
			vo.setStrStoreId(formBean.getStrStoreId());
			vo.setStrItemBrandId(formBean.getStrItemBrandId());
			vo.setStrRequestNo(formBean.getStrRequestNo());
			vo.setStrRemarks(formBean.getStrRemarks());

			String[] strTransferStoreIds = null;
			String[] strTransferRequestNos = null;

			if (formBean.getStrTransferingDtls() != null
					&& formBean.getStrTransferingDtls().length > 0) {

				int len = formBean.getStrTransferingDtls().length;

				strTransferStoreIds = new String[len];
				strTransferRequestNos = new String[len];

				for (int i = 0; i < len; i++) {

					strTransferStoreIds[i] = formBean.getStrTransferingDtls()[i]
							.replace("^", "#").split("#")[0];
					strTransferRequestNos[i] = formBean.getStrTransferingDtls()[i]
							.replace("^", "#").split("#")[3];

				}

				vo.setStrTransferOrderQtys(formBean.getStrTransferOrderQty());
				vo.setStrTransferStoreIds(strTransferStoreIds);
				vo.setStrTransferRequestNos(strTransferRequestNos);

			}

			bo.insertOrderModify(vo);

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "TransferApprovalTransDATA.insertOrderModify(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferApprovalTransDATA->insertOrderModify()",
					strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	public static void demandReject(TransferApprovalTransFB formBean) {

		TransferApprovalTransBO bo = null;
		TransferApprovalTransVO vo = null;

		String strmsgText = "";

		try {
			bo = new TransferApprovalTransBO();
			vo = new TransferApprovalTransVO();

			String strChkValues = formBean.chk[0];

			String[] strTemp = strChkValues.replace("@", "#").split("#");

			vo.setStrStoreId(strTemp[0]);
			vo.setStrRequestNo(strTemp[1]);
			vo.setStrRemarks(formBean.getComboValue());

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());

			bo.demandReject(vo);

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

		} catch (Exception e) {

			strmsgText = "TransferApprovalTransDATA.demandReject(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferApprovalTransDATA->demandReject()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	public static void transferReject(TransferApprovalTransFB formBean) {

		TransferApprovalTransBO bo = null;
		TransferApprovalTransVO vo = null;

		String strmsgText = "";

		try {
			bo = new TransferApprovalTransBO();
			vo = new TransferApprovalTransVO();

			String strChkValues = formBean.chk[0];

			String[] strTemp = strChkValues.replace("@", "#").split("#");

			vo.setStrStoreId(strTemp[0]);
			vo.setStrRequestNo(strTemp[1]);
			vo.setStrRemarks(formBean.getComboValue());

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());

			bo.transferReject(vo);

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

		} catch (Exception e) {

			strmsgText = "TransferApprovalTransDATA.transferReject(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferApprovalTransDATA->transferReject()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	public static void transferForcefullyClose(TransferApprovalTransFB formBean) {

		TransferApprovalTransBO bo = null;
		TransferApprovalTransVO vo = null;

		String strmsgText = "";

		try {
			bo = new TransferApprovalTransBO();
			vo = new TransferApprovalTransVO();

			String strChkValues = formBean.chk[0];

			String[] strTemp = strChkValues.replace("@", "#").split("#");

			vo.setStrStoreId(strTemp[0]);
			vo.setStrRequestNo(strTemp[1]);
			vo.setStrRemarks(formBean.getComboValue());

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());

			bo.transferForcefullyClose(vo);

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

		} catch (Exception e) {

			strmsgText = "TransferApprovalTransDATA.transferForcefullyClose(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferApprovalTransDATA->transferForcefullyClose()",
					strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	public static void cancelOrder(TransferApprovalTransFB formBean) {

		TransferApprovalTransBO bo = null;
		TransferApprovalTransVO vo = null;

		String strmsgText = "";

		try {
			bo = new TransferApprovalTransBO();
			vo = new TransferApprovalTransVO();

			String strChkValues = formBean.chk[0];

			String strOrderNo = strChkValues.replace("@", "#").split("#")[0];

			vo.setStrOrderNo(strOrderNo);
			vo.setStrRemarks(formBean.getComboValue());

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());

			bo.cancelOrder(vo);

			if (vo.getStrMsgType().equals("1")) {

				throw new Exception(vo.getStrMsgString());

			}

		} catch (Exception e) {

			strmsgText = "TransferApprovalTransDATA.cancelOrder(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("mms",
					"TransferApprovalTransDATA->cancelOrder()", strmsgText);
			formBean.setStrErr("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

}
