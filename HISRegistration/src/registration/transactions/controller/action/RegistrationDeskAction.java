package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: Dec 2013
 */
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;


public class RegistrationDeskAction implements ServletRequestAware, ServletResponseAware, SessionAware
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map sessionMap=null;
	
	 	 
	 public String execute()
	 {
		   return "success";
	 
	 }
	  
		
	public void setServletRequest(HttpServletRequest request) {
		System.out.println("Inside RegistrationDeskDuplicateAction ::: setServletRequest()");
		this.request=request;
		
	}

	
	public void setServletResponse(HttpServletResponse response) {
		System.out.println("Inside RegistrationDeskDuplicateAction ::: setServletResponse()");
		this.response=response;
		
	} 
	
	public void setSession(Map sessionMap) {
		System.out.println("setSession()");
		this.sessionMap=sessionMap;
		
	}
	
	}
