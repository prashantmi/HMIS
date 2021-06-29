package billing.transactions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;

/**
 * @author Anshul Jindal This is Action file.It is used to transfer the control
 *         to DATA File.
 */
public class OnlineRequestCancellationTransCNT extends CSRFGardTokenAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);

		return this.init(mapping, form, request, response);
	}

	/**
	 * This method is used to forward the request on first jsp page to enter a
	 * CR No.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);

		String strTarget = "index";
		return mapping.findForward(strTarget);

	}

	/**
	 * This method calls on submit the page after entering CR No. to access
	 * Request Details And calls the methods cancelledByCmb() and
	 * cancelReasonCmb() to display combos
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		String strTarget = "index";

		OnlineRequestCancellationTransFB formBean = (OnlineRequestCancellationTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		OnlineRequestCancellationTransDATA.onlineRequestDetails(formBean);
		OnlineRequestCancellationTransDATA.cancelledByCmb(formBean);
		OnlineRequestCancellationTransDATA.cancelReasonCmb(formBean);

		return mapping.findForward(strTarget);
	}

	/**
	 * This method calls on click of A Request No. (i.e hyperlink)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward GETPOPUP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		OnlineRequestCancellationTransFB formBean = (OnlineRequestCancellationTransFB) form;
		OnlineRequestCancellationTransDATA
				.getPopUp(request, response, formBean);
		return null;
	}

	/**
	 * This method calls if we again click on a Request No. (i.e hyperlink)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward GETPOPUPDATA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		OnlineRequestCancellationTransFB formBean = (OnlineRequestCancellationTransFB) form;
		OnlineRequestCancellationTransDATA.getPopUpData(request, response,
				formBean);
		return null;
	}

	/**
	 * This method calls on SAVE button to cancel a Request and insert the data
	 * into database.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward addData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);

	//	OnlineRequestCancellationTransFB formBean = (OnlineRequestCancellationTransFB) form;
		OnlineRequestCancellationTransDATA.insertData(form, request, response);
		//OnlineRequestCancellationTransDATA.onlineRequestDetails(formBean);
		//OnlineRequestCancellationTransDATA.cancelledByCmb(formBean);
		//OnlineRequestCancellationTransDATA.cancelReasonCmb(formBean);
		return mapping.findForward("index");
	}

	/**
	 * This method calls on cancel button to show the init page(in start up
	 * folder)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		ActionForward acFwd = new ActionForward();

		acFwd.setPath("/hisglobal/initPage.jsp");

		acFwd.setContextRelative(true);

		return acFwd;

	}

}
