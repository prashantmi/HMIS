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

public class RefundApprovalTransBSCNT extends CSRFGardTokenAction {
	public String bsid = "";

	/**This method is used to call REFUND  Method to transfer control  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		generateToken(request);

		return this.REFUND(mapping, form, request, response);

	}

	/**This method is used to call refund Page   
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	
	public ActionForward REFUND(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//generateToken(request);

		String target = "refund";
		
		RefundApprovalTransFB formBean = (RefundApprovalTransFB) form;
		formBean.setFilePath("");
		formBean.setIsOpenPopUp("0");
		
		if(request.getParameter("userValue") !=null){
			
			 formBean.setStrRefundMode(request.getParameter("userValue").toString());
			
		} 
		 
		
		String strOnlineRefundRequestAllowed = new BillConfigUtil(request.getSession().getAttribute("HOSPITAL_CODE").toString()).getGeneralOnlineRefundRequestAllowed();
		
		
		if(formBean.getStrRefundMode().equals("0") && strOnlineRefundRequestAllowed.equals("1") ){
			
			formBean.setStrErrMsg("No Privilege on Refund Approval, Please Contact System Administrator");
			
		}else if(formBean.getStrRefundMode().equals("1") && strOnlineRefundRequestAllowed.equals("0")) {
			
			formBean.setStrErrMsg("No Privilege on Refund Request, Please Contact System Administrator");
			
		}else{
			
			formBean.setStrErrMsg("");
			
		}
		
		
		
		return mapping.findForward(target);
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


	/**This method is Called when User Enter/Select Cr No & press GO
	 * button to get Patient Bill Details & Tariff Details  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	
	public ActionForward GO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//generateToken(request);

		RefundApprovalTransFB formBean = (RefundApprovalTransFB) form;
		
		  
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		
		String strTarget = "refund";
		RefundApprovalTransBSDATA.getRsnRmk(formBean);
		RefundApprovalTransBSDATA.getBillDtls(formBean,request);
		return mapping.findForward(strTarget);
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	
	
	public ActionForward TrfDtl(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
		RefundApprovalTransFB formBean = (RefundApprovalTransFB) form;
		String sid = "";
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		sid = RefundApprovalTransBSDATA.TariffDtl(formBean, request);
		
		response.setHeader("Cache-Control", "no-cache");
		try
		{
			formBean = (RefundApprovalTransFB)form;
			response.getWriter().print(sid);			
		} 
		catch (Exception e) 
		{
			new HisException("Billing","RefundApprovalTransBSCNT","RefundApprovalTransBSCNT.TrfDtl()-->" + e.getMessage());
		}
		return null;
	}
	
	public ActionForward PopUp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String sid = "";

		response.setHeader("Cache-Control", "no-cache");
		try {
			sid = RefundApprovalTransBSDATA.popUpShw(request);
			response.getWriter().print(sid);
		} catch (Exception e) {
			new HisException("Refund Approval Transaction",
					"RefundApprovalTransBSCNT",
					"RefundApprovalTransBSCNT.PopUp()-->" + e.getMessage());
		}
		return null;
	}
	
	
	/**This method calls on SAVE button to cancel a Request and insert the data 
	 * into database.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward ADDDATA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		validateToken(request, response);

		boolean retValue = false ;
		RefundApprovalTransFB formBean = (RefundApprovalTransFB) form;
		 
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		retValue = RefundApprovalTransBSDATA.insertData(form,request, response);
		if(retValue)
		{	
			return mapping.findForward("refund");			   
		}
		else
		{
			return this.unspecified(mapping, form, request, response);
		}	
		
	}
	
}
