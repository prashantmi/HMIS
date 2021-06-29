package Investigation_webservice.service;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import Investigation_webservice.bussiness.InvReportServiceBO;
import Investigation_webservice.vo.InvauthVO;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class InvReportService {

	@Resource
	WebServiceContext wsContext;
	

	   @WebMethod
		public String getMobileNo(String crNo ,String source, String key) throws Exception{
		  
		   
		   MessageContext mc = wsContext.getMessageContext();
		    HttpServletRequest requestContext = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 

			
			 String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
			   
			   String url= requestContext.getRequestURL().toString();
			    LogCreate(ipAddressRequestCameFrom,source,key,url,"1");
		     
		
			if(crNo == null || source==null || key==null)
				return messageeror("Please Provide Valid Data").toString();
			
			  String flag= authfunction(ipAddressRequestCameFrom,source,key);
				
			if(flag.split("#")[0].equalsIgnoreCase("true"))
			{
			InvReportServiceBO objBO = new InvReportServiceBO();
			return objBO.getMobileNo(crNo,flag);
		}
		else
		{
			
			String msg="Authorization Failed";
			return messageeror(msg).toString();
		}
		}
		
	 
	/*	public String getMobileNoget(String crNo , String source, String key) throws Exception{
			
		   SOAPMessageContext jaxwsContext = (SOAPMessageContext)wsContext.getMessageContext();
			HttpServletRequest requestContext = (HttpServletRequest)jaxwsContext.get(SOAPMessageContext.SERVLET_REQUEST);
			
			
			 String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
			   
			   String url= requestContext.getRequestURL().toString();
			    LogCreate(ipAddressRequestCameFrom,source,key,url,"1");
		     
			    
			    
			    
		      //   System.out.println(url);
			  
			if(crNo == null || source==null || key==null)
				return messageeror("Please Provide Valid Data").toString();
			
			String flag= authfunction("10.226.29.45",source,key);
				
			  
			if(flag.split("#")[0].equalsIgnoreCase("true"))
			{
			InvReportServiceBO objBO = new InvReportServiceBO();
			return objBO.getMobileNo(crNo,flag);
			}
			else
			{
				
				String msg="Authorization Failed";
				return messageeror(msg).toString();
			}
		}
		*/
		
		
	   @WebMethod
		public String getRequisitionNO(String crNo ,String Days, String source, String key) throws Exception{
			
			
		   MessageContext mc = wsContext.getMessageContext();
		    HttpServletRequest requestContext = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 

			
			
			 String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
			   
			   String url= requestContext.getRequestURL().toString();
			    LogCreate(ipAddressRequestCameFrom,source,key,url,"2");
		     
		
		   
			  
			if(crNo == null || source==null || key==null)
				return messageeror("Please Provide Valid Data").toString();
			  
			String flag= authfunction(ipAddressRequestCameFrom,source,key);
				
			  
			String days="30";
			if(Days!=null)
			{
				days=Days;
			}
			if(flag.split("#")[0].equalsIgnoreCase("true"))
			{
			InvReportServiceBO objBO = new InvReportServiceBO();
			return objBO.getRequisitionNo(crNo,days,flag);
		}
		else
		{
			
			String msg="Authorization Failed";
			return messageeror(msg).toString();
		}
		}
		
	
		/*public String getRequisitionNOGET(String crNo ,String Days, String source, String key) throws Exception{
			
		   SOAPMessageContext jaxwsContext = (SOAPMessageContext)wsContext.getMessageContext();
			HttpServletRequest requestContext = (HttpServletRequest)jaxwsContext.get(SOAPMessageContext.SERVLET_REQUEST);
			
			
			 String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
			   
			   String url= requestContext.getRequestURL().toString();
			    LogCreate(ipAddressRequestCameFrom,source,key,url,"2");
		     
		
			  
			if(crNo == null || source==null || key==null)
				return messageeror("Please Provide Valid Data").toString();
			
			String flag= authfunction("10.226.29.45",source,key);
				
			String days="30";
			if(Days!=null)
			{
				days=Days;
			}
			//System.out.println("zzz"+Base64.class.getProtectionDomain().getCodeSource().getLocation());
			if(flag.split("#")[0].equalsIgnoreCase("true"))
			{
			InvReportServiceBO objBO = new InvReportServiceBO();
			return objBO.getRequisitionNo(crNo,days,flag);
			}
			else
			{
				
				String msg="Authorization Failed";
				return messageeror(msg).toString();
			}
		}*/
		
	   @WebMethod
		public String getReport(String crNo ,String requisitionNo, String source, String key) throws Exception{
			
			
			 MessageContext mc = wsContext.getMessageContext();
			    HttpServletRequest requestContext = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 

				
			    
			String val="";
			String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
			   
			   String url= requestContext.getRequestURL().toString();
			    LogCreate(ipAddressRequestCameFrom,source,key,url,"3");
		     
		
			  
			if(crNo == null && requisitionNo==null || source==null || key==null)
				return messageeror("Please Provide Valid Data").toString();
			
			String flag= authfunction(ipAddressRequestCameFrom,source,key);
				
			if(flag.split("#")[0].equalsIgnoreCase("true"))
			{
			InvReportServiceBO objBO = new InvReportServiceBO();
			val= objBO.getReport(crNo,requisitionNo,flag);
			}
			else
			{
				
				String msg="Authorization Failed";
				val= messageeror(msg).toString();
			}
			return val;
		}
		
	  
	/*	public String getReportGET(String crNo ,String requisitionNo, String source, String key) throws Exception{
		
		   SOAPMessageContext jaxwsContext = (SOAPMessageContext)wsContext.getMessageContext();
			HttpServletRequest requestContext = (HttpServletRequest)jaxwsContext.get(SOAPMessageContext.SERVLET_REQUEST);
			
			
			   String ipAddressRequestCameFrom = requestContext.getRemoteAddr();
			   
			   String url= requestContext.getRequestURL().toString();
			    LogCreate(ipAddressRequestCameFrom,source,key,url,"3");
		     
		
			if(crNo == null || requisitionNo==null || source==null || key==null)
			{
				return messageeror("Please Provide Valid Data").toString();
			}
			
			String flag= authfunction("10.226.29.45",source,key);
				
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

		public static String authfunction(String ipAddressRequestCameFrom,String source,String key)
		{
			String flag="false";
		InvauthVO vo=new InvauthVO();
			try {
				
				InvReportServiceBO objBO = new InvReportServiceBO();
				//return objBO.getReport(crNo,requisitionNo,flag);
				
			vo=	objBO.getauthdata(source);
				
				//vo = InvReportServiceDAO.getauthdata(source);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
				
				if(vo.getIswhitelistreq()!=null && vo.getIswhitelistreq().equals("1"))
				{
				 
					if(vo.getWhitelistip().contains(ipAddressRequestCameFrom))
					{
						
						if(vo.getKey().contains(key))
						{
							flag="true";
							
						}
						
					}
					
				}
				else
				{
					
					flag="true";
					
				}
			
				if(vo.getIsseckeyreq()!=null && vo.getIsseckeyreq().equalsIgnoreCase("1"))
				{
				flag=flag+"#"+vo.getIsseckeyreq()+"#"+vo.getSeckey();
				}
				else
				{
					flag=flag+"#"+vo.getIsseckeyreq();
					
				}
						return flag;
			
		}
		
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


