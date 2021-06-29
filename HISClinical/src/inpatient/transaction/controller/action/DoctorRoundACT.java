package inpatient.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.DoctorRoundDtlVO;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.DoctorRoundFB;
import inpatient.transaction.controller.utl.DoctorRoundUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DoctorRoundACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		DoctorRoundFB fb = (DoctorRoundFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		ControllerUTIL.setSysdate(request);
		DoctorRoundUTL.getUnverifiedEntryByNurse(fb, request);
		return mapping.findForward("DOCTOR");
	}
	
	public ActionForward GETCONSULTANT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DoctorRoundFB fb = (DoctorRoundFB) form;
		DoctorRoundUTL.getEssentialDocRoundForNurse(fb, request);
		
		return mapping.findForward("NURSE");
	}
	
	public ActionForward NURSESAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DoctorRoundFB fb = (DoctorRoundFB) form;
		DoctorRoundUTL.saveDoctorVisitNotes(fb, request);
		fb.reset(mapping, request);
		return mapping.findForward("NURSE");
	}

	public ActionForward DOCINSTRUCTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DoctorRoundFB fb = (DoctorRoundFB) form;
		DoctorRoundUTL.getDoctorInstruction(fb, request);
		return mapping.findForward("DOCINSTRUCTION");
	}

	public ActionForward GETDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DoctorRoundFB fb = (DoctorRoundFB) form;
		DoctorRoundUTL.getRecordDetail(fb, request);
		return mapping.findForward("DOCTOR");
	}

	public ActionForward DOCTORUPDATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DoctorRoundFB fb = (DoctorRoundFB) form;
		DoctorRoundUTL.saveNVerifyNotesByDoctor(fb, request);
		fb.reset(mapping, request);
		return mapping.findForward("DOCTOR");
	}

	public ActionForward DOCTORSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DoctorRoundFB fb = (DoctorRoundFB) form;
		if(DoctorRoundUTL.saveNotesByDoctor(fb, request))
		{
			fb.reset(mapping, request);
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			DoctorRoundUTL.getUnverifiedEntryByNurse(fb, request);

			DoctorRoundDtlVO[] arrUnverifiedRecordVO = (DoctorRoundDtlVO[]) request.getSession().getAttribute(InpatientConfig.ARR_UNVERIFIED_RECORD_ENTERBY_NURSE);
			Status objStatus = new Status();
			if (arrUnverifiedRecordVO==null || arrUnverifiedRecordVO.length == 0)
				objStatus.add(Status.TRANSINPROCESS, "", "Record Saved Successfully");
			else
				objStatus.add(Status.LIST, "", "Record Saved Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
			return mapping.findForward("DOCTOR");
		}
		else
			return mapping.findForward("DOCTOR");
	}

	public ActionForward DOCPREVROUNDDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DoctorRoundFB fb = (DoctorRoundFB) form;
		DoctorRoundUTL.getDoctorPrevRoundDtl(fb, request);
		return mapping.findForward("DOCPREVROUNDDTL");
	}
	
	public ActionForward ADDNOTES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DoctorRoundFB fb = (DoctorRoundFB) form;
		DoctorRoundUTL.getVisitNotesToAdd(fb, request);
		return mapping.findForward("ADDNOTES");
	}
	
	public ActionForward SHOWNOTES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("ADDNOTES");
	}
	
	public ActionForward PREVIOUSNURSENOTES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DoctorRoundFB fb=(DoctorRoundFB)form;
		DoctorRoundUTL.getPreviousNurseProgressNotes(fb,request);
		return mapping.findForward("NURSEPREVROUNDDTL");
	}
	
	public ActionForward POPUPPAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DoctorRoundFB fb=(DoctorRoundFB)form;
		//ConsentRequestUTIL.setData(fb, request);
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		fb.reset(mapping, request);
		return mapping.findForward("NURSEPREVROUNDDTL");
	}
	
	public ActionForward DOCPOPUPPAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DoctorRoundFB fb=(DoctorRoundFB)form;
		//ConsentRequestUTIL.setData(fb, request);
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		fb.reset(mapping, request);
		return mapping.findForward("DOCPREVROUNDDTL");
	}
	//Added by Vasu on 26.Sept.2018 to update doctor visit Notes
	public ActionForward DOCTORDETAILUPDATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DoctorRoundFB fb = (DoctorRoundFB) form;
		if(DoctorRoundUTL.updateNotesByDoctor(fb, request))
		{
			fb.reset(mapping, request);
			WebUTIL.refreshTransState(request);
			DoctorRoundUTL.getUnverifiedEntryByNurse(fb, request);

			DoctorRoundDtlVO[] arrUnverifiedRecordVO = (DoctorRoundDtlVO[]) request.getSession().getAttribute(InpatientConfig.ARR_UNVERIFIED_RECORD_ENTERBY_NURSE);
			Status objStatus = new Status();
			if (arrUnverifiedRecordVO==null || arrUnverifiedRecordVO.length == 0)
				objStatus.add(Status.TRANSINPROCESS, "", "Record Saved Successfully");
			else
				objStatus.add(Status.LIST, "", "Record Saved Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
			return mapping.findForward("DOCTOR");
		}
		else
			return mapping.findForward("DOCTOR");
	}
	
}
