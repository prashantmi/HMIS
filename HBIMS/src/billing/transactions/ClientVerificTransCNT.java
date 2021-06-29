package billing.transactions;
import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ClientVerificTransCNT extends DispatchAction
{
	public ClientVerificTransCNT()
	{
		//super(new ClientVerificTransUTL(), "/transactions/ClientVerificTransCNT");
		
	}
	
	 /***********************UNSPECIFIED***************************/
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException

	{
		return this.APPROVED(mapping, form, request, response);
	}

	
   /***********************DISCOUNT APPROVED***************************/

	public ActionForward APPROVED(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
		String target = "clientverification";
        return mapping.findForward(target);
	}
	//////////////////////CLIENTVERIFICATION///////////////////////////
	public ActionForward CLIENTVERIFICATION(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
       
		String target = "report";
		ClientVerificTransFB formBean = (ClientVerificTransFB) form;
		String temp = null;
		temp = formBean.getStrGrName()+"/"+formBean.getStrGRDate();
	    ClientVerificTransDATA.genrateBill(request,formBean);
	    formBean.setStrGrName(temp);
		return mapping.findForward(target);
	}
	////////////////////////////INOVICE//////////////////////////////////
	public ActionForward INVOICE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		String target = "invoice";
		ClientVerificTransFB formBean = (ClientVerificTransFB) form;
		ClientVerificTransDATA.initClientDetail(request.getParameter("chk"),formBean);
        ClientVerificTransDATA.INVOICE(request.getParameter("chk"),formBean);
        
  		return mapping.findForward(target);
	}
	////////////////////////////DEPOSIT//////////////////////////////////
	public ActionForward DEPOSIT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		String target = "deposit";
		ClientVerificTransFB formBean = (ClientVerificTransFB) form;
		ClientVerificTransDATA.initClientDetail(request.getParameter("chk"),formBean);
        ClientVerificTransDATA.DEPOSIT(request.getParameter("chk"),formBean);
        return mapping.findForward(target);
	}
////////////////////////////RENEWEXTEND//////////////////////////////////
	public ActionForward RENEWEXTEND(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		String target = "renew-extend";
		ClientVerificTransFB formBean = (ClientVerificTransFB) form;
		ClientVerificTransDATA.ClientDetail(request.getParameter("chk"),formBean);
        ClientVerificTransDATA.DEPOSIT(request.getParameter("chk"),formBean);
        
  		return mapping.findForward(target);
	}
	/////////////////////////INSERTPRE/////////////////////////////////////
	public ActionForward INSERTPRE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		String target = "PrePaidReport";
        ClientVerificTransFB formBean = (ClientVerificTransFB) form;
        ClientVerificTransDATA.INSERTPRE(formBean);
     	return mapping.findForward(target);
	}
	///////////////////////////INSERTPOST///////////////////////////////////
	public ActionForward INSERTPOST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException {

		String target = "PostPaidReport";
        ClientVerificTransFB formBean = (ClientVerificTransFB) form;
        ClientVerificTransDATA.INSERTPOST(formBean);
     	return mapping.findForward(target);
	}
	///////////////////// AJAX FUNCTION //////////////////////////
	public ActionForward UNITVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException,Exception, SQLException
	{
		ClientVerificTransFB formBean = (ClientVerificTransFB)form;
		String strValmode = (String) request.getParameter("modName");
		formBean.setStrPaymentMode(strValmode);
	    String comboValues = ClientVerificTransDATA.AjaxResponse(formBean);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(comboValues);
	   	
		return null;
      }	
	/////////////////////////UNITVAL1/////////////////////////////
	public ActionForward UNITVAL1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException,Exception, SQLException
	{
		ClientVerificTransFB formBean = (ClientVerificTransFB)form;
		String strValmode = (String) request.getParameter("modName");
		formBean.setStrInvoiceNo(strValmode);
	    String comboValues = ClientVerificTransDATA.InvoiceDtl(formBean);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(comboValues);
	   	
		return null;
      }	
	
	
	
 
}
