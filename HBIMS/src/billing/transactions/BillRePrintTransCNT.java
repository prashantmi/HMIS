package billing.transactions;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import billing.BillConfigUtil;
import hisglobal.presentation.CSRFGardTokenAction;


public class BillRePrintTransCNT extends CSRFGardTokenAction 
{
	/**
	 * unspecified
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */	
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		return this.init(mapping, form, request, response);
	}
	/**
	 * (init) Method Used to transfer control 
	 * for specified target
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */		 
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		generateToken(request);
		BillRePrintTransFB formBean = (BillRePrintTransFB) form;
		
		BillConfigUtil billConfigUtil = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrPrintMessageLimit(billConfigUtil.getGeneralPrintMessageLimit());
		 		
		
		String strTarget = "index";
		return mapping.findForward(strTarget);
	}
	
	/**
	 * BILLSEARCHPOPUP--> Method Used to generate 
	 * Bill Search Pop-Up window for both cases
	 * a)with Cr No b) without Cr No
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */		
	
	public ActionForward BILLSEARCHPOPUP(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, SQLException
	{
       	String strTarget = "popup";
       	return mapping.findForward(strTarget);
	}
	/**
	 * (GO)--> Genrate Bill Detail
	 * Ajax Function for Pop-Up in View-Bill 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		generateToken(request);
		BillRePrintTransFB formBean = (BillRePrintTransFB) form;
		BillRePrintTransDATA.GO(request,response,formBean);
		return null;
	}
	
	/**
	 * (UPDATEPRINTSTATUS)--> Genrate Bill Print Status
	 * Ajax Function for Pop-Up in View-Bill 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */	
	public ActionForward UPDATEPRINTSTATUS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException 
    {
		BillRePrintTransDATA.updatePrintStatus(request, response);
		return null;

	}


	public ActionForward REPRINT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		BillRePrintTransDATA.rePrint(request, response);

		return null;

	}
	          
	
	
	
	
	/**
	 * PRINT --> This Method is Used to Insert Data into
	 * Database  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward PRINT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
	//	String strTarget = "index";
		BillRePrintTransFB formBean = (BillRePrintTransFB) form;
		formBean.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrIpAddr(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		Boolean retValue = BillRePrintTransDATA.getInsertBillDtls(formBean , request);     
		if(retValue)
		{	
			//return mapping.findForward(strTarget);
			return this.init(mapping, form, request, response);
		}
		return null;
	}
	
	public ActionForward CASE1(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, SQLException 
	{
		String strTarget = "popup";
		return mapping.findForward(strTarget);
	}
	
	public ActionForward CASE2(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, SQLException
	{
		String strTarget = "popup2";
		return mapping.findForward(strTarget);
	}
	
	/**
	 * BILLLISTING --> This Method is Used to generate 
	 * Bill-Listing popup  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	
	
	public ActionForward BILLLISTING(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{

		String target = "billlist";

		BillRePrintTransFB formBean = (BillRePrintTransFB) form;
		
		formBean.setStrBillUsrFuncName(request.getParameter("usrFuncName"));
		
		return mapping.findForward(target);
	}
	
	public ActionForward FETCHBILLLISTING(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		
		//String hosCode = "108";
		BillRePrintTransFB formBean = (BillRePrintTransFB) form;
		formBean.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		BillRePrintTransDATA.getBillListingDtls(request, response, formBean);
		
		return null;
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
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

        ActionForward acFwd = new ActionForward();

          acFwd.setPath("/hisglobal/initPage.jsp");

          acFwd.setContextRelative(true);

          return acFwd;

    }

}
