package hisglobal.masterxml.masterworkshop;

import hisglobal.presentation.WebUTIL;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MasterRouter extends HttpServlet
{
	//private static final String LIST_PAGE = "list";
	/*private static final String ADD_PAGE = "add";
	private static final String MODIFY_PAGE = "modify";
	private static final String VEIW_PAGE = "veiw";
	private static final String REPORT_PAGE = "report";*/

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//forward the request to the appropriate hadler --> list add...
		routeToRequestedMaster(request).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

	private RequestDispatcher routeToRequestedMaster(HttpServletRequest request)
	{
		String strUrl = request.getRequestURL().toString();
		RequestDispatcher rd = null;
		//System.out.println("RouteToRequestedMaster...");
		/////refreshing master related session///
		WebUTIL.refreshMasterSession(request);
		//if(strUrl.toLowerCase().indexOf(LIST_PAGE) != -1){
		int lstidx = strUrl.lastIndexOf(".");
		int begidx = strUrl.lastIndexOf("masterWorkshop/") + "masterWorkshop/".length();
		HttpSession ses = request.getSession();
		String mstFileName = strUrl.substring(begidx, lstidx);
		System.out.println("mstFilename:::::" + mstFileName);
		ses.setAttribute("mstFilename", mstFileName);
		WebUTIL.setMasterAttributeInSession(request, "mstFilename", mstFileName);
		//request.setAttribute("mstFilename",mstFileName);			
		//System.out.println("List..."); 
		//System.out.println("rd:  " + rd);
		rd = request.getRequestDispatcher("/masterWorkshop/mstHandlerListAction.cnt");
		/* }else 
		 if(strUrl.toLowerCase().indexOf(ADD_PAGE) != -1)
		 {
		     rd = request.getRequestDispatcher("/masterWorkshop/mstHandlerAddAction.cnt");		
		}
		
		 else    
		     rd = request.getRequestDispatcher("/masterWorkshop/mstErrorPage.cnt");*/
		return rd;
	}
}
