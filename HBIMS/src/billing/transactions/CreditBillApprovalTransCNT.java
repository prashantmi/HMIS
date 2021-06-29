package billing.transactions;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import hisglobal.presentation.CSRFGardTokenAction;



public class CreditBillApprovalTransCNT extends CSRFGardTokenAction {
	public String bsid = "";

	public CreditBillApprovalTransCNT() {
	}

	/** *********************UNSPECIFIED************************** */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException

	{
		generateToken(request);

		return this.APPROVED(mapping, form, request, response);
	}
	
	/** *********************DISCOUNT APPROVED************************** */

	public ActionForward APPROVED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		generateToken(request);

		String target = "approval";
		return mapping.findForward(target);
	}
	
	/** *************************GO FUNCTION********************************* */

	public ActionForward GO(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException, SQLException 
	{
		generateToken(request);

		String strTarget = "approval";
		CreditBillApprovalTransFB formBean = (CreditBillApprovalTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		String strChk = request.getParameter("strTempVal");
		
		if (strChk != null) 
		{
			CreditBillApprovalTransDATA.initOnlineReq(strChk, formBean);
			CreditBillApprovalTransDATA.getWaivedBy(formBean);
						
			return mapping.findForward(strTarget);
		} 
		else 
		{
			return this.unspecified(mapping, form, request, response);
		}

	}

	/** *************************B-Servcie ID********************************* */

	public ActionForward BId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception, SQLException {
		CreditBillApprovalTransFB formBean = (CreditBillApprovalTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		String sid = CreditBillApprovalTransDATA.AjaxResponse(formBean, request,response);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(sid);
		//System.out.println("ajax called....");
		return null;
	}

	/** *************************CANCEL********************************* */

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ActionForward acFwd = new ActionForward();

		acFwd.setPath("/hisglobal/initPage.jsp");

		acFwd.setContextRelative(true);

		return acFwd;

	}
	
	/** *************************INSERT********************************* */
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception, SQLException 
	{
		validateToken(request, response);

		boolean retValue = false;
		CreditBillApprovalTransFB formbean = (CreditBillApprovalTransFB) form;
		retValue = CreditBillApprovalTransDATA.getInsertData(formbean, request); 
		if (retValue) 
		{
			return mapping.findForward("approval");
		} 
		else 
		{
			return this.unspecified(mapping, form, request, response);
		}
	}
	/** *************************INITIALPAGE********************************* */

	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		// String target = "initial";
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/startup/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;

	}

	
	

	/** *************************POP - UP********************************* */
	public ActionForward PopUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception, SQLException {
		DiscountApprovalTransFB formbean = (DiscountApprovalTransFB) form;
		DiscountApprovalTransDATA.PopUpResponse(formbean, request, response);
		return null;
	}


}
