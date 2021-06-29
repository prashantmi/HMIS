package inpatient.transaction.controller.action;

/**
 * @copyright CDAC
 * @developer Hruday Meher
 */


import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.OutTakeFB;
import inpatient.transaction.controller.utl.OutTakeUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OutTakeACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	/**Getting The Essential Data for Intake Outtake Process
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		OutTakeFB fb=(OutTakeFB)form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		fb.resetIntake(mapping, request);
		fb.setIsIntakeOuttakeTimeBased(InpatientConfig.INTAKE_OUTTAKE_TIME_BASED);
		OutTakeUTL.getOutParameterList(fb,request);
		return mapping.findForward("NEW");
	}
	
	/**Saving the intake outtake data in the database
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OutTakeFB fb=(OutTakeFB)form;
		OutTakeUTL.saveOutParameter(fb,request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		fb.resetIntake(mapping, request);
		fb.setIntakeVOExistFlag("");
		fb.setOuttakeVOExistFlag("");
		OutTakeUTL.getOutParameterList(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Adding a new row for outtake detail
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADDROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OutTakeFB fb=(OutTakeFB)form;
		OutTakeUTL.addRow(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	/**Deleting a row for outtake detail
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OutTakeFB fb=(OutTakeFB)form;
		OutTakeUTL.deleteRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Viewing all the outtake detail of the patient in a popup 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward VIEWOUTTAKE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OutTakeFB fb=(OutTakeFB)form;
		fb.setIntakeOutMode(InpatientConfig.INTAKEOUT_MODE_OUTTAKE);
		OutTakeUTL.getOutParaDetail(fb,request);
		return mapping.findForward("VIEW");
	}
	
	/**Adding a new row for intake detail
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADDINTAKEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OutTakeFB fb=(OutTakeFB)form;
		OutTakeUTL.addIntakeRow(fb,request);
		fb.resetIntake(mapping, request);
		return mapping.findForward("NEW");
	}
	
	/**Deleting a row for intake detail
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward DELETEINTAKEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OutTakeFB fb=(OutTakeFB)form;
		OutTakeUTL.deleteIntakeRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Viewing all the intake detail of the patient in a popup
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward VIEWINTAKE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OutTakeFB fb=(OutTakeFB)form;
		fb.setIntakeOutMode(InpatientConfig.INTAKEOUT_MODE_INTAKE);
		OutTakeUTL.getOutParaDetail(fb,request);
		return mapping.findForward("VIEW");
	}
	
	/**Getting essential data for intake outtake summary
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ALLSUMMARY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OutTakeFB fb=(OutTakeFB)form;
		OutTakeUTL.getEssentialForAllSummary(fb,request);
		return mapping.findForward("ALLSUMMARY");
	}
	
	
	/** Showing the intake outtake summary in a popup
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward VIEWSUMMARY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OutTakeFB fb=(OutTakeFB)form;
		OutTakeUTL.viewSummary(fb,request);
		return mapping.findForward("ALLSUMMARY");
	}
	
}
