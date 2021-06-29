package new_investigation.transactions.controller.action;

import java.io.IOException;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.InvestigationRaisingDtlreqFB;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlreqUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;













public class OnlineReqRaisingPrevreqNEWACT
  extends CSRFGardTokenAction
{
  public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    generateToken(request);
    return NEW(mapping, form, request, response);
  }

  
  public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    generateToken(request);
    InvestigationRaisingDtlreqFB fb = (InvestigationRaisingDtlreqFB)form;
    HttpSession session = WebUTIL.getSession(request);
    
    

    if(session.getAttribute("patcrno")!=null)
        
    {
  	  
  	  String crnoo=(String) session.getAttribute("patcrno") ;
  	  
  	  if(crnoo!=null && !crnoo.equals(""))
  		  fb.setPatCrNo(crnoo);
    }
    
    InvestigationRaisingDtlreqUTL.getprevreq(fb, request);
    request.setAttribute("searchTestName", fb.getSearchTestName());
    
    
    
    
    String crno = fb.getPatCrNo();

    
    request.getSession().setAttribute("testName", (fb.getSearchTestName() == null) ? "" : fb.getSearchTestName());
    String fromwhichcall = (fb.getPrevreqfromwhichcall() == null) ? "0" : fb.getPrevreqfromwhichcall();
    
    fb.setPrevreqfromwhichcall(fromwhichcall);
    fb.setPatCrNo(crno);
    request.getSession().setAttribute("fromwhichcall", "0");
    fb.setSearchTestName(fb.getSearchTestName());
    return mapping.findForward("NEW");
  }






  
  public ActionForward AJX_CHECKBILLING(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws IOException {
    InvestigationRaisingDtlreqFB fb = (InvestigationRaisingDtlreqFB)objForm_p;
    
    String strBuff = InvestigationRaisingDtlreqUTL.getcheckbilling(fb, objRequest_p);
    objResponse_p.setHeader("Cache-Control", "no-cache");
    objResponse_p.getWriter().print(strBuff);
    
    return null;
  }


  
  public ActionForward CHECKBILLING(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws IOException {
    InvestigationRaisingDtlreqFB fb = (InvestigationRaisingDtlreqFB)objForm_p;
    
    int strBuff = InvestigationRaisingDtlreqUTL.checkBillDtlbeforedeletion(fb, objRequest_p);
    objResponse_p.setHeader("Cache-Control", "no-cache");
    objResponse_p.getWriter().print(Integer.toString(strBuff));
    
    return null;
  }













  
  public ActionForward DELETEREQDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    System.out.println("InvestigationRaisingDtlACT: DELETEREQDTL  ");
    InvestigationRaisingDtlreqFB fb = (InvestigationRaisingDtlreqFB)form;
    ControllerUTIL.setSysdate(request);
    
    String crno = fb.getPatCrNo();

    
    InvestigationRaisingDtlreqUTL.deleteReqDtl(fb, request);
    
    fb.setPatCrNo(crno);
    
    return DELETEAFTERCALL(mapping, form, request, response);
  }

  
  public ActionForward DELETEAFTERCALL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    generateToken(request);
    InvestigationRaisingDtlreqFB fb = (InvestigationRaisingDtlreqFB)form;
    HttpSession session = WebUTIL.getSession(request);
    
    String fromwhichcall = (fb.getPrevreqfromwhichcall() == null) ? "0" : fb.getPrevreqfromwhichcall();
    
    InvestigationRaisingDtlreqUTL.getprevreq(fb, request);
    request.setAttribute("searchTestName", (fb.getSearchTestName() == null) ? "" : fb.getSearchTestName());

    
    if (request.getSession().getAttribute("testName") == null)
    {
      request.getSession().setAttribute("testName", (fb.getSearchTestName() == null) ? "" : fb.getSearchTestName());
    }
    
    request.getSession().setAttribute("fromwhichcall", fromwhichcall);
    fb.setPrevreqfromwhichcall(fromwhichcall);
    return mapping.findForward("NEW");
  }
}
