package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import ipd.IpdTransConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class  PatientAdmissionCancellationTransCNT extends CSRFGardTokenAction{

	
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
		//saveToken(request);
		String target = "add";
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
			throws HisException {
		generateToken(request);
		//saveToken(request);
	 PatientAdmissionCancellationTransFB formBean = (PatientAdmissionCancellationTransFB)form;
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setIsSingleMenu(request.getParameter("isSingleMenu"));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionCancellationTransDATA.initPatientAdmission(formBean);
		if(formBean.getStrPatStatusCode().equals(IpdTransConfig.getOpdCode()))
		{
			formBean.setStrCrNo("");
			formBean.setStrMsgString("Patient is in OPD");
			return mapping.findForward("add");
		}
		else if(formBean.getStrPatStatusCode().equals(IpdTransConfig.getDiedCode()))
		{
			formBean.setStrCrNo("");
			formBean.setStrMsgString("Patient is DEAD!!!");
			return this.unspecified(mapping, form, request, response);
			//return mapping.findForward("add");
		}
		else
			return this.unspecified(mapping, formBean, request, response);
		
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
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		//formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NewBornBabyTransDATA.initBedStatus(formBean,request);
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
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		//formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setStrHospCode("108");
		NewBornBabyTransDATA.initBedDetails(formBean,request,response);
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
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NewBornBabyTransDATA.initWardDetails(formBean,request,response);
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
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NewBornBabyTransDATA.initRoomDetails(formBean,request,response);
		return null;
	}
	public ActionForward GETUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
	//	formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NewBornBabyTransDATA.initUnitDtl(formBean,request,response);
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
		PatientAdmissionCancellationTransFB formBean = (PatientAdmissionCancellationTransFB)form;
		
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		PatientAdmissionCancellationTransDATA.insert(formBean);
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
		PatientAdmissionCancellationTransFB formBean = (PatientAdmissionCancellationTransFB) form;
		formBean.setStrCrNo("");
		formBean.setStrMsgString("");
		formBean.setStrMsg("");
		formBean.setStrWarningMsg("");
			return this.unspecified(mapping, form, request, response);
	}
	public ActionForward SIGLEMENUHOME(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		ActionForward acFwd = new ActionForward();
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		acFwd.setPath("/ipd/transactions/PatientAdmissionModificationTransBSCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
}
