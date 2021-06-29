package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ChartParameterMappingFB;
import opd.master.controller.util.ChartParameterMappingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class ChartParameterMappingACTION extends CSRFGardTokenAction
{
	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action "NEW"
	 */
	public ActionForward unspecified(ActionMapping _mapping,ActionForm _form, HttpServletRequest _request,HttpServletResponse _response) throws Exception 
	{
		return this.NEW(_mapping, _form, _request, _response);
	}
	
	/**
	 * action called at the initial loading of a page or when a form is reset
	 * refreshes Transaction State
	 * get All essential field  
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward NEW(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request,HttpServletResponse _response) throws Exception 
	{
		generateToken(_request);
		WebUTIL.refreshTransState(_request);
		ChartParameterMappingFB fb=(ChartParameterMappingFB)_form;
		fb.reset(_mapping, _request);
		ChartParameterMappingUTIL.getEssentials(fb,_request);
		return _mapping.findForward("NEW");
	}
	
	/**
	 * Action after selecting chart 
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls the action "NEW"
	 */
	public ActionForward GETPARA(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
	{
		ChartParameterMappingFB fb = (ChartParameterMappingFB) _form;
		ChartParameterMappingUTIL.getParameters(fb, _request);
		return _mapping.findForward("NEW");
	}

	/**
	 * save Chart Parameter Detail Data
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward SAVE(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)throws Exception
	{
		validateToken(_request,_response);
		ChartParameterMappingFB fb = (ChartParameterMappingFB) _form;
		ChartParameterMappingUTIL.saveChartParaMapping(fb, _request);
		fb.reset(_mapping, _request);
		return _mapping.findForward("NEW");
	}

	/**
	 * After CANCEl action
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return action forwards to the output view called "NEW"
	 */	
	public ActionForward CANCEL(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse response)
	{
		WebUTIL.refreshTransState(_request);
		return _mapping.findForward("CANCEL");
	}	
}
