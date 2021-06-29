package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.CertificateMovementFB;
import mrd.transaction.controller.utl.CertificateMovementUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CertificateMovementACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	/** Getting List of Certificate For Movement
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
		CertificateMovementFB fb=(CertificateMovementFB)form;
		WebUTIL.refreshTransState(request);	
		fb.setSelectType(MrdConfig.CERTIFICATE_SELECT_TYPE_CR_NO);
		ControllerUTIL.setSysdate(request);
	//	fb.setCompCheckListId("");
	//	fb.setTempChkValue("");
		fb.setHmode("NEW");
		fb.setTempMode(fb.getHmode());
		//CertificateMovementUTL.getAllUnit(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Getting The List of unit
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward UNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CertificateMovementFB fb=(CertificateMovementFB)form;
		WebUTIL.refreshTransState(request);	
		ControllerUTIL.setSysdate(request);
		CertificateMovementUTL.getAllUnit(fb,request);
	//	fb.setCompCheckListId("");
	//	fb.setTempChkValue("");
		fb.setHmode("UNIT");
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	/** Getting The List of Certificate on the Basis of Cr No or Unit
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward SHOWLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CertificateMovementFB fb=(CertificateMovementFB)form;
		fb.setHmode(fb.getTempMode());
	//	fb.setCompCheckListId("");
	//	fb.setTempChkValue("");
		fb.setAcceptRejectFlag(MrdConfig.CERTIFICATE_ACCEPT);
		boolean flg = true;
		if(fb.getSelectType().equals(MrdConfig.CERTIFICATE_SELECT_TYPE_CR_NO))
		{
			flg = CertificateMovementUTL.getAllCertificateForMoveByCrNo(fb,request);
			//fb.setTempMode("CRNO");
		}	
		else	
			flg = CertificateMovementUTL.getAllCertificateForMoveBydUnit(fb,request);
		
		if(flg)		CertificateMovementUTL.getCheckListForMedNFitCertificate(fb, request);
		return mapping.findForward("NEW");
	}


	/** Saving the Certificate Movement
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
		CertificateMovementFB fb=(CertificateMovementFB)form;
		CertificateMovementUTL.saveCertificateMovement(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	/*public ActionForward GETCHECKLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CertificateMovementFB fb=(CertificateMovementFB)form;
		CertificateMovementUTL.getCheckListForMedNFitCertificate(fb,request);
		return mapping.findForward("CHECKLIST");
	}
	
	public ActionForward CLOSECHECKLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CertificateMovementFB fb=(CertificateMovementFB)form;
		CertificateMovementUTL.setSelectedCheckList(fb,request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward REJECT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CertificateMovementFB fb=(CertificateMovementFB)form;
		CertificateMovementUTL.rejectCertificateHandover(fb,request);
		return this.NEW(mapping, form, request, response);
	}*/
	 
	/** back to initPage
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CertificateMovementFB fb=(CertificateMovementFB)form;
		if(fb.getTempMode().equals("UNIT"))
			return this.NEW(mapping, form, request, response);
		else if(fb.getTempMode().equals("CRNO"))
			return this.NEW(mapping, form, request, response);	
		else 
			return this.FINALCANCEL(mapping, form, request, response);
	}
	
	public  ActionForward FINALCANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	
	/** Changing The Selection Mode
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward CHANGEMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CertificateMovementFB fb=(CertificateMovementFB)form;
		if(fb.getSelectType().equals(MrdConfig.CERTIFICATE_SELECT_TYPE_CR_NO))
			return this.NEW(mapping, form, request, response);
		else
			return this.UNIT(mapping, form, request, response);
	}
	
	/*Action mainly called for pagination*/
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CertificateMovementFB fb = (CertificateMovementFB) form;
		CertificateMovementUTL.setSelectedCertificateId(fb,request);
		fb.setHmode(fb.getTempMode());
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}
}
