package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.CertificateReceivedFB;
import mrd.transaction.controller.utl.CertificateReceivedUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CertificateReceivedACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	/** Getting the List Of Certificate, Rack & Shelf List
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		CertificateReceivedFB fb=(CertificateReceivedFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		fb.setCertificateReceivedMode(Config.CERTIFICATE_RECEIVED_OPTION);
		CertificateReceivedUTL.getEssentialForCertificateReceived(fb, request);
		return mapping.findForward("NEW");
	}
	
	/** Getting the List Of Shelf On Basis of Rack
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward SHELF(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CertificateReceivedFB fb=(CertificateReceivedFB)form;
		CertificateReceivedUTL.getShelf(fb, request,response);
		return null;
	}
	
	/** Saving the Certificate Received Details
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CertificateReceivedFB fb=(CertificateReceivedFB)form;
		if(CertificateReceivedUTL.saveReceiveCertificate(fb, request))
		{
			WebUTIL.refreshTransState(request);	
			fb.reset(mapping, request);
			fb.setCertificateReceivedMode(Config.CERTIFICATE_RECEIVED_OPTION);
			CertificateReceivedUTL.getEssentialForCertificateReceived(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.LIST,"Certificate Received","");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}
	
	/** Back to initPage
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
}
