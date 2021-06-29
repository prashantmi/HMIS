package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.transaction.controller.fb.OpdDocumentArchivalFB;
import opd.transaction.controller.util.OpdDocumentArchivalUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdDocumentArchivalACTION extends CSRFGardTokenAction
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
		OpdDocumentArchivalFB fb = (OpdDocumentArchivalFB) form;
		OpdDocumentArchivalUTIL.getDocumentArchivalEssentials(fb, request);

		return mapping.findForward("NEW");
	}

	public ActionForward REFRESHFORIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OpdDocumentArchivalFB fb = (OpdDocumentArchivalFB) form;
		OpdDocumentArchivalUTIL.addDocument(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward REMOVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdDocumentArchivalFB fb = (OpdDocumentArchivalFB) form;
		OpdDocumentArchivalUTIL.removeDocument(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdDocumentArchivalFB fb = (OpdDocumentArchivalFB) form;
		if(OpdDocumentArchivalUTIL.saveDocument(fb, request))
		{
			Status objStatus = new Status();
			OpdDocumentArchivalUTIL.getDocumentArchivalEssentials(fb, request);
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
		// OpdDocumentArchivalFB fb=(OpdDocumentArchivalFB)form;
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
		OpdDocumentArchivalFB fb = (OpdDocumentArchivalFB) form;
		response.setContentType(fb.getDocumentDirectoryPath());
		OpdDocumentArchivalUTIL.getViewDoc(fb, request,response);
		return null;
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdDocumentArchivalFB fb = (OpdDocumentArchivalFB) form;
		Status objStatus = new Status();
		fb.setHmode("");
		objStatus.add(Status.TRANSINPROCESS);
		//WebUTIL.setStatus(request, objStatus);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		return mapping.findForward("NEW");
	}
	//Added by Vasu 
	public ActionForward SAVEDOCUMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdDocumentArchivalFB fb = (OpdDocumentArchivalFB) form;
		if(OpdDocumentArchivalUTIL.savePatientDocument(fb, request))
		{
			Status objStatus = new Status();
			OpdDocumentArchivalUTIL.getDocumentArchivalEssentials(fb, request);
			objStatus.add(Status.TRANSINPROCESS, "Document Successfully Uploaded", "");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW"); 
	}

}
