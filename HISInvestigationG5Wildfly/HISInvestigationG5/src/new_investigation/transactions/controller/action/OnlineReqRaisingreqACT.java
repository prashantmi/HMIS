package new_investigation.transactions.controller.action;

import java.io.IOException;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.InvestigationRaisingDtlreqFB;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlUTL;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlreqUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;













public class OnlineReqRaisingreqACT
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

    
    fb.setSelectlabid(null);
    fb.setLabwisetestteriff("0");
    fb.setLabTestCodeArray("");
    fb.setPatAdmNo("-1");
    fb.setLabTestCodeArray("");
    fb.reset(mapping, request);
    session.removeAttribute("IsAddendumRaisingPOPUP");
    session.removeAttribute("IsAddendum");
    session.removeAttribute("IsAddendumENTRY");
    session.setAttribute("load", "0");
    session.setAttribute("savesucc", "0");
    session.setAttribute("patcrnoo", "");
    
    session.setAttribute("issuffientamountforgroup","0");
    
    session.removeAttribute("lsitprevreqxray");
    fb.setIssearchtestnamewise("0");
    WebUTIL.refreshTransState(request);
    session.setAttribute("load", "0");
    session.setAttribute("savesucc", "0");
    session.setAttribute("patcrno", "");
    
    fb.setShwdiv("0");


    
    return mapping.findForward("NEW");
  }

  
  public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    generateToken(request);
    
    System.out.println("call new modee");
    
    InvestigationRaisingDtlreqFB fb = (InvestigationRaisingDtlreqFB)form;
    HttpSession session = WebUTIL.getSession(request);
    String crno = fb.getPatCrNo();
    
    fb.setHmode("");
    fb.setShwdiv("1");
    session.setAttribute("load", "1");
    session.setAttribute("savesucc", "0");
    
    InvestigationRaisingDtlreqUTL.setPatientRegistrationEssentials(fb, request);
    
    InvestigationRaisingDtlreqUTL.setchargestestngroup(fb,request);
	
    fb.setPatCRNo(crno);
    session.setAttribute("load", "1");
    session.setAttribute("patcrno", crno);


    
    return mapping.findForward("NEW");
  }



  
  public ActionForward AJX_GETTESTWISEDATA(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws IOException {
    InvestigationRaisingDtlreqFB fb = (InvestigationRaisingDtlreqFB)objForm_p;
    
    if(fb.getTestlabcode()!=null)
    {
    	String ff=fb.getTestlabcode().replaceAll("@", "^") ;
    	fb.setTestlabcode(ff);
    }
    		
    String strBuff = InvestigationRaisingDtlreqUTL.getesttdatatestwisexrayprocess(fb, objRequest_p);
    objResponse_p.setHeader("Cache-Control", "no-cache");
    objResponse_p.getWriter().print(strBuff);
    
    return null;
  }


  
  public ActionForward AJX_CHECKPATCRNO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws IOException {
    InvestigationRaisingDtlreqFB fb = (InvestigationRaisingDtlreqFB)objForm_p;
    
    String strBuff = InvestigationRaisingDtlreqUTL.getisexistpatcrno(fb, objRequest_p);
    objResponse_p.setHeader("Cache-Control", "no-cache");
    objResponse_p.getWriter().print(strBuff);
    
    return null;
  }



  
  public ActionForward SAVEXRAYPROCESS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
   
	  
    HttpSession session = WebUTIL.getSession(request);
    System.out.println("act:SAVE");
    InvestigationRaisingDtlreqFB fb = (InvestigationRaisingDtlreqFB)form;
    String crnoo = fb.getPatCrNo();
    System.out.println("episode code:" + fb.getSelectedEpisodeRadio());
    InvestigationRaisingDtlreqUTL.saveRequisitionDetails(fb, request);
    session.removeAttribute("listpidpat");

    
    String casualitydesk = "0";
    if (fb.getCasualitydesk() != null && fb.getCasualitydesk().equals("")) {
      casualitydesk = "0";
    } else {
      casualitydesk = "1";
    }  session.setAttribute("load", "0");
    session.setAttribute("savesucc", "1");
    session.setAttribute("patcrnoo", crnoo);
    fb.setFinalMandValues("");
    fb.setNewlabtestcodearray("");
    fb.setIssearchtestnamewise("0");
    System.out.println("act:Save end.");
    session.setAttribute("IsAddendumRaisingPOPUP", "");
    session.removeAttribute("pidsetforgrpcase");
    fb.setPidd("");
    fb.setPiddata("");
    fb.setPiddmodalopen("");
    
    fb.setPiddmodalopen(null);
    fb.setSelectlabid(null);
    fb.setLabwisetestteriff("0");
    session.setAttribute("load", "0");
    
    session.setAttribute("savesucc", "1");
    session.setAttribute("patcrnoo", crnoo);
    
    if (session.getAttribute("IsAddendum") != null) {



      
      if (session.getAttribute("IsAddendumENTRY") != null) {
        
        fb.setIsentry("1");
        return NEW(mapping, form, request, response);
      } 
      
      return mapping.findForward("GOTOADDENDUM");
    } 

    
    fb.setCallingdesk(casualitydesk);
    session.setAttribute("load", "0");
    fb.setPatCrNo("");
    return mapping.findForward("NEW");
  }







  
  public ActionForward AJX_CHECKBILLING(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws IOException {
    InvestigationRaisingDtlreqFB fb = (InvestigationRaisingDtlreqFB)objForm_p;
    
    String strBuff = InvestigationRaisingDtlreqUTL.getcheckbilling(fb, objRequest_p);
    objResponse_p.setHeader("Cache-Control", "no-cache");
    objResponse_p.getWriter().print(strBuff);
    
    return null;
  }
}
