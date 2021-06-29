package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AdmissionReprintTransBSCNT extends CSRFGardTokenAction {
	/**
	 * forwards control to the Page visitorpassGeneration_ipdTrans.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		generateToken(request);
		AdmissionReprintTransFB formBean = (AdmissionReprintTransFB) form;

		String target = "add";
		return mapping.findForward(target);
	}

	/**
	 * forwards control to the Page visitorpassGeneration_ipdTrans.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		generateToken(request);
		AdmissionReprintTransFB formBean = (AdmissionReprintTransFB) form;
		// boolean retVal=false;

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSaveFlag("");
		formBean.setStrSaveStatus("");
		AdmissionReprintTransDATA.patStatusCode(formBean);

		if (formBean.getStrCase().equals("2")) // CR No Wise
			AdmissionReprintTransBSDATA.admissionList_BS(formBean);

		return this.unspecified(mapping, form, request, response);
	}

	public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);
		AdmissionReprintTransFB formBean = (AdmissionReprintTransFB) form;
		formBean.setStrAdmnNo("");
		formBean.setStrCrNo("");
		formBean.setStrRePrintCharge("0");
		formBean.setStrErrMsgString("");
		formBean.setStrNormalMsgString("");
		formBean.setStrSaveFlag("");
		return this.unspecified(mapping, form, request, response);
	}

	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}

	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);
		AdmissionReprintTransFB formBean = (AdmissionReprintTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		AdmissionReprintTransDATA.insert(formBean, request, response);
		formBean.setStrAdmnNo("");
		formBean.setStrCrNo("");
		formBean.setStrRePrintCharge("0");
		String target = "add";
		return mapping.findForward(target);
	}

	public ActionForward VistorPassDtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		AdmissionReprintTransFB formBean = (AdmissionReprintTransFB) form;
		// formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());
		String adm_No = (String) request.getParameter("admNo");
		formBean.setStrAdmnNo(adm_No);
		AdmissionReprintTransDATA.visitPass(formBean, request, response);

		return null;
	}

	/*
	 * public void SHOWRPT(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * HisException {
	 * 
	 * AdmissionReprintTransFB formBean = (AdmissionReprintTransFB) form;
	 * formBean
	 * .setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE"
	 * ).toString()); AdmissionReprintTransDATA.showReport(formBean, request,
	 * response);
	 * 
	 * }
	 */

}
