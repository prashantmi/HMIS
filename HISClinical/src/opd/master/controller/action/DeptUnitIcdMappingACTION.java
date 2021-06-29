package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.ChartUnitMappingMstFB;
import opd.master.controller.fb.DeptUnitIcdMappingFB;
import opd.master.controller.util.ChartUnitMappingMstUTIL;
import opd.master.controller.util.DeptUnitIcdMappingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeptUnitIcdMappingACTION extends CSRFGardTokenAction
{
	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm _form, HttpServletRequest _request,HttpServletResponse _response) throws Exception {
		
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
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) _form;
//		WebUTIL.refreshTransState(_request);
//		_fb.reset(mapping, _request);
//		_fb.setHmode("NEW");
		DeptUnitIcdMappingUTIL.getUnitListEssential(_fb, _request);
		return mapping.findForward("NEW");
	}
	/**
	 * action called after click on Next in Add Page
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "NEXT"
	 */
	public ActionForward NEXT(ActionMapping mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) _form;
		DeptUnitIcdMappingUTIL.getEssential(_fb, _request);
		return mapping.findForward("NEXT");
	}
	/**
	 * action called for populating SubGroup Combo
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "NEXT"
	 */
	public ActionForward GETSUBGROUP(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) _form;
		DeptUnitIcdMappingUTIL.getSubGroup(_fb, _request);
		return _mapping.findForward("NEXT");
		
	}
	
	public ActionForward GETMODSUBGROUP(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) _form;
		DeptUnitIcdMappingUTIL.getSubGroup(_fb, _request);
		return _mapping.findForward("MODIFY");
		
	}
	/**
	 * action called for getting Disease List
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "NEXT"
	 */
	public ActionForward GETDISEASE(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) _form;
		DeptUnitIcdMappingUTIL.getDisease(_fb, _request);
		return _mapping.findForward("NEXT");
		
	}
	public ActionForward GETMODDISEASE(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) _form;
		DeptUnitIcdMappingUTIL.getModDisease(_fb, _request);
		return _mapping.findForward("MODIFY");
		
	}
	public ActionForward MAPPEDDISEASE(ActionMapping _mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) _form;
//		DeptUnitIcdMappingUTIL.getMappedDisease(_fb, _request);
		return _mapping.findForward("MAPPEDDISEASE");
		
	}
	/**
	 * Action Called for Saving DeptIcdMapping
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return action forwards to the output view called "NEW"
	 */
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) _form;
		DeptUnitIcdMappingUTIL.saveDeptUnitIcdMapping(_fb, _request);
		DeptUnitIcdMappingUTIL.getUnitListEssential(_fb, _request);
		return mapping.findForward("NEW");
	}
	
	/**
	 * Action Called after click on Modify
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return action forwards to the output view called "NEXT"
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm _form, HttpServletRequest _request, HttpServletResponse _response)
	{
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) _form;
		WebUTIL.refreshTransState(_request);
		
		DeptUnitIcdMappingUTIL.getDeptUnitIcdForModify(_fb, _request);

		return mapping.findForward("MODIFY");
	}
	/**
	 * Action Called for Saving Modified DeptIcdMapping
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
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) form;
		DeptUnitIcdMappingUTIL.modifySaveDeptIcdMapping(_fb,request);
		return mapping.findForward("LIST"); 
	}
	public ActionForward DELETEROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) form;
		DeptUnitIcdMappingUTIL.deleteRow(_fb,request);
		return mapping.findForward("MAPPEDDISEASE"); 
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeptUnitIcdMappingFB _fb = (DeptUnitIcdMappingFB) form;
		DeptUnitIcdMappingUTIL.getDeptUnitIcdForModify(_fb,request);
		return mapping.findForward("MODIFY");
	}
	/**
	 * Action Called after Click on CANCEL
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return action forwards to the output view called "LIST"
	 */
	 public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		 WebUTIL.refreshTransState(request);
			return mapping.findForward("LIST");
		}

}
