/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : MRD
 ## Process/Database Object Name	    : UNIT WISE ESTIMATE PROCEDURE MAPPING MASTER
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/


package mrd.masters.controller.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.UnitWiseEstProcedureMappingMstFB;
import mrd.masters.controller.util.UnitWiseEstProcedureMappingMsUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
public class UnitWiseEstProcedureMappingMstACT extends CSRFGardTokenAction
{
	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	public ActionForward unspecified(ActionMapping mapping,ActionForm _form, HttpServletRequest _request,HttpServletResponse _response) throws Exception {
		
		return this.ADD(mapping, _form, _request, _response);
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
	public ActionForward ADD(ActionMapping mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		UnitWiseEstProcedureMappingMstFB _fb=(UnitWiseEstProcedureMappingMstFB)_form;
		UnitWiseEstProcedureMappingMsUTL.getProcedureUnitListEssential(_fb, _request);
		_fb.setTempMode(_fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	/**
	 * save Procedure Unit Mapping Detail Data
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		UnitWiseEstProcedureMappingMstFB fb=(UnitWiseEstProcedureMappingMstFB)_form;
		UnitWiseEstProcedureMappingMsUTL.saveProcedureUnitList(fb, _request);
		UnitWiseEstProcedureMappingMsUTL.getProcedureUnitListEssential(fb, _request);
		fb.reset(mapping, _request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitWiseEstProcedureMappingMstFB fb=(UnitWiseEstProcedureMappingMstFB)form;
		UnitWiseEstProcedureMappingMsUTL.getProcedureUnitListForModify(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitWiseEstProcedureMappingMstFB fb=(UnitWiseEstProcedureMappingMstFB)form;
		UnitWiseEstProcedureMappingMsUTL.modifySaveProcedureList(fb,request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("LIST"); 
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UnitWiseEstProcedureMappingMstFB fb=(UnitWiseEstProcedureMappingMstFB)form;
		UnitWiseEstProcedureMappingMsUTL.getProcedureUnitListForModify(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	

}
