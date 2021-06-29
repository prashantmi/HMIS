package mms.transactions.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.transactions.controller.data.TransferApprovalTransDATA;
import mms.transactions.controller.fb.TransferApprovalTransFB;
import mms.transactions.controller.utl.TransferApprovalTransUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TransferApprovalTransCNT extends GenericController {

	public TransferApprovalTransCNT() {
		super(new TransferApprovalTransUTL(), "/transactions/TransferApprovalTransCNT");
	}

	public ActionForward ORDER_GENERATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		TransferApprovalTransFB formBean = (TransferApprovalTransFB) form;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

		TransferApprovalTransDATA.initOrderGenerate(formBean);

		return mapping.findForward("orderGenerate");
	}

	public ActionForward GET_DEMAND_REQ_DTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException {

		TransferApprovalTransDATA.getDemandRequestDetails(request, response);

		return null;
	}

	public ActionForward ORDER_GENERATE_INSERT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException {

		TransferApprovalTransFB formBean = (TransferApprovalTransFB) form;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

		TransferApprovalTransDATA.insertOrderGenerate(formBean);

		return this.ORDER_GENERATE(mapping, formBean, request, response);
	}

	public ActionForward GET_TRANS_DTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		TransferApprovalTransDATA.getTransferingDetails(request, response);

		return null;
	}

	public ActionForward ORDER_MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		TransferApprovalTransFB formBean = (TransferApprovalTransFB) form;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

		String chk = formBean.getChk()[0];

		String strOrderNo = chk.replace("@", "#").split("#")[0];

		formBean.setStrOrderNo(strOrderNo);

		TransferApprovalTransDATA.initOrderModify(formBean);

		return mapping.findForward("orderModify");
	}

	
	

	public ActionForward ORDER_MODIFY_INSERT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException {

		TransferApprovalTransFB formBean = (TransferApprovalTransFB) form;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

		TransferApprovalTransDATA.insertOrderModify(formBean);

		return this.LIST(mapping, formBean, request, response);
	}
	
	public ActionForward ORDER_CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		TransferApprovalTransFB formBean = (TransferApprovalTransFB) form;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

		TransferApprovalTransDATA.cancelOrder(formBean);

		return this.LIST(mapping, form, request, response);
	}

	public ActionForward ORDER_VIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		TransferApprovalTransFB fb = (TransferApprovalTransFB) form;

		String chk = fb.getChk()[0];

		String strOrderNo = chk.replace("@", "#").split("#")[0];

		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/mms/transactions/TransferRequestTransCNT.cnt?hmode=ORDER_VIEW&strOrderNo="
				+ strOrderNo);

		acFwd.setContextRelative(true);
		return acFwd;
	}

	public ActionForward TRANSFER_REJECT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException {

		TransferApprovalTransFB formBean = (TransferApprovalTransFB) form;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

		TransferApprovalTransDATA.transferReject(formBean);

		return this.LIST(mapping, form, request, response);
	}

	public ActionForward TRANSFER_FFCLOSE(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException {

		TransferApprovalTransFB formBean = (TransferApprovalTransFB) form;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

		TransferApprovalTransDATA.transferForcefullyClose(formBean);

		return this.LIST(mapping, form, request, response);
	}

	public ActionForward TRANSFER_VIEW(ActionMapping _mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		TransferApprovalTransFB fb = (TransferApprovalTransFB) form;

		String chk = fb.getChk()[0];

		String strRequestNo = chk.replace("@", "#").split("#")[1];
		String strStoreId = chk.replace("@", "#").split("#")[0];

		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/mms/transactions/TransferRequestTransCNT.cnt?hmode=VIEW&strRequestNo="
				+ strRequestNo + "&strStoreId=" + strStoreId);

		acFwd.setContextRelative(true);
		return acFwd;
	}

	public ActionForward DEMAND_REJECT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		TransferApprovalTransFB formBean = (TransferApprovalTransFB) form;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

		TransferApprovalTransDATA.demandReject(formBean);

		return this.LIST(mapping, form, request, response);
	}

	public ActionForward DEMAND_VIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		TransferApprovalTransFB fb = (TransferApprovalTransFB) form;

		String chk = fb.getChk()[0];

		String strStoreId = chk.replace("@", "#").split("#")[0];
		String strRequestNo = chk.replace("@", "#").split("#")[1];

		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/mms/transactions/TransferDemandReqTransCNT.cnt?hmode=VIEW&strRequestNo="
				+ strRequestNo + "&strStoreId=" + strStoreId);
		acFwd.setContextRelative(true);
		return acFwd;
	}

}
