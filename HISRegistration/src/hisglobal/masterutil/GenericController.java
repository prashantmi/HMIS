package hisglobal.masterutil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;


public class GenericController extends ActionSupport implements Preparable,ServletRequestAware, ServletResponseAware, SessionAware
{

	MasterInterface masterObj = null;
	String cnt_page = "";
	String module_id = "";
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map sessionMap=null;
	StringBuffer br = new StringBuffer(1000);

	public GenericController() 
	{
	}

	public GenericController(MasterInterface masterObj,String cnt_page,String module_id)
	{
		this.masterObj = masterObj;		
		this.cnt_page = cnt_page;
		this.module_id = module_id;
	}

	public String LIST()
	{
		try
		{
			GenericData.LIST(request, masterObj, cnt_page, module_id, sessionMap);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String LISTPAGE() 
	{
		GenericData.LISTPAGE(request, response, masterObj);
		setServletResponse(response);
		return null;
	}
	
	public String SEARCH() 
	{
		GenericData.SEARCH(request, response, masterObj);
		return null;

	}
	public String DEFAULT() 
	{
		GenericData.DEFAULT(request, response, masterObj);
		setServletResponse(response);
		return null;

	}
	public String DELETE() 
	{
		GenericData.DELETE(request, response, masterObj);
		setServletResponse(response);
		return null;
	}

	public String REPORT(String cnt) {
		request.getSession().setAttribute("cnt_page",cnt );
		request.setAttribute("combo", request.getParameterValues("combo"));
		return "report";
	}

	public String VIEWDATA()
	{
		GenericData.VIEWDATA(request, response, masterObj);
		return null;
	}

	public String REPORTDATA()
	{
		GenericData.REPORTDATA(request, response, masterObj);
		return null;
	}

	public void setServletRequest(HttpServletRequest request) 
	{
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	} 

	public void setSession(Map sessionMap) 
	{
		System.out.println("setSession()");
		this.sessionMap=sessionMap;
	}
	
	public Map getSession() 
	{
		System.out.println("setSession()");
		return this.sessionMap;
	}

	public HttpServletRequest getRequest() 
	{
		System.out.println("setSession()");
		return this.request;
	}
	public HttpServletResponse  getResponse() 
	{
		System.out.println("getResponse()");
		return this.response;
	}

	public void prepare() throws Exception 
	{

	}

	public String list()
	 {
		LISTPAGE();
		return null;
	 }
	
	 public String search()
	 {
		SEARCH();
		return null;
	 }
	 
	 public String defaultActive()
	 {
		DEFAULT();
		return null;
	 }
	 
	 public String delete()
	 {
		DELETE();
		return null;
	 }
	 
	 public String fetchView()
	 {
		VIEWDATA();
		return null;
	 } 
	 
	 public String reportData()
	 {
		REPORTDATA();
		return null;
	 } 
	 
	 public String cancel()
	 {
		LIST();
		return SUCCESS;
	 }
	 
	 public String cancelList()
	 {
		//LIST();
		return "init";
	 }
}
