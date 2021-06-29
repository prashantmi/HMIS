package billing.transactions;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DiscountRecommendationApprovalTransCNT extends DispatchAction {
	public String bsid = "";

	public DiscountRecommendationApprovalTransCNT() {
	}

	/** *********************UNSPECIFIED************************** */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException

	{
		return this.APPROVED(mapping, form, request, response);
	}

	/** *********************DISCOUNT APPROVED************************** */

	public ActionForward APPROVED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		
		
		DiscountRecommendationApprovalTransFB formBean = (DiscountRecommendationApprovalTransFB) form;
		
		DiscountRecommendationApprovalTransDATA.getDepartmentList(formBean , request);
		
		String target = "approval";
		return mapping.findForward(target);
	}

	/** *************************CANCEL********************************* */

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ActionForward acFwd = new ActionForward();

		acFwd.setPath("/hisglobal/initPage.jsp");

		acFwd.setContextRelative(true);

		return acFwd;

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

	/** *************************GO FUNCTION********************************* */

	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		// System.out.println("cnt go");
		String strTarget = "approval";
		DiscountRecommendationApprovalTransFB formBean = (DiscountRecommendationApprovalTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String strChk = request.getParameter("strTempVal");

		if (strChk != null) {
			DiscountRecommendationApprovalTransDATA.initOnlineReq(strChk, formBean);
			DiscountRecommendationApprovalTransDATA.getRsnRmk(formBean);
			DiscountRecommendationApprovalTransDATA.getDepartmentList(formBean, request);
			return mapping.findForward(strTarget);
		} else {
			return this.unspecified(mapping, form, request, response);
		}

	}

	
	public ActionForward GETUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		
		DiscountRecommendationApprovalTransDATA.getUnitList(request, response);
		
		return null;
	}

	
	
	
	/** *************************B-Servcie ID********************************* */

	public ActionForward BId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception, SQLException {
		DiscountRecommendationApprovalTransFB formBean = (DiscountRecommendationApprovalTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String sid = DiscountRecommendationApprovalTransDATA.AjaxResponse(formBean, request,
				response);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(sid);
		return null;
	}

	/** *************************POP - UP********************************* */
	public ActionForward PopUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception, SQLException {
		DiscountRecommendationApprovalTransFB formbean = (DiscountRecommendationApprovalTransFB) form;
		DiscountRecommendationApprovalTransDATA.PopUpResponse(formbean, request, response);
		return null;
	}

	/** *************************INSERT********************************* */
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		boolean retValue = false;
		DiscountRecommendationApprovalTransFB formbean = (DiscountRecommendationApprovalTransFB) form;
		retValue = DiscountRecommendationApprovalTransDATA.getInsertData(formbean, request); // defined
																				// in
																				// DATA
																				// class

		if (retValue) {
			return this.APPROVED(mapping, form, request, response);
		} else {
			//System.out.println("unspecified in else");

			return this.unspecified(mapping, form, request, response);
		}
	}
}
