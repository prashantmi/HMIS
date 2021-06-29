package mrd.transaction.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.BirthDeathRegistrationUploadDeskFB;
import mrd.transaction.controller.utl.BirthDeathRegistrationUploadDeskUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BirthDeathRegistrationUploadDeskACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.BIRTH(mapping,form,request,response);
	}
	
	public ActionForward BIRTH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		BirthDeathRegistrationUploadDeskFB fb=(BirthDeathRegistrationUploadDeskFB)form;
		Status objStatus =new Status();
		BirthDeathRegistrationUploadDeskUTL.setTabSequence(MrdConfig.BIRTH_DEATH_REGISTRATION_UPLOAD_MODE_BIRTH, request);
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);
		fb.setDeskmode("BIRTH");
		return mapping.findForward("BIRTH");
	}
	
	public ActionForward DEATH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		BirthDeathRegistrationUploadDeskUTL.setTabSequence(MrdConfig.BIRTH_DEATH_REGISTRATION_UPLOAD_MODE_DEATH, request);
		return mapping.findForward("DEATH");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	
}
