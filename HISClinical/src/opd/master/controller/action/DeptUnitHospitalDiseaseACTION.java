/**
 * 
 */
package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.DeptUnitHospitalDiseaseFB;
import opd.master.controller.util.DeptUnitHospitalDiseaseUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author ashas
 *
 */
public class DeptUnitHospitalDiseaseACTION extends CSRFGardTokenAction{
	
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
		DeptUnitHospitalDiseaseFB _fb = (DeptUnitHospitalDiseaseFB) _form;
		WebUTIL.refreshTransState(_request);
		DeptUnitHospitalDiseaseUTIL.getUnitListEssential(_fb, _request);
		_fb.setTempMode(_fb.getHmode());
		return mapping.findForward("NEW");
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
		DeptUnitHospitalDiseaseFB _fb = (DeptUnitHospitalDiseaseFB) _form;
		DeptUnitHospitalDiseaseUTIL.saveDeptUnitHosDiseaseMapping(_fb, _request);
		DeptUnitHospitalDiseaseUTIL.getUnitListEssential(_fb, _request);
		_fb.reset(mapping, _request);
		_fb.setHmode(_fb.getTempMode());
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
		DeptUnitHospitalDiseaseFB _fb = (DeptUnitHospitalDiseaseFB) _form;
		WebUTIL.refreshTransState(_request);
		DeptUnitHospitalDiseaseUTIL.getUnitWiseMappedDiseasForModify(_fb, _request);
		_fb.setTempMode(_fb.getHmode());
		return mapping.findForward("NEW");
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
		DeptUnitHospitalDiseaseFB _fb = (DeptUnitHospitalDiseaseFB) form;
		DeptUnitHospitalDiseaseUTIL.modifySaveDeptUnitHosDiseaseMapping(_fb,request);
		_fb.setTempMode(_fb.getHmode());
		return mapping.findForward("LIST"); 
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DeptUnitHospitalDiseaseFB _fb = (DeptUnitHospitalDiseaseFB) form;
		DeptUnitHospitalDiseaseUTIL.getUnitWiseMappedDiseasForModify(_fb,request);
		return mapping.findForward("NEW");
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
