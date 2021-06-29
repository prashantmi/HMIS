package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.CertificateBEntryFormFB;
import mrd.transaction.controller.utl.CertificateBEntryFormUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import registration.RegistrationConfig;

	public class CertificateBEntryFormACT  extends CSRFGardTokenAction {
		
		
			public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				return this.NEW(mapping, form, request, response);
							
			}
			
			public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				generateToken(request);
				CertificateBEntryFormFB fb=(CertificateBEntryFormFB)form;
				WebUTIL.refreshTransState(request);	
				fb.reset(mapping, request);
				CertificateBEntryFormUTL.getCertificateBEntryList(fb, request);
				return mapping.findForward("LIST");
			}
			
			public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				CertificateBEntryFormFB fb=(CertificateBEntryFormFB)form;
			    CertificateBEntryFormUTL.getEssentialData(fb, request);  // shifted from new
				return mapping.findForward("NEW");
			}

			
			public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				CertificateBEntryFormFB fb=(CertificateBEntryFormFB)form;
				
				return mapping.findForward("GETPATDTL");
			}

											
			public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				validateToken(request,response);
				CertificateBEntryFormFB fb=(CertificateBEntryFormFB)form;
				CertificateBEntryFormUTL.saveReqDtl(fb, request);
				
				return mapping.findForward("NEW");
			}
			
			
			public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
			{
				CertificateBEntryFormFB fb=(CertificateBEntryFormFB)form;
				Status objStatus= new Status();		
				objStatus.add(Status.RECORDFOUND);
				objStatus.add(Status.NEW);
				request.setAttribute(Config.STATUS_OBJECT,objStatus);
				fb.setHmode("");
				return mapping.findForward("LIST");
			}
			
			
			public ActionForward GETDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				CertificateBEntryFormFB fb=(CertificateBEntryFormFB)form;
				//WebUTIL.refreshTransState(request);	
				//fb.reset(mapping, request);
				request.getSession().setAttribute(RegistrationConfig.UPLOADED_FILE_AS_ARRAY,null);
				CertificateBEntryFormUTL.getCertificateBEntryHandoverDtl(fb,request);
				return mapping.findForward("GETDTL");
			}
			
			/*public ActionForward CHANGEREQTYPE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				CertificateBEntryFormFB fb=(CertificateBEntryFormFB)form;
				if(fb.getReqType().equals("1"))
					return mapping.findForward("GETPATDTL");
				else
					return mapping.findForward("NEW");
			}*/

			
			public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				return mapping.findForward("NEW");
			}
			
			public  ActionForward FINALCANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
			{
				return mapping.findForward("FINALCANCEL");
			}
			
			
			public ActionForward SAVEMODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
			{
				validateToken(request,response);
				CertificateBEntryFormFB fb=(CertificateBEntryFormFB)form;
				if(CertificateBEntryFormUTL.saveCertificateBIssueDtl(fb,request))
				{
					
						//return this.GETDTL(mapping, form, request, response);
						return this.NEW(mapping, form, request, response);
					
				}
				else
					//return mapping.findForward("GETDTL");
				    return mapping.findForward("NEW");
			}
			
	}
