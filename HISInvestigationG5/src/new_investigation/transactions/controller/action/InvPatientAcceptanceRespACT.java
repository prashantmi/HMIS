package new_investigation.transactions.controller.action;

import hisglobal.backutil.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.reports.controller.fb.InvTrackingReportFB;
import new_investigation.reports.controller.utl.InvTrackingReportUTIL;
import new_investigation.transactions.controller.fb.InvPatientAcceptanceRespFB;
import new_investigation.transactions.controller.fb.InvResultValidationRespFB;
import new_investigation.transactions.controller.fb.InvPatientAcceptanceRespFB;
import new_investigation.transactions.controller.utl.InvPatientAcceptanceRespUTL;
import new_investigation.transactions.controller.utl.InvResultReValidationRespUTIL;
import new_investigation.transactions.controller.utl.InvResultValidationRespUTIL;
import new_investigation.transactions.controller.utl.SampleCollectionUTL;
import new_investigation.vo.InvPatientAcceptanceRespVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class InvPatientAcceptanceRespACT extends CSRFGardTokenAction {
	String error="Error Message Starts From Here [Added By Prashant] :";
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		session.setAttribute("patientAcceptanceSessionJson", null);
		WebUTIL.refreshTransState(request);
		return this.NEW(mapping,form,request,response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		session.setAttribute("patientAcceptanceSessionJson", null);
		session.removeAttribute(InvestigationConfig.LIST_EPISODE_VO);
		WebUTIL.refreshTransState(request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward AjaxGetPatAcceptanceReqnList(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		generateToken(request);
		InvPatientAcceptanceRespFB fb=(InvPatientAcceptanceRespFB)form;
		HttpSession session=request.getSession();
		session.removeAttribute(InvestigationConfig.LIST_EPISODE_VO);
		
		JsonObject jsonResponse = new JsonObject();
		StringWriter sw=new StringWriter();
		try {
			
			jsonResponse=InvPatientAcceptanceRespUTL.AjaxGetPatAcceptanceReqnList(fb,request);
			
			jsonResponse.addProperty("isSuccess", "1");
			response.setContentType("application/Json");
			response.getWriter().print(jsonResponse.toString());
		}
		catch (JsonIOException e) {
			e.printStackTrace();
			
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { 
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", error+sw.toString());
				response.getWriter().print(jsonResponse.toString());
			} catch (IOException e1) { e1.printStackTrace(); }
		}
		catch (IOException e) { 
			e.printStackTrace(); 
		}
		catch (Exception e) {
			e.printStackTrace();
			
			e.printStackTrace(new PrintWriter(sw));
			response.setContentType("text/html");
			try { 
				jsonResponse.addProperty("isSuccess", "0");
				jsonResponse.addProperty("errorMsg", error+sw.toString());
				response.getWriter().print(jsonResponse.toString());
			} catch (IOException e1) { e1.printStackTrace(); }
		}
		return null;
	}
	
	
	public ActionForward OLDPATACCEPTANCE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		InvPatientAcceptanceRespFB fb = (InvPatientAcceptanceRespFB) form;
		ControllerUTIL.setSysdate(request);
				
		return mapping.findForward("OLDPATACCEPTANCE");
	}
	
	public ActionForward AjaxShowReqnDetails(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		InvPatientAcceptanceRespFB fb = (InvPatientAcceptanceRespFB) form;
		ControllerUTIL.setSysdate(request);
		
		HttpSession session = WebUTIL.getSession(request);
		session.setAttribute("patientAcceptanceSessionJson", null); 
		//String sampleAreaName=fb.getSampleAreaName();
		//String sampleAreaCode=fb.getSampleAreaCode();
		//String machineCode = fb.getMachineCode();
		String acceptedToNotAccepted = fb.getAcceptedToNotAccepted();
		
		fb.setIsPatDetailPage("1");
		fb.setShowStatus("1");
		
		InvPatientAcceptanceRespUTL.getUserList(fb, request);
		InvPatientAcceptanceRespUTL.AjaxShowReqnDetails(fb, request);
		
		/*fb.setSampleAreaName(sampleAreaName);
	    fb.setSampleAreaCode(sampleAreaCode);
	    fb.setSelectedmachineCode(machineCode);
		*/
		fb.setAcceptedToNotAccepted(acceptedToNotAccepted);
		
	     return mapping.findForward("OLDPATACCEPTANCE");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		validateToken(request, response);
		InvPatientAcceptanceRespFB fb = (InvPatientAcceptanceRespFB) form;
		HttpSession session = request.getSession();
		String sampleAreaName = fb.getSampleAreaName();
		String sampleAreaCode = fb.getSampleAreaCode();
		String acceptedToNotAccepted = fb.getAcceptedToNotAccepted();
		InvPatientAcceptanceRespUTL.savePatientDetails(fb, request);
		// fb.setSampleAreaCode("-1");
		fb.setSampleAreaCode(sampleAreaCode);
		fb.setSampleAreaName(sampleAreaName);
		fb.setShowStatus("0");
		fb.setIsSampleAreaSelected("1");
		fb.setAcceptedToNotAccepted(acceptedToNotAccepted);
		Object gg = new Object();
		gg=session.getAttribute("isFilterpatacc");
	
	return this.OLDPATACCEPTANCE(mapping, form, request, response);

	}

	
	
	public ActionForward GETDETAILS(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("InvPatientAcceptanceRespACT: GETDETAILS  ");

		InvPatientAcceptanceRespFB fb = (InvPatientAcceptanceRespFB) form;
		System.out.println("patient_type = " + fb.getPatientType());

		ControllerUTIL.setSysdate(request);
		String sampleAreaName = fb.getSampleAreaName();
		String sampleAreaCode = fb.getSampleAreaCode();
		String parcrno = fb.getTempPatCRNo();
		/* Added by prashantMi */
		String acceptedToNotAccepted = fb.getAcceptedToNotAccepted();

		InvPatientAcceptanceRespUTL.setPatientEssentials(fb, request);

		fb.setShowStatus("0");
		fb.setIsSampleAreaSelected("1");
		fb.setSampleAreaName(sampleAreaName);
		fb.setSampleAreaCode(sampleAreaCode);
		fb.setTempPatCRNo(parcrno);
		fb.setAcceptedToNotAccepted(acceptedToNotAccepted);

		return mapping.findForward("NEW");
	}

	
	public ActionForward AJX_DUPLICACY_DAILYLABNO(ActionMapping objMapping_p, ActionForm objForm_p,
			HttpServletRequest objRequest_p, HttpServletResponse objResponse_p)
			throws HisException, Exception, SQLException {
		InvPatientAcceptanceRespFB fb = (InvPatientAcceptanceRespFB) objForm_p;
		// String sampleAreaCode=fb.getSampleAreaCode();
		StringBuffer strBuff = InvPatientAcceptanceRespUTL.checkDailyLabNoDuplicacy(fb, objRequest_p);
		// fb.setSampleAreaCode(sampleAreaCode);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}

	
	public ActionForward AJX_CHECK_AUTO_SAMPLENO_GEN(ActionMapping objMapping_p, ActionForm objForm_p,
			HttpServletRequest objRequest_p, HttpServletResponse objResponse_p)
			throws HisException, Exception, SQLException {
		InvPatientAcceptanceRespFB fb = (InvPatientAcceptanceRespFB) objForm_p;
		String sampleAreaCode = fb.getSampleAreaCode();
		StringBuffer strBuff = InvPatientAcceptanceRespUTL.checkAutoGenFormate(fb, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}


}
