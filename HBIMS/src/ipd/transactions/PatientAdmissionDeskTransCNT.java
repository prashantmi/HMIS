package ipd.transactions;


import ipd.IpdTransConfig;

import hisglobal.exceptions.HisException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientAdmissionDeskTransCNT extends DispatchAction{

	
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

		saveToken(request);
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
	public ActionForward GOPatient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		
	 	PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		//formBean.setStrHospCode("108");
	//	formBean.setStrSeatId("10001");
		//formBean.setStrCrNo("1080800003404");
		PatientAdmissionTransDATA.initPatientAdmission(formBean);
		if(formBean.getStrPatStatusCode().equals(IpdTransConfig.getAdmittedCode()))
		{
			//formBean.setStrCrNo("");
			formBean.setStrMsgString("Patient is already admitted");
			return mapping.findForward("add");
		}
		else if(formBean.getStrPatStatusCode().equals(IpdTransConfig.getDiedCode()))
		{
			formBean.setStrMsgString("Patient is DEAD!!!");
			return this.unspecified(mapping, form, request, response);
			//return mapping.findForward("add");
		}
		else if(formBean.getStrAdviceStatus().equals("0"))
		{
			formBean.setStrMsgString("Either Patient Advice is not Generated Or Expired");
			return this.unspecified(mapping, form, request, response);
			//return mapping.findForward("add");
		}
		else
		{
			return this.unspecified(mapping, formBean, request, response);
		}
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

		saveToken(request);
		String target = "bedstatus";
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initBedStatus(formBean,request);
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

		saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initBedDetails(formBean,request,response);
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

		saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initWardDetails(formBean,request,response);
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

		saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initRoomDetails(formBean,request,response);
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
			throws HisException {

		saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());

		PatientAdmissionTransDATA.insert(formBean,request);
		formBean.setStrCrNo("");
		return mapping.findForward("add");
	}
}
