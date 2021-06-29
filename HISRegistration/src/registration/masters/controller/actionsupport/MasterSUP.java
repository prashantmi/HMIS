/**
 * 
 */
package registration.masters.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 23-Jan-2014
 */
public class MasterSUP extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware
{
	

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	protected Map mapSession;	
	
			
	
	@Override
	public void setSession(Map sessionmap) {
		this.mapSession=sessionmap;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;		
	}



	public HttpServletRequest getRequest() {
		return request;
	}



	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}



	public HttpServletResponse getResponse() {
		return response;
	}



	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}



	public Map getMapSession() {
		return mapSession;
	}



	public void setMapSession(Map mapSession) {
		this.mapSession = mapSession;
	}



	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}


}
