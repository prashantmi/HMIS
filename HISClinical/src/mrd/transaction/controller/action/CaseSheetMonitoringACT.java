package mrd.transaction.controller.action;

/**
 * @author Pawan Kumar B N
 * Creation Date 05-Jul-2012
 */
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.CaseSheetMonitoringFB;
import mrd.transaction.controller.utl.CaseSheetMonitoringUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CaseSheetMonitoringACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		CaseSheetMonitoringFB fb=(CaseSheetMonitoringFB)form;
		WebUTIL.refreshTransState(request);	
		ControllerUTIL.setSysdate(request);
		/*fb.setHmode("NEW");
		fb.setTempMode(fb.getHmode());
		boolean flag=CaseSheetMonitoringUTL.getDeptUnitList(fb,request);*/
		//CaseSheetMonitoringUTL.getDeptUnitList(fb,request);
		fb.setSelection("0");
		fb.setHideDuplicateLabel("false");
		//fb.reset(mapping, request);
		Status status=new Status();
		status.add(Status.NEW);
		WebUTIL.setStatus(request,status);
		//CaseSheetDispatchUTL.getDeptUnitList(fb,request);
		fb.setDepartmentUnitCode("%");
		return this.UNIT(mapping, form, request, response);
		//return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetMonitoringFB fb=(CaseSheetMonitoringFB)form;
		CaseSheetMonitoringUTL.getPatientCaseSheetDtlByCrNo(fb,request);
		fb.setSelection("1");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHBYCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetMonitoringFB fb=(CaseSheetMonitoringFB)form;
		WebUTIL.refreshTransState(request);	
		ControllerUTIL.setSysdate(request);
		String sys=(String)request.getSession().getAttribute(Config.SYSDATE);
		String time=sys.split(" ")[1];
		fb.setHiddenTimeHr(time.split(":")[0]);
		fb.setHiddenTimeMin(time.split(":")[1]);
		fb.setSelection("1");
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetMonitoringFB fb=(CaseSheetMonitoringFB)form;
		ControllerUTIL.setSysdate(request);
		CaseSheetMonitoringUTL.getWardOnBasisOfUnitCode(fb, request);
		fb.setHmode("UNIT");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCASESHEETDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		CaseSheetMonitoringFB fb=(CaseSheetMonitoringFB)form;
		CaseSheetMonitoringUTL.getPatientCaseSheetDtl(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCASESHEETSTATUS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		CaseSheetMonitoringFB fb=(CaseSheetMonitoringFB)form;
		Status status=new Status();
		CaseSheetMonitoringUTL.getPatientCaseSheetLostFoundDtl(fb, request);
		status.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,status);
		return mapping.findForward("NEW");
	}
	
	public ActionForward UPDATECASESHEETSTATUS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CaseSheetMonitoringFB fb=(CaseSheetMonitoringFB)form;
		CaseSheetMonitoringUTL.updatePatientCaseSheetStatus(fb, request);
		if(fb.getSearchMode().equals("0")){
			return this.GETCASESHEETDTL(mapping, form, request, response);
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

	/*Action called for pagination*/
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CaseSheetMonitoringFB fb = (CaseSheetMonitoringFB) form;
		Status objStatus= new Status();
		
		objStatus.add(Status.RECORDFOUND);
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
}
