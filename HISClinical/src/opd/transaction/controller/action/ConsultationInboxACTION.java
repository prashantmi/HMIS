package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.ConsultationInboxFB;
import opd.transaction.controller.util.ConsultationInboxUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultationInboxACTION extends CSRFGardTokenAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		generateToken(request);
		ConsultationInboxFB fb = (ConsultationInboxFB) form;
		//fb.reset(mapping,request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		boolean flag=ConsultationInboxUTIL.getEssentials(fb,request);	
		if(flag)
		return mapping.findForward("NEW");
		else{
			fb.setMode("NEW");
			return mapping.findForward("CANCEL");
		}
	}

	public ActionForward GETMAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		ConsultationInboxFB fb = (ConsultationInboxFB) form;
		
		ConsultationInboxUTIL.getMailContent(fb,request);
		return mapping.findForward("MAIL");
		
		}
	
public ActionForward REPLY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	validateToken(request,response);
		ConsultationInboxFB fb = (ConsultationInboxFB) form;
		ConsultationInboxUTIL.replyMailContent(fb,request);
		return mapping.findForward("REPLY");
		
		}

public ActionForward SEND(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	validateToken(request,response);
	ConsultationInboxFB fb = (ConsultationInboxFB) form;
	ConsultationInboxUTIL.sendMail(fb,request);
	return this.NEW(mapping,form,request,response);	
	
	}

public ActionForward FORWARD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	validateToken(request,response);
	ConsultationInboxFB fb = (ConsultationInboxFB) form;
	ConsultationInboxUTIL.forwardMailContent(fb,request);
	return mapping.findForward("FORWARD");
	
	}

public ActionForward DELETE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
	validateToken(request,response);
	ConsultationInboxFB fb = (ConsultationInboxFB) form;
	ConsultationInboxUTIL.deleteMail(fb, request);
	return this.NEW(mapping,form,request,response);	
	
	}

public ActionForward GETPREMAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	
	ConsultationInboxFB fb = (ConsultationInboxFB) form;
	
	ConsultationInboxUTIL.getPreviousMailContent(fb,request);
	return mapping.findForward("MAIL");
	
	}
}
