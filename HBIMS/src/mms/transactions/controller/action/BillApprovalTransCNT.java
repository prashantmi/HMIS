package mms.transactions.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.transactions.controller.data.BillApprovalTransDATA;
import mms.transactions.controller.fb.BillApprovalTransFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * Developer : Tanvi Sappal
 * Version : 1.0
 * Date : 02/April/2009
 */

public class BillApprovalTransCNT extends DispatchAction {
	
		
	
	/** This method is used to forward the request on first jsp page
	 * And calls the methods getInitialValues() which is define in SupplierReturnReqTransDATA java file. AND LIST VALUES to display combos 
	 * on first page. 
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
	throws HisException, SQLException 
	{
		String strTarget = "billVerify";
		BillApprovalTransFB formBean = (BillApprovalTransFB) form;
		BillApprovalTransDATA.getInitialValues(formBean,request);
		return mapping.findForward(strTarget);
	}

	/** This method is used to display the value of item details  for this
	 *  calls the methods getItemDetails() which is define in SupplierReturnReqTransDATA java file. .
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public ActionForward GET_PO_DETAILS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception, SQLException 
	{
		String strTarget = "billVerify";
		BillApprovalTransFB formBean = (BillApprovalTransFB) form;
		BillApprovalTransDATA.getPODetails(formBean,request,response);
		return mapping.findForward(strTarget);
	}
	
	public ActionForward ScheduleItemDtls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception, SQLException 
	{
		BillApprovalTransFB formBean = (BillApprovalTransFB) form;
		BillApprovalTransDATA.getScheduleItemDtls(formBean,request,response);
		return null;
	}
	/**
	 * This function forward control List pop up window
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
		
		//String strTarget = "list";
	//	BillApprovalTransFB formBean = (BillApprovalTransFB) form;
		return mapping.findForward("list");
	}
	 
	   /**
		 * This function forward control List pop up window
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws HisException
		 * @throws SQLException
		 */
		public ActionForward LISTDTL(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {
			
			BillApprovalTransFB formBean = (BillApprovalTransFB) form;
			//System.out.println("in CNT");
			BillApprovalTransDATA.initSearchList(formBean, request,response);
			return null;
		}
		
		public ActionForward INSERT(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException, SQLException {

			BillApprovalTransFB fb = (BillApprovalTransFB) form;
			fb.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			fb.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			BillApprovalTransDATA.insert(fb, request, response);
			fb.setStrPONo("");
			return init(mapping, form, request, response);
		}
	
		public ActionForward CLEAR(ActionMapping _mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)throws HisException, SQLException 
		{
			BillApprovalTransFB fb = (BillApprovalTransFB) form;
			
			fb.setStrNormalMsg("");
			fb.setStrPONo("");
			return this.init(_mapping, form, request, response);
		}
				
		
		/** This method is used to cancel the Process.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	
		public ActionForward CANCELPAGE(ActionMapping _mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws HisException {
			ActionForward acFwd = new ActionForward();
			acFwd.setPath("/mms/transactions/BillApprovalDeskTransCNT.cnt?hmode=unspecified");
			acFwd.setContextRelative(true);
			return acFwd;
		}
		
		public ActionForward PENELTYDTL(ActionMapping _mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws HisException {
			
			BillApprovalTransFB formBean = (BillApprovalTransFB) form;
			BillApprovalTransDATA.getPeneltyDtl(formBean, request, response);
			return null;
		}
		
	
}
