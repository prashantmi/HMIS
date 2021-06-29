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

/*
 * 

 /***************************Start of program*****************************\

 ## Copyright Information: C-DAC,Noida 
 ## Project Name         : NIMS
 ## Name of Developer    :
 ## Module Name          : Billing
 ## Process/Database Object Name : Receipt Duplicate Print
 ## Purpose              : To take out duplicate print(s) of the bill(s)
 ## Date of Creation     : 19-11-2014
 ## Modification Log     :
 ##Modify Date           :
 ##Reason (CR/PRS)       :
 ##Modify By             :  
 * 
 */
public class BillDupPrintTransCNT extends CSRFGardTokenAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{
		generateToken(request);

		BillDupPrintTransFB formBean = (BillDupPrintTransFB) form;
		formBean.setFilePath("");
		return this.init(mapping, form, request, response);
	}

	/**
	 * (init) Method Used to transfer control for specified target
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{
		generateToken(request);

		String strTarget = "index";

		BillDupPrintTransFB formBean = (BillDupPrintTransFB) form;
		formBean.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillConfigUtil billConfigUtil = new BillConfigUtil(formBean.getStrHosCode());
		formBean.setStrPrintMessageLimit(billConfigUtil.getGeneralPrintMessageLimit());

		BillDupPrintTransDATA.GET_INITIAL_DATA(request, response, formBean);
		
		
		if (billConfigUtil.getGeneralDuplicatePrint().trim().equals("1")) 
		{
			formBean.setStrRcptNo("0");
			strTarget = "index";
		} 
		else 
		{
			strTarget = "receiptPrint";
		}

		return mapping.findForward(strTarget);
	}

	/**
	 * BILLSEARCHPOPUP--> Method Used to generate Bill Search Pop-Up window for
	 * both cases a)with Cr No b) without Cr No
	 */

	public ActionForward BILLSEARCHPOPUP(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, SQLException 
	{
		String strTarget = "popup";
		BillConfigUtil billConfigUtil = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		if (billConfigUtil.getGeneralDuplicatePrint().trim().equals("1")) 
		{
			strTarget = "popup";
		} 
		else 
		{
			strTarget = "popup3";
		}
		
		return mapping.findForward(strTarget);
	}

	/**
	 * (GO)--> Genrate Bill Detail Ajax Function for Pop-Up in View-Bill
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		generateToken(request);

		BillDupPrintTransFB formBean = (BillDupPrintTransFB) form;
		BillDupPrintTransDATA.GO(request, response, formBean);

		return null;
	}

	/**
	 * PRINT --> This Method is Used to Insert Data into Database
	 */
	public ActionForward PRINT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{
		BillDupPrintTransFB formBean = (BillDupPrintTransFB) form;
		formBean.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddr(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		Boolean retValue = BillDupPrintTransDATA.getInsertBillDtls(formBean,request);
		if (retValue) 
		{
			return this.init(mapping, form, request, response);
		}
		return null;
	}

	/**
	 * PRINT --> This Method is Used to UPDATE Data into Database
	  */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{
		BillDupPrintTransFB formBean = (BillDupPrintTransFB) form;
		formBean.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddr(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		Boolean retValue = BillDupPrintTransDATA.getUpdateBillDtls(formBean,request);
		if (retValue) 
		{
			return this.init(mapping, form, request, response);
		}
		return null;
	}

	public ActionForward REPRINT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		BillDupPrintTransDATA.rePrint(request, response);

		return null;

	}

	public ActionForward CASE1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{
		String strTarget = "popup";

		BillConfigUtil billConfigUtil = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		if (billConfigUtil.getGeneralDuplicatePrint().trim().equals("1")) 
		{
			strTarget = "popup";
		}
		else 
		{
			strTarget = "popup3";
		}
		return mapping.findForward(strTarget);
	}

	public ActionForward CASE2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{
		String strTarget = "popup2";

		BillConfigUtil billConfigUtil = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		if (billConfigUtil.getGeneralDuplicatePrint().trim().equals("1")) 
		{
			strTarget = "popup2";
		} 
		else 
		{
			strTarget = "popup4";
		}
		return mapping.findForward(strTarget);
	}

	/**
	 * BILLLISTING --> This Method is Used to generate Bill-Listing popup
	 */

	public ActionForward BILLLISTING(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{

		String target = "billlist";

		BillDupPrintTransFB formBean = (BillDupPrintTransFB) form;

		formBean.setStrBillUsrFuncName(request.getParameter("usrFuncName"));

		return mapping.findForward(target);
	}

	public ActionForward FETCHBILLLISTING(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, SQLException 
	{
		BillDupPrintTransFB formBean = (BillDupPrintTransFB) form;
		formBean.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillDupPrintTransDATA.getBillListingDtls(request, response, formBean);

		return null;
	}

	/**
	 * This method is used to Transfer Control Over Inti Page
	 */
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
	{
		ActionForward acFwd = new ActionForward();

		acFwd.setPath("/hisglobal/initPage.jsp");

		acFwd.setContextRelative(true);

		return acFwd;

	}

}
