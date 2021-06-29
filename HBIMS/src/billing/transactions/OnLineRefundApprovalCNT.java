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

public class OnLineRefundApprovalCNT extends CSRFGardTokenAction 
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
		//generateToken(request);
  
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

		String strTarget = "index";
		OnLineRefundApprovalFB formBean = (OnLineRefundApprovalFB) form;
	
		formBean.setStrHosCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
//		formBean.setStrIpAddr(request.getSession().getAttribute("IP_ADDR").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		OnLineRefundApprovalDATA.getOnLineRefundApprovalDtls(formBean,request);     
//		if(retValue)
//		{	
//			return this.init(mapping, form, request, response);
//		}
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
	
	public ActionForward SAVE(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, SQLException
	{		System.out.println("============>>>>>>>>>>>>inside save method before token validate");
		validateToken(request, response);
		System.out.println("============>>>>>>>>>>>>inside save method after token validate");
		OnLineRefundApprovalFB fb = (OnLineRefundApprovalFB) form;
		boolean retValue = false;	
	   	retValue = OnLineRefundApprovalDATA.SAVE(fb, request);
	
	    if (retValue)
	    	return this.unspecified(mapping, fb, request, response);
		else
			return this.CANCEL(mapping, form, request, response);
	
		       	
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
	public ActionForward SEARCH(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		
		OnLineRefundApprovalFB formBean = (OnLineRefundApprovalFB) form;
		OnLineRefundApprovalDATA.SEARCH(request,response,formBean);
		return null;
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
		//generateToken(request);

		OnLineRefundApprovalFB formBean = (OnLineRefundApprovalFB) form;
		OnLineRefundApprovalDATA.GO(request,response,formBean);
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
