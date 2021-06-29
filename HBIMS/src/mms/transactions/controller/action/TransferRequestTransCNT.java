package mms.transactions.controller.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.exceptions.HisException;
import hisglobal.transactionutil.GenericController;
import mms.transactions.controller.data.TransferRequestTransDATA;
import mms.transactions.controller.fb.TransferRequestTransFB;
import mms.transactions.controller.utl.TransferRequestTransUTL;

public class TransferRequestTransCNT extends GenericController {

	public TransferRequestTransCNT() {
		super(new TransferRequestTransUTL(), "/transactions/TransferRequestTransCNT");
	}

	/**
	 * forwards control to the ADD page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward GENERATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
		TransferRequestTransDATA.initialAdd(formBean, request);
		
		
		String strtarget = "generateRequest";
		return mapping.findForward(strtarget);

	}

	/**
	 * forwards control to the ADD page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
		formBean.setStrChk(request.getParameter("chk"));
		TransferRequestTransDATA.initModify(formBean, request);

		String strtarget = "modifyRequest";
		return mapping.findForward(strtarget);

	}

	/**
	 * Invoked by Ajax Functions and sets the required Item Name Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward SUBGRPNAMECMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
		TransferRequestTransDATA.subGroupCombo(formBean, request, response);
		return null;
	}

	/**
	 * Invoked by Ajax Functions and sets the required Item Brand Name Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward ITEMBRANDNAME(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
		TransferRequestTransDATA.itemBrandName(formBean, request, response);
		return null;
	}

	/**
	 * Invoked by Ajax Functions and sets the required Item Brand Name Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward TRANSFERDTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
		TransferRequestTransDATA.transferDtl(formBean, request, response);
		return null;
	}

	/**
	 * Invoked by Ajax Functions and sets the required Item Brand Name Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward TRANSFERITEMDTL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {
		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
		TransferRequestTransDATA.transferItem(formBean, request, response);
		return null;
	}

	/**
	 * To add datab For Existing Batch in Current Stock.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 * @throws SQLException
	 */

	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		saveToken(request);

		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		TransferRequestTransDATA.insert(formBean);
		return this.GENERATE(mapping, form, request, response);
	}

	/**
	 * To add datab For Existing Batch in Current Stock.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 * @throws SQLException
	 */

	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		saveToken(request);
		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
		formBean.setStrChk(request.getParameter("strModifyChk"));
		formBean.setStrStoreId(request.getParameter("strTmpStoreId"));
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		TransferRequestTransDATA.update(formBean, request, response);
		return this.LIST(mapping, form, request, response);
	}

	/**
	 * forwards control to the ADD page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward VIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
		formBean.setStrChk(request.getParameter("chk"));
		
		if(request.getParameter("strRequestNo") != null && request.getParameter("strRequestNo").trim().length() > 0){
			
			formBean.setStrChk(request.getParameter("strRequestNo"));				
		}
        if(request.getParameter("strStoreId") != null && request.getParameter("strStoreId").trim().length() > 0){
			
			formBean.setStrStoreId(request.getParameter("strStoreId"));				
		}
        else
        {
        	String strStoreId = formBean.getCombo()[0];	
        	formBean.setStrStoreId(strStoreId);
        	
        }		
		TransferRequestTransDATA.initView(formBean, request);
		formBean.setStrChk(request.getParameter("chk"));
		String strtarget = "viewRequest";
		return mapping.findForward(strtarget);

	}
	
	/**
	 * forwards control to the ADD page.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */

	public ActionForward ORDER_VIEW(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{

		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
        formBean.setStrOrderNo(request.getParameter("strOrderNo"));
		TransferRequestTransDATA.initOrderView(formBean, request);
		formBean.setStrChk(request.getParameter("chk"));
		String strtarget = "orderView";
		return mapping.findForward(strtarget);

	}
	

	/**
	 * is used to forward control to modify page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward REMOVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		boolean retValue = false;

		TransferRequestTransFB formBean = (TransferRequestTransFB) form;
		formBean.setStrChk(request.getParameter("chk"));
		retValue = TransferRequestTransDATA.CancelRecord(request, formBean);
		if (retValue) {
			return this.LIST(mapping, form, request, response);
		} else {
			return this.LIST(mapping, form, request, response);
		}

	}

	public ActionForward RETURNTODESK(ActionMapping _mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		return this.LIST(_mapping, form, request, response);
	}

	public ActionForward CANCELTOLIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		ActionForward acFwd = new ActionForward();
		String strPath = "";
		if (request.getParameter("strPath") != null) {
			strPath = request.getParameter("strPath").concat("?hmode=CANCEL");
			acFwd.setPath(strPath);
			acFwd.setContextRelative(true);

		}
		return acFwd;
	}
}
