package billing.transactions;

import hisglobal.config.HISConfig;
import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import hisglobal.presentation.CSRFGardTokenAction;


import hisglobal.vo.UserVO;
import billing.BillConfigUtil;

/**
 * @author Anshul Jindal
 * 
 */
public class BillingCancellationTransBSCNT extends CSRFGardTokenAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.DispatchAction#unspecified(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);

		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		formBean.setFilePath("");
		formBean.setIsOpenPopUp("0");

		return this.init(mapping, form, request, response);
	}

	/**
	 * This method is used to forward the request on first jsp page And calls
	 * the methods cancelledByCmb() and cancelReasonCmb() to display combos on
	 * first page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 * 
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		
		String strTarget = "index";
		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		
		UserVO userVO = (UserVO) request.getSession().getAttribute(HISConfig.USER_VO);
		
		if(BillConfigUtil.BACK_DATE_DAYEND_STOP_PROCESS.equals("1"))
		{		
			if(userVO.getCheckBackDateDayEndFlag().toString().equals("1"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Perform Day End First.\n If Day End Already Performed , Please Relogin");
				return mapping.findForward(strTarget);
			}
			else if(userVO.getCheckBackDateDayEndFlag().toString().equals("2"))
			{
				formBean.setStrErrMsg("Cash Collection Not Applicable, Please Deposit Day End Amount.\n If Already Deposited , Please Relogin");
				return mapping.findForward(strTarget);
			}
		}

		
		/*formBean.setFilePath("");
		formBean.setIsOpenPopUp("0");*/
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
		formBean.setCrbill("0");
		formBean.setPrintMode(BillConfigUtil.PRINT_MODE.toString());//Laser Printing or DMP Printing
		BillingCancellationTransBSDATA.cancelledByCmb(formBean);
		BillingCancellationTransBSDATA.cancelReasonCmb(formBean);
		return mapping.findForward(strTarget);
	}

	/**
	 * This method calls on submit the page after entering CR No. to access Bill
	 * Details
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);

		String strTarget = "index";

		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		 formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
		BillingCancellationTransBSDATA.billDetails(formBean);
		BillingCancellationTransBSDATA.cancelledByCmb(formBean);
		BillingCancellationTransBSDATA.cancelReasonCmb(formBean);

		return mapping.findForward(strTarget);
	}

	public ActionForward GOCRBILL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);

		String strTarget = "index";

		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		 formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());
		 formBean.setCrbill("1");
		BillingCancellationTransBSDATA.CrbillDetails(formBean);
		BillingCancellationTransBSDATA.cancelledByCmb(formBean);
		BillingCancellationTransBSDATA.cancelReasonCmb(formBean);

		return mapping.findForward(strTarget);
	}
	/**
	 * This method calls on submit the page after Entering Guarantor Name to
	 * access Bill Details
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward GO2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);

		String strTarget = "index2";

		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		 formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
		.toString());
		BillingCancellationTransBSDATA.billDetails(formBean);
		BillingCancellationTransBSDATA.cancelledByCmb(formBean);
		BillingCancellationTransBSDATA.cancelReasonCmb(formBean);

		return mapping.findForward(strTarget);
	}

	/**
	 * This method calls on click of A Bill No. (i.e hyperlink)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward GETPOPUP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillingCancellationTransBSDATA.getPopUp(request, response, formBean);
		return null;
	}

	/**
	 * This method calls if we again click on a Bill No. (i.e hyperlink)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward GETPOPUPDATA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillingCancellationTransBSDATA.getPopUpData(request, response, formBean);
		return null;
	}

	/**
	 * This method calls on SAVE button to cancel a bill and insert the data
	 * into database.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward addData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request, response);

	//	String strTarget = "index";
		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		
		
		BillingCancellationTransBSDATA.insertData(form, request, response);
		
		/*if (formBean.getStrCriteria().equals("1")) {
			BillingCancellationTransBSDATA.insertData(form, request, response);
			//BillingCancellationTransBSDATA.billDetails(formBean);
			BillingCancellationTransBSDATA.cancelledByCmb(formBean);
			BillingCancellationTransBSDATA.cancelReasonCmb(formBean);

			if (formBean.getStrCase().equals("1")) {
				strTarget = "index";
			}
			if (formBean.getStrCase().equals("2")) {
				strTarget = "index2";
			}
		}

		if (formBean.getStrCriteria().equals("2")) {
			BillingCancellationTransBSDATA.insertData(form, request, response);
			BillingCancellationTransBSDATA.cancelledByCmb(formBean);
			BillingCancellationTransBSDATA.cancelReasonCmb(formBean);
			strTarget = "index";

		}

		return mapping.findForward(strTarget);*/
		
		return this.init(mapping, form, request, response);
	}

	/**
	 * This method calls when we click on case 1 (i.e. With CR No.) to show the
	 * jsp page 1
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward CASE1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		BillingCancellationTransBSDATA.cancelledByCmb(formBean);
		BillingCancellationTransBSDATA.cancelReasonCmb(formBean);
		String strTarget = "index";
		return mapping.findForward(strTarget);
	}

	/**
	 * This method calls when we click on case 2 (i.e. WithOut CR No.) to show
	 * the jsp page 2(With Guarantor Name)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward CASE2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String strTarget = "index2";
		BillingCancellationTransBSDATA.cancelledByCmb(formBean);
		BillingCancellationTransBSDATA.cancelReasonCmb(formBean);
		return mapping.findForward(strTarget);

	}

	/**
	 * This method calls on cancel button to show the init page(in start up
	 * folder)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		ActionForward acFwd = new ActionForward();

		acFwd.setPath("/hisglobal/initPage.jsp");

		acFwd.setContextRelative(true);

		return acFwd;

	}
	
	public ActionForward TrfDtl(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
		BillingCancellationTransFB formBean = (BillingCancellationTransFB) form;
		String sid = "";
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		sid = BillingCancellationTransBSDATA.TariffDtl(formBean, request);
		
		response.setHeader("Cache-Control", "no-cache");
		try
		{
			formBean = (BillingCancellationTransFB)form;
			response.getWriter().print(sid);			
		} 
		catch (Exception e) 
		{
			new HisException("Billing","BillingCancellationTransBSCNT","BillingCancellationTransBSCNT.TrfDtl()-->" + e.getMessage());
		}
		return null;
	}
}
