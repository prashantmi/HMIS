package opd.transaction.controller.action;

import java.sql.SQLException;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.PatAllergyDtlVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.transaction.controller.fb.EpisodeAllergyFB;
import opd.transaction.controller.fb.OpdNextVisitDetailFB;
import opd.transaction.controller.util.EpisodeAllergiesNewUTIL;
import opd.transaction.controller.util.EpisodeAllergyUTIL;
import opd.transaction.controller.util.OpdNextVisitDetailUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class EpisodeAllergyACTION extends CSRFGardTokenAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}
	
	/** Getting Essential Data for Allergies
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		generateToken(request);
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		fb.reset(mapping,request);
		WebUTIL.getSession(request).removeAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		EpisodeAllergyUTIL.getEssentials(fb,request);	
		EpisodeAllergyUTIL.addAllergyDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Adding New Allergy Type For multirow and getting allergy name based on allergy type
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADDNEWALLERGY (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		EpisodeAllergyUTIL.addAllergyDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Adding allergy name allergy type wise for multirow 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADDDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		EpisodeAllergyUTIL.addDetailRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Deleting Allergy Name Allergy Type Wise From multirow
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward REMOVEDETAIL (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		EpisodeAllergyUTIL.removeAllergyRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	/**Deleting Allergy Type From multirow
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward REMOVEALLERGY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		EpisodeAllergyUTIL.removeAllergyDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Saving Allergy of the patient
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		//EpisodeAllergyUTIL.saveAllergyDetails(fb,request);
		if (EpisodeAllergyUTIL.saveAllergyDetails(fb, request)) //Modified by Vasu on 10.Oct.2018 to Refresh page after save
		{	
			fb.reset(mapping,request);
			WebUTIL.getSession(request).removeAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO);
			EpisodeAllergyUTIL.getEssentials(fb,request);	
			EpisodeAllergyUTIL.addAllergyDetail(fb,request);
			Status objStatus = new Status();
			objStatus.add(Status.TRANSINPROCESS, "", "Allergy Saved Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		//return this.NEW(mapping, form, request, response);
		return mapping.findForward("NEW");
	}
	
	/** Viewing the allergies detail of the given allergy
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward PREVIOUS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		EpisodeAllergyUTIL.getAllergyDtlEpisodeWise(fb,request);
		return mapping.findForward("PREVIOUS");
	}
	
	/** Getting Allergy Site
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ALLERGYSITE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		EpisodeAllergyUTIL.getAllAllergySite(fb,request);
		return mapping.findForward("ALLERGYSITE");
	}	
	
	/** getting Allergy Symptom
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward GETSYMPTOM(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		EpisodeAllergyUTIL.getCommonNAllergyTypeSymptom(fb,request);
		return mapping.findForward("GETSYMPTOM");
	}
	
	/** Adding allergy site of a given allergy name
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADDSITE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		EpisodeAllergyUTIL.addAllergySite(fb,request); 
		return mapping.findForward("ALLERGYSITE");
	}
	
	/**Adding allergy Symptom of a given allergy name
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADDSYMPTOM(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		EpisodeAllergyUTIL.addSymptom(fb,request); 
		return mapping.findForward("GETSYMPTOM");
	}
	
	/** Getting All Allergy Advice list
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADDADVICE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EpisodeAllergyFB fb = (EpisodeAllergyFB) form;
		EpisodeAllergyUTIL.addAdvice(fb,request); 
		return mapping.findForward("ADDADVICE");
	}
	
	/**Populating all the selected advice from popup to the main page 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward SHOWADVICE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("ADDADVICE");
	}
	
	/** Calculating no of days
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward DAYCAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EpisodeAllergyFB fb=(EpisodeAllergyFB)form;
		EpisodeAllergyUTIL.calculateDay(fb,request);
		return mapping.findForward("DAYCAL");
	}
	
	/** Populating the calculated days
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward SHOWDAYS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("DAYCAL");
	}
	
	/** Revoking the allergy
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward REVOKE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EpisodeAllergyFB fb=(EpisodeAllergyFB)form;
		//EpisodeAllergyUTIL.setEssentials(fb, request);
		//EpisodeAllergyUTIL.revokeAllergy(fb,request);
		if (EpisodeAllergyUTIL.revokeAllergy(fb, request)) //Modified by Vasu on 10.Oct.2018 to Refresh page after Revoke
		{
			fb.reset(mapping,request);
			WebUTIL.getSession(request).removeAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO);
			EpisodeAllergyUTIL.getEssentials(fb,request);	
			//EpisodeAllergyUTIL.addAllergyDetail(fb,request);
			Status objStatus = new Status();
			objStatus.add(Status.TRANSINPROCESS, "", "Allergy Revoked Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return this.NEW(mapping,form,request,response);	
	}
	public ActionForward AJX_G_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
{
		System.out.println("test::::::");
		EpisodeAllergyFB fb=(EpisodeAllergyFB)objForm_p;
		EpisodeAllergyUTIL.getEssentials(fb,objRequest_p);
		
		PatAllergyDtlVO[] arrPatAllergyDtlVO = (PatAllergyDtlVO[]) objRequest_p.getSession().getAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO);
	StringBuffer strBuff= new StringBuffer();
	strBuff.append("{header:'Allergies',data:[");
	for(PatAllergyDtlVO vo : arrPatAllergyDtlVO)
	{
		strBuff.append("{header:'* ',value:'" + vo.getAllergyName() + " (" + vo.getAllergyTypeName() + ") "+ vo.getDurationDays() + "'},");
	}
	if(arrPatAllergyDtlVO.length>0)	strBuff.delete(strBuff.length()-1, strBuff.length());
	strBuff.append("]}");
	System.out.println("OpdNextVisitDetailACTION.AJX_G_SUMMARY()   " +strBuff.toString() );
	objResponse_p.setHeader("Cache-Control", "no-cache");
	objResponse_p.getWriter().print(strBuff.toString());
	return null;	
}
	
	
}

