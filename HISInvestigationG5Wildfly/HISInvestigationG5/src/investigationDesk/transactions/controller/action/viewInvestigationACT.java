package investigationDesk.transactions.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import investigationDesk.transactions.controller.fb.viewInvestigationFB;
import investigationDesk.transactions.controller.utl.viewInvestigationUTL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class viewInvestigationACT
  extends DispatchAction
{
  public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) { return NEW(mapping, form, request, response); }


  
  public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    viewInvestigationFB fb = (viewInvestigationFB)form;
    ControllerUTIL.setSysdate(request);
    viewInvestigationUTL.getReqData(fb, request);
    request.getSession().setAttribute("patcrno", fb.getPatCrNo());
    fb.setCallingmode("0");
    return mapping.findForward("NEW");
  }


  
  public ActionForward EXTNEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    viewInvestigationFB fb = (viewInvestigationFB)form;
    ControllerUTIL.setSysdate(request);
    WebUTIL.setAttributeInSession(request, "dynamicDeskType", fb.getDeskType());
    request.getSession().setAttribute("patcrno", fb.getPatCrNo());
    viewInvestigationUTL.getReqData(fb, request);
    
    return mapping.findForward("NEW");
  }
  
  public ActionForward PRINTREPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    System.out.println("viewInvestigationACT: printreport  ");
    viewInvestigationFB fb = (viewInvestigationFB)form;
    
    viewInvestigationUTL.printReport(fb, request, response);
    
    return null;
  }


  
  public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    viewInvestigationFB fb = (viewInvestigationFB)form;
    Status objStatus = new Status();
    objStatus.add(Status.TRANSINPROCESS);
    WebUTIL.setStatus(request, objStatus);
    return mapping.findForward("NEW");
  }



  
  public ActionForward NEW_ALL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    viewInvestigationFB fb = (viewInvestigationFB)form;
    ControllerUTIL.setSysdate(request);
    String callingmode = fb.getCallingmode();
    viewInvestigationUTL.getReqData(fb, request);
    fb.setCallingmode(callingmode);
    return mapping.findForward("NEW");
  }
}
