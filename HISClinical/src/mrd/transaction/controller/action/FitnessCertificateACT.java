package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.FitnessCertificateFB;
import mrd.transaction.controller.utl.FitnessCertificateUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class FitnessCertificateACT extends CSRFGardTokenAction
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
		FitnessCertificateFB fb=(FitnessCertificateFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		fb.setGenerationMode(Config.MEDICAL_CERTIFICATE_GENERATION);
		FitnessCertificateUTL.setSysdate(request);
		return mapping.findForward("NEW");
	}
	
	/** Getting The Patient Detail & All Episode of the Patient
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		FitnessCertificateFB fb=(FitnessCertificateFB)form;
		FitnessCertificateUTL.getAllEpisodeOfThePatient(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Getting The List of Medical Certificate
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETMCDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		FitnessCertificateFB fb=(FitnessCertificateFB)form;
		FitnessCertificateUTL.getMCListOfThePat(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Getting the Fitness Date Based On Medical Certificate
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETFITNESSDATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		FitnessCertificateFB fb=(FitnessCertificateFB)form;
		FitnessCertificateUTL.showFitnessDate(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Saving The Fitness Date 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		validateToken(request,response);
		FitnessCertificateFB fb=(FitnessCertificateFB)form;
		FitnessCertificateUTL.saveFitnessDate(fb,request);
		return this.NEW(mapping, form, request, response);
	}	
}
