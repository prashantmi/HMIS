package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.PatientDischargePrintingFB;
import mrd.transaction.controller.utl.PatientDischargePrintingUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientDischargePrintingACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		PatientDischargePrintingFB fb=(PatientDischargePrintingFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		PatientDischargePrintingUTL.getDeptUnitList(fb,request);
		fb.setSelection("0");
		Status status=new Status();
		status.add(Status.NEW);
		WebUTIL.setStatus(request,status);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatientDischargePrintingFB fb=(PatientDischargePrintingFB)form;
		fb.setSelectCRNo("");
		PatientDischargePrintingUTL.getDischargePatientByCrNo(fb,request);
		fb.setSelection("1");
		fb.setPatCrNo("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBYCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatientDischargePrintingFB fb=(PatientDischargePrintingFB)form;
		WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
		//String sys=(String)request.getSession().getAttribute(Config.SYSDATE);
		// time=sys.split(" ")[1];
		//fb.setHiddenTimeHr(time.split(":")[0]);
		//fb.setHiddenTimeMin(time.split(":")[1]);
		fb.setSelection("1");
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatientDischargePrintingFB fb=(PatientDischargePrintingFB)form;
		ControllerUTIL.setSysdate(request);
		PatientDischargePrintingUTL.getWardOnBasisOfUnitCode(fb, request);
		fb.setHmode("UNIT");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDISCHARGEPAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatientDischargePrintingFB fb=(PatientDischargePrintingFB)form;
		fb.setSelectCRNo("");
		fb.setSelection("0");
		PatientDischargePrintingUTL.getDischargePatientList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDISCHARGEDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatientDischargePrintingFB fb=(PatientDischargePrintingFB)form;
		PatientDischargePrintingUTL.setDischargePrintSaveOption(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPRINTDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatientDischargePrintingFB fb=(PatientDischargePrintingFB)form;
		PatientDischargePrintingUTL.getDischargePrintDtl(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		PatientDischargePrintingFB fb=(PatientDischargePrintingFB)form;
		PatientDischargePrintingUTL.saveDischargePrinting(fb, request);
		if(fb.getSearchMode().equals("0")){
			
			return this.GETDISCHARGEPAT(mapping, form, request, response);
		}
		else{
			fb.setSelection("1");
			return this.GETPATDTL(mapping, form, request, response);
		}
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward POPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("POPUP");
	}

	/*Action called for pagination*/
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PatientDischargePrintingFB fb = (PatientDischargePrintingFB) form;
		Status objStatus= new Status();
		
		objStatus.add(Status.RECORDFOUND);
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
}
