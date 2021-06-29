package opd.transaction.controller.action;

/**
 * @copyright CDAC
 * @developer Hruday Meher
 * @lastModified Pargya Sharma  08-Jul-2010
 */

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdDiagnosisFB;
import opd.transaction.controller.util.OpdDiagnosisUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdDiagnosisACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	//Getting essential data for diagnosis
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		fb.reset(mapping, request);
		fb.setNumberOfRow("1");
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		OpdDiagnosisUTIL.getEssentials(fb, request);
		return mapping.findForward("NEW");
	}

	//Saving patient diagnosis
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		if (OpdDiagnosisUTIL.save(fb, request))
		{
			fb.reset(mapping, request);
			fb.setNumberOfRow("1");
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			OpdDiagnosisUTIL.getEssentials(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.TRANSINPROCESS, "", "Record Saved Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}

	//Opening a popup to search diagnosis
	public ActionForward POPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("POPUP");
	}

	public ActionForward POPUPMRD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("POPUPMRD");
	}

	//Searching diagnosis
	public ActionForward SEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.searchCode(fb, request);
		return mapping.findForward("POPUP");
	}

	public ActionForward SEARCHMRD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.searchCode(fb, request);
		return mapping.findForward("POPUPMRD");
	}

	//Searching Sub diagnosis on Click of Parent
	public ActionForward SEARCHSUB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.searchSubDiseaseByCode(fb, request);
		return mapping.findForward("POPUP");
	}
	
	//Searching Sub diagnosis on Click of Parent
	public ActionForward SEARCHSUBMRD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.searchSubDiseaseByCode(fb, request);
		return mapping.findForward("POPUPMRD");
	}

	//Populating the selected diagnosis to the parent page
	public ActionForward POPULATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.populate(fb, request, response);
		return null;
	}

	//Not In Use
	public ActionForward GETTEXTLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.getListForTextArea(fb, request, response);
		return null;
	}

	//Not In Use
	public ActionForward REPEATDIAGNOSIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.repeatDiagnosis(fb, request);
		return mapping.findForward("NEW");
	}

	//Getting all revoked and canceled diagnosis
	public ActionForward SHOWALL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.getAllRevokedDiagnosisDtl(fb, request);
		return mapping.findForward("SHOWALL");
	}

	//Not In Use
	public ActionForward REPEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.repeatSelectedDiagnosis(fb, request);
		return mapping.findForward("NEW");
	}

	//Getting previous diagnosis given to the patient diagnosis wise
	public ActionForward PREVIOUS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.getAllPreviousDiagDtl(fb, request);
		return mapping.findForward("PREVIOUS");
	}

	//Not In Use
	public ActionForward PREV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.getAllPreviousDiagDtl(fb, request);
		return mapping.findForward("SHOWALL");
	}

	//Changing From ICD to Hospital and vice versa,  added snomdct concept on 08.09.2016
	public ActionForward NEWCHANGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		validateToken(request,response);
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		OpdDiagnosisUTIL.getEssentialsByChoice(fb, request);
		OpdDiagnosisUTIL.generateComboOption(fb, request);
		fb.setNumberOfRow("1");
		return mapping.findForward("NEW");
	}
	
	
	//Revoking the diagnosis
	public ActionForward REVOKE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdDiagnosisFB fb = (OpdDiagnosisFB) form;
		if(OpdDiagnosisUTIL.revokeDiagnosis(fb, request))
		{
			fb.reset(mapping, request);
			fb.setNumberOfRow("1");
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			OpdDiagnosisUTIL.getEssentials(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.TRANSINPROCESS, "", "Record Revoked Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}

	public ActionForward AJX_G_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		OpdDiagnosisFB fb = (OpdDiagnosisFB) objForm_p;
		
		StringBuffer strBuff= new StringBuffer();
		strBuff = OpdDiagnosisUTIL.getEssentialDiagnosisDetail(fb, objRequest_p);		
		System.out.println("OpdDiagnosisACTION.AJX_G_SUMMARY()   " +strBuff.toString() );
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
}
