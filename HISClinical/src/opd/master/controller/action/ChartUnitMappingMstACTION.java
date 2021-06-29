package opd.master.controller.action;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ChartUnitMappingMstFB;
import opd.master.controller.util.ChartUnitMappingMstUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ChartUnitMappingMstACTION extends CSRFGardTokenAction
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
		ChartUnitMappingMstFB _fb=(ChartUnitMappingMstFB)_form;
		ChartUnitMappingMstUTIL.getChartUnitListEssential(_fb, _request);
		_fb.setTempMode(_fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	/**
	 * save Chart Unit Mapping Detail Data
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		ChartUnitMappingMstFB fb=(ChartUnitMappingMstFB)_form;
		ChartUnitMappingMstUTIL.saveChartUnitList(fb, _request);
		ChartUnitMappingMstUTIL.getChartUnitListEssential(fb, _request);
		fb.reset(mapping, _request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ChartUnitMappingMstFB fb=(ChartUnitMappingMstFB)form;
		ChartUnitMappingMstUTIL.getChartUnitListForModify(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ChartUnitMappingMstFB fb=(ChartUnitMappingMstFB)form;
		ChartUnitMappingMstUTIL.modifySaveChartList(fb,request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("LIST"); 
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ChartUnitMappingMstFB fb=(ChartUnitMappingMstFB)form;
		ChartUnitMappingMstUTIL.getChartUnitListForModify(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	

}
