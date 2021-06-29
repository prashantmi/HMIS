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


import billing.BillConfigUtil;

public class CashCollectionWithoutCrTransCNT extends CSRFGardTokenAction {

	/**
	 * forwards control to the Page cashcollection_billtrans.jsp
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
		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		String target = "cashcollectionwithoutcrno";
		
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(request.getSession().getAttribute("BACK_DATE_DAY_END_FLAG").toString().equals("1"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Perform Day End First.");
				return mapping.findForward(target);
			}
			else if(request.getSession().getAttribute("BACK_DATE_DAY_END_FLAG").toString().equals("2"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Deposit Day End Amount.");
				return mapping.findForward(target);
			}
		}
		
		CashCollectionWithoutCrTransDATA.checkCounterStatus(request, formBean);
		
		return this.WITHOUTCRNO(mapping, form, request, response);
	
	}

	public ActionForward WITHCRNO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		
		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		
		ActionForward acFwd = new ActionForward();
		
			
		if(formBean.getStrIsOnline().equals("1")){
			
			acFwd
			.setPath("/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode=ONLINEMODE&counterMode="+formBean.getStrCounterMode());
	
			
		}else{
	
			acFwd
			.setPath("/billing/transactions/CashCollectionOfflineTransCNT.cnt?hmode=OFFLINEMODE&counterMode="+formBean.getStrCounterMode());
			
			
		}
		
		 
		acFwd.setContextRelative(true);
		return acFwd;
	
	}
	
	   
	/**
	 * forwards Cash Collection to Without Cr. No. Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward WITHOUTCRNO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		generateToken(request);

		String target = "cashcollectionwithoutcrno";

		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(request.getSession().getAttribute("BACK_DATE_DAY_END_FLAG").toString().equals("1"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Perform Day End First.");
				return mapping.findForward(target);
			}
			else if(request.getSession().getAttribute("BACK_DATE_DAY_END_FLAG").toString().equals("2"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Deposit Day End Amount.");
				return mapping.findForward(target);
			}
		}
		
		String strIsOnline = "0";
		String strCounterMode = "0";
		
		if(request.getParameter("isOnline") != null ){
			
			strIsOnline = request.getParameter("isOnline").toString();
		}
		
		if(request.getParameter("counterMode") != null ){
			
			strCounterMode = request.getParameter("counterMode").toString();
		}
		
		formBean.setStrIsOnline(strIsOnline);
		formBean.setStrCounterMode(strCounterMode);

		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR").toString());

		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		

		BillConfigUtil billConfigUtil = null;
		billConfigUtil = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		
		CashCollectionWithoutCrTransDATA.initWithoutCrNo(request, formBean);
		
		formBean.setStrPatientMode("2");
		formBean.setStrPatTypeBoth("1");//External
		formBean.setStrPatientType("2");
		formBean.setStrIntExtPat("2");
		formBean.setStrIsPreviousCrNoReq(billConfigUtil.getGeneralPreviousCrNoRequiredExt());
		formBean.setStrIsRefPhysicanReq(billConfigUtil.getGeneralReferringPhysicianRequiredExt());
		formBean.setStrPreviousCrNoSearch(billConfigUtil.getGeneralPreviousCrNoSearchingExt());
		formBean.setStrGeneralChargeTypeId(billConfigUtil.getGeneralChargeTypeExt());
		formBean.setStrWardTypeId(billConfigUtil.getGeneralWardTypeExt());

		return mapping.findForward(target);
		
	}
		
	
	/**
	 * forwards Cash Collection to Without Cr. No. Mode-Internal
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward INTERNALPATIENT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		generateToken(request);

		String target = "cashcollectionwithoutcrno";

		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		BillConfigUtil billConfigUtil = null;
		billConfigUtil = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		String strIsOnline = "0";
		String strCounterMode = "0";
		
		if(request.getParameter("isOnline") != null ){
			
			strIsOnline = request.getParameter("isOnline").toString();
		}
		
		if(request.getParameter("counterMode") != null ){
			
			strCounterMode = request.getParameter("counterMode").toString();
		}
		
		formBean.setStrIsOnline(strIsOnline);
		formBean.setStrCounterMode(strCounterMode);
					
		CashCollectionWithoutCrTransDATA.initWithoutCrNo(request, formBean);
		
		formBean.setStrPatientMode("2");
		formBean.setStrPatTypeBoth("1");//Internal
		formBean.setStrPatientType("1");
		formBean.setStrIntExtPat("1");
		formBean.setStrIsPreviousCrNoReq(billConfigUtil.getGeneralPreviousCrNoRequiredInt());
		formBean.setStrIsRefPhysicanReq(billConfigUtil.getGeneralReferringPhysicianRequiredInt());
		formBean.setStrPreviousCrNoSearch(billConfigUtil.getGeneralPreviousCrNoSearchingInt());
		formBean.setStrGeneralChargeTypeId(billConfigUtil.getGeneralChargeTypeInt());
		formBean.setStrWardTypeId(billConfigUtil.getGeneralWardTypeInt());
		
		return mapping.findForward(target);
	}
	/**
	 * forwards Cash Collection to Without Cr. No. Mode-External
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward EXTERNALPATIENT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		generateToken(request);

		String target = "cashcollectionwithoutcrno";

		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		BillConfigUtil billConfigUtil = null;
		billConfigUtil = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		
		String strIsOnline = "0";
		String strCounterMode = "0";
		
		if(request.getParameter("isOnline") != null ){
			
			strIsOnline = request.getParameter("isOnline").toString();
		}
		
		if(request.getParameter("counterMode") != null ){
			
			strCounterMode = request.getParameter("counterMode").toString();
		}
		
		formBean.setStrIsOnline(strIsOnline);
		formBean.setStrCounterMode(strCounterMode);
					
		CashCollectionWithoutCrTransDATA.initWithoutCrNo(request, formBean);
		
		formBean.setStrPatientMode("2");
		formBean.setStrPatTypeBoth("1");//External
		formBean.setStrPatientType("2");
		formBean.setStrIntExtPat("2");
		formBean.setStrIsPreviousCrNoReq(billConfigUtil.getGeneralPreviousCrNoRequiredExt());
		formBean.setStrIsRefPhysicanReq(billConfigUtil.getGeneralReferringPhysicianRequiredExt());
		formBean.setStrPreviousCrNoSearch(billConfigUtil.getGeneralPreviousCrNoSearchingExt());
		formBean.setStrGeneralChargeTypeId(billConfigUtil.getGeneralChargeTypeExt());
		formBean.setStrWardTypeId(billConfigUtil.getGeneralWardTypeExt());

		
		return mapping.findForward(target);
	}

	 
	/**
	 * forwards control to the Cash Collection Page with Cancel Mode
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		return this.WITHOUTCRNO(mapping, form, request, response);
	}

	/**
	 * function invoked by Ajax, gives the required Previous Cr No.Details when Cr number is entered.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward PREVIOUSCRNODTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		
		CashCollectionWithoutCrTransDATA.getPreviousCrNoDtls(request, response, formBean);

		return null;

	}
	 
	  
	
	/**
	 * function invoked by Ajax, populates the billing Service Combo box based
	 * on Request Type & Hospital Service.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BILLDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CashCollectionTransDATA.getBillServiceDetails(request, response);

		return null;

	}
 
	/**
	 * function invoked by Ajax, populates the Group Combo box based on Hospital
	 * Service and with or without Pack Flag.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GROUPDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.getGroupDetails(request, response);

		return null;

	}

	/**
	 * function invoked by Ajax, populates the Tariff List based on Selected
	 * Group Code.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward TARIFFDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.getTariffDetails(request, response);

		return null;

	}
	
	/**
	 * function invoked by Ajax, populates the Tariff Details based on Tariff Code.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward TARIFFCODEDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.getTariffCodeDetails(request, response);

		return null;

	}
	

	/**
	 * function invoked by Ajax, populates the Group Details based on Hospital
	 * Service and with or without Package Flag.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward PKGGROUPDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.getGroupDetails(request, response);

		return null;

	}

	/**
	 * function invoked by Ajax, populates the Package Details based on row
	 * Index.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward PKGDTLS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.getPackageDetails(request, response);

		return null;

	}

	/**
	 * function invoked by Ajax, gets Tariff Unit Details for Off line Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward TRFUNIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.getOffLineTariffUnitDetails(request, response);

		return null;

	} 
	/**
	 * function invoked by Ajax, gets Refund Bill Tariff Pop-up details for Off line Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward REFUNDBILLTARIFFPOPUPDTLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.getOffLineRefundBillTariffPopupDetails(request,
				response);

		return null;

	}

	/**
	 * function invoked by Ajax, gets Unit details for Off line Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward UNITVAL12(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CashCollectionWithoutCrTransDATA.UNITVAL12(request, response);

		return null;
	}

	/**
	 * function invoked by Ajax, gets Refund Bill Tariff Pop-up details for On line Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward REFUNDTARIFFPOPUPDTLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.getOnLineRefundTariffPopupDetails(request,
				response);

		return null;

	}

	/**
	 * function invoked by Ajax, gets Without Cr. No. Bill Details 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GOBILL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);

		CashCollectionWithoutCrTransDATA.initWithoutCrNoBillDetails(request, response);

		return null;

	}

	/**
	 * opens a pop-up with bill list, used for search bills in Without Cr. No. Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BILLLISTING(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String target = "billlist";

		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;

		formBean.setStrBillUsrFuncName(request.getParameter("usrFuncName"));

		return mapping.findForward(target);
	}

	/**
	 * function invoked by Ajax, gets Without Cr. No. Bill List 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward FETCHBILLLISTING(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		CashCollectionWithoutCrTransDATA.getBillListingDtls(request, response, formBean);

		return null;
	}

	/**
	 * Invokes when Bill has been Canceled  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BILLCANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		
		formBean.setStrPreviousCrNo("");
		
		return this.WITHOUTCRNO(mapping, formBean, request, response);
	}
 
	public ActionForward WITHOUTCRNOREQTARIFF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.getWithoutCrNoOnlineReqTariffDtls(request, response);

		return null;

	}
	
		
	public ActionForward UPDATEPRINTSTATUS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.updatePrintStatus(request, response);

		return null;

	}
	
	
	public ActionForward REPRINT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		CashCollectionWithoutCrTransDATA.rePrint(request, response);

		return null;

	}
	            
	
	/**
	 * Insert's Without Cr. No. Receipt Services.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward WITHOUTCRNORECEIPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);

	//	String target = "cashcollectionwithoutcrno";

		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR")
				.toString());


		if(request.getSession().getAttribute("USER_LEVEL") != null){
			
			formBean.setStrUserLevel(request.getSession().getAttribute("USER_LEVEL")
					.toString());
			
		}else{
			formBean.setStrUserLevel("1");
		}
		
		
		
		CashCollectionWithoutCrTransDATA.insertWithoutCrNoReceiptServices(formBean , request);

			return this.WITHOUTCRNO(mapping, formBean, request, response);

	}
	
	
	/**
	 * Insert's Without Cr. No. Refund Services.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 */
	public ActionForward WITHOUTCRNOREFUND(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);

		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR")
				.toString());


		if(request.getSession().getAttribute("USER_LEVEL") != null){
			
			formBean.setStrUserLevel(request.getSession().getAttribute("USER_LEVEL")
					.toString());
			
		}else{
			formBean.setStrUserLevel("1");
		}
		
		
		
		CashCollectionWithoutCrTransDATA.insertWithoutCrNoRefundServices(formBean , request);

			return this.WITHOUTCRNO(mapping, formBean, request, response);

	}
	
	public ActionForward ONLINEWITHOUTCRNORECEIPT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);

		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;
		formBean.setStrOffLineTreatmentCategory(formBean.getStrWithoutCrNoOnlineRequestNo().replace("^","#").split("#")[12]);
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		
		formBean.setStrIpAddress(request.getSession().getAttribute("IP_ADDR")
				.toString());


		if(request.getSession().getAttribute("USER_LEVEL") != null){
			
			formBean.setStrUserLevel(request.getSession().getAttribute("USER_LEVEL")
					.toString());
			
		}else{
			formBean.setStrUserLevel("1");
		}
		
		CashCollectionWithoutCrTransDATA.insertOnlineWithoutCrNoReceiptServices(formBean , request);

			return this.WITHOUTCRNO(mapping, formBean, request, response);

	}
	

	
	public ActionForward ONLINE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;		
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/CashCollectionOnlineTransCNT.cnt?hmode=ONLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}	
	public ActionForward LF(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;				
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/LFNoTransCNT.cnt?hmode=OFFLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward PATWALLET(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;				
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/PatWalletTransCNT.cnt?hmode=OFFLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward OFFLINE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;		
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/CashCollectionOfflineTransCNT.cnt?hmode=OFFLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	public ActionForward ADVANCED(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws HisException 
	{
		CashCollectionWithoutCrTransFB formBean = (CashCollectionWithoutCrTransFB) form;				
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/billing/transactions/AdvanceCashCollectionOnlineTransCNT.cnt?hmode=ONLINEMODE&counterMode="+formBean.getStrCounterMode());
		acFwd.setContextRelative(true);
		return acFwd;	
	}
	/**This method is used to Transfer Control Over Inti Page   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward CANCELPAGE(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
        ActionForward acFwd = new ActionForward();
        acFwd.setPath("/hisglobal/initPage.jsp");
        acFwd.setContextRelative(true);
        return acFwd;
    }	
}
