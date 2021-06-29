package new_investigation.transactions.controller.action;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.invReportHistoryFB;
import new_investigation.transactions.controller.fb.reportDownloadProcessFB;
import new_investigation.transactions.controller.utl.invReportHistoryUTL;
import new_investigation.transactions.controller.utl.reportDownloadProcessUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bbpubliclogin.Email;
import bbpubliclogin.HOTPAlgorithm;
import bbpubliclogin.SendMessageToUser;




public class reportDownloadProcessACT extends CSRFGardTokenAction {

	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
	return this.NEW(mapping,form,request,response);
	}

	
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws Exception
	{
		generateToken(objRequest_p);
		reportDownloadProcessFB objFB = (reportDownloadProcessFB)objForm_p;
		HttpSession session=WebUTIL.getSession(objRequest_p);
		WebUTIL.refreshTransState(objRequest_p);
		ControllerUTIL.setSysdate(objRequest_p);
		//objFB.setShowStatus("0");
		return objMapping_p.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		reportDownloadProcessFB fb=(reportDownloadProcessFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}

	

	
	
	public ActionForward AJX_OTP(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws IOException 
	{
		HttpSession session = objRequest_p.getSession();
		boolean flag=false;
		String val="";
		reportDownloadProcessFB fb=(reportDownloadProcessFB)objForm_p;
		System.out.println("ajax fire:+AJX_OTP");
		String crNo = objRequest_p.getParameter("crNo");
		session.setAttribute("crNo", crNo);
		fb.setCrNo(crNo);
		String otp="";
		
		String validatecrno=reportDownloadProcessUTL.validatecrno(fb, objRequest_p);
		
		
		if(validatecrno.equals("0"))
		{
			
			val="2";   // invalide cr no
		}
		else
		{
			
	     String mobileno=reportDownloadProcessUTL.fetchMobileNo(fb,objRequest_p);
		if(mobileno==null || mobileno.equals("-"))
		{
			
		}
		else
		{
			try {
				otp = HOTPAlgorithm.generateOTP(crNo);
				
				SendMessageToUser.SendSMS("Your OTP is " +  otp + " for Telangana online portal. Please do not share your OTP with anyone.", mobileno);
				
				// add login details 
				reportDownloadProcessUTL.loginInsertDetails(crNo,objRequest_p);
				flag=true;
				
		/*		String OTPMsg = "Hi "+""+ ",<br><br>"
						+"A Signup request has been recieved for username:  " 
						+"<br><br>The One-Time-Password for Donor Signup is: "+
						".<br>"
						+ "It will expire in "+"<br>" 
						+ "If you have not made this request please contact e-RaktKosh immediately"
						+ " <br> http://www.eraktkosh.in/BLDAHIMS/bloodbank/contact.cnt<br><br>" ;
		
		OTPMsg += "eRaktKosh Administrator\nwww.eraktkosh.in";		
		
		String email="chanksgupta32@gmail.com";
		
		SendMessageToUser.SendEmail("eRaktKosh: OTP For Signup", OTPMsg, email, objRequest_p);*/
			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		
		}
		
		if(flag==true)
		{
			val="1";
		}
		else
		{
			val="0";
		}
		
		
		}
		/*StringBuffer strBuff = reportDownloadProcessUTL.autoCannedDetails(fb,objRequest_p);*/
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(val);
		return null;
	}
	
	
	public ActionForward AJX_OTP_VALIDATE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws IOException 
	{
		HttpSession session = objRequest_p.getSession();
		boolean flag=false;
		String val="";
		reportDownloadProcessFB fb=(reportDownloadProcessFB)objForm_p;
		System.out.println("ajax fire:+AJX_OTP_VALIDATE");
		String otp = objRequest_p.getParameter("otp");
	     String crNo=(String) session.getAttribute("crNo");
		
		  fb.setOtp(otp);
		int validate;
			validate = HOTPAlgorithm.getOTPValidationStatus(crNo,otp);
			
			if(validate==1)
			{
				val="1";
			}
			 if(validate==2)
			 {
				 val="2";
			 }
			 if(validate==3)
			 {
				 val="3";
			 }
		
		
		/*StringBuffer strBuff = reportDownloadProcessUTL.autoCannedDetails(fb,objRequest_p);*/
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(val);
		return null;
	}
	
	
	public ActionForward AJAX_GETLIST(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws IOException 
	{
		
		HttpSession session = objRequest_p.getSession();
		boolean flag=false;
		String val="";
		reportDownloadProcessFB fb=(reportDownloadProcessFB)objForm_p;
		System.out.println("ajax fire:+AJAX_GETLIST");
		
	     String crNo=(String) session.getAttribute("crNo");
		System.out.println("crno"+crNo);
		  fb.setCrNo(crNo);
		  session.setAttribute("crNo", crNo);
		  String json=reportDownloadProcessUTL.fetchlist(fb,objRequest_p);
		
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(json);
		return null;
	}
	
	public ActionForward PAT_GETLIST(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws IOException 
	{
		
		HttpSession session = objRequest_p.getSession();
		boolean flag=false;
		String val="";
		reportDownloadProcessFB fb=(reportDownloadProcessFB)objForm_p;
		System.out.println("ajax fire:+AJAX_GETLIST");
		
	     String crNo=(String) session.getAttribute("crNo");
		System.out.println("crno"+crNo);
		  fb.setCrNo(crNo);
		  String json=reportDownloadProcessUTL.Pfetchlist(fb,objRequest_p);
		
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(json);
		return null;
	}
	
	 
	
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("ajax: SHOWPATDETAILS  ");
		reportDownloadProcessFB fb=(reportDownloadProcessFB)form;
		ControllerUTIL.setSysdate(request);
		//fb.setShowStatus("1");
		reportDownloadProcessUTL.showResultEntryPatDetails(fb,request,response);
		  
		return null;
	} 
	
	
	
	public ActionForward SENDMAILREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//isValue=1;
		HttpSession session = request.getSession();
		System.out.println("ajax: sendMailReport  ");
		reportDownloadProcessFB fb=(reportDownloadProcessFB)form;
		//ControllerUTIL.setSysdate(request);
		//fb.setShowStatus("1");
		String base64bytearray=reportDownloadProcessUTL.sendMailReport(fb,request,response);
		
		String email = request.getParameter("email");
		
		 String crNo=(String) session.getAttribute("crNo");
		 fb.setCrNo(crNo);
		 String username=reportDownloadProcessUTL.fetchusername(fb,request);
		
		 
		String OTPMsg = "Dear "+username+ ",\r\n\r\n"
				+"The report as requested by you is attached herewith. Thank You for providing us an opportunity to serve you. " 
				+"\r\n\r\nRegards"+
				"\r\ne-Hospital Management Team, Telengana \r\n"
				+ "Health, Medical & Family Welfare Department, Government of Telangana "+"\r\n" 
				+ "Telephone No.: 040-24658702   Fax No.: 040-24619120   email: tsmsidc.itcell@gmail.com";
		

    //  String email="chanksgupta32@gmail.com";

       SendMessageToUser.SendEmail("Patient Report: Telangana eHMS", OTPMsg, email,base64bytearray, request);
        
      //Email.sendMail("chanksgupta32@gmail.com","Test","xxx","xxx");
       
       String  val="1";
    //   response.getOutputStream().flush();
     //  response.getOutputStream().close();
       response.setHeader("Cache-Control", "no-cache");
           response.getWriter().print(val);
        return null; 
		
	} 
	
	
	public ActionForward AJX_REOTP(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws IOException 
	{
		HttpSession session = objRequest_p.getSession();
		boolean flag=false;
		String val="";
		reportDownloadProcessFB fb=(reportDownloadProcessFB)objForm_p;
		System.out.println("ajax fire:+AJX_REOTP");
		//String crNo = objRequest_p.getParameter("crNo");
	String crNo=	(String) session.getAttribute("crNo");
	System.out.println("reotp crno"+crNo);
		//String crNo=session.getAttribute("crNo")
		fb.setCrNo(crNo);
		String otp="";
		String mobileno=reportDownloadProcessUTL.fetchMobileNo(fb,objRequest_p);
		
		
			otp = HOTPAlgorithm.getResendOTP(crNo);
			
	SendMessageToUser.SendSMS("Your OTP is " +  otp + " for Telangana online portal. Please do not share your OTP with anyone.", mobileno);
			
			flag=true;
		
		
		/*if(flag==true)
		{
			val="1";
		}
		else
		{
			val="0";
		}*/
		
		/*StringBuffer strBuff = reportDownloadProcessUTL.autoCannedDetails(fb,objRequest_p);*/
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(val);
		return null;
	}
}
