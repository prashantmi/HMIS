package mortuary.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.fb.DeceasedAcceptanceFB;
import mortuary.transaction.controller.utl.DeceasedAcceptanceUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeceasedAcceptanceACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
		 
	/**Getting The List of Dead Patient Send To Mortuary
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	{
		generateToken(request);
		DeceasedAcceptanceFB fb=(DeceasedAcceptanceFB)form;
		WebUTIL.refreshTransState(request);
		DeceasedAcceptanceUTL.getInHouseDeadPatientList(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	/** Getting The Deceased Detail
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward DECEASEDDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeceasedAcceptanceFB fb=(DeceasedAcceptanceFB)form;
		fb.setHandoverStorageFlag(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER);
		fb.setStorageFlag(MortuaryConfig.NORMAL_BODY_HANDOVER_OR_STORAGE_FLAG_STREACHER);
		DeceasedAcceptanceUTL.getDeceasdDetail(fb,request);
		return mapping.findForward("NEW");
	}	
	
	
	/**Getting Rack on the Basis of Chamber
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward GETRACK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeceasedAcceptanceFB fb=(DeceasedAcceptanceFB)form;
		DeceasedAcceptanceUTL.getRackBasedOnChamber(fb,request);
		return mapping.findForward("NEW");
	}	
	
	
	/**Saving Deceased Detail
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeceasedAcceptanceFB fb=(DeceasedAcceptanceFB)form;
		String deceasedNo = DeceasedAcceptanceUTL.saveDeceasedDetail(fb,request);
		if(deceasedNo!=null && !deceasedNo.equals(""))
		{
			WebUTIL.refreshTransState(request);
			DeceasedAcceptanceUTL.getInHouseDeadPatientList(fb,request);
			Status objStatus = new Status();
			objStatus.add(Status.DONE,"","Body Accepted Successfully with Deceased No. :: " + deceasedNo);
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}	
	
	/** Getting Police Verification Detail 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward SHOWEXISTING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DeceasedAcceptanceFB fb=(DeceasedAcceptanceFB)form;
		DeceasedAcceptanceUTL.getPoliceVerificationDetail(fb,request);
		return mapping.findForward("POLICE");
	}
	
	/** Setting Existing Police Verification Detail
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward EXISTPOLICEVER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeceasedAcceptanceFB fb=(DeceasedAcceptanceFB)form;
		DeceasedAcceptanceUTL.setExistingPoliceVerification(fb,request); 
		return mapping.findForward("POLICE");
	}
	
	
	/**Adding The Patient Image
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward REFRESHFORIMAGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeceasedAcceptanceFB fb=(DeceasedAcceptanceFB)form;
		fb.setTempChkValue("");
		DeceasedAcceptanceUTL.addNewImage(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	/** Removing The Newly Added Image
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward REMOVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeceasedAcceptanceFB fb=(DeceasedAcceptanceFB)form;
		fb.setTempChkValue("");
		DeceasedAcceptanceUTL.removeNewImage(fb,request);
		return mapping.findForward("NEW");
	}
	
	 
	/** Back To The Previous Page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	
	/** Back To the Init Page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward FINALCANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
