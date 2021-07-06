package hisglobal.tools.imageUtility.action;

import hisglobal.tools.imageUtility.fb.CommonServletFB;
import hisglobal.tools.imageUtility.fb.ImageServletFB;
import hisglobal.tools.imageUtility.fb.ImageUtilityFB;
import hisglobal.tools.imageUtility.util.CommonServletUTIL;
import hisglobal.tools.imageUtility.util.ImageServletUTIL;
import hisglobal.tools.imageUtility.util.ImageUtilityUTIL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CommonServletACTION extends DispatchAction{

	ServletContext sc=null;
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		
		 return  this.INIT(mapping,form,request,response);
	}
	
	public ActionForward INIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response )
	{
			sc = getServlet().getServletContext();
			System.out.println("INSIDE COMMONLIST");
			CommonServletFB formBean=(CommonServletFB)form;
			CommonServletUTIL util=new CommonServletUTIL();
			util.populateImageList(request,formBean);
			
			return mapping.findForward("COMMONLIST");
		
	}
	public ActionForward UPLOAD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response )
	{
		
		sc = getServlet().getServletContext();
		System.out.println("INSIDE IMAGELIST");
		CommonServletFB formBean=(CommonServletFB)form;
		CommonServletUTIL util=new CommonServletUTIL();
		util.populateImageList(request,formBean);
		
		return mapping.findForward("COMMONLIST");	
		
		
	}
	
}
