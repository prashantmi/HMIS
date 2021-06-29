package hisglobal.tools.imageUtility.action;

import hisglobal.tools.imageUtility.fb.ImageServletFB;
import hisglobal.tools.imageUtility.fb.ImageUtilityFB;
import hisglobal.tools.imageUtility.fb.VideoServletFB;
import hisglobal.tools.imageUtility.util.ImageServletUTIL;
import hisglobal.tools.imageUtility.util.ImageUtilityUTIL;
import hisglobal.tools.imageUtility.util.VideoServletUTIL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class VideoServletACTION extends DispatchAction{

	ServletContext sc=null;
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		return INIT(mapping, form, request, response);
		
	}
	
	public ActionForward INIT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response )
	{
		
		sc = getServlet().getServletContext();
		System.out.println("INSIDE VIDEOLIST");
		VideoServletFB formBean=(VideoServletFB)form;
		VideoServletUTIL util=new VideoServletUTIL();
		
		util.populateVideoList(request,formBean);
		//util.populateFormbeanWithUserConfigxml(request,formBean);
		return mapping.findForward("VIDEOLIST");
	}
	public ActionForward UPLOAD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response )
	{
		
		sc = getServlet().getServletContext();
		System.out.println("INSIDE VIDEOLIST");
		VideoServletFB formBean=(VideoServletFB)form;
		VideoServletUTIL util=new VideoServletUTIL();
		
		util.populateVideoList(request,formBean);
		//util.populateFormbeanWithUserConfigxml(request,formBean);
		return mapping.findForward("VIDEOLIST");
	}
	
}
