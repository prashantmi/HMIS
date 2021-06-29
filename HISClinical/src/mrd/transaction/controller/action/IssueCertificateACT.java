package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.IssueCertificateFB;
import mrd.transaction.controller.utl.IssueCertificateUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IssueCertificateACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	/** Action is called at the initial loading of a Page
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
		IssueCertificateFB fb=(IssueCertificateFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		fb.setPatCrNo("");
		IssueCertificateUTL.setSysdate(request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	/**  Getting The Patient Detail & List of Generated Medical Certificate
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		IssueCertificateFB fb=(IssueCertificateFB)form;
		fb.setCertificateType(MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE);
		IssueCertificateUTL.getGeneratedCertificateList(fb,request);
		fb.setHmode("GETPATDTL");
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	/** Getting The List of Certificate on the Basis of Certificate Type
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward CHANGEMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		IssueCertificateFB fb=(IssueCertificateFB)form;
		fb.reset(mapping, request);
		IssueCertificateUTL.getGeneratedCertificateList(fb,request);
		fb.setHmode("CHANGEMODE");
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	/** Showing the Details of a Certificate
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward SHOWCERDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		IssueCertificateFB fb=(IssueCertificateFB)form;
		IssueCertificateUTL.getMedicalCertificateDtl(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Saving the Issue Certificate Detail
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward SAVEISSUECER(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		validateToken(request,response);
		IssueCertificateFB fb=(IssueCertificateFB)form;
		IssueCertificateUTL.saveCertificateIssueDtl(fb,request);
		fb.setPatCrNo("");
		return this.NEW(mapping, form, request, response);
	}
	
	/** Back to The initPage
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public  ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		IssueCertificateFB fb=(IssueCertificateFB)form;
		
		if(fb.getTempMode().equals("GETPATDTL"))
			return this.NEW(mapping, form, request, response);
		else if(fb.getTempMode().equals("CHANGEMODE"))
			return this.NEW(mapping, form, request, response);
		else
			return this.FINALCANCEL(mapping, form, request, response);
	}
	
	public  ActionForward FINALCANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
