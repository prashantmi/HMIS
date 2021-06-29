package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ClientApprovalDetailsTransCNT extends GenericController {

	public ClientApprovalDetailsTransCNT() {
		super(new ClientApprovalDetailsTransUTL(),
				"/transactions/ClientApprovalDetailsTransCNT");

	}

	/**
	 * forwards control to the Client Approval Details Approval Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward APPROVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		String strTarget = "approval";
		ClientApprovalDetailsTransFB formBean = (ClientApprovalDetailsTransFB) form;
		ClientApprovalDetailsTransDATA.initClientApprovalDetails(formBean,
				request);
		return mapping.findForward(strTarget);

	}

	/**
	 * forwards control to the ClientApprovalTrans INSERT Approval Page Insert
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	// //used
	public ActionForward APPROVALINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);

		String strTarget = "approval";
		// boolean retValue = false ;
		ClientApprovalDetailsTransFB formBean = (ClientApprovalDetailsTransFB) form;
		boolean bRetVal = ClientApprovalDetailsTransDATA.InsertRecordApproval(
				request, response, formBean); // defined in DATA class
		if (bRetVal) {
			return this.LIST(mapping, form, request, response);

		} else {

			return mapping.findForward(strTarget);
		}
	}

	/**
	 * forwards control to the Client Approval Details Approval Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);

		String strTarget = "approval";

		ClientApprovalDetailsTransFB formBean = (ClientApprovalDetailsTransFB) form;

		ClientApprovalDetailsTransDATA.setPatientDetails(formBean, request);

		return mapping.findForward(strTarget);
	}

	/**
	 * This Method is Used to get Tariff details
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	/*
	 * public ActionForward TARIFFDTLS(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * HisException, SQLException {
	 * 
	 * ClientApprovalDetailsTransDATA.initTariffDtls(request, response);
	 * 
	 * return null;
	 *  }
	 */

	/**
	 * This Method is Used to get Tariff List
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	/*
	 * public ActionForward TARIFFLIST(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * HisException, SQLException {
	 * 
	 * ClientApprovalDetailsTransDATA.initTariffList(request, response);
	 * 
	 * return null;
	 *  }
	 */
	/**
	 * This Method is Used to return Ajax response
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward UNITVAL22(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		ClientApprovalDetailsTransFB formBean = (ClientApprovalDetailsTransFB) form;
		ClientApprovalDetailsTransDATA.UNITVAL22(request, response, formBean);

		return null;

	}

	/**
	 * forwards control to the Client Approval Details View Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward VIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String strTarget = "view";
		String chk2 = request.getParameter("chk");
		ClientApprovalDetailsTransFB formBean = (ClientApprovalDetailsTransFB) form;
		formBean.setStrChk(chk2);

		ClientApprovalDetailsTransDATA.initViewDetails(request, formBean);
		ClientApprovalDetailsTransDATA.setPatientDetails2(formBean, request);

		return mapping.findForward(strTarget);
	}

	/**
	 * forwards control to the Client Approval Details Re-Approval Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward REAPPROVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String strTarget = "reapproval";

		String chk2 = request.getParameter("chk");

		ClientApprovalDetailsTransFB formBean = (ClientApprovalDetailsTransFB) form;
		formBean.setStrChk(chk2);

		if (request.getParameter("chk") == null) {
			return this.LIST(mapping, form, request, response);
		} else {
			ClientApprovalDetailsTransDATA
					.setPatientDetails2(formBean, request);
			return mapping.findForward(strTarget);
		}
	}

	/**
	 * forwards control to the ClientApprovalTrans RE-APPROVALINSERT
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward REAPPROVALINSERT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, SQLException {
		validateToken(request, response);

		String strTarget = "reapproval";
		ClientApprovalDetailsTransFB formBean = (ClientApprovalDetailsTransFB) form;

		boolean bRetVal = ClientApprovalDetailsTransDATA
				.InsertRecordReApproval(request, response, formBean); // defined
		// in
		// DATA
		// class
		if (bRetVal) {
			return this.LIST(mapping, form, request, response);
		} else {
			return mapping.findForward(strTarget);
		}

	}

	/**
	 * forwards control to the ClientApprovalTrans CLOSEINSERT
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward CLOSE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		// boolean retValue = false ;
		ClientApprovalDetailsTransFB formBean = (ClientApprovalDetailsTransFB) form;
		boolean bRetVal = ClientApprovalDetailsTransDATA.InsertForClose(
				request, response, formBean); // defined in DATA class
		if (bRetVal) {
			return this.LIST(mapping, form, request, response);
		} else {
			return this.LIST(mapping, form, request, response);
		}
	}

	/**
	 * forwards control to the Client Approval Details ReFund Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	/*
	 * public ActionForward REFUND(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * HisException, SQLException {
	 * 
	 * String strTarget = "refund";
	 * 
	 * ClientApprovalDetailsTransFB formBean = (ClientApprovalDetailsTransFB)
	 * form; formBean.setStrHospitalCode(request.getSession().getAttribute(
	 * "HOSPITAL_CODE").toString());
	 * ClientApprovalDetailsTransDATA.initReFundDetails(request, formBean);
	 * 
	 * return mapping.findForward(strTarget); }
	 */
	/**
	 * This method is used get the PaymentMode
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */

	/*
	 * public ActionForward PAYMENTMODE(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * HisException, SQLException {
	 * 
	 * ClientApprovalDetailsTransDATA.paymentMode(request, response);
	 * 
	 * return null;
	 *  }
	 */

	/**
	 * forwards control to the Client Approval Details Close Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	// public ActionForward CLOSE(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws HisException, SQLException {
	//
	// String strTarget = "close";
	//
	// ClientApprovalDetailsTransFB formBean = (ClientApprovalDetailsTransFB)
	// form;
	//
	// ClientApprovalDetailsTransDATA.initCloseDetails(request, formBean);
	//
	// return mapping.findForward(strTarget);
	// }
}
