package opd.master.controller.action;

/**
 * @author  CDAC
 */

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.master.controller.fb.TemplateParameterMasterFB;
import opd.master.controller.util.AddTemplateParameterMasterUTIL;
import registration.RegistrationConfig;

public class AddTemplateParameterACTION extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		TemplateParameterMasterFB fb = (TemplateParameterMasterFB)form;
		Status status=new Status();

		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);
		ControllerUTIL.setSysdate(request);

		status.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,status);
		return mapping.findForward("SAME");
	}

	public ActionForward GOGETTEMPLATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		TemplateParameterMasterFB fb = (TemplateParameterMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddTemplateParameterMasterUTIL.getParameterList(fb,request);
		fb.setBlankTemplate();
		
		return mapping.findForward("SAME");
	}

	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//validateToken(request,response);
		TemplateParameterMasterFB fb = (TemplateParameterMasterFB)form;

		ControllerUTIL.setSysdate(request);

		boolean saveFlag=AddTemplateParameterMasterUTIL.SaveParameterToTemplateMaster(fb,request);

		if(saveFlag)
		{
			Status status=new Status();
			status.add(Status.NEW,"Template Successully Saved","");
			WebUTIL.refreshTransState(request);
			ControllerUTIL.setSysdate(request);
			fb.reset(mapping,request);
			request.setAttribute(Config.STATUS_OBJECT,status);
			fb.setHmode("ADD");
		}
		return mapping.findForward("SAME");
	}

	public ActionForward LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		TemplateParameterMasterFB fb = (TemplateParameterMasterFB)form;

		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);

		ControllerUTIL.setSysdate(request);
		
		AddTemplateParameterMasterUTIL.getModifyTemplate(fb,request);
		
		fb.setTransactionMode("MISTAKE");
		fb.setChoice(RegistrationConfig.CHOICE_MISTAKE);
		fb.setModeTempModify("0"); 
		
		return mapping.findForward("MODIFY");
	}

	public ActionForward MISTAKE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		TemplateParameterMasterFB fb = (TemplateParameterMasterFB)form;
		Status status=new Status();

		ControllerUTIL.setSysdate(request);
		status.add(Status.INPROCESS);
		
		WebUTIL.setStatus(request,status);
		fb.setHmode("MODIFY");
		fb.setTransactionMode("MISTAKE");
		fb.setChoice(RegistrationConfig.CHOICE_MISTAKE);
		fb.setModeTempModify("0"); 
		
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward ADDITION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		TemplateParameterMasterFB fb = (TemplateParameterMasterFB)form;
		Status status=new Status();

		ControllerUTIL.setSysdate(request);
		status.add(Status.INPROCESS);
		
		WebUTIL.setStatus(request,status);
		fb.setHmode("MODIFY");
		fb.setTransactionMode("ADDITION");
		fb.setChoice(RegistrationConfig.CHOICE_ADDITION);
		fb.setModeTempModify("0"); 
		
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYTEMPLATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		TemplateParameterMasterFB fb = (TemplateParameterMasterFB)form;
		ControllerUTIL.setSysdate(request);

		if(fb.getChoice().equals("1"))
		{
			AddTemplateParameterMasterUTIL.updateTemplate(fb,request);
			fb.setTransactionMode("MISTAKE");
		}
		if(fb.getChoice().equals("2"))
		{
			AddTemplateParameterMasterUTIL.addNewTemplateRow(fb,request); 
			fb.setTransactionMode("ADDITION");
		}
		
		// Setting  to Template Modify
		String forward="";
		if(fb.getModeTempModify().equals("1"))
		{
			fb.setHmode("MODIFY");
			AddTemplateParameterMasterUTIL.getModifyTemplateParametersForm(fb,request);
			forward="SAME";
		}
		else
			forward="MODIFY";
		return mapping.findForward(forward);
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		TemplateParameterMasterFB fb = (TemplateParameterMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddTemplateParameterMasterUTIL.UpdateParameterToTemplateMaster(fb,request);

		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);
	
		return mapping.findForward("LIST");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		TemplateParameterMasterFB fb = (TemplateParameterMasterFB)form;

		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);
		ControllerUTIL.setSysdate(request);
		
		AddTemplateParameterMasterUTIL.getViewTemplateParametersForm(fb,request);
		
		return mapping.findForward("SAME");
	}
}
