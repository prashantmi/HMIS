/**
 * 
 */
package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.OpdIcdMappingMasterFB;
import opd.master.controller.util.OpdIcdMappingMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author ashas
 *
 */
public class OpdIcdMappingMasterACTION extends CSRFGardTokenAction
{
	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);
		return this.ADD(mapping, form, request, response);
	}
	/**
	 * action called after click on ADD
	 * get All essential field  
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB) form;
		WebUTIL.refreshTransState(request);
		OpdIcdMappingMasterUTIL.getEssential(fb, request);
		return mapping.findForward("NEW");
	}
	/**
	 * action called for populating SubGroup Combo
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward GETSUBGROUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB) form;
		OpdIcdMappingMasterUTIL.getSubGroup(fb, request);
		return mapping.findForward("NEW");
	}
	/**
	 * action called for populating SubGroup Combo in MODIFY mode
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "MODIFY"
	 */
	public ActionForward GETMODSUBGROUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB) form;
		OpdIcdMappingMasterUTIL.getModSubGroup(fb, request);
		return mapping.findForward("MODIFY");
	}
	/**
	 * action called for getting Disease List
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward GETICDDISEASE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB) form;
		OpdIcdMappingMasterUTIL.getIcdDisease(fb, request);
		return mapping.findForward("NEW");
	}
	/**
	 * action called for getting Mapped Disease List
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "MAPPEDDISEASE"
	 */
	public ActionForward MAPPEDDISEASE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB)form;
		return mapping.findForward("MAPPEDDISEASE");
	}
	/**
	 * Action Called for Saving ICD Mapping
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB) form;
		OpdIcdMappingMasterUTIL.saveIcdMapping(fb, request);
		fb.reset(mapping, request);
		WebUTIL.refreshTransState(request);
		OpdIcdMappingMasterUTIL.getEssential(fb, request);
		return mapping.findForward("NEW");
	}
	/**
	 * Action Called After Click on Modify
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return action forwards to the output view called "MODIFY"
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB) form;
		WebUTIL.refreshTransState(request);
		OpdIcdMappingMasterUTIL.getIcdMappingForModify(fb, request);
		return mapping.findForward("MODIFY");
	}
	/**
	 * action called for getting Not Mapped Disease List
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "MODIFY"
	 */
	public ActionForward GETMODICDDISEASE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB) form;
		OpdIcdMappingMasterUTIL.getModIcdDisease(fb, request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward DELETEROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB) form;
		OpdIcdMappingMasterUTIL.deleteRow(fb,request);
		return mapping.findForward("MAPPEDDISEASE"); 
	}
	/**
	 * Action Called for Saving Modified ICD Mapping
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return action forwards to the output view called "LIST"
	 * @throws Exception 
	 */
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB) form;
		OpdIcdMappingMasterUTIL.modifySaveIcdMapping(fb,request);
		WebUTIL.refreshTransState(request);
		return mapping.findForward("LIST"); 
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdIcdMappingMasterFB fb = (OpdIcdMappingMasterFB) form;
		OpdIcdMappingMasterUTIL.getIcdMappingForModify(fb,request);
		return mapping.findForward("MODIFY");
	}
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		 WebUTIL.refreshTransState(request);
			return mapping.findForward("LIST");
		}
}
