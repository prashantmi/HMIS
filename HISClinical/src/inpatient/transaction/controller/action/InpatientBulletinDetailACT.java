
package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.InpatientBulletinDetailFB;
import inpatient.transaction.controller.utl.InpatientBulletinDetailUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InpatientBulletinDetailACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{

		return this.CALLDIRECTLY(mapping, form, request, response);
	}
	
	/** Getting the List of Admitted patient
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//Call Directly
	
	public ActionForward CALLDIRECTLY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		InpatientBulletinDetailFB fb=(InpatientBulletinDetailFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		ControllerUTIL.setSysdate(request);
		if(fb.getTransactionMode().equals(InpatientConfig.BULLETIN_BOARD))
			InpatientBulletinDetailUTL.getAllAdmittedPatientListBulletin(fb,request);
		return mapping.findForward("NEW");
	}
	
	//call from desk
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		InpatientBulletinDetailFB fb=(InpatientBulletinDetailFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		if(fb.getIsDirectCall()==null || fb.getIsDirectCall().trim().equals("") ) fb.setIsDirectCall("DESK");
		ControllerUTIL.setSysdate(request);
		if(fb.getTransactionMode().equals(InpatientConfig.BULLETIN_BOARD))
			InpatientBulletinDetailUTL.getAllAdmittedPatientListBulletin(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		InpatientBulletinDetailFB fb=(InpatientBulletinDetailFB)form;		
		InpatientBulletinDetailUTL.getAdmittedPatientList(fb, request);
		return mapping.findForward("NEW");
	}
	
	/*public ActionForward GETPATBULLETINDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		InpatientBulletinDetailFB fb=(InpatientBulletinDetailFB)form;		
		InpatientBulletinDetailUTL.getPatientBulletinDetail(fb, request);
		return mapping.findForward("NEW");
	}*/
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		InpatientBulletinDetailFB fb=(InpatientBulletinDetailFB)form;		
		InpatientBulletinDetailUTL.saveAndUpdatePatientBulletinDetails(fb, request);
		
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("inside CANCEL COMMON ACTION");
	    //WebUTIL.refreshTransState(request);		
		return mapping.findForward("CANCEL");
    }
	
	public ActionForward ADDREMARKS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		InpatientBulletinDetailFB fb=(InpatientBulletinDetailFB)form;		
		InpatientBulletinDetailUTL.getRemarks(fb, request);
		return mapping.findForward("ADDREMARKS");
	}
	
	public ActionForward SHOWREMARKS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("ADDREMARKS");
	}
	
	public ActionForward ORDERBYWARDTYPE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		InpatientBulletinDetailFB fb=(InpatientBulletinDetailFB)form;	
		InpatientBulletinDetailUTL.getOrderByWardType(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ORDERBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		InpatientBulletinDetailFB fb=(InpatientBulletinDetailFB)form;	
		InpatientBulletinDetailUTL.getOrderByName(fb, request);
		return mapping.findForward("NEW");
	}
}
