package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.PatAllergyDtlVO;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.transaction.controller.fb.EpisodeAllergyNewFB;
import opd.transaction.controller.util.EpisodeAllergiesNewUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EpisodeAllergiesNewACT extends CSRFGardTokenAction {
	
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
		EpisodeAllergyNewFB fb = (EpisodeAllergyNewFB) form;
		fb.reset(mapping,request);
		WebUTIL.getSession(request).removeAttribute(OpdConfig.ARRAY_EPISODE_ALLERGY_VO);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		EpisodeAllergiesNewUTIL.getEssentials(fb,request);		
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
		EpisodeAllergyNewFB fb = (EpisodeAllergyNewFB) form;
		EpisodeAllergiesNewUTIL.addDetailRow(fb,request);
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
		EpisodeAllergyNewFB fb = (EpisodeAllergyNewFB) form;
		EpisodeAllergiesNewUTIL.removeAllergyRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	/**Deleting Allergy Type From multirow
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward REMOVEALLERGY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		EpisodeAllergyNewFB fb = (EpisodeAllergyNewFB) form;
		EpisodeAllergiesNewUTIL.removeAllergyDetail(fb,request);
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
		EpisodeAllergyNewFB fb = (EpisodeAllergyNewFB) form;
		EpisodeAllergiesNewUTIL.saveAllergyDetails(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	/** Viewing the allergies detail of the given allergy
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward PREVIOUS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		EpisodeAllergyNewFB fb = (EpisodeAllergyNewFB) form;
		EpisodeAllergiesNewUTIL.getAllergyDtlEpisodeWise(fb,request);
		return mapping.findForward("PREVIOUS");
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
		EpisodeAllergyNewFB fb=(EpisodeAllergyNewFB)form;
		//EpisodeAllergiesNewUTIL.setEssentials(fb, request);
		EpisodeAllergiesNewUTIL.revokeAllergy(fb,request);
		return this.NEW(mapping,form,request,response);	
	}
	
	public ActionForward ADDADVICE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EpisodeAllergyNewFB fb = (EpisodeAllergyNewFB) form;
		EpisodeAllergiesNewUTIL.addAdvice(fb,request); 
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
		EpisodeAllergyNewFB fb=(EpisodeAllergyNewFB)form;
		EpisodeAllergiesNewUTIL.calculateDay(fb,request);
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
	
	
	public ActionForward AJX_G_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
{
		System.out.println("test::::::");
		EpisodeAllergyNewFB fb=(EpisodeAllergyNewFB)objForm_p;
		EpisodeAllergiesNewUTIL.getEssentials(fb,objRequest_p);
		
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

