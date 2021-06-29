package Investigation_webservice.service;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

import javax.xml.ws.WebServiceContext;

import javax.xml.ws.handler.MessageContext;


import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import Investigation_webservice.bussiness.InvCrNoBO;
import Investigation_webservice.bussiness.InvReportServiceBO;
import Investigation_webservice.vo.InvauthVO;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class InvCrNoService {

	@Resource
	WebServiceContext wsContext;
	


	   @WebMethod
		public String getDetailsBasedOnCRNumber(String crNo,String hospCode) throws java.lang.Exception{
			
			
		/*
		 * MessageContext mc = wsContext.getMessageContext(); HttpServletRequest
		 * requestContext = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
		 * 
		 * 
		 * 
		 * String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
		 * 
		 * String url= requestContext.getRequestURL().toString();
		 * LogCreate(ipAddressRequestCameFrom,source,key,url,"2");
		 * 
		 * 
		 * 
		 * 
		 * if(crNo == null || source==null || key==null) return
		 * messageeror("Please Provide Valid Data").toString();
		 * 
		 * String flag= authfunction(ipAddressRequestCameFrom,source,key);
		 * 
		 * 
		 * 
		 * if(flag.split("#")[0].equalsIgnoreCase("true")) {
		 */
			String hospitalCode = hospCode; 
			InvCrNoBO objBO = new InvCrNoBO();
			return objBO.getDetailsBasedOnCrNo(crNo,hospitalCode);
		}

	/*
	 * else {
	 * 
	 * String msg="Authorization Failed"; return messageeror(msg).toString(); }
	 */
	//	}
		
	
	/*
	 * public static String authfunction(String ipAddressRequestCameFrom,String
	 * source,String key) { String flag="false"; InvauthVO vo=new InvauthVO(); try {
	 * 
	 * InvReportServiceBO objBO = new InvReportServiceBO(); //return
	 * objBO.getReport(crNo,requisitionNo,flag);
	 * 
	 * vo= objBO.getauthdata(source);
	 * 
	 * //vo = InvReportServiceDAO.getauthdata(source); } catch (Exception e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * if(vo.getIswhitelistreq()!=null && vo.getIswhitelistreq().equals("1")) {
	 * 
	 * if(vo.getWhitelistip().contains(ipAddressRequestCameFrom)) {
	 * 
	 * if(vo.getKey().contains(key)) { flag="true";
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } else {
	 * 
	 * flag="true";
	 * 
	 * }
	 * 
	 * if(vo.getIsseckeyreq()!=null && vo.getIsseckeyreq().equalsIgnoreCase("1")) {
	 * flag=flag+"#"+vo.getIsseckeyreq()+"#"+vo.getSeckey(); } else {
	 * flag=flag+"#"+vo.getIsseckeyreq();
	 * 
	 * } return flag;
	 * 
	 * }
	 */
		public static JSONArray messageeror(String message) throws JSONException
		{
			JSONArray jarray2 = new JSONArray();
			JSONArray jarraynew = new JSONArray();
			JSONObject jobj = new JSONObject();
			JSONObject jobjnew = new JSONObject();
			jobjnew.put("TotalCount", "0");
			jobjnew.put("RequestStatus", "3");
			jobjnew.put("message", message);
			jobjnew.put("data", "");
			
			return jarraynew.put(jobjnew);
			
		}
		
		
		public static void LogCreate(String ipAddressRequestCameFrom,String source,String key,String url,String type)
		{
			
			InvReportServiceBO objBO = new InvReportServiceBO();
			//return objBO.getReport(crNo,requisitionNo,flag);
			
			objBO.logDetail(ipAddressRequestCameFrom,source,key,url,type);
		
		}
		
	}


