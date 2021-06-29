/**
 * 
 */
package mms.transactions.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.transactions.controller.data.LocalPurchaseNewTransDATA;
import mms.transactions.controller.fb.LocalPurchaseNewTransFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * Developer : Tanvi Sappal
 * Version : 1.0
 * Date : 18/June/2009
 */
public class LocalPurchaseNewTransCNT extends DispatchAction {
	
	/**
	 * To display the Item Category Name on the Screen.
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

		LocalPurchaseNewTransFB formBean = (LocalPurchaseNewTransFB)form;
		String target;
		formBean.setStrStoreName(request.getParameter("strStoreName").split("\\^")[0]);
		formBean.setStrItemCategoeryName(request.getParameter("strStoreName").split("\\^")[1]);
		formBean.setStrReqTypeId(request.getParameter("strType"));
		System.out.println("request.getParameter(strType)"+request.getParameter("strType"));
		formBean.setStrStoreId(request.getParameter("storeId"));
		LocalPurchaseNewTransDATA.initialAdd(formBean,request);
		if(formBean.getStrReqTypeId().equals("10") || formBean.getStrReqTypeId().equals("13"))
			target = "drug";
		else
			target = "item";
		return mapping.findForward(target);
	}
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		LocalPurchaseNewTransFB formBean = (LocalPurchaseNewTransFB)form;
		LocalPurchaseNewTransDATA.save(formBean,request,response);
		String strTarget = "printlp";
		return mapping.findForward(strTarget);
		//this.PRINT(mapping, formBean, request, response);
	}
	
	
	
	public ActionForward PRINT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {
		String strTarget = "printlp";
		LocalPurchaseNewTransFB formBean = (LocalPurchaseNewTransFB)form;
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		LocalPurchaseNewTransDATA.print(formBean, request, response);
		return mapping.findForward(strTarget);
		
	}
	
	
	public ActionForward CANCELTODESK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	/*	ActionForward acFwd = new ActionForward();
		acFwd.setPath("/startup/initPage.jsp");
		acFwd.setContextRelative(true);*/
	//	return acFwd;
	//	return this.LIST(mapping, form, request, response);
		
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/mms/transactions/LocalPurchaseDeskCNT.cnt?hmode=unspecified");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	//String strPrintHLP=LocalPurchaseNewTransHLP.getPrintDtl(vo);
	////formBean.setStrPrintDtls(strPrintHLP);
	//formBean.setStrPrintFlag("1");
}
