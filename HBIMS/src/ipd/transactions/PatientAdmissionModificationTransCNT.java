package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class  PatientAdmissionModificationTransCNT extends CSRFGardTokenAction{

	
	/**
	 * forwards control to the Page patientAdmission_add_ipdTrans.jsp
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
		String target = "add";
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrFlagForCheck("1");
		//PatientAdmissionModificationTransDATA.setPatientAdmittedToday(formBean,request,response);
		return mapping.findForward(target);
	}
	
	/**
	 * forwards control to the Page patientAdmission_add_ipdTrans.jsp
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
			throws Exception {
		
	//	saveToken(request);
		generateToken(request);
		String target = "add";
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		PatientAdmissionModificationTransDATA.initPatientAdmission(formBean);
		return mapping.findForward(target);
		
	}
	/**
	 * This function forward control to bed details pop up window
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward BEDSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		String target = "bedstatus";
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionModificationTransDATA.initBedStatus(formBean,request);
		return mapping.findForward(target);
	}
	/**This function is used to invoke PatientAdmissionTransDATA's initBedDetails() to search bed and it corresponding status
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws HisException
	 */
	public ActionForward BEDDETAILS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setStrHospCode("108");
		PatientAdmissionModificationTransDATA.initBedDetails(formBean,request,response);
		return null;
	}
	/**
	 * This function bring ward details in ward combo in bed details pop up window when user select ward type  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws HisException
	 */
	public ActionForward GETWARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	//	formBean.setStrHospCode("108");
		PatientAdmissionModificationTransDATA.initWardDetails(formBean,request,response);
		return null;
	}
	/**
	 * This function is used to bring room name in room combo on bed details pop up window
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward GETROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionModificationTransDATA.initRoomDetails(formBean,request,response);
		return null;
	}
	public ActionForward GETUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//saveToken(request);
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionModificationTransDATA.initUnitDtl(formBean,request,response);
		return null;
	}
	/**
	 * This function is used to insert details into database
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		validateToken(request, response);
		//saveToken(request);
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		PatientAdmissionModificationTransDATA.insert(formBean,request);
		formBean.setStrCrNo("");
		return mapping.findForward("add");
	}
	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB) form;
		formBean.setStrCrNo("");
		formBean.setStrMsgString("");
		formBean.setStrMsg("");
		formBean.setStrWarningMsg("");
			return this.unspecified(mapping, form, request, response);
	}
	/**
	 * This function bring ward details,treatment category & consultant values based on department  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws HisException
	 */
	public ActionForward GETWARDCATCONSULTANT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionModificationTransDATA.getWardCatConsultant(formBean,request,response);
		return null;
	}
	public ActionForward GETDISTRICT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionModificationTransDATA.initDistrict(formBean,request,response);
		return null;
	}
}
