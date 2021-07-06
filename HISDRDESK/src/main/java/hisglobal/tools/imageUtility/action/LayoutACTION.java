package hisglobal.tools.imageUtility.action;

import hisglobal.tools.imageUtility.fb.ImageUtilityFB;
import hisglobal.tools.imageUtility.util.ImageUtilityUTIL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.*;
public class LayoutACTION extends DispatchAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		
		 return  this.INIT(mapping,form,request,response);
	}
	
	public ActionForward INIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response )
	{
		
		ImageUtilityFB formBean=(ImageUtilityFB)form;
		ImageUtilityUTIL util=new ImageUtilityUTIL();
		
		util.populateFormbeanWithUserConfigxml(request,formBean,getServlet().getServletConfig().getServletContext());
		
		request.setAttribute("hmode","INIT");
		
		return mapping.findForward("layout");
	}	
	
	
}
