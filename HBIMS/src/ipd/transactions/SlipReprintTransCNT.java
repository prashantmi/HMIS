package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class  SlipReprintTransCNT extends DispatchAction{

	
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
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		
		saveToken(request);
	 SlipReprintTransFB formBean = (SlipReprintTransFB)form;
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		SlipReprintTransDATA.initPatientAdmission(formBean);
		
		
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

		saveToken(request);
		String target = "bedstatus";
		SlipReprintTransFB formBean = (SlipReprintTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		SlipReprintTransDATA.initBedStatus(formBean,request);
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
		SlipReprintTransFB formBean = (SlipReprintTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setStrHospCode("108");
		SlipReprintTransDATA.initBedDetails(formBean,request,response);
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
		SlipReprintTransFB formBean = (SlipReprintTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	//	formBean.setStrHospCode("108");
		SlipReprintTransDATA.initWardDetails(formBean,request,response);
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
		SlipReprintTransFB formBean = (SlipReprintTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setStrHospCode("108");
		SlipReprintTransDATA.initRoomDetails(formBean,request,response);
		return null;
	}
	public ActionForward GETUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		saveToken(request);
		SlipReprintTransFB formBean = (SlipReprintTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		SlipReprintTransDATA.initUnitDtl(formBean,request,response);
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
		SlipReprintTransFB formBean = (SlipReprintTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		SlipReprintTransDATA.insert(formBean,request);
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
		SlipReprintTransFB formBean = (SlipReprintTransFB) form;
		formBean.setStrCrNo("");
		formBean.setStrMsgString("");
		formBean.setStrMsg("");
		formBean.setStrWarningMsg("");
			return this.unspecified(mapping, form, request, response);
	}
}
