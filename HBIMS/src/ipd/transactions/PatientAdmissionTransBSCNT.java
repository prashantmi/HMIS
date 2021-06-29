package ipd.transactions;


import ipd.DisplayImage;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;
import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientAdmissionTransBSCNT extends CSRFGardTokenAction{

	
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
		//generateToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
	 	IpdConfigUtil util = new IpdConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
	 	formBean.setStrAdmissionMode(util.getStrAdmissionOnline());
		//saveToken(request);
		//String target = "add";
	 	String target = "addnew";
		
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
		generateToken(request);
	 	PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		PatientAdmissionTransDATA.initPatientAdmissionBS(formBean);
		
		
		/*
		 * If Patient is admitted the screen would not open 
		 */
		if(formBean.getStrPatStatusCode().equals(IpdTransConfig.getAdmittedCode()))
		{
			
			formBean.setStrMsgString("Patient is already admitted");
			formBean.setStrCrNo("");
			//return mapping.findForward("add");
			return mapping.findForward("addnew");
		}
		/*
		 * If Patient is died the screen would not open 
		 */
		
		//else if(formBean.getStrPatStatusCode().equals(IpdTransConfig.getDiedCode()))
		else if(formBean.getStrIsDead().equals("1"))
		{
			formBean.setStrMsgString("Patient is DEAD!!!");
			formBean.setStrCrNo("");
			return this.unspecified(mapping, form, request, response);
			//return mapping.findForward("add");
		}
		
		/*
		 * If advice is expired the screen would not open 
		 */
		
		/*else if(formBean.getStrAdviceStatus().equals("0"))
		{
			formBean.setStrMsgString("Either Patient Advice is not Generated Or Expired");
			formBean.setStrCrNo("");
			return this.unspecified(mapping, form, request, response);
			//return mapping.findForward("add");
		}*/
		else
		{
			return this.unspecified(mapping, formBean, request, response);
		}
	}
	/**
	 * After selection of Department-When Unit Mandatory
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * Last modified By Amit Kumar Ateria on 10-May-2011
	 */
	public ActionForward GETUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initUnit(formBean,request,response);
		return null;
	}
	
	public ActionForward GETTREATMENTCAT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initCat(formBean,request,response);
		return null;
	}
	public ActionForward CHARGEVALUE (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws HisException {
		
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initChargeValue(formBean,request,response);
		return null;
	}
	/**
	 * After selection of Department-When Unit Not Mandatory
	 * Gets Consultant & Ward
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * Last modified By Amit Kumar Ateria on 10-May-2011
	 */
	public ActionForward GETCONSWARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		//saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initConsWardDtl(formBean,request,response); 
		return null;
	}
	
	public ActionForward GETWARDNAME(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		//saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initTreatmenyCatWardDtl(formBean,request,response);
		return null;
	}
	
	public ActionForward GETWARDONUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		//saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initWardonUnit(formBean,request,response);
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

		//saveToken(request);
		validateToken(request, response);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrIsSingleWindowAdmission("1");//1-Yes
		PatientAdmissionTransDATA.insert(formBean,request);
		formBean.setStrCrNo("");
		formBean.setStrAdmissionCharge("0");
		formBean.setStrAdvanceDepsoitAtAdmissionCounter("0");
		//return mapping.findForward("add");
		return mapping.findForward("addnew");
	}
	public ActionForward PRINTSLIP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrAdmNo(request.getParameter("strAdmNo"));
		formBean.setIsDuplicateSlip(request.getParameter("duplicateMode"));	
		PatientAdmissionTransDATA.printSlip(formBean);
		
		
		request.setAttribute("strAdmNo", formBean.getStrAdmNo());
		return mapping.findForward("printadmissioncard");
	}
	
	public ActionForward PRINTSECONDSLIP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdConfigUtil ipdc=new IpdConfigUtil(formBean.getStrHospCode());
		formBean.setStrIsAdvanceAmountAtAdmission(ipdc.getStrAdmissionCharge());
		return mapping.findForward("secondSlip");
	}
	
	public ActionForward PRINTBILLSLIP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		
		request.setAttribute(request.getSession().getAttribute("SEATID").toString(),"seatId");
		return mapping.findForward("printadmissionthirdslip");
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
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB) form;
		formBean.setStrCrNo("");
		formBean.setStrMsgString("");
		formBean.setStrMsg("");
		formBean.setStrWarningMsg("");
		formBean.setStrPatientCrNo("");
		formBean.setStrSaveFlag("");
		return this.unspecified(mapping, form, request, response);
	}
	
	public ActionForward DISCHARGESLIP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrCrNo(request.getParameter("strCrNo"));
		formBean.setStrAdmNo(request.getParameter("strAdmNo"));
		formBean.setIsDuplicateSlip(request.getParameter("duplicateMode"));
		PatientAdmissionTransDATA.printSlip(formBean);
		request.setAttribute("strAdmNo", formBean.getStrAdmNo());
		return mapping.findForward("printdischargecard");
	}
	
	/*------------------Bed Popup Functions----------------------------*/
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

		//saveToken(request);
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

	//	saveToken(request);
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

		//saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initRoomDetails(formBean,request,response);
		
		return null;
	}
	
	public ActionForward GETDISTRICT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		//saveToken(request);
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		PatientAdmissionTransDATA.initDistrict(formBean,request,response);
		return null;
	}
	public ActionForward NEWBORNBABYADM(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/ipd/transactions/NewBornBabyTransBSCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward SIGLEMENUHOME(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		ActionForward acFwd = new ActionForward();
		PatientAdmissionModificationTransFB formBean = (PatientAdmissionModificationTransFB)form;
		acFwd.setPath("/ipd/transactions/PatientAdmissionModificationTransBSCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	
	public ActionForward GETPAYMODE(ActionMapping mapping,ActionForm form,HttpServletRequest req,HttpServletResponse res){
		
		PatientAdmissionTransFB formBean = (PatientAdmissionTransFB)form;
		formBean.setStrHospCode(req.getSession().getAttribute("HOSPITAL_CODE").toString());
		
			PatientAdmissionTransDATA.getPayMode(formBean,req,res);
			
	
		
		return null;
	}


}

