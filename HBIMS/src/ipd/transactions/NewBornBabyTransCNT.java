package ipd.transactions;

import ipd.IpdConfigUtil;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class NewBornBabyTransCNT extends DispatchAction{

	
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
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
	 	IpdConfigUtil util = new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	 	formBean.setStrOnlineOrNot(util.getStrNewBornBabyProcessType());
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

		//saveToken(request);
		//WebUTIL.refreshTransState(request);
	 	NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrCaseSheetNo("");
		NewBornBabyTransDATA.initPatientAdmission(formBean);
		
		
			return this.unspecified(mapping, formBean, request, response);
		
	}
	// to match ward room criteria for new born baby while going to allot same bed as mother
	// added by Debashis as per CR 13 jan 2012
	public ActionForward MATCHCRITERIA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NewBornBabyTransDATA.matchWardRoomCriteria(formBean,request,response);
		return null;
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
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
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
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
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
		//formBean.setStrHospCode("108");
		NewBornBabyTransDATA.initWardDetails(formBean,request,response);
		return null;
	}
	public ActionForward GETWARDSHARABLE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setStrHospCode("108");
		NewBornBabyTransDATA.initWardDetailsSh(formBean,request,response);
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
		//formBean.setStrHospCode("108");
		NewBornBabyTransDATA.initRoomDetails(formBean,request,response);
		return null;
	}
	public ActionForward GETROOMSH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//formBean.setStrHospCode("108");
		NewBornBabyTransDATA.initRoomDetailsSh(formBean,request,response);
		return null;
	}
	public ActionForward GETUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
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
			throws HisException {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		IpdConfigUtil icu=new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//System.out.println("Welcome to new borm baby case insertion");
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		NewBornBabyTransDATA.insert(formBean,request);
		/*System.out.println("icu.getStrMaxNoOfBabyMotherCanBorn()"+icu.getStrMaxNoOfBabyMotherCanBorn());
		System.out.println("formBean.getStrNumberOfChildrenBorn()"+formBean.getStrNumberOfChildrenBorn());
		System.out.println("formBean.getStrMaxBabyAllowed()"+formBean.getStrMaxBabyAllowed());*/
		/*if((Integer.parseInt(icu.getStrMaxNoOfBabyMotherCanBorn())-Integer.parseInt(formBean.getStrNumberOfChildrenBorn())<Integer.parseInt(formBean.getStrMaxBabyAllowed())-1) && Integer.parseInt(formBean.getStrNumberOfChildrenBorn())>0)
			return this.GO(mapping, form, request, response);
		else
			{
		     formBean.setStrCrNo("");
		     return mapping.findForward("add");
			}*/ //commented by ajay for enabling print slip from pat admission
		formBean.setStrCrNo("");
		return mapping.findForward("add");
	}
	public ActionForward CLEAR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		NewBornBabyTransFB formBean = (NewBornBabyTransFB) form;
		formBean.setStrCrNo("");
		formBean.setStrPatientCrNo("");
		formBean.setStrSaveFlag("");
		formBean.setStrMsgString("");
		formBean.setStrMsg("");
		formBean.setStrWarningMsg("");
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
	public ActionForward GETADVANCEAMOUNT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NewBornBabyTransDATA.getAdvanceAmount(formBean, request, response);
		return null;
	}
	
	public ActionForward UPDATEMOTHERDETAILS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
	 	IpdConfigUtil util = new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	 	formBean.setStrOnlineOrNot(util.getStrNewBornBabyProcessType());
		//saveToken(request);
		String target = "update";
		
		return mapping.findForward(target);
	}
	
	
	public ActionForward GOUPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		//WebUTIL.refreshTransState(request);
	 	NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrWardBedStatusFlag("0");// to identify same bed as mother option
		NewBornBabyTransDATA.initPatAdmUpdate(formBean);
		IpdConfigUtil ipdUtil =new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrPrintingMode(ipdUtil.getPrintModeforAdmissionTicket());
		
		return this.UPDATEMOTHERDETAILS(mapping, formBean, request, response);
		
	}
	
	
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		IpdConfigUtil icu=new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		NewBornBabyTransDATA.update(formBean);
		
		formBean.setStrCrNo("");
		return mapping.findForward("add");
		
		
	}
	
	public ActionForward SAMEBEDASMOTHER(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		//WebUTIL.refreshTransState(request);
	 	NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrWardBedStatusFlag("1");// to identify same bed as mother option
		NewBornBabyTransDATA.initPatAdmSameBedAsMom(formBean);
		IpdConfigUtil ipdUtil =new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrPrintingMode(ipdUtil.getPrintModeforAdmissionTicket());
		
			return this.unspecified(mapping, formBean, request, response);
		
	}
	public ActionForward GETDISTRICT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NewBornBabyTransDATA.initDistrict(formBean,request,response);
		return null;
	}
	public ActionForward GETSHARABLE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NewBornBabyTransDATA.getsharable(formBean,request,response);
		return null;
	}
	public ActionForward GETCONS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		NewBornBabyTransFB formBean = (NewBornBabyTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		NewBornBabyTransDATA.initCons(formBean, request, response);
		return null;
	}
}
