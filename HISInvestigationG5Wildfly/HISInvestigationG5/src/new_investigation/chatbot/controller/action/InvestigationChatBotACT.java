package new_investigation.chatbot.controller.action;

//import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.chatbot.controller.fb.investigationChatBotFB;
import new_investigation.chatbot.util.InvestigationChatBotUTIL;
//import new_investigation.transactions.controller.utl.MongoXmlHandler;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
//import org.codehaus.jettison.json.JSONArray;
//import org.codehaus.jettison.json.JSONObject;
//import webServiceReport.Report.InvFetchReport;

public class InvestigationChatBotACT extends CSRFGardTokenAction{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
		
		generateToken(request);
		WebUTIL.refreshTransState(request);
		return mapping.findForward("NEW");
		
	}
	/*public ActionForward AJX_GET_REPORT(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException{
		
		
		 String ipAddressRequestCameFrom = objRequest_p.getRemoteAddr();
		   
		   String url= objRequest_p.getRequestURL().toString();
		    LogCreate(ipAddressRequestCameFrom,source,key,url,"3");
	     
	
		  
		if(crNo == null && requisitionNo==null || source==null || key==null)
			return messageeror("Please Provide Valid Data").toString();
		
		String flag= authfunction(ipAddressRequestCameFrom,source,key);
			
		if(flag.split("#")[0].equalsIgnoreCase("true"))
		{
		InvReportServiceBO objBO = new InvReportServiceBO();
		return objBO.getReport(crNo,requisitionNo,flag);
		}
		else
		{
			
			String msg="Authorization Failed";
			return messageeror(msg).toString();
		}
	}
	*/
//	public ActionForward AJX_GET_REQUISITIONNO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest requestContext,
//			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException{

		public ActionForward AJX_GET_REQUISITIONNO(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response)throws Exception {
		
//		 String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
//		   
//		   String url= requestContext.getRequestURL().toString();
//		    LogCreate(ipAddressRequestCameFrom,source,key,url,"2" );
			investigationChatBotFB fb=(investigationChatBotFB)form;
			
			String crNo = fb.getCrNo();
		//if(crNo == null )//|| source==null || key==null)
			//System.out.println(crNo);
		  
//		String flag= authfunction(ipAddressRequestCameFrom,source,key);
			
		  
		//String days="30";
		/*if(Days!=null)
		{
			days=Days;
		}*/
//		if(flag.split("#")[0].equalsIgnoreCase("true"))
//		{
		//String flag = "1111";
			
		
		//InvReportServiceBO objBO = new InvReportServiceBO();
		String result = InvestigationChatBotUTIL.getRequisitionNo(crNo,request );
		//System.out.println("RESULT "+result);
		//System.out.println("PRINTED "+mapping.findForward(crNo));
		//return mapping.findForward(result);
		//response.setHeader("Cache-Control", "no-cache");
		//AJX_GET_REPORT(mapping,form,request, response, result);
		
		//String report= getReport(crNo, req_dno, hos_code);
		//String reporturl="";
		//byte[] res=getReport(reporturl);
		response.getWriter().print(result);
		return null;
		//return objBO.getRequisitionNo(crNo,days,flag);
		
		/*inv_cb obj = new inv_cb();
		return obj.getRequisitionNo(crNo);*/
//	}
//	else
//	{
//		
//		String msg="Authorization Failed";
//		return messageeror(msg).toString();
//	}
	}
		public ActionForward AJX_GET_REPORT(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response)throws Exception {
			//investigationChatBotFB fb=(investigationChatBotFB)form;
			//String crNo = fb.getCrNo();
			investigationChatBotFB fb=(investigationChatBotFB)form;
			
			String reporturl = fb.getReportUrl();
			System.out.println("dfdf " + reporturl);
			
			
			//request.setAttribute("reporturl", reporturl);
			String result = InvestigationChatBotUTIL.getReport(reporturl);
			response.getWriter().print(result);
			return null;
		}
		
		
		/*public String getReport_my(String crno, String req_dno, String hos_code){
			InvFetchReport obj = new InvFetchReport();
			try {
				return (obj.getPatientReport(crno, req_dno, hos_code));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}*/
		
		/*private  byte[] getReport(String filename) throws IOException
		{
			byte[] byteArray=null;
	         
			byteArray=MongoXmlHandler.getInstance().latestFetchFile(filename);	 
	    	
		    return byteArray;
		}*/
		
		/*public ActionForward AJX_GET_REPORT(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response, String reqNo)throws Exception {
		

			//investigationChatBotFB fb=(investigationChatBotFB)form;			
			//String reqNo = fb.getReqNo();
			String result = InvestigationChatBotUTIL.getReport(reqNo,request);
			response.getWriter().print(result);
			return null;
	}*/
		
		public ActionForward AJX_GET_QUES(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response)throws Exception {
			investigationChatBotFB fb=(investigationChatBotFB)form;			
			String ques = fb.getQues();
			//String ques = "h";
			System.out.println("ques " + ques);	
			//JSONArray result = new JSONArray();
			String result = InvestigationChatBotUTIL.getQues(ques,request);
			response.getWriter().print(result);
			return null;
		}
		
		public ActionForward AJX_GET_ANS(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response)throws Exception {
			investigationChatBotFB fb=(investigationChatBotFB)form;			
			String ans = fb.getAns();
			System.out.println("ans " + ans);	
			//JSONArray result = new JSONArray();
			String result = InvestigationChatBotUTIL.getAns(ans, request);
			response.getWriter().print(result);
			return null;
		}
		public ActionForward show_chatbot(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response)throws Exception {
			investigationChatBotFB fb=(investigationChatBotFB)form;			
			String flg = fb.getShow_chatbot();
			System.out.println("show chatbot " + flg);	
			String result = InvestigationChatBotUTIL.show_chatbot(flg, request);
			response.getWriter().print(result);
			return null;
		}
		public ActionForward show_chatbot_con(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response)throws Exception {
			investigationChatBotFB fb=(investigationChatBotFB)form;			
			String flg = fb.getShow_chatbot_con();
			System.out.println("show chatbot con" + flg);	
			String result = InvestigationChatBotUTIL.show_chatbot_con(flg, request);
			response.getWriter().print(result);
			return null;
		}
		public ActionForward AJX_GET_QUES_CAT(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response)throws Exception {
			investigationChatBotFB fb=(investigationChatBotFB)form;			
			String ques = fb.getQuesCat();
			System.out.println("ques " + ques);	
			String result = InvestigationChatBotUTIL.getQuesCat(ques,request);
			response.getWriter().print(result);
			return null;
		}
		
}
