package ipd.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.UserVO;
import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AdmissionAdviceTransCNT extends CSRFGardTokenAction {

	public AdmissionAdviceTransCNT() {

	}

	public ActionForward unspecified(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws Exception {
		//AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) _Form;
		generateToken(_Request);
		String target = "add1";	
		/////without desk
		
		return _Mapping.findForward(target);
	}
	/**
	 * forwards control to the Page admissionadvice_add_ipdtrans.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws HisException 
	{
		//saveToken(request);
		//generateToken(request);
		String target = "add";
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;		
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		//IpdConfigUtil ipdC=new IpdConfigUtil(request.getSession().getAttribute("hosp_code").toString());		
		//UserVO _userVo=ControllerUTIL.getUserVO(request);
		
		/********commented by vikrant for doctor desk ******/
		//IpdConfigUtil ipdC=new IpdConfigUtil(request.getSession().getAttribute("hosp_code").toString());		
		//IpdConfigUtil ipdC=new IpdConfigUtil(_userVo.getHospitalCode());
		IpdConfigUtil ipdC=new IpdConfigUtil(formBean.getStrHospCode());
		formBean.setStrDiagType(ipdC.getStrDiagnosisType());
		if(ipdC.getStrDiagnosisType().equals("1"))
			formBean.setStrDiagnosisType("0");
		if(ipdC.getStrDiagnosisType().equals("2"))
			formBean.setStrDiagnosisType("1");
		if(ipdC.getStrDiagnosisType().equals("0"))
			formBean.setStrDiagnosisType("0");
		formBean.setStrMode(ipdC.getStrAdmissionAdviceMode());
		formBean.setStrCrNo(request.getParameter("patCrNo"));
		formBean.setStrDepartment(request.getParameter("departmentUnitCode").substring(0, 3));
		formBean.setStrWard(request.getParameter("wardCode"));
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
		String str = formBean.getStrMsgString();
		
		if (!formBean.getStrMsgType().equals("1")) 
		{
			AdmissionAdviceTransDATA.initAdmissionAdviceOPDDesk(formBean, request);
		} 
		else 
		{
			AdmissionAdviceTransDATA.initAdmissionAdviceOPDDesk(formBean, request);
			formBean.setStrMsgString(str);
		}
		/*if(!formBean.getStrMsgType().equals("2"))
		{
			AdmissionAdviceTransDATA.initIcdDiagnosis(response,formBean);
			AdmissionAdviceTransDATA.initHosiptalDiagnosis(request,response, formBean);
		}*/
		return mapping.findForward(target);
	}

	/**
	 * forwards control to the Page admissionadvice_add_ipdtrans.jsp
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws HisException 
	{
		//saveToken(request);
		//generateToken(request);
		String target = "add1";
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		IpdConfigUtil ipdC=new IpdConfigUtil(formBean.getStrHospCode());
		formBean.setStrDiagType(ipdC.getStrDiagnosisType());
		if(ipdC.getStrDiagnosisType().equals("1"))//ICD
			formBean.setStrDiagnosisType("0");
		if(ipdC.getStrDiagnosisType().equals("2"))//Hospital
			formBean.setStrDiagnosisType("1");
		if(ipdC.getStrDiagnosisType().equals("0"))//Both
			formBean.setStrDiagnosisType("0");
		formBean.setStrMode(IpdTransConfig.getAdmissionAdviceMode());
		String str = formBean.getStrMsgString();
		if (!formBean.getStrMsgType().equals("1")) 
		{
			AdmissionAdviceTransDATA.setDeptComboValues(formBean,request,response);
			AdmissionAdviceTransDATA.initAdmissionAdvice(formBean, request);
		}
		else 
		{
			AdmissionAdviceTransDATA.initAdmissionAdvice(formBean, request);
			formBean.setStrMsgString(str);
		}
		/*if(formBean.getStrMsgType().equals("0"))
		{
			AdmissionAdviceTransDATA.initIcdDiagnosis(response,formBean);
			AdmissionAdviceTransDATA.initHosiptalDiagnosis(request,response, formBean);
		}*/

		return mapping.findForward(target);
	}

	/**
	 * forwards control to the Bed details pop up window
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BEDSTATUS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String strTarget = "";
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		AdmissionAdviceTransDATA.initBedStatus(formBean, request);
		strTarget = "bedstatus1";
		if(IpdTransConfig.getAdmissionAdviceMode().equals("0"))
			strTarget = "bedstatus";
		/*
		 * if(formBean.isDeptFound()==false) { strTarget="bedstatus1"; } else {
		 * 
		 * strTarget="bedstatus1"; }
		 */

		return mapping.findForward(strTarget);
	}

	/*
	 * public ActionForward BEDSTATUS1(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * HisException, SQLException {
	 * 
	 * String strTarget = "bedstatus1"; AdmissionAdviceTransFB formBean =
	 * (AdmissionAdviceTransFB) form;
	 * AdmissionAdviceTransDATA.initBedStatus1(formBean, request); return
	 * mapping.findForward(strTarget); }
	 */

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BEDSTATUSPATIENTDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		AdmissionAdviceTransDATA.initBedStatusPatientDtls(request, response,
				formBean);

		return null;

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BEDSTATUSDTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		AdmissionAdviceTransDATA.initBedStatusDtls(request, response, formBean);

		return null;

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward HOSITALPDIAGNOSIS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		AdmissionAdviceTransDATA.initHosiptalDiagnosis(request,response, formBean);

		return null;

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward ICDDIAGNOSIS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;	 
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		AdmissionAdviceTransDATA.initIcdDiagnosis(response, formBean);

		return null;

	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception, SQLException 
	{		//validateToken(request, response);
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		formBean.setStrHospCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());
		
		AdmissionAdviceTransDATA.insert(formBean);
		formBean.setStrCrNo("");
		formBean.setPatCrNo("");
		if(IpdTransConfig.getAdmissionAdviceMode().equals("1"))
		{
			if(formBean.getStrMode().equals("1"))
				return this.unspecified(mapping, formBean, request, response);
			else
			{
			  formBean.setStrFlagForSave("1");
			  return mapping.findForward("messageIpdDesk");
			}
		}
		else if(request.getSession().getAttribute("deskName").toString().equals("opdDesk"))
			return this.OPDLIST(mapping, formBean, request, response);
		else
			return this.CMOLIST(mapping, formBean, request, response);
	}

	/**
	 * This function forward control List pop up window
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward LIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String strTarget = "list";
System.out.println("opened--------------------------------------------------------------------------");
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		formBean.setStrCrNo(request.getParameter("crNo"));
		AdmissionAdviceTransDATA.initList(formBean, request);

		return mapping.findForward(strTarget);
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward LISTDTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		AdmissionAdviceTransDATA.initListDtls(request, response, formBean);

		return null;

	}

	/**
	 * This function forward ward combo details on main page on the basis of
	 * ward criteria
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward MODEWARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		AdmissionAdviceTransDATA.initWardCombo(request, response, formBean);
		
		return null;
	}

	/**
	 * This function forward room details in room combo on bed details pop up
	 * window
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward MODEROOM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		AdmissionAdviceTransDATA.initRoomCombo(request, response, formBean);
		return null;
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BEDDETAILS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		AdmissionAdviceTransDATA.initBedDetails(request, response, formBean);
		return null;
	}

	/**
	 * This function forward ward details on bed details pop up window on the
	 * basis of ward type code
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward WARD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		AdmissionAdviceTransDATA.initWardComboBedDetails(request, response,
				formBean);
		return null;
	}

	/**
	 * This function invoke AdmissionAdviceTransDATA's checkDuplocate
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward DUPLICATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) form;
		AdmissionAdviceTransDATA.checkDupicate(request, response, formBean);
		return null;
	}
	public ActionForward OPDLIST(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/opd/opdDesk.cnt?mode=unspecified&hmode=NEW");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward CMOLIST(ActionMapping _mapping, ActionForm _form,
			HttpServletRequest _request, HttpServletResponse _response)
			throws HisException {
		ActionForward acFwd = new ActionForward();
		acFwd
				.setPath("/registration/cmoDesk.cnt?mode=unspecified&hmode=NEW");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws HisException {

		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	public ActionForward UNITCOMBO(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) _Form;
		AdmissionAdviceTransDATA.setUnitComboValues(formBean, _Request, _Response);
		return null;
	}
	
	public ActionForward ADVANCEAMOUNT(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) _Form;
		AdmissionAdviceTransDATA.setAdvnaceAmountValues(formBean, _Request, _Response);
		return null;
	}
	public ActionForward PACKAGEAMOUNT(ActionMapping _Mapping, ActionForm _Form,
			HttpServletRequest _Request, HttpServletResponse _Response)
			throws HisException {
		AdmissionAdviceTransFB formBean = (AdmissionAdviceTransFB) _Form;
		AdmissionAdviceTransDATA.setPackageAmountValues(formBean, _Request, _Response);
		return null;
	}

}
