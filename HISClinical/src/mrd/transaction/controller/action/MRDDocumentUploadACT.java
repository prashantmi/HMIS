package mrd.transaction.controller.action;
import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.MRDDocumentUploadFB;
import mrd.transaction.controller.fb.PatImageUploadFB;
import opd.OpdConfig;
import mrd.transaction.controller.utl.MRDDocumentUploadUTIL;
import mrd.transaction.controller.utl.PatImageUploadUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

	public class MRDDocumentUploadACT extends CSRFGardTokenAction
	{
		/**
		 * the default action called when a page is loaded for the first time
		 * 
		 * @param mapping -object of ActionMapping
		 * @param form - object of ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return calls the action "NEW"
		 */
		public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception
		{
			return this.NEW(mapping, form, request, response);
		}

		public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			generateToken(request);
			MRDDocumentUploadFB fb = (MRDDocumentUploadFB) form;
			MRDDocumentUploadUTIL.getDocumentArchivalEssentials(fb, request);
			//MRDDocumentUploadUTIL.getInpatientDetailByCrNo(fb, request);
			
			// added by Dheeraj on 11-09-2018
			WebUTIL.refreshTransState(request);	
			ControllerUTIL.setSysdate(request);
			fb.reset(mapping, request);
			fb.setHmode("NEW");
			Status status=new Status();
			status.add(Status.NEW);
			WebUTIL.setStatus(request, status);

			return mapping.findForward("NEW");
		}
		
		public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			MRDDocumentUploadFB fb=(MRDDocumentUploadFB)form;
			MRDDocumentUploadUTIL.getDocumentArchivalEssentials(fb,request);
			//MRDDocumentUploadUTIL.getInpatientDetailByCrNo(fb, request);
			return mapping.findForward("NEW");
		}

		public ActionForward REFRESHFORIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception
		{
			MRDDocumentUploadFB fb = (MRDDocumentUploadFB) form;
			MRDDocumentUploadUTIL.addDocument(fb, request);
			return mapping.findForward("NEW");
		}

		public ActionForward REMOVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			MRDDocumentUploadFB fb = (MRDDocumentUploadFB) form;
			MRDDocumentUploadUTIL.removeDocument(fb, request);
			return mapping.findForward("NEW");
		}

		public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			validateToken(request,response);
			MRDDocumentUploadFB fb = (MRDDocumentUploadFB) form;
			if(MRDDocumentUploadUTIL.saveDocument(fb, request))
			{
				Status objStatus = new Status();
				MRDDocumentUploadUTIL.getDocumentArchivalEssentials(fb, request);
				objStatus.add(Status.TRANSINPROCESS, "Document Successfully Uploaded", "");
				request.setAttribute(Config.STATUS_OBJECT, objStatus);
			}
			return mapping.findForward("NEW"); 
		}

		public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			return mapping.findForward("SHOW");
		}

		public ActionForward PLAY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			// MRDDocumentUploadFB fb=(MRDDocumentUploadFB)form;
			// String file=request.getParameter("fileType");
			WebUTIL.setAttributeInSession(request, OpdConfig.SELECTED_FILE_PATH_FOR_PLAYER, request.getParameter("fileType"));
			return mapping.findForward("PLAY");
		}

		public ActionForward AJX_G_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
		{
			StringBuffer strBuff= new StringBuffer();
			strBuff.append("{header:'Document Archival Summary',data:[]}");//{header:'Head Archival',value:'value'}
			System.out.println("OpdDocumentArchivalACTION.AJX_G_SUMMARY()   " +strBuff.toString() );
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;	
		}

		public ActionForward VIEWDOC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			MRDDocumentUploadFB fb = (MRDDocumentUploadFB) form;
			response.setContentType(fb.getDocumentDirectoryPath());
			MRDDocumentUploadUTIL.getViewDoc(fb, request,response);
			return null;
		}
		
		public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			MRDDocumentUploadFB fb = (MRDDocumentUploadFB) form;
			Status objStatus = new Status();
			fb.setHmode("");
			objStatus.add(Status.TRANSINPROCESS);
			//WebUTIL.setStatus(request, objStatus);
			request.setAttribute(Config.STATUS_OBJECT,objStatus);
			return mapping.findForward("NEW");
		}

	}
