package mortuary.masters.controller.action;



import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL; 
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.masters.controller.hlp.ExternalLabTestMasterFB;
import mortuary.masters.controller.hlp.ExternalLabTestMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;



public class ExternalLabTestMasterACT extends CSRFGardTokenAction
{
	
	/**
	 * the action IS CALLED BY DEFAULT AND IT REDIRECTS TO ADD 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		
		return this.ADD(mapping, form, request, response);		
	}
	
	
	/**
	 * the action IS CALLED for add page
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		Status status = new Status();
		status.add(Status.NEW);
		ExternalLabTestMasterFB _fb = (ExternalLabTestMasterFB) form;
		
		WebUTIL.refreshTransState(_request); 
//		_fb.reset(mapping, _request);
		
		ControllerUTIL.setSysdate(_request);
		WebUTIL.setStatus(_request, status);
			
		return mapping.findForward("NEW");
	}

	
	/**
	 * the action is called for saving data
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 * @throws Exception 
	 */
	//*
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		ExternalLabTestMasterFB _fb = (ExternalLabTestMasterFB) form;
		boolean hasFlag = ExternalLabTestMasterUTL.saveExternalLabTestDetails(_fb, _request);
		//WebUTIL.refreshTransState(_request);
		if (hasFlag)
		{
			_fb.reset(mapping, _request);
		}
		return mapping.findForward("NEW");
		
		//return mapping.findForward(_fb.getHmode());
	}
	
	
	
	/**
	 * the action IS CALLED for going to Modify page
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		ExternalLabTestMasterFB _fb = (ExternalLabTestMasterFB) form;
	
		ExternalLabTestMasterUTL.getExternalLabTestDetails(_fb, _request);
		return mapping.findForward("NEW");
	}

	/**
	 * the action IS CALLED for going to save in case of Modify page
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 * @throws Exception 
	 */
	//*
	
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		ExternalLabTestMasterFB _fb = (ExternalLabTestMasterFB) form;
		//WebUTIL.refreshTransState(_request);
		boolean flag=ExternalLabTestMasterUTL.updateExternalLabTestDetails(_fb, _request);
		
		if(flag)
			return mapping.findForward(_fb.getHmode());
		else
			return mapping.findForward("NEW");
		
	}

	
	/**
	 * the action IS CALLED for going to view  in case of view page
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "ADD"
	 */
	//*
	
	
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		ExternalLabTestMasterFB _fb = (ExternalLabTestMasterFB) form;
		//WebUTIL.refreshTransState(_request);
		ExternalLabTestMasterUTL.getExternalLabTestDetails(_fb, _request);
		return mapping.findForward("NEW");
		
	}
	
	
	/**
	 * the action called when you want to get to the Listing Page
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param _request - HttpServletRequest
	 * @param _response - HttpServletResponse
	 * @return calls action "LIST"
	 */
	//*
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("LIST");
	}

}
